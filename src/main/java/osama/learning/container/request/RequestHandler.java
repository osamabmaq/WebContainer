package osama.learning.container.request;

import osama.learning.container.ServerThreadPools;
import osama.learning.container.exception.ErrorResponseFactory;
import osama.learning.container.exception.HttpException;
import osama.learning.container.extractors.RequestExtractor;
import osama.learning.container.response.Response;
import osama.learning.container.response.ResponseSender;
import osama.learning.container.servlet.Servlet;
import osama.learning.container.servlet.ServletsContainer;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static osama.learning.container.response.ResponseStatus.*;

public class RequestHandler implements Runnable {
    private static final ServletsContainer servlets = ServletsContainer.getInstance();
    private static final RequestExtractor requestExtractor = new RequestExtractor();
    private static final ExecutorService responseSenders = ServerThreadPools.getResponseSenders();
    private static final ErrorResponseFactory errorResponses = ErrorResponseFactory.getInstance();

    private final Socket client;

    public RequestHandler(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        Response response = null;
        try {
            Request request = requestExtractor.extract(client.getInputStream());
            Servlet servlet = servlets.getServlet(request.startLine().path().pathWithNoParams());
            response = servlet.respond(request);
        } catch (HttpException e) {
            response = e.getErrorResponse();
        } catch (Exception e) {
            response = errorResponses.getResponse(INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        } finally {
            sendResponseToResponseSenders(response);
        }
    }

    private void sendResponseToResponseSenders(Response response) {
        if (response != null)
            responseSenders.execute(new ResponseSender(response, client));
        else {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
