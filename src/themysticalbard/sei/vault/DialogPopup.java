package themysticalbard.sei.vault;

import javafx.scene.text.Text;
import javafx.stage.Popup;

public class DialogPopup extends Popup {
    DialogPopup(String text) {
        super();
        super.getContent().add(new Text(text));
    }
}
