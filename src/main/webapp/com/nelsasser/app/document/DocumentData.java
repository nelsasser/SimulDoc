package main.webapp.com.nelsasser.app.document;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}
