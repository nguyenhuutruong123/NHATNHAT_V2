package geso.dms.center.beans.tieuchithuong.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import geso.dms.center.beans.ctkhuyenmai.imp.Ctkhuyenmai;
import geso.dms.center.beans.tieuchithuong.ITichLuyItem;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTL;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class TieuchithuongTL implements ITieuchithuongTL 
{
	String userId;
	String id;
	String scheme;
	String thang;
	String nam;
	String diengiai;
	String kt = "0";
	ResultSet sanphamRs;
	String spIds;
	ResultSet nppRs;
	
	String active = "0";
	ResultSet kenhRs;
	String kenhIds;
	ResultSet vungRs;
	String vungIds;
	ResultSet kvRs;
	String kvIds;
	String mucnpp = "0";
	ResultSet nhomsanphamRs;
	String nhomspIds;	
	String ghighu = "";
	
	Hashtable<String, String> maspTraTT = new Hashtable<String, String>();;
	Hashtable<String, String> tenspTraTT = new Hashtable<String, String>();;
	Hashtable<String, String> soluongTT = new Hashtable<String, String>();;
	
	String doanhsotheoThung;
	String msg;
	String httt;
	String ptchietkhau;	
	String tungay;
	String dengay;
	String khoId;
	ResultSet khoRs;	
	String phanloai;
	
	dbutils db;
	
	String[] DKKMTICHLUY_KHACHHANG_Id;	
	String[] khDcDuyet;
	String[] khDcDuyetDisplay;
	ResultSet khDangKyRs;	
	String ngaytb_tungay;
	String ngaytb_dengay;	
	String phaidangky;
	String tinhtheosl;
	Utility  util =new Utility();	
	ArrayList<ITichLuyItem> tichluyItemList = new ArrayList();
	
	ArrayList<String> spMuaList = new ArrayList();
	public ResultSet sanphamMuaRs ;
	
	public void setSanphamMuaRs() {
		String data = "";
		for(int i = 0; i < spMuaList.size(); i++ )
		{			
			
			if(spMuaList.get(i).contains("#_#_#"))
			{
				String[] split =  spMuaList.get(i).split("#_#_#") ;
				
				if(data.trim().length() > 0 )
					data +=" union all select  '"+split[0]+"' ma , '"+split[1]+"'  sl "  ;
				else
					data= "select  '"+split[0]+"' ma , '"+split[1]+"'  sl  ";
			}
			
			
		}
		if(data.trim().length() > 0)
		{
			String query =  "\n select sp.pk_seq,sp.ma,sp.ten,dt.sl " +
							"\n from sanpham sp " +
							"\n inner join " +
							"\n (" +
							data +
							"\n )dt on dt.ma = sp.ma";
			
			this.sanphamMuaRs = db.get(query);
			
		}
		
	}
	public ResultSet getSanphamMuaRs() {
		return sanphamMuaRs;
	}
	public ArrayList<String> getSpMuaList() {
		return spMuaList;
	}
	public void setSpMuaList(ArrayList<String> spMuaList) {
		this.spMuaList = spMuaList;
	}
	
	
	public TieuchithuongTL()
	{
		this.id = "";
		this.thang = "";
		this.nam = "";
		this.diengiai = "";
		this.msg = "";
		this.scheme = "";
		this.spIds = "";
		
		
		this.vungIds = "";
		this.kvIds = "";
		this.kenhIds = "";
		
		this.doanhsotheoThung = "0";
		this.httt = "0";
		this.ptchietkhau = "0";
	
		this.tungay = "";
		this.dengay = "";
		this.khoId = "";
		this.phanloai = "0";
		this.nhomspIds = "";
		this.mucnpp = "0";
		this.phaidangky = "0";
		
		this.ngaytb_tungay = "";
		this.ngaytb_dengay = "";
		this.tinhtheosl = "0";
		this.db = new dbutils();
	}
	
	public TieuchithuongTL(String id)
	{
		this.id = id;
		this.thang = "";
		this.nam = "";
		this.diengiai = "";
		this.msg = "";
		this.scheme = "";
		this.spIds = "";
	
		
		
		
		this.vungIds = "";
		this.kvIds = "";
		this.kenhIds = "";
		
		
		this.doanhsotheoThung = "0";
		this.httt = "0";
		this.ptchietkhau = "0";
		
	
		this.mucnpp = "0";
		
		this.tungay = "";
		this.dengay = "";
		this.khoId = "";
		this.phanloai = "0";
		this.nhomspIds = "";
		this.phaidangky = "0";
		this.tinhtheosl = "0";
		this.ngaytb_tungay = "";
		this.ngaytb_dengay = "";
		
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

	public String getThang() 
	{
		return this.thang;
	}
	
	public void setThang(String thang) 
	{
		this.thang = thang;
	}
	
	public String getNam() 
	{
		return this.nam;
	}
	
	public void setNam(String nam)
	{
		this.nam = nam;
	}

	public String getId() 
	{
		return this.id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getDiengiai()
	{
		return this.diengiai;
	}

	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}

	public String getMsg()
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public boolean createTctSKU( ) 
	{
		try
		{
			

			Object[] data;
			
			int[] quyen = util.Getquyen("TieuchithuongTLSvl", "",this.userId);
			if(quyen[Utility.THEM]!=1)
			{
				this.msg = "User kh??ng ???????c ph??n quy???n s???a";
				return false;
			}
			
		
			
			
			if(this.thang.trim().length() <= 0)
			{
				this.msg = "Vui l??ng ch???n ng??y b???t ?????u";
				return false;
			}
			
			if(this.nam.trim().length() <= 0)
			{
				this.msg = "Vui l??ng ch???n ng??y k???t th??c";
				return false;
			}
			
			if(this.scheme.trim().length() <= 0)
			{
				this.msg = "Vui l??ng scheme";
				return false;
			}
			
			//Check Scheme
			String query = "select count(*) as sodong from TIEUCHITHUONGTL where scheme = '"+this.scheme+"' ";
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				int count = 0;
				if(rs.next())
				{
					count = rs.getInt("sodong");
					if(count > 0)
					{
						this.msg = "Scheme ???? t???n t???i, vui l??ng nh???p l???i";
						rs.close();
						return false;
					}
				}
				rs.close();
			}
			
			//Check tieu chi
			if(this.tichluyItemList.size()<=0)
			{
				this.msg = "Vui l??ng nh???p ti??u ch??";
				return false;
			}
			
			double tuMin = -1;
			for(int i = 0; i < this.tichluyItemList.size(); i++)
			{
				 ITichLuyItem tl = this.tichluyItemList.get(i);
				 if( tl.getTumuc() < tuMin )
				 {
					 this.msg = "Doanh s??? t??? m???c c???a "+tl.getDiengiai()+" ph???i l???n h??n ("+ format.format(tuMin) +")";
					 return false;
				 }
				
				 if( tl.getDenmuc() <= tl.getTumuc() )
				 {
					 this.msg = "Doanh s??? ?????n m???c c???a "+tl.getDiengiai()+" ph???i l???n h??n ("+ format.format(tl.getTumuc()) +")";
					 return false;
				 }
				 tuMin =  tl.getDenmuc();
				 if(tl.getLoaitra() ==2  )
				 {
					 if(tl.getSpList().size() <=0)
					 {
						 this.msg = "Vui l??ng ch???n s???n ph???m tr??? c???a "+tl.getDiengiai();
						 return false;
					 }
				 }
				 
				 if(tl.getTratichluy() <=0 )
				 {
					 if(tl.getLoaitra() == 0 || tl.getLoaitra() == 1) // tr??? ti??n hoac ck
					 {
						 this.msg = "Vui l??ng nh???p tr??? t??ch l??y l???n h??n 0 c???a "+tl.getDiengiai();
						 return false;
					 }
					 if(tl.getLoaitra() ==2  )// tr??? sp v?? b???t k??? trong
					 {
						 if(tl.getHinhthuc() == 0)// tr??? b???t k??? trong
						 {
							 this.msg = "Vui l??ng nh???p t???ng l?????ng tr??? cho s???n ph???m c???a "+tl.getDiengiai();
							 return false;
						 }
						 if(tl.getHinhthuc() == 1) // tra b???t bu???c
						 {
							 for(int x = 0 ; x< tl.getSpList().size();x++)
							 {
								 String matra= tl.getSpList().get(x)[0].toString();
								 double slbatbuoc = Utility.parseDouble(tl.getSpList().get(x)[1].toString());
								 if( slbatbuoc  <=0 )
								 {
									 this.msg = "Vui l??ng nh???p s??? l?????ng tr??? cho s???n ph???m "+matra +", c???a "+tl.getDiengiai();
									 return false;
								 }
							 }
						 }
					 }
					 
				 }
					 
			}
			if(this.spMuaList.size() <= 0)
			{
				this.msg = "Vui l??ng ch???n s???n ph???m trong ??i???u ki???n mua";
				return false;
			}
		
		
			if(this.khoId.trim().length() <= 0)
			{
				this.msg = "Vui l??ng ch???n kho ??p d???ng";
				return false;
			}
			
			if(!Ctkhuyenmai.kiemtra_scheme(this.db,this.scheme,""))
			{		
				this.msg = "Scheme khuy???n m??i/t??ch l??y ???? t???n t???i, vui l??ng nh???p l???i";
				return false;
			}
			
			
			db.getConnection().setAutoCommit(false);
			
			
			query = "insert TieuchithuongTL(scheme, thang, nam, diengiai, trangthai, ngaytao, nguoitao, ngaysua, nguoisua, ngayds_tungay, ngayds_denngay, khoId, phanloai ,ghichu, DOANHSOTHEOLUONG) " +
				"select '"+this.scheme+"','"+this.thang+"','"+this.nam+"',N'"+this.diengiai+"',0, '" + this.getDateTime() + "', "+this.userId+", '" + this.getDateTime() + "', "+this.userId+"" +
						", '"+this.tungay+"','"+this.dengay+"', "+this.khoId+",0 ,N'"+ this.ghighu+"',"+ this.tinhtheosl;
			
			
			
			if(db.updateReturnInt(query) <= 0)
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			
			rs = db.get( " select scope_identity() id ");   
			rs.next();
			this.id= rs.getString("id");
			
			query = " insert CTKHUYENMAI(scheme,DIENGIAI ,thuongtl_fk,kho_fk) " +
			" select scheme,DIENGIAI,PK_SEQ,khoId from TIEUCHITHUONGTL where pk_seq= " + this.id;
			if(db.updateReturnInt(query) <= 0)
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			/*
			 * String
			 * querycheck="select count(*) as sl from TieuchithuongTL where nam<'"+this.
			 * getDateTime()+"' and pk_seq="+this.id+""; ResultSet
			 * checkRs=db.get(querycheck); checkRs.next(); if(checkRs.getInt("sl")>0) {
			 * this.msg = "?????n ng??y ????ng k?? kh??ng ???????c nh??? h??n ng??y hi???n t???i!";
			 * db.getConnection().rollback(); return false; }
			 */
			String  querycheck="select count(*) as sl from TieuchithuongTL where nam<thang and pk_seq="+this.id+"";
			ResultSet checkRs=db.get(querycheck);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				this.msg = "?????n ng??y ????ng k?? kh??ng ???????c nh??? h??n t??? ng??y ????ng k??!";
				db.getConnection().rollback();
				return false;
			}
			checkRs.close();
			 querycheck="select count(*) as sl from TieuchithuongTL where ngayds_tungay>ngayds_denngay and pk_seq="+this.id+"";
			 checkRs=db.get(querycheck);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				this.msg = "?????n ng??y DS  kh??ng ???????c nh??? h??n t??? ng??y DS!";
				db.getConnection().rollback();
				return false;
			}
			checkRs.close();
			for(int i = 0; i < this.tichluyItemList.size(); i++)
			{
				 ITichLuyItem tl = this.tichluyItemList.get(i);	 
				
				 query = " insert TieuchithuongTL_TIEUCHI(thuongtl_fk,  tumuc, denmuc, chietkhau, donvi, muc,hinhthuc) " +
						 " select '" + this.id + "' as tctId,"+tl.getTumuc()+","+tl.getDenmuc()+","+tl.getTratichluy()+","+tl.getLoaitra()+","+i+","+tl.getHinhthuc()+" ";
				 System.out.println(query);
				 	
				 	if(db.updateReturnInt(query)<=0 )
					{
						this.msg = "Exception";
						db.getConnection().rollback();
						return false;
					}			
					if(tl.getLoaitra() == 2)
					{
						List<Object> dataTra = new ArrayList<Object>();
						String sqlSpTra = "";
						for(int x = 0 ; x < tl.getSpList().size(); x++ )
						{
							Object[] spX = tl.getSpList().get(x);
							if(sqlSpTra.trim().length() > 0)
								sqlSpTra += "\n union all select "+this.id+", pk_seq ,'"+spX[1].toString()+"',"+i+","+tl.getHinhthuc()+" from sanpham where ma = '"+spX[0]+"' ";
							else
								sqlSpTra += " select "+this.id+", pk_seq ,'"+spX[1].toString()+"',"+i+","+tl.getHinhthuc()+" from sanpham where ma = '"+spX[0]+"'  ";
							
						
							
						}
						query = " insert TIEUCHITHUONGTL_SPTRA( thuongtl_fk, sanpham_fk, soluong, muctra, hinhthuctra ) " + sqlSpTra;
						if(db.updateReturnInt(query)<=0 )
						{
							this.msg = "Exception";
							db.getConnection().rollback();
							return false;
						}		
					}
					
			}
			
			
			for(int i = 0; i < this.spMuaList.size(); i ++)
			{
				if(spMuaList.get(i).contains("#_#_#"))
				{
					String[] split =  spMuaList.get(i).split("#_#_#") ;
					
					
					
					query = "Insert TIEUCHITHUONGTL_SANPHAM(thuongtl_fk, sanpham_fk,quydoi) select '" + this.id + "', pk_seq,'"+split[1]+"' " +
					"from SanPham where ma = '"+split[0]+"' ";
		
					if(db.updateReturnInt(query)<=0 )
					{
						this.msg = "Exception";
						db.getConnection().rollback();
						return false;
					}
					
				}
				
				
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;

		} 
		catch (Exception e)
		{
			try 
			{
				db.getConnection().rollback();
				System.out.println("__EXCEPTION UPDATE: " + e.getMessage());
				e.printStackTrace();
				this.msg = "L???i khi t???o m???i CT t??ch l??y";
			} 
			catch (SQLException e1) {}
			
			return false;
		}
		
		
	}
	NumberFormat format = new DecimalFormat("#,###,###");
	public boolean updateTctSKU()
	{
		try
		{
			Object[] data;
			
			int[] quyen = util.Getquyen("TieuchithuongTLSvl", "",this.userId);
			if(quyen[Utility.SUA]!=1)
			{
				this.msg = "User kh??ng ???????c ph??n quy???n s???a";
				return false;
			}
			
		/*	if(!util.KiemTra_PK_SEQ_HopLe(this.id,"TIEUCHITHUONGTL", db))
			{
				this.msg =" Id kh??ng h???p l???!";
				return false;
			}
			*/
			
			if(this.thang.trim().length() <= 0)
			{
				this.msg = "Vui l??ng ch???n ng??y b???t ?????u";
				return false;
			}
			
			if(this.nam.trim().length() <= 0)
			{
				this.msg = "Vui l??ng ch???n ng??y k???t th??c";
				return false;
			}
			
			if(this.scheme.trim().length() <= 0)
			{
				this.msg = "Vui l??ng scheme";
				return false;
			}
			
			//Check Scheme
			String query = "select count(*) as sodong from TIEUCHITHUONGTL where scheme ='"+this.scheme+"'  and pk_seq != '" + this.id + "'";
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				int count = 0;
				if(rs.next())
				{
					count = rs.getInt("sodong");
					if(count > 0)
					{
						this.msg = "Scheme ???? t???n t???i, vui l??ng nh???p l???i";
						rs.close();
						return false;
					}
				}
				rs.close();
			}
			
			//Check tieu chi
			if(this.tichluyItemList.size()<=0)
			{
				this.msg = "Vui l??ng nh???p ti??u ch??";
				return false;
			}
			
			double tuMin = -1;
			for(int i = 0; i < this.tichluyItemList.size(); i++)
			{
				 ITichLuyItem tl = this.tichluyItemList.get(i);
				 if( tl.getTumuc() < tuMin )
				 {
					 this.msg = "Doanh s??? t??? m???c c???a "+tl.getDiengiai()+" ph???i l???n h??n ("+ format.format(tuMin) +")";
					 return false;
				 }
				
				 if( tl.getDenmuc() <= tl.getTumuc() )
				 {
					 this.msg = "Doanh s??? ?????n m???c c???a "+tl.getDiengiai()+" ph???i l???n h??n ("+ format.format(tl.getTumuc()) +")";
					 return false;
				 }
				 tuMin =  tl.getDenmuc();
				 if(tl.getLoaitra() ==2  )
				 {
					 if(tl.getSpList().size() <=0)
					 {
						 this.msg = "Vui l??ng ch???n s???n ph???m tr??? c???a "+tl.getDiengiai()+" ";
						 return false;
					 }
				 }
				 
				 if(tl.getTratichluy() <=0 )
				 {
					 if(tl.getLoaitra() == 0 || tl.getLoaitra() == 1) // tr??? ti??n hoac ck
					 {
						 this.msg = "Vui l??ng nh???p tr??? t??ch l??y l???n h??n 0 c???a "+tl.getDiengiai();
						 return false;
					 }
					 if(tl.getLoaitra() ==2  )// tr??? sp v?? b???t k??? trong
					 {
						 if(tl.getHinhthuc() == 0)// tr??? b???t k??? trong
						 {
							 this.msg = "Vui l??ng nh???p t???ng l?????ng tr??? cho s???n ph???m c???a "+tl.getDiengiai()+" ";
							 return false;
						 }
						 if(tl.getHinhthuc() == 1) // tra b???t bu???c
						 {
							 for(int x = 0 ; x< tl.getSpList().size();x++)
							 {
								 String matra= tl.getSpList().get(x)[0].toString();
								 double slbatbuoc = Utility.parseDouble(tl.getSpList().get(x)[1].toString());
								 if( slbatbuoc  <=0 )
								 {
									 this.msg = "Vui l??ng nh???p s??? l?????ng tr??? cho s???n ph???m "+matra +", c???a "+tl.getDiengiai();
									 return false;
								 }
							 }
						 }
					 }
					 
				 }
					 
			}
			if(this.spMuaList.size() <= 0)
			{
				this.msg = "Vui l??ng ch???n s???n ph???m trong ??i???u ki???n mua";
				return false;
			}
		
		
			if(this.khoId.trim().length() <= 0)
			{
				this.msg = "Vui l??ng ch???n kho ??p d???ng";
				return false;
			}
			
			db.getConnection().setAutoCommit(false);
			
			if(!Ctkhuyenmai.kiemtra_scheme(this.db,this.scheme,"select  pk_seq from ctkhuyenmai where thuongtl_fk = "+this.id+" "))
			{		
				db.getConnection().rollback();
				this.msg = "Scheme ???? t???n t???i trong khuy???n m??i, t??ch l??y, tr??ng b??y vui l??ng nh???p l???i";
				return false;
			}
			
			query = 	"\n update TieuchithuongTL set scheme ='"+this.scheme+"', thang ='"+this.thang+"', nam = '"+this.nam+"', diengiai =N'"+this.diengiai+"' , " +
						"\n 	ngaysua = '"+this.getDateTime() +"' , nguoisua ="+this.userId+",ngayds_tungay = '"+this.tungay+"' , ngayds_denngay ='"+this.dengay+"'" +
						"\n 		, khoId = "+this.khoId+", ghichu =N'"+this.ghighu+"' , DOANHSOTHEOLUONG ="+this.tinhtheosl+"  " +
						"\n where pk_seq = '" + this.id + "' and trangthai = 0";
			if(db.updateReturnInt(query) <= 0)
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			/*
			 * String
			 * querycheck="select count(*) as sl from TieuchithuongTL where nam<'"+this.
			 * getDateTime()+"' and pk_seq="+this.id+""; ResultSet
			 * checkRs=db.get(querycheck); checkRs.next(); if(checkRs.getInt("sl")>0) {
			 * this.msg = "?????n ng??y ????ng k?? kh??ng ???????c nh??? h??n ng??y hi???n t???i!";
			 * db.getConnection().rollback(); return false; }
			 */
			String querycheck="select count(*) as sl from TieuchithuongTL where nam<thang and pk_seq="+this.id+"";
			ResultSet checkRs=db.get(querycheck);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				this.msg = "?????n ng??y ????ng k?? kh??ng ???????c nh??? h??n t??? ng??y ????ng k??!";
				db.getConnection().rollback();
				return false;
			}
			checkRs.close();
			 querycheck="select count(*) as sl from TieuchithuongTL where ngayds_tungay>ngayds_denngay and pk_seq="+this.id+"";
			 checkRs=db.get(querycheck);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				this.msg = "?????n ng??y DS  kh??ng ???????c nh??? h??n t??? ng??y DS!";
				db.getConnection().rollback();
				return false;
			}
			checkRs.close();
			query = " update CTKHUYENMAI set scheme = N'"+this.scheme+"' , diengiai =N'"+this.diengiai+"' where thuongtl_fk = "+this.id+" ";
			if(db.updateReturnInt(query) <= 0)
			{
				this.msg = "Exception 1:";
				db.getConnection().rollback();
				return false;
			}
			
			query = "delete TieuchithuongTL_TIEUCHI where thuongtl_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Exception 2:";
				db.getConnection().rollback();
				return false;
			}
			query = "delete TIEUCHITHUONGTL_SANPHAM where thuongtl_fk = '" + this.id + "'";
			if(!db.update(query))
			{
				this.msg = "Exception 3:";
				db.getConnection().rollback();
				return false;
			}
			query = "delete TIEUCHITHUONGTL_SPTRA where thuongtl_fk = '" + this.id + "' ";
			if(!db.update(query))
			{
				this.msg = "Exception 3.1";
				db.getConnection().rollback();
				return false;
			}
			
			
			
			for(int i = 0; i < this.tichluyItemList.size(); i++)
			{
				 ITichLuyItem tl = this.tichluyItemList.get(i);	 
				
				 query = " insert TieuchithuongTL_TIEUCHI(thuongtl_fk,  tumuc, denmuc, chietkhau, donvi, muc,hinhthuc) " +
						 " select '" + this.id + "' as tctId,"+tl.getTumuc()+","+tl.getDenmuc()+","+tl.getTratichluy()+","+tl.getLoaitra()+","+i+","+tl.getHinhthuc()+" ";
				 System.out.println(query);
				 	
				 	if(db.updateReturnInt(query)<=0 )
					{
						this.msg = "Exception 4.1."+i;
						db.getConnection().rollback();
						return false;
					}			
					if(tl.getLoaitra() == 2)
					{
						List<Object> dataTra = new ArrayList<Object>();
						String sqlSpTra = "";
						for(int x = 0 ; x < tl.getSpList().size(); x++ )
						{
							Object[] spX = tl.getSpList().get(x);
							if(sqlSpTra.trim().length() > 0)
								sqlSpTra += "\n union all select "+this.id+", pk_seq ,'"+spX[1].toString()+"',"+i+","+tl.getHinhthuc()+" from sanpham where ma = '"+spX[0]+"' ";
							else
								sqlSpTra += " select "+this.id+", pk_seq ,'"+spX[1].toString()+"',"+i+","+tl.getHinhthuc()+" from sanpham where ma = '"+spX[0]+"'  ";
							
						
							
						}
						query = " insert TIEUCHITHUONGTL_SPTRA( thuongtl_fk, sanpham_fk, soluong, muctra, hinhthuctra ) " + sqlSpTra;
						if(db.updateReturnInt(query)<=0 )
						{
							this.msg = "Exception 4.2."+i;
							db.getConnection().rollback();
							return false;
						}		
					}
					
			}
			
			
			for(int i = 0; i < this.spMuaList.size(); i ++)
			{
				if(spMuaList.get(i).contains("#_#_#"))
				{
					System.out.println("spMuaList ["+i+"] "+ spMuaList.get(i));
					String[] split =  spMuaList.get(i).split("#_#_#") ;
					
					
					
					query = "Insert TIEUCHITHUONGTL_SANPHAM(thuongtl_fk, sanpham_fk,quydoi) select '" + this.id + "', pk_seq,'"+split[1]+"' " +
					"from SanPham where ma = '"+split[0]+"' ";
		
					if(db.updateReturnInt(query)<=0 )
					{
						this.msg = "Exception 5:";
						db.getConnection().rollback();
						return false;
					}
					
				}
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		} 
		catch (Exception e)
		{
			try 
			{
				db.getConnection().rollback();
				System.out.println("__EXCEPTION UPDATE: " + e.getMessage());
				e.printStackTrace();
				
				this.msg = "Exception" ;
			} 
			catch (SQLException e1) {}
			
			return false;
		}
		
		
	}

	public void init() 
	{
		String query = "\n select scheme, thang, nam,isnull(ghichu,'') as ghichu, diengiai, mucvuot, " +
		"\n chietkhauMucVuot, donviMucVuot, hinhthuctra, DOANHSOTHEOTHUNG, HTTT, PT_TRATL, " +
		"\n ngayds_tungay, ngayds_denngay, khoId, phanloai, ngaytb_tungay, ngaytb_denngay, " +
		"\n batbuocdangky, DOANHSOTHEOLUONG " +
		"\n from TieuchithuongTL where pk_seq = '" + this.id + "'";		
		System.out.println("__Khoi tao tieu chi thuong: " + query);

		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				NumberFormat format = new DecimalFormat("#,###,###");
				while(rs.next())
				{
					this.scheme = rs.getString("scheme");
					this.thang = rs.getString("thang");
					this.nam = rs.getString("nam");					
					this.diengiai= rs.getString("diengiai");
					this.doanhsotheoThung = rs.getString("DOANHSOTHEOTHUNG");					
					this.httt = rs.getString("HTTT");
					this.ptchietkhau = rs.getString("PT_TRATL");
					this.ghighu = rs.getString("ghichu");
					this.tungay = rs.getString("ngayds_tungay");
					this.dengay = rs.getString("ngayds_denngay");
					this.khoId = rs.getString("khoId");					
					this.phanloai = rs.getString("phanloai");
					this.ngaytb_tungay = rs.getString("ngaytb_tungay");
					this.ngaytb_dengay = rs.getString("ngaytb_denngay");
					this.phaidangky = rs.getString("batbuocdangky");
					this.tinhtheosl = rs.getString("DOANHSOTHEOLUONG");
				}
				rs.close();
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}

		this.createNdk();
		this.createRs();
		this.createKhachHangDK();
	}
	
	private void createKhachHangDK()
	{
		try
		{

			String DKKMTICHLUY_KHACHHANG_IdTmp ="";
			String khDcDuyetTmp ="";
			
			String query = "\n select khdk.DKKMTICHLUY_FK, khdk.KHACHHANG_FK, npp.TEN as nhaphanphoi, kh.TEN as khachhang, isnull(khdk.DANGKY, 0) as dangky, nppdk.Muc " + 
			 "\n from DANGKYKM_TICHLUY_KHACHHANG khdk  " + 
			 "\n inner join DANGKYKM_TICHLUY nppdk on khdk.DKKMTICHLUY_FK = nppdk.PK_SEQ " + 
			 "\n inner join TIEUCHITHUONGTL thuong on thuong.PK_SEQ = nppdk.tieuchitl_fk " + 
			 "\n inner join KHACHHANG kh on kh.PK_SEQ = khdk.KHACHHANG_FK " + 
			 "\n inner join NHAPHANPHOI npp on npp.PK_SEQ = nppdk.npp_fk " + 
			 "\n where  thuong.PK_SEQ = "+ this.id ;
			System.out.println(":::: lay khach hang dang ky: " + query);
			ResultSet rs = db.get(query); 
			this.khDangKyRs = db.get(query); 
			while(rs.next())
			{
				DKKMTICHLUY_KHACHHANG_IdTmp += rs.getString("DKKMTICHLUY_FK") + "," + rs.getString("KHACHHANG_FK")+"__" ;
				khDcDuyetTmp += "0" + "__" ;
			}
			
			if(DKKMTICHLUY_KHACHHANG_IdTmp.trim().length() >2)
			{
				DKKMTICHLUY_KHACHHANG_IdTmp =DKKMTICHLUY_KHACHHANG_IdTmp.substring(0, DKKMTICHLUY_KHACHHANG_IdTmp.length() - 2);
				khDcDuyetTmp =khDcDuyetTmp.substring(0, khDcDuyetTmp.length() - 2);
				DKKMTICHLUY_KHACHHANG_Id = DKKMTICHLUY_KHACHHANG_IdTmp.split("__");
				khDcDuyet = khDcDuyetTmp.split("__");
				
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void createNdk() 
	{
		tichluyItemList.clear();
		spMuaList.clear();
		
		String query = "select hinhthuc,ghichu, tumuc, denmuc, chietkhau, donvi, muc,isnull(mucphanbo,'') as mucphanbo  " +
					   "from TieuchithuongTL_TIEUCHI " +
					   "where thuongtl_fk = '" + this.id + "' order by muc ";
		
		System.out.println("___Khoi tao tieu chi: " + query);
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			NumberFormat format = new DecimalFormat("##,###,###");
			NumberFormat format2 = new DecimalFormat("##,###,###.##");
			try 
			{
				while(rs.next())
				{
					ResultSet rsDETAIL = null;
					ITichLuyItem item = new TichLuyItem();
					item.setMuc(rs.getInt("muc"));
					item.setLoaitra(rs.getInt("donvi")); 
					item.setHinhthuc(rs.getInt("hinhthuc"));
					item.setTumuc(rs.getDouble("tumuc"));
					item.setDenmuc(rs.getDouble("denmuc"));
					item.setTratichluy(rs.getDouble("chietkhau"));
					
					if(item.getLoaitra() == 2)// trasp
					{
						query = "\n select sp.ma, soluong " +
								"\n from TIEUCHITHUONGTL_SPTRA a inner join sanpham sp on a.sanpham_fk = sp.pk_seq " +
								"\n where a.muctra = "+item.getMuc()+" and a.thuongtl_fk = '" + this.id + "'  ";
						rsDETAIL = db.get(query);
						item.getSpList().clear();
						while(rsDETAIL.next())
						{
							Object[]  a = { rsDETAIL.getString("ma"),rsDETAIL.getDouble("soluong")  };
							item.getSpList().add(a);
						}	
						item.setSanPhamRs(db);
					}	
					tichluyItemList.add(item);
					
					
					
				}
				
				query  = " select sp.ma,isnull(a.quydoi,0) quydoi from TIEUCHITHUONGTL_SANPHAM a inner join sanpham sp on a.sanpham_fk = sp.pk_seq where a.thuongtl_fk =  "+ this.id;
				rs  = db.get(query);
				while(rs.next())
				{
					spMuaList.add ( rs.getString("ma") +"#_#_#"+rs.getString("quydoi") );
				}
				this.setSanphamMuaRs();
				
				
			} 
			catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getScheme()
	{
		return this.scheme;
	}

	public void setScheme(String scheme) 
	{
		this.scheme = scheme;
	}
	
	

	

	public void createRs() {
		

		String query = "select pk_seq, ten from KHO where trangthai = '1'";
		this.khoRs = db.get(query);
		
		query = "select pk_seq, ten from NHOMSANPHAM where trangthai = '1'  and type='1'";
		this.nhomsanphamRs = db.get(query);
		
	}
	
	public void setSanphamRs(ResultSet spRs) {
		
		this.sanphamRs = spRs;
	}

	
	public ResultSet getSanphamRs() {
		
		return this.sanphamRs;
	}

	
	public String getSpIds() {
		
		return this.spIds;
	}

	
	public void setSpIds(String spIds) {
		
		this.spIds = spIds;
	}

	
	public void setNppRs(ResultSet nppRs) {
		
		this.nppRs = nppRs;
	}

	
	public ResultSet getNppRs() {
		
		return this.nppRs;
	}

	
	
	
	public void setVungRs(ResultSet vungRs) {
		
		this.vungRs = vungRs;
	}

	
	public ResultSet getVungRs() {
		
		return this.vungRs;
	}

	
	public String getVungIds() {
		
		return this.vungIds;
	}

	
	public void setVungIds(String vungIds) {
		
		this.vungIds = vungIds;
	}

	
	public void setKhuvucRs(ResultSet kvRs) {
		
		this.kvRs = kvRs;
	}

	
	public ResultSet getKhuvucRs() {
		
		return this.kvRs;
	}

	
	public String getKhuvucIds() {
		
		return this.kvIds;
	}

	
	public void setKhuvucIds(String kvIds) {
		
		this.kvIds = kvIds;
	}

	
	

	
	public String getDoanhsotheoThung() {
		
		return this.doanhsotheoThung;
	}

	
	public void setDoanhsotheoThung(String isThung) {
		
		this.doanhsotheoThung = isThung;
	}

	
	public String getHTTT() {
		
		return this.httt;
	}

	
	public void setHTTT(String httt) {
		
		this.httt = httt;
	}

	
	public String getPT_TRACK() {
		
		return this.ptchietkhau;
	}

	
	public void setPT_TRACK(String ptTRACK) {
		
		this.ptchietkhau = ptTRACK;
	}



	
	
	public String getNgayDS_Tungay() {
		
		return this.tungay;
	}

	
	public void setNgayDS_Tungay(String tungay) {
		
		this.tungay = tungay;
	}

	
	public String getNgayDS_Denngay() {
		
		return this.dengay;
	}

	
	public void setNgayDS_Denngay(String denngay) {
		
		this.dengay = denngay;
	}

	
	public void setKhoRs(ResultSet khoRs) {
		
		this.khoRs = khoRs;
	}

	
	public ResultSet getKhoRs() {
		
		return this.khoRs;
	}

	
	public String getKhoIds() {
		
		return this.khoId;
	}

	
	public void setKhoIds(String khoIds) {
		
		this.khoId = khoIds;
	}

	



	
	public String getPhanloai() {

		return this.phanloai;
	}


	public void setPhanloai(String phanloai) {

		this.phanloai = phanloai;
	}
	
	
	
	public void setNhomsanphamRs(ResultSet spRs) {
		
		this.nhomsanphamRs = spRs;
	}
	
	public ResultSet getNhomsanphamRs() {
		
		return this.nhomsanphamRs;
	}
	
	public String getNhomsanphamIds() {
		
		return this.nhomspIds;
	}
	
	public void setNhomsanphamIds(String nhomspIds) {
		
		this.nhomspIds = nhomspIds;
	}
	
	public void loadSP_NHOM() 
	{
		if(this.nhomspIds.trim().length() > 0)
		{
			this.spMuaList.clear();

			String query = "\n select b.MA, b.TEN " + 
			"\n from NHOMSANPHAM_SANPHAM a inner join SANPHAM b on a.SP_FK = b.PK_SEQ " +
			"\n where a.NSP_FK = '" + this.nhomspIds + "' ";
			System.out.println("query = "+ query);
			ResultSet rs = db.get(query);
			if(rs != null )
			{
				try 
				{
					while(rs.next())
					{
						spMuaList.add(rs.getString("MA")+ "#_#_#0" );	
					}
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			this.setSanphamMuaRs();
		}
	}
	
	public void setKenhRs(ResultSet kenhRs) {
		
		this.kenhRs = kenhRs;
	}
	
	public ResultSet getKenhRs() {
		
		return this.kenhRs;
	}
	
	public String getKenhIds() {
		
		return this.kenhIds;
	}
	
	public void setKenhIds(String kenhIds) {
		
		this.kenhIds = kenhIds;
	}
	
	
	public String getGhichu() {
		
		return this.ghighu;
	}
	
	public void setGhichu(String ghichu) {
		
		this.ghighu = ghichu;
	}
	
	public String getKT() {
		
		return this.kt;
	}
	
	public void setKT(String kt) {
		
		this.kt = kt;
	}
	
	public String getActiveTab() {
		
		return this.active;
	}
	
	public void setActiveTab(String active) {
		
		this.active = active;
	}
	
	public String getMucNPP() {
		
		return this.mucnpp;
	}
	
	public void setMucNPP(String MucNpp) {
		
		this.mucnpp = MucNpp;
	}
	
	
	
	

	
	public String getNgayTB_Tungay() {
		
		return this.ngaytb_tungay;
	}

	
	public void setNgayTB_Tungay(String tungay) {
		
		this.ngaytb_tungay = tungay;
	}

	
	public String getNgayTB_Denngay() {
		
		return this.ngaytb_dengay;
	}

	
	public void setNgayTB_Denngay(String denngay) {
		
		this.ngaytb_dengay = denngay;
	}

	
	public String getPhaidangky() {
		
		return this.phaidangky;
	}

	
	public void setPhandangky(String phaidangky) {
		
		this.phaidangky = phaidangky;
	}

	@Override
	public String getTinhtheoSl() {
		// TODO Auto-generated method stub
		return this.tinhtheosl;
	}

	@Override
	public void setTinhtheoSl(String value) {
		this.tinhtheosl = value;
	}


	public ArrayList<ITichLuyItem> getTichluyItemList() {
		return tichluyItemList;
	}
	public void setTichluyItemList(ArrayList<ITichLuyItem> tichluyItemList) {
		this.tichluyItemList = tichluyItemList;
	}
	
	public dbutils getDb() {
		return db;
	}
	List<Object> dataUpload = new ArrayList<Object>();
	public List<Object> getDataUpload() {
		return dataUpload;
	}
	public void setDataUpload(List<Object> dataUpload) {
		this.dataUpload = dataUpload;
	}
	
	public boolean uploadPhanbo(String values)
	{
		String query = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			
			Object[] data;
			
			query = " update TIEUCHITHUONGTL set ThoiDiemPhanBo = dbo.GetLocalDate(default) , NguoiPhanBo = '"+this.userId+"',ngaysua= '"+getDateTime()+"', nguoisua = '"+this.userId+"'  where trangthai = 0 and pk_seq =  "+ this.id;
			if(db.updateReturnInt(query)!=1)
			{
				this.msg = "L???i Upload";
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			String error = "";
			query  = " select nppId from ("+values+") data where nppId not in (select pk_seq from  nhaphanphoi) ";
			ResultSet rs = db.get(query);
			while(rs.next())
			{
				if(error.trim().length() > 0)
					error += ", "  + rs.getString("nppId");
				else
					error += rs.getString("nppId");
				
			}
			if(error.trim().length() > 0)
			{
				this.msg = "C??c id NPP sau kh??ng t???n t???i"+ error;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
		//	db.viewQuery(query, this.dataUpload);
			
			query  = " select nppId,muc from ("+values+") data" +
					 " group by nppId,muc " +
					 " having count(*) > 1 ";
			rs = db.get(query);
			while(rs.next())
			{
				if(error.trim().length() > 0)
					error += ", "  + rs.getString("nppId") + ", m???c  "+(rs.getDouble("muc") +1) ;
				else
					error +=  rs.getString("nppId") + ", m???c  "+(rs.getDouble("muc") +1) ;
				
			}
			if(error.trim().length() > 0)
			{
				this.msg = "C??c id NPP sau  b??? l???p d??ng"+ error;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			query  = " select nppId , sosuat , muc from ("+values+") data where cast( sosuat as float)  < 0.0 order by nppId " ;
			
			
				rs = db.get(query);
				while(rs.next())
				{
					if(error.trim().length() > 0)
						error += ", "  + rs.getString("nppId") +" , m???c "+(rs.getDouble("muc") +1)+"  ";
					else
						error += rs.getString("nppId") +" , m???c "+(rs.getDouble("muc") +1)+"  ";
					
				}
				if(error.trim().length() > 0)
				{
					this.msg = "C??c NPP sau ??ang c?? s??? su???t b?? h??n 0 :"+ error;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
			
			query = " delete TIEUCHITHUONGTL_NPP where thuongtl_fk =  "+ this.id;
			if(!db.update(query))
			{
				this.msg = "L???i Upload";
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			query = " insert TIEUCHITHUONGTL_NPP (thuongtl_fk,npp_fk,soluong,muc) select "+this.id+",nppId, sosuat,muc from (" + values + ") a where cast( sosuat as float)  > 0 ";
			
			if( db.updateReturnInt(query) < 0 )
			{
				this.msg = "L???i Upload";
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			this.msg = "Upload th??nh c??ng";
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			Utility.rollback_and_shutdown(db); 
			this.msg = "L???i ngo???i l???";
			return false;
		}
	}
	
	

}
