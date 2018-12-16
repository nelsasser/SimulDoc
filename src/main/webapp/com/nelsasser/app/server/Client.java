package main.webapp.com.nelsasser.app.server;

import com.google.gson.JsonObject;
import main.webapp.com.nelsasser.app.document.Document;

public class Client {

    private String name;
    private String docID;
    private String password;

    public Client(String uid, String docID, String password) {
        this.name = uid;
        this.docID = docID;
        this.password = password;
    }

    public String getName() { return name; }

    public String getDocID() { return docID; }

    public String getPassword() { return password; }

}
