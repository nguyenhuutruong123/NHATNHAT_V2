package geso.dms.distributor.servlets.chuyenkho;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenKho;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenKhoList;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenKho;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenKhoList;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ErpChuyenKhoUpdateSvl")
public class ErpChuyenKhoUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	public ErpChuyenKhoUpdateSvl()
	{
		super();
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

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
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
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
			IErpChuyenKho lsxBean = new ErpChuyenKho(id);
			lsxBean.setUserId(userId);
			
			String nextJSP = "";
			
			lsxBean.init();
			session.setAttribute("khoxuat", lsxBean.getKhoXuatId());
			session.setAttribute("kenhId", lsxBean.getKbhId());
			session.setAttribute("nppId", lsxBean.getNppId());
			
			if (!querystring.contains("display"))
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoUpdate.jsp";
			else
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoDisplay.jsp";
			
			session.setAttribute("lsxBean", lsxBean);
			response.sendRedirect(nextJSP);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}   
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
			IErpChuyenKho lsxBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			if (id == null)
			{
				System.out.println("Vao day");
				lsxBean = new ErpChuyenKho("");
			} else
			{
				
				lsxBean = new ErpChuyenKho(id);
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
			
			String khoxuatId = util.antiSQLInspection(request.getParameter("khoxuatId"));
			if (khoxuatId == null)
				khoxuatId = "";
			lsxBean.setKhoXuatId(khoxuatId);
			session.setAttribute("khoxuat", lsxBean.getKhoXuatId());
			
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
			
			// THONG TIN IN
			String lenhdieudong = util.antiSQLInspection(request.getParameter("lenhdieudong"));
			if (lenhdieudong == null)
				lenhdieudong = "";
			lsxBean.setLenhdieudong(lenhdieudong);
			
			String lddcua = util.antiSQLInspection(request.getParameter("lddcua"));
			if (lddcua == null)
				lddcua = "";
			lsxBean.setLDDcua(lddcua);
			
			String lddveviec = util.antiSQLInspection(request.getParameter("lddveviec"));
			if (lddveviec == null)
				lddveviec = "";
			lsxBean.setLDDveviec(lddveviec);
			
			String ngaydieudong = util.antiSQLInspection(request.getParameter("ngaydieudong"));
			if (ngaydieudong == null)
				ngaydieudong = "";
			lsxBean.setNgaydieudong(ngaydieudong);
			
			String sohopdong = util.antiSQLInspection(request.getParameter("sohopdong"));
			if (sohopdong == null)
				sohopdong = "";
			lsxBean.setSohopdong(sohopdong);
			
			String ngayhopdong = util.antiSQLInspection(request.getParameter("ngayhopdong"));
			if (ngayhopdong == null)
				ngayhopdong = "";
			lsxBean.setNgayhopdong(ngayhopdong);
			
			String nguoivanchuyen = util.antiSQLInspection(request.getParameter("nguoivanchuyen"));
			if (nguoivanchuyen == null)
				nguoivanchuyen = "";
			lsxBean.setNguoivanchuyen(nguoivanchuyen);
			
			String ptvanchuyen = util.antiSQLInspection(request.getParameter("ptvanchuyen"));
			if (ptvanchuyen == null)
				ptvanchuyen = "";
			lsxBean.setPtvanchuyen(ptvanchuyen);
			
			String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu") == null ? "" : request.getParameter("sochungtu").trim());
			lsxBean.setSoChungTu(sochungtu);
			
			// System.out.println("----KHO XUAT: " + khoxuatId + "  -- KHO NHAP:  " +
			// khonhapId );
			
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
			
			String action = request.getParameter("action");
			if (spMa != null && action.equals("save")) // LUU LAI THONG TIN NGUOI DUNG
																								 // NHAP
			{
				Hashtable<String, String> sanpham_soluong = new Hashtable<String, String>();
				for (int i = 0; i < spMa.length; i++)
				{
					// System.out.println("---SP MA LA: " + spMa[i]);
					String temID = spMa[i];
					
					String[] spSOLO = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spSOLO"));
					
					String[] spNgayHetHan = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spNGAYHETHAN"));
					
					String[] soLUONGXUAT = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spSOLUONG"));

					String[] spNgayNhapKho = util.antiSQLInspection_Array(request.getParameterValues(temID + "_spNGAYNHAPKHO"));
					
					if (soLUONGXUAT != null)
					{
						for (int j = 0; j < soLUONGXUAT.length; j++)
						{
							if (soLUONGXUAT[j] != null && soLUONGXUAT[j].trim().length() > 0)
							{
								sanpham_soluong.put(spMa[i] + "__" + spSOLO[j] + "__" + spNgayHetHan[j] + "__" + spNgayNhapKho[j], soLUONGXUAT[j].replaceAll(",", ""));
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
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoNew.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IErpChuyenKhoList obj = new ErpChuyenKhoList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("userId", userId);
						
						response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpChuyenKho.jsp");
					}
				} else
				{
					if (!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoUpdate.jsp";
						response.sendRedirect(nextJSP);
					} else
					{
						IErpChuyenKhoList obj = new ErpChuyenKhoList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKho.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else 
			{
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				session.setAttribute("ngaynghiepvu", ngayyeucau);
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoNew.jsp";
				if (id != null)
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKhoUpdate.jsp";
				
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
