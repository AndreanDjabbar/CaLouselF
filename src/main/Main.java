package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public Main() {
		System.out.println("Test");
		System.out.println("Test Branch");
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
	}

}
