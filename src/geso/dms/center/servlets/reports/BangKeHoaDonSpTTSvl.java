package geso.dms.center.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.beans.report.IBangKeHoaDonSpList;
import geso.dms.center.beans.report.IBcDoanhThuBanHangTTList;
import geso.dms.center.beans.report.imp.BangKeHoaDonSpList;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/BangKeHoaDonSpTTSvl")
public class BangKeHoaDonSpTTSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BangKeHoaDonSpTTSvl()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		
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
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String loaihoadon = request.getParameter("loaihoadon");
		if (loaihoadon == null)
			loaihoadon = "0";

		obj = new Stockintransit();
		obj.setuserId(userId);
		obj.setView("TT");
		String nextJSP = "";
		obj.init_user();


		nextJSP = request.getContextPath() + "/pages/Center/BangKeHoaDonSpTT.jsp";
		session.setAttribute("obj", obj);
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		OutputStream out = response.getOutputStream();

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		IStockintransit obj = new Stockintransit();
		obj.setuserId(userId);

		obj.setView("TT");
		
		String tungay = util.antiSQLInspection(request.getParameter("tungay")==null?"": request.getParameter("tungay"));
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay")==null?"": request.getParameter("denngay"));
		obj.setdenngay(denngay);

		String vungId = util.antiSQLInspection(request.getParameter("vungId")==null?"": request.getParameter("vungId"));
		obj.setvungId(vungId);

		String kenhId = util.antiSQLInspection(request.getParameter("kenhId")==null?"": request.getParameter("kenhId"));
		obj.setkenhId(kenhId);    


		String nhomId = util.antiSQLInspection(request.getParameter("nhomId")==null?"": request.getParameter("nhomId"));
		obj.setNhomhangid(nhomId);


		String khId = util.antiSQLInspection(request.getParameter("khId")==null?"": request.getParameter("khId"));
		obj.setkhId(khId);

		String ddkdId = util.antiSQLInspection( request.getParameter("ddkdId")==null?"": request.getParameter("ddkdId"));
		obj.setDdkd(ddkdId);

		String spId =util.antiSQLInspection(request.getParameter("spId")==null?"": request.getParameter("spId"));
		obj.setSpId(spId);


		String nppId =util.antiSQLInspection(request.getParameter("nppId")==null?"": request.getParameter("nppId"));
		obj.setNppId(nppId);

		obj.setMucCN_DT("0");

		obj.setMuc_KhachHang(util.antiSQLInspection(request.getParameter("kh")) != null ? util.antiSQLInspection(request.getParameter("kh")) : "");

		obj.set_Action(action);
		System.out.println("___ATION "+action+"___"+nppId);

		if (action.equals("excel")  )
		{
			
			try {
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCDoanhThuTheoSanPham.xlsm");
				String query = AjaxDistributionTT.queryDoanhThuTheoSP(request, util,AjaxDistributionTT.QUERY_EXCEL_ACTION,0,0) ;
				ExportToExcel(session,out, obj, query);
				obj.getDb().shutDown();
				return;
			}
			catch (Exception ex) {
				ex.printStackTrace();
				System.out.println("Error Here : "+ex.toString());
				request.getSession().setAttribute("errors", ex.getMessage());
			}
			
			
			/*try
			{ 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=DoanhThuTheoSanPham.xlsm");
				FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BangKeHoaDonSpTT.xlsm");
				Workbook workbook = new Workbook();
				workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				CreateStaticHeader(workbook, obj);
				obj.setUserId(userId);
				obj.init("");
				String query=obj.getQueryHd();
				System.out.println("query : "+ query);
				FillData(workbook,obj, query);
				workbook.save(out);
				fstream.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				obj.setMsg("Khong the tao pivot.");
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/BangKeHoaDonSpTT.jsp";
			response.sendRedirect(nextJSP); 
		}
		else  if(action.equals("view") || action.equals("next") || action.equals("prev")){

			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			obj.setUserId(userId);
			obj.init("");

			session.setAttribute("obj", obj);
			response.sendRedirect(request.getContextPath() + "/pages/Center/BangKeHoaDonSpTT.jsp");
		}*/
		}
		else if(action.equals("search"))
		{	
			obj.setuserId(userId);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			obj.init_user();
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/BangKeHoaDonSpTT.jsp";
			response.sendRedirect(nextJSP); 
		}
		else
		{
			session.setAttribute("obj", obj);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/BangKeHoaDonSpTT.jsp";
			obj.init_user();
			response.sendRedirect(nextJSP);  
		}
	}

	private void ExportToExcel(HttpSession session,OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try
		{ 				
			FileInputStream fstream = null;
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(session,workbook, obj, query);			
			workbook.save(out);					
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	private void TaoBaoCao(HttpSession session,com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		try
		{		
			
			redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis();
		
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "BÁO CÁO DOANH THU THEO SẢN PHẨM");
			cell = cells.getCell("A2");
			/*ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());		*/	
			
			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			
			int location  = 0;
			int row = 10;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				String headerColumnName = Utility.GLanguage(rsmd.getColumnName(i).replace("(%)",""),session,jedis)  ;
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(headerColumnName);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{					
					cell = cells.getCell(row,location + i-1 );
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 37;
							if(rsmd.getColumnName(i).contains("%")|| rsmd.getColumnName(i).contains("(%)") )	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++row;
			}
			if(rs!=null)rs.close();		
			geso.dms.center.util.Utility.JedisClose(jedis);
		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception("Lỗi : Không có dữ liệu để xuất file !");
		}	
	}
	
	
}
