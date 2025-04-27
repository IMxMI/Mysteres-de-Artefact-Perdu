package org.jeu;

public class Quete {
    private String nom;
    private String description;
    private boolean terminee;
    private String condition; // Par exemple, "trouver_cristal"
    private String recompense; // Par exemple, "fragment"

    public Quete(String nom, String description, String condition, String recompense) {
        this.nom = nom;
        this.description = description;
        this.condition = condition;
        this.recompense = recompense;
        this.terminee = false;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public boolean isTerminee() {
        return terminee;
    }

    public void terminer() {
        this.terminee = true;
    }

    public String getCondition() {
        return condition;
    }

    public String getRecompense() {
        return recompense;
    }
}