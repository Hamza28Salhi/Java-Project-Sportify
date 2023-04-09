/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;

import edu.worshop.interfaces.ProduitCRUD;


import edu.worshop.model.Produit;

import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author lenovo
 */
public class Produit1CRUD implements ProduitCRUD{
     Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    @Override
    public void ajouterProduit(Produit P) {
       try {
            String req = "INSERT INTO `produit`( `categorie_id`, `nom_produit`, `prix_produit`, `marque_produit`, `image`, `quantite`) VALUES ('"+P.getCategorie_id()+"', '"+P.getNom_produit()+"', '"+P.getPrix_produit()+"', '"+P.getMarque_produit()+"', '"+P.getImage()+"', '"+P.getQuantite()+"')";
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Produit ajoutée!!!");
        } catch (SQLException ex) {
            System.out.println("Produit non ajoutée");
    
    
}
}
     @Override
    public List<Produit> afficherProduit() {
       List<Produit> list = new ArrayList<>();
        try {
            String req = "Select * from produit";
            Statement st = conn.createStatement();
           
            ResultSet RS= st.executeQuery(req);
            while(RS.next()){
             Produit P = new Produit();
             P.setId(RS.getInt(1));
             P.setCategorie_id(RS.getInt(2));
             P.setNom_produit(RS.getString(3));
             P.setPrix_produit(RS.getDouble(4));
             P.setMarque_produit(RS.getString(5));
             P.setImage(RS.getString(6));
             P.setQuantite(RS.getInt(7));
             list.add(P);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

  @Override
    public void supprimerProduit(int id) {
        try {
            String req = "DELETE FROM `produit` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
  @Override
    public void modifierProduit(Produit P) {
        try {
            
            String req = "UPDATE `Produit` SET `categorie_id` = '"+P.getCategorie_id()+"', `nom_produit` = '" + P.getNom_produit()+ "', `prix_produit` = '" + P.getPrix_produit()+ "', `marque_produit` = '" + P.getMarque_produit()+ "', `image` = '" + P.getImage()+ "', `quantite` = '" + P.getQuantite()+ "' WHERE `produit`.`id` = " + P.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Produit updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}