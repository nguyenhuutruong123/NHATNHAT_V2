package geso.dms.distributor.servlets.tamung;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.tamung.IDuyettamung;
import geso.dms.distributor.beans.tamung.IErpTamUng;
import geso.dms.distributor.beans.tamung.IErpTamUngList;
import geso.dms.distributor.beans.tamung.imp.Duyettamung;
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

/**
 * Servlet implementation class ErpTamUngUpdateSvl
 */
@WebServlet("/ErpTamUngUpdateSvl")
public class ErpTamUngUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpTamUngUpdateSvl() {
		super();

	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
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

		String tuId = util.getId(querystring);

		String userId = util.getUserId(querystring);
		
		IErpTamUng tuBean = new ErpTamUng(tuId);
		
		String duyettu = request.getParameter("duyettu");
		if(duyettu == null)
			duyettu = "";
		tuBean.setduyettu(duyettu);
		
		if (action.equals("update")) {
			
			tuBean.setUserId(userId);
			tuBean.setCongtyId((String)session.getAttribute("congtyId"));
			
			tuBean.Init();
			session.setAttribute("tuBean", tuBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUngUpdate.jsp";
			response.sendRedirect(nextJSP);
		} else if (action.equals("display")) {
			tuBean.setUserId(userId);
			tuBean.setCongtyId((String)session.getAttribute("congtyId"));
			
			tuBean.Init();
			session.setAttribute("tuBean", tuBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUngDisplay.jsp";
			response.sendRedirect(nextJSP);
		}

	}

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String congtyId = (String) session.getAttribute("congtyId");
		
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect("/Traphaco/index.jsp");
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			session.setMaxInactiveInterval(30000);

			IErpTamUng tuBean = new ErpTamUng();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			Utility util = new Utility();
			session.setAttribute("util", util);

			String id = util.antiSQLInspection(request.getParameter("id"));
			tuBean.setUserId(userId);
			session.setAttribute("userId", userId);
			session.setAttribute("userTen", userTen);
			session.setAttribute("congtyId", congtyId);
			
			String action = request.getParameter("action");
			tuBean.setId(id);
			tuBean.setCongtyId((String)session.getAttribute("congtyId"));
			
			String nextJSP = "";
			String NgayTamUng = util.antiSQLInspection(request.getParameter("NgayTamUng"));
			tuBean.setNgayTamUng(NgayTamUng);

			String DoiTuongTamUng = util.antiSQLInspection(request.getParameter("DoiTuongTamUng"));
			tuBean.setDoiTuongTamUng(DoiTuongTamUng);

			session.setAttribute("doituong", DoiTuongTamUng);
			session.setAttribute("congtyId", (String)session.getAttribute("congtyId"));

			String NhanVienId = util.antiSQLInspection(request.getParameter("NhanVienId"));
			if (NhanVienId == null)
				NhanVienId = "";
			else if (DoiTuongTamUng.equals("1")) {
				tuBean.setTenHienThi(NhanVienId);
				tuBean.setNhanVienId(NhanVienId.split(" -- ")[0]);
			}
			
			String NccId = util.antiSQLInspection(request.getParameter("NccId"));
		
			if (NccId == null)
				NccId = "";
			if (DoiTuongTamUng.equals("2")) {
				tuBean.setTenHienThi(NccId);
				tuBean.setNccId(NccId.split(" -- ")[0]);
			}
			String LyDoTamUng = util.antiSQLInspection(request.getParameter("LyDoTamUng"));
			tuBean.setLyDoTamUng(LyDoTamUng);

			String SoTienTamUng = util.antiSQLInspection(request.getParameter("SoTienTamUng"));
			
			if(SoTienTamUng == null)
				SoTienTamUng = "0";
			else
				SoTienTamUng = SoTienTamUng.replace(",","");
			tuBean.setSoTienTamUng(SoTienTamUng);

			String TienTeId = util.antiSQLInspection(request.getParameter("TienTeId"));
			tuBean.setTienTeId(TienTeId);

			String ThoiGianHoanUng = util.antiSQLInspection(request.getParameter("ThoiGianHoanUng"));
			if (ThoiGianHoanUng == null)
				ThoiGianHoanUng = "";
			tuBean.setThoiGianHoanUng(ThoiGianHoanUng);
			
			String HtttId = util.antiSQLInspection(request.getParameter("htttId"));
			if (HtttId == null)
				HtttId = "";
			tuBean.setHtttId(HtttId);
			
			String diachincc = util.antiSQLInspection(request.getParameter("diachincc"));
			if (diachincc == null)
				diachincc = "";
			tuBean.setdiachincc(diachincc);

			String HinhThucHoanUng = util.antiSQLInspection(request.getParameter("HinhThucHoanUng"));
			if (HinhThucHoanUng == null)
				HinhThucHoanUng = "";
			tuBean.setHinhThucHoanUng(HinhThucHoanUng);

			String poId = util.antiSQLInspection(request.getParameter("PoId"));
			if (poId == null)
				poId = "";
			tuBean.setPoId(poId);
			
			String nccId = util.antiSQLInspection(request.getParameter("nccId"));
			if (nccId == null)
				nccId = "";
			tuBean.setnccId(nccId);			
			
			String duyettu = request.getParameter("duyettu");
			if(duyettu == null)
				duyettu = "";
			tuBean.setduyettu(duyettu);
			
			tuBean.createRs();
			if (id == null) {
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUngNew.jsp";
			} else {
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUngUpdate.jsp";
			}
			if (action.equals("save")) {
				if (id == null) {
					if (!(tuBean.Save())) {
						session.setAttribute("tuBean", tuBean);
						
					} else {
						IErpTamUngList tuList = new ErpTamUngList();
						tuList.setUserId(userId);
						tuList.setCongtyId((String)session.getAttribute("congtyId"));
						
						tuList.init();
						session.setAttribute("tuList", tuList);
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUng.jsp";
					}
				} else {
					if (!(tuBean.Edit())) {
						session.setAttribute("tuBean", tuBean);

					} else {
						
						if(tuBean.getduyettu().equals("1"))
						{
							 IDuyettamung ddmhBean = new Duyettamung();
							 ddmhBean.setCongtyId((String)session.getAttribute("congtyId"));
						     ddmhBean.setUserId(userId);
						     ddmhBean.setCtyId((String)session.getAttribute("congtyId"));
						     
						     ddmhBean.init();
						     session.setAttribute("ddmhBean", ddmhBean);

							 nextJSP = request.getContextPath() + "/pages/Distributor/ErpDuyetDeNghiTamUng.jsp";
						   	 response.sendRedirect(nextJSP);
						   	 return;
						}
						else
						{
							IErpTamUngList tuList = new ErpTamUngList();
							tuList.setUserId(userId);
							tuList.setCongtyId((String)session.getAttribute("congtyId"));
							
							tuList.init();
							session.setAttribute("tuList", tuList);
							nextJSP = request.getContextPath() + "/pages/Distributor/ErpTamUng.jsp";
						}
					}
				}
			}
			session.setAttribute("tuBean", tuBean);
			response.sendRedirect(nextJSP);
		}
	}
}
