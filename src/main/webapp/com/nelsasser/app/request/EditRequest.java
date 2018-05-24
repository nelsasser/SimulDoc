package main.webapp.com.nelsasser.app.request;

import main.webapp.com.nelsasser.app.document.EditType;
import main.webapp.com.nelsasser.app.server.Client;

public class EditRequest extends Request{

    private int priority;
    private int line;
    private int character;
    private EditType edit;

    public EditRequest(Client user, int priority, int line, int character, String edit) {
        super(user);

        this.priority = priority;
        this.line = line;
        this.character = character;
        this.edit = new EditType(edit);
    }

    public int getPriority() { return priority; }
}
