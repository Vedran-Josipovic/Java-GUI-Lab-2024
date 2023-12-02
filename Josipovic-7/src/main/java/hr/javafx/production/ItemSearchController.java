package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class ItemSearchController {
    @FXML
    private TextField itemNameTextField;
    @FXML
    private ComboBox<Category> itemCategoryComboBox;//Nisam sig jel categroy ili item
    @FXML
    private TableView<Item> itemTableView;


    public void itemSearch() {
        List<Category> categories = FileUtils.inputCategories();
        List<Item> items = FileUtils.inputItems(categories);
        ObservableList itemObservableList = FXCollections.observableArrayList(items);

        itemTableView.setItems(itemObservableList);
    }

}
