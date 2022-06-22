
package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.report.Ireport;
import geso.dms.distributor.beans.report.imp.Report;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class BCDonDatHangNPP extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	private String setQueryETC(HttpServletRequest request,Ireport obj)
	{

		
		String conditiondh= "";
		String conditionth= "";
		Utility util = new Utility();
		
		
		if (obj.getChinhanhId().length() > 0)
		{
			conditiondh += " and ddh.NPP_FK ='" + obj.getChinhanhId() + "'";
			conditionth += " and th.Npp_FK ='" + obj.getChinhanhId() + "'";
		}
		else
		{
			conditiondh += " and ddh.NPP_FK in " + util .quyen_npp(obj.getuserId()) ;
			conditionth += " and th.Npp_FK in " + util .quyen_npp(obj.getuserId()) ;

		}
		if (obj.getnppId().length() > 0)
		{
			conditiondh += " and ddh.Npp_FK ='" + obj.getnppId() + "'";
			conditionth += " and th.Npp_FK ='" + obj.getnppId() + "'";
		}
		
		if (obj.getKvId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk ='" + obj.getKvId() + "'";
			conditionth += " and npp.khuvuc_fk ='" + obj.getKvId() + "'";
			
		}
		if (obj.getVungId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk =" + obj.getVungId() + ")  ";
			conditionth += " and npp.khuvuc_fk  in (select pk_seq from khuvuc where vung_fk =" + obj.getVungId() + ")  ";
			
		}

		if(obj.getTrangthai().length()>0)
		{
			conditiondh += " and ddh.trangthai ='" + obj.getTrangthai() + "'";
			conditionth += " and th.trangthai ='" + obj.getTrangthai() + "'";
		}		
		
		String query =
			 "\n  	with ddh as    " + 
			 "\n  	(    " + 
			 "\n  	    " + 
			 "\n  		SELECT distinct DDH.*   " + 
			 "\n 		FROM     " + 
			 "\n  		ERP_DONDATHANGNPP DDH     " + 
			 "\n  		WHERE DDH.NgayDonHang >='"+obj.gettungay()+"' AND DDH.NgayDonHang <= '"+obj.getdenngay()+"' and ddh.trangthai !=3    " + 
			 "\n  			AND DDH.KHACHHANG_FK IS NOT NULL  " + 
			 "\n  	)    " + 
			 "\n   	, th as      " + 
			 "\n   	(      " + 
			 "\n   		SELECT distinct th.*   " + 
			 "\n   		FROM     Erp_HangTraLaiNpp th       " + 
			 "\n   		INNER JOIN KHACHHANG KH ON KH.PK_SEQ = th.KHACHHANG_FK  " + 
			 "\n   		WHERE th.NGAYTRA >='"+obj.gettungay()+"' AND th.NGAYTRA <=  '"+obj.getdenngay()+"'  and kh.kbh_fk = 100052 and th.trangthai !=3  	    " + 
			 "\n   	)     " + 
			 "\n  	SELECT  cast ( ISNULL(DDH.pk_seq,'NA') as varchar) AS MaDonHang, cast ( DDH.HopDong_fk as varchar) MaHopDong  " + 
			 "\n  		,DDH.NgayDonHang  " + 
			 "\n  	,CASE DDH.TRANGTHAI  WHEN '0' THEN N'Chưa chốt' WHEN '1' THEN N'Chờ duyệt'   WHEN '2' THEN N'Đã duyệt'   WHEN '3' THEN N'Đã Hủy'   WHEN '4' THEN N'Hoàn Tất'  END AS TRANGTHAI " + 
			 "\n 	,npp.mafast MaNPP,npp.ten TenNPP,kh.MAfast AS MAKH, kh.TEN AS TENKH, KV.TEN AS VUNG,V.TEN   AS MIEN ,SP.MA AS MASP,     " + 
			 "\n      " + 
			 "\n  		SP.TEN AS TENSP,DVDL.DONVI    " + 
			 "\n  		   " + 
			 "\n  		 , ISNULL( DH_SP.SOLUONG,0) AS SoLuong,(DH_SP.DONGIA* ISNULL(DH_SP.SOLUONG,0)) AS TongTien     " + 
			 "\n      " + 
			 "\n  		, case  DDH.isdhkhac when 0 then 'HB' else 'KM' end loaidonhang    " + 
			 "\n      " + 
			 "\n  	FROM DDH       " + 
			 "\n  	INNER JOIN khachhang kh ON kh.PK_SEQ = DDH.khachhang_fk    " + 
			 "\n  	inner join nhaphanphoi npp on npp.pk_Seq = DDH.npp_Fk   " + 
			 "\n  	LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=DDH.nguoisua     " + 
			 "\n  	LEFT JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK         " + 
			 "\n  	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK        " + 
			 "\n  	INNER JOIN ERP_DONDATHANGNPP_SANPHAM DH_SP ON DH_SP.DONDATHANG_FK = DDH.PK_SEQ     " + 
			 "\n  	INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=DH_SP.dvdl_fk     " + 
			 "\n  	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DH_SP.SANPHAM_FK     " + 
			 "\n  	WHERE DH_SP.soluong > 0  " + conditiondh + 
			 "\n  	union all    " + 
			 "\n   	SELECT  cast (-1* th.pk_seq  as varchar) AS MaDonHang,'' MaHopDong  " + 
			 "\n   		,th.NGAYTRA   " + 
			 "\n  	,CASE th.TRANGTHAI  WHEN '0' THEN N'Chưa chốt' WHEN '1' THEN N'Đã chốt'   WHEN '2' THEN N'Hoàn tất'   WHEN '3' THEN N'Đã Hủy '     END AS TRANGTHAI  " + 
			 "\n  	,npp.mafast MaNPP,npp.ten TenNPP,kh.MAfast , kh.TEN , KV.TEN AS VUNG,V.TEN   AS MIEN ,SP.MA AS MASP,       " + 
			 "\n         " + 
			 "\n   		SP.TEN AS TENSP,DVDL.DONVI      " + 
			 "\n   		 ,(-1)* ISNULL( DH_SP.SOLUONG,0) AS SoLuong      " + 
			 "\n   		 ,(-1)*(DH_SP.DONGIA* ISNULL(DH_SP.SOLUONG,0)) AS tongTien       " + 
			 "\n         " + 
			 "\n   		, N'TH' loaidonhang      " + 
			 "\n         " + 
			 "\n   	FROM th         " + 
			 "\n   	INNER JOIN khachhang kh ON kh.PK_SEQ = th.khachhang_fk    " + 
			 "\n  	inner join nhaphanphoi npp on npp.pk_Seq = th.npp_Fk   " + 
			 "\n   	LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=th.nguoisua       " + 
			 "\n   	LEFT JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK              " + 
			 "\n   	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK            " + 
			 "\n   	INNER JOIN Erp_HangTraLaiNpp_SanPham DH_SP ON DH_SP.HangTraLai_fk = th.PK_SEQ         " + 
			 "\n   	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DH_SP.SANPHAM_FK     " + 
			 "\n   	INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=SP.dvdl_fk         " + 
			 "\n   	WHERE 1=1  " +conditionth ;
			 			 
	
		System.out.println("BC Don Dat Hang NPP : " + query);
		return query;
	
	}
	private String setQuery(HttpServletRequest request,Ireport obj)
	{
		
		String conditiondh= "";
		String conditionth= "";
		Utility util = new Utility();
		
		
		if (obj.getChinhanhId().length() > 0)
		{
			conditiondh += " and ddh.NPP_FK ='" + obj.getChinhanhId() + "'";
			conditionth += " and th.Npp_FK ='" + obj.getChinhanhId() + "'";
		}
		else
		{
			conditiondh += " and ddh.NPP_Dat_FK in " + util .quyen_npp(obj.getuserId()) + " and ddh.kbh_fk in  " + util.quyen_kenh(obj.getuserId());
			conditionth += " and th.NppTra_FK in " + util .quyen_npp(obj.getuserId()) + " and th.kbh_fk in  " + util.quyen_kenh(obj.getuserId());

		}
		
		if (obj.getnppId().length() > 0)
		{
			conditiondh += " and ddh.NPP_Dat_FK ='" + obj.getnppId() + "'";
			conditionth += " and th.NppTra_FK ='" + obj.getnppId() + "'";
		}
		if (obj.getKvId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk ='" + obj.getKvId() + "'";
			conditionth += " and npp.khuvuc_fk ='" + obj.getKvId() + "'";
			
		}
		if (obj.getVungId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk =" + obj.getVungId() + ")  ";
			conditionth += " and npp.khuvuc_fk  in (select pk_seq from khuvuc where vung_fk =" + obj.getVungId() + ")  ";
			
		}

		if(obj.getTrangthai().length()>0)
		{
			conditiondh += " and ddh.trangthai ='" + obj.getTrangthai() + "'";
			conditionth += " and th.trangthai ='" + obj.getTrangthai() + "'";
		}
		
		
		
		String query =
			 "\n 	with ddh as  " + 
			 "\n 	(  " + 
			 "\n 	  " + 
			 "\n 		SELECT distinct DDH.* , isnull(nh.pk_seq,0)nhId , nh.ngaynhan NgayNhanHang,dat.NgayDonHang NgayDatHang  " +
			 "\n		FROM   " + 
			 "\n 		ERP_DONDATHANGNPP DDH   " +
			 "\n		left join ERP_DonDatHang dat on dat.pk_Seq = DDH.From_DonDatHang " + 
			 "\n 		left join ERP_YCXUATKHONPP_DDH x on DDH.pk_seq = x.DDH_FK  " + 
			 "\n 		left join NhapHang nh on nh.ycxknpp_fk = x.ycxk_fk and nh.TRANGTHAI!=2   " + 
			 "\n 		WHERE DDH.NgayDonHang >='"+obj.gettungay()+"' AND DDH.NgayDonHang <= '"+obj.getdenngay()+"' and ddh.trangthai !=3  " + 
			 "\n 	)  " + 
			 "\n  	, th as    " + 
			 "\n  	(    " + 
			 "\n  		SELECT distinct th.* , 0 nhId,      th.ngaytra NgayNhanHang,     th.ngaytra NgayDatHang   " + 
			 "\n  		FROM     " + 
			 "\n  		Erp_HangTraLaiNpp th     " + 
			 "\n  		WHERE th.NGAYTRA >='"+obj.gettungay()+"' AND th.NGAYTRA <= '"+obj.getdenngay()+"'  and NppTra_FK is not null and th.trangthai !=3  	  " + 
			 "\n  	)   " + 		 			 
			 "\n 	SELECT  DDH.From_dondathang AS MaDatHang,ISNULL(DDH.pk_seq,'NA') AS MaDonHang, DDH.nhId MaNhanHang " + 
			 "\n 		,DDH.NgayDonHang,DDH.NgayDatHang,DDH.NgayNhanHang        " + 
			 "\n 	,CASE DDH.TRANGTHAI  WHEN '0' THEN N'Chưa chốt' WHEN '1' THEN N'Chờ duyệt'   WHEN '2' THEN N'Đã duyệt'   WHEN '3' THEN N'Đã Hủy'   WHEN '4' THEN N'Hoàn Tất'  END AS TRANGTHAI" +
			 "\n	,NPP.MA AS MANPP, NPP.TEN AS TENNPP, KV.TEN AS VUNG,V.TEN   AS MIEN ,SP.MA AS MASP,   " + 
			 "\n   " + 
			 "\n 		SP.TEN AS TENSP,DVDL.DONVI  " + 
			 "\n 		 ,ISNULL( (SELECT SUM(SOLUONG) AS THUCXUAT  FROM NHAPHANG_SP NHSP where NHSP.NHAPHANG_FK   =ddh.nhId AND SANPHAM_FK=SP.PK_SEQ  ) ,0) AS THUCXUAT  " + 
			 "\n 		 , ISNULL( DH_SP.SOLUONG,0) AS SLDAT,(DH_SP.DONGIA* ISNULL(DH_SP.SOLUONG,0)) AS TONGTIENDAT   " + 
			 "\n   " + 
			 "\n 		, case  DDH.isdhkhac when 0 then 'HB' else 'KM' end loaidonhang  " + 
			 "\n   " + 
			 "\n 	FROM DDH     " + 
			 "\n 	INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = DDH.NPP_Dat_FK   " + 
			 "\n 	LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=DDH.nguoisua   " + 
			 "\n 	LEFT JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK       " + 
			 "\n 	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK      " + 
			 "\n 	INNER JOIN ERP_DONDATHANGNPP_SANPHAM DH_SP ON DH_SP.DONDATHANG_FK = DDH.PK_SEQ   " + 
			 "\n 	INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=DH_SP.dvdl_fk   " + 
			 "\n 	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DH_SP.SANPHAM_FK   " + 
			 "\n 	WHERE npp.loainpp in (4) " + conditiondh +
			 
			 "\n 	union all  " + 
			 
			 "\n  	SELECT  0  MaDatHang, -1* th.pk_seq AS MaDonHang, th.nhId MaNhanHang   " + 
			 "\n  		,th.NGAYTRA AS NGAYDAT,th.NgayDatHang ,th.NgayNhanHang         " + 
			 "\n  	,CASE th.TRANGTHAI  WHEN '0' THEN N'Chưa chốt' WHEN '1' THEN N'Đã chốt'   WHEN '2' THEN N'Hoàn tất'   WHEN '3' THEN N'Đã Hủy '     END AS TRANGTHAI  " + 
			 "\n 	,NPP.MA AS MANPP, NPP.TEN AS TENNPP, KV.TEN AS VUNG,V.TEN   AS MIEN ,SP.MA AS MASP,     " + 
			 "\n      " + 
			 "\n  		SP.TEN AS TENSP,DVDL.DONVI    " + 
			 "\n  		 ,(-1)* ISNULL( DH_SP.SOLUONG,0) AS THUCXUAT    " + 
			 "\n  		 ,(-1)* ISNULL( DH_SP.SOLUONG,0) AS SLDAT,(-1)*(DH_SP.DONGIA* ISNULL(DH_SP.SOLUONG,0)) AS TONGTIENDAT     " + 
			 "\n      " + 
			 "\n  		, N'TH' loaidonhang    " + 
			 "\n      " + 
			 "\n  	FROM th       " + 
			 "\n  	INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = th.NppTra_FK      " + 
			 "\n  	LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=th.nguoisua     " + 
			 "\n  	LEFT JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK            " + 
			 "\n  	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK          " + 
			 "\n  	INNER JOIN Erp_HangTraLaiNpp_SanPham DH_SP ON DH_SP.HangTraLai_fk = th.PK_SEQ       " + 
			 "\n  	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DH_SP.SANPHAM_FK   " + 
			 "\n  	INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=SP.dvdl_fk       " + 
			 "\n  	WHERE npp.loainpp in (4) " + conditionth;
			 
	
		System.out.println("BC Don Dat Hang NPP : " + query);
		return query;
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
		Ireport obj = new Report();
		Utility util = new Utility();
		
		String view = "";
		view = util.antiSQLInspection(request.getParameter("view"));
		if (view == null)
			view = "";
		obj.setView(view);
		
		
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setuserId(userId);
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/BCDonDatHangNPP.jsp";
		response.sendRedirect(nextJSP);

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
		Ireport obj = new Report();
		Utility util = new Utility();

		
		String view = "";
		view = util.antiSQLInspection(request.getParameter("view"));
		if (view == null)
			view = "";
		obj.setView(view);
		
		
		String loaidonhang = "";
		loaidonhang = util.antiSQLInspection(request.getParameter("loaidonhang"));
		if (loaidonhang == null)
			loaidonhang = "0";
		obj.setLoaidonhang(loaidonhang);
		
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			userId = "";
		obj.setuserId(userId);

		String userTen = (String) session.getAttribute("userTen");
		obj.setuserTen(userTen);

		String nppId = "";
		nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		obj.setnppId(nppId);

		String vungId = request.getParameter("vungId");
		if (vungId == null)
			vungId = "";
		obj.setVungId(vungId);

		String khuvucId = request.getParameter("khuvucId");
		if (khuvucId == null)
			khuvucId = "";
		obj.setKvId(khuvucId);

		String tungay = util.antiSQLInspection(request.getParameter("Sdays"));
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("Edays"));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);

		obj.getNppInfo();

		String action = request.getParameter("action");
		if (action.equals("tao"))
		{

			request.setCharacterEncoding("utf-8");

			response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=BCDonDatHangNPP_" + util.setTieuDe(obj) + ".xlsm");

			OutputStream out = response.getOutputStream();

			String query = "";
			if(loaidonhang.equals("1"))
			{
				query = setQueryETC(request,obj);
				try
				{
					ExportToExcel(out, obj, query);
					return;
				} catch (Exception e)
				{
					obj.setMsg("Khong the tao bao cao " + e.getMessage());
				}
			}
			else
			{
				query = setQuery(request,obj);
				try
				{
					if (!CreatePivotTable(out, obj, query))
					{
						response.setContentType("text/html");
						PrintWriter writer = new PrintWriter(out);
						writer.print("Xin loi khong co bao cao trong thoi gian nay");
						writer.close();
					}
					return;
				} catch (Exception e)
				{
					obj.setMsg("Khong the tao bao cao " + e.getMessage());
				}	
			}
			
		}
			obj.init();
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/BCDonDatHangNPP.jsp";
			response.sendRedirect(nextJSP);
			return;
	}

	
	private void ExportToExcel(OutputStream out,Ireport obj,String query )throws Exception
	{
		try{ 			

			FileInputStream fstream = null;
			Workbook workbook = new Workbook();

			fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonHangETC.xlsm");
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			TaoBaoCao(workbook, obj, query);
			
			workbook.save(out);
			fstream.close();
						

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,Ireport obj,String query)throws Exception
	{
		dbutils db = new dbutils();
		try{

			

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo Đơn hàng ETC");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());

			
			
			ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			
			int location  = 200;
			int row = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				
				for(int i =1;i <=socottrongSql ; i ++)
				{
					
					cell = cells.getCell(row,location + i-1 );
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				
				++row;
			}
			if(rs!=null)rs.close();
			
			
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
		db.shutDown();
	}

	
	
	private boolean CreatePivotTable(OutputStream out, Ireport obj, String query) throws Exception
	{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonDatHangNPP.xlsm");
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		CreateHeader(workbook, obj);

		isFillData = FillData(workbook, query, obj);
		if (!isFillData)
		{
			if (fstream != null)
				fstream.close();
			return false;
		}
		workbook.save(out);
		fstream.close();
		return true;
	}

	private void CreateHeader(Workbook workbook, Ireport obj)
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();

		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo Đơn Đặt Hàng Nhà Phân Phối");
		cell = cells.getCell("A2");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());

		int column = 200;
		
		cell = cells.getCell(0,column++);cell.setValue("MaDonhang");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("MaDatHang");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("MaNhanHang");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("NgayDatHang");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("NgayDonHang");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("NgayNhanHang");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("TRANGTHAI");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("MANPP");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("TENNPP");ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell(0,column++);cell.setValue("VUNG");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("MIEN");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("MASP");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("TENSP");ReportAPI.setCellHeader(cell);
		

		cell = cells.getCell(0,column++);cell.setValue("DONVI");ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell(0,column++);cell.setValue("THUCXUAT");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("SLDAT");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("TONGTIENDAT");ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);cell.setValue("loaidonhang");ReportAPI.setCellHeader(cell);
	}

	private boolean FillData(Workbook workbook, String query, Ireport obj) throws Exception
	{
		ResultSet rs = null;
		dbutils db = new dbutils();

		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		for (int i = 0; i < 4; ++i)
		{
			cells.setColumnWidth(i, 15.0f);
		}
		rs = db.get(query);
		int index = 1;
		if (rs != null)
		{
			try
			{
				Cell cell = null;
				while (rs.next())
				{
					int column = 200;	
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("MaDonhang"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("MaDatHang"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("MaNhanHang"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("NgayDatHang"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("NgayDonHang"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("NgayNhanHang"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("TRANGTHAI"));
					
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("MANPP"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("TENNPP"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("VUNG"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("MIEN"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("MASP"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("TENSP"));

					cell = cells.getCell(index,column++);cell.setValue(rs.getString("DONVI"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getDouble("THUCXUAT"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getDouble("SLDAT"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getDouble("TONGTIENDAT"));
					cell = cells.getCell(index,column++);cell.setValue(rs.getString("loaidonhang"));		
		
					index++;
				}

				if (rs != null)
					rs.close();

				if(index ==1)
				{
					throw new Exception("Không có báo cáo với điều kiện đã chọn !");
				}
				if (db != null)
					db.shutDown();
			} catch (Exception ex)
			{
				throw new Exception(ex.getMessage());
			}
		} else
		{
			return false;
		}
		return true;
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
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

}
