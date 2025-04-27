package org.jeu;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Gère la sauvegarde et le chargement des comptes joueurs pour le jeu.
 * <p>
 * Cette classe est responsable de la sérialisation et de la désérialisation
 * des comptes dans un fichier JSON local ("sauvegardes.json").
 * Toutes les méthodes sont statiques et la classe ne peut pas être instanciée.
 */
public final class SauvegardeManager {

    /** Chemin du fichier de sauvegarde JSON */
    private static final Path FICHIER = Paths.get("sauvegardes.json");

    /** Objet Gson configuré pour la sérialisation/desérialisation en JSON */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /** Map contenant tous les comptes enregistrés, indexés par nom d'utilisateur */
    private static Map<String, Compte> comptes = new HashMap<>();

    // Initialisation des comptes à partir du disque lors du chargement de la classe
    static {
        chargerDepuisDisque();
    }

    /**
     * Charge les comptes depuis le fichier de sauvegarde JSON si celui-ci existe.
     * Remplace la map {@code comptes} par celle du fichier, ou la laisse vide si erreur.
     */
    private static void chargerDepuisDisque() {
        if (Files.exists(FICHIER)) {
            try {
                String json = Files.readString(FICHIER);
                Type t = new TypeToken<Map<String, Compte>>(){}.getType();
                comptes = (Map<String, Compte>) Optional.ofNullable(GSON.fromJson(json, t))
                        .orElse(new HashMap<>());
            } catch (IOException e) {
                System.err.println("Erreur de lecture du fichier de sauvegarde : " + e.getMessage());
            }
        }
    }

    /**
     * Sauvegarde tous les comptes dans le fichier JSON {@code FICHIER}.
     * Affiche une erreur en cas d'échec d'écriture.
     */
    private static void saveToDisk() {
        try {
            Files.writeString(FICHIER, GSON.toJson(comptes));
        }
        catch (IOException e) {
            System.err.println("Erreur d'écriture dans le fichier de sauvegarde : " + e.getMessage());
        }
    }

    /**
     * Authentifie un utilisateur avec son nom et son mot de passe.
     *
     * @param user Le nom d'utilisateur
     * @param pass Le mot de passe
     * @return Le compte correspondant si trouvé et mot de passe correct, {@code null} sinon
     */
    public static Compte login(String user, String pass) {
        Compte c = comptes.get(user);
        return (c != null && c.getPassword().equals(pass)) ? c : null;
    }

    /**
     * Crée un nouveau compte utilisateur et le sauvegarde immédiatement sur disque.
     *
     * @param user       Le nom d'utilisateur
     * @param pass       Le mot de passe
     * @param zoneDepart La zone de départ du joueur
     * @return Le nouveau compte créé
     */
    public static Compte creerCompte(String user, String pass, Zone zoneDepart) {
        Compte c = new Compte(user, pass, zoneDepart.toString());
        comptes.put(user, c);
        saveToDisk();
        return c;
    }

    /**
     * Met à jour la sauvegarde du joueur fourni (zone actuelle, inventaire, fragments…)
     * et écrit la sauvegarde sur disque.
     *
     * @param j Le joueur à sauvegarder
     */
    public static void sauvegarder(Joueur j) {
        Compte c = j.getCompte();
        c.setZoneCourante(j.getZoneActuelle().toString());
        c.setInventaire(j.getSac().nomsItems());
        List<String> nomsFragments = j.getFragments()
                .stream()
                .map(Fragments::name)
                .toList();
        c.setFragments(nomsFragments);
        saveToDisk();
    }
}
