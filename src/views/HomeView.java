package views;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.SessionManager;

public class HomeView {

    public void show(Stage primaryStage) {
        VBox vbox = new VBox(10);
        vbox.setStyle("-fx-padding: 20;");

        
        String username = (String) SessionManager.getInstance().getUsername();

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        vbox.getChildren().add(welcomeLabel);

        Button logoutButton = new Button("Logout");
        vbox.getChildren().add(logoutButton);

        logoutButton.setOnAction(e -> {
            System.out.println("Logging out...");
            SessionManager.getInstance().clear(); 
            new LoginView().show(primaryStage);
        });

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setTitle("Home Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
