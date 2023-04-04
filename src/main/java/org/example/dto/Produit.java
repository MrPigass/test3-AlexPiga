package org.example.dto;

public class Produit {
    private int id;
    private String nom;
    private String description;
    private int poids;
    private String ville;
    private int cmn_num;

    public Produit(int id, String description, int poids, String vil_num) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.poids = poids;
        this.ville = vil_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public String getVil_num() {
        return ville;
    }

    public void setVil_num(String vil_num) {
        this.ville = vil_num;
    }

    public int getCmn_num() {
        return cmn_num;
    }

    public void setCmn_num(int cmn_num) {
        this.cmn_num = cmn_num;
    }
}
