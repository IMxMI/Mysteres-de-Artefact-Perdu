package org.jeu;

import java.util.stream.Collectors;

public class Jeu {
    private Zone zoneCourante;
    private Zone zonePrecedente = null;
    private Joueur joueur;
    Zone[] zones = new Zone[6];

    public Jeu() {
        creerCarte();
    }

    private void creerCarte() {
        zones[0] = new Zone("Nouvelle-Dauréa", "NouvelleDaurea.png");
        zones[1] = new Zone("La Clairière des Souvenirs", "ClairiereSouvenir.png");
        zones[2] = new Zone("La Zone Désertique", "ZoneDesertique.png");
        zones[3] = new Zone("Camp Tarsis", "CampTarsis.png");
        zones[4] = new Zone("La Grotte", "Grotte.png");
        zones[5] = new Zone("Laboratoire Abandonné", "laboratoire.png");

        Item carte = new Item("Carte", "Permet de visualiser la carte du monde.");
        Item cleVegetale = new Item("Clé Végétale", "Une clé mystique aux motifs végétaux, peut-être pour un mécanisme.");
        Item eauBenite = new Item("Eau Bénite", "Dissout les ronces magiques dans les forêts.");
        Item corde = new Item("Corde", "Utile pour descendre dans des fosses ou grimper.");
        Item eclatCristal = new Item("Éclat de Cristal", "Un cristal précieux échangeable contre un fragment.");
        Item torche = new Item("Torche", "Illumine les zones sombres comme les grottes.");
        Item potionTemps = new Item("Potion de Temps", "Ralentit le temps pour prolonger les délais.");

        zones[0].addItem(carte);
        zones[0].addItem(cleVegetale);
        zones[0].addItem(eauBenite);
        zones[1].addItem(corde);
        zones[2].addItem(eclatCristal);
        zones[3].addItem(torche);
        zones[3].addItem(potionTemps);

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
        System.out.println(zoneCourante.descriptionLongue());
        System.out.println();
    }

    private void afficherMessageDeBienvenue() {
        System.out.println("Bienvenue !");
        System.out.println();
        System.out.println("Tapez '?' pour obtenir de l'aide.");
        System.out.println();
        afficherLocalisation();
        System.out.println("Affichage de l'image : " + zoneCourante.nomImage());
    }

    public void traiterCommande(String commandeLue) {
        System.out.println("> " + commandeLue);
        String[] parts = commandeLue.split(" ", 2);
        String commande = parts[0].toUpperCase();

        switch (commande) {
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
                if (parts.length < 2) {
                    System.out.println("Prendre quoi ? Exemple : P Torche");
                } else {
                    Item itemToTake = zoneCourante.getItems().stream()
                            .filter(i -> i.getNom().equalsIgnoreCase(parts[1]))
                            .findFirst()
                            .orElse(null);
                    joueur.prendreItem(itemToTake);
                }
                break;
            case "D":
            case "DEPOSER":
                if (parts.length < 2) {
                    System.out.println("Déposer quoi ? Exemple : D Torche");
                } else {
                    Item itemToDrop = joueur.getSac().getItems().stream()
                            .filter(i -> i.getNom().equalsIgnoreCase(parts[1]))
                            .findFirst()
                            .orElse(null);
                    if (itemToDrop != null) {
                        joueur.deposerItem(itemToDrop);
                    } else {
                        System.out.println("Vous ne possédez pas cet objet.");
                    }
                }
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
                joueur.sauvegarderJeu();
                break;
            case "M":
            case "AFFICHER":
                afficherCarte();
                break;
            case "U":
            case "UTILISER":
                if (parts.length < 2) {
                    System.out.println("Utiliser quoi ? Exemple : U Torche");
                } else {
                    utiliserItem(parts[1]);
                }
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
                System.out.println("Commande inconnue. Tapez \"?\" pour la liste des commandes.");
        }
    }

    private void afficherAide() {
        System.out.println("Etes-vous perdu ?");
        System.out.println();
        System.out.println("Les commandes autorisées sont :");
        System.out.println();
        System.out.println(Commande.toutesLesDescriptions());
        System.out.println();
    }

    private void allerEn(String direction) {
        Zone nouvelle = zoneCourante.obtientSortie(direction);
        if (nouvelle == null) {
            System.out.println("Pas de sortie " + direction);
            return;
        }
        zonePrecedente = zoneCourante;
        zoneCourante = nouvelle;
        if (joueur != null) {
            joueur.setZoneActuelle(zoneCourante);
        }
        System.out.println(zoneCourante.descriptionLongue());
        System.out.println();
        System.out.println("Affichage de l'image : " + zoneCourante.nomImage());
    }

    private void terminer() {
        System.out.println("Au revoir...");
        // In a console game, exit the program
        System.exit(0);
    }

    public Zone getZoneDepart() {
        return zones[0];
    }

    private void revenir() {
        if (zonePrecedente == null) {
            System.out.println("Vous n’avez aucun chemin à rebrousser.");
            return;
        }
        Zone tmp = zoneCourante;
        zoneCourante = zonePrecedente;
        zonePrecedente = tmp;
        if (joueur != null) joueur.setZoneActuelle(zoneCourante);
        System.out.println("Vous faites demi-tour…");
        System.out.println(zoneCourante.descriptionLongue());
        System.out.println();
        System.out.println("Affichage de l'image : " + zoneCourante.nomImage());
    }

    private void test() {
        // TODO: Exécuter une action de test
    }

    private void vendre() {
        final String ECLAT = "Éclat de Cristal";
        if (!zoneCourante.toString().equalsIgnoreCase("Nouvelle-Dauréa")) {
            System.out.println("Vous ne pouvez vendre qu’au comptoir de Nouvelle-Dauréa.");
            return;
        }
        if (!joueur.getSac().listeNoms().contains(ECLAT)) {
            System.out.println("Vous n’avez pas l’" + ECLAT + " à vendre.");
            return;
        }
        joueur.getSac().enleverItem(
                joueur.getSac().getItems().stream()
                        .filter(i -> i.getNom().equals(ECLAT))
                        .findFirst()
                        .orElse(null)
        );
        if (!joueur.getFragments().contains(Fragments.PREMIER)) {
            joueur.getFragments().add(Fragments.PREMIER);
        }
        System.out.println("Vous vendez l’éclat : le premier fragment vous est remis !");
    }

    private void voirIndices() {
        if (joueur.getIndices().isEmpty()) {
            System.out.println("Vous n’avez recueilli aucun indice pour l’instant.");
        } else {
            System.out.println("Indices collectés : " + joueur.getIndices());
        }
    }

    private void afficherInventaire() {
        System.out.println("Inventaire («" + joueur.getSac().getItems().size() + "/5») : " + joueur.getSac().listeNoms());
    }

    private void afficherCarte() {
        System.out.println("Affichage de l'image : carte.png");
        System.out.println();
    }

    public void connecterJoueur(Compte compte) {
        Zone pos = trouverZone(compte.getZoneCourante());
        Sac sac = new Sac();
        for (String nomItem : compte.getInventaire()) {
            String description = switch (nomItem) {
                case "Carte" -> "Permet de visualiser la carte du monde.";
                case "Clé Végétale" -> "Une clé mystique aux motifs végétaux, peut-être pour un mécanisme.";
                case "Eau Bénite" -> "Dissout les ronces magiques dans les forêts.";
                case "Corde" -> "Utile pour descendre dans des fosses ou grimper.";
                case "Éclat de Cristal" -> "Un cristal précieux échangeable contre un fragment.";
                case "Torche" -> "Illumine les zones sombres comme les grottes.";
                case "Potion de Temps" -> "Ralentit le temps pour prolonger les délais.";
                default -> "Objet inconnu.";
            };
            sac.ajouterItem(new Item(nomItem, description));
        }
        Joueur j = new Joueur(sac, pos, compte);
        for (String nomFrag : compte.getFragments()) {
            j.getFragments().add(Fragments.valueOf(nomFrag));
        }
        zoneCourante = pos;
        joueur = j;
        afficherMessageDeBienvenue();
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

    private void utiliserItem(String itemName) {
        Item item = joueur.getSac().getItems().stream()
                .filter(i -> i.getNom().equalsIgnoreCase(itemName))
                .findFirst()
                .orElse(null);
        if (item == null) {
            System.out.println("Vous ne possédez pas cet objet.");
            return;
        }
        String zoneName = zoneCourante.toString();
        switch (item.getNom()) {
            case "Eau Bénite":
                if (zoneName.equals("La Clairière des Souvenirs")) {
                    System.out.println("Vous utilisez l'Eau Bénite pour dissoudre les ronces. Le chemin est dégagé !");
                    joueur.getSac().enleverItem(item);
                } else {
                    System.out.println("L'Eau Bénite n'a aucun effet ici.");
                }
                break;
            case "Torche":
                if (zoneName.equals("La Grotte")) {
                    System.out.println("Vous allumez la Torche, révélant le labyrinthe.");
                } else {
                    System.out.println("La Torche n'est pas nécessaire ici.");
                }
                break;
            case "Potion de Temps":
                if (zoneName.equals("La Grotte") || zoneName.equals("Camp Tarsis")) {
                    System.out.println("Vous buvez la Potion de Temps, ralentissant le temps.");
                    joueur.getSac().enleverItem(item);
                } else {
                    System.out.println("La Potion de Temps n'a aucun effet ici.");
                }
                break;
            case "Carte":
                afficherCarte();
                break;
            case "Clé Végétale":
                if (zoneName.equals("Laboratoire Abandonné")) {
                    System.out.println("Vous utilisez la Clé Végétale pour activer un mécanisme.");
                    joueur.getSac().enleverItem(item);
                } else {
                    System.out.println("La Clé Végétale n'a aucun effet ici.");
                }
                break;
            case "Corde":
                if (zoneName.equals("Camp Tarsis")) {
                    System.out.println("Vous utilisez la Corde pour descendre dans la fosse.");
                    joueur.getSac().enleverItem(item);
                } else {
                    System.out.println("La Corde n'a aucun effet ici.");
                }
                break;
            default:
                System.out.println("Cet objet ne peut pas être utilisé.");
        }
    }
}