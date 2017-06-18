package esgi.jobseeker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ConnectionController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField pswdField;

    @FXML
    private void onConnectionClick(ActionEvent event) {
        try {
            System.out.println("Login : " + loginField.getText() + "\nPassword : " + pswdField.getText());
            boolean result = WebserviceConnector.getInstance().getConnectionToken(loginField.getText(),
                    pswdField.getText());
            if (result) {
                System.out.println("Success !");
            } else {
                System.out.println("Incorrect credentials.");
            }
        } catch (Exception e) {
            System.out.println("Error while connecting.");
        }
    }
}
