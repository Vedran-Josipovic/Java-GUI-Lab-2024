package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    public void initialize() {
        itemNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        itemCategoryTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getCategory().getName()));
        itemWidthTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getWidth().toString()));
        itemHeightTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getHeight().toString()));
        itemLengthTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getLength().toString()));
        itemProductionCostTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getProductionCost().toString()));
        itemSellingPriceTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getSellingPrice().toString()));

        Category allCategories = new Category(999L, "Any category", "Any");
        List<Category> categories = FileUtils.inputCategories();
        categories.add(allCategories);
        itemCategoryComboBox.setItems(FXCollections.observableArrayList(categories));
        itemCategoryComboBox.getSelectionModel().selectLast();
    }


    public void itemSearch() {
        List<Category> categories = FileUtils.inputCategories();
        List<Item> items = FileUtils.inputItems(categories);

        String itemName = itemNameTextField.getText();
        Category selectedCategory = itemCategoryComboBox.getValue();

        Stream<Item> filteredStream = items.stream();
        filteredStream = filteredStream.filter(i -> i.getName().toLowerCase().contains(itemName.toLowerCase()));
        if (selectedCategory != null && !selectedCategory.getName().equals("Any category")) {
            filteredStream = filteredStream.filter(i -> i.getCategory().equals(selectedCategory));
        }

        itemTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }

}
