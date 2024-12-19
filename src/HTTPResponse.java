import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class HTTPResponse {
    public String notFoundResponse() {
        return """
                HTTP/1.1 404 Not Found
                Content-Type: text/html; charset=UTF-8
               """;
    }

    public String noGetResponse() {
        return """
                HTTP/1.1 405 Method Not Allowed
                Allow: GET
               """;
    }

    public boolean resourceExists(String requestLine) {
        Path resourcePath = Path.of(Arrays.asList(requestLine.split(" ")).get(1));
        return Files.exists(resourcePath);
    }

    public boolean isGET(String requestLine) {
        return requestLine.contains("GET");
    }
}
