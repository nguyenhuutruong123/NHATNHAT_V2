package geso.dms.center.servlets.reports;

import geso.dms.center.beans.baocaodoanhsotheonhanvaloai.IBCDoanhSoTheoNhanVaLoai;
import geso.dms.center.beans.baocaodoanhsotheonhanvaloai.imp.BCDoanhSoTheoNhanVaLoai;
import geso.dms.center.beans.bcchartdoanhsosp.IBcchartdoanhsosp;
import geso.dms.center.beans.bcchartdoanhsosp.imp.Bcchartdoanhsosp;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BCDoanhSoTheoNhanVaLoaiSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	public BCDoanhSoTheoNhanVaLoaiSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out  = response.getWriter();

		HttpSession session = request.getSession();

		Utility util = new Utility();
		out = response.getWriter();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		IBCDoanhSoTheoNhanVaLoai obj = new BCDoanhSoTheoNhanVaLoai();
		obj.setUserID(userId);
		
		String msg = "";
		obj.createBrand();
		obj.createKenh();
		obj.createASM();
		obj.createRSM();
		obj.setMessage(msg);
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/BCDoanhSoTheoNhanVaLoai.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();

		HttpSession session = request.getSession();

		out = response.getWriter();
		Utility util = new Utility();

		String userId = util.antiSQLInspection(request.getParameter("userId"));
		
		//lấy thông tin từ jsp
		String nam = request.getParameter("nam");
		String namDen = request.getParameter("namDen");
		String thangDen = request.getParameter("thangDen");
		String thangTu = request.getParameter("thangTu");
		String[] idNhan = request.getParameterValues("nhId");
		String[] idKenh = request.getParameterValues("kenhId");
		String[] idASM = request.getParameterValues("gsbhId");
		String[] idRSM = request.getParameterValues("bmId");
		
		
		//khởi tạo đối tượng
		IBCDoanhSoTheoNhanVaLoai obj = new BCDoanhSoTheoNhanVaLoai();
		
		if(nam == null) nam = "";
		if(namDen == null) namDen = "";
		if(thangTu == null) thangTu = "";
		if(thangDen == null) thangDen = "";
		obj.setNam(nam);
		obj.setNamDen(namDen);
		obj.setThangDen(thangDen);
		obj.setThangTu(thangTu);
			String chuoi ="";
			if (idNhan != null)
			{
				for (int i = 0; i < idNhan.length; i++)
				{

					chuoi += idNhan[i] + ",";
				}
			}	
			System.out.println("sp "+chuoi);
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			obj.setIdBrand(chuoi);
			
			chuoi ="";
			if (idKenh != null)
			{
				for (int i = 0; i < idKenh.length; i++)
				{

					chuoi += idKenh[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			obj.setIdKenh(chuoi);
			
			chuoi ="";
			if (idRSM != null)
			{
				for (int i = 0; i < idRSM.length; i++)
				{

					chuoi += idRSM[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			obj.setIdRSM(chuoi);
			
			chuoi ="";
			if (idASM != null)
			{
				for (int i = 0; i < idASM.length; i++)
				{

					chuoi += idASM[i] + ",";
				}
			}	
			if(chuoi.length() > 0)
				chuoi = chuoi.substring(0,chuoi.length() - 1);
			obj.setIdASM(chuoi);
			
			
			obj.setUserID(userId);
			obj.createASM();
			obj.createBrand();
			
			obj.createRSM();
			obj.init();
			obj.createKenh();
			
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);

			response.sendRedirect(request.getContextPath() + "/pages/Center/BCDoanhSoTheoNhanVaLoai.jsp");
		}
}
