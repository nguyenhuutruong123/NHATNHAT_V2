package geso.dms.distributor.beans.khoasongay.imp;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.khoasongay.IKhoasongay;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.FixData;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Khoasongay implements IKhoasongay , Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;
	String userId;	
	String ngaykhoaso;
	String msg;

	String nppId;
	String nppTen;
	String sitecode;

	String thanhcong;

	//don hang chua chot
	ResultSet dhcclist;

	//don hang da xuat kho
	ResultSet dhdxklist;

	//don hang da chot
	ResultSet dhdclist;

	String ngayksgn;
	String thangks;
	String namks;

	boolean isPxkChuaChot;
	boolean isPthChuaChot;
	dbutils db;

	public Khoasongay()
	{
		this.ngaykhoaso = this.getDateTime();
		this.ngayksgn = "";
		this.msg = "";
		this.isPthChuaChot = false;
		this.isPxkChuaChot = false;

		this.thangks = "";
		this.namks = "";
		this.init_THANG_NAM_KHOASO();

		this.thanhcong = "0";
		db = new dbutils();
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

	public String getUserId() 
	{		
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;		
	}

	public String getNgaygiao() 
	{		
		return this.ngaykhoaso;
	}

	public void setNgaygiao(String ngaykhoaso) 
	{
		this.ngaykhoaso = ngaykhoaso;
	}

	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}

	public String getNppTen() 
	{
		return this.nppTen;
	}

	public void setNppTen(String nppTen) 
	{
		this.nppTen = nppTen;
	}

	public String getSitecode() 
	{
		return this.sitecode;
	}

	public void setSitecode(String sitecode) 
	{
		this.sitecode = sitecode;
	}

	private void getNppInfo()
	{
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();
	}

	public String getNgaykhoaso()
	{	
		return this.ngaykhoaso;
	}

	public void setNgaykhoaso(String ngaykhoaso)
	{
		this.ngaykhoaso = ngaykhoaso;	
	}

	public String getMessege() 
	{
		return this.msg;
	}

	public void setMessege(String msg)
	{		
		this.msg = msg;
	}

	public ResultSet getDhChuaChotList() 
	{	
		return this.dhcclist;
	}

	public void setDhChuaChotList(ResultSet dhcclist) 
	{
		this.dhcclist = dhcclist;		
	}

	public ResultSet getDhDaXuatKhoList() 
	{	
		return this.dhdxklist;
	}

	public void setDhDaXuatKhoList(ResultSet dhdxklist) 
	{
		this.dhdxklist = dhdxklist;	
	}

	public ResultSet getDhDaChotList() 
	{	
		return this.dhdclist;
	}

	public void setDhDaChotList(ResultSet dhdclist) 
	{
		this.dhdclist = dhdclist;	
	}

	public boolean KhoaSoNgay(String ngayks) 
	{	

		String query="";
		query = "select top(1) NAM as namMax, THANGKS as thangMax " +
		"from KHOASOTHANG where NPP_FK = '" + this.nppId + "' order by NAM desc, THANGKS desc ";
		System.out.println("1.Khoi tao thang: " + query);
		ResultSet rs = db.get(query);

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

		try 
		{



			if(this.thangks.trim().length() <= 0)
			{
				this.init_THANG_NAM_KHOASO();
			}





			String sql = "select thangks, nam from khoasothang where npp_fk='"+this.nppId+"' order by nam desc, thangks desc";
			rs = db.get(sql);
			int thangKSMax = 0;
			{
				if(rs.next())
				{
					thangKSMax = rs.getInt("thangks");
				}
			}

			if( (Integer.parseInt(this.thangks) - thangKSMax) > 1 )
			{
				this.msg = "Vui lòng kiểm tra lại tháng muốn khóa sổ";
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

			db.getConnection().setAutoCommit(false);
			String msg=Thuc_thi(db, this.nppId);
			if(msg.length()>0)
			{
				this.msg=msg;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			else
			{
				this.msg="Khóa sổ thành công.";
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
			return true;
		} 
		catch(Exception e) 
		{
			this.msg = "Lỗi khi khóa sổ tháng: " + e.getMessage();
			db.update("rollback");
			e.printStackTrace();
			return false;
		}

	}

	public void init() 
	{
		this.getNppInfo();
		try 
		{
			String query = "select top(1) NAM as namMax, THANGKS as thangMax " +
			"from KHOASOTHANG where NPP_FK = '" + this.nppId + "' order by NAM desc, THANGKS desc ";
			System.out.println("1.Khoi tao thang: " + query);
			ResultSet rs = db.get(query);

			String thangKsMax = "";
			String namKsMax = "";

			if(rs != null)
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
				rs.close();
			}

			this.createDhccList();
			this.createDhdxkList();

			if(this.isPxkChuaChot)
				this.msg += "\n+ Có phiếu xuất kho chưa chốt trong tháng khóa sổ.";
			/*if(this.isPthChuaChot)
				this.msg += "\n+ Co phieu thu hoi chua chot trong ngay khoa so.";*/
		} 
		catch(Exception e) { }
	}


	private void createDhdxkList()
	{
		//String query = "select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.tonggiatri,b.smartid, b.pk_seq as khId, b.ten as khTen from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq where a.ngaynhap = '" + this.ngaykhoaso + "' and a.npp_fk = '" + this.nppId + "' and a.trangthai = '3' and a.tinhtrang = '0'";
		String query = "select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.tonggiatri,b.smartid, b.pk_seq as khId, b.ten as khTen " +
		"from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq " +
		"where year(a.ngaynhap) = '" + this.namks + "' and month(a.ngaynhap) = '" + this.thangks + "' and a.npp_fk = '" + this.nppId + "' and a.trangthai = '3'";
		this.dhdxklist = db.get(query);
	}

	private void createDhccList() 
	{
		//String query = "select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.tonggiatri,b.smartid, b.pk_seq as khId, b.ten as khTen from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq where a.ngaynhap = '" + this.ngaykhoaso + "' and a.npp_fk = '" + this.nppId + "' and a.trangthai = '0' ";

		String query = "select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.tonggiatri,b.smartid, b.pk_seq as khId, b.ten as khTen " +
		"from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq " +
		"where year(a.ngaynhap) = '" + this.namks + "' and month(a.ngaynhap) = '" + this.thangks + "' and a.npp_fk = '" + this.nppId + "' and a.trangthai = '0' ";
		this.dhcclist = db.get(query);

		//check phieuxuatkho, phieuthuhoi
		/*query = "select count(*) as sodong from phieuxuatkho " +
				"where npp_fk = '" + this.nppId + "' and ngaylapphieu = '" + this.ngaykhoaso + "' and trangthai = '0'";*/

		query = "select count(*) as sodong from phieuxuatkho " +
		"where npp_fk = '" + this.nppId + "' and year(ngaylapphieu) = '" + this.namks + "' and month( ngaylapphieu ) = '" + this.thangks + "' and trangthai = '0'";
		System.out.println("Query pxk: " + query + "\n");
		ResultSet rs = db.get(query);
		try 
		{
			if(rs.next())
			{
				if(rs.getInt("sodong") > 0)
					this.isPxkChuaChot = true;
				rs.close();
			}

			//TRAPHACO KO CO PHIEU THU HOI
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
	}

	public String getNgayKhoaSoGanNhat() 
	{
		return this.ngayksgn;
	}

	public void setNgayKhoaSoGanNhat(String nksgn) 
	{
		this.ngayksgn = nksgn;
	}

	public void createRs() 
	{
		this.getNppInfo();

		this.createDhccList();
		this.createDhdxkList();

		if(this.isPxkChuaChot)
			this.msg += "\n+ Có phiếu xuất kho chưa chốt trong tháng khóa sổ.";
		/*if(this.isPthChuaChot)
			this.msg += "\n+ Co phieu thu hoi chua chot trong ngay khoa so.";*/
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}


	public void DBclose() {


		try {

			if(this.dhcclist != null)
				this.dhcclist.close();
			if(this.dhdclist != null)
				this.dhdclist.close();
			if(this.dhdxklist != null)
				this.dhdxklist.close();
			if(this.db != null)
				this.db.shutDown();


		} catch (Exception e) {
			// TODO: handle exception
		}

	}


	public boolean isPxkChuaChot() 
	{
		return this.isPxkChuaChot;
	}

	public void setIsPxkChuaChot(boolean pxkChuaChot) 
	{
		this.isPxkChuaChot = pxkChuaChot;
	}

	public boolean isPthChuaChot() 
	{
		return this.isPthChuaChot;
	}

	public void setIsPthChuaChot(boolean pthChuaChot) 
	{
		this.isPthChuaChot = pthChuaChot;
	}

	public String getDksThanhCong() 
	{
		return this.thanhcong;
	}

	public void setDksThanhCong(String tc) 
	{
		this.thanhcong = tc;
	}

	public String getThangks() {

		return this.thangks;
	}


	public void setThangks(String thangks) {

		this.thangks = thangks;
	}


	public String getNamks() {

		return this.namks;
	}


	public void setNamks(String namks) {

		this.namks = namks;
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


	private String Thuc_thi(dbutils db,String nppid)
	{


		String ngaydaydh="";

		String sql="select Max(NgayThangNam) as ngaydauthang,SUBSTRING(   convert(char(10),DATEADD(month,1,Max(NgayThangNam)),126),1,7) as thangks, "
			+" convert(char(10),DATEADD(month,1,Max(NgayThangNam)),126) as ngaydauthang1,"
			+" convert(char(10),dateadd(month,1+datediff(month,0,DATEADD(month,1,Max(NgayThangNam))),-1),126) as  ngaycuoithang,"				
			+ " convert(char(10),DATEADD(month,2,Max(NgayThangNam)),126)as ngaydaydh from KHOASOTHANG where NPP_FK="+nppid;
		System.out.println("nppid: "+nppid);

		System.out.println(sql);
		ResultSet rs1=db.get(sql);

		try
		{
			if(rs1.next())
			{

				// sai 
				ngaydaydh=rs1.getString("ngaydaydh");
				String tungay=rs1.getString("ngaydauthang1");
				String denngay=rs1.getString("ngaycuoithang");
				String thangks=rs1.getString("thangks");
				String thangks2_check=this.getthang_ks(db,nppid);
				System.out.println("tungay ---"+tungay +"--- den nagy"+ denngay);


				// kiêm tra sai book


				String query="\n select count(*) sl  " + 
				"\n from ufn_Booked_Total("+nppid+") a right join NHAPP_KHO b  " + 
				"\n on a.npp_fk=b.NPP_FK and a.sanpham_fk=b.SANPHAM_FK and a.kbh_fk=b.KBH_FK  " + 
				"\n and a.kho_fk=b.KHO_FK  " + 
				"\n where isnull(b.BOOKED,0)<>isnull(a.Total_Booked,0) and b.NPP_FK="+nppid+"   " ;
				ResultSet check=db.get(query);
				check.next();
				int sl=check.getInt("sl");
				if(sl>0)
				{
					return " sai booked kho tổng không thể khóa sổ tháng ";
				}
				check.close();

				query="\n select count(*) sl  " + 
				"\n from ufn_Booked_chitiet_nhapkho("+nppid+",null) a right join NHAPP_KHO_CHITIET b  " + 
				"\n on a.npp_fk=b.NPP_FK and a.sanpham_fk=b.SANPHAM_FK and a.kbh_fk=b.KBH_FK  " + 
				"\n and a.kho_fk=b.KHO_FK and a.solo=b.solo and a.ngayhethan=b.NGAYHETHAN and a.ngaynhapkho=b.NGAYNHAPKHO  " + 
				"\n where isnull(b.BOOKED,0)<>isnull(a.BOOKED_SQL,0) and b.NPP_FK="+nppid+"";
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return " sai booked kho chi tiết không thể khóa sổ tháng ";
				}
				check.close();


				query="\n select count(*)sl from [dbo].[ufn_Booked_Total_KhoaSoThang]("+nppid+",'"+denngay+"') where isnull(Total_Booked,0) > 0 " ;
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return " Còn nghiệp vụ booked kho tổng không thể khóa sổ tháng trước ngày "+denngay+" ";
				}
				check.close();


				query="\n select count(*)sl from [dbo].[ufn_Booked_chitiet_nhapkho_KhoaSoThang]("+nppid+",null,'"+denngay+"') where isnull(BOOKED_SQL,0) > 0 ";
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return " Còn nghiệp vụ booked kho chi tiết không thể khóa sổ tháng trước ngày "+denngay+" ";
				}
				check.close();





				query=	 "\n select count(*) sl   " + 
				"\n from ufn_XNT_Total("+nppid+") a right join NHAPP_KHO b  " + 
				"\n on a.npp_fk=b.NPP_FK and a.sanpham_fk=b.SANPHAM_FK and a.kbh_fk=b.KBH_FK  " + 
				"\n and a.kho_fk=b.KHO_FK  " + 
				"\n where isnull(b.SOLUONG,0)<>isnull(a.XNT,0) and b.NPP_FK="+nppid+" ";
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return "Lỗi lệch NXT so với tồn hiện tại (tổng)! ";
				}
				check.close();




				query= 	 "\n select count(*) sl   " + 
				"\n from ufn_XNT_ChiTiet_NGAYNHAPKHO("+nppid+") a   " + 
				"\n right join NHAPP_KHO_CHITIET b  " + 
				"\n 	on a.npp_fk=b.NPP_FK and a.sanpham_fk=b.SANPHAM_FK and a.kbh_fk=b.KBH_FK  " + 
				"\n 	and a.kho_fk=b.KHO_FK and a.solo=b.solo and a.ngayhethan=b.NGAYHETHAN and a.ngaynhapkho=b.NGAYNHAPKHO  " + 
				"\n where isnull(b.SOLUONG,0)<>isnull(a.XNT,0) and b.NPP_FK="+nppid+"   " ;
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return "Lỗi lệch NXT so với tồn hiện tại (chi tiết)!  ";
				}
				check.close();

				
				query= "\n select count(*) sl from dieuchinhtonkho where trangthai = 0 and ngaydc <='"+denngay+"' and NPP_FK="+nppid+"   " ;
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return "Còn nghiệp vụ kiểm kho chưa chốt trong tháng vui lòng kiểm tra !";
				}
				check.close();
				
				query= "\n select count(*) sl from nhaphang where trangthai = 0 and ngaynhan <= '"+denngay+"' and npp_fk = "+nppid+"    " ;
				check=db.get(query);
				check.next();
				sl=check.getInt("sl");
				if(sl>0)
				{
					return "Còn nghiệp vụ nhập hàng chưa chốt trong tháng vui lòng kiểm tra !";
				}
				check.close();
				

				if(!thangks.equals(thangks2_check)){
					System.out.println("nppid: "+nppid);

					System.out.println(sql);
					return "Loi:Không thể xác định được ngày khóa sổ  "+nppid;
				}

				query=	 "\n INSERT INTO TONKHOTHANG(NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLUONG,THANG,NAM)"+
						 "\n select npp_fk,kbh_fk,kho_fk,sanpham_fk,XNT,MONTH('"+denngay+"'),YEAR('"+denngay+"')   " + 
						 "\n from [dbo].[ufn_XNT_Total_KhoaSoThang]("+nppid+",'"+denngay+"')  " ;
				System.out.println("query thang la "+query);
				if(!db.update(query))
				{

					return "Không thể thiết lập khóa sổ tháng " +query;
				}

				query=	"\n insert into TONKHOTHANG_CHITIET(NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,THANG,NAM,SOLUONG)"+
						"\n select NPP_FK,KBH_FK,KHO_FK,SANPHAM_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,MONTH('"+denngay+"'),YEAR('"+denngay+"'),XNT  " + 
						"\n from [dbo].[ufn_XNT_ChiTiet_NGAYNHAPKHO_KhoaSoThang]("+nppid+",'"+denngay+"')  " ;

				System.out.println("XNT_CT_"+query);
				if(!db.update(query))			
				{
					return "Không thể thiết lập khóa sổ tháng " +query;
				}	
				
				
				query =" select COUNT (1)sd  where MONTH (DATEADD (dd, -1, DATEADD(qq, DATEDIFF(qq, 0,'"+tungay+"') +1, 0))) = MONTH('"+tungay+"') and YEAR(DATEADD (dd, -1, DATEADD(qq, DATEDIFF(qq, 0, '"+tungay+"') +1, 0))) = YEAR('"+tungay+"')";
				// lênh check tháng cuối quý
				ResultSet rs = db.get(query);
				
				if(rs.next())
				{
					if(rs.getInt("sd") > 0)// nếu là tháng cuối quý thì thiết lập lại hạng cửa hàng
					{
						String msg = CustomerClass(db, nppid,tungay);
						if(msg.length()>0)
						{

							return "Lỗi tăng hạng KH: "+ msg;
						}
					}
				}
				rs.close();
				
				


				query = "insert into khoasothang(thangks, nam, ngaytao, nguoitao, npp_fk,ngaythangnam) " +
				"select MONTH('"+tungay+"'), YEAR('"+denngay+"'), '" + this.getDateTime() + "', '"+this.userId+"', '" + nppid + "','"+tungay+"'";
				System.out.println("Query khoa so thang la: " + query);
				if(!db.update(query))
				{

					return "Không thể thiết lập khóa sổ tháng ";
				}	

				query=
					"\n	select d.MA as nppMA,d.TEN as nppTEN,c.MA as spMa,c.TEN as spTEN,isnull(a.SoLuong,0)as Tong,isnull(b.SoLuong,0) as CT  "+
					"\n	from  "+
					"\n	(  "+
					"\n		select a.NPP_FK,a.SANPHAM_FK,SUM(SOLUONG) as SoLuong,KHO_FK  "+
					"\n		from TONKHOTHANG a " +
					" \n  where a.npp_fk='"+nppid+"' and thang=MONTH('"+tungay+"') and nam=YEAR('"+denngay+"')        "+
					"\n		group by NPP_FK,SANPHAM_FK,KHO_FK "+
					"\n	)as a full outer join  "+ 
					"\n	(  "+
					"\n		select a.NPP_FK,a.SANPHAM_FK,SUM(SOLUONG) as SoLuong,KHO_FK  "+
					"\n		from TONKHOTHANG_CHITIET a   "+
					" \n  where a.npp_fk='"+nppid+"' and thang=MONTH('"+tungay+"') and nam=YEAR('"+denngay+"')       "+
					"\n		group by NPP_FK,SANPHAM_FK,KHO_FK   "+
					"\n	)as b on a.NPP_FK=b.NPP_FK and a.SANPHAM_FK=b.SANPHAM_FK  and a.KHO_FK=b.KHO_FK  "+
					"\n	left join SANPHAM c on c.PK_SEQ=ISNULL(a.SANPHAM_FK,b.SANPHAM_FK)  "+ 
					"\n	LEFT join NHAPHANPHOI d on d.PK_SEQ=ISNULL(a.NPP_FK,b.NPP_FK)  "+
					" \n where round(ISNULL(a.SoLuong,0),1)  <> round( isnull(b.SoLuong,0),1) ";

				System.out.println("query so la" +query);
				rs =db.get(query);
				String msg="";
				while(rs.next())
				{
					msg+=""+rs.getString("spMa") +" - "+rs.getString("spTen")+"----"+rs.getDouble("Tong")+"-----"+ rs.getDouble("CT")+"  \n";
				}
				rs.close();
				if(msg.length()>0)
				{

					msg +="Lỗi phát sinh do lệch số lượng của sản phẩm theo XNT TỔNG và XNT CT"+msg ;
					return msg;
				}

			}
			else
			{
				System.out.println("loi ràu ----------------");
				return "loi rui khong lay dc ngay ks";
			}
			rs1.close();
		} catch( Exception e)
		{
			e.printStackTrace();
			return "Loi rui khong feed dc";
		}
		return "";

	}

	public String CustomerClass(Idbutils db,String nppId,String ngaytrongquy)
	{
		String query =   "\n insert KH_HCH_LOG(thangks,nam,NPP_FK,KhachHang_FK,HCH_FK,doanhso ) " +
						 "\n select month('"+ngaytrongquy+"'),year('"+ngaytrongquy+"'),kh.NPP_FK, kh.PK_SEQ khId, hang.HCH_FK, ISNULL(ds.doanhso,0)doanhso   " + 
						 "\n from KHACHHANG kh  " + 
						 "\n left join  " + 
						 "\n (  " + 
						 "\n 	select dh.KHACHHANG_FK, round(SUM(Soluong*Giamua)/3.0,0) doanhso  " + 
						 "\n 	from DONHANG dh  " + 
						 "\n 	inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK  " + 
						 "\n 	where dh.NPP_FK ="+nppId+" and dh.TRANGTHAI = 1 and dh.NGAYNHAP >=CONVERT( varchar(10), DATEADD(qq, DATEDIFF(qq, 0, '"+ngaytrongquy+"'), 0),120) and dh.NGAYNHAP <=CONVERT( varchar(10),DATEADD (dd, -1, DATEADD(qq, DATEDIFF(qq, 0, '"+ngaytrongquy+"') +1, 0)),120)  " + 
						 "\n 	group by dh.KHACHHANG_FK  " + 
						 "\n )ds on kh.PK_SEQ = ds.KHACHHANG_FK  " + 
						 "\n inner join HANGCUAHANG_DOANHTHU hang on hang.DT_Tu <= ISNULL(ds.doanhso,0) and  ISNULL(ds.doanhso,0) < hang.DT_Den  " + 
						 "\n where kh.NPP_FK = "+nppId+" and kh.TRANGTHAI = 1  " ;
		if(!db.update(query))
		{
			return "Lỗi 1:" + query;
		}
		query = "update kh  set HCH_FK = l.HCH_FK from KHACHHANG kh inner join KH_HCH_LOG l on kh.PK_SEQ = l.KhachHang_FK and kh.NPP_FK = l.NPP_FK  where kh.NPP_FK ="+nppId+" and kh.TRANGTHAI = 1 and l.thangks  = month('"+ngaytrongquy+"') and l.nam = year('"+ngaytrongquy+"') ";
		if(!db.update(query))
		{
			return "Lỗi 2:" + query;
		}
		
		
		
		query =  "\n insert DDKD_KH_DOANHSOTHANG(thangks,nam,ddkd_fk,NPP_FK,KHACHHANG_FK,doanhso)  " + 
				 "\n select MONTH(dh.NGAYNHAP)thang ,YEAR(dh.NGAYNHAP) nam ,ddkd.ddkd_fk, dh.NPP_FK,dh.KHACHHANG_FK ,SUM(SOLUONG * GIAMUA) doanhso  " + 
				 "\n from DONHANG dh   " + 
				 "\n inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK  " + 
				 "\n left join DDKD_AllKhachHangthang ddkd on  CHARINDEX(  ',' +cast( DH.KHACHHANG_FK as varchar)+',', ddkd.Khachhang_Fks)>=1 and ddkd.thangks = MONTH(dh.NGAYNHAP) and ddkd.nam =YEAR(dh.NGAYNHAP)   " + 
				 "\n where dh.NPP_FK = 100002 and dh.TRANGTHAI = 1 and MONTH(dh.NGAYNHAP)=6 and YEAR(dh.NGAYNHAP) = 2017  " + 
				 "\n group by   MONTH(dh.NGAYNHAP),YEAR(dh.NGAYNHAP),dh.NPP_FK,dh.KHACHHANG_FK,ddkd.ddkd_fk ";
		if(!db.update(query))
		{
			return "Lỗi 2:" + query;
		}
		
		
		return "";
	}
	

	private String getthang_ks(dbutils db, String nppid) {
		// TODO Auto-generated method stub
		try{
			String query="select  top  1  NAM,THANGKS from  KHOASOTHANG where  NPP_FK="+nppid+" order by NAM desc,THANGKS desc";
			ResultSet rs=db.get(query);
			int thangks_ketiep=0;
			int namkh_ketiep=	0;
			if(rs.next()){
				if(rs.getInt("THANGKS")==12){
					thangks_ketiep=1;
					namkh_ketiep=rs.getInt("NAM")+1;

				}else{
					thangks_ketiep=rs.getInt("THANGKS")+1;
					namkh_ketiep=rs.getInt("NAM");

				}

			}
			rs.close();

			if(thangks_ketiep> 0 && namkh_ketiep >0 ) {
				return namkh_ketiep+"-"+(thangks_ketiep>=10?thangks_ketiep:"0"+thangks_ketiep);
			}else{
				return "";
			}



		}catch(Exception  er){
			return "";
		}

	}


	private String DayDH_OTC (String ngaydaydh,dbutils db,String nppid)
	{
		String msg="";
		String sql="update donhang set ngaynhap='"+ngaydaydh+"' where trangthai=0  and npp_fk="+nppid;
		if(!db.update(sql))
		{
			msg="không thể đẩy DHOTC lỗi 26";
			return msg;
		}

		return msg;
	}


	private String DayDH_ETC (String thangks,String ngaydaydh, dbutils db,String nppid)
	{
		String msg="";

		String sql="update ERP_DONDATHANGNPP set ngaydonhang='"+ngaydaydh+"' where ngaydonhang like '"+thangks+"%'  and  trangthai=0  and npp_fk="+nppid;
		System.out.println("sql : "+sql);
		if(!db.update(sql))
		{
			msg="không thể đẩy DHETC lỗi 26";
			return msg;
		}

		return msg;
	}

	public String capNhatKM(String id, String nppId, String khId, String trangthai, dbutils db,String ngaydh) 
	{
		try 
		{
			String query =  " select ctkmId, trakmId, khoNVBH, sum(tonggiatri) as tonggiatri " +
			" from donhang_ctkm_trakm where donhangid='" + id + "' and khoNVBH = '0' " +
			" group by ctkmId, trakmId, khoNVBH";
			//System.out.println("Cau lenh lay du lieu cap nhat: " + query + "\n");
			ResultSet rs = db.get(query);

			while (rs.next()) 
			{
				String ctkmId = rs.getString("ctkmId");
				String tonggiatri = rs.getString("tonggiatri");
				String trakmId = rs.getString("trakmId");
				String st = "update Phanbokhuyenmai set DASUDUNG = DASUDUNG - '" + tonggiatri + "' " +
				"where ctkm_fk='" + ctkmId + "' and npp_fk='" + nppId + "'";
				System.out.println(st);

				if (!db.update(st)) {
					return "Error :" + st;
				}


			}
			rs.close();

			Utility util_kho=new Utility();
			if (!trangthai.equals("3")) 
			{
				query=  " SELECT DH.NPP_FK,  KM.KHO_FK, " +
				" SP.PK_SEQ AS SPID,DH.KBH_FK  "+
				" ,sum(DHCT.SOLUONG) as SOLUONG "+
				" FROM DONHANG_CTKM_TRAKM  DHCT "+
				" INNER JOIN SANPHAM SP ON SP.MA= DHCT.SPMA "+
				" INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ=DHCT.CTKMID "+
				" INNER JOIN DONHANG DH ON DH.PK_SEQ=DHCT.DONHANGID "+
				" WHERE DONHANGID= "+id+" AND KHONVBH = '0' AND DH.TRANGTHAI='0'   "+
				" GROUP BY DH.NPP_FK,  KM.KHO_FK,SP.PK_SEQ  ,DH.KBH_FK";
				ResultSet rssp=db.get(query);
				while(rssp.next()){
					String _spid=rssp.getString("SPID") ;
					String _nppid=rssp.getString("NPP_FK") ;
					String _khoid=rssp.getString("KHO_FK") ;
					String _kbhid=rssp.getString("KBH_FK") ;
					double _SOLUONG=rssp.getDouble("SOLUONG");

					String msg1= util_kho.Update_NPP_Kho_Sp(ngaydh, "9398.donhang.java,Cập nhật khuyến mãi", db, _khoid,_spid , _nppid, _kbhid, 0, (-1)* _SOLUONG, _SOLUONG, 0);
					if(msg1.length() > 0){

						return "Error :" + msg1;
					}
				}
				rssp.close();

				// delete neu ton tai, cap nhat lai kho voi so luong tang
				query = "delete from donhang_ctkm_trakm where donhangid = '" + id + "' and khoNVBH = '0' ";

				System.out.println(query);
				if (!db.update(query))
				{

					System.out.println("Error :" + query);
					return "Error :" + query;
				}


				query=  " SELECT DH.NPP_FK,   KM.KHO_FK,DHCT.sanpham_fk AS SPID,DH.KBH_FK ,DHCT.SOLO,DHCT.NGAYHETHAN,DHCT.NGAYNHAPKHO "+
				" , DHCT.SOLUONG  SOLUONG "+
				" FROM DONHANG_CTKM_TRAKM_CHITIET  DHCT "+
				" INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ=DHCT.ctkm_fk "+
				" INNER JOIN DONHANG DH ON DH.PK_SEQ=DHCT.DONHANG_fk "+
				" WHERE DHCT.donhang_fk= "+id+" AND DHCT.KHONVBH = '0'   ";

				rssp=db.get(query);
				while(rssp.next()){
					String _spid=rssp.getString("SPID") ;
					String _nppid=rssp.getString("NPP_FK") ;
					String _khoid=rssp.getString("KHO_FK") ;
					String _kbhid=rssp.getString("KBH_FK") ;
					double _SOLUONG=rssp.getDouble("SOLUONG");
					String _solo=rssp.getString("solo") ;
					String _ngaynhapkho=rssp.getString("ngaynhapkho") ;
					String _ngayhethan=rssp.getString("ngayhethan") ;


					String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(ngaydh, "9448. donhang.java Cập nhật đơn hàng" 
							, db, _khoid, _spid, _nppid, _kbhid, _solo, _ngayhethan, _ngaynhapkho, 0 ,(-1)* _SOLUONG,    _SOLUONG, _SOLUONG, 0); // giảm booked, tăng avail lại

					if(msg1.length() > 0){

						return "Error :" + msg1;
					}
				}
				rssp.close();

				// delete neu ton tai, cap nhat lai kho voi so luong tang
				query = "delete from DONHANG_CTKM_TRAKM_CHITIET where DONHANG_FK = '" + id + "' and khoNVBH = '0' ";

				System.out.println(query);
				if (!db.update(query))
				{

					System.out.println("Error :" + query);
					return "Error :" + query;
				}

				query = "delete DUYETTRAKHUYENMAI_DONHANG where donhang_fk = '" + id + "' ";
				if (!db.update(query))
				{

					System.out.println("Error :" + query);
					return "Error :" + query;
				}

				query = "delete DUYETTRAKHUYENMAI_DUNO_DONHANG_DATRA where donhang_fk = '" + id + "' ";
				if (!db.update(query))
				{

					System.out.println("Error :" + query);
					return "Error :" + query;
				}



				Update_GiaTri_DonHang(id, db);

			}

			//  chức năng xóa bảng chi tiết cập nhật lại số lô


			System.out.println("Oke Da xONg");
			return "";
		} 
		catch (Exception e1) 
		{
			e1.printStackTrace();

			System.out.println("Error :" + e1.toString());
			return "Error :" + e1.toString();
		}
	}





	private String Delete_ETC(String lsxId, String userId, String nppId,dbutils db) 
	{

		String msg = "";

		Utility util = new Utility();

		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_HOADONNPP", lsxId, "ngayxuatHD", db);
		if(msg.length()>0)
		{

			return msg;
		}

		try
		{	

			String query = "";

			// Kiểm tra import =1 thì k cho hủy
			query = "select isnull(import, 0) as import, \n" +
			"(  SELECT count(B.PK_SEQ) DEM \n" +
			"	FROM ERP_THUTIENNPP_HOADON A INNER JOIN ERP_THUTIENNPP B ON A.THUTIENNPP_FK = B.PK_SEQ \n" +
			"   INNER JOIN ERP_HOADONNPP C ON A.HOADONNPP_FK = C.PK_SEQ AND A.LOAIHD = 0 \n" +
			" 	LEFT JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = C.NPP_DAT_FK AND NPP.LOAINPP=4 and NPP.TRUCTHUOC_FK = '"+ nppId +"'  \n" +
			"   LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = C.KHACHHANG_FK  \n" +
			" 	AND C.KHACHHANG_FK in \n"+
			" 	(SELECT PK_SEQ from KHACHHANG WHERE KBH_FK=100052 and NPP_FK='" + nppId +"') \n"+
			"   WHERE B.TRANGTHAI IN (0,1) AND C.PK_SEQ = "+lsxId + " AND C.KHACHHANG_FK = A.KHACHHANG_FK  ) is_ThuTien \n"+
			"from ERP_DONDATHANGNPP " +
			"where pk_seq in (select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK = '" + lsxId + "')";

			System.out.println(query);
			ResultSet rsKT = db.get(query);
			int ktra = 0;
			int is_ThuTien = 0;

			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					ktra = rsKT.getInt("import");
					is_ThuTien = rsKT.getInt("is_ThuTien");
				}
				rsKT.close();
			}

			if(ktra == 1)
			{
				msg = "Đơn hàng này GESO import nên không hủy được. ";

				return msg;
			}
			else
			{

				if(is_ThuTien > 0)
				{
					msg = "Hóa đơn đã được thu tiền. Bạn không được xóa/hủy hóa đơn. ";

					return msg;
				}

				query = "update ERP_HOADONNPP set trangthai = '3' where pk_seq = '" + lsxId + "' ";
				System.out.println(query);
				if(!db.update(query))
				{
					msg = "Không thể hủy ERP_HOADONNPP " + query;
					db.getConnection().rollback();
					return msg;
				}

				// Lấy mã đơn đặt hàng
				query = " select DDH_FK,  " +
				" ( select top 1  YCXK_FK from ERP_YCXUATKHONPP_DDH where ddh_fk = a.DDH_FK order by ycxk_fk desc) as soPXK   " +
				" from ERP_HOADONNPP_DDH a where HOADONNPP_FK = '" + lsxId + "' ";
				ResultSet rsDDH = db.get(query);
				String dondathang_fk1 = "";
				String pxk_fk = "";
				/*if(rsDDH != null)*/
				{

					if(rsDDH.next())
					{
						dondathang_fk1 = rsDDH.getString("DDH_FK");	
						pxk_fk = rsDDH.getString("soPXK");	
					}

					query = "select khachhang_fk, a.kbh_fk, a.npp_fk, a.npp_dat_fk, " +
					"( select priandsecond from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatOTC,  " +
					"( select tuxuatETC from NHAPHANPHOI where pk_seq = a.npp_fk ) as tuxuatETC,  " +
					"( select loaiNPP from NHAPHANPHOI where pk_seq = a.npp_fk ) as loaiNPP, " +
					"( select tructhuoc_fk from NHAPHANPHOI where pk_seq = a.npp_fk ) as tructhuoc_fk,  " +
					" ISNULL( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh  " +
					"from ERP_DondathangNPP a where a.pk_seq = '" + dondathang_fk1 + "' order by pk_seq desc";

					System.out.println(dondathang_fk1);

					String khachhangID = "";
					String loaiNPP = "";
					String tructhuoc = "";

					String npp_dat_fk = "";
					String kbh_fk1 = "";

					String tuquanlyKHO_OTC = "0";
					String tuquanlyKHO_ETC = "0";			

					ResultSet rs = db.get(query);
					/*if(rs != null)*/
					{
						if(rs.next())
						{
							if(rs.getString("khachhang_fk") != null)
								khachhangID = rs.getString("khachhang_fk");

							loaiNPP = rs.getString("loaiNPP");
							tructhuoc = rs.getString("tructhuoc_fk");
							nppId = rs.getString("npp_fk");

							if(rs.getString("npp_dat_fk") != null)
								npp_dat_fk = rs.getString("npp_dat_fk");

							if(rs.getString("dungchungkenh").equals("1"))
								kbh_fk1 = "100025";
							else
								kbh_fk1 = rs.getString("kbh_fk");

							if(rs.getString("tuxuatOTC") != null)
								tuquanlyKHO_OTC = rs.getString("tuxuatOTC");

							if(rs.getString("tuxuatETC") != null)
								tuquanlyKHO_ETC = rs.getString("tuxuatETC");

						}
						rs.close();
					}

					if( ( khachhangID.trim().length() > 0 && tuquanlyKHO_ETC.equals("0") )  )
					{
						if (tructhuoc.trim().length() >= 5) // TRỰC THUỘC NPP
						{
							// check tồn kho
							msg = HuyPhieuXuatKho_CapTren_NPP(db,dondathang_fk1, tructhuoc);
							if(msg.trim().length() > 0)
							{
								msg = "Khong the huy: " + msg;
								return msg;
							}
						}
						else
							// PHIẾU YCXK của HO
						{
							msg = HuyPhieuXuatKho_CapTren_HO(dondathang_fk1, tructhuoc, db);
							if(msg.trim().length() > 0)
							{
								msg = "Khong the huy: " + msg;
								return msg;
							}
						}
					}
					else
						// Tạo PXK cho NPP (NPP TU QUAN LY TON KHO)
					{
						msg = HuyPhieuXuatKhoNPP( pxk_fk, npp_dat_fk, db ,dondathang_fk1 );
						if(msg.trim().length() > 0)
						{
							msg = "Khong the huy: " + msg;
							return msg;
						}
					}
				} 
			}

			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{

				return msg;
			}


		}
		catch (Exception e) 
		{

			return "Exception: " + e.getMessage();
		}		

		return "";
	}


	private String HuyPhieuXuatKhoNPP(String ycxkId, String npp_dat_fk, dbutils db,String dondathang_fk1)
	{

		Utility util_kho=new Utility();
		String msg = "";
		try
		{			
			//CHECK KHOA SO THANG
			String query ="";
			//CHECK XEM DUOI NPP DA NHAN HANG CHUA
			if (npp_dat_fk.trim().length() > 0)
			{
				//Tu dong tao nhan hang
				query = " select count(*) as soDONG from NHAPHANG where YCXKNPP_FK = '" + ycxkId + "' and trangthai = '1' ";

				int soDONG = 0;
				ResultSet rs = db.get(query);
				{
					if(rs.next())
						soDONG = rs.getInt("soDONG");
					rs.close();
				}

				if(soDONG > 0)
				{
					msg = "Hóa đơn đã có nhận hàng, bạn không thể hủy / xóa ";
					return msg;
				}

				//XOA NHAN HANG
				query = " delete NHAPHANG_SP where nhaphang_fk in ( select pk_seq from NHAPHANG where YCXKNPP_FK = '" + ycxkId + "' )  ";
				if(!db.update(query))
				{
					msg = "Không thể cập nhật NHAPHANG_SP:  " + query;
					return msg;
				}

				query = " delete NHAPHANG where YCXKNPP_FK = '" + ycxkId + "'   ";
				if(!db.update(query))
				{
					msg = "Không thể cập nhật NHAPHANG_SP:  " + query;
					return msg;
				}
			}


			query = " SELECT ngayyeucau,kho_fk,NPP_FK,SANPHAM_FK,kbh_fk ,soluong FROM   " +
			" (  " +
			"	select ngayyeucau,a.kho_fk, a.NPP_FK, b.SANPHAM_FK, case when isnull(c.dungchungkenh, 0) = 0 then  " +
			"   a.kbh_fk else 100025 end as kbh_fk,  SUM(b.soluongXUAT) as soluong  " +
			"	from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM b on a.PK_SEQ = b.YCXK_FK " +
			"			inner join NHAPHANPHOI c on a.npp_FK = c.pk_seq  " +
			"	where a.PK_SEQ = '" + ycxkId + "' and b.soluongXUAT > 0 " +
			"	group by a.kho_fk, a.kbh_fk, c.dungchungkenh, a.NPP_FK, b.SANPHAM_FK ,ngayyeucau  " +
			" )  a " ;

			ResultSet rskho=db.get(query);
			while(rskho.next()){
				String _kho_fk=rskho.getString("kho_fk");
				String _npp_fk=rskho.getString("NPP_FK");
				String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
				String _kbh_fk=rskho.getString("kbh_fk");
				String ngayyeucau=rskho.getString("ngayyeucau");
				double soluong=rskho.getDouble("soluong");
				// tăng booked, tăng số lượng
				String msg1= util_kho.Update_NPP_Kho_Sp(ngayyeucau, "Hủy hóa đơn ETC +Đối tác ErpHoadontaichinhNPPSvl 776 ", 
						db, _kho_fk, _SANPHAM_FK, _npp_fk, _kbh_fk, soluong, soluong, 0, 0);
				if(msg1.length()>0 ){
					return msg1;
				}

			}
			rskho.close();
			query= " SELECT NGAYYEUCAU,KHO_FK,NPP_FK,SANPHAM_FK,KBH_FK ,SOLO,NGAYNHAPKHO,NGAYHETHAN,SOLUONG FROM "+   
			" (    "+
			" SELECT NGAYYEUCAU,A.KHO_FK, A.NPP_FK, B.SANPHAM_FK,SOLO,B.NGAYNHAPKHO,NGAYHETHAN, "+
			" CASE WHEN ISNULL(C.DUNGCHUNGKENH, 0) = 0 THEN   "+
			" A.KBH_FK ELSE 100025 END AS KBH_FK,  SUM(B.SOLUONG) AS SOLUONG "+  
			" FROM ERP_YCXUATKHONPP A INNER JOIN ERP_YCXUATKHONPP_SANPHAM_CHITIET B ON A.PK_SEQ = B.YCXK_FK "+ 
			" INNER JOIN NHAPHANPHOI C ON A.NPP_FK = C.PK_SEQ   "+
			" WHERE A.PK_SEQ = "+ycxkId+"  AND B.SOLUONG > 0  "+
			" GROUP BY A.KHO_FK, A.KBH_FK, C.DUNGCHUNGKENH, A.NPP_FK, B.SANPHAM_FK ,NGAYYEUCAU ,SOLO,NGAYNHAPKHO,NGAYHETHAN  "+ 
			" ) A ";
			rskho=db.get(query);
			while(rskho.next()){
				String _kho_fk=rskho.getString("kho_fk");
				String _npp_fk=rskho.getString("NPP_FK");
				String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
				String _kbh_fk=rskho.getString("kbh_fk");
				String ngayyeucau=rskho.getString("ngayyeucau");
				String solo=rskho.getString("solo");
				String ngaynhapkho=rskho.getString("NGAYNHAPKHO");
				String ngayhethan=rskho.getString("NGAYHETHAN");

				double soluong=rskho.getDouble("soluong");
				// tăng booked, tăng số lượng
				String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(ngayyeucau, "Hủy hóa đơn ETC +Đối tác ErpHoadontaichinhNPPSvl 776 ",
						db, _kho_fk, _SANPHAM_FK, _npp_fk, _kbh_fk, solo, ngayhethan, ngaynhapkho, soluong, soluong, 0, 0, 0);
				if(msg1.length()>0 ){
					return msg1;
				}
			}

			rskho.close();

			//MO LAI TRANG THAI DON HANG
			query = "update ERP_DONDATHANGNPP set trangthai = '0' ,checkkho='1' where pk_seq in  " +
			" ( select DDH_FK from ERP_YCXUATKHONPP_DDH where ycxk_fk = '" + ycxkId + "' ) and TrangThai!=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Đơn đặt hàng này đã mở trạng thái rồi "+query;
				return msg;
			}

			query = "Update ERP_YCXUATKHONPP set TRANGTHAI= '3' where pk_seq = '" + ycxkId + "' --and TrangThai!=3 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Đơn đặt hàng này đã mở trạng thái rồi "+query;
				return msg;
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Không thể hủy hóa đơn " + e.getMessage();
		}
		return "";
	}

	private String HuyPhieuXuatKho_CapTren_HO (String dondathang_fk1, String tructhuoc,dbutils db)
	{
		String msg="";

		try 
		{			

			String	query = "select sp.PK_SEQ, sp.TEN, LOAI, SCHEME, SUM(dathang.soluong) as soluongXUAT " +
			"from    " +
			"(    " +
			"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,    " +
			"				case when a.dvdl_fk IS null then a.soluong     " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong    " +
			"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )     " +
			"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )	 end as soluong, 0 as loai, ' ' as scheme   " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
			"		where a.dondathang_fk in ( '" + dondathang_fk1 + "' )   " +
			")    " +
			"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ  " +
			"group by sp.PK_SEQ, sp.TEN, LOAI, SCHEME ";
			System.out.println("--CHECK KHO CHI TIET: " + query);

			ResultSet rsCHECK = db.get(query);


			db.getConnection().setAutoCommit(false);

			if(rsCHECK != null)
			{
				while(rsCHECK.next())
				{
					String sanpham_fk = rsCHECK.getString("PK_SEQ");
					String LOAI = rsCHECK.getString("LOAI");
					String SCHEME = rsCHECK.getString("SCHEME");
					double soLUONG = rsCHECK.getDouble("soluongXUAT");

					query = "Update ERP_KHOTT_SANPHAM set soluong = soluong + '" + soLUONG + "', AVAILABLE = AVAILABLE + '" + soLUONG + "' " +
					"where KHOTT_FK = '" + 100001 + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";

					//CAP NHAT KHO CHI TIET
					query = "select AVAILABLE, SOLO from ERP_KHOTT_SP_CHITIET " +
					"where KHOTT_FK = '" + 100001 + "'  and SANPHAM_FK = '" + sanpham_fk + "' order by ngayhethan asc ";

					ResultSet rsTK = db.get(query);
					double avai = 0;
					double totalXUAT = 0;
					while(rsTK.next())
					{
						double soluongCT = 0;
						String solo = rsTK.getString("SOLO");						
						avai = rsTK.getDouble("AVAILABLE");

						soluongCT = avai;

						query = "Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + soluongCT + "', AVAILABLE = AVAILABLE - '" + soluongCT + "' " +
						"where KHOTT_FK = '" + 100001 + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "'  ";

						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
							return msg;
						}
					}

					rsTK.close();

				}
				rsCHECK.close();
			}

			query = "update ERP_DONDATHANGNPP set trangthai = '0' where pk_seq = '" + dondathang_fk1 + "' ";
			System.out.println(query);
			if(!db.update(query))
			{
				msg = "Không thể cập nhật trạng thái ERP_DONDATHANGNPP:  " + query;
				db.getConnection().rollback();
				return msg;
			}

			query = "select * from ERP_YCXUATKHONPP_DDH  WHERE ddh_fk='"+dondathang_fk1+"'";
			ResultSet RS_PhieuXUATKHO = db.get(query);			
			String PhieuXUATKHO="";
			if(RS_PhieuXUATKHO!=null)
				PhieuXUATKHO = RS_PhieuXUATKHO.getString("ycxk_fk"); 

			// hủy phiếu xuất kho
			query = "Update ERP_YCXUATKHONPP set TRANGTHAI= '3' where pk_seq = '" + PhieuXUATKHO + "' ";
			System.out.println(query);
			if(!db.update(query))
			{
				msg = "Không thể cập nhật trạng thái ERP_YCXUATKHONPP:  " + query;
				return msg;
			}	

		}
		catch (Exception e) 
		{
			msg = "--LOI DUYET: " + e.getMessage();
			e.printStackTrace();
			return msg;
		}

		return "";
	}

	private String HuyPhieuXuatKho_CapTren_NPP(dbutils db ,String dondathang_fk1, String tructhuoc)
	{
		// check tồn kho
		String msg="";
		String query = "";
		query =  "select khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
		"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = '" + tructhuoc + "' ), 0) as tonkho  " +
		"from     " +
		"(     " +
		"	select c.kho_fk as khoxuat_fk, '" + tructhuoc + "' as npp_fk, c.kbh_fk, a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
		"			case when a.dvdl_fk IS null then a.soluong      " +
		"				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
		"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
		"								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
		"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
		"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
		"	where a.dondathang_fk in ( " + dondathang_fk1 + " )     " +
		")     " +
		"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
		"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN  ";		
		System.out.println("Câu query ở đây: " + query);
		ResultSet rs_tonkho = db.get(query);
		try
		{
			System.out.println("vao day: ");
			if(rs_tonkho != null)
			{
				System.out.println("vao day1: ");
				while(rs_tonkho.next())
				{
					System.out.println("vao day2: ");
					String npp_fk = rs_tonkho.getString("npp_fk");					
					String kbh_fk = rs_tonkho.getString("kbh_fk");
					String kho_fk = rs_tonkho.getString("kho_fk");
					String sanpham_fk = rs_tonkho.getString("PK_SEQ");

					double soluong = rs_tonkho.getDouble("soluongXUAT");
					double tonkho = rs_tonkho.getDouble("tonkho");							

					//CAP NHAT KHO XUAT TONG
					query = "Update NHAPP_KHO set soluong = soluong + '" + soluong + "', BOOKED = BOOKED + '" + soluong + "' " +
					"where KHO_FK = '"+kho_fk+"' and KBH_FK = '" + kbh_fk + "' and NPP_FK = '" + npp_fk + "' and SANPHAM_FK = '" + sanpham_fk + "' ";
					System.out.println(query);
					if(db.updateReturnInt(query)!=1)
					{
						msg = "Khong the cap nhat NHAPP_KHO: " + query;
						rs_tonkho.close();
						return msg;
					}

					//CAP NHAT KHO CHI TIET
					query = "select AVAILABLE, SOLO, ngayhethan from NHAPP_KHO_CHITIET " +
					"where AVAILABLE > 0 and KHO_FK = '" + kho_fk + "'  and SANPHAM_FK = '" + sanpham_fk  + "' and NPP_FK = '" + npp_fk + "' and KBH_FK = '" + kbh_fk + "' order by ngayhethan asc ";

					System.out.println(query);

					ResultSet rsTK = db.get(query);
					double avai = 0;
					double totalXUAT = 0;
					while(rsTK.next())
					{
						double soluongCT = 0;
						String solo = rsTK.getString("SOLO");
						String ngayhethan = rsTK.getString("ngayhethan");

						avai = rsTK.getDouble("AVAILABLE");
						//totalXUAT += avai;
						soluongCT = avai;
						query = "Update NHAPP_KHO_CHITIET set soluong = soluong + '" + soluongCT + "', AVAILABLE = AVAILABLE + '" + soluongCT + "' " +
						"where KHO_FK = '" + kho_fk + "' and SOLO = '" + solo + "' and SANPHAM_FK = '" + sanpham_fk + "' adn KBH_FK = '" + kbh_fk + "' and NPP_FK = '" + npp_fk + "' and NgayHetHan='"+ngayhethan+"' ";
						System.out.println(query);

						if(db.updateReturnInt(query)!=1)
						{
							msg = "Khong the cap nhat NHAPP_KHO_CHITIET: " + query;
							rsTK.close();
							return msg;
						}
					}
					rsTK.close();
				}
				rs_tonkho.close();


				query = "update ERP_DONDATHANGNPP set trangthai = '0' where pk_seq = '" + dondathang_fk1 + "' ";
				System.out.println(query);
				if(!db.update(query))
				{
					msg = "Không thể cập nhật trạng thái ERP_DONDATHANGNPP:  " + query;
					return msg;
				}

				query = "select * from ERP_YCXUATKHONPP_DDH  WHERE ddh_fk='"+dondathang_fk1+"'";
				ResultSet RS_PhieuXUATKHO = db.get(query);
				//----
				String PhieuXUATKHO="";
				if(RS_PhieuXUATKHO!=null)
					PhieuXUATKHO = RS_PhieuXUATKHO.getString("ycxk_fk"); //////////////////

				// hủy phiếu xuất kho
				query = "Update ERP_YCXUATKHONPP set TRANGTHAI= '3' where pk_seq = '" + PhieuXUATKHO + "' ";
				System.out.println("cap nhat huy phieu xuat kho"+query);
				if(!db.update(query))
				{
					msg = "Không thể cập nhật trạng thái ERP_YCXUATKHONPP:  " + query;
				}	
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			return "Exception  HuyPhieuXuatKho_CapTren_NPP ";
		}
		return msg;
	}


	private String Delete_OTC(String lsxId, String userId, String nppId,dbutils db) 
	{

		String msg = "";

		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("HoaDon", lsxId, "NgayXuatHD", db);
		if(msg.length()>0)
		{

			return msg;		
		}
		try
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");


			String query = "";

			// Kiểm tra import =1 thì k cho hủy
			query = 				
				" SELECT count(B.PK_SEQ) is_ThuTien " +
				" FROM ERP_THUTIENNPP_HOADON A INNER JOIN ERP_THUTIENNPP B ON A.THUTIENNPP_FK = B.PK_SEQ \n" +
				" INNER JOIN HOADON C ON A.HOADONNPP_FK = C.PK_SEQ AND A.LOAIHD = 0 \n" +
				" AND A.KHACHHANG_FK in \n"+
				" (SELECT PK_SEQ FROM KHACHHANG WHERE KBH_FK = 100025 and NPP_FK='" + nppId +"')  " +	
				" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = C.KHACHHANG_FK  \n" +
				" WHERE B.TRANGTHAI IN (0,1) AND C.PK_SEQ = "+lsxId + " AND C.KHACHHANG_FK = A.KHACHHANG_FK  \n";

			System.out.println(query);
			ResultSet rsKT = db.get(query);
			int is_Thutien = 0;

			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					is_Thutien =  rsKT.getInt("is_ThuTien");
				}
				rsKT.close();
			}

			if(is_Thutien > 0)
			{
				msg = "Hóa đơn này đã thu tiền. Bạn không được phép xóa/hủy hóa đơn ";

				return msg;
			}



			//TU DONG XOA PHIEU XUAT KHO
			query = "select DDH_FK as donhang_fk,  " +
			"	( select npp_fk from HOADON where pk_seq = '" + lsxId + "' ) as nppId,	" +
			"	ISNULL( ( select Import from DONHANG where pk_seq = a.DDH_FK ), 0) as Import, " +
			"	( select PXK_FK from PHIEUXUATKHO_DONHANG where donhang_fk = a.DDH_FK and PXK_FK in ( select pk_seq from PHIEUXUATKHO where trangthai != '2' and npp_fk = ( select npp_fk from HOADON where pk_seq = '" + lsxId + "' ) ) ) as soPXK  " +
			"from HOADON_DDH a where hoadon_fk = '" + lsxId + "'";
			System.out.println("---HUY HOA DON: " + query );

			String donhang_fk = "";
			String Import = "";
			String pxkId = "";

			Import="";
			ResultSet rs = db.get(query);
			{
				while(rs.next())
				{
					donhang_fk = rs.getString("donhang_fk");
					Import = rs.getString("Import");
					nppId = rs.getString("nppId");

					if(Import.equals("0"))
					{
						pxkId = rs.getString("soPXK");
						//TANG KHO NGUOC LAI


						query = "update phieuxuatkho set trangthai = '2' where pk_seq = '" + pxkId + "' and TrangThai=1 ";
						if( db.updateReturnInt(query) <= 0 )
						{
							rs.close();
							msg = "Không thể hủy " + query;

							return msg;
						}

						//MO LAI TRANG THAI DON HANG
						query = "Update DONHANG set trangthai = '0',checkkho=1, daxuathoadon = '0' where pk_seq = '" + donhang_fk + "' and TrangThai=1 ";
						System.out.println("---CAP NHAT DON HANG: " + query);
						if( db.updateReturnInt(query) <= 0 )
						{
							rs.close();
							msg = "Không thể hủy đơn hàng " + query;

							return msg;
						}

						//XOA PHIEU CAN TRU NEU CO
						query = "UPDATE CANTRUCONGNO set trangthai = '2' where pk_seq in ( select CANTRUCONGNO_FK from CANTRUCONGNO_HOADON where hoadon_fk = '" + lsxId + "' ) ";
						if( !db.update(query) )
						{
							rs.close();
							msg = "Không thể hủy CANTRUCONGNO " + query;

							return msg;
						}
					}
					else
					{
						rs.close();
						msg = "Không thể hủy hóa đơn tự IMPORT vào hệ thống ";

						return msg;
					}
				}
				rs.close();
			}

			if(Import.equals("0")) {

				query=" SELECT ddh_fk FROM HOADON_DDH WHERE HOADON_FK="+lsxId;

				ResultSet rshd=db.get(query);
				int count=0;
				String ddhid="";
				while(rshd.next()){
					ddhid=rshd.getString("ddh_fk");
					count++;
				}
				rshd.close();
				if(count >1){
					db.getConnection().rollback();
					return "Vui lòng báo Admin để được trợ giúp, hóa đơn không hợp lệ để hủy, số chứng từ:"+lsxId;
				}


				query=" DELETE DONHANG_SANPHAM_CHITIET WHERE DONHANG_FK="+ddhid;
				if(!db.update(query)){

					return "Không thể thực hiện dòng lệnh : "+ query;
				}

				query=" DELETE DONHANG_CTKM_TRAKM_CHITIET where DONHANG_FK="+ddhid;
				if(!db.update(query)){

					return "Không thể thực hiện dòng lệnh : "+ query;
				}



				query=  " SELECT PK_SEQ,LOAIHOADON FROM  HOADON  "+
				" where   TRANGTHAI not in (3,5) and   pk_seq in (select hoadon_fk from HOADON_DDH where DDH_FK in "+ 
				" (select DDH_FK from HOADON_DDH where HOADON_FK = "+lsxId+"))  ";
				ResultSet rshoadon=db.get(query);
				int i=0;
				while(rshoadon.next()){
					i++;
					String hoadon_fk=rshoadon.getString("PK_SEQ");

					query=  " SELECT a.ngayxuathd, A.KHO_FK, A.KBH_FK, A.NPP_FK, SP.PK_SEQ as sanpham_fk,case when  B.SOLO=' ' then 'NA' " +
					" else B.SOLO end SOLO, SUM(B.SOLUONG) AS SOLUONG, case  when B.NGAYHETHAN=' ' then '2030-12-31' else B.ngayhethan end ngayhethan , NULL AS SCHEME "+   
					" ,ISNULL(B.NGAYNHAPKHO,'') AS NGAYNHAPKHO   "+
					" FROM HOADON A INNER JOIN HOADON_SP_CHITIET B ON A.PK_SEQ = B.HOADON_FK "+   
					" INNER JOIN SANPHAM SP ON SP.MA=B.MA "+
					" WHERE A.PK_SEQ = "+hoadon_fk+
					" GROUP BY a.ngayxuathd , A.KHO_FK, A.KBH_FK, A.NPP_FK,SP.PK_SEQ, B.SOLO,B.NGAYHETHAN  ,B.NGAYNHAPKHO " +
					" UNION ALL " +
					" SELECT  a.ngayxuathd, B.KHO_FK, A.KBH_FK, A.NPP_FK, SP.PK_SEQ, case when  B.SOLO=' ' then 'NA' " +
					" else B.SOLO end SOLO,  SUM(B.SOLUONG) AS SOLUONG, case  when B.NGAYHETHAN=' ' then '2030-12-31' else B.ngayhethan end ngayhethan ,SCHEME   "+
					" ,ISNULL(B.NGAYNHAPKHO,'') AS NGAYNHAPKHO   "+
					" FROM HOADON A INNER JOIN HOADON_CTKM_TRAKM_CHITIET B ON A.PK_SEQ = B.HOADONID "+   
					" INNER JOIN SANPHAM SP ON SP.PK_SEQ=B.SANPHAM_FK "+
					" WHERE A.PK_SEQ =    "+hoadon_fk + 
					" GROUP BY  a.ngayxuathd, B.KHO_FK, A.KBH_FK, A.NPP_FK,SP.PK_SEQ, B.SOLO,B.NGAYHETHAN  ,B.NGAYNHAPKHO,SCHEME  ";

					System.out.println(query);
					ResultSet rskho=db.get(query);
					while(rskho.next()){
						String _khoid=rskho.getString("kho_fk");
						String ngayxuathd=rskho.getString("ngayxuathd");
						String _kbh_fk=rskho.getString("kbh_fk");
						String _NPP_FK=rskho.getString("NPP_FK");
						String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
						String _SOLO=rskho.getString("SOLO");
						String _NgayHetHan=rskho.getString("NgayHetHan");
						String _ngaynhapkho=rskho.getString("ngaynhapkho");
						String SCHEME=rskho.getString("SCHEME");

						double soluongct=rskho.getDouble("SOLUONG");
						String msg1=util.Update_NPP_Kho_Sp_Chitiet(ngayxuathd, "Xoa HoadontaichinhSvl.java 552 ", db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, 
								_NgayHetHan, _ngaynhapkho, soluongct, soluongct, 0, 0, 0);

						if(msg1.length() >0){

							return msg1;
						}
						msg1=util.Update_NPP_Kho_Sp(ngayxuathd, "Xoa HoadontaichinhSvl.java 552", db, _khoid, 
								_SANPHAM_FK, _NPP_FK, _kbh_fk, soluongct, soluongct, 0, 0);


						if(msg1.length() >0){

							return msg1;
						}
						if(rshoadon.getString("loaihoadon").equals("0")){
							query=" insert into DONHANG_SANPHAM_CHITIET  (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk) values ("+ddhid+","+_SANPHAM_FK+","+_kbh_fk+","+_khoid+",N'"+_SOLO+"','"+_NgayHetHan+"','"+_ngaynhapkho+"',"+soluongct+","+_NPP_FK+")";
							if(db.updateReturnInt(query) <=0){
								db.getConnection().rollback();
								return "Không thể thực hiện dòng lệnh : "+ query;


							}
						}else{
							query="INSERT INTO DONHANG_CTKM_TRAKM_CHITIET (DONHANG_FK,SANPHAM_FK,SOLUONG,SOLO,NGAYNHAPKHO,NGAYHETHAN,KHONVBH,CTKM_FK,trakm_fk) values " +
							"("+ddhid+","+_SANPHAM_FK+","+soluongct+",'"+_SOLO+"','"+_ngaynhapkho+"','"+_NgayHetHan+"','0',"+SCHEME+",1)";
							if(db.updateReturnInt(query) <=0){

								return "Không thể thực hiện dòng lệnh : "+ query;


							}

						}

					}


				}
				if(i >3 || i<1){
					db.getConnection().rollback();
					return "Vui lòng báo Admin để được xử lý,lỗi khi hủy hóa đơn được tạo ra nhiều từ đơn đặt hàng : "+lsxId;

				}

				msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
				if(msg.length()>0)
				{

					return msg;
				}	
			}



			query = "update HOADON set trangthai = '3', nguoisua = '" + userId + "',NgayGioSua=getdate(),NgaySua='"+getDateTime()+"' where pk_seq = '" + lsxId + "' and TrangThai!=3 ";
			System.out.println("---HUY HOA DON: 01 " + query );
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã xóa rồi ";

				return msg;
			}

			//HUY HOA DON KHUYEN MAI HOAC BAN NEU CO
			query = " update HOADON set trangthai = '3', nguoisua = '" + userId + "',NgayGioSua=getdate(),NgaySua='"+getDateTime()+"' " +
			" where  loaihoadon in ( 1,2) and pk_seq in (select hoadon_fk from HOADON_DDH where DDH_FK in (select DDH_FK from HOADON_DDH where HOADON_FK = '" + lsxId + "') and hoadon_fk != '" + lsxId + "' )  ";

			System.out.println("---HUY HOA DON: 02 " + query );
			if(!db.update(query))
			{
				msg = "Không thể cập nhật HOADON " + query;

				return msg;
			}





			//Đối vơi HD tháng 1,ckQúy và tháng lấy trong 1 HĐ
			/********************************************/
			query=			
				"	select ckThang.hoadon_fk as ckThangId,ckQuy.hoadon_fk as ckQuyId  "+ 
				"	from  "+
				"	(  "+
				"		select b.hoadon_fk,a.KHACHHANG_FK,a.NPP_FK  "+
				"		from HOADON a inner join HOADON_CHIETKHAU b on b.hoadon_fk=a.PK_SEQ  "+
				"		where b.tichluyQUY=0 and a.TRANGTHAI not in (1,3,5) and diengiai like 'CT%' and a.NGAYXUATHD like '2015-01%' and a.pk_seq='"+lsxId+"' "+
				"	)as ckThang left join  "+ 
				"	(  "+
				"		select b.hoadon_fk,a.KHACHHANG_FK,a.NPP_FK  "+
				"		from HOADON a inner join HOADON_CHIETKHAU b on b.hoadon_fk=a.PK_SEQ  "+
				"		where b.tichluyQUY=1 and a.TRANGTHAI not in (1,3,5) and b.diengiai like 'CQ%' and a.NGAYXUATHD like '2015-01%'  "+
				"	)as ckQuy on ckThang.KHACHHANG_FK=ckQuy.KHACHHANG_FK and ckThang.NPP_FK=ckQuy.NPP_FK and ckThang.hoadon_fk!=ckQuy.hoadon_fk  "+
				"	where ckQuy.hoadon_fk is not null  ";
			rs=db.get(query);
			while(rs.next())
			{
				msg +="Vui lòng xóa hóa đơn đã hưởng ck Qúy  mã số "+rs.getString("ckQuyId")+" trước khi xóa HĐ đã hưởng ck Tháng \n";
			}
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}
			/********************************************/

			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{

				return msg;
			}

			//Cập nhật số hóa đơn trong đơn hàng
			util.Update_SoHoaDon("select DDH_FK from HOADON_DDH where HOADON_FK = '" + lsxId + "'", db);


			//Kiểm tra xem đã có hưởng ck tháng hay chưa.
			msg=util.Check_CK_DaHuong(lsxId,userId,db);

		}
		catch (Exception e) 
		{
			e.printStackTrace();

			return "Exception: " + e.getMessage();
		}

		return msg;
	}



	public void Update_GiaTri_DonHang(String dhId, dbutils db)
	{

		try{
			String query =  "update DH set " +
			"		DH.tongtienTRUOCCHIETKHAU = TGT.tongTIEN, " +
			"		DH.tongtienCHIETKHAU = TGT.tongTL, " +
			"		DH.TONGGIATRI = case TGT.thanhtoan when 1 then TGT.tongTIEN - TGT.tongTL else TGT.tongTIEN end, " +
			"		DH.LOAIKHACHHANG = KH.xuatkhau	  " +
			"from DONHANG DH inner join KHACHHANG KH on DH.khachhang_fk = KH.pk_seq " +
			"inner join " +
			"( " +
			"	select d.thanhtoanQUY as thanhtoan, a.pk_seq as donhangID,  " +
			" (isnull( (          "+
			"		select sum( round ( ( ( round ( (soluong * giamua), 0  ) - chietkhau ) * ( 1 + thueVAT / 100 ) ), 0 ) )  as tienBvat       "+   
			"		from donhang_sanpham           "+
			"		where isnull(isnhapkhau,1)=1 and donhang_fk = a.pk_seq  and ( soluong * giamua ) != 0 ) ,0) "+
			"		- isnull( ( select sum(tonggiatri) from donhang_ctkm_trakm where donhangId = a.pk_seq and SPMA is null ), 0 )     " +
			"		- isnull( ( select sum( round(thanhtien, 0) )  from DONHANG_CHIETKHAUBOSUNG where donhang_fk = a.pk_seq )	, 0 )  ) as tongTIEN, " +
			"		isnull( ( select sum( round(thanhtoan, 0) )  from DUYETTRAKHUYENMAI_DONHANG where donhang_fk = a.pk_seq and HIENTHI = '1' )	, 0 ) 	as tongTL					         " +
			"	from donhang a inner join khachhang d on a.khachhang_fk = d.pk_seq         " +
			"	where  a.import = '0' and a.trangthai != 2 and a.pk_seq = '" + dhId + "'  " +
			") " +
			"TGT on DH.pk_seq = TGT.donhangID where DH.pk_seq = '" + dhId + "' ";

			db.update(query);

			query = "update DH set " +
			"		DH.tonggiatriNK = case TGT.thanhtoan when 1 then TGT.tongTIEN - TGT.tongTL else TGT.tongTIEN end " +
			"from DONHANG DH inner join KHACHHANG KH on DH.khachhang_fk = KH.pk_seq " +
			"inner join " +
			"( " +
			"	select d.thanhtoanQUY as thanhtoan, a.pk_seq as donhangID,  " +
			" (isnull( (          "+
			"		select sum( round ( ( ( round ( (soluong * giamua), 0  ) - chietkhau ) * ( 1 + thueVAT / 100 ) ), 0 ) )  as tienBvat       "+   
			"		from donhang_sanpham           "+
			"		where  donhang_fk = a.pk_seq  and ( soluong * giamua ) != 0 ) ,0) "+
			"		- isnull( ( select sum(tonggiatri) from donhang_ctkm_trakm where donhangId = a.pk_seq and SPMA is null ), 0 )     " +
			"		- isnull( ( select sum( round(thanhtien, 0) )  from DONHANG_CHIETKHAUBOSUNG where donhang_fk = a.pk_seq )	, 0 )  ) as tongTIEN, " +
			"		isnull( ( select sum( round(thanhtoan, 0) )  from DUYETTRAKHUYENMAI_DONHANG where donhang_fk = a.pk_seq and HIENTHI = '1' )	, 0 ) 	as tongTL					         " +
			"	from donhang a inner join khachhang d on a.khachhang_fk = d.pk_seq         " +
			"	where  a.import = '0' and a.trangthai != 2 and a.pk_seq = '" + dhId + "'  " +
			") " +
			"TGT on DH.pk_seq = TGT.donhangID where DH.pk_seq = '" + dhId + "' ";

			db.update(query);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}



}
