package main.webapp.com.nelsasser.app.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import main.webapp.com.nelsasser.app.server.Client;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.io.*;
import java.util.*;

public class Document {

    private Client owner;
    private ArrayList<Client> clients = new ArrayList<>();
    private ArrayList<Client> sharedUsers = new ArrayList<>();
    String name = "";
    String id = "";

    private DocumentData documentData = new DocumentData();

    public Document() {

    }

    public Document(Client owner, String name) {
        this.name = name;
        this.owner = owner;
        this.sharedUsers.add(this.owner);
        this.id = ServerUtils.createUniqueID(64);
    }

    public void addClient(Client c) {
        clients.add(c);
    }

    public JsonObject getCurrentUsersJson() {
        JsonObject jsonObject = new JsonObject();

        for(Client c : clients) {
            jsonObject.add(c.getUID(), c.getAsJsonObject());
        }

        return jsonObject;
    }

    public DocumentData getDocumentData() {
        return documentData;
    }

    public void shareDocument(Client client) {
        sharedUsers.add(client);
    }

    public String getId() {return id;}

    public void export(String destination) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(new File(destination + "/" + this.id)));

            String doc = this.toJson().toString();

            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();

            String prettyJson = prettyGson.toJson(this.toJson());

            output.write(prettyJson);

            output.close();
        }  catch (FileNotFoundException e) {
            System.out.println("File not found @: " + destination + "/" + this.id);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public JsonObject sharedUsersToJson() {
        JsonObject jsonObject = new JsonObject();

        for(Client c : sharedUsers) {
            jsonObject.add(c.getUID(), c.getAsJsonObject());
        }

        return jsonObject;
    }

    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("header", "document");

        jsonObject.add("owner", this.owner.getAsJsonObject());

        jsonObject.add("shared", this.sharedUsersToJson());

        jsonObject.add("current_users", this.getCurrentUsersJson());

        jsonObject.add("document_data", this.getDocumentData().getDataAsJson());

        return jsonObject;
    }

    public boolean isSharedWith(Client c) {
        return sharedUsers.contains(c);
    }
}
