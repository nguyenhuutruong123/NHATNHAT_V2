package geso.dms.distributor.servlets.xuatkho;

import geso.dms.distributor.beans.xuatkho.IErpYeucauxuatkhoNpp;
import geso.dms.distributor.beans.xuatkho.IErpYeucauxuatkhoNppList;
import geso.dms.distributor.beans.xuatkho.imp.ErpYeucauxuatkhoNpp;
import geso.dms.distributor.beans.xuatkho.imp.ErpYeucauxuatkhoNppList;
import geso.dms.distributor.servlets.hoadontaichinhNPP.ErpHoadontaichinhNPPSvl;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpYeucauxuatkhoNppSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpYeucauxuatkhoNppSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpYeucauxuatkhoNppList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    PrintWriter out = response.getWriter();
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
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
    	String lsxId = util.getId(querystring);
	    obj = new ErpYeucauxuatkhoNppList();
	    
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "0";
	    obj.setLoaidonhang(loaidonhang);
	    
	    String nppId = request.getParameter("nppId");
	    if(nppId == null)
	    	nppId = "";
	    //obj.setNppId(nppId);
	    System.out.println("---NPP ID: " + nppId);
	    
    	if(action.equals("chot"))
    	{
    		String msg = this.Chot(lsxId, userId, nppId);
			obj.setMsg(msg);
    	}
    	else if(action.equals("delete"))
    	{
    		String msg = this.Delete(lsxId, nppId);
			obj.setMsg(msg);
    	}
	    
	    obj.setUserId(userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
			
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpYeuCauXuatKhoNpp.jsp";
		response.sendRedirect(nextJSP);
	    
	}

	private String Delete(String lsxId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_YCXUATKHONPP", lsxId, "ngayyeucau", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "";
			
			query = "update ERP_YCXUATKHONPP set trangthai=0 where pk_seq = '" + lsxId + "' and trangthai=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Trạng thái PXK không hợp lệ, vui lòng kiểm tra lại: " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			query = "update ERP_HOADONNPP set hoantat = 0 ,trangthai = 1,NgaySua='"+getDateTime()+"'  where pk_seq in ( select hoadon_fk from ERP_YCXUATKHONPP_DDH where ycxk_fk = '" + lsxId + "' ) and trangthai in (2)  ";
			if(db.updateReturnInt(query)<=0)
			{
				msg = "Hóa đơn đã chuyển trạng thái ,vui lòng xem lại ";
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			query = "delete ERP_YCXUATKHONPP_DDH where ycxk_fk = '" + lsxId + "' ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_YCXUATKHONPP_DDH " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			query = "delete ERP_YCXUATKHONPP_SANPHAM where ycxk_fk = '" + lsxId + "' ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_YCXUATKHONPP_SANPHAM " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			query = "delete ERP_YCXUATKHONPP_SANPHAM_CHITIET where ycxk_fk = '" + lsxId + "' ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_YCXUATKHO_SANPHAM " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			query = "delete ERP_YCXUATKHONPP  where pk_seq = '" + lsxId + "' ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_YCXUATKHONPP " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			
			query  = "delete PhieuXuat_SoChungTu   where tableName = 'ERP_YCXUATKHONPP' and Id_FK = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "Không thể xóa PhieuXuat_SoChungTu " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			

			//Tu dong tao nhan hang
			query = " select count(*) as soDONG from NHAPHANG where YCXKNPP_FK = '" + lsxId + "' and trangthai = '1' ";
			
			int soDONG = 0;
			ResultSet rs = db.get(query);
			{
				if(rs.next())
					soDONG = rs.getInt("soDONG");
				rs.close();
			}
			
			if(soDONG > 0)
			{
				Utility.rollback_and_shutdown(db);
				msg = "Hóa đơn đã có nhận hàng, bạn không thể hủy / xóa ";
				return msg;
			}
			
			//XOA NHAN HANG
			query = " delete NHAPHANG_SP where nhaphang_fk in ( select pk_seq from NHAPHANG where YCXKNPP_FK = '" + lsxId + "' )  ";
			if(!db.update(query))
			{
				Utility.rollback_and_shutdown(db);
				msg = "Không thể cập nhật NHAPHANG_SP:  " + query;
				return msg;
			}
			
			query = " delete NHAPHANG where YCXKNPP_FK = '" + lsxId + "'   ";
			if(!db.update(query))
			{
				Utility.rollback_and_shutdown(db);
				msg = "Không thể cập nhật NHAPHANG_SP:  " + query;
				return msg;
			}
		
			
			
			
			
			Utility.commit_and_shutdown(db);
			return "Xóa thành công!";
		}
		catch (Exception e) 
		{
			Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
		
		
	}
	
	private String Chot(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		
		String msg="";
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_YCXUATKHONPP", lsxId, "ngayyeucau", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String kq = ErpHoadontaichinhNPPSvl.Chot_YCXK_NPP( db,lsxId, userId, nppId) ;
			if(kq.length() > 0)
			{
				Utility.rollback_and_shutdown(db);
				return kq;
			}
			
			
			
			Utility.commit_and_shutdown(db);
			return " Chốt thành công! ";
		}
		catch (Exception e) 
		{
			Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    String action = request.getParameter("action");
	    if (action == null)
	    {
	    	action = "";
	    }
	    
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "0";
	    
	    
		IErpYeucauxuatkhoNppList obj = new ErpYeucauxuatkhoNppList();
		obj.setLoaidonhang(loaidonhang);
		
		
	 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    obj.setUserId(userId);
	    
	    
	    obj.getNppInfo();
	    
	    String khId = request.getParameter("khId");
	    if(khId == null)
	    	khId = "";
	    obj.setNppTen(khId);
	    

	    System.out.println("k = "+ khId);
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpYeucauxuatkhoNpp lsxBean = new ErpYeucauxuatkhoNpp();
	    	lsxBean.setUserId(userId);
	    	lsxBean.createRs();
	    	
	    	session.setAttribute("dvkdId", "");
			session.setAttribute("kbhId", "");
			session.setAttribute("nppId", "");
    		
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpYeuCauXuatKhoNppNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/ErpYeuCauXuatKhoNpp.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpYeuCauXuatKhoNpp.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpYeucauxuatkhoNppList obj)
	{
		Utility util = new Utility();
		String query =
				"select a.sonetId,a.PK_SEQ, a.trangthai, a.ngayyeucau, isnull(c.ten, d.ten) as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, " +
						"	 (	Select cast(YCXK1.DDH_FK as varchar(10)) + ',' AS [text()]  " +
						"		From ERP_YCXUATKHONPP_DDH YCXK1   " +
						"		Where YCXK1.ycxk_fk = a.pk_seq  " +
						"		For XML PATH ('') )  as ddhIds    " +
						"from ERP_YCXUATKHONPP a inner join KHO b on a.kho_fk = b.pk_seq " +
						"	left join NHAPHANPHOI c on a.NPP_DAT_FK = c.pk_seq " +
						"	left join KHACHHANG d on a.khachhang_fk = d.pk_seq  " +
						"	inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"	inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.npp_fk = '" + obj.getNppId() + "' and a.kho_fk in "+util.quyen_kho(obj.getUserId()); 
		
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
	
		
		String khId = request.getParameter("khId");
		if(khId == null)
			khId = "";
		obj.setNppTen(khId);
		
		if(tungay.length() > 0)
			query += " and a.ngayyeucau >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngayyeucau <= '" + denngay + "'";
	
		if(trangthai.length() > 0)
		{
			if(trangthai.equals("0"))
				query += " and a.TrangThai = '" + trangthai + "'";
			else
				query += " and a.TrangThai >= '" + trangthai + "'";
		}
		
		if(khId.length() > 0)
		{
			query += " and isnull(c.maFAST + ' - ' + c.TEN ,d.maFAST + ' - ' + d.TEN) = N'" + khId + "'";
		}
		
		System.out.print(query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
