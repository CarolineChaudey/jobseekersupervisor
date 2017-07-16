package main.java.jobseeker.controllers;

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
            System.out.println("Login : " + loginField.getText() + "\nPassword : " + pswdField.getText());
            boolean result = WebserviceConnector.getInstance().getConnectionToken(loginField.getText(),
                    pswdField.getText());
            if (result) {
                System.out.println("Success !");
                changeWindow();
            } else {
                System.out.println("Incorrect credentials.");
            }
        } catch (Exception e) {
            System.out.println("Error while connecting.");
        }
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
