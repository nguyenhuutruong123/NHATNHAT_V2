package geso.dms.center.beans.ctkhuyenmai.imp;

import geso.dms.center.beans.ctkhuyenmai.*;
import geso.dms.center.beans.dieukienkhuyenmai.ISanpham;
import geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.db.sql.Idbutils;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

public class Ctkhuyenmai implements ICtkhuyenmai
{
	String phanbotdv;
	String loaipb;
	String userId;
	String id;
	String scheme;
	String diengiai;
	String tungay;
	String denngay;
	String type;
	String ngansach;
	String dasudung;
	String nguoitao;
	String nguoisua;
	String ngaytao;
	String ngaysua;
	String msg;
	ResultSet Dsnpp;
	ResultSet DsnppIds;
	String[] npp;
	List<IDieukienkm> dkkmList = new ArrayList<IDieukienkm>();
	List<ITrakm> trakmList = new ArrayList<ITrakm>();
	ResultSet trakmRs;
	String trakmId;
	
	//Search trakhuyenmai
	String tkm_diengiai;
	String tkm_tungay;
	String tkm_denngay;
	String ngayds;
	String ngayktds;
	//Search dieukienkhuyenmai
	String dkkm_diengiai;
	String dkkm_tungay;
	String dkkm_denngay;
	String khoId;
	ResultSet khoIds;
	
	String loaicnIds;
	ResultSet loaicnRs;
	
	dbutils db;
	ResultSet Nhomkhnpp;
	String NhomkhnppId;
	String vungId;
	String khuvucId;
	ResultSet vungs;
    ResultSet khuvucs;
    String load;
    
    ResultSet kenhRs;
	String kbhIds;
	
	ResultSet loaiKhRs;
	String loaiKhIds;
	
	String phamvi;
	
	ResultSet nhomspRs;
	
	//Loai ngan sach default 1 -- Ap phan bo
	String loains;
	String active;
	
	boolean dacotrongdh;
	
	String loaikhuyenmai;
	String tylevoiPrimary;
	
	String apDungDHLe;
	String phanbotheoDH;
	
	//
	ResultSet _hangCuaHangs;
	String _hangCuaHangSelectedIds;

	//
	String _soXuatToiDa;	
	String cttbId;
	ResultSet cttbRs;
	
	String ctkmId;
	ResultSet ctkmRs;
	
	String loaiapdung;
	String xuatHDCoVAT;
	String inchung ;
	String sanphamdautien = "0";
	String ap_dung_npp = "0";
	
	
	String khonhan_fk = "";
	
	String ngunghd = "0";
	
	
	String khong_tich_luy = "";
	
	public String getKhong_tich_luy() {
		return khong_tich_luy;
	}
	public void setKhong_tich_luy(String khong_tich_luy) {
		this.khong_tich_luy = khong_tich_luy;
	}
	
	public String getNgunghd() {
		return ngunghd;
	}

	public void setNgunghd(String ngunghd) {
		this.ngunghd = ngunghd;
	}
	
	public String getKhonhan_fk() {
		return khonhan_fk;
	}
	public void setKhonhan_fk(String khonhan_fk) {
		this.khonhan_fk = khonhan_fk;
	}
	
	public Ctkhuyenmai(String[] param)
	{
		this.id = param[0];
		this.scheme = param[1];
		this.diengiai = param[2];
		this.tungay = param[3];
		this.denngay = param[4];
		this.type = param[5];
		this.ngansach = param[6];
		this.dasudung = param[7];
		this.ngaytao = param[8];
		this.nguoitao = param[9];
		this.ngaysua = param[10];
		this.nguoisua = param[11];		
		this.nguoisua = param[11];		
		this.msg = "";
			
		this.trakmId = "";
		this.tkm_diengiai = "";
		this.tkm_tungay = "";
		this.tkm_denngay = "";
		
		this.loaicnIds = "";
		
		this.dkkm_diengiai = "";
		this.dkkm_tungay = "";
		this.dkkm_denngay = "";
		this.khoId= "";
		this.vungId ="";
		this.khuvucId ="";
		this.ngayds ="";
		this.ngayktds="";
		this.kbhIds = "";
		this.loaiKhIds = "";
		this.load = "0";
		this.loains = "1";
		this.active = "0";
		this.dacotrongdh = false;
		this.loaikhuyenmai = "0";  //0 sencond, 1 Primary, 2 Ap dung cho ca 2
		this.tylevoiPrimary = "0";
		this.phamvi = "0";
		
		this.apDungDHLe = "0";
		this.phanbotheoDH = "0";
		this._soXuatToiDa="0";
		this.loaipb = "0"; // 0 : PHAN BO THEO TIEN. 1 : PHAN BO THEO SUAT
		this.phanbotdv = "0";
		this._hangCuaHangSelectedIds="";
		this.cttbId = "";
		this.ctkmId = "";
		this.loaiapdung = "";
		this.xuatHDCoVAT = "1";
		this.inchung="0";
		db = new dbutils();
	}
	
	
	
	public Ctkhuyenmai(String id)
	{
		this.id = id;
		this.scheme = "";
		this.diengiai = "";
		this.tungay = "";
		this.denngay = "";
		this.type = "";
		this.ngansach = "";
		this.dasudung = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.msg = "";
		this.NhomkhnppId ="";
		this.trakmId = "";
		this.tkm_diengiai = "";
		this.tkm_tungay = "";
		this.tkm_denngay = "";
		
		this.loaicnIds = "";
		
		this.dkkm_diengiai = "";
		this.dkkm_tungay = "";
		this.dkkm_denngay = "";
		this.khoId= "";
		this.vungId ="";
		this.khuvucId ="";
		this.load = "0";
		this.ngayds ="";
		this.ngayktds="";
		this.kbhIds = "";
		this.loaiKhIds = "";
		this.loains = "1";
		this.active = "0";
		this.dacotrongdh = false;
		this.loaikhuyenmai = "0";  //0 sencond, 1 Primary, 2 Ap dung cho ca 2
		this.tylevoiPrimary = "0";
		this.apDungDHLe = "0";
		this.phanbotheoDH = "0";
		this.phamvi = "0";
		this._soXuatToiDa="0";
		this.loaipb = "0"; // 0 : PHAN BO THEO TIEN. 1 : PHAN BO THEO SUAT
		this.phanbotdv = "0";
		this._hangCuaHangSelectedIds="";
		this.cttbId = "";
		this.ctkmId = "";
		this.loaiapdung = "";
		this.xuatHDCoVAT = "1";
		this.inchung="0";
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
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getScheme()
	{
		return this.scheme;
	}
	
	public void setScheme(String scheme)
	{
		this.scheme = scheme;
	}
	
	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}
	
	public String getDiengiai()
	{
		return diengiai;
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
	
	public String getType()
	{
		return this.type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getNgansach()
	{
		return this.ngansach;
	}
	
	public void setNgansach(String ngansach)
	{
		this.ngansach = ngansach;
	}
	
	public String getDasudung()
	{
		return this.dasudung;
	}
	
	public void setDasudung(String dasudung)
	{
		this.dasudung = dasudung;
	}
	
	public List<IDieukienkm> getDkkmList() 
	{		
		return this.dkkmList;
	}
	
	public void setDkkmList(List<IDieukienkm> dkkmlist) 
	{
		this.dkkmList = dkkmlist;		
	}

	public ResultSet getTrakmRs()
	{		
		return this.trakmRs;
	}
	
	public void setTrakmRs(ResultSet trakmRs) 
	{
		this.trakmRs = trakmRs;	
	}
	
	public String getTrakmId()
	{		
		return this.trakmId;
	}
	
	public void setTrakmId(String trakmId) 
	{
		this.trakmId = trakmId;		
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
		
	public boolean CreateCtkm() 
	{  
		if(!checkDieuKienThoaLuuCTKM())
		{
			this.msg = "Đối với những scheme giới hạn ngân sách và phân bổ theo số suất thì không thể khai báo trả khuyến mãi chiết khấu. Vui lòng kiểm tra lại.";
			return false;
		}
		
		if(!kiemtra_scheme( db,this.scheme ,""))
		{		
			this.msg = "Scheme khuyến mại đã tồn tại, vui lòng nhập lại";
			return false;
		}
		if(this.xuatHDCoVAT.equals("1") && this.inchung.equals("1"))
		{
			this.msg = "In chung thì không tick Xuất hóa đơn có VAT ";
			return false;
		}
		
		/*if(this.khoId.equals("100001"))
		{
			this.loains = "0";
			this.ngansach = "0";
		}*/
		

		this.ngaytao = getDateTime();
		this.nguoitao = this.userId;
		
		try 
		{
			String query;
			this.db.getConnection().setAutoCommit(false);
			if(this.NhomkhnppId.length() <= 0 )
			{
				this.NhomkhnppId = "null";
			}
			
			//Chan truong hop: neu PhanBoTheoDH=0 va _soXuatToiDa!=0
			if(this.phanbotheoDH.trim().equals("0") && !this._soXuatToiDa.trim().equals("0"))
			{
				this.setPhanbotheoDH("1");
			}
			//Chan truong hop: neu PhanBoTheoDH=1 va _soXuatToiDa==0
			if(this.phanbotheoDH.trim().equals("1") && this._soXuatToiDa.trim().equals("0"))
			{
				this.setPhanbotheoDH("0");
			}
				
			String _cttbId = "NULL";
			if(this.cttbId.trim().length() > 0)
				_cttbId = this.cttbId;
			
			String _ctkmId = "NULL";
			if(this.ctkmId.trim().length() > 0)
				_ctkmId = this.ctkmId;
			
			String nppTao_Fk = this.phanloai.equals("2") ? "NULL" : this.nppTaoId;
			
			query = "\n Insert into Ctkhuyenmai(PHANBOTHEOTDV, LOAIPHANBO, nppTao_Fk,khong_tich_luy,khonhan_fk,ap_dung_npp,scheme, diengiai, tungay, denngay, loaict, ngansach, ngaytao, nguoitao, ngaysua, nguoisua,kho_fk,nhomkhnpp_fk, loaingansach, tilevoiprimary, ApDUNGCHODHLE,PHANBOTHEODONHANG,SoXuatToiDa, cttb_fk, ctkm_fk, xuatHD_coVAT,inchung, sanphamdautien) " +
					"\n values('"+ this.phanbotdv +"','"+this.loaipb +"', "+nppTao_Fk+","+this.khong_tich_luy+","+this.khonhan_fk+","+this.ap_dung_npp+",'" + this.scheme.trim() + "', N'" + this.diengiai + "', '" + this.tungay + "' , '" + this.denngay + "' , '" + this.type + "', '" + this.ngansach + "', " +
							"'" + this.ngaytao + "', '" + this.nguoitao + "', '" + this.ngaytao + "', '" + this.nguoitao + "','"+ this.khoId +"'," + this.NhomkhnppId + ", '" + this.loains + "', '" + this.tylevoiPrimary + "', '" + this.apDungDHLe + "','"+this.phanbotheoDH+"','"+this._soXuatToiDa+"', " + _cttbId + ", " + _ctkmId + ", '" + this.xuatHDCoVAT + "',"+this.inchung+","+this.sanphamdautien+" )";

			System.out.println(query);
			if(this.db.updateReturnInt(query)<=0)
			{
				this.db.getConnection().rollback();
				this.msg = "Khong the tao moi 'Ctkhuyenmai', " + query;
				return false; 
			}
			
			//lay dkkm current
			String ctkmCurrent = "";
			query = " select scope_identity() ctkmId ";
			
			ResultSet rsCtkm = this.db.get(query);						
			rsCtkm.next();
			ctkmCurrent = rsCtkm.getString("ctkmId");
			
			rsCtkm.close();
			System.out.println( "ctkmId = "+ ctkmCurrent);
			
			if(this.ctkmId.trim().length() > 0)
			{
				query = "Update CTKHUYENMAI set ctkm_fk = '" + ctkmCurrent + "' where pk_seq = '" + this.ctkmId + "' ";
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "Khong the tao moi 'CTKHUYENMAI', " + query;
					return false; 
				}	
			}
				
			if(this.type.equals("3"))
			{
				query = "insert into NGAYTINHDSKM values('" + ctkmCurrent + "','" + this.ngayds + "','" + this.ngayktds + "')";
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "Khong the tao moi 'NGAYTINHDSKM', " + query;
					return false; 
				}	
			}
			if(this.sanphamdautien.equals("1"))
			{
				if(this.dkkmList.size() > 1)
				{
					this.db.getConnection().rollback();
					this.msg = "CTKM áp dụng sản phẩm đầu tiên chỉ được khai báo 1 điều kiện mua ";
					return false; 
				}
				query =" select count(*)kq from DIEUKIENKM_SANPHAM where DIEUKIENKHUYENMAI_FK =  " + this.dkkmList.get(0).getId();
				ResultSet hehe= this.db.get(query);
				if(hehe.next())
				{
					if(hehe.getInt("kq")  > 1)
					{
						this.db.getConnection().rollback();
						this.msg = "CTKM áp dụng sản phẩm đầu tiên chỉ được khai báo 1 sản phẩm trong điều kiện mua ";
						return false; 
					}
				}
			}
				
			for(int i = 0; i < this.dkkmList.size(); i++)
			{
				Dieukienkm dkkm = (Dieukienkm)this.dkkmList.get(i);	
				if(dkkm.getId().trim().length() > 0)
				{
					query = "Insert into ctkm_dkkm(ctkhuyenmai_fk, dkkhuyenmai_fk, pheptoan, thutudieukien) values('" + ctkmCurrent + "', '" + dkkm.getId() + "', '" + dkkm.getPheptoan() + "', '" + dkkm.getThutudk() + "')";
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "2.Khong the tao moi 'ctkm_dkkm', " + query;
						return false; 
					}
				}
			}
			
			
			
			for(int i = 0; i < this.trakmList.size(); i++)
			{
				Trakm tkm = (Trakm)this.trakmList.get(i);	
				if(tkm.getId().trim().length() > 0)
				{
					query = "Insert into ctkm_trakm(ctkhuyenmai_fk, trakhuyenmai_fk, pheptoan, thutu) values('" + ctkmCurrent + "', '" + tkm.getId() + "', '" + tkm.getPheptoan() + "', '" + tkm.getThutudk() + "')";
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "3.Khong the tao moi 'ctkm_trakm', " + query;
						return false; 
					}
				}
			}
			

			query="delete from ctkm_npp where ctkm_fk = '" + ctkmCurrent + "'";
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "4.Khong the tao moi 'ctkm_npp', " + query;
				return false; 
			}
			
			//System.out.println(query);
	    	for(int i = 0; i < this.npp.length; i++)
	    	{
	    		query ="insert into ctkm_npp values('" + ctkmCurrent +"','"+ this.npp[i] +"','1',"+this.nguoitao+",'"+this.nguoitao+"','"+this.ngaytao+"','"+this.ngaytao+"')";
	    		if(!this.db.update(query))
	    		{
	    			this.db.getConnection().rollback();
	    			this.setMessage("5.Khong The tao moi Ctkm loi :"+ query);
	    			return false;
	    		}
	    	}
	    	
	    	query = "Insert CTKHUYENMAI_KBH(ctkm_fk, kbh_fk)  " +
					"select '" + ctkmCurrent + "', pk_seq from KenhBanHang where pk_seq > 0  ";
	    	if(this.kbhIds.trim().length() > 0)
	    		query += " and pk_seq in ( " + this.kbhIds + " ) ";
	    	
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "4.Khong the tao moi 'CTKHUYENMAI_KBH', " + query;
				return false; 
			}
			
			
			if(this.loaiKhIds.trim().length() > 0)
			{
				query = "Insert CTKHUYENMAI_LOAIKH(ctkm_fk, LOAIKH_Fk)  " +
						"select '" + ctkmCurrent + "', pk_seq from loaicuahang where pk_seq >= 0  ";
		    	if(this.loaiKhIds.trim().length() > 0)
		    		query += " and pk_seq in ( " + this.loaiKhIds + " ) ";
		    	
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "4.Khong the tao moi 'CTKHUYENMAI_LOAIKH', " + query;
					return false; 
				}
			}
			
			if(this.phamvi.trim().length() > 0)
			{
				String[] _pv = this.phamvi.split(",");
				
				for(int i = 0; i < _pv.length; i++)
				{
					query = "Insert CTKHUYENMAI_PHAMVI(ctkm_fk, xuatkhau)  " +
							"select '" + ctkmCurrent + "', '" + _pv[i] + "'  ";
			    	
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "4.Khong the tao moi 'CTKHUYENMAI_PHAMVI', " + query;
						return false; 
					}
				}
			}
	
			//******6.Tao moi "CTKHUYENMAI_HANGCUAHANG"
			if(this._hangCuaHangSelectedIds.trim().length() > 0)
			{
				query = "delete from CTKHUYENMAI_HANGCUAHANG where CTKM_FK='" + ctkmCurrent + "' " 
						+ "";
				System.out.println("CTKHUYENMAI_HANGCUAHANG DELETE sql: " + query);
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "Khong the xoa 'CTKHUYENMAI_HANGCUAHANG', " + query;
					return false; 
				}
				
				String[] temp = this._hangCuaHangSelectedIds.split(",");
				for(int i=0; i< temp.length ; i++)
				{
					query = "insert into CTKHUYENMAI_HANGCUAHANG(CTKM_FK,HCH_FK) " +
								"select '" + ctkmCurrent + "','" + temp[i] + "' ";
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "6.Khong the tao moi 'CTKHUYENMAI_HANGCUAHANG', " + query;
						return false; 
					}
				}
			}
			
			if(this.loaiapdung.trim().length() > 0)
			{
				String[] _pv = this.loaiapdung.split(",");
				
				for(int i = 0; i < _pv.length; i++)
				{
					query = "Insert CTKHUYENMAI_LOAIAPDUNG(ctkm_fk, LOAIAPDUNG)  " +
							"select '" + ctkmCurrent + "', '" + _pv[i] + "'  ";
			    	
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "4.Khong the tao moi 'CTKHUYENMAI_LOAIAPDUNG', " + query;
						return false; 
					}
				}
			}
			this.db.getConnection().commit();
		} 
		catch (Exception e) 
		{
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			this.msg = "Khong them moi chuong trinh khuyen mai nay, vui long kiem tra lai. Loi dong lenh sau :" + e.toString();
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}
	
	boolean checkDieuKienThoaLuuCTKM()
	{
		if(this.loains.trim().equals("1") && this.phanbotheoDH.trim().equals("1"))
		{
			String tmp = "";
			for(int i = 0; i < this.trakmList.size(); i++)
			{
				Trakm tkm = (Trakm)this.trakmList.get(i);	
				if(tkm.getId().trim().length() > 0)
				{
					tmp += "'" + tkm.getId() + "',";
				}
			}
			if(tmp.length() > 0){tmp = tmp.substring(0, tmp.lastIndexOf(","));}
			String sql = "SELECT COUNT(PK_SEQ) NUM FROM TRAKHUYENMAI WHERE PK_SEQ IN ("+ tmp +") AND LOAI = '2'";
			ResultSet rs = this.db.get(sql);
			try { 
				rs.next();
				int num = rs.getInt("NUM");
				rs.close();
				if(num > 0) { return false; }
			} catch (SQLException e) { e.printStackTrace(); }
		}
	 return true;	
	}

	
	public boolean UpdateCtkm() 
	{
		if(!checkDieuKienThoaLuuCTKM())
		{
			this.msg = "Đối với những scheme giới hạn ngân sách và phân bổ theo số suất thì không thể khai báo trả khuyến mãi chiết khấu. Vui lòng kiểm tra lại.";
			return false;
		}
		
		if(!kiemtra_scheme( db,this.scheme ,this.id))
		{		
			this.msg = "Scheme khuyến mại đã tồn tại, vui lòng nhập lại";
			return false;
		}
		if(this.xuatHDCoVAT.equals("1") && this.inchung.equals("1"))
		{
			this.msg = "In chung thì không tick xuất hóa đơn có VAT ";
			return false;
		}
		
		String _cttbId = "NULL";
		if(this.cttbId.trim().length() > 0)
			_cttbId = this.cttbId;
		
		String _ctkmId = "NULL";
		if(this.ctkmId.trim().length() > 0)
			_ctkmId = this.ctkmId;
		
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		try 
		{
			this.db.getConnection().setAutoCommit(false);
			String sql = "";
			boolean flag = CTKM_phat_sinh_DH(this.db, this.id) ;
			
			String log = "";
			if(flag)
				log ="CTKM đã có đơn hàng thỏa KM bạn chỉ có thể đổi 1 số thông tin !";
			
			if(!flag) //Neu chua co don hang thi duoc sua cac khai bao
			{
				if(this.NhomkhnppId.length() == 0)
					this.NhomkhnppId = "null";
				
				//Chan truong hop: neu PhanBoTheoDH=0 va _soXuatToiDa!=0
				if(this.phanbotheoDH.trim().equals("0") && !this._soXuatToiDa.trim().equals("0"))
				{
					this.setPhanbotheoDH("1");
				}
				//Chan truong hop: neu PhanBoTheoDH=1 va _soXuatToiDa==0
				if(this.phanbotheoDH.trim().equals("1") && this._soXuatToiDa.trim().equals("0"))
				{
					this.setPhanbotheoDH("0");
				}
				
				this.ngansach= this.ngansach.replaceAll(",","");
				String query = "Update Ctkhuyenmai set PHANBOTHEOTDV = '"+ this.phanbotdv +"', LOAIPHANBO = '"+ this.loaipb +"', khong_tich_luy = "+this.khong_tich_luy+",khonhan_fk = "+this.khonhan_fk+" ,ap_dung_npp = "+ this.ap_dung_npp +" ,sanphamdautien = "+ this.sanphamdautien +" , scheme = '" + this.scheme.trim() + "', diengiai = N'" + this.diengiai + "', tungay = '" + this.tungay + "', " +
								"denngay = '" + this.denngay + "', loaict = '" + this.type + "', ngansach = '" + this.ngansach + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.nguoisua + "', " +
								"kho_fk ='"+ this.khoId +"',nhomkhnpp_fk ="+ this.NhomkhnppId +", loaingansach='" + this.loains + "', tilevoiprimary = '" + this.tylevoiPrimary + "', ApDUNGCHODHLE = '" + this.apDungDHLe + "',PHANBOTHEODONHANG='"+this.phanbotheoDH+"',SOXUATTOIDA='"+ this._soXuatToiDa +"', cttb_fk = " + _cttbId + ", ctkm_fk = " + _ctkmId + ", xuatHD_coVAT = '" + this.xuatHDCoVAT + "',inchung="+this.inchung+ "  " +
								"where pk_seq = '" + this.id + "'";
				
				System.out.println("Truong Hop 1 :"+query);
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "1.Khong the cap nhat 'Ctkhuyenmai', " + query;
					return false; 
				}
				
				if(this.ctkmId.trim().length() > 0)
				{
					query = "Update CTKHUYENMAI set ctkm_fk = '" + this.id + "' where pk_seq = '" + this.ctkmId + "' ";
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "Khong the cap nhat 'CTKHUYENMAI', " + query;
						return false; 
					}	
				}
				
				
				if(this.type.equals("3"))
				{
					 sql ="update NGAYTINHDSKM set NGAYDS = '"+ this.ngayds +"', NGAYKTDS = '"+ this.ngayktds +"' where ctkm_fk ='"+ this.id +"'";
					 if(!this.db.update(sql))
					 {
						this.db.getConnection().rollback();
						this.msg = "2.Khong the tao moi 'NGAYTINHDSKM', " + query;
						return false; 
					 }	
				}
				
				query = "delete from ctkm_dkkm where ctkhuyenmai_fk = '" + this.id + "'";
				this.db.update(query);
				
				if(this.sanphamdautien.equals("1"))
				{
					if(this.dkkmList.size() > 1)
					{
						this.db.getConnection().rollback();
						this.msg = "CTKM áp dụng sản phẩm đầu tiên chỉ được khai báo 1 điều kiện mua ";
						return false; 
					}
					query =" select count(*)kq from DIEUKIENKM_SANPHAM where DIEUKIENKHUYENMAI_FK =  " + this.dkkmList.get(0).getId();
					ResultSet hehe= this.db.get(query);
					if(hehe.next())
					{
						if(hehe.getInt("kq")  > 1)
						{
							this.db.getConnection().rollback();
							this.msg = "CTKM áp dụng sản phẩm đầu tiên chỉ được khai báo 1 sản phẩm trong điều kiện mua ";
							return false; 
						}
					}
				}
				
				for(int i = 0; i < this.dkkmList.size(); i++)
				{
					Dieukienkm dkkm = (Dieukienkm)this.dkkmList.get(i);	
					
					if(dkkm.getId().trim().length()>0)
					{							
						query = "Insert into ctkm_dkkm(ctkhuyenmai_fk, dkkhuyenmai_fk, pheptoan, thutudieukien) values('" + this.id + "', '" + dkkm.getId() + "', '" + dkkm.getPheptoan() + "', '" + dkkm.getThutudk() + "')";
						if(!this.db.update(query))
						{
							this.db.getConnection().rollback();
							this.msg = "3.Khong the tao moi 'ctkm_dkkm', " + query;
							return false; 
						}
					}
				}
				
				query = "delete from ctkm_trakm where ctkhuyenmai_fk = '" + this.id + "'";
				this.db.update(query);
				
				for(int i = 0; i < this.trakmList.size(); i++)
				{
					Trakm tkm = (Trakm)this.trakmList.get(i);	
					if(tkm.getId().trim().length()>0)
					{
						query = "Insert into ctkm_trakm(ctkhuyenmai_fk, trakhuyenmai_fk, pheptoan, thutu) values('" + this.id + "', '" + tkm.getId() + "', '" + tkm.getPheptoan() + "', '" + tkm.getThutudk() + "')";
						if(!this.db.update(query))
						{
							this.db.getConnection().rollback();
							this.msg = "4.Khong the tao moi 'ctkm_trakm', " + query;
							return false; 
						}
					}
				}
				query = "delete CTKHUYENMAI_KBH where ctkm_fk = '" + this.id + "'";
				this.db.update(query);
				
				query = "Insert CTKHUYENMAI_KBH(ctkm_fk, kbh_fk)  " +
						"select '" + this.id + "', pk_seq from KenhBanHang where pk_seq > 0  ";
		    	if(this.kbhIds.trim().length() > 0)
		    		query += " and pk_seq in ( " + this.kbhIds + " ) ";
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.msg = "4.Khong the cap nhat 'CTKHUYENMAI_KBH', " + query;
					return false; 
				}
				
				
				query = "delete CTKHUYENMAI_LOAIKH where ctkm_fk = '" + this.id + "'";
				this.db.update(query);
				if(this.loaiKhIds.trim().length() > 0)
				{
					query = "Insert CTKHUYENMAI_LOAIKH(ctkm_fk, LOAIKH_Fk)  " +
							"select '" + this.id + "', pk_seq from loaicuahang where pk_seq >= 0  ";
			    	if(this.loaiKhIds.trim().length() > 0)
			    		query += " and pk_seq in ( " + this.loaiKhIds + " ) ";
			    	
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "4.Khong the tao moi 'CTKHUYENMAI_LOAIKH', " + query;
						return false; 
					}
				}
				
				query = "delete CTKHUYENMAI_PHAMVI where ctkm_fk = '" + this.id + "'";
				this.db.update(query);
				if(this.phamvi.trim().length() > 0)
				{
					String[] _pv = this.phamvi.split(",");
					
					for(int i = 0; i < _pv.length; i++)
					{
						query = "Insert CTKHUYENMAI_PHAMVI(ctkm_fk, xuatkhau)  " +
								"select '" + this.id + "', '" + _pv[i] + "'  ";
				    	
						if(!this.db.update(query))
						{
							this.db.getConnection().rollback();
							this.msg = "4.Khong the tao moi 'CTKHUYENMAI_PHAMVI', " + query;
							return false; 
						}
					}
				}
				
				query = "delete from CTKHUYENMAI_HANGCUAHANG where CTKM_FK='" + this.id + "' ";
				this.db.update(query);
				
				if(this._hangCuaHangSelectedIds.trim().length() >0)
				{
					String [] _ids = this._hangCuaHangSelectedIds.split(",");
					for(int i=0; i<_ids.length; i++)
					{
						query = "insert into CTKHUYENMAI_HANGCUAHANG(CTKM_FK,HCH_FK) " +
								"select '" + this.id + "','" + _ids[i] + "' ";
						if(!this.db.update(query))
						{
							this.db.getConnection().rollback();
							this.msg = "5.Khong the tao moi 'CTKHUYENMAI_HANGCUAHANG', " + query;
							return false;
						}
					}
				}
				
				query = "delete from CTKHUYENMAI_LOAIAPDUNG where CTKM_FK='" + this.id + "' ";
				this.db.update(query);
				
				//
				if(this.loaiapdung.trim().length() > 0)
				{
					String[] _pv = this.loaiapdung.split(",");
					
					for(int i = 0; i < _pv.length; i++)
					{
						query = "Insert CTKHUYENMAI_LOAIAPDUNG(ctkm_fk, LOAIAPDUNG)  " +
								"select '" + this.id + "', '" + _pv[i] + "'  ";
				    	
						if(!this.db.update(query))
						{
							this.db.getConnection().rollback();
							this.msg = "4.Khong the tao moi 'CTKHUYENMAI_LOAIAPDUNG', " + query;
							return false; 
						}
					}
				}
				
			}
			else 
			{	
				String	query = "\n	Update Ctkhuyenmai set  DienGiai=N'"+this.diengiai+"',denngay = '" + this.denngay + "' " +
						"\n	, ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.nguoisua + "' " +
						"\n	where pk_seq = '" + this.id + "' " +
						"\n	and not exists( select 1 from donhang  a inner join donhang_ctkm_trakm b on a.pk_seq=b.donhangid  where a.trangthai != 2 and a.ngaynhap > '"+this.denngay+"' and b.ctkmid = "+this.id+" )  ";
				System.out.println("Update Truong Hop 2"+query);
				int cn = this.db.updateReturnInt(query);
				if(cn <=0)
				{
					this.db.getConnection().rollback();
					this.msg = " Đã phát sinh đơn hàng sau ngày ("+this.denngay+") ";
					return false; 
				}
				if(cn >0)
					log +=" Đã thay đổi ngày hết hạn CTKM!";
				
				/*
				//kiem tra denngay cua ctkhuyenmai co lon hon ngay lon nhat cua don hang khong?
				String query="select max(ngaynhap) as maxngaynhan from donhang  a inner join donhang_ctkm_trakm b on "+
						" a.pk_seq=b.donhangid where    ctkmid="+this.id +
						" having max(ngaynhap) <='"+this.denngay+"'"; 
				System.out.println("Check Ngay 1 :"+query);
				
				 rs = this.db.get(query);
				try 
				{
					if(rs.next())
					{}
					else
					{
						this.db.getConnection().rollback();
						this.msg = "II. Khong the cap nhat CT Khuyen Mai, Da Co Nha Phan Phoi Ap Chuong Trinh Khuyen Mai Toi Ngay Nay";
						return false;
					}
					rs.close();
				}
				catch (Exception e) 
				{
					this.db.getConnection().rollback();
					this.msg = "II. Khong the cap nhat CT Khuyen Mai, " + query;
					return false;
				}*/
			}
			
			String query= "UPDATE Ctkm_npp SET CHON=0,NGAYSUA='"+this.ngaysua+"',nguoisua='"+this.nguoisua+"' where ctkm_fk = '" + this.id + "' and chon='1'";
					 
					 /*" UPDATE CTKM_NPP SET CHON = '0', NGAYSUA = CONVERT(VARCHAR(10), GETDATE(), 120), NGUOISUA = '"+this.nguoisua+"' "+ 
					 " FROM CTKM_NPP WHERE CTKM_FK = '" + this.id + "' AND CHON = '1' "+ 
					 " AND NOT EXISTS  "+ 
					 " (  "+ 
					 " 	SELECT 1 FROM DONHANG DH "+ 
					 " 	INNER JOIN DONHANG_CTKM_TRAKM DHKM ON DHKM.DONHANGID = DH.PK_SEQ "+ 
					 " 	INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ = DHKM.CTKMID "+ 
					 " 	WHERE DH.TRANGTHAI != '2' AND DH.NGAYNHAP BETWEEN KM.TUNGAY AND KM.DENNGAY "+ 
					 " 	AND DH.NPP_FK = CTKM_NPP.NPP_FK AND DHKM.CTKMID = CTKM_NPP.CTKM_FK "+ 
					 " ) ";*/
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "II. Khong the cap nhat  CT Khuyen Mai, " + query;
				return false; 
			}
			
			for(int i = 0; i < this.npp.length; i++)
			{
				query ="insert into ctkm_npp values('" + this.id +"','"+ this.npp[i] +"','1','"+this.nguoisua+"','"+this.nguoisua+"','"+this.ngaysua+"','"+this.ngaysua+"')";
				if(!this.db.update(query))
				{
					query="update ctkm_npp set chon='1',nguoisua='"+this.nguoisua+"',ngaysua='"+this.ngaysua+"' where npp_fk='"+this.npp[i]+"' and ctkm_fk='"+this.id+"' ";
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg = "Khong The Cap Nhat 'CTKM_NPP', " + query;
						return false; 
					}
				}
			}
			
			query = " SELECT COUNT(NPP_FK) NUM "+
			 " FROM CTKM_NPP WHERE CTKM_FK = '" + this.id + "' AND CHON = '0' "+ 
			 " AND EXISTS "+ 
			 " (  "+ 
			 " 	SELECT 1 FROM DONHANG DH "+ 
			 " 	INNER JOIN DONHANG_CTKM_TRAKM DHKM ON DHKM.DONHANGID = DH.PK_SEQ "+ 
			 " 	INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ = DHKM.CTKMID "+ 
			 " 	WHERE DH.TRANGTHAI != '2' AND DH.NGAYNHAP BETWEEN KM.TUNGAY AND KM.DENNGAY "+ 
			 " 	AND DH.NPP_FK = CTKM_NPP.NPP_FK AND DHKM.CTKMID = CTKM_NPP.CTKM_FK "+ 
			 " ) ";
			ResultSet rsktra = this.db.get(query);
			rsktra.next();
			if(rsktra.getInt("NUM") > 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Không thể bỏ chọn nhà phân phối đã phát sinh đơn hàng áp chương trình khuyến mãi này.";
				return false; 
			}
			rsktra.close();
			
			query =  
			 "\n if (select COUNT(*) from CTKHUYENMAI where PK_SEQ = " + this.id +" and ISNULL(loaingansach,0)=  0) >=1 " + 
			 "\n and (select COUNT(*) from phanbokhuyenmai where CTKM_FK = " + this.id +" ) >=1 " + 
			 "\n insert phanbokhuyenmai (CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG,SOXUATTOIDA)  " + 
			 "\n select CTKM_FK,NPP_FK ,99999999999,0,0,0 from CTKM_NPP x " +
			 "\n where x.CTKM_FK =" + this.id +" and x.CHON = '1' " +
			 "\n 	and not exists (select 1 from phanbokhuyenmai y where y.ctkm_fk = x.CTKM_FK and x.npp_fk = y.NPP_FK ) ";
			int themphanbo= this.db.updateReturnInt(query);// theem phan bo cho npp moi duoc them vao ctkm da chay, chi ap dung cho ctkm ko gioi han ngan sach 
			if(themphanbo < 0)
			{
				this.db.getConnection().rollback();
				this.msg = "Khong The Cap Nhat 'CTKM_NPP', " + query;
				return false; 
			}
			if(themphanbo >0)
				log += ", đã phân bổ cho NPP mới thêm vào!";
			this.msg = log;
			this.db.getConnection().commit();
		} 
		catch (Exception e) 
		{
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			this.msg="Loi xay Ra Trong Qua Trinh Cap Nhat :"+ e.toString();
			return false; 
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	public void createRS() 
	{
		CreateNhomkhnpp();
		CreateVung();
		
		this.kenhRs = this.db.get("select pk_seq, ten from kenhbanhang where trangthai = '1'");
		String query = "select pk_seq as nspId, diengiai as nspTen from nhomsanpham where nguoitao is not null and type = '1' and trangthai='1'";
		this.nhomspRs = this.db.getScrol(query);	

		System.out.println("Lay nhom san pham: " + query);
	
		query = " select pk_seq as loaiId, loai as loaiTEN from loaicuahang where trangthai = 1 ";
		this.loaiKhRs = this.db.get(query);
		
		query = "select '0' as loaiId, N'Chi nhánh cấp 1' as loaiNPP " +
				"union " +
				"select '1' as loaiId, N'Chi nhánh cấp 2' as loaiNPP " +
				"union " +
				"select '2' as loaiId, N'Quầy bán buôn' as loaiNPP " +
				"union " +
				"select '3' as loaiId, N'Quầy TraphacoDMS' as loaiNPP " +
				"union " +
				"select '4' as loaiId, N'Đối tác' as loaiNPP " +
				"union " +
				"select '5' as loaiId, N'Chi nhánh đối tác' as loaiNPP " +
				"union " +
				"select '6' as loaiId, N'Khách hàng ETC' as loaiNPP ";
		this.loaicnRs = this.db.get(query);
		
		this.cttbRs = this.db.get("select pk_seq, scheme + ' - ' + diengiai as ten from CTTRUNGBAY order by ngayketthuctb desc");
		
		query = "select pk_seq, scheme + ' - ' + diengiai as ten from CTKHUYENMAI where pk_seq > 0 ";
		if(this.id.trim().length() > 0)
			query += " and pk_seq != '" + this.id + "' ";
		query += " order by denngay desc ";
		
		this.ctkmRs = this.db.get(query);
	}

	private void createDkkmList()
	{
		String query = "select a.pk_seq as dkkmId, a.diengiai, cast(isnull(a.tongluong, 0) as numeric(18, 0)) as tongluong, " +
						"cast(isnull(a.tongtien, 0) as numeric(18, 0)) as tongtien, " +
						"isnull(b.pheptoan, '2') as pheptoan, b.thutudieukien, a.loai, a.isThung " +
					"from dieukienkhuyenmai a inner join ctkm_dkkm b on a.pk_seq = b.dkkhuyenmai_fk " +
					"where b.ctkhuyenmai_fk = '" + this.id + "' order by b.thutudieukien asc";
		
		System.out.println("Khoi tao dieu kien khuyen mai: " + query);
		ResultSet rs = this.db.get(query);
		List<IDieukienkm> listDkkm = new ArrayList<IDieukienkm>();
		if(rs != null)
		{
			try 
			{
				IDieukienkm dkkm = null;
				while(rs.next())
				{
					String dkkmId = rs.getString("dkkmId");
					String diengiai = rs.getString("diengiai");
					String tongluong = rs.getString("tongluong");
					String tongtien = rs.getString("tongtien");
					String pheptoan = rs.getString("pheptoan");
					String thutudk = rs.getString("thutudieukien");
					dkkm = new Dieukienkm(dkkmId, diengiai, tongluong, tongtien, pheptoan, thutudk);
					dkkm.setTheothung(rs.getInt("isThung"));
					
					IDieukienDetail dkDetail;
					dkDetail = new DieukienDetail();
					dkDetail.setDiengiai(diengiai);
					dkDetail.setLoaidieukien(rs.getString("loai"));
					
					if(Double.parseDouble(tongluong) > 0)
					{
						dkDetail.setSotong(tongluong);
						dkDetail.setHinhthuc("1");
					}
					else
					{
						dkDetail.setSotong(tongtien);
						dkDetail.setHinhthuc("2");
					}
					
					//Khoi tao SP
					query = "select b.PK_SEQ as spId, b.MA as spMa, b.TEN as spTen, ISNULL(a.soluong, 0) as soluong  " +
							"from DIEUKIENKM_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ where a.DIEUKIENKHUYENMAI_FK = '" + dkkmId + "'";
					System.out.println("Khoi tao sp TKM: "+query);
					ResultSet spDetail = this.db.get(query);
					
					List<ISanpham> sp_dkkmList = new ArrayList<ISanpham>();
					while(spDetail.next())
					{
						ISanpham sp_dkkm = new Sanpham();
						sp_dkkm.setId(spDetail.getString("spId"));
						sp_dkkm.setMasanpham(spDetail.getString("spMa"));
						sp_dkkm.setTensanpham(spDetail.getString("spTen"));
						if(spDetail.getDouble("soluong") > 0)
							sp_dkkm.setSoluong(spDetail.getString("soluong"));
						
						sp_dkkmList.add(sp_dkkm);
					}
					spDetail.close();
					
					dkDetail.setSpList(sp_dkkmList);
					
					dkkm.setDieukineDetail(dkDetail);
					listDkkm.add(dkkm);
					
				}
				if(rs!=null){
					rs.close();
				}
			} 
			catch (SQLException e) {}
		}
		this.dkkmList = listDkkm;
	}
	
	private void createTrakmList()
	{
		String query = "select a.pk_seq as trakmId, a.diengiai, cast(isnull(a.tongluong, 0) as float) as tongluong, " +
							"cast(isnull(a.tongtien, 0) as float) as tongtien, a.hinhthuc, " +
							"cast(isnull(a.chietkhau, 0) as float) as chietkhau, isnull(b.pheptoan, '1') as pheptoan, a.loai, b.thutu " +
						"from trakhuyenmai a inner join ctkm_trakm b on a.pk_seq = b.trakhuyenmai_fk " +
						"where b.ctkhuyenmai_fk = '" + this.id + "' order by b.thutu asc";
		
		System.out.println("Khoi tao tra khuyen mai: " + query);
		ResultSet rs = this.db.get(query);
		List<ITrakm> listTrakm = new ArrayList<ITrakm>();
		if(rs != null)
		{
			try 
			{
				ITrakm trakm = null;
				while(rs.next())
				{
					String trakmId = rs.getString("trakmId");
					String diengiai = rs.getString("diengiai");
					String tongluong = rs.getString("tongluong");
					String tongtien = rs.getString("tongtien");
					String chietkhau = rs.getString("chietkhau");
					String pheptoan = rs.getString("pheptoan");
					String thutudk = rs.getString("thutu");
					trakm = new Trakm(trakmId, diengiai, tongluong, tongtien, chietkhau, pheptoan, thutudk);
					
					
					ITrakmDetail tkmDetail;
					tkmDetail = new TrakmDetail();
					tkmDetail.setDiengiai(diengiai);
					tkmDetail.setLoaitra(rs.getString("loai"));
					tkmDetail.setHinhthuc(rs.getString("hinhthuc"));
					
					if(Double.parseDouble(tongluong) > 0)
					{
						tkmDetail.setSotong(tongluong);
					}
					else
					{
						tkmDetail.setSotong(tongtien);
					}
					
					//Khoi tao SP
					query = "select b.PK_SEQ as spId, b.MA as spMa, b.TEN as spTen, ISNULL(a.soluong, 0) as soluong  " +
							"from TRAKHUYENMAI_SANPHAM a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ where a.TRAKHUYENMAI_FK = '" + trakmId + "'";
					System.out.println("Khoi tao sp TKM: "+query);
					ResultSet spDetail = this.db.get(query);
					
					List<ISanpham> sp_dkkmList = new ArrayList<ISanpham>();
					while(spDetail.next())
					{
						ISanpham sp_dkkm = new Sanpham();
						sp_dkkm.setId(spDetail.getString("spId"));
						sp_dkkm.setMasanpham(spDetail.getString("spMa"));
						sp_dkkm.setTensanpham(spDetail.getString("spTen"));
						if(spDetail.getDouble("soluong") > 0)
							sp_dkkm.setSoluong(spDetail.getString("soluong"));
						
						sp_dkkmList.add(sp_dkkm);
					}
					spDetail.close();
					
					tkmDetail.setSpList(sp_dkkmList);
					
					trakm.setTraDetail(tkmDetail);
					listTrakm.add(trakm);
					
				}
				if(rs!=null){
					rs.close();
				}
			} 
			catch (Exception e) 
			{
				System.out.println("____Exception: " + e.getMessage());
			}
		}
		this.trakmList = listTrakm;
	}
	
	public String getAp_dung_npp() {
		return ap_dung_npp;
	}
	public void setAp_dung_npp(String ap_dung_npp) {
		this.ap_dung_npp = ap_dung_npp;
	}
	
	public void init() 
	{
		String query = "select isnull(a.PHANBOTHEOTDV,'0') as PHANBOTHEOTDV, isnull(a.loaiphanbo,'0') as loaipb, a.khong_tich_luy,isnull(a.ngunghoatdong,'0') as ngunghoatdong, a.khonhan_fk,a.ap_dung_npp,a.sanphamdautien,a.PK_SEQ as ctkmId, a.SCHEME, isnull(a.DIENGIAI, '') as diengiai, a.TUNGAY, a.DENNGAY, " +
						"case when a.LOAICT is null then '1' else a.LOAICT end as type, a.NGANSACH, isnull(a.loaingansach, '') as loaingansach, " +
						"a.DASUDUNG, a.NGAYTAO, a.NGAYSUA, a.tilevoiprimary, a.ApDUNGCHODHLE, b.TEN as nguoitao, c.TEN as nguoisua, a.kho_fk,a.nhomkhnpp_fk, " +
						"isnull(d.NGAYDS, '') as NGAYDS, isnull(NGAYKTDS, '') as NGAYKTDS, isnull(e.donhangId, '0') as donhangId,a.PHANBOTHEODONHANG,a.SoXuatToiDa, a.cttb_fk, a.ctkm_fk, a.xuatHD_coVAT,isnull(a.inchung,0) inchung " +
				"from CTKHUYENMAI a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ " +
				"left join ngaytinhdskm d on a.pk_seq = d.ctkm_fk left join donhang_ctkm_trakm e on a.pk_seq = e.ctkmId " +
				"where a.pk_seq = '" + this.id + "' order by a.NGAYTAO DESC, a.pk_seq DESC";
		
		System.out.println("ctkhuyen mai:"+query);
		ResultSet rs = this.db.get("init: " + query);
		try
        {
            while(rs.next())
            {    
            	this.phanbotdv = rs.getString("PHANBOTHEOTDV")== null ? "0": rs.getString("PHANBOTHEOTDV");
            	this.loaipb = rs.getString("loaipb")== null ? "0": rs.getString("loaipb") ;  
            	this.khong_tich_luy =   rs.getString("khong_tich_luy")== null ? "0": rs.getString("khong_tich_luy");       	
            	this.khonhan_fk = rs.getString("khonhan_fk")== null ? "0": rs.getString("khonhan_fk") ;
            	this.sanphamdautien =   rs.getString("sanphamdautien")== null ? "0": rs.getString("sanphamdautien");
            	this.ap_dung_npp =   rs.getString("ap_dung_npp")== null ? "0": rs.getString("ap_dung_npp");
            	this.id = rs.getString("ctkmId");
            	this.scheme = rs.getString("scheme");
            	this.diengiai = rs.getString("diengiai");
            	this.tungay = rs.getString("tungay");
            	this.denngay = rs.getString("denngay");
            	this.type = rs.getString("type");
            	this.ngansach = rs.getString("ngansach");
            	this.loains = rs.getString("loaingansach");
            	this.dasudung = rs.getString("dasudung");
            	this.ngaytao = rs.getString("ngaytao");
            	this.nguoitao = rs.getString("nguoitao");
            	this.ngaysua = rs.getString("ngaysua");
            	this.nguoisua = rs.getString("nguoisua");
            	//this.trakmId = rs.getString("trakmId");
            	this.khoId = rs.getString("kho_fk");
            	this.NhomkhnppId = rs.getString("nhomkhnpp_fk");
            	this.tylevoiPrimary = rs.getString("tilevoiprimary");
            	this.apDungDHLe = rs.getString("ApDUNGCHODHLE");
            	this.phanbotheoDH = rs.getString("PHANBOTHEODONHANG")==null?"0":rs.getString("PHANBOTHEODONHANG");
            	this._soXuatToiDa = rs.getString("soXuatToiDa")==null?"0":rs.getString("soXuatToiDa");
            	this.cttbId = rs.getString("cttb_fk") == null? "" : rs.getString("cttb_fk");
            	this.ctkmId = rs.getString("ctkm_fk") == null? "" : rs.getString("ctkm_fk");
            	this.inchung = rs.getString("inchung");
            	this.xuatHDCoVAT = rs.getString("xuatHD_coVAT");
            	/*String sql ="select * from ngaytinhdskm where ctkm_fk ='"+ this.id +"'";
            	ResultSet tb = this.db.get(sql);
            	if(tb != null)
        		{ 
            		tb.next();
            		this.ngayds = tb.getString("NGAYDS");
            		this.ngayktds =tb.getString("NGAYKTDS");
            		tb.close();
        		}*/
            	this.ngayds = rs.getString("NGAYDS");
        		this.ngayktds = rs.getString("NGAYKTDS");
        		
        		if(rs.getString("donhangId").length() > 1)
        			this.dacotrongdh = true;
            }
            rs.close();
       	}
        catch (java.sql.SQLException e){}
        
        this.createRS();
        this.createDkkmList();
        this.createTrakmList();
        if(this.id.length() > 0)
    	{
    		query = "select npp_fk as nppId from ctkm_npp where chon='1' and ctkm_fk ='"+ this.id +"'";    		
    		rs = this.db.get(query);
    		String nppIds="";
    		if(rs != null)
    		{
    			try 
    			{
					while(rs.next())
					{
						if(rs.getString("nppId") != null)
							nppIds += rs.getString("nppId") + ",";
					}
					rs.close();
				} 
    			catch (SQLException e) {}
    			   			
    			if(nppIds.length() > 0)
    			{
    				nppIds = nppIds.substring(0, nppIds.length() - 1);
    				this.npp = nppIds.split(",");
    			}
    		}
    	}

        
        //Init kenh ban hang
        ResultSet checkDh = this.db.get("select kbh_fk from CTKHUYENMAI_KBH where ctkm_fk ='" + this.id + "'");
        if(checkDh != null)
        {
        	try 
        	{
        		String kbhIds = "";
				while(checkDh.next())
				{
					kbhIds += checkDh.getString("kbh_fk") + ",";
				}
				checkDh.close();
				
				if(kbhIds.trim().length() > 0)
				{
					kbhIds = kbhIds.substring(0, kbhIds.length() - 1);
					this.kbhIds = kbhIds;
				}
			} 
        	catch (Exception e) 
        	{
        		System.out.println("Exception Kenh Ban Hang: " + e.getMessage());
        	}
        }
        
        
        //Init loai khach hang
        checkDh = this.db.get("select LOAIKH_Fk from CTKHUYENMAI_LOAIKH where ctkm_fk ='" + this.id + "'");
        if(checkDh != null)
        {
        	try 
        	{
        		String kbhIds = "";
				while(checkDh.next())
				{
					kbhIds += checkDh.getString("LOAIKH_Fk") + ",";
				}
				checkDh.close();
				
				if(kbhIds.trim().length() > 0)
				{
					kbhIds = kbhIds.substring(0, kbhIds.length() - 1);
					this.loaiKhIds = kbhIds;
				}
			} 
        	catch (Exception e) 
        	{
        		System.out.println("Exception Loai Khach Hang: " + e.getMessage());
        	}
        }
        
        
        //Init pham vi
        checkDh = this.db.get("select xuatkhau from CTKHUYENMAI_PHAMVI where ctkm_fk ='" + this.id + "'");
        if(checkDh != null)
        {
        	try 
        	{
        		String kbhIds = "";
				while(checkDh.next())
				{
					kbhIds += checkDh.getString("xuatkhau") + ",";
				}
				checkDh.close();
				
				if(kbhIds.trim().length() > 0)
				{
					kbhIds = kbhIds.substring(0, kbhIds.length() - 1);
					this.phamvi = kbhIds;
				}
			} 
        	catch (Exception e) 
        	{
        		System.out.println("Exception PHAM VI: " + e.getMessage());
        	}
        }
        
        //Init loai ap dung
        checkDh = this.db.get("select LOAIAPDUNG from CTKHUYENMAI_LOAIAPDUNG where ctkm_fk ='" + this.id + "'");
        if(checkDh != null)
        {
        	try 
        	{
        		String lapIds = "";
				while(checkDh.next())
				{
					lapIds += checkDh.getString("LOAIAPDUNG") + ",";
				}
				checkDh.close();
				
				if(lapIds.trim().length() > 0)
				{
					lapIds = lapIds.substring(0, lapIds.length() - 1);
					this.loaiapdung = lapIds;
				}
			} 
        	catch (Exception e) 
        	{
        		System.out.println("Exception LOAI AP DUNG: " + e.getMessage());
        	}
        }
      
        //Init các hạng cửa hàng của 1 chương trình KM xác định
        query = "select a.HCH_FK, b.DIENGIAI \n "
        		+ "from CTKHUYENMAI_HANGCUAHANG a, HANGCUAHANG b \n "
        		+ "where a.HCH_FK=b.PK_SEQ and a.CTKM_FK='" + this.id + "' "
        		+ "";
        System.out.println("___"+query);
        checkDh = this.db.get(query);
        if(checkDh != null)
        {
        	try 
        	{
        		String hcString = "";
				while(checkDh.next())
				{
					hcString += checkDh.getString("HCH_FK") + ",";
				}
				checkDh.close();
				
				if(hcString.trim().length() > 0)
				{
					hcString = hcString.substring(0, hcString.length() - 1);
					this._hangCuaHangSelectedIds = hcString;
				}
			} 
        	catch (Exception e) 
        	{
        		System.out.println("Exception PHAM VI: " + e.getMessage());
        	}
        }
        
        //Neu this._soXuatToiDa !=0 ma this.phanbotheoDH=0 thi phai xet lai this.phanbotheoDH=1
/*        if(this._soXuatToiDa != "0" )
        {
        	this.phanbotheoDH="1";
        }
        else
        {
        	this.phanbotheoDH="0";
        }*/
        
	}

	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public void setkhoId(String khoId) 
	{
	   this.khoId = khoId;
	}
	
	public String getkhoId()
	{
		return this.khoId;
	}

	public ResultSet getkhoIds() 
	{
		ResultSet rs = this.db.getScrol("select * from kho where trangthai='1'");
		return rs;
	}

	public void setDsnpp(ResultSet Dsnpp) 
	{
		this.Dsnpp = Dsnpp;
	}

	public ResultSet getDsnpp()
	{
		return this.Dsnpp;
	}
	
    public void CreateDsnpp()
    {
    	Utility Ult = new Utility();
	   	String query =  " select pk_seq, ma , ten from nhaphanphoi "+
	   					" where pk_seq != 1 and trangthai = '1' and isKHACHHANG = '0'";
	   	if(this.phanloai.equals("2"))
	   	{
	   		query += " and pk_seq in " + Ult.quyen_npp(this.userId);
	   	}
	   	else
	   	{
	   		query += " and ( pk_seq = "+this.nppTaoId+" or tructhuoc_fk ="+this.nppTaoId+") ";
	   	}
	   	
	   	//loc theo khu vuc
	   	if(khuvucId.length() > 0)
	   		query += " and  khuvuc_fk in (" + khuvucId + ") ";
	   	
	   	//khong chon khu vuc, loc theo vung
	   	if(vungId.length() > 0)
	   		query += " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk in (" + vungId + ") ) ";
	    
	   	if(kbhIds.length() > 0)
	   		query += " and pk_seq in (select npp_fk from nhapp_kbh where kbh_fk in (" + this.kbhIds + "))";
	   	if(npp!=null)
	   	{
	   		String nppIds = "";
	   		for(int i=0;i<npp.length;i++)
	   		{
	   			nppIds+=npp[i]+",";	
	   		}
	   		if(nppIds.length()>0)
	   		{
	   			nppIds=nppIds.substring(0,nppIds.length()-1);
	   			query+=" and pk_seq not in ("+nppIds+") union all select pk_seq,ma , ten from nhaphanphoi where pk_seq != 1 and trangthai = '1' and isKHACHHANG='0'  and pk_Seq in ("+nppIds+" )";
	   		}
	   	}
	   	System.out.println("1.Khoi tao NPP:" + query);
	   	this.Dsnpp = this.db.get(query);	   	
    }

	public void setNpp(String[] npp) 
	{	
		this.npp = npp;
	}

	public Hashtable<Integer, String> getnpp()
	{
		Hashtable<Integer, String> selected = new Hashtable<Integer, String>();
		if(this.npp != null){
			int size = (this.npp).length;
			int m = 0;
			while(m < size){
				selected.put(new Integer(m), this.npp[m]) ;
				m++;
			}
		}else{
			selected.put(new Integer(0), "null");
		}
		return selected;	
	}

   public ResultSet getNhomkhnpp() 
   {	
		return this.Nhomkhnpp;
   }
   
   private void CreateNhomkhnpp()
   { 
	   this.Nhomkhnpp = this.db.get("select * from nhomkhachhangnpp");
   }
	
	public void setNhomkhnppId(String NhomkhnppId) 
	{	
		this.NhomkhnppId = NhomkhnppId;
	}

	
	public String getNhomkhnppId() {
		
		return this.NhomkhnppId;
	}

	
	public void setVungId(String vungId) {
		
		this.vungId = vungId;
	}

	
	public String getVungId() {
		
		return this.vungId;
	}

	
	public void setKhuvucId(String khuvucId) {
		
		this.khuvucId = khuvucId;
	}

	
	public String getKhuvucId() {
		
		return this.khuvucId;
	}

	
	public void setVungs(ResultSet vungs) {
		
		this.vungs = vungs;
	}

	
	public ResultSet getVungs() {
		
		return this.vungs;
	}

	
	public void setKhuvuc(ResultSet khuvucs) {
		
		this.khuvucs = khuvucs;
	}

	
	public ResultSet getKhuvuc() {
		
		return this.khuvucs;
	}
   
	public void CreateVung()
	{
		db = new dbutils();
		String sql = "select * from vung";
		
		this.vungs = this.db.get(sql);
		if(this.vungId.length() > 0)
		{
			sql = "select * from khuvuc where vung_fk in (" + this.vungId + ")";
			this.khuvucs = this.db.get(sql); 
		}
		else
		{
			sql = "select * from khuvuc";
			this.khuvucs = this.db.get(sql);
		}
		CreateDsnpp();
	}

	
	public void setload(String load) {
		
		this.load = load;
	}

	public String getload() {
		
		return this.load;
	}

	
	public void setngayds(String ngayds) {
		
	      this.ngayds = ngayds;	
	}

	
	public String getngayds() {
		
		return this.ngayds;
	}

	
	public void setngayktds(String ngayktds) {
		
		this.ngayktds = ngayktds;
	}

	
	public String getngayktds() {
		
		return this.ngayktds;
	}

	public void setKbhRs(ResultSet kbh)
	{
		this.kenhRs = kbh;
	}

	public ResultSet getKbhRs()
	{
		return this.kenhRs;
	}

	public void setKbhIds(String kbhIds) 
	{
		this.kbhIds = kbhIds;
	}

	public String getKbhIds() 
	{
		return this.kbhIds;
	}

	public void setLoaiNganSach(String loains)
	{
		this.loains = loains;
	}

	public String getLoaiNganSach() 
	{
		return this.loains;
	}

	public void setActive(String active) 
	{
		this.active = active;
	}

	public String getActive()
	{
		return this.active;
	}

	public ResultSet getDsnppSelected() 
	{
		return this.DsnppIds;
	}

	
	public void setDsnppSelected(ResultSet DsnppSelected) 
	{
		this.DsnppIds = DsnppSelected;
	}

	public void setDacotrongdh(boolean dacotrongdh) 
	{
		this.dacotrongdh = dacotrongdh;
	}

	public boolean getDacotrongdh() 
	{
		return this.dacotrongdh;
	}

	
	public void DbClose() 
	{
		try
		{
			if(Dsnpp!=null){
				Dsnpp.close();
			}
			if(DsnppIds!=null){
				DsnppIds.close();
			}
			if(trakmRs!=null){
				trakmRs.close();
			}
			if(khoIds!=null){
				khoIds.close();
			}
			if(Nhomkhnpp!=null){
				Nhomkhnpp.close();
			}
			if(vungs!=null){
				vungs.close();
			}
			if(khuvucs!=null){
				khuvucs.close();
			}
			if(kenhRs!=null){
				kenhRs.close();
			}
			if(this.nhomspRs != null)
				nhomspRs.close();
			
			if(this._hangCuaHangs !=null){
				_hangCuaHangs.close();
			}
			
			
			 if(db!=null){
				 this.db.shutDown();
			 }
		}
		catch(Exception er){}
	}

	
	public String getLoaikhuyenmai() 
	{
		return this.loaikhuyenmai;
	}

	public void setLoaikhuyenmai(String loaikm) 
	{
		this.loaikhuyenmai = loaikm;
	}

	public String getTylevoiPrimary()
	{
		return this.tylevoiPrimary;
	}

	public void setTylevoiPrimary(String tyle)
	{
		this.tylevoiPrimary = tyle;
	}

	public List<ITrakm> getTrakmList() 
	{
		return this.trakmList;
	}

	public void setTrakmList(List<ITrakm> trakmList) 
	{
		this.trakmList = trakmList;
	}

	public void setNhomspRs(ResultSet nspRs) 
	{
		this.nhomspRs = nspRs;
	}

	public ResultSet getNhomspRs()
	{
		return this.nhomspRs;
	}

	public void setApdungchoDHLe(String flag) 
	{
		this.apDungDHLe = flag;
	}

	public String getApdungchoDHLe() 
	{
		return this.apDungDHLe;
	}

	
	public void initExcel()
	{
		String query = "select a.PK_SEQ as ctkmId, a.SCHEME, isnull(a.DIENGIAI, '') as diengiai, a.TUNGAY, a.DENNGAY, " +
				"case when a.LOAICT is null then '1' else a.LOAICT end as type, a.NGANSACH, isnull(a.loaingansach, '') as loaingansach, " +
				"a.DASUDUNG, a.NGAYTAO, a.NGAYSUA, a.tilevoiprimary, a.ApDUNGCHODHLE, b.TEN as nguoitao, c.TEN as nguoisua, a.kho_fk,a.nhomkhnpp_fk, " +
				"isnull(d.NGAYDS, '') as NGAYDS, isnull(NGAYKTDS, '') as NGAYKTDS, isnull(e.donhangId, '0') as donhangId " +
		"from CTKHUYENMAI a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ " +
		"left join ngaytinhdskm d on a.pk_seq = d.ctkm_fk left join donhang_ctkm_trakm e on a.pk_seq = e.ctkmId " +
		"where a.pk_seq = '" + this.id + "' order by a.NGAYTAO DESC, a.pk_seq DESC";

		System.out.println("ctkhuyen mai:"+query);
		ResultSet rs = this.db.get(query);
		try
		{
		    while(rs.next())
		    {    	
		    	this.id = rs.getString("ctkmId");
		    	this.scheme = rs.getString("scheme");
		    	this.diengiai = rs.getString("diengiai");
		    	this.tungay = rs.getString("tungay");
		    	this.denngay = rs.getString("denngay");
		    	this.type = rs.getString("type");
		    	this.ngansach = rs.getString("ngansach");
		    	this.loains = rs.getString("loaingansach");
		    	this.dasudung = rs.getString("dasudung");
		    	this.ngaytao = rs.getString("ngaytao");
		    	this.nguoitao = rs.getString("nguoitao");
		    	this.ngaysua = rs.getString("ngaysua");
		    	this.nguoisua = rs.getString("nguoisua");
		    	this.khoId = rs.getString("kho_fk");
		    	this.NhomkhnppId = rs.getString("nhomkhnpp_fk");
		    	this.tylevoiPrimary = rs.getString("tilevoiprimary");
		    	this.apDungDHLe = rs.getString("ApDUNGCHODHLE");
		    	this.ngayds = rs.getString("NGAYDS");
				this.ngayktds = rs.getString("NGAYKTDS");
				if(rs.getString("donhangId").length() > 1)
					this.dacotrongdh = true;
		    }
		    rs.close();
		}
		catch (java.sql.SQLException e){}
		
		query=
		"	select distinct kv.DIENGIAI as kvTen" +
		"		from CTKM_NPP km inner join NHAPHANPHOI npp "+
		"		on npp.PK_SEQ=km.NPP_FK and CHON=1 "+
		"		inner join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK "+
		"	where km.CTKM_FK='"+this.id+"' ";	
		rs=this.db.get(query);
		System.out.println("[KhuVuc]"+query);
		try
		{
			while(rs.next())
			{
				this.khuvucId+=rs.getString("kvTen") +";";  
			}
			rs.close();
			if(this.khuvucId.length()>0)
				this.khuvucId=this.khuvucId.substring(0,this.khuvucId.length()-1);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		this.createDkkmList();
	    this.createTrakmList();
	 	if(this.id.length() > 0)
	   	{
	   		this.DsnppIds = this.db.get("select pk_seq, ten,ma  from nhaphanphoi where pk_seq != 1 and pk_seq in (select NPP_FK from CTKM_NPP where   chon='1' and CTKM_FK = '" + this.id + "')");
	   	}
	}
	
	public void setPhanbotheoDH(String pbtheoDH) {
		
		this.phanbotheoDH = pbtheoDH;
	}


	public String getPhanbotheoDH() {
		
		return this.phanbotheoDH;
	}

	
	public void setLoaikhRs(ResultSet loaiKh) {
		
		this.loaiKhRs = loaiKh;
	}

	
	public ResultSet getLoaikhRs() {
		
		return this.loaiKhRs;
	}

	
	public void setLoaikhIds(String lkhIds) {
		
		this.loaiKhIds = lkhIds;
	}

	
	public String getLoaikhIds() {
		
		return this.loaiKhIds;
	}

	
	public void setPhamvi(String phamvi) {

		this.phamvi = phamvi;
	}


	public String getPhamvi() {

		return this.phamvi;
	}

	
	public void setLoaicnRs(ResultSet loaiCn) {

		this.loaicnRs = loaiCn;
	}

	
	public ResultSet getLoaicnRs() {

		return this.loaicnRs;
	}

	
	public void setLoaicnIds(String loaicnIds) {

		this.loaicnIds = loaicnIds;
	}

	
	public String getLoaicnIds() {

		return this.loaicnIds;
	}

	
	public void setSoXuatToiDa(String _soXuatToiDa) {

		this._soXuatToiDa=_soXuatToiDa;
	}

	
	public String getSoXuatToiDa() {

		return this._soXuatToiDa;
	}

	
	public ResultSet getHangCuaHangs() {

		ResultSet rs = this.db.get("select hch.PK_SEQ,hch.DIENGIAI from HANGCUAHANG hch where hch.TRANGTHAI='1'");
		return rs;
	}

	
	public void setHangCuaHangIds(String _hchIds){

		this._hangCuaHangSelectedIds = _hchIds;
	}

	
	public String getHangCuaHangIds()
	{
		return this._hangCuaHangSelectedIds;
	}

	
	public void setCttbRs(ResultSet cttbRs) {
		
		this.cttbRs = cttbRs;
	}

	
	public ResultSet getCttbRs() {
		
		return this.cttbRs;
	}

	
	public void setCttbId(String cttbId) {
		
		this.cttbId = cttbId;
	}

	
	public String getCttbId() {
		
		return this.cttbId;
	}

	
	public void setCtkmRs(ResultSet ctkmRs) {
		
		this.ctkmRs = ctkmRs;
	}

	
	public ResultSet getCtkmRs() {
		
		return this.ctkmRs;
	}

	
	public void setCtkmId(String ctkmId) {
		
		this.ctkmId = ctkmId;
	}

	
	public String getCtkmId() {
		
		return this.ctkmId;
	}

	
	public void setLoaiapdung(String loaiapdung) {
		
		this.loaiapdung = loaiapdung;
	}

	
	public String getLoaiapdung() {
		
		return this.loaiapdung;
	}

	
	public void setXuatHDCoVAT(String xuatHDCoVAT) {
		
		this.xuatHDCoVAT = xuatHDCoVAT;
	}

	
	public String getXuatHDCoVAT() {
		
		return this.xuatHDCoVAT;
	}


	public String getInchung() {
		return inchung;
	}

	public void setInchung(String inchung) {
		this.inchung = inchung;
	}

	public String getSanphamdautien() {
		return sanphamdautien;
	}
	public void setSanphamdautien(String sanphamdautien) {
		this.sanphamdautien = sanphamdautien;
	}
	
	public static boolean  kiemtra_scheme(Idbutils db,String scheme ,String Id)
	{
		String sql = "select count(*) as num  from ctkhuyenmai where scheme ='" + scheme + "'";
		if(Id != null && Id.trim().length() > 0)
			sql += " and pk_seq  not in ( " + Id + " ) ";
		
		int count = 0;
		ResultSet rs = db.get(sql);
		try 
		{
			rs.next();
			count = rs.getInt("num");
		
		} 
		catch (Exception e) 
		{
			return false;
		}
		
		if(count > 0)
			return false;
		
		return true;
		
	}
	
	public static boolean  CTKM_phat_sinh_DH(Idbutils db ,String Id) throws SQLException
	{
		boolean flag = false;
    	String sql="select count(*) as num" +
    			"  from " +
    			"  ( 	" +
    			"  		select ctkmid  from donhang_ctkm_trakm where  ctkmid='" + Id+ "'AND DONHANGID IN (SELECT PK_sEQ FROM DONHANG WHERE TRANGTHAI!=2 ) union all " +
    			" 		select ctkmid  from ERP_DONDATHANGNPP_CTKM_TRAKM where  ctkmid='" + Id+ "' union all " +
    			" 		select ctkmid  from ERP_DONDATHANG_CTKM_TRAKM where  ctkmid='" + Id+ "' " +
    			"	) a	  ";
		System.out.println(sql);
    	ResultSet rs = db.get(sql);
    	
    	rs.next();
		if(rs.getInt("num") > 0)
		{
			flag = true;
			
			rs.close();
			//return false;
		}
		rs.close();
		return flag;
		
	}

	String phanloai = "";
	String loaiUser = "";
	String nppTaoId=  "";
	String view = "";
	public void init_user()
	{
		try{
			
			Utility Ult = new Utility();
			String query="select phanloai,loai from nhanvien where pk_seq="+this.userId;
			System.out.println(" user :" + query);
			ResultSet rs=this.db.get(query);
			if(rs!=null){
				if(rs.next()){

					this.phanloai = rs.getString("phanloai");
					System.out.println("Phan loai : "+this.phanloai);					 				
					this.loaiUser =  rs.getString("loai");
					if( rs.getString("phanloai").equals("1")){
						this.nppTaoId = Ult.getIdNhapp(this.userId);
					}
					rs.close();
				}
			}
		}catch(Exception er){

		}
		System.out.println("nppTaoId = "+ this.nppTaoId);
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public dbutils getDb() {
		return db;
	}
	@Override
	public void setloaiPhanbo(String loaipb) {
		// TODO Auto-generated method stub
		this.loaipb = loaipb;
	}
	@Override
	public String getloaiPhanbo() {
		// TODO Auto-generated method stub
		return this.loaipb;
	}
	@Override
	public void setPhanbomucTDV(String pbtdv) {
		// TODO Auto-generated method stub
		this.phanbotdv = pbtdv;
	}
	@Override
	public String getPhanbomucTDV() {
		// TODO Auto-generated method stub
		return this.phanbotdv;
	}
}
