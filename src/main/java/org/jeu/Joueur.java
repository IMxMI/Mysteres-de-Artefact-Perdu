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

    public List<String> getIndices()   {   return indices;   }

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
        sac.ajouterItem(item);
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

    public void deposerObjet(Item item) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    public void sauvegarderJeu() {
        SauvegardeManager.sauvegarder(this);
        System.out.println("Partie sauvegardée !\n");
    }

}
