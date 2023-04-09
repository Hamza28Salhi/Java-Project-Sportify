/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;

import java.sql.Date;

/**
 *
 * @author HOUYEM
 */
public class Evenement {
    
     private int id;
     private Date date;
    private String type,lieu,description,titre,even_pic;

    public Evenement(Date date, String type, String lieu, String description, String even_pic, String titre) {
        this.date = date;
        this.type = type;
        this.lieu = lieu;
        this.description = description;
        this.even_pic = even_pic;
        this.titre = titre;
    }

    public Evenement() {
       
    }

    public Evenement(int id, Date date, String type, String lieu, String description, String titre, String even_pic) {
        this.id = id;
        this.date = date;
        this.type = type;
        this.lieu = lieu;
        this.description = description;
        this.titre = titre;
        this.even_pic = even_pic;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEven_pic() {
        return even_pic;
    }

    public void setEven_pic(String even_pic) {
        this.even_pic = even_pic;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", date=" + date + ", type=" + type + ", lieu=" + lieu + ", description=" + description + ", titre=" + titre + ", even_pic=" + even_pic + '}';
    }
    
    
    
    
}


