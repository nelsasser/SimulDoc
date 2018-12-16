package main.webapp.com.nelsasser.app.request.editrequest;

import com.google.gson.*;
import main.webapp.com.nelsasser.app.server.Client;

import java.lang.reflect.Type;

public class EditRequestDeserializer implements JsonDeserializer {

    @Override
    public Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonElement jsonClient = jsonObject.get("user");
        JsonElement id = jsonClient.getAsJsonObject().get("id");

        //Client client = new Client(id.toString());

        int priority = jsonObject.get("priority").getAsInt();
        int line = jsonObject.get("line").getAsInt();
        int character = jsonObject.get("character").getAsInt();
        String edit = jsonObject.get("edit").toString();

       // return new EditRequest(client, priority, line, character, edit);
        return null;
    }
}
