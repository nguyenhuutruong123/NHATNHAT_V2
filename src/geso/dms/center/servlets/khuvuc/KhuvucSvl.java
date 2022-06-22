package geso.dms.center.servlets.khuvuc;

import geso.dms.center.beans.khuvuc.*;
import geso.dms.center.beans.khuvuc.imp.*;
import geso.dms.center.beans.vung.IVungmien;
import geso.dms.center.beans.vung.IVungmienList;
import geso.dms.center.beans.vung.imp.VungmienList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.util.UtilitySyn;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


public class KhuvucSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
		
    public KhuvucSvl()
    {
        super();
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		IKhuvucList obj;
		
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
	    obj = new KhuvucList();
	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    String action = util.getAction(querystring);	    
	    String kvId = util.getId(querystring);
	    
	    if (!Utility.isValid(userId))
	    	userId = util.antiSQLInspection(request.getParameter("userId")); 

	    if (action.equals("delete")){	   		  	    	
	    	obj.setMsg(Delete(kvId));
	    }	   	
	   
	    obj.setUserId(userId);
	    obj.init("");
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/KhuVuc.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		IKhuvucList obj = new KhuvucList();
		Utility util = new Utility();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    //this.out = response.getWriter();
	    
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    //out.println(action); 
	        
	    
	    if (action.equals("new")){
	    	// Empty Bean for distributor
	    	IKhuvuc kvBean = (IKhuvuc) new Khuvuc("");
	    	kvBean.setUserId(userId);
	    	// Save Data into session
	    	session.setAttribute("kvBean", kvBean);
    		
    		String nextJSP = request.getContextPath() + "/pages/Center/KhuVucNew.jsp";
    		response.sendRedirect(nextJSP);
    		
	    }

		else if (action.equals("excel")) {
			obj.init(getSearchQuery(request,obj));
			ToExcel(response, obj);
		} 
	    
	    if (action.equals("search")){
	    	String search = getSearchQuery(request,obj);
	    	
	    	//obj = new KhuvucList(search);
	    	obj.init(search);
			obj.setUserId(userId);
			
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		session.setAttribute("abc", search);
	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/KhuVuc.jsp");	    	
	    	
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request,IKhuvucList obj){
		   // PrintWriter out = response.getWriter();
			
		//IKhuvucList obj = new KhuvucList();
		
		Utility util = new Utility();
		
			String vung = util.antiSQLInspection(request.getParameter("VungMien"));
	    	if ( vung == null)
	    		vung = "";
	    	obj.setVmId(vung);
	    	
	    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
	    	if (trangthai == null)
	    		trangthai = "";    	
		
	   if (trangthai.equals("2"))
	  	trangthai = "";
	    	
	    	obj.setTrangthai(trangthai);
	    	
	    //	String query = "select a.pk_seq as id, a.ten as kvTen, a.trangthai as trangthai, b.pk_seq as vmId, b.ten as vmTen, c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, a.diengiai";
			//query = query + " from khuvuc a, vung b, nhanvien c, nhanvien d";
		//	query = query + " where a.vung_fk=b.pk_seq and a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq";
			
			String query = "select a.ma,a.pk_seq as id, a.ten as kvTen, a.trangthai as trangthai, b.pk_seq as vmId, b.ten as vmTen, c.ten as nguoitao, a.ngaytao as ngaytao, d.ten as nguoisua, a.ngaysua as ngaysua, a.diengiai";
			query = query + " from khuvuc a, vung b, nhanvien c, nhanvien d";
			query = query + " where a.vung_fk=b.pk_seq and a.nguoitao = c.pk_seq and a.nguoisua = d.pk_seq";
		
			//Utility util = new Utility();
	    	if (vung.length()>0){
			//	query = query + " and a.vung_fk ='" + vung + "'";		
	    		query = query + " and a.vung_fk ='" + vung + "'";
	    	}
	  
	    	if(trangthai.length() > 0){
	    		query = query + " and a.trangthai = '" + trangthai + "'";	
	    	}
	    	query = query + " order by a.ten";
	    	System.out.println("cau lenh:"+ query);
	    	return query;
		}	
	boolean kiemtra(String sql)
	{dbutils db =new dbutils();
    	ResultSet rs = db.get(sql);
		try {//kiem tra ben san pham
		while(rs.next())
		{ if(rs.getString("num").equals("0"))
		   return true;
		}
		} catch(Exception e) {
		
			e.printStackTrace();
		}
		 return false;
	}

	private String Delete(String id)
	{	
		String msg ="";
		IKhuvucList obj = new KhuvucList();
		System.out.println("iddd::: "+id);
		dbutils db = new dbutils();
		String sql ="select count(khuvuc_fk) as num from nhaphanphoi where khuvuc_fk='"+ id + "'";
		try {
			if(kiemtra(sql)){
				 sql ="select count(khuvuc_fk) as num from giamsatbanhang where khuvuc_fk='"+ id + "'";
				if(kiemtra(sql)){
					sql = "select count(khuvuc_fk) as num from ASM_KHUVUC where khuvuc_fk='"+ id + "'";
					if (kiemtra(sql)) {
						db.update("delete from khuvuc where pk_seq = '" + id + "'");
					//db.shutDown();
					}
					else msg = "Không thể xóa khu vực đã tồn tại trong quản lí khu vực";
				}
				else msg = "Khu vực này đã tồn tại trong giám sát bán hàng rồi nên không thể xóa được";
			}
			else msg = "Khu vực này đã tồn tại trong nhà phân phối nên không thể xóa được";
			
			//SYN QUA ERP
			UtilitySyn.SynData(db, "KHUVUC", "KHUVUC", "PK_SEQ", id, "2", true);
		}catch(Exception e){
			e.printStackTrace();
			msg = "Lỗi trong quá trình xóa.";
		}finally {
			db.shutDown();
		}
		
		return msg;
		
	/*	ResultSet rs1 = db.get("select count(khuvuc_fk) as num from nhaphanphoi where khuvuc_fk='"+ id + "'");
		try{
			rs1.next();			
			if (rs1.getString("num").equals("0")){		
				db.update("delete from khuvuc where pk_seq = '" + id + "'");
				db.shutDown();
			}
		}catch(Exception e){}
		*/
	}




	private void ToExcel(HttpServletResponse response, IKhuvucList obj) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=KhuVuc.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("KhuVuc", k);
			sheet.addCell(new Label(0, 1, "KHU VỰC: ", new WritableCellFormat(cellTitle)));

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
			sheet.addCell(new Label(1, 4, "KHU VỰC", cellFormat));
			sheet.addCell(new Label(2, 4, "DIỄN GIẢI", cellFormatSpecical));
			sheet.addCell(new Label(3, 4, "MIỀN", cellFormat));
			sheet.addCell(new Label(4, 4, "TRẠNG THÁI", cellFormat));
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
			
			List<IKhuvuc> list = (List<IKhuvuc>)obj.getKvList();
			IKhuvuc kv = null;

			int stt = 0;
			for(int i = 0; i < list.size(); i++) 
			{
				kv = list.get(i);
				stt++;
				String type = "0";
				cformat = type.equals("1") ? cellFormat3 : cellFormat2;

				number = new Number(0, j, stt, cformat);
				sheet.addCell(number);
				label = new Label(1, j, kv.getTen(), cformat);
				sheet.addCell(label);
				label = new Label(2, j, kv.getDiengiai(), cformat);
				sheet.addCell(label);
				label = new Label(3, j, kv.getVungmien(), cformat);
				sheet.addCell(label);
				label = new Label(4, j, kv.getTrangthai().equals("Hoat dong") ? "Hoạt động" : "Ngưng hoạt động", cformat);
				sheet.addCell(label);
				label = new Label(5, j, kv.getNgaytao(), cformat);
				sheet.addCell(label);
				label = new Label(6, j, kv.getNguoitao(), cformat);
				sheet.addCell(label);
				label = new Label(7, j, kv.getNgaysua(), cformat);
				sheet.addCell(label);
				label = new Label(8, j, kv.getNguoisua(), cformat);
				sheet.addCell(label);

				j++;
			}
			w.write();
			w.close();
			for(int i = 0; i < list.size(); i++){
				kv = list.get(i);
			
			}
			obj.closeDB();
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
