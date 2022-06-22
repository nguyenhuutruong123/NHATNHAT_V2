package geso.dms.distributor.servlets.buttoantonghop;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.buttoantonghop.imp.ErpButToanTongHop;
import geso.dms.distributor.beans.buttoantonghop.imp.ErpButToanTongHopList;
import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHop;
import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHopList;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ErpButToanTongHopUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ErpButToanTongHopUpdateSvl()
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
		out.println("actin = "+ action);

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

	    System.out.println("view = "+ view);
	    
	    
		if (action.equals("update"))
		{
			IErpButToanTongHop btthBean = new ErpButToanTongHop(hdId);
			btthBean.setView(view);
			btthBean.setUserId(userId);
			btthBean.setnppId(util.getIdNhapp(userId));
			btthBean.Init();
			session.setAttribute("btthBean", btthBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHopUpdate.jsp";
			response.sendRedirect(nextJSP);
		} 
		else if (action.equals("display"))
		{
			IErpButToanTongHop btthBean = new ErpButToanTongHop(hdId);
			btthBean.setUserId(userId);
			btthBean.setView(view);
			btthBean.setnppId(util.getIdNhapp(userId));
			btthBean.Init();
			session.setAttribute("btthBean", btthBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHopDisplay.jsp";
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
			
			
			IErpButToanTongHop btthBean = new ErpButToanTongHop();

			
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
			
			String scrollTop = util.antiSQLInspection(request.getParameter("scrollTop"));
			btthBean.setScrollTop( (int)Math.round(Utility.parseDouble(scrollTop)));
			
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
			
			String[] TaiKhoanNoIds = request.getParameterValues("TaiKhoanNoIds");
			btthBean.setTaiKhoanNoIds(TaiKhoanNoIds);

			String[] TaiKhoanCoIds = request.getParameterValues("TaiKhoanCoIds");
			btthBean.setTaiKhoanCoIds(TaiKhoanCoIds);
	
			
			
			String[] doituongnoIds = request.getParameterValues("doituongnoIds");
			btthBean.setDoiTuongNoIds(doituongnoIds);
			
			System.out.println("doituongnoIds = "  + doituongnoIds.length);
			for(int i = 0 ; i < doituongnoIds.length; i ++)
			{
				System.out.println("doituongnoIds ["+i+"]= "  + doituongnoIds[i]);
			}
			
			String[] doituongcoIds = request.getParameterValues("doituongcoIds");
			btthBean.setDoiTuongCoIds(doituongcoIds);

			
			String[] yeutonoIds = request.getParameterValues("yeutonoIds");
			btthBean.setYeuToNoIds(yeutonoIds);
			
			String[] yeutocoIds = request.getParameterValues("yeutocoIds");
			btthBean.setYeuToCoIds(yeutocoIds);
			
			
			
			String[] nghiepvuketoanIds = request.getParameterValues("nghiepvuketoanIds");
			btthBean.setNghiepvuketoanIds(nghiepvuketoanIds);
			int c =0;
			if(nghiepvuketoanIds != null)
				c = nghiepvuketoanIds.length;
			btthBean.setCount(c);
			
			String[] Sotien = request.getParameterValues("Sotien");
			if(Sotien != null){
				for(int i = 0; i < Sotien.length; i++){
					if(Sotien[i].length() == 0) Sotien[i] = "0";
				}
			}
			btthBean.setSotien(Sotien);
			
			
			
			String[] dg = request.getParameterValues("dg");
			btthBean.setDg(dg);
				
			String[] pk_seq = request.getParameterValues("pk_seq");
			btthBean.setPKSEQIds(pk_seq);
			
			
			
			
			if (id == null)
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHopNew.jsp";
			} else
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHopUpdate.jsp";
			}
			if (action.equals("save"))
			{
				if (id == null)
				{
					if (!(btthBean.Save()))
					{
						btthBean.createRs();
						session.setAttribute("btthBean", btthBean);
						response.sendRedirect(nextJSP);
					}
					else
					{
						IErpButToanTongHopList btthList = new ErpButToanTongHopList();
						btthList.setCongtyId((String)session.getAttribute("congtyId"));
						btthList.setUserId(userId);
						btthList.setView(view);
						btthBean.setnppId(util.getIdNhapp(userId));
						btthList.init();
						session.setAttribute("btthList", btthList);
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
						response.sendRedirect(nextJSP);
					}
				} 
				else
				{
					if (!(btthBean.Edit()))
					{
						btthBean.createRs();
						session.setAttribute("btthBean", btthBean);
						response.sendRedirect(nextJSP);
					} 
					else
					{
						IErpButToanTongHopList btthList = new ErpButToanTongHopList();
						btthList.setView(view);
						btthList.setCongtyId((String)session.getAttribute("congtyId"));
						btthList.setUserId(userId);
						btthBean.setnppId(util.getIdNhapp(userId));
						btthList.init();
						session.setAttribute("btthList", btthList);
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			} else if(action.equals("savesauchot"))
			{
				if(id != null)
				{
					if (!(btthBean.Editsauchot()))
					{
						btthBean.createRs();
						session.setAttribute("btthBean", btthBean);
						response.sendRedirect(nextJSP);
					} 
					else
					{
						IErpButToanTongHopList btthList = new ErpButToanTongHopList();
						btthList.setView(view);
						btthList.setCongtyId((String)session.getAttribute("congtyId"));
						btthList.setUserId(userId);
						btthList.init();
						session.setAttribute("btthList", btthList);
						nextJSP = request.getContextPath() + "/pages/Distributor/ErpButToanTongHop.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else{
				btthBean.createRs();
				
				session.setAttribute("btthBean", btthBean);
				response.sendRedirect(nextJSP);
				
			}
		}
	}
}
