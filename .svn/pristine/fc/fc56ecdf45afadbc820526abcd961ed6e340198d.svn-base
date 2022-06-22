package geso.dms.distributor.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
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
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class BCChotSoLuongDieuChinhKeToa extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BCChotSoLuongDieuChinhKeToa() {
        super();
    } 
    
    NumberFormat formatter = new DecimalFormat("#,###,###");
    NumberFormat formatter2 = new DecimalFormat("#,###,###.###");
    
    
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
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String view = request.getParameter("view");
	    if(view == null)
	    	view = "";
	    
	    System.out.println("userid: "+userId+" -- view: "+view);
	    obj = new Stockintransit();
	    obj.setuserId(userId);
	    if(!view.equals("TT"))
	    	obj.getNppInfo();
	    System.out.println("NPPID: "+obj.getnppId());
	    //obj.setLoaiMenu(view);
	    obj.initBCChiPhiKhuyenMai();

	    	
	    //obj.setCongtyId(congtyId);
	    //obj.createRsBC_GiaThanh();
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCChotSoLuongDieuChinhKeToa.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utility util = new Utility();
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    String userId = request.getParameter("userId");
	    String action = request.getParameter("action");
		if (action == null){
			action = "";
		}
		
		String view = request.getParameter("view");
		if(view == null)
		view = "";
		    
		    
	    obj = new Stockintransit();
	    obj.setuserId(userId);
	    
	    //obj.setLoaiMenu(view);
	    if(view.equals("NPP"))
	    	obj.getNppInfo();
		    
	   	
	    String tungay = request.getParameter("tungay");
	    if(tungay == null)
	    	tungay = "";
	    obj.settungay(tungay);
	    
	    String denngay = request.getParameter("denngay");
	    if(denngay == null)
	    	denngay = "";
	    obj.setdenngay(denngay);
	    
	    String vungId = request.getParameter("vungId");
	    if(vungId == null)
	    	vungId = "";
	    obj.setvungId(vungId);
	    
	    String kvId = request.getParameter("kvId");
	    if(kvId == null)
	    	kvId = "";
	    obj.setkhuvucId(kvId);
	    
	    String npp = request.getParameter("nppId");
	    if(npp.equals(""))
	    	npp = null;
	    obj.setnppId(npp);
	    
	    String khId = request.getParameter("khId");
	    if(khId == null || khId.equals(""))
	    	khId = null;
	    obj.setkhId(khId);
	    
		session.setAttribute("obj", obj);
	    
		if( action.equals("taobaocao"))
		{
	    	try 
	    	{	
	    		OutputStream out = response.getOutputStream(); 
	    		
				response.setContentType("application/xlsm");
	    		response.setHeader("Content-Disposition", "attachment; filename=BCChotSoLuongDieuChinhKeToa.xlsm");
	
				TongHopChiPhiSX(out, obj);
			} 
	    	catch (Exception e) 
	    	{ 
	    		e.printStackTrace();
	    		System.out.println("Exception: " + e.getMessage()); 
	    	}
		}
		else
		{
			obj.initBCChiPhiKhuyenMai();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BCChotSoLuongDieuChinhKeToa.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	
	private void TongHopChiPhiSX(OutputStream out, IStockintransit obj) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCChotSoLuongDieuChinhKeToa.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		BCTongHopChiPhiSX(workbook, obj);

		workbook.save(out);
		fstream.close();
		
    }

	private void BCTongHopChiPhiSX(Workbook workbook, IStockintransit obj) 
	{ 
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    //worksheet.setName("Sheet1");
	    Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setName("Times New Roman");
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);

	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 
	    
	    //create data
	    dbutils db = new dbutils();

	    cell = cells.getCell("A3");
	    cell.setValue("Từ ngày: " + obj.gettungay() + " - Đến ngày: " + obj.getdenngay() );
	    
	    cell = cells.getCell("A4");
	    cell.setValue( "Ngày tạo " + this.getDateTime());
	    String condition = "";
	    String condition_chitiet = "";
	    
	    Style BZStyle = cells.getCell("BZ6").getStyle(); //Set color white ẩn để pivot
	    
	    cell = cells.getCell("AA1"); cell.setStyle(BZStyle); cell.setValue("MADC");
	    cell = cells.getCell("AB1"); cell.setStyle(BZStyle); cell.setValue("DDKDMA");
	    cell = cells.getCell("AC1"); cell.setStyle(BZStyle); cell.setValue("DDKDTEN");
	    cell = cells.getCell("AD1"); cell.setStyle(BZStyle); cell.setValue("KHMA");
	    cell = cells.getCell("AE1"); cell.setStyle(BZStyle); cell.setValue("KHTEN");
	    cell = cells.getCell("AF1"); cell.setStyle(BZStyle); cell.setValue("BSMA");
	    cell = cells.getCell("AG1"); cell.setStyle(BZStyle); cell.setValue("BSTEN");
	    cell = cells.getCell("AH1"); cell.setStyle(BZStyle); cell.setValue("NGAYCHOTDC");
	    cell = cells.getCell("AI1"); cell.setStyle(BZStyle); cell.setValue("SOTIENCK");
	    cell = cells.getCell("AJ1"); cell.setStyle(BZStyle); cell.setValue("NGAYCHITRA");
	    cell = cells.getCell("AK1"); cell.setStyle(BZStyle); cell.setValue("SOTIENCHITRA");
	    
	    if (obj.gettungay() != null) { 
		    if (obj.gettungay().length() > 0) {
		    	condition += "\n and b.NGAY >= convert(varchar,'"+obj.gettungay()+"',21)";
		    }
	    }
	    
	    if (obj.getdenngay() != null) {
		    if (obj.getdenngay().length() > 0) {
		    	condition += "\n and b.NGAY <= convert(varchar,'"+obj.getdenngay()+"',21)";
		    }
	    }
	    
	    if (obj.getvungId() != null) {
		    if (obj.getvungId().length() > 0) {
		    	condition += "\n and v.pk_seq = "+obj.getvungId();
		    	condition_chitiet += "\n and v.pk_seq = "+obj.getvungId();
		    }
	    }
	    
	    if (obj.getkhuvucId() != null) {
		    if (obj.getkhuvucId().length() > 0) {
		    	condition += "\n and kv.pk_seq = "+obj.getkhuvucId()+") ";
		    	condition_chitiet += "\n and kv.pk_seq = "+obj.getkhuvucId();
		    }
	    }
	    
	    
	    if (obj.getnppId() != null) {
		    if (obj.getnppId().length()>0)
		    {
		    	condition += "\n and a.npp_fk ="+obj.getnppId();
		    	condition_chitiet += "\n and a.npp_fk ="+obj.getnppId();
		    }
	    }
	    
	    if (obj.getkhId() != null && obj.getkhId().length() > 0) {
	    	condition += "\n and kh.pk_seq = "+obj.getkhId();
	    	condition_chitiet += "\n and kh.pk_seq = "+obj.getkhId();
	    }
	    
	    System.out.println("NPP: "+obj.getnppId());
	    //if  (obj.getnppId().length() > 0)
	    //	condition = " and npp_fk in "+u.quyen_npp(obj.getuserId())+"  ";
	    
	    String query = "select a.pk_seq MADC, ddkd.mafast DDKDMA,ddkd.ten DDKDTEN, kh.mafast KHMA, kh.ten KHTEN, bs.ma BSMA, bs.ten BSTEN, b.ngay NGAYCHOTDC " +
	    		"\n ,isnull(a.tongtien,0) SOTIENCK,b.ngay NGAYCHITRA, b.sotien SOTIENCHITRA " +
				"\n from  " +
				"\n DieuChinhDonHangBacSi a inner join DieuChinhDonHangBacSi_ThanhToan b on a.pk_seq =  b.DieuChinhDonHangBacSi_fk " +
				"\n inner join daidienkinhdoanh ddkd on ddkd.pk_seq = a.ddkd_fk " +
				"\n inner join nhaphanphoi npp on npp.pk_seq = a.npp_fk " +
				"\n inner join khachhang kh on kh.pk_seq = a.khachhang_fk " +
				"\n inner join bacsi bs on bs.pk_seq = a.bacsi_fk " +
				"\n inner join khuvuc kv on kv.pk_seq = npp.khuvuc_fk " +
				"\n where 1=1 "+condition;

	    
	    System.out.println("Query BC: " + query);
	    ResultSet rsChiphi = db.get(query);
	    
		try 
		{
			//int rowIndex = 6;
			//int colIndex = 0;
			int colIndex = 2;
			int stt = 1;
			
			//BZStyle.setTextWrapped(true);
			Style BXStyle = cells.getCell("BX6").getStyle();
			
			if(rsChiphi != null)
			{
				double totalSL = 0;
				double totalTT = 0;
				double totalKM = 0;
				double totalTong = 0;
				
				while( rsChiphi.next() )
				{	
					String MADC = rsChiphi.getString("MADC");
					String DDKDMA = rsChiphi.getString("DDKDMA");
					String DDKDTEN = rsChiphi.getString("DDKDTEN");
					String KHMA = rsChiphi.getString("KHMA");
					String KHTEN = rsChiphi.getString("KHTEN");
					String BSMA = rsChiphi.getString("BSMA");
					String BSTEN = rsChiphi.getString("BSTEN");
					String NGAYCHOTDC = rsChiphi.getString("NGAYCHOTDC");
					Double SOTIENCK = rsChiphi.getDouble("SOTIENCK");
					String NGAYCHITRA = rsChiphi.getString("NGAYCHITRA");
					Double SOTIENCHITRA = rsChiphi.getDouble("SOTIENCHITRA");
					
					 cell = cells.getCell("AA1"); cell.setStyle(BZStyle); cell.setValue("MADC");
					    cell = cells.getCell("AB1"); cell.setStyle(BZStyle); cell.setValue("DDKDMA");
					    cell = cells.getCell("AC1"); cell.setStyle(BZStyle); cell.setValue("DDKDTEN");
					    cell = cells.getCell("AD1"); cell.setStyle(BZStyle); cell.setValue("KHMA");
					    cell = cells.getCell("AE1"); cell.setStyle(BZStyle); cell.setValue("KHTEN");
					    cell = cells.getCell("AF1"); cell.setStyle(BZStyle); cell.setValue("BSMA");
					    cell = cells.getCell("AG1"); cell.setStyle(BZStyle); cell.setValue("BSTEN");
					    cell = cells.getCell("AH1"); cell.setStyle(BZStyle); cell.setValue("NGAYCHOTDC");
					    cell = cells.getCell("AI1"); cell.setStyle(BZStyle); cell.setValue("SOTIENCK");
					    cell = cells.getCell("AJ1"); cell.setStyle(BZStyle); cell.setValue("NGAYCHITRA");
					    cell = cells.getCell("AK1"); cell.setStyle(BZStyle); cell.setValue("SOTIENCHITRA");
					
					cell = cells.getCell( "AA" + colIndex );     cell.setStyle(BZStyle); cell.setValue( MADC );
					cell = cells.getCell( "AB"+ colIndex ); cell.setStyle(BZStyle); cell.setValue( DDKDMA );	
					cell = cells.getCell( "AC" + colIndex ); cell.setStyle(BZStyle); cell.setValue( DDKDTEN );
					cell = cells.getCell( "AD" + colIndex ); cell.setStyle(BZStyle); cell.setValue( KHMA );
					cell = cells.getCell( "AE" + colIndex ); cell.setStyle(BZStyle); cell.setValue( KHTEN );
					cell = cells.getCell( "AF" + colIndex ); cell.setStyle(BZStyle); cell.setValue( BSMA );
					cell = cells.getCell( "AG" + colIndex ); cell.setStyle(BZStyle); cell.setValue( BSTEN );
					cell = cells.getCell( "AH" + colIndex ); cell.setStyle(BZStyle); cell.setValue( NGAYCHOTDC );
					cell = cells.getCell( "AI" + colIndex ); cell.setStyle(BZStyle); cell.setValue( SOTIENCK );
					this.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 37);
					cell = cells.getCell( "AJ" + colIndex ); cell.setStyle(BZStyle); cell.setValue( NGAYCHITRA );
					cell = cells.getCell( "AK" + colIndex ); cell.setStyle(BZStyle); cell.setValue( SOTIENCHITRA );
					this.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 37);
					
					stt++;
					//rowIndex ++;
					colIndex++;
				}
				rsChiphi.close();		
				
			}		
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	    
	    db.shutDown();	    
	}
	
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void setCellBackground(Cell cell,Color color,int borderLineType,boolean bold,int decimal){
		Style style = cell.getStyle();
		style.setColor(color);
		style.setBorderLine(BorderType.BOTTOM, borderLineType);
		style.setBorderLine(BorderType.LEFT, borderLineType);
		style.setBorderLine(BorderType.TOP, borderLineType);
		style.setBorderLine(BorderType.RIGHT, borderLineType);
		style.setNumber(decimal);
		
		
		
		Font font = new Font();
		font.setName("Times New Roman");
		font.setColor(Color.BLACK);
		font.setBold(bold);
		style.setFont(font);
		font.setSize(11);
		
		cell.setStyle(style);		
	}
	

}
