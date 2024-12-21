package org.example.javafxfriendslistview;   // Jan 2024

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FriendsListApplication extends Application {

    /// This FriendsListApplication is a JavaFX Application which is created by calling the
    /// launch() method (from main).  The Application class is started in its own JavaFX Application
    /// thread and the start() method is then called.
    /// [Application class API](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html)
    /// [javafx API](https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/application/Application.html)
    @Override
    public void start(Stage stage) throws IOException {

        // Create the FriendsManagerService (service)
        FriendsManagerService friendsManagerService = new FriendsManagerService();
        initializeSomeFriendLists(friendsManagerService); // populate for testing purposes

        // get the FXML view resource
        FXMLLoader loader = new FXMLLoader(FriendsListApplication.class.getResource("friends-view.fxml"));

        // Create a new Controller and inject (pass in) the FriendsManagerService (i.e. Dependency Injection DI)
        // This gives the Controller access to the Model (i.e. The FriendsManagerService)
        FriendsListController controller = new FriendsListController(friendsManagerService);

        /// Note that when we construct a Controller (as above) then the fx:controller attribute
        /// must be removed from the fxml file, as it is an instruction to instantiate a Controller,
        /// but here we have already instantiated it.

        loader.setController(controller);

        Parent root = loader.load();  // load in the root of UI

        // Load the Scene (containing GUI Controls)
        Scene scene = new Scene(root, 400, 550);
        stage.setScene(scene);

        stage.setTitle("Friends Finder Application");

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    /**
     * Convenience method, that initializes the FriendsManagerService
     * with an initial set of data so that we can test the app.
     */
    private void initializeSomeFriendLists(FriendsManagerService friendsManagerService) {

        friendsManagerService.addFriend("Luke","R2-D2");
        friendsManagerService.addFriend("Luke","C-3PO");
        friendsManagerService.addFriend("Luke","Obi-Wan Kenobi");

        friendsManagerService.addFriend("Ross","Monica");
        friendsManagerService.addFriend("Ross","Joey");
        friendsManagerService.addFriend("Ross","Rachel");
        friendsManagerService.addFriend("Ross","Lisa");
    }

}