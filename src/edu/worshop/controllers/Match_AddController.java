/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class Match_AddController implements Initializable {

    @FXML
    private TextField fullnameF;
    @FXML
    private TextField emailF;
    @FXML
    private TextField passwordF;
    @FXML
    private TextField adreeseF;
    @FXML
    private DatePicker dateNaissanceField;
    @FXML
    private ImageView imageView;
    @FXML
    private ComboBox<?> roleu;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        label.setFont(font);
    }    

    @FXML
    private void chooseImage(MouseEvent event) {
    }

    @FXML
    private void chooseImage(ActionEvent event) {
    }

    @FXML
    private void AddUser2(MouseEvent event) {
    }

    @FXML
    private void AddUser2(ActionEvent event) {
    }
    
}
