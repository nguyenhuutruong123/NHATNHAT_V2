package geso.dms.center.beans.thongtinsanpham.imp;

import geso.dms.center.beans.thongtinsanpham.IThongtinsanpham;
import geso.dms.center.db.sql.*;
import geso.dms.distributor.util.Utility;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.io.Serializable;
import java.util.Hashtable;

public class Thongtinsanpham implements IThongtinsanpham, Serializable
{
	private static final long serialVersionUID = -9217977546733690415L;
	String userId;
	String id;
	String masp;
	String ten;
	String isKtl;

	String dvdlId;
	String dvdlTen;
	String dvdlETCId;
	ResultSet dvdl;
	
	String dvkdId;
	String dvkdTen;
	ResultSet dvkd;
	
	String nhId;
	String nhTen;
	ResultSet nh;
	
	String clId;
	String clTen;
	ResultSet cl;
	
	String trangthai;
	String spmoi;
	String spchuluc;
	String giablc;
		
	String msg;

	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String QcDongGoi = "";


	String Quydoithuong;
	String hansudung;
	
	String[] nspIds;
	ResultSet nsp;
	ResultSet nspSelected;
	ResultSet RsNganhHang;
	String nganhhangid;
	String[] sl1;
	String[] dvdl1;
	String[] sl2;
	String[] dvdl2;
	
	String type;
	String[] spIds;
	String[] spStt;
	ResultSet spList;
	ResultSet spSelectedList; //update
	ResultSet rsPacksize;
	dbutils db;
	private String kl="";
	private String dvkl="";
	private String dvtt="";
	private String tt="";
	String machuan="";
	String PacksizeId;
	String tenchuan="";
	String nhanhangIds,chungloaiIds;

	String tenviettat;
	String thanhphan;
	String dangbaoche;
	String hamluong;
	
	String nccId = ""; // kho ERP ko liên quan DMS
	ResultSet nccRs;
	
	double pt_vat = 0;
	double pt_vat_2 = 0;
	String isKm = "0";
	//String isKtl = "0";
	String hinhanh;
	public String getHinhanh() {
		return hinhanh;
	}
	
	
	double tontoithieu = 0;
	
	
	public double getTontoithieu() {
		return tontoithieu;
	}
	public void setTontoithieu(String tontoithieu) {
		this.tontoithieu = geso.dms.center.util.Utility.parseDouble(tontoithieu);
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public Thongtinsanpham(String[] param)
	{	
		this.db = new dbutils();
		this.id = param[0];
		this.masp = param[1];
		this.ten = param[2];
		this.dvdlTen = param[3];
		this.dvkdTen = param[4];
		this.dvkdId = param[5];
		this.nhTen = param[6];
		this.nhId = param[7];
		this.clId = param[8];
		this.clTen = param[9];	
		this.giablc = param[10];
		this.trangthai=param[11];
		this.dvdlId = param[12];
    	this.ngaysua = param[13];
    	this.nguoisua = param[14];
    	this.sl1 = new String[5];
    	this.sl2 = new String[5];
    	this.dvdl1 = new String[5];
    	this.dvdl2 = new String[5];
    	this.type = param[15]; 
    	this.msg = "";
    	this.PacksizeId="";
    	this.machuan="";
    	this.Quydoithuong="1";
    	this.tenchuan="";
    	this.hansudung="530";
    	this.chungloaiIds="";
    	this.nhanhangIds="";
    	this.dvdlETCId = "";
    	this.nhomHangId="";
    	this.spchuluc="";
    	this.spmoi="";
    	this.hinhanh="";
    	this.pt_vat = 0;
    	
    	this.tenviettat="";
    	this.thanhphan="";
    	this.dangbaoche="";
    	this.hamluong="";
	}
	
	public Thongtinsanpham()
	{	
		this.db = new dbutils();
		this.id = "";
		this.masp = "";
		this.ten = "";
		this.dvdlTen = "";
		this.dvkdTen = "";
		this.dvkdId = "";
		this.nhTen = "";
		this.nhId = "";
		this.clId = "";
		this.clTen = "";	
		this.giablc = "";
		this.trangthai= "1";
		this.dvdlId = "";
    	this.ngaysua = "";
    	this.nguoisua = "";
    	this.msg = "";
    	this.type = "";
    	this.nganhhangid="";
    	this.machuan="";
    	this.tenchuan="";
    	
    	this.PacksizeId="";
    	this.sl1 = new String[5];
    	this.sl2 = new String[5];
    	this.dvdl1 = new String[5];
    	this.dvdl2 = new String[5]; 	
    	this.Quydoithuong ="1";
    	this.hansudung="530";
    	this.chungloaiIds="";
    	this.nhanhangIds="";
    	this.dvdlETCId = "";
    	this.nhomHangId="";
    	this.spchuluc="";
    	this.spmoi="";
    	this.nccId = "";
    	this.hinhanh="";

    	this.tenviettat="";
    	this.thanhphan="";
    	this.dangbaoche="";
    	this.hamluong="";
    	this.pt_vat = 0;
	}
	
	public dbutils getDb() {
		return db;
	}
	
	public String getIsKtl() {
		return isKtl;
	}
	
	public void setIsKtl(String isKtl) {
		this.isKtl = isKtl;
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

	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getMasp()
	{
		return this.masp;
	}

	public void setMasp(String masp)
	{
		this.masp = masp;
	}

	public String getTen()
	{
		return this.ten;
	}

	public void setTen(String ten)
	{
		this.ten = ten;
	}

	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}
	
	public String getDvdlId()
	{
		return this.dvdlId;
	}

	public void setDvdlId(String dvdlId)
	{
		this.dvdlId = dvdlId;
	}

	public String getDvdlTen()
	{
		return this.dvdlTen;
	}

	public void setDvdlTen(String dvdlTen)
	{
		this.dvdlTen = dvdlTen;
	}

	public ResultSet getDvdl()
	{
		return this.dvdl;
	}

	public void setDvdl(ResultSet dvdl)
	{
		this.dvdl = dvdl;
	}

	public String getDvkdId()
	{
		return this.dvkdId;
	}

	public void setDvkdId(String dvkdId)
	{
		this.dvkdId = dvkdId;
	}

	public String getDvkdTen()
	{
		return this.dvkdTen;
	}

	public void setDvkdTen(String dvkdTen)
	{
		this.dvkdTen = dvkdTen;
	}
	
	public String getNhId()
	{
		return this.nhId;
	}

	public void setNhId(String nhId)
	{
		this.nhId = nhId;
	}

	public String getNhTen()
	{
		return this.nhTen;
	}

	public void setNhTen(String nhTen)
	{
		this.nhTen = nhTen;
	}
	
	public String getClId()
	{
		return this.clId;
	}

	public void setClId(String clId)
	{
		this.clId = clId;
	}

	public String getClTen()
	{
		return this.clTen;
	}

	public void setClTen(String clTen)
	{
		this.clTen = clTen;
	}

	public String getNgaytao()
	{
		return this.ngaytao;
		
	}

	public void setNgaytao(String ngaytao)
	{
		this.ngaytao = ngaytao;
	}
	
	public String getNgaysua()
	{
		return this.ngaysua;
		
	}

	public void setNgaysua(String ngaysua)
	{
		this.ngaysua = ngaysua;
	}
	
	public String getNguoitao()
	{
		return this.nguoitao;
	}
	
	public void setNguoitao(String nguoitao)
	{
		this.nguoitao = nguoitao;
	}

	public String getNguoisua()
	{
		return this.nguoisua;
	}

	public void setNguoisua(String nguoisua)
	{
		this.nguoisua = nguoisua;
	}
	
	public String getMessage()
	{
		return this.msg;
	}

	public void setMessage(String msg)
	{
		this.msg = msg;
	}
	
	public void setDvkd(ResultSet dvkd)
	{
		this.dvkd = dvkd;
	}

	public ResultSet getDvkd()
	{
		return this.dvkd;
	}

	public void setNh(ResultSet nh)
	{
		this.nh = nh;
	}

	public ResultSet getNh()
	{
		return this.nh;
	}

	public void setCl(ResultSet cl)
	{
		this.cl = cl;
	}
	
	public ResultSet getCl()
	{
		return this.cl;
	}

	public void setGiablc(String giablc)
	{
		this.giablc = giablc;
	}
	
	public String getGiablc()
	{
		return this.giablc;
	}

	public void setNsp(ResultSet nsp)
	{
		this.nsp = nsp;
	}

	public ResultSet getNsp()
	{
		return this.nsp;
	}

	public Hashtable<Integer, String> getNspIds()
	{
		Hashtable<Integer, String> selected = new Hashtable<Integer, String>();
		if (this.nspIds != null){
			int size = (this.nspIds).length;
			int m = 0;
			while(m < size){
				selected.put(new Integer(m), this.nspIds[m]) ;
				m++;
			}
		}
		else{
			selected.put(new Integer(0), "null");
		}
		return selected;
	}

	public void setNspIds(String[] nspIds)
	{
		this.nspIds = nspIds;
	}

	public String[] getSl1()
	{
		return this.sl1 ;
	}

	public void setSl1(String[] sl1)
	{
		this.sl1 = sl1;
	}
	
	public String[] getDvdl1()
	{
		return this.dvdl1;
	}

	public void setDvdl1(String[] dvdl1)
	{
		this.dvdl1 = dvdl1;
	}

	public String[] getSl2()
	{
		return this.sl2 ;
	}

	public void setSl2(String[] sl2)
	{
		this.sl2 = sl2;
	}

	public String[] getDvdl2()
	{
		return this.dvdl2 ;
	}

	public void setDvdl2(String[] dvdl2)
	{
		this.dvdl2 = dvdl2;
	}
	
	public ResultSet createDvdlRS()
	{  			
		ResultSet dvdlRS = this.db.getScrol("select pk_seq as dvdlId, donvi as dvdlTen from donvidoluong where trangthai='1' order by donvi");
		return dvdlRS;
	}
	
	private ResultSet createDvkdRS(){  	
				ResultSet dvkdRS =  this.db.get("select distinct(a.pk_seq) as dvkdId, a.donvikinhdoanh as dvkdTen from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk and b.checked ='1' and a.trangthai ='1' order by a.donvikinhdoanh");
		return dvkdRS;
	}
	
	private ResultSet createNhRS(){  	
		ResultSet nhRS;
		if (dvkdId.length()>0){
			String query="select pk_seq as pk_seq, ten as diengiai from nhanhang where trangthai='1' and dvkd_fk='" + this.dvkdId + "'";
			
					System.out.println("day nek : "+query);
			nhRS =  this.db.getScrol(query);
		}else{
			String query="select pk_seq , ten as diengiai from nhanhang where trangthai='1'";
			
			System.out.println("day nek 1 : "+query);
			
			nhRS =  this.db.getScrol(query);
		}
		
		return nhRS;
		
	}

	private ResultSet createClRS() {  	
		
		String query = " select  a.pk_seq, a.ten as diengiai from chungloai a \r\n" + 
				" inner join nhanhang_chungloai b on a.pk_seq = b.cl_fk \r\n" + 
				" left join nhanhang c on c.pk_seq = b.NH_FK where 1 = 1";
		
		if (this.nhId.length()>0){
			query = query + "  and c.pk_seq = '" + this.nhId + "'";
		
		}
		System.out.println("chung loai npp "+query);
		return this.db.getScrol(query);		
	}
	
	private void createNspRS()
	{
		
		if (this.id.length() > 0)
		{
			this.nspSelected = this.db.get("select a.nsp_fk as nspId, b.TEN as nspTen, b.diengiai as diengiai from nhomsanpham_sanpham a inner join nhomsanpham b on a.nsp_fk = b.pk_seq where a.sp_fk = '" + this.id + "'");
			String query = "select pk_seq as nspId, ten as nspTen, diengiai as diengiai from nhomsanpham where trangthai='1' and type = '0' order by ten";
			this.nsp = this.db.get(query);
			System.out.println("khoi tao nsp :"+query);
		}
		else
		{
			this.nsp = this.db.get("select pk_seq as nspId, ten as nspTen, diengiai as diengiai from nhomsanpham where trangthai='1' and type = '0' order by ten");
		}
	}
	
	private void createSpIds() 
	{
		ResultSet rs = db.get("select sanpham_fk from Bundle_Sanpham where bundle_fk = '" + this.id + "'");
		if (rs != null)
		{
			try 
			{
				String str = "";
				while(rs.next())
				{
					str = str + rs.getString("sanpham_fk") + ",";
				}
				rs.close();
				
				if (str.length() > 0)
				{
					str = str.substring(0, str.length() - 1);
					this.spIds = str.split(",");
				}
			} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}

	private void createNspIds() 
	{
		ResultSet rs = db.get("select nsp_fk from nhomsanpham_sanpham where sp_fk = '" + this.id + "'");
		if (rs != null)
		{
			try 
			{
				String str = "";
				while (rs.next())
				{
					str = str + rs.getString("nsp_fk") + ",";
				}
				if (str.length() > 0)
				{
					str = str.substring(0, str.length() - 1);
					this.nspIds = str.split(",");
				}
			} 
			catch (SQLException e) {e.printStackTrace();}
		}
	}

	public void CreateRS()
	{
		this.dvdl = createDvdlRS();		
		this.dvkd = createDvkdRS();
		this.nh = createNhRS();
		this.cl = createClRS();	
		this.createNspRS();		
		this.createSpList();				
		
		String query = "select pk_seq, ten from packsize ";
		this.rsPacksize = db.get(query);
		
		query = "select pk_seq,ten,diengiai from nganhhang ";
		this.RsNganhHang = db.get(query);
		
		query = "select pk_seq,ma,ten from nhomhang order by ten ";
		this.nhomHangRs = this.db.get(query);
		
		query = " select pk_seq,ten ,ten diengiai from NHACUNGCAP ";
		this.nccRs = db.get(query);
	}
	
	boolean masanpham()
	{  
		String sql;
		if (this.id.length() == 0){
			sql = "select count(*) as num from sanpham where ma =dbo.Trim( N'" + this.masp + "')";
		}else{
			sql = "select count(*) as num from sanpham where pk_seq <> '"+ this.id +"' and ma =dbo.Trim(N'" + this.masp + "')";
		}
		System.out.println("thong tin sp:"+sql);
		ResultSet rs = db.get(sql);
		if (rs != null) {
			try 
			{
				rs.next();
				if (rs.getString("num").equals("0"))
					return false;
			} 
			catch (SQLException e){
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	public void xoaanhSp()
	{
		try {
			this.db.getConnection().setAutoCommit(false);
			String quString="update sanpham set hinhanh='' where pk_seq="+this.id;
			if(!db.update(quString))
			{
				this.msg = "Lỗi xóa ảnh";
				this.db.getConnection().rollback();
				return;
			}
			
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(false);
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean UpdateSp()
	{
		try{
			
			if (masanpham())
			{
				this.msg ="Mã sản phẩm đã trùng .";
				return false;
			}

			this.db.getConnection().setAutoCommit(false);
			this.ngaysua = getDateTime();
			this.nguoisua = this.userId;

			String cloai = Utility.isValid(this.clId) ? this.clId : "null";
			String nhanhang = Utility.isValid(this.nhId) ? this.nhId : "null";
			if (!Utility.isValid(PacksizeId)){
				this.PacksizeId = null;
			}
			
			if (!Utility.isValid(nccId)) {				
				this.msg = "Vui lòng chọn nhà cung cấp!";
				this.db.getConnection().rollback();
				return false;
			}

			String dvdl_etc_fk = !Utility.isValid(this.dvdlETCId) ? "NULL" : this.dvdlETCId;		
			String nccId_fk = !Utility.isValid(this.nccId) ? "NULL" : this.nccId;

			if (this.nhId.equals(""))
				this.nhId = null;
			//ma = N'" + this.masp + "', --> ko cho sửa MA
			String query = "\n update sanpham set tontoithieu = "+this.tontoithieu+",hinhanh=N'"+this.hinhanh+"', isKm= "+this.isKm+", PT_VAT = "+this.pt_vat+", pt_vat_2 = "+this.pt_vat_2+", ncc_fk = '"+this.nccId+"', " +
			"\n DVDL_ETC_FK = " + dvdl_etc_fk + ", HanSuDung = '"+this.hansudung+"', tenchuan = N'"+this.tenchuan+"', quydoithungthuong = "+this.Quydoithuong+", " +
			"\n NGANHHANG_FK = "+(this.nganhhangid.equals("")? null:this.nganhhangid)+", machuan='"+this.machuan+"', packsize_fk = "+this.PacksizeId+", " +
			"\n  ten = N'" + this.ten + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.userId + "', " +
			"\n dvdl_fk = '" + this.dvdlId + "', trangthai = '" + this.trangthai + "', dvkd_fk = '" + this.dvkdId + "', " +
			"\n nhanhang_fk = "+this.nhId+", chungloai_fk = " + cloai + ", type = '" + this.type + "', trongluong = '" + this.kl + "', " +
			"\n thetich = '" + this.tt + "', loaisanpham_fk = null, spchuluc = '"+this.spchuluc+"', spmoi = '"+this.spmoi+"', " +
			"\n tenviettat = N'"+this.tenviettat+"', thanhphan = N'"+this.thanhphan+"', dangbaoche = N'"+this.dangbaoche+"', " +
			"\n hamluong = N'"+this.hamluong+"', QcDongGoi = N'"+this.QcDongGoi+"' " +
			"\n where pk_seq = '" + this.id + "'";
			System.out.println("UPDATE SANPHAM: "+query);
			if (!this.db.update(query))
			{		
				this.msg = "Exception 1.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}

			query = "delete from nhomsanpham_sanpham where sp_fk ='" + this.id + "'";
			if (!this.db.update(query))
			{   
				this.msg = "Exception 2.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}		

			if (!(this.nspIds == null)){
				int size = nspIds.length;
				for (int i = 0; i < size ; i++){
					if (this.nspIds[i] != null){
						query = "insert into nhomsanpham_sanpham(sp_fk, nsp_fk) values('" + this.id + "', '" + nspIds[i] + "')";
						if (!this.db.update(query)){
							this.msg = "Exception 3.";
							this.db.getConnection().rollback();
							this.db.getConnection().setAutoCommit(true);
							return false;
						}
					}
				}
			}

			if (this.type.equals("1"))
			{
				db.update("delete from Bundle_Sanpham where bundle_fk='" + this.id + "'");
				if (!this.db.update(query))
				{
					this.msg = "Exception 4.";
					this.db.getConnection().rollback();
					this.db.getConnection().setAutoCommit(true);
					return false;
				}

				if (this.spIds != null)
				{
					for(int i = 0; i < this.spIds.length; i++)
					{
						if (this.spIds[i] != null)
						{
							String[] arr = this.spIds[i].split("-");
							query = "insert into Bundle_Sanpham(bundle_fk, sanpham_fk, soluong) values('" + this.id + "','" + arr[0] + "', '" + arr[1] + "')";
							System.out.println("Cau lenh insert bundle: " + query + "\n");
							if (!this.db.update(query))
							{	
								this.msg = "Exception 5.";
								this.db.getConnection().rollback();
								this.db.getConnection().setAutoCommit(true);
								return false;
							}
						}
					}
				}
			}

			for (int i = 0; i < 5 ; i++)
			{
				sl1[i]=sl1[i].trim();
				sl2[i]=sl2[i].trim();
				if ( sl1[i].length() > 0 && Double.parseDouble(sl1[i])==0)
				{
					this.msg = " Không được nhập quy cách bằng 0";			
					this.db.getConnection().rollback();
					return false;
				}
				if (sl2[i].length() > 0 && Double.parseDouble(sl2[i])==0)
				{
					this.msg = " Không được nhập quy cách bằng 0";			
					this.db.getConnection().rollback();
					return false;
				}
				if ((sl1[i].length() > 0) && (dvdl1[i].length() > 0) && (sl2[i].length() > 0) && (dvdl2[i].length() > 0))
				{ 
					query = "\n select count(*) as sodong from quycach " +
					"\n where sanpham_fk = '"+this.id+"' and dvdl2_fk = '"+ dvdl2[i] +"' " +
					"\n and dvdl1_fk = '"+dvdl1[i]+"' and ( soluong1!='"+ sl1[i] +"'  or soluong2!='"+sl2[i]+"' ) " ;
					System.out.println("[PhatSinhDuLieu]"+query);
					ResultSet rs = db.get(query);
					int	sodong = 0;
					while(rs.next())
					{
						sodong = rs.getInt("sodong");
					}
					System.out.println("So dong: "+sodong);
					
					if (rs != null) rs.close();

					if (sodong > 0)
					{
						query= "\n select c.DONVI as spDonVi, d.TEN as spTEN, COUNT(*) as SoDong, N'Đơn đặt hàng' as NghiepVu " +
						"\n from ERP_DONDATHANGNPP a inner join ERP_DONDATHANGNPP_SANPHAM b on b.dondathang_fk = a.PK_SEQ " +
						"\n inner join DONVIDOLUONG c on c.PK_SEQ = b.dvdl_fk " +
						"\n inner join SANPHAM d on d.PK_SEQ = b.sanpham_fk " +
						"\n where  b.soluong != 0 and b.dvdl_fk = '"+dvdl2[i]+"' and b.sanpham_fk = '"+this.id+"' " +
						"\n group by c.DONVI, d.TEN " +
						"\n union all " +
						"\n select c.DONVI as spDonVi, d.TEN as spTEN, COUNT(*) as SoDong, N'Đơn đặt hàng' as NghiepVu " +
						"\n from ERP_DONDATHANG a inner join ERP_DONDATHANG_SANPHAM b on b.dondathang_fk = a.PK_SEQ " +
						"\n inner join DONVIDOLUONG c on c.PK_SEQ = b.dvdl_fk " +
						"\n inner join SANPHAM d on d.PK_SEQ = b.sanpham_fk " +
						"\n where  b.soluong != 0 and b.dvdl_fk = '"+dvdl2[i]+"' and b.sanpham_fk = '"+this.id+"' " +
						"\n group by c.DONVI, d.TEN " +
						"\n union all " +
						"\n select c.DONVI as spDonVi, d.TEN as spTEN, COUNT(*) as SoDong, N'Xuất chuyển nội bộ' as NghiepVu " +
						"\n from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b on b.chuyenkho_fk = a.PK_SEQ  "+
						"\n inner join DONVIDOLUONG c on c.PK_SEQ = b.dvdl_fk " +
						"\n inner join SANPHAM d on d.PK_SEQ = b.sanpham_fk " + 
						"\n where b.soluongchuyen != 0 and b.dvdl_fk = '"+dvdl2[i]+"' and b.sanpham_fk = '"+this.id+"' " +
						"\n group by c.DONVI, d.TEN " +
						"\n union all " +
						"\n select c.DONVI as spDonVi, d.TEN as spTEN, COUNT(*) as SoDong, N'Xuất chuyển nội bộ' as NghiepVu " +
						"\n from ERP_CHUYENKHONPP a inner join ERP_CHUYENKHONPP_SANPHAM b on b.chuyenkho_fk = a.PK_SEQ " +
						"\n inner join DONVIDOLUONG c on c.PK_SEQ = b.dvdl_fk " +
						"\n inner join SANPHAM d on d.PK_SEQ = b.sanpham_fk " +
						"\n where b.soluongchuyen != 0 and b.dvdl_fk = '"+dvdl2[i]+"' and b.sanpham_fk = '"+this.id+"' " +
						"\n group by c.DONVI, d.TEN " +
						"\n union all  "+
						"\n select c.DONVI as spDonVi, d.TEN as spTEN, COUNT(*) as SoDong, N'Chuyển Kênh' as NghiepVu " +
						"\n from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM b on b.chuyenkenh_fk = a.PK_SEQ " +
						"\n inner join DONVIDOLUONG c on c.PK_SEQ = b.dvdl_fk " +
						"\n inner join SANPHAM d on d.PK_SEQ = b.sanpham_fk " +
						"\n where b.soluongchuyen !=0 and b.dvdl_fk = '"+dvdl2[i]+"' and b.sanpham_fk = '"+this.id+"' " +  
						"\n group by c.DONVI, d.TEN  ";
						System.out.println("Kiem tra nghiep vu phat sinh: "+query);
						rs = db.get(query);
						while(rs.next())
						{
							msg += "("+ rs.getString("NghiepVu")+")\n";
						}
						
						if (rs != null) rs.close();

						if (msg.length() > 0)
						{
							this.msg = "Sản phẩm đã phát sinh dữ liệu nên không thể thay đổi quy cách.";
							this.db.getConnection().rollback();
							return false;
						}
					}
					
					query = "\n delete quycach where sanpham_fk = '"+this.id+"' and dvdl2_fk = '"+ dvdl2[i] +"' and dvdl1_fk = '"+dvdl1[i]+"' ";
					if (!this.db.update(query))
					{	
						this.msg = "Exception 6.";
						this.db.getConnection().rollback();
						this.db.getConnection().setAutoCommit(true);
						return false;
					}
					
					if (Utility.isValid(sl1[i]) && !sl1[i].equals("0") && Utility.isValid(sl2[i]) && !sl2[i].equals("0")) {
						query = "\n insert into quycach(sanpham_fk, dvdl1_fk, soluong1, dvdl2_fk, soluong2,stt) " +
						"\n values('"+ this.id +"','"+dvdl1[i]+"','"+ sl1[i] +"','"+ dvdl2[i] +"','"+sl2[i] +"','"+i+"')";
						System.out.println("--CHEN QUY CACH: " + query);
						if (!(this.db.update(query)))
						{
							this.msg = "Exception 7.";
							this.db.getConnection().rollback();
							this.db.getConnection().setAutoCommit(true);
							return false;
						}
					}
				}
			}			

		/*	query = "\n INSERT INTO LOG_SANPHAM(SANPHAM_FK,NGUOISUA,GIA,SOLUONG1,SOLUONG2,DVDL) "+
			"\n SELECT SP.PK_SEQ AS SPID,SP.NGUOISUA,BG.GIABANLECHUAN,QC.SOLUONG1,QC.SOLUONG2,DVDL.PK_SEQ AS DVDL_FK "+
			"\n FROM SANPHAM SP INNER JOIN QUYCACH QC ON QC.SANPHAM_FK = SP.PK_SEQ "+
			"\n INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ = QC.DVDL1_FK  "+
			"\n WHERE SP.PK_SEQ="+this.id+"  ";
			System.out.print("[LOG_SANPHAM]: "+query);
			db.update(query); */

			query = "\n insert into nhapp_kho(npp_fk,kbh_fk,KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE) " +
			"\n select npp.PK_SEQ, kenh.PK_SEQ as kbhId, kho.PK_SEQ as khoId, sp.PK_SEQ as spId, " +
			"\n 0 as SoLuong, 0 as Booked, 0 as avail " +
			"\n from KHO kho, SANPHAM sp, KENHBANHANG kenh, NHAPHANPHOI npp " +  
			"\n where not exists " +  
			"\n ( " +
			"\n     select * from  NHAPP_KHO a " +
			"\n     where a.KHO_FK=kho.PK_SEQ and a.KBH_FK = kenh.PK_SEQ " +
			"\n     and a.SANPHAM_FK=sp.PK_SEQ and a.npp_fk = npp.pk_Seq " +
			"\n ) and sp.DVKD_FK in ("+this.dvkdId+") "+ 
			"\n and sp.pk_Seq="+this.id+" ";
			if (!db.update(query))
			{
				this.msg = "Exception 8.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}			

			query = "\n UPDATE SP SET TIMKIEM = "+ 
			"\n upper(dbo.ftBoDau(sp.MA)) + '-' +upper(dbo.ftBoDau(isnull(SP.BARCODE,''))) " +
			"\n +'-'+ upper(dbo.ftBoDau(isnull(SP.ten,''))) " +
			"\n + '-' +upper(dbo.ftBoDau(isnull(DVDL.DONVI,''))) " +
			"\n FROM SANPHAM SP INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=SP.DVDL_FK " +     
			"\n where SP.PK_SEQ = '"+this.id+"' ";
			if (!db.update(query))
			{
				this.msg = "Exception 9.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}
			
			query = "\n INSERT INTO ERP_KHOTT_SANPHAM(KHOTT_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,GIATON) " +
			"\n SELECT PK_SEQ AS KHOID, '"+this.id+"' AS SPID, 0 as SoLuong, 0 as Booked, 0 as avai,0 AS GIA " +
			"\n FROM ERP_KHOTT KHO "+
			"\n WHERE NOT EXISTS ( SELECT * FROM ERP_KHOTT_SANPHAM WHERE SANPHAM_FK='"+this.id+"' AND KHO.PK_SEQ = KHOTT_FK) ";
			if (!db.update(query))
			{
				this.msg = "Exception 10.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}

			query = "delete from nhomhang_sanpham where sanpham_fk='"+this.id+"'";
			if (!db.update(query))
			{
				this.msg = "Exception 11.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}

			query = "\n INSERT INTO ERP_KHOTT_SP_CHITIET(KHOTT_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,NGAYHETHAN,NGAYSANXUAT,NgayNhapKho) " + 
			"\n SELECT KHO.PK_SEQ AS KHOID,SP.PK_SEQ AS SPID,0 AS SL,0 AS BOOKED,0 AS AVAI ,'NA','2030-12-31','2030-12-31',convert(char(10),getdate(),126) " +
			"\n FROM ERP_KHOTT KHO ,SANPHAM SP " +
			"\n WHERE NOT EXISTS (SELECT * FROM ERP_KHOTT_SP_CHITIET WHERE SANPHAM_FK=SP.PK_SEQ AND KHO.PK_SEQ=KHOTT_FK AND SOLO = 'NA') " ;  
			if (!db.update(query))
			{
				this.msg = "Exception 12.";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}

			if (Utility.isValid(this.nhomHangId))
			{
				query = "\n insert into nhomhang_sanpham(nhomhang_fk,sanpham_fk)" +
				"\n select pk_seq, '"+this.id+"'  " +
				"\n from NhomHang where pk_seq in ("+this.nhomHangId+")  ";
				if (!this.db.update(query))
				{
					this.msg = "Exception 13.";
					this.db.getConnection().rollback();
					this.db.getConnection().setAutoCommit(true);
					return false;
				}
			}
			
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(false);
		}

		catch(Exception e)
		{
			e.printStackTrace();
			this.db.update("rollback");
			this.msg = "Exceptiont: "+e.getMessage();
			return false;
		}
		
		return true; 	
	}
	
	public boolean CreateSp()
	{
		String query = "";

		try
		{
			if (masanpham())
			{
				this.msg = "Mã sản phẩm đã trùng!.";
				return false;
			}

			this.db.getConnection().setAutoCommit(false);
			this.ngaysua = getDateTime();
			this.nguoisua = this.userId;

			String cloai = Utility.isValid(this.clId) ? this.clId : "null";
			String nhanhang = Utility.isValid(this.nhId) ? this.nhId : "null";
			if (!Utility.isValid(PacksizeId)){
				this.PacksizeId = null;
			}

			String dvdl_etc_fk = !Utility.isValid(this.dvdlETCId) ? "NULL" : this.dvdlETCId;		
			String nccId_fk = !Utility.isValid(this.nccId) ? "NULL" : this.nccId;
/*
			if (!Utility.isValid(nhId))
				nhId = null;*/
			
			if (!Utility.isValid(nccId)) {				
				this.msg = "Vui lòng chọn nhà cung cấp!";
				this.db.getConnection().rollback();
				return false;
			}

			query = "\n insert into sanpham(tontoithieu,isKm, PT_VAT,PT_VAT_2, khoErp_fk, DVDL_ETC_FK, hansudung, tenchuan, quydoithungthuong, nganhhang_fk, " +
			"\n machuan,packsize_fk,ma,ten,ngaytao,ngaysua,nguoitao,nguoisua,dvdl_fk,trangthai,dvkd_fk,nhanhang_fk,chungloai_fk,type,trongluong,thetich, loaisanpham_fk,spchuluc,spmoi,tenviettat,thanhphan,dangbaoche,hamluong,QCDongGoi,ncc_fk,hinhanh) " +
			"\n values("+this.tontoithieu+","+this.isKm+","+this.pt_vat+","+this.pt_vat_2+"," +nccId_fk + "," + dvdl_etc_fk + ",'"+this.hansudung+"',N'"+this.tenchuan+"',"+this.Quydoithuong+","+(this.nganhhangid.equals("")? null:this.nganhhangid)+",'"+this.machuan+"',"+this.PacksizeId+",'" + this.masp + "', N'" + this.ten + "', '" + this.ngaysua + "','" + this.ngaysua + "','" + this.userId + "','" + this.userId + "', '" + this.dvdlId + "','" + this.trangthai + "', '" + this.dvkdId + "',"+ nhanhang + "," + cloai + ",'" + this.type + "','" + this.kl + "','" + this.tt + "', null,'"+ this.spchuluc+"','"+this.spmoi+"',N'"+this.tenviettat+"',N'"+this.thanhphan+"',N'"+this.dangbaoche+"',N'"+this.hamluong+"',N'"+this.QcDongGoi+"','"+this.nccId+"', N'"+this.hinhanh+"')";
			System.out.println("-----1.INSERT SANPHAM: " + query);
			if (!this.db.update(query))
			{
				this.msg = "Exception 1.";			
				this.db.getConnection().rollback();
				return false;
			}

			query = "select scope_identity() as spId";
			ResultSet rs = this.db.get(query);
			rs.next();
			this.id = rs.getString("spId");
			if (rs != null) rs.close();
			
			boolean error = false;

			for (int i = 0; i < 5 ; i++)
			{
				if(!(sl1[i] == null) )
				{
					sl1[i]=sl1[i].trim();
					sl2[i]=sl2[i].trim();
					if ( sl1[i].length() > 0 && Double.parseDouble(sl1[i])==0)
					{
						this.msg = " Không được nhập quy cách bằng 0";			
						this.db.getConnection().rollback();
						return false;
					}
					if (sl2[i].length() > 0 && Double.parseDouble(sl2[i])==0)
					{
						this.msg = " Không được nhập quy cách bằng 0";			
						this.db.getConnection().rollback();
						return false;
					}
					if ((sl1[i].length() > 0) && (dvdl1[i].length() > 0) && (sl2[i].length() > 0) && (dvdl2[i].length() > 0))
					{ 
						if (Utility.isValid(sl1[i]) && !sl1[i].equals("0") && Utility.isValid(sl2[i]) && !sl2[i].equals("0")) {
							query = "\n insert into quycach(sanpham_fk, dvdl1_fk, soluong1, dvdl2_fk, soluong2,stt) " +
							"\n values('"+ this.id +"','"+dvdl1[i]+"','"+ sl1[i] +"','"+ dvdl2[i] +"','"+sl2[i] +"','"+i+"')";
							System.out.println("--CHEN QUY CACH: " + query);
							if (!(this.db.update(query)))
							{
								this.msg = "Lỗi tạo mới 2.";			
								this.db.getConnection().rollback();
								return false;
							}
						}
						if (dvdl2[i].equals("100018"))
							error = false;
					}
				}
			}	
			
			/*boolean error = false;*/
			query = "select count(*) c from quycach where sanpham_fk = "+this.id;
			rs = db.get(query);
			while (rs.next()) {
				int count = rs.getInt("c");
				if (count <= 0) {
					error = true;
				}
			}
			rs.close();
			
			if (error)
			{
				this.db.getConnection().rollback();
				this.msg = "Vui lòng nhập quy cách sản phẩm.";
				return false;
			}
			if (this.nspIds != null)
			{
				int size = nspIds.length;
				for (int i = 0; i < size ; i++)
				{				
					if (this.nspIds[i] != null)
					{
						query = "insert into nhomsanpham_sanpham(sp_fk, nsp_fk) values('" + this.id + "','" + nspIds[i] + "')";
						if (!this.db.update(query)){
							this.msg = "Exception 3";			
							this.db.getConnection().rollback();
							return false;
						}
					}		
				}
			}

			if (Utility.isValid(type) && type.equals("1"))
			{
				if (this.spIds != null)
				{
					for(int i = 0; i < this.spIds.length; i++)
					{
						if (this.spIds[i] != null)
						{
							String[] arr = this.spIds[i].split("-");
							query = "insert into Bundle_Sanpham(bundle_fk, sanpham_fk, soluong) values('" + this.id + "','" + arr[0] + "', '" + arr[1] + "')";
							System.out.print("4.insett: "+query);
							if (!this.db.update(query)){
								this.msg = "Exception 4.";			
								this.db.getConnection().rollback();
								return false;
							}
						}
					}
				}
			}

			query = "insert into bgmuanpp_sanpham ( BGMUANPP_FK, SANPHAM_FK, GIAMUANPP, TRANGTHAI, giamuanpp_tuvc ) " +
			"select pk_seq,'" + this.id + "', 0, '0', 0 from banggiamuanpp  ";
			System.out.println("-----5.INSERT SANPHAM: " + query);
			if (!this.db.update(query))
			{
				this.msg = "Exception 5.";			
				this.db.getConnection().rollback();
				return false;
			}

			rs = this.db.get("select pk_seq from banggiasieuthi where dvkd_fk='" + this.dvkdId + "'");
			while (rs.next())
			{
				query = "insert into banggiast_sanpham values('" + rs.getString("pk_seq") + "','" + this.id + "', '0')";
				if (!this.db.update(query))
				{
					this.msg = "Exception 6.";			
					this.db.getConnection().rollback();
					return false;
				}
			}
			
			if (rs != null)
				rs.close();

			//Them vao bangphanquyen sanpham 
			query = "\n insert into nhanvien_Sanpham(nhanvien_Fk,Sanpham_fk)  "+
			"\n select pk_Seq,'"+this.id+"'  as spId  "+
			"\n from nhanvien   "+
			"\n where PHANLOAI=2 and trangthai=1 ";
			System.out.println("-----7.INSERT SANPHAM: " + query);
			if (!db.update(query))
			{
				this.msg = "Exception 7.";			
				this.db.getConnection().rollback();
				return false;
			}

			/*
			 * Chen nhung san pham chua co trong kho.
			 * Theo don vi kinh doanh va kenh ban hang cua npp
			 */
			query = "\n insert into nhapp_kho(npp_fk,kbh_fk,KHO_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE) " +
			"\n select  npp.PK_SEQ, kenh.PK_SEQ as kbhId, kho.PK_SEQ as khoId, sp.PK_SEQ as spId, " +
			"\n (select case when npp.quanlykho = 0 then 999999 else 0 end) as SoLuong, 0 as Booked, " +
			"\n (select case when npp.quanlykho = 0 then 999999 else 0 end) as avail " +
			"\n from KHO kho, SANPHAM sp, KENHBANHANG kenh, NHAPHANPHOI npp " +  
			"\n where not exists " +  
			"\n ( " +
			"\n     select * from  NHAPP_KHO a " +
			"\n     where a.KHO_FK = kho.PK_SEQ and a.KBH_FK = kenh.PK_SEQ " +
			"\n     and a.SANPHAM_FK = sp.PK_SEQ and a.npp_fk = npp.pk_Seq " +
			"\n ) " + 
			"\n and sp.pk_Seq = "+this.id+
			"\n and npp.pk_seq in (select distinct a.pk_seq as nppId from nhaphanphoi a where ISNULL(isKHACHHANG,0) = 0 and trangthai = 1) ";
			if (!db.update(query))
			{
				this.msg = "Exception 8.";			
				this.db.getConnection().rollback();
				return false;
			}			

			query = "\n INSERT INTO nhapp_kho_chitiet(KHO_FK,NPP_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,SOLUONG,BOOKED,AVAILABLE,GIAMUA,soluongNXT,NGAYNHAPKHO,Tonthoidiem) " + 
			"\n select distinct k.kho_fk, k.npp_fk, "+this.id+", k.kbh_fk, 'NA', '2030-12-31', " +
			"\n (select case when (select quanlykho from nhaphanphoi where pk_seq = k.npp_fk) = 0 then 999999 else 0 end) as SoLuong, " +
			"\n 0 as Booked,(select case when (select quanlykho from nhaphanphoi where pk_seq = k.npp_fk) = 0 then 999999 else 0 end) as avail, " +
			"\n 0, 0, convert(char(10),getdate(),126),0 " +
			"\n from nhapp_kho k " +
			"\n where npp_fk in (select distinct a.pk_seq as nppId from nhaphanphoi a where ISNULL(isKHACHHANG,0) = 0 and trangthai = 1) " +
			"\n and kbh_fk in (select pk_seq from kenhbanhang where trangthai = '1') ";
			if (!db.update(query))
			{
				this.msg = "Exception 9.";			
				this.db.getConnection().rollback();
				return false;
			}

			query = "UPDATE SP SET TIMKIEM = " + 
			"\n upper(dbo.ftBoDau(sp.MA)) + '-' +upper(dbo.ftBoDau(isnull(SP.BARCODE,''))) " +
			"\n +'-'+ upper(dbo.ftBoDau(isnull(SP.ten,''))) + '-' +upper(dbo.ftBoDau(isnull(DVDL.DONVI,''))) " +
			"\n FROM SANPHAM SP INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ = SP.DVDL_FK " +     
			"\n where SP.PK_SEQ = '"+this.id+"' ";
			if (!db.update(query))
			{
				this.msg = "Exception 10.";			
				this.db.getConnection().rollback();
				return false;
			}
			
			query= "\n INSERT INTO ERP_KHOTT_SANPHAM(KHOTT_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,GIATON) " +
			"\n SELECT PK_SEQ AS KHOID, '"+this.id+"' AS SPID, 0 AS SL, 0 AS BOOKED, 0 AS AVAI, 0 AS GIA " +
			"\n FROM ERP_KHOTT KHO " +
			"\n WHERE NOT EXISTS (SELECT * FROM ERP_KHOTT_SANPHAM WHERE SANPHAM_FK = '"+this.id+"' AND KHO.PK_SEQ = KHOTT_FK) ";
			if (!db.update(query))
			{
				this.msg = "Exception 11.";			
				this.db.getConnection().rollback();
				return false;
			}

			query = "\n INSERT INTO ERP_KHOTT_SP_CHITIET(KHOTT_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,NGAYHETHAN,NGAYSANXUAT,ngaynhapkho) " + 
			"\n SELECT KHO.PK_SEQ AS KHOID, SP.PK_SEQ AS SPID, 0 AS SL, 0 AS BOOKED, 0 AS AVAI, 'NA', " +
			"\n '2030-12-31', '2030-12-31', convert(char(10),getdate(),126) " +
			"\n FROM ERP_KHOTT KHO, SANPHAM SP " +
			"\n WHERE NOT EXISTS (SELECT * FROM ERP_KHOTT_SP_CHITIET WHERE SANPHAM_FK = SP.PK_SEQ AND KHO.PK_SEQ = KHOTT_FK AND SOLO = 'NA') ";  
			if (!db.update(query))
			{
				this.msg = "Exception 12.";			
				this.db.getConnection().rollback();
				return false;
			}

			if (Utility.isValid(nhomHangId))
			{
				query = "\n insert into nhomhang_sanpham(nhomhang_fk,sanpham_fk) " +
				"\n select pk_seq, "+this.id+
				"\n from NhomHang where pk_seq in ("+this.nhomHangId+") ";
				if (!this.db.update(query))
				{
					this.msg = "Exception 13.";			
					this.db.getConnection().rollback();
					return false;
				}
			}

			query =	"\n update Sp set TKVT = " +
			"\n case " +
			"\n when data.dauso IN ('3','5','6','9') then '156' else '155' end, " +
			"\n tkdt = case " +
			"\n when data.dauso IN ('3','5') then '51113' " + 
			"\n when data.dauso IN ('6') then '51116' " +
			"\n when data.dauso IN ('9') then '51119' else '5112' end, " +
			"\n tkgv = case " +
			"\n when data.dauso IN ('3','5') then '63213' " +
			"\n when data.dauso IN ('6') then '63216' " +
			"\n when data.dauso IN ('9') then '63219' else '6322' end, " +
			"\n TKCK = case " +
			"\n when data.dauso IN ('3','5') then '51113' " + 
			"\n when data.dauso IN ('6') then '51116' " +
			"\n when data.dauso IN ('9') then '51119' else '5112' end " + 
			"\n from " +
			"\n ( " +
			"\n select PK_SEQ as spId, MA, SUBSTRING(ma,1,1) as DauSo " +
			"\n from SANPHAM " +
			"\n )as data inner join SANPHAM sp on sp.PK_SEQ = data.spId " +
			"\n where sp.pk_seq = '"+this.id+"' ";
			if (!this.db.update(query))
			{
				this.msg = "Exception 14.";			
				this.db.getConnection().rollback();
				return false;
			}

			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			this.msg = "Exception: "+e.getMessage();
			this.db.update("rollback");
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

	public void init()
	{
		try
		{
			String query = "\n select a.tontoithieu,A.HINHANH as hinhanh, a.isKm, isnull(a.pt_vat,0) pt_vat, isnull(a.PT_VAT_2,0) PT_VAT_2, a.ncc_fk, isnull(tenviettat,'') tenviettat, " +
			"\n isnull(thanhphan,'') thanhphan, isnull(dangbaoche,'') dangbaoche, isnull(hamluong,'') hamluong, " +
			"\n isnull(a.nhanhang_fk,0) as nhanhang_fk, isnull(a.hansudung,180) as HanSuDung, isnull(tenchuan,'') as tenchuan, " +
			"\n isnull(quydoithungthuong,1) as quydoithungthuong, isnull(nganhhang_fk,0) as nganhhang_fk, " +
			"\n isnull(machuan,'') as machuan, isnull(a.packsize_fk ,0) as packsize_fk, a.pk_seq as id, a.ma as masp, " +
			"\n a.ten as tensp, b.donvikinhdoanh as dvkd, b.pk_seq as dvkdId, c.pk_seq as clId, c.ten as chungloai, " +
			"\n e.pk_seq as nhId, d.donvi, e.ten as nhanhang, isnull(d.pk_seq,0) as dvdlId, a.trangthai, " +
			"\n f.giabanlechuan as giablc, isnull(a.type,'' ) as type, a.trongluong, a.thetich, a.loaisanpham_fk, " +
			"\n a.DVDL_ETC_FK, a.spmoi, a.spchuluc,isnull(a.QcDongGoi,'') as QcDongGoi " +
			"\n from sanpham a " +
			"\n left join donvikinhdoanh b on a.dvkd_fk = b.pk_seq " +
			"\n left join chungloai c on a.chungloai_fk = c.pk_seq " +
			"\n left join donvidoluong d on a.dvdl_fk = d.pk_seq " +
			"\n left join nhanhang e on a.nhanhang_fk = e.pk_seq " +
			"\n left join banggiablc_sanpham f on a.pk_seq = f.sanpham_fk " +
			"\n left join banggiabanlechuan g on f.bgblc_fk = g.pk_seq " +
			"\n where a.pk_seq = '" + this.id + "'";
			System.out.print("Init SP: " + query);		
			ResultSet rs =  this.db.get(query);
			while (rs.next()) {    
				this.tontoithieu = rs.getString("tontoithieu")==null?0:rs.getDouble("tontoithieu");
				//this.isKtl = rs.getString("isKtl");
				this.nccId = rs.getString("ncc_fk");
				this.pt_vat = rs.getDouble("pt_vat");
				this.pt_vat_2 = rs.getDouble("PT_VAT_2");
				this.isKm = rs.getString("isKm");
				this.id = rs.getString("id");
				this.masp = rs.getString("masp");
				this.tenchuan = rs.getString("tenchuan");
				this.Quydoithuong = rs.getString("quydoithungthuong");
				this.ten = rs.getString("tensp");
				this.machuan = rs.getString("machuan");
				this.nganhhangid = rs.getString("nganhhang_fk");
				this.PacksizeId = rs.getString("packsize_fk");
				this.hansudung = rs.getString("HanSuDung");
				this.tenviettat = rs.getString("tenviettat");
				this.hamluong = rs.getString("hamluong");
				this.dangbaoche = rs.getString("dangbaoche");
				this.thanhphan = rs.getString("thanhphan");
				this.QcDongGoi = rs.getString("qcdonggoi");
				this.dvkdId = "";
				this.hinhanh = rs.getString("hinhanh")==null?"":rs.getString("hinhanh");
				if (rs.getString("dvkdId") != null){
					this.dvkdId = rs.getString("dvkdId");
				}

				this.nhId = "";
				if (rs.getString("nhId") != null){
					this.nhId = rs.getString("nhId");
				}

				this.clId = "";
				if (rs.getString("clId") != null){
					this.clId = rs.getString("clId");
				}

				this.giablc = "";
				if (rs.getString("giablc") != null)
					this.giablc = rs.getString("giablc");

				this.trangthai = rs.getString("trangthai"); 	
				this.type = rs.getString("type");

				this.dvdlId = "";
				if (rs.getString("dvdlId") != null){
					this.dvdlId = rs.getString("dvdlId");
				}

				if (rs.getString("trongluong") != null)
					this.kl = rs.getString("trongluong");

				if (rs.getString("thetich") != null)
					this.tt = rs.getString("thetich");

				if (rs.getString("DVDL_ETC_FK") != null )
					this.dvdlETCId = rs.getString("DVDL_ETC_FK");

				this.spchuluc = rs.getString("spchuluc") == null ? "" : rs.getString("spchuluc");
				this.spmoi = rs.getString("spmoi") == null ? "" : rs.getString("spmoi");
			}
			rs.close();

			//Get quy cách
			query = "\n select soluong1 as sl1, dvdl1_fk as dvdl1, soluong2 as sl2, dvdl2_fk as dvdl2 " +
			"\n from quycach where sanpham_fk = '" + id + "' and dvdl1_fk = "+this.dvdlId+
			"\n and dvdl2_fk = 100018 order by stt ";
			System.out.println("quy cach 1 "+query);
			rs = this.db.get(query);
			if (rs !=null){
				if (rs.next()){
					this.sl1[0] = rs.getString("sl1");
					this.dvdl1[0] = rs.getString("dvdl1");
					this.sl2[0] = rs.getString("sl2");
					this.dvdl2[0] = rs.getString("dvdl2");
				}
				rs.close();
			}

			query = " select soluong1 as sl1, dvdl1_fk as dvdl1, soluong2 as sl2, dvdl2_fk as dvdl2 " +
			"\n from quycach " +
			"\n where sanpham_fk = '" + id + "' and (dvdl1_fk <> "+this.dvdlId+" or dvdl2_fk <> 100018) " +
			"\n order by stt "; 
			System.out.println("quy cach 2 "+query);
			rs = this.db.get(query);
			int i = 1;
			if (rs != null){
				while (rs.next())
				{
					this.sl1[i] = rs.getString("sl1");
					this.dvdl1[i] = rs.getString("dvdl1");
					this.sl2[i] = rs.getString("sl2");
					this.dvdl2[i] = rs.getString("dvdl2");
					System.out.print("Don vi do luong la: " + this.sl1[i] + "---- sl2 "+this.sl2[i]+" \n");
					
					i++;
				}
				rs.close();
			}

			if (Utility.isValid(id))
			{
				query = "select nhomhang_fk, sanpham_fk from nhomhang_sanpham where sanpham_fk = '"+this.id+"'";
				rs = this.db.get(query);
				try
				{
					while(rs.next())
					{
						this.nhomHangId += "," + rs.getString("nhomhang_fk");
					}
					rs.close();

					if (Utility.isValid(nhomHangId))
					{
						nhomHangId = nhomHangId.substring(1);
					}
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}

			CreateRS();
			createNspIds();
			createSpIds();
		}
		catch (java.sql.SQLException e){
			e.printStackTrace();
		}
	}
	
	public void DBClose(){
		
		try{				
			if (this.nh != null)
				this.nh.close();
			if (this.cl != null)
				this.cl.close();
			if (this.dvdl != null)
				this.dvdl.close();
			if (this.dvkd != null)
				this.dvkd.close();
			if (this.nsp != null)
				this.nsp.close();
			if (rsPacksize!=null){
				rsPacksize.close();
			}
			this.db.shutDown();	
		}catch(java.sql.SQLException e){}
	}

	public void setType(String type) 
	{	
		this.type = type;
	}
	
	public String getType() 
	{	
		return this.type;
	}
	
	public ResultSet getSanphamRs()
	{
		return this.spList;
	}
		
	public void setSanphamRs(ResultSet spRs) 
	{
		this.spList = spRs;	
	}
		
	public Hashtable<Integer, String> getSpIds() 
	{	
		Hashtable<Integer, String> selected = new Hashtable<Integer, String>();
		if (this.spIds != null){
			int size = (this.spIds).length;
			int m = 0;
			while(m < size){
				selected.put(new Integer(m), this.spIds[m]) ;
				m++;
			}
		}
		else{
			selected.put(new Integer(0), "null");
		}
		return selected;
	}
	
	public void setSpIds(String[] spIds)
	{
		this.spIds = spIds;
	}

	private void createSpList()
	{
		if (this.type.equals("1"))
		{
			String query = "select pk_seq, ma as spMa, ten as spTen, isnull(bdsp.soluong,'0') as soluong ";
			if (this.nhanhangIds.length() > 0)
				query = query +	" and nhanhang_fk  in (" + this.nhanhangIds + ")";
			if (this.chungloaiIds.length() > 0)
				query = query +	" and chungloai_fk in(  " + this.chungloaiIds + " ) ";
			System.out.println("Id : "+this.id);
			query+=
			"from sanpham sp left join "+ 
			"( "+
			"	select bundle_fk,sanpham_fk,soluong "+
			"	from  "+
			"    bundle_sanpham " +
			"   where 1=1   " ;
			if (this.id.length() > 0)
			{
				//this.spSelectedList = this.db.get(query + " and pk_seq in(select sanpham_fk from Bundle_Sanpham where bundle_fk='" + this.id + "')");
				query+= 
				"   and bundle_fk='"+this.id+"' and sanpham_fk !='"+this.id+"' ";
			}
			query+=
			")bdsp on sp.pk_seq = bdsp.sanpham_fk " +
			" where dvkd_fk = '"+this.dvkdId+"'"+
			" order by soluong desc ";
			System.out.print("SP List : "+query);
			this.spList = db.get(query);
		}
	}

	public void setNspSelected(ResultSet nsp)
	{
		this.nspSelected = nsp;
	}

	public ResultSet getNspSelected() 
	{
		return this.nspSelected;
	}

	public ResultSet getSanphamSelectedRs() 
	{
		return this.spSelectedList;
	}

	public void setSanphamSelectedRs(ResultSet spRs) 
	{
		this.spSelectedList = spRs;
	}

	public void init2() 
	{
		try
		{
			String query ="select  a.ncc_fk,isnull(tenviettat,'') tenviettat,isnull(thanhphan,'') thanhphan,isnull(dangbaoche,'') dangbaoche,isnull(hamluong,'') hamluong,isnull(a.nhanhang_fk,0) as nhId,isnull(a.hansudung,180) as HanSuDung,a.pk_seq as id, a.ma as masp, a.ten as tensp, b.donvikinhdoanh as dvkd, b.pk_seq as dvkdId, c.pk_seq as clId, c.ten as chungloai, e.pk_seq as nhId, d.donvi, e.ten as nhanhang, d.pk_seq as dvdlId, a.trangthai,f.giabanlechuan as giablc, a.type, a.DVDL_ETC_FK,a.spchuluc,a.spmoi ";
			query = query + " from sanpham a inner join donvikinhdoanh b on a.dvkd_fk = b.pk_seq left join chungloai c on a.chungloai_fk = c.pk_seq left join donvidoluong d on a.dvdl_fk = d.pk_seq left join nhanhang e on a.nhanhang_fk = e.pk_seq ";
			query = query  + " inner join banggiablc_sanpham f on a.pk_seq = f.sanpham_fk inner join banggiabanlechuan g on f.bgblc_fk = g.pk_seq ";
				     
			query = query + " where a.pk_seq = '" + this.id + "'";

			ResultSet rs =  this.db.get(query);
	    
	        rs.next(); 
	        this.nccId = rs.getString("ncc_fk");
	        this.tenviettat=rs.getString("tenviettat");
	    	this.hamluong=rs.getString("hamluong");
	    	this.dangbaoche=rs.getString("dangbaoche");
	    	this.thanhphan=rs.getString("thanhphan");
	        
	    	this.id = rs.getString("id");
	    	this.masp = rs.getString("masp");
	    	this.ten = rs.getString("tensp");
	    	this.hansudung=rs.getString("HanSuDung");
	    	if (rs.getString("dvkdId")== null){
	    		this.dvkdId = "";
	    	}else{
	    		this.dvkdId = rs.getString("dvkdId");
	    	}
	    	if (rs.getString("nhanhang_fk")== null){
	    		this.nhId = "";
	    	}else{
	    		this.nhId = rs.getString("nhanhang_fk");
	    	}

	    	if (rs.getString("clId")== null){
	    		this.clId = "";
	    	}else{
	    		this.clId = rs.getString("clId");
	    	}

	    	this.giablc = rs.getString("giablc");
	    	this.trangthai = rs.getString("trangthai"); 	
	    	this.type = rs.getString("type");

	    	if (rs.getString("dvdlId")== null){
	    		this.dvdlId = "";
	    	}else{
	    		this.dvdlId = rs.getString("dvdlId");
	    	}
	    	
	    	if (rs.getString("DVDL_ETC_FK") != null )
	    		this.dvdlETCId = rs.getString("DVDL_ETC_FK");
	    	this.spchuluc=rs.getString("spchuluc");
	    	this.spmoi=rs.getString("spmoi");
	    	rs.close();
	    	
	    	query = "select soluong1 as sl1, dvdl1_fk as dvdl1, soluong2 as sl2, dvdl2_fk as dvdl2 from quycach where sanpham_fk = '" + id + "' order by stt"; 
	    	//this.msg =query;
	    	rs= this.db.get(query);
	    	int i = 0;
	    	while (rs.next()){
	    		this.sl1[i] = rs.getString("sl1");
	    		this.dvdl1[i] = rs.getString("dvdl1");
	    		this.sl2[i] = rs.getString("sl2");
	    		this.dvdl2[i] = rs.getString("dvdl2");
	    		i++;
	    	}
	    	rs.close();
	    	
	    	this.dvdl = createDvdlRS();
			
			this.dvkd = createDvkdRS();
			this.nh = createNhRS();
			this.cl= createClRS();

			this.createNspRS2();
			this.createSpList2();	
			
	    	createNspIds();
			createSpIds();
	    	
	   	}catch (java.sql.SQLException e){}
	}

	private void createSpList2() 
	{
		String query = "select pk_seq, ma as spMa, ten as spTen from sanpham where pk_seq in(select sanpham_fk from Bundle_Sanpham where bundle_fk='" + this.id + "')";
		this.spSelectedList = this.db.get(query);					
	}

	private void createNspRS2() 
	{
		this.nspSelected = this.db.get("select a.nsp_fk as nspId, b.TEN as nspTen, b.diengiai as diengiai from nhomsanpham_sanpham a inner join nhomsanpham b on a.nsp_fk = b.pk_seq where a.sp_fk = '" + this.id + "'");
	}
	
	
	public void setKL(String kl) 
	{
		this.kl = kl;
	}

	public void setDVKL(String dvkl) 
	{
		this.dvkl = dvkl;
	}

	public void setDVTT(String dvtt)
	{
		this.dvtt = dvtt;
	}

	public void setTT(String tt) 
	{
		this.tt = tt;
	}

	public String getKL()
	{
		return this.kl;
	}

	public String getDVKL() 
	{
		return this.dvkl;
	}

	public String getDVTT() 
	{
		return this.dvtt;
	}

	public String getTT()
	{
		return this.tt;
	}

	@Override
	public ResultSet getPacksizeRs() {

		return this.rsPacksize;
	}

	@Override
	public void setPacksizeRs(ResultSet rs) {

		this.rsPacksize=rs;
	}

	@Override
	public String getPacksizeId() {

		return this.PacksizeId;
	}

	@Override
	public void setPacksizeId(String packsizeid) {

		this.PacksizeId=packsizeid;
	}

	@Override
	public String getMachuan() {

		return this.machuan;
	}

	@Override
	public void setMachuan(String _machuan) {

		this.machuan=_machuan;
	}

	@Override
	public String getNganhhangid() {

		return this.nganhhangid;
	}

	@Override
	public void setNganhhangid(String nganhhangid) {

		this.nganhhangid=nganhhangid;
	}

	@Override
	public ResultSet getRsNganhHang() {

		return this.RsNganhHang;
	}

	@Override
	public void setRsNganhhang(ResultSet rs) {

		this.RsNganhHang=rs;
	}

	@Override
	public String getquydoithuong() {

		return this.Quydoithuong;
	}

	@Override
	public void setquydoithuong(String quydoithuong) {

		this.Quydoithuong=quydoithuong;
	}

	@Override
	public String getTenchuan() {

		return this.tenchuan;
	}

	@Override
	public void setTenchuan(String Tenchuan) 
	{
		this.tenchuan=Tenchuan;
	}

	public String getHansudung()
	{
		return hansudung;
	}

	public void setHansudung(String hansudung)
	{
		this.hansudung = hansudung;
	}

	public String getNhanhangIds()
	{
		return nhanhangIds;
	}

	public void setNhanhangIds(String nhanhangIds)
	{
		this.nhanhangIds = nhanhangIds;
	}

	public String getChungloaiIds()
	{
		return chungloaiIds;
	}

	public void setChungloaiIds(String chungloaiIds)
	{
		this.chungloaiIds = chungloaiIds;
	}

	public String getDvdlETCId()
	{
		return this.dvdlETCId;
	}

	public void setDvdlETCId(String dvdlETCId) 
	{
		this.dvdlETCId = dvdlETCId;
	}

	String nhomHangId;
	ResultSet nhomHangRs;

	public String getNhomHangId()
  {
  	return nhomHangId;
  }

	public void setNhomHangId(String nhomHangId)
  {
  	this.nhomHangId = nhomHangId;
  }

	public ResultSet getNhomHangRs()
  {
  	return nhomHangRs;
  }

	public void setNhomHangRs(ResultSet nhomHangRs)
  {
  	this.nhomHangRs = nhomHangRs;
  }

	public String getSpMoi() {
		return this.spmoi;
	}

	public void setSpMoi(String spmoi) {
		this.spmoi=spmoi;
	}

	public String getSpChuLuc() {
		return this.spchuluc;
	}
	
	public void setSpChuLuc(String spchuluc) {
		this.spchuluc=spchuluc;
	}
	public String getTenviettat() {
		return tenviettat;
	}

	public void setTenviettat(String tenviettat) {
		this.tenviettat = tenviettat;
	}

	public String getThanhphan() {
		return thanhphan;
	}

	public void setThanhphan(String thanhphan) {
		this.thanhphan = thanhphan;
	}

	public String getDangbaoche() {
		return dangbaoche;
	}

	public void setDangbaoche(String dangbaoche) {
		this.dangbaoche = dangbaoche;
	}

	public String getHamluong() {
		return hamluong;
	}

	public void setHamluong(String hamluong) {
		this.hamluong = hamluong;
	}
	public String getQcDongGoi() {
		return QcDongGoi;
	}


	public void setQcDongGoi(String qcDongGoi) {
		QcDongGoi = qcDongGoi;
	}


	public String getNccId() {
		return nccId;
	}
	public void setNccId(String khoId) {
		this.nccId = khoId;
	}
	public ResultSet getNccRs() {
		return nccRs;
	}
	public void setNccRs(ResultSet khoRs) 
	{
		this.nccRs = khoRs;
	}
	public double getPt_vat() {
		return pt_vat;
	}
	public void setPt_vat(double pt_vat) {
		this.pt_vat = pt_vat;
	}
	
	public String getIsKm() {
		return isKm;
	}
	public void setIsKm(String isKm) {
		this.isKm = isKm;
	}
	
	public double getPt_vat_2() {
		return pt_vat_2;
	}
	public void setPt_vat_2(String pt_vat_2) {
		this.pt_vat_2 = geso.dms.center.util.Utility.parseDouble(pt_vat_2.replace(",",""));
	}
}


