package main.webapp.com.nelsasser.app.document;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

import main.webapp.com.nelsasser.app.document.Document;

public class DocumentSerializer implements JsonSerializer {

    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();

        Document doc = (Document) o;

        jsonObject.addProperty("header", "document");

        jsonObject.add("current_users", doc.getCurrentUsersJson());

        jsonObject.add("document_data", doc.getDocumentData().getDataAsJson());

        return jsonObject;
    }
}
