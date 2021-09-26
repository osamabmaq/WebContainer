package osama.learning.container.response;

import osama.learning.container.HttpHeaders;

public class Response {
    private final ResponseStartLine startLine;
    private final HttpHeaders httpHeaders;
    private final StringBuilder body;

    public Response() {
        this.startLine = new ResponseStartLine();
        this.httpHeaders = new HttpHeaders();
        this.body = new StringBuilder();
    }

    public ResponseStatus getResponseStatus() {
        return startLine.getResponseStatus();
    }

    public void setResponseStatus(ResponseStatus status) {
        startLine.setResponseStatus(status);
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public Response appendToBody(String str) {
        body.append(str);
        return this;
    }

    @Override
    public String toString() {
        return startLine
                + "\n"
                + (httpHeaders.isEmpty() ? "\r\n" : httpHeaders + "\n\r\n")
                + body;
    }
}
