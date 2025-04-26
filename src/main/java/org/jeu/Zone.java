package org.jeu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Zone {
    private String description;
    private String nomImage;
    private HashMap<String, Zone> sorties;
    private List<Item> items;

    public Zone(String description, String image) {
        this.description = description;
        this.nomImage = image;
        this.sorties = new HashMap<>();
        this.items = new ArrayList<>();
    }

    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return new ArrayList<>(items); // Return a copy to prevent external modification
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    public String nomImage() {
        return nomImage;
    }

    public String toString() {
        return description;
    }

    public String descriptionLongue() {
        StringBuilder sb = new StringBuilder("Vous êtes dans " + description + "\n");
        sb.append("Sorties : ").append(sorties()).append("\n");
        if (!items.isEmpty()) {
            sb.append("Objets : ").append(items.stream().map(Item::getNom).collect(Collectors.joining(", "))).append("\n");
        }
        return sb.toString();
    }

    private String sorties() {
        return sorties.isEmpty() ? "aucune" : String.join(", ", sorties.keySet()).toLowerCase();
    }

    public Zone obtientSortie(String direction) {
        return sorties.get(direction.toUpperCase());
    }
}