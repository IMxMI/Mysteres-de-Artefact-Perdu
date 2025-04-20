package org.jeu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
		Jeu jeu = new Jeu();

        Scanner sc = new Scanner(System.in);
        System.out.print("Nom d'utilisateur : ");
        String user = sc.nextLine();
        System.out.print("Mot de passe      : ");
        String pass = sc.nextLine();

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
