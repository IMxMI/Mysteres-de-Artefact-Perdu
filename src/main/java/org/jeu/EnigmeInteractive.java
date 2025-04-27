package org.jeu;

import javax.swing.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EnigmeInteractive implements Enigme {
    public enum Type {
        SIMON, JUSTE_PRIX, MASTERMIND
    }

    private final Type type;
    private boolean resolu = false;

    public EnigmeInteractive(Type type) {
        this.type = type;
    }

    private Jeu jeu;

    public EnigmeInteractive(Type type, Jeu jeu) {
        this.type = type;
        this.jeu = jeu;
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
            case MASTERMIND -> Mastermind();
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
            // On calcule le temps écoulé et s'il dépasse 60, le joueur a perdu
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

    private void Mastermind() {
        if(jeu.getJoueur().getFragments().size() != 4){
            jeu.getGUI().afficher("Vous n'avez pas assez de fragments pour activer l'odinateur\nvous avez causer la fin du monde.");
            return;
        }
        final int MAX_ESSAIS = 5;
        final int CODE_LENGTH = 4;
        String[] couleurs = {"Violet", "Vert", "Rouge", "Bleu"};
        List<String> listeCouleurs = new ArrayList<>(Arrays.asList(couleurs));
        Collections.shuffle(listeCouleurs); // Mélange aléatoire
        String[] code = listeCouleurs.subList(0, CODE_LENGTH).toArray(new String[0]); // Sélection des 4 premières couleurs

        JOptionPane.showMessageDialog(null, "Bienvenue dans le terminal. Trouvez le code secret de 4 couleurs (Violet, Vert, Rouge, Bleu) en " + MAX_ESSAIS + " essais.");

        for (int essai = 1; essai <= MAX_ESSAIS; essai++) {
            System.out.println(Arrays.toString(code));
            String proposition = JOptionPane.showInputDialog("Essai " + essai + "/" + MAX_ESSAIS + " : Entrez une combinaison de 4 couleurs séparées par des espaces (ex: Rouge Bleu Vert Violet).");
            if (proposition == null) return; // Si le joueur annule

            String[] propositionCouleurs = proposition.split(" ");
            if (propositionCouleurs.length != CODE_LENGTH) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer exactement 4 couleurs.");
                continue;
            }

            // Vérification si le joueur a trouvé le code
            if (java.util.Arrays.equals(propositionCouleurs, code)) {
                JOptionPane.showMessageDialog(null, "Félicitations ! Vous avez trouvé le code : " + String.join(" ", code) + "\n Ordinateur déverrouillé, activation des processus.");
                resolu = true;

                // Afficher une image via GUI
                if (jeu != null && jeu.getGUI() != null) {
                    jeu.getGUI().afficheImage("labo02.png"); // Chemin de l'image
                }
                return;
            }

            // Calcul des indices
            int bienPlaces = 0, malPlaces = 0;
            boolean[] codeUtilise = new boolean[CODE_LENGTH];
            boolean[] propUtilise = new boolean[CODE_LENGTH];

            // Vérification des couleurs bien placées
            for (int i = 0; i < CODE_LENGTH; i++) {
                if (propositionCouleurs[i].equalsIgnoreCase(code[i])) {
                    bienPlaces++;
                    codeUtilise[i] = true;
                    propUtilise[i] = true;
                }
            }

            // Vérification des couleurs mal placées
            for (int i = 0; i < CODE_LENGTH; i++) {
                if (!propUtilise[i]) {
                    for (int j = 0; j < CODE_LENGTH; j++) {
                        if (!codeUtilise[j] && propositionCouleurs[i].equalsIgnoreCase(code[j])) {
                            malPlaces++;
                            codeUtilise[j] = true;
                            break;
                        }
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Indices : " + bienPlaces + " bien placée(s), " + malPlaces + " mal placée(s).");
        }

        JOptionPane.showMessageDialog(null, "Vous avez échoué ! Le code était : " + String.join(" ", code));
        System.exit(0); // Fin du jeu
    }


}
