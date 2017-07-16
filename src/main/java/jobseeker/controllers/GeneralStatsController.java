package main.java.jobseeker.controllers;

import main.java.jobseeker.WebserviceConnector;
import main.java.jobseeker.model.QuantityPerDate;
import main.java.jobseeker.model.QuantityPerState;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.TextField;

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
    public void initialize() throws Exception {
    }

    private void initStackedChart(String tag) throws Exception {
        // get data
        List<QuantityPerDate> adFlowData = WebserviceConnector.getInstance().getAdFlowByTag(tag);
        List<QuantityPerDate> appFlowData = WebserviceConnector.getInstance().getAppFlowByTag(tag);

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

    private void initPieChart(String tag) throws Exception {
        List<QuantityPerState> quantityPerStates = WebserviceConnector.getInstance().getAppGlobalStateByTag(tag);
        List<PieChart.Data> slices = new ArrayList<>();
        for (QuantityPerState quantityPerState : quantityPerStates) {
            slices.add(new PieChart.Data(quantityPerState.getState(), quantityPerState.getNb()));
        }
        applicationStatePie.setData(FXCollections.observableArrayList(slices));
    }

    @FXML
    public void initCharts(ActionEvent event) throws Exception {
        String tag = promptTag.getText();
        initStackedChart(tag);
        initPieChart(tag);
    }
}
