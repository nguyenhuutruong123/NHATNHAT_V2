package geso.dms.center.servlets.chitieu;

import geso.dms.center.beans.chitieu.IChiTieu;
import geso.dms.center.beans.chitieu.imp.ChiTieu;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ChiTieuPriSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ChiTieuPriSvl()
	{
		super();
	}

	private String gettenuser(String userId_)
	{
		dbutils db = new dbutils();
		String sql_getnam = "select ten from nhanvien where pk_seq=" + userId_;
		ResultSet rs_tenuser = db.get(sql_getnam);
		if (rs_tenuser != null)
		{
			try
			{
				while (rs_tenuser.next())
				{
					return rs_tenuser.getString("ten");
				}
			} catch (Exception er)
			{
				return "";
			}
		}
		return "";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Utility util = new Utility();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IChiTieu obj = new ChiTieu();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setUserId(userId);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		session.setAttribute("userId", userId);
		session.setAttribute("userTen", gettenuser(userId));
		String action = util.getAction(querystring);
		// out.println(action);
		String id = util.getId(querystring);
		obj.setID(id);
		String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPri.jsp";// default
		obj.setLoaict("0");
		obj.Init();
		String loaict = "0";
		if (action.equals("delete"))
		{
			obj.DeleteChitieu_Pri();
			obj.setListChiTieu("", loaict);
			session.setAttribute("obj", obj);
		} else if (action.equals("chot"))
		{

			obj.ChotChiTieu_Pri();
			obj.setListChiTieu("", loaict);
			session.setAttribute("obj", obj);
		} else if (action.equals("new"))
		{
			nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNew.jsp";
		} else if (action.equals("unchot"))
		{
			obj.setID(id);
			obj.UnChotChiTieu_Pri();
			obj.setListChiTieu("", loaict);
			session.setAttribute("obj", obj);
		} else if (action.equals("excel"))
		{
			XuatFileExcel(response, id);
		} else
		{
			obj.setListChiTieu("", loaict);
			session.setAttribute("obj", obj);
		}
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utility util = new Utility();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String username = util.antiSQLInspection(request.getParameter("userTen"));

		String thangtk = util.antiSQLInspection(request.getParameter("thang"));
		String namtk = util.antiSQLInspection(request.getParameter("nam"));
		String action = request.getParameter("action");
		dbutils db = new dbutils();
		ChiTieu chitieu = new ChiTieu();
		chitieu.setNam(Integer.parseInt(thangtk));
		chitieu.setThang(Integer.parseInt(thangtk));

		chitieu.setUserId(userId);
		HttpSession session = request.getSession();
		String loaict = (String) util.antiSQLInspection(request.getParameter("loaichitieu"));

		String dvkdid = util.antiSQLInspection(request.getParameter("selectdvkd"));
		chitieu.setDVKDID(dvkdid);
		chitieu.Init();

		Utility Ult = new Utility();
		if (action.equals("search"))
		{
			String sql_getdata = "";
			sql_getdata = "SELECT      kbh.ten as kenhbanhang, c.kenh_fk,c.trangthai,C.PK_SEQ, C.THANG, C.NAM, C.CHITIEU,C.DIENGIAI, C.NGAYKETTHUC,C.DVKD_FK,DONVIKINHDOANH,C.SONGAYLAMVIEC, C.NGAYTAO, C.NGAYSUA,NT.TEN AS NGUOITAO, "
					+ "NS.TEN AS NGUOISUA FROM dbo.CHITIEU AS C inner join donvikinhdoanh as d on d.pk_seq=c.DVKD_FK  INNER JOIN "
					+ "dbo.NHANVIEN AS NT ON C.NGUOITAO = NT.PK_SEQ  "
					+ " INNER JOIN dbo.NHANVIEN AS NS ON C.NGUOISUA = NS.PK_SEQ  "
					+ " inner join kenhbanhang kbh on kbh.pk_seq=c.kenh_fk "
					+ "where 1=1 "
					+ " and kenh_fk in "
					+ Ult.quyen_kenh(userId);

			String where = "";
			if (!thangtk.equals("0"))
			{
				where = " and C.THANG=" + thangtk + " ";
			}
			if (!namtk.equals("0"))
			{
				where = where + "and C.NAM=" + namtk + " ";
			}

			if (!dvkdid.equals(""))
			{
				where = where + "and C.DVKD_FK=" + dvkdid;
			}
			if (where != "")
			{// if have search condition
				sql_getdata = sql_getdata + where;
			}
			// System.out.println("CAU LENH TIM KIEM :" + sql_getdata);
			chitieu.setListChiTieu(sql_getdata, loaict);

			session.setAttribute("obj", chitieu);
			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPri.jsp";// default
			response.sendRedirect(nextJSP);
		} else if (action.equals("new"))
		{

			IChiTieu obj = new ChiTieu();
			obj.setUserId(userId);
			obj.Init();

			session.setAttribute("userId", userId);
			session.setAttribute("userTen", username);
			session.setAttribute("obj", chitieu);

			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriNew.jsp";
			response.sendRedirect(nextJSP);

		}
		if (action.equals("excel"))
		{
			String sql_getdata = "";
			sql_getdata = "SELECT      kbh.ten as kenhbanhang, c.kenh_fk,c.trangthai,C.PK_SEQ, C.THANG, C.NAM, C.CHITIEU,C.DIENGIAI, C.NGAYKETTHUC,C.DVKD_FK,DONVIKINHDOANH,C.SONGAYLAMVIEC, C.NGAYTAO, C.NGAYSUA,NT.TEN AS NGUOITAO, "+
				    " NS.TEN AS NGUOISUA ,ISNULL(CONVERT(varchar(23),C.DATEDUYET, 121), '00:00:00') AS DATEDUYET  ,ISNULL(CONVERT(varchar(23),C.DATEDUYET, 121), '00:00:00') AS DATEHUYDUYET,ISNULL(ND.TEN,'') AS NGUOIDUYET,NH.TEN AS NGUOIHUYDUYET  " +
				    " FROM dbo.CHITIEU AS C INNER JOIN "+ 
				    " dbo.NHANVIEN AS NT ON C.NGUOITAO = NT.PK_SEQ  "+ 
				    " INNER JOIN dbo.NHANVIEN AS NS ON C.NGUOISUA = NS.PK_SEQ  "+
				    " LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=C.NGUOIDUYET" +
		            " LEFT JOIN NHANVIEN NH ON NH.PK_SEQ=C.NGUOIHUY     " +
				    " inner join kenhbanhang kbh on kbh.pk_seq=c.kenh_fk "+
				    " inner join DONVIKINHDOANH D on D.pk_seq=C.DVKD_FK " +
				    "where 1=1 "+ " and kenh_fk in "+ Ult.quyen_kenh(userId);
			String where = "";
			if (!thangtk.equals("0"))
			{
				where = " and C.THANG=" + thangtk + " ";
			}
			if (!namtk.equals("0"))
			{
				where = where + "and C.NAM=" + namtk + " ";
			}

			if (!dvkdid.equals(""))
			{
				where = where + "and C.DVKD_FK=" + dvkdid;
			}
			if (where != "")
			{
				sql_getdata = sql_getdata + where;
			}
			ToExcel(response, sql_getdata);
		}

	}

	private void ToExcel(HttpServletResponse response, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ChiTieuMuaVao.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("ChiTieuMuaVao", k);
			sheet.addCell(new Label(0, 1, "Chỉ tiêu Mua Vào : ", new WritableCellFormat(cellTitle)));

			sheet.addCell(new Label(0, 2, "Ngày tạo: "));
			sheet.addCell(new Label(1, 2, "" + getDateTime()));

			sheet.addCell(new Label(2, 4, "Đơn vị tiền tệ:VND"));

			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			cellFont.setColour(Colour.BLACK);

			WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

			cellFormat.setBackground(jxl.format.Colour.LIME);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormatSpecical = new WritableCellFormat(cellFont);

			cellFormatSpecical.setBackground(jxl.format.Colour.LIGHT_ORANGE);
			cellFormatSpecical.setWrap(true);
			cellFormatSpecical.setAlignment(Alignment.CENTRE);
			cellFormatSpecical.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormatSpecical.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			sheet.addCell(new Label(0, 4, "STT", cellFormat));
			sheet.addCell(new Label(1, 4, "THÁNG", cellFormatSpecical));
			sheet.addCell(new Label(2, 4, "NĂM", cellFormat));
			sheet.addCell(new Label(3, 4, "ĐVKD ", cellFormat));
			sheet.addCell(new Label(4, 4, "KÊNH ", cellFormat));
			sheet.addCell(new Label(5, 4, "CHỈ TIÊU ", cellFormat));
			sheet.addCell(new Label(6, 4, "TRẠNG THÁI ", cellFormat));
			sheet.addCell(new Label(7, 4, "SỐ NGÀY LÀM VIỆC ", cellFormat));
			sheet.addCell(new Label(8, 4, "NGÀY KẾT THÚC ", cellFormat));
			sheet.addCell(new Label(9, 4, "NGÀY TẠO", cellFormat));
			sheet.addCell(new Label(10, 4, "NGƯỜI TẠO", cellFormat));
			sheet.addCell(new Label(11, 4, "NGÀY SỬA", cellFormat));
			sheet.addCell(new Label(12, 4, "NGƯỜI SỬA", cellFormat));
			
			sheet.addCell(new Label(13, 4, "NGƯỜI DUYỆT", cellFormat));
			sheet.addCell(new Label(14, 4, "NGÀY GIỜ DUYỆT", cellFormat));

			sheet.setRowView(100, 4);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 35);
			sheet.setColumnView(8, 15);
			sheet.setColumnView(9, 15);
			sheet.setColumnView(10, 15);
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 15);
			sheet.setColumnView(13, 15);
			sheet.setColumnView(14, 60);
			dbutils db = new dbutils();

			ResultSet rs = db.get(query);

			WritableCellFormat cellFormat2 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));

			cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormat3 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));
			cellFormat3.setBackground(jxl.format.Colour.YELLOW);
			cellFormat3.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cformat = null;
			Label label;
			Number number;
			int stt = 0;
			
			System.out.println("[Sql]"+query);
			
			while (rs.next())
			{
				String type = "0";
				cformat = type.equals("1") ? cellFormat3 : cellFormat2;
				stt++;
				number = new Number(0, j, stt, cformat);
				sheet.addCell(number);
				label = new Label(1, j, rs.getString("THANG"), cformat);
				sheet.addCell(label);
				label = new Label(2, j, rs.getString("Nam"), cformat);
				sheet.addCell(label);
				label = new Label(3, j, rs.getString("DONVIKINHDOANH"), cformat);
				sheet.addCell(label);
				label = new Label(4, j, rs.getString("kenhbanhang"), cformat);
				sheet.addCell(label);
				number = new Number(5, j, rs.getDouble("chitieu"), cformat);
				sheet.addCell(number);
				label = new Label(6, j, rs.getInt("trangthai") == 0 ? "Ngưng hoạt động" : rs.getInt("trangthai") == 1 ? "Hoạt động" : "Đã hủy", cformat);
				sheet.addCell(label);

				number = new Number(7, j, rs.getDouble("SONGAYLAMVIEC"), cformat);
				sheet.addCell(number);
				label = new Label(8, j, rs.getString("NGAYKETTHUC"), cformat);
				sheet.addCell(label);

				label = new Label(9, j, rs.getString("ngaytao"), cformat);
				sheet.addCell(label);
				label = new Label(10, j, rs.getString("NguoiTao"), cformat);
				sheet.addCell(label);
				label = new Label(11, j, rs.getString("NgaySua"), cformat);
				sheet.addCell(label);
				label = new Label(12, j, rs.getString("NguoiSua"), cformat);
				sheet.addCell(label);

				label = new Label(13, j, rs.getString("NguoiDuyet")==null?"":rs.getString("NguoiDuyet"), cformat);sheet.addCell(label);
				label = new Label(14, j, rs.getString("DateDuyet")==null?"":rs.getString("DateDuyet"), cformat);sheet.addCell(label);
				
				
				j++;
			}
			w.write();
			w.close();
			rs.close();
			db.shutDown();
		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			if (out != null)
				out.close();

		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void XuatFileExcel(HttpServletResponse response, String id) throws IOException
	{
		OutputStream out1 = null;
		dbutils db = new dbutils();
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ChiTieu(MuaVao).xls");
			WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = w.createSheet("ChiTieu(MuaVao)", 0);

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet.addCell(new Label(0, 1, "Chỉ tiêu Mua Vào : ", new WritableCellFormat(cellTitle)));

			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			cellFont.setColour(Colour.BLACK);

			WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

			cellFormat.setBackground(jxl.format.Colour.GRAY_25);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormatSpecical = new WritableCellFormat(cellFont);

			cellFormatSpecical.setBackground(jxl.format.Colour.LIGHT_ORANGE);
			cellFormatSpecical.setWrap(true);
			cellFormatSpecical.setAlignment(Alignment.CENTRE);
			cellFormatSpecical.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormatSpecical.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			String sql = " SELECT  KBH.TEN AS KENH, c.kenh_fk,c.trangthai,  C.PK_SEQ, C.THANG "
					+ " , C.NAM, C.CHITIEU,C.DIENGIAI,C.DVKD_FK,donvikinhdoanh, C.NGAYKETTHUC,C.SONGAYLAMVIEC, C.NGAYTAO, C.NGAYSUA,NT.TEN AS NGUOITAO, "
					+ " NS.TEN AS NGUOISUA FROM dbo.CHITIEU AS C " + " inner join donvikinhdoanh d on d.pk_seq=c.dvkd_fk  " + " INNER JOIN NHANVIEN AS NT ON C.NGUOITAO = NT.PK_SEQ  "
					+ " INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ=C.KENH_FK  " + " INNER JOIN dbo.NHANVIEN AS NS ON C.NGUOISUA = NS.PK_SEQ   where  c.pk_seq=" + id;
			ResultSet rs = db.get(sql);

			String thang = "";
			String nam = "";
			String dvkdId = "";
			String kbhId = "";

			System.out.println("[ChiTieu]" + sql);
			while (rs.next())
			{
				sheet.addCell(new Label(0, 0, "CHỈ TIÊU THÁNG " + rs.getString("thang") + "-" + rs.getString("nam"), new WritableCellFormat(cellTitle)));
				sheet.addCell(new Label(0, 2, "DIỄN GIẢI :" + rs.getString("diengiai"), new WritableCellFormat(cellTitle)));
				sheet.addCell(new Label(0, 3, "KÊNH :" + rs.getString("KENH"), new WritableCellFormat(cellTitle)));

				sheet.addCell(new Label(0, 5, "MÃ", cellFormat));
				sheet.addCell(new Label(1, 5, "TÊN", cellFormat));
				sheet.addCell(new Label(2, 5, "CHỨC VỤ", cellFormat));
				sheet.addCell(new Label(3, 5, "CHỈ TIÊU", cellFormat));

				thang = rs.getString("thang");
				nam = rs.getString("nam");
				dvkdId = rs.getString("DVKD_FK");
				kbhId = rs.getString("kenh_fk");
			}
			rs.close();

			sheet.setRowView(100, 4);

			sheet.setColumnView(0, 20);
			sheet.setColumnView(1, 70);
			sheet.setColumnView(2, 10);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 35);
			sheet.setColumnView(8, 15);
			sheet.setColumnView(9, 15);
			sheet.setColumnView(10, 15);
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 15);
			sheet.setColumnView(13, 15);
			sheet.setColumnView(14, 60);

			sql = "select pk_seq,ten from nhomsanpham where pk_seq in (select nhomsp_fk from CHITIEUNPP_NHOMSP where chitieu_sec_fk=" + id + ")";

			System.out.println("[nhomsanpham]" + sql);

			rs = db.get(sql);
			String[] nhom = new String[10];
			String[] nhomid = new String[10];

			int i = 0;
			String strselect = "";
			String strngoac = "[0]";

			if (rs != null)
			{
				while (rs.next())
				{
					nhomid[i] = rs.getString("pk_seq");
					nhom[i] = rs.getString("ten");
					if (i == 0)
					{
						strselect = " ,[" + rs.getString("pk_seq") + "] as CT" + rs.getString("pk_seq") + "";
						strngoac = " [0], [" + rs.getString("pk_seq") + "]";
					} else
					{
						strselect = strselect + ", [" + rs.getString("pk_seq") + "] as CT" + rs.getString("pk_seq") + "";
						strngoac = strngoac + ",[" + rs.getString("pk_seq") + "]";
					}
					i++;
				}
			}
			sql = " select kv.ten as khuvuc ,v.ten as vung ,b.chitieu_fk,b.nhapp_fk,b.chitieu,a.ten ,a.sitecode " + " from chitieu_nhapp b  " + " inner join nhaphanphoi a on a.pk_seq=nhapp_fk  "
					+ " inner join khuvuc kv on kv.pk_seq=a.khuvuc_fk " + " inner join vung v on v.pk_seq=kv.vung_fk " + " where chitieu_fk=" + id + "  ";
			System.out.println("[chitieu_nhapp_sec]" + sql);
			rs = db.get(sql);
			Label label;
			Number number;
			int j = 6;

			WritableCellFormat cellFormat2 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));

			cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			if (rs != null)
				while (rs.next())
				{

					label = new Label(0, j, rs.getString("sitecode"), cellFormat2);
					sheet.addCell(label);

					label = new Label(1, j, rs.getString("ten"), cellFormat2);
					sheet.addCell(label);
					label = new Label(2, j, "NPP", cellFormat2);
					sheet.addCell(label);

					number = new Number(3, j, rs.getDouble("chitieu"), cellFormat2);
					sheet.addCell(number);

					for (int k = 0; k < i; k++)
					{
						number = new Number(7 + k, j, rs.getDouble(nhomid[k]), cellFormat2);
						sheet.addCell(number);
					}

					j++;
				}

			sql = "select chitieu_fk,a.pk_seq,a.manhanvien,a.ten,chitieu   from chitieu_gsbh  b inner join giamsatbanhang a on a.pk_seq=gsbh_fk where chitieu_fk=" + id;
			rs = db.get(sql);
			if (rs != null)
				while (rs.next())
				{

					label = new Label(0, j, rs.getString("manhanvien"), cellFormat2);
					sheet.addCell(label);

					label = new Label(1, j, rs.getString("ten"), cellFormat2);
					sheet.addCell(label);
					label = new Label(2, j, "SS", cellFormat2);
					sheet.addCell(label);

					number = new Number(3, j, rs.getDouble("chitieu"), cellFormat2);
					sheet.addCell(number);

					for (int k = 0; k < i; k++)
					{
						number = new Number(7 + k, j, rs.getDouble(nhomid[k]), cellFormat2);
						sheet.addCell(number);
					}

					j++;
				}
			sql = "select chitieu_fk,a.pk_seq,a.manhanvien,a.ten,chitieu   from chitieu_asm  b inner join asm a on a.pk_seq=asm_fk where chitieu_fk=" + id;
			rs = db.get(sql);
			if (rs != null)
				while (rs.next())
				{

					label = new Label(0, j, rs.getString("MANHANVIEN"), cellFormat2);
					sheet.addCell(label);

					label = new Label(1, j, rs.getString("TEN"), cellFormat2);
					sheet.addCell(label);
					label = new Label(2, j, "ASM", cellFormat2);
					sheet.addCell(label);

					number = new Number(3, j, rs.getDouble("chitieu"), cellFormat2);
					sheet.addCell(number);

					for (int k = 0; k < i; k++)
					{
						number = new Number(7 + k, j, rs.getDouble(nhomid[k]), cellFormat2);
						sheet.addCell(number);
					}

					j++;
				}
			w.write();
			w.close();

		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.toString());
			e.printStackTrace();
		} finally
		{
			if (out1 != null)
				out1.close();
		}
	}

}
