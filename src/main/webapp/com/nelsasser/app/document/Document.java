package main.webapp.com.nelsasser.app.document;

import main.webapp.com.nelsasser.app.server.Client;
import main.webapp.com.nelsasser.app.utils.ServerUtils;


public class Document {

    private Client owner;
    String name = "";
    String id = "";

    private DocumentData documentData = new DocumentData();

    public Document() {

    }

    public Document(Client owner, String name) {
        this.name = name;
        this.owner = owner;
        this.id = ServerUtils.createUniqueID(64, name);
    }

    public DocumentData getDocumentData() {
        return documentData;
    }


    public String getId() {return id;}

}
