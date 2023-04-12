/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String address;
    private String password;
    private String full_name;
    private List<String> roles;

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String email) {
        this.email = email;
    }

    public User(int id, String email, String address, String password, String full_name, List<String> roles) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.roles = roles;
    }

    public User(String email, String address, String password, String full_name, List<String> roles) {
        this.email = email;
        this.address = address;
        this.password = password;
        this.full_name = full_name;
        this.roles = roles;
    }

    public User(String email, String address, String full_name, List<String> roles) {
        this.email = email;
        this.address = address;
        this.full_name = full_name;
        this.roles = roles;
    }

    public User(String email, String address, String password, String full_name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_Name) {
        this.full_name = full_name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", full_name='" + full_name + '\'' +
                ", roles=" + roles +
                '}';
    }
}
