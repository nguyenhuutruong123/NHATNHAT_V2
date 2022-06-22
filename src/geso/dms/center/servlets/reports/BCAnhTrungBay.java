package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.PlacementType;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.aspose.cells.Picture;


public class BCAnhTrungBay extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String setQuery(IStockintransit obj) 
	{
		
		if(obj.getCttbId().trim().length() <=0)
			return "";
		String query =   "\n	select distinct cttb.SCHEME,cttb.DIENGIAI as CTTB,v.TEN as Vung, tt.TEN as tinhthanh,npp.MaFAST as nppMa,npp.TEN as NPP -- " + 
						 "\n 			,ddkd.MANHANVIEN as tdvMa, ddkd.TEN as TDV,isnull(c.ghichu,'')GhiChu,isnull(kh.diachigiaohang,'')DiaChiGiaoHang " +
						 "\n		, (select top 1 ngayId from tuyenbanhang where   pk_seq in ( select tbh_fk from khachhang_tuyenbh where khachhang_fk = anh.khachhang_fk )  )NGAYID   " +
						 "\n		,kh.maFAST as khMa, kh.TEN as KhachHang,convert(varchar,anh.thoidiem,120) as NgayChup,anh.ANHTRUNGBAY as ANHCHUP  -- " + 
						 "\n 	from CTTB_KHACHHANG_ANHCHUP anh -- " + 
						 "\n 	inner join DAIDIENKINHDOANH ddkd on anh.DDKD_FK = ddkd.PK_SEQ -- " + 
						 "\n 	inner join KHACHHANG kh on kh.PK_SEQ = anh.KHACHHANG_FK -- " + 
						 "\n 	inner join NHAPHANPHOI npp on kh.NPP_FK = npp.PK_SEQ -- " + 
						 "\n 	inner join CTTRUNGBAY cttb on anh.cttb_fk= cttb.PK_SEQ -- " + 
						 "\n 	left join DKTRUNGBAY_KHACHHANG c on c.KHACHHANG_FK = kh.pk_seq and c.DKTRUNGBAY_FK  = anh.DKTRUNGBAY_FK  " + 
						 "\n 	left join TINHTHANH tt on tt.PK_SEQ = npp.TINHTHANH_FK -- " + 
						 "\n 	left join VUNG v on v.PK_SEQ = tt.VUNG_FK -- " +
						 "\n 	where ddkd.ISPG=0 AND cttb.pk_seq = "+obj.getCttbId();
		
		query += " and anh.DDKD_FK in (" + Utility.PhanQuyenDDKD(obj.getuserId()) + ")" ;
		query += " and kh.NPP_FK in (" + Utility.Quyen_npp(obj.getuserId()) + ")" ;
		
		
		if(obj.gettungay().length() >0 )
			query += " and convert(varchar,anh.thoidiem,120) >= '"+obj.gettungay()+"'";
		if(obj.getdenngay().length() >0 )
			query += " and convert(varchar,anh.thoidiem,120) <= '"+obj.getdenngay()+"'";
		
		if(obj.getkhId().length() >0 )
			query += " and kh.pk_seq="+obj.getkhId() ;
		else if(obj.getDdkd().length() >0 )
			query += " and ddkd.pk_seq="+obj.getDdkd() ;
		else if(obj.getnppId().length() >0 )
			query += " and npp.pk_seq="+obj.getnppId() ;
		else if(obj.getTinhthanhid().trim().length() >0)
			query += " and tt.pk_seq="+obj.getTinhthanhid() ;
		else if(obj.getvungId().trim().length() >0)
			query += " and v.pk_seq="+obj.getTinhthanhid() ;
		
		if (obj.getnppId().length()>0) {
			query += " and anh.KHACHHANG_FK in (select khachhang_fk from khachhang_npp where npp_fk = "+obj.getnppId();}
		else {
			query += " and anh.KHACHHANG_FK in (select khachhang_fk from khachhang_npp where npp_fk in " +
					 "\n (select npp_fk from phamvihoatdong where nhanvien_fk= " +obj.getuserId()+"))";}
	
		query +="\n order by NgayChup desc,cttb.SCHEME,Vung,tinhthanh,nppMa,tdvMa,NGAYID,khMa  "; 
			
		System.out.println("____BC anh trung bay: " + query);
		return query;
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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


		String view=request.getParameter("view");
		if(view == null)
			view = "";
		
		
		String delete=request.getParameter("delete");
		if(delete != null)
		{
			System.out.println("delete = " + delete);
			String msg = Delete(delete, userId);
			obj.setMsg(msg);
			System.out.println("msg = " + msg);
		//	session.setAttribute("errors", msg);
		}
		else
			System.out.println("delete = null");
		obj.setLoaiMenu(view);

		obj.setuserId(userId);
		obj.init();	

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);

		String nextJSP = request.getContextPath() + "/pages/Center/BCAnhTrungBay.jsp";
		response.sendRedirect(nextJSP);
	}


	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
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
		Utility  util = new Utility();

		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			userId = "";
		obj.setuserId(userId);

		String userTen = (String) session.getAttribute("userTen");
		obj.setuserTen(userTen);

		
		String view=request.getParameter("view");
		if(view == null)
			view = "";
		
		obj.setLoaiMenu(view);
		
		
		
		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		if (vungId == null)
			vungId = "";
		obj.setvungId(vungId);

		String tinhthanhId = util.antiSQLInspection(request.getParameter("tinhthanhId"));
		if (tinhthanhId == null)
			tinhthanhId = "";
		obj.setTinhthanhid(tinhthanhId);

		String nppId="";
		nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if (nppId == null)
			nppId = "";
		obj.setnppId(nppId);
	
		System.out.println("nppId ="  + obj.getnppId());
		
		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";
		obj.setDdkd(ddkdId);

		String khId = util.antiSQLInspection(request.getParameter("khId"));
		if (khId == null)
			khId = "";
		obj.setkhId(khId);

		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);

		String cttbId = util.antiSQLInspection(request.getParameter("cttbId"));
		if (cttbId == null)
			cttbId = "";
		obj.setCttbId(cttbId);

		String action = request.getParameter("action");
		if (action.equals("tao")) 
		{
			try 
			{
				request.setCharacterEncoding("utf-8");

				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCAnhTrungBay.xlsm");

				OutputStream out = response.getOutputStream();

				String query = setQuery(obj);

				//CreatePivotTable(out, obj, query);
				CreatePivotTable(out, response, request, obj, query);
				
				
			} 
			catch (Exception ex) 
			{
				request.getSession().setAttribute("errors", ex.getMessage());
			}
		}
		else
		{
			obj.init();
			if(action.equals("xemtrenweb"))
			{
				obj.setDataRs( setQuery(obj));
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/BCAnhTrungBay.jsp";
			response.sendRedirect(nextJSP);

		}

	}
	
	private void CreatePivotTable(OutputStream out, HttpServletResponse response, HttpServletRequest request, IStockintransit obj, String query) throws IOException 
	{
		HttpSession session = request.getSession();
		String chuoi = getServletContext().getInitParameter("path") + "\\BCAnhTrungBay_new.xlsm";
		FileInputStream fstream = new FileInputStream(chuoi);
		Workbook workbook = new Workbook();
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		CreateHeader(workbook,obj); 
		try {
			FillData(workbook, query, obj);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
		
		obj.init();
		session.setAttribute("obj", obj);
		
		boolean flag = false;
		try {
			workbook.save(out);
			fstream.close();
			flag = true;
		}
		catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}		
		String nextJSP = request.getContextPath() + "/pages/Center/BCAnhTrungBay.jsp";
		if (!flag) { response.sendRedirect(nextJSP); }
		else { return; }		
	}

	/*private void CreatePivotTable(OutputStream out,IStockintransit obj, String query) throws Exception {
		try 
		{

			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			CreateHeader(workbook,obj); 
			FillData(workbook, query, obj);			
			workbook.save(out);

		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}*/

	private void CreateHeader(Workbook workbook,IStockintransit obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);	    
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();	 

		cells.setRowHeight(0, 20.0f);	    
		Cell cell = cells.getCell("A1");	
		ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Báo cáo ảnh trưng bày");
		cell = cells.getCell("A2");
		ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());


		int dongbatdauheader =7;
		int cotbatdau =0;

		cells.setRowHeight(dongbatdauheader, 50f);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);cell.setValue("CTTB");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);cell.setValue("MIỀN");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("TỈNH THÀNH");
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("MÃ CN/ĐT");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("CHI NHÁNH ĐỐI TÁC");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("MÃ TDV");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("TRÌNH DƯỢC VIỆC");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("TUYẾN");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("MÃ KH");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("KHÁCH HÀNG");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("ĐỊA CHỈ");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("GHI CHÚ");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("NGÀY CHỤP");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("ẢNH CHỤP");  
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

	}
	private void FillData(Workbook workbook, String query, IStockintransit obj) throws Exception
	{
		ResultSet rs = null;
		dbutils db = new dbutils();
		
		try
		{	
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();

			for(int i=0;i<15;++i)
			{
				cells.setColumnWidth(i, 15.0f);	    	
			}	
			rs = db.get(query);
			int index = 8;
			Cell cell = null;

			while (rs.next())
			{
				int cotbatdau = 0;
				
		
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("CTTB"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("Vung"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("tinhthanh"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				

				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("nppMa"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("NPP"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("tdvMa"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("TDV"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				

				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("NGAYID"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("khMa"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);


				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("KhachHang"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("DiaChiGiaoHang"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("GhiChu"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);


				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("NgayChup"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

				cells.setRowHeight(index, 50f);
				String img = rs.getString("ANHCHUP");
				//String dir =  getServletContext().getInitParameter("pathhinh") +"AnhTrungBay\\";
				/*img=dir+img;*/
				
				String dir = getServletContext().getInitParameter("pathhinhJSP")+"AnhTrungBay";
				img=dir+"/"+img;
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(img);	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				//img="D:\\hinhlogo.png";
				//img=dir+"anhtest.jpg";
				System.out.println("img : "+ img);
			/*	try
				{
					cell = cells.getCell(index,cotbatdau);	
					//cell.setValue("aaaaaaaaaaaahemaha");
					
					cells.setColumnWidth(index, 30);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					cell.getStyle().setHAlignment(TextAlignmentType.RIGHT);
					
					@SuppressWarnings("deprecation")
					int pictureIndex=worksheet.getPictures().add(index,cell.getColumnIndex(),index,cell.getColumnIndex(),img);
					Picture picture = worksheet.getPictures().get(pictureIndex);	
					
					picture.setWidth(100);
					picture.setHeight(50);	
					//Set the placement type
					picture.setPlacement(PlacementType.MOVE_AND_SIZE);
				//	picture.setWidth(100);
				//	picture.setHeight(50);

				} catch (Exception e)
				{ System.out.println("Exception: " + e.getMessage()); }*/
				index++;

			}
			if(rs!=null){
				rs.close();
			}	

			ReportAPI.setHidden(workbook,14);

		}
		catch(Exception ex)
		{
			if(rs != null)
			{
				rs.close();
			}
			System.out.println("Error Here : "+ex.toString());
			if(db != null)
				db.shutDown();

			ex.printStackTrace();

			throw new Exception(ex.getMessage()  );
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getPiVotName()
	{
		String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
		String name = sdf.format(cal.getTime());
		name = name.replaceAll("-", "");
		name = name.replaceAll(" ", "_");
		name = name.replaceAll(":", "");
		return "_" + name;

	}

	
	public String Delete(String anhchup,String userId)
	{
		dbutils db = new dbutils();
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "insert KHACHHANG_ANHCHUP_LOG(Ngay,KhachHang_fk,cttb_fk,anhchup,npp_fk,ddkd_fk,NguoiXoa)" +
					"	select convert(varchar(10),thoidiem,120),KhachHang_fk,cttb_fk,ANHTRUNGBAY,null,ddkd_fk,"+userId+" from CTTB_KHACHHANG_ANHCHUP where	ANHTRUNGBAY =N'"+anhchup+"' ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Lỗi tạo log:" +query;
			}
			query = "delete CTTB_KHACHHANG_ANHCHUP where	anhtrungbay =N'"+anhchup+"'";
			if(db.updateReturnInt(query)!=1)
			{
				db.getConnection().rollback();
				return "Lỗi Xóa ảnh:" +query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			return "Xóa ảnh thành công!";
		}catch(Exception e)
		{
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
			return "Exeption:" + e.getMessage();
			
		}
	}
	
	
}
