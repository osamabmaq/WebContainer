package osama.learning.container.exception;

import osama.learning.container.response.Response;
import osama.learning.container.response.ResponseStatus;

public class HttpException extends RuntimeException {
    private final Response errorResponse;
    protected static final ErrorResponseFactory responseFactory = ErrorResponseFactory.getInstance();

    public HttpException(ResponseStatus status) {
        this.errorResponse = responseFactory.getResponse(status);
    }

    public Response getErrorResponse() {
        return errorResponse;
    }
}
