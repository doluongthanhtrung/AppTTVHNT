package com.example.user.appttvhnt;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public class DemoDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME= "TTVHNTdemo";
    private static final int DATABASE_VERSION= 1;

    public DemoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //initDatabase((Activity) context,DATABASE_NAME);
    }

   /* public static SQLiteDatabase initDatabase(Activity activity, String databaseName){
        try {
            String outFileName = activity.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if(!f.exists()) {
                InputStream e = activity.getAssets().open(databaseName);
                File folder = new File(activity.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return activity.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }*/

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE CauLacBo ( MaCLB varchar(25) NOT NULL PRIMARY KEY,TenCLB varchar(255) NOT NULL)");
        addCLBData();

        db.execSQL("CREATE TABLE LichSinhHoat (ID int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,MaCLB varchar(20) NOT NULL,Thu int(11) NOT NULL,Gio varchar(10) NOT NULL)");
        addLSHData();

        db.execSQL("CREATE TABLE DiemDanhSV (MaDD int(11) NOT NULL PRIMARY KEY AUTOINCREMENT,MaCLB varchar(20) NOT NULL,MSSV int(11) NOT NULL,Ngay varchar(20) NOT NULL,TrangThai int(11) NOT NULL,LyDo varchar(255) DEFAULT NULL)");
        addDDSVData();
    }

    private void addDDSVData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO DiemDanhSV VALUES(10, 'GT1', 1711050077, '15/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(17, 'EV-PR2', 1711230746, '15/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(7, 'DX', 1711060790, '15/10/2018', 0, 'bệnh')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(6, 'DX', 1611700825, '15/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(11, 'GT1', 1711050077, '15/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(12, 'EV-PR1', 1711100176, '15/10/2018', 0, 'bệnh')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(13, 'EV-PR2', 1711141893, '15/10/2018', 0, 'bệnh')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(16, 'EV-PR2', 1711141893, '15/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(15, 'GT2', 1511201198, '15/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(18, 'EV-PR2', 1711701141, '15/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(19, 'EV-PR2', 1711170480, '15/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(20, 'GT1', 1711050077, '21/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(21, 'GT1', 1711060855, '21/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(22, 'GT1', 1711050077, '21/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(23, 'GT1', 1711050077, '23/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(24, 'DX', 1711143536, '23/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(25, 'EV-PR1', 1711100176, '23/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(26, 'EV-PR1', 1711230214, '23/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(27, 'EV-PR1', 1711143368, '23/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(28, 'DX', 1611700825, '23/10/2018', 1, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(29, 'DX', 1711061253, '23/10/2018', 0, '')");
        db.execSQL("INSERT INTO DiemDanhSV VALUES(30, 'DX', 1711200152, '23/10/2018', 1, '')");
    }

    private void addLSHData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO LichSinhHoat VALUES(1, 'DX', 2, '16:30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(2, 'DX', 4, '16h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(3, 'EV-PR1', 3, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(4, 'EV-PR1', 5, '16h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(5, 'EV-PR2', 5, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(6, 'EV-PR2', 7, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(7, 'GT1', 2, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(8, 'GT1', 5, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(9, 'GT2', 3, '14h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(10, 'GT2', 7, '14h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(11, 'GT3', 4, '16h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(12, 'GT3', 7, '14h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(13, 'PI1', 5, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(14, 'PI1', 7, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(15, 'PI2', 3, '9h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(16, 'PI2', 5, '9h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(17, 'TN1', 3, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(18, 'TN1', 6, '18h30')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(19, 'TN2', 2, '9h20')");
        db.execSQL("INSERT INTO LichSinhHoat VALUES(20, 'TN2', 6, '9h30')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void addCLBData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO CauLacBo VALUES('TN1','Thanh nhạc 1')");
        db.execSQL("INSERT INTO CauLacBo VALUES('TN2','Thanh nhạc 2')");
        db.execSQL("INSERT INTO CauLacBo VALUES('GT1','Guitar 1')");
        db.execSQL("INSERT INTO CauLacBo VALUES('GT2','Guitar 2')");
        db.execSQL("INSERT INTO CauLacBo VALUES('GT3','Guitar 3')");
        db.execSQL("INSERT INTO CauLacBo VALUES('PI1','Piano 1')");
        db.execSQL("INSERT INTO CauLacBo VALUES('PI2','Piano 2')");
        db.execSQL("INSERT INTO CauLacBo VALUES('DX','Diễn xuất')");
        db.execSQL("INSERT INTO CauLacBo VALUES('EV-PR1','Sự kiện truyền thông 1')");
        db.execSQL("INSERT INTO CauLacBo VALUES('EV-PR2','Sự kiện truyền thông 2')");
    }

    public int getTaiKhoanCount() {
        String query="SELECT * FROM TaiKhoan";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getCLBCount() {
        String query="SELECT * FROM CauLacBo";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getNhanVienCount() {
        String query="SELECT * FROM NhanVien";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getGiangVienCount() {
        String query="SELECT * FROM GiangVien";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getSinhVienCount() {
        String query="SELECT * FROM SinhVien";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public int getTaiLieuISOCount() {
        String query="SELECT * FROM TaiLieuISO";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public void AddTaiKhoan(TAIKHOAN tk){
        SQLiteDatabase db=getWritableDatabase();
        if(tk.getLoaitaikhoan().equals("Admin")){
            db.execSQL("INSERT INTO TaiKhoan VALUES('"+tk.getTendangnhap()+"','"+tk.getMatkhau()+"',0)");
        } else
        db.execSQL("INSERT INTO TaiKhoan VALUES('"+tk.getTendangnhap()+"','"+tk.getMatkhau()+"',1)");
    }

    public int KiemTraDangNhap(String TenDangNhap,String matKhau){
        SQLiteDatabase db=this.getReadableDatabase();


        Cursor user = db.rawQuery("SELECT TenDangNhap, LoaiTaiKhoan FROM TaiKhoan WHERE TenDangNhap='" + TenDangNhap + "' AND MatKhau='" + matKhau+"'",null);
        while (user.moveToNext()){
            int loai=user.getInt(1);
            switch (loai) {
                case 0:
                    return 0;
                case 1:
                    return 1;
            }
        }
        return 2;
    }

    public ArrayList<ItemHS_GV> getDSGV(){
        ArrayList<ItemHS_GV> gvList=new ArrayList<ItemHS_GV>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT MaNV,HoTen, Sdt FROM NhanVien WHERE ChucVu='GV'",null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV gv = new ItemHS_GV();
                gv.setId(cursor.getString(0));
                gv.setHoten(cursor.getString(1));
                gv.setSdt(cursor.getString(2));

                // Thêm vào danh sách.
                gvList.add(gv);
            } while (cursor.moveToNext());
        }

        return gvList;
    }

    public ArrayList<ItemHS_GV> getDSISO(){
        ArrayList<ItemHS_GV> isoList=new ArrayList<ItemHS_GV>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM TaiLieuISO",null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV iso = new ItemHS_GV();
                iso.setHoten(cursor.getString(1));
                iso.setSdt(cursor.getString(2));

                // Thêm vào danh sách.
                isoList.add(iso);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return isoList;
    }

    public ArrayList<LichSinhHoat> getDSCLB(ArrayList<ItemHS_GV> SVList){
        ArrayList<LichSinhHoat> clbList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM CauLacBo ",null);
        if (cursor.moveToFirst()) {
            do {
                LichSinhHoat clb = new LichSinhHoat();
                clb.setMaCLB(cursor.getString(0));
                clb.setTenCLB(cursor.getString(1));
                clb.setSoLuong(String.valueOf(getDDSVData(clb.getMaCLB())));
                clb.setTong(String.valueOf(CountSVByCLB(SVList,clb.getMaCLB())));
                // Thêm vào danh sách.
                clbList.add(clb);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return clbList;
    }

    private int CountSVByCLB(ArrayList<ItemHS_GV> SVList,String maclb){
        int dem=0;
        for(int i=0;i<SVList.size();i++){
            if(SVList.get(i).getChucvu().equals(maclb)){
                dem++;
            }
        }
        return dem;
    }
    public int getDDSVData(String maclb) {
        String query="SELECT MSSV FROM dbo.LichSinhHoat WHERE MaCLB='"+maclb+"'";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }

    public ArrayList<LichSinhHoat> getDSCLBByThu(ArrayList<ItemHS_GV> SVList, int thu){
        ArrayList<LichSinhHoat> clbList=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT l.ID, c.MaCLB, c.TenCLB, l.Thu, l.Gio FROM dbo.caulacbo c, dbo.LichSinhHoat l WHERE c.MaCLB=l.MaCLB",null);
        if (cursor.moveToFirst()) {
            do {
                LichSinhHoat clb = new LichSinhHoat();
                clb.setID(cursor.getInt(0));
                clb.setMaCLB(cursor.getString(1));
                clb.setTenCLB(cursor.getString(2));
                clb.setThu(cursor.getInt(3));
                clb.setGio(cursor.getString(4));
                clb.setSoLuong(String.valueOf(getDDSVData(clb.getMaCLB())));
                clb.setTong(String.valueOf(CountSVByCLB(SVList,clb.getMaCLB())));

                // Thêm vào danh sách.
                if(clb.getThu()==thu){
                    clbList.add(clb);
                }
            } while (cursor.moveToNext());
        }
        cursor.close();

        return clbList;
    }

    public ArrayList<ItemHS_GV> getDSCLBFull() {
        ArrayList<ItemHS_GV> clblist = new ArrayList<ItemHS_GV>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CauLacBo", null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV CLB = new ItemHS_GV();
                CLB.setId(cursor.getString(0));
                CLB.setHoten(cursor.getString(1));

                // Thêm vào danh sách.
                clblist.add(CLB);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return clblist;
    }
    public ArrayList<ItemHS_GV> getDSCTV(){
        ArrayList<ItemHS_GV> ctvList=new ArrayList<ItemHS_GV>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT ManV, HoTen, Sdt FROM NhanVien WHERE ChucVu='CTV'",null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV ctv = new ItemHS_GV();
                ctv.setId(cursor.getString(0));
                ctv.setHoten(cursor.getString(1));
                ctv.setSdt(cursor.getString(2));

                // Thêm vào danh sách.
                ctvList.add(ctv);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return ctvList;
    }
    public String getGVbyMaCLB(String maCLB){
        String gv=null;
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("Select n.HoTen FROM NhanVien n,GiangVien g WHERE g.MaNV=N.MaNV AND g.MaCLB='"+maCLB+"'",null);
        if (cursor.moveToFirst())
            do{
                gv=cursor.getString(0);
            }while (cursor.moveToNext());
        cursor.close();
        return gv;
    }

    public ArrayList<ItemHS_GV> getDSSVbyMaCLB(String maCLB){
        ArrayList<ItemHS_GV> hsList=new ArrayList<ItemHS_GV>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT MSSV,HoTen, Sdt FROM SinhVien WHERE MaCLB='"+maCLB+"'",null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV hs = new ItemHS_GV();
                hs.setId(cursor.getString(0));
                hs.setHoten(cursor.getString(1));
                hs.setSdt(cursor.getString(2));

                // Thêm vào danh sách.
                hsList.add(hs);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return hsList;
    }

    public ArrayList<TAIKHOAN> getDSTK(){
        ArrayList<TAIKHOAN> tkList=new ArrayList<TAIKHOAN>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM TaiKhoan",null);
        if (cursor.moveToFirst()) {
            do {
                TAIKHOAN tk=new TAIKHOAN();
                tk.setTendangnhap(cursor.getString(0));
                tk.setMatkhau(cursor.getString(1));
                if(cursor.getInt(2)==0){
                    tk.setLoaitaikhoan("Admin");
                } else tk.setLoaitaikhoan("User");
                // Thêm vào danh sách.
                tkList.add(tk);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return tkList;
    }

    public void UpdateTaiKhoan(TAIKHOAN tk,String TenDangNhap){
        SQLiteDatabase db=getWritableDatabase();
        if(tk.getLoaitaikhoan().equals("Admin")){
            db.rawQuery("UPDATE TaiKhoan SET MatKhau ='"+tk.getMatkhau()+"', LoaiTaiKhoan = 0 WHERE TenDangNhap='"+TenDangNhap+"'",null);
        } else db.rawQuery("UPDATE TaiKhoan SET MatKhau ='"+tk.getMatkhau()+"', LoaiTaiKhoan = 1 WHERE TenDangNhap='"+TenDangNhap+"'",null);
    }

    public void DeleteTaiKhoan(String TenDangNhap){
        SQLiteDatabase db=getWritableDatabase();
        db.rawQuery("DELETE FROM TaiKhoan WHERE TenDangNhap='"+TenDangNhap+"'",null);
    }

    public ArrayList<ItemHS_GV> getDSSVTK(String dk){
        ArrayList<ItemHS_GV> hsList=new ArrayList<ItemHS_GV>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM SinhVien WHERE MSSV='"+dk+"'",null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV hs=new ItemHS_GV();
                hs.setId(cursor.getString(0));
                hs.setHoten(cursor.getString(1));
                hs.setSdt(cursor.getString(2));
                hs.setClb(cursor.getString(3));
                // Thêm vào danh sách.
                hsList.add(hs);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return hsList;
    }

    public void AddNV(ItemHS_GV nv) {
        SQLiteDatabase db=getWritableDatabase();
        int sdt= Integer.parseInt(nv.getSdt());

        db.execSQL("INSERT INTO NhanVien VALUES('"+nv.getId()+"','"+nv.getHoten()+"',"+sdt+",'"+nv.getChucvu()+"')");
        /*if(!nv.getClb().equals("")){
            Cursor cursor=db.rawQuery("SELECT * FROM GiangVien WHERE MaCLB='"+nv.getClb()+"'",null);
            if(cursor.getCount()==0){
                db.execSQL("INSERT INTO GiangVien VALUES('"+nv.getClb()+"','"+nv.getId()+"')");
            } else db.execSQL("UPDATE GiangVien SET MaNV='"+nv.getId()+"' WHERE MaCLB='"+nv.getClb()+"'");
            cursor.close();
        }*/

    }

    public void DeleteGiangVien(String id) {
        SQLiteDatabase db=getWritableDatabase();
        db.rawQuery("DELETE FROM NhanVien WHERE MaNV='"+id+"'",null);
    }

    public void CapNhatGV(String manv, String sdt, String clb) {
        int SDT= Integer.parseInt(sdt);
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("UPDATE NhanVien SET Sdt='"+SDT+"' WHERE MaNV='"+manv+"'");
    }

}
