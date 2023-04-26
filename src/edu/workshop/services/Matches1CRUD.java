/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.workshop.services;
import edu.worshop.interfaces.MatchesCRUD;
import edu.worshop.model.Equipe;
import edu.worshop.model.Matches;
import edu.worshop.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
/**
 *
 * @author Ace River
 */
public class Matches1CRUD  implements MatchesCRUD{
    
   Statement ste;
    Connection conn = MyConnection.getInstance().getConn();
    
    
  public void ajouterMatches(Matches M) {
    try {
String req = "INSERT INTO `Matches`(`nom`, `stade`, `date`, `score`, `nom_equipe_id`) VALUES ('" + M.getNom() + "','" + M.getStade() + "','" + M.getDate() + "','" + M.getScore() + "','" + M.getNomEquipeId() + "')";

        Statement st = conn.createStatement();
        st.executeUpdate(req);
        System.out.println("Match ajouté avec succès !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}



public List<Matches> afficherMatches() {
    List<Matches> list = new ArrayList<>();
    try {
        String req = "SELECT m.id, m.nom, m.stade, m.date, m.score, m.nom_equipe_id, e.nom AS equipe_nom FROM matches m JOIN equipe e ON m.nom_equipe_id = e.id";
        Statement st = conn.createStatement();
        ResultSet RS = st.executeQuery(req);
        while(RS.next()){
            Equipe equipe = new Equipe(RS.getInt("nom_equipe_id"), RS.getString("equipe_nom"));
            Matches R = new Matches(RS.getInt("id"), RS.getString("nom"), RS.getString("stade"), RS.getDate("date"), RS.getString("score"), equipe);
            list.add(R);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return list;
}


        
          public void supprimerMatches(int id) {
        try {
            String req = "DELETE FROM `matches` WHERE id = " + id;
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Matches deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
          
           public void modifierMatches(Matches M) {
        try {
            
String req = "UPDATE `Matches` SET `nom`='" + M.getNom() + "', `stade`='" + M.getStade() + "', `date`='" + M.getDate() + "', `score`='" + M.getScore() + "', `nom_equipe_id`='" + M.getNomEquipeId() + "' WHERE `id`=" + M.getId();
            Statement st = conn.createStatement();
            st.executeUpdate(req);
            System.out.println("Matches updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    public ObservableMap<String, Object> getMatchStatistics() {
    ObservableMap<String, Object> stats = FXCollections.observableHashMap();
    try {
        // Get the total number of matches
        String totalMatchesQuery = "SELECT COUNT(*) FROM matches";
        PreparedStatement stmt1 = conn.prepareStatement(totalMatchesQuery);
        ResultSet rs1 = stmt1.executeQuery();
        if (rs1.next()) {
            stats.put("totalMatches", rs1.getInt(1));
        }

        // Get the number of matches per team
        String teamMatchesQuery = "SELECT nom_equipe_id, COUNT(*) FROM matches GROUP BY nom_equipe_id";
        PreparedStatement stmt2 = conn.prepareStatement(teamMatchesQuery);
        ResultSet rs2 = stmt2.executeQuery();
        ObservableMap<String, Integer> teamMatchCounts = FXCollections.observableHashMap();
        while (rs2.next()) {
            teamMatchCounts.put(rs2.getString(1), rs2.getInt(2));
        }
        stats.put("teamMatches", teamMatchCounts);
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return stats;
}

    
    
    
}
