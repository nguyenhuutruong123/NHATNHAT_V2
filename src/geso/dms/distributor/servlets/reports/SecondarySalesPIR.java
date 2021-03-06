package geso.dms.distributor.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

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
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import java.util.*;

public class SecondarySalesPIR extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public SecondarySalesPIR()
	{
		super();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String TT=request.getParameter("view")==null?"":request.getParameter("view");
		obj.setTrungtam(TT);
		obj.setuserId(userId);
		obj.setdiscount("1");
		obj.setvat("0");
		obj.setdvdlId("");
		obj.settype("1");
		String nppId = util.getIdNhapp(userId);
		obj.getRsnpp(nppId);
		
		
		obj.init();
		
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Distributor/SecondarySalesPurchaseInventoryReport.jsp";
		response.sendRedirect(nextJSP);
	}
	
	private String getPiVotName()
	{
		String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String name = sdf.format(cal.getTime());
		name = name.replaceAll("-", "");
		name = name.replaceAll(" ", "_");
		name = name.replaceAll(":", "");
		return "_" + name;
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		IStockintransit obj = new Stockintransit();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		
		
		Utility util = new Utility();
		if (userId == null)
			userId = "";
		obj.setuserId(userId);
		geso.dms.distributor.util.Utility Ult = new geso.dms.distributor.util.Utility();
		
		
		String TT=request.getParameter("view")==null?"":request.getParameter("view");
		obj.setTrungtam(TT);
		
		String nppId = "";
		if(TT.equals("TT"))
		{
			nppId = request.getParameter("nppId");
		}
		else
		{
			 nppId = Ult.getIdNhapp(userId);
		}
		if(nppId == null) nppId = "";
		obj.setnppId(nppId);
		
		obj.getRsnpp(nppId);
		
		System.out.println("nha phan phoi id"+nppId);
		
		String nppTen = Ult.getTenNhaPP();
		obj.setuserTen(userTen);
		
		
		
		String kenhId = request.getParameter("kenhId"); // <!---
		if (kenhId == null)
			kenhId = "";
		obj.setkenhId(kenhId);
		
		String dvkdId = request.getParameter("dvkdId"); // <!---
		if (dvkdId == null)
			dvkdId = "";
		obj.setdvkdId(dvkdId);
		
		String nhanhangId = request.getParameter("nhanhangId"); // <!---
		if (nhanhangId == null)
			nhanhangId = "";
		obj.setnhanhangId(nhanhangId);
		
		
		String chungloaiId = request.getParameter("chungloaiId");// <!---
		if (chungloaiId == null)
			chungloaiId = "";
		obj.setchungloaiId(chungloaiId);
		
		String tungay = request.getParameter("Sdays"); // <!---
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);
		
		String denngay = request.getParameter("Edays");// <!---
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		
		String khoId = request.getParameter("khoId"); // <!---
		if (khoId == null)
			khoId = "";
		obj.setkhoId(khoId);
		
		String vat = request.getParameter("vat");
		obj.setvat(vat);
		
		String discount = request.getParameter("discount");
		obj.setdiscount(discount);
		
		String instransit = request.getParameter("instransit");
		obj.setHangDiDuong(instransit);
		
		String nppids = request.getParameter("nppids");
		if(nppids==null)
			nppids="";
		obj.setNppids(nppids);
		
		
		System.out.println("nha phan phoi idssssss"+nppids);
		
		System.out.println("TT=============================="+TT);
		if(nppids.length()>0)
			obj.setnppId(nppids);
		System.out.println("nha phan phoi "+obj.getnppId() +" npp ids "+obj.getNppids());
		obj.setsanphamId(util.antiSQLInspection(request.getParameter("sanphamId")) != null ? util.antiSQLInspection(request.getParameter("sanphamId")) : "");
		obj.setnhanhangId(util.antiSQLInspection(request.getParameter("nganhhangId")) != null ? util.antiSQLInspection(request.getParameter("nganhhangId")) : "");
		obj.setHoaDonKmDb(util.antiSQLInspection(request.getParameter("hdkmdb")) != null ? util.antiSQLInspection(request.getParameter("hdkmdb")) : "");
		obj.setHangDiDuong(util.antiSQLInspection(request.getParameter("instransit")) != null ? util.antiSQLInspection(request.getParameter("instransit")) : "");
		
		obj.settype(util.antiSQLInspection(request.getParameter("type")) != null ? util.antiSQLInspection(request.getParameter("type")) : "");	
		obj.setLaytheo(util.antiSQLInspection(request.getParameter("laysolo")) != null ? util.antiSQLInspection(request.getParameter("laysolo")) : "");
		obj.setLayluongchuyenkho(util.antiSQLInspection(request.getParameter("layluongck")) != null ? util.antiSQLInspection(request.getParameter("layluongck")) : "0");
		
		System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::"+obj.getLayluongchuyenkho());
		
		String[] fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);
		
		String[] fieldsAn = request.getParameterValues("fieldsAn");
		obj.setFieldHidden(fieldsAn);
		String action = request.getParameter("action");
		if (action.equals("tao"))
		{
			String error = "";
			if(tungay.trim().length() <=0)
				error += "\n Vui l??ng ch???n t??? ng??y";
			if(denngay.trim().length() <=0)
				error += "\n Vui l??ng ch???n ?????n ng??y";
			if(obj.getnppId() == null || obj.getnppId().trim().length() <=0)
			{
				error += "\n Vui l??ng ch???n nh?? ph??n ph???i";
			}
			
			if(error.length() > 0)
			{
				obj.setMsg(error);
			}
			else
			{
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "Attachment; filename=BaoCaoXuatNhapTon(NPP)" + this.getPiVotName() + ".xlsm");
				OutputStream out = response.getOutputStream();
				try
				{
					
					if(obj.gettype().equals("0"))
					{
						CreatePivotTable(out, response, request, fieldsHien, obj); 
						System.out.println("vao 1111111111111111111111111111");
					}else 
					{
						CreatePivotTable_ByProduct(out, response, request, fieldsHien, obj);
						System.out.println("vao 22222222222222222222222222222");
					}
					obj.getDb().shutDown();
				} catch (Exception ex)
				{
					ex.printStackTrace();
					obj.setMsg(ex.getMessage());
					request.getSession().setAttribute("errors", ex.getMessage());
				}
				return;
			}
		}
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		
		String nextJSP = request.getContextPath() + "/pages/Distributor/SecondarySalesPurchaseInventoryReport.jsp";
		response.sendRedirect(nextJSP);
		
	}
	
	
	private void CreatePivotTable_ByProduct(OutputStream out, HttpServletResponse response, HttpServletRequest request, String[] fieldsHien, IStockintransit obj) throws Exception
	{
		try
		{
			String strfstream = getServletContext().getInitParameter("path") + "\\BaoCaoXNT_TheoSanPham.xlsm";
			FileInputStream fstream = new FileInputStream(strfstream);
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			CreateHeader_TheoSanPham(workbook, obj);
			FillData_TheoSanPham(workbook, fieldsHien, obj);
			System.out.println("vao 2222222222222222222222222222222S----1111111111111111111111111111111111");

			workbook.save(out);
			fstream.close();
			
		} catch (Exception ex)
		{
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	
	private void FillData_TheoSanPham(Workbook workbook, String[] fieldsHien, IStockintransit obj) throws Exception
  {
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		
		Cells cells = worksheet.getCells();
		
		dbutils db = new dbutils();
		
		String condition="";
		if(obj.getkhoId().length()>0)
			condition+= " and kho_fk= "+obj.getkhoId();
		if(obj.getsanphamId().length()>0)
			condition+= " and sanpham_fk= "+obj.getsanphamId();
		if(obj.getkenhId().length()>0)
			condition+= " and kbh_fk= "+obj.getkenhId();
		
		String query=  " select  sp.GIABANLECHUAN ,( select ten from kho where pk_seq = dat.kho_fk ) tenkho,dat.spma,dat.spten,dat.npp_fk,dat.kbh_fk,dat.kho_fk,dat.sanpham_fk,dat.dvten, \n"+
					   "	 ISNULL(dat.[T???n ?????u],0) as tondau,ISNULL(dat.[Nh???p],0) as nhap,ISNULL(dat.[Xu???t],0) as xuat, \n"+
					   "	 ISNULL(dat.[T???n ?????u],0)  + ISNULL(dat.[Nh???p],0)  +ISNULL(dat.[Xu???t],0) as toncuoi from ( \n"+
					   "	select * from [baocao_XNT_total_tungay_denngay]("+obj.getnppId()+",'"+obj.gettungay()+"','"+obj.getdenngay()+"') where 1=1 "+ condition+" ) \n"+
					   "	as data pivot (sum(xnt) for nghiepvu in ([T???n ?????u],[Nh???p],[Xu???t])) as dat \n"+
					   " inner join BANGGIABLC_SANPHAM sp on sp.SANPHAM_FK=dat.sanpham_fk \n"+
					   " where sp.BGBLC_FK=(select top(1) pk_seq from BANGGIABANLECHUAN order by tungay DESC )\n";
		System.out.println("query xnt tong "+query);
		ResultSet rs =db.get(query);
		int index = 12;
		Cell cell = null;
		int SoTT=0;
		while (rs.next())
		{
			SoTT++;
			
			cell = cells.getCell("A" + String.valueOf(index)); cell.setValue(SoTT);
			 ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 3);
			 Style	  style = cell.getStyle();
				style.setHAlignment(TextAlignmentType.LEFT);
				 style.setVAlignment(TextAlignmentType.LEFT);
				 cell.setStyle(style);
			 
			 
			cell = cells.getCell("B" + String.valueOf(index)); cell.setValue( rs.getString("spMa")  );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 0);
			style.setHAlignment(TextAlignmentType.LEFT);
			 style.setVAlignment(TextAlignmentType.LEFT);
			 cell.setStyle(style);
			
			
			cell = cells.getCell("C" + String.valueOf(index)); cell.setValue( rs.getString("spTen")  );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 0);
			style.setHAlignment(TextAlignmentType.LEFT);
			 style.setVAlignment(TextAlignmentType.LEFT);
			 cell.setStyle(style);
			
			cell = cells.getCell("D" + String.valueOf(index)); cell.setValue( rs.getString("dvTen")  );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 0);
			style.setHAlignment(TextAlignmentType.LEFT);
			 style.setVAlignment(TextAlignmentType.LEFT);
			 cell.setStyle(style);
			
			
			cell = cells.getCell("E" + String.valueOf(index)); cell.setValue( rs.getDouble("TonDau")  );
			 ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 0);
			
			
			cell = cells.getCell("F" + String.valueOf(index)); cell.setValue( rs.getDouble("Nhap")  );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 0);
			
			
			cell = cells.getCell("G" + String.valueOf(index)); cell.setValue( rs.getDouble("Xuat")*(-1)  );

			 ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false,0);
			
			
			cell = cells.getCell("H" + String.valueOf(index)); cell.setValue( rs.getDouble("toncuoi")   );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 43);
			
			cell = cells.getCell("I" + String.valueOf(index)); cell.setValue(rs.getString("tenkho")  );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 43);
		
			cell = cells.getCell("J" + String.valueOf(index)); cell.setValue( rs.getDouble("toncuoi")*rs.getDouble("GIABANLECHUAN")  );
			ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.DASHED, false, 43);
		
			
			index++;
		}
		if (index == 2)
		{
			throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
		}
		if (rs != null)
			rs.close();
		if (db != null)
			db.shutDown();
	  
  }

	private void CreateHeader_TheoSanPham(Workbook workbook, IStockintransit obj)
  {
		Worksheets worksheets = workbook.getWorksheets();
    Worksheet worksheet = worksheets.getSheet(0);	    
    worksheet.setName("Sheet1");
    Cells cells = worksheet.getCells();	 
    
    cells.setRowHeight(0, 20.0f);	    
    Cell cell = cells.getCell("A1");	
    
    Style style = cell.getStyle();
    style.setTextWrapped(true);
    style = cell.getStyle();
    com.aspose.cells.Font font = style.getFont();
    font.setName("Times New Roman");

	  cell.setStyle(style); 
	 dbutils db = new dbutils();
	 
	 
	 ResultSet rs = db.get("select TEN as nppTen,Ma as nppMa,DIACHI as diachi,FAX,DIENTHOAI,MaKho,MASOTHUE,DIACHIXHD,XUATTAIKHO from NHAPHANPHOI where pk_Seq='"+obj.getnppId()+"'");
	 String nppTen="";
	 String diachi = "";
	 String fax="";
	 String dienthoai="";
	 String diachixuatHD="";
	 String xuattaikho="";
	 String makho="";
	try
	{
		while(rs.next())
		{
			nppTen= rs.getString("nppTen");
			diachi= rs.getString("diachi");
			fax= rs.getString("fax");
			dienthoai= rs.getString("dienthoai");
			diachixuatHD= rs.getString("DIACHIXHD");
			xuattaikho= rs.getString("xuattaikho");
			makho= rs.getString("makho");
			
		}
		if(rs!=null)rs.close();
		if(db!=null)db.shutDown();
		
	} catch (SQLException e)
	{
		e.printStackTrace();
	}
	
	cell = cells.getCell("A"+String.valueOf(1));		cell.setValue(nppTen);cell.setStyle(style);
  ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.NONE, false, 0);
  
  cell = cells.getCell("A"+String.valueOf(2));		cell.setValue("?????a ch???: " +diachi);cell.setStyle(style);
  ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.NONE, false, 0);

  
  cell = cells.getCell("A"+String.valueOf(3));		cell.setValue("??i???n tho???i: " +dienthoai +"  - "+" Fax: "+fax);cell.setStyle(style);
  ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.NONE, false, 0);

	
	    
   cell = cells.getCell("A"+String.valueOf(5));		cell.setValue("T???NG H???P XU???T NH???P T???N");cell.setStyle(style);
   ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.NONE, true, 0);
   ReportAPI.mergeCells(worksheet, 4, 4, 0, 7);
   style = cell.getStyle();
	  style.setHAlignment(TextAlignmentType.CENTER);
	  style.setVAlignment(TextAlignmentType.CENTER);
	  cell.setStyle(style);
   
    
   cell = cells.getCell("A"+String.valueOf(6));		cell.setValue("Kho :"+makho+" - "+xuattaikho);cell.setStyle(style);
   ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.NONE, false, 0);
   ReportAPI.mergeCells(worksheet, 5, 5, 0, 7);
   style = cell.getStyle();
	  style.setHAlignment(TextAlignmentType.CENTER);
	  style.setVAlignment(TextAlignmentType.CENTER);
	  cell.setStyle(style);
   
   
   
   cell = cells.getCell("A"+String.valueOf(7));		cell.setValue("T??? ng??y "+obj.gettungay() +" -?????n ng??y :"+obj.getdenngay());cell.setStyle(style);
   ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.NONE, false , 0);
  ReportAPI.mergeCells(worksheet, 6, 6, 0, 7);
  style = cell.getStyle();
  style.setHAlignment(TextAlignmentType.CENTER);
  style.setVAlignment(TextAlignmentType.CENTER);
  cell.setStyle(style);
    
   
    
  }

	private void CreatePivotTable(OutputStream out, HttpServletResponse response, HttpServletRequest request, String[] fieldsHien, IStockintransit obj) throws Exception
	{
		try
		{
			
			String 	strfstream = getServletContext().getInitParameter("path") + "\\BaoCaoXuatNhapTon_ChiTiet.xlsm";
			
			FileInputStream fstream = new FileInputStream(strfstream);
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
	
				CreateHeader(workbook, obj);
				FillData(workbook, fieldsHien, obj);
		
			workbook.save(out);
			fstream.close();
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	
	
	
	
	private void FillData(Workbook workbook, String[] fieldsHien, IStockintransit obj) throws Exception
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		
		Cells cells = worksheet.getCells();
		
		dbutils db = new dbutils();
		
		String condition="";
		if(obj.getkhoId().length()>0)
			condition+= " and data.kho_fk= "+obj.getkhoId();
		if(obj.getsanphamId().length()>0)
			condition+= " and data.sanpham_fk= "+obj.getsanphamId();
		if(obj.getkenhId().length()>0)
			condition+= " and data.kbh_fk= "+obj.getkenhId();
		
		String query=" select kh.TEN as kho,kenh.TEN as kenh,spMa,spTen,SoLo,NgayHetHan,sum(XNT) as soluong,nghiepvu \n"+
					 "	,sps.khoErp_fk,abs(sum(XNT) * sp.GIABANLECHUAN) as thanhtien \n"+
					 " 	 from ufn_XNT_ChiTiet_NGAYNHAPKHO_tungay_denngay_nghiepvu("+obj.getnppId()+",'"+obj.gettungay()+"','"+obj.getdenngay()+"') data  \n"+
					 "	 inner join  BANGGIABLC_SANPHAM sp on sp.SANPHAM_FK=data.sanpham_fk \n"+
					 "	 inner join SANPHAM sps on sps.PK_SEQ=data.sanpham_fk \n"+ 
					 "	 inner join KHO kh on kh.PK_SEQ=data.kho_fk \n"+
					 "	 inner join KENHBANHANG kenh on kenh.PK_SEQ=data.kbh_fk \n"+
					 "	where sp.BGBLC_FK=(select top(1) pk_seq from BANGGIABANLECHUAN order by tungay DESC ) "+condition+" \n"+
					 "	 group by spMa,spTen,SoLo,NgayHetHan,nghiepvu,sps.khoErp_fk, sp.GIABANLECHUAN,kh.TEN,kenh.TEN order by nghiepvu \n";

		ResultSet	rs = db.get(query);
		System.out.println("vao detail:::::::::::::::::::::::::::::" +query);
		
		int index = 2;
		Cell cell = null;
		while (rs.next())
		{
			cell = cells.getCell("EA" + String.valueOf(index)); cell.setValue(rs.getString("kho"));
			cell = cells.getCell("EB" + String.valueOf(index));cell.setValue(rs.getString("kenh"));
			cell = cells.getCell("EC" + String.valueOf(index));cell.setValue(rs.getString("spma"));
			cell = cells.getCell("ED" + String.valueOf(index));cell.setValue(rs.getString("spten"));
			cell = cells.getCell("EE" + String.valueOf(index));cell.setValue(rs.getString("solo"));
			cell = cells.getCell("EF" + String.valueOf(index));cell.setValue(rs.getDouble("soluong"));
			cell = cells.getCell("EG" + String.valueOf(index));cell.setValue(rs.getString("ngayhethan"));
			cell = cells.getCell("EH" + String.valueOf(index));cell.setValue(rs.getDouble("thanhtien"));
			cell = cells.getCell("EI" + String.valueOf(index));cell.setValue(rs.getString("nghiepvu"));
					
			index++;
		}
		if (index == 2)
		{
			throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
		}
		if (rs != null)
			rs.close();
		if (db != null)
			db.shutDown();
		
	}
	
	private void CreateHeader(Workbook workbook, IStockintransit obj) throws Exception
	{
		
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		
		Cells cells = worksheet.getCells();
		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		ReportAPI.getCellStyle(cell, Color.RED, true, 14, "XU???T NH???P T???N");
		
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "T??? ng??y : " + obj.gettungay() + "   ?????n ng??y: " + obj.getdenngay());
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ng??y t???o: " + obj.getDateTime());
		
		cell = cells.getCell("B3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "T???o b???i : " + obj.getuserTen());
		
		String message = "";
		
		cells.setRowHeight(2, 18.0f);
		cell = cells.getCell("A5");
		ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);
		
		cell = cells.getCell("EA1");
		cell.setValue("Kho");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EB1");
		cell.setValue("kenhbanhang");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EC1");
		cell.setValue("masanpham");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("ED1");
		cell.setValue("tensanpham");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EE1");
		cell.setValue("solo");
		ReportAPI.setCellHeader(cell);
	
		cell = cells.getCell("EF1");
		cell.setValue("soluong");
		ReportAPI.setCellHeader(cell);
	
		cell = cells.getCell("EG1");
		cell.setValue("ngayhethan");
		ReportAPI.setCellHeader(cell);
	
		cell = cells.getCell("EH1");
		cell.setValue("thanhtien");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EI1");
		cell.setValue("nghiepvu");
		ReportAPI.setCellHeader(cell);
		
		
		
		
	}
	
}

