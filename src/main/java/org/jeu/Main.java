package org.jeu;
import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Jeu jeu = new Jeu();

        Console console = System.console();
        String user, pass;

        if (console == null) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nom d'utilisateur : ");
            user = sc.nextLine();
            System.out.print("Mot de passe      : ");
            pass = sc.nextLine();
        } else {
            user = console.readLine("Nom d'utilisateur : ");
            char[] pwdArray = console.readPassword("Mot de passe      : ");
            pass = new String(pwdArray);
        }

        Compte compte = SauvegardeManager.login(user, pass);
        if (compte == null) {
            System.out.println("=> nouveau compte créé");
            compte = SauvegardeManager.creerCompte(user, pass, jeu.getZoneDepart());
        }

        jeu.connecterJoueur(compte);
        GUI gui = new GUI(jeu);
        jeu.setGUI(gui);
    }
}
