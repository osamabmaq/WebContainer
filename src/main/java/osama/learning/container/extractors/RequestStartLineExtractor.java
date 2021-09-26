package osama.learning.container.extractors;

import osama.learning.container.exception.HttpException;
import osama.learning.container.request.RequestMethod;
import osama.learning.container.request.RequestStartLine;
import osama.learning.container.response.ResponseStatus;

import java.io.IOException;

public class RequestStartLineExtractor implements Extractor<RequestStartLine, String> {
    private static final RequestPathExtractor pathExtractor = new RequestPathExtractor();

    @Override
    public RequestStartLine extract(String startLine) throws IOException {
        String[] startLineElements = startLine.trim().split(" ");
        return new RequestStartLine(
                getMethod(startLineElements[0]),
                pathExtractor.extract(startLineElements[1]),
                startLineElements[2]
        );
    }

    private RequestMethod getMethod(String startLineElement) {
        try {
            return RequestMethod.valueOf(startLineElement);
        } catch (IllegalArgumentException e) {
            throw new HttpException(ResponseStatus.METHOD_NOT_ALLOWED);
        }
    }
}
