package geso.dms.distributor.beans.xoanoncc.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.xoanoncc.IErpXoaNoNCC;
import geso.dms.distributor.beans.xoanoncc.IHoadon;

public class ErpXoaNoNCC implements IErpXoaNoNCC 
{

	String userId;
	String id;
	String ngaychungtu;
	String ngayghiso;
	
	String nppId;
	ResultSet nppRs;
	
	String htttId;
	ResultSet htttRs;
	
	String ndId;
	ResultSet ndRs;
	
	String nganhangId;
	ResultSet nganhangRs;
	String chinhanhId;
	ResultSet chinhanhRs;
	
	String sotaikhoan;
	String noidungtt;
	String sotientt;
	
	String ctttId;
	ResultSet ctttRs;
	
	String hoadonId;
	List<IHoadon> hoadonList;
	
	String ctttIds;
	List<IHoadon> ctttList;
	
	String loaixnId; //loại xóa nợ: Xóa nợ KH trả trước/ Xóa tạm ứng
	String DoiTuongTamUng="";
	String nccId="";
	ResultSet nccRs;
	
	String nvId="";
	ResultSet nvRs;
	
	ResultSet tienteRs;
	String tienteId;
	
	String congtyId;
	String nppdangnhap;
	
	String msg;
	dbutils db;
	
	public ErpXoaNoNCC()
	{
		this.id = "";
		this.ngaychungtu = "";
		this.ngayghiso = "";
		this.nppId = "";
		this.htttId = "";
		this.ndId = "";
		this.nganhangId = "";
		this.chinhanhId = "";
		this.sotaikhoan = "";
		this.noidungtt = "";
		this.sotientt = "";
		this.hoadonId = "";
		this.ctttId = "";
		this.ctttIds = "";
		
		this.loaixnId="0";
		this.DoiTuongTamUng="0";
		this.nccId="";
		this.nvId="";
		this.tienteId = "100000";
		this.msg = "";
		this.congtyId = "";
		this.nppdangnhap = "";
		this.hoadonList = new ArrayList<IHoadon>();
		this.ctttList = new ArrayList<IHoadon>();
		
		this.db = new dbutils();
	}
	
	public ErpXoaNoNCC(String id)
	{
		this.id = id;
		this.ngaychungtu = "";
		this.ngayghiso = "";
		this.nppId = "";
		this.htttId = "";
		this.ndId = "";
		this.nganhangId = "";
		this.chinhanhId = "";
		this.sotaikhoan = "";
		this.noidungtt = "";
		this.sotientt = "";
		this.hoadonId = "";
		this.ctttId = "";
		this.ctttIds = "";
		
		this.loaixnId="";
		this.DoiTuongTamUng="";
		this.congtyId = "";
		this.nppdangnhap = "";
		
		this.msg = "";
		this.hoadonList = new ArrayList<IHoadon>();
		this.ctttList = new ArrayList<IHoadon>();
		
		this.db = new dbutils();
	}
	
	public void setDoiTuongTamUng(String doiTuongTamUng) {
		DoiTuongTamUng = doiTuongTamUng;
	}
	public String getDoiTuongTamUng() {
		return DoiTuongTamUng;
	}
	
	public void setNccId(String nccId) {
		this.nccId = nccId;
	}
	public String getNccId() {
		return nccId;
	}
	
	public void setNccRs(ResultSet nccRs)
	{
		this.nccRs = nccRs;
	}
	public ResultSet getNccRs() 
	{
		return nccRs;
	}
	
	
	public String getNvId() 
	{
		return nvId;
	}
	public void setNvId(String nvId)
	{
		this.nvId = nvId;
	}
	
	public ResultSet getNvRs() 
	{
		return nvRs;
	}
	public void setNvRs(ResultSet nvRs) 
	{
		this.nvRs = nvRs;
	}
	
	public String getLoaixnId() {
		return loaixnId;
	}
	public void setLoaixnId(String loaixnId) {
		this.loaixnId = loaixnId;
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

	public String getNgaychungtu() 
	{
		return this.ngaychungtu;
	}

	public void setNgaychungtu(String ngaychungtu) 
	{
		this.ngaychungtu = ngaychungtu;
	}

	public String getNppId()
	{
		return this.nppId;
	}

	public void setNppId(String nppId)
	{
		this.nppId = nppId;	
	}

	public ResultSet getNppRs()
	{
		return this.nppRs;
	}

	public void setNppRs(ResultSet nppRs) 
	{
		this.nppRs = nppRs;
	}

	public String getHtttId() 
	{
		return this.htttId;
	}

	public void setHtttId(String htttId)
	{
		this.htttId = htttId;
	}

	public ResultSet getHtttRs() 
	{
		return this.htttRs;
	}

	public void setHtttRs(ResultSet htttRs)
	{
		this.htttRs = htttRs;	
	}

	public String getNganhangId()
	{
		return this.nganhangId;
	}

	public void setNganhangId(String nganhangId)
	{
		this.nganhangId = nganhangId;
	}

	public ResultSet getNganhangRs() 
	{
		return this.nganhangRs;
	}

	public void setNganhangRs(ResultSet nganhangRs) 
	{
		this.nganhangRs = nganhangRs;
	}

	public String getChinhanhId() 
	{
		return this.chinhanhId;
	}

	public void setChinhanhId(String cnId)
	{
		this.chinhanhId = cnId;
	}

	public ResultSet getChinhanhRs()
	{
		return this.chinhanhRs;
	}

	public void setChinhanhRs(ResultSet chinhanhRs) 
	{
		this.chinhanhRs = chinhanhRs;
	}

	public String getSotaikhoan() 
	{
		return this.sotaikhoan;
	}

	public void setSotaikhoan(String sotk)
	{
		this.sotaikhoan = sotk;
	}

	public String getNoidungtt()
	{
		return this.noidungtt;
	}

	public void setNoidungtt(String ndtt) 
	{
		this.noidungtt = ndtt;
	}

	public String getSotientt() 
	{
		return this.sotientt;
	}

	public void setSotientt(String sotientt) 
	{
		this.sotientt = sotientt;
	}

	public String getHoadonIds() 
	{
		return this.hoadonId;
	}

	public void setHoadonIds(String hdIds) 
	{
		this.hoadonId = hdIds;
	}

	public List<IHoadon> getHoadonRs() 
	{
		return this.hoadonList;
	}

	public void setHoadonRs(List<IHoadon> hoadonRs)
	{
		this.hoadonList = hoadonRs;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}

	public ResultSet getTienteRs() {
		
		return this.tienteRs;
	}

	
	public void setTienteRs(ResultSet tienteRs) {
		
		this.tienteRs = tienteRs;
	}

	public String getTienteId() {
		
		return this.tienteId;
	}

	
	public void setTienteId(String ttId) {
		
		this.tienteId = ttId;
	}
	
	public boolean createTTHD() 
	{
		this.getNppInfo();
		if(this.ctttList.size() <= 0)
		{
			this.msg = "Vui lòng chọn chứng từ trả trước";
			return false;
		}
		
		if(this.sotientt.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số tiền thanh toán";
			return false;
		}
		
		//Tinh lai tong tien
		long tongthanhtoan = 0;
		for(int i = 0; i < this.hoadonList.size(); i++)
		{
			IHoadon hd = this.hoadonList.get(i);
			if(hd.getThanhtoan().length() > 0)
				tongthanhtoan += Long.parseLong(hd.getThanhtoan().replaceAll(",", ""));
		}
		
		if(tongthanhtoan <= 0)
		{
			this.msg = "Vui lòng chọn hóa đơn để thanh toán";
			return false;
		}
		
		try 
		{
			String ngaytao = getDateTime();
			
			db.getConnection().setAutoCommit(false);
			String query="";
			
		
			if(this.nccId.trim().length() > 0 || this.nvId.trim().length() > 0)
			{
				if(this.loaixnId.equals("0")) this.nvId = "NULL";
				else this.nccId = "NULL" ;
					
				 query = "Insert ERP_XOANONCC(TIENTE_FK, NGAYCHUNGTU, NGAYGHISO, TRANGTHAI, NCC_FK, NHANVIEN_FK , TONGTIENTRATRUOC, TONGTIENHOADON, GHICHU, NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA, LOAIXOANO, NPP_FK ) " +
					"values(" + this.tienteId + ", '" + this.ngaychungtu + "', '" + this.ngayghiso + "', '0', " + this.nccId + ", "+ this.nvId +" , '" + this.sotientt.replaceAll(",", "") + "', '" + tongthanhtoan + "', N'" + this.noidungtt + "', " +
							"'"  + ngaytao + "', '" + this.userId + "', '" + ngaytao + "', '" + this.userId + "',"+ this.loaixnId +", "+this.nppdangnhap+" )";
	
				if(!db.update(query))
				{
					this.msg = "Khong the tao moi ERP_XOANONCC: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
				}
			}
			String tthdCurrent = "";
			query = "select IDENT_CURRENT('ERP_XOANONCC') as tthdId";
			
			ResultSet rsTthd = db.get(query);						
			if(rsTthd.next())
			{
				tthdCurrent = rsTthd.getString("tthdId");
				rsTthd.close();
			}
			
			
			for(int i = 0; i < this.ctttList.size(); i++)
			{
				IHoadon hoadon = this.ctttList.get(i);

				String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
				String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
				String conlai = hoadon.getConlai().replaceAll(",", "");
				
				if(thanhtoan.length() > 0)
				{
					if(Float.parseFloat(thanhtoan) > 0)
					{
						if(hoadon.getLoaict().equals("0")) // CHI TẠM ỨNG
						{
							query = "Insert ERP_XOANONCC_TAMUNG(xoanoncc_fk, THANHTOANHOADON_FK, SOTIENTT, SOTIENAVAT, CONLAI) " +
									"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat + "', '" + conlai.trim() + "')";		
						}
						else if(hoadon.getLoaict().equals("1")) // BÚT TOÁN TỔNG HỢP
						{
							query = "Insert ERP_XOANONCC_BTTH(xoanoncc_fk, BTTH_FK, SOTIENTT, SOTIENAVAT, CONLAI) " +
									"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat + "', '" + conlai.trim() + "')";	
						}
						
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_XOANONCC_TAMUNG: " + query;
							System.out.println(this.msg);
							db.getConnection().rollback();
							return false;
						}	
						
					}
				}
			}
			
			for(int i = 0; i < this.hoadonList.size(); i++)
			{

				IHoadon hoadon = this.hoadonList.get(i);

				String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
			
				String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
				String conlai = hoadon.getConlai().replaceAll(",", "");
				
				if(thanhtoan.length() > 0)
				{
					if(Float.parseFloat(thanhtoan) > 0)
					{
						if(this.loaixnId.equals("0"))  // Xóa nợ NCC
						{
							query = "Insert ERP_XOANONCC_HOADONNCC(xoanoncc_fk, hoadonncc_FK, TIENTHANHTOAN, TIENCHUNGTU, CONLAI, LOAICT) " +
									"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat.trim() + "', '" + conlai.trim() + "', "+hoadon.getLoaict()+")";
							System.out.println(query);
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_XOANONCC_HOADONNCC: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
						}   
						else    // Xóa nợ nhân viên
						{
							query = "Insert ERP_XOANONCC_DNTT(xoanoncc_fk, dntt_FK, TIENTHANHTOAN, TIENCHUNGTU, CONLAI, LOAICT) " +
									"values('" + tthdCurrent + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat.trim() + "', '" + conlai.trim() + "', "+hoadon.getLoaict()+")";
							System.out.println(query);
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_XOANONCC_DNTT: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
						}
						
					}
				}
				
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (SQLException e) 
		{
			try 
			{
				db.getConnection().rollback();
			}
			catch (SQLException e1) {}
			return false;
		}
		
		return true;
	}

	public boolean updateTTHD() 
	{
		this.getNppInfo();
		
		if(this.ctttList.size() <= 0)
		{
			this.msg = "Vui lòng chọn chứng từ trả trước";
			return false;
		}
		
		if(this.sotientt.trim().length() <= 0)
		{
			this.msg = "Vui lòng nhập số tiền thanh toán";
			return false;
		}
		
		//Tinh lai tong tien
		long tongthanhtoan = 0;
		for(int i = 0; i < this.hoadonList.size(); i++)
		{
			IHoadon hd = this.hoadonList.get(i);
			if(hd.getThanhtoan().length() > 0)
				tongthanhtoan += Long.parseLong(hd.getThanhtoan().replaceAll(",", ""));
		}
		
		if(tongthanhtoan <= 0)
		{
			this.msg = "Vui lòng chọn hóa đơn để thanh toán";
			return false;
		}
		
		try 
		{
			String ngaysua = getDateTime();
			
			db.getConnection().setAutoCommit(false);
			String query="";

				if(this.loaixnId.equals("0")) this.nvId = "NULL";
				else this.nccId = "NULL" ;
			
				 query = "update ERP_XOANONCC set TIENTE_FK = " + this.tienteId + ", NGAYCHUNGTU = '" + this.ngaychungtu + "', NGAYGHISO = '" + this.ngayghiso + "', " +
					" NCC_FK = " + this.nccId + ", NHANVIEN_FK = "+ this.nvId +", GHICHU = N'" + this.noidungtt + "', " +
					" TONGTIENTRATRUOC = '" + this.sotientt.replaceAll(",", "") + "', TONGTIENHOADON = '" + tongthanhtoan + "',  " +
					" NGAYSUA = '" + ngaysua + "', NGUOISUA = '" + this.userId + "', LOAIXOANO = "+ this.loaixnId +" where PK_SEQ = '"  + this.id + "'";

					if(!db.update(query))
					{
					this.msg = "Khong the cap nhat ERP_XOANCCTHANHTOAN: " + query;
					System.out.println(this.msg);
					db.getConnection().rollback();
					return false;
					}
				
			
			if(this.loaixnId.equals("0"))	
			{
				query = "delete ERP_XOANONCC_HOADONNCC where XOANONCC_FK = '" + this.id + "'";
				if(!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_XOANONCC_HOADONNCC: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			else
			{
				query = "delete ERP_XOANONCC_DNTT where XOANONCC_FK = '" + this.id + "'";
				if(!db.update(query))
				{
					this.msg = "Khong the cap nhat ERP_XOANONCC_DNTT: " + query;
					db.getConnection().rollback();
					return false;
				}
			}
			
			query = "delete ERP_XOANONCC_TAMUNG where XOANONCC_FK = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_XOANONCC_TAMUNG: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete ERP_XOANONCC_BTTH where XOANONCC_FK = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Khong the cap nhat ERP_XOANONCC_BTTH: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			for(int i = 0; i < this.ctttList.size(); i++)
			{
				IHoadon hoadon = this.ctttList.get(i);

				String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
				String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
				String conlai = hoadon.getConlai().replaceAll(",", "");
				
				if(thanhtoan.length() > 0)
				{
					if(Float.parseFloat(thanhtoan) > 0)
					{
						if(hoadon.getLoaict().equals("0")) //CHI TẠM ỨNG
						{
							query = "Insert ERP_XOANONCC_TAMUNG(xoanoncc_fk, THANHTOANHOADON_FK, SOTIENTT, SOTIENAVAT, CONLAI) " +
									"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat + "', '" + conlai.trim() + "')";
						}
						else if(hoadon.getLoaict().equals("1"))
						{
							query = "Insert ERP_XOANONCC_BTTH(xoanoncc_fk, BTTH_FK, SOTIENTT, SOTIENAVAT, CONLAI) " +
									"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat + "', '" + conlai.trim() + "')";	
						}
						
						if(!db.update(query))
						{
							this.msg = "Khong the tao moi ERP_XOANONCC_TAMUNG: " + query;
							System.out.println(this.msg);
							db.getConnection().rollback();
							return false;
						}
					}
				}
			}
			
			for(int i = 0; i < this.hoadonList.size(); i++)
			{

				IHoadon hoadon = this.hoadonList.get(i);

				String thanhtoan = hoadon.getThanhtoan().replaceAll(",", "");
				String avat = hoadon.getTongtiencoVAT().replaceAll(",", "");
				String conlai = hoadon.getConlai().replaceAll(",", "");
				
				if(thanhtoan.length() > 0)
				{
					if(Float.parseFloat(thanhtoan) > 0)
					{
						if(this.loaixnId.equals("0"))  // Xóa nợ NCC
						{
							query = "Insert ERP_XOANONCC_HOADONNCC(xoanoncc_fk, hoadonncc_FK, TIENTHANHTOAN, TIENCHUNGTU, CONLAI, LOAICT) " +
									"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat.trim() + "', '" + conlai.trim() + "', "+hoadon.getLoaict()+")";
							System.out.println(query);
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_XOANONCC_HOADONNCC: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
						}   
						else    // Xóa nợ nhân viên
						{
							query = "Insert ERP_XOANONCC_DNTT(xoanoncc_fk, dntt_FK, TIENTHANHTOAN, TIENCHUNGTU, CONLAI, LOAICT) " +
									"values('" + this.id + "', '" + hoadon.getId() + "', '" + thanhtoan.trim() + "', '" + avat.trim() + "', '" + conlai.trim() + "', "+hoadon.getLoaict()+")";
							System.out.println(query);
							if(!db.update(query))
							{
								this.msg = "Khong the tao moi ERP_XOANONCC_DNTT: " + query;
								System.out.println(this.msg);
								db.getConnection().rollback();
								return false;
							}
						}
						
					}
				}
				
			}
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (SQLException e) 
		{
			try 
			{
				db.getConnection().rollback();
			}
			catch (SQLException e1) {}
			return false;
		}
		
		return true;
	}

	public boolean chotTTHD(String userId)
	{
		try 
		{
			String ngaysua = getDateTime();
			
			db.getConnection().setAutoCommit(false);
			
			String query = "update ERP_XOANONCC set TRANGTHAI = '1', NGUOISUA = '" + userId + "', NGAYSUA = '" + ngaysua + "' where PK_SEQ = '"  + this.id + "'";
			System.out.println("1.Cap nhat ERP_XOANONCC: " + query);
			
			if(!db.update(query))
			{
				this.msg = "Khong the chot ERP_XOANONCC: " + query;
				System.out.println(this.msg);
				db.getConnection().rollback();
				return false;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (SQLException e) 
		{
			try 
			{
				db.getConnection().rollback();
			}
			catch (SQLException e1) {}
			return false;
		}
		
		return true;
	}

	public void init()
	{
		NumberFormat formatter = new DecimalFormat("#,###,###"); 
		String query = "select pk_seq, ngaychungtu, ngayghiso, trangthai, ncc_fk, nhanvien_fk, ghichu, TONGTIENTRATRUOC, TONGTIENHOADON, TIENTE_FK, LOAIXOANO \n" +
					   "from ERP_XOANONCC \n"+
					   "where pk_seq = '" + this.id + "'";
		ResultSet rs = db.get(query);
		System.out.println(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.loaixnId = rs.getString("loaixoano");
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.ngayghiso = rs.getString("ngayghiso");
					this.nccId = rs.getString("ncc_fk") == null ? "": rs.getString("ncc_fk") ;
					this.nvId = rs.getString("nhanvien_fk") == null ? "": rs.getString("nhanvien_fk") ;
                    this.tienteId = rs.getString("TIENTE_FK");					
					
					this.noidungtt = rs.getString("ghichu");
					this.sotientt = formatter.format(rs.getDouble("TONGTIENTRATRUOC"));
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
		}		
		
		this.createRs();
	}

	public void initDisplay()
	{
		NumberFormat formatter = new DecimalFormat("#,###,###"); 
		String query = " select pk_seq, ngaychungtu, ngayghiso, ncc_fk,  ghichu, TONGTIENTRATRUOC, TONGTIENHOADON, TIENTE_FK  " +
					  " from ERP_XOANONCC where pk_seq = '" + this.id + "'";
		System.out.println(query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.ngaychungtu = rs.getString("ngaychungtu");
					this.ngayghiso = rs.getString("ngayghiso");
					this.tienteId = rs.getString("TIENTE_FK");
					this.nccId=rs.getString("ncc_fk");
					this.noidungtt = rs.getString("ghichu");
					this.sotientt = formatter.format(rs.getDouble("TONGTIENTRATRUOC"));
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
			}
		} 
		if(this.nccId == null)this.nccId=""; 
		
		this.nccRs= db.get(" select pk_seq, ma, ten from TraphacoERP.dbo.erp_nhacungcap where trangthai=1 and npp_fk = 1");

			query = "select b.pk_seq, 'Tam Ung' as kyhieu, b.pk_seq as sohoadon, b.ngayghinhan as ngayhoadon, a.sotienAVAT, a.SOTIENTT, a.ConLai " +
			"from ERP_XOANONCC_TAMUNG a inner join ERP_THANHTOANHOADON b on a.thanhtoanhoadon_fk = b.pk_seq where a.xoanoncc_fk = '" + this.id + "'";

		System.out.println("1.Khoi tao hoadon display: " + query);
		
		ResultSet rsHoadon = db.get(query);
		List<IHoadon> hdList = new ArrayList<IHoadon>();
		if(rsHoadon != null)
		{
			try 
			{
				IHoadon hd = null;
				while(rsHoadon.next())
				{
					String id = rsHoadon.getString("pk_seq");
					String kyhieu = rsHoadon.getString("kyhieu");
					String sohoadon = rsHoadon.getString("sohoadon");
					String ngayhd = rsHoadon.getString("ngayhoadon");
					String avat = formatter.format(rsHoadon.getDouble("sotienAVAT"));
					
					String dathanhtoan = "";
					if(rsHoadon.getDouble("SOTIENTT") > 0)
						dathanhtoan = formatter.format(rsHoadon.getDouble("SOTIENTT"));
					
					hd = new Hoadon(id, kyhieu, sohoadon, ngayhd, avat, dathanhtoan, "");
					hdList.add(hd);
				}
				rsHoadon.close();
			} 
			catch (Exception e)
			{
				System.out.println("115.Khoi tao Hoadon Display: " + query);
			}
		}
		this.hoadonList = hdList;
		

			query = 
			"select b.pk_seq, b.pk_seq as sochungtu, b.ngayghinhan as ngayhoadon, a.tienchungtu as sotienAVAT, a.tienthanhtoan as SOTIENTT, a.ConLai " +
			" from ERP_XOANONCC_HOADONNCC a inner join ERP_PARK b on a.hoadonncc_fk = b.pk_seq where a.Xoanoncc_fk = '" + this.id + "'";

		System.out.println("1.Khoi tao cttt display: " + query);
		
		rsHoadon = db.get(query);
		List<IHoadon> ctttList = new ArrayList<IHoadon>();
		if(ctttList != null)
		{
			try 
			{
				IHoadon hd = null;
				while(rsHoadon.next())
				{
					String id = rsHoadon.getString("pk_seq");
					String kyhieu = rsHoadon.getString("sochungtu");
					String sohoadon = rsHoadon.getString("sochungtu");
					String ngayhd = rsHoadon.getString("ngayhoadon");
					String avat = formatter.format(rsHoadon.getDouble("sotienAVAT"));
					
					String dathanhtoan = "";
					if(rsHoadon.getDouble("SOTIENTT") > 0)
						dathanhtoan = formatter.format(rsHoadon.getDouble("SOTIENTT"));
					
					hd = new Hoadon(id, kyhieu, sohoadon, ngayhd, avat, dathanhtoan, "");
					ctttList.add(hd);
				}
				rsHoadon.close();
			} 
			catch (Exception e)
			{
				System.out.println("115.Khoi tao Hoadon Display: " + query);
			}
		}
		System.out.println("__So cttt: " + ctttList.size());
		this.ctttList = ctttList;
	}
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppdangnhap=util.getIdNhapp(this.userId);
	}

	
	public void createRs() 
	{
		this.getNppInfo();
		
		NumberFormat formatter = new DecimalFormat("#,###,###");
		System.out.println(" select pk_seq, ma, ten from TraphacoERP.dbo.erp_nhacungcap where trangthai=1 AND NPP_FK = 1 ");
		this.nccRs= db.get(" select pk_seq, ma, ten from TraphacoERP.dbo.erp_nhacungcap where trangthai=1 AND NPP_FK = 1 ");
		
		this.nvRs = db.get(" select pk_seq, ma, ten from erp_nhanvien where trangthai=1 and NPP_FK = "+this.nppdangnhap);
		
		this.tienteRs = db.get("select pk_seq, ma + ', ' + ten as TEN from ERP_TIENTE ");
	
	 // Load chứng từ trả trước : Chi tạm ứng
		
	  if( this.ctttList.size() <= 0 && ( this.nccId.trim().length() > 0 || this.nvId.trim().length() > 0) ) // Load chứng từ trả trước: chi phí khác
	  {
		  String query= "";
		  if(this.nccId.trim().length() >0)
		  {
			  if(this.id.length() >0)
			  {
				// Cách tính ra số tiền còn lại để xóa nợ NCC như sau:
				// b.sotienTT: số tiền đã tạm ứng cho NCC
				// thanh_toan.tongthanhtoan: số tiền đã xóa tạm ứng NCC khác với chứng từ được chọn
				// a.sotientt: số tiền xóa tạm ứng của chứng từ Xóa tạm ứng NCC được chọn
				  
			   query += 	"SELECT 0 as loaict, b.pk_seq, N'Chi Tạm Ứng' as kyhieu, b.pk_seq as sohoadon, b.NGAYGHINHAN as ngayhoadon, \n" +
							"		b.sotienTT - isnull(thanh_toan.tongthanhtoan, '0') as sotienAVAT, a.sotientt as DaThanhToan \n" +														
							"FROM 	ERP_XOANONCC_TAMUNG a " +
							"INNER JOIN ERP_THANHTOANHOADON b on a.thanhtoanhoadon_fk = b.pk_seq \n" +
							"LEFT JOIN	\n" +
							"		(" +
							"		select XTU.thanhtoanhoadon_fk, sum(XTU.SOTIENTT) as tongthanhtoan \n" +
							"   	from ERP_XOANONCC_TAMUNG XTU inner join ERP_XOANONCC XN on XTU.XOANONCC_FK = XN.PK_SEQ \n" +
							"  	 	where XN.trangthai != '2' and XTU.XOANONCC_FK != '" + this.id + "' \n" +
							"   	and XTU.thanhtoanhoadon_fk in (select thanhtoanhoadon_fk from ERP_XOANONCC_TAMUNG where XOANONCC_FK = '" + this.id + "') \n" +
							"   	group by thanhtoanhoadon_fk" +
							"		)thanh_toan on a.thanhtoanhoadon_fk = thanh_toan.thanhtoanhoadon_fk \n" +
							" WHERE a.XOANONCC_FK = '" + this.id + "' \n";
			   
			// LẤY RA NHỮNG XÓA NỢ NCC BÚT TOÁN TỔNG HỢP
							
				query+=		" UNION ALL \n"+
				
							" SELECT 1 as loaict, b.BUTTOANTONGHOP_FK pk_seq, N'Bút toán tổng hợp' as kyhieu, c.pk_seq as sohoadon, c.ngaybuttoan ngayhoadon,  \n" +
						 	"        isnull(b.NO,0) - isnull(thanh_toan.tienthanhtoan, '0') as tienAvat, \n" +
						 	"        a.sotientt as DaThanhToan \n" +
							" FROM   ERP_XOANONCC_BTTH a INNER JOIN \n"+
							" ERP_BUTTOANTONGHOP_CHITIET b ON a.btth_fk = b.buttoantonghop_fk \n"+ 
							" INNER JOIN ERP_BUTTOANTONGHOP c ON b.buttoantonghop_fk = c.pk_seq \n"+
							" LEFT JOIN \n"+  
							" 		( \n"+  
							"			select 	a.btth_fk, sum(a.sotientt) as tienthanhtoan \n"+ 
							"			from 	ERP_XOANONCC_BTTH a \n"+ 
							"					inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq \n"+  
							"			where 	b.trangthai != 2 and a.XOANONCC_FK != '" + this.id + "' \n"+
							"					and a.btth_fk IN (	select btth_fk from ERP_XOANONCC_BTTH \n" +
							"   									where XOANONCC_FK = '" + this.id + "' ) \n" +
							"			group by a.btth_fk \n"+
							" 		)thanh_toan on a.btth_fk = thanh_toan.btth_fk \n"+ 
							" WHERE a.XOANONCC_FK = '" + this.id + "' and b.NO > 0 and b.NCC_FK = "+this.nccId+" \n" +
							
							"UNION ALL \n";
				
			  }
			  
			  // CHI TẠM ỨNG NCC
			  query += 		"(SELECT 0 as loaict, hoadon.pk_seq, N'Chi Tạm Ứng' as kyhieu, hoadon.sohoadon, hoadon.ngayhoadon,  " +
			  				"		 hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0') as sotienAVAT, \n"+
			  				"		 isnull(dathanhtoan.DATHANHTOAN, '') as DATHANHTOAN  \n"+
			  				" FROM \n" +
			  				" (	\n" +
			  				//TRƯỜNG HỢP UNC - TẠM ỨNG NCC
			  				"	SELECT tt.PK_SEQ, tt.PK_SEQ as sohoadon, tt.NGAYGHINHAN as ngayhoadon ,isnull(TTHD_HD.SOTIENTT,0)  as sotienAVAT \n"+
			  				"	FROM ERP_THANHTOANHOADON tt  \n"+
			  				"   INNER JOIN "+
			  				"       ( \n"+
			  				"         SELECT a.THANHTOANHD_FK , SUM(a.SOTIENTT) as SOTIENTT \n"+
			  				"         FROM ERP_THANHTOANHOADON_HOADON a inner join ERP_THANHTOANHOADON b on a.THANHTOANHD_FK = b.PK_SEQ  \n"+
			  				"         WHERE b.TRANGTHAI = 1 and a.LOAIHD = 1 AND b.npp_fk = "+this.nppdangnhap+
			  				"         GROUP BY a.THANHTOANHD_FK "+
			  				"        ) TTHD_HD ON tt.PK_SEQ = TTHD_HD.THANHTOANHD_FK "+
			  				"	WHERE TT.NPP_FK = "+this.nppdangnhap+" AND TT.TRANGTHAI=1 and tt.NCC_FK = '" +this.nccId+ "'  AND tt.TIENTE_FK = " + this.tienteId + " "+
			  				" 	and tt.pk_seq not in (SELECT thanhtoanhoadon_fk FROM ERP_XOANONCC_TAMUNG WHERE xoanoncc_fk = '" + (this.id == "" ? "0":this.id) + "')  "; 
			  							 
			  query += 		" ) HOADON \n"+
			  				" LEFT JOIN  \n"+
			  				" ( \n"+
			  				"   SELECT HOADON_FK, SUM(ISNULL(DATHANHTOAN, 0)) as DATHANHTOAN  \n"+
			  				"	FROM  " +
			  				"		( \n"+
			  				
			  				// TRƯỜNG HỢP XÓA TẠM ỨNG KHÁC VỚI XÓA TẠM ỨNG ĐANG ĐƯỢC CHỌN
			  				"	   	SELECT XNTU.THANHTOANHOADON_FK as HOADON_FK, sum(XNTU.SOTIENTT) as DATHANHTOAN   \n"+
			  				"	   	FROM ERP_XOANONCC_TAMUNG XNTU " +
			  				"		INNER JOIN ERP_XOANONCC XN on XNTU.XOANONCC_FK = XN.PK_SEQ  \n"+
			  				"	   	WHERE XN.TRANGTHAI != 2 and XN.NPP_FK = "+this.nppdangnhap+" \n ";
			  
			  if(this.id.trim().length() > 0)
			  	   query += " 		and XN.pk_seq != '" + this.id + "' \n";
			  
			  	   query +=	" 		GROUP BY THANHTOANHOADON_FK  \n"+
			  	   			
			  	   			"      	UNION ALL  \n"+
			  	   			
			  	   			// TRƯỜNG HỢP XÓA TẠM ỨNG CHO NCC
			  	   			"       SELECT XHD.HOADON_FK , sum(XHD.SOTIENTT) as DATHANHTOAN  \n"+
			  	   			"       FROM ERP_XOAKHTRATRUOC_HOADON XHD " +
			  	   			"		INNER JOIN ERP_XOAKHTRATRUOC XTT on XHD.xoakhtratruoc_fk = XTT.PK_SEQ \n"+
			  	   			"       WHERE  XTT.TRANGTHAI != 2 AND XTT.NCC_FK = '" +this.nccId+ "' and XTT.NPP_FK = "+this.nppdangnhap+" \n"+
			  	   			"       GROUP BY HOADON_FK  \n"+
			  	   			"	) HOADONDATT  \n"+
			  	   			"	group by HOADON_FK \n"+
			  	   			") dathanhtoan on hoadon.pk_seq = dathanhtoan.hoadon_fk \n"+
			  	   			" WHERE round(hoadon.sotienAVAT - isnull(dathanhtoan.DATHANHTOAN, '0'), 0) > 0 ) ";
			  	   
			  	// LẤY RA NHỮNG XÓA NỢ NCC BÚT TOÁN TỔNG HỢP
			  	   
			  	 query+=
							" UNION ALL \n"+
							
							" select 1 as loaict, chungtu.pk_seq pk_seq, N'Bút toán tổng hợp' as kyhieu, chungtu.pk_seq as sohoadon,chungtu.ngaychungtu ngayhoadon,  \n" +
							"       isnull(chungtu.sotientt,0) - (ISNULL(dathanhtoan.tienthanhtoan, 0)) as tienAvat, \n" +
							"       0 as DaThanhToan \n" + 
							" from \n"+ 
							" ( \n"+ 
							"	select	a.pk_seq, CAST(a.pk_seq as varchar(10)) as sochungtu, NGAYBUTTOAN ngaychungtu, sum(isnull(NO,0)) as SOTIENTTNT, sum(NO) sotientt, 1 as tigia \n"+  
							"	from	ERP_BUTTOANTONGHOP a INNER JOIN ERP_BUTTOANTONGHOP_CHITIET b on a.PK_SEQ = b.BUTTOANTONGHOP_FK \n"+   
							"	where	trangthai = '1' and a.NPP_FK = "+this.nppdangnhap+" \n"+			
							"			and b.ncc_fk = '"+this.nccId+"' and b.NO > 0 and TAIKHOANKT_FK IN (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '33111000' and NPP_FK = "+this.nppdangnhap+")";
			
			  	 query += 	"	and pk_seq not in (select btth_fk from ERP_XOANONCC_BTTH where xoanoncc_fk = '" + (this.id == "" ? "0": this.id)+ "' ) \n";							
				
			  	 query+=
							"	group by a.pk_seq, khachhang_fk, TAIKHOANKT_FK, ngaybuttoan \n"+
							" )chungtu \n"+ 
							" left join \n"+  
							" ( \n"+  
							"	select 	a.btth_fk, sum(a.sotientt) as tienthanhtoan \n"+ 
							"	from 	ERP_XOANONCC_BTTH a \n"+ 
							"			inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq \n"+  
							"	where 	b.trangthai != 2 and b.ncc_fk = "+this.nccId+" and b.NPP_FK = "+this.nppdangnhap+" \n";
				
			  	 query += 	" 	and b.pk_seq != '" + (this.id == "" ? "0": this.id) + "' \n";
				
			  	 query +=	"	group by a.btth_fk  \n"+
							" )dathanhtoan on chungtu.pk_seq = dathanhtoan.btth_fk  \n"+ 
							"where chungtu.sotientt - ISNULL(dathanhtoan.tienthanhtoan, 0) > 0 \n";
		    
		  }
		  else if(this.nvId.trim().length() > 0)
		  {
			  if(this.id.trim().length() > 0)
			  {
				  query +=  " SELECT 0 as loaict, b.pk_seq, N'Chi Tạm Ứng' as kyhieu, '' as sohoadon, b.NGAYGHINHAN as ngayhoadon, \n" +
							"        b.sotienTT - isnull(thanh_toan.tongthanhtoan, '0') as sotienAVAT, a.sotientt as DaThanhToan \n" +														
							" FROM ERP_XOANONCC_TAMUNG a inner join ERP_THANHTOANHOADON b on a.thanhtoanhoadon_fk = b.pk_seq \n" +
							"      left join	\n" +
							"      (" +
							"		select XTU.thanhtoanhoadon_fk, sum(XTU.SOTIENTT) as tongthanhtoan \n" +
							"   	from ERP_XOANONCC_TAMUNG XTU inner join ERP_XOANONCC XN on XTU.XOANONCC_FK = XN.PK_SEQ \n" +
							"   	where XN.trangthai != '2' and XTU.XOANONCC_FK != '" + this.id + "' and XN.NPP_FK = "+this.nppdangnhap+" \n" +
							"   			and XTU.thanhtoanhoadon_fk in (select thanhtoanhoadon_fk from ERP_XOANONCC_TAMUNG where XOANONCC_FK = '" + this.id + "') \n" +
							"   	group by thanhtoanhoadon_fk" +
							"		)thanh_toan on a.thanhtoanhoadon_fk = thanh_toan.thanhtoanhoadon_fk \n" +
							" WHERE a.XOANONCC_FK = '" + this.id + "' \n" +
							
							" UNION ALL "; 
				  
			// LẤY RA NHỮNG XÓA NỢ NCC BÚT TOÁN TỔNG HỢP
				
					query+=	"select 1 as loaict, b.BUTTOANTONGHOP_FK pk_seq, N'Bút toán tổng hợp' as kyhieu, cast(c.pk_seq as nvarchar(50)) as sohoadon, c.ngaybuttoan ngayhoadon,  \n" +
						 	"       isnull(b.NO,0) - isnull(thanh_toan.tienthanhtoan, '0') as tienAvat, \n" +
						 	"       a.sotientt as DaThanhToan \n" +
							"from  ERP_XOANONCC_BTTH a INNER JOIN \n"+
							"		ERP_BUTTOANTONGHOP_CHITIET b ON a.btth_fk = b.buttoantonghop_fk \n"+ 
							"		INNER JOIN ERP_BUTTOANTONGHOP c ON b.buttoantonghop_fk = c.pk_seq \n"+
							" 		left join \n"+  
							" 		( \n"+  
							"			select 	a.btth_fk, sum(a.sotientt) as tienthanhtoan \n"+ 
							"			from 	ERP_XOANONCC_BTTH a \n"+ 
							"					inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq \n"+  
							"			where 	b.trangthai != 2 and a.XOANONCC_FK != '" + this.id + "' \n"+
							"					and a.btth_fk IN (	select btth_fk from ERP_XOANONCC_BTTH \n" +
							"   									where XOANONCC_FK = '" + this.id + "' ) \n" +
							"			group by a.btth_fk \n"+
							" 		)thanh_toan on a.btth_fk = thanh_toan.btth_fk \n"+ 
							"where a.XOANONCC_FK = '" + this.id + "' and b.NO > 0 and b.NHANVIEN_FK = "+this.nvId+" \n" +
					
							"UNION ALL \n";
			  
			  }
			  
			  query += 	  " SELECT 0 as loaict, TTHD.PK_SEQ, N'Chi tạm ứng' as kyhieu, '' AS SOHOADON, TTHD.NGAYGHINHAN as NGAYHOADON, \n"+
						  "		  isnull(TTHD_HD.SOTIENTT,0) - ISNULL(DACANTRU.CANTRU,0) - ISNULL(DAXOANO.XOANO,0) - ISNULL(THUHOITU.DATHANHTOAN,0) SOTIENAVAT, 0 AS DATHANHTOAN \n"+ 
						  " FROM ERP_THANHTOANHOADON TTHD INNER JOIN ERP_NHANVIEN NV ON TTHD.NHANVIEN_FK = NV.PK_SEQ \n"+
						  " LEFT JOIN  \n"+
						  "     ( \n"+
						  "      select a.THANHTOANHD_FK, SUM(a.SOTIENTT)as SOTIENTT \n"+
						  "      from ERP_THANHTOANHOADON_HOADON a inner join ERP_THANHTOANHOADON b on a.THANHTOANHD_FK = b.PK_SEQ \n"+
						  "      where a.LOAIHD = 1 and b.TRANGTHAI = 1 and b.NHANVIEN_FK = "+ this.nvId +" and b.NPP_FK = "+this.nppdangnhap+"  \n"+
						  "      group by a.THANHTOANHD_FK \n"+
						  "     )TTHD_HD ON TTHD.PK_SEQ = TTHD_HD.THANHTOANHD_FK \n"+
						  " LEFT JOIN  \n"+
						  "     ( \n"+
						  "      select a.THANHTOANHOADON_FK, SUM(a.SOTIENCANTRU) as CANTRU \n"+
						  "      from ERP_DENGHITT_THANHTOANHOADON a inner join ERP_MUAHANG b on a.DENGHITT_FK = b.PK_SEQ \n"+
						  "      where b.TRANGTHAI not in (3,4) and b.NHANVIEN_FK = "+ this.nvId +" and b.NPP_FK = "+this.nppdangnhap+"  \n"+
						  "      group by a.THANHTOANHOADON_FK \n"+
						  "     )DACANTRU ON TTHD.PK_SEQ = DACANTRU.THANHTOANHOADON_FK \n"+
						  " LEFT JOIN \n"+
						  "     ( \n"+
						  "      select a.THANHTOANHOADON_FK, SUM(a.sotientt) as XOANO \n"+
						  "      from ERP_XOANONCC_TAMUNG a inner join ERP_XOANONCC b on a.xoanoncc_fk = b.PK_SEQ \n"+
						  "      where b.TRANGTHAI != 2 and b.NHANVIEN_FK = "+ this.nvId +" and b.NPP_FK = "+this.nppdangnhap+"  \n";
						  
						  if(this.id.trim().length() > 0)
						  {
							 query += " and b.PK_SEQ != "+ this.id +" "; 
						  }
				  
				  query +="      group by a.THANHTOANHOADON_FK \n"+
						  "     )DAXOANO ON TTHD.PK_SEQ = DAXOANO.THANHTOANHOADON_FK \n"+
						  " LEFT JOIN  \n"+
						  "     ( \n"+
						  "      select TTHD.HOADON_FK , sum(TTHD.SOTIENTT) as DATHANHTOAN   \n"+
						  "	     from ERP_THUTIEN_HOADON TTHD  inner join ERP_THUTIEN TT on TTHD.THUTIEN_FK = TT.PK_SEQ  \n"+
						  "      where TT.NOIDUNGTT_FK = 100001 AND  TT.TRANGTHAI != 2 and TT.NHANVIEN_FK = "+ this.nvId +" and TT.NPP_FK = "+this.nppdangnhap+" \n"+
						  "      group by TTHD.HOADON_FK   \n"+
						  "      ) THUHOITU ON TTHD.PK_SEQ = THUHOITU.HOADON_FK \n"+
						  " WHERE TTHD.NPP_FK = "+this.nppdangnhap+"  and TTHD.TRANGTHAI = 1  and  \n"+
						  "       ( isnull(TTHD_HD.SOTIENTT,0) - ISNULL(DACANTRU.CANTRU,0) - ISNULL(DAXOANO.XOANO,0) - ISNULL(THUHOITU.DATHANHTOAN,0) >0 )  \n";
				         if(this.id.trim().length() > 0)
				         {
				        	 query += " AND TTHD.PK_SEQ != ( select THANHTOANHOADON_FK from ERP_XOANONCC_TAMUNG where XOANONCC_FK =  "+ this.id +" ) ";
				         }
	         
	         // LẤY RA NHỮNG XÓA NỢ NHÂN VIÊN BÚT TOÁN TỔNG HỢP
		  	   
		  	 query+=	" UNION ALL \n"+
		  	 
						" SELECT 1 as loaict, chungtu.pk_seq pk_seq, N'Bút toán tổng hợp' as kyhieu, cast(chungtu.pk_seq as nvarchar(50)) as sohoadon,chungtu.ngaychungtu ngayhoadon,  \n" +
						"       isnull(chungtu.sotientt,0) - (ISNULL(dathanhtoan.tienthanhtoan, 0)) as tienAvat, \n" +
						"       0 as DaThanhToan \n" + 
						" FROM \n"+ 
						" ( \n"+ 
						"	SELECT	a.pk_seq, CAST(a.pk_seq as varchar(10)) as sochungtu, NGAYBUTTOAN ngaychungtu, sum(isnull(NO,0)) as SOTIENTTNT, sum(NO) sotientt, 1 as tigia \n"+  
						"	FROM	ERP_BUTTOANTONGHOP a INNER JOIN ERP_BUTTOANTONGHOP_CHITIET b on a.PK_SEQ = b.BUTTOANTONGHOP_FK \n"+   
						"	WHERE	trangthai = '1' and a.NPP_FK = "+this.nppdangnhap+" \n"+			
						"			and b.nhanvien_fk = '"+this.nvId+"' and b.NO > 0 and TAIKHOANKT_FK IN (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '14100000' and NPP_FK = "+this.nppdangnhap+")";
		
		  	 query += 	"	and pk_seq not in (select btth_fk from ERP_XOANONCC_BTTH where xoanoncc_fk = '" + (this.id == "" ? "0": this.id)+ "' ) \n";							
			
		  	 query+=
						"	group by a.pk_seq, khachhang_fk, TAIKHOANKT_FK, ngaybuttoan \n"+
						" )chungtu \n"+ 
						" LEFT JOIN \n"+  
						" ( \n"+  
						"	select 	a.btth_fk, sum(a.sotientt) as tienthanhtoan \n"+ 
						"	from 	ERP_XOANONCC_BTTH a \n"+ 
						"			inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq \n"+  
						"	where 	b.trangthai != 2 and b.nhanvien_fk = "+this.nvId+" and b.NPP_FK = "+this.nppdangnhap+" \n";
			
		  	 query += 	" and b.pk_seq != '" + (this.id == "" ? "0": this.id) + "' \n";
			
		  	 query +=
						"	group by a.btth_fk  \n"+
						" )dathanhtoan on chungtu.pk_seq = dathanhtoan.btth_fk  \n"+ 
						" WHERE chungtu.sotientt - ISNULL(dathanhtoan.tienthanhtoan, 0) > 0 \n";
		  }
		  
		  System.out.println("1.Khoi tao chứng từ trả trước: " + query);
			
			ResultSet rsHoadon = db.get(query);
			List<IHoadon> cttList = new ArrayList<IHoadon>();
			if(rsHoadon != null)
			{
				try 
				{
					IHoadon hd = null;
					while(rsHoadon.next())
					{
						String id = rsHoadon.getString("pk_seq");
						String kyhieu = rsHoadon.getString("kyhieu");
						String sohoadon = rsHoadon.getString("sohoadon");
						String ngayhd = rsHoadon.getString("ngayhoadon");
						String avat = formatter.format(rsHoadon.getDouble("sotienAVAT"));
						String loaict = rsHoadon.getString("loaict");
						
						String dathanhtoan = "";
						if(this.id.length() > 0)
						{
							if(rsHoadon.getDouble("DaThanhToan") > 0)
								dathanhtoan = formatter.format(rsHoadon.getDouble("DaThanhToan"));
						}
						
						hd = new Hoadon(id, kyhieu, sohoadon, ngayhd, avat, dathanhtoan, "");
						hd.setLoaict(loaict);
						
						cttList.add(hd);
					}
					rsHoadon.close();
				} 
				catch (SQLException e) {}
			}
			this.ctttList = cttList;
	  }
	  
	  // Load Hóa đơn : NCC(hóa đơn ncc) , NV(Đề nghị TT)
	  
	  if( (this.nccId.trim().length() > 0 || this.nvId.trim().length()> 0)  && this.hoadonList.size() <= 0) // Khởi tạo hóa đơn
	  { 
		  String query= "";
		  
		  if(this.nccId.trim().length() >0) 
		  {	
			  if(this.id.length() >0)
			  {
				 // LOAICT = 0 : HÓA ĐƠN NHÀ CUNG CẤP
				query += 	" SELECT c.pk_seq, c.kyhieu, c.sohoadon , c.ngayhoadon , \n" +
							" (c.sotienAVAT) - isnull(thanh_toan.tongthanhtoan, '0') as tienAvat, a. tienthanhtoan as SOTIENTT,  \n" +
							" isnull((SELECT distinct D.SOPO + ',' \n"+
				    		" 		  FROM ERP_HOADONNCC_DONMUAHANG A INNER JOIN ERP_MUAHANG D ON A.MUAHANG_FK = D.PK_SEQ AND HOADONNCC_FK = c.PK_SEQ \n"+					
							" 		  for xml path('') ),'') SOPO, 0 as loaict \n"+
							" FROM  ERP_XOANONCC_HOADONNCC a \n" + 
							" INNER JOIN ERP_HOADONNCC c on a.hoadonncc_fk = c.pk_seq and a.LOAICT = 0 \n" +
							" INNER JOIN ERP_PARK b on c.PARK_FK = b.pk_seq \n" +	
							" LEFT JOIN \n "+
							" ( \n" +
							"	SELECT a.HOADON_FK , sum(a.SOTIENTT) as sotientt   \n"+
							"   FROM ERP_THANHTOANHOADON_HOADON a \n" +
							"	INNER JOIN ERP_THANHTOANHOADON b on a.THANHTOANHD_FK = b.pk_seq    \n"+
							"   WHERE b.trangthai = 1 and b.ncc_fk ='" + this.nccId + "' and a.LOAIHD = 0   \n"+
							"   GROUP BY a.HOADON_FK  \n"+
							" ) HDTT on HDTT.HOADON_FK = a.hoadonncc_fk  \n"+
							" LEFT JOIN	\n" +
							"   (" +
							"		SELECT TTHD.HOADONNCC_FK, sum(TTHD.TienThanhToan) as tongthanhtoan \n" +
							"       FROM ERP_XOANONCC_HOADONNCC TTHD inner join ERP_XOANONCC TT on TTHD.XOANONCC_FK = TT.PK_SEQ \n" +
							"       WHERE TT.trangthai != '2' and TTHD.XOANONCC_FK != '" + this.id + "' \n" +
							"       and TTHD.hoadonncc_fk in (select hoadonncc_fk from ERP_XOANONCC_HOADONNCC where XOANONCC_FK = '" + this.id + "') and TTHD.LOAICT = 0 \n" +
							"       GROUP BY TTHD.HOADONNCC_FK" +
							"	)thanh_toan on a.HOADONNCC_FK = thanh_toan.HOADONNCC_FK \n" +
							" WHERE a.XOANONCC_FK = '" + this.id + "' AND a.LOAICT = 0 \n";
				
				// LOAICT = 1: BÚT TOÁN TỔNG HỢP
				
				query +=	" UNION ALL \n";
			
				query += 	" SELECT b.pk_seq, N'Bút toán tổng hợp' Kyhieu, cast(b.pk_seq as nvarchar(50)) sohoadon , b.NGAYBUTTOAN, \n" +
							" (PLSP.SOTIENVND) - isnull(thanh_toan.tongthanhtoan, '0') as tienAvat, a.tienthanhtoan as SOTIENTT, ' ' SOPO, 1 as loaict \n" +
							" FROM  ERP_XOANONCC_HOADONNCC a \n" +
							" INNER JOIN ERP_BUTTOANTONGHOP b on a.hoadonncc_fk = b.PK_SEQ \n"+ 
							" INNER JOIN (	SELECT BUTTOANTONGHOP_FK, NCC_FK,SUM(CO) AS SOTIENVND \n"+ 
							"               FROM   ERP_BUTTOANTONGHOP_CHITIET \n"+ 
							"               WHERE  TAIKHOANKT_FK IN (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '33111000' and NPP_FK = "+this.nppdangnhap+") \n"+
							"					   AND NCC_FK IS NOT NULL AND CO > 0 AND NCC_FK = '"+this.nccId+"'  \n"+
							"				GROUP BY BUTTOANTONGHOP_FK, NCC_FK ) AS PLSP  ON b.PK_SEQ= PLSP.BUTTOANTONGHOP_FK \n"+
							"  LEFT JOIN	\n" +
							"  ( \n" +
							"	  SELECT TTHD.HOADONNCC_FK, sum(TTHD.TienThanhToan) as tongthanhtoan \n" +
							"     FROM ERP_XOANONCC_HOADONNCC TTHD inner join ERP_XOANONCC TT on TTHD.XOANONCC_FK = TT.PK_SEQ \n" +
							"     WHERE TT.trangthai != '2' and TTHD.XOANONCC_FK != '" + this.id + "' \n" +
							"     and TTHD.hoadonncc_fk in (select hoadonncc_fk from ERP_XOANONCC_HOADONNCC where XOANONCC_FK = '" + this.id + "') and TTHD.LOAICT = 1 \n" +
							"     GROUP BY TTHD.HOADONNCC_FK \n" +
							"	) thanh_toan on a.HOADONNCC_FK = thanh_toan.HOADONNCC_FK \n" +
							" WHERE a.XOANONCC_FK = '" + this.id + "' AND a.LOAICT = 1 \n";			
				
				query += " UNION ALL \n";
			  }
			  
			  // HÓA ĐƠN NCC
			  query += 		" SELECT chungtu.pk_seq, chungtu.kyhieu, chungtu.sohoadon, chungtu.ngayhoadon, chungtu.tienAvat - ISNULL(dathanhtoan.tienthanhtoan, 0) as tienAvat, \n " +
				    		"		0 as SOTIENTT, isnull(chungtu.SOPO,'') SOPO, 0 as loaict \n " +
				    		" FROM \n" +
				    		" 	( \n" +
				    		"	SELECT b.PK_SEQ, b.kyhieu, b.sohoadon, b.PK_SEQ as sochungtu, b.ngayhoadon, b.sotienAVAT - isnull(HDTT.sotientt,0) as tienAvat,   \n"+
				    		"			(SELECT distinct C.SOPO + ',' \n"+
				    		"			FROM ERP_HOADONNCC_DONMUAHANG A INNER JOIN ERP_MUAHANG C ON A.MUAHANG_FK = C.PK_SEQ AND HOADONNCC_FK = b.PK_SEQ \n"+					
							"			for xml path('') ) SOPO \n"+
				    		"	FROM ERP_PARK a \n" +
				    		"	INNER JOIN ERP_HOADONNCC b on a.PK_SEQ=b.park_fk  \n"+
				    		"   LEFT JOIN \n"+
				    		"   (" +
				    		"		SELECT a.HOADON_FK , sum(a.SOTIENTT) as sotientt  \n"+
				    		"       FROM ERP_THANHTOANHOADON_HOADON a " +
				    		"		INNER JOIN ERP_THANHTOANHOADON b on a.THANHTOANHD_FK = b.pk_seq   \n"+
				    		"       WHERE b.trangthai = 1 and b.ncc_fk ='"+this.nccId +"' and a.LOAIHD = 0   \n"+
				    		"       GROUP by a.HOADON_FK  \n" +
				    		"	) HDTT on HDTT.HOADON_FK = a.pk_seq \n"+
				    		" WHERE a.TRANGTHAI=2 and a.ncc_fk ='"+this.nccId +"'  AND a.TIENTE_FK = " + this.tienteId + "  and b.NPP_FK = "+this.nppdangnhap+"  \n " ;
			  
			  if(this.id.length() > 0){
					query += " and b.pk_seq not in (select hoadonncc_fk from ERP_XOANONCC_HOADONNCC where xoanoncc_fk = '" + this.id + "' and loaict = 0 ) \n";
			  }
			  query +=     	
						    "  )chungtu \n" +
							"LEFT JOIN  \n" +
							" (  \n" +
							" 	SELECT a.hoadonncc_fk, sum(a.tienthanhtoan) as tienthanhtoan \n" +
							" 	FROM ERP_XOANONCC_HOADONNCC a inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq  \n" +
							" 	WHERE b.ncc_fk ='"+this.nccId +"' and b.NPP_FK = "+this.nppdangnhap+" AND b.TRANGTHAI NOT IN (2) and a.loaict = 0  \n";
							if(this.id.trim().length() > 0)
								query += " and b.pk_seq != '" + this.id + "' \n";
				
			  query += 		"	GROUP BY a.hoadonncc_fk \n" +
							" )dathanhtoan on chungtu.pk_seq = dathanhtoan.hoadonncc_fk \n" +
							"WHERE round(chungtu.tienAvat - ISNULL(dathanhtoan.tienthanhtoan, 0), 0) > 0 ";			
		  
			// ERP_BUTTOANTONGHOP
			  
			  query += 
							" UNION ALL \n"+
						  
						    " SELECT chungtu.pk_seq, chungtu.kyhieu, chungtu.sohoadon, chungtu.ngayhoadon, chungtu.tienAvat - ISNULL(dathanhtoan.tienthanhtoan, 0) as tienAvat,     \n " +
				    	    "		0 as SOTIENTT, isnull(chungtu.SOPO,'') SOPO, 1 as loaict \n " +
				    		" FROM \n" +
				    		" ( \n" +
				    		"	SELECT a.PK_SEQ, N'Bút toán tổng hợp' KYHIEU,  cast(a.PK_SEQ as nvarchar(50) ) sohoadon, a.PK_SEQ as sochungtu, a.NGAYBUTTOAN ngayhoadon, isnull(b.CO,0) - isnull(HDTT.sotientt,0) as tienAvat, ' ' SOPO   \n"+
				    		"	FROM ERP_BUTTOANTONGHOP a \n" +
				    		"	INNER JOIN ERP_BUTTOANTONGHOP_CHITIET b on a.PK_SEQ = b.BUTTOANTONGHOP_FK  \n"+
				    		"   LEFT JOIN \n"+
				    		"   (" +
				    		"		SELECT a.HOADONNCC_FK HOADON_FK , sum(a.TIENTHANHTOAN) as sotientt  \n"+
				    		"       FROM ERP_XOANONCC_HOADONNCC a \n" +
				    		"		INNER JOIN ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq   \n"+
				    		"       WHERE b.trangthai = 1 and b.ncc_fk ='"+this.nccId +"' and a.LOAICT = 1    \n"+
				    		"       GROUP BY a.HOADONNCC_FK  \n" +
				    		"	) HDTT on HDTT.HOADON_FK = a.pk_seq \n"+
				    		"   WHERE a.TRANGTHAI = 1 and b.ncc_fk ='"+this.nccId +"' and a.NPP_FK = "+this.nppdangnhap+" AND isnull(b.CO,0) > 0  AND \n "+
				    		"	b.TAIKHOANKT_FK IN (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '33111000' and NPP_FK = "+this.nppdangnhap+")  ";
			  
			  if(this.id.length() > 0){
					query += 		" and a.PK_SEQ not in (select hoadonncc_fk from ERP_XOANONCC_HOADONNCC where xoanoncc_fk = '" + this.id + "' and loaict = 1 ) \n";
			  }
			  
			  query +=     	" )chungtu \n" +
							" LEFT JOIN  \n" +
							" (  \n" +
							" 	SELECT a.hoadonncc_fk, sum(a.tienthanhtoan) as tienthanhtoan \n" +
							" 	FROM ERP_XOANONCC_HOADONNCC a inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq  \n" +
							" 	WHERE b.ncc_fk ='"+this.nccId +"' and b.NPP_FK = "+this.nppdangnhap+" AND b.TRANGTHAI NOT IN (2) and a.loaict = 1  \n";
							if(this.id.trim().length() > 0)
								query += " and b.pk_seq != '" + this.id + "' \n";
				
			  query += 		"	GROUP BY a.hoadonncc_fk \n" +
							" )dathanhtoan on chungtu.pk_seq = dathanhtoan.hoadonncc_fk \n" +
							"  WHERE round(chungtu.tienAvat - ISNULL(dathanhtoan.tienthanhtoan, 0), 0) > 0 ";
					  			  	
		  }
		  else if(this.nvId.trim().length() > 0)
		  {
			  if(this.id.trim().length() > 0)
			  {
				  // ĐỀ NGHỊ THANH TOÁN
				  query =	  "SELECT DNTT.PK_SEQ, N'Đề nghị thanh toán' as KYHIEU, CAST(DNTT.SOPO as nvarchar(50)) as SOHOADON, DNTT.NGAYMUA as NGAYHOADON, \n"+
							  "       XN.TIENCHUNGTU AS TIENAVAT, XN.TIENTHANHTOAN AS SOTIENTT, '' SOPO, 0 as loaict \n"+
							  "FROM ERP_MUAHANG DNTT \n"+
							  "     INNER JOIN ERP_XOANONCC_DNTT XN ON DNTT.PK_SEQ = XN.DNTT_FK \n"+
							  "WHERE XN.xoanoncc_fk = "+ this.id  +" AND DNTT.NHANVIEN_FK = "+ this.nvId +" AND XN.LOAICT = 0 \n"+
							  
							  "UNION ALL \n";
				  
				// LOAICT = 1: BÚT TOÁN TỔNG HỢP
							
				   query += 	" SELECT b.pk_seq, N'Bút toán tổng hợp' Kyhieu, cast(b.pk_seq as nvarchar(50)) sohoadon , b.NGAYBUTTOAN, \n" +
								" (PLSP.SOTIENVND) - isnull(thanh_toan.tongthanhtoan, '0') as tienAvat, a.tienthanhtoan as SOTIENTT, ' ' SOPO, 1 as loaict \n" +
								" FROM  ERP_XOANONCC_DNTT a \n" +
								"      INNER JOIN ERP_BUTTOANTONGHOP b on a.dntt_fk = b.PK_SEQ \n"+ 
								"	   INNER JOIN (	SELECT BUTTOANTONGHOP_FK, NHANVIEN_FK,SUM(CO) AS SOTIENVND \n"+ 
								"                   FROM   ERP_BUTTOANTONGHOP_CHITIET \n"+ 
								"                   WHERE  TAIKHOANKT_FK IN (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '14100000' and NPP_FK = "+this.nppdangnhap+") \n"+
								"						   AND NHANVIEN_FK IS NOT NULL AND CO > 0 AND NHANVIEN_FK = '"+this.nvId+"'  \n"+
								"					GROUP BY BUTTOANTONGHOP_FK, NHANVIEN_FK ) AS PLSP  ON b.PK_SEQ= PLSP.BUTTOANTONGHOP_FK "+
								"   	LEFT JOIN	\n" +
								"   	(" +
								"			SELECT TTHD.DNTT_FK HOADONNCC_FK, sum(TTHD.TienThanhToan) as tongthanhtoan \n" +
								"       	FROM ERP_XOANONCC_DNTT TTHD inner join ERP_XOANONCC TT on TTHD.XOANONCC_FK = TT.PK_SEQ \n" +
								"      	 	WHERE TT.trangthai != '2' and TTHD.XOANONCC_FK != '" + this.id + "' \n" +
								"       	and TTHD.DNTT_FK in (select hoadonncc_fk from ERP_XOANONCC_HOADONNCC where XOANONCC_FK = '" + this.id + "') and TTHD.LOAICT = 1 \n" +
								"       	GROUP BY TTHD.DNTT_FK" +
								"		) thanh_toan on a.DNTT_FK = thanh_toan.HOADONNCC_FK \n" +
								" WHERE a.XOANONCC_FK = '" + this.id + "' AND a.LOAICT = 1 \n";			
				
				   query += 	" UNION ALL \n";
					
			  }
			
			  // ĐỀ NGHỊ THANH TOÁN
				  query +=	  "SELECT DNTT.PK_SEQ, N'Đề nghị thanh toán' as KYHIEU, CAST(DNTT.SOPO as nvarchar(50)) as SOHOADON, DNTT.NGAYMUA as NGAYHOADON, \n"+
							  "       DNTT.TONGTIENCONLAI - ISNULL(DATHANHTOAN.DATHANHTOAN,0) - ISNULL(DAXOANO.TIENTHANHTOAN,0) AS TIENAVAT, 0 AS SOTIENTT, '' SOPO, 0 as loaict \n"+
							  "FROM ERP_MUAHANG DNTT \n"+
							  "     LEFT JOIN  \n"+
							  "     (SELECT b.HOADON_FK, SUM(b.SOTIENTT) as DATHANHTOAN \n"+
							  "      FROM  ERP_THANHTOANHOADON a inner join ERP_THANHTOANHOADON_HOADON b on a.PK_SEQ = b.THANHTOANHD_FK \n"+
							  "      WHERE a.TRANGTHAI != 2 and b.LOAIHD = 6 and a.NPP_FK = "+this.nppdangnhap+"  \n"+
							  "      GROUP BY b.HOADON_FK \n"+
							  "     )DATHANHTOAN ON DNTT.PK_SEQ = DATHANHTOAN.HOADON_FK \n"+
							  "    LEFT JOIN  " +
					    	  "     (  " +
					    	  " 	SELECT a.dntt_fk, sum(a.tienthanhtoan) as tienthanhtoan \n" +
					    	  " 	FROM ERP_XOANONCC_DNTT a inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq  \n" +
					    	  " 	WHERE b.nhanvien_fk ='"+this.nvId +"' and b.NPP_FK = "+this.nppdangnhap+" \n";
			  
				if(this.id.trim().length() > 0)
					query += "         and b.pk_seq != '" + this.id + "' \n";
	
					query +=  "      GROUP BY a.dntt_fk \n" +
					    	  "     )daxoano on DNTT.pk_seq = daxoano.dntt_fk \n" +
							  "WHERE DNTT.TRANGTHAI = 1 AND DNTT.LOAIHANGHOA_FK = 2 and TYPE = '1' and DNTT.NHANVIEN_FK = "+ this.nvId +" and DNTT.NPP_FK = "+this.nppdangnhap+"  \n"+
							  " AND  DNTT.TONGTIENCONLAI - ISNULL(DATHANHTOAN.DATHANHTOAN,0) - ISNULL(DAXOANO.TIENTHANHTOAN,0) >0 ";
   
			   if(this.id.trim().length() > 0)
					query += " and DNTT.pk_seq != ( select DNTT_FK from ERP_XOANONCC_DNTT where XOANONCC_FK = '" + this.id + "')  \n";
   
	   
	// ERP_BUTTOANTONGHOP
		  
		  query += 		" UNION ALL \n"+
						  
						" select chungtu.pk_seq, chungtu.kyhieu, chungtu.sohoadon, chungtu.ngayhoadon, chungtu.tienAvat - ISNULL(dathanhtoan.tienthanhtoan, 0) as tienAvat,     \n " +
				 	    "		0 as SOTIENTT, isnull(chungtu.SOPO,'') SOPO, 1 as loaict \n " +
				 		" from \n" +
				 		" ( \n" +
				 		"	select a.PK_SEQ, N'Bút toán tổng hợp' KYHIEU,  cast(a.PK_SEQ as nvarchar(50) ) sohoadon, a.PK_SEQ as sochungtu, a.NGAYBUTTOAN ngayhoadon, isnull(b.CO,0) - isnull(HDTT.sotientt,0) as tienAvat, ' ' SOPO   \n"+
				 		"	from ERP_BUTTOANTONGHOP a \n" +
				 		"	inner join ERP_BUTTOANTONGHOP_CHITIET b on a.PK_SEQ = b.BUTTOANTONGHOP_FK  \n"+
				 		"   left join \n"+
				 		"   (" +
				 		"		select a.DNTT_FK HOADON_FK , sum(a.TIENTHANHTOAN) as sotientt  \n"+
				 		"       from ERP_XOANONCC_DNTT a \n" +
				 		"		inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq   \n"+
				 		"       where b.trangthai = 1 and b.nhanvien_fk ='"+this.nvId +"' and a.LOAICT = 1    \n"+
				 		"       group by a.DNTT_FK  \n" +
				 		"	) HDTT on HDTT.HOADON_FK = a.pk_seq \n"+
				 		"   where a.TRANGTHAI = 1 and b.nhanvien_fk ='"+this.nvId +"' and a.NPP_FK = "+this.nppdangnhap+" AND isnull(b.CO,0) > 0  AND \n "+
				 		"	b.TAIKHOANKT_FK IN (SELECT PK_SEQ FROM ERP_TAIKHOANKT WHERE SOHIEUTAIKHOAN = '14100000' and NPP_FK = "+this.nppdangnhap+")  ";
		  
					  if(this.id.length() > 0){
							query += 		" and a.PK_SEQ not in (select dntt_fk from ERP_XOANONCC_DNTT where xoanoncc_fk = '" + this.id + "' and loaict = 1 ) \n";
					  }
		  
		  query +=   	" )chungtu \n" +
						" left join  \n" +
						" (  \n" +
						" 	select a.dntt_fk hoadonncc_fk, sum(a.tienthanhtoan) as tienthanhtoan \n" +
						" 	from ERP_XOANONCC_DNTT a inner join ERP_XOANONCC b on a.xoanoncc_fk = b.pk_seq  \n" +
						" 	where b.nhanvien_fk ='"+this.nvId +"' and b.NPP_FK = "+this.nppdangnhap+" AND b.TRANGTHAI NOT IN (2) and a.loaict = 1  \n";
						if(this.id.trim().length() > 0)
							query += " and b.pk_seq != '" + this.id + "' \n";
			
		  query +=		"  group by a.dntt_fk \n" +
						" )dathanhtoan on chungtu.pk_seq = dathanhtoan.hoadonncc_fk \n" +
						" where round(chungtu.tienAvat - ISNULL(dathanhtoan.tienthanhtoan, 0), 0) > 0 ";
		  }
		  
	     System.out.println("1.Query khoi tao hoa don : " + query);
			
			ResultSet rsHoadon = db.get(query);
			List<IHoadon> hdList = new ArrayList<IHoadon>();
			if(rsHoadon != null)
			{
				try 
				{
					IHoadon hd = null;
					while(rsHoadon.next())
					{
						String id = rsHoadon.getString("pk_seq");
						String kyhieu = rsHoadon.getString("kyhieu");
						String sohoadon = rsHoadon.getString("sohoadon");
						String ngayhd = rsHoadon.getString("ngayhoadon");
						String avat = formatter.format(rsHoadon.getDouble("tienAvat"));
						String sopo = rsHoadon.getString("sopo");
						String loaict = rsHoadon.getString("loaict");
						
						String dathanhtoan = "";
						if(this.id.length() > 0)
						{
							if(rsHoadon.getDouble("SOTIENTT") > 0)
								dathanhtoan = formatter.format(rsHoadon.getDouble("SOTIENTT"));
						}
						
						hd = new Hoadon(id, kyhieu, sohoadon, ngayhd, avat, dathanhtoan, "");
						hd.setHopdong(sopo);
						hd.setLoaict(loaict);
						hdList.add(hd);
					}
					rsHoadon.close();
				} 
				catch (Exception e) 
				{
					System.out.println("115.Exception: " + e.getMessage());
				}
			}
			this.hoadonList = hdList;
	  }
	}

	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public void DBclose() 
	{
		
	}

	
	public String getNgayghiso() 
	{
		return this.ngayghiso;
	}

	public void setNgayghiso(String ngayghiso) 
	{
		this.ngayghiso = ngayghiso;
	}

	public String getNoidungId()
	{
		return this.ndId;
	}

	public void setNoidungId(String ndId) 
	{
		this.ndId = ndId;
	}

	public ResultSet getNoidungRs()
	{
		return this.ndRs;
	}

	public void setNoidungRs(ResultSet ndRs) 
	{
		this.ndRs = ndRs;	
	}

	
	public String getCttratruocId() 
	{
		return this.ctttId;
	}

	public void setCttratruocId(String ctttId) 
	{
		this.ctttId = ctttId;
	}

	public ResultSet getCttratruocRs() 
	{
		return this.ctttRs;
	}

	public void setCttratruocRs(ResultSet ctttRs)
	{
		this.ctttRs = ctttRs;
	}

	public String getCtttIds() 
	{
		return this.ctttIds;
	}

	public void setCtttIds(String ctttIds) 
	{
		this.ctttIds = ctttIds;
	}

	public List<IHoadon> getCtttList() 
	{
		return this.ctttList;
	}

	public void setCtttList(List<IHoadon> hoadonRs) 
	{
		this.ctttList = hoadonRs;
	}

	
	public String getCongtyId() {
	
		return this.congtyId;
	}

	
	public void setCongtyId(String congtyId) {
	
		this.congtyId = congtyId;
	}

	
	public String getnppdangnhap() {
	
		return this.nppdangnhap;
	}

	
	public void setnppdangnhap(String nppdangnhap) {
	
		this.nppdangnhap = nppdangnhap;
	}

	

}
