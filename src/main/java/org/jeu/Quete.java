package org.jeu;

public class Quete {
    private String nom;
    private String description;
    private String condition;
    private String recompense;
    private Enigme enigme;
    private boolean terminee = false;

    public Quete(String nom, String description, String condition, String recompense) {
        this.nom = nom;
        this.description = description;
        this.condition = condition;
        this.recompense = recompense;
    }

    public Quete(String nom, String description, String condition, String recompense, Enigme enigme) {
        this.nom = nom;
        this.description = description;
        this.condition = condition;
        this.recompense = recompense;
        this.enigme = enigme;
    }

    public boolean resoudreEnigme(Joueur joueur) {
        if (enigme != null && enigme.estResolu()) {
            terminee = true;
            if(recompense != ""){
                joueur.getFragments().add(Fragments.valueOf(recompense));
            }
            return true;
        }
        return false;
    }

    public String interagirAvecEnigme(Joueur joueur) {
        if (enigme != null) {
            enigme.poser();
            if (enigme.estResolu()) {
                if (resoudreEnigme(joueur)) {
                    return "Bravo ! Vous avez résolu l'énigme. Voici votre récompense : " + recompense;
                }
            } else {
                return "Vous n'avez pas encore résolu l'énigme.";
            }
        }
        return "Aucune énigme associée à cette quête.";
    }

    public boolean isTerminee() {
        return terminee;
    }

    public Enigme getEnigme() {
        return enigme;
    }

    public void terminer() {
        this.terminee = true;
    }

    public String getRecompense() {
        return recompense;
    }

    public String getCondition() {
        return condition;
    }
}