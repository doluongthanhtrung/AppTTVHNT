package com.example.user.appttvhnt;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuanLyTK extends Fragment {
    ArrayList<TAIKHOAN> arrayList;
    ListView lv;
    TaiKhoanApdater apdater;
    Button btThem, btHuy;
    EditText MatKhau,ReMatKhau;
    RadioButton Admin,User;
    TextView TieuDe, TenDangNhap;
    String tendangnhap,matkhau,rematkhau,loaitaikhoan;
    ButtonThemClick click;
    final String URLGETDATATK="http://thanhtrungcnttk15.atwebpages.com/getdataTK.php";
    final String URLCAPNHATTK="http://thanhtrungcnttk15.atwebpages.com/capnhatTK.php";
    final String URLXOATK="http://thanhtrungcnttk15.atwebpages.com/xoaTK.php";

    public interface ButtonThemClick{
        void fabClick();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        click=(ButtonThemClick) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.quan_ly_tk, container, false);



        lv=(ListView) view.findViewById(R.id.listViewTK);

        arrayList=new ArrayList<>();
        getdataTK();

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fabtk);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click.fabClick();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showmenu(arrayList.get(position));
            }
        });

        return view;
    }

    private void showmenu(TAIKHOAN tk){
        PopupMenu popupMenu=new PopupMenu(getActivity(),lv);
        popupMenu.getMenuInflater().inflate(R.menu.context_menu,popupMenu.getMenu());
        final TAIKHOAN taikhoan=tk;
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch(item.getItemId()){
                    case R.id.item_xoa_tai_khoan:
                        DialogXoa(taikhoan.getTendangnhap());
                        break;
                    case R.id.item_cap_nhat:
                        DialogSua(taikhoan);
                        break;
                }

                return false;
            }
        });
        popupMenu.show();
    }

    private void DialogXoa(String TenDangNhap) {
        final String tendangnhap=TenDangNhap;
        AlertDialog.Builder dialog=new AlertDialog.Builder(getActivity());
        dialog.setTitle("Xác nhận");
        dialog.setMessage("Bạn có muốn xóa tài khoản "+TenDangNhap+" không?");
        dialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DeleteTaiKhoan(tendangnhap);
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

    private void DeleteTaiKhoan(final String tendangnhap) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLXOATK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("success")){
                            Toast.makeText(getActivity(), "Xóa tài khoản thành công", Toast.LENGTH_LONG).show();
                            getdataTK();
                        } else Toast.makeText(getActivity(), "Lỗi xóa tài khoản", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("TenDangNhap",tendangnhap);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void getdata(){
        apdater=new TaiKhoanApdater(getActivity(),R.layout.item_tai_khoan,DangNhapActivity.arrayList);
        lv.setAdapter(apdater);
    }
    private void getdataTK(){
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATATK, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrayList.clear();
                        for (int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                TAIKHOAN tk=new TAIKHOAN();
                                tk.setTendangnhap(object.getString("TenDangNhap"));
                                tk.setMatkhau(object.getString("MatKhau"));
                                if(object.getInt("LoaiTaiKhoan")==0)
                                    tk.setLoaitaikhoan("Admin");
                                else tk.setLoaitaikhoan("User");
                                arrayList.add(tk);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        apdater=new TaiKhoanApdater(getActivity(),R.layout.item_tai_khoan,DangNhapActivity.arrayList);
                        lv.setAdapter(apdater);
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
    private void DialogSua(TAIKHOAN tk) {

        Dialog dialog=new Dialog(getActivity());
        tendangnhap=tk.getTendangnhap();
        dialog.setContentView(R.layout.sua_tai_khoan);

        AnhXa(dialog);
        TenDangNhap.setText(tk.getTendangnhap());
        if(tk.getLoaitaikhoan().equals("Admin")){
            Admin.setChecked(true);
        } else User.setChecked(true);

        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkhau=MatKhau.getText().toString();
                rematkhau=ReMatKhau.getText().toString();
                if(Admin.isChecked())
                    loaitaikhoan="Admin";
                else loaitaikhoan="User";
                if(matkhau.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập mật khẩu!",Toast.LENGTH_SHORT).show();
                    MatKhau.forceLayout();
                }else {
                    if (!matkhau.equals(rematkhau)) {
                        Toast.makeText(getActivity(), "Mật khẩu nhập lại không khớp với mật khẩu. Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                        ReMatKhau.setText("");
                        ReMatKhau.forceLayout();
                    } else {
                        TAIKHOAN tkSua = new TAIKHOAN();
                        tkSua.setTendangnhap(tendangnhap);
                        tkSua.setMatkhau(matkhau);
                        tkSua.setLoaitaikhoan(loaitaikhoan);
                        UpdateTaiKhoan(tkSua);
                        //dialog.dismiss();
                    }
                }
                    }
                });
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void UpdateTaiKhoan(final TAIKHOAN tk) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLCAPNHATTK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("success")){
                            Toast.makeText(getActivity(), "Cập nhật tài khoản thành công", Toast.LENGTH_LONG).show();
                            getdataTK();
                        } else Toast.makeText(getActivity(), "Lỗi cập nhật tài khoản", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi", Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("TenDangNhap",tk.getTendangnhap());
                params.put("MatKhau",tk.getMatkhau());
                if(tk.getLoaitaikhoan().equals("Admin")){
                    params.put("LoaiTaiKhoan","0");
                }
                else params.put("LoaiTaiKhoan","1");
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void AnhXa(Dialog dialog) {
        btThem=(Button) dialog.findViewById(R.id.button_them);
        btHuy=(Button) dialog.findViewById(R.id.button_huy);
        TenDangNhap=(TextView) dialog.findViewById(R.id.textViewTDN);
        MatKhau=(EditText) dialog.findViewById(R.id.editTextMatKhau);
        ReMatKhau=(EditText) dialog.findViewById(R.id.editTextMatKhau2);
        Admin=(RadioButton) dialog.findViewById(R.id.radioButtonAdmin);
        User=(RadioButton) dialog.findViewById(R.id.radioButtonUser);
        TieuDe=(TextView) dialog.findViewById(R.id.textView4);
    }
}
