package geso.dms.center.servlets.reports;

import geso.dms.center.beans.Router.IDRouter;
import geso.dms.center.beans.Router.imp.Router;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.OutputStream;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
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


public class Routereport extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	Utility util = new Utility();

	public Routereport()
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

		IDRouter obj = new Router();
		obj.setUserId(userId);
		obj.setStatus("1");
		obj.init();

		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/RouteReport.jsp";
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
		IDRouter obj = new Router();
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		
		String khuvucId = util.antiSQLInspection(request.getParameter("khuvucId"));
		if (khuvucId.trim().length() > 0)
			obj.setkhuvucId(khuvucId);

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

		String status = util.antiSQLInspection(request.getParameter("status"));
		obj.setStatus(status);
		
		String userId = (String) session.getAttribute("userId");
		
		obj.setUserId(userId);

		obj.init();
		String action = request.getParameter("action");

		if (action.equals("export"))
		{
			OutputStream out = response.getOutputStream();
			String userTen = (String) session.getAttribute("userTen");
			response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=RouteReport "+util.setTieuDe(obj)+".xlsm");
			
			CreatePivotTable(out, response, request, obj, userId, userTen);
			// userTen, nppId, tuyenId, ddkdId, kenhId, userId );
		} 
		else if (action.equals("exportmcp"))
		{
			XuatFileExcelSR(response, nppId, ddkdId,obj);
			session.setAttribute("obj", obj);
			if (obj.getMsg().length() > 0) {
				String nextJSP = request.getContextPath() + "/pages/Center/RouteReport.jsp";
				response.sendRedirect(nextJSP);
			}
		} 
		else if (action.equals("exportmcp2")){
			XuatFileMCP2(response, obj);
		}
		else
		{
			//obj.createNPP();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Center/RouteReport.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private void XuatFileMCP2(HttpServletResponse response, IDRouter obj) {
		String query = "SELECT p.maFast as MANPP, p.TEN as TENNPP, STUFF   \n "+      
					   "	 (     \n "+      
					   "		(	select distinct ' , ' + d.maFAST   \n "+      
					   "			from KHACHHANG_TUYENBH tbh inner join TUYENBANHANG t on t.PK_SEQ = tbh.TBH_FK   " +
					   "			INNER JOIN DAIDIENKINHDOANH d on d.PK_SEQ = t.DDKD_FK \n "+      
					   "			where tbh.KHACHHANG_FK = kh.PK_SEQ" +  
					   "			ORDER BY ' , ' + d.maFAST   \n "+      
					   "			FOR XML PATH('')   \n "+      
					   "		 ), 1, 2, ''   \n "+      
					   "	 ) as NVBH,   \n "+      
					   "kh.maFAST, kh.TEN, kh.CHUCUAHIEU, kh.DIACHI, isnull(kh.DiaChiGiaoHang,'') as DiaChiGiaoHang, l.DIENGIAI AS LOAI, h.HANG, n.TEN as NHOM, px.TEN AS phuongxa, qh.TEN AS QUANHUYEN, t.TEN AS TINHTHANH,   \n "+      
					   "tt.TEN as TTNT, kh.DIENTHOAI, kh.DiDong, kh.MaHD, kh.MASOTHUE, kh.songayno, kh.sotienno, k.TEN as KBH,   \n "+      
					   "STUFF   \n "+      
					   "	 (     \n "+      
					   "		(	select distinct ' , ' + cast(t.NGAYID as varchar(20))   \n "+      
					   "			from KHACHHANG_TUYENBH tbh inner join TUYENBANHANG t on t.PK_SEQ = tbh.TBH_FK   \n "+      
					   "			where tbh.KHACHHANG_FK = kh.PK_SEQ  " +       
					   "			ORDER BY ' , ' + cast(t.NGAYID as varchar(20))   \n "+      
					   "			FOR XML PATH('')   \n "+      
					   "		 ), 1, 2, ''   \n "+      
					   "	 ) as NGAYID,   \n "+      
					   " STUFF   \n "+
					   " (     \n "+      
					   "	(	select distinct ' , ' + cast(tbh.TANSO as varchar(20))   \n "+      
					   "		from KHACHHANG_TUYENBH tbh inner join TUYENBANHANG t on t.PK_SEQ = tbh.TBH_FK   \n "+      
					   "		where tbh.KHACHHANG_FK = kh.PK_SEQ " +     
					   "		ORDER BY ' , ' + cast(tbh.TANSO as varchar(20))   \n "+      
					   "		FOR XML PATH('')   \n "+      
					   "	 ), 1, 2, ''   \n "+      
					   " ) as TANSO,   \n "+      
					   "STUFF   \n "+      
					   "	 (     \n "+      
					   "		(	select ' , ' + p.TEN   \n "+      
					   "			from NHAPHANPHOI p   \n "+      
					   "			where p.PK_SEQ in (select NPP_FK from KHACHHANG_NPP where KHACHHANG_FK = kh.PK_SEQ) and p.PK_SEQ != kh.NPP_FK   \n "+      
					   "			ORDER BY ' , ' + p.TEN   \n "+      
					   "			FOR XML PATH('')   \n "+      
					   "		 ), 1, 2, ''   \n "+      
					   "	 ) as nppKhac, case when kh.LAT IS NOT NULL AND kh.LONG IS NOT NULL THEN N'Có' else '' end as TOADO,(select ten from diaban where pk_seq = kh.diaban_fk) as diaban \n "+      
					   "FROM KHACHHANG kh left join LOAICUAHANG l on l.PK_SEQ = kh.LCH_FK   \n " +
					   "inner join NHAPHANPHOI p on p.PK_SEQ = kh.NPP_FK "+      
					   "left join ThanhThiNongThon tt on tt.PK_SEQ = kh.thanhthinongthon_fk   \n "+      
					   "left join KENHBANHANG k on k.PK_SEQ = kh.KBH_FK   \n " +
					   "LEFT JOIN HANGCUAHANG h on kh.HCH_FK = h.PK_SEQ " +
					   "left join NHOMKHACHHANG_KHACHHANG nkh on nkh.KH_FK = kh.PK_SEQ left join NHOMKHACHHANG n on n.PK_SEQ = nkh.NKH_FK " +
					   "LEFT JOIN PhuongXa px on px.PK_SEQ = kh.PHUONGXA_FK " +
					   "LEFT JOIN QUANHUYEN qh on qh.PK_SEQ = kh.QUANHUYEN_FK " +
					   "LEFT JOIN TINHTHANH t on t.PK_SEQ = kh.TINHTHANH_FK " +
					   "LEFT JOIN KHACHHANG_TUYENBH tuyen on tuyen.khachhang_fk = kh.pk_seq " +
					   "where kh.TRANGTHAI = 1 "; //and tuyen.ngayid <> '8' 
		if (obj.getnppId().length() > 0)
			query += " and kh.NPP_FK = " + obj.getnppId();
		if (obj.getddkdId().length() > 0)
			query += " and kh.PK_SEQ in (SELECT tbh.KHACHHANG_FK FROM KHACHHANG_TUYENBH tbh inner join TUYENBANHANG t on t.PK_SEQ = tbh.TBH_FK  WHERE t.DDKD_FK = " + obj.getddkdId() + ")";

		if (obj.getkhuvucId().length() > 0)
			query += " and kh.PK_SEQ in (SELECT tbh.KHACHHANG_FK FROM KHACHHANG_TUYENBH tbh inner join TUYENBANHANG t on t.PK_SEQ = tbh.TBH_FK  WHERE t.DDKD_FK in (select pk_seq from DAIDIENKINHDOANH WHERE DIABAN_FK IN (SELECT PK_SEQ FROM DIABAN WHERE KHUVUC_FK = '" + obj.getkhuvucId() + "')) )";
		OutputStream out1 = null;
		try
		{
			String loi = "";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=XuatMCP.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());
			dbutils db = new dbutils();
			WritableSheet sheet = w.createSheet("KHACHANG", 0);

			sheet.addCell(new Label(0, 1, "DANH SÁCH KHÁCH HÀNG"));

			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			cellFont.setColour(Colour.BLACK);

			WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

			cellFormat.setBackground(jxl.format.Colour.GRAY_25);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			
			int cot = 0;
			sheet.addCell(new Label(cot++, 4, "STT", cellFormat));
			sheet.addCell(new Label(cot++, 4, "MÃ FAST NPP", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TÊN NPP", cellFormat));
			sheet.addCell(new Label(cot++, 4, "MÃ DDKD", cellFormat));
			sheet.addCell(new Label(cot++, 4, "MÃ FAST KH", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TÊN ĐƠN VỊ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "CHỦ CỬA HIỆU", cellFormat));
			sheet.addCell(new Label(cot++, 4, "ĐỊA CHỈ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "ĐỊA CHỈ GIAO HÀNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "LOẠI KHÁCH HÀNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "NHÓM KH", cellFormat));
			sheet.addCell(new Label(cot++, 4, "HẠNG KH", cellFormat));
			sheet.addCell(new Label(cot++, 4, "PHƯỜNG/XÃ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "QUẬN/HUYỆN", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TỈNH/THÀNH PHỐ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "THÀNH THỊ/NÔNG THÔN", cellFormat));
			sheet.addCell(new Label(cot++, 4, "SỐ ĐIỆN THOẠI", cellFormat));
			sheet.addCell(new Label(cot++, 4, "SDT DI ĐỘNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "SỐ HỢP ĐỒNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "MÃ SỐ THUẾ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "SỐ NGÀY NỢ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "SỐ TIỀN NỢ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "KÊNH BÁN HÀNG", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TUYẾN THỨ", cellFormat));
			sheet.addCell(new Label(cot++, 4, "TẦN SUẤT VIẾNG THĂM", cellFormat));
			sheet.addCell(new Label(cot++, 4, "NPP KHÁC", cellFormat));
			sheet.addCell(new Label(cot++, 4, "ĐỊA BÀN", cellFormat));
			
			ResultSet rs1 = db.get(query);
			Label label;
			int j = 5;
			// set style to cell data
			WritableCellFormat cellFormat2 = new WritableCellFormat();

			cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			while (rs1.next())
			{
				cot = 0;
				label = new Label(cot++, j, (j-4)+"", cellFormat2);

				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("MANPP"), cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("TENNPP"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("NVBH"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("maFAST"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("TEN"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("chucuahieu"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("diachi"), cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("diachigiaohang"), cellFormat2);
				sheet.addCell(label);
				
				
				label = new Label(cot++, j, rs1.getString("LOAI")  , cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("NHOM")  , cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("HANG")  , cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("phuongxa")  , cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("quanhuyen"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("tinhthanh"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("TTNT"), cellFormat2);
				sheet.addCell(label);

				
				label = new Label(cot++, j, rs1.getString("dienthoai"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("didong"), cellFormat2);
				sheet.addCell(label);


				label = new Label(cot++, j, rs1.getString("MaHD"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("MaSoThue"), cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("songayno"), cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("sotienno"), cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("KBH"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("ngayid"), cellFormat2);
				sheet.addCell(label);

				label = new Label(cot++, j, rs1.getString("tanso"), cellFormat2);
				sheet.addCell(label);
				
				label = new Label(cot++, j, rs1.getString("nppKhac"), cellFormat2);
				sheet.addCell(label);
				label = new Label(cot++, j, rs1.getString("diaban"), cellFormat2);
				sheet.addCell(label);
				//label = new Label(cot++, j, rs1.getString("TOADO"), cellFormat2);
				//sheet.addCell(label);
				
				j++;
			}
			w.write();
			w.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}


	private void CreatePivotTable(OutputStream out, HttpServletResponse response, HttpServletRequest request, IDRouter obj,
		String userId, String userTen)throws IOException
	{
		Workbook workbook = new Workbook();
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		
		String sql = "select pk_seq, ten, MaNhanVien  from daidienkinhdoanh where trangthai = 1 ";
		
		
		if (obj.getddkdId().length() > 0){
			sql = sql + " and pk_seq=" + obj.getddkdId();
		}
		if (obj.getkhuvucId().length() > 0)
			sql += " and diaban_fk in (select pk_seq from DIABAN WHERE KHUVUC_FK = '"+obj.getkhuvucId()+"')";
		
		System.out.println("lay ddkd : "+sql);
		dbutils db = new dbutils();
		ResultSet rs = db.get(sql);
		if (rs != null)
		{
			try
			{
				int i = 0;
				while (rs.next())
				{
					Worksheets worksheets = workbook.getWorksheets();

					Worksheet worksheet = worksheets.addSheet(rs.getString("pk_seq") + "-" + rs.getString("ten")+"-"+ rs.getString("MaNhanVien"));
					CreateTuyenDDKD(worksheet, obj, userId, userTen, rs.getString("pk_seq"), rs.getString("ten")+'-'+rs.getString("MaNhanVien"));
					// //System.out.println("Lan thu "+i);
					i++;
				}
				rs.close();
			} catch (Exception er)
			{

			}
		}
		db.shutDown();

		workbook.save(out);
	}

	private void CreateTuyenDDKD(Worksheet worksheet, IDRouter obj, String userId, String userTen, String ddkdid,
		String ddkdten)
	{
		CreateStaticHeader(worksheet, userTen, obj, ddkdid, ddkdten);
		CreateStaticData(worksheet, obj, userId, ddkdid, ddkdten);

	}

	private void CreateStaticHeader(Worksheet worksheet, String userTen, IDRouter obj, String ddkdid, String tenddkd)
	{

		Cells cells = worksheet.getCells();

		Style style;
		// cells.setColumnWidth(0, 200.0f);
		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		cell.setValue("Sales Rep Route Report - Channel: General Trade");
		style = cell.getStyle();

		Font font2 = new Font();
		font2.setColor(Color.RED);// mau chu
		font2.setSize(16);// size chu
		font2.setBold(true);

		style.setFont(font2);
		style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu
		cell.setStyle(style);

		cell = cells.getCell("A3");
		getCellStyle(worksheet, "A3", Color.BLUE, true, 10);
		cell.setValue("Reporting Date: " + this.getDateTime());

		cell = cells.getCell("A4");
		getCellStyle(worksheet, "A4", Color.BLUE, true, 10);
		cell.setValue("Created by:  " + userTen);

	}

	private void CreateStaticData(Worksheet worksheet, IDRouter obj, String userId, String ddkd, String ddkdten)
	{
		dbutils db = new dbutils();
		Cells cells = worksheet.getCells();
		String st = "";
		int dem = 0;
        st=st+"    ISPG=0 ";
		if (obj.getnppId().length() > 0)
		{
			st = st + " and tbh.npp_fk ='" + obj.getnppId() + "'";
		}

		if (obj.gettuyenId().length() > 0)
		{
			if (st.length() > 0)
				st = st + " and tbh.ngaylamviec ='" + obj.gettuyenId() + "' ";
			else
				st = " and tbh.ngaylamviec ='" + obj.gettuyenId() + "' ";
		}

		if (obj.getddkdId().length() > 0)
		{
			if (st.length() > 0)
				st = st + " and tbh.ddkd_fk ='" + obj.getddkdId() + "' ";
			else
				st = st + " and tbh.ddkd_fk ='" + obj.getddkdId() + "' ";
		}

		if (obj.getkenhId().length() > 0)
		{
			if (st.length() > 0)
				st = st + " and kh.kbh_fk ='" + obj.getkenhId() + "' ";
			else
				st = st + " and kh.kbh_fk ='" + obj.getkenhId() + "' ";
		}
		if (st.length() > 0)
			st = " where " + st;
		// khoi tao ket noi csdl
		String st1 = " 1=1 ";

		if (obj.getnppId() != "")
		{
			st1 = st1 + " and npp_fk = '" + obj.getnppId() + "' ";
		}
		if (obj.gettuyenId() != "")
		{

			st1 = st1 + " and ngaylamviec = '" + obj.gettuyenId() + "' ";
		}

		if (st1 == "")
		{
			st1 = " group by ngayid order by ngayid";
		} else
		{
			st1 = " where " + st1 + " group by ngayid order by ngayid";
		}
		String sql2 = "select ngayid  from tuyenbanhang " + st1;
		// System.out.println("Lay Du Lieu :"+sql2);
		ResultSet rs2 = db.get(sql2);
		int i = 5;

		if (rs2 != null)
		{
			try
			{
				while (rs2.next())
				{
					i = i + 2;

					Integer ngay = Integer.parseInt(rs2.getString("ngayid"));
					if (ngay == 2)
					{
						Cell cell = cells.getCell("A" + i);
						cell.setValue("Monday");
					} else if (ngay == 3)
					{
						Cell cell = cells.getCell("A" + i);
						cell.setValue("Tuesday");
					} else if (ngay == 4)
					{
						Cell cell = cells.getCell("A" + i);
						cell.setValue("Wednesday");
					} else if (ngay == 5)
					{
						Cell cell = cells.getCell("A" + i);
						cell.setValue("Thursday");
					} else if (ngay == 6)
					{
						Cell cell = cells.getCell("A" + i);
						cell.setValue("Friday");
					} else
					{
						Cell cell = cells.getCell("A" + i);
						cell.setValue("Saturday");
					}
					i = i + 1;
					tieude(worksheet, String.valueOf(i));

					geso.dms.center.util.Utility ut = new geso.dms.center.util.Utility();
		
					String sql = "select tbh.DIENGIAI as diengiai,v.ten as vung, kv.ten as khuvuc, npp.ten as npp, ddkd.ten as ddkd, tbh.ngaylamviec,kh.SmartId as Customer_Key,kh.ten as Customer_Name,kh.diachi as Address,qh.ten as province,case when ds.tonggiatri is null then 0 else ds.tonggiatri end as Average_Volume, lch.diengiai as Outlet_Type, " +
						"vt.vitri as Outlet_Location,hch.hang as Outlet_Class,kh_tuyen.tanso as Frequency " +
						"from khachhang kh " +
						"inner join nhaphanphoi npp on npp.pk_seq = kh.npp_fk " +
						"left join loaicuahang lch on lch.pk_seq = kh.lch_fk " +
						"left join vitricuahang vt on vt.pk_seq = kh.vtch_fk " +
						"left join hangcuahang hch on hch.pk_seq = kh.hch_fk " +
						"left join KHACHHANG_TUYENBH kh_tuyen on kh_tuyen.khachhang_fk = kh.pk_seq " +
						"left join (select khachhang_fk,cast(sum(tonggiatri)/3 as int) as tonggiatri from donhang where ngaynhap >'2011-10-01' and ngaynhap < '2011-12-31' group by khachhang_fk) as ds " +
						"on ds.khachhang_fk = kh.pk_seq " +
						"left join (select * from tuyenbanhang where ngayid = " +
						ngay +
						") tbh on tbh.pk_seq = kh_tuyen.tbh_fk " +
						"inner join daidienkinhdoanh ddkd on ddkd.pk_seq = tbh.ddkd_fk " +
						"inner join DIABAN db on db.PK_SEQ = ddkd.DIABAN_FK "+
						"inner join khuvuc kv on kv.pk_seq = db.khuvuc_fk " +
						"inner join vung v on v.pk_seq = kv.vung_fk " +
						"left join quanhuyen qh on kh.quanhuyen_fk = qh.pk_seq " +	
						st

						+ " and ddkd.pk_seq = " + ddkd + " and npp.pk_seq in " + ut.quyen_npp(userId) +// sua 
											// cho
						
						" order by v.ten, kv.ten, npp.ten ";

					System.out.println("Lay Du Lieu cuoi cung :" + sql);
					ResultSet rs = db.get(sql);
					i = i + 1;

					if (rs != null)
					{

						cells.setColumnWidth(0, 8.0f);
						cells.setColumnWidth(1, 10.0f);
						cells.setColumnWidth(2, 15.0f);
						cells.setColumnWidth(3, 15.0f);
						cells.setColumnWidth(4, 15.0f);
						cells.setColumnWidth(5, 15.0f);
						cells.setColumnWidth(6, 15.0f);
						cells.setColumnWidth(7, 15.0f);
						cells.setColumnWidth(8, 15.0f);
						cells.setColumnWidth(9, 15.0f);
						cells.setColumnWidth(10, 15.0f);
						cells.setColumnWidth(11, 20.0f);
						cells.setColumnWidth(12, 15.0f);
						cells.setColumnWidth(13, 15.0f);
						cells.setColumnWidth(14, 20.0f);
						cells.setColumnWidth(15, 20.0f);

						try
						{

							Cell cell = null;
							int stt = 1;
							while (rs.next())// lap den cuoi bang du lieu
							{

								// lay tu co so du lieu, gan bien

								String Stt = String.valueOf(stt);
								String Region = rs.getString("vung");
								String Area = rs.getString("khuvuc");
								String Distributor = rs.getString("npp");
								String SalesRep = rs.getString("ddkd");
								String Thu = rs.getString("ngaylamviec");
								String CustomerCode = rs.getString("Customer_Key");
								String CustomerName = rs.getString("Customer_Name");
								String Address = rs.getString("Address");
								String Town = rs.getString("province");
								String AverageVolume = rs.getString("Average_Volume");
								String OutletType = rs.getString("Outlet_Type");
								String OutletLocation = rs.getString("Outlet_Location");
								String OutletClass = rs.getString("Outlet_Class");
								String Frequency = rs.getString("Frequency");
								String diengiai = rs.getString("diengiai");

								// cell = cells.getCell("AA" +
								// Integer.toString(i)); cell.setValue(Channel);
								cell = cells.getCell("A" + Integer.toString(i));
								cell.setValue(Stt);
								CreateBorderSetting(worksheet, "A" + Integer.toString(i));
								cell = cells.getCell("B" + Integer.toString(i));
								cell.setValue(Region);
								CreateBorderSetting(worksheet, "B" + Integer.toString(i));
								cell = cells.getCell("C" + Integer.toString(i));
								cell.setValue(Area);
								CreateBorderSetting(worksheet, "C" + Integer.toString(i));
								cell = cells.getCell("D" + Integer.toString(i));
								cell.setValue(Distributor);
								CreateBorderSetting(worksheet, "D" + Integer.toString(i));
								cell = cells.getCell("E" + Integer.toString(i));
								cell.setValue(SalesRep);
								CreateBorderSetting(worksheet, "E" + Integer.toString(i));
								// cell = cells.getCell("F" +
								// Integer.toString(i)); cell.setValue(Thu);
								// CreateBorderSetting(worksheet,"F" +
								// Integer.toString(i));
								cell = cells.getCell("F" + Integer.toString(i));
								cell.setValue(CustomerCode);
								CreateBorderSetting(worksheet, "F" + Integer.toString(i));
								cell = cells.getCell("G" + Integer.toString(i));
								cell.setValue(CustomerName);
								CreateBorderSetting(worksheet, "G" + Integer.toString(i));
								cell = cells.getCell("H" + Integer.toString(i));
								cell.setValue(Address);
								CreateBorderSetting(worksheet, "H" + Integer.toString(i));
								cell = cells.getCell("I" + Integer.toString(i));
								cell.setValue(Town);
								CreateBorderSetting(worksheet, "I" + Integer.toString(i));
								cell = cells.getCell("J" + Integer.toString(i));
								cell.setValue(Float.parseFloat(AverageVolume));
								CreateBorderSetting(worksheet, "J" + Integer.toString(i));
								cell = cells.getCell("K" + Integer.toString(i));
								cell.setValue(OutletType);
								CreateBorderSetting(worksheet, "K" + Integer.toString(i));
								cell = cells.getCell("L" + Integer.toString(i));
								cell.setValue(OutletLocation);
								CreateBorderSetting(worksheet, "L" + Integer.toString(i));
								cell = cells.getCell("M" + Integer.toString(i));
								cell.setValue(OutletClass);
								CreateBorderSetting(worksheet, "M" + Integer.toString(i));
								cell = cells.getCell("N" + Integer.toString(i));
								cell.setValue(Frequency);
								CreateBorderSetting(worksheet, "N" + Integer.toString(i));
								cell = cells.getCell("O" + Integer.toString(i));
								cell.setValue(diengiai);
								CreateBorderSetting(worksheet, "O" + Integer.toString(i));

								i++;
								stt++;
							}// end while

						}// end try
						catch (Exception e)
						{
						} finally
						{
							if (rs != null)
								rs.close();
							/*
							 * if (db!=null){ db.shutDown(); }
							 */
						}
					}// end if
				}// end while
			} catch (Exception e)
			{
				// System.out.println("Errr" + e.toString());
			}// end try

			finally
			{
				if (rs2 != null)
					try
					{
						rs2.close();
					} catch (Exception e)
					{
						// System.out.println("Errr" + e.toString());
						e.printStackTrace();
					}
			}
			if (db != null)
			{
				db.shutDown();
			}
		}// end if

	}

	private void getCellStyle(Worksheet worksheet, String a, Color mau, Boolean dam, int size)
	{

		Cells cells = worksheet.getCells();
		Style style;
		Cell cell = cells.getCell(a);
		style = cell.getStyle();
		Font font1 = new Font();
		font1.setColor(mau);
		font1.setBold(dam);
		font1.setSize(size);
		style.setFont(font1);
		cell.setStyle(style);
	}

	
	private void tieude(Worksheet worksheet, String a) throws IOException
	{

		Cells cells = worksheet.getCells();
		Cell cell = cells.getCell("A" + a);
		cell.setValue("STT");
		CreateBorderSetting(worksheet, "A" + a);
		getCellStyle(worksheet, "A" + a, Color.BLUE, true, 10);
		cell = cells.getCell("B" + a);
		cell.setValue("Khu Vực");
		CreateBorderSetting(worksheet, "B" + a);
		getCellStyle(worksheet, "B" + a, Color.BLUE, true, 10);
		cell = cells.getCell("C" + a);
		cell.setValue("Khu vực");
		CreateBorderSetting(worksheet, "C" + a);
		getCellStyle(worksheet, "C" + a, Color.BLUE, true, 10);
		cell = cells.getCell("D" + a);
		cell.setValue("Chi nhánh/Đối tác");
		CreateBorderSetting(worksheet, "D" + a);
		getCellStyle(worksheet, "D" + a, Color.BLUE, true, 10);
		cell = cells.getCell("E" + a);
		cell.setValue("NHÂN VIÊN BÁN HÀNG");
		CreateBorderSetting(worksheet, "E" + a);
		getCellStyle(worksheet, "E" + a, Color.BLUE, true, 10);
		// cell = cells.getCell("F" + a); cell.setValue("Thu");
		// CreateBorderSetting(worksheet,"F"+a);
		// getCellStyle(worksheet,"F"+a,Color.BLUE,true,10);
		cell = cells.getCell("F" + a);
		cell.setValue("Mã khách hàng");
		CreateBorderSetting(worksheet, "F" + a);
		getCellStyle(worksheet, "F" + a, Color.BLUE, true, 10);
		cell = cells.getCell("G" + a);
		cell.setValue("Tên khách hàng");
		CreateBorderSetting(worksheet, "G" + a);
		getCellStyle(worksheet, "G" + a, Color.BLUE, true, 10);
		cell = cells.getCell("H" + a);
		cell.setValue("Địa chỉ");
		CreateBorderSetting(worksheet, "H" + a);
		getCellStyle(worksheet, "H" + a, Color.BLUE, true, 10);
		cell = cells.getCell("I" + a);
		cell.setValue("Quận");
		CreateBorderSetting(worksheet, "I" + a);
		getCellStyle(worksheet, "I" + a, Color.BLUE, true, 10);
		cell = cells.getCell("J" + a);
		cell.setValue("Doanh số trung bình");
		CreateBorderSetting(worksheet, "J" + a);
		getCellStyle(worksheet, "J" + a, Color.BLUE, true, 10);
		cell = cells.getCell("K" + a);
		cell.setValue("Loại cửa hàng");
		CreateBorderSetting(worksheet, "K" + a);
		getCellStyle(worksheet, "K" + a, Color.BLUE, true, 10);
		cell = cells.getCell("L" + a);
		cell.setValue("Vị trí của hàng");
		CreateBorderSetting(worksheet, "L" + a);
		getCellStyle(worksheet, "L" + a, Color.BLUE, true, 10);
		cell = cells.getCell("M" + a);
		cell.setValue("Hạng cửa hàng");
		CreateBorderSetting(worksheet, "M" + a);
		getCellStyle(worksheet, "M" + a, Color.BLUE, true, 10);
		cell = cells.getCell("N" + a);
		cell.setValue("Tần số ");
		CreateBorderSetting(worksheet, "N" + a);
		getCellStyle(worksheet, "N" + a, Color.BLUE, true, 10);
		cell = cells.getCell("O" + a);
		cell.setValue("Diễn giải TBH");
		CreateBorderSetting(worksheet, "O" + a);
		getCellStyle(worksheet, "O" + a, Color.BLUE, true, 10);
	}

	public void CreateBorderSetting(Worksheet worksheet, String fileName) throws IOException
	{
		Cells cells = worksheet.getCells();
		Cell cell;
		Style style;

		cell = cells.getCell(fileName);
		style = cell.getStyle();

		// Set border color
		style.setBorderColor(BorderType.TOP, Color.BLACK);
		style.setBorderColor(BorderType.BOTTOM, Color.BLACK);
		style.setBorderColor(BorderType.LEFT, Color.BLACK);
		style.setBorderColor(BorderType.RIGHT, Color.BLACK);
		// style.setBorderColor(BorderType.DIAGONAL_DOWN, Color.BLUE);
		// style.setBorderColor(BorderType.DIAGONAL_UP, Color.BLUE);

		// Set the cell border type
		style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
		style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
		style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
		style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN);
		// style.setBorderLine(BorderType.DIAGONAL_DOWN, BorderLineType.DASHED);
		// style.setBorderLine(BorderType.DIAGONAL_UP, BorderLineType.DASHED);

		cell.setStyle(style);

	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void XuatFileExcelSR(HttpServletResponse response, String NppId, String DdkdId,IDRouter obj) throws IOException
	{
		OutputStream out1 = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=XuatMCP "+util.setTieuDe(obj)+".xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();

			dbutils db = new dbutils();
			String sql = "\n select " +
			"\n STUFF((	" +
			"\n     select ' , ' + p.TEN " +      
			"\n     from NHAPHANPHOI p " +      
			"\n     where p.PK_SEQ in (select NPP_FK from DAIDIENKINHDOANH_NPP where DDKD_FK = ddkd.PK_SEQ " +(NppId.length() > 0 ? " and NPP_FK = " + NppId : "") +") " +      
			"\n     ORDER BY ' , ' + p.TEN " +      
			"\n     FOR XML PATH('') " +      
			"\n     ), 1, 2, '' " +      
			"\n ) as tennpp, kv.ten as tenkv, ddkd.pk_seq as ddkdid, ddkd.ten as ddkdten, ddkd.MaNhanVien " +
			"\n FROM daidienkinhdoanh ddkd left join DIABAN db on db.PK_SEQ = ddkd.DIABAN_FK " +
			"\n left join khuvuc kv  on kv.pk_Seq = db.khuvuc_fk " +
			"\n where ddkd.TRANGTHAI = 1 ";

			if (!DdkdId.equals(""))
				sql = sql + " and ddkd.pk_seq=" + DdkdId;
			else
				if (NppId.length() > 0)
					sql = sql + " and ddkd.pk_seq in (select ddkd_fk from DAIDIENKINHDOANH_NPP where npp_fk = "+NppId+" ) ";
				else
					if (obj.getkhuvucId().length() > 0)
						sql += " and kv.PK_SEQ = " + obj.getkhuvucId();

			sql += "\n and ddkd.pk_seq in "+util.Quyen_Ddkd(obj.getUserId())+" " ;

			System.out.println("danh sach NVBH :" + sql);
			ResultSet rs = db.get(sql);
			int k = 0;
			boolean chkRs = false;
			while (rs.next())
			{
				chkRs = true;
				WritableSheet sheet = w.createSheet(rs.getString("ddkdid"), k);

				sheet.addCell(new Label(0, 1, "NPP : "));
				sheet.addCell(new Label(1, 1, "" + rs.getString("tennpp")));

				sheet.addCell(new Label(0, 2, "KHU VỰC : "));
				sheet.addCell(new Label(1, 2, "" + rs.getString("tenkv")));

				sheet.addCell(new Label(0, 3, "NVBH : "));
				sheet.addCell(new Label(1, 3, "" + rs.getString("ddkdten") +" - " + rs.getString("MaNhanVien")   ));

				WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
				cellFont.setColour(Colour.BLACK);

				WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

				cellFormat.setBackground(jxl.format.Colour.GRAY_25);
				cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

				int cot = 0;
				sheet.addCell(new Label(cot++, 4, "SỐ TT", cellFormat));
				sheet.addCell(new Label(cot++, 4, "MÃ CHI NHÁNH", cellFormat));
				sheet.addCell(new Label(cot++, 4, "MÃ HỆ THỐNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "MÃ KH SAP", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TÊN ĐƠN VỊ (dùng xuất HĐTC)", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TÊN CỬA HIỆU", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TÊN NGƯỜI MUA", cellFormat));
				//sheet.addCell(new Label(cot++, 4, "SỐ NHÀ/ĐƯỜNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "ĐỊA CHỈ GIAO HÀNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "CHIẾT KHẤU", cellFormat));
				sheet.addCell(new Label(cot++, 4, "LOẠI KHÁCH HÀNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "NHÓM KH", cellFormat));
				sheet.addCell(new Label(cot++, 4, "HẠNG KH", cellFormat));
				sheet.addCell(new Label(cot++, 4, "VỊ TRÍ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "SỐ NHÀ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TÊN ĐƯỜNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "ẤP/KHÓM/TỔ/KHU DÂN CƯ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "PHƯỜNG/XÃ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "QUẬN/HUYỆN", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TỈNH/THÀNH PHỐ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "THÀNH THỊ/NÔNG THÔN", cellFormat));
				sheet.addCell(new Label(cot++, 4, "SỐ ĐIỆN THOẠI", cellFormat));
				sheet.addCell(new Label(cot++, 4, "DI ĐỘNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "NGÀY SINH", cellFormat));
				sheet.addCell(new Label(cot++, 4, "SỐ HỢP ĐỒNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "MÃ SỐ THUẾ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "SỐ NGÀY NỢ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "SỐ TIỀN NỢ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "KÊNH BÁN HÀNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "KHO", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TUYẾN THỨ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TẦN SUẤT VIẾNG THĂM", cellFormat));
				sheet.addCell(new Label(cot++, 4, "KINH ĐỘ (LONG)", cellFormat));
				sheet.addCell(new Label(cot++, 4, "VĨ ĐỘ (LAT)", cellFormat));
				sheet.addCell(new Label(cot++, 4, "VỢ/CHỒNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "NGÀY SINH VỢ/CHỒNG", cellFormat));
				sheet.addCell(new Label(cot++, 4, "CON 1", cellFormat));
				sheet.addCell(new Label(cot++, 4, "NGÀY SINH CON 1", cellFormat));
				sheet.addCell(new Label(cot++, 4, "CON 2", cellFormat));
				sheet.addCell(new Label(cot++, 4, "NGÀY SINH CON 2", cellFormat));
				sheet.addCell(new Label(cot++, 4, "GHI CHÚ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TÀI TRỢ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "NGÀY TÀI TRỢ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "MÃ NPP KHÁC", cellFormat));
				
				sheet.addCell(new Label(cot++, 4, "PHƯỜNG XÃ", cellFormat));
				sheet.addCell(new Label(cot++, 4, "QUẬN HUYỆN", cellFormat));
				sheet.addCell(new Label(cot++, 4, "TỈNH THÀNH", cellFormat));

				sql =  "\n with tuyen as ( " +
				"\n select a.khachhang_fk,b.ngayId, max(sott)sott,max(tanso)tanso " + 
				"\n from khachhang_tuyenbh a inner join tuyenbanhang b on a.tbh_fk= b.pk_Seq " +
				"\n where b.ddkd_fk = " + rs.getString("ddkdid") + 
				"\n group by a.khachhang_fk, b.ngayId " + 
				"\n ) " +
				"\n select kh.pk_seq MaHeThong, isnull(kh.SoNha,kh.DiaChi) SoNha, " +
				"\n kh.phuongxa, " +
				"\n (select diengiai from thanhthinongthon where pk_Seq = kh.thanhthinongthon_fk) as thanhthinongthon_fk, " +
				"\n kh.didong,kh.songayno,kh.sotienno, " +
				"\n STUFF " +      
				"\n ((	" +
				"\n     select ';' + p.MaFAST " +      
				"\n     from NHAPHANPHOI p " +      
				"\n     where p.PK_SEQ in (select NPP_FK from KHACHHANG_NPP where KHACHHANG_FK = kh.PK_SEQ  and NPP_FK != kh.NPP_FK) " +      
				"\n     ORDER BY ';' + p.MaFAST " +      
				"\n     FOR XML PATH('') " +      
				"\n     ), 1, 1, '' " +      
				"\n ) as nppKhac, " +
				"\n npp.MaFAST AS MANPP, kh.MST_CaNhan, kh.TenKyHD, vt.vitri, hch.hang as hangcuahang, lch.loai as loaicuahang, " +
				"\n kh.phuongXa as Phuong, 'NA' as nhomcuahang, kh.tenduong, kh.apto, kh.didong, kh.masothue, kh.songayno, " +
				"\n kh.sotienno, k.ten as kho, kh.long, kh.lat, kh.vochong, kh.Ngsinh_VoChong, kh.con1, kh.con2, kh.Ngsinh_Con1, " +
				"\n kh.Ngsinh_Con2, kh.taitro, kh.ngaytaitro, kh.ghichu, kh.chucuahieu, kh.tinhthanh_fk, " +
				"\n kh.quanhuyen_fk, kh.maFAST, kh.MaCu, isnull(kh.nguoimuahang,'NA') nguoimuahang, kh.diachigiaohang, " +
				"\n kh.PT_CHIETKHAU as chietkhau, kh.ten as tenkh, kh.tencuahieu, kh.smartid, kh.diachi, kh.dienthoai, tbh.tanso, " +
				"\n tbh.sott, kh.NgaySinh, kh.MaHD, kh.MaSoThue, kh.XuatKhau, KH.THANHTOAN, kbh.Ten as KenhBanHang, kh.ThanhToanQuy, " +
				"\n STUFF " +
				"\n (( " +
				"\n     select ';' + cast( p.ngayId as varchar) " +      
				"\n     from tuyen p " +      
				"\n     where p.khachhang_fk = kh.pk_seq " +      
				"\n     ORDER BY ';' + cast( p.ngayId as varchar) " +      
				"\n     FOR XML PATH('') " +      
				"\n     ), 1, 1, '' " +      
				"\n ) as ngayid ,  qh.ten qhTen, px.ten pxTen, tt.ten ttTen " +
				"\n from khachhang kh " +
				"\n inner join " +
				"\n ( " +
				"\n     select khachhang_fk, max(sott)sott,max(tanso)tanso " +
				"\n     from tuyen " +
				"\n     group by khachhang_fk " +
				"\n )tbh on kh.pk_seq = tbh.khachhang_fk " +
				"\n inner join NHAPHANPHOI npp on npp.PK_SEQ = kh.NPP_FK " +
				"\n left join kenhbanhang kbh on kbh.pk_Seq = kh.kbh_fk " +
				"\n left join nhomkhachhang_khachhang nkhkh on kh.pk_seq = nkhkh.kh_fk " +
				"\n left join nhomkhachhang nkh on nkh.pk_seq = nkhkh.nkh_fk "+
				"\n left join hangcuahang hch on hch.pk_seq = kh.hch_fk " +
				"\n left join loaicuahang lch on lch.pk_seq = kh.lch_fk " +
				"\n left join vitricuahang vt on vt.pk_seq = kh.vtch_fk " + 
				"\n left join tinhthanh tt on tt.pk_seq = kh.tinhthanh_fk " +
				"\n left join kho k on kh.kho_fk = k.pk_seq " +
				"\n left join quanhuyen qh on qh.pk_seq = kh.quanhuyen_fk and kh.tinhthanh_fk = qh.tinhthanh_fk" +
				"\n left join phuongxa px on px.pk_seq = kh.phuongxa " +
				"\n where kh.trangthai = '1' ";

				if (NppId.length() > 0)
					sql += " and kh.PK_SEQ IN ( SELECT KHACHHANG_FK FROM KHACHHANG_NPP WHERE NPP_FK = " + NppId + ")";

				sql += " order by ngayid, tbh.sott ";
				System.out.println("with query: "+sql);

				ResultSet rs1 = db.get(sql);
				Label label;

				Number number;
				int j = 5;
				// set style to cell data
				WritableCellFormat cellFormat2 = new WritableCellFormat();

				cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

				while (rs1.next())
				{
					cot = 0;
					label = new Label(cot++, j, rs1.getString("sott"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("MANPP"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("MaHeThong"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("maFAST"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("tenkh"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("tencuahieu"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("nguoimuahang"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("diachigiaohang"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("chietkhau"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("loaicuahang"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("nhomcuahang"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("hangcuahang"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("vitri"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("SoNha"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("tenduong"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("apto"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("phuongxa"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("quanhuyen_fk"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("tinhthanh_fk"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("thanhthinongthon_fk"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("dienthoai"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("didong"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("NgaySinh"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("MaHD"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("MaSoThue"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("songayno"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("sotienno"), cellFormat2);
					sheet.addCell(label);				

					label = new Label(cot++, j, rs1.getString("kenhbanhang"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("kho"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("ngayid"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("tanso"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("long"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("lat"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("vochong"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("ngsinh_vochong"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("con1"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("ngsinh_con1"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("con2"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("ngsinh_con2"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("ghichu"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("taitro"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("ngaytaitro"), cellFormat2);
					sheet.addCell(label);

					label = new Label(cot++, j, rs1.getString("nppKhac"), cellFormat2);
					sheet.addCell(label);
					
					label = new Label(cot++, j, rs1.getString("pxTen"), cellFormat2);
					sheet.addCell(label);
					label = new Label(cot++, j, rs1.getString("qhTen"), cellFormat2);
					sheet.addCell(label);
					label = new Label(cot++, j, rs1.getString("ttTen"), cellFormat2);
					sheet.addCell(label);

					j++;
				}

				k++;
			}

			if (!chkRs)
			{
				obj.setMsg("Không có dữ liệu.");
			}

			w.write();
			w.close();
		} 
		catch (Exception e)
		{
			System.out.println("Lỗi ngoại lệ: "+e.getMessage());
			e.printStackTrace();
		} 
		
		finally
		{
			if (out1 != null)
				out1.close();
		}
	}
}
