package org.example;
public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
    private Joueur joueur;
    public Jeu() {
        creerCarte();
        gui = null;
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    
    private void creerCarte() {
        Zone [] zones = new Zone [4];
        zones[0] = new Zone("le couloir", "Couloir.jpg" );
        zones[1] = new Zone("l'escalier", "Escalier.jpg" );
        zones[2] = new Zone("la grande salle", "GrandeSalle.jpg" );
        zones[3] = new Zone("la salle à manger", "SalleAManger.jpg" );
        zones[0].ajouteSortie(Sortie.EST, zones[1]);
        zones[1].ajouteSortie(Sortie.OUEST, zones[0]);
        zones[1].ajouteSortie(Sortie.EST, zones[2]);
        zones[2].ajouteSortie(Sortie.OUEST, zones[1]);
        zones[3].ajouteSortie(Sortie.NORD, zones[1]);
        zones[1].ajouteSortie(Sortie.SUD, zones[3]);
        zoneCourante = zones[1]; 
        
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
            // Déplacements
            case "N":
            case "NORD":
                allerEn("NORD");
                break;
            case "S":
            case "SUD":
                allerEn("SUD");
                break;
            case "E":
            case "EST":
                allerEn("EST");
                break;
            case "O":
            case "OUEST":
                allerEn("OUEST");
                break;
            case "H":
            case "HAUT":
                allerEn("HAUT");
                break;
            case "B":
            case "BAS":
                allerEn("BAS");
                break;

            case "R":
            case "REVENIR":
                revenir();
                break;

            case "P":
            case "PRENDRE":
                // TODO : item à définir 
                joueur.prendreItem(item);
                break;

            case "D":
            case "DEPOSER":
                // TODO : item à définir 
                joueur.deposerObjet(item);
                break;

            case "A":
            case "A(FFICHER L'INVENTAIRE ACTUEL)":
                afficherInventaire();
                break;

            case "T":
            case "TEST":
                test();
                break;

            case "C":
            case "COMMUNIQUER":
                joueur.communiquerAvecPNJ();
                break;

            case "V":
            case "VENDRE":
                vendre();
                break;

            case "X":
            case "VOIR_INDICE":
                voirIndices();
                break;

            case "Z":
            case "SAUVEGARDER":
                sauvegarderJeu();
                break;

            case "M":
            case "AFFICHER":
                afficherCarte();
                break;

            case "?":
            case "AIDE":
                afficherAide();
                break;

            case "Q":
            case "QUITTER":
                terminer();
                break;

            default:
                gui.afficher("Commande inconnue. Tapez \"?\" pour la liste des commandes.");
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

    private void revenir() {
        // TODO: Gérer le retour à la zone précédente 
    }

    private void test() {
        // TODO: Exécuter une action de test ou un débogage
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

    private void sauvegarderJeu() {
        // TODO: Mettre en place la logique de sauvegarde
    }

    private void afficherCarte() {
        // TODO: Afficher un plan du monde (texte ou image)
    }

}
