package org.example.javafxfriendslistview;    // Jan 2024

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/// This is the Model part in the Model-View-Controller (MVC) architecture.
/// The Model is where we store "State" i.e. data to be used (people and their friends)
/// The View is defined in the FXML file that contains the UI Components.
/// The Controller is a class that has Event Listeners - methods that are called
/// when an GUI event happens (e.g. mouse click) and those methods may modify the
/// state (Model).
///
/// Note in particular here that the getFriends() method returns a *clone* of the
/// friends list, and NOT a reference to the actual list of friends from the Map.
/// It is good practice to do this, as otherwise we are allowing a 'reference leak'.
/// I.e. if we return a reference to the ArrayList in the Map, then the code that
/// receives that reference will then be able to change what is in the Map.
/// This would break encapsulation of data within the class. (i.e. code from outside
/// this class would have access to the member data of teh class)
///
public class FriendsManagerService {

    // Map to map a person's name to a list of their friends names (1:M)
    //
    private final Map<String, ArrayList<String>> map;

    public FriendsManagerService() {
        map = new HashMap<String, ArrayList<String>>();
    }

    public void addFriend( String personsName, String friendsName) {
        if(map.containsKey(personsName)) {
            map.get(personsName).add(friendsName);
        }
        else {
            ArrayList<String> list = new ArrayList<>();
            list.add(friendsName);
            map.put(personsName, list);
        }
    }

    public void removeFriend( String personsName, String friendsName) {
        if(map.containsKey(personsName)) {
            map.get(personsName).remove(friendsName);  // get list of names, and remove the one specified
        }
    }

    /**
     * @param personsName name of person whose friends we want to access
     * @return List of friends names or null
     */
    public List<String> getFriends(String personsName) {
        List<String> list =  map.get(personsName);
        if(list == null) {
            return null;
        }

        return new ArrayList<>( list );            // clone(copy) the list to prevent reference leakage
    }

}
