package main;

import java.sql.PreparedStatement;

import controllers.UserController;
import database.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import models.User;
import views.BuyerPage;
import views.RegisterView;

public class Main extends Application {

	public Main() {
		
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		RegisterView registerView = new RegisterView();
//        registerView.show(primaryStage);
        
        BuyerPage test = new BuyerPage();
        test.start(primaryStage);
    }

}
