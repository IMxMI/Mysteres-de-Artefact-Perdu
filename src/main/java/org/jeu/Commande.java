package org.jeu;
import java.util.ArrayList;
import java.util.List;

public enum Commande {
	// Déplacements
	HAUT("H", "H (aller en haut)"),
	BAS("B", "B (aller en bas)"),
	REVENIR("R", "R (revenir à la zone précédente)"),

	// Divers
	PRENDRE("P", "P (prendre un objet)"),
	DEPOSER("D", "D (déposer un objet)"),
	AFFICHER_INVENTAIRE("A", "A (afficher l'inventaire actuel)"),
	TEST("T", "T (test)"),
	COMMUNIQUER("C", "C (Communiquer avec un autre Personnage)"),
	VENDRE("V", "V (Vendre)"),
	VOIR_INDICE("X", "X (voir les indices)"),
	SAUVEGARDER("Z", "Z (sauvegarder la partie)"),
	AFFICHER("M", "M (afficher la carte)"),

	AIDE("?", "? (aide)"), 
	QUITTER("Q", "Q (quitter)");

	private String abreviation;
	private String description;
	private Commande(String c, String d ) { 
		abreviation = c;
		description = d; 
	}
	@Override
	public String toString() { 
		return name();
	}
	
	public static List<String> toutesLesDescriptions() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.description);
		}
		return resultat;
	}
	
	public static List<String> toutesLesAbreviations() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.abreviation);
		}
		return resultat;
	}
	
	public static List<String> tousLesNoms() { 
		ArrayList<String> resultat = new ArrayList<String>();
		for(Commande c : values()) {
			resultat.add( c.name());
		}
		return resultat;
	}

}
