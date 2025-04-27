package org.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un compte joueur dans le jeu.
 * <p>
 * Cette classe contient les informations d'identification du joueur,
 * sa position actuelle, son inventaire et les fragments collectés.
 * </p>
 */
public class Compte {

    /** Nom d'utilisateur associé au compte */
    private String username;

    /** Mot de passe du compte */
    private String password;

    /** Nom de la zone actuelle où se trouve le joueur */
    private String zoneCourante;

    /** Liste des noms d'objets présents dans l'inventaire du joueur */
    private List<String> inventaire;

    /** Liste des noms des fragments collectés par le joueur */
    private List<String> fragments;

    /**
     * Constructeur par défaut (nécessaire pour la désérialisation).
     */
    public Compte() {

    }

    /**
     * Construit un nouveau compte avec le nom d'utilisateur, le mot de passe
     * et la zone de départ spécifiés. Initialise l'inventaire et les fragments à vide.
     *
     * @param username    Le nom d'utilisateur
     * @param password    Le mot de passe
     * @param zoneDepart  La zone de départ du joueur
     */
    public Compte(String username, String password, String zoneDepart) {
        this.username    = username;
        this.password    = password;
        this.zoneCourante = zoneDepart;
        this.inventaire  = new ArrayList<>();
        this.fragments    = new ArrayList<>();
    }

    // ===================
    // Getters / Setters
    // ===================

    /**
     * Retourne le nom d'utilisateur du compte.
     * @return Le nom d'utilisateur
     */
    public String getUsername() {
        return username;
    }

    /**
     * Retourne le mot de passe du compte.
     * @return Le mot de passe
     */
    public String getPassword() {
        return password;
    }

    /**
     * Retourne la zone courante du joueur.
     * @return Le nom de la zone actuelle
     */
    public String getZoneCourante() {
        return zoneCourante;
    }

    /**
     * Retourne la liste des objets présents dans l'inventaire du joueur.
     * @return La liste des noms d'objets
     */
    public List<String> getInventaire() {
        return inventaire;
    }

    /**
     * Retourne la liste des fragments collectés par le joueur.
     * @return La liste des noms de fragments
     */
    public List<String> getFragments() {
        return fragments;
    }

    /**
     * Modifie la zone courante du joueur.
     * @param z La nouvelle zone courante
     */
    public void setZoneCourante(String z) {
        this.zoneCourante = z;
    }

    /**
     * Modifie l'inventaire du joueur.
     * @param i La nouvelle liste d'objets
     */
    public void setInventaire(List<String> i) {
        this.inventaire = i;
    }

    /**
     * Modifie la liste des fragments collectés par le joueur.
     * @param f La nouvelle liste de fragments
     */
    public void setFragments(List<String> f) {
        fragments = f;
    }
}
