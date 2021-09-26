package osama.learning.container.request;

import java.util.Map;

public record RequestPath(String pathWithNoParams, Map<String, String> queryParameters) {
    @Override
    public String toString() {
        StringBuilder fullPath = new StringBuilder();
        fullPath.append(this.pathWithNoParams);
        if (!queryParameters.isEmpty()) {
            fullPath.append("?");
            queryParameters.forEach((key, value) ->
                    fullPath.append(key).append("=").append(value));
        }
        return fullPath.toString();
    }
}
