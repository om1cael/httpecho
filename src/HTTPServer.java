import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();
            PrintWriter dataSend = new PrintWriter(socket.getOutputStream());
            BufferedReader clientData = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            handleRequest(clientData, dataSend);
            dataSend.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void handleRequest(BufferedReader request, PrintWriter dataSend) throws IOException {
        HTTPResponse httpResponse = new HTTPResponse();
        String requestLine = request.readLine();

        if(!httpResponse.isGET(requestLine)) {
            dataSend.write(httpResponse.notGetResponse());
            return;
        }

        if(!httpResponse.resourceExists(requestLine)) {
            dataSend.write(httpResponse.notFoundResponse());
            return;
        }

        dataSend.write(httpResponse.getOKResponse(requestLine));
    }
}