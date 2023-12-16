package hr.javafx.production;

import hr.javafx.production.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class MenuBarController {
    public void showItemSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemSearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Item Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showCategorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categorySearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Category Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showFactorySearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factorySearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Factory Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void showStoreSearchScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeSearch.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Store Search");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showAdvancedViewScreen(){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("advancedView.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Advanced view");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void showStoreAddScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storeAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Adding new Store");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showCategoryAddScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("categoryAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Adding new Category");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showItemAddScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("itemAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Adding new Item");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void showFactoryAddScreen() {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("factoryAdd.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load(), HelloApplication.width, HelloApplication.height);
            HelloApplication.getMainStage().setTitle("Adding new Factory");
            HelloApplication.getMainStage().setScene(scene);
            HelloApplication.getMainStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }




}
