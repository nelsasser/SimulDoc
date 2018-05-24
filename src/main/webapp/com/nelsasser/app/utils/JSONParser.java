package main.webapp.com.nelsasser.app.utils;

public class JSONParser {

    int currentIndex = 0;
    String data;
    String currentPath = "";
    JSONData jsonData;
    String currentKey = "";

    public JSONParser(String file) {
        this.data = file;
        jsonData = new JSONData();
    }

    public JSONData getJSONData() {

        //keep going until we hit first open bracket
        for(; currentIndex < data.length() - 1; currentIndex++) {
            if(data.substring(currentIndex, currentIndex + 1).equals("{")) {
                break;
            }
        }

        return jsonData;
    }

    private String getNextKey() {
        //go until first "
        while(!data.substring(currentIndex, currentIndex + 1).equals("\"")) {
            currentIndex++;
        }

        String key = "";

        while(!data.substring(currentIndex, currentIndex + 1).equals("\"")) {
            key += data.substring(currentIndex, currentIndex + 1);
            currentIndex++;
        }

        return key;
    }

    private void parseObject() {

    }

    private void parseArray() {

    }

    private void parseInt() throws InvalidJSONException {
        String num = "";

        while(!data.substring(currentIndex, currentIndex +  1).equals(",")) {
            num += data.substring(currentIndex, currentIndex + 1);
            currentIndex++;
        }

        jsonData.addLeaf(currentPath, currentKey, num);
    }

    private void getNextValue() {
        if(nextValueIsInt()) {
            parseInt();
        } else if(nextValueIsArray()) {
            parseArray();
        } else if(nextValueIsObject()) {
            parseObject();
        }
    }

    private boolean nextValueIsObject() {
        return data.substring(currentIndex, currentIndex + 1).equals("{");
    }

    private boolean nextValueIsInt() {
        return Character.isDigit(data.charAt(currentIndex));
    }

    private boolean nextValueIsArray() {
        return data.substring(currentIndex, currentIndex + 1).equals("[");
    }
}
