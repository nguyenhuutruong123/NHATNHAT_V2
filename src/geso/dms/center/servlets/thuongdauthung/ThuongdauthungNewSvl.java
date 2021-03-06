package geso.dms.center.servlets.thuongdauthung;

import geso.dms.center.beans.thuongdauthung.imp.Sanpham;
import geso.dms.center.beans.thuongdauthung.ISanpham;
import geso.dms.center.beans.thuongdauthung.IThuongdauthung;
import geso.dms.center.beans.thuongdauthung.imp.Thuongdauthung;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class ThuongdauthungNewSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ThuongdauthungNewSvl()
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
		String querystring = request.getQueryString();
		Utility util = new Utility();
		String userId = util.getUserId(querystring);
		// System.out.println("Ten user:  "+ userId);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.getId(querystring);

		IThuongdauthung obj = new Thuongdauthung(id);

		obj.setUserId(userId);
		Utility Ult = new Utility();
		session.setAttribute("userId", userId);
		String tenuser = gettenuser(userId);
		session.setAttribute("userTen", tenuser);

		String action = util.getAction(querystring);
		if (action.equals("display"))
		{
			obj.setDisplay("1");
		}
		obj.createRs();
		session.setAttribute("check", "0");
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/ThuongdauthungUpdate.jsp";// default
		response.sendRedirect(nextJSP);

	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();

		Utility util = new Utility();

		IThuongdauthung obj = new Thuongdauthung();

		String id = util.antiSQLInspection(request.getParameter("id"));
		try
		{

			obj.setID(Double.parseDouble(id));

		} catch (Exception err)
		{
		}
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setUserId(userId);
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		obj.setDienGiai(diengiai);
		obj.setNguoiSua(userId);
		obj.setNguoiTao(userId);
		obj.setNgayTao(this.getDateTime());
		obj.setNgaySua(this.getDateTime());

		String nsp_fk = util.antiSQLInspection(request.getParameter("nsp_fk"));
		if(nsp_fk == null || nsp_fk =="")
			nsp_fk = "null";
		obj.setNsp_fk(nsp_fk);

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		obj.setDenngay(denngay);


		String loaict = util.antiSQLInspection(request.getParameter("loaict") == null ? "0" : request
				.getParameter("loaict"));
		obj.setLoaict(loaict);



		double soluong = 0;
		try
		{
			soluong = Double.parseDouble(util.antiSQLInspection(request.getParameter("soluong")));
		} catch (Exception e)
		{
		}
		obj.setSoluong(soluong);



		double thuongASM = 0;
		try
		{
			thuongASM = Double.parseDouble(util.antiSQLInspection(request.getParameter("thuongASM")).replace(",", ""));
		} catch (Exception e)
		{
		}
		obj.setThuongASM(thuongASM);



		double thuongSR = 0;
		try
		{
			thuongSR = Double.parseDouble(util.antiSQLInspection(request.getParameter("thuongSR")).replace(",", ""));
		} catch (Exception e)
		{
		}
		obj.setThuongSR(thuongSR);

		double thuongSS = 0;
		try
		{
			thuongSS = Double.parseDouble(util.antiSQLInspection(request.getParameter("thuongSS")).replace(",", ""));
		} catch (Exception e)
		{
		}
		obj.setThuongSS(thuongSS);


		String[] sanpham = request.getParameterValues("sanpham");




		String[] nhomchitieuId = request.getParameterValues("nhomchitieuId");
		
		String[] nhomCtSelected = request.getParameterValues("nhomCtSelected");
		String[] Thuong_Selected = request.getParameterValues("Thuong_Selected");
		
		String nhomchitieuIds = "";
		if (nhomchitieuId != null)
		{
			String[] thuongchitieuId = new String[nhomchitieuId.length];
			
			for (int i = 0; i < nhomchitieuId.length; i++)
			{
				if (nhomchitieuId[i].trim().length() > 0)
				{

					nhomchitieuIds += nhomchitieuId[i].trim() + ",";
					if(nhomCtSelected != null)
					{
						for (int z = 0; z < nhomCtSelected.length; z++)
						{
							if(nhomchitieuId[i].equals(nhomCtSelected[z]))
							{
								thuongchitieuId[i] = Thuong_Selected[z].replace(",","").trim();
								break;
							}
						}
					}
					if(thuongchitieuId[i] == null)
					{
						thuongchitieuId[i] = "0";
					}
				}
			}
			if (nhomchitieuIds.length() > 0)
			{
				nhomchitieuIds = nhomchitieuIds.substring(0, nhomchitieuIds.length() - 1);
				obj.setNhomCtSelected(nhomchitieuId);
				obj.setThuong_Selected(thuongchitieuId);
			}
		}
		
		
		
		System.out.println("alo1 2345:" + nhomchitieuIds);
		obj.setNhomchitieuId(nhomchitieuIds);




		String[] spId = request.getParameterValues("spId");
		String[] spMa = request.getParameterValues("spMa");
		String[] spTen = request.getParameterValues("spTen");

		String spIds = "";
		List<ISanpham> spList = new ArrayList<ISanpham>();

		if (spId != null)
		{
			for (int i = 0; i < spId.length; i++)
			{
				if (spId[i].trim().length() > 0)
				{
					ISanpham sp = new Sanpham();
					sp.setId(spId[i]);
					sp.setMa(spMa[i]);
					sp.setTen(spTen[i]);
					spList.add(sp);
					spIds += spId[i] + ",";
				}
			}
			if (spIds.length() > 0)
			{
				spIds = spIds.substring(0, spIds.length() - 1);
			}
		}
		obj.setSpList(spList);

		String action = request.getParameter("action");
		if (action.equals("save"))
		{
			if ((obj.getID() == 0 ? obj.CreateLuongKhac() : obj.UpdateLuongKhac()))
			{
				session.setAttribute("nam", 0);
				session.setAttribute("thang", 0);
				obj.setLuongkhacRs("");
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Center/Thuongdauthung.jsp";
				response.sendRedirect(nextJSP);

				System.out.println(obj.getMessage());
			} else
			{
				obj.createRs();
				String nextJSP = request.getContextPath() + "/pages/Center/ThuongdauthungUpdate.jsp";// default
				session.setAttribute("obj", obj);
				response.sendRedirect(nextJSP);
				System.out.println(obj.getMessage());
			}
		} else if (action.equals("excel"))
		{
			try
			{
				OutputStream out = response.getOutputStream();
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=ThuongDauThung.xlsm");
				FileInputStream fstream = new FileInputStream(
						getServletContext().getInitParameter("path") + "\\ThuongDauThung.xlsm");
				Workbook workbook = new Workbook();
				workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				CreateStaticHeader(workbook, obj);
				obj.setUserId(userId);
				obj.setID(Double.parseDouble(id));
				obj= new Thuongdauthung(id);
				FillData(workbook, obj);
				workbook.save(out);
				fstream.close();
			} catch (Exception ex)
			{
				ex.printStackTrace();
				obj.setMessage("Khong the tao pivot.");
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/ThuongdauthungUpdate.jsp";// default
			response.sendRedirect(nextJSP);
		} else
		{
			obj.createRs();
			//obj.createSpList();
			String nextJSP = request.getContextPath() + "/pages/Center/ThuongdauthungUpdate.jsp";// default
			session.setAttribute("obj", obj);
			response.sendRedirect(nextJSP);
			System.out.println(obj.getMessage());
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		return dateFormat.format(date);
	}

	private boolean FillData(Workbook workbook, IThuongdauthung obj) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();
		ResultSet hdRs = obj.getDataRs();
		int SoTt = 1;

		int cotdautien = 50;
		int index = 1;

		ResultSetMetaData rsmd = hdRs.getMetaData();
		int socottrongSql = rsmd.getColumnCount();

		try
		{
			Cell cell = null;

			while (hdRs.next())
			{

				for(int k =1 ; k <=socottrongSql; k ++)
				{
					cell = cells.getCell(index,cotdautien+k-1);
					if(k >3)
						cell.setValue(hdRs.getDouble(k));	
					else
						cell.setValue(hdRs.getString(k));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}

				++index;
			}	
			if (hdRs != null)
				hdRs.close();

			if (db != null)
				db.shutDown();

			if (index == 0)
			{
				throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
			}

		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		return true;

	}


	private void CreateStaticHeader(Workbook workbook, IThuongdauthung obj)
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();

		Style style;
		Font font = new Font();
		font.setColor(Color.RED);// mau chu
		font.setSize(16);// size chu
		font.setBold(true);

		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		style = cell.getStyle();
		style.setFont(font);
		style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu



		cells.setRowHeight(3, 18.0f);
		cell = cells.getCell("A2");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "T??? ng??y : " + obj.getTungay() + " -"+"?????n ng??y : " + obj.getDenngay() + "");

		cells.setRowHeight(4, 18.0f);
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ng??y b??o c??o: " + ReportAPI.NOW("yyyy-MM-dd"));

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "???????c t???o b???i:  ="+ obj.getNguoiSua());


		String loaict = "S??? l?????ng th??ng";
		if(obj.getLoaict().equals("1"))
			loaict = "S??? l?????ng l???";
		else if(obj.getLoaict().equals("2"))
			loaict = "S??? kh??ch h??ng";
		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A5");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Lo???i CT: "+loaict);

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A6");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Th?????ng NVBH ");

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("B6");
		cell.setValue(obj.getThuongSR());

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A7");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Th?????ng ASM ");

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("B7");
		cell.setValue(obj.getThuongSS());

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("A8");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Th?????ng Qu???n l?? KV ");

		cells.setRowHeight(5, 18.0f);
		cell = cells.getCell("B8");
		cell.setValue(obj.getThuongASM());


		int cotdautien = 50;
		int index = 0;

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("Quan ly khu vuc");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("ASM");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("Trinh Duoc Vien");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("Thuc Dat");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);		

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("Thuong Trinh Duoc Vien");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);		

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("Thuong ASM");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

		cell = cells.getCell(index,cotdautien++);
		cell.setValue("Thuong Quan Ly Khu Vuc");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
	}

}
