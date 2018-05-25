package main.webapp.com.nelsasser.app.document;

import com.google.gson.JsonObject;

import java.awt.*;
import java.util.*;
import java.util.List;

public class DocumentData {

    private List<List<Character>> lines = new ArrayList<>();

    //maximum number of character per line
    private static final int MAX_NUM_CHARS = 50;

    //default template for cleaning words
    private static final String DEFAULT_CLEANING_TEMPLATE = ".?\'\"!@#%$()<>{}[],";

    //default color for text in the document
    private static final Color DEFAULT_TEXT_COLOR = Color.BLACK;

    //all text that is bold, italicized, underlines, or struck through in the document
    private List<TextRange> boldText = new ArrayList<>();
    private List<TextRange> italicizedText = new ArrayList<>();
    private List<TextRange> underlinedText = new ArrayList<>();
    private List<TextRange> strikeThroughText = new ArrayList<>();

    //all non-default text colors in the document
    private Map<Color, List<TextRange>> colorTextMap = new HashMap<>();

    public DocumentData() {

    }

    private void addLine() {
        lines.add(new ArrayList<Character>());
    }

    private void removeLastLine() {
        lines.remove(lines.size() - 1);
    }

    private int getCharacterAtLine(int lineNumber) {
        List<Character> line = lines.get(lineNumber);

        return line.size();
    }

    private List<String> getWordsAtLine(int lineNumber) {
        List<Character> line = lines.get(lineNumber);
        List<String> words = new ArrayList<>();

        int index = 0;
        int len = line.size();

        //make sure we don't go past the edge
        while(index < len) {
            String word = "";
            //each word ends with either the line ends or when there is a space
            while(index < len && !line.get(index).equals(" ")) {
                word += line.get(index);
                index++;
            }
        }

        return words;
    }

    //TODO: implement
    private String cleanWord(String word, String template) {
        return null;
    }

    //sets color of text range
    private void colorText(Color c, TextRange textRange) {
        if(!colorTextMap.containsKey(c)) {
            colorTextMap.put(c, new ArrayList<TextRange>());
        }
        colorTextMap.get(c).add(textRange);
    }

    //adds bold text
    private void addBoldText(TextRange textRange) {
        boldText.add(textRange);
    }

    //adds italicized text
    private void addItalicText(TextRange textRange) {
        italicizedText.add(textRange);
    }

    //adds underlined text
    private void addUnderlinedText(TextRange textRange) {
        underlinedText.add(textRange);
    }

    //adds struckthrough text
    private void addStrikeThroughText(TextRange textRange) {
        strikeThroughText.add(textRange);
    }

    public List<List<Character>> getLines() {
        return lines;
    }

    public JsonObject getDataAsJson() {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("bold_text", boldJson());
        jsonObject.add("italic_text", italicJson());
        jsonObject.add("underlined_text", underJson());
        jsonObject.add("strikethrough_text", strikeJson());
        jsonObject.add("color", colorJson());
        jsonObject.add("text", textJson());

        return jsonObject;
    }

    private JsonObject textJson() {
        JsonObject jsonObject = new JsonObject();

        for(int i = 0; i < lines.size(); i++) {
            List<Character> line = lines.get(i);
            String str = "";
            for(Character c : line) {
                str += c;
            }
            jsonObject.addProperty("" + i, str);
        }

        return jsonObject;
    }

    private JsonObject boldJson() {
        JsonObject jsonObject = new JsonObject();

        for(int i = 0; i < boldText.size(); i++) {
            jsonObject.add("" + i, boldText.get(i).json());
        }

        return jsonObject;
    }

    private JsonObject italicJson() {
        JsonObject jsonObject = new JsonObject();

        for(int i = 0; i < italicizedText.size(); i++) {
            jsonObject.add("" + i, italicizedText.get(i).json());
        }

        return jsonObject;
    }

    private JsonObject underJson() {
        JsonObject jsonObject = new JsonObject();

        for(int i = 0; i < underlinedText.size(); i++) {
            jsonObject.add("" + i, underlinedText.get(i).json());
        }

        return jsonObject;
    }

    private JsonObject strikeJson() {
        JsonObject jsonObject = new JsonObject();

        for(int i = 0; i < strikeThroughText.size(); i++) {
            jsonObject.add("" + i, strikeThroughText.get(i).json());
        }

        return jsonObject;
    }

    private JsonObject colorJson() {
        JsonObject jsonObject = new JsonObject();

        Set<Color> s = colorTextMap.keySet();
        for(Color c : s) {
            jsonObject.add(c.toString(), colorTextJson(c));

        }

        return jsonObject;
    }

    private JsonObject colorTextJson(Color c) {
        JsonObject jsonObject = new JsonObject();

        List<TextRange> text = colorTextMap.get(c);

        for(int i = 0; i < text.size(); i++) {
            jsonObject.add("" + i, text.get(i).json());
        }

        return jsonObject;
    }

    public static int getMaxNumChars() {
        return MAX_NUM_CHARS;
    }

    public static String getDefaultCleaningTemplate() {
        return DEFAULT_CLEANING_TEMPLATE;
    }

    public static Color getDefaultTextColor() {
        return DEFAULT_TEXT_COLOR;
    }

    public List<TextRange> getBoldText() {
        return boldText;
    }

    public List<TextRange> getItalicizedText() {
        return italicizedText;
    }

    public List<TextRange> getUnderlinedText() {
        return underlinedText;
    }

    public List<TextRange> getStrikeThroughText() {
        return strikeThroughText;
    }

    public Map<Color, List<TextRange>> getColorTextMap() {
        return colorTextMap;
    }
}
