package main.webapp.com.nelsasser.app;

import main.webapp.com.nelsasser.app.document.Document;
import main.webapp.com.nelsasser.app.manager.DocumentManager;
import main.webapp.com.nelsasser.app.utils.ServerUtils;

import java.util.HashMap;
import java.util.Map;

public class DocumentKeeper {

    private static Map<String, DocumentManager> openDocuments = new HashMap<>();

    public DocumentKeeper() {

    }

    public static void closeDocument(DocumentManager documentManager) {
        if(openDocuments.containsKey(documentManager.getDocument().getId())) {
            openDocuments.remove(documentManager.getDocument().getId());
        } else {
            System.out.println("Document not open");
        }
    }

    public static void openDocument(DocumentManager documentManager) {
        if(!openDocuments.containsKey(documentManager.getDocument().getId())) {
            openDocuments.put(documentManager.getDocument().getId(), documentManager);
        } else {
            System.out.println("Document already open.");
        }
    }

    public static boolean isDocumentOpen(String docId) {
        return openDocuments.containsKey(ServerUtils.createUniqueID(64, docId));
    }

    public static Document getDocument(String docId) {
        if(!isDocumentOpen(docId)) {
            System.out.println("Failed to get document. It is not open");
            return null;
        } else {
            return openDocuments.get(docId).getDocument();
        }
    }
}
