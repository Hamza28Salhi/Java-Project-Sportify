/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.Equipe1CRUD;
import edu.workshop.services.PDFGenerator;
import edu.worshop.interfaces.EquipeCRUD;
import edu.worshop.model.Equipe;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.SortedSet;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class AffichageEquipeBackController implements Initializable {
        @FXML
    private ListView<Equipe> affichageEquipeBackfx;
        
    //static Equipe E;
static int id,classement;
static String nom,joueurs,entraineur,categorie,picture;
static Equipe E = new Equipe();
        private ObservableList<Equipe> equipeList;
    @FXML
    private TextField searchField;
    @FXML
    private ImageView cview;
    private TextField pdff;
    @FXML
    private ImageView addeh;
    @FXML
    private ImageView editeh;
    @FXML
    private ImageView deleteh;
    @FXML
    private ImageView pdfff;
    @FXML
    private ImageView playy;
    @FXML
    private Text label;


    /**
     * Initializes the controller class.
     */
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        ListView<Equipe> list1 = affichageEquipeBackfx;
EquipeCRUD inter = new Equipe1CRUD();
List<Equipe> list2 = inter.afficherEquipe();
equipeList = FXCollections.observableArrayList(list2);

    
    
for (int i = 0; i < list2.size(); i++) {
    Equipe E = list2.get(i);
    list1.getItems().add(E); // add Equipe to ListView
}
list1.setCellFactory(param -> new ListCell<Equipe>() {
    @Override
    protected void updateItem(Equipe item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            setText(item.getId() + "      " + item.getNom() + "      " + item.getJoueurs() + "     " + item.getClassement() + "     " + item.getEntraineur() + "     " + " (" + item.getCategorie() + ")");
            ImageView imageView = new ImageView();
            imageView.setFitHeight(70);
            imageView.setFitWidth(70);
            File file = new File("upload/" + item.getPicture());
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                imageView.setImage(image);
                setGraphic(imageView);
            }
            setOnMouseClicked(event -> {
                if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                    // Handle double click event
                    // For example, you can open a new window or show a dialog
                    System.out.println("Double clicked on " + item);
                }
            });
        }
    }
});

        

        // Ajouter une fonction de recherche
        FilteredList<Equipe> filteredList = new FilteredList<>(equipeList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(e -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if ( e.getNom().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par nom complet
                } else if (e.getJoueurs().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par adresse e-mail
                } else if (String.valueOf(e.getClassement()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par ID
                } else if (e.getEntraineur().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par date de naissance
                } else if (e.getCategorie().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Recherche par adresse
                }

                return false; // Aucune correspondance trouvée
            });
        });

        SortedList<Equipe> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
 

        } 
    
    
    
    
    
    
     String path = "C:\\xampp\\htdocs\\music\\perfectgirl.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);


    private void SupprimerEquipeBack(ActionEvent event) {
    ListView<Equipe> list = (ListView<Equipe>) affichageEquipeBackfx;
    EquipeCRUD inter = new Equipe1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Equipe E = list.getSelectionModel().getSelectedItem();
        inter.supprimerEquipe(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Equipe à supprimer.");
    }
    }  
    
    private void AjoutEquipeBack(ActionEvent event) {
    
    try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
}

    private void ModifierEquipeBack(ActionEvent event) {
        
        ListView<Equipe> list = affichageEquipeBackfx;
        EquipeCRUD inter = new Equipe1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Equipe e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String nom = e.getNom();
        String joueurs = e.getJoueurs();
        int classement = e.getClassement();
        String entraineur = e.getEntraineur();
        String categorie = e.getCategorie();
     
        E=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierEquipeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);

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
    private void Matchesref(ActionEvent event) {
                try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageMatchesBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }

    @FXML
    private void Equipesref(ActionEvent event) {
              try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AffichageEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
    }

    private void musicButton(ActionEvent event) {
             mediaPlayer.play();
       // Image img = new Image("C:\\xampp\\htdocs\\music\\ala.jpg");
             
//         Notifications notificationBuilder = Notifications.create()
//                .title("Musique")
//                .text("      Musique Jouée").graphic(new ImageView(img)).hideAfter(Duration.seconds(5))
//                .position(Pos.BOTTOM_RIGHT);
     
    }

    @FXML
    private void pauseMusicButton(ActionEvent event) {
           mediaPlayer.pause();
        //Image img = new Image("fllogo.png");
      
    }


void uploadpdf(ActionEvent event)throws FileNotFoundException, IOException {
         Random rand = new Random();
        int x = rand.nextInt(1000);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload File Path");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", ".gif", ".pdf"));
        File file = fileChooser.showOpenDialog(null);
        String DBPath = "C:\\\\xampp\\\\htdocs\\\\ImageP\\\\"  + x + ".pdf";
        if (file != null) {
            FileInputStream Fsource = new FileInputStream(file.getAbsolutePath());
            FileOutputStream Fdestination = new FileOutputStream(DBPath);
            BufferedInputStream bin = new BufferedInputStream(Fsource);
            BufferedOutputStream bou = new BufferedOutputStream(Fdestination);
            System.out.println(file.getAbsoluteFile());
            String path=file.getAbsolutePath();
            Image img = new Image(file.toURI().toString());
            cview.setImage(img);    
            pdff.setText(DBPath);
            int b = 0;
            while (b != -1) {
                b = bin.read();
                bou.write(b);
            }
            bin.close();
            bou.close();          
        } else {
            System.out.println("error");
        }
    }

PDFGenerator pdf = new PDFGenerator();
    private void générerpdf(ActionEvent event) throws FileNotFoundException, SQLException {
        if(affichageEquipeBackfx.getSelectionModel().getSelectedItem()!= null){
        Equipe E = affichageEquipeBackfx.getSelectionModel().getSelectedItem();
        
            try {
                try {
					pdf.GeneratePdf("Equipe",E);
				} catch (BadElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } catch (IOException ex) {
                Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
}

    }

    @FXML
    private void addeh(MouseEvent event) {
        try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutEquipeBack.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AjoutEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
        //showAlert("Error loading");
    }
 
    }

    @FXML
    private void editeh(MouseEvent event) {
             ListView<Equipe> list = affichageEquipeBackfx;
        EquipeCRUD inter = new Equipe1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        

        Equipe e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String nom = e.getNom();
        String joueurs = e.getJoueurs();
        int classement = e.getClassement();
        String entraineur = e.getEntraineur();
        String categorie = e.getCategorie();
     
        E=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierEquipeBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void deleteh(MouseEvent event) {
         ListView<Equipe> list = (ListView<Equipe>) affichageEquipeBackfx;
    EquipeCRUD inter = new Equipe1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Equipe E = list.getSelectionModel().getSelectedItem();
        inter.supprimerEquipe(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Equipe à supprimer.");
    }
        
    }

    @FXML
    private void pdfff(MouseEvent event)  throws FileNotFoundException, SQLException {
        if(affichageEquipeBackfx.getSelectionModel().getSelectedItem()!= null){
        Equipe E = affichageEquipeBackfx.getSelectionModel().getSelectedItem();
        
            try {
                try {
					pdf.GeneratePdf("Equipe",E);
				} catch (BadElementException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } catch (IOException ex) {
                Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(AffichageEquipeBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
}

    }
    @FXML
    private void playy(MouseEvent event) {
           mediaPlayer.play();
    }
    
    
    
 
    }
