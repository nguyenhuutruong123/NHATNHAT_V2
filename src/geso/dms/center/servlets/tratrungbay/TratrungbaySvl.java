package geso.dms.center.servlets.tratrungbay;

import geso.dms.center.beans.tratrungbay.*;
import geso.dms.center.beans.tratrungbay.imp.*;
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


public class TratrungbaySvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	PrintWriter out; 

	public TratrungbaySvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		ITratrungbayList obj;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out  = response.getWriter();

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

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);
		out.println(action);

		String tratbId = util.getId(querystring);
		obj = new TratrungbayList();
		obj.setUserId(userId);
		
		if (action.equals("delete")){	
			obj.setMsg( Delete(tratbId,obj));
			out.print(tratbId);
		}

		
		
		obj.init("");
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/TraTrungBay.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		ITratrungbayList obj = new TratrungbayList();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");


		Utility util = new Utility();

		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.antiSQLInspection(request.getParameter("action"));
		if (action == null){
			action = "";
		}


		String search = getSearchQuery(request,obj,userId);
		if (action.equals("Tao moi"))
		{
			ITratrungbay tratbBean = (ITratrungbay) new Tratrungbay("");
			tratbBean.setUserId(userId);
			tratbBean.createRS();
			session.setAttribute("tratbBean", tratbBean);

			String nextJSP = request.getContextPath() + "/pages/Center/TraTrungBayNew.jsp";
			response.sendRedirect(nextJSP);

		}else if (action.equals("toExcel"))
		{
			ToExcel(response, obj, search);
		}
		else{	    

			obj.init(search);
			obj.setUserId(userId);

			session.setAttribute("obj", obj);  	
			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/TraTrungBay.jsp");    	
		}
	}

	private String getSearchQuery(HttpServletRequest request, ITratrungbayList obj,String userId)
	{	

		Utility util = new Utility();

		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if ( diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);

		String query = "select ROW_NUMBER() OVER(ORDER BY a.pk_seq DESC) AS stt, a.PK_SEQ as trakmId, a.diengiai, isnull(a.TONGLUONG, 0) as tongluong, isnull(a.TONGTIEN, 0) as tongtien, a.loai, a.hinhthuc, a.ngaytao, a.ngaysua, b.TEN as nguoitao, c.TEN as nguoisua   ";
		query = query + "  from tratrungbay a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ where 1=1 "  ;
		if (diengiai.length()>0){
			query = query + " and ( upper(dbo.ftBoDau(a.diengiai)) like upper(N'%" + util.replaceAEIOU(diengiai) + "%') or upper(a.pk_seq) like upper('%" + diengiai + "%') )";			
		}

		if (tungay.length()>0){
			query = query + " and a.ngaytao >= '" + convertDate(tungay) + " '";	
		}

		if (denngay.length()>0){
			query = query + " and a.ngaytao <= '" + convertDate(denngay) + " '";	
		}
		System.out.println("query = "+query);
		query = query + " order by a.NGAYTAO DESC, a.pk_seq DESC";
		return query;
	}

	private String convertDate(String date) 
	{
		//chuyen dinh dang dd-MM-yyyy sang dinh dang yyyy-MM-dd
		if(!date.contains("-"))
			return "";
		String[] arr = date.split("-");
		if(arr[0].length() < arr[2].length())
			return arr[2] + "-" + arr[1] + "-" + arr[0];
		return date;
	}

	private String Delete(String tratbId,ITratrungbayList obj ) 
	{

		try
		{
			obj.getDb().getConnection().setAutoCommit(false);
			String query =  "";
			
			query = " select count(*) a from  cttb_tratb  where tratrungbay_fk='" + tratbId + "'";
			ResultSet rs =obj.getDb().get(query);
			rs.next();
			if(rs.getInt("a") > 0 )
			{
				rs.close();
				Utility.rollback_throw_exception(obj.getDb());
				return "Không thể xoá trả trưng bày này. Đã phát sinh trong chương trình trưng bày";
			}
			rs.close();
			//Xoa Cac Bang Con Truoc
			query = "delete from TRATRUNGBAY_SANPHAM where TRATRUNGBAY_FK='" + tratbId + "'";
			if(!obj.getDb().update(query))
			{
				System.out.println("query ="+ query);
				Utility.rollback_throw_exception(obj.getDb());
				return "Lỗi xóa  trả trưng bày sp";
			}
			

			//Xoa Bang Cha
			query= "delete from tratrungbay where pk_seq = '" + tratbId + "'";
			if(!obj.getDb().update(query))
			{

				System.out.println("query ="+ query);
				Utility.rollback_throw_exception(obj.getDb());
				return "Lỗi xóa trả  trưng bày ";
			}
			//db.update("commit");
			obj.getDb().getConnection().commit();
			obj.getDb().getConnection().setAutoCommit(true);
			return "Xóa thành công!";
		}catch(Exception err){
			Utility.rollback_throw_exception(obj.getDb());
			return "Exception:" + err.getMessage();
		}
	}

	private void ToExcel(HttpServletResponse response, ITratrungbayList obj, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=TraTrungBay.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("TraTrungBay", k);
			sheet.addCell(new Label(0, 1, "TRẢ TRƯNG BÀY: ", new WritableCellFormat(cellTitle)));

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
			sheet.addCell(new Label(1, 4, "MÃ TRẢ TB", cellFormat));
			sheet.addCell(new Label(2, 4, "DIỄN GIẢI", cellFormatSpecical));
			sheet.addCell(new Label(3, 4, "TỔNG LƯỢNG", cellFormat));
			sheet.addCell(new Label(4, 4, "TỔNG TIỀN", cellFormat));
			sheet.addCell(new Label(5, 4, "NGÀY TẠO", cellFormat));
			sheet.addCell(new Label(6, 4, "NGƯỜI TẠO", cellFormat));
			sheet.addCell(new Label(7, 4, "NGÀY SỬA", cellFormat));
			sheet.addCell(new Label(8, 4, "NGƯỜI SỬA", cellFormat));

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

			while (rs.next())
			{
				String type = "0";
				cformat = type.equals("1") ? cellFormat3 : cellFormat2;

				number = new Number(0, j, rs.getDouble("STT"), cformat);
				sheet.addCell(number);
				label = new Label(1, j, rs.getString("trakmId"), cformat);
				sheet.addCell(label);
				label = new Label(2, j, rs.getString("DienGiai"), cformat);
				sheet.addCell(label);
				number = new Number(3, j, rs.getDouble("TongLuong"), cformat);
				sheet.addCell(number);
				number = new Number(4, j, rs.getDouble("TongTien"), cformat);
				sheet.addCell(number);
				label = new Label(5, j, rs.getString("NgayTao"), cformat);
				sheet.addCell(label);
				label = new Label(6, j, rs.getString("NguoiTao"), cformat);
				sheet.addCell(label);
				label = new Label(7, j, rs.getString("NgaySua"), cformat);
				sheet.addCell(label);
				label = new Label(8, j, rs.getString("NguoiSua"), cformat);
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
