package geso.dms.center.beans.duyettrakhuyenmai.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import com.aspose.cells.Cell;

import geso.dms.center.beans.duyettrakhuyenmai.IDuyettrakhuyenmai;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class Duyettrakhuyenmai implements IDuyettrakhuyenmai
{

	ResultSet dangkyTichluyRs ;

	List<String> dangkyIds = new ArrayList<String>() ;

	String userId;
	String id;

	ResultSet ctkmRs;
	String ctkmId;
	String DOANHSOTHEOLUONG = "0";
	String[] tinhthanhTen;
	String[] ddkdTen;

	String ngayduyet = "";
	String tungay_ds = "";
	String denngay_ds = "";
	String diengiai;

	ResultSet khachhangRs;
	String khIds;

	//String[] nppId;
	String[] nppTen;
	String[] khId;
	String[] khMa;
	String[] khTen;
	String[] doanhso;
	String[] dat;
	String[] soxuat;
	String[] tongtien;
	String[] sanpham;

	String thang, nam;
	String msg;

	dbutils db;
	private String[] thuongthem;
	private String[] Mucthuong;
	private String[] mucdk;

	public Duyettrakhuyenmai()
	{
		this.id = "";
		this.ngayduyet = "";
		this.diengiai = "";
		this.msg = "";
		this.ctkmId = "";
		this.khIds = "";
		this.thang = "";
		this.nam = "";
		this.db = new dbutils();
	}

	public Duyettrakhuyenmai(String id)
	{
		this.id = id;
		this.ngayduyet = "";
		this.diengiai = "";
		this.msg = "";
		this.ctkmId = "";
		this.khIds = "";

		this.thang = "";
		this.nam = "";
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

	public void setKhachhangRs(ResultSet khRs) {

		this.khachhangRs = khRs;
	}


	public ResultSet getKhachhangRs() {

		return this.khachhangRs;
	}

	ResultSet khDuyetRs ;
	Hashtable<String,List<String[]>> spDuyetTra = new Hashtable<String, List<String[]>>();

	public ResultSet getKhDuyetRs() {
		return khDuyetRs;
	}


	public void createRs() 
	{	
		Object[] data; 
		String query = "";
		getNgayduyet();
		getTungay_ds();

		if(this.ngayduyet.trim().length() > 0)
		{
			query = "\n select PK_SEQ as ctkmId, SCHEME + ', ' + DIENGIAI as SCHEME  " +
			"\n from TIEUCHITHUONGTL  where trangthai = '1'  and isnull(NgayKetThucDuyetTra,'2050-12-31') >= '"+ this.ngayduyet +"'  ";
			
			this.ctkmRs = db.get(query);
		}

		if(ctkmId!= null && ctkmId.trim().length() >0 && this.tungay_ds.trim().length() > 0)
		{
			query = "\n select pk_seq,DienGiai,NgayDangKy, case when NgayDangKy <= '"+this.tungay_ds+"' then 1 else 0 end flagg " +
			"\n from dangkykm_tichluy where thuongtl_fk =  "+this.ctkmId+" and trangthai = '1' " ;
			
			this.dangkyTichluyRs = db.get(query);
		}

		if(this.id.trim().length() > 0)
		{
			try 
			{
				query = "select PK_SEQ as ctkmId, SCHEME + ', ' + DIENGIAI as SCHEME from TIEUCHITHUONGTL  where pk_seq = '" + this.ctkmId + "' ";
				this.ctkmRs = db.get(query);

				String npp_Ten = "";
				String kh_Ten = "";
				String kh_Id = "";
				String kh_Ma = "";
				String doanh_so = "";
				String mucdk = "";
				String mucdat = "";
				String mucduyet = "";
				String tong_tien = "";
				String sanpham = "";
				String ddkdten = "";
				String tinhthanh = "";

				String subq = "\n 	'['+ STUFF((" + 
				"\n SELECT ',{\"spMa\":' + '''' + CAST(t2.ma AS NVARCHAR(MAX)) + '''' " + 
				"\n + ',\"spTen\":'''+CAST(t2.ten AS NVARCHAR(MAX)) " + 
				"\n +''',\"SoLuong\":\"'+CAST(t1.soluong AS NVARCHAR(MAX)) + '\"}'" + 
				"\n FROM DUYETTRAKHUYENMAI_KHACHHANG_sanpham t1 inner join sanpham t2 on t1.sanpham_fk = t2.pk_seq " + 
				"\n where t1.duyetkm_fk  = km.duyetkm_fk and t1.khId= km.khId FOR XML PATH(''), TYPE " + 
				"\n ).value('.', 'nvarchar(max)'),1,1,''" + 
				"\n ) + ']' ";

				query = "select p.TEN as NPP, kh.PK_SEQ as KHACHHANG_FK, kh.SMARTID AS MAFAST, kh.TEN as KHTEN, km.DOANHSO, km.sodiem,km.SoLuongMua, isnull(km.MUCDANGKY_fk,-1) + 1 as MUCDANGKY, isnull(km.MUCDAT,-1) + 1 as MUCDAT, km.thuong as TONGTIEN, km.sanpham, isnull(km.MUCDUYET,-1)+1 AS MUCDUYET, " +
				"isnull(tt.ten, '') as tinhthanh, isnull(dbo.[ListOfTDV](kh.pk_seq),'') as daidienkinhdoanh, "+subq+" as jsonSP " +
				"\n from DUYETTRAKHUYENMAI_KHACHHANG km " +
				"\n inner join KHACHHANG kh on km.khId = kh.PK_SEQ " +
				"\n inner join NHAPHANPHOI p on p.PK_SEQ = kh.NPP_FK " +
				"\n left join TINHTHANH tt on tt.PK_SEQ = kh.TINHTHANH_FK " +
				"\n where km.duyetkm_fk = '"+this.id+"'";
				System.out.println("1.Chi tieu tich luy tap trung KH: " + query);
				this.khDuyetRs = db.get(query);
				ResultSet rs = db.get(query);

				while(rs.next())
				{
					ddkdten += rs.getString("daidienkinhdoanh") + "__";
					tinhthanh += rs.getString("tinhthanh") + "__";
					npp_Ten += rs.getString("NPP") + "__";
					kh_Id += rs.getString("KHACHHANG_FK") + "__";	
					kh_Ma += rs.getString("MAFAST") + "__";
					kh_Ten += rs.getString("KHTEN") + "__";
					doanh_so += rs.getFloat("DOANHSO") + "__";
					mucdk += rs.getString("MUCDANGKY") + "__";
					mucdat += rs.getString("MUCDAT") + "__";
					mucduyet += rs.getString("MUCDUYET") + "__";
					tong_tien += rs.getString("TONGTIEN") + "__";
					sanpham += rs.getString("SANPHAM") + "__";				
				}
				rs.close();
				
				if (kh_Id.trim().length() > 0)
				{
					npp_Ten = npp_Ten.substring(0, npp_Ten.length() - 2);
					this.nppTen = npp_Ten.split("__");

					kh_Id = kh_Id.substring(0, kh_Id.length() - 2);
					this.khId = kh_Id.split("__");

					kh_Ma = kh_Ma.substring(0, kh_Ma.length() - 2);
					this.khMa = kh_Ma.split("__");

					kh_Ten = kh_Ten.substring(0, kh_Ten.length() - 2);
					this.khTen = kh_Ten.split("__");

					mucdk = mucdk.substring(0, mucdk.length() - 2);
					this.mucdk = mucdk.split("__");

					mucdat = mucdat.substring(0, mucdat.length() - 2);
					this.Mucthuong = mucdat.split("__");

					mucduyet = mucduyet.substring(0, mucduyet.length() - 2);
					this.soxuat = mucduyet.split("__");

					doanh_so = doanh_so.substring(0, doanh_so.length() - 2);
					this.doanhso = doanh_so.split("__");

					tong_tien = tong_tien.substring(0, tong_tien.length() - 2);
					this.tongtien = tong_tien.split("__");

					sanpham = sanpham.substring(0, sanpham.length() - 2);
					this.sanpham = sanpham.split("__");

					ddkdten = ddkdten.substring(0, ddkdten.length() - 2);
					this.ddkdTen = ddkdten.split("__");

					tinhthanh = tinhthanh.substring(0, tinhthanh.length() - 2);
					this.tinhthanhTen = tinhthanh.split("__");
				}
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("__Loi khi DUyet Exception: " + e.getMessage());
			}	
		}
	}


	public boolean createTctSKU( ) 
	{

		if(this.ctkmId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn chương trình khuyến mãi";
			return false;
		}
		
		
		
		if(this.id.trim().length() <= 0)
		{
			//this.msg = "Vui lòng chọn chương trình khuyến mãi 2.";
			//return false;
			boolean isCreated = createDuyet();
			if (!isCreated) {
				this.msg = "Tạo mới Duyệt trả khuyến mãi thất bại";
				return false;
			}
		}

		try 
		{
			db.getConnection().setAutoCommit(false);
			String query = "delete from DUYETTRAKHUYENMAI_KHACHHANG where duyetkm_fk = " + this.id;
			if(!db.update(query))
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}

			query = "delete from DUYETTRAKHUYENMAI_KHACHHANG_SanPham where duyetkm_fk = " + this.id;
			if(!db.update(query))
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}

			query =   "\n with khDs as (	" + 
			"\n 	select "+this.ctkmId+" thuongtl_fk,a.duyetkm_fk,duyet.tungay_ds, duyet.denngay_ds ,dk.khachhang_fk, max(mucdangky_fk)muc " + 
			"\n 	from DUYETTRAKHUYENMAI_Dangky a" + 
			"\n 	inner join DUYETTRAKHUYENMAI duyet on duyet.pk_seq =  a.duyetkm_fk" + 
			"\n 	inner join DANGKYKM_TICHLUY_KHACHHANG dk on a.dangky_fk = dk.DKKMTICHLUY_FK" + 
			"\n 	 where a.duyetkm_fk = " + this.id + 
			"\n 	 group by duyet.tungay_ds, duyet.denngay_ds ,dk.khachhang_fk,a.duyetkm_fk " + 
			"\n ) " + 
			"\n , ds as  " + 
			"\n (  " + 
			"\n  	select dh.KHACHHANG_FK, SUM(sp.SOLUONG * sp.dongia) as ds, SUM(sp.SOLUONG) as SOLUONG " +
			"\n				,  SUM(sp.SOLUONG * isnull(x.quydoi,0) ) sodiem	 " + 
			"\n   	from hoadon dh inner join hoadon_sp sp on sp.hoadon_fk = dh.PK_SEQ and isnull(sp.khong_tich_luy,0) = 0           " + 
			"\n   	inner join khDs on khDs.khachhang_fk = dh.khachhang_fk  and  khDs.tungay_ds <= dh.ngayxuathd  and  khDs.denngay_ds >= dh.ngayxuathd " +
			"\n		inner join tieuchithuongtl_sanpham x on x.thuongtl_fk = khDs.thuongtl_fk and x.sanpham_fk = sp.SANPHAM_FK " + 
			"\n   	where dh.TRANGTHAI in (2,4) and dh.loaihoadon = 0      " + 
			"\n   	group by dh.KHACHHANG_FK   " + 
			"\n )" + 
			"\n  " + 
			"\n  insert DUYETTRAKHUYENMAI_KHACHHANG(duyetkm_fk, khId, doanhso,SoluongMua,sodiem, MUCDANGKY_FK, MUCDAT, thuong, TRANGTHAI, donvi,hinhthuc)  " + 
			"\n  select khDs.duyetkm_fk, khDs.KHACHHANG_FK, ISNULL(ds.DS,0) as DOANHSO, ISNULL(ds.SOLUONG,0) as SLMua, ISNULL(ds.sodiem,0) as sodiem,  khDs.muc as MUCDANGKY, isnull(muc.muc,-1)   " + 
			"\n ,CASE WHEN muc.donvi = 0 then muc.chietkhau * ISNULL(ds.DS,0)/ 100 " + 
			"\n 	when muc.donvi = 1 then muc.chietkhau " + 
			"\n 	when muc.donvi = 2 and muc.hinhthuc = 0 then muc.chietkhau" + 
			"\n 	else 0 end as TONGTIEN" + 
			"\n   " + 
			"\n , 0, muc.donvi   ,muc.hinhthuc" + 
			"\n from   khDs " + 
			"\n left join ds on ds.khachhang_fk = khDs.khachhang_fk" + 
			"\n inner join tieuchithuongtl tc on tc.pk_seq = khDs.thuongtl_fk" + 
			"\n inner join KHACHHANG kh on kh.PK_SEQ = khDs.KHACHHANG_FK   " + 
			"\n inner join NHAPHANPHOI p on p.PK_SEQ = kh.NPP_FK   " + 
			"\n outer apply " + 
			"\n (   " + 
			"\n   	select thuongtl_fk, tumuc, denmuc, chietkhau, donvi, muc ,hinhthuc " + 
			"\n 	from TIEUCHITHUONGTL_TIEUCHI " + 
			"\n 	where thuongtl_fk = tc.pk_seq  " + 
			"\n 		and case when ISNULL(tc.DOANHSOTHEOLUONG,0) = 0 then isnull(ds.DS,0) when ISNULL(tc.DOANHSOTHEOLUONG,0) = 1 then isnull(ds.SOLUONG,0) else isnull(ds.sodiem,0)  end between tumuc and denmuc" + 
			"\n ) muc     " + 
			"\n where 1=1" ;

			System.out.println("tich luy query = " + query);
			if(!db.update(query))
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			query = "\n  insert DUYETTRAKHUYENMAI_KHACHHANG_SanPham(duyetkm_fk,khId,sanpham_fk,soluong) " + 
			"\n  select duyetkm_fk, khId, tra.sanpham_fk , case when tra.hinhthuctra= 1 then tra.soluong else 0 end " + 
			"\n  from  DUYETTRAKHUYENMAI_KHACHHANG duyet " + 
			"\n  inner join TIEUCHITHUONGTL_SPTRA tra on tra.thuongtl_fk = "+this.ctkmId+" and tra.muctra= duyet.mucdat " + 
			"\n  where duyet.duyetkm_fk ="+this.id+" and duyet.donvi = 2 " ;
			if(!db.update(query))
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return true;
		}
		catch (Exception e) 
		{
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.msg = "Không thể duyệt khuyến mại tích lũy";
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateTctSKU()
	{
		if (khId == null)
		{
			this.msg = "Không có khách hàng nào được chọn";
			return false;
		}

		if(this.ngayduyet.trim().length() <= 0)
		{
			this.msg = "Bạn phải chọn ngày duyệt";
			return false;
		}

		if (this.ctkmId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn chương trình khuyến mại";
			return false;
		}

		try 
		{
			db.getConnection().setAutoCommit(false);

			String query = "UPDATE DUYETTRAKHUYENMAI SET tungay_ds = '"+ this.tungay_ds +"', denngay_ds = '"+ this.denngay_ds +"', ngayduyet = '" + this.ngayduyet + "', diengiai = N'" + this.diengiai + "', nguoisua = '" + this.userId + "', ngaysua = '" + getDateTime() + "' " +
			"WHERE trangthai=0 and PK_SEQ = '" + this.id + "'";

			if(db.updateReturnInt(query) != 1)
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			String querycheck="select count(*) as sl from DUYETTRAKHUYENMAI where tungay_ds>denngay_ds and pk_seq="+ this.id+"";
			ResultSet checkRs=db.get(querycheck);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				this.msg = "Đến ngày DS  không được nhỏ hơn từ ngày DS!";
				db.getConnection().rollback();
				return false;
			}
			checkRs.close();
			/*query = "delete from DUYETTRAKHUYENMAI_KHACHHANG where duyetkm_fk = " + this.id;
			if(!db.update(query))
			{
				this.msg = "Không thể xóa DUYETTRAKHUYENMAI_KHACHHANG ";
				db.getConnection().rollback();
				return false;
			}

			if(khId != null)
			{
				String sqlInsert = "";
				for(int i = 0; i < khId.length; i++)
				{
					System.out.println(doanhso[i].trim());
					System.out.println(tongtien[i].trim());
					System.out.println(mucdk[i].trim());
					System.out.println(Mucthuong[i].trim());
					System.out.println(sanpham[i].trim());
					sqlInsert += "select '" + khId[i].trim() + "' as khId, '" + doanhso[i].trim().replaceAll(",", "") + "' as doanhso, '" + tongtien[i].trim().replaceAll(",", "") + "' as tongtien, '" + this.mucdk[i] + "' as donvi, " + this.Mucthuong[i].trim() + " as MUCDANGKY_FK, '" + this.sanpham[i] + "' as SANPHAM, 0 as TRANGTHAI ";
					sqlInsert += " union ";
				}

				if(sqlInsert.trim().length() > 0)
				{
					sqlInsert = sqlInsert.substring(0, sqlInsert.length() - 7);

					query = " insert DUYETTRAKHUYENMAI_KHACHHANG(duyetkm_fk, khId, doanhso, thuong, MUCDANGKY, MUCDAT, SANPHAM, TRANGTHAI)" +
							" select IDENT_CURRENT('DUYETTRAKHUYENMAI'), duyet.* from ( " + sqlInsert + " ) duyet ";

					System.out.println("Chen Khach Hang: " + query);
					if(!db.update(query))
					{
						this.msg = "Không thể tạo mới DUYETTRAKHUYENMAI_KHACHHANG " + query;
						db.getConnection().rollback();
						return false;
					}
				}
			}*/

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.msg = "Không thể duyệt khuyến mại tích lũy.";
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean createDuyet()
	{
		
		if(this.ngayduyet.trim().length() <= 0)
		{
			this.msg = "Bạn phải chọn ngày duyệt";
			return false;
		}
		if(this.tungay_ds.trim().length() <= 0)
		{
			this.msg = "Bạn phải chọn ngày bắt đầu tính doanh số";
			return false;
		}
		if(this.denngay_ds.trim().length() <= 0)
		{
			this.msg = "Bạn phải chọn  ngày kết thúc tính doanh số";
			return false;
		}

		if(this.ctkmId.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn chương trình khuyến mại";
			return false;
		}

		if(this.dangkyIds.size() <=0)
		{
			this.msg = "Vui lòng chọn danh sách đăng ký";
			return false;
		}

		try 
		{
			Object[] data ;

			db.getConnection().setAutoCommit(false);

			String
			query = " insert DUYETTRAKHUYENMAI(tungay_ds,denngay_ds,ngayduyet, ctkm_fk, diengiai, trangthai, ngaytao, nguoitao, ngaysua, nguoisua) " +
			" values('"+this.tungay_ds+"','"+this.denngay_ds+"','"+this.ngayduyet+"',"+this.ctkmId+",N'"+this.diengiai+"'" +
					",0,'"+this.getDateTime()+"',"+this.userId+",'"+this.getDateTime()+"',"+this.userId+")";
			
			if(db.updateReturnInt(query) !=1)
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			ResultSet rs = db.get(" select scope_identity() id ");
			rs.next();
			String idTemp = rs.getString("id");

			String dkStr = "";
			
			for(int i = 0; i < this.dangkyIds.size(); i++)
			{
			
				if(dkStr.trim().length() >0)
					dkStr +=","+this.dangkyIds.get(i);
				else
					dkStr =this.dangkyIds.get(i);
			}

			query  = " update DANGKYKM_TICHLUY set trangthai = 1 where trangthai = 1 and pk_seq in ("+dkStr+") ";
			
			if(db.updateReturnInt(query) != this.dangkyIds.size()  )
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}

			query = " insert DUYETTRAKHUYENMAI_Dangky(duyetkm_fk,dangky_fk) " +
			" select "+idTemp+",pk_seq from DANGKYKM_TICHLUY where trangthai = 1 and pk_seq in ("+dkStr+") ";

			if(db.updateReturnInt(query) != this.dangkyIds.size()  )
			{
				this.msg = "Exception";
				db.getConnection().rollback();
				return false;
			}
			String querycheck="select count(*) as sl from DUYETTRAKHUYENMAI where tungay_ds>denngay_ds and pk_seq="+idTemp+"";
			ResultSet checkRs=db.get(querycheck);
			checkRs.next();
			if(checkRs.getInt("sl")>0) {
				this.msg = "Đến ngày DS  không được nhỏ hơn từ ngày DS!";
				db.getConnection().rollback();
				return false;
			}
			checkRs.close();

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			this.id  = idTemp;
			return true;
		} 
		catch (Exception e) 
		{
			try {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			this.msg = "Không thể duyệt khuyến mại tích lũy.";
			e.printStackTrace();
			return false;
		}

	}

	public void init() 
	{
		String query = "select tc.DOANHSOTHEOLUONG,duyet.denngay_ds,duyet.tungay_ds,duyet.ngayduyet, duyet.diengiai, duyet.ctkm_fk  " +
		"from DuyetTraKhuyenMai duyet inner join tieuchithuongtl tc on tc.pk_seq = duyet.ctkm_fk where duyet.pk_seq = '" + this.id + "'";

		System.out.println("__Khoi tao tieu chi thuong: " + query);

		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.DOANHSOTHEOLUONG = rs.getString("DOANHSOTHEOLUONG");
					this.ctkmId = rs.getString("ctkm_fk");
					this.ngayduyet = rs.getString("ngayduyet");				
					this.diengiai= rs.getString("diengiai");
					
					this.tungay_ds = rs.getString("tungay_ds");
					this.denngay_ds = rs.getString("denngay_ds");
				}
				rs.close();
				this.dangkyIds.clear();
				query= " select dangky_fk from DUYETTRAKHUYENMAI_Dangky where duyetkm_fk = "+ this.id;
				rs = db.get(query);
				while(rs.next())
				{
					this.dangkyIds.add(rs.getString("dangky_fk"));
				}

			} 
			catch (Exception e)
			{
				System.out.println("115.Error Meg: " + e.getMessage());
			}
		}

		this.createRs();
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}


	public void setCtkmRs(ResultSet ctkmRs) {

		this.ctkmRs = ctkmRs;
	}


	public ResultSet getCtkmRs() {

		return this.ctkmRs;
	}


	public String getCtkmId() {

		return this.ctkmId;
	}


	public void setCtkmId(String ctkmId) {

		this.ctkmId = ctkmId;
	}


	public String getNgayduyet() {

		if(this.ngayduyet.trim().length() <=0 )
			this.ngayduyet = Utility.getNgayHienTai();

		return this.ngayduyet;
	}


	public void setNgayduyet(String ngayduyet) {
		this.ngayduyet = ngayduyet;
	}


	/*public String[] getNppId() {

		return this.nppId;
	}


	public void setNppId(String[] nppId) {

		this.nppId = nppId;
	}*/


	public String[] getNppTen() {

		return this.nppTen;
	}


	public void setNppTen(String[] nppTen) {

		this.nppTen = nppTen;
	}


	public void setKhId(String[] khId) {

		this.khId = khId;
	}


	public String[] getKhTen() {

		return this.khTen;
	}


	public void setKhTen(String[] khTen) {

		this.khTen = khTen;
	}


	public String[] getDoanhso() {

		return this.doanhso;
	}


	public void setDoanhso(String[] doanhso) {

		this.doanhso = doanhso;
	}


	public String[] getDat() {

		return this.dat;
	}


	public void setDat(String[] dat) {

		this.dat = dat;
	}


	public String[] getSoxuat() {

		return this.soxuat;
	}


	public void setSoxuat(String[] soxuat) {

		this.soxuat = soxuat;
	}


	public String[] getTongtien() {

		return this.tongtien;
	}


	public void setTongtien(String[] tongtien) {

		this.tongtien = tongtien;
	}

	public String[] getKhId() {

		return this.khId;
	}


	public String[] getSanpham() {

		return this.sanpham;
	}


	public void setSanpham(String[] sanpham) {

		this.sanpham = sanpham;
	}

	@Override
	public String getThang() {
		// TODO Auto-generated method stub
		return this.thang;
	}

	@Override
	public void setThang(String value) {
		this.thang = value;
	}

	@Override
	public String getNam() {
		// TODO Auto-generated method stub
		return this.nam;
	}

	@Override
	public void setNam(String value) {
		this.nam = value;
	}

	@Override
	public String[] getThuongthem() {
		return this.thuongthem;
	}

	@Override
	public void setThuongthem(String[] value) {
		this.thuongthem = value;
	}

	@Override
	public String[] getMucthuong() {
		return this.Mucthuong;
	}

	@Override
	public void setMucthuong(String[] value) {
		this.Mucthuong = value;
	}

	@Override
	public String[] getMucDk() {
		return this.mucdk;
	}

	@Override
	public void setMucDk(String[] value) {
		this.mucdk= value;
	}

	@Override
	public String[] getKhMa() {
		// TODO Auto-generated method stub
		return this.khMa;
	}

	@Override
	public void setKhMa(String[] khMa) {
		this.khMa = khMa;
	}

	@Override
	public boolean setDuyetMucThuong(String khachhang_fks,Hashtable<String, Integer> kh_muc) {
		int sokh = 0;
		String query  = "";
		String dataUpload = "";
		String[] khIds = khachhang_fks.split(",");
		for(int i=0; i< khIds.length; i++){
			dataUpload += "select "+this.id+" as duyetkm_fk, "+khIds[i]+" as khid, "+kh_muc.get(khIds[i])+" - 1 as mucduyet union \n";
		}
		if(dataUpload.length() > 0)
			dataUpload = dataUpload.substring(0, dataUpload.length()-7);
		try 
		{
			if(dataUpload.length() > 0)
			{
				db.getConnection().setAutoCommit(false);
				
				query = "\n select isnull(max(isnull(muc,0)) + 1,-1) muc " +
				"\n from tieuchithuongtl_tieuchi where thuongtl_fk =" + this.ctkmId;
				ResultSet rs=  db.get(query);
				rs.next();
				int muc = rs.getInt("muc");
				String error = "";
				String sql_check  = " select khid, mucduyet from ("+dataUpload+") data where data.mucduyet >" +muc;
				rs = db.get(sql_check);
				while(rs.next())
				{
					if(error.trim().length() > 0)
						error += ", "  + rs.getString("khId") + "  , mức "+( rs.getInt("muc") + 1  )+"  ";
					else
						error += rs.getString("khId") + "  , mức "+( rs.getInt("muc") + 1  )+"  ";
				}
				
				if(error.trim().length() > 0)
				{
					this.msg = "Các id KH duyệt mức không hợp lệ: "+ error;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}				
				
				query = "UPDATE DUYETTRAKHUYENMAI SET trangthai = 0 WHERE trangthai = 0 and PK_SEQ = '" + this.id + "'";
				if(db.updateReturnInt(query) != 1)
				{
					this.msg = "Exception";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				query = "update a set a.MUCDUYET = d.mucduyet, uploading = 1 from DUYETTRAKHUYENMAI_KHACHHANG a inner join (" + dataUpload +") d on a.duyetkm_fk = d.duyetkm_fk and a.khid = d.khid";
				sokh = db.updateReturnInt(query);
				if(sokh <=0 ){
					this.msg = "Không có khách hàng nào được upload";
					
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				System.out.println(" sokh upload = "+ sokh);

				query = "\n update ds set thuong =  " +      						
						"\n 	CASE WHEN muc.donvi = 0 then muc.chietkhau * ds.doanhso  / 100 " + 
						"\n 	when muc.donvi = 1 then muc.chietkhau " + 
						"\n 	when muc.donvi = 2 and muc.hinhthuc = 0 then muc.chietkhau " + 
						"\n 	else 0 end, donvi = muc.donvi, hinhthuc = muc.hinhthuc " + 						
						"\n from DUYETTRAKHUYENMAI d " +      
						"\n inner join DUYETTRAKHUYENMAI_KHACHHANG ds on  ds.uploading = 1 and ds.duyetkm_fk = d.PK_SEQ " +    
						"\n outer apply " + 
						"\n (   " + 
						"\n   	select thuongtl_fk, tumuc, denmuc, chietkhau, donvi, muc ,hinhthuc " + 
						"\n 	from TIEUCHITHUONGTL_TIEUCHI " + 
						"\n 	where thuongtl_fk = d.ctkm_fk  " + 
						"\n 			and muc = ds.MUCDUYET " + 
						"\n )muc     " + 
						"\n where d.PK_SEQ = '"+this.id+"'";	
				System.out.println("query ="+ query);
				if(!db.update(query)){
					this.msg = "Exception";
					
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				query = "delete from DUYETTRAKHUYENMAI_KHACHHANG_SanPham where duyetkm_fk = " + this.id + " and  khId in ( select khId from DUYETTRAKHUYENMAI_KHACHHANG where uploading = 1 and duyetkm_fk =" + this.id + " ) ";
				if(!db.update(query))
				{
					this.msg = "Exception";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				
				
				query = "\n  insert DUYETTRAKHUYENMAI_KHACHHANG_SanPham(duyetkm_fk,khId,sanpham_fk,soluong) " + 
				"\n  select duyetkm_fk, khId, tra.sanpham_fk , case when tra.hinhthuctra= 1 then tra.soluong else 0 end " + 
				"\n  from  DUYETTRAKHUYENMAI_KHACHHANG duyet " + 
				"\n  inner join TIEUCHITHUONGTL_SPTRA tra on tra.thuongtl_fk = "+this.ctkmId+" and tra.muctra= duyet.MUCDUYET " +				 
				"\n  where duyet.uploading = 1 and duyet.duyetkm_fk ="+this.id+" and duyet.donvi = 2 " ;
				if(!db.update(query))
				{
					this.msg = "Exception";
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				query = "update a set uploading = 0 from DUYETTRAKHUYENMAI_KHACHHANG a where a.uploading = 1 and a.duyetkm_fk ="+this.id+" ";		
				if(db.updateReturnInt(query) <=0 ){
					this.msg = "Exception";
					
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				this.msg = "Có " + sokh + " khách hàng được upload";
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
			
			return true;
		}
		catch(Exception ex)
		{
			Utility.rollback_and_shutdown(db);
			this.msg = "Exception";
			ex.printStackTrace();
			return false;
		}
		
	}

	@Override
	public void DbClose() {
		this.db.shutDown();
	}

	@Override
	public String[] gettinhthanhTen() {
		// TODO Auto-generated method stub
		return this.tinhthanhTen;
	}

	@Override
	public void settinhthanhTen(String[] ttTen) {
		// TODO Auto-generated method stub
		this.tinhthanhTen  = ttTen;
	}

	@Override
	public String[] getddkdTen() {
		// TODO Auto-generated method stub
		return this.ddkdTen;
	}

	@Override
	public void setddkdTen(String[] ddkdTen) {
		// TODO Auto-generated method stub
		this.ddkdTen = ddkdTen;
	}

	@Override
	public String[] getTinhthanhid() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTinhthanhid(String[] tinhthanhid) {
		// TODO Auto-generated method stub

	}	

	public String getTungay_ds() {
		System.out.println("this.tungay_ds : "+this.tungay_ds);
		if(this.tungay_ds.trim().length() <=0 )
			this.tungay_ds = Utility.getNgayHienTai();

		return tungay_ds;
	}
	public void setTungay_ds(String tungay_ds) {
		this.tungay_ds = tungay_ds;
	}
	public String getDenngay_ds() {
		if(this.denngay_ds.trim().length() <=0 )
			this.denngay_ds = Utility.getNgayHienTai();

		return denngay_ds;
	}
	public void setDenngay_ds(String denngay_ds) {
		this.denngay_ds = denngay_ds;
	}

	public ResultSet getDangkyTichluyRs() {
		return dangkyTichluyRs;
	}

	public List<String> getDangkyIds() {
		return dangkyIds;
	}
	public void setDangkyIds(String[]dangkyIds) {
		this.dangkyIds.clear();
		if(dangkyIds != null)
		{
			for(int i = 0; i < dangkyIds.length ; i++ )
			{
				this.dangkyIds.add(dangkyIds[i]==null?"":dangkyIds[i] );
			}
		}

	}
	public String getDOANHSOTHEOLUONG() {
		return DOANHSOTHEOLUONG;
	}
	public void setDOANHSOTHEOLUONG(String dOANHSOTHEOLUONG) {
		DOANHSOTHEOLUONG = dOANHSOTHEOLUONG;
	}
}
