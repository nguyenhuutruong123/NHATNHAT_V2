package geso.dms.distributor.beans.nhaphang.imp;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.FixData;
import geso.dms.center.util.SendMail;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.nhaphang.INhaphang;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Nhaphang implements INhaphang
{
	String userId;
	String id;
	
	String nppId;
	
	String ngayyeucau;
	String ngaynhan;
	String sochungtu;
	String ghichu;

	String msg;
	String trangthai;

	String ddhId;
	ResultSet ddhRs;
	

	String khonhanId;
	ResultSet khonhanRs;

	String[] spPK_SEQ;
	String[] spId;
	String[] spMa;
	String[] spTen;
	String[] spDonvi;
	String[] spSolo;
	String[] spTonkho;
	String[] spXuat;
	String[] spSoluong;
	String[] spDongia;
	String[] spLoai;
	String[] spSCheme;
	String[] spvat;
	String[] spchietkhau;
	public String[] getSpchietkhau()
	{
		return spchietkhau;
	}

	public void setSpchietkhau(String[] spchietkhau)
	{
		this.spchietkhau = spchietkhau;
	}

	public String[] getSpvat()
	{
		return spvat;
	}

	public void setSpvat(String[] spvat)
	{
		this.spvat = spvat;
	}
	dbutils db;
	
	public Nhaphang()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";
		this.ngaynhan = "";
		this.sochungtu = "";
		this.khonhanId = "";
		this.loaiDh="";
		this.db = new dbutils();
	}
	
	public Nhaphang(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";
		this.ngaynhan = "";
		this.sochungtu = "";
		this.khonhanId = "";
		this.loaiDh="";
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

	public String getNgayyeucau() 
	{
		return this.ngayyeucau;
	}

	public void setNgayyeucau(String ngayyeucau) 
	{
		this.ngayyeucau = ngayyeucau;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public void createRs() 
	{
		String	query = "select PK_SEQ, CAST(pk_seq as varchar(10)) + ' / ' + NgayDonHang as ten " +
						"from ERP_DONDATHANG where TRANGTHAI in (2, 4) and PK_SEQ in ( select ddh_fk from NHAPHANG_DDH where nhaphang_fk = '" + this.id + "' )  ";
		this.ddhRs = db.get(query);
		Utility util = new Utility();
		if(this.id.trim().length() > 0 )
		{
			/*query = "declare @tb table  ";
				query+="( ";
				query+="sanpham_fk numeric(18,0), ";
			query += "dongia float,thuevat float,chietkhau float ";
					query+=") ";
			
					query+="declare @chuyenkho_fk int;";
			query += "set @chuyenkho_fk=(select CHUYENKHO_FK from NHAPHANG where PK_SEQ=" + this.id + ") ";
			
					query+="declare @ycxkfk int;";
			query += "set @ycxkfk=(select YCXK_FK  from NHAPHANG where PK_SEQ=" + this.id + ") ";
			
					query+="declare @ycxknppfk int;";
			query += "set @ycxknppfk=(select YCXKNPP_FK  from NHAPHANG where PK_SEQ=" + this.id + ") ";
			
					query+="declare @cknppfk int;";
			query += "set @cknppfk=(select CHUYENKHONPP_FK  from NHAPHANG where PK_SEQ=" + this.id + ") ";
			
			query += "if(@chuyenkho_fk is not null) ";
			query += "begin ";
			query += "insert into @tb(sanpham_fk, dongia,thuevat,chietkhau)";
			query += "select sanpham_fk,dongia,0,0 from ERP_CHUYENKHO_SANPHAM where chuyenkho_fk=@chuyenkho_fk  ";
			query += "end ";
			query += " if(@ycxkfk is not null) ";
			query += "begin ";
			query += "insert into @tb(sanpham_fk, dongia,thuevat,chietkhau) ";
			query += "    select sanpham_fk,dongia,thueVAT,chietkhau from ERP_DONDATHANG_SANPHAM where dondathang_fk in  ( select ddh_fk from ERP_YCXUATKHO_DDH where ycxk_fk=@ycxkfk) ";
			query += "	end ";
			query += "if(@ycxknppfk is not null) ";
			query += "begin ";
			query += "insert into @tb(sanpham_fk, dongia,thuevat,chietkhau) ";
			query += " select sanpham_fk,dongia,thueVAT,chietkhau from ERP_DONDATHANGNPP_SANPHAM where dondathang_fk in  ( select ddh_fk from ERP_YCXUATKHONPP_DDH where ycxk_fk=@ycxknppfk)";
			query += "end ";
			query += "if(@cknppfk is not null) ";
			query += "begin ";
			
			query += "insert into @tb(sanpham_fk, dongia,thuevat,chietkhau) ";
			query += "select sanpham_fk,dongia,0,0 from ERP_CHUYENKHONPP_SANPHAM where chuyenkho_fk =@cknppfk ";
			query += "end ";
			
					query += "select * from @tb ";
			
					query += "inner join ";
			query += "(select b.PK_SEQ, b.MA, b.TEN, c.DONVI, dongia, isnull(a.SOLO, 'NA') as SOLO, a.SOLUONG,";
			query += "ISNULL(soluongNHAN, a.SOLUONG) as soluongNHAN, isnull(a.loai, 0) as loai,";
			query += " isnull(a.SCHEME, '') as SCHEME,isnull(a.NgayHetHan,'') as spNGAYHETHAN, a.ID  ";
			query += "from NHAPHANG_SP a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ 		";
			query += "inner join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ  where a.NHAPHANG_FK = '" + this.id + "') b on b.PK_SEQ = sanpham_fk  ";
			*/
			
			  query =
			  "  select a.thuevat,0 chietkhau,b.PK_SEQ, b.MA, b.TEN, c.DONVI, isnull(a.DONGIA, 0) as DONGIA, isnull(a.SOLO, 'NA') as SOLO, a.SOLUONG, ISNULL(soluongNHAN, a.SOLUONG) as soluongNHAN, isnull(a.loai, 0) as loai, isnull(a.SCHEME, '') as SCHEME,isnull(a.NgayHetHan,'') as spNGAYHETHAN, a.ID    "
			  +
			  "  from NHAPHANG_SP a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ "
			  + "		inner join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ " +
			  "  where a.NHAPHANG_FK = '" + this.id + "' ";
			 
			
			System.out.println("---INIT NK: " + query);
			ResultSet spRs = db.get(query);
			NumberFormat formater = new DecimalFormat("##,###,###");
			
			if(spRs != null)
			{
				try 
				{
					String spPK_SEQ = "";
					String spID = "";
					String spMA = "";
					String spTEN = "";
					String spDONVI = "";
					String spDONGIA = "";
					String spVAT = "";
					String spchietkhau = "";
					String spSOLO = "";
					String spXUAT = "";
					String spSOLUONG = "";
					String spLOAI = "";
					String spSCHEME = "";
					String spNGAYHETHAN = "";
					
					while(spRs.next())
					{
						spPK_SEQ += spRs.getString("ID") + "__";
						spID += spRs.getString("PK_SEQ") + "__";
						spMA += spRs.getString("MA") + "__";
						spTEN += spRs.getString("TEN") + "__";
						spDONVI += spRs.getString("DONVI") + "__";
						spDONGIA += spRs.getDouble("DONGIA") + "__";
						spXUAT += formater.format(spRs.getDouble("SOLUONG")) + "__";
						spSOLUONG += formater.format(spRs.getDouble("soluongNHAN")) + "__";
						spchietkhau += formater.format(spRs.getDouble("chietkhau")) + "__";
						spVAT += formater.format(spRs.getDouble("thuevat")) + "__";
						spLOAI += spRs.getString("LOAI") + "__";
						
						if(spRs.getString("SOLO").trim().length() > 0)
							spSOLO += spRs.getString("SOLO") + "__";
						else 
							spSOLO += " __";
						
						if(spRs.getString("spNGAYHETHAN").trim().length() > 0)
							spNGAYHETHAN += spRs.getString("spNGAYHETHAN") + "__";
						else 
							spNGAYHETHAN += " __";
										
						if(spRs.getString("SCHEME").trim().length() > 0)
							spSCHEME += spRs.getString("SCHEME") + "__";
						else
							spSCHEME += " __";
						
					}
					spRs.close();
					
					if(spMA.trim().length() > 0)
					{
						spPK_SEQ = spPK_SEQ.substring(0, spPK_SEQ.length() - 2);
						this.spPK_SEQ = spPK_SEQ.split("__");
						
						spID = spID.substring(0, spID.length() - 2);
						this.spId = spID.split("__");
						
						spMA = spMA.substring(0, spMA.length() - 2);
						this.spMa = spMA.split("__");
						
						spTEN = spTEN.substring(0, spTEN.length() - 2);
						this.spTen = spTEN.split("__");
						
						spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
						this.spDonvi = spDONVI.split("__");
						
						spDONGIA = spDONGIA.substring(0, spDONGIA.length() - 2);
						this.spDongia = spDONGIA.split("__");
						
						spSOLO = spSOLO.substring(0, spSOLO.length() - 2);
						this.spSolo = spSOLO.split("__");
						
						spXUAT = spXUAT.substring(0, spXUAT.length() - 2);
						this.spXuat = spXUAT.split("__");
						
						spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
						this.spSoluong = spSOLUONG.split("__");

						spLOAI = spLOAI.substring(0, spLOAI.length() - 2);
						this.spLoai = spLOAI.split("__");
						
						spSCHEME = spSCHEME.substring(0, spSCHEME.length() - 2);
						this.spSCheme = spSCHEME.split("__");
						
						spNGAYHETHAN = spNGAYHETHAN.substring(0, spNGAYHETHAN.length() - 2);
						this.spNgayHetHan = spNGAYHETHAN.split("__");
						
						spVAT = spVAT.substring(0, spVAT.length() - 2);
						this.spvat = spVAT.split("__");
						spchietkhau = spchietkhau.substring(0, spchietkhau.length() - 2);
						this.spchietkhau = spchietkhau.split("__");
					}
					
				} 
				catch (Exception e) {e.printStackTrace(); System.out.println("EXCEPTION SP: " + e.getMessage() ); }
				
			}
			
		}
		
		this.khonhanRs = db.get("select pk_seq, ten from KHO where pk_seq in " + util.quyen_kho(this.userId)+" order by pk_seq asc");
		
		
		this.getNppInfo();
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
	}
	
	public void init() 
	{
		String query = 
				"\n select SoChungTu,ngaychungtu as ngayyeucau, isnull(ghichu,'') as ghichu, npp_fk, isnull(ngaynhan, '') as ngaynhan, trangthai, isnull(ycxk_fk, chuyenkho_fk) as ycxk_fk, isnull(kho_fk, 100000) as kho_fk " +
				"\n	,	(	select top(1) isnull(a.loaidonhang,b.LoaiDonHang) as loaidh  from ERP_DONDATHANG a inner join ERP_CHUYENKHO b on b.ddh_fk=a.PK_SEQ  "+
				"\n		where b.PK_SEQ=NHAPHANG.CHUYENKHO_FK and b.trangthai=1 "+
				"\n )as LoaiDH  "+
				"\n from NHAPHANG where pk_seq = '" + this.id + "'";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ngayyeucau = rs.getString("ngayyeucau");
					this.ghichu = rs.getString("ghichu");
					this.ngaynhan = rs.getString("ngaynhan");
					String loaidh=rs.getString("loaidh")==null?"0":rs.getString("loaidh");
					if(loaidh.equals("4"))
						this.khonhanId="100001";
					else
					this.khonhanId = rs.getString("kho_fk");
					System.out.println("da vao day"+this.khonhanId);
					if(rs.getString("trangthai").equals("1"))
						this.sochungtu = rs.getString("SoChungTu");
					else // tu lay so chung tu view lên
					{
						query = LaySoChungTu();
						ResultSet rsChungTu = db.get(query);
						rsChungTu.next();
						this.sochungtu  = rsChungTu.getString("SoChungTu");
						rsChungTu.close();
					}
					this.loaiDh=rs.getString("loaiDh")==null?"":rs.getString("loaiDh");
					
				}
				rs.close();
				
				//INIT DDH
				query = "select ddh_fk from NHAPHANG_DDH where nhaphang_fk = '" + this.id + "' ";
				rs = db.get(query);
				System.out.println("quer __"+query);
				String ddhID = "";
				while(rs.next())
				{
					ddhID += rs.getString("ddh_fk") + ",";
				}
				rs.close();
				
				if(ddhID.trim().length() > 0)
				{
					this.ddhId = ddhID.substring(0, ddhID.length() - 1);
				}
				
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}

		this.createRs();
	
	}

	public void DBclose() {
		
		try{
			
			this.db.shutDown();
			
		}catch(Exception er){
			
		}
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getGhichu() {
		
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		
		this.ghichu = ghichu;
	}

	
	public String[] getSpId() {
		
		return this.spId;
	}

	
	public void setSpId(String[] spId) {
		
		this.spId = spId;
	}

	
	public String[] getSpMa() {
		
		return this.spMa;
	}

	
	public void setSpMa(String[] spMa) {
		
		this.spMa = spMa;
	}

	
	public String[] getSpTen() {
		
		return this.spTen;
	}

	
	public void setSpTen(String[] spTen) {
		
		this.spTen = spTen;
	}

	
	public String[] getSpDonvi() {
		
		return this.spDonvi;
	}

	
	public void setSpDonvi(String[] spDonvi) {
		
		this.spDonvi = spDonvi;
	}

	
	public String[] getSpSoluong() {
		
		return this.spSoluong;
	}

	
	public void setSpSoluong(String[] spSoluong) {
		
		this.spSoluong = spSoluong;
	}
	
	public String getDondathangId() {
		
		return this.ddhId;
	}

	
	public void setDondathangId(String kbhId) {
		
		this.ddhId = kbhId;
	}

	
	public ResultSet getDondathangRs() {
		
		return this.ddhRs;
	}

	
	public void setDondathangRs(ResultSet ddhRs) {
		
		this.ddhRs = ddhRs;
	}
	

	public String[] getSpTonKho() {
		
		return this.spTonkho;
	}

	
	public void setSpTonKho(String[] spTonkho) {
		
		this.spTonkho = spTonkho; 
	}

	
	public boolean update() 
	{
		if(this.ngaynhan.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn ngày nhận hàng";
			return false;
		}
		
		if(spId == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm nhập kho";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spId.length; i++)
			{
				if( spSoluong[i].trim().length() > 0 )
				{
					if(Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) > 0)
						coSP = true;
					
					if( Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) >  Double.parseDouble(spXuat[i].trim().replaceAll(",", "")) )
					{
						this.msg = "Số lương nhận của sản phẩm (" + spTen[i] + ") không được vượt quá số lượng xuất ";
						return false;
					}
				}
			}
			
			if(!coSP)
			{
				this.msg = "Không có sản phẩm nào được nhập số lượng nhận. Vui lòng kiểm tra lại.";
				return false;
			}	
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			
			
			
			String query = " Update NHAPHANG set ngaynhan = '" + this.ngaynhan + "', ngaysua = '" + this.getDateTime() + "', nguoisua = '" + this.userId + "', kho_fk = '" + this.khonhanId + "' where pk_seq = '" + this.id + "' ";
			
			System.out.println("1.Update NHAPHANG: " + query);
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật NHAPHANG " + query;
				db.getConnection().rollback();
				return false;
			}
			
			
			
			for(int i = 0; i < spId.length; i++)
			{
				String soluongNHAN = "0";
				if(spSoluong[i].trim().replaceAll(",", "").length() > 0)
					soluongNHAN = spSoluong[i].trim().replaceAll(",", "");
				
				/*query = "UPDATE NHAPHANG_SP set soluongNHAN = '" + soluongNHAN + "' " +
						"where nhaphang_fk = '" + this.id + "' and sanpham_fk = '" + spId[i] + "' and loai = '" + spLoai[i] + "' and solo = '" + spSolo[i] + "'  and NgayHetHan='"+spNgayHetHan[i]+"' ";
				
				System.out.println("3.Update NHAPHANG_SP: " + query);
				if(db.updateReturnInt(query)!=1)
				{
					this.msg = "Khong the cap nhat NHAPHANG_SP: " + query;
					db.getConnection().rollback();
					return false;
				}*/
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}
	
	public String LaySoChungTu()
	{
		String query =
			"\n		select  (select loainpp from NHAPHANPHOI where PK_SEQ= NHAPHANG.NPP_FK) as loainpp,case   "+ 
			"\n			when YCXK_FK IS not null then CAST( YCXK_FK   AS VARCHAR(50) )  "+
			"\n			when CHUYENKHO_FK IS not null then (SELECT SoChungTu from ERP_CHUYENKHO where PK_SEQ=NHAPHANG.CHUYENKHO_FK)  "+
			"\n			when YCXKNPP_FK IS not null then  "+
			"\n	 "+
			"\n			(     "+
			"\n				SELECT top(1) SOHOADON FROM ERP_HOADONNPP_DDH A    "+
			"\n					INNER JOIN  ERP_YCXUATKHONPP_DDH B ON A.DDH_FK=B.DDH_FK   "+
			"\n					INNER JOIN ERP_HOADONNPP C ON C.PK_SEQ=A.HOADONNPP_FK     "+
			"\n				WHERE C.TRANGTHAI IN (2,4)	AND B.YCXK_FK=NHAPHANG.YCXKNPP_FK  "+   
			"\n			)  "+  
			"\n			when CHUYENKHONPP_FK IS NOT NULL THEN (SELECT SoChungTu from ERP_CHUYENKHONPP where pk_seq=NHAPHANG.CHUYENKHONPP_FK) end  as SoChungTu  "+
			"\n			,YCXK_FK,CHUYENKHO_FK,YCXKNPP_FK,CHUYENKHONPP_FK, NPP_FK  "+
			"\n		from NHAPHANG  " +
			"\n 	where pk_Seq='"+this.id+"'   ";
		return query;
	}
	
	public boolean chot() 
	{
		getNppInfo();
		
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("NhapHang", this.id, "NgayNhan", db);
		if(msg.length()>0)
			return false;
		
		if(this.ngaynhan.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn ngày nhận hàng";
			return false;
		}
		
	/*	if(this.sochungtu.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số chứng từ";
			return false;
		}*/
		
		if(spId == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm nhập kho";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spId.length; i++)
			{
				if( spSoluong[i].trim().length() > 0 )
				{
					if(spSoluong[i].trim().replaceAll(",", "").length() > 0)
						coSP = true;
					
					if( Double.parseDouble(spSoluong[i].trim().replaceAll(",", "")) >  Double.parseDouble(spXuat[i].trim().replaceAll(",", "")) )
					{
						this.msg = "Số lương nhận của sản phẩm (" + spTen[i] + ") không được vượt quá số lượng xuất ";
						return false;
					}
				}
			}
			
			if(!coSP)
			{
				this.msg = "Không có sản phẩm nào được nhập số lượng nhận. Vui lòng kiểm tra lại.";
				return false;
			}	
		}
		
		try
		{
			//CHECK SO CHUNG TU
			
			String ycxk_fk = "";
			String npp_fk = "";
			String spIds = "";
			
			String query =LaySoChungTu();
			
			System.out.println("[SoCT]"+query);
			
			ResultSet rs = db.get(query);
		String loainpp="";
			if(rs.next())
			{
				ycxk_fk = rs.getString("SoChungTu")==null ? "": rs.getString("SoChungTu");
				npp_fk = rs.getString("NPP_FK");
				loainpp=rs.getString("loainpp");
			}
			
			if(rs != null)
			{				
				rs.close();	
			}
			
			/*if(ycxk_fk.length()<=0)
			{
				this.msg = "Số chứng từ chưa phát sinh!Vui lòng liên hệ TT!";
				return false;
			}
			*/
			/*if(!loainpp.equals("4"))
			{
				if(!this.sochungtu.trim().equals(ycxk_fk.trim()) )
				{
					this.msg = "Số chứng từ không hợp lệ";
					return false;
				}
			}
			*/


			db.getConnection().setAutoCommit(false);
			
			/*if(this.loaiDh.equals("4"))
			{
				this.khonhanId="100001";
			}*/
			
			// cập nhật trạng thái là đã chốt
			query = " Update NHAPHANG set  trangthai = '1' "+
					" where pk_seq = '" + this.id+ "' and trangthai=0 ";

			System.out.println("1.Update NHAPHANG: " + query);
			if (db.updateReturnInt(query) != 1)
			{
				this.msg = "Không thể cập nhật NHAPHANG " + query;
				db.getConnection().rollback();
				return false;
			}
			
			query="select isnull(YCXK_FK,0) as  YCXK_FK ,isnull(CHUYENKHO_FK,0) as  CHUYENKHO_FK ,isnull(CHUYENKHONPP_FK,0) CHUYENKHONPP_FK,isnull(YCXKNPP_FK,0) YCXKNPP_FK  from nhaphang where pk_seq= "+this.id;
			
			ResultSet rs1=db.get(query);
			while(rs1.next())
			{
				String YCXK_FK = rs1.getString("YCXK_FK");
				String CHUYENKHO_FK = rs1.getString("CHUYENKHO_FK");
				String CHUYENKHONPP_FK = rs1.getString("CHUYENKHONPP_FK");
				String YCXKNPP_FK =rs1.getString("YCXKNPP_FK");
				
				if(!YCXK_FK.equals("0"))
				{
					
				}
				if(!CHUYENKHO_FK.equals("0"))
				{
					query="update  ERP_CHUYENKHO set khonhan_fk="+this.khonhanId  +" where pk_Seq="+CHUYENKHO_FK;
					if(!db.update(query))
					{
						this.msg = "Khong the cap nhat NHAPHANG_SP: " + query;
						db.getConnection().rollback();
						return false;
					}
				}
				if(!CHUYENKHONPP_FK.equals("0"))
				{
					
				}
				if(!YCXKNPP_FK.equals("0"))
				{
					
				}
			}
			
			// HÀNG KHUYẾN MẠI THÌ VẪN VÔ KHO LÚC TẠO NHẬN HÀNG
			query = " UPDATE NHAPHANG_SP SET KHONHAN_FK = '" + this.khonhanId + "' "+
		    		" WHERE NHAPHANG_fK = '" + this.id + "' and isnull(SCHEME,'') = ''   " ;
			
			System.out.println(":::>"+query);
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat NHAPHANG_SP: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			// NHập vô kho nhận được chọn
			query = " UPDATE NHAPHANG_SP SET KHONHAN_FK = '" + this.khonhanId + "' "+
		    		" WHERE NHAPHANG_fK = '" + this.id + "' and KHONHAN_FK IS NULL " ;
			
			System.out.println(":::"+query);
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat NHAPHANG_SP: " + query;
				db.getConnection().rollback();
				return false;
			}
			
			//NẾU LÔ NA THÌ PHẢI CHO VỀ NGÀY HẾT HẠN 2030-12-31
			query = "UPDATE NHAPHANG_SP set ngayhethan = '2030-12-31',solo = 'NA' " +
					"where nhaphang_fk = '" + this.id + "' and solo = 'NA' ";
			
			System.out.println(":::"+query);
			int up = db.updateReturnInt(query);
			if(up < 0)
			{
				this.msg = "Khong the cap nhat NHAPHANG_SP: " + query;
				db.getConnection().rollback();
				return false;
			}
			System.out.println(":::NHAPHANG_SP = "+up);
			
			for(int i = 0; i < spId.length; i++)
			{
				String soluongNHAN = "0";
				if(spSoluong[i].trim().replaceAll(",", "").length() > 0)
					soluongNHAN = spSoluong[i].trim().replaceAll(",", "");
				
				if(spSolo[i].toUpperCase().trim().equals("NA"))
				{
					spSolo[i] = "NA";
					spNgayHetHan[i] = "2030-12-31";
				}
				
				/*//TANG KHO
				String kho_fk = "";
				if(spLoai[i].equals("1"))  //SPKM
					kho_fk = " select khonhan_fk from NHAPHANG_SP where nhaphang_fk = '" + this.id + "' and sanpham_fk = '" + spId[i] + "' and solo = '" + spSolo[i] + "' and isnull(scheme,'') = '" + spSCheme[i] + "' and NgayHetHan='"+spNgayHetHan[i]+"' AND ID = '" + spPK_SEQ[i] + "' ";
				else
					kho_fk = " select khonhan_fk from NHAPHANG_SP where nhaphang_fk = '" + this.id + "' and sanpham_fk = '" + spId[i] + "' and solo = '" + spSolo[i] + "' and isnull(loai,0) = '0'  and NgayHetHan='"+spNgayHetHan[i]+"' AND ID = '" + spPK_SEQ[i] + "'  ";
				*/
				if(spLoai[i].equals("1"))
				{
					//style = " class='mySCHME' ";
					//kho = "Hàng KM";
				}
				else
				{
					//kho = "Hàng bán";
				}
		/*		query = "select a.sanpham_fk  " +
							"from NHAPP_KHO a " +
							"where soluongNXT < 0 and npp_fk = ( select NPP_FK from NHAPHANG where PK_SEQ = '" + this.id + "' )  " +
									" and sanpham_fk = '" + spId[i] + "' and kho_fk in ( " + kho_fk + " ) and kbh_fk = ( select KBH_FK from NHAPHANG where PK_SEQ = '" + this.id + "' ) ";
				
				System.out.println("---CHECK NHA AM TON KHO: " + query);
				boolean daxulyAMKHO = true;

				ResultSet rsCHECK_NXT = db.get(query);
				if(rsCHECK_NXT != null)
				{
					if(rsCHECK_NXT.next())
					{
						spIds += rsCHECK_NXT.getString("sanpham_fk") + ",";
					}
					rsCHECK_NXT.close();
				}*/
				
		
				
				
				
				// LẤY LẠI NGÀY NHẬP KHO, KHO NHẬN TRƯỚC KHI TĂNG KHO
				query = " Update NHAPHANG set SoChungTu='" + this.sochungtu + "',ngaynhan = '" + this.ngaynhan + "', kho_fk = '" + this.khonhanId + "', ngaysua = '" + this.getDateTime() + "', nguoisua = '" + this.userId + "' " + 
				" where pk_seq = '" + this.id+ "' ";

				System.out.println("1.Update NHAPHANG: " + query);
				if (db.updateReturnInt(query) != 1)
				{
					this.msg = "Không thể cập nhật NHAPHANG " + query;
					db.getConnection().rollback();
					return false;
				}
				 
			 
				query="update NHAPHANG_SP set SOLO='"+spSolo[i]+"',ngaynhapkho = '"+ngaynhan+"',ngayhethan='"+spNgayHetHan[i]+"' where nhaphang_fk='"+this.id+"' ";
				if(db.updateReturnInt(query) < 1 )
				{
					this.msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
					db.getConnection().rollback();
					return false;
				}	
			}
			
			
			/// CẬP NHẬT LẠI SỐ LƯỢNG KHO CHI TIẾT :
			query="select a.khonhan_fk kho_fk,b.ngaynhan, b.NPP_FK, a.SANPHAM_FK, a.soluong as soluongNHAN, 0, b.KBH_FK, a.SOLO,  Ngayhethan " +
			" from NHAPHANG_SP a inner join NHAPHANG b on a.NHAPHANG_FK = b.PK_SEQ  " +
			" where b.PK_SEQ = '" + this.id + "'  ";
			
			System.out.println("query la "+query);
			ResultSet nhspRs= db.get(query);
			boolean davo = false;
			while (nhspRs.next())
			{
				String khoId= nhspRs.getString("kho_fk");
				String nppId= nhspRs.getString("NPP_FK");
				String spId= nhspRs.getString("SANPHAM_FK");
				double soluong= nhspRs.getDouble("soluongNHAN");
				String kbhId= nhspRs.getString("KBH_FK");
				String solo= nhspRs.getString("SOLO");
				String ngaynhan= nhspRs.getString("ngaynhan");
				String ngayhethan= nhspRs.getString("Ngayhethan");
				
				
				// LẤY NGÀY NHẬP KHO CHO NHAPHANG_SP TRƯỚC
				
				
				this.msg=util.Update_NPP_Kho_Sp_Chitiet(ngaynhan , "Nhận hàng", db, khoId,spId , nppId, kbhId, solo, ngayhethan, ngaynhan, soluong, 0.0, soluong, soluong, 0.0);
				
				if(this.msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
				
				this.msg=util.Update_NPP_Kho_Sp(ngaynhan, "Nhận hàng", db, khoId, spId, npp_fk, kbhId, soluong, 0.0, soluong, 0.0);
				
				if(this.msg.length()>0)
				{
					db.getConnection().rollback();
					return false;
				}
				davo = true;
			}
			if(!davo)
			{
				this.msg = "Không thể tăng kho:"+ query;
				db.getConnection().rollback();
				return false;
			}
			
			
			 
			 
			//KHI CÓ NHẬP HÀNG SẼ FIX TỰ ĐỘNG NHỮNG NHÀ ÂM TỒN KHO
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			//Xứ lý những sản phẩm âm kho khi có nhập hàng --> bat buoc phai commit TRAN mới thực hiện xử lý tiếp
			//////chỗ này chỉ nên xử lý những SP có trong nhập hàng, nếu không những SP khác cũng sẽ sinh ra các đổi số lô nếu có không cần thiết
			if(spIds.trim().length() > 0)
			{
			/*	spIds = spIds.substring(0, spIds.length() - 1);
				
				FixData fixed = new FixData();
				String msgERROR = fixed.ProcessDATA(npp_fk, spIds, db, "0");
				
				if(msgERROR.trim().length() > 0)
				{
					System.out.println("---SENDDDDDDDDDD MAILLLLLLLL");
					SendMail mail = new SendMail();
					
					String msg = "Hệ thống đã chạy chế độ tự sửa lỗi tồn kho. Nhưng gặp lỗi khi chạy của NPP ( " + npp_fk + " ). Vui lòng kiểm tra và xử lý gấp.";
					mail.postMailHTML("vudq@geso.us,xuantvt@geso.us,taiba@geso.us,hienttd@geso.us", "haind@geso.us,luonghv@geso.us", "TraphacoDMS chạy tồn kho tự động ", msg);
				}*/
			}
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public String[] getSpLoai() {

		return this.spLoai;
	}


	public void setSpLoai(String[] spLoai) {

		this.spLoai = spLoai;
	}

	
	public String[] getSpScheme() {
		
		return this.spSCheme;
	}

	
	public void setSpScheme(String[] spScheme) {
		
		this.spSCheme = spScheme;
	}


	public String[] getSpSolo() {
		
		return this.spSolo;
	}

	
	public void setSpSolo(String[] spSolo) {
		
		this.spSolo = spSolo;
	}

	
	public String[] getSpXuat() {
		
		return this.spXuat;
	}

	
	public void setSpXuat(String[] spXuat) {
		
		this.spXuat = spXuat;
	}

	
	public String[] getSpDongia() {
		
		return this.spDongia;
	}

	
	public void setSpDongia(String[] spDongia) {
		
		this.spDongia = spDongia;
	}

	
	public boolean create() {
		
		return false;
	}

	
	public String getNgaynhap() {
		
		return this.ngaynhan;
	}

	
	public void setNgaynhap(String ngaynhap) {
		
		this.ngaynhan = ngaynhap;
	}

	
	public String getSochungtu() {
		
		return this.sochungtu;
	}

	
	public void setSOchungtu(String sochungtu) {
		
		this.sochungtu = sochungtu;
	}

	
	public String getKhonhanId() {
		
		return this.khonhanId;
	}

	
	public void setKhonhanId(String khonhanId) {
		
		this.khonhanId = khonhanId;
	}

	
	public ResultSet getKhonhanRs() {
		
		return this.khonhanRs;
	}

	
	public void setKhonhanRs(ResultSet khonhanRs) {
		
		this.khonhanRs = khonhanRs;
	}
	String[] spNgayHetHan;

	public String[] getSpNgayHetHan()
  {
  	return spNgayHetHan;
  }

	public void setSpNgayHetHan(String[] ngayHetHan)
  {
  	this.spNgayHetHan = ngayHetHan;
  }
		
	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	String loaiDh;
	public String getLoaiDh()
  {
  	return loaiDh;
  }

	public void setLoaiDh(String loaiDh)
  {
  	this.loaiDh = loaiDh;
  }

	@Override
	public String[] getPK_SEQ() {
		// TODO Auto-generated method stub
		return this.spPK_SEQ;
	}

	@Override
	public void setPK_SEQ(String[] pk_seq) {
		this.spPK_SEQ = pk_seq;
	}
	public ResultSet getKhuyenMaiRs()
	{
		if( this.id != null && this.id.length() > 0)
		{
			String query =  
							"\n select N'' MA , N'Trả tiền' TEN, '' as donvi, a.DIENGIAI scheme , 1 soluong, a.giatri tonggiatri " +
							"\n from NhapHang_ChietKhau a  " +
							"\n where a.NHAPHANG_FK = '" + this.id + "'  " ;
			System.out.println("--getKMRS: " + query);
			return db.get(query);
		}
		return null;
	}	
}
