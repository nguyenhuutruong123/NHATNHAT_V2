package geso.dms.center.servlets.nhomhang;


import geso.dms.center.beans.nhomhang.INhomHang;
import geso.dms.center.beans.nhomhang.imp.NhomHang;
import geso.dms.center.beans.nhomhang.INhomHangList;
import geso.dms.center.beans.nhomhang.imp.NhomHangList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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

@WebServlet("/NhomHangSvl")
public class NhomHangSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public NhomHangSvl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		INhomHangList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//this.out = response.getWriter();

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
		//out = response.getWriter();
		obj = new NhomHangList();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		//out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);
		//out.println(action);

		String nppId = util.getId(querystring);
		if(action!=null)
		{
			if (action.equals("delete"))
			{
				 obj.setMsg(Delete(nppId));
			}
			obj.setUserId(userId);
			obj.init();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Center/NhomHang.jsp";
			response.sendRedirect(nextJSP);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		INhomHangList obj = new NhomHangList();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//OutputStream out = response.getOutputStream();

		Utility util = new Utility();

		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));

		String ten = util.antiSQLInspection(request.getParameter("nppTen"));
		if (ten == null)
			ten = "";
		obj.setTen(ten);

		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}
		String nextJSP = "";

		if (action.equals("new"))
		{
			INhomHang nppBean = (NhomHang) new NhomHang();
			nppBean.setUserId(userId);
			nppBean.createRs();
			session.setAttribute("nhBean", nppBean);
			nextJSP = request.getContextPath() + "/pages/Center/NhomHangNew.jsp";
			session.setAttribute("userId", userId);
			response.sendRedirect(nextJSP);

		} else if (action.equals("search"))
		{
			obj.setUserId(userId);
			String search = getSearchQuery(request,obj);
			obj.init_search(search);
			nextJSP = request.getContextPath() + "/pages/Center/NhomHang.jsp";
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			response.sendRedirect(nextJSP);
		} 


	  /*  else if (request.getParameter("action").equals("excel")) {
	    	ToExcel(response, getSearchQuery(request));
	    }*/
		
		else if (action.equals("view") || action.equals("next") || action.equals("prev"))
		{

			obj.setUserId(userId);
			obj.setNxtApprSplitting(Integer.parseInt(util.antiSQLInspection(request.getParameter("nxtApprSplitting"))));
			obj.init();
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			session.setAttribute("obj", obj);
			nextJSP = request.getContextPath() + "/pages/Center/NhomHang.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private String Delete(String id)
	{
		dbutils db = new dbutils();
		try
		{
		    db.getConnection().setAutoCommit(false);
		    /*String sql = "SELECT COUNT(SanPham_FK) AS num FROM dbo.NhomHang_SanPham WHERE dbo.NhomHang_SanPham.NhomHang_FK = '"+id+"'";
		    ResultSet rs1 = db.get(sql);
		    rs1.next();
		    if(!rs1.getString("num").equals("0")) {
		    	db.getConnection().rollback();
		    	return "Nhóm sản phẩm đã có dữ liệu không thể xóa";
		    }*/
		    String sql = " delete from  NhomHang_SanPham where NhomHang_FK = '" + id + "' ";
		    if(!db.update(sql))
		    {
		    	db.getConnection().rollback();
		    	return "Lỗi phát sinh khi cập nhật "+sql;
		    }
			sql="delete from NhomHang where pk_seq='"+id+"'";
		    if(!db.update(sql))
		    {
		    	db.getConnection().rollback();
		    	return "Lỗi phát sinh khi cập nhật "+sql;
		    }
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
	    } catch (SQLException e){ e.printStackTrace(); } 
		finally { if(db!=null)db.shutDown(); }
		return "";
	}

	private String getSearchQuery(HttpServletRequest request,INhomHangList obj )
	{
		

		Utility util = new Utility();

		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null)
			ten = "";
		obj.setTen(ten);
		

		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null)
			ma = "";
		obj.setMa(ma);

	
		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));
		if (trangthai == null)
			trangthai = "";

		if (trangthai.equals("2"))
			trangthai = "";

		//obj.setTrangthai(trangthai);
	
		String query=
				"SELECT a.pk_seq as id,a.ma,a.ten,nt.TEN as NguoiTao,a.ngaytao,ns.TEN as NguoiSua,a.ngaysua "+
				"FROM NhomHang a inner join NHANVIEN nt on nt.PK_SEQ=a.nguoitao "+
				"	inner join NHANVIEN ns on ns.PK_SEQ=a.nguoisua where 1=1 ";
		
		if(ma.trim().length()>0)
		{
			query+=" and a.ma like N'%"+ma+"%'";
		}
		if(ten.trim().length()>0)
		{
			query+="  and a.ten like N'%"+ten+"%'";
		}
		System.out.println("query "+query);
		
		
		
		return query;
	}
	


	private void ToExcel(HttpServletResponse response, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=NhomHang.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("NhomHang", k);
			sheet.addCell(new Label(0, 1, "NHÓM NHÀ PHÂN PHỐI: ", new WritableCellFormat(cellTitle)));

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
			sheet.addCell(new Label(1, 4, "MÃ", cellFormat));
			sheet.addCell(new Label(2, 4, "TÊN", cellFormatSpecical));
			sheet.addCell(new Label(3, 4, "NGÀY TẠO", cellFormat));
			sheet.addCell(new Label(4, 4, "NGƯỜI TẠO", cellFormat));
			sheet.addCell(new Label(5, 4, "NGÀY SỬA", cellFormat));
			sheet.addCell(new Label(6, 4, "NGƯỜI SỬA", cellFormat));

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

				number = new Number(0, j, stt, cformat);
				sheet.addCell(number);
				label = new Label(1, j, rs.getString("nppMa"), cformat);
				sheet.addCell(label);
				label = new Label(2, j, rs.getString("nppTen"), cformat);
				sheet.addCell(label);
				label = new Label(3, j, rs.getString("NgayTao"), cformat);
				sheet.addCell(label);
				label = new Label(4, j, rs.getString("NguoiTao"), cformat);
				sheet.addCell(label);
				label = new Label(5, j, rs.getString("NgaySua"), cformat);
				sheet.addCell(label);
				label = new Label(6, j, rs.getString("NguoiSua"), cformat);
				sheet.addCell(label);

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
