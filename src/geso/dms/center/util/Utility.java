package geso.dms.center.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

import geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham;
import geso.dms.center.beans.routesumaryreport.IRouteSumaryReport;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTBList;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTLList;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.db.sql.dbutils_syn;
import geso.dms.distributor.beans.report.Ireport;
import geso.dms.distributor.beans.reports.imp.Reports;
import geso.dms.distributor.util.FixData;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.DatatypeConverter;

import org.apache.poi.ss.usermodel.DateUtil;

import redis.clients.jedis.Jedis;

import com.oreilly.servlet.MultipartRequest;
public class Utility implements Serializable
{
	private static final long serialVersionUID = 1L;
	public static  final boolean IsDongBo =false;
	String nppId;
	String nppTen;
	String sitecode;
	String phanloai;
	String tructhuoc_fk;

	public String getPhanloai()
	{
		return phanloai;
	}

	public void setPhanloai(String phanloai)
	{
		this.phanloai = phanloai;
	}

	public static String getNgayHienTai()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}	

	public static final int THEM = 0;
	public static final int XOA = 1;
	public static final int SUA = 2;
	public static final int XEM = 3;
	public static final int CHOT = 4;
	public static final int HUYCHOT = 5;
	public static final int KICHHOAT = 0;
	
	public static final String LINKSERVER_DB = "[DHT_ERP].[ERP_DUOCHT_TEST_DONGBO_DMS]";
	
	public static final String[] tanso = {"F1-1","F1-2","F1-3","F1-4","F2-1","F2-2","F4","F8","F12","F16", "F20", "F24"};

	public static String[] getTanSo()
	{
		return tanso;
	}
	
	public String ChietKhau(String nam)
	{
		if(nam.trim().equals("2013"))
			return "1";
		else 
			return  "1";
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

	public String Doisangchuoi(String[] checknpp)
	{
		// TODO Auto-generated method stub
		String str = "";
		if (checknpp != null)
		{
			for (int i = 0; i < checknpp.length; i++)
			{
				if (i == 0)
				{
					str = checknpp[i];
				} else
				{
					str = str + "," + checknpp[i];
				}
			}
		}
		return str;

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
	
	public static String Quyen_kenh(String userId)
	{
		String sql ="( select kenh_fk as kbh_fk from nhanvien_kenh where nhanvien_fk ='"+ userId +"'    )";
		//	String sql ="( select pk_seq from KENHBANHANG where trangthai ='1' )";
		return sql;
	}

	public String Check_Kho_Tong_VS_KhoCT(String nppId, geso.dms.distributor.db.sql.dbutils db)
	{
		String query =  " select count(*) as sodong "+
		" from  "+ 
		" ( " +
		"	 select npp_fk, kbh_fk, kho_fk, sanpham_fk, sum(available) as available, sum(soluong) as soluong, sum(booked) as booked_ct  "+
		"	 from nhapp_kho_chitiet where npp_fk = '" + nppId + "' "+
		"	 group by kbh_fk, npp_fk, kho_fk, sanpham_fk	  "+
		" ) " +
		" CT full outer join nhapp_kho total on total.npp_fk=ct.npp_fk and total.kbh_fk=ct.kbh_fk  "+
		"		and total.sanpham_fk=ct.sanpham_fk and total.kho_fk=ct.kho_fk   "+
		" where    "+
		"		(round( isnull(ct.available,0),1) + round( isnull(ct.booked_ct,0),1) != round(isnull(total.available,0),1) + round(isnull(total.booked ,0),1)  "+ 
		"			or round(isnull(total.soluong,0),0) != round(isnull(ct.soluong,0),0)  "+ 
		"		) and  isnull(total.npp_fk, ct.npp_fk) = '" + nppId + "' ";

		//System.out.println("Check_Kho_Tong_VS_KhoCT " + query);
		String msg = "";
		ResultSet rs = db.get(query);
		try
		{
			while(rs.next())
			{
				if(rs.getInt("soDONG") > 0 )
				{
					msg += "Lỗi phát sinh do lệch Số lượng ";
					return msg;

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

	public String Check_Kho_Tong_VS_KhoCT_TT(String nppId, dbutils db)
	{
		String query =  " select count(*) as sodong "+
		" from  "+ 
		" ( " +
		"	 select npp_fk, kbh_fk, kho_fk, sanpham_fk, sum(available) as available, sum(soluong) as soluong, sum(booked) as booked_ct  "+
		"	 from nhapp_kho_chitiet where npp_fk = '" + nppId + "' "+
		"	 group by kbh_fk, npp_fk, kho_fk, sanpham_fk	  "+
		" ) " +
		" CT full outer join nhapp_kho total on total.npp_fk=ct.npp_fk and total.kbh_fk=ct.kbh_fk  "+
		"		and total.sanpham_fk=ct.sanpham_fk and total.kho_fk=ct.kho_fk   "+
		" where    "+
		"		(round( isnull(ct.available,0),1) + round( isnull(ct.booked_ct,0),1) != round(isnull(total.available,0),1) + round(isnull(total.booked ,0),1)  "+ 
		"			or round(isnull(total.soluong,0),0) != round(isnull(ct.soluong,0),0)  "+ 
		"		) and  isnull(total.npp_fk, ct.npp_fk) = '" + nppId + "' ";

		//System.out.println("Check_Kho_Tong_VS_KhoCT " + query);
		String msg = "";
		ResultSet rs = db.get(query);
		try
		{
			while(rs.next())
			{
				if(rs.getInt("soDONG") > 0 )
				{
					msg += "Lỗi phát sinh do lệch Số lượng ";
					return msg;

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
	public String quyen_khoTT(String userId,int kichhoat)
	{	
		
		String sql ="( select KHO_fK from NHANVIEN_KHO where nhanvien_fk ='"+ userId +"')";
		
		// kichhoat = 0: Khong cho hien thi
		if(kichhoat == 0) {
			 sql ="( select kho_fk from NHANVIEN_KHO a\r\n" + 
				"				inner join kho b\r\n" + 
				"				on a.kho_fk = b.PK_SEQ\r\n" + 
				"				where nhanvien_fk = '" +userId + "' and b.loaikho != 99)";
			// loaikho >= 99 duoc lua chon de xem co kichhoat hien thi hoac khong? 
		}
		return sql;
	}
	public String quyen_kho(String userId)
	{	
			String sql ="(select KHO_fK from NHANVIEN_KHO where nhanvien_fk ='"+ userId +"')";
			return sql;
	}
	public String quyen_kho_dathang(String userId,String npp_tructhuoc)
	{	
		String sql ="( select  pk_seq as KHO_fK from kho where loaikho=0 )";
		return sql;
	}

	public boolean coQuyen(int[] quyen, int tacvu) 
	{
		return quyen != null && quyen.length > tacvu && quyen[tacvu] == 1;
	}

	public String getChuyenNguUrl(String servlet,String parametes,HttpSession session)
	{
		String query=
			"\n	select   B.PK_SEQ as DanhMuc_FK,a.PK_SEQ as pk_Seq  , c.ten ten1,  b.ten ten2 , a.ten , a.servlet,  "+  
			"\n			a.parameters, c.sott as stt1, b.sott as stt2, a.sott           "+
			"\n	from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq        "+       
			"\n	inner join ungdung c on b.ungdung_fk = c.pk_seq               "+
			"\n	where a.level = '3' and b.level = '2'  and a.TrangThai=1 and a.servlet='"+servlet+"' and a.parameters='"+parametes+"' "+           
			"\n	union all            "+
			"\n	select c.PK_SEQ as DanhMuc_FK ,a.PK_SEQ ,  c.ten   as ten1 , '' ten2 , a.ten, a.servlet, a.parameters, c.sott as stt1, a.sott as stt2, a.sott "+ 
			"\n	from ungdung a  inner join ungdung c on a.ungdung_fk = c.pk_seq               "+
			"\n	where a.level = '3' and c.level = '1'    and a.TrangThai=1  and a.servlet='"+servlet+"' and a.parameters='"+parametes+"'    "+        
			"\n	order by stt1 asc, stt2 asc, sott asc  " ;
		//System.out.println("query getUrl: " + query);
		dbutils db = new dbutils();
		Jedis j = ChuyenNgu.getJedis(ChuyenNgu.timeout * 5);
		String url="";
		ResultSet rs =db.get(query);
		try
		{
			while(rs.next())
			{
				String ten1 = rs.getString("ten1");
				String ten2 = rs.getString("ten2");
				String ten3 = rs.getString("ten");
				if(ten2.trim().length() >0)
					url = ChuyenNgu.get(ten1,session,j) + " > " + ChuyenNgu.get(ten2,session,j)  + " > " + ChuyenNgu.get(ten3,session,j);
				else		
					url=ChuyenNgu.get(ten1,session,j)  + " > " + ChuyenNgu.get(ten3,session,j);
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
	
	public String getUrl(String servlet, String parametes)
	{
		String query = "\n select B.PK_SEQ as DanhMuc_FK, a.PK_SEQ as pk_Seq, " +
		"\n c.ten + ' > '+  b.ten as TENDANHMUC, a.ten , a.servlet, a.parameters, " +
		"\n c.sott as stt1, b.sott as stt2, a.sott " +
		"\n from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq " +       
		"\n inner join ungdung c on b.ungdung_fk = c.pk_seq " +
		"\n where a.level = '3' and b.level = '2' " +
		"\n     and a.TrangThai = 1 and a.servlet = '" + servlet + "' and a.parameters = '" + parametes + "' " +           
		"\n union all " +
		"\n select c.PK_SEQ as DanhMuc_FK, a.PK_SEQ, c.ten as TENDANHMUC, a.ten, a.servlet, " +
		"\n a.parameters, c.sott as stt1, a.sott as stt2, a.sott " + 
		"\n from ungdung a inner join ungdung c on a.ungdung_fk = c.pk_seq " +
		"\n where a.level = '3' and c.level = '1' " +
		"\n     and a.TrangThai = 1 and a.servlet = '" + servlet + "' and a.parameters = '" + parametes + "' " +         
		"\n order by stt1 asc, stt2 asc, sott asc ";
		//System.out.println("getUrl Query: " + query);
		dbutils db = new dbutils();
		String url = "";
		ResultSet rs = db.get(query);

		try
		{
			while(rs.next())
			{
				url = rs.getString("TENDANHMUC")+ " > " + rs.getString("ten");
			}
			if (rs != null) rs.close();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.shutDown();
		}

		return url;
	}

	public static int[] Getquyen(String servlet, String parameters, String userId)
	{
		int[] quyen = new int[6];
		int them =0;
		int xoa=0;
		int sua=0;
		int xem=0;
		int chot=0;
		int huychot=0;
		String query =	
			/*		"	select 1 as Them,1 as Xoa,1 as Sua,1 AS Xem,1 as Chot,1 as HuyChot from nhanvien where pk_Seq='"+userId+"'  ";*/
			/*	"	union  select 1 as Them,1 as Xoa,1 as Sua,1 AS Xem,1 as Chot,1 as HuyChot from nhanvien WHERE  PK_SEQ='"+userId+"' and IsAdmin=1  "+*/ 		
			" 	SELECT ISNULL(THEM,0) AS THEM,ISNULL(XOA,0) AS XOA,ISNULL(SUA,0) AS SUA,ISNULL(XEM,0) AS XEM,ISNULL(CHOT,0) AS CHOT, "+
			"	ISNULL(HUYCHOT,'0') AS HUYCHOT "+
			"	FROM NHOMQUYEN  A INNER JOIN PHANQUYEN B ON A.DMQ_FK = B.DMQ_FK  "+ 
			"	INNER JOIN UNGDUNG UD ON UD.PK_SEQ=A.UNGDUNG_FK  "+
			" WHERE B.NHANVIEN_FK='"+userId+"' AND UD.SERVLET='"+servlet+"' AND UD.PARAMETERS='"+parameters+"' ";
		System.out.println("[QUERY quyen]"+query);

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


	public String getTructhuoc_fk()
	{
		return tructhuoc_fk;
	}
	public void setTructhuoc_fk(String tructhuoc_fk)
	{
		this.tructhuoc_fk = tructhuoc_fk;
	}


	public String getIdNhapp(String userid)
	{
		String sql=
			"			select nv.dangnhap,npp.LoaiNPP, npp.khosap, npp.pk_seq,npp.sitecode,npp.ten,npp.tructhuoc_fk,nv.PhanLoai  \n"+ 
			"			from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode   \n"+
			"			where nv.pk_seq='"+userid+"' and nv.trangthai='1'  and nv.PHANLOAI=1 and npp.isKHACHHANG = '0'  \n"+ 
			"			union all  \n"+
			"			select nv.dangnhap,npp.LoaiNPP, npp.khosap, npp.pk_seq,npp.sitecode,gs.ten,npp.tructhuoc_fk,nv.PhanLoai  \n"+ 
			"			from nhanvien nv inner join GIAMSATBANHANG gs on nv.GSBH_FK=gs.PK_SEQ  \n"+
			"				inner join NHAPHANPHOI npp on npp.SITECODE=nv.CONVSITECODE  \n"+
			"			where nv.pk_seq='"+userid+"' and nv.trangthai='1'  and nv.PHANLOAI=2 and gs.TRANGTHAI=1  \n"+
			"			and npp.TRANGTHAI=1 \n";

		//System.out.println("___getIdNhapp__\n" + sql + "\n----------------------------------------------");

		dbutils db=new dbutils();
		ResultSet rs= db.get(sql);
		try
		{
			if(rs.next())
			{
				this.nppId=rs.getString("pk_seq");
				this.nppTen=rs.getString("ten");
				this.sitecode=rs.getString("sitecode");
				this.tructhuoc_fk = rs.getString("tructhuoc_fk") == null?"":rs.getString("tructhuoc_fk");
				this.phanloai=rs.getString("PhanLoai") == null?"":rs.getString("PhanLoai");

				rs.close();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
		db.shutDown();
		return this.nppId;
	}

	public String getTieuDe(String table,String column,String id ,geso.dms.center.db.sql.dbutils db)
	{
		String query=" select  dbo.ftBoDau("+column+")  from "+table+" where pk_seq in ("+id+")";
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
			/*System.out.print("[TieuDe]"+query);*/
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

	public String setTieuDe(IStockintransit obj  )
	{
		String tieude="_";
		dbutils db = new dbutils();

		if(obj.getkenhId()!=null && obj.getkenhId().length()>0)
		{
			tieude +=this.getTieuDe( "KenhBanHang","Ten", obj.getkenhId(), db )+ "_"; 
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
			tieude +=this.getTieuDe( "ASM","Ten", obj.getASMId(), db )+ "_"; 
		}
		if(obj.getBMId()!=null && obj.getBMId().length()>0)
		{
			tieude +=this.getTieuDe( "BM","Ten", obj.getBMId(), db )+ "_"; 
		}

		if(obj.getgsbhId()!=null && obj.getgsbhId().length()>0)
		{
			tieude += this.getTieuDe( "GiamSatBanHang","Ten", obj.getgsbhId(),db ) + "_" ;
		}
		if(obj.getDdkd()!=null && obj.getDdkd().length()>0)
		{
			tieude += this.getTieuDe( "DaiDienKinhDoanh","Ten", obj.getDdkd(),db ) + "_" ;
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

	public String setTieuDe(Reports obj  )
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

	public String setTieuDe(ITieuchithuongTLList obj  )
	{
		String tieude="";
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

	public String getTenNhaPP(){
		return this.nppTen;
	}
	public String getSitecode(){
		return this.sitecode;
	}
	public String ValidateParam(String param){		
		String result;
		if (param == null){
			result="";
		}else{
			if (param.indexOf("=") > 0){
				result = "";
			}else{
				result = param;
			}
		}
		return result;
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
			if(userId.contains(";"))
				userId = userId.split(";")[0];
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
		String view;
		String tmp;
		if (querystring != null){
			if (querystring.contains("&")){
				tmp = querystring.split("&")[1];
				view = tmp.split("=")[1];
			}else{
				view = "";
			}
		}else{
			view = "";
		}
		return view;

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

	public String[] ResultSetToArrayString(ResultSet rs)
	{
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
	public String[] compareArrayString(String[] s1, String[] s2){
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

	public int CompareDATE(String _date1, String _date2)
	{
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//Date date1 = sdf.parse("2014-10-01");
			//Date date2 = sdf.parse("2014-10-01");

			Date date1 = sdf.parse(_date1);
			Date date2 = sdf.parse(_date2);

			////System.out.println(sdf.format(date1));
			////System.out.println(sdf.format(date2));

			return date1.compareTo(date2);
		}
		catch (Exception e) {
			return 0;
		}
	}
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	public	boolean isNumeric(String input){ 
		boolean result = true;
		char[] all = input.toCharArray();

		for(int i = 0; i < all.length;i++) {
			if(!(Character.isDigit(all[i]))) {
				result = false;
			}
		}
		return result;
	}

	public String calSum(String a, String b)
	{
		if(a==null||b==null)
			return "";
		String s = "" + (a.length()+ b.length())/a.length();
		return s;
	}

	public boolean check(String a, String b, String c)
	{
		if(a==null||b==null||c==null)
			return false;
		String tmp = calSum(a, b);
		if (tmp.equals(c)){
			return true;
		}else{
			return false;
		}
	}

	public boolean isSessionAlive(HttpSession session){
		String userId = (String)session.getAttribute("userId");
		String userTen = (String)session.getAttribute("userTen");
		String sum = (String)session.getAttribute("sum");
		if(check(userId, userTen, sum)){		
			return true;
		}else{			
			return false;
		}
	}

	public String quyen_sanpham(String userId)
	{
		//String sql ="( select sanpham_fk from nhanvien_sanpham where nhanvien_fk ='"+ userId +"'     union  select PK_sEQ FROM SanPham WHERE 1=(SELECT iSAdmin FROM NHANVIEN WHERE iSAdmin=1 AND PK_SEQ='"+userId+"')   )";
		String sql ="( select sanpham_fk from nhanvien_sanpham where nhanvien_fk ='"+ userId +"'     )";
		return sql;
	}

	public String quyen_npp(String userId)
	{   
		String sql =" ( select npp_fk from phamvihoatdong where nhanvien_fk ='"+ userId +"'   ) ";

		return sql;
	}
	public static String Quyen_npp(String userId)
	{   
		String sql =" ( select npp_fk from phamvihoatdong where nhanvien_fk ='"+ userId +"'   ) ";

		return sql;
	}


	public String Quyen_Ddkd(String userId)
	{   
		String sql ="( select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk in  ( select npp_fk from phamvihoatdong where nhanvien_fk ='"+userId+"'      )) ";
		return sql;
	}

	public String Quyen_KhuVuc(String userId)
	{   
		String sql ="( select pk_Seq from KhuvUC where pk_Seq in  " +
		" ( select khuvuc_Fk from NhapHANphoi where khuvuc_Fk is not null and  pk_Seq in "+quyen_npp(userId)+"   ) )";
		return sql;
	}

	public String Quyen_Vung(String userId)
	{   
		String sql ="( select vung_fk from KhuvUC where pk_Seq in  " +
		" ( select khuvuc_Fk from NhapHANphoi where khuvuc_Fk is not null and  pk_Seq in "+quyen_npp(userId)+"   ) )";
		return sql;
	}

	public String Quyen_TinhThanh(String userId)
	{   
		String sql ="( select pk_Seq from TinhThanh where pk_Seq in  " +
		" ( select tinhthanh_fk from NhapHANphoi where tinhthanh_fk is not null and  pk_Seq in "+quyen_npp(userId)+"   ) )";
		return sql;
	}

	public String Quyen_TinhThanhTheoKhachHang(String userId)
	{   
		String sql ="( select pk_Seq from TinhThanh where pk_Seq in  " +
		" ( select tinhthanh_fk from KhachHang where tinhthanh_fk is not null and  npp_fk in "+quyen_npp(userId)+"   ) )";
		return sql;
	}

	public String quyen_kenh(String userId)
	{
		String sql ="( select kenh_fk as kbh_fk from nhanvien_kenh where nhanvien_fk ='"+ userId +"'    )";
		//	String sql ="( select pk_seq from KENHBANHANG where trangthai ='1' )";
		return sql;
	}

	public int[] quyen_ungdung(String userId)
	{  
		int mang[] = new int[300];
		String sql ="select ungdung_fk from nhomquyen where dmq_fk in (select pk_seq from danhmucquyen where pk_seq in (select dmq_fk from phanquyen where nhanvien_fk ='"+ userId +"'))";
		//System.out.println("PHAN QUYEN : " + sql);
		dbutils db=new dbutils();
		ResultSet rs= db.get(sql);
		for(int j = 0;j<300;j++)
			mang[j] = 0;
		int i = 0;
		if(rs!=null)
			try {
				while(rs.next())
				{
					i = Integer.parseInt(rs.getString("ungdung_fk"));
					mang[i] = 1;

				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			db.shutDown();
			return mang;
	}

	/*public ResultSet getMenuRs(String userId,String loaiMenu,dbutils db )
	{
		String query=
				"\n select pk_seq as ungdungcha, '' as level1, '' as level2, ten, servlet, parameters, sott as stt1, -1 as stt2, -1 as sott, level,       "+   
				"\n   	isnull((select count(*)  from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq inner join ungdung c on b.ungdung_fk = c.pk_seq   "+   
				"\n  		where c.pk_seq = ud.pk_seq and a.level = '3' and a.PK_SEQ in  (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk='"+userId+"')),0) as				cosubmenu,0 as maxstt, 0 as totalstt, 1 as max_total_stt, 1 as molevel1, 0 as molevel2        "+   
				"\n   from ungdung ud       "+   
				"\n   where level = '1'   and ud.loaiMenu='"+loaiMenu+"' and ud.TrangThai=1    "+   
				"\n   and ud.PK_SEQ in 	  "+   
				"\n   (  "+   
				"\n   	select PK_SEQ from UNGDUNG where ungdung_fk is null and Level=1  "+   
				"\n   	and PK_SEQ in (select distinct UNGDUNG.ungdung_fk from nhanvien_ungdung inner join UNGDUNG on nhanvien_ungdung.ungdung_fk=UNGDUNG.PK_SEQ  "+   
				"\n   	where nhanvien_ungdung.nhanvien_Fk='"+userId+"' and ungdung.level=3 )  and UNGDUNG.TrangThai=1   "+   
				"\n   	union  "+   
				"\n   	  "+   
				"\n   	select PK_SEQ from UNGDUNG where ungdung_fk is null and Level=1  "+   
				"\n   	and PK_SEQ in (select distinct ud.ungdung_fk from nhanvien_ungdung inner join UNGDUNG on nhanvien_ungdung.ungdung_fk=UNGDUNG.PK_SEQ  "+   
				"\n   	inner join UNGDUNG ud on ud.PK_SEQ=ungdung.ungdung_fk  "+   
				"\n   	where nhanvien_ungdung.nhanvien_Fk='"+userId+"' and ungdung.level=3 ) and UNGDUNG.TrangThai=1  "+   
				"\n    )  "+   
				"\n     "+   
				"\n   union all         "+   
				"\n   select b.pk_seq as ungdungcha, '' as level1, '' as level2, a.ten, a.servlet, a.parameters, b.sott as stt1, a.sott as stt2, -1 as sott, a.level,   "+   
				"\n   0, 0 as		maxstt, 0 as totalstt, 1 as max_total_stt, 0 as molevel1, 1 as molevel2         "+   
				"\n   from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq       "+   
				"\n   where a.level = '2' 	 and a.loaiMenu='"+loaiMenu+"' 	and a.TrangThai=1 and b.TrangThai=1  "+   
				"\n   and exists  	  "+   
				"\n   (   "+   
				"\n   	select nvud.ungdung_Fk from NhanVien_UngDung nvud inner join UNGDUNG ud on nvud.ungdung_fk=ud.PK_SEQ  "+   
				"\n  	 where nvud.NhanVien_fk='"+userId+"'    and ud.UNGDUNG_FK=a.PK_SEQ     "+   
				"\n   )  "+   
				"\n   union all         "+   
				"\n   select b.pk_seq as ungdungcha, c.ten as level1, b.ten as level2, a.ten, a.servlet, a.parameters, c.sott as stt1, b.sott as stt2, a.sott, a.level, 0,  "+   
				"\n   ( select max(sott) from ungdung where ungdung_fk = b.pk_seq and PK_SEQ 	in    "+   
				"\n   (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk='"+userId+"') )   as maxstt, b.sott + a.sott as totalstt,   "+

				"\n		isnull(( select max(sott) from ungdung where ungdung_fk=b.pk_seq and "+
				"\n	PK_SEQ IN (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk='"+userId+"')),0)" +
				"\n + isnull(( select max(sott) from ungdung l1  where ungdung_fk =c.PK_SEQ   "+
				"\n		AND PK_sEQ IN (select b.ungdung_fk from NhanVien_UngDung a inner join UNGDUNG b on b.PK_SEQ=a.UngDung_fk where NhanVien_fk='"+userId+"') ),0)  as max_total_stt, 0 as molevel1, 0 as molevel2	      "+   
				"\n   from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq          "+   
				"\n   inner join ungdung c on b.ungdung_fk = c.pk_seq         "+   
				"\n   where a.level = '3' and b.level = '2'     and a.loaiMenu='"+loaiMenu+"' and a.TrangThai=1 and b.TrangThai=1 and c.TrangThai=1 "+   
				"\n   and a.pk_seq    "+   
				"\n   in  (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk='"+userId+"')  "+   
				"\n   union all      "+   
				"\n   select a.pk_seq as ungdungcha, c.ten as level1, '' as level2, a.ten, a.servlet, a.parameters, c.sott as stt1, a.sott as stt2, a.sott, a.level, 0,       "+   
				"\n   ( select max(sott) from ungdung where ungdung_fk = c.pk_seq ) as maxstt, a.sott as totalstt, ( select max(sott) from ungdung where ungdung_fk = c.pk_seq ) as max_total_stt, 0 as molevel1, 0 as molevel2	      "+   
				"\n   from ungdung a  inner join ungdung c on a.ungdung_fk = c.pk_seq         "+   
				"\n   where a.level = '3' and c.level = '1'     and a.loaiMenu='"+loaiMenu+"'  and a.TrangThai=1  and c.TrangThai=1     "+   
				"\n   and a.pk_seq  in     "+   
				"\n   (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk= '"+userId+"' )      "+   
				"\n   order by stt1 asc, stt2 asc, sott asc  ";	

		ResultSet rs =db.get(query);
		//System.out.println("Menu Query: "+query);
		return rs;
	}
	 */

	public ResultSet getMenuRs(String userId, String loaiMenu, dbutils db)
	{
		String query = "\n select pk_seq as ungdungcha, '' as level1, '' as level2, ten, servlet, parameters, " +
		"\n sott as stt1, -1 as stt2, -1 as sott, level, " +
		"\n isnull(( " +
		"\n     select count(*) " +
		"\n     from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq " +
		"\n     inner join ungdung c on b.ungdung_fk = c.pk_seq " +   
		"\n     where a.trangthai = 1 and b.trangthai = 1 and c.trangthai = 1 and c.pk_seq = ud.pk_seq " +
		"\n     and a.level = '3' and a.PK_SEQ in (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk = '" + userId + "'))" +
		"\n ,0) as cosubmenu, " +
		"\n 0 as maxstt, 0 as totalstt, 1 as max_total_stt, 1 as molevel1, 0 as molevel2 " +   
		"\n from ungdung ud " +   
		"\n where level = '1' and ud.loaiMenu = '" + loaiMenu + "' and ud.TrangThai = 1 " +   
		"\n and ud.PK_SEQ in " +   
		"\n ( " +   
		"\n     select PK_SEQ from UNGDUNG " +
		"\n     where ungdung_fk is null and Level = 1 " +   
		"\n     and PK_SEQ in ( " +
		"\n         select distinct UNGDUNG.ungdung_fk " +
		"\n         from nhanvien_ungdung inner join UNGDUNG on nhanvien_ungdung.ungdung_fk = UNGDUNG.PK_SEQ " +   
		"\n         where nhanvien_ungdung.nhanvien_Fk = '" + userId + "' and ungdung.level = 3 " +
		"\n     ) " +
		"\n     and UNGDUNG.TrangThai = 1 " +   
		"\n     union " +     
		"\n     select PK_SEQ from UNGDUNG " +
		"\n     where ungdung_fk is null and Level = 1 " +   
		"\n     and PK_SEQ in ( " +
		"\n         select distinct ud.ungdung_fk " +
		"\n         from nhanvien_ungdung inner join UNGDUNG on nhanvien_ungdung.ungdung_fk = UNGDUNG.PK_SEQ " +   
		"\n         inner join UNGDUNG ud on ud.PK_SEQ = ungdung.ungdung_fk " +   
		"\n         where nhanvien_ungdung.nhanvien_Fk = '" + userId + "' and ungdung.level = 3 " +
		"\n     ) " +
		"\n     and UNGDUNG.TrangThai = 1 " +   
		"\n ) " +   
		"\n union all " +   
		"\n select b.pk_seq as ungdungcha, '' as level1, '' as level2, a.ten, a.servlet, a.parameters, " +
		"\n b.sott as stt1, a.sott as stt2, -1 as sott, a.level, 0, 0 as maxstt, 0 as totalstt, " +
		"\n 1 as max_total_stt, 0 as molevel1, 1 as molevel2 " +   
		"\n from ungdung a " +
		"\n inner join ungdung b on a.ungdung_fk = b.pk_seq " +   
		"\n where a.level = '2' and a.loaiMenu = '" + loaiMenu + "' and a.TrangThai = 1 and b.TrangThai = 1 " +   
		"\n and exists " +   
		"\n ( " +   
		"\n     select nvud.ungdung_Fk " +
		"\n     from NhanVien_UngDung nvud inner join UNGDUNG ud on nvud.ungdung_fk = ud.PK_SEQ " +   
		"\n     where nvud.NhanVien_fk = '" + userId + "' and ud.UNGDUNG_FK = a.PK_SEQ " +   
		"\n ) " +   
		"\n union all " +   
		"\n select b.pk_seq as ungdungcha, c.ten as level1, b.ten as level2, a.ten, a.servlet, a.parameters, " +
		"\n c.sott as stt1, b.sott as stt2, a.sott, a.level, 0, " +   
		"\n ( " +
		"\n     select max(sott) " +
		"\n     from ungdung where trangthai = 1 and ungdung_fk = b.pk_seq " +
		"\n     and PK_SEQ in (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk = '" + userId + "') " +
		"\n ) as maxstt, " +
		"\n b.sott + a.sott as totalstt,   "+
		"\n isnull(( " +
		"\n     select max(sott) from ungdung where ungdung_fk = b.pk_seq " +
		"\n     and PK_SEQ IN (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk = '"+userId+"') " +
		"\n ),0)" +
		"\n + isnull(( " +
		"\n     select max(sott) " +
		"\n     from ungdung l1 where trangthai = 1 and ungdung_fk = c.PK_SEQ " +
		"\n     AND PK_SEQ IN " +
		"\n     ( " +
		"\n         select b.ungdung_fk from NhanVien_UngDung a inner join UNGDUNG b on b.PK_SEQ = a.UngDung_fk " +
		"\n         where NhanVien_fk = '" + userId + "' " +
		"\n     ) " +
		"\n ),0) as max_total_stt, 0 as molevel1, 0 as molevel2	" +   
		"\n from ungdung a inner join ungdung b on a.ungdung_fk = b.pk_seq " +   
		"\n inner join ungdung c on b.ungdung_fk = c.pk_seq " +   
		"\n where a.level = '3' and b.level = '2' and a.loaiMenu = '" + loaiMenu + "' " +
		"\n and a.TrangThai = 1 and b.TrangThai = 1 and c.TrangThai = 1 " +   
		"\n and a.pk_seq in (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk = '" + userId + "') " +   
		"\n union all " +   
		"\n select a.pk_seq as ungdungcha, c.ten as level1, '' as level2, a.ten, a.servlet, a.parameters, " +
		"\n c.sott as stt1, a.sott as stt2, a.sott, a.level, 0, " +   
		"\n ( " +
		"\n     select max(sott) " +
		"\n     from ungdung " +
		"\n     where trangthai = 1 and ungdung_fk = c.pk_seq " +
		"\n ) as maxstt, a.sott as totalstt, " +
		"\n (select max(sott) from ungdung where ungdung_fk = c.pk_seq) as max_total_stt, " +
		"\n 0 as molevel1, 0 as molevel2 " +   
		"\n from ungdung a inner join ungdung c on a.ungdung_fk = c.pk_seq " +   
		"\n where a.level = '3' and c.level = '1' and a.loaiMenu = '" + loaiMenu + "' " +
		"\n and a.TrangThai = 1 and c.TrangThai = 1 " +   
		"\n and a.pk_seq in (select ungdung_Fk from NhanVien_UngDung where NhanVien_fk = '" + userId + "') " +   
		"\n order by stt1 asc, stt2 asc, sott asc ";	
		ResultSet rs = db.get(query);
		//System.out.println("Menu Query: " + query);

		return rs;
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
		'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', 'Ỹ', 'Ý', 'Ỳ', 'Ỵ', 'ỳ', 'ỵ', 'ý', 'ỹ'};

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
		'U', 'u', 'Ỹ', 'Y', 'Y', 'Y', 'y', 'y', 'y', 'y'};

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
		//System.out.println(String.valueOf(buffer, 0, n));
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

	public String setTieuDe(ITieuchithuongTBList obj)
	{
		String tieude="";
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

	public String setTieuDe(Ireport obj)
	{

		String tieude="";
		dbutils db = new dbutils();


		if(obj.getkenhId()!=null && obj.getkenhId().length()>0)
		{
			tieude +=this.getTieuDe( "KenhBanHang","Ten", obj.getkenhId(), db )+ "_"; 
		}
		if(obj.getdvkdId()!=null && obj.getdvkdId().length()>0)
		{
			tieude += this.getTieuDe( "DonViKinhDoanh","DienGiai", obj.getdvkdId(),db ) + "_" ;
		}



		if(obj.getkhachhangId()!=null && obj.getkhachhangId().length()>0)
		{
			tieude += this.getTieuDe( "KhachHang","Ten", obj.getkhachhangId(),db ) + "_" ;
		}

		if(obj.getnhanhangId()!=null && obj.getnhanhangId().length()>0 )
		{
			tieude += this.getTieuDe( "NhanHang","Ten", obj.getnhanhangId(),db ) + "_" ;
		}
		if(obj.getchungloaiId()!=null && obj.getchungloaiId().length()>0 )
		{
			tieude += this.getTieuDe( "ChungLoai","Ten", obj.getchungloaiId(),db ) + "_" ;
		}
		if(obj.getVungId()!=null && obj.getVungId().length()>0)
		{
			tieude += this.getTieuDe( "Vung","Ten", obj.getVungId(),db ) + "_" ;
		}

		if(obj.getKvId()!=null && obj.getKvId().length()>0)
		{
			tieude += this.getTieuDe( "KhuVuc","Ten", obj.getKvId(),db ) + "_" ;
		}

		if(obj.getnppId()!=null && obj.getnppId().length()>0)
		{
			tieude += this.getTieuDe( "NhaPhanPhoi","Ten", obj.getnppId(), db ) + "_" ;
		}

		if(obj.gettungay()!=null && obj.gettungay().length()>0)
		{
			tieude += obj.gettungay() + "_";
		}
		if(obj.getdenngay()!=null && obj.getdenngay().length()>0)
		{
			tieude += obj.getdenngay() + "_";
		}



		db.shutDown();
		return tieude;
	}

	public String setTieuDe(IRouteSumaryReport obj)
	{
		String tieude="";
		dbutils db = new dbutils();
		if(obj.getKhuVuc()!=null && obj.getKhuVuc().length()>0)
		{
			tieude += this.getTieuDe( "KhuVuc","Ten", obj.getKhuVuc(), db ) + "_" ;
		}

		if(obj.getnppId()!=null && obj.getnppId().length()>0)
		{
			tieude += this.getTieuDe( "NhaPhanPhoi","Ten", obj.getnppId(), db ) + "_" ;
		}


		db.shutDown();
		return tieude;
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
		//System.out.println("GET_XNT_SP " +kbh_fk+","+npp_fk+","+kho_fk+","+ngaynhap+","+sanpham_fk);
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

							String query="select distinct ngaydonhang as ngaynhap from ufn_XNT_Total_SP_NgayPhatSinhNV("+npp_fk+",'"+ngaynhap+"',"+sanpham_fk+","+kho_fk+") "+
									" union all select '"+ngaynhap+"' ";
							////System.out.println("--------query "+query);
							ResultSet rs3=null;
							if(!nghiepvu.equals(""))
							{
							 rs3=db.getRsByPro("XNT_Total_SP_NgayPhatSinhNV", param2);
								//System.out.println("nghiep vu la XNT_Total_SP_NgayPhatSinhNV"+ npp_fk+",'"+ngaynhap+"',"+sanpham_fk+","+kho_fk+","+userid);

							}
							else
							{
							 rs3=db.getRsByPro("XNT_Total_SP_NgayPhatSinhNV_khac", param2);
								//System.out.println("nghiep vu la XNT_Total_SP_NgayPhatSinhNV_khac"+ npp_fk+",'"+ngaynhap+"',"+sanpham_fk+","+kho_fk+","+userid);

							}

							double min=999999999;

							while(rs3.next())
							{
								String que="select ma from sanpham where pk_seq="+sanpham_fk;
								ResultSet rscc=db.get(que);
								rscc.next();
								sanphamma=rscc.getString("ma");
								rscc.close();
								String ngaychay=rs3.getString("ngaynhap");

								//System.out.println("vao day 1"+ngaychay);
								String quString=" select spten,SANPHAM_fk,npp_fk,kho_fk,kbh_fk,total_booked  from ufn_Booked_Total_denngay("+npp_fk+",'"+ngaychay+"') where sanpham_fk= "+sanpham_fk +" and kbh_fk="+kbh_fk +" and kho_fk="+kho_fk ;
								//System.out.println("vao cau nay ne "+quString);
								ResultSet rs2=db.get(quString);
								if(rs2.next())
								{
									book=rs2.getDouble("total_booked") - luongbookcu;
									////System.out.println("luong book la "+book );
								}
								rs2.close();
								double xntthoidhien=0;
								param1[0]=kbh_fk;
								param1[1]=npp_fk;
								param1[2]=kho_fk;
								param1[3]=ngaychay;
								param1[4]=sanpham_fk;
								ResultSet	rsxnt = db.getRsByPro("GET_XNT_SP", param1);
								//System.out.println("EXEC   GET_XNT_SP " +kbh_fk+","+npp_fk+","+kho_fk+",'"+ngaychay+"',"+sanpham_fk);
								if(rsxnt.next())
								{
								tonhientai=rsxnt.getDouble("soluong")-book;
								xntthoidhien=rsxnt.getDouble("soluong");
								}
								else
									tonhientai=0-book;
								//System.out.println("luong book "+book +"xnt --"+rsxnt.getDouble("soluong")+" --- luong dh "+soluongbook + "ngay nhap "+ngaychay);

								//System.out.println("ton hien tai la  "+tonhientai +"-----"+soluongbook );
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

	public String Update_NPP_Kho_Sp_Chitiet(String ngaychungtu, String nghiepvu, Idbutils db, String khott_fk, String spId, 
			String npp_fk, String kbh_fk, String solo, String ngayhethan, String ngaynhapkho,
			double soluong, double booked, double available, double tonthoidiem, double dongia) {

		try 
		{

			int dungchungkenh = 0;
			String querylog = "";
			String query = "\n select isnull(dungchungkenh,0) dungchungkenh from nhaphanphoi where pk_seq = "+npp_fk;
			ResultSet rs = db.get(query);
			while (rs.next()) {
				dungchungkenh = rs.getInt("dungchungkenh");
			}
			rs.close();

			if (dungchungkenh == 1) {
				String kenh_OTC = "100025";
				kbh_fk = kenh_OTC;
			}

			int flag = 0;
			if (ngayhethan == null || ngayhethan.length() != 10) {
				return "Không xác định được ngày hết hạn của ID sản phẩm :"+spId+" và số lô: "+solo;
			}

			if (!solo.equals("NA"))
			{
				query = "\n select kho.booked, tonthoidiem, sanpham_fk, available, soluong, solo, ngayhethan, " +
				"\n sp.ma+ ' ' + sp.ten as ten, ISNULL(KHO.GIAMUA,0) AS GIATON " +
				"\n from NHAPP_KHO_CHITIET kho " +
				"\n inner join sanpham sp on kho.sanpham_fk = sp.pk_seq " +
				"\n where KBH_FK = "+kbh_fk+" AND NPP_FK = "+npp_fk+" " +
				"\n AND kho.KHO_FK = "+khott_fk+" and sanpham_fk = "+spId+
				"\n and solo = N'"+solo+"' and ngayhethan = '" +ngayhethan +"' " +
				"\n and ngaynhapkho = '"+ngaynhapkho+"' ";
			}
			else if (solo.equals("NA"))
			{
				query = "\n select kho.booked, tonthoidiem, sanpham_fk, available, soluong, solo, ngayhethan, " +
				"\n sp.ma+ ' ' + sp.ten as ten, ISNULL(KHO.GIAMUA,0) AS GIATON  " +
				"\n from NHAPP_KHO_CHITIET kho " +
				"\n inner join sanpham sp on kho.sanpham_fk = sp.pk_seq " +
				"\n where KBH_FK ="+kbh_fk+" AND NPP_FK = "+npp_fk+
				"\n AND kho.KHO_FK = "+khott_fk+" and sanpham_fk = "+spId+
				"\n and solo = N'"+solo+"' and ngayhethan = '"+ ngayhethan +"' " +
				"\n and ngaynhapkho= '"+ngaynhapkho+"'" ;
			}

			double available_ton = 0;
			double giaton = 0;
			double soluongton = 0;
			double tonthoidiem_ = 0;
			double booked_ton = 0;
			//System.out.println("[UTILITY KHO : QUERY LAY SAN PHAM KHO CHI TIET]: " +query);
			ResultSet rsCheck = db.get(query);
			if (rsCheck.next()) {
				tonthoidiem_= rsCheck.getDouble("tonthoidiem");
				soluongton = rsCheck.getDouble("soluong");
				available_ton = rsCheck.getDouble("available");
				booked_ton = rsCheck.getDouble("booked");
				giaton = rsCheck.getDouble("GIATON");
				//System.out.println("sanpham_fk: "+spId+"--soluong tata: "+soluongton+"--booked: "+booked_ton+"--avai: "+available_ton+"--soluong: "+soluong+"--book "+booked+"--avai: "+available);

				if (available < 0 && available_ton < (-1)*available) {
					//System.out.println("ton hien tai: "+available_ton+"--luong xuat ban: "+(-1)*available);
					return "Số lượng tồn hiện tại trong kho của sản phẩm: "+rsCheck.getString("ten") + " - Số lô: "+solo+" - Ngày nhập kho: "+ngaynhapkho+" - Ngày hết hạn : "+ngayhethan+" "+ 
					"  ["+available_ton+"] không đủ để trừ kho, vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

				if (soluong < 0 && soluongton < (-1)*soluong) {
					return "Số lượng tồn trong kho của sản phẩm: "+rsCheck.getString("ten") + " - Số lô: "+solo+" - Ngày nhập kho: "+ngaynhapkho+" - Ngày hết hạn : "+ngayhethan+" "+ 
					"  ["+soluongton+"] không đủ để trừ kho, vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

				if (available < 0 || soluong < 0) {
					int i = this.CompareDATE(ngaynhapkho, ngaychungtu);
					if (i > 0) {
						//System.out.println("ngay nhap kho "+ngaynhapkho +"ngay chung tu "+ngaychungtu);
						return "Chỉ được xuất những lô có ngày nhập nhỏ hơn ngày làm nghiệp vụ:  Số lô "+solo+" -- sản phẩm  : "+rsCheck.getString("ten") + " - Ngày nhập kho : "+ ngaynhapkho +" --Ngay hết han: "+ngayhethan +" không hợp lệ." ;
					}
				}

				query = "\n Update NHAPP_KHO_CHITIET set booked = round(isnull(booked,0),1) + round("+booked+",1), " +
				"\n soluong = round(ISNULL(soluong,0),1) + round(" + soluong + ",1), " +
				"\n AVAILABLE = round(ISNULL(AVAILABLE,0),1) + round(" + available + ",1) " +
				"\n where KBH_FK = "+kbh_fk+" AND NPP_FK = "+npp_fk+" " +
				"\n AND KHO_FK = "+khott_fk+" and sanpham_fk = "+spId+
				"\n and solo = N'"+solo+"' and ngayhethan = '"+ngayhethan+"' " +
				"\n and ngaynhapkho = '"+ngaynhapkho+"'";

				querylog = "\n insert into log_kho_solo (nghiepvu, sanpham_fk, kbh_fk, kho_fk, npp_fk, solo, " +
				"\n ngayhethan, ngaynhapkho, soluong, book, avai, ngaychungtu, soluongNV, bookNv, avaiNV) " +
				"select N'"+nghiepvu+"', sanpham_fk, kbh_fk, kho_fk, npp_fk, solo, ngayhethan, ngaynhapkho, " +
				"\n SOLUONG, BOOKED, AVAILABLE, '"+ngaychungtu+"', round(" + soluong + ",1), " +
				"\n round("+booked+",1), round(" + available + ",1) " +
				"\n from nhapp_kho_chitiet " +
				"\n where solo = '"+solo+"' and ngayhethan = '"+ngayhethan+"' and ngaynhapkho = '"+ngaynhapkho+"' " +
				"\n and npp_fk = '"+npp_fk+"' and kbh_fk = '"+kbh_fk+"' and kho_fk = '"+khott_fk+"' and sanpham_fk = '"+spId+"' ";
			}
			else {
				query = "\n SELECT sp.ma+ ' '+ sp.ten as ten from sanpham sp where pk_seq = "+spId;
				ResultSet rssp1 = db.get(query);
				String tensp = "";

				if (rssp1.next()) {
					tensp = rssp1.getString("ten");
				}
				rssp1.close();

				if (available < 0 || soluong < 0) {
					int i = this.CompareDATE(ngaynhapkho,ngaychungtu);
					if (i > 0) {
						return "Chỉ được xuất những lô có ngày nhập nhỏ hơn ngày làm nghiệp vụ: Số lô "+solo+" -- sản phẩm: "+tensp+" - Ngày nhập kho: "+ ngaynhapkho+" --Ngay hết hạn: "+ngayhethan+" không hợp lệ.";
					}
				}

				flag = 1;
				query = "\n INSERT INTO NHAPP_KHO_CHITIET(KHO_FK, SANPHAM_FK, NPP_FK, KBH_FK, SOLO, NGAYHETHAN, NGAYNHAPKHO, " +
				"\n GIAMUA, SOLUONG, BOOKED, AVAILABLE, TONTHOIDIEM) VALUES " +
				"\n ("+khott_fk+", "+ spId+", "+npp_fk+", "+kbh_fk+", N'"+ solo +"', '"+ ngayhethan +"', '"+ ngaynhapkho +"',"+dongia+", " +
				"\n round("+soluong+",1), round("+booked+",1), round("+available+",1), round("+tonthoidiem+",1)) ";

				querylog = "\n insert into log_kho_solo(nghiepvu, sanpham_fk, kbh_fk, kho_fk, npp_fk, solo, ngayhethan, ngaynhapkho, " +
				"\n soluong, book, avai, ngaychungtu, soluongNV, bookNv, avaiNV) " +
				"\n select N'"+nghiepvu+"', sanpham_fk, kbh_fk, kho_fk, npp_fk, solo, ngayhethan, ngaynhapkho, SOLUONG, BOOKED, AVAILABLE, " +
				"\n '"+ngaychungtu+"', round(" + soluong + ",1), round("+booked+",1), round(" + available + ",1) " +
				"\n from nhapp_kho_chitiet " +
				"\n where solo = '"+solo+"' and ngayhethan = '"+ngayhethan+"' and ngaynhapkho = '"+ngaynhapkho+"' " +
				"\n and npp_fk = '"+npp_fk+"' and kbh_fk = '"+kbh_fk+"' and kho_fk = '"+khott_fk+"' and sanpham_fk = '"+spId+"' ";

				if (available < 0 && available_ton < (-1)*available) {
					return "Số lượng tồn hiện tại trong kho của sản phẩm: "+tensp+ " - Số lô :"+solo+" - Ngày nhập kho: "+ngaynhapkho+" - Ngày hết hạn: "+ngayhethan+" "+
					"  ["+available_ton+"] không đủ để trừ kho, vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

				if (soluong < 0 && soluongton < (-1)*soluong) {
					return "Số lượng tồn trong kho của sản phẩm: "+tensp+ " - Số lô: "+solo+" - Ngày nhập kho: "+ngaynhapkho+" - Ngày hết hạn: "+ngayhethan+" "+
					"  ["+soluongton+"] không đủ để trừ kho, vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này." ;
				}

			}
			rsCheck.close();
			//System.out.println( nghiepvu+ " CAP NHAT KHO : "+query);

			int resultInt = -1;
			System.out.println("query chi tiet : "+ query);
			resultInt = db.updateReturnInt(query);
			if (resultInt != 1)
			{
				return " Không thể cập nhật Kho chi tiết 1.";
			}

			if (flag == 0 || flag == 1)
			{	
				resultInt = db.updateReturnInt(querylog);
				if (resultInt != 1)
				{
					return " Không thể cập nhật Kho chi tiết 2.";
				}
			}
		}
		catch (Exception er) {
			er.printStackTrace();
			return  "Lỗi ngoại lệ cập nhật Kho chi tiết: "+er.getMessage();
		}

		return "";
	}

	public String Update_NPP_Kho_Sp(String ngaychungtu, String nghiepvu, Idbutils db, String khott_fk, String spId, String npp_fk, 
			String kbh_fk, double soluong, double booked, double available, double dongia) {

		try {

			int dungchungkenh = 0;
			String query = "\n select isnull(dungchungkenh,0) dungchungkenh from nhaphanphoi where pk_seq = "+npp_fk;
			ResultSet rs = db.get(query);
			while (rs.next()) {
				dungchungkenh = rs.getInt("dungchungkenh");
			}
			rs.close();

			if (dungchungkenh == 1) {
				String kenh_OTC = "100025";
				kbh_fk = kenh_OTC;
			}

			int flag = 0;
			String querylog = "";
			query = "\n select kho.booked,sanpham_fk, available, soluong, sp.ma+ ' ' + sp.ten as ten,  ISNULL(KHO.GIAMUA,0) AS GIATON   " +
			"\n from NHAPP_KHO kho " +
			"\n inner join sanpham sp on kho.sanpham_fk = sp.pk_seq " +
			"\n where KBH_FK = "+kbh_fk+" AND NPP_FK = "+npp_fk+" " +
			"\n AND kho.KHO_FK = "+khott_fk+" and sanpham_fk = "+spId;
			//System.out.println("[UTILITY KHO : QUERY LAY SAN PHAM KHO TONG]: " +query);
			double available_ton = 0;
			double giaton = 0;
			double soluongton = 0;
			double book_ton = 0;
			ResultSet rsCheck = db.get(query);
			if (rsCheck.next()){
				soluongton = rsCheck.getDouble("soluong");
				available_ton = rsCheck.getDouble("available");
				giaton = rsCheck.getDouble("GIATON");
				book_ton = rsCheck.getDouble("booked");

				if(available < 0 && available_ton < (-1)*available ){
					return "Số lượng tồn hiện tại trong kho của sản phẩm: "+rsCheck.getString("ten") + "  ["+available_ton+"] không đủ để trừ kho,vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

				if(soluong < 0 && soluongton <(-1)*soluong ){
					return "Số lượng tồn  trong kho của sản phẩm: "+rsCheck.getString("ten") + "  ["+soluongton+"] không đủ để trừ kho,vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

				querylog = "\n insert into log_kho (nghiepvu, sanpham_fk, kbh_fk, kho_fk, npp_fk, soluong, book, avai, " +
				"\n ngaychungtu, soluongNV, bookNv, avaiNV) " +
				"\n select N'"+nghiepvu+"', sanpham_fk, kbh_fk, kho_fk, npp_fk, SOLUONG, BOOKED, AVAILABLE, " +
				"\n '"+ngaychungtu+"', round(" + soluong + ",1), round("+booked+",1), round(" + available + ",1) " +
				"\n from nhapp_kho " +
				"\n where npp_fk = '"+npp_fk+"' and kbh_fk = '"+kbh_fk+"' " +
				"\n and kho_fk = '"+khott_fk+"' and sanpham_fk = '"+spId+"' ";

				query = "\n Update NHAPP_KHO set booked = round(isnull(booked,0),1) + round("+booked+",1), " +
				"\n soluong = round(ISNULL(soluong,0),1) + round(" + soluong + ",1), " +
				"\n AVAILABLE = round(ISNULL(AVAILABLE,0),1) + round(" + available + ",1), " +
				"\n GIAMUA = "+(giaton >0?giaton:dongia)+
				"\n where KBH_FK = "+kbh_fk+" AND NPP_FK = "+npp_fk+" " +
				"\n AND KHO_FK = "+khott_fk+" and sanpham_fk = "+spId;
			}
			else {

				flag = 1;

				query = "\n INSERT INTO NHAPP_KHO (KHO_FK, SANPHAM_FK, NPP_FK, KBH_FK, GIAMUA, SOLUONG, BOOKED, AVAILABLE) " +
				"\n VALUES ("+khott_fk+", "+ spId+", "+npp_fk+", "+kbh_fk+", "+dongia+", " +
				"\n round("+soluong+",1), round("+booked+",1), round("+available+",1)) ";

				querylog = "\n insert into log_kho (nghiepvu, sanpham_fk, kbh_fk, kho_fk, npp_fk, " +
				"\n soluong, book, avai, ngaychungtu, soluongNV, bookNv, avaiNV) " +
				"\n select N'"+nghiepvu+"', sanpham_fk, kbh_fk, kho_fk, npp_fk, SOLUONG, BOOKED, AVAILABLE, " +
				"\n '"+ngaychungtu+"', round(" + soluong + ",1), round("+booked+",1), round(" + available + ",1) " +
				"\n from nhapp_kho where npp_fk = '"+npp_fk+"' and kbh_fk = '"+kbh_fk+"' " +
				"\n and kho_fk = '"+khott_fk+"' and sanpham_fk = '"+spId+"' ";

				if (available < 0 && available_ton < (-1)*available ){
					return "Số lượng tồn hiện tại trong kho của sản phẩm: "+rsCheck.getString("ten") + "  ["+available_ton+"] không đủ để trừ kho,vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

				if(soluong < 0 && soluongton <(-1)*soluong ){
					return "Số lượng tồn  trong kho của sản phẩm: "+rsCheck.getString("ten") + "  ["+soluongton+"] không đủ để trừ kho,vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này.";
				}

			}
			rsCheck.close();

			int resultInt = -1;

			resultInt = db.updateReturnInt(query);
			if (resultInt != 1)
			{
				return  "Không thể cập nhật Kho 1.";
			}

			if (flag == 0 || flag == 1)
			{
				resultInt = db.updateReturnInt(querylog);
				if (resultInt != 1)
				{
					return  "Không thể cập nhật Kho 2.";
				}
			}
		}
		catch (Exception er){
			er.printStackTrace();
			return  "Lỗi ngoại lệ cập nhật Kho: "+er.getMessage();
		}

		return "";
	}

	public String getngayhoadon(String userid,Idbutils db1,String  ngaydonhang ,String khachhangid,int loaichot)
	{
		String ngayhoadon="";
		String nppId="";

		String npp="select distinct  a.PK_SEQ from NHAPHANPHOI a inner join NHANVIEN nv on nv.CONVSITECODE=a.SITECODE where nv.PK_SEQ="+userid;
		ResultSet npprs=db1.get(npp);
		try {
			npprs.next();
			nppId=npprs.getString("pk_Seq");
			npprs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(loaichot==1)
		{
			String query =" select isnull(SOHOADONTU,'') as SOHOADONTU,isnull(SOHOADONDEN,'') as SOHOADONDEN,isnull(SOHOADONTU2,'') as SOHOADONTU2,isnull(SOHOADONDEN2,'') as SOHOADONDEN2 from NHANVIEN where pk_seq="+userid;
			//System.out.println("AAAAA:"+ query);
			ResultSet mauHDrs = db1.get(query);

			String SOHOADONTU1="";
			String SOHOADONDEN1="";
			String SOHOADONTU2="";
			String SOHOADONDEN2="";
			if(mauHDrs!=null)
			{
				try {
					while(mauHDrs.next())
					{
						SOHOADONTU1 = mauHDrs.getString("SOHOADONTU");
						SOHOADONDEN1 = mauHDrs.getString("SOHOADONDEN");
						SOHOADONTU2 = mauHDrs.getString("SOHOADONTU2");
						SOHOADONDEN2 = mauHDrs.getString("SOHOADONDEN2");

					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					mauHDrs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}


			// CN HÀ NỘI && (CN HCM CÓ KH KHAI BÁO MẪU 2) DÙNG MẪU 2 (HO), CÒN LẠI DÙNG MẪU 1
			String query_kyhieu = " NV.KYHIEU ";				
			String query_sohdTU = " NV.SOHOADONTU ";	
			String query_sohdDEN = " NV.SOHOADONDEN ";	
			String query_mauhd = "1";
			String query_ngayhd = " NV.NGAYHOADON ";

			query =" select mauhoadon from KHACHHANG where PK_SEQ ='"+khachhangid+"'";
			//System.out.println("AAAAA:"+ query);
			ResultSet mauHD = db1.get(query);
			String mau = "";
			if(mauHD!=null)
			{
				try {
					while(mauHD.next())
					{
						mau = mauHD.getString("mauhoadon");
					}
					mauHD.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			/*if(nppId.equals("100002") ||(nppId.equals("106210") && mau.equals("2"))  )
			{

				query_kyhieu = " NV.KYHIEU2 ";				
				query_sohdTU = " NV.SOHOADONTU2 ";	
				query_sohdDEN = " NV.SOHOADONDEN2 ";				
				query_mauhd = "2";
				query_ngayhd = " NV.NGAYHOADON2 ";

			}
			 */

			if(((SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0) && (SOHOADONDEN2.trim().length()==0 && SOHOADONTU2.trim().length()==0)) || ((SOHOADONDEN1.trim().length()==0 && SOHOADONTU1.trim().length()==0) && (SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)) )
			{
				if(SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0)
				{
					query_kyhieu = " NV.KYHIEU ";				
					query_sohdTU = " NV.SOHOADONTU ";	
					query_sohdDEN = " NV.SOHOADONDEN ";	
					query_mauhd = "1";
					query_ngayhd = " NV.NGAYHOADON  ";
				}
				if(SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)
				{
					query_kyhieu = " NV.KYHIEU2 ";				
					query_sohdTU = " NV.SOHOADONTU2 ";	
					query_sohdDEN = " NV.SOHOADONDEN2 ";				
					query_mauhd = "2";
					query_ngayhd = " NV.NGAYHOADON2 ";
				}
			}


			mau=query_mauhd;

			// LAY TT KHAI BAO SO HD TRONG DLN
			query= " SELECT ISNULL("+ query_ngayhd +", '') as NGAYHOADON "+
			" FROM NHANVIEN NV  \n" +
			" WHERE NV.pk_seq = '" + userid + "' \n";
			//System.out.println("Câu check khai báo SHD "+query);
			ResultSet rsLayDL = db1.get(query);

			int check_OTC = 0;
			int check_ETC = 0;

			try {
				while(rsLayDL.next())
				{

					ngayhoadon = rsLayDL.getString("ngayhoadon");
					if(ngayhoadon.trim().length() <= 0) 
						ngayhoadon = ngaydonhang;

				}
				rsLayDL.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if(loaichot==0)
		{
			String sql="";
			/*if(nppId.equals("100002"))
			{
				sql="select case when ngayhoadon2='' or ngayhoadon2 is null then '"+ngaydonhang+"'  else ngayhoadon2 end ngayhoadon from nhanvien where pk_seq="+userid;
			}
			else*/
			{
				sql="select case when ngayhoadon='' or ngayhoadon is null then '"+ngaydonhang+"'  else ngayhoadon end ngayhoadon from nhanvien where pk_seq="+userid;

			}
			//System.out.println("lay ngay hoad don : " +sql);
			ResultSet rs=db1.get(sql);
			try {
				if (rs.next())
				{
					ngayhoadon=rs.getString("ngayhoadon");
				}else{
					ngayhoadon= ngaydonhang;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}



		//System.out.println("ngay hoa don la "+ngayhoadon);

		return ngayhoadon;
	}



	public String[] LayThongTinSHD( geso.dms.distributor.db.sql.dbutils db, String userId, String nppId, String ngaydonhang, String khachhangID )
	{
		String kyhieuhoadon = "";
		String sohoadon = "";
		String ngayhoadon = "";
		String mau = "1";
		String msg = "";

		sohoadon = "NA";
		kyhieuhoadon = "NA";

		String chuoi = "";
		long sohoadontu = 0;
		long sohoadonden = 0;

		try
		{
			String query = "";

			// Lấy mẫu hóa đơn của Khách hàng dùng
			String SOHOADONTU1 = "";
			String SOHOADONDEN1 = "";
			String SOHOADONTU2 = "";
			String SOHOADONDEN2 = "";

			if( khachhangID.trim().length() > 0 )
			{
				query =" select mauhoadon from KHACHHANG where PK_SEQ = '" + khachhangID + "'";
				//System.out.println("AAAAA:"+ query);
				ResultSet mauHD = db.get(query);

				if(mauHD!=null)
				{
					while(mauHD.next())
					{
						mau = mauHD.getString("mauhoadon");
					}
					mauHD.close();
				}
				query = " select isnull(SOHOADONTU,'') as SOHOADONTU,isnull(SOHOADONDEN,'') as SOHOADONDEN,isnull(SOHOADONTU2,'') as SOHOADONTU2,isnull(SOHOADONDEN2,'') as SOHOADONDEN2 from NHANVIEN where pk_seq="+userId;
				//System.out.println("AAAAA:"+ query);
				ResultSet mauHDrs = db.get(query);

				if(mauHDrs != null)
				{
					while(mauHDrs.next())
					{
						SOHOADONTU1 = mauHDrs.getString("SOHOADONTU");
						SOHOADONDEN1 = mauHDrs.getString("SOHOADONDEN");
						SOHOADONTU2 = mauHDrs.getString("SOHOADONTU2");
						SOHOADONDEN2 = mauHDrs.getString("SOHOADONDEN2");
					}
					mauHDrs.close();
				}
			}

			/*if( loai == 4 || loai == 5 )	// DOI TAC VA CHI NHANH CUA DOI TAC
			{
				kyhieuhoadon = "NA";
				sohoadon = "NA";
				ngayhoadon = ngaydonhang;
				mau = "1";
			}*/
			//else
			{
				// CN HÀ NỘI && (CN HCM CÓ KH KHAI BÁO MẪU 2) DÙNG MẪU 2 (HO), CÒN LẠI DÙNG MẪU 1
				String query_kyhieu = " NV.KYHIEU ";				
				String query_sohdTU = " NV.SOHOADONTU ";	
				String query_sohdDEN = " NV.SOHOADONDEN ";	
				String query_mauhd = "1";
				String query_ngayhd = " NV.NGAYHOADON  ";



				if(((SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0) && (SOHOADONDEN2.trim().length()==0 && SOHOADONTU2.trim().length()==0)) || ((SOHOADONDEN1.trim().length()==0 && SOHOADONTU1.trim().length()==0) && (SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)) )
				{
					if(SOHOADONDEN1.trim().length()>0 && SOHOADONTU1.trim().length()>0)
					{
						query_kyhieu = " NV.KYHIEU ";				
						query_sohdTU = " NV.SOHOADONTU ";	
						query_sohdDEN = " NV.SOHOADONDEN ";	
						query_mauhd = "1";
						query_ngayhd = " NV.NGAYHOADON  ";
					}

					if(SOHOADONDEN2.trim().length()>0 && SOHOADONTU2.trim().length()>0)
					{
						query_kyhieu = " NV.KYHIEU2 ";				
						query_sohdTU = " NV.SOHOADONTU2 ";	
						query_sohdDEN = " NV.SOHOADONDEN2 ";				
						query_mauhd = "2";
						query_ngayhd = " NV.NGAYHOADON2 ";
					}
				}
				mau = query_mauhd;

				// LAY TT KHAI BAO SO HD TRONG DLN
				query= " SELECT ISNULL("+ query_ngayhd +", '') as NGAYHOADON, (CASE WHEN ISNULL("+ query_kyhieu +",'-1') = '' THEN '-1' ELSE ISNULL("+ query_kyhieu +",'-1') END)  as KYHIEU, \n"+
				"        ISNULL("+ query_sohdTU +", -1) AS SOHOADONTU, ISNULL("+ query_sohdDEN +", -1) AS SOHOADONDEN,  \n"+
				"        (select count(hd.pk_seq) as dem  "+
				"         from HOADON hd               "+
				"         where hd.trangthai != 3 and isnumeric(hd.sohoadon)=1 and hd.mauhoadon = "+ query_mauhd +" and hd.kyhieu = ISNULL("+ query_kyhieu +",'-1')  "+
				"               and cast(hd.sohoadon as int) >= cast(ISNULL("+ query_sohdTU +", -1) as int) "+
				"               and cast(hd.sohoadon as int) <= cast(ISNULL("+ query_sohdDEN +", -1) as int) and hd.NGUOISUA = NV.PK_SEQ) isSd_OTC, \n" +
				"        (select count(hd.pk_seq) as dem  "+
				"         from ERP_HOADONNPP hd               "+
				"         where hd.trangthai != 3 and isnumeric(hd.sohoadon)=1 and hd.mauhoadon = "+ query_mauhd +" and hd.kyhieu = ISNULL("+ query_kyhieu +",'-1')  "+
				"               and cast(hd.sohoadon as int) >= cast(ISNULL("+ query_sohdTU +", -1) as int) "+
				"               and cast(hd.sohoadon as int) <= cast(ISNULL("+ query_sohdDEN +", -1) as int) and hd.NGUOISUA = NV.PK_SEQ) isSd_ETC \n" +
				" FROM NHANVIEN NV  \n" +
				" WHERE NV.pk_seq = '" + userId + "' \n";
				//System.out.println("Câu check khai báo SHD "+query);
				ResultSet rsLayDL = db.get(query);

				int check_OTC = 0;
				int check_ETC = 0;

				while(rsLayDL.next())
				{
					kyhieuhoadon= rsLayDL.getString("kyhieu");
					sohoadontu = rsLayDL.getLong("sohoadontu");
					sohoadonden = rsLayDL.getLong("sohoadonden");
					ngayhoadon = rsLayDL.getString("ngayhoadon");
					if(ngayhoadon.trim().length() <= 0)  ngayhoadon = ngaydonhang;
					check_OTC = rsLayDL.getInt("isSd_OTC");
					check_ETC = rsLayDL.getInt("isSd_ETC");
				}
				rsLayDL.close();

				if(kyhieuhoadon.equals("-1") || sohoadontu == -1 || sohoadonden == -1 )
				{
					msg = "Vui lòng thiết lập khoảng Số hóa đơn cho USER ";
					return new String[]{ msg };
				}

				if(check_OTC <= 0 && check_ETC <= 0)
				{
					chuoi = ("000000" + sohoadontu);
					chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());
				}
				else
				{
					// LAY SOIN MAX TRONG OTC && ETC
					query= " SELECT  \n"+
					"       (select max(cast(sohoadon as float)) as soin_max  "+
					"        from HOADON hd               "+
					"        where hd.trangthai != 3 and isnumeric(hd.sohoadon) = 1 and hd.mauhoadon = "+ query_mauhd +" and hd.kyhieu = ISNULL("+ query_kyhieu +",'-1')  "+
					"              and cast(hd.sohoadon as int) >= cast(ISNULL("+ query_sohdTU +", -1) as int) "+
					"              and cast(hd.sohoadon as int) <= cast(ISNULL("+ query_sohdDEN +", -1) as int) and hd.nguoisua = NV.PK_SEQ ) soinMAX_OTC, \n" +
					"       (select max(cast(sohoadon as float)) as soin_max "+
					"        from ERP_HOADONNPP hd               "+
					"        where hd.trangthai != 3 and isnumeric(hd.sohoadon) = 1 and hd.mauhoadon = "+ query_mauhd +" and hd.kyhieu = ISNULL("+ query_kyhieu +",'-1')  "+
					"              and cast(hd.sohoadon as int) >= cast(ISNULL("+ query_sohdTU +", -1) as int) "+
					"              and cast(hd.sohoadon as int) <= cast(ISNULL("+ query_sohdDEN +", -1) as int) and hd.nguoisua = NV.PK_SEQ) soinMAX_ETC  \n" +
					" FROM NHANVIEN NV  \n" +
					" WHERE NV.pk_seq = '" + userId + "' \n";

					//System.out.println("Câu lấy SHD Max: "+query);
					long soinMAX_OTC = 0;
					long soinMAX_ETC = 0;

					ResultSet laySOIN = db.get(query);	
					while(laySOIN.next())
					{
						soinMAX_OTC = laySOIN.getLong("soinMAX_OTC");
						soinMAX_ETC = laySOIN.getLong("soinMAX_ETC");
					}
					laySOIN.close();

					if( soinMAX_OTC > soinMAX_ETC ) 
					{
						chuoi = ("000000" + (soinMAX_OTC > 0 ? (soinMAX_OTC + 1) : "1" ));
					}
					else
					{
						chuoi = ("000000" + (soinMAX_ETC > 0 ? (soinMAX_ETC + 1) : "1"));
					}

					chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length()).trim();
				}


				if(Integer.parseInt(chuoi) > sohoadonden )
				{ 
					//CHECK THEM NEU TRONG KHOANG HOA DON CUA USER DO VAN CON SHD THI TU DONG LAY SO DO
					query= " SELECT  \n"+
					"      (select  max(cast(hd.sohoadon as float)) as soin_max   \n"+
					"       from HOADON hd                                     \n"+
					"       where hd.trangthai != 3 and isnumeric(hd.sohoadon) = 1 and hd.mauhoadon = "+ query_mauhd +" and hd.kyhieu = ISNULL("+ query_kyhieu +",'-1')  \n"+
					"             and cast(hd.sohoadon as float) >= cast(ISNULL("+ query_sohdTU +", -1) as float)                                 \n"+
					"             and cast(hd.sohoadon as float) <= cast(ISNULL("+ query_sohdDEN +", -1) as float)  and hd.nguoisua = NV.PK_SEQ                               \n"+
					"       having max(cast(hd.sohoadon as float)) != ( select  MAX(cast(SOHOADON as float)) as SOIN_MAX  from HOADON where trangthai!= 3 and  kyhieu = ISNULL("+ query_kyhieu +",'-1')  and nguoisua= NV.PK_SEQ) \n"+
					"       ) soinMAX_OTC 										  \n"+								  
					" FROM NHANVIEN NV   \n" +
					" WHERE NV.pk_seq = '" + userId + "' \n";

					//System.out.println("Câu lấy HD không dùng "+query);
					ResultSet SoMAX_HD = db.get(query);
					String soinmax = "";
					while(SoMAX_HD.next())
					{
						soinmax = SoMAX_HD.getString("soinMAX_OTC")== null ? "" : SoMAX_HD.getString("soinMAX_OTC") ;
						chuoi = ("000000" + (SoMAX_HD.getLong("soinMAX_OTC")));

					} 
					SoMAX_HD.close();

					chuoi = chuoi.substring(chuoi.length() - 7, chuoi.length());

					if(soinmax.trim().length() <= 0 )
					{
						msg = "Số hóa đơn tiếp theo  đã vượt quá Số hóa đơn đến (" + sohoadonden + ")  trong dữ liệu nền. Vui lòng vào dữ liệu nền khai báo lại ! ";
						return new String[]{ msg };
					}
				}

				sohoadon = chuoi ;

				// KIEM TRA LAI SO HOA DON MOI TAO CO TRUNG VS SO HOA DON NAO HIEN TAI TRONG HD O & E 
				query = " select (select count(*) from HOADON where (SOHOADON = '" + sohoadon + "' ) and kyhieu = '" + kyhieuhoadon + "' and trangthai != '3' and npp_fk = '" + nppId + "' and sohoadon != 'NA' and mauhoadon = "+ query_mauhd +") as KtraOTC, " +
				"        (select count(*) from ERP_HOADONNPP where (SOHOADON = '" + sohoadon + "' ) and kyhieu = '" + kyhieuhoadon + "' and trangthai != '3' and npp_fk = '" + nppId + "' and sohoadon != 'NA' and mauhoadon = "+ query_mauhd +") as KtraETC " +
				" from NHANVIEN where pk_seq = '" + userId + "' ";

				//System.out.println("Câu kiểm tra lại SHD: "+query);
				ResultSet RsRs = db.get(query);
				int KT_OTC = 0;
				int KT_ETC = 0;
				while(RsRs.next())
				{
					KT_OTC = RsRs.getInt("KtraOTC") ;
					KT_ETC = RsRs.getInt("KtraETC") ;
				}
				RsRs.close();

				if(KT_OTC > 0 || KT_ETC > 0) // CÓ HÓA ĐƠN (CỦA USER KHÁC) CÓ SỐ HD TRÙNG VS SỐ HÓA ĐƠN MỚI TẠO
				{
					msg = "Số hóa đơn tiếp theo đã trùng với số hóa đơn trong Hóa Đơn OTC/ETC ! ";
					return new String[]{ msg };
				}
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return new String[]{ ex.getMessage() };
		}




		return new String[]{ kyhieuhoadon, sohoadon, ngayhoadon, mau };
	}
	public String[] LayThongTinSHD1( geso.dms.distributor.db.sql.dbutils db, String userId, String nppId, String ngaydonhang, String khachhangID )
	{
		String kyhieuhoadon = "";
		String sohoadon = "";
		String ngayhoadon = "";
		String mau = "1";
		String msg = "";

		sohoadon = "NA";
		kyhieuhoadon = "NA";

		String chuoi = "";
		long sohoadontu = 0;
		long sohoadonden = 0;

		// LAY TT KHAI BAO SO HD TRONG DLN
		String query= " SELECT case when isnull(kyhieu,'')='' then kyhieu2 else kyhieu end as kyhieu \n"+
		" FROM NHANVIEN NV  \n" +
		" WHERE NV.pk_seq = '" + userId + "' \n";
		//System.out.println("Câu check khai báo SHD "+query);
		ResultSet rsLayDL = db.get(query);
		try {
			while(rsLayDL.next())
			{
				kyhieuhoadon= rsLayDL.getString("kyhieu");
				ngayhoadon = rsLayDL.getString("ngayhoadon");
				if(ngayhoadon.trim().length() <= 0)  ngayhoadon = ngaydonhang;

			}

			rsLayDL.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return new String[]{ kyhieuhoadon, sohoadon, ngayhoadon, mau };
	}


	public String Update_KhoTT(  String ngaychungtu, String nghiepvu, Idbutils db, String khott_fk, String spId, String solo, String ngayhethan, String ngaynhapkho,
			double soluong, double booked, double available ) 
	{
		try
		{
			int flag=0;
			String querylog = "";
			String query =  "  select sanpham_fk, available, soluong, sp.ma + ' ' + sp.ten as ten   " +
			"  from ERP_KHOTT_SP_CHITIET kho " +
			"  inner join sanpham sp  on kho.sanpham_fk = sp.pk_seq " + 
			" where KHOTT_FK = " + khott_fk + " and sanpham_fk = " + spId + " and solo = '" + solo + "' and ngayhethan = '" + ngayhethan + "' and ngaynhapkho = '" + ngaynhapkho + "' ";

			//System.out.println("[UTILITY KHO : QUERY CHECK KHO]" + query);
			double available_ton=0;
			double giaton = 0;
			double soluongton=0;

			ResultSet rsCheck = db.get(query);
			rsCheck.next();
			{
				soluongton=rsCheck.getDouble("soluong");
				available_ton=rsCheck.getDouble("available");

				if(available < 0 && available_ton < (-1)*available )
				{
					return "Số lượng tồn hiện tại trong kho của sản phẩm : " + rsCheck.getString("ten") + "  [" + available_ton + "], số lô [" + solo + "], ngày hết hạn [" + ngayhethan + "], ngày nhập kho [" + ngaynhapkho + "], không đủ để trừ kho, vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này " ;
				}

				if(soluong < 0 && soluongton < (-1) * soluong )
				{
					return "Số lượng tồn  trong kho của sản phẩm : " + rsCheck.getString("ten") + "  [" + soluongton + "] không đủ để trừ kho, vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này " ;
				}

				/*querylog = "insert into log_kho (nghiepvu,sanpham_fk,kbh_fk,kho_fk,npp_fk,soluong,book,avai,ngaychungtu,soluongNV,bookNv,avaiNV)"+
						   "select N'"+nghiepvu+"',sanpham_fk,kbh_fk,kho_fk,npp_fk,SOLUONG,BOOKED,AVAILABLE,'"+ngaychungtu+"',round(" + soluong + ",1),round("+booked+",1),round(" + available + ",1) from nhapp_kho where  npp_fk='"+npp_fk+"' and kbh_fk='"+kbh_fk+"' and kho_fk='"+khott_fk+"' and sanpham_fk='"+spId+"'  ";*/

				query = " Update ERP_KHOTT_SP_CHITIET set booked = round(isnull(booked,0),1) + round(" + booked + ", 1), soluong = round(ISNULL(soluong,0),1) + round(" + soluong + ",1), " +
				" 	AVAILABLE = round(ISNULL(AVAILABLE,0),1) + round(" + available + ", 1)  "+
				"  where KHOTT_FK = " + khott_fk + " and sanpham_fk = " + spId + " and solo = '" + solo + "' and ngayhethan = '" + ngayhethan + "' and ngaynhapkho = '" + ngaynhapkho + "' ";
				//System.out.println("update kho chi tiet "+query);

				int resultInt = db.updateReturnInt(query);
				if(resultInt != 1)
				{
					return  " Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
				}

				query = " Update ERP_KHOTT_SANPHAM set booked = round(isnull(booked,0),1) + round(" + booked + ", 1), soluong = round(ISNULL(soluong,0),1) + round(" + soluong + ",1), " +
				" 	AVAILABLE = round(ISNULL(AVAILABLE,0),1) + round(" + available + ", 1)  "+
				"  where KHOTT_FK = " + khott_fk + " and sanpham_fk = " + spId + "  ";
				//System.out.println("update kho tong "+query);

				resultInt = db.updateReturnInt(query);
				if(resultInt != 1)
				{
					return  " Không thể cập nhật ERP_KHOTT_SANPHAM " + query;

				}
			}
			/*else
			{
				query=  " INSERT INTO NHAPP_KHO ( KHO_FK,SANPHAM_FK,NPP_FK,KBH_FK,GIAMUA,SOLUONG,BOOKED,AVAILABLE ) VALUES  " +
						" ("+khott_fk+","+ spId+","+npp_fk+","+kbh_fk+","+dongia+",round("+soluong+",1),round("+booked+",1),round("+available+",1))";
				flag=1;
				querylog="insert into log_kho (nghiepvu,sanpham_fk,kbh_fk,kho_fk,npp_fk,soluong,book,avai,ngaychungtu,soluongNV,bookNv,avaiNV)"+
						"select N'"+nghiepvu+"',sanpham_fk,kbh_fk,kho_fk,npp_fk,SOLUONG,BOOKED,AVAILABLE,'"+ngaychungtu+"',round(" + soluong + ",1),round("+booked+",1),round(" + available + ",1) from nhapp_kho where npp_fk='"+npp_fk+"' and kbh_fk='"+kbh_fk+"' and kho_fk='"+khott_fk+"' and sanpham_fk='"+spId+"'  ";

				if(available < 0 && available_ton < (-1)*available ){
					return "Số lượng tồn hiện tại trong kho của sản phẩm : "+rsCheck.getString("ten") + "  ["+available_ton+"] không đủ để trừ kho,vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này " ;
				}

				if(soluong < 0 && soluongton <(-1)*soluong ){
					return "Số lượng tồn  trong kho của sản phẩm : "+rsCheck.getString("ten") + "  ["+soluongton+"] không đủ để trừ kho,vui lòng kiểm tra lại xuất nhập tồn của sản phẩm này " ;
				}

			}
			 */

			rsCheck.close();

			/*if(flag==0)
			{
				int resultInt = db.updateReturnInt(querylog);
				if(resultInt != 1)
				{
					return  " Không thể cập nhật log_kho " + querylog;

				}
			}*/

			/*if(flag==1)
			{
			  resultInt = db.updateReturnInt(querylog);
				if(resultInt != 1)
				{
					return  " Không thể cập nhật log_kho " + querylog;

				}
			}*/
		}
		catch(Exception er)
		{
			er.printStackTrace();
			return  "không thể thực hiện cập nhật kho  Util.Nhap_Kho_Sp : "+er.getMessage();
		}

		return "";
	}

	public String Update_TaiKhoan_FULL (geso.dms.distributor.db.sql.dbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
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

			//System.out.println("1.Cap nhat tai khoan NO: " + query);
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

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
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

			//System.out.println("2.Cap nhat tai khoan NO: " + query);
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

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
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

			//System.out.println("1.Cap nhat tai khoan NO: " + query);
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

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
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

			//System.out.println("2.Cap nhat tai khoan NO: " + query);
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

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
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

			//System.out.println("1.Cap nhat tai khoan NO: " + query);
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

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
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

			//System.out.println("2.Cap nhat tai khoan NO: " + query);
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

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		return msg;

	}

	public String Update_TaiKhoan_Diengiai (Idbutils db, String thang, String nam, String ngaychungtu, String ngayghinhan, String loaichungtu, String sochungtu, String taikhoanNO_fk, String taikhoanCO_fk, String NOIDUNGNHAPXUAT_FK, String NO, String CO, 
			String DOITUONG_NO, String MADOITUONG_NO, String DOITUONG_CO, String MADOITUONG_CO, String LOAIDOITUONG, String SOLUONG, String DONGIA, String TIENTEGOC_FK, String DONGIANT, String TIGIA_FK, String TONGGIATRI, String TONGGIATRINT, String khoanmuc,String Diengiai)
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


		//GHI CO
		if(Float.parseFloat(_CO) != 0) 
		{
			query = "select count(*) as sodong from ERP_TAIKHOAN_NOCO " +
			"where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' " +
			"and thang = '" + thang + "' and nam = '" + nam + "'";

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
				query = " update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND + " + _thanhtienViet + ", " +
				" GIATRINGUYENTE = GIATRINGUYENTE + "  + _thanhtienNT + ", " +
				" GIATRICONGUYENTE = GIATRICONGUYENTE + "  + _thanhtienNT + 
				" where taikhoankt_fk = '" + taikhoanCO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = " insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				" select '" + taikhoanCO_fk + "', " + _thanhtienViet + ", '0', '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", " + _thanhtienNT + ", 0,'" + thang + "', '" + nam + "' ";
			}

			//System.out.println("1.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "1.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}


			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC,DIENGIAI) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanCO_fk + "', '" + taikhoanNO_fk + "', " + _ndnhapxuat_fk + ", '0', " + _CO + ", " +
			" N'" + DOITUONG_CO + "', N'" + MADOITUONG_CO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "',N'"+Diengiai+"' ) ";

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		//GHI NO
		if(Float.parseFloat(_NO) != 0) 
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
				" where taikhoankt_fk = '" + taikhoanNO_fk + "' and nguyente_fk = '" + TIENTEGOC_FK + "' and thang = '" + thang + "' and nam = '" + nam + "'";
			}
			else
			{
				query = "insert ERP_TAIKHOAN_NOCO(TAIKHOANKT_FK, GIATRICOVND, GIATRINOVND, NGUYENTE_FK, GIATRINGUYENTE, GIATRICONGUYENTE, GIATRINONGUYENTE, THANG, NAM) " +
				"select '" + taikhoanNO_fk + "', '0', " + _thanhtienViet + ", '" + TIENTEGOC_FK + "', " + _thanhtienNT + ", 0, " + _thanhtienNT + ", '" + thang + "', '" + nam + "' ";
			}

			//System.out.println("2.Cap nhat tai khoan NO: " + query);
			if(!db.update(query))
			{
				msg = "2.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}


			//GHI PHAT SINH VA DOI UNG CHO TAO KHOAN CO
			query = "insert ERP_PHATSINHKETOAN ( ngaychungtu, ngayghinhan, loaichungtu, sochungtu, taikhoan_fk, taikhoandoiung_fk, NOIDUNGNHAPXUAT_FK, NO, CO, " +
			"  DOITUONG,  MADOITUONG, LOAIDOITUONG, SOLUONG, DONGIA, TIENTEGOC_FK, DONGIANT, TIGIA_FK, TONGGIATRI, TONGGIATRINT, KHOANMUC,diengiai) " +
			"values ( '" + ngaychungtu + "', '" + ngayghinhan + "', N'" + loaichungtu + "', " + _sochungtu + ", '" + taikhoanNO_fk + "', '" + taikhoanCO_fk + "', " + _ndnhapxuat_fk + ", " + _NO + ", '0', " +
			" N'" + DOITUONG_NO + "', N'" + MADOITUONG_NO + "', '" + LOAIDOITUONG + "', " + _soluong + ", " + _dongia + ", '" + TIENTEGOC_FK + "', " + _dongiaNT + ", '" + TIGIA_FK + "', " + _thanhtienViet + ", " + _thanhtienNT + ", N'" + khoanmuc + "',N'"+Diengiai+"' ) ";

			//System.out.println("3.Cap nhat ERP_PHATSINHKETOAN: " + query );
			if(!db.update(query))
			{
				msg = "3.Không thể cập nhật tài khoản kế toán " + query;
				return msg;
			}

		}

		return msg;
	}

	public String getLoai(String querystring){
		String loai;
		String tmp;
		if(querystring != null){
			if(querystring.contains("&")){
				tmp = querystring.split("&")[2];
				loai = tmp.split("=")[1];
			}else{
				loai = "";
			}
		}else{
			loai = "";
		}
		return loai;
	}

	public String getSoChungTu(String querystring){
		String sochungtu;
		String tmp;
		if(querystring != null){
			if(querystring.contains("&")){
				tmp = querystring.split("&")[3];
				sochungtu = tmp.split("=")[1];
			}else{
				sochungtu = "";
			}
		}else{
			sochungtu = "";
		}
		return sochungtu;
	}


	public static Double parseDouble(String s)
	{
		try{
			return Double.parseDouble(s);
		}catch (Exception e) {
			return 0.0;
		}
	}

	public static String parseDoubleStr(String s)
	{
		try{
			return Double.toString(Double.parseDouble(s.replaceAll(",", "")));
		}catch (Exception e) {
			return "0";
		}
	}


	String Check_CK_DaHuong(String hoadonId,String userId, geso.dms.distributor.db.sql.dbutils db)
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
		//System.out.println("[Check_CK_DaHuong]"+query);
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
				//System.out.println("[Check_CK_DaHuong]"+sohoadon);
			}
			rs.close();
			if(sohoadon.length()>0)
			{
				msg= "DOANH Sá»� TRONG BIÃŠN BIá»‚N BÃ™ TRá»ª CHIáº¾T KHáº¤U THÃ�NG Sáº¼ THAY Ä�á»”I Náº¾U Báº N XÃ“A/ Há»¦Y HÃ“A Ä�Æ N NÃ€Y!";
				query=
					" INSERT INTO HuyHoaDon(HoaDonHuy_Fk,SoHoaDon,HoaDon_FK,GhiChu,NguoiSua) " +
					" select '"+hoadonId+"','"+sohoadon+"','"+hoadonck+"',N'"+msg+"','"+userId+"'  ";
				db.update(query);
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Lá»—i phÃ¡t sinh khi check ck thÃ¡ng Ä‘Ã£ hÆ°á»Ÿng !;";
		}
		return msg;
	}

	public void Update_SoHoaDon( String dhId, geso.dms.distributor.db.sql.dbutils db )
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

	public static DefaultTableModel getDefaultTableDB_Syns(String query ) 
	{ 
		dbutils_syn db = new dbutils_syn();
		try 
		{
			ResultSet set = db.get(query);
			DefaultTableModel   table = new DefaultTableModel();
			ResultSetMetaData metaData = set.getMetaData();
			int totalColumn = metaData.getColumnCount();
			Object[] dataRow = new Object[totalColumn];
			if(set!= null)
			{
				for(int i=1;i<=totalColumn;i++)
				{

					table.addColumn(metaData.getColumnName(i));

				}
				while(set.next())
				{
					for(int i=1;i<=totalColumn;i++)
					{
						dataRow[i-1] = set.getObject(i);
					}
					table.addRow(dataRow);
				}

			}
			set.close();
			db.shutDown();
			return table;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally
		{
			db.shutDown();
		}
	}

	public static DefaultTableModel getDefaultTableDB(Idbutils db,String query ) 
	{ 

		try 
		{
			//System.out.println( " querry getDefaultTableDB = " + query);
			ResultSet set = db.get(query);
			DefaultTableModel   table = new DefaultTableModel();
			ResultSetMetaData metaData = set.getMetaData();
			int totalColumn = metaData.getColumnCount();
			Object[] dataRow = new Object[totalColumn];
			if(set!= null)
			{
				for(int i=1;i<=totalColumn;i++)
				{

					table.addColumn(metaData.getColumnName(i));

				}
				while(set.next())
				{
					for(int i=1;i<=totalColumn;i++)
					{
						dataRow[i-1] = set.getObject(i);
					}
					table.addRow(dataRow);
				}

			}
			set.close();
			return table;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally
		{

		}
	}

	public static String CheckVat(Idbutils db ,String tableName,String columnVat, String columnId, String Id )
	{
		return "";
		/*
		 * try { String query = " select count(distinct "+columnVat+")kq from " +
		 * tableName + " where "+columnId+" = "+Id+"  "; ResultSet rs = db.get(query);
		 * if(rs.next()) { if( rs.getInt("kq") > 1 ) { rs.close(); return
		 * "Lỗi tồn tại 2 thuế suất trong đơn hàng"; } else { rs.close(); return ""; } }
		 * else { rs.close(); return "Không lấy được thông tin thuế xuất ĐH "; }
		 * 
		 * }catch(Exception e) { return
		 * " Lỗi ngoại lệ khi kiểm tra vat ("+e.getMessage()+") "; }
		 */
	}


	public static String CheckVat_Loai2(Idbutils db ,String tableName,String columnVat, String columnId, String Id,String columnSoLuong )
	{
		try
		{
			String query =  " select count(distinct "+columnVat+")kq from " + tableName +
			" where "+columnId+" = "+Id+" and "+columnSoLuong+" > 0 ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if( rs.getInt("kq") > 1 )
				{
					rs.close();
					return "Lỗi tồn tại 2 thuế suất trong đơn hàng";
				}
				else
				{
					rs.close();
					return "";
				}
			}
			else
			{
				rs.close();
				return "Không lấy được thông tin thuế xuất ĐH ";
			}

		}catch(Exception e)
		{
			return " Lỗi ngoại lệ khi kiểm tra vat ("+e.getMessage()+") ";
		}
	}


	public static String getIdNhapp(Idbutils db,String userId) 
	{

		String nppId = "";
		String sql =
			"			select nv.dangnhap,npp.LoaiNPP, npp.khosap, npp.pk_seq,npp.sitecode,npp.ten,npp.tructhuoc_fk  "+ 
			"			from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=sitecode   "+
			"			where nv.pk_seq='"+userId+"' and nv.trangthai='1'  and nv.PHANLOAI=1 and npp.isKHACHHANG = '0'  "+ 
			"			union all  "+
			"			select nv.dangnhap,npp.LoaiNPP, npp.khosap, npp.pk_seq,npp.sitecode,gs.ten,npp.tructhuoc_fk  "+ 
			"			from nhanvien nv inner join GIAMSATBANHANG gs on nv.GSBH_FK=gs.PK_SEQ  "+
			"				inner join NHAPHANPHOI npp on npp.SITECODE=nv.CONVSITECODE  "+
			"			where nv.pk_seq='"+userId+"' and nv.trangthai='1'  and nv.PHANLOAI=2 and gs.TRANGTHAI=1 and npp.isKHACHHANG = '0'  "+
			"			and npp.TRANGTHAI=1 ";

		//System.out.println("____ID_"+sql);

		ResultSet rs = db.get(sql);
		try
		{
			while(rs.next())
			{
				nppId=rs.getString("pk_seq");
			}
			if(rs!=null)rs.close();
		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
		return nppId;
	}

	public static String  UpdateChungTuSoNet(String tableInsert,String _NPP_FK,String _id,String _ngaychungtu, int sonetInt, String tableName,String prefixName  )
	{
		String query = "\n insert "+tableInsert+"(NPP_FK,Id_FK,nam,thang,number,sochungtu,tableName) " +
		"\n select "+_NPP_FK+","+_id+",year('"+_ngaychungtu+"'),month('"+_ngaychungtu+"'),"+sonetInt+", '"+prefixName+"' + dbo.[PlusZero]("+sonetInt+",5),'"+tableName+"'";
		//System.out.println("Utility.UpdateChungTuSoNet3387:"+query);
		return query;

	}

	public static int getChungTuSonet(Idbutils db,String userId,String npp_fk ,String tableCheck,String thang, String Nam, String Id,String tableName )
	{
		try
		{
			if(npp_fk == null)
			{
				npp_fk = Utility.getIdNhapp( db, userId);
				if(npp_fk.trim().length() <= 0)
					npp_fk = "1";
			}
			int so = -1;
			String query =   "\n ;WITH Missing (missnum, maxid)  " + 
			"\n AS  " + 
			"\n (  " + 
			"\n  	SELECT 1 AS missnum, (select max(number) from "+tableCheck+" where npp_fk = "+npp_fk+" and thang = "+thang+" and Nam="+Nam+" )  " + 
			"\n  	UNION ALL  " + 
			"\n  	SELECT missnum + 1, maxid FROM Missing  " + 
			"\n  	WHERE missnum < maxid  " + 
			"\n )  " + 
			"\n SELECT top 1 missnum  " + 
			"\n FROM Missing  " + 
			"\n LEFT OUTER JOIN  "+tableCheck+"  tt on tt.number = Missing.missnum and  npp_fk = "+npp_fk+" and  thang = "+thang+" and Nam="+Nam+" " + 
			"\n WHERE tt.number is NULL  " + 
			"\n order by missnum  " + 
			"\n OPTION (MAXRECURSION 0); " ;
			//System.out.println("Utility center 3370: getSoChungTu: " + query);
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if( rs.getString("missnum") != null )
				{

					so = rs.getInt("missnum"); // có trống thì return luôn
					rs.close();
					return so;
				}
			}
			query =" select isnull(max(number),0) + 1 num from "+tableCheck+" where npp_fk = "+npp_fk+" and thang = "+thang+" and Nam="+Nam;
			//System.out.println("Utility center 3383: getSoChungTuMax: " + query);
			rs = db.get(query);
			if(rs.next())
			{
				if( rs.getString("num") != null )
				{
					so = rs.getInt("num"); // trả về số chứng từ
					rs.close();
					return so;
				}
			}
			rs.close();
			return -1;
		}catch(Exception e)
		{
			return -1;
		}
	}

	public static boolean KiemTraNgayChungTuSoNet(Idbutils db,String userId,String npp_fk ,String tableCheck,String ngaychungtuNew, String Id,String tableId )
	{
		if(1==1 ) return true;
		try
		{

			if(npp_fk == null)
			{
				npp_fk = Utility.getIdNhapp( db, userId);
				if(npp_fk.trim().length() <= 0)
					npp_fk = "1";
			}
			String query =" select count(*)num  from "+tableCheck+" where npp_fk = "+npp_fk+" and tableName = '"+tableId+"' and thang =month('"+ngaychungtuNew+"') and Nam= year('"+ngaychungtuNew+"')";
			//System.out.println("Utility center 3383: ChecDoiNgayChungTu: " + query);
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				int num = 0;
				if( rs.getString("num") != null )
				{
					num = rs.getInt("num"); // trả về số chứng từ


				}
				rs.close();
				return num > 0 ; 

			}
			rs.close();
			return false;
		}catch(Exception e)
		{
			return false;
		}
	}

	//ks

	public String Check_Huy_NghiepVu_KhoaSo(String table, String id, String column, geso.dms.distributor.db.sql.dbutils db)
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
		//System.out.println("______"+query);
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

	public String Check_Huy_NghiepVu_KhoaSo(String table,String id,String column,String nppId, geso.dms.distributor.db.sql.dbutils db)
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

		//System.out.println("Check_Huy_NghiepVu_KhoaSo: " + query);
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
		//System.out.println("______"+query);
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

	public static boolean ktDaChonTrongRs(String value, String chuoi,String splitString)
	{
		String [] arr = chuoi.split(splitString);
		for(int i = 0;i < arr.length ; i ++ )
		{
			if(value.equals(arr[i]))
				return true;
		}
		return false;
	}
	public String getDDKD_GSBH(String userId){
		String ddkdIds = "";
		String gsbh_fk = "0";
		dbutils db = new dbutils();
		String sql = "select ISNULL(GSBH_FK,0) AS GSBH_FK from NHANVIEN where PHANLOAI = 2 and LOAI = 3 AND PK_SEQ = '"+userId+"' and trangthai='1'";	
		ResultSet rs = db.get(sql);
		try
		{
			if(rs.next())
			{
				gsbh_fk = rs.getString("GSBH_FK");
			}
			if(rs!=null)rs.close();
			if(!gsbh_fk.equals("0"))
				ddkdIds = " ( select DDKD_FK from DDKD_GSBH where GSBH_FK ="+gsbh_fk+"  )";
			/*ddkdIds = "( " +
				"	select PK_SEQ from DAIDIENKINHDOANH d " +
				"	where d.diaban_fk in ( " +
				"		select PK_SEQ from DIABAN where KHUVUC_FK = ( " +
				"					select KHUVUC_FK from GIAMSATBANHANG where PK_SEQ = '"+gsbh_fk+"' " +
				"			) " +
				"	) " +
				")";*/


		}
		catch(Exception er)
		{
			er.printStackTrace();
		}
		finally
		{
			db.shutDown();
		}
		return ddkdIds;
	}

	//Thêm từ CVI
	public static String PhanQuyenNppTheoDDKD(String ddkdId)
	{
		return " select NPP_FK from DaiDienKinhDoanh_NPP where DDKD_FK in ("+ddkdId+" )  ";
	}

	public static String PhanQuyenNppTheoGSBH(String gsbhId)
	{
		return  " select NPP_FK from DaiDienKinhDoanh_NPP where DDKD_FK in ( select DDKD_FK from DDKD_GSBH where GSBH_FK = "+gsbhId+" )  ";
	}
	public static String PhanQuyenNppTheoKV(String kvId)
	{
		return  " select NPP_FK from DaiDienKinhDoanh_NPP where DDKD_FK in ( select DDKD_FK from DDKD_GSBH where GSBH_FK in (select gsbh_FK from gsbh_khuvuc where khuvuc_fk ="+kvId+" ) )  ";
	}
	public static String PhanQuyenNppTheoVung(String vId)
	{
		return  " select NPP_FK from DaiDienKinhDoanh_NPP where DDKD_FK in ( select DDKD_FK from DDKD_GSBH where GSBH_FK in (select gsbh_FK from gsbh_khuvuc where khuvuc_fk in (select pk_seq from khuvuc where vung_fk = "+vId+") ) )  ";
	}

	public static String PhanQuyenDDKD(String userId)
	{
		return 	 		 "\n select PK_SEQ   " + 
		"\n from DAIDIENKINHDOANH yddkd  " + 
		"\n where exists   " + 
		"\n (  " + 
		"\n 	select 1 from nhanvien xnv where xnv.pk_seq =  " + userId + 
		"\n 		and (  	   (  isnull(xnv.loai,0) in (0,5) and  exists (select 1 from DaiDienKinhDoanh_NPP  where  ddkd_fk = yddkd.pk_seq and NPP_FK in ( select npp_fk from phamvihoatdong where nhanvien_fk = xnv.pk_seq )   )        )	 	   " + 
		"\n 				or (  isnull(xnv.loai,0) = 3 and  exists (select 1 from ddkd_gsbh where gsbh_fk = xnv.gsbh_fk and ddkd_fk = yddkd.pk_seq   )        )	  " + 
		"\n 				or (  isnull(xnv.loai,0) = 2 and  exists (select 1 from ddkd_gsbh where gsbh_fk in (select pk_seq from GIAMSATBANHANG where ASM_FK  = xnv.ASM_FK ) and ddkd_fk = yddkd.pk_seq    )        )	    " + 
		"\n 				or (  isnull(xnv.loai,0) = 1 and  exists (select 1 from ddkd_gsbh where gsbh_fk in (select pk_seq from GIAMSATBANHANG where ASM_FK in (select pk_seq from asm where bm_fk  = xnv.BM_FK) ) and ddkd_fk = yddkd.pk_seq    )        )	  " + 
		"\n 		)  " + 
		"\n 		  " + 
		"\n )  " ;
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

	public static void main(String[] args) {

	}
	public static String rollback_throw_exception(Idbutils db)
	{
		try {
			db.getConnection().rollback();
			db.getConnection().setAutoCommit(true);
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception:"+ e.getMessage();
		}
	}
	public static String rollback_and_shutdown(Idbutils db)
	{
		try {
			db.getConnection().rollback();
			db.shutDown();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception:"+ e.getMessage();
		}
	}

	public static String commit_and_shutdown(Idbutils db)
	{
		try {
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			return "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Exception:"+ e.getMessage();
		}
	}
	public static Integer parseInt(String s)
	{
		try{
			return Integer.parseInt(s);
		}catch (Exception e) {
			return 0;
		}
	}

	public static String getValueFromClient(String key ,String contentType,MultipartRequest multi,HttpServletRequest request)
	{
		String kq = "";
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			kq = multi.getParameter(key);
		else
			kq = request.getParameter(key);
		return kq;

	}
	public static String [] getArrValueFromClient(String key ,String contentType,MultipartRequest multi,HttpServletRequest request)
	{
		String kq[] = null  ;
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			kq = multi.getParameterValues(key);
		else
			kq = request.getParameterValues(key);
		return kq;
	}


	public static String sendPost( String url ,String urlParameters ) throws Exception {



		byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setDoOutput(true);
		//con.setRequestProperty ("Authorization", "ALC YWxjX2FwaWF1dGhvcml0eTp0cnVvbmdkbV9hcGlhdXRob3JpdHk=");
		con.setRequestMethod( "POST" );
		con.setRequestProperty("User-Agent", "Java client");
		con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty( "charset", "utf-8");
		con.setRequestProperty("Content-Length", Integer.toString(postData.length));

		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);


		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream(), "UTF-8"));
		bw.write(urlParameters);
		bw.flush();
		bw.close();

		StringBuilder content;
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8" ));
		String line;
		content = new StringBuilder();

		while ((line = in.readLine()) != null) {
			content.append(line);
			content.append(System.lineSeparator());
		}
		String kq = content.toString();        
		// kq = new String( kq.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);        
		return kq;//content.toString();

	}
	public static String SECRET_KEY = "GESO1234";	
	public static String dongMa_ver1_noreplace(String ma) throws  NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

		SecretKeySpec skeySpec = new SecretKeySpec(GlobalValue.SECRET_KEY.getBytes(), "DES");

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		byte[] byteEncrypted = cipher.doFinal(ma.getBytes());
		String encrypted =  DatatypeConverter.printBase64Binary(byteEncrypted);
		return   replaceSupser(encrypted);
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
			e.printStackTrace();
		}
		return in;
	}

	public static String giaiMa_ver1_noreplace(String ma) throws   NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException  {


		SecretKeySpec skeySpec = new SecretKeySpec(GlobalValue.SECRET_KEY.getBytes(), "DES");

		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		byte[] byteDecrypted = cipher.doFinal(DatatypeConverter.parseBase64Binary(ma));
		String decrypted = new String(byteDecrypted);

		return decrypted.replaceAll("\\s+", "");
	}
	public static String dongMa(String ma) {

		try {
			return dongMa_ver1_noreplace(ma);
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		}
		return "";

	}

	public static String giaiMa(String ma) {
		try {
			return giaiMa_ver1_noreplace(ma);
		} catch (InvalidKeyException e) {

			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		} catch (NoSuchPaddingException e) {

			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {

			e.printStackTrace();
		} catch (BadPaddingException e) {

			e.printStackTrace();
		} 
		return "";
	}
	
	public static String dongMa_json(String ma) {

		
		return Encryption_T.encrypt(ma);
	}

	public static String giaiMa_json(String ma) {
	
		return Encryption_T.decrypt(ma);
	}
	public static int getNamHienTai()
	{
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	public static String getParameterFromSearch(String querystring, String action){

		if (querystring != null){
			String []tmp = querystring.split("&");
			if(tmp != null){
				for(String t : tmp){
					if(t.contains(action)){
						if(t.split("=").length < 2)
							return "";
						return t.split("=")[1];
					}
				}
			}
		}
		return "";
	}

	
	public static String GLanguage(String key, String languageId)
	{
		return ChuyenNgu.get(key, languageId);
	}	
	public static String GLanguage(String key, String languageId, Jedis j)
	{
		return ChuyenNgu.get(key, languageId);
	}	
	
	public static String GLanguage(String key, HttpSession session)
	{	
		return ChuyenNgu.get(key, session);
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
			try
			{
				j.disconnect();
				j.close();			
				j = null;
			}
			catch (Exception e) {
			 System.out.println(" xxx" + e.toString());
			}
		}
	}
	public static int KiemTraTransaction(String tableName,String columnName, String id, Idbutils db) throws Exception
	{
		String query = " select isnull(dangcapnhat,0) dangcapnhat from "+tableName+" where "+columnName+" = "+id;
		ResultSet rsCheck = db.get(query);
		rsCheck.next();
		String dangcapnhat = rsCheck.getString("dangcapnhat");
		rsCheck.close();

		query = "\n update "+tableName+" set dangcapnhat =  isnull(dangcapnhat,0) + 1 " +
		"\n where "+columnName+" = "+id+" and isnull(dangcapnhat,0) = " + dangcapnhat;

		return db.updateReturnInt(query);
	}
	public static int Check_Count_Query(Idbutils db,String query ) throws SQLException
	{
		//thow exxception truong hop query sai
		ResultSet rs = db.get(query);
		rs.next();
		int sd = rs.getInt(1);
		rs.close();
		return sd;
	}
	public static String Get_FirstString_Query(String query ) 
	{
		dbutils db = new dbutils();
		try
		{
			String kq= Get_FirstString_Query( db, query );
			db.shutDown(); 
			return kq;
		}
		catch (Exception e) {
			db.shutDown(); 
			return "";
		}
		
	}
	public static String Get_FirstString_Query(Idbutils db,String query ) throws SQLException
	{
		//thow exxception truong hop query sai
		ResultSet rs = db.get(query);
		rs.next();
		String sd = rs.getString(1);
		rs.close();
		return sd;
	}
	
	public static String getDateString(String datestr)
	{
		//SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // tren file dang de text '2019-12-31
				try {
					Date varDate=dateFormat.parse(datestr);
					dateFormat=new SimpleDateFormat("yyyy-MM-dd");
					return dateFormat.format(varDate);
				}catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return "";
				}		
	}
	
	
}