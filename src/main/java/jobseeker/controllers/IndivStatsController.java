package main.java.jobseeker.controllers;

import main.java.jobseeker.WebserviceConnector;
import main.java.jobseeker.model.AdForRow;
import main.java.jobseeker.model.ApplicationForRow;
import main.java.jobseeker.model.Seeker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    @FXML
    private TableView<ApplicationForRow> appTable;
    @FXML
    private TableColumn adPosition;
    @FXML
    private TableColumn date;
    @FXML
    private TableColumn adCompany;
    @FXML
    private TableColumn adState;

    private ObservableList<ApplicationForRow> adObservableList;

    @FXML
    public void initialize() throws Exception {
        List<Seeker> seekers = WebserviceConnector.getInstance().getSupervisorSeekers();
        System.out.println(seekers);
        seekerBox.setItems(FXCollections.observableList(seekers));
        setColumsValues();
    }

    @FXML
    public void onSeekerSelect(javafx.event.ActionEvent event) throws Exception {
        Seeker seeker = seekerBox.getSelectionModel().getSelectedItem();
        List<ApplicationForRow> apps = WebserviceConnector.getInstance().getApplicationsBySeeker(seeker.getId());
        System.out.println(apps);
        initPieChart(apps);
        initTable(apps);
    }

    private void initTable(List<ApplicationForRow> applications) {
        adObservableList = FXCollections.observableList(applications);
        appTable.setItems(adObservableList);
    }

    private void initPieChart(List<ApplicationForRow> applications) throws Exception {
        List<PieChart.Data> slices = new ArrayList<>();
        Map<String, Integer> nbPerState = getNbPerState(applications);
        System.out.println(nbPerState);
        for (String state: nbPerState.keySet()) {
            slices.add(new PieChart.Data(state, nbPerState.get(state)));
        }
        applicationStatePie.setData(FXCollections.observableArrayList(slices));
    }

    private Map<String, Integer> getNbPerState(List<ApplicationForRow> applications) {
        Map<String, Integer> nbPerState = new HashMap<>();
        for (ApplicationForRow app : applications) {
            Integer nb = nbPerState.get(app.getState());
            if (nb == null) {
                nbPerState.put(app.getState(), 1);
            } else {
                nbPerState.put(app.getState(), nb + 1);
            }
        }
        return nbPerState;
    }

    private void setColumsValues() {
        adCompany.setCellValueFactory(new PropertyValueFactory<AdForRow, String>("company"));
        adPosition.setCellValueFactory(new PropertyValueFactory<AdForRow, String>("position"));
        date.setCellValueFactory(new PropertyValueFactory<AdForRow, String>("createdAt"));
        adState.setCellValueFactory(new PropertyValueFactory<AdForRow, String>("state"));
    }
}
