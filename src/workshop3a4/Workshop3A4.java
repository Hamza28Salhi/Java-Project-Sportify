/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a4;

import edu.workshop.services.Evenement1CRUD;
import edu.workshop.services.Equipe1CRUD;
import edu.workshop.services.Matches1CRUD;
import edu.workshop.services.Reservation1CRUD;
import edu.worshop.model.Evenement;
import edu.worshop.model.Equipe;
import edu.worshop.model.Matches;

import edu.worshop.model.Reservation;
import java.sql.Date;

/**
 *
 * @author belkn
 */
public class Workshop3A4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       ///MyConnection conn = MyConnection.getInstance();
        //Evenement E1 = new Evenement(Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaaaaaa", " ", "sportify" );
         //Reservation R1 = new Reservation(16, "par virement", "houyem", "kaaniche", "houyem@gmail.com", "12345678" );
       // Evenement1CRUD event = new Evenement1CRUD();
       // Reservation1CRUD res = new Reservation1CRUD();
        Equipe1CRUD eq = new Equipe1CRUD();
        
       // Equipe E1 = new Equipe("hamza", "conférence",Integer.parseInt("2023"), "aaaaaaa", "bonga", "sportify.png" );

        //eq.ajouterEquipe(E1);
        //eq.supprimerEquipe(64);
        //System.out.println( eq.afficherEquipe());
        //eq.modifierEquipe(new Equipe(63,"hamzaa", "conférence",Integer.parseInt("2023"), "aaaaaaa", "bonga", "sportify.png" ));

        Matches1CRUD ma = new Matches1CRUD();
        
        Matches M1 = new Matches("HamzaAAA", "conférence",Date.valueOf("2027-10-10"),"confé",28 );

        //ma.ajouterMatches(M1);
        ma.modifierMatches(new Matches(61, "Egon HamzaAAA", "houyem", Date.valueOf("2027-10-10"), "houyem@gmail.com",30 ));

        //System.out.println( ma.afficherMatches());
        //ma.supprimerMatches(43);


        

        
        //event.ajouterEvenement(E1);
        //res.ajouterReservation(R1);
        //per.ajouterPersonne2(p1);
        //System.out.println( event.afficherEvenement());
        //System.out.println( res.afficherReservation());
        //event.supprimerEvenement(19);
       // res.supprimerReservation(26);
        //event.modifierEvenement(new Evenement(17,Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaabbbbbaaaa", " ", "sportify" ));
        //res.modifierReservation(new Reservation(30,16, "par espèces", "houyem", "kaaniche", "houyem@gmail.com", "12345678" ));
          
       
        
    }
    
}
