package com.example.project.model;

public class Pokemon {
    private  int idPokemon;
    private String Name;
    private  String Element;
    private int ImagePokemon;

    public Pokemon(int idPokemon, String name, String element, int imagePokemon) {
        this.idPokemon = idPokemon;
        Name = name;
        Element = element;
        ImagePokemon = imagePokemon;
    }

    public int getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(int idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getElement() {
        return Element;
    }

    public void setElement(String element) {
        Element = element;
    }

    public int getImagePokemon() {
        return ImagePokemon;
    }

    public void setImagePokemon(int imagePokemon) {
        ImagePokemon = imagePokemon;
    }
}
