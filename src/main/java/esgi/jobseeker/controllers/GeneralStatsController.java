package esgi.jobseeker.controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.StackedAreaChart;
import javafx.scene.control.TextField;

import java.util.Date;

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

    }
}
