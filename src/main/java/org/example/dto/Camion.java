package org.example.dto;

public class Camion {
    private  int cmn_num;
    private int charge;
    private int charge_max;

    public Camion(int cmn_num, int charge, int charge_max) {
        this.cmn_num = cmn_num;
        this.charge = charge;
        this.charge_max = charge_max;
    }

    public int getCmn_num() {
        return cmn_num;
    }

    public void setCmn_num(int cmn_num) {
        this.cmn_num = cmn_num;
    }

    public int getCharge() {
        return charge;
    }

    public void setCharge(int charge) {
        this.charge = charge;
    }

    public int getCharge_max() {
        return charge_max;
    }

    public void setCharge_max(int charge_max) {
        this.charge_max = charge_max;
    }

}
