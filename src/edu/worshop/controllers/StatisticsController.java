/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Matches1CRUD;
import edu.worshop.interfaces.MatchesCRUD;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableMap;
import javafx.util.Callback;

public class StatisticsController implements Initializable {

  @FXML
private TableColumn<Map.Entry<String, Object>, String> teamNameColumn;
@FXML
private TableColumn<Map.Entry<String, Object>, Integer> matchCountColumn;
@FXML
private TableColumn<Map.Entry<String, Object>, String> teamIdColumn;
@FXML
private TableView<Map.Entry<String, Object>> teamTable;
@FXML
private Label totalMatchesLabel;

@Override
public void initialize(URL url, ResourceBundle rb) {
    // Set up the table columns
    teamNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Object>, String>, ObservableValue<String>>() {
        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Object>, String> p) {
            return new SimpleStringProperty(p.getValue().getKey());
        }
    });
    matchCountColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Object>, Integer>, ObservableValue<Integer>>() {
        @Override
        public ObservableValue<Integer> call(TableColumn.CellDataFeatures<Map.Entry<String, Object>, Integer> p) {
            return new SimpleIntegerProperty((Integer) p.getValue().getValue()).asObject();
        }
    });
 teamIdColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Map.Entry<String, Object>, String>, ObservableValue<String>>() {
    @Override
    public ObservableValue<String> call(TableColumn.CellDataFeatures<Map.Entry<String, Object>, String> p) {
        String teamName = p.getValue().getKey();
        Map<String, Object> teamInfo = (Map<String, Object>) p.getValue().getValue();
       Integer teamId = (Integer) ((Map<String, Object>) p.getValue().getValue()).get(teamName);
return new SimpleStringProperty(String.valueOf(teamId.intValue()));

    }
});

    // Get the match statistics from the database
    Matches1CRUD matchesCRUD = new Matches1CRUD();
    ObservableMap<String, Object> statisticsMap = matchesCRUD.getMatchStatistics();

    // Update the totalMatchesLabel
    int totalMatches = (int) statisticsMap.get("totalMatches");
    totalMatchesLabel.setText(String.valueOf(totalMatches));

    // Populate the teamTable
    ObservableMap<String, Integer> teamMatchCounts = (ObservableMap<String, Integer>) statisticsMap.get("teamMatches");
    ObservableList<Map.Entry<String, Object>> teamData = FXCollections.observableArrayList();
    for (String teamName : teamMatchCounts.keySet()) {
        Map<String, Object> teamRow = new HashMap<>();
        teamRow.put(teamName, teamMatchCounts.get(teamName));
        teamData.add(new SimpleEntry<>(teamName, teamRow));
    }
    teamTable.setItems(teamData);
}
}

