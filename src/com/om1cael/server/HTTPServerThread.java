package com.om1cael.server;

import com.om1cael.utils.HTTPParser;
import com.om1cael.utils.HTTPResponse;

import java.io.*;
import java.net.Socket;

public class HTTPServerThread implements Runnable {
    Socket socket;

    public HTTPServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("New request by " + socket.getLocalAddress() + " at thread " + Thread.currentThread().getName());

        try(DataOutputStream dataToSend = new DataOutputStream(socket.getOutputStream());
            BufferedReader clientRequest = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            if(!socket.isClosed()) {
                this.handleRequest(clientRequest, dataToSend);
                dataToSend.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(BufferedReader request, DataOutputStream dataToSend) throws IOException {
        HTTPResponse httpResponse = new HTTPResponse();
        HTTPParser httpParser = new HTTPParser();
        String requestLine = request.readLine();

        if(!httpResponse.isGET(requestLine)) {
            dataToSend.writeUTF(httpResponse.notGetResponse());
            return;
        }

        if(!httpResponse.resourceExists(requestLine)) {
            dataToSend.writeUTF(httpResponse.notFoundResponse());
            return;
        }

        if(httpResponse.getImageExtension(requestLine) != null) {
            sendImage(dataToSend, httpResponse, httpParser, requestLine);
            return;
        }

        dataToSend.writeUTF(httpResponse.getOKResponse(requestLine));
    }

    public void sendImage(DataOutputStream dataToSend, HTTPResponse httpResponse, HTTPParser httpParser, String requestLine) {
        final String headers = httpResponse.getImageResponse(requestLine);

        byte[] buffer = new byte[4096];
        byte[] imageContent = httpParser.getImageContent(HTTPResponse.getResource(requestLine));
        int size;

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageContent)) {
            dataToSend.write(headers.getBytes());

            while((size = byteArrayInputStream.read(buffer)) != -1) {
                dataToSend.write(buffer, 0, size);
            }

            dataToSend.flush();
        } catch (IOException e) {
            System.err.println("Error while sending the image: " + e.getMessage());
        }
    }
}
