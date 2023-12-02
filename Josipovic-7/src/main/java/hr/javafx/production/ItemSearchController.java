package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

public class ItemSearchController {
    @FXML
    private TextField itemNameTextField;
    @FXML
    private ComboBox<Category> itemCategoryComboBox;//Nisam sig jel categroy ili item
    @FXML
    private TableView<Item> itemTableView;

    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryTableColumn;
    @FXML
    private TableColumn<Item, String> itemWidthTableColumn;
    @FXML
    private TableColumn<Item, String> itemHeightTableColumn;
    @FXML
    private TableColumn<Item, String> itemLengthTableColumn;
    @FXML
    private TableColumn<Item, String> itemProductionCostTableColumn;
    @FXML
    private TableColumn<Item, String> itemSellingPriceTableColumn;

    public void initialize(){
        itemNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        itemCategoryTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCategory().getName()));
        itemWidthTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getWidth().toString()));
        itemHeightTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getHeight().toString()));
        itemLengthTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getLength().toString()));
        itemProductionCostTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getProductionCost().toString()));
        itemSellingPriceTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSellingPrice().toString()));
    }



    public void itemSearch() {
        List<Category> categories = FileUtils.inputCategories();
        List<Item> items = FileUtils.inputItems(categories);
        ObservableList itemObservableList = FXCollections.observableArrayList(items);

        itemTableView.setItems(itemObservableList);
    }

}
