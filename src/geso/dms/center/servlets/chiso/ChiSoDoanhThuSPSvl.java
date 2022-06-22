package geso.dms.center.servlets.chiso;

import geso.dms.center.beans.report.IBcDoanhThuSanPhamList;
import geso.dms.center.beans.report.imp.BcDoanhThuSanPhamList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
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


public class ChiSoDoanhThuSPSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBcDoanhThuSanPhamList obj;
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
		
		obj = new BcDoanhThuSanPhamList();
		obj.setUserId(userId);
		obj.setView("TT");
		String npp=request.getParameter("npp");
		if(npp!=null)
		{
			obj.setFlagnpp(npp);
			obj.setNppId(obj.getidnpp(userId));
			System.out.println("");
		}
		else
		obj.setView("");
		
		System.out.println("flag npp::::::::::::::::"+obj.getFlagnpp());
		String nextJSP = "";
		//obj.init2("");
		obj.createRs();
		
		nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuSP.jsp";
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
	    System.out.println("userTen: " + userTen);
	    
	    OutputStream out = response.getOutputStream();
	    
	    String action = request.getParameter("action");
	    if (action == null)
	    	action = "";
	    
	    IBcDoanhThuSanPhamList obj = new BcDoanhThuSanPhamList();
	    obj.setUserId(userId);
	    
	    obj.setView("TT");
	    
		String npp=request.getParameter("npp");
		if(npp!=null)
			obj.setFlagnpp(npp);
		else
		obj.setView("");
	   
	    String tungay =request.getParameter("Sdays")==null?"": request.getParameter("Sdays");
	    obj.setTuNgay(tungay);
	    
	    String denngay = request.getParameter("Edays")==null?"": request.getParameter("Edays");
	    obj.setDenNgay(denngay);
	    
	    String vungId = request.getParameter("vungId")==null?"": request.getParameter("vungId");
	    obj.setVungId(vungId);
	
	    String kbhId = request.getParameter("kbhId")==null?"": request.getParameter("kbhId");
	    obj.setKbhId(kbhId);    
	    
	    String ttId = request.getParameter("ttId")==null?"": request.getParameter("ttId");
	    obj.setTtId(ttId);   
	    
	    String nhomId = request.getParameter("nhomId")==null?"": request.getParameter("nhomId");
	    obj.setNhomId(nhomId);
	    
	    String khId = request.getParameter("khId")==null?"": request.getParameter("khId");
	    obj.setKhId(khId);
	    
	    String ddkdId =  request.getParameter("ddkdId")==null?"": request.getParameter("ddkdId");
	    obj.setDdkdId(ddkdId);
	    
	    String spId =request.getParameter("spId")==null?"": request.getParameter("spId");
	    obj.setSpId(spId);
	    
	    String nppId =request.getParameter("nppId")==null?"": request.getParameter("nppId");
	    obj.setNppId(nppId);
	    
	    String xemId = request.getParameter("xemId")==null?"": request.getParameter("xemId");
	    obj.setxemtheo(Integer.parseInt(xemId));
		
	    obj.set_Action(action);
	    
	    System.out.println("___ATION: "+action);
	    
	    if (action.equals("excel")  )
	    {
	    	try
		    { 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BcDoanhThuSPTheoMatHang.xlsm");
				//FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BcDoanhThuKhachHang.xlsm");
				Workbook workbook = new Workbook();
				//workbook.open(fstream);
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				
				obj.setUserId(userId);
				if(npp.equals("1"))
					obj.xuatexcelNPP("");
				else
					obj.xuatexcelTT("");
				String query=obj.getQueryHd();
				System.out.println(" \n [excel query]:   " + query);
				
				CreateStaticHeader(workbook, obj, userTen);
				FillData(workbook,obj, query);
				
				workbook.save(out);
				//fstream.close();
		    }
		    catch (Exception ex)
		    {
		    	ex.printStackTrace();
		        obj.setMsg("Khong the tao pivot.");
		    }
	    	/*session.setAttribute("obj", obj);
	  		session.setAttribute("userId", userId);
	  		String nextJSP = "";
	  		nextJSP = request.getContextPath() + "/pages/Center/ChiTieuDoanhThu.jsp";
	  		response.sendRedirect(nextJSP); */
	    	return;
	    }
	    else  if(action.equals("view") || action.equals("next") || action.equals("prev")){
	    	
	    	System.out.println("______::::::::::::::::_____"+action+"_________"+request.getParameter("nxtApprSplitting"));
	    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
	    	obj.setUserId(userId);
	    	//obj.init2("");
	    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
	    	session.setAttribute("obj", obj);
	    	
	    	String 	nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuSP.jsp";
	    	response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	//obj.init2("");
	    	System.out.println(" \n ChiSoDoanhThuSPSVL:  " + obj.getQueryHd());
			session.setAttribute("obj", obj);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuSP.jsp";
			response.sendRedirect(nextJSP);  
	    }
	}
	
	private boolean FillData(Workbook workbook, IBcDoanhThuSanPhamList obj, String query) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();		
		ResultSet hdRs = db.get(query);
		
		DecimalFormat df = new DecimalFormat("0.###");
			   	
		String soct = "", soctcur = "";
		int i = 7;
		int soluong = 0, tongslct = 0, tongsl = 0;
		double tt = 0, ttck = 0, ck = 0, tongttct = 0, tongttckct = 0, tongtt = 0, tongttck = 0, chietkhaungay = 0, tongckngay = 0;
		if (hdRs != null) 
		{
			try 
			{
				Cell cell = null;
				ResultSet sltongRs;
				int soluongct = 0;
				while (hdRs.next()) 
				{	
					chietkhaungay = hdRs.getDouble("chietkhaungay");
					soctcur = hdRs.getString("sonetId");
					soluong = hdRs.getInt("soluong");
					tt = hdRs.getDouble("BVAT");
					ck = hdRs.getDouble("tonggiamtru");
					
					if(soct.trim().length() <= 0)
					{
						soct = hdRs.getString("sonetId");
						query = "select SUM(soluong) soluong from \n" +
								"( \n" +
								"select SUM(soluong) soluong from PHIEUXUATKHO a inner join PHIEUXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.PXK_FK where a.sonetId = '"+soctcur+"' \n" +
								"union \n" +
								"select SUM(soluong) soluong from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.PXK_FK where a.sonetId = '"+soctcur+"' \n" +
								") a";
						System.out.println("soluongct = "+query);
						sltongRs = db.get(query);
						if(sltongRs.next())
						{
							soluongct = sltongRs.getInt("soluong");
							if(soluongct > 0)
							{
								double ckngay = Math.round(soluong / soluongct * chietkhaungay * tt / 100.0); 
								ck += ckngay;
								tongckngay += ckngay;
							}
						}sltongRs.close();
					}
					ttck = tt - ck;
					
					if(soct.equals(soctcur))
					{
						tongslct += soluong;
						double ckngay = 0;
						if(soluongct == tongsl)
							ckngay = Math.round(chietkhaungay * tt / 100.0) - tongckngay;
						else
						{
							ckngay = Math.round(soluong / soluongct * chietkhaungay * tt / 100.0);
							tongckngay += ckngay;
						}
						ck += ckngay;
						ttck = tt - ck;
						tongttct += tt;
						tongttckct += ttck;
						tongsl += soluong;
						tongtt += tt;
						tongttck += ttck;
						cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(hdRs.getString("ngaylapphieu"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(soctcur);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("sohoadon"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("khma"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
						cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getString("khten"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
						cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getString("spma"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(hdRs.getString("spten"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(hdRs.getString("donvi"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(hdRs.getString("solo"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(soluong);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						cell = cells.getCell("K" + Integer.toString(i));	cell.setValue(hdRs.getDouble("dongia"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						cell = cells.getCell("L" + Integer.toString(i));	cell.setValue(tt);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						if(obj.getFlagnpp().equals("1"))
						{
							cell = cells.getCell("M" + Integer.toString(i));	cell.setValue(ck);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("N" + Integer.toString(i));	cell.setValue(ttck);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("O" + Integer.toString(i));	cell.setValue(hdRs.getString("ghichu"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("P" + Integer.toString(i));	cell.setValue(hdRs.getString("loaihd"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("Q" + Integer.toString(i));	cell.setValue(hdRs.getString("nppMa"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						}
						else
						{
							cell = cells.getCell("M" + Integer.toString(i));	cell.setValue(ttck);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("N" + Integer.toString(i));	cell.setValue(hdRs.getString("ghichu"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("O" + Integer.toString(i));	cell.setValue(hdRs.getString("loaihd"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("P" + Integer.toString(i));	cell.setValue(hdRs.getString("nppMa"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						}
					}
					else
					{
						//Dòng tổng chi tiết
						cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
						cell = cells.getCell("E" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
						cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("G" + Integer.toString(i));	cell.setValue("Cộng phiếu :");
						ReportAPI.getCellStyle_Border(cell,Color.BLACK,true, 9, "Cộng phiếu :" );						
						cell = cells.getCell("H" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("I" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(tongslct);
						ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongslct );
						cell = cells.getCell("K" + Integer.toString(i));	cell.setValue("");
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						cell = cells.getCell("L" + Integer.toString(i));	cell.setValue(tongttct);
						ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttct );
						if(obj.getFlagnpp().equals("1"))
						{
							cell = cells.getCell("M" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("N" + Integer.toString(i));	//cell.setValue(tongttckct);
							ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttckct );
							cell = cells.getCell("O" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("P" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("Q" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						}
						else
						{
							cell = cells.getCell("M" + Integer.toString(i));	//cell.setValue(tongttckct);
							ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttckct );
							cell = cells.getCell("N" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("O" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("P" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						}
						i++;
						
						//in dòng chứng từ mới
						soct = hdRs.getString("sonetId");
						tongsl += soluong;
						query = "select SUM(soluong) soluong from PHIEUXUATKHO a inner join PHIEUXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.PXK_FK where a.sonetId = '"+soctcur+"'";
						System.out.println("soluongct = "+query);
						sltongRs = db.get(query);
						tongckngay = 0;
						if(sltongRs.next())
						{
							soluongct = sltongRs.getInt("soluong");
							if(soluongct > 0)
							{
								double ckngay = Math.round(soluong / soluongct * chietkhaungay * tt / 100.0); 
								ck += ckngay;
								tongckngay += ckngay;
							}
						}sltongRs.close();
						ttck = tt - ck;
						tongslct = soluong;
						tongttct = tt;
						tongttckct = ttck;
						tongtt += tt;
						tongttck += ttck;
						cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(hdRs.getString("ngaylapphieu"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(soctcur);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("sohoadon"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("khma"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
						cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getString("khten"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
						cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getString("spma"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(hdRs.getString("spten"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
						cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(hdRs.getString("donvi"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(hdRs.getString("solo"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(soluong);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						cell = cells.getCell("K" + Integer.toString(i));	cell.setValue(hdRs.getDouble("dongia"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						cell = cells.getCell("L" + Integer.toString(i));	cell.setValue(tt);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						if(obj.getFlagnpp().equals("1"))
						{
							cell = cells.getCell("M" + Integer.toString(i));	cell.setValue(ck);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("N" + Integer.toString(i));	cell.setValue(ttck);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("O" + Integer.toString(i));	cell.setValue(hdRs.getString("ghichu"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("P" + Integer.toString(i));	cell.setValue(hdRs.getString("loaihd"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("Q" + Integer.toString(i));	cell.setValue(hdRs.getString("nppMa"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						}
						else
						{
							cell = cells.getCell("M" + Integer.toString(i));	cell.setValue(ttck);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							cell = cells.getCell("N" + Integer.toString(i));	cell.setValue(hdRs.getString("ghichu"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("O" + Integer.toString(i));	cell.setValue(hdRs.getString("loaihd"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("P" + Integer.toString(i));	cell.setValue(hdRs.getString("nppMa"));
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						}
					}
					++i;
				} hdRs.close();
				
				//Dòng tổng chi tiết
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue("Cộng phiếu :");
				ReportAPI.getCellStyle_Border(cell,Color.BLACK,true, 9, "Cộng phiếu :" );						
				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(tongslct);
				ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongslct );
				cell = cells.getCell("K" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
				cell = cells.getCell("L" + Integer.toString(i));	cell.setValue(tongttct);
				ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttct );
				if(obj.getFlagnpp().equals("1"))
				{
					cell = cells.getCell("M" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("N" + Integer.toString(i));	//cell.setValue(tongttckct);
					ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttckct );
					cell = cells.getCell("O" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("P" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("Q" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}
				else
				{
					cell = cells.getCell("M" + Integer.toString(i));	//cell.setValue(tongttckct);
					ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttckct );
					cell = cells.getCell("N" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("O" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("P" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}
				i++;
				
				//In Tong cong cuoi cung.
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("G" + Integer.toString(i));	//cell.setValue("Tổng cộng :");
			    ReportAPI.getCellStyle_Border(cell,Color.BLACK,true, 9, "Tổng cộng :" );					
				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("J" + Integer.toString(i));	//cell.setValue(tongsl);
				ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongsl );
				cell = cells.getCell("K" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
				cell = cells.getCell("L" + Integer.toString(i));	//cell.setValue(tongtt);
				ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongtt );
				if(obj.getFlagnpp().equals("1"))
				{
					cell = cells.getCell("M" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("N" + Integer.toString(i));	//cell.setValue(tongttck);
				    ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttck );
					cell = cells.getCell("O" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("P" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("Q" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}
				else
				{
					cell = cells.getCell("M" + Integer.toString(i));	//cell.setValue(tongttck);
				    ReportAPI.getCellStyle_double(cell,"Times New Roman",Color.BLACK,true, 9, tongttck );
					cell = cells.getCell("N" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("O" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("P" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}
				
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
	
	private void CreateStaticHeader(Workbook workbook, IBcDoanhThuSanPhamList obj, String userTen)
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
	   	
	   	cells.merge(2, 0, 2, 8);
	   	cells.merge(3, 0, 3, 8);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A3");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.CENTER);// canh le cho chu 	        
	    
	    cell.setStyle(style);
	    String tieude = "CHI TIẾT DOANH THU THEO MẶT HÀNG";
	    ReportAPI.getCellStyle(cell,Color.BLACK, true, 14, tieude);
	    
	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("A4");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.CENTER);// canh le cho chu
	    cell.setStyle(style);
	    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Từ ngày " + obj.getTuNgay() + "  Đến ngày " + obj.getDenNgay() );
	    		
	    cells.setColumnWidth(0, 15);
		cell = cells.getCell("A6");	cell.setValue("Ngày ctừ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);		

		cells.setColumnWidth(1, 10);
		cell = cells.getCell("B6");	cell.setValue("Số ctừ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(2, 10);
		cell = cells.getCell("C6");	cell.setValue("Số HD");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(3, 15);
		cell = cells.getCell("D6");	cell.setValue("Mã KH");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
		cells.setColumnWidth(4, 30);
		cell = cells.getCell("E6");	cell.setValue("Tên KH");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(5, 15);
		cell = cells.getCell("F6");	cell.setValue("Mã HH/VT");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
		cells.setColumnWidth(6, 30);
		cell = cells.getCell("G6");	cell.setValue("Tên hàng");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(7, 10);
		cell = cells.getCell("H6");	cell.setValue("Dvt");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

		cells.setColumnWidth(8, 15);
		cell = cells.getCell("I6");	cell.setValue("Lô");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
	
		cells.setColumnWidth(9, 10);
		cell = cells.getCell("J6");	cell.setValue("Số lượng");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(10, 10);
		cell = cells.getCell("K6");	cell.setValue("Đơn giá");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(11, 10);
		cell = cells.getCell("L6");	cell.setValue("T. tiền");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		if(obj.getFlagnpp().equals("1"))
		{
			cells.setColumnWidth(12, 10);
			cell = cells.getCell("M6");	cell.setValue("Tiền CK/GG");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(13, 10);
			cell = cells.getCell("N6");	cell.setValue("T. tiền CK/GG");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(14, 10);
			cell = cells.getCell("O6");	cell.setValue("Ghi chú");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(15, 10);
			cell = cells.getCell("P6");	cell.setValue("KV");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(16, 10);
			cell = cells.getCell("Q6");	cell.setValue("DV");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		}
		else
		{
			cells.setColumnWidth(12, 10);
			cell = cells.getCell("M6");	cell.setValue("T. tiền CK/GG");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(13, 10);
			cell = cells.getCell("N6");	cell.setValue("Ghi chú");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(14, 10);
			cell = cells.getCell("O6");	cell.setValue("KV");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(15, 10);
			cell = cells.getCell("P6");	cell.setValue("DV");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		}
	}
}
