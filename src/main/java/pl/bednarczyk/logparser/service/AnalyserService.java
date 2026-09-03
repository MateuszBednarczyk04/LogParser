package pl.bednarczyk.logparser.service;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import pl.bednarczyk.logparser.api.LogAnalysisResponse;

@AiService
public interface AnalyserService {

  @UserMessage("Przeanalizuj poniższy log z błędem i sformatuj wynik zgodnie ze schematem: {{log}}")
  LogAnalysisResponse analyse(@V("log") final String rawLog);

}
