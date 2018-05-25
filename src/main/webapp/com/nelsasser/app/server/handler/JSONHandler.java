package main.webapp.com.nelsasser.app.server.handler;

import com.amazonaws.services.dynamodbv2.xspec.S;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import com.google.gson.*;
import main.webapp.com.nelsasser.app.document.Document;
import main.webapp.com.nelsasser.app.document.DocumentDeserializer;
import main.webapp.com.nelsasser.app.document.DocumentSerializer;
import main.webapp.com.nelsasser.app.server.Client;

public class JSONHandler {

    public void handle(HttpExchange httpExchange, String data) throws IOException {
        Document d = test();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Document.class, new DocumentSerializer())
                .registerTypeAdapter(Document.class, new DocumentDeserializer())
                .create();

        String json = gson.toJson(d);
        System.out.println(json);

        gson.fromJson(json, Document.class);
    }

    private Document test() {
        //create a fake documet
        Document d = new Document();

        for(int i = 0; i < 10; i++) {
            d.addClient(new Client("" + (int)(Math.random() * 100)));
        }

        return d;
    }
}
