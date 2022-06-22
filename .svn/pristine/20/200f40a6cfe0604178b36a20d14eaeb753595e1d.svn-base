package geso.dms.center.servlets.khott;

import geso.dms.center.beans.khott.IKhoTT;
import geso.dms.center.beans.khott.imp.KhoTT;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KhoTTSvl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public KhoTTSvl() {
    	
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utility util = new Utility();
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");

    	PrintWriter out = response.getWriter();
    	HttpSession session = request.getSession();

    	String querystring = request.getQueryString();

    	String userId = util.getUserId(querystring);

    	if (userId.length()==0)
    		userId = util.antiSQLInspection(request.getParameter("userId"));

    	String action = util.getAction(querystring);	    
    	String khoId = util.getId(querystring);

    	if (action.equals("delete")){	   		
    		KhoTT obj = new KhoTT();

    		obj.setId(khoId);
    		obj.Delete();
    		obj.setListkho("");
    		session.setAttribute("obj", obj);
    		session.setAttribute("userId", userId);
    		String nextJSP = request.getContextPath() + "/pages/Center/KhoTT.jsp";
    		response.sendRedirect(nextJSP);
    	}
    	else if (action.equals("update")){
    		KhoTT obj = new KhoTT(khoId);
    		session.setAttribute("obj", obj);
    		session.setAttribute("userId", userId);
    		String nextJSP = request.getContextPath() + "/pages/Center/KhoTTUpdate.jsp";
    		response.sendRedirect(nextJSP);
    	}
    	else if (action.equals("display")){

    	}
    	else{
    		KhoTT obj = new KhoTT();
    		obj.setListkho("");
    		//Data object is saved into session
    		session.setAttribute("obj", obj);

    		// userId is saved into session
    		session.setAttribute("userId", userId);
    		session.setAttribute("tungay", "");
    		session.setAttribute("denngay", "");
    		String nextJSP = request.getContextPath() + "/pages/Center/KhoTT.jsp";
    		response.sendRedirect(nextJSP);
    	}
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Utility util = new Utility();
    	request.setCharacterEncoding("UTF-8");
    	response.setCharacterEncoding("UTF-8");
    	response.setContentType("text/html; charset=UTF-8");
    	HttpSession session = request.getSession();

    	String userId = util.antiSQLInspection(request.getParameter("userId"));

    	if (request.getParameter("action").equals("search")){
        	KhoTT obj = new KhoTT();
    		String search = getSearchQuery(obj, request);
    		obj.setListkho(search);
    		session.setAttribute("obj", obj);
    		session.setAttribute("userId", userId);
    		response.sendRedirect(request.getContextPath() + "/pages/Center/KhoTT.jsp");
    	}

    	if (request.getParameter("action").equals("new")){
    		IKhoTT khoBean = new KhoTT();
    		session.setAttribute("obj", khoBean);
    		session.setAttribute("userId", userId);
    		String nextJSP = request.getContextPath() + "/pages/Center/KhoTTNew.jsp";
    		response.sendRedirect(nextJSP);
    	}
    }
	
	private String getSearchQuery(KhoTT obj, ServletRequest request){
		Utility util = new Utility();
		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null)
			ten = "";
		obj.setTen(ten);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));   	
		if (trangthai == null)
			trangthai = "";    		

		String query = "\n SELECT K.PK_SEQ, K.TEN, K.NGAYTAO, K.NGAYSUA, K.TRANGTHAI, K.MA, NT.TEN AS NGUOITAO, NS.TEN AS NGUOISUA " +
		"\n FROM ERP_KHOTT K INNER JOIN  dbo.NHANVIEN NT ON K.NGUOITAO = NT.PK_SEQ " +
		"\n INNER JOIN dbo.NHANVIEN AS NS ON K.NGUOISUA = NS.PK_SEQ WHERE  K.PK_SEQ > 0 ";

		if (ten.length()>0){
			query = query + " and upper(k.ten) like upper(N'%" + ten + "%')";
		}

		if (trangthai.length() > 0){
			query = query + " and k.trangthai = '" + trangthai + "'";
		}
		
		System.out.print("getSearchQuery: " + query);
		return query;
	}
}
