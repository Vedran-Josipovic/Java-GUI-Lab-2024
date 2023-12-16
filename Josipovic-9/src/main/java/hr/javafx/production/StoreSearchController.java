package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.utility.FileUtils;
import hr.java.production.utility.InventoryAnalyzer;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StoreSearchController {
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField storeWebAddressTextField;
    @FXML
    private TextField storeItemsTextField;

    @FXML
    private TableView<Store> storeTableView;

    @FXML
    private TableColumn<Store, String> storeNameTableColumn;
    @FXML
    private TableColumn<Store, String> storeWebAddressTableColumn;
    @FXML
    private TableColumn<Store, String> storeItemsTableColumn;

    public void initialize() {
        storeNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        storeWebAddressTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getWebAddress()));
        storeItemsTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(InventoryAnalyzer.getNamesAsString(param.getValue().getItems())));
    }

    public void storeSearch() {
        List<Category> categories = FileUtils.inputCategories();
        List<Item> items = FileUtils.inputItems(categories);
        List<Store> stores = FileUtils.inputStores(items);

        String storeName = storeNameTextField.getText();
        String storeWebAddress = storeWebAddressTextField.getText();
        String storeItems = storeItemsTextField.getText();

        Stream<Store> filteredStream = stores.stream();

        filteredStream = filteredStream.filter(s -> s.getName().toLowerCase().contains(storeName.toLowerCase()));
        filteredStream = filteredStream.filter(s -> s.getWebAddress().toLowerCase().contains(storeWebAddress.toLowerCase()));
        filteredStream = filteredStream.filter(s -> InventoryAnalyzer.getNamesAsString(s.getItems()).toLowerCase().contains(storeItems.toLowerCase()));

        storeTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }
}
