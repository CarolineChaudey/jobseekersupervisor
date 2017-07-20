package main.java.jobseeker.controllers;

import javafx.scene.control.Alert;
import main.java.jobseeker.WebserviceConnector;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectionController {
    @FXML
    private TextField loginField;
    @FXML
    private PasswordField pswdField;

    @FXML
    private void onConnectionClick(ActionEvent event) {
        try {
            boolean result = WebserviceConnector.getInstance().initConnectionToken(loginField.getText(), pswdField.getText());
            if (result) {
                changeWindow();
            } else {
                showDialog(Alert.AlertType.ERROR, "Mauvais login et/ou mot de passe.");
            }
        } catch (Exception e) {
            showDialog(Alert.AlertType.ERROR, "Avez-vous vérifié votre connexion internet ?");
        }
    }

    private void showDialog(Alert.AlertType alertType, String content) {
        final String ERROR_DIALOG_TITLE = "Echec de la connexion";
        Alert alert = new Alert(alertType);
        alert.setTitle(ERROR_DIALOG_TITLE);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void changeWindow() {
        System.out.println("in changeWindow");
        Parent root;
        try {

            root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"));
            Stage newStage = new Stage();
            newStage.setTitle("JobSeekerSupervisor");
            newStage.setScene(new Scene(root, 800, 600));
            newStage.show();
            Stage stage = (Stage) ((Node)(this.loginField)).getScene().getWindow();
            stage.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
