/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Post1CRUD;
import edu.worshop.interfaces.PostCRUD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;

public class GraphdataController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private BarChart<?, ?> barChart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Create the x and y axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Set the labels for the axes
        xAxis.setLabel("Titre des Posts");
        yAxis.setLabel("Nombre de Likes");

        // Create the chart
        barChart = new BarChart<>(xAxis, yAxis);

        // Create the data series
        XYChart.Series dataSeries = new XYChart.Series();
        dataSeries.setName("Likes par post");

        // Add data to the series
        // Replace with actual data from your database
        dataSeries.getData().add(new XYChart.Data("Post 1", 10));
        dataSeries.getData().add(new XYChart.Data("Post 2", 20));
        dataSeries.getData().add(new XYChart.Data("Post 3", 15));
        dataSeries.getData().add(new XYChart.Data("Post 4", 5));

/*PostCRUD inter = new Post1CRUD();
Post_ListControl

File file = new File("upload/" + P.getImagePost());
            Image image = new Image(file.toURI().toString());
            PostImage.setImage(image);
for (int i = 0; i < list2.size(); i++) {
    Commentaire C = list2.get(i);
    list1.getItems().add(C); // add Post to ListView
}
*/
        // Add the series to the chart
        barChart.getData().add(dataSeries);

        // Add the chart to the anchor pane
        main_form.getChildren().add(barChart);
    }
}
