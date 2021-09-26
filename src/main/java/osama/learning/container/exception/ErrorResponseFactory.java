package osama.learning.container.exception;

import osama.learning.container.HttpHeaders;
import osama.learning.container.response.Response;
import osama.learning.container.response.ResponseStartLine;
import osama.learning.container.response.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ErrorResponseFactory {
    private final Map<ResponseStatus, Response> errorResponses = new HashMap<>();

    private static final ErrorResponseFactory instance = new ErrorResponseFactory();

    private ErrorResponseFactory() {
    }

    public static ErrorResponseFactory getInstance() {
        return instance;
    }

    public Response getResponse(ResponseStatus status) {
        if (errorResponses.containsKey(status))
            return errorResponses.get(status);
        Response response = generateResponse(status);
        errorResponses.put(status, response);
        return response;
    }

    private Response generateResponse(ResponseStatus status) {
        Response response = new Response();
        response.setResponseStatus(status);
        response.appendToBody(generateBody(status));
        return response;
    }

    private String generateBody(ResponseStatus status) {
        StringBuilder body = new StringBuilder();
        body.append("<html><head><title>")
                .append(status.getStatusString())
                .append(" ")
                .append(status.getStatusCode())
                .append("</title></head><body><h1>")
                .append(status.getStatusString())
                .append("</h1></body></html>");
        return body.toString();
    }
}
