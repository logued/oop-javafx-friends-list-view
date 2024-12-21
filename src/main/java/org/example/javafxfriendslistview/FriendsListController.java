package org.example.javafxfriendslistview;    // Jan 20204

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

/// This is the "Controller" in the Model-View-Controller Architecture (MVC)
/// It is simply a class that contains the event listeners that will handle
/// events coming from the GUI.
/// We can annotate fields in this class so that references to the UI components (Controls)
/// will be injected here. i.e. Controls defined in the FXML file and identified by fx:id="xxx"
/// that have a field with a corresponding name, and an @FXML annotation, will be automatically
/// bound (connected) to those controls.  Hence, we can access the UI Controls using the
/// field names (references to Controls).
///

public class FriendsListController {

    // dependency on the friends manager service
    private final FriendsManagerService friendsManagerService;

    @FXML
    public Label titleText;
    @FXML
    public TextField nameTextField;
    @FXML
    public Button showFriendsButton;
    @FXML
    public Button clearAllButton;
    @FXML
    private ListView<String> listView;
    @FXML
    private Label messageLabel;


    /// Constructor for this Controller that accepts a reference
    /// to a FriendsManagerService.  This controller needs to get data
    /// from the service (which is known as the Model in the MODEL-VIEW-CONTROLLER Architecture)
    /// This is called "Dependency Injection" (DI) because, instead of creating
    /// the FriendsManagerService, we "inject" a reference to a service that is
    /// created elsewhere. (This reduces the coupling between the two classes).
    ///
    public FriendsListController(FriendsManagerService service) {
        this.friendsManagerService = service;
    }

    /// Event Listener i.e. a method that is called when some GUI event
    /// happens. This method is called when the user clicks on the ShowFriends
    /// Button control.  The method is identified in the Button definition
    /// in the  friends-view.fxml file.
    /// <Button fx:id="showFriendsButton"
    ///         onAction="#onShowFriendsButtonClick" ...
    @FXML
    protected void onShowFriendsButtonClick() {

        String name = nameTextField.getText();  // get the name to search for

        if(name==null || name.isEmpty()) {
            messageLabel.setText("Please enter a name");
            listView.getItems().clear();  // clear listview
            return;
        }

        listView.setPrefSize(200,250);

        /// Call the service to retrieve the persons list of friends
        List<String> list = friendsManagerService.getFriends(name);
        if(list==null || list.isEmpty()) {
            messageLabel.setText("No friends found");
            listView.getItems().clear();
            return;
        }

        /// Note that listView.getItems.clear() will clear not only the listview
        /// but also the *underlying* list.  This may not be what you intend so pay attention.
        /// In this example, the Model (FriendsManagerService) returns a clone(copy) of the
        /// friends list, so when we clear the ListView, the *copy* of the friends list is also cleared,
        /// but the underlying source of the friends list (in th Map) is not cleared, as this
        /// controller class is dealing wit a copy of the list and has no access to the Map
        /// or to its contents.  We have not leaked any references from our Model out to the GUI.
        /// This is good practice.

        /// Convert the list into an Observable Array List (as required by ListView)
        /// and set the listView to display the list of friends
        listView.setItems((FXCollections.observableList(list)));
    }

    @FXML
    protected void onClearAllButtonClick() {
        listView.getItems().clear();
        nameTextField.clear();
    }
}