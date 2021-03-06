
package geso.dms.distributor.servlets.chuyentuyen;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyentuyen.IChuyenTuyen;
import geso.dms.distributor.beans.chuyentuyen.imp.ChuyenTuyen;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ChuyenTuyenSvl")
public class ChuyenTuyenSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ChuyenTuyenSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IChuyenTuyen obj;
		PrintWriter out;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		out = response.getWriter();

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
		Utility util = new Utility();
		out = response.getWriter();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String action = request.getParameter("action");
		String type = request.getParameter("type");
		if(type == null)
			type = "";
		
		if(type.equals("sohoadon"))
		{
			out.println(userId);
		
			obj = new ChuyenTuyen();
			
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			
			System.out.println("user1 id"+userId);
			obj.setUserId(userId);
			
			System.out.println("user1 id"+obj.getUserId());
			obj.initSohoadon();
			session.setAttribute("ctuyenBean", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/SoHoaDon.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			if (action != null && action.equals("check"))
			{
				String ddkdFromId=request.getParameter("ddkdFromId");
				String tuyenFromId=request.getParameter("tuyenFromId");
				String ddkdToId=request.getParameter("ddkdToId");
				String tuyenToId=request.getParameter("tuyenToId");
				String nppId=request.getParameter("nppId");
				dbutils db=new dbutils();
				String query=	
						"select COUNT(*) as SoDong,(select smartId From KHACHHANG WHERE PK_SEQ=KHACHHANG_FK) AS SmartId, "+
						"	(select TEN From KHACHHANG WHERE PK_SEQ=KHACHHANG_FK) AS khTen, "+
						"	(select DIACHI From KHACHHANG WHERE PK_SEQ=KHACHHANG_FK) AS diachi "+
						"from KHACHHANG_TUYENBH "+
						"	where TBH_FK in("+tuyenFromId+","+tuyenToId+") "+
						"group by KHACHHANG_FK "+ 
						"having COUNT(*)>1 ";
				System.out.println("[Query]"+query);
				ResultSet rs=db.get(query);
				String flag="";
				try
				{
					while(rs.next())
					{
						flag="true";
					}
				} catch (SQLException e)
				{
					System.out.println("__Error Check"+e.getMessage());
					e.printStackTrace();
				}
				out.write(flag);
			} 
			else
			{
				out.println(userId);
				if (userId.length() == 0)
					userId = util.antiSQLInspection(request.getParameter("userId"));
				obj = new ChuyenTuyen();
				obj.setUserId(userId);
				obj.createRs();
				session.setAttribute("ctuyenBean", obj);
				String nextJSP = request.getContextPath() + "/pages/Distributor/ChuyenTuyen.jsp";
				response.sendRedirect(nextJSP);
			}
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
		} 
		else
		{

			IChuyenTuyen ctuyenBean;
			PrintWriter out;

			ctuyenBean = new ChuyenTuyen();
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			out = response.getWriter();

			userId = request.getParameter("userId");
			ctuyenBean.setUserId(userId);

			String nppId = request.getParameter("nppId");
			if (nppId == null)
				nppId = "";
			ctuyenBean.setNppId(nppId);
			
			String action = request.getParameter("action");
			if(action.equals("saveSOHOADON"))
			{
				ctuyenBean.setUserId(userId);
				
				String[] nvId = request.getParameterValues("nvId");
				ctuyenBean.setNvId(nvId);
				
				String[] nvMA = request.getParameterValues("nvMA");
				ctuyenBean.setNvMa(nvMA);
				
				String[] nvTEN = request.getParameterValues("nvTEN");
				ctuyenBean.setNvTen(nvTEN);
					
				//if(!ctuyenBean.getNppId().equals("100002") )
				//{
				String[] nvKyhieuhoadon = request.getParameterValues("nvKyhieuhoadon");
				ctuyenBean.setNvKyhieu(nvKyhieuhoadon);				
				
				String[] nvSohoadontu = request.getParameterValues("nvSohoadontu");
				ctuyenBean.setNvSotu(nvSohoadontu);
				
				String[] nvSohoadonden = request.getParameterValues("nvSohoadonden");
				ctuyenBean.setNvSoden(nvSohoadonden);
				
				String[] nvNgayhd = request.getParameterValues("nvNgayhd");
				ctuyenBean.setNvNgayHD(nvNgayhd);
			//	}
				
			//	if(ctuyenBean.getNppId().equals("106210") || ctuyenBean.getNppId().equals("100002") )
				{
					String[] nvKyhieuhoadon2 = request.getParameterValues("nvKyhieuhoadon2");
					ctuyenBean.setNvKyhieu2(nvKyhieuhoadon2);
					
					String[] nvSohoadontu2 = request.getParameterValues("nvSohoadontu2");
					ctuyenBean.setNvSotu2(nvSohoadontu2);
					
					String[] nvSohoadonden2 = request.getParameterValues("nvSohoadonden2");
					ctuyenBean.setNvSoden2(nvSohoadonden2);
					
					String[] nvNgayhd2 = request.getParameterValues("nvNgayhd2");
					ctuyenBean.setNvNgayHD2(nvNgayhd2);
				}
				
				if(ctuyenBean.saveSohoadon())
					ctuyenBean.initSohoadon();
				
				session.setAttribute("ctuyenBean", ctuyenBean);
				String nextJSP;
				nextJSP = request.getContextPath() + "/pages/Distributor/SoHoaDon.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				String ddkdFromId = request.getParameter("ddkdFromId");
				if (ddkdFromId == null)
					ddkdFromId = "";
				ctuyenBean.setDdkdFromId(ddkdFromId);
	
				String ddkdToId = request.getParameter("ddkdToId");
				if (ddkdToId == null)
					ddkdToId = "";
				ctuyenBean.setDdkdToId(ddkdToId);
	
				String tuyenFromId = request.getParameter("tuyenFromId");
				if (tuyenFromId == null)
					tuyenFromId = "";
				ctuyenBean.setTuyenFromId(tuyenFromId);
	
				String tuyenToId = request.getParameter("tuyenToId");
				if (tuyenToId == null)
					tuyenToId = "";
				ctuyenBean.setTuyenToId(tuyenToId);
	
				boolean error = false;
	
				if (ddkdFromId.trim().length() == 0)
				{
					ctuyenBean.setMessage("Vui Long Chon Dai Dien Kinh Doanh");
					error = true;
				}
	
				if (ddkdToId.trim().length() == 0)
				{
					ctuyenBean.setMessage("Vui Long Chon Dai Dien Kinh Doanh");
					error = true;
				}
				/*if (tuyenFromId.trim().length() == 0)
				{
					ctuyenBean.setMessage("Vui Long Chon Tuyen Ban Hang");
					error = true;
				}*/	
				/*if (tuyenToId.trim().length() == 0)
				{
					ctuyenBean.setMessage("Vui Long Chon Tuyen Ban Hang");
					error = true;
				}*/
				
				if (action.equals("save") && !error)
				{
					String[] khIds = request.getParameterValues("khIds");
					String[] tansoIds = request.getParameterValues("tansoList");
					String[] sott = request.getParameterValues("thutu");
					
					if(ddkdToId.trim().length() != 0 && ddkdFromId.trim().length() != 0
							&& tuyenToId.trim().length() == 0 && tuyenFromId.trim().length() == 0)
					{
						if(ctuyenBean.MoveTbh_All())
						{
							ctuyenBean.setMessage("Chuy???n tuy???n th??nh c??ng");
						}
					}
					else
					{
						if (!ctuyenBean.MoveTbh(khIds, tansoIds, sott))
						{
		
						} else
						{
							ctuyenBean.setMessage("Chuy???n tuy???n th??nh c??ng");
						}
					}
					ctuyenBean.setUserId(userId);
					ctuyenBean.createRs();
					session.setAttribute("ctuyenBean", ctuyenBean);
					String nextJSP;
					nextJSP = request.getContextPath() + "/pages/Distributor/ChuyenTuyen.jsp";
					response.sendRedirect(nextJSP);
				} 
				else
				{
					ctuyenBean.setUserId(userId);
					ctuyenBean.createRs();
					session.setAttribute("ctuyenBean", ctuyenBean);
					String nextJSP;
					nextJSP = request.getContextPath() + "/pages/Distributor/ChuyenTuyen.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		}
	}
}
