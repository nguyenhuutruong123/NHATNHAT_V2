package geso.dms.distributor.servlets.nhaphang;

import geso.dms.distributor.beans.nhaphang.INhaphangList;
import geso.dms.distributor.beans.nhaphang.imp.NhaphangList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class NhaphangSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;

	public NhaphangSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{
			INhaphangList obj;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			// out.println(action);

			userId = util.getUserId(querystring);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			obj = new NhaphangList();
			obj.setUserId(userId);
			obj.createNhaphanglist("");
			session.setAttribute("obj", obj);

			String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHang.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{
			INhaphangList obj;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			obj = new NhaphangList();

			Utility util = new Utility();
			userId = util.antiSQLInspection(request.getParameter("userId"));

			String action = request.getParameter("action");
			if (action == null)
			{
				action = "";
			}
			obj.setUserId(userId);
			String search = getSearchQuery(request, obj);
			System.out.println(search);

			obj.setUserId(userId);
			obj.createNhaphanglist(search);
 
			session.setAttribute("obj", obj);

			String nextJSP = request.getContextPath() + "/pages/Distributor/NhapHang.jsp";
			
			if (action.equals("toExcel")) 
    	{
				ToExcel(response, obj,getSearchQuery_Excel(request,obj));
				return;
			} 
			
			response.sendRedirect(nextJSP);

			
		
			
		}

	}
	
	private String getSearchQuery_Excel(HttpServletRequest request,INhaphangList obj) 
	{
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		if (userId == null)
			userId = "";
		obj.setDangnhap(userId);

		String nppId = util.getIdNhapp(userId);

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";
		obj.setDenngay(denngay);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);

		


		String query=
		"		select a.sonetId,a.NGAYCHUNGTU,c.MA as spMA,b.SOLUONG,b.SOLUONG*b.DONGIA as GiaTri,b.SOLO,b.NGAYHETHAN  "+
		"		, case    "+
		"			when YCXK_FK IS not null then CAST( YCXK_FK   AS VARCHAR(50) )  "+ 
		"			when CHUYENKHO_FK IS not null then (SELECT SoChungTu from ERP_CHUYENKHO where PK_SEQ=a.CHUYENKHO_FK) "+  
		"			when YCXKNPP_FK IS not null then  "+
		"		(      "+ 
		"				SELECT SOHOADON FROM ERP_HOADONNPP_DDH Aa     "+
		"					INNER JOIN  ERP_YCXUATKHONPP_DDH Bb ON Aa.DDH_FK=Bb.DDH_FK   "+ 
		"					INNER JOIN ERP_HOADONNPP Cc ON Cc.PK_SEQ=Aa.HOADONNPP_FK  "+   
		"				WHERE C.TRANGTHAI IN (2,4)	AND bB.YCXK_FK=a.YCXKNPP_FK  "+  
		"			)    "+
		"		when CHUYENKHONPP_FK IS NOT NULL THEN (  SELECT SoChungTu from ERP_CHUYENKHONPP where pk_seq=a.CHUYENKHONPP_FK)  "+ 
		"		else SoChungTu end   as SoChungTu ,  "+
		"		CASE when YCXKNPP_FK IS not null then  "+ 
		"		(       "+
		"				SELECT KYHIEU FROM ERP_HOADONNPP_DDH Aa     "+
		"					INNER JOIN  ERP_YCXUATKHONPP_DDH Bb ON Aa.DDH_FK=Bb.DDH_FK   "+  
		"					INNER JOIN ERP_HOADONNPP Cc ON Cc.PK_SEQ=Aa.HOADONNPP_FK  "+    
		"				WHERE C.TRANGTHAI IN (2,4)	AND bB.YCXK_FK=a.YCXKNPP_FK  "+  
		"		) end as KyHieu   "+
		"		from NHAPHANG a inner join NHAPHANG_SP b on b.NHAPHANG_FK=a.PK_SEQ  "+
		"		inner join SANPHAM c on c.PK_SEQ=b.SANPHAM_FK  "+
		"	where a.npp_fk = '" + nppId + "'   ";
		
		
		if (tungay.length() > 0)
		{
			query = query + " and a.ngaychungtu >= '" + tungay + "'";
		}

		if (denngay.length() > 0)
		{
			query = query + " and a.ngaychungtu <= '" + denngay + "'";
		}

		if (trangthai.length() > 0)
		{
			query = query + " and a.trangthai = '" + trangthai + "'";
		}
		query = query + " order by a.trangthai, a.ngaychungtu, a.SoChungTu";
		
		System.out.println("_________"+query);
	return query;
  	
	}
	
	
	private void ToExcel(HttpServletResponse response, INhaphangList obj,String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=NhapHang.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 1;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 15);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);
			
			WritableCellFormat celltieude = new WritableCellFormat(cellTitle);
			celltieude.setAlignment(Alignment.CENTRE);
			
			WritableFont cellFont = new WritableFont(WritableFont.ARIAL, 13);
			cellFont.setColour(Colour.BLACK);
			
			NumberFormat dp3 = new NumberFormat("#,###,###,##");
			WritableCellFormat inFormat = new WritableCellFormat(dp3);
			inFormat.setFont(cellFont);
		
			inFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			
			WritableFont cellFontWhite = new WritableFont(WritableFont.TIMES, 13);
			cellFontWhite.setColour(Colour.WHITE);
			
			WritableCellFormat cellFormat = new WritableCellFormat(cellFontWhite);

			cellFormat.setBackground(jxl.format.Colour.GRAY_80);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.WHITE);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.WHITE);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.WHITE);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.WHITE);

			
			WritableCellFormat cformat3 = new WritableCellFormat(cellFont);
			cformat3.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cformat3.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cformat3.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cformat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			
			sheet = w.createSheet("Nhập Hàng", k);// ten sheet
					
			sheet.addCell(new Label(0, 0, "Ngày hóa đơn", cellFormat));
			sheet.addCell(new Label(1, 0, "Ký Hiệu Hóa Đơn", cellFormat));
			sheet.addCell(new Label(2, 0, "Số Hóa Đơn", cellFormat));
			sheet.addCell(new Label(3, 0, "Mã Hàng", cellFormat));
			sheet.addCell(new Label(4, 0, "Số Lượng", cellFormat));
			sheet.addCell(new Label(5, 0, "Trị giá chưa VAT", cellFormat));
			sheet.addCell(new Label(6, 0, "Số Lô", cellFormat));
			sheet.addCell(new Label(7, 0, "Hạn dùng", cellFormat));
			
		
		
			inFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			
			sheet.setRowView(100, 4);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			dbutils db = new dbutils();

			ResultSet rs = db.get(query);

			Label label;
			Number number;
			
			rs=db.get(query);
			
			while (rs.next())
			{
				label = new Label(0, j,getFormatDate( rs.getString("NgayChungTu")), cformat3);sheet.addCell(label);
				label = new Label(1, j, rs.getString("KyHieu")==null?"":rs.getString("KyHieu"), cformat3);sheet.addCell(label);
				label = new Label(2, j, rs.getString("SoChungTu"), cformat3);sheet.addCell(label);
				label = new Label(3, j, rs.getString("spMa"), cformat3);sheet.addCell(label);
				number = new Number(4, j, rs.getDouble("SoLuong"), inFormat);sheet.addCell(number);
				number = new Number(5, j, rs.getDouble("GiaTri"), inFormat);sheet.addCell(number);
				label = new Label(6, j, rs.getString("SoLo"), cformat3);sheet.addCell(label);
				label = new Label(7, j,getFormatDate( rs.getString("NgayHetHan")), cformat3);sheet.addCell(label);
				j++;
			}
			w.write();
			w.close();
			rs.close();
			db.shutDown();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (out != null)
				out.close();

		}
		
	}
	
	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");
		
		return arr[2] + "/" + arr[1] + "/" + arr[0];
	}
	

	private String getSearchQuery(HttpServletRequest request, INhaphangList obj)
	{
		// PrintWriter out = response.getWriter();
		Utility util = new Utility();
		geso.dms.center.util.Utility utilCenter = new geso.dms.center.util.Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		if (userId == null)
			userId = "";
		obj.setDangnhap(userId);

		String nppId = util.getIdNhapp(userId);

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";
		obj.setDenngay(denngay);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);

		String soct = util.antiSQLInspection(request.getParameter("soct"));
		if (soct == null)
			soct = "";
		obj.setSoct(soct);

		String sodh = util.antiSQLInspection(request.getParameter("sodh"));
		if (sodh == null)
			sodh = "";
		obj.setSodh(sodh);

		String query =  	
			"\n	select  a.sonetId,a.ngaychungtu,  "+ 
			"\n	 case     "+
			"\n		when A.YCXK_FK IS not null then   'XKHO_' + CAST( A.YCXK_FK   AS VARCHAR(50) )  "+
			"\n		when A.CHUYENKHO_FK IS not null then 'XCNB_' + ( SELECT SoChungTu from ERP_CHUYENKHO where PK_SEQ=A.CHUYENKHO_FK)  "+  
			"\n		when A.YCXKNPP_FK IS not null then  "+  
			"\n		'HD_' + (     "+
					"			select SOHOADON from ERP_HOADONNPP_DDH aa      "+
					"			inner join  ERP_YCXUATKHONPP_DDH b on aa.DDH_FK=b.ddh_fk     "+
					"			inner join ERP_HOADONNPP c on c.PK_SEQ=aa.HOADONNPP_FK      "+   
					"			where c.TRANGTHAI in (2,4)	and b.ycxk_fk=a.YCXKNPP_FK  "+			
			"\n		) when a.CHUYENKHONPP_FK IS NOT NULL THEN 'XCNB_' +(SELECT SoChungTu from ERP_CHUYENKHONPP where PK_SEQ=A.CHUYENKHONPP_FK)  "+
			"\n	end as dathang_fk ,  "+
			"\n		a.ngaynhan, a.pk_seq as chungtu, isnull(e.donvikinhdoanh, '') as donvikinhdoanh, f.ten as kbh ,isnull(sp.tt,0) - isnull(km.tt,0)  as sotienavat, b.ten as nguoitao,  "+
			"\n		 c.ten as	nguoisua, a.trangthai  ,a.YCXK_FK,a.YCXKNPP_FK,a.CHUYENKHO_FK,a.CHUYENKHONPP_FK  "+
			"\n	from nhaphang a  inner join   nhanvien b  on  a.nguoitao = b.pk_seq "+   
			"\n	inner  join  nhanvien c on a.nguoisua = c.pk_seq     "+
			"\n	left join  donvikinhdoanh e on e.pk_seq = a.dvkd_fk  "+   
			"\n	left join  kenhbanhang f  on f.pk_seq = a.kbh_fk" +
			"\n	outer apply" +
			"\n	(" +
			"\n		select sum( round(SOLUONG* dongia * (1 + thuevat/100.0),0) ) tt from NHAPHANG_SP where NHAPHANG_FK=a.pk_seq	" +
			"\n	) sp " +
			"\n	outer apply" +
			"\n	(" +
			"\n		select sum(giatri) tt from nhaphang_chietkhau where NHAPHANG_FK=a.pk_seq	" +
			"\n	) km " +
			"\n	where a.npp_fk = '" + nppId + "' ";
		
		System.out.println("query search ;" + query);

		if (tungay.length() > 0)
		{
			query = query + "   and a.ngaychungtu >= '" + tungay + "'";
		}

		if (denngay.length() > 0)
		{
			query = query + " and a.ngaychungtu <= '" + denngay + "'";
		}
		if (soct.length() > 0) {
			String fk = " 	select   case     "
					+ "		when A.YCXK_FK IS not null then   'XKHO_' + CAST( A.YCXK_FK   AS VARCHAR(50) )  "
					+ "		when A.CHUYENKHO_FK IS not null then 'XCNB_' + ( SELECT TOP(1) SoChungTu from ERP_CHUYENKHO where PK_SEQ=A.CHUYENKHO_FK)  "
					+ "		when A.YCXKNPP_FK IS not null then  "
					+ "		'HD_' + (     "
					+ "			select SOHOADON from ERP_HOADONNPP_DDH aa      "
					+ "			inner join  ERP_YCXUATKHONPP_DDH b on aa.DDH_FK=b.ddh_fk     "
					+ "			inner join ERP_HOADONNPP c on c.PK_SEQ=aa.HOADONNPP_FK      "
					+ "			where c.TRANGTHAI in (2,4)	and b.ycxk_fk=a.YCXKNPP_FK  "
					+ "		) when a.CHUYENKHONPP_FK IS NOT NULL THEN 'XCNB_' +(SELECT TOP(1) SoChungTu from ERP_CHUYENKHONPP where pk_seq=A.CHUYENKHONPP_FK)  "
					+ "	end as dathang_fk";
			query = query + "and   (" + fk + ") like '%" + soct + "%'";
		}

		if (sodh.length() > 0) {
			query = query
 + " and a.pk_seq  in (select nhaphang_fk from NHAPHANG_DDH where ddh_fk like '%" + sodh + "%') ";

		}

		if (trangthai.length() > 0)
		{
			query = query + " and a.trangthai = '" + trangthai + "'";
		}

		query = query + " order by  ngaychungtu desc,trangthai desc, chungtu desc";
		return query;
	}

}
