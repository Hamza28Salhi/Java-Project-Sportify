/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionuser;

import Entities.User;
import Services.ServiceUser;
import java.util.List;

/**
 *
 * @author azizo
 */
public class GestionUser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        User p = new User();
        ServiceUser sp = new ServiceUser();
        //User s = new User("yacine2.khouini@gmail.com", "adress", "pass", "yacine khouini");
       User s = new User("aziz.mans@gmail.com","address","password", "mohamed aziz mansour","image");
       sp.add(s);
       sp.afficher();
      /* ServiceUser userService = new ServiceUser();
            
            // Test the authenticate method
            User user = userService.authenticate("ahmed@gmail.com", "aaaaaaaa");
            if (user != null) {
                System.out.println("User authenticated successfully: " + user.getEmail() + user.getAddress() + user.getFull_name() + user.getRoles());
            } else {
                System.out.println("Invalid email or password.");
            }  */
       
        //sp.modifier(new User( "aziz.mansour@gmail.com", "adress", "pass", "yacine khouini"));
        //sp.supprimer(new User("yacine.@gmail.com"));
    }

}
