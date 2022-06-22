package geso.dms.center.servlets.thongtinsanpham;

import geso.dms.center.beans.nhapkho.IErpNhapkho;
import geso.dms.center.beans.nhapkho.IErpNhapkhoList;
import geso.dms.center.beans.nhapkho.imp.ErpNhapkho;
import geso.dms.center.beans.nhapkho.imp.ErpNhapkhoList;
import geso.dms.center.beans.thongtinsanpham.IThongtinsanpham;
import geso.dms.center.beans.thongtinsanpham.IThongtinsanphamList;
import geso.dms.center.beans.thongtinsanpham.imp.Thongtinsanpham;
import geso.dms.center.beans.thongtinsanpham.imp.ThongtinsanphamList;
import geso.dms.center.beans.tieuchithuong.IDuyetsuungho;
import geso.dms.center.beans.tieuchithuong.imp.Duyetsuungho;
import geso.dms.center.beans.upload.IUpload;
import geso.dms.center.beans.upload.imp.Upload;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.khachhang.imp.Khachhang;
import geso.dms.distributor.db.sql.dbutils;
import jxl.Sheet;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;

import com.aspose.cells.BorderType;
import com.aspose.cells.Color;
import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.oreilly.servlet.MultipartRequest;



public class ThongtinsanphamSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "c:\\upload\\excel\\";
	PrintWriter out;

	private int items = 50;
	private int splittings = 10;

	public ThongtinsanphamSvl() {
		super();
	}   

	private void settingPage(IThongtinsanphamList obj) {
		Utility util = new Utility();
		if(getServletContext().getInitParameter("items") != null){
			String i = getServletContext().getInitParameter("items").trim();
			if(util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if(getServletContext().getInitParameter("splittings") != null){
			String i = getServletContext().getInitParameter("splittings").trim();
			if(util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();	    

		
		  if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		  { 
			  session.setAttribute("flag",null);
			  response.sendRedirect(request.getContextPath() + "/redirect.jsp"); 
			  return; 
		  } 
		  else 
		  { session.setAttribute("flag",null); }
		 
		
		session.setAttribute("flag",null);
		Utility util = new Utility();
		out = response.getWriter();


		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length()==0)
			userId = request.getParameter("userId");

		String action = util.getAction(querystring);
		out.println(action);

		String spId = util.getId(querystring);


		String isBack =  util.antiSQLInspection(request.getParameter("isBack"));
		IThongtinsanphamList obj;
		if(isBack != null)
		{
			obj =  (IThongtinsanphamList)session.getAttribute("thongtinsanphamListAttri");
			obj.setQuery(getSearchQuery(null, obj));
			obj.setUserId(userId);
			obj.init();
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);  		
			response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");	    
		}
		else
		{
			obj = new ThongtinsanphamList();
			if (action.equals("delete")){	   		  	    	
				Delete(obj,spId);
				out.print(spId);
			}
			obj.setUserId(userId);
			settingPage(obj);
			obj.init();
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");
		}

	}  	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility util = (geso.dms.center.util.Utility) session.getAttribute("util");
		OutputStream out = response.getOutputStream();
		//Utility util = new Utility();
		String contentType = request.getContentType();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		  
		if(!util.check(userId, userTen, sum)){
			System.out.println("checksum error !!");
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}	
		   
		else
			{
			
			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}
			IThongtinsanphamList obj;
			String search = "";
		    String nextJSP = "";
		    obj = new ThongtinsanphamList();
			settingPage(obj);
			
			
			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			{
				// ---- CHỖ NÀY LẤY DỮ LIỆU TỪ JSP NÈ -----//
				System.out.println("vao upload nhap kho ");
				MultipartRequest multi = new MultipartRequest(request, UPLOAD_DIRECTORY, 20000000, "UTF-8");
				
				  if(!csdr.__validate_post_mul(multi)) {
				  System.out.println("contentType loi : "+ contentType);
				  response.sendRedirect(request.getContextPath() + "/redirect.jsp"); return; }
				 
				Enumeration files = multi.getFileNames();
				String filename = "";
				System.out.println("__userId" + userId);
				while (files.hasMoreElements())
				{
					String name = (String) files.nextElement();
					filename = multi.getFilesystemName(name);
					System.out.println("File  " + UPLOAD_DIRECTORY + filename);
				}
	
				if (filename == null)
				{
					obj.setMsg("Vui lòng chọn file ");
				} 
	
				if (filename != null && filename.length() > 0)
				{
					readExcel(UPLOAD_DIRECTORY + filename, obj, response);
				}
				
				/*obj.setUserId(userId);
				obj.init();
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");*/
			} 
			
			if (action.equals("new"))
			{
				// Empty Bean for distributor
				IThongtinsanpham spBean = (IThongtinsanpham) new Thongtinsanpham();
				spBean.setUserId(userId);
				spBean.CreateRS();
				// Save Data into session
				session.setAttribute("userId", userId);
				session.setAttribute("spBean", spBean);
	
				nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamNew.jsp";
				response.sendRedirect(nextJSP);
	
			}
			
			if (action.equals("search"))
			{
				obj.setQuery(getSearchQuery(request, obj));
				obj.setUserId(userId);
				obj.init();
	
				session.setAttribute("obj", obj);
	
				session.setAttribute("userId", userId);
	
				response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");	    	
	
			}
			else 
				if(action.equals("view") || action.equals("next") || action.equals("prev")){
	
					search = getSearchQuery(request, obj);
	
					obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
					obj.setUserId(userId);
					obj.setQuery(search);
					obj.init();
					obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
					session.setAttribute("obj", obj);
					response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");
				}
	
			if (action.equals("excel"))
			{
				obj = new ThongtinsanphamList();
	
				obj.setQuery(getSearchQuery(request, obj));
	
				try
				{
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=DanhSachSanPham.xls");
	
					Workbook workbook = new Workbook();
	
					CreateStaticHeader(workbook, "Nguyen Duy Hai");
					CreateStaticData(workbook, getQueryExcel(request, obj));
	
					//Saving the Excel file
					workbook.save(out);
					return;
				}
				catch (Exception ex)
				{
					obj.setMsg("Khong the tao pivot.");
				}
	
				obj.setUserId(userId);
				obj.init();
	
				session.setAttribute("obj", obj);
	
				session.setAttribute("userId", userId);
	
				response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");	    		
			}
		}
	}   

	private String getSearchQuery(HttpServletRequest request, IThongtinsanphamList obj)
	{
		if(request != null)
		{
			Utility util=new Utility();
			String masp =  util.antiSQLInspection(request.getParameter("masp"));
			if (masp == null)
				masp = "";
			obj.setMasp(masp);

			String tensp = util.antiSQLInspection(request.getParameter("tensp"));
			if (tensp == null)
				tensp = "";
			obj.setTensp(tensp);

			String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
			if (dvkdId == null)
				dvkdId = "";    	
			obj.setDvkdId(dvkdId);
			

			String nhId = util.antiSQLInspection(request.getParameter("nhId"));
			if (nhId == null)
				nhId = "";    	
			obj.setNhId(nhId);

			String clId = util.antiSQLInspection(request.getParameter("clId"));
			if (clId == null)
				clId = "";    	
			obj.setClId(clId);

			String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));   	
			if (trangthai == null)
				trangthai = "";    	

			if (trangthai.equals("2"))
				trangthai = "";

			obj.setTrangthai(trangthai);
		}
		String   query ="select ROW_NUMBER() OVER(ORDER BY a.ma DESC) AS 'stt', a.pk_seq, a.ma, a.ten, a.trongluong, a.thetich, b.donvikinhdoanh as dvkd,b.pk_seq as dvkdId, c.ten as chungloai, e.pk_seq as nhId, d.donvi, e.ten as nhanhang, d.pk_seq as clId, a.trangthai --, isnull(f.giabanlechuan,'0') as giablc \n";
		query = query + " from sanpham a left join donvikinhdoanh b on a.dvkd_fk = b.pk_seq left join chungloai c on a.chungloai_fk = c.pk_seq left join donvidoluong d on a.dvdl_fk = d.pk_seq left join nhanhang e on a.nhanhang_fk = e.pk_seq ";
		//	query = query  + " left join banggiablc_sanpham f on a.pk_seq = f.sanpham_fk left join banggiabanlechuan g on f.bgblc_fk = g.pk_seq  where 1=1 ";
		query = query +" where 1=1";   
		if (obj.getMasp().length()>0){
			query = query + " and upper(a.ma) like upper('%" + obj.getMasp() + "%')";	
		}

		if (obj.getTensp().length()>0){
			geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
			query = query + " and upper(dbo.ftBoDau(a.ten)) like upper(N'%"+ util.replaceAEIOU(obj.getTensp().trim()).trim()+"%')";	
		}

		if (obj.getDvkdId().length()>0){
			query = query + " and b.pk_seq = '" + obj.getDvkdId() + "'";	
		}

		if (obj.getNhId().length()>0){
			query = query + " and e.pk_seq = '" + obj.getNhId() + "'";   		
		}

		if (obj.getClId().length()>0){
			query = query + " and c.pk_seq = '" + obj.getClId() + "'";    		
		}

		if(obj.getTrangthai().length() > 0){
			query = query + " and a.trangthai = '" + obj.getTrangthai() + "'";   		
		}


		return query;

	}	

	private String getQueryExcel(HttpServletRequest request, IThongtinsanphamList obj)
	{
		Utility util=new Utility();
		String masp = util.antiSQLInspection(request.getParameter("masp"));
		if (masp == null)
			masp = "";
		obj.setMasp(masp);

		String tensp = util.antiSQLInspection(request.getParameter("tensp"));
		if (tensp == null)
			tensp = "";
		obj.setTensp(tensp);

		String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
		if (dvkdId == null)
			dvkdId = "";    	
		obj.setDvkdId(dvkdId);

		String nhId = util.antiSQLInspection(request.getParameter("nhId"));
		if (nhId == null)
			nhId = "";    	
		obj.setNhId(nhId);

		String clId = util.antiSQLInspection(request.getParameter("clId"));
		if (clId == null)
			clId = "";    	
		obj.setClId(clId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));   	
		if (trangthai == null)
			trangthai = "";    	

		if (trangthai.equals("2"))
			trangthai = "";

		obj.setTrangthai(trangthai);

		String   query =
			"select distinct  b.pk_seq as dvkdId,b.donvikinhdoanh as dvkd,e.ten as nhanhang, d.pk_seq as clId,\n  "+ 
			"	n.TEN as NganhHang,p.TEN as PackSize,qc.SOLUONG1 as QuyCach,qvc.SOLUONG2 as GoiVanChuyen\n  "+ 
			"	,a.pk_seq, a.ma,a.MACHUAN ,a.ten, a.tenchuan , a.trongluong,\n  "+ 
			"	a.thetich, c.ten as chungloai, e.pk_seq as nhId, d.donvi, a.trangthai \n  "+ 
			"	from sanpham a \n  "+ 
			"left join donvikinhdoanh b on a.dvkd_fk = b.pk_seq \n  "+ 
			"left join chungloai c on a.chungloai_fk = c.pk_seq \n  "+ 
			"left join donvidoluong d on a.dvdl_fk = d.pk_seq\n  "+ 
			"left join nhanhang e on a.nhanhang_fk = e.pk_seq  \n  "+ 
			"left join NGANHHANG n on n.PK_SEQ=a.NGANHHANG_FK\n  "+ 
			"left join Packsize p  on p.PK_SEQ=a.PACKSIZE_FK\n  "+ 
			"left join QUYCACH qc on qc.SANPHAM_FK=a.PK_SEQ and qc.DVDL2_FK=100018\n  "+ 
			"left join QUYCACH qvc on qvc.SANPHAM_FK=a.PK_SEQ and qvc.DVDL2_FK=100052 " +
			"where 1=1 ";
		if (masp.length()>0){
			query = query + " and upper(a.ma) like upper('%" + masp + "%')";	
		}

		if (tensp.length()>0){
						query = query + " and upper(dbo.ftBoDau(a.ten)) like upper(N'%"+ util.replaceAEIOU(tensp.trim()).trim()+"%')";	
		}

		if (dvkdId.length()>0){
			query = query + " and b.pk_seq = '" + dvkdId + "'";	
		}

		if (nhId.length()>0){
			query = query + " and e.pk_seq = '" + nhId + "'";   		
		}

		if (clId.length()>0){
			query = query + " and c.pk_seq = '" + clId + "'";    		
		}

		if(trangthai.length() > 0){
			query = query + " and a.trangthai = '" + trangthai + "'";   		
		}
		System.out.println("Excel "+query);
		return query;

	}	


	boolean kiemtra(String sql,String id)
	{
		dbutils db =new dbutils();
		String query = "select count(*) as num from " + sql + " where sanpham_fk ='"+ id +"'";
		System.out.println("Delete query: "+query);
		ResultSet rs = db.get(query);
		try 
		{	
			//kiem tra ben san pham
			while(rs.next())
			{ 
				if(rs.getString("num").equals("0"))
					return false;
			}
		} 
		catch (SQLException e) {}
		query =  "select count(*) as num from ERP_KHOTT_SANPHAM where sanpham_fk ='"+ id +"' and soluong > 0 ";
		ResultSet rs2 = db.get(query);
		try 
		{
			while(rs2.next())
			{ 
				if(rs2.getString("num").equals("0"))
					return false;
			}
		} 
		catch (SQLException e) {}

		return true;

	}
	public static void main(String[] args) 
	{
		dbutils db = new dbutils();
		try
		{
			String 
		
			query =  "\n select PK_SEQ " + 
					 "\n from sanpham kh " + 
					 "\n " ;
			ResultSet rs= db.get(query);
			while (rs.next())
			{
				IThongtinsanphamList obj = new ThongtinsanphamList();
				String khId = rs.getString("PK_SEQ");
				Delete(obj,khId);
				System.out.println("Xoa("+khId+"):"+ obj.getMsg());
				obj.DBClose();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		db.shutDown();
	}
	public static void Delete(IThongtinsanphamList obj,String id){
		dbutils db = null; 

		try {
			db = new dbutils();			
			db.getConnection().setAutoCommit(false);

			if (kiemtra("TONKHOTHANG", id, db) || kiemtra("ERP_KHOTT_SANPHAM", id, db) || kiemtra("nhaphang_sp", id, db)
					|| kiemtra("donhangtrave_sanpham", id, db) || kiemtra("dieukienkm_sanpham", id, db) 
					|| kiemtra("phieuxuatkho_spkm", id, db) || kiemtra("HOADON_SP", id, db) 
					|| kiemtra("trakhuyenmai_sanpham", id, db) || kiemtra("donhangthuhoi_sanpham", id, db) 
					|| kiemtra("phieuxuatkho_sanpham", id, db) || kiemtra("denghidathang_sp", id, db) 
					|| kiemtra("phieuthuhoi_sanpham", id, db) || kiemtra("donhang_sanpham", id, db) 
					|| kiemtra("dieuchinhtonkho_sp", id, db) || kiemtra("dontrahang_sp", id, db) 
					|| kiemtra("HOADON_CTKM_TRAKM", id, db) ||kiemtra("phieuxuatkho_spkm", id, db) 
					|| kiemtra("DONHANG_CTKM_TRAKM_CHITIET", id, db))
			{
				obj.setMsg("Sản phẩm đã phát sinh dữ liệu, không thể xoá.");
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			}
			else
			{	
				String sql = "delete from nhomsanpham_sanpham where sp_fk = '" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong nhóm sản phẩm.");
					return;
				}

				sql = "delete from quycach where sanpham_fk = '" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại quy cách.");
					return;
				}

				sql = "DELETE FROM BANGGIABLC_SANPHAM WHERE SANPHAM_FK='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong bảng giá bán lẻ chuẩn.");
					return;
				}

				sql = "delete from bgbanlenpp_sanpham where sanpham_fk='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại torng bảng giá bán lẻ nhà phân phối.");
					return;
				}

				sql = "delete from bgmuanpp_sanpham where sanpham_fk='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong bảng giá mua nhà phân phối.");
					return;
				}

				sql = "delete from banggiast_sanpham where sanpham_fk='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong bảng giá siêu thị.");
					return;
				}

				sql = "delete from nhapp_kho where sanpham_fk='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong kho.");
					return;
				}

				sql = "delete from nhanvien_sanpham where sanpham_fk = '" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại mã nhân viên.");
					return;
				}

				sql = "delete from NHAPP_KHO_CHITIET where sanpham_fk='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm có trong nhà phân phối.");
					return;
				}

				sql = "delete from NhomHang_SanPham where sanpham_fk='" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong nhóm hàng.");
					return;
				}

				sql = "delete from TONKHOTHANG where sanpham_fk='" + id + "' and soluong = 0 and booked  = 0 and available = 0";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong bảng tồn kho tháng.");
					return;
				}

				sql = "delete from TONKHOTHANG_chitiet where sanpham_fk='" + id + "' and soluong = 0 and booked  = 0 and available = 0";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xoá sản phẩm đã tồn tại trong bảng tồn kho tháng.");
					return;
				}

				sql = "delete from sanpham where pk_seq = '" + id + "'";
				if (!db.update(sql))
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					obj.setMsg("Không thể xóa, Sản phẩm đã tồn tại trong Đơn hàng.");
					return;
				}

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}		
		}
		catch (Exception e) {
			e.printStackTrace();
			try {
				db.getConnection().rollback();
				obj.setMsg("Exception khi xoá SP: " + e.getMessage());
			}
			catch (Exception ex) {
				//ex.printStackTrace();
			}
		}
		finally {
			if (db != null) {
				db.shutDown();
			}
		}
	}

	private void CreateStaticHeader(Workbook workbook, String UserName) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();
		Style style;

		Cell cell = cells.getCell("A1"); 
		cell.setValue("DANH SÁCH SẢN PHẨM");	    
		style = cell.getStyle();
		Font font2 = new Font();	
		font2.setName("Calibri");
		font2.setColor(Color.NAVY);
		font2.setSize(18);
		font2.setBold(true);
		style.setFont(font2);
		style.setHAlignment(TextAlignmentType.CENTER);					
		cell.setStyle(style);

		font2 = new Font();	
		font2.setName("Calibri");
		font2.setBold(true);
		font2.setSize(11);

		cell = cells.getCell("A3");
		cell.setValue("Ngày tạo : " + this.getDateTime());
		style = cell.getStyle();
		style.setFont(font2);
		cell.setStyle(style);

		//tieu de
		cell = cells.getCell("A8");cell.setValue("Đơn vị kinh doanh");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);    
		cell = cells.getCell("B8");cell.setValue("Ngành hàng");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("C8");cell.setValue("Packsize");  					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("D8");cell.setValue("Nhãn hàng"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("E8");cell.setValue("Chủng loại");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("F8");cell.setValue("Mã sản phẩm");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("G8");cell.setValue("Mã chuẩn"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("H8");cell.setValue("Tên sản phẩm");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("I8");cell.setValue("Tên chuẩn");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("J8");cell.setValue("Đơn vị đo lường"); 			style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		//Them trong luong +The tich
		cell = cells.getCell("K8");cell.setValue("Trọng lượng");	      		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("L8");cell.setValue("Thể tích"); 	 				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("M8");cell.setValue("Quy cách Thùng");  			style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("N8");cell.setValue("Quy cách Gói vận chuyển");	style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		cell = cells.getCell("O8");cell.setValue("Tình trạng");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell);
		worksheet.setName("DANH SÁCH SẢN PHẨM");
	}

	private void CreateStaticData(Workbook workbook, String query) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		//System.out.println("Get san pham :"+query);
		NumberFormat formatter = new DecimalFormat("#,###,##0.0"); 
		NumberFormat formatTheTich = new DecimalFormat("#,###,##0.00000"); 
		NumberFormat formatKhoiLuong = new DecimalFormat("#,###,##0.000"); 
		int i = 9;
		if(rs != null)
		{
			try 
			{
				cells.setColumnWidth(0, 35.0f);
				cells.setColumnWidth(1, 15.0f);
				cells.setColumnWidth(2, 20.0f);
				cells.setColumnWidth(3, 15.0f);
				cells.setColumnWidth(4, 15.0f);
				cells.setColumnWidth(5, 25.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 45.0f);
				cells.setColumnWidth(8, 40.0f);
				cells.setColumnWidth(9, 15.0f);
				cells.setColumnWidth(10, 15.0f);
				cells.setColumnWidth(11, 15.0f);
				cells.setColumnWidth(12, 15.0f);
				cells.setColumnWidth(13, 25.0f);
				cells.setColumnWidth(14, 15.0f);
				for(int j = 0; j < 11; j++)
				{
					if(j==0)
						cells.setRowHeight(j, 23.0f);
					else
						cells.setRowHeight(j, 13.0f);
				}

				Cell cell = null;

				Style style;
				Font font2 = new Font();
				font2.setName("Calibri");				
				font2.setSize(11);

				while(rs.next())
				{

					String nganhang = "";
					if(rs.getString("nganhhang")!= null)
						nganhang = rs.getString("nganhhang");

					String packsize = "";
					if(rs.getString("packsize")!= null)
						packsize = rs.getString("packsize");

					String dvkd = "";
					if(rs.getString("dvkd")!= null)
						dvkd = rs.getString("dvkd");

					String nhanhang = "";
					if(rs.getString("nhanhang") != null)
						nhanhang = rs.getString("nhanhang");

					String chungloai = "";
					if(rs.getString("chungloai") != null)
						chungloai = rs.getString("chungloai");

					String masp = "";
					if(rs.getString("ma") != null)
						masp = rs.getString("ma");

					String machuan = "";
					if(rs.getString("machuan") != null)
						machuan = rs.getString("machuan");


					String tensp = "";
					if(rs.getString("ten") != null)
						tensp = rs.getString("ten");

					String tenchuan = "";
					if(rs.getString("tenchuan") != null)
						tenchuan = rs.getString("tenchuan");


					String dvdl = "";
					if(rs.getString("donvi") != null)
						dvdl = rs.getString("donvi");


					String quycach = "";
					if(rs.getString("quycach") != null)
						quycach = rs.getString("quycach");

					String goivanchuyen = "";
					if(rs.getString("goivanchuyen") != null)
						goivanchuyen = rs.getString("goivanchuyen");

					String trangthai="Hoạt động";
					if(rs.getString("trangthai").equals("0")){
						trangthai="Không hoạt động";
					}



					String trongluong="";
					if(rs.getString("trongluong")!=null)
						trongluong=formatKhoiLuong.format((rs.getDouble("trongluong")));

					String thetich="";
					if(rs.getString("thetich")!=null)
						thetich=formatTheTich.format((rs.getDouble("thetich")));

					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(dvkd); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(nganhang); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(packsize); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);

					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(nhanhang); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(chungloai); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(masp); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(machuan); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(tensp); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(tenchuan); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(dvdl); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);

					//Trong luong
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue(trongluong); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//The tich
					cell = cells.getCell("L" + Integer.toString(i));	cell.setValue(thetich); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);

					cell = cells.getCell("M" + Integer.toString(i));	cell.setValue(quycach); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);

					cell = cells.getCell("N" + Integer.toString(i));	cell.setValue(goivanchuyen);	style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);

					cell = cells.getCell("O" + Integer.toString(i));	cell.setValue(trangthai); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					i++;
				}
				rs.close();
			}
			catch (SQLException e){ e.printStackTrace(); }
		}
	}

	/*private void CreateStaticHeader(Workbook workbook, String UserName) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);

	    Cells cells = worksheet.getCells();

	    Cell cell = cells.getCell("A1"); 
	    cell.setValue("DANH SÁCH SẢN PHẨM");

	    cell = cells.getCell("A3");
	    cell.setValue("Ngày tạo : " + this.getDateTime());
	    //cell = cells.getCell("A4");
	    //cell.setValue("User:  " + UserName);

	    //tieu de
	    cell = cells.getCell("A8");cell.setValue("Đơn vị kinh doanh");
	    cell = cells.getCell("B8");cell.setValue("Ngành hàng");
	    cell = cells.getCell("C8");cell.setValue("Packsize");
	    cell = cells.getCell("D8");cell.setValue("Nhãn hàng");
	    cell = cells.getCell("E8");cell.setValue("Chủng loại");
	    cell = cells.getCell("F8");cell.setValue("Mã sản phẩm");
	    cell = cells.getCell("G8");cell.setValue("Mã chuẩn");
	    cell = cells.getCell("H8");cell.setValue("Tên sản phẩm");
	    cell = cells.getCell("I8");cell.setValue("Tên chuẩn");
	    cell = cells.getCell("J8");cell.setValue("Đơn vị đo lường");
	    cell = cells.getCell("K8");cell.setValue("Giá bán lẻ chuẩn");
	  //Them trong luong +The tich
	    cell = cells.getCell("L8");cell.setValue("Trọng lượng");	    
	    cell = cells.getCell("M8");cell.setValue("Thể tích");
	    cell = cells.getCell("N8");cell.setValue("Quy cách Thùng");
	    cell = cells.getCell("O8");cell.setValue("Quy cách Gói vận chuyển");
	    cell = cells.getCell("P8");cell.setValue("Tình trạng");
	    worksheet.setName("Danh sách  sản phẩm");
	}

	private void CreateStaticData(Workbook workbook, String query) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();

		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		System.out.println("Get san pham :"+query);
		NumberFormat formatter = new DecimalFormat("#,###,##0.0"); 
		NumberFormat formatTheTich = new DecimalFormat("#,###,##0.00000"); 
		NumberFormat formatKhoiLuong = new DecimalFormat("#,###,##0.000");  
		int i = 9;
		if(rs != null)
		{
			try 
			{
				cells.setColumnWidth(0, 25.0f);
				cells.setColumnWidth(1, 15.0f);
				cells.setColumnWidth(2, 15.0f);
				cells.setColumnWidth(3, 30.0f);
				cells.setColumnWidth(4, 45.0f);
				cells.setColumnWidth(5, 25.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);
				cells.setColumnWidth(9, 15.0f);
				cells.setColumnWidth(10, 15.0f);
				for(int j = 0; j < 11; j++)
					cells.setRowHeight(j, 13.0f);

				Cell cell = null;
				while(rs.next())
				{

					String nganhang = "";
					if(rs.getString("nganhhang")!= null)
						nganhang = rs.getString("nganhhang");

					String packsize = "";
					if(rs.getString("packsize")!= null)
						packsize = rs.getString("packsize");

					String dvkd = "";
					if(rs.getString("dvkd")!= null)
						dvkd = rs.getString("dvkd");

					String nhanhang = "";
					if(rs.getString("nhanhang") != null)
						nhanhang = rs.getString("nhanhang");

					String chungloai = "";
					if(rs.getString("chungloai") != null)
						chungloai = rs.getString("chungloai");

					String masp = "";
					if(rs.getString("ma") != null)
						masp = rs.getString("ma");

					String machuan = "";
					if(rs.getString("machuan") != null)
						machuan = rs.getString("machuan");


					String tensp = "";
					if(rs.getString("ten") != null)
						tensp = rs.getString("ten");

					String tenchuan = "";
					if(rs.getString("tenchuan") != null)
						tenchuan = rs.getString("tenchuan");


					String dvdl = "";
					if(rs.getString("donvi") != null)
						dvdl = rs.getString("donvi");

					String gblc = "";
					if(rs.getString("giablc") != null)
						gblc = formatter.format(rs.getDouble("giablc"));

					String quycach = "";
					if(rs.getString("quycach") != null)
						quycach = rs.getString("quycach");

					String goivanchuyen = "";
					if(rs.getString("goivanchuyen") != null)
						goivanchuyen = rs.getString("goivanchuyen");

					String trangthai="Hoạt động";
					if(rs.getString("trangthai").equals("0")){
						trangthai="Không hoạt động";
					}

					String trongluong="";
					if(rs.getString("trongluong")!=null)
						trongluong=formatKhoiLuong.format((rs.getDouble("trongluong")));

					String thetich="";
					if(rs.getString("thetich")!=null)
						thetich=formatTheTich.format((rs.getDouble("thetich")));

					cell = cells.getCell("A" + Integer.toString(i));cell.setValue(dvkd);
					cell = cells.getCell("B" + Integer.toString(i));cell.setValue(nganhang);
					cell = cells.getCell("C" + Integer.toString(i));cell.setValue(packsize);

					cell = cells.getCell("D" + Integer.toString(i));cell.setValue(nhanhang);
					cell = cells.getCell("E" + Integer.toString(i));cell.setValue(chungloai);
					cell = cells.getCell("F" + Integer.toString(i));cell.setValue(masp);
					cell = cells.getCell("G" + Integer.toString(i));cell.setValue(machuan);
					cell = cells.getCell("H" + Integer.toString(i));cell.setValue(tensp);
					cell = cells.getCell("I" + Integer.toString(i));cell.setValue(tenchuan);
					cell = cells.getCell("J" + Integer.toString(i));cell.setValue(dvdl);
					cell = cells.getCell("K" + Integer.toString(i));cell.setValue(gblc);
					//Trong luong
					cell = cells.getCell("L" + Integer.toString(i));cell.setValue(trongluong);
					//The tich
					cell = cells.getCell("M" + Integer.toString(i));cell.setValue(thetich);

					cell = cells.getCell("N" + Integer.toString(i));
					cell.setValue(quycach);

					cell = cells.getCell("O" + Integer.toString(i));
					cell.setValue(goivanchuyen);

					cell = cells.getCell("P" + Integer.toString(i));
					cell.setValue(trangthai);
					i++;
				}
				rs.close();
			}
			catch (SQLException e){ e.printStackTrace(); }
		}
	}*/
	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	private void setCellBorderStyle(Cell cell) {
		Style style = cell.getStyle();
		style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);
		cell.setStyle(style);
	}

	private static boolean kiemtra(String sql, String id, Idbutils db)
	{
		String query = "select count(*) as num from " + sql + " where sanpham_fk = '" + id + "'";
		System.out.println("Delete query: "+query);
		ResultSet rs = db.get(query);
		try 
		{	
			String check = "0";
			//kiem tra ben san pham
			while (rs.next())
			{ 
				check = rs.getString("num");
				if (!rs.getString("num").equals("0"))
					return false;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		query = "select count(*) as num from ERP_KHOTT_SANPHAM where sanpham_fk = '" + id + "' and soluong > 0 ";
		ResultSet rs2 = db.get(query);
		try 
		{
			while (rs2.next())
			{ 
				if (rs2.getString("num").equals("0"))
					return false;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}

		return true;			
	}
	 
	private void readExcel(String fileName, IThongtinsanphamList spBean, HttpServletResponse response) 
	{
		File file = new File(fileName);
		//jxl.Workbook w = null;
		dbutils db = new dbutils();
		try
		{
			boolean loiErrorRollback = false;
			String errror = "";
			int indexRow = 6;
			String query="";
			db.getConnection().setAutoCommit(false);
			
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=THONGTINUPLOAD.xls");
			WritableWorkbook wr = jxl.Workbook.createWorkbook(response.getOutputStream());
			jxl.Workbook workbook;
			WorkbookSettings ws = new WorkbookSettings();
			ws.setEncoding("ISO8859_1");
			workbook = jxl.Workbook.getWorkbook( file, ws );
			Sheet[] sheet1 = workbook.getSheets();
			
			// DELETE FROM TEMP TABLE
			query = "delete from temp_sp";
			db.update(query);
			//createTempTable(db);
			// ----------------------------
			
			for (int ii = 0; ii < sheet1.length; ii++)
			{
				String valueSP = "";
				WritableSheet sheetwrite = wr.createSheet(sheet1[ii].getName(), ii);
				sheetwrite.addCell(new Label(0, 0, "THÔNG TIN UPLOAD SẢN PHẨM"));
				
				jxl.Sheet sheet = sheet1[ii]; 
				for (int i = indexRow; i < sheet.getRows(); i++)
				{
					jxl.Cell[] cells = sheet.getRow(i);
					String inerror = "";
					if(cells !=null)
					{
						if(cells.length >= 1 && cells[1] != null)
						{
							String donvikinhdoanh = getValue(sheet, 0, i);
							String nganhhang = getValue(sheet, 1, i);
							String nhanhang = getValue(sheet, 2, i);
							String chungloai = getValue(sheet, 3, i);
							String masp = getValue(sheet, 4, i);
							String tensp = getValue(sheet, 5, i);
							String donvidoluong = getValue(sheet, 6, i);
							
							System.out.println(" dòng "+ i +" : "+ donvikinhdoanh +" - "+ nganhhang);

							float trongluong = 0, thetich = 0, thuesuat = 0, soluongdonvichuan = 0, soluongdonviquydoi = 0;
							
							try 
							{
								if(getValue(sheet,7,i).length() > 0)  
								{
									trongluong = Float.parseFloat(getValue(sheet,7,i).replace("'",""));
									if (trongluong < 0)
									{
										inerror = inerror + " Thông tin trọng lượng sản phẩm trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại.";
									}
								}
								
							} catch (Exception e) {
								inerror = inerror + "Thông tin trọng lượng chỉ nhập số.";
							}
							
							try
							{
								if(getValue(sheet,8,i).length() > 0)  
								{
									thetich = Float.parseFloat(getValue(sheet,8,i).replace("'",""));
									if (thetich < 0)
									{
										inerror = inerror + " Thông tin thể tích sản phẩm trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại.";
									}
								}
								
							} catch (Exception e) {
								inerror = inerror + "Thông tin thể tích chỉ nhập số.";
							}
							
							try
							{
								if(getValue(sheet,9,i).length() > 0)  
								{
									thuesuat = Float.parseFloat(getValue(sheet,9,i).replace("'",""));
									if (thuesuat < 0)
									{
										inerror = inerror + " Thông tin thuế suất trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại.";
									}
								}
								
							} catch (Exception e) {
								inerror = inerror + "Thông tin thuế suất chỉ nhập số.";
							}
							
							try
							{
								if(getValue(sheet,10,i).length() > 0)  
								{
									soluongdonvichuan = Float.parseFloat(getValue(sheet,10,i).replace("'",""));
									if (soluongdonvichuan < 0)
									{
										inerror = inerror + " Thông tin đơn vị chuẩn trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại.";
									}
								}
								
							} catch (Exception e) {
								inerror = inerror + "Thông tin đơn vị chuẩn chỉ nhập số.";
							}
							
							try
							{
								if(getValue(sheet,11,i).length() > 0)  
								{
									soluongdonviquydoi = Float.parseFloat(getValue(sheet,11,i).replace("'",""));
									if (soluongdonviquydoi < 0)
									{
										inerror = inerror + " Thông tin đơn vị quy đổi trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại.";
									}
								}
								
							} catch (Exception e) {
								inerror = inerror + "Thông tin đơn vị quy đổi chỉ nhập số.";
							}

							String donvichuan = getValue(sheet, 12, i);
							String donviquydoi = getValue(sheet, 13, i);
							
							
							if (!inerror.equals(""))
							{
								loiErrorRollback = true;
								sheetwrite.addCell(new Label(0, indexRow, inerror));
							} 
							errror += inerror;
							if(errror.length() <=0 && !loiErrorRollback)	
							{
								valueSP ="\n select N'"+ donvikinhdoanh +"' dvkd, N'"+ nganhhang +"' nganhhang, N'"+ nhanhang +"' nhanhang, N'"+ chungloai +"' chungloai, "
										+ " N'"+ masp +"' masp, N'"+ tensp +"' tensp, N'"+ donvidoluong +"' donvidoluong, '"+ trongluong +"' trongluong, '"+ thetich +"' thetich, '"+ thuesuat +"' thuesuat, "
										+ " N'"+ donvichuan +"' donvichuan, '"+ soluongdonvichuan +"' as soluongdonvichuan, N'"+ donviquydoi +"' as donviquydoi, '"+ soluongdonviquydoi +"' as soluongdonviquydoi ";

								query = "insert TEMP_SP select * from ("+ valueSP +") a";
								db.update(query);
							}
						} 
					}
					
					inerror = "";
					indexRow++;
				}
				
				int sodongInsert = 0;
				if (loiErrorRollback)
				{
					if (!db.getConnection().getAutoCommit())
					{
						sheetwrite.addCell(new Label(1, 2 , "Thông báo: Lỗi Error "+errror));
						db.getConnection().rollback();
						db.getConnection().setAutoCommit(true);
					}
				}
				else if (valueSP.trim().length() >0)
				{
					boolean pass = true;
					String sql = "";
					ResultSet checkSql = null;
					String khLoi = "";
					if (pass)
					{
						sql = 
							" INSERT INTO SANPHAM(MA, TEN, DVKD_FK, DVDL_FK, NGANHHANG_FK, NHANHANG_FK, CHUNGLOAI_FK, TRONGLUONG, THETICH, PT_VAT, "+
							" TYPE, TONTOITHIEU, ISKM,  KHOERP_FK, TRANGTHAI, NCC_FK, NGAYTAO, NGAYSUA, NGUOITAO, NGUOISUA ) "+
							" SELECT DISTINCT _TEMP.MASANPHAM, _TEMP.TENSANPHAM, DVKD.PK_SEQ AS DVKD_FK, DVDL.PK_SEQ AS DVDL_FK, _NH.PK_SEQ AS NGANHHANG_FK, NH.PK_SEQ AS NHANHANG_FK, CL.PK_SEQ AS CHUNGLOAI_FK, _TEMP.TRONGLUONG, _TEMP.THETICH, _TEMP.THUESUAT PT_VAT, "+
							" '0' TYPE, '0' TONTOITHIEU, '0' ISKM,  '100001' KHOERP_FK, '0' TRANGTHAI, '100001' NCC_FK, CONVERT(VARCHAR(10), GETDATE(), 120) NGAYTAO, CONVERT(VARCHAR(10), GETDATE(), 120) NGAYSUA, '100002' NGUOITAO, '100002' NGUOISUA "+
							" FROM TEMP_SP _TEMP "+
							" LEFT JOIN DONVIKINHDOANH DVKD ON DVKD.DONVIKINHDOANH = _TEMP.DONVIKINHDOANH "+
							" LEFT JOIN NGANHHANG _NH ON _NH.TEN = _TEMP.NGANHHANG "+
							" LEFT JOIN NHANHANG NH ON NH.MA = _TEMP.NHANHANG "+
							" LEFT JOIN CHUNGLOAI CL ON CL.MA = _TEMP.CHUNGLOAI "+
							" LEFT JOIN DONVIDOLUONG DVDL ON DVDL.DONVI = _TEMP.DONVIDOLUONG "
							+ "WHERE NOT EXISTS ( SELECT 1 FROM SANPHAM SP WHERE SP.MA = _TEMP.MASANPHAM )";
								
						System.out.println("Insert SQL: "+sql);
						sodongInsert = db.updateReturnInt(sql);	
						System.out.println("sodongInsert : "+ sodongInsert);
						if (sodongInsert < 0)
						{
							pass = false;
							sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi tạo mới SP : "));
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
						}											
					}
					
					if (pass)
					{
						sql = " UPDATE SP SET SP.TEN = _TEMP.TENSANPHAM, SP.DVKD_FK = DVKD.PK_SEQ, SP.DVDL_FK = DVDL.PK_SEQ, SP.NGANHHANG_FK = _NH.PK_SEQ, "
							+ " SP.NHANHANG_FK = NH.PK_SEQ, SP.CHUNGLOAI_FK = CL.PK_SEQ, SP.TRONGLUONG = _TEMP.TRONGLUONG, SP.THETICH = _TEMP.THETICH, SP.PT_VAT = _TEMP.THUESUAT "
							+ " FROM SANPHAM SP "
							+ " INNER JOIN TEMP_SP _TEMP ON _TEMP.MASANPHAM = SP.MA "
							+ " LEFT JOIN DONVIKINHDOANH DVKD ON DVKD.DONVIKINHDOANH = _TEMP.DONVIKINHDOANH "+
							" LEFT JOIN NGANHHANG _NH ON _NH.TEN = _TEMP.NGANHHANG "+
							" LEFT JOIN NHANHANG NH ON NH.MA = _TEMP.NHANHANG "+
							" LEFT JOIN CHUNGLOAI CL ON CL.MA = _TEMP.CHUNGLOAI "+
							" LEFT JOIN DONVIDOLUONG DVDL ON DVDL.DONVI = _TEMP.DONVIDOLUONG ";
								
						System.out.println("Insert SQL: "+sql);
						sodongInsert = db.updateReturnInt(sql);	
						System.out.println("sodongInsert : "+ sodongInsert);
						if (sodongInsert < 0)
						{
							pass = false;
							sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi cập nhật SP : "));
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
						}											
					}
					
					if(pass)
					{
						query = 
							"UPDATE SP SET TIMKIEM = " + 
							"\n upper(dbo.ftBoDau(sp.MA)) + '-' +upper(dbo.ftBoDau(isnull(SP.BARCODE,''))) " +
							"\n +'-'+ upper(dbo.ftBoDau(isnull(SP.ten,''))) + '-' +upper(dbo.ftBoDau(isnull(DVDL.DONVI,''))) " +
							"\n FROM SANPHAM SP "
							+ " INNER JOIN TEMP_SP _TEMP ON _TEMP.MASANPHAM = SP.MA "
							+ " INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ = SP.DVDL_FK ";
						System.out.println("query : "+ query);
						db.update(query);
					}
					
					if(pass)
					{
						sql =
						" select count(*) num from temp_sp _temp "+
						" INNER JOIN DONVIDOLUONG DV1 ON dbo.ftBoDau(DV1.DONVI) = _TEMP.DONVICHUAN "+
						" INNER JOIN DONVIDOLUONG DV2 ON dbo.ftBoDau(DV2.DONVI) = _TEMP.DONVIQUYDOI "+
						" INNER JOIN SANPHAM SP ON SP.MA = _TEMP.MASANPHAM  "+
						" where exists "+
						" ( select 1 from "+
						"	( "+
						"		select b.sanpham_fk,  b.dvdl_fk  from  ERP_DONDATHANGNPP_SANPHAM B "+ 
						" 		WHERE B.SOLUONG != 0 and b.dvdl_fk = dv2.pk_seq and b.sanpham_fk = sp.pk_seq "+ 
						"		union "+ 
						"		SELECT  b.sanpham_fk,  b.dvdl_fk FROM ERP_DONDATHANG_SANPHAM B "+ 
						" 		WHERE B.SOLUONG != 0 and b.dvdl_fk = dv2.pk_seq and b.sanpham_fk = sp.pk_seq "+ 
						" 		UNION "+ 
						" 		SELECT  b.sanpham_fk,  b.dvdl_fk FROM ERP_CHUYENKHONPP_SANPHAM B "+ 
						" 		WHERE B.SOLUONGCHUYEN != 0 and b.dvdl_fk = dv2.pk_seq and b.sanpham_fk = sp.pk_seq "+ 
						" 		UNION "+ 
						" 		SELECT  b.sanpham_fk,  b.dvdl_fk FROM ERP_CHUYENKENH_SANPHAM B "+ 
						" 		WHERE B.SOLUONGCHUYEN != 0 and b.dvdl_fk = dv2.pk_seq and b.sanpham_fk = sp.pk_seq  "+
						"	) as ct where ct.sanpham_fk = sp.pk_seq  and ct.dvdl_fk = dv2.PK_SEQ "+
						") ";
						ResultSet rsCheck = db.get(sql);
						rsCheck.next();
						int num = 0;
						num = rsCheck.getInt("num");
						if(num > 0)
						{
							pass = false;
							sheetwrite.addCell(new Label(1, indexRow +1 , "Sản phẩm đã phát sinh dữ liệu nên không thể thay đổi quy cách."));
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
						}
					}

					// INSERT QUY CACH
					if (pass) {
						query = 
							" INSERT INTO QUYCACH(SANPHAM_FK, DVDL1_FK, SOLUONG1, DVDL2_FK, SOLUONG2) "+
							" SELECT SP.PK_SEQ AS SANPHAM_FK, DV1.PK_SEQ AS DVDL1_FK, _TEMP.SOLUONGDONVICHUAN SOLUONG1, DV2.PK_SEQ AS DVDL2_FK, _TEMP.SOLUONGDONVIQUYDOI SOLUONG2 "+
							" FROM TEMP_SP _TEMP "+
							" INNER JOIN DONVIDOLUONG DV1 ON dbo.ftBoDau(DV1.DONVI) = dbo.ftBoDau(_TEMP.DONVICHUAN) "+
							" INNER JOIN DONVIDOLUONG DV2 ON dbo.ftBoDau(DV2.DONVI) = dbo.ftBoDau(_TEMP.DONVIQUYDOI) "+
							" INNER JOIN SANPHAM SP ON SP.MA = _TEMP.MASANPHAM " +
							" WHERE NOT EXISTS ( SELECT 1 FROM QUYCACH QC WHERE QC.SANPHAM_FK = SP.PK_SEQ AND QC.DVDL1_FK = DV1.PK_SEQ AND QC.DVDL2_FK = DV2.PK_SEQ ) ";
						
						System.out.println("quy cach : "+ query);
						db.update(query);
					}

					if (pass)
					{
						sheetwrite.addCell(new Label(1, 2 , "Thông báo: Upload Thành công!"));
						db.getConnection().commit();
						db.getConnection().setAutoCommit(true);
					}
				}

			}
			
			try
			{
				wr.write();
				wr.close();
			}
			catch(Exception e) { e.printStackTrace(); }
		}	
		catch (Exception e)
		{
			e.printStackTrace();
			//return "Vui lòng coi lại định dạng file " + e.getMessage();
		}
	}
	
	public void createTempTable(Idbutils db) {
		String query = "\n IF OBJECT_ID('tempdb.dbo.#temp_SP') IS NOT NULL DROP TABLE #temp_SP " +
		"\n create table #temp_SP ( " +
		"\n donvikinhdoanh nvarchar(max), " +
		"\n nganhhang nvarchar(max), " +
		"\n nhanhang nvarchar(max), " +
		"\n chungloai nvarchar(max), " +
		"\n masanpham nvarchar(max), " +
		"\n tensanpham nvarchar(max), " +
		"\n donvidoluong nvarchar(max), " + 	
		"\n trongluong varchar(10), " +
		"\n thetich varchar(10), " +
		"\n thuesuat varchar(10), " +
		"\n donvichuan nvarchar(max), " +
		"\n soluongdonvichuan varchar(10), " +
		"\n donviquydoi nvarchar(max), " +
		"\n soluongdonviquydoi varchar(10) " +
		") ";
		db.update(query);
		
		//Đơn vị chuẩn	Số lượng đv chuẩn	Đơn vị quy đổi	Số lượng đv quy đổi

	}
	
	private String getValue(jxl.Sheet sheet, int column, int row)
	{
		jxl.Cell cell;
		cell = sheet.getCell(column, row);
		try{
			return cell.getContents().replace("'", "");
		}catch(Exception er){
			return	"0";
		}
	}
	
}