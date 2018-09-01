package main.webapp.com.nelsasser.app.utils;

import java.awt.*;
import java.io.InputStream;
import java.util.Random;

public class ServerUtils {

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

    public static String createUniqueID(int len) {
        String id = "";

        Random r = new Random();

        for(int i = 0; i < len; i++) {
            id += "" + alphanum.charAt(r.nextInt(alphanum.length() - 1));
        }

        return id;
    }
}
