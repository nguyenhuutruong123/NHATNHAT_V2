package geso.dms.center.servlets.daidienkinhdoanh;

import geso.dms.center.beans.daidienkinhdoanh.*;
import geso.dms.center.beans.daidienkinhdoanh.imp.*;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Row;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.Vector;

public class DaidienkinhdoanhSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
	private static final long serialVersionUID = 1L;
	Utility bodau=new Utility();
   
    public DaidienkinhdoanhSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IDaidienkinhdoanhList obj = new DaidienkinhdoanhList();
	    
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
	    PrintWriter out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);

	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    
	    String ddkdId = util.getId(querystring);
	    
	    String isPG = util.antiSQLInspection(request.getParameter("isPG"));
	    if(isPG == null) isPG = "0";
	    obj.setIsPG(isPG);
	    
	    System.out.println("isPG :"+isPG);
	    if (action.equals("delete"))
	    { 
	    	Delete(ddkdId,obj);
	    	//out.print(ddkdId);
	    }
	    obj.setRequestObj(request);
	    obj.setUserId(userId);
	    obj.setIsPG(isPG);
	    //System.out.println("user iad 1: "+userId);
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanh.jsp";
		response.sendRedirect(nextJSP);
	}

	String query1="";
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IDaidienkinhdoanhList obj = new DaidienkinhdoanhList();
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	   
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    String userTen = util.antiSQLInspection(request.getParameter("userTen"));
	    String action = request.getParameter("action");
	    String isPG = util.antiSQLInspection(request.getParameter("isPG"));
	    if(isPG == null) isPG = "0";
	    obj.setIsPG(isPG);
	      //----
	    obj.setUserId(userId);
	    obj.setRequestObj(request);
	    obj.setIsPG(isPG);
	    String search = "";
	    String nextJSP = "";
	    if(action.equals("excel"))
	    {
	    	try{
    		response.setContentType("application/xlsm");
    		if(isPG.equals("0"))
    			response.setHeader("Content-Disposition", "attachment; filename=DanhSachNVBH(tt).xlsm");
    		else
    			response.setHeader("Content-Disposition", "attachment; filename=DanhSachPG(tt).xlsm");
    		OutputStream out1 = response.getOutputStream();
    		search = getSearchQuery(request,obj);
			ExportToExcel(out1, obj, search, userTen);
			out1.close();
			return;
	    	}
	    	catch (Exception e) {
	    		e.printStackTrace();
				//System.out.println("Không thể xuất excel " + e.getMessage());
			}
	    }
	    if (action.equals("new"))
	    {
	    	// Empty Bean for distributor
	    	IDaidienkinhdoanh ddkdBean = (IDaidienkinhdoanh) new Daidienkinhdoanh("");
	    	ddkdBean.setUserId(userId);
	    	ddkdBean.setIsPG(isPG);
	    	// Save Data into session
	    	session.setAttribute("ddkdBean", ddkdBean);
    		
    		nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhNew.jsp";  		
	    }
	    else if (action.equals("search")){	    
	    	search = getSearchQuery(request,obj);
			obj.setUserId(userId);
			
    		session.setAttribute("abc", search);
	    		
    		nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanh.jsp";
	    }
	    else{
		    
		    //phantrang
		    
	    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");

	    	//------------------------
	    	
	    	search = getSearchQuery(request, obj);

	    	nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanh.jsp";
	    }
	    
	    obj.init(search);
	   
    	session.setAttribute("obj", obj);  	
		session.setAttribute("userId", userId);
    		
		response.sendRedirect(nextJSP); 
	}
	
	private String getSearchQuery(HttpServletRequest request,IDaidienkinhdoanhList obj)
	{	
		
		Utility util = new Utility();
		
		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
    	if (maFAST == null)
    		maFAST = "";    	
    	obj.setMaFAST(maFAST);
		
		String ten = util.antiSQLInspection(request.getParameter("ddkdTen"));
    	if ( ten == null)
    		ten = "";
    	obj.setTen(ten);
    	
    	String sodienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
    	if (sodienthoai == null)
    		sodienthoai = "";    	
    	obj.setSodienthoai(sodienthoai);
    	
    	String kbhId = util.antiSQLInspection(request.getParameter("KenhBanHang"));
    	if (kbhId == null)
    		kbhId = "";    	
    	obj.setKbhId(kbhId);
    	
    	String gsbhId = util.antiSQLInspection(request.getParameter("GSBanHang"));
    	if (gsbhId == null)
    		gsbhId = "";    	
    	obj.setGsbhId(gsbhId);
    	
    	String nppId = util.antiSQLInspection(request.getParameter("NhaPhanPhoi"));
    	if (nppId == null)
    		nppId = "";    	
    	obj.setNppId(nppId);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
    	if (trangthai == null)
    		trangthai = "";    	
	
    	if (trangthai.equals("2"))
    		trangthai = "";
    	
    	obj.setTrangthai(trangthai);
    	
    	String isPG = util.antiSQLInspection(request.getParameter("isPG"));
	    if(isPG == null) isPG = "0";
	    obj.setIsPG(isPG);
    	
    	String query =  "select distinct isnull(a.mafast,'') as mafast, a.pk_seq  as id, a.ten , a.dienthoai, a.diachi, a.trangthai, a.ngaytao,   " +
						"	b.ten as nguoitao, a.ngaysua, c.ten as nguoisua,  f.ten as gsbhTen,   " +
						"	g.ten as kbhTen, a.manhanvien AS manhanvien ,isnull(tn.TEN,'') as TienNhiem,  " +
						"	isnull((select REPLACE(  " +
						"				(	SELECT npp.TEN AS [data()]  " +
						"					FROM  NHAPHANPHOI npp inner join DAIDIENKINHDOANH_NPP ddkd_npp on npp.pk_seq = ddkd_npp.npp_fk and  ddkd_npp.DDKD_FK = a.PK_SEQ  " +
						"					FOR XML PATH('p')   " +
						"			),' ',' ')  ), '') as nppTEN " +
						"from daidienkinhdoanh a inner join nhanvien b on a.nguoitao = b.pk_seq   " +
						"	 left join DAIDIENKINHDOANH tn on tn.PK_SEQ=a.DDKDTIENNHIEM  " +
					  	 " left join DAIDIENKINHDOANH_NPP ddkd_npp on a.PK_SEQ=ddkd_npp.ddkd_fk "+
						"	 inner join  nhanvien c on   a.nguoisua = c.pk_seq  " +
						"	 left join ddkd_gsbh e on a.pk_seq = e.ddkd_fk   " +
						"	 left join giamsatbanhang f on e.gsbh_fk = f.pk_seq   " +
						"	 left join  kenhbanhang g on f.kbh_fk=g.pk_seq   " +
						"where 1=1 "; //and f.pk_seq in (select gsbh_fk from nhapp_giamsatbh where NPP_FK in (ddkd_npp.NPP_FK )) ";
						
    	if (ten.length()>0)
    	{
			query = query + " and (dbo.ftBodau(a.ten) like  UPPER( N'%" + bodau.replaceAEIOU(ten) + "%') or dbo.ftBodau(a.mafast) like upper(N'%"+bodau.replaceAEIOU(ten)+"%'))";
    	}
    	
    	if (sodienthoai.length()>0)
    	{
			query = query + " and upper(a.dienthoai) like upper('%" + sodienthoai + "%')";
    	}
    	
   	
    	if (gsbhId.length()>0){
			query = query + " and e.gsbh_fk='" + gsbhId + "'";
			
    	}
    	
    	if (kbhId.length()>0){
			query = query + " and g.pk_seq = '" + kbhId + "'";
			
    	}
    	
    	if (nppId.length()>0){
			query = query + " and a.pk_seq in  ( select ddkd_fk from DAIDIENKINHDOANH_NPP where NPP_FK = '" + nppId + "' )   ";
			
    	}
    	
    	if(trangthai.length() > 0){
    		query = query + " and a.trangthai = '" + trangthai + "'";
    		
    	}
    	
    	if(maFAST.length()>0)
    	{
    		query+= " and a.maFAST like '%"+maFAST+"%' ";
    	}
    	
    	if(isPG.length()>0)
    	{
    		query+= " and isnull(a.isPG,0)="+isPG;
    	}
    	//query = query + "  order by a.ten";
    	System.out.println("Serch   "+query);
    	return query;
	}
	
	private void Delete(String id,IDaidienkinhdoanhList obj)
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
	
	private void ExportToExcel(OutputStream out,IDaidienkinhdoanhList obj, String query, String ten)throws Exception
	 {
		try{ 
			String chuoi=getServletContext().getInitParameter("path") + "\\DanhSachDDKD(tt).xlsm";
			
			FileInputStream fstream = new FileInputStream(chuoi);
			
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			CreateHeader(workbook,obj, ten);
			FillData(workbook,obj, query);
			
			workbook.save(out);	
			fstream.close();
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		
	}
	 
	private void CreateHeader(Workbook workbook,IDaidienkinhdoanhList obj, String ten) throws Exception
	{
		try {
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("Sheet1");
			Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
			Date date = calendar.getTime();
		    int day = calendar.get(Calendar.DATE);
			Cells cells = worksheet.getCells();
			cells.setColumnWidth(0, 25.0f);
			/*
			 * Style style = cells.getCell("AZ9").getStyle(); Font font = style.getFont();
			 * font.setBold(true); style.setFont(font); style.setTextWrapped(true);
			 */
			
			
			Cell cell = cells.getCell("A1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16,
					"DANH SÁCH NHÂN VIÊN BÁN HÀNG");
			
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Ngày tạo : "
					+ ReportAPI.NOW("dd/MM/yyyy : hh-mm-ss"));
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10,
					"Người tạo : " + ten);
			
			//cell = cells.getCell("A9");cell.setStyle(style);		cell.setValue("Mã RSM");
			//cell = cells.getCell("B9");cell.setStyle(style);		cell.setValue("Tên RSM");
			cell = cells.getCell("A9");
			/* cell.setStyle(style); */		cell.setValue("Mã");
			cell = cells.getCell("B9");
			/* cell.setStyle(style) */;		cell.setValue("Mã DMS");
			cell = cells.getCell("C9");
			/* cell.setStyle(style); */		cell.setValue("Họ Tên");
			cell = cells.getCell("D9");
			/* cell.setStyle(style); */		cell.setValue("Điện thoại");
			cell = cells.getCell("E9");
			/* cell.setStyle(style); */		cell.setValue("Trực thuộc");
			cell = cells.getCell("F9");
			/* cell.setStyle(style); */		cell.setValue("Cấp trên");
			cell = cells.getCell("G9");
			/* cell.setStyle(style); */		cell.setValue("Trạng thái");
			cell = cells.getCell("H9");
			/* cell.setStyle(style); */		cell.setValue("Ngày sửa");
			cell = cells.getCell("I9");
			/* cell.setStyle(style); */		cell.setValue("Người sửa");

		} catch (Exception ex) 
		{
			System.out.println(ex.toString());
			throw new Exception("Khong the tao duoc Header cho bao cao.!!!");
		}
	}
	private void FillData(Workbook workbook,IDaidienkinhdoanhList obj, String query)throws Exception
	{
		try{
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			//worksheet.setGridlinesVisible(false);
			Cells cells = worksheet.getCells();
			Style Style = cells.getCell("AZ9").getStyle();
			Style.setTextWrapped(true);
			dbutils db = new dbutils();
			ResultSet rs = db.get(query);
			Cell cell = null;
			int countRow = 10;
			for (int i = 0;i < 9; i++) {
				if (i == 4)
					cells.setColumnWidth(i, 20f);
				else cells.setColumnWidth(i, 15f);
			}
			
			while(rs.next()){
				cell = cells.getCell("A" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(rs.getString("manhanvien"));
				cell = cells.getCell("B" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(rs.getString("mafast"));
				cell = cells.getCell("C" + String.valueOf(countRow));
				cell.setStyle(Style);	cell.setValue(rs.getString("ten"));
				cell = cells.getCell("D" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(rs.getString("dienthoai"));
				cell = cells.getCell("E" + String.valueOf(countRow));
				String nppTen = rs.getString("nppTen");
				String ten = xuliString(nppTen);
				cell.setStyle(Style);		cell.setValue(ten);
				cell = cells.getCell("F" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(rs.getString("gsbhTen"));
				String trangthai = "Hoạt động ";
				if(rs.getString("trangthai").equals("0"))
					trangthai = "Ngưng hoạt động ";
				cell = cells.getCell("G" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(trangthai);
				cell = cells.getCell("H" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(rs.getString("ngaysua"));
				cell = cells.getCell("I" + String.valueOf(countRow));
				cell.setStyle(Style);		cell.setValue(rs.getString("nguoitao"));
				++countRow;
			}
			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}
			//ReportAPI.setHidden(workbook,28);
	
		}catch(Exception ex){
			
			System.out.println("Errrorr : "+ex.toString());
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	public String xuliString(String nppTen) {
		String result = "";
		String array[] = nppTen.split("<p>");
		for (String temp : array) 
			result = result +temp;
		String array1[] = result.split("</p>");
		result = "";
		for (String temp1 : array1)
			result = result + "; " + temp1;
		result = result.replaceFirst("; ", "");
 		return result;
	}
	public void setQuery(IStockintransit obj,HttpServletRequest request)
	{
		Utility util = new Utility();
		obj.setuserTen(util.antiSQLInspection(request.getParameter("userTen")));

		query1 =
			"	SELECT 	ISNULL(V.TEN,'') AS VUNG ,ISNULL(KV.TEN,'') AS KHUVUC,ASM.MAFAST AS ASMMA,ASM.TEN AS ASMTEN, " +  
			"	F.MANHANVIEN AS GSBHMA,F.TEN AS GSBHTEN,NPP.MAFAST AS NPPMA,A.mafast AS DDKDMA,A.TEN AS DDKDTEN,1 AS DATA, " +  
			"	A.DIENTHOAI,A.DIACHI,A.EMAIL,A.PHANTRAMCHUYEN,TN.MANHANVIEN AS DDTIENNHIEMMA,TN.TEN AS DDTIENNHIEMTEN,A.SONAMLAMVIEC,A.NGAYVAOCONGTY,A.LOAIHD,A.NGAYKYHD,A.TEAMLEADER,A.SOTAIKHOAN,A.GHICHU, " +  
			"	A.HDLD,A.NGAYKETTHUCHD,A.TRANGTHAI," +
			"	STUFF((SELECT ', '+ TEN FROM NHAPHANPHOI aa INNER JOIN daidienkinhdoanh_NPP b on aa.pk_seq = b.npp_fk WHERE b.ddkd_fk = a.pk_seq FOR XML PATH ('')), 1, 2, '')NPPTEN \r\n" + 
			"	--BM.TEN AS BMTEN,BM.MAFAST AS BMMA \r\n"+
			"FROM DAIDIENKINHDOANH A 	\r\n" + 
			"INNER JOIN NHANVIEN B ON A.NGUOITAO = B.PK_SEQ 	\r\n" + 
			"LEFT JOIN DAIDIENKINHDOANH TN ON TN.PK_SEQ=A.DDKDTIENNHIEM	\r\n" + 
			"INNER JOIN  NHANVIEN C ON   A.NGUOISUA = C.PK_SEQ 	\r\n" + 
			"left join nhaphanphoi npp on npp.pk_seq in (select npp_fk from daidienkinhdoanh_npp where ddkd_fk = a.pk_seq) \n"+
			"LEFT JOIN DDKD_GSBH E ON A.PK_SEQ = E.DDKD_FK  	\r\n" + 
			"LEFT JOIN GIAMSATBANHANG F ON E.GSBH_FK = F.PK_SEQ\r\n" + 
			"LEFT JOIN gsbh_khuvuc gkv on gkv.gsbh_fk = f.pk_seq	\r\n" + 
			"LEFT JOIN KHUVUC KV ON KV.PK_SEQ=GKV.KHUVUC_FK 	\r\n" + 
			"LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK		\r\n" + 
			"LEFT JOIN ASM_KHUVUC ON ASM_KHUVUC.KHUVUC_FK=KV.PK_SEQ	\r\n" + 
			"LEFT JOIN ASM ON ASM.PK_SEQ=ASM_KHUVUC.ASM_FK		\r\n" + 
			"--LEFT JOIN BM_CHINHANH ON BM_CHINHANH.VUNG_FK=V.PK_SEQ		\r\n" + 
			"--LEFT JOIN BM ON BM.PK_SEQ=BM_CHINHANH.BM_FK		\r\n" + 
			"LEFT JOIN  KENHBANHANG G ON F.KBH_FK=G.PK_SEQ  \r\n"+ 
			" WHERE   \r\n"+
			" ASM.TRANGTHAI=1  AND F.TRANGTHAI=1  \r\n" +
			"-- AND ASM_KHUVUC.NGAYBATDAU<='"+getDateTime()+"' AND ASM_KHUVUC.NGAYKETTHUC>='"+getDateTime()+"' \r\n"+
			"-- AND BM_CHINHANH.NGAYBATDAU<='"+getDateTime()+"' AND BM_CHINHANH.NGAYKETTHUC>='"+getDateTime()+"' ";

		
		String tenNVBH = util.antiSQLInspection(request.getParameter("ddkdTen"));
		if(tenNVBH.length() > 0 ) query1 += " and A.TIMKIEM like upper(dbo.ftBoDau(N'%" +tenNVBH.trim() + "%')) ";
		
		String soDT = util.antiSQLInspection(request.getParameter("DienThoai"));
		if(soDT.length() > 0) query1 += " AND A.DIENTHOAI = '" + soDT.trim() + "'";
		
		String kenhBH = util.antiSQLInspection(request.getParameter("KenhBanHang"));
		if(kenhBH.length() > 0 ) query1 += " AND G.PK_SEQ = '" + kenhBH + "'";
		
		String GSBH = util.antiSQLInspection(request.getParameter("GSBanHang"));
		if(GSBH.length() > 0 ) query1 += " AND F.PK_SEQ = '" + GSBH + "'";
		
		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));
		if(!trangthai.equals("2")) query1 += " AND A.TRANGTHAI = '" + trangthai + "'";
		
		String npp = util.antiSQLInspection(request.getParameter("NhaPhanPhoi"));
		if(npp.length() > 0 ) query1 += " AND A.NPP_FK = '" + npp + "'";
		
		System.out.println("Xuất NHÂN VIÊN BÁN HÀNG : " + query1);
	}
	public String getQuery()
	{
		return this.query1;
	}
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
      
        return dateFormat.format(date);	
	}

}




