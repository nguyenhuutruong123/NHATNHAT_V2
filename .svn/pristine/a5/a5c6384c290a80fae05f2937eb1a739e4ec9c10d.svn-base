package geso.dms.center.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Hashtable;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham;
import geso.dms.center.beans.routesumaryreport.IRouteSumaryReport;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTBList;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTLList;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.report.Ireport;
import geso.dms.distributor.beans.reports.imp.Reports;
import geso.dms.distributor.util.FixData;

import javax.servlet.http.HttpSession;
public class KeToan implements Serializable
{
	private static final long serialVersionUID = 1L;

	
	public static ResultSet getNghiepvuketoanRs(Idbutils db,String userId,String nppId,String tkno,String tkco)
	{
		
		String query =" select TKNO,DTNO,YTNO,TKCO,DTCO,YTCO,MA,TEN from NghiepVuKeToan where 1=1 ";
			if(tkno.trim().length() > 0)
				query +=" and TKNO in ("+tkno+")";
			if(tkco.trim().length() > 0)
				query +=" and TKCO in ("+tkco+") ";
		System.out.println("nghiep vy ke toan = " + query);
		return db.getScrol(query);
	}
	public static ResultSet getNhomHangRs(Idbutils db,String userId,String nppId)
	{
		return  db.getScrol(" select ten as ma,ten from ChungLoai ");
	}
	
	public static String getDonVi(Idbutils db,String userId,String nppId)
	{ 
		String view = "TT";
		if(nppId.trim().length() > 2)
			view =  "NPP";
		
		if(view.equals("NPP"))
		{ 
			String query  = 
				"\n select mafast,ten,diachi from KHACHHANG where NPP_FK = " + nppId +
				"\n union all "+
				"\n select mafast,ten,diachi from Nhaphanphoi where pk_seq = " + nppId +
				"\n union all "+
				"\n select mafast,ten,diachi from Nhaphanphoi where tructhuoc_fk = " + nppId ;
			return query;
		}
		else
		{
			String query  = 
				"\n select mafast,ten,diachi from KHACHHANG where NPP_FK = 1 " +
				"\n union all "+
				"\n select mafast,ten,diachi from Nhaphanphoi where pk_seq = 1" +
				"\n union all "+
				"\n select mafast,ten,diachi from Nhaphanphoi where tructhuoc_fk = 1 ";
			return query;
		}
	}
	
	
	public static Hashtable<String, ResultSet>  getNghieVuKeToanHash(Idbutils db,String userId,String nppId)
	{
		String view = "TT";
		if(nppId.trim().length() > 2)
			view = "NPP";
		Hashtable<String, ResultSet> tmp = new  Hashtable<String, ResultSet>();
		
		String query =  " select TEN as ma, DIENGIAI as ten from KHO where PK_SEQ in (select kho_fk from nhanvien_kho where nhanvien_fk = "+userId+" ) ";
		System.out.println("KHO = " + query);
		
		ResultSet	doituongRs = db.getScrol(query);
		tmp.put("KHO", doituongRs);
		
		
		
		query  = " select mafast as ma, ten  from nhaphanphoi where  pk_seq = 1 " ; // HO
		
		if(!view.equals("TT"))
				
			query += 	"\n union all select mafast as ma,  ten  from nhaphanphoi where  pk_seq = "+ nppId +"  and mafast is not null "+ // chính nó (Chi nhánh)
						"\n union all select mafast as ma,  ten  from nhaphanphoi where trangthai = 1 and tructhuoc_fk = "+ nppId +"  and mafast is not null "+// NPP trực thuộc nó 
						"\n union all select mafast as ma,  ten  from khachhang where trangthai = 1 and npp_fk =   "+ nppId ; // Khách hàng lẻ của CN
					
		else
			query += "\n union all select mafast as ma,  ten  from nhaphanphoi where trangthai = 1 and pk_seq != 1  and mafast is not null ";	// CN,NPP trực thuộc nó 
		System.out.println("Cong no = " + query);
			
		doituongRs = db.getScrol(query);
		tmp.put("CONGNO", doituongRs);
		
		
		
		if(!view.equals("TT"))
			query = "\n select mafast as ma, ten  from DaiDienKinhdoanh  where trangthai = 1 and pk_Seq in ( select ddkd_fk from daidienkinhdoanh_npp where  npp_fk =   "+ nppId +")" +
					"\n union all" +
					"\n select maFAST, TEN from GIAMSATBANHANG  where trangthai = 1 and pk_Seq in  ( select gsbh_fk  from ddkd_gsbh  where ddkd_fk in (select ddkd_fk from daidienkinhdoanh_npp where  npp_fk =   "+ nppId +")  ) ";
		else
			query = "\n select mafast as ma, ten  from DaiDienKinhdoanh  where trangthai = 1" +
					"\n union all" +
					"\n select maFAST, TEN from GIAMSATBANHANG  where trangthai = 1  ";

		System.out.println("NV = " + query);
		doituongRs = db.getScrol(query);
		tmp.put("NHANVIEN", doituongRs);
		
		query =  " select ma,ten from ChiPhiBanHang ";
		System.out.println("ChiPhiBanHang = " + query);
		doituongRs = db.getScrol(query);
		tmp.put("CPBH", doituongRs);
		
		query =  " select ma,ten from NganHang ";
		System.out.println("NganHang = " + query);
		doituongRs = db.getScrol(query);
		tmp.put("NH", doituongRs);
		
		return tmp;
		
		
	}
	

}