/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;



/**
 *
 * @author HOUYEM
 */
public class Reservation {
    private int id, evenement_id;
    private String paiement,nom,prenom,adresse,telephone;
    //Evenement e;

    public Reservation(int evenement_id, String paiement, String nom, String prenom, String adresse, String telephone) {
        this.evenement_id = evenement_id;
        this.paiement = paiement;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        
    }

    public Reservation(int id, int evenement_id, String paiement, String nom, String prenom, String adresse, String telephone) {
        this.id = id;
        this.evenement_id = evenement_id;
        this.paiement = paiement;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
       
    }

    

   

    
    
    
    public Reservation() {
       
    }

    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEvenement_id() {
        return evenement_id;
    }

    public void setEvenement_id(int evenement_id) {
        this.evenement_id = evenement_id;
    }

    
       

    public String getPaiement() {
        return paiement;
    }

    public void setPaiement(String paiement) {
        this.paiement = paiement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Reservation{ " + "evenement_id=" + evenement_id + ", paiement=" + paiement + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", telephone=" + telephone +  '}';
    }

    

    

        
     
}
