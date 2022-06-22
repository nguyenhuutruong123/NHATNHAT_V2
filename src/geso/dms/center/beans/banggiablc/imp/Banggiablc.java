package geso.dms.center.beans.banggiablc.imp;

import geso.dms.center.beans.banggiablc.IBanggiablc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.db.sql.*;
import geso.dms.center.util.Utility;
import geso.dms.center.util.UtilitySyn;
public class Banggiablc implements IBanggiablc
{
	
	String display ="0";
	
	private static final long serialVersionUID = -9217977546733610214L;
	String userId;
	String id;
	String ten;
	String dvkd;
	String dvkdId;
	String kbhId = "";
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	String donvi;
	dbutils db;
	ResultSet dvkdIds,kbhRs, kvRs, loaiKhRs;
	String khuvucIds = "", loaiKhIds = "";
		
	ResultSet sanphamlist;
	ResultSet newsplist;
	String[] giablc; //luu thong tin cac gia ban le chuan thay doi
	String[] quycach;
	String tungay;
	
	String[] spIdArr,spMaArr,spTenArr,donviArr ,dongiaArr;
	
	
	
	
	public Banggiablc(String[] param)
	{
		this.id 		= param[0];
		this.ten 		= param[1];
		this.dvkd 		= param[2];
		this.trangthai 	= param[3];
		this.ngaytao 	= param[4];
		this.nguoitao 	= param[5];
		this.ngaysua 	= param[6];
		this.nguoisua 	= param[7];
		this.msg = "";
		this.dvkdId = "";
		this.db = new dbutils();
		this.donvi ="";
		//createRS();
		createDvkdRS();
	}
	
	public Banggiablc()
	{
		this.id 		= "";
		this.ten 		= "";
		this.dvkd 		= "";
		this.trangthai 	= "";
		this.ngaytao 	= "";
		this.nguoitao 	= "";
		this.ngaysua 	= "";
		this.nguoisua 	= "";
		this.msg = "";
		this.dvkdId = "";
		this.tungay="";
		this.db = new dbutils();
		this.donvi = "";
		//createRS();
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

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}
	
	public String getDvkdId() 
	{
		return this.dvkdId;
	}

	public void setDvkdId(String dvkdId) 
	{
		this.dvkdId = dvkdId;
	}
	
	public String getDvkd() 
	{
		return this.dvkd;
	}
	
	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}
	
	public void setDvkd(String dvkd) 
	{
		this.dvkd = dvkd;
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
	
	public ResultSet getDvkdIds() 
	{
		return this.dvkdIds;
	}

	public void setDvkdIds(ResultSet dvkdIds) 
	{
		this.dvkdIds = dvkdIds;
	}

	public String[] getGiablc() 
	{
		return this.giablc;
	}

	public void setGiablc(String[] giablc) 
	{
		this.giablc = giablc;
	}

	public ResultSet getSanPhamList() 
	{
		return this.sanphamlist;
	}

	public void setSanPhamList(ResultSet sanphamlist) 
	{
		this.sanphamlist = sanphamlist;
	}

	public ResultSet getNewSanPhamList() 
	{
		return this.newsplist;
	}

	public void setNewSanPhamList(ResultSet newsplist) 
	{
		this.newsplist = newsplist;
	}

	public boolean CreateBgblc(HttpServletRequest request) 
	{
		try
		{
			this.db.getConnection().setAutoCommit(false);
			
			String conditionBG="";
			if(this.loaiKhIds.trim().length() > 0)
			{
				conditionBG= " and kh.LCH_FK in (" + this.loaiKhIds + ")";
			}
			if(this.khuvucIds.trim().length() > 0)
			{
				conditionBG= " and kh.KHUVUC_FK in (" + this.khuvucIds + ")";
			}
			
			boolean taoBG = true;
			String query = "SELECT COUNT(*) AS PS "
					+ "\nFROM "
					+ "\n	( "
					+ "\n	SELECT a.PK_SEQ, a.NGAYGIO_TAO FROM dbo.DONHANG a inner join khachhang kh on a.khachhang_fk=k.pk_Seq "
					+ "\n 	where a.trangthai<>2 and a.kbh_fk ='"+this.kbhId+"' "+conditionBG+""
					+ "\n	) donhang "
					+ "\nWHERE donhang.NGAYGIO_TAO >= '" + this.tungay + "' ";
			ResultSet rsPS = this.db.get(query);
			if (rsPS != null) {
				rsPS.next();
				if (rsPS.getDouble("PS") > 0) {
					taoBG = false;
				}
				rsPS.close();
			}
			
			if (taoBG == false) {
				this.msg = "Đã có phát sinh đơn hàng từ ngày ("	+ this.tungay + "): " ;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			String kv = this.khuvucIds.length() > 0 ?   this.khuvucIds : "0";
			
			query = "insert into banggiabanlechuan (kbh_fk,ten, ngaytao, ngaysua, nguoitao, nguoisua, dvkd_fk, trangthai, tungay) "+
					"\n select "+this.kbhId+",N'" + this.ten + "','" + this.ngaytao + "','" + this.ngaysua + "','" + this.nguoitao + "','" + this.nguoisua + "','" + this.dvkdId + "',0,'"+this.tungay+"'" +
					"\n where not exists " +
					"\n ( select 1 from banggiabanlechuan where kbh_fk='" + this.kbhId + "' and dvkd_fk='" + this.dvkdId + "' and tungay='"+this.tungay+"' and pk_seq in ( select BangGiaBanLeChuan_FK from BangGiaBanLeChuan_KhuVuc where khuvuc_fk in ("+kv+") ) ) ";
			System.out.println("1. BGBLC : "+query);
			if (this.db.updateReturnInt(query) <=0){
				this.msg = "Đã tồn tại bảng giá bán lẻ chuẩn từ ngày ("+this.tungay+"):  "+query;
				this.db.getConnection().rollback();	
				return false;		
			}
			
			query = "select scope_identity() bgblcId";				
			ResultSet rs = db.get(query);
			rs.next();
			String bgid = rs.getString("bgblcId");
			rs.close();
			//System.out.println(rs.getString("bgblcId"));
			
			for(int i = 0; i < spIdArr.length; i++)
			{
				query = "\n insert into banggiablc_sanpham(SANPHAM_FK,BGBLC_FK,GIABANLECHUAN) " +
				  		"\n values('" + spIdArr[i] + "','" + bgid + "', " + Utility.parseDouble(dongiaArr[i].trim().replaceAll(",", "")) +")";				
				System.out.println("2. BGBLC SP : "+query);
				if (this.db.updateReturnInt(query) <=0){
				
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					this.db.getConnection().rollback();					
					return false;		
				}
			}
			
			query = "delete BANGGIABANLECHUAN_LOAIKHACHHANG where bgblc_fk = "+bgid;
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.msg = "Đã tồn tại bảng giá khách hàng loại này. Vui lòng kiểm tra lại.";
				return false; 
			}
			
			if(this.loaiKhIds.trim().length() > 0)
			{
				query = "Insert BANGGIABANLECHUAN_LOAIKHACHHANG(bgblc_fk, Lkh_fk, tungay) " +
						"select '" + bgid + "', pk_seq, '"+ this.tungay +"' from loaicuahang where pk_seq >= 0 ";
		    	if(this.loaiKhIds.trim().length() > 0)
		    		query += " and pk_seq in ( " + this.loaiKhIds + " ) ";
		    	
		    	System.out.println(query);
				if(db.updateReturnInt(query) < 1)
				{
					db.getConnection().rollback();
					this.msg = "Đã tồn tại bảng giá khách hàng loại này. Vui lòng kiểm tra lại.";
					//this.msg = "6. Khong the tao moi 'BANGGIABANLECHUAN_LOAIKHACHHANG', " + query;
					return false; 
				}
			}
			
			if(this.khuvucIds.trim().length() > 0)
			{
		    	query = "insert into BangGiaBanLeChuan_KhuVuc(BangGiaBanLeChuan_FK,KhuVuc_FK) "+ 
						" select '"+ bgid +"', pk_seq From KhuVuc where pk_Seq in ("+ this.khuvucIds.trim() + ")  ";
		    	System.out.println(query);
				if(db.updateReturnInt(query) < 1)
				{
					db.getConnection().rollback();
					this.msg = "Đã tồn tại bảng giá khách hàng khu vực này. Vui lòng kiểm tra lại.";
					//this.msg = "6. Khong the tao moi 'BANGGIABANLECHUAN_LOAIKHACHHANG', " + query;
					return false; 
				}
			}
			
			this.id = bgid;
			rs.close();
			this.db.getConnection().commit();						
			return true;
		} catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Loi : "+e.toString());
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
	}

	public boolean UpdateBgblc(HttpServletRequest request) 
	{
		try
		{
     		this.db.getConnection().setAutoCommit(false);
     		String command= "\n update banggiabanlechuan set tungay='"+this.tungay+"',ten = N'" + this.ten + "'" +
     						"\n 	, ngaysua = '" + this.ngaysua + "', nguoisua = '" + this.nguoisua + "'" +
     						"\n where pk_seq = '" + this.id + "' and trangthai = 0";
     		if (this.db.updateReturnInt(command) <=0){
			
				this.db.getConnection().rollback();
				this.msg=" Khong The Chinh Sua Bang Gia ,Vui Long Kiem Tra Lai Cac Thong Tin ";
				return false;
			}
		
			command="delete from banggiablc_sanpham where bgblc_fk= " + this.id + " ";
			 if(!this.db.update(command))
			 {
				 this.msg = "Vui lòng liên hệ Admin để kiểm tra "+command;
				 this.db.getConnection().rollback();
				 return false;
			 }
			for(int i = 0; i < spIdArr.length; i++)
			{
				command = 	"\n insert into banggiablc_sanpham(SANPHAM_FK,BGBLC_FK,GIABANLECHUAN) " +
				  			"\n values('" + spIdArr[i] + "','" + this.id + "', " + Utility.parseDouble(dongiaArr[i].trim().replaceAll(",", "")) +")";				
				System.out.println("2. BGBLC SP : "+command);
				if (this.db.updateReturnInt(command) <=0){
				
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+command;
					this.db.getConnection().rollback();						
					return false;		
				}
			}
			
			String query = "delete BANGGIABANLECHUAN_LOAIKHACHHANG where bgblc_fk = "+this.id;
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.msg = "Đã tồn tại bảng giá khách hàng loại này. Vui lòng kiểm tra lại.";
				return false; 
			}
			
			if(this.loaiKhIds.trim().length() > 0)
			{
				query = "Insert BANGGIABANLECHUAN_LOAIKHACHHANG(bgblc_fk, Lkh_fk, tungay)  " +
						"select '" + this.id + "', pk_seq, '"+ this.tungay +"' from loaicuahang where pk_seq >= 0  ";
		    	if(this.loaiKhIds.trim().length() > 0)
		    		query += " and pk_seq in ( " + this.loaiKhIds + " ) ";
				if(!db.update(query))
				{
					db.getConnection().rollback();
					this.msg = "Đã tồn tại bảng giá khách hàng loại này. Vui lòng kiểm tra lại.";
					return false; 
				}
			}
			
			query = "delete BangGiaBanLeChuan_KhuVuc where BangGiaBanLeChuan_FK = "+this.id;
			if(!db.update(query))
			{
				db.getConnection().rollback();
				this.msg = "Đã tồn tại bảng giá khách hàng khu vực này. Vui lòng kiểm tra lại.";
				return false; 
			}
			if(this.khuvucIds.trim().length() > 0)
			{
		    	query = "insert into BangGiaBanLeChuan_KhuVuc(BangGiaBanLeChuan_FK,KhuVuc_FK) "+ 
						" select '"+ this.id +"', pk_seq From KhuVuc where pk_Seq in ("+ this.khuvucIds.trim() + ")  ";
		    	System.out.println(query);
				if(db.updateReturnInt(query) < 1)
				{
					db.getConnection().rollback();
					this.msg = "Đã tồn tại bảng giá khách hàng khu vực này. Vui lòng kiểm tra lại.";
					return false; 
				}
			}
			
			this.db.getConnection().commit();
			return true;
		}catch(Exception e)
		{
			this.msg="Loi Xay Ra Tong Qua Trinh Sua :"+ e.getMessage() ;
			e.printStackTrace();
			try { this.db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return false;
		}
		finally{ try { this.db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
	}

	private void createDvkdRS(){  				
		/*String sql = "select distinct a.donvikinhdoanh as dvkd, a.pk_seq as dvkdId from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk and b.checked ='1' and a.trangthai ='1' order by a.donvikinhdoanh";*/
		String sql = "select distinct a.donvikinhdoanh as dvkd, a.pk_seq as dvkdId from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk and a.trangthai ='1' order by a.donvikinhdoanh";
		this.dvkdIds  =  this.db.get(sql);
	}
	private void createSpRSNew(dbutils cn){
		if (this.id.length()>0)
		{
			if (this.dvkdId.length()==0)
			{			
				ResultSet rs = this.db.get("select kbh_fk,dvkd_fk as dvkdId, ten, trangthai from banggiabanlechuan where pk_seq = '" + this.id + "'");
				try{
					rs.next();
					this.dvkdId = rs.getString("dvkdId");
					this.ten = rs.getString("ten");
					this.trangthai = rs.getString("trangthai");
					this.kbhId = rs.getString("kbh_fk");
					if(rs!=null){
						rs.close();
					}
				}catch(Exception e){}
			}
			String sql = 	"\n select a.pk_seq as id, a.ma, a.ten, c.giabanlechuan from " +
							"\n sanpham a, donvikinhdoanh b, donvidoluong dvdl, banggiablc_sanpham c, banggiabanlechuan d " +
							"\n where a.trangthai='1' and a.dvkd_fk = b.pk_seq and d.dvkd_fk = b.pk_seq " +
							"\n			and c.bgblc_fk = d.pk_seq and c.sanpham_fk = a.pk_seq " +
							"\n			and b.pk_seq = '"+ this.dvkdId +"' and d.KBH_FK =    " + this.kbhId +
							"\n order by ma";
			this.sanphamlist = this.db.get(sql);
			System.out.println("sanphamlist : "+sql);
			this.newsplist = this.db.get("\n select a.pk_seq as id, a.ma, a.ten " +
										 "\n from sanpham a, donvikinhdoanh b " +
										 "\n where a.trangthai='1' and a.dvkd_fk = b.pk_seq " +
										 "\n		and b.pk_seq = '"+ this.dvkdId + "' " +
										 "\n		and a.pk_seq not in " +
										 "\n				( select a.pk_seq as id " +
										 "\n					from sanpham a, donvikinhdoanh b, banggiablc_sanpham c, banggiabanlechuan d " +
										 "\n					where a.dvkd_fk = b.pk_seq and d.dvkd_fk = b.pk_seq and c.bgblc_fk = d.pk_seq " +
										 "\n					and c.sanpham_fk = a.pk_seq and b.pk_seq = '" + this.dvkdId + "' and d.KBH_FK = "+this.kbhId+" ) " +
										 "\n order by ma ");
			
		}else
		{
			if (this.dvkdId.length() > 0){			
				this.sanphamlist = this.db.get("select a.pk_seq as id, a.ma, a.ten from sanpham a, donvikinhdoanh b where a.trangthai='1' and  a.dvkd_fk = b.pk_seq and b.pk_seq = '" + this.dvkdId + "' order by ma");
			}else{
				ResultSet rs = this.db.get("select pk_seq as dvkdId from donvikinhdoanh order by pk_seq");
				try{
					rs.next();
					this.dvkdId = rs.getString("dvkdId");
					this.ten = "";
					this.trangthai = "1";
					if(rs!=null){
						rs.close();
					}
				}catch(Exception e){}
				String sql = 	"\n select a.pk_seq as id, a.ma, a.ten,isnull( c.giabanlechuan,0) as giabanlechuan ,isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)  as Quycach, dv.DIENGIAI as donvi		" +
								"\n from sanpham a  		" +
								"\n inner join donvikinhdoanh b on a.DVKD_FK=b.PK_SEQ 		" +
								"\n left join banggiablc_sanpham c on c.SANPHAM_FK=a.PK_SEQ 		" +
								"\n left join  banggiabanlechuan d on d.PK_SEQ=c.BGBLC_FK  	" +
								"\n inner join DONVIDOLUONG dv on dv.PK_SEQ = a.DVDL_FK	" +
								"\n left join QUYCACH qc  on qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018 and qc.dvdl1_fk=a.dvdl_fk  	" +
								"\n where    b.pk_seq = '"+this.dvkdId +"' and d.kbh_fk ="+this.kbhId+"  order by ma"; 
				this.sanphamlist = this.db.get(sql);
				System.out.println("SP LIST 2 : "+sql);
			}
			
		}
	}
	
	private void initSP() throws SQLException
	{
		//System.out.println("initSP : "+ this.id+" - "+this.dvkdId+" - "+ this.kbhId);
		String sql =
			  "\n select a.pk_seq as id, a.ma, a.ten,isnull( c.giabanlechuan,0) as giabanlechuan, "  +
			  "\n isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)  as Quycach, dv.DIENGIAI as donvi "+
			  "\n from sanpham a "+
			  "\n inner join donvikinhdoanh b on a.DVKD_FK=b.PK_SEQ "+
			  "\n left join banggiablc_sanpham c on c.SANPHAM_FK=a.PK_SEQ and c.BGBLC_FK="+this.id+" and  b.pk_seq = '"+this.dvkdId+"'	"+		
			  "\n left join banggiabanlechuan d on d.PK_SEQ=c.BGBLC_FK and d.kbh_fk ="+this.kbhId+" "+
			  "\n inner join DONVIDOLUONG dv on dv.PK_SEQ = a.DVDL_FK "+
			  "\n left join QUYCACH qc  on qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018 and qc.dvdl1_fk=a.dvdl_fk "+
			  "\n and isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)<>0 where 1=1 " +
			  "\n order by ma"; 
		//System.out.println(" sql === "+ sql);
		String spIdStr = "",spMaStr = "",spTenStr = "",donviStr = "" ,dongiaStr = "";
		ResultSet rs=  db.get(sql);
		 while(rs.next())
		 {
			 if(spIdStr.trim().length() <=0)
			 {
				 spIdStr = rs.getString("id");
				 spMaStr = rs.getString("ma");
				 spTenStr = rs.getString("ten");
				 donviStr = rs.getString("donvi");
				 dongiaStr = rs.getString("giabanlechuan");
			 }
			 else
			 {
				 spIdStr += " __ "  + rs.getString("id");
				 spMaStr += " __ "  + rs.getString("ma");
				 spTenStr += " __ "  + rs.getString("ten");
				 donviStr += " __ "  + rs.getString("donvi");
				 dongiaStr += " __ " + rs.getString("giabanlechuan");
			 }
		 }
		 if(spIdStr.trim().length() >0)
		 {
			 spIdArr = spIdStr.split(" __ ");
			 spMaArr = spMaStr.split(" __ ");
			 spTenArr = spTenStr.split(" __ ");
			 donviArr = donviStr.split(" __ ");
			 dongiaArr = dongiaStr.split(" __ ");
		 }
		 rs.close();
	}
	
	/*private void initSP() throws SQLException
	{

		if (this.dvkdId.length()==0)
		{			
			ResultSet rs = this.db.get("select kbh_fk,dvkd_fk as dvkdId, ten, trangthai from banggiabanlechuan where pk_seq = '" + this.id + "'");
			try
			{
				rs.next();
				this.dvkdId = rs.getString("dvkdId");
				this.ten = rs.getString("ten");
				this.trangthai = rs.getString("trangthai");
				this.kbhId = rs.getString("kbh_fk");
				if(rs!=null)
				{
					rs.close();
				}
			}catch(Exception e){}
		}
		String 
		 sql ="\n 	select a.pk_seq as id, a.ma, a.ten,isnull( c.giabanlechuan,0) as giabanlechuan " 
			 +"\n 		,isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)  as Quycach, dv.DIENGIAI as donvi		"
			 +"\n 	from sanpham a  		"
			 +"\n 	inner join  donvikinhdoanh b on a.DVKD_FK=b.PK_SEQ 		"
			 +"\n 	left join   banggiablc_sanpham c on c.SANPHAM_FK=a.PK_SEQ and  c.BGBLC_FK="+this.id+""		
			 +"\n 	left join   banggiabanlechuan d on d.PK_SEQ=c.BGBLC_FK    and  d.kbh_fk ="+this.kbhId+" 	"
			 +"\n 	inner join  DONVIDOLUONG dv on dv.PK_SEQ = a.DVDL_FK	    "
			 +"\n   left join QUYCACH qc  on qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018 and qc.dvdl1_fk=a.dvdl_fk  	"
			 +"\n 	where    b.pk_seq = '"+this.dvkdId+"'   and isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)<>0  order by ma"; 
		System.out.println(" sql === "+ sql);
		String spIdStr = "",spMaStr = "",spTenStr = "",donviStr = "" ,dongiaStr = "";
		ResultSet rs=  db.get(sql);
		 while(rs.next())
		 {
			 if(spIdStr.trim().length() <=0)
			 {
				 spIdStr = rs.getString("id");
				 spMaStr = rs.getString("ma");
				 spTenStr = rs.getString("ten");
				 donviStr = rs.getString("donvi");
				 dongiaStr = rs.getString("giabanlechuan");
			 }
			 else
			 {
				 spIdStr += " __ "  + rs.getString("id");
				 spMaStr += " __ "  + rs.getString("ma");
				 spTenStr += " __ "  + rs.getString("ten");
				 donviStr += " __ "  + rs.getString("donvi");
				 dongiaStr += " __ " + rs.getString("giabanlechuan");
			 }
		 }
		 if(spIdStr.trim().length() >0)
		 {
			 spIdArr = spIdStr.split(" __ ");
			 spMaArr = spMaStr.split(" __ ");
			 spTenArr = spTenStr.split(" __ ");
			 donviArr = donviStr.split(" __ ");
			 dongiaArr = dongiaStr.split(" __ ");
		 }
		 System.out.println("spIdStr =" + spIdStr);
		 rs.close();
	}*/
	
	public String[] getSpIdArr() {
		return spIdArr;
	}
	public void setSpIdArr(String[] spIdArr) {
		this.spIdArr = spIdArr;
	}
	public String[] getSpMaArr() {
		return spMaArr;
	}
	public void setSpMaArr(String[] spMaArr) {
		this.spMaArr = spMaArr;
	}
	public String[] getSpTenArr() {
		return spTenArr;
	}
	public void setSpTenArr(String[] spTenArr) {
		this.spTenArr = spTenArr;
	}
	public String[] getDongiaArr() {
		return dongiaArr;
	}
	public void setDongiaArr(String[] dongiaArr) {
		this.dongiaArr = dongiaArr;
	}
	public String[] getDonviArr() {
		return donviArr;
	}
	public void setDonviArr(String[] donviArr) {
		this.donviArr = donviArr;
	}
	
	private void createSpRS()
	{
		String sql ="";
		if (this.id.length()>0)
		{
			//System.out.println("this.id : "+ this.id);
			//System.out.println("dvkdid : "+ this.dvkdId +" - kbhid : "+ this.kbhId);
			if (this.dvkdId.length()==0)
			{			
				ResultSet rs = this.db.get("select kbh_fk,dvkd_fk as dvkdId, ten, trangthai from banggiabanlechuan where pk_seq = '" + this.id + "'");
				try
				{
					rs.next();
					this.dvkdId = rs.getString("dvkdId");
					this.ten = rs.getString("ten");
					this.trangthai = rs.getString("trangthai");
					this.kbhId = rs.getString("kbh_fk");
					if(rs!=null)
					{
						rs.close();
					}
				}catch(Exception e){}
			}
			
			sql ="\n select a.pk_seq as id, a.ma, a.ten,isnull( c.giabanlechuan,0) as giabanlechuan " 
				+"\n ,isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)  as Quycach, dv.DIENGIAI as donvi "
				+"\n from sanpham a "
				+"\n inner join donvikinhdoanh b on a.DVKD_FK=b.PK_SEQ "
				+"\n left join banggiablc_sanpham c on c.SANPHAM_FK=a.PK_SEQ "		
				+"\n left join  banggiabanlechuan d on d.PK_SEQ=c.BGBLC_FK "
				+"\n inner join DONVIDOLUONG dv on dv.PK_SEQ = a.DVDL_FK "
				+"\n left join QUYCACH qc  on qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018 and qc.dvdl1_fk=a.dvdl_fk "
				+"\n where kbh_fk = "+ this.kbhId +" "
				+"\n and b.pk_seq = '"+this.dvkdId+"' and isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)<>0 "
				+"\n and c.BGBLC_FK="+this.id+" order by ma"; 
			this.sanphamlist = this.db.get(sql);
			System.out.println("sanphamlist khoi tao : "+sql);
		}else
		{
			if (this.dvkdId.length() > 0)
			{	
				sql=
				"	select distinct a.pk_seq as id, a.ma, a.ten"+//, qc.SOLUONG1/qc.SOLUONG2 as QuyCach "+
				"	from sanpham a, donvikinhdoanh b ,QUYCACH qc  "+
				"	where a.trangthai='1' and  a.dvkd_fk = b.pk_seq "+
				"		and qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018 "+
				"		and b.pk_seq = '" + this.dvkdId + "' "+ 
				"	order by ma ";
				System.out.println("sanphamlist khoi tao : "+sql);
				this.sanphamlist = this.db.get(sql);
			}else
			{
				ResultSet rs = this.db.get("select pk_seq as dvkdId from donvikinhdoanh order by pk_seq");
				try
				{
					
					rs.next();
					this.dvkdId = rs.getString("dvkdId");
					this.ten = "";
					this.trangthai = "1";
					if(rs!=null)
					{
						rs.close();
					}
				}catch(Exception e){}
				sql=	"\n select a.pk_seq as id, a.ma, a.ten,isnull( c.giabanlechuan,0) as giabanlechuan ,isnull((qc.SOLUONG1/nullif(qc.SOLUONG2,0)),0)  as Quycach, dv.DIENGIAI as donvi			" +
						"\n from sanpham a  			" +
						"\n inner join donvikinhdoanh b on a.DVKD_FK=b.PK_SEQ 			" +
						"\n left join banggiablc_sanpham c on c.SANPHAM_FK=a.PK_SEQ and c.BGBLC_FK = ( select top 1 pk_seq from banggiabanlechuan where kbh_fk = "+this.kbhId+" and trangthai = 1 order by tungay desc    )	" +
						"\n left join  banggiabanlechuan d on d.PK_SEQ=c.BGBLC_FK  		" +
						"\n inner join DONVIDOLUONG dv on dv.PK_SEQ = a.DVDL_FK	     " +
						"\n left join QUYCACH qc  on qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018 and qc.dvdl1_fk=a.dvdl_fk  		" +
						"\n where    b.pk_seq = '"+this.dvkdId+"'  " +
						"\n order by ma";
					this.sanphamlist = this.db.get(sql);
					System.out.println("SP LIST 2 : "+sql);
			}
		}
	}
	
	public void createRS()
	{
		createDvkdRS();
		createSpRS();
		createKvRS();
		createLkhRs();
		this.kbhRs = db.get( " select pk_seq , ten from kenhbanhang where trangthai = 1 ");
	}
	 
	public void createKvRS() {
		String query = " SELECT PK_SEQ KVID, TEN KVTEN FROM KHUVUC WHERE TRANGTHAI = '1' ";
		kvRs = db.get(query);
	}
	
	public void init()
	{
		ResultSet rs = this.db.get("select kbh_fk,ten, trangthai, dvkd_fk as dvkdId,tungay from banggiabanlechuan where pk_seq='" + this.id + "'");
		try
		{
			if (rs.next())
			{
				
				this.ten = rs.getString("ten");
				this.trangthai = rs.getString("trangthai");
				this.dvkdId = rs.getString("dvkdId");
				this.tungay=rs.getString("tungay");
				this.kbhId = rs.getString("kbh_fk");
				if(rs!=null){
					rs.close();
				}
				
			}
			else
			{
				this.dvkdId = "100001";
				this.kbhId = "100025";
			}
			
			String query = "select LKH_FK from BANGGIABANLECHUAN_LOAIKHACHHANG where bgblc_fk =" + this.id;
			ResultSet lkhRs = db.get(query);
			String lkhIds = "";
			while (lkhRs.next())
			{
				lkhIds += lkhRs.getString("LKH_FK") + ", ";
			}
			lkhRs.close();
			
			if(lkhIds.trim().length() > 0)
			{
				lkhIds = lkhIds.substring(0, lkhIds.length() - 1);
				this.loaiKhIds = lkhIds;
			}
			
			query = "select KHUVUC_FK from BANGGIABANLECHUAN_KHUVUC where BANGGIABANLECHUAN_FK =" + this.id;
			ResultSet kvRs = db.get(query);
			String kvIds = "";
			while (kvRs.next())
			{
				kvIds += kvRs.getString("KHUVUC_FK") + ", ";
			}
			kvRs.close();
			
			if(kvIds.trim().length() > 0)
			{
				kvIds = kvIds.substring(0, kvIds.length() - 1);
				this.khuvucIds = kvIds;
			}
			System.out.println("this.kbh: "+ this.kbhId);
			if(rs!=null){
				rs.close();
			}
			initSP();	
		}catch(Exception e){}
		
		createRS();
	}

	@Override
	public void DbClose() {
		try{
		if( dvkdIds!=null){
			dvkdIds.close();
		}
		if( newsplist!=null){
			newsplist.close();
		}
		if( sanphamlist!=null){
			sanphamlist.close();
		}
	}catch(Exception er){
		
	}
	}

	@Override
	public String[] getQuycach()
	{
		
		return this.quycach;
	}

	@Override
	public void setQuycach(String[] quycach)
	{
		this.quycach=quycach;
		
	}


	public String getDonViDoLuong() {
		
		return this.donvi;
	}


	public void setDonViDoLuong(String donvi) {
		this.donvi= donvi;
	}
	
	
	public String getTungay() {
		return tungay;
	}

	public void setTungay(String tungay) {
		this.tungay = tungay;
	}

	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public String getKbhId() {
		return kbhId;
	}
	public void setKbhId(String kbhId) {
		this.kbhId = kbhId;
	}
	
	public ResultSet getKbhRs() {
		return kbhRs;
	}
	
	@Override
	public String getKhuvucIds() {
		// TODO Auto-generated method stub
		return this.khuvucIds;
	}

	@Override
	public void setKhuvucIds(String kvIds) {
		// TODO Auto-generated method stub
		this.khuvucIds = kvIds;
	}

	@Override
	public ResultSet getKhuvucRs() {
		// TODO Auto-generated method stub
		return this.kvRs;
	}

	@Override
	public void setKhuvucRs(ResultSet kvRs) {
		// TODO Auto-generated method stub
		this.kvRs = kvRs;
	}
	
	public String getLoaiKhIds() {
		return loaiKhIds;
	}
	public void setLoaiKhIds(String loaiKhIds) {
		this.loaiKhIds = loaiKhIds;
	}
	public ResultSet getLoaiKhRs() {
		return loaiKhRs;
	}
	public void setLoaiKhRs(ResultSet loaiKhRs) {
		this.loaiKhRs = loaiKhRs;
	}

	
	public void createLkhRs() {
		String query = " select pk_seq as loaiId, loai as loaiTEN,diengiai from loaicuahang where trangthai = 1 ";
		loaiKhRs = db.get(query);
	}
}


