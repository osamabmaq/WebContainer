package osama.learning.container;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerThreadPools {
    private static final ExecutorService requestHandlers = Executors.newCachedThreadPool();
    private static final ExecutorService responseSenders = Executors.newCachedThreadPool();

    public static ExecutorService getRequestHandlers(){
        return requestHandlers;
    }

    public static ExecutorService getResponseSenders(){
        return responseSenders;
    }
}
