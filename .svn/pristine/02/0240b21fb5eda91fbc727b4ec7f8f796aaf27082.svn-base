package geso.dms.center.servlets.thongbao;

import geso.dms.center.beans.thongbao.IThongbao;
import geso.dms.center.beans.thongbao.IThongbaoList;
import geso.dms.center.beans.thongbao.imp.Thongbao;
import geso.dms.center.beans.thongbao.imp.ThongbaoList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThongbaoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ThongbaoSvl() {
        super();
    }
    private boolean deletefile(String file)
	{
		System.out.println(file);
		  File f1 = new File(file);
		  boolean success = f1.delete();
		  if (!success)
		  {
			return false;
		  }
		  else
		  {
			 return true;
		   }
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");	    	    
	    HttpSession session = request.getSession();
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
	    String userId = (String) session.getAttribute("userId");
	    
	    IThongbaoList obj =  new ThongbaoList();
	    
	    String task = request.getParameter("task");
	    if(task == null)
	    	task = "";
	    obj.setTask(task);
	    System.out.println("TASK: "+task);
	    
	    String viewMode = request.getParameter("viewMode");
	    if(viewMode == null)
	    	viewMode = "1";
	    obj.setViewMode(viewMode);
	    
	    String loaivanban = request.getParameter("loaivanban");
	    if(loaivanban == null)
	    	loaivanban = "";
	    obj.setLoaithongbao(loaivanban);
	    
	    if(task.trim().length() > 0)
	    {
	    	if(task.equals("xoa"))
	    	{
	    		String pk = request.getParameter("id");	    		
	    		dbutils db = new dbutils();
	    		try 
	    		{
					db.getConnection().setAutoCommit(false);
					String query = "select filename from thongbao where pk_seq='"+pk+"'";
					System.out.println("cau select: "+query);
					ResultSet rs = db.get(query);
					String filename="";
					try 
					{
						rs.next();
						filename = rs.getString("filename");
					}
					catch (Exception e) 
					{
						db.update("rollback");
						obj.setMsg("Không thể xóa, lỗi: không thể lấy tên file");
					}
					if(!filename.equals("0"))
					{
						if(!this.deletefile("C:\\java-tomcat\\dinhkem\\"+filename))
						{
							db.update("rollback");
							obj.setMsg("Không thể xóa, lỗi: không thể lấy delete file");
						}
					}
					query = "delete thongbao_nhanvien where thongbao_fk= '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}
		    		query = "delete THONGBAO_VBHD where thongbao_fk= '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}
		    		query = "delete THONGBAO_VBCC where thongbao_fk= '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}
		    		query = "delete THONGBAO_VBTT where thongbao_fk= '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}
		    		query = "delete THONGBAO_VBSDBS where thongbao_fk= '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}query = "delete THONGBAO_DDKD_TRALOI where thongbao_fk= '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}
		    		query = "delete thongbao where pk_seq = '" + pk+ "'";
		    		if(!db.update(query))
		    		{
		    			db.update("rollback");
		    			obj.setMsg("Không thể xóa, lỗi: "+ query);
		    		}
		    		
		    		db.getConnection().commit();
					db.getConnection().setAutoCommit(true);
	    		} 
	    		catch (Exception e)
	    		{
	    			db.update("rollback");
	    			obj.setMsg("Không thể xóa, lỗi: "+ e.getMessage());
				}
	    	}
	    	else if(task.equals("1"))
	    	{
	    		obj =  new ThongbaoList(userId);
	    		obj.setUserId(userId);
	    		obj.setViewMode(viewMode);
	    		obj.setTask(task);
	    		//obj.setLoaithongbao(loaivanban);
	    		
	    		obj.initNhanvien("");
	    		
	    		session.setAttribute("obj", obj);
	    		String nextJSP = request.getContextPath() + "/pages/Center/ThongBaoNhanVien.jsp";
	    		response.sendRedirect(nextJSP);
	    		return;
	    	}
	    	else if(task.equals("2"))
	    	{
	    		obj =  new ThongbaoList(userId);
	    		obj.setUserId(userId);
	    		obj.setViewMode(viewMode);
	    		obj.setLoaithongbao(loaivanban);
	    		
	    		obj.init("");
	    		
	    		session.setAttribute("obj", obj);
	    		String nextJSP = request.getContextPath() + "/pages/Center/ThongBao.jsp";
	    		response.sendRedirect(nextJSP);
	    		return;
	    	}
	    }
	    
	    obj =  new ThongbaoList();
	    
	    obj.setUserId(userId);
	    System.out.println("___Loai van ban: " + loaivanban);
	    obj.setViewMode(viewMode);
	    System.out.println("___VIEW MODE: " + viewMode);
	    obj.setLoaithongbao(loaivanban);
	    
	    obj.init("");
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/ThongBao.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");	    	    
	    HttpSession session = request.getSession();	
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    String userId = (String) session.getAttribute("userId");
	    
	    IThongbaoList obj = new ThongbaoList();	  
	    Utility ulti=new Utility();
	    
	    String maso = ulti.antiSQLInspection(request.getParameter("maso"));
	    String trangthai = ulti.antiSQLInspection(request.getParameter("trangthai"));
	    String ngaybatdau = ulti.antiSQLInspection(request.getParameter("ngaybatdau"));
	    String ngayketthuc = ulti.antiSQLInspection(request.getParameter("ngayketthuc"));
	    String tieude =ulti.antiSQLInspection( request.getParameter("tieude"));
	    String noidung = ulti.antiSQLInspection(request.getParameter("noidung"));
	    
	    String viewMode = request.getParameter("viewMode");
		if(viewMode == null)
			viewMode = "1";
		obj.setViewMode(viewMode);
		
		String loaivanban = request.getParameter("loaivanban");
	    if(loaivanban == null)
	    	loaivanban = "";
	    obj.setLoaithongbao(loaivanban);
	    String task = request.getParameter("task");
	    if(task == null)
	    	task = "";
	    obj.setTask(task); 
	   
 	    if(ngaybatdau == null)
	    	ngaybatdau = "";
	    obj.setNgaybatdau(ngaybatdau);
	    
	    if(tieude == null)
	    	tieude = "";
	    obj.setTieude(tieude);
	    
	    if(noidung == null)
	    	noidung = "";
	    obj.setNoidung(noidung);
	    
	    if(ngayketthuc == null)
	    	ngayketthuc = "";
	    obj.setNgayketthuc(ngayketthuc);
	    
	    if(maso == null)
	    	maso = "";
	    obj.setId(maso);
	    
	    Utility util = new Utility();
	    
	    String query = "select   ct.pk_seq,ct.trangthai, ct.tinhtrang,ct.noidung,ct.tieude, ct.filename, ct.ngaybatdau, case when len(ct.ngayketthuc) <= 0 then N'Vô thời hạn' else ct.ngayketthuc end as ngayketthuc,ct.ngaytao,ct.nguoitao,ct.ngaysua,ct.nguoisua ,NV.TEN as TENNV,NV.PK_SEQ as MANV,NV2.TEN as TENNVS,NV2.PK_SEQ as MANVS, ct.nguoitao as nguoitaoTB from thongbao ct inner join NHANVIEN nv on ct.nguoitao = nv.PK_SEQ inner join NHANVIEN nv2 on ct.NGUOISUA = nv2.PK_SEQ where CT.trangthai != '2' AND 1 = 1 ";
	    	query += " and ct.nguoitao = '" + userId + "' ";
	    
	    if(maso.length() > 0)
	    	query += " and cast(ct.pk_seq as nvarchar(20)) like '%" + maso + "%'";
	    if(ngaybatdau.length() > 0)
	    	query += " and ct.ngaybatdau >= '" + ngaybatdau + "'";
	    if(loaivanban.length() > 0)
	    	query += " and ct.loaithongbao = '" + loaivanban + "'";
	    if(ngayketthuc.length() > 0)
	    	query += " and ct.ngayketthuc <= '" + ngayketthuc + "' and ct.ngayketthuc != ''";
	    if(tieude.length() > 0)
	    	query += " and upper(dbo.ftBoDau(ct.tieude)) like upper(N'%" + util.replaceAEIOU(tieude) + "%')";
	    if(noidung.length() > 0)
	    	query += " and upper(dbo.ftBoDau(ct.noidung)) like upper(N'%" + util.replaceAEIOU(noidung) + "%')";
	    
	    String action = request.getParameter("action");
	    System.out.println("\n\n serch "+query);
	    //System.out.println(" action"+action);
	    if(action.equals("search"))
	    {
		    obj.init(query);
			session.setAttribute("obj", obj);					
			String nextJSP = request.getContextPath() + "/pages/Center/ThongBao.jsp";
			response.sendRedirect(nextJSP);
	    }
	    else if (action.equals("nhanvien"))
	    {
    	    if(trangthai == null)
    	    	trangthai = "";
    	    obj.setTrangthai(trangthai);
    	    query= "select a.ngaybatdau,a.ngayketthuc,a.pk_seq,a.tieude, a.tinhtrang,a.filename, a.noidung,b.trangthai, a.nguoitao as nguoitaoTB,c.ten as tennv,a.loaithongbao,a.nguoitao,a.nguoisua,a.ngaytao,a.ngaysua from thongbao a "
    	    		+ "inner join thongbao_nhanvien b on a.pk_seq = b.thongbao_fk "
    	    		+ "inner join nhanvien c on c.pk_seq = a.nguoitao "

    	    		+ "where  A.trangthai != '2' AND nhanvien_fk='"+userId+"' and 1=1 ";
    	    if(maso.length() > 0)
    	    	query += " and a.pk_seq = '" + maso + "'";
    	    if(trangthai.length() > 0)
    	    	query += " and b.trangthai= '" + trangthai + "'";
    	    if(ngaybatdau.length() > 0)
    	    	query += " and a.ngaybatdau >= '" + ngaybatdau + "'";
    	    if(ngayketthuc.length() > 0)
    	    	query += " and a.ngayketthuc <= '" + ngayketthuc + "'";
    	    if(tieude.length() > 0)
    	    	query += " and a.tieude like N'%" + tieude + "%'";
    	    if(noidung.length() > 0)
    	    	query += " and a.noidung like N'%" + noidung + "%'";
    		 if(loaivanban.trim().length() > 0)
    			 query += " and a.loaithongbao = '" + loaivanban + "' ";
    	    obj.initNhanvien(query);
			session.setAttribute("obj", obj);					
			String nextJSP = request.getContextPath() + "/pages/Center/ThongBaoNhanVien.jsp";
			response.sendRedirect(nextJSP);
	    } 
	    else if(action.equals("new"))
	    {
	    	IThongbao tbBean = new Thongbao();	
	    	tbBean.createRs();
	    	tbBean.setLoaithongbao(loaivanban);
	    	obj.setViewMode(viewMode);
	    	
	    	session.setAttribute("tbBean", tbBean);		
	    	
	    	System.out.println("__Vao tao moi: --  ViewMode la: " + viewMode + "  -- Trong Bean: " + obj.getViewMode());
			String nextJSP = request.getContextPath() + "/pages/Center/ThongBaoNew.jsp";
			if(viewMode.equals("0"))
				nextJSP = request.getContextPath() + "/pages/Center/ThongBaoNew_NPP.jsp";
			
			response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		obj = new ThongbaoList();
	    		obj.setViewMode(viewMode);
	    
		    	System.out.println("nxtApprSplitting "+Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.init(query);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	String nextJSP = request.getContextPath() + "/pages/Center/ThongBao.jsp";
		    	response.sendRedirect(nextJSP);
		    }
	    	else if(action.equals("viewnv") || action.equals("nextnv") || action.equals("prevnv"))
	    	{
	    		obj = new ThongbaoList();
	    		obj.setViewMode(viewMode);
	    		
	    	    if(trangthai == null)
	    	    	trangthai = "";
	    	    obj.setTrangthai(trangthai);
	    	    tieude = request.getParameter("tieude");
	    	    if(ngaybatdau == null)
	    	    	ngaybatdau = "";
	    	    if(tieude == null)
	    	    	tieude = "";
	    	    obj.setTieude(tieude);
	    	    query= "select a.ngaybatdau,a.ngayketthuc,a.pk_seq,a.tieude, a.tinhtrang,a.filename, a.noidung,b.trangthai, a.nguoitao as nguoitaoTB,c.ten as tennv,a.loaithongbao,a.nguoitao,a.nguoisua,a.ngaytao,a.ngaysua from thongbao a "
	    	    		+ "inner join thongbao_nhanvien b on a.pk_seq = b.thongbao_fk "
	    	    		+ "inner join nhanvien c on c.pk_seq = a.nguoitao "

	    	    		+ "where  A.trangthai != '2' AND nhanvien_fk='"+userId+"' and 1=1 ";
	    	    if(maso.length() > 0)
	    	    	query += " and a.pk_seq = '" + maso + "'";
	    	    if(trangthai.length() > 0)
	    	    	query += " and b.trangthai= '" + trangthai + "'";
	    	    if(ngaybatdau.length() > 0)
	    	    	query += " and a.ngaybatdau >= '" + ngaybatdau + "'";
	    	    if(ngayketthuc.length() > 0)
	    	    	query += " and a.ngayketthuc <= '" + ngayketthuc + "'";
	    	    if(tieude.length() > 0)
	    	    	query += " and a.tieude like N'%" + tieude + "%'";
	    	    if(noidung.length() > 0)
	    	    	query += " and a.noidung like N'%" + noidung + "%'";
	    		 if(loaivanban.trim().length() > 0)
	    			 query += " and a.loaithongbao = '" + loaivanban + "' ";
		    	System.out.println("nxtApprSplitting sxl"+Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	obj.setUserId(userId);
		    	obj.init(query);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	String nextJSP = request.getContextPath() + "/pages/Center/ThongBaoNhanVien.jsp";
		    	response.sendRedirect(nextJSP);
	    	}
	    	else
	    	{
		    	obj = new ThongbaoList();
		    	obj.setViewMode(viewMode);
		    	obj.setLoaithongbao(loaivanban);
		    	obj.init(query);
				obj.setUserId(userId);				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
	    		String nextJSP = request.getContextPath() + "/pages/Center/ThongBao.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	}

}
