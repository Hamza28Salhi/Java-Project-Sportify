/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ace River
 */
@Entity
@Table(name = "equipe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Equipe.findAll", query = "SELECT e FROM Equipe e")
    , @NamedQuery(name = "Equipe.findById", query = "SELECT e FROM Equipe e WHERE e.id = :id")
    , @NamedQuery(name = "Equipe.findByNom", query = "SELECT e FROM Equipe e WHERE e.nom = :nom")
    , @NamedQuery(name = "Equipe.findByJoueurs", query = "SELECT e FROM Equipe e WHERE e.joueurs = :joueurs")
    , @NamedQuery(name = "Equipe.findByClassement", query = "SELECT e FROM Equipe e WHERE e.classement = :classement")
    , @NamedQuery(name = "Equipe.findByEntraineur", query = "SELECT e FROM Equipe e WHERE e.entraineur = :entraineur")
    , @NamedQuery(name = "Equipe.findByCategorie", query = "SELECT e FROM Equipe e WHERE e.categorie = :categorie")
    , @NamedQuery(name = "Equipe.findByPicture", query = "SELECT e FROM Equipe e WHERE e.picture = :picture")})
public class Equipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
     Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Column(name = "joueurs")
    private String joueurs;
    @Basic(optional = false)
    @Column(name = "classement")
    private int classement;
    @Basic(optional = false)
    @Column(name = "entraineur")
    private String entraineur;
    @Basic(optional = false)
    @Column(name = "categorie")
    private String categorie;
    @Column(name = "picture")
    private String picture;
    @OneToMany(mappedBy = "nomEquipeId")
    private Collection<Matches> matchesCollection;

    public Equipe() {
    }

    public Equipe(Integer id) {
        this.id = id;
    }

    public Equipe(Integer id, String nom, String joueurs, int classement, String entraineur, String categorie,String Picture) {
        this.id = id;
        this.nom = nom;
        this.joueurs = joueurs;
        this.classement = classement;
        this.entraineur = entraineur;
        this.categorie = categorie;
        this.picture = picture;

    }
     public Equipe(Integer id, String nom, String joueurs, int classement, String entraineur, String categorie) {
        this.id = id;
        this.nom = nom;
        this.joueurs = joueurs;
        this.classement = classement;
        this.entraineur = entraineur;
        this.categorie = categorie;
       

    }
     public Equipe( String nom, String joueurs, int classement, String entraineur, String categorie,String picture) {
        this.nom = nom;
        this.joueurs = joueurs;
        this.classement = classement;
        this.entraineur = entraineur;
        this.categorie = categorie;
        this.picture = picture;

    }
        public Equipe( String nom, String joueurs, int classement, String entraineur, String categorie) {
        this.nom = nom;
        this.joueurs = joueurs;
        this.classement = classement;
        this.entraineur = entraineur;
        this.categorie = categorie;

    }
     public Equipe(int id, String nom) {
    this.id = id;
    this.nom = nom;
}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(String joueurs) {
        this.joueurs = joueurs;
    }

    public int getClassement() {
        return classement;
    }

    public void setClassement(int classement) {
        this.classement = classement;
    }

    public String getEntraineur() {
        return entraineur;
    }

    public void setEntraineur(String entraineur) {
        this.entraineur = entraineur;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Collection<Matches> getMatchesCollection() {
        return matchesCollection;
    }

    public void setMatchesCollection(Collection<Matches> matchesCollection) {
        this.matchesCollection = matchesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipe)) {
            return false;
        }
        Equipe other = (Equipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
  
    public String toString() {
        return "Equipe{" + "id=" + id + ", nom=" + nom + ", joueurs=" + joueurs + ", classement=" + classement + ", entraineur=" + entraineur + ", picture=" + picture+  '}';
    }
}
