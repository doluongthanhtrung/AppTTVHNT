package com.example.user.appttvhnt;

public class LichSinhHoat {
    private int ID;
    private String MaCLB;
    private String TenCLB;
    private int Thu;
    private String Gio;
    private String Tong;
    private String SoLuong;

    public LichSinhHoat() {
    }

    public LichSinhHoat(int ID, String maCLB, String tenCLB, int thu, String gio, String tong, String soLuong) {
        this.ID = ID;
        MaCLB = maCLB;
        TenCLB = tenCLB;
        Thu = thu;
        Gio = gio;
        Tong = tong;
        SoLuong = soLuong;
    }

    public int getID() {
        return ID;
    }

    public String getMaCLB() {
        return MaCLB;
    }

    public String getTenCLB() {
        return TenCLB;
    }

    public int getThu() {
        return Thu;
    }

    public String getGio() {
        return Gio;
    }

    public String getTong() {
        return Tong;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMaCLB(String maCLB) {
        MaCLB = maCLB;
    }

    public void setTenCLB(String tenCLB) {
        TenCLB = tenCLB;
    }

    public void setThu(int thu) {
        Thu = thu;
    }

    public void setGio(String gio) {
        Gio = gio;
    }

    public void setTong(String tong) {
        Tong = tong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }
}
