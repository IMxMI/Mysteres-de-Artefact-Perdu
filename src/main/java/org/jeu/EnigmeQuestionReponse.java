package org.jeu;

import javax.swing.*;
import java.util.function.Predicate;

public class EnigmeQuestionReponse implements Enigme {
    private final String question;
    private final Predicate<String> validation;
    private boolean resolu = false;

    public EnigmeQuestionReponse(String question, Predicate<String> validation) {
        this.question = question;
        this.validation = validation;
    }

    @Override
    public boolean estResolu() {
        return resolu;
    }

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
