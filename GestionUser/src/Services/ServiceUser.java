/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import Utils.Sportify;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Utils.Sportify;

/**
 *
 * @author azizo
 */
public class ServiceUser implements IService {

    Connection cnx;
    Statement stm;

    public ServiceUser() {
        cnx = Sportify.getInstance().getCnx();

    }

    @Override
    public void add(Object p) {
        try {
            if (p instanceof User) { // Vérifie si p est un objet de type User
                //String qry = "INSERT INTO user(id, email , password, address, full_name ) VALUES ('" + ((User) p).getId() + "','" + ((User) p).getEmail() + "','" + ((User) p).getPassword() + "' ,'" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "')"; 
                String qry = "INSERT INTO user(email , password, address, full_name ) VALUES ('" +  ((User) p).getEmail() + "','" + ((User) p).getPassword() + "' ,'" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "')"; 
                 //String qry = "INSERT INTO user(email, password, address, full_name) VALUES ('" + ((User) p).getEmail() + "','" + ((User) p).getPassword() + "','" + ((User) p).getAddress() + "','" + ((User) p).getFull_name() + "')";
                stm = cnx.createStatement();
                stm.executeUpdate(qry);
            } else {
                System.out.println("L'objet passé en paramètre n'est pas un utilisateur.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
        List<User> User = new ArrayList();

        try {
            //  String qry = "SELECT id, email, address, password, full_name  FROM user";
            String qry = "SELECT id, full_name, email,address  FROM user";
            stm = cnx.createStatement();
            PreparedStatement ps = cnx.prepareCall(qry);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                User c = new User();
                c.setId(rs.getInt(1));
                // c.setId(rs.getInt(1));
                c.setFull_name(rs.getString(2));
                c.setEmail(rs.getString(3));
               // c.getPassword(rs.getString(3));
                c.setAddress(rs.getString(4));
                
                User.add(c);
            }
            return User;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return User;
    }

    @Override
    public void supprimer(Object p) {
        try {
            if (p instanceof User) {
                String qry = "DELETE FROM user WHERE email='" + ((User) p).getEmail() + "'";
                stm = cnx.createStatement();
                stm.executeUpdate(qry);
            } else {
                System.out.println("L'objet passé en paramètre n'est pas un utilisateur.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public boolean modifier(Object E) {
        try {
            String qry = "UPDATE user SET email = '" + ((User) E).getEmail() + "', password = '" + ((User) E).getPassword() + "', address = '" + ((User) E).getAddress() + "', full_name = '" + ((User) E).getFull_name() + "' WHERE id = " + ((User) E).getId();
           //String qry = "UPDATE user SET  address = '" + ((User) E).getAddress() + "', full_name = '" + ((User) E).getFull_name() + "' WHERE email = '" + ((User) E).getEmail() + "'";

           stm = cnx.createStatement();
            stm.executeUpdate(qry);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
        @Override
    public void update(User user) {
    try {
        String qry = "UPDATE user SET email = '" + user.getEmail() + "', password = '" + user.getPassword() + "', address = '" + user.getAddress() + "', full_name = '" + user.getFull_name() + "' WHERE id = " + user.getId();
        Statement st = cnx.createStatement();
        st.executeUpdate(qry);
        System.out.println("User updated !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    @Override
    public User authenticate(String email, String password) {
    try {
        String qry = "SELECT * FROM user WHERE email = ? AND password = ?";
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setAddress(rs.getString("address"));
            user.setFull_name(rs.getString("full_name"));
            return user;
        } else {
            return null;
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        return null;
    }
}
    
    }

    
    

