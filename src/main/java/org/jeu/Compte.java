package org.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe représentant un compte utilisateur dans le jeu.
 * Elle contient les informations de connexion et l'état du joueur.
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
     * Constructeur de la classe Compte
     * @param username le nom d'utilisateur
     * @param password le mot de passe
     * @param zoneDepart la zone de départ
     */
    public Compte(String username, String password, String zoneDepart) {
        this.username    = username;
        this.password    = password;
        this.zoneCourante = zoneDepart;
        this.inventaire  = new ArrayList<>();
        this.fragments    = new ArrayList<>();
    }

    /**
     * @return le nom d'utilisateur
     */
    public String getUsername()            {
        return username;
    }

    /**
     * @return le mot de passe
     */
    public String getPassword()            {
        return password;
    }

    /**
     * @return la zone courante
     */
    public String getZoneCourante()        {
        return zoneCourante;
    }
    /**
     * @return l'inventaire
     */
    public List<String> getInventaire()    {
        return inventaire;
    }

    /**
     * @return les fragments
     */
    public List<String> getFragments()            {
        return fragments;
    }

    /**
     * Set la zone courante
     * @param z
     */
    public void setZoneCourante(String z)  {
        this.zoneCourante = z;
    }
    /**
     * Set l'inventaire
     * @param i
     */
    public void setInventaire(List<String> i){
        this.inventaire = i;
    }

    /**
     * Set les fragments
     * @param f
     */
    public void setFragments(List<String> f){
        fragments = f;
    }



}
