package org.jeu;

import javax.swing.*;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Classe représentant une énigme interactive dans le jeu.
 */
public class EnigmeInteractive implements Enigme {
    /**
     * Enumération des types d'énigmes.
     */
    public enum Type {
        SIMON, JUSTE_PRIX, MASTERMIND, PILLIERS_ORDRE
    }

    /**
     * Type de l'énigme.
     */
    private final Type type;
    /**
     * Indique si l'énigme est résolue.
     */
    private boolean resolu = false;

    /**
     * Constructeur de la classe EnigmeInteractive.
     *
     * @param type le type d'énigme
     */
    public EnigmeInteractive(Type type) {
        this.type = type;
    }

    private Jeu jeu;

    /**
     * Constructeur de la classe EnigmeInteractive.
     *
     * @param type le type d'énigme
     * @param jeu  le jeu associé
     */
    public EnigmeInteractive(Type type, Jeu jeu) {
        this.type = type;
        this.jeu = jeu;
    }

    /**
     * @return true si l'énigme est résolue, false sinon
     */
    @Override
    public boolean estResolu() {
        return resolu;
    }

    /**
     * Pose l'énigme au joueur.
     */
    @Override
    public void poser() {
        switch (type) {
            case SIMON -> SuperSimon();
            case JUSTE_PRIX -> JustePrix();
            case MASTERMIND -> Mastermind();
            case PILLIERS_ORDRE -> PilliersOrdre();
        }
    }

    /**
     * Enigme du jeu Simon.
     * @return
     */
    private boolean SuperSimon() {
        String symboles = "@#$%&*";
        StringBuilder sequence = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int alea = (int)(Math.random() * symboles.length());
            char symbole = symboles.charAt(alea);
            sequence.append(symbole);
        }

        String solution = sequence.toString().toString();
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
        JOptionPane.showMessageDialog(null, "Vous avez 45 secondes pour deviner le code à 4 chiffres avant que le garde ne vous voit.");
        while (true) {
            String s = JOptionPane.showInputDialog("Trouvez le code :");
            if (s == null) return;
            long secondes = Instant.now().getEpochSecond() - debut.getEpochSecond();
            if (secondes > 45) {
                JOptionPane.showMessageDialog(null, "Temps écoulé, le garde vous a attrapé !");
                jeu.gameOver();
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
            jeu.gameOver();
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
            if (java.util.Arrays.equals(propositionCouleurs, code)) {
                JOptionPane.showMessageDialog(null, "Félicitations ! Vous avez trouvé le code : " + String.join(" ", code) + "\n Ordinateur déverrouillé, activation des processus.");
                jeu.gameWin();
                resolu = true;

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
        jeu.gameOver();
    }

    private void PilliersOrdre() {
        if (jeu == null || jeu.getJoueur() == null || jeu.getJoueur().getSac() == null) {
            JOptionPane.showMessageDialog(null, "Erreur : Le jeu ou le joueur n'est pas correctement initialisé.");
            return;
        }

        String[] inscriptions = {
                "Je suis l'aube d'un cycle éternel.",
                "Je suis le lien entre le début et la fin.",
                "Je suis l'ombre qui clôt le jour."
        };

        List<Integer> chiffres = new ArrayList<>(List.of(1, 2, 3));
        Collections.shuffle(chiffres);

        // Mélanger les inscriptions selon l'ordre des chiffres
        String[] inscriptionsMelangees = new String[inscriptions.length];
        for (int i = 0; i < chiffres.size(); i++) {
            inscriptionsMelangees[i] = inscriptions[chiffres.get(i) - 1];
        }

        String combinaisonCorrecte = chiffres.stream().map(String::valueOf).reduce("", String::concat);

        StringBuilder message = new StringBuilder("Les piliers portent les inscriptions suivantes :\n");
        for (int i = 0; i < inscriptionsMelangees.length; i++) {
            message.append("Pilier ").append(i + 1).append(": ").append(inscriptionsMelangees[i]).append("\n");
        }
        message.append("\nEntrez l'ordre correct des piliers (par exemple, 123) :");

        while (!resolu) {
            String reponse = JOptionPane.showInputDialog(message.toString());
            if (reponse == null) return;

            if (reponse.equals(combinaisonCorrecte)) {
                JOptionPane.showMessageDialog(null, "Félicitations ! Vous avez trouvé la bonne combinaison.");
                resolu = true;

                // Ajouter un cristal au sac du joueur
                jeu.getJoueur().getSac().ajouterItem(new Item("cristal", "Un cristal scintillant."));

                // Afficher une image si le GUI est disponible
                if (jeu.getGUI() != null) {
                    jeu.getGUI().afficheImage("desert02.png");
                }

                // Retirer le PNJ de la zone
                if (jeu.zones[2] != null) {
                    jeu.zones[2].removePnj();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Mauvaise combinaison. Réessayez.");
            }
        }
    }


}
