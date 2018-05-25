package main.webapp.com.nelsasser.app.server;

import com.google.gson.JsonObject;
import main.webapp.com.nelsasser.app.document.Document;

public class Client {

    private String uid;
    private Document currentDocument = null;

    public Client(String uid) {
        this.uid = uid;
    }

    public void setCurrentDocument(Document d) {
        currentDocument = d;
    }

    public String getUID() { return uid; }

    public JsonObject getAsJsonObject() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("id", uid);

        return jsonObject;
    }
}
