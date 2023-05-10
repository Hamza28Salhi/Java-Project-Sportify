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
import static com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil.matches;
import edu.workshop.services.Mailservice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.Matches1CRUD;
import edu.worshop.interfaces.MatchesCRUD;
import edu.worshop.model.Matches;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.regex.Pattern.matches;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.persistence.Column;
import jfxtras.scene.control.CalendarPicker;



/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class AffichageMatchesBackController implements Initializable {
     @FXML
    private ListView<Matches> affichageMatchesBackfx;
           private Matches1CRUD matchesCRUD = new Matches1CRUD();


        
        private List<String> matches = new ArrayList<>();





    //static Matches E;
static int id;   
static Date date;
static String nom,stade,score,nomEquipeId;
static Matches E = new Matches();
    private TextField lieutf;
    @FXML
    private ImageView code_qr;
    @FXML
    private Button SendMail;
    @FXML
    private TextField recipientField;
    @FXML
    private TextField idField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField stadeField;
    @FXML
    private Text label;
    @FXML
    private Button fill;
    @FXML
    private ImageView bellbi;
    @FXML
    private ImageView stats;
    @FXML
    private ImageView printa;
    @FXML
    private ImageView qrcodeuh;
    @FXML
    private ImageView addeh;
    @FXML
    private ImageView deleteh;
    @FXML
    private ImageView editeh;
    @FXML
    private TableView<Matches> affichageMatchesBackfxt;
    @FXML
private TableColumn<Matches, Integer> idColumn;
@FXML
private TableColumn<Matches, String> nomColumn;
@FXML
private TableColumn<Matches, String> stadeColumn;
@FXML
private TableColumn<Matches, String> scoreColumn;
@FXML
private TableColumn<Matches, Date> dateColumn;
    @FXML
    private Button trier;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
    // ListView
        ListView<Matches> list1 = affichageMatchesBackfx;
        MatchesCRUD inter = new Matches1CRUD();
        List<Matches> list2 = inter.afficherMatches();
        for (int i = 0; i < list2.size(); i++) {
            Matches E = list2.get(i);
            list1.getItems().add(E); // add Matches to ListView
        }

        // TableView
        //idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        stadeColumn.setCellValueFactory(new PropertyValueFactory<>("stade"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

        List<Matches> matchesList = matchesCRUD.afficherMatches();
        ObservableList<Matches> matchesObservableList = FXCollections.observableArrayList(matchesList);
        affichageMatchesBackfxt.setItems(matchesObservableList);
        affichageMatchesBackfxt.getColumns().addAll(idColumn, nomColumn, stadeColumn, scoreColumn, dateColumn);
        
         Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
        
    }


         
    


    private void SupprimerMatchesBack(ActionEvent event) {
    ListView<Matches> list = (ListView<Matches>) affichageMatchesBackfx;
    MatchesCRUD inter = new Matches1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Matches E = list.getSelectionModel().getSelectedItem();
        inter.supprimerMatches(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Matches à supprimer.");
    }
    }  
    


    private void ModifierMatchesBack(ActionEvent event) {
        
        ListView<Matches> list = affichageMatchesBackfx;
        MatchesCRUD inter = new Matches1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
        

        Matches e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String nom = e.getNom();
        String stade = e.getStade();
        String score = e.getScore();
        String nomEquipeId = e.nomEquipeId.getNom();
     
        E=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierMatchesBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
  private void showAlert(String message) {
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void AjoutMatchesBack(ActionEvent event) {
           try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutMatchesBack.fxml"));
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

    private void map(ActionEvent event) throws IOException {
          Parent previousScene = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/Map.fxml"));
    Scene scene = new Scene(previousScene);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    
    }
      public void setlocal(String ad){
        this.lieutf.setText(ad);
    }

private void QrCode(ActionEvent event) {
    Matches p = affichageMatchesBackfx.getSelectionModel().getSelectedItem();

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    String Information = "nom du match : "+p.getNom()+"\n"+"Date : "+p.getDate();
    int width = 300;
    int height = 300;
BufferedImage bufferedImage = null;
try {
    BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    bufferedImage.createGraphics();

    Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
    
    // Set background color to red-blue gradient
    GradientPaint gradient = new GradientPaint(0, 0, new Color(238, 33, 50), width, height, new Color(0, 47, 108));
    graphics.setPaint(gradient);
    graphics.fillRect(0, 0, width, height);
    
    // Calculate the size of the QR code without padding
    int qrCodeSize = Math.min(width, height);

    // Calculate the padding needed around the QR code
    int paddingX = (width - qrCodeSize) / 2;
    int paddingY = (height - qrCodeSize) / 2;
    

    // Draw the QR code onto the image
    graphics.setColor(Color.BLACK);
    for (int i = 0; i < qrCodeSize; i++) {
        for (int j = 0; j < qrCodeSize; j++) {
            if (byteMatrix.get(i, j)) {
                graphics.fillRect(i + paddingX, j + paddingY, 1, 1);
            }
        }
    }

    // Load logo image and resize if needed
    BufferedImage logoImage = ImageIO.read(new File("upload/logo.png"));
    if (logoImage.getWidth() > qrCodeSize / 4) {
        logoImage = resizeImage(logoImage, qrCodeSize / 4, qrCodeSize / 4);
    }

    // Draw logo onto QR code
    int centerX = paddingX + qrCodeSize / 2;
    int centerY = paddingY + qrCodeSize / 2;
    graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    graphics.drawImage(logoImage, centerX - (logoImage.getWidth() / 2), centerY - (logoImage.getHeight() / 2), null);

    System.out.println("Success...");

    code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
} catch (WriterException | IOException ex) {
    ex.printStackTrace();
}

}
private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics2D = resizedImage.createGraphics();
    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
    graphics2D.dispose();
    return resizedImage;
}

    private void stat(ActionEvent event) {
          // Create a map to store the frequency of each type
    Map<String, Integer> typeFrequency = new HashMap<>();

    // Loop through the items in the TableView
    for (Matches match : affichageMatchesBackfx.getItems()) {
        String type = match.nomEquipeId.getNom();
        if (typeFrequency.containsKey(type)) {
            typeFrequency.put(type, typeFrequency.get(type) + 1);
        } else {
            typeFrequency.put(type, 1);
        }
    }
    
    // Create a PieChart data set
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (String type : typeFrequency.keySet()) {
        int frequency = typeFrequency.get(type);
        double percentage = (double) frequency / affichageMatchesBackfx.getItems().size() * 100;
        String percentageText = String.format("%.2f%%", percentage);
        PieChart.Data slice = new PieChart.Data(type + " " + percentageText, frequency);
        pieChartData.add(slice);
    }

    // Create a PieChart with the data set
    PieChart chart = new PieChart(pieChartData);

    // Show percentage values in the chart's tooltip
    for (final PieChart.Data data : chart.getData()) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format("%.2f%%", (data.getPieValue() / affichageMatchesBackfx.getItems().size() * 200)));
        Tooltip.install(data.getNode(), tooltip);
    }

    // Show the chart in a new window
    Scene scene = new Scene(chart);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
        
        
    }
     public void setMatchesCRUD(Matches1CRUD matchesCRUD) {
        this.matchesCRUD = matchesCRUD;
    }

    private void onStartButtonClicked(ActionEvent event) {
                 matchesCRUD = new Matches1CRUD();

        if (matchesCRUD != null) {
            matchesCRUD.startNotifications();
        } else {
            System.out.println("matchesCRUD is null");
        }
    }

    private void onStopButtonClicked(ActionEvent event) {
        
                matchesCRUD.stopNotifications();

    }

private void handlePrintButtonAction(ActionEvent event) {
    // get the list of matches
    List<Matches> matches = matchesCRUD.afficherMatches();

    // create a new TableView to display the matches
    TableView<Matches> tableView = new TableView<>();

    // create the columns for the table
    TableColumn<Matches, Integer> idColumn = new TableColumn<>("ID");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Matches, String> dateColumn = new TableColumn<>("Date");
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    TableColumn<Matches, String> nomColumn = new TableColumn<>("Nom");
    nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

    TableColumn<Matches, String> stadeColumn = new TableColumn<>("Stade");
    stadeColumn.setCellValueFactory(new PropertyValueFactory<>("stade"));
    
     TableColumn<Matches, String> scoreColumn = new TableColumn<>("score");
    scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    
    



    // add the columns to the table
    tableView.getColumns().addAll(idColumn, dateColumn, nomColumn, stadeColumn,scoreColumn);

    // add the matches to the table
    tableView.getItems().addAll(matches);

    // create a new Stage to display the table
    Stage printStage = new Stage();
    Scene printScene = new Scene(tableView);

    // set the print button as the default button for the stage
    Button printButton = new Button("Print");
    printButton.setOnAction(e -> {
        // create a PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // print the table view
            boolean success = job.printPage(tableView);
            if (success) {
                job.endJob();
            }
        }
        // close the print stage
        printStage.close();
    });
    printScene.setRoot(new VBox(tableView, printButton));

    // show the print stage
    printStage.setScene(printScene);
    printStage.show();
}


    @FXML
    private void SendMail(ActionEvent event) {
        Mailservice mailService = new Mailservice();
    String recipient = recipientField.getText();
     String date = idField.getText();
    String nom = nomField.getText();
    String stade = stadeField.getText();
    mailService.sendMail(recipient,date,nom,stade);
        
    }

    @FXML
    private void recipientField(ActionEvent event) {
    }

    @FXML
    private void idField(ActionEvent event) {
    }

    @FXML
    private void nomField(ActionEvent event) {
    }

    @FXML
    private void stadeField(ActionEvent event) {
    }

    @FXML
private void fill(ActionEvent event) {
    ListView<Matches> list = (ListView<Matches>) affichageMatchesBackfx;
    Matches selectedMatch = list.getSelectionModel().getSelectedItem();
    if (selectedMatch != null) {
        idField.setText(String.valueOf(selectedMatch.getId()));
        nomField.setText(selectedMatch.getNom());
        stadeField.setText(selectedMatch.getStade());
        // Convert date to string and set it in idField
        String dateString = selectedMatch.getDate().toString();
        idField.setText(dateString);
    } else {
        showAlert("Veuillez sélectionner une Matches.");
    }
}

    @FXML
    private void bellbi(MouseEvent event) {
                  matchesCRUD = new Matches1CRUD();

        if (matchesCRUD != null) {
            matchesCRUD.startNotifications();
        } else {
            System.out.println("matchesCRUD is null");
        }
        
        
    }

    @FXML
    private void stats(MouseEvent event) {
           // Create a map to store the frequency of each type
    Map<String, Integer> typeFrequency = new HashMap<>();

    // Loop through the items in the TableView
    for (Matches match : affichageMatchesBackfx.getItems()) {
        String type = match.nomEquipeId.getNom();
        if (typeFrequency.containsKey(type)) {
            typeFrequency.put(type, typeFrequency.get(type) + 1);
        } else {
            typeFrequency.put(type, 1);
        }
    }
    
    // Create a PieChart data set
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (String type : typeFrequency.keySet()) {
        int frequency = typeFrequency.get(type);
        double percentage = (double) frequency / affichageMatchesBackfx.getItems().size() * 100;
        String percentageText = String.format("%.2f%%", percentage);
        PieChart.Data slice = new PieChart.Data(type + " " + percentageText, frequency);
        pieChartData.add(slice);
    }

    // Create a PieChart with the data set
    PieChart chart = new PieChart(pieChartData);

    // Show percentage values in the chart's tooltip
    for (final PieChart.Data data : chart.getData()) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format("%.2f%%", (data.getPieValue() / affichageMatchesBackfx.getItems().size() * 200)));
        Tooltip.install(data.getNode(), tooltip);
    }

    // Show the chart in a new window
    Scene scene = new Scene(chart);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
        
    }

    @FXML
    private void printa(MouseEvent event) {
        // get the list of matches
    List<Matches> matches = matchesCRUD.afficherMatches();

    // create a new TableView to display the matches
    TableView<Matches> tableView = new TableView<>();

    // create the columns for the table
    TableColumn<Matches, Integer> idColumn = new TableColumn<>("ID");
    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

    TableColumn<Matches, String> dateColumn = new TableColumn<>("Date");
    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));

    TableColumn<Matches, String> nomColumn = new TableColumn<>("Nom");
    nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));

    TableColumn<Matches, String> stadeColumn = new TableColumn<>("Stade");
    stadeColumn.setCellValueFactory(new PropertyValueFactory<>("stade"));
    
     TableColumn<Matches, String> scoreColumn = new TableColumn<>("score");
    scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));
    
    



    // add the columns to the table
    tableView.getColumns().addAll(idColumn, dateColumn, nomColumn, stadeColumn,scoreColumn);

    // add the matches to the table
    tableView.getItems().addAll(matches);

    // create a new Stage to display the table
    Stage printStage = new Stage();
    Scene printScene = new Scene(tableView);

    // set the print button as the default button for the stage
    Button printButton = new Button("Print");
    printButton.setOnAction(e -> {
        // create a PrinterJob
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            // print the table view
            boolean success = job.printPage(tableView);
            if (success) {
                job.endJob();
            }
        }
        // close the print stage
        printStage.close();
    });
    printScene.setRoot(new VBox(tableView, printButton));

    // show the print stage
    printStage.setScene(printScene);
    printStage.show();
    }

    @FXML
    private void qrcodeur(MouseEvent event) {
            Matches p = affichageMatchesBackfx.getSelectionModel().getSelectedItem();

    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    String Information = "nom du match : "+p.getNom()+"\n"+"Date : "+p.getDate();
    int width = 300;
    int height = 300;
BufferedImage bufferedImage = null;
try {
    BitMatrix byteMatrix = qrCodeWriter.encode(Information, BarcodeFormat.QR_CODE, width, height);
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    bufferedImage.createGraphics();

    Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
    
    // Set background color to red-blue gradient
    GradientPaint gradient = new GradientPaint(0, 0, new Color(238, 33, 50), width, height, new Color(0, 47, 108));
    graphics.setPaint(gradient);
    graphics.fillRect(0, 0, width, height);
    
    // Calculate the size of the QR code without padding
    int qrCodeSize = Math.min(width, height);

    // Calculate the padding needed around the QR code
    int paddingX = (width - qrCodeSize) / 2;
    int paddingY = (height - qrCodeSize) / 2;
    

    // Draw the QR code onto the image
    graphics.setColor(Color.BLACK);
    for (int i = 0; i < qrCodeSize; i++) {
        for (int j = 0; j < qrCodeSize; j++) {
            if (byteMatrix.get(i, j)) {
                graphics.fillRect(i + paddingX, j + paddingY, 1, 1);
            }
        }
    }

    // Load logo image and resize if needed
    BufferedImage logoImage = ImageIO.read(new File("upload/logo.png"));
    if (logoImage.getWidth() > qrCodeSize / 4) {
        logoImage = resizeImage(logoImage, qrCodeSize / 4, qrCodeSize / 4);
    }

    // Draw logo onto QR code
    int centerX = paddingX + qrCodeSize / 2;
    int centerY = paddingY + qrCodeSize / 2;
    graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
    graphics.drawImage(logoImage, centerX - (logoImage.getWidth() / 2), centerY - (logoImage.getHeight() / 2), null);

    System.out.println("Success...");

    code_qr.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
} catch (WriterException | IOException ex) {
    ex.printStackTrace();
}

        
    }

    @FXML
    private void addeh(MouseEvent event) {
         try {
        Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/AjoutMatchesBack.fxml"));
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
    private void deleteh(MouseEvent event) {
         ListView<Matches> list = (ListView<Matches>) affichageMatchesBackfx;
    MatchesCRUD inter = new Matches1CRUD();

    int selectedIndex = list.getSelectionModel().getSelectedIndex();
    if (selectedIndex >= 0) {
        Matches E = list.getSelectionModel().getSelectedItem();
        inter.supprimerMatches(E.getId());
        list.getItems().remove(selectedIndex);
    } else {
        showAlert("Veuillez sélectionner une Matches à supprimer.");
    }
    }

    @FXML
    private void editeh(MouseEvent event) {
          ListView<Matches> list = affichageMatchesBackfx;
        MatchesCRUD inter = new Matches1CRUD();
        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        
        

        Matches e = list.getSelectionModel().getSelectedItem();
 
        int id = e.getId();
        String nom = e.getNom();
        String stade = e.getStade();
        String score = e.getScore();
        String nomEquipeId = e.nomEquipeId.getNom();
     
        E=e;
        
        
        
        try {

            Parent page1
                    = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/ModifierMatchesBack.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageMatchesBackController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    @FXML
    private void trier(ActionEvent event) {
          ObservableList<Matches> list = affichageMatchesBackfx.getItems();
    Collections.sort(list, new Comparator<Matches>() {
        @Override
        public int compare(Matches e1, Matches e2) {
            return e1.getNom().compareToIgnoreCase(e2.getNom());
        }
    });
    affichageMatchesBackfx.setItems(list);
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
    
    


    
