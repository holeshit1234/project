package com.example.project.model;

public class User {

    private  int idUser;
    private String Username;
    private  String Password;

    public User(int idUser, String username, String password) {
        this.idUser = idUser;
        Username = username;
        Password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
