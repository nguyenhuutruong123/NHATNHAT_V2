package geso.dms.center.servlets.reports;

import geso.dms.center.beans.report.IBcTheKho;
import geso.dms.center.beans.report.imp.BcTheKho;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.beans.reports.IBCBangKeHoaDon;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.formula.functions.Npv;

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
import com.google.gson.annotations.Until;

@WebServlet("/BCSoQuyTienMat")
public class BCSoQuyTienMat extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BCSoQuyTienMat()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();

		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String trungtam=request.getParameter("trungtam");

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String loaihoadon = request.getParameter("loaihoadon");
		if (loaihoadon == null)
			loaihoadon = "0";

		obj = new Stockintransit();
		obj.setuserId(userId);


		String view = request.getParameter("view");
		if(view == null)
			view = "";
		obj.setLoaiMenu(view);

		if(view.length()<=0)
		{
			String  nppId=util.getIdNhapp(userId);
			obj.setNppId(nppId);
		}
		String nextJSP = "";
		nextJSP = request.getContextPath() + "/pages/Center/BCSoQuyTienMat.jsp";

		session.setAttribute("obj", obj);
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		HttpSession session = request.getSession();

		
		String userTen = (String) session.getAttribute("userTen");
		
		
		OutputStream out = response.getOutputStream();

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		IStockintransit obj = new Stockintransit();
		obj.setuserId(userId);
		obj.setuserTen(userTen);
		String view = request.getParameter("view");
		if(view == null)
			view = "";
		obj.setLoaiMenu(view);

		if(view.length()<=0)
		{
			String  nppId=util.getIdNhapp(userId);
			obj.setNppId(nppId);
			obj.setnppId(nppId);
		}

		String tungay =request.getParameter("Sdays")==null?"": request.getParameter("Sdays");
		obj.settungay(tungay);

		String denngay = request.getParameter("Edays")==null?"": request.getParameter("Edays");
		obj.setdenngay(denngay);

		String vungId = request.getParameter("vungId")==null?"": request.getParameter("vungId");
		obj.setvungId(vungId);

		String kbhId = request.getParameter("kbhId")==null?"": request.getParameter("kbhId");
		obj.setkenhId(kbhId);    


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

/*
		String nppId =request.getParameter("nppId")==null?"": request.getParameter("nppId");
		obj.setNppId(nppId);


	*/

		String khoId =request.getParameter("khoId")==null?"": request.getParameter("khoId");
		obj.setKhoid(khoId);


		System.out.println("___ATION "+action);


		if (action.equals("excel")  )
		{
			try
			{ 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=SoQuyTienMat.xlsm");
				
		    	ExportToExcel( out, obj );
		    	return;

			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				obj.setMsg("Khong the tao pivot.");
			}
			
		}


		else if(action.equals("search"))
		{	
			obj.setuserId(userId);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/BCSoQuyTienMat.jsp";

			response.sendRedirect(nextJSP); 
		}
		else
		{
			session.setAttribute("obj", obj);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/BCSoQuyTienMat.jsp";

			
			response.sendRedirect(nextJSP);  
		}

	}

	
	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");
		if(arr.length==3)
			return arr[2] + "/" + arr[1] + "/" + arr[0];
		else
			return "";
	}

	
	
	private void ExportToExcel(OutputStream out,IStockintransit obj )
	{
		try{ 			

			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			TaoBaoCao(workbook, obj,7,0,0);
			workbook.save(out);			

		}catch(Exception ex){
			ex.printStackTrace();
			
		}

	}
	public String getQuery(IStockintransit obj)
	{
		String nppInsert = "1";
		if(obj.getLoaiMenu().equals(""))
			nppInsert = obj.getNppId();
		return "  select * from [dbo].[ufn_CongNoDauKy]('"+ obj.gettungay() +"','"+ obj.getdenngay() +"', "+nppInsert+",'1111' )  order by NGAYCHUNGTU ";
	}
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,int countRow,int column,int sheetNum)throws Exception
	{
		try{

			String nppInsert = "1";
			if(obj.getLoaiMenu().equals(""))
				nppInsert =obj.getNppId();
			
			String querytondau = "\n declare @xnt float =  ( select isnull( sum ( isnull(thu,0) - isnull(chi,0) ) , 0 ) XNT from [dbo].[ufn_CongNoDauKy]('1900-01-01',convert (char(10),dateadd (dd,-1,'"+ obj.gettungay() +"'),126), "+nppInsert+",'1111' ) )" +
								 "\n declare @maNPP nvarchar(200) =  ( select mafast from nhaphanphoi where pk_seq = "+nppInsert+" )" +
								 "\n select  @xnt as XNT,  @maNPP as  maNPP   ";
			System.out.println("query ton dau = " + querytondau);
			ResultSet rstondau=obj.getDb().get(querytondau);
			rstondau.next();
			double TonDau = rstondau.getDouble("XNT");
			String maNPP =  rstondau.getString("maNPP");
			rstondau.close();
			
			
			String query = getQuery(obj);
			
			
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();

			com.aspose.cells.Cell cell = cells.getCell("D4");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "SỔ QUỸ TIỀN MẶT");
			Style style = cell.getStyle();
			com.aspose.cells.Font font = new com.aspose.cells.Font();
			font.setColor(Color.RED);//mau chu
			font.setSize(16);// size chu
			font.setBold(true);
			style.setFont(font);
			cell.setStyle(style);	
			
			
			
			cell = cells.getCell("A1");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Người tạo:"+ obj.getuserTen());
			
			cell = cells.getCell("D5");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Thời gian:" + obj.gettungay() +" đến " + obj.getdenngay());
			
	
			cell = cells.getCell("A7");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Tài khoản:");
			
			
			cell = cells.getCell("B7");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Tiền mặt VND	[1111] - "+maNPP);
			
			ResultSet	rs = obj.getDb().get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
		
			
			
			int cong = 0;
			cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY CT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("SỐ CT THU"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("SỐ CT CHI"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			
			cells.setColumnWidth(cong, (float) ("KHÁCH GIAO DỊCH".length()));
			cell = cells.getCell(countRow, cong++ );cell.setValue("KHÁCH GIAO DỊCH");	 ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			
			int cotdiengiai = cong;
			cell = cells.getCell(countRow, cong );cell.setValue("DIỄN GIẢI"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow + 1, cong ++ );cell.setValue("Số dư đầu kỳ"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("TKĐƯ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0)
			;
			
			int cotthu = cong;
			cell = cells.getCell(countRow, cong++ );cell.setValue("THU"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			int cotchi = cong;
			cell = cells.getCell(countRow, cong );cell.setValue("CHI"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			countRow ++;
			
			cell = cells.getCell(countRow, cotthu );cell.setValue(TonDau); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			int sott = 1;
			countRow ++;
			double tongthu = 0;
			double tongchi = 0;
			while(rs.next())
			{
				
				Color c = Color.WHITE;
				boolean bold =false; 
				
				cong = 0;
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("NGAYCHUNGTU")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("CTThu")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("CTChi")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				
				
				if(rs.getString("KHGIAODICH") != null)
				{
				float dorongchu = rs.getString("KHGIAODICH").trim().length();
				float withOld = cells.getColumnWidth(cong);
				if(dorongchu/1.2 >  withOld )
					cells.setColumnWidth(cong, (float) (dorongchu/1.2));
				
				
				}
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("KHGIAODICH")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				
				
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("DienGiai")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("TKDU")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("Thu")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("Chi")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
				TonDau +=  rs.getDouble("Thu") - rs.getDouble("Chi");
				tongthu += rs.getDouble("Thu");
				tongchi += rs.getDouble("Chi");
				
			
				++countRow;
			}
			
			
			cell = cells.getCell(countRow,cotdiengiai); cell.setValue("Cộng PS"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);
			cell = cells.getCell(countRow,cotthu); cell.setValue(tongthu); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);
			cell = cells.getCell(countRow,cotchi); cell.setValue(tongchi); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);
			++countRow;
			cell = cells.getCell(countRow,cotdiengiai); cell.setValue("Tồn cuối"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);
			cell = cells.getCell(countRow,cotthu); cell.setValue(TonDau); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 43);
			
			
			if(rs!=null)rs.close();



		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	
	
}
