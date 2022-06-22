package geso.dms.erp.beans.thutien.imp;

import geso.dms.erp.beans.thutien.IDinhkhoanco;
import geso.dms.center.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dinhkhoanco implements IDinhkhoanco
{
	private static final long serialVersionUID = -9217977546733610214L;
	
	String taikhoanId;
	String doituongId;
	
	String doituongdinhkhoan;
	String sotien;
	String sotienNT;

	dbutils db;
	
	public Dinhkhoanco()
	{
		this.taikhoanId = "";
		this.doituongId ="";
		
		this.doituongdinhkhoan = "";
		this.sotien = "";
		this.sotienNT ="";
		
	}

	public Dinhkhoanco(String taikhoanId, String doituongdinhkhoan,String doituongId, String sotienNT, String sotien)
	{
		this.taikhoanId = taikhoanId;
		this.doituongId = doituongId;		
		this.doituongdinhkhoan = doituongdinhkhoan;
		this.sotienNT = sotienNT;
		this.sotien = sotien;
	}

	public String getTaikhoanId()
	{
		return this.taikhoanId ;
	}

	public void setTaikhoanId(String taikhoanId) 
	{
		this.taikhoanId = taikhoanId ;
	}


	public String getDoituongId()
	{
		return this.doituongId ;
	}

	public void setDoituongId(String doituongId) 
	{
		this.doituongId = doituongId;
	}

	public String getDoituongdinhkhoan() 
	{
		return this.doituongdinhkhoan ;
	}

	public void setDoituongdinhkhoan(String doituongdinhkhoan) 
	{
		this.doituongdinhkhoan = doituongdinhkhoan ;
	}

	public String getSotien() 
	{
		return this.sotien ;
	}

	public void setSotien(String sotien) 
	{
		this.sotien = sotien;
	}

	public String CheckDoiTuongDinhKhoan(String dinhkhoanco) {
		db = new dbutils();
		
	
		String dungchokho = "";
		String dungchonganhang = "";
		String dungchoncc = "";
		String dungchotaisan = "";
		String dungchokhachhang = "";
		String dungchonhanvien = "";
		
		String query= "";
		this.doituongdinhkhoan = "0";
		
		if(this.taikhoanId.trim().length() > 0)
		{
			query = "Select * from ERP_TAIKHOANKT where  PK_SEQ = '" + dinhkhoanco +"'";
			System.out.println(query);
			ResultSet dtrs = this.db.get(query);
			try {
				while(dtrs.next())
				{
					dungchokho = dtrs.getString("dungchokho");
					dungchonganhang = dtrs.getString("dungchonganhang");
					dungchoncc = dtrs.getString("dungchoncc");
					dungchotaisan = dtrs.getString("dungchotaisan");
					dungchokhachhang = dtrs.getString("dungchokhachhang");
					dungchonhanvien = dtrs.getString("dungchonhanvien");				
				}
				dtrs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			if(dungchokho.equals("1"))
			{
				this.doituongdinhkhoan = "1";
			}
			else if(dungchonganhang.equals("1"))
			{
				this.doituongdinhkhoan = "2";
			}
			else if(dungchoncc.equals("1"))
			{
				this.doituongdinhkhoan = "3";
			}
			else if(dungchotaisan.equals("1"))
			{
				this.doituongdinhkhoan = "4";
			}
			else if(dungchokhachhang.equals("1"))
			{
				this.doituongdinhkhoan = "5";
			}
			else if(dungchonhanvien.equals("1"))
			{
				this.doituongdinhkhoan = "6";
			}
		}
		return this.doituongdinhkhoan;
	}
	
	public String getSotienNT() 
	{
		return this.sotienNT ;
	}

	public void setSotienNT(String sotienNT) 
	{
		this.sotienNT = sotienNT;
	}

}
