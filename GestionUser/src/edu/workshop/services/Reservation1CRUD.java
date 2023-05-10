/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;
import edu.worshop.interfaces.ReservationCRUD;


import edu.worshop.model.Reservation;

import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HOUYEM
 */
public class Reservation1CRUD implements ReservationCRUD{
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterReservation(Reservation R) {
       try {
            String req = "INSERT INTO `reservation`( `evenement_id`, `paiement`, `nom`, `prenom`, `adresse`, `telephone`) VALUES ('"+R.getEvenement_id()+"', '"+R.getPaiement()+"', '"+R.getNom()+"', '"+R.getPrenom()+"', '"+R.getAdresse()+"', '"+R.getTelephone()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reservation ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Reservation non ajoutée");
    
    
}
    }
    
      @Override
    public List<Reservation> afficherReservation() {
       List<Reservation> list = new ArrayList<>();
        try {
            String req = "Select * from reservation";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Reservation R = new Reservation();
             R.setId(RS.getInt(1));
             R.setEvenement_id(RS.getInt(2));
             
             R.setNom(RS.getString(3));
             R.setPrenom(RS.getString(4));
             R.setAdresse(RS.getString(5));
             R.setTelephone(RS.getString(6));
             R.setPaiement(RS.getString(7));
             list.add(R);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerReservation(int id) {
        try {
            String req = "DELETE FROM `reservation` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   @Override
    public void modifierReservation(Reservation R) {
        try {
            
            String req = "UPDATE `Reservation` SET `evenement_id` = '"+R.getEvenement_id()+"', `paiement` = '" + R.getPaiement()+ "', `nom` = '" + R.getNom()+ "', `prenom` = '" + R.getPrenom()+ "', `adresse` = '" + R.getAdresse()+ "', `telephone` = '" + R.getTelephone()+ "' WHERE `reservation`.`id` = " + R.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Reservation updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
