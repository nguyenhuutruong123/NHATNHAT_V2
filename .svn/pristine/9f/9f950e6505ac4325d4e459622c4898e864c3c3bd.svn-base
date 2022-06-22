package geso.dms.distributor.servlets.dontrahang;

import geso.dms.distributor.beans.dontrahang.IDontrahang;
import geso.dms.distributor.beans.dontrahang.IDontrahangList;
import geso.dms.distributor.beans.dontrahang.imp.Dontrahang;
import geso.dms.distributor.beans.dontrahang.imp.DontrahangList;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DontrahangUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	
	public DontrahangUpdateSvl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		HttpSession session = request.getSession();
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	
		final HttpServletResponse response2=response;
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{
			session.setMaxInactiveInterval(30000);
			
			Utility util = new Utility();
			
			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			String id = util.getId(querystring);
			IDontrahang lsxBean = new Dontrahang(id);
			lsxBean.setUserId(userId);
			
			String nextJSP = "";
			
			lsxBean.init();
			
			if (!querystring.contains("display"))
				nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangUpdate.jsp";
			else
				nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangDisplay.jsp";
			
			session.setAttribute("lsxBean", lsxBean);
			
			session.setAttribute("kenhId", lsxBean.getKbhId());
			session.setAttribute("kbhId", lsxBean.getKbhId());
			session.setAttribute("khoId", lsxBean.getKhoXuatId());
			session.setAttribute("nppId",lsxBean.getNppId() );
			
			response.sendRedirect(nextJSP);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IDontrahang lsxBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			if (id == null)
			{
				lsxBean = new Dontrahang("");
			} else
			{
				
				lsxBean = new Dontrahang(id);
			}
			
			lsxBean.setUserId(userId);
			
			String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
			if (ngayyeucau == null || ngayyeucau.trim().length() <= 0)
				ngayyeucau = getDateTime();
			lsxBean.setNgayyeucau(ngayyeucau);
			
			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if (ghichu == null)
				ghichu = "";
			lsxBean.setGhichu(ghichu);
			
			
			String sochungtu = util.antiSQLInspection( request.getParameter("sochungtu")==null? "":request.getParameter("sochungtu") );
			lsxBean.setSoChungTu(sochungtu);

			
			String khoxuatId = util.antiSQLInspection(request.getParameter("khoxuatId"));
			if (khoxuatId == null)
				khoxuatId = "";
			lsxBean.setKhoXuatId(khoxuatId);
			
			
			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			lsxBean.setNppId(nppId);
			
			String khId = util.antiSQLInspection(request.getParameter("khId"));
			if (khId == null)
				khId = "";
			lsxBean.setKhId(khId);
			
			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			if (kbhId == null)
				kbhId = "";
			lsxBean.setKbhId(kbhId);
			
			session.setAttribute("kenhId", lsxBean.getKbhId());
			session.setAttribute("kbhId", lsxBean.getKbhId());
			session.setAttribute("khoId", lsxBean.getKhoXuatId());
			session.setAttribute("nppId", request.getParameter("nppId"));
			
			
			String[] spMa = util.antiSQLInspection_Array(request.getParameterValues("spMa"));
			lsxBean.setSpMa(spMa);
			
			String[] spTen = util.antiSQLInspection_Array(request.getParameterValues("spTen"));
			lsxBean.setSpTen(spTen);
			
			String[] dvt = util.antiSQLInspection_Array(request.getParameterValues("donvi"));
			lsxBean.setSpDonvi(dvt);
			
			String[] soluong = util.antiSQLInspection_Array(request.getParameterValues("soluong"));
			lsxBean.setSpSoluong(soluong);
			
			String[] tonkho = util.antiSQLInspection_Array(request.getParameterValues("tonkho"));
			lsxBean.setSpTonkho(tonkho);
			
			String[] dongia = util.antiSQLInspection_Array(request.getParameterValues("dongia"));
			lsxBean.setSpGianhap(dongia);
			
			String[] spVat = util.antiSQLInspection_Array(request.getParameterValues("spVat"));
			lsxBean.setSpVat(spVat);
			
			
			String action = request.getParameter("action");
			
			if(spMa != null && ( action.equals("save") || action.equals("submit1")))  //LUU LAI THONG TIN NGUOI DUNG NHAP
			{
				Hashtable<String, String> sanpham_soluong = new Hashtable<String, String>();
				for(int i = 0; i < spMa.length; i++ )
				{
					String temID = spMa[i];
					String[] spSOLO = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spSOLO"));
					
					String[] spNgayHetHan = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spNGAYHETHAN"));
					
					String[] soLUONGXUAT = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spSOLUONG"));
					
					String[] spNgayNhapKho = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spNGAYNHAPKHO"));
					
					
					if(soLUONGXUAT != null)
					{
						for(int j = 0; j < soLUONGXUAT.length; j++ )
						{
							System.out.println("solo : "+spSOLO[j]);
							System.out.println("soLUONGXUAT : "+soLUONGXUAT[j]);
							System.out.println("spNgayHetHan : "+spNgayHetHan[j]);
							System.out.println("spNgayNhapKho : "+spNgayNhapKho[j]);
							
							
							
							if(soLUONGXUAT[j] != null && soLUONGXUAT[j].trim().length() > 0)
							{
								sanpham_soluong.put(spMa[i] + "__" + spSOLO[j]+ "__" + spNgayHetHan[j]+ "__" + spNgayNhapKho[j], soLUONGXUAT[j].replaceAll(",", "") );
							}
						}
					}
				}
				
				lsxBean.setSanpham_Soluong(sanpham_soluong);
			}
		    
			
			
			if (action.equals("save"))
			{
				if (id == null)
				{
					if (!lsxBean.createNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangNew.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IDontrahangList obj = new DontrahangList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("userId", userId);
						
						response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonTraHang.jsp");
					}
				} else
				{
					if (!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangUpdate.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IDontrahangList obj = new DontrahangList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHang.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else
			{
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangNew.jsp";
				if (id != null)
					nextJSP = request.getContextPath() + "/pages/Distributor/DonTraHangUpdate.jsp";
				
				response.sendRedirect(nextJSP);
			}
		}
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
