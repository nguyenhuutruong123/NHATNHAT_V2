package geso.dms.center.servlets.duyettratrungbay;

import geso.dms.center.beans.duyettratrungbay.IDuyettratrungbay;
import geso.dms.center.beans.duyettratrungbay.IDuyettratrungbayList;
import geso.dms.center.beans.duyettratrungbay.imp.Duyettratrungbay;
import geso.dms.center.beans.duyettratrungbay.imp.DuyettratrungbayList;
import geso.dms.center.beans.tieuchithuong.ITieuchithuongTL;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Worksheet;
import com.oreilly.servlet.MultipartRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class DuyettratrungbayNewSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public DuyettratrungbayNewSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Utility util = new Utility();
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
		IDuyettratrungbay dttbBean = new Duyettratrungbay();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		dttbBean.setUserId(userId);
		dttbBean.init();
		session.setAttribute("dttbBean", dttbBean);
		session.setAttribute("schemeId", "0");
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraTrungBay.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String contentType = request.getContentType();
		HttpSession session = request.getSession();	
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);

		
		
		Utility util = new Utility();
		String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraTrungBayNew.jsp";
		//System.out.println("contentType : "+ contentType);
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			if(!csdr.__validate_post_mul(multi))
			{
				System.out.println("contentType loi : "+ contentType);
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
			Upload(multi,request, response);
			return ;
		} 
		
		
		if(!csdr.__validate_post())
		{
			System.out.println("contentType loi : "+ contentType);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		IDuyettratrungbay dttbBean = new Duyettratrungbay();
		String id = util.antiSQLInspection(request.getParameter("id"));
		String userId = (String) session.getAttribute("userId");
		dttbBean.setId(id);
		dttbBean.setUserId(userId);
		{
			String schemeId = util.antiSQLInspection(request.getParameter("cttbId"));
			dttbBean.setSchemeId(schemeId);
			session.setAttribute("schemeId", dttbBean.getSchemeId());
			
			String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
			if (diengiai == null)
				diengiai = "";
			
			dttbBean.setDiengiai(diengiai);
			/*else
			{
				dttbBean.setLantt(Integer.parseInt(lantt));
			}
			if(lantt != null)
				dttbBean.setLantt(Integer.parseInt(lantt));
			
			String vungId = util.antiSQLInspection(request.getParameter("vungId"));
			dttbBean.setVungId(vungId);

			String kvId = util.antiSQLInspection(request.getParameter("kvId"));
			dttbBean.setKvId(kvId);

			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			dttbBean.setNppId(nppId);
		 	*/
			String[] khIds = request.getParameterValues("mafast");
			if(khIds == null)
				khIds = new String[0];
			Hashtable<String, Integer> kh_tra = new Hashtable<String, Integer>();
			for(int i=0; i< khIds.length; i++){
				String tongtien = util.antiSQLInspection(request.getParameter("tt_" + khIds[i]));
				System.out.println("tt_" + khIds[i]);
				if(tongtien == null || tongtien.equals("null"))
					tongtien = "0";
				kh_tra.put(khIds[i], Integer.parseInt(tongtien));
			}
			dttbBean.setKhIds(khIds);
			dttbBean.setTraTb(kh_tra);
			dttbBean.setKhRs();
			
			String action = request.getParameter("action");
			System.out.println("action "+ action);
			
			if (action.equals("excel"))
			{
				
				try
				{
					response.setContentType("application/xls");
					response.setHeader("Content-Disposition", "attachment; filename=DuyetTraTrungBay_Template.xls");
					OutputStream out = response.getOutputStream();
					ExportToExcel(out,dttbBean);
					dttbBean.getDb().shutDown();
					
					Cookie fileDwnld = new Cookie("fileDownload", "true");
			    	fileDwnld.setPath("/");
			    	response.addCookie(fileDwnld);
			    	return;
					
				} 
				catch (Exception ex)
				{
					ex.printStackTrace();					
				}
				
			}
			else if (action.equals("aptrungbay"))
			{
				dttbBean.Aptrungbay();
				dttbBean.init();
				session.setAttribute("dttbBean", dttbBean);
				session.setAttribute("userId", userId);
				response.sendRedirect(nextJSP);
			}
			else if (action.equals("save"))
			{
				if(id.length() == 0)
				{
					if(!dttbBean.Luutratb())
					{
						dttbBean.createRs();
						session.setAttribute("dttbBean", dttbBean);
						session.setAttribute("userId", userId);
						response.sendRedirect(nextJSP);
					}
					else{
						dttbBean.init();
						session.setAttribute("dttbBean", dttbBean);
						session.setAttribute("userId", userId);
						response.sendRedirect(nextJSP);
					}
				}
				else{
					if(!dttbBean.UpdateTb())
					{
						dttbBean.createRs();
						session.setAttribute("dttbBean", dttbBean);
						session.setAttribute("userId", userId);
						response.sendRedirect(nextJSP);
					}
					else{
						IDuyettratrungbayList obj = new DuyettratrungbayList();
						obj.setUserId(userId);
						obj.init();
						session.setAttribute("dttbBean", obj);
						session.setAttribute("userId", userId);
						nextJSP = request.getContextPath() + "/pages/Center/DuyetTraTrungBay.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}

			if (request.getParameter("action").equals("chot"))
			{
				System.out.println("___CHOT DUYET TRUNG BAY...");
				dttbBean.Chot(request);
			}
		}
		
		
	}
	
	
	public void Upload(MultipartRequest multi,HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraTrungBayNew.jsp";
		Utility util = new Utility();
		HttpSession session = request.getSession();
		IDuyettratrungbay dttbBean = new Duyettratrungbay();
		String id = util.antiSQLInspection(request.getParameter("id"));
		String userId = (String) session.getAttribute("userId");
		dttbBean.setId(id);
		dttbBean.setUserId(userId);
		dttbBean.setId(id);
		dttbBean.setUserId(userId);
		
	    dttbBean.setSchemeId(util.antiSQLInspection(multi.getParameter("cttbId"))==null? "":util.antiSQLInspection(multi.getParameter("cttbId")));	   	 
		dttbBean.setId(util.antiSQLInspection(multi.getParameter("id"))==null? "":util.antiSQLInspection(multi.getParameter("id")));	 
		dttbBean.setDiengiai(util.antiSQLInspection(multi.getParameter("diengiai"))==null? "":util.antiSQLInspection(multi.getParameter("diengiai")));	 
	  	Enumeration files = multi.getFileNames(); 
	  	String filenameu  ="";
	  	while (files.hasMoreElements())
        {
             String name = (String)files.nextElement();
             filenameu = multi.getFilesystemName(name); 
             System.out.println("name :   "+name);;
        }
	  	boolean err = false;
	  	String filename = "C:\\java-tomcat\\data\\" + filenameu;
		//String filename=    getServletContext().getRealPath("\\upload\\")+ "\\"+ filenameu;
		if(filenameu == null)
			dttbBean.setMessage("Kh??ng c?? file n??o ???????c upload");
		if (filenameu != null && filename.length() > 0)
		{
			//doc file excel
			File file = new File(filename);
			System.out.println("filename  "+file);
			Workbook workbook;
			int indexRow=8;
	
			try 
			{						
				System.out.println(file);
				workbook = Workbook.getWorkbook(file);
				
				Sheet[] sheet1 = workbook.getSheets();
				Sheet sheet=sheet1[0];					 
				Cell[] cells ;
				String khachhang_fks = "";
				Hashtable<String, Integer> kh_tra = new Hashtable<String, Integer>();
				int sokh = 0;
				//System.out.println("getRows = " +sheet.getRows());
				for(int i= indexRow; i < sheet.getRows();i++)
				{	
					cells = sheet.getRow(i);
					
					if (cells.length >= 3){
					
						String KHACHHANG_FK = cells[1].getContents().toString();
						String suattra = cells[6].getContents().toString();
						khachhang_fks += KHACHHANG_FK.trim() + ",";
						if(suattra.trim().length() == 0)
							suattra = "0";
						kh_tra.put(KHACHHANG_FK, Integer.parseInt(suattra.trim().replace(",", "")));
						sokh ++;
						System.out.println("__ " + KHACHHANG_FK + ":" + suattra);
					}							
				}
				if(sokh == 0)
					dttbBean.setMessage("Kh??ng c?? kh??ch h??ng n??o ???????c ch???n!");
				
				if(khachhang_fks.length() > 0)
					khachhang_fks = khachhang_fks.substring(0, khachhang_fks.length() - 1);
				dttbBean.setKhIds(khachhang_fks.split(","));
				dttbBean.setTraTb(kh_tra);
				
				
				if(dttbBean.Upload())
				{
					dttbBean.init();
					session.setAttribute("dttbBean", dttbBean);
					session.setAttribute("userId", userId);
					response.sendRedirect(nextJSP);
					return;
				}
				
				
			}catch(Exception er){
				er.printStackTrace();
				System.out.println("Exception. "+er.getMessage());
				dttbBean.setMessage("L???i trong qu?? tr??nh upload: " + er.getMessage());
			}
			
		}
		dttbBean.setKhRs();
		dttbBean.createRs();
		session.setAttribute("dttbBean", dttbBean);
		session.setAttribute("userId", userId);
		response.sendRedirect(nextJSP);
	
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void ExportToExcel(OutputStream out,IDuyettratrungbay obj )throws Exception
	{
		try
		{ 			
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);

			String query = setQuery(obj);
			TaoBaoCao(workbook, obj, query);
			workbook.save(out);			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	public String setQuery (IDuyettratrungbay obj )
	{
		String query =	 	 "\n select npp.ten [NPP], kh.smartId[M?? KH(D??? li???u Upload)], kh.ten [T??n KH] , dnct.doanhso [Doanh s???] , dnct.XUATDENGHI [Su???t ????? ngh???]" + 
							 "\n 	, case isnull(duyet.duyetAnh,0) when 1 then N'???? duy???t' when 2 then N'Kh??ng Duy???t' when 0 then N'Ch??a duy???t' else '' end [Duy???t ???nh]" + 
							 "\n 	, isnull(dnct.XUATDUYET,0) [Su???t duy???t (D??? li???u Upload)], ddkd.DDKDMA [M?? NVBH] , ddkd.DDKDTEN [NVBH]" + 
							 "\n from DENGHITRATB_KHACHHANG dnct" + 
							 "\n inner join khachhang kh on kh.PK_SEQ = dnct.KHACHHANG_FK" + 
							 "\n inner join NHAPHANPHOI npp on npp.pk_seq = kh.npp_fk" + 
							 " outer apply ( select top 1 duyetAnh from DENGHITRATB_KHACHHANG_DUyetAnh x where dnct.denghitratb_fk =x.denghitratb_fk and x.KHACHHANG_FK = dnct.KHACHHANG_FK  ) duyet " +
							 "\n outer apply" +
								"\n (" +
								"\n		select top 1 ddkd.ten DDKDTEN ,ddkd.smartId DDKDMA" +
								"\n 	from daidienkinhdoanh ddkd" +
								"\n		inner join tuyenbanhang tbh on tbh.ddkd_fk = ddkd.pk_seq" +
								"\n 	inner join khachhang_tuyenbh x on x.tbh_fk = tbh.pk_seq " +
								"\n		where x.khachhang_fk  = kh.pk_seq and tbh.npp_fk = kh.npp_fk " +
								"\n )ddkd " +
							 "\n where dnct.DENGHITRATB_FK = "+ obj.getId();
						
		System.out.println("query BC="+ query);
		return query;
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IDuyettratrungbay obj,String query )throws Exception
	{
		try
		{
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();

			com.aspose.cells.Cell cell = cells.getCell("B3");
			cells.setRowHeight(0, 50.0f);
			 cell = cells.getCell("A1");
			 ReportAPI.getCellStyle(cell, Color.RED, true, 16,"FORM UPLOAD DUY???T TR??NG B??Y");

			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ng??y t???o : "+ getDateTime());		
		
			ResultSet rs=  obj.getDb().get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int countRow = 7;
			int column = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{

				cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);	

			}
			countRow ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{
					Color c = Color.WHITE;
					cell = cells.getCell(countRow,column + i-1 );
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
				}
				++countRow;
			}
			
			if(rs!=null)rs.close();
			


		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("L???i kh??ng l???y t??ch l??y !");
		}
	}
}
