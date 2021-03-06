package geso.dms.distributor.servlets.thanhtoanhoadon;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.thanhtoanhoadon.IErpThanhtoanhoadon;
import geso.dms.distributor.beans.thanhtoanhoadon.IErpThanhtoanhoadonList;
import geso.dms.distributor.beans.thanhtoanhoadon.imp.ErpThanhtoanhoadon;
import geso.dms.distributor.beans.thanhtoanhoadon.imp.ErpThanhtoanhoadonList;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpThanhtoanhoadonSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpThanhtoanhoadonSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpThanhtoanhoadonList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    String tthdId = util.getId(querystring);
	    
	    obj = new ErpThanhtoanhoadonList();
	    obj.setUserId(userId);
	    obj.setnppdangnhap(util.getIdNhapp(userId));
	    
	    
	    if (action.equals("delete"))
	    {	
	    	String msg = Delete(tthdId, userId);
	    	if(msg.length() > 0)
	    		obj.setmsg(msg);
	    }
	    else
	    {
	    	if(action.equals("chot"))
	    	{
	    		IErpThanhtoanhoadon tthd = new ErpThanhtoanhoadon(tthdId);
	    		tthd.setUserId(userId);
	    		tthd.init();
	    		if(!tthd.chotTTHD(userId))
	    		{
	    			obj.setmsg(tthd.getMsg());
	    		}
	    	}
	    }
	    
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpThanhToanHoaDon.jsp";
		response.sendRedirect(nextJSP);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpThanhtoanhoadonList obj;
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
	    
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpThanhtoanhoadon tthdBean = new ErpThanhtoanhoadon();
	    	tthdBean.setCtyId(ctyId);
	    	tthdBean.setnppdangnhap(util.getIdNhapp(userId));
	    	tthdBean.setUserId(userId);
	    	
	    	tthdBean.createRs();
    		
	    	session.setAttribute("doituongkhac", "");
	    	session.setAttribute("doituong", "");
	    	session.setAttribute("loaithanhtoan", "");
	    	session.setAttribute("nhomncccn", "");
	    	
	    	session.setAttribute("tthdBean", tthdBean);

    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpThanhToanHoaDonNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		obj = new ErpThanhtoanhoadonList();
		    	String search = getSearchQuery(request, obj);
		    	
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setnppdangnhap(util.getIdNhapp(userId));
		    	obj.setCongtyId(ctyId);
		    	obj.setUserId(userId);
		    	
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpThanhToanHoaDon.jsp");	
		    }
	    	else
	    	{
		    	obj = new ErpThanhtoanhoadonList();		    	
		    	obj.setnppdangnhap(util.getIdNhapp(userId));
		    	obj.setCongtyId(ctyId);
		    	obj.setUserId(userId);
		    	
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpThanhToanHoaDon.jsp");
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpThanhtoanhoadonList obj)
	{
		String query =  " SELECT DISTINCT A.LOAITHANHTOAN,  A.PK_SEQ AS TTHDID, A.TRANGTHAI, A.NGAYGHINHAN, A.NGAYTAO, A.NGAYSUA, a.phinganhang, a.vat , isnull(a.thanhtoantutienvay,0) as thanhtoantutienvay, " +
						" CASE WHEN A.NCC_FK IS NOT NULL THEN  B.TEN " +
						"      ELSE (CASE WHEN NV.TEN IS NOT NULL THEN NV.TEN "+
						"                 WHEN KH.TEN IS NOT NULL THEN KH.TEN "+
						"                 WHEN DVTH.TEN IS NOT NULL THEN DVTH.TEN "+
						"                 ELSE F.DIENGIAI END) END  AS NCCTEN," +
						" C.TEN AS HTTTTEN , isnull(a.iskttduyet,0) as iskttduyet,  "+
						" D.TEN AS NGUOITAO, E.TEN AS NGUOISUA, A.PREFIX " +
						"    ,CASE \n" +
						"   	WHEN a.NCC_FK is not null or a.NHANVIEN_FK is not null or a.KHACHHANG_FK is not null THEN \n" +
						"   			(isnull((select cast(isnull(CT.sohoadon,'') as varchar(20)) +', ' as [text()] \n" +
						"   	   		from  ERP_THANHTOANHOADON_HOADON CT \n" +
						"               where CT.THANHTOANHD_FK = A.PK_SEQ \n"+
						"   	   		For XML PATH ('')),'')) \n" +
						"   	WHEN a.DVTH_FK is not null THEN \n" +
						"   			(isnull((select cast(isnull(CT.sohoadon,'') as varchar(20)) +', ' as [text()] \n" +
						"   	   		from  ERP_THANHTOANHOADON_HOADONBOPHAN CT \n" +
						"               where CT.THANHTOANHD_FK = A.PK_SEQ \n"+
						"   	   		For XML PATH ('')),'')) \n" +
						"   	ELSE '' \n" +
						"   	END	as sohoadon, A.SOTIENTT, isnull(A.SOCHUNGTU,'') SOCTTUDONG, isnull(a.machungtu,'') machungtu  \n" +
						" FROM ERP_THANHTOANHOADON A LEFT  JOIN ERP_NHACUNGCAP B ON A.NCC_FK = B.PK_SEQ  " +
						" LEFT JOIN NHOMNHACUNGCAPCN F ON A.NHOMNCCCN = F.PK_SEQ "+
						" LEFT JOIN NHOMNHACUNGCAPCN_NCC G ON F.PK_SEQ = G.NHOMNHACUNGCAPCN_FK "+
						" LEFT JOIN ERP_NHANVIEN NV ON NV.PK_SEQ=  A.NHANVIEN_FK  " +
						" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ=  A.KHACHHANG_FK  " +
						" LEFT JOIN ERP_DONVITHUCHIEN dvth on dvth.pk_seq = a.dvth_fk \n" +
						" INNER JOIN ERP_HINHTHUCTHANHTOAN C ON A.HTTT_FK = C.PK_SEQ " +
						" INNER JOIN NHANVIEN D ON A.NGUOITAO = D.PK_SEQ INNER JOIN NHANVIEN E ON A.NGUOISUA = E.PK_SEQ " +
						" WHERE ((C.PK_SEQ != 100002) OR (C.PK_SEQ = 100002 AND A.TRANGTHAI = 1)) and a.HTTT_FK IN ( '100000' , '100003' ) AND A.CONGTY_FK  = "+obj.getCongtyId()+" ";

 
		
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String nccId = request.getParameter("nccId");
		if(nccId == null)
			nccId = "";
		obj.setNccId(nccId);
		
		String nvId = request.getParameter("nvId");
		if(nvId == null)
			nvId = "";
		obj.setNvId(nvId);
		
		String sotien = (request.getParameter("sotientt").replaceAll(",", ""));
		if(sotien == null)
			sotien = "";
		obj.setSotien(sotien);
		
		String[] loaihoadon = request.getParameterValues("loaihoadon");
		if (loaihoadon != null)
		{
			String _scheme = "";
			for(int i = 0; i < loaihoadon.length; i++)
			{
				_scheme += loaihoadon[i] + ",";
			}
			
			if(_scheme.trim().length() > 0)
			{
				_scheme = _scheme.substring(0, _scheme.length() - 1);
				obj.setLoaihoadon(_scheme);
			}
		}
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String sochungtu = request.getParameter("sochungtu");
		if(sochungtu == null)
			sochungtu = "";
		obj.setSochungtu(sochungtu);
		
		String sohoadon = request.getParameter("sohoadon");
		if(sohoadon == null)
			sohoadon = "";
		obj.setSohoadon(sohoadon);
		
		if(tungay.length() > 0)
			query += " and a.ngayghinhan >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngayghinhan <= '" + denngay + "'";
		
		if(nccId.length() > 0)
			query += " and b.pk_seq = '" + nccId + "'";
		
		if(nvId.length() > 0)
			query += " and nv.pk_seq = '" + nvId + "'";
		
		if(obj.getLoaihoadon().trim().length() > 0)
			query += " and a.pk_seq in ( select thanhtoanhd_fk from ERP_THANHTOANHOADON_HOADON where loaihd in ( " + obj.getLoaihoadon() + " ) ) ";
		
		if(trangthai.length() > 0)
		{
			if(trangthai.equals("0"))
				query += " and a.trangthai = '0' and isnull(a.iskttduyet,0) = 0 ";
			if(trangthai.equals("-1"))
				query += " and a.trangthai = '0' and isnull(a.iskttduyet,0) = 1 ";
			if(trangthai.equals("1"))
				query += " and a.trangthai = '1' ";
			if(trangthai.equals("2"))
				query += " and a.trangthai = '2' ";
			if(trangthai.equals("3"))
				query += " and a.trangthai = '3' ";
		}
		
		if(sochungtu.length() >0){
			query+=" and (cast( a.pk_seq as varchar(10)) like '%"+sochungtu+"%' or cast(a.prefix as varchar(10)) + cast( a.pk_seq as varchar(10)) like '%"+sochungtu+"%') ";
			
		}		
		if(sotien.length()>0){
			query+=" and A.sotientt = "+sotien;
		}
		
		
		if(sohoadon.length() >0){
			query += "  and ( a.pk_seq in (select tt_hd.thanhtoanhd_fk from ERP_THANHTOANHOADON_HOADON tt_hd where tt_hd.sohoadon like '%"+ sohoadon +"%')"
				   + "       or a.pk_seq in (select tt_hd.thanhtoanhd_fk from ERP_THANHTOANHOADON_HOADONBOPHAN tt_hd where tt_hd.sohoadon like '%"+ sohoadon +"%') )";
		}
		
		
		System.out.println("Cau search : "+ query);
		return query;
	}
	
	private String Delete(String tthdId, String userId)
	{
		dbutils db = new dbutils();
		
		String msg = "";
		
		String query = "UPDATE  ERP_THANHTOANHOADON set TRANGTHAI = '2' , NGUOISUA = "+ userId +" , NGAYSUA = '"+ getDateTime() +"' where PK_SEQ = '" + tthdId + "'  ";
		System.out.println(query);
		if(!db.update(query))
		{
			msg = "Kh??ng th??? x??a ERP_THANHTOANHOADON: " + query;
		}
		
		query = " UPDATE ERP_MUAHANG SET ISDACHI = 0, ISHOANTAT = 0, ISKTT = 0 , TRANGTHAI = 0 WHERE PK_SEQ IN (SELECT HOADON_FK FROM ERP_THANHTOANHOADON_HOADON WHERE LOAIHD = 6 AND THANHTOANHD_FK = "+tthdId+" ) ";
		System.out.println(query);
		if(!db.update(query))
		{
			msg = "Kh??ng th??? x??a c???p nh???t DNTT : " + query;
		}
		
		query = " UPDATE ERP_DUYETMUAHANG SET TRANGTHAI = 0 WHERE MUAHANG_FK IN (SELECT HOADON_FK FROM ERP_THANHTOANHOADON_HOADON WHERE LOAIHD = 6 AND THANHTOANHD_FK = "+tthdId+" ) AND LOAICAP_FK = 10004 ";
		System.out.println(query);
		if(!db.update(query))
		{
			msg = "Kh??ng th??? x??a c???p nh???t DNTT : " + query;
		}
		
		
		// ?????M S??? PHI???U T???M ???NG
				
		query = " UPDATE ERP_TAMUNG SET ISDACHI = 0, ISHOANTAT = 0, ISKTT = 0, TRANGTHAI = 0 WHERE PK_SEQ IN (SELECT HOADON_FK FROM ERP_THANHTOANHOADON_HOADON WHERE LOAIHD = 1 AND THANHTOANHD_FK = "+tthdId+" ) ";
		
		System.out.println(query);
		if(!db.update(query))
		{
			msg = "Kh??ng th??? x??a c???p nh???t DNTT : " + query;
		}
		
		query = " UPDATE ERP_DUYETTAMUNG SET TRANGTHAI = 0 WHERE TAMUNG_FK IN (SELECT HOADON_FK FROM ERP_THANHTOANHOADON_HOADON WHERE LOAIHD = 1 AND THANHTOANHD_FK = "+tthdId+") AND LOAICAP_FK = 10004 ";
		System.out.println(query);
		if(!db.update(query))
		{
			msg = "Kh??ng th??? x??a c???p nh???t DNTT : " + query;
		}
		
		db.shutDown();
			
		return msg;
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}
