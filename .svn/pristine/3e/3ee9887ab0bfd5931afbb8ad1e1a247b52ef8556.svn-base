package geso.dms.center.servlets.tieuchithuong;

import geso.dms.center.beans.tieuchithuong.ITieuchithuongTLList;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTL_Tiendo;
import geso.dms.center.beans.tieuchithuong.imp.TieuchithuongTLList;
import geso.dms.center.beans.tieuchithuong.imp.TieuchithuongTL_Tiendo;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Z.DB;

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
import com.cete.dynamicpdf.pageelements.IListProperties;

public class TieuchithuongTLReportSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
    
    public TieuchithuongTLReportSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	
	    
	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    ITieuchithuongTLList obj = new TieuchithuongTLList();
	    obj.setUserId(userId);
	    
	    obj.initReport("");
		session.setAttribute("obj", obj);
	    
	    String nextJSP = "/SGP/pages/Center/TieuChiThuongTLReport.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    HttpSession session = request.getSession();	
	    
	    Utility util = new Utility();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));     
	    ITieuchithuongTLList obj = new TieuchithuongTLList();
	    
	    String userName = (String) session.getAttribute("userTen");  
	    if(userName == null)
	    	userName = "";
	    
	    String thang = request.getParameter("thang");
	    if(thang == null)
	    	thang = "";
	    obj.setThang(thang);
	    
	    String nam = request.getParameter("nam");
	    if(nam == null)
	    	nam = "";
	    obj.setNam(nam);
	    
	    String tungay = request.getParameter("Sdays");
	    if(tungay == null)
	    	tungay = "";
	    obj.setTungay(tungay);
	    
	    String denngay = request.getParameter("Edays");
	    if(denngay == null)
	    	denngay = "";
	    obj.setDenngay(denngay);
	    
	    String xemtheo = request.getParameter("xemtheo");
	    if(xemtheo == null)
	    	xemtheo = "1";
	    System.out.println("___Type la: " + xemtheo);
	    obj.setType(xemtheo);
	    
	    String vungId = request.getParameter("vungId");
	    if(vungId == null)
	    	vungId = "";
	    obj.setVungId(vungId);
	    
	    String khuvucId = request.getParameter("khuvucId");
	    if(khuvucId == null)
	    	khuvucId = "";
	    obj.setKvId(khuvucId);
	    
	    String nppId = request.getParameter("nppId");
	    if(nppId == null)
	    	nppId = "";
	    obj.setNppIds(nppId);
	    
	    String schemeId = request.getParameter("schemeId");
	    if(schemeId == null)
	    	schemeId = "";
	    obj.setSchemeIds(schemeId);
	    
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    if(action.equals("taobc"))
	    {
    		if(obj.getNam().trim().length() <= 0 || obj.getThang().trim().length() <= 0 || obj.getSchemeIds().trim().length() <= 0 )
    		{
    			obj.setUserId(userId);
        		obj.initReport("");
        		
        		if(obj.getSchemeIds().trim().length() <= 0)
    			{
        			obj.setMsg("Vui lòng chọn Scheme.");
    			}
        		else
        		{
        			obj.setMsg("Thời gian bạn chọn không hợp lệ");
        		}

    	    	session.setAttribute("obj", obj);  	
        		session.setAttribute("userId", userId);
    		
        		response.sendRedirect("/SGP/pages/Center/TieuChiThuongTLReport.jsp");
    		}
    		else
    		{
    			response.setContentType("application/xlsm");
    			response.setHeader("Content-Disposition", "attachment; filename=TieuChiThuongTL.xlsm");
    			
    			OutputStream outPv = response.getOutputStream();
    			obj.setUserId(userName);
    			createPivotTable(obj, outPv);
    		}
	    }
	    else
	    {
    		obj.setUserId(userId);
    		obj.initReport("");

	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect("/SGP/pages/Center/TieuChiThuongTLReport.jsp");
	    }
	   
	}

	private void createPivotTable(ITieuchithuongTLList obj, OutputStream outPv)
	{
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();	
		
		try 
		{
			fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\TieuChiThuongTL.xlsm");
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
		     CreateStaticHeader(workbook, obj.getTungay(), obj.getDenngay(), obj.getUserId(), obj);
		     CreateStaticData(workbook, obj);
		    
		     workbook.save(outPv);
				
			fstream.close();
		} 
		catch (Exception e) {}
	}
	
	private void CreateStaticHeader(Workbook workbook, String dateFrom, String dateTo, String UserName, ITieuchithuongTLList obj) 
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
	    
	    String tieude = "THƯỞNG TÍCH LŨY DOANH SỐ";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	         
	    if(obj.getType().trim().equals("1"))
	    {
		    cells.setRowHeight(3, 18.0f);
		    cell = cells.getCell("A2");
		    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Tháng: " + dateFrom );
		    
		    cells.setRowHeight(3, 18.0f);
		    cell = cells.getCell("B2"); 
		    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Năm: " + dateTo );
	    }
	    else
	    {
	    	cells.setRowHeight(3, 18.0f);
		    cell = cells.getCell("A2");
		    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Từ ngày: " + obj.getTungay() );
		    
		    cells.setRowHeight(3, 18.0f);
		    cell = cells.getCell("B2"); 
		    ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Đến ngày: " + obj.getDenngay() );
	    }
		
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + UserName);
	    
	    cell = cells.getCell("EA1"); 	cell.setValue("Vung");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("EB1"); 	cell.setValue("KhuVuc");  ReportAPI.setCellHeader(cell);	     
	    cell = cells.getCell("EC1"); 	cell.setValue("MaNhaPhanPhoi");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("ED1"); 	cell.setValue("TenNhaPhanPhoi");  ReportAPI.setCellHeader(cell); 
	    cell = cells.getCell("EE1"); 	cell.setValue("KhachHang");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("EF1"); 	cell.setValue("DoanhSo");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("EG1"); 	cell.setValue("Scheme");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("EH1"); 	cell.setValue("Muc");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("EI1"); 	cell.setValue("Tiendo");  ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("EJ1"); 	cell.setValue("Thuong");  ReportAPI.setCellHeader(cell);
	}
	
	private boolean CreateStaticData(Workbook workbook, ITieuchithuongTLList obj) 
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
	    
	    
	    String condition = "";
	    if(obj.getVungId().length() > 0)
	    	condition += " and VUNG.pk_seq = '" + obj.getVungId() + "' ";
	    if(obj.getKvId().length() > 0)
	    	condition += " and KHUVUC.pk_seq = '" + obj.getKvId() + "' ";
	    if(obj.getNppIds().length() > 0)
	    	condition += " and NHAPHANPHOI.pk_seq = '" + obj.getNppIds() + "' ";
		
	    System.out.println("Tu ngay: " + obj.getTungay() + "  -- Den ngay: " + obj.getDenngay());
	    
	    String query = "select * from TIEUCHITHUONGTL a inner join TIEUCHITHUONGTL_TIENDO b on a.PK_SEQ = b.thuongtl_fk where a.pK_seq = "+obj.getSchemeIds()+"";
	    List<ITieuchithuongTL_Tiendo> listtiendo = new ArrayList<ITieuchithuongTL_Tiendo>();
	    ResultSet rs = db.get(query);
	    if(rs != null)
	    {
	    	try
	    	{
		    	while(rs.next())
		    	{
		    		ITieuchithuongTL_Tiendo td = new TieuchithuongTL_Tiendo();
		    		td.setMuc(rs.getString("muc"));
		    		td.setMuc(rs.getString("tiendo"));
		    		td.setMuc(rs.getString("tungay"));
		    		td.setMuc(rs.getString("denngay"));
		    		td.setMuc(rs.getString("phaidat"));
		    		listtiendo.add(td);
		    	}
		    } 
			catch (Exception e) 
			{
				System.out.println("Exception: " + e.getMessage());
			}
	    }
	    
	    query = "select Vung.TEN as vungTen, KhuVuc.TEN as kvTen, NhaPhanPhoi.sitecode, NhaPhanPhoi.TEN as nppTen, scheme,  " 
	    		+"\n KHACHHANG.ten as khTen, ds.npp_fk, kh_apdung.KHACHHANG_FK, SOXUAT, DANGKY, tieuchi.muc, HTTT, kh_apdung.thuongtl_fk, ds.doanhso, "
				+"\n tieuchi.tumuc, tieuchi.denmuc, tieuchi.chietkhau, tieuchi.donvi"
				+"\n from "
				+"\n ("
				+"\n 	select a.scheme, c.KHACHHANG_FK, c.SOXUAT, c.DANGKY, c.MUC, a.HTTT, a.PK_SEQ as thuongtl_fk"
				+"\n 	from TIEUCHITHUONGTL a "
				+"\n 	inner join DANGKYKM_TICHLUY b on a.CTKM_FK = b.CTKM_FK"
				+"\n 	inner join DANGKYKM_TICHLUY_KHACHHANG c on b.PK_SEQ = c.DKKMTICHLUY_FK"
				+"\n 	where a.pK_seq = "+obj.getSchemeIds()+" and c.TTDUYET = 1"
				+"\n ) kh_apdung"
				+"\n inner join "
				+"\n ("
				+"\n 	-- ngày tính doanh số trong ctkmtl"
				+"\n 	select a.NPP_FK, a.KHACHHANG_FK, SUM(b.dongiagoc * b.SOLUONG) as doanhso "
				+"\n 	from DONHANG a inner join DONHANG_SANPHAM b on a.PK_SEQ = b.DONHANG_FK where b.SANPHAM_FK in "
				+"\n 	(select sanpham_fk from TIEUCHITHUONGTL_SANPHAM a inner join TIEUCHITHUONGTL b on a.thuongtl_fk = b.PK_SEQ "
				+"\n 	where b.pK_seq = "+obj.getSchemeIds()+") and a.trangthai = '1' ";
	    if(obj.getTungay().trim().length() > 0)
	    	query += "and a.NGAYNHAP >= '" + obj.getTungay() + "'";
	    if(obj.getDenngay().trim().length() > 0)
	    	query += "and a.NGAYNHAP <= '" + obj.getDenngay() + "'"; 
		query +="\n 	group by a.NPP_FK, KHACHHANG_FK"
				+"\n ) ds on kh_apdung.KHACHHANG_FK = ds.KHACHHANG_FK "
				+"\n full outer join"
				+"\n (  "
				+"\n 	select b.thuongtl_fk, b.tumuc, b.denmuc, b.chietkhau, b.donvi, b.muc from TIEUCHITHUONGTL a inner join TieuchithuongTL_TIEUCHI b on a.PK_SEQ = b.thuongtl_fk "
				+"\n 	where a.pK_seq = "+obj.getSchemeIds()+"  "
				+"\n ) tieuchi on kh_apdung.thuongtl_fk = tieuchi.thuongtl_fk "
				+"\n inner join NHAPHANPHOI on ds.npp_fk = NHAPHANPHOI.pk_seq   inner join KHUVUC on NHAPHANPHOI.khuvuc_fk = KHUVUC.pk_seq   " 
				+"\n inner join VUNG on KHUVUC.vung_fk = VUNG.pk_seq " 
				+"\n inner join KHACHHANG on ds.KHACHHANG_FK = KHACHHANG.PK_SEQ   "
				+"\n where tieuchi.muc = case "
				+"\n 		when kh_apdung.HTTT = 0 then kh_apdung.MUC"
				+"\n 		when kh_apdung.HTTT = 1 then (	select muc from TIEUCHITHUONGTL a inner join TieuchithuongTL_TIEUCHI b on a.PK_SEQ = b.thuongtl_fk "
				+"\n 										where ds.doanhso between tumuc and denmuc)"
				+"\n 		end";
	    System.out.println("lay bao cao "+query);
	    rs = db.get(query);
		
		int i = 2;
		if(rs!=null)
		{
			try 
			{
				for(int j = 0; j < 15; j++)
					cells.setColumnWidth(i, 15.0f);
				
				Cell cell = null;
				while(rs.next())
				{
					String vung = rs.getString("vungTen");
					String khuvuc = rs.getString("kvTen");
					
					String maNPP = rs.getString("sitecode");
					String tenNPP = rs.getString("nppten");
					
					String kh = rs.getString("khTen");
					
					String scheme = rs.getString("scheme");
					
					double doanhso = rs.getDouble("doanhso");
					System.out.println("__DOANH SO: " + doanhso);
					
					String muc = rs.getString("muc");
					double denmuc = rs.getDouble("denmuc");
					
					String thuong = find_thuong_khachhang(doanhso, listtiendo, muc, obj.getSchemeIds(), db, obj.getDenngay(), denmuc);
					
					double thuongKH = Double.parseDouble(thuong.split("_")[0]);
					
					if(thuongKH > 0)
					{	 
						cell = cells.getCell("EA" + Integer.toString(i)); 	cell.setValue(vung);
						cell = cells.getCell("EB" + Integer.toString(i)); 	cell.setValue(khuvuc);
						cell = cells.getCell("EC" + Integer.toString(i)); 	cell.setValue(maNPP);
						cell = cells.getCell("ED" + Integer.toString(i)); 	cell.setValue(tenNPP);	
						cell = cells.getCell("EE" + Integer.toString(i)); 	cell.setValue(kh);
						cell = cells.getCell("EF" + Integer.toString(i)); 	cell.setValue(doanhso);
						cell = cells.getCell("EG" + Integer.toString(i)); 	cell.setValue(scheme);
						cell = cells.getCell("EH" + Integer.toString(i)); 	cell.setValue(Integer.parseInt(muc)+1);
						cell = cells.getCell("EI" + Integer.toString(i)); 	cell.setValue(Double.parseDouble(thuong.split("_")[2]));
						cell = cells.getCell("EJ" + Integer.toString(i)); 	cell.setValue(thuongKH);
					}
					
					i++;
				}
				if(rs!=null)
					rs.close();
				if(db != null) 
					db.shutDown();
				if(i==2){
					throw new Exception("Khong co bao cao trong thoi gian nay...");
				}
			
			} 
			catch (Exception e) 
			{
				System.out.println("Exception: " + e.getMessage());
			}
		} 
		else 
		{
			if(db != null) 
				db.shutDown();
			return false;
		}
		
		if(db != null) 
			db.shutDown();
		return true;
	}
	public String find_thuong_khachhang(double doanhso, List<ITieuchithuongTL_Tiendo> listtiendo, String muc, String schemeId, dbutils db, String denngay, double denmuc) 
	{
		double thuong = 0;
		String donvithuong = "";
		String query = "select * from TIEUCHITHUONGTL a inner join TIEUCHITHUONGTL_TIEUCHI b on a.PK_SEQ = b.thuongtl_fk where a.pK_seq = "+schemeId;
		String[] tcmuc = new String[5];
		String[] chietkhau = new String[5];
		String[] donvi = new String[5];
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				int i = 0;
				while(rs.next())
				{
					tcmuc[i] = rs.getString("muc");
					chietkhau[i] = rs.getString("chietkhau");
					donvi[i] = rs.getString("donvi");
					i++;
				}
			}
			catch(Exception er)
			{
				er.printStackTrace();
			}
		}
		int sotd = 0, dat = 0;
		double tiendo = doanhso / denmuc * 100;
		for (int i = 0; i < listtiendo.size(); i++) {
			ITieuchithuongTL_Tiendo td = listtiendo.get(i);
			if(muc.trim().equals(td.getMuc()))
			{
				sotd++;
				if(denngay.compareTo(td.getDenngay()) >= 0)
				{
					if(tiendo >= Double.parseDouble(td.getPhaidat()))
						dat++;
				}
			}
		}
		if(sotd == dat)
		{
			for (int i = 0; i < tcmuc.length; i++)
			{
				if(muc.trim().equals(tcmuc[i]))
				{
					thuong = Double.parseDouble(chietkhau[i]);
					donvithuong = donvi[i];
				}
			}
		}
		
		System.out.println("Doanh so: " + doanhso + "___Tien thuong: " + thuong);
		System.out.println("___Don vi thuong trong ham: " + donvithuong);
		return Double.toString(thuong) + "_" + donvithuong + "_" + Double.toString(tiendo);
	}
	
	
}
