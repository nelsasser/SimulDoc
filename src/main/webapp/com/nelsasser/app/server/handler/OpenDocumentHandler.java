package main.webapp.com.nelsasser.app.server.handler;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import main.webapp.com.nelsasser.app.DocumentKeeper;
import main.webapp.com.nelsasser.app.server.Client;

import java.io.IOException;

public class OpenDocumentHandler {

    public OpenDocumentHandler() {

    }

    public void handle(HttpExchange httpExchange, String data) throws IOException {
        //get id that user wants to open
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(data).getAsJsonObject();
        String docId = jsonObject.get("docID").toString();

        //get client
        Client client = new Client(jsonObject.get("client").getAsJsonObject().get("id").toString());

        //check to see if the document is already open
        boolean docIsOpen = DocumentKeeper.isDocumentOpen(docId);

        if(docIsOpen) {
            //if the document is already open, check to see if the document is shared with the user
            boolean isShared = DocumentKeeper.getDocument(docId).isSharedWith(client);

            if(!isShared) {

            }
        } else {
            //if document is not open, load it into a new document manager from a file
        }
    }

    private JsonObject createResponse(boolean shared) {
        return null;
    }
}
