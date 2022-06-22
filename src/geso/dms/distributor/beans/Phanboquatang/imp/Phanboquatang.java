package geso.dms.distributor.beans.Phanboquatang.imp;

import geso.dms.distributor.beans.Phanboquatang.IPhanboquatang;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.db.sql.*;
import geso.dms.center.util.Utility;
import geso.dms.center.util.UtilitySyn;
public class Phanboquatang implements IPhanboquatang
{
	private static final long serialVersionUID = -9217977546733610214L;
	String userId;
	String id;
	String Ma;
	String diengiai;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String tungay;
	String denngay;
	String sanphamid;
	String msg;
	dbutils db;
	String[] ddkdidArr,soluongArr;
	String nppid;
	String ddkdid;
	String soluong;
	ResultSet rssanpham;
	ResultSet rsddkd;
	public ResultSet getRsddkd() {
		return rsddkd;
	}


	public void setRsddkd(ResultSet rsddkd) {
		this.rsddkd = rsddkd;
	}


	public ResultSet getRssanpham() {
		return rssanpham;
	}


	public void setRssanpham(ResultSet rssanpham) {
		this.rssanpham = rssanpham;
	}


	public String getSoluong() {
		return soluong;
	}


	public void setSoluong(String soluong) {
		this.soluong = soluong;
	}


	public String getDdkdid() {
		return ddkdid;
	}


	public void setDdkdid(String ddkdid) {
		this.ddkdid = ddkdid;
	}

	
	
	public String getNppid() {
		return nppid;
	}


	public void setNppid(String nppid) {
		this.nppid = nppid;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getMa() {
		return Ma;
	}


	public void setMa(String ma) {
		Ma = ma;
	}


	public String getDiengiai() {
		return diengiai;
	}


	public void setDiengiai(String diengiai) {
		this.diengiai = diengiai;
	}


	public String getTrangthai() {
		return trangthai;
	}


	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}


	public String getNgaytao() {
		return ngaytao;
	}


	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}


	public String getNguoitao() {
		return nguoitao;
	}


	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}


	public String getNgaysua() {
		return ngaysua;
	}


	public void setNgaysua(String ngaysua) {
		this.ngaysua = ngaysua;
	}


	public String getNguoisua() {
		return nguoisua;
	}


	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}


	public String getTungay() {
		return tungay;
	}


	public void setTungay(String tungay) {
		this.tungay = tungay;
	}


	public String getDenngay() {
		return denngay;
	}


	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}


	public String getSanphamid() {
		return sanphamid;
	}


	public void setSanphamid(String sanphamid) {
		this.sanphamid = sanphamid;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public dbutils getDb() {
		return db;
	}


	public void setDb(dbutils db) {
		this.db = db;
	}


	public String[] getDdkdidArr() {
		return ddkdidArr;
	}


	public void setDdkdidArr(String[] ddkdidArr) {
		this.ddkdidArr = ddkdidArr;
	}


	public String[] getSoluongArr() {
		return soluongArr;
	}


	public void setSoluongArr(String[] soluongArr) {
		this.soluongArr = soluongArr;
	}
	
	
	public Phanboquatang()
	{
		this.id 		= "";
		this.Ma 		= "";
		this.diengiai	= "";
		this.trangthai 	= "";
		this.ngaytao 	= "";
		this.nguoitao 	= "";
		this.ngaysua 	= "";
		this.nguoisua 	= "";
		this.msg = "";
		this.tungay="";
		this.denngay="";
		this.sanphamid="";
		this.db = new dbutils();
		
	}

	
	public boolean Createphanbo(HttpServletRequest request) 
	{
	
		try{
			getNppInfo();
			String quString="select count(*) sl from phanbo_quatang where Ma=N'"+this.Ma+"'";
			ResultSet rss=db.get(quString);
			rss.next();
			int sl=rss.getInt("sl");
			rss.close();
			if(sl>0)
			{
				this.msg = " Mã chương trình đã tồn tại ";
				return false;
			}
			
			this.db.getConnection().setAutoCommit(false);
			 quString="insert into phanbo_quatang (trangthai,nguoitao,nguoisua,Diengiai,tungay,denngay,sanpham_fk,ma,npp_fk)"+
					" select '"+trangthai+"',N'"+this.nguoitao+"',N'"+this.nguoisua+"',N'"+this.diengiai+"','"+this.tungay+"','"+this.denngay+"',"+this.sanphamid+",N'"+this.Ma+"',"+this.nppid+" ";
			if(db.updateReturnInt(quString)<=0)
			{
				db.getConnection().rollback();
				this.msg = " lỗi trong quá trình lưu !!!"+quString;
				return false;		
			}
			String query = "select  SCOPE_IDENTITY() as khId";
			ResultSet rs = this.db.get(query);
			rs.next();
			this.id = rs.getString("khId");
			rs.close();
			
			if(this.ddkdidArr!=null &&  (this.soluongArr!=null))
			{
				for(int i=0;i<this.ddkdidArr.length;i++)
				{
					if(Integer.parseInt(this.soluongArr[i])>0)
					{
						quString="insert into phanbo_quatang_ddkd (phanbo_fk,ddkd_fk,soluong,sanpham_fk)"+
								" select '"+id+"',N'"+this.ddkdidArr[i]+"',"+this.soluongArr[i]+","+this.sanphamid+" ";
						if(db.updateReturnInt(quString)<=0)
						{
							db.getConnection().rollback();
							this.msg = " lỗi trong quá trình lưu !!!";
							return false;		
						}
					}
					
				}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}catch(Exception e){
			
			return false;
		}
		return false;
	}

	public boolean Updatephanbo(HttpServletRequest request) 
	{
	
		try{
			getNppInfo();
			String quString="select count(*) sl from phanbo_quatang where Ma=N'"+this.Ma+"' and pk_seq<>"+this.id;
			ResultSet rss=db.get(quString);
			rss.next();
			int sl=rss.getInt("sl");
			rss.close();
			if(sl>0)
			{
				this.msg = " Mã chương trình đã tồn tại ";
				return false;
			}
			
			this.db.getConnection().setAutoCommit(false);
			 quString="update  phanbo_quatang "+
					" set nguoisua=N'"+this.nguoisua+"',diengiai=N'"+this.diengiai+"',tungay='"+this.tungay+"',denngay='"+this.denngay+"',sanpham_fk="+this.sanphamid+",Ma=N'"+this.Ma+"',npp_fk="+this.nppid+" where pk_Seq="+this.id+" ";
			if(db.updateReturnInt(quString)<=0)
			{
				db.getConnection().rollback();
				this.msg = " lỗi trong quá trình lưu !!!"+quString;
				return false;		
			}
			
			quString="delete from phanbo_quatang_ddkd where phanbo_fk="+this.id;
			
			if(db.updateReturnInt(quString)<=0)
			{
				db.getConnection().rollback();
				this.msg = " lỗi trong quá trình lưu !!!";
				return false;		
			}
			
			if(this.ddkdidArr!=null &&  (this.soluongArr!=null))
			{
				for(int i=0;i<this.ddkdidArr.length;i++)
				{
					if(Integer.parseInt(this.soluongArr[i])>0)
					{
						quString="insert into phanbo_quatang_ddkd (phanbo_fk,ddkd_fk,soluong,sanpham_fk)"+
								" select '"+id+"',N'"+this.ddkdidArr[i]+"',"+this.soluongArr[i]+","+this.sanphamid+" ";
						if(db.updateReturnInt(quString)<=0)
						{
							db.getConnection().rollback();
							this.msg = " lỗi trong quá trình lưu !!!";
							return false;		
						}
					}
					
				}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}catch(Exception e){
			
			return false;
		}
		return false;
	}

	
	public void createRS()
	{
		getNppInfo();
		String query="select pk_seq,ten from sanpham  ";
		this.rssanpham=this.db.get(query);
		if(this.id==null || this.id.trim().length()==0)
		{
			query="select a.pk_seq,a.ten,0 as soluong from daidienkinhdoanh a inner join DAIDIENKINHDOANH_NPP b on a.pk_seq=b.ddkd_fk where b.npp_fk="+this.nppid;
			this.rsddkd=db.get(query);
		}
		else
		{
			query=" select pk_seq,ten, sum(soluong ) as soluong from("+
					"select a.pk_seq,a.ten,0 as soluong from daidienkinhdoanh a inner join DAIDIENKINHDOANH_NPP b on a.pk_seq=b.ddkd_fk where b.npp_fk="+this.nppid+
				  " union all  select b.pk_seq,b.ten,a.soluong from phanbo_quatang_ddkd a inner join daidienkinhdoanh b on a.ddkd_fk=b.pk_seq where a.phanbo_fk="+this.id+" "+
				  " ) as data group by pk_seq,ten";
				  	
			this.rsddkd=db.get(query);
			
			query="select trangthai,nguoitao,nguoisua,Diengiai,tungay,denngay,sanpham_fk,ma,npp_fk from phanbo_quatang where pk_Seq="+this.id;
			ResultSet rs=db.get(query);
			try {
				rs.next();
				this.Ma=rs.getString("MA");
				this.tungay=rs.getString("tungay");
				this.denngay=rs.getString("denngay");
				this.sanphamid=rs.getString("sanpham_fk");
				this.diengiai=rs.getString("diengiai");
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("query la "+query);
	}
	
	public void init()
	{
		
		
	}

	private void getNppInfo()
	{		
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();	
			try
			{
				this.nppid=util.getIdNhapp(this.userId);
			}
			catch(Exception ex){}
		
		
	}

}


