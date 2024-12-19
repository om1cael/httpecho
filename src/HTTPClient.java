import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTPClient {
    final static String request =
            """
            GET /index.html HTTP/1.1
            Host: example.com
            User-Agent: ExampleClient/1.0
            Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
            """;

    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 8080);
            PrintWriter dataSend = new PrintWriter(socket.getOutputStream());
            BufferedReader dataReceived = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            dataSend.write(request);
            dataSend.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
