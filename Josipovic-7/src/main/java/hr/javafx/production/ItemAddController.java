package hr.javafx.production;


import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.math.BigDecimal;

public class ItemAddController {
    @FXML
    private TextField itemIDTextField;
    @FXML
    private TextField itemNameTextField;
    @FXML
    private ComboBox<Category> itemCategoryComboBox;
    @FXML
    private TextField itemWidthTextField;
    @FXML
    private TextField itemHeightTextField;
    @FXML
    private TextField itemLengthTextField;
    @FXML
    private TextField itemProductionCostTextField;
    @FXML
    private TextField itemSellingPriceTextField;

    @FXML
    public void initialize() {
        itemCategoryComboBox.setItems(FXCollections.observableArrayList(FileUtils.inputCategories()));
    }

    public void saveItem() {
        try {
            Long id = Long.parseLong(itemIDTextField.getText());
            String name = itemNameTextField.getText();
            Category category = itemCategoryComboBox.getValue();
            BigDecimal width = new BigDecimal(itemWidthTextField.getText());
            BigDecimal height = new BigDecimal(itemHeightTextField.getText());
            BigDecimal length = new BigDecimal(itemLengthTextField.getText());
            BigDecimal productionCost = new BigDecimal(itemProductionCostTextField.getText());
            BigDecimal sellingPrice = new BigDecimal(itemSellingPriceTextField.getText());
            Discount discount = new Discount(BigDecimal.ZERO);

            Item newItem = new Item(id, name, category, width, height, length, productionCost, sellingPrice, discount);
            FileUtils.saveItem(newItem);
            clearForm();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        itemIDTextField.clear();
        itemNameTextField.clear();
        itemCategoryComboBox.setValue(null);
        itemWidthTextField.clear();
        itemHeightTextField.clear();
        itemLengthTextField.clear();
        itemProductionCostTextField.clear();
        itemSellingPriceTextField.clear();
    }
}
