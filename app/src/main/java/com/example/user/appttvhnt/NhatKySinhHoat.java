package com.example.user.appttvhnt;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NhatKySinhHoat extends Fragment {

    ListView lv;
    NhatKyApdater adapter;
    ArrayList<NhatKy> arrayList;
    ArrayList<ItemHS_GV> clbList;
    ArrayList<String> CLBList;
    TextView tvNgay;
    EditText edtNoiDung;
    Spinner spinnerCLB;
    Button btThem,btHuy;
    final String URLGETDATACLB="http://thanhtrungcnttk15.atwebpages.com/getdataCLB.php";
    final String URLGETDATANK="http://thanhtrungcnttk15.atwebpages.com/getDataNhatKy.php";
    final String URLTHEMNK="http://thanhtrungcnttk15.atwebpages.com/insertNhatKy.php";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.nhat_ky_sinh_hoat,container,false);
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabnhatky);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogThemNK();
            }
        });
        lv=(ListView) view.findViewById(R.id.listViewNhatKy);
        clbList=new ArrayList<>();
        arrayList=new ArrayList<>();
        Toast.makeText(getActivity(), "Đang tải dũ liệu...", Toast.LENGTH_SHORT).show();
        getData();
        return view;
    }

    private void DialogThemNK() {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.them_nhat_ky);

        AnhXa(dialog);

        Calendar c=Calendar.getInstance();

        tvNgay.setText(""+c.get(Calendar.DAY_OF_MONTH)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR));
        CLBList=new ArrayList<>();
        CLBList.add("Câu lạc bộ");
        for(int i=0;i<clbList.size();i++){
            CLBList.add(clbList.get(i).getHoten());
        }
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,CLBList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCLB.setAdapter(adapter);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(spinnerCLB.getSelectedItemPosition()==0||edtNoiDung.getText().equals("")){
                    Toast.makeText(getActivity(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    String ngay=tvNgay.getText().toString();
                    String nd=edtNoiDung.getText().toString();
                    String maclb=clbList.get(spinnerCLB.getSelectedItemPosition()-1).getId();
                    ThemNhatKy(maclb,nd,ngay);
                    dialog.dismiss();
                }
            }
        });
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void ThemNhatKy(final String maclb, final String nd, final String ngay) {
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLTHEMNK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(getActivity(), "Thêm thành công!", Toast.LENGTH_SHORT).show();
                            getData();
                        } else Toast.makeText(getActivity(), "Thêm không thành công!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi: "+ error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parmas=new HashMap<>();
                parmas.put("MaCLB",maclb);
                parmas.put("NoiDung",nd);
                parmas.put("Ngay",ngay);
                return parmas;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(Dialog dialog){
        btThem=(Button) dialog.findViewById(R.id.buttonThemNK);
        btHuy=(Button) dialog.findViewById(R.id.buttonHuyNK);
        spinnerCLB=(Spinner) dialog.findViewById(R.id.spinnerCLB);
        edtNoiDung=(EditText) dialog.findViewById(R.id.editTextND);
        tvNgay=(TextView) dialog.findViewById(R.id.textViewNgay);
    }

    private void getData() {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATACLB, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
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
                        getDataNK();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi hệ thống!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void getDataNK() {
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Request.Method.GET, URLGETDATANK, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                NhatKy nk=new NhatKy();
                                for(int j=0;j<clbList.size();j++){
                                    if(object.getString("MaCLB").equals(clbList.get(j).getId())){
                                        nk.setTenCLB(clbList.get(j).getHoten());
                                        break;
                                    }
                                }
                                nk.setNoiDung(object.getString("NoiDung"));
                                nk.setNgay(object.getString("Ngay"));
                                arrayList.add(nk);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Toast.makeText(getActivity(), "Tải dữ liệu thành công ", Toast.LENGTH_SHORT).show();
                        adapter=new NhatKyApdater(getActivity(),R.layout.item_kq_clb,arrayList);
                        lv.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi hệ thống!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
