package geso.dms.center.beans.Chenck.imp;

import geso.dms.center.beans.Chenck.Ichenck;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class chietkhau  implements Ichenck,Serializable{

	private String nppid;
	private String thang;
	private String quy;
	private String nam;
	private String boga;
	private String xanh;
	private String duno;
	private String ischenduno;
	private String ischenckthang;
	private String ischenckquy;
	private ResultSet Rsnppid;
	private String dh;
	private String msg;

	dbutils db;
	public chietkhau(){
		this.nppid="";
		this.thang="";
		this.quy="";
		this.nam="";
		this.boga="";
		this.xanh="";
		this.duno="";
		this.ischenduno="0";
		this.ischenckthang="1";
		this.ischenckquy="";
		this.dh="";
		this.db = new dbutils();
	}
	
	public void init()
	{
		String sql="select ten,PK_SEQ from NHAPHANPHOI where pk_seq!=1";
		this.Rsnppid=this.db.get(sql);
	}
	
	public void chenchietkhauquy(String dh,String npp)
	{
		try {
			db.getConnection().setAutoCommit(false);
			if(this.boga!=null && !this.boga.equals("0") && this.boga.length()>0)
			{
			String sql="insert DUYETTRAKHUYENMAI_DONHANG (duyetkm_fk, khachhang_fk, donhang_fk, thanhtoan, tichluyQUY, DIENGIAI, ptthue, HIENTHI) \n"+
					   " select '100011' duyetkm_fk , khachhang_fk, pk_seq, "+this.boga+"  , 1 as tichluyQuy, 'CQB5' as DienGiai, 5, 1 as Hienthi \n"+     
					   "	from DONHANG \n"+
					   "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
			System.out.println("cchen  boga"+sql);
				if(db.updateReturnInt(sql)<=0)
				{
					db.getConnection().rollback();
					this.msg="loi chen ck quy boga "+sql;
					System.out.println("loi boga" +sql);
					return;
				}
				 sql="insert DUYETTRAKHUYENMAI_DONHANG_LOG (duyetkm_fk, khachhang_fk, donhang_fk, thanhtoan, tichluyQUY, DIENGIAI, ptthue, HIENTHI) \n"+
						   " select '100011' duyetkm_fk , khachhang_fk, pk_seq, "+this.boga+"  , 1 as tichluyQuy, 'CQB5' as DienGiai, 5, 1 as Hienthi \n"+     
						   "	from DONHANG \n"+
						   "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
				System.out.println("cchen  boga"+sql);
					if(db.updateReturnInt(sql)<=0)
					{
						db.getConnection().rollback();
						this.msg="loi chen ck quy boga "+sql;
						System.out.println("loi boga" +sql);
						return;
					}
				
				
			}
			System.out.println("xan la "+this.xanh );
			if(this.xanh!=null && !this.xanh.equals("0") && this.xanh.length()>0)
			{
				String sql="insert DUYETTRAKHUYENMAI_DONHANG (duyetkm_fk, khachhang_fk, donhang_fk, thanhtoan, tichluyQUY, DIENGIAI, ptthue, HIENTHI) \n"+
						   " select '100011' duyetkm_fk , khachhang_fk, pk_seq, "+this.xanh+", 1 as tichluyQuy, 'CQX5' as DienGiai, 5, 1 as Hienthi \n"+     
						   "	from DONHANG \n"+
						   "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
				System.out.println("cchen  xanh"+sql);
				if(db.updateReturnInt(sql)<=0)
				{
					db.getConnection().rollback();
					this.msg="loi chen ck quy xanh "+sql;
					return;
				}
				
				 sql="insert DUYETTRAKHUYENMAI_DONHANG_LOG (duyetkm_fk, khachhang_fk, donhang_fk, thanhtoan, tichluyQUY, DIENGIAI, ptthue, HIENTHI) \n"+
						   " select '100011' duyetkm_fk , khachhang_fk, pk_seq, "+this.xanh+", 1 as tichluyQuy, 'CQX5' as DienGiai, 5, 1 as Hienthi \n"+     
						   "	from DONHANG \n"+
						   "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
				System.out.println("cchen  xanh"+sql);
				if(db.updateReturnInt(sql)<=0)
				{
					db.getConnection().rollback();
					this.msg="loi chen ck quy xanh "+sql;
					System.out.println("loi xanh" +sql);
					return;
				}
				
				
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			this.msg="Chèn chiết khấu quý cho đơn hàng "+dh+" thành công.";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	public void chenchietkhauthang(String dh,String npp)
	{
		
		try {
			db.getConnection().setAutoCommit(false);
		
		int thangnext=0;
		int namnext=0;
		if(Integer.parseInt(this.thang)==12)
		{
			thangnext=1;
			namnext=Integer.parseInt(this.nam)+1;
		}
		else
		{
			thangnext=Integer.parseInt(this.thang)+1;
			namnext=Integer.parseInt(this.nam);
		}
		
		String thangn=thangnext+"";
		String ngaythang="";
		System.out.println("thang======"+this.thang +"-----nam========="+this.nam);
		System.out.println("thang======"+thangnext +"-----nam========="+namnext);

		if(thangn.length()<2)
			ngaythang=namnext+"-0"+thangn;
		else
			ngaythang=namnext+"-"+thangn;
		System.out.println("thang======"+namnext +"-----nam========="+thangn+"------ngay thang:"+ngaythang);

		
		if((this.boga!=null && !this.boga.equals("0") && this.boga.length()>0 )||( this.xanh!=null && !this.xanh.equals("0") && this.xanh.length()>0))
		{
			float thanhtoan=0;
			if(!this.boga.equals(""))
				thanhtoan=thanhtoan+Float.parseFloat(this.boga);
			if(!this.xanh.equals(""))
				thanhtoan=thanhtoan+Float.parseFloat(this.xanh);
			
		String sql="insert DUYETTRAKHUYENMAI_DONHANG (duyetkm_fk, khachhang_fk, donhang_fk, thanhtoan, tichluyQUY, DIENGIAI, ptthue, HIENTHI,ngaythang) \n"+
				   " select '100011' duyetkm_fk , khachhang_fk, pk_seq, "+thanhtoan+"  , 0 as tichluyQuy, 'CT5' as DienGiai, 5, 0 as Hienthi,'"+ngaythang+"' \n"+     
				   "	from DONHANG \n"+
				   "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
		  
				if(db.updateReturnInt(sql)<=0)
				{
					db.getConnection().rollback();
					this.msg="loi chen ck thang "+sql;
					return;
				}
				
				 sql="update donhang set denghitraCK=1  \n"+
					 "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
				  
						if(db.updateReturnInt(sql)<=0)
						{
							db.getConnection().rollback();
							this.msg="loi chen ck thang "+sql;
							return;
						}
				
				 sql="insert DUYETTRAKHUYENMAI_DONHANG_LOG (duyetkm_fk, khachhang_fk, donhang_fk, thanhtoan, tichluyQUY, DIENGIAI, ptthue, HIENTHI,ngaythang) \n"+
						   " select '100011' duyetkm_fk , khachhang_fk, pk_seq, "+thanhtoan+"  , 0 as tichluyQuy, 'CT5' as DienGiai, 5, 0 as Hienthi,'"+ngaythang+"' \n"+     
						   "	from DONHANG \n"+
						   "		WHERE PK_SEQ ='"+dh+"' and npp_fk="+npp;
				  
						if(db.updateReturnInt(sql)<=0)
						{
							db.getConnection().rollback();
							this.msg="loi chen ck thang "+sql;
							return;
						}
				
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				this.msg="Chèn chiết khấu tháng cho đơn hàng "+dh+" thành công.";
		
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public String getDh() {
		return dh;
	}

	public void setDh(String dh) {
		this.dh = dh;
	}
	
	public ResultSet getRsnppid() {
		return Rsnppid;
	}
	public void setRsnppid(ResultSet rsnppid) {
		Rsnppid = rsnppid;
	}
	public String getNppid() {
		return nppid;
	}
	public void setNppid(String nppid) {
		this.nppid = nppid;
	}
	public String getThang() {
		return thang;
	}
	public void setThang(String thang) {
		this.thang = thang;
	}
	public String getQuy() {
		return quy;
	}
	public void setQuy(String quy) {
		this.quy = quy;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public String getBoga() {
		return boga;
	}
	public void setBoga(String boga) {
		this.boga = boga;
	}
	public String getXanh() {
		return xanh;
	}
	public void setXanh(String xanh) {
		this.xanh = xanh;
	}
	public String getDuno() {
		return duno;
	}
	public void setDuno(String duno) {
		this.duno = duno;
	}
	public String getIschenduno() {
		return ischenduno;
	}
	public void setIschenduno(String ischenduno) {
		this.ischenduno = ischenduno;
	}
	public String getIschenckthang() {
		return ischenckthang;
	}
	public void setIschenckthang(String ischenckthang) {
		this.ischenckthang = ischenckthang;
	}
	public String getIschenckquy() {
		return ischenckquy;
	}
	public void setIschenckquy(String ischenckquy) {
		this.ischenckquy = ischenckquy;
	}
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
