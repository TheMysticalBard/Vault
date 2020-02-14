package themysticalbard.sei.vault;

import javafx.scene.control.*;
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
                //Class that stores user prompts in the Dialog popup.
                class PromptResult {
                    String username, password;

                    PromptResult(String user, String pass){
                        username = user; password = pass;
                    }
                }

                //Creates a dialog popup for the user to input the username and password
                Dialog<PromptResult> dialog = new Dialog<>();
                Label usernameLabel = new Label("Username:");
                Label passwordLabel = new Label("Password:");
                TextField usernamePrompt = new TextField();
                TextField passwordPrompt = new TextField();
                usernamePrompt.setPromptText("Username");
                passwordPrompt.setPromptText("Password");
                HBox userFields = new HBox(20, usernameLabel, usernamePrompt);
                HBox passFields = new HBox(20, passwordLabel, passwordPrompt);
                dialog.getDialogPane().setContent(new VBox(20, userFields, passFields));

                ButtonType submitType = ButtonType.OK;
                dialog.getDialogPane().getButtonTypes().add(submitType);
                dialog.getDialogPane().lookupButton(submitType);

                dialog.setResultConverter(param -> new PromptResult(usernamePrompt.getText(), passwordPrompt.getText()));

                PromptResult promptResult = dialog.showAndWait().orElse(null);

                String username = (promptResult == null) ? null : promptResult.username;
                String password = (promptResult == null) ? null : promptResult.password;

                //Get the Employee associated with the username and verify credentials
                loggedIn = vault.verified(username, password);

                if(loggedIn)
                    vault.setUser(username);

                //Change to logout
                super.setText(loggedIn ? "Logout" : "Login");

                //Display popup with error if wrong answers given
                if (!loggedIn) {
                    DialogPopup invalidCredentialPopup = new DialogPopup("The account credentials could not be verified, please try again.");
                    invalidCredentialPopup.showAndWait();
                }
            }
        });
    }
}