package main.webapp.com.nelsasser.app.server.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import main.webapp.com.nelsasser.app.utils.JSONData;
import main.webapp.com.nelsasser.app.utils.JSONParser;

import java.io.IOException;

public class JSONHandler {

    public void handle(HttpExchange httpExchange, String data) throws IOException {
        JSONData jsonData = JSONParser.getJSONData(data);

        System.out.println(data);
    }
}
