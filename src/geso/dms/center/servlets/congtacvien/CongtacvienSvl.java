package geso.dms.center.servlets.congtacvien;

import geso.dms.center.beans.congtacvien.*;
import geso.dms.center.beans.congtacvien.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.oreilly.servlet.MultipartRequest;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import jxl.Sheet;

public class CongtacvienSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "c:\\upload\\excel\\";
   
    public CongtacvienSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ICongtacvienList obj = new CongtacvienList();
	    
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	       	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    PrintWriter out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);

	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    
	    String ddkdId = util.getId(querystring);
	    
	   // System.out.println("Action nek :"+action);
	    if (action.equals("delete"))
	    { 
	    	Delete(ddkdId,obj);
	    	//out.print(ddkdId);
	    }
	    obj.setRequestObj(request);
	    obj.setUserId(userId);
	    //System.out.println("user iad 1: "+userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/CongTacVien.jsp";
		response.sendRedirect(nextJSP);
	}

	String query1="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ICongtacvienList obj = new CongtacvienList();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	   
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    String action = request.getParameter("action");
	      //----
	    obj.setUserId(userId);
	    obj.setRequestObj(request);
	    String search = "";
	    String nextJSP = "";
	    String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, UPLOAD_DIRECTORY, 20000000, "UTF-8");
			userId = util.antiSQLInspection(multi.getParameter("userId"));
			session.setAttribute("userId", userId);
			obj.setUserId(userId);
			
			Enumeration files = multi.getFileNames();
			String filename = "";
			System.out.println("__userId" + userId);
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				System.out.println("File  " + UPLOAD_DIRECTORY + filename);
			}
			if (filename != null && filename.length() > 0)
				obj.setMsg(this.readExcel(UPLOAD_DIRECTORY + filename, obj));

			search = getSearchQuery(request,obj);
			obj.setUserId(userId);
			obj.init(search);
    		session.setAttribute("abc", search);
	    		
    		nextJSP = request.getContextPath() + "/pages/Center/CongTacVien.jsp";
    		obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
    		session.setAttribute("obj", obj);  	
			session.setAttribute("userId", userId);
	    		
			response.sendRedirect(nextJSP); 
		} 
		else
		{
		    if (action.equals("new"))
		    {
		    	// Empty Bean for distributor
		    	ICongtacvien ddkdBean = (ICongtacvien) new Congtacvien("");
		    	ddkdBean.setUserId(userId);
		    	// Save Data into session
		    	session.setAttribute("ddkdBean", ddkdBean);
	    		
	    		nextJSP = request.getContextPath() + "/pages/Center/CongTacVienNew.jsp";  		
		    }
		    else if (action.equals("search")){	    
		    	search = getSearchQuery(request,obj);
				obj.setUserId(userId);
				
	    		session.setAttribute("abc", search);
		    		
	    		nextJSP = request.getContextPath() + "/pages/Center/CongTacVien.jsp";
		    }
		    else if (action.equals("excel"))
			{
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "Attachment; filename=CongTacVien.xls");
				OutputStream out = response.getOutputStream();
				try
				{
					obj.setUserId(userId);
					CreatePivotTable(out, response, request, obj); 
					
				} catch (Exception ex)
				{
					obj.setMsg(ex.getMessage());
					request.getSession().setAttribute("errors", ex.getMessage());
				}
			}
		    else{
			    
			    //phantrang
			    
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
	
		    	//------------------------
		    	
		    	search = getSearchQuery(request, obj);
	
		    	nextJSP = request.getContextPath() + "/pages/Center/CongTacVien.jsp";
		    }
		    
		    obj.init(search);
		   
	    	session.setAttribute("obj", obj);  	
			session.setAttribute("userId", userId);
	    		
			response.sendRedirect(nextJSP); 
		}
	}
	private void CreatePivotTable(OutputStream out, HttpServletResponse response, HttpServletRequest request, ICongtacvienList obj) throws Exception
	{
		try
		{
			dbutils db = new dbutils();
			/*String strfstream = getServletContext().getInitParameter("path") + "\\CongTacVien.xlsm";
			FileInputStream fstream = new FileInputStream(strfstream);*/
			Workbook workbook = new Workbook();
			//workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			Worksheets worksheets = workbook.getWorksheets();
	  		Worksheet worksheet = worksheets.getSheet("sheet1");
	  		
	  		Cells cells = worksheet.getCells();
			   
	  		Cell cell = cells.getCell("P1");
			Style style1=cell.getStyle();
			
			this.TaoBaoCao(db,worksheet, obj,"CỘNG TÁC VIÊN",style1);
			
			workbook.save(out);
			//fstream.close();
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	private void TaoBaoCao(dbutils db, Worksheet worksheet, ICongtacvienList obj, String diengiai, Style style12) 
	{
		try{  
			Utility util = new Utility();
			Cells cells = worksheet.getCells();
			for(int i=0;i<30;i++){
				cells.setColumnWidth(i, 10.0f);   
			}
			
			cells.setRowHeight(0, 50.0f);
			Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 14, diengiai);
		
			worksheet.setGridlinesVisible(false);
			ResultSet rs;
			String query = 
				"select a.MA, a.TEN, a.DIACHI, a.DIENTHOAI, a.GIOITINH, a.CHUYENKHOA, a.CHUCVU, a.LICHKHAM, b.TEN MADIABAN, b.DIENGIAI TENDIABAN, \n"+
				"c.maFAST MANVBH, c.TEN TENNVBH, e.maFAST MAKH, e.TEN TENKH  \n"+
				"from CONGTACVIEN a  \n"+
				"left join DIABAN b on a.DIABAN_FK = b.PK_SEQ left join DAIDIENKINHDOANH c on a.NVBH_FK = c.PK_SEQ \n"+
				"left join CONGTACVIEN_KHACHHANG d on a.PK_SEQ = d.CTV_FK inner join KHACHHANG e on d.KH_FK = e.PK_SEQ";
	   
			System.out.println("query " + query);
			rs = db.get(query);
	   
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
	   
			int countRow = 3;

			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow,i-1 );
				Style s = cell.getStyle();
				s.setTextWrapped(true);
				s.setHAlignment(TextAlignmentType.CENTER);
				s.setVAlignment(TextAlignmentType.JUSTIFY);
				cell.setStyle(s);
				ReportAPI.setCellBackground(cell, Color.GRAY, BorderLineType.THIN, false, 0);
				
				ReportAPI.getCellStyle(cell, "Arial", Color.WHITE, true, 9, rsmd.getColumnName(i));
    	
			}
			countRow ++;
	   
			NumberFormat formatter = new DecimalFormat("#,###,###");
			while(rs.next())
			{
				for(int i = 1; i <= socottrongSql; i ++)
				{
					cell = cells.getCell(countRow,i-1 );
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i)==Types.INTEGER || rsmd.getColumnType(i)==Types.NUMERIC)
					{
						//cell.setStyle(style12);
						ReportAPI.getCellStyle_double(cell, "Arial", Color.BLACK, false, 9, rs.getDouble(i));
						// ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					}
					else
					{
						ReportAPI.getCellStyle(cell, "Arial", Color.BLACK, false, 9, rs.getString(i));
						// ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				++countRow;
			}
	   
			if(rs!=null)rs.close();

		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	private String getSearchQuery(HttpServletRequest request,ICongtacvienList obj)
	{	
		
		Utility util = new Utility();
		
		String ma = util.antiSQLInspection(request.getParameter("ma"));
    	if (ma == null)
    		ma = "";    	
    	obj.setMa(ma);
		
		String ten = util.antiSQLInspection(request.getParameter("ten"));
    	if ( ten == null)
    		ten = "";
    	obj.setTen(ten);
    	
    	String sodienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
    	if (sodienthoai == null)
    		sodienthoai = "";    	
    	obj.setSodienthoai(sodienthoai);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
    	if (trangthai == null)
    		trangthai = "";
    	if (trangthai.equals("2"))
    		trangthai = "";   
    	obj.setTrangthai(trangthai);
    	
    	String query =  "select a.ma, a.pk_seq  as id, a.ten , a.dienthoai, a.diachi, a.trangthai, a.ngaytao,   " +
						"	b.ten as nguoitao, a.ngaysua, c.ten as nguoisua " +
						"from congtacvien a inner join nhanvien b on a.nguoitao = b.pk_seq   " +
				
						"	 inner join  nhanvien c on   a.nguoisua = c.pk_seq  " +
				
						"where 1=1  ";
		
    	if (ma.length()>0)
    	{
			query = query + " and a.MA like upper('%" + ma + "%')";
    	}
    	
    	if (ten.length()>0)
    	{
			query = query + " and upper(dbo.ftBoDau(a.TEN)) like N'%'+upper(dbo.ftBoDau(N'%" +ten + "%'))+'%'";
    	}
    	
    	if (sodienthoai.length()>0)
    	{
			query = query + " and upper(a.dienthoai) like upper('%" + sodienthoai + "%')";
    	}
    	
    	if(trangthai.length() > 0){
    		query = query + " and a.trangthai = '" + trangthai + "'";
    		
    	}
    	
    	System.out.println("Serch   "+query);
    	return query;
	}
	
	private void Delete(String id,ICongtacvienList obj)
	{
		dbutils db = new dbutils();
		
			try{
			db.getConnection().setAutoCommit(false);
			String sql="delete from ddkd_gsbh where ddkd_fk = '" + id + "'";
			System.out.println("1." +sql);
			if(!db.update(sql))
			{
				db.update("rollback");
				obj.setMsg("Không thể xóa khi có giám sát");
				db.shutDown();
				return;
			}
			 sql="delete from daidienkinhdoanh where pk_Seq = '" + id + "'";
			 System.out.println("2." +sql);
			
			if(!db.update(sql))
			{
				db.update("rollback");
				System.out.println("Vao rollback");
				obj.setMsg("Không thể xóa khi đã có tuyến");
				db.shutDown();
				return;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		}catch(Exception  e)
		{
			System.out.println("Loi "+e.toString());
			db.update("rollback");
			obj.setMsg("Lỗi " + e.toString());
			db.shutDown();
			return;
		}
	}
	private String readExcel(String fileName, ICongtacvienList obj) 
	{
		try
		{
			File inputWorkbook = new File(fileName);
			jxl.Workbook w = null;
			w = jxl.Workbook.getWorkbook(inputWorkbook);
			jxl.Sheet sheet = w.getSheet(0);
			int sodong = sheet.getRows();
			
			String ctvMA = "";
			String ctvTen = "";
			String dbMA = "";
			String tdvMA = "";
			String khMA = "";
			String Diachi = "";
			String Dienthoai = "";
			String Gioitinh = "";
			String Chuyenkhoa = "";
			String Chucvu = "";
			String Lichkham = "";
			String query = "";
			dbutils db = new dbutils();
			try
			{
				db.getConnection().setAutoCommit(false);
				for (int i = 4; i < sodong; i++)
				{
					ctvMA = getValue(sheet, 0, i).trim();
					ctvTen = getValue(sheet, 1, i).trim().length() <= 0 ? "" : getValue(sheet, 1, i).trim();
					Diachi = getValue(sheet, 2, i).trim().length() <= 0 ? "" : getValue(sheet, 2, i).trim();
					Dienthoai = getValue(sheet, 3, i).trim().length() <= 0 ? "" : getValue(sheet, 3, i).trim();
					Gioitinh = getValue(sheet, 4, i).trim().length() <= 0 ? "" : getValue(sheet, 4, i).trim();
					Chuyenkhoa = getValue(sheet, 5, i).trim().length() <= 0 ? "" : getValue(sheet, 5, i).trim();
					Chucvu = getValue(sheet, 6, i).trim().length() <= 0 ? "" : getValue(sheet, 6, i).trim();
					Lichkham = getValue(sheet, 7, i).trim().length() <= 0 ? "" : getValue(sheet, 7, i).trim();
					dbMA = getValue(sheet, 8, i).trim().length() <= 0 ? "" : getValue(sheet, 8, i).trim();
					tdvMA = getValue(sheet, 10, i).trim().length() <= 0 ? "" : getValue(sheet, 10, i).trim();
					khMA = getValue(sheet, 12, i).trim().length() <= 0 ? "" : getValue(sheet, 12, i).trim();
					
					query = "select count(ma) num from congtacvien where ma = '"+ctvMA+"' ";
					ResultSet rsnum = db.get(query);
					if(rsnum.next())
					{
						int num = rsnum.getInt("num");
						if(num > 0)
						{
							ctvTen = ctvTen.replace("'", "''");
							query = "update CONGTACVIEN set ten = N'" + ctvTen + "', dienthoai ='" + Dienthoai + "', diachi = N'"+ Diachi + "', nguoisua = '" + obj.getUserId() + "', "+
									"ngaysua = '" + this.getDateTime() + "', gioitinh=N'"+Gioitinh+"',chuyenkhoa=N'"+Chuyenkhoa+"',chucvu=N'"+Chucvu+"', "+
									"lichkham='"+Lichkham+"' ";
							if(dbMA.trim().length()>0)query += ", diaban_fk = (select pk_seq from diaban where ten = '"+dbMA+"') ";
							if(tdvMA.trim().length()>0)query += ", tdv_fk = (select pk_seq from daidienkinhdoanh where mafast = '"+tdvMA+"') ";
							query += "where ma = '"+ctvMA+"'";
							System.out.println("[update] "+query);
							if(!db.update(query))
							{
								db.update("rollback");
								return "Lỗi: " + query;
							}
							
							if(khMA.length()>0)
							{		
								query = "delete a from congtacvien_khachhang a inner join congtacvien b on a.ctv_fk = b.pk_seq "+
										"inner join khachhang c on a.kh_fk = c.pk_seq where b.ma = '"+ctvMA+"' and c.mafast = '"+khMA+"'";
								System.out.println("---DELETE KH: " + query );
								if(!(db.update(query)))
								{
									db.update("rollback");
									return "Lỗi: " + query;
								}
								
								query = "insert CONGTACVIEN_KHACHHANG(ctv_fk, kh_fk) select (select pk_seq from congtacvien where ma = '"+ctvMA+"'), "+
										"pk_seq from KHACHHANG where mafast = '" + khMA + "'  ";
								System.out.println("---CHEN KH: " + query );
								if(!(db.update(query)))
								{
									db.update("rollback");
									return "Lỗi: " + query;
								}
							}
						}
						else
						{
							String diaban = "";
							if(dbMA.trim().length()>0) diaban = ", (select pk_seq from diaban where ten = '" + dbMA +"')"; else diaban = ", null";
							String tdv = "";
							if(tdvMA.trim().length()>0) tdv = ", (select pk_seq from daidienkinhdoanh where ten = '" + dbMA +"')"; else tdv = ", null";
							ctvTen = ctvTen.replace("'", "''");
							query = "insert into congtacvien(ma, ten,dienthoai,diachi, trangthai,nguoitao,nguoisua,ngaytao,ngaysua,gioitinh,chuyenkhoa,chucvu,"+
									"lichkham, diaban_fk, tdv_fk) " +
					  				"select '" + ctvMA + "', N'" + ctvTen +"','" + Dienthoai + "',N'"+ Diachi + "', '1', '" + obj.getUserId() + "','" + obj.getUserId() + "',"+
									"'" + this.getDateTime() + "','" + this.getDateTime() + "','"+Gioitinh+"',N'"+Chuyenkhoa+"',N'"+Chucvu+"','"+Lichkham+"' "+
					  				""+diaban+tdv+"";
							System.out.println("[insert] "+query);
							if(!db.update(query))
							{
								db.update("rollback");
								return "Lỗi: " + query;
							}
							
							if(khMA.length()>0)
							{	
								query = "delete a from congtacvien_khachhang a inner join congtacvien b on a.ctv_fk = b.pk_seq "+
										"inner join khachhang c on a.kh_fk = c.pk_seq where b.ma = '"+ctvMA+"' and c.mafast = '"+khMA+"'";
								System.out.println("---DELETE KH: " + query );
								if(!(db.update(query)))
								{
									db.update("rollback");
									return "Lỗi: " + query;
								}
								query = "insert CONGTACVIEN_KHACHHANG(ctv_fk, kh_fk) select (select pk_seq from congtacvien where ma = '"+ctvMA+"'), "+
										"pk_seq from KHACHHANG where mafast = '" + khMA + "'  ";
								System.out.println("---CHEN NPP: " + query );
								if(!(db.update(query)))
								{
									db.update("rollback");
									return "Lỗi: " + query;
								}
							}
						}
					}rsnum.close();
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				db.update("rollback");
				db.shutDown();
				return "Lỗi trong quá trình upload "+e.getMessage();
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return "Vui lòng coi lại file " + e.getMessage();
		}
		return "";
	}
	
	private String getValue(Sheet sheet, int column, int row)
	{
		jxl.Cell cell;
		cell = sheet.getCell(column, row);
		try
		{
			return cell.getContents();
		} 
		catch (Exception er)
		{
			return "0";
		}
	}
}




