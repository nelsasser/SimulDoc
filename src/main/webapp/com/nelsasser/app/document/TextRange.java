package main.webapp.com.nelsasser.app.document;

import com.google.gson.JsonObject;

public class TextRange {

    private int startLine, startCharacter, endLine, endCharacter;

    public TextRange(int startLine, int startCharacter, int endLine, int endCharacter) {
        this.startLine = startLine;
        this.startCharacter = startCharacter;
        this.endLine = endLine;
        this.endCharacter = endCharacter;
    }

    public String toString() {
        return "<" + startLine + ":" + startCharacter + "~" + endLine + ":" + endCharacter + ">";
    }

    public static TextRange parseTextRange(String range) {
        try {

            range = range.substring(1, range.length() - 1);
            String[] data = range.split("~");

            return new TextRange(
                    Integer.parseInt(data[0].split(":")[0]),
                    Integer.parseInt(data[0].split(":")[1]),
                    Integer.parseInt(data[1].split(":")[0]),
                    Integer.parseInt(data[1].split(":")[1]));

        } catch (Exception e) {
            System.out.println("Bad text range!");
        }

        return null;
    }

    public JsonObject json() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("start_line", startLine);
        jsonObject.addProperty("start_character", startCharacter);
        jsonObject.addProperty("end_line", endLine);
        jsonObject.addProperty("end_character", endCharacter);
        return jsonObject;
    }
}
