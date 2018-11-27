package com.example.user.appttvhnt;

public class ItemHS_GV {
    private String id;
    private String hoten;
    private String clb;
    private String sdt;
    private String chucvu;
    private String check="2";
    private String sobuoi="0";

    public ItemHS_GV() {
    }

    public ItemHS_GV(String id, String hoten, String clb, String sdt, String chucvu, String check, String sobuoi) {
        this.id = id;
        this.hoten = hoten;
        this.clb = clb;
        this.sdt = sdt;
        this.chucvu = chucvu;
        this.check = check;
        this.sobuoi = sobuoi;
    }

    public String getId() {
        return id;
    }

    public String getHoten() {
        return hoten;
    }

    public String getClb() {
        return clb;
    }

    public String getSdt() {
        return sdt;
    }

    public String getChucvu() {
        return chucvu;
    }

    public String getCheck() {
        return check;
    }

    public String getSobuoi() {
        return sobuoi;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public void setClb(String clb) {
        this.clb = clb;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public void setCheck(String check) {
        this.check = check;
    }

    public void setSobuoi(String sobuoi) {
        this.sobuoi = sobuoi;
    }
}