package main.webapp.com.nelsasser.app.document;

import main.webapp.com.nelsasser.app.request.EditRequest;
import main.webapp.com.nelsasser.app.server.Client;

import java.util.*;

public class Document {

    private Map<String, Client> currentUsers = new HashMap<>();
    private PriorityQueue<EditRequest> editRequests;

    private DocumentData documentData = new DocumentData();

    public Document() {
        editRequests = new PriorityQueue<>(new Comparator<EditRequest>() {
            @Override
            public int compare(EditRequest request1, EditRequest request2) {
                return request1.getPriority() - request2.getPriority();
            }
        });
    }

    public void addClient(Client c) {
        currentUsers.put(c.getUID(), c);
    }

    public void addEditRequest(EditRequest request) {
        editRequests.offer(request);
    }
}
