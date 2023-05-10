/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.controllers;

import edu.workshop.services.Matches1CRUD;
import edu.worshop.model.Equipe;
import edu.worshop.model.Matches;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import com.toedter.calendar.JCalendar;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;



/**
 * FXML Controller class
 *
 * @author Ace River
 */
public class CalendarController implements Initializable {

    @FXML
    private GridPane calendarGridPane;
     @FXML private Label monthLabel;



     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
        

    
}}