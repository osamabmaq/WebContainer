package osama.learning.container.extractors;

import osama.learning.container.HttpHeaders;
import osama.learning.container.request.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class RequestExtractor implements Extractor<Request, InputStream> {
    private final RequestStartLineExtractor startLineExtractor = new RequestStartLineExtractor();

    public Request extract(InputStream in) throws IOException {
        BufferedReader lineReader = new BufferedReader(new InputStreamReader(in));
        return new Request(
                startLineExtractor.extract(lineReader.readLine()),
                extractHeaders(lineReader),
                extractBody(lineReader)
        );
    }

    private HttpHeaders extractHeaders(BufferedReader lineReader) throws IOException {
        String line = lineReader.readLine();
        HttpHeaders headers = new HttpHeaders();
        while (!line.isBlank()) {
            String[] headerNameValue = line.split(": ");
            headers.setHeader(headerNameValue[0], headerNameValue[1]);
            line = lineReader.readLine();
        }
        return headers;
    }

    private String extractBody(BufferedReader lineReader) throws IOException {
        StringBuilder body = new StringBuilder();
        while (lineReader.ready())
            body.append(lineReader.readLine());
        return body.toString();
    }
}
