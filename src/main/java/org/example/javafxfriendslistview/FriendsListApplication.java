package org.example.javafxfriendslistview;   // Jan 2025

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FriendsListApplication extends Application {

    /// We create a JavaFX Application by writing a class that
    /// extends javafx.application.Application. (as above)
    /// The Application is started in its own JavaFX Application thread by
    /// calling the launch() method.
    /// The start() method is then called by the JavaFX runtime.
    /// [Application class API](https://docs.oracle.com/javase/8/javafx/api/javafx/application/Application.html)
    /// [javafx API](https://docs.oracle.com/javase/8/javafx/api/index.html?javafx/application/Application.html)

    public static void main(String[] args) {
        launch(); // call static JavaFX Application method
    }

    /// start() is called by the JavaFX runtime system, after our JavaFX Application
    /// is launched.  This is the entry point to our program where we load UI and Controllers
    ///
    @Override
    public void start(Stage stage) throws IOException {

        // get the FXML view resource
        FXMLLoader loader = new FXMLLoader(FriendsListApplication.class.getResource("friends-list-view.fxml"));
        Parent root = loader.load();  // load in the root (Parent container) of UI, and instantiate the Controller

        /// Create the FriendsListModel (The Model), populate it with some data,
        /// and inject the Model into the Controller (i.e. pass in a reference
        /// to the Model so that the Controller can access it.) (Dependency Injection!)
        FriendsListModel friendsListModel = new FriendsListModel();

        /// Get a reference to the Controller.  The controller for the App
        /// was declared in the FXML code and was instantiated (created) by JavaFX.
        FriendsListController controller = loader.getController();  // get a reference to the controller
        controller.setModel(friendsListModel); // Inject Dependency (dependency on the Model)

        // Load the Scene (containing GUI Controls)
        Scene scene = new Scene(root, 450, 650);
        stage.setScene(scene);
        stage.setTitle("Friends Finder Application");
        stage.show();
        // Once the stage (GUI) is displayed, the program is then driven by
        // events that are captured in the GUI (e.g. Button click, text entry etc).
    }
}