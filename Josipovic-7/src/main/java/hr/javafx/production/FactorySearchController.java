package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class FactorySearchController {
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private TextField factoryAddressTextField;
    @FXML
    private TextField factoryItemsTextField;

    @FXML
    private TableView<Factory> factoryTableView;

    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryAddressTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryItemsTableColumn;

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

}
