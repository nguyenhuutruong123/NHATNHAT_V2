package geso.dms.center.servlets.tinhthanh;

import geso.dms.center.beans.tinhthanh.ITinhthanh;
import geso.dms.center.beans.tinhthanh.ITinhthanhList;
import geso.dms.center.beans.tinhthanh.imp.Tinhthanh;
import geso.dms.center.beans.tinhthanh.imp.TinhthanhList;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

/**
 * Servlet implementation class TinhthanhSvl
 */
public class TinhthanhSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TinhthanhSvl() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	    Utility util = new Utility();
	    
	    
	    String delete = util.antiSQLInspection(request.getParameter("delete"));
	    if (delete == null) {
	    	delete = "";
	    }
	    ITinhthanhList tinhList = new TinhthanhList();
	    tinhList.init();
	    
	    if(delete.trim().length() >0)
	    {
	    	 Delete( tinhList,delete) ;
	    }
	    
	   
	    
	    session.setAttribute("tinhList", tinhList);
		response.sendRedirect(request.getContextPath() + "/pages/Center/Tinhthanh.jsp");
	}
	
	public void Delete(ITinhthanhList obj,String id) {
		
		try
		{
			obj.getDb().getConnection().setAutoCommit(false);
			
			
			String query = " select count(*)sd from khachhang where tinhthanh_fk =  "  + id;
			ResultSet rs= obj.getDb().get(query);
			rs.next();
			int cokh = rs.getInt("sd");
			if(cokh > 0)
			{
				obj.setMsg("Tỉnh thành đã dùng cho khách hàng");			
				obj.getDb().getConnection().rollback();
				return ;
			}
			
			 query = " select count(*)sd from nhaphanphoi where tinhthanh_fk =  "  + id;
			rs= obj.getDb().get(query);
				rs.next();
			cokh = rs.getInt("sd");
				if(cokh > 0)
				{
					obj.setMsg("Tỉnh thành đã dùng cho NPP");			
					obj.getDb().getConnection().rollback();
					return ;
				}
			
			query =  "\n delete tinhthanh where pk_seq = " + id ;
			
			if (obj.getDb().updateReturnInt(query)<= 0 ) 
			{
				obj.setMsg("Tỉnh thành  không thể xóa!");			
				obj.getDb().getConnection().rollback();
				return ;
			}
			
			
			obj.getDb().getConnection().commit();
			obj.getDb().getConnection().setAutoCommit(true);
			obj.setMsg("Xóa thành công!");		
			return;
		}
		catch(Exception e)
		{
			Utility.rollback_throw_exception(obj.getDb());
			obj.setMsg("Exception:" + e.getMessage());
			return ;
			
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
	    Utility util = new Utility();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    ITinhthanhList tinhList = new TinhthanhList();
	    
	    String ten = util.antiSQLInspection(request.getParameter("ten"));
	    if (ten == null) {
	    	ten = "";
	    }
	    tinhList.setTen(ten);
	    
	    String ma = util.antiSQLInspection(request.getParameter("ma"));
	    if (ma == null) {
	    	ma = "";
	    }
	    tinhList.setMa(ma);
	    
	    String vungId = util.antiSQLInspection(request.getParameter("vungId"));
	    if (vungId == null) {
	    	vungId = "";
	    }
	    tinhList.setVung(vungId);
	    
	    String action = util.antiSQLInspection(request.getParameter("action"));
	    
	    
	    if (action.equals("excel")) {
	    	tinhList.init();
		    
			request.setCharacterEncoding("utf-8");
			response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=BCTinhThanh.xlsm");
			OutputStream out1 = response.getOutputStream();
			try {
				ExportToExcel( out1, tinhList );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			tinhList.getDb().shutDown();
			return;
			
	    }
	    if (action.equals("submit")) {
	    	tinhList.init();
		    
		    session.setAttribute("tinhList", tinhList);
			response.sendRedirect(request.getContextPath() + "/pages/Center/Tinhthanh.jsp");
	    }
	    if (action.equals("new")) {
	    	ITinhthanh tinh = new Tinhthanh(userId);
	    	tinh.createRs();
		    session.setAttribute("tinh", tinh);
			response.sendRedirect(request.getContextPath() + "/pages/Center/TinhthanhUpdate.jsp");
	    }
	}
	
	private void ExportToExcel(OutputStream out,ITinhthanhList obj )throws Exception
	{
		try{ 					
			
		
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook, obj);			
			workbook.save(out);					

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,ITinhthanhList obj)throws Exception
	{
		
		try{		

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Danh mục tỉnh thành ");
			cell = cells.getCell("A2");
		
			
			ResultSet rs = obj.getTinhthanhRs();
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			
			int location  = 0;
			int row = 10;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i).replace("(%)",""));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{					
					cell = cells.getCell(row,location + i-1 );
					
					cell.setValue(rs.getString(i));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
				}
				
				++row;
			}
			
			if(rs!=null)rs.close();		
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}	
	}
	

}
