package org.jeu;

/**
 * Interface représentant une énigme dans le jeu.
 * <p>
 * Toute énigme doit pouvoir être posée au joueur et permettre de vérifier si elle a été résolue.
 * </p>
 */
public interface Enigme {

    /**
     * Vérifie si l'énigme a été résolue par le joueur.
     *
     * @return {@code true} si l'énigme est résolue, {@code false} sinon
     */
    boolean estResolu();

    /**
     * Pose l'énigme au joueur (affichage et interaction).
     */
    void poser();
}
