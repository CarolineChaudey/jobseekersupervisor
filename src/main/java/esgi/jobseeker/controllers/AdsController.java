package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Ad;
import esgi.jobseeker.model.AdForRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caroline on 26/06/17.
 */
public class AdsController {

    @FXML
    private TableView<AdForRow> adsTable;
    @FXML
    private TableColumn adCompany;
    @FXML
    private TableColumn adPosition;
    @FXML
    private TableColumn adEmail;
    @FXML
    private TableColumn adPublicationDate;
    @FXML
    private TableColumn adJobDuration;
    @FXML
    private TableColumn closeAd;

    private ObservableList<AdForRow> adObservableList;

    @FXML
    public void initialize() throws Exception {
        setColumsValues();
        List<Ad> adsData = WebserviceConnector.getInstance().getAllAds();
        List<AdForRow> adsForRow = convertIntoAdsForRow(adsData);
        System.out.println("OBJECT ADS = " + adsForRow.toString());

        adObservableList = FXCollections.observableList(adsForRow);
        adsTable.setItems(adObservableList);
    }

    private List<AdForRow> convertIntoAdsForRow(List<Ad> ads) {
        List<AdForRow> adsForRows = new ArrayList<>();
        for(Ad ad : ads) {
            adsForRows.add(new AdForRow(ad));
        }
        return adsForRows;
    }

    private void setColumsValues() {
        adCompany.setCellValueFactory(new PropertyValueFactory<Ad, String>("company"));
        adPosition.setCellValueFactory(new PropertyValueFactory<Ad, String>("position"));
        adEmail.setCellValueFactory(new PropertyValueFactory<Ad, String>("email"));
        adJobDuration.setCellValueFactory(new PropertyValueFactory<Ad, String>("jobDuration"));
        adPublicationDate.setCellValueFactory(new PropertyValueFactory<Ad, String>("publicationDate"));
    }
}
