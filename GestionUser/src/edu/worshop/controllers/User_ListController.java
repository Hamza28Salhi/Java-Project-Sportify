/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import edu.workshop.services.ServiceUser;
import edu.worshop.interfaces.IService;
import edu.worshop.model.User;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author azizo
 */
public class User_ListController implements Initializable {

    @FXML
    private ListView<User> User_Listfx;
    ServiceUser sp = new ServiceUser();
    @FXML
    private TextField searchField;
    @FXML
    private Label label;

    int index = -1;

    // Declare an ObservableList to store the users
    private ObservableList<User> userList;

    static int id;
    static Date date;
    static String full_name, email, address, password, img_user;
    static User U = new User();
    @FXML
    private Button stat;
    @FXML
    private Button addUser;
    @FXML
    private Button deleteUser;
    @FXML
    private Button updateUser;
    @FXML
    private Button pdf;
     @FXML
    private Button logout;
    @FXML
    private Button pdf1;

    /**
     * Initializes the controller class.
     */
     public void initialize(URL url, ResourceBundle rb) {
         ListView<User> list1 = User_Listfx;
        ServiceUser inter = new ServiceUser();
        List<User> list2 = inter.ListUsers();
        userList = FXCollections.observableArrayList(list2);

        list1.setCellFactory(param -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    HBox card = new HBox();
                    card.setAlignment(Pos.CENTER_LEFT);
                    card.setSpacing(15);

                    ImageView userImage = new ImageView();
                    userImage.setFitHeight(90);
                    userImage.setFitWidth(130);
                    File file = new File(System.getProperty("user.dir") + "/upload/" + item.getImg_user());

                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        userImage.setImage(image);
                    } else {
                        Image defaultImage = new Image("default.png");
                        userImage.setImage(defaultImage);
                    }

                    if (file.exists()) {
                        Image image = new Image(file.toURI().toString());
                        userImage.setImage(image);
                    }

                    VBox userInfo = new VBox();
                    userInfo.setAlignment(Pos.CENTER_LEFT);
                    userInfo.setSpacing(5);

                    Label fullNameLabel = new Label(item.getFull_name());
                    fullNameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

                    Label emailLabel = new Label("Email:   " +item.getEmail());
                    emailLabel.setFont(Font.font("Arial", 14));

                    Label addressLabel = new Label("Address:   " +item.getAddress());
                    addressLabel.setFont(Font.font("Arial", 14));

                    Label birthDateLabel = new Label("Birth Date:   " + item.getDate_naissance().toString());
                    birthDateLabel.setFont(Font.font("Arial", 14));
                    
                    Label rolesLabel = new Label("Roles:   " + String.join(", ", item.getRoles()));
                    rolesLabel.setFont(Font.font("Arial", 14));

                    userInfo.getChildren().addAll(fullNameLabel, emailLabel, addressLabel,birthDateLabel,rolesLabel);

                    card.getChildren().addAll(userImage, userInfo);
                    setGraphic(card);

                }
            }
        });

        // Add search functionality
        FilteredList<User> filteredList = new FilteredList<>(userList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(user -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (user.getFull_name().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by full name
                } else if (user.getEmail().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by email address
                } else if (String.valueOf(user.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by ID
                } else if (user.getDate_naissance().toString().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by birth date
                } else if (user.getAddress().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Search by address
                }

                return false; // No matches found
            });
        });

        SortedList<User> sortedList = new SortedList<>(filteredList);
        list1.setItems(sortedList);

        
 

        
// Load custom font
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 50);
        Font buttonfont = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 16);
        label.setFont(font);
        stat.setFont(buttonfont);
        addUser.setFont(buttonfont);
        deleteUser.setFont(buttonfont);
        updateUser.setFont(buttonfont);
        pdf.setFont(buttonfont);
        logout.setFont(buttonfont);
        pdf1.setFont(buttonfont);
 
    }

    @FXML
    private void deleteUser(ActionEvent event) {
        ListView<User> list = (ListView<User>) User_Listfx;
        ServiceUser inter = new ServiceUser();

        int selectedIndex = list.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            User E = list.getSelectionModel().getSelectedItem();
            inter.supprimer(E);
            list.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "plz select a user to delete", ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    private void updateUser(ActionEvent event) {

        ListView<User> list = (ListView<User>) User_Listfx;
        int selectedIndex = list.getSelectionModel().getSelectedIndex();

        User e = list.getSelectionModel().getSelectedItem();

        int id = e.getId();
        String name = e.getFull_name();
        String email = e.getEmail();
        String address = e.getAddress();
        String password = e.getPassword();
        String img_user = e.getImg_user();
        Date date = e.getDate_naissance();
        List<String> roles = e.getRoles();

        U = e;
        U.setRoles(roles);
        
        

        try {

            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/User_Update.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_UpdateController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    @FXML
    private void addUser(ActionEvent event) {
        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("/edu/worshop/gui/User_Add.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(User_AddController.class.getName()).log(Level.SEVERE, null, ex);
            //showAlert("Error loading");
        }

    }

    


@FXML
private void pdf(ActionEvent event) {
    Document document = new Document(PageSize.A4);
    Color headerColor = Color.web("#0692a1");
    Color cellColor = Color.web("#ff7a4a");
    //Font font = FontFactory.getFont("/fonts/VTFRedzone-Classic.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);

    try {
        PdfWriter.getInstance(document, new FileOutputStream("user.pdf"));

        document.open();

        Paragraph paragraph = new Paragraph("Détails de l'utilisateur");
        paragraph.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph);

        document.add(Chunk.NEWLINE);

        PdfPTable pdfTable = new PdfPTable(2);

        ObservableList<User> selectedUsers = User_Listfx.getSelectionModel().getSelectedItems();

        pdfTable.addCell("Nom du champ");
        pdfTable.addCell("Valeur");

        for (User user : selectedUsers) {
            pdfTable.addCell("ID");
            pdfTable.addCell(String.valueOf(user.getId()));

            pdfTable.addCell("Nom complet");
            pdfTable.addCell(user.getFull_name());

            pdfTable.addCell("Adresse email");
            pdfTable.addCell(user.getEmail());

            pdfTable.addCell("Adresse");
            pdfTable.addCell(user.getAddress());

            pdfTable.addCell("Mot de passe");
            pdfTable.addCell(user.getPassword());

            pdfTable.addCell("Date de naissance");
            pdfTable.addCell(String.valueOf(user.getDate_naissance()));

            // Add image to the PDF
            String imageName = user.getImg_user();
            String imagePath = "upload/" + imageName; // Replace "upload/" with the actual path of the upload folder
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(imagePath);
            image.scaleToFit(200, 200); // Resize the image
            image.setAlignment(Element.ALIGN_CENTER); // Center the image
            document.add(image);

            pdfTable.completeRow(); // Add a new row for each selected item
        }

        document.add(pdfTable);

        document.close();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Export PDF");
        alert.setHeaderText(null);
        alert.setContentText("Le fichier PDF a été généré avec succès !");
        alert.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
    }
}




    @FXML
void stat(ActionEvent event) {

    // Create a map to store the frequency of each role
    Map<String, Integer> roleFrequency = new HashMap<>();

    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sportify", "root", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT roles FROM user");
        while (rs.next()) {
            String role = rs.getString("roles");
            if (roleFrequency.containsKey(role)) {
                roleFrequency.put(role, roleFrequency.get(role) + 1);
            } else {
                roleFrequency.put(role, 1);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Create a PieChart data set
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (String role : roleFrequency.keySet()) {
        int frequency = roleFrequency.get(role);
        double percentage = (double) frequency / roleFrequency.size() * 100;
        String percentageText = String.format("%.2f%%", percentage);
        PieChart.Data slice = new PieChart.Data(role + " " + percentageText, frequency);
        pieChartData.add(slice);
    }

    // Create a PieChart with the data set
    PieChart chart = new PieChart(pieChartData);

    // Show percentage values in the chart's tooltip
    for (final PieChart.Data data : chart.getData()) {
        Tooltip tooltip = new Tooltip();
        tooltip.setText(String.format("%.2f%%", (data.getPieValue() / roleFrequency.size() * 200)));
        Tooltip.install(data.getNode(), tooltip);
    }

    // Show the chart in a new window
    Scene scene = new Scene(chart);
    Stage stage = new Stage();
    stage.setScene(scene);
    stage.show();
}
@FXML
    private void logout(ActionEvent event) {
         try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/worshop/gui/Front_Login.fxml"));
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