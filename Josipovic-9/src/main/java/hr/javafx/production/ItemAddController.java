package hr.javafx.production;


import hr.java.production.exception.ValidationException;
import hr.java.production.model.Category;
import hr.java.production.model.Discount;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
            validateInputs();

            Long id = parseLongField(itemIDTextField.getText(), "ID");
            BigDecimal width = parseBigDecimalField(itemWidthTextField.getText(), "Width");
            BigDecimal height = parseBigDecimalField(itemHeightTextField.getText(), "Height");
            BigDecimal length = parseBigDecimalField(itemLengthTextField.getText(), "Length");
            BigDecimal productionCost = parseBigDecimalField(itemProductionCostTextField.getText(), "Production Cost");
            BigDecimal sellingPrice = parseBigDecimalField(itemSellingPriceTextField.getText(), "Selling Price");
            Discount discount = new Discount(BigDecimal.ZERO);

            Item newItem = new Item(id, itemNameTextField.getText(), itemCategoryComboBox.getValue(), width, height, length, productionCost, sellingPrice, discount);

            FileUtils.saveItem(newItem);
            FileUtils.appendItemToFile(newItem);

            clearForm();
            showAlert(AlertType.INFORMATION, "Success", "Item Saved", "Item has been successfully saved.");

        } catch (ValidationException e) {
            showAlert(AlertType.ERROR, "Validation Error", "Invalid Input", e.getMessage());
        }
    }

    private void validateInputs() throws ValidationException {
        if (itemIDTextField.getText().isEmpty() || itemNameTextField.getText().isEmpty() ||
                itemCategoryComboBox.getValue() == null || itemWidthTextField.getText().isEmpty() ||
                itemHeightTextField.getText().isEmpty() || itemLengthTextField.getText().isEmpty() ||
                itemProductionCostTextField.getText().isEmpty() || itemSellingPriceTextField.getText().isEmpty()) {
            throw new ValidationException("Please fill in all fields to save the item.");
        }
    }

    private Long parseLongField(String text, String fieldName) throws ValidationException {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new ValidationException(fieldName + " must be a valid number.");
        }
    }

    private BigDecimal parseBigDecimalField(String text, String fieldName) throws ValidationException {
        try {
            return new BigDecimal(text);
        } catch (NumberFormatException e) {
            throw new ValidationException(fieldName + " must be a valid decimal number.");
        }
    }

    private void showAlert(AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
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
