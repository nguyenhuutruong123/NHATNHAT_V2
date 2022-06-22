package geso.dms.distributor.servlets.xoanoncc;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.xoanoncc.*;
import geso.dms.distributor.beans.xoanoncc.imp.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpXoaNoNCCUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
	PrintWriter out;
	
    public ErpXoaNoNCCUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		
		{
			session.setMaxInactiveInterval(30000);
		
			this.out = response.getWriter();
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    out.println(userId);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId"));
		    
		    String id = util.getId(querystring);  	
			IErpXoaNoNCC tthdBean = new ErpXoaNoNCC(id);
	        tthdBean.setUserId(userId);
	        tthdBean.setCongtyId((String)session.getAttribute("congtyId"));
	    	tthdBean.setnppdangnhap(util.getIdNhapp(userId));
	    	
	        
	        String nextJSP;
	        
	        if(request.getQueryString().indexOf("display") >= 0 ) 
	        {
	        	tthdBean.init();
	        	nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCDisplay.jsp";
	        }
	        else
	        {
	        	tthdBean.init();
	        	nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCUpdate.jsp";
	        }
	 
	        session.setAttribute("tthdBean", tthdBean);
	        response.sendRedirect(nextJSP);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 
		
		String sum = (String) session.getAttribute("sum");
		
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpXoaNoNCC tthdBean;
			
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			
		    if(id == null)
		    {  	
		    	tthdBean = new ErpXoaNoNCC("");
		    }
		    else
		    {
		    	tthdBean = new ErpXoaNoNCC(id);
		    }
	
		    tthdBean.setUserId(userId);
	        tthdBean.setCongtyId((String)session.getAttribute("congtyId"));
	    	tthdBean.setnppdangnhap(util.getIdNhapp(userId));
	    	
			
		    String ngaychungtu = util.antiSQLInspection(request.getParameter("ngaychungtu"));
			if (ngaychungtu == null || ngaychungtu == "")
				ngaychungtu = this.getDateTime();				
	    	tthdBean.setNgaychungtu(ngaychungtu);
	    	
	    	String ngayghiso = util.antiSQLInspection(request.getParameter("ngayghiso"));
			if (ngayghiso == null || ngayghiso == "")
				ngayghiso = this.getDateTime();				
	    	tthdBean.setNgayghiso(ngayghiso);
	    	
	    	String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if (ghichu == null)
				ghichu = "";				
	    	tthdBean.setNoidungtt(ghichu);
	    	
	    	
	    	String nccId = util.antiSQLInspection(request.getParameter("nccId"));
			if (nccId == null)
				nccId = "";				
	    	tthdBean.setNccId(nccId);
	    	
	    	System.out.println("Nhà cung cấp:"+nccId);
	    	
	    	String nvId = util.antiSQLInspection(request.getParameter("nvId"));
			if (nvId == null)
				nvId = "";				
	    	tthdBean.setNvId(nvId);
	    	
	    	String tienteId = util.antiSQLInspection(request.getParameter("tienteId"));
			if (tienteId == null)
				tienteId = "";				
	    	tthdBean.setTienteId(tienteId);
	    	
	    	String loaiId = util.antiSQLInspection(request.getParameter("loaiId"));
			if (loaiId == null)
				loaiId = "";				
	    	tthdBean.setLoaixnId(loaiId);

	    	/*String[] ctttIds = request.getParameterValues("ctttIds");
			if (ctttIds != null)
			{
				String ctttId = "";
				for(int i = 0; i < ctttIds.length; i++)
					ctttId += ctttIds[i] + ",";
				
				if(ctttId.length() > 0)
				{
					ctttId = ctttId.substring(0, ctttId.length() - 1);
					tthdBean.setCttratruocId(ctttId);
				}
			}*/

	    	//Luu lai hoa don
	    	String[] idchungtu = request.getParameterValues("idchungtu");
	    	String[] loaicttt = request.getParameterValues("loaicttt");
	    	String[] khchungtu = request.getParameterValues("khchungtu");
			String[] sochungtutt = request.getParameterValues("khchungtu");
			String[] ngaychungtutt = request.getParameterValues("ngaychungtu");
			String[] tienchungtu = request.getParameterValues("tienchungtu");
			String[] tienthanhtoan = request.getParameterValues("tienthanhtoan");
			String[] tienconlai = request.getParameterValues("tienconlai");

			
			List<IHoadon> cttList =  new ArrayList<IHoadon>();
			
			if(idchungtu != null)
			{		
				IHoadon hoadon = null;
				int m = 0;
				while(m < idchungtu.length)
				{
					hoadon = new Hoadon(idchungtu[m], khchungtu[m], sochungtutt[m], ngaychungtutt[m], tienchungtu[m], tienthanhtoan[m], tienconlai[m]);
					hoadon.setLoaict(loaicttt[m]);
					cttList.add(hoadon);
					
					m++;
				}	
			}
			tthdBean.setCtttList(cttList);
			
			
	    	String sotienthanhtoan = request.getParameter("sotienthanhtoan");
	    	if(sotienthanhtoan == null)
	    		sotienthanhtoan = "";
	    	tthdBean.setSotientt(sotienthanhtoan);
	    	
	    	//Luu lai hoa don
	    	String[] idHd = request.getParameterValues("idHd"); 
	    	String[] kyhieuhd = request.getParameterValues("kyhieuhd");
			String[] sohd = request.getParameterValues("sohd");
			String[] ngayhd = request.getParameterValues("ngayhd");
			String[] avat = request.getParameterValues("avat");
			String[] thanhtoan = request.getParameterValues("thanhtoan");
			String[] conlai = request.getParameterValues("conlai");
			String[] loaict = request.getParameterValues("loaictHd");
			
			List<IHoadon> hdList =  new ArrayList<IHoadon>();
			
			if(kyhieuhd != null)
			{		
				IHoadon hoadon = null;
				int m = 0;
				while(m < kyhieuhd.length)
				{
					hoadon = new Hoadon(idHd[m], kyhieuhd[m], sohd[m], ngayhd[m], avat[m], thanhtoan[m], conlai[m]);
					hoadon.setLoaict(loaict[m]);
					hdList.add(hoadon);
					
					m++;
				}	
			}
			tthdBean.setHoadonRs(hdList);
			
			String action = request.getParameter("action");
			System.out.println("Action la: " + action);
			
			if(action.equals("save"))
			{	
				if(id == null) //tao moi
				{
					if(!tthdBean.createTTHD())
					{					     	
	    		    	tthdBean.createRs();
	    		    
	    		    	session.setAttribute("tthdBean", tthdBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						//tthdBean.updateDonmuahang(tthdBean.getDonmuahangId());
						
						IErpXoaNoNCCList obj = new ErpXoaNoNCCList();
					    obj.setUserId(userId);
					    obj.setCongtyId((String)session.getAttribute("congtyId"));
					    obj.setnppdangnhap(util.getIdNhapp(userId));
				    	
					    obj.init("");
					    
						session.setAttribute("obj", obj);
								
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCC.jsp";
						response.sendRedirect(nextJSP);
					}
				}
				else
				{
					if(!tthdBean.updateTTHD())
					{
						tthdBean.createRs();
		    		    
						session.setAttribute("tthdBean", tthdBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						//tthdBean.updateDonmuahang(tthdBean.getDonmuahangId());
						
						IErpXoaNoNCCList obj = new ErpXoaNoNCCList();
					    obj.setUserId(userId);
					    obj.setCongtyId((String)session.getAttribute("congtyId"));
					    obj.setnppdangnhap(util.getIdNhapp(userId));
					    
					    obj.init("");
					    
						session.setAttribute("obj", obj);
								
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCC.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				String nextJSP;
				hdList.clear();
				tthdBean.setHoadonRs(hdList);
				
				cttList.clear();
				tthdBean.setCtttList(cttList);
				
				tthdBean.setCongtyId((String)session.getAttribute("congtyId"));
				tthdBean.setnppdangnhap(util.getIdNhapp(userId));
				
				tthdBean.createRs();
				
				if (id == null){
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCNew.jsp";
				}
				else{
					nextJSP = request.getContextPath() + "/pages/Distributor/ErpXoaNoNCCUpdate.jsp";   						
				}
				
				session.setAttribute("tthdBean", tthdBean);
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
