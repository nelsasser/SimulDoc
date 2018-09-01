package main.webapp.com.nelsasser.app;

import com.sun.net.httpserver.*;
import main.webapp.com.nelsasser.app.server.handler.Handler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) {

        try {
            //create a new server on port 5000
            int port = 5000;
            HttpServer server = HttpServer.create(new InetSocketAddress(port), 100);

            //create a handler that will deal with traffic to the server
            server.createContext("/", new Handler());
            server.setExecutor(null);

            //start the server
            server.start();
            System.out.println("Successfully started server at port:" + port);
        } catch (IOException e) {
            System.out.println("Failed to create server");
            System.err.println(e);
        }
    }
}
