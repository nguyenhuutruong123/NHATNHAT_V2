package geso.dms.center.servlets.khuyenmaidacbiet;

import geso.dms.center.beans.ctkhuyenmai.imp.Ctkhuyenmai;
import geso.dms.center.beans.khuyenmaidacbiet.IKhuyenMaiDacBiet;
import geso.dms.center.beans.khuyenmaidacbiet.IKhuyenMaiDacBietList;
import geso.dms.center.beans.khuyenmaidacbiet.imp.KhuyenMaiDacBiet;
import geso.dms.center.beans.khuyenmaidacbiet.imp.KhuyenMaiDacBietList;
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

import com.extentech.formats.XLS.UsrExcl;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class KhuyenMaiDacBietSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	private int items = 50; 
	//String userId;
	//String nppId;

	private int splittings = 20;

	public KhuyenMaiDacBietSvl() 
	{
		super();
	}

	private void settingPage(IKhuyenMaiDacBietList obj) {
		Utility util = new Utility();
		if(getServletContext().getInitParameter("items") != null){
			String i = getServletContext().getInitParameter("items").trim();
			if(util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if(getServletContext().getInitParameter("splittings") != null){
			String i = getServletContext().getInitParameter("splittings").trim();
			if(util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

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
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			this.out  = response.getWriter();


			session.setMaxInactiveInterval(30000);

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			//out.println(userId);
			System.out.println();
			Date date = new Date();
			System.out.println("DonhangSvl user :" + userId + "  ,sessionId: " + session.getId() );

			if (userId.length()==0)
				userId = request.getParameter("userId");
	



			String id = request.getParameter("id")  == null ? "":  request.getParameter("id") ;
			String action = request.getParameter("action")  == null ? "":  request.getParameter("action") ;
			System.out.println("ACTION LA: " + action);

			IKhuyenMaiDacBietList obj = new KhuyenMaiDacBietList();	
			obj.setUserId(userId);
			String msg = "";
			
			if(Utility.parseInt(action) == Utility.XOA)
			{
				msg = Xoa( obj,id); 
			}
			else
				if(Utility.parseInt(action) == Utility.CHOT)
				{
					msg = Chot( obj,id); 
				}
			
			
			
			settingPage(obj);
			obj.init(null);
			obj.setMsg(msg);
			session.setAttribute("obj", obj);
			session.setAttribute("khId", "");
			
			String nextJSP = request.getContextPath() + "/pages/Center/KhuyenMaiDacBiet.jsp";
			response.sendRedirect(nextJSP);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");


			session.setMaxInactiveInterval(30000);

			userId = request.getParameter("userId");
			System.out.println();
			System.out.println("DonhangSvl user :" + userId + "  ,sessionId: " + session.getId()) ;
			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}

			if (action.equals("new"))
			{
				// Empty Bean for distributor
				IKhuyenMaiDacBiet dhBean = (IKhuyenMaiDacBiet) new KhuyenMaiDacBiet("");
				dhBean.setUserId(userId);


				// Save Data into session
				session.setAttribute("dhBean", dhBean);//truyen vao session mot doi tuong donhang co gia tri rong khi khoi tao de ben form don hang nhan dc
			

				String nextJSP = request.getContextPath() + "/pages/Center/KhuyenMaiDacBietUpdate.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				IKhuyenMaiDacBietList obj;
				obj = new KhuyenMaiDacBietList();
				settingPage(obj);

				obj.setUserId(userId);

				if(action.equals("search"))
				{
					obj.setUserId(userId);

					obj.init(request);
					session.setAttribute("userId", userId);
					session.setAttribute("obj", obj);

					response.sendRedirect(request.getContextPath() + "/pages/Center/KhuyenMaiDacBiet.jsp");	    		    	
				}
				else if(action.equals("view") || action.equals("next") || action.equals("prev"))
				{
					obj.setUserId(userId);
					
					obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

					obj.init(request);
					obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
					session.setAttribute("obj", obj);
					response.sendRedirect(request.getContextPath() + "/pages/Center/KhuyenMaiDacBiet.jsp");
				}
				
				
				 
			}
		}
	}


	private String Xoa(IKhuyenMaiDacBietList obj,String dhId) 
	{
		try {
			obj.getDb().getConnection().setAutoCommit(false);
		
			if(Ctkhuyenmai.CTKM_phat_sinh_DH(obj.getDb(),dhId))
			{
				Utility.rollback_throw_exception(obj.getDb());
				return "Đã phát sinh  ĐH  không thể xóa";
			}
			
			String query = " delete CTKM_NPP_SANPHAM where ctkm_fk = " + dhId;
			if(!obj.getDb().update(query))
			{
				Utility.rollback_throw_exception(obj.getDb());
				return "Không thể xóa chi tiết";
			}
			query = " delete ctkhuyenmai where trangthai = 0 and pk_seq = " + dhId;
			if(obj.getDb().updateReturnInt(query)!=1)
			{
				Utility.rollback_throw_exception(obj.getDb());
				return "Không thể xóa CTKM";
			}
			
			obj.getDb().getConnection().commit();
			return "Xóa thành công";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utility.rollback_throw_exception(obj.getDb());
			return "Exception:" + e.getMessage();
		}
	}
	private String Chot(IKhuyenMaiDacBietList obj,String dhId) 
	{
		try {
			obj.getDb().getConnection().setAutoCommit(false);
		
			
			String query = " update ctkhuyenmai set trangthai = 1 where trangthai = 0 and pk_seq = " + dhId;
			if(obj.getDb().updateReturnInt(query)!=1)
			{
				Utility.rollback_throw_exception(obj.getDb());
				return "Không thể chuyển trạng thái CTKM";
			}
			
			obj.getDb().getConnection().commit();
			return "Chốt thành công";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Utility.rollback_throw_exception(obj.getDb());
			return "Exception:" + e.getMessage();
		}
	}

	

	
	
	
	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");

		return arr[2] + "/" + arr[1] + "/" + arr[0];
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}

