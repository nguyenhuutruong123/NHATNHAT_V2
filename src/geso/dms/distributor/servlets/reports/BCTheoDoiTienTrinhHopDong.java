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
import geso.dms.center.servlets.report.ReportAPI;

public class BCTheoDoiTienTrinhHopDong extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BCTheoDoiTienTrinhHopDong() {
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
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCTheoDoiTienTrinhHopDong.jsp";
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
	    System.out.println("khId: "+khId);
	    
		session.setAttribute("obj", obj);
	    
		if( action.equals("taobaocao"))
		{
	    	try 
	    	{	
	    		OutputStream out = response.getOutputStream(); 
	    		
				response.setContentType("application/xlsm");
	    		response.setHeader("Content-Disposition", "attachment; filename=BCTheoDoiTienTrinhHopDong.xlsm");
	
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
			String nextJSP = request.getContextPath() + "/pages/Distributor/BCTheoDoiTienTrinhHopDong.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	
	private void TongHopChiPhiSX(OutputStream out, IStockintransit obj) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCTheoDoiTienTrinhHopDong.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		BCTongHopChiPhiSX(workbook, obj);

		workbook.save(out);
		fstream.close();
		
    }

	private void BCTongHopChiPhiSX(Workbook workbook, IStockintransit obj) 
	{
		try{ 		
			
			FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCTheoDoiTienTrinhHopDong.xlsm");
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");

			Cells cells = worksheet.getCells();
			Cell cell;
			Style style;
		    Font font = new Font();
		    font.setName("Times New Roman");
		    font.setColor(Color.RED);//mau chu
		    font.setSize(16);// size chu
		   	font.setBold(true);
			
			cell = cells.getCell("A1");
		    style = cell.getStyle();
		    style.setFont(font); 
		    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 
		    
		    cell = cells.getCell("A3");
		    cell.setValue("Từ ngày: " + obj.gettungay() + " - Đến ngày: " + obj.getdenngay() );
		    
		    cell = cells.getCell("A4");
		    cell.setValue( "Ngày tạo " + this.getDateTime());
			
			String query = "\n select distinct a.NgayDonHang " +
					"\n from ERP_DONDATHANGNPP a " +
					"\n where a.HOPDONG_FK is not null " +
					"\n and a.TRANGTHAI = 4 and NPP_FK = " + obj.getnppId();
			if(obj.gettungay().length() > 0)
				query += "\n and a.NgayDonHang >= '"+obj.gettungay()+"'";
			if(obj.getdenngay().length() > 0)
				query += "\n and a.NgayDonHang <= '"+obj.getdenngay()+"'";
			
			query += "\n union " +
					 "\n select distinct a.ngaytra NgayDonHang " +
					 "\n from ERP_hangtralainpp a where a.HOPDONG_FK is not null " +
					 "\n and a.TRANGTHAI = '1' and NPP_FK = " + obj.getnppId();
			if(obj.gettungay().length() > 0)
				query += " and a.ngayhoadon >= '"+obj.gettungay()+"'";
			if(obj.getdenngay().length() > 0)
				query += " and a.ngayhoadon <= '"+obj.getdenngay()+"'";
			
			dbutils db = new dbutils();
			String sql = 	"\n SELECT * FROM " +
							"\n ( " +
							"\n 	SELECT distinct CAST(NgayDonHang as varchar(7)) as THANG, " +
							"\n		REPLACE(CAST(NgayDonHang as varchar(7)), '-', '') AS ID " +
							"\n 	FROM (" + query+ "  ) d" +
							"\n	) d ORDER BY THANG";
			System.out.println(sql);
			ResultSet r = db.getScrol(sql);
			String subSelect = "";
			String subPivot = "";
			String subIn = "";
			int col = 0;
			while(r.next())
			col++;
			String[] select_sl = new String[col];
			String[] select_ds = new String[col];
			String[] col_row= new String[col];
			r.beforeFirst();
			int i = 0;
			while(r.next()){
				//subSelect += "sl.["+r.getString("THANG")+"] as SL"+r.getString("ID")+", ds.["+r.getString("THANG")+"] as DS"+r.getString("ID")+", \n";
				
				subSelect += "(isnull(sl.["+r.getString("THANG")+"],0) - isnull(sltralai.["+r.getString("THANG")+"],0)) as SL"+r.getString("ID")+", " +
							 "(isnull(ds.["+r.getString("THANG")+"],0) - isnull(dstralai.["+r.getString("THANG")+"],0)) as DS"+r.getString("ID")+", \n";
				
				subPivot += "PIVOTTable.["+r.getString("THANG")+"], \n";
				subIn += "["+r.getString("THANG")+"], ";
				select_sl[i] = "SL" + r.getString("ID");
				select_ds[i] = "DS" + r.getString("ID");
				col_row[i] = r.getString("THANG");
				i++;
			}
					
			if(subSelect.length() > 0){
				
				subSelect = subSelect.substring(0, subSelect.length() - 3);
				subPivot = subPivot.substring(0, subPivot.length() - 3);
				subIn = subIn.substring(0, subIn.length() - 2);
				
				col = 9 + 2; //thêm 2 cột
				cell = cells.getCell(4, col);
				cell.setValue("Đã xuất");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
				
				for(int j=1; j<= select_sl.length * 2 - 1; j++){
					cell = cells.getCell(4, col + j);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
				}
				cells.merge(4, col, 4, col + select_sl.length * 2 - 1);
				
				for(int j=0; j< select_sl.length; j++){
					cell = cells.getCell(5, col);
					cell.setValue(ConvertThangNam(col_row[j]));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
					
					cells.merge(5, col, 5, col + 1);
					
					cell = cells.getCell(6, col++);
					cell.setValue("Số lượng");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
					
					cell = cells.getCell(6, col++);
					cell.setValue("Doanh số");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
					
				}
				
				cell = cells.getCell(4, col);
				cell.setValue("Còn lại");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
				for(int j=0; j<=1; j++)
					for(int k=4; k<=5; k++){
						cell = cells.getCell(k, col + j);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
					}
				
				cells.merge(4, col, 5, col + 1);
				
				cell = cells.getCell(6, col++);
				cell.setValue("Số lượng");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
				
				cell = cells.getCell(6, col++);
				cell.setValue("Doanh số");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0, TextAlignmentType.CENTER);
				
				
				
				query = "\n select sptungay,spdenngay,hd.LoaiDonHang, hd.PK_SEQ, CONVERT(VARCHAR(10), CAST(hd.TuNgay AS DATETIME), 105)as TuNgay, CONVERT(VARCHAR(10), CAST(hd.DenNgay AS DATETIME), 105)as DenNgay, hd.MaHopDong, t.TEN as TINHTHANH, k.maFAST, isnull(k.TEN,'') as KHACHHANG, " +      
						"\n 	s.MA as MASP, s.ten as TENSP, hdsp.soluong, hdsp.dongia * (1 + hdsp.thueVAT / 100.0) as dongia, hdsp.soluong *  hdsp.dongia * (1 + hdsp.thueVAT / 100.0) as thanhtien, dv.DONVI, " +      
						"\n 	d.* " +      
						"\n from ERP_HOPDONGNPP hd inner join " +
						"( " +
						"\n 	select ISNULL(hdchinh.hopdong_fk, hdpl.HOPDONG_FK) as HOPDONG_FK,  " +
						"\n 		ISNULL(hdchinh.SANPHAM_FK, hdpl.SANPHAM_FK) as SANPHAM_FK, " +
						"\n 		ISNULL(hdchinh.dvdl_fk, hdpl.dvdl_fk) as dvdl_fk, " +
						"\n 		ISNULL(hdchinh.soluong,0) + ISNULL(hdpl.soluong, 0) as soluong, " +
						"\n 		ISNULL(hdchinh.dongia, hdpl.dongia) as dongia, " +
						"\n 		ISNULL(hdchinh.thueVAT, hdpl.thueVAT) as thueVAT, " +
						"\n 		isnull(sptungay,'') sptungay, " +
						"\n 		isnull(spdenngay,'') spdenngay " +
						"\n 	from " +
						"\n 	( " +
						"\n 		select _hdsp.tungay sptungay,_hdsp.denngay spdenngay,_hdsp.hopdong_fk, _hdsp.sanpham_fk, _hdsp.dvdl_fk, _hdsp.soluong, _hdsp.dongia, _hdsp.thueVAT " +
						"\n 		from ERP_HopDongNPP_SANPHAM _hdsp inner join ERP_HOPDONGNPP _hd on _hdsp.hopdong_fk = _hd.PK_SEQ " +
						"\n 		where _hd.HOPDONG_FK is null and _hd.LoaiDonHang != 1 " +
						"\n 	) hdchinh " +
						"\n 	full outer join " +
						"\n 	( " +
						"\n 		select _hd.HOPDONG_FK, _hdsp.sanpham_fk, _hdsp.dvdl_fk, sum(_hdsp.soluong) as soluong, avg(_hdsp.dongia) as dongia, avg(_hdsp.thueVAT) as thueVAT " +
						"\n 		from ERP_HopDongNPP_SANPHAM _hdsp inner join ERP_HOPDONGNPP _hd on _hdsp.hopdong_fk = _hd.PK_SEQ " +
						"\n 		where _hd.HOPDONG_FK is not null and _hd.LoaiDonHang = 1 " +
						"\n 		group by _hd.HOPDONG_FK, _hdsp.sanpham_fk, _hdsp.dvdl_fk " +
						"\n 	) hdpl on hdchinh.hopdong_fk = hdpl.HOPDONG_FK and hdchinh.sanpham_fk = hdpl.sanpham_fk " +
						"\n ) " +
						"\n hdsp on hd.PK_SEQ = hdsp.hopdong_fk " +      
						"\n inner join SANPHAM s on s.PK_SEQ = hdsp.sanpham_fk " +   
						"\n left join DONVIDOLUONG dv on dv.PK_SEQ = hdsp.dvdl_fk " +
						"\n left join " +
						"\n	( " +      
						"\n 	SELECT ISNULL(sl.HOPDONG_FK, sltralai.hopdong_fk) hopdong_fk, " +
						"\n		ISNULL(ISNULL(sl.sanpham_fk, sltralai.sanpham_fk), ISNULL(ds.sanpham_fk, dstralai.sanpham_fk)) as sanpham_fk,  " + subSelect +      
						"\n 	FROM " +      
						"\n 	( " +      
						"\n 		SELECT PIVOTTable.HOPDONG_FK, PIVOTTable.sanpham_fk,  " + subPivot +
						"\n 		FROM " +
						"\n			( " +      
						"\n 			SELECT CAST(d.NgayDonHang as varchar(7)) as THANG, s.sanpham_fk, sum(s.soluong) as soluong, " +      
						"\n 			d.HOPDONG_FK " +      
						"\n 			FROM ERP_DONDATHANGNPP d inner join ERP_DONDATHANGNPP_SANPHAM s on d.PK_SEQ = s.dondathang_fk " +      
						"\n 			WHERE d.HOPDONG_FK is not null and d.trangthai in ('2','4') " +   
						"\n 			group by CAST(d.NgayDonHang as varchar(7)), s.sanpham_fk, d.HOPDONG_FK " +      
						"\n 		) d PIVOT ( SUM(d.soluong) for d.THANG in ("+subIn+") ) AS PIVOTTable " +      
						"\n 	) sl " +
												
						"\n 	full outer join " + 
						"\n  	(  " + 
						"\n  		SELECT PIVOTTable.HOPDONG_FK, PIVOTTable.sanpham_fk, " + subPivot + 
						"\n  		FROM  " + 
						"\n  		( " + 
						"\n  			select CAST(t.ngaytra as varchar(7)) as THANG, ct.sanpham_fk, t.hopdong_fk, sum(ct.soluong) soluong " +
						"\n 			from Erp_HangTraLaiNpp t  " + 
						"\n  			inner join erp_hangtralainpp_sanpham ct on ct.hangtralai_fk = t.pk_seq " + 
						"\n  			where t.HOPDONG_FK is not null and t.trangthai = '1' " + 
						"\n 			group by ct.sanpham_fk, t.hopdong_fk, CAST(t.ngaytra as varchar(7)) " + 
						"\n  		) d PIVOT(  " + 
						"\n  		SUM(d.soluong) for d.THANG in ("+subIn+") " + 
						"\n  		) AS PIVOTTable  " + 
						"\n   	) sltralai on sl.sanpham_fk = sltralai.sanpham_fk and sl.HOPDONG_FK = sltralai.HOPDONG_FK "+ 
						
						"\n  	full outer join " + 
						"\n  	(  " + 
						"\n  		SELECT PIVOTTable.HOPDONG_FK, PIVOTTable.sanpham_fk, " + subPivot +  
						"\n  		FROM  " + 
						"\n  		( " + 
						"\n  			select CAST(t.ngaytra as varchar(7)) as THANG, ct.sanpham_fk, t.hopdong_fk, sum(ct.soluong*ct.dongia) as doanhso " +
						"\n 			from Erp_HangTraLaiNpp t  " + 
						"\n  			inner join erp_hangtralainpp_sanpham ct on ct.hangtralai_fk = t.pk_seq " + 
						"\n  			where t.HOPDONG_FK is not null and t.trangthai = '1' " + 
						"\n  			group by ct.sanpham_fk, t.hopdong_fk, CAST(t.ngaytra as varchar(7)) " + 
						"\n  		) d PIVOT(  " + 
						"\n  		sum(d.doanhso) for d.THANG in ("+subIn+") " + 
						"\n  		) AS PIVOTTable  " + 
						"\n   	) dstralai on sl.sanpham_fk = dstralai.sanpham_fk and sl.HOPDONG_FK = dstralai.HOPDONG_FK "+
						
						"\n		full outer join " +
						"\n		( " +      
						"\n 		SELECT PIVOTTable.HOPDONG_FK, PIVOTTable.sanpham_fk,  "+ subPivot +  
						"\n 		FROM " +
						"\n			( " +      
						"\n 			SELECT CAST(d.NgayDonHang as varchar(7)) as THANG, s.sanpham_fk, sum(s.soluong*s.dongia) as doanhso, " +      
						"\n 			d.HOPDONG_FK " +      
						"\n 			FROM ERP_DONDATHANGNPP d inner join ERP_DONDATHANGNPP_SANPHAM s on d.PK_SEQ = s.dondathang_fk " +      
						"\n 			WHERE d.HOPDONG_FK is not null and d.trangthai in ('2','4') " +     
						"\n 			group by CAST(d.NgayDonHang as varchar(7)), s.sanpham_fk, d.HOPDONG_FK " +      
						"\n 		) d PIVOT ( SUM(d.doanhso) for d.THANG in ("+subIn+") ) AS PIVOTTable " +      
						"\n 	) ds on sl.sanpham_fk = ds.sanpham_fk and sl.HOPDONG_FK = ds.HOPDONG_FK " +      
						"\n ) d on d.sanpham_fk = s.PK_SEQ and hd.PK_SEQ = d.HOPDONG_FK " +      
						"\n left join KHACHHANG k on k.PK_SEQ = hd.KHACHHANG_FK " +      
						"\n left join TINHTHANH t on t.PK_SEQ = k.TINHTHANH_FK " +
						"\n left join nhaphanphoi npp on npp.pk_seq = hd.npp_fk " +
						"\n left join khuvuc kv on kv.pk_seq = npp.khuvuc_fk " +
						"\n where 1 =1";
				if(obj.gettungay().length() > 0)
					query += "\n and hd.Tungay >= '"+obj.gettungay()+"'";
				if(obj.getdenngay().length() > 0)
					query += "\n and hd.Tungay <= '"+obj.getdenngay()+"'";
				if (obj.getkhId() != null && obj.getkhId().length() > 0) {
					query += "\n and k.pk_seq = " +obj.getkhId();
				}
				if (obj.getkhuvucId() != null && obj.getkhuvucId().length() > 0) {
					query += "\n and kv.pk_seq = " +obj.getkhuvucId();
				}
				if (obj.getnppId() != null && obj.getnppId().length() > 0) {
					query += "\n and npp.pk_seq = " +obj.getnppId();
				}


				query += "\n order by t.TEN, hd.PK_SEQ, k.maFAST";
				System.out.println("pivot: " + query);
				ResultSet rs = db.get(query);
				int countRow = 7;
				col = 0;
				
				while(rs.next())
				{
					String mafast = rs.getString("MAFAST");
					col = 0;
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("TuNgay")  + " - " + rs.getString("DenNgay"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.CENTER);
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("MaHopDong"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.CENTER);
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("TINHTHANH"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.CENTER);

					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("KHACHHANG"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.LEFT);
					
					col = 4;
					
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("TENSP"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.LEFT);
				
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("sptungay"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.LEFT);

					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("spdenngay"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.LEFT);


					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getString("DONVI"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0, TextAlignmentType.CENTER);
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getFloat("DONGIA"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getFloat("soluong"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
					
					cell = cells.getCell(countRow, col++);
					cell.setValue(rs.getFloat("thanhtien"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
					
					Double tongsl = 0.0; Double tongds = 0.0;
					
					for(int j=0; j< select_sl.length; j++){
						Double temp = rs.getDouble(select_sl[j]);
						cell = cells.getCell(countRow, col++);
						cell.setValue(temp == null || temp == 0.0?"":temp);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
						
						cell = cells.getCell(countRow, col++);
						temp = rs.getDouble(select_ds[j]);
						cell.setValue(temp == null || temp == 0.0?"":temp);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
					
						tongsl += rs.getDouble(select_sl[j]);
						tongds+= rs.getDouble(select_ds[j]);
					}
					cell = cells.getCell(countRow, col++);
					Double temp = rs.getDouble("soluong") - Math.abs(tongsl);
					cell.setValue(temp == null || temp == 0.0?"":temp);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
					
					temp = rs.getDouble("thanhtien") - Math.abs(tongds);
					cell = cells.getCell(countRow, col++);
					cell.setValue(temp == null || temp == 0.0?"":temp);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41, TextAlignmentType.RIGHT);
					
					countRow++;
				}
			}
			
			
			if(db!=null){
				db.shutDown();
			}

			//fstream.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String ConvertThangNam(String namthang){
		String[] ngay = namthang.split("-");
		return ngay[1] +"-"+ ngay[0];
	}

}
