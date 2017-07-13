package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Application;
import esgi.jobseeker.model.Seeker;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Created by caroline on 10/07/17.
 */
public class IndivStatsController {
    @FXML
    private ComboBox<Seeker> seekerBox;
    @FXML
    private Button submit;

    @FXML
    public void initialize() throws Exception {
        List<Seeker> seekers = WebserviceConnector.getInstance().getSupervisorSeekers();
        System.out.println(seekers);
        seekerBox.setItems(FXCollections.observableList(seekers));
    }

    @FXML
    public void onSeekerSelect(javafx.event.ActionEvent event) throws Exception {
        Seeker seeker = seekerBox.getSelectionModel().getSelectedItem();
        List<Application> apps = WebserviceConnector.getInstance().getApplicationsBySeeker(seeker.getId());
        System.out.println(apps);
    }
}
