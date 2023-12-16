package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.model.Item;
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

public class CategorySearchController {
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;
    @FXML
    private TableView<Category> categoryTableView;

    @FXML
    private TableColumn<Category, String> categoryNameTableColumn;
    @FXML
    private TableColumn<Category, String> categoryDescriptionTableColumn;


    public void initialize() {
        categoryNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        categoryDescriptionTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDescription()));
    }

    public void categorySearch() {
        List<Category> categories = FileUtils.inputCategories();

        String categoryName = categoryNameTextField.getText();
        String categoryDescription = categoryDescriptionTextField.getText();

        Stream<Category> filteredStream = categories.stream();

        filteredStream = filteredStream.filter(c -> c.getName().toLowerCase().contains(categoryName.toLowerCase()));
        filteredStream = filteredStream.filter(c -> c.getDescription().toLowerCase().contains(categoryDescription.toLowerCase()));

        categoryTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
    }
}
