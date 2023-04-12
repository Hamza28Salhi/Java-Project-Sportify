/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.util.List;
import Entities.User;

/**
 *
 * @author azizo
 * @param <T>
 */
public interface IService<T> {
     
    public void add(T p);
    public List<T> afficher();
    public void supprimer(T p);
    public boolean modifier(T p);
    public void update(User E);
    public User authenticate(String email, String password);
        

    
}
