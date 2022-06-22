package geso.dms.erp.beans.lenhsanxuat.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import AI.E;

import geso.dms.center.util.Utility;
import geso.dms.center.util.Utility_Kho;
import geso.dms.erp.db.sql.dbutils;
import geso.dms.erp.beans.danhmucvattu.IDanhmucvattu_SP;
import geso.dms.erp.beans.danhmucvattu.imp.Danhmucvattu_SP;
import geso.dms.erp.beans.lenhsanxuat.IErpLenhsanxuat;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;
import geso.dms.erp.beans.phieuxuatkho.imp.SpDetail;

public class ErpLenhsanxuat implements IErpLenhsanxuat 
{
	private static final long serialVersionUID = 1L;
	String congtyId;
	String userId;
	String id;
	String ngaytao;
	String ngaydukien;
	String BomId="";
	ResultSet RsBom;
	String kho;
	/**
	 * @return the kho
	 */
	public String getKho() {
		return kho;
	}

	/**
	 * @param kho the kho to set
	 */
	public void setKho(String kho) {
		this.kho = kho;
	}

	/**
	 * @return the sanpham
	 */
	public String getSanPham() {
		return sanpham;
	}

	/**
	 * @param sanpham the sanpham to set
	 */
	public void setSanPham(String sanpham) {
		this.sanpham = sanpham;
	}

	String sanpham;

	String soluongsx;
	String PO;
	String msg;
	String trangthai;
	String Ngaytieuhao;
	
	String khoId;
	ResultSet khoRs;
	
	ResultSet spRs;
	String spId;
	
	ResultSet kbsxRs;
	String kbsxId;
	
	ResultSet nhamayRs;
	String nhamayId;
	
	String viewBom;
	String soluongchuan;
	String cpTT;
	ResultSet rsnhapkho;
	String nhapkhoid;
	String TieuHaoId;
	List<IDanhmucvattu_SP> dmvtList;
	
	ResultSet chitietNlRs;
	String Soluongnhapkho;
	dbutils db;
	NumberFormat formatter1 = new DecimalFormat("######.###");
	public ErpLenhsanxuat()
	{
		this.id = "";
		this.Soluongnhapkho="0";
		this.ngaytao = "";
		this.ngaydukien = "";
		this.Ngaytieuhao="";
		this.soluongsx = "";
		this.msg = "";
		this.khoId = "";
		this.PO = "";
		
		this.spId = "";
		this.kbsxId = "";
		this.TieuHaoId="";
		this.nhamayId = "";
		this.nhapkhoid="";
		
		this.viewBom = "0";
		this.soluongchuan = "";
		this.cpTT = "0";
		this.trangthai = "0";
		
		this.dmvtList = new ArrayList<IDanhmucvattu_SP>();
		
		this.db = new dbutils();
	}
	
	public ErpLenhsanxuat(String id)
	{
		this.id = id;
		this.ngaytao = "";
		this.ngaydukien = "";
		this.soluongsx = "";
		this.nhapkhoid="";
		this.Ngaytieuhao="";
		this.msg = "";
		this.khoId = "";
		this.PO = "";
		this.spId = "";
		this.kbsxId = "";
		this.nhamayId = "";
		this.viewBom = "0";
		this.soluongchuan = "";
		this.TieuHaoId="";
		this.cpTT = "0";
		this.trangthai = "0";
		this.Soluongnhapkho="0";
		
		this.dmvtList = new ArrayList<IDanhmucvattu_SP>();
		
		this.db = new dbutils();
	}

	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String Id) 
	{
		this.id = Id;
	}

	public String getNgaytao() 
	{
		return this.ngaytao;
	}

	public void setNgaytao(String ngaytao) 
	{
		this.ngaytao = ngaytao;
	}

	public String getNgaydukien() 
	{
		return this.ngaydukien;
	}

	public void setNgaydukien(String ngaydk)
	{
		this.ngaydukien = ngaydk;
	}

	public String getSoluong() 
	{
		return this.soluongsx;
	}

	public void setSoluong(String soluong)
	{
		this.soluongsx  =soluong;
	}

	public String getKhottId()
	{
		return this.khoId;
	}

	public void setKhottId(String khott) 
	{
		this.khoId = khott;
	}

	public ResultSet getKhoTTRs()
	{
		return this.khoRs;
	}

	public void setKhoTTRs(ResultSet khottRs)
	{
		this.khoRs = khottRs;
	}

	public String getmsg() 
	{
		return this.msg;
	}

	public void setmsg(String msg) 
	{
		this.msg = msg;	
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getTime()
	{
		Date date = new Date();
	    SimpleDateFormat simpDate;

	    //format 24h
	    simpDate = new SimpleDateFormat("kk:mm:ss");
	    
	    return simpDate.format(date);
	}
	
	public boolean createLsx() 
	{
		if(this.ngaytao.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày bắt đầu";
			return false;
		}
		
		if(this.ngaydukien.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày dự kiến hoàn thành";
			return false;
		}
		
		if(this.spId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn sản phẩm";
			return false;
		}
		
		/*if(this.kbsxId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn kịch bản sản xuất";
			return false;
		}*/
		
		if(this.soluongsx.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số lượng sản xuất";
			return false;
		}
		
		if(this.dmvtList.size() <= 0)
		{
			this.msg = "Không thể tải thông tin danh mục vật tư. Vui lòng kiểm tra lại BOM của sản phẩm.";
			return false;
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "insert ERP_LENHSANXUAT(BOM_FK,KHO_FK,sanpham_fk, ngaysanxuat, ngaybatdau, ngaydukienHT, soluong, trangthai, ngaytao, nguoitao, ngaysua, nguoisua) " +
							"values("+this.BomId+","+this.khoId+",'" + this.spId + "', '" + this.ngaytao + "', '" + this.ngaytao + "', '" + this.ngaydukien + "', '" +  this.soluongsx+ "', '0', '" + getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "', '" + this.userId + "') "; 
							
			if(!db.update(query))
			{
				this.msg = "Không thể tạo mới lệnh sản xuất: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//Cho nao cho phep chinh sua Bom ---> Tao table ERP_LENHSANXUAT_BOM
			if(this.dmvtList.size() > 0)
			{
				for(int i = 0; i < this.dmvtList.size(); i++)
				{
					IDanhmucvattu_SP vattu = this.dmvtList.get(i);
					
					if(vattu.getMaVatTu().trim().length() > 0 && vattu.getSoLuong().trim().length() > 0)
					{
					 
							query = " insert Erp_LenhSanXuat_Bom(lenhsanxuat_fk, soluongchuan, chophepthaythe, VATTU_FK, SOLUONG)  " +
									" select IDENT_CURRENT('Erp_LenhSanXuat'), '" + this.soluongchuan + "', '" + this.cpTT + "', pk_seq, '" + vattu.getSoLuong() + "' " +
									" from SanPham where ma =  '" + vattu.getMaVatTu()+ "' ";
						  
						System.out.println("___ Insert Erp_LenhSanXuat_Bom: " + query);
						
						if(db.updateReturnInt( query) <1)
						{
							this.msg = "Không thể tạo mới Erp_LenhSanXuat_Bom: " + query;
							db.getConnection().rollback();
							return false;
						}
						
					}
				}
			}
			else
			{
			 /*
				query = "insert ERP_LENHSANXUAT_BOM(LENHSANXUAT_FK, SOLUONGCHUAN, CHOPHEPTHAYTHE, VATTU_FK, SOLUONG, VATTUTT_FK, SOLUONGTT, TILE)  " +
		  				"select IDENT_CURRENT('ERP_LENHSANXUAT'), a.SOLUONGCHUAN, a.CHOPHEPTT, b.VATTU_FK, b.SOLUONG * " + this.soluongsx + " / a.SOLUONGCHUAN, b.VATTUTT_FK, b.SOLUONGTT, b.TILE   " +
		  				"from ERP_DANHMUCVATTU a inner join ERP_DANHMUCVATTU_VATTU b on a.PK_SEQ = b.DANHMUCVT_FK  " +
		  				"where a.PK_SEQ = ( select bom_fk from ERP_KICHBANSANXUAT where pk_seq = '" + this.kbsxId + "' ) ";
		  		
				if(!db.update(query))
				{
					this.msg = "Không thể tạo mới ERP_LENHSANXUAT_BOM: " + query;
					db.getConnection().rollback();
					return false;
				}*/
				this.msg = "Không thể có nguyên liệu chọn để tạo lệnh sản xuất ";
				db.getConnection().rollback();
				return false;
				
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			return true;
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "Không thể tạo mới lệnh sản xuất: " + e.getMessage();
			return false;
		}
		
	}

	public boolean updateLsx() 
	{
		if(this.ngaytao.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày bắt đầu";
			return false;
		}
		
		if(this.ngaydukien.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày dự kiến hoàn thành";
			return false;
		}
		
		if(this.spId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn sản phẩm";
			return false;
		}
		
		 
		if(this.soluongsx.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số lượng sản xuất";
			return false;
		}
		
		if(this.dmvtList.size() <= 0)
		{
			this.msg = "Không thể tải thông tin danh mục vật tư. Vui lòng kiểm tra lại kịch bản sản xuất.";
			return false;
		}	
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "Update ERP_LENHSANXUAT set BOM_FK ="+this.BomId+",KHO_FK="+this.khoId+", kichbansanxuat_fk = null, sanpham_fk = '" + this.spId + "', ngaybatdau = '" + this.ngaytao + "', ngaydukienHT = '" + this.ngaydukien + "', " +
					"soluong = '" + this.soluongsx + "', nhamay_fk = null, ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "' where pk_seq = '" + this.id + "' ";
							 			
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật lệnh sản xuất: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			if(this.dmvtList.size() > 0)
			{
				query = "delete Erp_LenhSanXuat_Bom where lenhsanxuat_fk = '" + this.id + "'";
				if(!db.update(query))
				{
					this.msg = "Không thể cập nhật Kichbansanxuat: " + query;
					db.getConnection().rollback();
					return false;
				}
				
				for(int i = 0; i < this.dmvtList.size(); i++)
				{
					IDanhmucvattu_SP vattu = this.dmvtList.get(i);
					
					if(vattu.getMaVatTu().trim().length() > 0 && vattu.getSoLuong().trim().length() > 0)
					{
						 
							query = " insert Erp_LenhSanXuat_Bom(lenhsanxuat_fk, soluongchuan, chophepthaythe, VATTU_FK, SOLUONG)  " +
								  	" select "+ this.id+", '" + this.soluongchuan + "', '" + this.cpTT + "', pk_seq, '" + vattu.getSoLuong() + "' " +
									" from SanPham where ma = N'" + vattu.getMaVatTu()+ "' ";
 
							if(db.updateReturnInt(query) <1)
							{
								this.msg = "Không thể tạo mới Kichbansanxuat: " + query;
								db.getConnection().rollback();
								return false;
							}
						
					}
				}
			}else{
				this.msg = "Không có nguyên liệu để tạo lệnh sản xuất";
				db.getConnection().rollback();
				return false;
			}
 
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			return true;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			this.msg = "Không thể tạo mới lệnh sản xuất: " + e.getMessage();
			return false;
		}
	}

	
	public void createRs()
	{
		String sql;
		this.spRs = db.get("select pk_seq, ma + ', ' + ten as ten from SanPham where trangthai = '1' and loaisanpham_fk in ( 100005, 100011, 100012 ) ");
 
		if(this.id != null && this.id.trim().length() > 0) {
			sql=" SELECT PK_SEQ,NGAYNHAPKHO  FROM ERP_NHAPKHO  WHERE PK_SEQ NOT IN  "+
				" ( "+
				"  SELECT B.NHAPKHO_FK FROM ERP_LENHSANXUAT_GIAY A "+   
				"  INNER JOIN ERP_TIEUHAONGUYENLIEU TH ON TH.LENHSANXUAT_FK=A.PK_SEQ "+
				"  INNER JOIN  ERP_LSXTIEUHAO_NHAPKHO B ON TH.PK_SEQ=B.TIEUHAO_FK "+
				"  WHERE A.PK_SEQ="+this.id+"  AND TH.TRANGTHAI =1 "+
				"  ) AND SOLENHSANXUAT=" + this.id;
			//System.out.println("[ErpLenhsanxuat.createRs] sql = " + sql);
			this.rsnhapkho = db.get(sql);
		}
 
		sql="select PK_SEQ,TEN from erp_khott where LOAI=5";
		this.khoRs=this.db.get(sql);
		if(this.spId!=null && this.spId.length() >0){
			sql=" select  a.PK_SEQ, a.DIENGIAI +'-'+ sp.MA as TEN  from ERP_DANHMUCVATTU a inner join SANPHAM sp on sp.PK_SEQ= a.SANPHAM_FK  " +
					"  where a.TRANGTHAI='1' AND a.SANPHAM_FK=   "+spId;
			System.out.println("view BOm "+sql);
			this.RsBom=db.get(sql);
		}
	}

	
	private String getNgayDuKien(String ngaybatdau, float sogio) 
	{
		//ngay lam 8h, bat dau tinh tu 8h AM
		int ngay = Math.round( sogio / 8 );
		Calendar c1 = Calendar.getInstance();
		
		String[] arr = ngaybatdau.split("-");
		c1.set(Integer.parseInt( arr[0]), Integer.parseInt( arr[1]) - 1, Integer.parseInt( arr[2]) );
		
		//System.out.println("___Date ngaybatdau: " + c1.get(Calendar.DAY_OF_WEEK) );
		
		c1.add(Calendar.DATE, ngay);
		
		Calendar c2 = Calendar.getInstance();
		c2.set(Integer.parseInt( arr[0]), Integer.parseInt( arr[1]) - 1, Integer.parseInt( arr[2]) );
		
		while( c2.getTime().compareTo(c1.getTime()) < 0 )
		{
			//neu la ngay chu nhat thi phai tang 1 len 1 ngay
			if(c2.get(Calendar.DAY_OF_WEEK) == 8)
				c1.add(Calendar.DATE, 1);
			
			c2.add(Calendar.DATE, 1);
		}
		
		//System.out.println("___Date ngaydukienHT: " + c1.get(Calendar.DAY_OF_WEEK) );
		
		String ngaykt = Integer.toString(c1.get(Calendar.DATE));
		if(ngaykt.trim().length() < 2)
			ngaykt = "0" + ngaykt;
		
		String thangkt = Integer.toString(c1.get(Calendar.MONTH) + 1);
		if(thangkt.trim().length() < 2)
			thangkt = "0" + thangkt;
		
		System.out.println("Ngay du kien tinh duoc: " + Integer.toString(c1.get(Calendar.YEAR)) + "-" + thangkt + "-" + ngaykt);
		return Integer.toString(c1.get(Calendar.YEAR)) + "-" + thangkt + "-" + ngaykt;
	}
	
	private String getNgayBatDau(String ngayhoanthanh, float sogio) 
	{
		//ngay lam 8h, bat dau tinh tu 8h AM
		int ngay = Math.round( sogio / 8 );
		Calendar c1 = Calendar.getInstance();
		
		String[] arr = ngayhoanthanh.split("-");
		c1.set(Integer.parseInt( arr[0]), Integer.parseInt( arr[1]) - 1, Integer.parseInt( arr[2]) );
		
		c1.add(Calendar.DATE, (-1) * ngay);
		

		Calendar c2 = Calendar.getInstance();
		c2.set(Integer.parseInt( arr[0]), Integer.parseInt( arr[1]) - 1, Integer.parseInt( arr[2]) );
	
		while( c2.getTime().compareTo(c1.getTime()) > 0 )
		{
			//neu la ngay chu nhat thi phai tang 1 len 1 ngay
			if(c2.get(Calendar.DAY_OF_WEEK) == 8 || c2.get(Calendar.DAY_OF_WEEK) == 1)
			{
				c1.add(Calendar.DATE, -1);
			}
			
			c2.add(Calendar.DATE, -1);
		}
		
		String ngaykt = Integer.toString(c1.get(Calendar.DATE));
		if(ngaykt.trim().length() < 2)
			ngaykt = "0" + ngaykt;
		
		String thangkt = Integer.toString(c1.get(Calendar.MONTH) + 1);
		if(thangkt.trim().length() < 2)
			thangkt = "0" + thangkt;
		
		//System.out.println("___Date ngay bat dau: " + c1.get(Calendar.DAY_OF_WEEK) );
		System.out.println("1.Ngay bat dau tinh duoc: " + Integer.toString(c1.get(Calendar.YEAR)) + "-" + thangkt + "-" + ngaykt);
		
		return Integer.toString(c1.get(Calendar.YEAR)) + "-" + thangkt + "-" + ngaykt;
	}
	
	public static void main(String[] arg)
	{
		ErpLenhsanxuat lsx = new ErpLenhsanxuat();
		lsx.getNgayDuKien("2013-01-13", 32);
	}

	public void init()
	{
		String query = "SELECT ISNULL(BOM_FK,0) AS BOM_FK , \n" +
		"	ERP_LENHSANXUAT.kho_fk,\n" +
		"	ERP_LENHSANXUAT.trangthai,\n" +
		"	ISNULL(ERP_LENHSANXUAT.lenhsanxuatdukien_fk, '-1') AS PO,\n" +
		"	ERP_LENHSANXUAT.kichbansanxuat_fk,\n" +
		"	ERP_LENHSANXUAT.sanpham_fk,\n" +
		"	ERP_LENHSANXUAT.ngaybatdau,\n" +
		"	isnull(ERP_LENHSANXUAT.ngaydukienHT, '') AS ngaydukienHT,\n" +
		"	ERP_LENHSANXUAT.soluong,\n" +
		"	ERP_LENHSANXUAT.nhamay_fk,\n" +
		"	ERP_KHOTT.TEN AS KHOTT,\n" +
		"	SANPHAM.TEN AS SANPHAM\n" +
		"FROM\n" +
		"	ERP_LENHSANXUAT \n" +
		"	left join ERP_KHOTT ON ERP_KHOTT.PK_SEQ = ERP_LENHSANXUAT.KHO_FK\n" +
		"	LEFT JOIN SANPHAM ON ERP_LENHSANXUAT.SANPHAM_FK = SANPHAM.PK_SEQ\n" +
		"	\n" +
		"WHERE\n" +
		"	ERP_LENHSANXUAT.pk_seq = '" +this.id+
		"'";
		
		System.out.println("1.Init: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					if(!rs.getString("PO").equals("-1"))
					{
						this.PO = "Pln" + rs.getString("PO");
					}
					if(rs.getString("kichbansanxuat_fk") != null)
					{
						this.kbsxId = rs.getString("kichbansanxuat_fk");
					}
					this.spId = rs.getString("sanpham_fk");
					this.nhamayId = rs.getString("nhamay_fk");
					this.ngaytao = rs.getString("ngaybatdau");
					this.ngaydukien = rs.getString("ngaydukienHT");
					this.soluongsx = rs.getString("soluong");
					this.trangthai = rs.getString("trangthai");
					this.khoId=rs.getString("kho_fk");
					this.kho = rs.getString("KHOTT");
					this.sanpham = rs.getString("SANPHAM");
					this.BomId= rs.getString("BOM_FK");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
		}
		this.createRs();
		
		
		query = "select b.MA as vtMa, b.Ten as vtTen, d.DONVI as vtDonvi, a.SOLUONG,  ISNULL(c.MA, '') as vtTTMa, ISNULL(c.TEN, '') as vtTTTen, ISNULL(e.donvi, '') as vtTTDonvi, " +
				"a.tile, a.soluongTT, a.soluongchuan, a.chophepthaythe   " +
				"from Erp_LenhSanXuat_Bom a inner Join SanPham b on a.vattu_fk = b.PK_SEQ left Join SanPham c on a.vattutt_fk = c.pk_seq  " +
				"inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ left join DONVIDOLUONG e on c.DVDL_FK = e.PK_SEQ   " +
				"where a.lenhsanxuat_fk = '" + this.id + "'";

				System.out.println("__Khoi tao BOM 1: " + query);
				
				rs = db.get(query);
				 
				List<IDanhmucvattu_SP> dmvtList = new ArrayList<IDanhmucvattu_SP>();
				
				try 
				{
					IDanhmucvattu_SP vt = null;
					while(rs.next())
					{
						vt = new Danhmucvattu_SP();
						
						vt.setMaVatTu(rs.getString("vtMa"));
						vt.setTenVatTu(rs.getString("vtTen"));
						vt.setDvtVT(rs.getString("vtDonvi"));
 
						vt.setSoLuong(Float.toString(rs.getFloat("SOLUONG")));
						this.soluongchuan=rs.getString("soluongchuan");
						vt.setMaVatTuThayThe(rs.getString("vtTTMa"));
						vt.setTenVatTuThayThe(rs.getString("vtTTTen"));
						vt.setDvtThayThe(rs.getString("vtTTDonvi"));
						
						if( rs.getString("tile") != null )
						{
							vt.setTile(rs.getString("tile"));
						}
						
						if(rs.getString("soluongTT") != null )
						{
							if(rs.getString("soluongTT").trim().length() > 0 )
							{
							   vt.setSoluongTT(Float.toString(rs.getFloat("soluongTT")));
							}
						}
						
						this.cpTT = rs.getString("chophepthaythe");
						
						dmvtList.add(vt);
						
					}
					rs.close();
	
					this.dmvtList = dmvtList;
				} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	
		
		
	}

	
	public void DBclose()
	{
		this.db.shutDown();
		try 
		{
			if(this.spRs != null)
				this.spRs.close();
			if(this.nhamayRs != null)
				this.nhamayRs.close();
			if(this.kbsxRs != null)
				this.kbsxRs.close();
		}
		catch (SQLException e) {}
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg=msg;
	}

	
	public String getSpId() 
	{
		return this.spId;
	}

	public void setSpId(String spId) 
	{
		this.spId = spId;
	}

	public ResultSet getSpRs()
	{
		return this.spRs;
	}

	public void setSpRs(ResultSet spRs)
	{
		this.spRs = spRs;
	}

	public String getKbsxId() 
	{
		return this.kbsxId;
	}

	public void setKbsxId(String kbsxId)
	{
		this.kbsxId = kbsxId;
	}

	public ResultSet getKbsxRs() 
	{
		return this.kbsxRs;
	}

	public void setKbsxRs(ResultSet kbsxRs) 
	{
		this.kbsxRs = kbsxRs;
	}

	
	public String getNhamayId()
	{
		return this.id;
	}

	public void setNhamayId(String nhamayId) 
	{
		this.nhamayId = nhamayId;
	}

	public ResultSet getNhamayRs()
	{
		return this.nhamayRs;
	}

	public void setNhamayRs(ResultSet nhamayRs) 
	{
		this.nhamayRs = nhamayRs;
	}

	public String getPlaintOrder() 
	{
		return this.PO;
	}

	public void setPlaintOrder(String PO) 
	{
		this.PO = PO;
	}

	public ResultSet getChitietNLRs() 
	{
		return this.chitietNlRs;
	}

	public void setChitietNLRs(ResultSet nlRs) 
	{
		this.chitietNlRs = nlRs;
	}

	public String getTuan(String ngay) 
	{
		String[] ngayArr = ngay.split("-");
		
		int ngayTT = Integer.parseInt(ngayArr[2]);
		
		if( 1 <= ngayTT && ngayTT <= 7)
		{
			return "Tuần 1";
		}
		
		if( 8 <= ngayTT && ngayTT <= 14)
		{
			return "Tuần 2";
		}
		
		if( 15 <= ngayTT && ngayTT <= 21)
		{
			return "Tuần 3";
		}
		
		return "Tuần 4";
	}

	public void setListDanhMuc(List<IDanhmucvattu_SP> list) 
	{
		this.dmvtList = list;
	}

	public List<IDanhmucvattu_SP> getListDanhMuc() 
	{
		return this.dmvtList;
	}

	public String getSoluongchuan() 
	{
		return this.soluongchuan;
	}

	public void setSoluongchuan(String slgchuan) 
	{
		this.soluongchuan = slgchuan;
	}

	public String getChophepTT() 
	{
		return this.cpTT;
	}

	public void setChophepTT(String chophepTT) 
	{
		this.cpTT = chophepTT;
	}

	public void changeKbsx() 
	{
		//this.nhamayRs = db.get("select pk_seq, manhamay + ', ' + tennhamay as nhamayTen from ERP_NHAMAY");
		String query = "select pk_seq, ma + ', ' + ten as ten from SanPham where trangthai = '1' and loaisanpham_fk in ( 100005, 100011, 100012 ) ";
		System.out.println("[ErpLenhsanxuat.changeKbsx] query = " + query);
		this.spRs = db.get(query);
		this.dmvtList.clear();
		
		if( this.spId.trim().length() > 0  && this.soluongsx.trim().length() > 0 )
		{
			query = "";
			
			double so=0;
			try {
				so=Double.parseDouble(this.trangthai);
			
			} catch(Exception er){
				
			}
			
			if( so <=0 )
			{
				query = " SELECT isnull(PHANTRAMHAOHUT,0) as PHANTRAMHAOHUT ,SP1.MA AS vtMa, SP1.ten AS vtTen, DVDL1.DONVI AS vtDonvi, VATTU.SOLUONG,  " +
						" ISNULL(SP2.MA, '') AS vtTTMa, SP2.ten AS vtTTTen, ISNULL(DVDL2.donvi, '') AS vtTTDonvi, " + 
						" VATTU.tile, isnull(VATTU.SoLuongTT, '0') as SoLuongTT, DANHMUC.SOLUONGCHUAN, DANHMUC.CHOPHEPTT    " +	
						" FROM ERP_DANHMUCVATTU DANHMUC  " +
						" INNER JOIN ERP_DANHMUCVATTU_VATTU VATTU ON VATTU.DANHMUCVT_FK = DANHMUC.PK_SEQ " +
						" INNER Join SanPham SP1 ON VATTU.VATTU_FK = SP1.PK_SEQ " +
						" LEFT  Join SanPham SP2 ON VATTU.VATTUTT_FK = SP2.PK_SEQ  " +
						" INNER JOIN DONVIDOLUONG DVDL1 ON SP1.DVDL_FK = DVDL1.PK_SEQ " +
						" LEFT  JOIN DONVIDOLUONG DVDL2 ON SP2.DVDL_FK = DVDL2.PK_SEQ " +
						" WHERE DANHMUC.SANPHAM_FK = '" + this.spId + "' and DANHMUC.TRANGTHAI = '1' AND DANHMUC.PK_SEQ= " +this.BomId;
						 
			}
			else
			{
				query = " select  b.MA as vtMa, b.TEN as vtTen, d.DONVI as vtDonvi, a.SOLUONG,  ISNULL(c.MA, '') as vtTTMa, ISNULL(c.TEN, '') as vtTTTen, ISNULL(e.donvi, '') as vtTTDonvi, " +
						" a.tile, a.soluongTT, a.soluongchuan, a.chophepthaythe as chopheptt  " +
						" from Erp_LenhSanXuat_Bom a inner Join SanPham b on a.vattu_fk = b.PK_SEQ left Join SanPham c on a.vattutt_fk = c.pk_seq  " +
						" inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ left join DONVIDOLUONG e on c.DVDL_FK = e.PK_SEQ   " +
						" where a.lenhsanxuat_fk = '" + this.id + "'";
			}
			
			System.out.println("Change BOM nek :  " + query);
			
			ResultSet rs = db.get(query);
			
			List<IDanhmucvattu_SP> dmvtList = new ArrayList<IDanhmucvattu_SP>();
			
			try 
			{
				IDanhmucvattu_SP vt = null;
				while(rs.next())
				{
					vt = new Danhmucvattu_SP();
					
					vt.setMaVatTu(rs.getString("vtMa"));
					vt.setTenVatTu(rs.getString("vtTen"));
					vt.setDvtVT(rs.getString("vtDonvi"));
					
					if(this.soluongchuan.trim().length() <= 0)
						this.soluongchuan = rs.getString("soluongchuan");
					
					
					
					if( so <=0 )
					{
						double dinhmuc = Float.parseFloat(this.soluongsx) * rs.getFloat("SOLUONG") / Float.parseFloat(this.soluongchuan);
						
						dinhmuc=dinhmuc+ dinhmuc* rs.getFloat("PHANTRAMHAOHUT")/100; 
						
						vt.setSoLuong(formatter1.format(dinhmuc));
					}
					else
					{
						vt.setSoLuong(Float.toString(rs.getFloat("SOLUONG")));
					}
					
					vt.setMaVatTuThayThe(rs.getString("vtTTMa"));
					vt.setTenVatTuThayThe(rs.getString("vtTTTen"));
					vt.setDvtThayThe(rs.getString("vtTTDonvi"));
					vt.setTile(rs.getString("tile"));
					
					if(rs.getString("soluongTT") != null )
					{
						if( so <=0 )
						{	
							if(rs.getString("soluongTT").trim().length() > 0 )
							{
								float dinhmucTT = Float.parseFloat(this.soluongsx) * rs.getFloat("soluongTT") / Float.parseFloat(this.soluongchuan);
								vt.setSoluongTT(Float.toString(dinhmucTT));
							}
						}
						else
						{
							vt.setSoluongTT(Float.toString(rs.getFloat("soluongTT")));
						}
					}
					
					this.cpTT = rs.getString("chopheptt");
					
					dmvtList.add(vt);
					
				}
				rs.close();
				
				this.dmvtList = dmvtList;
				System.out.println("Size "+this.dmvtList .size());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
			 
		}
	}
	

	public boolean tieuhaoLsx(String khoTieuhao_fk) 
	{
		if(this.msg.trim().length() > 0)
		{
			this.msg = "Vui lòng kiểm tra lại các thông tin: \n" + this.msg;
			return false;
		}
 
		//Check vattu List
		if(this.dmvtList.size() <= 0)
		{
			this.msg = "Không có danh mục vật tư nào để tiêu hao.";
			return false;
		}
		Utility util = new Utility();
		
		if(this.Ngaytieuhao.length() ==0){
			this.msg = "Vui lòng chọn ngày tiêu hao.";
			return false;
		}
		String msg1=util.CheckNgayGhiNhanHopLe_Provence(db, Ngaytieuhao);
		if(msg1.length() >0){
			this.msg = msg1;
			return false;
		}
		
		for(int i = 0; i < this.dmvtList.size(); i++)
		{
			IDanhmucvattu_SP vattu = this.dmvtList.get(i);
			if(vattu.getSoLuongTHThucTe().trim().length() <= 0 || vattu.getSoLuongTHThucTe().equals("0"))
			{
				this.msg = "Vui lòng nhập số lượng tiêu hao thực tế của vật tư: " + vattu.getMaVatTu() + ", " + vattu.getTenVatTu();
				return false;
			}
			
			List<ISpDetail> spDetail = vattu.getSpDetailList();
			if(spDetail.size() <= 0)
			{
				this.msg = "Vui lòng kiểm tra lại Lô tiêu hao của vật tư: " + vattu.getMaVatTu() + ", " + vattu.getTenVatTu();
				return false;
			}
			
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
 
			String query="INSERT INTO ERP_TIEUHAONGUYENLIEU (ngaytieuhao,LENHSANXUAT_FK,NHAPKHO_FK,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA,TRANGTHAI,NGAYLAP) " +
					" VALUES ('"+this.Ngaytieuhao+"',"+this.id+","+this.nhapkhoid+","+this.userId+","+this.userId+",'"+this.getDateTime()+"','"+this.getDateTime()+"',1,'"+this.getDateTime()+"')";
			if(!db.update(query)){
				this.msg="Lỗi dòng lệnh: "+query;
				db.update("rollback");
				return false;
			}
			  query = "select IDENT_CURRENT('ERP_TIEUHAONGUYENLIEU') as dhId";
			  ResultSet rsDh = db.get(query);
			 
				rsDh.next();
				this.TieuHaoId = rsDh.getString("dhId");
				rsDh.close();
			 
	 
			for(int i = 0; i < this.dmvtList.size(); i++)
			{
				IDanhmucvattu_SP vattu = this.dmvtList.get(i);
				 
				  query =  "select b.LOAISANPHAM_FK, GIATON from ERP_KHOTT_SANPHAM a inner join SanPham b on a.SANPHAM_FK = b.PK_SEQ  " +
								"where SANPHAM_FK = ( select pk_seq from SanPham where ma = '" + vattu.getMaVatTu() + "' ) and KHOTT_FK = '" + khoTieuhao_fk + "'";
				ResultSet rsGia = db.get(query);
				float giaton = 0;
				String loaisanpham = "";
				
				if(rsGia != null)
				{
					if(rsGia.next())
					{
						giaton = rsGia.getFloat("GIATON");
						loaisanpham = rsGia.getString("LOAISANPHAM_FK");
					}
					rsGia.close();
				}
				
				query = " Insert ERP_LENHSANXUAT_TIEUHAO (tieuhaonguyenlieu_fk,lenhsanxuat_fk, sanpham_fk, khott_fk, soluong, dongia, thanhtien) " +
						" select "+this.TieuHaoId+",'" + this.id + "', pk_seq, '" + khoTieuhao_fk + "', '" + vattu.getSoLuongTHThucTe() + "', " + giaton + ", " + vattu.getSoLuongTHThucTe() + " * " + giaton +
						" from SanPham where ma = '" + vattu.getMaVatTu() + "' ";
				
				System.out.println("1.Insert ERP_LENHSANXUAT_TIEUHAO: " + query);
				if(!db.update(query))
				{
					this.msg = "1.Khong the cap nhat ERP_LENHSANXUAT_TIEUHAO: " + query;
					db.getConnection().rollback();
					return false;
				}
 
				if(vattu.getSoLuongTHThucTe().trim().length() > 0)
				{
					List<ISpDetail> spDetail = vattu.getSpDetailList();
					for(int j = 0; j < spDetail.size(); j++)
					{
						ISpDetail detail = spDetail.get(j);
						
						String solo = detail.getSolo();
						String soluongct = detail.getSoluong();
						String vitriId = "100000";
						query = "Insert ERP_LENHSANXUAT_TIEUHAO_CHITIET (tieuhaonguyenlieu_fk,lenhsanxuat_fk, sanpham_fk, solo, ngayhethan, soluong, bean) " +
								"select "+this.TieuHaoId+",'" + this.id + "', pk_seq, '" + detail.getSolo() + "', '', '" + detail.getSoluong() + "', 100000 " +
								"from SanPham where ma = '" + vattu.getMaVatTu() + "' ";
						
						//System.out.println("2.Insert ERP_LENHSANXUAT_TIEUHAO_CHITIET: " + query);
						if(!db.update(query))
						{
							this.msg = "1.Khong the cap nhat ERP_LENHSANXUAT_TIEUHAO_CHITIET: " + query;
							db.getConnection().rollback();
							return false;
						}
						
						query = "update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + soluongct + "', AVAILABLE = AVAILABLE - '" + soluongct + "' " +
								"where KHOTT_FK = '" + khoTieuhao_fk + "' " +
									   "and SANPHAM_FK = ( select pk_seq from SanPham where ma = '" + vattu.getMaVatTu() + "' ) " +
									   	"and SOLO = '" + solo + "'";
						
						//System.out.println("3.Update ERP_KHOTT_SP_CHITIET: " + query);
						if(db.updateReturnInt(query) <= 0 )
						{
							this.msg = "3.Không đủ tồn kho để cập nhật ERP_KHOTT_SP_CHITIET: " + query;
							db.getConnection().rollback();
							return false;
						}
						
						
					/*	query = "update ERP_KHOTT_SANPHAM set soluong = soluong - '" + soluongct + "', AVAILABLE = AVAILABLE - '" + soluongct + "' " +
								"where KHOTT_FK = '" + khoTieuhao_fk + "' " +
									   	"and SANPHAM_FK = ( select pk_seq from SanPham where ma = '" + vattu.getMaVatTu() + "' ) ";
						
						//System.out.println("4.Update ERP_KHOTT_SANPHAM: " + query);
						if(!db.update(query))
						{
							this.msg = "4.Không đủ tồn kho để cập nhật ERP_KHOTT_SANPHAM: " + query;
							db.getConnection().rollback();
							return false;
						}*/
						
						
						String spid_= this.getIdSp(vattu.getMaVatTu());
						String khoit_cn= khoTieuhao_fk;
						double soluongct_=0;
						double booked_ct_=0;
						double avai_ct_=0;
						try{ soluongct_ =  (-1)*Double.parseDouble(soluongct.replaceAll(",", ""));}catch(Exception err){}
						try{ avai_ct_ = (-1)*Double.parseDouble(soluongct.replaceAll(",", ""));}catch(Exception err){}
						
						  msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"Erplenhsanxaut.java 1113");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							this.msg= "Lỗi :"+msg1;
							return false;
						}
						
						
					}
					
				}
 
			}
			
	 
			//GHI NHAN BUT TOAN
			query = " select ngaytieuhao from ERP_TIEUHAONGUYENLIEU where pk_seq = '" + this.TieuHaoId + "' ";
			ResultSet rsTH = db.get(query);
			
			String ngaytieuhao = "";
			String thang = "";
			String nam = "";
			System.out.println(query);
			
			if(rsTH.next())
			{
				ngaytieuhao = rsTH.getString("ngaytieuhao");
			}
			rsTH.close();
			
			
			nam = ngaytieuhao.substring(0, 4);
			thang = ngaytieuhao.substring(5, 7);
			
			String queryTK = 	" select '62110000' as taikhoanNO_CP_NVL, d.SOHIEUTAIKHOAN as taikhoanCO_CP_NVL,  " +
									" '15410000' as taikhoanNO_KC_CP_NVL, '62110000' as taikhoanCO_KC_CP_NVL,  " +
									" N'Sản phẩm' as doituong, b.PK_SEQ as madoituong, a.SOLUONG as SOLUONGTHUCTE,  " +
									" ISNULL ( ( select top(1) AVG(giaton) from ERP_TONKHOTHANG where SANPHAM_FK = b.PK_SEQ group by NAM, THANG order by NAM desc, THANG desc  ), 0 ) AS DONGIA_TON   " +
								" from ERP_LENHSANXUAT_TIEUHAO a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ   " +
									" inner join ERP_LOAISANPHAM c on b.LOAISANPHAM_FK = c.PK_SEQ   " +
									" inner join ERP_TAIKHOANKT d on c.TAIKHOANKT_FK = d.PK_SEQ   " +
								" where a.TIEUHAONGUYENLIEU_FK = '" + this.TieuHaoId + "' ";
			
			System.out.println("----LAY TAI KHOAN: " + queryTK);
			String doituong = "Sản phẩm";
			String madoituong = "";
			
		
			if(queryTK.trim().length() > 0)
			{
				ResultSet tkRs = db.get(queryTK);
				
				if(tkRs != null)
				{
					while(tkRs.next())
					{
						String taikhoanNO_CP_NVL = "";
						String taikhoanCO_CP_NVL = "";
						
						String taikhoanNO_KC_CP_NVL = "";
						String taikhoanCO_KC_CP_NVL = "";
						
						doituong = tkRs.getString("doituong");
						madoituong = tkRs.getString("madoituong");
						String tiente_fk = "100000";
						
						double dongiaTON = tkRs.getDouble("DONGIA_TON");
						double soluong = tkRs.getDouble("soluongthucte");

						taikhoanNO_CP_NVL = tkRs.getString("taikhoanNO_CP_NVL");
						taikhoanCO_CP_NVL = tkRs.getString("taikhoanCO_CP_NVL");
						
						taikhoanNO_KC_CP_NVL = tkRs.getString("taikhoanNO_KC_CP_NVL");
						taikhoanCO_KC_CP_NVL = tkRs.getString("taikhoanCO_KC_CP_NVL");
							
						//    Chi phí NVL dùng
						if(taikhoanNO_CP_NVL.trim().length() <= 0 || taikhoanCO_CP_NVL.trim().length() <= 0)
						{
							db.getConnection().rollback();
							this.msg = "22.Loại sản phẩm và nội dung nhập tương ứng chưa có tài khoản kế toán đi kèm, vui lòng kiểm tra lại dữ liệu nền.";
							tkRs.close();
							return false;
						}
						
						double chiphiNVL = Math.round(dongiaTON * soluong);
						msg = util.Update_TaiKhoan_TheoSoHieu( db, thang, nam, ngaytieuhao, ngaytieuhao, "Tiêu hao sản xuất", this.TieuHaoId , taikhoanNO_CP_NVL, taikhoanCO_CP_NVL, "", 
													Double.toString(chiphiNVL), Double.toString(chiphiNVL), doituong, madoituong, "0", Double.toString(soluong), Double.toString(dongiaTON), tiente_fk, Double.toString(dongiaTON), "1", dongiaTON + "*" + soluong, dongiaTON + "*" + soluong, "Chi phí NVL dùng" );
						if(msg.trim().length() > 0)
						{
							db.getConnection().rollback();
							tkRs.close();
							return false;
						}
						
						
						//     Kết chuyển chi phí NVL
						if(taikhoanNO_KC_CP_NVL.trim().length() <= 0 || taikhoanCO_KC_CP_NVL.trim().length() <= 0)
						{
							db.getConnection().rollback();
							this.msg = "23.Loại sản phẩm và nội dung nhập tương ứng chưa có tài khoản kế toán đi kèm, vui lòng kiểm tra lại dữ liệu nền.";
							tkRs.close();
							return false;
						}
						
						msg = util.Update_TaiKhoan_TheoSoHieu( db, thang, nam, ngaytieuhao, ngaytieuhao, "Tiêu hao sản xuất", this.TieuHaoId , taikhoanNO_KC_CP_NVL, taikhoanCO_KC_CP_NVL, "", 
								Double.toString(chiphiNVL), Double.toString(chiphiNVL), doituong, madoituong, "0", Double.toString(soluong), Double.toString(dongiaTON), tiente_fk, Double.toString(dongiaTON), "1", dongiaTON + "*" + soluong, dongiaTON + "*" + soluong, "Kết chuyển chi phí NVL" );
						if(msg.trim().length() > 0)
						{
							db.getConnection().rollback();
							tkRs.close();
							return false;
						}
					}	
					tkRs.close();
				}
				
			}
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			e.printStackTrace();
			this.msg = "Ecception: " + e.getMessage();
			return false;
		}

		return true;
	}

	private String getIdSp(String maVatTu)
	{
		String Idvt="";
		try{
			String query="select pk_seq from SanPham where ma = '" + maVatTu + "'";
			ResultSet rs=db.get(query);
			 
			if(rs.next()){
				Idvt=rs.getString("pk_seq");
			}
			rs.close();
		}catch(Exception er){}
		return Idvt;
	}

	private boolean TangKhoQC() 
	{
		String query = " select pk_seq, nhapkho_fk, sanpham_fk, solo, ngayhethan, soluong, soluongDat  " +
					   " from ERP_YeuCauKiemDinh  " +
					   " where nhapkho_fk is not null and datangkho = '0' and isnull(soluongDat, 0) > 0 " +
					   "		and nhapkho_fk in ( select pk_seq from ERP_NHAPKHO where SOLENHSANXUAT = '" + this.id + "' )";
		
		System.out.println("Lay QC da kiem: " + query);
		ResultSet rsQC = db.get(query);
		if(rsQC != null)
		{
			try
			{
				while(rsQC.next())
				{
					boolean _cophieutieuhao = true;
					boolean _daChot = true;
					
					if(_cophieutieuhao && _daChot) 
					{ 
						//Không có phiếu tiêu hao hoặc nếu có thì phải chốt phiếu tiêu hao thì mới tăng kho
						//XỬ LÝ CÁC NGHIỆP VỤ LIÊN QUAN ĐẾN KHO
						
						String khott_fk = "";
						query = " select KHONHAP from ERP_NHAPKHO where PK_SEQ = '" + rsQC.getString("nhapkho_fk") + "' ";
						
						System.out.println("__Lay kho: " + query);
						ResultSet rsKho = db.get(query);
						if(rsKho.next())
						{
							khott_fk = rsKho.getString("KHONHAP");
						}
						rsKho.close();
						
						String vitri = "100000";
						/*query = "Update ERP_KHOTT_SANPHAM set soluong = soluong + " + rsQC.getString("soluongDat") + ", AVAILABLE = AVAILABLE + " + rsQC.getString("soluongDat") + " " +
							"where khott_fk = '" + khott_fk + "' and sanpham_fk = '" + this.spId + "'";
						
						if(db.updateReturnInt(query) <= 0 )
						{
							this.msg = "1.Không thể cập nhật ERP_KHOTT_SANPHAM " + query;
							return false;
						}*/
						
						String spid_= this.spId;
						String khoit_cn= khott_fk;
						double soluongct_=0;
						double booked_ct_=0;
						double avai_ct_=0;
						try{ soluongct_ = Double.parseDouble(rsQC.getString("soluongDat").replaceAll(",", ""));}catch(Exception err){}
						try{ avai_ct_ = Double.parseDouble(rsQC.getString("soluongDat").replaceAll(",", ""));}catch(Exception err){}
						
						String msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0,this.id,"Erplenhsanxuat.java 1315");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							this.msg= "Lỗi :"+msg1;
							return false;
						}

						
						query = "select count(*) as sodong from ERP_KHOTT_SP_CHITIET " +
							"where  KHOTT_FK = '" + khott_fk + "' and SANPHAM_FK = '" + this.spId + "' and SOLO = '" + rsQC.getString("SoLo") + "' and BIN = '" + vitri + "'";
						
						ResultSet rsCheck = db.get(query);
						boolean flag = false;
						if(rsCheck.next())
						{
							if(rsCheck.getInt("sodong") > 0)
								flag = true;
							rsCheck.close();
						}
						
						if(flag)
						{
							query = "update ERP_KHOTT_SP_CHITIET set soluong = soluong + " + rsQC.getString("soluongDat") + ", AVAILABLE = AVAILABLE + " + rsQC.getString("soluongDat") + ", " +
									" NGAYNHAPKHO = '" + this.getDateTime() + "'  " +
									"where KHOTT_FK = '" + khott_fk + "' and SANPHAM_FK = '" + this.spId + "' and SOLO = '" + rsQC.getString("SoLo") + "' and BIN = '" + vitri + "'";
						}
						else
						{
							query = "insert ERP_KHOTT_SP_CHITIET(KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, NGAYSANXUAT, NGAYHETHAN, NGAYNHAPKHO, BIN) " +
									"select '" + khott_fk + "', '" + this.spId + "', " + rsQC.getString("soluongDat") + ", '0', " + rsQC.getString("soluongDat") + ", '" + rsQC.getString("SoLo") + "', NgaySanXuat, NgayHetHan, '" + this.getDateTime() + "', '" + vitri + "' " +
									"from Erp_YeuCauKiemDinh where pk_seq = '" + rsQC.getString("pk_seq") + "' ";
						}
						
						System.out.println("__ERP_KHOTT_SP_CHITIET: " + query);
						if(!db.update(query))
						{
							this.msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET " + query;
							return false;
						}
						
						
						double soluong_khongdat = Double.parseDouble(rsQC.getString("SoLuong")) - Double.parseDouble(rsQC.getString("soluongDat"));
						if(soluong_khongdat > 0)
						{
							query = "select count(*) as sodong from ERP_KHOTT_SP_CHITIET_TRANGTHAI " +
									"where  KHOTT_FK = '" + khott_fk + "' and SANPHAM_FK = '" + this.spId + "' and SOLO = '" + rsQC.getString("SoLo") + "' and BIN = '" + vitri + "' and trangthai = '-1' ";
							
							rsCheck = db.get(query);
							
							flag = false;
							if(rsCheck.next())
							{
								if(rsCheck.getInt("sodong") > 0)
									flag = true;
								rsCheck.close();
							}
							
							if(flag)
							{
								query = "update ERP_KHOTT_SP_CHITIET_TRANGTHAI set soluong = soluong + " + soluong_khongdat + ", AVAILABLE = AVAILABLE + " + soluong_khongdat + "  " +
										"where KHOTT_FK = '" + khott_fk + "' and SANPHAM_FK = '" + this.spId + "' and SOLO = '" + rsQC.getString("SoLo") + "' and BIN = '" + vitri + "' and trangthai = '-1' ";
							}
							else
							{
								query = "insert ERP_KHOTT_SP_CHITIET_TRANGTHAI(KHOTT_FK, SANPHAM_FK, SOLUONG, BOOKED, AVAILABLE, SOLO, BIN, TrangThai) " +
										"values( '" + khott_fk + "', '" + this.spId + "', " + soluong_khongdat + ", '0', '" +soluong_khongdat + "', '" + rsQC.getString("SoLo") + "', '" + vitri + "', '-1' )";
							}
							
							System.out.println("__Kho trang thai: " + query);
							if(!db.update(query))
							{
								this.msg = "Không thể cập nhật ERP_KHOTT_SP_CHITIET_TRANGTHAI " + query;
								return false;
							}
						}
					}
					
					//Cap nhat lai trang thai cua kiem dinh la da kiem dinh.
					//NEU DA THUC HIEN TIEU HAO, THI DANH DAU LA DA TANG KHO

					query = "update ERP_YeuCauKiemDinh set  DaTangKho = '1' where pk_seq = '" + rsQC.getString("pk_seq") + "'";
					
					System.out.println("----CAP NHAT TRANG THAI: " + query);
					if(!db.update(query))
					{
						this.msg = "Không thể cập nhật ERP_YeuCauKiemDinh " + query;
						return false;
					}
				}
				rsQC.close();
			}
			catch (Exception e) {
				
				this.msg = "Lỗi khi cập nhật QC: " + e.getMessage();
				return false;
			}
		}
		
		return true;
		
	}

	public boolean checkTieuHaoLsx() 
	{
		
		this.initLsx_TieuHao();
		return true;
 
	}

	private void initTieuHao() 
	{
		
		String query =  " select trangthai, ISNULL(lenhsanxuatdukien_fk, '-1') as PO, kichbansanxuat_fk, sanpham_fk, ngaybatdau, ngaydukienHT, soluong, nhamay_fk " +
						" from ERP_LENHSANXUAT where pk_seq = '" + this.id + "'";
		
		//System.out.println("1.Init tieu hao: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					if(!rs.getString("PO").equals("-1"))
					{
						this.PO = "Pln" + rs.getString("PO");
					}
					this.kbsxId = rs.getString("kichbansanxuat_fk");
					this.spId = rs.getString("sanpham_fk");
					this.nhamayId = rs.getString("nhamay_fk");
					this.ngaytao = rs.getString("ngaybatdau");
					this.ngaydukien = rs.getString("ngaydukienHT");
					this.soluongsx = rs.getString("soluong");
					this.trangthai = rs.getString("trangthai");
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		String khoTieuhao_fk="";
		if(this.nhapkhoid.length()  >0){
			String sql= " select khosx.PK_SEQ  from ERP_NHAPKHO nk "+  
			" inner join ERP_LENHSANXUAT lsx on lsx.PK_SEQ=nk.SOLENHSANXUAT "+ 
			" inner join ERP_KHOTT kho on kho.PK_SEQ=lsx.KHO_FK "+
			" inner join ERP_KHOTT khosx on khosx.TRUNGTAMPP_FK=kho.PK_SEQ "+  
			" and khosx.LOAI='1'  where nk.PK_SEQ="+nhapkhoid;
			 rs=db.get(sql);
			 try{
				if(rs.next()){
				khoTieuhao_fk=rs.getString("pk_seq");
				}else{
					this.msg="Không xác định được kho tiêu hao";
					return;
				}
			rs.close();
			 }catch(Exception er)
			 {
				 
			 }
		}
		this.dmvtList.clear();
		if(this.nhapkhoid.length() > 0){
		  query = " select b.MA as vtMa, b.Ten as vtTen, d.DONVI as vtDonvi, a.SOLUONG,  ISNULL(c.MA, '') as vtTTMa, ISNULL(c.TEN, '') as vtTTTen, ISNULL(e.donvi, '') as vtTTDonvi, " +
				" a.tile, a.soluongTT, a.soluongchuan, a.chophepthaythe   " +
				" from Erp_LenhSanXuat_Bom a inner Join SanPham b on a.vattu_fk = b.PK_SEQ left Join SanPham c on a.vattutt_fk = c.pk_seq  " +
				" inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ left join DONVIDOLUONG e on c.DVDL_FK = e.PK_SEQ   " +
				" where a.lenhsanxuat_fk = '" + this.id + "'";
		
		System.out.println("__Khoi tao BOM 1400: " + query);
		
	    	rs = db.get(query);
		if(rs != null)
		{
			List<IDanhmucvattu_SP> dmvtList = new ArrayList<IDanhmucvattu_SP>();
		
			try 
			{
				IDanhmucvattu_SP vt = null;
				while(rs.next())
				{
					vt = new Danhmucvattu_SP();
					
					vt.setMaVatTu(rs.getString("vtMa"));
					vt.setTenVatTu(rs.getString("vtTen"));
					vt.setDvtVT(rs.getString("vtDonvi"));
					double soluongtieuhao=Double.parseDouble(this.Soluongnhapkho);
					double soluongsx=Double.parseDouble(this.soluongsx);
					double tieuhao=0; 
					tieuhao=rs.getDouble("SOLUONG")* soluongtieuhao/ soluongsx;
					vt.setSoLuong(formatter1.format(tieuhao));
					vt.setMaVatTuThayThe(rs.getString("vtTTMa"));
					vt.setTenVatTuThayThe(rs.getString("vtTTTen"));
					vt.setDvtThayThe(rs.getString("vtTTDonvi"));
					 
						float tongluong = 0;
					 
						double tongsoluongxuat=Double.parseDouble(formatter1.format(tieuhao));
						
						List<ISpDetail> spDetail = new ArrayList<ISpDetail>();
						System.out.println("tongsoluongxuat"+tongsoluongxuat);
						query =  " select SANPHAM_FK, isnull(AVAILABLE, 0) as soluong, SOLO " +
					    " from ERP_KHOTT_SP_CHITIET a  " +
						" where   isnull(AVAILABLE, 0) >0 and a.khott_fk = '" + khoTieuhao_fk + "'  and a.sanpham_fk = ( select pk_seq from SanPham where ma = '" + rs.getString("vtMa") + "' ) " +
						" order by a.ngayhethan asc, a.AVAILABLE asc";
		 
						System.out.println(" 1348. Get du lieu : "+query);
						 ResultSet rsSpDetail = db.get(query);
						while(rsSpDetail.next())
						{	
							ISpDetail	spCon = new SpDetail();
							double slgton =  rsSpDetail.getDouble("soluong") ;
							String solo = rsSpDetail.getString("solo");
							 
								if( tongsoluongxuat >0) {
									if(tongsoluongxuat <rsSpDetail.getDouble("soluong")){
										spCon.setSoluong(  formatter1.format(tongsoluongxuat));
										tongsoluongxuat=0;
									}else{
										tongsoluongxuat=tongsoluongxuat-rsSpDetail.getDouble("soluong");
										spCon.setSoluong(formatter1.format(slgton));
									}
								}else{
									spCon.setSoluong("0");
								}
						
							spCon.setSoluongton(formatter1.format(slgton));
							spCon.setSolo(solo);
							spDetail.add(spCon);
						 
						}
						rsSpDetail.close();
						vt.setSpDetailList(spDetail);
					if( rs.getString("tile") != null )
					{
						vt.setTile(rs.getString("tile"));
					}
					
					if(rs.getString("soluongTT") != null )
					{
						if(rs.getString("soluongTT").trim().length() > 0 )
						{
							vt.setSoluongTT(Float.toString(rs.getFloat("soluongTT")));
						}
					}
					this.cpTT = rs.getString("chophepthaythe");
					dmvtList.add(vt);
					
				}
				rs.close();
				
				this.dmvtList = dmvtList;
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
									
		}
	}

	private void initLsx_TieuHao() 
	{
		try{
				
			boolean datieuhao=false;
				String sql=" select PK_SEQ,NGAYNHAPKHO  from ERP_NHAPKHO where SOLENHSANXUAT="+this.id;
				this.rsnhapkho=db.get(sql);
				
				
				if( this.nhapkhoid.length() >0 ){
					sql=" SELECT NKSP.SOLUONGNHAP,SOLENHSANXUAT,SANPHAM_FK FROM ERP_NHAPKHO  NK "+
						" INNER JOIN ERP_NHAPKHO_SANPHAM  NKSP ON NK.PK_SEQ=NKSP.SONHAPKHO_FK  "+
						" WHERE NK.PK_SEQ="+this.nhapkhoid;
					 
					ResultSet rs=db.get(sql);
					 if(rs.next()){
						 this.Soluongnhapkho=rs.getString("SOLUONGNHAP");
						 
					 }
					rs.close();
					this.TieuHaoId="";
					sql="SELECT PK_SEQ,NGAYTIEUHAO FROM ERP_TIEUHAONGUYENLIEU WHERE NHAPKHO_FK ="+this.nhapkhoid+" and lenhsanxuat_fk="+this.id+" and trangthai=1";
					System.out.println("4444 ID Nek : "+sql);
					rs=db.get(sql);
					if(rs.next()){
						datieuhao=true;
						this.Ngaytieuhao=rs.getString("NGAYTIEUHAO");
						this.TieuHaoId=rs.getString("PK_SEQ");
					}
					rs.close();
					
				}
				
				
				
			if(( this.TieuHaoId.length() ==0) ){
			 
				System.out.println("Vao day 1"+this.TieuHaoId);
				this.initTieuHao();
				
			}else {
				System.out.println("Vao day 2 : "+this.TieuHaoId);
				
				//hien thi tieu hao
					
						 	
					 
						/*String	query = " select b.pk_seq as spId, b.MA as vtMa, b.Ten as vtTen, d.DONVI as vtDonvi, a.SOLUONG,  ISNULL(c.MA, '') as vtTTMa, " +
										" ISNULL(c.TEN, '') as vtTTTen, ISNULL(e.donvi, '') as vtTTDonvi, a.tile, a.soluongchuan, a.soluongTT, a.chophepthaythe, f.soluong as tieuhao   " +
										" from Erp_LenhSanXuat_Bom a inner Join SanPham b on a.vattu_fk = b.PK_SEQ left Join SanPham c on a.vattutt_fk = c.pk_seq  " +
										" inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ left join DONVIDOLUONG e on c.DVDL_FK = e.PK_SEQ " +
										" inner join ERP_LENHSANXUAT_TIEUHAO  f on a.lenhsanxuat_fk = f.lenhsanxuat_fk and a.vattu_fk = f.sanpham_fk  " +
										" where a.lenhsanxuat_fk = '" + this.id + "' and f.TIEUHAONGUYENLIEU_FK ="+this.TieuHaoId;

*/
				
				String query= " select b.pk_seq as spId, b.MA as vtMa, b.Ten as vtTen, d.DONVI as vtDonvi, a.SOLUONG as tieuhao,   "+
					" isnull(lsx.SOLUONG,0) as soluong     ,isnull( lsx.tile,0) as tile,isnull( lsx.soluongchuan,0) as soluongchuan  "+
					" from  ERP_LENHSANXUAT_TIEUHAO  a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ   "+
					" left join ERP_LENHSANXUAT_BOM  as lsx on lsx.LENHSANXUAT_FK=a.lenhsanxuat_fk and   lsx.VATTU_FK=a.SANPHAM_FK  "+
					" inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ   "+
					" where a.lenhsanxuat_fk = '"+this.id+"' and a.TIEUHAONGUYENLIEU_FK ="+this.TieuHaoId;
						
						System.out.println("Get Bom : "+query);
 
						ResultSet rs = db.get(query);
 
						List<IDanhmucvattu_SP> dmvtList = new ArrayList<IDanhmucvattu_SP>();
		 
						IDanhmucvattu_SP vt = null;
						while(rs.next())
						{
							vt = new Danhmucvattu_SP();
							
							vt.setIdVT(rs.getString("spId"));
							vt.setMaVatTu(rs.getString("vtMa"));
							vt.setTenVatTu(rs.getString("vtTen"));
							vt.setDvtVT(rs.getString("vtDonvi"));
							vt.setSoLuong(rs.getString("SOLUONG"));
		  
							vt.setSoLuongTHThucTe(rs.getString("tieuhao"));
					 
							//vt.setMaVatTuThayThe(rs.getString("vtTTMa"));
							//vt.setTenVatTuThayThe(rs.getString("vtTTTen"));
							//vt.setDvtThayThe(rs.getString("vtTTDonvi"));
							vt.setTile(rs.getString("tile"));
							
							if(this.soluongchuan.trim().length() <= 0)
								this.soluongchuan = rs.getString("soluongchuan");
							
							//this.cpTT = rs.getString("chophepthaythe");
							
						/*	if(rs.getString("soluongTT") != null)
							{
								float dinhmucTT = Float.parseFloat(this.soluongsx) * rs.getFloat("soluongTT") / Float.parseFloat(this.soluongchuan);
								vt.setSoluongTT(Float.toString(dinhmucTT));
							}*/
							
							
							//Them chi tiet da tieu hao
							query = " select a.SOLO, a.SOLUONG, c.pk_seq as khuId, c.diengiai as khuTen, b.PK_SEQ as vtId, b.MA as vitri " +
									" , kct.NGAYSANXUAT \n" +
									" from ERP_LENHSANXUAT_TIEUHAO_CHITIET a left join ERP_VITRIKHO b on a.BEAN = b.PK_SEQ " +
									" left join ERP_KHUVUCKHO c on b.KHU_FK = c.pk_seq " +
									" left join ERP_KHOTT_SP_CHITIET kct on kct.KHOTT_FK = " + this.khoId + "\n" +
									" and kct.SANPHAM_FK = " + vt.getIdVT() + " and kct.SOLO = a.SOLO \n" +
									" where a.lenhsanxuat_fk = '" + this.id + "' and a.SANPHAM_FK = '" + rs.getString("spId") + "'  and a.TIEUHAONGUYENLIEU_FK ="+this.TieuHaoId;
							
							System.out.println("3.San pham tieu hao Detail: " + query);
							ResultSet rsSpDetail = db.get(query);
							
							List<ISpDetail> spConList = new ArrayList<ISpDetail>();
							ISpDetail spCon = null;
							 
								while(rsSpDetail.next())
								{
									String idhangmua = rs.getString("spId");
									String solo = rsSpDetail.getString("solo");
									String slg = rsSpDetail.getString("soluong");
									String khu = rsSpDetail.getString("khuTen");
									String vitri = rsSpDetail.getString("vitri");
									String vitriId = rsSpDetail.getString("vtId");
									
									spCon = new SpDetail(idhangmua, solo, slg, khu, vitri, vitriId);
									spCon.setSoluongton("");
									spCon.setNgaysanxuat(rsSpDetail.getString("NGAYSANXUAT"));
									spConList.add(spCon);
								}
								rsSpDetail.close();
					 
							vt.setSpDetailList(spConList);	
							
							dmvtList.add(vt);
							
						}
						rs.close();
						
						this.dmvtList = dmvtList;
					}	
			 
		
		}catch(Exception er){
			er.printStackTrace();
		}
  
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}


	public void initDisplay() 
	{
		String query = "select  trangthai, ISNULL(lenhsanxuatdukien_fk, '-1') as PO, kichbansanxuat_fk, sanpham_fk, ngaybatdau, ngaydukienHT, soluong, nhamay_fk " +
				"from ERP_LENHSANXUAT where pk_seq = '" + this.id + "'";
		
		 
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					if(!rs.getString("PO").equals("-1"))
					{
						this.PO = "Pln" + rs.getString("PO");
					}
					//this.kbsxId = rs.getString("kichbansanxuat_fk") == null? "" : rs.getString("kichbansanxuat_fk");
					this.spId = rs.getString("sanpham_fk");
					//this.nhamayId = rs.getString("nhamay_fk") == null ? "" : rs.getString("nhamay_fk");
					this.ngaytao = rs.getString("ngaybatdau");
					this.ngaydukien = rs.getString("ngaydukienHT");
					this.soluongsx = rs.getString("soluong");
					this.trangthai = rs.getString("trangthai");
					
				}
				rs.close();
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		query = "select b.MA as vtMa, b.Ten as vtTen, d.DONVI as vtDonvi, a.SOLUONG,  ISNULL(c.MA, '') as vtTTMa, ISNULL(c.TEN, '') as vtTTTen, ISNULL(e.donvi, '') as vtTTDonvi, " +
					"a.tile, a.soluongTT, a.soluongchuan, a.chophepthaythe   " +
				"from Erp_LenhSanXuat_Bom a inner Join SanPham b on a.vattu_fk = b.PK_SEQ left Join SanPham c on a.vattutt_fk = c.pk_seq  " +
				"inner join DONVIDOLUONG d on b.DVDL_FK = d.PK_SEQ left join DONVIDOLUONG e on c.DVDL_FK = e.PK_SEQ   " +
				"where a.lenhsanxuat_fk = '" + this.id + "'";
		
		System.out.println("__Khoi tao BOM: " + query);
		
		rs = db.get(query);
		 
			List<IDanhmucvattu_SP> dmvtList = new ArrayList<IDanhmucvattu_SP>();
		
			try 
			{
				IDanhmucvattu_SP vt = null;
				while(rs.next())
				{
					vt = new Danhmucvattu_SP();
					
					vt.setMaVatTu(rs.getString("vtMa"));
					vt.setTenVatTu(rs.getString("vtTen"));
					vt.setDvtVT(rs.getString("vtDonvi"));
 
					vt.setSoLuong(Float.toString(rs.getFloat("SOLUONG")));
					
					vt.setMaVatTuThayThe(rs.getString("vtTTMa"));
					vt.setTenVatTuThayThe(rs.getString("vtTTTen"));
					vt.setDvtThayThe(rs.getString("vtTTDonvi"));
					
					if( rs.getString("tile") != null )
					{
						vt.setTile(rs.getString("tile"));
					}
					
					if(rs.getString("soluongTT") != null )
					{
						if(rs.getString("soluongTT").trim().length() > 0 )
						{
						 
							vt.setSoluongTT(Float.toString(rs.getFloat("soluongTT")));
						}
					}
					
					this.cpTT = rs.getString("chophepthaythe");
					
					dmvtList.add(vt);
					
				}
				rs.close();
				
				this.dmvtList = dmvtList;
				System.out.println("Size nak: "+this.dmvtList.size());
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
	 
		
		this.createRs();	
	}

	public String getViewBom() 
	{
		return this.viewBom;
	}

	public void setViewBom(String viewBom) 
	{
		this.viewBom = viewBom;
	}

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
	}

	
	public ResultSet getRsNhapKho() {
		
		return rsnhapkho;
	}

	
	public void setRsNhapKho(ResultSet Rs) {
		
		rsnhapkho=Rs;
	}

	
	public String getNhapKhoId() {
		
		return this.nhapkhoid;
	}

	
	public void setNhapkhoId(String _nhapkhoId) {
		
		this.nhapkhoid= _nhapkhoId;
	}

	
	public String getSoLuongNhapKho() {
		
		return this.Soluongnhapkho;
	}

	
	public void setSoLuongNhapKho(String _soluongnhapkho) {
		
		this.Soluongnhapkho=_soluongnhapkho;
	}

	
	public String getTieuHaoId() {
		
		return this.TieuHaoId;
	}

	
	public void setTieuHaoId(String _TieuhaoId) {
		
		this.TieuHaoId=_TieuhaoId;
	}

	
	public String getKhoId() {
		
		return this.khoId;
	}

	
	public void setKhoId(String KhoId) {
		
		this.khoId=KhoId;
	}

	
	public ResultSet getKhoRs() {
		
		return this.khoRs;
	}

	
	public void setKhoRs(ResultSet RsKho) {
		
		this.khoRs=RsKho;
	}
 
	public String getNgaytieuhao() {
		return this.Ngaytieuhao;
	}
 
	public void setNgaytieuhao(String Ngaytieuhao) {
		this.Ngaytieuhao=Ngaytieuhao;
	}

	@Override
	public String getBomId()
	{
		// TODO Auto-generated method stub
		return this.BomId;
	}

	@Override
	public void setBomId(String BomId)
	{
		// TODO Auto-generated method stub
		this.BomId=BomId;
	}

	@Override
	public ResultSet getrsBom()
	{
		// TODO Auto-generated method stub
		return this.RsBom;
	}

	@Override
	public void setrsBom(ResultSet rsBom)
	{
		// TODO Auto-generated method stub
		this.RsBom=rsBom;
	}


}
