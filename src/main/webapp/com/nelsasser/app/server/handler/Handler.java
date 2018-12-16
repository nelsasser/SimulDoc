package main.webapp.com.nelsasser.app.server.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import main.webapp.com.nelsasser.app.server.Client;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.io.IOException;
import java.io.InputStream;

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
        System.out.println("user trying to connect");

        //if data or exchange is null stop the request
        if(data == null || httpExchange == null) {
            throw new IllegalArgumentException("Data is null or Http Exchange is null!");
        }

        //System.out.println(data);

        //get the json data
        JsonObject parsedData = new JsonParser().parse(data).getAsJsonObject();

        try {
            //get connection data
            String header = parsedData.get("header").toString();
            String username = parsedData.get("user").toString();
            String docID = parsedData.get("id").toString();
            String pass = parsedData.get("pass").toString();

            if(username.trim().equals("\"\"") || docID.trim().equals("\"\"") || pass.trim().equals("\"\"")) {
                throw new IllegalArgumentException();
            } else {
                if(header.equals("\"connect\"")) {
                    System.out.println("User " + username + " connecting to " + docID + "...");
                } else if(header.equals("\"create\"")) {
                    System.out.println(username + " creating document " + docID + "...");
                    new OpenDocumentHandler().handle(new Client(username, docID, pass), httpExchange);
                } else {
                    System.out.println("Bad header.");
                }
            }
        } catch(Exception e) {
            System.out.println("Failed to parse JSON, possible bad data");
        }

    }
}
