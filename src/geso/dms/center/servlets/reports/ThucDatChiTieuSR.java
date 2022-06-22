package geso.dms.center.servlets.reports;

 import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.Worksheet;
//import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;


public class ThucDatChiTieuSR extends HttpServlet {
	
	private static final long serialVersionUID = 1L; 
	
	public ThucDatChiTieuSR() {
		super();
	}

	//Hashtable<String, String> HeaderHash = new Hashtable<String, String>();
	Map<String, String> HeaderHash = new HashMap<String, String>();
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
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String view = util.getView(querystring);
		//System.out.println("querystring : "+ querystring+" - view : "+ view);
		obj.setuserId(userId);
		obj.init();
		obj.setLoaiNv("1");
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		session.setAttribute("view", view);
		String nextJSP = request.getContextPath() + "/pages/Center/ThucDatChiTieuSR.jsp";
		response.sendRedirect(nextJSP);
	}

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
		IStockintransit obj = new Stockintransit();	
		Utility util = new Utility();

		obj.setuserId((String)session.getAttribute("userId")==null?"":(String) session.getAttribute("userId"));
		obj.setuserTen((String)session.getAttribute("userTen")==null? "":(String) session.getAttribute("userTen"));
		obj.setnppId(util.antiSQLInspection(request.getParameter("nppId"))==null?"":util.antiSQLInspection(request.getParameter("nppId")));
		
		String view = util.antiSQLInspection(request.getParameter("view"));
		session.setAttribute("view", view);
		
		String nppId ="";
		if(view.equals("TT")) { nppId = util.antiSQLInspection(request.getParameter("nppId"))==null?"":util.antiSQLInspection(request.getParameter("nppId")); }
		else { nppId=util.getIdNhapp((String)session.getAttribute("userId")==null?"":(String) session.getAttribute("userId")); }
		//System.out.println("nppId : "+ nppId);
		obj.setnppId(nppId);
		
		obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId"))==null? "":util.antiSQLInspection(request.getParameter("kenhId")));
		obj.setdvkdId(util.antiSQLInspection(request.getParameter("dvkdId"))==null? "":util.antiSQLInspection(request.getParameter("dvkdId")));
		obj.setMonth(util.antiSQLInspection(request.getParameter("month"))==null? "":util.antiSQLInspection(request.getParameter("month")));
		obj.setYear(util.antiSQLInspection(request.getParameter("year"))==null? "":util.antiSQLInspection(request.getParameter("year")));	   	 
		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId"))==null? "":util.antiSQLInspection(request.getParameter("vungId")));	   	 
		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId"))==null? "":util.antiSQLInspection(request.getParameter("khuvucId")));	 
		obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlId"))==null? "":util.antiSQLInspection(request.getParameter("dvdlId")));
		obj.setLoaiNv(util.antiSQLInspection(request.getParameter("loainv"))==null? "":util.antiSQLInspection(request.getParameter("loainv")));
		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId"))==null? "":util.antiSQLInspection(request.getParameter("ddkdId")));

		if(util.antiSQLInspection(request.getParameter("covat"))!= null){ obj.setcovat("1"); }
		else{ obj.setcovat("0"); }

		String []fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);		 
		
		String[] vungid = util.antiSQLInspection_Array(request.getParameterValues("vungId"));
		String s = "";
		if(vungid != null)
		{
			for(int i = 0; i < vungid.length; i++)
			{ s += vungid[i] +","; }
			if(s.length() > 0)
			{ s = s.substring(0,s.length() -1); }
		}
		//System.out.println("VUng: "+s);
		obj.setvungId(s);
		String[] khuvucId = util.antiSQLInspection_Array(request.getParameterValues("khuvucId"));  
		s = "";
		if(khuvucId != null)
		{
			for(int i = 0; i < khuvucId.length; i++)
			{
				s += khuvucId[i] +",";
			}
			if(s.length() > 0)
			{
				s = s.substring(0,s.length() -1);
			}
		}
		obj.setkhuvucId(s);
		
		/*String[] nppId =  util.antiSQLInspection_Array(request.getParameterValues("nppId"));   
		 s = "";
		if(nppId != null)
		{
			for(int i = 0; i < nppId.length; i++)
			{
				s += nppId[i] +",";
			}
			if(s.length() > 0)
			{
				s = s.substring(0,s.length() -1);
			}
		}
		if(s.length() <= 0)
		{
			s = " select pk_seq from nhaphanphoi where 1 = 1 ";
			if(obj.getkhuvucId().length() > 0)
			{
				s+= " and khuvuc_fk in ("+obj.getkhuvucId()+") ";
			}
			
			if(obj.getvungId().length() > 0)
			{
				s+= " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk in ("+obj.getvungId()+") )";
			}
		}
		obj.setnppId(s);*/
		
		String nextJSP = request.getContextPath() + "/pages/Center/ThucDatChiTieuSR.jsp";		 
		try
		{
			String action=util.antiSQLInspection(request.getParameter("action"));
			//System.out.println("action _______+________"+action);
			if(action.equals("Taomoi")){
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=ThucHienChiTieu.xlsm");
				OutputStream out = response.getOutputStream();
				ExportToExcel(out,obj);			
				return;
			}	
			
			if(action.equals("chot"))
			{
				String msg =  KhoaSo( obj);
				obj.setMsg(msg);
				session.setAttribute("obj", obj);
				session.setAttribute("userId", obj.getuserId());
			}	
		}catch(Exception ex){
			obj.setMsg(ex.getMessage());
		}
		obj.init();	    
		session.setAttribute("obj", obj);
		session.setAttribute("userId", obj.getuserId());		
		response.sendRedirect(nextJSP);
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook, IStockintransit obj )throws Exception
	{
		int countRow = 10;
		int column = 0;
		//Chỉnh sửa theo Template mới
		try{
			
			String query =  setQuery(obj);
			String nhanvien = "";
			if(obj.getLoaiNv().equals("1")) {nhanvien = "SR";};
			if(obj.getLoaiNv().equals("5")) {nhanvien = "NPP";};
			
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("ThucDatChiTieu");
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");	
			ReportAPI.getCellStyle(cell,Color.RED, true, 16, "THỰC ĐẠT CHỈ TIÊU "+ nhanvien);
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
			cell = cells.getCell("A5");
			ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());

			Font font = new Font();
			font.setColor(Color.RED);//mau chu
			font.setSize(16);// size chu
			font.setBold(true);

			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			//Set Header phụ
			String sql = "";
			ArrayList<String> temp_arr = obj.getArr_text_baocaoSR();
			if (temp_arr != null && temp_arr.size() > 0);
			{
				for (int i = 0; i < temp_arr.size(); i ++) {
					sql += ", 'a' "+temp_arr.get(i);
				}
				if (sql !=  null && sql.length() > 0) {
					sql = sql.substring(1);
					sql = "select "+sql;
				}
			}
			ResultSet temprs = obj.getDb().get(sql);
			ResultSetMetaData rsmd2 = temprs.getMetaData();
			int socottieude = rsmd2.getColumnCount();
			//System.out.println("temp_arr.size: "+temp_arr.size()+"socottieude "+socottieude+" sql: "+sql);
			
			Map<String, String> sortedMap = new TreeMap<String, String>(HeaderHash);
			Set<String> keys = sortedMap.keySet();
			int j = 5;
	        for(String key: keys){
	            //System.out.println("Key: " + key+ " -- Value: "+ sortedMap.get(key));
				cell = cells.getCell(countRow - 1, column + j - 1 );cell.setValue( sortedMap.get(key) ); cells.setRowHeight(countRow - 1, 23.0f);
				ReportAPI.mergeCells(worksheet, countRow - 1, countRow - 1, column + j - 1, column + j + 4);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j ); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j + 1 );ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j + 2 );	ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j + 3 );	ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j + 4 );	ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				j = j + 6;
	        }
	        sortedMap.clear();
			
			/*Enumeration<String> keys = sortedMap.keys();
			String key = "";
			int j = 5;
			while (keys.hasMoreElements()) 
			{
				key = keys.nextElement();
				
				////System.out.println("Key: " + key+ " -- Value: "+ HeaderHash.get(key)+ " - column "+ (column + i-1));
				//System.out.println("Key: " + key+ " -- Value: "+ HeaderHash.get(key));
				cell = cells.getCell(countRow - 1, column + j - 1 );cell.setValue( HeaderHash.get(key) ); cells.setRowHeight(countRow - 1, 23.0f);
				ReportAPI.mergeCells(worksheet, countRow - 1, countRow - 1, column + j - 1, column + j + 1);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j ); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				cell = cells.getCell(countRow - 1, column + j + 1 ); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				j = j + 3;
			}*/
			HeaderHash.clear();
			/*}*/	
			
			//Header chính
			for( int i =1 ; i <=socottrongSql -1; i ++  )
			{
				////System.out.println("rsmd.getColumnName("+i+") : "+ rsmd.getColumnName(i));
				//cell = cells.getCell(countRow - 1, column + i-1 );cell.setValue(rsmd.getColumnName(i)); cells.setRowHeight(countRow, 23.0f);
				cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i)); cells.setRowHeight(countRow, 23.0f);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			countRow ++;

			//Nội dung
			int stt = 0;
			//long mang[] = new long[10000];
			while(rs.next())
			{
				Color c = Color.WHITE;
				boolean bold =false;
				
				//mang[stt] = rs.getLong("pk_seq");

				stt++;

				for(int i = 1;i <=socottrongSql -1; i ++)
				{
					cell = cells.getCell(countRow,column + i-1 );
					if(rsmd.getColumnName(i).equals("STT"))
					{					
						cell.setValue(stt);
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
						////System.out.println("STT: "+stt);

					}else
						if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
						{
							int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ Lệ"))	
							{
								format = 10;
							}
							cell.setValue(rs.getDouble(i));
							ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, format);
							//cells.setColumnWidth(column + i-1, 30.0f);
							worksheet.autoFitColumn(column + i-1);
						}
						else
						{
							cell.setValue(rs.getString(i));
							ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
							//cells.setColumnWidth(column + i-1, 30.0f);
							worksheet.autoFitColumn(column + i-1);
						}
					
					cells.setRowHeight(countRow, 23.0f);
				}

				++countRow;
			}

			if(rs!=null)
			{
				rs.close();
			}

		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Không có báo cáo trong thời gian này.");
		}
	}
	
	public String KhoaSo(IStockintransit obj)
	{
		dbutils db = new dbutils();
		try
		{
			String dauthang = obj.getYear() + "-" + (obj.getMonth().trim().length() > 1 ? obj.getMonth() : "0" + obj.getMonth()) + "-01";
			db.getConnection().setAutoCommit(false);
			
			String query = " delete KhoaSoChiTieu_DDKD where thang ="+obj.getMonth()+" and nam =  "+obj.getYear()+" ";
			if(obj.getnppId().trim().length() >0)
				query += "  and NHANVIEN_FK in (select pk_seq from daidienkinhdoanh where NPP_FK  in ("+obj.getnppId()+" )) ";
			//System.out.println("____"+query);
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return "lỗi \n " + query;
			}
			
			
			 query = "\n select a.loai isTinhLuong,a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a" +
			"\n inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+
			"\n and b.TRANGTHAI = 1 and b.LOAI = 1  " +
			"\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq " +
			"\n where ct.TCTCT_FK = a.pk_seq and ct.chitieu > 0 and ctnv.trangthai = 1 ) ";
			//System.out.println("QRBC "+query );
			ResultSet rs = db.get(query);
			while(rs.next())
			{
				String tctctId = rs.getString("pk_seq");
				String tieuchi = rs.getString("tieuchi");
				String diengiai = rs.getString("diengiai");
						query =	"\n insert KhoaSoChiTieu_DDKD(thang,nam,CTNV_FK,TCTCT_FK,NSP_FK,TIEUCHI,NhanVien_FK,ChiTieu,ThucDat,TyLeDat,Thuong,	nguoitao) " +
								"\n select "+obj.getMonth()+" thang, "+obj.getYear()+" nam " +
								"\n		,ct.CTNV_FK,ct.tctct_fk,ct.NSP_FK,ct.TIEUCHI,ct.NhanVien_FK " +
								"\n		,ct.ChiTieu,isnull(td.thucdat,0)thucdat	, dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) tyle,0 thuong" +
								"\n		,"+obj.getuserId()+" nguoitao	  " +
								"\n from ChiTieuNhanVien_DDKD ct " +
								"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
								"\n left join [dbo].[ufn_KPI_SR]("+dauthang+"," + tctctId+","+tieuchi+",null) td on ct.NhanVien_FK = td.NhanVien_FK " +
								"\n where ct.chitieu > 0  " +
								"\n and ct.tctct_fk = " + tctctId+" ";
						if(obj.getnppId().trim().length() >0)
							query += "  and ct.NhanVien_FK in (select pk_seq from daidienkinhdoanh where NPP_FK in ("+obj.getnppId()+" )) ";
						
				//System.out.println("query bc= " + query);
				if(db.updateReturnInt(query)<0)
				{
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return "lỗi khóa sổ tiêu chí("+diengiai+"):\n " + query;
				}
				
				
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "Lưu chỉ tiêu tháng được chọn thành công";
		}
		catch (Exception e) {
			try {	db.getConnection().rollback();} catch (SQLException e1) {}
			try {	db.getConnection().setAutoCommit(true);} catch (SQLException e1) {}
			return "Lỗi:" + e.getMessage();
		}
		
	}
	
	private void ExportToExcel(OutputStream out,IStockintransit obj )throws Exception
	{
		try
		{ 			
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			TaoBaoCao(workbook, obj);
			workbook.save(out);			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}
	
	
	public String setQuery (IStockintransit obj) throws SQLException {

		String quyen = ""; 
		/*"\n select PK_SEQ   " + 
		"\n from DAIDIENKINHDOANH yddkd  " + 
		"\n where exists   " + 
		"\n (  " + 
		"\n 	select 1 from nhanvien xnv where xnv.pk_seq =  " + obj.getuserId() + 
		"\n 		and (  	   (  xnv.loai !=3 and  exists (select 1 where yddkd.NPP_FK in ( select npp_fk from phamvihoatdong where nhanvien_fk = xnv.pk_seq )   )        )	 	   " + 
		"\n 				or (  xnv.loai = 3 and  exists (select 1 from ddkd_gsbh where gsbh_fk = xnv.gsbh_fk and ddkd_fk = yddkd.pk_seq   )        )	  " + 		 
		"\n 		)  " + 
		"\n 		  " + 
		"\n )  " ;*/

		String diengiai = "";
		String query = "";

		String sql = ""; 
		if(obj.getLoaiNv().equals("1"))
		{
			sql = " select a.loai isTinhLuong, a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a " +
					  " inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ " +
					  " where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+ " " +
					  " and b.TRANGTHAI = '1' and b.LOAI = '"+ obj.getLoaiNv() +"' ";

				if(obj.getDdkd().length() > 0)
				{
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct "
						+ " where ct.TCTCT_FK = a.pk_seq  and ct.NhanVien_FK = '"+obj.getDdkd()+"' and ct.chitieu > 0 ) ";
				}

				sql += "\n and exists (select 1 from  ChiTieuNhanVien_DDKD ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq "
					+ " where ct.TCTCT_FK = a.pk_seq and ct.chitieu > 0 and ctnv.trangthai = 1 "
					+ ") order by a.pk_seq ";
		}
		else if(obj.getLoaiNv().equals("2"))
		{
			sql = " select a.loai isTinhLuong, a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a " +
					  " inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ " +
					  " where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+ " " +
					  " and b.TRANGTHAI = '1' and b.LOAI = 1 ";

				if(obj.getDdkd().length() > 0)
				{
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_GSBH ct "
						+ " where ct.TCTCT_FK = a.pk_seq  and ct.NhanVien_FK = '"+obj.getDdkd()+"' and ct.chitieu > 0 ) ";
				}

				sql += "\n and exists (select 1 from  ChiTieuNhanVien_GSBH ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq "
					+  "  where ct.TCTCT_FK = a.pk_seq and isnull(ct.chitieu,0) > 0 and ctnv.trangthai = 1 "
					+  " ) order by a.pk_seq ";
		}else if(obj.getLoaiNv().equals("3"))
		{
			sql = " select a.loai isTinhLuong, a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a " +
					  " inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ " +
					  " where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+ " " +
					  " and b.TRANGTHAI = '1' and b.LOAI = 1 ";

				if(obj.getDdkd().length() > 0)
				{
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_ASM ct "
						+ " where ct.TCTCT_FK = a.pk_seq  and ct.NhanVien_FK = '"+obj.getDdkd()+"' and ct.chitieu > 0 ) ";
				}

				sql += "\n and exists (select 1 from  ChiTieuNhanVien_ASM ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq "
					+  "  where ct.TCTCT_FK = a.pk_seq and isnull(ct.chitieu,0) > 0 and ctnv.trangthai = 1 "
					+  " ) order by a.pk_seq ";
		}
			
		/*else if(obj.getLoaiNv().equals("5"))
		{
			sql = " select a.loai isTinhLuong, a.tieuchi,a.pk_seq, a.diengiai from tieuchithuong_chitiet a " +
				  " inner join tieuchitinhthuong b on a.TIEUCHITINHTHUONG_FK = b.PK_SEQ " +
				  " where b.THANG = "+obj.getMonth()+" and b.NAM = "+obj.getYear()+ " " +
				  " and b.TRANGTHAI = '1' and b.LOAI = '"+ obj.getLoaiNv() +"' ";

				if(obj.getnppId().length() > 0)
				{
					sql += "\n and exists (select 1 from  ChiTieuNhanVien_NPP ct "
						+ " where ct.TCTCT_FK = a.pk_seq  and ct.NhanVien_FK = '"+obj.getnppId()+"' and ct.chitieu > 0 ) ";
				}

				sql += "\n and exists (select 1 from  ChiTieuNhanVien_NPP ct inner join chitieunhanvien ctnv on ct.ctnv_fk = ctnv.pk_seq "
					+ " where ct.TCTCT_FK = a.pk_seq and ct.chitieu > 0 and ctnv.trangthai = '1' " 
					+ ") order by a.pk_seq ";
		}*/

		//System.out.println("QRBC : "+sql );
		String tct_fks = "";
		ResultSet rs = obj.getDb().get(sql);
		HeaderHash.clear();
		while(rs.next())
		{
			if(tct_fks.trim().length() > 0)
				tct_fks += "," + rs.getString("pk_seq");
			else
				tct_fks = rs.getString("pk_seq");
			if(diengiai.trim().length() > 0)
				diengiai += "__" + rs.getString("pk_seq");
			else
				diengiai += rs.getString("pk_seq");

			HeaderHash.put(rs.getString("pk_seq"), rs.getString("diengiai"));
		}

		if(obj.getLoaiNv().equals("1"))
		{
			query = "\n select cast( tct.pk_seq as varchar) [Tên Chỉ Tiêu], ddkd.pk_seq, ddkd.mafast as [MÃ NV], ddkd.Ten as [NHÂN VIÊN], [dbo].[GetTenNPP](DDKD.PK_SEQ) [Nhà Phân Phối] " +
					"\n ,ct.ChiTieu as [Chỉ Tiêu],ct.ChiTieu as [Chỉ Tiêu Upload], isnull(td.thucdat,0) [Thực đạt] , dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) [Tỷ lệ đạt], dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) [Tỷ lệ đạt Upload]" +
					"\n ,case when ISNULL(tct.LOAIDS,0) = 1 then  isnull(td.DoanhSo,0) * muc.thuong / 100.0 else   muc.thuong end [Thưởng]  " +
					"\n from ChiTieuNhanVien_DDKD ct " +
					"\n inner join DaiDienKinhDoanh ddkd on ct.NhanVien_FK = ddkd.pk_seq " +
					"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
					"\n outer apply [dbo].[ufn_KPI_DDKD]('"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"',ct.tctct_fk,ct.NhanVien_FK) td   " +
					"\n outer apply " +
					"\n ( " +
					"\n 	select max(thuong)thuong " +
					"\n 	from TieuChiThuong_ChiTiet_MucThuong x " +
					"\n 	where x.TCTCT_FK = tct.PK_SEQ " +
					"\n		and x.TU <= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " +
					"\n		and x.DEN >= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " + 
					"\n ) muc " +
					"\n where ct.chitieu > '0' and ct.tctct_fk in ("+tct_fks+") ";
			if(obj.getDdkd().length()  > 0)
			{ query += "\n and ct.NhanVien_FK = '"+obj.getDdkd()+"' "; }
			if (obj.getnppId() != null && obj.getnppId().length() > 0)
			{ query += "\n and exists ( select 1 from daidienkinhdoanh_npp ddnpp where ddnpp.ddkd_fk = ddkd.pk_seq and ddnpp.npp_fk = '"+ obj.getnppId() +"' ) "; }
				//query +=  "\n and ct.NhanVien_FK in ("+quyen+") ";
		}
		else if(obj.getLoaiNv().equals("2"))
		{
			query = "\n select cast( tct.pk_seq as varchar) [Tên Chỉ Tiêu], ddkd.pk_seq, ddkd.mafast as [MÃ NV], ddkd.Ten as [NHÂN VIÊN], [dbo].[GetTenNPP](DDKD.PK_SEQ) [Nhà Phân Phối] " +
					"\n ,ct.ChiTieu as [Chỉ Tiêu],ct.ChiTieuUpload [Chỉ Tiêu Upload], isnull(td.thucdat,0) [Thực đạt] , dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) [Tỷ lệ đạt], dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieuUpload as float) ) [Tỷ lệ đạt Upload]" +
					"\n ,case when ISNULL(tct.LOAIDS,0) = 1 then  isnull(td.DoanhSo,0) * muc.thuong / 100.0 else   muc.thuong end [Thưởng]  " +
					"\n from ChiTieuNhanVien_GSBH ct " +
					"\n inner join giamsatbanhang ddkd on ct.NhanVien_FK = ddkd.pk_seq " +
					"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
					"\n outer apply [dbo].[ufn_KPI_GSBH]('"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"',ct.tctct_fk,ct.NhanVien_FK) td   " +
					"\n outer apply " +
					"\n ( " +
					"\n 	select max(thuong)thuong " +
					"\n 	from TieuChiThuong_ChiTiet_MucThuong x " +
					"\n 	where x.TCTCT_FK = tct.PK_SEQ " +
					"\n		and x.TU <= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " +
					"\n		and x.DEN >= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " + 
					"\n ) muc " +
					"\n where ct.chitieu > '0' and ct.tctct_fk in ("+tct_fks+") ";
			if(obj.getDdkd().length()  > 0)
			{ query += "\n and ct.NhanVien_FK = '"+obj.getDdkd()+"' "; }
			if (obj.getnppId() != null && obj.getnppId().length() > 0)
			{ query += "\n and exists ( select 1 from NHAPP_GIAMSATBH ddnpp where ddnpp.gsbh_fk = ddkd.pk_seq and ddnpp.npp_fk = '"+ obj.getnppId() +"' ) "; }
				//query +=  "\n and ct.NhanVien_FK in ("+quyen+") ";
		}
		else if(obj.getLoaiNv().equals("3"))
		{
			query = "\n select cast( tct.pk_seq as varchar) [Tên Chỉ Tiêu], ddkd.pk_seq, ddkd.mafast as [MÃ NV], ddkd.Ten as [NHÂN VIÊN], [dbo].[GetTenNPP](DDKD.PK_SEQ) [Nhà Phân Phối] " +
					"\n ,ct.ChiTieu as [Chỉ Tiêu],ct.ChiTieuUpload [Chỉ Tiêu Upload], isnull(td.thucdat,0) [Thực đạt] , dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) [Tỷ lệ đạt], dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieuUpload as float) ) [Tỷ lệ đạt Upload]" +
					"\n ,case when ISNULL(tct.LOAIDS,0) = 1 then  isnull(td.DoanhSo,0) * muc.thuong / 100.0 else   muc.thuong end [Thưởng]  " +
					"\n from ChiTieuNhanVien_ASM ct " +
					"\n inner join asm ddkd on ct.NhanVien_FK = ddkd.pk_seq " +
					"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
					"\n outer apply [dbo].[ufn_KPI_ASM]('"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"',ct.tctct_fk,ct.NhanVien_FK) td   " +
					"\n outer apply " +
					"\n ( " +
					"\n 	select max(thuong)thuong " +
					"\n 	from TieuChiThuong_ChiTiet_MucThuong x " +
					"\n 	where x.TCTCT_FK = tct.PK_SEQ " +
					"\n		and x.TU <= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " +
					"\n		and x.DEN >= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " + 
					"\n ) muc " +
					"\n where ct.chitieu > '0' and ct.tctct_fk in ("+tct_fks+") ";
				if(obj.getDdkd().length()  > 0)
				{ query += "\n and ct.NhanVien_FK = '"+obj.getDdkd()+"' "; }
				
				//query +=  "\n and ct.NhanVien_FK in ("+quyen+") ";
			}
		/*else if(obj.getLoaiNv().equals("5"))
		{
			//String nspid = obj.getNspId().length()>0?obj.getNspId(): "null";
			query = "\n select cast( tct.pk_seq as varchar) [Tên Chỉ Tiêu], '' pk_seq, '' as [MÃ NV], '' as [NVBH], DDKD.TEN [Nhà Phân Phối] " +
					"\n ,ct.ChiTieu as [Chỉ Tiêu], isnull(td.thucdat,0) [Thực đạt] , dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float) ) [Tỷ lệ đạt]" +
					"\n ,case when ISNULL(tct.LOAIDS,0) = 1 then  isnull(td.DoanhSo,0) * muc.thuong / 100.0 else   muc.thuong end [Thưởng]  " +
					"\n from ChiTieuNhanVien_NPP ct " +
					"\n inner join NHAPHANPHOI ddkd on ct.NhanVien_FK = ddkd.pk_seq " +
					"\n inner join tieuchithuong_chitiet tct on ct.tctct_fk = tct.pk_seq  " +
					"\n outer apply [dbo].[ufn_KPI_NPP_TEST]('"+obj.getYear() +"-"+ obj.getMonth() +"-01"+"',ct.tctct_fk, tct.NHOMSP_FK, ct.NhanVien_FK) td   " +
					"\n outer apply " +
					"\n ( " +
					"\n 	select max(thuong)thuong " +
					"\n 	from TieuChiThuong_ChiTiet_MucThuong x " +
					"\n 	where x.TCTCT_FK = tct.PK_SEQ " +
					"\n		and x.TU <= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " +
					"\n		and x.DEN >= 100* dbo.PhepChia(isnull(td.thucdat,0),cast(ct.ChiTieu as float)) " + 
					"\n ) muc " +
					"\n where ct.chitieu > '0' and ct.tctct_fk in ("+tct_fks+") ";
			if (obj.getnppId() != null && obj.getnppId().length() > 0)
			{ query += "\n and ddkd.PK_SEQ in ( "+obj.getnppId()+" )  "; }
		}*/

		String[] _diengiai = null;
		String text = "";
		String temp = "";
		//System.out.println("diengiai : "+ diengiai);
		_diengiai = diengiai.split("__");
		String[] tieuchithuong = new String[]{"Chỉ Tiêu","Chỉ Tiêu Upload","Thực Đạt","Tỷ Lệ Đạt","Tỷ Lệ Đạt Upload","Thưởng"}; 

		ArrayList<String> temp_arr = new ArrayList<String>();
		if (_diengiai != null && _diengiai.length > 0) {
			for (int i = 0; i<_diengiai.length; i++) {
				for (int ii = 0; ii < tieuchithuong.length; ii ++) {
					text += ","+"["+_diengiai[i]+"_"+tieuchithuong[ii]+"]";
					temp = "["+_diengiai[i]+"_"+tieuchithuong[ii]+"]";
					
					/*text += ","+"["+tieuchithuong[ii]+"]";
					temp = "["+tieuchithuong[ii]+"]";*/
					
					temp_arr.add(temp);
				}
			}

			if (temp_arr != null) {
				obj.setArr_text_baocaoSR(temp_arr);
			}
		}

		if (text != null && text.length() > 0 ) {
			text = text.substring(1);
			obj.setText_baocaoSR(text);
		}

		String header_query = "\n with temp as (";
		String footer_query = "\n ) " +
		"\n select Row_number() OVER ( order by ( [Mã NV] ) asc)[STT],[Nhà Phân Phối],[Mã NV],[NHÂN VIÊN], " +
		"\n "+text+", " +
		"\n pk_seq " +
		"\n from  " +
		"\n (" +
		"\n 	select [Nhà Phân Phối],[Mã NV],[NHÂN VIÊN],[Tên Chỉ Tiêu]+'_'+col col,value,pk_seq from " +
		"\n 	( " +
		"\n 		select [Mã NV],[NHÂN VIÊN],[Nhà Phân Phối],[Tên Chỉ Tiêu],[Chỉ Tiêu],[Chỉ Tiêu Upload],[Thực Đạt],[Tỷ Lệ Đạt],[Tỷ Lệ Đạt Upload],[Thưởng],pk_seq from temp " +
		"\n		) rt  " +
		"\n 	unpivot (value for col in ([Chỉ Tiêu],[Chỉ Tiêu Upload],[Thực Đạt],[Tỷ Lệ Đạt],[Tỷ Lệ Đạt Upload],[Thưởng]) ) unpiv  " +
		"\n ) tp " +
		"\n pivot ( SUM(value) FOR col in ("+text+")) piv ";

		String abc = header_query + query + footer_query;
		System.out.println("Query báo cáo Template mới: "+abc);

		return abc;

	}


}
