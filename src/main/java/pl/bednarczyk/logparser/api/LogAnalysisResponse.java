package pl.bednarczyk.logparser.api;

import java.util.List;

public record LogAnalysisResponse(String rootCause,
                                  String affectedComponent,
                                  Severity severity,
                                  List<String> suggestedFixSteps,
                                  boolean isTransientInfrastructureError) {

}
