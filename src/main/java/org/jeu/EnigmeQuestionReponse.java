package org.jeu;

import javax.swing.*;
import java.util.function.Predicate;

/**
 * Classe représentant une énigme de type question-réponse.
 * Cette classe permet de poser une question au joueur et de valider sa réponse.
 */
public class EnigmeQuestionReponse implements Enigme {
    private final String question;
    private final Predicate<String> validation;
    private boolean resolu = false;

    /**
     * Constructeur de la classe EnigmeQuestionReponse.
     *
     * @param question   La question à poser
     * @param validation La fonction de validation de la réponse
     */
    public EnigmeQuestionReponse(String question, Predicate<String> validation) {
        this.question = question;
        this.validation = validation;
    }

    /**
     * Vérifie si l'énigme est résolue.
     * @return
     */
    @Override
    public boolean estResolu() {
        return resolu;
    }

    /**
     * Pose l'énigme au joueur.
     * Cette méthode affiche la question et attend la réponse du joueur.
     * Si la réponse est correcte, l'énigme est marquée comme résolue.
     */
    @Override
    public void poser() {
        String reponse = JOptionPane.showInputDialog(question);
        if (reponse != null && validation.test(reponse.toLowerCase())) {
            JOptionPane.showMessageDialog(null, "Bonne réponse !");
            resolu = true;
        } else {
            JOptionPane.showMessageDialog(null, "Mauvaise réponse.");
        }
    }
}
