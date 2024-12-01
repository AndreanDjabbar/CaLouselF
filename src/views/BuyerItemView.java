package views;

import java.math.BigDecimal;
import java.util.List;

import controllers.ItemController;
import controllers.UserController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import models.Item;
import utils.SessionManager;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BuyerItemView extends Application {

    private ItemController itemController;
    private ObservableList<Item> data;
    private TableView<Item> itemList;
    private Button backButton;
    private Scene scene;
    private String userRole;

    @Override
    public void start(Stage primaryStage) {
        initComponents();
        configureLayout();

        UserController userController = new UserController();
        String username = SessionManager.getInstance().getUsername();
        userRole = userController.getRoleByUsername(username);  

        configureActions(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Item View");
        primaryStage.show();
    }

    private void initComponents() {
        itemController = new ItemController();
        List<Item> items = itemController.getAllItems();
        data = FXCollections.observableArrayList(items);

        itemList = new TableView<>();
        backButton = new Button("Back");

        createTableColumns();
        scene = new Scene(createMainLayout(), 900, 400);
    }

    private void createTableColumns() {
        TableColumn<Item, String> itemName = new TableColumn<>("Name");
        itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));

        TableColumn<Item, String> itemCategory = new TableColumn<>("Category");
        itemCategory.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));

        TableColumn<Item, String> itemSize = new TableColumn<>("Size");
        itemSize.setCellValueFactory(new PropertyValueFactory<>("itemSize"));

        TableColumn<Item, BigDecimal> itemPrice = new TableColumn<>("Price");
        itemPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        TableColumn<Item, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setPrefWidth(300);  

        actionCol.setCellFactory(createActionButtons());

        itemList.getColumns().addAll(itemName, itemCategory, itemSize, itemPrice, actionCol);
        itemList.setItems(data);
    }



    private Callback<TableColumn<Item, Void>, TableCell<Item, Void>> createActionButtons() {
        return param -> new TableCell<>() {
            private final Button purchase = new Button("Purchase");
            private final Button offer = new Button("Make Offer");
            private final Button addWishlist = new Button("Add to Wishlist");
            private final HBox buttonGroup = new HBox(5, purchase, offer, addWishlist);

            {
                if ("buyer".equals(userRole)) {
                    purchase.setOnAction(e -> handlePurchase(getCurrentItem()));
                    offer.setOnAction(e -> handleOffer(getCurrentItem()));
                    addWishlist.setOnAction(e -> handleWishlist(getCurrentItem()));
                } else {
                    buttonGroup.getChildren().clear();
                }
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || getIndex() >= getTableView().getItems().size()) {
                    setGraphic(null);
                } else {
                    setGraphic(buttonGroup);
                }
            }

            private Item getCurrentItem() {
                return getTableView().getItems().get(getIndex());
            }
        };
    }

    private void handlePurchase(Item item) {
        System.out.println("Purchased: " + item.getItemName());
    }

    private void handleOffer(Item item) {
        System.out.println("Offer made for: " + item.getItemName());
    }

    private void handleWishlist(Item item) {
        System.out.println("Added to wishlist: " + item.getItemName());
    }

    private BorderPane createMainLayout() {
        BorderPane border = new BorderPane();
        border.setTop(backButton);
        ScrollPane scrollPane = new ScrollPane(itemList);
        scrollPane.setFitToWidth(true);  
        border.setCenter(scrollPane);
        return border;
    }


    private void configureLayout() {
        
    }

    private void configureActions(Stage primaryStage) {
        backButton.setOnAction(e -> {
            System.out.println("Navigating back to Home...");
        });
    }

    public static void itemView(String[] args) {
        launch(args);
    }
}
