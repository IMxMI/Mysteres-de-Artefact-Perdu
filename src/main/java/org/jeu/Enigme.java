package org.jeu;

/**
 * Interface représentant une énigme dans le jeu.
 * Elle définit les méthodes nécessaires pour poser et résoudre une énigme.
 */
public interface Enigme {
    /**
     * Résout l'énigme.
     * @return true si l'énigme est résolue, false sinon.
     */
    boolean estResolu();

    /**
     * Pose l'énigme au joueur.
     */
    void poser();
}
