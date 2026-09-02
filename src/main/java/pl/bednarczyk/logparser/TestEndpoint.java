package pl.bednarczyk.logparser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/api/v1/test")
class TestEndpoint {

  private final OllamaConnector connector;

  @PostMapping
  public String test(@RequestBody final TestRequest testRequest) {
    return connector.message(testRequest.message);
  }

  record TestRequest(String message) {

  }

}
