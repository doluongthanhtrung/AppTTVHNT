package com.example.user.appttvhnt.Model;

import java.io.Serializable;

public class TAIKHOAN {
    private String tendangnhap;
    private String matkhau;
    private String loaitaikhoan;

    public TAIKHOAN() {
    }

    public TAIKHOAN(String tendangnhap, String matkhau, String loaitaikhoan) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.loaitaikhoan = loaitaikhoan;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
