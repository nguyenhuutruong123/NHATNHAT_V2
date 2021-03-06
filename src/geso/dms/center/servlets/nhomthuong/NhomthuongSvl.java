package geso.dms.center.servlets.nhomthuong;

import geso.dms.center.beans.nhomthuong.INhomthuong;
import geso.dms.center.beans.nhomthuong.INhomthuongList;
import geso.dms.center.beans.nhomthuong.imp.Nhomthuong;
import geso.dms.center.beans.nhomthuong.imp.NhomthuongList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class NhomthuongSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;
	PrintWriter out;
	HttpServletRequest request;
	HttpServletResponse response;
	INhomthuongList obj;
	dbutils db;

	public NhomthuongSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		this.request = request;
		this.response = response;
		this.db = new dbutils();

		response.setContentType("text/html");

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
		out = response.getWriter();
		obj = new NhomthuongList();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String action = util.getAction(querystring);
		out.println(action);

		String nkmId = util.getId(querystring);

		if (action.equals("delete"))
		{
			Delete(nkmId);
		}

		if (action.equals("chot"))
		{
			System.out.println("1.Action la: Chot + Id: " + nkmId);
			Chot(nkmId);
		}
		ResultSet Dsnkm = db.get("select a.tungay,a.denngay ,a.pk_seq, a.ten, a.diengiai, a.trangthai, a.type, a.ngaytao, a.ngaysua, b.ten as nguoitao," + " c.ten as nguoisua from NHOMSANPHAMCHITIEU a, nhanvien b, nhanvien c"
				+ " where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ  ");
		obj.setDsnkm(Dsnkm);
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);

		String nextJSP = request.getContextPath() + "/pages/Center/NhomThuong.jsp";
		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Utility Ult = new Utility();
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String userId = request.getParameter("userId");

		this.obj = new NhomthuongList();
		this.db = new dbutils();

		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		out.println(action);
		String diengiai = request.getParameter("diengiai");
		if (diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);
		String tungay = request.getParameter("tungay");
		if (tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		String denngay = request.getParameter("denngay");
		if (denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		String trangthai = request.getParameter("trangthai");
		if (trangthai == null)
			trangthai = "";

		if (trangthai.equals("2"))
			trangthai = "";
		obj.setTrangthai(trangthai);
		String query = "select  a.tungay,a.denngay,a.pk_seq, a.ten, a.type, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from NHOMSANPHAMCHITIEU a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type = '4' ";
		if (diengiai.length() > 0)
		{
			query = query + " and upper (a.diengiai) like upper(N'%" + diengiai + "%')";
			
		}
		if (tungay.length() > 0)
		{
			query = query + " and a.tungay >= '" + tungay + "'";

		}

		if (denngay.length() > 0)
		{
			query = query + " and a.denngay <= '" + denngay + "'";

		}

		if (trangthai.length() > 0)
		{
			query = query + " and a.trangthai = '" + trangthai + "'";

		}
		// query = query
		// +" and a.pk_seq in (select nsp_fk from NHOMSANPHAMCHITIEU_SANPHAM where sp_fk in "+
		// Ult.quyen_sanpham(userId) +")";
		System.out.println(query);
		// Perform searching. Each Nhomthuong is saved into Nhomthuong
		if (action.equals("new"))
		{
			// Empty Bean for distributor
			INhomthuong nkmBean = (INhomthuong) new Nhomthuong();

			nkmBean.UpdateRS();
			// Save Data into session
			session.setAttribute("nkmBean", nkmBean);
			session.setAttribute("userId", userId);

			String nextJSP = request.getContextPath() + "/pages/Center/NhomThuongNew.jsp";
			response.sendRedirect(nextJSP);

		} else if (action.equals("search"))
		{
			
			ResultSet Dsnkm = db.get(query);
			obj.setDsnkm(Dsnkm);
			session.setAttribute("obj", obj);

			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/NhomThuong.jsp");
		}
		else if (action.equals("excel"))
		{
			ToExcel(response, query);
		} 
		else if(action.equals("refresh"))
		{
			query = "select  a.tungay,a.denngay,a.pk_seq, a.ten, a.type, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from NHOMSANPHAMCHITIEU a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type = '4' ";
			ResultSet Dsnkm = db.get(query);
			obj = new NhomthuongList();
			obj.setDsnkm(Dsnkm);
			session.setAttribute("obj", obj);

			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/NhomThuong.jsp");
		}
		else
		{
			obj = new NhomthuongList();

			ResultSet Dsnkm = db
					.get("select a.pk_seq, a.type, a.ten, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from NHOMSANPHAMCHITIEU a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='1' and a.pk_seq in (select nsp_fk from NHOMSANPHAMCHITIEU_SANPHAM where sp_fk in "
							+ Ult.quyen_sanpham(userId) + ")");
			obj.setDsnkm(Dsnkm);
			session.setAttribute("obj", obj);

			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/NhomThuong.jsp");
		}
	}

	public void Delete(String nkmId)
	{
		String command;
		try {
			dbutils db=new dbutils();
			String query="select  count(*) sl  from TIEUCHITHUONG_CHITIET where nhomsp_fk="+nkmId;
			 System.out.println("query la "+query);
			ResultSet rs=db.get(query);
			rs.next();
			int  sl=rs.getInt("sl");
			rs.close();
			if(sl>0)
			{
				db.shutDown();
				return;
			}
			
			db.getConnection().setAutoCommit(false);
			
			
			command = "delete from NHOMSANPHAMCHITIEU_sanpham where nsp_fk ='" + nkmId + "'";
			if(!db.update(command))
			{
				db.getConnection().rollback();
				return;
			}

			command = "delete from NHOMSANPHAMCHITIEU where pk_seq ='" + nkmId + "'";
			if(!db.update(command))
			{
				db.getConnection().rollback();
				return;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	private void Chot(String nkmId)
	{
		String command = "update NHOMSANPHAMCHITIEU set trangthai = '1' where pk_seq ='" + nkmId + "'";
		db.update(command);
	}

	private void ToExcel(HttpServletResponse response, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=NhomSKU.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("NhomSanPhamChiTieu", k);
			sheet.addCell(new Label(0, 1, "NH??M S???N PH???M CH??? TI??U", new WritableCellFormat(cellTitle)));

			sheet.addCell(new Label(0, 2, "Ng??y t???o: "));
			sheet.addCell(new Label(1, 2, "" + getDateTime()));

			sheet.addCell(new Label(2, 4, "????n v??? ti???n t???:VND"));

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

			sheet.addCell(new Label(0, 4, "STT", cellFormat));
			sheet.addCell(new Label(1, 4, "M?? NH??M", cellFormat));
			sheet.addCell(new Label(2, 4, "T??N NH??M", cellFormat));
			sheet.addCell(new Label(3, 4, "DI???N GI???I", cellFormat));
			sheet.addCell(new Label(4, 4, "LO???I", cellFormat));
			sheet.addCell(new Label(5, 4, "TR???NG TH??I ", cellFormat));
			sheet.addCell(new Label(6, 4, "NG??Y T???O", cellFormat));
			sheet.addCell(new Label(7, 4, "NG?????I T???O", cellFormat));
			sheet.addCell(new Label(8, 4, "NG??Y S???A", cellFormat));
			sheet.addCell(new Label(9, 4, "NG?????I S???A", cellFormat));

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
			while (rs.next())
			{
				String type = rs.getString("type");
				if (type == null)
					type = "";
				else if (type.equals("4"))
					type = "SKU In";
				else if (type.equals("6"))
					type = "SKU Out";

				cformat = cellFormat2;
				stt++;
				number = new Number(0, j, stt, cformat);
				sheet.addCell(number);
				label = new Label(1, j, rs.getString("pk_seq"), cformat);
				sheet.addCell(label);
				label = new Label(2, j, rs.getString("ten"), cformat);
				sheet.addCell(label);
				label = new Label(3, j, rs.getString("diengiai"), cformat);
				sheet.addCell(label);
				label = new Label(4, j, type, cformat);
				sheet.addCell(label);

				label = new Label(5, j, rs.getInt("trangthai") == 0 ? "Ch??? x??? l??" : rs.getInt("trangthai") == 1 ? "???? ch???t" : "???? h???y", cformat);
				sheet.addCell(label);

				label = new Label(6, j, rs.getString("ngaytao"), cformat);
				sheet.addCell(label);
				label = new Label(7, j, rs.getString("NguoiTao"), cformat);
				sheet.addCell(label);
				label = new Label(8, j, rs.getString("NgaySua"), cformat);
				sheet.addCell(label);
				label = new Label(9, j, rs.getString("NguoiSua"), cformat);
				sheet.addCell(label);

				j++;
			}
			w.write();
			w.close();
			rs.close();
			db.shutDown();
		} catch (Exception e) { e.printStackTrace(); } finally { if (out != null) out.close(); }
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
}
