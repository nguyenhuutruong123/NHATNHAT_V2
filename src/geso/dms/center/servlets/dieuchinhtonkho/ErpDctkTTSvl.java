package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IErpDctkTT;
import geso.dms.center.beans.dieuchinhtonkho.IErpDctkTTList;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpDctkTT;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpDctkTTList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpDctkTTSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpDctkTTSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDctkTTList obj;
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
	    
	    String lsxId = util.getId(querystring);
	    obj = new ErpDctkTTList();
	    
	    if (action.equals("delete"))
	    {	
	    	String msg = this.DeleteChuyenKho(lsxId);
			obj.setMsg(msg);
	    }
	    else
	    {
	    	if(action.equals("chot"))
	    	{
	    		String msg = this.Chot(lsxId);
    			obj.setMsg(msg);
	    	}
	    }
	    
	    obj.setUserId(userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
			
		String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTT.jsp";
		response.sendRedirect(nextJSP);
	}

	private String Chot(String lsxId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
		
			String	query=" select b.KhoTT_FK,a.sanpham_fk,(tonthucte-tonkho) as soluong,solo,ngayhethan,a.ngaynhapkho from ERP_DIEUCHINHTONKHO_SANPHAM a inner join ERP_DIEUCHINHTONKHO b "+
					  "		on a.dieuchinh_fk=b.PK_SEQ  where pk_seq="+lsxId ;
				ResultSet rs=db.get(query);
				while(rs.next())
				{
					String kho_fk=rs.getString("KhoTT_FK");
					String sanpham_fk=rs.getString("sanpham_fk");
					double soluong=rs.getDouble("soluong");
					String solo=rs.getString("solo");
					String ngayhethan=rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");
					
					if(soluong<0)
					{
						query = " Update ERP_KHOTT_SANPHAM set soluong = soluong - '" + Math.abs(soluong) + "', booked = booked - '" + Math.abs(soluong) + "' " +
								" where khott_fk = '" + kho_fk + "' and sanpham_fk = '" + sanpham_fk + "' ";
						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
							db.getConnection().rollback();
							return msg;
						}
						
						query = " Update ERP_KHOTT_SP_CHITIET set soluong = soluong - '" + Math.abs(soluong) + "', booked = booked - '" + Math.abs(soluong) + "' " +
								" where khott_fk = '" + kho_fk + "' and sanpham_fk = '" + sanpham_fk + "' and solo = '" + solo + "' and ngayhethan='"+ ngayhethan +"' and ngaynhapkho='"+ ngaynhapkho +"' ";
						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
							db.getConnection().rollback();
							return msg;
						}
					}
					else
					{
						query = " Update ERP_KHOTT_SANPHAM set soluong = soluong + '" + Math.abs(soluong) + "', available = available + '" + Math.abs(soluong) + "' " +
								" where khott_fk = '" + kho_fk + "' and sanpham_fk = '" + sanpham_fk + "' ";
						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
							db.getConnection().rollback();
							return msg;
						}
						
						query = " Update ERP_KHOTT_SP_CHITIET set soluong = soluong + '" + Math.abs(soluong) + "', available = available + '" + Math.abs(soluong) + "' " +
								" where khott_fk = '" + kho_fk + "' and sanpham_fk = '" + sanpham_fk + "' and solo = '" + solo + "' and ngayhethan='"+ ngayhethan +"' and ngaynhapkho='"+ ngaynhapkho +"' ";
						if(!db.update(query))
						{
							msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
							db.getConnection().rollback();
							return msg;
						}
					}
					
					
					
				}
				rs.close();
				query = "update  ERP_DIEUCHINHTONKHO set trangthai=1 where pk_seq = '" + lsxId + "'";
				if(!db.update(query))
				{
					msg = "2.Khong the xoa: " + query;
					db.getConnection().rollback();
					return msg;
				}
			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
	}

	private String DeleteChuyenKho(String lsxId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
		

		String	query=" select b.KhoTT_FK,a.sanpham_fk,(tonthucte-tonkho) as soluong,solo,ngayhethan,a.ngaynhapkho from ERP_DIEUCHINHTONKHO_SANPHAM a inner join ERP_DIEUCHINHTONKHO b "+
					  "		on a.dieuchinh_fk=b.PK_SEQ  where pk_seq="+lsxId ;
				ResultSet rs=db.get(query);
				while(rs.next())
				{
					String kho_fk=rs.getString("KhoTT_FK");
					String sanpham_fk=rs.getString("sanpham_fk");
					double soluong=rs.getDouble("soluong");
					String solo=rs.getString("solo");
					String ngayhethan=rs.getString("ngayhethan");
					String ngaynhapkho=rs.getString("ngaynhapkho");
					
					query = " Update ERP_KHOTT_SANPHAM set booked = booked - '" + Math.abs(soluong) + "', available = available + '" + Math.abs(soluong) + "' " +
							" where khott_fk = '" + kho_fk + "' and sanpham_fk = '" + sanpham_fk + "' ";
					if(!db.update(query))
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						db.getConnection().rollback();
						return msg;
					}
					
					query = " Update ERP_KHOTT_SP_CHITIET set booked = booked - '" + Math.abs(soluong) + "', available = available + '" + Math.abs(soluong) + "' " +
							" where khott_fk = '" + kho_fk + "' and sanpham_fk = '" + sanpham_fk + "' and solo = '" + solo + "' and ngayhethan='"+ ngayhethan +"' and ngaynhapkho='"+ ngaynhapkho +"' ";
					if(!db.update(query))
					{
						msg = "Khong the cap nhat ERP_KHOTT_SP_CHITIET: " + query;
						db.getConnection().rollback();
						return msg;
					}
					
				}
				rs.close();
			query = "update  ERP_DIEUCHINHTONKHO set trangthai=2 where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "2.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null)
	    {
	    	action = "";
	    }
	    
		IErpDctkTTList obj = new ErpDctkTTList();
	 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpDctkTT lsxBean = new ErpDctkTT();
	    	lsxBean.createRs();
    		
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTTNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTT.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		String nextJSP = request.getContextPath() + "/pages/Center/ErpDieuChinhTonKhoTT.jsp";
	    		response.sendRedirect(nextJSP);
	    		
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpDctkTTList obj)
	{
		String query =  "select a.PK_SEQ, a.trangthai, a.ngaydieuchinh, a.lydo, NV.TEN as nguoitao, b.ten as khodieuchinh, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   " +
						"from ERP_DIEUCHINHTONKHO a inner join ERP_KHOTT b on a.khott_fk = b.pk_seq  " +
						"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 ";
		
		String tungaySX = request.getParameter("tungay");
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = request.getParameter("denngay");
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		

		if(tungaySX.length() > 0)
			query += " and a.ngaydieuchinh >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.ngaydieuchinh <= '" + denngaySX + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
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
