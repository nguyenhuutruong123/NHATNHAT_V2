package geso.dms.distributor.servlets.tamung;

import geso.dms.distributor.util.*;
import geso.dms.distributor.beans.tamung.IErpTamUng;
import geso.dms.distributor.beans.tamung.IErpTamUngList;
import geso.dms.distributor.beans.tamung.imp.ErpTamUng;
import geso.dms.distributor.beans.tamung.imp.ErpTamUngList;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ErpTamUngSvl")
public class ErpTamUngSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ErpTamUngSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		IErpTamUngList tuList;
		String userId;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		HttpSession session = request.getSession();

		Utility util = new Utility();
		out = response.getWriter();

		String querystring = request.getQueryString();
		String action = util.getAction(querystring);
		out.println(action);

		String hdId = util.getId(querystring);

		userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		tuList = new ErpTamUngList();
		tuList.setUserId(userId);
		
		tuList.setCongtyId((String)session.getAttribute("congtyId"));
		
		out.println(userId);

		if (action.equals("chot"))
		{	
			IErpTamUng hdth = new ErpTamUng(hdId);
			hdth.setUserId(userId);
			hdth.setCongtyId((String)session.getAttribute("congtyId"));
			tuList.setMsg(hdth.Chot());
			
			/*dbutils db = new dbutils();
    		db.update("update ERP_TAMUNG set DACHOT = '1' where pk_seq = '" + hdId + "'");
    		db.shutDown();*/
    		

		} else if (action.equals("delete"))
		{
			IErpTamUng hdth = new ErpTamUng(hdId);
			hdth.setUserId(userId);
			hdth.setCongtyId((String)session.getAttribute("congtyId"));
			
			tuList.setMsg(hdth.Delete());
		}
		tuList.init();
		session.setAttribute("tuList", tuList);
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUng.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		IErpTamUngList tuList;
		String userId;
		Utility util = new Utility();
		HttpSession session = request.getSession();

		tuList = new ErpTamUngList();
		String TuNgay = util.antiSQLInspection(request.getParameter("TuNgay"));
		tuList.setTuNgay(TuNgay);

		String DenNgay = util.antiSQLInspection(request.getParameter("DenNgay"));
		tuList.setDenNgay(DenNgay);

		String SoTienTamUng = util.antiSQLInspection(request.getParameter("SoTienTamUng"));
		tuList.setSoTienTamUng(SoTienTamUng);
		
		String DoiTuongTamUng = util.antiSQLInspection(request.getParameter("DoiTuongTamUng"));
		tuList.setDoiTuongTamUng(DoiTuongTamUng);

		session.setAttribute("doituong", DoiTuongTamUng);

		String NhanVienId = util.antiSQLInspection(request.getParameter("NhanVienId"));
		if (NhanVienId == null)
			NhanVienId = "";
		else if (DoiTuongTamUng.equals("1")) {
			tuList.setTenHienThi(NhanVienId);
			tuList.setNhanVienId(NhanVienId.split(" -- ")[0]);
		}

		String NccId = util.antiSQLInspection(request.getParameter("NccId"));
		if (NccId == null)
			NccId = "";
		if (DoiTuongTamUng.equals("2")) {
			tuList.setTenHienThi(NccId);
			tuList.setNccId(NccId.split(" -- ")[0]);
		}

		String TienTeId = util.antiSQLInspection(request.getParameter("TienTeId"));
		tuList.setTienTeId(TienTeId);
	
		userId = util.antiSQLInspection(request.getParameter("userId"));
		tuList.setUserId(userId);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null) trangthai = ""; else trangthai = trangthai.trim();
		tuList.setTrangThai(trangthai);
		
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		
		String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
		if(sochungtu == null)
			sochungtu = "";
		tuList.setSochungtu(sochungtu);
		

		if (action.equals("view") || action.equals("next") || action.equals("prev"))
		{
			tuList = new ErpTamUngList();
			tuList.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			// tuList.setUserId(userId);
			tuList.setUserId(userId);
			tuList.setCongtyId((String)session.getAttribute("congtyId"));
			
			tuList.init();
			tuList.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			session.setAttribute("tuList", tuList);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUng.jsp";
			response.sendRedirect(nextJSP);
		} 
		else if (action.equals("new"))
		{
			IErpTamUng tuBean = new ErpTamUng();
			tuBean.setUserId(userId);
			tuBean.setCongtyId((String)session.getAttribute("congtyId"));
			
			tuBean.createRs();
			session.setAttribute("tuBean", tuBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUngNew.jsp";
			response.sendRedirect(nextJSP);
		} else
		{
			// tuList.setUserId(userId);
			tuList.setUserId(userId);
			tuList.setCongtyId((String)session.getAttribute("congtyId"));
			
			tuList.init();
			session.setAttribute("tuList", tuList);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUng.jsp";
			response.sendRedirect(nextJSP);
		}

	}
}
