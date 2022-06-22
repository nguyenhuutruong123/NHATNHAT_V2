package geso.dms.distributor.servlets.khachhang;

import geso.dms.distributor.beans.khachhang.*;
import geso.dms.distributor.beans.khachhang.imp.*;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.tagext.TryCatchFinally;

import org.json.JSONArray;
import org.json.JSONObject;

import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class KhachhangSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private int items = 50;
	private int splittings = 20;


	public KhachhangSvl() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		
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
		
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {

			IKhachhangList obj;
			PrintWriter out; 

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			out  = response.getWriter();

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			out.println(userId);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String action = util.getAction(querystring);
			out.println(action);

			String khId = util.getId(querystring);
			obj = new KhachhangList();
			String msg = "";

			String view = request.getParameter("view");
			if (view == null)
				view = "";
			obj.setView(view);

			if (action.equals("delete"))
			{	   		  	    	
				try
				{
					msg = Delete(khId, userId, obj);
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
					msg = e.getMessage();
				}
				obj.setMsg(msg);
			}
			if (action.equals("duyet"))
			{	   		  	    	
				try
				{
					msg = Duyet(khId, userId, request);
				} catch (SQLException e)
				{
					e.printStackTrace();

				}
				obj.setMsg(msg);
			}
			if (action.equals("dongbo"))
			{	   		  	    	
				try
				{
					msg = DongBo(khId);
				} 
				catch (SQLException e)
				{
					e.printStackTrace();

				}
				obj.setMsg(msg);
			}

			settingPage(obj);
			obj.setUserId(userId);
			obj.init("");
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHang.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private void settingPage(IKhachhangList obj) {
		Utility util = new Utility();
		if (getServletContext().getInitParameter("items") != null) {
			String i = getServletContext().getInitParameter("items").trim();
			if (util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if (getServletContext().getInitParameter("splittings") != null) {
			String i = getServletContext().getInitParameter("splittings").trim();
			if (util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}

		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else { 
			OutputStream out = response.getOutputStream();	
			IKhachhangList obj = new KhachhangList();

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			Utility util = new Utility();
			userId = util.antiSQLInspection(request.getParameter("userId"));

			String action = request.getParameter("action");
			if (action == null) {
				action = "";
			}

			String view = request.getParameter("view");
			if (view == null)
				view ="";
			obj.setView(view);

			if (action.equals("new"))
			{
				IKhachhang khBean = (IKhachhang) new Khachhang("");
				khBean.setView(view);
				khBean.setUserId(userId);
				khBean.createRS();

				if (!obj.getView().equals("TT"))
				{
					util = new Utility();
					util.getIdNhapp(userId);		

					if (util.getLoaiNpp().equals("0") ||util.getLoaiNpp().equals("1") ||util.getLoaiNpp().equals("2"))
					{
						khBean.setThanhtoan("1");
					}
					else if (util.getLoaiNpp().equals("4") ||util.getLoaiNpp().equals("5"))
					{
						khBean.setThanhtoan("0");
					}

					if (util.getLoaiNpp().equals("0") ||util.getLoaiNpp().equals("1") ||util.getLoaiNpp().equals("2")||util.getLoaiNpp().equals("3"))
					{
						khBean.setThanhtoanQuy("1");
					}
					else if (util.getLoaiNpp().equals("4") ||util.getLoaiNpp().equals("5"))
					{
						khBean.setThanhtoanQuy("0");
					}
				}

				session.setAttribute("khBean", khBean);
				String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangNew.jsp";
				response.sendRedirect(nextJSP);
			}

			settingPage(obj);

			if (action.equals("search"))
			{	    
				obj.setUserId(userId);
				String search = getSearchQuery(request, obj);

				obj.init(search);

				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);

				response.sendRedirect(request.getContextPath() + "/pages/Distributor/KhachHang.jsp");	    		    	
			}
			else if (action.equals("excel"))
			{
				obj.setUserId(userId);
				obj.setQuery(getSearchQuery(request, (IKhachhangList) obj));

				try
				{
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=DanhsachKhachhang_"+getDateTime()+".xls");

					Workbook workbook = new Workbook();

					CreateStaticHeader(workbook, "Admin");
					CreateStaticData(workbook, getQueryExcel(request, (IKhachhangList) obj));

					//Saving the Excel file
					workbook.save(out);
				}
				catch (Exception ex)
				{
					obj.setMsg("Khong the tao pivot.");
				}




				session.setAttribute("obj", obj);

				session.setAttribute("userId", userId);
				return;

				//response.sendRedirect(request.getContextPath() + "/pages/Distributor/KhachHang.jsp");	    		
			}
			else if (action.equals("excel_hoadon"))  //THU NGHIEM
			{
				System.out.println("--------BAT DAU IN................"); 
				try
				{
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=HoaDonInTuGESO.xls");

					Workbook workbook = new Workbook();

					CreateStaticHeader_HD(workbook, "HoaDon");
					CreateStaticData_HD(workbook);

					//Saving the Excel file
					workbook.save(out);
				}
				catch (Exception ex)
				{
					obj.setMsg("Khong the tao pivot.");
				}

				System.out.println("--------IN XONG................");   		
			}
			else  if (action.equals("view") || action.equals("next") || action.equals("prev")) {

				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				obj.setUserId(userId);
				String search = getSearchQuery(request, obj);
				obj.init(search);
				System.out.println("Phan Trang: "+request.getParameter("nxtApprSplitting"));   		
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);
				response.sendRedirect(request.getContextPath() + "/pages/Distributor/KhachHang.jsp");
			}
		}
	}

	private String getSearchQuery(HttpServletRequest request, IKhachhangList obj)
	{		
		Utility util = new Utility();
		util.getIdNhapp(obj.getUserId());

		String ten = util.antiSQLInspection(request.getParameter("khTen"));
		if (ten == null)
			ten = "";
		obj.setTen(ten);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String hchId = util.antiSQLInspection(request.getParameter("hchTen"));
		if (hchId == null)
			hchId = "";    	
		obj.setHchId(hchId);

		String kbhId = util.antiSQLInspection(request.getParameter("kbhTen"));
		if (kbhId == null)
			kbhId = "";    	
		obj.setKbhId(kbhId);

		String vtchId = util.antiSQLInspection(request.getParameter("vtchTen"));
		if (vtchId == null)
			vtchId = "";    	
		obj.setVtId(vtchId);

		String lchId = util.antiSQLInspection(request.getParameter("lchId"));
		if (lchId == null)
			lchId = "";    	
		obj.setLchId(lchId);

		String diadiemId = util.antiSQLInspection(request.getParameter("diadiemId"));
		if (diadiemId == null)
			diadiemId = "";    	
		obj.setDiadiemId(diadiemId);

		String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
		if (xuatkhau == null)
			xuatkhau = "0";    	
		obj.setXuatkhau(xuatkhau);

		String diachi = util.antiSQLInspection(request.getParameter("diachi"));
		if (diachi == null)
			diachi = "";    	
		obj.setDiachi(diachi.trim());

		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
		if (maFAST == null)
			maFAST = "";    	
		obj.setMaFAST(maFAST);

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";    	
		obj.setDdkdId(ddkdId);

		String tbhId = util.antiSQLInspection(request.getParameter("tbhId"));
		if (tbhId == null)
			tbhId = "";    	
		obj.setTbhId(tbhId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
		if (trangthai == null)
			trangthai = "";    		
		obj.setTrangthai(trangthai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay")); 	
		if (tungay == null)
			tungay = "";    		
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay")); 	
		if (denngay == null)
			denngay = "";    		
		obj.setDenngay(denngay);
		System.out.println("[UserID] : " + obj.getUserId());

		String loaiKH = util.antiSQLInspection(request.getParameter("loaikh")); 	
		if (loaiKH == null)
			loaiKH = "";    		
		obj.setloaiKH(loaiKH);

		String hopdong = util.antiSQLInspection(request.getParameter("hopdong")); 	
		if (hopdong == null)
			hopdong = "";    		
		obj.setHopdong(hopdong);
		
		String nppChinh = "1";
		if (!obj.getView().equals("TT"))
			nppChinh = "case when a.npp_fk = " + nppId + " then 1 else 0 end ";
		
		String  query = "\n	select isnull(a.dongbo,0) DongBo, " + nppChinh + " as nppChinh, isnull(a.daduyet,0) as khdaduyet, " +
		"\n ROW_NUMBER() OVER(ORDER BY a.pk_seq DESC) AS 'stt', isnull(a.mafast,'') as mafast , a.pk_seq as khId, " +
		"\n a.smartid, a.ten as khTen, a.trangthai, a.ngaytao, a.ngaysua,  isnull(b.ten,'') as nguoitao, isnull(c.ten,'') as nguoisua, " +
		//"\n isnull(ddkd.ten,b.ten) as nguoitao, isnull(ddkd2.ten,c.ten) as nguoisua, " +
		"\n d.chietkhau, d.pk_seq as mckId, e.diengiai as kbhTen, e.pk_seq as kbhId, f.hang as hchTen, " +
		"\n f.pk_seq as hchId, g.loai as lchTen, g.pk_seq as lchId, h.ten as nppTen, h.pk_seq as nppId, " +
		"\n	k.sotienno as ghcnTen, k.pk_seq as ghcnId, l.ten as bgstTen, l.pk_seq as bgstId, m.vitri as vtchTen, " +
		"\n	m.pk_seq as vtchId, a.dienthoai, a.diachi, " +
		"\n	STUFF   " +
		"\n	( " +
		"\n		(   "+
		"\n			select DISTINCT TOP 100 PERCENT ' , ' + tbh.DIENGIAI " +
		"\n			from KHACHHANG_TUYENBH khtbh inner join TUYENBANHANG tbh on tbh.PK_SEQ = khtbh.TBH_FK " + 
		"\n			where khtbh.KHACHHANG_FK = a.PK_SEQ and tbh.NPP_FK = a.NPP_FK  "+ 
		"\n			ORDER BY ' , ' + tbh.DIENGIAI   " +
		"\n			FOR XML PATH('')   " +
		"\n		 ), 1, 2, ''   "+
		"\n	) + ' '  AS tbhTen,a.CHUCUAHIEU,a.MaHD,n.ten as LoaiCH " + 
		"\n from " +
		"\n khachhang a inner join nhanvien b on a.nguoitao = b.pk_seq " + 
		"\n inner join nhanvien c on a.nguoisua = c.pk_seq " +
		"\n left join mucchietkhau d on a.chietkhau_fk = d.pk_seq   "+
		"\n left join kenhbanhang e on a.kbh_fk = e.pk_seq " +
		"\n left join hangcuahang f on a.hch_fk = f.pk_seq " +
		"\n left join loaicuahang g on a.lch_fk = g.pk_seq " +
		"\n inner join nhaphanphoi h on a.npp_fk = h.pk_seq " +
		"\n left join gioihancongno k on a.ghcn_fk = k.pk_seq " +
		"\n left join banggiasieuthi l on a.bgst_fk = l.pk_seq " +
		"\n left join vitricuahang m on a.vtch_fk = m.pk_seq " +
		"\n left join LOAIKHACHHANG n on n.pk_seq = a.XuatKhau " +
		//"\n left join daidienkinhdoanh ddkd on ddkd.pk_seq = a.DDDKTAO_FK " +
		//"\n left join daidienkinhdoanh ddkd2 on ddkd2.pk_seq = a.ddkdSUA_FK " +
		"\n where 1 = 1 ";

		if (obj.getTbhId().equals("-1"))
		{
			query += "\n and a.PK_SEQ NOT IN ( " +
			"\n     SELECT KHACHHANG_FK FROM KHACHHANG_TUYENBH " +
			"\n     where khachhang_fk in (select khachhang_fk from KHACHHANG_NPP where NPP_FK in " +
			"\n         (select Npp_fk from PHAMVIHOATDONG where Nhanvien_fk = '" + obj.getUserId() + "') " +
			"\n     )" +
			"\n )";
		}
		
		if (ten.length() > 0)
		{ 
			query = query + " and (upper(dbo.ftBoDau(a.ten)) like upper(N'%" + util.replaceAEIOU(ten) + "%') or a.mafast like upper(N'%" + ten.trim()+ "%')) ";			
		}

		if (kbhId.length() > 0) {
			query = query + " and a.kbh_fk = '" + kbhId + "'";	
		}

		if (hchId.length() > 0) {
			query = query + " and a.hch_fk = '" + hchId + "'";			
		}

		if (vtchId.length() > 0) {
			query = query + " and a.vtch_fk = '" + vtchId + "'";			
		}

		if (lchId.length() > 0) {
			query = query + " and a.lch_fk = '" + lchId + "'";			
		}

		if (diadiemId.length() > 0)
		{
			query += " and a.diadiem_fk = " + diadiemId + " ";
		}

		if (diachi.length() > 0)
		{
			query += " and (upper(dbo.ftBoDau(a.diachi)) like (N'%" + util.replaceAEIOU(diachi) + "%')) ";
		}

		if (maFAST.length() > 0)
		{
			query += " and upper(a.MAFAST) like upper((N'%" + util.replaceAEIOU(maFAST) + "%')) ";
		}

		if (ddkdId.length() > 0)
		{
			query += " and a.pk_Seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select PK_SEQ from tuyenbanhang where ddkd_Fk = '" + ddkdId + "')) ";
		}

		if (tbhId.length() > 0 && !tbhId.equals("-1"))
		{
			query += " and a.pk_Seq in (select KHACHHANG_FK from KHACHHANG_TUYENBH where tbh_fk = '" + tbhId + "') ";
		}
		else if (tbhId.equals("-1")) {
			query += " and a.pk_Seq not in (select KHACHHANG_FK from KHACHHANG_TUYENBH) ";
		}

		if (trangthai.length() > 0)
		{
			query = query + " and a.trangthai = '" + trangthai + "'";
		}

		if (tungay.length() > 0)
		{
			query = query + " and a.NGAYTAO > = '" + tungay + "'";
		}

		if (denngay.length() > 0)
		{
			query = query + " and a.NGAYTAO <= '" + denngay + "'";
		}

		if (loaiKH.length() > 0)
		{
			query = query + " and n.pk_seq = '" + loaiKH + "'";
		}

		if (hopdong.length() > 0)
		{
			query = query + " and a.KhongKyHopDong = '" + hopdong + "'";
		}

		if (nppId.length() > 0)
		{
			query += " and a.pk_seq in (select khachhang_fk from KHACHHANG_NPP where  npp_fk IN ('"+nppId+"'))";	
		}

		System.out.println("Query Search: " + query);
		
		return query;
	}	
	
	public static void main(String[] args) 
	{
		dbutils db = new dbutils();
		try
		{
			String 
			/*query =  "\n select PK_SEQ " + 
					 "\n from KHACHHANG kh " + 
					 "\n where not exists (select 1 from KHACHHANG_TUYENBH where KHACHHANG_FK = kh.PK_SEQ  )" + 
					 "\n 		and  not exists (select 1 from DONHANG where trangthai !=2 and KHACHHANG_FK = kh.PK_SEQ  ) ";*/
			query =  "\n select PK_SEQ " + 
					 "\n from KHACHHANG kh " + 
					 "\n where  pk_seq in (select pk_seq from khxoa20210727) " ;
			ResultSet rs= db.get(query);
			while (rs.next())
			{
				String khId = rs.getString("PK_SEQ");
				String deleteStr = KhachhangSvl.DeleteNghiepVu_KHachHang( khId,true);
				System.out.println("Xoa("+khId+"):"+ deleteStr);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		db.shutDown();
	}
	

	public static String Delete(String id, String userId,IKhachhangList obj) throws SQLException
	{
		dbutils db = new dbutils();
		//Utility util = new Utility();
		//String nppId = util.getIdNhapp(userId);
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "select count(*) as SoDong From DONHANG WHERE trangthai !=2 and KHACHHANG_FK = '"+id+"'";
			ResultSet rs = db.get(query);
			int SoDong = 0;
			while (rs.next())
			{
				SoDong = rs.getInt("SoDong");
			}
			rs.close();
			
			if (SoDong > 0)
			{
				return "Không thể xoá do đã phát sinh dữ liệu đơn hàng!";
			}		

			query = "delete from donhang_sanpham where donhang_fk in (select pk_seq from donhang where trangthai = 2 and khachhang_fk = '"+ id +"') ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 1.";
			}
			
			query = "delete from donhang_sanpham_chitiet where donhang_fk in (select pk_seq from donhang where trangthai = 2 and khachhang_fk = '"+ id +"') ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 2.";
			}
			
			query = "delete from donhang_ctkm_trakm where donhangId in ( select pk_seq from donhang where trangthai = 2 and khachhang_fk = '"+ id +"' ) ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 3.";
			}
			
			query = "delete from donhang_ctkm_trakm_chitiet where donhang_fk in ( select pk_seq from donhang where trangthai = 2 and khachhang_fk = '"+ id +"' ) ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 4.";
			}
			
			query = "delete from donhang where trangthai = 2 and khachhang_fk = '"+ id +"' ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 5.";
			}

			query = "delete from KhachHang_NPP where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 6.";
			}

			query = "delete from khachhang_nkhachhang where khachhang_fk='" + id + "'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 7.";
			}

			query = "delete from NHOMKHACHHANG_KHACHHANG where kh_fk='" + id + "'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 8.";
			}

			query = "delete from khachhang_tuyenBH where khachhang_fk='" + id + "'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 9.";
			}

			query = "delete from nvgn_kh where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 10.";
			}

			query = "delete from KHACHHANG_TUYENBH where khachhang_fk = '"+ id +"'";

			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 11.";
			}

			query = "delete from KHACHHANG_ANHCHUP where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 12.";
			}

			query = "delete from KHACHHANG_CONGNO where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 13.";
			}

			query = "delete from KhachHang_DaiDienKinhDoanh where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 14.";
			}

			query = "delete from KHACHHANG_KHODOITHU where KH_FK = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 14.";
			}

			query = "delete from KHACHHANG_MUCTIEUNGAY where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 15.";
			}

			query = "delete from KHACHHANG_TOADO_LOG where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 16.";
			}

			query = "delete from KHACHHANG_YKIEN where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 17.";			
			}

			query = "delete from ddkd_khachhang where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 18.";
			}

			query = "delete from DDKD_KHACHHANG_LOG where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 19.";
			}

			query = "delete from makhachhang where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 20.";
			}
			
			query = "delete from DDKD_Ngay_KH_Log where khachhang_fk = '"+ id +"'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa khách hàng 20.";
			}

			query = "delete from khachhang where pk_seq = '" + id + "'  ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				System.out.println("::::"+query);
				return "Không thể xóa khách hàng 21.";
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e) 
		{
			db.getConnection().rollback();
			e.printStackTrace();
			return "Exception xoá khách hàng: " + e.getMessage();
		}
		finally
		{
			if (db != null)
				db.shutDown();	
		}

		return "Xoá khách hàng thành công.";
	}

	public static String DeleteNghiepVu_KHachHang(String khId,boolean xoakh) throws SQLException {
		dbutils db = new dbutils();
		// Utility util = new Utility();
		// String nppId = util.getIdNhapp(userId);

		try {
			db.getConnection().setAutoCommit(false);

			String query = "";
			/// hóa đơn
			query = " delete hoadon_sp where hoadon_fk in (select pk_seq from hoadon where khachhang_fk="+khId+") ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}

			query = " delete hoadon_sp_CHitiet where hoadon_fk in (select pk_seq from hoadon where khachhang_fk="+khId+") ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete hoadon_chietkhau where hoadon_fk in (select pk_seq from hoadon where khachhang_fk="+khId+") ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete hoadon_ddh where hoadon_fk in (select pk_seq from hoadon where khachhang_fk="+khId+") ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete hoadon_ctkm_trakm where hoadon_fk in (select pk_seq from hoadon where khachhang_fk="+khId+") ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}

			
			query = " delete hoadon_ctkm_trakm_Chitiet where hoadon_fk in (select pk_seq from hoadon where khachhang_fk="+khId+") ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = "delete from hoadon where khachhang_fk="+khId+"  ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				System.out.println("::::" + query);
				return "Không thể xóa khách hàng 21.";
			}
			
			query =" select PXK_FK from PHIEUXUATKHO_DONHANG where donhang_fk in ( select pk_seq from donhang where khachhang_fk="+khId+" )";
			ResultSet rs= db.get(query);
			while(rs.next())
			{
				String pxkId = rs.getString("PXK_FK");
				// xuát kho
				query = " delete PHIEUXUATKHO_DONHANG where PXK_FK in ("+pxkId+") ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return "Không thể xóa khách hàng 1.";
				}
				query = " delete PHIEUXUATKHO_SANPHAM where PXK_FK in ("+pxkId+") ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return "Không thể xóa khách hàng 1.";
				}
				query = " delete PHIEUXUATKHO_SANPHAM_CHITIET where PXK_FK in ("+pxkId+") ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return "Không thể xóa khách hàng 1.";
				}
				query = " delete PHIEUXUATKHO_SPKM where PXK_FK in ("+pxkId+") ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return "Không thể xóa khách hàng 1.";
				}
				query = " delete PHIEUXUATKHO_SPKM_CHITIET where PXK_FK in ("+pxkId+") ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return "Không thể xóa khách hàng 1.";
				}
				query = " delete PHIEUXUATKHO_TIENKM where PXK_FK in ("+pxkId+") ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return "Không thể xóa khách hàng 1.";
				}
				
				query = "delete from PHIEUXUATKHO where pk_seq="+pxkId+"  ";
				if (!db.update(query)) {
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					System.out.println("::::" + query);
					return "Không thể xóa khách hàng 21.";
				}
			}
			
			
			
			
			
			query = " delete DONHANG_SANPHAM where donhang_Fk in (select pk_seq from donhang where khachhang_fk="+khId+")";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete DONHANG_SANPHAM_chitiet where donhang_Fk in (select pk_seq from donhang where khachhang_fk="+khId+")";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete donhang_ctkm_dkkm where donhang_Fk in (select pk_seq from donhang where khachhang_fk="+khId+")";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete DONHANG_CTKM_TRAKM where donhangID in (select pk_seq from donhang where khachhang_fk="+khId+")";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete DONHANG_CTKM_TRAKM_CHITIET where donhang_Fk in (select pk_seq from donhang where khachhang_fk="+khId+")";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete from donhang where khachhang_fk="+khId+"  ";
			if (!db.update(query)) {
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				System.out.println("::::" + query);
				return "Không thể xóa khách hàng 21.";
			}
			
			
			
			
			
			if(xoakh)
			{
				query = "delete from KhachHang_NPP where khachhang_fk in ( select pk_seq from khachhang where pk_seq = " + khId + " )   ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 6.";
				}

				query = "delete from khachhang_nkhachhang where khachhang_fk in ( select pk_seq from khachhang where pk_seq = " + khId + " )";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 7.";
				}

				query = "delete from NHOMKHACHHANG_KHACHHANG where kh_fk in ( select pk_seq from khachhang where pk_seq = " + khId + " )";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 8.";
				}

				query = "delete from khachhang_tuyenBH where khachhang_fk in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 9.";
				}

				query = "delete from nvgn_kh where khachhang_fk   in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 10.";
				}

				query = "delete from KHACHHANG_TUYENBH where khachhang_fk   in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";

				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 11.";
				}

				query = "delete from KHACHHANG_ANHCHUP where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 12.";
				}

				query = "delete from KHACHHANG_CONGNO where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 13.";
				}

				query = "delete from KhachHang_DaiDienKinhDoanh where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 14.";
				}

				query = "delete from KHACHHANG_KHODOITHU where KH_FK  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 14.";
				}

				query = "delete from KHACHHANG_MUCTIEUNGAY where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 15.";
				}

				query = "delete from KHACHHANG_TOADO_LOG where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 16.";
				}

				query = "delete from KHACHHANG_YKIEN where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 17.";			
				}

				query = "delete from ddkd_khachhang where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 18.";
				}

				query = "delete from DDKD_KHACHHANG_LOG where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 19.";
				}

				query = "delete from makhachhang where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 20.";
				}
				
				query = "delete from DDKD_Ngay_KH_Log where khachhang_fk  in ( select pk_seq from khachhang where pk_seq = " + khId + " ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 20.";
				}

				query = "delete from khachhang where  pk_seq = " + khId + " ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					System.out.println("::::"+query);
					return "Không thể xóa khách hàng 21.";
				}
				
			}
			
			

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "Xoá khách hàng thành công.";
		} catch (Exception e) {
			geso.dms.center.util.Utility.rollback_and_shutdown(db);
			e.printStackTrace();
			return "Exception xóa: " + e.getMessage();
		} 

		
	}
	
	private String getQueryExcel(HttpServletRequest request, IKhachhangList obj)
	{
		Utility util = new Utility();
		String ten = util.antiSQLInspection(request.getParameter("khTen"));
		if (ten == null)
			ten = "";
		obj.setTen(ten);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		
		String hchId = util.antiSQLInspection(request.getParameter("hchTen"));
		if (hchId == null)
			hchId = "";    	
		obj.setHchId(hchId);

		String kbhId = util.antiSQLInspection(request.getParameter("kbhTen"));
		if (kbhId == null)
			kbhId = "";    	
		obj.setKbhId(kbhId);

		String vtchId = util.antiSQLInspection(request.getParameter("vtchTen"));
		if (vtchId == null)
			vtchId = "";    	
		obj.setVtId(vtchId);

		String lchId = util.antiSQLInspection(request.getParameter("lchTen"));
		if (lchId == null)
			lchId = "";    	
		obj.setLchId(lchId);

		String diadiemId = util.antiSQLInspection(request.getParameter("diadiemId"));
		if (diadiemId == null)
			diadiemId = "";    	
		obj.setDiadiemId(diadiemId);

		String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
		if (xuatkhau == null)
			xuatkhau = "0";    	
		obj.setXuatkhau(xuatkhau);

		String diachi = util.antiSQLInspection(request.getParameter("diachi"));
		if (diachi == null)
			diachi = "";    	
		obj.setDiachi(diachi.trim());

		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
		if (maFAST == null)
			maFAST = "";    	
		obj.setMaFAST(maFAST.trim());

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";    	
		obj.setDdkdId(ddkdId);

		String tbhId = util.antiSQLInspection(request.getParameter("tbhId"));
		if (tbhId == null)
			tbhId = "";    	
		obj.setTbhId(tbhId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai")); 	
		if (trangthai == null)
			trangthai = "";    		
		obj.setTrangthai(trangthai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay")); 	
		if (tungay == null)
			tungay = "";    		
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay")); 	
		if (denngay == null)
			denngay = "";    		
		obj.setDenngay(denngay);

		String loaiKH = util.antiSQLInspection(request.getParameter("lchId")); 	
		if (loaiKH == null)
			loaiKH = "";    		
		obj.setloaiKH(loaiKH);

		String hopdong = util.antiSQLInspection(request.getParameter("hopdong")); 	
		if (hopdong == null)
			hopdong = "";    		
		obj.setHopdong(hopdong);

			
		String query = "\n select isnull(a.moiquanhe,'')moiquanhe,isnull(a.thoigiangap,'')thoigiangap,(case when a.gioitinh=0 then N'Nữ' when a.gioitinh=1 then N'Nam' else '' end) gioitinh,ROW_NUMBER() OVER(ORDER BY a.pk_seq DESC) AS 'stt', isnull(a.mafast,'') as mafast , a.pk_seq as khId, a.smartid, a.ten as khTen, isnull(a.MST_CaNhan,'') as MST_CaNhan,a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua, d.chietkhau, d.pk_seq as mckId,  " + 
		"\n		e.diengiai as kbhTen, e.pk_seq as kbhId, f.hang as hchTen, f.pk_seq as hchId, g.loai as lchTen, g.pk_seq as lchId, '' as nppId,   " +
		"\n		k.sotienno as ghcnTen, k.pk_seq as ghcnId, l.ten as bgstTen, l.pk_seq as bgstId, m.vitri as vtchTen,  " +
		"\n		m.pk_seq as vtchId, a.dienthoai, a.diachi,  isnull(a.MASOTHUE,'') as masothue, ISNULL(a.cmnd,'') as cmnd ,ISNULL(a.ngaysinh,'') as ngaysinh, ISNULL(a.MaHD,'') as maHD, g.LOAI as loaKH , " +
		"\n STUFF       " +
		"\n (     " +
		"\n 	(    " +
		"\n 		select ', ' + ten    " +
		"\n 		from nhaphanphoi aa inner join khachhang_npp bb on aa.pk_seq = bb.npp_fk  " +
		"\n 		where khachhang_fk = a.pk_seq  " +
		"\n 		FOR XML PATH('')       " +
		"\n 	 ), 1, 2, ''    " +
		"\n ) + ' '  AS nppTen, " +
		"\n STUFF       " +
		"\n (     " +
		"\n 	(    " +
		"\n 		select '; ' + convert(varchar,tbh.ngayid)     " +
		"\n 		from KHACHHANG_TUYENBH khtbh inner join TUYENBANHANG tbh on tbh.PK_SEQ=khtbh.TBH_FK    " +
		"\n 		where khtbh.KHACHHANG_FK=a.PK_SEQ    " +
		"\n 		order by tbh.ngayid      " +
		"\n 		FOR XML PATH('') " +
		"\n 	 ), 1, 2, ''    " +
		"\n ) + ''  AS tbhTen, " +
		"\n a.CHUCUAHIEU, " +
		"\n isnull(a.lat,'0')lat, isnull(a.long,'0')lon, isnull(a.ANHCUAHANG,'')ANHCUAHANG, " +
		"\n tdv.mafast maNVBH, tdv.ten tentdv, gsbh.ten tengsbh " +
		"\n  FROM    " +
		"\n  khachhang a inner join nhanvien b on a.nguoitao = b.pk_seq   " + 
		"\n  inner join nhanvien c on a.nguoisua = c.pk_seq    " +
		"\n  left join mucchietkhau d on a.chietkhau_fk = d.pk_seq   " +
		"\n  left join kenhbanhang e on a.kbh_fk = e.pk_seq    " +
		"\n  left join hangcuahang f on a.hch_fk = f.pk_seq    " +
		"\n  left join loaicuahang g on a.lch_fk = g.pk_seq    " +
		"\n  inner join nhaphanphoi h on a.npp_fk = h.pk_seq    " +
		"\n  left join gioihancongno k on a.ghcn_fk = k.pk_seq    " +
		"\n  left join banggiasieuthi l on a.bgst_fk = l.pk_seq    " +
		"\n  left join vitricuahang m on a.vtch_fk = m.pk_seq    " +
		//"\n left join LOAIKHACHHANG n on a.XuatKhau= n.pk_seq  " +
		"\n left join  " +
		"\n ( " +
		"\n 	select aaa.pk_seq,mafast, ten, khachhang_fk from daidienkinhdoanh aaa inner join tuyenbanhang bbb on aaa.pk_Seq = bbb.DDKD_FK " +
		"\n 	inner join khachhang_tuyenbh ccc on ccc.TBH_FK = bbb.PK_SEQ " +
		"\n 	group by aaa.pk_seq,mafast, ten, khachhang_fk  " +
		"\n ) tdv on tdv.khachhang_fk = a.pk_seq " +
		"\n left join " +
		"\n ( " +
		"\n 	select ddkd_fk, gsbh_fk, bbb.ten from ddkd_gsbh aaa inner join GIAMSATBANHANG bbb on aaa.GSBH_FK = bbb.PK_SEQ " +
		"\n 	group by ddkd_fk, gsbh_fk, bbb.ten " +
		"\n ) gsbh on gsbh.DDKD_FK = tdv.pk_seq " +
		"\n where 1 = 1 ";
		
		if (obj.getTbhId().equals("-1"))
		{
			query += "\n and a.PK_SEQ NOT IN " +
			"\n ( " +
			"\n     SELECT KHACHHANG_FK " +
			"\n     FROM KHACHHANG_TUYENBH " +
			"\n     where khachhang_fk in " +
			"\n     ( " +
			"\n         select khachhang_fk " +
			"\n         from KHACHHANG_NPP " +
			"\n         where NPP_FK in (select Npp_fk from PHAMVIHOATDONG where Nhanvien_fk = '" + obj.getUserId() + "') " +
			"\n     ) " +
			"\n )";
		}
		
		if (ten.length() > 0)
		{ 	
			query = query + "\n and ( upper(dbo.ftBoDau(a.ten)) like upper(N'%" + util.replaceAEIOU(ten) + "%') or a.smartid like upper(N'%" + ten.trim()+ "%')) ";			
		}

		if (kbhId.length() > 0) {
			query = query + "\n and a.kbh_fk ='" + kbhId + "'";	
		}

		if (hchId.length() > 0) {
			query = query + "\n and a.hch_fk='" + hchId + "'";			
		}

		if (vtchId.length() > 0) {
			query = query + " and a.vtch_fk='" + vtchId + "'";			
		}

		if (lchId.length() > 0) {
			query += "\n and a.lch_fk='" + lchId + "'";			
		}

		if (diadiemId.length() > 0)
		{
			query += "\n and a.diadiem_fk="+diadiemId+" ";
		}

		if (diachi.length() > 0)
		{
			query += "\n and (upper(dbo.ftBoDau(a.diachi)) like (N'%" + util.replaceAEIOU(diachi) + "%') )  ";
		}

		if (maFAST.length() > 0)
		{
			query += "\n and a.maFAST like N'%"+maFAST+"%' ";
		}

		if (ddkdId.length() > 0)
		{
			query += "\n and a.pk_Seq in (select khachhang_fk from KHACHHANG_TUYENBH where tbh_fk in (select PK_SEQ from tuyenbanhang where ddkd_Fk='"+ddkdId+"')) ";
		}

		if (tbhId.length() > 0)
		{
			query += "\n and a.pk_Seq in (select KHACHHANG_FK from KHACHHANG_TUYENBH where tbh_fk ='"+tbhId+"' ) ";
		}

		if (trangthai.length() > 0)
		{
			query = query + "\n and a.trangthai='" + trangthai + "'";
		}

		if (tungay.length() > 0)
		{
			query = query + "\n and a.NGAYTAO>='" + tungay + "'";
		}

		if (denngay.length() > 0)
		{
			query = query + "\n and a.NGAYTAO<='" + denngay + "'";
		}

		if (loaiKH.length() > 0)
		{
			query = query + "\n and g.pk_seq='" + loaiKH + "'";
		}

		if (hopdong.length() > 0)
		{
			query = query + "\n and a.KhongKyHopDong='" + hopdong + "'";
		}
		if (nppId.length() > 0)
		{
			query += "\n and exists (select 1 from khachhang_npp where khachhang_fk = a.pk_seq and npp_fk = "+obj.getNppId()+") ";
		}
		System.out.println("Query excel: "+query );


		return query;

	}

	private void CreateStaticHeader(Workbook workbook, String UserName) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();
		Style style;

		Cell cell = cells.getCell("A1"); 
		cells.merge(0,0,0,11);
		cell.setValue("DANH SÁCH KHÁCH HÀNG");	
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
		int cot = 0;
		cell = cells.getCell(4,cot++);cell.setValue("Giám sát bán hàng");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Mã NVBH");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Nhân viên bán hàng");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED); 
		cell = cells.getCell(4,cot++);cell.setValue("Tuyến");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);    
		cell = cells.getCell(4,cot++);cell.setValue("Mã Fast");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);    
		cell = cells.getCell(4,cot++);cell.setValue(" Mã hợp đồng");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Phân loại KH ");  					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Họ và Tên khách hàng ");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Tên đơn vị "); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Địa chỉ");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Mã số thuế"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Mã số thuế TNCN"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Số CMND "); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Số điện thoại"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue(" Ngày sinh"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);

		cell = cells.getCell(4,cot++);cell.setValue("Đã XĐTĐ"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Đã chụp hình"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Giới tính"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Mối quan hệ với chủ nhà thuốc"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Thời gian gặp chủ nhà thuốc"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell(4,cot++);cell.setValue("Nhà phân phối chính"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);

		worksheet.setName(" DSKH ");
	}

	private void CreateStaticData(Workbook workbook, String query) 
	{


		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		//System.out.println("Get san pham :"+query);
		NumberFormat formatter = new DecimalFormat("#,###,###");
		int i = 5;
		if (rs != null)
		{
			try 
			{
				cells.setColumnWidth(0, 15f);
				cells.setColumnWidth(1, 40.0f);
				cells.setColumnWidth(2, 30.0f);
				cells.setColumnWidth(3, 16.0f);
				cells.setColumnWidth(4, 16.0f);
				cells.setColumnWidth(5, 28.0f);
				cells.setColumnWidth(6, 40.0f);
				cells.setColumnWidth(7, 50.0f);
				cells.setColumnWidth(8, 15.0f);
				cells.setColumnWidth(9, 15.0f);
				cells.setColumnWidth(10, 15.0f);
				cells.setColumnWidth(11, 15.0f);
				cells.setColumnWidth(12, 15.0f);
				cells.setColumnWidth(13, 35.0f);
				cells.setColumnWidth(14, 35.0f);

				for (int j = 0; j < 11; j++)
				{
					if (j==0)
						cells.setRowHeight(j, 23.0f);
					else
						cells.setRowHeight(j, 13.0f);
				}

				Cell cell = null;

				Style style;
				Font font2 = new Font();
				font2.setName("Calibri");				
				font2.setSize(11);

				while (rs.next())
				{
					int cot = 0;
					String tengsbh = rs.getString("tengsbh");
					String maNVBH = rs.getString("maNVBH");
					String tentdv = rs.getString("tentdv");
					String ddkd = rs.getString("tbhTen");
					String mafast = rs.getString("mafast");
					String loaiKH = rs.getString("loaKH");
					String chuCH = rs.getString("CHUCUAHIEU");
					String ten = rs.getString("khTen");
					String diachi = rs.getString("diachi");
					String masothue = rs.getString("masothue");
					String CMND = rs.getString("cmnd");
					String sodt = rs.getString("dienthoai");
					String ngaysinh = rs.getString("ngaysinh");
					String maHD = rs.getString("maHD");
					String mast_tncn=rs.getString("MST_CaNhan");
					
					cell = cells.getCell(i,cot++);	cell.setValue(tengsbh); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(maNVBH); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(tentdv); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(ddkd); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(mafast); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(maHD); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(loaiKH); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(chuCH); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(ten); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(diachi); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(masothue); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(mast_tncn); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(CMND); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(sodt); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(ngaysinh); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(rs.getString("lat").equals("0")?"0":"1"); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(rs.getString("ANHCUAHANG").equals("")?"0":"1"); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					String gioitinh = rs.getString("gioitinh");
					cell = cells.getCell(i,cot++);	cell.setValue(gioitinh); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(rs.getString("moiquanhe")); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(rs.getString("thoigiangap")); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell(i,cot++);	cell.setValue(rs.getString("nppTen")); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					
					
					i++;
				}
				rs.close();
			}
			catch (Exception e) { e.printStackTrace(); }
		}
	}

	private void setCellBorderStyle(Cell cell, short align) {
		Style style = cell.getStyle();
		style.setHAlignment(align);
		style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);
		cell.setStyle(style);
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}




	private void CreateStaticHeader_HD(Workbook workbook, String UserName) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();
		Style style;

		Cell cell = cells.getCell("A1"); 
		cell.setValue("DANH SÁCH HÓA ĐƠN");	
		style = cell.getStyle();
		Font font2 = new Font();	
		font2.setName("Calibri");
		font2.setColor(Color.NAVY);
		font2.setSize(18);
		font2.setBold(true);
		style.setFont(font2);
		style.setHAlignment(TextAlignmentType.LEFT);					
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
		cell = cells.getCell("A5");cell.setValue("ID hóa đơn");  		style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);    
		cell = cells.getCell("B5");cell.setValue("Số hóa đơn");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("C5");cell.setValue("Ký hiệu hóa đơn");  					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("D5");cell.setValue("Ngày hóa đơn"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);

		cell = cells.getCell("E5");cell.setValue("Mã hàng");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("F5");cell.setValue("Tên hàng hóa, dịch vụ");  				style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("G5");cell.setValue("Số lô"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("H5");cell.setValue("Hạn dùng"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("I5");cell.setValue("ĐVT"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("J5");cell.setValue("Số lượng"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("K5");cell.setValue("Đơn giá"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("L5");cell.setValue("Thành tiền"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("M5");cell.setValue("TS %"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("N5");cell.setValue("Thuế GTGT"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);
		cell = cells.getCell("O5");cell.setValue("Số tiền TT"); 					style.setFont(font2); cell.setStyle(style);	setCellBorderStyle(cell, HorizontalAlignmentType.CENTRED);

		worksheet.setName(" HOA DON ");
	}

	private void CreateStaticData_HD(Workbook workbook) 
	{

		System.out.println("Vào đây !");
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		dbutils db = new dbutils();

		String query = "";

		ResultSet rs = db.get(query);
		int i = 6;
		if (rs != null)
		{
			try 
			{
				cells.setColumnWidth(0, 40.0f);
				cells.setColumnWidth(1, 30.0f);
				cells.setColumnWidth(2, 16.0f);
				cells.setColumnWidth(3, 16.0f);
				cells.setColumnWidth(4, 28.0f);
				cells.setColumnWidth(5, 40.0f);
				cells.setColumnWidth(6, 50.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);
				cells.setColumnWidth(9, 15.0f);
				cells.setColumnWidth(10, 15.0f);

				for (int j = 0; j < 11; j++)
				{
					if (j==0)
						cells.setRowHeight(j, 23.0f);
					else
						cells.setRowHeight(j, 13.0f);
				}

				Cell cell = null;

				Style style;
				Font font2 = new Font();
				font2.setName("Calibri");				
				font2.setSize(11);

				while (rs.next())
				{

					String ddkd = rs.getString("tbhTen");
					String maKH = rs.getString("mafast");
					String loaiKH = rs.getString("loaKH");
					String chuCH = rs.getString("CHUCUAHIEU");
					String ten = rs.getString("khTen");
					String diachi = rs.getString("diachi");
					String masothue = rs.getString("masothue");
					String CMND = "";
					String sodt = rs.getString("masothue");
					String ngaysinh = "";
					String maHD = rs.getString("maHD");

					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(ddkd); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(maKH); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(loaiKH); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);

					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(chuCH); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(ten); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(diachi); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(masothue); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(CMND); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(sodt); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(ngaysinh); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue(maHD); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell, HorizontalAlignmentType.LEFT);
					i++;
				}
				rs.close();
			}
			catch (Exception e) { e.printStackTrace(); }
		}
	}
	
  
	
	private String Duyet(String id, String userId, HttpServletRequest request) throws SQLException
	{
		String status = "0";
		String msg = ""; 
		dbutils db = new dbutils();
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "update khachhang set daduyet =1 where isnull(daduyet,0) = 0 and pk_Seq ='" + id + "'";
			//System.out.println(query);
			if (db.updateReturnInt(query)!=1)
			{
				geso.dms.center.util.Utility.rollback_and_shutdown(db);
				return "Không thể duyệt Khách Hàng.";
			}
			
			query = "select count(1) num from khachhang kh where kh.pk_seq = '"+ id +"' and exists ( select 1 from nhaphanphoi npp where npp.pk_seq = kh.npp_fk and npp.loainpp = '0' ) ";
			ResultSet rsCheck = db.get(query);
			rsCheck.next();
			int num = 0;
			num = rsCheck.getInt("num");
			rsCheck.close();
			
			if(num > 0)
			{
				query = "SELECT '' as MaKho,'' as MaNX,'' tuvanchuyen, kh.smartid, "
						+ " ( SELECT TEN FROM GIAMSATBANHANG WHERE PK_SEQ = ( select gsbh_fk from ddkd_gsbh where ddkd_fk = ( select DISTINCT ddkd_fk from TUYENBANHANG x inner join KHACHHANG_TUYENBH y on x.PK_SEQ = y.TBH_FK where y.KHACHHANG_FK = KH.PK_SEQ ) ) ) AS gsbhTen, "
						+ " ( SELECT TEN FROM DAIDIENKINHDOANH WHERE PK_SEQ = ( select DISTINCT ddkd_fk from TUYENBANHANG x inner join KHACHHANG_TUYENBH y on x.PK_SEQ = y.TBH_FK where y.KHACHHANG_FK = KH.PK_SEQ ) ) AS ddkdTen, "
						+ " (select top 1 ten from KHUVUC where pk_seq in (select khuvuc_fk from KHUVUC_QUANHUYEN where QUANHUYEN_FK = kh.quanhuyen_fk)) as khuvuc, "+ 
						" CONVERT (char(10),getdate(),126) as  ngaytao, CONVERT (char(10),getdate(),126) as ngaysua, 100003 nguoitao, 100003 as nguoisua,\r\n" + 
						" kh.dienthoai, kh.diachigiaohang as diachi, kh.trangthai, \r\n" + 
						" kh.ten, kh.maFAST as ma, kh.MaFAST, kh.tinhthanh_fk, kh.quanhuyen_fk, kh.masothue, kh.diachigiaohang diachixhd, NULL as khosap, \r\n" + 
						" kh.CHUCUAHIEU as TENNGUOIDAIDIEN,'' as FAX,'' AS  EMAIL,'' as  HINHTHUCTHANHTOAN,'' NGANHANG, '' SOTAIKHOAN, kh.GHICHU,\r\n" + 
						" '' ngaybatdau, ''ngayketthuc, 0 as ChietKhau, NULL DDKD_FK,'' XUATTAIKHO, kh.TenKyHd, kh.cmnd,0 HanMucDoanhThu, '' thukho, \r\n" +
						" KH.KBH_FK, kbh.ten as kbhten, NULL taiKhoanKHNB_FK, 100000 as CONGTY_FK, ( select top(1) ma from nhaphanphoi where pk_seq = kh.npp_fk ) as NPP_FK, '' congnovo,'' congnoket, isnull(kh.sotienno,0) hanmucno, "+
						" isnull(kh.songayno,0) songayno,NULL khuvucgiaohang_fk,NULL loaikh_fk,NULL nhomkh_fk,kh.ten  TENXUATHD,0  ISDTK, kh.pk_seq,1 IS_KHACHHANG, "
						+ " (select TOP(1) LOAI from LOAICUAHANG LCH where LCH.PK_SEQ = KH.LCH_FK ) AS LCH_FK "
						+ " FROM KHACHHANG KH "
						+ " INNER JOIN NHAPHANPHOI NPP ON KH.NPP_FK = NPP.PK_SEQ "
						+ " left join kenhbanhang kbh on kbh.pk_seq = kh.kbh_fk "
						+ " WHERE npp.loainpp = 0 and KH.PK_SEQ = "+ id +" ";
				
				//System.out.println("query : "+ query);
				ResultSet rs = db.get(query);
				ResultSetMetaData rsmd = rs.getMetaData();
				JSONObject obj = new JSONObject();
				while(rs.next()) 
				{
				  int numColumns = rsmd.getColumnCount();
				  for (int i=1; i<=numColumns; i++) 
				  {
				    String column_name = rsmd.getColumnName(i);
				    obj.put(column_name, rs.getObject(column_name) == null ? "" : rs.getObject(column_name));
				  }
				}
				System.out.println(obj);			
				
				String urlParam = "khachhangchitiet="+ obj;
				//System.out.println("urlParam : " + urlParam); 
				String url = request.getServletContext().getInitParameter("path_api") + "DuyetKhachHang";
				//String url = "http://localhost:56491/API.asmx/DuyetKhachHang";
				//http://localhost:56491/API.asmx
			  
				String data = ""; 
				try 
				{ 
					  data = geso.dms.center.util.Utility.sendPost(url, urlParam);
					  JsonObject odata = (JsonObject) new JsonParser().parse(data);
					  System.out.println(odata);
					  status = odata.get("status").getAsString(); 
					  msg = odata.get("msg").getAsString();
				} catch (Exception e1) 
				{ 
					e1.printStackTrace(); 
					status = "0"; 
					msg = " Không thể kết nối tới server."; 
				}
				
				System.out.println("status "+ status);
				if(status.trim().equals("1"))
				{
					// GHI LOG
					String sql_log = "INSERT INTO KHACHHANG_API_LOG(ID, USERID, _DATE, URL_1) "
							+ " SELECT '"+ id +"' AS ID, '"+ userId +"' AS USERID, GETDATE() AS _DATE, N'"+ urlParam.replaceAll("'", "''") +"' AS URL_1 ";
					db.update(sql_log);
					
					//System.out.println("vo day !");
					geso.dms.center.util.Utility.commit_and_shutdown(db);
					return msg;
				}
				else
				{
					//System.out.println("khong vo day co !");
					geso.dms.center.util.Utility.rollback_and_shutdown(db);
					return msg;
				}
			}
			
			geso.dms.center.util.Utility.commit_and_shutdown(db);
			return msg;
						
		}catch(Exception e) 
		{
			db.shutDown();
			e.printStackTrace();
			return "Không thể duyệt Khách hàng. Lỗi: " + e.getMessage();
		}
	}
 
	private String DongBo(String id) throws SQLException
	{
		dbutils db = new dbutils();

		try 
		{
			db.getConnection().setAutoCommit(false);

			String query = "update khachhang set dongbo = 1 where isnull(daduyet,0) = 0 and isnull(dongbo,0) = 0 and pk_Seq ='" + id + "'";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể đồng bộ khách hàng!";
			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e) 
		{
			db.getConnection().rollback();
			e.printStackTrace();
			return "Exception đồng bộ: " + e.getMessage();
		}
		finally {
			if (db != null) {
				db.shutDown();
			}
		}
		
		return "Đồng bộ thành công!";
	}
}
