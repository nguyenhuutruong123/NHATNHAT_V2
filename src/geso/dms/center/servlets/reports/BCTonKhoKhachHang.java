package geso.dms.center.servlets.reports;

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

public class BCTonKhoKhachHang extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BCTonKhoKhachHang() {
        super();
    } 
    
    NumberFormat formatter = new DecimalFormat("#,###,###");
    NumberFormat formatter2 = new DecimalFormat("#,###,###.###");
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj = new Stockintransit();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    //String congtyId = (String)session.getAttribute("congtyId");

	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    obj.setuserId(userId);
	    obj.initBCTonKhoKH();
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/BCTonKhoKhachHang.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj = new Stockintransit();
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
		    
	   	
	    String tungay = request.getParameter("tungay");
	    if(tungay == null)
	    	tungay = "";
	    obj.settungay(tungay);
	    
	    String denngay = request.getParameter("denngay");
	    if(denngay == null)
	    	denngay = "";
	    obj.setdenngay(denngay);
	    
	    String npp = request.getParameter("nppId");
	    if(npp == null)
	    	npp = "";
	    obj.setNppId_BCTKKH(npp);
	    
	    String ddkd = request.getParameter("ddkdId");
	    if(ddkd == null)
	    	ddkd = "";
	    obj.setDdkdId_BCTKKH(ddkd);
	    
		session.setAttribute("obj", obj);
	    
		if( action.equals("taobaocao"))
		{
	    	try 
	    	{	
	    		OutputStream out = response.getOutputStream(); 
	    		
				response.setContentType("application/xlsm");
	    		response.setHeader("Content-Disposition", "attachment; filename=BCTonKhoKhachHang.xlsm");
	
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
			String nextJSP = request.getContextPath() + "/pages/Center/BCTonKhoKhachHang.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	
	private void TongHopChiPhiSX(OutputStream out, IStockintransit obj) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BaoCaoTonKhoKhachHang.xlsm");

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
	    
	    String condition_tuden = "";
	    if (obj.gettungay().length() > 0 && obj.getdenngay().length() > 0)
	    {
	    	condition_tuden = "where a.ngay >= '"+obj.gettungay() +"' and a.ngay <='"+obj.getdenngay()+"' ";
	    }
	    
	    String condition_npp = "";
	    if  (obj.getNppId_BCTKKH().length() > 0) {
	    	condition_npp = " and a.npp_fk = "+obj.getNppId_BCTKKH();
	    }
	    
	    String condition_ddkd = "";
	    if  (obj.getDdkdId_BCTKKH().length() > 0) {
	    	condition_ddkd = " and a.ddkd_fk = "+obj.getDdkdId_BCTKKH();
	    }
	    
	    
	   /* String query = " select a.ngay ngaykiemton, d.mafast manvbh, d.ten tennvbh, isnull(b.makhthamkhao,'') makhthamkhao, b.mafast makh, \r\n" + 
	    		" b.ten tenkh, b.diachikinhdoanh diachikinhdoanhkh, e.ma masp, e.ten tensp, g.diengiai dvt, soluong soluongton \r\n" + 
	    		" from TONKHO_KHACHHANG a left join khachhang b on a.kh_fk = b.pk_seq \r\n" + 
	    		" left join nhaphanphoi c on c.pk_seq = a.npp_fk \r\n" + 
	    		" left join daidienkinhdoanh d on d.pk_seq = a.ddkd_fk \r\n" + 
	    		" left join sanpham e on e.pk_seq = a.sanpham_fk \r\n" + 
	    		" left join donvidoluong g on g.pk_seq = e.dvdl_fk "
	    		+ condition_tuden + " "+ condition_npp +" "+ condition_ddkd +
	    		" order by a.ngay desc";*/
	    
	    String query = " select a.ngay ngaykiemton, d.mafast manvbh, d.ten tennvbh, b.pk_seq makhthamkhao, b.mafast makh, \r\n" + 
		" b.ten tenkh, '' diachikinhdoanhkh, e.ma masp, e.ten tensp, g.diengiai dvt, soluong soluongton \r\n" + 
		" from TONKHO_KHACHHANG a left join khachhang b on a.kh_fk = b.pk_seq \r\n" + 
		" left join nhaphanphoi c on c.pk_seq = a.npp_fk \r\n" + 
		" left join daidienkinhdoanh d on d.pk_seq = a.ddkd_fk \r\n" + 
		" left join sanpham e on e.pk_seq = a.sanpham_fk \r\n" + 
		" left join donvidoluong g on g.pk_seq = e.dvdl_fk "
		+ condition_tuden + " "+ condition_npp +" "+ condition_ddkd +
		" order by a.ngay desc";
	    
	    System.out.println("Câu truy vấn: " + query);
	    ResultSet rsChiphi = db.get(query);
	    String ngaykiemton, manvbh, tennvbh, makhthamkhao, makh, tenkh, diachikinhdoanhkh, masp, tensp, dvt, soluongton;
	      	    
		try 
		{
			int rowIndex = 7;
			int colIndex = 0;
			
			Style B1Style = cells.getCell("Y3").getStyle();
			B1Style.setTextWrapped(true);
			
			Style E1Style = cells.getCell("Y3").getStyle();
			E1Style.setTextWrapped(true);
			
			Style F1Style = cells.getCell("Y3").getStyle();
			F1Style.setTextWrapped(true);
			
			if(rsChiphi != null)
			{
				while( rsChiphi.next() )
				{
					//int stt = rsChiphi.getInt("stt");
					ngaykiemton = rsChiphi.getString("ngaykiemton");
					manvbh = rsChiphi.getString("manvbh");
					tennvbh = rsChiphi.getString("tennvbh");
					makh = rsChiphi.getString("makh");
					makhthamkhao = rsChiphi.getString("makhthamkhao");
					tenkh = rsChiphi.getString("tenkh");
					diachikinhdoanhkh = rsChiphi.getString("diachikinhdoanhkh");
					masp = rsChiphi.getString("masp");
					tensp = rsChiphi.getString("tensp");
					dvt = rsChiphi.getString("dvt");
					soluongton = rsChiphi.getString("soluongton");	
					
					cell = cells.getCell( rowIndex, colIndex );     cell.setStyle(B1Style); cell.setValue( ngaykiemton );
					cell = cells.getCell( rowIndex, colIndex + 1 ); cell.setStyle(B1Style); cell.setValue( manvbh );
					cell = cells.getCell( rowIndex, colIndex + 2 ); cell.setStyle(B1Style); cell.setValue( tennvbh );
					
					cell = cells.getCell( rowIndex, colIndex + 3 ); cell.setStyle(E1Style); cell.setValue( makhthamkhao );
					cell = cells.getCell( rowIndex, colIndex + 4 ); cell.setStyle(F1Style); cell.setValue( makh );
					
					cell = cells.getCell( rowIndex, colIndex + 5 ); cell.setStyle(E1Style); cell.setValue( tenkh );
					cell = cells.getCell( rowIndex, colIndex + 6 ); cell.setStyle(F1Style); cell.setValue( diachikinhdoanhkh );
					
					cell = cells.getCell( rowIndex, colIndex + 7 ); cell.setStyle(E1Style); cell.setValue( masp );
					cell = cells.getCell( rowIndex, colIndex + 8 ); cell.setStyle(F1Style); cell.setValue( tensp );
					cell = cells.getCell( rowIndex, colIndex + 9 ); cell.setStyle(F1Style); cell.setValue( dvt );
					cell = cells.getCell( rowIndex, colIndex + 10 ); cell.setStyle(F1Style); cell.setValue( soluongton );
						
					rowIndex ++;
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

}
