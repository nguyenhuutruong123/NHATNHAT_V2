package geso.dms.distributor.servlets.tamung;
import geso.dms.distributor.beans.tamung.IDuyettamung;
import geso.dms.distributor.beans.tamung.imp.Duyettamung;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Erp_DuyettamungSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public Erp_DuyettamungSvl() {
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

   	    IDuyettamung ddmhBean = new Duyettamung();
   	    ddmhBean.setCongtyId((String)session.getAttribute("congtyId"));
   	    ddmhBean.setUserId(userId);
   	    
   	    ddmhBean.init();
		// Data is saved into session
		session.setAttribute("ddmhBean", ddmhBean);

		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDuyetDeNghiTamUng.jsp";
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
	    
	    String dvthId = util.antiSQLInspection(request.getParameter("dvthId"));
	    
	    String lspId = util.antiSQLInspection(request.getParameter("lspId"));

	    String Id = util.antiSQLInspection(request.getParameter("Id"));
	    
	    String ngaymua = util.antiSQLInspection(request.getParameter("ngaymua"));
	    
	    String maDMH = util.antiSQLInspection(request.getParameter("maDMH"));
	    
	    String nccId = util.antiSQLInspection(request.getParameter("nccId"));
	    
	    String action = util.antiSQLInspection(request.getParameter("action")); 
	    
	    String loaicap = util.antiSQLInspection(request.getParameter("loaicap")); 
	    	
	    System.out.println("DvthId:" + dvthId);
	    System.out.println("CtyId:" + ctyId);
	    
	    IDuyettamung ddmhBean = new Duyettamung();
	    ddmhBean.setCongtyId((String)session.getAttribute("congtyId"));
	    ddmhBean.setUserId(userId);
	    ddmhBean.setCtyId((String)session.getAttribute("congtyId"));
   	    ddmhBean.setDvthId(dvthId);
   	    ddmhBean.setNccId(nccId);
   	    ddmhBean.setMaDMH(maDMH);
   	    ddmhBean.setNgaymua(ngaymua);
   	    ddmhBean.setLspId(lspId);
   	    
   	    String lydoxoa = request.getParameter("lydoxoa");
		if( lydoxoa == null )
			lydoxoa = "";
		ddmhBean.setLydoxoa(lydoxoa);
		
		String lydomo = request.getParameter("lydomo");
		if( lydomo == null )
			lydomo = "";
		ddmhBean.setLydomoduyet(lydomo);
		
		String lydosua = request.getParameter("lydosua");
		if( lydosua == null )
			lydosua = "";
		ddmhBean.setLydosua(lydosua);		
   	 
   	    ddmhBean.setRequest(request);
   	    if(action.equals("duyet")){ // DUYỆT => CÓ || CHƯA CÓ USER TRONG BẢNG ERP_DUYETTAMUNG
   	    	
   	    	ddmhBean.Duyetmuahang(Id);
   	    }
   	    else if(action.equals("boduyet")) // BỎ DUYỆT => CHẮC CHẮN QUYỀN CỦA USER ĐÃ CÓ TRONG ERP_DUYETTAMUNG
   	    {
   	    	ddmhBean.BoDuyetmuahang(Id);
   	    }
   	    else if(action.equals("xoaphieu")) // XÓA => CÓ || CHƯA CÓ USER TRONG BẢNG ERP_DUYETTAMUNG
	    {
	    	ddmhBean.Xoamuahang(Id);
	    }
   	    else if(action.equals("suaphieu"))  // SỬA => CÓ || CHƯA CÓ USER TRONG BẢNG ERP_DUYETTAMUNG
	    {
   	    	ddmhBean.Suamuahang(Id);
   	    	
   	    	String nextJSP = "ErpTamUngUpdateSvl?userId=" + userId + "&update=" + Id + "&chucnang=" + loaicap+"&duyettu=1";
			response.sendRedirect(nextJSP);
			
			return;
	    }
   	    
   	    ddmhBean.init();
		// Data is saved into session
		session.setAttribute("ddmhBean", ddmhBean);
//		session.setAttribute("userId", userId);

		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDuyetDeNghiTamUng.jsp";
   		response.sendRedirect(nextJSP);

	}

}
