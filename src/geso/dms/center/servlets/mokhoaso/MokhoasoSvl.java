package geso.dms.center.servlets.mokhoaso;

import geso.dms.center.beans.mokhoaso.IMokhoaso;
import geso.dms.center.beans.mokhoaso.imp.Mokhoaso;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class MokhoasoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MokhoasoSvl() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    Utility util = new Utility();
	    
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();
	    String querystring = request.getQueryString();
	    String loai="";
	    		loai=request.getParameter("loai");
	   
	    		
	    String userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = request.getParameter("userId");
	    
		session.setAttribute("userId", userId);
	    IMokhoaso mksBean = new Mokhoaso();
	    mksBean.setVungId("");
	    mksBean.setKhuvucId("");
	    mksBean.setNppId("");
	    mksBean.init();
	    
	    if(loai!=null)
	    {
	    	if(loai.equals("1"))
	    	{
	    	 session.setAttribute("mksBean", mksBean);
	 		String nextJSP = request.getContextPath() + "/pages/Center/khoasoTT.jsp";
	 		response.sendRedirect(nextJSP);
	    	}
	    	if(loai.equals("2"))
	    	{
		    	 session.setAttribute("mksBean", mksBean);
		 		String nextJSP = request.getContextPath() + "/pages/Center/khoasotudongTT.jsp";
		 		response.sendRedirect(nextJSP);

	    	}
	    }
	    else
	    {
	    session.setAttribute("mksBean", mksBean);
		String nextJSP = request.getContextPath() + "/pages/Center/Mokhoaso.jsp";
		response.sendRedirect(nextJSP);
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    Utility util = new Utility();
	    
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();
	    
	    String userId = request.getParameter("userId");	  
	    String vungId = request.getParameter("vungId");
	    String kvId = request.getParameter("kvId");
	    String nppId = request.getParameter("nppId");
	    String [] nppids=request.getParameterValues("nppks");
	    String [] npptdks=request.getParameterValues("nppkstd");
	    String [] npp=request.getParameterValues("npp");
	    
	    String loai="";
		loai=request.getParameter("loai");

	    
	    String action = request.getParameter("action");
	    
	    if (vungId == null) vungId = "";
	    if (kvId == null) kvId = "";
	    if (nppId == null) nppId = "";
	    
	    IMokhoaso mksBean = new Mokhoaso();
	    mksBean.setVungId(vungId);
	    mksBean.setKhuvucId(kvId);
	    mksBean.setNppId(nppId);
	    mksBean.setUserId(userId);
	 
	    
	    
	    if(action.equals("open"))
	    {
	    	if(nppId.length()> 0)
	    	{
	    		mksBean.setMsg(mksBean.MoKhoaSoNgay());
	    	}
	    	else
	    	{
	    		mksBean.setMsg("Vui lòng chọn Nhà phân phối");
	    	}
	    	
	    }
	    if(action.equals("close"))
	    {
	    	if(nppids!=null)
	    	{
	    		String msg="";
	    		for(int i=0;i<nppids.length;i++)
	    		{
	    			String tennpp="";
	    			boolean flag=mksBean.KhoaSoNgay(nppids[i]);
	    			dbutils db1=new dbutils();
	    			ResultSet rs=db1.get("select ten from nhaphanphoi where pk_seq="+nppids[i]);
	    			try {
						if(rs.next())
							
								tennpp=rs.getString("ten");
								if(flag)
								{
									msg+="khóa số thành công: ";
								}
								
								msg+=tennpp+mksBean.getMsg()+"\n";
							
							
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    			
	    		}
	    		mksBean.setMsg(msg);
	    	}
	    	else
	    	{
	    		mksBean.setMsg("Vui lòng chọn Nhà phân phối");
	    	}
	    	
	    }

	    
	    if(action.equals("tlks"))
	    {
	    	if(npptdks!=null)
	    	{
	    		String msg="";
	    		for(int i=0;i<npp.length;i++)
	    		{
	    			thietlapks_td(npp[i],npptdks[i]);	
	    		}
	    		
	    	}
	    	else
	    	{
	    		mksBean.setMsg("Vui lòng chọn Nhà phân phối");
	    	}
	    	
	    }
	    mksBean.init();
	    if(loai!=null)
	    {
	    	if(loai.equals("1"))
	    	{
	    	 session.setAttribute("mksBean", mksBean);
	 		String nextJSP = request.getContextPath() + "/pages/Center/khoasoTT.jsp";
	 		response.sendRedirect(nextJSP);
	    	}
	    	if(loai.equals("2"))
	    	{
	    		
	    		 session.setAttribute("mksBean", mksBean);
	 	 		String nextJSP = request.getContextPath() + "/pages/Center/khoasotudongTT.jsp";
	 	 		response.sendRedirect(nextJSP);
	    	}
	    }
	    else
	    {
	    session.setAttribute("mksBean", mksBean);
		String nextJSP = request.getContextPath() + "/pages/Center/Mokhoaso.jsp";
		response.sendRedirect(nextJSP);
	    }


	}
	
	private void thietlapks_td(String nppid,String ngayks)
	{
		dbutils db=new dbutils();
		db.update("delete from KHAOSOTHANGTUDONG where nppid="+nppid);
		if(!ngayks.equals(""))
		{
			db.update("insert KHAOSOTHANGTUDONG(NPPID,NGAYKS,TRANGTHAI) values("+nppid+",'"+ngayks+"',0)");	
		}
	}

}
