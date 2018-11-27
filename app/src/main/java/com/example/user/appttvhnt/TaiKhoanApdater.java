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

    private class ViewHolder{
        TextView txtTenTaiKhoan,txtMatKhau,txtLoaiTaiKhoan;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);
            holder.txtTenTaiKhoan=(TextView) view.findViewById(R.id.textViewTenTaiKhoan);
            holder.txtMatKhau=(TextView) view.findViewById(R.id.textViewMatKhau);
            holder.txtLoaiTaiKhoan=(TextView) view.findViewById(R.id.textViewLoaiTaiKhoan);

            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        TAIKHOAN tk=itemList.get(position);
        holder.txtTenTaiKhoan.setText(tk.getTendangnhap());
        holder.txtMatKhau.setText(tk.getMatkhau());
        holder.txtLoaiTaiKhoan.setText(tk.getLoaitaikhoan());

        return view;
    }
}
