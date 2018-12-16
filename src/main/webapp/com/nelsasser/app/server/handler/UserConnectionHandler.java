package main.webapp.com.nelsasser.app.server.handler;

import com.sun.net.httpserver.HttpExchange;
import main.webapp.com.nelsasser.app.server.Client;

import java.io.IOException;
import java.io.OutputStream;

public class UserConnectionHandler {

    public void handle(HttpExchange httpExchange, String data) throws IOException {
        /*
        //read the input
        InputStream inputStream = httpExchange.getRequestBody();
        */


        String response = "Connected succesfully";
        httpExchange.sendResponseHeaders(200, response.length());

        //send the response
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();


        /**
         * TODO
         *  -when the user logs on, check to see if their username and password is stored somewhere
         *  -implement some sort of database to check for this
         *  -return if connection is successful or not
         */
    }
}
