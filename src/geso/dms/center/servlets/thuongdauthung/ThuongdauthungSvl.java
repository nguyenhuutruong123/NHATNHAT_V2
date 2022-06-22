package geso.dms.center.servlets.thuongdauthung;

import geso.dms.center.beans.thuongdauthung.IThuongdauthung;
import geso.dms.center.beans.thuongdauthung.imp.Thuongdauthung;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThuongdauthungSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ThuongdauthungSvl() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String gettenuser(String userId_){
		dbutils db=new dbutils();
		String sql_getnam="select ten from nhanvien where pk_seq="+ userId_;
		ResultSet rs_tenuser=db.get(sql_getnam);
		if(rs_tenuser!= null){
			try{
				while(rs_tenuser.next()){
					return rs_tenuser.getString("ten");
				}
			}catch(Exception er){
				return "";
			}
		}
		return "";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utility util = new Utility();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		IThuongdauthung obj = new Thuongdauthung();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);  
		obj.setUserId(userId);
		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		session.setAttribute("userId", userId); 
		session.setAttribute("userTen", gettenuser(userId));
		String action = util.getAction(querystring);
		//out.println(action);   
		String idlist = util.getId(querystring); 
		try{
			obj.setID(Double.parseDouble(idlist));
		}catch(Exception er){};

		obj.createRs();
		String nextJSP = request.getContextPath() + "/pages/Center/Thuongdauthung.jsp";//default
		
		if(action.equals("delete")){
			obj.DeleteLuongKhac();
			obj.setLuongkhacRs("");
			session.setAttribute("obj", obj);
		}else if(action.equals("chot")){
			System.out.println("heheheheeeeeeeeeeeeeeee");
			obj = new Thuongdauthung(idlist);	
			obj.chotLuongKhac();
			obj.setLuongkhacRs("");
			session.setAttribute("obj", obj);
		}
		else if(action.equals("new")){// circumstance add new 
			nextJSP= request.getContextPath() + "/pages/Center/ThuongdauthungUpdate.jsp";
		}
		else if(action.equals("unchot")){// circumstance add new 
			obj.setID(Double.parseDouble(idlist));			
			//obj.UnChotChiTieu_Sec();	
			obj.UnchotLuongKhac();
			obj.setLuongkhacRs("");
			session.setAttribute("obj", obj);
		}
		else{
			session.setAttribute("thang",0);
			session.setAttribute("nam",0);
			obj.setLuongkhacRs("");
			session.setAttribute("obj", obj);
		}
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Utility util = new Utility();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String username=util.antiSQLInspection(request.getParameter("userTen"));

		String thangtk=util.antiSQLInspection(request.getParameter("thang"));
		String namtk=util.antiSQLInspection(request.getParameter("nam"));
		String action =request.getParameter("action");

		IThuongdauthung thuongdauthung =new Thuongdauthung();
		thuongdauthung.setUserId(userId);
		HttpSession session=request.getSession();

		session.setAttribute("nam",Integer.parseInt(namtk));
		session.setAttribute("thang",Integer.parseInt(thangtk));

		Utility Ult = new Utility(); 
		if(action.equals("search"))
		{
			String  sql_getdata="";

			sql_getdata="SELECT    c.trangthai,C.PK_SEQ, C.tungay, C.denngay, C.DIENGIAI,  C.NGAYTAO, C.NGAYSUA,NT.TEN AS NGUOITAO "+ 
			",NS.TEN AS NGUOISUA FROM thuongdauthung AS C INNER JOIN "+
			"dbo.NHANVIEN AS NT ON C.NGUOITAO = NT.PK_SEQ INNER JOIN dbo.NHANVIEN AS NS ON C.NGUOISUA = NS.PK_SEQ "; 
			/*"where 1=1 and c.tructhuoc_fk = "+thuongdauthung.getTructhuocId()+" ";*/
	
			String where ="";
			if(!thangtk.equals("0")){
				where=" and month(C.denngay)="+ thangtk + " ";	
			}
			if(!namtk.equals("0")){
				where=where + "and year(C.denngay)="+namtk +" ";
			}

			if(where !=""){//if have search condition
				sql_getdata=sql_getdata+ where;	
			}
			sql_getdata += "\n order by  C.PK_SEQ desc";

			thuongdauthung.setLuongkhacRs(sql_getdata);
			session.setAttribute("obj", thuongdauthung);
			String nextJSP = request.getContextPath() + "/pages/Center/Thuongdauthung.jsp";//default
			response.sendRedirect(nextJSP);
		}
		else if(action.equals("new"))
		{
			IThuongdauthung obj = new Thuongdauthung();
			obj.setUserId(userId);
			obj.createRs();
			session.setAttribute("userId",userId);
			session.setAttribute("userTen", username);
			session.setAttribute("obj", obj);
			session.setAttribute("check", "0");
			String	nextJSP= request.getContextPath() + "/pages/Center/ThuongdauthungUpdate.jsp";
			response.sendRedirect(nextJSP);

		}
	}

}
