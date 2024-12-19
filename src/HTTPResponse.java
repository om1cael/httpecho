import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class HTTPResponse {
    public String getOKResponse(String requestLine) {
        HTTPParser httpParser = new HTTPParser();

        return "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html; charset=UTF-8\r\n"
                + "Content-Length: " + httpParser.getContentLength(getResource(requestLine)) + "\r\n"
                + "\r\n"
                + httpParser.getHTMLContent(getResource(requestLine)).getFirst();
    }

    public String notFoundResponse() {
        return "HTTP/1.1 404 Not Found\r\n"
                + "Content-Type: text/html; charset=UTF-8\r\n"
                + "\r\n"
                + "This page was not found."
                + "\r\n";
    }

    public String notGetResponse() {
        return "HTTP/1.1 405 Method Not Allowed\r\n"
                + "Allow: GET\r\n"
                + "\r\n";
    }

    public static Path getResource(String requestLine) {
        Path resourcePath = Path.of(Arrays.asList(requestLine.split(" ")).get(1).replace("/", ""));
        if(Files.isDirectory(resourcePath)) return Path.of("index.html");

        return resourcePath;
    }

    public boolean resourceExists(String requestLine) {
        return Files.exists(getResource(requestLine));
    }

    public boolean isGET(String requestLine) {
        return requestLine.contains("GET");
    }
}
