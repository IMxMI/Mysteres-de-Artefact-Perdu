package org.jeu;

/**
 * Classe représentant un personnage non-joueur (PNJ) dans le jeu.
 * Un PNJ a un nom, un dialogue et peut être associé à une quête.
 */
public class PNJ {
    private final String nom;
    private final String dialogue;
    private final Quete quete;

    /**
     * Constructeur de la classe PNJ.
     * @param nom
     * @param dialogue
     * @param quete
     */
    public PNJ(String nom, String dialogue, Quete quete) {
        this.nom = nom;
        this.dialogue = dialogue;
        this.quete = quete;
    }

    /**
     * Interagit avec le joueur.
     * Si le PNJ a une quête active, il pose une énigme ou demande un objet.
     * Si la quête est terminée, il donne une récompense au joueur.
     *
     * @param joueur Le joueur qui interagit avec le PNJ
     * @return Un message indiquant le résultat de l'interaction
     */
    public String interagir(Joueur joueur) {
        if (quete != null && !quete.isTerminee()) {
            if (quete.getEnigme() != null) {
                quete.getEnigme().poser();
                if (quete.getEnigme().estResolu()) {
                    quete.terminer();
                    joueur.getFragments().add(Fragments.valueOf(quete.getRecompense()));
                    return "Bravo ! Vous avez résolu l'énigme. Voici votre récompense : " + quete.getRecompense();
                } else {
                    return "Vous n'avez pas encore résolu l'énigme.";
                }
            } else if (joueur.getSac().possedeItem(quete.getCondition())) {
                quete.terminer();
                joueur.getFragments().add(Fragments.valueOf(quete.getRecompense()));
                return "Merci pour " + quete.getCondition() + "! Voici votre récompense : " + quete.getRecompense();
            } else {
                return dialogue + " Apportez-moi " + quete.getCondition() + ".";
            }
        }
        return "Je n'ai rien à vous demander pour le moment.";
    }

    public String getNom() {
        return nom;
    }

    public Quete getQuete() {
        return quete;
    }
}