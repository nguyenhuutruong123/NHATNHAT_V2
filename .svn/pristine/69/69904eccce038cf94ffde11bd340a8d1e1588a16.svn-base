package geso.dms.distributor.servlets.catalog;

import geso.dms.center.beans.nhomsanpham.INhomsanpham;
import geso.dms.center.beans.nhomsanpham.INhomsanphamList;
import geso.dms.center.beans.nhomsanpham.imp.Nhomsanpham;
import geso.dms.center.beans.nhomsanpham.imp.NhomsanphamList;
import geso.dms.center.beans.thongtinsanpham.IThongtinsanphamList;
import geso.dms.center.beans.thongtinsanpham.imp.ThongtinsanphamList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.catalog.Icatalog;
import geso.dms.distributor.beans.catalog.IcatalogList;
import geso.dms.distributor.beans.catalog.imp.catalog;
import geso.dms.distributor.beans.catalog.imp.catalogList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

public class catalogNewSvl extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	public catalogNewSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		Icatalog nspBean = new catalog();

		// Collecting data from NhomsanphamNew.jsp
		String contentType = request.getContentType();
		Utility util = new Utility();
		String action = "", userId = "", id="", chungloai="", ghichu="", nhanhang="", nspId="", ten="", filename="", diengiai="";
		String folderupload= getServletContext().getInitParameter("catalogpath");
		boolean error = false;
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, folderupload, 20000000, "UTF-8");
			try 
			{
			 action= multi.getParameter("action");
			 userId = util.antiSQLInspection(multi.getParameter("userId"));
			 nspBean.setUserId(userId);
			 ten = util.antiSQLInspection(multi.getParameter("ten"));
			 nspBean.setTen(ten);

			 id = util.antiSQLInspection(multi.getParameter("nspId"));
			 nspBean.setId(id);
			 
			chungloai = util.antiSQLInspection(multi.getParameter("chungloai"));
			if (!(chungloai == null))
				nspBean.setChungloai(chungloai);
			
			ghichu = util.antiSQLInspection(multi.getParameter("ghichu"));
			if (!(ghichu == null))
				nspBean.setGhichu(ghichu);

			 nhanhang = util.antiSQLInspection(multi.getParameter("nhanhang"));
			 if (!(nhanhang == null))
					nspBean.setNhanhang(nhanhang);
			 
			if (ten != null && ten.trim().length() > 0) {	nspBean.setTen(ten); }
			else 
			{
				nspBean.setMsg("Vui lòng nhập vào nhóm sản phẩm.");
				error = true;
				action = util.antiSQLInspection(multi.getParameter("action"));
			}
			
			String ma = util.antiSQLInspection(multi.getParameter("Ma"));
			if (!(ma == null))
				nspBean.setMa(ma);
					 
				 Enumeration files = multi.getFileNames();
					String filenameu = "";
					while (files.hasMoreElements())
					{
						String name = (String) files.nextElement();
						filenameu = multi.getFilesystemName(name);
						System.out.println("name  " + name);
						
						File f=new File(folderupload+filenameu);
						f.renameTo(new File (folderupload+ma+"___"+filenameu));
					}

					 filename =  filenameu;

					 String fileName1 = multi.getParameter("tenfile");
						if (fileName1 == null)
							fileName1 = "";
				 if (!(filename == null))
					nspBean.setDuongdan(ma+"___"+filename);
				 else
					 nspBean.setDuongdan(fileName1);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		else
		{
			geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
			if(!csdr.__validate_post())
			{
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
			userId = util.antiSQLInspection(request.getParameter("userId"));
			System.out.println("userId  " + userId);
			ten = util.antiSQLInspection(request.getParameter("ten"));
			nspBean.setTen(ten);

			nspId = util.antiSQLInspection(request.getParameter("nspId"));
			System.out.println("nspId  " + nspId);
			nspBean.setUserId(userId);

			if (ten != null && ten.trim().length() > 0) {	nspBean.setTen(ten); }
			else 
			{
				nspBean.setMsg("Vui lòng nhập vào nhóm sản phẩm.");
				error = true;
				action = util.antiSQLInspection(request.getParameter("action"));
			}
		}
		
		//System.out.println("action là   " + action);
		if (action.equals("save") && !error) {
			String ma = util.antiSQLInspection(request.getParameter("Ma"));
			if (ma != null) nspBean.setMa(ma);
			nhanhang = util.antiSQLInspection(request.getParameter("nhanhang"));
			if (nhanhang != null) nspBean.setNhanhang(nhanhang);
			String tenc = util.antiSQLInspection(request.getParameter("ten"));
			if (tenc != null) nspBean.setTen(tenc);
			chungloai = util.antiSQLInspection(request.getParameter("chungloai"));
			if (chungloai != null) nspBean.setChungloai(chungloai);
		 
			String file = util.antiSQLInspection(request.getParameter("filename"));
			if (file != null) nspBean.setDuongdan(file);
			String msg=nspBean.saveNsp();
			if (msg.trim().length()>0) 
			{
				nspBean.setMsg(msg);
				session.setAttribute("nspBean", nspBean);
				String nextJSP = request.getContextPath() + "/pages/Center/catalogNew.jsp";
				response.sendRedirect(nextJSP);
			} 
			else 
			{
				IcatalogList obj = new catalogList();
				obj.init("");
				session.setAttribute("obj", obj);
				nspBean.setMa(ma);
				nspBean.setNhanhang(nhanhang);
				nspBean.setTen(tenc);
				nspBean.setChungloai(chungloai);
				nspBean.setGhichu(ghichu);
				nspBean.setDuongdan(file);
				session.setAttribute("userId", userId);
				response.sendRedirect(request.getContextPath() + "/pages/Center/catalog.jsp");
			}
		}
	}

	private void getNspBeanList(String parent, List<INhomsanpham> nsplist) {
		dbutils db = new dbutils();
		String query = "select a.nsp_parent_fk as parent,a.loainhom, a.pk_seq, a.ten, a.diengiai, a.loaithanhvien, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua from nhomsanpham a, nhanvien b, nhanvien c where a.nguoitao = b.PK_SEQ and a.nguoisua = c.PK_SEQ and a.type='0' and a.nsp_parent_fk = '"
				+ parent + "' order by pk_seq";
		ResultSet rs = db.get(query);
		try {
			String[] param = new String[11];
			INhomsanpham nspBean;
			while (rs.next()) {
				param[0] = rs.getString("pk_seq");
				param[1] = rs.getString("ten");
				param[2] = rs.getString("diengiai");
				param[3] = rs.getString("loaithanhvien");
				param[4] = rs.getString("trangthai");
				param[5] = rs.getString("ngaytao");
				param[6] = rs.getString("ngaysua");
				param[7] = rs.getString("nguoitao");
				param[8] = rs.getString("nguoisua");
				param[9] = rs.getString("parent");
				param[10] = rs.getString("loainhom");
				nspBean = new Nhomsanpham(param, false);
				nsplist.add(nspBean);
				getNspBeanList(param[0], nsplist);
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
		}
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}