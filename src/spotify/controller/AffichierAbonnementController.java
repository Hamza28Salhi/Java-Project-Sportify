/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotify.controller;


import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import spotify.entities.Abonnement;
import spotify.services.AbonnementService;

/**
 * FXML Controller class
 *
 */
public class AffichierAbonnementController implements Initializable {

    @FXML
    private TableView<Abonnement> table;
    @FXML
    private TableColumn<Abonnement, String> titre;
    @FXML
    private TableColumn<Abonnement, String> lieu;
    @FXML
    private TableColumn<Abonnement, Integer> nbrplace;

    private ObservableList<Abonnement> UserData = FXCollections.observableArrayList();
    
    AbonnementService evenementService = new AbonnementService();
    
    public static int ide ;
    @FXML
    private TextField rechercher;
    



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        try {
            List<Abonnement> listb= new ArrayList<Abonnement>();
            
            listb = evenementService.getAllAbonnement();
     
            System.out.println("hello"+listb);
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            titre.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nom")
                      );
            lieu.setCellValueFactory
                      (
                              new PropertyValueFactory<>("prix")
                      );
            nbrplace.setCellValueFactory
                      (
                              new PropertyValueFactory<>("descirption")
                      );
            
        } catch (SQLDataException ex) {
            Logger.getLogger(AfficherCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }    


    @FXML
    private void Ajouter(ActionEvent event) {
        
                Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/spotify/view/AddAbonnement.fxml"));
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
    private void Modefier(ActionEvent event) {
        
      ide =  table.getSelectionModel().getSelectedItem().getId();     

        
                  Parent root;
               try {
              root = FXMLLoader.load(getClass().getResource("/spotify/view/ModefierAbonnement.fxml"));
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
    private void Supprimer(ActionEvent event) throws SQLDataException 
{        
        int id =  table.getSelectionModel().getSelectedItem().getId();     
        evenementService.deleteAbonnement(id);
        resetTableData();
        
        
        
    }
    
    
    
        
        public void resetTableData() throws SQLDataException
    {
        List<Abonnement> lisre = new ArrayList<>();
        lisre = evenementService.getAllAbonnement();
        ObservableList<Abonnement> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void Pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLDataException {
        
        
        String file_name="C:\\Users\\USER\\Desktop\\pdf\\pdfpayment.pdf";
        Document document = new Document();
        
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        List<Abonnement> lisre = new ArrayList<>();
        lisre = evenementService.getAllAbonnement();
        for (Abonnement v : lisre){
        Paragraph p =new Paragraph("Npm:"+v.getNom()+"  Description :"+v.getDescirption());
        document.add(p);
        }

        
        document.close();
        
    }

    @FXML
    private void recherche(ActionEvent event) throws SQLDataException {
        
                  titre.setCellValueFactory
                      (
                              new PropertyValueFactory<>("nom")
                      );
            lieu.setCellValueFactory
                      (
                              new PropertyValueFactory<>("prix")
                      );
            nbrplace.setCellValueFactory
                      (
                              new PropertyValueFactory<>("descirption")
                      );
    List<Abonnement> list = evenementService.getAllAbonnement();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Abonnement> filtredData= new FilteredList<>(UserData, b ->true);
            rechercher.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Abonnement> Reservation;
                filtredData.setPredicate((Abonnement pointVente) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    if(pointVente.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (String.valueOf(pointVente.getPrix()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Abonnement> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        
        
    }
    

    
}
