package com.example.user.appttvhnt;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MenuRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.style.BulletSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        TrangChu.ListItemSelect,
        DanhSachLop.OnClickTenCLB,
        ThemTK.OnClickButtonTK,
        QuanLyTK.ButtonThemClick,ThemGV.OnClickButtonThemNV,
        DanhSachGV.DanhSachGVCLick,ThemSV.OnClickButtonThemSV,DanhSachLopChiTiet.ThemSV,DanhSachCTV.ThemCTVClick
{
    int loaitaikhoan;
    Button btThem, btHuy;
    EditText MatKhau,ReMatKhau,MatKhauHienTai;
    RadioButton Admin,User;
    TextView TieuDe;
    String tendangnhap,matkhau,rematkhau,LoaiTK;
    android.support.v7.app.ActionBar actionBar;
    ArrayList<ItemHS_GV> arrayGVList;
    TAIKHOAN tk;
    final String URLGETDATAGV="http://thanhtrungcnttk15.atwebpages.com/getDataTenGV.php";
    final String URLCAPNHATTK="http://thanhtrungcnttk15.atwebpages.com/capnhatTK.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        actionBar=getSupportActionBar();
        tk=new TAIKHOAN();
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        if(bundle!=null){
            loaitaikhoan=bundle.getInt(DangNhapActivity.LOAITAIKHOAN);
            tk.setTendangnhap(bundle.getString(DangNhapActivity.TENDANGNHAP));
            tk.setMatkhau(bundle.getString(DangNhapActivity.MATKHAU));
        }
        if(loaitaikhoan==0) {
            tk.setLoaitaikhoan("Admin");
            setContentView(R.layout.activity_main);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
        }else {
            tk.setLoaitaikhoan("User");
            setContentView(R.layout.activity_main_user);
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_user);
            navigationView.setNavigationItemSelectedListener(this);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        initFragment();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else replaceFragmentContent(new TrangChu());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id==R.id.action_logout) {
            finish();
        } else if (id==R.id.action_info){
            ThongTin();
        } else if (id==R.id.action_DoiMatKhau) {
            DialogSua(tk);
        }
        return super.onOptionsItemSelected(item);
    }

    private void ThongTin() {
        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.thong_tin);
        dialog.show();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.item_trang_chu) {
            replaceFragmentContent(new TrangChu());
        } else if (id==R.id.item_ds_gv){
            DanhSachGV ds=new DanhSachGV();
            Bundle bundle=new Bundle();
            bundle.putInt("LoaiTaiKhoan",loaitaikhoan);
            ds.setArguments(bundle);
            replaceFragmentContent(ds);
        } else if (id==R.id.item_ds_ctv){
            DanhSachCTV ds=new DanhSachCTV();
            Bundle bundle=new Bundle();
            bundle.putInt("LoaiTaiKhoan",loaitaikhoan);
            ds.setArguments(bundle);
            replaceFragmentContent(ds);
        } else if (id==R.id.item_them){
            replaceFragmentContent(new ThemTK());
        } else if (id==R.id.item_quan_ly){
            replaceFragmentContent(new QuanLyTK());
        } else if (id==R.id.item_ds_lop) {
            DanhSachLop ds=new DanhSachLop();
            Bundle bundle=new Bundle();
            bundle.putInt("LoaiTaiKhoan",loaitaikhoan);
            ds.setArguments(bundle);
            replaceFragmentContent(ds);
        } else if (id==R.id.item_danh_sach_iso) {
            replaceFragmentContent(new DanhSachISO());
        } else if (id==R.id.item_nhat_ky_sh){
            //replaceFragmentContent(new NhatKySinhHoat());
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

                FragmentManager fmgr = getSupportFragmentManager();

                FragmentTransaction ft = fmgr.beginTransaction();

                ft.replace(R.id.fr_main, fragment);


                ft.commit();
        }

    }

    private void initFragment() {

        TrangChu firstFragment = new TrangChu();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fr_main, firstFragment);

        ft.commit();

    }

    @Override
    public void onSelecitem(int i) {
        switch (i){
            case 0:
                replaceFragmentContent(new NhatKySinhHoat());
                break;
            case 1:
                DanhSachGV ds=new DanhSachGV();
                Bundle bundle=new Bundle();
                bundle.putInt("LoaiTaiKhoan",loaitaikhoan);
                ds.setArguments(bundle);
                replaceFragmentContent(ds);
                break;
            case 2:
                DanhSachLop dsLop=new DanhSachLop();
                Bundle bundleLop=new Bundle();
                bundleLop.putInt("LoaiTaiKhoan",loaitaikhoan);
                dsLop.setArguments(bundleLop);
                replaceFragmentContent(dsLop);
                break;
            case 3:
                replaceFragmentContent(new DanhSachISO());
                break;
            case 4:
                Toast.makeText(this, "Tính năng đang phát triển", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onSelectTenCLB(final String id, final String TenCLB) {
        arrayGVList=new ArrayList<>();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATAGV, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String TenGV = "";
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                ItemHS_GV gv=new ItemHS_GV();
                                gv.setId(object.getString("MaNV"));
                                gv.setHoten(object.getString("HoTen"));
                                gv.setSdt(object.getString("SDT"));
                                gv.setClb(object.getString("MaCLB"));
                                arrayGVList.add(gv);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for(int i=0;i<=arrayGVList.size();i++){
                            if(arrayGVList.get(i).getClb().equals(id)){
                                TenGV=arrayGVList.get(i).getHoten();
                                break;
                            }
                        }
                        DanhSachLopChiTiet ds=new DanhSachLopChiTiet();
                        Bundle bundle=new Bundle();
                        bundle.putString("ID",id);
                        bundle.putString("TENCLB",TenCLB);
                        bundle.putString("TENGV",TenGV);
                        ds.setArguments(bundle);
                        replaceFragmentContent(ds);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void ClickButton() {
        replaceFragmentContent(new QuanLyTK());
    }

    @Override
    public void fabClick() {
        replaceFragmentContent(new ThemTK());
    }

    private void DialogSua(final TAIKHOAN tk) {
        final Dialog dialog=new Dialog(this);
        tendangnhap=tk.getTendangnhap();
        dialog.setContentView(R.layout.cap_nhat_mat_khau);

        AnhXa(dialog);

        btThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                matkhau=MatKhau.getText().toString();
                rematkhau=ReMatKhau.getText().toString();
                LoaiTK=tk.getLoaitaikhoan();
                if(!MatKhauHienTai.getText().toString().equals(tk.getMatkhau())){
                    Toast.makeText(MainActivity.this, "Mật khẩu hiện tại không chính xác. Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                } else{
                    if(matkhau.equals("")){
                        Toast.makeText(MainActivity.this,"Vui lòng nhập mật khẩu!",Toast.LENGTH_SHORT).show();
                        MatKhau.forceLayout();
                    }else {
                        if (!matkhau.equals(rematkhau)) {
                            Toast.makeText(MainActivity.this, "Mật khẩu nhập lại không khớp với mật khẩu. Vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                            ReMatKhau.setText("");
                            ReMatKhau.forceLayout();
                        } else {
                            TAIKHOAN tkSua = new TAIKHOAN();
                            tkSua.setTendangnhap(tendangnhap);
                            tkSua.setMatkhau(matkhau);
                            tkSua.setLoaitaikhoan(LoaiTK);
                            UpdateTaiKhoan(tkSua);
                            dialog.dismiss();
                        }
                    }
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

    private void UpdateTaiKhoan(final TAIKHOAN tk) {
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URLCAPNHATTK,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().equals("success")){
                            Toast.makeText(MainActivity.this, "Cập nhật tài khoản thành công", Toast.LENGTH_LONG).show();
                        } else Toast.makeText(MainActivity.this, "Lỗi cập nhật tài khoản", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi: "+ error.toString(), Toast.LENGTH_LONG).show();
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
        MatKhau=(EditText) dialog.findViewById(R.id.editTextMatKhau);
        ReMatKhau=(EditText) dialog.findViewById(R.id.editTextMatKhau2);
        MatKhauHienTai=(EditText) dialog.findViewById(R.id.editTextMatKhauHT);
    }

    @Override
    public void ButtonThemNV(String chucvu) {
        if(chucvu.equals("GV")){
            DanhSachGV ds=new DanhSachGV();
            Bundle bundle=new Bundle();
            bundle.putInt("LoaiTaiKhoan",loaitaikhoan);
            ds.setArguments(bundle);
            replaceFragmentContent(ds);
        }
        else {
            DanhSachCTV ds=new DanhSachCTV();
            Bundle bundle=new Bundle();
            bundle.putInt("LoaiTaiKhoan",loaitaikhoan);
            ds.setArguments(bundle);
            replaceFragmentContent(ds);
        }
    }

    @Override
    public void ThemNVCLick(String them) {
        ThemGV ds=new ThemGV();
        Bundle bundle=new Bundle();
        bundle.putString("THEM",them);
        ds.setArguments(bundle);
        replaceFragmentContent(ds);
    }

    @Override
    public void ButtonThemSV(final String id, final String TenCLB) {
        arrayGVList=new ArrayList<>();
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, URLGETDATAGV, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String TenGV = "";
                        for(int i=0;i<response.length();i++){
                            try {
                                JSONObject object=response.getJSONObject(i);
                                ItemHS_GV gv=new ItemHS_GV();
                                gv.setId(object.getString("MaNV"));
                                gv.setHoten(object.getString("HoTen"));
                                gv.setSdt(object.getString("SDT"));
                                gv.setClb(object.getString("MaCLB"));
                                arrayGVList.add(gv);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for(int i=0;i<=arrayGVList.size();i++){
                            if(arrayGVList.get(i).getClb().equals(id)){
                                TenGV=arrayGVList.get(i).getHoten();
                                break;
                            }
                        }
                        DanhSachLopChiTiet ds=new DanhSachLopChiTiet();
                        Bundle bundle=new Bundle();
                        bundle.putString("ID",id);
                        bundle.putString("TENCLB",TenCLB);
                        bundle.putString("TENGV",TenGV);
                        ds.setArguments(bundle);
                        replaceFragmentContent(ds);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Lỗi hệ thống", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void ButtonHuy() {
        DanhSachLop dsLop=new DanhSachLop();
        Bundle bundleLop=new Bundle();
        bundleLop.putInt("LoaiTaiKhoan",loaitaikhoan);
        dsLop.setArguments(bundleLop);
        replaceFragmentContent(dsLop);
    }

    @Override
    public void ClickThemSV() {
        replaceFragmentContent(new ThemSV());
    }

    @Override
    public void ThemCTVClick(String them) {
        ThemGV ds=new ThemGV();
        Bundle bundle=new Bundle();
        bundle.putString("THEM",them);
        ds.setArguments(bundle);
        replaceFragmentContent(ds);
    }
}
