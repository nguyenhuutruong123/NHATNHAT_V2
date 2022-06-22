//Lo trinh ban hang
package geso.dms.center.servlets.reports;

import geso.dms.center.beans.lotrinh.*;
import geso.dms.center.beans.lotrinh.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.CodingErrorAction;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class LoTrinhBanHangReport extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	Utility util = new Utility();
	NumberFormat formatter = new DecimalFormat("#,###,###.###");
	public LoTrinhBanHangReport()
	{
		super();
	}	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();
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
		String userTen = (String) session.getAttribute("userTen");
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		session.setAttribute("userId", userId);
		session.setAttribute("tungay", "");
		session.setAttribute("denngay", "");
		session.setAttribute("loi", "");
		session.setAttribute("userTen", userTen);

		ILoTrinh obj = new LoTrinh();
		obj.setUserId(userId);
		obj.setStatus("1");
		obj.init();

		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/LoTrinhBanHangReport.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		ILoTrinh obj = new LoTrinh();
		
		String nnId = (String)session.getAttribute("nnId");

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));

		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
		if (kenhId == null)
			kenhId = "";
		obj.setKenhId(kenhId);

		if (nppId == null)
			nppId = "";
		obj.setnppId(nppId);

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";
		obj.setddkdId(ddkdId);

		String tuyenId = util.antiSQLInspection(request.getParameter("tuyenId"));
		if (tuyenId == null)
			tuyenId = "";
		obj.settuyenId(tuyenId);
		
		 String userId = util.antiSQLInspection(request.getParameter("userId")==null?"":request.getParameter("userId"));    
		 obj.setUserId(userId);
		
		String tungay = request.getParameter("tungay");
    	if(tungay == null) tungay = ""; else tungay = tungay.trim();
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if(denngay == null || denngay.trim().length() <= 0) 
    		denngay = getDateTime();
    	else 
    		denngay = denngay.trim();
    	obj.setDenngay(denngay);

    	String khuvucId = util.antiSQLInspection(request.getParameter("khuvucId"));
    	
		if (khuvucId == null)
			khuvucId = "";
		obj.setkhuvucId(khuvucId);
		
		
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
    	
		if (vungId == null)
			vungId = "";
		obj.setVungId(vungId);
		
		
		String tinhthanhId = util.antiSQLInspection(request.getParameter("tinhthanhId"));
    	
		if (tinhthanhId == null)
			tinhthanhId = "";
		System.out.println("Tinh thanh "+tinhthanhId);
		obj.setTinhthanhId(tinhthanhId);
		
		session.setAttribute("loi", "");
		
		obj.init();
		String action = request.getParameter("action");

		if (action.equals("exportmcp"))
		{
			System.out.println("XuatMcp__");
			if(tungay.trim().length() <= 0 || denngay.trim().length() <= 0 )
			{
				if(tungay.trim().length() <= 0)
					session.setAttribute("loi", "Bạn phải chọn ngày bắt đầu xem báo cáo"); 
				else
					session.setAttribute("loi", "Bạn phải chọn ngày kết thúc xem báo cáo");
			
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Center/LoTrinhBanHangReport.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				XuatFileExcelSR(response, obj, nnId);
			}
		}  
		else
		{
			if(action.equals("chitiet"))
			{
				System.out.println("XuatDetail__");
				if(tungay.trim().length() <= 0 || denngay.trim().length() <= 0 )
				{
					if(tungay.trim().length() <= 0)
						session.setAttribute("loi", "Bạn phải chọn ngày bắt đầu xem báo cáo"); 
					else
						session.setAttribute("loi", "Bạn phải chọn ngày kết thúc xem báo cáo");
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/LoTrinhBanHangReport.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
/*					System.out.println("VO Xuat FILE Detail__");
					XuatFileExcelChiTiet(response, obj, nnId);*/
					try
					{
						response.setContentType("application/xlsm");
						System.out.println(" my contentType 2 :" +  response.getContentType());
						response.setHeader("Content-Disposition", "attachment; filename=BCLoTrinhBanHangChiTiet"+util.setTieuDe(obj)+".xlsm");
						OutputStream out = response.getOutputStream();
						String query = setQuery(obj);
						CreatePivotTable(out, obj,query);
						obj.DBclose();
						return;
					}
					catch(Exception e)
					{
						response.setContentType("text/html; charset=UTF-8");
						e.printStackTrace();
						session.setAttribute("loi", "Không có báo cáo theo điều kiện("+e.getMessage()+")");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Center/LoTrinhBanHangReport.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				String status = util.antiSQLInspection(request.getParameter("status"));
				obj.setStatus(status);
			//	obj.createNPP();
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Center/LoTrinhBanHangReport.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}

	private void XuatFileExcelChiTiet(HttpServletResponse response, ILoTrinh lotrinh, String nnId) throws IOException
	{
		OutputStream out1 = null;
		
		try
		{
			NumberFormat formater = new DecimalFormat("#,###,###.##");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=LoTrinhBanHangChiTiet.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());
			
			WritableSheet sheet = w.createSheet("LoTrinhBanHang", 0);
			
			
			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);
			
			sheet.addCell(new Label(0, 0, "BÁO CÁO LỘ TRÌNH BÁN HÀNG CHI TIẾT" , new WritableCellFormat(cellTitle))); 
			sheet.addCell(new Label(0, 1, "TỪ NGÀY: " + lotrinh.getTungay())); //TỪ NGÀY
			sheet.addCell(new Label(0, 2, "ĐẾN NGÀY: " + lotrinh.getDenngay() )); //ĐẾN NGÀY
			
			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			cellFont.setColour(Colour.BLACK);

			WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

			cellFormat.setBackground(jxl.format.Colour.GREY_50_PERCENT);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			int cot = 0;
			sheet.addCell(new Label(cot++, 4, "STT", cellFormat));
			sheet.addCell(new Label(cot++, 4, "ĐỊA BÀN", cellFormat));
			sheet.addCell(new Label(cot++, 4, "CHI NHÁNH / ĐỐI TÁC", cellFormat));
			sheet.addCell(new Label(cot++, 4, "MÃ NVBH", cellFormat));
			sheet.addCell(new Label(cot++, 4, "NHÂN VIÊN BÁN HÀNG", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "MÃ KH", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "LOẠI KH", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "KHÁCH HÀNG", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "ĐỊA CHỈ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TUYẾN BH", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "NGÀY", cellFormat));
			sheet.addCell(new Label(cot++, 4, "PHẢI VT", cellFormat));
			sheet.addCell(new Label(cot++, 4, "ĐÃ VT", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "SỐ ĐH", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "ĐH CÓ VT", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "ĐH KHÔNG VT", cellFormat)); 
		
			 
			sheet.addCell(new Label(cot++, 4, "QUÃNG ĐƯỜNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TG ĐẾN" , cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "TG ĐI", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "TG DI CHUYỂN", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "TỔNG TG", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "DOANH THU", cellFormat)); 
			sheet.addCell(new Label(cot++, 4, "SKU", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TG MUA HÀNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "Đ/Đ XUẤT HÀNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "GHI CHÚ VIẾNG THĂM", cellFormat));
			
			for(int i = 0; i < cot ; i ++)
			{
				sheet.setColumnView(i, 20);
			}
		
			
			WritableCellFormat cellFormat2 = new WritableCellFormat();
			cellFormat2.setBackground(jxl.format.Colour.YELLOW);
			
			WritableCellFormat cellFormat3 = new WritableCellFormat();
			cellFormat3.setBackground(jxl.format.Colour.GRAY_25);
			
			WritableCellFormat cellFormat4 = new WritableCellFormat(new jxl.write.NumberFormat("#,#"));
			cellFormat4.setBackground(jxl.format.Colour.GRAY_25);
			
			WritableCellFormat cellFormat5 = new WritableCellFormat(new jxl.write.NumberFormat("#,##0.00"));
			cellFormat5.setBackground(jxl.format.Colour.GRAY_25);
			
			WritableCellFormat cellFormat6 = new WritableCellFormat(new jxl.write.NumberFormat("#,#"));
			
			
			geso.dms.center.util.Utility util  =new geso.dms.center.util.Utility();
			String tuyen =   "\n  IF OBJECT_ID('tempdb.dbo.#ddkd_sokh') IS NOT NULL DROP TABLE #ddkd_sokh  " + 
							 "\n  select distinct ddkd_sokh.*  " + 
							 "\n 	INTO #ddkd_sokh  " + 
							 "\n  from ddkd_sokh   " +
							 "\n  inner join daidienkinhdoanh ddkd on ddkd_sokh.DDKD_FK = ddkd.pk_seq " +  
							 "\n  WHERE ddkd_sokh.NGAY >='" + lotrinh.getTungay() + "' AND  ddkd_sokh.NGAY <='" + lotrinh.getDenngay() + "'  " ;
			
			if(lotrinh.getddkdId().trim().length() > 0)
				tuyen += "\n and ddkd_sokh.DDKD_FK = '" + lotrinh.getddkdId() + "' ";
			else 
				if(lotrinh.getddkdId().trim().length() > 0|| lotrinh.getTinhthanhId().trim().length() > 0 )
				{
					if(lotrinh.getnppId().trim().length() > 0)
						tuyen += "\n and exists ( select  1 from daidienkinhdoanh_npp x where npp_fk = '" + lotrinh.getnppId() + "' and x.ddkd_fk = ddkd_sokh.DDKD_FK )  ";
					if(lotrinh.getTinhthanhId().trim().length() > 0)
						tuyen += "\n and ddkd.diaban_fk in ("+lotrinh.getTinhthanhId()+") ";
				}
				else
					if(lotrinh.getkhuvucId().trim().length() > 0)
						tuyen += "\n and ddkd.diaban_fk in (select pk_seq from diaban where khuvuc_fk = "+lotrinh.getkhuvucId()+") ";					
					else if(lotrinh.getVungId().length() > 0)
						tuyen += "\n and ddkd.diaban_fk in (select pk_seq from diaban " +
								 "\n 						where khuvuc_fk in ( select khuvuc_fk from khuvuc where vung_fk = "+lotrinh.getVungId()+") ) ";
						
			
			tuyen += "\n and ddkd_sokh.DDKD_FK in "+util.Quyen_Ddkd(lotrinh.getUserId())+" " ;
			
			
			 String query = tuyen +	 
			 				 "\n  select distinct CODI.MANHANVIEN,CODI.ddkd_fk, CODI.ddkdTen,  CODI.NGAY, vtham.SOVT,isnull( CODI.PHAIVT,0)PHAIVT, ISNULL(CODI.NGAYID,0) as NGAYID,     " + 
							 "\n  			isnull(doanhso.doanhso,0) as DOANHSO,    " + 
							 "\n  			isnull(doanhso.SODH,0) as SODH,     " + 
							 "\n  			isnull(doanhso.DHCOVT,0)as DHCOVT,   	   " + 
							 "\n  		 	isnull(doanhso.SODH,0) - isnull(doanhso.DHCOVT,0) DHKOVT     " + 
							 "\n  		 	,vtham.TGDenBatDau,TGCuoiCung  " +
							 "\n			, [dbo].[TongKhoangCachTDV](CODI.ddkd_fk,CODI.NGAY)QuangDuong	" + 
							 "\n  		 	  " + 
							 "\n   from  " + 
							 "\n   (       " + 
							 "\n   		select distinct a.ddkd_fk, b.ten as ddkdTen, b.maFAST  as MANHANVIEN,isnull(a.sokh,0) AS PHAIVT, a.NGAYID   " + 
							 "\n   		, a.NGAY    " + 
							 "\n   		from #ddkd_sokh a inner join daidienkinhdoanh b on a.ddkd_fk = b.PK_SEQ     " + 
							 "\n   )     " + 
							 "\n   CODI    " + 
							 "\n   left join  " + 
							 "\n   (  " + 
							 "\n 		select  dh.DDKD_FK,NGAYNHAP, SUM(soluong*giamua) as doanhso ,COUNT(distinct dh.PK_SEQ) SODH  " + 
							 "\n 		,	COUNT ( distinct vt.khachhang_fk)  DHCOVT  " + 
							 "\n 		from DONHANG dh   " + 
							 "\n 		inner join DONHANG_SANPHAM dh_sp	 on dh.PK_SEQ = dh_sp.DONHANG_FK   " + 
							 "\n 		inner join #ddkd_sokh kh on kh.ddkd_fk = dh.ddkd_fk and dh.ngaynhap = kh.ngay  and charindex(',' + cast(dh.khachhang_fk as varchar(10)) + ',', kh.AllKhachHang_Fks) >= 1    " + 
							 "\n 		left join  ddkd_khachhang vt on vt.khachhang_fk =dh.KHACHHANG_FK and dh.ddkd_fk = vt.ddkd_fk  and dh.NGAYNHAP  = CONVERT(varchar(10), vt.thoidiem, 121)  " + 
							 "\n 		where  dh.TRANGTHAI != 2 and dh.IsPDA = 1   " + 
							 "\n   " + 
							 "\n 		group by dh.DDKD_FK,NGAYNHAP  " + 
							 "\n     " + 
							 "\n   )DOANHSO on CODI.ddkd_fk = DOANHSO.DDKD_FK and CODI.ngay = DoanhSo.NGAYNHAP  " + 
							 "\n   left join     " + 
							 "\n   (    " + 
							 "\n  		SELECT DDKH.ddkd_fk, COUNT(DISTINCT DDKH.KHACHHANG_FK) AS SOVT, CONVERT(VARCHAR(10), DDKH.THOIDIEM , 120) as NGAY    " + 
							 "\n  		,min(DDKH.thoidiem) TGDenBatDau, case when max(DDKH.thoidiemdi) > max(DDKH.thoidiem) then max(DDKH.thoidiemdi) else max(DDKH.thoidiem) end TGCuoiCung  " + 
							 "\n  		FROM ddkd_khachhang DDKH   " + 
							 "\n  		inner join daidienkinhdoanh ddkd on ddkd.pk_seq =  DDKH.ddkd_fk  " + 
							 "\n  		where DDKH.DDKD_FK in (select ddkd_fk from #ddkd_sokh)    " + 
							 "\n  		group by DDKH.ddkd_fk, CONVERT(VARCHAR(10), DDKH.THOIDIEM , 120)    " + 
							 "\n   ) vtham on vtham.ddkd_fk = CODI.ddkd_fk and vtham.NGAY = CODI.NGAY     " + 
							 "\n   where PHAIVT is not null " + 
							 "\n   order by  codi.DDKD_FK asc, codi.NGAY asc    " ;

			System.out.println("LAY BC: " + query);
			dbutils db = new dbutils();
			ResultSet rs = db.get(query);
			
			int count = 5;
			int stt = 1;
			if(rs != null)
			{
				String ngayOLD = "";
				String ddkdTenOLD = "";
				int firstRow = 5;
				String dayS = "";
				String dayE = "";
				Label label;
				String ngayCu = "";
				while(rs.next())
				{
		
					
					String ddkdId = rs.getString("ddkd_fk");
					String maddkd = rs.getString("MANHANVIEN");
					String ddkdTen = rs.getString("ddkdTen");
					String ngay = rs.getString("NGAY");
					String ngayId = rs.getString("NGAYID");
					String pvt = rs.getString("PHAIVT");
					String dvt = rs.getString("SOVT");
					String sodh = rs.getString("SODH");
					String sodhcovt = rs.getString("DHCOVT");
					String sodhkovt = rs.getString("DHKOVT");
					double ds = rs.getDouble("DOANHSO");
		
					
					double quangduong = rs.getDouble("QuangDuong");
					
					
					//String soKM = formater.format(quangduong);
					String olai ="";// rs.getString("OLai");
					String den ="";//= rs.getString("ThoiDiemDen") == null ? "" : rs.getString("ThoiDiemDen") ;
					String roidi="";// = rs.getString("ThoiDiemDi") == null ? "" : rs.getString("ThoiDiemDi") ;
					
					
					if(!ddkdTenOLD.equals(ddkdTen) || !ngayOLD.equals(ngay))
					{
						firstRow = count;
						
						label = new Label(3, count, maddkd, cellFormat3);
						sheet.addCell(label);
						label = new Label(4, count, ddkdTen, cellFormat3);
						sheet.addCell(label);
						label = new Label(5, count, "", cellFormat3);
						sheet.addCell(label);
						label = new Label(6, count, "", cellFormat3);
						sheet.addCell(label);
						label = new Label(7, count, "", cellFormat3);
						sheet.addCell(label);							
						label = new Label(8, count, "", cellFormat3);
						sheet.addCell(label);
						
						label = new Label(9, count, ngayId, cellFormat3);
						sheet.addCell(label);
						label = new Label(10, count, ngay, cellFormat3);
						sheet.addCell(label);
						//label = new Label(11, count, pvt, cellFormat3);
						sheet.addCell(new Number(11, count, Integer.parseInt(pvt != null?pvt:"0"), cellFormat3));
						//sheet.addCell(label);
						//label = new Label(12, count, dvt, cellFormat3);
						//sheet.addCell(label);
						sheet.addCell(new Number(12, count, Integer.parseInt(dvt!=null?dvt:"0"), cellFormat3));
						//label = new Label(13, count, sodh, cellFormat3);
						//sheet.addCell(label);
						sheet.addCell(new Number(13, count, Integer.parseInt(sodh!=null?sodh:"0"), cellFormat3));
						//label = new Label(14, count, sodhcovt, cellFormat3);
						//sheet.addCell(label);
						sheet.addCell(new Number(14, count, Integer.parseInt(sodhcovt!=null?sodhcovt:"0"), cellFormat3));
						//label = new Label(15, count, sodhkovt, cellFormat3);
						//sheet.addCell(label);
						sheet.addCell(new Number(15, count, Integer.parseInt(sodhkovt!=null?sodhkovt:"0"), cellFormat3));
						
						//label = new Label(16, count, formatter.format(ds), cellFormat3);
						//sheet.addCell(label);
						sheet.addCell(new Number(21, count, ds, cellFormat4));
						
						
						//sheet.addCell(label);
						sheet.addCell(new Number(16, count, quangduong, cellFormat5));
						
						
//						sheet.addCell(new Label(21, count, "", cellFormat5));
//						sheet.addCell(new Label(22, count, "", cellFormat5));
						ngayCu = ngayOLD;
						ddkdTenOLD = ddkdTen;
						ngayOLD = ngay;
						count ++;
						
						
					}
					
					
					query =  "\n 	 select DDKD.*  " + 
							 "\n 		, isnull(DATEDIFF(MINUTE, ThoiDiemDen, ThoiDiemDi), -1) as OLai  " + 
							 "\n 		,DonHang.NPP as DDXUATHANG   " + 
							 "\n 		  " + 
							 "\n 		,DonHang.NgayGio_Tao as NgayGioDonHang  " + 
							 "\n 		,ISNULL(DonHang.SODH,0) as SODH   " + 
							 "\n 		,isnull(DonHang.DHCOVT,0) as DHCOVT  " + 
							 "\n 		, ISNULL(DonHang.SODH,0)  - isnull(DonHang.DHCOVT,0) DHKOVT  " + 
							 "\n   " + 
							 "\n 		,isnull(DonHang.DoanhSo,0) DS, isnull(DonHang.SKU,0)SKU  " + 
							 "\n 		,DDKD.NgayId  " + 
							 "\n 		,DDKD.TanSo	   " + 
							 "\n 	 from     " + 
							 "\n 	(     " + 
							 "\n 		select b.npp_fk,npp.TEN as nppTen,l.loai as loaikh, b.PK_SEQ AS KHACHHANG_FK, b.mafast, b.TEN as khTen, b.DIACHI, min(a.thoidiem) as ThoiDiemDen,     " + 
							 "\n 			 min(a.thoidiemdi) as ThoiDiemDi,CONVERT(varchar(10), a.thoidiem, 121) as NGAY, ISNULL(a.GHICHU,'') AS GHICHU   ,db.TEN DiaBan  " + 
							 "\n 			 ,case when	a.isDungTuyen = 0  then N'Ngoài Tuyến'  else 	N'Đúng tuyến' end as VTNT ,	a.TanSo,a.NgayId  " + 
							 "\n 		from ddkd_khachhang a inner join KHACHHANG b on a.khachhang_fk = b.PK_SEQ    " + 
							 "\n 		left join DIABAN db on b.DIABAN_FK = db.PK_SEQ    " + 
							 "\n 		 left join LOAICUAHANG l on l.pk_seq = b.LCH_FK      " + 
							 "\n 		 inner join NHAPHANPHOI npp on npp.PK_SEQ = b.npp_fk          " + 
							 "\n 		where a.ddkd_fk = "+ddkdId+"  and CONVERT(varchar(10), a.thoidiem, 121) = '" + ngay + "'      " + 
							 "\n 		group by b.npp_fk,npp.TEN ,l.loai, b.mafast, a.khachhang_fk, b.TEN, b.DIACHI  " + 
							 "\n 				, CONVERT(varchar(10), a.thoidiem, 121), b.PK_SEQ, a.GHICHU  ,db.TEN,	a.isDungTuyen,	a.TanSo,a.NgayId  " + 
							 "\n 	)      " + 
							 "\n 	DDKD left join  " + 
							 "\n 	(  " + 
							 "\n 		select dh.KHACHHANG_FK,COUNT(distinct dh.PK_SEQ)SODH,MIN(NgayGio_Tao)NgayGio_Tao   " + 
							 "\n 			,	COUNT ( distinct vt.khachhang_fk)  DHCOVT  " + 
							 "\n 			,SUM(SOLUONG *GIAMUA)DoanhSo,COUNT( distinct dh_sp.sanpham_fk) as SKU  " + 
							 "\n 			,(select top 1 ten from Nhaphanphoi where pk_seq in  ( select npp_fk from donhang x where x.NgayGio_Tao  = MIN(dh.NgayGio_Tao) and khachhang_fk = dh.KHACHHANG_FK))NPP  " + 
							 "\n 		from DONHANG dh  " + 
							 "\n 		inner join DONHANG_SANPHAM dh_sp on dh.PK_SEQ = dh_sp.DONHANG_FK  " + 
							 "\n 		left join  ddkd_khachhang vt on vt.khachhang_fk = dh.KHACHHANG_FK and dh.ddkd_fk = vt.ddkd_fk and dh.NGAYNHAP  = CONVERT(varchar(10), vt.thoidiem, 121)  " + 
							 "\n 		left join TUYENBANHANG tbh on tbh.DDKD_FK = vt.ddkd_fk and tbh.NGAYID = DATEPART(dw,vt.thoidiem)  " + 
							 "\n 		left join KHACHHANG_TUYENBH khtbh on vt.khachhang_fk = khtbh.KHACHHANG_FK and tbh.PK_SEQ = khtbh.TBH_FK  " + 
							 "\n 		where dh.DDKD_FK = "+ddkdId+" AND NGAYNHAP =  '" + ngay + "'   AND dh.TRANGTHAI != 2 AND IsPDA = 1  " + 
							 "\n 		group by dh.KHACHHANG_FK  " + 
							 "\n 	)  DonHang on DDKD.KHACHHANG_FK = DonHang.KHACHHANG_FK  " + 
							 "\n order by DDKD.NPP_FK,ThoiDiemDen asc ";
					
					//System.out.println("--LO TRINH CHI TIET: \n" + query);
					ResultSet rsKh = db.get(query);
					int tmp = 0;
					dayS = "";
					dayE = "";
					String mafastOld = "", mafastNew = "";
					String thoiGianDiOld = "", thoiGianDiNew = "";
					int olaiCu = -1, tongThoiGianOLai = 0;
					if(rsKh != null)
					{
						while(rsKh.next())
						{	

							mafastNew = rsKh.getString("mafast");
						
							double sku = rsKh.getDouble("SKU");
							
							olai = rsKh.getString("OLai");
							den = rsKh.getString("ThoiDiemDen") == null ? "" : rsKh.getString("ThoiDiemDen") ;
							roidi = rsKh.getString("ThoiDiemDi") == null ? "" : rsKh.getString("ThoiDiemDi") ;
							
							if(roidi.length() > 0)
								dayE = roidi;
							if(dayS.length() <= 0)
							{
								dayS = den;
								label = new Label(18, firstRow, den, cellFormat3);
								sheet.addCell(label);
							}
							tmp ++;
							label = new Label(0, count, String.valueOf(stt));
							sheet.addCell(label);
							
							label = new Label(1, count, rsKh.getString("diaban"));
							sheet.addCell(label);
							
							label = new Label(2, count, rsKh.getString("nppTen"));
							sheet.addCell(label);
							sheet.addCell(new Label(12, count, rsKh.getString("VTNT")));
							//label = new Label(3, count, maddkd);
							//sheet.addCell(label);
							
							label = new Label(4, count, ddkdTen);
							sheet.addCell(label);
							
							label = new Label(5, count, mafastNew);
							sheet.addCell(label);
							
							
							
							label = new Label(6, count, rsKh.getString("loaikh"));
							sheet.addCell(label);
							label = new Label(7, count, rsKh.getString("khTen"));
							sheet.addCell(label);							
							label = new Label(8, count, rsKh.getString("DIACHI"));
							sheet.addCell(label);
							
							sheet.addCell(new Number(14, count, Integer.parseInt(rsKh.getString("DHCOVT") != null?rsKh.getString("DHCOVT"):"0")));
							//label = new Label(15, count, rsKh.getString("DHKOVT"));
							//sheet.addCell(label);
							sheet.addCell(new Number(15, count, Integer.parseInt(rsKh.getString("DHKOVT") != null?rsKh.getString("DHKOVT"):"0")));
							//label = new Label(16, count, formatter.format(rsKh.getFloat("DS")));
							//sheet.addCell(label);
							sheet.addCell(new Number(21, count, rsKh.getFloat("DS"), cellFormat6));
							
							label = new Label(17, count, den);
							sheet.addCell(label);
							
							label = new Label(18, count, roidi);
							sheet.addCell(label);
							
							sheet.addCell(new Number(20, count, Integer.parseInt(olai != null?olai:"0")));
							sheet.addCell(new Number(22, count, sku));
							
							sheet.addCell(new Label(23, count,rsKh.getString("NgayGioDonHang") ) );
							sheet.addCell(new Label(24, count,rsKh.getString("DDXUATHANG") ) );
							sheet.addCell(new Label(25, count,rsKh.getString("GHICHU") ) );

							int thoiGianOLai =0;
							int olaiInt = Integer.parseInt(olai); 
							thoiGianDiNew = roidi;
							thoiGianDiOld = thoiGianDiOld.equals("") ? roidi : thoiGianDiOld;
							mafastOld = mafastOld.equals("") ? mafastNew : mafastOld;
							olaiCu = olaiCu == -1 ? olaiInt : olaiCu;
						//	System.out.println("ngayCu" + ngayCu + " ngay" + ngay);
						//	System.out.println("Khoi tao : olai" + olai + " olaiCu" + olaiCu);

							if(olaiInt > 0)
								tongThoiGianOLai+= olaiInt;
						//	System.out.println("tongThoiGianOLai: "+tongThoiGianOLai+": thoiGianOLai: "+thoiGianOLai);
						//	tongThoiGianOLai += thoiGianOLai; 
							thoiGianDiOld = thoiGianDiNew;
							ngayCu = ngay;
							mafastOld = mafastNew;
							count ++;
							stt++;
						}
						label = new Label(18, firstRow, dayE, cellFormat3);
						sheet.addCell(label);
						label = new Label(17, firstRow, dayS, cellFormat3);
						sheet.addCell(label);
						
						//System.out.println("DayE "+dayE);
						if(dayS.length() > 0 && dayE.length() > 0){
							SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd HH:mm");
							Date t1 = ft.parse(dayS);
							Date t2 = ft.parse(dayE);
							long diff = Math.abs(t2.getTime() - t1.getTime());
							long diffMinutes = diff / (60 * 1000) % 60;
							long diffHours = diff / (60 * 60 * 1000) % 24;
							//sheet.addCell(new Number(22, firstRow, diffHours, cellFormat3));
							label = new Label(20, firstRow, diffHours + ":" + diffMinutes, cellFormat3);
							sheet.addCell(label);
							//System.out.println("TongTG: "+tongThoiGianOLai);
							long phutOLai = tongThoiGianOLai%60;
							long gioOLai = tongThoiGianOLai/60;
							
							long phutDiChuyen = diffMinutes - phutOLai;
							long gioDiChuyen = diffHours - gioOLai;
							
							if (phutDiChuyen < 0) {
								phutDiChuyen = 60 + phutDiChuyen;
								gioDiChuyen--;
							}

							label = new Label(19, firstRow, gioDiChuyen + ":" + phutDiChuyen, cellFormat3);
							sheet.addCell(label);
							
						}
						else
						{
							label = new Label(19, firstRow, "" , cellFormat3);
							sheet.addCell(label);
							label = new Label(20, firstRow, "" , cellFormat3);
							sheet.addCell(label);
						}
						rsKh.close();
					}
					if(tmp == 0)
						count --;
				}
				
				db.update("IF OBJECT_ID('tempdb.dbo.#ddkd_sokh') IS NOT NULL DROP TABLE #ddkd_sokh ");
				
				rs.close();
			}
			
			w.write();
			w.close();
		} 
		catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} 
		finally
		{
			if (out1 != null)
				out1.close();
		}

	}

	private void XuatFileExcelSR(HttpServletResponse response, ILoTrinh lotrinh, String nnId) throws IOException
	{
		OutputStream out1 = null;
		try
		{
			String NppId = lotrinh.getnppId();
			String DdkdId = lotrinh.getddkdId();
			String tuyenId = lotrinh.gettuyenId();
			String tungay = lotrinh.getTungay();
			String denngay = lotrinh.getDenngay();
			String tinhthanhId = lotrinh.getTinhthanhId();
			String vungId = lotrinh.getVungId();
			String khuvucId = lotrinh.getkhuvucId();
			
			String[] tungays = tungay.split("-");
			String[] denngays = denngay.split("-");
			
			String ngayId = lotrinh.gettuyenId();
			if(ngayId == null) ngayId = ""; else ngayId = ngayId.trim();
			int ngayIdi = 0;
			try {
				ngayIdi = Integer.parseInt(ngayId);
				if(ngayIdi < 1 || ngayIdi > 7) {
					ngayIdi = 0;
				}
			} catch(Exception e) { }
			
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=LoTrinhBanHang.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			dbutils db = new dbutils();
			String sql = "select isnull(STUFF " +
					" (  " +
					"	(	select ' , ' + p.ten " +
					"		from NHAPHANPHOI p " +
					"		where p.PK_SEQ in (select NPP_FK from DAIDIENKINHDOANH_NPP where DDKD_FK = a.PK_SEQ ";
			if(NppId.trim().length() > 0)
			{
				sql+=" and NPP_FK =" + NppId +"  ";
			}	
			sql += ") " +
					"		ORDER BY ' , '  +p.ten " +
					"		FOR XML PATH('') " +
					"	 ), 1, 2, '' " +
					" ), '') as nppten, kv.ten as tenkv, a.pk_seq as ddkdid, a.ten  as ddkdten " +
				" from daidienkinhdoanh a inner join DIABAN db on a.DIABAN_FK = db.pk_seq  " +
				"inner join KHUVUC kv  on kv.pk_Seq = db.KHUVUC_FK " +
				" where 1=1 ";
			
			
			if (DdkdId.trim().length() > 0)
			{
				sql += " and a.pk_seq=" + DdkdId;
			}
			
			if(khuvucId.trim().length() > 0)
			{
				sql+=" and kv.pk_seq=" + khuvucId +"  ";
			}
			
			if(vungId.trim().length() > 0)
			{
				sql+=" and kv.pk_seq in ( select pk_seq from KHUVUC where vung_fk ='"+vungId+"')";
			}
			if(tinhthanhId.trim().length() > 0) //diaban
			{
				sql += " and a.DIABAN_FK = " + tinhthanhId;
			}
			/*if(lotrinh.getkhuvucId().trim().length() > 0)
			{
				sql += " and kv.pk_seq = '" + lotrinh.getkhuvucId() + "' ";
			}*/
			System.out.println("XuatFileExcelSR thong tin chinh' = "  + sql);
			ResultSet rs = db.get(sql);
			int k = 0;
			while (rs.next())
			{
				WritableSheet sheet = w.createSheet(rs.getString("ddkdid"), k);

				sheet.addCell(new Label(0, 1, "NPP : ")); //NPP
				sheet.addCell(new Label(1, 1, "" + rs.getString("nppten")));

				sheet.addCell(new Label(0, 2, "KHU VỰC : ")); //KHU VỰC 
				sheet.addCell(new Label(1, 2, "" + rs.getString("tenkv")));

				sheet.addCell(new Label(0, 3, "NVBH : ")); //NVBH 
				sheet.addCell(new Label(1, 3, "" + rs.getString("ddkdten")));
				
				sheet.addCell(new Label(2, 3, "Đơn vị tiền tệ: VND")); //Đơn vị tiền tệ				

				WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
				cellFont.setColour(Colour.BLACK);

				WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

				cellFormat.setBackground(jxl.format.Colour.GRAY_25);
				cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

				// cellFormat.setFont()
				sheet.addCell(new Label(0, 4, "KÊNH", cellFormat)); //"KÊNH"
				sheet.addCell(new Label(1, 4, "KHU VỰC", cellFormat)); //"KHU VỰC"
				sheet.addCell(new Label(2, 4, "CHI NHÁNH / ĐỐI TÁC", cellFormat)); //"NHÀ PHÂN PHỐI"
				sheet.addCell(new Label(3, 4, "NVBH", cellFormat)); //"NVBH"
				sheet.addCell(new Label(4, 4, "TUYẾN BÁN HÀNG", cellFormat)); //"TUYẾN BÁN HÀNG"
				sheet.addCell(new Label(5, 4, "NGÀY", cellFormat)); //"NGÀY"
				sheet.addCell(new Label(6, 4, "STT", cellFormat)); //"STT"
				sheet.addCell(new Label(7, 4, "KHÁCH HÀNG", cellFormat)); //"KHÁCH HÀNG"
				sheet.addCell(new Label(8, 4, "ĐỊA CHỈ", cellFormat)); //"ĐỊA CHỈ"
				sheet.addCell(new Label(9, 4, "DOANH SỐ", cellFormat)); //"DOANH SỐ"
				sheet.addCell(new Label(10, 4, "THỜI ĐIỂM ĐẾN", cellFormat)); //"THỜI GIAN"
				sheet.addCell(new Label(11, 4, "ĐỘ LỆCH", cellFormat)); //"ĐỘ LỆCH"
				sheet.addCell(new Label(12, 4, "THỜI ĐIỂM ĐI", cellFormat)); //"THỜI ĐIỂM ĐI"
				sheet.addCell(new Label(13, 4, "GHI CHÚ", cellFormat));
				sheet.setColumnView(0, 16);
				sheet.setColumnView(1, 16);
				sheet.setColumnView(2, 20);
				sheet.setColumnView(3, 20);
				sheet.setColumnView(4, 20);
				sheet.setColumnView(5, 20);
				sheet.setColumnView(6, 20);
				sheet.setColumnView(7, 20);
				sheet.setColumnView(8, 20);
				sheet.setColumnView(9, 20);
				sheet.setColumnView(10, 20);
				sheet.setColumnView(11, 20);
				
				sheet.setColumnView(12, 20);
				
				sql = 
					" select   vt.vitri ,hch.hang as hangcuahang,lch.loai as loaicuahang,kh.phuongXa as Phuong, viengtham.ngayvt as NGAYTHANG, " +
					" 		kh.chucuahieu,kh.tinhthanh_fk,kh.quanhuyen_fk,   " +
					" 		kh.ten as tencuahieu, kh.smartid, kh.diachi, kh.dienthoai, viengtham.NGAYID, " +
					" 		kbh.DIENGIAI AS KBH, npp.TEN AS NPP, KV.TEN AS KHUVUC, ddkd.TEN, isnull(kh.LAT, '0') as khLat, isnull(kh.long, '0') as khLong,  " +
					" 		ISNULL ( ( select sum(soluong*giamua + (SOLUONG * GIAMUA * NH.THUEXUAT / 100)) 	from donhang_sanpham dhsp inner join donhang dh on dh.pk_seq=dhsp.donhang_fk INNER JOIN SANPHAM s ON s.PK_SEQ = dhsp.SANPHAM_FK  INNER JOIN NGANHHANG NH ON s.NGANHHANG_FK = NH.PK_SEQ where dh.khachhang_fk=kh.pk_seq and dh.trangthai = 1 and dh.ngaynhap = viengtham.ngayvt    	),0 )  as DoanhSo  , " +
					" 		viengtham.*  " +
					" from DAIDIENKINHDOANH ddkd " +
					" left join " +
					" ( " +
					" 	 select c.ThoidiemDi , a.pk_seq as ddkdId, a.TEN as ddkdTen, b.DIENGIAI, b.NGAYID, thoidiem, datepart(dw, c.thoidiem) as ngayidvt, 0 as type, c.khachhang_fk as khId, b.NPP_FK as nppId, b.PK_SEQ as tbhId, Replace(convert(nvarchar(10), thoidiem , 102) , '.', '-' ) as  ngayvt, kh_tbh.SOTT as sott, kh_tbh.TANSO as tanso, isnull(c.lat, '0') as lat, isnull(c.long, '0') as long, isnull(c.dolech, '') as dolech, ISNULL(c.GHICHU,'') AS GHICHU " +
					" 	 from DAIDIENKINHDOANH a inner join TUYENBANHANG b on a.PK_SEQ = b.DDKD_FK " +
					" 	 left join ddkd_khachhang c on a.PK_SEQ = c.ddkd_fk and b.NGAYID = datepart(dw, c.thoidiem) " +
					" 	 inner join KHACHHANG_TUYENBH kh_tbh on b.PK_SEQ = kh_tbh.TBH_FK and kh_tbh.KHACHHANG_FK = c.khachhang_fk  " +
					" 	 left join  " +
					" 	 ( " +
					" 		select   MAX(thoidiem) as thoidiemmax,ddkd_fk,khachhang_fk, Replace(convert(nvarchar(10), thoidiem , 102) , '.', '-' ) as ngay from ddkd_khachhang  " +
					"  		group by ddkd_fk,khachhang_fk, Replace(convert(nvarchar(10), thoidiem , 102) , '.', '-' )  " +
					" 	 )  " +
					" 	 maxtd on maxtd.thoidiemmax = c.thoidiem and maxtd.ddkd_fk = c.ddkd_fk and maxtd.khachhang_fk = c.khachhang_fk  " +
					"  " +
					" union all " +
					" 	 select  c.ThoidiemDi,a.pk_seq as ddkdId, a.TEN as ddkdTen, b.DIENGIAI, b.NGAYID, thoidiem, datepart(dw, c.thoidiem), 1, c.khachhang_fk, b.NPP_FK, b.PK_SEQ, Replace(convert(nvarchar(10), thoidiem , 102) , '.', '-' ), kh_tbh.SOTT as sott, kh_tbh.TANSO as tanso, isnull(c.lat, '0') as lat, isnull(c.long, '0') as long, isnull(c.dolech, '') as dolech, ISNULL(c.GHICHU,'') AS GHICHU " +
					" 	 from DAIDIENKINHDOANH a inner join TUYENBANHANG b on a.PK_SEQ = b.DDKD_FK " +
					" 	 left join ddkd_khachhang c on a.PK_SEQ = c.ddkd_fk and b.NGAYID != datepart(dw, c.thoidiem) " +
					" 	 inner join KHACHHANG_TUYENBH kh_tbh on b.PK_SEQ = kh_tbh.TBH_FK and kh_tbh.KHACHHANG_FK = c.khachhang_fk  " +
					" 	 left join  " +
					" 	 ( " +
					" 		select   MAX(thoidiem) as thoidiemmax,ddkd_fk,khachhang_fk, Replace(convert(nvarchar(10), thoidiem , 102) , '.', '-' ) as ngay from ddkd_khachhang  " +
					"  		group by ddkd_fk,khachhang_fk, Replace(convert(nvarchar(10), thoidiem , 102) , '.', '-' )  " +
					" 	 )  " +
					" 	 maxtd on maxtd.thoidiemmax = c.thoidiem and maxtd.ddkd_fk = c.ddkd_fk and maxtd.khachhang_fk = c.khachhang_fk  " +
					" ) " +
					" viengtham on viengtham.ddkdId = ddkd.PK_SEQ " +
					"  " +
					" left join khachhang kh on kh.pk_seq = viengtham.khId " +
					" left join hangcuahang hch on hch.pk_seq = kh.hch_fk  " +
					" left join loaicuahang lch on lch.pk_seq = kh.lch_fk  " +
					" left join vitricuahang vt on vt.pk_seq = kh.vtch_fk " +
					" left join NHAPHANPHOI npp on npp.PK_SEQ = viengtham.nppId " +
					" left join DDKD_GSBH on DDKD_GSBH.DDKD_FK = ddkd.PK_SEQ  " +
					" left join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = DDKD_GSBH.GSBH_FK  " +
					" left join KENHBANHANG kbh on kbh.PK_SEQ = gsbh.KBH_FK  " +
					" inner join DIABAN db on db.PK_SEQ = ddkd.DIABAN_FK " +
					" left join KHUVUC KV on db.KHUVUC_FK = KV.PK_SEQ  " +
					" where viengtham.ddkdId = '"+rs.getString("ddkdid")+"' and '" + tungay + "' <= ngayvt and ngayvt <= '" + denngay + "' " + (ngayIdi != 0 ? " and viengtham.ngayid = '" + lotrinh.gettuyenId().trim() + "' " : " " );
					
					
					if(NppId.trim().length() > 0)
					{
						sql+=" and npp.pk_seq=" + NppId +"  ";
					}else
					if(khuvucId.trim().length() > 0)
					{
						sql+=" and kv.pk_seq=" + khuvucId +"  ";
					}
					else
						if(vungId.trim().length() > 0)
						{
							sql+=" and kv.pk_seq in ( select pk_seq from KHUVUC where vung_fk ='"+vungId+"')";
						}
				sql +=	" order by ddkdTen, thoidiem asc, type asc, viengtham.NGAYID asc, sott ";
				
				
				
				System.out.println("[LoTrinhBanHangReport.XuatExcel] sql = \n" + sql);

				ResultSet rs1 = db.get(sql);
				Label label;

				int j = 5;
				// set style to cell data
				WritableCellFormat cellFormat2 = new WritableCellFormat();

				cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
				
				WritableCellFormat cellFormat3 = new WritableCellFormat();
				cellFormat3.setBackground(jxl.format.Colour.YELLOW);
				cellFormat3.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat3.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat3.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
				
				WritableCellFormat cformat;
				
				String thoidiemTruoc = "";

				if (rs1 != null)
					while (rs1.next())
					{
						String thoidiemTam = rs1.getString("thoidiem");
						if(thoidiemTam == null) thoidiemTam = ""; else thoidiemTam = thoidiemTam.trim();
						
						
						if(!thoidiemTam.trim().equals(thoidiemTruoc)) {
							thoidiemTruoc = thoidiemTam;
						
							//Xử lý độ lệch
							String dolech = "";
							String dolechDb = rs1.getString("dolech"); //Lấy độ lệch trong db
							
							if (dolechDb == null) {
								double kc = -200;						
								double khlat = rs1.getDouble("khLat");
								double khlon = rs1.getDouble("khLong");
								double lat = rs1.getDouble("lat");
								double lon = rs1.getDouble("long");
								
								if(khlat != 0 && khlon != 0 && lat != 0 && lon != 0) {
									kc = getKhoangCachHaversine(khlat, khlon, lat, lon);
								}
								
								if(kc >= 0) {
									dolech = kc + "";
								}
							} else {
								dolech = dolechDb;
							}
							
							double _dolech = -1;
							try {
								_dolech = Double.parseDouble(dolech);
								if(_dolech > 30) {
									_dolech = 30;
								}
							} catch(Exception e) {
								
							}
							
							String type = rs1.getString("type") == null ? "0" : rs1.getString("type").trim();
							
							cformat = type.equals("1") ? cellFormat3 : cellFormat2;
							
							//Vẽ giao diện
							label = new Label(0, j, rs1.getString("KBH"), cformat);
							sheet.addCell(label);
	
							label = new Label(1, j, rs1.getString("KHUVUC"), cformat);
							sheet.addCell(label);
	
							label = new Label(2, j, rs1.getString("NPP"), cformat);
							sheet.addCell(label);
	
							label = new Label(3, j, rs1.getString("TEN"), cformat);
							sheet.addCell(label);
	
							label = new Label(4, j, rs1.getString("NGAYID"), cformat);
							sheet.addCell(label);
							
							label = new Label(5, j, rs1.getString("NGAYTHANG"), cformat);
							sheet.addCell(label);
	
							label = new Label(6, j, rs1.getString("SOTT"), cformat);
							sheet.addCell(label);
	
							label = new Label(7, j, rs1.getString("TENCUAHIEU"), cformat);
							sheet.addCell(label);
	
							label = new Label(8, j, rs1.getString("DIACHI"), cformat);
							sheet.addCell(label);
							
							label = new Label(9, j, formatter.format( rs1.getDouble("DoanhSo") )  , cformat);
							sheet.addCell(label);						
	
							label = new Label(10, j, rs1.getString("THOIDIEM"), cformat);
							sheet.addCell(label);
							
							label = new Label(11, j, String.valueOf(_dolech), cformat);
							sheet.addCell(label);
							
							label = new Label(12, j, rs1.getString("thoidiemdi") , cformat);
							sheet.addCell(label);
							label = new Label(13, j, rs1.getString("GHICHU") , cformat);
							sheet.addCell(label);
							j++;
							
						}
					}

				k++;
			}
			w.write();
			w.close();
		} catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			if (out1 != null)
				out1.close();
		}
	}
	
	public double getKhoangCachHaversine(double lat1, double long1, double lat2, double long2)
    {
        double R = 6371.00;
        double dLat, dLon, a, c;

        dLat = toRad(lat2 - lat1);
        dLon = toRad(long2 - long1);
        lat1 = toRad(lat1);
        lat2 = toRad(lat2);
        a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.sin(dLon / 2) * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.abs(R * c * 1000  ); //m
    }
	
	private double toRad(double value)
    {
        return value * Math.PI / 180;
    }
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getMonth() 
	{
        DateFormat dateFormat = new SimpleDateFormat("MM");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getYear() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	

	public static void main(String[] args)
	{
		/*Calendar tu_ngay = new GregorianCalendar(2013, 1, 28);		
		
		tu_ngay.add(Calendar.DATE, 1);
		System.out.println("COMPARE: " + tu_ngay.get(Calendar.YEAR));
		System.out.println("COMPARE: " + tu_ngay.get(Calendar.MONTH));
		System.out.println("COMPARE: " + tu_ngay.get(Calendar.DAY_OF_MONTH));*/	
	}
	public String setQuery(ILoTrinh lotrinh)
	{	
		String tuyen =   "\n 	select distinct ddkd_sokh.DDKD_FK,Ngay,NgayId,SoKH,khachhang_fks ,Allkhachhang_fks  " + 
						 "\n 	from ddkd_sokh     " + 
						 "\n 	inner join daidienkinhdoanh ddkd on ddkd_sokh.DDKD_FK = ddkd.pk_seq   " + 
						 "\n 	WHERE ddkd_sokh.NGAY >='"+lotrinh.getTungay()+"' AND  ddkd_sokh.NGAY <='"+lotrinh.getDenngay()+"'    " + 
						 "\n 	and ddkd_sokh.ngayId !=0  " ;
		
		if(lotrinh.getnppId().trim().length() > 0)
		{
			tuyen += "\n and exists ( select  1 from daidienkinhdoanh_npp x where npp_fk = '" + lotrinh.getnppId() + "' and x.ddkd_fk = ddkd_sokh.DDKD_FK )  ";
		}
		else if(lotrinh.getddkdId().trim().length() > 0)
		{
			tuyen += "\n and ddkd_sokh.DDKD_FK = '" + lotrinh.getddkdId() + "' ";
		}
		else 
		{
			if(lotrinh.getddkdId().trim().length() > 0|| lotrinh.getTinhthanhId().trim().length() > 0 )
			{
				/*if(lotrinh.getnppId().trim().length() > 0)
					tuyen += "\n and exists ( select  1 from daidienkinhdoanh_npp x where npp_fk = '" + lotrinh.getnppId() + "' and x.ddkd_fk = ddkd_sokh.DDKD_FK )  ";
*/					if(lotrinh.getTinhthanhId().trim().length() > 0)
					tuyen += "\n and ddkd.diaban_fk in ("+lotrinh.getTinhthanhId()+") ";
			}
			else
				if(lotrinh.getkhuvucId().trim().length() > 0)
					tuyen += "\n and ddkd.diaban_fk in (select pk_seq from diaban where khuvuc_fk = "+lotrinh.getkhuvucId()+") ";					
				else if(lotrinh.getVungId().length() > 0)
					tuyen += "\n and ddkd.diaban_fk in (select pk_seq from diaban " +
							 "\n 						where khuvuc_fk in ( select khuvuc_fk from khuvuc where vung_fk = "+lotrinh.getVungId()+") ) ";
		}
		
		if (lotrinh.getddkdId() != null && lotrinh.getddkdId().length() > 0) {
			tuyen += "\n and ddkd.pk_seq = "+lotrinh.getddkdId();
		}
		
		if (!lotrinh.getView().equals("NPP"))
		{
			tuyen += "\n and ddkd_sokh.DDKD_FK in ("+geso.dms.center.util.Utility.PhanQuyenDDKD(lotrinh.getUserId())+" ) " ;
		}
		
		
		
		String query = "\n  with ddkdsokh as  " + 
		 "\n  (  " + 
		 					tuyen +
		 "\n  )  " + 
		 
		 "\n  ,vt as (" + 
		 "\n  		select a.khachhang_fk, a.ddkd_fk ,a.ngaythuchien" + 
		 "\n  		from ddkd_khachhang a inner join ddkdsokh on a.ddkd_fk = ddkdsokh.DDKD_FK and a.ngaythuchien = ddkdsokh.NGAY" + 
		 "\n  		group by a.khachhang_fk, a.ddkd_fk ,a.ngaythuchien" + 
		 "\n  ) " + 
		 "\n  , dh as(" + 
		 "\n  	select dh.* " + 
		 "\n  	from donhang dh" + 
		 "\n  	inner join ddkdsokh on dh.DDKD_FK = ddkdsokh.DDKD_FK and dh.NGAYNHAP = ddkdsokh.NGAY" + 
		 "\n  	where dh.TRANGTHAI != 2 and dh.IsPDA = 1 " + 
		 "\n  )" + 
		 "\n  , khngay as" + 
		 "\n  (" + 
		/* "\n  	select  dh.NGAY ,kh.PK_SEQ,kh.TEN,kh.DIACHI,kh.maFAST,kh.diaban_fk,kh.LCH_FK,kh.NPP_FK,dh.DDKD_FK " + 
		 "\n  	from khachhang kh inner join [DDKD_Ngay_KH_Log] dh on  kh.PK_SEQ =dh.khachhang_fk" +
		 "\n	inner join ddkdsokh on ddkdsokh.ddkd_fk = dh.ddkd_fk and ddkdsokh.ngay = dh.ngay " + 
		 "\n  	union" + 
		 "\n  	select  dh.NGAYNHAP NGAY , kh.PK_SEQ,TEN,DIACHI,maFAST,kh.diaban_fk,LCH_FK,kh.NPP_FK,dh.DDKD_FK" + 
		 "\n  	from khachhang kh inner join dh on  dh.KHACHHANG_FK = kh.PK_SEQ" + 
		 "\n  	union" + */
		 "\n  	select  vt.ngaythuchien NGAY , kh.PK_SEQ,TEN,DIACHI,maFAST,kh.diaban_fk,LCH_FK,kh.NPP_FK,vt.ddkd_fk" + 
		 "\n  	from khachhang kh inner join vt on  vt.KHACHHANG_FK = kh.PK_SEQ" + 
		 "\n  )    " + 
		 "\n  select	ROW_NUMBER() OVER (PARTITION BY MANHANVIEN,NGAY ORDER BY  MANHANVIEN,NGAY, case when  MAKH ='' then 0 else 1 end,isnull(TGDenBatDau,'3000')  )-1 STT    " + 
		 "\n  	,kq.*  " + 
		 "\n  from   " + 
		 "\n  (  " + 
		 "\n    select distinct null DiaBan,null NPP,ddkd.maFAST MANHANVIEN, ddkd.TEN ddkdTen,'' MaKH,''LoaiKH,'' KhachHang,''DiaChi  " + 
		 "\n  			, N'Thứ ' + cast( ISNULL(CODI.NGAYID,0) as varchar) as NGAYID,  CODI.NGAY  " + 
		 "\n  			,cast ( isnull( CODI.sokh,0) as varchar) PHAIVT, cast ( isnull(  vtham.SOVT ,0) as varchar) DaVT      " + 
		 "\n    			,isnull(doanhso.SODH,0) as SODH,isnull(doanhso.DHCOVT,0)as DHCOVT  " + 
		 "\n    			,isnull(doanhso.SODH,0) - isnull(doanhso.DHCOVT,0) DHKOVT      " + 
		 "\n    			,cast( [dbo].[TongKhoangCachTDV](CODI.ddkd_fk,CODI.NGAY) as varchar) quangduong  " + 
		 "\n    			, convert ( varchar ,vtham.TGDenBatDau,120)TGDenBatDau  ,convert ( varchar ,TGCuoiCung,120) TGDi" + 
		 "\n 			, cast ( DATEDIFF(MINUTE, vtham.TGDenBatDau,vtham.TGCuoiCung)  - isnull(vtham.tongdichuyen_phut,0) as varchar) TgDiChuyen	" + 
		 "\n 			,cast ( DATEDIFF(MINUTE, vtham.TGDenBatDau,vtham.TGCuoiCung) as varchar) TongTg  " + 
		 "\n    			, isnull(doanhso.doanhso,0) as DOANHTHU,'' SKU, '' TGMuaHang,''DDGiaoHang,''Ghichu    	 	    " + 
		 "\n     from  ddkdsokh CODI" + 
		 "\n 	inner join   daidienkinhdoanh ddkd on CODI.DDKD_FK = ddkd.PK_SEQ     " + 
		 "\n     outer apply" + 
		 "\n     (    " + 
		 "\n   		select SUM(soluong*giamua) as doanhso ,COUNT(distinct dh.PK_SEQ) SODH    " + 
		 "\n   			, COUNT ( distinct case when vt.khachhang_fk is not null then dh.pk_seq else null end )  DHCOVT    " + 
		 "\n   		from dh     " + 
		 "\n   		inner join DONHANG_SANPHAM dh_sp	 on dh.PK_SEQ = dh_sp.DONHANG_FK     " + 
		 "\n   		left join  vt on vt.khachhang_fk =dh.KHACHHANG_FK and dh.ddkd_fk = vt.ddkd_fk  and dh.NGAYNHAP  = vt.ngaythuchien    " + 
		 "\n 		where  CODI.ddkd_fk = dh.DDKD_FK and CODI.ngay = dh.NGAYNHAP       " + 
		 "\n     )DOANHSO     " + 
		 "\n 	outer apply   " + 
		 "\n     (      " + 
		 "\n    		SELECT  COUNT(DISTINCT DDKH.KHACHHANG_FK) AS SOVT " + 
		 "\n    		,min(DDKH.thoidiem) TGDenBatDau, case when max(DDKH.thoidiemdi) > max(DDKH.thoidiem) then max(DDKH.thoidiemdi) else max(DDKH.thoidiem) end TGCuoiCung" + 
		 "\n 		, sum(   DATEDIFF(MINUTE, DDKH.thoidiem, DDKH.thoidiemdi) ) tongdichuyen_phut" + 
		 "\n 		, sum(   DATEDIFF(hour, DDKH.thoidiem, DDKH.thoidiemdi) ) tongdichuyen_gio     " + 
		 "\n    		FROM ddkd_khachhang DDKH     " + 
		 "\n    		where  DDKH.ddkd_fk = CODI.ddkd_fk and DDKH.ngaythuchien = CODI.NGAY          " + 
		 "\n     ) vtham  " + 
		 "\n     where CODI.sokh is not null   " + 		 
		 "\n     union all        " + 		 		 
		 "\n     select    " + 
		 "\n  		db.diengiai DiaBan,npp.ten NPP,tdv.mafast MaTDV,tdv.ten TenTDV,kh.mafast MaKH, lch.diengiai LoaiKH,kh.ten KhachHang  " + 
		 "\n  		,kh.DiaChi,'' ngayId,ddkdsokh.ngay	,'' phaivt" + 
		 "\n 		,	isnull (DDKD.VTNT, case when charindex(',' + cast(kh.pk_seq as varchar(10)) + ',', ddkdsokh.KhachHang_Fks) >= 1 then N'Không VT' else '' end ) davt  " + 
		 "\n  		,ISNULL(DonHang.SODH,0) as SODH   ,isnull(DonHang.DHCOVT,0) as DHCOVT    " + 
		 "\n  		, ISNULL(DonHang.SODH,0)  - isnull(DonHang.DHCOVT,0) DHKOVT    " + 
		 "\n  		,'' QuangDuong,convert ( varchar ,DDKD.ThoiDiemDen,120),convert ( varchar ,DDKD.ThoiDiemDi,120)  " + 
		 "\n  		,'' TGDiChuyen  " + 
		 "\n   		, cast ( isnull(DATEDIFF(MINUTE, ThoiDiemDen, ThoiDiemDi), 0) as varchar)  as TongTG    " + 
		 "\n   		,isnull(DonHang.DoanhSo,0) DS, cast ( isnull(DonHang.SKU,0) as varchar) SKU    " + 
		 "\n   		,convert ( varchar , DonHang.NgayGio_Tao,120) as NgayGioDonHang    " + 
		 "\n   		,npp.TEN DDXUATHANG     " + 
		 "\n   		 ,DDKD.GhiChu     " + 
		 "\n   	from ddkdsokh   " + 
		 "\n   	inner join daidienkinhdoanh tdv on tdv.pk_Seq = ddkdsokh.ddkd_fk  " + 
		 "\n     inner join khngay kh on kh.NGAY = ddkdsokh.NGAY and kh.DDKD_FK = ddkdsokh.DDKD_FK " + 
		 "\n   	left join diaban db on db.pk_seq = kh.diaban_fk  " + 
		 "\n  	left join loaicuahang lch on lch.pk_seq = kh.lch_fk  " + 
		 "\n   	inner join nhaphanphoi npp on npp.pk_seq = kh.npp_fk  " + 
		 "\n   	outer apply       " + 
		 "\n   	(       " + 
		 "\n   		select	 min(a.thoidiem) as ThoiDiemDen,       " + 
		 "\n   			 min(a.thoidiemdi) as ThoiDiemDi, ISNULL(a.GHICHU,'') AS GHICHU     " + 
		 "\n   			 ,case when	a.isDungTuyen = 0  then N'Ngoài Tuyến'  else 	N'Đúng tuyến' end as VTNT ,	a.TanSo,a.NgayId    " + 
		 "\n   		from ddkd_khachhang a   " + 
		 "\n   		where  ddkdsokh.ddkd_fk = a.DDKD_FK and kh.pk_seq = a.KHACHHANG_FK and ddkdsokh.ngay = a.ngaythuchien   " + 
		 "\n   		group by  a.GHICHU ,	a.isDungTuyen,	a.TanSo,a.NgayId    " + 
		 "\n   	)  	DDKD " + 
		 "\n   	outer apply    " + 
		 "\n   	(    " + 
		 "\n   		select COUNT(distinct dh.PK_SEQ)SODH,MIN(NgayGio_Tao)NgayGio_Tao     " + 
		 "\n   			,	COUNT ( distinct case when vt.khachhang_fk is not null then dh.pk_seq else null end)  DHCOVT    " + 
		 "\n   			,SUM(SOLUONG *GIAMUA)DoanhSo,COUNT( distinct dh_sp.sanpham_fk) as SKU    " + 
		 "\n   			    " + 
		 "\n   		from  dh    " + 
		 "\n   		inner join DONHANG_SANPHAM dh_sp on dh.PK_SEQ = dh_sp.DONHANG_FK   " + 
		 "\n   		left join   vt on vt.khachhang_fk = dh.KHACHHANG_FK and dh.ddkd_fk = vt.ddkd_fk and dh.NGAYNHAP  = vt.ngaythuchien   " + 
		 "\n 		where kh.pk_seq = dh.KHACHHANG_FK  and  dh.ddkd_fk =  ddkdsokh.DDKD_FK and ddkdsokh.ngay   = dh.ngaynhap  " + 
		 "\n   	)  DonHang " + 
		 "\n   )kq " + 
		 "\n  where 1=1 " + 
		 "\n   order by manhanvien,ngay	";
		
			System.out.println("query = "+query);
			return query;
	}
	
	
	private void CreatePivotTable(OutputStream out,ILoTrinh obj,String query) throws Exception 
	{
		try 
		{
		
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(com.aspose.cells.FileFormatType.EXCEL2007XLSM);
			CreateHeader(workbook,obj); 
			FillData(workbook, obj,query);			
			workbook.save(out);
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	

	private void CreateHeader(com.aspose.cells.Workbook workbook,ILoTrinh obj) 
	{
		com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
		com.aspose.cells.Worksheet worksheet = worksheets.getSheet(0);	    
	    worksheet.setName("Sheet1");
	    com.aspose.cells.Cells cells = worksheet.getCells();	 
	    
	    cells.setRowHeight(0, 20.0f);	   
	    com.aspose.cells. Cell cell = cells.getCell("A1");	
	    ReportAPI.getCellStyle(cell,com.aspose.cells.Color.NAVY,true,10,"BÁO CÁO LỘ TRÌNH BÁN HÀNG CHI TIẾT"  );
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell,com.aspose.cells.Color.NAVY,true,10,"Từ ngày  : " + obj.getTungay() + "  đến ngày : " + obj.getDenngay());
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell,com.aspose.cells.Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
	    
	    
	    int cot = 0;
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("STT");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("ĐỊA BÀN");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("CHI NHÁNH / NPP");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("MÃ NVBH");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TDV");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("MÃ KH");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("LOẠI KH");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("KHÁCH HÀNG");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("ĐỊA CHỈ");
	    
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TUYẾN BH");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("NGÀY");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("PHẢI VT");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("ĐÃ VT");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("SỐ ĐH");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("ĐH CÓ VT");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("ĐH KHÔNG VT");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("QUÃNG ĐƯỜNG");
	    
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TG ĐẾN");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TG ĐI");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TG DI CHUYỂN");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TỔNG TG");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("DOANH THU");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("SKU");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("TG MUA HÀNG");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("Đ/Đ XUẤT HÀNG");
	    cell = cells.getCell(4,cot++);ReportAPI.setCellBackground(cell, com.aspose.cells.Color.GRAY, BorderLineType.THIN, true, 0); cell.setValue("GHI CHÚ VIẾNG THĂM");   
	}
	
	public boolean isDouble(String s)
	{
		try
		{
			double a= Double.parseDouble(s);
			
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	private void FillData(com.aspose.cells.Workbook workbook, ILoTrinh obj,String query) throws Exception
	{
		//System.out.println("cau qery" + query);
		
		ResultSet rs = null;
		try
		{	
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			com.aspose.cells.Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
				
			
			int index = 5;
			int column = 0;
			com.aspose.cells.Cell cell = null;
		    boolean coData= false;
		    
		    rs = obj.getDb().get(query);
		    
		    ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
		    
		    while(rs.next())
			{
		    	coData = true;
		    	com.aspose.cells.Color c = com.aspose.cells.Color.WHITE;
				if(rs.getInt("STT") <=0)
					c = com.aspose.cells.Color.SILVER;
				for(int i =1;i <=socottrongSql ; i ++)
				{
					
					cell = cells.getCell(index,column + i-1 );
						
					if( i !=1 && isDouble(rs.getString(i)) && ! rsmd.getColumnName(i).toUpperCase().contains("MA") )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, format);
					}
					else
					{
						if(i != 1)
							cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
				}
				
				++index;
			}

			if(rs!=null){
				rs.close();
			}	

			if(!coData)
				throw new Exception("Không có dữ liệu theo điều kiện lọc");
		    			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(rs != null)
			{
				rs.close();
			}
			ex.printStackTrace();
			
			throw new Exception(ex.getMessage());
		}
	}
	
	
	
}
