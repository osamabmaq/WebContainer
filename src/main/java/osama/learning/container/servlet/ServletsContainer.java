package osama.learning.container.servlet;

import osama.learning.container.exception.HttpException;
import osama.learning.container.response.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ServletsContainer {
    private final Map<String, Servlet> servlets = new HashMap<>();

    private static final ServletsContainer instance = new ServletsContainer();

    private ServletsContainer() {
    }

    public static ServletsContainer getInstance() {
        return instance;
    }

    public void add(String path, Servlet servlet) {
        if (isPresent(path))
            throw new IllegalArgumentException("Path " + path + " is already bound to a servlet");
        servlets.put(path, servlet);
    }

    public boolean isPresent(String path) {
        return servlets.containsKey(path);
    }

    public Servlet getServlet(String path) {
        return Optional.ofNullable(servlets.get(path)).orElseThrow(() ->
                new HttpException(ResponseStatus.NOT_FOUND)
        );
    }
}
