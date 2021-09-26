package osama.learning.container.request;

import osama.learning.container.HttpHeaders;

public record Request(RequestStartLine startLine, HttpHeaders httpHeaders, String body) {
    @Override
    public String toString() {
        return startLine
                + "\n"
                + (httpHeaders.isEmpty() ? "\r\n" : httpHeaders + "\n\r\n")
                + body;
    }
}