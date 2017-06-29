package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Ad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by caroline on 26/06/17.
 */
public class AdsController {

    @FXML
    private TableView<Ad> adsTable;
    @FXML
    private TableColumn adCompany;
    @FXML
    private TableColumn adPosition;
    @FXML
    private TableColumn adDescription;
    @FXML
    private TableColumn adPublicationDate;
    @FXML
    private TableColumn adJobDuration;
    @FXML
    private TableColumn adContractTypes;
    @FXML
    private TableColumn closeAd;
    private ObservableList<Ad> adObservableList;

    @FXML
    public void initialize() throws Exception {
        setColumsValues();
        List<Ad> adsData = WebserviceConnector.getInstance().getAllAds();
        adObservableList = FXCollections.observableList(adsData);
        adsTable.setItems(adObservableList);
        System.out.println("All ads : " + adsData.toString());
    }

    private void setColumsValues() {
        adCompany.setCellValueFactory(new PropertyValueFactory<Ad, String>("company"));
        adPosition.setCellValueFactory(new PropertyValueFactory<Ad, String>("position"));
        adDescription.setCellValueFactory(new PropertyValueFactory<Ad, String>("description"));
        adContractTypes.setCellValueFactory(new PropertyValueFactory<Ad, String>("contractTypes"));
        adJobDuration.setCellValueFactory(new PropertyValueFactory<Ad, String>("jobDuration"));
        adPublicationDate.setCellValueFactory(new PropertyValueFactory<Ad, String>("publicationDate"));
    }
}
