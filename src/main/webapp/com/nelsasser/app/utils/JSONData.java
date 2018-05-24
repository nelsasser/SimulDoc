package main.webapp.com.nelsasser.app.utils;


import com.amazonaws.services.dynamodbv2.xspec.M;

import java.util.*;

public class JSONData {

    Map<String, Object> dataTree;
    String root;
    long l = System.currentTimeMillis();

    public JSONData() {
        root = "" + this.hashCode() + l;
        dataTree = new HashMap<>();
        dataTree.put(root, new HashMap<String, Object>());
    }

    public String rootPath() {
        return root;
    }

    public void addLeaf(String path, String key, String value) throws InvalidJSONException {
        Object o = this.getValueFromPath(path);

        if(o instanceof Map) {
            Map<String, Object> m = (Map) o;
            m.put(key, value);
        } else {
            throw new InvalidJSONException("Path does not lead to valid branch in tree");
        }

    }

    public void addBranch(String path, String key, Map<String, Object> branch) throws InvalidJSONException {
        Object o = this.getValueFromPath(path);

        if(o instanceof Map) {
            Map<String, Object> m = (Map) o;
            m.put(key, branch);
        } else {
            throw new InvalidJSONException("Path does not lead to valid branch in tree");
        }
    }

    public Object getValueFromKey(String key) throws InvalidJSONException {
        return getValueFromPath(getPathToKey(key));
    }

    public Object getValueFromPath(String path) throws InvalidJSONException{
        StringTokenizer tokenizer = new StringTokenizer(path);

        Map currentMap = dataTree;

        while (tokenizer.hasMoreTokens()) {
            String next = tokenizer.nextToken();

            //if the current branch does not have another branch
            //with given key, then path is invalid.
            if(!currentMap.containsKey(next)) {
                throw new InvalidJSONException("Invalid data path");
            }

            //check to see if we have found value at end of path
            if(!tokenizer.hasMoreTokens()) {
                return currentMap.get(next);
            }

            //check to see if we should continue down the branch
            if(currentMap.get(next) instanceof Map) {
                currentMap = (Map) currentMap.get(next);
            } else {
                throw new InvalidJSONException("Invalid data type in data tree: " + currentMap.get(next).getClass());
            }
        }

        throw new InvalidJSONException("Empty path.");
    }

    public String getPathToKey(String key) throws InvalidJSONException {
        String path= "";

        //get all available starting points
        Set<String> keySet = dataTree.keySet();
        Iterator<String> iter = keySet.iterator();

        while(iter.hasNext()) {
            //check to make sure we are not already where we want to be
            String head = iter.next();
            if(head.equals(key)) {
                return head;
            } else {
                //explore all possible branches
                if(dataTree.get(head) instanceof Map) {
                    Map m = (Map) dataTree.get(head);

                    path = getPathToKeyHelper(m, head, key);
                }
            }
        }

        //if no path is found throw an exception
        if(path.equals("")) {
            throw new InvalidJSONException("Path is not found in json tree.");
        }

        return path;
    }

    private String getPathToKeyHelper(Map<String, Object> map, String parent, String destination) {
        String p = "";

        String str = "";

        //check to see if we have reached our destination
        if(parent.equals(destination)) {
            str = parent;
        } else {
            //if we have not, go through each of the sub-branches in this branch
            Set<String> keySet = map.keySet();
            Iterator<String> iter = keySet.iterator();
            while(iter.hasNext()) {
                String key = iter.next();

                //check to see if any sub-branches exist
                if(map.get(key) instanceof Map) {

                    //go through all available sub-branches
                    str = getPathToKeyHelper((Map) map.get(key), key, destination);

                } else if(key.equals(destination)) {
                    str += key;
                }
            }
        }

        //add on to the path only when we get a hit
        if(!str.equals("")) {
            p += "/" + str;
        }

        return p;
    }
}
