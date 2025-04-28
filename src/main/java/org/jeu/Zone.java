package org.jeu;
import java.util.HashMap;

/**
 * Classe représentant une zone dans le jeu.
 * Chaque zone a une description, une image associée, des sorties vers d'autres zones,
 * un PNJ (Personnage Non Joueur) et des objets.
 */
public class Zone 
{
    private String description;
    private String nomImage;
    private HashMap<String,Zone> sorties;
    private PNJ pnj;
    private Item[] items;

    /**
     * Constructeur de la classe Zone.
     * @param description La description de la zone
     * @param image L'image associée à la zone
     */
    public Zone(String description, String image) {
        this.description = description;
        nomImage = image;
        sorties = new HashMap<>();
    }

    /**
     * Ajoute une sortie à la zone.
     * @param sortie
     * @param zoneVoisine
     */
    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }

    public String nomImage() {
        return nomImage;
    }
     
    public String toString() {
        return description;
    }

    /**
     * Renvoie la description longue de la zone, incluant les sorties et le PNJ.
     * @return La description longue de la zone
     */
    public String descriptionLongue()  {
        String text = "Vous êtes dans " + description + "\nSorties : " + sorties();
        if(pnj != null) {
            text += "\nVous voyez " + pnj.getNom() + " C ou COMMUNIQUER pour interagir.";
        }
        return text;
    }

    private String sorties() {
        return sorties.keySet().toString();
    }

    public Zone obtientSortie(String direction) {
    	return sorties.get(direction);
    }

    public PNJ getPNJ() {
        return pnj;
    }

    public void removePnj() {
        this.pnj = null;
    }

    public void ajouterPNJ(PNJ pnjNouvelleDaurea) {
        this.pnj = pnjNouvelleDaurea;
    }
}

