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
        System.out.println("On a cliqué !");
    }
}
