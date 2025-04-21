package org.jeu;

import java.util.ArrayList;
import java.util.List;

public class Sac {
    private final List<Item> items;
    private static final int CAPACITE_MAX = 5;

    public Sac() {
        items = new ArrayList<>();
    }


    public boolean ajouterItem(Item item) {
        if (items.size() >= CAPACITE_MAX) {
            System.out.println("Le sac est plein ! \n Vous ne pouvez pas porter plus de " + CAPACITE_MAX + " objets.");
            return false;
        }
        else {
            return items.add(item);
        }
    }

    public boolean enleverItem(Item item) {
        return items.remove(item);
    }

    public boolean contient(Item item) {

        return items.contains(item);
    }

    public List<Item> getItems() {

        return items;
    }

    public void vider() {
        items.clear();
     }

    public List<String> listeNoms() {

        return items.stream().map(Item::getNom).toList();
    }

    // Sac.java
    List<String> nomsItems() {
        return items.stream().map(Item::getNom).toList();
    }


    public boolean possedeItem(String condition) {
        return items.stream().anyMatch(item -> item.getNom().equals(condition));
    }
}
