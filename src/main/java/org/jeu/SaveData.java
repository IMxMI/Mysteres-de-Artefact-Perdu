package org.jeu;

import java.util.List;

/**
 * Représente les données sauvegardées d'un compte joueur.
 * <p>
 * Cette classe sert de structure de données simple pour la sérialisation/désérialisation
 * (notamment avec Gson) des informations liées à un joueur dans le fichier de sauvegarde.
 * </p>
 */
public class SaveData {

    /** Nom d'utilisateur du joueur */
    public String nomUtilisateur;

    /** Mot de passe associé au joueur */
    public String motDePasse;

    /** Nom de la zone actuelle où se trouve le joueur */
    public String zoneActuelle;

    /** Liste des noms d'objets présents dans l'inventaire du joueur */
    public List<String> inventaire;

    /** Liste des noms des fragments possédés par le joueur */
    public List<String> fragments;
}
