package geso.dms.center.beans.kehoachpg.imp;
import geso.dms.center.beans.kehoachpg.IKehoachPG;
import geso.dms.center.db.sql.*;
import geso.dms.center.util.Utility;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.record.formula.Ptg;

import com.oreilly.servlet.MultipartRequest;

public class KehoachPG implements IKehoachPG
{
	
	
	private static final long serialVersionUID = -9217977546733610214L;
	boolean isdelete;
	String userId =  "";
	String id =  "";
	String ten =  "";
	String tuan = "";
	String nam = "";
	String trangthai =  "";
	String ngaytao =  "";
	String nguoitao =  "";
	String ngaysua =  "";
	String nguoisua =  "";
	String msg =  "";
	String view = "";
	String tungay =  "";
	String denngay =  "";
	
	String nppId;
	
	ResultSet nppList;
	
	dbutils db;
	
	
	

	public KehoachPG(String id,String userId,String view)
	{
		this.db = new dbutils();
		this.id = id;
		this.userId = userId;
		this.view = view;
		if (id.length() > 0)
			this.init();
		else
			this.createRS();
	}
	

	public KehoachPG(String id)
	{
		this.db = new dbutils();
		this.id = id;
		
		
		if (id.length() > 0)
			this.init();
		else
			this.createRS();
	}

	
	
	public dbutils getDb() {
		return db;
	}
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
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

	

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getNppId() 
	{		
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}

	public String getNgaytao()
	{
		return this.ngaytao;
	}

	public void setNgaytao(String ngaytao)
	{
		this.ngaytao = ngaytao;
	}

	public String getNguoitao()
	{
		return this.nguoitao;
	}

	public void setNguoitao(String nguoitao)
	{
		this.nguoitao = nguoitao;
	}

	public String getNgaysua()
	{
		return this.ngaysua;
	}

	public void setNgaysua(String ngaysua)
	{
		this.ngaysua = ngaysua;
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

	

	


	public void setNppList(ResultSet npplist)
	{
		this.nppList = npplist;
	}

	public ResultSet getNppList() 
	{
		return this.nppList;
	}

	public boolean getIsDelete() 
	{
		return this.isdelete;
	}

	public void setIsDelete(boolean isDelete) 
	{
		this.isdelete = isDelete;
	}

	public void createNppList()
	{
		Utility util = new Utility();
		String query =  " select a.pk_seq as nppId, a.ten as nppTen, 'Khu vuc ' + b.ten as kvTen " +
						" from nhaphanphoi a inner join khuvuc b on a.khuvuc_fk = b.pk_seq " +
						" where a.trangthai = '1' and a.sitecode = a.convsitecode " +
						"  		and a.pk_seq in "+util.quyen_npp(this.userId)+" " +
						" order by b.pk_seq asc ";
		
		
		this.nppList = this.db.get(query);
		// and priandsecond='0'
		System.out.println("Danh sach NPP lay duoc: " + query + "\n");
	}

	

	
	ResultSet logRs ;
	
	public void createRS() 
	{
		
		
		createNppList();
		
		thiet_lap_tuan();
		
		
	}
	
	public void thiet_lap_tuan()
	{
		String ngayXuly = "";
		
		if(this.tuan.trim().length() <=0 && this.nam.trim().length() <=0)
		{
			ngayXuly = Utility.getNgayHienTai();
			
			
			
		}
		else
		{
			String query =  "\n DECLARE @TaskWeek INT =   " + this.tuan + 
							"\n DECLARE @TaskYear INT =  " + this.nam +
							"\n SELECT convert(varchar(10), DATEADD(WEEK, @TaskWeek - 1,DATEADD(dd, 1 - DATEPART(dw, '1/1/' + CONVERT(VARCHAR(4),@TaskYear))  " +
							"\n 	, '1/1/' + CONVERT(VARCHAR(4),@TaskYear))),120)  ngay  " ;
			ResultSet rs = db.get(query);
			
			try 
			{
				rs.next();
				ngayXuly = rs.getString("ngay");
				rs.close();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		if(ngayXuly.trim().length() > 0)
		{
			String query =  "\n declare @d  varchar(10) = '"+ ngayXuly +"' " +
			"\n select datepart(week, @d) tuan , year ( @d ) nam " +
			"\n 	, convert(varchar(10),DATEADD(dd, -(DATEPART(dw, @d)-1), @d),120) _start " +
			"\n 	,convert(varchar(10),DATEADD(dd, 7-(DATEPART(dw, @d)), @d),120) _end  " ;
			ResultSet rs = db.get(query);
			
			try 
			{
				rs.next();
				this.tuan = rs.getString("tuan");	
				this.nam  = rs.getString("nam");
				this.tungay = rs.getString("_start");
				this.denngay = rs.getString("_end");
				rs.close();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	}
	
	public String LuuKhachHang(String kehoachId ,String contentType,MultipartRequest multi,HttpServletRequest request)
	{
		try
		{
			String query = "";
			Enumeration files = multi.getFileNames();
			String filenameu = "";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filenameu = multi.getFilesystemName(name);
				System.out.println("name ___:   " + name);
			}
			String filename = "C:\\java-tomcat\\data\\" + filenameu;
			if (filename.length() > 0)
			{
				// doc file excel
				File file = new File(filename);
				System.out.println("filename  " + file);
				Workbook workbook= Workbook.getWorkbook(file);
				Sheet[] sheet1 = workbook.getSheets();
				Hashtable<String, String> hashNgay = getHash_Ngay_Theo_Thu(db, this.tungay, this.denngay);
				for (int t = 0; t < sheet1.length; t++)
				{
					Sheet sheet = sheet1[t];
					//System.out.println("____Name_______ :  " + sheet.getName());
					String nvbhid = sheet.getName().trim();
					
					if(Utility.parseDouble(nvbhid) <=0 )
					{
						return " Sheet ("+nvbhid+")  không hợp lệ vui lòng xóa hoặc sửa lại!";
					}
					
					String sql = 
						" select ddkd.pk_seq,ddkd.ten " +
						" from daidienkinhdoanh ddkd  " + 
						" where ddkd.isPG= 1 and ddkd.pk_seq = " + nvbhid +
						" and ddkd.pk_seq in (select ddkd_fk from daidienkinhdoanh_npp where npp_fk = "+this.nppId+" )  ";
					System.out.println("get du lieu : "+sql);
					ResultSet rs = db.get(sql);
					if(rs.next())
					{ System.out.println("do nothing."); }
					else
					{
						return " ("+nvbhid+") Nhân viên không phải PG ,không thuộc NPP hiện tại hoặc mã hệ thống  của PG không hợp lệ  ở sheet("+nvbhid+")!";
					}
				}
				List<String> khList = new ArrayList<String>();
				for (int t = 0; t < sheet1.length; t++)
				{
					Sheet sheet = sheet1[t];
					String ddkdId = sheet.getName().trim();
					System.out.println("ddkdId : "+ ddkdId);
					Cell[] cells;
					int indexRow = 5;
					for (int i = indexRow;i <sheet.getRows(); i++)
					{
						//System.out.println("sheet.getRows() : "+ sheet.getRows());
						cells = sheet.getRow(i);
						//String inerror = "";
						//String query_insert = "";
						if (cells.length > 0)
						{
							if (cells[0] != null && cells[0].getContents().toString().length() > 0)
							{	
								khList.clear();
								String stt = getValue(sheet,0,i,true);
								System.out.println("stt : "+ stt);
								if(stt.trim().length() > 0 && Utility.parseDouble(stt) > 0 )
								{
									System.out.println("vo day !");
									for(int x = 1; x <=7; x++)
									{
										String ma =  getValue(sheet,x,i,true);
										String ngay = hashNgay.get( String.valueOf(x) );
										
										if(ngay == null)
										{
											return " Chưa lấy được ngày của ngày ("+x+")  ở dòng " + (indexRow + 1) ;
											
										}
										if(ma.trim().length() > 0 && ngay.trim().length() > 0 )
										{
											query = "\n  insert  KeHoachPG_KhachHang_Ngay ( npp_fk,ddkd_fk,khachhang_fk,ngay,thoidiem,kehoachPG_fk,stt,ngayId ) " +
													"\n  select kh.npp_fk,"+ddkdId+", kh.pk_seq, '"+ngay+"', getdate(), "+kehoachId+", "+stt+","+x +
													"\n  from khachhang kh where kh.npp_fk = "+this.nppId+" and  kh.mafast = '"+ma+"' ";
											int sodong =db.updateReturnInt(query);
											System.out.println("sodong = "+ sodong);
											if(sodong <=0 )
											{
												System.out.println("query = "+ query);
												return "  Dòng (" + (indexRow + 1) + ") khách hàng ("+ma+")  ngày ("+x+") không thuộc NPP này/ hoặc đã  bị trùng  " ;
											}
										}
									}
								} // if(stt.trim().length() > 0 && Utility.parseDouble(stt) > 0 )
							}
						}
					}
				}
				
			}
			return "";
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return "Exception :" + e.getMessage();
		}
	}

	public boolean CreateKehoach(String contentType,MultipartRequest multi,HttpServletRequest request) 
	{
		try{
			
			thiet_lap_tuan();
			if(this.tuan.trim().length() <=0)
			{
				this.msg = "Chưa lấy được tuần vui lòng kiểm tra lại";
				return false;
			}
			if(this.nam.trim().length() <=0)
			{
				this.msg = "Chưa lấy được năm vui lòng kiểm tra lại";
				return false;
			}
			if(this.tungay.trim().length() <=0)
			{
				this.msg = "Chưa lấy được từ ngày vui lòng kiểm tra lại";
				return false;
			}
			if(this.denngay.trim().length() <=0)
			{
				this.msg = "Chưa lấy được đến ngày vui lòng kiểm tra lại";
				return false;
			}
			if(this.nppId.trim().length() <=0)
			{
				this.msg = "Chưa lấy được nhà phân phối vui lòng kiểm tra lại";
				return false;
			}
			if(this.ten.trim().length() <=0)
			{
				this.msg = "Nhập diễn giải ";
				return false;
			}
			
			this.db.getConnection().setAutoCommit(false);
			this.ngaytao = getDateTime();
			this.nguoitao = this.userId;

			String query = "\n insert kehoachPG(NPP_FK,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,TRANGTHAI,TUAN,NAM,TUNGAY,DENNGAY,DIENGIAI)" +
						   "\n select "+this.nppId+",getdate(),getdate(),'"+this.userId+"','"+this.userId+"',0,"+this.tuan+","+this.nam+" " +
						   "\n ,'"+this.tungay+"','"+this.denngay+"',N'"+this.ten+"' ";
			
			if(db.updateReturnInt(query) <=0)
			{
				this.msg = "Kế hoạch NPP đã bị trùng vui lòng thử lại!";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
				
			}
			ResultSet rs = db.get(" select scope_identity() id " ); 
			rs.next();
			String kehoachId = rs.getString("id"); 
			if(kehoachId == null || kehoachId.trim().length() <=0)
			{
				this.msg = "Tài khoản đã hết hạn, vui lòng đăng nhập lại";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}
			
			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			{
				String kq= LuuKhachHang( kehoachId , contentType, multi, request);
				if(kq.trim().length() > 0)
				{
					this.msg = kq;
					this.db.getConnection().rollback();
					this.db.getConnection().setAutoCommit(true);
					return false;
				}
				
			}
			
			
			
			
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}
		catch(Exception e){
			geso.dms.center.util.Utility.rollback_throw_exception(this.db);
			this.msg = "Lỗi ngoại lệ: "+e.getMessage();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	public boolean UpdateKehoach(String contentType,MultipartRequest multi,HttpServletRequest request) 
	{
		try{
			thiet_lap_tuan();
			if(this.tuan.trim().length() <=0)
			{
				this.msg = "Chưa lấy được tuần vui lòng kiểm tra lại";
				return false;
			}
			if(this.nam.trim().length() <=0)
			{
				this.msg = "Chưa lấy được năm vui lòng kiểm tra lại";
				return false;
			}
			if(this.tungay.trim().length() <=0)
			{
				this.msg = "Chưa lấy được từ ngày vui lòng kiểm tra lại";
				return false;
			}
			if(this.denngay.trim().length() <=0)
			{
				this.msg = "Chưa lấy được đến ngày vui lòng kiểm tra lại";
				return false;
			}
			if(this.nppId.trim().length() <=0)
			{
				this.msg = "Chưa lấy được nhà phân phối vui lòng kiểm tra lại";
				return false;
			}
			if(this.ten.trim().length() <=0)
			{
				this.msg = "Nhập diễn giải ";
				return false;
			}
			this.db.getConnection().setAutoCommit(false);
			this.ngaytao = getDateTime();
			this.nguoitao = this.userId;

			String query = "\n update  kehoachPG set NGAYSUA = getdate() ,NGUOISUA =  '"+this.userId+"' ,TUAN = "+this.tuan+"" +
							"\n ,NAM = "+this.nam+" ,TUNGAY = '"+this.tungay+"' ,DENNGAY = '"+this.denngay+"' ,DIENGIAI = N'"+this.ten+"' " +
						   "\n where trangthai = 0 and pk_seq =  " + this.id;
			
			if(db.updateReturnInt(query) <=0)
			{
				this.msg = "Kế hoạch NPP đã bị trùng hoặc trạng thái không hợp lệ, vui lòng thử lại! " + query;
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
				
			}
			query = " delete KeHoachPG_KhachHang_Ngay where KeHoachPG_FK =   "+ this.id; 
			if(db.updateReturnInt(query) < 0)
			{
				this.msg = "Lỗi xóa kế hoạch chi tiết!";
				this.db.getConnection().rollback();
				this.db.getConnection().setAutoCommit(true);
				return false;
			}
			
			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			{
				System.out.println("vo Multi.");
				String kq= LuuKhachHang( this.id , contentType, multi, request);
				if(kq.trim().length() > 0)
				{
					this.msg = kq;
					this.db.getConnection().rollback();
					this.db.getConnection().setAutoCommit(true);
					return false;
				}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		}
		catch(Exception e){
			geso.dms.center.util.Utility.rollback_throw_exception(this.db);
			this.msg = "Exception: "+e.getMessage();
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public void init() 
	{
		String query =  "\n select a.PK_SEQ ,a.DIENGIAI,a.TRANGTHAI,a.NPP_FK,a.TUAN,a.NAM,a.TUNGAY,a.DENNGAY " +
						"\n from kehoachPG a "+
						"\n where a.pk_seq = "+this.id;
		System.out.println("Init: "+query);
		ResultSet rs =  this.db.get(query);
		try
		{
			rs.next();        	
			this.ten = rs.getString("DIENGIAI");		
			this.trangthai = rs.getString("TRANGTHAI");
			this.nppId = rs.getString("NPP_FK")==null?"": rs.getString("NPP_FK");
			this.tuan = rs.getString("TUAN");
			this.nam = rs.getString("NAM");
			this.tungay = rs.getString("TUNGAY");
			this.denngay = rs.getString("DENNGAY");
			
		}
		catch(Exception e){e.printStackTrace();}
		createRS(); 
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	public void DBClose() {
		try{

			if (nppList!=null){
				nppList.close();
			}

			
			
			if (db!=null){
				db.shutDown();
			}
		}catch(Exception er){
			er.printStackTrace();
		}
	}

	public String getTuan() {
		return tuan;
	}
	public void setTuan(String tuan) {
		this.tuan = tuan;
	}
	public String getNam() {
		return nam;
	}
	public void setNam(String nam) {
		this.nam = nam;
	}
	public String getTungay() {
		return tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	
	public static  Hashtable<String, String> getHash_Ngay_Theo_Thu(Idbutils db,String tungay,String denngay)
	{
		Hashtable<String, String> hTanSo = new Hashtable<String, String>();
		String sql = "  select ngay,DATEPART(dw,ngay) thu from [dbo].[uf_CacNgayTrongKhoangThoiGian](  '"+tungay+"','"+denngay+"') ";
		System.out.println("getHash_Ngay_Theo_Thu = "+ sql);
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				System.out.println("thu = "+ rs.getString("thu") + " , ngay = "+  rs.getString("ngay")  );
				hTanSo.put(rs.getString("thu").trim(), rs.getString("ngay").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return hTanSo;
	}
	
	String getValue(Sheet sheet,int col,int row,boolean replaceDauPhay)
	{
		try{
			if(replaceDauPhay)
				return sheet.getCell(col,row).getContents().trim().replaceAll("'","").replaceAll(",", "").trim();
			else 
				return sheet.getCell(col,row).getContents().trim().trim();
		}catch (Exception e) {
			System.out.println(" sheet " + sheet.getName() + ", col = "+ col +", row = "+ row ); 
			e.printStackTrace();
			return "";
		}
	}

	
}
	