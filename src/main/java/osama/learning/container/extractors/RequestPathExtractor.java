package osama.learning.container.extractors;

import osama.learning.container.request.RequestPath;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RequestPathExtractor implements Extractor<RequestPath, String> {
    @Override
    public RequestPath extract(String pathLine) {
        String[] pathElements = pathLine.split("[?]");
        return new RequestPath(pathElements[0], getQueryParameters(pathElements));
    }

    private Map<String, String> getQueryParameters(String[] pathElements) {
        if (pathElements.length == 1)
            return new HashMap<>();
        Map<String, String> queryParameters = new HashMap<>();
        Arrays.stream(pathElements[1].split("&")).forEach(parameter -> {
            Map.Entry<String, String> queryParameterEntry = getQueryParameterEntry(parameter.split("[=]"));
            queryParameters.put(queryParameterEntry.getKey(), queryParameterEntry.getValue());
        });
        return queryParameters;
    }

    private Map.Entry<String, String> getQueryParameterEntry(String[] parameterNameValue) {
        if (parameterNameValue.length != 2)
            return Map.entry(parameterNameValue[0], "");
        return Map.entry(parameterNameValue[0], parameterNameValue[1]);
    }
}
