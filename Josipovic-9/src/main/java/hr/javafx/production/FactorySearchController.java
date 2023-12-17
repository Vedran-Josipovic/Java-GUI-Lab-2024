package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.utility.DatabaseUtils;
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
        factoryNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        factoryAddressTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getAddress().formattedToString()));
        factoryItemsTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(InventoryAnalyzer.getNamesAsString(param.getValue().getItems())));
    }

    public void factorySearch() {
        List<Category> categories = DatabaseUtils.getCategories();
        List<Item> items = FileUtils.inputItems(categories);
        List<Factory> factories = FileUtils.inputFactories(items);

        String factoryName = factoryNameTextField.getText();
        String factoryAddress = factoryAddressTextField.getText();
        String factoryItems = factoryItemsTextField.getText();

        Stream<Factory> filteredStream = factories.stream();

        filteredStream = filteredStream.filter(f -> f.getName().toLowerCase().contains(factoryName.toLowerCase()));
        filteredStream = filteredStream.filter(f -> f.getAddress().formattedToString().toLowerCase().contains(factoryAddress.toLowerCase()));
        filteredStream = filteredStream.filter(f -> InventoryAnalyzer.getNamesAsString(f.getItems()).toLowerCase().contains(factoryItems.toLowerCase()));

        factoryTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }

}
