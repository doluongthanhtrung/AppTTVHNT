package com.example.user.appttvhnt.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.appttvhnt.R;
import com.example.user.appttvhnt.Model.TAIKHOAN;

import java.util.HashMap;
import java.util.Map;

public class ThemTK extends Fragment {

    Button btThem, btHuy;
    EditText TenDangNhap,MatKhau,ReMatKhau;
    RadioButton Admin,User;
    String tendangnhap,matkhau,rematkhau,loaitaikhoan;
    OnClickButtonTK onClickButtonTK;
    final String URLTHEMTK="http://thanhtrungcnttk15.atwebpages.com/insertTK.php";

    public interface OnClickButtonTK{
        public void ClickButton();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onClickButtonTK=(OnClickButtonTK) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.them_tai_khoan, container, false);

        AnhXa(view);
        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tendangnhap=TenDangNhap.getText().toString();
                matkhau=MatKhau.getText().toString();
                rematkhau=ReMatKhau.getText().toString();
                if(Admin.isChecked())
                    loaitaikhoan="Admin";
                else loaitaikhoan="User";
                if(tendangnhap.equals("")||matkhau.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng điền tên đăng nhập và mật khẩu!",Toast.LENGTH_LONG).show();
                    TenDangNhap.forceLayout();
                }else {
                    if (!matkhau.equals(rematkhau)) {
                        Toast.makeText(getActivity(), "Mật khẩu nhập lại không khớp với mật khẩu. Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                        ReMatKhau.setText("");
                        ReMatKhau.forceLayout();
                    } else {
                        TAIKHOAN tk = new TAIKHOAN();
                        tk.setTendangnhap(tendangnhap);
                        tk.setMatkhau(matkhau);
                        tk.setLoaitaikhoan(loaitaikhoan);
                        AddTaiKhoan(tk);
                    }
                }
            }
        });
        btHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickButtonTK.ClickButton();
            }
        });
        return view;
    }

    private void AddTaiKhoan(final TAIKHOAN tk) {
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLTHEMTK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("success")){
                            Toast.makeText(getActivity(), "Thêm tài khoản thành công", Toast.LENGTH_LONG).show();
                            onClickButtonTK.ClickButton();
                        } else Toast.makeText(getActivity(), "Lỗi thêm tài khoản", Toast.LENGTH_LONG).show();
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

    private void AnhXa(View view) {
        btThem=(Button) view.findViewById(R.id.button_them);
        btHuy=(Button) view.findViewById(R.id.button_huy);
        TenDangNhap=(EditText) view.findViewById(R.id.editTextTenDangNhap);
        MatKhau=(EditText) view.findViewById(R.id.editTextMatKhau);
        ReMatKhau=(EditText) view.findViewById(R.id.editTextMatKhau2);
        Admin=(RadioButton) view.findViewById(R.id.radioButtonAdmin);
        User=(RadioButton) view.findViewById(R.id.radioButtonUser);
    }
}
