/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spotify.entities;

/**
 *

 */
public class Abonnement {
    
    
    private int prix , id ,id_cate ;
    
    private String nom,image ,descirption ;

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescirption() {
        return descirption;
    }

    public void setDescirption(String descirption) {
        this.descirption = descirption;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "prix=" + prix + ", id=" + id + ", id_cate=" + id_cate + ", nom=" + nom + ", image=" + image + ", descirption=" + descirption + '}';
    }
    
    
    
    
}
