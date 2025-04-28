package org.jeu;

/**
 * Classe principale du jeu.
 * Cette classe gère la logique du jeu, y compris la création de la carte,
 */
public class Jeu {

    private GUI gui; 
	private Zone zoneCourante;
    private Zone zonePrecedente = null;
    private Joueur joueur;
    private Item item;

    /**
     * Constructeur de la classe Jeu.
     * Initialise la carte du jeu et la GUI.
     */
    public Jeu() {
        creerCarte();
        gui = null;
    }

    /**
     * Définit la GUI du jeu.
     * @param g La GUI à associer au jeu
     */
    public void setGUI( GUI g) { gui = g; afficherMessageDeBienvenue(); }
    Zone [] zones = new Zone [9];
    /**
     * Crée la carte du jeu.
     * Initialise les zones, les sorties et les PNJ.
     */
    private void creerCarte() {
        
        zones[0] = new Zone("Nouvelle-Dauréa", "ville01.png" );
        zones[1] = new Zone("La Clairière des Souvenirs", "foret01.png" );
        zones[2] = new Zone("La Zone Désertique", "desert01.png" );
        zones[3] = new Zone("Camp Tarsis", "camp01.png" );
        zones[4] = new Zone("La Grotte", "grotte01.png" );
        zones[5] = new Zone("Laboratoire Abandonnée", "labo01.png" );
        zones[6] = new Zone("game over", "gameover.png" );
        zones[7] = new Zone("Introduction", "ville04.png" );
        zones[8] = new Zone("game win", "gamewin.png" );

        zones[0].ajouteSortie(Sortie.NORD, zones[1]);
        zones[0].ajouteSortie(Sortie.EST, zones[2]);

        zones[1].ajouteSortie(Sortie.SUD, zones[0]);
        zones[1].ajouteSortie(Sortie.EST, zones[2]);

        zones[2].ajouteSortie(Sortie.OUEST, zones[1]);
        zones[2].ajouteSortie(Sortie.SUD, zones[0]);
        zones[2].ajouteSortie(Sortie.EST, zones[3]);

        zones[3].ajouteSortie(Sortie.OUEST, zones[2]);
        zones[3].ajouteSortie(Sortie.SUD, zones[4]);

        zones[7].ajouteSortie(Sortie.EXIT, zones[0]);

        zones[4].ajouteSortie(Sortie.EST, zones[5]);
        zoneCourante = zones[7];


        Quete queteCristal = new Quete("Trouver le cristal", "Apportez un cristal au PNJ.", "cristal", "PREMIER");
        PNJ pnjNouvelleDaurea = new PNJ("Le Marchand", "Bonjour, aventurier, j'ai entendu parler que tu rechercher les fragments d'une relique du passé. \nJe pense pouvoir t'aider !", queteCristal);
        zones[0].ajouterPNJ(pnjNouvelleDaurea);

        EnigmeInteractive enigme = new EnigmeInteractive(EnigmeInteractive.Type.SIMON);
        Quete queteEnigme = new Quete("Résoudre l'énigme", "Répondez à l'énigme du PNJ.", "reponse_correcte", "DEUXIEME", enigme);
        PNJ pnjClairiere = new PNJ("Un Sage", "Je vais vous poser une énigme...", queteEnigme);
        zones[1].ajouterPNJ(pnjClairiere);

        EnigmeInteractive enigmePilliers = new EnigmeInteractive(EnigmeInteractive.Type.PILLIERS_ORDRE, this);
        Quete quetePilliers = new Quete("Résoudre l'énigme des piliers", "Trouvez l'ordre correct des piliers pour obtenir le cristal.", "reponse_correcte", "", enigmePilliers);
        PNJ pnjDesert = new PNJ("des Piliers antique", "Seul celui qui résout l'énigme des piliers peut obtenir le cristal.", quetePilliers);
        zones[2].ajouterPNJ(pnjDesert);

        EnigmeInteractive enigmeCadenas = new EnigmeInteractive(EnigmeInteractive.Type.JUSTE_PRIX, this);
        Quete queteCadenas = new Quete("Résoudre l'énigme", "Trouver un code à 4 chiffres en 60 secondes", "reponse_correcte", "TROISIEME", enigmeCadenas);
        zones[3].ajouterPNJ(new PNJ("Un coffre derrière le garde", "", queteCadenas));


        EnigmeQuestionReponse enigmeGrotte = new EnigmeQuestionReponse("Il est marqué : 'Implorez l'artefact pour qu'il vous accorde sa bénédiction...' ", r -> r.contains("oculus") || r.contains("Oculus") || r.contains("OCULUS"));
        Quete queteGrotte = new Quete("Implorer l'artefact", "La réponse du joueur doit contenir le nom de l'artefact", "reponse_correcte", "QUATRIEME", enigmeGrotte);
        zones[4].ajouterPNJ(new PNJ("Une inscription sur le mur", "", queteGrotte));

        EnigmeInteractive enigmeMastermind = new EnigmeInteractive(EnigmeInteractive.Type.MASTERMIND, this);
        Quete queteMastermind = new Quete(
                "Activer le terminal",
                "Trouvez le code secret pour activer le terminal.",
                "code_terminal",
                "cristal",
                enigmeMastermind
        );
        zones[5].ajouterPNJ(new PNJ(
                "IA du terminal",
                "Bienvenue, humain. Pour activer ce terminal, vous devez résoudre une énigme.",
                queteMastermind
        ));
    }

    /**
     * Affiche la localisation actuelle du joueur.
     */
    private void afficherLocalisation() {
            gui.afficher( zoneCourante.descriptionLongue());
            gui.afficher();
    }

    /**
     * Affiche un message de bienvenue au joueur.
     */
    private void afficherMessageDeBienvenue() {
            gui.afficher("Bienvenue !");
            gui.afficher();
            gui.afficher("Tapez '?' pour obtenir de l'aide.");
            gui.afficher();
            afficherLocalisation();
            gui.afficheImage(zoneCourante.nomImage());
            if(zoneCourante.nomImage().equals("ville04.png")) {
                introduction();
            }
    }

    /**
     * Affiche l'introduction du jeu.
     */
    private void introduction() {
        gui.afficher("Salut Kael, nous devons vite assembler l'Oculus.\nTrouve les fragments avant les starsis !\n");
        gui.afficher("Souhaite tu que je te rappelle pourquoi notre mission est importante ? (Répondez par O/N ou Oui/Non)");
    }

    /**
     * Gère la suite de l'introduction en fonction de la réponse du joueur.
     * @param s La réponse du joueur (O/N ou Oui/Non)
     */
    private void introductionSuite(String s){
        gui.afficher( "> "+ s + "\n");
        switch (s.toUpperCase()){
            case "N","NON" -> {
                gui.afficher("Pas de problème ! Bonne aventure.\n");
                allerEn("EXIT");
                break;
            }
            case "O","OUI" -> {
                gui.afficher("En 1958, en pleine Guerre froide, Dwight D. Eisenhower fonda le DARPA (Defense Advanced Research Projects Agency), une agence du département de la Défense des États-Unis. Son objectif était clair : être à la pointe de l'innovation technologique pour garantir la suprématie militaire américaine. Cette vision prit une dimension particulièrement ambitieuse en 1989, lorsque le département de la Défense chargea le DARPA de développer un programme d’armement dont le but est d’assurer l’annihilation de toute forme de vie en cas de défaite des forces armées américaines. \n" +
                        "\n" +
                        "Ce projet, mené dans le plus grand secret, devint un enjeu stratégique pour contrer une Union soviétique perçue comme une menace croissante. Les chercheurs travaillèrent sans relâche, explorant des domaines tels que la manipulation génétique, la bioéthique, et les interfaces homme-machine. Malgré les efforts déployés, les résultats restèrent infructueux, et en 1991, peu avant la fin officielle de la Guerre froide, le projet fut abruptement abandonné. Toutes les données furent classées Extremely Secret (Extrêmement Secret) et archivées dans des installations protégées par des mesures de sécurité extrême.\n" +
                        "En 2006, le Docteur Edward Phillips, alors directeur du DARPA, obtient l'autorisation du département de la Défense pour relancer le projet. Motivé par les avancées récentes en biotechnologie, Phillips était convaincu que les échecs passés étaient dus à des limitations technologiques aujourd’hui surmontées. Pendant près de dix ans, le DARPA travailla sans relâche, expérimentant sur des cellules humaines et animales, repoussant les frontières éthiques.\n" +
                        "En 2015, après de très nombreux échecs, les opérateurs du programme ont dévoilé un prototype. Celui-ci prenait la forme d’un virus, totalement inoffensif à l’état latent, mais capable, une fois activé, de tuer toute forme de vie en quelques heures. Lors d’essais sur des spécimens humains, les chercheurs ont découvert qu’ils pouvaient en faire bien plus qu’une simple arme de dissuasion : le virus pourrait devenir l’arme ultime de l’arsenal américain, capable d’éliminer avec précision n’importe quelle cible.\n" +
                        "\n" +
                        "En 2022, après un piratage massif orchestré par un groupe de hackers anonymes, une partie des données ultra secrètes des projets du DARPA fut rendue publique. Parmi elles, l'existence du virus expérimental surnommé \"Le Juge\" fut révélée. Ce virus, conçu pour cibler et éliminer toute forme de vie avec une précision redoutable, déclencha une onde de choc mondiale. Des extraits de rapports internes du DARPA confirmèrent les tests inhumains menés sur des prisonniers et des cobayes humains, ainsi que les véritables intentions derrière ce projet : une arme capable de garantir l'hégémonie des États-Unis par la terreur absolue.\n" +
                        "La panique s’installa immédiatement. Bien que le gouvernement américain s'efforçait de minimiser l’affaire, affirmant que le projet était sous contrôle et qu’il s’agissait avant tout d’une arme de dissuasion, la communauté internationale ne fut pas convaincue.\n" +
                        "Les premières fissures apparurent au sein des alliances stratégiques des États-Unis. Des pays alliés, comme le Royaume-Uni et le Japon, exigèrent des explications, tandis que des adversaires comme la Russie et la Chine accusèrent Washington de préparer un génocide global en cas de conflit.\n" +
                        "Aux États-Unis, les révélations déclenchent des protestations massives. Le gouvernement, pour réprimer la dissidence et les menaces internes, utilisa \"Le Juge\" de manière ciblée, éliminant des militants et opposants politiques. Ces assassinats secrets alimentèrent une colère populaire grandissante.\n" +
                        "En réponse, des mouvements anti-gouvernementaux se développèrent, et les États-Unis s’enfoncèrent dans une spirale de répression, de soulèvements, et de divisions politiques.\n" +
                        "En 2025, l’instabilité atteint un point critique. Des États clés proclamèrent leur indépendance, l’armée se fragmenta, et des mutineries éclatèrent. La loi martiale ne suffit plus à contenir les révoltes. Sur la scène internationale, la peur d’une utilisation du virus mena à une militarisation mondiale, à l’effondrement des alliances, et à des conflits régionaux.\n" +
                        "En 2030, Le Juge n'avait jamais été déployé à grande échelle, mais sa simple existence avait suffi à provoquer une série de guerres régionales alimentées par la peur et la méfiance. Les gouvernements, pris entre des insurrections internes et des conflits internationaux, s’effondrèrent les uns après les autres. Les États-Unis, ravagés par la guerre civile et l’instabilité politique, entraînèrent dans leur chute l’ordre mondial, plongeant la planète dans une ère de chaos, d’anarchie, et de luttes pour le pouvoir.\n" +
                        "\n" +
                        "Après des décennies de reconstruction, la société humaine a retrouvé une certaine stabilité. De grandes cités ont émergé au milieu des ruines, et des alliances ont permis à des régions entières de renouer avec un semblant d’ordre. Cependant, les cicatrices de l’effondrement mondial sont encore visibles, et des factions s’affrontent pour le contrôle des ressources, des territoires et des vestiges du passé. \n" +
                        "Au cœur de cette reconstruction, un artefact ancien refait surface : L'Oculus du Jugement. Ce dispositif, dont l’origine exacte se perd dans les brumes du passé, est réputé pour avoir la capacité de contrôler un pouvoir ancien, oublié de tous. Bien que personne ne sache précisément ce que cet artefact active, une chose est certaine : son contrôle pourrait faire basculer l’équilibre fragile du monde.\n" +
                        "Cet artéfact d’une puissance inégalée a été volé et fragmenté en plusieurs morceaux dispersé dans différentes zones, gardés par des énigmes et des obstacles. Avec la menace d’une faction hostile qui souhaite se la procurer, le joueur incarne un jeune aventurier mandaté nommé Kael Soren pour récupérer les fragments et reconstituer l'artefact avant qu'il ne tombe entre de mauvaises mains.\n" +
                        "L'objectif principal est de réunir les fragments de L’Oculus du Jugement tout en évitant des pièges, en résolvant des énigmes et en récoltant des indices et des objets . L'histoire se déroule dans plusieurs zones uniques qui offrent différents niveaux de difficulté dans le monde des Terres des Héritages Perdus.\n\n\n" +
                        "[EXIT] pour quitter l'introduction.");
                break;
            }
            case "EXIT" -> allerEn("EXIT");
        }
    }

    /**
     * Traite la commande lue par le joueur.
     * @param commandeLue La commande saisie par le joueur
     */
    public void traiterCommande(String commandeLue) {
    	gui.afficher( "> "+ commandeLue + "\n");
        if (zoneCourante.nomImage().equals("ville04.png")) {
            introductionSuite(commandeLue);
            return;
        }
        switch (commandeLue.toUpperCase()) {
            case "N", "NORD" -> allerEn("NORD");
            case "S", "SUD" -> allerEn("SUD");
            case "E", "EST" -> allerEn("EST");
            case "O", "OUEST" -> allerEn("OUEST");
            case "H", "HAUT" -> allerEn("HAUT");
            case "B", "BAS" -> allerEn("BAS");
            case "R", "REVENIR" -> revenir();
            case "I", "AFFICHER L'INVENTAIRE ACTUEL" -> afficherInventaire();

            case "C", "COMMUNIQUER" -> {
                PNJ pnj = zoneCourante.getPNJ();
                if (pnj != null) {

                    if(zoneCourante.toString().equalsIgnoreCase("La Clairière des Souvenirs")){
                        gui.afficheImage("foret02.png");
                    }

                    if (zoneCourante.toString().equalsIgnoreCase("Camp Tarsis")){
                        gui.afficheImage("camp02.png");
                    }
                    gui.afficher(pnj.interagir(joueur));

                    if (zoneCourante.toString().equalsIgnoreCase("La Grotte") && pnj.getQuete().isTerminee()) {
                        gui.afficheImage("grotte03.png");
                        zones[4].removePnj();
                    }

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
            case "X", "VOIR_INDICE" -> voirIndices();

            case "Z", "SAUVEGARDER" -> joueur.sauvegarderJeu();
            case "M", "AFFICHER" -> afficherCarte();

            case "?", "AIDE" -> afficherAide();
            case "Q", "QUITTER" -> terminer();

            default -> gui.afficher("Commande inconnue. Tapez \"?\" pour la liste des commandes.");
        }
        // Déplacements
            }

    /**
     * affiche l'aide du jeu.
     */
    private void afficherAide() {
        gui.afficher("Etes-vous perdu ?");
        gui.afficher();
        gui.afficher("Les commandes autorisées sont :");
        gui.afficher();
        gui.afficher(Commande.toutesLesDescriptions().toString());
        gui.afficher();
    }


    /**
     * Affiche un message de fin de jeu.
     * Indique que le joueur a perdu et doit recommencer.
     */
    public void gameOver() {
        zoneCourante = zones[6];
        gui.afficher("Vous êtes mort !\n");
        gui.afficher("Vous devez recommencer le jeu.\n");
        gui.afficheImage("gameover.png");
        gui.enable(false);
    }

    /**
     * Gère le déplacement du joueur vers une nouvelle zone.
     * @param direction La direction dans laquelle le joueur souhaite se déplacer
     */
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


    /**
     * Affiche un message de fin de jeu et désactive l'interface graphique.
     */
    private void terminer() {
    	gui.afficher( "Au revoir...");
    	gui.enable( false);
    }

    /**
     * Retourne la zone actuelle du joueur.
     * @return La zone actuelle
     */
    public Zone getZoneDepart() {
        return zones[7];
    }

    /**
     * Retourne la zone actuelle du joueur.
     * @return La zone actuelle
     */
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

    /**
     * Connecte un joueur à une zone spécifique.
     * @param compte
     */
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

    /**
     * Trouve une zone par son nom.
     * @param nomZone
     * @return
     */
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

    /**
     * Retourne la classe GUI.
     * @return
     */
    public GUI getGUI() {
        return gui;
    }

    public Joueur getJoueur() {
        return joueur;
    }

    public void gameWin(){
        zoneCourante = zones[8];
        gui.afficheImage("gamewin.png");
    }

}
