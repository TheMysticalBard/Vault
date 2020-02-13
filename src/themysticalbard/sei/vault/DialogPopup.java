package themysticalbard.sei.vault;

import javafx.scene.control.Alert;

public class DialogPopup extends Alert {
    DialogPopup(String text) {
        super(AlertType.WARNING);
        super.setHeaderText(null);
        super.setContentText(text);
    }
}