package geso.dms.center.servlets.reports;

import geso.dms.center.beans.bandott.IBandott;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
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
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class KhoaSoChiTieuSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public KhoaSoChiTieuSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setuserId(userId);
		//obj.getNppInfo();
		//obj.init();

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/KhoaSoChiTieu.jsp";
		response.sendRedirect(nextJSP);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		obj.setuserId((String) session.getAttribute("userId") == null ? "" : (String) session.getAttribute("userId"));

		obj.setuserTen((String) session.getAttribute("userTen") == null ? "" : (String) session.getAttribute("userTen"));

		obj.setnppId(util.antiSQLInspection(request.getParameter("nppId")) == null ? "" : util.antiSQLInspection(request.getParameter("nppId")));

		obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId")) == null ? "" : util.antiSQLInspection(request.getParameter("kenhId")));

		obj.setdvkdId(util.antiSQLInspection(request.getParameter("dvkdId")) == null ? "" : util.antiSQLInspection(request.getParameter("dvkdId")));

		obj.setMonth(util.antiSQLInspection(request.getParameter("month")) == null ? "" : util.antiSQLInspection(request.getParameter("month")));

		obj.setYear(util.antiSQLInspection(request.getParameter("year")) == null ? "" : util.antiSQLInspection(request.getParameter("year")));

		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId")) == null ? "" : util.antiSQLInspection(request.getParameter("vungId")));

		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId")) == null ? "" : util.antiSQLInspection(request.getParameter("khuvucId")));

		obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlId")) == null ? "" : util.antiSQLInspection(request.getParameter("dvdlId")));

		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId")) == null ? "" : util.antiSQLInspection(request.getParameter("ddkdId")));

		obj.setgsbhId(util.antiSQLInspection(request.getParameter("gsbhId")) == null ? "" : util.antiSQLInspection(request.getParameter("gsbhId")));

		String[] fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);

		String userId = request.getParameter("userId");
		obj.setuserId(userId);
		String view=request.getParameter("view");
		if(view == null)
			view = "";

	
		
	
		
		
		String tctctId =  request.getParameter("tctctId");
		if(tctctId == null)
			tctctId = "";
		obj.setTctctId(tctctId);
		
		
		String action = util.antiSQLInspection(request.getParameter("action"));
		if (action.equals("Taomoi"))
		{
			String msg =  KhoaSo( obj);
			obj.setMsg(msg);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", obj.getuserId());
			
		}else
		{
			//obj.init();
			session.setAttribute("obj", obj);
			session.setAttribute("userId", obj.getuserId());
		}
		String nextJSP = request.getContextPath() + "/pages/Center/KhoaSoChiTieu.jsp";
		response.sendRedirect(nextJSP);

	}


	public String KhoaSo(IStockintransit obj)
	{
		try
		{
			obj.getDb().getConnection().setAutoCommit(false);
			
			String query = " delete KhoaSoChiTieu_DDKD where thang ="+obj.getMonth()+" and nam =  "+obj.getYear()+" ";
			if(!obj.getDb().update(query))
			{
				obj.getDb().getConnection().rollback();
				obj.getDb().getConnection().setAutoCommit(true);
				return "lỗi \n " + query;
			}
			
			 query =  			"\n select a.loai isTinhLuong,a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a" +
			"\n inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+
			"\n and b.TRANGTHAI = 1 and b.LOAI = 1  " +
			"\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq " +
			"\n where ct.TCTCT_FK = a.pk_seq and ct.chitieu > 0 and ctnv.trangthai = 1 ) ";
			System.out.println("QRBC "+query );
			ResultSet rs = obj.getDb().get(query);
			boolean daInsert = false;
			while(rs.next())
			{
				String tctctId = rs.getString("pk_seq");
				String tieuchi = rs.getString("tieuchi");
				String diengiai = rs.getString("diengiai");
						query =	"\n insert KhoaSoChiTieu_DDKD(thang,nam,CTNV_FK,TCTCT_FK,NSP_FK,TIEUCHI,NhanVien_FK,ChiTieu,ThucDat,TyLeDat,Thuong,	nguoitao) " +
								"\n select "+obj.getMonth()+" thang, "+obj.getYear()+" nam " +
								"\n		,ct.CTNV_FK,ct.tctct_fk,ct.NSP_FK,ct.TIEUCHI,ct.NhanVien_FK " +
								"\n		,ct.ChiTieu,isnull(td.thucdat,0)thucdat	, dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) tyle,0 thuong" +
								"\n		,"+obj.getuserId()+" nguoitao	  " +
								"\n from ChiTieuNhanVien_DDKD ct " +
								"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
								"\n left join [dbo].[ufn_KPI_DDKD](0,'"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"'," + tctctId+",null) td on ct.NhanVien_FK = td.NhanVien_FK " +
								"\n where ct.chitieu > 0  "
								+ " and ct.tctct_fk = " + tctctId+" ";
				System.out.println("query bc= " + query);
				if(obj.getDb().updateReturnInt(query)<=0)
				{
					obj.getDb().getConnection().rollback();
					obj.getDb().getConnection().setAutoCommit(true);
					return "lỗi khóa sổ tiêu chí("+diengiai+"):\n " + query;
				}
			}
			obj.getDb().getConnection().commit();
			obj.getDb().getConnection().setAutoCommit(true);
			return "Khóa sổ chỉ tiêu thành công";
		}
		catch (Exception e) {
			try {	obj.getDb().getConnection().rollback();} catch (SQLException e1) {}
			try {	obj.getDb().getConnection().setAutoCommit(true);} catch (SQLException e1) {}
			return "Lỗi:" + e.getMessage();
		}
		
	}
	


	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	

	


	
}
