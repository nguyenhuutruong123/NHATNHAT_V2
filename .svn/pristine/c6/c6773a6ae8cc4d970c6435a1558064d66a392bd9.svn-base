package geso.dms.center.servlets.reports;
 
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.util.Utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
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
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class IventoryTT_Svl extends HttpServlet 
{	 
	private static final long serialVersionUID = 1L;
	public IventoryTT_Svl() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		Utility util = new Utility();
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect("/KHUONGDUY/index.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		String userTen = (String) session.getAttribute("userTen");
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		
		

		IStockintransit obj = new Stockintransit();	
		
		obj.setView(util.antiSQLInspection(request.getParameter("view"))!=null?
				util.antiSQLInspection(request.getParameter("view")):"TT");
		
		obj.settungay("");
		obj.setdenngay("");
		obj.setMsg("");
		obj.setnppId("");
		obj.setuserId(userId);
		obj.init_user();

		session.setAttribute("obj", obj);
		session.setAttribute("userTen", userTen);
		String nextJSP = request.getContextPath() + "/pages/Center/InventoryTT.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		OutputStream out = response.getOutputStream(); 
		IStockintransit obj = new Stockintransit();	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect("/KHUONGDUY/index.jsp");
			return;
		}
		String nextJSP = request.getContextPath() + "/pages/Center/InventoryTT.jsp";
		Utility util = new Utility();
		try {

			obj.setView(util.antiSQLInspection(request.getParameter("view"))!=null?
					util.antiSQLInspection(request.getParameter("view")):"TT");
			
			obj.setuserTen((String) session.getAttribute("userTen")!=null?
					(String) session.getAttribute("userTen"):"");

			obj.settungay(util.antiSQLInspection(request.getParameter("tungay"))!=null?
					util.antiSQLInspection(request.getParameter("tungay")):"");

			obj.setuserId(util.antiSQLInspection(request.getParameter("userId"))!=null?
					util.antiSQLInspection(request.getParameter("userId")):"");

			obj.setnppId(util.antiSQLInspection(request.getParameter("nppId"))!=null?
					util.antiSQLInspection(request.getParameter("nppId")):"");

			obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId"))!=null?
					util.antiSQLInspection(request.getParameter("kenhId")):"");

			obj.setdvkdId(util.antiSQLInspection(request.getParameter("dvkdId"))!=null?
					util.antiSQLInspection(request.getParameter("dvkdId")):"");


			obj.setvungId(util.antiSQLInspection(request.getParameter("vungId"))!=null?
					util.antiSQLInspection(request.getParameter("vungId")):"");

			obj.setnhanhangId(util.antiSQLInspection(request.getParameter("nhanhangId"))!=null?
					util.antiSQLInspection(request.getParameter("nhanhangId")):"");

			obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId"))!=null?
					util.antiSQLInspection(request.getParameter("khuvucId")):"");

			obj.setchungloaiId(util.antiSQLInspection(request.getParameter("chungloaiId"))!=null?
					util.antiSQLInspection(request.getParameter("chungloaiId")):"");

			obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlid"))!=null?
					util.antiSQLInspection(request.getParameter("dvdlid")):"");

			String piece = util.antiSQLInspection(request.getParameter("piece"));
			if(piece == null)
				piece = "0";		
			obj.setLon_hon_0(piece);
			String laysolo = util.antiSQLInspection(request.getParameter("laysolo"));
			if(laysolo==null)
			{
				laysolo="0";
			}
			obj.settype(laysolo);

			String date = util.antiSQLInspection(request.getParameter("date"));
			if(date==null)
			{
				date="0";
			}
			obj.setxemtheo(date);


			obj.init_user();
			session.setAttribute("obj", obj);

			String action = util.antiSQLInspection(request.getParameter("action"));
			if(action.equals("taomoi"))
			{
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=TonHienTai_"+util.setTieuDe(obj)+".xlsm");

				boolean isTrue = CreatePivotTable(out, obj);
				if(!isTrue)
				{
					PrintWriter writer = new PrintWriter(out);
					writer.write("Khong tao duoc bao cao trong thoi gian nay...!!!");
				}
				return;
			}

			response.sendRedirect(request.getContextPath() + "/pages/Center/InventoryTT.jsp");
		} catch (Exception ex) {
			ex.printStackTrace();
			obj.setMsg(ex.getMessage());
			response.sendRedirect(nextJSP);
		}
	}

	private boolean CreatePivotTable(OutputStream out,IStockintransit obj) throws Exception
	{   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();		
		
		String chuoi=getServletContext().getInitParameter("path") + "\\Iventory(NPP).xlsm";
		
	    if(obj.gettype().trim().equals("1")){
	    	chuoi=getServletContext().getInitParameter("path") + "\\Iventory(NPP_LO).xlsm";
	    }
		
		fstream = new FileInputStream(chuoi);

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		CreateStaticHeader(workbook, obj);	     
		boolean isTrue = CreateStaticData(workbook, obj);
		if(!isTrue){
			return false;
		}
		workbook.save(out);

		fstream.close();
		return true;
	}

	
	private void CreateStaticHeader(Workbook workbook , IStockintransit obj) throws Exception 
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
		cell.setValue("Từ ngày  " + obj.gettungay() + "      Đến ngày " + obj.getdenngay());    
		cell = cells.getCell("A3");getCellStyle(workbook,"A3",Color.NAVY,true,9);
		cell.setValue("Ngày Tạo : " + Utility.getNgayHienTai() );
		cell = cells.getCell("A4");getCellStyle(workbook,"A4",Color.NAVY,true,9);
		cell.setValue("Tạo Bởi:  " + obj.getuserTen());

		//tieu de, hang dau tien cua bang du lieu, cell la toa do cua exel

		int cot = 26;

		cell = cells.getCell(0,cot++); cell.setValue("Kenh Ban Hang");
		cell = cells.getCell(0,cot++); cell.setValue("Ten San Pham");
		cell = cells.getCell(0,cot++); cell.setValue("So Luong ");	  
		cell = cells.getCell(0,cot++); cell.setValue("Kho");
		cell = cells.getCell(0,cot++); cell.setValue("Ma Nha Phan Phoi");
		cell = cells.getCell(0,cot++); cell.setValue("TEN NHA PHAN PHOI");
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
			cell = cells.getCell(0,cot++); 	cell.setValue("SoDangKy");  ReportAPI.setCellHeader(cell);
		}


		worksheet.setName("Sheet1");
	}
	private boolean CreateStaticData(Workbook workbook,IStockintransit obj) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();
		Utility utl = new  Utility();
		

		String condition = "";
		String cond_detail = "";
		
		
		if (obj.getkenhId().length() > 0)
			cond_detail = cond_detail + " and k.kbh_fk ='" + obj.getkenhId() + "'";	
		if(obj.getLon_hon_0().equals("1"))
		{
			cond_detail += " and k.AVAILABLE > 0";
		}
		if(obj.getPhanloai().equals("2"))
		{
			if(obj.getnppId().length() > 0)
			{
				cond_detail+= " and k.npp_fk ='" + obj.getnppId() + "'";	
			}
			else
				if (obj.getkhuvucId().length() > 0)
					cond_detail+= " and k.npp_fk in ( select pk_seq from nhaphanphoi where khuvuc_fk ="+obj.getkhuvucId()+" )";
				else
					if (obj.getvungId().length() > 0)
						cond_detail+= " and k.npp_fk in ( select pk_seq from nhaphanphoi where khuvuc_fk  in ( select pk_seq from khuvuc where vung_FK = "+obj.getvungId()+"  ) )";
					
			cond_detail +=" and k.npp_fk in ("+geso.dms.center.util.Utility.Quyen_npp(obj.getuserId())+") ";
			cond_detail +=" and k.kbh_fk in ("+geso.dms.center.util.Utility.Quyen_kenh(obj.getuserId())+") ";
		}
		else
		{
			String nppId = utl.getIdNhapp(obj.getuserId());
			obj.setnppId(nppId);
			cond_detail+= " and k.npp_fk ='" + nppId + "'";
			
		}
		
		
		////////
		if (obj.getdvkdId().length() > 0)
			condition = condition + " and dvkd.PK_SEQ ='" + obj.getdvkdId() + "'";				
		if (obj.getnhanhangId().length() > 0)
			condition = condition + " and nh.pk_seq ='" + obj.getnhanhangId() + "'";
		if (obj.getchungloaiId().length() > 0)
			condition = condition + " and cl.pk_seq ='" + obj.getchungloaiId() + "'";
		
		if(obj.getdvdlId().length()>0)
		{
			condition+= " and sp.dvdl_fk='"+obj.getdvdlId()+"' "; 
		}


		String bang=" select kho_fk,kbh_fk,sanpham_fk,npp_fk,soluong,AVAILABLE,booked ,'' solo,'' as ngayhethan,'' ngaynhapkho, '' SoDangKy  " +
		" from nhapp_kho k where  1= 1 " + cond_detail ;

		if(obj.gettype().trim().equals("1")){

			bang=" select kho_fk,kbh_fk,k.sanpham_fk,npp_fk,soluong,AVAILABLE,booked,solo, ngayhethan,ngaynhapkho,'' SoDangKy " +
			" from nhapp_kho_chitiet k " +
			" where  1= 1 " + cond_detail ;
			
			if(geso.dms.center.util.Utility.parseInt(obj.getDate()) > 0)
			{
				bang += " and DATEDIFF(month,getdate(),ngayhethan) <= "+obj.getDate()+" ";
			}
			
		}



		String sql ="\n  select npp.ten as tennpp,isnull(TKN.SoDangKy,'') as SoDangKy,isnull(TKN.ngaynhapkho,'') as ngaynhapkho,tkn.SoLuong,tkn.solo, tkn.ngayhethan,kbh.ten as Channel,sp.ma as Sku_code,sp.ten as SKU,tkn.AVAILABLE as Piece,k.ten as Warehouse, "+
		"\n tkn.npp_fk as Distributor_code,"+
		"\n nh.ten as Brans, isnull(cast(tkn.AVAILABLE/cast(qc.soluong1 as float) as float), 0) as Quantily,"+
		"\n dvkd.donvikinhdoanh as Business_unit,cl.ten as Category, "+
		"\n  tkn.AVAILABLE  "+
		"\n  	,  [dbo].[GiaBanLeNppSanPham](tkn.kbh_fk,npp.pk_seq,sp.pk_seq,'"+Utility.getNgayHienTai()+"' )  DonGia " +
		"\n		,booked "+  
		"\n from ( "+bang+") tkn " +
		"\n inner join nhaphanphoi npp on npp.pk_seq =  tkn.NPP_FK " +
		"\n left join kenhbanhang kbh on kbh.pk_seq = tkn.kbh_fk "+
		"\n inner join sanpham sp on sp.pk_seq = tkn.sanpham_fk "+
		"\n left join donvikinhdoanh dvkd on dvkd.pk_seq = sp.dvkd_fk "+
		"\n inner join kho k on k.pk_seq = tkn.kho_fk "+
		"\n left join quycach qc on  qc.sanpham_fk = sp.pk_seq and sp.dvdl_fk =qc.dvdl1_fk and qc.dvdl2_fk= 100018"+
		"\n left join donvidoluong dvdl on dvdl.pk_seq = sp.dvdl_fk "+
		"\n left join chungloai cl on cl.pk_seq = sp.chungloai_fk "+
		"\n left join nhanhang nh on nh.pk_seq = sp.nhanhang_fk "+			
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
					String tennpp = rs.getString("tennpp");

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
					cell = cells.getCell(i,cot++); cell.setValue(tennpp);
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
						cell = cells.getCell(i,cot++); 	cell.setValue(rs.getString("SoDangKy"));  ReportAPI.setCellHeader(cell);
					}


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
}
