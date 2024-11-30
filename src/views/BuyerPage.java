package views;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Item;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuyerPage extends Application{
	
	Scene scene;
	BorderPane border;
	MenuBar menuBar;
	Menu viewItem, wishlist, history;
	ScrollPane scroll;
	TableView<Item> itemList;
	Button purchase, offer, addWishlist;
	
	public void init() {
		
		scroll = new ScrollPane();
		
		border = new BorderPane();
		
		menuBar = new MenuBar();
		
		viewItem = new Menu("View Item"); 
		wishlist = new Menu("Wishlist"); 
		history = new Menu("History"); 
		
		scene = new Scene(border,800,800);
		
		itemList = new TableView<>();
		
		purchase = new Button("Buy");
		offer = new Button("Make Offer");
		addWishlist = new Button("Add to Wishlist");
		
	}
	
	public void setTable() {
		TableColumn<Item, String> itemView = new TableColumn<>("Item List");
		itemView.setCellValueFactory(new PropertyValueFactory<>("Item List"));
		
		TableColumn<Item, Void> actionColumn = new TableColumn<>("Action Button");
		actionColumn.setCellFactory(param -> new TableCell<>() {
			HBox buttonGroup = new HBox(5, purchase, offer, addWishlist);
		});
	}
	
	public void layout() {
		border.setTop(menuBar);
		border.setCenter(scroll);
		
		scroll.setContent(itemList);
		
		menuBar.getMenus().addAll(viewItem,wishlist,history);
		
	}
	

	public static void itemView(String [] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		init();
		setTable();
		layout();
		primaryStage.setScene(scene);
		primaryStage.setTitle("Item View");
		primaryStage.show();
		
	}

}
