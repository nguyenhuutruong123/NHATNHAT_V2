package geso.dms.center.servlets.chitieu;
import geso.dms.center.beans.chitieunpp.IChiTieuNhaPP;
import geso.dms.center.beans.chitieunpp.imp.ChiTieuDDKD;
import geso.dms.center.beans.chitieunpp.imp.ChiTieuNhaPP;
import geso.dms.center.util.Utility;

 
import java.io.IOException;
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

public class ChiTieuNhaPPTTNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChiTieuNhaPPTTNewSvl() {
        super();
        // TODO Auto-generated constructor stub
    }


	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
      
        return dateFormat.format(date);	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			Utility util=new Utility(); 
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
 
		String querystring = request.getQueryString();
		userId = util.getUserId(querystring); 
	 
		if (userId.length()==0){
			userId = util.antiSQLInspection(request.getParameter("userId"));
		}
		String id=util.getId(querystring);
		IChiTieuNhaPP obj = new ChiTieuNhaPP(id);
		session.setAttribute("userId", userId);
		obj.setUserId(userId);
		obj.init();
		session.setAttribute("obj",obj);
		String action = util.getAction(querystring);
	 
		if(action.equals("update")){
			String nextJSP = request.getContextPath() + "/pages/Center/ChitieunppUpdatett.jsp";//default
			response.sendRedirect(nextJSP);
		}
		else if(action.equals("display")){
			String nextJSP = request.getContextPath() + "/pages/Center/ChitieuNppTTDisplay.jsp";//default
			response.sendRedirect(nextJSP);
		}


		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			Utility util=new Utility(); 
		int loi=0;
	 
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8"); 
		
		userId = util.antiSQLInspection(request.getParameter("userId"));
 
		String madh=util.antiSQLInspection(request.getParameter("id")); 
		String action=request.getParameter("action"); 
		if(action==null){
			action="";
		}
	 
		ChiTieuNhaPP ctbean=new ChiTieuNhaPP();
		ctbean.setUserId(userId);
		try{
		ctbean.setID(Double.parseDouble(madh));
		}catch(Exception er){
			
		}
	 
		ctbean.setNguoiSua(userId);
		String ngaysua = getDateTime();
		ctbean.setNgaySua(ngaysua);
    	String ngaytao=getDateTime();
    	ctbean.setNguoiTao(userId);
    	ctbean.setNgayTao(ngaytao);   	

		  int thang=0;
		  try {
			  thang=Integer.parseInt(util.antiSQLInspection(request.getParameter("thang")));
		  }catch(Exception ex){
		  }
		  ctbean.setThang(thang);
		  int nam=0;
		  try{
			  nam=Integer.parseInt(util.antiSQLInspection(request.getParameter("nam")));
		  }catch(Exception ex ){ 
		  }
		  
		  ctbean.setNam(nam);
		  String ngaykethuc=util.antiSQLInspection(request.getParameter("ngayketthuc"));
		  ctbean.setNgayKetThuc(ngaykethuc);
		  double tongchitieu= 0;
		  try{
			 tongchitieu= Double.parseDouble(util.antiSQLInspection(request.getParameter("tongchitieu").replace(",","")));
		  }catch(Exception ex){
			  
		  }
		  if(tongchitieu<=0)
		  {
			 loi=loi+1;
		  }
		 
		  ctbean.setChitieu(tongchitieu);
		  String diengiai=util.antiSQLInspection(request.getParameter("diengiai"));
		  ctbean.setDienGiai(diengiai);
		  String songaylamviec=util.antiSQLInspection(request.getParameter("songaylamviec"));
		  ctbean.setSoNgayLamViec(songaylamviec);
		  String dvkd_fk=util.antiSQLInspection(request.getParameter("selectdvkd"));
		  ctbean.setDVDKId(dvkd_fk);
		  String kenhid=util.antiSQLInspection(request.getParameter("kenhid"));
		  ctbean.setKenhId(kenhid);
		  String tennpp =util.antiSQLInspection(request.getParameter("tennpp"));
		  ctbean.setTenNPP(tennpp);
		  
		  String	nextJSP;
		  String tenform=util.antiSQLInspection(request.getParameter("tenform"));
		 
		  if(tenform==null){
			  tenform="";
		  }
		  if(action.equals("update")){
			  nextJSP=request.getContextPath() + "/pages/Center/ChitieunppUpdatett.jsp";//default
		  }else{
			  nextJSP= request.getContextPath() + "/pages/Distributor/ChiTieuNppNew.jsp";
		  }	
		 
		 
	    	String[] maddkd = request.getParameterValues("maddkd");//ma nha pp
	    	String[] tenddkd =request.getParameterValues("tenddkd");//ten nha pp
			String[] chitieu = request.getParameterValues("chitieu");//
		    List<ChiTieuDDKD>  spList = new ArrayList<ChiTieuDDKD>();
			  if(maddkd != null){					
				int m = 0;
				while(m < maddkd.length){
					ChiTieuDDKD ddkd = new ChiTieuDDKD();
					if(maddkd[m] != ""){
						if(maddkd[m] != ""){ //chi them nhung san pham co so luong > 0
							ddkd.setDDKDId(maddkd[m]);
							ddkd.setDDKDTen(tenddkd[m]);
						   double ctvung=0;
						   try{
							   ctvung=Double.parseDouble(chitieu[m].replace(",",""));
						   }catch(Exception er){
							
						   }
						    ddkd.setChiTieu(ctvung);
						   
							spList.add(ddkd);
						}
					}
					m++;
				}	
			  }
			  ctbean.setListChiTieuDDKD(spList); 
			  
			  session.setAttribute("userId",userId);  
			 if(action.equals("update")){ 
					 if(ctbean.EditChiTieu(request)){ 
							session.setAttribute("nam",0);
							session.setAttribute("thang",0);
						 
							ctbean.setListChiTieu("");
							session.setAttribute("obj", ctbean);
						    nextJSP=request.getContextPath() + "/pages/Center/chitieunpptt.jsp";//default
					 }else{
						 
						 String msg=ctbean.getMessage();
						 ctbean=new ChiTieuNhaPP(madh);
						 ctbean.setMessage(msg);
						 ctbean.setUserId(userId);
						 
						 
					 }
				 }	  
		  
		  session.setAttribute("obj", ctbean);
		  response.sendRedirect(nextJSP);
	}}

}
