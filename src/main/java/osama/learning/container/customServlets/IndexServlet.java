package osama.learning.container.customServlets;

import osama.learning.container.ServerStarter;
import osama.learning.container.exception.HttpException;
import osama.learning.container.request.Request;
import osama.learning.container.response.Response;
import osama.learning.container.response.ResponseStatus;
import osama.learning.container.servlet.Servlet;

import java.io.IOException;

public class IndexServlet extends Servlet {
    @Override
    protected Response respondToGet(Request request, Response response) {
        response.setResponseStatus(ResponseStatus.OK);
        response.appendToBody(getFileAsString("/index.html"));
        return response;
    }

    private String getFileAsString(String path) {
        try {
            return new String(ServerStarter.class.getResourceAsStream(path).readAllBytes());
        } catch (IOException e) {
            throw new HttpException(ResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
