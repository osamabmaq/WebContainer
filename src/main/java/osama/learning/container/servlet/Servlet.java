package osama.learning.container.servlet;

import osama.learning.container.exception.HttpException;
import osama.learning.container.request.Request;
import osama.learning.container.response.Response;
import osama.learning.container.response.ResponseStartLine;
import osama.learning.container.response.ResponseStatus;

public class Servlet {
    public Response respond(Request request) {
        return switch (request.startLine().method()) {
            case GET -> respondToGet(request, new Response());
            case POST -> respondToPost(request, new Response());
        };
    }

    protected Response respondToGet(Request request, Response response) {
        throw new HttpException(ResponseStatus.METHOD_NOT_ALLOWED);
    }

    protected Response respondToPost(Request request, Response response) {
        throw new HttpException(ResponseStatus.METHOD_NOT_ALLOWED);
    }
}