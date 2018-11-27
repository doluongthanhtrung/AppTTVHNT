package com.example.user.appttvhnt;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DanhSachLopChiTiet extends ListFragment {

    ArrayList<ItemHS_GV> arraySVList;
    ItemHS_GVApdater apdater;
    ThemSV themsv;
    RadioButton Co, Khong;
    TextView TenSV;
    EditText LyDo;
    Button Luu,Huy;
    String mssv, id;
    TextView tvCLB,tvGV;
    String trangthai="2";
    final String URLGETDATASV="http://thanhtrungcnttk15.atwebpages.com/getDSSV.php";
    final String URLDIEMDANH="http://thanhtrungcnttk15.atwebpages.com/insertDiemDanh.php";


    public interface ThemSV{
        public void ClickThemSV();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        themsv= (ThemSV) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.danh_sach_chi_tiet,container,false);

        id=getArguments().getString("ID");
        String tenCLB=getArguments().getString("TENCLB");
        String tenGV=getArguments().getString("TENGV");

        arraySVList=new ArrayList<>();
        getdataSV(id);

        tvCLB=(TextView) view.findViewById(R.id.textViewCLB);
        tvGV=(TextView) view.findViewById(R.id.textViewGV);
        tvCLB.setText(tenCLB);
        tvGV.setText(tenGV);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabsv);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                themsv.ClickThemSV();
            }
        });

        return view;
    }

    private void getdataSV(final String maclb){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATASV, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arraySVList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                Random rd=new Random();
                                String buoi= String.valueOf(rd.nextInt(8));
                                ItemHS_GV sv=new ItemHS_GV();
                                sv.setId(object.getString("MSSV"));
                                sv.setHoten(object.getString("HoTenSV").trim());
                                sv.setSdt(object.getString("SDT"));
                                sv.setChucvu(object.getString("MaCLB"));
                                sv.setSobuoi(buoi);
                                if(sv.getChucvu().equals(maclb)) {
                                    arraySVList.add(sv);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        apdater= new ItemHS_GVApdater(getActivity(),R.layout.item_ds_chi_tiet,arraySVList);
                        setListAdapter(apdater);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Lỗi hệ thống!"+error.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("Loi",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        DialogDiemDanh(position);
    }

    private void DialogDiemDanh(final int position) {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.diem_danh_sv);

        Co=(RadioButton) dialog.findViewById(R.id.radio_co);
        Khong=(RadioButton) dialog.findViewById(R.id.radio_khong);
        LyDo=(EditText) dialog.findViewById(R.id.editTextLyDo);
        TenSV=(TextView) dialog.findViewById(R.id.textViewTenSV);
        Luu=(Button) dialog.findViewById(R.id.buttonLuu);
        Huy=(Button) dialog.findViewById(R.id.buttonHuy);

        TenSV.setText(arraySVList.get(position).getHoten());
        mssv=arraySVList.get(position).getId();
        Calendar c=Calendar.getInstance();
        final String ngay=c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR);

        Luu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Co.isChecked()){
                    trangthai="1";
                } else if(Khong.isChecked()){
                    trangthai="0";
                }
                RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URLDIEMDANH,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().equals("success")){
                                    arraySVList.get(position).setCheck(trangthai);
                                    arraySVList.get(position).setSobuoi(String.valueOf(Integer.parseInt(arraySVList.get(position).getSobuoi())+1));
                                    apdater.notifyDataSetChanged();
                                    Toast.makeText(getActivity(),"Lưu thành công",Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                } else Toast.makeText(getActivity(),"Lưu không thành công",Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(), "Lỗi: "+error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params=new HashMap<>();
                        params.put("MaCLB",id);
                        params.put("MSSV",mssv);
                        params.put("Ngay",ngay);
                        params.put("TrangThai",trangthai);
                        params.put("LyDo",LyDo.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });

        Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
