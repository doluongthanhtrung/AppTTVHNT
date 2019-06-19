package com.example.user.appttvhnt.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
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
import com.example.user.appttvhnt.Model.ItemHS_GV;
import com.example.user.appttvhnt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ThemGV extends Fragment {
    TextView TieuDe;
    Button btLuu, btHuy;
    EditText MaNV,HoTen,SDT;
    RadioButton CTV,GV;
    String manv,hoten,clb,sdt;
    OnClickButtonThemNV click;
    ArrayList<ItemHS_GV> clbList;
    Spinner spinner;
    final String URLTHEMNV="http://thanhtrungcnttk15.atwebpages.com/insertNV.php";
    final String URLGETDATACLB="http://thanhtrungcnttk15.atwebpages.com/getdataCLB.php";

    public interface OnClickButtonThemNV{
        void ButtonThemNV(String chucvu);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        click= (OnClickButtonThemNV) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.them_gv, container, false);

        AnhXa(view);
        Bundle bundle=getArguments();
        final String them=bundle.getString("THEM");
        if(them.equals("CTV")){
            TieuDe.setText("Thêm mới CTV");
            CTV.setChecked(true);
            spinner.setEnabled(false);
        } else GV.setChecked(true);

        clbList=new ArrayList<ItemHS_GV>();
        getDataCLB();

        btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                manv=MaNV.getText().toString();
                hoten=HoTen.getText().toString();
                sdt= SDT.getText().toString();

                ItemHS_GV nv=new ItemHS_GV();

                if(manv.equals("")||hoten.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng điền đầy đủ thông tin!",Toast.LENGTH_LONG).show();
                } else {
                        if(GV.isChecked()){
                        if(spinner.getSelectedItemPosition()==0){
                            Toast.makeText(getActivity(),"Vui lòng chọn CLB",Toast.LENGTH_LONG).show();
                        } else {
                            nv.setId(manv);
                            nv.setHoten(hoten);
                            nv.setSdt(sdt);
                            nv.setChucvu("GV");
                            nv.setClb(clbList.get(spinner.getSelectedItemPosition()-1).getId());
                            AddNV(nv);
                        }
                    } else if(CTV.isChecked()){
                        nv.setId(manv);
                        nv.setHoten(hoten);
                        nv.setSdt(sdt);
                        nv.setChucvu("CTV");
                        AddNV(nv);
                    } else {
                        nv.setId(manv);
                        nv.setHoten(hoten);
                        nv.setSdt(sdt);
                        nv.setChucvu("NV");
                        AddNV(nv);
                    }
                }
            }
        });
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.ButtonThemNV(them);
            }
        });

        return view;
    }

    private void AddNV(final ItemHS_GV nv) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLTHEMNV,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("success")){
                            Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            click.ButtonThemNV(nv.getChucvu());
                        } else Toast.makeText(getActivity(), "Lỗi thêm ", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("MaNV",nv.getId());
                params.put("HoTen",nv.getHoten());
                params.put("ChucVu",nv.getChucvu());
                params.put("SDT",nv.getSdt());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(View view) {
        btLuu=(Button) view.findViewById(R.id.btLuu);
        btHuy=(Button) view.findViewById(R.id.btHuy);
        MaNV=(EditText) view.findViewById(R.id.editTextID);
        HoTen=(EditText) view.findViewById(R.id.editTextHoTen);
        SDT=(EditText) view.findViewById(R.id.editTextSDT);
        CTV=(RadioButton) view.findViewById(R.id.radioCTV);
        GV=(RadioButton) view.findViewById(R.id.radioGV);
        spinner=(Spinner) view.findViewById(R.id.spinner2);
        TieuDe=(TextView) view.findViewById(R.id.textViewTieDeThem);
    }

    private void getDataCLB() {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATACLB, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        clbList.clear();
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                ItemHS_GV clb=new ItemHS_GV();
                                clb.setId(object.getString("MaCLB"));
                                clb.setHoten(object.getString("TenCLB"));
                                clbList.add(clb);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayList<String> arrayList=new ArrayList<String>();
                        arrayList.add("Câu lạc bộ");
                        for(int i=0;i<clbList.size();i++){
                            arrayList.add(clbList.get(i).getHoten());
                        }
                        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,arrayList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
