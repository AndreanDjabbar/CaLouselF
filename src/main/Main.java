package main;

import java.sql.PreparedStatement;

import controllers.ItemController;
import controllers.UserController;
import database.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import models.User;
import views.ApproveItemView;
import views.RegisterView;
import views.UploadItemView;

public class Main extends Application {

	public Main() {
		
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		ApproveItemView test = new ApproveItemView();
		test.show(primaryStage);
		
//		ItemController test = new ItemController();
//		test.uploadItemToQueue(1, "test", "test", "test", "test");
//		
//		BuyerPage test1 = new BuyerPage();
//		test1.start(primaryStage);
		
		
    }

}
