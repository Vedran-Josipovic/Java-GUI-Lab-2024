package hr.javafx.production;

import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.utility.FileUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedViewController {
    @FXML
    private TextField itemNameTextField;
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private TextField storeNameTextField;

    @FXML
    private TableView<Factory> factoryTableView;
    @FXML
    private TableView<Store> storeTableView;
    @FXML
    private TableView<Item> itemTableView;

    @FXML
    private TableColumn<Item, String> itemNameTableColumn;
    @FXML
    private TableColumn<Factory, String> factoryNameTableColumn;
    @FXML
    private TableColumn<Store, String> storeNameTableColumn;


    public void initialize() {
        factoryNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        itemNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        storeNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));

        itemTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                filterFactoriesByItem(newValue);
                filterStoresByItem(newValue);
            }
        });
    }

    private void filterFactoriesByItem(Item selectedItem) {
        List<Factory> allFactories = FileUtils.inputFactories(FileUtils.inputItems(FileUtils.inputCategories()));
        List<Factory> filteredFactories = allFactories.stream()
                .filter(factory -> factory.getItems().contains(selectedItem))
                .collect(Collectors.toList());

        factoryTableView.setItems(FXCollections.observableArrayList(filteredFactories));
    }

    private void filterStoresByItem(Item selectedItem) {
        List<Store> allStores = FileUtils.inputStores(FileUtils.inputItems(FileUtils.inputCategories()));
        List<Store> filteredStores = allStores.stream()
                .filter(store -> store.getItems().contains(selectedItem))
                .collect(Collectors.toList());

        storeTableView.setItems(FXCollections.observableArrayList(filteredStores));
    }

    public void itemSearch() {
        List<Item> items = FileUtils.inputItems(FileUtils.inputCategories());
        String itemName = itemNameTextField.getText();

        Stream<Item> filteredStream = items.stream();

        filteredStream = filteredStream.filter(c -> c.getName().toLowerCase().contains(itemName.toLowerCase()));

        itemTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }

    public void factorySearch() {
        List<Factory> factories = FileUtils.inputFactories(FileUtils.inputItems(FileUtils.inputCategories()));
        String factoryName = factoryNameTextField.getText().toLowerCase();

        Stream<Factory> filteredStream = factories.stream()
                .filter(f -> f.getName().toLowerCase().contains(factoryName));

        factoryTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }

    public void storeSearch() {
        List<Store> stores = FileUtils.inputStores(FileUtils.inputItems(FileUtils.inputCategories()));
        String storeName = storeNameTextField.getText().toLowerCase();

        Stream<Store> filteredStream = stores.stream()
                .filter(s -> s.getName().toLowerCase().contains(storeName));

        storeTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }


}
