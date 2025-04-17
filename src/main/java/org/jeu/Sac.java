package org.jeu;

import java.util.ArrayList;
import java.util.List;

public class Sac {
    private List<Item> items;

    public Sac() {
        items = new ArrayList<>();
    }

    public boolean ajouterItem(Item item) {

        return items.add(item);
    }

    public boolean prendreItem(Item item) {

        return items.remove(item);
    }

    public boolean contient(Item item) {

        return items.contains(item);
    }

    public List<Item> getItems() {

        return items;
    }
}
