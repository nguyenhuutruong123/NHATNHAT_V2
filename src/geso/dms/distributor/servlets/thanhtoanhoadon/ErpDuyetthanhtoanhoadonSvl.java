package geso.dms.distributor.servlets.thanhtoanhoadon;
import geso.dms.distributor.beans.thanhtoanhoadon.IDuyetthanhtoanhoadon;
import geso.dms.distributor.beans.thanhtoanhoadon.imp.Duyetthanhtoanhoadon;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ErpDuyetthanhtoanhoadonSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ErpDuyetthanhtoanhoadonSvl() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    Utility util = new Utility();
	    HttpSession session = request.getSession();
	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));

	    IDuyetthanhtoanhoadon ddmhBean = new Duyetthanhtoanhoadon();
   	    ddmhBean.setCtyId((String)session.getAttribute("congtyId"));
   	    ddmhBean.setUserId(userId);
   	    ddmhBean.setnppdangnhap(util.getIdNhapp(userId));
   	    ddmhBean.init();
		// Data is saved into session
		session.setAttribute("dtthdBean", ddmhBean);

		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDuyetThanhToanHoaDon.jsp";
   		response.sendRedirect(nextJSP);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

	    Utility util = new Utility();
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String ctyId = util.antiSQLInspection(request.getParameter("ctyId"));
	    
	    String khId = util.antiSQLInspection(request.getParameter("khId"));
	    
	    String nvId = util.antiSQLInspection(request.getParameter("nvId"));

	    String Id = util.antiSQLInspection(request.getParameter("Id"));
	    
	    String LoaiId = util.antiSQLInspection(request.getParameter("LoaiId"));
	    
	    String ngayghinhan = util.antiSQLInspection(request.getParameter("ngayghinhan"));
	    
	    String maTTHD = util.antiSQLInspection(request.getParameter("maTTHD"));
	    
	    String nccId = util.antiSQLInspection(request.getParameter("nccId"));
	    
	    String loaict = util.antiSQLInspection(request.getParameter("loaict"));
	    
	    String action = util.antiSQLInspection(request.getParameter("action")); 

	    
	    IDuyetthanhtoanhoadon ddmhBean = new Duyetthanhtoanhoadon();
	    ddmhBean.setCtyId((String)session.getAttribute("congtyId"));
	    ddmhBean.setUserId(userId);
	    ddmhBean.setnppdangnhap(util.getIdNhapp(userId));
	    
	    ddmhBean.setCtyId(ctyId);
   	    ddmhBean.setNccId(nccId);
   	    ddmhBean.setMaTTHD(maTTHD);
   	    ddmhBean.setNgayghinhan(ngayghinhan);
   	    ddmhBean.setNvId(nvId);
   	    ddmhBean.setKhId(khId);
   	    ddmhBean.setLoaiCT(loaict);
   	 
   	    ddmhBean.setRequest(request);
   	    if(action.equals("duyet")){
   	    	ddmhBean.Duyetmuahang(Id, LoaiId);
   	    }
   	    ddmhBean.init();
		// Data is saved into session
		session.setAttribute("dtthdBean", ddmhBean);
//		session.setAttribute("userId", userId);

		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDuyetThanhToanHoaDon.jsp";
   		response.sendRedirect(nextJSP);

	}

}
