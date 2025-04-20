package org.jeu;

import java.util.List;

/**
 * Petit POJO (Plain Old Java Object) qui décrit l'état minimal à sauver.
 * Il est volontairement détaché du reste du modèle pour éviter
 * toute sérialisation profonde ou circulaire.
 */
public class SaveData {

    public String nomUtilisateur;
    public String motDePasse;
    public String zoneActuelle;
    public List<String> inventaire;
    public List<String> fragments;
}


