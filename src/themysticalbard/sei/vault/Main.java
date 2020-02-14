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

        //This button opens and closes the vault based on whether the vault is unlocked or not.
        //If the vault is unlocked, it can be opened and closed freely. If it is locked, it may only be closed.
        Button openVault = new Button("Open Vault");
        openVault.setOnAction(event -> {
            if(vault.isUnlocked()) {
                DialogPopup openPopup = new DialogPopup(vault.isOpen() ? "The vault closes." : "The vault is unlocked and opens easily.");
                openPopup.showAndWait();

                vault.toggleOpen();
                openVault.setText(vault.isOpen() ? "Close Vault" : "Open Vault");
            }
            else {
                DialogPopup myPopup = new DialogPopup(vault.isOpen() ? "The vault closes." : "You cannot open the vault, because it is locked.");
                myPopup.showAndWait();

                if(vault.isOpen()) {
                    vault.toggleOpen();
                    openVault.setText("Open Vault");
                }
            }
        });

        Button unlockVault = new Button("Unlock Vault");
        unlockVault.setOnAction(event -> {
            if(vault.currentUser() != null) {
                vault.toggleLock();
                unlockVault.setText(vault.isUnlocked() ? "Lock Vault" : "Unlock Vault");
            }
            else {
                DialogPopup invalidCredentialPopup = new DialogPopup("You must be logged in to unlock or lock the vault.");
                invalidCredentialPopup.showAndWait();
            }
        });

        Button modOrAddUser = new Button("Modify/Add User");
        modOrAddUser.setOnAction(event -> {
            if(vault.currentUser() instanceof Administrator) {
                LoginDialog changePasswordDialog = new LoginDialog();
                vault.changePassword(changePasswordDialog.getUsername(), changePasswordDialog.getPassword());
            }
            else {
                DialogPopup invalidCredentialPopup = new DialogPopup("You must be an administrator to perform this action.");
                invalidCredentialPopup.showAndWait();
            }
        });

        //Add all the buttons to the button container
        buttonContainer.getChildren().add(openVault);
        buttonContainer.getChildren().add(unlockVault);
        buttonContainer.getChildren().add(modOrAddUser);

        root.setCenter(buttonContainer);
    }

    public static void main(String[] args) {
        launch(args);
    }
}