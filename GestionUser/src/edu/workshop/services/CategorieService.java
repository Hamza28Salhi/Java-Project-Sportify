/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;

import edu.worshop.model.CategorieAbionnement;
import edu.worshop.utils.MyConnection;
import java.sql.Connection;
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
public class CategorieService {
    
    private final Connection cnx;

    private static CategorieService instance;
    
        public CategorieService() {
        cnx = MyConnection.getInstance().getConn();
    }
    
    public static CategorieService getInstance()
    {
        if (instance == null) {
            instance = new CategorieService();
        }
        return instance; 
    }
    
   public void addCategorie(CategorieAbionnement q)throws SQLDataException, SQLException{
        
         
         
         String query ="INSERT INTO `categories`(`nom`) VALUES (?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getDescription());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierCategorie(CategorieAbionnement e) throws SQLDataException {

               
                String query = "UPDATE `categories` SET `nom`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,e.getDescription());
                st.setInt(2,e.getId_categori());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<CategorieAbionnement> getAllCategories() throws SQLDataException {

        
        List<CategorieAbionnement> list =new ArrayList<CategorieAbionnement>();
        int count =0;
        
        String requete="select * from categories";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                CategorieAbionnement e = new CategorieAbionnement();
                e.setId_categori(rs.getInt("id"));
                e.setDescription(rs.getString("nom"));
                
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
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
       public CategorieAbionnement get_CatById(int i) {
        CategorieAbionnement e = new CategorieAbionnement();
        int nombre = 0;
        String requete = "select * from categories where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId_categori(rs.getInt("id"));
                e.setDescription(rs.getString("nom"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
       
     public CategorieAbionnement getCategorieByDescription(String i) {
        CategorieAbionnement e = new CategorieAbionnement();
        int nombre = 0;
        String requete =  "SELECT * FROM `categories` WHERE nom =\""+i+"\"";
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {


                e.setId_categori(rs.getInt("id"));
                e.setDescription(rs.getString("nom"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
    
    
    

    public boolean deleteCategori(int idCat) throws SQLDataException {

        
        
        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM categories WHERE id="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategorieService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }


}