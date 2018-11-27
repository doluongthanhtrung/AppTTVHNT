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
        db.execSQL("CREATE TABLE IF NOT EXISTS TaiKhoan(TenDangNhap VARCHAR(20) PRIMARY KEY,MatKhau VARCHAR(20) NOT NULL,LoaiTaiKhoan INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS CauLacBo(MaCLB VARCHAR(10) PRIMARY KEY,TenCLB NVARCHAR(100),IsFull INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS SinhVien(MSSV VARCHAR(12) PRIMARY KEY,HoTen VARCHAR(100) NOT NULL,Sdt INTEGER,MaCLB VARCHAR(10) NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS NhanVien(MaNV VARCHAR(15) PRIMARY KEY,HoTen VARCHAR(100) NOT NULL,Sdt INTEGER,ChucVu VARCHAR(50))");
        db.execSQL("CREATE TABLE IF NOT EXISTS GiangVien(MaCLB VARCHAR(10) PRIMARY KEY,MaNV VARCHAR(15))");
        db.execSQL("CREATE TABLE IF NOT EXISTS TaiLieuISO(MaISO INTEGER PRIMARY KEY,NoiDung VARCHAR(100),Trang INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS DiemDanh(MaDD INTEGER PRIMARY KEY AUTOINCREMENT,MSSV VARCHAR(12),date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TaiKhoan");
        db.execSQL("DROP TABLE IF EXISTS CauLacBo");
        db.execSQL("DROP TABLE IF EXISTS SinhVien");
        db.execSQL("DROP TABLE IF EXISTS NhanVien");
        db.execSQL("DROP TABLE IF EXISTS TaiLieuISO");

        onCreate(db);
    }

    //Dữ liệu mặc đinh
    public void addDefualtdataIfNeed(){
        if(getTaiKhoanCount()==0){
            addTaiKhoanData();
        } else if(getCLBCount()==0){
            addCLBData();
        } else if(getNhanVienCount()==0) {
            addNhanVienData();
        } else if(getGiangVienCount()==0){
            addGiangVienData();
        } else if(getSinhVienCount()==0){
            addSinhVienData();
        } else if(getTaiLieuISOCount()==0){
            addTaiLieuISOData();
        }
    }

    private void addGiangVienData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO GiangVien VALUES('TN1','TTT')");
        db.execSQL("INSERT INTO GiangVien VALUES('GT1','CH')");
        db.execSQL("INSERT INTO GiangVien VALUES('Pi1','YHD')");
        db.execSQL("INSERT INTO GiangVien VALUES('TN2','TTT')");
        db.execSQL("INSERT INTO GiangVien VALUES('GT2','CH')");
        db.execSQL("INSERT INTO GiangVien VALUES('Pi2','YHD')");
        db.execSQL("INSERT INTO GiangVien VALUES('DX','MP')");
        db.execSQL("INSERT INTO GiangVien VALUES('EV-PR','BPT')");
    }

    private void addNhanVienData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO NhanVien VALUES('LNS','Nguyễn Sỹ Luân',null,'GĐ')");
        db.execSQL("INSERT INTO NhanVien VALUES('TND','Nguyễn Duy Thịnh',null,'PGĐ')");
        db.execSQL("INSERT INTO NhanVien VALUES('HLT','Lê Thanh Hiệp',0823916502,'CTV')");
        db.execSQL("INSERT INTO NhanVien VALUES('THS','Hoàng Sinh Trung',0947165733,'CTV')");
        db.execSQL("INSERT INTO NhanVien VALUES('TLD','Lữ Đình Trương',null,'CTV')");
        db.execSQL("INSERT INTO NhanVien VALUES('TLKT','Lê Khổng Thanh Toàn',0903373014,'CTV')");
        db.execSQL("INSERT INTO NhanVien VALUES('TTT','Trần Thiện Thảo',null,'GV')");
        db.execSQL("INSERT INTO NhanVien VALUES('CH','Chình Hân',null,'GV')");
        db.execSQL("INSERT INTO NhanVien VALUES('YHD','Huỳnh Đông Uyên',null,'GV')");
        db.execSQL("INSERT INTO NhanVien VALUES('BPT','Phạm Thái Bình',null,'GV')");
        db.execSQL("INSERT INTO NhanVien VALUES('MP','Mai Phương',null,'GV')");
    }

    private void addCLBData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO CauLacBo VALUES('TN1','Thanh nhạc 1',0)");
        db.execSQL("INSERT INTO CauLacBo VALUES('TN2','Thanh nhạc 2',1)");
        db.execSQL("INSERT INTO CauLacBo VALUES('GT1','Guitar 1',1)");
        db.execSQL("INSERT INTO CauLacBo VALUES('GT2','Guitar 2',0)");
        db.execSQL("INSERT INTO CauLacBo VALUES('Pi1','Piano 1',0)");
        db.execSQL("INSERT INTO CauLacBo VALUES('Pi2','Piano 2',1)");
        db.execSQL("INSERT INTO CauLacBo VALUES('DX','Diễn xuất',0)");
        db.execSQL("INSERT INTO CauLacBo VALUES('EV-PR','Sự kiện truyền thông',0)");
    }

    private void addTaiKhoanData() {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO TaiKhoan VALUES('Admin','admin',0)");
        db.execSQL("INSERT INTO TaiKhoan VALUES('User','user',1)");
        db.execSQL("INSERT INTO TaiKhoan VALUES('thanhtrung','password',0)");
        db.execSQL("INSERT INTO TaiKhoan VALUES('thanhhiep','matkhau',1)");

    }

    private void addSinhVienData() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO SinhVien VALUES(1711140933,'Huỳnh Thị Tường Vi',886789756,'TN1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711240418,'Nguyễn Thị Anh Thư',1296671155,'TN1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711160290,'Trương Nguyễn Phương Trang',967792532,'TN1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711290051,'Lê Thị Hồng Nhiên',1229638258,'TN1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711160565,'trịnh kim ngân',1666199495,'TN1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711290172,'Phạm Thị Cẩm Thúy',911652420,'TN2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1611701164,'La Ngọc Liên',1296286957,'TN2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711700026,'Vũ Thị Ngọc Anh',961414856,'TN2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711700901,'Phan Ngọc Quỳnh Như',1222738878,'TN2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711700471,'Hà Trang Vân',1863844697,'TN2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711270039,'HỒ NGỌC ĐIỆP HÂN',907208082,'Pi1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711141299,'Lê Tường Vi',1662447099,'Pi1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711170154,'Nguyễn Hoàng Oanh',981771714,'Pi1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711150454,'Thai thuy yen nhi',1648097571,'Pi1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1511270149,'Trần Uyên Trâm',983278945,'Pi1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711290223,'Dương Lê Ngọc Nguyệt',963313610,'Pi2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711150394,'Phan Thị Thu Hà',1638311329,'Pi2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711060425,'Nguyễn Thái Đức',1214357984,'Pi2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711040495,'Trang Minh Vũ',947539798,'Pi2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711190241,'Nguyễn Thị Tuyết Nhung',1668105211,'Pi2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711150088,'TRẦN TÙNG LINH',1215870239,'GT1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711062058,'Nguyễn Ngọc Thông',1669625514,'GT1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711140092,'Hứa Hướng Dương',946482747,'GT1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711140015,'Lâm Tuấn Anh',1246684263,'GT1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711290169,'Mai Hoàng Phúc',908707928,'GT1')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711060855,'Trần Đức Thanh',1282941742,'GT2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711050077,'Đỗ Trọng Tín',943160454,'GT2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711230557,'Đoàn Tấn Phong',927765360,'GT2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711161453,'Trần Khánh Vân',932310990,'GT2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711240155,'Lưu Hoàng Vũ',933536376,'GT2')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711701754,'Phạm Huỳnh Anh Thư',908758281,'DX')");
        db.execSQL("INSERT INTO SinhVien VALUES(1611700825,'Quách Ngọc Bảo Châu',1204683239,'DX')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711060790,'Phạm Anh Hào',869367707,'DX')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711200152,'Nguyễn hoàng yến linh',1627143301,'DX')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711200422,'Võ Thanh Phú',928593810,'DX')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711700720,'Mai Khả Ngọc',1678299608,'EV-PR')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711230214,'NGUYỄN THANH UYÊN',902496128,'EV-PR')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711170138,'Võ Hoàng Yến Nhi',1217046499,'EV-PR')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711100176,'Nguyễn Thị Thanh',1647798596,'EV-PR')");
        db.execSQL("INSERT INTO SinhVien VALUES(1711141893,'Trần Thị Thanh Thiên',1284541695,'EV-PR')");
    }

    private void addTaiLieuISOData() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO TaiLieuISO VALUES(0,'Cơ cấu Trung tâm',1)");
        db.execSQL("INSERT INTO TaiLieuISO VALUES(1,'Văn bản hoạt động VHNT',5)");
        db.execSQL("INSERT INTO TaiLieuISO VALUES(2,'Cuộc thi MISS HUTECH',10)");
        db.execSQL("INSERT INTO TaiLieuISO VALUES(3,'Các khóa học ngắn hạn',20)");
        db.execSQL("INSERT INTO TaiLieuISO VALUES(4,'Văn bản hoạt động Trung tâm',25)");
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

    public ArrayList<ItemHS_GV> getDSCLB(){
        ArrayList<ItemHS_GV> clbList=new ArrayList<ItemHS_GV>();
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM CauLacBo",null);
        if (cursor.moveToFirst()) {
            do {
                ItemHS_GV clb = new ItemHS_GV();
                clb.setId(cursor.getString(0));
                clb.setHoten(cursor.getString(1));

                // Thêm vào danh sách.
                clbList.add(clb);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return clbList;
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
