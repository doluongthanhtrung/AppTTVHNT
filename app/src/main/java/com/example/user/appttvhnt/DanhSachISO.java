package com.example.user.appttvhnt;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DanhSachISO extends ListFragment{

    ArrayList<ItemHS_GV> arrayList;
    ItemHS_GVApdater apdater;
    DemoDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.danh_sach_iso,container,false);
        database=new DemoDatabase(getActivity());
        database.addDefualtdataIfNeed();
        arrayList=database.getDSISO();

        apdater=new ItemHS_GVApdater(getActivity(),R.layout.item_iso,arrayList);
        setListAdapter(apdater);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabiso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogThem();
            }
        });
        return view;
    }
    private void DialogThem(){
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.them_danh_muc_iso);
        Button btnLuu=(Button) dialog.findViewById(R.id.buttonLuu);
        Button btnHuy=(Button) dialog.findViewById(R.id.buttonHuy);
        EditText tvND=(EditText) dialog.findViewById(R.id.editTextND);
        EditText tvTrang=(EditText) dialog.findViewById(R.id.editTextTrang);
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
