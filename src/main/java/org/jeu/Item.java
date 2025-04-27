package org.jeu;

/**
 * Représente un objet (item) que le joueur peut posséder dans le jeu.
 * <p>
 * Un item est caractérisé par un nom et une description.
 * </p>
 */
public class Item {

    /** Nom de l'item */
    private String nom;

    /** Description de l'item */
    private String description;

    /**
     * Construit un nouvel item avec le nom et la description spécifiés.
     *
     * @param nom         Le nom de l'item
     * @param description La description de l'item
     */
    public Item(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Retourne le nom de l'item.
     * @return Le nom de l'item
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la description de l'item.
     * @return La description de l'item
     */
    public String getDescription() {
        return description;
    }
}
