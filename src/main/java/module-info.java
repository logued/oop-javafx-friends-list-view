module org.example.javafxfriendslistview {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javafxfriendslistview to javafx.fxml;
    exports org.example.javafxfriendslistview;
}