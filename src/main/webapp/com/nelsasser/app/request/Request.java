package main.webapp.com.nelsasser.app.request;

import main.webapp.com.nelsasser.app.server.Client;

public abstract class Request {

    protected Client user;

    public Request(Client user) {
        this.user = user;
    }

}
