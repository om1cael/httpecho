import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HTTPParser {
    public List<String> getHTMLContent(Path resourcePath) {
        try(BufferedReader reader = new BufferedReader(Files.newBufferedReader(resourcePath))) {
            int htmlContentLength = 0;
            String line;
            StringBuilder content = new StringBuilder();

            while((line = reader.readLine()) != null) {
                content.append(line).append("\r\n");;
                htmlContentLength += (line + "\r\n").length();
            }


            return List.of(content.toString(), String.valueOf(htmlContentLength / 2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getContentLength(Path resourcePath) {
        String response = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html; charset=UTF-8\r\n"
                + "\r\n"
                + getHTMLContent(resourcePath).getFirst();

        return response.length() + Integer.parseInt(getHTMLContent(resourcePath).getLast());
    }
}
