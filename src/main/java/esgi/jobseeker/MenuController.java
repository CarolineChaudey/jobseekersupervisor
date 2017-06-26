package esgi.jobseeker;

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
        //if (null == content.getChildren()) {
            content.getChildren().clear();
        //}
        try {
            Node stage = FXMLLoader.load(getClass().getResource("/fxml/AdsMonitoring.fxml"));
            content.getChildren().add(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
