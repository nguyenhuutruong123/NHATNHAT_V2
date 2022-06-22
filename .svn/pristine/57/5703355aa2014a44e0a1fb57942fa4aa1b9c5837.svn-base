package geso.dms.center.servlets.phuongxa;

import geso.dms.center.beans.phuongxa.*;
import geso.dms.center.beans.phuongxa.imp.Phuongxa;
import geso.dms.center.beans.phuongxa.imp.PhuongxaList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.khachhang.IKhachhangList;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;


public class PhuongxaSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private int items = 50;
	private int splittings = 20;
	
    public PhuongxaSvl()
    {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		IPhuongxaList obj;
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
	    obj = new PhuongxaList();
	    Utility util = new Utility();
	  	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	  
	    
	    String kvId = util.getId(querystring);
	    String param = "";
	    if (action.equals("delete")) {	   		  	    	
	    	obj.setMsg(Delete(kvId));
	    
	    }
	    settingPage(obj);
	    obj.setUserId(userId);
	    obj.init("");
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/PhuongXa.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		IPhuongxaList obj = new PhuongxaList();
		Utility util = new Utility();
		
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
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.antiSQLInspection(request.getParameter("action"));
	    if (action == null) {
	    	action = "";
	    }
	    
	    String ttId = util.antiSQLInspection(request.getParameter("tinhthanh"));
		if (ttId == null)
			ttId = "";
		obj.setTinhthanhId(ttId);

		String qhId = util.antiSQLInspection(request.getParameter("quanhuyen"));
		if (qhId == null)
			qhId = "";
		obj.setQuanhuyenId(qhId);
		
		String tenphuongxa = util.antiSQLInspection(request.getParameter("tenphuongxa"));
		if (tenphuongxa == null)
			tenphuongxa = "";
		obj.setTenPhuong(tenphuongxa);
		
		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
		if (trangthai == null)
			trangthai = "";    	

		if (trangthai.equals("2"))
			trangthai = "";
		obj.setTrangthai(trangthai);
	    settingPage(obj);
	    
	    if (action.equals("excel")) {
	    	try
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=PhuongXa.xls");
				OutputStream out1 = response.getOutputStream();
				String query = setQuery(obj);
				ExportToExcel( out1, obj, query );
				obj.getDb().shutDown();
				return;
			}
			catch (Exception ex)
			{
				obj.setMsg("Khong the tao pivot.");
				ex.printStackTrace();;
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			return;	
	    }	 
	    else
	    if (action.equals("new")) {
	    	IPhuongxa kvBean = (IPhuongxa) new Phuongxa("");
	    	kvBean.setUserId(userId);
	    	session.setAttribute("kvBean", kvBean);    		
    		String nextJSP = request.getContextPath() + "/pages/Center/PhuongXaNew.jsp";
    		response.sendRedirect(nextJSP);    		
	    }	    
	    else if (action.equals("search")) {
	    	/*String search = getSearchQuery(request, obj);	    	
	    	obj.init(search);
			obj.setUserId(userId);			
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		session.setAttribute("abc", search);	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/PhuongXa.jsp");*/
    		
    		obj.setUserId(userId);
			String search = getSearchQuery(request, obj);
			obj.init(search);
			session.setAttribute("obj", obj);  	
			session.setAttribute("userId", userId);
			session.setAttribute("abc", search);
			response.sendRedirect(request.getContextPath() + "/pages/Center/PhuongXa.jsp");
	    }
	   
	    else if (action.equals("view") || action.equals("next") || action.equals("prev")) {

			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.setUserId(userId);
			String search = getSearchQuery(request, obj);
			obj.init(search);
			////System.out.println("Phan Trang: "+request.getParameter("nxtApprSplitting"));   		
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			session.setAttribute("obj", obj);
			response.sendRedirect(request.getContextPath() + "/pages/Center/PhuongXa.jsp");
		}
	    
	    else {	    
	    	String search = "";
	    	obj.init(search);
			obj.setUserId(userId);			
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		session.setAttribute("abc", search);	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/PhuongXa.jsp");	   	
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request,IPhuongxaList obj)
	{
		obj.getDataSearch().clear();
		Utility util = new Utility();

		String ttId = util.antiSQLInspection(request.getParameter("tinhthanh"));
		if (ttId == null)
			ttId = "";
		obj.setTinhthanhId(ttId);

		String qhId = util.antiSQLInspection(request.getParameter("quanhuyen"));
		if (qhId == null)
			qhId = "";
		obj.setQuanhuyenId(qhId);

		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
		if (trangthai == null)
			trangthai = "";  
		
		String tenphuongxa = util.antiSQLInspection(request.getParameter("tenphuongxa"));   	
		if (tenphuongxa == null)
			tenphuongxa = "";  
		obj.setTenPhuong(tenphuongxa);

		if (trangthai.equals("2"))
			trangthai = "";
		obj.setTrangthai(trangthai);

		String query = "\n SELECT TT.pk_Seq AS MATENTT, QH.pk_seq AS MATENQH, PX.pk_Seq, PX.Ten AS TENPX, TT.TEN AS TENTT, QH.TEN AS TENQH, NT.TEN AS NGUOITAO, " +
		"\n NS.TEN AS NGUOISUA, PX.NGAYTAO, PX.NGAYSUA, PX.TRANGTHAI " + 
		"\n FROM PHUONGXA PX " + 
		"\n INNER JOIN TINHTHANH TT ON TT.PK_SEQ = PX.TinhThanh_FK " + 
		"\n INNER JOIN QUANHUYEN QH ON QH.PK_SEQ = PX.QuanHuyen_FK " + 
		"\n INNER JOIN NHANVIEN NT ON NT.PK_SEQ = PX.NGUOITAO " + 
		"\n INNER JOIN NHANVIEN NS ON NS.PK_SEQ = PX.NGUOISUA " + 
		"\n where 1 = 1 ";

		if (ttId.length() > 0) {
			query += " and tt.pk_seq = '"+ ttId +"' ";
		}

		if (qhId.length() > 0) {
			query += " and qh.pk_seq = '"+ qhId +"' ";
		}

		if(trangthai.length() > 0) {
			query += " and px.trangthai = '"+ trangthai +"' ";	
		}
		
		if(tenphuongxa.length() > 0) {
			query += " and px.diengiai like '%"+ util.replaceAEIOU(tenphuongxa) +"%' ";	
		}
		return query;
	}	
	
	boolean kiemtra(String sql)
	{
		dbutils db = new dbutils();
		ResultSet rs = db.get(sql);
		try {
			while(rs.next())
			{ 
				if(rs.getString("num").equals("0"))
				return true;
			}
		} catch(Exception e) {	
			e.printStackTrace();
		}
		
		return false;
	}

	private String Delete(String id)
	{	
		IPhuongxaList obj = new PhuongxaList();
		dbutils db = new dbutils();
		try 
		{
			db.getConnection().setAutoCommit(false);
			String sql = "SELECT COUNT(PHUONGXA) AS NUM FROM KHACHHANG WHERE PHUONGXA = '"+ id +"' ";
			ResultSet rs = db.get(sql);
			int num = 0;
			rs.next();
			num = rs.getInt("NUM");
			rs.close();
			System.out.println("num : "+ num);
			if(num != 0)
			{
				db.getConnection().rollback();
				obj.setMsg("Đã có khách hàng chọn phường xã này, không được xóa phường xã!");	
				return obj.getMsg();
			}
			else
			{
				if(!db.update("DELETE FROM PHUONGXA WHERE PK_SEQ = '" + id + "'"))
				{
					db.getConnection().rollback();
					return obj.getMsg();
					
				}
				else { db.getConnection().commit(); }
			}
			
			/*
			 * if(kiemtra(sql)) { if(!db.update("DELETE FROM PHUONGXA WHERE PK_SEQ = '" + id
			 * + "'")) { db.getConnection().commit();
			 * db.getConnection().setAutoCommit(true); return obj.getMsg();
			 * 
			 * } } else{ db.getConnection().rollback();
			 * db.getConnection().setAutoCommit(true); obj.
			 * setMsg("Thông tin phường xã này đã được khai báo trong dữ liệu khách hàng. Không thể xóa !"
			 * ); return obj.getMsg(); }
			 */
			
		} catch (SQLException e) { e.printStackTrace(); }
		finally { try { db.getConnection().setAutoCommit(true); if(db != null) { db.shutDown(); } } catch (SQLException e) { e.printStackTrace(); } }
		return "";
	}
	
	public String setQuery(IPhuongxaList obj)
	{
		
		String query= 
		
			"\n select ROW_NUMBER() OVER(PARTITION BY 1 ORDER BY tt.TEN,qh.TEN, px.ten ) as STT " +
			"\n , px.pk_seq [Mã phường xã] , px.ten	[Phường xã], qh.pk_seq	[Mã quận/ huyện] , qh.ten	[Quận/ Huyện], tt.pk_seq	[Mã tỉnh], tt.ten	[Tỉnh]  " +
			"\n from PhuongXa px  " +
			"\n inner join QUANHUYEN qh on qh.PK_SEQ = px.quanhuyen_fk  " +
			"\n inner join TINHTHANH tt on tt.PK_SEQ = px.TINHTHANH_FK " +
			"\n where 1=1 " ;
		
		if(obj.getTrangthai()!= null && obj.getTrangthai().length() >0)
		{
			query += " and px.trangthai = " + obj.getTrangthai();
		}
		if(obj.getTinhthanhId()!= null && obj.getTinhthanhId().length() >0)
		{
			query += " and px.TINHTHANH_FK = " + obj.getTinhthanhId();
		}
		if(obj.getQuanhuyenId()!= null && obj.getQuanhuyenId().length() >0)
		{
			query += " and px.quanhuyen_fk = " + obj.getQuanhuyenId();
		}
		
		////System.out.println("barcode : "+query);
		return query;
	}
	private void ExportToExcel(OutputStream out,IPhuongxaList obj,String query )throws Exception
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

	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IPhuongxaList obj,String query)throws Exception
	{

		try{		

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			com.aspose.cells.Cell cell = cells.getCell("A1");;	

			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Danh sách phường xã");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + Utility.getNgayHienTai() );


			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int location  = 0;
			int row = 10;
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
	
	private void settingPage(IPhuongxaList obj) {
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
}
