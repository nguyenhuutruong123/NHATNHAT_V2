package geso.dms.distributor.servlets.hoadontaichinh;

import geso.dms.center.beans.khuvuc.imp.Khuvuc;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.hoadontaichinh.IBCChiTietCongNo;
import geso.dms.distributor.beans.hoadontaichinh.imp.BCChiTietCongNo;
import geso.dms.distributor.beans.reports.IBCCongNoKH;
import geso.dms.distributor.beans.reports.imp.BCCongNoKH;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.io.*;
import java.text.SimpleDateFormat;





import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.examples.MergedCells;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.examples.MergingCells;
import org.apache.poi.hssf.record.MergeCellsRecord.MergedRegion;

import Z.DB;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.HorizontalAlignmentType;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.extentech.formats.XLS.Mergedcells;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class BCChiTietCongNoSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public BCChiTietCongNoSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub	

		IBCChiTietCongNo obj = new BCChiTietCongNo();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//	PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Utility util=new Utility();
		String userTen = (String)session.getAttribute("userTen");
		String querystring=request.getQueryString();
		String userId = util.getUserId(querystring);
		/*String nppId= util.getIdNhapp(userId);*/

		session.setAttribute("userTen", userTen);
		session.setAttribute("userId", userId);
		session.setAttribute("tungay", "");
		session.setAttribute("denngay","");
		session.setAttribute("loi", "");

		System.out.println("userId" + userId);

		obj.setUserId(userId);
		obj.setUserName(userTen);



		String nppId ="";
		String view = request.getParameter("view");
		if(view == null)
			view = "NPP";
		if(view.equals("TT"))
		{
			nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			obj.setNppId(nppId);
		}else
		{
			nppId=util.getIdNhapp(userId);
			obj.setNppId(nppId);
		}

		obj.init1();
		String nextJSP ="";
		if(view.equals("TT"))
		{
			nextJSP = request.getContextPath() + "/pages/Center/BaoCaoChiTietCongNo.jsp";
		}
		if(view.equals("NPP"))
		{
			nextJSP = request.getContextPath() + "/pages/Distributor/BaoCaoChiTietCongNo.jsp";
		}
		obj.setLoaimenu(view);

		session.setAttribute("obj", obj);
		response.sendRedirect(nextJSP);
	}
	//
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IBCChiTietCongNo obj = new BCChiTietCongNo();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		//	PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		Utility util = new Utility();
		String tuNgay = util.antiSQLInspection(request.getParameter("tuNgay"));
		obj.setTuNgay(tuNgay);
		String denNgay = util.antiSQLInspection(request.getParameter("denNgay"));
		obj.setDenNgay(denNgay);

		String KhachHang = util.antiSQLInspection(request.getParameter("KhachHang"));
		if(KhachHang==null)
			KhachHang="";
		obj.setKHId(KhachHang);

		String Vung = util.antiSQLInspection(request.getParameter("vungId"));
		System.out.println("Vung "+ Vung);
		if(Vung==null)
			Vung="";
		obj.setvungId(Vung);

		String khuvuc = util.antiSQLInspection(request.getParameter("khuvucId"));
		if(khuvuc==null)
			khuvuc="";
		obj.setkhuvucId(khuvuc);

		String ttId = util.antiSQLInspection(request.getParameter("ttId"));
		System.out.println("ttId "+ Vung);
		if(ttId==null)
			ttId="";
		obj.setTtId(ttId);

		String type = util.antiSQLInspection(request.getParameter("type"));
		if(type==null)
			type="0";
		obj.settype(type);

		String doitacId = util.antiSQLInspection(request.getParameter("doitacId"));
		if(doitacId==null)
			doitacId="";
		obj.setDoiTacId(doitacId);

		session.setAttribute("tungay", tuNgay);
		session.setAttribute("denngay", denNgay);
		session.setAttribute("vungId", Vung);
		session.setAttribute("khuvucId", khuvuc);
		String action = request.getParameter("action");
		String userTen = (String)session.getAttribute("userTen");
		String userId = (String) session.getAttribute("userId"); 


		obj.setUserId(userId);
		obj.setUserName(userTen);

		String nppId= util.getIdNhapp(userId);

		String nppID="";

		String view = request.getParameter("view");
		if(view == null)
			view = "NPP";



		if(view.equals("TT"))
		{
			nppId = util.antiSQLInspection(request.getParameter("nppHOId"));
			if (nppId == null)
				nppId = "";
			obj.setDoiTacHOId(nppId);
			obj.setNppId("1");
		}else
		{
			nppId=util.getIdNhapp(userId);
			obj.setNppId(nppId);
		}
		obj.setLoaimenu(view);


		String nguon = request.getParameter("nguon");
		if(nguon == null)
			nguon = "0";
		obj.setTheoChungloai(nguon);

		if(action.equals("excel")){
			OutputStream out = response.getOutputStream();
			try
			{ 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=SoChiTietCongNo.xlsm");

				ExportToExcel( out, obj );
				return;

			}
			catch (Exception ex)
			{
				ex.printStackTrace();

			}


		}

		else
		{

			String nextJSP ="";
			if(view.equals("TT"))
			{
				nextJSP = request.getContextPath() + "/pages/Center/BaoCaoChiTietCongNo.jsp";
			}
			if(view.equals("NPP"))
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/BaoCaoChiTietCongNo.jsp";
			}

			obj.init1();

			session.setAttribute("obj", obj);

			response.sendRedirect(nextJSP);
		}
	}





	private void ExportToExcel(OutputStream out,IBCChiTietCongNo obj )
	{
		try{ 			

			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			TaoBaoCao(workbook, obj,7,0,0);
			workbook.save(out);			

		}catch(Exception ex){
			ex.printStackTrace();

		}

	}
	public String getQuery(IBCChiTietCongNo obj,String nppInsert,String khInsert, String doitacInsert,String YTGoc)
	{
		if(obj.getTheoChungloai().equals("0"))
			return   "\n  select  ''YTGoc,ngayCt,SoCt,SoHoaDon,NGayHD,TKDU,YTDU,NoiDung, SUM(PSNO)PSNO,SUM(PSCO)PSCO   " + 
			"\n  from [dbo].[ufn_SoChiTietCongNo]('"+ obj.getTuNgay() +"','"+ obj.getDenNgay() +"', "+nppInsert+","+khInsert+","+doitacInsert+", '"+YTGoc+"' )  " + 
			"\n  group by ngayCt,SoCt,SoHoaDon,NGayHD,TKDU,YTDU,NoiDung ";

		return "  select * from  dbo.ufn_SoChiTietCongNo('"+ obj.getTuNgay() +"','"+ obj.getDenNgay() +"', "+nppInsert+","+khInsert+","+doitacInsert+", '"+YTGoc+"' ) order by YTGoc,NgayCt,YTDU, SoCT ,TKDU ";
	}
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IBCChiTietCongNo obj,int countRow,int column,int sheetNum)throws Exception
	{
		try{



			Color c = Color.WHITE;
			boolean bold =false; 
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();

			com.aspose.cells.Cell cell = cells.getCell("D4");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "SỔ CHI TIẾT CÔNG NỢ");
			Style style = cell.getStyle();
			com.aspose.cells.Font font = new com.aspose.cells.Font();
			font.setColor(Color.RED);//mau chu
			font.setSize(16);// size chu
			font.setBold(true);
			style.setFont(font);
			cell.setStyle(style);	

			cell = cells.getCell("A1");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Người tạo:"+ obj.getUserName());

			cell = cells.getCell("D5");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Thời gian:" + obj.getTuNgay() +" đến " + obj.getDenNgay());


			cell = cells.getCell("A7");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Tài khoản:");


			cell = cells.getCell("B7");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Phải thu của khách hàng	[131]");


			int dongbatdau = countRow;


			int cong = 0;
			int batdau = 0;
			cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY CT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			cell = cells.getCell(countRow, cong++ );cell.setValue("SỐ CT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			cell = cells.getCell(countRow, cong++ );cell.setValue("SỐ HĐ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY HĐ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			int tkdu_column = cong;
			cell = cells.getCell(countRow, cong++ );cell.setValue("TK ĐƯ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);


			//cells.setColumnWidth(cong, (float) ("KHÁCH GIAO DỊCH".length()));
			cell = cells.getCell(countRow, cong++ );cell.setValue("YTĐƯ");	 ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);


			int psno_column = cong;
			cell = cells.getCell(countRow, cong++ );cell.setValue("PS NỢ VNĐ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			int psco_column = cong;
			cell = cells.getCell(countRow, cong++ );cell.setValue("PS CÓ VNĐ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			int ketthuc = cong;
			cell = cells.getCell(countRow, cong++ );cell.setValue("NỘI DUNG"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);

			countRow ++;




			




			String  kh ="";
			
			if(obj.getLoaimenu().equals("NPP"))
			{
				if(obj.gettype().equals("0")) // kh
				{
					kh ="\n select  pk_seq,ten,mafast from khachhang where trangthai = 1 and npp_fk =   " + obj.getNppId();
					if(obj.getKHId().trim().length() > 0)
						kh += " and pk_seq=  "+ obj.getKHId();
				}
				else
				{
					kh ="\n select  pk_seq,ten,mafast from nhaphanphoi where trangthai = 1 and tructhuoc_fk =   " + obj.getNppId();
					if(obj.getDoiTacId().trim().length() > 0)
						kh += " and pk_seq=  "+ obj.getDoiTacId();
				}

			}
			else
			{
				kh ="\n select  pk_seq,ten,mafast from nhaphanphoi where trangthai = 1 and tructhuoc_fk = 1 ";
				if(obj.getDoiTacHOId().trim().length() > 0)
					kh += " and pk_seq=  "+ obj.getDoiTacHOId();
			}
			System.out.println(" kh === "+ kh);
			ResultSet inFo = obj.getDb().get(kh);
			while(inFo.next())
			{
				String nppInsert = "1";
				String khInsert = "0";
				String doitacInsert = "0";
				
				
				if(obj.getLoaimenu().equals("NPP"))
				{
					nppInsert = obj.getNppId();
					if(obj.gettype().equals("0")) 
					{
						khInsert = inFo.getString("pk_seq");
					}
					else
					{
						doitacInsert = inFo.getString("pk_seq");
					}
				}
				else
				{
					doitacInsert = inFo.getString("pk_seq");
				}
				String tenKh = inFo.getString("ten");
				String ma = inFo.getString("mafast");
				String querytondau = "\n select  cl.Ten YTGoc,isnull( sum ( isnull(PSNO,0) - isnull(PSCO,0) ) , 0 ) XNT " +
				"\n from CHUNGLOAI CL " +
				"\n left join dbo.ufn_SoChiTietCongNo('1900-01-01',convert (char(10), dateadd(dd,-1,'"+ obj.getTuNgay() +"'),126), "+nppInsert+","+khInsert+","+doitacInsert+",'' ) kq " +
				"\n  on cl.Ten = kq.YTGoc  " +
				"\n group by cl.Ten  ";
				if(obj.getTheoChungloai().equals("0"))
					querytondau =   "\n select  '' YTGoc,isnull( sum ( isnull(PSNO,0) - isnull(PSCO,0) ) , 0 ) XNT " +
					"\n from  dbo.ufn_SoChiTietCongNo('1900-01-01',convert (char(10), dateadd(dd,-1,'"+ obj.getTuNgay() +"'),126), "+nppInsert+","+khInsert+","+doitacInsert+",'' ) kq " ;

				ResultSet rstondau=obj.getDb().get(querytondau);
				System.out.println(" kq =  " + querytondau);
				while(rstondau.next())
				{
					String  YTGoc = rstondau.getString("YTGoc");
					double  TonDau = rstondau.getDouble("XNT");
					String query = getQuery(obj,nppInsert,khInsert,doitacInsert,YTGoc);
					System.out.println("query = "+ query);

					for(int z = batdau; z<=ketthuc;z++)
					{	
						cell = cells.getCell(countRow,z); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
					cells.merge(countRow, batdau, countRow ,ketthuc );
					cell = cells.getCell(countRow, batdau );cell.setValue("Đối tượng:" + tenKh +"["+ma+"] - " + YTGoc); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					countRow ++;
					ResultSet	rs = obj.getDb().get(query);
					cell = cells.getCell(countRow, tkdu_column );cell.setValue("Dư đầu:"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					cell = cells.getCell(countRow, psno_column );cell.setValue(TonDau); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					countRow ++;
					double tongphatsinhno = 0;
					double tongphatsinhco = 0;
					while(rs.next())
					{


						cong = 0;
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("NgayCt")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("SoCT")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("SOHOADON")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("NgayHD")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("TKDU")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("YTDU")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("PSNO")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("PSCO")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
						cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("NoiDung")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
						TonDau +=  rs.getDouble("PSNO") - rs.getDouble("PSCO");
						tongphatsinhno +=  rs.getDouble("PSNO") ;
						tongphatsinhco += rs.getDouble("PSCO");
						++countRow;
					}	

					for(int z = batdau; z<=ketthuc;z++)
					{	
						cell = cells.getCell(countRow,z); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
					cell = cells.getCell(countRow, tkdu_column );cell.setValue("Tổng cộng:"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					cell = cells.getCell(countRow, psno_column );cell.setValue(tongphatsinhno); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					cell = cells.getCell(countRow, psco_column );cell.setValue(tongphatsinhco); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					countRow ++;
					for(int z = batdau; z<=ketthuc;z++)
					{	
						cell = cells.getCell(countRow,z); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
					cell = cells.getCell(countRow, tkdu_column );cell.setValue("Lũy kế:"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					cell = cells.getCell(countRow, psno_column );cell.setValue(tongphatsinhno); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					cell = cells.getCell(countRow, psco_column );cell.setValue(tongphatsinhco); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					
					countRow ++;
					for(int z = batdau; z<=ketthuc;z++)
					{	
						cell = cells.getCell(countRow,z); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, false, 0);
					}
					cell = cells.getCell(countRow, tkdu_column );cell.setValue("Dư cuối:"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					if(TonDau > 0)
					{
						cell = cells.getCell(countRow, psno_column );cell.setValue(Math.abs(TonDau)); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					}
					else
					{
						cell = cells.getCell(countRow, psco_column );cell.setValue(Math.abs(TonDau)); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 43);
					}
					if(rs!=null)rs.close();
					countRow ++;
				}




			}






		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}

}
