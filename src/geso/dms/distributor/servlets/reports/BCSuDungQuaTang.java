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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
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
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class BCSuDungQuaTang extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BCSuDungQuaTang() {
        super();
    } 
    
    NumberFormat formatter = new DecimalFormat("#,###,###");
    NumberFormat formatter2 = new DecimalFormat("#,###,###.###");
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();	    	   

	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String view = request.getParameter("view");
	    if(view == null)
	    	view = "";
	    
	    System.out.println("userid: "+userId+" -- view: "+view);
	    obj = new Stockintransit();
	    obj.setuserId(userId);
	    if(!view.equals("TT"))
	    	obj.getNppInfo();
	    System.out.println("NPPID: "+obj.getnppId());
	    //obj.setLoaiMenu(view);
	    obj.initBCSuDungQuaTang();

	    	
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCSuDungQuaTang.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utility util = new Utility();
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    String userId = request.getParameter("userId");
	    String action = request.getParameter("action");
		if (action == null){
			action = "";
		}
		
		String view = request.getParameter("view");
		if(view == null)
		view = "";
		    
		    
	    obj = new Stockintransit();
	    obj.setuserId(userId);
	    
	    //obj.setLoaiMenu(view);
	    if(view.equals("NPP"))
	    	obj.getNppInfo();
		    
	   	
	    String tungay = request.getParameter("tungay");
	    if(tungay == null)
	    	tungay = "";
	    obj.settungay(tungay);
	    
	    String denngay = request.getParameter("denngay");
	    if(denngay == null)
	    	denngay = "";
	    obj.setdenngay(denngay);
	    
	    String vungId = request.getParameter("vungId");
	    if(vungId == null)
	    	vungId = "";
	    obj.setvungId(vungId);
	    
	    String kvId = request.getParameter("kvId");
	    if(kvId == null)
	    	kvId = "";
	    obj.setkhuvucId(kvId);
	    
	    String npp = request.getParameter("nppId");
	    if(npp.equals(""))
	    	npp = "";
	    obj.setnppId(npp);
	    
	    
	    String ddkdId = request.getParameter("ddkdId");
	    if(ddkdId == null || ddkdId.equals(""))
	    	ddkdId = "";
	    obj.setDdkd(ddkdId);
	    
	    String khId = request.getParameter("khId");
	    if(khId == null || khId.equals(""))
	    	khId = null;
	    obj.setkhId(khId);
	    
	    String ctId = request.getParameter("ctId");
	    if(ctId == null || ctId.equals(""))
	    	ctId = "";
	    obj.setCttbId(ctId); 
	    
		session.setAttribute("obj", obj);
	    
		if( action.equals("taobaocao"))
		{
			try
			{
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=SuDungQuaTang_" + util.setTieuDe(obj) + ".xlsm");
				OutputStream out = response.getOutputStream();
				ExportToExcel(out,obj);
				
			} catch (Exception ex)
			{
				ex.printStackTrace();
				obj.initBCSuDungQuaTang();
				session.setAttribute("obj", obj);
				session.setAttribute("userId", obj.getuserId());
				obj.setMsg("Lỗi không lấy được báo cáo ! Kiểm tra lại chỉ tiêu nhân viên hoặc công thức thưởng");
			}
		}
		else
		{
			obj.initBCSuDungQuaTang();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BCSuDungQuaTang.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	
	

	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public void setCellBackground(Cell cell,Color color,int borderLineType,boolean bold,int decimal){
		Style style = cell.getStyle();
		style.setColor(color);
		style.setBorderLine(BorderType.BOTTOM, borderLineType);
		style.setBorderLine(BorderType.LEFT, borderLineType);
		style.setBorderLine(BorderType.TOP, borderLineType);
		style.setBorderLine(BorderType.RIGHT, borderLineType);
		style.setNumber(decimal);
		
		
		
		Font font = new Font();
		font.setName("Times New Roman");
		font.setColor(Color.BLACK);
		font.setBold(bold);
		style.setFont(font);
		font.setSize(11);
		
		cell.setStyle(style);		
	}
	
	
	private void ExportToExcel( OutputStream out,IStockintransit obj )throws Exception
	{
		try{ 			

			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			int sheetNum = 0;						
			if(workbook.getWorksheets().getNumberOfSheets() == sheetNum)
				workbook.getWorksheets().addSheet();
			String query = setQuery(obj);
			TaoBaoCao(workbook, obj, query);
			workbook.save(out);			

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	public String setQuery (IStockintransit obj) throws SQLException
	{	
		String query =   "\n with pb as" + 
						 "\n (	" + 
						 "\n   select pb.pk_seq pbId,pbdd.ddkd_fk,pb.diengiai,sp.ten SanPham,pb.tungay,pb.denngay ,ddkd.ten NVBH, pbdd.soluong SlPhanBo" + 
						 "\n   from phanbo_quatang pb" + 
						 "\n   inner join phanbo_quatang_ddkd  pbdd on pb.pk_seq = pbdd.phanbo_fk" + 
						 "\n   inner join DAIDIENKINHDOANH ddkd on ddkd.pk_seq = pbdd.ddkd_fk" + 
						 "\n   inner join sanpham sp on sp.pk_seq = pb.sanpham_fk" + 
						 "\n   where pb.trangthai = 1 and exists (select 1 from phanbo_quatang_ddkd_bacsi where phanbo_fk = pb.pk_seq and ngay >='"+obj.gettungay()+"' and ngay <= '"+obj.getdenngay()+"'  ) " + 
						 		( 	obj.getnppId().trim().length() > 0 ? " and pb.npp_fk = "+ obj.getnppId() :""  )+
						 		( 	obj.getCttbId().trim().length() > 0 ? " and pb.pk_seq = "+ obj.getCttbId() :""  )+
						 		(	obj.getDdkd().trim().length() > 0 ? " and pbdd.ddkd_fk = "+ obj.getDdkd() :""  )+
						 "\n ), sd as" + 
						 "\n (" + 
						 "\n 	select ddkd_fk,sanpham_fk,phanbo_fk,sum(soluong)soluong" + 
						 "\n 	from phanbo_quatang_ddkd_bacsi" + 
						 "\n 	where phanbo_fk in (select pbId from  pb )" + 
						 "\n 	group by ddkd_fk,sanpham_fk,phanbo_fk" + 
						 "\n )" + 
						 "\n " + 
						 "\n select pb.diengiai [Tên chương trình], pb.tungay [Từ ngày],pb.denngay [Đến ngày],pb.SanPham [Sản phẩm],pb.NVBH, pb.SlPhanBo [SL phân bổ], isnull(sd.soluong,0) [SL đã tặng],  pb.SlPhanBo - isnull(sd.soluong,0) [SL còn lại]" + 
						 "\n from pb" + 
						 "\n left join sd on pb.pbId = sd.phanbo_fk   and pb.ddkd_fk = sd.ddkd_fk ";
		System.out.println("query = "+ query);
		return query;
		
	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		try{


			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("SheetBaoCao");
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");	
		    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Báo cáo sử dụng quà tặng");
		    cell = cells.getCell("A4");
		    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 

			
			Style style;
			Font font = new Font();
			font.setColor(Color.RED);//mau chu
			font.setSize(16);// size chu
			font.setBold(true);

			ResultSet	rs =obj.getDb().get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			System.out.println("Số cột trong SQL: "+socottrongSql);

			int countRow = 8;
			int column= 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(countRow, column + i-1 );cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			countRow ++;
			int stt = 0;
			double diem = 0;
			long mang[] = new long[10000];
			while(rs.next())
			{
				
				Color c = Color.WHITE;
				boolean bold =false;
				
				for(int i =1;i <=socottrongSql ; i ++)
				{
				
					cell = cells.getCell(countRow,column + i-1 );
					if(rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
					}
				}
				
				++countRow;
			}
			if(rs!=null)rs.close();
			

		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
	}
}
