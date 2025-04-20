package org.jeu;

import javax.swing.*;
import java.time.Instant;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class EnigmeInteractive implements Enigme {
    public enum Type {
        SIMON, JUSTE_PRIX
    }

    private final Type type;
    private boolean resolu = false;

    public EnigmeInteractive(Type type) {
        this.type = type;
    }

    @Override
    public boolean estResolu() {
        return resolu;
    }

    @Override
    public void poser() {
        switch (type) {
            case SIMON -> SuperSimon();
            case JUSTE_PRIX -> JustePrix();
        }
    }

    private void SuperSimon() {
        // TODO : à implémenter
        System.out.println("C'est parti pour le Super Simon...");
    }

    private void JustePrix() {
        final int code = ThreadLocalRandom.current().nextInt(1000, 10000);
        Instant debut = Instant.now();
        while (true) {
            String s = JOptionPane.showInputDialog("Devinez le code à 4 chiffres (en 60 secondes):");
            // Si le joueur annule ou ferme la fenêtre, on quitte
            if (s == null) return;
            // On calcule le temps écoulé et si il dépasse 60, le joueur a perdu
            long secondes = Instant.now().getEpochSecond() - debut.getEpochSecond();
            if (secondes > 60) {
                JOptionPane.showMessageDialog(null, "Temps écoulé !");
                break;
            }
            try {
                int guess = Integer.parseInt(s);
                if (guess == code) {
                    JOptionPane.showMessageDialog(null, "Bravo !");
                    resolu = true;
                    break;
                } else {
                    JOptionPane.showMessageDialog(null, guess < code ? "Plus !" : "Moins !" );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrée invalide.");
            }
        }
    }


}
