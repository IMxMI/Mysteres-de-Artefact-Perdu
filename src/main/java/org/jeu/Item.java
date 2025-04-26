package org.jeu;

/**
 * Represents an item in the game "Mystères de l'Artefact Perdu."
 * Each item has a name and description, and is found and used in specific zones.
 */
public class Item {
    private final String nom;
    private final String description;

    /**
     * Constructs an Item with a name and description.
     *
     * @param nom        The name of the item (e.g., "Torche").
     * @param description A brief description of the item's purpose or effect.
     */
    public Item(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Gets the name of the item.
     *
     * @return The item's name.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Gets the description of the item.
     *
     * @return The item's description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the item, typically its name.
     *
     * @return The item's name.
     */
    @Override
    public String toString() {
        return nom;
    }

    /**
     * Checks if two items are equal based on their name.
     *
     * @param obj The object to compare with.
     * @return True if the items have the same name, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Item other = (Item) obj;
        return nom.equals(other.nom);
    }

    /**
     * Génère un code de hachage basé sur le nom de l'objet.
     *
     * @return The hash code.
     */
    @Override
    public int hashCode() {
        return nom.hashCode();
    }
}