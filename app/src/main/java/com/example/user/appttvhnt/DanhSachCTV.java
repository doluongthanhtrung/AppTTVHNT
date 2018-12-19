package com.example.user.appttvhnt;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DanhSachCTV extends ListFragment {
    ArrayList<ItemHS_GV> arrayList;
    ItemHS_GVApdater apdater;
    TextView TieuDe;
    String manv,sdt,clb;
    EditText HoTen,SDT;
    Spinner CLB;
    Button btLuu,btHuy;
    RadioButton Co, Khong;
    TextView TenSV;
    EditText LyDo;
    Button Luu,Huy;
    String trangthai;
    ThemCTVClick click;
    final String URLGETDATACTV="http://thanhtrungcnttk15.atwebpages.com/getdataCTV.php";
    final String URLCAPNHATNV="http://thanhtrungcnttk15.atwebpages.com/capnhatNV.php";
    final String URLXOANV="http://thanhtrungcnttk15.atwebpages.com/xoaNV.php";
    final String URLDIEMDANHNV="http://thanhtrungcnttk15.atwebpages.com/insertDiemDanhNV.php";
    int ltk;

    public interface ThemCTVClick{
        public void ThemCTVClick(String them);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        click= (ThemCTVClick) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.danh_sach_ctv, container, false);


        arrayList=new ArrayList<>();
        Toast.makeText(getActivity(), "Đang tải dữ liệu...", Toast.LENGTH_SHORT).show();
        getData();

        ltk=getArguments().getInt("LoaiTaiKhoan");
        if (ltk==1){
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabctv);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Bạn không thể thêm giảng viên mới. Chỉ Admin mới có thể làm điều này!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        } else{
            FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabctv);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    click.ThemCTVClick("CTV");
                }
            });
        }

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if(ltk==0) {
            showmenu(arrayList.get(position), l,position);
        } else Toast.makeText(getActivity(),"Copy số điện thoại",Toast.LENGTH_LONG).show();
        super.onListItemClick(l, v, position, id);
    }

    private void showmenu(final ItemHS_GV itemHS_gv, ListView lv, final int position) {
        PopupMenu popupMenu=new PopupMenu(getActivity(),lv);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item_cap_nhat:
                        DialogCapNhat(itemHS_gv);
                        break;
                    case R.id.item_xoa:
                        DialogXoa(itemHS_gv);
                        break;
                    case R.id.item_diem_danh:
                        DialogDiemDanh(position);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void DialogCapNhat(final ItemHS_GV itemHS_gv) {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.cap_nhat_gv_sv);

        SDT=(EditText) dialog.findViewById(R.id.editTextSoDienThoai);
        CLB=(Spinner) dialog.findViewById(R.id.spinnerclb);
        btLuu=(Button) dialog.findViewById(R.id.buttonLuu);
        btHuy=(Button) dialog.findViewById(R.id.buttonHuy);
        TieuDe=(TextView) dialog.findViewById(R.id.textViewCapNhat);
        CLB.setEnabled(false);
        TieuDe.setText("Cập nhật CTV");
        btLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manv=itemHS_gv.getId();
                sdt=SDT.getText().toString();
                CapNhatNV(manv,sdt);
                getData();
                dialog.dismiss();
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

    private void CapNhatNV(final String manv, final String sdt) {
        RequestQueue requestQueue=Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLCAPNHATNV,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(getActivity(),"Cập nhập thành công",Toast.LENGTH_SHORT).show();
                            getData();
                        } else Toast.makeText(getActivity(),"Lỗi cập nhật",Toast.LENGTH_SHORT).show();
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
                params.put("MaNV",manv);
                params.put("SDT",sdt);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void DialogXoa(final ItemHS_GV itemHS_gv) {
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setTitle("Xác nhận");
        dialog.setMessage("Bạn có muốn xóa "+itemHS_gv.getHoten()+" không?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteNV(itemHS_gv.getId());
                getData();
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void DeleteNV(final String id) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLXOANV,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("success")){
                            Toast.makeText(getActivity(), "Xóa thành công", Toast.LENGTH_LONG).show();
                            getData();
                        } else Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi hệ thống", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("MaNV",id);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getData() {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATACTV, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayList.clear();
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                ItemHS_GV gv=new ItemHS_GV();
                                gv.setId(object.getString("MaNV"));
                                gv.setHoten(object.getString("HoTen"));
                                gv.setSdt(object.getString("SDT"));
                                arrayList.add(gv);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        apdater=new ItemHS_GVApdater(getActivity(),R.layout.item_hs_gv,arrayList);
                        setListAdapter(apdater);
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

    private void DialogDiemDanh(final int position) {
        final Dialog dialog=new Dialog(getActivity());
        dialog.setContentView(R.layout.diem_danh_sv);

        Co=(RadioButton) dialog.findViewById(R.id.radio_co);
        Khong=(RadioButton) dialog.findViewById(R.id.radio_khong);
        LyDo=(EditText) dialog.findViewById(R.id.editTextLyDo);
        TenSV=(TextView) dialog.findViewById(R.id.textViewTenSV);
        Luu=(Button) dialog.findViewById(R.id.buttonLuu);
        Huy=(Button) dialog.findViewById(R.id.buttonHuy);

        TenSV.setText(arrayList.get(position).getHoten());
        manv=arrayList.get(position).getId();
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
                StringRequest stringRequest=new StringRequest(Request.Method.POST, URLDIEMDANHNV,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.trim().equals("success")){
                                    arrayList.get(position).setCheck(trangthai);
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
                        params.put("MaNV",manv);
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
}
