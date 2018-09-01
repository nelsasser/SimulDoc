package main.webapp.com.nelsasser.app.request.editrequest;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import main.webapp.com.nelsasser.app.request.Request;
import main.webapp.com.nelsasser.app.server.Client;
import main.webapp.com.nelsasser.app.utils.JSONHeaders;

public class EditRequest extends Request {

    private int priority;
    private int line;
    private int character;
    private String edit;

    public EditRequest() {
    }

    @Override
    public JsonElement toJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("header", JSONHeaders.EDIT_REQUEST);

        jsonObject.add("user", this.getUser().getAsJsonObject());
        jsonObject.addProperty("priority", this.getPriority());
        jsonObject.addProperty("line", this.getLine());
        jsonObject.addProperty("number", this.getCharacter());
        jsonObject.addProperty("edit", this.getEdit());

        return jsonObject;
    }

    public EditRequest(Client user, int priority, int line, int character, String edit) {
        super(user);

        this.priority = priority;
        this.line = line;
        this.character = character;
        this.edit = edit;
    }

    public int getPriority() { return priority; }

    public int getLine() {
        return line;
    }

    public int getCharacter() {
        return character;
    }

    public String getEdit() {
        return edit;
    }
}
