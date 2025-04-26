package org.jeu;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    private Sac sac;
    private Zone zoneActuelle;
    private Compte compte;
    private final List<Fragments> fragments = new ArrayList<>();
    private final List<String> indices = new ArrayList<>();

    public Joueur(Sac sac, Zone zoneActuelle, Compte compte) {
        this.sac = sac;
        this.zoneActuelle = zoneActuelle;
        this.compte = compte;
    }

    public Sac getSac() {
        return sac;
    }

    public List<Fragments> getFragments() {
        return fragments;
    }

    public Zone getZoneActuelle() {
        return zoneActuelle;
    }

    public void setZoneActuelle(Zone zoneActuelle) {
        this.zoneActuelle = zoneActuelle;
    }

    public List<String> getIndices() {
        return indices;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public void setSac(Sac sac) {
        this.sac = sac;
    }

    public void prendreItem(Item item) {
        if (item == null || !zoneActuelle.containsItem(item)) {
            System.out.println("Cet objet n’est pas ici.");
            return;
        }
        if (!sac.ajouterItem(item)) {
            System.out.println("Le sac est plein. Voulez-vous déposer un objet ? (Tapez D <nom>)");
            return;
        }
        zoneActuelle.removeItem(item);
        System.out.println("Vous prenez : " + item.getNom());
    }

    public void deposerItem(Item item) {
        if (item == null || !sac.contient(item)) {
            System.out.println("Vous ne possédez pas cet objet.");
            return;
        }
        sac.enleverItem(item);
        zoneActuelle.addItem(item);
        System.out.println("Vous déposez : " + item.getNom());
    }

    public void communiquerAvecPNJ() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean possedeFragment(Fragments f) {
        return fragments.contains(f);
    }

    public void sauvegarderJeu() {
        SauvegardeManager.sauvegarder(this);
        System.out.println("Partie sauvegardée !");
    }
}