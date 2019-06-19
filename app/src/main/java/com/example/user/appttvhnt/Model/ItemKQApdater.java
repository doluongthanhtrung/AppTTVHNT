package com.example.user.appttvhnt.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.appttvhnt.R;

import java.util.List;

public class ItemKQApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ItemHS_GV> itemList;

    public ItemKQApdater(Context context, int layout, List<ItemHS_GV> itemList) {
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
        TextView txtCLB;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder.txtTen=(TextView) view.findViewById(R.id.textviewHoTen);
            holder.txtSDT=(TextView) view.findViewById(R.id.textviewSDT);
            holder.txtCLB=(TextView) view.findViewById(R.id.textViewCLB);

            view.setTag(holder);
        }else {
            holder = (ViewHolder) view.getTag();
        }
        ItemHS_GV item=itemList.get(i);
        holder.txtTen.setText(item.getHoten());
        holder.txtSDT.setText(item.getSdt());
        holder.txtCLB.setText(item.getClb());
        return view;
    }
}
