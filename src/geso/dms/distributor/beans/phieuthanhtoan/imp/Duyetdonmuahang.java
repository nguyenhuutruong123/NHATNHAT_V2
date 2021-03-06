package geso.dms.distributor.beans.phieuthanhtoan.imp;

import geso.dms.distributor.beans.phieuthanhtoan.IDuyetdonmuahang;
import geso.dms.center.util.Utility;
import geso.dms.center.db.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Duyetdonmuahang implements IDuyetdonmuahang {
	
	String congtyId;
	String userId;
	String ctyId;
	String nppId;
	
	String dvthId;
	ResultSet dvth;
	
	String lspId;
	String ngaymua;
	String maDMH;
	String nccId;
	String lydoxoa;
	String lydomoduyet;
	String lydosua;
	ResultSet nccList;
	ResultSet lsp;
	
	ResultSet polist;
	
	HttpServletRequest request;	
	String msg;
	dbutils db;
    Utility util;

	public Duyetdonmuahang(){
		this.userId = "";
		//this.ctyId = "100001";
		this.ctyId="";
		this.dvthId = "";
		this.lspId = "";
		this.ngaymua="";
		this.maDMH = "";
		this.nccId ="";
		this.msg = "";
		this.lydoxoa = "";
		this.lydomoduyet = "";
		this.lydosua = "";
		this.nppId = "";
		this.db = new dbutils();
		this.util=new Utility();
	}

	public void setNccList(ResultSet nccList)
	{
		this.nccList = nccList;
	}
	
	public ResultSet getNccList() 
	{
		return nccList;
	}
	
	public void setNccId(String nccId)
	{
		this.nccId = nccId;
	}
	
	public String getNccId() 
	{
		return nccId;
	}
	
	public void setMaDMH(String maDMH) 
	{
		this.maDMH = maDMH;
	}
	
	public String getMaDMH() 
	{
		return maDMH;
	}
	
	public String getUserId(){
		return this.userId;
	}
	
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getCtyId(){
		return this.ctyId;
	}
	
	public void setCtyId(String ctyId){
		this.ctyId = ctyId;
	}
	
	public String getNgaymua(){
		return this.ngaymua;
	}
	
	public void setNgaymua(String ngaymua){
		this.ngaymua = ngaymua;
	}
	
	public String getDvthId(){
		return this.dvthId;
	}
	
	public void setDvthId(String dvthId){
		this.dvthId = dvthId;
	}
	
	public String getLspId(){
		return this.lspId;
	}
	
	public void setLspId(String lspId){
		this.lspId = lspId;
	}
	
	public String getMsg(){
		return this.msg;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}

	public ResultSet getLspList(){
		return this.lsp ;
	}
	
	public void setLspList(ResultSet lsp){
		this.lsp = lsp;
	}

	public ResultSet getDvthList(){
		return this.dvth ;
	}
	
	public void setDvthList(ResultSet dvth){
		this.dvth = dvth;
	}

	public ResultSet getPoList(){
		return this.polist ;
	}
	
	public void setPoList(ResultSet polist){
		this.polist = polist;
	}

	public void setRequest(HttpServletRequest request){
		this.request = request;
	}
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
	}
	
	public void init()
	{		
		this.getNppInfo();
		String query = "";
		
		// INSERT QUY???N V??O B???NG ERP_DUYETMUAHANG_NHAP NH???NG PHI???U DNTT TR???NG TH??I = 0
		
		try{
			
			query = "DELETE ERP_DUYETMUAHANG_NHAP ";
			
			System.out.println(query);
			if (!db.update(query))
			{
				msg = "Khong the cap nhat ERP_DUYETMUAHANG_NHAP: " + query;
				db.getConnection().rollback();
			}
			
			query = "SELECT NGUOITAO, PK_SEQ MUAHANG_FK, ( SELECT distinct A.LOAICAP_FK  \n" +
					" 									   FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
					" 									   WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = MH.NGUOITAO AND A.NPP_FK = "+this.nppId+" ) LOAICAP_FK " +
					"FROM ERP_MUAHANG MH WHERE TRANGTHAI = 0 AND isnull(DACHOT, 0) = '1' AND ISDNTT = 1 AND LOAIHANGHOA_FK = 2  and TYPE = '1' and MH.NPP_FK = " +this.nppId +
					
					"UNION ALL \n" +
					
					"SELECT NGUOITAO, PK_SEQ MUAHANG_FK, ( SELECT distinct A.LOAICAP_FK  \n" +
					" 									   FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
					" 									   WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = MH.NGUOITAO  AND A.NPP_FK = "+this.nppId+" ) LOAICAP_FK " +
					"FROM ERP_MUAHANG MH WHERE TRANGTHAI = 1 AND isnull(DACHOT, 0) = '1' AND ISDNTT = 1 AND LOAIHANGHOA_FK = 2  and TYPE = '1' AND ISNULL( ISHOANTAT, 0 ) = 0 AND MH.NPP_FK = "+this.nppId ; // ???? RA PHI???U CHI, NH??NG PHI???U CHI CH??A CH???T => ????? NGH??? THANH TO??N CH??A HO??N T???T
			
			System.out.println(query);
			ResultSet rs = db.get(query);
			
			String nhanvien_fk = "";
			String muahang_fk = "";
			String loaicap_fk = "";
			int stt = 0;
			
			rs = db.get(query);
			if(rs != null)
			{
				while(rs.next())
				{
					stt = 0 ;
					nhanvien_fk = rs.getString("NGUOITAO");
					muahang_fk = rs.getString("MUAHANG_FK");
					loaicap_fk = rs.getString("LOAICAP_FK");
										
					query = " INSERT ERP_DUYETMUAHANG_NHAP ( MUAHANG_FK, TRANGTHAI, NHANVIEN_FK, LOAICAP_FK, THUTU ) "+
							" VALUES ("+muahang_fk+", 0 , "+nhanvien_fk+", "+loaicap_fk+", "+stt+") ";	
					
					if (!db.update(query))
					{
						this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
						db.getConnection().rollback();
					}
					
					stt++;
					
					for(int i = 9999; i < 10005; i++ )
					{
						query =	" SELECT B.NVQL_FK, B.LoaiCap_FK \n" +
								" FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
								" WHERE A.NPP_FK = "+this.nppId+" AND B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +" AND B.LoaiCap_FK = "+i;	
						
						System.out.println("8.quanlycap : " + query);
						
						ResultSet rs1 = db.get(query);
						if(rs1!=null)
						{
							if(rs1.next())
							{
								nhanvien_fk = rs1.getString("NVQL_FK");
								loaicap_fk = rs1.getString("LoaiCap_FK");
								
								query = " INSERT ERP_DUYETMUAHANG_NHAP ( MUAHANG_FK, TRANGTHAI, NHANVIEN_FK, LOAICAP_FK, THUTU ) "+
										" VALUES ("+muahang_fk+", 0 , "+nhanvien_fk+", "+loaicap_fk+", "+stt +") ";						
								System.out.println(query);
								if (!db.update(query))
								{
									this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
									db.getConnection().rollback();
								}
								
								stt++;
							}
							rs1.close();
						}
						
					}			
				}
				rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
				//1. LO???I 1 : ????? NGH??? THANH TO??N C???N PH???I DUY???T; LO???I 2: ????? NGH??? THANH TO??N C???P TR??N ???? DUY???T CH??? ???????C XEM
		
				query =     "\n SELECT distinct NV.TEN as NGUOITAO, MUAHANG.PK_SEQ AS MHID, NGAYMUA AS NGAY, DVTH.TEN AS DVTH, "+
				            "\n CASE WHEN MUAHANG.NHACUNGCAP_FK IS NOT NULL THEN NCC.TEN WHEN MUAHANG.NHANVIEN_FK IS NOT NULL THEN NV1.TEN ELSE KH.TEN END AS NCC, MUAHANG.TONGTIENAVAT, " +
							"\n case MUAHANG.LOAIHANGHOA_FK when '0' then SP.MA when '1' then TS.ma else TKKT.SOHIEUTAIKHOAN+' - ' + TKKT.TENTAIKHOAN + '-' + CP.TEN end as MA,  " +
							"\n case MUAHANG.LOAIHANGHOA_FK when '0' then SP.TEN else MUAHANG_SP.diengiai end AS SP,  " +
							"\n MUAHANG_SP.SOLUONG, isnull(MUAHANG_SP.DONGIA, 0) as DONGIA, isnull(MUAHANG_SP.THANHTIEN, 0) as THANHTIEN, isnull(MUAHANG.VUOTNGANSACH, 0) as vuotNganSach, " +
							"\n MUAHANG.SOPO as SOCHUNGTU, DUYETMUAHANG.LOAICAP_FK, " +
							"\n	CASE WHEN ( DUYETMUAHANG.LOAICAP_FK > (SELECT MAX(LOAICAP_FK) FROM ERP_DUYETMUAHANG A WHERE A.MUAHANG_FK = DUYETMUAHANG.MUAHANG_FK AND A.TRANGTHAI = 1 ))  THEN 0 "+ //?????N L?????T M??NH DUY???T
							"\n		 WHEN ( DUYETMUAHANG.LOAICAP_FK = (SELECT MAX(LOAICAP_FK) FROM ERP_DUYETMUAHANG A WHERE A.MUAHANG_FK = DUYETMUAHANG.MUAHANG_FK AND A.TRANGTHAI = 1 ))  THEN 1 "+ //M??NH ???? DUY???T PHI???U N??Y R???I
							"\n ELSE 2 END LOAI,  MUAHANG.TRANGTHAI TRANGTHAI \n"+
						    "\n FROM ERP_MUAHANG MUAHANG " +
							"\n INNER JOIN NHANVIEN NV ON NV.PK_SEQ = MUAHANG.NGUOITAO  " +
							"\n INNER JOIN ERP_MUAHANG_SP MUAHANG_SP ON MUAHANG_SP.MUAHANG_FK = MUAHANG.PK_SEQ   " +
							"\n INNER JOIN ERP_DONVITHUCHIEN DVTH ON DVTH.PK_SEQ = MUAHANG.DONVITHUCHIEN_FK  " +
							"\n LEFT JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = MUAHANG.NHACUNGCAP_FK   " +							
							"\n LEFT JOIN ERP_NHANVIEN NV1 ON NV1.PK_SEQ = MUAHANG.NHANVIEN_FK   " +	
							"\n LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = MUAHANG.KHACHHANG_FK   " +	
							"\n LEFT JOIN SANPHAM SP ON SP.PK_SEQ = MUAHANG_SP.SANPHAM_FK  " +
							"\n LEFT join TraphacoERP.dbo.ERP_TAISANCODINH TS on TS.pk_seq = MUAHANG_SP.TAISAN_FK  " +
							"\n LEFT JOIN TraphacoERP.dbo.ERP_NHOMCHIPHI CP on CP.PK_SEQ = MUAHANG_SP.CHIPHI_FK  " + 
							"\n LEFT JOIN ERP_TAIKHOANKT TKKT ON TKKT.SOHIEUTAIKHOAN = CP.TAIKHOAN_FK "+
							"\n LEFT JOIN ERP_DUYETMUAHANG_NHAP DUYETMUAHANG ON DUYETMUAHANG.MUAHANG_FK = MUAHANG.PK_SEQ AND DUYETMUAHANG.NHANVIEN_FK = " +this.userId +
						    "\n WHERE isnull(MUAHANG.DACHOT, 0) = '1' AND MUAHANG.ISDNTT = 1" +
						    "\n AND DUYETMUAHANG.LOAICAP_FK > 9999 " + // CH??? HI???N NH???NG LO???I C???P > 9999	
						    "\n AND DUYETMUAHANG.NHANVIEN_FK = "+this.userId+ // TRONG B???NG DUY???T C?? USERID N??Y
						    "\n AND MUAHANG.NPP_FK = '" + this.nppId + "' " +
							"\n AND MUAHANG.LOAIHANGHOA_FK = 2  and MUAHANG.TYPE = '1' "+ // CH??? L???Y ????? NGH??? THANH TO??N							
							"\n AND MUAHANG.TRANGTHAI IN ( 0 , 1 ) AND ISNULL( MUAHANG.ISHOANTAT, 0 ) = 0 "; // C???P CU???I C??NG CH??A DUY???T
								
				// 2.T???M ???NG ???? HO??N T???T
								
				query += 	"\n UNION ALL "+
				
							"\n SELECT distinct NV.TEN as NGUOITAO, MUAHANG.PK_SEQ AS MHID, NGAYMUA AS NGAY, DVTH.TEN AS DVTH, "+
				            "\n CASE WHEN MUAHANG.NHACUNGCAP_FK IS NOT NULL THEN NCC.TEN WHEN MUAHANG.NHANVIEN_FK IS NOT NULL THEN NV1.TEN ELSE KH.TEN END AS NCC, MUAHANG.TONGTIENAVAT, " +
							"\n case MUAHANG.LOAIHANGHOA_FK when '0' then SP.MA when '1' then TS.ma else TKKT.SOHIEUTAIKHOAN+' - ' + TKKT.TENTAIKHOAN + '-' + CP.TEN end as MA,  " +
							"\n case MUAHANG.LOAIHANGHOA_FK when '0' then SP.TEN else MUAHANG_SP.diengiai end AS SP,  " +
							"\n MUAHANG_SP.SOLUONG, isnull(MUAHANG_SP.DONGIA, 0) as DONGIA, isnull(MUAHANG_SP.THANHTIEN, 0) as THANHTIEN, isnull(MUAHANG.VUOTNGANSACH, 0) as vuotNganSach, " +
							"\n MUAHANG.SOPO as SOCHUNGTU, DUYETMUAHANG.LOAICAP_FK, 2 LOAI, MUAHANG.TRANGTHAI TRANGTHAI   " +
						    "\n FROM ERP_MUAHANG MUAHANG " +
							"\n INNER JOIN NHANVIEN NV ON NV.PK_SEQ = MUAHANG.NGUOITAO  " +
							"\n INNER JOIN ERP_MUAHANG_SP MUAHANG_SP ON MUAHANG_SP.MUAHANG_FK = MUAHANG.PK_SEQ   " +
							"\n INNER JOIN ERP_DONVITHUCHIEN DVTH ON DVTH.PK_SEQ = MUAHANG.DONVITHUCHIEN_FK  " +
							"\n LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = MUAHANG.NHACUNGCAP_FK   " +							
							"\n LEFT JOIN ERP_NHANVIEN NV1 ON NV1.PK_SEQ = MUAHANG.NHANVIEN_FK   " +	
							"\n LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = MUAHANG.KHACHHANG_FK   " +	
							"\n LEFT JOIN SANPHAM SP ON SP.PK_SEQ = MUAHANG_SP.SANPHAM_FK  " +
							"\n LEFT join TraphacoERP.dbo.ERP_TAISANCODINH TS on TS.pk_seq = MUAHANG_SP.TAISAN_FK  " +
							"\n LEFT JOIN TraphacoERP.dbo.ERP_NHOMCHIPHI CP on CP.PK_SEQ = MUAHANG_SP.CHIPHI_FK  " + 
							"\n LEFT JOIN ERP_TAIKHOANKT TKKT ON TKKT.SOHIEUTAIKHOAN = CP.TAIKHOAN_FK "+
							"\n LEFT JOIN ERP_DUYETMUAHANG DUYETMUAHANG ON DUYETMUAHANG.MUAHANG_FK = MUAHANG.PK_SEQ AND DUYETMUAHANG.NHANVIEN_FK = " +this.userId +
						    "\n WHERE isnull(MUAHANG.DACHOT, 0) = '1' AND MUAHANG.ISDNTT = 1" +
						    "\n AND DUYETMUAHANG.LOAICAP_FK > 9999 " + // CH??? HI???N NH???NG LO???I C???P > 9999	
						    "\n AND DUYETMUAHANG.NHANVIEN_FK = "+this.userId+ // TRONG B???NG DUY???T C?? USERID N??Y
						    "\n AND MUAHANG.NPP_FK = '" + this.nppId + "' " +
							"\n AND MUAHANG.LOAIHANGHOA_FK = 2  and MUAHANG.TYPE = '1' "+ // CH??? L???Y ????? NGH??? THANH TO??N
							"\n AND MUAHANG.TRANGTHAI = 1 AND ISNULL( MUAHANG.ISHOANTAT, 0 ) = 1 "; // C???P CU???I C??NG CH??A DUY???T
							
				query += " ORDER BY NGAY DESC , TRANGTHAI ASC ";
		System.out.println(" 1. init duyet :" + query);
		
		this.polist = this.db.get(query);
		query=" SELECT PK_SEQ AS DVTHID, TEN AS DVTH FROM ERP_DONVITHUCHIEN WHERE TRANGTHAI = '1' " ;
		this.dvth = this.db.get(query) ;
		this.lsp = this.db.get("SELECT PK_SEQ AS LSPID, TEN AS LSP FROM ERP_LOAISANPHAM WHERE TRANGTHAI = '1' ");
		this.nccList = this.db.get("SELECT PK_SEQ, MA + '-' + TEN AS TENNCC FROM TraphacoERP.dbo.ERP_NHACUNGCAP WHERE TRANGTHAI = '1' ");
	}
	
	public boolean Duyetmuahang(String Id){
		 		
		this.getNppInfo();
		try
		{
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						
			//1. KI???M TRA C???P DUY???T C???A USER ????NG NH???P
			
			String nhanvien_fk = this.userId;
			
			String query =  "\n SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK " +
							"\n FROM ( "+
					
							"\n 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  " +
							"\n 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ " +
							"\n 		WHERE A.NPP_FK = "+this.nppId+" AND  B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +
							
							"\n 		UNION ALL "+
							
							"\n 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK " +
							"\n 		FROM ERP_CAPQUANLY A WHERE A.NPP_FK = "+this.nppId+" AND  A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk +
							
							"\n ) A  ";
			
			System.out.println(query);
			ResultSet RsKt = db.get(query);
			int stt = 0;
			String loaicap_fk = "";
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					loaicap_fk =  RsKt.getString("LOAICAP_FK");
				}
				RsKt.close();
			}
			
			// N???U 1 NH??N VI??N C?? 2 QUY???N => B??O L???I
			if(stt > 1)
			{
				this.msg = "Nh??n vi??n n??y c?? 2 quy???n. Vui l??ng ktra l???i";
				db.getConnection().rollback();
				return false;
			}
			
			// C???P NH???T QUY???N C???A USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(MUAHANG_FK) dem FROM ERP_DUYETMUAHANG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND MUAHANG_FK = "+Id;
			
			System.out.println(query);
			RsKt = db.get(query);
			int count = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					count = RsKt.getInt("dem");
				}
				RsKt.close();
			}
			
			if(count>0) // ???? C?? TRONG B???NG DUY???T
				query = " UPDATE ERP_DUYETMUAHANG SET TRANGTHAI = 1 WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND MUAHANG_FK = "+Id;			
			else			
				query = " INSERT ERP_DUYETMUAHANG ( MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU ) SELECT MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, 1, THUTU  FROM ERP_DUYETMUAHANG_NHAP WHERE MUAHANG_FK = "+Id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// UPDATE QUY???N V??O B???NG ERP_MUAHANG
			
			if(loaicap_fk.equals("10000")) // C???P QU???N L?? TR???C TI???P
			{
				query = " UPDATE ERP_MUAHANG SET ISQLTT = 1 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
				
			}
			else if(loaicap_fk.equals("10001")) // QU???N L?? CS
			{
				query = " UPDATE ERP_MUAHANG SET ISCS = 1 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10002")) // DUY???T ??NTT/??NTU
			{
				query = " UPDATE ERP_MUAHANG SET ISDUYETCHI = 1 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10003")) // K??? TO??N T???NG H???P
			{
				query = " UPDATE ERP_MUAHANG SET ISKTTH = 1  WHERE PK_SEQ = "+Id;	
				
				System.out.println(query);
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10004")) // K??? TO??N TR?????NG
			{
				query = " UPDATE ERP_MUAHANG SET ISKTT = 1 , TRANGTHAI = 1, isDaChi = 1 WHERE PK_SEQ = "+Id;	
				
				System.out.println(query);
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			if(loaicap_fk.equals("10004")||loaicap_fk.equals("10003")||loaicap_fk.equals("10002")||loaicap_fk.equals("10001")) //C??C LO???I C???P B???T BU???C PH???I G???N M?? CHI PH??
			{
								
				// KI???M TRA ?????NH KHO???N ????NG HAY KH??NG
				
				// ch???y ?????nh kho???n
				
				String taikhoanCO_DS = "";
				String taikhoanNO_DS = "";
				
				String taikhoanCO_VAT = "";
				String taikhoanNO_VAT = "";
				
				String loaidoituongCO = "";
				String madoituongCO = "";
				
				String loaidoituongNO = "";
				String madoituongNO = "";
				
				query= "  SELECT	N'CHI PH?? ' AS LOAIDOITUONGNO, E.PK_SEQ AS MADOITUONGNO,    \n" +  
					   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN N'NH?? CUNG C???P'  WHEN A.NHANVIEN_FK  IS NOT NULL THEN N'NH??N VI??N' ELSE N'KH??CH H??NG' END AS LOAIDOITUONGCO,   \n" +  
					   "  CASE  WHEN  A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.PK_SEQ WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.PK_SEQ ELSE KH.PK_SEQ END AS MADOITUONGCO, \n" +  
					   "  A.NGAYMUA AS NGAYHOADON, (D.SOLUONG* D.DONGIA)  AS DOANHSO,(D.SOLUONG* D.DONGIA)*  D.PHANTRAMTHUE/100 AS THUE   ,    \n" +  
					   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK END AS TAIKHOANCO_DS,   \n" +  
					   "  CASE  WHEN A.NHACUNGCAP_FK  IS NOT NULL THEN NCC.TAIKHOAN_FK WHEN A.NHANVIEN_FK  IS NOT NULL THEN NV.TAIKHOAN_FK ELSE KH.TAIKHOAN_FK  END AS TAIKHOANCO_VAT,   \n" +  
					   "  (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = E.TAIKHOAN_FK AND CONGTY_FK = A.CONGTY_FK) AS TAIKHOANNO_DS,   \n" +  
					   "  ( SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '13311000' AND CONGTY_FK = A.CONGTY_FK  ) AS TAIKHOANNO_VAT   \n" +  
					   "  FROM ERP_MUAHANG A  \n" +  
					   "  LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = A.NHANVIEN_FK    \n" +  
					   "  LEFT JOIN ERP_NHACUNGCAP NCC ON A.NHACUNGCAP_FK = NCC.PK_SEQ  \n" + 
					   "  LEFT JOIN KHACHHANG KH ON A.KHACHHANG_FK = KH.PK_SEQ   \n" +  
					   "  LEFT JOIN ERP_MUAHANG_SP D ON A.PK_SEQ = D.MUAHANG_FK   \n" +  
					   "  LEFT JOIN ERP_NHOMCHIPHI E ON D.CHIPHI_FK = E.PK_SEQ   \n" +  
					   "  WHERE   A.PK_SEQ ="+Id;
				
				System.out.println("Cau tk1"+ query);
				ResultSet rsTk = db.get(query);
				if(rsTk!= null)
				{
					while(rsTk.next())
					{
						
						double totalDS = Math.round(rsTk.getDouble("DOANHSO"));
						double totalVAT = Math.round(rsTk.getDouble("THUE"));
						
						taikhoanCO_DS = rsTk.getString("taikhoanCO_DS") == null ? "": rsTk.getString("taikhoanCO_DS") ;
						taikhoanNO_DS = rsTk.getString("taikhoanNO_DS") == null ? "": rsTk.getString("taikhoanNO_DS")  ;
						
						taikhoanCO_VAT = rsTk.getString("taikhoanCO_VAT") == null ? "": rsTk.getString("taikhoanCO_VAT")  ;
						taikhoanNO_VAT = rsTk.getString("taikhoanNO_VAT") == null ? "": rsTk.getString("taikhoanNO_VAT") ;
						
						loaidoituongCO = rsTk.getString("loaidoituongCO");
						madoituongCO = rsTk.getString("madoituongCO");
						loaidoituongNO = rsTk.getString("loaidoituongNO");
						madoituongNO = rsTk.getString("madoituongNO");
	
						String ngayghinhan = rsTk.getString("ngayhoadon");
						String nam = ngayghinhan.substring(0, 4);
						String thang = ngayghinhan.substring(5, 7);
						
						String tiente_fk = "100000";					
					
						if(totalDS > 0)
						{
						
							if(taikhoanCO_DS.trim().length() <= 0 || taikhoanNO_DS.trim().length() <= 0 )
							{
								this.msg = "Vui l??ng nh???p kho???n m???c chi ph?? ?????y ????? ";
								db.getConnection().rollback();
							 
								return false;
							}
						}
						
						if(totalVAT > 0)
						{
							if(taikhoanCO_VAT.trim().length() <= 0 || taikhoanNO_VAT.trim().length() <= 0 )
							{
								this.msg = "Vui l??ng nh???p kho???n m???c chi ph?? ?????y ????? ";
								db.getConnection().rollback();
								return false;
							}	
							
						}
						
					}
					rsTk.close();
				}
				
				if(loaicap_fk.equals("10004")) // CH??? C?? C???P K??? TO??N TR?????NG CH???T M???I T??? ?????NG SINH RA PHI???U CHI || UNC 
				{				
					//T??? T???O PHI???U CHI || ???Y NHI???M CHI
					
					query = " SELECT MH.NGAYMUA, MH.NHACUNGCAP_FK, MH.NHANVIEN_FK, MH.KHACHHANG_FK, MH.TONGTIENAVAT, MH.TIENTE_FK, MH.HTTT_FK HTTTID, MH.SOPO, MH.NGUOITAO \n"+
							" FROM ERP_MUAHANG MH \n"+
							" LEFT JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ =  MH.NHACUNGCAP_FK \n"+
							" LEFT JOIN ERP_NHANVIEN NV ON MH.NHANVIEN_FK = NV.PK_SEQ \n"+
							" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = MH.KHACHHANG_FK \n"+
							" WHERE MH.PK_SEQ = "+Id+" \n";
					
					System.out.println(query);
					ResultSet RsDNTT = db.get(query);
					
					String ngaytamung = "";
					String ncc_fk = "";
					String nhanvienfk = "";
					String khachhang_fk = "";
					double sotienavat = 0;
					String htttId = "";
					String tiente_fk = "";
					String PO = "";
					String nguoitao = "";
					
					if(RsDNTT!=null)
					{
						try 
						{
							while (RsDNTT.next())
							{
								ngaytamung = RsDNTT.getString("NGAYMUA");
								ncc_fk = RsDNTT.getString("NHACUNGCAP_FK");
								nhanvienfk =  RsDNTT.getString("NHANVIEN_FK");
								khachhang_fk = RsDNTT.getString("KHACHHANG_FK");
								sotienavat = RsDNTT.getDouble("TONGTIENAVAT");
								htttId = RsDNTT.getString("HTTTID");
								tiente_fk = RsDNTT.getString("tiente_fk");
								PO = RsDNTT.getString("SOPO");
								nguoitao = RsDNTT.getString("NGUOITAO");
							}
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
					}
									
					String prefix = "";
					if(htttId.equals("100000"))
						prefix = "DNPC";
					
					if(htttId.equals("100001"))
						prefix = "DNBN";
					
					query = 
							"Insert ERP_THANHTOANHOADON " +
							"( DVTH_FK, NGAYGHINHAN, NCC_FK ,NHANVIEN_FK, HTTT_FK, NGANHANG_FK, CHINHANH_FK, SOTAIKHOAN, NOIDUNGTT, " +
							"  SOTIENTT, SOTIENTTNT, SOTIENHD, SOTIENHDNT, PHINGANHANG, PHINGANHANGNT, VAT, VATNT, CHENHLECHVND, " +
							"  TRICHPHI, SOTAIKHOAN_TP, NGANHANG_TP_FK, CHINHANH_TP_FK, " +
							"  NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA, LOAITHANHTOAN, TIENTE_FK, TIGIA , THANHTOANTUTIENVAY, KHACHHANG_FK, CTKEMTHEO, NPP_FK, PREFIX, TRANGTHAI " +
							") " +
							"values(NULL, '" + getDateTime() + "', " + ncc_fk + "," + " "+nhanvienfk +", '" + htttId + "', " +
							" NULL, NULL , NULL , N'Thanh to??n cho ????? ngh??? thanh to??n s??? "+Id+"', " +
							"" + sotienavat + ", "+ sotienavat + ", " + sotienavat  + ", " + sotienavat + " , " +
							" 0 , 0 , 0 ,0, 0, 0, '', null , null , '"  + getDateTime() + "', '" + nguoitao + "', '" + getDateTime() + "', '" 
							+ nguoitao + "',0, " + tiente_fk + ", 1 , '0', "+khachhang_fk+" , N'', "+this.nppId+", '"+prefix+ "', 0 )";
					
					System.out.println(query);
					
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi Phi???u Chi/UNC: " + query;
						System.out.println(this.msg);
						db.getConnection().rollback();
						return false;
					}
					
					query = "select IDENT_CURRENT('ERP_THANHTOANHOADON') as tthdId";
					
					ResultSet rsTthd = db.get(query);	
					String tthdCurrent = "";
					if(rsTthd.next())
					{
						tthdCurrent = rsTthd.getString("tthdId");
						rsTthd.close();
					}
					
					// C???P NH???T M?? CH???NG T???
					query = " update ERP_THANHTOANHOADON set machungtu =  Prefix + SUBSTRING(NGAYGHINHAN, 6, 2) + SUBSTRING(NGAYGHINHAN, 0, 5) + '-' + dbo.LaySoChungTu( " + tthdCurrent + " ) " + 
							" where pk_seq = '" + tthdCurrent + "' ";
					
					System.out.println("[ERP_THANHTOANHOADON] error message:" + query);
					
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_THUTIEN: " + query;
						System.out.println("[ErpThutien.createTTHD] error message:" + this.msg);
						db.getConnection().rollback();
						return false;
					}
					
					//TRONG B???NG ERP_THANHTOANHOADON_HOADON LOAIHD = 6 L?? ????? NGH??? THANH TO??N
					
					query = "Insert ERP_THANHTOANHOADON_HOADON( THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, SOHOADON ) " +
							"values('" + tthdCurrent + "', '" + Id + "', '" + sotienavat + "', '" + sotienavat + "'," +
							" 0, 0 , '6', '"+ Id +"')";				
					
					System.out.println(query);
					
					if(!db.update(query))
					{
						this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADON: " + query;
						System.out.println(this.msg);
						db.getConnection().rollback();
						return false;
					}
					
				}
			}
												
			db.getConnection().commit();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return false;
		}
		
		return true;
	}
	
	public String getDaduyet(String mhId){
		String tmp = "";
		String query =  "SELECT DUYETMUAHANG.MUAHANG_FK AS MHID, NV.PK_SEQ AS NVID, NV.DANGNHAP AS NVTEN " +
						"FROM ERP_DUYETMUAHANG DUYETMUAHANG " +
						"INNER JOIN ERP_CHUCDANH CHUCDANH ON CHUCDANH.PK_SEQ = DUYETMUAHANG.CHUCDANH_FK " +
						"INNER JOIN NHANVIEN NV ON NV.PK_SEQ = CHUCDANH.NHANVIEN_FK " +
						"WHERE DUYETMUAHANG.TRANGTHAI = '1' AND MUAHANG_FK = " + mhId + "  ";
		ResultSet rs = this.db.get(query);
		try{
			if(rs != null){
				while(rs.next()){
					tmp = tmp  + rs.getString("NVTEN") + " ; " ;
				}
				if(tmp.length() > 0)
					tmp = tmp.substring(0, tmp.length()-2);
				rs.close();
				return tmp;
			}else{
				return tmp;
			}
		}catch(java.sql.SQLException e){return tmp;}

	}
	public void DBclose(){
		try{
			if(this.dvth != null) this.dvth.close();
			if(this.lsp != null) this.lsp.close();
			if(this.polist != null) this.lsp.close();
			this.db.shutDown();
		}catch(java.sql.SQLException e){}
	}

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
	}

	
	public boolean BoDuyetmuahang(String Id) {
		
		this.getNppInfo();
		
		try
		{
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						
			//1. KI???M TRA C???P DUY???T C???A USER ????NG NH???P
			
			String nhanvien_fk = this.userId;
			
			String query =   	"\n SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK " +
								"\n FROM ( "+
								
								"\n 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  " +
								"\n 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ " +
								"\n 		WHERE A.NPP_FK = "+this.nppId+" AND B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +
								
								"\n 		UNION ALL "+
								
								"\n 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK " +
								"\n 		FROM ERP_CAPQUANLY A WHERE A.NPP_FK = "+this.nppId+" AND A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk +
								
								"\n ) A  ";
			
			System.out.println(query);
			ResultSet RsKt = db.get(query);
			int stt = 0;
			String loaicap_fk = "";
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					loaicap_fk =  RsKt.getString("LOAICAP_FK");
				}
				RsKt.close();
			}
			
			// N???U 1 NH??N VI??N C?? 2 QUY???N => B??O L???I
			if(stt > 1)
			{
				this.msg = "Nh??n vi??n n??y c?? 2 quy???n. Vui l??ng ktra l???i";
				db.getConnection().rollback();
				return false;
			}
			
			// UPDATE QUY???N C???A USER
			query = " UPDATE ERP_DUYETMUAHANG SET TRANGTHAI = 0 , lydomoduyet = N'"+this.lydomoduyet+"' WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND MUAHANG_FK = "+Id;
			
			System.out.println(query);
			
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// UPDATE QUY???N V??O B???NG ERP_MUAHANG
			
			if(loaicap_fk.equals("10000")) // C???P QU???N L?? TR???C TI???P
			{
				query = " UPDATE ERP_MUAHANG SET ISQLTT = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
				
			}
			else if(loaicap_fk.equals("10001")) // QU???N L?? CS
			{
				query = " UPDATE ERP_MUAHANG SET ISCS = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10002")) // DUY???T ??NTT/??NTU
			{
				query = " UPDATE ERP_MUAHANG SET ISDUYETCHI = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10003")) // K??? TO??N T???NG H???P
			{
				query = " UPDATE ERP_MUAHANG SET ISKTTH = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10004")) // K??? TO??N TR?????NG
			{
				
				// KI???M TRA XEM PHI???U N??Y ???? RA PHI???U CHI CH??A - N???U RA R???I KH??NG ???????C PH??P B??? DUY???T
				
				query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
						 " WHERE A.LOAIHD = 6 AND B.TRANGTHAI != 2 AND A.HOADON_FK = "+Id+"  ";
				
				ResultSet rs = db.get(query);
				
				int count = 0;
				if(rs!=null)
				{
					if(rs.next())
					{
						count = rs.getInt("dem");
					}
					rs.close();	
				}
				
				if(count <= 0)
				{				
					query = " UPDATE ERP_MUAHANG SET ISKTT = 0 , TRANGTHAI = 0, isDACHI = 0 WHERE PK_SEQ = "+Id;	
					
					if (!db.update(query))
					{
						this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
				else
				{
					this.msg = "????? ngh??? thanh to??n ???? ra phi???u chi r???i. B???n kh??ng ???????c ph??p b??? duy???t.";
					db.getConnection().rollback();
					return false;
				}
				
			}
			
												
			db.getConnection().commit();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return false;
		}
		
		return true;
	}

	
	public String getLydoxoa() {
	
		return this.lydoxoa;
	}

	
	public void setLydoxoa(String lydoxoa) {
	
		this.lydoxoa = lydoxoa;
	}

	
	public String getLydomoduyet() {
		
		return this.lydomoduyet;
	}

	
	public void setLydomoduyet(String lydomoduyet) {
		
		this.lydomoduyet = lydomoduyet;
	}

	
	public boolean Xoamuahang(String Id) {
		
		try
		{
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						
			//1. KI???M TRA C???P DUY???T C???A USER ????NG NH???P
			
			String nhanvien_fk = this.userId;
			
			String query =  "\n SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK " +
							"\n FROM ( "+
							
							"\n 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  " +
							"\n 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ " +
							"\n 		WHERE A.NPP_FK = "+this.nppId+" AND B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +
							
							"\n 		UNION ALL "+
							
							"\n 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK " +
							"\n 		FROM ERP_CAPQUANLY A WHERE A.NPP_FK = "+this.nppId+" AND A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk +
							
							"\n ) A  ";
			
			System.out.println(query);
			ResultSet RsKt = db.get(query);
			int stt = 0;
			String loaicap_fk = "";
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					loaicap_fk =  RsKt.getString("LOAICAP_FK");
				}
				RsKt.close();
			}
			
			// N???U 1 NH??N VI??N C?? 2 QUY???N => B??O L???I
			if(stt > 1)
			{
				this.msg = "Nh??n vi??n n??y c?? 2 quy???n. Vui l??ng ktra l???i";
				db.getConnection().rollback();
				return false;
			}
			
			
			// C???P NH???T QUY???N C???A USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(MUAHANG_FK) dem FROM ERP_DUYETMUAHANG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND MUAHANG_FK = "+Id;
			
			System.out.println(query);
			RsKt = db.get(query);
			int count = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					count = RsKt.getInt("dem");
				}
				RsKt.close();
			}
			
			if(count>0) // ???? C?? TRONG B???NG DUY???T
				query = " UPDATE ERP_DUYETMUAHANG SET TRANGTHAI = 2, lydoxoa = N'"+this.lydoxoa+"' WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND MUAHANG_FK = "+Id;			
			else			
				query = " INSERT ERP_DUYETMUAHANG ( MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU , lydoxoa) SELECT MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, 1, THUTU, N'"+this.lydoxoa+"'  FROM ERP_DUYETMUAHANG_NHAP WHERE MUAHANG_FK = "+Id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
						

			// KI???M TRA XEM PHI???U N??Y ???? RA PHI???U CHI CH??A - N???U C?? R??NG K CHO X??A
			
			query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
					 " WHERE A.LOAIHD = 6 AND B.TRANGTHAI != 2 AND A.HOADON_FK = "+Id+"  ";
			
			ResultSet rs = db.get(query);
			
			count = 0;
			if(rs!=null)
			{
				if(rs.next())
				{
					count = rs.getInt("dem");
				}
				rs.close();	
			}
			
			if(count>0)
			{
				this.msg = "????? ngh??? thanh to??n n??y ???? ra phi???u chi r???i. B???n kh??ng th??? x??a phi???u n??y. ";
				db.getConnection().rollback();
				return false;
			}
						
			// UPDATE QUY???N V??O B???NG ERP_MUAHANG
			
			query = " UPDATE ERP_MUAHANG SET TRANGTHAI = 3 WHERE PK_SEQ = "+Id;	
			
			System.out.println(query);
			
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_MUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
								
			db.getConnection().commit();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return false;
		}
		
		return true;
	}


	public String getLydosua() {
	
		return this.lydosua;
	}


	public void setLydosua(String lydosua) {
	
		this.lydosua = lydosua;
	}

	
	public boolean Suamuahang(String Id) {
		
		try
		{
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						
			//1. KI???M TRA C???P DUY???T C???A USER ????NG NH???P
			
			String nhanvien_fk = this.userId;
			
			String query =  " SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK \n" +
							" FROM ( \n"+
							
							" 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  \n" +
							" 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
							" 		WHERE A.NPP_FK = "+this.nppId+" AND B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +
							
							" 		UNION ALL \n"+
							
							" 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK \n" +
							" 		FROM ERP_CAPQUANLY A WHERE A.NPP_FK = "+this.nppId+" AND A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk +
							
							") A  \n";
			
			System.out.println(query);
			ResultSet RsKt = db.get(query);
			int stt = 0;
			String loaicap_fk = "";
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					loaicap_fk =  RsKt.getString("LOAICAP_FK");
				}
				RsKt.close();
			}
			
			// N???U 1 NH??N VI??N C?? 2 QUY???N => B??O L???I
			if(stt > 1)
			{
				this.msg = "Nh??n vi??n n??y c?? 2 quy???n. Vui l??ng ktra l???i";
				db.getConnection().rollback();
				return false;
			}
			
			// C???P NH???T QUY???N C???A USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(MUAHANG_FK) dem FROM ERP_DUYETMUAHANG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND MUAHANG_FK = "+Id;
			
			System.out.println(query);
			RsKt = db.get(query);
			int count = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					count = RsKt.getInt("dem");
				}
				RsKt.close();
			}
			
			if(count>0) // ???? C?? TRONG B???NG DUY???T
				query = " UPDATE ERP_DUYETMUAHANG SET lydosua = N'"+this.lydosua+"' WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND MUAHANG_FK = "+Id;
			else			
				query = " INSERT ERP_DUYETMUAHANG ( MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU , LYDOSUA ) SELECT MUAHANG_FK, NHANVIEN_FK, LOAICAP_FK, 0, THUTU, N'"+this.lydosua+"'  FROM ERP_DUYETMUAHANG_NHAP WHERE MUAHANG_FK = "+Id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// C???P NH???T NG?????I S???A PHI???U
			query = " UPDATE ERP_MUAHANG SET NGUOISUA = "+this.userId+" WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND MUAHANG_FK = "+Id;
			
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// KI???M TRA XEM PHI???U N??Y ???? RA PHI???U CHI CH??A - N???U C?? R??NG K CHO S???A PHI???U
			
			query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
					 " WHERE A.LOAIHD = 6 AND B.TRANGTHAI != 2 AND A.HOADON_FK = "+Id+"  ";
			
			ResultSet rs = db.get(query);
			
			count = 0;
			if(rs!=null)
			{
				if(rs.next())
				{
					count = rs.getInt("dem");
				}
				rs.close();	
			}
			
			if(count > 0)
			{	
				this.msg = "????? ngh??? thanh to??n ???? ra phi???u chi r???i. B???n kh??ng ???????c ph??p ch???nh s???a.";
				db.getConnection().rollback();
				return false;
			}
														
			db.getConnection().commit();
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return false;
		}
		
		return true;
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
