package org.example.entites;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "villes", schema = "distribution")
public class VillesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "vil_num")
    private int vilNum;
    @Basic
    @Column(name = "nom")
    private String nom;

    public int getVilNum() {
        return vilNum;
    }

    public void setVilNum(int vilNum) {
        this.vilNum = vilNum;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VillesEntity that = (VillesEntity) o;
        return vilNum == that.vilNum && Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vilNum, nom);
    }
}
