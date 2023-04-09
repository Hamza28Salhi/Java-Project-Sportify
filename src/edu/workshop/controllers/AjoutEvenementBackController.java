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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import static jdk.nashorn.internal.objects.NativeJava.type;

/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class AjoutEvenementBackController implements Initializable {

    @FXML
    private DatePicker DateEvenementfx;
    @FXML
    private TextField TypeEvenementfx;
    @FXML
    private TextField LieuEvenementfx;
    @FXML
    private TextArea DescriptionEvenementfx;
    @FXML
    private TextField Even_picEvenementfx;
    @FXML
    private TextField TitreEvenementfx;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    
    private void AjouterEvenementBack(ActionEvent event) {
                Date date = java.sql.Date.valueOf(DateEvenementfx.getValue());
        String type = TypeEvenementfx.getText();
        String lieu = LieuEvenementfx.getText();
        String description = DescriptionEvenementfx.getText();
        String even_pic = Even_picEvenementfx.getText();
        String titre = TitreEvenementfx.getText();




              Evenement E = new Evenement(date, type, lieu, description, even_pic, titre);
                    Evenement1CRUD event1 = new Evenement1CRUD();
                    event1.ajouterEvenement(E);
    }
    
}
