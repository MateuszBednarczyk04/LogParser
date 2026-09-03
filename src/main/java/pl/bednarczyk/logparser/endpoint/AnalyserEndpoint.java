package pl.bednarczyk.logparser.endpoint;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.bednarczyk.logparser.api.AnalyseLogRequest;
import pl.bednarczyk.logparser.api.LogAnalysisResponse;
import pl.bednarczyk.logparser.service.AnalyserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/analyser")
class AnalyserEndpoint {

  private final AnalyserService analyserService;

  @PostMapping
  public LogAnalysisResponse analyse(@RequestBody final AnalyseLogRequest request) {
    return analyserService.analyse(request.rawLog());
  }

}
