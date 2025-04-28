package org.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un compte joueur dans le jeu.
 * Cette classe contient les informations de base d'un joueur, y compris son nom d'utilisateur,
 * son mot de passe, la zone actuelle où il se trouve, ainsi que son inventaire et ses fragments.
 */
public class Compte {
    private String username;
    private String password;
    private String zoneCourante;
    private List<String> inventaire;
    private List<String> fragments;

    public Compte() {
    }

    /**
     * Constructeur de la classe Compte.
     *
     * @param username    Le nom d'utilisateur du joueur
     * @param password    Le mot de passe du joueur
     * @param zoneDepart  La zone de départ du joueur
     */
    public Compte(String username, String password, String zoneDepart) {
        this.username    = username;
        this.password    = password;
        this.zoneCourante = zoneDepart;
        this.inventaire  = new ArrayList<>();
        this.fragments    = new ArrayList<>();
    }

    /**
     * Récupère le nom d'utilisateur.
     * @return
     */
    public String getUsername()            {
        return username;
    }
    /**
     * Récupère le mot de passe.
     * @return
     */
    public String getPassword()            {
        return password;
    }

    /**
     * Récupère la zone actuelle.
     * @return
     */
    public String getZoneCourante()        {
        return zoneCourante;
    }
    /**
     * Récupère l'inventaire du joueur.
     * @return
     */
    public List<String> getInventaire()    {
        return inventaire;
    }

    /**
     * Récupère la liste des fragments du joueur.
     * @return
     */
    public List<String> getFragments()            {
        return fragments;
    }

    /**
     * Définit la zone actuelle du joueur.
     * @param z
     */
    public void setZoneCourante(String z)  {
        this.zoneCourante = z;
    }
    /**
     * Définit l'inventaire du joueur.
     * @param i
     */
    public void setInventaire(List<String> i){
        this.inventaire = i;
    }

    /**
     * Définit la liste des fragments du joueur.
     * @param f
     */
    public void setFragments(List<String> f){
        fragments = f;
    }



}
