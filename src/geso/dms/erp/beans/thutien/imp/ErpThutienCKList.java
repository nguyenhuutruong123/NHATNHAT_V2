package geso.traphaco.erp.beans.thutien.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import geso.traphaco.center.db.sql.dbutils;
import geso.traphaco.center.util.IDinhKhoanKeToan;
import geso.traphaco.center.util.IPhanTrang;
import geso.traphaco.center.util.PhanTrang;
import geso.traphaco.center.util.Phan_Trang;
import geso.traphaco.center.util.Utility;
import geso.traphaco.center.util.DinhKhoanKeToan;
import geso.traphaco.center.util.IThongTinHienThi;
import geso.traphaco.center.util.ThongTinHienThi;
import geso.traphaco.erp.beans.thutien.IErpThutienCKList;

public class ErpThutienCKList  extends Phan_Trang  implements IErpThutienCKList
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	
	String nccId;
	ResultSet nppRs;
	String htttId;
	ResultSet htttRs;   
	String ctyId;
	String trangthai;
	String msg;
	String nguoisuaId;
	String nppdangnhap;
	
	ResultSet tthdRs;
	
	ResultSet nguoisuaRs;
	
	
	List<IThongTinHienThi> hienthiList;
	
	private int num;
	private int[] listPages;
	private int currentPages;
	
	dbutils db;
	Utility util;
	
	String Sochungtu="";
	String Sohoadon="";
	String sotien = "";
	
	String bangke="";
	String MaPhieu = "";
	
	String khId;
	ResultSet khRs;
	
	String nvId;
	ResultSet nvRs;
	
	String kbhId;
	ResultSet kbhRs;
	
	String nhomkhId;
	ResultSet nhomkhRs;
	
	String khohangId;
	ResultSet khohangRs;
	
	String nvgnId;
	ResultSet nvgnRs;
	
	String ghichu;
	String sobangke;
	
	String ctyTen;
	String diachi;
	String masothue;
	
	public ErpThutienCKList()
	{
		this.tungay = "";
		this.denngay = "";
		this.nccId = "";
		this.htttId = "";
		this.trangthai = "";
		this.nguoisuaId = "";
		this.msg = "";
		this.ctyId = "";
		this.khId = "";
		this.nvId = "";
		this.sotien ="";
		this.kbhId = "";
		this.nhomkhId = "";
		this.nppdangnhap = "";
		this.bangke= "";
		this.MaPhieu= "";
		this.khohangId = "";
		this.nvgnId = "";
		this.ghichu = "";	
		this.sobangke = "";
		this.ctyTen = "";
		this.diachi = "";
		this.masothue = "";
		
		currentPages = 1;
		num = 1;
		util=new Utility();
		this.db = new dbutils();
		
		this.hienthiList = new ArrayList<IThongTinHienThi>();
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getCtyId()
	{
		return this.ctyId;
	}

	public void setCtyId(String ctyId) 
	{
		this.ctyId = ctyId;
		
		String query = "SELECT TEN, DIACHI, MASOTHUE FROM ERP_CONGTY WHERE PK_SEQ = " + this.ctyId;
		
		ResultSet rs = this.db.get(query);
		
		try{
			if(rs != null) {
				rs.next();
				this.ctyTen = rs.getString("TEN");
				this.diachi = rs.getString("DIACHI");				
				this.masothue = rs.getString("MASOTHUE");
				
				rs.close();
			}
			
		}catch(java.sql.SQLException e){
			e.printStackTrace();
		}
	}

	public String getTungay()
	{
		return this.tungay;
	}

	public void setTungay(String tungay) 
	{
		this.tungay = tungay;
	}

	public String getDenngay()
	{
		return this.denngay;
	}

	public void setDenngay(String denngay) 
	{
		this.denngay = denngay;
	}

	public String getNccId() 
	{
		return this.nccId;
	}

	public void setNccId(String nccid) 
	{
		this.nccId = nccid;
	}

	public ResultSet getNccList() 
	{
		return this.nppRs;
	}

	public void setNccList(ResultSet ncclist) 
	{
		this.nppRs = ncclist;
	}

	public String getHtttId() 
	{
		return this.htttId;
	}

	public void setHtttId(String htttid) 
	{
		this.htttId = htttid;
	}

	public ResultSet getHtttList()
	{
		return this.htttRs;
	}

	public void setHtttList(ResultSet htttlist)
	{
		this.htttRs = htttlist;	
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}

	public void setmsg(String msg)
	{
		this.msg = msg;
	}

	public String getmsg() 
	{
		return this.msg;
	}

	public ResultSet getTThoadonList() 
	{
		return this.tthdRs;
	}

	public void setTThoadonList(ResultSet tthdlist) 
	{
		this.tthdRs = tthdlist;	
	}
	
	public int getNum()
	{
		return this.num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
		listPages = PhanTrang.getListPages(num);
	}
	
	public int getCurrentPage() 
	{
		return this.currentPages;
	}

	public void setCurrentPage(int current) 
	{
		this.currentPages = current;
	}

	public int[] getListPages() 
	{
		return this.listPages;
	}

	public void setListPages(int[] listPages) 
	{
		this.listPages = listPages;
	}

	public int getLastPage() 
	{
		ResultSet rs = db.get("select count(*) as c from ERP_thutien");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage)
	{
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}
	
	public void init(String search)
	{
		this.getNppInfo();   
		
		String query = "";
		
		this.nppRs = db.get("select pk_seq, ma + ', ' + isnull(Ten,'') as nppTen from KhachHang where trangthai = '1' AND congty_fk = "+this.ctyId);
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1'");
		this.nguoisuaRs = db.get("select pk_seq, ten from nhanvien where trangthai=1");
		this.khRs = db.get("select pk_Seq, ma + ', '+ ten as khTen from ERP_NHACUNGCAP where trangthai = 1");
		this.nvRs = db.get("select pk_seq, ma + ', '+ ten as nvTen from ERP_NHANVIEN where trangthai = 1");
		this.kbhRs =  db.get("select pk_seq, diengiai from KENHBANHANG where trangthai = 1");
		this.nhomkhRs = db.get("select pk_seq, diengiai ma from NHOMKHACHHANG where trangthai = 1");
		
		this.khohangRs=db.get("select pk_seq ,ten from kho where 1 = 1 and PK_SEQ IN ( " + util.quyen_kho(this.userId)+ ") " );
		
		/*query = "select pk_seq, ten from NHANVIENGIAONHAN a where trangthai = 1 and npp_fk IN ( "+this.nppdangnhap+" )"
				+ util.getPhanQuyen_TheoNhanVien("NHANVIENGIAONHAN", "a", "pk_seq", this.getLoainhanvien(), this.getDoituongId() );
		this.nvgnRs = db.get(query);*/
		
		if(search.length() <= 0)
		{
			query = " select a.pk_seq as tthdId,(isnull(a.prefix, 'BC') + cast(a.pk_seq as nvarchar(50))) sochungtu ,a.trangthai, a.ngaychungtu, a.ngayghiso, a.ngaytao, a.ngaysua, \n" +
					" case when b.ten is null and ncc.ten is null then '' \n" +
					" when b.ten is not null and ncc.ten is null then isnull(b.Ten,'') \n" +
					" when b.ten is null and ncc.ten is not null then isnull(ncc.ten,'') end as nppTen, \n" +
					"	c.ten as htttTen, \n" +
					" d.ten as nguoitao, e.ten as nguoisua, f.ten as noidungTen, ISNULL(a.ISKTTDUYET,0) ISKTTDUYET, \n" +
					" CASE WHEN a.TIENTE_FK = 100000 \n"+
					"      THEN (case when a.noidungtt_fk != 100002 then ISNULL(a.THUDUOC, 0) \n"+
					"                 else (select sum(sotien) from erp_thutien_dinhkhoanco where thutien_fk = a.pk_seq) end ) \n"+
					"      ELSE (case when a.noidungtt_fk != 100002 then ISNULL(a.THUDUOCNT, 0) \n"+
					"                 else (select sum(sotiennt) from erp_thutien_dinhkhoanco where thutien_fk = a.pk_seq) end ) \n"+
					" END AS THUCTHU, isnull(a.machungtu,'') machungtu \n" +
					" from ERP_THUTIEN a " +
					" INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = a.TIENTE_FK \n" +
					" left join KhachHang b on a.KHACHHANG_FK = b.pk_seq left join ERP_NhaCungCap ncc on a.NCC_FK = ncc.pk_seq \n" +
					" inner join ERP_HINHTHUCTHANHTOAN c on a.HTTT_FK = c.pk_seq \n" +
					" inner join ERP_NOIDUNGTHUTIEN f on a.noidungtt_fk = f.pk_seq \n" +
					" inner join NHANVIEN d on a.nguoitao = d.pk_seq  \n" +
					" inner join NHANVIEN e on a.nguoisua = e.pk_seq  \n"+
					" where a.congty_fk = "+this.ctyId+" and a.HTTT_FK = 100001";
		}
		else
		{
			query = search;
		}
 
		System.out.println("Init "+ query);
		String query_init = createSplittingData_ListNew(this.db, 25, 10, " tthdId desc", query) ;
		
		this.tthdRs = db.get(query_init);	
	}

	private String LayDuLieu(String id) {String query = " select noidungtt_fk, httt_fk, isnull(bangke_fk,0) bangke_fk from ERP_THUTIEN WHERE PK_SEQ = "+id;
	
	String noidungthutien  ="";
	String bangke_fk = "";
	String laykt = "";
	ResultSet psRs = db.get(query);
	try{
		psRs.next();
			noidungthutien = psRs.getString("noidungtt_fk");
			bangke_fk = psRs.getString("bangke_fk");
		psRs.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	
	if (!bangke_fk.equals("0")) // SỬ DỤNG BẢNG KÊ
	{
		query = "select tt.ngayghiso, tt.tiente_fk, tthd.sotienTT, tt.tiente_fk, isnull(tt.chietkhau,0) as chietkhau, \n"+
				" isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, \n"+
				" hd.tenkhachhang khachhang_fk, tt.httt_fk, nh.ma nganhang_fk, tt.chinhanh_fk, tt.noidungtt_fk ,\n"+
				" (select SOHIEUTAIKHOAN from ERP_TAIKHOANKT WHERE PK_SEQ = ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where Sotaikhoan = tt.sotaikhoan AND CONGTY_FK = "+ this.ctyId + " )) as taikhoanNO_SoHieu, \n"+
				" ( '13100000'  ) as taikhoanCO_KH_SoHieu,  \n"+
				" ( '11110000') HTTHANHTOAN, tthd.SOTIENTT SOTIENTT, isnull(tthd.xoachenhlech,0) xoachenhlech, isnull(tthd.tienchenhlech,0) tienchenhlech,   \n"+
				" ( nhomcp.TAIKHOAN_FK ) taikhoanNO_chietkhau, \n"+
				" ('71100000'  ) taikhoanCO_chietkhau \n"+
				"from erp_thutien tt inner join \n"+
				" ERP_THUTIEN_HOADON tthd on tt.pk_seq = tthd.thutien_fk \n"+
				" left join ERP_NHOMCHIPHI nhomcp on tthd.macp = nhomcp.PK_SEQ \n"+
				" inner join ERP_HOADONNPP hd on tthd.HOADON_FK = hd.PK_SEQ \n"+
				" inner join ERP_NGANHANG nh on tt.nganhang_fk = nh.PK_SEQ \n"+
				"where tt.pk_seq = '" + id + "'";

		System.out.println("1."+query);
		ResultSet thutien = db.get(query);

		String kh_fk = "";
		String nh_fk = "";
		String taikhoanNO_SoHieu = "";
		String taikhoanCO_KH_SoHieu = "";
		String taikhoanNO_CK_SoHieu = "";
		String taikhoanCO_CK_SoHieu = "";
		double xoachenhlech = 0;
		double tienchenhlech = 0;
					

		double sotienTT = 0;
		int i = 0;
		if (thutien != null) {
			try{
				while (thutien.next()) {
					kh_fk = thutien.getString("khachhang_fk");
					nh_fk = thutien.getString("nganhang_fk");
					sotienTT = thutien.getDouble("sotienTT");
					xoachenhlech = thutien.getDouble("xoachenhlech");
					tienchenhlech = thutien.getDouble("tienchenhlech");
					
					taikhoanNO_SoHieu = thutien.getString("taikhoanNO_SoHieu")== null ? "": thutien.getString("taikhoanNO_SoHieu") ;
					taikhoanCO_KH_SoHieu = thutien.getString("taikhoanCO_KH_SoHieu")== null ? "": thutien.getString("taikhoanCO_KH_SoHieu") ;
						
					taikhoanNO_CK_SoHieu = thutien.getString("taikhoanNO_chietkhau")== null ? "": thutien.getString("taikhoanNO_chietkhau") ;
					taikhoanCO_CK_SoHieu = thutien.getString("taikhoanCO_chietkhau")== null ? "": thutien.getString("taikhoanCO_chietkhau") ;
					
					
					String nam = thutien.getString("ngayghiso").substring(0, 4);
					String thang = thutien.getString("ngayghiso").substring(5, 7);
					String tiente_fk = thutien.getString("tiente_fk");

					// GHI NHAN SO TIEN THU DUOC					
					if (sotienTT > 0) {
						
						//TIEN THU DUOC
						if(laykt.trim().length()>0) laykt += " UNION ALL \n";
						
						laykt +=
						"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
						"       "+sotienTT+" SOTIEN, '"+nh_fk+"' DOITUONG	, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 1 SAPXEP \n"+
						"   FROM ERP_THUTIEN TT \n"+
						"   WHERE TT.PK_SEQ = '"+id+"' \n"+
		
						"   UNION ALL \n"+
		
						"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_KH_SoHieu+"' SOHIEUTAIKHOAN, \n"+
						"       "+sotienTT+" SOTIEN, N'"+kh_fk+"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 2 SAPXEP \n"+
						" 	FROM ERP_THUTIEN TT  \n"+
						"   WHERE TT.PK_SEQ = '"+id+"' \n";
						
						i++;
					}
					
					if(tienchenhlech>0 && xoachenhlech == 1)
					{
						
						if(laykt.trim().length()>0) laykt += " UNION ALL \n";
						
						laykt +=
								"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_CK_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+tienchenhlech+" SOTIEN, '' DOITUONG	, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 1 SAPXEP \n"+
								"   FROM ERP_THUTIEN TT \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n"+
				
								"   UNION ALL \n"+
				
								"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_KH_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+tienchenhlech+" SOTIEN, N'"+kh_fk+"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 2 SAPXEP \n"+
								" 	FROM ERP_THUTIEN TT  \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n";
						i++;
					}
					
					
					if(tienchenhlech<0 && xoachenhlech == 1)
					{
						tienchenhlech = tienchenhlech*(-1);
						if(laykt.trim().length()>0) laykt += " UNION ALL \n";
						
						laykt +=
								"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanCO_KH_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+tienchenhlech+" SOTIEN, N'"+kh_fk+"' DOITUONG	, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 1 SAPXEP \n"+
								"   FROM ERP_THUTIEN TT \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n"+
				
								"   UNION ALL \n"+
				
								"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_CK_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+tienchenhlech+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 2 SAPXEP \n"+
								" 	FROM ERP_THUTIEN TT  \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n";
						
						i++;
					}

				}

				thutien.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}
	else
	{
	
	query =  	"	SELECT tt.ngayghiso, tt.tiente_fk, tt.sotienTT, isnull(tt.thuduoc, 0) as thuduoc, isnull(chietkhau,0) as chietkhau, " +
				" 		   isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, tt.dinhkhoanco, tt.noidungtt_fk , " +
				" 		   tt.khachhang_fk, tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, kh.ma as maKH, tt.noidungtt_fk ," +
				" 		   (case when tt.NCC_FK is not null then (select SOHIEUTAIKHOAN from ERP_TAIKHOANKT where PK_SEQ = ncc.TAIKHOAN_FK )" +
				"       		 else (select SOHIEUTAIKHOAN from ERP_TAIKHOANKT where PK_SEQ = nv.TAIKHOAN_FK ) end ) as taikhoanCO_NCC_NV_SoHieu , " +
				" 			(case when tt.NCC_FK is not null then N'Nhà cung cấp' else N'Nhân viên' end )as DOITUONG_TU, " +
				" 			(case when tt.NCC_FK is not null then tt.NCC_FK else tt.NHANVIEN_FK  end)as NCC_NV_FK , " +
				" 			( select SOHIEUTAIKHOAN from ERP_TAIKHOANKT where pk_seq in ( select TaiKhoan_fk from ERP_NGANHANG_CONGTY where Sotaikhoan=tt.sotaikhoan )  ) as taikhoanNO_SoHieu, " +
				" 			( select SOHIEUTAIKHOAN from ERP_TAIKHOANKT where pk_seq  = kh.taikhoan_fk ) as taikhoanCO_KH_SoHieu, (isnull(kh.ma,'') + ' - ' +kh.ten) as TENKH " +
				"	FROM erp_thutien tt " +
				"		 left join KHACHHANG kh on TT.KHACHHANG_FK = KH.PK_SEQ  " +
				"		 left join ERP_NHACUNGCAP ncc on tt.NCC_FK = ncc.PK_SEQ " +
				"		 left join ERP_NHANVIEN nv on tt.NHANVIEN_FK = nv.PK_SEQ " +
				"	WHERE tt.pk_seq = '" + id + "'";
		
		
		
		String dtno="";
		String dtco="";
		String tenkh="";
		
		ResultSet psktRs = db.get(query);
		if(psktRs != null)
		{
			try{
				while(psktRs.next())
				{
					String khachhang_fk = psktRs.getString("khachhang_fk");
					tenkh = psktRs.getString("TENKH");
					String hinhthuctt = psktRs.getString("httt_fk");
					double tonggiatri = Math.round(psktRs.getDouble("thuduoc"));
					
					String nam = psktRs.getString("ngayghiso").substring(0, 4);
					String thang = psktRs.getString("ngayghiso").substring(5, 7);
					String tiente_fk = psktRs.getString("tiente_fk");
					noidungthutien = psktRs.getString("noidungtt_fk");
					
					String taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_KH_SoHieu") ;
					String taikhoanNO_SoHieu = "";
					
					if(hinhthuctt.equals("100001"))
					{
						taikhoanNO_SoHieu = psktRs.getString("taikhoanNO_SoHieu") == null ? "": psktRs.getString("taikhoanNO_SoHieu") ;
					}
					else
					{
						taikhoanNO_SoHieu = "11110000";
					}
					
					if( noidungthutien.equals("100000")|| noidungthutien.equals("100001")) // THU TIEN HÓA ĐƠN && KHACH HANG TRA TRUOC
					{
						//TIEN THU DUOC
						laykt =
						"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
						"       "+tonggiatri+" SOTIEN, (CASE TT.HTTT_FK WHEN 100001 THEN (select MA+' - '+TEN from ERP_NGANHANG where PK_SEQ = tt.NGANHANG_FK  ) " +
						" 											  ELSE '' END ) DOITUONG	, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 1 STT, 1 SAPXEP \n"+
						"   FROM ERP_THUTIEN TT LEFT JOIN KHACHHANG KH ON TT.KHACHHANG_FK = KH.PK_SEQ  \n"+
						"   WHERE TT.PK_SEQ = '"+id+"' \n"+
		
						"   UNION ALL \n"+
		
						"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
						"       "+tonggiatri+" SOTIEN, KH.TEN DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 1 STT, 2 SAPXEP \n"+
						" 	FROM ERP_THUTIEN TT LEFT JOIN KHACHHANG KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
						"   WHERE TT.PK_SEQ = '"+id+"' \n";
						 
						
						double phinganhang = Math.round(psktRs.getDouble("phinganhang"));
						
						if(phinganhang > 0){
							taikhoanNO_SoHieu = "64250000";
							
							if(laykt.trim().length()>0) laykt += " UNION ALL \n";
								
							laykt +=
							"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+phinganhang+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 2 STT, 1 SAPXEP \n"+
							"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n"+
			
							"   UNION ALL \n"+
			
							"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+phinganhang+" SOTIEN, KH.TEN DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 2 STT, 2 SAPXEP \n"+
							" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n";
							}
						
						//GHI NHAN SO TIEN CHENH LECH 
						double chenhlech = Math.round(psktRs.getDouble("chenhlech"));
						
						if(chenhlech != 0){
							if(chenhlech > 0)
							{
								if(tiente_fk.equals("100000")) // VND
								{	
									taikhoanNO_SoHieu = "81180000";
									taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_KH_SoHieu") ;
								}else{  // NGOAI TỆ
									 
									taikhoanNO_SoHieu = "63580000 ";
									taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_KH_SoHieu") ;							
								}
								
								chenhlech = Math.abs(chenhlech);
								
								if(laykt.trim().length()>0) laykt += " UNION ALL \n";
								
								laykt +=
								"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 3 STT, 1 SAPXEP \n"+
								"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n"+
				
								"   UNION ALL \n"+
				
								"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, (KH.MA +' - '+ KH.TEN) DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 3 STT, 2 SAPXEP \n"+
								" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n";
								
								
							}										
							else
							{
								chenhlech= chenhlech*(-1);
								if(tiente_fk.equals("100000")) // VND
								{
									taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_KH_SoHieu") ;
									taikhoanCO_SoHieu = "71180000"; 
								}else {   // NGOẠI TỆ
									
									taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_KH_SoHieu") ;
									taikhoanCO_SoHieu = "51580000"; 
								}
								
								//chenhlech = Math.abs(chenhlech);
								
								if(laykt.trim().length()>0) laykt += " UNION ALL \n";
								
								laykt +=
								"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, (KH.MA +' - '+ KH.TEN) DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 4 STT, 1 SAPXEP \n"+
								"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n"+
				
								"   UNION ALL \n"+
				
								"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 4 STT, 2 SAPXEP \n"+
								" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n";
								
							}
							
							
							
						}	
											
						//GHI NHAN TIEN CHIET KHAU (Dung cho Thu tien ban hang)
						double chietkhau = Math.round(psktRs.getDouble("chietkhau"));
						
						if(chietkhau > 0)
						{
							taikhoanNO_SoHieu = "64183000";
							taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_KH_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_KH_SoHieu") ;
							
							if(laykt.trim().length()>0) laykt += " UNION ALL \n";
							
							laykt +=
							"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+chietkhau+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 5 STT, 1 SAPXEP \n"+
							"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n"+
			
							"   UNION ALL \n"+
			
							"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+chietkhau+" SOTIEN, (KH.MA +' - '+ KH.TEN) DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 5 STT, 2 SAPXEP \n"+
							" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n";
							
						}
	
					}
					else if (noidungthutien.equals("100002"))// THU KHÁC
					{
						query = "SELECT TK.SOHIEUTAIKHOAN as TAIKHOAN_COSH, dkc.SOTIEN as SOTIEN, ISNULL(tt.PHINGANHANG,0) as PHINGANHANG, \n"+
								"       CASE WHEN tt.HTTT_FK = 100000 THEN '11110000' \n"+
								"            ELSE (select b.SOHIEUTAIKHOAN from ERP_NGANHANG_CONGTY a inner join ERP_TAIKHOANKT b on a.TAIKHOAN_FK = b.PK_SEQ where a.SoTaiKhoan = tt.SoTaiKhoan) \n"+
								"       END AS TAIKHOAN_NOSH, '64250000' as TAIKHOAN_COSH_PHINH, \n"+
								"       CASE WHEN tt.HTTT_FK = 100000 THEN '' ELSE NH.MA + ' - '+ NH.TEN END AS MADOITUONGNO, \n"+							
								"       CASE WHEN dkc.DOITUONGDINHKHOAN = 1 THEN (select MA + ' - ' + TEN from SANPHAM where pk_seq = dkc.DOITUONG_FK) \n"+
								"            WHEN dkc.DOITUONGDINHKHOAN = 2 THEN (select MA + ' - ' + TEN from ERP_NGANHANG where pk_seq = dkc.DOITUONG_FK)  \n"+
								"            WHEN dkc.DOITUONGDINHKHOAN = 3 THEN (select MA + ' - ' + TEN from ERP_NHACUNGCAP where pk_seq = dkc.DOITUONG_FK)  \n"+
								"            WHEN dkc.DOITUONGDINHKHOAN = 4 THEN (select MA + ' - ' + TEN from ERP_TAISANCODINH where pk_seq = dkc.DOITUONG_FK)  \n"+
								"            WHEN dkc.DOITUONGDINHKHOAN = 5 THEN (select MA + ' - ' + TEN from khachhang where pk_seq = dkc.DOITUONG_FK)  \n"+
								"            WHEN dkc.DOITUONGDINHKHOAN = 6 THEN (select MA + ' - ' + TEN from ERP_NHANVIEN where pk_seq = dkc.DOITUONG_FK)  \n"+
								"            ELSE '' \n"+
								"       END AS MADOITUONGCO \n"+
								"FROM ERP_THUTIEN_DINHKHOANCO dkc INNER JOIN ERP_THUTIEN tt ON dkc.THUTIEN_FK = tt.PK_SEQ \n"+
								"     LEFT JOIN ERP_NGANHANG NH ON tt.NGANHANG_FK = NH.PK_SEQ \n"+
								"     INNER JOIN ERP_TAIKHOANKT TK ON dkc.TAIKHOAN_FK = TK.PK_SEQ \n"+
								"WHERE tt.PK_SEQ = "+ id +" ";
						
						ResultSet rsDKC = db.get(query);
						int i = 1;
						
						if(rsDKC != null)
						{
							while(rsDKC.next())
							{
								 taikhoanCO_SoHieu= rsDKC.getString("TAIKHOAN_COSH") == null ? "" : rsDKC.getString("TAIKHOAN_COSH");
								 taikhoanNO_SoHieu= rsDKC.getString("TAIKHOAN_NOSH") == null ? "" : rsDKC.getString("TAIKHOAN_NOSH");
								
								 double sotienco = Math.round(rsDKC.getDouble("SOTIEN"));
								 double phinganhang = Math.round(rsDKC.getDouble("phinganhang"));
								   							 							 
								if(sotienco >0)
								{															
									if(laykt.trim().length()>0) laykt += " UNION ALL \n";
									
									laykt +=
									"	SELECT distinct N'NỢ' NO_CO, "+ id +" AS PK_SEQ,  '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
									"       "+sotienco+" SOTIEN, '"+  rsDKC.getString("MADOITUONGNO") +"'  DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+ i +"  STT, 1 SAPXEP \n"+
					
									"   UNION ALL \n"+
					
									"   SELECT distinct N'CÓ' NO_CO, "+ id +" AS PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
									"       "+sotienco+" SOTIEN, '"+  rsDKC.getString("MADOITUONGCO") +"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+ i +"  STT, 2 SAPXEP \n";
	
								}
								
								//GHI NHAN SO TIEN PHI NGAN HANG
								
								if(phinganhang > 0)
								{
									taikhoanCO_SoHieu= rsDKC.getString("TAIKHOAN_COSH_PHINH") == null ? "" : rsDKC.getString("TAIKHOAN_COSH_PHINH");
									
									if(laykt.trim().length()>0) laykt += " UNION ALL \n";
									
									laykt +=
									"	SELECT distinct N'NỢ' NO_CO, "+ id +" AS PK_SEQ,  '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
									"       "+phinganhang+" SOTIEN, '"+  rsDKC.getString("MADOITUONGNO") +"'  DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+ i +"  STT, 3 SAPXEP \n"+
					
									"   UNION ALL \n"+
					
									"   SELECT distinct  N'CÓ' NO_CO, "+ id +" AS PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
									"       "+phinganhang+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+ i +"  STT, 4 SAPXEP \n";
	
								}
						
						 i ++;
					   } rsDKC.close();
				      } 
						
					}
					else if (noidungthutien.equals("100003")){
						
						if(hinhthuctt.equals("100001")) //thanh toan NGANHANG (CHUYEN KHOAN)
						{
							taikhoanNO_SoHieu = psktRs.getString("taikhoanNO_SoHieu") == null ? "" : psktRs.getString("taikhoanNO_SoHieu");
						}
						else
						{
							taikhoanNO_SoHieu = "11110000";
						}
						taikhoanCO_SoHieu =  psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_NCC_NV_SoHieu");
						
						//GHI NHAN SO TIEN THU DUOC
						
						if(tonggiatri > 0)
						{
							if(laykt.trim().length()>0) laykt += " UNION ALL \n";
							
							laykt +=
							"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+tonggiatri+" SOTIEN, (CASE TT.HTTT_FK WHEN 100001 THEN (select MA+' - '+TEN from ERP_NGANHANG where PK_SEQ=tt.NGANHANG_FK  ) " +
							" 											  ELSE '' END ) DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 8 STT, 1 SAPXEP \n"+
							"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n"+
			
							"   UNION ALL \n"+
			
							"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+tonggiatri+" SOTIEN, (CASE WHEN TT.NCC_FK IS NOT NULL THEN (select MA+' - '+TEN from ERP_NHACUNGCAP where PK_SEQ=tt.NCC_FK  ) " +
							" 											  ELSE (select MA+' - '+TEN from ERP_NHANVIEN where PK_SEQ=tt.NHANVIEN_FK  ) END ) DOITUONG , '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 8 STT, 2 SAPXEP \n"+
							" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n";
						}
						
						//GHI NHAN SO TIEN PHI NGAN HANG
						double phinganhang = Math.round(psktRs.getDouble("phinganhang"));
						
						if(phinganhang > 0)
						{
							taikhoanNO_SoHieu = "64250000";
							
							if(laykt.trim().length()>0) laykt += " UNION ALL \n";
							
							laykt +=
							"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+tonggiatri+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU,9 STT, 1 SAPXEP \n"+
							"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n"+
			
							"   UNION ALL \n"+
			
							"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
							"       "+tonggiatri+" SOTIEN, (CASE TT.HTTT_FK WHEN 100001 THEN (select MA+' - '+TEN from ERP_NGANHANG where PK_SEQ=tt.NGANHANG_FK  ) " +
							" 											  ELSE '' END ) DOITUONG , '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 9 STT, 2 SAPXEP \n"+
							" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
							"   WHERE TT.PK_SEQ = '"+id+"' \n";
						}
						
						//GHI NHAN SO TIEN CHENH LECH
						double chenhlech = Math.round(psktRs.getDouble("chenhlech"));
						
						if(chenhlech != 0)
						{
							if(chenhlech > 0)
							{
								if(tiente_fk.equals("100000"))  // VND
								{
									taikhoanNO_SoHieu = "81180000";
									taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_NCC_NV_SoHieu") ;
								}else {   // NGOẠI TỆ
								
									taikhoanNO_SoHieu = "63580000";
									taikhoanCO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_NCC_NV_SoHieu") ;
								
								}
								
								chenhlech = Math.abs(chenhlech);
								
								if(laykt.trim().length()>0) laykt += " UNION ALL \n";
								
								laykt +=
								"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 10 STT, 1 SAPXEP \n"+
								"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n"+
				
								"   UNION ALL \n"+
				
								"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, (CASE WHEN TT.NCC_FK IS NOT NULL THEN (select MA+' - '+TEN from ERP_NHACUNGCAP where PK_SEQ=tt.NCC_FK  ) " +
								" 											  ELSE (select MA+' - '+TEN from ERP_NHANVIEN where PK_SEQ=tt.NHANVIEN_FK ) END ) DOITUONG , '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU,10 STT, 2 SAPXEP \n"+
								" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n";
								
							}
							else
							{
								chenhlech= chenhlech*(-1);						
								if(tiente_fk.equals("100000"))  // VND
								{
									taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_NCC_NV_SoHieu") ;
									taikhoanCO_SoHieu = "71180000";
								}else {
									
									taikhoanNO_SoHieu = psktRs.getString("taikhoanCO_NCC_NV_SoHieu") == null ? "" : psktRs.getString("taikhoanCO_NCC_NV_SoHieu") ;
									taikhoanCO_SoHieu = "51580000";								
								}
								
								chenhlech = Math.abs(chenhlech);
								
								if(laykt.trim().length()>0) laykt += " UNION ALL \n";
								
								laykt +=
								"	SELECT N'NỢ' NO_CO,  TT.PK_SEQ, '"+taikhoanNO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, (CASE WHEN TT.NCC_FK IS NOT NULL THEN (select MA+' - '+TEN from ERP_NHACUNGCAP where PK_SEQ=tt.NCC_FK  ) " +
								" 											  ELSE (select MA+' - '+TEN from ERP_NHANVIEN where PK_SEQ=tt.NHANVIEN_FK  ) END ) DOITUONG , '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 11 STT, 1 SAPXEP \n"+
								"   FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n"+
				
								"   UNION ALL \n"+
				
								"   SELECT N'CÓ' NO_CO, TT.PK_SEQ, '"+taikhoanCO_SoHieu+"' SOHIEUTAIKHOAN, \n"+
								"       "+chenhlech+" SOTIEN, '' DOITUONG , '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 11 STT, 2 SAPXEP \n"+
								" 	FROM ERP_THUTIEN TT LEFT JOIN khachhang KH ON TT.KHACHHANG_FK = KH.PK_SEQ  \n"+
								"   WHERE TT.PK_SEQ = '"+id+"' \n";
								
							}
						}
						
						
					}
				}
				psktRs.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
		if(laykt.trim().length()>0) laykt += " ORDER BY PK_SEQ, STT, SAPXEP \n";
		
		if(laykt.trim().length()<=0){
			laykt += " SELECT '' NO_CO, '' PK_SEQ, '' SOHIEUTAIKHOAN, '' SOTIEN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 1 STT, 1 SAPXEP " +
					 " FROM ERP_THUTIEN " +
					 " WHERE PK_SEQ ='"+id+"'";
		}
	
		return laykt;
	}

	public void DBclose() 
	{
		
			try {
				if(this.nppRs != null)
					this.nppRs.close();
				
				if(this.htttRs != null)
					this.htttRs.close();
				
				if(this.tthdRs != null)
					this.tthdRs.close();
				this.db.shutDown();		
				
			} catch (SQLException e) {e.printStackTrace();
}
		
		
	}

	
	
	public String getsochungtu() {
		
		return this.Sochungtu;
	}

	
	public void setsochungtu(String sochungtu) {
		
		this.Sochungtu=sochungtu;
	}

	
	public String getsohoadon() {
		
		return this.Sohoadon;
	}

	
	public void setsohoadon(String sohoadon) {
		
		this.Sohoadon=sohoadon;
	}

	public ResultSet getNguoisuaRs() 
	{
		return this.nguoisuaRs;
	}

	public void setNguoisuaRs(ResultSet nguoisuaRs) 
	{
		this.nguoisuaRs = nguoisuaRs;
	}

	public String getNguoisuaId()
	{
		return this.nguoisuaId;
	}

	public void setNguoisuaId(String nguoisuaId) 
	{
		this.nguoisuaId = nguoisuaId ;
	}

	public List<IThongTinHienThi> getHienthiList() 
	{
		return this.hienthiList;
	}

	public void setHienthiList(List<IThongTinHienThi> hienthiList) 
	{
		this.hienthiList = hienthiList;
	}

	
	public String getKhId() {
		
		return this.khId;
	}

	
	public void setKhId(String khid) {
		
		this.khId = khid;
	}

	
	public ResultSet getKhList() {
		
		return this.khRs;
	}

	
	public void setKhList(ResultSet khrs) {
		
		this.khRs = khrs; 
	}

	
	public String getNvId() {
		
		return this.nvId;
	}

	
	public void setNvId(String nvid) {
		
		this.nvId = nvid;
	}

	
	public ResultSet getNvList() {
		
		return this.nvRs;
	}

	
	public void setNvList(ResultSet nvrs) {
		
		this.nvRs = nvrs;
	}

	
	public String getSotien() {
		
		return this.sotien;
	}

	
	public void setSotien(String sotien) {
		
		this.sotien = sotien;
	}

	
	public String getKbhId() {
		
		return this.kbhId;
	}

	
	public void setKbhId(String kbhid) {
		
		this.kbhId = kbhid;
	}

	
	public ResultSet getKbhRs() {
		
		return this.kbhRs;
	}

	
	public void setKbhRs(ResultSet kbhrs) {
		
		this.kbhRs = kbhrs;
	}

	
	public String getNhomkhId() {
		
		return this.nhomkhId;
	}

	
	public void setNhomkhId(String nhomkhid) {
		
		this.nhomkhId = nhomkhid;
	}

	
	public ResultSet getNhomkhRs() {
		
		return this.nhomkhRs;
	}

	
	public void setNhomkhRs(ResultSet Nhomkhrs) {
		
		this.nhomkhRs = Nhomkhrs;
	}

	
	public String getnppdangnhap() {
		
		return this.nppdangnhap;
	}

	
	public void setnppdangnhap(String nppdangnhap) {
		
		this.nppdangnhap = nppdangnhap;
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.traphaco.distributor.util.Utility util=new geso.traphaco.distributor.util.Utility();
		this.nppdangnhap=util.getIdNhapp(this.userId);
	}

	
	public String getBangKe() {
		
		return this.bangke;
	}

	
	public void setBangke(String bangke) {
		
		this.bangke = bangke;
	}

	
	public String getMaPhieu() {
		
		return this.MaPhieu;
	}

	
	public void setMaPhieu(String MaPhieu) {
		
		this.MaPhieu = MaPhieu;
	}
	
	Object loainhanvien;
	Object doituongId;
	public String getLoainhanvien() 
	{
		if( this.loainhanvien == null )
			return "";
		
		return this.loainhanvien.toString();
	}

	public void setLoainhanvien(Object loainhanvien) 
	{
		this.loainhanvien = loainhanvien;
	}
	
	public String getDoituongId() 
	{
		if( this.doituongId == null )
			return "";
		
		return this.doituongId.toString();
	}

	public void setDoituongId(Object doituongId) 
	{
		this.doituongId = doituongId;
	}
	
public String getKhohangId() {
		
		return this.khohangId;
	}

	
	public void setKhohangId(String khohangid) {
		
		this.khohangId = khohangid;
	}

	
	public ResultSet getKhohangRs() {
		
		return this.khohangRs;
	}

	
	public void setKhohangRs(ResultSet khohangrs) {
		
		this.khohangRs = khohangrs;
	}

	
	public String getNvgnId() {
		
		return this.nvgnId;
	}

	
	public void setNvgnId(String Nvgnid) {
		
		this.nvgnId = Nvgnid;
	}

	
	public ResultSet getNvgnRs() {
		
		return nvgnRs;
	}

	
	public void setNvgnRs(ResultSet nvgnrs) {
		
		this.nvgnRs = nvgnrs;
	}
	
	public String getGhichu() {
		
		return this.ghichu;
	}
	
	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
		
	}
	
	public String getsobangke() {
		
		return this.sobangke;
	}

	
	public void setsobangke(String sobangke) {
		
		this.sobangke = sobangke;
	}
	
	public String getCtyTen() {
		
		return this.ctyTen;
	}

	
	public String getDiachi() {
	
		return this.diachi;
	}

	
	public String getMasothue() {
	
		return this.masothue;
	}
	
}
