package geso.traphaco.erp.servlets.thutien;

import geso.traphaco.center.db.sql.dbutils;
import geso.traphaco.center.util.Utility;
import geso.traphaco.erp.beans.thutien.IErpThutienCK;
import geso.traphaco.erp.beans.thutien.IErpThutienCKList;
import geso.traphaco.erp.beans.thutien.imp.ErpThutienCK;
import geso.traphaco.erp.beans.thutien.imp.ErpThutienCKList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


public class ErpThutienCKSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpThutienCKSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpThutienCKList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    String tthdId = util.getId(querystring);
	    
	    obj = new ErpThutienCKList();
	    obj.setCtyId(ctyId);
	    obj.setnppdangnhap(util.getIdNhapp(userId));
	    obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    obj.setDoituongId(session.getAttribute("doituongId"));
	    
	    if (action.equals("delete"))
	    {	
	    	String msg = Delete(tthdId, userId );
	    	if(msg.length() > 0)
	    		obj.setmsg(msg);
	    	
	    	obj.setUserId(userId);
	    	obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	 	    obj.setDoituongId(session.getAttribute("doituongId"));
	    	obj.setnppdangnhap(util.getIdNhapp(userId));
	    	
		    obj.init("");
		    
			session.setAttribute("obj", obj);
					
			String nextJSP = "/TraphacoERP/pages/Erp/ErpThuTienCK.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else if(action.equals("chot"))
	    {
	    		IErpThutienCK tthd = new ErpThutienCK(tthdId) ;
	    		tthd.setCtyId(ctyId);
	    		tthd.setnppdangnhap(util.getIdNhapp(userId));
	    		tthd.setLoainhanvien(session.getAttribute("loainhanvien"));
	    		tthd.setDoituongIddn(session.getAttribute("doituongId"));
	    		    
	    		if(!tthd.chotTTHD(userId))
	    		{
	    			obj.setmsg(tthd.getMsg());
	    		}
	    		
    		    obj.setUserId(userId);
    		    obj.init("");
    		    
    			session.setAttribute("obj", obj);
    					
    			String nextJSP = "/TraphacoERP/pages/Erp/ErpThuTienCK.jsp";
    			response.sendRedirect(nextJSP);    	
    	    	
	    }
	    else
	    {	    
		    obj.setUserId(userId);
		    obj.init("");
		    obj.setnppdangnhap(util.getIdNhapp(userId));
		    
			session.setAttribute("obj", obj);
					
			String nextJSP = "/TraphacoERP/pages/Erp/ErpThuTienCK.jsp";
			response.sendRedirect(nextJSP);
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpThutienCKList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    OutputStream out = response.getOutputStream();
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    
	    String chungtu=util.antiSQLInspection(request.getParameter("chungtu"));
		if (chungtu == null)
			chungtu = "";
	
		String trangthaiphieu=util.antiSQLInspection(request.getParameter("trangthaiphieu"));
		if (trangthaiphieu == null)
			trangthaiphieu = "";
	
	    if(action.equals("Tao moi"))
	    {
	    	IErpThutienCK tthdBean = new ErpThutienCK();
	    	tthdBean.setCtyId(ctyId);
	    	tthdBean.setUserId(userId);
	    	tthdBean.setLoainhanvien(session.getAttribute("loainhanvien"));
	    	tthdBean.setDoituongIddn(session.getAttribute("doituongId"));
	    	tthdBean.setnppdangnhap(util.getIdNhapp(userId));
	    	
	    	tthdBean.createRs();
    		
	    	session.setAttribute("tthdBean", tthdBean);

    		String nextJSP = "/TraphacoERP/pages/Erp/ErpThuTienCKNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		obj = new ErpThutienCKList();
	    		obj.setCtyId(ctyId);
	    		obj.setnppdangnhap(util.getIdNhapp(userId));
	    		obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    		obj.setDoituongId(session.getAttribute("doituongId"));
	    		
		    	String search = getSearchQuery(request, obj);
		    	
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	response.sendRedirect("/TraphacoERP/pages/Erp/ErpThuTienCK.jsp");	
		    }
	    	else if (action.equals("excel"))
	 	    {
	    		obj = (IErpThutienCKList) new ErpThutienCKList();	 	    	
	 		    obj.setCtyId(ctyId);	 
	 		    obj.setnppdangnhap(util.getIdNhapp(userId));
	 		    obj.setLoainhanvien(session.getAttribute("loainhanvien"));
	    		obj.setDoituongId(session.getAttribute("doituongId"));
	    		
	 		   // String search = getSearchQuery(request, obj);
	    		
	    		try
				{
					//response.setContentType("application/vnd.ms-excel");
					//response.setHeader("Content-Disposition", "attachment; filename=DanhsachKhachhang.xls");

					response.setContentType("application/xlsm");
					response.setHeader("Content-Disposition", "attachment; filename=BCThuTienCK.xlsm");
			        
			        Workbook workbook = new Workbook();
			    	
					FileInputStream fstream = null;
					String chuoi = getServletContext().getInitParameter("path") + "\\BCThuTienCK.xlsm";
					
					fstream = new FileInputStream(chuoi);		
					workbook.open(fstream);
					workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
					
					String querythutien = getQueryExcelThuTien(request, "", "", obj); 
	 				String querythutienhds = getQueryExcelThuTienHoaDon(request, "", "", obj); 
					
					CreateStaticData(workbook, querythutien, querythutienhds, obj );
					workbook.save(out);
					
					fstream.close();
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}

				obj.setUserId(userId);


				session.setAttribute("obj", obj);

				session.setAttribute("userId", userId);
				return;	 	    		
	 	    }
	    	 else if(action.equals("chot"))
	 	    {	    		
	    			
	 	    		IErpThutienCK tthd = new ErpThutienCK(chungtu) ;
	 	    		
	 	    		obj = new ErpThutienCKList();	 	    		
	 	    		String search = getSearchQuery(request, obj);
 	    		    obj.setUserId(userId);
 	    		    obj.setnppdangnhap(util.getIdNhapp(userId));
 	    		    obj.setLoainhanvien(session.getAttribute("loainhanvien"));
 		    		obj.setDoituongId(session.getAttribute("doituongId"));
 	    		    obj.setCtyId(ctyId);
	 				
	 	    		if(!tthd.chotTTHD(userId))
	 	    		{
	 	    			obj.setmsg(tthd.getMsg());
	 	    		}
	 	    		
 	    		    obj.init("");
 	    		     	    		   
 	    			session.setAttribute("obj", obj);
 	    					
 	    			String nextJSP = "/TraphacoERP/pages/Erp/ErpThuTienCK.jsp";
 	    			response.sendRedirect(nextJSP);    	
	 	    	
	 	    }
	    	 else if (action.equals("delete"))
	 	    {		 	    		
	    		 obj = new ErpThutienCKList();
	    		 obj.setUserId(userId);
		 		 obj.setnppdangnhap(util.getIdNhapp(userId));
		 	  	 obj.setCtyId(ctyId);
		 	  	 obj.setLoainhanvien(session.getAttribute("loainhanvien"));
		    	 obj.setDoituongId(session.getAttribute("doituongId"));
	 	    	// String search = getSearchQuery(request, obj);
	 	    	String msg = Delete(chungtu, userId );
	 	    	if(msg.length() > 0)
	 	    	obj.setmsg(msg);
	 	    		 		    
	 	    	obj.init("");
	 			session.setAttribute("obj", obj);
	 					
	 			String nextJSP = "/TraphacoERP/pages/Erp/ErpThuTienCK.jsp";
	 			response.sendRedirect(nextJSP);
	 	    }
	    	else
	    	{
		    	obj = new ErpThutienCKList();
		    	obj.setCtyId(ctyId);
		    	obj.setUserId(userId);
				obj.setnppdangnhap(util.getIdNhapp(userId));
				obj.setLoainhanvien(session.getAttribute("loainhanvien"));
		    	obj.setDoituongId(session.getAttribute("doituongId"));
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				
				  
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		response.sendRedirect("/TraphacoERP/pages/Erp/ErpThuTienCK.jsp");
	    	}
	    }
	}
	private void CreateStaticHeader(Workbook workbook, String UserName) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet1 = worksheets.getSheet(0);
	   	   
	    Cells cells = worksheet1.getCells();
	   	    
	    Cell cell = cells.getCell("A1"); 
	    cell.setValue("PHẢI THU KHÁCH HÀNG");
 
	    cell = cells.getCell("A6");
	    cell.setValue("Số hệ thống Pk_seq");
	    
	    cell = cells.getCell("B6");
	    cell.setValue("Số phiếu");
	    
	    cell = cells.getCell("C6");
	    cell.setValue("Ngày ghi sổ");
	    
	    cell = cells.getCell("D6");	  
	    cell.setValue("ID khách hàng");
	    
	    cell = cells.getCell("E6");
	    cell.setValue("Tên khách hàng");
	    
	    cell = cells.getCell("F6");
	    cell.setValue("Thu được");
	    
	    cell = cells.getCell("G6");
	    cell.setValue("Thu được NT");
	    
	    cell = cells.getCell("H6");
	    cell.setValue("Phí ngân hàng");
	    
	    cell = cells.getCell("I6");
	    cell.setValue("Phí ngân hàng NT");
	    
	    cell = cells.getCell("J6");
	    cell.setValue("Chênh lệch");	 
 
	    cell = cells.getCell("K6");
	    cell.setValue("Chiết khấu");
	    	  
	    worksheet1.setName("Phatsinh");
	    
	    
	    Worksheet worksheet2 = workbook.createSheet();
	   	   
	    Cells cell1s = worksheet2.getCells();
	   	    
	    Cell cell1 = cell1s.getCell("A1"); 
	    cell1.setValue("HÓA ĐƠN KHÁCH HÀNG");
 
	    cell1 = cell1s.getCell("A6");
	    cell1.setValue("Thu tiền fk");
	    
	    cell1 = cell1s.getCell("B6");
	    cell1.setValue("ID khách hàng");
	    
	    cell1 = cell1s.getCell("C6");
	    cell1.setValue("Tên khách hàng");
	    
	    cell1 = cell1s.getCell("D6");
	    cell1.setValue("Hóa đơn");
	    
	    cell1 = cell1s.getCell("E6");
	    cell1.setValue("Số hóa đơn");
	    
	    cell1 = cell1s.getCell("F6");
	    cell1.setValue("Ngày xuất hóa đơn");
	    
	    cell1 = cell1s.getCell("G6");
	    cell1.setValue("Tổng tiền VND");
	    
	    cell1 = cell1s.getCell("H6");
	    cell1.setValue("Tổng tiền USD");
	    
	    cell1 = cell1s.getCell("I6");
	    cell1.setValue("Thanh toán VND");	 
 
	    cell1 = cell1s.getCell("J6");
	    cell1.setValue("Thanh toán USD");
	    
	    cell1 = cell1s.getCell("K6");
	    cell1.setValue("Ngày TT");
	    	  
	    worksheet2.setName("Hoadon");
	}
	
	
	private void CreateStaticData(Workbook workbook, String query , String query2, IErpThutienCKList obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
	    
		dbutils db = new dbutils();
		
		Cell cell = null;
		
		cells.setRowHeight(0, 20.0f);
		cell = cells.getCell("A1");			
		cell.setValue("Đơn vị: " + obj.getCtyTen());

		cells.setRowHeight(1, 20.0f);
		cell = cells.getCell("A2");
		cell.setValue("Địa chỉ: " + obj.getDiachi());
		
		cells.setRowHeight(2, 20.0f);
		cell = cells.getCell("A3");
		cell.setValue("Mã số thuế: " + obj.getMasothue()); 
		
		ResultSet rs = db.get(query);
				
		int i = 8;
		if(rs != null)
		{
			try 
			{				
				while(rs.next())
				{	
					cell = cells.getCell("A" + Integer.toString(i));
					cell.setValue(rs.getString("machungtu"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("B" + Integer.toString(i));
					cell.setValue(rs.getString("trangthai"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("C" + Integer.toString(i));
					cell.setValue(rs.getString("mafast"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("D" + Integer.toString(i));
					cell.setValue(rs.getString("tenkh"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("E" + Integer.toString(i));
					cell.setValue(rs.getString("kbh_fk"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("F" + Integer.toString(i));
					cell.setValue(rs.getString("SOHOADON"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("G" + Integer.toString(i));
					cell.setValue(rs.getString("NGAYXUATHD"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
										
					cell = cells.getCell("H" + Integer.toString(i));
					cell.setValue(rs.getDouble("tongtienavat"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("I" + Integer.toString(i));
					cell.setValue(rs.getDouble("thucthu"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("J" + Integer.toString(i));
					cell.setValue(rs.getString("xoachenhlech"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("K" + Integer.toString(i));
					cell.setValue(rs.getDouble("tienchenhlech"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cells.getCell("L" + Integer.toString(i));
					cell.setValue(rs.getString("tencp"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
														 					
					i++;
				}
				rs.close();
			}
			catch (Exception e){ e.printStackTrace(); }
		}
		
		
		worksheet = worksheets.getSheet(1);
	    Cells cell1s = worksheet.getCells();	
	    
	    cell1s.setRowHeight(0, 20.0f);
		cell = cell1s.getCell("A1");			
		cell.setValue("Đơn vị: " + obj.getCtyTen());

		cell1s.setRowHeight(1, 20.0f);
		cell = cell1s.getCell("A2");
		cell.setValue("Địa chỉ: " + obj.getDiachi());
		
		cell1s.setRowHeight(2, 20.0f);
		cell = cell1s.getCell("A3");
		cell.setValue("Mã số thuế: " + obj.getMasothue()); 
		
		
		rs = db.get(query2);
				
		i = 8;
		if(rs != null)
		{
			try 
			{				
				cell = null;
				while(rs.next())
				{					
					
					cell = cell1s.getCell("A" + Integer.toString(i));
					cell.setValue(rs.getString("machungtu"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("B" + Integer.toString(i));
					cell.setValue(rs.getString("trangthai"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("C" + Integer.toString(i));
					cell.setValue(rs.getString("mafast"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("D" + Integer.toString(i));
					cell.setValue(rs.getString("tenKH"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("E" + Integer.toString(i));
					cell.setValue(rs.getString("KBH_FK"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("F" + Integer.toString(i));
					cell.setValue(rs.getString("sohoadon"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
				
					cell = cell1s.getCell("G" + Integer.toString(i));
					cell.setValue(rs.getString("ngayxuathd"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("H" + Integer.toString(i));
					cell.setValue(rs.getString("MASP"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("I" + Integer.toString(i));
					cell.setValue(rs.getDouble("tienavat"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("J" + Integer.toString(i));
					cell.setValue(rs.getDouble("thanhtoan"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("K" + Integer.toString(i));
					cell.setValue(rs.getDouble("ptchietkhau"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
									 
					cell = cell1s.getCell("L" + Integer.toString(i));
					cell.setValue(rs.getDouble("tienck"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					cell = cell1s.getCell("M" + Integer.toString(i));
					cell.setValue(rs.getDouble("thucthu"));
					cell = CreateBorderSetting(cell);
					cell = CreateBorderSetting(cells.getCell("Z1"));
					
					i++;
				}
				rs.close();
			}
			catch (Exception e){ e.printStackTrace(); }
		}
	 
	}
	
	public Cell CreateBorderSetting(Cell cell) throws IOException
    {
	
        Style style;
        style = cell.getStyle();

        //Set border color
        style.setBorderColor(BorderType.TOP, Color.BLACK);
        style.setBorderColor(BorderType.BOTTOM, Color.BLACK);
        style.setBorderColor(BorderType.LEFT, Color.BLACK);
        style.setBorderColor(BorderType.RIGHT, Color.BLACK);

        //Set the cell border type
        style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);

        cell.setStyle(style);
        return cell;
    }
	
	private String getQueryExcelThuTien(HttpServletRequest request, String pages, String soDong, IErpThutienCKList obj)
	{    	
    	
		// HÓA ĐƠN TÀI CHÍNH
    	String query = " SELECT A.machungtu, case A.TRANGTHAI when 0 then N'Chưa chốt' when 1 then N'Đã chốt' when 2 then N'Đã xóa' end AS TRANGTHAI,ISNULL(ISNULL(D.PK_SEQ, E.PK_SEQ), F.PK_SEQ) KHACHHANG_FK, \n"+
				       " CASE WHEN C.KHACHHANG_FK IS NOT NULL THEN D.maFAST WHEN  C.NPP_DAT_FK IS NOT NULL THEN E.MAFAST \n"+
				       " ELSE F.MA END MAFAST, \n"+
				       " CASE WHEN C.KHACHHANG_FK IS NOT NULL THEN D.TEN WHEN  C.NPP_DAT_FK IS NOT NULL THEN E.TEN \n"+
				       " ELSE F.TEN END TENKH, \n"+
				       " G.TEN KBH_FK, C.SOHOADON , C.NGAYXUATHD, C.tongtienavat, isnull(b.SOTIENTT,0) thucthu, case ISNULL(xoachenhlech, 0) when 0 then N'Không' else N'Có' end xoachenhlech, \n"+
				       " ISNULL(tienchenhlech,0) tienchenhlech, \n"+
				       " isnull((select cast(B.macp as nvarchar(50)) from erp_nhomchiphi nhomcp where B.macp = nhomcp.pk_seq ),'') macp, \n"+
				       " isnull((select nhomcp.ten from erp_nhomchiphi nhomcp where B.macp = nhomcp.pk_seq ),'') tencp \n"+
				       " FROM ERP_THUTIEN A INNER JOIN ERP_THUTIEN_HOADON B ON A.PK_SEQ = B.THUTIEN_FK \n"+
				       " INNER JOIN ERP_HOADONNPP C ON B.HOADON_FK = C.PK_SEQ \n"+
				       " LEFT JOIN KHACHHANG D ON C.KHACHHANG_FK = D.PK_SEQ \n"+
				       " LEFT JOIN NHAPHANPHOI E ON C.NPP_DAT_FK = E.PK_SEQ \n"+
				       " LEFT JOIN ERP_NHANVIEN F ON C.nhanvien_fk = F.PK_SEQ \n"+
				       " LEFT JOIN KENHBANHANG G ON C.KBH_FK = G.PK_SEQ \n"+
				       " WHERE B.LOAIHOADON = 0 AND A.HTTT_FK = 100001 AND A.CONGTY_FK = "+obj.getCtyId();
    	
    	System.out.println(query);
    	return query;
    	
	}	
	
	private String getQueryExcelThuTienHoaDon(HttpServletRequest request, String pages, String soDong, IErpThutienCKList obj)
	{
		// HÓA ĐƠN TÀI CHÍNH
    	String query = " select A.machungtu, case A.TRANGTHAI when 0 then N'Chưa chốt' when 1 then N'Đã chốt' when 2 then N'Đã xóa' end AS TRANGTHAI , ISNULL(ISNULL(D.PK_SEQ, E.PK_SEQ), F.PK_SEQ) KHACHHANG_FK, \n"+
				       " CASE WHEN C.KHACHHANG_FK IS NOT NULL THEN D.maFAST WHEN  C.NPP_DAT_FK IS NOT NULL THEN E.MAFAST \n"+
				       " ELSE F.MA END MAFAST, \n"+
				       " CASE WHEN C.KHACHHANG_FK IS NOT NULL THEN D.TEN WHEN  C.NPP_DAT_FK IS NOT NULL THEN E.TEN \n"+
				       " ELSE F.TEN END TENKH, \n"+
				       " G.TEN KBH_FK, C.SOHOADON, C.NGAYXUATHD, H.TIENAVAT , B.thanhtoan, isnull(B.ptchietkhau,0) ptchietkhau, isnull(B.tienck,0) tienck, isnull(B.thucthu, B.thanhtoan) thucthu, SP.MA_FAST MASP \n"+
				       " from ERP_THUTIEN A INNER JOIN ERP_THUTIEN_HOADON_SP B ON A.PK_SEQ = B.thutien_fk \n"+
				       " INNER JOIN ERP_HOADONNPP_SP H ON B.hoadon_fk = H.HOADON_FK AND B.sanpham_fk = H.SANPHAM_FK \n"+
				       " INNER JOIN ERP_HOADONNPP C ON B.hoadon_fk = C.PK_SEQ \n"+
				       " LEFT JOIN KHACHHANG D ON C.KHACHHANG_FK = D.PK_SEQ \n"+
				       " LEFT JOIN NHAPHANPHOI E ON C.NPP_DAT_FK = E.PK_SEQ \n"+
				       " LEFT JOIN ERP_NHANVIEN F ON C.nhanvien_fk = F.PK_SEQ \n"+
				       " LEFT JOIN KENHBANHANG G ON C.KBH_FK = G.PK_SEQ \n"+
				       " LEFT JOIN SANPHAM SP ON B.sanpham_fk = SP.PK_SEQ \n"+
				       " WHERE B.LOAIHOADON = 0 AND LEN(H.SCHEME) <= 0 AND A.HTTT_FK = 100001 AND A.CONGTY_FK = "+obj.getCtyId();

			System.out.println(query);
			return query;

	}	
	
	
	private String getSearchQuery(HttpServletRequest request, IErpThutienCKList obj)
	{
		Utility util = new Utility();
		String query = 	" select a.pk_seq as tthdId,(isnull(a.prefix, 'BC') + cast(a.pk_seq as nvarchar(50))) sochungtu, a.trangthai, a.ngaychungtu, a.ngayghiso, a.ngaytao, a.ngaysua, " +
						" case when b.ten is null and ncc.ten is null and nv.ten is null then '' " + // thu khác
						"      when nv.ten is not null then nv.ten " +
						"      when ncc.ten is not null then ncc.ten " +
						"      when b.ten is not null then b.Ten " +
						" 	   else '' end as nppTen, isnull(a.ISKTTDUYET,0) ISKTTDUYET, " +
						"c.ten as htttTen, " +
						" d.ten as nguoitao, e.ten as nguoisua, f.ten as noidungTen, " +
						" CASE WHEN a.TIENTE_FK = 100000 "+
						"      THEN (case when a.noidungtt_fk != 100002 then ISNULL(a.THUDUOC, 0) "+
						"                 else (select sum(sotien) from erp_thutien_dinhkhoanco where thutien_fk = a.pk_seq) end ) "+
						"      ELSE (case when a.noidungtt_fk != 100002 then ISNULL(a.THUDUOCNT, 0) "+
						"                 else (select sum(sotiennt) from erp_thutien_dinhkhoanco where thutien_fk = a.pk_seq) end ) "+
						" END AS THUCTHU, " +
						" TT.MA AS TIENTE, " +
						" isnull( (select cast(isnull(AA.sohoadon,'') as varchar(20)) +', ' as [text()] "+
						"		 from "+  
						"		   		(select sohoadon, PK_SEQ "+
						"				 from erp_hoadon "+
						"		         where pk_seq in ( select b.HOADON_FK from ERP_THUTIEN tt1 inner join ERP_THUTIEN_HOADON b on tt1.PK_SEQ=b.THUTIEN_FK where tt1.PK_SEQ=a.PK_SEQ and b.LOAIHOADON = 0 ) "+ 
						"		         union "+
						"		         select sohoadon, PK_SEQ  "+
						"		         from ERP_HoaDonPheLieu "+
						"		         where pk_seq in ( select b.HOADON_FK from ERP_THUTIEN tt1 inner join ERP_THUTIEN_HOADON b on tt1.PK_SEQ=b.THUTIEN_FK where tt1.PK_SEQ=a.PK_SEQ and b.LOAIHOADON = 1 ) "+   		
						"		   		)AA For XML PATH ('') "+
						"		  ),'')  	as SOHOADON, a.MACHUNGTU "+
						" from ERP_THUTIEN a " +
						" INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = a.TIENTE_FK  " +
						" left join KhachHang b on a.KHACHHANG_FK = b.pk_seq " +
						" left join ERP_NhaCungCap ncc on a.NCC_FK = ncc.pk_seq " +
						" left join erp_nhanvien nv on a.nhanvien_fk= nv.pk_seq " +
						" inner join ERP_HINHTHUCTHANHTOAN c on a.HTTT_FK = c.pk_seq " +
						" inner join ERP_NOIDUNGTHUTIEN f on a.noidungtt_fk = f.pk_seq " +
						" inner join NHANVIEN d on a.nguoitao = d.pk_seq  " +
						" inner join NHANVIEN e on a.nguoisua = e.pk_seq  " +
						" where 1 = 1 and a.congty_fk ="+obj.getCtyId()+" and a.HTTT_FK = 100001 " ;

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		if(ghichu == null)
			ghichu = "";
		obj.setGhichu(ghichu);
		
		String khachhang = util.antiSQLInspection(request.getParameter("khachhang"));
		if(khachhang == null)
			khachhang = "";
		obj.setKhId(khachhang);
		
		String nccId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nccId == null)
			nccId = "";
		obj.setNccId(nccId);
		
		String nvgnId = util.antiSQLInspection(request.getParameter("nvgnId"));
		if(nvgnId == null)
			nvgnId = "";
		obj.setNvgnId(nvgnId);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);		
		
		String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
		if(sochungtu == null)
			sochungtu = "";
		obj.setsochungtu(sochungtu);
		
		String sohoadon = util.antiSQLInspection(request.getParameter("sohoadon"));
		if(sohoadon == null)
			sohoadon = "";
		obj.setsohoadon(sohoadon);
		
		String nguoisua = util.antiSQLInspection(request.getParameter("nguoisua"));
		if(nguoisua == null)
			nguoisua = "";
		obj.setNguoisuaId(nguoisua);
				
		String nvId = util.antiSQLInspection(request.getParameter("nvId"));
		if(nvId == null)
			nvId = "";
		obj.setNvId(nvId);
		
		String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
		if(kbhId == null)
			kbhId = "";
		obj.setKbhId(kbhId);		
		
		String khohhid = util.antiSQLInspection(request.getParameter("khohhid"));
		if(khohhid == null)
			khohhid = "";
		obj.setKhohangId(khohhid);	
		
		String sobangke=util.antiSQLInspection(request.getParameter("sobangke"));
		if (sobangke == null)
			sobangke = "";
		obj.setsobangke(sobangke);
		
		if(tungay.length() > 0)
			query += " and a.ngaychungtu >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngaychungtu <= '" + denngay + "'";
		
		if(nccId.length() > 0)
			query += " and isnull(b.pk_seq, ncc.pk_seq) = '" + nccId + "'";
				
		if(nvId.length()>0)
			query += " and nv.pk_seq = '"+nvId+"'";
				
		if(trangthai.length() > 0)			
			query += " and a.trangthai = "+trangthai;
		
		if(sochungtu.length() >0)
			query += " and (A.MACHUNGTU like '%"+sochungtu+"%' ) ";
		
		if(nguoisua.length() >0)
			query += " and a.nguoisua = '"+nguoisua+"' ";
		
		if(ghichu.length() > 0)
			query += " and a.GhiChu like N'%"+ghichu+"%' ";
			
		if(kbhId.length()>0)
			query += " and a.PK_SEQ IN ( SELECT THUTIEN_FK FROM ERP_THUTIEN_HOADON TTHD INNER JOIN ERP_HOADONNPP HD ON TTHD.HOADON_FK = HD.PK_SEQ AND TTHD.LOAIHOADON = 0 WHERE HD.KBH_FK =  "+kbhId+" )";
		
		if(khohhid.length()>0)
			query += " and a.PK_SEQ IN ( SELECT THUTIEN_FK FROM ERP_THUTIEN_HOADON TTHD INNER JOIN ERP_HOADONNPP HD ON TTHD.HOADON_FK = HD.PK_SEQ AND TTHD.LOAIHOADON = 0 WHERE HD.KHO_FK =  "+khohhid+" )";
			
		if(khachhang.length()>0)
			query += " and a.PK_SEQ IN ( SELECT THUTIEN_FK FROM ERP_THUTIEN_HOADON TTHD " +
					"					 INNER JOIN ERP_HOADONNPP HD ON TTHD.HOADON_FK = HD.PK_SEQ AND TTHD.LOAIHOADON = 0 " +
					" 					 LEFT JOIN KHACHHANG KH on HD.KHACHHANG_FK = kh.PK_SEQ \n"+
					" 					 LEFT JOIN NHAPHANPHOI NPP on HD.NPP_DAT_FK = npp.PK_SEQ \n"+
					" 					 LEFT JOIN ERP_NHANVIEN NV on HD.nhanvien_fk = nv.PK_SEQ \n"+
					"					 WHERE 1 = 1 " +
					" and ( ( kh.ten like N'%" + khachhang+ "%' or kh.ma like N'%" + khachhang+ "%' or kh.mafast = N'%"+khachhang+"%' ) or ( npp.ma like N'%" + khachhang
					+ "%' or npp.ten like N'%" + khachhang+ "%' or npp.mafast like N'%"+khachhang+"%' ) ) ) ";
		
		if(sobangke.length()>0)
			query += " and A.BANGKE_FK LIKE '%"+sobangke+"%' ";
		
		System.out.println(query);
		return query;
	}
	
	private String Delete(String tthdId, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try 
		{
			db.getConnection().setAutoCommit(false);
						
			String query = "update ERP_ThuTien set trangthai = '2' , nguoisua = "+ userId +"  , ngaysua = '"+ getDateTime() +"' where pk_seq = '" + tthdId + "'  ";
			
			if(!db.update(query))
			{
				msg = "Không thể xóa ERP_ThuTien: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = " update ERP_BANGKETHUTIEN SET TRANGTHAI = 0 WHERE PK_SEQ = (SELECT BANGKE_FK FROM ERP_THUTIEN WHERE PK_SEQ = "+tthdId+") ";
			
			System.out.println("1.Cap nhat ERP_BANGKETHUTIEN: " + query);

			if (!db.update(query)) {
				msg = "Khong the chot ERP_THUTIEN: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			
			return msg;
		} 
		catch (SQLException e)
		{ 
			db.shutDown(); 
			return "Khong the xoa ERP_ThuTien"; 
		}
		
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}

