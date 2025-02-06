package org.example.javafxfriendslistview;    // Jan 2025

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

import java.util.List;

/// This is the "Controller" in the Model-View-Controller Architecture (MVC)
/// It is simply a class that contains the event listeners that have been attached
/// to UI Controls in the GUI to handle any UI events (e.g. mouse click)
/// We can annotate fields in this class (with @FXML) so that
/// references to the UI components (Controls) will be injected here.
/// i.e. Controls defined in the FXML file and identified by: fx:id="xxx"
/// Each field name must correspond to the "fx:id" of the UI Control.
/// Hence, we can access the UI Controls using the field names (references to Controls).
/// The Controller is associated with one UI, as it is identified in the FXML code
/// as follows: "fx:controller="org.example.javafxfriendslistview.FriendsListController"
///
public class FriendsListController {

    // dependency on the friends list Model
    private FriendsListModel friendsListModel;

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

    /// Constructor for this Controller (required by JavaFX to create the controller.)
    ///
    public FriendsListController() {
    }

    /// The Constructor is called first, then @FXML fields are populated,
    /// then initialize() is called. The Constructor has no access to FXML fields,
    /// but initialize() does, due to the sequence of execution.
    @FXML
    private void initialize() {
        System.out.println("Initializing FriendsListController - initialize() called.");

        // Set a listener to handle an "Enter" keypress on the name text field.
        // This can't be done in the constructor as the nameTextField will not have
        // been loaded, but can be done in initialize() as it called after the UI
        // controls are created.
        //
        nameTextField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                // find person and show their friends
                findPersonAndShowFriends();
            }
        });
    }

    /// Event Listener i.e. a method that is called when some GUI event
    /// happens. This method is called when the user clicks on the ShowFriends
    /// Button control.  The method is identified in the Button definition
    /// in the  friends-list-view.fxml file.
    /// <Button fx:id="showFriendsButton"
    ///         onAction="#onShowFriendsButtonClick" ...
    @FXML
    protected void onShowFriendsButtonClick() {
        findPersonAndShowFriends();
    }

    /// Actions to be taken when user clicks on Show Friends button, or
    /// presses ENTER when the cursor is in the TextField.
    /// Extract the name from the TextField, search for the person,
    /// if found, display the persons friends.
    ///
    private void findPersonAndShowFriends() {

        messageLabel.setText(""); // Blank out previous messages

        String name = nameTextField.getText();  // get the name to search for

        // some basic validation
        if( name==null || name.isEmpty() ) {
            messageLabel.setText("Please enter a name");
            listView.getItems().clear();  // clear listview
            return;
        }

        /// Access the Model to retrieve the person's list of friends
        List<String> list = friendsListModel.getFriends(name);
        if( list==null || list.isEmpty() ) {
            messageLabel.setText("No friends found");
            listView.getItems().clear();
            return;
        }

        /// Note that listView.getItems.clear() will clear not only the listview
        /// but also the list it is bound to.  This may not be what you intend so pay extra attention.
        /// In this example, the Model (FriendsListModel) returns a clone(copy) of the
        /// friends list, so when we clear the ListView, the *copy* of the friends list is also cleared,
        /// but the underlying source of the friends list (in the Map) is not cleared, as this
        /// controller class is dealing with a copy of the list and has no access to the Map
        /// or to its contents.  We have not leaked any references from our Model out to the GUI.
        /// This is good practice.

        /// Convert the list into an Observable Array List (as required by ListView)
        /// and set the listView to display the list of friends.
        /// This binds the ListView to the underlying ArrayList.
        ///
        listView.setItems((FXCollections.observableList(list)));
    }

    /// This Controller needs to get data from the FriendsListModel
    /// (which is known as the Model in the MODEL-VIEW-CONTROLLER Architecture)
    /// The model is instantiated in the main App and is passed into this controller
    /// as a dependency using the setModel() method below.
    /// This is called "Dependency Injection" (DI), because, instead of instantiating
    /// the FriendsListModel here, we "inject" a reference to a Model that is
    /// created elsewhere. (This reduces the coupling between the two classes).

    ///  Setter to inject the dependency on the Model - FriendsListModel class.
    ///
    public void setModel(FriendsListModel friendsListModel) {
        this.friendsListModel = friendsListModel;
    }

    @FXML
    protected void onClearAllButtonClick() {
        listView.getItems().clear();
        nameTextField.clear();
        messageLabel.setText("");
    }
}