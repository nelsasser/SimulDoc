package main.webapp.com.nelsasser.app.server.handler;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import main.webapp.com.nelsasser.app.server.handler.UserConnectionHandler;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Handler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException{
        //read the input
        InputStream inputStream = httpExchange.getRequestBody();

        //set response headers
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
            httpExchange.sendResponseHeaders(204, -1);
            return;
        }

        moveExchangeToUsefulHandler(httpExchange, ServerUtils.convertStreamToString(inputStream));

        /*
        //create a response
        String response = "Pong";
        httpExchange.sendResponseHeaders(200, response.length());

        //send the response
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
        */
    }

    public void moveExchangeToUsefulHandler(HttpExchange httpExchange, String data) {

        //if data or exchange is null end it all...
        if(data == null || httpExchange == null) {
            throw new IllegalArgumentException("Data is null or Http Exchange is null!");
        }

        //if we have a connecting user, send the exchange to a user connection handler
        if(data.toLowerCase().contains("connecting")) {
            //create a new user connection handler
            UserConnectionHandler usrConctHandler = new UserConnectionHandler();

            //attempt to connect user to server
            try {
                usrConctHandler.handle(httpExchange, data);
            } catch (IOException e) {
                System.out.println("Failed to connect user " + data);
            }
        } else if(data.substring(0,4).equals("json")) {
            JSONHandler jsonHandler = new JSONHandler();

            try {
                jsonHandler.handle(httpExchange, data.substring(4));
            } catch (IOException e) {
                System.out.println("Failed to read json data");
            }
        }

    }
}
