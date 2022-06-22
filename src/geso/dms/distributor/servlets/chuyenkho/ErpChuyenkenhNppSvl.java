package geso.dms.distributor.servlets.chuyenkho;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenkenhNpp;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenkenhNppList;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenkenhNpp;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenkenhNppList;

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

public class ErpChuyenkenhNppSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpChuyenkenhNppSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpChuyenkenhNppList obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("userId")));
	    
	    String action = util.getAction(querystring);
		   
	    String lsxId = util.getId(querystring);
	    obj = new ErpChuyenkenhNppList();
	    obj.setUserId(userId);
	    
	    String type = util.antiSQLInspection(request.getParameter("type"));
	    if(type == null)
	    	type = "chuyenkenh";
	    obj.setType(type);
	    
	    if (action.equals("delete") )
	    {	
	    	String msg = this.DeleteChuyenKho(lsxId, userId);
			obj.setMsg(msg);
	    }
	    else if(action.equals("chot"))
    	{
    		String msg = this.Chot(lsxId, userId);
    		System.out.println("___Chot__"+msg);
    		obj.setMsg(msg);
    	}
	    
	    obj.init("");
		session.setAttribute("obj", obj);
			
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKenhNpp.jsp";
		response.sendRedirect(nextJSP);
	}

	private String Chot(String id, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKenh", id, "NgayChuyen", db);
			if(msg.length()>0)
				return msg;
			
			db.getConnection().setAutoCommit(false);
			
			String query = "update ERP_ChuyenKenh set trangthai = '1' where pk_seq = '" + id + "' and trangthai=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "2.Khong the xoa: " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			query=
			" 	select count(*)   as SoDong " +
			" 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk " +
			" 	where chuyenkenh_fk = '" + id + "' " ;
			
			System.out.println("::::_01"+query);
			int SoDong=0;
			ResultSet rs =db.get(query);
			 while(rs.next())
			 {
				 SoDong=rs.getInt("SoDong");
			 }
			 if(rs!=null)rs.close();
			 
			 
		
			query=	" 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan   " +
			" 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk " +
			" 	where chuyenkenh_fk = '" + id + "' " +
			" 	group by a.khoxuat_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan , a.ngaychuyen,b.ngaynhapkho " ;
			System.out.println("lay san pham chuyen kenh : "+ query);
			ResultSet nkRs=db.get(query);
			while (nkRs.next())
			{
				String kho_fk=nkRs.getString("kho_fk");
				String npp_fk=nkRs.getString("npp_fk");
				String kbh_fk=nkRs.getString("kbh_fk");
				String sanpham_fk=nkRs.getString("sanpham_fk");
				String solo=nkRs.getString("solo");
				double soluong=nkRs.getDouble("tongxuat");
				String ngayhethan=nkRs.getString("ngayhethan");
				String ngaychuyen=nkRs.getString("ngaychuyen");
				String ngaynhapkho=nkRs.getString("ngaynhapkho");
				
				
				msg=util.Update_NPP_Kho_Sp_Chitiet( ngaychuyen, "Chuyển kênh NPP -" + id, db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, ngayhethan, ngaynhapkho, (-1)*soluong, (-1)*soluong, 0.0, 0.0, 0.0);
				if(msg.length()>0)
				{
					Utility.rollback_and_shutdown(db);
					return "1.1" +msg;
				}
				msg=util.Update_NPP_Kho_Sp( ngaychuyen, "Chuyển kênh NPP -" + id, db, kho_fk, sanpham_fk, npp_fk, kbh_fk, (-1)*soluong, (-1)*soluong, 0.0, 0.0);
				if(msg.length()>0)
				{
					Utility.rollback_and_shutdown(db);
					return "1.2" +msg;
				}
			}

			
			//TANG KHO CUA KENH NHAN
			query = " 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk, a.khonhan_fk, loaichuyen, a.npp_fk, a.kbh_nhan_fk, b.sanpham_fk, b.solo, b.ngayhethan, SUM(b.soluong) as tongnhan  " +
					" 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk " +
					" 	where chuyenkenh_fk = '" + id + "' " +
					" 	group by a.khoxuat_fk, a.khonhan_fk, loaichuyen, a.npp_fk, a.kbh_nhan_fk, b.solo, b.ngayhethan, b.sanpham_fk ,a.ngaychuyen,b.ngaynhapkho";
			System.out.println("---CHECK TANG KHO NHAN: " + query);
			rs = db.get(query);
			
			String nppID = "";
			{
				while(rs.next())
				{
					String loaichuyen = rs.getString("loaichuyen");
					String khoID = rs.getString("kho_fk");  //CHUYEN KENH
					if(loaichuyen.equals("chuyenkho"))  //CHUYEN GIUA CAC KHO VOI NHAU
					khoID = rs.getString("khonhan_fk");
					
					nppID = rs.getString("npp_fk");
					String kbhID = rs.getString("kbh_nhan_fk");
					String spID = rs.getString("sanpham_fk");
					String solo = rs.getString("solo");
					String ngayhethan = rs.getString("ngayhethan");
					double tongnhan = rs.getDouble("tongnhan");
					String ngaychuyen = rs.getString("ngaychuyen");
					String ngaynhapkho = rs.getString("ngaynhapkho");

					msg=util.Update_NPP_Kho_Sp_Chitiet( ngaychuyen, "Chuyển kênh NPP + " + id, db, khoID, spID, nppID, kbhID, solo, ngayhethan, ngaychuyen, tongnhan, 0.0, tongnhan, tongnhan, 0.0);
					if(msg.length()>0)
					{
						Utility.rollback_and_shutdown(db);
						return "2.1" +msg;
					}

					msg=util.Update_NPP_Kho_Sp( ngaychuyen, "Chuyển kênh NPP + " + id, db, khoID, spID, nppID, kbhID, tongnhan, 0.0, tongnhan, 0.0);
					if(msg.length()>0)
					{
						Utility.rollback_and_shutdown(db);
						return  "2.2" +msg;
					}
				}
				rs.close();
			}
			msg= util.Check_Kho_Tong_VS_KhoCT(nppID, db);
			if(msg.length()>0)
			{
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Utility.rollback_and_shutdown(db);
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		
		return "";
	}

	private String DeleteChuyenKho(String lsxId, String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_ChuyenKenh", lsxId, "NgayChuyen", db);
			if(msg.length()>0)
				return msg;
		
			
			db.getConnection().setAutoCommit(false);

			String query = "update ERP_ChuyenKenh set trangthai = '2' where pk_seq = '" + lsxId + "' and trangthai=0 ";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "2.Khong the xoa: " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			
			//TANG KHO NGUOC LAI
//			query = " update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
//					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
//					" from " +
//					" ( " +
//					" 	select a.khoxuat_fk as kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat ,b.ngayhethan  " +
//					" 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk " +
//					" 	where chuyenkenh_fk = '" + lsxId + "' " +
//					" 	group by a.khoxuat_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk ,b.ngayhethan " +
//					" ) " +
//					" CT inner join NHAPP_KHO_CHITIET kho on CT.kho_fk = kho.KHO_FK  " +
//					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.solo = kho.SOLO and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk and CT.ngayhethan=kho.NGAYHETHAN ";
//			
//			System.out.println("::_Tang_"+query);
//			if(!db.update(query))
//			{
//				msg = "Không thể cập nhật NHAPP_KHO_CHITIET " + query;
//				Utility.rollback_and_shutdown(db);
//				return msg;
//			}
//			
//			query = " update kho set kho.AVAILABLE = kho.AVAILABLE + CT.tongxuat, " +
//					" 			   kho.BOOKED = kho.BOOKED - CT.tongxuat " +
//					" from " +
//					" ( " +
//					" 	select a.khoxuat_fk as kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk, SUM(b.soluong) as tongxuat  " +
//					" 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk " +
//					" 	where chuyenkenh_fk = '" + lsxId + "' " +
//					" 	group by a.khoxuat_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk " +
//					" ) " +
//					" CT inner join NHAPP_KHO kho on CT.kho_fk = kho.KHO_FK  " +
//					" 	and CT.sanpham_fk = kho.SANPHAM_FK and CT.NPP_FK = kho.npp_fk and CT.KBH_FK = kho.kbh_fk ";
//			
//			System.out.println("::_Giam_"+query);
//			if(!db.update(query))
//			{
//				msg = "Không thể cập nhật NHAPP_KHO " + query;
//				Utility.rollback_and_shutdown(db);
//				return msg;
//			}
			query=	" 	select a.ngaychuyen,b.ngaynhapkho,a.khoxuat_fk as kho_fk, a.npp_fk, a.kbh_fk, b.sanpham_fk, b.solo, SUM(b.soluong) as tongxuat,b.NgayHetHan   " +
			" 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk " +
			" 	where chuyenkenh_fk = '" + lsxId + "' " +
			" 	group by a.khoxuat_fk, a.npp_fk, a.kbh_fk, b.solo, b.sanpham_fk,b.NgayHetHan , b.ngaynhapkho,a.ngaychuyen " ;
			
			ResultSet nkRs=db.get(query);
			
			while(nkRs.next()) 
			{
				String kho_fk=nkRs.getString("kho_fk");
				String npp_fk=nkRs.getString("npp_fk");
				String kbh_fk=nkRs.getString("kbh_fk");
				String sanpham_fk=nkRs.getString("sanpham_fk");
				String solo=nkRs.getString("solo");
				double soluong=nkRs.getDouble("tongxuat");
				String ngayhethan=nkRs.getString("ngayhethan");
				String ngaychuyen=nkRs.getString("ngaychuyen");
				String ngaynhapkho=nkRs.getString("ngaynhapkho");
				
				msg=util.Update_NPP_Kho_Sp_Chitiet( ngaychuyen, "Chuyển kênh NPP" + lsxId, db, kho_fk, sanpham_fk, npp_fk, kbh_fk, solo, ngayhethan, ngaynhapkho, 0.0,(-1)*soluong, soluong, soluong, 0.0);
				if(msg.length()>0)
				{
					Utility.rollback_and_shutdown(db);
					return msg;
				}
				msg= util.Update_NPP_Kho_Sp( ngaychuyen, "Chuyển kênh NPP" + lsxId, db, kho_fk, sanpham_fk, npp_fk, kbh_fk, 0.0, (-1)*soluong, soluong, 0.0);
				if(msg.length()>0)
				{
					Utility.rollback_and_shutdown(db);
					return msg;
				}
			}
			msg= util.Check_Kho_Tong_VS_KhoCT(util.getIdNhapp(userId), db);
			if(msg.length()>0)
			{
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}
		
		
		return "";
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		Utility util = new Utility();
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    String action = util.antiSQLInspection(request.getParameter("action"));
	    if (action == null)
	    {
	    	action = "";
	    }
	    
		IErpChuyenkenhNppList obj = new ErpChuyenkenhNppList();
	 
	   
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("userId"))); 
	    
	    String type = util.antiSQLInspection(request.getParameter("type"));
	    if(type == null)
	    	type = "chuyenkenh";
	    obj.setType(type);
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpChuyenkenhNpp lsxBean = new ErpChuyenkenhNpp();
	    	lsxBean.setType(type);
	    	lsxBean.setUserId(userId);
	    	lsxBean.createRs();
    		
	    	session.setAttribute("khoxuatID", "");
			session.setAttribute("kenhxuatID", "");
			session.setAttribute("nppID", "");
			
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKenhNppNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(util.antiSQLInspection(request.getParameter("nxtApprSplitting"))));
		    	obj.setUserId(userId);
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKenhNpp.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setUserId(userId);
		    	obj.init(search);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpChuyenKenhNpp.jsp";
	    		response.sendRedirect(nextJSP);
	    		
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpChuyenkenhNppList obj)
	{
		Utility util = new Utility();
		String query =  "select a.PK_SEQ, a.trangthai, a.ngaychuyen, a.ghichu as lydo, NV.TEN as nguoitao, b.ten as khoxuat, c.diengiai as kbhTEN, d.diengiai as kbhNHANTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, isnull(a.loaichuyen, 'chuyenkenh') as loaichuyen   " +
						"from ERP_CHUYENKENH a inner join ERP_KHOTT b on a.khoxuat_fk = b.pk_seq   " +
						"	inner join KENHBANHANG c on a.kbh_fk = c.pk_seq inner join KENHBANHANG d on a.kbh_nhan_fk = d.pk_seq  " +
						"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 and a.loaichuyen = '" + obj.getType() + "' ";
		
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		query += " and a.NPP_FK = '" + nppId + "' ";
		
		String tungaySX = util.antiSQLInspection(request.getParameter("tungay"));
		if(tungaySX == null)
			tungaySX = "";
		obj.setTungayTao(tungaySX);
		
		String denngaySX = util.antiSQLInspection(request.getParameter("denngay"));
		if(denngaySX == null)
			denngaySX = "";
		obj.setDenngayTao(denngaySX);
		
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String ctId = util.antiSQLInspection(request.getParameter("chungtu"));
		System.out.println("ma ne:" +ctId);
		if(ctId == null)
			ctId = "";
		obj.setctId(ctId);
		

		if(tungaySX.length() > 0)
			query += " and a.ngaychuyen >= '" + tungaySX + "'";
		
		if(denngaySX.length() > 0)
			query += " and a.ngaychuyen <= '" + denngaySX + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(ctId.length() > 0)
			query += " and a.PK_SEQ like'%" + ctId + "%'";
		
		System.out.print("Search:" + query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
