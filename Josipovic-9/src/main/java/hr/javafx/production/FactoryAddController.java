package hr.javafx.production;

import hr.java.production.exception.ValidationException;
import hr.java.production.model.Address;
import hr.java.production.model.Factory;
import hr.java.production.model.Item;
import hr.java.production.utility.FileUtils;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;

import java.util.HashSet;
import java.util.Set;

public class FactoryAddController {
    @FXML
    private TextField factoryIdTextField;
    @FXML
    private TextField factoryNameTextField;
    @FXML
    private ComboBox<Address> addressComboBox;
    @FXML
    private ListView<Item> itemListView;

    @FXML
    public void initialize() {
        addressComboBox.setItems(FXCollections.observableArrayList(FileUtils.inputAddresses()));
        addressComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Address address, boolean empty) {
                super.updateItem(address, empty);
                if (address == null || empty) {
                    setText(null);
                } else {
                    setText(address.formattedToString());
                }
            }
        });

        addressComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Address address, boolean empty) {
                super.updateItem(address, empty);
                if (empty || address == null) {
                    setText(null);
                } else {
                    setText(address.formattedToString());
                }
            }
        });

        itemListView.setItems(FXCollections.observableArrayList(FileUtils.inputItems(FileUtils.inputCategories())));
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

    public void saveFactory() {
        try {
            validateInputs();

            Long id = parseLongField(factoryIdTextField.getText(), "ID");
            String name = factoryNameTextField.getText();
            Address address = addressComboBox.getValue();
            Set<Item> selectedItems = new HashSet<>(itemListView.getSelectionModel().getSelectedItems());

            Factory newFactory = new Factory(id, name, address, selectedItems);
            FileUtils.saveFactory(newFactory);
            FileUtils.appendFactoryToFile(newFactory);
            clearForm();
            showAlert(Alert.AlertType.INFORMATION, "Success", "Factory Saved", "Factory has been successfully saved.");
        } catch (ValidationException e) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Invalid Input", e.getMessage());
        }
    }

    private void validateInputs() throws ValidationException {
        if (factoryIdTextField.getText().isEmpty() || factoryNameTextField.getText().isEmpty() ||
                addressComboBox.getValue() == null || itemListView.getSelectionModel().getSelectedItems().isEmpty()) {
            throw new ValidationException("Please fill in all fields and select at least one item to save the factory.");
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
        factoryIdTextField.clear();
        factoryNameTextField.clear();
        addressComboBox.setValue(null);
        itemListView.getSelectionModel().clearSelection();
    }
}
