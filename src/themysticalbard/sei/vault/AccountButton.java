package themysticalbard.sei.vault;

import javafx.scene.control.Button;

public class AccountButton extends Button {
    private boolean loggedIn = false;

    AccountButton(String text) {
        super(text);
        super.setOnAction(event -> {
            //Create popup
            

            //Verify credentials


            //Change to logout
            super.setText(loggedIn ? "Login" : "Logout");
            loggedIn = !loggedIn;
        });
    }

}