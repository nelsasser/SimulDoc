package main.webapp.com.nelsasser.app.manager;

import main.webapp.com.nelsasser.app.document.Document;
import com.sun.net.httpserver.*;
import main.webapp.com.nelsasser.app.server.handler.DocumentHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class DocumentManager implements Runnable{

    int port = 0;
    Document document;
    HttpServer localServer;


    public DocumentManager(Document d) {
        this.document = d;
    }

    @Override
    public void run() {

        //create local server to direct all document traffic to
        InetSocketAddress address = new InetSocketAddress(0);

        try {
            localServer = HttpServer.create(address, 100);
            localServer.createContext("/", new DocumentHandler());
            localServer.setExecutor(null);
            localServer.start();

            port = localServer.getAddress().getPort();

            System.out.println("created document server at port : " + port);

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public int getPort() {
        return port;
    }


    public Document getDocument() {return document;}
}