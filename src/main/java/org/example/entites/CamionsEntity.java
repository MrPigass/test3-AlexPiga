package org.example.entites;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "camions", schema = "distribution")
public class CamionsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cmn_num")
    private int cmnNum;
    @Basic
    @Column(name = "charge")
    private int charge;
    @Basic
    @Column(name = "charge_max")
    private int chargeMax;

    public int getCmnNum() {
        return cmnNum;
    }

    public void setCmnNum(int cmnNum) {
        this.cmnNum = cmnNum;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getChargeMax() {
        return chargeMax;
    }

    public void setChargeMax(int chargeMax) {
        this.chargeMax = chargeMax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CamionsEntity that = (CamionsEntity) o;
        return cmnNum == that.cmnNum && charge == that.charge && chargeMax == that.chargeMax;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cmnNum, charge, chargeMax);
    }
}
