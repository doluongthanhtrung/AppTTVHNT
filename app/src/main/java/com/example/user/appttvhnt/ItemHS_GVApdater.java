package com.example.user.appttvhnt;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.renderscript.Sampler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemHS_GVApdater extends BaseAdapter {

    private Context context;
    private int layout;
    private List<ItemHS_GV> itemList;

    public ItemHS_GVApdater(Context context, int layout, List<ItemHS_GV> itemList) {
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

    public class ViewHolder {
        TextView txtTen;
        TextView txtSDT;
        TextView txtSoBuoi;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        switch (layout){
            case R.layout.item_ds_chi_tiet:{
                ViewHolder holder;
                //if (view==null){
                    holder=new ItemHS_GVApdater.ViewHolder();
                    LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view=inflater.inflate(layout,null);

                    holder.txtTen=(TextView) view.findViewById(R.id.textviewHoTen);
                    holder.txtSDT=(TextView) view.findViewById(R.id.textviewSDT);
                    holder.txtSoBuoi=(TextView) view.findViewById(R.id.textViewSoBuoi);

                    view.setTag(holder);
                /*}else {
                    holder = (ViewHolder) view.getTag();
                }*/
                ItemHS_GV item=itemList.get(i);
                holder.txtTen.setText(item.getHoten());
                holder.txtSDT.setText(item.getSdt());
                if(item.getCheck().equals("1")){
                    holder.txtTen.setTextColor(Color.GREEN);
                } else if(item.getCheck().equals("0")){
                    holder.txtTen.setTextColor(Color.RED);
                }
                holder.txtSoBuoi.setText(item.getSobuoi());
                break;
            }
            default:{
                ViewHolder holder;
                /*if (view==null){*/
                holder=new ItemHS_GVApdater.ViewHolder();
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view=inflater.inflate(layout,null);

                holder.txtTen=(TextView) view.findViewById(R.id.textviewHoTen);
                holder.txtSDT=(TextView) view.findViewById(R.id.textviewSDT);

                view.setTag(holder);
                /*}else {
                    holder = (ViewHolder) view.getTag();
                }*/
                ItemHS_GV item=itemList.get(i);
                holder.txtTen.setText(item.getHoten());
                holder.txtSDT.setText(item.getSdt());
                if(item.getCheck().equals("1")){
                    holder.txtTen.setTextColor(Color.GREEN);
                } else if(item.getCheck().equals("0")){
                    holder.txtTen.setTextColor(Color.RED);
                }
                break;
            }
        }
        return view;
    }
}
