package org.jeu;

/**
 * Enumère les différents fragments que le joueur peut collectionner dans le jeu.
 * <p>
 * Chaque fragment possède un nom affiché et une zone d'obtention spécifique.
 * </p>
 */
public enum Fragments {

    /** Fragment obtenu dans la zone Nouvelle‑Dauréa (commerce) */
    PREMIER  ("Fragment du Cœur",          "Nouvelle‑Dauréa (commerce)"),

    /** Fragment obtenu dans la Clairière des Souvenirs */
    DEUXIEME ("Fragment de la Forêt",      "Clairière des Souvenirs"),

    /** Fragment obtenu dans le Camp Tarsis */
    TROISIEME("Fragment de la Guerre",     "Camp Tarsis"),

    /** Fragment obtenu dans La Grotte */
    QUATRIEME("Fragment des Ténèbres",     "La Grotte");

    /** Nom affiché du fragment */
    private final String nomAffiche;

    /** Zone où le fragment peut être obtenu */
    private final String zoneObtention;

    /**
     * Construit un fragment avec un nom affiché et une zone d'obtention.
     *
     * @param nomAffiche    Le nom affiché du fragment
     * @param zoneObtention La zone d'obtention du fragment
     */
    Fragments(String nomAffiche, String zoneObtention) {
        this.nomAffiche   = nomAffiche;
        this.zoneObtention = zoneObtention;
    }

    /**
     * Retourne le nom affiché du fragment.
     * @return Le nom affiché
     */
    public String getNom() {
        return nomAffiche;
    }

    /**
     * Retourne la zone d'obtention du fragment.
     * @return Le nom de la zone d'obtention
     */
    public String getZoneObtention() {
        return zoneObtention;
    }

    /**
     * Retourne le nom affiché du fragment (pour l'affichage).
     * @return Le nom affiché du fragment
     */
    @Override
    public String toString() {
        return nomAffiche;
    }
}
