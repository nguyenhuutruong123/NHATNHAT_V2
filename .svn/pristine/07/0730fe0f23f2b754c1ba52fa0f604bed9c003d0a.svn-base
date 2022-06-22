package geso.dms.distributor.beans.tamung.imp;

import geso.dms.distributor.beans.tamung.IDuyettamung;
import geso.dms.center.util.Utility;
import geso.dms.center.db.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class Duyettamung implements IDuyettamung {
	
	String congtyId;
	String userId;
	String ctyId;
	
	String dvthId;
	ResultSet dvth;
	
	String lspId;
	String ngaymua;
	String maDMH;
	String nccId;
	String lydoxoa;
	String lydomoduyet;
	String lydosua;
	String nppId;
	ResultSet nccList;
	ResultSet lsp;
	
	ResultSet polist;
	
	HttpServletRequest request;	
	String msg;
	dbutils db;
    Utility util;

	public Duyettamung(){
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
		String query = "";
		
		this.getNppInfo();
		// INSERT QUYỀN VÀO BẢNG ERP_DUYETMUAHANG_NHAP NHỮNG PHIẾU TU TRẠNG THÁI = 0
		
		try{
			query = "DELETE ERP_DUYETTAMUNG_NHAP ";		
			
			System.out.println(query);
			if (!db.update(query))
			{
				msg = "Khong the cap nhat ERP_DUYETTAMUNG_NHAP: " + query;
				db.getConnection().rollback();
			}
			
			query = "SELECT NGUOITAO, PK_SEQ TAMUNG_FK, ( SELECT distinct A.LOAICAP_FK  \n" +
					" 									   FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
					" 									   WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = TU.NGUOITAO ) LOAICAP_FK \n" +
					"FROM ERP_TAMUNG TU WHERE TRANGTHAI = 0 AND isnull( DACHOT, 0 ) = '1' and TU.NPP_FK = " +this.nppId +			
					
					"UNION ALL \n" +
			
					"SELECT NGUOITAO, PK_SEQ TAMUNG_FK, ( SELECT distinct A.LOAICAP_FK  \n" +
					" 									   FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
					" 									   WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = TU.NGUOITAO ) LOAICAP_FK " +
					"FROM ERP_TAMUNG TU WHERE TRANGTHAI = 1 AND isnull( DACHOT, 0 ) = '1' AND ISNULL( ISHOANTAT, 0 ) = 0 and TU.NPP_FK = " +this.nppId ; // ĐÃ RA PHIẾU CHI, NHƯNG PHIẾU CHI CHƯA CHỐT
					
			System.out.println(query);
			ResultSet rs = db.get(query);
			
			String nhanvien_fk = "";
			String tamung_fk = "";
			String loaicap_fk = "";
			int stt = 0;
			
			rs = db.get(query);
			
			if(rs != null)
			{
				while(rs.next())
				{
					stt = 0 ;
					nhanvien_fk = rs.getString("NGUOITAO");
					tamung_fk = rs.getString("TAMUNG_FK");
					loaicap_fk = rs.getString("LOAICAP_FK");
					
					query = " INSERT ERP_DUYETTAMUNG_NHAP ( TAMUNG_FK, TRANGTHAI, NHANVIEN_FK, LOAICAP_FK, THUTU ) "+
							" VALUES ("+tamung_fk+", 0 , "+nhanvien_fk+", "+loaicap_fk+", "+stt+") ";	
			
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
								
								query = " INSERT ERP_DUYETTAMUNG_NHAP (TAMUNG_FK, TRANGTHAI, NHANVIEN_FK, LOAICAP_FK, THUTU ) "+
										" VALUES ("+tamung_fk+", 0 , "+nhanvien_fk+", "+loaicap_fk+", "+stt +") ";						
								System.out.println(query);
								if (!db.update(query))
								{
									this.msg = "Khong the cap nhat ERP_DUYETTAMUNG_NHAP: " + query;
									db.getConnection().rollback();
								}
								
								stt++;
							}
							rs1.close();
						}
						
					}
					
				}rs.close();
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
				
		//1. loại 1 : TẠM ỨNG CẦN PHẢI DUYỆT; loại 2: TẠM ỨNG CẤP TRÊN ĐÃ DUYỆT CHỈ ĐƯỢC XEM
		
		query =  " SELECT 	distinct NV.TEN as NGUOITAO, TAMUNG.PK_SEQ AS MHID, TAMUNG.NGAYTAMUNG AS NGAY,  \n"+
				 " 			CASE WHEN TAMUNG.NCC_FK IS NOT NULL THEN NCC.TEN WHEN TAMUNG.NHANVIEN_FK IS NOT NULL THEN NV1.TEN END AS NCC, \n" +
				 " 			TAMUNG.SOTIENTAMUNG TONGTIENAVAT,  \n"+
				 " 			isnull(TAMUNG.SOTIENTAMUNG, 0) as THANHTIEN, TAMUNG.PK_SEQ as SOCHUNGTU, TAMUNG.TRANGTHAI , DUYETTAMUNG.LOAICAP_FK, \n" +
				 "			CASE WHEN ( DUYETTAMUNG.LOAICAP_FK > (SELECT MAX(LOAICAP_FK) FROM ERP_DUYETTAMUNG A WHERE A.TAMUNG_FK = DUYETTAMUNG.TAMUNG_FK AND A.TRANGTHAI = 1 ))  THEN 0 \n"+ //ĐẾN LƯỢT MÌNH DUYỆT
				 "				 WHEN ( DUYETTAMUNG.LOAICAP_FK = (SELECT MAX(LOAICAP_FK) FROM ERP_DUYETTAMUNG A WHERE A.TAMUNG_FK = DUYETTAMUNG.TAMUNG_FK AND A.TRANGTHAI = 1 ))  THEN 1 \n"+ //MÌNH ĐÃ DUYỆT PHIẾU NÀY RỒI
				 " 			ELSE 2 END LOAI \n"+
				 " FROM ERP_TAMUNG TAMUNG \n"+
				 " INNER JOIN NHANVIEN NV ON NV.PK_SEQ = TAMUNG.NGUOITAO    \n"+
				 " LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = TAMUNG.NCC_FK   \n"+							
				 " LEFT JOIN ERP_NHANVIEN NV1 ON NV1.PK_SEQ = TAMUNG.NHANVIEN_FK  \n"+  
				 " LEFT JOIN ERP_DUYETTAMUNG_NHAP DUYETTAMUNG ON DUYETTAMUNG.TAMUNG_FK = TAMUNG.PK_SEQ AND DUYETTAMUNG.NHANVIEN_FK = " +this.userId +
				 " AND DUYETTAMUNG.NHANVIEN_FK = "+this.userId +
				 " WHERE TAMUNG.TRANGTHAI IN ( 0 , 1 ) AND isnull(TAMUNG.DACHOT, 0) = '1' \n"+
				 " AND DUYETTAMUNG.LOAICAP_FK > 9999  \n"+ // CHỈ HIỆN NHỮNG LOẠI CẤP > 9999	
				 " AND DUYETTAMUNG.NHANVIEN_FK = "+this.userId+ // TRONG BẢNG DUYỆT CÓ USERID NÀY
				 " AND TAMUNG.npp_fk = '"+ this.nppId + "' \n"+
				 " AND ISNULL( TAMUNG.ISHOANTAT, 0 ) = 0  \n";
				
				
		// 2.TẠM ỨNG ĐÃ HOÀN TẤT
		
		query += " UNION ALL \n"+
		
				 " SELECT 	distinct NV.TEN as NGUOITAO, TAMUNG.PK_SEQ AS MHID, TAMUNG.NGAYTAMUNG AS NGAY,  \n"+
				 " 			CASE WHEN TAMUNG.NCC_FK IS NOT NULL THEN NCC.TEN WHEN TAMUNG.NHANVIEN_FK IS NOT NULL THEN NV1.TEN END AS NCC, \n" +
				 " 			TAMUNG.SOTIENTAMUNG TONGTIENAVAT,  \n"+
				 " 			isnull(TAMUNG.SOTIENTAMUNG, 0) as THANHTIEN, TAMUNG.PK_SEQ as SOCHUNGTU, TAMUNG.TRANGTHAI , DUYETTAMUNG.LOAICAP_FK, 2 AS LOAI  \n"+
				 " FROM ERP_TAMUNG TAMUNG \n"+
				 " INNER JOIN NHANVIEN NV ON NV.PK_SEQ = TAMUNG.NGUOITAO    \n"+
				 " LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = TAMUNG.NCC_FK   \n"+							
				 " LEFT JOIN ERP_NHANVIEN NV1 ON NV1.PK_SEQ = TAMUNG.NHANVIEN_FK  \n"+  
				 " LEFT JOIN ERP_DUYETTAMUNG DUYETTAMUNG ON DUYETTAMUNG.TAMUNG_FK = TAMUNG.PK_SEQ AND DUYETTAMUNG.NHANVIEN_FK = " +this.userId +
				 " AND DUYETTAMUNG.NHANVIEN_FK = "+this.userId +
				 " WHERE isnull( TAMUNG.DACHOT, 0 ) = '1' \n"+
				 " AND DUYETTAMUNG.LOAICAP_FK > 9999  \n"+ // CHỈ HIỆN NHỮNG LOẠI CẤP > 9999	
				 " AND DUYETTAMUNG.NHANVIEN_FK = "+this.userId+ // TRONG BẢNG DUYỆT CÓ USERID NÀY
				 " AND TAMUNG.NPP_fk = '"+ this.nppId + "' \n"+
				 " AND TAMUNG.TRANGTHAI IN ( 1 ) AND ISNULL( TAMUNG.ISHOANTAT, 0 ) = 1 \n"; //KIỂM TRA CẤP TRÊN CỦA USER NÀY ĐÃ DUYỆT PHIẾU CHƯA	
				
		
		
		query += "ORDER BY MHID ASC , NGAY ASC";

		System.out.println(" 1. init duyet :" + query);
		
		this.polist = this.db.get(query);
		query=" SELECT PK_SEQ AS DVTHID, TEN AS DVTH FROM ERP_DONVITHUCHIEN WHERE TRANGTHAI = '1'  ";
		this.dvth = this.db.get(query) ;
		this.lsp = this.db.get("SELECT PK_SEQ AS LSPID, TEN AS LSP FROM ERP_LOAISANPHAM WHERE TRANGTHAI = '1' ");
		this.nccList = this.db.get("SELECT PK_SEQ, MA + '-' + TEN AS TENNCC FROM TraphacoERP.dbo.ERP_NHACUNGCAP WHERE TRANGTHAI = '1' and NPP_FK = 1 ");
	}
	
	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public boolean Duyetmuahang(String Id){
		 	
		this.getNppInfo();
		try
		{
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						
			//1. KIỂM TRA CẤP DUYỆT CỦA USER ĐĂNG NHẬP
			
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
			
			// NẾU 1 NHÂN VIÊN CÓ 2 QUYỀN => BÁO LỖI
			if(stt > 1)
			{
				this.msg = "Nhân viên này có 2 quyền. Vui lòng ktra lại";
				db.getConnection().rollback();
				return false;
			}			

			// CẬP NHẬT QUYỀN CỦA USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(TAMUNG_FK) dem FROM ERP_DUYETTAMUNG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND TAMUNG_FK = "+Id;
			
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
			
			if(count>0) // ĐÃ CÓ TRONG BẢNG DUYỆT
				query = " UPDATE ERP_DUYETTAMUNG SET TRANGTHAI = 1 WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND TAMUNG_FK = "+Id;			
			else			
				query = " INSERT ERP_DUYETTAMUNG ( TAMUNG_FK, NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU ) SELECT TAMUNG_FK, NHANVIEN_FK, LOAICAP_FK, 1, THUTU  FROM ERP_DUYETTAMUNG_NHAP WHERE TAMUNG_FK = "+Id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETTAMUNG: " + query;
				db.getConnection().rollback();
				return false;
			}
						
			// UPDATE QUYỀN VÀO BẢNG ERP_MUAHANG
			
			if(loaicap_fk.equals("10000")) // CẤP QUẢN LÝ TRỰC TIẾP
			{
				query = " UPDATE ERP_TAMUNG SET ISQLTT = 1 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
				
			}
			else if(loaicap_fk.equals("10001")) // QUẢN LÝ CS
			{
				query = " UPDATE ERP_TAMUNG SET ISCS = 1 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}		
			else if(loaicap_fk.equals("10002")) // DUYỆT ĐNTT/ĐNTU
			{
				query = " UPDATE ERP_TAMUNG SET ISDUYETCHI = 1 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10003")) // KẾ TOÁN TỔNG HỢP
			{
				query = " UPDATE ERP_TAMUNG SET ISKTTH = 1 WHERE PK_SEQ = "+Id;	
				
				System.out.println(query);
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10004")) // KẾ TOÁN TRƯỞNG
			{
				query = " UPDATE ERP_TAMUNG SET ISKTT = 1, TRANGTHAI = 1, isDaChi = 1 WHERE PK_SEQ = "+Id;	
				
				System.out.println(query);
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			
			if(loaicap_fk.equals("10004"))
			{				
				//TỰ TẠO PHIẾU CHI || ỦY NHIỆM CHI
				
				query = " SELECT TU.NGAYTAMUNG, TU.NCC_FK, TU.NHANVIEN_FK, TU.SOTIENTAMUNG, TU.LYDOTAMUNG, TU.TIENTE_FK, TU.HTTT_FK HTTTID, TU.NGUOITAO " +
						" FROM ERP_TAMUNG TU " +
						" LEFT JOIN TraphacoERP.dbo.ERP_NHACUNGCAP NCC ON NCC.PK_SEQ = TU.NCC_FK "+
						" LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ = TU.NHANVIEN_FK "+
						" LEFT JOIN NHANVIEN N ON N.PK_SEQ = TU.NGUOITAO "+
						" WHERE TU.PK_SEQ = '"+Id+"' AND TU.NPP_FK = "+this.nppId;
				System.out.println(query);
				ResultSet RsTamUng = db.get(query);
				
				String ngaytamung = "";
				String ncc_fk = "";
				String nhanvienfk = "";
				double sotientamung = 0;
				String htttId = "";
				String tiente_fk = "";
				String nguoitao = "";
				
				if(RsTamUng!=null)
				{
					try 
					{
						while (RsTamUng.next())
						{
							ngaytamung = RsTamUng.getString("NGAYTAMUNG");
							ncc_fk = RsTamUng.getString("NCC_FK");
							nhanvienfk =  RsTamUng.getString("NHANVIEN_FK");
							sotientamung = RsTamUng.getDouble("SOTIENTAMUNG");
							htttId = RsTamUng.getString("HTTTID");
							tiente_fk = RsTamUng.getString("tiente_fk");
							nguoitao = RsTamUng.getString("nguoitao");
						}
					} catch (SQLException e) {
						RsTamUng.close();
						e.printStackTrace();
					}
					finally
					{
						RsTamUng.close();
					}
				}
				double TienTamUng_VND =  0;
				double TienTamUng_NT = 0;
				double SoTienHD_VND = 0;
				double SoTienHD_NT = 0;
				
				if(tiente_fk.equals("100000"))
				{
					TienTamUng_VND = sotientamung;
					SoTienHD_VND  =  sotientamung;
					System.out.println("TienTamUng_VND");
				}
				else
				{
					TienTamUng_NT = sotientamung;
					SoTienHD_NT = sotientamung;
					System.out.println("TienTamUng_NT");
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
					" NULL, NULL , NULL , N'Thanh toán cho đề nghị tạm ứng số "+Id+"', " +
					"" + sotientamung + ", "+ TienTamUng_NT + ", " + SoTienHD_VND  + ", " + SoTienHD_NT + " , " +
					" 0 , 0 , 0 ,0, 0, 0, '', null , null , '"  + getDateTime() + "', '" + nguoitao + "', '" + getDateTime() + "', '" 
					+ nguoitao + "',0, " + tiente_fk + ", 1 , '0', null , N'', "+this.nppId+", '"+prefix+ "', 0 )";
				
				System.out.println(query);
				
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi Phiếu Chi/UNC: " + query;
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
				
				// cập nhật mã chứng từ
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
				
				//TRONG BẢNG ERP_THANHTOANHOADON_HOADON LOAIHD = 1 LÀ ĐỂ NGHỊ TẠM ỨNG
				
				query = "Insert ERP_THANHTOANHOADON_HOADON(THANHTOANHD_FK, HOADON_FK, SOTIENTT, SOTIENAVAT, SOTIENNT, CONLAI, LOAIHD, SOHOADON) " +
						"values('" + tthdCurrent + "', '" + Id + "', '" + sotientamung + "', '" + sotientamung + "'," +
								" 0, 0 , '1', '"+ Id +"')";				
				
				System.out.println(query);
				
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADON: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
				}
				
				
				query = 
				"INSERT INTO ERP_THANHTOANHOADON_PHINGANHANG(THANHTOANHD_FK, MAHOADON, MAUHOADON, KYHIEU, SOHOADON, " +
				"NGAYHOADON, TENNCC, MST, TIENHANG, THUESUAT, TIENTHUE, NGANHANG_FK, CHINHANH_FK) " +
				"VALUES(" + tthdCurrent + ", N'', N'', N'', ''," +
				"'" + getDateTime() + "', N'', '', 0, 0, 0, " +
				"null, null)";
		
				System.out.println(query);
				
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_THANHTOANHOADON_HOADON: " + query;
					System.out.println(this.msg);
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
	
	public String getDaduyet(String mhId){
		String tmp = "";
		/*String query =  "SELECT DUYETMUAHANG.TAMUNG_FK AS MHID, NV.PK_SEQ AS NVID, NV.DANGNHAP AS NVTEN " +
						"FROM ERP_DUYETTAMUNG DUYETMUAHANG " +
						"INNER JOIN ERP_CHUCDANH CHUCDANH ON CHUCDANH.PK_SEQ = DUYETMUAHANG.CHUCDANH_FK " +
						"INNER JOIN NHANVIEN NV ON NV.PK_SEQ = CHUCDANH.NHANVIEN_FK " +
						"WHERE DUYETMUAHANG.TRANGTHAI = '1' AND TAMUNG_FK = " + mhId + "  ";
		ResultSet rs = this.db.get(query);*/
		return tmp;
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
						
			//1. KIỂM TRA CẤP DUYỆT CỦA USER ĐĂNG NHẬP
			
			String nhanvien_fk = this.userId;
			
			String query =  " SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK \n" +
							" FROM ( \n"+
					
							" 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  \n" +
							" 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
							" 		WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +" AND A.NPP_FK = "+this.nppId +
							
							" 		UNION ALL \n"+
							
							" 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK \n" +
							" 		FROM ERP_CAPQUANLY A WHERE A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk + " AND A.NPP_FK = "+this.nppId +
							
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
			
			// NẾU 1 NHÂN VIÊN CÓ 2 QUYỀN => BÁO LỖI
			if(stt > 1)
			{
				this.msg = "Nhân viên này có 2 quyền. Vui lòng ktra lại";
				db.getConnection().rollback();
				return false;
			}
			
			// UPDATE QUYỀN CỦA USER
			query = " UPDATE ERP_DUYETTAMUNG SET TRANGTHAI = 0 , lydomoduyet = N'"+this.lydomoduyet+"' WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND TAMUNG_FK = "+Id;
			
			System.out.println(query);
			
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETTAMUNG: " + query;
				db.getConnection().rollback();
				return false;
			}
						
			
			// UPDATE QUYỀN VÀO BẢNG ERP_MUAHANG
			
			if(loaicap_fk.equals("10000")) // CẤP QUẢN LÝ TRỰC TIẾP
			{
				query = " UPDATE ERP_TAMUNG SET ISQLTT = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
				
			}
			else if(loaicap_fk.equals("10001")) // Quản lý CS
			{
				query = " UPDATE ERP_TAMUNG SET ISCS = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}		
			else if(loaicap_fk.equals("10002")) // Duyệt ĐNTT/ĐNTU
			{
				query = " UPDATE ERP_TAMUNG SET ISDUYETCHI = 0 WHERE PK_SEQ = "+Id;	
				
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10003")) // Kế toán tổng hợp
			{
				
				query = " UPDATE ERP_TAMUNG SET ISKTTH = 0 WHERE PK_SEQ = "+Id;	
				
				System.out.println(query);
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else if(loaicap_fk.equals("10004")) // KTT
			{
				query = " UPDATE ERP_TAMUNG SET ISKTT = 0, TRANGTHAI = 0, isDaChi = 0 WHERE PK_SEQ = "+Id;	
				
				System.out.println(query);
				if (!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return false;
				}
				
				// KIẾM TRA XEM PHIẾU NÀY ĐÃ RA PHIẾU CHI CHƯA
				
				query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
						 " WHERE A.LOAIHD = 1 AND B.TRANGTHAI != 2 AND A.HOADON_FK = "+Id+"  ";
				
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
					query = " UPDATE ERP_TAMUNG SET ISGANMACP = 0 , TRANGTHAI = 0, isDaChi = 0 WHERE PK_SEQ = "+Id;	
					
					System.out.println(query);
					if (!db.update(query))
					{
						this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
				else
				{
					this.msg = "Tạm ứng đã ra phiếu chi rồi. Bạn không được phép bỏ duyệt.";
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
			this.getNppInfo();
			
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						

			// KIẾM TRA XEM PHIẾU NÀY ĐÃ RA PHIẾU CHI CHƯA
			
			String query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
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
			
			if(count>0)
			{
				this.msg = "Đề nghị thanh toán này đã ra phiếu chi rồi. Bạn không thể xóa phiếu này. ";
				db.getConnection().rollback();
				return false;
			}
			

			//1. KIỂM TRA CẤP DUYỆT CỦA USER ĐĂNG NHẬP			
			
			String nhanvien_fk = this.userId;
			
					query = " SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK \n" +
							" FROM ( \n"+
					
							" 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  \n" +
							" 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
							" 		WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk + "  AND A.NPP_FK = "+this.nppId +
							
							" 		UNION ALL \n"+
							
							" 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK \n" +
							" 		FROM ERP_CAPQUANLY A WHERE A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk + " AND A.NPP_FK = "+this.nppId +
							
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
			
			// NẾU 1 NHÂN VIÊN CÓ 2 QUYỀN => BÁO LỖI
			if(stt > 1)
			{
				this.msg = "Nhân viên này có 2 quyền. Vui lòng ktra lại";
				db.getConnection().rollback();
				return false;
			}
						
			
			// CẬP NHẬT QUYỀN CỦA USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(TAMUNG_FK) dem FROM ERP_DUYETTAMUNG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND TAMUNG_FK = "+Id;
			
			System.out.println(query);
			RsKt = db.get(query);
			count = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					count = RsKt.getInt("dem");
				}
				RsKt.close();
			}
			
			if(count>0) // ĐÃ CÓ TRONG BẢNG DUYỆT
				query = " UPDATE ERP_DUYETTAMUNG SET TRANGTHAI = 2, lydoxoa = N'"+this.lydoxoa+"' WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND TAMUNG_FK = "+Id;			
			else			
				query = " INSERT ERP_DUYETTAMUNG ( TAMUNG_FK, NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU , lydoxoa) SELECT TAMUNG_FK, NHANVIEN_FK, LOAICAP_FK, 1, THUTU, N'"+this.lydoxoa+"'  FROM ERP_DUYETTAMUNG_NHAP WHERE TAMUNG_FK = "+Id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// KIẾM TRA XEM PHIẾU NÀY ĐÃ RA PHIẾU CHI CHƯA
			
			query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
					 " WHERE A.LOAIHD = 1 AND B.TRANGTHAI != 2 AND A.HOADON_FK = "+Id+"  ";
			
			rs = db.get(query);
			
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
				this.msg = "Tạm ứng đã ra phiếu chi rồi. Bạn không được phép bỏ duyệt.";
				db.getConnection().rollback();
				return false;
			}			
			
			// UPDATE QUYỀN VÀO BẢNG ERP_MUAHANG
			
			query = " UPDATE ERP_TAMUNG SET TRANGTHAI = 2 WHERE PK_SEQ = "+Id;	
			
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
			this.getNppInfo();
			
			db = new dbutils();
						
			db.getConnection().setAutoCommit(false);
						
			// KIẾM TRA XEM PHIẾU NÀY ĐÃ RA PHIẾU CHI CHƯA
			
			String query =  " SELECT count(A.HOADON_FK) dem FROM ERP_THANHTOANHOADON_HOADON A INNER JOIN ERP_THANHTOANHOADON B ON A.THANHTOANHD_FK = B.PK_SEQ" +
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
			
			if(count>0)
			{
				this.msg = "Đề nghị thanh toán này đã ra phiếu chi rồi. Bạn không thể xóa phiếu này. ";
				db.getConnection().rollback();
				return false;
			}
			
			//1. KIỂM TRA CẤP DUYỆT CỦA USER ĐĂNG NHẬP
			
			String nhanvien_fk = this.userId;
			
			query = " SELECT distinct A.LOAICAP_FK, A.NHANVIEN_FK \n" +
					" FROM ( \n"+
			
					" 		SELECT A.LOAICAP_FK, A.NHANVIEN_FK  \n" +
					" 		FROM ERP_CAPQUANLY_CT A INNER JOIN ERP_CAPQUANLY B ON A.CAPQUANLY_FK = B.PK_SEQ \n" +
					" 		WHERE B.TRANGTHAI = 1 AND A.NHANVIEN_FK = " + nhanvien_fk +" AND A.NPP_FK = "+this.nppId +
					
					" 		UNION ALL \n"+
					
					" 		SELECT A.LOAICAP_FK, NVQL_FK NHANVIEN_FK \n" +
					" 		FROM ERP_CAPQUANLY A WHERE A.TRANGTHAI = 1 AND A.NVQL_FK = "+nhanvien_fk + " AND A.NPP_FK = "+this.nppId+
					
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
			
			// NẾU 1 NHÂN VIÊN CÓ 2 QUYỀN => BÁO LỖI
			if(stt > 1)
			{
				this.msg = "Nhân viên này có 2 quyền. Vui lòng ktra lại";
				db.getConnection().rollback();
				return false;
			}
			
			// CẬP NHẬT QUYỀN CỦA USER TRONG ERP_DUYETMUAHANG
			
			query = " SELECT count(TAMUNG_FK) dem FROM ERP_DUYETTAMUNG WHERE NHANVIEN_FK = "+nhanvien_fk +" AND LOAICAP_FK = "+loaicap_fk+ " AND TAMUNG_FK = "+Id;
			
			System.out.println(query);
			RsKt = db.get(query);
			count = 0;
			
			if(RsKt!=null)
			{
				while(RsKt.next())
				{
					stt++;
					count = RsKt.getInt("dem");
				}
				RsKt.close();
			}
					
			if(count>0) // ĐÃ CÓ TRONG BẢNG DUYỆT
				query = " UPDATE ERP_DUYETTAMUNG SET lydosua = N'"+this.lydosua+"' WHERE NHANVIEN_FK = "+nhanvien_fk+" AND LOAICAP_FK = "+loaicap_fk+" AND TAMUNG_FK = "+Id;
			else			
				query = " INSERT ERP_DUYETTAMUNG ( TAMUNG_FK , NHANVIEN_FK, LOAICAP_FK, TRANGTHAI, THUTU , LYDOSUA ) SELECT TAMUNG_FK, NHANVIEN_FK, LOAICAP_FK, 0, THUTU, N'"+this.lydosua+"'  FROM ERP_DUYETTAMUNG_NHAP WHERE TAMUNG_FK = "+Id+" AND NHANVIEN_FK = "+nhanvien_fk;
						
			if (!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
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


}
