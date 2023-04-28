/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
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
import java.sql.Date;
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
import javax.swing.filechooser.FileSystemView;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
                    userImage.setFitWidth(120);
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

        U = e;

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

    /*
    @FXML
    private void printUser(MouseEvent event) {
        try {
            // create new workbook
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("User Data");

            // create header row
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("Full Name");
            header.createCell(2).setCellValue("Email");
            header.createCell(3).setCellValue("Date of Birth");
            header.createCell(4).setCellValue("Address");
            header.createCell(5).setCellValue("Roles");

            // populate data rows
            ObservableList<User> users = User_Listfx.getItems();
            int rowIndex = 1;
            for (User user : users) {
                XSSFRow row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getFull_name());
                row.createCell(2).setCellValue(user.getEmail());
                // create a date formatter
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

// format the date of birth
                String formattedDate = dateFormatter.format(user.getDate_naissance());
                row.createCell(3).setCellValue(formattedDate);
                row.createCell(4).setCellValue(user.getAddress());
                row.createCell(5).setCellValue(String.join(",", user.getRoles()));
            }

            // save workbook to desktop
            Path desktop = FileSystemView.getFileSystemView().getHomeDirectory().toPath().resolve("Desktop");
            Path filePath = desktop.resolve("user_data.xlsx");
            try (FileOutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                workbook.write(outputStream);
            }

            // show success message
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Export Successful");
            alert.setHeaderText(null);
            alert.setContentText("User data has been exported to:\n" + filePath.toString());
            alert.showAndWait();

        } catch (IOException ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Export Failed");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while exporting user data:\n" + ex.getMessage());
            alert.showAndWait();
        }
    }
     */
    @FXML
    private void pdf(ActionEvent event) {
        Document document = new Document(PageSize.A4);
        Color headerColor = Color.web("#0692a1");
        Color cellColor = Color.web("#ff7a4a");
        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/VTFRedzone-Classic.ttf"), 12);

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

// Loop through the items in the ListView
        for (User user : User_Listfx.getItems()) {
            for (String role : user.getRoles()) {
                if (roleFrequency.containsKey(role)) {
                    roleFrequency.put(role, roleFrequency.get(role) + 1);
                } else {
                    roleFrequency.put(role, 1);
                }
            }
        }

// Create a PieChart data set
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (String role : roleFrequency.keySet()) {
            int frequency = roleFrequency.get(role);
            double percentage = (double) frequency / User_Listfx.getItems().size() * 100;
            String percentageText = String.format("%.2f%%", percentage);
            PieChart.Data slice = new PieChart.Data(role + " " + percentageText, frequency);
            pieChartData.add(slice);
        }

// Create a PieChart with the data set
        PieChart chart = new PieChart(pieChartData);

// Show percentage values in the chart's tooltip
        for (final PieChart.Data data : chart.getData()) {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(String.format("%.2f%%", (data.getPieValue() / User_Listfx.getItems().size() * 200)));
            Tooltip.install(data.getNode(), tooltip);
        }

// Show the chart in a new window
        Scene scene = new Scene(chart);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
