package osama.learning.container.response;

public enum ResponseStatus {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_FOUND(404, "Not Found"),
    BAD_REQUEST(400, "Bad Request"),
    METHOD_NOT_ALLOWED(405, "Method Not Alowed"),
    OK(200, "OK");

    private final int statusCode;
    private final String statusString;

    ResponseStatus(int statusCode, String statusString) {
        this.statusCode = statusCode;
        this.statusString = statusString;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusString() {
        return statusString;
    }

    @Override
    public String toString() {
        return statusCode + " " + statusString;
    }
}
