package geso.dms.center.servlets.nhomkhuyenmai;

import geso.dms.center.beans.nhomkhuyenmai.INhomkhuyenmai;
import geso.dms.center.beans.nhomkhuyenmai.INhomkhuyenmaiList;
import geso.dms.center.beans.nhomkhuyenmai.imp.Nhomkhuyenmai;
import geso.dms.center.beans.nhomkhuyenmai.imp.NhomkhuyenmaiList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

public class NhomkhuyenmaiSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	 static final long serialVersionUID = 1L;
	 PrintWriter out;
	 HttpServletRequest request;
	 HttpServletResponse response;
	 INhomkhuyenmaiList obj;
	 dbutils db;
	 public NhomkhuyenmaiSvl() {
		super();
	 }   	
			
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			    
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
	    obj = new NhomkhuyenmaiList();
			    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);
			    
	    if (userId.length()==0)
	    	userId = request.getParameter("userId");
			    
		    String action = util.getAction(querystring);
		    out.println(action);
			    
		    String nkmId = util.getId(querystring);

			if (action.equals("delete")){				
			   	Delete(nkmId);
		    }

		  //  List<INhomkhuyenmai> nkmlist = new ArrayList<INhomkhuyenmai>(); 
			    
		   // getNkmBeanList(nkmlist);
			    
			// Save data into session
			//obj.setNkmList(nkmlist);
			Utility Ult = new Utility();
			String sql = "select a.pk_seq, a.ten, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao," +
    		" c.ten as nguoisua from nhomsanpham a, nhanvien b, nhanvien c" +
    		" where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='1' and a.pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in "+ Ult.quyen_sanpham(userId)+")";
						
			    ResultSet Dsnkm = db.get(sql);
			    obj.setDsnkm(Dsnkm);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);

			String nextJSP = request.getContextPath() + "/pages/Center/NhomKhuyenMai.jsp";
			response.sendRedirect(nextJSP);
			    
		}  	

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			Utility util=new Utility();
		    String userId = util.antiSQLInspection( request.getParameter("userId"));

		    this.obj = new NhomkhuyenmaiList();
		    this.db = new dbutils();
			    
			String action = request.getParameter("action");
			if (action == null){
			   	action = "";
			}
			
			String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
	    	if (diengiai == null)
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
	    	String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));   	
	    	if (trangthai == null)
	    		trangthai = "";    	
		
	    	if (trangthai.equals("2"))
	    		trangthai = "";
	    	obj.setTrangthai(trangthai);
	    	String query = "select a.pk_seq, a.ten, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from nhomsanpham a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='1'";
	    	if (diengiai.length()>0){
				query = query + " and upper(dbo.ftBoDau(a.diengiai)) like upper(N'%" + util.replaceAEIOU(diengiai) + "%')";
				
	    	}
	    	

	    	if (tungay.length() > 0) {
	    		query = query + " and a.ngaytao >= '" + tungay + "'";
	    		
	    	}
	    	
	    	if (denngay.length() > 0) {
	    		query = query + " and a.ngaytao <= '" + denngay + "'";
	    		
	    	}
	    	
	    	if(trangthai.length() > 0){
	    		query = query + " and a.trangthai = '" + trangthai + "'";
	    		
	    	}
	    	query = query +" and a.pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in "+ Ult.quyen_sanpham(userId) +")";
          System.out.println(query);
			// Perform searching. Each Nhomkhuyenmai is saved into Nhomkhuyenmai
			if (action.equals("new")){
			   	// Empty Bean for distributor
			   	INhomkhuyenmai nkmBean = (INhomkhuyenmai) new Nhomkhuyenmai();
			    	
			    nkmBean.UpdateRS();
			    // Save Data into session
		    	session.setAttribute("nkmBean", nkmBean);
		    	session.setAttribute("userId", userId);
		    		

		    	String nextJSP = request.getContextPath() + "/pages/Center/NhomKhuyenMaiNew.jsp";
		    	response.sendRedirect(nextJSP);
		    		
			 }
			else if (action.equals("search")){
			    	
			   	 ResultSet Dsnkm = db.get(query);
			   	 obj.setDsnkm(Dsnkm);
				session.setAttribute("obj", obj);

			    session.setAttribute("userId", userId);
				    		
			    response.sendRedirect(request.getContextPath() + "/pages/Center/NhomKhuyenMai.jsp");
			}
			else if(action.equals("toExcel"))
			{
				ToExcel(response,obj,query);
			}
			 else
			{ obj = new NhomkhuyenmaiList();
			
			ResultSet Dsnkm = db.get("select a.pk_seq, a.ten, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from nhomsanpham a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='1' and a.pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in "+ Ult.quyen_sanpham(userId)+")");
			   	 obj.setDsnkm(Dsnkm);
				session.setAttribute("obj", obj);

			    session.setAttribute("userId", userId);
				    		
			    response.sendRedirect(request.getContextPath() + "/pages/Center/NhomKhuyenMai.jsp");
			}
		}

		private void Delete(String nkmId){
			    			    
			    String command;
				command = "delete from nhomsanpham_sanpham where nsp_fk ='" + nkmId + "'";
				//System.out.println("Delete 1: "+command);
				db.update(command);
				
			    command = "delete from nhomsanpham where pk_seq ='" + nkmId + "'";
			    //System.out.println("Delete 2: "+command);
			   	db.update(command);
				
			}

			
			private void  getNkmBeanList(List<INhomkhuyenmai> nkmlist,String userId){	
				 Utility Ult = new Utility(); 	
			   	String query = "select a.pk_seq, a.ten, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from nhomsanpham a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='1' and a.pk_seq in (select nsp_fk from NHOMSANPHAM_SANPHAM where sp_fk in "+ Ult.quyen_sanpham(userId)+") order by pk_seq";
			   	ResultSet rs = db.get(query);
			   	try{	
			   		String[] param = new String[11];
		    		INhomkhuyenmai nkmBean;
		    		while (rs.next()){	    			
						param[0]= rs.getString("pk_seq");
						param[1]= rs.getString("ten");	
						param[2]= rs.getString("diengiai");
						param[3]= rs.getString("trangthai");
						param[4]= rs.getString("ngaytao");
						param[5]= rs.getString("ngaysua");
						param[6]= rs.getString("nguoitao");
						param[7]= rs.getString("nguoisua");			
						
						nkmBean = new Nhomkhuyenmai(param);					
						nkmlist.add(nkmBean);						
		    		}   
		    		if(rs!=null){
		    			rs.close();
		    		}
			   	}catch(Exception e){}
			}
			
			private List<INhomkhuyenmai> getNkmListS(String query){  	
				    
				ResultSet rs = db.get(query);
				List<INhomkhuyenmai> nkmlist = new ArrayList<INhomkhuyenmai>();			
								
				INhomkhuyenmai nkmBean;
				String[] param = new String[10];
				try{
					while(rs.next()){
						param[0]= rs.getString("pk_seq");
						param[1]= rs.getString("ten");	
						param[2]= rs.getString("diengiai");
						param[3]= rs.getString("trangthai");
						param[4]= rs.getString("ngaytao");
						param[5]= rs.getString("ngaysua");
						param[6]= rs.getString("nguoitao");
						param[7]= rs.getString("nguoisua");			
						nkmBean = new Nhomkhuyenmai(param);
						nkmlist.add(nkmBean);			
					}
					if(rs!=null){
		    			rs.close();
		    		}
				}catch(Exception e){}
				return nkmlist;
			}

			private String getSearchQuery(String userId){
//			    PrintWriter out = response.getWriter();
				geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
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
		    	String query = "select a.pk_seq, a.ten, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from nhomsanpham a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='1'";
		    	
		    	
		    	if (diengiai.length()>0){
					query = query + " and upper(dbo.ftBoDau(a.diengiai)) like upper(N'%" + util.replaceAEIOU(diengiai) + "%')";
					
		    	}
		    	

		    	if (tungay.length() > 0) {
		    		query = query + " and a.ngaytao >= '" + tungay + "'";
		    		
		    	}
		    	
		    	if (denngay.length() > 0) {
		    		query = query + " and a.ngaytao <= '" + denngay + "'";
		    		
		    	}
		    	
		    	if(trangthai.length() > 0){
		    		query = query + " and a.trangthai = '" + trangthai + "'";
		    		
		    	}
              System.out.println(query);
		    	return query;
			}
			private void ToExcel(HttpServletResponse response, INhomkhuyenmaiList obj,String query) throws IOException
			{
				OutputStream out = null;
				try
				{
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=NhomKhuyenMai.xls");
					WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

					int k = 0;
					int j = 5;
				
					WritableSheet sheet=null;
					
					WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
					cellTitle.setColour(Colour.BLACK);
					cellTitle.setBoldStyle(WritableFont.BOLD);
					
					sheet = w.createSheet("NhomKhuyenMai", k);
					sheet.addCell(new Label(0, 1, "NHÓM KHUYẾN MẠI: ",new WritableCellFormat(cellTitle)));

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
					
					sheet.addCell(new Label(0, 4, "TÊN NHÓM", cellFormat));
					sheet.addCell(new Label(1, 4, "DIỄN GIẢI", cellFormatSpecical));
					sheet.addCell(new Label(2, 4, "TRẠNG THÁI", cellFormat));
					sheet.addCell(new Label(3, 4, "NGÀY TẠO", cellFormat));
					sheet.addCell(new Label(4, 4, "NGƯỜI TẠO", cellFormat));
					sheet.addCell(new Label(5, 4, "NGÀY SỬA", cellFormat));
					sheet.addCell(new Label(6, 4, "NGƯỜI SỬA", cellFormat));
					sheet.setRowView(100, 4);
					
					sheet.setColumnView(0, 15);
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
					
					WritableCellFormat cellFormat2 = new WritableCellFormat(new  jxl.write.NumberFormat("#,###,###"));

					cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
					cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
					cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
					cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
					
					WritableCellFormat cellFormat3 = new WritableCellFormat(new  jxl.write.NumberFormat("#,###,###"));
					cellFormat3.setBackground(jxl.format.Colour.YELLOW);
					cellFormat3.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
					cellFormat3.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
					cellFormat3.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
					cellFormat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
					
					WritableCellFormat cformat =null;
					Label label;
					
					while (rs.next())
					{
						String type = "0";
						cformat = type.equals("1") ? cellFormat3 : cellFormat2;
						
						String trangthai=rs.getString("TrangThai");
						if(trangthai.equals("1"))
							trangthai="Hoạt động ";
						else if(trangthai.equals("0"))
							trangthai="Ngưng hoạt động";
						
						label = new Label(0, j, rs.getString("Ten"), cformat);sheet.addCell(label);
						label = new Label(1, j, rs.getString("DienGiai"), cformat);sheet.addCell(label);
						label = new Label(2, j, trangthai, cformat);sheet.addCell(label);
						label = new Label(3, j, rs.getString("NgayTao"), cformat);sheet.addCell(label);
						label = new Label(4, j, rs.getString("NguoiTao"), cformat);sheet.addCell(label);
						label = new Label(5, j, rs.getString("NgaySua"), cformat);sheet.addCell(label);
						label = new Label(6, j, rs.getString("NguoiSua"), cformat);sheet.addCell(label);
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
