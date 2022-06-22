package geso.dms.distributor.servlets.phieuchiNPP;

import geso.dms.center.util.Utility;

import geso.dms.distributor.beans.phieuchiNPP.*;
import geso.dms.distributor.beans.phieuchiNPP.imp.*;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpPhieuChiNPPUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public ErpPhieuChiNPPUpdateSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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

		String userId = util.getUserId(querystring);
		
		
		String view = util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";

		
		String nppId  = "";
		if(!view.equals("TT"))
			nppId = util.getIdNhapp(userId);
	    if(nppId == null)
	    	nppId = "";

	    
	    
	    
		if (action.equals("update"))
		{
			IErpPhieuChiNPP btthBean = new ErpPhieuChiNPP(hdId);
			btthBean.setView(view);
			btthBean.setUserId(userId);
			btthBean.setnppId(util.getIdNhapp(userId));
			btthBean.Init();
			session.setAttribute("obj", btthBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuChiNPPUpdate.jsp";
			response.sendRedirect(nextJSP);
		} 
		else if (action.equals("display"))
		{
			IErpPhieuChiNPP btthBean = new ErpPhieuChiNPP(hdId);
			btthBean.setUserId(userId);
			btthBean.setView(view);
			btthBean.setnppId(util.getIdNhapp(userId));
			btthBean.Init();
			session.setAttribute("obj", btthBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuChiNPPDisplay.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
//		String sum = (String) session.getAttribute("sum");
		
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			session.setMaxInactiveInterval(30000);
			
			
			IErpPhieuChiNPP btthBean = new ErpPhieuChiNPP();

			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			Utility util = new Utility();
			session.setAttribute("util", util);

			String id = util.antiSQLInspection(request.getParameter("id"));
			btthBean.setUserId(userId);
			session.setAttribute("userId", userId);
			session.setAttribute("userTen", userTen);

			String action = request.getParameter("action");
			btthBean.setId(id);			
			
			String pageX = request.getParameter("pageX");
			String pageY = request.getParameter("pageY");
			
			
			btthBean.setPageX((int)Math.round(geso.dms.center.util.Utility.parseDouble(pageX) ) );
			btthBean.setPageY((int)Math.round(geso.dms.center.util.Utility.parseDouble(pageY)));
			
			
			
			String view = util.antiSQLInspection(request.getParameter("view"));
			if(view == null)
				view = "";
			String nppId  = "";
			if(!view.equals("TT"))
				nppId = util.getIdNhapp(userId);
		    if(nppId == null)
		    	nppId = "";
			
		    btthBean.setView(view);
		    
		    
		    
			String nextJSP = "";
			String NgayButToan=util.antiSQLInspection(request.getParameter("NgayButToan"));
			btthBean.setNgayButToan(NgayButToan);
			String DienGiai=util.antiSQLInspection(request.getParameter("DienGiai"));
			btthBean.setDienGiai(DienGiai);
			
			
			String htttId = util.antiSQLInspection(request.getParameter("htttId"));
			btthBean.setHtttId(htttId);
			
			
			String nguoinoptien = util.antiSQLInspection(request.getParameter("nguoinoptien"));
			btthBean.setNguoiNopTien(nguoinoptien);
			
			String donvi = util.antiSQLInspection(request.getParameter("donvi"));
			btthBean.setDonvi(donvi);
			
			String diachi = util.antiSQLInspection(request.getParameter("diachi"));
			btthBean.setDiachi(diachi);
			
			
			
			String[] nghiepvuketoanId = request.getParameterValues("nghiepvuketoanId");
			
			String[] TaiKhoanNoIds = request.getParameterValues("TaiKhoanNoIds");
			

			String[] TaiKhoanCoIds = request.getParameterValues("TaiKhoanCoIds");
			
			
			
			String[] doituongnoIds = request.getParameterValues("doituongnoIds");
			

			
			String[] doituongcoIds = request.getParameterValues("doituongcoIds");
			
			
			String[] yeutonoIds = request.getParameterValues("yeutonoIds");
			
			
			String[] yeutocoIds = request.getParameterValues("yeutocoIds");
			
			
			
			String[] dg = request.getParameterValues("dg");
			
				
			String[] pk_seq = request.getParameterValues("pk_seq");
			
			String[] Sotien = request.getParameterValues("Sotien");
			if(Sotien != null){
				for(int i = 0; i < Sotien.length; i++){
					if(Sotien[i].length() == 0) Sotien[i] = "0";
				}
			}
			
			int c =0;
			if(nghiepvuketoanId != null && nghiepvuketoanId.length > 0)
			{
				c = nghiepvuketoanId.length;
			}
			
			/*if(htttId.trim().length() > 0)
			{
				c = 1;
				if(nghiepvuketoanId != null && nghiepvuketoanId.length > 0)
				{
					c = nghiepvuketoanId.length;
				}
				else 
				{
					nghiepvuketoanId = new String[1];
					TaiKhoanNoIds = new String[1];
					TaiKhoanCoIds = new String[1];
					doituongnoIds = new String[1];
					doituongcoIds = new String[1];
					yeutonoIds = new String[1];
					yeutocoIds = new String[1];
				}
			}*/
			System.out.println("action = " + action);
			System.out.println("c ========1:" + c);
			if(action.equals("Xoa"))
			{
				c --;
				
				int xoaId = (int)Math.round(Utility.parseDouble(request.getParameter("xoaId")));
				
				btthBean.setNghiepvuketoanId(xoa1Dong(nghiepvuketoanId,xoaId));
				btthBean.setYeuToNoIds(xoa1Dong(yeutonoIds,xoaId));
				btthBean.setYeuToCoIds(xoa1Dong(yeutocoIds,xoaId));
				btthBean.setDoiTuongCoIds(xoa1Dong(doituongcoIds,xoaId));
				btthBean.setDoiTuongNoIds(xoa1Dong(doituongnoIds,xoaId));
				btthBean.setTaiKhoanCoIds(xoa1Dong(TaiKhoanCoIds,xoaId));
				btthBean.setTaiKhoanNoIds(xoa1Dong(TaiKhoanNoIds,xoaId));
				btthBean.setPKSEQIds(xoa1Dong(pk_seq,xoaId));
				btthBean.setDg(xoa1Dong(dg,xoaId));
				btthBean.setSotien(xoa1Dong(Sotien,xoaId));
			}else
			if(action.equals("addThem"))
			{
				c ++;
				btthBean.setNghiepvuketoanId(addThem1DongNull(nghiepvuketoanId));
				btthBean.setYeuToNoIds(addThem1DongNull(yeutonoIds));
				btthBean.setYeuToCoIds(addThem1DongNull(yeutocoIds));
				btthBean.setDoiTuongCoIds(addThem1DongNull(doituongcoIds));
				btthBean.setDoiTuongNoIds(addThem1DongNull(doituongnoIds));
				btthBean.setTaiKhoanCoIds(addThem1DongNull(TaiKhoanCoIds));
				btthBean.setTaiKhoanNoIds(addThem1DongNull(TaiKhoanNoIds));
				btthBean.setPKSEQIds(addThem1DongNull(pk_seq));
				btthBean.setDg(addThem1DongNull(dg));
				btthBean.setSotien(addThem1DongNull(Sotien));
			}
			else
			{
				
				btthBean.setNghiepvuketoanId(nghiepvuketoanId);
				btthBean.setYeuToNoIds(yeutonoIds);
				btthBean.setYeuToCoIds(yeutocoIds);
				btthBean.setDoiTuongCoIds(doituongcoIds);
				btthBean.setDoiTuongNoIds(doituongnoIds);
				btthBean.setTaiKhoanCoIds(TaiKhoanCoIds);
				btthBean.setTaiKhoanNoIds(TaiKhoanNoIds);
				btthBean.setPKSEQIds(pk_seq);
				btthBean.setDg(dg);
				btthBean.setSotien(Sotien);
			}
			btthBean.setCount(c);
			
			
			
			System.out.println("c ========" + c);
			
			
			
			
			
			
			if (id == null)
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuChiNPPNew.jsp";
			} else
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuChiNPPUpdate.jsp";
			}
			if (action.equals("save"))
			{
				if (id == null)
				{
					if (!(btthBean.Save()))
					{
						btthBean.createRs();
						session.setAttribute("obj", btthBean);
						response.sendRedirect(nextJSP);
						
					}
					else
					{
						IErpPhieuChiNPPList btthList = new ErpPhieuChiNPPList();
						System.out.println("Save xong ra trang ErpPhieuChiNPPList = ");
						btthList.setUserId(userId);
						btthList.setView(view);
						btthBean.setnppId(util.getIdNhapp(userId));
						btthList.init("");
						session.setAttribute("obj", btthList);
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuChiNPP.jsp";
						response.sendRedirect(nextJSP);
					}
				} 
				else
				{
					if (!(btthBean.Edit()))
					{
						btthBean.createRs();
						session.setAttribute("obj", btthBean);
						response.sendRedirect(nextJSP);
						
					} 
					else
					{
						IErpPhieuChiNPPList btthList = new ErpPhieuChiNPPList();
						btthList.setView(view);

						btthList.setUserId(userId);
						btthBean.setnppId(util.getIdNhapp(userId));
						btthList.init("");
						System.out.println("cap nhat xong ra trang ErpPhieuChiNPPList = ");
						session.setAttribute("obj", btthList);
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuChiNPP.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else{
				btthBean.createRs();
				session.setAttribute("obj", btthBean);
				response.sendRedirect(nextJSP);
				
			}
		}
	}
	
	public String[] addThem1DongNull(String[]a )
	{
		if(a == null)
			return new String[1];
		String[] kq = new String[(a.length +1)];
		for(int i = 0 ; i < a.length; i++)
		{
			kq[i] = a[i];
		}
		return kq;
	}
	public String[] xoa1Dong(String[]a,int dong )
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0 ; i < a.length; i++)
		{
			if(i !=  dong)
				result.add( a[i] == null ? "" : a[i]);
		}
		
		
		String[] str = result.toArray(new String[result.size()]);
		
		return  str;
	}
}
