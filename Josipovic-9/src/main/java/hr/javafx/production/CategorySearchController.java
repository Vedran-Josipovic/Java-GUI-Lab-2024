package hr.javafx.production;

import hr.java.production.model.Category;
import hr.java.production.utility.DatabaseUtils;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.List;

public class CategorySearchController {
    @FXML
    private TextField categoryIdTextField;
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
    @FXML
    private TableColumn<Category, String> categoryIdTableColumn;


    public void initialize() {
        categoryIdTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getId().toString()));
        categoryNameTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getName()));
        categoryDescriptionTableColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getDescription()));
    }

    public void categorySearch() {
        //List<Category> categories = DatabaseUtils.getCategories();

        Long categoryId = null;
        if (!categoryIdTextField.getText().isEmpty()) {
            categoryId = Long.valueOf(categoryIdTextField.getText());
        }
        String categoryName = categoryNameTextField.getText();
        String categoryDescription = categoryDescriptionTextField.getText();

        Category category = new Category(categoryId, categoryName, categoryDescription);
        List<Category> filteredCategories = DatabaseUtils.getCategoriesByFilters(category);


        /*Stream<Category> filteredStream = categories.stream();
        filteredStream = filteredStream.filter(c -> c.getName().toLowerCase().contains(categoryName.toLowerCase()));
        filteredStream = filteredStream.filter(c -> c.getDescription().toLowerCase().contains(categoryDescription.toLowerCase()));*/
        //categoryTableView.setItems(FXCollections.observableArrayList(filteredStream.collect(Collectors.toList())));
        categoryTableView.setItems(FXCollections.observableArrayList(filteredCategories));
    }
}
