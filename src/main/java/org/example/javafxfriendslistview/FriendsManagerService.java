package org.example.javafxfriendslistview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendsManager {

    // Map to map a person's name to a list of their friends names
    //
    Map<String, ArrayList<String>> map;

    public FriendsManager() {
        map = new HashMap<String, ArrayList<String>>();
        initialize(); // initializes this class with data for convenience
    }


    public void addFriend( String name, String friendsName) {
        if(map.containsKey(name)) {
            map.get(name).add(friendsName);
        }
        else {
            ArrayList<String> list = new ArrayList<>();
            list.add(friendsName);
            map.put(name, list);
        }
    }

    public void removeFriend( String name, String friendsName) {
        if(map.containsKey(name)) {
            map.get(name).remove(friendsName);
        }
    }

    /**
     *
     * @param name name of person whose friends we want to access
     * @return List of friends names
     */
    public List<String> getFriends(String name) {
        return map.get(name);
    }

}
