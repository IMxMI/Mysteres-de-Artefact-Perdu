package org.jeu;

public class PNJ {
    private final String nom;
    private final String dialogue;
    private final Quete quete;

    public PNJ(String nom, String dialogue, Quete quete) {
        this.nom = nom;
        this.dialogue = dialogue;
        this.quete = quete;
    }

    public String interagir(Joueur joueur) {
        if (quete != null && !quete.isTerminee()) {
            if (joueur.getSac().possedeItem(quete.getCondition())) {
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
}