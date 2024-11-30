package views;

import controllers.UserController;
import javafx.animation.PauseTransition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.SessionManager;
import validators.UserValidator;
import javafx.scene.control.Hyperlink;

public class LoginView {

    public void show(Stage primaryStage) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter Username");
        grid.add(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter Password");
        grid.add(passwordField, 1, 1);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 2);

        Label errorLabel = new Label();
        errorLabel.setTextFill(Color.RED);
        grid.add(errorLabel, 1, 3);

        Hyperlink registerLink = new Hyperlink("Don't have an account? Register here.");
        grid.add(registerLink, 1, 4);

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            UserController userController = new UserController();
            String result = userController.userLogin(username, password);

            if (result.equalsIgnoreCase("Login successful")) {
                errorLabel.setText("Login successful!");
                errorLabel.setTextFill(Color.GREEN); 
                SessionManager.getInstance().setUsername(username);
                PauseTransition pause = new PauseTransition(Duration.seconds(3));
                pause.setOnFinished(event -> new HomeView().show(primaryStage));
                pause.play();
            } else {
                errorLabel.setText(result);  
                errorLabel.setTextFill(Color.RED);  
            }
        });

        
        registerLink.setOnAction(event -> {
            new RegisterView().show(primaryStage);  
        });

        Scene scene = new Scene(grid, 400, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}