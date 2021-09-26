package osama.learning.container.customServlets;

import osama.learning.container.request.Request;
import osama.learning.container.response.Response;
import osama.learning.container.response.ResponseStatus;
import osama.learning.container.servlet.Servlet;

import java.time.LocalDateTime;

public class CurrentTimeServlet extends Servlet {
    @Override
    protected Response respondToGet(Request request, Response response) {
        response.setResponseStatus(ResponseStatus.OK);
        response.appendToBody("<h1>").appendToBody(LocalDateTime.now().toString()).appendToBody("</h1>");
        return response;
    }
}
