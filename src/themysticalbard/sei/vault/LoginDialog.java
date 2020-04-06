package themysticalbard.sei.vault;

import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Optional;

class PromptResult {
	String username, password;

	PromptResult(String user, String pass){
		username = user; password = pass;
	}
}

public class LoginDialog extends Dialog<PromptResult> {
	PromptResult result;
	//Constructor creates all the text-fields and buttons
	public LoginDialog() {
		super();
		Label usernameLabel = new Label("Username:");
		Label passwordLabel = new Label("Password:");
		TextField usernamePrompt = new TextField();
		TextField passwordPrompt = new TextField();
		usernamePrompt.setPromptText("Username");
		passwordPrompt.setPromptText("Password");
		HBox userFields = new HBox(20, usernameLabel, usernamePrompt);
		HBox passFields = new HBox(20, passwordLabel, passwordPrompt);
		super.getDialogPane().setContent(new VBox(20, userFields, passFields));

		ButtonType submitType = ButtonType.OK;
		super.getDialogPane().getButtonTypes().add(submitType);
		super.getDialogPane().lookupButton(submitType);

		super.setResultConverter(param -> new PromptResult(usernamePrompt.getText(), passwordPrompt.getText()));
	}

	public void showAndGetInput() {
		result = super.showAndWait().orElse(null);
	}

	public String getUsername() {
		return (result == null) ? null : result.username;
	}

	public String getPassword() {
		return (result == null) ? null : result.password;
	}
}
