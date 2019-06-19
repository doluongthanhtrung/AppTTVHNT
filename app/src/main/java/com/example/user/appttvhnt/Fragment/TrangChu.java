package com.example.user.appttvhnt.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.appttvhnt.Model.ItemApdater;
import com.example.user.appttvhnt.Model.ItemTrangChu;
import com.example.user.appttvhnt.R;

import java.util.ArrayList;

public class TrangChu extends ListFragment {

    ArrayList<ItemTrangChu> arrayItem;
    ItemApdater apdater;
    ListItemSelect listItemSelect;

    public interface ListItemSelect{
        public void onSelecitem(int i);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        listItemSelect=(ListItemSelect) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trang_chu, container, false);
        arrayItem=new ArrayList<>();
        AddArray();
        apdater =new ItemApdater(getActivity(),R.layout.item_trang_chu,arrayItem);
        setListAdapter(apdater);
        return view;
    }


    private void AddArray() {
        arrayItem.add(new ItemTrangChu("Nhật ký sinh hoat",R.drawable.icon5));
        arrayItem.add(new ItemTrangChu("Danh sách giảng viên",R.drawable.icon2));
        arrayItem.add(new ItemTrangChu("Danh sách lớp",R.drawable.icon1));
        arrayItem.add(new ItemTrangChu("Dữ liệu ISO",R.drawable.icons8_iso_40));
        arrayItem.add(new ItemTrangChu("Lịch",R.drawable.icon3));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listItemSelect.onSelecitem(position);
    }
}
