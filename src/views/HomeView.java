package views;

import controllers.UserController;
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
        UserController userController = new UserController();

        String username = SessionManager.getInstance().getUsername();
        String role = userController.getRoleByUsername(username);

        Label welcomeLabel = new Label("Welcome, " + username + "!");
        vbox.getChildren().add(welcomeLabel);
        
        if (role.equalsIgnoreCase("seller")) {
            Button uploadItemButton = new Button("Upload Item");
            vbox.getChildren().add(uploadItemButton);
            
            uploadItemButton.setOnAction(e -> {
            	new UploadItemView().show(primaryStage);
            });
        }
        
        if (role.equalsIgnoreCase("admin")) {
            Button approveItemButton = new Button("Approve Item Request");
            vbox.getChildren().add(approveItemButton);
            
            approveItemButton.setOnAction(e -> {
                new ApproveItemView().show(primaryStage);
            });
        }
        if (role.equalsIgnoreCase("buyer")) {
        	Button BuyerviewItemButton = new Button ("View item");
        	vbox.getChildren().add(BuyerviewItemButton);
        	
        	BuyerviewItemButton.setOnAction(e->{
        		new BuyerItemView().start(primaryStage);
        	});
        }
        
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
