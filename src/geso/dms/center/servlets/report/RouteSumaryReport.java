package geso.dms.center.servlets.report;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.beans.routesumaryreport.IRouteSumaryReport;
import geso.dms.center.beans.routesumaryreport.imp.*;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;


import com.aspose.cells.Font;
import com.aspose.cells.HorizontalAlignmentType;


import com.aspose.cells.Color;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


/**
 * Servlet implementation class RouteSumaryReport
 */
public class RouteSumaryReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private String setQuery(String NPPID) {
		String query = "SELECT DISTINCT"
				+ "	NPP.PK_SEQ AS NPPID, NPP.TEN AS NPP,"
				+ "	DDKD.PK_SEQ AS DDKDID, DDKD.TEN AS DDKD,"
				+ "	LCH.PK_SEQ AS LCHID, LCH.LOAI,"
				+ "	isnull(DSTHANG.DSTHANG,0) AS DSTHANG,"
				+ "	TONGLCH.SOCUAHIEU AS CUAHIEU,"
				+ "	(T2_1.SOCUAHIEU + T3_1.SOCUAHIEU + T4_1.SOCUAHIEU + T5_1.SOCUAHIEU + T6_1.SOCUAHIEU + T7_1.SOCUAHIEU)*4 AS GHETHAMTHANG,"
				+ "	T2_1.SOCUAHIEU AS T2_1,"
				+ "	T3_1.SOCUAHIEU AS T3_1,"
				+ "	T4_1.SOCUAHIEU AS T4_1,"
				+ "	T5_1.SOCUAHIEU AS T5_1,"
				+ "	T6_1.SOCUAHIEU AS T6_1,"
				+ "	T7_1.SOCUAHIEU AS T7_1 "
				+ " FROM TUYENBANHANG TBH "
				+ " 	INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = TBH.NPP_FK"
				+ " 	INNER JOIN DAIDIENKINHDOANH DDKD ON TBH.DDKD_FK = DDKD.PK_SEQ"
				+ "	 INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.TBH_FK = TBH.PK_SEQ"
				+ "	 INNER JOIN KHACHHANG KH ON KH.PK_SEQ = KH_TBH.KHACHHANG_FK"
				+ "	 INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "	 left JOIN "
				+ "	 ("
				+ "		SELECT "
				+ "			DH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			SUM(DH_SP.SOLUONG*DH_SP.GIAMUA-DH_SP.CHIETKHAU) AS DSTHANG"
				+ "		FROM DONHANG_SANPHAM DH_SP"
				+ "		INNER JOIN DONHANG DH ON DH.PK_SEQ = DH_SP.DONHANG_FK"
				+ "		INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = DH.KHACHHANG_FK"
				+ "		INNER JOIN KHACHHANG KH ON KH.PK_SEQ = DH.KHACHHANG_FK"
				+ "		INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "		INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "		INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "		WHERE DH.NGAYNHAP LIKE '"+this.getDateTime()+"%' AND DH.TRANGTHAI='1' and dh.npp_fk='"+NPPID+"' "
				+ "			  AND DH.KHACHHANG_FK IN("
				+ "						SELECT	KH.PK_SEQ"
				+ "						FROM KHACHHANG KH"
				+ "							INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "						)"
				+ "		GROUP BY DH.NPP_FK,  DDKD.PK_SEQ, LCH.PK_SEQ"
				+ " 	)DSTHANG ON DSTHANG.NPPID = NPP.PK_SEQ AND DSTHANG.DDKDID = DDKD.PK_SEQ AND DSTHANG.LCHID = LCH.PK_SEQ"
				+ "	 INNER JOIN"
				+ "	 ("
				+ "		SELECT"
				+ "			KH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "		FROM KHACHHANG KH"
				+ "		INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "		INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "		INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "		INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ  where kh.npp_fk='"+NPPID+"'"	
				+ "		GROUP BY DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK  "
				+ "	 )TONGLCH ON TONGLCH.NPPID = NPP.PK_SEQ AND TONGLCH.DDKDID = DDKD.PK_SEQ AND TONGLCH.LCHID = LCH.PK_SEQ"
				+ "	 INNER JOIN"
				+ "	 (		SELECT"
				+ "				TBH.DIENGIAI AS TBH,"
				+ "				KH.NPP_FK AS NPPID,"
				+ "				DDKD.PK_SEQ AS DDKDID,"
				+ "				LCH.PK_SEQ AS LCHID,"
				+ "				COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "			FROM KHACHHANG KH"
				+ "				INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "				INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "				INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "				INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "			WHERE tbh.npp_fk='"+NPPID+"'  and TBH.NGAYID='2' "
						/*"AND KH_TBH.TANSO IN ('1 tuan / 1 lan', '2 tuan / 1 lan-le' )"*/
				+ "			GROUP BY TBH.PK_SEQ, TBH.DIENGIAI, DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK"
				+ "	 )T2_1 ON T2_1.NPPID = NPP.PK_SEQ AND T2_1.DDKDID = DDKD.PK_SEQ AND T2_1.LCHID = LCH.PK_SEQ"
				+ " 	INNER JOIN"
				+ "	 ("
				+ "		SELECT"
				+ "			TBH.DIENGIAI AS TBH,"
				+ "			KH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "		FROM KHACHHANG KH"
				+ "			INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "			INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "			INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "			INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "		WHERE tbh.npp_fk='"+NPPID+"'  and TBH.NGAYID='3' "
						/*"AND KH_TBH.TANSO IN ('1 tuan / 1 lan', '2 tuan / 1 lan-le' )"*/
				+ "		GROUP BY TBH.PK_SEQ, TBH.DIENGIAI, DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK"
				+ "	 )T3_1 ON T3_1.NPPID = NPP.PK_SEQ AND T3_1.DDKDID = DDKD.PK_SEQ AND T3_1.LCHID = LCH.PK_SEQ"
				+ "	 INNER JOIN"
				+ "	 ("
				+ "		SELECT"
				+ "			TBH.DIENGIAI AS TBH,"
				+ "			KH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "		FROM KHACHHANG KH"
				+ "			INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "			INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "			INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "			INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "		WHERE tbh.npp_fk='"+NPPID+"'  and  TBH.NGAYID='4' "
						/*"AND KH_TBH.TANSO IN ('1 tuan / 1 lan', '2 tuan / 1 lan-le' )"*/
				+ "		GROUP BY TBH.PK_SEQ, TBH.DIENGIAI, DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK"
				+ "	 )T4_1 ON T4_1.NPPID = NPP.PK_SEQ AND T4_1.DDKDID = DDKD.PK_SEQ AND T4_1.LCHID = LCH.PK_SEQ"
				+ "	 INNER JOIN"
				+ "	 ("
				+ "		SELECT"
				+ "			TBH.DIENGIAI AS TBH,"
				+ "			KH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "		FROM KHACHHANG KH"
				+ "			INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "			INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "			INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "			INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "		WHERE tbh.npp_fk='"+NPPID+"'   AND TBH.NGAYID='5' "
						/*"AND KH_TBH.TANSO IN ('1 tuan / 1 lan', '2 tuan / 1 lan-le' )"*/
				+ "		GROUP BY TBH.PK_SEQ, TBH.DIENGIAI, DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK"
				+ "	 )T5_1 ON T5_1.NPPID = NPP.PK_SEQ AND T5_1.DDKDID = DDKD.PK_SEQ AND T5_1.LCHID = LCH.PK_SEQ"
				+ " 	INNER JOIN"
				+ "	 ("
				+ "		SELECT"
				+ "			TBH.DIENGIAI AS TBH,"
				+ "			KH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "		FROM KHACHHANG KH"
				+ "			INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "			INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "			INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "			INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "		WHERE tbh.npp_fk='"+NPPID+"'  and TBH.NGAYID='6' "
						/*"AND KH_TBH.TANSO IN ('1 tuan / 1 lan', '2 tuan / 1 lan-le' )"*/
				+ "		GROUP BY TBH.PK_SEQ, TBH.DIENGIAI, DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK"
				+ "	 )T6_1 ON T6_1.NPPID = NPP.PK_SEQ AND T6_1.DDKDID = DDKD.PK_SEQ AND T6_1.LCHID = LCH.PK_SEQ"
				+ "	INNER JOIN"
				+ "	("
				+ "		SELECT"
				+ "			TBH.DIENGIAI AS TBH,"
				+ "			KH.NPP_FK AS NPPID,"
				+ "			DDKD.PK_SEQ AS DDKDID,"
				+ "			LCH.PK_SEQ AS LCHID,"
				+ "			COUNT(KH.PK_SEQ) AS SOCUAHIEU"
				+ "		FROM KHACHHANG KH"
				+ "			INNER JOIN KHACHHANG_TUYENBH KH_TBH ON KH_TBH.KHACHHANG_FK = KH.PK_SEQ"
				+ "			INNER JOIN TUYENBANHANG TBH ON TBH.PK_SEQ = KH_TBH.TBH_FK"
				+ "			INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = TBH.DDKD_FK"
				+ "			INNER JOIN LOAICUAHANG LCH ON KH.LCH_FK = LCH.PK_SEQ"
				+ "		WHERE  tbh.npp_fk='"+NPPID+"'  and TBH.NGAYID='7' "
						/*"AND KH_TBH.TANSO IN ('1 tuan / 1 lan', '2 tuan / 1 lan-le' )"*/
				+ "		GROUP BY TBH.PK_SEQ, TBH.DIENGIAI, DDKD.PK_SEQ,  LCH.PK_SEQ,  KH.NPP_FK"
				+ " 	)T7_1 ON T7_1.NPPID = NPP.PK_SEQ AND T7_1.DDKDID = DDKD.PK_SEQ AND T7_1.LCHID = LCH.PK_SEQ "
				+ " 	WHERE TBH.NPP_FK = " + NPPID;
		
		System.out.println(query);
		//return "";
		return query;
	}

	public String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date date = new Date();
		return dateFormat.format(date);
	}
    public RouteSumaryReport() {
        super();
       
    }	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");	    
		HttpSession session = request.getSession();
		
	    IRouteSumaryReport obj =  new RouteSumaryReportCls();
	    Utility util = new Utility();
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
//	    System.out.println(userId);
	    obj.setuserId(userId);
	    obj.init();	    
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/RouteSumaryReport.jsp";
		response.sendRedirect(nextJSP);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");	
		
		HttpSession session = request.getSession();
		IRouteSumaryReport obj =  new RouteSumaryReportCls();
		String userId = (String)session.getAttribute("userId");  
	    String userTen = (String)session.getAttribute("userTen"); 
		try{
		    if(userId ==null)		    	
		    	userId ="";		    
		    obj.setuserId(userId);
		    
		    obj.setuserTen(userTen);
		    String khuvucs = request.getParameter("khuvucs");
		    if(khuvucs==null){
		    	khuvucs = "";
		    }		    
		    obj.setKhuVuc(khuvucs);
		    
		    String hoatdong = request.getParameter("hoatdong");
		    if(hoatdong==null){
		    	hoatdong = "";
		    }
		    obj.setStatus(hoatdong);
		    
		    String npps = request.getParameter("npps");
		    if(npps==null){
		    	npps = "";
		    }
		    obj.setnppId(npps);
		    
		    Utility util = new Utility();
		    String action=request.getParameter("action");
		    if(action!=null){		    	
		    	if(action.equals("create")){
		    		response.setContentType("application/vnd.ms-excel");
			        response.setHeader("Content-Disposition", "attachment; filename=TomTatTuyenDuong(tt)"+util.setTieuDe(obj)+".xls");
			        OutputStream out = response.getOutputStream();
			     
			        String sql=setQuery(obj.getnppId());
			        Workbook workbook = new Workbook();			
					FillData(workbook,sql,obj); 
					workbook.save(out, 0);
		    	}		    	
		    }				        
			
		}catch(Exception ex){
			System.out.println("Loi : "+ex.toString());
	
		}
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/RouteSumaryReport.jsp";
		response.sendRedirect(nextJSP);
	}

	private void getCellStyle(Workbook workbook, String a, Color mau, Boolean dam, int size)
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    Cells cells = worksheet.getCells();
		Style style;
		Cell cell = cells.getCell(a); 
		 style = cell.getStyle();
	        Font font1 = new Font();
	        font1.setColor(mau);
	        font1.setBold(dam);
	        font1.setSize(size);
	        style.setFont(font1);
	        cell.setStyle(style);
	        
	}
	private void CreateHeader(Workbook workbook, int i,IRouteSumaryReport obj) throws Exception {
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		
		Cells cells = worksheet.getCells();

		cells.setRowHeight(0, 40.0f);
		Cell cell = cells.getCell("A1");
	    cell.setValue("B??O C??O T??M T???T TUY???N ???????NG ");   	
	    getCellStyle(workbook,"A1",Color.RED,true,15);
	  
	  
	  
	    cells.setRowHeight(3, 18.0f);
	    
	    cell = cells.getCell("A3");  getCellStyle(workbook,"A3",Color.NAVY,true,9);
	    cell.setValue("Ng??y b??o c??o: " + this.getDateTime());
	    
	    cell = cells.getCell("A4");getCellStyle(workbook,"A4",Color.NAVY,true,9);
	    cell.setValue("Ng?????i t???o b??o c??o : " + obj.getuserTen());
	   
	    cells.setRowHeight(4, 18.0f);
	    
	    
	    cell = cells.getCell("A5");getCellStyle(workbook,"A5",Color.NAVY,true,9);
	    cell.setValue("Nh?? ph??n ph???i  : " + obj.getNppTen());
	    
		//bat 
		
		cells.setColumnWidth(0, 13.0f);
		cells.merge(i, 0, i + 1, 0);
		cell = cells.getCell("A" + String.valueOf(i + 1));
		cell.setValue("Mang");
		setHeaderCell(cell);
		cell = cells.getCell("A" + String.valueOf(i + 2));
		setCellBorderStyle(cell);

		cells.setColumnWidth(1, 20.0f);
		cells.merge(i, 1, i + 1, 1);
		cell = cells.getCell("B" + String.valueOf(i + 1));
		cell.setValue("Cua Hieu");
		setHeaderCell(cell);
		cell = cells.getCell("B" + String.valueOf(i + 2));
		setCellBorderStyle(cell);

		cells.setColumnWidth(2, 30.0f);
		cells.merge(i, 2, i + 1, 2);
		cell = cells.getCell("C" + String.valueOf(i + 1));
		cell.setValue("Ghe Tham Thang");
		setHeaderCell(cell);
		cell = cells.getCell("C" + String.valueOf(i + 2));
		setCellBorderStyle(cell);

		cells.setColumnWidth(3, 20.0f);
		cells.merge(i, 3, i + 1, 3);
		cell = cells.getCell("D" + String.valueOf(i + 1));
		cell.setValue("Doanh So Thang");
		setHeaderCell(cell);
		cell = cells.getCell("D" + String.valueOf(i + 2));
		setCellBorderStyle(cell);

		cells.merge(i, 4, i, 9);
		cell = cells.getCell("E" + String.valueOf(i + 1));
		cell.setValue("Ghe tham tuan 1");
		setHeaderCell(cell);
		cells.getCells(i, 4, i, 9, true);
		Iterator<Cell> iCell = cells.getCellIterator();
		while (iCell.hasNext()) {
			setHeaderCell(iCell.next());
		}

		cells = worksheet.getCells();
		setCell(cells, 4, 3.0f, "E" + String.valueOf(i + 2), "T2");
		setCell(cells, 5, 3.0f, "F" + String.valueOf(i + 2), "T3");
		setCell(cells, 6, 3.0f, "G" + String.valueOf(i + 2), "T4");
		setCell(cells, 7, 3.0f, "H" + String.valueOf(i + 2), "T5");
		setCell(cells, 8, 3.0f, "I" + String.valueOf(i + 2), "T6");
		setCell(cells, 9, 3.0f, "J" + String.valueOf(i + 2), "T7");

		cells.merge(i, 10, i, 15);
		cell = cells.getCell("K" + String.valueOf(i + 1));
		cell.setValue("Ghe tham tuan 2");
		setHeaderCell(cell);

		setCell(cells, 10, 3.0f, "K" + String.valueOf(i + 2), "T2");
		setCell(cells, 11, 3.0f, "L" + String.valueOf(i + 2), "T3");
		setCell(cells, 12, 3.0f, "M" + String.valueOf(i + 2), "T4");
		setCell(cells, 13, 3.0f, "N" + String.valueOf(i + 2), "T5");
		setCell(cells, 14, 3.0f, "O" + String.valueOf(i + 2), "T6");
		setCell(cells, 15, 3.0f, "P" + String.valueOf(i + 2), "T7");

		cells.merge(i, 16, i, 21);
		cell = cells.getCell("Q" + String.valueOf(i + 1));
		cell.setValue("Ghe tham tuan 3");
		setHeaderCell(cell);
		setCell(cells, 16, 3.0f, "Q" + String.valueOf(i + 2), "T2");
		setCell(cells, 17, 3.0f, "R" + String.valueOf(i + 2), "T3");
		setCell(cells, 18, 3.0f, "S" + String.valueOf(i + 2), "T4");
		setCell(cells, 19, 3.0f, "T" + String.valueOf(i + 2), "T5");
		setCell(cells, 20, 3.0f, "U" + String.valueOf(i + 2), "T6");
		setCell(cells, 21, 3.0f, "V" + String.valueOf(i + 2), "T7");

		cells.merge(i, 22, i, 27);
		cell = cells.getCell("W" + String.valueOf(i + 1));
		cell.setValue("Ghe tham tuan 4");
		setHeaderCell(cell);
		setCell(cells, 22, 3.0f, "W" + String.valueOf(i + 2), "T2");
		setCell(cells, 23, 3.0f, "X" + String.valueOf(i + 2), "T3");
		setCell(cells, 24, 3.0f, "Y" + String.valueOf(i + 2), "T4");
		setCell(cells, 25, 3.0f, "Z" + String.valueOf(i + 2), "T5");
		setCell(cells, 26, 3.0f, "AA" + String.valueOf(i + 2), "T6");
		setCell(cells, 27, 3.0f, "AB" + String.valueOf(i + 2), "T7");
	}


	private void setCell(Cells cells,int index,float width,String colName,String value){	
		cells.setColumnWidth(index, width);
		Cell cell = cells.getCell(colName);
		cell.setValue(value);		
		setHeaderCell(cell);
	}

	private void FillData(Workbook workbook, String query,IRouteSumaryReport obj) throws Exception {
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();
		
		

		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		Cell cell = null;	
		cells.merge(3, 3, 3,9);	
		cell = cells.getCell("D4");
		Style style = cell.getStyle();
		Font font = style.getFont();
		font.setSize(15);
		font.setBold(true);
		style.setFont(font);cell.setStyle(style);
		
		int index = 7;
		List<ObjSumaryReport> list = new ArrayList<ObjSumaryReport>();
		Hashtable<String, String> hash = new Hashtable<String, String>();
		while (rs.next()) {
			list.add(new ObjSumaryReport(rs.getString("DDKDID"), rs
					.getString("DDKD"), rs.getString("LOAI"), rs
					.getString("CUAHIEU"), rs.getString("GHETHAMTHANG"),rs.getString("DSTHANG"), 
					rs.getString("T2_1"), rs.getString("T3_1"),	rs.getString("T4_1"), rs.getString("T5_1"), rs.getString("T6_1"),rs.getString("T7_1"), 
					rs.getString("T2_1"), rs.getString("T3_1"), rs.getString("T4_1"), rs.getString("T5_1"), rs.getString("T6_1"), rs.getString("T7_1"), 
					rs.getString("T2_1"), rs.getString("T3_1"), rs.getString("T4_1"), rs.getString("T5_1"), rs.getString("T6_1"),rs.getString("T7_1"),
					rs.getString("T2_1"), rs.getString("T3_1"), rs.getString("T4_1"),rs.getString("T5_1"), rs.getString("T6_1"), rs.getString("T7_1")));
			if (hash.get(rs.getString("DDKDID")+"_"+rs.getString("LOAI")) == null) {
				hash.put(rs.getString("DDKDID")+"_"+rs.getString("LOAI"), rs.getString("DDKD"));
			}
		}
		if(rs!=null)
		rs.close();
		if(db!=null){
			db.shutDown();
		}
		Set<Entry<String, String>> sets = hash.entrySet();
		Iterator<Entry<String, String>> it = sets.iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String KEY = entry.getKey();
			String VALUE = entry.getValue();
			CreateHeader(workbook, index,obj);
			cell = cells.getCell("A" + index);
			cell.setValue(KEY);
			setFont(cell);
			cell = cells.getCell("B" + index);
			cell.setValue(VALUE);
			setFont(cell);
			index += 3;
			for (int j = 0; j < list.size(); ++j) {
				//System.out.println( " chung ta la chien thang :"+KEY);
				//System.out.println(list.get(j).maddkd +"_"+list.get(j).loai.trim());
				
				if (KEY.trim().equals(list.get(j).maddkd.trim()+"_"+list.get(j).loai.trim()) ) {
					cell = cells.getCell("A" + index);
					cell.setValue(list.get(j).loai);
					setCellBorderStyle(cell);
					cell = cells.getCell("B" + index);
					cell.setValue(list.get(j).cuahieu);
					setCellBorderStyle(cell);
					cell = cells.getCell("C" + index);
					cell.setValue(list.get(j).ghethangthang);
					setCellBorderStyle(cell);
					cell = cells.getCell("D" + index);
					cell.setValue( Double.parseDouble((list.get(j).dsThang)));
					
					setCellBorderStyle(cell);
					cell = cells.getCell("E" + index);
					cell.setValue(list.get(j).t21);
					setCellBorderStyle(cell);
					cell = cells.getCell("F" + index);
					cell.setValue(list.get(j).t31);
					setCellBorderStyle(cell);
					cell = cells.getCell("G" + index);
					cell.setValue(list.get(j).t41);
					setCellBorderStyle(cell);
					cell = cells.getCell("H" + index);
					cell.setValue(list.get(j).t51);
					setCellBorderStyle(cell);
					cell = cells.getCell("I" + index);
					cell.setValue(list.get(j).t61);
					setCellBorderStyle(cell);
					cell = cells.getCell("J" + index);
					cell.setValue(list.get(j).t71);
					setCellBorderStyle(cell);

					cell = cells.getCell("K" + index);
					cell.setValue(list.get(j).t22);
					setCellBorderStyle(cell);
					cell = cells.getCell("L" + index);
					cell.setValue(list.get(j).t32);
					setCellBorderStyle(cell);
					cell = cells.getCell("M" + index);
					cell.setValue(list.get(j).t42);
					setCellBorderStyle(cell);
					cell = cells.getCell("N" + index);
					cell.setValue(list.get(j).t52);
					setCellBorderStyle(cell);
					cell = cells.getCell("O" + index);
					cell.setValue(list.get(j).t62);
					setCellBorderStyle(cell);
					cell = cells.getCell("P" + index);
					cell.setValue(list.get(j).t72);
					setCellBorderStyle(cell);

					cell = cells.getCell("Q" + index);
					cell.setValue(list.get(j).t23);
					setCellBorderStyle(cell);
					cell = cells.getCell("R" + index);
					cell.setValue(list.get(j).t33);
					setCellBorderStyle(cell);
					cell = cells.getCell("S" + index);
					cell.setValue(list.get(j).t43);
					setCellBorderStyle(cell);
					cell = cells.getCell("T" + index);
					cell.setValue(list.get(j).t53);
					setCellBorderStyle(cell);
					cell = cells.getCell("U" + index);
					cell.setValue(list.get(j).t63);
					setCellBorderStyle(cell);
					cell = cells.getCell("V" + index);
					cell.setValue(list.get(j).t73);
					setCellBorderStyle(cell);

					cell = cells.getCell("W" + index);
					cell.setValue(list.get(j).t24);
					setCellBorderStyle(cell);
					cell = cells.getCell("X" + index);
					cell.setValue(list.get(j).t34);
					setCellBorderStyle(cell);
					cell = cells.getCell("Y" + index);
					cell.setValue(list.get(j).t44);
					setCellBorderStyle(cell);
					cell = cells.getCell("Z" + index);
					cell.setValue(list.get(j).t54);
					setCellBorderStyle(cell);
					cell = cells.getCell("AA" + index);
					cell.setValue(list.get(j).t64);
					setCellBorderStyle(cell);
					cell = cells.getCell("AB" + index);
					cell.setValue(list.get(j).t74);
					setCellBorderStyle(cell);
				}
			}

			index += 3;
		}
		hash.clear();
		list.clear();
		
	}


	private void setCellBorderStyle(Cell cell){
		Style style = cell.getStyle();
		style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);
		cell.setStyle(style);		
	}
	private void setHeaderCell(Cell cell){
		Style style = cell.getStyle();
		style.setHAlignment(HorizontalAlignmentType.CENTRED);
		style.setBorderLine(BorderType.TOP, 1);
		style.setBorderLine(BorderType.RIGHT, 1);
		style.setBorderLine(BorderType.BOTTOM, 1);
		style.setBorderLine(BorderType.LEFT, 1);	
		//style.setColor(Color.SILVER);		
		Font font = style.getFont();		
		font.setBold(true);
		style.setFont(font);
		cell.setStyle(style);
	}
	private void setFont(Cell cell){
		Style style = cell.getStyle();
		Font font = style.getFont();
		font.setBold(true);
		style.setFont(font);
		cell.setStyle(style);
	}

}
