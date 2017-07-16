package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Application;
import esgi.jobseeker.model.Seeker;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caroline on 10/07/17.
 */
public class IndivStatsController {
    @FXML
    private ComboBox<Seeker> seekerBox;
    @FXML
    private Button submit;
    @FXML
    private PieChart applicationStatePie;
    private final String [] states = {"CREATED", "SENT", "REFUSED", "INTERVIEW", "ACCEPTED"};

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
        initPieChart(apps);
    }

    private void initPieChart(List<Application> applications) throws Exception {
        List<PieChart.Data> slices = new ArrayList<>();
        Map<String, Integer> nbPerState = getNbPerState(applications);
        System.out.println(nbPerState);
        for (String state: nbPerState.keySet()) {
            slices.add(new PieChart.Data(state, nbPerState.get(state)));
        }
        applicationStatePie.setData(FXCollections.observableArrayList(slices));
    }

    private Map<String, Integer> getNbPerState(List<Application> applications) {
        Map<String, Integer> nbPerState = new HashMap<>();
        for (Application app : applications) {
            Integer nb = nbPerState.get(app.getState());
            if (nb == null) {
                nbPerState.put(app.getState(), 1);
            } else {
                nbPerState.put(app.getState(), nb + 1);
            }
        }
        return nbPerState;
    }
}
