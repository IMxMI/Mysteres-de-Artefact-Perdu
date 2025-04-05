package org.jeu;
public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
    
    public Jeu() {
        creerCarte();
        gui = null;
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    
    private void creerCarte() {
        Zone [] zones = new Zone [6];
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
        case "?" : case "AIDE" : 
            afficherAide(); 
        	break;
        case "N" : case "NORD" :
        	allerEn( "NORD"); 
        	break;
       case "S" : case "SUD" :
        	allerEn( "SUD"); 
        	break;
        case "E" : case "EST" :
        	allerEn( "EST"); 
        	break;
        case "O" : case "OUEST" :
        	allerEn( "OUEST"); 
        	break;
        case "Q" : case "QUITTER" :
        	terminer();
        	break;
       	default : 
            gui.afficher("Commande inconnue");
            break;
        }
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
    	Zone nouvelle = zoneCourante.obtientSortie( direction);
    	if ( nouvelle == null ) {
        	gui.afficher( "Pas de sortie " + direction);
    		gui.afficher();
    	}
        else {
        	zoneCourante = nouvelle;
        	gui.afficher(zoneCourante.descriptionLongue());
        	gui.afficher();
        	gui.afficheImage(zoneCourante.nomImage());
        }
    }
    
    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }
}
