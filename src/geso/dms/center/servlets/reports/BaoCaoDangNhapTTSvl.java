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

public class BaoCaoDangNhapTTSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BaoCaoDangNhapTTSvl() {
        super();
        
    }  
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utility util=new Utility();
    	  
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");	
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();	  
		String querystring=request.getQueryString();
		String kettoan=util.antiSQLInspection(request.getParameter("ketoan"));
		String userId = util.getUserId(querystring);
		if(userId==null) {
			obj.setuserId("");
		}
		obj.setuserId(userId);
		String userTen = (String)session.getAttribute("userTen");
		if(userTen==null) {
			obj.setuserTen("");
		}
		obj.setuserTen(userTen);
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("util", util);
		session.setAttribute("userTen", userTen);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/BaoCaoDangNhapTT.jsp";
		if(kettoan!=null)
		{
			 nextJSP = request.getContextPath() + "/pages/Center/BaoCaoKeToan.jsp";
		}
		
		
		response.sendRedirect(nextJSP);
 	}
	
 	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 		Utility util=new Utility();
 		  
 		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();		
 		OutputStream out = response.getOutputStream(); 
 		IStockintransit obj = new Stockintransit();	
 		String nextJSP = request.getContextPath() + "/pages/Center/BaoCaoDangNhapTT.jsp";
		try
		    {
			String userId = (String) session.getAttribute("userId");
			String userTen = (String) session.getAttribute("userTen");			
			
			obj.setuserId(userId == null ? "" : userId);
			obj.setuserTen(userTen == null ? "" : userTen);
			obj.settungay(util.antiSQLInspection(request.getParameter("Sdays"))==null? "":util.antiSQLInspection(request.getParameter("Sdays")));			
			obj.setdenngay(util.antiSQLInspection(request.getParameter("Edays"))==null? "":util.antiSQLInspection(request.getParameter("Edays")));
			obj.setvungId(util.antiSQLInspection(request.getParameter("mien")) == null ? "" : util.antiSQLInspection(request.getParameter("mien")));
			obj.setTinhthanhid(util.antiSQLInspection(request.getParameter("tinh")) == null ? "" : util.antiSQLInspection(request.getParameter("tinh")));
			
			String action = util.antiSQLInspection(request.getParameter("action"));
			String kettoan=util.antiSQLInspection(request.getParameter("ketoan"));
			System.out.println("ke toang la "+kettoan);
			if(kettoan!=null)
			{
				 nextJSP = request.getContextPath() + "/pages/Center/BaoCaoKeToan.jsp";
			}
			
			if (action.equals("Taomoi")) {
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BaoCaoDangNhap"+util.setTieuDe(obj)+".xlsm");
				CreatePivotTable(out,obj);
				return;
			}	
			
			if (action.equals("bc1")) {
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=TTDonhangBanNPP"+util.setTieuDe(obj)+".xlsm");
				CreatePivotTable1(out,obj);
				return;
			}		
			
			if (action.equals("bc2")) {
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=TTDonHangBanKH"+util.setTieuDe(obj)+".xlsm");
				CreatePivotTable2(out,obj);
				return;
			}	
			
			if (action.equals("bc3")) {
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=HangHoa.xlsm"+util.setTieuDe(obj)+".xlsm");
				CreatePivotTable3(out,obj);
				return;
			}	
			
			
		}
		catch (Exception ex) {
			obj.setMsg(ex.getMessage());
		}
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("util", util);
		session.setAttribute("userTen", obj.getuserTen());
		session.setAttribute("userId", obj.getuserId());
		response.sendRedirect(nextJSP);
 	}

 	private void CreatePivotTable(OutputStream out,IStockintransit obj) throws Exception
    {       
 				
 		String fstreamstr = getServletContext().getInitParameter("path") + "\\DangNhapTT.xlsm";
 		FileInputStream fstream = new FileInputStream(fstreamstr);
 		
 		Workbook workbook = new Workbook();
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		CreateStaticHeader(workbook,obj);	     
	    CreateStaticData(workbook,obj);
	    workbook.save(out);
	    fstream.close();
    }
 	
 	private void CreatePivotTable1(OutputStream out,IStockintransit obj) throws Exception
    {       
 				
 		String fstreamstr = getServletContext().getInitParameter("path") + "\\TTdonhangbannpp.xlsm";
 		FileInputStream fstream = new FileInputStream(fstreamstr);
 		
 		Workbook workbook = new Workbook();
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		CreateStaticHeader(workbook,obj);	     
	    //CreateStaticData(workbook,obj);
	    workbook.save(out);
	    fstream.close();
    }
 	private void CreatePivotTable2(OutputStream out,IStockintransit obj) throws Exception
    {       
 				
 		String fstreamstr = getServletContext().getInitParameter("path") + "\\DonhangBanKH.xlsm";
 		FileInputStream fstream = new FileInputStream(fstreamstr);
 		
 		Workbook workbook = new Workbook();
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		CreateStaticHeader(workbook,obj);	     
	    CreateStaticData(workbook,obj);
	    workbook.save(out);
	    fstream.close();
    }
 	
 	private void CreatePivotTable3(OutputStream out,IStockintransit obj) throws Exception
    {       
 				
 		String fstreamstr = getServletContext().getInitParameter("path") + "\\HangHoa.xlsm";
 		FileInputStream fstream = new FileInputStream(fstreamstr);
 		
 		Workbook workbook = new Workbook();
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		CreateStaticHeader(workbook,obj);	     
	    CreateStaticData3(workbook,obj);
	    workbook.save(out);
	    fstream.close();
    }
 	
	private void CreateStaticHeader(Workbook workbook, IStockintransit obj) {
 		
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
	    
	
	  /*  cells.setRowHeight(2, 18.0f);
	    cell = cells.getCell("A3"); 
	    getCellStyle(workbook,"A3",Color.NAVY,true,10);	    
		cell.setValue("Từ ngày: " + obj.gettungay());
	    
	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("B3"); getCellStyle(workbook,"B3",Color.NAVY,true,9);	        
		cell.setValue("Đến ngày: " + obj.getdenngay());
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A4");getCellStyle(workbook,"A4",Color.NAVY,true,9);
		cell.setValue("Ngày báo cáo: " + this.getDate());
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A5");getCellStyle(workbook,"A5",Color.NAVY,true,9);
		cell.setValue("Được tạo bởi:  " + obj.getuserTen());

		cell = cells.getCell("AA1"); cell.setValue("Mien");
		cell = cells.getCell("AB1"); cell.setValue("Chi nhanh doi tac");
		cell = cells.getCell("AC1"); cell.setValue("Tai khoan");
		cell = cells.getCell("AD1"); cell.setValue("Ho ten");
		cell = cells.getCell("AE1"); cell.setValue("Ngay");
		cell = cells.getCell("AF1"); cell.setValue("Thoi gian dang nhap");
		cell = cells.getCell("AG1"); cell.setValue("Thoi gian dang xuat");*/
		

	}
 	private void CreateStaticData(Workbook workbook,IStockintransit obj) throws Exception
 	{
 		Worksheets worksheets = workbook.getWorksheets();
 	    Worksheet worksheet = worksheets.getSheet(0);
 	    Cells cells = worksheet.getCells();
 	    dbutils db = new dbutils();
 	    Utility Ult = new  Utility();

		String chuoi="";
 	    if(obj.getvungId().length()>0)
 	    	chuoi=chuoi +"and v.PK_SEQ='"+obj.getvungId()+"' ";
 	    if(obj.getTinhthanhid().length()>0)
 	    	chuoi=chuoi + " and tp.PK_SEQ='"+obj.getTinhthanhid()+"'";
 	    
 	    String sql = "";
 	    
 	 
 	    
 	    if (obj.gettungay() != null && obj.gettungay().trim().length() > 0 && obj.getdenngay() != null && obj.getdenngay().trim().length() > 0)
 	    
		sql = "select a.pk_seq,a.ngaynhap,b.mafast, case when a.trangthai = 0 then N'Chưa chốt' when a.trangthai = 1 then N'Đã chốt' else N'Đã huỷ' end as trangthai, case when d.trangthai = 2 then N'Đã xuất HĐ' else N'Chưa xuất HĐ' end as trangthaiHD, "+
					"\n	b.diachigiaohang diachigh,tt.ten tten,qh.ten qhten,b.PHUONGXA,a.ghichu " +
					"\n	from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq "+
					"\n	left join hoadon_ddh c on c.ddh_fk = a.pk_seq "+
					"\n	left join hoadon d on d.pk_seq = c.hoadon_fk "+
					"\n	inner join tinhthanh tt on b.tinhthanh_fk = tt.pk_seq "+
					"\n	inner join quanhuyen qh on tt.pk_seq = qh.TINHTHANH_FK and b.quanhuyen_fk = qh.pk_seq "+	
					"\n inner join nhaphanphoi npp on npp.pk_seq = a.npp_fk " +
					" where  npp.loainpp = 0 and '"+obj.gettungay()+"' <= a.ngaynhap <= '"+obj.getdenngay()+"' " +chuoi;
 	    else
 	    	sql = "select a.pk_seq,a.ngaynhap,b.mafast, case when a.trangthai = 0 then N'Chưa chốt' when a.trangthai = 1 then N'Đã chốt' else N'Đã huỷ' end as trangthai, case when d.trangthai = 2 then N'Đã xuất HĐ' else N'Chưa xuất HĐ' end as trangthaiHD, "+
			"\n	b.diachigiaohang diachigh,tt.ten tten,qh.ten qhten,b.PHUONGXA,a.ghichu " +
			"\n	from donhang a inner join khachhang b on a.khachhang_fk = b.pk_seq "+
			"\n	left join hoadon_ddh c on c.ddh_fk = a.pk_seq "+
			"\n	left join hoadon d on d.pk_seq = c.hoadon_fk "+
			"\n	inner join tinhthanh tt on b.tinhthanh_fk = tt.pk_seq "+
			"\n	inner join quanhuyen qh on tt.pk_seq = qh.TINHTHANH_FK and b.quanhuyen_fk = qh.pk_seq "+
			"\n inner join nhaphanphoi npp on npp.pk_seq = a.npp_fk " +
			"\n where npp.loainpp = 0";

 	    System.out.println("[BaoCaoDangNhapTTSvl.CreateStaticData] sql = " + sql);
 	    ResultSet rs = db.get(sql);
 	    int i = 7; 	     	    
 		if(rs!=null)
 		{
 			try 
 			{
 				cells.setColumnWidth(0, 15.0f);
 				cells.setColumnWidth(1, 15.0f);
 				cells.setColumnWidth(2, 15.0f);
 				cells.setColumnWidth(3, 15.0f);
 				cells.setColumnWidth(4, 15.0f);
 				cells.setColumnWidth(5, 15.0f);
 				cells.setColumnWidth(6, 15.0f);
 				cells.setColumnWidth(7, 15.0f);
 				cells.setColumnWidth(8, 15.0f);
 				cells.setColumnWidth(9, 15.0f);
 				
 				Cell cell = null;
 				Style style;
				String mien, chinhanh, taikhoan, hoten, ngaybd, ngaykt,ngay;
				String madh, ngaynhap, mafast, trangthai, trangthaihd, diachigh, tten, qhten, phuongxa, ghichu;
				while (rs.next()) 
				{
					madh = rs.getString("pk_seq");
					ngaynhap = rs.getString("ngaynhap");
					mafast = rs.getString("mafast");
					trangthai = rs.getString("trangthai");
					trangthaihd = rs.getString("trangthaihd");
					diachigh = rs.getString("diachigh");
					tten = rs.getString("tten");
					qhten = rs.getString("qhten");
					phuongxa = rs.getString("phuongxa");
					ghichu = rs.getString("ghichu");

					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(madh);
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(ngaynhap);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(mafast);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(trangthai);
					cell = cells.getCell("L" + Integer.toString(i));	cell.setValue(trangthaihd);
					cell = cells.getCell("T" + Integer.toString(i));	cell.setValue(diachigh);
					cell = cells.getCell("W" + Integer.toString(i));	cell.setValue(tten);
					cell = cells.getCell("X" + Integer.toString(i));	cell.setValue(qhten);
					cell = cells.getCell("Y" + Integer.toString(i));	cell.setValue(phuongxa);
					cell = cells.getCell("Z" + Integer.toString(i));	cell.setValue(ghichu);
					i++;
				} 	
				if(rs != null)
				{
		 			rs.close();
				}
				if(db!=null)
				{
					db.shutDown();
				}
 			}
			catch (Exception e)
 			{	 		
 				e.printStackTrace();
	 			throw new Exception(e.getMessage());
	 		}
	 		finally 
			{
	 			if(rs != null)
	 			rs.close();
	 		}
	 	} else {	 			
			throw new Exception("Không thể tạo báo cáo trong thời gian này");
	 	}		 
 	}	
 	
 	private void CreateStaticData3(Workbook workbook,IStockintransit obj) throws Exception
 	{
 		Worksheets worksheets = workbook.getWorksheets();
 	    Worksheet worksheet = worksheets.getSheet(0);
 	    Cells cells = worksheet.getCells();
 	    dbutils db = new dbutils();
 	    Utility Ult = new  Utility();

		String chuoi="";
 	    if(obj.getvungId().length()>0)
 	    	chuoi=chuoi +"and v.PK_SEQ='"+obj.getvungId()+"' ";
 	    if(obj.getTinhthanhid().length()>0)
 	    	chuoi=chuoi + " and tp.PK_SEQ='"+obj.getTinhthanhid()+"'";
 	    
 	    String sql = "";
 	    
 	 
 	    
 	    if (obj.gettungay() != null && obj.gettungay().trim().length() > 0 && obj.getdenngay() != null && obj.getdenngay().trim().length() > 0)
 	    
		sql = " 	select a.donhang_fk, a.sanpham_fk, " +
					"\n (select distinct dvdl.donvi from quycach qc inner join donvidoluong dvdl on qc.dvdl1_fk = dvdl.pk_seq where sanpham_fk = a.sanpham_fk)donvi, "+
					"\n a.soluong, a.giamua, a.thanhtien,isnull(ckprogram,0)ck, a.chietkhau, a.thuevat, a.tienvat "+
					"\n from donhang_sanpham a "+
					"\n inner join donhang b on b.pk_seq = a.DONHANG_FK "+
					"\n inner join nhaphanphoi c on c.pk_seq = b.NPP_FK "+
					"\n where c.loaiNPP = 0 and '"+obj.gettungay()+"' <= b.ngaynhap <= '"+obj.getdenngay()+"' " +chuoi;
 	    else
 	    	sql = " 	select a.donhang_fk, a.sanpham_fk, " +
				" (select distinct dvdl.donvi from quycach qc inner join donvidoluong dvdl on qc.dvdl1_fk = dvdl.pk_seq where sanpham_fk = a.sanpham_fk)donvi, "+
				"a.soluong, a.giamua, a.thanhtien,isnull(ckprogram,0)ck, a.chietkhau, a.thuevat, a.tienvat "+
				"from donhang_sanpham a "+
				"inner join donhang b on b.pk_seq = a.DONHANG_FK "+
				"inner join nhaphanphoi c on c.pk_seq = b.NPP_FK "+
				" where c.loaiNPP = 0 ";

 	    System.out.println("[BaoCaoDangNhapTTSvl.CreateStaticData] sql = " + sql);
 	    ResultSet rs = db.get(sql);
 	    int i = 7; 	     	    
 		if(rs!=null)
 		{
 			try 
 			{
 				cells.setColumnWidth(0, 15.0f);
 				cells.setColumnWidth(1, 15.0f);
 				cells.setColumnWidth(2, 15.0f);
 				cells.setColumnWidth(3, 15.0f);
 				cells.setColumnWidth(4, 15.0f);
 				cells.setColumnWidth(5, 15.0f);
 				cells.setColumnWidth(6, 15.0f);
 				cells.setColumnWidth(7, 15.0f);
 				cells.setColumnWidth(8, 15.0f);
 				cells.setColumnWidth(9, 15.0f);
 				
 				Cell cell = null;
 				Style style;
				String mien, chinhanh, taikhoan, hoten, ngaybd, ngaykt,ngay;
				String madh,masp,dvt,soluong,dongia,thanhtien,ptck,tck,vat,tvat;
				while (rs.next()) 
				{
					madh = rs.getString("donhang_fk");
					masp = rs.getString("sanpham_fk");
					dvt = rs.getString("donvi");
					soluong = rs.getString("soluong");
					dongia = rs.getString("giamua");
					thanhtien = rs.getString("thanhtien");
					ptck = rs.getString("ck");
					tck = rs.getString("chietkhau");
					vat = rs.getString("thuevat");
					tvat = rs.getString("tienvat");

					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(madh);
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(masp);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(dvt);
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(soluong);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(dongia);
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(thanhtien);
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(ptck);
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(tck);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(vat);
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(tvat);
					i++;
				} 	
				if(rs != null)
				{
		 			rs.close();
				}
				if(db!=null)
				{
					db.shutDown();
				}
 			}
			catch (Exception e)
 			{	 		
 				e.printStackTrace();
	 			throw new Exception(e.getMessage());
	 		}
	 		finally 
			{
	 			if(rs != null)
	 			rs.close();
	 		}
	 	} else {	 			
			throw new Exception("Không thể tạo báo cáo trong thời gian này");
	 	}		 
 	}	
 	
 	
 	private void getCellStyle(Workbook workbook, String a, Color mau, Boolean dam, int size)
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    Cells cells = worksheet.getCells();
		Style style;
		Cell cell = cells.getCell(a); 
		style = cell.getStyle();
	    Font font1 = new Font();
	    font1.setColor(mau);
	    font1.setBold(dam);
	    font1.setSize(size);
	    style.setFont(font1);
	    
		//Setting the horizontal alignment of the text in the cell 
	    style.setHAlignment(TextAlignmentType.LEFT);
	    cell.setStyle(style);
	}

	private String getDate() 
	{
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy: hh:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);	
	} 	
}

 