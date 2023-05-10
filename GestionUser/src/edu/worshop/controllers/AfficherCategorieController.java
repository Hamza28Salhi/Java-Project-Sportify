/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.CategorieService;
import edu.worshop.model.CategorieAbionnement;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 */
public class AfficherCategorieController implements Initializable {

    @FXML
    private TableView<CategorieAbionnement> table;
    @FXML
    private TableColumn<CategorieAbionnement, String> des;
    
    
    private ObservableList<CategorieAbionnement> UserData = FXCollections.observableArrayList();
    
    CategorieService cs = new CategorieService();
    
    static int id ;
    @FXML
    private Label label;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            List<CategorieAbionnement> listb= new ArrayList<CategorieAbionnement>();
            
            listb = cs.getAllCategories();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            des.setCellValueFactory
                      (
                              new PropertyValueFactory<>("description")
                      );
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
        
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
     
            Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AddCategorie.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
        
    }

    @FXML
    private void Modif(ActionEvent event) {
        
              id =  table.getSelectionModel().getSelectedItem().getId_categori();     
              Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModefierCategorie.fxml"));
               Stage myWindow = (Stage) table.getScene().getWindow();
               Scene sc = new Scene(root);
               myWindow.setScene(sc);
               myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
               myWindow.show();
               } catch (IOException ex) {
               Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
               }
        
        
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLDataException {
        
        int id =  table.getSelectionModel().getSelectedItem().getId_categori();     
        cs.deleteCategori(id);
        resetTableData();
        
        
    }
    
    
        public void resetTableData() throws SQLDataException
    {
        List<CategorieAbionnement> lisre = new ArrayList<>();
        lisre = cs.getAllCategories();
        ObservableList<CategorieAbionnement> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void redirecttoabonnement(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AffichierAbonnement.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }
    
    
    
    
    
}
