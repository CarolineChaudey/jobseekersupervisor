package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.QuantityPerDate;
import esgi.jobseeker.model.QuantityPerState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private StackedAreaChart<Date, Integer> adApplicationRatio;
    @FXML
    private PieChart applicationStatePie;

    @FXML
    public void initialize() throws Exception {
        List<QuantityPerState> quantityPerStates = WebserviceConnector.getInstance().getAppGlobalStateByTag("dev");
        List<PieChart.Data> slices = new ArrayList<>();
        for (QuantityPerState quantityPerState : quantityPerStates) {
            slices.add(new PieChart.Data(quantityPerState.getState(), quantityPerState.getNb()));
        }
        applicationStatePie.setData(FXCollections.observableArrayList(slices));
    }

    private List<String> getDateAxis(Integer interval) {
        List<String> dateList = new ArrayList<>();
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DAY_OF_MONTH, -(interval));
        System.out.println(date.get(Calendar.DAY_OF_MONTH));

        for (int i = 0; i < interval; i++) {
            dateList.add(date.getTime().toString());
            date.add(Calendar.DAY_OF_MONTH, 1);
        }

        return dateList;
    }
}
