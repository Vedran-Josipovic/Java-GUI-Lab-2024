package hr.javafx.production;

import hr.java.production.exception.ValidationException;
import hr.java.production.model.Item;
import hr.java.production.model.Store;
import hr.java.production.utility.DatabaseUtils;
import hr.java.production.utility.FileUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;

import java.util.HashSet;
import java.util.Set;

public class StoreAddController {
    @FXML
    private TextField storeIdTextField;
    @FXML
    private TextField storeNameTextField;
    @FXML
    private TextField webAddressTextField;
    @FXML
    private ListView<Item> itemListView;

    @FXML
    public void initialize() {
        itemListView.setItems(FXCollections.observableArrayList(FileUtils.inputItems(DatabaseUtils.getCategories())));
        itemListView.setCellFactory(param -> new ComboBoxListCell<>() {
            @Override
            public void updateItem(Item item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getName());
                }
            }
        });
        itemListView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
    }

    public void saveStore() {
        try {
            validateInputs();

            Long id = parseLongField(storeIdTextField.getText(), "ID");
            String name = storeNameTextField.getText();
            String webAddress = webAddressTextField.getText();
            Set<Item> selectedItems = new HashSet<>(itemListView.getSelectionModel().getSelectedItems());

            Store newStore = new Store(id, name, webAddress, selectedItems);
            FileUtils.saveStore(newStore);
            FileUtils.appendStoreToFile(newStore);

            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Store Saved", "Store has been successfully saved.");
        } catch (ValidationException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid Input", e.getMessage());
        }


    }

    private void validateInputs() throws ValidationException {
        if (storeIdTextField.getText().isEmpty() || storeNameTextField.getText().isEmpty() ||
                webAddressTextField.getText().isEmpty() || itemListView.getSelectionModel().getSelectedItems().isEmpty()) {
            throw new ValidationException("Please fill in all fields and select at least one item to save the store.");
        }
    }

    private Long parseLongField(String text, String fieldName) throws ValidationException {
        try {
            return Long.parseLong(text);
        } catch (NumberFormatException e) {
            throw new ValidationException(fieldName + " must be a valid number.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void clearForm() {
        storeIdTextField.clear();
        storeNameTextField.clear();
        webAddressTextField.clear();
        itemListView.getSelectionModel().clearSelection();
    }
}
