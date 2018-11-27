package com.example.user.appttvhnt;

public class ItemTrangChu {
    private String Ten;
    private int Hinh;

    public ItemTrangChu(String ten, int hinh) {
        Ten = ten;
        Hinh = hinh;
    }

    public String getTen() {
        return Ten;
    }

    public int getHinh() {
        return Hinh;
    }
}
