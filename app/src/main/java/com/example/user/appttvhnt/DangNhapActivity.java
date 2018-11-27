package com.example.user.appttvhnt;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DangNhapActivity extends Activity {

    EditText txttenDangNhap, txtmatKhau;
    Button btndangNhap, btnthoat;
    public static final String LOAITAIKHOAN="LoaiTaiKhoan";
    public static final String TENDANGNHAP="TenDangNhap";
    public static final String MATKHAU="MatKhau";
    final String URLGETDATATK="http://thanhtrungcnttk15.atwebpages.com/getdataTK.php";
    public static ArrayList<TAIKHOAN> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        //Xử lý sự kiện
        txttenDangNhap= (EditText) findViewById(R.id.txtDangNhap);
        txtmatKhau=(EditText) findViewById(R.id.txtMatKhau);
        btndangNhap=(Button) findViewById(R.id.btnDangNhap);
        btnthoat= (Button) findViewById(R.id.btnThoat);

        Toast.makeText(this, "Đang tải dữ liệu...", Toast.LENGTH_SHORT).show();

        arrayList=new ArrayList<>();
        getdataTK();

        btndangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenDangNhap=txttenDangNhap.getText().toString();
                String matKhau=txtmatKhau.getText().toString();
                //int kiemtradangnhap=KiemTraDangNhap(tenDangNhap,matKhau); //Code chính thức
                int kiemtradangnhap=0; //Code for debug (xóa)
                if(kiemtradangnhap==0){
                    Toast.makeText(DangNhapActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(DangNhapActivity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt(LOAITAIKHOAN,kiemtradangnhap);
                    bundle.putString(TENDANGNHAP,tenDangNhap);
                    bundle.putString(MATKHAU,matKhau);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    txttenDangNhap.setText("");
                    txtmatKhau.setText("");
                } else if(kiemtradangnhap==1){
                    Toast.makeText(DangNhapActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(DangNhapActivity.this,MainActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt(LOAITAIKHOAN,kiemtradangnhap);
                    bundle.putString(TENDANGNHAP,tenDangNhap);
                    bundle.putString(MATKHAU,matKhau);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    txttenDangNhap.setText("");
                    txtmatKhau.setText("");
                } else Toast.makeText(DangNhapActivity.this, "Lỗi đăng nhập. Tài khoản hoặc mật khẩu không trùng khớp",Toast.LENGTH_LONG).show();
                }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private int KiemTraDangNhap(String tenDangNhap, String matKhau) {
        for (int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).getTendangnhap().equals(tenDangNhap)&&arrayList.get(i).getMatkhau().equals(matKhau)){
                if(arrayList.get(i).getLoaitaikhoan().equals("Admin")){
                    return 0;
                }
                if(arrayList.get(i).getLoaitaikhoan().equals("User")){
                    return 1;
                }
            }
        }
        return 2;
    }

    private void getdataTK(){
        RequestQueue requestQueue= Volley.newRequestQueue(this);
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
                        Toast.makeText(DangNhapActivity.this, "Tải dữ liệu thành công! ", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DangNhapActivity.this,"Lỗi hệ thống!"+error.toString(),Toast.LENGTH_SHORT).show();
                        Log.d("Loi",error.toString());
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }
}
