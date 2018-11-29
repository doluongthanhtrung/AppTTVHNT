package com.example.user.appttvhnt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DanhSachLop extends Fragment{

    ArrayList<LichSinhHoat> CLBbyLSH;
    ArrayList<ItemHS_GV> clblist;
    ArrayList<String> CLBList,thu;
    ArrayAdapter adapter;
    ListView lv;
    OnClickTenCLB onClickTenCLB;
    FloatingActionButton fabLop,fabThem,fabXoa;
    Button LuuCLB, HuyCLB,XoaCLb,HuyXoaCLB;
    EditText MaCLB,TenCLB;
    Spinner CLB, THU;
    OvershootInterpolator interpolator=new OvershootInterpolator();
    boolean isMenuOpen;
    int ltk;
    final String URLGETDATASV="http://thanhtrungcnttk15.atwebpages.com/getDSSV.php";
    final String URLGETDATACLB="http://thanhtrungcnttk15.atwebpages.com/getdataCLB.php";
    final String URLTHEMCLB="http://thanhtrungcnttk15.atwebpages.com/insertCLB.php";
    final String URLXOACLB="http://thanhtrungcnttk15.atwebpages.com/xoaCLB.php";

    public interface OnClickTenCLB{
        public void onSelectTenCLB(String id, String TenCLB);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onClickTenCLB=(OnClickTenCLB) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.danh_sach_lop, container, false);

        Toast.makeText(getActivity(), "Đang tải dữ liệu...", Toast.LENGTH_SHORT).show();
        lv=(ListView) view.findViewById(R.id.listViewLop);
        THU=(Spinner) view.findViewById(R.id.spinnerThu);

        thu=new ArrayList<>();
        getdataThu();
        ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,thu);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        THU.setAdapter(arrayAdapter);

        //arraySVList=new ArrayList<>();
        //CLBbyLSH=new ArrayList<>();
        clblist=new ArrayList<>();
        getdata();

        ltk=getArguments().getInt("LoaiTaiKhoan");
        if(ltk==1) {

            fabLop = (FloatingActionButton) view.findViewById(R.id.fabLop);
            fabThem = (FloatingActionButton) view.findViewById(R.id.fabThem);
            fabXoa = (FloatingActionButton) view.findViewById(R.id.fabXoa);

            fabThem.setAlpha(0f);
            fabXoa.setAlpha(0f);

            fabThem.setTranslationY(100f);
            fabXoa.setTranslationY(100f);

            fabLop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Chỉ Admin mới có thể thực hiện chức năng này!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }else {
            fabLop = (FloatingActionButton) view.findViewById(R.id.fabLop);
            fabThem = (FloatingActionButton) view.findViewById(R.id.fabThem);
            fabXoa = (FloatingActionButton) view.findViewById(R.id.fabXoa);

            fabThem.setAlpha(0f);
            fabXoa.setAlpha(0f);

            fabThem.setTranslationY(100f);
            fabXoa.setTranslationY(100f);

            fabLop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isMenuOpen){
                        closeMenu();
                    }
                    else {
                        openMenu();
                    }

                }
            });
            fabThem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogThemCLB();
                }
            });

            fabXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogXoaCLB();
                }
            });
        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "Vui lòng chờ...", Toast.LENGTH_SHORT).show();
                onClickTenCLB.onSelectTenCLB(clblist.get(position).getId(),clblist.get(position).getHoten());
            }
        });

        return view;
    }

    private void getdataThu() {
        thu.add("Tất cả");
        thu.add("Chủ nhật");
        thu.add("Thứ 2");
        thu.add("Thứ 3");
        thu.add("Thứ 4");
        thu.add("Thứ 5");
        thu.add("Thứ 6");
        thu.add("Thứ 7");
    }

    /*private void getdataSV(){
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
                                arraySVList.add(sv);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        //getdataCLB(0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),"Lỗi hệ thống: "+error.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("Loi",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }*/

    private void getdata() {
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATACLB, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        clblist.clear();
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                ItemHS_GV clb=new ItemHS_GV();
                                clb.setId(object.getString("MaCLB"));
                                clb.setHoten(object.getString("TenCLB"));
                                clblist.add(clb);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        CLBList=new ArrayList<>();
                        for(int i=0;i<clblist.size();i++){
                            CLBList.add(clblist.get(i).getHoten());
                        }
                        adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,CLBList);
                        lv.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi hệ thống: "+error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void DialogXoaCLB() {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.xoa_clb);

        XoaCLb=(Button) dialog.findViewById(R.id.buttonXoaCLB);
        HuyXoaCLB=(Button) dialog.findViewById(R.id.buttonHuyXoaCLB);
        CLB=(Spinner) dialog.findViewById(R.id.spinnerXoaCLB);

        ArrayAdapter spinneradapter=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,CLBList);
        spinneradapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CLB.setAdapter(spinneradapter);
        XoaCLb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URLXOACLB,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().equals("success")){
                                    Toast.makeText(getActivity(),"Xóa CLB thành công!",Toast.LENGTH_SHORT).show();
                                    getdata();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(getActivity(),"Lỗi. Xóa CLB không thành công",Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(),"Lỗi hệ thống. Xóa CLB không thành công",Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params=new HashMap<>();
                        params.put("MaCLB",clblist.get(CLB.getSelectedItemPosition()).getId());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        HuyXoaCLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void DialogThemCLB() {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.them_clb);

        LuuCLB=(Button) dialog.findViewById(R.id.buttonThemCLB);
        HuyCLB=(Button) dialog.findViewById(R.id.buttonHuyCLB);
        MaCLB=(EditText) dialog.findViewById(R.id.editTextMaCLB);
        TenCLB=(EditText) dialog.findViewById(R.id.editTextTenCLB);

        LuuCLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URLTHEMCLB,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().equals("success")){
                                    Toast.makeText(getActivity(),"Thêm thành công",Toast.LENGTH_SHORT).show();
                                    getdata();
                                    dialog.dismiss();
                                } else Toast.makeText(getActivity(),"Lỗi. Thêm không thành công",Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getActivity(),"Lỗi hệ thống",Toast.LENGTH_SHORT).show();
                            }
                        }
                ){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params=new HashMap<>();
                        params.put("MaCLB",MaCLB.getText().toString());
                        params.put("TenCLB",TenCLB.getText().toString());
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
        HuyCLB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void openMenu(){
        isMenuOpen= !isMenuOpen;

        fabLop.animate().setInterpolator(interpolator).rotation(45f).setDuration(300).start();

        fabThem.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fabXoa.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private void closeMenu(){
        isMenuOpen= !isMenuOpen;

        fabLop.animate().setInterpolator(interpolator).rotation(0f).setDuration(300).start();

        fabThem.animate().translationY(100f).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fabXoa.animate().translationY(100f).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
    }


}
