package esgi.jobseeker.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;

/**
 * Created by caroline on 26/06/17.
 */
public class MenuController {
    @FXML
    private Menu adPart;
    @FXML
    private Pane content;

    @FXML
    public void goToAds(ActionEvent event) {
        System.out.println("MenuController :: goToAds");
        changeSubView("/fxml/AdsMonitoring.fxml");
    }

    @FXML
    public void goToCreateAd(ActionEvent event) {
        System.out.println("MenuController :: goToCreateAd");
        changeSubView("/fxml/adForm.fxml");
    }

    private void changeSubView(String subViewPath) {
        content.getChildren().clear();
        try {
            Node stage = FXMLLoader.load(getClass().getResource(subViewPath));
            content.getChildren().add(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
