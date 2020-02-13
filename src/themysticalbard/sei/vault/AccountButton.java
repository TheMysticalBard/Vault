package themysticalbard.sei.vault;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.Optional;

public class AccountButton extends Button {
    private boolean loggedIn = false;

    AccountButton(String text, Vault v) {
        super(text);
        super.setOnAction(event -> {
            //Create popup to enter username and password
            class Potato {
                String username, pass;

                Potato(String u, String p){username = u; pass = p;}
            }

            Dialog<Potato> dialog = new Dialog<>();
            TextField usernamePrompt = new TextField(), passwordPrompt = new TextField();
            usernamePrompt.setPromptText("Username");
            passwordPrompt.setPromptText("Password");
            dialog.getDialogPane().setContent(new VBox(20, usernamePrompt, passwordPrompt));

            ButtonType submitType = ButtonType.OK;
            dialog.getDialogPane().getButtonTypes().add(submitType);
            dialog.getDialogPane().lookupButton(submitType).disableProperty().bind(usernamePrompt.textProperty().isEmpty().or(passwordPrompt.textProperty().isEmpty()));

            dialog.setResultConverter(new Callback<ButtonType, Potato>() {
                @Override
                public Potato call(ButtonType param) {
                    return new Potato(usernamePrompt.getText(), passwordPrompt.getText());
                }
            });

            Potato pot = dialog.showAndWait().orElse(null);

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