package geso.dms.center.beans.nhapkho.imp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.nhapkho.IErpNhapkho;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class ErpNhapkho implements IErpNhapkho
{
	private static final long serialVersionUID = 1L;
	String userId;
	String id;
	
	String ngayyeucau;
	String checkupload;


	String ghichu;
	
	String lydo = "";
	String sochungtugoc ="";
	
	String msg;
	String trangthai;
	
	String khoNhanId;
	ResultSet khoNhanRs;

	String[] spId;
	String[] spMa;
	String[] spTen;
	String[] spDonvi;
	String[] spSoluong;
	String[] spGianhap;
	String[] spSolo;
	String[] spHansudung;
	String[] spNgaysanxuat;
	String[] spNgayhethan;
	
	Hashtable<String, String> sp_chitiet;
	
	dbutils db;
	
	public ErpNhapkho()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.checkupload = "";
		this.msg = "";
		this.trangthai = "0";
		
		this.sp_chitiet = new Hashtable<String, String>();
		this.db = new dbutils();
	}
	
	public ErpNhapkho(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.khoNhanId = "";
		this.msg = "";
		this.trangthai = "0";
		this.checkupload = "";

		this.sp_chitiet = new Hashtable<String, String>();
		this.db = new dbutils();
	}
	public String getCheckupload() {
		return checkupload;
	}

	public void setCheckupload(String checkupload) {
		this.checkupload = checkupload;
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

	public String getKhoNhapId()
	{
		return this.khoNhanId;
	}

	public void setKhoNhapId(String khonhaptt) 
	{
		this.khoNhanId = khonhaptt;
	}

	public ResultSet getKhoNhapRs() 
	{
		return this.khoNhanRs;
	}

	public void setKhoNHapRs(ResultSet khonhapRs) 
	{
		this.khoNhanRs = khonhapRs;
	}
	
	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean createNK() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày nhập kho";
			return false;
		}
		
		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho nhận";
			return false;
		}
		
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm nhập";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 )
				{
					if(spSoluong[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					coSP = true;
				}
			}
			
			if(!coSP)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm";
				return false;
			}
			
			//CHECK TRUNG MA + TRUNG SO LO
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0)
				{
					String ct = sp_chitiet.get(spMa[i]);
					if(ct != null)
                	{
                		String[] ctARR = ct.substring(0, ct.length() - 3).split("___");
                		for(int j = 0; j <  ctARR.length - 1; j++ )
                		{
                			for(int k = j + 1; k < ctARR.length; k++ )
                			{
                				if( ctARR[j].contains("__") && ctARR[j].contains("__") )
                				{
                					String[] _ct = ctARR[j].split("__"); 
                					String[] _ct2 = ctARR[k].split("__"); 
                    				
                    				if( _ct[1].trim().equals(_ct2[1].trim()) )
                        			{
    									this.msg = "Sản phẩm : " + spTen[i] + " , Với số lô (" + _ct2[1].trim() + ") đã bị trùng. Vui lòng kiểm tra lại ";
    									return false;
                        			}
                				}
                			}
                		}
                	}
				}
			}
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			Object[] data = null;
			data = dbutils.setObject(this.ngayyeucau,this.sochungtugoc,this.lydo,this.ghichu,this.khoNhanId,this.userId,this.userId);
		
			String query = " insert ERP_NHAPKHO(ngaynhap, SoChungTuGoc, lydo, ghichu, trangthai, khonhap_fk, ngaytao, nguoitao, ngaysua, nguoisua,import) " +
						   " values(?, ?, ?, ?, '0', ?, '" + getDateTime() + "', ?, '" + getDateTime() + "', ?,1 )";
			
			System.out.println("1.Insert NK: " + query);
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể tạo mới ERP_NHAPKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			/*query = "select SCOPE_IDENTITY() as btId";
			ResultSet rsBtId = db.get(query);	*/
			
			//rsBtId.next();
		    String nhapkhoCurrentId = db.getPk_seq();
		    //rsBtId.close();
			
		  
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0)
				{
					String ct = sp_chitiet.get(spMa[i]);
					if(ct != null)
                	{
                		String[] ctARR = ct.substring(0, ct.length() - 3).split("___");
                		for(int j = 0; j <  ctARR.length; j++ )
                		{
                			String[] _ct = ctARR[j].split("__"); 
                			System.out.println("san pham la "+_ct[0] +"__"+_ct[1] +"__" + _ct[2]);
                			if(_ct[0].trim().length() > 0 && _ct[1].trim().length() > 0 && _ct[2].trim().length() > 0 )
                			{
                				data = null;
                				data = dbutils.setObject(spDonvi[i].trim(),_ct[0].trim().replaceAll(",", ""),_ct[1].trim(),_ct[2].trim(),_ct[3].trim(),this.ngayyeucau,spMa[i].trim());
                			
	                			query = "insert ERP_NHAPKHO_SANPHAM( nhapkho_fk, SANPHAM_FK, DVDL_FK, soluong, gianhap, solo, ngaysanxuat, ngayhethan,ngaynhapkho ) " +
										"select IDENT_CURRENT('ERP_NHAPKHO'), pk_seq,ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = ? ), DVDL_FK ), ?, '0', ?, ?,  ?,? " +
										"from SANPHAM where MA = ? ";
								
								System.out.println("1.Insert NK - SP: " + query);
								if(db.update_v2(query,data) < 0)
								{
									this.msg = "Khong the tao moi ERP_NHAPKHO_SANPHAM: " + query;
									db.getConnection().rollback();
									return false;
								}
                			}
                			
                		}
                	}
				}
			}
			
			data = null;
			data = dbutils.setObject(nhapkhoCurrentId);
			query="select count(*) sl from ERP_NHAPKHO_SANPHAM where nhapkho_fk=? and (ngayhethan=ngaysanxuat  or ngaynhapkho is null )";
			ResultSet rscsl=db.get_v2(query,data);
			rscsl.next();
			if(rscsl.getInt("sl")>0)
			{
				db.getConnection().rollback();
				rscsl.close();
				this.msg = "ngày hết hạn và ngày nhập kho đang trùng nhau" ;
				return false;
			}
			rscsl.close();
			
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

	public boolean updateNK() 
	{
		if(this.ngayyeucau.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập ngày nhập kho";
			return false;
		}
		
		if( this.khoNhanId.trim().length() <= 0 )
		{
			this.msg = "Vui lòng chọn kho nhận";
			return false;
		}
		
		if(spMa == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm nhập";
			return false;
		}
		else
		{
			boolean coSP = false;
			for(int i = 0; i < spMa.length; i++)
			{
				if( spMa[i].trim().length() > 0 )
				{
					if(spSoluong[i].trim().length() <= 0)
					{
						this.msg = "Bạn phải nhập số lượng của sản phẩm ( " + spTen[i] + " )";
						return false;
					}
					
					coSP = true;
				}
			}
			
			if(!coSP)
			{
				this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm";
				return false;
			}
			
			//CHECK TRUNG MA + TRUNG SO LO
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0)
				{
					String ct = sp_chitiet.get(spMa[i]);
					if(ct != null)
                	{
                		String[] ctARR = ct.substring(0, ct.length() - 3).split("___");
                		System.out.println("ct la "+ct);
                		for(int j = 0; j <  ctARR.length - 1; j++ )
                		{
                			System.out.println("gia tri la " + ctARR[j] );
                		}
                		for(int j = 0; j <  ctARR.length - 1; j++ )
                		{
                			for(int k = j + 1; k < ctARR.length; k++ )
                			{
                				if( ctARR[j].contains("__") && ctARR[j].contains("__") )
                				{
                					String[] _ct = ctARR[j].split("__"); 
                					String[] _ct2 = ctARR[k].split("__"); 
                    				
                    				if( _ct[1].trim().equals(_ct2[1].trim()) )
                        			{
    									this.msg = "Sản phẩm : " + spTen[i] + " , Với số lô (" + _ct2[1].trim() + ") đã bị trùng. Vui lòng kiểm tra lại ";
    									return false;
                        			}
                				}
                			}
                		}
                	}
				}
			}	
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			
			if(!Utility.KiemTraNgayChungTuSoNet(db,this.userId,"1","PhieuNhap_SoChungTu",this.ngayyeucau, this.id,"ERP_NHAPKHO" ))
			{
				this.db.getConnection().rollback();
				this.msg="Không được đổi ngày khác tháng với số chứng từ đã phát sinh!";
				return false;
			}
			
			
			
			
			String khonhan_fk = this.khoNhanId.trim().length() <= 0 ? "null" : this.khoNhanId.trim();
			
			Object[] data = null;
			data = dbutils.setObject(this.lydo,this.sochungtugoc,this.ngayyeucau,this.ghichu,khonhan_fk,this.userId,this.id);
			String query =	" Update ERP_NHAPKHO set  lydo =?,SoChungTuGoc =?, ngaynhap = ?, ghichu = ?, " +
							" khonhap_fk = ?, ngaysua = '" + getDateTime() + "', nguoisua = ? " + 
							" where pk_seq = ? ";
		
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể cập nhật ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return false;
			}
			
			data = null;
			data = dbutils.setObject(this.id);
						
			query = "delete ERP_NHAPKHO_SANPHAM where nhapkho_fk = ?";
			if(db.update_v2(query,data) < 0)
			{
				this.msg = "Không thể cập nhật ERP_NHAPKHO_SANPHAM " + query;
				db.getConnection().rollback();
				return false;
			}
			
			for(int i = 0; i < spMa.length; i++)
			{
				if(spMa[i].trim().length() > 0)
				{
					String ct = sp_chitiet.get(spMa[i]);
					if(ct != null)
                	{
                		String[] ctARR = ct.substring(0, ct.length() - 3).split("___");
                		for(int j = 0; j <  ctARR.length; j++ )
                		{
                			String[] _ct = ctARR[j].split("__"); 
                			
                			if(_ct[0].trim().length() > 0 && _ct[1].trim().length() > 0 && _ct[2].trim().length() > 0 && _ct[3].trim().length() > 0 )
                			{
                				data = null;
                				data = dbutils.setObject(this.id,spDonvi[i].trim(),_ct[0].trim().replaceAll(",", ""),_ct[1].trim(),_ct[2].trim(),_ct[3].trim(),this.ngayyeucau,spMa[i].trim());
	                			query = "insert ERP_NHAPKHO_SANPHAM( nhapkho_fk, SANPHAM_FK, DVDL_FK, soluong, gianhap, solo, ngaysanxuat, ngayhethan,ngaynhapkho ) " +
										"select ?, pk_seq, ISNULL( ( select pk_Seq from DONVIDOLUONG where donvi = ? ), DVDL_FK ), ?, '0', ?, ?,  ?,? " +
										"from SANPHAM where MA = ? ";
								
								System.out.println("1.Insert NK - SP: " + query);
								if(db.update_v2(query,data) < 0)
								{
									this.msg = "Khong the tao moi ERP_NHAPKHO_SANPHAM: " + query;
									db.getConnection().rollback();
									return false;
								}
                			}
                			
                		}
                	}
				}
			}
			
			data = null;
			data = dbutils.setObject(this.id);
			query="select count(*) sl from ERP_NHAPKHO_SANPHAM where nhapkho_fk=? and ( ngayhethan=ngaysanxuat or ngaynhapkho is null ) ";
			ResultSet rscsl=db.get_v2(query,data);
			rscsl.next();
			if(rscsl.getInt("sl")>0)
			{
				db.getConnection().rollback();
				rscsl.close();
				this.msg = "ngày hết hạn và ngày nhập kho đang trùng nhau" ;
				return false;
			}
			rscsl.close();
			
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


	public void createRs() 
	{
		Utility util =new Utility(); 
		String query = "select PK_SEQ, TEN from kho  WHERE trangthai = '1' AND PK_sEQ IN "+util.quyen_khoTT(userId,util.KICHHOAT)+" ";
		System.out.println("khoNhanRs: "+query);
		this.khoNhanRs = db.get(query);
		
		this.dvtRs = db.getScrol("select PK_SEQ, DONVI from DONVIDOLUONG where trangthai = '1' ");
		
		
	}

	private void createChuyenKho_SanPham() 
	{
		String query =  "select b.MA, b.TEN, DV.donvi, a.soluong, a.gianhap, a.solo, a.ngaysanxuat, a.ngayhethan, isnull(b.hansudung, 0) as hansudung    " +
						" from ERP_NHAPKHO_SANPHAM a inner Join SanPham b on a.SANPHAM_FK = b.PK_SEQ    " +
						" 		INNER JOIN DONVIDOLUONG DV ON DV.PK_SEQ = a.DVDL_FK       " +
						"where a.NHAPKHO_FK = '" + this.id + "' ";
		
		System.out.println("---INIT SP: " + query);
		ResultSet spRs = db.get(query);
		
		NumberFormat formater = new DecimalFormat("##,###,###.##");
		if(spRs != null)
		{
			try 
			{
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spHANSUDUNG = "";
				String spNGAYHETHAN = "";
				String spSOLO = "";
				Hashtable<String, String> sp_chitiet = new Hashtable<String, String>();
				while(spRs.next())
				{
					if(!spMA.contains(spRs.getString("MA")) && !spTEN.contains(spRs.getString("TEN")) )
					{
						spMA += spRs.getString("MA") + "__";
						spTEN += spRs.getString("TEN") + "__";
						spDONVI += spRs.getString("DONVI") + "__";
						spHANSUDUNG += spRs.getString("hansudung") + "__";
						spNGAYHETHAN += spRs.getString("NGAYHETHAN") + "__";
						spSOLO+=spRs.getString("solo") + "__";
					}
					
					String ct = sp_chitiet.get(spRs.getString("MA"));
					if(ct == null)
						ct = "";
					
					if(ct.trim().length() <= 0)
					{
						ct = formater.format(spRs.getDouble("SOLUONG")) + "__" + spRs.getString("SOLO") + "__" + spRs.getString("NGAYSANXUAT") +"__" +spRs.getString("ngayhethan") + "___";
						sp_chitiet.put(spRs.getString("MA"), ct);
					}
					else
					{
						ct += formater.format(spRs.getDouble("SOLUONG")) + "__" + spRs.getString("SOLO") + "__" + spRs.getString("NGAYSANXUAT") + "__" +spRs.getString("ngayhethan") + "___";
						sp_chitiet.put(spRs.getString("MA"), ct);
					}
				}
				spRs.close();
				
				if(spMA.trim().length() > 0)
				{
					spMA = spMA.substring(0, spMA.length() - 2);
					this.spMa = spMA.split("__");
					
					spTEN = spTEN.substring(0, spTEN.length() - 2);
					this.spTen = spTEN.split("__");
					
					spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
					this.spDonvi = spDONVI.split("__");
					
					spHANSUDUNG = spHANSUDUNG.substring(0, spHANSUDUNG.length() - 2);
					this.spHansudung = spHANSUDUNG.split("__");
					
					
					spNGAYHETHAN = spNGAYHETHAN.substring(0, spNGAYHETHAN.length() - 2);
					this.spNgayhethan = spHANSUDUNG.split("__");
					
					spSOLO = spSOLO .substring(0, spSOLO .length() - 2);
					this.spSolo = spSOLO.split("__");
					
					this.sp_chitiet = sp_chitiet;
				}
				
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
		}
		
		//System.out.println("---MA SP: " + this.spMa.length);
		
	}

	public void init() 
	{
		String query =  "select ngaynhap, ISNULL(SoChungTuGoc, '') as SoChungTuGoc" +
						", ISNULL(lydo, '') as lydo, ISNULL(ghichu, '') as ghichu, khonhap_fk, trangthai,isnull(import,0) as import " +
						"from ERP_NHAPKHO where pk_seq = '" + this.id + "'";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					this.ngayyeucau = rs.getString("ngaynhap");
					this.ghichu = rs.getString("ghichu");
					this.khoNhanId = rs.getString("khonhap_fk");
					this.trangthai = rs.getString("trangthai");
					this.checkupload = rs.getString("import");
					this.lydo = rs.getString("lydo");
					this.sochungtugoc = rs.getString("SoChungTuGoc");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("---LOI INIT: " + e.getMessage());
			}
		}
		
		this.createRs();
		
		this.createChuyenKho_SanPham();
		
	}

	public void DBclose() {
		
		try{
			
			if(khoNhanRs!=null){
				khoNhanRs.close();
			}
			
			this.db.shutDown();
			
		}catch(Exception er){
			
		}
	}
	
	public String getDateTime() 
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

	
	public String[] getSpGianhap() {
		
		return this.spGianhap;
	}

	
	public void setSpGianhap(String[] spGianhap) {
		
		this.spGianhap = spGianhap;
	}

	
	public String[] getSpSolo() {
		
		return this.spSolo;
	}

	
	public void setSpSolo(String[] spSolo) {
		
		this.spSolo = spSolo;
	}

	
	public String[] getSpNgaysanxuat() {
		
		return this.spNgaysanxuat;
	}

	
	public void setSpNgaysanxuat(String[] spNgaysanxuat) {
		
		this.spNgaysanxuat = spNgaysanxuat;
	}

	
	public String[] getSpNgayhethan() {
		
		return this.spNgayhethan;
	}

	
	public void setSpNgayhethan(String[] spNgayhethan) {
		
		this.spNgayhethan = spNgayhethan;
	}

	
	public String[] getSpHansudung() {
		
		return this.spHansudung;
	}


	public void setSpHansudung(String[] spHansudung) {
		
		this.spHansudung = spHansudung;
	}

	
	public Hashtable<String, String> getSp_Chitiet() {
		
		return this.sp_chitiet;
	}

	
	public void setSp_Chitiet(Hashtable<String, String> sp_chitiet) {
		
		this.sp_chitiet = sp_chitiet;
	}
	
	ResultSet dvtRs;

	public ResultSet getDvtRs() {

		return this.dvtRs;
	}
	
	public void setDvtRs(ResultSet dvtRs) {
		
		this.dvtRs = dvtRs;
	}
	
	public String getSochungtuGoc() {
		return sochungtugoc;
	}
	public void setSochungtuGoc(String sochungtu) {
		this.sochungtugoc = sochungtu;
	}
	public String getLydo() {
		return lydo;
	}
	public void setLydo(String lydo) {
		this.lydo = lydo;
	}
	
}
