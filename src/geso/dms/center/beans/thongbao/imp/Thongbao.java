package geso.dms.center.beans.thongbao.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import geso.dms.center.beans.thongbao.IThongbao;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class Thongbao implements IThongbao
{
	String ID, USERID, TRANGTHAI, TIEUDE, NOIDUNG, MSG, NGAYBATDAU, NGAYKETTHUC, KHUVUC, VUNG, SOLUONG;
	String nhanvienIds;
	dbutils db;
	String file;
	ResultSet thongbaonhanvienRs;
	ResultSet khuvucRs;
	ResultSet vungRs;
	
	String loaithongbao;
	String hethieuluc;
	String chinhanh;
	String doitac;
	
	ResultSet vbhdRs;
	String vbhdId;
	
	ResultSet vbccRs;
	String vbccId;
	
	ResultSet vbttRs;
	String vbttId;
	
	ResultSet vbsdbsRs;
	String vbsdbsId;
	
	String viewMode;
	
	String cancu;
	String huongdan;
	String thaythe;
	String suadoi;
	
	String duoccancuTu;
	String duochuongdanTu;
	String duocsuadoiTu;
	String duocthaytheTu;
	
	String noidungTL;
	ResultSet thaoluanRs;
	
	ResultSet nguoinhanTLRs;
	String nguoinhanTlId;
	
	ResultSet ddkdRs;
	String ddkdId;
	
	ResultSet nvgnRs;
	String[] nvgnIds;
	String nvgnId = "";
	ResultSet nppRs;
	String nppId = "";

	
	public Thongbao(String id)
	{
		this.db = new dbutils();
		this.ID = id;
		this.USERID = "";
		this.TRANGTHAI = "";
		this.TIEUDE = "";
		this.file = "";
		this.VUNG = "";
		this.NOIDUNG = "";
		this.NGAYBATDAU = "";
		this.NGAYKETTHUC = "";
		this.KHUVUC = "";
		this.MSG = "";		
		this.nhanvienIds = "";
		this.loaithongbao = "";
		this.vbhdId = "";
		this.vbccId = "";
		this.vbttId = "";
		this.vbsdbsId = "";
		this.hethieuluc = "";
		this.chinhanh="";
		this.doitac="";
		
		this.viewMode = "1"; //Center
		
		this.cancu = "0";
		this.thaythe = "0";
		this.huongdan = "0";
		this.suadoi = "0";
		
		this.duoccancuTu = "";
		this.duochuongdanTu = "";
		this.duocsuadoiTu = "";
		this.duocthaytheTu = "";
		
		this.nguoinhanTlId = "";
		this.noidungTL = "";
		this.ddkdId = "";
		this.ttId="";
		this.loaiNv="";
	}
	
	public Thongbao()
	{
		this.db=new dbutils();
		this.ID="";
		this.file="";
		this.USERID="";
		this.TRANGTHAI="";
		this.TIEUDE="";
		this.VUNG="";
		this.NOIDUNG="";
		this.NGAYBATDAU="";
		this.NGAYKETTHUC="";
		this.KHUVUC="";
		this.MSG="";
		
		this.nhanvienIds = "";
		this.loaithongbao = "";
		this.vbhdId = "";
		this.vbccId = "";
		this.vbttId = "";
		this.vbsdbsId = "";
		this.hethieuluc = "";
		this.chinhanh="";
		this.doitac="";
		
		this.cancu = "0";
		this.thaythe = "0";
		this.huongdan = "0";
		this.suadoi = "0";
		
		this.duoccancuTu = "";
		this.duochuongdanTu = "";
		this.duocsuadoiTu = "";
		this.duocthaytheTu = "";
		
		this.viewMode = "1"; //Center
		
		this.nguoinhanTlId = "";
		this.noidungTL = "";
		this.ddkdId = "";
		this.ttId="";
		this.loaiNv="";
	}
	
	public String getNppId() {
		return nppId;
	}
	public void setNppId(String nppId) {
		this.nppId = nppId;
	}
	public ResultSet getNppRs() {
		return nppRs;
	}
	public void setNppRs(ResultSet nppRs) {
		this.nppRs = nppRs;
	}
	public String getNvgnId() {
		return nvgnId;
	}
	public void setNvgnId(String nvgnId) {
		this.nvgnId = nvgnId;
	}
	public String[] getNvgnIds() {
		return nvgnIds;
	}
	public void setNvgnIds(String[] nvgnIds) {
		this.nvgnIds = nvgnIds;
	}
	public ResultSet getNvgnRs() {
		return nvgnRs;
	}
	public void setNvgnRs(ResultSet nvgnRs) {
		this.nvgnRs = nvgnRs;
	}
	public String getId() 
	{		
		return this.ID;
	}

	
	public void setId(String id) 
	{
		this.ID=id;		
	}

	
	public String getUserId() 
	{		
		return this.USERID;
	}

	
	public void setUserId(String userId) 
	{		
		this.USERID=userId;
	}

	
	public String getTrangthai() 
	{		
		return this.TRANGTHAI;
	}

	
	public void setTrangthai(String trangthai) 
	{		
		this.TRANGTHAI=trangthai;
	}

	
	public String getTieude() 
	{		
		return this.TIEUDE;
	}

	
	public void setTieude(String tieude)
	{	
		this.TIEUDE=tieude;
	}

	
	public String getNoidung() 
	{		
		return this.NOIDUNG;
	}

	
	public void setNoidung(String noidung)
	{	
		this.NOIDUNG=noidung;
	}

	
	public void init() 
	{		
		 String query = "select * from thongbao where PK_SEQ = '" + this.ID + "'";
		 ResultSet rs = db.get(query);
		 if(rs != null)
		 {
			 try
			 {
				while(rs.next())
				 {
					 this.NOIDUNG = rs.getString("noidung");
					 this.TIEUDE = rs.getString("tieude");
					 this.TRANGTHAI= rs.getString("trangthai");
					 this.NGAYBATDAU= rs.getString("ngaybatdau");
					 this.NGAYKETTHUC=rs.getString("ngayketthuc");
					 this.file=rs.getString("filename");
					 this.hethieuluc = rs.getString("hethieuluc");
					 this.loaithongbao = rs.getString("loaithongbao");
				 }
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Exception init: " + e.getMessage());
			}
			
			//NV Nhan Thong bao
			query = "select nhanvien_fk from thongbao_nhanvien where thongbao_fk = '" + this.ID + "'";
			System.out.println("QUERY NV THONGBAO: "+query);
			ResultSet rsnv = db.get(query);
			if(rsnv != null)
			{
				 try
				 {
					String nvId = ""; 
					while(rsnv.next())
					{
						 nvId += rsnv.getString("nhanvien_fk") + ",";
					}
					rsnv.close();
					
					if(nvId.trim().length() > 0)
					{
						nvId = nvId.substring(0, nvId.length() - 1);
						this.nhanvienIds = nvId;
						System.out.println("NHÂN VIÊN NHẬN: "+nvId);
					}
				} 
				catch (Exception e) {}
			 }
			
			//init DDKD
			query = "select ddkd_fk from THONGBAO_DDKD where thongbao_fk = '" + this.ID + "'";
			rs = db.get(query);
			try 
			{
				String ddkd = "";
				while(rs.next())
				{
					ddkd += rs.getString("ddkd_fk") + ",";
				}
				rs.close();
				
				if(ddkd.trim().length() > 0)
				{
					ddkd = ddkd.substring(0, ddkd.length() - 1);
					this.ddkdId = ddkd;
					System.out.println("___Dai dien KD: " + ddkd);
				}
			} 
			catch (Exception e) {e.printStackTrace();}
			
			//init NVGN
			query = "select nvgn_fk from THONGBAO_NVGN where thongbao_fk = '" + this.ID + "'";
			rs = db.get(query);
			try 
			{
				String nvgn_fk = "";
				while(rs.next())
				{
					nvgn_fk += rs.getString("nvgn_fk") + ",";
				}
				rs.close();
				
				if(nvgn_fk.trim().length() > 0)
				{
					nvgn_fk = nvgn_fk.substring(0, nvgn_fk.length() - 1);
					this.nvgnId = nvgn_fk;
					System.out.println("___NVGN: " + nvgnId);
				}
			} 
			catch (Exception e) {e.printStackTrace();}
			
			//Van Ban Huong Dan
			query = "select vbhd_fk from THONGBAO_VBHD where thongbao_fk = '" + this.ID + "'";
			rsnv = db.get(query);
			if(rsnv != null)
			{
				 try
				 {
					String vbhdId = ""; 
					while(rsnv.next())
					{
						vbhdId += rsnv.getString("vbhd_fk") + ",";
					}
					rsnv.close();
					
					if(vbhdId.trim().length() > 0)
					{
						vbhdId = vbhdId.substring(0, vbhdId.length() - 1);
						this.vbhdId = vbhdId;
						this.huongdan = "1";
					}
				} 
				catch (Exception e) { System.out.println("11.Exception: " + e.getMessage()); }
			 }
			
			 //Van Ban Can Cu
			 query = "select vbcc_fk from THONGBAO_VBCC where thongbao_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbccId = ""; 
					while(rsnv.next())
					{
						vbccId += rsnv.getString("vbcc_fk") + ",";
					}
					rsnv.close();
					
					if(vbccId.trim().length() > 0)
					{
						vbccId = vbccId.substring(0, vbccId.length() - 1);
						this.vbccId = vbccId;
						this.cancu = "1";
					}
				} 
				catch (Exception e) { System.out.println("22.Exception: " + e.getMessage()); }
			  }
			 
			 //Van Ban Thay The
			 query = "select vbtt_fk from THONGBAO_VBTT where thongbao_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbttId = ""; 
					while(rsnv.next())
					{
						vbttId += rsnv.getString("vbtt_fk") + ",";
					}
					rsnv.close();
					
					if(vbttId.trim().length() > 0)
					{
						vbttId = vbttId.substring(0, vbttId.length() - 1);
						this.vbttId = vbttId;
						this.cancu = "1";
					}
				} 
				catch (Exception e) { System.out.println("33.Exception: " + e.getMessage()); }
			  }
			 
			 //Van Ban Sua Doi Bo Sung
			 query = "select vbsdbs_fk from THONGBAO_VBSDBS where thongbao_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbsdbsId = ""; 
					while(rsnv.next())
					{
						vbsdbsId += rsnv.getString("vbsdbs_fk") + ",";
					}
					rsnv.close();
					
					if(vbsdbsId.trim().length() > 0)
					{
						vbsdbsId = vbsdbsId.substring(0, vbsdbsId.length() - 1);
						this.vbsdbsId = vbsdbsId;
						this.suadoi = "1";
					}
				} 
				catch (Exception e) { System.out.println("33.Exception: " + e.getMessage()); }
			  }
			
		 }
		
		 this.createRs();
		 
	}
	
	public void DBClose() 
	{		
		if (this.db != null)
			this.db.shutDown();
	}

	public void setMsg(String Msg) 
	{
		this.MSG=Msg;
	}
	public String getMsg() 
	{
		return this.MSG;
	}

	public ResultSet getThongbaonhanvienList() 
	{
		return this.thongbaonhanvienRs;
	}

	public void setThongbaonhanvienList(ResultSet thongbaonhanvien) 
	{
		this.thongbaonhanvienRs=thongbaonhanvien;
	}

	public void createRs() 
	{
		String query = "";
		
		this.nppRs = db.get("select pk_seq,ten from nhaphanphoi where trangthai =1");
		
		if(this.ID == null)
			this.ID = "";
		
		this.vungRs=db.get("select * from vung where trangthai = '1' ");
	    query = "select * from khuvuc where trangthai = '1'  ";
		 if(this.VUNG.trim().length() > 0)
			query += "and vung_fk='"+ this.VUNG +"'";
		this.khuvucRs = db.get(query);
		
		query="select PK_SEQ,TEN,VUNG_FK from TinhThanh where 1=1 ";
		if(this.VUNG.length()>0)
		{
			query+=" and VUNG_FK='"+this.VUNG+"' ";
		}
		this.ttRs=this.db.get(query);
		
		
		query = "select distinct nhanvien.pk_seq, nhanvien.dangnhap, nhanvien.ten, nhanvien.dienthoai, 0 as trangthai " +
				"from nhanvien inner join  phamvihoatdong a on a.nhanvien_fk = nhanvien.pk_seq " +
		  			"inner join nhaphanphoi npp on npp.pk_Seq = a.npp_fk " +
		  			"inner join khuvuc kv on kv.pk_seq = npp.khuvuc_fk  "+
		  		" where  phanloai = 2 and kv.trangthai = '1' and nhanvien.pk_seq not in ( select nhanvien_fk from THONGBAO_NHANVIEN where thongbao_fk = '" + ( ( this.ID == null || this.ID.trim().length() <= 0 ) ? "0" : this.ID ) + "' )  ";
		if(this.KHUVUC.trim().length() > 0)
			query += " and kv.pk_seq = '" + this.KHUVUC + "'";
		if(this.VUNG.trim().length() > 0 )
			query += " and kv.vung_fk = '" + this.VUNG + "'";
		
		if(this.ttId.length()>0)
		{
			query+= " and npp.tinhthanh_fk='"+this.ttId+"' ";
		}
		if(this.loaiNv.length()>0)
		{
			query+= " and nhanvien.loai='"+this.loaiNv+"' ";
		}
		
		if(this.nhanvienIds.trim().length() > 0)
		{
			query += " union select nv.PK_SEQ, nv.DANGNHAP, nv.TEN, nv.DIENTHOAI, isnull(tb_nv.trangthai, 0) as trangthai " +
					 "from NhanVien nv left join THONGBAO_NHANVIEN tb_nv on nv.pk_seq = tb_nv.nhanvien_fk and tb_nv.thongbao_fk = '" + ( ( this.ID == null || this.ID.trim().length() <= 0 ) ? "0" : this.ID ) + "'  " +
					 "where nv.trangthai = '1' and nv.pk_seq in ( " + this.nhanvienIds + " ) ";
		}
		
		query += " union " +
		  		"select distinct nv.pk_seq, nv.dangnhap, nv.ten, nv.dienthoai, isnull(tb_nv.trangthai, 0) as trangthai " +
		  		"from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode=npp.sitecode " +
		  			"inner join khuvuc kv on kv.pk_seq=npp.khuvuc_fk " +
		  			"left join THONGBAO_NHANVIEN tb_nv on nv.pk_seq = tb_nv.nhanvien_fk and tb_nv.thongbao_fk = '" + ( ( this.ID == null || this.ID.trim().length() <= 0 ) ? "0" : this.ID ) + "' " +
		  		"where phanloai = 1 and kv.trangthai = '1' ";
		if(this.KHUVUC.trim().length() > 0)
			query += " and kv.pk_seq = '" + this.KHUVUC + "'";
		if(this.VUNG.trim().length() > 0 )
			query += " and kv.vung_fk = '" + this.VUNG + "'";
		
		if(this.ttId.length()>0)
		{
			query+= " and npp.tinhthanh_fk='"+this.ttId+"' ";
		}
		if(this.loaiNv.length()>0)
		{
			query+= " and nv.loai='"+this.loaiNv+"' ";
		}
		
		System.out.println("Out Chi nhánh: "+chinhanh);
		System.out.println("Out Đối tác: "+doitac);
		
		if((this.chinhanh.trim().length()==0 && this.doitac.trim().length()==0) || (this.chinhanh.trim().length()==1 && this.doitac.trim().length()==1) )
		{
			
		}
		else
		{
			if(this.chinhanh.equals("1"))
			{
				query+=" and npp.loaiNPP in(0,1,5) ";
			}
			if(this.doitac.equals("1"))
			{
				query+=" and npp.loaiNPP in(4,5)";
			}
		}
		
		System.out.println("List Nhân viên đăng nhập: "+query);
		

		this.thongbaonhanvienRs = db.get(query);
		
		//query = "select pk_seq, noidung from THONGBAO where loaithongbao = '1' order by pk_seq desc ";
		query = "select pk_seq, tieude as noidung from THONGBAO where hethieuluc = '0' and pk_seq != '" + ( this.ID.trim().length() <= 0 ? "0" : this.ID ) + "'  ";
		if(this.ID.trim().length() > 0)
			query += " union select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbhd_fk from THONGBAO_VBHD where thongbao_fk = '" + this.ID + "' ) ";
		query += "  order by pk_seq desc ";
		System.out.println("1.Lay thong bao HD: " + query);
		this.vbhdRs = db.get(query);
		
		//query = "select pk_seq, noidung from THONGBAO where loaithongbao = '2' order by pk_seq desc ";
		query = "select pk_seq, tieude as noidung from THONGBAO where hethieuluc = '0' and pk_seq != '" + ( this.ID.trim().length() <= 0 ? "0" : this.ID ) + "' ";
		if(this.ID.trim().length() > 0)
			query += " union select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbcc_fk from THONGBAO_VBCC where thongbao_fk = '" + this.ID + "' ) ";
		query += "  order by pk_seq desc ";
		System.out.println("2.Lay thong bao CC: " + query);
		this.vbccRs = db.get(query);
		
		//query = "select pk_seq, noidung from THONGBAO where loaithongbao = '0' order by pk_seq desc ";
		query = "select pk_seq, tieude as noidung from THONGBAO where hethieuluc = '0' and pk_seq != '" + ( this.ID.trim().length() <= 0 ? "0" : this.ID ) + "' ";
		if(this.ID.trim().length() > 0)
			query += " union select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbtt_fk from THONGBAO_VBTT where thongbao_fk = '" + this.ID + "' ) ";
		query += "  order by pk_seq desc ";
		System.out.println("3.Lay thong bao TT: " + query);
		this.vbttRs = db.get(query);
		
		query = "select pk_seq, tieude as noidung from THONGBAO where hethieuluc = '0' and pk_seq != '" + ( this.ID.trim().length() <= 0 ? "0" : this.ID ) + "' ";
		if(this.ID.trim().length() > 0)
			query += " union select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbsdbs_fk from THONGBAO_VBSDBS where thongbao_fk = '" + this.ID + "' ) ";
		query += "  order by pk_seq desc ";
		System.out.println("4.Lay thong bao SD BS: " + query);
		this.vbsdbsRs = db.get(query);
		
		
		query = "select  distinct  a.PK_SEQ, a.MANHANVIEN, a.TEN, a.DIACHI, isnull(a.DIENTHOAI, '') as dienthoai  " +
				"from DAIDIENKINHDOANH a inner join daidienkinhdoanh_npp ddkd on a.pk_seq = ddkd.ddkd_fk  " +
				"  inner join NHAPHANPHOI b on ddkd.NPP_FK = b.PK_SEQ  "+ 
				"where a.TRANGTHAI = '1'";
		
		System.out.println("Out Chi nhánh: "+chinhanh);
		System.out.println("Out Đối tác: "+doitac);
		
		if((this.chinhanh.trim().length()==0 && this.doitac.trim().length()==0) || (this.chinhanh.trim().length()==1 && this.doitac.trim().length()==1) )
		{
			
		}
		else
		{
			if(this.chinhanh.equals("1"))
			{
				query+=" and b.loaiNPP in(0,1,5) ";
			}
			if(this.doitac.equals("1"))
			{
				query+=" and b.loaiNPP in(4,5)";
			}
		}
		
		if(this.KHUVUC.trim().length() > 0)
			query += " and b.khuvuc_fk = '" + this.KHUVUC + "' ";
		if(this.VUNG.trim().length() > 0)
			query += " and b.khuvuc_fk in ( select pk_seq from KhuVuc where vung_fk = '" + this.VUNG + "' ) ";
		if(this.ttId.length()>0)
		{
			query+= " and b.tinhthanh_fk='"+this.ttId+"' ";
		}
		
		if(this.ddkdId.trim().length() > 0)
			query += " union select PK_SEQ, MANHANVIEN, TEN, DIACHI, isnull(DIENTHOAI, '') as DIENTHOAI from DAIDIENKINHDOANH where pk_seq in ( " + this.ddkdId + " ) ";
		
		System.out.println("List NHÂN VIÊN BÁN HÀNG: "+query);
		this.ddkdRs = db.get(query);
		
		query = "select pk_seq, ten,dienthoai from nhanviengiaonhan where trangthai = 1";
		if (this.nppId != null && this.nppId.length() > 0)
		{
			query += " and npp_fk = "+this.nppId;
		}
		this.nvgnRs = db.get(query);
		
	}
	
	public String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public boolean createTb() 
	{
		if( this.TIEUDE.trim().length() <= 0 )
		{
			this.MSG = "Vui lòng nhập tiêu đề";
			return false;
		}
		
		if( this.NOIDUNG.trim().length() <= 0 )
		{
			this.MSG="Vui lòng nhập nội dung";
			return false;
		}
		
		if( this.loaithongbao.length() <= 0 )
		{
			this.MSG="Vui lòng nhập loại thông báo";
			return false;
		}
		
		if( this.nhanvienIds.trim().length() <= 0 && this.ddkdId.trim().length() <=0 && this.nvgnId.length() < 0)
		{
			this.MSG = "Vui lòng chọn nhân viên hoặc NHÂN VIÊN BÁN HÀNG nhận thông báo";
			return false;
		}

		if(this.NGAYKETTHUC.trim().length() > 0)
		{
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			String[] ngaybatdau=this.NGAYBATDAU.split("-");
			c1.set(Integer.parseInt(ngaybatdau[0]),Integer.parseInt(ngaybatdau[1]),Integer.parseInt(ngaybatdau[2]));
			String[] ngayketthuc=this.NGAYKETTHUC.split("-");
			c2.set(Integer.parseInt(ngayketthuc[0]),Integer.parseInt(ngayketthuc[1]),Integer.parseInt(ngayketthuc[2]));
			boolean kt =c2.after(c1);
			boolean kt2 =c1.equals(c2);
			if(kt==false && kt2==false )
			{
				this.MSG = "Ngày bắt đầu (" + ngaybatdau[0]+"-"+ngaybatdau[1]+"-"+ngaybatdau[2] + ") nhỏ hơn ngày kết thúc (" + ngayketthuc[0]+"-"+ngayketthuc[1]+"-"+ngayketthuc[2] + ")";
				return false;
			}
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);	
			String ngay=this.getDateTime();
			 
			if(this.file==null|| this.file.length()==0)
				this.file="0";
			
			String query ="insert into thongbao(tieude,noidung,filename,nguoitao,ngaytao,nguoisua,ngaysua,ngaybatdau,ngayketthuc,trangthai, loaithongbao, hethieuluc)" +
					" values(N'" + this.TIEUDE +"', N'"+this.NOIDUNG+"', N'"+this.file+"', '"+ this.USERID +"', '"+ngay+"', '"+this.USERID+"','" + ngay + "','"+this.NGAYBATDAU+"','"+this.NGAYKETTHUC+"','"+"0"+"', '" + this.loaithongbao + "', '" + this.hethieuluc + "')";
			if(!db.update(query))
			{
				Utility.rollback_and_shutdown(db);
				this.MSG="Không thể tạo mới, vui lòng thử lại";
				
				return false;
			}
			
			/*query = "select IDENT_CURRENT('thongbao') as ddhId";
			System.out.println("cau select: "+query);
			ResultSet rs = this.db.get(query);			
			rs.next();
			String id = rs.getString("ddhId");*/
			
			//Insert thêm cho NVGN:
			if(this.nvgnIds != null && this.nvgnIds.length > 0)
			{
				for (int i = 0; i < this.nvgnIds.length ; i++) {
					query = "insert into THONGBAO_NVGN(thongbao_fk, nvgn_fk, trangthai) " +
							"select IDENT_CURRENT('thongbao'),"+this.nvgnIds[i]+", 2";
					System.out.println("INSERT NVGN: "+query);
					if(!db.update(query))
					{
						db.getConnection().rollback();
						this.MSG = "Không thể cập nhật, vui lòng thử lại";
						return false;
					}
				}
			}
			if(this.nhanvienIds.trim().length() > 0)
			{
				query = "insert into THONGBAO_NHANVIEN(thongbao_fk, nhanvien_fk, trangthai) " +
						"select IDENT_CURRENT('thongbao'), pk_seq, 2 from NhanVien where pk_seq in ( " + this.nhanvienIds + " ) ";
				System.out.println("INSERT NHANVIENDANGNHAP: "+query);
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			if(this.vbhdId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBHD(thongbao_fk, vbhd_fk, trangthai) " +
						"select IDENT_CURRENT('thongbao'), pk_seq, 2 from Thongbao where pk_seq in ( " + this.vbhdId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}

			if(this.vbccId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBCC(thongbao_fk, vbcc_fk, trangthai) " +
						"select IDENT_CURRENT('thongbao'), pk_seq, 2 from ThongBao where pk_seq in ( " + this.vbccId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			
			if(this.vbttId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBTT(thongbao_fk, vbtt_fk, trangthai) " +
						"select IDENT_CURRENT('thongbao'), pk_seq, 2 from ThongBao where pk_seq in ( " + this.vbttId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
				
				query = " Update Thongbao set hethieuluc = '1' where pk_seq in ( " + this.vbttId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			
			if(this.vbsdbsId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBSDBS(thongbao_fk, vbsdbs_fk, trangthai) " +
						"select IDENT_CURRENT('thongbao'), pk_seq, 2 from ThongBao where pk_seq in ( " + this.vbsdbsId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			
			if(this.ddkdId.trim().length() > 0)
			{
				query = "Insert THONGBAO_DDKD(thongbao_fk, ddkd_fk, trangthai) select IDENT_CURRENT('thongbao'), pk_seq, 0 " +
						"from DaiDienKinhDoanh where pk_seq in ( " + this.ddkdId + " ) ";
				if(!db.update(query))
				{
					this.MSG = "Không thể tạo mới, vui lòng thử lại";
					db.getConnection().rollback();
					return false;
				}
			}
			
		   db.getConnection().commit();
		   db.getConnection().setAutoCommit(true);
		}
		catch(Exception er)
		{
			this.MSG=er.toString();
			Utility.rollback_and_shutdown(db);
			return false;
		}
		return true;
	}


	public void initDisplay() 
	{
		String query = "select * from thongbao where PK_SEQ = '" + this.ID + "'";
		 ResultSet rs = db.get(query);
		 if(rs != null)
		 {
			 try
			 {
				while(rs.next())
				 {
					 this.NOIDUNG = rs.getString("noidung");
					 this.TIEUDE = rs.getString("tieude");
					 this.TRANGTHAI= rs.getString("trangthai");
					 this.NGAYBATDAU= rs.getString("ngaybatdau");
					 this.NGAYKETTHUC=rs.getString("ngayketthuc");
					 this.file=rs.getString("filename");
					 this.hethieuluc = rs.getString("hethieuluc");
					 this.loaithongbao = rs.getString("loaithongbao");
				 }
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("Exception init: " + e.getMessage());
			}
			
			query = "select pk_seq, ten,dienthoai from nhanviengiaonhan where trangthai = 1";
			if (this.nppId != null && this.nppId.length() > 0)
			{
				query += " and npp_fk = "+this.nppId;
			}
			this.nvgnRs = db.get(query);
			
			//NV Nhan Thong bao
			query = "select nhanvien_fk from thongbao_nhanvien where thongbao_fk = '" + this.ID + "'";
			ResultSet rsnv = db.get(query);
			if(rsnv != null)
			{
				 try
				 {
					String nvId = ""; 
					while(rsnv.next())
					{
						 nvId += rsnv.getString("nhanvien_fk") + ",";
					}
					rsnv.close();
					
					if(nvId.trim().length() > 0)
					{
						nvId = nvId.substring(0, nvId.length() - 1);
						this.nhanvienIds = nvId;
					}
				} 
				catch (Exception e) {}
			 }
			
			//init DDKD
			query = "select ddkd_fk from THONGBAO_DDKD where thongbao_fk = '" + this.ID + "'";
			System.out.println("__INIT DDKD: " + query);
			rs = db.get(query);
			try 
			{
				String ddkd = "";
				while(rs.next())
				{
					ddkd += rs.getString("ddkd_fk") + ",";
				}
				rs.close();
				
				if(ddkd.trim().length() > 0)
				{
					ddkd = ddkd.substring(0, ddkd.length() - 1);
					this.ddkdId = ddkd;
					//System.out.println("___Dai dien KD: " + ddkd);
				}
			} 
			catch (Exception e) {e.printStackTrace();}
			
			//init NVGN
			query = "select nvgn_fk from THONGBAO_NVGN where thongbao_fk = '" + this.ID + "'";
			System.out.println("__INIT NVGN: " + query);
			rs = db.get(query);
			try 
			{
				String nvgn_fk = "";
				while(rs.next())
				{
					nvgn_fk += rs.getString("nvgn_fk") + ",";
				}
				rs.close();
				
				if(nvgn_fk.trim().length() > 0)
				{
					nvgn_fk = nvgn_fk.substring(0, nvgn_fk.length() - 1);
					this.nvgnId = nvgn_fk;
				}
			} 
			catch (Exception e) {e.printStackTrace();}
			
			
			//Van Ban Huong Dan
			query = "select vbhd_fk from THONGBAO_VBHD where thongbao_fk = '" + this.ID + "'";
			rsnv = db.get(query);
			if(rsnv != null)
			{
				 try
				 {
					String vbhdId = ""; 
					while(rsnv.next())
					{
						vbhdId += rsnv.getString("vbhd_fk") + ",";
					}
					rsnv.close();
					
					if(vbhdId.trim().length() > 0)
					{
						vbhdId = vbhdId.substring(0, vbhdId.length() - 1);
						this.vbhdId = vbhdId;
						this.huongdan = "1";
					}
				} 
				catch (Exception e) { System.out.println("11.Exception: " + e.getMessage()); }
			 }
			
			 //Van Ban Can Cu
			 query = "select vbcc_fk from THONGBAO_VBCC where thongbao_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbccId = ""; 
					while(rsnv.next())
					{
						vbccId += rsnv.getString("vbcc_fk") + ",";
					}
					rsnv.close();
					
					if(vbccId.trim().length() > 0)
					{
						vbccId = vbccId.substring(0, vbccId.length() - 1);
						this.vbccId = vbccId;
						this.cancu = "1";
					}
				} 
				catch (Exception e) { System.out.println("22.Exception: " + e.getMessage()); }
			  }
			 
			 //Van Ban Thay The
			 query = "select vbtt_fk from THONGBAO_VBTT where thongbao_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbttId = ""; 
					while(rsnv.next())
					{
						vbttId += rsnv.getString("vbtt_fk") + ",";
					}
					rsnv.close();
					
					if(vbttId.trim().length() > 0)
					{
						vbttId = vbttId.substring(0, vbttId.length() - 1);
						this.vbttId = vbttId;
						this.thaythe = "1";
					}
				} 
				catch (Exception e) { System.out.println("33.Exception: " + e.getMessage()); }
			  }
			 
			 //Van Ban Sua Doi Bo Sung
			 query = "select vbsdbs_fk from THONGBAO_VBSDBS where thongbao_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbsdbsId = ""; 
					while(rsnv.next())
					{
						vbsdbsId += rsnv.getString("vbsdbs_fk") + ",";
					}
					rsnv.close();
					
					if(vbsdbsId.trim().length() > 0)
					{
						vbsdbsId = vbsdbsId.substring(0, vbsdbsId.length() - 1);
						this.vbsdbsId = vbsdbsId;
						this.suadoi = "1";
					}
				} 
				catch (Exception e) { System.out.println("33.Exception: " + e.getMessage()); }
			  }
			 
			 
			 //Xet xem van ban duoc huong dan, can cu, sua doi, thay the tu dau
			 query = "select b.pk_seq, b.tieude " +
			 		 "from THONGBAO_VBHD a inner join THONGBAO b on a.thongbao_fk = b.pk_seq " +
			 		 "where a.vbhd_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbhdTu = ""; 
					while(rsnv.next())
					{
						vbhdTu += rsnv.getString("pk_seq") + "__" + rsnv.getString("tieude") + "___";
					}
					rsnv.close();
					
					if(vbhdTu.trim().length() > 0)
					{
						vbhdTu = vbhdTu.substring(0, vbhdTu.length() - 3);
						this.duochuongdanTu = vbhdTu;
					}
				 } 
				 catch (Exception e) { System.out.println("12.Exception: " + e.getMessage()); }
			  }
			 
			 query = "select b.pk_seq, b.tieude " +
			 		 "from THONGBAO_VBCC a inner join THONGBAO b on a.thongbao_fk = b.pk_seq " +
			 		 "where a.vbcc_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbccTu = ""; 
					while(rsnv.next())
					{
						vbccTu += rsnv.getString("pk_seq") + "__" + rsnv.getString("tieude") + "___";
					}
					rsnv.close();
					
					if(vbccTu.trim().length() > 0)
					{
						vbccTu = vbccTu.substring(0, vbccTu.length() - 3);
						this.duoccancuTu = vbccTu;
					}
				 } 
				 catch (Exception e) { System.out.println("13.Exception: " + e.getMessage()); }
			  }
			 
			 query = "select b.pk_seq, b.tieude " +
			 		 "from THONGBAO_VBTT a inner join THONGBAO b on a.thongbao_fk = b.pk_seq " +
			 		 "where a.vbtt_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbttTu = ""; 
					while(rsnv.next())
					{
						vbttTu += rsnv.getString("pk_seq") + "__" + rsnv.getString("tieude") + "___";
					}
					rsnv.close();
					
					if(vbttTu.trim().length() > 0)
					{
						vbttTu = vbttTu.substring(0, vbttTu.length() - 3);
						this.duocthaytheTu = vbttTu;
					}
				 } 
				 catch (Exception e) { System.out.println("14.Exception: " + e.getMessage()); }
			  }
			 
			 query = "select b.pk_seq, b.tieude " +
			 		 "from THONGBAO_VBSDBS a inner join THONGBAO b on a.thongbao_fk = b.pk_seq " +
			 		 "where a.vbsdbs_fk = '" + this.ID + "'";
			 rsnv = db.get(query);
			 if(rsnv != null)
			 {
				 try
				 {
					String vbsdbsTu = ""; 
					while(rsnv.next())
					{
						vbsdbsTu += rsnv.getString("pk_seq") + "__" + rsnv.getString("tieude") + "___";
					}
					rsnv.close();
					
					if(vbsdbsTu.trim().length() > 0)
					{
						vbsdbsTu = vbsdbsTu.substring(0, vbsdbsTu.length() - 3);
						this.duocsuadoiTu = vbsdbsTu;
					}
				 } 
				 catch (Exception e) { System.out.println("15.Exception: " + e.getMessage()); }
			  }
			
		 }
		
		 this.createRsDisplay();
	}

	private void createRsDisplay() 
	{
		String query = "";
		
		this.vungRs=db.get("select * from vung where trangthai = '1' ");
	    query = "select * from khuvuc where trangthai = '1'  ";
		 if(this.VUNG.trim().length() > 0)
			query += "and vung_fk='"+ this.VUNG +"'";
		this.khuvucRs = db.get(query);
		
		
		query = " select nv.PK_SEQ, nv.DANGNHAP, nv.TEN, nv.DIENTHOAI, isnull(tb_nv.trangthai, 0) as trangthai " +
				"from NhanVien nv left join THONGBAO_NHANVIEN tb_nv on nv.pk_seq = tb_nv.nhanvien_fk and tb_nv.thongbao_fk = '" + ( this.ID.trim().length() <= 0 ? "0" : this.ID ) + "'  " +
				"where nv.trangthai = '1' and nv.pk_seq in ( " + this.nhanvienIds + " ) ";
		

		this.thongbaonhanvienRs = db.get(query);
		
		
		query = " select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbhd_fk from THONGBAO_VBHD where thongbao_fk = '" + this.ID + "' ) ";
		System.out.println("___Van ban huong dan: " + query);
		this.vbhdRs = db.get(query);
		
		
		query = " select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbcc_fk from THONGBAO_VBCC where thongbao_fk = '" + this.ID + "' ) ";
		System.out.println("___Van ban can cu: " + query);
		this.vbccRs = db.get(query);
		
		
		query = " select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbtt_fk from THONGBAO_VBTT where thongbao_fk = '" + this.ID + "' ) ";
		System.out.println("___Van ban thay the: " + query);
		this.vbttRs = db.get(query);
		

		query = " select pk_seq, tieude as noidung from THONGBAO where pk_seq in ( select vbsdbs_fk from THONGBAO_VBSDBS where thongbao_fk = '" + this.ID + "' ) ";
		System.out.println("___Van ban sua doi: " + query);
		this.vbsdbsRs = db.get(query);
		
		
		if(this.ddkdId.length() > 0)
		{
			query = " select nv.PK_SEQ, nv.MANHANVIEN, nv.TEN, isnull(nv.DIENTHOAI, '') as DIENTHOAI, isnull(tb_nv.trangthai, 0) as trangthai " +
					"from DAIDIENKINHDOANH nv left join THONGBAO_DDKD tb_nv on nv.pk_seq = tb_nv.ddkd_fk and tb_nv.thongbao_fk = '" + ( this.ID.trim().length() <= 0 ? "0" : this.ID ) + "'  " +
					"where nv.trangthai = '1' and nv.pk_seq in ( " + this.ddkdId + " ) ";
			
			this.ddkdRs = db.get(query);
		}

		
	}

	public String getNgaybatdau() 
	{
		return this.NGAYBATDAU;
	}

	public void setNgaybatdau(String ngaybatdau)
	{
		this.NGAYBATDAU=ngaybatdau;
	}

	public String getNgayketthuc() 
	{
		return this.NGAYKETTHUC;
	}

	public void setNgayketthuc(String ngayketthuc) 
	{
		this.NGAYKETTHUC=ngayketthuc;
	}

	public boolean updateTb() 
	{
		String sql = "select trangthai from THONGBAO where pk_seq = '" + this.ID + "' ";
		ResultSet rsTrangthai = db.get(sql);
		try 
		{
			if(rsTrangthai.next())
			{
				if(rsTrangthai.getString("trangthai").equals("1"))  //DA CHOT
				{
					sql = "UPDATE THONGBAO set hethieuluc = '" + this.hethieuluc + "' where pk_seq = '" + this.ID + "' ";
					db.update(sql);
					rsTrangthai.close();
					return true;
				}
			}
			rsTrangthai.close();
		} 
		catch (Exception e) {}
		
		if( this.TIEUDE.trim().length() <= 0 )
		{
			this.MSG = "Vui lòng nhập tiêu đề";
			return false;
		}
		
		if( this.NOIDUNG.trim().length() <= 0 )
		{
			this.MSG="Vui lòng nhập nội dung";
			return false;
		}
		
		if( this.loaithongbao.length() <= 0 )
		{
			this.MSG="Vui lòng nhập loại thông báo";
			return false;
		}
		
		if( this.nhanvienIds.trim().length() <= 0 && this.ddkdId.trim().length() <=0 )
		{
			this.MSG = "Vui lòng chọn nhân viên hoặc NHÂN VIÊN BÁN HÀNG nhận thông báo";
			return false;
		}
		
		/////
		if(this.NGAYKETTHUC.trim().length() > 0)
		{
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			String[] ngaybatdau=this.NGAYBATDAU.split("-");
			c1.set(Integer.parseInt(ngaybatdau[0]),Integer.parseInt(ngaybatdau[1]),Integer.parseInt(ngaybatdau[2]));
			String[] ngayketthuc=this.NGAYKETTHUC.split("-");
			c2.set(Integer.parseInt(ngayketthuc[0]),Integer.parseInt(ngayketthuc[1]),Integer.parseInt(ngayketthuc[2]));
			boolean kt =c2.after(c1);
			boolean kt2 =c1.equals(c2);
			if(kt==false && kt2==false )
			{
				this.MSG = "Ngày bắt đầu (" + ngaybatdau[0]+"-"+ngaybatdau[1]+"-"+ngaybatdau[2] + ") nhỏ hơn ngày kết thúc (" + ngayketthuc[0]+"-"+ngayketthuc[1]+"-"+ngayketthuc[2] + ")";
				return false;
			}
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);	
			String ngay=this.getDateTime();
			
			
			String query = "update thongbao set tieude=N'"+this.TIEUDE+"',NOIDUNG=N'"+this.NOIDUNG+"',NGAYBATDAU='" + this.NGAYBATDAU + "', " +
								"NGAYKETTHUC='"+this.NGAYKETTHUC+"',filename=N'"+this.file+"',NGUOISUA='"+this.USERID+"',NGAYSUA='"+ngay+"', loaithongbao = '" + this.loaithongbao + "', hethieuluc = '" + this.hethieuluc + "' " +
							"where pk_seq='"+ this.ID+"'";
			if(!db.update(query))
			{
				this.MSG="Không thể cập nhật, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete thongbao_nhanvien where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete THONGBAO_NVGN where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			//Insert thêm cho NVGN:
			if(this.nvgnIds != null && this.nvgnIds.length > 0)
			{
				for (int i = 0; i < this.nvgnIds.length ; i++) {
					query = "insert into THONGBAO_NVGN(thongbao_fk, nvgn_fk, trangthai) " +
							"select IDENT_CURRENT('thongbao'),"+this.nvgnIds[i]+", 2";
					System.out.println("INSERT NVGN: "+query);
					if(!db.update(query))
					{
						db.getConnection().rollback();
						this.MSG = "Không thể cập nhật, vui lòng thử lại";
						return false;
					}
				}
			}
			
			
			query = "delete THONGBAO_VBHD where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete THONGBAO_VBCC where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete THONGBAO_VBTT where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete THONGBAO_VBSDBS where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete THONGBAO_DDKD where thongbao_fk = '" + this.ID + "'";
			if(!db.update(query))
			{
				this.MSG="Không thể xóa, vui lòng thử lại";
				db.getConnection().rollback();
				return false;
			}
			
			if(this.nhanvienIds.trim().length() > 0)
			{
				query = "insert into THONGBAO_NHANVIEN(thongbao_fk, nhanvien_fk, trangthai) " +
						"select '" + this.ID + "', pk_seq, 2 from NhanVien where pk_seq in ( " + this.nhanvienIds + " ) ";
				System.out.println("UPDATE THONGBA_NHANVIEN: "+query);
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			if(this.vbhdId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBHD(thongbao_fk, vbhd_fk, trangthai) " +
						"select '" +  this.ID + "', pk_seq, 2 from Thongbao where pk_seq in ( " + this.vbhdId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
		
			if(this.vbccId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBCC(thongbao_fk, vbcc_fk, trangthai) " +
						"select '" + this.ID + "', pk_seq, 2 from ThongBao where pk_seq in ( " + this.vbccId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			
			if(this.vbttId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBTT(thongbao_fk, vbtt_fk, trangthai) " +
						"select '" + this.ID + "', pk_seq, 2 from ThongBao where pk_seq in ( " + this.vbttId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
				
				query = " Update Thongbao set hethieuluc = '1' where pk_seq in ( " + this.vbttId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			
			if(this.vbsdbsId.trim().length() > 0)
			{
				query = "insert into THONGBAO_VBSDBS(thongbao_fk, vbsdbs_fk, trangthai) " +
						"select '" + this.ID + "', pk_seq, 2 from ThongBao where pk_seq in ( " + this.vbsdbsId + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.MSG = "Không thể cập nhật, vui lòng thử lại";
					return false;
				}
			}
			
			System.out.println("DAI DIEN KD: " + this.ddkdId);
			if(this.ddkdId.trim().length() > 0)
			{
				query = "Insert THONGBAO_DDKD(thongbao_fk, ddkd_fk, trangthai) select '" + this.ID + "', pk_seq, 0 " +
						"from DaiDienKinhDoanh where pk_seq in ( " + this.ddkdId + " ) ";
				System.out.println("CHEN DAI DIEN KD: " + query);
				
				if(!db.update(query))
				{
					this.MSG = "Không thể tạo mới, vui lòng thử lại";
					db.getConnection().rollback();
					return false;
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception er)
		{
			Utility.rollback_and_shutdown(db);
			this.MSG=er.toString();
			return false;
		}
		return true;
	}

	public String getKhuvuc()
	{
		return this.KHUVUC;
	}

	public void setKhuvuc(String khvuc) 
	{
		this.KHUVUC=khvuc;
	}

	
	public ResultSet getKhuvucList() {
		
		return this.khuvucRs;
	}

	
	public void setKhuvucList(ResultSet khuvucrs) {
		
		this.khuvucRs=khuvucrs;
	}

	public String getSoluong() {
		return this.SOLUONG;
	}

	public void setSoluong(String soluong) {

		this.SOLUONG=soluong;
	}

	
	public String getVung() {
		
		return this.VUNG;
	}

	
	public void setVung(String vung) {
		
		this.VUNG=vung;
	}

	
	public ResultSet getVungList() {
		
		return this.vungRs;
	}

	
	public void setVungList(ResultSet vungrs) {
		
		this.vungRs=vungrs;
	}

	public String getFile() {

		return this.file;
	}


	public void setFile(String file) {

		this.file=file;
	}
	
	public void setLoaithongbao(String loaitb) {
		
		this.loaithongbao = loaitb;
	}

	
	public String getLoaithongbao() {
		
		return this.loaithongbao;
	}

	public void setHethieuluc(String hethieuluc) {
		
		this.hethieuluc = hethieuluc;
	}

	
	public String getHethieuluc() {
		
		return this.hethieuluc;
	}

	public String getNhanvienIds() {
	
		return this.nhanvienIds;
	}


	public void setNhanvienIds(String nhanvien) {
	
		this.nhanvienIds = nhanvien;
	}

	
	public void setVbhdId(String vbhdId) {
		
		this.vbhdId = vbhdId;
	}

	
	public String getVbhdId() {
		
		return this.vbhdId;
	}

	
	public ResultSet getVbhdRs() {
		
		return this.vbhdRs;
	}

	
	public void setVbhdRs(ResultSet vbhdRs) {
		
		this.vbhdRs = vbhdRs;
	}

	
	public void setVbccId(String vbccId) {
		
		this.vbccId = vbccId;
	}

	
	public String getVbcId() {
		
		return this.vbccId;
	}

	
	public ResultSet getVbccRs() {
		
		return this.vbccRs;
	}

	
	public void setVbccRs(ResultSet vccRs) {
		
		this.vbccRs = vccRs;
	}

	
	public void setVbttId(String vbttId) {
		
		this.vbttId = vbttId;
	}

	
	public String getVbttId() {
		
		return this.vbttId;
	}

	
	public ResultSet getVbttRs() {
		
		return this.vbttRs;
	}

	
	public void setVbttRs(ResultSet vbttRs) {
		
		this.vbttRs = vbttRs;
	}

	public String getViewMode() {

		return this.viewMode;
	}

	public void setViewMode(String viewMode) {
		
		this.viewMode = viewMode;
	}

	
	public String getHienCanCu() {
		
		return this.cancu;
	}

	
	public void setHienCanCu(String cancu) {
		
		this.cancu = cancu;
	}

	
	public String getHienHuongDan() {
		
		return this.huongdan;
	}

	
	public void setHienHuongDan(String huongdan) {
		
		this.huongdan = huongdan;
	}

	
	public String getThayThe() {
		
		return this.thaythe;
	}

	
	public void setThayThe(String thaythe) {
		
		this.thaythe = thaythe;
	}

	
	public void setVbsdbsId(String vbttId) {
		
		this.vbsdbsId = vbttId;
	}

	
	public String getVbsdbsId() {
		
		return this.vbsdbsId;
	}

	
	public ResultSet getVbsdbsRs() {
		
		return this.vbsdbsRs;
	}

	
	public void setVbsdbsRs(ResultSet vbsdbsRs) {
		
		this.vbsdbsRs = vbsdbsRs;
	}

	
	public String getSuaDoiBoSung() {

		return this.suadoi;
	}

	
	public void setSuaDoiBoSung(String suadoiBS) {
		
		this.suadoi = suadoiBS;
	}

	
	public String getDuocCanCuTu() {
		
		return this.duoccancuTu;
	}

	
	public void setDuocCanCuTu(String duoccancuTu) {
		
		this.duoccancuTu = duoccancuTu;
	}

	
	public String getDuocHuongDanTu() {
		
		return this.duochuongdanTu;
	}

	
	public void setDuocHuongDanTu(String duochuongdanTu) {
		
		this.duochuongdanTu = duochuongdanTu;
	}

	
	public String getDuocSuaDoiTu() {
		
		return this.duocsuadoiTu;
	}

	
	public void setDuocSuaDoiTu(String duocsuadoiTu) {
		
		this.duocsuadoiTu = duocsuadoiTu;
	}

	
	public String getDuocThayTheTu() {
		
		return this.duocthaytheTu;
	}

	
	public void setDuocThayTheTu(String duocthaytheTu) {
		
		this.duocthaytheTu = duocthaytheTu;
	}


	public boolean guitraloiTb() 
	{
		if(this.noidungTL.trim().length() <= 0)
		{
			this.MSG = "Vui lòng nhập nội dung trả lời";
			return false;
		}
		
		String query = "insert THONGBAO_NHANVIEN_TRALOI(thongbao_fk, nhanvien_fk, nguoithuchien, phanloai, noidung) " +
					   "values('" + this.ID + "', '" + this.USERID + "', '" + this.USERID + "', '0', N'" + this.noidungTL + "')";
		
		System.out.println("Tạo trả lời: " + query);
		if(!db.update(query))
		{
			this.MSG = "Không thể gửi trả lời. Vui lòng kiểm tra lại";
			return false;
		}
		
		this.MSG = "Bạn đã trả lời thông báo vào lúc " + getTime();
		return true;
	}

	
	public String getNoidungtraloi() {
		
		return this.noidungTL;
	}

	
	public void setNoidungtraloi(String noidungTL) {
		
		this.noidungTL = noidungTL;
	}

	
	public ResultSet getThaoluan() {
		
		return this.thaoluanRs;
	}

	
	public void setThaoluan(ResultSet thaoluan) {
		
		this.thaoluanRs = thaoluan;
	}
	
	public String getTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public void initThaoLuan()
	{
		String query = "\n select a.noidung, a.thoidiem, a.phanloai, case when a.nhanvien_fk = a.nguoithuchien then N'Me' else N'@' + b.TEN end nguoithuchien " +
					"\n from THONGBAO_NHANVIEN_TRALOI a inner join NHANVIEN b on a.nguoithuchien = b.PK_SEQ  " +
					"\n where a.thongbao_fk = '" + this.ID + "' and a.nhanvien_fk = '" + this.USERID + "'  " +
					"\n union all " +
					"\n select a.traloi as noidung, a.thoidiem, 0 as phanloai, N'@' + b.TEN  as nguoithuchien   " +
					"\n from THONGBAO_DDKD_TRALOI a inner join DAIDIENKINHDOANH b on a.ddkd_fk = b.PK_SEQ   " +
					"\n where a.thongbao_fk = '" + this.ID + "'  " +
					"\n select a.traloi as noidung, a.thoidiem, 0 as phanloai, N'@' + b.TEN  as nguoithuchien   " +
					"\n from THONGBAO_NVGN_TRALOI a inner join NHANVIENGIAONHAN b on a.nvgn_fk = b.PK_SEQ   " +
					"\n where a.thongbao_fk = '" + this.ID + "'  " +		
					"\n order by thoidiem desc";
		System.out.println("____Init Thao Luan: " + query);
		this.thaoluanRs = db.get(query);
	}

	
	public void initThaoLuanTT() 
	{	
		String query = "\n select a.noidung, a.thoidiem, a.phanloai, case when a.nhanvien_fk = a.nguoithuchien then N'@' + b.TEN  else N'Me' end nguoithuchien " +
					   "\n from THONGBAO_NHANVIEN_TRALOI a inner join NHANVIEN b on a.nguoithuchien = b.PK_SEQ  " +
					   "\n where a.thongbao_fk = '" + this.ID + "'  ";
		if(this.nguoinhanTlId.trim().length() > 0)
			query += "\n  and a.nhanvien_fk = '" + this.nguoinhanTlId + "' ";
		
		query += "\n union all " +
				    "\n select a.traloi as noidung, a.thoidiem, 0 as phanloai, N'@' + b.TEN  as nguoithuchien  " +
					"\n from THONGBAO_DDKD_TRALOI a inner join DAIDIENKINHDOANH b on a.ddkd_fk = b.PK_SEQ   " +
					"\n where a.thongbao_fk = '" + this.ID + "'  ";
		
		if (this.nvgnId != null && this.nvgnId.length() > 0) {
			query += "\n union all" +
					"\n select a.traloi as noidung, a.thoidiem, 0 as phanloai, N'@' + b.TEN  as nguoithuchien   " +
					"\n from THONGBAO_NVGN_TRALOI a inner join NHANVIENGIAONHAN b on a.nvgn_fk = b.PK_SEQ   " +
					"\n where a.thongbao_fk = '" + this.ID + "'  ";
		}
			
		query +=  "order by thoidiem desc";
		System.out.println("____Init Thao Luan: " + query);
		
		this.thaoluanRs = db.get(query);
		
		query = "select PK_SEQ, TEN from NHANVIEN where PK_SEQ in ( select NHANVIEN_FK from THONGBAO_NHANVIEN_TRALOI where thongbao_fk = '" + this.ID + "' )";
		this.nguoinhanTLRs = db.get(query);
	}

	
	public ResultSet getNguoinhanTLRs() {
		
		return this.nguoinhanTLRs;
	}

	
	public void setNguoinhanTLRs(ResultSet nguoinhanTLrs) {
		
		this.nguoinhanTLRs = nguoinhanTLrs;
	}

	
	public String getNguoinhanTLId() {
		
		return this.nguoinhanTlId;
	}

	
	public void setNguoinhanTLId(String nguoinhanId) {
		
		this.nguoinhanTlId = nguoinhanId;
	}

	public boolean guitraloiTbTT() 
	{
		if(this.noidungTL.trim().length() <= 0)
		{
			this.MSG = "Vui lòng nhập nội dung trả lời";
			return false;
		}
		
		if(this.nguoinhanTlId.trim().length() <= 0)
		{
			this.MSG = "Vui lòng chọn người nhận trả lời";
			return false;
		}
		
		String query = "insert THONGBAO_NHANVIEN_TRALOI(thongbao_fk, nhanvien_fk, nguoithuchien, phanloai, noidung) " +
					   "values('" + this.ID + "', '" + this.nguoinhanTlId + "', '" + this.USERID + "', '1', N'" + this.noidungTL + "')";
		
		System.out.println("Tạo trả lời: " + query);
		if(!db.update(query))
		{
			this.MSG = "Không thể gửi trả lời. Vui lòng kiểm tra lại";
			return false;
		}
		
		this.MSG = "Bạn đã trả lời thông báo vào lúc " + getTime();
		return true;
	}


	public ResultSet getDdkdRs() {
		
		return this.ddkdRs;
	}

	
	public void setDdkdRs(ResultSet ddkdRs) {
		
		this.ddkdRs = ddkdRs;
	}

	
	public String getDdkdId() {
		
		return this.ddkdId;
	}

	
	public void setDdkdId(String ddkdId) {
		
		this.ddkdId = ddkdId;
	}

	public void setChiNhanh(String chinhanh) {
		this.chinhanh=chinhanh;
	}

	public String getChiNhanh() {
		return this.chinhanh;
	}

	public void setDoiTac(String doitac) {
		this.doitac=doitac;
	}

	public String getDoiTac() {
		return this.doitac;
	}

	
	String ttId,loaiNv;
	public String getLoaiNv()
  {
  	return loaiNv;
  }

	public void setLoaiNv(String loaiNv)
  {
  	this.loaiNv = loaiNv;
  }


	ResultSet ttRs;

	public String getTtId()
  {
  	return ttId;
  }

	public void setTtId(String ttId)
  {
  	this.ttId = ttId;
  }

	public ResultSet getTtRs()
  {
  	return ttRs;
  }

	public void setTtRs(ResultSet ttRs)
  {
  	this.ttRs = ttRs;
  }
	
}
