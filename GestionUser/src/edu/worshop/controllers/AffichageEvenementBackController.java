/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Produit1CRUD;
import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.interfaces.ProduitCRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Produit;
import edu.worshop.model.Reservation;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author HOUYEM
 */
public class AffichageEvenementBackController implements Initializable {

    @FXML
    private ListView<Evenement> affichageEvenementBackfx;
    @FXML
    private ImageView code_qr;
    //static Evenement E;
static int id;
static Date date;
static String type,lieu,description,even_pic,titre;
static Evenement E = new Evenement();
    @FXML
    private TextField rechercheAvanceefx;
    @FXML
    private Text label;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      /*
        ListView<Evenement> list1 = affichageEvenementBackfx;
EvenementCRUD inter = new Evenement1CRUD();
List<Evenement> list2 = inter.afficherEvenement();
for (int i = 0; i < list2.size(); i++) {
    Evenement E = list2.get(i);
    list1.getItems().add(E); // add Evenement to ListView
}*/

Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
        
        ListView<Evenement> list1 = affichageEvenementBackfx;
EvenementCRUD inter = new Evenement1CRUD();
List<Evenement> list2 = inter.afficherEvenement();
for (int i = 0; i < list2.size(); i++) {
    Evenement P = list2.get(i);
    list1.getItems().add(P);
   















}
list1.setCellFactory(param -> new ListCell<Evenement>() {
    @Override
    protected void updateItem(Evenement item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText( "  " + item.getDate()+ "     " + item.getLieu()+ "     " + item.getDescription()+ "     "+ item.getType()+ "     " + " (" + item.getTitre()+ ")");
            ImageView imageView = new ImageView();
           
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            File file = new File("upload/" + item.getEven_pic());
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
                setGraphic(imageView);
            }
        }
    }
});




























// add Evenement to ListView
          // refrechpane();
         
         
    }
        
      
        

    @FXML
    private void SupprimerEvenementBack(ActionEvent event) {
    ListView<Evenement> list = (ListView<Evenement>) affichageEvenementBackfx;
    EvenementCRUD inter = new Evenement1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Evenement E = list.getSelectionModel().getSelectedItem();
        inter.supprimerEvenement(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner un événement à supprimer.");
    }
    }  
    
    

    @FXML
    private void AjoutEvenementBack(ActionEvent event) {
    
    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutEvenementBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
}

    @FXML
    private void ModifierEvenementBack(ActionEvent event) {
        
        ListView<Evenement> list = affichageEvenementBackfx;
        EvenementCRUD inter = new Evenement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
  if (selectedIndex >=0){
            
        
        Evenement e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        Date date = e.getDate();
        String type = e.getType();
        String lieu = e.getLieu();
        String description = e.getDescription();
        String even_pic = e.getEven_pic();
        String titre = e.getTitre();
        E=e;
      
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierEvenementBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        }else{showAlert("Veuillez sélectionner un événement à modifier.");
  }
    }

    private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void AjoutReservationBack(ActionEvent event) {
            ListView<Evenement> list = affichageEvenementBackfx;
        EvenementCRUD inter = new Evenement1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
         Evenement e = list.getSelectionModel().getSelectedItem();
        E=e;
        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutReservationFront.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
            

        }
    }

    @FXML
    private void qr_code(ActionEvent event) {
      Evenement E = affichageEvenementBackfx.getSelectionModel().getSelectedItem();
      

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String Information = "date : "+E.getDate()+"\n"+"type : "+E.getType()+"\n"+"lieu : "+E.getLieu()+"\n"+"description : "+E.getDescription()+"\n"+"even_pic : "+E.getEven_pic()+"\n"+"titre : "+E.getTitre();
        int width = 300;
        int height = 300;
        BufferedImage bufferedImage = null;
         try{
            BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
            code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
            
        } catch (WriterException ex) {
        }
          
    }

    @FXML
    private void trier(ActionEvent event) {
          ObservableList<Evenement> listeEvenements = affichageEvenementBackfx.getItems();
    Collections.sort(listeEvenements, new Comparator<Evenement>() {
        @Override
        public int compare(Evenement e1, Evenement e2) {
            return e1.getTitre().compareToIgnoreCase(e2.getTitre());
        }
    });
    affichageEvenementBackfx.setItems(listeEvenements);
    }

    @FXML
    private void statistiqueEvenement(ActionEvent event) {
        // Create a map to store the frequency of each type
        Map<String, Integer> typeFrequency = new HashMap<>();

        // Loop through the items in the TableView
        for (Evenement o : affichageEvenementBackfx.getItems()) {
            //int points = o.getPoints();
            String lieu = o.getLieu();

            if (typeFrequency.containsKey(lieu)) {
                typeFrequency.put(lieu, typeFrequency.get(lieu) + 1);
            } else {
                typeFrequency.put(lieu, 1);
            }
        }
    
        // Create a PieChart data set
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String lieu: typeFrequency.keySet()) {
            int frequency = typeFrequency.get(lieu);
            double percentage = (double) frequency / affichageEvenementBackfx.getItems().size() * 100;

            String percentageText = String.format("%.2f%%", percentage);


            PieChart.Data slice = new PieChart.Data(lieu + " " + percentageText, frequency);
            pieChartData.add(slice);
        }


    
         // Create a PieChart with the data set
        PieChart chart = new PieChart(pieChartData);
     
        // Show percentage values in the chart's tooltip
        for (final PieChart.Data data : chart.getData()) {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("%.2f%%", (data.getPieValue() / affichageEvenementBackfx.getItems().size() * 200)));
            Tooltip.install(data.getNode(), tooltip);
        }

        // Show the chart in a new window
        Scene scene = new Scene(chart);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
       } 

    @FXML
    private void rechercheAvancee(ActionEvent event) {
            String search = rechercheAvanceefx.getText().toLowerCase();

    ListView<Evenement> list = affichageEvenementBackfx;
    EvenementCRUD inter = new Evenement1CRUD();
    List<Evenement> list2 = inter.afficherEvenement();

    // filtrer les événements qui correspondent à la recherche
    ObservableList<Evenement> filteredList = FXCollections.observableArrayList();
    for (Evenement e : list2) {
        if (e.getTitre().toLowerCase().contains(search) ||
                e.getLieu().toLowerCase().contains(search) ||
                e.getType().toLowerCase().contains(search) ||
                e.getDescription().toLowerCase().contains(search)) {
            filteredList.add(e);
        }
    }

    // afficher les événements filtrés dans la liste
    list.setItems(filteredList);
}

    @FXML
    private void redirecttoreservation(ActionEvent event) {
         try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/AfficherReservationBack.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                    
                    
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }

    @FXML
    private void redirecttohome(ActionEvent event) {
        try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/home.fxml"));
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
    

   
    






      
    

