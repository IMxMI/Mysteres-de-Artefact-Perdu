package org.jeu;

public enum Fragments {

    PREMIER  ("Fragment du Cœur",          "Nouvelle‑Dauréa (commerce)"),
    DEUXIEME ("Fragment de la Forêt",      "Clairière des Souvenirs"),
    TROISIEME("Fragment de la Guerre",     "Camp Tarsis");

    private final String nomAffiche;
    private final String zoneObtention;

    Fragments(String nomAffiche, String zoneObtention) {
        this.nomAffiche   = nomAffiche;
        this.zoneObtention = zoneObtention;
    }

    public String getNom(){
        return nomAffiche;
    }
    public String getZoneObtention(){
        return zoneObtention;
    }

    @Override
    public String toString() { return nomAffiche; }
}
