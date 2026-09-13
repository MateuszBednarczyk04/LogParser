package pl.bednarczyk.logparser.service.impl;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import pl.bednarczyk.logparser.configuration.DocumentsProperties;
import pl.bednarczyk.logparser.service.RagService;

import java.util.List;

@Service
@EnableConfigurationProperties(DocumentsProperties.class)
class InMemoryRagImpl implements RagService {

  private final EmbeddingStore embeddingStore;

  InMemoryRagImpl(final DocumentsProperties documentsProperties) {
    final List<Document> documents = FileSystemDocumentLoader.loadDocuments(documentsProperties.getPath());
    this.embeddingStore = new InMemoryEmbeddingStore();
    EmbeddingStoreIngestor.ingest(documents, embeddingStore);
  }

  @Override
  public EmbeddingStoreContentRetriever embeddingStoreContentRetriever() {
    return EmbeddingStoreContentRetriever.from(embeddingStore);
  }
}
