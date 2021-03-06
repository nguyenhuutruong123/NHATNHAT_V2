package geso.dms.center.beans.trakhuyenmai.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import geso.dms.center.beans.dieukienkhuyenmai.ISanpham;
import geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham;
import geso.dms.center.beans.trakhuyenmai.ITrakhuyenmai;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

public class Trakhuyenmai implements ITrakhuyenmai
{
	String userId;
	String id;
	String diengiai;
	String tongtien;
	String tongluong;
	String chietkhau;
	String type; //1 tra tien; 2 tra chiet khau; 3 tra san pham
	String hinhthuc; //1 bat buoc, 2 tuy chon dat tong luong
	String nguoitao;
	String nguoisua;
	String ngaytao;
	String ngaysua;
	String msg;
	
	ResultSet nhomspRs;
	String nhomspId;
	
	List<ISanpham> spList;
	Hashtable<String, Integer> sp_nhomspIds;
	List<ISanpham> spSudungList; //list sanpham nguoi dung nhap
	
	dbutils db;
	
	public Trakhuyenmai(String[] param)
	{
		this.id = param[0];
		this.diengiai = param[1];
		this.tongtien = param[2];
		this.tongluong = param[3];
		this.chietkhau = param[4];
		this.type = param[5];
		this.hinhthuc = param[6];
		this.ngaytao = param[7];
		this.nguoitao = param[8];
		this.ngaysua = param[9];
		this.nguoisua = param[10];		
		this.msg = "";		
		this.nhomspId = "";
		
		sp_nhomspIds = new Hashtable<String, Integer>();
		spSudungList = new ArrayList<ISanpham>();
		db = new dbutils();
	}
	
	public Trakhuyenmai(String id)
	{
		this.id = id;
		this.diengiai = "";
		this.tongtien = "";
		this.tongluong = "";
		this.chietkhau = "";
		this.type = "";
		this.hinhthuc = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";
		this.msg = "";		
		this.nhomspId = "";
		
		sp_nhomspIds = new Hashtable<String, Integer>();
		spSudungList = new ArrayList<ISanpham>();
		db = new dbutils();
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
	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}
	public String getDiengiai()
	{
		return diengiai;
	}
	public String getTongtien()
	{
		return this.tongtien;
	}
	public void setTongtien(String tongtien)
	{
		this.tongtien = tongtien;
	}
	public String getTongluong()
	{
		return this.tongluong;
	}
	public void setTongluong(String tongluong)
	{
		this.tongluong = tongluong;
	}
	public String getType()
	{
		return this.type;
	}
	public void setType(String type)
	{
		this.type = type;
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
	
	public List<ISanpham> getSpList() 
	{
		return this.spList;
	}

	public void setSpList(List<ISanpham> spList)
	{
		this.spList = spList;
	}

	public boolean CreateTrakm(String[] masp, String[] soluong) 
	{
		dbutils db = new dbutils();
		this.ngaytao = getDateTime();
		this.nguoitao = this.userId;
		
		if(this.type.equals("1")) //tra tien
			this.tongluong = this.chietkhau = "";
		if(this.type.equals("2") || this.type.equals("3"))
			this.tongtien = "";
		if(this.hinhthuc.equals("1"))
			this.tongluong = this.tongtien = this.chietkhau = "";
		if(this.hinhthuc == "")
			this.hinhthuc = "2";
		
		if(this.tongluong == "")
			this.tongluong = "null";
		if(this.chietkhau == "")
			this.chietkhau = "null";
		if(this.tongtien == "")
			this.tongtien = "null";
				
		try 
		{
			db.getConnection().setAutoCommit(false);
				
			String query=
			" select COUNT(*) AS SODONG "+
			" from TRAKHUYENMAI  "+
			" where DIENGIAI=N'"+this.diengiai+"'";
			if(this.id.length()>0)
				query+=" and pk_seq!='"+this.id+"'";
			
			System.out.print("[Check]"+query);
			ResultSet rsCheck=db.get(query);
			while(rsCheck.next())
			{
				if(rsCheck.getInt(1)>0)
				{
					this.msg="Di???n gi???i ???? t???n t???i,vui l??ng nh???p l???i";
					return false;
				}
			}	
			if(rsCheck!=null)rsCheck.close();
			query = "Insert into Trakhuyenmai(diengiai, tongluong, tongtien, chietkhau, loai, hinhthuc, ngaytao, nguoitao, ngaysua, nguoisua) values(";
			query = query + "N'" + this.diengiai + "', " + this.tongluong + ", " + this.tongtien + "," + this.chietkhau + ", '" + this.type + "', '" + this.hinhthuc + "', '" + this.ngaytao + "', '" + this.nguoitao + "', '" + this.ngaytao + "', '" + this.nguoitao + "')";
			
			if(!db.update(query))
			{
				Utility.rollback_and_shutdown(db); 
				this.msg = "Exception";
				return false; 
			}
			if(this.type.equals("3")) //tra sanpham
			{
				if(masp.length > 0)
				{
					String trakmCurrent = "";
					query = "select IDENT_CURRENT('Trakhuyenmai') as trakmId";
					
					ResultSet rsTrakm = this.db.get(query);						
					rsTrakm.next();
					trakmCurrent = rsTrakm.getString("trakmId");
					rsTrakm.close();
					
					for(int i = 0; i < masp.length; i++)
					{
						if(masp[i].length() > 0)
						{
							ResultSet rs = db.get("select pk_seq from sanpham where ma='" + masp[i].trim() + "'");
							String pk_seq = "";
							if(rs != null) 
							{
								try
								{
									rs.next();
									pk_seq = rs.getString("pk_seq");
									rs.close();
								}
								catch (SQLException e) {}
							} 
							if(this.hinhthuc.equals("2"))
								soluong[i] = "null";
	
							if(pk_seq != "")
								query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk, soluong, dongia) values('" + trakmCurrent + "', '" + pk_seq + "', " + soluong[i].trim() + ", null)";
							if(!db.update(query))
							{
								Utility.rollback_and_shutdown(db); 
								this.msg = "Exception";
								return false; 
							}
						}					
					}
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			Utility.rollback_and_shutdown(db);
			this.msg = "Exception";
			return false;			
			
		}
		return true;
	}

	public boolean UpdateTrakm(String[] masp, String[] soluong) 
	{
		
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		
		
		if(this.type.equals("1")) //tra tien
			this.tongluong = this.chietkhau = "";
		if(this.type.equals("2") || this.type.equals("3"))
			this.tongtien = "";
		if(this.hinhthuc.equals("1"))
			this.tongluong = this.tongtien = this.chietkhau = "";
		if(this.hinhthuc == "")
			this.hinhthuc = "2";
		
		if(this.tongluong == "")
			this.tongluong = "null";
		if(this.chietkhau == "")
			this.chietkhau = "null";
		if(this.tongtien == "")
			this.tongtien = "null";
				
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			if(checkExits(db))
			{
				Utility.rollback_and_shutdown(db);
				this.msg = "Tr??? khuy???n m???i ???? ???????c s??? d???ng cho ch????ng tr??nh khuy???n m???i. B???n kh??ng th??? s???a l???i";
				return false;
			}
			
			String query=
			" select COUNT(*) AS SODONG "+
			" from TRAKHUYENMAI  "+
			"  where DIENGIAI=N'"+this.diengiai+"'";
			if(this.id.length()>0)
				query+=" and pk_seq!='"+this.id+"'";
			System.out.println("quey:"+ query);
			
			ResultSet rsCheck=db.get(query);
			rsCheck.next();
			
			if(rsCheck.getInt("SODONG")>0)
			{
				Utility.rollback_and_shutdown(db);
				this.msg="Di???n gi???i ???? t???n t???i,vui l??ng nh???p l???i";
				return false;
			}
			rsCheck.close();
			
				
			 query = "Update Trakhuyenmai set diengiai = N'" + this.diengiai + "', tongluong = " + this.tongluong + ", tongtien = " + this.tongtien + ", chietkhau=" + this.chietkhau + ", loai = '" + this.type + "', hinhthuc = '" + this.hinhthuc + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.nguoisua + "' where pk_seq='" + this.id + "'";
		
			if(!db.update(query))
			{
				Utility.rollback_and_shutdown(db);
				this.msg = "Exception";
				return false; 
			}
			
			query = "delete from trakhuyenmai_sanpham where trakhuyenmai_fk = '" + this.id + "'";
			db.update(query);
			
			if(this.type.equals("3"))
			{
				if(masp.length > 0)
				{
					for(int i = 0; i < masp.length; i++)
					{
						if(masp[i].length() > 0)
						{
							ResultSet rs = db.get("select pk_seq from sanpham where ma='" + masp[i].trim() + "'");
							String pk_seq = "";
							if(rs != null) 
							{
								try
								{
									rs.next();
									pk_seq = rs.getString("pk_seq");
									rs.close();
								}
								catch (SQLException e) {}
							} 
							if(this.hinhthuc.equals("2"))
								soluong[i] = "null";
							if(pk_seq != "")
								query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk, soluong, dongia) values('" + this.id + "', '" + pk_seq + "', " + soluong[i].trim() + ", null)";
							if(!db.update(query))
							{
								Utility.rollback_and_shutdown(db);
								this.msg ="Exception";
								return false; 
							}
						}					
					}
				}
			}
			db.getConnection().commit();
		} 
		catch (SQLException e) {
			Utility.rollback_and_shutdown(db);
			e.printStackTrace();
			return false;
			
		}
		return true;
	}

	public void createRS() 
	{
		//Utility Ult = new Utility();
		String query = "select pk_seq as nspId, diengiai as nspTen from nhomsanpham where nguoitao is not null and type = '1' and trangthai='1'";
		this.nhomspRs = db.get(query);
		
		System.out.println("Cau lenh lay nhom san pham: " + query + "\n");
		
		if(this.nhomspId.length() > 0)
			this.createSpList();
	}
	
	private void createSpList()
	{
		//String query = "select b.pk_seq, b.MA, b.TEN, isnull(c.giamuanpp, 0) as gblc from NHOMSANPHAM_SANPHAM a inner join SANPHAM b on a.SP_FK = b.PK_SEQ inner join bgmuanpp_sanpham c on b.pk_seq = c.sanpham_fk  ";
		//query = query + " where a.NSP_FK = '" + this.nhomspId + "'";
		
		String query = "select b.pk_seq, b.MA, b.TEN from NHOMSANPHAM_SANPHAM a inner join SANPHAM b on a.SP_FK = b.PK_SEQ ";
		query = query + " where a.NSP_FK = '" + this.nhomspId + "'";
		
		ResultSet rs = db.get(query);
		List<ISanpham> splist = new ArrayList<ISanpham>();
		if(rs != null)
		{
			try 
			{
				ISanpham sp = null;
				while(rs.next())
				{
					String spId = rs.getString("pk_seq");
					String ma = rs.getString("MA");
					String ten = rs.getString("TEN");
					//String dongia = rs.getString("gblc");
					sp = new Sanpham(spId, ma, ten, "", "");
					splist.add(sp);
				}
			} 
			catch (SQLException e) {}
		}
		this.spList = splist;
	}
	
	public void createTrakmSpList()
	{		
		String query = "select a.soluong, a.dongia, b.pk_seq, b.MA, b.TEN from trakhuyenmai_sanpham a inner join SANPHAM b on a.SanPham_FK = b.PK_SEQ where a.trakhuyenmai_fk = '" + this.id + "'";				
		ResultSet rs = db.get(query);
		List<ISanpham> splist = new ArrayList<ISanpham>();
		if(rs != null)
		{
			try 
			{
				ISanpham sp = null;
				while(rs.next())
				{
					String spId = rs.getString("pk_seq");
					String ma = rs.getString("MA");
					String ten = rs.getString("TEN");
					String dongia = rs.getString("dongia");
					String soluong = "";
					if(rs.getString("soluong") != null)
						soluong = rs.getString("soluong");
					sp = new Sanpham(spId, ma, ten, soluong, dongia);
					splist.add(sp);
				}
			} 
			catch (SQLException e) {}
		}
		this.spSudungList = splist;
	}

	public void init() 
	{
		String query = "select a.PK_SEQ as trakmId, a.DIENGIAI, isnull(tongluong,0) as tongluong, isnull(tongtien,0) as tongtien, isnull(chietkhau,0) as chietkhau, isnull(a.LOAI,1) as loai, isnull(a.HINHTHUC,0) as hinhthuc, a.NGAYTAO, a.NGAYSUA, b.TEN as nguoitao, c.TEN as nguoisua ";
		query = query + " from TRAKHUYENMAI a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ where a.PK_SEQ='" + this.id + "'";
		System.out.println("tra khuyen mai"+query);
		ResultSet rs = db.get(query);
		try
        {
            rs.next();        	
            this.id = rs.getString("trakmId");
			this.diengiai = rs.getString("diengiai");
			if(rs.getString("tongluong") != null)
				this.tongluong = rs.getString("tongluong");
			else
				this.tongluong="0";
			if(rs.getString("tongtien") != null)
				this.tongtien = rs.getString("tongtien");
			else 
				this.tongtien = "0";
			if(rs.getString("chietkhau") != null)
				this.chietkhau = rs.getString("chietkhau");
			else
				this.chietkhau ="0";
			
			this.type = rs.getString("loai");
			this.hinhthuc = rs.getString("hinhthuc");
			this.ngaytao = rs.getString("ngaytao");
			this.nguoitao = rs.getString("nguoitao");
			this.ngaysua = rs.getString("ngaysua");
			this.nguoisua = rs.getString("nguoisua");		
			rs.close();
       	}
        catch (java.sql.SQLException e){}
        Utility Ult = new Utility();
        this.nhomspRs = db.get("select pk_seq as nspId, diengiai as nspTen from nhomsanpham where type = '1' and trangthai='1' and a.pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in"+ Ult.quyen_sanpham(this.userId)+")");	
        this.createTrakmSpList();
	}
	
	public ResultSet getNhomspRs()
	{
		return this.nhomspRs;
	}

	public void setNhomspRs(ResultSet nhomspRs) 
	{
		this.nhomspRs = nhomspRs;
	}

	public String getNhomspId() 
	{
		return this.nhomspId;
	}

	public void setNhomspId(String nhomspId) 
	{
		this.nhomspId = nhomspId;
	}

	public String getHinhthuc() 
	{
		return this.hinhthuc;
	}

	public void setHinhthuc(String hinhthuc) 
	{
		this.hinhthuc = hinhthuc;
	}

	public Hashtable<String, Integer> getSp_nhomspIds()
	{
		return this.sp_nhomspIds;
	}

	public void setSp_nhomspIds(Hashtable<String, Integer> sp_nhomspIds)
	{
		this.sp_nhomspIds = sp_nhomspIds;
	}

	public List<ISanpham> getSpSudungList() 
	{
		return this.spSudungList;
	}

	public void setSpSudungList(List<ISanpham> splist) 
	{
		this.spSudungList = splist;
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getChietkhau() 
	{
		return this.chietkhau;
	}

	public void setChietkhau(String chietkhau)
	{
		this.chietkhau = chietkhau;
	}

	private boolean checkExits(dbutils db) 
	{
		ResultSet rs = db.get("select count(*) as sodong from ctkm_trakm inner join DONHANG_CTKM_TRAKM on ctkm_trakm.CTKHUYENMAI_FK=DONHANG_CTKM_TRAKM.CTKMID WHERE TRAKHUYENMAI_FK = '" + this.id + "'");
		int sodong = 0;
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					sodong = rs.getInt("sodong");
					rs.close();
				}
			} catch(Exception e) { sodong = 0; }
		}
		if(sodong > 0)
			return true;
		return false;
	}
	
}

