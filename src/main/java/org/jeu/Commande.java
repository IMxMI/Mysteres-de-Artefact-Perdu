package org.jeu;

import java.util.ArrayList;
import java.util.List;

/**
 * Enumère l'ensemble des commandes disponibles dans le jeu.
 * <p>
 * Chaque commande possède une abréviation et une description textuelle, facilitant l'affichage de l'aide et le traitement des entrées utilisateur.
 * </p>
 */
public enum Commande {
	// Déplacements
	/** Aller en haut */
	HAUT("H", "H (aller en haut)"),
	/** Aller en bas */
	BAS("B", "B (aller en bas)"),
	/** Revenir à la zone précédente */
	REVENIR("R", "R (revenir à la zone précédente)"),

	// Divers
	/** Prendre un objet */
	PRENDRE("P", "P (prendre un objet)"),
	/** Déposer un objet */
	DEPOSER("D", "D (déposer un objet)"),
	/** Afficher l'inventaire actuel */
	AFFICHER_INVENTAIRE("A", "A (afficher l'inventaire actuel)"),
	/** Commande de test */
	TEST("T", "T (test)"),
	/** Communiquer avec un autre personnage */
	COMMUNIQUER("C", "C (Communiquer avec un autre Personnage)"),
	/** Vendre un objet */
	VENDRE("V", "V (Vendre)"),
	/** Voir les indices */
	VOIR_INDICE("X", "X (voir les indices)"),
	/** Sauvegarder la partie */
	SAUVEGARDER("Z", "Z (sauvegarder la partie)"),
	/** Afficher la carte */
	AFFICHER("M", "M (afficher la carte)"),

	/** Afficher l'aide */
	AIDE("?", "? (aide)"),
	/** Quitter le jeu */
	QUITTER("Q", "Q (quitter)");

	/** Abréviation de la commande (pour la saisie utilisateur) */
	private String abreviation;

	/** Description de la commande (pour l'affichage de l'aide) */
	private String description;

	/**
	 * Construit une commande avec son abréviation et sa description.
	 * @param c L'abréviation de la commande
	 * @param d La description détaillée de la commande
	 */
	private Commande(String c, String d) {
		abreviation = c;
		description = d;
	}

	/**
	 * Retourne le nom de la constante enum.
	 * @return Le nom de la commande
	 */
	@Override
	public String toString() {
		return name();
	}

	/**
	 * Retourne la liste de toutes les descriptions des commandes.
	 * @return Une liste de descriptions de commandes
	 */
	public static List<String> toutesLesDescriptions() {
		ArrayList<String> resultat = new ArrayList<String>();
		for (Commande c : values()) {
			resultat.add(c.description);
		}
		return resultat;
	}

	/**
	 * Retourne la liste de toutes les abréviations des commandes.
	 * @return Une liste d'abréviations de commandes
	 */
	public static List<String> toutesLesAbreviations() {
		ArrayList<String> resultat = new ArrayList<String>();
		for (Commande c : values()) {
			resultat.add(c.abreviation);
		}
		return resultat;
	}

	/**
	 * Retourne la liste des noms de toutes les commandes.
	 * @return Une liste des noms des commandes (constantes enum)
	 */
	public static List<String> tousLesNoms() {
		ArrayList<String> resultat = new ArrayList<String>();
		for (Commande c : values()) {
			resultat.add(c.name());
		}
		return resultat;
	}
}
