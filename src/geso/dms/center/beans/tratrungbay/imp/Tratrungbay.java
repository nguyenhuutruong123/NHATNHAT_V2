package geso.dms.center.beans.tratrungbay.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import geso.dms.center.beans.tratrungbay.ITratrungbay;
import geso.dms.center.beans.nhomsptrungbay.ISanpham;
import geso.dms.center.beans.nhomsptrungbay.imp.Sanpham;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

public class Tratrungbay implements ITratrungbay
{
	String userId;
	String id;
	String diengiai;
	String tongtien;
	String tongluong;
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
	
	public Tratrungbay(String[] param)
	{
		this.id = param[0];
		this.diengiai = param[1];
		this.tongtien = param[2];
		this.tongluong = param[3];
		this.type = param[4];
		this.hinhthuc = param[5];
		this.ngaytao = param[6];
		this.nguoitao = param[7];
		this.ngaysua = param[8];
		this.nguoisua = param[9];		
		this.msg = "";		
		this.nhomspId = "";
		
		sp_nhomspIds = new Hashtable<String, Integer>();
		spSudungList = new ArrayList<ISanpham>();
		db = new dbutils();
	}
	
	public Tratrungbay(String id)
	{
		this.id = id;
		this.diengiai = "";
		this.tongtien = "";
		this.tongluong = "";
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

	public boolean CreateTratb(String[] masp, String[] dongia, String[] soluong) 
	{
		//dbutils db = new dbutils();
		this.ngaytao = getDateTime();
		this.nguoitao = this.userId;
		
		if(this.type.equals("1")) //tra tien
			this.tongluong = "";
		if(this.type.equals("2"))
			this.tongtien = "";
		if(this.hinhthuc.equals("1"))
			this.tongluong = this.tongtien = "";
		if(this.hinhthuc == "")
			this.hinhthuc = "2";
		
		if(this.tongluong == "")
			this.tongluong = "null";
		if(this.tongtien == "")
			this.tongtien = "null";
				
		try 
		{
			this.db.getConnection().setAutoCommit(false);
				
			String query = "Insert into Tratrungbay(diengiai, tongluong, tongtien, loai, hinhthuc, ngaytao, nguoitao, ngaysua, nguoisua) values(";
			query = query + "N'" + this.diengiai + "', " + this.tongluong + ", " + this.tongtien + ", '"  + this.type + "', '" + this.hinhthuc + "', '" + this.ngaytao + "', '" + this.nguoitao + "', '" + this.ngaytao + "', '" + this.nguoitao + "')";
			
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception";
				return false; 
			}
			if(this.type.equals("2")) //tra sanpham
			{
				if(masp.length > 0)
				{
					String tratbCurrent = "";
					query = "select scope_identity() as tratbId";
					
					ResultSet rsTratb = this.db.get(query);						
					rsTratb.next();
					tratbCurrent = rsTratb.getString("tratbId");
					rsTratb.close();
					
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
								catch(Exception e) {}
							} 
							if(this.hinhthuc.equals("2"))
								soluong[i] = "null";
							if(pk_seq != "")
								query = "Insert into tratrungbay_sanpham(tratrungbay_fk, sanpham_fk, soluong, dongia) values('" + tratbCurrent + "', '" + pk_seq + "', " + soluong[i].trim() + ", '" + dongia[i].replaceAll(",", "") + "')";
							if(!this.db.update(query))
							{
								this.db.getConnection().rollback();
								this.msg = "Exception";
								return false; 
							}
						}					
					}
				}
			}
			this.db.getConnection().commit();
		} 
		catch(Exception e) {
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return false;
		} finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	public boolean UpdateTratb(String[] masp, String[] dongia, String[] soluong) 
	{
		//dbutils db = new dbutils();
		this.ngaysua = getDateTime();
		this.nguoisua = this.userId;
		
		if(this.type.equals("1")) //tra tien
			this.tongluong = "";
		if(this.type.equals("2"))
			this.tongtien = "";
		if(this.hinhthuc.equals("1"))
			this.tongluong = this.tongtien = "";
		if(this.hinhthuc == "")
			this.hinhthuc = "2";
		
		if(this.tongluong == "")
			this.tongluong = "null";
		if(this.tongtien == "")
			this.tongtien = "null";
				
		try 
		{
			if(checkExits(this.db))
			{
				this.msg = "Tr??? TB ???? ???????c s??? d???ng trong CTTB. B???n kh??ng th??? s???a l???i";
				return false;
			}
			
			this.db.getConnection().setAutoCommit(false);
				
			String query = "Update Tratrungbay set diengiai = N'" + this.diengiai + "', tongluong = " + this.tongluong + ", tongtien = " + this.tongtien + ", loai = '" + this.type + "', hinhthuc = '" + this.hinhthuc + "', ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.nguoisua + "' where pk_seq='" + this.id + "'";
		
			if(!this.db.update(query))
			{
				Utility.rollback_and_shutdown(db);
				this.msg = "Exception";
				return false; 
			}
			
			query = "delete from tratrungbay_sanpham where tratrungbay_fk = '" + this.id + "'";
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg = "Exception";
				return false; 
			}
			
			if(this.type.equals("2"))
			{
				if(masp.length > 0)
				{
					for(int i = 0; i < masp.length; i++)
					{
						if(masp[i].length() > 0)
						{
							ResultSet rs = this.db.get("select pk_seq from sanpham where ma='" + masp[i].trim() + "'");
							String pk_seq = "";
							if(rs != null) 
							{
								try
								{
									rs.next();
									pk_seq = rs.getString("pk_seq");
									rs.close();
								}
								catch(Exception e) {}
							} 
							if(this.hinhthuc.equals("2"))
								soluong[i] = "null";
							if(pk_seq != "")
								query = "Insert into tratrungbay_sanpham(tratrungbay_fk, sanpham_fk, soluong, dongia) values('" + this.id + "', '" + pk_seq + "', " + soluong[i].trim() + ", '" + dongia[i].replaceAll(",", "") + "')";
							if(!this.db.update(query))
							{
								this.db.getConnection().rollback();
								this.msg = "Exception";
								return false; 
							}
						}					
					}
				}
			}
			this.db.getConnection().commit();
		} 
		catch(Exception e) {
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return false;
		} finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
		return true;
	}

	public void createRS() 
	{
		Utility Ult = new Utility();
		this.nhomspRs = db.get("select pk_seq as nspId, diengiai as nspTen from nhomsanpham where type = '1' and trangthai='1' and pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in "+ Ult.quyen_sanpham(this.userId) +")");		
		if(this.nhomspId.length() > 0)
			this.createSpList();
	}
	
	private void createSpList()
	{
		String query = 
			" select b.pk_seq, b.MA, b.TEN, isnull(c.giabanlechuan, 0) as gblc from NHOMSANPHAM_SANPHAM a " +
			" inner join SANPHAM b on a.SP_FK = b.PK_SEQ " +
			/*" left join banggiablc_sanpham c on b.pk_seq = c.sanpham_fk ";*/
		    " left join banggiablc_sanpham c on c.SANPHAM_FK = b.PK_SEQ and c.BGBLC_FK = " +
		    " ( select top 1 pk_seq from banggiabanlechuan where trangthai = '1' order by tungay desc ) ";
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
					String dongia = rs.getString("gblc");
					sp = new Sanpham(spId, ma, ten, "", dongia);
					splist.add(sp);
				}
			} 
			catch(Exception e) {}
		}
		this.spList = splist;
	}
	
	public void createTratbSpList()
	{		
		String query = "select a.soluong, a.dongia, b.pk_seq, b.MA, b.TEN from tratrungbay_sanpham a inner join SANPHAM b on a.SanPham_FK = b.PK_SEQ where a.tratrungbay_fk = '" + this.id + "'";				
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
			catch(Exception e) {}
		}
		this.spSudungList = splist;
	}

	public void init() 
	{
		String query = "select a.PK_SEQ as tratbId, a.DIENGIAI, tongluong, tongtien, a.LOAI, a.HINHTHUC, a.NGAYTAO, a.NGAYSUA, b.TEN as nguoitao, c.TEN as nguoisua ";
		query = query + " from TRATRUNGBAY a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ where a.PK_SEQ='" + this.id + "'";
		ResultSet rs = db.get(query);
		try
        {
            rs.next();        	
            this.id = rs.getString("tratbId");
			this.diengiai = rs.getString("diengiai");
			if(rs.getString("tongluong") != null)
				this.tongluong = rs.getString("tongluong");
			if(rs.getString("tongtien") != null)
				this.tongtien = rs.getString("tongtien");
			
			this.type = rs.getString("loai");
			this.hinhthuc = rs.getString("hinhthuc");
			this.ngaytao = rs.getString("ngaytao");
			this.nguoitao = rs.getString("nguoitao");
			this.ngaysua = rs.getString("ngaysua");
			this.nguoisua = rs.getString("nguoisua");		
			rs.close();
       	}
        catch(Exception e){}
    	
        Utility Ult = new Utility();
		this.nhomspRs = db.get("select pk_seq as nspId, diengiai as nspTen from nhomsanpham where type = '1' and trangthai='1' and pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in "+ Ult.quyen_sanpham(this.userId) +")");		
	
        this.createTratbSpList();
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
	private boolean checkExits(dbutils db) 
	{
		ResultSet rs = db.get("select count(*) as sodong from CTTB_TRATB where TRATRUNGBAY_FK = '" + this.id + "'");
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

