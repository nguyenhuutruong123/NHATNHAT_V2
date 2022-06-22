package geso.dms.center.servlets.chiso;

import geso.dms.center.beans.report.IBcDoanhThuKhachHangList;
import geso.dms.center.beans.report.imp.BcDoanhThuKhachHangList;
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


public class ChiSoDoanhThuNVSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		IBcDoanhThuKhachHangList obj;
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
		
		
		obj = new BcDoanhThuKhachHangList();
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
		
		nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuNV.jsp";
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
	    
	    IBcDoanhThuKhachHangList obj = new BcDoanhThuKhachHangList();
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
					response.setHeader("Content-Disposition", "attachment; filename=BcDoanhThuSPTheoKhachHang.xlsm");
					//FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BcDoanhThuKhachHang.xlsm");
					Workbook workbook = new Workbook();
					//workbook.open(fstream);
					workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
					
					obj.setUserId(userId);
					//obj.xuatexcel("");
					obj.init2("");
					String query=obj.getQueryHd();
					System.out.println(" \n [excel query]:   " + query);
					
					if(obj.getxemtheo()==1) // xem theo khach hang
					{
						CreateStaticHeader(workbook, obj, userTen);
						FillData(workbook,obj, query);
					}
					else // xem theo trinh duoc vien
					{
						CreateStaticHeader2(workbook, obj, userTen);
						FillData2(workbook,obj, query);
					}

					
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
	    	obj.init2("");
	    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
	    	session.setAttribute("obj", obj);
	    	
	    	String 	nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuNV.jsp";
	    	response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	obj.init2("");
	    	System.out.println(" \n ChiSoDoanhThuNVSVL:  " + obj.getQueryHd());
			session.setAttribute("obj", obj);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/ChiSoDoanhThuNV.jsp";
			response.sendRedirect(nextJSP);  
	    }
	}
	
	private boolean FillData(Workbook workbook, IBcDoanhThuKhachHangList obj, String query) throws Exception
   {
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();		
		ResultSet hdRs = db.get(query);
		DecimalFormat df = new DecimalFormat("0.###");
		//double DonGia_AVG=0;
		double total_BVAT=0;
		double total_AVAT=0;
		double total_AVAT_trutralai = 0;
		double total_VAT=0;
		double total_AVAT_1KH=0;
		double dongia = 0;
		String biet_maKHs = "";
		String biet_maKHmoi = "";
		String biet_tenNVBHmoi = "";
		String biet_khMaHD = "";
		String biet_tenKHmoi = "";
		int demSP = 0 ;
		int i = 9;
		int SoTt=1;
		if (hdRs != null) 
		{
			try 
			{
				Cell cell = null;
				while (hdRs.next()) 
				{					
					if(biet_maKHs.indexOf(hdRs.getString("khMA"))==-1)
					{
						if(biet_maKHs.equals("")==false)
						{
							//cells.merge(i-1, 2, i-1, 3); //merge o cot C & D
							cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
							cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(biet_tenNVBHmoi);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
							cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(biet_maKHmoi + " Total" );
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(biet_khMaHD);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
							cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(biet_tenKHmoi);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
							cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
							cell = cells.getCell("G" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
							cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(demSP);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("I" + Integer.toString(i));	cell.setValue("" );
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
							cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(total_AVAT_1KH);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							i=i+1;
							SoTt = 1;
							demSP = 0;
							total_AVAT_1KH = 0;
						}
						
						biet_maKHs = biet_maKHs + hdRs.getString("khMA") + ",";
						biet_maKHmoi = hdRs.getString("khMA");
						biet_tenNVBHmoi = hdRs.getString("tdvTen");
						biet_khMaHD = hdRs.getString("khMAHD");
						biet_tenKHmoi = hdRs.getString("khTEN");
					}
					
					if(hdRs.getString("spMa").equals("")==false)
					{
						demSP++;
					}
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(SoTt++ );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(hdRs.getString("tdvTen"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("khMA"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);			
					
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("khMAHD"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getString("khTEN"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getString("spMa"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(hdRs.getString("spTen"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(Double.parseDouble(df.format(hdRs.getDouble("SoLuong"))));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					if(hdRs.getDouble("SoLuong") != 0)
					{
						dongia = hdRs.getDouble("DOANHSO")/hdRs.getDouble("SoLuong");
					}
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(dongia);//cell.setValue(hdRs.getString("DonGia"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(hdRs.getDouble("DOANHSO"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue(hdRs.getDouble("DOANHSOTRUTRALAI"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
											
					//total_BVAT += (hdRs.getDouble("BVAT"));
					//total_VAT +=  (hdRs.getDouble("VAT"));
					total_AVAT += (hdRs.getDouble("DOANHSO"));
					total_AVAT_trutralai += (hdRs.getDouble("DOANHSOTRUTRALAI"));
					//total_AVAT_1KH += (hdRs.getDouble("AVAT"));
					++i;
				}
				//In "... Total" o dong cuoi cung cua bang
				//cells.merge(i-1, 2, i-1, 3);				
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(biet_tenNVBHmoi);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);	
				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(biet_maKHmoi + " Total" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(biet_khMaHD);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);	
				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(biet_tenKHmoi);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);				
				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(demSP);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(total_AVAT_1KH);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
				
				
				//In Tong cong cuoi cung.
				cells.merge(6, 0, 6, 1);
				cell = cells.getCell("A" + Integer.toString(7)); cell.setValue("Tổng cộng");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell("B" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("C" + Integer.toString(7)); cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("D" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("E" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("F" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("G" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("H" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("I" + Integer.toString(7)); cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				
				cell = cells.getCell("J" + Integer.toString(7)); cell.setValue(Math.round(total_AVAT));			
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);
				
				cell = cells.getCell("K" + Integer.toString(7)); cell.setValue(Math.round(total_AVAT_trutralai));			
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);

				if (hdRs != null) hdRs.close();
				if(db != null) db.shutDown();
				if(i==2){ throw new Exception("Không có báo cáo với điều kiện đã chọn."); }
			}catch(Exception ex){ ex.printStackTrace(); throw new Exception(ex.getMessage()); }
		}
		else { return false; }		
		return true;
  }

	private boolean FillData2(Workbook workbook, IBcDoanhThuKhachHangList obj, String query) throws Exception
	{
		System.out.println("filldata2");
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();		
		ResultSet hdRs = db.get(query);
		
		DecimalFormat df = new DecimalFormat("0.###");
		
		double DonGia_AVG=0;
		double total_BVAT=0;
		double total_AVAT=0;
		double total_AVAT_Trutralai = 0;
		double total_VAT=0;
		double total_AVAT_1NVBH=0;
		double dongia = 0;
		String biet_maNVBHs = "";
		String biet_tenNVBHmoi = "";
		int biet_slsp_1NVBH = 0;
		int i = 9;
		int SoTt=1;
		if (hdRs != null) 
		{
			try 
			{
				Cell cell = null;
				while (hdRs.next()) 
				{					
					if(biet_maNVBHs.indexOf(hdRs.getString("tdvTen"))==-1)
					{
						if(biet_maNVBHs.equals("")==false)
						{
							//cells.merge(i-1, 2, i-1, 3);
							cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
							cell = cells.getCell("B" + Integer.toString(i));	//cell.setValue(biet_tenNVBHmoi);
							cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
							cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
							cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
							cell = cells.getCell("E" + Integer.toString(i));	//cell.setValue(biet_slsp_1NVBH); // demSP
							cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
							cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);		
							cell = cells.getCell("G" + Integer.toString(i));	//cell.setValue(total_AVAT_1NVBH);
							cell.setValue("");
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
							i=i+1;
							SoTt = 1;
							biet_slsp_1NVBH = 0;
							total_AVAT_1NVBH = 0;
						}
						biet_maNVBHs = biet_maNVBHs + hdRs.getString("tdvTen") + ",";
						biet_tenNVBHmoi = hdRs.getString("tdvTen");
					}
					
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(SoTt++ );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(hdRs.getString("tdvTen"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("spMa"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("spTen"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(Integer.parseInt(df.format(hdRs.getDouble("SoLuong"))) );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					if(hdRs.getDouble("SoLuong") != 0)
					{
						dongia = hdRs.getDouble("DOANHSO")/hdRs.getDouble("SoLuong");
					}

					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(dongia);//cell.setValue(hdRs.getString("DonGia"));					
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(hdRs.getDouble("DOANHSO"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(hdRs.getDouble("DOANHSOTRUTRALAI"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
											
					/*total_BVAT += (hdRs.getDouble("BVAT"));
					total_VAT +=  (hdRs.getDouble("VAT"));
					total_AVAT += (hdRs.getDouble("AVAT"));
					total_AVAT_1NVBH += (hdRs.getDouble("AVAT"));*/
					
					total_AVAT += (hdRs.getDouble("DOANHSO"));
					total_AVAT_Trutralai += (hdRs.getDouble("DOANHSOTRUTRALAI"));
					//biet_slsp_1NVBH = hdRs.getInt("sosp");
					++i;
				}
				//In "... Total" o dong cuoi cung cua bang
				//cells.merge(i-1, 2, i-1, 3);				
				
				/*cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(biet_tenNVBHmoi);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);	
				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);	
				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);						
				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(biet_slsp_1NVBH);//demSP
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);							
				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(total_AVAT_1NVBH);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);*/
				
				//In Tong cong cuoi cung.
				cells.merge(6, 0, 6, 1);
				cell = cells.getCell("A" + Integer.toString(7));	cell.setValue("Tổng cộng");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				cell = cells.getCell("B" + Integer.toString(7));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("C" + Integer.toString(7));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("D" + Integer.toString(7));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("E" + Integer.toString(7));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("F" + Integer.toString(7));	cell.setValue("" );
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				cell = cells.getCell("G" + Integer.toString(7));	cell.setValue(Math.round(total_AVAT));			
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);
				cell = cells.getCell("H" + Integer.toString(7));	cell.setValue(Math.round(total_AVAT_Trutralai));			
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 41);
				if (hdRs != null) hdRs.close();
				if(db != null) db.shutDown();
				if(i==2){ throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!"); }
			} catch(Exception ex){ ex.printStackTrace(); throw new Exception(ex.getMessage()); }
		}
		else { return false; }		
		return true;
	}
	
	private void CreateStaticHeader(Workbook workbook, IBcDoanhThuKhachHangList obj, String userTen)
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
	   	
	   	cells.merge(0, 0, 0, 9);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.CENTER);// canh le cho chu 	        
	    
	    String tieude = "DOANH THU SẢN PHẨM THEO KHÁCH HÀNG";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	    
	    
	    String message = "";
			cells.setRowHeight(2, 18.0f);
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);   

	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Từ ngày : " + obj.getTuNgay() + "" );
	    
	    
	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("B4"); 
	    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Đến ngày : " + obj.getDenNgay() + "" );
		
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A5");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A6");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  "  + userTen  );
	    		
	    cells.setColumnWidth(0, 10);
		cell = cells.getCell("A8");	cell.setValue("STT");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);		

		cells.setColumnWidth(1, 30);
		cell = cells.getCell("B8");	cell.setValue("NHÂN VIÊN BÁN HÀNG");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(2, 15);
		cell = cells.getCell("C8");	cell.setValue("MÃ KH");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(3, 15);
		cell = cells.getCell("D8");	cell.setValue("MÃ HỢP ĐỒNG");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
		cells.setColumnWidth(4, 30);
		cell = cells.getCell("E8");	cell.setValue("TÊN KHÁCH HÀNG");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(5, 15);
		cell = cells.getCell("F8");	cell.setValue("MÃ SP");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
		cells.setColumnWidth(6, 30);
		cell = cells.getCell("G8");	cell.setValue("TÊN SP");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(7, 10);
		cell = cells.getCell("H8");	cell.setValue("SỐ LƯỢNG");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

		cells.setColumnWidth(8, 20);
		cell = cells.getCell("I8");	cell.setValue("ĐƠN GIÁ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
	
		cells.setColumnWidth(9, 20);
		cell = cells.getCell("J8");	cell.setValue("DOANH SỐ");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		cells.setColumnWidth(9, 20);
		cell = cells.getCell("K8");	cell.setValue("DOANH SỐ TRỪ TRẢ LẠI");
		ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
  }
	
	private void CreateStaticHeader2(Workbook workbook, IBcDoanhThuKhachHangList obj, String userTen)
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
		   	
		   	cells.merge(0, 0, 0, 9);
		   	
		    cells.setRowHeight(0, 20.0f);
		    Cell cell = cells.getCell("A1");
		    style = cell.getStyle();
		    style.setFont(font); 
		    style.setHAlignment(TextAlignmentType.CENTER);// canh le cho chu 	        
		    
		    String tieude = "DOANH THU SẢN PHẨM THEO KHÁCH HÀNG";
		    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
		    
		    
		    String message = "";
				cells.setRowHeight(2, 18.0f);
				cell = cells.getCell("A3");
				ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);   

		    cells.setRowHeight(3, 18.0f);
		    cell = cells.getCell("A4");
		    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Từ ngày : " + obj.getTuNgay() + "" );
		    
		    
		    cells.setRowHeight(3, 18.0f);
		    cell = cells.getCell("B4"); 
		    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Đến ngày : " + obj.getDenNgay() + "" );
			
		    cells.setRowHeight(4, 18.0f);
		    cell = cells.getCell("A5");
		    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
		    
		    cells.setRowHeight(5, 18.0f);
		    cell = cells.getCell("A6");
		    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  "  + userTen  );
		    		
		    cells.setColumnWidth(0, 10);
			cell = cells.getCell("A8");	cell.setValue("STT");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);		

			cells.setColumnWidth(1, 30);
			cell = cells.getCell("B8");	cell.setValue("NHÂN VIÊN BÁN HÀNG");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
						
/*			cells.setColumnWidth(2, 15);
			cell = cells.getCell("C8");	cell.setValue("MÃ HỢP ĐỒNG");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);*/
			
			cells.setColumnWidth(2, 15);
			cell = cells.getCell("C8");	cell.setValue("MÃ SP");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					
			cells.setColumnWidth(3, 30);
			cell = cells.getCell("D8");	cell.setValue("TÊN SP");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(4, 10);
			cell = cells.getCell("E8");	cell.setValue("SỐ LƯỢNG");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

			cells.setColumnWidth(5, 20);
			cell = cells.getCell("F8");	cell.setValue("ĐƠN GIÁ");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
			cells.setColumnWidth(6, 20);
			cell = cells.getCell("G8");	cell.setValue("DOANH SỐ");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			cells.setColumnWidth(7, 20);
			cell = cells.getCell("H8");	cell.setValue("DOANH SỐ TRỪ TRẢ LẠI");
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
	  }
}
