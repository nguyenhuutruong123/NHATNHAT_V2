package geso.dms.distributor.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.beans.report.IBangKeHoaDonSpList;
import geso.dms.center.beans.report.imp.BangKeHoaDonSpList;
import geso.dms.distributor.beans.report.IBChhangnhapList;
import geso.dms.distributor.beans.report.imp.BcHangnhapList;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;

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

@WebServlet("/BCHangnhapSvl")
public class BCHangnhapSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public BCHangnhapSvl()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IBChhangnhapList obj;
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
		
		obj = new BcHangnhapList();
		obj.setUserId(userId);
		obj.setView("TT");
		String nextJSP = "";
		obj.init("");
		
		
		nextJSP = request.getContextPath() + "/pages/Distributor/BcHangnhap.jsp";
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
    
    IBChhangnhapList obj = new BcHangnhapList();
    obj.setUserId(userId);
    
    obj.setView("TT");
    
    String tungay =util.antiSQLInspection(request.getParameter("Sdays")==null?"": request.getParameter("Sdays"));
    obj.setTuNgay(tungay);
    
    String denngay = util.antiSQLInspection(request.getParameter("Edays")==null?"": request.getParameter("Edays"));
    obj.setDenNgay(denngay);
    
    String vungId = util.antiSQLInspection(request.getParameter("vungId")==null?"": request.getParameter("vungId"));
    obj.setVungId(vungId);

    String kbhId = util.antiSQLInspection(request.getParameter("kbhId")==null?"": request.getParameter("kbhId"));
    obj.setKbhId(kbhId);    
    

    String ttId = util.antiSQLInspection(request.getParameter("ttId")==null?"": request.getParameter("ttId"));
    obj.setTtId(ttId);   
    
    String nhomId = util.antiSQLInspection(request.getParameter("nhomId")==null?"": request.getParameter("nhomId"));
    obj.setNhomId(nhomId);
    
    
    String khId =util.antiSQLInspection( request.getParameter("khId")==null?"": request.getParameter("khId"));
    obj.setKhId(khId);
    
    String ddkdId =  util.antiSQLInspection(request.getParameter("ddkdId")==null?"": request.getParameter("ddkdId"));
    obj.setDdkdId(ddkdId);
    
    String spId =util.antiSQLInspection(request.getParameter("spId")==null?"": request.getParameter("spId"));
    obj.setSpId(spId);
    
    
    String nppId =util.antiSQLInspection(request.getParameter("nppId")==null?"": request.getParameter("nppId"));
    obj.setNppId(nppId);
    
    String khoid =util.antiSQLInspection(request.getParameter("khoid")==null?"": request.getParameter("khoid"));
    obj.setKhoid(khoid);
    
    
    String loaihoadon =util.antiSQLInspection(request.getParameter("loaidonhang")==null?"": request.getParameter("loaidonhang"));
    obj.setLoaiHoaDon(loaihoadon);

    //obj.setMucCN_DT(util.antiSQLInspection(request.getParameter("cndt")) != null ? util.antiSQLInspection(request.getParameter("cndt")) : "");
    obj.setMucCN_DT("0");
		
	obj.setMuc_KhachHang(util.antiSQLInspection(request.getParameter("kh")) != null ? util.antiSQLInspection(request.getParameter("kh")) : "");
	
	obj.setLaynk(util.antiSQLInspection(request.getParameter("klaynhomnk")) != null ? util.antiSQLInspection(request.getParameter("klaynhomnk")) : "0");
	System.out.println("nhom sp la"+ (request.getParameter("klaynhomnk")));
    
    obj.setAction(action);
    
    System.out.println("___ATION "+action+"___"+nppId);
    
    
    if (action.equals("excel")  )
    {
    	try
	    { 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=Baocaohangnhapkho.xlsm");
				FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BChangnhapkho.xlsm");
				Workbook workbook = new Workbook();
				workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				
				obj.setUserId(userId);
				obj.init("");
				CreateStaticHeader(workbook, obj);
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
  		nextJSP = request.getContextPath() + "/pages/Distributor/BcHangnhap.jsp";
  		response.sendRedirect(nextJSP); 
    }
    else  if(action.equals("view") || action.equals("next") || action.equals("prev")){
    	
    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
    	obj.setUserId(userId);
    	obj.init("");
    	
    	session.setAttribute("obj", obj);
    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/BcHangnhap.jsp");
    }
    
    else if(action.equals("search"))
    {	
    	obj.setUserId(userId);
    	session.setAttribute("obj", obj);
  		session.setAttribute("userId", userId);
  		obj.init("");
  		String nextJSP = "";
  		nextJSP = request.getContextPath() + "/pages/Distributor/BcHangnhap.jsp";
  		response.sendRedirect(nextJSP); 
    }
    else
    {
			session.setAttribute("obj", obj);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Distributor/BcHangnhap.jsp";
			obj.init("");
			response.sendRedirect(nextJSP);  
    }
		
	}
	
	private boolean FillData(Workbook workbook, IBChhangnhapList obj, String query) throws Exception
  {
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();		
		ResultSet hdRs = db.get(query);
		Style style;
		
		double DonGia_AVG=0;
		double total_BVAT=0;
		double total_AVAT=0;
		double total_VAT=0;
		int i = 11;
		int SoTt=1;
		if (hdRs != null) 
		{
			try 
			{
				Cell cell = null;

				while (hdRs.next()) 
				{					
					DonGia_AVG=0;
					
					if(hdRs.getDouble("SoLuong")>0)
						DonGia_AVG=hdRs.getDouble("BVAT")/hdRs.getDouble("SoLuong");
					
					
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(SoTt++ );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
				
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(hdRs.getString("spMa"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("spTen"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("spDonVi"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SoLuong"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(DonGia_AVG);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					/*style=cell.getStyle();
					style.setCustom("#,##0.0000");
					cell.setStyle(style);*/
					
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(hdRs.getDouble("BVAT"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(hdRs.getDouble("VAT"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(hdRs.getDouble("AVAT"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					
					
					
					total_BVAT += hdRs.getDouble("BVAT");
					total_VAT += hdRs.getDouble("VAT");
					total_AVAT += hdRs.getDouble("AVAT");
					
					++i;					
				}
				
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("Tổng cộng");
				ReportAPI.mergeCells(worksheet, i-1,i-1, 0, 5);
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
				
				
				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue( total_BVAT );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);
				
				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(total_VAT  );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);
				
				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue( total_AVAT);			
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);
				

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

	private void CreateStaticHeader(Workbook workbook, IBChhangnhapList obj) throws SQLException
  {
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");
			Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu
	    String query="select TEN,DIACHI,isnull(DIENTHOAI,isnull(FAX,'')) as dienthoai from nhaphanphoi where pk_seq= "+obj.getNppId();
	    System.out.println("info" +query);
	    dbutils db=new dbutils();
	    ResultSet rs=db.get(query);
	    rs.next();
	    ReportAPI.getCellStyle(cell,Color.BLACK, true, 9, rs.getString("TEN"));
	    
	    cell = cells.getCell("A2");
		ReportAPI.getCellStyle(cell, Color.BLACK, true, 9, rs.getString("diachi"));   
		
		 cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.BLACK, true, 9, rs.getString("dienthoai"));   
			rs.close();
			db.shutDown();
			
	   /* String tieude = "DOANH THU THEO SẢN PHẨM";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	    */
	    String message = "";
			cells.setRowHeight(2, 18.0f);
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);   

			
			if(!obj.getKhoid().equals(""))
			{
				  query="select ten from kho where pk_seq= "+obj.getKhoid();
				    System.out.println("info" +query);
				     db=new dbutils();
				     rs=db.get(query);
				    rs.next();
				    
			 cells.setRowHeight(4, 18.0f);
			    cell = cells.getCell("A6");
			    ReportAPI.getCellStyle(cell, Color.BLACK, true, 9, "kho: "+ rs.getString("ten") );
			    rs.close();
				db.shutDown();
				
			}	
			
	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("A7");
	    ReportAPI.getCellStyle(cell,Color.BLACK,true, 9, "Từ ngày : " + obj.getTuNgay() + " Đến ngày : " + obj.getDenNgay() + "" );
		
	   /* cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A5");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    */
/*	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A6");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  "    );*/
	    		
	/*	cell = cells.getCell("A8");	cell.setValue("STT");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		

		cell = cells.getCell("B8");	cell.setValue("MÃ VẬT TƯ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("C8");	cell.setValue("TÊN VẬT TƯ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("D8");	cell.setValue("ĐVT");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("E8");	cell.setValue("SỐ LƯỢNG");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("F8");	cell.setValue("ĐƠN GIÁ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("G8");	cell.setValue("DOANH THU");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("H8");	cell.setValue("THUẾ GTGT");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell("I8");	cell.setValue("TỔNG TIỀN");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);*/
		
		
		
  }

}
