/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.worshop.interfaces;
import edu.worshop.model.Matches;
import java.util.List;

/**
 *
 * @author Ace River
 */
public interface MatchesCRUD {
    
        public void ajouterMatches(Matches M);
    public List<Matches> afficherMatches();
    public void supprimerMatches(int id);
    public void modifierMatches(Matches M);
}
