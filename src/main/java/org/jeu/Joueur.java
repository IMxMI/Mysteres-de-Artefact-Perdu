package org.jeu;


import java.util.ArrayList;
import java.util.List;
public class Joueur {

    private Sac sac;
    private Zone zoneActuelle;
    private Compte compte;
    private final List<Fragments> fragments = new ArrayList<>();

    public Joueur(Sac sac, Zone zoneActuelle, Compte compte) {
        this.sac = sac;
        this.zoneActuelle = zoneActuelle;
        this.compte = compte;
    }

    public Sac getSac() {
        return sac;
    }

    public void setSac(Sac sac) {
        this.sac = sac;
    }

    public Zone getZoneActuelle() {
        return zoneActuelle;
    }

    public void setZoneActuelle(Zone zoneActuelle) {
        this.zoneActuelle = zoneActuelle;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }


    public void prendreItem(Item item) {
        // TODO : on vérifie d’abord que l’item est bien présent dans la zone
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deposerItem(Item item) {
        sac.enleverItem(item);
    }
    
    public void communiquerAvecPNJ() {
        // TODO : implémenter la logique de communication selon le scénario
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean possedeFragment(Fragments f) {
        return fragments.contains(f);
    }
    public List<Fragments> getFragments() {
        return fragments;
    }
    public void deposerObjet(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void sauvegarderJeu() {
        SauvegardeManager.sauvegarder(this);
        System.out.println("Partie sauvegardée !\n");
    }

}
