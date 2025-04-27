package org.jeu;
import java.util.HashMap;

public class Zone 
{
    private String description;
    private String nomImage;
    private HashMap<String,Zone> sorties;
    private PNJ pnj;
    private Item[] items;

    public Zone(String description, String image) {
        this.description = description;
        nomImage = image;
        sorties = new HashMap<>();
    }

    public void ajouteSortie(Sortie sortie, Zone zoneVoisine) {
        sorties.put(sortie.name(), zoneVoisine);
    }

    public String nomImage() {
        return nomImage;
    }
     
    public String toString() {
        return description;
    }

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

