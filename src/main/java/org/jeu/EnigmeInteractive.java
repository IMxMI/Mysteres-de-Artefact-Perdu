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

    private boolean SuperSimon() {
        String symboles = "*"; //@#$%&*
        String sequence = "";

        for (int i = 0; i < 6; i++) {
            int alea = (int)(Math.random() * symboles.length());
            char symbole = symboles.charAt(alea);
            sequence = sequence + symbole;
        }

        String solution = sequence.toString();
        JOptionPane.showMessageDialog(null, "Retenez cette suite :\n" + solution);

        String reponse = JOptionPane.showInputDialog("Tapez la séquence :");

        if (reponse != null && reponse.replaceAll("\\s+", "").equals(solution)) {
            JOptionPane.showMessageDialog(null, "Bravo !");
            resolu = true; // Mise à jour de la variable resolu
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Raté ! C'était : " + solution);
            return false;
        }
    }




    private void JustePrix() {
        final int code = ThreadLocalRandom.current().nextInt(1000, 10000);
        Instant debut = Instant.now();
        JOptionPane.showMessageDialog(null, "Vous avez 60 secondes pour deviner le code à 4 chiffres avant que le garde ne vous voit.");
        while (true) {
            String s = JOptionPane.showInputDialog("Trouvez le code :");
            // Si le joueur annule ou ferme la fenêtre, on quitte
            if (s == null) return;
            // On calcule le temps écoulé et si il dépasse 60, le joueur a perdu
            long secondes = Instant.now().getEpochSecond() - debut.getEpochSecond();
            if (secondes > 60) {
                JOptionPane.showMessageDialog(null, "Temps écoulé, le garde vous a attrapé !");
                break;
            }
            try {
                int guess = Integer.parseInt(s);
                if (guess == code) {
                    JOptionPane.showMessageDialog(null, "Le cadenas est dévérouillé !");
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
