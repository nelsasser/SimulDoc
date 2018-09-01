package main.webapp.com.nelsasser.app.request.editrequest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import main.webapp.com.nelsasser.app.utils.JSONHeaders;

import java.lang.reflect.Type;

public class EditRequestSerializer implements JsonSerializer {
    @Override
    public JsonElement serialize(Object o, Type type, JsonSerializationContext jsonSerializationContext) {
        return ((EditRequest)o).toJson();
    }
}
