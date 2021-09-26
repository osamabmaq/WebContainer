package osama.learning.container.response;

public class ResponseStartLine {
    private final String httpVersion = "Http/1.1";
    private ResponseStatus responseStatus;

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    @Override
    public String toString() {
        return httpVersion + " " + responseStatus;
    }
}
