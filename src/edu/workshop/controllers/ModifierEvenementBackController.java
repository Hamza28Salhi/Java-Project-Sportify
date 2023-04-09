/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.controllers;

import edu.workshop.services.Evenement1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.model.Evenement;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class ModifierEvenementBackController implements Initializable {

    @FXML
    private DatePicker DateMfx;
    @FXML
    private TextField TypeMfx;
    @FXML
    private TextField LieuMfx;
    @FXML
    private TextArea DescriptionMfx;
    @FXML
    private TextField Even_picMfx;
    @FXML
    private TextField TitreMfx;
    static int id;
    static Date date;
    static String type;
    static String lieu;
    static String description;
    static String even_pic;
    static String titre;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //TypeMfx.setText(String.valueOf(AffichageEvenementBackController.E.getType()));
        //LieuMfx.setText(String.valueOf(AffichageEvenementBackController.E.getLieu()));
        //DescriptionMfx.setText(String.valueOf(AffichageEvenementBackController.E.getDescription()));
        //Even_picMfx.setText(String.valueOf(AffichageEvenementBackController.E.getEven_pic()));
        //TitreMfx.setText(String.valueOf(AffichageEvenementBackController.E.getTitre()));
        
    } 
    
     private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void ModifierEvenementBack(ActionEvent event) {
        
        EvenementCRUD inter = new Evenement1CRUD();
        
        Date date = java.sql.Date.valueOf(DateMfx.getValue());
        String type = TypeMfx.getText();
        String lieu = LieuMfx.getText();
        String description = DescriptionMfx.getText();
        String even_pic = Even_picMfx.getText();
        String titre = TitreMfx.getText();
        System.out.println(AffichageEvenementBackController.id);
        Evenement even = new Evenement(AffichageEvenementBackController.id, date, type, lieu, description, even_pic, titre );
                inter.modifierEvenement(even);
                

    }
    
}
