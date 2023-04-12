/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package workshop3a4;

import edu.workshop.services.Post1CRUD;
import edu.worshop.model.Post;
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
        
       /// MyConnection conn = MyConnection.getInstance();
        //Evenement E1 = new Evenement(Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaaaaaa", " ", "sportify" );
         //Post P1 = new Post("jtitre", "jcontenu", "jimage", "jauteur",Date.valueOf("2023-1-1"));
         Post P1 = new Post("jtitre", "jcontenu", "jimage", "jauteur");       
// Evenement1CRUD event = new Evenement1CRUD();
        Post1CRUD pst = new Post1CRUD();
        
        System.out.println(P1);
        //event.ajouterEvenement(E1);
        //res.ajouterReservation(R1);
        pst.ajouterPost(P1);
        //pst.supprimerPost(7);
        //( event.afficherEvenement());
        //System.out.println( res.afficherReservation());
        //event.supprimerEvenement(19);
       // res.supprimerReservation(26);
        //event.modifierEvenement(new Evenement(17,Date.valueOf("2023-1-1"), "conférence", "Tunis", "aaabbbbbaaaa", " ", "sportify" ));
        //res.modifierReservation(new Reservation(30,16, "par espèces", "houyem", "kaaniche", "houyem@gmail.com", "12345678" ));
          
       
        
    }
    
}
