package main.webapp.com.nelsasser.app.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.webapp.com.nelsasser.app.server.Client;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.IntBuffer;
import java.util.List;

public class UserConnectionHandler {

    public void handle(HttpExchange httpExchange, String data) throws IOException {
        /*
        //read the input
        InputStream inputStream = httpExchange.getRequestBody();
        */

        //split user id from connection message
        String[] tokens = data.split(":");
        try {
            //grab user id
            String id = tokens[1];
            System.out.println("Creating user #" + id);
            Client client = new Client(id);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Bad connection input, no user id submitted!");
        }

        String response = "Connected succesfully";
        httpExchange.sendResponseHeaders(200, response.length());

        //send the response
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
