package geso.dms.distributor.servlets.buttoantonghop;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHop;
import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHopList;
import geso.dms.distributor.beans.buttoantonghop.imp.ErpButToanTongHop;
import geso.dms.distributor.beans.buttoantonghop.imp.ErpButToanTongHopList;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ErpButToanTongHopSvl")
public class ErpButToanTongHopSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ErpButToanTongHopSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		IErpButToanTongHopList btthList;
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

		btthList = new ErpButToanTongHopList();
		
		String view = util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";
		btthList.setView(view);

		
		
		
		btthList.setCongtyId((String)session.getAttribute("congtyId"));
		btthList.setUserId(userId);
		out.println(userId);

		if (action.equals("chot"))
		{
			IErpButToanTongHop btth = new ErpButToanTongHop(hdId);
			btth.setUserId(userId);
			btthList.setMsg(btth.Chot());

		} else if (action.equals("delete"))
		{
			
			IErpButToanTongHop hdth = new ErpButToanTongHop(hdId);
			hdth.setUserId(userId);
			btthList.setMsg(hdth.Delete());
		}
		btthList.init();

		session.setAttribute("btthList", btthList);
		session.setAttribute("congtyId", btthList.getCongtyId());
		
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		IErpButToanTongHopList btthList;
		String userId;
		Utility util = new Utility();

		HttpSession session = request.getSession();
		
		btthList = new ErpButToanTongHopList();
		btthList.setCongtyId((String)session.getAttribute("congtyId"));
		

		String view = util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";
		btthList.setView(view);
		
		
		String NgayButToan = util.antiSQLInspection(request.getParameter("NgayButToan"));
		if (NgayButToan == null) NgayButToan ="";
		btthList.setNgayButToan(NgayButToan);
		
		String DenNgayButToan = util.antiSQLInspection(request.getParameter("DenNgayButToan"));
		if (DenNgayButToan == null) DenNgayButToan ="";
		btthList.setDenNgayButToan(DenNgayButToan);
		
		String Sochungtu = util.antiSQLInspection(request.getParameter("Sochungtu"));
		if (Sochungtu == null) Sochungtu ="";
		btthList.setSoChungTu(Sochungtu);
		
		String Nguoitao = util.antiSQLInspection(request.getParameter("Nguoitao"));
		if (Nguoitao == null) Nguoitao ="";
		btthList.setNguoiTao(Nguoitao);
		
		String Sotien = util.antiSQLInspection(request.getParameter("Sotien"));
		if (Sotien == null) Sotien ="";
		btthList.setSoTien(Sotien.replaceAll(",", ""));
		
		String Taikhoanno = util.antiSQLInspection(request.getParameter("Taikhoanno"));
		if (Taikhoanno == null) Taikhoanno ="";
		btthList.setTaiKhoanNo(Taikhoanno);
		
		String Taikhoanco = util.antiSQLInspection(request.getParameter("Taikhoanco"));
		if (Taikhoanco == null) Taikhoanco ="";
		btthList.setTaiKhoanCo(Taikhoanco);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null) trangthai ="";
		btthList.setTrangthai(trangthai);
		
		userId = util.antiSQLInspection(request.getParameter("userId"));
		btthList.setUserId(userId);
		String action = request.getParameter("action");
		if (action == null)
			action = "";
		
		String chungtu=util.antiSQLInspection(request.getParameter("chungtu"));
		if (chungtu == null)
			chungtu = "";

		if (action.equals("view") || action.equals("next") || action.equals("prev"))
		{
			btthList = new ErpButToanTongHopList();
			btthList.setView(view);
			btthList.setCongtyId((String)session.getAttribute("congtyId"));
			
			btthList.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			// btthList.setUserId(userId);
			btthList.init();
			btthList.setAttribute(request, action, "list", "crrApprSplitting","nxtApprSplitting");
			session.setAttribute("btthList", btthList);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
			response.sendRedirect(nextJSP);
			
		} else if (action.equals("new"))
		{
			IErpButToanTongHop btthBean = new ErpButToanTongHop();
			btthBean.setView(view);
			btthBean.setUserId(userId);
			btthBean.Init();
			btthList.DBClose();
			btthBean.createRs();
			session.setAttribute("btthBean", btthBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHopNew.jsp";
			response.sendRedirect(nextJSP);
		}
		else if (action.equals("chot"))
		{
			IErpButToanTongHop btth = new ErpButToanTongHop(chungtu);
			btth.setView(view);
			btth.setUserId(userId);
			btthList.setMsg(btth.Chot());
			
			btthList.init();
			session.setAttribute("btthList", btthList);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
			response.sendRedirect(nextJSP);

		}
		 else if (action.equals("delete"))
			{
				
				IErpButToanTongHop hdth = new ErpButToanTongHop(chungtu);
				hdth.setView(view);
				hdth.setUserId(userId);
				btthList.setMsg(hdth.Delete());
				
				btthList.init();
				session.setAttribute("btthList", btthList);
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
				response.sendRedirect(nextJSP);
			}
		else
		{
			// btthList.setUserId(userId);
			btthList.init();
			session.setAttribute("btthList", btthList);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
			response.sendRedirect(nextJSP);
		}

	}
}
