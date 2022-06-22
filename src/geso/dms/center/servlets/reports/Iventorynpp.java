package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.PivotFieldDataDisplayFormat;
import com.aspose.cells.PivotFieldType;
import com.aspose.cells.PivotTable;
import com.aspose.cells.PivotTables;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;



public class Iventorynpp extends HttpServlet {	
	/**
   * 
   */
  private static final long serialVersionUID = 1L;

	public Iventorynpp() {
        super();
    }
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		IStockintransit obj = new Stockintransit();
		
		Utility util=new Utility();
		//HttpSession session = request.getSession();
		//String userTen = (String)session.getAttribute("userTen");
		//String querystring=request.getQueryString();
		//String userId=	util.getUserId(querystring);
		obj.init();
		obj.settungay("");
		obj.setdenngay("");
		obj.setMsg("");
		obj.setuserId(userId);
		obj.setuserTen(userTen);
    	
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		session.setAttribute("userTen", userTen);
		session.setAttribute("util", util);
		String nextJSP = request.getContextPath() + "/pages/Center/Inventorynpp.jsp";
		response.sendRedirect(nextJSP);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream out = response.getOutputStream(); 
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
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  
		String manpp=util.getIdNhapp(userId);
		String nextJSP = request.getContextPath() + "/pages/Center/Inventorynpp.jsp";
		try {
			obj.setuserTen((String) session.getAttribute("userTen")!=null?
						(String) session.getAttribute("userTen"):"");
			
			obj.settungay(util.antiSQLInspection(request.getParameter("tungay"))!=null?
						util.antiSQLInspection(request.getParameter("tungay")):"");
			
			obj.setdenngay(util.antiSQLInspection(request.getParameter("denngay"))!=null?
					util.antiSQLInspection(request.getParameter("denngay")):"");
			
			obj.setuserId(util.antiSQLInspection(request.getParameter("userId"))!=null? 
						util.antiSQLInspection(request.getParameter("userId")):"");
			String avaliable = util.antiSQLInspection(request.getParameter("avail"));
			
			obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId"))!=null?
					util.antiSQLInspection(request.getParameter("kenhId")):"");
			
			String laysolo = util.antiSQLInspection(request.getParameter("laysolo"));
			if(laysolo==null){
				laysolo="0";
			}
			obj.settype(laysolo);

			response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=Iventorynpp.xlsm");
			dbutils db=new dbutils();
		 	String sql="select * from banggiamuanpp_npp bgmnpp where bgmnpp.npp_fk ='"+manpp+"'";
		    ResultSet rscheck=db.get(sql);
				
			   
			    if(rscheck!=null)
		/*	    if(!rscheck.next()){
			       obj.setMsg("Trên trung tâm chưa chọn bảng giá mua cho nhà phân phối,vui lòng liên hệ với admin trung tâm để xử lý tình huống này");
			       session.setAttribute("obj", obj);
					session.setAttribute("userId", userId);
					session.setAttribute("userTen", userTen);
					session.setAttribute("util", util);
					response.sendRedirect(nextJSP);
					
			    }else*/{
			
					boolean isTrue = CreatePivotTable(out, obj,avaliable);
					if(!isTrue){
						PrintWriter writer = new PrintWriter(out);
						writer.write("Khong co bao cao trong thoi gian nay....!!!");
						writer.close();
					}
			    }
			    rscheck.close();
		} catch (Exception ex) {
			obj.setMsg(ex.getMessage());
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			session.setAttribute("userTen", userTen);
			session.setAttribute("util", util);
			response.sendRedirect(nextJSP);
			System.out.println("Loi  khong lay duoc bao cao : "+ex.toString());
		}
	}
	private boolean CreatePivotTable(OutputStream out,IStockintransit obj, String avaliable) throws Exception
    {   
		String chuoi=getServletContext().getInitParameter("path") + "\\Iventory(NPP).xlsm";
		
	    if(obj.gettype().trim().equals("1")){
	    	chuoi=getServletContext().getInitParameter("path") + "\\Iventory(NPP_LO).xlsm";
	    }
	    System.out.println("chuoi la "+chuoi);
		FileInputStream fstream;
		fstream = new FileInputStream(chuoi);
			
		Workbook workbook = new Workbook();
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		
		
	     //Buoc2 tao khung
	    //ham tao khu du lieu
	     CreateStaticHeader(obj,workbook, obj.getDateTime(),obj.getDateTime(), obj.getuserTen());
	    
	     boolean isTrue =  CreateStaticData(workbook,obj,avaliable);
	     if(!isTrue)
	    	 return false;
	     workbook.save(out);
	     fstream.close();
	     return true;
	    
   }
	
	private void CreateStaticHeader(IStockintransit obj, Workbook workbook, String dateFrom, String dateTo, String UserName) throws Exception 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    Cells cells = worksheet.getCells();
	   
	    
	    
	   	Style style;
	    //cells.setColumnWidth(0, 200.0f);
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");  
	    cell.setValue("TỒN HIỆN TẠI");   	
	    
	  
	    style = cell.getStyle();
	   
	   Font font2 = new Font();
       font2.setColor(Color.RED);//mau chu
       font2.setSize(14);// size chu
       style.setFont(font2); 
       style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu       
       cell.setStyle(style);
	    cell = cells.getCell("A2"); getCellStyle(workbook,"A2",Color.NAVY,true,9);
	    cell.setValue("Từ ngày  " + dateFrom + "      Đến ngày " + dateTo);    
	    cell = cells.getCell("A3");getCellStyle(workbook,"A3",Color.NAVY,true,9);
	     cell.setValue("Ngày Tạo : " + this.getDateTime());
	    cell = cells.getCell("A4");getCellStyle(workbook,"A4",Color.NAVY,true,9);
	    cell.setValue("Tạo Bởi:  " + UserName);

	    //tieu de, hang dau tien cua bang du lieu, cell la toa do cua exel
	   
	    int cot = 26;
	    
	    cell = cells.getCell(0,cot++); cell.setValue("Kenh Ban Hang");
	    cell = cells.getCell(0,cot++); cell.setValue("Ten San Pham");
	    cell = cells.getCell(0,cot++); cell.setValue("So Luong ");	  
	    cell = cells.getCell(0,cot++); cell.setValue("Kho");
	    cell = cells.getCell(0,cot++); cell.setValue("Ma Nha Phan Phoi");
	    cell = cells.getCell(0,cot++); cell.setValue("Ma San Pham");
	    cell = cells.getCell(0,cot++); cell.setValue("So Luong Kien");
	    cell = cells.getCell(0,cot++); cell.setValue("Don Vi Kinh Doanh");
	    cell = cells.getCell(0,cot++); cell.setValue("Chung Loai");
	    cell = cells.getCell(0,cot++); cell.setValue("Nhan Hang");
	    cell = cells.getCell(0,cot++); cell.setValue("So Tien");
	    cell = cells.getCell(0,cot++); cell.setValue("Booked");
	    cell = cells.getCell(0,cot++); cell.setValue("TonThucTe");
	    if(obj.gettype().trim().equals("1"))
	    {
	    	 cell = cells.getCell(0,cot++); 	cell.setValue("SoLo");  ReportAPI.setCellHeader(cell);
	 	     cell = cells.getCell(0,cot++); 	cell.setValue("NgayHetHan");  ReportAPI.setCellHeader(cell);
	 	    cell = cells.getCell(0,cot++); 	cell.setValue("ngaynhapkho");  ReportAPI.setCellHeader(cell);
	    }
	    
	    cell = cells.getCell(0,cot++); 	cell.setValue("TonTamTinh");  ReportAPI.setCellHeader(cell);
	    worksheet.setName("Sheet1");
	}
	private boolean CreateStaticData(Workbook workbook,IStockintransit obj, String avaliable) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();
	    Utility utl = new  Utility();
	    String manpp ="";
	    manpp = utl.getIdNhapp(obj.getuserId());
	    
	    String sql1 = "";
	    if (obj.getkenhId().length() > 0)
			sql1 = sql1 + " and kbh.pk_seq ='" + obj.getkenhId() + "' ";
	    
	
	    	String condition = "";
	    	condition = condition + sql1;
	    	if(avaliable.equals("1")){
	    		condition +=" and tkn.AVAILABLE > 0";
	    	}
	    	
	    	String bang="select kho_fk,kbh_fk,sanpham_fk,npp_fk,soluong,AVAILABLE,booked ,'' solo,'' as ngayhethan,'' ngaynhapkho  from nhapp_kho where npp_fk = '"+manpp+"'";
			
	    	String tontamtinh = " select kho_fk,kbh_fk,sanpham_fk,npp_fk,total_booked BookTam , '' as solo, '' as ngayhethan,100 as HanSuDung,'' ngaynhapkho  from [dbo].[ufn_TonTamTinh]("+manpp+")   " ;
			
	    	
	    	
	    	if(obj.gettype().trim().equals("1")){
	    		
	    		tontamtinh = "\n 	select ngaynhapkho,kho_fk,kbh_fk,sanpham_fk,npp_fk,sum(Booked_SQL) BookTam ,solo, ngayhethan,100 as HanSuDung  " +
							 "\n	from [dbo].[ufn_TonTamTinh_ChiTiet]("+manpp+")  " +
							 "\n	group by kho_fk,kbh_fk,sanpham_fk,npp_fk,solo, ngayhethan,ngaynhapkho " ;
	    		
				bang="select kho_fk,kbh_fk,sanpham_fk,npp_fk,soluong,AVAILABLE,booked,solo, ngayhethan,ngaynhapkho from nhapp_kho_chitiet where npp_fk = '"+manpp+"'";
			}
			
			
			
			String sql ="\n  select isnull(TKN.ngaynhapkho,'') as ngaynhapkho,tkn.SoLuong,tkn.solo, tkn.ngayhethan,kbh.ten as Channel,sp.ma as Sku_code,sp.ten as SKU,tkn.AVAILABLE as Piece,k.ten as Warehouse, "+
			"\n tkn.npp_fk as Distributor_code,"+
            "\n nh.ten as Brans, isnull(cast(tkn.AVAILABLE/cast(qc.soluong1 as float) as float), 0) as Quantily,"+
			"\n dvkd.donvikinhdoanh as Business_unit,cl.ten as Category, "+
			"\n  tkn.AVAILABLE  "+
			"\n  	,  [dbo].[GiaBanLeNppSanPham](tkn.kbh_fk,npp.pk_seq,sp.pk_seq,'"+Utility.getNgayHienTai()+"' )  DonGia " +
			"\n		,booked,  tkn.AVAILABLE -  isnull(ttt.BookTam,0)TonTamTinh "+  
			"\n from ( "+bang+") tkn " +
			"\n inner join nhaphanphoi npp on npp.pk_seq =  "+manpp +
			"\n left join kenhbanhang kbh on kbh.pk_seq = tkn.kbh_fk "+
			"\n inner join sanpham sp on sp.pk_seq = tkn.sanpham_fk "+
			"\n left join donvikinhdoanh dvkd on dvkd.pk_seq = sp.dvkd_fk "+
			"\n inner join kho k on k.pk_seq = tkn.kho_fk "+
			"\n left join quycach qc on  qc.sanpham_fk = sp.pk_seq and sp.dvdl_fk =qc.dvdl1_fk  " +
			"\n  and qc.dvdl2_fk= 100018"+
			"\n left join donvidoluong dvdl on dvdl.pk_seq = sp.dvdl_fk "+
			"\n left join chungloai cl on cl.pk_seq = sp.chungloai_fk "+
			"\n left join nhanhang nh on nh.pk_seq = sp.nhanhang_fk "+
			"\n left join  ("+tontamtinh+") ttt on tkn.ngaynhapkho = ttt.ngaynhapkho and tkn.kho_fk = ttt.kho_fk and tkn.kbh_fk = ttt.kbh_fk and tkn.sanpham_fk = ttt.sanpham_fk and ttt.npp_fk  = tkn.npp_fk and ttt.solo = tkn.solo and ttt.ngayhethan = tkn.ngayhethan "+
            "\n where 1=1 " + condition;
			System.out.println("GET DU LIEU   : "+sql);
		ResultSet rs = db.get(sql);
		
		int i = 1;
		if(rs!=null)
		{
			
			
			try 
			{// se do rong cho cac cot se dung
				cells.setColumnWidth(0, 25.0f);
				cells.setColumnWidth(1, 25.0f);
				cells.setColumnWidth(2, 30.0f);
				cells.setColumnWidth(3, 15.0f);
				cells.setColumnWidth(4, 15.0f);			
				cells.setColumnWidth(5, 15.0f);	
				cells.setColumnWidth(6, 15.0f);	
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);
			
				
				Cell cell = null;
				while(rs.next())// lap den cuoi bang du lieu
				{
					 int cot = 26;
					//lay tu co so du lieu, gan bien
					String Channel = rs.getString("Channel");
					String SKU =rs.getString("SKU");
					String Piece =rs.getString("Piece");			
										
					String Warehouse = rs.getString("Warehouse");
					String DistributorCode =rs.getString("Distributor_code");
					String SkuCode = rs.getString("SKU_code");		
					double Quantily = rs.getDouble("Quantily");
					String BusinessUnit = rs.getString("Business_unit");
					String Category = rs.getString("Category");
					String Brands = rs.getString("Brans");
					double DonGia = rs.getDouble("DonGia");
					double Amount = rs.getDouble("AVAILABLE")*DonGia;
					cell = cells.getCell(i,cot++); cell.setValue(Channel);
					cell = cells.getCell(i,cot++); cell.setValue(SKU);
					cell = cells.getCell(i,cot++); cell.setValue(rs.getDouble("AVAILABLE"));
					cell = cells.getCell(i,cot++); cell.setValue(Warehouse);				
					cell = cells.getCell(i,cot++); cell.setValue(DistributorCode);
					cell = cells.getCell(i,cot++); cell.setValue(SkuCode);
					cell = cells.getCell(i,cot++); cell.setValue(Quantily);
					cell = cells.getCell(i,cot++); cell.setValue(BusinessUnit);
					cell = cells.getCell(i,cot++); cell.setValue(Category);
					cell = cells.getCell(i,cot++); cell.setValue(Brands);
					cell = cells.getCell(i,cot++); cell.setValue(Amount);
					cell = cells.getCell(i,cot++); cell.setValue( rs.getDouble("BOOKED"));
					cell = cells.getCell(i,cot++); cell.setValue( rs.getDouble("SoLuong"));
					
					
					if(obj.gettype().trim().equals("1"))
					{
				    	 cell = cells.getCell(i,cot++); 	cell.setValue(rs.getString("solo"));  ReportAPI.setCellHeader(cell);
				 	     cell = cells.getCell(i,cot++); 	cell.setValue(rs.getString("ngayhethan"));  ReportAPI.setCellHeader(cell);
				 	    cell = cells.getCell(i,cot++); 	cell.setValue(rs.getString("ngaynhapkho"));  ReportAPI.setCellHeader(cell);
				    }
					
					 cell = cells.getCell(i,cot++); 	cell.setValue(rs.getDouble("TonTamTinh"));
					 
					i++;
					
				}
				
				if(i==1)
					throw new Exception("Xin loi.Khong co bao cao trong thoi gian nay...!");
				//create pivot
				 getAn(workbook,49);
		
			}catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println(ex.toString());
				throw new Exception(ex.getMessage());
				
			}
			finally{
				if(rs!=null)
					rs.close();
				if(db != null)
					db.shutDown();
				
			}
		}	    
	   return true;
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
	private void getAn(Workbook workbook,int i)
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    Cells cells = worksheet.getCells();
	    for(int j = 26; j <= i; j++)
	    { 
	    	cells.hideColumn(j);
	    }
		
	}
	
	private String getDate() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);	
	}
}
