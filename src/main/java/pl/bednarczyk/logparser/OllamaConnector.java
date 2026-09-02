package pl.bednarczyk.logparser;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

@Service
class OllamaConnector {

  private final ChatModel model;

  OllamaConnector() {
    this.model = OllamaChatModel.builder()
        .baseUrl("http://localhost:11434")
        .temperature(0.0)
        .logRequests(true)
        .logResponses(true)
        .modelName("llama3.2:1b")
        .build();
  }


  public String message(final String message) {
    return model.chat(message);
  }

}
