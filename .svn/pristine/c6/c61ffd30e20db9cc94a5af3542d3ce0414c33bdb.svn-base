package geso.dms.distributor.servlets.donhang;

import geso.dms.center.beans.nhaphanphoi.INhaphanphoiList;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.IDonhangList;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.beans.donhang.imp.DonhangList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;
import com.extentech.formats.XLS.UsrExcl;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class DonhangSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;

	private int items = 50; 
	//String userId;
	//String nppId;

	private int splittings = 20;

	public DonhangSvl() 
	{
		super();
	}

	private void settingPage(IDonhangList obj) {
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

	protected void doGet(HttpServletRequest request,  HttpServletResponse response) throws ServletException, IOException 
	{
		
		

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
		
		
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	
		final HttpServletResponse response2=response;
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			
			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			this.out  = response.getWriter();


			session.setMaxInactiveInterval(30000);

			
			
			
			Utility util = new Utility();
			out = response.getWriter();

			
			
			 String querystring = request.getQueryString();
			 
			String url = request.getHeader("Initiator");
			System.out.println("URL::::::::::::::"+url);
			System.out.println("URL2::::::::::::::"+request.getAttribute("javax.servlet.forward.request_uri"));
			
			 String view = util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("view")));
				if (view == null) {
					view = "";
				}
				
			
			
			userId = util.getUserId(querystring);
			//out.println(userId);
			System.out.println();
			Date date = new Date();
			System.out.println("DonhangSvl user :" + userId + "  ,sessionId: " + session.getId() );

			if (userId.length()==0)
				userId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("userId"));
			String nppId;
			if(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("nppId")) != null)
				nppId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("nppId"));


			//Lay Nha PP Theo Dang Nhap Moi
			nppId = util.getIdNhapp(userId);

			String action = util.getAction(querystring);
			if(action == null)
				action = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("action"));
			System.out.println("ACTION LA: " + action);

			String dhId = util.getId(querystring);

			String msg = "";
			
			if (action.equals("unChot")) {
				IDonhang dh = new Donhang("");
				String _msg = "";
				_msg = dh.MoChotDonHang(dhId, userId, dh.getDb());
				if (_msg != null && _msg.length() > 0) {
					System.out.println("vo dayyyy");
					msg = _msg;
				}
				else {
					msg = "Mở chốt đơn hàng thành công!";
				}
			}
			
			IDonhangList obj; 
			obj = new DonhangList();	
			obj.setUserId(userId);
			settingPage(obj);
			obj.init("");
			obj.setMsg(msg);
			session.setAttribute("obj", obj);
			session.setAttribute("khId", "");
			
			String nextJSP = request.getContextPath() + "/pages/Distributor/DonHang.jsp";
			response.sendRedirect(nextJSP);
		}
	}
	
	void PDA_CHOT( HttpServletRequest request, HttpServletResponse response )  throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String dhId = request.getParameter("dhId");
		String userId = request.getParameter("userId");
		System.out.println("dhId = "+ dhId);
		Donhang dh = new Donhang("");		
		String msg = dh.DuyetDonHang(dhId, Donhang.PDA_CLICK, userId, request);		
		String kq = dh.getKqDuyet();
		 dh.DBclose();
		response.getWriter().write("{\"RESULT\":\""+kq+"\",\"MSG\":\""+msg+"\"}");
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if(request.getParameter("isPDADuyet")!= null && request.getParameter("isPDADuyet").equals("1") )
		{
			PDA_CHOT(  request,  response );
			return;
		}
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");


			session.setMaxInactiveInterval(30000);
			//--- check user
			Utility util = new Utility();
		    String view = util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("view")));
			if (view == null) {
				view = "";
			}
		
			userId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("userId"));
			System.out.println();
			System.out.println("DonhangSvl user :" + userId + "  ,sessionId: " + session.getId()) ;
			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}

			if (action.equals("new"))
			{
				// Empty Bean for distributor
				IDonhang dhBean = (IDonhang) new Donhang("");
				dhBean.setUserId(userId);
				dhBean.createRS();

				// Save Data into session
				session.setAttribute("dhBean", dhBean);//truyen vao session mot doi tuong donhang co gia tri rong khi khoi tao de ben form don hang nhan dc
				session.setAttribute("khId", "");
				session.setAttribute("ddkdId", "");
				session.setAttribute("ngaydonhang", "" );
				session.setAttribute("nppId", dhBean.getNppId());

				String nextJSP = request.getContextPath() + "/pages/Distributor/DonHangNew.jsp";
				response.sendRedirect(nextJSP);
			}
			else if(action.equals("giaohang")) {
				
				String dhId = util.antiSQLInspection(request.getParameter("dhId"));
				String nppId =util.antiSQLInspection( request.getParameter("nppId"));
				IDonhangList obj =  new DonhangList();
				obj.setUserId(userId);
				String update_trangthai = "update DONHANG\r\n" + 
						"set TRANGTHAI = 5\r\n" + 
						"where npp_fk = '"+nppId +"'   \r\n" + 
						"	and PK_SEQ = '"+dhId +"' \r\n" + 
						"	and TRANGTHAI = 4";
				dbutils db = new dbutils();
				try {
					db.getConnection().setAutoCommit(false);
					db.update(update_trangthai);
					db.getConnection().commit();
				} catch (Exception e) {
					System.out.println("Loi khi update trang thai giao hang : "+e);

					try {
						db.getConnection().setAutoCommit(true);
						db.getConnection().rollback();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				obj.init(getSearchQuery(request, obj));
				session.setAttribute("userId", userId);
				session.setAttribute("obj", obj);
				response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHang.jsp");
				System.out.println(update_trangthai);
			}
			else
			{
				IDonhangList obj;
				obj = new DonhangList();
				settingPage(obj);

				obj.setUserId(userId);

				if(action.equals("search"))
				{
					obj.setUserId(userId);
					String search = getSearchQuery(request, obj);
					if(obj.getIsSearch())
					{
						String sumqr = getSumQuery(request, obj);
						obj.getSumBySearch(sumqr);
					}
					
					System.out.println("IS SEARCH: " + obj.getIsSearch() + " ---- cau lenh chay:"+ search);

					obj.init(search);
					session.setAttribute("userId", userId);
					session.setAttribute("obj", obj);

					response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHang.jsp");	    		    	
				}
				else if(action.equals("view") || action.equals("next") || action.equals("prev"))
				{
					obj.setUserId(userId);
					System.out.println("____"+request.getParameter("nxtApprSplitting")+"____");
					String search = getSearchQuery(request, obj);

					obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

					obj.init(search);
					obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
					session.setAttribute("obj", obj);
					response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHang.jsp");
				}
				else if(action.equals("delete"))
				{
					String dhId = util.antiSQLInspection(request.getParameter("dhId"));
					String nppId =util.antiSQLInspection( request.getParameter("nppId"));
					
					String msg = "";
					String lydoxoa = util.antiSQLInspection(request.getParameter("lydoxoa"));
					if(lydoxoa == null)
						lydoxoa = "";
					System.out.println("Ly do xoa: " + lydoxoa);

					msg = XoaDonHang_Trahaco(dhId, nppId, lydoxoa, userId);

					obj = new DonhangList();
					obj.setUserId(userId);
					settingPage(obj);
					obj.init("");
					obj.setMsg(msg);

					session.setAttribute("obj", obj);
					session.setAttribute("khId", "");

					String nextJSP = request.getContextPath() + "/pages/Distributor/DonHang.jsp";
					response.sendRedirect(nextJSP);	
				}
				else if(action.equals("duyet"))  //DUYET PHAI DUNG DOPOST THI MOI GIU LAI DUOC CAC DK LOC
				{
					String dhId = util.antiSQLInspection(request.getParameter("dhId"));
					System.out.println("dhId = "+ dhId);
					Donhang dh = new Donhang("");
					String msg = dh.DuyetDonHang(dhId,Donhang.DONHANGSVL_CLICK, userId, request);

					String search = getSearchQuery(request, obj);
					if(obj.getIsSearch())
					{
						String sumqr = getSumQuery(request, obj);
						obj.getSumBySearch(sumqr);
					}

					obj.setUserId(userId);
					obj.init(search);
					obj.setMsg(msg);

					session.setAttribute("userId", userId);
					session.setAttribute("obj", obj);

					response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHang.jsp");	   
				}
				
				/*
				 * else if(action.equals("dongbolai")) //DUYET PHAI DUNG DOPOST THI MOI GIU LAI
				 * DUOC CAC DK LOC { System.out.println("-- dongbolai"); String dhId =
				 * util.antiSQLInspection(request.getParameter("dhId"));
				 * System.out.println("dhId = "+ dhId); Donhang dh = new Donhang(""); String msg
				 * = dh.DuyetDonHang_chiDongBo(dhId,Donhang.DONHANGSVL_CLICK, userId, request);
				 * 
				 * String search = getSearchQuery(request, obj); if(obj.getIsSearch()) { String
				 * sumqr = getSumQuery(request, obj); obj.getSumBySearch(sumqr); }
				 * 
				 * obj.setUserId(userId); obj.init(search); obj.setMsg(msg);
				 * 
				 * session.setAttribute("userId", userId); session.setAttribute("obj", obj);
				 * 
				 * response.sendRedirect(request.getContextPath() + "/pages/Distributor/DonHang.jsp"); }
				 */
				
				else if (action.equals("toExcel")) 
				{
					ToExcel(response, obj,getSearchQuery_Excel(request,obj,0));
				} 
				else if (action.equals("toExcel1")) 
				{
					
					ToExcel( response,obj, getSearchQuery_Excel(request,obj,1));
				} 
			}
		}
	}
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IDonhangList obj,String query)throws Exception
	{

		try{		

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			
			com.aspose.cells.Cell cell;	


			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int location  = 0;
			int row = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i).replace("_isNum","").replace("_isNum2","")  );
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}


			row ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{					
					cell = cells.getCell(row,location + i-1 );

					if(rsmd.getColumnName(i).contains("_isNum") )
					{
						int format = 37;
						if(rsmd.getColumnName(i).contains("_isNum2"))	
							format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++row;
			}



			if(rs!=null)rs.close();		

		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}	
	}
	private void ToExcel(HttpServletResponse response, IDonhangList obj,String query) {
		{
	    	try
	    	{

	    		response.setContentType("application/xlsm");
	    		response.setHeader("Content-Disposition", "attachment; filename=Donhang.xls");
	    		OutputStream out1 = response.getOutputStream();
	    		
	    		ExportToExcel(out1, obj, query);
	    		obj.getDb().shutDown();
	    		return;
	    	}
	    	catch (Exception e) 
	    	{
				e.printStackTrace();
			}
	    }
	}
	
	private void ExportToExcel(OutputStream out,IDonhangList obj,String query )throws Exception
	{
		try{ 					


			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			TaoBaoCao(workbook, obj, query);			
			workbook.save(out);					

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}


	private String XoaDonHang_Trahaco(String dhId, String nppId, String lydoxoa,String userid) 
	{
		String msg = "";
		dbutils db = new dbutils();
		geso.dms.center.util.Utility util_kho = new geso.dms.center.util.Utility();

		try 
		{
			
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);

			int SoDong = 0;
			String query = "\n select count(*) as SoDong, B.HoaDon_FK " +
			"\n from HoaDon a inner join HoaDon_DDH b on b.HOADON_FK = a.pk_seq " +
			"\n where b.ddh_fk = '" + dhId + "' and a.TrangThai not in (3,5) " +
			"\n group by b.HoaDon_FK";
			System.out.println("Check SoDong: " + query);
			ResultSet rs = db.get(query);
			while (rs.next())
			{
				SoDong = rs.getInt("SoDong");
				System.out.println("HoaDon_FK: " + rs.getDouble("HoaDon_FK"));
			}
			rs.close();

			if (SoDong > 0)
			{
				msg = "Vui lòng huỷ hoá đơn trước khi xoá đơn hàng.";
				db.getConnection().rollback();
				return msg;
			}		 

			String quanlykho = "";
			query = "select isnull(quanlykho,0) quanlykho from nhaphanphoi where pk_Seq = (select npp_fk from donhang where pk_seq = " + dhId + ")";
			rs = db.get(query);
			while (rs.next()) {
				quanlykho = rs.getString("quanlykho");
			}
			rs.close();
			
			if (quanlykho != null && quanlykho.equals("1")) {
				//Revert tổng
				query = "\n SELECT KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG " +  
				"\n FROM " +
				"\n ( " +
				"\n     SELECT C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ AS SANPHAM_FK, SUM(A.SOLUONG) AS SOLUONG " +
				"\n     FROM DONHANG_SANPHAM A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ INNER JOIN DONHANG C ON A.DONHANG_FK = C.PK_SEQ " +  
				"\n     WHERE C.TRANGTHAI != 2 AND A.DONHANG_FK = " + dhId +  
				"\n     GROUP BY C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ, B.MA " +  
				"\n     UNION ALL " +
				"\n     SELECT E.NPP_FK, A.KHO_FK, E.KBH_FK, D.PK_SEQ AS SANPHAM_FK, SUM(A.SOLUONG) AS SOLUONG " +  
				"\n     FROM DONHANG_CTKM_TRAKM A INNER JOIN CTKHUYENMAI B ON A.CTKMID = B.PK_SEQ " +
				"\n     INNER JOIN SANPHAM D ON A.SPMA = D.MA INNER JOIN DONHANG E ON A.DONHANGID = E.PK_SEQ " +  
				"\n     WHERE E.TRANGTHAI != 2 AND A.SPMA IS NOT NULL AND A.DONHANGID = " + dhId +
				"\n     GROUP BY E.NPP_FK, A.KHO_FK, E.KBH_FK,  A.SPMA, D.PK_SEQ " +  
				"\n ) TX " +
				"\n GROUP BY KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK ";	
				ResultSet rskho = db.get(query);
				while (rskho.next()) {
					String khoid = rskho.getString("kho_fk");
					String KBHid = rskho.getString("KBH_FK");
					String NPpid = rskho.getString("NPP_FK");
					String spid = rskho.getString("SANPHAM_FK");
					double soluong = rskho.getDouble("SOLUONG");

					  String msg1 = util_kho.Update_NPP_Kho_Sp("", "Xoá đơn hàng DonhangSvl", db, khoid, spid, NPpid, KBHid, 0, soluong*(-1), soluong, 0);
					if (msg1.length() >0) {
						msg = "Lỗi khi xoá đơn hàng 1.";
						db.getConnection().rollback();
						return msg;
					} 
				}
				rskho.close();

				//Revert chi tiết
				query = "\n SELECT KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK, SOLO, NGAYHETHAN, NGAYNHAPKHO, SUM(SOLUONG) AS SOLUONG " +  
				"\n FROM " +
				"\n ( " +
				"\n     SELECT C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ AS SANPHAM_FK, " +
				"\n         A.SOLO,A.NGAYHETHAN, A.NGAYNHAPKHO, SUM(A.SOLUONG) AS SOLUONG " +
				"\n     FROM DONHANG_SANPHAM_CHITIET A " +
				"\n     INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ " + 
				"\n     INNER JOIN DONHANG C ON A.DONHANG_FK = C.PK_SEQ " +
				"\n     WHERE C.TRANGTHAI != 2 AND A.DONHANG_FK = " + dhId +
				"\n     GROUP BY C.NPP_FK, C.KHO_FK, C.KBH_FK, B.PK_SEQ, B.MA, A.SOLO, A.NGAYHETHAN, A.NGAYNHAPKHO " +
				"\n     UNION ALL " +
				"\n     SELECT E.NPP_FK, A.KHO_FK, E.KBH_FK, D.PK_SEQ AS SANPHAM_FK, " +
				"\n         A.SOLO, A.NGAYHETHAN, A.NGAYNHAPKHO, SUM(A.SOLUONG) AS SOLUONG " +  
				"\n     FROM DONHANG_CTKM_TRAKM_CHITIET A " +
				"\n     INNER JOIN CTKHUYENMAI B ON A.CTKM_FK = B.PK_SEQ " +
				"\n     INNER JOIN SANPHAM D ON A.SANPHAM_FK = D.PK_SEQ " +
				"\n     INNER JOIN DONHANG E ON A.DONHANG_FK = E.PK_SEQ " +  
				"\n     WHERE E.TRANGTHAI != 2 AND A.SANPHAM_FK IS NOT NULL AND A.DONHANG_FK = " + dhId + 
				"\n     GROUP BY E.NPP_FK, A.KHO_FK, E.KBH_FK, A.SANPHAM_FK, D.PK_SEQ, A.SOLO, A.NGAYHETHAN, A.NGAYNHAPKHO " +
				"\n ) TX " +
				"\n GROUP BY KHO_FK, KBH_FK, NPP_FK, SANPHAM_FK,SOLO, NGAYHETHAN, NGAYNHAPKHO ";
				rskho = db.get(query);
				while (rskho.next()) {
					String khoid = rskho.getString("kho_fk");
					String KBHid = rskho.getString("KBH_FK");
					String NPpid = rskho.getString("NPP_FK");
					String spid = rskho.getString("SANPHAM_FK");
					double soluong = rskho.getDouble("SOLUONG");
					String solo = rskho.getString("SOLO");
					String ngayhethan = rskho.getString("NGAYHETHAN");
					String NGAYNHAPKHO = rskho.getString("NGAYNHAPKHO");

					 String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet("", "Xoá đơn hàng chi tiết Donhangsvl", db, khoid, spid, NPpid, KBHid, solo, ngayhethan, NGAYNHAPKHO, 0, soluong*(-1), soluong, soluong, 0) ;
					if (msg1.length() >0) {
						msg = "Lỗi khi xoá đơn hàng 2.";
						db.getConnection().rollback();
						return msg;
					} 
				}
				rskho.close();
			}	

			query = "delete DUYETTRAKHUYENMAI_DONHANG where donhang_fk = '" + dhId + "'";
			if (!db.update(query))
			{
				msg = "Lỗi khi xoá đơn hàng 3.";
				db.getConnection().rollback();
				return msg;
			}

			query = "delete DUYETTRAKHUYENMAI_DUNO_DONHANG_DATRA where donhang_fk = '" + dhId + "' ";
			if (!db.update(query))
			{
				msg = "Lỗi khi xoá đơn hàng 4.";
				db.getConnection().rollback();
				return msg;
			}

			query = "\n update DONHANG set trangthai = '2', GHICHU = N'" + lydoxoa + "' " +
			"\n where pk_seq = '" + dhId + "' and TrangThai != 2 ";
			if (db.updateReturnInt(query)!=1)
			{
				msg = "Lỗi khi xoá đơn hàng 5.";
				db.getConnection().rollback();
				return msg;
			}

			query = "delete phieuxuatkho_donhang where donhang_fk = '" + dhId + "' ";
			if (!db.update(query))
			{
				msg = "Lỗi khi xoá đơn hàng 6.";
				db.getConnection().rollback();
				return msg;
			}

			if (quanlykho != null && quanlykho.equals("1")) {
				Utility util = new Utility();
				msg = util.Check_Kho_Tong_VS_KhoCT(nppId, db);
				if (msg.length() > 0)
				{
					db.getConnection().rollback();
					return msg;
				}
			}

			
			Object[] data = null;
			
			String val= "xóa đơn hàng " + dhId + " với lý do " + lydoxoa + "";
			data = dbutils.setObject(userid,val, userid,userid );
			
			query = "\n insert into thongbao(tieude, noidung, filename, nguoitao, ngaytao, nguoisua, ngaysua, " +
			"\n ngaybatdau, ngayketthuc, trangthai, loaithongbao, hethieuluc) " +
			"\n values(N'Thông báo đơn hàng xóa do User ' + (select ten from nhanvien where pk_seq = ?), " +
			"\n ?, 0, ?, convert(char(10),getdate(),126), " +
			"\n ?, convert(char(10),getdate(),126), convert(char(10),getdate(),126), convert(char(10),getdate(),126), '1', '5', '0') ";
			if (db.update_v2(query, data)  != 1)
			{
				db.getConnection().rollback();
				msg = "Lỗi gửi thông báo 1.";
				return msg;
			}
			
			String thongbao_fk = "";
			thongbao_fk =db.getPk_seq();
			String ddkd_fk = "";
			String ASM_FK = "";
			String GSBH_FK = "";
			query = "\n select DDKD_FK, gsbh_fk from donhang where pk_seq = " + dhId;
			ResultSet rsdh = db.get(query);
			while (rsdh.next())
			{
				ddkd_fk = rsdh.getString("DDKD_FK");
				GSBH_FK = rsdh.getString("gsbh_fk");
			}
			rsdh.close();

			query = "\n Insert THONGBAO_DDKD(thongbao_fk, ddkd_fk, trangthai) " +
			"\n select " + thongbao_fk + ", pk_seq, 0 " +
			"\n from DaiDienKinhDoanh where pk_seq in (" + ddkd_fk + ") ";
			if (!db.update(query))
			{
				db.getConnection().rollback();
				msg = "Lỗi gửi thông báo 2.";
				return msg;
			}

			query = "\n select distinct aa.ASM_FK " +
			"\n from ASM_KHUVUC aa " +  
			"\n inner join NHAPHANPHOI bb " + 
			"\n inner join nhapp_giamsatbh cc on bb.PK_SEQ = cc.npp_fk on bb.KHUVUC_FK = aa.KHUVUC_FK " + 
			"\n where cc.GSBH_FK = " + GSBH_FK;
			rsdh = db.get(query);
			while (rsdh.next())
			{
				ASM_FK = rsdh.getString("ASM_FK");
				query = "\n insert into THONGBAO_NHANVIEN(thongbao_fk, nhanvien_fk, trangthai) " +
				"\n select " + thongbao_fk + ", pk_seq, 0 " +
				"\n from NhanVien where ASM_FK in (" + ASM_FK + ") ";
				System.out.println("INSERT THONGBAO_NHANVIEN: " + query);
				if (!db.update(query))
				{
					db.getConnection().rollback();
					msg = "Lỗi gửi thông báo 3.";
					return msg;
				}
			}
			rsdh.close();

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			try 
			{
				db.getConnection().rollback();
			} 
			catch (Exception e1) {}
			e.printStackTrace();
			msg = "Exception xoá đơn hàng: " + e.getMessage();
			return msg;
		} 
		finally
		{
			if (db!=null) db.shutDown();
		}
		return msg;
	}

	private String getSearchQuery_Excel(HttpServletRequest request,IDonhangList obj, int value ) 
	{
		Utility util=new Utility();
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdTen"));
		if ( ddkdId == null)
			ddkdId = "";
		obj.setDdkdId(ddkdId);

		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";    	
		obj.setTrangthai(trangthai);
		System.out.println(" ---- TRANG THAI LA: " + trangthai);

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);

		String sodonhang = util.antiSQLInspection(request.getParameter("sodonhang"));
		if (sodonhang == null)
			sodonhang = "";    	
		obj.setSohoadon(sodonhang.trim());

		String khachhang = util.antiSQLInspection(request.getParameter("khachhang"));
		if (khachhang == null)
			khachhang = "";    	
		obj.setKhachhang(khachhang.trim());

		String mafast = util.antiSQLInspection(request.getParameter("mafast"));
		if(mafast==null)
			mafast="";
		obj.setMafast(mafast);
		
		String query = 
				"	select b.DONHANG_FK,a.NGAYNHAP,c.maFAST,c.TEN,c.DIACHI,d.MA as spMa,b.SOLUONG  "+
						"			from donhang a inner join donhang_sanpham b on b.donhang_fk=a.pk_Seq   "+
						"			inner join khachhang c on c.PK_SEQ=a.KHACHHANG_FK  "+
						"			inner join SANPHAM d on d.PK_SEQ=b.SANPHAM_FK  "+
						"		where a.npp_Fk='"+nppId+"'  ";
		// XUAT EXCEL 1
		if(value == 1) {
			 query = 
					"select \r\n" + 
					"c.PK_SEQ as Ma_KH \r\n" + 
					", '' as ong_ba	\r\n" + 
					",'' as ma_qs	\r\n" + 
					",'' as so_seri\r\n" + 
					",a.PK_SEQ as So_CT\r\n" + 
					",a.NGAYNHAP as NGAYCHUNGTU\r\n" + 
					",b.SOLUONG\r\n" + 
					",b.GIAMUA as Gia2\r\n" + 
					",b.THANHTIEN as Tien2\r\n" + 
					",'' as gia	\r\n" + 
					",'' as tien\r\n" + 
					",b.CHIETKHAU as tl_ck\r\n" + 
					",tien_ck  = b.THANHTIEN*b.CHIETKHAU \r\n" + 
					", '' as ma_thue	\r\n" + 
					", '' as thue_suat	\r\n" + 
					", '' as tien_thue	\r\n" + 
					", '' as ma_nx\r\n" + 
					",a.BM as ma_bm\r\n" + 
					", '' as tk_dt	\r\n" + 
					", '' as tk_vt	\r\n" + 
					", '' as tk_gv	\r\n" + 
					", '' as tk_ck	\r\n" + 
					", '' as tk_thue_co\r\n" + 
					",b.KHO_FK AS MaKho\r\n" + 
					",d.TKVT as Ma_VT\r\n" + 
					", '' as dien_gia	\r\n" + 
					", '' as han_tt	\r\n" + 
					", '' as ma_gd	\r\n" + 
					", '' as ma_vv_i	\r\n" + 
					", '' as ma_phi_i	\r\n" + 
					", '' as km_ck	\r\n" + 
					", '' as tk_km_i	\r\n" + 
					", '' as ma_dvcs\r\n" + 
					"from donhang a \r\n" + 
					"inner join donhang_sanpham b on b.donhang_fk=a.pk_Seq   \r\n" + 
					"inner join khachhang c on c.PK_SEQ=a.KHACHHANG_FK  \r\n" + 
					"inner join SANPHAM d on d.PK_SEQ=b.SANPHAM_FK "+
					"where a.npp_Fk='"+nppId+"' ";
		}

		if (ddkdId.length() > 0)
		{
			//query = query + " and a.ddkd_Fk = '" + ddkdId + "'";		
			query+=" and a.KHACHHANG_FK in (select KHACHHANG_FK from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on b.PK_SEQ=a.TBH_FK  where b.DDKD_FK="+ddkdId+")";

		}    

		if (trangthai.length() > 0)
		{
			if(trangthai.equals("4"))
			{
				query += " and a.trangthai in (1,3) and a.DAXUATHOADON  = 1 ";
			}
			else if (trangthai.equals("7"))
			{
				query += " and  a.DAIN  = 1 ";
			}
			else
			{
				if(trangthai.equals("1"))
				{
					query += " and a.trangthai ='1' and a.DAXUATHOADON  = 0 ";
				}
				else
				{
					query += " and a.trangthai = '" + trangthai + "'";
				}
			}
		}
		else
			query += " and a.trangthai != '2' ";

		if (tungay.length() > 0)
		{
			query = query + " and a.ngaynhap >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and a.ngaynhap <= '" + denngay + "'";	
		}
		if (sodonhang.length() > 0)
		{
			query = query + " and a.pk_seq like '%" + sodonhang + "%'";	
		}
		if (khachhang.length() > 0)
		{
			
			query = query + " and (c.smartid like '%"+ util.replaceAEIOU(khachhang) +"%' or c.pk_seq like (N'%" + util.replaceAEIOU(khachhang) + "%') or [dbo].[fuConvertToUnsign1](lower(c.ten)) like lower(N'%" + util.replaceAEIOU(khachhang) + "%') )";	
			// System.out.println("1/ bá»� dáº¥u: " + util.replaceAEIOU(khachhang));
		}
		if (mafast.length() > 0)
		{
			query = query + " and c.maFAST like '%" + mafast + "%'";	
		}
		System.out.println("Query cua ban: " + query);
		return query;

	}


	private String getSumQuery(HttpServletRequest request, IDonhangList obj) 
	{
		Utility util = new Utility();
		String nppId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String ddkdId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("ddkdTen"));
		if ( ddkdId == null)
			ddkdId = "";
		obj.setDdkdId(ddkdId);

		String trangthai = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";    	
		obj.setTrangthai(trangthai);

		String tungay = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);

		String sodonhang = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("sodonhang"));
		if (sodonhang == null)
			sodonhang = "";    	
		obj.setSohoadon(sodonhang.trim());

		String khachhang = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("khachhang"));
		if (khachhang == null)
			khachhang = "";    	
		obj.setKhachhang(khachhang.trim());

		String mafast = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("mafast"));
		if(mafast==null)
			mafast="";
		obj.setMafast(mafast);

		obj.setTtId(util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("ttId"))) == null ? "" : util.antiSQLInspection(request.getParameter("ttId")));

		obj.setQhId(util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("qhId"))) == null ? "" : util.antiSQLInspection(request.getParameter("qhId")));



		// Báº¯t buá»™c pháº£i dÃ¹ng hÃ m chá»— UTIL Ä‘á»ƒ cáº­p nháº­t tá»•ng giÃ¡ trá»‹ Ä‘Æ¡n hÃ ng
		// trÆ°á»›c, cÃ¢u trÃªn nÃ³ cháº¡y quÃ¡ lÃ¢u
		String query = "select sum(tongtienTRUOCCHIETKHAU) as TONGTRUOCCK, sum(tongtienCHIETKHAU) as TONGCK, sum(tonggiatri) as TONGSAUCK   " +
				"from DONHANG a inner join khachhang d on a.khachhang_fk = d.pk_seq      " +
				"where a.npp_fk = '" + nppId + "' " ;

		if (ddkdId.length() > 0)
		{
			//query = query + " and e.pk_seq = '" + ddkdId + "'";	
			query+=" and a.KHACHHANG_FK in (select KHACHHANG_FK from KHACHHANG_TUYENBH a inner join TUYENBANHANG b on b.PK_SEQ=a.TBH_FK  where b.DDKD_FK="+ddkdId+")";
		}    

		if (trangthai.length() > 0)
		{
			if(trangthai.equals("4"))
			{
				query += " and a.trangthai in (1,3) and a.DAXUATHOADON  = 1 ";
			}
			else if (trangthai.equals("7"))
			{
				query += " and  a.DAIN  = 1 ";
			}
			else
			{
				if(trangthai.equals("1"))
				{
					query += " and a.trangthai ='1' and a.DAXUATHOADON  = 0 ";
				}
				else
				{
					query += " and a.trangthai = '" + trangthai + "'";
				}
			}
		}
		else
			query += " and a.trangthai != '2' ";

		if (tungay.length() > 0)
		{
			query = query + " and a.ngaynhap >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and a.ngaynhap <= '" + denngay + "'";	
		}
		if (sodonhang.length() > 0)
		{
			query = query + " and a.pk_seq like '%" + sodonhang + "%'";	
		}
		if (khachhang.length() > 0)
		{

			query = query + " and d.timkiem like '%" + util.replaceAEIOU(khachhang) + "%' ";	
		}
		if (mafast.length() > 0)
		{
			query = query + " and d.maFAST like '%" + mafast + "%'";	
		}

		if(obj.getTtId().length()>0)
		{
			query+= " and a.khachhang_fk in (select pk_Seq from khachhang where tinhthanh_Fk='"+obj.getTtId()+"')";
		}

		if(obj.getQhId().length()>0)
		{
			query+= " and a.khachhang_fk in (select pk_Seq from khachhang where quanhuyen_fk='"+obj.getQhId()+"')";
		}

		//query += end;

		System.out.println("Query cua ban: " + query);
		return query;
	}

	private String getSearchQuery(HttpServletRequest request,IDonhangList obj) 
	{
		Utility util = new Utility();

		String nppId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("nppId"));
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String ddkdId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("ddkdTen"));
		if ( ddkdId == null)
			ddkdId = "";
		obj.setDdkdId(ddkdId);

		String trangthai = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("trangthai"));
		if (trangthai == null)
			trangthai = "";    	
		obj.setTrangthai(trangthai);
		System.out.println(" ---- TRANG THAI LA: " + trangthai);

		String tungay = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);

		String sodonhang = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("sodonhang"));
		if (sodonhang == null)
			sodonhang = "";    	
		obj.setSohoadon(sodonhang.trim());

		String khachhang = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("khachhang"));
		if (khachhang == null)
			khachhang = "";    	
		obj.setKhachhang(khachhang.trim());

		String mafast = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("mafast"));
		if(mafast==null)
			mafast="";
		obj.setMafast(mafast);
		
		String  quanhuyen=geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("qhId"));
		if(quanhuyen==null)
			quanhuyen="";
		obj.setQhId(quanhuyen);
		
		obj.setTtId(util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("ttId"))) == null ? "" : util.antiSQLInspection(request.getParameter("ttId")));

		if ( ddkdId.trim().length() <= 0 && trangthai.trim().length() <= 0 && tungay.trim().length() <= 0 && denngay.trim().length() <= 0 
				&& sodonhang.trim().length() <= 0 && khachhang.trim().length() <= 0 && mafast.trim().length() <= 0 && quanhuyen.trim().length() <= 0     )
			obj.setIsSearch(false);
		else
			obj.setIsSearch(true);

		/*String query =  "select isnull(a.ngaytaodh,a.ngaygio) as ngaygio,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
				"			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId,"+
				"			(	select top(1) aa.pk_seq from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ    \n " +
				"						inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ   \n " +
				"				where cc.KHACHHANG_FK = d.pk_seq  \n " +
				"			) as ddkdId, \n  " +
				"			(	select top(1) aa.TEN from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ    \n " +
				"						inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ   \n " +
				"				where cc.KHACHHANG_FK = d.pk_seq  \n " +
				"			) as ddkdTEN, \n  " +
				"			'' as nppTen, isnull(a.tonggiatriNK,a.tonggiatri) tonggiatri, d.THANHTOAN, a.VAT, " +
				"	   ' ' as nguoitao, 0 as exitPXK,     " +
				"STUFF   "+     
				"(  "+      
				"(  "+     
				"	select DISTINCT TOP 100 PERCENT ' , ' + RTRIM(ltrim(isnull(aa.pk_seq,''))) +' '+isnull(cast(aa.LOAIHOADON as nvarchar),'') "+ 
				"	from HOADON aa inner join HOADON_DDH bb on bb.HOADON_FK=aa.PK_SEQ    "+ 
				"	where aa.TRANGTHAI in (2,4) and bb.DDH_FK=A.PK_SEQ    "+ 
				"	ORDER BY ' , ' +  RTRIM(ltrim(isnull(aa.pk_seq,''))) +' '+isnull(cast(aa.LOAIHOADON as nvarchar),'')  "+  
				"	FOR XML PATH('')         "+ 
				" ), 1, 2, ''      "+ 
				") AS SoHoaDon  "+
				" from donhang a   " +
				"		inner join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
				"		left join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
				"where a.npp_fk = '" + nppId + "'  ";*/
		
		String query =  "select isnull(a.ghichu, '') as ghichu, a.synced,isnull(a.ngaytaodh,a.ngaygio) as ngaygio,a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
				"			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId,"+
				"			e.pk_seq as ddkdId, \n  " +
				"			(select top (1)'T'+CAST(tb.NGAYID as nvarchar(2))+ '_'+ dd.ten from tuyenbanhang tb inner join khachhang_tuyenbh khtb on tb.pk_seq=khtb.tbh_fk "+
				" inner join daidienkinhdoanh dd on dd.pk_seq = tb.ddkd_fk where khtb.khachhang_fk=d.pk_seq and dd.PK_SEQ = a.DDKD_FK ) as ddkdTen, \n  " +
				"			'' as nppTen, isnull(a.tonggiatriNK,a.tonggiatri) tonggiatri, d.THANHTOAN, a.VAT, " +
				"	   ' ' as nguoitao, 0 as exitPXK,     " +
				"	   isnull( a.sohoadon, '' ) AS SoHoaDon  "+
				" from donhang a   " +
				"		inner join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
				"		left join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
				"where a.npp_fk = '" + nppId + "'  ";
		
		if(nppId.equals("106218")||nppId.equals("106243")||nppId.equals("106233")|| nppId.equals("106244"))
		{
			query+=" and  d.QUANHUYEN_FK in ( select QUANHUYEN_FK from NhanVien_QuanHuyen where nhanvien_fk ='"+obj.getUserId()+"') ";
		}



		if (ddkdId.length() > 0)
		{
			query = query + " and e.pk_seq = '" + ddkdId + "'";		
		}    

		if (trangthai.length() > 0)
		{
			if(trangthai.equals("4")) 
			{
				query += " and ((a.trangthai in (1,3) and a.DAXUATHOADON  = 1) or a.trangthai = 4) ";
			}
			else if (trangthai.equals("7"))
			{
				query += " and  a.DAIN  = 1 ";
			}
			else
			{
				if(trangthai.equals("1"))
				{
					query += " and a.trangthai ='1' and a.DAXUATHOADON  = 0 ";
				}
				else
				{
					query += " and a.trangthai = '" + trangthai + "'";
				}
			}
		}
		else
			query += " and a.trangthai != '2' ";

		if (tungay.length() > 0)
		{
			query = query + " and a.ngaynhap >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and a.ngaynhap <= '" + denngay + "'";	
		}
		if (sodonhang.length() > 0)
		{
			query = query + " and a.pk_seq like '%" + sodonhang + "%'";	
		}
		if (khachhang.length() > 0)
		{
			//query = query + " and (d.smartid like '%"+ util.replaceAEIOU(khachhang) +"%' or d.pk_seq like (N'%" + util.replaceAEIOU(khachhang) + "%') or [dbo].[fuConvertToUnsign1](lower(d.ten)) like lower(N'%" + util.replaceAEIOU(khachhang) + "%') )";	

			query = query + " and d.timkiem like '%" + util.replaceAEIOU(khachhang) + "%' ";	
		}
		if (mafast.length() > 0)
		{
			query = query + " and d.maFAST like '%" + mafast + "%'";	
		}

		if(obj.getTtId().length()>0)
		{
			query+= " and a.khachhang_fk in (select pk_Seq from khachhang where tinhthanh_Fk='"+obj.getTtId()+"')";
		}

		if(obj.getQhId().length()>0)
		{
			query+= " and a.khachhang_fk in (select pk_Seq from khachhang where quanhuyen_fk='"+obj.getQhId()+"')";
		}

		System.out.println("Query cua ban: " + query);
		return query;
	}
	

	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");

		return arr[2] + "/" + arr[1] + "/" + arr[0];
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}

