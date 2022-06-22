package geso.dms.center.servlets.reports;

import geso.dms.center.beans.report.IBcDoanhThuKhachHangList;
import geso.dms.center.beans.report.imp.BcDoanhThuKhachHangList;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.beans.reports.IBcDoanhThuSanPham;
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

@WebServlet("/ChiSoDoanhThuNVSvl")
public class ChiSoDoanhThuNVSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;


	public ChiSoDoanhThuNVSvl()
	{ super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		Utility util = new Utility();

		String querystring = request.getQueryString();
		System.out.println("query string la "+querystring);
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));


		String KHTT = request.getParameter("DSKHT");
		if (KHTT == null)
			KHTT = "";

		String loaihoadon = request.getParameter("loaihoadon");
		if (loaihoadon == null)
			loaihoadon = "0";

		obj = new Stockintransit();
		obj.setuserId(userId);
		obj.setView("TT");
		String nextJSP = "";
		obj.setxemtheo("0");

		obj.init_user();
		
		nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuNV.jsp";
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

		OutputStream out = response.getOutputStream();

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		IStockintransit obj = new Stockintransit();
		obj.setuserId(userId);


		obj.setView("TT");

		String tungay =request.getParameter("Sdays")==null?"": request.getParameter("Sdays");
		obj.settungay(tungay);

		String denngay = request.getParameter("Edays")==null?"": request.getParameter("Edays");
		obj.setdenngay(denngay);

		String vungId = request.getParameter("vungId")==null?"": request.getParameter("vungId");
		obj.setvungId(vungId);

		String kbhId = request.getParameter("kbhId")==null?"": request.getParameter("kbhId");
		obj.setkenhId(kbhId);    

		String tpId = request.getParameter("tpId");
		if (tpId == null)
			tpId = "";
		obj.setTinhthanhid(tpId);

		String qhId = request.getParameter("qhId");
		if (qhId == null)
			qhId = "";
		obj.setQhId(qhId);

		String phuongxaId = request.getParameter("phuongxaId");
		if (phuongxaId == null)
			phuongxaId = "";
		System.out.println("phuongxa : "+ phuongxaId);
		obj.setPhuongxa(phuongxaId);

		String ttId = request.getParameter("ttId")==null?"": request.getParameter("ttId");
		obj.setTtId(ttId);   

		String nhomId = request.getParameter("nhomId")==null?"": request.getParameter("nhomId");
		obj.setNhomhangid(nhomId);


		String khId = request.getParameter("khId")==null?"": request.getParameter("khId");
		obj.setkhId(khId);

		String ddkdId =  request.getParameter("ddkdId")==null?"": request.getParameter("ddkdId");
		obj.setDdkd(ddkdId);

		String spId =request.getParameter("spId")==null?"": request.getParameter("spId");
		obj.setSpId(spId);


		String KHTT =request.getParameter("DSKHT")==null?"": request.getParameter("DSKHT"); 
		if (KHTT == null)
			KHTT = "";

		String KHTT_client = request.getParameter("DSKHT_client");
		if (KHTT_client == null)
			KHTT_client = "";
		obj.setDSKHT_client(KHTT_client);


		String thangbd =request.getParameter("thangbd")==null?"": request.getParameter("thangbd"); 
		if (thangbd == null)
			thangbd = "";
		obj.setThangbd(thangbd);
		System.out.println("thang bdat la "+thangbd);

		String nambd =request.getParameter("nambd")==null?"": request.getParameter("nambd"); 
		if (nambd == null)
			nambd = "";
		obj.setNambd(nambd);

		String thangkt =request.getParameter("thangkt")==null?"": request.getParameter("thangkt"); 
		if (thangkt == null)
			thangkt = "";
		obj.setThangkt(thangkt);

		String namkt =request.getParameter("namkt")==null?"": request.getParameter("namkt"); 
		if (namkt == null)
			namkt = "";
		obj.setNamkt(namkt);

		String DSKHT =request.getParameter("DSKHT")==null?"": request.getParameter("DSKHT"); 
		if (DSKHT == null)
			DSKHT = "";
		obj.setDSKHT(DSKHT);

		String nppId =request.getParameter("nppId")==null?"": request.getParameter("nppId");
		obj.setNppId(nppId);
		obj.setLaynk(util.antiSQLInspection(request.getParameter("klaynhomnk")) != null ? util.antiSQLInspection(request.getParameter("klaynhomnk")) : "0");
		obj.set_Action(action);

		System.out.println("___ATION "+action);


		if (action.equals("excel")  )
		{
			if(KHTT.equals("DSKHT"))
			{
				return;
			}
			else
			{
				try {
					request.setCharacterEncoding("utf-8");
					response.setContentType("application/xlsm");
					response.setHeader("Content-Disposition", "attachment; filename=BCDoanhsotheosanpham.xlsm");
					String query = AjaxDistributionTT.queryDoanhThuKhachHang(request, util,AjaxDistributionTT.QUERY_EXCEL_ACTION,0,0) ;
					ExportToExcel( out, obj, query );
					obj.getDb().shutDown();
					return;
				}
				catch (Exception ex) {
					ex.printStackTrace();
					System.out.println("Error Here : "+ex.toString());
					request.getSession().setAttribute("errors", ex.getMessage());
				}
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "";
			if(KHTT.equals("DSKHT"))
			{
				nextJSP = request.getContextPath() + "/pages/Center/BcDoanhThuKhachHangThang.jsp";
				response.sendRedirect(nextJSP); 
				return;
			}
			nextJSP = request.getContextPath() + "/pages/Center/BcDoanhThuKhachHang.jsp";
			response.sendRedirect(nextJSP); 
		}

		else
		{

			String nextJSP = "";
			if(KHTT.equals("DSKHT"))
			{
				obj.init_user();
				session.setAttribute("obj", obj);
				nextJSP = request.getContextPath() + "/pages/Center/BcDoanhThuKhachHangThang.jsp";
				response.sendRedirect(nextJSP); 
				return;
			}
			obj.init_user();
			session.setAttribute("obj", obj);
			nextJSP = request.getContextPath() + "/pages/Center/BcDoanhThuKhachHang.jsp";
			response.sendRedirect(nextJSP);  
		}

	}
	private void ExportToExcel(OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 					
			
			FileInputStream fstream = null;
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook, obj, query);			
			workbook.save(out);					

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		
		try{		

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo doanh số khách hàng ");
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
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i).replace("(%)",""));
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
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}	
	}

}
