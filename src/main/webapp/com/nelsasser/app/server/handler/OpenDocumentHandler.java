package main.webapp.com.nelsasser.app.server.handler;

import com.amazonaws.services.opsworkscm.model.Server;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import main.webapp.com.nelsasser.app.DocumentKeeper;
import main.webapp.com.nelsasser.app.document.Document;
import main.webapp.com.nelsasser.app.manager.DocumentManager;
import main.webapp.com.nelsasser.app.server.Client;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.io.IOException;
import java.io.OutputStream;

public class OpenDocumentHandler {

    public OpenDocumentHandler() {

    }

    public void handle(Client client, HttpExchange exchange) throws IOException {

        //check to see if the document is already open
        boolean docIsOpen = DocumentKeeper.isDocumentOpen(client.getDocID());

        //if it is open, then return a failure
        if(docIsOpen) {
            ServerUtils.sendResponse(ServerUtils.CLIENT_ERROR,
                    "Document already open. Aborting creation.",
                    exchange);
            System.out.println("Failed to open new document.");
        } else {
            //if not open, open new document and return port number
            Document document = new Document(client, client.getDocID());
            DocumentManager documentManager = new DocumentManager(document);
            documentManager.run();
            int newDocPort = documentManager.getPort();
            DocumentKeeper.openDocument(documentManager);
            ServerUtils.sendResponse(ServerUtils.SUCCESS, Integer.toString(newDocPort), exchange);
        }
    }
}
