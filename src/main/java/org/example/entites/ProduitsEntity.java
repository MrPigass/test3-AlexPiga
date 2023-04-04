package org.example.entites;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "produits", schema = "distribution")
public class ProduitsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "prd_num")
    private int prdNum;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "poids")
    private int poids;
    @Basic
    @Column(name = "vil_num")
    private Integer vilNum;
    @Basic
    @Column(name = "cmn_num")
    private Integer cmnNum;

    public int getPrdNum() {
        return prdNum;
    }

    public void setPrdNum(int prdNum) {
        this.prdNum = prdNum;
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

    public Integer getVilNum() {
        return vilNum;
    }

    public void setVilNum(Integer vilNum) {
        this.vilNum = vilNum;
    }

    public Integer getCmnNum() {
        return cmnNum;
    }

    public void setCmnNum(Integer cmnNum) {
        this.cmnNum = cmnNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProduitsEntity that = (ProduitsEntity) o;
        return prdNum == that.prdNum && poids == that.poids && Objects.equals(description, that.description) && Objects.equals(vilNum, that.vilNum) && Objects.equals(cmnNum, that.cmnNum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prdNum, description, poids, vilNum, cmnNum);
    }
}
