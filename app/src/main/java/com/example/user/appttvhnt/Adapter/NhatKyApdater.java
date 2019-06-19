package com.example.user.appttvhnt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.appttvhnt.Model.NhatKy;
import com.example.user.appttvhnt.R;

import java.util.List;

public class NhatKyApdater extends BaseAdapter {
    private Context context;
    private int layout;
    private List<NhatKy> itemList;

    public NhatKyApdater(Context context, int layout, List<NhatKy> itemList) {
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
        TextView txtNgay;
        TextView txtCLB;
        TextView txtND;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder.txtNgay=(TextView) view.findViewById(R.id.textViewNgay);
            holder.txtCLB=(TextView) view.findViewById(R.id.textViewCLB);
            holder.txtND=(TextView) view.findViewById(R.id.textViewND);

            view.setTag(holder);
        } else {
            holder= (ViewHolder) view.getTag();
        }
        NhatKy item=itemList.get(i);
        holder.txtNgay.setText(item.getNgay());
        holder.txtCLB.setText(item.getTenCLB());
        holder.txtND.setText(item.getNoiDung());
        return view;
    }
}
