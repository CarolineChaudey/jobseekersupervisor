package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Seeker;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.util.List;

/**
 * Created by caroline on 10/07/17.
 */
public class IndivStatsController {
    @FXML
    private ComboBox<Seeker> seekerBox;

    @FXML
    public void initialize() throws Exception {
        List<Seeker> seekers = WebserviceConnector.getInstance().getSupervisorSeekers();
        System.out.println(seekers);
        seekerBox.setItems(FXCollections.observableList(seekers));
    }
}
