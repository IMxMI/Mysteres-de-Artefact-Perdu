package org.example;


public class Joueur {

    private Sac sac;
    private Zone zoneActuelle;
    private Compte compte;

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
    }

    public void deposerItem(Item item) {
        // TODO : vérifier que l’objet est bien dans le sac,
    }
    
    public void communiquerAvecPNJ() {
        // TODO : implémenter la logique de communication selon le scénario
    }

    public boolean possedeFragment(String nomFragment) {
        // TODO : parcourir sac.items pour trouver si l’objet "fragment" est présent
        return false;
    }
}
