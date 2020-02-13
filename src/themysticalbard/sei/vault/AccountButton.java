package themysticalbard.sei.vault;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AccountButton extends Button {
    private boolean loggedIn = false;

    AccountButton(String text, Vault v) {
        super(text);
        super.setOnAction(event -> {
            //Create popup to enter username and password
            class PromptResult {
                String username, pass;

                PromptResult(String u, String p){ username = u; pass = p; }
            }

            Dialog<PromptResult> dialog = new Dialog<>();
            TextField usernamePrompt = new TextField(), passwordPrompt = new TextField();
            usernamePrompt.setPromptText("Username");
            passwordPrompt.setPromptText("Password");
            dialog.getDialogPane().setContent(new VBox(20, usernamePrompt, passwordPrompt));

            ButtonType submitType = ButtonType.OK;
            dialog.getDialogPane().getButtonTypes().add(submitType);
            dialog.getDialogPane().lookupButton(submitType).disableProperty().bind(usernamePrompt.textProperty().isEmpty().or(passwordPrompt.textProperty().isEmpty()));

            dialog.setResultConverter(param -> new PromptResult(usernamePrompt.getText(), passwordPrompt.getText()));

            PromptResult pot = dialog.showAndWait().orElse(null);

            String username = pot == null ? null : pot.username;
            String password = pot == null ? null : pot.pass;

            //Verify credentials
            Employee e = v.getAccounts().get(username);
            if (e == null) {
                loggedIn = false;
            }

            //Change to logout
            super.setText(loggedIn ? "Login" : "Logout");
            loggedIn = !loggedIn;
        });
    }

}