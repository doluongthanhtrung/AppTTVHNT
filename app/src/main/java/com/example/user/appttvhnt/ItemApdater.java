package com.example.user.appttvhnt;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemTrangChu> itemList;

    public ItemApdater(Context context, int layout, List<ItemTrangChu> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHolder{
        TextView txtTen;
        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder.txtTen=(TextView) view.findViewById(R.id.tvTen);
            holder.imgHinh=(ImageView) view.findViewById(R.id.imgHinh);
            view.setTag(holder);
        }else {
            holder =(ViewHolder) view.getTag();
        }
        ItemTrangChu item=itemList.get(i);
        holder.txtTen.setText(item.getTen());
        holder.imgHinh.setImageResource(item.getHinh());
        return view;
    }
}
