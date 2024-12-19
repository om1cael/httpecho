public class HTTPResponse {
    public String noGetResponse() {
        return """
                HTTP/1.1 405 Method Not Allowed
                Allow: GET
               """;
    }

    public boolean isGET(String requestLine) {
        return requestLine.contains("GET");
    }
}
