package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.QuantityPerDate;
import esgi.jobseeker.model.QuantityPerState;
import esgi.jobseeker.util.DateAxis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.TextField;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by caroline on 01/07/17.
 */
public class GeneralStatsController {
    @FXML
    private TextField promptTag;
    @FXML
    private LineChart<Date, Number> adApplicationRatio;
    @FXML
    private PieChart applicationStatePie;
    @FXML
    private DateAxis bottomAxis;
    @FXML
    private NumberAxis leftAxis;

    @FXML
    public void initialize() throws Exception {
        initPieChart();
        initStackedChart();
    }

    private void initStackedChart() throws Exception {
        // get data
        List<QuantityPerDate> adFlowData = WebserviceConnector.getInstance().getAdFlowByTag("dev");
        List<QuantityPerDate> appFlowData = WebserviceConnector.getInstance().getAppFlowByTag("dev");

        ObservableList<XYChart.Series<Date, Number>> series = FXCollections.observableArrayList();

        ObservableList<XYChart.Data<Date, Number>> adSeriesData = FXCollections.observableArrayList();
        for (QuantityPerDate quantityPerDate : adFlowData) {
            //System.out.println(quantityPerDate.getDay().toString() + quantityPerDate.getNb());
            adSeriesData.add(new XYChart.Data<Date, Number>(quantityPerDate.getDay(), quantityPerDate.getNb()));
        }

        ObservableList<XYChart.Data<Date, Number>> appSeriesData = FXCollections.observableArrayList();
        for (QuantityPerDate quantityPerDate : appFlowData) {
            System.out.println(quantityPerDate.getDay().toString() + " " + quantityPerDate.getNb());
            appSeriesData.add(new XYChart.Data<Date, Number>(quantityPerDate.getDay(), quantityPerDate.getNb()));
        }

        series.add(new XYChart.Series<>("Annonces postées", adSeriesData));
        series.add(new XYChart.Series<>("Candidatures envoyées", appSeriesData));

        adApplicationRatio.setData(series);
    }

    private void initPieChart() throws Exception {
        List<QuantityPerState> quantityPerStates = WebserviceConnector.getInstance().getAppGlobalStateByTag("dev");
        List<PieChart.Data> slices = new ArrayList<>();
        for (QuantityPerState quantityPerState : quantityPerStates) {
            slices.add(new PieChart.Data(quantityPerState.getState(), quantityPerState.getNb()));
        }
        applicationStatePie.setData(FXCollections.observableArrayList(slices));
    }
}
