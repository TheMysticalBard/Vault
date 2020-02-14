package themysticalbard.sei.vault;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Vault vault = new Vault();
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Citrils Vault");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();

        AccountButton accountManagement = new AccountButton("Login", vault);

        root.setTop(accountManagement);

        VBox buttonContainer = new VBox();

        Button openVault = new Button("Open Vault");
        openVault.setOnAction(event -> {
            //Check if vault is unlocked, otherwise give them a DialogPopup
            if(vault.isUnlocked()) {
                DialogPopup openPopup = new DialogPopup("The vault is unlocked and opens easily.");
                openPopup.showAndWait();
            }
            else {
                DialogPopup myPopup = new DialogPopup("You cannot open the vault, because it is locked.");
                myPopup.showAndWait();
            }
        });
        buttonContainer.getChildren().add(openVault);

        root.setCenter(buttonContainer);
    }

    public static void main(String[] args) {
        launch(args);
    }
}