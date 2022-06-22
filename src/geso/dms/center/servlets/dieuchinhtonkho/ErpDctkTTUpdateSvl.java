package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IErpDctkTT;
import geso.dms.center.beans.dieuchinhtonkho.IErpDctkTTList;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpDctkTT;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpDctkTTList;
import geso.dms.center.util.Utility;
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


public class ErpDctkTTUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
    public ErpDctkTTUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SalesUpERP/index.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
		
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId")); 
		    
		    String id = util.getId(querystring);  	
		    IErpDctkTT lsxBean = new ErpDctkTT(id);
		    lsxBean.setUserId(userId); 
		    
		    String nextJSP = "";
		    if(!querystring.contains("display"))
		    	lsxBean.init();
		    else
		    	lsxBean.init_dislay();	
    		
    		if(!querystring.contains("display"))
    			nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTUpdate.jsp";
    		else
    			nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTDisplay.jsp";
    		
	        session.setAttribute("lsxBean", lsxBean);
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
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SalesUpERP/index.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpDctkTT lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpDctkTT("");
		    }
		    else
		    {
		    	lsxBean = new ErpDctkTT(id);
		    }
	
		    lsxBean.setUserId(userId);
		    
		    String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
		    if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
		    	ngayyeucau = getDateTime();
		    lsxBean.setNgayyeucau(ngayyeucau);
		    	    
		    String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		    if(ghichu == null)
		    	ghichu = "";
		    lsxBean.setGhichu(ghichu);
		    
			String khonhapId = util.antiSQLInspection(request.getParameter("khonhapId"));
			if (khonhapId == null)
				khonhapId = "";				
			lsxBean.setKhoNhapId(khonhapId);
			
			String[] spMa = request.getParameterValues("spMa");
			lsxBean.setSpMa(spMa);

			String[] solo = request.getParameterValues("solo");
			lsxBean.setSpSolo(solo);
			
			String[] ngaynhapkho = request.getParameterValues("ngaynhapkho");
			lsxBean.setSpNgaynhapkho(ngaynhapkho);
			
			String[] ngayhethan = request.getParameterValues("ngayhethan");
			lsxBean.setSpNgayhethan(ngayhethan);

			String[] tonthucte = request.getParameterValues("tonthucte");
			lsxBean.setSpSoluong(tonthucte);
			
			String[] tonkho = request.getParameterValues("tonkho");
			lsxBean.setSpTonkho(tonkho);
			
			Hashtable<String, String> sp_chitiet = new Hashtable<String, String>();
			if(spMa != null)
			{
				for(int i = 0; i < spMa.length; i++ )
				{
					if(tonthucte[i].trim().length() > 0)
					{
						sp_chitiet.put(spMa[i] + "__" + solo[i]+"__"+ngayhethan[i] + "__" + ngaynhapkho[i], tonthucte[i] );
						if(spMa[i].equals("ZONG66"))
						System.out.println("sanpham ----- "+ spMa[i] + "__" + solo[i]+"__"+ngayhethan[i] + "__" + ngaynhapkho[i] +"---"+tonthucte[i]);
					}
				}
			}
			lsxBean.setSp_Chitiet(sp_chitiet);
			
		    String action = request.getParameter("action");
			if(action.equals("save"))
			{	
				if(id == null)
				{
					if(!lsxBean.createNK())
					{
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpDctkTTList obj = new ErpDctkTTList();
						obj.setUserId(userId);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
				
			    		response.sendRedirect(request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTT.jsp");
					}
				}
				else
				{
					if(!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpDctkTTList obj = new ErpDctkTTList();
					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTT.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				lsxBean.createRs();
				
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "";
				
				nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTNew.jsp";
				if(id != null)
					nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTUpdate.jsp";
				
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
