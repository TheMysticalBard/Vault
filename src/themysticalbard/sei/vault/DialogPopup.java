package themysticalbard.sei.vault;

import javafx.scene.control.Alert;

public class DialogPopup extends Alert {
	//Constructor initializes the type of alert, the text within, and the title
	DialogPopup(String text, AlertType type) {
		super(type);
		super.setHeaderText(null);
		super.setContentText(text);
		super.setTitle("");
	}
}