package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Ad;
import esgi.jobseeker.model.ContractType;
import esgi.jobseeker.model.Website;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
    private TextField email;
    @FXML
    private TextField url;
    @FXML
    private TextField jobDuration;
    @FXML
    private TextField organization;
    @FXML
    private TextField tags;
    @FXML
    private ComboBox<Website> websiteComboBox;
    //@FXML
    //private ComboBox<ContractType> contractTypeComboBox;
    @FXML
    private CheckComboBox<ContractType> contractTypeCheckComboBox;


    @FXML
    public void initialize() throws Exception {
        System.out.println("AdFormController :: initialize");
        List<Website> websites = WebserviceConnector.getInstance().getAllWebsites();
        List<ContractType> contractTypes = WebserviceConnector.getInstance().getAllContractTypes();
        websiteComboBox.setItems(FXCollections.observableArrayList(websites));
        //contractTypeComboBox.setItems(FXCollections.observableArrayList(contractTypes));
        contractTypeCheckComboBox.getItems().addAll(contractTypes);
    }

    @FXML
    public void submitAd(ActionEvent event) {
        System.out.println("AdFormController :: submitAd");
        Ad ad = new Ad(null, position.getText(), description.getText(), email.getText(), url.getText(),
                        organization.getText(), convertedTags(), null, websiteComboBox.getValue(),
                        null);
        System.out.println(ad.toString());
    }

    private List<String> convertedTags() {
        String brutTags = tags.getText();
        String [] splitedTags = brutTags.split(",");
        return Arrays.asList(splitedTags);
    }
}
