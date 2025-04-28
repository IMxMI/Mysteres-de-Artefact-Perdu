package org.jeu;

import java.util.ArrayList;
import java.util.List;
/**
 * Classe représentant un sac d'inventaire dans le jeu.
 * Cette classe gère les objets que le joueur peut porter, avec une capacité maximale de 5 objets.
 */
public class Sac {
    private final List<Item> items;
    private static final int CAPACITE_MAX = 5;

    public Sac() {
        items = new ArrayList<>();
    }


    /**
     * Ajoute un item au sac.
     * @param item
     * @return
     */
    public boolean ajouterItem(Item item) {
        if (items.size() >= CAPACITE_MAX) {
            System.out.println("Le sac est plein ! \n Vous ne pouvez pas porter plus de " + CAPACITE_MAX + " objets.");
            return false;
        }
        else {
            return items.add(item);
        }
    }

    /**
     * Enlève un item du sac.
     * @param item
     * @return
     */
    public boolean enleverItem(String nomItem) {
        return items.removeIf(item -> item.getNom().equals(nomItem));
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
