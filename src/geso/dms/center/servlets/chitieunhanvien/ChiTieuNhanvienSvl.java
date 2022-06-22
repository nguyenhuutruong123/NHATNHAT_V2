	package geso.dms.center.servlets.chitieunhanvien;

import geso.dms.center.beans.chitieunhanvien.IChiTieuNhanvien;
import geso.dms.center.beans.chitieunhanvien.imp.ChiTieuNhanvien;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChiTieuNhanvienSvl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ChiTieuNhanvienSvl() {
		super();
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utility util = new Utility();

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
		ChiTieuNhanvien obj = new ChiTieuNhanvien();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);  
		obj.setUserId(userId);
		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		session.setAttribute("userId", userId); 
		session.setAttribute("userTen", gettenuser(userId));
		String action = util.getAction(querystring);
		 
		String idlist = util.getId(querystring); 
		try{
			obj.setID(idlist);
		}catch(Exception er){};

		String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvien.jsp";//default
		//System.out.println("vao day 1");

		if(action.equals("delete"))
		{
			obj.DeleteChiTieuLuongThuong();
			obj.setListChiTieu("");
			session.setAttribute("obj", obj);
		}
		else if(action.equals("chot"))
		{

			obj.ChotChiTieuLuongThuong();
			obj.setListChiTieu("");
			session.setAttribute("obj", obj);
		}
		else if(action.equals("new")){// circumstance add new 
			
			nextJSP= request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";
			
		}
		else if(action.equals("unchot")){// circumstance add new 
			obj.setID(idlist);			
			obj.UnChotChiTieuLuongThuong();							
			obj.setListChiTieu("");
			session.setAttribute("obj", obj);
		}
		else{
			session.setAttribute("thang",0);
			session.setAttribute("nam",0);
			obj.setListChiTieu("");
			session.setAttribute("obj", obj);
		}
		System.out.println("page: "+nextJSP);
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utility util = new Utility();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String username=util.antiSQLInspection(request.getParameter("userTen"));

		String thangtk=util.antiSQLInspection(request.getParameter("thang"));
		String namtk=util.antiSQLInspection(request.getParameter("nam"));
		String action =request.getParameter("action");
		//System.out.println("Action: " + action);  
		IChiTieuNhanvien chitieu=new ChiTieuNhanvien();
		chitieu.setUserId(userId);
		HttpSession session=request.getSession();
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		String loaict=request.getParameter("loai");
		chitieu.setLoai(loaict);
		session.setAttribute("nam",Integer.parseInt(namtk));
		session.setAttribute("thang",Integer.parseInt(thangtk));

		Utility Ult = new Utility(); 
		if(action.equals("search"))
		{
			String  sql_getdata="";

			sql_getdata="SELECT   c.trangthai,C.PK_SEQ, C.THANG, C.NAM, C.DIENGIAI, C.NGAYTAO, C.NGAYSUA,NT.TEN AS NGUOITAO, "+ 
			"NS.TEN AS NGUOISUA FROM ChiTieuNhanVien AS C INNER JOIN "+
			"dbo.NHANVIEN AS NT ON C.NGUOITAO = NT.PK_SEQ INNER JOIN dbo.NHANVIEN AS NS ON C.NGUOISUA = NS.PK_SEQ " +
			"where 1=1 ";

			String where ="";
			if(!thangtk.equals("0")){
				where=" and C.THANG="+ thangtk + " ";	
			}
			if(!namtk.equals("0")){
				where=where + "and C.NAM="+namtk +" ";
			}
			if(where !=""){//if have search condition
				sql_getdata=sql_getdata+ where;	
			}
			sql_getdata +="\n order by  C.NAM desc, C.THANG desc ";
			chitieu.setListChiTieu(sql_getdata);
			session.setAttribute("obj", chitieu);
			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNhanvien.jsp";
			response.sendRedirect(nextJSP);
		}
		else if(action.equals("new"))
		{
			IChiTieuNhanvien obj = new ChiTieuNhanvien();
			obj.setID("");
			obj.setIsDisplay("0");
			obj.setUserId(userId);
			session.setAttribute("userId",userId);
			session.setAttribute("userTen", username);
			session.setAttribute("obj", chitieu);
			session.setAttribute("check", "0");
			String	nextJSP= request.getContextPath() + "/pages/Center/ChiTieuNhanvienUpdate.jsp";
			response.sendRedirect(nextJSP);
		}
	}

}
