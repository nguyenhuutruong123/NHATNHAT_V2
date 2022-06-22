package geso.dms.center.beans.BCdoanhsohopdong.imp;

import java.sql.ResultSet;

import geso.dms.center.beans.BCdoanhsohopdong.IBCdoanhsohopdong;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class BCdoanhsohopdong implements IBCdoanhsohopdong {

	String nppId;
	

	String tungay;
	String denngay;
	
	ResultSet npp;
	
	dbutils db;
	
	
	private String status;
	public BCdoanhsohopdong()
	{	
		this.status = "";
		this.nppId ="";
		this.tungay = "";
		this.denngay = "";
		db = new dbutils();
	}
	
	String loaiNv,phanloai,userId;
	public String getLoaiNv()
  {
  	return loaiNv;
  }

	public void setLoaiNv(String loaiNv)
  {
  	this.loaiNv = loaiNv;
  }

	public String getPhanloai()
  {
  	return phanloai;
  }

	public void setPhanloai(String phanloai)
  {
  	this.phanloai = phanloai;
  }

	public String getUserId()
  {
  	return userId;
  }

	public void setUserId(String userId)
  {
  	this.userId = userId;
  }
	
	
	public void setnppId(String nppId) {
		
		this.nppId = nppId;
	}

	
	public String getnppId() {
		
		return this.nppId;
	}

	
	
	
	
	
	public ResultSet getnpp() {
		
		return this.npp;
	}

	
	
	
	Utility Ult = new Utility();
	private String nppTen;
	public void init() 
	{
		String sql = "";
		
		Utility Ult = new Utility();		
		try
		{

			sql = "select phanloai,LOAI from nhanvien where pk_seq=" + this.userId;
			ResultSet rs = this.db.get(sql);
			if (rs != null)
			{
				if (rs.next())
				{
					this.phanloai = rs.getString("phanloai");
					loaiNv= rs.getString("LOAI")==null?"":rs.getString("LOAI");

					if (rs.getString("phanloai").equals("1")||( this.phanloai.equals("2")   && loaiNv.equals("3")   )  )
					{
						this.nppId = Ult.getIdNhapp(this.userId);
						this.nppTen = Ult.getTenNhaPP();
						//this.tructhuoc_fk=Ult.getTructhuoc_fk();
					}
					rs.close();
				}
			}
			System.out.println("sql : " + sql);
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		
	


		
	}


	
	


	public void DBclose() {
		// TODO Auto-generated method stub
		try {
			if(this.db != null)
				this.db.shutDown();
		
			if(this.npp != null)
				this.npp.close();
			
			
				
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	
	public void createNPP() {
		// TODO Auto-generated method stub
		
		//System.out.println("nhapp: select * from nhaphanphoi where khuvuc_fk = '"+this.khuvucId+"' order by ten");
	}

	
	public String getStatus() {
		// TODO Auto-generated method stub
		return this.status;
	}


	public void setStatus(String status) {
		// TODO Auto-generated method stub
		this.status = status;
	}

	
	public String getTungay(){
		return this.tungay;
	}
	
	public void setTungay(String tungay){
		this.tungay = tungay;
	}

	public String getDenngay(){
		return this.denngay;
	}
	
	public void setDenngay(String denngay){
		this.denngay = denngay;
	}

	
	
	
}
