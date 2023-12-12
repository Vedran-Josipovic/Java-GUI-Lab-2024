package hr.javafx.production;

import hr.java.production.exception.ValidationException;
import hr.java.production.model.Category;
import hr.java.production.utility.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class CategoryAddController {
    @FXML
    private TextField categoryIdTextField;
    @FXML
    private TextField categoryNameTextField;
    @FXML
    private TextField categoryDescriptionTextField;

    public void addCategory() {
        try {
            validateInputs();

            Long id = parseLongField(categoryIdTextField.getText(), "ID");
            String name = categoryNameTextField.getText().trim();
            String description = categoryDescriptionTextField.getText().trim();

            Category newCategory = new Category(id, name, description);
            FileUtils.saveCategory(newCategory);
            FileUtils.appendCategoryToFile(newCategory);
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Category added successfully.");
        } catch (ValidationException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", e.getMessage());
        }
    }

    private void validateInputs() throws ValidationException {
        if (categoryIdTextField.getText().isEmpty() || categoryNameTextField.getText().isEmpty() || categoryDescriptionTextField.getText().isEmpty()) {
            throw new ValidationException("Please fill in all fields.");
        }
    }

    private Long parseLongField(String text, String fieldName) throws ValidationException {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new ValidationException(fieldName + " must be a valid number.");
        }
    }

    private void clearForm() {
        categoryIdTextField.clear();
        categoryNameTextField.clear();
        categoryDescriptionTextField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
