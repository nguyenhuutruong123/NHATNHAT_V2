package geso.dms.distributor.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;

import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.PivotFieldType;
import com.aspose.cells.PivotTable;
import com.aspose.cells.PivotTables;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


public class AdjustInventoryReportnpp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	public String setQuery(HttpServletRequest request,String st)
	{	
		String query =  "\n SELECT KHO.TEN AS TENKHO,DCTK.LYDODC, " +
						"\n	KBH.PK_SEQ AS KBHID,KBH.TEN AS KENH,VUNG.PK_SEQ AS VUNGID,VUNG.TEN AS VUNG,KV.PK_SEQ AS KVID,KV.TEN AS KHUVUC," +
						"\n	ISNULL(QH.PK_SEQ,0) AS QHID,ISNULL(QH.TEN,'') AS QUANHUYEN,NPP.PK_SEQ AS NPPID,NPP.TEN AS NPP," +
						"\n	DCTK.NGAYDC AS NGAY,SP.MA, SP.TEN,SUM(CONVERT(NUMERIC(18,0),DCTK_SP.TONHIENTAI)) AS TONDAU," +
						"\n	SUM(CONVERT(NUMERIC(18,0),DCTK_SP.DIEUCHINH)) AS DIEUCHINH,SUM(CONVERT(NUMERIC(18,0),DCTK_SP.TONMOI)) AS TONCUOI " +
						"\n FROM DIEUCHINHTONKHO DCTK" +
						"\n	INNER JOIN DIEUCHINHTONKHO_SP DCTK_SP ON DCTK_SP.DIEUCHINHTONKHO_FK = DCTK.PK_SEQ" +
						"\n	INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = DCTK.KBH_FK" +
						"\n	INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = DCTK.NPP_FK" +
						"\n	INNER JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK" +
						"\n	INNER JOIN VUNG VUNG ON VUNG.PK_SEQ = KV.VUNG_FK" +
						"\n	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DCTK_SP.SANPHAM_FK" +
						"\n  INNER JOIN KHO ON KHO.PK_SEQ=DCTK.KHO_FK  "+
						"\n	LEFT  JOIN QUANHUYEN QH on QH.PK_SEQ = NPP.QUANHUYEN_FK " +
					
						"\n WHERE DCTK.TRANGTHAI = '1' AND  DCTK_SP.DIEUCHINH <>0 " + st +
						"\n GROUP BY KBH.PK_SEQ,DCTK.LYDODC, KBH.TEN, VUNG.PK_SEQ, VUNG.TEN, KV.PK_SEQ, KV.TEN, QH.PK_SEQ, QH.TEN, NPP.PK_SEQ, NPP.TEN, SP.MA, SP.TEN, DCTK.NGAYDC,KHO.TEN ";
		System.out.println("BCao :"+query);
		return query;
		
	}
	
   
    public AdjustInventoryReportnpp() {
        super();
        
    }

	
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
//	    System.out.println(userId);
	    obj.setuserId(userId);
	    obj.init();	    
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Distributor/AdjustInventoryReport.jsp";
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
	    String userId= util.antiSQLInspection(request.getParameter("userId"));
	    if(userId ==null)
	    	userId ="";
	    obj.setuserId(userId);
	    String userTen = (String) session.getAttribute("userTen"); 
        obj.setuserTen(userTen);
   	 	String nppId=util.antiSQLInspection(request.getParameter("nppId"));
   	 	if(nppId ==null) 
   	 		nppId ="";
   	 	obj.setnppId(nppId);
   	 
//	     String nppTen=request.getParameter("nppTen");
	   	 
	   	 String kenhId=util.antiSQLInspection(request.getParameter("kenhId"));
	   	 if(kenhId == null)
	   		 kenhId ="";
	   	 obj.setkenhId(kenhId);
	   	 
	   	 String dvkdId=util.antiSQLInspection(request.getParameter("dvkdId"));
	   	 if(dvkdId == null)
	   		 dvkdId ="";
	   	 obj.setdvkdId(dvkdId);
	   	 
	   	 String nhanhangId=util.antiSQLInspection(request.getParameter("nhanhangId"));
	   	 if(nhanhangId ==null)
	   		 nhanhangId = "";
	   	 obj.setnhanhangId(nhanhangId);
	   	 
	   	 String chungloaiId=util.antiSQLInspection(request.getParameter("chungloaiId"));
	   	 if(chungloaiId==null)
	   		chungloaiId = "";
	   	 obj.setchungloaiId(chungloaiId);
	   	 
	   	 String tungay=util.antiSQLInspection(request.getParameter("Sdays"));
	   	 if(tungay ==null)
	   		 tungay ="";
	   	 obj.settungay(tungay);
	   	 
	   	 String denngay=util.antiSQLInspection(request.getParameter("Edays"));
	   	 if(denngay == null)
	   		 denngay ="";
	   	 obj.setdenngay(denngay);
	   	 
	    	 String vungId=util.antiSQLInspection(request.getParameter("vungId"));
	   	 if(vungId ==null)
	   		 vungId = "";
	   	 obj.setvungId(vungId);
	   	 
	   	 String khuvucId=util.antiSQLInspection(request.getParameter("khuvucId"));
	   	 if(khuvucId == null)
	   		 khuvucId ="";
	   	 obj.setkhuvucId(khuvucId);
	   	 
	   	 String sanphamId=util.antiSQLInspection(request.getParameter("sanphamId"));
	   	 if(sanphamId == null)
	   		 sanphamId ="";
	   	 obj.setsanphamId(sanphamId);
	
	   	 String []fieldsHien = request.getParameterValues("fieldsHien");
		 obj.setFieldShow(fieldsHien);
		 
		 String [] fieldsAn =request.getParameterValues("fieldsAn");
			obj.setFieldHidden(fieldsAn)  ;
		
		 
		geso.dms.distributor.util.Utility Ult = new geso.dms.distributor.util.Utility();
		 nppId = Ult.getIdNhapp(userId);
		 obj.setnppId(nppId);
		 obj.setnppTen(Ult.getTenNhaPP());
		 String sql ="";
		 if(tungay.length()>0)sql =sql +" and DCTK.NGAYDC >='"+ tungay +"'";
		 if(denngay.length()>0) sql = sql +" and  DCTK.NGAYDC <='"+ denngay +"'";
		 if(kenhId.length()>0) sql = sql +" and kbh.pk_seq ='"+ kenhId +"'";
		 if(dvkdId.length()>0) sql = sql +" and sp.dvkd_fk ='"+ dvkdId +"'";
		 if(nppId.length()>0)sql =sql +" and npp.pk_seq ='"+ nppId +"'";
		 if(nhanhangId.length()>0) sql = sql +" and nhan.pk_seq ='"+ nhanhangId +"'";
		 if(chungloaiId.length()>0)sql = sql +" and chungloai.pk_seq ='"+ chungloaiId +"'";
		 if(sanphamId.length()>0) sql = sql + " and sp.pk_seq = '"+ sanphamId +"'";
		 String action=request.getParameter("action");
		 if(tungay.length()>0 && denngay.length()>0)
		 {
				 if(action.equals("tao"))
				 {
						try{
							request.setCharacterEncoding("utf-8");
							response.setCharacterEncoding("utf-8");			
							
							/*response.setContentType("application/vnd.ms-excel");
					        response.setHeader("Content-Disposition", "attachment; filename=AdjustInventoryReport.xls");*/
							
							   response.setContentType("application/xlsm");
								response.setHeader("Content-Disposition", "attachment; filename=AdjustInventoryReport(NPP).xlsm");

					        OutputStream out = response.getOutputStream();
					        
					        String query =  setQuery(request,sql);       
					        CreatePivotTable(out, response, request,obj,query);	// Create PivotTable 
					        return;
					        
						}catch(Exception ex){
							ex.printStackTrace();
							 obj.setMsg("B???n kh??ng t???o ???????c b??o c??o trong th???i gian n??y");
						}
				 }
			 
		 }
		 else{
			 obj.setMsg("B???n ph???i ch???n ng??y b???t ?????u v?? ng??y k???t th??c");
		 }
		 	obj.init();	    
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Distributor/AdjustInventoryReport.jsp";
			response.sendRedirect(nextJSP);
		 
	}

	private void CreatePivotTable(OutputStream out,HttpServletResponse response, HttpServletRequest request,IStockintransit obj, String query ) throws Exception 
	{
		try 
		{
			FileInputStream fstream;
			
			String streamstr = getServletContext().getInitParameter("path") + "\\AdjustInventoryReport(NPP).xlsm";
			fstream = new FileInputStream(streamstr);
			//fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\AdjustInventoryReport(NPP).xlsm");
			//fstream = new FileInputStream("D:\\Best Stable\\Best\\WebContent\\pages\\Templates\\AdjustInventoryReport(NPP).xlsm");	

			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			CreateHeader(workbook,obj); 
			FillData(workbook,obj,query);			
			workbook.save(out);
			fstream.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}
	
	}
	private void FillData(Workbook workbook,IStockintransit obj, String query)throws Exception {
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();
		
		for(int i=0;i<10;++i){
	    	cells.setColumnWidth(i, 15.0f);	    	
	    }	
		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		int index = 2;
	    Cell cell = null;	    
		while (rs.next()) {
			int column = 26;
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("KENH"));			
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("NGAY"));
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("MA"));
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("TEN"));
			cell = cells.getCell(index-1,column++);		cell.setValue(Float.parseFloat(rs.getString("DIEUCHINH")));
					
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("NPPID"));
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("TENKHO"));
			cell = cells.getCell(index-1,column++);		cell.setValue(rs.getString("LYDODC"));
			index++;			
		}
	
		ReportAPI.setHidden(workbook,11);
		
	}
	private void CreateHeader(Workbook workbook, IStockintransit obj)throws Exception {
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);	    
	    worksheet.setName("Sheet1");
	    
	    Cells cells = worksheet.getCells();	    
	    cells.setRowHeight(0, 20.0f);	    
	    Cell cell = cells.getCell("A1");	
	    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Ki???m kho");
	    cell = cells.getCell("A2");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"T??? ng??y : " + obj.gettungay() + "   ?????n ng??y : " + obj.getdenngay());
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ng??y t???o b??o c??o : " + obj.getDateTime());
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"???????c t???o b???i : " + obj.getuserTen());     
	    
	    int column = 26;
	    cell =cells.getCell(0,column++);			cell.setValue("Kenh");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);		cell.setValue("Ngay Dieu Chinh");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);		cell.setValue("SKU");		ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);		cell.setValue("Ten San Pham");			ReportAPI.setCellHeader(cell);
		cell =cells.getCell(0,column++);		cell.setValue("Dieu Chinh");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell(0,column++);	cell.setValue("Ma CN/DT");				ReportAPI.setCellHeader(cell);	
		cell = cells.getCell(0,column++);	cell.setValue("KHO");				ReportAPI.setCellHeader(cell);
		cell =cells.getCell(0,column++);	cell.setValue("Ly Do Dieu Chinh");				ReportAPI.setCellHeader(cell);
	}

}
