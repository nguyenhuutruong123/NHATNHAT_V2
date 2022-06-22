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


public class BCAnhQuaTang extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String setQuery(IStockintransit obj) 
	{
		String query = " select ddkd.maFAST as MANVBH,ddkd.TEN as TENNVBH,b.maFAST as MAKH,b.TEN as TENKH,a.ngaygiotao as 'NgayGioChup',a.ANHCHUP as anhchup, isnull(b.diachigiaohang,'')diachigiaohang  " + 
				 "\n from  " + 
				 "\n KHACHHANG_ANHquatang a  " + 
				 "\n inner join KHACHHANG b on a.KHACHHANG_FK =b.PK_SEQ  " + 
				 //"\n left join DKTRUNGBAY_KHACHHANG c on c.KHACHHANG_FK = b.pk_Seq and c.DKTRUNGBAY_FK in (select pk_seq from dangkytrungbay) " +
				 "\n inner join NHAPHANPHOI npp on b.NPP_FK = npp.PK_SEQ " + 
				// "\n inner join DAIDIENKINHDOANH ddkd on a.DDKD_FK = ddkd.PK_SEQ " + 
				 "\n inner join DAIDIENKINHDOANH ddkd on a.ddkd_fk=ddkd.PK_SEQ  where 1=1  ";
		if(obj.gettungay().length() >0 )
			query += " and a.NGAY >= '"+obj.gettungay()+"'";
		if(obj.getdenngay().length() >0 )
			query += " and a.NGAY <= '"+obj.getdenngay()+"'";
		if(obj.getkhId().length() >0 )
			query += " and b.pk_seq="+obj.getkhId() ;
		else if(obj.getDdkd().length() >0 )
			query += " and ddkd.pk_seq="+obj.getDdkd() ;
		else if(obj.getnppId().length() >0 )
			query += " and npp.pk_seq="+obj.getnppId() ;
			
		query +="\n order by a.ngay desc  "; 
			

		System.out.println("____BC anh qua tang: " + query);
		return query;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
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

		String nextJSP = request.getContextPath() + "/pages/Center/BCAnhQuaTang.jsp";
		response.sendRedirect(nextJSP);
	}


	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
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

		String action = request.getParameter("action");
		if (action.equals("tao")) 
		{
			try 
			{
				request.setCharacterEncoding("utf-8");

				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCAnhQuaTang.xlsm");
				System.out.println("111111");
				OutputStream out = response.getOutputStream();

				String query = setQuery(obj);

				CreatePivotTable(out, obj, query);
				
				
			} 
			catch (Exception ex) 
			{
				request.getSession().setAttribute("errors", ex.getMessage());
			}
			/*obj.setMsg("Chưc năng sẽ phát triển sau!");
			
			System.out.println("aaaaaaaaaaaaaaaa");
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "/ThienThao/pages/Center/BCAnhQuaTang.jsp";
			response.sendRedirect(nextJSP);*/
		}
		else
		{
			obj.init();
			if(action.equals("xemtrenweb"))
			{
				obj.setDataRs2( setQuery(obj));
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/BCAnhQuaTang.jsp";
			response.sendRedirect(nextJSP);

		}

	}

	private void CreatePivotTable(OutputStream out,IStockintransit obj, String query) throws Exception {
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
	}

	private void CreateHeader(Workbook workbook,IStockintransit obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);	    
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();	 

		cells.setRowHeight(0, 20.0f);	    
		Cell cell = cells.getCell("A1");	
		ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Báo cáo ảnh quà tặng");
		cell = cells.getCell("A2");
		ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());


		int dongbatdauheader =7;
		int cotbatdau =0;

		cells.setRowHeight(dongbatdauheader, 50f);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);cell.setValue("Mã NVBH");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);cell.setValue("Tên NVBH");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("Mã KHÁCH HÀNG");
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
		

	/*	cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("MÃ NVBH");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("TRÌNH DƯỢC VIỆC");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("TUYẾN");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("MÃ KH");	
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("KHÁCH HÀNG");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);

		cell = cells.getCell(dongbatdauheader,cotbatdau++);		cell.setValue("NGÀY CHỤP");		
		ReportAPI.setCellBackground(cell, Color.SILVER, BorderLineType.THIN, true, 0);*/


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
				
		
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("MANVBH"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("TENNVBH"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("MAKH"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);


				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("TENKH"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("DIACHIGIAOHANG"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
				
				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("GHICHU"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);


				cell = cells.getCell(index,cotbatdau++);cell.setValue(rs.getString("NgayGioChup"));	
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);

				cells.setRowHeight(index, 50f);
				String img = rs.getString("anhchup");
				String dir = getServletContext().getInitParameter("pathhinh")+"\\AnhQuaTang";
				System.out.println("dir: "+dir);
				img=dir+"\\"+img;
				//img="D:\\hinhlogo.png";
				//img=dir+"anhtest.jpg";
				
				try
				{
					cell = cells.getCell(index,cotbatdau);	
					//cell.setValue(img);
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
					int pictureIndex=worksheet.getPictures().add(index,cell.getColumnIndex(),index,cell.getColumnIndex(),img);
					Picture picture = worksheet.getPictures().get(pictureIndex);						
					picture.setWidth(100);
					picture.setHeight(50);	
					//Set the placement type
					picture.setPlacement(PlacementType.FREE_FLOATING);
				} catch (Exception e)
				{
						//System.out.println("Exception: " + e.getMessage());
				}
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
					"	select Ngay,KhachHang_fk,cttb_fk,anhchup,npp_fk,ddkd_fk,"+userId+" from KHACHHANG_ANHCHUP where	anhchup =N'"+anhchup+"'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Lỗi tạo log:" +query;
			}
			query = "delete KHACHHANG_ANHCHUP where	anhchup =N'"+anhchup+"'";
			if(!db.update(query))
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
