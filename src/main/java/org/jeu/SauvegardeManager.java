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

public final class SauvegardeManager {

    private static final Path FICHIER = Paths.get("sauvegardes.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static Map<String, Compte> comptes = new HashMap<>();

    static {
        chargerDepuisDisque();
    }

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

    private static void saveToDisk() {
        try {
            Files.writeString(FICHIER, GSON.toJson(comptes));
        }
        catch (IOException e) {
            System.err.println("Erreur d'écriture dans le fichier de sauvegarde : " + e.getMessage());
        }
    }


    public static Compte login(String user, String pass) {
        Compte c = comptes.get(user);
        return (c != null && c.getPassword().equals(pass)) ? c : null;
    }

    public static Compte creerCompte(String user, String pass, Zone zoneDepart) {
        Compte c = new Compte(user, pass, zoneDepart.toString());
        comptes.put(user, c);
        saveToDisk();
        return c;
    }

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
