package geso.dms.distributor.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.servlets.reports.AjaxDistributionTT;
import geso.dms.distributor.db.sql.dbutils;

import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


@WebServlet("/BcDoanhThuSanPhamSvl")
public class BcDoanhThuSanPhamSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BcDoanhThuSanPhamSvl() {
        super();
       
    }

  	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{
  		IStockintransit obj;
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
  		
  		String querystring = request.getQueryString();
  		String userId = util.getUserId(querystring);
  		
  		if (userId.length() == 0)
  			userId = util.antiSQLInspection(request.getParameter("userId"));
  		
  		String loaihoadon = request.getParameter("loaihoadon");
  		if (loaihoadon == null)
  			loaihoadon = "0";
  		String view= request.getParameter("View");
  		if (view == null)
  			view = "";
  		
  		obj = new Stockintransit();
  		obj.setuserId(userId);
  		obj.setView(view);
  		String nextJSP = "";
  		obj.init_user();  		
  		
  		nextJSP = request.getContextPath() + "/pages/Distributor/BcDoanhThuSanPham.jsp";
  		session.setAttribute("obj", obj);
  		response.sendRedirect(nextJSP);  		
  	}
  	
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  	{

  		request.setCharacterEncoding("UTF-8");
  		response.setCharacterEncoding("UTF-8");
  		response.setContentType("text/html; charset=UTF-8");

  		geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
  		String userId = util.antiSQLInspection(request.getParameter("userId")); 
  		HttpSession session = request.getSession();
  		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
  		OutputStream out = response.getOutputStream();

  		String action = request.getParameter("action");
  		if (action == null)
  			action = "";

  		IStockintransit obj = new Stockintransit();
  		obj.setuserId(userId);
  		obj.init_user();  		
  		String view= request.getParameter("View");
  		if (view == null)
  			view = "";
  		obj.setView(view);

  		
  		String tungay =  util.antiSQLInspection(request.getParameter("tungay")==null?"": request.getParameter("tungay"));
  		obj.settungay(tungay);

  		String denngay = util.antiSQLInspection(request.getParameter("denngay")==null?"": request.getParameter("denngay"));
  		obj.setdenngay(denngay);

  		String vungId = util.antiSQLInspection(request.getParameter("vungId")==null?"": request.getParameter("vungId"));
  		obj.setvungId(vungId);

  		String kbhId = util.antiSQLInspection(request.getParameter("kenhId")==null?"": request.getParameter("kenhId"));
  		obj.setkenhId(kbhId);    

  		String tpId = util.antiSQLInspection(request.getParameter("tpId"));
  		if (tpId == null)
  			tpId = "";
  		obj.setTinhthanhid(tpId);

  		String qhId = util.antiSQLInspection(request.getParameter("qhId"));
  		if (qhId == null)
  			qhId = "";
  		obj.setQhId(qhId);

  		String phuongxaId = util.antiSQLInspection(request.getParameter("phuongxaId"));
  		if (phuongxaId == null)
  			phuongxaId = "";
  		System.out.println("phuongxa : "+ phuongxaId);
  		obj.setPhuongxa(phuongxaId);

  		String ttId = util.antiSQLInspection(request.getParameter("ttId")==null?"": request.getParameter("ttId"));
  		obj.setTtId(ttId);   

  		String nhomId = util.antiSQLInspection(request.getParameter("nhomId")==null?"": request.getParameter("nhomId"));
  		obj.setNhomhangid(nhomId);

  		String khId = util.antiSQLInspection(request.getParameter("khid")==null?"": request.getParameter("khid"));
  		obj.setkhId(khId);
  		System.out.println("khach hnag id "+khId);

  		String ddkdId = util.antiSQLInspection( request.getParameter("ddkdId")==null?"": request.getParameter("ddkdId"));
  		obj.setDdkd(ddkdId);

  		String spId = util.antiSQLInspection(request.getParameter("spId")==null?"": request.getParameter("spId"));
  		obj.setSpId(spId);

  		String nppId = util.antiSQLInspection(request.getParameter("nppId")==null?"": request.getParameter("nppId"));
  		obj.setNppId(nppId);

  		System.out.println("nha phan phoi"+nppId);

  		String loaihoadon = util.antiSQLInspection(request.getParameter("loaidonhang")==null?"": request.getParameter("loaidonhang"));
  		obj.setLoaihoadon(loaihoadon);

  		//obj.setMucCN_DT(util.antiSQLInspection(request.getParameter("cndt")) != null ? util.antiSQLInspection(request.getParameter("cndt")) : "");
  		obj.setMucCN_DT("0");

  		obj.setMuc_KhachHang(util.antiSQLInspection(request.getParameter("kh")) != null ? util.antiSQLInspection(request.getParameter("kh")) : "");

  		obj.setLaynk(util.antiSQLInspection(request.getParameter("klaynhomnk")) != null ? util.antiSQLInspection(request.getParameter("klaynhomnk")) : "0");

  		obj.setLaytheo(util.antiSQLInspection(request.getParameter("laytheo")) != null ? util.antiSQLInspection(request.getParameter("laytheo")) : "0");
  		
  		
  		obj.set_Action(action);

  		if (action.equals("excel"))
  		{
  			try {
  				request.setCharacterEncoding("utf-8");
  				response.setContentType("application/xlsm");
  				response.setHeader("Content-Disposition", "attachment; filename=BCDoanhsotheoSanPham.xlsm");
  				String query = AjaxDistributionTT.queryDoanhSoTheoSP(request, util,AjaxDistributionTT.QUERY_EXCEL_ACTION,0,0) ;
  				ExportToExcel( out, obj, query );
  				obj.getDb().shutDown();
  				return;
  			}
  			catch (Exception ex) {
  				ex.printStackTrace();
  				System.out.println("Error Here : "+ex.toString());
  				request.getSession().setAttribute("errors", ex.getMessage());
  			}
  			
  			session.setAttribute("obj", obj);
  			session.setAttribute("userId", userId);
  			String nextJSP = "";
  			nextJSP = request.getContextPath() + "/pages/Distributor/BcDoanhThuSanPham.jsp";
  			response.sendRedirect(nextJSP); 
  		}
  		

  		else if (action.equals("search"))
  		{	
  			obj.setuserId(userId);
  			session.setAttribute("obj", obj);
  			session.setAttribute("userId", userId);
  			obj.init_user();
  			String nextJSP = "";
  			nextJSP = request.getContextPath() + "/pages/Distributor/BcDoanhThuSanPham.jsp.jsp";
  			response.sendRedirect(nextJSP); 
  		}
  		else
  		{
  			System.out.println("nhom nhap khau"+obj.getLaynk());
  			session.setAttribute("obj", obj);
  			String nextJSP = "";
  			nextJSP = request.getContextPath() + "/pages/Distributor/BcDoanhThuSanPham.jsp";
  			obj.init_user();
  			response.sendRedirect(nextJSP);  
  		}

  	}
  	
  private void ExportToExcel(OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 					
			
			FileInputStream fstream = null;
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(workbook, obj, query);			
			workbook.save(out);					

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		
		try{		
			redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis();
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo doanh số sản phẩm ");
			cell = cells.getCell("A2");
			 
			
			ResultSet rs = obj.getDb().get(query);
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
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 37;
							if(rsmd.getColumnName(i).contains("%")|| rsmd.getColumnName(i).contains("(%)") )	
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
			geso.dms.center.util.Utility.JedisClose(jedis);
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}	
		
	}
	
}
