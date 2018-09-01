package main.webapp.com.nelsasser.app.document;

import com.google.gson.*;
import main.webapp.com.nelsasser.app.server.Client;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;

public class DocumentDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonObject currentUsers = jsonObject.get("current_users").getAsJsonObject();

        JsonObject documentData = jsonObject.get("document_data").getAsJsonObject();

        Document doc = new Document();

        //get all of the id's for the clients
        Set<String> entries = currentUsers.keySet();
        for(String entry : entries) {
            //add each client to the list of current clients
            doc.addClient(new Client(entry));
        }

        //get the document data
        entries = documentData.keySet();
        for(String entry : entries) {
            //todo: implement this
        }


        return doc;
    }
}
