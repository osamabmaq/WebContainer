package osama.learning.container.request;

public enum RequestMethod {
    GET("GET"),
    POST("POST");
    private final String name;

    RequestMethod(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
