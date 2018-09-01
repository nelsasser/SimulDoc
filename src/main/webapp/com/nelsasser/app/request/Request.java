package main.webapp.com.nelsasser.app.request;

import com.google.gson.JsonElement;
import main.webapp.com.nelsasser.app.server.Client;

public abstract class Request {

    protected Client user;

    public Request(){}

    public Request(Client user) {
        this.user = user;
    }

    public Client getUser() {return user;}

    public abstract JsonElement toJson();

}
