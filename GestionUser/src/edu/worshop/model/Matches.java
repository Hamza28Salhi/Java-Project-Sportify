/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ace River
 */
@Entity
@Table(name = "matches")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matches.findAll", query = "SELECT m FROM Matches m")
    , @NamedQuery(name = "Matches.findById", query = "SELECT m FROM Matches m WHERE m.id = :id")
    , @NamedQuery(name = "Matches.findByNom", query = "SELECT m FROM Matches m WHERE m.nom = :nom")
    , @NamedQuery(name = "Matches.findByStade", query = "SELECT m FROM Matches m WHERE m.stade = :stade")
    , @NamedQuery(name = "Matches.findByDate", query = "SELECT m FROM Matches m WHERE m.date = :date")
    , @NamedQuery(name = "Matches.findByScore", query = "SELECT m FROM Matches m WHERE m.score = :score")
    , @NamedQuery(name = "Matches.findByVideo", query = "SELECT m FROM Matches m WHERE m.video = :video")
    , @NamedQuery(name = "Matches.findByLatitude", query = "SELECT m FROM Matches m WHERE m.latitude = :latitude")
    , @NamedQuery(name = "Matches.findByLongitude", query = "SELECT m FROM Matches m WHERE m.longitude = :longitude")})
    
public class Matches implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "stade")
    private String stade;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "score")
    private String score;
    @Column(name = "video")
    private String video;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @JoinColumn(name = "nom_equipe_id", referencedColumnName = "id")
    @ManyToOne
    public Equipe nomEquipeId;

    public Matches() {
    }

    public Matches(Integer id) {
        this.id = id;
    }
    

    public Matches(Integer id, String nom, String stade, Date date,String score) {
        this.id = id;
        this.nom = nom;
        this.stade = stade;
        this.date = date;
        this.score = score;
        this.nomEquipeId = nomEquipeId;
    
    }
     public Matches( String nom, String stade, Date date,String score) {
        this.nom = nom;
        this.stade = stade;
        this.date = date;
        this.score = score;
     
    
    }
     public Matches(Integer id, String nom, String stade, Date date, String score, Equipe nomEquipeId) {
    this.id = id;
    this.nom = nom;
    this.stade = stade;
    this.date = date;
    this.score = score;
    this.nomEquipeId = nomEquipeId;
}

     public Matches( String nom, String stade, Date date, String score, Integer nomEquipeId) {
    
    this.nom = nom;
    this.stade = stade;
    this.date = date;
    this.score = score;
    this.nomEquipeId = new Equipe(nomEquipeId, null);
}
          public Matches(Integer id, String nom, String stade, Date date, String score, Integer nomEquipeId) {
        this.id = id;
    this.nom = nom;
    this.stade = stade;
    this.date = date;
    this.score = score;
    this.nomEquipeId = new Equipe(nomEquipeId, null);
}
          
                    public Matches( String nom, String stade, Date date, String score, Equipe nomEquipeId) {
   
    this.nom = nom;
    this.stade = stade;
    this.date = date;
    this.score = score;
    this.nomEquipeId = nomEquipeId;
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

    public String getStade() {
        return stade;
    }

    public void setStade(String stade) {
        this.stade = stade;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

 
    
    public int getNomEquipeId() {
    return nomEquipeId.getId();
}

    public void setNomEquipeId(Equipe nomEquipeId) {
        this.nomEquipeId = nomEquipeId;
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
        if (!(object instanceof Matches)) {
            return false;
        }
        Matches other = (Matches) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

   /* @Override
  
      public String toString() {
        return "Matches{ "+ ", nom=" + nom + ", stade=" + stade + ", date=" + date + ", score=" + score +  ", Nom Equipe=" + (nomEquipeId != null ? nomEquipeId.getNom() : null) +
            '}';
        
        
    }*/

    @Override
    public String toString() {
        return "Matches{" + "nom=" + nom + ", stade=" + stade + ", date=" + date + ", score=" + score + ", Nom Equipe=" + (nomEquipeId != null ? nomEquipeId.getNom() : null)  + '}';
    }

    
}
