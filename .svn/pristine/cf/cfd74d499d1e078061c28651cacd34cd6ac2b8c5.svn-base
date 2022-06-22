package geso.dms.center.servlets.NguongDoanhThu;



import geso.dms.center.beans.NguongDoanhthu.INguongDoanhthu;
import geso.dms.center.beans.NguongDoanhthu.imp.NguongDoanhthu;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class NguongDoanhThuSvl
 */
@WebServlet("/NguongDoanhThuSvl")
public class NguongDoanhThuSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NguongDoanhThuSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String querystring = request.getQueryString();
		Utility util = new Utility();
		String userId = util.getUserId(querystring);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.getId(querystring);
		
		INguongDoanhthu obj=new NguongDoanhthu();
		
		String action = request.getParameter("view");
		System.out.println("ss ------------------ action   la "+action);
			obj.init();
		session.setAttribute("userId", userId);
		String tenuser = gettenuser(userId);
		session.setAttribute("userTen", tenuser);
		session.setAttribute("obj", obj);
		session.setAttribute("check", "0");
		String nextJSP="";
		if(action==null)
			action="";
		
		if(action.equals("KHKM"))
		{
			 nextJSP = request.getContextPath() + "/pages/Center/KhachangDonhang.jsp";// default
		}
		else
		{
			 nextJSP = request.getContextPath() + "/pages/Center/NguongDoanhThu.jsp";// default
		}
		
		response.sendRedirect(nextJSP);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();
		String action = request.getParameter("view");
		String nppid = request.getParameter("nppId");
		String tungay = request.getParameter("Sdays");
		String denngay = request.getParameter("Edays");
		
		System.out.println("vao do post : "+action +"---" +nppid);
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			try 
			{
				importExcell(request,response,session,contentType,multi);
				return;
			} catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		
		response.setContentType("application/xls");
		response.setHeader("Content-Disposition", "attachment; filename=Chitieu_Template.xls");
		OutputStream out = response.getOutputStream();
		INguongDoanhthu nguongdoanhthu=new NguongDoanhthu();
		
		
		
		if(action==null)
			action="";
		
			try {
				if(action.equals("KHKM"))
				{
					ExportToExcel_KH(out,nguongdoanhthu,tungay,denngay,nppid);
				}
				else
				{
					ExportToExcel(out,nguongdoanhthu);
				}
			
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
	
	private void ExportToExcel(OutputStream out,INguongDoanhthu obj )throws Exception
	{
		try
		{ 			
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);

			String query = setQuery(obj);
			//String query ="";
			TaoBaoCao(workbook, obj, query, 0);
			workbook.save(out);			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	private void ExportToExcel_KH(OutputStream out,INguongDoanhthu obj,String tungay,String dengay,String nppid )throws Exception
	{
		try
		{ 			
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);

			String query = setQuery_KH(obj,tungay,dengay,nppid);
			//String query ="";
			TaoBaoCao_KH(workbook, obj, query, 0,tungay,dengay,nppid);
			workbook.save(out);			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	
	private void TaoBaoCao_KH(com.aspose.cells.Workbook workbook,INguongDoanhthu obj,String query,int sheetNum,String tungay,String denngay,String nppid )throws Exception
	{
		try
		{
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();
		
			com.aspose.cells.Cell cell = cells.getCell("B3");
			

			cell = cells.getCell(0, 0 );cell.setValue("BÁO CÁO KHÁCH HÀNG KHÔNG CÓ PHÁT SINH ĐƠN HÀNG");
			cell = cells.getCell(1, 0 );cell.setValue("TỪ NGÀY "+tungay +" ĐẾN NGÀY "+denngay);
			dbutils db = new dbutils();

			Hashtable<String,String> hashDienGiai = new Hashtable<String, String>();
			
			
			System.out.println("query export bc "+query);
		ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 7;
			int column = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				
				cell = cells.getCell(countRow, column + i );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			

			}
			countRow ++;
				rs = db.get(query);
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					Color c = Color.WHITE;
					cell = cells.getCell(countRow,column + i);
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 0);
					}
				}
				++countRow;
			}
			
			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}


		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,INguongDoanhthu obj,String query,int sheetNum )throws Exception
	{
		try
		{
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();
		
			com.aspose.cells.Cell cell = cells.getCell("B3");
			
			dbutils db = new dbutils();

			
			Hashtable<String,String> hashDienGiai = new Hashtable<String, String>();
			String sql ="select a.pk_Seq,b.TEN as [Tên nhà phân phối],a.maFAST as [Mã khách hàng] ,a.TEN as [Tên khách hàng],isnull(a.nguongdoanhthu,0) [Ngưỡng Doanh Thu] from KHACHHANG a inner join NHAPHANPHOI b on a.NPP_FK=b.PK_SEQ where a.TRANGTHAI=1";
	
			
			System.out.println("query export bc "+sql);
		ResultSet	rs = db.get(sql);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 7;
			int column = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				
				cell = cells.getCell(countRow, column + i );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			

			}
			countRow ++;
				rs = db.get(query);
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					Color c = Color.WHITE;
					cell = cells.getCell(countRow,column + i);
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, true, 0);
					}
				}
				++countRow;
			}
			
			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}


		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	
	public String setQuery (INguongDoanhthu obj )
	{
		
		
		String query ="select a.pk_seq,b.TEN as [Tên nhà phân phối],a.maFAST as [Mã khách hàng] ,a.TEN as [Tên khách hàng],isnull(a.nguongdoanhthu,0) [Ngưỡng Doanh Thu] from KHACHHANG a inner join NHAPHANPHOI b on a.NPP_FK=b.PK_SEQ where a.TRANGTHAI=1 order by b.ten ASC";
				System.out.println("query BC="+ query);
		return query;
	}
	public String setQuery_KH (INguongDoanhthu obj,String tungay,String denngay,String nppid )
	{
		String condition="";
		String condition1="";
		if(nppid.length()>0)
		{
			condition += " and a.npp_fk="+nppid;
			condition1+= " and aa.npp_fk="+nppid;
		}
		if(tungay.length() >0)
		{
			condition1 += " and  aa.NGAYNHAP >='"+tungay+"'";
		}
		if(denngay.length() >0)
		{
			condition1 += " and aa.NGAYNHAP <='"+denngay+"' "; 
		}
		
		String query =" select b.TEN [Tên nhà phân phối],a.maFAST [Mã SONET],a.TEN [Tên khách hàng] from KHACHHANG a inner join NHAPHANPHOI b "+
					  "  	on a.NPP_FK=b.PK_SEQ "+
					  "  	where   not exists (select 1 from DONHANG aa where aa.KHACHHANG_FK=a.PK_SEQ "+condition1+" ) "+condition+" ";
				System.out.println("query BC="+ query);
		return query;
	}
	private String gettenuser(String userId_)
	{
		dbutils db = new dbutils();
		String sql_getnam = "select ten from nhanvien where pk_seq=" + userId_;
		ResultSet rs_tenuser = db.get(sql_getnam);
		if (rs_tenuser != null)
		{
			try
			{
				while (rs_tenuser.next())
				{
					return rs_tenuser.getString("ten");
				}
			} catch (Exception er)
			{
				return "";
			}
		}
		return "";
	}

	

	public void importExcell(HttpServletRequest request, HttpServletResponse response,HttpSession session,String contentType,MultipartRequest multi ) throws ServletException, IOException, SQLException
	{

		INguongDoanhthu obj = new NguongDoanhthu();

		Enumeration files = multi.getFileNames();
		String filenameu = "";
		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			filenameu = multi.getFilesystemName(name);
			System.out.println("name  " + name);
			;
		}

		String filename = "C:\\java-tomcat\\data\\" + filenameu;

		if(filename.indexOf("xlsx")>=0)
		{

		}

		if (filename.length() > 0)
		{
			// doc file excel
			dbutils db =new dbutils();
			
			
			File file = new File(filename);
			System.out.println("filename  " + file);
			Workbook workbook;
			ResultSet rs = null;

			int indexRow = 8;

			int socotfixcung = 2;
			/*if(loainv.equals("1"))
				socotfixcung = 6;*/
			int socot =  socotfixcung;
			try
			{

				System.out.println(file);
				workbook = Workbook.getWorkbook(file);
				Sheet sheet = workbook.getSheet(0);
				Cell[] cells = sheet.getRow(indexRow);
				cells = sheet.getRow(7);
				boolean loi = false;
				String values = "";
				String valuesDetail = "";

				int cot=4;

				for (int i = indexRow ; i < sheet.getRows(); i++)
				{
					System.out.println("Vao Day: " + i);
					cells = sheet.getRow(i);
					if(cells.length > 0 && !loi)
					{

						String mafast = getStringValue(cells,1);
						String NguongDT=getStringValue(cells,5);
						String query="update khachhang set nguongdoanhthu="+NguongDT+" where pk_seq='"+mafast+"'  ";
						if(db.updateReturnInt(query)<=0)
						{
							session.setAttribute("userId", obj.getUserid());
							String tenuser = gettenuser( obj.getUserid());
							session.setAttribute("userTen", tenuser);
							session.setAttribute("obj", obj);
							session.setAttribute("check", "0");
							obj.setMessage("lỗi upload hạn mức  "+mafast);
							String nextJSP = request.getContextPath() + "/pages/Center/NguongDoanhThu.jsp";// default
							response.sendRedirect(nextJSP);return;
							
						}
						System.out.println("manv la "+mafast+"---"+NguongDT);
						
					}

				}
				session.setAttribute("userId", obj.getUserid());
				String tenuser = gettenuser( obj.getUserid());
				session.setAttribute("userTen", tenuser);
				session.setAttribute("obj", obj);
				session.setAttribute("check", "0");
				obj.setMessage("upload thành công  ");
				String nextJSP = request.getContextPath() + "/pages/Center/NguongDoanhThu.jsp";// default
				response.sendRedirect(nextJSP);
				return;
				
			} catch (Exception er)
			{
				er.printStackTrace();
				obj.setMessage("Thong bao loi : " + er.toString());
				System.out.println(er.toString());

			}

		}


	}
	
	public String getStringValue(Cell[] cells,int vitri)
	{
		try
		{
			return cells[vitri].getContents().toString().replace(",", "");
		}
		catch (Exception e) {
			return "";
		}
	}

	
}
