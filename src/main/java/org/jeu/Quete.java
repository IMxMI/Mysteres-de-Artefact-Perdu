package org.jeu;

/**
 * Classe représentant une quête dans le jeu.
 * Cette classe contient les informations de base d'une quête, y compris son nom,
 * sa description, sa condition, sa récompense et une énigme associée.
 */
public class Quete {
    private String nom;
    private String description;
    private String condition;
    private String recompense;
    private Enigme enigme;
    private boolean terminee = false;

    /**
     * Constructeur de la classe Quete.
     *
     * @param nom        Le nom de la quête
     * @param description La description de la quête
     * @param condition   La condition à remplir pour terminer la quête
     * @param recompense  La récompense obtenue après avoir terminé la quête
     */
    public Quete(String nom, String description, String condition, String recompense) {
        this.nom = nom;
        this.description = description;
        this.condition = condition;
        this.recompense = recompense;
    }

    /**
     * Constructeur de la classe Quete avec énigme.
     *
     * @param nom        Le nom de la quête
     * @param description La description de la quête
     * @param condition   La condition à remplir pour terminer la quête
     * @param recompense  La récompense obtenue après avoir terminé la quête
     * @param enigme      L'énigme associée à la quête
     */
    public Quete(String nom, String description, String condition, String recompense, Enigme enigme) {
        this.nom = nom;
        this.description = description;
        this.condition = condition;
        this.recompense = recompense;
        this.enigme = enigme;
    }

    /**
     * Donne récompense au joueur si l'énigme est résolue.
     * @param joueur
     * @return
     */
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

    /**
     * Interagit avec l'énigme associée à la quête.
     * Si l'énigme est résolue, le joueur reçoit une récompense.
     *
     * @param joueur Le joueur qui interagit avec l'énigme
     * @return Un message indiquant le résultat de l'interaction
     */
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