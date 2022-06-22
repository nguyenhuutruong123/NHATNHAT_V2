package geso.dms.distributor.servlets.donhangctv;

import geso.dms.distributor.beans.donhangctv.IBCXNT_CTV;
import geso.dms.distributor.beans.donhangctv.imp.BCXNT_CTV;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

import java.util.*;

public class BCXNT_CTVSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public BCXNT_CTVSvl()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IBCXNT_CTV obj = new BCXNT_CTV();
		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		
		obj.setuserId(userId);
		String isdlpp = request.getParameter("isdlpp");
		if( isdlpp == null )
			isdlpp = "1";
		obj.setIsDlpp(isdlpp);
		
		obj.init_dlpp_ctv("");
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCXNT_CTV.jsp";
		response.sendRedirect(nextJSP);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IBCXNT_CTV obj = new BCXNT_CTV();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		
		Utility util = new Utility();
		if (userId == null)
			userId = "";
		obj.setuserId(userId);
		obj.setuserTen(userTen);
		
		String tungay = request.getParameter("Sdays"); // <!---
		if (tungay == null)
			tungay = "";
		
		String denngay = request.getParameter("Edays");// <!---
		if (denngay == null)
			denngay = "";
		
		String isdlpp = request.getParameter("isdlpp");// <!---
		if (isdlpp == null)
			isdlpp = "";
		obj.setIsDlpp(isdlpp);
		
		obj.setMonth( util.antiSQLInspection(request.getParameter("Sdays")) != null ? util.antiSQLInspection(request.getParameter("Sdays")) : "" );
		obj.setYear( util.antiSQLInspection(request.getParameter("Edays")) != null ? util.antiSQLInspection(request.getParameter("Edays")) : "" );
		obj.setkhuvucId( util.antiSQLInspection(request.getParameter("khuvucId")) != null ? util.antiSQLInspection(request.getParameter("khuvucId")) : "" );
		obj.setTinhthanhid( util.antiSQLInspection(request.getParameter("tinhthanhId")) != null ? util.antiSQLInspection(request.getParameter("tinhthanhId")) : "" );
		obj.setkhTen( util.antiSQLInspection(request.getParameter("khachang")) != null ? util.antiSQLInspection(request.getParameter("khachang")) : "" );
		obj.setSpId( util.antiSQLInspection(request.getParameter("tenSP")) != null ? util.antiSQLInspection(request.getParameter("tenSP")) : "" );
		obj.setnppId( util.antiSQLInspection(request.getParameter("nppId")) != null ? util.antiSQLInspection(request.getParameter("nppId")) : "" );
		
		if (!tungay.equals("") && !denngay.equals(""))
		{
			String action = request.getParameter("action");
			System.out.println(":: ACTION: " + action);
			
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			if (action.equals("tao"))
			{
				if( obj.getIsDlpp().equals("0") ) //BÁO CÁO XNT CỘNG TÁC VIÊN
				{
					OutputStream out = response.getOutputStream();
					
					try
				    {
				        response.setContentType("application/xlsm");
						response.setHeader("Content-Disposition", "attachment; filename=BaoCaoXNTCongTacVien.xlsm");
				        
				        Workbook workbook = new Workbook();
				    	
						FileInputStream fstream = null;
						String chuoi = getServletContext().getInitParameter("path") + "\\BCXuatNhapTonCTV.xlsm";
						
						fstream = new FileInputStream(chuoi);		
						workbook.open(fstream);
						workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				        
					    String query = getSearchQueryCTV(request, obj);
					    CreateStaticDataCTV(workbook, query, obj);
					
					    //Saving the Excel file
					    workbook.save(out);
					    fstream.close();
				    }
				    catch (Exception ex){ ex.printStackTrace(); }
				}
			}
			else
			{
				if(obj.getIsDlpp().equals("0"))
				{
					String query = getSearchQueryCTV(request, obj);
					
					obj.init_dlpp_ctv(query);
					session.setAttribute("obj", obj);
					session.setAttribute("userId", userId);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/BCXNT_CTV.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					obj.init_dlpp_ctv("");
					session.setAttribute("obj", obj);
					session.setAttribute("userId", userId);
					
					String nextJSP = request.getContextPath() + "/pages/Distributor/BCXNT_CTV.jsp";
					response.sendRedirect(nextJSP);
				}
			}
		}
	}
	
	/************* XNT CONG TAC VIEN *****************************************************************************/
	
	private String getSearchQueryCTV(HttpServletRequest request, IBCXNT_CTV obj) 
	{
		String tungay = "";
		String denngay = "";
		
		if( obj.getMonth().trim().length() < 2 )
		{
			tungay = obj.getYear() + "-" + "0" + obj.getMonth() + "-01";
			denngay = obj.getYear() + "-" + "0" + obj.getMonth() + "-31";
		}
		else
		{
			tungay = obj.getYear() + "-" + obj.getMonth() + "-01";
			denngay = obj.getYear() + "-" + obj.getMonth() + "-31";
		}
		
		String conditon = "";
		if( obj.getkhuvucId().trim().length() > 0 )
			conditon += " and bc.khachhang_fk in ( select pk_seq from KHACHHANG where npp_fk = '" + obj.getnppId() + "' and diaban in ( select pk_seq from DIABAN where khuvuc_fk = '" + obj.getkhuvucId() + "' ) ) ";
		if( obj.getTinhthanhid().trim().length() > 0 )
			conditon += " and bc.khachhang_fk in ( select pk_seq from KHACHHANG where npp_fk = '" + obj.getnppId() + "' and tinhthanh_fk = '" + obj.getTinhthanhid() + "' ) ";
		if( obj.getkhTen().trim().length() > 0 )
			//conditon += " and dbo.ftBoDau( makhCAP2 + ' ' + tenkhCAP2 ) like N'%" + obj.getkhTen() + "%' ";
			conditon += " and khachhang_fk = '" + obj.getkhTen() + "' ";
		if( obj.getSpId().trim().length() > 0 )
			conditon += " and dbo.ftBoDau( maSP + ' ' + tenSP ) like N'%" + obj.getSpId() + "%' ";
		
		String query = "select makhCAP1, tenkhCAP1, maSP, tenSP, donvi, dongia, tondau, nhaptrongky, xuattrongky, tinhthanh " +
					   "from ufn_xnt_ctv ( '" + tungay + "', '" + denngay + "', " + obj.getnppId() + " ) bc "+
					   "inner join nhaphanphoi a on bc.npp_fk = a.pk_seq "+
					   "inner join nhaphanphoi b on bc.npp_fk = b.pk_seq where 1 = 1 and a.pk_seq = "+obj.getnppId()+" ";
					   
		query += conditon;
		query += "	order by tenkhCAP1 asc, tenSP asc  ";
		
		System.out.print(":: BAO CAO XNT CTV: " + query);
		return query;

	}

	private void CreateStaticDataCTV(Workbook workbook, String query, IBCXNT_CTV obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
	    Cell cell = null;
	    Style style;
		Font font2 = new Font();
		//font2.setName("Calibri");				
		font2.setSize(11);
	    
	    cell = cells.getCell("A2"); cell.setValue( "Tháng: " + obj.getMonth() ); 
	    style = cell.getStyle(); style.setFont(font2); cell.setStyle(style);
	    
	    cell = cells.getCell("A3");	cell.setValue( "Năm: " + obj.getYear() ); 
	    style = cell.getStyle(); style.setFont(font2); cell.setStyle(style);
	    
		dbutils db = new dbutils();
		
		ResultSet rs = db.get(query);

		int i = 7;
		if(rs != null)
		{
			try 
			{
				int stt = 1;
				while(rs.next())
				{
					double dongia = rs.getDouble("DONGIA");
					
					double tondau = rs.getDouble("tondau");
					double nhaptrongky = rs.getDouble("nhaptrongky");
					//double nhaptrahang = rs.getDouble("nhaptrahang");
					//double dieuchinh = rs.getDouble("dieuchinh");
					double xuattrongky = rs.getDouble("xuattrongky");
					
					//NHAP TRA HANG KHONG DUOC AM KHO KHI THE HIEN TREN BC NAY
					//if( tondau + nhaptrongky - nhaptrahang - xuattrongky < 0 )
						//nhaptrahang = tondau + nhaptrongky - xuattrongky;
					
					//double toncuoi = tondau + nhaptrongky - nhaptrahang + dieuchinh - xuattrongky;
					double toncuoi = tondau + nhaptrongky - xuattrongky;
					double thanhtienTC = toncuoi * dongia;
					
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue( rs.getString("tinhthanh") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("B" + Integer.toString(i));	cell.setValue( rs.getString("diaban") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue( rs.getString("makhCAP1") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue( rs.getString("tenkhCAP1") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("E" + Integer.toString(i));	cell.setValue( rs.getString("makhCAP2") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("F" + Integer.toString(i));	cell.setValue( rs.getString("tenkhCAP2") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue( rs.getString("maSP") ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue( rs.getString("tenSP") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue( rs.getString("DONVI") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue( rs.getDouble("DONGIA") ); 			style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue( tondau ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue( nhaptrongky );	style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);					
					//cell = cells.getCell("M" + Integer.toString(i));	cell.setValue( nhaptrahang ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					//cell = cells.getCell("N" + Integer.toString(i));	cell.setValue( dieuchinh ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue( xuattrongky ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue( toncuoi ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);
					cell = cells.getCell("L" + Integer.toString(i));	cell.setValue( thanhtienTC ); 		style = cell.getStyle(); style.setFont(font2); cell.setStyle(style); setCellBorderStyle(cell);

					i++;
					stt++;
				}
				rs.close();
			}
			catch (Exception e){ 
				db.shutDown();
				e.printStackTrace(); }
		}
		
		db.shutDown();
	}
	
	/************** END XNT CONG TAC VIEN *************************************************************************/
	
	private void setCellBorderStyle(Cell cell) 
	{
		Style style = cell.getStyle();
		style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);
		cell.setStyle(style);
	}
	
	
	public String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}


}

