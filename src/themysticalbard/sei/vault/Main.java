package themysticalbard.sei.vault;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
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
                DialogPopup openPopup = new DialogPopup(vault.isOpen() ? "The vault closes." : "The vault is unlocked and opens easily.", AlertType.INFORMATION);
                openPopup.showAndWait();

                vault.toggleOpen();
                openVault.setText(vault.isOpen() ? "Close Vault" : "Open Vault");
            }
            else {
                DialogPopup myPopup = new DialogPopup(vault.isOpen() ? "The vault closes." : "You cannot open the vault, because it is locked.", vault.isOpen() ? AlertType.INFORMATION : AlertType.WARNING);
                myPopup.showAndWait();

                if(vault.isOpen()) {
                    vault.toggleOpen();
                    openVault.setText("Open Vault");
                }
            }
        });

        //Creates and initializes a button to lock and unlock the vault
        Button unlockVault = new Button("Unlock Vault");
        unlockVault.setOnAction(event -> {
            //Check to make sure a user is logged in to access the lock as admin or employee
            if(vault.currentUser() != null) {
                vault.toggleLock();
                unlockVault.setText(vault.isUnlocked() ? "Lock Vault" : "Unlock Vault");
            }
            //Display warning stating that they are unable to use this feature
            else {
                DialogPopup invalidCredentialPopup = new DialogPopup("You must be logged in to unlock or lock the vault.", AlertType.WARNING);
                invalidCredentialPopup.showAndWait();
            }
        });

        //Creates and initializes a button to modify or add a user
        Button modOrAddUser = new Button("Modify/Add User");
        modOrAddUser.setOnAction(event -> {
            //Check to see if the user is the admin to actually update users
            if(vault.currentUser() instanceof Administrator) {
                LoginDialog changePasswordDialog = new LoginDialog();
                changePasswordDialog.setTitle("Manage Accounts");
                changePasswordDialog.showAndGetInput();
                vault.changePassword(changePasswordDialog.getUsername(), changePasswordDialog.getPassword());
            }
            //Display warning stating that they are no able to access this feature
            else {
                DialogPopup invalidCredentialPopup = new DialogPopup("You must be an administrator to perform this action.", AlertType.WARNING);
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