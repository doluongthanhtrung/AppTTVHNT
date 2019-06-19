package com.example.user.appttvhnt.Model;

public class NhatKy {
    private String TenCLB;
    private String Ngay;
    private String NoiDung;

    public NhatKy() {
    }

    public NhatKy(String tenCLB, String ngay, String noiDung) {
        TenCLB = tenCLB;
        Ngay = ngay;
        NoiDung = noiDung;
    }

    public String getTenCLB() {
        return TenCLB;
    }

    public String getNgay() {
        return Ngay;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setTenCLB(String tenCLB) {
        TenCLB = tenCLB;
    }

    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }
}
