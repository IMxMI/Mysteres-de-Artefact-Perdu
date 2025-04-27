package org.jeu;

public class Item {
    private String nom;
    private String description;

    public Item(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}

