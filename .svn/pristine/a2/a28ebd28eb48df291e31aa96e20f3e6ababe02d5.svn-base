package geso.dms.center.beans.mokhoaso.imp;
import geso.dms.center.beans.mokhoaso.*;
import geso.dms.center.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;


public class Mokhoaso implements IMokhoaso{
	String vungId;
	String kvId;
	String nppId;
	String msg;
	ResultSet vung;
	ResultSet kv;
	ResultSet npp;
	ResultSet nppkstd;
	public ResultSet getNppkstd() {
		return nppkstd;
	}
	public void setNppkstd(ResultSet nppkstd) {
		this.nppkstd = nppkstd;
	}

	dbutils db;
	
	public Mokhoaso(){
		this.vungId = "";
		this.kvId = "";
		this.nppId = "";
		this.msg = "";
		this.db = new dbutils();
	}
	public void setVungId(String vungId){
		this.vungId = vungId;
	}
	
	public String getVungId(){
		return this.vungId;
	}
	
	public void setKhuvucId(String kvId){
		this.kvId = kvId;
	}
	
	public String getKhuvucId(){
		return this.kvId;
	}

	public void setNppId(String nppId){
		this.nppId = nppId;
	}
	
	public String getNppId(){
		return this.nppId;
	}
	
	public void setMsg(String msg){
		this.msg = msg;
	}
	
	public String getMsg(){
		return this.msg;
	}

	public void setVung(ResultSet vung){
		this.vung = vung;
	}
	
	public ResultSet getVung(){
		return this.vung;
	}

	public void setKhuvuc(ResultSet kv){
		this.kv = kv;
	}
	
	public ResultSet getKhuvuc(){
		return this.kv;
	}

	public void setNpp(ResultSet npp){
		this.npp = npp;
	}
	
	public ResultSet getNpp(){
		return this.npp;
	}

	public void init(){
		String vung;
		String kv;
		String npp;
		String nppks;
		
		vung = "SELECT PK_SEQ AS VUNGID, TEN FROM VUNG";
		this.vung = this.db.get(vung);
		//System.out.println(vung);
		
		if(this.vungId.length() > 0){
			
			kv = "SELECT PK_SEQ AS KVID, TEN FROM KHUVUC WHERE VUNG_FK = '" + this.vungId + "'";			
			this.kv = db.get(kv);
			
		}else{
			kv ="";
			this.kv = null;
			
		}
		

		if(this.kvId.length() > 0){
			npp = "SELECT PK_SEQ AS NPPID, TEN FROM NHAPHANPHOI WHERE KHUVUC_FK = '" + this.kvId + "'";
			this.npp = db.get(npp);
			nppks="select nppid,ngayks,trangthai from KHAOSOTHANGTUDONG";
			System.out.println("_____________________________"+nppks);
			this.nppkstd=db.get(nppks);
		}else{
			this.npp = null;
			this.nppkstd=null;
		}
		
	}
	
	String thangks,namks,userId;
	
	public String getUserId()
  {
  	return userId;
  }
	public void setUserId(String userId)
  {
  	this.userId = userId;
  }
	public String getThangks()
  {
  	return thangks;
  }
	public void setThangks(String thangks)
  {
  	this.thangks = thangks;
  }
	public String getNamks()
  {
  	return namks;
  }
	public void setNamks(String namks)
  {
  	this.namks = namks;
  }
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
	public String MoKhoaSoNgay()
	{
		
		String query = "SELECT TOP(1) ThangKs,nam FROM KhoaSoThang WHERE NPP_FK = '"+ this.nppId +"' ORDER BY nam DESC,thangks desc ";
		ResultSet rs = this.db.get(query);
		try
		{
			
			this.db.getConnection().setAutoCommit(false);
			
			if(rs.next())
			{
				namks = rs.getString("nam");
				thangks = rs.getString("thangks");
			}
			rs.close();
						
			query=
			"insert into MoKhoaSo(Thang,Nam,npp_fk,NguoiTao,NgayTao,NguoiSua,NgaySua) " +
			" select '"+this.thangks+"','"+this.namks+"','"+this.nppId+"','"+this.userId+"','"+this.getDateTime()+"','"+this.userId+"','"+this.getDateTime()+"'   ";
			if(!this.db.update(query))
			{
				db.update("rollback");
				return "Không thể mở khóa sổ tháng: " + query;
			}
			
			 String hdId = "";
			 query = "select SCOPE_IDENTITY() as hdId";
			 ResultSet rs1 = db.get(query);
			 rs1.next();
			 hdId = rs1.getString("hdId");
			
			query=
			"insert into MoKhoaSo_TonKho(MoKhoaSo_fk,npp_fk,kbh_fk,kho_fk,sanpham_fk,soluong,thang,nam) " +
			"select '"+hdId+"','"+this.nppId+"',kbh_fk,kho_fk,sanpham_fk,soluong,thang,nam " +
			"from TonKhoThang where npp_fk='"+this.nppId+"'  and thang='"+this.thangks+"' and nam='"+this.namks+"'  ";
			if(!this.db.update(query))
			{
				db.update("rollback");
				return "Không thể mở khóa sổ tháng: " + query;
			}
			
			query=
			"insert into MoKhoaSo_TonKho_ChiTiet(MoKhoaSo_fk,npp_fk,kbh_fk,kho_fk,sanpham_fk,soluong,thang,nam,SoLo,NgayHetHan) " +
			"select '"+hdId+"','"+this.nppId+"',kbh_fk,kho_fk,sanpham_fk,soluong,thang,nam,SoLo,NgayHetHan " +
			"from TonKhoThang_ChiTiet where npp_fk='"+this.nppId+"'  and thang='"+this.thangks+"' and nam='"+this.namks+"'  ";
			if(!this.db.update(query))
			{
				db.update("rollback");
				return "Không thể mở khóa sổ tháng: " + query;
			}

			
			int SoDong=0;
			query= "select count(*) as SoDong from KhoaSoThang where npp_fk='"+this.nppId+"'";
			rs =db.get(query);
			while(rs.next())
			{
				SoDong = rs.getInt("SoDong");
			}
			if(rs!=null)rs.close();
			if(SoDong==1)
			{
				db.update("rollback");
				return "Không thể mở khóa sổ tháng đầu kỳ ";
			}
			
			/*if(msg.trim().length() <= 0)*/
			{
				query = "DELETE FROM KhoaSoThang WHERE NPP_FK = '" + this.nppId + "' AND thangks = '" + thangks + "' and nam='"+namks+"' ";
				if(!this.db.update(query))
				{
					db.update("rollback");
					return "Không thể mở khóa sổ tháng: " + query;
				}
				
				query = "DELETE FROM TonKhoThang WHERE NPP_FK = '" + this.nppId + "' AND Thang='"+this.thangks+"' and nam='"+namks+"'  ";
				if(!this.db.update(query))
				{
					db.update("rollback");
					return "Không thể mở khóa sổ tháng: " + query;
				}
				query = "DELETE FROM TonKhoThang_CHITIET WHERE NPP_FK = '" + this.nppId + "' AND Thang='"+this.thangks+"' and nam='"+namks+"'  ";
				if(!this.db.update(query))
				{
					db.update("rollback");
					return "Không thể mở khóa sổ tháng: " + query;
				}
				this.db.getConnection().commit();
				this.db.getConnection().setAutoCommit(true);
				return "Mở khóa sổ ("+this.thangks+" -"+this.namks+" )  thành công";
			}
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			this.db.update("rollback");
			return "Không thể mở khóa sổ tháng : " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		
	}
	
	private String checkDH(String ngayks){
		
		String query = "SELECT COUNT(PK_SEQ) AS NUM FROM DONHANG " +
					   "WHERE NGAYNHAP > '"+ngayks+"' " +
					   "AND TRANGTHAI IN ('1','3') AND NPP_FK = '" + this.nppId + "'";
		
		System.out.println("1.Check don hang: " + query);	
		ResultSet rs = db.get(query);
		boolean check = false;
		try
		{
			rs.next();
			if (rs.getString("NUM").equals("0"))
			{
				check = true;
			}
			rs.close();
		}
		catch(Exception e){ return e.getMessage(); }
		
		if(!check){
			return "Có đơn hàng đã chốt sau ngày khóa sổ (" + ngayks + "), bạn không thể mở khóa sổ ngày (" + ngayks + ") được";
		}
		
		query="SELECT PK_SEQ FROM NHAPHANG WHERE trangthai='1' and NGAYNHAN > '"+ngayks+"' " +
				" AND NPP_FK = " + this.nppId;
		System.out.println("2.Check nhaphang: "+query);
		
		check = false;
		rs = db.get(query);
		
		try
		{
			if(rs.next())
			{
				check = true;
			}
			rs.close();
		}
		
		catch(Exception e)
		{
			return e.getMessage();
		}
		
		if(check)
		{
			return "Có nhận hàng đã chốt sau ngày khóa sổ (" + ngayks + "), bạn không thể mở khóa sổ ngày (" + ngayks + ") được";
		}
		
		query="SELECT PK_SEQ FROM DIEUCHINHTONKHO WHERE trangthai='1' and NGAYDC >'"+ngayks+"' " +
				" AND NPP_FK = " + this.nppId;
		System.out.println("2.Check DIEU CHINH TON KHO: "+query);
		
		check = false;
		rs = db.get(query);
		
		try
		{
			if(rs.next())
			{
				check = true;
			}
			rs.close();
		}
		
		catch(Exception e)
		{
			return e.getMessage();
		}
		
		if(check)
		{
			return "Có Kiểm kho đã chốt sau ngày khóa sổ (" + ngayks + "), bạn không thể mở khóa sổ ngày (" + ngayks + ") được";
		}
		return "";
		
	}
	
	public void DBClose(){
		try{
			if(this.vung != null) this.vung.close();
			if(this.kv != null) this.kv.close();
			if(this.npp != null) this.npp.close();
			if(this.db!=null){
				this.db.shutDown();
			}
		}catch(Exception e){}
	}
	public boolean KhoaSoNgay(String nppids) 
	{	
		System.out.println("::::::::::::::::::::::::::::::::::::::oalal");
		String query="";
		this.msg="";
	
		dbutils dbks=new dbutils();
		 query = "select top(1) NAM as namMax, THANGKS as thangMax " +
						"from KHOASOTHANG where NPP_FK = '" + nppids + "' order by NAM desc, THANGKS desc ";
		System.out.println("1.Khoi tao thang: " + query);
		ResultSet rs = dbks.get(query);

		String thangKsMax = "";
		String namKsMax = "";
		{
			try
	        {
		        while(rs.next())
		        {
		        	thangKsMax = rs.getString("thangMax");
		        	namKsMax = rs.getString("namMax"); 
	
		        	if(thangKsMax.equals("12"))
		        	{
		        		this.thangks = "1";
		        		this.namks = Integer.toString(Integer.parseInt(namKsMax) + 1);
		        	}
		        	else
		        	{
		        		this.thangks = Integer.toString(Integer.parseInt(thangKsMax) + 1);
		        		this.namks = namKsMax;
		        	}
		        }
		        if(rs!=null)rs.close();
	        } 
			catch (SQLException e)
	        {
		        e.printStackTrace();
	        }
		}
		
		
		String kq1=createDhccList(nppids);
		if(!kq1.equals(""))
		{
			this.msg=kq1;
			return false;
		}
		try 
		{
			
			if(this.thangks.trim().length() <= 0)
			{
				this.init_THANG_NAM_KHOASO();
			}
			String sql = "select thangks, nam from khoasothang where npp_fk='"+nppids+"' order by nam desc, thangks desc";
			 rs = dbks.get(sql);
			int thangKSMax = 0;
			{
				if(rs.next())
				{
					thangKSMax = rs.getInt("thangks");
				}
			}
			
			if( (Integer.parseInt(this.thangks) - thangKSMax) > 1 )
			{
				this.msg = " Vui lòng kiểm tra lại tháng muốn khóa sổ";
				return false;
			}
			
			
			
			String tungay="";
			String denngay="";
			if(this.thangks.length()<2)
			{
				tungay=this.namks+"-0"+this.thangks+"-01";
				denngay=this.namks+"-0"+this.thangks+"-"+LastDayOfMonth(Integer.parseInt(this.thangks), Integer.parseInt(this.namks));
			}
			else
			{
				tungay=this.namks+"-"+this.thangks+"-01";
				denngay=this.namks+"-"+this.thangks+"-"+LastDayOfMonth(Integer.parseInt(this.thangks), Integer.parseInt(this.namks));
			}
			sql="select COUNT(*) as sl from ufn_XNT_Total_FULL('"+tungay+"','"+denngay+"',"+nppids+",null,null) where NXT<0 and SOLUONG <>0";
			System.out.println("::::::::::::::::::-"+sql);
			rs=db.get(sql);
			if(rs.next())
			{
				if(rs.getInt("sl")>0)
				{
					this.msg="XNT âm không thể khóa sổ tháng";
					return false;
				}
			}
			//FIX tự động nếu có bị lệch tổng và chi tiết
			/*FixData fixed = new FixData();
			String error = fixed.ProcessDATA(nppId, "");
			if( error.trim().length() > 0 )
			{
				this.msg = "Có lỗi khi khóa sổ tháng. Vui lòng liên hệ với Admin để được xử lý";
				return false;
			}*/
			
			dbks.getConnection().setAutoCommit(false);
			query=
			 "   declare @ThangKs int ,@NamKs int,@nppId numeric(18,0),@Thang int,@Nam int,@TuNgay char(10),@DenNgay char(10)   \n "+      
		   "   set @ThangKs = '"+this.thangks+"'   \n "+      
		   "   set @NamKs   =  '"+this.namks+"'   \n "+      
		   "   set @nppId=('"+nppids+"' )   \n "+      
		   "   select top(1) @Thang=THANGKS,@Nam=NAM from KHOASOTHANG where NPP_FK=@nppId order by NAM desc,THANGKS desc   \n "+      
		   "   set @TuNgay=cast(@Nam as CHAR(4))+'-'+case    \n "+      
		   "   	when len(dbo.Trim( cast( @Thang as varchar(2)) )) <2 then '0' +cast( @Thang as varchar(2)) else cast( @Thang as varchar(2)) end +'-01'   \n "+      
		   "      \n "+      
		   "   select @TuNgay =(select convert(varchar(10),DATEADD(month,1,@TuNgay),20) )      \n "+      
		   " select @DenNgay= convert(varchar(10), DATEADD(DD,-1,DATEADD(mm,1,@TuNgay)) ,20)    \n"+      
		   "      \n "+      
		   "   INSERT INTO TONKHOTHANG(NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLUONG,THANG,NAM)   \n "+      
		   "   SELECT DATA.NPP_FK,DATA.KBH_FK,DATA.KHO_FK,DATA.SANPHAM_FK,DATA.soluong,'"+this.thangks+"','"+this.namks+"'   \n "+      
		   "   from    \n "+      
		   "   (   \n "+      
		   "   	select xnt.NPP_FK,xnt.KBH_FK,xnt.KHO_FK,xnt.sanpham_fk,sum(soluong) as soluong    \n "+      
		   "   	from      \n "+      
		   "   	(      \n "+      
		   "   	   \n "+      
		   "   	select  npp_fk,kbh_fk, kho_fk, sanpham_fk, sum(soluong) as soluong ,N'Tồn đầu' as type 			      \n "+      
		   "   	from TONKHOTHANG			      \n "+      
		   "   	where  THANG=@Thang and NAM=@Nam and NPP_FK=@nppId   \n "+      
		   "   	group by kbh_fk, npp_fk, kho_fk, sanpham_fk	      \n "+      
		   "      \n "+      
		   "   	union all      \n "+      
		   "      \n "+      
		   "   	select b.npp_fk,b.kbh_fk,a.khonhan_fk as kho_fk,c.pk_seq as sanpham_fk,sum(cast(soluongnhan as int)) as soluong,N'Nhập hàng' as type       \n "+      
		   "   	from nhaphang_sp a inner join nhaphang b on a.nhaphang_fk = b.pk_seq		      \n "+      
		   "   		inner join sanpham c on c.pk_seq = a.sanpham_fk       \n "+      
		   "   	where   b.trangthai =1 and b.NGAYNHAN>=@TuNgay and b.NGAYNHAN<=@DenNgay   \n "+      
		   "   	group by b.npp_fk,b.kbh_fk,c.pk_seq,a.khonhan_fk 	      \n "+      
		   "      \n "+      
		   "   	union all      \n "+      
		   "      \n "+      
		   "   	select ck.npp_fk, ck.kbh_fk, ck.khoxuat_fk as kho_Fk, ck.sanpham_fk ,(-1)*sum(soluong) as SoLuong,N'Xuất chuyển nội bộ' as Type      \n "+      
		   "   	from       \n "+      
		   "   	(      \n "+      
		   "   	select  c.npp_fk, c.kbh_fk, c.khoxuat_fk, a.sanpham_fk,            \n "+      
		   "   		case when a.dvdl_fk IS null then a.soluongchuyen             \n "+      
		   "   			 when a.dvdl_fk = b.DVDL_FK then a.soluongchuyen            \n "+      
		   "   			 else  a.soluongchuyen * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )             \n "+      
		   "   							 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong          \n "+      
		   "   	from ERP_CHUYENKHONPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ         \n "+      
		   "   	inner join ERP_CHUYENKHONPP c on a.chuyenkho_fk = c.pk_seq         \n "+      
		   "   	where c.trangthai=1 and  c.NgayChuyen>=@TuNgay and c.NgayChuyen<=@DenNgay   \n "+      
		   "   	)as ck      \n "+      
		   "   	group by ck.npp_fk, ck.kbh_fk, ck.khoxuat_fk, ck.sanpham_fk 	      \n "+      
		   "      \n "+      
		   "   	union all      \n "+      
		   " select  a.NPP_FK, a.kbh_fk, a.kho_fk, b.SANPHAM_FK,-(1)*SUM(b.soluong) as soluong ,'Xuất ETC'  as NghiepVu \n " + 
		   " from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.YCXK_FK   \n  " + 
		   " where  b.soluong > 0  \n  " + 
		   "	and a.NPP_FK=@nppId and a.PK_SEQ in (Select ycxk_fk from ERP_YCXUATKHONPP_DDH  where ddh_fk in  \n  " + 
		   "	(select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK in (select PK_SEQ from ERP_HOADONNPP where TRANGTHAI not IN (3,5)  \n " + 
		   "	and NGAYXUATHD>=@TuNgay AND NGAYXUATHD <=@DenNgay )) ) and a.trangthai != '3'" + 
		   "group by a.kho_fk, a.kbh_fk, a.NPP_FK, b.SANPHAM_FK  \n  " +
		   "   	union all      \n "+      
		   "      \n "+      
		   "   	SELECT DTH.NPP_FK,DTH.KBH_FK ,DTH.KHO_FK ,THSP.SANPHAM_FK,(-1)* SUM(THSP.SOLUONG) AS SOLUONG ,N'Trả Hàng về NCC' AS TYPE      \n "+      
		   "   	FROM DONTRAHANG_SP THSP INNER JOIN  DONTRAHANG DTH ON  DTH.PK_SEQ = THSP.DONTRAHANG_FK      \n "+      
		   "   	WHERE DTH.TRANGTHAI =2 AND DTH.NGAYTRA >=@TuNgay AND DTH.NGAYTRA <=@DenNgay AND THSP.SOLUONG > 0       \n "+      
		   "   	GROUP BY DTH.NPP_FK,DTH.KBH_FK,THSP.SANPHAM_FK,DTH.KHO_FK	      \n "+      
		   "      \n "+      
		   "   	union all      \n "+      
		   "      \n "+      
		   "   	SELECT  DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK ,      \n "+      
		   "   		SUM( CAST( ISNULL(DCTK_SP.DIEUCHINH,0) AS INT) ) AS SOLUONG, N'Kiểm kho' AS TYPE 	        \n "+      
		   "   	FROM	DIEUCHINHTONKHO DCTK  INNER JOIN DIEUCHINHTONKHO_SP DCTK_SP ON DCTK_SP.DIEUCHINHTONKHO_FK = DCTK.PK_SEQ       \n "+      
		   "   	WHERE   DCTK.TRANGTHAI =1 AND DCTK.NGAYDC >= @TuNgay AND DCTK.NGAYDC <= @DenNgay   \n "+      
		   "   	GROUP BY  DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK      \n "+      
		   "      \n "+      
		   "      \n "+      
		   "      \n "+      
		  " union all "+
			"	select dh.npp_fk,dh.KBH_FK,dh.KHO_FK,dhsp.sanpham_fk,(-1)*(soluong) as soluong ,N'Xuất hàng bán' as NghiepVu   \n"+       
			"	from hoadon_sp dhsp         \n"+
			"	inner join hoadon dh on dh.pk_seq = dhsp.hoadon_fk \n"+  	      
			"	where dh.trangthai not in (3,5) and isnull(dh.LOAIHOADON,0)=0 and dh.ngayxuathd >= @TuNgay  and dh.ngayxuathd <= @DenNgay \n"+   
			"	and dh.NPP_FK=@nppId   \n"+		   
		   "      \n "+      
		   "   		union all      \n "+      
		   "   		   \n "+      
			 "		select NPP_FK,kbh_fk,kho_Fk,sanpham_fk,(-1)*SUM(SoLuong) as SoLuong,N'Xuất KM' AS NghiepVu  \n "+
			 "		from \n "+ 
			 "		(  \n "+
			 "			select	a.KBH_FK,a.npp_fk,c.pk_Seq as sanpham_Fk,b.kho_Fk,  \n "+
			 "		case when len(dbo.Trim(b.SOLO))=0 then 'NA' ELSE dbo.Trim(b.SOLO) end  as SoLo,  \n "+
			 "				b.SOLUONG as SoLuong,case when len(dbo.Trim(b.SOLO))=0 then '2030-12-31' ELSE dbo.Trim(b.ngayhethan) end  as NgayHetHan \n "+
			 "			from  HOADON a inner join HOADON_CTKM_TRAKM_CHITIET b on b.hoadonID=a.PK_SEQ \n "+
			  "				inner join SANPHAM c on c.PK_SEQ=b.sanpham_fk \n "+
			 "				where a.NPP_FK=@nppId and a.NGAYXUATHD>=@TuNgay and a.NGAYXUATHD<=@DenNgay  \n "+
			 "				and a.TRANGTHAI not in (3,5) and a.LOAIHOADON in (1,2) \n "+
			 "	) hd \n "+
			"		group by hd.NPP_FK,hd.SANPHAM_FK,hd.KHO_FK,hd.SoLo,KBH_FK \n "+
		   "   	   \n "+      
		   "   		   \n "+      
			"		union all    \n "+
			"		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,(-1)* SUM(b.soluong) as tongxuat ,N'L.Đổi kênh(-)' as NghiepVu  \n "+            
			"		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk              \n "+
			"		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay and a.KHONHAN_FK is  null                 \n "+  
			"		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk             \n "+
					            
			"		union all             \n "+
			"		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,SUM(b.soluong) as tongxuat ,N'M.Đổi kênh(+)' as NghiepVu \n "+            
			"		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk              \n "+
			"		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay and a.KHONHAN_FK is  null             \n "+      
			"		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk            \n "+
					            
			"		union all             \n "+
			"		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,(-1)* SUM(b.soluong) as tongxuat ,N'N.Chuyển kho(-)' as NghiepVu \n "+            
			"		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk                        \n "+
			"		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay     and a.KHONHAN_FK is not null                   \n "+
			"		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk                      \n "+
			"		union all                      \n "+
			"		select a.NPP_FK,a.KBH_FK,a.KHONHAN_FK, b.sanpham_fk,SUM(b.soluong) as tongxuat ,N'O.Chuyển kho(+)' as NghiepVu \n "+            
			"		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk                      \n "+  
			"		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay  and a.KHONHAN_FK is not null                  \n "+
			"		group by a.KHONHAN_FK,a.KBH_FK, a.npp_fk, b.sanpham_fk \n "+			 
			 "		union all \n "+
			 "		select  a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_Fk,SUM(b.SoLuong) as SoLuong,N'Hàng trả lại' as Type \n "+
			 "	from Erp_HangTraLaiNpp a inner join Erp_HangTraLaiNpp_SanPham b on b.hangtralai_fk=a.pk_Seq \n "+
			 "		where a.trangthai=1  and a.ngaytra>=@TuNgay and a.ngaytra <=@DenNgay   and a.npp_fk=@nppId \n "+
			 "	group by a.npp_fk,b.sanpham_Fk,a.kbh_fk,a.kho_fk 		    \n "+
				"		 union all \n "+
			  "   		select a.tructhuoc_fk as npp_Fk, a.kbh_fk,a.KhoXuat_FK as kho_fk , b.sanpham_fk,(-1)*SUM(b.soluongchuyen) as tongxuat ,N'Xuất kho khác' as Type   \n "+      
			  "   		from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b on a.pk_seq = b.chuyenkho_fk    \n "+      
			  "   		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay  and a.TrucThuoc_FK=@nppId    \n "+      
			  "   		group by a.khoxuat_fk, a.tructhuoc_fk, a.kbh_fk, b.sanpham_fk   \n "+      
		   "   	)as xnt   \n "+      
		   "   	group by xnt.NPP_FK,xnt.KBH_FK,xnt.KHO_FK,xnt.sanpham_fk   \n "+      
		   "   ) as DATA    \n "+      
		   "   where    \n "+      
		   "   DATA.NPP_FK=@nppId     \n ";
			
			System.out.println("____________"+query);
			
			if(dbks.updateReturnInt(query)<=0)
			{
				dbks.getConnection().rollback();
				this.msg = " Không thể thiết lập khóa sổ tháng " + query;
				return false;
			}
			
			query=
			 "   declare @ThangKs int ,@NamKs int,@nppId numeric(18,0),@Thang int,@Nam int,@TuNgay char(10),@DenNgay char(10)   \n "+      
		   "   set @ThangKs = '"+this.thangks+"'   \n "+      
		   "   set @NamKs   =  '"+this.namks+"'   \n "+      
		   "   set @nppId=('"+nppids+"' )   \n "+      
		   "   select top(1) @Thang=THANGKS,@Nam=NAM from KHOASOTHANG where NPP_FK=@nppId order by NAM desc,THANGKS desc   \n "+      
		   "   set @TuNgay=cast(@Nam as CHAR(4))+'-'+case    \n "+      
		   "   	when len(dbo.Trim( cast( @Thang as varchar(2)) )) <2 then '0' +cast( @Thang as varchar(2)) else cast( @Thang as varchar(2)) end +'-01'   \n "+      
		   "      \n "+      
		   "   select @TuNgay =(select convert(varchar(10),DATEADD(month,1,@TuNgay),20) )      \n "+      
		   " select @DenNgay= convert(varchar(10), DATEADD(DD,-1,DATEADD(mm,1,@TuNgay)) ,20)    \n"+
		   " 	insert into TONKHOTHANG_CHITIET(NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLO,NGAYHETHAN,THANG,NAM,SOLUONG) "+
			 "	select DATA.NPP_FK,KBH_FK ,DATA.KHO_FK,DATA.SANPHAM_FK,SoLo,NgayHetHan,@ThangKs,@NamKs,DATA.soluong as TonCuoi   \n "+      
			 "   from    \n "+      
			 "   (   \n "+      
			 "   	select xnt.NPP_FK,KBH_FK,xnt.KHO_FK,xnt.sanpham_fk,SUM(soluong) as soluong ,SoLo,NgayHetHan      \n "+      
			 "   	from         \n "+      
			 "   	(         \n "+      
			 "   		select  npp_fk, KBH_FK,kho_fk, SANPHAM_FK,SOLO as SoLo, sum(soluong) as soluong ,N'A.Tồn đầu' as type,NGAYHETHAN as NgayHetHan			       \n "+      
			 "   		from TONKHOTHANG_CHITIET      \n "+      
			 "   		where   NPP_FK=@nppId and THANG=@Thang and NAM=@Nam       \n "+      
			 "   		group by  npp_fk, kho_fk, SANPHAM_FK,SOLO,NGAYHETHAN,KBH_FK      \n "+      
			 "   	      \n "+      
			"   		union all         \n "+      
			"   		      \n "+      
			"   		select b.npp_fk,b.KBH_FK,a.khonhan_fk as kho_fk,c.pk_seq as sanpham_fk,a.SOLO, sum(cast(soluongnhan as int)) as soluong,     \n "+      
			"   			N'B.Nhập hàng' as type  ,NGAYHETHAN        \n "+      
			"   		from nhaphang_sp a inner join nhaphang b on a.nhaphang_fk = b.pk_seq		         \n "+      
			"   			inner join sanpham c on c.pk_seq = a.sanpham_fk          \n "+      
			"   		where   b.trangthai =1 and b.NGAYNHAN>=@TuNgay and b.NGAYNHAN<=@DenNgay   \n "+      
			"   		group by b.npp_fk,b.KBH_FK,c.pk_seq,a.khonhan_fk ,SOLO	   ,NGAYHETHAN      \n "+      
			"         \n "+      
			"   		union all         \n "+      
			"         \n "+      
			"   		select ck.npp_fk, ck.KBH_FK,ck.khoxuat_fk as kho_Fk, ck.sanpham_fk ,ck.solo,(-1)*sum(soluong) as SoLuong,N'C.Xuất chuyển nội bộ' as Type ,      \n "+      
			"   			ngayhethan        \n "+      
			"   		from          \n "+      
			"   		(         \n "+      
			"   			select  c.npp_fk, c.kbh_fk, c.khoxuat_fk, a.sanpham_fk,  a.soluong    ,a.solo,a.ngayhethan      \n "+      
			"   			from ERP_CHUYENKHONPP_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ            \n "+      
			"   			inner join ERP_CHUYENKHONPP c on a.chuyenkho_fk = c.pk_seq            \n "+      
			"   			where c.trangthai=1 and  c.NgayChuyen>=@TuNgay  and c.NPP_FK=@nppId   and c.NgayChuyen<=@DenNgay    \n "+      
			"   		)as ck         \n "+      
			"   		group by ck.npp_fk, ck.khoxuat_fk, ck.sanpham_fk ,ck.solo	,ck.ngayhethan,ck.KBH_FK      \n "+      
			"   		         \n "+      
			"   		union all      \n "+      
			"   	      \n "+      
			"   		select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.solo,SUM(b.Soluong) as SoLuong,N'E.Đổi số lô(+)' as Type,b.ngayhethan      \n "+      
			"   		from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq      \n "+      
			"   		where a.trangthai=1 and ngaydoi>=@TuNgay     and ngaydoi<=@DenNgay   \n "+      
			"   		group by a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.solo,b.ngayhethan      \n "+      
			"         \n "+      
			"   		union all 			      \n "+      
			"   			select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.soloOLD,(-1)*SUM(b.Soluong) as SoLuong,N'F.Đổi số lô(-)' as Type,NgayHetHanOLD      \n "+      
			"   			from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq      \n "+      
			"   			where a.trangthai=1 and ngaydoi>=@TuNgay and ngaydoi<=@DenNgay       \n "+      
			"   			group by a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.soloOLD,NgayHetHanOLD      \n "+      
			"   	      \n "+      
			"   		union all       \n "+      
			"         \n "+      
			"   		select  a.NPP_FK,a.KBH_FK,a.kho_fk, b.SANPHAM_FK, b.solo,-(1)*SUM(b.soluong) as soluong ,'ETC'  as NghiepVu,ngayhethan   \n "+      
			"   		from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.YCXK_FK     \n "+      
			"   		where  b.soluong > 0    \n "+      
			"   			and a.NPP_FK=@nppId and a.PK_SEQ in (Select ycxk_fk from ERP_YCXUATKHONPP_DDH  where ddh_fk in    \n "+      
			"   			(select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK in (select PK_SEQ from ERP_HOADONNPP where TRANGTHAI not IN (3,5)   \n "+      
			"   			and NGAYXUATHD>=@TuNgay and NGAYXUATHD<=@DenNgay   \n "+      
			"   			)))and a.TRANGTHAI!=3   \n "+      
			"   		group by a.kho_fk, a.NPP_FK, b.SANPHAM_FK, b.solo,ngayhethan,a.KBH_FK       \n "+      
			"   		union all         \n "+      
			"   	      \n "+      
			"   		SELECT DTH.NPP_FK ,DTH.KBH_FK,DTH.KHO_FK ,THSP.SANPHAM_FK,THSP.SoLo,(-1)* SUM(THSP.SOLUONG) AS SOLUONG ,N'H.Trả hàng' AS TYPE ,NgayHetHan      \n "+      
			"   		FROM DONTRAHANG_SP THSP INNER JOIN  DONTRAHANG DTH ON  DTH.PK_SEQ = THSP.DONTRAHANG_FK         \n "+      
			"   		WHERE DTH.TRANGTHAI =2 AND DTH.NGAYTRA >=@TuNgay  AND THSP.SOLUONG > 0       and DTH.NGAYTRA<=@DenNgay   \n "+      
			"   		GROUP BY DTH.NPP_FK,DTH.KBH_FK,THSP.SANPHAM_FK,DTH.KHO_FK,THSP.SoLo,NgayHetHan	         \n "+      
			"         \n "+      
			"   		union all         \n "+      
			"         \n "+      
			"   		SELECT  DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK , DCTK_SP.SOLO,      \n "+      
			"   			SUM( CAST( ISNULL(DCTK_SP.DIEUCHINH,0) AS INT) ) AS SOLUONG, N'I.Kiểm kho' AS TYPE 	     ,NgayHetHan      \n "+      
			"   		FROM	DIEUCHINHTONKHO DCTK  INNER JOIN DIEUCHINHTONKHO_SP DCTK_SP ON DCTK_SP.DIEUCHINHTONKHO_FK = DCTK.PK_SEQ          \n "+      
			"   		WHERE   DCTK.TRANGTHAI =1 AND DCTK.NGAYDC >= @TuNgay   and DCTK.NGAYDC<=@DenNgay   \n "+      
			"   		GROUP BY  DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK ,DCTK_SP.SOLO  ,NgayHetHan      \n "+      
			"         \n "+      
			"   		union all         \n "+      
			"   		      \n "+      
			"   		select NPP_FK,hd.KBH_FK,kho_Fk,sanpham_fk,SoLo,(-1)*SUM(SoLuong) as SoLuong,N'J.Xuất bán ' AS Type,NGAYHETHAN as NgayHetHan      \n "+      
			"   		from       \n "+      
			"   		(      \n "+      
			"   			select  a.KBH_FK,a.NPP_FK,c.PK_SEQ as sanpham_fk,      \n "+      
			"   				(      \n "+      
			"   					select top(1) kho_fk from DONHANG aa inner join HOADON_DDH bb on bb.DDH_FK=aa.PK_SEQ      \n "+      
			"   						where bb.HOADON_FK=a.PK_SEQ      \n "+      
			"   				) as kho_Fk,      \n "+      
			"   			case when len(dbo.Trim(b.SOLO))=0 then 'NA' ELSE dbo.Trim(b.SOLO) end  as SoLo,      \n "+      
			"   				b.SOLUONG as SoLuong,      \n "+      
			"   				case when len(dbo.Trim(b.SOLO))=0 then '2030-12-31' ELSE dbo.Trim(b.NGAYHETHAN) end  as NGAYHETHAN      \n "+      
			"   			from  HOADON a inner join HOADON_SP_CHITIET b on b.hoadon_fk=a.PK_SEQ      \n "+      
			"   				inner join SANPHAM c on c.MA=b.MA      \n "+      
			"   				where a.NPP_FK=@nppId and a.NGAYXUATHD>=@TuNgay  and a.NGAYXUATHD<=@DenNgay   \n "+      
			"   				and a.TRANGTHAI not in (3,5) and a.LOAIHOADON=0      \n "+      
			"   		) hd      \n "+      
			"   		group by hd.NPP_FK,hd.SANPHAM_FK,hd.KHO_FK,hd.SOLO	,NGAYHETHAN	,hd.KBH_FK      \n "+      
			"   		      \n "+      
			"   		union all      \n "+      
			"   		      \n "+      
			"   		select NPP_FK,kbh_fk,kho_Fk,sanpham_fk,SoLo as SoLo,(-1)*SUM(SoLuong) as SoLuong,N'Xuất KM' AS NghiepVu,NgayHetHan   \n "+      
			"   		from    \n "+      
			"   		(   \n "+      
			"   			select	a.npp_fk,c.pk_Seq as sanpham_Fk,b.kho_Fk,b.kbh_fk,   \n "+      
			"   			case when len(dbo.Trim(b.SOLO))=0 then 'NA' ELSE dbo.Trim(b.SOLO) end  as SoLo,   \n "+      
			"   				b.SOLUONG as SoLuong,case when len(dbo.Trim(b.SOLO))=0 then '2030-12-31' ELSE dbo.Trim(b.ngayhethan) end  as NgayHetHan   \n "+      
			"   			from  HOADON a inner join HOADON_CTKM_TRAKM_CHITIET b on b.hoadonID=a.PK_SEQ   \n "+      
			"   				inner join SANPHAM c on c.PK_SEQ=b.sanpham_fk   \n "+      
			"   				where a.NPP_FK=@nppId and a.NGAYXUATHD>=@TuNgay and a.NGAYXUATHD<=@DenNgay   \n "+      
			"   				and a.TRANGTHAI not in (3,5) and a.LOAIHOADON in (1,2)   \n "+      
			"   		) hd   \n "+      
			"   		group by hd.NPP_FK,hd.SANPHAM_FK,hd.KHO_FK,hd.SoLo,NgayHetHan,kbh_fk   \n "+      
			"   		      \n "+      
			"   		union all      \n "+      
			"   		      \n "+      
			"   		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,(-1)* SUM(b.soluong) as tongxuat ,N'L.Đổi kênh(-)' as Type,b.ngayhethan      \n "+      
			"   		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk       \n "+      
			"   		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay and a.KHONHAN_FK is  null            \n "+      
			"   		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk,b.solo,b.ngayhethan      \n "+      
			"   		      \n "+      
			"   		union all      \n "+      
			"   		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,SUM(b.soluong) as tongxuat ,N'M.Đổi kênh(+)' as Type,b.ngayhethan      \n "+      
			"   		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk       \n "+      
			"   		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay and a.KHONHAN_FK is  null            \n "+      
			"   		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk,b.solo,b.ngayhethan      \n "+      
			"   		      \n "+      
			"   		union all       \n "+      
			"   		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,(-1)* SUM(b.soluong) as tongxuat ,N'N.Chuyển kho(-)' as Type,b.ngayhethan      \n "+      
			"   		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk                 \n "+      
			"   		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay     and a.KHONHAN_FK is not null            \n "+      
			"   		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk  ,solo,b.ngayhethan              \n "+      
			"   		union all                \n "+      
			"   		select a.NPP_FK,a.KBH_FK,a.KHONHAN_FK, b.sanpham_fk, solo,SUM(b.soluong) as tongxuat ,N'O.Chuyển kho(+)' as Type,b.ngayhethan      \n "+      
			"   		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk                 \n "+      
			"   		where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay  and a.KHONHAN_FK is not null            \n "+      
			"   		group by a.KHONHAN_FK,a.KBH_FK, a.npp_fk, b.sanpham_fk,solo ,ngayhethan      \n "+      
			"   		      \n "+      
			"   		union all      \n "+      
			"   		      \n "+      
			"   		select  a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_Fk,b.solo ,SUM(b.SoLuong) as SoLuong,N'P.Hàng trả lại' as Type,b.NgayHetHAN          \n "+      
			"   		from Erp_HangTraLaiNpp a inner join Erp_HangTraLaiNpp_SanPham b on b.hangtralai_fk=a.pk_Seq      \n "+      
			"   		where a.trangthai=1  and a.ngaytra>=@TuNgay  and a.ngaytra<=@DenNgay  and a.npp_fk=@nppId      \n "+      
			"   		group by a.npp_fk,a.kbh_fk,b.sanpham_Fk,a.kho_fk,b.SoLo,b.NgayHetHAN      \n "+      
			"	union all  "+
			" select a.TrucThuoc_FK as npp_fk,a.KBH_FK ,a.KhoXuat_FK as Kho_FK,b.sanpham_fk,b.solo,(-1)*sum(b.soluong) as SoLuong,N'Xuất hàng khác' as NghiepVu,b.NgayHetHan  "+
			" from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on b.chuyenkho_fk=a.PK_SEQ  "+
			" where a.TRANGTHAI=1 and a.TrucThuoc_FK=@nppId and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay  "+
			" group by a.KhoXuat_FK,b.sanpham_fk,a.TrucThuoc_FK,a.KBH_FK,b.solo,b.ngayhethan "+
			"   	)as xnt      \n "+      
			"   	group by xnt.NPP_FK,xnt.KHO_FK,xnt.sanpham_fk,xnt.KBH_FK ,SoLo,NgayHetHan     \n "+      
			"   ) as DATA    \n "+      
			"   where DATA.NPP_FK=@nppId ";
			
			System.out.println("XNT_CT_"+query);
			
			if(dbks.updateReturnInt(query)<=0)
			{
				dbks.getConnection().rollback();
				this.msg = " Không thể thiết lập khóa sổ tháng " + query;
				return false;
			}			
			
			query = "insert into khoasothang(thangks, nam, ngaytao, nguoitao, npp_fk) " +
					"values('" + this.thangks + "', '" + this.namks + "', '" + this.getDateTime() + "', '" + this.userId + "', '" + nppids + "')";
			System.out.println("Query khoa so thang la: " + query);
			if(!dbks.update(query))
			{
				dbks.getConnection().rollback();
				this.msg = " Không thể thiết lập khóa sổ tháng " + query;
				return false;
			}	
			
			query=
		"	select d.MA as nppMA,d.TEN as nppTEN,c.MA as spMa,c.TEN as spTEN,a.SoLuong as Tong,b.SoLuong as CT  "+
		"	from  "+
		"	(  "+
		"		select a.NPP_FK,a.SANPHAM_FK,SUM(SOLUONG) as SoLuong,KHO_FK  "+
		"		from TONKHOTHANG a " +
		"   where a.npp_fk='"+nppids+"' and thang='"+this.thangks+"' and nam='"+this.namks+"'        "+
		"		group by NPP_FK,SANPHAM_FK,KHO_FK "+
		"	)as a full outer join  "+ 
		"	(  "+
		"		select a.NPP_FK,a.SANPHAM_FK,SUM(SOLUONG) as SoLuong,KHO_FK  "+
		"		from TONKHOTHANG_CHITIET a   "+
		"   where a.npp_fk='"+nppids+"' and thang='"+this.thangks+"' and nam='"+this.namks+"'        "+
		"		group by NPP_FK,SANPHAM_FK,KHO_FK   "+
		"	)as b on a.NPP_FK=b.NPP_FK and a.SANPHAM_FK=b.SANPHAM_FK  and a.KHO_FK=b.KHO_FK  "+
		"	left join SANPHAM c on c.PK_SEQ=ISNULL(a.SANPHAM_FK,b.SANPHAM_FK)  "+ 
		"	LEFT join NHAPHANPHOI d on d.PK_SEQ=ISNULL(a.NPP_FK,b.NPP_FK)  "+
		" where cast(ISNULL(a.SoLuong,0) as numeric (18,0)) <> cast (isnull(b.SoLuong,0) as numeric (18,0)) ";
			
			System.out.println("___Query__"+query);
			
			 rs =dbks.get(query);
			while(rs.next())
			{
				msg+="-"+rs.getString("spMa") +" - "+rs.getString("spTen")+" \n";
			}
			rs.close();
			if(msg.length()>0)
			{
				dbks.getConnection().rollback();
				msg +="Lỗi phát sinh do lệch số lượng của sản phẩm theo XNT TỔNG và XNT CT"+msg ;
				return false;
			}
			dbks.getConnection().commit();
			dbks.getConnection().setAutoCommit(true);
			return true;
		} 
		catch(Exception e) 
		{
			this.msg = nppids+"Lỗi khi khóa sổ tháng: " + e.getMessage();
			dbks.update("rollback");
			e.printStackTrace();
			return false;
		}
		finally
		{
			if(dbks!=null)dbks.shutDown();
		}
	}
	
	private String createDhccList(String nppids) 
	{
		//String query = "select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.tonggiatri,b.smartid, b.pk_seq as khId, b.ten as khTen from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq where a.ngaynhap = '" + this.ngaykhoaso + "' and a.npp_fk = '" + this.nppId + "' and a.trangthai = '0' ";
		String kq="";
		String query = "select count(*) as sl " +
					  "from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq " +
					  "where year(a.ngaynhap) = '" + this.namks + "' and month(a.ngaynhap) = '" + this.thangks + "' and a.npp_fk = '" + nppids + "' and a.trangthai = '0' ";
		
		System.out.println("::::::::::::_----"+query);
		ResultSet rs=db.get(query);
	
		try {
			if(rs.next())
			{
				if(rs.getInt("sl")>0)
					return "Có đơn hàng chưa chốt vui lòng chốt trước khi khóa số tháng";
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//check phieuxuatkho, phieuthuhoi
		/*query = "select count(*) as sodong from phieuxuatkho " +
				"where npp_fk = '" + this.nppId + "' and ngaylapphieu = '" + this.ngaykhoaso + "' and trangthai = '0'";*/
		
		query = "select count(*) as sodong from phieuxuatkho " +
				"where npp_fk = '" + this.nppId + "' and year(ngaylapphieu) = '" + this.namks + "' and month( ngaylapphieu ) = '" + this.thangks + "' and trangthai = '0'";
		System.out.println("Query pxk: " + query + "\n");
		 rs = db.get(query);
		try 
		{
			if(rs.next())
			{
				if(rs.getInt("sodong") > 0)
					return "Có phiếu xuất kho chưa chốt vui lòng chốt trước khi khóa số tháng";
				rs.close();
			}
			
			//dms KO CO PHIEU THU HOI
			/*query = "select count(pk_seq) as sodong from phieuthuhoi where npp_fk = '" + this.nppId + "' and ngaytao = '" + this.ngaykhoaso + "' and trangthai = '0'";
			System.out.println("Query pth: " + query + "\n");
			ResultSet rsTh = db.get(query);
			if(rsTh.next())
			{
				if(rs.getInt("sodong") > 0)
					this.isPthChuaChot = true;
				rsTh.close();
			}*/
		} 
		catch(Exception e) {}
		return kq;
	}
	private void init_THANG_NAM_KHOASO() 
	{
		String[] arr = this.getDateTime().split("-");
		
		int namHT = Integer.parseInt(arr[0]);
		int thangHT = Integer.parseInt(arr[1]);
		
		if(thangHT == 1)
		{
			this.thangks = "12";
			this.namks = Integer.toString(namHT - 1);
		}
		else
		{
			this.thangks = Integer.toString(thangHT - 1);
			this.namks = Integer.toString(namHT);
		}
		
	}
	public static void main(String[] arg)
	{
		//Create a hashtable
		Hashtable<String, Integer> myhash = new Hashtable<String, Integer>();

		//Put things in Hashtable
		myhash.put("A", 5);
		myhash.put("C", 4);
		myhash.put("B", 1);
		
		ArrayList al = new ArrayList(myhash.keySet()); 
		Collections.sort(al); 
		
		/*Enumeration<String> keys = myhash.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			
			System.out.println("Key: " + key + " -- Value: " + myhash.get(key));
		}*/
		
		for (Iterator i = al.iterator(); i.hasNext();) { 

			Object obj = myhash.get(i.next());
			System.out.println("Value: " + obj.toString());

			} 
		
		
		
	}
		
	 private String LastDayOfMonth(int month, int year) 
	   {
	       String ngay = "";
	       switch (month)
	       {
	           case 1:
	           case 3:
	           case 5:
	           case 7:
	           case 8:
	           case 10:
	           case 12:
	               {
	                   ngay = "31";
	                   break;
	               }
	           case 4:
	           case 6:
	           case 9:
	           case 11:
	               {
	                   ngay = "30";
	                   break;
	               }
	           case 2:
	               {
	                   if (year % 4 == 0)
	                       ngay = "29";
	                   else
	                       ngay = "28";
	                   break;
	               }
	       }

	       return ngay;
	   }
}
