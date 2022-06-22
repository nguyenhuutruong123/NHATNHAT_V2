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

public class BCTheoDoiSoLuongXuatHopDong extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BCTheoDoiSoLuongXuatHopDong() {
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
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCTheoDoiSoLuongXuatHopDong.jsp";
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
	    		response.setHeader("Content-Disposition", "attachment; filename=BCTheoDoiSoLuongXuatHopDong.xlsm");
	
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
			String nextJSP = request.getContextPath() + "/pages/Distributor/BCTheoDoiSoLuongXuatHopDong.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	
	private void TongHopChiPhiSX(OutputStream out, IStockintransit obj) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCTheoDoiSoLuongXuatHopDong.xlsm");

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
	    
	    if (obj.gettungay() != null) { 
		    if (obj.gettungay().length() > 0) {
		    	condition += "\n and A.NGAYTAO >= convert(varchar,'"+obj.gettungay()+"',21)";
		    }
	    }
	    
	    if (obj.getdenngay() != null) {
		    if (obj.getdenngay().length() > 0) {
		    	condition += "\n and A.NGAYTAO <= convert(varchar,'"+obj.getdenngay()+"',21)";
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
		    	condition += "\n and exists (select 1 from khuvuc where vung_fk = v.pk_seq and pk_seq = "+obj.getkhuvucId()+") ";
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
	    
	    System.out.println("NPP: "+obj.getnppId());
	    //if  (obj.getnppId().length() > 0)
	    //	condition = " and npp_fk in "+u.quyen_npp(obj.getuserId())+"  ";
	    
	    String query = "\n select  a.MaHopDong, kh.mafast makh, kh.ten khten, sp.ma spma, " +
	    	"\n b.soluong SLHD, isnull(dadung.sl,0) SLDaXuat, b.soluong - isnull(dadung.sl,0) SLConLai " +
	    	"\n from erp_hopdongnpp a inner join erp_hopdongnpp_sanpham b on a.pk_seq = b.hopdong_fk " +
	    	"\n  inner join khachhang kh on kh.pk_seq = a.KHACHHANG_FK " +
	    	"\n  left join (select sum(soluong) sl,sanpham_fk,aa.HOPDONG_FK from erp_dondathangnpp_sanpham bb  " +
	    	"\n 			inner join erp_dondathangnpp aa on aa.pk_seq = bb.dondathang_fk where aa.trangthai !=2 group by hopdong_fk,sanpham_fk) dadung " +
	    	"\n  on dadung.sanpham_fk = b.sanpham_fk and dadung.HOPDONG_FK = a.pk_seq " +
	    	"\n  inner join sanpham sp on sp.pk_seq = b.sanpham_fk " +
	    	"\n inner join nhaphanphoi npp on npp.pk_seq = a.npp_fk  " +
	    	"\n inner join tinhthanh tt on tt.pk_seq = npp.tinhthanh_fk  " +
	    	"\n  inner join vung v on v.pk_seq = tt.vung_fk  " +
	    	"\n  where 1 = 1 ";

	    
	    System.out.println("Query BC: " + query);
	    ResultSet rsChiphi = db.get(query);
	    
		try 
		{
			int rowIndex = 6;
			int colIndex = 0;
			int stt = 1;
			
			Style BZStyle = cells.getCell("BZ6").getStyle();
			//BZStyle.setTextWrapped(true);
			Style BXStyle = cells.getCell("BX6").getStyle();
			Color clr = new Color(255,255,255);
			
			if(rsChiphi != null)
			{
				double totalSL = 0;
				double totalTT = 0;
				double totalKM = 0;
				double totalTong = 0;
				
				while( rsChiphi.next() )
				{	
					String mahopdong = rsChiphi.getString("mahopdong");
					String makh = rsChiphi.getString("makh");
					String khten = rsChiphi.getString("khten");
					String spma = rsChiphi.getString("spma");
					Double slhd = rsChiphi.getDouble("slhd");
					Double sldaxuat = rsChiphi.getDouble("sldaxuat");
					Double slconlai = rsChiphi.getDouble("slconlai");
					
					cell = cells.getCell( rowIndex, colIndex );     cell.setStyle(BZStyle); cell.setValue( stt );
					cell = cells.getCell( rowIndex, colIndex + 1 ); cell.setStyle(BZStyle); cell.setValue( mahopdong );	
					cell = cells.getCell( rowIndex, colIndex + 2 ); cell.setStyle(BZStyle); cell.setValue( makh );
					cell = cells.getCell( rowIndex, colIndex + 3 ); cell.setStyle(BZStyle); cell.setValue( khten );
					cell = cells.getCell( rowIndex, colIndex + 4 ); cell.setStyle(BZStyle); cell.setValue( spma );
					cell = cells.getCell( rowIndex, colIndex + 5 ); cell.setStyle(BZStyle); cell.setValue( slhd );
					this.setCellBackground(cell, clr, BorderLineType.THIN, false, 37);
					cell = cells.getCell( rowIndex, colIndex + 6 ); cell.setStyle(BZStyle); cell.setValue( sldaxuat );
					this.setCellBackground(cell, clr, BorderLineType.THIN, false, 37);
					cell = cells.getCell( rowIndex, colIndex + 7 ); cell.setStyle(BZStyle); cell.setValue( slconlai );
					this.setCellBackground(cell, clr, BorderLineType.THIN, false, 37);
					
					rowIndex ++; stt++;
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
