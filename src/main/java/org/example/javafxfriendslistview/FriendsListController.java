package org.example.javafxfriendslistview;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {
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
    protected void onShowFriendsButtonClick() {

        //String name = nameTextField.getText();  // get the name to search for

        System.out.println(friendsList);
        //ListView<String> list = new ListView<String>();
        //ObservableList<String> items = FXCollections.observableArrayList ("Single", "Double", "Suite", "Family App");
        listView.setPrefSize(200,250);
        listView.setItems((FXCollections.observableArrayList(friendsList)));
        //listView.getSelectionModel().clearSelection();
        listView.refresh();
    }



}