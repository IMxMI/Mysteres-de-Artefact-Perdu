package org.jeu;
public class Jeu {
	
    private GUI gui; 
	private Zone zoneCourante;
    private Zone zonePrecedente = null;
    private Joueur joueur;
    private Item item;
  
    public Jeu() {
        creerCarte();
        gui = null;
    }

    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    Zone [] zones = new Zone [6];
    private void creerCarte() {
        
        zones[0] = new Zone("Nouvelle-Dauréa", "ville01.png" );
        zones[1] = new Zone("La Clairière des Souvenirs", "foret01.png" );
        zones[2] = new Zone("La Zone Désertique", "desert01.png" );
        zones[3] = new Zone("Camp Tarsis", "camp01.png" );
        zones[4] = new Zone("La Grotte", "grotte01.png" );
        zones[5] = new Zone("Laboratoire Abandonnée", "labo01.png" );



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

        Quete queteCristal = new Quete("Trouver le cristal", "Apportez un cristal au PNJ.", "cristal", "PREMIER");
        PNJ pnjNouvelleDaurea = new PNJ("Ancien", "Bonjour, aventurier, j'ai entendu parler que tu rechercher les fragments d'une relique du passé. Je pense pouvoir t'aider !", queteCristal);
        zones[0].ajouterPNJ(pnjNouvelleDaurea);

        Quete queteEnigme = new Quete("Résoudre l'énigme", "Répondez à l'énigme du PNJ.", "reponse_correcte", "DEUXIEME");
        PNJ pnjClairiere = new PNJ("Sage", "Je vais vous poser une énigme...", queteEnigme);
        zones[1].ajouterPNJ(pnjClairiere);

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
            case "C", "COMMUNIQUER" -> {
                PNJ pnj = zoneCourante.getPNJ();
                if (pnj != null) {
                    gui.afficher(pnj.interagir(joueur));
                    if (zoneCourante.toString().equalsIgnoreCase("Nouvelle-Dauréa")) {
                        if (joueur.getSac().possedeItem("cristal")) {
                            gui.afficheImage("ville03.png");
                        } else {
                            gui.afficheImage("ville02.png");
                        }
                    }
                } else {
                    gui.afficher("Il n'y a personne à qui parler ici.");
                }
            }
            case "V", "VENDRE" -> vendre();
            case "X", "VOIR_INDICE" -> voirIndices();

            case "Z", "SAUVEGARDER" -> joueur.sauvegarderJeu();
            case "M", "AFFICHER" -> afficherCarte();

            case "?", "AIDE" -> afficherAide();
            case "Q", "QUITTER" -> terminer();

            case "PO" -> {
                Item item1 = new Item("cristal", "Un cristal scintillant.");
                joueur.getSac().ajouterItem(item1);
            }
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
            gui.afficher("Pas de sortie " + direction + "\n");
            return;
        }

        zonePrecedente = zoneCourante;
        zoneCourante   = nouvelle;

        if (joueur != null) {
            joueur.setZoneActuelle(zoneCourante);
        }

        gui.afficher(zoneCourante.descriptionLongue());
        gui.afficher();
        gui.afficheImage(zoneCourante.nomImage());
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
        if (zonePrecedente == null) {
            gui.afficher("Vous n’avez aucun chemin à rebrousser.\n");
            return;
        }
        Zone tmp = zoneCourante;
        zoneCourante = zonePrecedente;
        zonePrecedente = tmp;
        if (joueur != null) joueur.setZoneActuelle(zoneCourante);

        gui.afficher("Vous faites demi‑tour…\n");
        gui.afficher(zoneCourante.descriptionLongue());
        gui.afficher();
        gui.afficheImage(zoneCourante.nomImage());
    }

    private void test() {
        // TODO: Exécuter une action de test
    }

    private void vendre() {
        final String ECLAT = "Éclat de cristal";
        if (!zoneCourante.toString().equalsIgnoreCase("Nouvelle-Dauréa")) {
            gui.afficher("Vous ne pouvez vendre qu’au comptoir de Nouvelle‑Dauréa.\n");
            return;
        }
        if (!joueur.getSac().listeNoms().contains(ECLAT)) {
            gui.afficher("Vous n’avez pas l’" + ECLAT + " à vendre.\n");
            return;
        }

        /* on enlève l’objet et on crédite le fragment 1 ---------------- */
        joueur.getSac().enleverItem(
                joueur.getSac().getItems()
                        .stream()
                        .filter(i -> i.getNom().equals(ECLAT))
                        .findFirst()
                        .orElse(null)
        );
        if (!joueur.getFragments().contains(Fragments.PREMIER)) {
            joueur.getFragments().add(Fragments.PREMIER);
        }

        gui.afficher("Vous vendez l’éclat : le premier fragment vous est remis !\n");
    }

    private void voirIndices() {
        if (joueur.getIndices().isEmpty()) {
            gui.afficher("Vous n’avez recueilli aucun indice pour l’instant.\n");
        } else {
            gui.afficher("Indices collectés : " + joueur.getIndices() + "\n");
        }
    }

    private void afficherInventaire() {
        gui.afficher("Inventaire («" + joueur.getSac().getItems().size()
                + "/5») : " + joueur.getSac().listeNoms() + "\n");
    }

    private void afficherCarte() {
        gui.afficheImage("carte.png");          // située dans resources/images/
        gui.afficher();                         // saut de ligne seulement
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
