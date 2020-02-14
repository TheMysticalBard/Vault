package themysticalbard.sei.vault;

import javafx.scene.control.Alert;

public class DialogPopup extends Alert {
    DialogPopup(String text, AlertType type) {
        super(type);
        super.setHeaderText(null);
        super.setContentText(text);
        super.setTitle("");
    }
}