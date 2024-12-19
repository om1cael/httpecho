import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTPServerThread implements Runnable {
    Socket socket;

    public HTTPServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("New connection by " + socket.getLocalAddress() + " at thread " + Thread.currentThread().getName());

        try(PrintWriter dataToSend = new PrintWriter(socket.getOutputStream());
            BufferedReader clientRequest = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            this.handleRequest(clientRequest, dataToSend);
            dataToSend.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(BufferedReader request, PrintWriter dataToSend) throws IOException {
        HTTPResponse httpResponse = new HTTPResponse();
        String requestLine = request.readLine();

        if(!httpResponse.isGET(requestLine)) {
            dataToSend.write(httpResponse.notGetResponse());
            return;
        }

        if(!httpResponse.resourceExists(requestLine)) {
            dataToSend.write(httpResponse.notFoundResponse());
            return;
        }

        if(httpResponse.isCSS(requestLine)) {
            dataToSend.write(httpResponse.getCSSResponse(requestLine));
            return;
        }

        dataToSend.write(httpResponse.getOKResponse(requestLine));
    }
}
