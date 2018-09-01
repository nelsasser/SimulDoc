package main.webapp.com.nelsasser.app.server.handler;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.webapp.com.nelsasser.app.document.Document;
import main.webapp.com.nelsasser.app.document.DocumentDeserializer;
import main.webapp.com.nelsasser.app.document.DocumentSerializer;
import main.webapp.com.nelsasser.app.utils.JSONHeaders;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.io.IOException;
import java.io.InputStream;

public class DocumentHandler implements HttpHandler {

    public void handle(HttpExchange httpExchange, String data) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Document.class, new DocumentSerializer())
                .registerTypeAdapter(Document.class, new DocumentDeserializer())
                .create();

        Document d = gson.fromJson(data, Document.class);
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
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

        String sentData = ServerUtils.convertStreamToString(inputStream);

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(sentData).getAsJsonObject();
        String header = jsonObject.get("header").toString();

        if(header.equals(JSONHeaders.OPEN_DOCUMENT_REQUEST)) {
            String docID = jsonObject.get("docID").toString();
            //check to see if document is already open

        }

    }
}
