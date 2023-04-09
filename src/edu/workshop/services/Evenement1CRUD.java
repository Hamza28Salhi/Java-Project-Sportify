/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;

import edu.worshop.interfaces.EvenementCRUD;
import edu.worshop.model.Evenement;

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
public class Evenement1CRUD implements EvenementCRUD{
    Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterEvenement(Evenement E) {
       try {
            String req = "INSERT INTO `evenement`( `date`, `type`, `lieu`, `description`, `even_pic`, `titre`) VALUES ('"+E.getDate()+"','"+E.getType()+"','"+E.getLieu()+"','"+E.getDescription()+"','"+E.getEven_pic()+"','"+E.getTitre()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Evènement ajouté!!!");
        } catch (SQLException ex) {
            System.out.println("Evènement non ajouté");
    
    
}
    }
    
    @Override
    public List<Evenement> afficherEvenement() {
       List<Evenement> list = new ArrayList<>();
        try {
            String req = "Select * from evenement";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Evenement E = new Evenement();
             E.setDate(RS.getDate(2));
             E.setId(RS.getInt(1));
             E.setType(RS.getString(3));
             E.setLieu(RS.getString(4));
             E.setDescription(RS.getString(5));
             E.setEven_pic(RS.getString(6));
             E.setTitre(RS.getString(7));
             list.add(E);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    @Override
    public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM `evenement` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     @Override
    public void modifierEvenement(Evenement E) {
        try {
            String req = "UPDATE `Evenement` SET `date` = '" + E.getDate()+ "', `type` = '" + E.getType()+ "', `lieu` = '" + E.getLieu()+ "', `description` = '" + E.getDescription()+ "', `even_pic` = '" + E.getEven_pic()+ "', `titre` = '" + E.getTitre()+ "' WHERE `evenement`.`id` = " + E.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Evenement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}



