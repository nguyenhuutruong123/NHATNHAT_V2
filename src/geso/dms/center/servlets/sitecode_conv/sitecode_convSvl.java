package geso.dms.center.servlets.sitecode_conv;


import geso.dms.center.beans.sitecode_conv.Isitecode_conv;
import geso.dms.center.beans.sitecode_conv.imp.sitcode_conv;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

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

public class sitecode_convSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public sitecode_convSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();

		HttpSession session = request.getSession();

		Utility util = new Utility();
		out = response.getWriter();
		Isitecode_conv obj;
		obj = new sitcode_conv();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);
		out.println(action);

		String nppId = util.getId(querystring);
		obj.setUserid(userId);
		obj.Init("");
		obj.settrangthai("");
		obj.setMsg("");
		obj.setten("");
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/sitecode_conv.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		HttpSession session = request.getSession();
		Isitecode_conv obj;
		obj = new sitcode_conv();
		obj.setUserid(userId);
		String ten = util.antiSQLInspection(request.getParameter("nppTen"));
		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));
		obj.setten(ten);
		obj.settrangthai(trangthai);
		
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
	
		
		String sql=
		"	select conv.sitecode,conv.ten,isnull (conv.convsitecode,'') as convsitecode, "+
		"		isnull(conv.tennpptn,'NA') as npptiennhiem,ISNULL(npp.pk_seq,'0') as idnpptn, "+ 
		"		conv.trangthai,conv.ngaytao,conv.ngaysua,nt.ten as nguoitao,ns.ten as nguoisua,isnull(npp.ten,'') as tenupdate , "+
		"		isnull(dms.PK_SEQ,0) as MaDms "+
		"	from sitecode_conv conv 	  "+
		"		left join nhaphanphoi npp on npp.sitecode=conv.convsitecode "+ 
		"		left join NHAPHANPHOI dms on dms.CONVSiteCode=conv.SITECODE  "+
		"		inner join nhanvien nt on nt.pk_seq= conv.nguoitao  "+
		"		inner join nhanvien ns on conv.nguoisua=ns.pk_seq  " +
		" WHERE 1=1 ";

		String dk = "";

		if (ten != "")
		{
			dk = dk + " and ( upper(dbo.ftBoDau(conv.ten)) like upper(N'%" + util.replaceAEIOU(ten.trim()) + "%')  OR  upper(dbo.ftBoDau(conv.sitecode)) like upper(N'%" + util.replaceAEIOU(ten.trim()) + "%')) ";
		}
		if (trangthai != "")
		{
			dk = dk + " and conv.trangthai = '" + trangthai + "' ";
		}
		sql = sql + dk;
		System.out.println("[nppErp]"+sql);
		if (action.equals("view") || action.equals("next") || action.equals("prev"))
		{
			obj.setUserid(userId);
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			obj.Init(sql);
			String nextJSP = request.getContextPath() + "/pages/Center/sitecode_conv.jsp";
			session.setAttribute("obj", obj);
			response.sendRedirect(nextJSP);
		}else if (action.equals("excel"))
	    {
	    	ToExcel(response, obj, sql);
	    }
		else 
		{
			obj.Init(sql);
			String nextJSP = request.getContextPath() + "/pages/Center/sitecode_conv.jsp";
			session.setAttribute("obj", obj);
			response.sendRedirect(nextJSP);
		}
	}
	
	private void ToExcel(HttpServletResponse response, Isitecode_conv obj, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=NppErp.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("NppErp", k);
			sheet.addCell(new Label(0, 1, "NH?? PH??N PH???I ERP: ", new WritableCellFormat(cellTitle)));

			sheet.addCell(new Label(0, 2, "Ng??y t???o: "));
			sheet.addCell(new Label(1, 2, "" + getDateTime()));

			sheet.addCell(new Label(2, 4, "????n v??? ti???n t???:VND"));

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
			sheet.addCell(new Label(1, 4, "T??N NPP ERP", cellFormat));
			sheet.addCell(new Label(2, 4, "SITECODE ERP", cellFormatSpecical));
			sheet.addCell(new Label(3, 4, "NPP DMS", cellFormat));
			sheet.addCell(new Label(4, 4, "SITECODE DMS", cellFormat));
			sheet.addCell(new Label(5, 4, "NG??Y T???O", cellFormat));
			sheet.addCell(new Label(6, 4, "NG?????I T???O", cellFormat));
			sheet.addCell(new Label(7, 4, "NG??Y S???A", cellFormat));
			sheet.addCell(new Label(8, 4, "NG?????I S???A", cellFormat));
			sheet.addCell(new Label(9, 4, "TR???NG TH??I", cellFormat));

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
				stt++;
				String type = "0";
				cformat = type.equals("1") ? cellFormat3 : cellFormat2;

				number = new Number(0, j, stt, cformat);sheet.addCell(number);
				label = new Label(1, j, rs.getString("ten"), cformat);sheet.addCell(label);
				label = new Label(2, j, rs.getString("SITECODE"), cformat);sheet.addCell(label);
				String trangthai=rs.getString("trangthai");
				if(trangthai.equals("0"))
					label = new Label(3, j, rs.getString("npptiennhiem"), cformat);
				else 
					label = new Label(3, j, rs.getString("tenupdate"), cformat);
				label = new Label(4, j, rs.getString("convsitecode"), cformat);sheet.addCell(label);
				
				label = new Label(5, j, rs.getString("NgayTao"), cformat);sheet.addCell(label);
				label = new Label(6, j, rs.getString("NguoiTao"), cformat);sheet.addCell(label);
				label = new Label(7, j, rs.getString("NgaySua"), cformat);sheet.addCell(label);
				label = new Label(8, j, rs.getString("NguoiSua"), cformat);sheet.addCell(label);
				label = new Label(9, j, rs.getInt("trangthai") == 0 ? "Ch??? x??? l??" : "Ho??n t???t", cformat);sheet.addCell(label);
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
