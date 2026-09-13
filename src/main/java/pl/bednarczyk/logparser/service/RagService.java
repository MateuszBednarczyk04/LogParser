package pl.bednarczyk.logparser.service;

import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;

public interface RagService {

  EmbeddingStoreContentRetriever embeddingStoreContentRetriever();
}
