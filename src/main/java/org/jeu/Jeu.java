package org.jeu;
public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
    private Joueur joueur;
    private Item item;
  
    public Jeu() {
        creerCarte();
        gui = null;
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    Zone [] zones = new Zone [6];
    private void creerCarte() {
        
        zones[0] = new Zone("Nouvelle-Dauréa", "NouvelleDaurea.png" );
        zones[1] = new Zone("La Clairière des Souvenirs", "ClairiereSouvenir.png" );
        zones[2] = new Zone("La Zone Désertique", "ZoneDesertique.png" );
        zones[3] = new Zone("Camp Tarsis", "CampTarsis.png" );
        zones[4] = new Zone("La Grotte", "Grotte.png" );
        zones[5] = new Zone("Laboratoire Abandonnée", "laboratoire.png" );

        zones[0].ajouteSortie(Sortie.NORD, zones[1]);
        zones[0].ajouteSortie(Sortie.EST, zones[2]);

        zones[1].ajouteSortie(Sortie.SUD, zones[0]);
        zones[1].ajouteSortie(Sortie.EST, zones[2]);

        zones[2].ajouteSortie(Sortie.OUEST, zones[1]);
        zones[2].ajouteSortie(Sortie.SUD, zones[0]);
        zones[2].ajouteSortie(Sortie.EST, zones[3]);

        zones[3].ajouteSortie(Sortie.OUEST, zones[2]);
        zones[3].ajouteSortie(Sortie.SUD, zones[4]);

        zones[4].ajouteSortie(Sortie.EST, zones[5]);
        zoneCourante = zones[0];

    }

    private void afficherLocalisation() {
            gui.afficher( zoneCourante.descriptionLongue());
            gui.afficher();
    }

    private void afficherMessageDeBienvenue() {
    	gui.afficher("Bienvenue !");
    	gui.afficher();
        gui.afficher("Tapez '?' pour obtenir de l'aide.");
        gui.afficher();
        afficherLocalisation();
        gui.afficheImage(zoneCourante.nomImage());
    }
    
    public void traiterCommande(String commandeLue) {
    	gui.afficher( "> "+ commandeLue + "\n");
        switch (commandeLue.toUpperCase()) {
            case "N", "NORD" -> allerEn("NORD");
            case "S", "SUD" -> allerEn("SUD");
            case "E", "EST" -> allerEn("EST");
            case "O", "OUEST" -> allerEn("OUEST");
            case "H", "HAUT" -> allerEn("HAUT");
            case "B", "BAS" -> allerEn("BAS");
            case "R", "REVENIR" -> revenir();
            case "P", "PRENDRE" -> 
                joueur.prendreItem(item);
            case "D", "DEPOSER" -> 
                joueur.deposerObjet(item);
            case "A", "A(FFICHER L'INVENTAIRE ACTUEL)" -> afficherInventaire();
            case "T", "TEST" -> test();
            case "C", "COMMUNIQUER" -> joueur.communiquerAvecPNJ();

            case "V", "VENDRE" -> vendre();
            case "X", "VOIR_INDICE" -> voirIndices();

            case "Z", "SAUVEGARDER" -> joueur.sauvegarderJeu();
            case "M", "AFFICHER" -> afficherCarte();

            case "?", "AIDE" -> afficherAide();
            case "Q", "QUITTER" -> terminer();

            default -> gui.afficher("Commande inconnue. Tapez \"?\" pour la liste des commandes.");
        }
        // Déplacements
            }

    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }


    private void allerEn(String direction) {
        Zone nouvelle = zoneCourante.obtientSortie(direction);
        if (nouvelle == null) {
            gui.afficher("Pas de sortie " + direction);
            gui.afficher();
        } else {
            zoneCourante = nouvelle;

            if (joueur != null) {
                joueur.setZoneActuelle(zoneCourante);
            }

            gui.afficher(zoneCourante.descriptionLongue());
            gui.afficher();
            gui.afficheImage(zoneCourante.nomImage());
        }
    }


    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }

    // Jeu.java
    public Zone getZoneDepart() {
        return zones[0];
    }

    private void revenir() {
        // TODO: Gérer le retour à la zone précédente 
    }

    private void test() {
        // TODO: Exécuter une action de test
    }

    private void vendre() {
        // TODO: Vendre un objet à un PNJ ou à un marchand 
    }

    private void voirIndices() {
        // TODO: Afficher les indices collectés par le joueur
    }

    private void afficherInventaire() {
        // TODO: Lister les objets dans le sac du joueur
    }

    private void afficherCarte() {
        // TODO: Afficher un plan du monde (texte ou image)
    }

    public void connecterJoueur(Compte compte) {

        Zone pos = trouverZone(compte.getZoneCourante());

        Sac sac = new Sac();
        for (String nomItem : compte.getInventaire()) {
            sac.ajouterItem(new Item(nomItem, ""));
        }

        Joueur j = new Joueur(sac, pos, compte);
        for (String nomFrag : compte.getFragments()) {
            j.getFragments().add(Fragments.valueOf(nomFrag));
        }

        zoneCourante = pos;
        joueur       = j;
    }

    public Zone trouverZone(String nomZone) {
        if (nomZone == null) {
            return zones[0];
        }
        for (Zone z : zones) {
            if (z != null && z.toString().equalsIgnoreCase(nomZone.trim())) {
                return z;
            }
        }
        return zones[0];
    }

}
