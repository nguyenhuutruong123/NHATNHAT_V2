package geso.dms.distributor.servlets.tamung;

import geso.dms.distributor.beans.tamung.INhanvien;
import geso.dms.distributor.beans.tamung.INhanvienList;
import geso.dms.distributor.beans.tamung.imp.Nhanvien;
import geso.dms.distributor.beans.tamung.imp.NhanvienList;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NhanvienSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NhanvienSvl() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		INhanvienList obj;
		HttpSession session = request.getSession();
		String ctyId = (String) session.getAttribute("congtyId");
		

		obj = new NhanvienList();
		obj.setCtyId(ctyId);

		String chixem = request.getParameter("chixem");
		obj.setChixem(chixem);

		Utility util = new Utility();
		out = response.getWriter();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);
		obj.setuserId(userId);
        
		obj.init("");
		session.setAttribute("obj", obj);
		response.sendRedirect(request.getContextPath() + "/pages/Distributor/NhanVienTamUng.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		INhanvienList obj;
		obj = new NhanvienList();

		HttpSession session = request.getSession();
		String ctyId = (String) session.getAttribute("congtyId");

		obj.setCtyId(ctyId);
		

		String chixem = request.getParameter("chixem");
		obj.setChixem(chixem);

		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		obj.setuserId(userId);
		String ma = util.antiSQLInspection(request.getParameter("ma"));
		if (ma == null)
			ma = "";
		obj.setMa(ma);

		String ten = util.antiSQLInspection(request.getParameter("ten"));
		if (ten == null)
			ten = "";
		obj.setTen(ten);

		String Trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (Trangthai == null)
			Trangthai = "";
		obj.setTrangthai(Trangthai);

		String pbId = util.antiSQLInspection(request.getParameter("pbId"));
		if (pbId == null)
			pbId = "";
		obj.setPbId(pbId);

		String st = "";

		if (ma.length() > 0) {
			st = st + " and upper(NVTU.MA) like upper(N'%" + util.replaceAEIOU(ma) + "%')";
		}

		if (ten.length() > 0) {
			st = st + " and dbo.ftBoDau(upper(NVTU.TEN)) like upper(N'%" + util.replaceAEIOU(ten) + "%')";
		}

		if (Trangthai.length() > 0) {
			st = st + " and NVTU.TRANGTHAI <='" + Trangthai + "'";

		}

		if (pbId.length() > 0) {
			st = st + " and NVTU.DVTH_FK ='" + pbId + "'";

		}

		obj.init(st);

		String action = request.getParameter("action");
		if (action.equals("xoa")) {
			obj = new NhanvienList();
			obj.setCtyId(ctyId);
			obj.setuserId(userId);
			obj.init("");
			session.setAttribute("obj", obj);
			response.sendRedirect(request.getContextPath() + "/pages/Distributor/NhanVienTamUng.jsp");
		} else if (action.equals("new")) {
			INhanvien objnv = new Nhanvien();
			objnv.setuserId(userId);
			objnv.setCtyId(ctyId);
			objnv.setuserId(userId);
			objnv.init();

			session.setAttribute("obj", objnv);
			response.sendRedirect(request.getContextPath() + "/pages/Distributor/NhanVienTamUngUpdate.jsp");
		} else {
			session.setAttribute("obj", obj);
			response.sendRedirect(request.getContextPath() + "/pages/Distributor/NhanVienTamUng.jsp");
		}

	}

}
