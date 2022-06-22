package geso.dms.distributor.servlets.dieuchuyentien;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.dieuchuyentien.IErpDieuchuyentien;
import geso.dms.distributor.beans.dieuchuyentien.IErpDieuchuyentienList;
import geso.dms.distributor.beans.dieuchuyentien.imp.ErpDieuchuyentien;
import geso.dms.distributor.beans.dieuchuyentien.imp.ErpDieuchuyentienList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpDieuchuyentienUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpDieuchuyentienUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDieuchuyentien obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    String ctyId = (String)session.getAttribute("congtyId");
	    String action = util.getAction(querystring);
	    
	    String nppId = util.getIdNhapp(userId);
	    if(nppId == null)
	    	nppId = "";

		String npp_duocchon_id = session.getAttribute("npp_duocchon_id") == null ? "" : session.getAttribute("npp_duocchon_id").toString();

		
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String Id = util.getId(querystring);
	    
	    obj = new ErpDieuchuyentien();
	    obj.setCongtyId(ctyId);
	    obj.setId(Id);
	    obj.setUserId(userId);
	    
	    obj.setnppId(util.getIdNhapp(userId));
	    obj.setNpp_duocchon_id(npp_duocchon_id);
		
	    obj.init();
	    
		session.setAttribute("obj", obj);
		String nextJSP;
		
		if(action.equals("update")){
			 nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTienUpdate.jsp";
		}else{
			 nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTienDisplay.jsp";
		}
		
		response.sendRedirect(nextJSP);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDieuchuyentien obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    String ctyId = (String)session.getAttribute("congtyId");
	    String Id = util.antiSQLInspection(request.getParameter("Id"));
	    System.out.println("Id: " + Id);
	    
	    String npp_duocchon_id = session.getAttribute("npp_duocchon_id") == null ? "" : session.getAttribute("npp_duocchon_id").toString();

	    obj = new ErpDieuchuyentien();
	    obj.setCongtyId(ctyId);
	    obj.setId(Id);
	    obj.setUserId(userId);
	    
	    obj.setnppId(util.getIdNhapp(userId));
	    obj.setNpp_duocchon_id(npp_duocchon_id);
	    
	    System.out.println("npp id: " + obj.getnppId());
	    String ngaychungtu = util.antiSQLInspection(request.getParameter("ngaychungtu"));
	    if(ngaychungtu == null) ngaychungtu = "";
	    obj.setNgaychungtu(ngaychungtu);
	    
	    String sochungtu = util.antiSQLInspection(request.getParameter("sochungtu"));
	    if(sochungtu == null) sochungtu = "";
	    obj.setSochungtu(sochungtu);
	    
	    String ttId = util.antiSQLInspection(request.getParameter("ttId"));
	    if(ttId == null){
	    	ttId = "";
	    }
	    obj.setTtId(ttId);
	    
	    String tigia = util.antiSQLInspection(request.getParameter("tigia"));
	    if(tigia == null){
	    	tigia = "1";
	    }else{
	    	tigia = tigia.replaceAll(",", "");
	    }
	    
	    obj.setTigia(tigia);

	    String nhchuyenId = util.antiSQLInspection(request.getParameter("nhchuyenId"));
	    obj.setNhchuyenId(nhchuyenId);

	    String nhnhanId = util.antiSQLInspection(request.getParameter("nhnhanId"));
	    obj.setNhnhanId(nhnhanId);
	    
	    String nhkyquyId = util.antiSQLInspection(request.getParameter("nhkyquyId"));
	    if(nhkyquyId == null)
	    	nhkyquyId = "";
	    obj.setNhKyquyId(nhkyquyId);
	    
	    String nccId = util.antiSQLInspection(request.getParameter("nccId"));
	    if(nccId == null)
	    	nccId = "";
	    obj.setNccId(nccId);
	    
	    
	    String POId = util.antiSQLInspection(request.getParameter("poId"));
	    if(POId == null)
	    	POId = "";
	    obj.setPOId(POId);
	    
	    String loaidc = util.antiSQLInspection(request.getParameter("loaidc"));
	    if(loaidc == null)
	    	loaidc = "";
	    obj.setLoaidc(loaidc);
	    
	    String sotienVND = util.antiSQLInspection(request.getParameter("sotienVND"));
	    if(sotienVND == null){
	    	sotienVND = "0";
	    }else{
	    	sotienVND = sotienVND.replaceAll(",", "");
	    }

	    obj.setSotienVND(sotienVND);

	    String sotienNT = util.antiSQLInspection(request.getParameter("sotienNT"));
	    if(sotienNT == null){
	    	sotienNT = "0";
	    }else{
	    	sotienNT = sotienNT.replaceAll(",", "");
	    }
	    obj.setSotienNT(sotienNT);
	    
	    String trichphi = util.antiSQLInspection(request.getParameter("trichphi"));
	    if(trichphi == null) trichphi = "0";
	    obj.setTrichphi(trichphi);
	    
	    String nhtrichphiId = util.antiSQLInspection(request.getParameter("nhtrichphiId"));
	    obj.setNHTrichphiId(nhtrichphiId);


	    String phiNT = util.antiSQLInspection(request.getParameter("phiNT"));
	    if(phiNT == null){
	    	phiNT = "0";
	    }else{
	    	phiNT = phiNT.replaceAll(",", "");
	    }
	    obj.setPhiNT(phiNT);


	    String phiVND = util.antiSQLInspection(request.getParameter("phiVND"));
	    if(phiVND == null){
	    	phiVND = "0";
	    }else{
	    	phiVND = phiVND.replaceAll(",", "");
	    }
	    
	    obj.setPhiVND(phiVND);

	    String thueNT = util.antiSQLInspection(request.getParameter("thueNT"));
	    if(thueNT == null){
	    	thueNT = "0";
	    }else{
	    	thueNT = thueNT.replaceAll(",", "");
	    }
	    obj.setThueNT(thueNT);


	    String thueVND = util.antiSQLInspection(request.getParameter("thueVND"));
	    if(thueVND == null){
	    	thueVND = "0";
	    }else{
	    	thueVND = thueVND.replaceAll(",", "");
	    }
	    obj.setThueVND(thueVND);
	    
    	String maHD_VAT = util.antiSQLInspection(request.getParameter("maHD_VAT"));
    	if(maHD_VAT == null)
    		maHD_VAT = "";
    	obj.setMahoadon(maHD_VAT);

    	String mauHD_VAT = util.antiSQLInspection(request.getParameter("mauHD_VAT"));
    	if(mauHD_VAT == null)
    		mauHD_VAT = "";
    	obj.setMauhoadon(mauHD_VAT);
    	
    	String kyhieu_VAT = util.antiSQLInspection(request.getParameter("kyhieu_VAT"));
    	if(kyhieu_VAT == null)
    		kyhieu_VAT = "";
    	obj.setKyhieu(kyhieu_VAT);

    	String sohd_VAT = util.antiSQLInspection(request.getParameter("sohd_VAT"));
    	if(sohd_VAT == null)
    		sohd_VAT = "";
    	obj.setSohoadon(sohd_VAT);
    	
    	String ngayhd_VAT = util.antiSQLInspection(request.getParameter("ngayhd_VAT"));
    	if(ngayhd_VAT == null)
    		ngayhd_VAT = "";
    	obj.setNgayhoadon(ngayhd_VAT);	    	

    	String nghangTen = util.antiSQLInspection(request.getParameter("nghangTen"));
    	if(nghangTen == null)
    		nghangTen = "";
    	obj.setTenNH_VAT(nghangTen);
    	
   	
    	String mst_VAT = util.antiSQLInspection(request.getParameter("mst_VAT"));
    	if(mst_VAT == null)
    		mst_VAT = "";
    	obj.setMST(mst_VAT);

	    String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
	    if(ghichu == null) ghichu = "";
	    obj.setGhichu(ghichu);

	    if(action.equals("save"))
	    {
	    	if(Id == null){
	    		if(!obj.Taodieutien()){
	    			
	    			obj.setnppId(util.getIdNhapp(userId));
	    			obj.setNpp_duocchon_id(npp_duocchon_id);
	    			
					obj.createRs();
	    			session.setAttribute("obj", obj);
					
	    			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTienUpdate.jsp";
	    			response.sendRedirect(nextJSP);
	    		}else{
	    			IErpDieuchuyentienList dc = new ErpDieuchuyentienList();
	    		    dc.setCongtyId(ctyId);
	    		    dc.setUserId(userId);
	    		    
	    		    obj.setnppId(util.getIdNhapp(userId));	    			
	    		    dc.init("");
	    		    obj.DBclose();
	    			session.setAttribute("obj", dc);
	    					
	    			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTien.jsp";
	    			response.sendRedirect(nextJSP);
	    		}
	    	}else{
	    		if(!obj.Capnhatdieutien()){
	    			obj.createRs();
	    			session.setAttribute("obj", obj);
					
	    			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTienUpdate.jsp";
	    			response.sendRedirect(nextJSP);	    			
	    		}else{
	    			IErpDieuchuyentienList dc = new ErpDieuchuyentienList();
	    		    dc.setCongtyId(ctyId);
	    		    dc.setUserId(userId);
	    		    
	    		    dc.setnppId(util.getIdNhapp(userId));
	    		    dc.setNpp_duocchon_id(npp_duocchon_id);
	    			
	    		    dc.init("");
	    		    obj.DBclose();
	    			session.setAttribute("obj", dc);
	    					
	    			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTien.jsp";
	    			response.sendRedirect(nextJSP);
	    		}
	    	}
	    }
	    else
	    {
			obj.createRs();
			session.setAttribute("obj", obj);
			String nextJSP;
			
			if(Id == null){
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTienNew.jsp";
			}else{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDieuChuyenTienUpdate.jsp";
			}
			response.sendRedirect(nextJSP);	    			
    	}
	}
}