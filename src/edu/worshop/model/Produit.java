/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;

/**
 *
 * @author lenovo
 */
public class Produit {
     private int id, categorie_id;
        private String nom_produit;
        
        private double prix_produit;
        private String marque_produit, image;
        private int quantite;
        
        

    public Produit(int categorie_id, String nom_produit, double prix_produit, String marque_produit, String image, int quantite) {
        this.categorie_id = categorie_id;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.marque_produit = marque_produit;
        this.image = image;
        this.quantite = quantite;
    }

  

    public Produit(int id, int categorie_id, String nom_produit, double prix_produit, String marque_produit, String image, int quantite) {
        this.id = id;
        this.categorie_id = categorie_id;
        this.nom_produit = nom_produit;
        this.prix_produit = prix_produit;
        this.marque_produit = marque_produit;
        this.image = image;
        this.quantite = quantite;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }

    public double getPrix_produit() {
        return prix_produit;
    }

    public void setPrix_produit(double prix_produit) {
        this.prix_produit = prix_produit;
    }

    public String getMarque_produit() {
        return marque_produit;
    }

    public void setMarque_produit(String marque_produit) {
        this.marque_produit = marque_produit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", categorie_id=" + categorie_id + ", nom_produit=" + nom_produit + ", prix_produit=" + prix_produit + ", marque_produit=" + marque_produit + ", image=" + image + ", quantite=" + quantite + '}';
    }
        
        
        
}
