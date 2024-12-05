package main;

import javafx.application.Application;
import javafx.scene.control.ListView.EditEvent;
import javafx.stage.Stage;
import views.EditItemView;
import views.LoginView;
import views.SellerItemView;
import views.UploadItemView;

public class Main extends Application {

	public Main() {
		
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
//		
		LoginView defaultStart = new LoginView();
		defaultStart.show(primaryStage);
		
//		EditItemView test = new EditItemView();
//		test.show(primaryStage, 0);
//		UploadItemView test = new UploadItemView();
//		test.show(primaryStage);
//		ItemController test = new ItemController();
//		test.uploadItemToQueue(1, "test", "test", "test", "test");
//		
//		BuyerPage test1 = new BuyerPage();
//		test1.start(primaryStage);
		
    }

}
