package main;

import javafx.application.Application;
import javafx.stage.Stage;
import views.LoginView;
import views.SellerItemView;

public class Main extends Application {

	public Main() {
		
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		LoginView defaultStart = new LoginView();
		defaultStart.show(primaryStage);
//		ItemController test = new ItemController();
//		test.uploadItemToQueue(1, "test", "test", "test", "test");
//		
//		BuyerPage test1 = new BuyerPage();
//		test1.start(primaryStage);
		
    }

}
