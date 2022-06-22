package geso.dms.center.servlets.chitieu;

import geso.dms.center.beans.chitieunpp.imp.ChiTieuNhaPP;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chitieunpp.imp.ChitieuSKUInList;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
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
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ChiTieuNppTTSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ChiTieuNppTTSvl()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{
			Utility util = new Utility();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			ChiTieuNhaPP obj = new ChiTieuNhaPP();
			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			if (userId.length() == 0)
				userId = request.getParameter("userId");
			session.setAttribute("userId", userId);

			obj.setUserId(userId);

			String action = util.getAction(querystring);
			String nextJSP = request.getContextPath() + "/pages/Center/chitieunpptt.jsp";// default
			if (action.equals("chot"))
			{
				obj.setID(Double.parseDouble(util.getId(querystring)));
				obj.ChotChiTieu();
			}
			obj.setListChiTieu("");
			session.setAttribute("obj", obj);

			response.sendRedirect(nextJSP);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{
			Utility util = new Utility();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			userId = request.getParameter("userId");

			String thangtk = request.getParameter("thang");
			String namtk = request.getParameter("nam");

			ChiTieuNhaPP chitieu = new ChiTieuNhaPP();
			chitieu.setThang(Integer.parseInt(thangtk));
			chitieu.setNam(Integer.parseInt(namtk));
			chitieu.setUserId(userId);

			String sql = 
					"SELECT    C.KENH_FK,kbh.ten as kenh,C.TRANGTHAI, C.PK_SEQ, C.THANG, C.NAM, C.NHAPP_FK, K.TEN AS TENNPP, C.CHITIEU,C.DIENGIAI, C.NGAYKETTHUC,C.DVKD_FK,d.donvikinhdoanh,C.SONGAYLAMVIEC, C.NGAYTAO, C.NGAYSUA,NT.TEN AS NGUOITAO, "
					+ " NS.TEN AS NGUOISUA FROM dbo.CHITIEUNPP AS C "
					+ "INNER JOIN dbo.NHAPHANPHOI AS K ON C.NHAPP_FK = K.PK_SEQ INNER JOIN  "
					+ " dbo.NHANVIEN AS NT ON C.NGUOITAO = NT.PK_SEQ  "
					+ "INNER JOIN dbo.NHANVIEN AS NS ON C.NGUOISUA = NS.PK_SEQ "
					+ "inner join donvikinhdoanh d on d.pk_seq=C.DVKD_FK "
					+ "inner join kenhbanhang kbh on kbh.pk_seq=c.kenh_fk where 1=1 " + " and NHAPP_FK in  " + util.quyen_npp(userId) + " ";

			if (!thangtk.equals("0"))
			{
				sql += " and C.THANG=" + thangtk + " ";
			}
			if (!namtk.equals("0"))
			{
				sql += "and C.NAM=" + namtk + " ";
			}

			String nppId = request.getParameter("nppId");
			if (nppId == null)
				nppId = "";
			chitieu.setNppId(nppId);

			String vungId = request.getParameter("vungId");
			if (vungId == null)
				vungId = "";
			chitieu.setVungId(vungId);

			String khuvucId = request.getParameter("khuvucId");
			if (khuvucId == null)
				khuvucId = "";
			chitieu.setKhuvucId(khuvucId);

			if (nppId.length() > 0)
				sql += "  and K.PK_SEQ = '" + nppId + "'";
			if (khuvucId.length() > 0)
				sql += " and k.KHUVUC_FK =" + khuvucId + "  ";
			if (vungId.length() > 0)
				sql += " and k.KHUVUC_FK in ( select pk_seq from KHUVUC where VUNG_FK=" + vungId + " ) ";

			System.out.println("[nppId]" + nppId + "[kvId]" + khuvucId + "[vungId]" + vungId);
			System.out.println("[Init]" + sql);
			String action = request.getParameter("action");
		    if (action == null){
		    	action = "";
		    }
			chitieu.setListChiTieu(sql);

			if (action.equals("excel"))
     		{
     			ToExcel(response, sql);
     		}
    		
    	
			
			
			session.setAttribute("obj", chitieu);
			String nextJSP = request.getContextPath() + "/pages/Center/chitieunpptt.jsp";// default
			response.sendRedirect(nextJSP);

		}
	}
	private void ToExcel(HttpServletResponse response, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=ChiTieuSKUIN.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("ChiTieuSKUIN", k);
			sheet.addCell(new Label(0, 1, "Chỉ tiêu nhóm SKU IN : ", new WritableCellFormat(cellTitle)));

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
			sheet.addCell(new Label(3, 4, "ĐVKD", cellFormat));
			sheet.addCell(new Label(4, 4, "KÊNH", cellFormat));
			sheet.addCell(new Label(5, 4, "NHÀ PHÂN PHỐI", cellFormat));
			sheet.addCell(new Label(6, 4, "TRẠNG THÁI ", cellFormat));
			sheet.addCell(new Label(7, 4, "NGÀY TẠO", cellFormat));
			sheet.addCell(new Label(8, 4, "NGƯỜI TẠO", cellFormat));
			sheet.addCell(new Label(9, 4, "NGÀY SỬA", cellFormat));
			sheet.addCell(new Label(10, 4, "NGƯỜI SỬA", cellFormat));

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
			int stt=0;
			while (rs.next())
			{
				String type = "0";
				cformat = type.equals("1") ? cellFormat3 : cellFormat2;
				stt++;
				number = new Number(0, j, stt, cformat);sheet.addCell(number);
				label = new Label(1, j, rs.getString("THANG"), cformat);sheet.addCell(label);
				label = new Label(2, j, rs.getString("Nam"), cformat);sheet.addCell(label);
				label = new Label(3, j, rs.getString("donvikinhdoanh"), cformat);sheet.addCell(label);
				label = new Label(4, j, rs.getString("kenh"), cformat);sheet.addCell(label);
				label = new Label(5, j, rs.getString("TENNPP"), cformat);sheet.addCell(label);
				
				label = new Label(6, j, rs.getInt("trangthai") == 0 ? "Chờ xử lý" :rs.getInt("trangthai") == 1? "Đã chốt":"Đã hủy", cformat);sheet.addCell(label);
				
				label = new Label(7, j, rs.getString("ngaytao"), cformat);sheet.addCell(label);
				label = new Label(8, j, rs.getString("NguoiTao"), cformat);sheet.addCell(label);
				label = new Label(9, j, rs.getString("NgaySua"), cformat);sheet.addCell(label);
				label = new Label(10, j, rs.getString("NguoiSua"), cformat);sheet.addCell(label);

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
}
