package geso.dms.center.servlets.vitricuahang;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import geso.dms.center.db.sql.*;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.hangcuahang.IHangcuahangList;
import geso.dms.center.beans.vitricuahang.*;
import geso.dms.center.beans.vitricuahang.imp.*;

 public class VitricuahangSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
		private static final long serialVersionUID = 1L;
		
		PrintWriter out;
		
	    public VitricuahangSvl()
	    {
	        super();
	    }

		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			IVitricuahangList obj;
			
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
		    obj = new VitricuahangList();    
		    String querystring = request.getQueryString();
		    String userId = util.getUserId(querystring);
		    out.println(userId);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String action = util.getAction(querystring);
		    out.println(action);
		    
		    String vtchId = util.getId(querystring);

		    if (action.equals("delete")){	   		  	    	
		    	obj.setMsg(Delete(vtchId));
		    	out.print(vtchId);
		    }
		   	
		  
		    obj.setUserId(userId);
		    obj.init("");
			session.setAttribute("obj", obj);
					
			String nextJSP = request.getContextPath() + "/pages/Center/ViTriCuaHang.jsp";
			response.sendRedirect(nextJSP);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
		{
			
			IVitricuahangList obj;
			
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
		    
		    String action = request.getParameter("action");
		    if (action == null){
		    	action = "";
		    }
		    
		        
		    obj = new VitricuahangList();
		    
		    if (action.equals("new")){
		    	// Empty Bean for distributor
		    	IVitricuahang vtchBean = (IVitricuahang) new Vitricuahang("");
		    	vtchBean.setUserId(userId);
		    	// Save Data into session
		    	session.setAttribute("vtchBean", vtchBean);
	    		
	    		String nextJSP = request.getContextPath() + "/pages/Center/ViTriCuaHangNew.jsp";
	    		response.sendRedirect(nextJSP);
	    		
		    }
		    if (action.equals("search")){
		    	String search = getSearchQuery(request,obj);
		    	
		    	//obj = new VitricuahangList(search);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		    		
	    		response.sendRedirect(request.getContextPath() + "/pages/Center/ViTriCuaHang.jsp");	    	
		    	
		    }
		    
		    if (action.equals("excel"))
			{
				ToExcel(response, obj,  getSearchQuery(request,obj));
			}
		    
		}
		
		private String getSearchQuery(HttpServletRequest request,IVitricuahangList obj){
			   // PrintWriter out = response.getWriter();
			
			Utility util = new Utility();
							
				String vitri = util.antiSQLInspection(request.getParameter("ViTriCuaHang"));
		    	if ( vitri == null)
		    		vitri = "";
		    	obj.setVitricuahang(vitri);
		    	
		    	String diengiai = util.antiSQLInspection(request.getParameter("DienGiai"));
		    	if (diengiai == null)
		    		diengiai = "";    	
		    	obj.setDiengiai(diengiai);
		    	
		    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
		    	if (trangthai == null)
		    		trangthai = "";    	
			
		    	if (trangthai.equals("2"))
		    		trangthai = "";
		    	
		    	obj.setTrangthai(trangthai);
		    	
		    	String query;
		    	query = "select a.pk_seq as id, a.vitri, a.diengiai, a.trangthai, a.ngaytao, b.ten as nguoitao, a.ngaysua, b.ten as nguoisua"; 
				query = query + " from vitricuahang a, nhanvien b, nhanvien c where a.nguoitao = b.pk_seq and a.nguoisua = c.pk_seq";
				//geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		    	if (vitri.length()>0){
		    		
					query = query + " and upper(dbo.ftBoDau(a.vitri)) like upper(N'%" + util.replaceAEIOU(vitri) + "%')";
					
		    	}
		    	
		    	if (diengiai.length()>0){
					query = query + " and upper(dbo.ftBoDau(a.diengiai)) like upper(N'%" + util.replaceAEIOU(diengiai) + "%')";
					
		    	}
		  
		    	if(trangthai.length() > 0){
		    		query = query + " and a.trangthai = '" + trangthai + "'";
		    		
		    	}
		    	query = query + "  order by vitri";
		    	return query;
			}	
		
		private String Delete(String id)
		{
			dbutils db = new dbutils();
			
			IVitricuahangList obj = new VitricuahangList();
			
			ResultSet rs1 = db.get("select count(vtch_fk) as num from khachhang where vtch_fk='"+ id + "'");
			try{
				rs1.next();			
				if (rs1.getString("num").equals("0")){
					db.update("delete from vitricuahang where pk_seq = '" + id + "'");
				}
				else
					return  "V??? tr?? kh??ch h??ng ???? t???n t???i trong kh??ch h??ng. Kh??ng th??? x??a";
			}catch(Exception e){
				e.printStackTrace();
			}
			finally {
				db.shutDown();
			}
			return "";
		}
		
		private void ToExcel(HttpServletResponse response, IVitricuahangList obj, String query) throws IOException
		{
			OutputStream out = null;
			try
			{
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "attachment; filename=ViTriCuaHang.xls");
				WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

				int k = 0;
				int j = 5;

				WritableSheet sheet = null;

				WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
				cellTitle.setColour(Colour.BLACK);
				cellTitle.setBoldStyle(WritableFont.BOLD);

				sheet = w.createSheet("ViTriCuaHang", k);
				sheet.addCell(new Label(0, 1, "V??? tr?? c???a h??ng : ", new WritableCellFormat(cellTitle)));

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
				sheet.addCell(new Label(1, 4, "V??? TR?? C???A H??NG", cellFormat));
				sheet.addCell(new Label(2, 4, "DI???N GI???I", cellFormatSpecical));
				sheet.addCell(new Label(3, 4, "TR???NG TH??I", cellFormat));
				sheet.addCell(new Label(4, 4, "NG??Y T???O", cellFormat));
				sheet.addCell(new Label(5, 4, "NG?????I T???O", cellFormat));
				sheet.addCell(new Label(6, 4, "NG??Y S???A", cellFormat));
				sheet.addCell(new Label(7, 4, "NG?????I S???A", cellFormat));

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
					label = new Label(1, j, rs.getString("vitri"), cformat);sheet.addCell(label);
					label = new Label(2, j, rs.getString("diengiai"), cformat);sheet.addCell(label);
					label = new Label(3, j, rs.getInt("trangthai") == 0 ? "Ng??ng ho???t ?????ng" : "Ho???t ?????ng", cformat);sheet.addCell(label);
					label = new Label(4, j, rs.getString("ngaytao"), cformat);sheet.addCell(label);
					label = new Label(5, j, rs.getString("NguoiTao"), cformat);sheet.addCell(label);
					label = new Label(6, j, rs.getString("NgaySua"), cformat);sheet.addCell(label);
					label = new Label(7, j, rs.getString("NguoiSua"), cformat);sheet.addCell(label);

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
 
 
