package osama.learning.container;

import java.util.HashMap;
import java.util.Map;

public class HttpHeaders {
    private final Map<String, String> headersMap = new HashMap<>();

    public void setHeader(String header, String value) {
        headersMap.put(header, value);
    }

    public boolean isEmpty() {
        return headersMap.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder headersString = new StringBuilder();
        headersMap.forEach((key, value) ->
                headersString.append(key).append(": ").append(value).append("\n")
        );
        return headersString.toString().trim();
    }
}
