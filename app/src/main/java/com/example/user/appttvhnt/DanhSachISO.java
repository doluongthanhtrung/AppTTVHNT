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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class DanhSachISO extends ListFragment{

    ArrayList<ItemHS_GV> arrayList;
    ItemHS_GVApdater apdater;
    final String URLGETDATAISO="http://thanhtrungcnttk15.atwebpages.com/getDataISO.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.danh_sach_iso,container,false);

        Toast.makeText(getActivity(), "Đang tải dữ liệu...", Toast.LENGTH_SHORT).show();

        arrayList=new ArrayList<>();
        getDSISO();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabiso);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogThem();
            }
        });
        return view;
    }

    private void getDSISO() {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATAISO, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayList.clear();
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                ItemHS_GV iso=new ItemHS_GV();
                                iso.setHoten(object.getString("TenDanhMuc"));
                                iso.setSdt(object.getString("Trang"));
                                arrayList.add(iso);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        apdater=new ItemHS_GVApdater(getActivity(),R.layout.item_iso,arrayList);
                        setListAdapter(apdater);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
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
                Toast.makeText(getActivity(), "Tính năng đang phát triển..", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
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
