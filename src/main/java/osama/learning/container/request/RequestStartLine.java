package osama.learning.container.request;

public record RequestStartLine(RequestMethod method, RequestPath path, String httpVersion) {
    @Override
    public String toString() {
        return method + " " + path.toString() + " " + httpVersion;
    }
}
