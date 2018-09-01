package main.webapp.com.nelsasser.app.server.handler;

import com.google.gson.JsonParser;
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

        //move the sent data to a different handler that will be more suited to parse it
        moveExchangeToUsefulHandler(httpExchange, ServerUtils.convertStreamToString(inputStream));
    }

    public void moveExchangeToUsefulHandler(HttpExchange httpExchange, String data) {

        //if data or exchange is null stop the request
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
        } else {
            //get the json header to parse the file
            String header = new JsonParser().parse(data).getAsJsonObject().get("header").toString();

            //place '/' to uncomment

            //*
            try {
                if(header.equals("open_document_request")) {
                    OpenDocumentHandler openDocumentHandler = new OpenDocumentHandler();
                    openDocumentHandler.handle(httpExchange, data);
                }
            } catch (IOException e) {
                System.out.println("Failed to read json data");
            }
            //*/
        }

    }
}
