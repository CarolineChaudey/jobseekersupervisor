package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Ad;
import esgi.jobseeker.model.AdToSend;
import esgi.jobseeker.model.ContractType;
import esgi.jobseeker.model.Website;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.controlsfx.control.CheckComboBox;

import java.util.Arrays;
import java.util.List;

/**
 * Created by caroline on 27/06/17.
 */
public class AdFormController {
    @FXML
    private TextField position;
    @FXML
    private TextArea description;
    @FXML
    private TextField contactEmail;
    @FXML
    private TextField urlApplication;
    @FXML
    private TextField jobDuration;
    @FXML
    private TextField company;
    @FXML
    private TextField tags;
    @FXML
    private ComboBox<Website> websiteComboBox;
    @FXML
    private CheckComboBox<ContractType> contractTypeCheckComboBox;


    @FXML
    public void initialize() throws Exception {
        System.out.println("AdFormController :: initialize");
        List<Website> websites = WebserviceConnector.getInstance().getAllWebsites();
        List<ContractType> contractTypes = WebserviceConnector.getInstance().getAllContractTypes();
        websiteComboBox.setItems(FXCollections.observableArrayList(websites));
        contractTypeCheckComboBox.getItems().addAll(contractTypes);
    }

    @FXML
    public void submitAd(ActionEvent event) throws Exception {
        System.out.println("AdFormController :: submitAd");
        Integer parsedJobDuration;
        try {
            parsedJobDuration = Integer.parseInt(jobDuration.getText());
        } catch (NumberFormatException e) {
            parsedJobDuration = null;
        }
        Ad ad = new Ad(null, position.getText(), description.getText(), contactEmail.getText(), urlApplication.getText(),
                        company.getText(), parsedJobDuration, convertedTags(),
                        contractTypeCheckComboBox.getCheckModel().getCheckedItems(), websiteComboBox.getValue(), null);
        AdToSend adToSend = new AdToSend(ad);
        System.out.println(adToSend.toString());
        boolean hasSucceeded = WebserviceConnector.getInstance().saveAd(adToSend);
        if (hasSucceeded) {
            showDialog(Alert.AlertType.CONFIRMATION, "Annonce enregistrée.",
                    "La nouvelle annonce est sauvegardée et en ligne.");
            resetForm();
        } else {
            showDialog(Alert.AlertType.ERROR, "Echec enregistrement",
                    "La nouvelle annonce n'a pas pu être enregistrée.");
        }
    }

    private void showDialog(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
    }

    private void resetForm() {
        position.setText("");
        description.setText("");
        contactEmail.setText("");
        urlApplication.setText("");
        jobDuration.setText("");
        company.setText("");
        tags.setText("");
    }

    private List<String> convertedTags() {
        String brutTags = tags.getText();
        String [] splitedTags = brutTags.split(",");
        return Arrays.asList(splitedTags);
    }
}
