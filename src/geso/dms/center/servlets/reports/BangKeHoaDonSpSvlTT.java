package geso.dms.center.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.beans.bangkehoadonsp.BangKeHoaDonSpList;
import geso.dms.center.beans.bangkehoadonsp.IBangKeHoaDonSpList;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;

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

@WebServlet("/BangKeHoaDonSpSvlTT")
public class BangKeHoaDonSpSvlTT extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public BangKeHoaDonSpSvlTT()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IBangKeHoaDonSpList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		
		Utility util = new Utility();
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		
		String loaihoadon = request.getParameter("loaihoadon");
		if (loaihoadon == null)
			loaihoadon = "0";
		
		obj = new BangKeHoaDonSpList();
		obj.setUserId(userId);
		String nextJSP = "";
		obj.init("");
		
		nextJSP = request.getContextPath() + "/pages/Center/BangKeHoaDonSp.jsp";
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
    
    OutputStream out = response.getOutputStream();
    
    String action = request.getParameter("action");
    if (action == null)
    	action = "";
    
    IBangKeHoaDonSpList obj = new BangKeHoaDonSpList();
    obj.setUserId(userId);
    
    String tungay = request.getParameter("Sdays");
    if(tungay == null)
    	tungay = "";
    obj.setTuNgay(tungay);
    
    String denngay = request.getParameter("Edays");
    if(denngay == null)
    	denngay = "";
    obj.setDenNgay(denngay);
    
    String khId = request.getParameter("khId");
    if(khId == null)
    	khId = "";
    obj.setKhId(khId);
    
    String ddkdId = request.getParameter("ddkdId");
    if(ddkdId == null)
    	ddkdId = "";
    obj.setDdkdId(ddkdId);
    
    String spId = request.getParameter("spId");
    if(spId == null)
    	spId = "";
    obj.setSpId(spId);
    
    String kbhId = request.getParameter("kbhId");
    if(kbhId == null)
    	kbhId = "";
    obj.setKbhId(kbhId);
  	

    String view = request.getParameter("view");
	if(view == null)
		view = "";
	obj.setView(view);
	System.out.println("VIEW LA: " + view);
	
			String npp="";
			if(view.length()>0  )
			{
				 npp=  request.getParameter("npp");
				 if(npp==null)
					 npp="";
			}
			else 
			{
				npp=util.getIdNhapp(userId);
			}
			obj.setNppId(npp);
	
    if (action.equals("excel")  )
    {
    	try
	    { 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BangKeHoaDonSp.xlsm");
	      FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BangKeHoaDonSp.xlsm");
				Workbook workbook = new Workbook();
				workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				CreateStaticHeader(workbook, obj);
				obj.setUserId(userId);
	  		obj.init(action);
				String query=obj.getQueryHd();
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
  		nextJSP = request.getContextPath() + "/pages/Distributor/BangKeHoaDonSp.jsp";
  		response.sendRedirect(nextJSP); 
    }
    else  if(action.equals("view") || action.equals("next") || action.equals("prev")){
    	
    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
    	obj.setUserId(userId);
  		obj.init(action);
    	
    	session.setAttribute("obj", obj);
    	response.sendRedirect(request.getContextPath() + "/pages/Center/BangKeHoaDonSp.jsp");
    }
    
    else if(action.equals("search"))
    {	
    	obj.setUserId(userId);
    	session.setAttribute("obj", obj);
  		session.setAttribute("userId", userId);
  		obj.init(action);
  		String nextJSP = "";
  		nextJSP = request.getContextPath() + "/pages/Center/BangKeHoaDonSp.jsp";
  		response.sendRedirect(nextJSP); 
    }
    else
    {
			session.setAttribute("obj", obj);
			String nextJSP = "";
			obj.setMsg("B???n ph???i ch???n kho???ng th???i gian l???y b??o c??o");
			nextJSP = request.getContextPath() + "/pages/Distributor/BangKeHoaDonSp.jsp";
			response.sendRedirect(nextJSP);  
    }
		
	}

	private boolean FillData(Workbook workbook, IBangKeHoaDonSpList obj, String query) throws Exception
  {
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();		
		ResultSet hdRs = db.get(query);
		
		double total_SoLuong=0;
		double total_BVAT=0;
		double total_AVAT=0;
		double total_VAT=0;
		int i = 9;
		int SoTt=1;
		if (hdRs != null) 
		{
			try 
			{
				Cell cell = null;

				while (hdRs.next()) 
				{					
					
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(SoTt++ );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue( hdRs.getInt("n_Row")==1?hdRs.getString("khMa"):"" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("soHoaDon"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getInt("n_Row")==1?hdRs.getString("khTen"):"" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getInt("n_Row")==1?hdRs.getString("khDiaCHI"):"" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getString("spTen"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue( hdRs.getDouble("soluong") );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);
					
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue( hdRs.getDouble("dongia") );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);
					
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue( hdRs.getDouble("BVAT") );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);
					
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue( hdRs.getDouble("VAT") );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);
					
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue( hdRs.getDouble("AVAT") );			
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);
					
					
					total_SoLuong += hdRs.getDouble("soluong");
					total_BVAT += hdRs.getDouble("BVAT");
					total_VAT += hdRs.getDouble("VAT");
					total_AVAT += hdRs.getDouble("AVAT");
					
					++i;					
				}
				
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("T???ng c???ng");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				
				
				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(total_SoLuong );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 3);
				
				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue( "" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 3);
				
				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue( total_BVAT );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 3);
				
				cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(total_VAT  );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 3);
				
				cell = cells.getCell("K" + Integer.toString(i));	cell.setValue( total_AVAT);			
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 3);
				

				if (hdRs != null) hdRs.close();
				
				if(db != null) db.shutDown();
				
				if(i==2){					
					throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
				}
				  
			}catch(Exception ex){
				ex.printStackTrace();
				throw new Exception(ex.getMessage());
			}
		}else{
			return false;
		}		
		return true;
	  
  }

	private void CreateStaticHeader(Workbook workbook, IBangKeHoaDonSpList obj)
  {
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");
			Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	        
	    
	    String tieude = "B???NG K?? H??A ????N C???A M???T M???T H??NG";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	    
	    String message = "";
			cells.setRowHeight(2, 18.0f);
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);   

	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "T??? ng??y : " + obj.getTuNgay() + "" );
	    
	    
	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("B4"); 
	    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "?????n ng??y : " + obj.getDenNgay() + "" );
	 
		
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A5");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ng??y b??o c??o: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A6");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "???????c t???o b???i:  "    );
	    		
		cell = cells.getCell("A8");	cell.setValue("STT");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("B8");	cell.setValue("M?? KH(FAST)");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("C8");	cell.setValue("S??? H??");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("D8");	cell.setValue("T??n KH");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("E8");	cell.setValue("?????a ch???");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("F8");	cell.setValue("S???n ph???m");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("G8");	cell.setValue("S??? l?????ng");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("H8");	cell.setValue("????n gi??");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("I8");	cell.setValue("Th??nh ti???n");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("J8");	cell.setValue("Thu???");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("K8");	cell.setValue("T???ng ti???n thu???");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
	  
  }
}
