package geso.dms.distributor.util;

import geso.dms.center.beans.Router.IDRouter;
import geso.dms.center.beans.lotrinh.ILoTrinh;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTLList;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.ChuyenNgu;
import geso.dms.center.util.GlobalValue;
import geso.dms.distributor.beans.reports.imp.Reports;
import geso.dms.distributor.db.sql.dbutils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.lang.Math;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import redis.clients.jedis.Jedis;

public class Utility implements Serializable
{
	private static final long serialVersionUID = 1L;
	String nppId="";
	String nppTen="";
	String sitecode="";
	String dangnhap="";
	String loaiNpp="";
	private String khoSAP;

	public static final int THEM = 0;
	public static final int XOA = 1;
	public static final int SUA = 2;
	public static final int XEM = 3;
	public static final int CHOT = 4;
	public static final int HUYCHOT = 5;

	public String getLoaiNpp()
	{
		return loaiNpp;
	}
	public static String getNgayHienTai()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	public void setLoaiNpp(String loaiNpp)
	{
		this.loaiNpp = loaiNpp;
	}

	public String ChietKhau(String nam)
	{
		if(nam.trim().equals("2013"))
			return "0.97";
		else return  "0.965";
	}

	public String Check_CK_DaHuong(String hoadonId,String userId, dbutils db)
	{
		String		query=	 
			"	select  "+  
			"	STUFF      "+  
			"	(     "+
			"		(    "+
			"				select DISTINCT TOP 100 PERCENT ' , ' +  hd.SOHOADON "+  
			"				from HOADON_CHIETKHAU ck inner join HOADON hd on hd.PK_SEQ=ck.hoadon_fk "+  
			"				where  hd.LOAIHOADON=0 and DATEPART(MONTH,hd.ngayxuathd)=DATEPART(MONTH,a.ngayxuathd)+1 "+  
			"				and ck.tichluyQUY=0 and ROUND(ck.chietkhau,0) >0    "+
			"				and hd.KHACHHANG_FK=a.KHACHHANG_FK and hd.TRANGTHAI not in (3,5) "+ 
			"			ORDER BY ' , ' + hd.SOHOADON   "+
			"			FOR XML PATH('')       "+
			"		 ), 1, 2, ' '    "+
			"	) + ' '  as SoHoaDon , "+
			"	STUFF      "+  
			"	(     "+
			"		(    "+
			"				select DISTINCT TOP 100 PERCENT ' , ' +  cast(hd.pk_seq as varchar(20) ) "+  
			"				from HOADON_CHIETKHAU ck inner join HOADON hd on hd.PK_SEQ=ck.hoadon_fk "+  
			"				where  hd.LOAIHOADON=0 and DATEPART(MONTH,hd.ngayxuathd)=DATEPART(MONTH,a.ngayxuathd)+1 "+  
			"				and ck.tichluyQUY=0 and ROUND(ck.chietkhau,0) >0    "+
			"				and hd.KHACHHANG_FK=a.KHACHHANG_FK and hd.TRANGTHAI not in (3,5) "+ 
			"			ORDER BY ' , ' + cast(hd.pk_seq as varchar(20) )   "+
			"			FOR XML PATH('')       "+
			"		 ), 1, 2, ' '    "+
			"	) + ' '  as hoadonck "+
			" from HoaDon a  "+
			" where pk_Seq='"+hoadonId+"' ";
		System.out.println("[Check_CK_DaHuong]"+query);
		String msg = "";
		String sohoadon="";
		String hoadonck="";
		ResultSet rs = db.get(query);
		try
		{
			while(rs.next())
			{
				sohoadon += rs.getString("SoHoaDon")==null?"":rs.getString("SoHoaDon") + " ,";
				hoadonck += rs.getString("hoadonck")==null?"":rs.getString("hoadonck") + " ,";
				System.out.println("[Check_CK_DaHuong]"+sohoadon);
			}
			rs.close();
			if(sohoadon.length()>0)
			{
				msg= "DOANH SỐ TRONG BIÊN BIỂN BÙ TRỪ CHIẾT KHẤU THÁNG SẼ THAY ĐỔI NẾU BẠN XÓA/ HỦY HÓA ĐƠN NÀY!";
				query=
					" INSERT INTO HuyHoaDon(HoaDonHuy_Fk,SoHoaDon,HoaDon_FK,GhiChu,NguoiSua) " +
					" select '"+hoadonId+"','"+sohoadon+"','"+hoadonck+"',N'"+msg+"','"+userId+"'  ";
				db.update(query);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Lỗi phát sinh khi check ck tháng đã hưởng !;";
		}
		return msg;
	}

	public String getUrl(String servlet,String parametes)
	{
		String query=
			"	select   B.PK_SEQ as DanhMuc_FK,a.PK_SEQ as pk_Seq  , c.ten + ' > '+  b.ten as TENDANHMUC, a.ten , a.servlet,  "+  
			"			a.parameters, c.sott as stt1, b.sott as stt2, a.sott           "+
			"	from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq        "+       
			"	inner join ungdung c on b.ungdung_fk = c.pk_seq               "+
			"	where a.level = '3' and b.level = '2'  and a.TrangThai=1 and a.servlet='"+servlet+"' and a.parameters='"+parametes+"' "+           
			"	union all            "+
			"	select c.PK_SEQ as DanhMuc_FK ,a.PK_SEQ ,  c.ten   as TENDANHMUC, a.ten, a.servlet, a.parameters, c.sott as stt1, a.sott as stt2, a.sott "+ 
			"	from ungdung a  inner join ungdung c on a.ungdung_fk = c.pk_seq               "+
			"	where a.level = '3' and c.level = '1'    and a.TrangThai=1  and a.servlet='"+servlet+"' and a.parameters='"+parametes+"'    "+         
			"	order by stt1 asc, stt2 asc, sott asc  " ;
		dbutils db = new dbutils();
		String url="";
		ResultSet rs =db.get(query);
		try
		{
			while(rs.next())
			{
				url=rs.getString("TENDANHMUC")+ " > " + rs.getString("ten");
			}
			if(rs!=null)rs.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.shutDown();
		}
		return url;
	}

	public String getIdNhapp(String userId, dbutils db) 
	{
		String sql=
			"			select nv.dangnhap,npp.LoaiNPP, npp.khosap, npp.pk_seq,npp.sitecode,npp.ten  "+ 
			"			from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode   "+
			"			where nv.pk_seq='"+userId+"' and nv.trangthai='1'  and nv.PHANLOAI=1 and npp.isKHACHHANG = '0'  "+ 
			"			union all  "+
			"			select nv.dangnhap,npp.LoaiNPP, npp.khosap, npp.pk_seq,npp.sitecode,gs.ten  "+ 
			"			from nhanvien nv inner join GIAMSATBANHANG gs on nv.GSBH_FK=gs.PK_SEQ  "+
			"				inner join NHAPHANPHOI npp on npp.SITECODE=nv.CONVSITECODE  "+
			"			where nv.pk_seq='"+userId+"' and nv.trangthai='1'  and nv.PHANLOAI=2 and gs.TRANGTHAI=1  "+
			"			and npp.TRANGTHAI=1 ";

		//System.out.println("Get Thong Tin NPP :"+sql);
		ResultSet rs= db.get(sql);
		try{
			if(rs.next()){
				this.nppId=rs.getString("pk_seq");
				this.nppTen= rs.getString("ten");
				this.sitecode=rs.getString("sitecode");
				this.dangnhap = rs.getString("dangnhap");
				this.setKhoSAP(rs.getString("khosap"));
				this.loaiNpp = rs.getString("LoaiNPP")==null?"": rs.getString("LoaiNPP");
				rs.close();
			}
		}catch(Exception er){

		}
		return this.nppId;
	}


	public boolean kiemtra_xnt_denngay_solo(String npp_fk,String kho_fk,String denngay,String sanpham_fk,String solo,String ngayhethan,dbutils db,String kbh_fk)
	{
		String[]  param1 = new String[6];

		param1[0]=npp_fk;
		param1[1]=kho_fk;
		param1[2]=denngay;
		param1[3]=sanpham_fk;
		param1[4]=solo;
		param1[5]=ngayhethan;
		ResultSet	rs1 = db.getRsByPro("GET_CHITIET_XNT_SP_LO", param1);
		double soluong_tong=0;
		try {
			while(rs1.next()){
				if(rs1.getString("kbh_fk").equals(kbh_fk))
				{
					if(rs1.getInt("soluong")>=0)
						return true;
					else
					{
						System.out.println("-------soluong "+rs1.getInt("soluong"));
						return false;
					}
				}

			}
			rs1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch blocksss
			e.printStackTrace();
		}

		return true;
	}


	public String kiemtra_xnt_denngay(String npp_fk,String kho_fk,String sanpham_fk,String ngaynhap,dbutils db,String kbh_fk,double soluongbook,String nghiepvu,double luongbookcu,String userid)
	{
		/*String[]  param1 = new String[5];

		param1[0]=kbh_fk;
		param1[1]=npp_fk;
		param1[2]=kho_fk;
		param1[3]=ngaynhap;
		param1[4]=sanpham_fk;
		double book=0;
		double tonhientai=0;
		ResultSet	rs1 = db.getRsByPro("GET_XNT_SP", param1);
		System.out.println("GET_XNT_SP " +kbh_fk+","+npp_fk+","+kho_fk+","+ngaynhap+","+sanpham_fk);
		String sanphamma="";
		String ngay="";
		try {
			while(rs1.next())
			{
				if(rs1.getString("kbh_fk").equals(kbh_fk))
				{
					if(rs1.getDouble("soluong")<0)
					{
						return "xnt tại thời điểm "+ngaynhap +" đang âm không thể tạo đơn hàng vui lòng chọn ngày khác !!!!";
					}
					else
					{


						String[]  param2 = new String[5];
						param2[0]=npp_fk;
						param2[1]=ngaynhap;
						param2[2]=sanpham_fk;
						param2[3]=kho_fk;
						param2[4]=userid;

						String que="select ma from sanpham where pk_seq="+sanpham_fk;
						ResultSet rscc=db.get(que);
						rscc.next();
						sanphamma=rscc.getString("ma");
						rscc.close();
						String query="select distinct ngaydonhang as ngaynhap from ufn_XNT_Total_SP_NgayPhatSinhNV("+npp_fk+",'"+ngaynhap+"',"+sanpham_fk+","+kho_fk+") "+
									" union all select '"+ngaynhap+"' ";
						//System.out.println("--------query "+query);
						ResultSet rs3=null;
						if(!nghiepvu.equals(""))
							rs3=db.getRsByPro("XNT_Total_SP_NgayPhatSinhNV", param2);
						else
							rs3=db.getRsByPro("XNT_Total_SP_NgayPhatSinhNV_khac", param2);


						double min=999999999;

						while(rs3.next())
						{
							String ngaychay=rs3.getString("ngaynhap");

							System.out.println("vao day 1"+ngaychay);
							String quString=" select spten,SANPHAM_fk,npp_fk,kho_fk,kbh_fk,total_booked  from ufn_Booked_Total_denngay("+npp_fk+",'"+ngaychay+"') where sanpham_fk= "+sanpham_fk +" and kbh_fk="+kbh_fk +" and kho_fk="+kho_fk ;
							System.out.println("vao cau nay ne "+quString);
							ResultSet rs2=db.get(quString);
							if(rs2.next())
							{
								book=rs2.getDouble("total_booked") - luongbookcu;
								//System.out.println("luong book la "+book );
							}
							rs2.close();
							double xntthoidhien=0;
							param1[0]=kbh_fk;
							param1[1]=npp_fk;
							param1[2]=kho_fk;
							param1[3]=ngaychay;
							param1[4]=sanpham_fk;
							ResultSet	rsxnt = db.getRsByPro("GET_XNT_SP", param1);
							System.out.println("EXEC   GET_XNT_SP " +kbh_fk+","+npp_fk+","+kho_fk+",'"+ngaychay+"',"+sanpham_fk);
							if(rsxnt.next())
							{
								tonhientai=rsxnt.getDouble("soluong")-book;
								xntthoidhien=rsxnt.getDouble("soluong");
							}
							else
								tonhientai=0-book;
							System.out.println("luong book "+book +"xnt --"+xntthoidhien+" --- luong dh "+soluongbook + "ngay nhap "+ngaychay);

							System.out.println("ton hien tai la  "+tonhientai +"-----"+soluongbook );
							if(tonhientai - soluongbook <0  )
							{
								//return "Tồn hiện tại tại thời điểm "+ngay+"   không cho phép đặt nếu đặt sẽ bị âm kho  sản phẩm " +sanphamma;
								return "tồn kho của ngày "+ngaychay+" chỉ có thể đặt tối đa "+ ( tonhientai ) +" cho sản phẩm" +sanphamma  +"không thể đặt vượt";
							}
							if(tonhientai  < 0  )
							{
								return "Tồn hiện tại tại thời điểm "+ngaychay+"   không cho phép đặt nếu đặt sẽ bị âm kho  sản phẩm " +sanphamma;
								//return "Bạn chỉ có thể đặt tối đa "+ (soluongbook - tonhientai ) +" cho sản phẩm" +sanphamma;
							}

						}
						rs3.close();








					}

				}

			}
			rs1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return "lỗi đơn hàng";
		}*/

		return "";
	}


	public String kiemtra_xnt_denhientai(String npp_fk,String kho_fk,String sanpham_fk,String ngaynhap,dbutils db,String kbh_fk,double soluongbook,String nghiepvu,double luongbookcu,String userid)
	{
		String[]  param1 = new String[5];

		param1[0]=kbh_fk;
		param1[1]=npp_fk;
		param1[2]=kho_fk;
		param1[3]=getDateTime();
		param1[4]=sanpham_fk;
		double book=0;
		double tonhientai=0;
		ResultSet	rs1 = db.getRsByPro("GET_XNT_SP", param1);
		System.out.println("GET_XNT_SP " +kbh_fk+","+npp_fk+","+kho_fk+","+getDateTime()+","+sanpham_fk);
		String sanphamma="";
		String ngay="";
		try {
			while(rs1.next())
			{
				System.out.println("soluong la -"+ luongbookcu + "---" +soluongbook);
				if((rs1.getDouble("soluong") + luongbookcu ) - soluongbook <0)
				{
					return "hết tồn kho sản phẩm !!!";
				}
			}
			rs1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return "lỗi đơn hàng";
		}

		return "";
	}



	public String getIdNhapp(String userId) 
	{

		dbutils db = new dbutils();
		String sql =
			"			select nv.dangnhap,npp.LoaiNPP, npp.quanlykho, npp.khosap, npp.pk_seq,npp.sitecode,npp.ten,npp.tructhuoc_fk  "+ 
			"			from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode   "+
			"			where nv.pk_seq='"+userId+"' and nv.trangthai='1'  and nv.PHANLOAI=1 and npp.isKHACHHANG = '0'  " +
			" 			and npp.TRANGTHAI = '1' "+ 
			"			union all  "+
			"			select nv.dangnhap,npp.LoaiNPP,npp.quanlykho, npp.khosap, npp.pk_seq,npp.sitecode,gs.ten,npp.tructhuoc_fk  "+ 
			"			from nhanvien nv inner join GIAMSATBANHANG gs on nv.GSBH_FK=gs.PK_SEQ  "+
			"				inner join NHAPHANPHOI npp on npp.SITECODE=nv.CONVSITECODE  "+
			"			where nv.pk_seq='"+userId+"' and nv.trangthai='1'  and nv.PHANLOAI=2 and gs.TRANGTHAI=1 and npp.isKHACHHANG = '0'  "+
			"			and npp.TRANGTHAI = '1' ";

		System.out.println("____ID_"+sql);

		ResultSet rs = db.get(sql);
		try
		{
			while(rs.next())
			{
				this.nppId=rs.getString("pk_seq");
				this.nppTen= rs.getString("ten");
				this.sitecode=rs.getString("tructhuoc_fk"); //Dùng biến sitecode cũ ICP để lưu lại loại NPP (CN1, CN2, đối tác, quầy...)
				this.dangnhap = rs.getString("dangnhap");
				this.setKhoSAP(rs.getString("khosap"));
				this.loaiNpp = rs.getString("loaiNPP")==null?"": rs.getString("loaiNPP");
			}
			if(rs!=null)rs.close();
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
		finally
		{
			db.shutDown();
		}
		return this.nppId;
	}

	public String getTenNhaPP(){
		return this.nppTen;
	}
	public String getSitecode(){
		return this.sitecode;
	}

	public String getDangNhap(){
		return this.dangnhap;
	}

	public int[] Getquyen(String servlet, String parameters, String userId)
	{

		int[] quyen = new int[6];
		int them =0;
		int xoa=0;
		int sua=0;
		int xem=0;
		int chot=0;
		int huychot=0;
		String query =	

			"	SELECT ISNULL(THEM,0) AS THEM,ISNULL(XOA,0) AS XOA,ISNULL(SUA,0) AS SUA,ISNULL(XEM,0) AS XEM,ISNULL(CHOT,0) AS CHOT, "+
			"	ISNULL(HUYCHOT,'0') AS HUYCHOT "+
			"	FROM NHOMQUYEN  A INNER JOIN PHANQUYEN B ON A.DMQ_FK = B.DMQ_FK  "+ 
			"	INNER JOIN UNGDUNG UD ON UD.PK_SEQ=A.UNGDUNG_FK  "+
			" WHERE B.NHANVIEN_FK='"+userId+"' AND UD.SERVLET='"+servlet+"' AND UD.PARAMETERS='"+parameters+"' ";

		System.out.println("[QUERY]"+query);

		dbutils db = new  dbutils();
		ResultSet rscheck= db.get(query);
		try
		{
			while(rscheck.next())
			{
				if(rscheck.getInt("THEM")!=0)
					them=rscheck.getInt("THEM");

				if(rscheck.getInt("XOA")!=0)
					xoa=rscheck.getInt("XOA");

				if(rscheck.getInt("SUA")!=0)
					sua=rscheck.getInt("SUA");

				if(rscheck.getInt("XEM")!=0)
					xem=rscheck.getInt("XEM");

				if(rscheck.getInt("CHOT")!=0)
					chot=rscheck.getInt("CHOT");

				if(rscheck.getInt("HuyChot")!=0)
					huychot=rscheck.getInt("HuyChot");

			}
			rscheck.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		quyen[THEM]=them;
		quyen[XOA]=xoa;
		quyen[SUA]=sua;
		quyen[XEM]=xem;
		quyen[CHOT]=chot;
		quyen[HUYCHOT]=huychot;
		return quyen;

	}


	public boolean isValidDate(String inDate) {

		if (inDate == null)
			return false;

		//set the format to use as a constructor argument
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		if (inDate.trim().length() != dateFormat.toPattern().length())
			return false;

		dateFormat.setLenient(false);

		try {
			//parse the inDate parameter
			dateFormat.parse(inDate.trim());
		}
		catch (ParseException pe) {
			return false;
		}
		return true;
	}

	public String getUserId(String querystring){
		String userId;
		String tmp;
		if (querystring != null){
			if (querystring.contains("&")){
				tmp = querystring.split("&")[0];
			}else{
				tmp = querystring;
			}
			userId = tmp.split("=")[1];
		}else{
			userId = "";
		}
		return userId;
	}
	public String getAction(String querystring){
		String action;
		String tmp;
		if (querystring != null){
			if (querystring.contains("&")){
				tmp = querystring.split("&")[1];
				action = tmp.split("=")[0];
			}else{
				action = "";
			}
		}else{
			action = "";
		}
		return action;
	}
	
	public String getView(String querystring){
		String action;
		String tmp;
		if (querystring != null){
			if (querystring.contains("&")){
				tmp = querystring.split("&")[1];
				action = tmp.split("=")[1];
			}else{
				action = "";
			}
		}else{
			action = "";
		}
		return action;
	}

	public String getId(String querystring){
		String id;
		String tmp;
		if (querystring != null){
			if (querystring.contains("&")){
				tmp = querystring.split("&")[1];
				id = tmp.split("=")[1];
			}else{
				id = "";
			}
		}else{
			id = "";
		}
		return id;

	}

	public Hashtable<Integer, String>  ArraystringToHashtable(String[] s){
		Hashtable<Integer, String> h = new Hashtable<Integer, String>();
		if(s != null){
			int size = s.length;
			int m = 0;
			while(m < size){
				h.put(new Integer(m), s[m]) ;
				m++;
			}
		}else{
			h.put(new Integer(0), "null");
		}
		return h;
	}

	public String[]  ResultSetToArrayString(ResultSet rs){
		String[] s = new String[10];
		try{
			int m = rs.getFetchSize();
			s = new String[m+1];		 	
			while(rs.next()){
				s[1] = rs.getString(1);
			}
		}catch(Exception e){}
		return s;
	}

	// tra ve nhung thanh phan cua s1 khong nam trong s2
	public String[] compareArrayString(String[] s1, String[] s2)
	{
		int i = s1.length;
		int j = s2.length;	

		String[] s = new String[i];
		int k = 0;
		for (int m = 0; m < i; m++){
			boolean result = true;
			for (int n = 0; n < j; n++){
				if (s1[m].equals(s2[n])){
					result = false;
				}
				if (result){
					s[k++]=s1[m];
				}
			}
		}
		return s;
	}

	public String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	public	boolean isNumeric(String input)
	{ 
		boolean result = true;
		char[] all = input.toCharArray();

		for(int i = 0; i < all.length;i++) {
			if(!(Character.isDigit(all[i]))) {
				result = false;
			}
		}
		return result;
	}

	public boolean checkHopLe(String userId)
	{
		dbutils db = new dbutils();
		String query = "select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode  where nv.pk_seq = '" + userId + "'";
		ResultSet rs = db.get(query);
		String nppId = "";
		int dakhoaso30 = 0;
		int dacodctk01 = 0;
		try 
		{
			if(rs.next())
			{
				nppId = rs.getString("pk_seq");
				rs.close();
			}
			query = "select count(*) as dakhoaso from khoasongay where ngayks = '2012-04-30' and npp_fk = '" + nppId + "'";
			rs = db.get(query);

			if(rs.next())
			{
				dakhoaso30 = rs.getInt("dakhoaso");
				rs.close();
			}

			if(dakhoaso30 == 0)  //chua khoa so ngay nay
				return true;

			query = "select count(npp_fk) as sodong from dieuchinhtonkho where npp_fk = '" + nppId + "' and trangthai = '1' and ngaydc = '2012-05-01'";
			rs = db.get(query);

			if(rs.next())
			{
				dacodctk01 = rs.getInt("sodong");
				rs.close();
			}

			if(dacodctk01 == 0)
				return false;

		} 
		catch(Exception e) { return false; }
		return true;
	}

	public String ngaykhoaso(String nhaphanphoi)
	{   
		String ngay = "";
		dbutils db = new dbutils();
		String sql ="select isnull(max(ngayks), '') as ngay from khoasongay where npp_fk ='"+ nhaphanphoi+"'";
		ResultSet rs = db.get(sql);
		try
		{
			if(rs != null)
			{
				rs.next();
				ngay = rs.getString("ngay");	
			}
			db.shutDown();
		}
		catch(Exception e){ db.shutDown(); }

		return ngay;
	}

	public void setKhoSAP(String khoSAP) {
		this.khoSAP = khoSAP;
	}
	public String getKhoSAP() {
		return khoSAP;
	}

	//chuyen tieng viet khong dau

	private static char[] SPECIAL_CHARACTERS = { ' ', '!', '"', '#', '$', '%',
		'*', '+', ',', ':', '<', '=', '>', '?', '@', '[', '\\', ']', '^',
		'`', '|', '~', 'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò',
		'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê',
		'ì', 'í', 'ò', 'ó', 'ô', 'õ', 'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ',
		'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ',
		'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ',
		'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ', 'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế',
		'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị',
		'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ',
		'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở', 'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ',
		'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', 'Ý', 'Ỳ', 'Ỵ', 'ỳ', 'ỵ', 'ý'};

	private static char[] REPLACEMENTS = { '-', '\0', '\0', '\0', '\0', '\0',
		'\0', '_', '\0', '_', '\0', '\0', '\0', '\0', '\0', '\0', '_',
		'\0', '\0', '\0', '\0', '\0', 'A', 'A', 'A', 'A', 'E', 'E', 'E',
		'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a',
		'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o', 'u', 'u', 'y', 'A',
		'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a',
		'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A',
		'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E', 'e',
		'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I',
		'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o',
		'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
		'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',
		'U', 'u', 'Y', 'Y', 'Y', 'y', 'y', 'y'};

	public String replaceAEIOU(String s) 
	{
		int maxLength = Math.min(s.length(), 236);
		char[] buffer = new char[maxLength];
		int n = 0;
		for (int i = 0; i < maxLength; i++) 
		{
			char ch = s.charAt(i);
			int index = Arrays.binarySearch(SPECIAL_CHARACTERS, ch);
			if (index >= 0) 
			{
				buffer[n] = REPLACEMENTS[index];
			} 
			else 
			{
				buffer[n] = ch;
			}
			// skip not printable characters
			if (buffer[n] > 31) {
				n++;
			}
		}

		// skip trailing slashes
		while (n > 0 && buffer[n - 1] == '/') 
		{
			n--;
		}
		return String.valueOf(buffer, 0, n);
	}

	public static String antiSQLInspection(String param)
	{
		String tmp = param;
		////System.out.println("Chuoi moi:" + tmp);

		String[] keywords = {" or ","delete","insert","update","create", "alter","drop","=","--", "select","\\(","\\)"};

		boolean trbl = false;

		if(tmp != null){
			//tmp = tmp.toLowerCase();
			for (int i = 0; i < keywords.length; i++){
				if( tmp.toLowerCase().contains(keywords[i])){
					tmp = tmp.replaceAll(keywords[i], "--");
					trbl = true;
					break;
				}
				//////System.out.println("Chuoi moi:" + tmp);
			}

		}

		//				////System.out.println("Chuoi moi:" + tmp);
		if(trbl == true)
			return tmp;
		else return param;
	}

	public String getTieuDe(String table,String column,String id ,geso.dms.distributor.db.sql.dbutils db)
	{
		String query=" select dbo.ftBoDau("+column+") from "+table+" where pk_seq in ("+id+")";
		ResultSet rs= db.get(query);
		String tieude="";

		try
		{
			while(rs.next())
			{
				tieude +=java.net.URLDecoder.decode(rs.getString(1).replaceAll("%(?![0-9a-fA-F]{2})", "%25").replace(" ", "-"), "UTF-8");
			}
			rs.close();
		}catch(Exception er)
		{
			er.printStackTrace();
		}		
		return tieude;
	}

	public static String replacer(StringBuffer outBuffer) 
	{
		String data = outBuffer.toString();
		try {
			data = data.replaceAll("%(?![0-9a-fA-F]{2})", "%25");
			data = data.replaceAll("+", "%2B");
			data = URLDecoder.decode(data, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	public String setTieuDe(IStockintransit obj )
	{
		String tieude="";
		dbutils db = new dbutils();

		if(obj.getkenhId()!=null && obj.getkenhId().length()>0)
		{
			tieude += this.getTieuDe( "KenhBanHang","Ten", obj.getkenhId(), db )+ "_"; 
		}
		if(obj.getdvkdId()!=null && obj.getdvkdId().length()>0)
		{
			tieude += this.getTieuDe( "DonViKinhDoanh","DienGiai", obj.getdvkdId(),db ) + "_" ;
		}

		if(obj.getvungId()!=null && obj.getvungId().length()>0)
		{
			tieude += this.getTieuDe( "Vung","Ten", obj.getvungId(),db ) + "_" ;
		}

		if(obj.getkhuvucId()!=null && obj.getkhuvucId().length()>0)
		{
			tieude += this.getTieuDe( "KhuVuc","Ten", obj.getkhuvucId(),db ) + "_" ;
		}

		if(obj.getnppId()!=null && obj.getnppId().length()>0)
		{
			tieude += this.getTieuDe( "NhaPhanPhoi","Ten", obj.getnppId(), db ) + "_" ;
		}

		if(obj.getASMId()!=null && obj.getASMId().length()>0)
		{
			tieude +=this.getTieuDe( "ASM", "Ten", obj.getASMId(), db )+ "_"; 
		}
		if(obj.getBMId()!=null && obj.getBMId().length()>0)
		{
			tieude +=this.getTieuDe( "BM","Ten", obj.getBMId(), db )+ "_"; 
		}

		if(obj.getgsbhId()!=null && obj.getgsbhId().length()>0)
		{
			tieude += this.getTieuDe( "GiamSatBanHang", "Ten", obj.getgsbhId(),db ) + "_" ;
		}
		if(obj.getDdkd()!=null && obj.getDdkd().length()>0)
		{
			tieude += this.getTieuDe( "DaiDienKinhDoanh", "Ten", obj.getDdkd(),db ) + "_" ;
		}
		if(obj.getnhanhangId()!=null && obj.getnhanhangId().length()>0 )
		{
			tieude += this.getTieuDe( "NhanHang","Ten", obj.getnhanhangId(),db ) + "_" ;
		}
		if(obj.getchungloaiId()!=null && obj.getchungloaiId().length()>0 )
		{
			tieude += this.getTieuDe( "ChungLoai","Ten", obj.getchungloaiId(),db ) + "_" ;
		}
		if(obj.getNspId()!=null && obj.getNspId().length()>0 )
		{
			tieude += this.getTieuDe( "NhomSanPham","Ten", obj.getNspId(),db ) + "_" ;
		}
		if(obj.getsanphamId()!=null && obj.getsanphamId().length()>0 )
		{
			tieude += this.getTieuDe( "SanPham","Ten", obj.getsanphamId(),db ) + "_" ;
		}
		if(obj.getctkmtlId()!=null && obj.getctkmtlId().length()>0)
		{
			tieude +=this.getTieuDe( "CTKHUYENMAI","DienGiai", obj.getctkmtlId(), db )+ "_"; 
		}
		if(obj.getPrograms()!=null && obj.getPrograms().length()>0)
		{
			tieude +=this.getTieuDe( "CTKHUYENMAI","DienGiai", obj.getPrograms(), db )+ "_"; 
		}
		if(obj.getpromotion()!=null && obj.getpromotion().length()>0)
		{
			tieude +=this.getTieuDe( "CTKHUYENMAI","DienGiai", obj.getpromotion(), db )+ "_"; 
		}

		if(obj.getdvdlId()!=null && obj.getdvdlId().length()>0)
		{
			tieude +=this.getTieuDe( "DonViDoLuong","DonVi", obj.getdvdlId(), db )+ "_"; 
		}
		if(obj.gettungay()!=null && obj.gettungay().length()>0)
		{
			tieude += obj.gettungay() + "_";
		}
		if(obj.getdenngay()!=null && obj.getdenngay().length()>0)
		{
			tieude += obj.getdenngay() + "_";
		}
		if(obj.getFromMonth()!=null && obj.getFromMonth().length()>0)
		{
			tieude += obj.getFromMonth() + "_";
		}
		if(obj.getFromYear()!=null && obj.getFromYear().length()>0)
		{
			tieude += obj.getFromYear() + "_";
		}
		if(obj.getToMonth()!=null && obj.getToMonth().length()>0)
		{
			tieude += obj.getToMonth() + "_";
		}
		if(obj.getToYear()!=null && obj.getToYear().length()>0)
		{
			tieude += obj.getToYear() + "_";
		}

		db.shutDown();
		return tieude;
	}

	public String setTieuDe(Reports obj )
	{
		String tieude="";
		dbutils db = new dbutils();

		if(obj.getKenhId()!=null && obj.getKenhId().length()>0)
		{
			tieude +=this.getTieuDe( "KenhBanHang","Ten", obj.getKenhId(), db )+ "_"; 
		}

		if(obj.getVungId()!=null && obj.getVungId().length()>0)
		{
			tieude += this.getTieuDe( "Vung","Ten", obj.getVungId(),db ) + "_" ;
		}

		if(obj.getKhuVucId()!=null && obj.getKhuVucId().length()>0)
		{
			tieude += this.getTieuDe( "KhuVuc","Ten", obj.getKhuVucId(),db ) + "_" ;
		}

		if(obj.getNppId()!=null && obj.getNppId().length()>0)
		{
			tieude += this.getTieuDe( "NhaPhanPhoi","Ten", obj.getNppId(), db ) + "_" ;
		}

		if(obj.getcttbid()!=null && obj.getcttbid().length()>0)
		{
			tieude +=this.getTieuDe( "CTTRUNGBAY","DienGiai", obj.getcttbid(), db )+ " "; 
		}
		if(obj.getNhanHang()!=null && obj.getNhanHang().length()>0)
		{
			tieude +=this.getTieuDe( "NhanHang","Ten", obj.getNhanHang(), db )+ "_"; 
		}

		if(obj.getSanPhamId()!=null && obj.getSanPhamId().length()>0 )
		{
			tieude += this.getTieuDe( "SanPham","Ten", obj.getSanPhamId(),db ) + "_" ;
		}
		if(obj.getSKU()!=null && obj.getSKU().length()>0 )
		{
			tieude += this.getTieuDe( "SanPham","Ten", obj.getSKU(),db ) + "_" ;
		}		    
		if(obj.getTuNgay()!=null && obj.getTuNgay().length()>0)
		{
			tieude += obj.getTuNgay() + "_";
		}

		db.shutDown();
		return tieude;
	}

	public String setTieuDe(ITieuchithuongTLList obj )
	{
		String tieude="_";
		dbutils db = new dbutils();

		if(obj.getVungId()!=null && obj.getVungId().length()>0)
		{
			tieude += this.getTieuDe( "Vung","Ten", obj.getVungId(),db ) + "_" ;
		}

		if(obj.getKvId()!=null && obj.getKvId().length()>0)
		{
			tieude += this.getTieuDe( "KhuVuc","Ten", obj.getKvId(),db ) + "_" ;
		}

		if(obj.getNppIds()!=null && obj.getNppIds().length()>0)
		{
			tieude += this.getTieuDe( "NhaPhanPhoi","Ten", obj.getNppIds(), db ) + "_" ;
		}
		if(obj.getSchemeIds()!=null && obj.getSchemeIds().length()>0)
		{
			tieude += this.getTieuDe( "CTKhuyenMai","DienGiai", obj.getSchemeIds(), db ) + "_" ;
		}
		if(obj.getNam()!=null && obj.getNam().length()>0)
		{
			tieude += obj.getNam() +"_";
		}
		if(obj.getThang()!=null && obj.getThang().length()>0)
		{
			tieude += obj.getThang() +"_";
		}

		if(obj.getTungay()!=null && obj.getTungay().length()>0)
		{
			tieude += obj.getTungay() +"_";
		}
		if(obj.getDenngay()!=null && obj.getDenngay().length()>0)
		{
			tieude += obj.getDenngay() +"_";
		}
		db.shutDown();
		return tieude;
	}

	public String setTieuDe(IDRouter obj)
	{
		String tieude = "_";
		dbutils db = new dbutils();
		if (obj.getnppId() != null && obj.getnppId().length() > 0)
		{
			tieude += this.getTieuDe("NhaPhanPhoi", "Ten", obj.getnppId(), db) + "_" ;
		}

		if (obj.getkhuvucId()!= null && obj.getkhuvucId().length() > 0)
		{
			tieude += this.getTieuDe("KhuVuc", "Ten", obj.getkhuvucId(), db) + "_" ;
		}

		if (obj.getddkdId()!=null && obj.getddkdId().length() > 0)
		{
			tieude += this.getTieuDe("DaiDienKinhDoanh", "Ten", obj.getddkdId(), db ) + "_" ;
		}
		db.shutDown();
		
		return tieude;
	}

	public String setTieuDe(ILoTrinh obj) 
	{
		String tieude="_";
		dbutils db = new dbutils();
		if(obj.getnppId()!=null && obj.getnppId().length()>0)
		{
			tieude += this.getTieuDe( "NhaPhanPhoi","Ten", obj.getnppId(),db ) + "_" ;
		}

		if(obj.getkhuvucId()!=null && obj.getkhuvucId().length()>0)
		{
			tieude += this.getTieuDe( "KhuVuc","Ten", obj.getkhuvucId(),db ) + "_" ;
		}

		if(obj.getddkdId()!=null && obj.getddkdId().length()>0)
		{
			tieude += this.getTieuDe( "DaiDienKinhDoanh","Ten", obj.getddkdId(), db ) + "_" ;
		}
		db.shutDown();
		return tieude;
	}


	//CAP NHAT TONG GIA TRI DON HANG
	public static String Update_GiaTri_DonHang(String dhId, dbutils db)
	{
		try{
			
			String query = " update DONHANG_SANPHAM set tienkhuyenmai = 0 where donhang_Fk = " + dhId;
			db.update(query);
			
			
			 query = " select a.CTKMID,a.TONGGIATRI, dk.doanhso, dk.countsp\r\n" + 
					"from DONHANG_CTKM_TRAKM a\r\n" + 
					"cross apply\r\n" + 
					"(\r\n" + 
					"	select sum(doanhso)doanhso,count(distinct sanpham_fk) countsp from DONHANG_CTKM_DKKM x \r\n" + 
					"	where x.donhang_fk = a.DONHANGID and x.ctkm_fk  = a.CTKMID\r\n" + 
					")dk\r\n" + 
					"where a.donhangID = "+dhId+" and a.sanpham_fk is null " ;
			
			ResultSet rs= db.get(query);
			while(rs.next())
			{
				String ctkmId = rs.getString("CTKMID");
				double tienkhuyenmaiTong = rs.getDouble("TONGGIATRI");
				double doanhsoTong = rs.getDouble("doanhso");
				int countsp = rs.getInt("countsp");
				
				
				
				query = " with kq as\r\n" + 
						"(\r\n" + 
						"	select ROW_NUMBER() OVER (PARTITION BY 1      ORDER BY ( donhang_fk  )) RN, round( " + tienkhuyenmaiTong + " * a.doanhso/ " + doanhsoTong + ",0) tienkm\r\n" + 
						"		, dh.*\r\n" + 
						"	from DONHANG_SANPHAM dh\r\n" + 
						"	cross apply\r\n" + 
						"	(\r\n" + 
						"		select sum(x.soluongmua)soluongmua , sum(x.doanhso)doanhso \r\n" + 
						"		from DONHANG_CTKM_DKKM x where  x.donhang_fk = dh.DONHANG_FK and x.ctkm_fk ="+ctkmId+"\r\n" + 
						"			and x.sanpham_fk = dh.SANPHAM_FK\r\n" + 
						"	)a\r\n" + 
						"	where dh.donhang_fk =  "+dhId+" \r\n" + 
						")\r\n" + 
						"update kq set tienkhuyenmai = isnull(tienkhuyenmai,0) + case when rn = "+countsp+" then " + tienkhuyenmaiTong + " - isnull(( select sum(isnull(tienkm,0)) from kq where rn <> "+countsp+" ),0) else  isnull(tienkm,0) end ";
				if(!db.update(query)) { return "Exception: promotion amount "; }
				
				
				
			}
			query = " update DONHANG_SANPHAM set thanhtien = round( soluong* giamua - isnull(tienkhuyenmai,0),0)\r\n" + 
					",	tienvat =	round( round( soluong* giamua - isnull(tienkhuyenmai,0),0) * THUEVAT/100.0,0)\r\n" + 
					" where donhang_Fk = " + dhId;
			if(!db.update(query)) 
			{ 
				return "Exception: promotion amount "; 
			}
			
			
			
			 query ="\n update dh set TONGGIATRI = tgt.tongtien  \r\n" + 
			 		"from donhang dh\r\n" + 
			 		"cross apply\r\n" + 
			 		"(\r\n" + 
			 		"	select  sum( thanhtien +  tienvat ) tongtien from DONHANG_SANPHAM dhsp where  dhsp.donhang_fk = dh.pk_seq\r\n" + 
			 		")tgt\r\n" + 
			 		
			 		"where dh.PK_SEQ = " + dhId;
			
			if( db.updateReturnInt(query)!=1 )
			{
				return "Lỗi cập nhật tổng giá trị đơn hàng";
			}
	
		
			return "";
				
				
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "Exception:" + ex.getMessage();
		}

	}
	
	public static void main(String[] args) {
		
		dbutils db = new dbutils();
		try
		{
			db.getConnection().setAutoCommit(false);
			String kq =Update_GiaTri_HoaDon( "1000004593",  db);
			if(kq.trim().length() > 0)
			{
				db.getConnection().rollback();
			}
			else
				db.getConnection().commit();
			
			System.out.println("kq = "+ kq);
			
		}
		catch (Exception e) {
			db.update("rollback");
		}
		finally
		{
			db.shutDown();
		}
		
	}
	

	
	//CAP NHAT TONG GIA TRI DON HANG
	public static String Update_GiaTri_HoaDon(String hdId, Idbutils db)
	{
		String msg="";
		try{
			
			String tienkm = " isnull((select SUM(ISNULL(tonggiatri,0)) from HOADON_DDH a inner join DONHANG_CTKM_TRAKM b on a.DDH_FK=b.DONHANGID where a.HOADON_FK='"+hdId+"' and b.SPMA is null),0) ";
			
			String 

			query = "\nupdate hd set " +
		            "\n		hd.tongtienAVAT =  round( TGT.avat - "+tienkm+"  ,0)    " +
		            "\n		, hd.tongtienbVAT =  round( TGT.bvat - "+tienkm+"/(1.0 + TGT.vat/100.0 )  ,0)" +
		            "\n		,  hd.vat =		isnull(TGT.tienvat,0) " +
		            "\n from HoaDon hd " +
		            "\n inner join " +
		            "\n ( " +
		            "\n    	select  VAT,sum((SOLUONG * dongia) * ( 1 + VAT/100.0)) avat, sum(SOLUONG * dongia)bvat,sum((SOLUONG * dongia) * ( VAT/100.0)) tienvat   " +
		            "\n		from HOADON_SP where hoadon_fk  = " + hdId +
		            "\n		GROUP by VAT "+
		            "\n )TGT on 1=1 where hd.pk_seq = '" + hdId + "' ";

			System.out.println("cap nhat hoa don:" + query);
			if( db.updateReturnInt(query) !=1 )
			{
				return "Lỗi:" + query;
			}
	
		
		
				
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "Lỗi :" + ex.getMessage();
		}

		return msg;
	}
	
	
	

	//CAP NHAT TONG GIA TRI DON HANG
	public static String CapNhatTongGiamTru(String hdId, Idbutils db)
	{
		String  query = "";
		try
		{
			query = " select top 1 DDH_FK from HOADON_DDH where HoaDon_FK  = "+ hdId + ""; 
			ResultSet rs = db.get(query);
			rs.next();
			String dhId = rs.getString("DDH_FK");
			query =  "\n select isnull( (select SUM (tonggiatri) from DONHANG_CTKM_TRAKM where DONHANGID=  "+dhId+" and SPMA is null),0) + isnull( (select isnull(tiengiamtru,0) from DONHANG where PK_SEQ =  "+dhId+"),0) tonggiamtru  " + //+ isnull( (select  sum(round( soluong*giamua* chietkhaungay/100.0 ,0)) from DONHANG_SANPHAM where DONHANG_FK =  "+dhId+"),0)
				 	 "\n 		, (select COUNT(*) from HOADON_SP_CHITIET where hoadon_fk = "+ hdId + " ) sodong  " +
				 	 "\n 		, (select sum(floor(SOLUONG*DONGIA))  from HOADON_SP_CHITIET where hoadon_fk = "+ hdId + " )doanhso	" ;																			 
			rs = db.get(query);
			rs.next();
			double tonggiamtru = rs.getDouble("tonggiamtru");
			int sodong = rs.getInt("sodong");
			double doanhso  = rs.getDouble("doanhso");
			int dem = 0;
			System.out.println("giam tru don hang = "+ tonggiamtru +", sosp = " + sodong +", doanhso  = "+ doanhso);
			query = " select ma,SOLO,NGAYHETHAN,NGAYNHAPKHO,floor(SOLUONG*DONGIA)thanhtien from HOADON_SP_CHITIET where hoadon_fk =  "+ hdId + " ";
			rs = db.get(query);
			while(rs.next())
			{
				dem ++;
				double thanhtien = rs.getDouble("thanhtien");
				String masp  = rs.getString("ma");
				String SOLO  = rs.getString("SOLO");
				String NGAYHETHAN  = rs.getString("NGAYHETHAN");
				String NGAYNHAPKHO  = rs.getString("NGAYNHAPKHO");
				
				System.out.println("ty trong( "+ masp +")  = " + thanhtien +"/"+ doanhso +" = "+ (thanhtien/doanhso));
				
				if(dem == sodong)
					query =" update HOADON_SP_CHITIET  " +
						   " 	set tonggiamtru =  "+tonggiamtru+"  " +
						   " 					- isnull( ( select sum( isnull(tonggiamtru,0) ) from HOADON_SP_CHITIET where hoadon_fk =  "+ hdId + "  and  ( ma !='"+masp+"' or  SOLO !='"+SOLO+"' or  NGAYHETHAN !='"+NGAYHETHAN+"' or  NGAYNHAPKHO !='"+NGAYNHAPKHO+"' )    )  ,0) " +
						   " where hoadon_fk ="+hdId+"  and  ma ='"+masp+"' and  SOLO ='"+SOLO+"' and  NGAYHETHAN ='"+NGAYHETHAN+"' and  NGAYNHAPKHO ='"+NGAYNHAPKHO+"'     ";
				else
					query ="\n update HOADON_SP_CHITIET  " +
						   "\n 		set tonggiamtru =  Round (  (    "+tonggiamtru+"*(" + thanhtien +"/"+ doanhso +")    )   ,0 )    " +
						   "\n where hoadon_fk ="+hdId+"  and  ma ='"+masp+"' and  SOLO ='"+SOLO+"' and  NGAYHETHAN ='"+NGAYHETHAN+"' and  NGAYNHAPKHO ='"+NGAYNHAPKHO+"'     ";
				if(db.updateReturnInt(query)!=1)
				{
					return "Lỗi:"+ query;
				}
			}
			return "";
			
			
		}
		catch(Exception ex){
			
			ex.printStackTrace();
			return "Lỗi ngoại lệ"+ ex.getMessage() +"("+query+")";
		}
	}


	public void Update_SoHoaDon( String dhId, dbutils db )
	{
		String query =   "update A set  "+
		"	A.sohoadon =  STUFF (         "+
		"			(        "+
		"				select DISTINCT TOP 100 PERCENT ' , ' + RTRIM(ltrim(isnull(aa.pk_seq,''))) + ' ' + isnull(cast(aa.LOAIHOADON as nvarchar),'')   "+
		"				from HOADON aa inner join HOADON_DDH bb on bb.HOADON_FK=aa.PK_SEQ      "+
		"				where aa.TRANGTHAI in ( 2, 4 ) and bb.DDH_FK = A.PK_SEQ      "+
		"				ORDER BY ' , ' +  RTRIM(ltrim(isnull(aa.pk_seq,''))) + ' ' + isnull(cast(aa.LOAIHOADON as nvarchar),'')     "+
		"				FOR XML PATH('')           "+
		"			 ), 1, 2, ''        "+
		"			)  "+
		"from DONHANG A  "+
		"where A.PK_SEQ in ( " + dhId + " ) ";
		db.update(query);
	}

	//CAP NHAT TONG GIA TRI DON HANG
	public int CompareDATE(String _date1, String _date2)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//Date date1 = sdf.parse("2014-10-01");
			//Date date2 = sdf.parse("2014-10-01");

			Date date1 = sdf.parse(_date1);
			Date date2 = sdf.parse(_date2);

			//System.out.println(sdf.format(date1));
			//System.out.println(sdf.format(date2));

			return date1.compareTo(date2);
		}
		catch (Exception e) {
			return 0;
		}
	}

	public String Check_Kho_Tong_VS_KhoCT(String nppId, dbutils db)
	{
		String query =  " select count( * ) as soDONG  "+
		" from  "+ 
		" ( " +
		"	 select npp_fk, kbh_fk, kho_fk, sanpham_fk, sum(available) as available, sum(soluong) as soluong, sum(booked) as booked_ct  "+
		"	 from nhapp_kho_chitiet where npp_fk = '" + nppId + "' "+
		"	 group by kbh_fk, npp_fk, kho_fk, sanpham_fk	  "+
		" ) " +
		" CT full outer join nhapp_kho total on total.npp_fk=ct.npp_fk and total.kbh_fk=ct.kbh_fk  "+
		"		and total.sanpham_fk=ct.sanpham_fk and total.kho_fk=ct.kho_fk   "+
		" where    "+
		"		( round(isnull(ct.available,0),0) + round(isnull(ct.booked_ct,0),0) != round(isnull(total.available,0),0) + round(isnull(total.booked ,0),0)  "+ 
		"			or round(isnull(total.soluong,0),0) != round(isnull(ct.soluong,0),0)  "+ 
		"		) and  isnull(total.npp_fk, ct.npp_fk) = '" + nppId + "' ";

		System.out.println("Check_Kho_Tong_VS_KhoCT " + query);
		String msg = "";
		ResultSet rs = db.get(query);
		try
		{
			if(rs.next())
			{
				if(rs.getInt("soDONG") > 0 )
				{
					//FIX tự động nếu có bị lệch tổng và chi tiết

					//FixData fixed = new FixData();
					//String error = fixed.ProcessDATA(nppId, "");

					/*if(error.trim().length() > 0) //Không thể tự động FIX được
					{
						rs.close();
						msg = "Lỗi phát sinh do lệch Số lượng của sản phẩm " + msg ;

						//SEND MAIL CANH BAO
						SendMail mail = new SendMail();

						String tb = "1.Hệ thống đã chạy chế độ tự sửa lỗi tồn kho. Nhưng gặp lỗi khi chạy của NPP ( " + nppId + " ). Vui lòng kiểm tra và xử lý gấp.";
						mail.postMailHTML("vudq@geso.us,xuantvt@geso.us,taiba@geso.us,hienttd@geso.us", "haind@geso.us,luonghv@geso.us", "TraphacoDMS chạy tồn kho tự động ", tb);


						return msg;
					}*/

					msg = "Lỗi phát sinh do lệch Số lượng của sản phẩm " ;

				}
			}
			rs.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Lỗi phát sinh khi check Tồn Kho";
		}

		return msg;
	}

	public String Check_Kho_Tong_VS_KhoCT(String nppId)
	{
		String query =  " select count( * ) as soDONG  "+
		" from  "+ 
		" ( " +
		"	 select npp_fk, kbh_fk, kho_fk, sanpham_fk, sum(available) as available, sum(soluong) as soluong, sum(booked) as booked_ct  "+
		"	 from nhapp_kho_chitiet where npp_fk = '" + nppId + "' "+
		"	 group by kbh_fk, npp_fk, kho_fk, sanpham_fk	  "+
		" ) " +
		" CT full outer join nhapp_kho total on total.npp_fk=ct.npp_fk and total.kbh_fk=ct.kbh_fk  "+
		"		and total.sanpham_fk=ct.sanpham_fk and total.kho_fk=ct.kho_fk   "+
		" where    "+
		"		( isnull(ct.available,0) + isnull(ct.booked_ct,0) != isnull(total.available,0) + isnull(total.booked ,0)  "+ 
		"			or isnull(total.soluong,0) != isnull(ct.soluong,0)  "+ 
		"		) and  isnull(total.npp_fk, ct.npp_fk) = '" + nppId + "' ";

		System.out.println("Check_Kho_Tong_VS_KhoCT " + query);
		String msg = "";
		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		try
		{
			if(rs.next())
			{
				if(rs.getInt("soDONG") > 0 )
				{
					//FIX tự động nếu có bị lệch tổng và chi tiết
					/*FixData fixed = new FixData();
					String error = fixed.ProcessDATA(nppId, "");

					if(error.trim().length() > 0) //Không thể tự động FIX được
					{
						rs.close();
						msg = "Lỗi phát sinh do lệch Số lượng của sản phẩm " + msg ;

						//SEND MAIL CANH BAO
						SendMail mail = new SendMail();

						String tb = "1.Hệ thống đã chạy chế độ tự sửa lỗi tồn kho. Nhưng gặp lỗi khi chạy của NPP ( " + nppId + " ). Vui lòng kiểm tra và xử lý gấp.";
						mail.postMailHTML("vudq@geso.us,xuantvt@geso.us,taiba@geso.us,hienttd@geso.us", "haind@geso.us,luonghv@geso.us", "TraphacoDMS chạy tồn kho tự động ", tb);


						return msg;
					}*/

					msg = "Lỗi phát sinh do lệch Số lượng của sản phẩm ";
				}
			}
			rs.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Lỗi phát sinh khi check Tồn Kho";
		}

		return msg;
	}

	

	//Khi sửa bắt kỳ ghiệp vụ gì liên quan tới tồn kho trong quá khú, mà khác ngày hiện tại đều phải check NXT tới ngày đó có ok không
	////Cái này sẽ gài khi kho đã được xử lý hết
	public String Check_NghiepVu_QuaKhu(String ngaynghiepvu, String nppId, String khoId, String kbhId, String spId, String soluong, dbutils db)
	{
		//Chỉ cần check các nghiệp vụ liên quan tới xuất, nhập
		/*String tableNAME; 
		String nghiepvuId; 
		String spIds = "";
		if(tableNAME.toUpperCase().equals("DONHANG"))
		{
			spIds = " select sanpham_fk from DONHANG_SANPHAM where donhang_fk = '" + nghiepvuId + "' ";
		}
		else if(tableNAME.toUpperCase().equals("HOADON"))
		{
			spIds = " select sanpham_fk from HOADON_SANPHAM where hoadon_fk = '" + nghiepvuId + "' ";
		}
		else if(tableNAME.toUpperCase().equals("NHAPHANG"))
		{
			spIds = " select sanpham_fk from NHAPHANG_SP where nhaphang_fk = '" + nghiepvuId + "' ";
		}

		if(spId.trim().length() > 0)
			spIds += " AND sanpham_fk = '" + spIds + "' ";*/

		/*//B1. Check NXT tới thời điểm hiện tại không được âm -> NXT tới hiện tại chính là tồn kho hiện tại
		String query = " select XNT from ufn_XNT_Check_NV_QuaKhu_Total ( " + nppId + ", " + spId + ", '', '' ) ";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					if( rs.getDouble("XNT") < Double.parseDouble(soluong) )
					{
						rs.close();
						return "Theo nhập xuất tồn hiện tại bạn chỉ có thể cập nhật số lượng của sản phẩm ( " + spId + " ) tới ( " + rs.getDouble("XNT") + " ) ";
					}
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				return e.getMessage();
			}
		}
		 */
		//B2. Check NXT từ ngày làm nghiệp vụ tới ngày hiện tại không được âm ( từng ngày 1 )
		/*	String now = this.getDateTime();
		ngaynghiepvu = TangNgay(-1, ngaynghiepvu);
		System.out.println("ngay hien tai"+now);

		while( now.length() > 0 )
		{
			//CHECK NXT
			String tungay = ngaynghiepvu;
			String denngay = TangNgay(1, ngaynghiepvu);
			System.out.println("--TU NGAY::: " + tungay + " -- DEN NGAY::: " + denngay);

			//CHECK TUNG NGAY CO BI AM NXT KHONG
			String query = "select NXT from ufn_XNT_Total_FULL ( '" + tungay + "', '" + denngay + "', " + nppId + ", " + spId + ", " + khoId + " ) where kbh_fk = '100025' and NXT - " + soluong + " < 0 ";
			System.out.println("---CAU LENH CHECK: " + query);
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				try 
				{
					if(rs.next())
					{
						double NXT = rs.getDouble("NXT");
						double cotheXUAT = 0;
						if(NXT > 0 )
							cotheXUAT = NXT;

						rs.close();
						System.out.println("---CAU LENH CHECK xnt am : " + query);
						return "Theo nhập xuất tồn tới ngày ( " + denngay + " ) bạn chỉ có thể xuất tối đa ( " + cotheXUAT + " ), vượt quá số lượng muốn xuất ( " + soluong + " ) ";
					}
					rs.close();
				} 
				catch (Exception e) { }
			}

			if(ngaynghiepvu.equals(now))
				break;
			ngaynghiepvu = TangNgay(1, ngaynghiepvu);	
			System.out.println(ngaynghiepvu+"ngay hien tai"+now);*/

		//	}

		//	System.out.println("---CAHY XONG....");
		return "";
	}

	public boolean check_book_chitiet_ngaynhap(dbutils db,String npp_fk,String sanpham_fk ,String kbh_fk,String kho_fk,String solo,String ngayhethan,String ngaynhapkho)
	{
		/*int flag=0;
		String quString=" select count(*) as sl  from ufn_Booked_ChiTiet_nhapkho ("+npp_fk+", "+sanpham_fk+") a \n"+
						" full outer join NHAPP_KHO_CHITIET b  \n"+
						"  on a.npp_fk=b.NPP_FK and a.sanpham_fk=b.SANPHAM_FK and a.kho_fk=b.KHO_FK \n"+
						"  and a.kbh_fk=b.KBH_FK and a.solo=b.SOLO and a.ngayhethan=b.NGAYHETHAN \n"+
						"  and a.ngaynhapkho=b.ngaynhapkho \n"+
						"  where ISNULL (a.booked_Sql,0)<>ISNULL(b.BOOKED,0) "
						+ "\n  and isnull( a.npp_fk,b.NPP_FK)="+npp_fk+" and isnull( a.sanpham_fk,b.SANPHAM_FK)="+sanpham_fk+" and isnull( a.kho_fk,b.KHO_FK)="+kho_fk+""
						+ "\n  and isnull(a.kbh_fk,b.KBH_FK)="+kbh_fk+" and isnull(a.solo,b.SOLO)='"+solo+"' and isnull( a.ngayhethan,b.NGAYHETHAN)='"+ngayhethan+"' and isnull(a.ngaynhapkho,b.ngaynhapkho)='"+ngaynhapkho+"' ";

			ResultSet rs=db.get(quString);
			try {
				if(rs.next())
				{
					if(rs.getInt("sl")==0)
					{
						flag=1;
					}
					else
					{
						flag=0;

					}
				}
				else
				{
					flag=0;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(flag==1)
				return true;
			else
				return false;*/
		return true;

	} 

	private String TangNgay(int songay, String ngaythang) 
	{
		String kq = "";

		String[] arr = ngaythang.split("-");

		// lấy thời điểm bây giờ: 
		Calendar c = Calendar.getInstance(); 

		c.set(Calendar.YEAR, Integer.parseInt(arr[0]));
		c.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
		c.set(Calendar.DATE, Integer.parseInt(arr[2]));

		c.add(Calendar.DATE, songay);

		//System.out.println("---THANG NEW::: " + ( c.get(Calendar.MONTH) + 1 ) + " -- NAM NEW::: " + c.get(Calendar.YEAR)  +  "  -- NGAY NEW::: " + ( c.get(Calendar.DATE) + 1 ) );

		String thangNEW = ( c.get(Calendar.MONTH) + 1 ) < 10 ? ( "0" + Integer.toString(( c.get(Calendar.MONTH) + 1 ) )) : Integer.toString( c.get(Calendar.MONTH) + 1 );
		String ngayNEW = ( c.get(Calendar.DATE) ) < 10 ? ( "0" + Integer.toString(c.get(Calendar.DATE))) : Integer.toString( c.get(Calendar.DATE) );

		kq = c.get(Calendar.YEAR) + "-" + thangNEW + "-" + ngayNEW;

		//System.out.println("---KQ::: " + kq);
		return kq;
	}

/*
	public static void main(String[] arg)
	{
		Utility util = new Utility();
		dbutils db = new dbutils();

		String query = " SELECT PK_SEQ FROM NHAPHANPHOI WHERE isKHACHHANG = 0 AND PK_SEQ != 1 ";

		ResultSet rs = db.get(query);
		String npp_fk = "";
		String msg = "";

		try
		{
			if(rs!=null)
			{
				while(rs.next())
				{
					npp_fk = rs.getString("PK_SEQ");

					query = " INSERT ERP_TAIKHOANKT (SOHIEUTAIKHOAN, TENTAIKHOAN, LOAITAIKHOAN_FK, TAIKHOANCOCHITIET, NGUOITAO, NGUOISUA, NGAYSUA, TRANGTHAI, CONGTY_FK, \n"+
					" DUNGCHOKHO, DUNGCHONGANHANG, DUNGCHONCC, DUNGCHOTAISAN, DUNGCHOKHACHHANG, COTTDOANHTHU, DUNGCHOCOPHIEU, DUNGCHOCONGTYCON, DUNGCHONHANVIEN, DUNGCHODOITUONGKYQUY,  npp_fk) \n"+
					" select SOHIEUTAIKHOAN, TENTAIKHOAN, LOAITAIKHOAN_FK, TAIKHOANCOCHITIET, NGUOITAO, NGUOISUA, NGAYSUA, TRANGTHAI, NULL CONGTY_FK, \n"+
					" DUNGCHOKHO, DUNGCHONGANHANG, DUNGCHONCC, DUNGCHOTAISAN, DUNGCHOKHACHHANG, COTTDOANHTHU, DUNGCHOCOPHIEU, DUNGCHOCONGTYCON, DUNGCHONHANVIEN, DUNGCHODOITUONGKYQUY,  npp_fk \n"+
					" from ERP_TAIKHOANKT \n"+
					" WHERE npp_fk =  " + npp_fk;
					if(!db.update(query))
					{
						msg = "3.Không thể cập nhật tài khoản kế toán " + query;
					}
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace(); 
		}

		 String nppId = "106210";
		 String spId = "100110";
		 String khoId = "100000";
		 String kbhId = "100025";
		 String soluong = "1445";

		 String msg = util.Check_NghiepVu_QuaKhu("2016-02-28", nppId, khoId, kbhId, spId, soluong, db);

		 System.out.println("---- KET QUA CHAY::::  " + msg );

	}*/

	public String Update_TaiKhoan_FULL (dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc, String VAT, String DienGiai, String MaChungTu, String isNPP, String masp, String tensp, String donvi, String kbh_fk, String kho_fk, String Solo, String Ngayhethan, String tienhang )
	{

		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}

		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}

		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}


		String msg = "";

		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;

		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;

		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();

		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();

		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();

		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();

		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();

		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;

		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;

		String _VAT = "0";
		if(VAT.trim().length() > 0)
			_VAT = VAT;

		String _TIENHANG = "0";
		if(tienhang.trim().length() > 0)
			_TIENHANG = tienhang;

		String _DIENGIAI = "";
		if(DienGiai.trim().length()>0)
			_DIENGIAI = DienGiai;

		String _MACHUNGTU = "";
		if(MaChungTu.trim().length()>0)
			_MACHUNGTU = MaChungTu;

		String _ISNPP = "null";
		if(isNPP.trim().length()>0)
			_ISNPP = isNPP;

		String _MASP = "null";
		if(masp.trim().length()>0)
			_MASP = masp;

		String _TENSP = "null";
		if(tensp.trim().length()>0)
			_TENSP = tensp;

		String _DONVI = "null";
		if(donvi.trim().length()>0)
			_DONVI = donvi;

		String _KBH = "null";
		if(kbh_fk.trim().length()>0)
			_KBH = kbh_fk;

		String _KHO = "null";
		if(kho_fk.trim().length()>0)
			_KHO = kho_fk;

		String _SOLO = "null";
		if(Solo.trim().length()>0)
			_SOLO = Solo;

		String _NGAYHETHAN = "null";
		if(Ngayhethan.trim().length()>0)
			_NGAYHETHAN = Ngayhethan;

		//String kho_fk, String Solo, String Ngayhethan

		//GHI CO
		/*if(Float.parseFloat(_CO) != 0) */
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";

			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }

			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}

			System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}


			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"DOITUONG, MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, VAT, DIENGIAI , MACHUNGTU, ISNPP, MAHANG, TENHANG, DONVI, KBH_FK, SOLO, NGAYHETHAN, KHO_FK , TIENHANG ) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
			" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+_VAT+", N'"+_DIENGIAI+"', N'"+_MACHUNGTU+"', "+_ISNPP+", N'"+_MASP+"', N'"+_TENSP+"', N'"+_DONVI+"', "+_KBH+", '"+_SOLO+"', '"+_NGAYHETHAN+"', "+_KHO+", "+_TIENHANG+") ";

			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		//GHI NO
		/*if(Float.parseFloat(_NO) != 0) */
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";

			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }

			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "'";
			}

			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, VAT , DIENGIAI , MACHUNGTU, ISNPP , MAHANG, TENHANG, DONVI, KBH_FK , SOLO, NGAYHETHAN, KHO_FK , TIENHANG ) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
			" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+_VAT+", N'"+_DIENGIAI+"', N'"+_MACHUNGTU+"' , "+_ISNPP+", N'"+_MASP+"', N'"+_TENSP+"', N'"+_DONVI+"', "+_KBH+", '"+_SOLO+"', '"+_NGAYHETHAN+"', "+_KHO+", "+_TIENHANG+") ";

			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		return msg;

	}

	public String Update_TaiKhoan_Vat_DienGiai_CHIKHAC (dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc, String VAT, String DienGiai, String MaChungTu, String isNPP,
			String MAHOADON, String MAUHOADON, String KYHIEU, String SOHOADON, String NGAYHOADON, String TENNCC, String MST, String TIENHANG, String THUESUAT, String MAFAST_DT, String TEN_DT, String TEN_PB, String TEN_KBH, String TEN_VV, String TEN_DIABAN, String TEN_TINHTHANH, String TEN_BENHVIEN, String TEN_SANPHAM, String DIENGIAI_CT)
	{

		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}

		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}

		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}


		String msg = "";

		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;

		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;

		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();

		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();

		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();

		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();

		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();

		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;

		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;

		String _VAT = "0";
		if(VAT.trim().length() > 0)
			_VAT = VAT;

		String _DIENGIAI = "";
		if(DienGiai.trim().length()>0)
			_DIENGIAI = DienGiai;

		String _MACHUNGTU = "";
		if(MaChungTu.trim().length()>0)
			_MACHUNGTU = MaChungTu;

		String _ISNPP = "null";
		if(isNPP.trim().length()>0)
			_ISNPP = isNPP;

		String _MAHOADON = "";
		if(MAHOADON.trim().length()>0)
			_MAHOADON = MAHOADON;

		String _MAUHOADON = "";
		if(MAUHOADON.trim().length()>0)
			_MAUHOADON = MAUHOADON;

		String _KYHIEU = "";
		if(KYHIEU.trim().length()>0)
			_KYHIEU = KYHIEU;

		String _SOHOADON = "";
		if(SOHOADON.trim().length()>0)
			_SOHOADON = SOHOADON;

		String _NGAYHOADON = "";
		if(NGAYHOADON.trim().length()>0)
			_NGAYHOADON = NGAYHOADON;

		String _TENNCC = "";
		if(TENNCC.trim().length()>0)
			_TENNCC = TENNCC;

		String _MST = "";
		if(MST.trim().length()>0)
			_MST = MST;

		String _TIENHANG = "0";
		if(TIENHANG.trim().length()>0)
			_TIENHANG = TIENHANG;

		String _THUESUAT = "0";
		if(THUESUAT.trim().length()>0)
			_THUESUAT = THUESUAT;

		String _MAFAST_DT = "";
		if(MAFAST_DT.trim().length()>0)
			_MAFAST_DT = MAFAST_DT;

		String _TEN_DT = "";
		if(TEN_DT.trim().length()>0)
			_TEN_DT = TEN_DT;

		String _TEN_PB = ""; 
		if(TEN_PB.trim().length()>0)
			_TEN_PB = TEN_PB;

		String _TEN_KBH = "";
		if(TEN_KBH.trim().length()>0)
			_TEN_KBH = TEN_KBH;

		String _TEN_VV = "";
		if(TEN_VV.trim().length()>0)
			_TEN_VV = TEN_VV;

		String _TEN_DIABAN = "";
		if(TEN_DIABAN.trim().length()>0)
			_TEN_DIABAN = TEN_DIABAN;

		String _TEN_TINHTHANH = "";
		if(TEN_TINHTHANH.trim().length()>0)
			_TEN_TINHTHANH = TEN_TINHTHANH;

		String _TEN_BENHVIEN = "";
		if(TEN_BENHVIEN.trim().length()>0)
			_TEN_BENHVIEN = TEN_BENHVIEN; 

		String _TEN_SANPHAM = "";
		if(TEN_SANPHAM.trim().length()>0)
			_TEN_SANPHAM = TEN_SANPHAM;

		String _DIENGIAI_CT = "";
		if(DIENGIAI_CT.trim().length()>0)
			_DIENGIAI_CT = DIENGIAI_CT;

		//GHI CO
		/*if(Float.parseFloat(_CO) != 0) */
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";

			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }

			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}

			System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, VAT, DIENGIAI , MACHUNGTU, ISNPP , " +
			"MAHOADON, MAUHOADON, KYHIEU, SOHOADON, NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT,  MAFAST_DT, TEN_DT, TEN_PB, TEN_KBH, TEN_VV, TEN_DIABAN, TEN_TINHTHANH, TEN_BENHVIEN, TEN_SANPHAM, DIENGIAI_CT ) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
			" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+_VAT+", N'"+_DIENGIAI+"', N'"+_MACHUNGTU+"', "+_ISNPP+", " +
			" N'"+ _MAHOADON + "', N'"+_MAUHOADON+"' , N'"+_KYHIEU+"', N'"+_SOHOADON+"', N'"+_NGAYHOADON+"', N'"+_TENNCC+"', N'"+_MST+"', "+_TIENHANG+", "+_THUESUAT+", N'"+_MAFAST_DT+"', N'"+_TEN_DT+"', N'"+_TEN_PB+"', N'"+_TEN_KBH+"', N'"+_TEN_VV+"', N'"+_TEN_DIABAN+"', N'"+_TEN_TINHTHANH+"', N'"+_TEN_BENHVIEN+"', N'"+_TEN_SANPHAM+"', N'"+_DIENGIAI_CT+"') ";

			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		//GHI NO
		/*if(Float.parseFloat(_NO) != 0) */
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";

			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }

			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "'";
			}

			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, VAT , DIENGIAI , MACHUNGTU, ISNPP," +
			"MAHOADON, MAUHOADON, KYHIEU, SOHOADON, NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, MAFAST_DT, TEN_DT, TEN_PB, TEN_KBH, TEN_VV, TEN_DIABAN, TEN_TINHTHANH, TEN_BENHVIEN, TEN_SANPHAM, DIENGIAI_CT ) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
			" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+_VAT+", N'"+_DIENGIAI+"', N'"+_MACHUNGTU+"' , "+_ISNPP+", " +
			" N'"+ _MAHOADON + "', N'"+_MAUHOADON+"' , N'"+_KYHIEU+"', N'"+_SOHOADON+"', N'"+_NGAYHOADON+"', N'"+_TENNCC+"', N'"+_MST+"', "+_TIENHANG+", "+_THUESUAT+", N'"+_MAFAST_DT+"', N'"+_TEN_DT+"', N'"+_TEN_PB+"', N'"+_TEN_KBH+"', N'"+_TEN_VV+"', N'"+_TEN_DIABAN+"', N'"+_TEN_TINHTHANH+"', N'"+_TEN_BENHVIEN+"', N'"+_TEN_SANPHAM+"', N'"+_DIENGIAI_CT+"') ";

			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		return msg;

	}


	public String Update_TaiKhoan_Vat_DienGiai(dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc, String VAT, String DienGiai, String MaChungTu, String isNPP)
	{

		//CHECK THANG KHOA SO CO HOP LE HAY KHONG ( CHI DUOC CHOT SAU THANG KHOA SO + 1 )
		String query = "select THANGKS, NAM from ERP_KHOASOKETOAN order by NAM desc, THANGKS desc";
		String thangKS = "1";
		String namKS = "2013";
		ResultSet rsCheck = db.get(query);
		if(rsCheck != null)
		{
			try 
			{
				if(rsCheck.next())
				{
					thangKS = rsCheck.getString("THANGKS");
					namKS = rsCheck.getString("NAM");
				}
				rsCheck.close();
			} 
			catch (Exception e) {}
		}

		String thangHopLe = "";
		String namHopLe = "";
		if(Integer.parseInt(thangKS) == 12 )
		{
			thangHopLe =  "1";
			namHopLe = Integer.toString( Integer.parseInt(namKS)  + 1);
		}
		else
		{
			thangHopLe =  Integer.toString(Integer.parseInt(thangKS) + 1);
			namHopLe = namKS;
		}

		if( ( Integer.parseInt(thangHopLe) != Integer.parseInt(thang) ) || ( Integer.parseInt(namHopLe) != Integer.parseInt(nam) ) )
		{
			//TAM THOI CHUA CHECK
			//return "Bạn chỉ có thể đóng nghiệp vụ sau tháng khóa sổ gần nhất ( " + thangKS + "-" + namKS + " ) 1 tháng";
		}


		String msg = "";

		String _ndnhapxuat_fk = "null";
		if(NOIDUNGNHAPXUAT_FK.trim().length() > 0)
			_ndnhapxuat_fk = NOIDUNGNHAPXUAT_FK;

		String _sochungtu = "null";
		if(sochungtu.trim().length() > 0)
			_sochungtu = sochungtu;

		String _soluong = "null";
		if(SOLUONG.trim().length() > 0)
			_soluong = SOLUONG.trim();

		String _dongia = "null";
		if(DONGIA.trim().length() > 0)
			_dongia = DONGIA.trim();

		String _thanhtienViet = "null";
		if(TONGGIATRI.trim().length() > 0)
			_thanhtienViet = TONGGIATRI.trim();

		String _dongiaNT = "null";
		if(DONGIANT.trim().length() > 0)
			_dongiaNT = DONGIANT.trim();

		String _thanhtienNT = "null";
		if(TONGGIATRINT.trim().length() > 0)
			_thanhtienNT = TONGGIATRINT.trim();

		String _NO = "0";
		if(NO.trim().length() > 0)
			_NO = NO;

		String _CO = "0";
		if(CO.trim().length() > 0)
			_CO = CO;

		String _VAT = "0";
		if(VAT.trim().length() > 0)
			_VAT = VAT;

		String _DIENGIAI = "";
		if(DienGiai.trim().length()>0)
			_DIENGIAI = DienGiai;

		String _MACHUNGTU = "";
		if(MaChungTu.trim().length()>0)
			_MACHUNGTU = MaChungTu;

		String _ISNPP = "null";
		if(isNPP.trim().length()>0)
			_ISNPP = isNPP;


		//GHI CO
		/*if(Float.parseFloat(_CO) != 0) */
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";

			ResultSet rsTKNo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKNo.next())
				{
					sodong = rsTKNo.getInt("sodong");
				}
				rsTKNo.close();
			} 
			catch (Exception e) { }

			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}

			System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}


			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, VAT, DIENGIAI , MACHUNGTU, ISNPP ) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
			" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+_VAT+", N'"+_DIENGIAI+"', N'"+_MACHUNGTU+"', "+_ISNPP+") ";

			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		//GHI NO
		/*if(Float.parseFloat(_NO) != 0) */
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";

			ResultSet rsTKCo = db.get(query);
			int sodong = 0;
			try 
			{
				if(rsTKCo.next())
				{
					sodong = rsTKCo.getInt("sodong");
				}
				rsTKCo.close();
			} 
			catch (Exception e) { }

			if(sodong > 0) //daco
			{
				query = "update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRINONGUYENTE = GIATRINONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "' ";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "'";
			}

			System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC, VAT , DIENGIAI , MACHUNGTU, ISNPP ) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
			" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "', "+_VAT+", N'"+_DIENGIAI+"', N'"+_MACHUNGTU+"' , "+_ISNPP+") ";

			System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		return msg;

	}

	public static String getFirstDayOfMonth(String date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate;
		Calendar c = null;
		try {
			convertedDate = dateFormat.parse(date);
			c = Calendar.getInstance();
			c.setTime(convertedDate);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		Date lastDayOfMonth = c.getTime();  
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(lastDayOfMonth);
	}

	public static String getLastDayOfMonth(String date)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date convertedDate;
		Calendar c = null;
		try {
			convertedDate = dateFormat.parse(date);
			c = Calendar.getInstance();
			c.setTime(convertedDate);
			c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		} catch (ParseException e) {
			e.printStackTrace();
			return "";
		}
		Date lastDayOfMonth = c.getTime();  
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(lastDayOfMonth);
	}
	public static String  kiemtra_ngayhethanlo(dbutils db,String ngaydonhang,String sanpham_fk,String solo,String ngayhethan,String ngaynhapkho,String kbh_fk,String kho_fk,String npp_fk) {
		
		int soluong=0;
		String msg="";
		String query=" select count(*) as sl from NHAPP_KHO_CHITIET  \n"+
					 "	where sanpham_fk='"+ sanpham_fk +"' and NPP_FK='"+ npp_fk +"' and SOLO='"+ solo +"' and KBH_FK='"+ kbh_fk +"' and KHO_FK='"+ kho_fk +"' and NGAYHETHAN='"+ ngayhethan +"' and NGAYNHAPKHO='"+ ngaynhapkho +"' \n"+
					 "	and DATEDIFF(MM,'"+ ngaydonhang +"',ngayhethan)<12 ";
		System.out.println("kiem tra solo "+query);
		ResultSet rs=db.get(query);
		try {
			rs.next();
			soluong=rs.getInt("sl");
			if(soluong>0)
			{
				rs.close();
				query="select MA from sanpham where pk_seq="+sanpham_fk;
				rs=db.get(query);
				rs.next();
				String MASP=rs.getString("MA");
				rs.close();
				msg+= "Sản phẩm "+MASP + " solo " +solo +" ngày hết hạn "+ngayhethan +" có hạn sử dụng dưới 12 tháng \n" ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
		return msg;
	}
	
	
	///////////// khoa so
	
	public String Check_Huy_NghiepVu_KhoaSo(String table, String id, String column, String nppId, dbutils db)
	{
		
		
	String	query="select count(*) as sl from khoasothang where npp_fk=(select a.npp_fk from "+table+" a where a.pk_seq= '"+id+"')";
		ResultSet rsck=db.get(query);
		try {
			rsck.next();
			if(rsck.getInt("sl")==0)
			{
				rsck.close();
				return "Chưa thiết lập khóa sổ tháng vui lòng thiết lập.";
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			  return "Lỗi phát sinh khi check khóa sổ;";
		}
	
		/*query = " select DATEPART(MONTH, " + column + ") as Thang, DATEPART(YEAR, " + column + ") as Nam "+
						" from " + table + "  a  "+
						" where PK_SEQ = '" + id + "' and exists  "+
						" (  "+
						"	select 1 from KHOASOTHANG where THANGKS= DATEPART(MONTH,"+column+") "+
						"	and NAM=DATEPART(YEAR, " + column + ") and NPP_FK = '" + nppId + "'  "+
						" )  ";*/
		query = " select DATEPART(MONTH, " + column + ") as Thang, DATEPART(YEAR, " + column + ") as Nam "+
				" from " + table + "  a  "+
				" where PK_SEQ = '" + id + "' and exists  "+
				" (  "+
				"	select 1 from KHOASOTHANG where ((THANGKS= DATEPART(MONTH,"+column+") "+
				"	and NAM=DATEPART(YEAR, " + column + ")) or (NAM > DATEPART(YEAR,"+column+") )) and NPP_FK = '" + nppId + "'  "+
				" )  ";
		
		System.out.println("Check_Huy_NghiepVu_KhoaSo: " + query);
		String msg = "";
		ResultSet rs = db.get(query);
		try
	    {
		    while(rs.next())
		    {
		    	msg = "Bạn không được thực hiện nghiệp vụ trong tháng đã khóa sổ !";
		    }
		    rs.close();
	    } 
		catch (Exception e)
	    {
		    e.printStackTrace();
		    return "Lỗi phát sinh khi check khóa sổ;";
	    }
		return msg;
		
		
	}
	
	public String Check_Huy_NghiepVu_KhoaSo(String table, String id, String column, String nppId)
	{
		dbutils db=new dbutils();
		String query="select count(*) as sl from khoasothang where npp_fk=(select a.npp_fk from "+table+" a where a.pk_seq= '"+id+"')";
		ResultSet rsck=db.get(query);
		try {
			rsck.next();
			if(rsck.getInt("sl")==0)
			{
				rsck.close();
				return "Chưa thiết lập khóa sổ tháng vui lòng thiết lập.";
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			  return "Lỗi phát sinh khi check khóa sổ;";
		}
	
		/*query = " select DATEPART(MONTH, " + column + ") as Thang, DATEPART(YEAR, " + column + ") as Nam "+
						" from " + table + "  a  "+
						" where PK_SEQ = '" + id + "' and exists  "+
						" (  "+
						"	select 1 from KHOASOTHANG where THANGKS= DATEPART(MONTH,"+column+") "+
						"	and NAM=DATEPART(YEAR, " + column + ") and NPP_FK = '" + nppId + "'  "+
						" )  ";*/
		query = " select DATEPART(MONTH, " + column + ") as Thang, DATEPART(YEAR, " + column + ") as Nam "+
				" from " + table + "  a  "+
				" where PK_SEQ = '" + id + "' and exists  "+
				" (  "+
				"	select 1 from KHOASOTHANG where ((THANGKS= DATEPART(MONTH,"+column+") "+
				"	and NAM=DATEPART(YEAR, " + column + ")) or (NAM > DATEPART(YEAR,"+column+") )) and NPP_FK = '" + nppId + "'  "+
				" )  ";
		
		System.out.println("Check_Huy_NghiepVu_KhoaSo: " + query);
		String msg = "";
	
		ResultSet rs = db.get(query);
		try
	    {
		    while(rs.next())
		    {
		    	msg = "Bạn không được thực hiện nghiệp vụ trong tháng đã khóa sổ !";
		    }
		    rs.close();
	    } 
		catch (Exception e)
	    {
		    e.printStackTrace();
		    return "Lỗi phát sinh khi check khóa sổ;";
	    }
		return msg;
		
		
	}
	
	
	public String Check_Huy_NghiepVu_KhoaSo(String table, String id, String column, dbutils db)
	{
		
		String query="select count(*) as sl from khoasothang where npp_fk=(select a.npp_fk from "+table+" a where a.pk_seq= '"+id+"')";
		ResultSet rsck=db.get(query);
		try {
			rsck.next();
			if(rsck.getInt("sl")==0)
			{
				rsck.close();
				return "Chưa thiết lập khóa sổ tháng vui lòng thiết lập.";
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			  return "Lỗi phát sinh khi check khóa sổ;";
		}
	
		/*query =  "		select DATEPART(MONTH,"+column+") as Thang,DATEPART(YEAR,"+column+") as Nam "+
						"		from "+table+"  a  "+
						"		where PK_SEQ='"+id+"' and exists  "+
						"		(  "+
						"			select 1 from KHOASOTHANG where THANGKS= DATEPART(MONTH,"+column+") "+
						"			and NAM=DATEPART(YEAR,"+column+") and NPP_FK=a.NPP_FK  "+
						"		)  ";*/
		query =  "		select DATEPART(MONTH,"+column+") as Thang,DATEPART(YEAR,"+column+") as Nam "+
				"		from "+table+"  a  "+
				"		where PK_SEQ='"+id+"' and exists  "+
				"		(  "+
				"			select 1 from KHOASOTHANG where ((THANGKS= DATEPART(MONTH,"+column+") "+
				"			and NAM=DATEPART(YEAR,"+column+")) or (NAM > DATEPART(YEAR,"+column+") )) and NPP_FK=a.NPP_FK  "+
				"		)  ";
		String msg = "";
		System.out.println("______"+query);
		ResultSet rs = db.get(query);
		try
	    {
		    while(rs.next())
		    {
		    	msg= "Bạn không được thực hiện nghiệp vụ trong tháng đã khóa sổ !";
		    }
		    rs.close();
	    } 
		catch (Exception e)
	    {
		    e.printStackTrace();
		    return "Lỗi phát sinh khi check khóa sổ;";
	    }
		return msg;
		
	
	}
	
	public String Check_Huy_NghiepVu_KhoaSo(String table, String id, String column)
	{
		dbutils db=new dbutils();
		String query="select count(*) as sl from khoasothang where npp_fk=(select a.npp_fk from "+table+" a where a.pk_seq= '"+id+"')";
		ResultSet rsck=db.get(query);
		try {
			rsck.next();
			if(rsck.getInt("sl")==0)
			{
				rsck.close();
				return "Chưa thiết lập khóa sổ tháng vui lòng thiết lập.";
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
			  return "Lỗi phát sinh khi check khóa sổ;";
		}
		 /*query =  "		select DATEPART(MONTH,"+column+") as Thang,DATEPART(YEAR,"+column+") as Nam "+
						"		from "+table+"  a  "+
						"		where PK_SEQ='"+id+"' and exists  "+
						"		(  "+
						"			select 1 from KHOASOTHANG where THANGKS= DATEPART(MONTH,"+column+") "+
						"			and NAM=DATEPART(YEAR,"+column+") and NPP_FK=a.NPP_FK  "+
						"		)  ";*/
		 query =  "		select DATEPART(MONTH,"+column+") as Thang,DATEPART(YEAR,"+column+") as Nam "+
					"		from "+table+"  a  "+
					"		where PK_SEQ='"+id+"' and exists  "+
					"		(  "+
					"			select 1 from KHOASOTHANG where ((THANGKS= DATEPART(MONTH,"+column+") "+
					"			and NAM=DATEPART(YEAR,"+column+")) or (NAM > DATEPART(YEAR,"+column+") )) and NPP_FK=a.NPP_FK  "+
					"		)  ";
		String msg = "";
	
		ResultSet rs = db.get(query);
		try
	    {
		    while(rs.next())
		    {
		    	msg= "Bạn không được thực hiện nghiệp vụ trong tháng đã khóa sổ !";
		    }
		    rs.close();
	    } 
		catch (Exception e)
	    {
		    e.printStackTrace();
		    return "Lỗi phát sinh khi check khóa sổ;";
	    }
		
		return msg;		
	}
	
	public static boolean isValid(Object obj) {
		try {
			if (obj == null) {
				return false;
			}
			else {
				if (obj instanceof String) {
					String temp = (String) obj;
					if (temp != null && temp.trim().length() > 0) 
						return true;
					else 
						return false;
				}
				else if (obj instanceof String[]) {
					String[] temp = (String[]) obj;
					if (temp != null && temp.length > 0) 
						return true;
					else 
						return false;
				}
				else if (obj instanceof int[]) {
					int[] temp = (int[]) obj;
					if (temp != null && temp.length > 0) 
						return true;
					else 
						return false;
				}
				else if (obj instanceof double[]) {
					double[] temp = (double[]) obj;
					if (temp != null && temp.length > 0) 
						return true;
					else 
						return false;
				}
				else if (obj instanceof float[]) {
					float[] temp = (float[]) obj;
					if (temp != null && temp.length > 0) 
						return true;
					else 
						return false;
				}
				else if (obj instanceof ArrayList<?>) {
					ArrayList<?> temp = (ArrayList<?>) obj;
					if (temp != null && temp.size() > 0) 
						return true;
					else 
						return false;
				}
				else if (obj instanceof Hashtable<?, ?>) {
					Hashtable<?, ?> temp = (Hashtable<?, ?>) obj;
					if (temp != null && temp.size() > 0) 
						return true;
					else 
						return false;
				}
				else {
					return true;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String[] antiSQLInspection_Array(String[] arr)
	{
		if(arr != null)
		{
			for(int i = 0; i < arr.length; i++ )
			{
				if( arr[i] != null)
					arr[i] = antiSQLInspection(  arr[i] );
			}
		}
		return arr;
	}
	public static String Update_GiaTri_ERP_DonDatHang(String dhId, Idbutils db,boolean isNPP)
	{
		try
		{
			String query = 	     
				 "\n update erp_dondathang set " + 
				 "\n AVAT =  round( (dhsp.thanhtien - isnull(km.tienkm,0)) * (1 - isnull(dh.chietkhau,0)/100.0) *  ( 1 + isnull(dhsp.thuevat,0)/100.0)  ,0), " + 
				 "\n BVAT  =  round( (dhsp.thanhtien - isnull(km.tienkm,0)) * (1 - isnull(dh.chietkhau,0)/100.0)  ,0), " + 
				 "\n ckThanhToan = (dhsp.thanhtien - isnull(km.tienkm,0)) * ( isnull(dh.chietkhau,0)/100.0), " + 
				 "\n vat = (dhsp.thanhtien - isnull(km.tienkm,0)) * (1 - isnull(dh.chietkhau,0)/100.0) *  (  isnull(dhsp.thuevat,0)/100.0)" + 
				 "\n from erp_dondathang dh" + 
				 "\n cross apply ( select sum( soluong * dongia)thanhtien, max(isnull(thuevat,0))thuevat  from ERP_DONDATHANG_SANPHAM dhsp where dhsp.dondathang_fk = dh.pk_seq )dhsp" + 
				 "\n outer apply ( select sum(tonggiatri) tienkm from erp_dondathang_ctkm_trakm where spma is null and DONDATHANGID = dh.pk_seq )km" +
				 "\n where dh.pk_seq = '"+ dhId +"' ";
			if (!db.update(query)) { return "Lỗi phát sinh khi cập nhật giá trị đơn đặt hàng "; }
			if(isNPP)
			{
				query = " update ERP_DONDATHANG_SANPHAM set soluongNppdat = soluong where dondathang_fk = '"+ dhId +"'";
				if (!db.update(query)) { return "Lỗi phát sinh khi cập nhật giá trị đơn đặt hàng 2 "; }
			}
			return "";
		}
		catch(Exception ex){
			ex.printStackTrace();
			return "Lỗi";
		}
	}
	
	 public static String dongMa(String ma) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		   
		    SecretKeySpec skeySpec = new SecretKeySpec(GlobalValue.SECRET_KEY.getBytes(), "DES");
		    
		    Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
		    cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		    byte[] byteEncrypted = cipher.doFinal(ma.getBytes());
		    String encrypted =  DatatypeConverter.printBase64Binary(byteEncrypted);
		    return replaceSupser(encrypted);
	}
	 
	 public static String replaceSupser(String in){
			try {
				return URLEncoder.encode(in, "UTF-8")
				.replaceAll("\\+", "%20")
				.replaceAll("\\%21", "!")
				.replaceAll("\\%27", "'")
				.replaceAll("\\%28", "(")
				.replaceAll("\\%29", ")")
				.replaceAll("\\%7E", "~");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return in;
		}
		public static boolean CheckSessionUser(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException
		{
			String ssUserId = (String)session.getAttribute("userId");
			String userId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("userId"));
			return ssUserId == null || !ssUserId.equals(userId);
		}
		public static void RedireactLogin(HttpSession session, HttpServletRequest request,HttpServletResponse response) throws IOException
		{
			session.setAttribute("userId", null);
			String nextJSP = request.getServletContext().getInitParameter("RedirectNoScript") ;
			session.setAttribute("logoutMsg", "Lỗi dữ liệu");
			response.sendRedirect(nextJSP);
		}
		
		public static boolean CheckRuleUser(HttpSession session, HttpServletRequest request,HttpServletResponse response, String svl, String param, int quyen ) throws IOException
		{
			String userId = (String)session.getAttribute("userId");		
			int[] q =  geso.dms.center.util.Utility.Getquyen(svl, param,userId);
			return q[quyen]!=1;
		}
		
		public static boolean val_doget(HttpSession session,HttpServletRequest request,HttpServletResponse response ) throws IOException
		{
			 String flag=request.getParameter("flag");
			 String flagsession=(String) session.getAttribute("flag");  	
			
			 if(flag==null || flag.equals("") || flagsession==null || flagsession.equals("") || !flag.equals(flagsession))
			 {			 
				 return false;
			 }
			 else
			 {
				 return true;
			 }
			
		}
		
		public static String GLanguage(String key, String languageId)
		{
			return ChuyenNgu.get(key, languageId);
		}	
		public static String GLanguage(String key, HttpSession session)
		{	
			return ChuyenNgu.get(key, session);
		}
		public static String GLanguage(String key, String languageId, Jedis j)
		{
			return ChuyenNgu.get(key, languageId);
		}	
		public static String GLanguage(String key,  HttpSession session, Jedis j)
		{	
			return ChuyenNgu.get( key,  session,  j);
		}
		
		public static long SLanguage(String key, HttpSession session, String value)
		{
			return ChuyenNgu.set( key,  session,  value);
		}
		public static long SLanguage(String key, String languageId, String value)
		{
			return ChuyenNgu.set( key,  languageId,  value);
		}
		public static Jedis getJedis() {
			return ChuyenNgu.getJedis();
		}
		public static void JedisClose(Jedis j)
		{
			if(j != null)
			{
				j.close();
				j = null;
			}
		}
}
