package com.om1cael.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8080);
            ExecutorService executorService = Executors.newCachedThreadPool()
        ) {
            System.out.println("httpecho is running!");

            while(true) {
                HTTPServerThread httpServerThread = new HTTPServerThread(serverSocket.accept());
                executorService.execute(httpServerThread);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}