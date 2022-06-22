package geso.dms.center.servlets.reports;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.baocao.IBaocao;
import geso.dms.center.beans.baocao.imp.Baocao;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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

public class ErpXuatnhaptonTTSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpXuatnhaptonTTSvl() {
        super();
    }

    NumberFormat formatter = new DecimalFormat("#,###,###");
    NumberFormat formatter2 = new DecimalFormat("#,###,###.00");
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IBaocao obj;
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
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    obj = new Baocao();
	    obj.setUserId(userId);
	    obj.createRs();
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/ErpBCNhapXuatTon.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OutputStream out = response.getOutputStream(); 
		IBaocao obj;
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
	    String userId = request.getParameter("userId");

	    obj = new Baocao();
	    obj.setUserId(userId);
	    Utility uti=new Utility();
	    String tungay = uti.antiSQLInspection(request.getParameter("tungay"));
	    if(tungay == null)
	    	tungay = "";
	    obj.setTuNgay(tungay);
	    
	    String denngay =uti.antiSQLInspection( request.getParameter("denngay"));
	    if(denngay == null)
	    	denngay = "";
	    obj.setDenNgay(denngay);
	    
	    String loaisp = uti.antiSQLInspection(request.getParameter("loaisanpham"));
	    if(loaisp == null)
	    	loaisp = "";
	    obj.setLoaiSanPhamIds(loaisp);
	    
	    String khoId = uti.antiSQLInspection(request.getParameter("khoId"));
	    if(khoId == null)
	    	khoId = "";
	    obj.setKhoIds(khoId);
	    
	    String khoTen = uti.antiSQLInspection(request.getParameter("khoTen"));
	    if(khoTen == null)
	    	khoTen = "";
	    obj.setKhoTen(khoTen);
	    
	    String[] spIds = uti.antiSQLInspection_Array(request.getParameterValues("spIds"));
	    String spId = "";
	    if(spIds != null)
	    {
	    	for(int i = 0; i < spIds.length; i++)
	    		spId += spIds[i] + ",";
	    	if(spId.length() > 0)
	    		spId = spId.substring(0, spId.length() - 1);
	    	obj.setSanPhamIds(spId);
	    }
	    
	    String action = request.getParameter("action");
	    System.out.println("Action nhan duoc: " + action);
	    if(action.equals("search"))
	    {
	    	obj.createRs();
	    	session.setAttribute("obj", obj);
			
			String nextJSP = request.getContextPath() + "/pages/Center/ErpBCNhapXuatTon.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=NhapXuatTonTT.xlsm");
			
	    	boolean isTrue = false;
			try 
			{
				isTrue = CreatePivotTable(out, obj, "");	
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				isTrue = false;
			}
			
			if(!isTrue)
			{
				obj.createRs();
				session.setAttribute("obj", obj);
				obj.setMsg("Không thể tạo báo cáo");
				
				String nextJSP = request.getContextPath() + "/pages/Center/ErpBCNhapXuatTon.jsp";
				response.sendRedirect(nextJSP);
			}
	    }
	}
	
	

	private boolean CreatePivotTable(OutputStream out, IBaocao obj, String condition) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();		
		
		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\ErpBCNhapxuatton.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
	     CreateStaticHeader(workbook, obj.getTuNgay(), obj.getDenNgay(), obj.getUserTen());	     
	     boolean isTrue = CreateStaticData(workbook, obj, condition);
	     if(!isTrue){
	    	 return false;
	     }
	     workbook.save(out);
			
		fstream.close();
	     return true;
   }
	
	private void CreateStaticHeader(Workbook workbook, String dateFrom, String dateTo, String UserName) 
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
	    
	    String tieude = "BÁO CÁO NHẬP XUẤT TỒN";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	           
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Từ ngày: " + dateFrom);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("B2");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Đến ngày: " + dateTo);
	    
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + UserName);
	    

	    //tieu de, hang dau tien cua bang du lieu, cell la toa do cua exel
	    cell = cells.getCell("AA1"); 	cell.setValue("Kho");   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AB1"); 	cell.setValue("MaSanPham");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AC1"); 	cell.setValue("TenSanPham");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AD1"); 	cell.setValue("DonVi");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AE1"); 	cell.setValue("SoLo");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AF1"); 	cell.setValue("TonDau");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AG1"); 	cell.setValue("TongNhap");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AH1"); 	cell.setValue("TongXuat");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AI1"); 	cell.setValue("DieuChinh");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AJ1"); 	cell.setValue("TonCuoi");	   ReportAPI.setCellHeader(cell);
	    cell = cells.getCell("AK1"); 	cell.setValue("KhoERP");	   ReportAPI.setCellHeader(cell);
	}
	
	private boolean CreateStaticData(Workbook workbook, IBaocao obj, String condition) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
		
		String query =  "\n select isnull(kho_sp.khoErp_fk,'') khoErp_fk ,kho_sp.tenKHO, kho_sp.ma, kho_sp.ten, kho_sp.donvi, kho_sp.solo, isnull(nhapDK.soluong, 0) - isnull(xuatDK.soluong, 0) + isnull(dieuchinhDK.soluong, 0) as tonDAU, " +
						"\n		isnull(nhapTK.soluong, 0) as nhapTK, isnull(xuatTK.soluong, 0) as xuatTK, isnull(dieuchinh.soluong, 0) as dieuchinhTK, " +
						"\n		isnull(nhapDK.soluong, 0) - isnull(xuatDK.soluong, 0) + isnull(dieuchinhDK.soluong, 0) + isnull(nhapTK.soluong, 0) - isnull(xuatTK.soluong, 0) + isnull(dieuchinh.soluong, 0) as tonCUOI " +
						"\n from " +
						"\n ( " +
						"\n	select b.khoErp_fk,c.pk_seq as khoId, c.ma as maKHO, c.ten as tenKHO, b.pk_seq as spId, b.ma, b.ten, d.donvi as donvi, LODAUKY.SOLO as SOLO, 0 as soluong  " +
						"\n	from ERP_KHOTT_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
						"\n		inner join ERP_KHOTT c on a.khott_fk = c.pk_seq " +
						"\n		inner join DONVIDOLUONG d on b.dvdl_fk = d.pk_seq " +
						"\n		inner join " +
						"\n		( " +
						"\n			select distinct a.pk_seq khonhap_fk, b.sanpham_fk, b.solo " +
						"\n			 from ERP_KHOTT a inner join ERP_KHOTT_SP_CHITIET b on a.pk_seq = b.KHOTT_FK   " +
						"\n			where a.trangthai = '1'   " +
						"\n		) " +
						"\n		LODAUKY on c.pk_seq = LODAUKY.khonhap_fk and b.pk_seq = LODAUKY.sanpham_fk " +
						"\n ) " +
						"\n kho_sp left join " +
						"\n ( " +
						"\n	select khonhap_fk, sanpham_fk, solo, sum(soluong * quycach) as soluong " +
						"\n	from " +
						"\n	( " +
						"\n		select a.khonhap_fk, c.PK_SEQ as sanpham_fk, b.solo, b.SOLUONG, ( case when b.DVDL_FK=c.DVDL_FK then 1 "+
						"\n else b.soluong/(select soluong2/soluong1 "+
						"\n from QUYCACH where DVDL1_FK=c.DVDL_FK and DVDL2_FK=b.DVDL_FK and sanpham_fk=b.sanpham_fk) "+
						"\n end  ) as quycach " +
						"\n		from ERP_NHAPKHO a inner join ERP_NHAPKHO_SANPHAM b on a.pk_seq = b.nhapkho_fk  " +
						"\n			inner join SANPHAM c on b.sanpham_fk = c.pk_seq  " +
						"\n			inner join DONVIDOLUONG d on c.DVDL_FK = d.pk_seq  " +
						"\n		where a.trangthai = '1' and a.ngaynhap <= '" + obj.getTuNgay() + "' " +
						"\n	)  " +
						"\n	nhapko group by khonhap_fk, sanpham_fk, solo " +
						"\n ) " +
						"\n nhapDK on kho_sp.khoId = nhapDK.khonhap_fk and kho_sp.spId = nhapDK.sanpham_fk and kho_sp.solo = nhapDK.solo left join  " +
						"\n ( " +
						"\n	select a.kho_fk, b.sanpham_fk, b.solo, sum(b.soluong) as soluong " +
						"\n	from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.ycxk_fk  " +
						"\n	where a.trangthai = '2' and a.ngayyeucau <= '" + obj.getTuNgay() + "' " +
						"\n	group by a.kho_fk, b.sanpham_fk, b.solo " +
						
						"\n union all "+
						
						"\n	select a.KhoXuat_FK as kho_fk, b.sanpham_fk, b.solo, sum(b.soluong) as soluong "+
						"\n			from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.chuyenkho_fk  "+ 
						"\n		where a.trangthai = '1' and  a.NgayChuyen <= '" + obj.getTuNgay() + "' "+
						"\n	group by a.KhoXuat_FK, b.sanpham_fk, b.solo "+
						
						"\n ) " +
						"\n xuatDK on kho_sp.khoId = xuatDK.kho_fk and kho_sp.spId = xuatDK.sanpham_fk and kho_sp.solo = xuatDK.solo left join " +
						"\n ( " +
						"\n	select a.khott_fk as kho_fk, b.sanpham_fk, b.solo, sum(b.tonthucte - b.tonkho) as soluong " +
						"\n	from ERP_DIEUCHINHTONKHO a inner join ERP_DIEUCHINHTONKHO_SANPHAM b on a.PK_SEQ = b.dieuchinh_fk  " +
						"\n	where a.trangthai = '1' and a.ngaydieuchinh <= '" + obj.getTuNgay() + "' " +
						"\n	group by a.khott_fk, b.sanpham_fk, b.solo " +
						"\n ) " +
						"\n dieuchinhDK on kho_sp.khoId = dieuchinhDK.kho_fk and kho_sp.spId = dieuchinhDK.sanpham_fk and kho_sp.solo = dieuchinhDK.solo left join " +
						"\n ( " +
						"\n	select khonhap_fk, sanpham_fk, solo, sum(soluong * quycach) as soluong " +
						"\n	from " +
						"\n	( " +
						"\n		select a.khonhap_fk, c.PK_SEQ as sanpham_fk, b.solo, b.SOLUONG, ( case when b.DVDL_FK=c.DVDL_FK then 1 "+
						 "\n  else b.soluong/(select soluong2/soluong1  "+
						 "\n from QUYCACH where DVDL1_FK=c.DVDL_FK and DVDL2_FK=b.DVDL_FK and sanpham_fk=b.sanpham_fk )"+
						"\n end  ) as quycach " +
						"\n		from ERP_NHAPKHO a inner join ERP_NHAPKHO_SANPHAM b on a.pk_seq = b.nhapkho_fk  " +
						"\n			inner join SANPHAM c on b.sanpham_fk = c.pk_seq  " +
						"\n			inner join DONVIDOLUONG d on c.DVDL_FK = d.pk_seq  " +
						"\n		where a.trangthai = '1' and a.ngaynhap > '" + obj.getTuNgay() + "' and a.ngaynhap <= '" + obj.getDenNgay() + "' " +
						"\n	)  " +
						"\n	nhapko group by khonhap_fk, sanpham_fk, solo " +
						"\n ) " +
						"\n nhapTK on kho_sp.khoId = nhapTK.khonhap_fk and kho_sp.spId = nhapTK.sanpham_fk and kho_sp.solo = nhapTK.solo left join  " +
						"\n ( " +
						"\n	select a.kho_fk, b.sanpham_fk, b.solo, sum(b.soluong) as soluong " +
						"\n	from ERP_YCXUATKHO a inner join ERP_YCXUATKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.ycxk_fk  " +
						"\n	where a.trangthai = '2' and a.ngayyeucau > '" + obj.getTuNgay() + "' and a.ngayyeucau <= '" + obj.getDenNgay() + "' " +
						"\n	group by a.kho_fk, b.sanpham_fk, b.solo " +
						
						"\n union all"+
						"\n	select a.KhoXuat_FK as kho_fk, b.sanpham_fk, b.solo, sum(b.soluong) as soluong "+
						"\n			from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on a.PK_SEQ = b.chuyenkho_fk  "+ 
						"\n		where a.trangthai = '1' and  a.NgayChuyen > '" + obj.getTuNgay() + "' and a.NgayChuyen <= '" + obj.getDenNgay() + "' "+
						"\n	group by a.KhoXuat_FK, b.sanpham_fk, b.solo "+
						
						"\n ) " +
						"\n xuatTK on kho_sp.khoId = xuatTK.kho_fk and kho_sp.spId = xuatTK.sanpham_fk and kho_sp.solo = xuatTK.solo left join " +
						"\n ( " +
						"\n	select a.khott_fk as kho_fk, b.sanpham_fk, b.solo, sum(b.tonthucte - b.tonkho) as soluong " +
						"\n	from ERP_DIEUCHINHTONKHO a inner join ERP_DIEUCHINHTONKHO_SANPHAM b on a.PK_SEQ = b.dieuchinh_fk  " +
						"\n	where a.trangthai = '1' and a.ngaydieuchinh > '" + obj.getTuNgay() + "' and a.ngaydieuchinh <= '" + obj.getDenNgay() + "' " +
						"\n	group by a.khott_fk, b.sanpham_fk, b.solo " +
						"\n ) " +
						"\n dieuchinh on kho_sp.khoId = dieuchinh.kho_fk and kho_sp.spId = dieuchinh.sanpham_fk and kho_sp.solo = dieuchinh.solo " +
						"\n where 1=1  ";
		
		if(obj.getKhoIds().length() > 0)
			query += " and kho_sp.khoId = '" + obj.getKhoIds() + "' ";
		
		if(obj.getSanPhamIds().trim().length() > 0)
			query += " and kho_sp.spId in ( " + obj.getSanPhamIds() + " ) ";
		
		
		Utility util =new Utility(); 
		query+=  " AND  kho_sp.khoId IN "+util.quyen_khoTT(obj.getUserId(),util.KICHHOAT)+" ";
		
		
		query += " order by kho_sp.tenKHO asc";
	
		System.out.println("1.Bao cao hang nhap kho: " + query);
		ResultSet rs = db.get(query);
		
		int i = 2;
		if(rs!=null)
		{
			try 
			{
				Cell cell = null;
				while(rs.next())// lap den cuoi bang du lieu
				{
					String kho = rs.getString("tenKHO");
					String maSp = rs.getString("Ma");
					String tenSp = rs.getString("Ten");		
					String donvi = rs.getString("donvi");
					String solo = rs.getString("SoLo");
					Double tondau = rs.getDouble("tonDAU");
					Double nhap = rs.getDouble("nhapTK");
					Double xuat = rs.getDouble("xuatTK");
					Double dieuchinh = rs.getDouble("dieuchinhTK");
					Double toncuoi = rs.getDouble("tonCUOI");

					cell = cells.getCell("AA" + Integer.toString(i)); 	cell.setValue(kho);
					cell = cells.getCell("AB" + Integer.toString(i)); 	cell.setValue(maSp);
					cell = cells.getCell("AC" + Integer.toString(i)); 	cell.setValue(tenSp);
					cell = cells.getCell("AD" + Integer.toString(i)); 	cell.setValue(donvi);
					cell = cells.getCell("AE" + Integer.toString(i)); 	cell.setValue(solo);
					cell = cells.getCell("AF" + Integer.toString(i)); 	cell.setValue(tondau);
					cell = cells.getCell("AG" + Integer.toString(i)); 	cell.setValue(nhap);
					cell = cells.getCell("AH" + Integer.toString(i)); 	cell.setValue(xuat);
					cell = cells.getCell("AI" + Integer.toString(i)); 	cell.setValue(dieuchinh);
					cell = cells.getCell("AJ" + Integer.toString(i)); 	cell.setValue(toncuoi);
					cell = cells.getCell("AK" + Integer.toString(i)); 	cell.setValue(rs.getString("khoErp_fk"));
					i++;
				}
				if(rs!=null)
					rs.close();
				if(db != null) 
					db.shutDown();
				if(i==2){
					throw new Exception("Khong co bao cao trong thoi gian nay...");
				}
			
			} catch (Exception e) 
			{
				System.out.println("115.Exception: " + e.getMessage());
				throw new Exception(e.getMessage());
			}
		} else {
			if(db != null) db.shutDown();
			return false;
		}
		
		if(db != null) db.shutDown();
		return true;
		
	}
	
	
}
