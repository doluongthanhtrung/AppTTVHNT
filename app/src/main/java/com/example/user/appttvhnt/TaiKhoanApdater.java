package com.example.user.appttvhnt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TaiKhoanApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TAIKHOAN> itemList;

    public TaiKhoanApdater(Context context, int layout, List<TAIKHOAN> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(layout,null);

        TextView txtTenTaiKhoan=(TextView) view.findViewById(R.id.textViewTenTaiKhoan);
        TextView txtMatKhau=(TextView) view.findViewById(R.id.textViewMatKhau);
        TextView txtLoaiTaiKhoan=(TextView) view.findViewById(R.id.textViewLoaiTaiKhoan);

        TAIKHOAN tk=itemList.get(position);
        txtTenTaiKhoan.setText(tk.getTendangnhap());
        txtMatKhau.setText(tk.getMatkhau());
        txtLoaiTaiKhoan.setText(tk.getLoaitaikhoan());

        return view;
    }
}
