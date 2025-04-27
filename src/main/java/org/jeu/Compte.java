package org.jeu;

import java.util.ArrayList;
import java.util.List;

public class Compte {
    private String username;
    private String password;
    private String zoneCourante;
    private List<String> inventaire;
    private List<String> fragments;

    public Compte() {

    }

    public Compte(String username, String password, String zoneDepart) {
        this.username    = username;
        this.password    = password;
        this.zoneCourante = zoneDepart;
        this.inventaire  = new ArrayList<>();
        this.fragments    = new ArrayList<>();
    }

    /* getters / setters */
    public String getUsername()            {
        return username;
    }
    public String getPassword()            {
        return password;
    }
    public String getZoneCourante()        {
        return zoneCourante;
    }
    public List<String> getInventaire()    {
        return inventaire;
    }

    public List<String> getFragments()            {
        return fragments;
    }

    public void setZoneCourante(String z)  {
        this.zoneCourante = z;
    }
    public void setInventaire(List<String> i){
        this.inventaire = i;
    }

    public void setFragments(List<String> f){
        fragments = f;
    }



}
