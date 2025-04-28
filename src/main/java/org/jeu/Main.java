package org.jeu;

import java.io.Console;
import java.util.Scanner;

/**
 * Point d'entrée principal de l'application du jeu.
 * <p>
 * Cette classe gère l'authentification de l'utilisateur (connexion ou création d'un compte),
 * la connexion du joueur au jeu, puis le lancement de l'interface graphique.
 * Le mot de passe est masqué lors de la saisie en terminal si possible.
 * </p>
 */
public class Main {

    /**
     * Méthode principale du programme.
     * <ul>
     *   <li>Demande à l'utilisateur son nom et son mot de passe (masqué si le terminal le permet).</li>
     *   <li>Tente de connecter l'utilisateur à un compte existant ; sinon crée un nouveau compte.</li>
     *   <li>Connecte le joueur au jeu et lance l'interface graphique.</li>
     * </ul>
     *
     * @param args Les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {

        Console console = System.console();
        String user, pass;

        if (console == null) {
            // Cas d'un IDE ou d'un environnement sans Console (mot de passe non masqué)
            Scanner sc = new Scanner(System.in);
            System.out.print("Nom d'utilisateur : ");
            user = sc.nextLine();
            System.out.print("Mot de passe      : ");
            pass = sc.nextLine();
        } else {
            // Cas d'un terminal réel (mot de passe masqué)
            user = console.readLine("Nom d'utilisateur : ");
            char[] pwdArray = console.readPassword("Mot de passe      : ");
            pass = new String(pwdArray);
        }

        Compte compte = SauvegardeManager.login(user, pass);
        Jeu jeu = new Jeu();
        if (compte == null) {
            System.out.println("=> nouveau compte créé");
            compte = SauvegardeManager.creerCompte(user, pass, jeu.getZoneDepart());
        }
        jeu.connecterJoueur(compte);
        GUI gui = new GUI(jeu);
        jeu.setGUI(gui);
    }
}
