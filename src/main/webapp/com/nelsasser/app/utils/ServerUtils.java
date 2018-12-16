package main.webapp.com.nelsasser.app.utils;

import com.sun.net.httpserver.HttpExchange;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Random;

public class ServerUtils {

    public static final int SUCCESS = 200;
    public static final int SERVER_ERROR = 500;
    public static final int CLIENT_ERROR = 400;

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String lower = upper.toLowerCase();
    private static final String num = "0123456789";
    private static final String alphanum = upper + lower + num + "_-";

    /**
     *
     * @param inputStream stream to grab text from
     * @return text from stream
     */
    public static String convertStreamToString(InputStream inputStream) {
        java.util.Scanner s = new java.util.Scanner(inputStream);
        return s.hasNext() ? s.next() : "";
    }

    public static String convertColorToHex(Color color) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();

        int ba1, ba2, bg1, bg2, bb1, bb2;
        //apply mask to get only last 4 bits
        ba1 = r & 0x0F;
        //shift bits right four and get mask again to get first 4 bits
        ba2 = (r >>> 4) & 0x0F;
        //repeat for the rest of 'em.
        bg1 = g & 0x0F;
        bg2 = (g >>> 4) & 0x0F;
        bb1 = b & 0x0F;
        bb2 = (b >>> 4) & 0x0F;

        int[] c = {ba1, ba2, bg1, bg2, bb1, bb2};

        //go through each 4 bits and grab the hex value for it
        String hex = "#";
        for(int i : c) {
            if(i < 10) {
                hex += i;
            } else {
                hex += (char)(i + 55);
            }
        }

        return hex;
    }

    public static String createUniqueID(int len, String seed) {
        String id = "";

        Random r = new Random(seed.hashCode());

        for(int i = 0; i < len; i++) {
            id += "" + alphanum.charAt(r.nextInt(alphanum.length() - 1));
        }

        return id;
    }

    public static void sendResponse(int code, String response, HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(code, response.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
