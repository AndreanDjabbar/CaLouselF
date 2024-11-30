package views;

import java.math.BigDecimal;
import java.util.List;

import controllers.ItemController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.ItemQueue;
import models.ItemQueue;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuyerPage extends Application{
	
	ItemController itemController;
	List<ItemQueue> items;
    ObservableList<ItemQueue> data;
	Scene scene;
	BorderPane border;
	MenuBar menuBar;
	Menu viewItem, wishlist, history;
	ScrollPane scroll;
	TableView<ItemQueue> itemList;
	
	public void init() {
		
		itemController = new ItemController();
		
		items = itemController.getAllItems();
		data = FXCollections.observableArrayList(items);
		
		scroll = new ScrollPane();
		
		border = new BorderPane();
		
		menuBar = new MenuBar();
		
		viewItem = new Menu("View Item"); 
		wishlist = new Menu("Wishlist"); 
		history = new Menu("History"); 
		
		scene = new Scene(border,800,800);
		
		itemList = new TableView<>();
		
		
		
		
		
	}
	
	public void setTable() {
		TableColumn<ItemQueue, String> itemName = new TableColumn<>("Name");
		itemName.setCellValueFactory(new PropertyValueFactory<>("Name"));
		
		TableColumn<ItemQueue, String> itemCategory = new TableColumn<>("Category");
		itemCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
		
		TableColumn<ItemQueue, String> itemSize = new TableColumn<>("Size");
		itemSize.setCellValueFactory(new PropertyValueFactory<>("Size"));
		
		TableColumn<ItemQueue, BigDecimal> itemPrice = new TableColumn<>("Price");
		itemPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
		
		TableColumn<ItemQueue, Void> actionCol = new TableColumn<>("Actions");
		
		Callback<TableColumn<ItemQueue, Void>, TableCell<ItemQueue, Void>> cellFactory = 
	            new Callback<TableColumn<ItemQueue, Void>, TableCell<ItemQueue, Void>>() {
	                @Override
	                public TableCell<ItemQueue, Void> call(final TableColumn<ItemQueue, Void> param) {
	                    return new TableCell<ItemQueue, Void>() {
	                    	
	                    	

	                        private final Button purchase = new Button("Buy");
	                        private final Button offer = new Button("Make Offer");
	                        private final Button addWishlist = new Button("Add to Wishlist");
	                        private final HBox buttonGroup = new HBox(10, purchase, offer, addWishlist);

	                        {
	                            purchase.setOnAction(e -> {
	                                ItemQueue selectedItem = getTableView().getItems().get(getIndex());
	                                
	                            });

	                            offer.setOnAction(e -> {
	                                ItemQueue selectedItem = getTableView().getItems().get(getIndex());

	                            });
	                            
	                            addWishlist.setOnAction(e -> {
	                            	ItemQueue selectedItem = getTableView().getItems().get(getIndex());
	                            });
	                        }

	                        @Override
	                        protected void updateItem(Void item, boolean empty) {
	                            super.updateItem(item, empty);
	                            if (empty) {
	                                setGraphic(null);
	                            } else {
	                                setGraphic(buttonGroup);
	                            }
	                        }
	                    };
	                }
	            };
	            
	            actionCol.setCellFactory(cellFactory);
	            itemList.getColumns().addAll(itemName, itemCategory, itemSize, itemPrice, actionCol);
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
