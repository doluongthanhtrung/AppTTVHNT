package com.example.user.appttvhnt.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.appttvhnt.Fragment.LichSinhHoat;
import com.example.user.appttvhnt.R;

import java.util.List;

public class CLBListApdater extends BaseAdapter {

    private Context context;
    private int layout;
    private List<LichSinhHoat> itemList;

    public CLBListApdater(Context context, int layout, List<LichSinhHoat> itemList) {
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

    public class ViewHolder{
        TextView txtTenCLB,txtTong,txtSoLuong;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view==null){
            holder=new ViewHolder();
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder.txtTenCLB=(TextView) view.findViewById(R.id.textviewCLB);
            holder.txtSoLuong=(TextView) view.findViewById(R.id.textviewSoLuong);
            holder.txtTong=(TextView) view.findViewById(R.id.textviewTong);
            view.setTag(holder);
        } else holder= (ViewHolder) view.getTag();

        LichSinhHoat item=itemList.get(i);
        holder.txtTenCLB.setText(item.getTenCLB());
        holder.txtTong.setText(String.valueOf(item.getTong()));
        holder.txtSoLuong.setText(String.valueOf(item.getSoLuong()));
        return view;
    }
}
