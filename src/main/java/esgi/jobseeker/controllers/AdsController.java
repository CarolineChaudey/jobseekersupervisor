package esgi.jobseeker.controllers;

import esgi.jobseeker.WebserviceConnector;
import esgi.jobseeker.model.Ad;
import esgi.jobseeker.model.AdForRow;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

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
        setButtonColumn();
    }

    public void setButtonColumn() {
        // pour qu'il n'y ai un bouton que sur les lignes remplies
        closeAd.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                return new SimpleBooleanProperty(param.getValue() != null);
            }
        });
        // ajout du bouton
        closeAd.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new CloseAdCell(getStage(), adsTable);
            }
        });
    }

    private Stage getStage() {
        Stage stage = (Stage) ((Node)(this.adsTable)).getScene().getWindow();
        return stage;
    }

    // classe interne pour la cellule avec un bouton
    // apparemmment pas moyen de le faire en fxml
    private class CloseAdCell extends TableCell<AdForRow, Boolean> {
        private Button closeButton = new Button("X");
        private StackPane paddedButton = new StackPane();

        CloseAdCell(final Stage stage, final TableView table) {
            paddedButton.setPadding(new Insets(3));
            paddedButton.getChildren().add(closeButton);
            closeButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    AdForRow adToDelete = (AdForRow) CloseAdCell.this.getTableView().getItems().get(CloseAdCell.this.getIndex());
                    System.out.println(adToDelete.toString());
                    try {
                        boolean response = WebserviceConnector.getInstance().closeAd(adToDelete.getId());
                        if (response) {
                            System.out.println("Annonce clôturée.");
                            getTableView().getItems().remove(adToDelete);
                        } else {
                            System.out.println("Echec clôture.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erreur : impossible de clore l\'annonce.");
                    }
                }
            });
        }

        @Override
        protected void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!empty) {
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setGraphic(paddedButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
