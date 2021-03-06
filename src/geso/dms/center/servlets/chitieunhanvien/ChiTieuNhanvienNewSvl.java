package geso.dms.center.servlets.chitieunhanvien;

import geso.dms.center.beans.chitieunhanvien.IChiTieuNhanvien;
import geso.dms.center.beans.chitieunhanvien.imp.ChiTieuNhanvien;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;
import com.oreilly.servlet.MultipartRequest;

public class ChiTieuNhanvienNewSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ChiTieuNhanvienNewSvl()
	{
		super();
	}

	private String gettenuser(String userId_)
	{
		dbutils db = new dbutils();
		String sql_getnam = "select ten from nhanvien where pk_seq=" + userId_;
		ResultSet rs_tenuser = db.get(sql_getnam);
		if (rs_tenuser != null)
		{
			try
			{
				while (rs_tenuser.next())
				{
					return rs_tenuser.getString("ten");
				}
			} catch (Exception er)
			{
				return "";
			}
		}
		return "";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String querystring = request.getQueryString();
		Utility util = new Utility();
		String userId = util.getUserId(querystring);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("userId")));
		String id = util.getId(querystring);

		
		String	loai = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("loai")));
		if(loai == null) loai = "";
		System.out.println("loai tu jsp "+loai);
		
		IChiTieuNhanvien obj = new ChiTieuNhanvien(id,loai,userId);
		
		String action = util.getAction(querystring);
		if (action.equals("display"))
			obj.setIsDisplay("1");

		obj.setUserId(userId);

		session.setAttribute("userId", userId);
		String tenuser = gettenuser(userId);
		session.setAttribute("userTen", tenuser);
		session.setAttribute("obj", obj);
		session.setAttribute("check", "0");
		String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();

		Utility util = new Utility();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			String loainv = util.antiSQLInspection(multi.getParameter("loai"));
			System.out.println("loai nv a "+loainv);

			try 
			{
				xulySR(request,response,util,session,contentType,multi);
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			IChiTieuNhanvien chitieu = new ChiTieuNhanvien();

			String id = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("id")));
			if(id == null ) id ="";
			chitieu.setID(id);

			String isDisplay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("isDisplay")));
			if(isDisplay == null ) isDisplay ="1";
			chitieu.setIsDisplay(isDisplay);

			String loaiUpload = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("loaiUpload")));
			chitieu.setLoaiUpload(loaiUpload);
			
			String vungId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("vungId")));
			chitieu.setVung(vungId==null?"":vungId);
			
			String khuvucId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("khuvucId")));
			chitieu.setKhuVuc(khuvucId==null?"":khuvucId);
			
			try
			{
				int thang = Integer.parseInt(util.antiSQLInspection(util.antiSQLInspection(request.getParameter("thang"))));
				chitieu.setThang(thang);
			} catch (Exception er)
			{

			}
			try
			{
				int nam = Integer.parseInt(util.antiSQLInspection(util.antiSQLInspection(request.getParameter("nam"))));
				chitieu.setNam(nam);
			} catch (Exception er)
			{

			}


			String userId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("userId")));
			chitieu.setUserId(userId);

			String loainv = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("loai")));
			chitieu.setLoai(loainv);
			System.out.println("loainv:  " + loainv);
			String diengiai = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("diengiai")));
			chitieu.setDienGiai(diengiai);
			chitieu.setNguoiSua(userId);
			chitieu.setNguoiTao(userId);
			chitieu.setNgayTao(this.getDateTime());
			chitieu.setNgaySua(this.getDateTime());
			chitieu.initBCNhanVien();

			String action = util.antiSQLInspection(request.getParameter("action"));
			System.out.println("action la "+action);
			if(action == null) action ="";
			if (action.equals("excel"))
			{
				System.out.println("vao day ne execelll loaii="+ chitieu.getLoai());
				
				try
				{
					String query ="";
					response.setContentType("application/xls");
					response.setHeader("Content-Disposition", "attachment; filename=Chitieu_Template.xls");
					OutputStream out = response.getOutputStream();

						ExportToExcel(out,chitieu);

				} 
				catch (Exception ex)
				{
					ex.printStackTrace();					
					chitieu.setMessage(ex.getMessage());
				}
			}

			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default
			session.setAttribute("obj", chitieu);
			response.sendRedirect(nextJSP);
			System.out.println(chitieu.getMessage());
		}
	}


	public void xulySR(HttpServletRequest request, HttpServletResponse response,Utility util,HttpSession session,String contentType,MultipartRequest multi ) throws ServletException, IOException, SQLException
	{

		IChiTieuNhanvien chitieu = new ChiTieuNhanvien();

		try
		{
			int thang = Integer.parseInt(util.antiSQLInspection(multi.getParameter("thang")));
			chitieu.setThang(thang);
		} catch (Exception er)
		{

		}
		try
		{
			int nam = Integer.parseInt(util.antiSQLInspection(multi.getParameter("nam")));
			chitieu.setNam(nam);
		} catch (Exception er)
		{

		}
		String id = util.antiSQLInspection(multi.getParameter("id"));
		if(id == null ) id ="";
		chitieu.setID(id);


		String userId = util.antiSQLInspection(multi.getParameter("userId"));
		chitieu.setUserId(userId);

		String loainv = util.antiSQLInspection(multi.getParameter("loai"));
		chitieu.setLoai(loainv);
		System.out.println("loainv:  " + loainv);
		String loaiUpload = util.antiSQLInspection(multi.getParameter("loaiUpload"));
		chitieu.setLoaiUpload(loaiUpload);

		String diengiai = util.antiSQLInspection(multi.getParameter("diengiai"));
		chitieu.setDienGiai(diengiai);
		chitieu.setNguoiSua(userId);
		chitieu.setNguoiTao(userId);
		chitieu.setNgayTao(this.getDateTime());
		chitieu.setNgaySua(this.getDateTime());

		Enumeration files = multi.getFileNames();
		String filenameu = "";
		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			filenameu = multi.getFilesystemName(name);
			System.out.println("name  " + name);
			;
		}

		String filename = "C:\\java-tomcat\\data\\" + filenameu;

		if(filename.indexOf("xlsx")>=0)
		{

		}

		if (filename.length() > 0)
		{
			// doc file excel
			File file = new File(filename);
			System.out.println("filename  " + file);
			Workbook workbook;
			ResultSet rs = null;
			dbutils db =new dbutils();
			
			
			String loaiSQL = "1";
			
			String query ="select count(*)so  " +
			"\n from TieuChiThuong_ChiTiet a " +
			"\n inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.pk_Seq " +
			"\n inner join TieuChi c on c.TieuChi = a.TieuChi and c.LOAI = b.LOAI and c.LoaiUpload =  " + chitieu.getLoaiUpload() + " "+ 
			"\n where b.loai = "+loaiSQL+" and  b.trangthai = 1 " +
			"\n and b.thang ="+chitieu.getThang()+" and b.nam = "+ chitieu.getNam();
			rs = db.get(query);
			int soKpi = 0;
			if(rs != null )
			{
				if(rs.next())
					soKpi = rs.getInt("so");
			}
			if(soKpi <=0)
			{
				System.out.println("query check:" + query);
				chitieu.setMessage("Ch??a thi???t l???p KPI cho th??ng");
				String nextJSP ="";
				nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default						
				
				session.setAttribute("obj", chitieu);
				response.sendRedirect(nextJSP);
				return;
			}
			db.shutDown();


			int indexRow = 8;

			int socotfixcung = 3;
			
			
			int socot = soKpi + socotfixcung;
			String[] maKpiArr=new String[soKpi];
			if(!loainv.equals("1"))// c???p qu???n l?? c?? th??m c???t sum t??? c???p d?????i
			{
				socot = 2 * soKpi + socotfixcung;
				maKpiArr=new String[2 * soKpi];
			}
			try
			{

				System.out.println(file);
				workbook = Workbook.getWorkbook(file);
				Sheet sheet = workbook.getSheet(0);
				Cell[] cells = sheet.getRow(indexRow);
				cells = sheet.getRow(7);
				boolean loi = false;
				String values = "";
				String valuesDetail = "";

				
				
				for ( int i = socotfixcung ; i < socot; i ++)
				{
					String makpi = getStringValue(cells,i);
					
					try {
						System.out.println("MAKPI "+makpi);
						double so = Double.parseDouble(makpi.replace("_","" )); 
					
					}
					catch(Exception e)
					{
						e.printStackTrace();
						loi = true;
						indexRow = Integer.MAX_VALUE;
						chitieu.setMessage("L???i d??ng excel th??? 8,(M?? nh??m KPI) ");
					}
					maKpiArr[i-socotfixcung] = makpi;
				}

				for (int i = indexRow ; i < sheet.getRows(); i++)
				{
					System.out.println("Vao Day: " + i);
					cells = sheet.getRow(i);
					if(cells.length > 0 && !loi)
					{

						String manv = getStringValue(cells,0);
						if(manv.trim().length() >0)
							for(int z= 0; z < maKpiArr.length; z ++)
							{
								String maKpi = maKpiArr[z];
								if(!maKpi.contains("_")) // kh??ng l???y c??c m?? SUM t??? c???p d?????i
								{
									double sotien = getDoubleValue(cells,z + socotfixcung);
									values += "\n select "+manv+" as manv, "+maKpi+" as tctct_fk, "+sotien+" as [chitieu] union all ";
								}
							}
					}

				}
				if (values.length() > 0 && !loi)
				{
					int cut = values.lastIndexOf("]")+1;
					values = values.substring(0, cut );
				}
				else
				{
					loi = true;
					if(chitieu.getMessage().trim().length() <=0)
						chitieu.setMessage("Chua co file up load");
				}
				if(loi)
				{
					System.out.println("Khong Thanh Cong truoc khi luu");
					String nextJSP ="";
					nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default						
					// Xoa het chi tieu cu di
					session.setAttribute("obj", chitieu);
					response.sendRedirect(nextJSP);
					System.out.println(chitieu.getMessage());
				}
				else
					if ( 
							( id == null || id.trim().length() <=0) ?
									chitieu.CreateChiTieuLuongThuong(values,valuesDetail)
									:chitieu.UpdateChiTieuLuongThuong(values,valuesDetail)
					)
					{
						// Thanh cong
						session.setAttribute("nam", 0);
						session.setAttribute("thang", 0);
						chitieu.setListChiTieu("");
						session.setAttribute("obj", chitieu);
						String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvien.jsp";
						response.sendRedirect(nextJSP);

						System.out.println(chitieu.getMessage());
					} 
					else
					{
						if(( id == null || id.trim().length() <=0))
							chitieu.setID("");

						System.out.println("Khong Thanh Cong");
						String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default
						session.setAttribute("obj", chitieu);
						response.sendRedirect(nextJSP);

						System.out.println(chitieu.getMessage());

					}

			} catch (Exception er)
			{
				er.printStackTrace();
				chitieu.setMessage("Thong bao loi : " + er.toString());
				System.out.println(er.toString());

			}

		}


	}

	public void xulyMien(HttpServletRequest request, HttpServletResponse response,Utility util,HttpSession session,String contentType,MultipartRequest multi ) throws ServletException, IOException, SQLException
	{

		IChiTieuNhanvien chitieu = new ChiTieuNhanvien();

		try
		{
			int thang = Integer.parseInt(util.antiSQLInspection(multi.getParameter("thang")));
			chitieu.setThang(thang);
		} catch (Exception er)
		{

		}
		try
		{
			int nam = Integer.parseInt(util.antiSQLInspection(multi.getParameter("nam")));
			chitieu.setNam(nam);
		} catch (Exception er)
		{

		}
		String id = util.antiSQLInspection(multi.getParameter("id"));
		if(id == null ) id ="";
		chitieu.setID(id);


		String userId = util.antiSQLInspection(multi.getParameter("userId"));
		chitieu.setUserId(userId);

		String loainv = util.antiSQLInspection(multi.getParameter("loai"));
		chitieu.setLoai(loainv);
		System.out.println("loainv:  " + loainv);
		String loaiUpload = util.antiSQLInspection(multi.getParameter("loaiUpload"));
		chitieu.setLoaiUpload(loaiUpload);

		String diengiai = util.antiSQLInspection(multi.getParameter("diengiai"));
		chitieu.setDienGiai(diengiai);
		chitieu.setNguoiSua(userId);
		chitieu.setNguoiTao(userId);
		chitieu.setNgayTao(this.getDateTime());
		chitieu.setNgaySua(this.getDateTime());

		Enumeration files = multi.getFileNames();
		String filenameu = "";
		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			filenameu = multi.getFilesystemName(name);
			System.out.println("name  " + name);
			;
		}

		String filename = "C:\\java-tomcat\\data\\" + filenameu;

		if(filename.indexOf("xlsx")>=0)
		{

		}

		if (filename.length() > 0)
		{
			// doc file excel
			File file = new File(filename);
			System.out.println("filename  " + file);
			Workbook workbook;
			ResultSet rs = null;
			dbutils db =new dbutils();
			String query ="select count(*)so  " +
			"\n from TieuChiThuong_ChiTiet a " +
			"\n inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.pk_Seq " +
			"\n inner join TieuChi c on c.TieuChi = a.TieuChi   " +
			"\n where  b.trangthai = 1 " +
			"\n and b.thang ="+chitieu.getThang()+" and b.nam = "+ chitieu.getNam();
			rs = db.get(query);
			int soKpi = 0;
			if(rs != null )
			{
				if(rs.next())
					soKpi = rs.getInt("so");
			}
			if(soKpi <=0)
			{
				System.out.println("query check l:" + query);
				chitieu.setMessage("Ch??a thi???t l???p KPI cho th??ng");
				String nextJSP ="";
				nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default						
				// Xoa het chi tieu cu di
				session.setAttribute("obj", chitieu);
				response.sendRedirect(nextJSP);
				return;
			}
			db.shutDown();


			int indexRow = 8;

			int socotfixcung = 2;
			if(loainv.equals("1"))
				socotfixcung = 6;
			int socot = soKpi + socotfixcung;
			try
			{

				System.out.println(file);
				workbook = Workbook.getWorkbook(file);
				Sheet sheet = workbook.getSheet(0);
				Cell[] cells = sheet.getRow(indexRow);
				cells = sheet.getRow(7);
				boolean loi = false;
				String values = "";
				String valuesDetail = "";

				String[] maKpiArr=new String[soKpi];
				
				for ( int i = socotfixcung ; i < socot; i ++)
				{
					String makpi = getStringValue(cells,i);
					try { double so = Double.parseDouble(makpi); }
					catch(Exception e)
					{
						loi = true;
						indexRow = Integer.MAX_VALUE;
						chitieu.setMessage("loi dong excel thu 8,(M?? nhom KPI) ");
					}
					maKpiArr[i-socotfixcung] = makpi;
				}

				for (int i = indexRow ; i < sheet.getRows(); i++)
				{
					System.out.println("Vao Day: " + i);
					cells = sheet.getRow(i);
					if(cells.length > 0 && !loi)
					{

						String manv = getStringValue(cells,0);
						if(manv.trim().length() >0)
							for(int z= 0; z < maKpiArr.length; z ++)
							{
								String maKpi = maKpiArr[z];
								double sotien = getDoubleValue(cells,z + socotfixcung);
								values += "\n select "+manv+" as manv, "+maKpi+" as tctct_fk, "+sotien+" as [chitieu] union all ";
							}
					}

				}
				if (values.length() > 0 && !loi)
				{
					int cut = values.lastIndexOf("]")+1;
					values = values.substring(0, cut );
				}
				else
				{
					loi = true;
					if(chitieu.getMessage().trim().length() <=0)
						chitieu.setMessage("Chua co file up load");
				}
				if(loi)
				{
					System.out.println("Khong Thanh Cong truoc khi luu");
					String nextJSP ="";
					nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default						
					// Xoa het chi tieu cu di
					session.setAttribute("obj", chitieu);
					response.sendRedirect(nextJSP);
					System.out.println(chitieu.getMessage());
				}
				else
					if ( 
							( id == null || id.trim().length() <=0) ?
									chitieu.CreateChiTieuLuongThuong(values,valuesDetail)
									:chitieu.UpdateChiTieuLuongThuong(values,valuesDetail)
					)
					{
						// Thanh cong
						session.setAttribute("nam", 0);
						session.setAttribute("thang", 0);
						chitieu.setListChiTieu("");
						session.setAttribute("obj", chitieu);
						String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvien.jsp";
						response.sendRedirect(nextJSP);

						System.out.println(chitieu.getMessage());
					} 
					else
					{
						if(( id == null || id.trim().length() <=0))
							chitieu.setID("");

						System.out.println("Khong Thanh Cong");
						String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default
						session.setAttribute("obj", chitieu);
						response.sendRedirect(nextJSP);

						System.out.println(chitieu.getMessage());

					}

			} catch (Exception er)
			{
				er.printStackTrace();
				chitieu.setMessage("Thong bao loi : " + er.toString());
				System.out.println(er.toString());

			}

		}


	}
	public void XulySR_DoPhuMatHang(HttpServletRequest request, HttpServletResponse response,Utility util,HttpSession session,String contentType,MultipartRequest multi ) throws ServletException, IOException, SQLException
	{
		IChiTieuNhanvien chitieu = new ChiTieuNhanvien();

		try
		{
			int thang = Integer.parseInt(util.antiSQLInspection(multi.getParameter("thang")));
			chitieu.setThang(thang);
		} catch (Exception er)
		{

		}
		try
		{
			int nam = Integer.parseInt(util.antiSQLInspection(multi.getParameter("nam")));
			chitieu.setNam(nam);
		} catch (Exception er)
		{

		}
		String id = util.antiSQLInspection(multi.getParameter("id"));
		if(id == null ) id ="";
		chitieu.setID(id);


		String userId = util.antiSQLInspection(multi.getParameter("userId"));
		chitieu.setUserId(userId);

		String loainv = util.antiSQLInspection(multi.getParameter("loai"));
		chitieu.setLoai(loainv);

		String loaiUpload = util.antiSQLInspection(multi.getParameter("loaiUpload"));
		chitieu.setLoaiUpload(loaiUpload);

		String diengiai = util.antiSQLInspection(multi.getParameter("diengiai"));
		chitieu.setDienGiai(diengiai);
		chitieu.setNguoiSua(userId);
		chitieu.setNguoiTao(userId);
		chitieu.setNgayTao(this.getDateTime());
		chitieu.setNgaySua(this.getDateTime());
		Enumeration files = multi.getFileNames();
		String filenameu = "";
		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			filenameu = multi.getFilesystemName(name);
			System.out.println("name  " + name);
			;
		}
		String filename = "C:\\java-tomcat\\data\\" + filenameu;
		if (filename.length() > 0)
		{

			// doc file excel
			File file = new File(filename);
			System.out.println("filename  " + file);
			Workbook workbook;
			dbutils db = new dbutils();
			try
			{


				ResultSet rs;
				System.out.println(file);
				workbook = Workbook.getWorkbook(file);
				Sheet[] sheet1 = workbook.getSheets();

				System.out.println("[SoSheet]"+sheet1.length);

				boolean error = false;
				for (int t = 0; t < sheet1.length; t++)
				{
					if(!error)
					{
						Sheet sheet = sheet1[t];
						System.out.println("____Name_______ :  " + sheet.getName());
						String tctctId = sheet.getName().trim();
						String sql = 
							"\n select count(*)sodong from TIEUCHITHUONG_CHITIET a " +
							"\n inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_Fk = b.pk_seq " +
							"\n				and  b.trangthai = 1 and b.thang ="+chitieu.getThang()+" and b.nam ="+chitieu.getNam()+" " +
							"\n inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + chitieu.getLoaiUpload() +
							"\n where  b.loai = "+loainv+" and  a.pk_seq ="+tctctId;
						System.out.println("get du lieu : "+sql);
						rs = db.get(sql);
						int sodong = 0;
						if (rs.next())
						{
							sodong = rs.getInt("sodong");
						} 
						if(rs!=null)rs.close();
						if(sodong <=0)
						{
							chitieu.setMessage("Kh??ng t???n t???i lo???i ch??? ti??u  m??("+sheet.getName()+") trong th??ng  "+ chitieu.getThang()+" n??m " +  chitieu.getNam());
							error = true;
						}

					}
				}
				if(error)
				{
					String nextJSP  = request.getContextPath() + "/pages/Center/ChiTieuNhanVienUpdate.jsp";// default						
					// Xoa het chi tieu cu di
					db.shutDown();
					session.setAttribute("obj", chitieu);
					response.sendRedirect(nextJSP);
					return;	
				}

				Hashtable<String,String> htpSanPham = getHtpSanPham(db,chitieu);
				String values ="";
				for (int t = 0; t < sheet1.length; t++)
				{
					Sheet sheet = sheet1[t];
					String tctctId = sheet.getName().trim();
					String query ="select count(*)so  from NHOMSANPHAMCHITIEU_SANPHAM where nsp_fk = (select nhomsp_fk from tieuchithuong_chitiet where pk_Seq ="+tctctId+")";
					rs = db.get(query);
					int soSp = 0;
					if(rs != null )
					{
						if(rs.next())
							soSp = rs.getInt("so");
					}
					int indexRow = 8;
					int socotfixcung = 2;
					int socot = soSp + socotfixcung;
					
					Cell[] cells = sheet.getRow(indexRow);
					cells = sheet.getRow(7);
					
					String[] maSpArr=new String[soSp];
					
					for ( int i = socotfixcung ; i < socot; i ++)
					{
						String sanpham_fkTemp = getStringValue(cells,i);
						
						String sanpham_fk = htpSanPham.get(tctctId + "-" + sanpham_fkTemp);
						if(sanpham_fk == null)
						{
							String nextJSP  = request.getContextPath() + "/pages/Center/ChiTieuNhanVienUpdate.jsp";// default						
							db.shutDown();
							chitieu.setMessage("S???n ph???m c?? m?? upload("+sanpham_fkTemp+") kh??ng n???m trong nh??m s???n ph???m c???a lo???i ch??? ti??u ("+tctctId+") ");
							// Xoa het chi tieu cu di
							session.setAttribute("obj", chitieu);
							response.sendRedirect(nextJSP);
							return;	
						}
						maSpArr[i-socotfixcung] = sanpham_fk;
					}
					for (int i = indexRow ; i < sheet.getRows(); i++)
					{
						cells = sheet.getRow(i);
						if(cells.length > 0 )
						{

							String manv = getStringValue(cells,0);
							if(manv.trim().length() >0)
								for(int z= 0; z < maSpArr.length; z ++)
								{
									String maSp = maSpArr[z];
									double sotien = getDoubleValue(cells,z + socotfixcung);
									values += "\n select "+manv+" as manv, "+tctctId+" as tctct_fk, "+maSp+" sanpham_fk , "+sotien+" as [chitieu] union all ";
								}
						}

					}
					
				}

				if (values.length() > 0 )
				{
					int cut = values.lastIndexOf("]")+1;
					values = values.substring(0, cut );
				}
				else
				{
					String nextJSP  = request.getContextPath() + "/pages/Center/ChiTieuNhanVienUpdate.jsp";// default						
					db.shutDown();
					chitieu.setMessage("Kh??ng c?? d??? li???u ????? upload");
					// Xoa het chi tieu cu di
					session.setAttribute("obj", chitieu);
					response.sendRedirect(nextJSP);
					return;	
				}

				if ( 
						( id == null || id.trim().length() <=0) ?
								chitieu.CreateChiTieuLuongThuong(values,"")
								:chitieu.UpdateChiTieuLuongThuong(values,"")
				)
				{
					db.shutDown();
					// Thanh cong
					session.setAttribute("nam", 0);
					session.setAttribute("thang", 0);
					chitieu.setListChiTieu("");
					session.setAttribute("obj", chitieu);
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvien.jsp";
					response.sendRedirect(nextJSP);

					System.out.println(chitieu.getMessage());
				} 
				else
				{
					db.shutDown();
					if(( id == null || id.trim().length() <=0))
						chitieu.setID("");

					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default
					session.setAttribute("obj", chitieu);
					response.sendRedirect(nextJSP);
				}
			} 
			catch (Exception er)
			{
				db.shutDown();
				chitieu.setMessage("L???i ngo???i l??? ("+er.getMessage()+")");
				String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";// default
				session.setAttribute("obj", chitieu);
				response.sendRedirect(nextJSP);
			}
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		return dateFormat.format(date);
	}



	public String getStringValue(Cell[] cells,int vitri)
	{
		try
		{
			return cells[vitri].getContents().toString().replace("\t", "").replace(",", "").replace(" ", "").trim();
		}
		catch (Exception e) {
			return "";
		}
	}

	public double getDoubleValue(Cell[] cells,int vitri)
	{
		try
		{
			return Double.parseDouble(cells[vitri].getContents().toString().replace("\t", "").replace(",", "").replace(" ", "").trim());
		}
		catch (Exception e) {
			return 0.0;
		}
	}
	
	public String setQueryQuanLy(IChiTieuNhanvien obj)
	{
		String ma = "mafast";
		String  chuoi = "";
		String chuoi2="";
		if(obj.getLoai().equals("2"))
		{
			chuoi = "Giamsatbanhang";
			chuoi2 ="CHITIEUNHANVIEN_GSBH";
		}
		else
			if(obj.getLoai().equals("3"))
			{
				chuoi = "ASM";
				chuoi2 ="CHITIEUNHANVIEN_ASM";
			}
		String id = "0";
		if(obj.getID() != null & obj.getID().trim().length() > 0)
			id = obj.getID();
		
		String query =	 	"\n declare @col nvarchar(max),@col2 nvarchar(max),@colTotal nvarchar(max),@sql nvarchar(max)  " + 
		"\n set @col =  STUFF((SELECT ',' + QuoteName(a.PK_SEQ)   " + 
		"\n                             from TIEUCHITHUONG_CHITIET a   " + 
		"\n                             inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " +
		"\n 							inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() + 
		"\n                             where  b.TRANGTHAI = 1 and b.THANG = "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI =1  " + 
		"\n                             order by a.TIEUCHI,isnull(a.NHOMSP_FK,0)  " + 
		"\n                             FOR XML PATH('')),1,1,'')   " + 
		"\n set @col2 =  STUFF((SELECT ',' +  QuoteName( '_'  + cast( a.PK_SEQ as varchar) )    " + 
		"\n                             from TIEUCHITHUONG_CHITIET a   " + 
		"\n                             inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " +
		"\n 							inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() + 
		"\n                             where  b.TRANGTHAI = 1 and b.THANG = "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI =1  " + 
		"\n                             order by a.TIEUCHI,isnull(a.NHOMSP_FK,0)  " + 
		"\n                             FOR XML PATH('')),1,1,'')   " + 
		
		"\n set @colTotal =  STUFF((SELECT  ',' + QuoteName(a.PK_SEQ) + ',' + QuoteName( '_'  + cast( a.PK_SEQ as varchar) )      " + 
		"\n                             from TIEUCHITHUONG_CHITIET a   " + 
		"\n                             inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " +
		"\n 							inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() + 
		"\n                             where  b.TRANGTHAI = 1 and b.THANG = "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI =1  " + 
		"\n                             order by a.TIEUCHI,isnull(a.NHOMSP_FK,0)  " + 
		"\n                             FOR XML PATH('')),1,1,'')   " + 
		
		"\n set @sql=  " + 
		"\n 	N' select ddkd.pk_seq [M?? h??? th???ng], ddkd." + ma + " [M??], ddkd.TEN,'+@colTotal+'  " + 
		"\n 	from "+chuoi+"  ddkd  " + 
		"\n 	outer apply " +
		"\n		(  " + 
		"\n 		select * from  " + 
		"\n 		(  " + 
		"\n 			select a.PK_SEQ as thuongctId,(SELECT sum(CHITIEUUpload) FROM "+chuoi2+" where nhanvien_fk =ddkd.pk_seq and ctnv_fk ="+id+" and tctct_fk = a.pk_seq )  as data  " + 
		"\n 			from TIEUCHITHUONG_CHITIET a   " + 
		"\n 			inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " + 
		"\n 			inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() +
		"\n 			where  b.TRANGTHAI = 1 and b.THANG =  "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI = 1  " + 
		"\n 		) dt PIVOT ( max(data) FOR thuongctId IN ('+@col+')) AS pvt  " + 
		"\n 	)data " +
		"\n 	outer apply " +
		"\n		(  " + 
		"\n 		select * from  " + 
		"\n 		(  " + 
		"\n 			select ''_''  + cast( a.PK_SEQ as varchar) as thuongctId,(SELECT sum(CHITIEU) FROM "+chuoi2+" where nhanvien_fk =ddkd.pk_seq and ctnv_fk ="+id+" and tctct_fk = a.pk_seq )  as data  " + 
		"\n 			from TIEUCHITHUONG_CHITIET a   " + 
		"\n 			inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " + 
		"\n 			inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() +
		"\n 			where  b.TRANGTHAI = 1 and b.THANG =  "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI = 1 " + 
		"\n 		) dt PIVOT ( max(data) FOR thuongctId IN ('+@col2+')) AS pvt  " + 
		"\n 	)data2 where 1=1 ' " +
		"\n execute (@sql) " ;

		System.out.println("query BC="+ query);
		return query;
	}
	
	public String setQuery (IChiTieuNhanvien obj )
	{
	
		if(!obj.getLoai().equals("1"))
			return setQueryQuanLy( obj);
		
		String chuoi = "DAIDIENKINHDOANH";
		String ma = "mafast";
		if(obj.getLoai().equals("2"))
			chuoi = "Giamsatbanhang";
		else
			if(obj.getLoai().equals("3"))
				chuoi = "ASM";
			else
				if(obj.getLoai().equals("4"))
					chuoi = "BM";
				else
					if(obj.getLoai().equals("5"))
						chuoi = "Nhaphanphoi";
				
		String id = "0";
		if(obj.getID() != null & obj.getID().trim().length() > 0)
			id = obj.getID();
			
		
	
		String query =	 	"\n declare @col nvarchar(max),@sql nvarchar(max)  " + 
							"\n set @col =  STUFF((SELECT ',' + QuoteName(a.PK_SEQ)   " + 
							"\n                             from TIEUCHITHUONG_CHITIET a   " + 
							"\n                             inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " +
							"\n 							inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() + 
							"\n                             where  b.TRANGTHAI = 1 and b.THANG = "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI ="+obj.getLoai()+"   " + 
							"\n                             order by a.TIEUCHI,isnull(a.NHOMSP_FK,0)  " + 
							"\n                             FOR XML PATH('')),1,1,'')   " + 
							"\n set @sql=  " + 
							"\n 	N' select ddkd.pk_seq [M?? h??? th???ng], ddkd." + ma + " [M??], ddkd.TEN,'+@col+'  " + 
							"\n 	from "+chuoi+"  ddkd  " + 
							"\n 	outer apply " +
							"\n		(  " + 
							"\n 		select * from  " + 
							"\n 		(  " + 
							"\n 			select a.PK_SEQ as thuongctId,(SELECT sum(CHITIEU) FROM CHITIEUNHANVIEN_DDKD where nhanvien_fk =ddkd.pk_seq and ctnv_fk ="+id+" and tctct_fk = a.pk_seq )  as data  " + 
							"\n 			from TIEUCHITHUONG_CHITIET a   " + 
							"\n 			inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " + 
							"\n 			inner join TieuChi c on c.TieuChi = a.TieuChi   and c.LOAI = b.LOAI and c.LoaiUpload =  " + obj.getLoaiUpload() +
							"\n 			where  b.TRANGTHAI = 1 and b.THANG =  "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI = "+obj.getLoai()+"  " + 
							"\n 		) dt PIVOT ( max(data) FOR thuongctId IN ('+@col+')) AS pvt  " + 
							"\n 	)data where 1=1 and ddkd.trangthai = ''1'' ' " +
						 
							"\n execute (@sql) " ;
	
		System.out.println("query BC="+ query);
		return query;
	}


	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IChiTieuNhanvien obj,String query,int sheetNum )throws Exception
	{
		try
		{
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();

			com.aspose.cells.Cell cell = cells.getCell("B3");
			dbutils db = new dbutils();
			cells.setRowHeight(0, 50.0f);
			 cell = cells.getCell("A1");
			 if(obj.getLoai().equals("1"))
				 ReportAPI.getCellStyle(cell, Color.RED, true, 16,"FORM UPLOAD CH??? TI??U NVBH");
			 else if(obj.getLoai().equals("2"))
				 ReportAPI.getCellStyle(cell, Color.RED, true, 16,"FORM UPLOAD CH??? TI??U GSBH");
			 else if(obj.getLoai().equals("3"))
				 ReportAPI.getCellStyle(cell, Color.RED, true, 16,"FORM UPLOAD CH??? TI??U ASM");
			 else if(obj.getLoai().equals("4"))
				 ReportAPI.getCellStyle(cell, Color.RED, true, 16,"FORM UPLOAD CH??? TI??U RSM");
			 else if(obj.getLoai().equals("5"))
				 ReportAPI.getCellStyle(cell, Color.RED, true, 16,"FORM UPLOAD CH??? TI??U NPP");

			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ng??y t???o : "+ getDateTime());		
		
			String loaiSQL = "1";
			Hashtable<String,String> hashDienGiai = new Hashtable<String, String>();
			String sql ="select a.pk_seq, a.diengiai as diengiai from TIEUCHITHUONG_CHITIET a   inner join TIEUCHITINHTHUONG b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ  " +
			" where    b.TRANGTHAI = 1 and b.THANG = "+obj.getThang()+" and b.NAM = "+obj.getNam()+" and b.LOAI = "+loaiSQL+" ";
			System.out.println("lay template " + sql);
			ResultSet rs = db.get(sql);
			while(rs.next())
			{
				hashDienGiai.put(rs.getString("pk_seq"), rs.getString("diengiai"));
				System.out.println("id " + rs.getString("pk_seq") + " - dien giai " + rs.getString("diengiai"));
			}

			rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 7;
			int column = 0;
			
			if(!obj.getLoai().equals("1"))// c???p qu???n l?? v??? kh??c
				for( int i =1 ; i <=socottrongSql ; i ++  )
				{
					cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i));
					ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
					if(hashDienGiai.get(rsmd.getColumnName(i))!= null)
					{
						cell = cells.getCell(countRow-1, column + i-1 );cell.setValue(hashDienGiai.get(rsmd.getColumnName(i)));
						ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
						cell = cells.getCell(countRow-1, 1 + column + i-1 );cell.setValue("[Sum c???p d?????i]" +  	hashDienGiai.get(rsmd.getColumnName(i)));
						ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
					}
						
	
				}
			else
				for( int i =1 ; i <=socottrongSql ; i ++  )
				{
					if(hashDienGiai.get(rsmd.getColumnName(i))!= null)
					{
						cell = cells.getCell(countRow-1, column + i-1 );cell.setValue(hashDienGiai.get(rsmd.getColumnName(i)));
						ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
					}
					cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i));
					ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);	
	
				}
			countRow ++;
			column = 0;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					Color c = Color.WHITE;
					cell = cells.getCell(countRow,column + i-1 );
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 0);
					}
				}
				++countRow;
			}
			
			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}


		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("L???i kh??ng l???y ???????c ch??? ti??u, vui l??ng ki???m tra l???i c??ng th???c th?????ng, ho???c th??ng ch??? ti??u!");
		}
	}
	
	
	private Hashtable<String, String> getHtpSanPham(dbutils db,IChiTieuNhanvien chitieu)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			"\n select distinct nsp.SP_FK,a.pk_seq " +
			"\n	from TIEUCHITHUONG_CHITIET a " +
			"\n inner join TIEUCHITINHTHUONG b on  a.TIEUCHITINHTHUONG_Fk = b.pk_seq " +
			"\n				 and b.loai = "+chitieu.getLoai()+" and b.trangthai = 1 and b.thang ="+chitieu.getThang()+" and b.nam ="+chitieu.getNam()+" " +
			"\n inner join TieuChi c on c.TieuChi = a.TieuChi  and c.LOAI = b.LOAI and c.LoaiUpload =  " + chitieu.getLoaiUpload() +
			"\n inner join NHOMSANPHAMCHITIEU_SANPHAM nsp on nsp.NSP_FK = a.NHOMSP_FK " ;
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("pk_seq").trim() +"-" + rs.getString("SP_FK").trim(),  rs.getString("SP_FK").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			System.out.println("Loi lay getHtpSanPham : " + er.toString());
		}
		return htbtuyen;
	}
	
	
	private void ExportToExcel(OutputStream out,IChiTieuNhanvien obj )throws Exception
	{
		try
		{ 			
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);

			String query = setQuery(obj);
			TaoBaoCao(workbook, obj, query, 0);
			workbook.save(out);	
			out.close();
			return;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	
}
