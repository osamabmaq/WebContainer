package osama.learning.container;

import osama.learning.container.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class ServerListener {
    private static final ExecutorService requestHandlers = ServerThreadPools.getRequestHandlers();

    private final int port;
    private final ServerSocket serverSocket;

    public ServerListener(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(this.port);
    }

    public void listen() {
        while (true) {
            try {
                requestHandlers.execute(new RequestHandler(serverSocket.accept()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getPort() {
        return port;
    }
}
