package themysticalbard.sei.vault;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class AccountButton extends Button {
    private boolean loggedIn = false;

    AccountButton(String text, Vault vault) {
        super(text);
        super.setOnAction(event -> {
            if(vault.currentUser() != null) {
                loggedIn = false;
                vault.setUser(null);
                super.setText("Login");
            }
            else {
                //Create a login dialog box to get the username and password
                LoginDialog loginDialog = new LoginDialog();

                String username = loginDialog.getUsername();
                String password = loginDialog.getPassword();

                //Get the Employee associated with the username and verify credentials
                loggedIn = vault.verified(username, password);

                if(loggedIn)
                    vault.setUser(username);

                //Change to logout
                super.setText(loggedIn ? "Logout" : "Login");

                //Display popup with error if wrong answers given
                if (!loggedIn) {
                    DialogPopup invalidCredentialPopup = new DialogPopup("The account credentials could not be verified, please try again.", AlertType.ERROR);
                    invalidCredentialPopup.showAndWait();
                }
            }
        });
    }
}