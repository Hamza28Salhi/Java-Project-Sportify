/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;

import edu.worshop.model.Abonnement;
import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 
 */
public class AbonnementService {
    
    private final Connection cnx;

    private static AbonnementService instance;
    
        public AbonnementService() {
        cnx = MyConnection.getInstance().getConn();
    }
    
    public static AbonnementService getInstance()
    {
        if (instance == null) {
            instance = new AbonnementService();
        }
        return instance; 
    }
    
   public void addAbonnement(Abonnement q)throws SQLDataException, SQLException{
        
         
 String query ="INSERT INTO `abonnement`( `nom`, `description`, `prix`,`image`,`id_categorie`) VALUES (?,?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getNom());
                st.setString(2,q.getDescirption());
                st.setInt(3,q.getPrix());
                st.setString(4,q.getImage());
                st.setInt(5, q.getId_cate());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                

    }

    public boolean ModifierAbonnement(Abonnement q) throws SQLDataException {

               
                String query = "UPDATE `abonnement` SET `nom`=?,`description`=?,`prix`=?,`image`=?,`id_categorie `=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,q.getNom());
                st.setString(2,q.getDescirption());
                st.setInt(3,q.getPrix());
                st.setString(4,q.getImage());
                st.setInt(5, q.getId_cate());
                st.setInt(6,q.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Abonnement> getAllAbonnement() throws SQLDataException {

        
        List<Abonnement> list =new ArrayList<Abonnement>();
        int count =0;
        
        String requete="select * from abonnement";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Abonnement e = new Abonnement();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDescirption(rs.getString("description"));
                e.setPrix(rs.getInt("prix"));
                e.setImage(rs.getString("image"));
                e.setId_cate(rs.getInt("id_categorie"));
                
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public Abonnement get_Abonnement(int id) {
        Abonnement e = new Abonnement();
        String requete = "select * from abonnement where id="+id;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setDescirption(rs.getString("description"));
                e.setPrix(rs.getInt("prix"));
                e.setImage(rs.getString("image"));
                e.setId_cate(rs.getInt("id_categorie"));
                

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }


    public boolean deleteAbonnement(int id) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM `abonnement` WHERE `id` ="+id;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AbonnementService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }


}