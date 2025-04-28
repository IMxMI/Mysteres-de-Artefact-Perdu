package org.jeu;

/**
 * Interface représentant une énigme dans le jeu.
 * Cette interface définit les méthodes nécessaires pour poser une énigme et vérifier si elle est résolue.
 */
public interface Enigme {
    /**
     * Vérifie si l'énigme est résolue.
     *
     * @return true si l'énigme est résolue, false sinon
     */
    boolean estResolu();
    /**
     * Pose l'énigme au joueur.
     * Cette méthode doit être implémentée pour poser l'énigme spécifique.
     */
    void poser();
}
