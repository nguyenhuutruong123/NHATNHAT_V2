package geso.dms.center.servlets.reports;

import geso.dms.center.beans.report.IBcTheKho;
import geso.dms.center.beans.report.imp.BcTheKho;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.distributor.beans.reports.IBCBangKeHoaDon;
import geso.dms.distributor.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.record.formula.functions.Npv;

import com.aspose.cells.BorderLineType;
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
import com.google.gson.annotations.Until;

@WebServlet("/BcTheKhoSvl")
public class BcTheKhoSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BcTheKhoSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IBcTheKho obj;
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

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		String trungtam=request.getParameter("trungtam");

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String loaihoadon =  util.antiSQLInspection(request.getParameter("loaihoadon"));
		if (loaihoadon == null)
			loaihoadon = "0";

		obj = new BcTheKho();
		obj.setUserId(userId);


		String view = request.getParameter("view");
		if(view == null)
			view = "";
		obj.setView(view);

		if(view.length()<=0)
		{
			String  nppId=util.getIdNhapp(userId);
			obj.setNppId(nppId);
		}
		String nextJSP = "";
		obj.init("");
		System.out.println("vao kho tt"+trungtam==null?"null":trungtam);
		if(trungtam!=null)
		{
			nextJSP = request.getContextPath() + "/pages/Center/BcTheKhoTT.jsp";
		}
		else
		{
			nextJSP = request.getContextPath() + "/pages/Center/BcTheKho.jsp";
		}

		session.setAttribute("obj", obj);
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		String userTen = (String) session.getAttribute("userTen");
		
		
		OutputStream out = response.getOutputStream();

		String action = request.getParameter("action");
		if (action == null)
			action = "";

		IBcTheKho obj = new BcTheKho();
		obj.setUserId(userId);
		obj.setUserName(userTen);
		String view = request.getParameter("view");
		if(view == null)
			view = "";
		obj.setView(view);

		if(view.length()<=0)
		{
			String  nppId=util.getIdNhapp(userId);
			obj.setNppId(nppId);
		}

		String tungay = util.antiSQLInspection(request.getParameter("Sdays")==null?"": request.getParameter("Sdays"));
		obj.setTuNgay(tungay);

		String denngay =  util.antiSQLInspection(request.getParameter("Edays")==null?"": request.getParameter("Edays"));
		obj.setDenNgay(denngay);

		String vungId =  util.antiSQLInspection(request.getParameter("vungId")==null?"": request.getParameter("vungId"));
		obj.setVungId(vungId);

		String kbhId =  util.antiSQLInspection(request.getParameter("kbhId")==null?"": request.getParameter("kbhId"));
		obj.setKbhId(kbhId);    


		String ttId =  util.antiSQLInspection(request.getParameter("ttId")==null?"": request.getParameter("ttId"));
		obj.setTtId(ttId);   

		String nhomId = util.antiSQLInspection( request.getParameter("nhomId")==null?"": request.getParameter("nhomId"));
		obj.setNhomId(nhomId);


		String khId =  util.antiSQLInspection(request.getParameter("khId")==null?"": request.getParameter("khId"));
		obj.setKhId(khId);

		String ddkdId =  util.antiSQLInspection( request.getParameter("ddkdId")==null?"": request.getParameter("ddkdId"));
		obj.setDdkdId(ddkdId);

		String spId = util.antiSQLInspection(request.getParameter("spId")==null?"": request.getParameter("spId"));
		obj.setSpId(spId);


		String nppId = util.antiSQLInspection(request.getParameter("nppId")==null?"": request.getParameter("nppId"));
		obj.setNppId(nppId);


		String loaihoadon = util.antiSQLInspection(request.getParameter("loaidonhang")==null?"": request.getParameter("loaidonhang"));
		obj.setLoaiHoaDon(loaihoadon);


		String khoId = util.antiSQLInspection(request.getParameter("khoId")==null?"": request.getParameter("khoId"));
		obj.setKhoId(khoId);

		String TT = util.antiSQLInspection(request.getParameter("trungtam")==null?"": request.getParameter("trungtam"));
		obj.setAction(action);

		obj.setType(util.antiSQLInspection(request.getParameter("type")) == null ? "" : util.antiSQLInspection(request.getParameter("type")));

		System.out.println("___ATION "+action);


		if (action.equals("excel")  )
		{
			try
			{ 
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BcTheKho.xlsm");
				FileInputStream fstream;
				if(TT.equals("TT"))
				{
					com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
					workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
					
					obj.setUserId(userId);

					obj.initTT();

					String query=obj.getQueryHd();
					FillData_TT(workbook, obj, query);
					workbook.save(out);
					return;
				}
				else
				{
					response.setContentType("application/xlsm");
					response.setHeader("Content-Disposition", "attachment; filename=TheKho.xlsm");
					
			    	ExportToExcel( out, obj );
			    	return;
				}

			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				obj.setMsg("Khong the tao pivot.");
			}
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "";
			if(TT.equals("TT"))
				nextJSP = request.getContextPath() + "/pages/Center/BcTheKhoTT.jsp";
			else
				nextJSP = request.getContextPath() + "/pages/Center/BcTheKho.jsp";
			response.sendRedirect(nextJSP); 
		}
		else  if(action.equals("view") || action.equals("next") || action.equals("prev")){

			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			obj.setUserId(userId);
			obj.init("");

			session.setAttribute("obj", obj);
			response.sendRedirect(request.getContextPath() + "/pages/Center/BcTheKho.jsp");
		}

		else if(action.equals("search"))
		{	
			obj.setUserId(userId);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = "";
			if(TT.equals("TT"))
			{
				System.out.println("vao day rui TT:::::::::::::::::::::::::::::::::::::::::::::::::::::::");
				obj.initTT();
				nextJSP = request.getContextPath() + "/pages/Center/BcTheKhoTT.jsp";
			}
			else
			{
				obj.init("");

				nextJSP = request.getContextPath() + "/pages/Center/BcTheKho.jsp";
			}
			response.sendRedirect(nextJSP); 
		}
		else
		{
			session.setAttribute("obj", obj);
			String nextJSP = "";
			nextJSP = request.getContextPath() + "/pages/Center/BcTheKho.jsp";
			obj.init("");
			response.sendRedirect(nextJSP);  
		}

	}

	private boolean FillData(Workbook workbook, IBcTheKho obj, String query) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cell cell = null;

		Cells cells = worksheet.getCells();		
		ResultSet hdRs = obj.getTotalRs();

		Font font = new Font();
		font.setName("Times New Roman");
		font.setColor(Color.BLACK);
		font.setBold(true);
		font.setSize(11);

		if (hdRs != null) 
		{
			try 
			{
				while (hdRs.next()) 
				{					
					cell = cells.getCell("G" + Integer.toString(9));	cell.setValue(hdRs.getDouble("TonDau"));ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 3,font);
					cell = cells.getCell("F" + Integer.toString(10));	cell.setValue(hdRs.getDouble("Nhap"));ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41,font);
					cell = cells.getCell("G" + Integer.toString(11));	cell.setValue(hdRs.getDouble("Xuat"));ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41,font);
					cell = cells.getCell("G" + Integer.toString(12));	cell.setValue(hdRs.getDouble("TonCuoi"));ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 3,font);
				}
				if (hdRs != null) hdRs.close();

				int i = 15;
				hdRs=db.get(query);

				while (hdRs!=null&& hdRs.next()) 
				{					

					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue(hdRs.getString("spMa"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(hdRs.getString("NgayCT"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(hdRs.getString("khMA")==null?"":hdRs.getString("khMA"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(hdRs.getString("khTEN")==null?"":hdRs.getString("khTEN"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getString("NghiepVu"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					if( hdRs.getDouble("SoLuong") >0  )
					{
						cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SoLuong"));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					}
					else
					{
						cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SoLuong")*(-1));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					}

					i++;
				}


				if(db != null) db.shutDown();

				if(i==15){					
					throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
				}

			}catch(Exception ex){
				ex.printStackTrace();
				throw new Exception(ex.getMessage());
			}
		}else{
			return false;
		}		
		return true;

	}

	private boolean FillData_TT(Workbook workbook, IBcTheKho obj, String query) throws Exception
	{
		System.out.println("da vao day nhe :::::::::::::::::::::::::");
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
 
		Cells cells = worksheet.getCells();		
 
		com.aspose.cells.Cell cell = cells.getCell("A1");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( "Thẻ kho");
		Style style = cell.getStyle();
		com.aspose.cells.Font font = new com.aspose.cells.Font();
		font.setColor(Color.RED);//mau chu
		font.setSize(16);// size chu
		font.setBold(true);
		style.setFont(font);
		cell.setStyle(style);	
		
		
		
		cell = cells.getCell("A2");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( "Người tạo:");
		
		cell = cells.getCell("B2");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, false, 0);
		cell.setValue( obj.getUserName());
		
		cell = cells.getCell("A3");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( "Thời gian:");
		
		cell = cells.getCell("B3");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, false, 0);
		cell.setValue(obj.getTuNgay() +" đến " + obj.getDenNgay() );
		
		
		cell = cells.getCell("A4");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( "Vật tư:");
		
		cell = cells.getCell("B4");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( obj.getSpTen());
		
		cell = cells.getCell("A5");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( "Đơn vị:");
		
		cell = cells.getCell("B5");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( obj.getSpDonvi());
		
		cell = cells.getCell("A6");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( "Mã Số:");
		
		cell = cells.getCell("B6");
		ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
		cell.setValue( obj.getSpMa());
		
		
		ResultSet	rs = obj.getDb().get(query);

		ResultSetMetaData rsmd = rs.getMetaData();
		int socottrongSql = rsmd.getColumnCount();
		String querysolo="";
		if(obj.getSolo().trim().length()<0)
		{
			querysolo=" and solo='"+ obj.getSolo() +"' ";
		}
		if(obj.getKhoId().trim().length()<0)
		{
			querysolo +=" and kho_fk='"+ obj.getKhoId() +"' ";
		}
		if(obj.getKbhId().trim().length()<0)
		{
			querysolo +=" and kbh_fk='"+ obj.getKbhId() +"' ";
		}
		String querytondau=    
				"declare @Denngay nvarchar(20),@Tungay nvarchar(20),@sanpham_fk numeric(18,0)   \n "+      
				"set @Denngay='"+obj.getTuNgay()+"'   \n "+      
				"set @sanpham_fk=("+obj.getSpId()+")   \n"+    
				" \n "+      
				" \n  select      "+
				" \n    ISNULL(sum(SOLUONG),0) as XNT "+
 				" \n   from  (    "+
				" \n    "+
				" \n   "+
				" \n  select a.NgayNhap as ngayct,a.pk_seq as soct,a.KhoNhap_FK,'1' npp_fk,b.sanpham_fk,(b.soluong) as soluong,N'Nhập kho' as nghiepvu 																									  "+
				" \n  from ERP_NHAPKHO a inner join ERP_NHAPKHO_SANPHAM  b   																																											  "+
				" \n  on a.PK_SEQ=b.nhapkho_fk     																																																		  "+
				" \n  where a.NgayNhap<@Denngay and a.TRANGTHAI=1   AND b.SANPHAM_FK=@sanpham_fk																																							  "+
				" \n  																																																									  "+
				" \n   union all   																																																						  "+
				" \n  	select  a.NgayChuyen as ngayct,a.pk_seq as soct,a.KhoXuat_FK,a.NPP_FK,b.sanpham_fk,(-1)*(b.soluongchuyen) as soluong,N'Xuất chuyển nội bộ' as nghiepvu  																		  "+
				" \n 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b    																																										  "+
				" \n  	on a.PK_SEQ=b.chuyenkho_fk   																																																	  "+
				" \n  	where  a.NgayChuyen< @Denngay and a.TRANGTHAI=1   AND b.SANPHAM_FK=@sanpham_fk	 																																					  "+
				" \n  																																																									  "+
				" \n  union all   																																																						  "+
				" \n  	select  a.NgayDieuChinh as ngayct,a.pk_seq as soct,a.KhoTT_FK,'1' as npp_fk,b.sanpham_fk,(-1)*(b.tonthucte) as soluong,N'Điều chỉnh tồn kho' as nghiepvu  from ERP_DIEUCHINHTONKHO a inner join ERP_DIEUCHINHTONKHO_SANPHAM b     "+
				" \n  	on a.PK_SEQ=b.dieuchinh_fk   																																																	  "+
				" \n  	where a.NgayDieuChinh<@Denngay and TRANGTHAI=1   AND b.SANPHAM_FK=@sanpham_fk	 																																					  "+
				" \n 																																																									  "+
				" \n  union all   																																																						  "+
				" \n  	select ngayct,soct,data.khott_FK,data.NPP_FK,data.SANPHAM_FK,(-1)*(data.SOLUONG),N'Xuất bán' as nghiepvu																														  "+
				" \n 	from (   																																																						  "+
				" \n  	select  a.NGAYXUATHD as ngayct,a.pk_seq as soct,(select top(1) dh.Kho_FK	   																																					  "+
				" \n  				from ERP_DONDATHANG dh    																																															  "+
				" \n  				inner join ERP_HOADON_DDH dhsp on dh.PK_SEQ=dhsp.DDH_FK   																																							  "+
				" \n  				where a.PK_SEQ=dhsp.HOADON_FK) as khott_FK,   																																										  "+
				" \n  			b.SANPHAM_FK,b.SOLUONG,a.NPP_FK   																																														  "+
				" \n  	 from erp_hoadon a inner join ERP_HOADON_SP b   																																												  "+
				" \n  	on a.PK_SEQ=b.HOADON_FK   																																																		  "+
				" \n  	where a.NGAYXUATHD<@Denngay    AND b.SANPHAM_FK=@sanpham_fk		 and TRANGTHAI not in (3,5)) as data  																																  "+
				" \n  																																																									  "+
				" \n  union all   																																																						  "+
				" \n  	select  a.NgayChuyen as ngayct,a.pk_seq as soct,a.KhoXuat_FK,a.NPP_FK,b.sanpham_fk,(-1)*(b.soluongchuyen),N'Xuất kho khác' as nghiepvu    																						  "+
				" \n  	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b   																																										  "+
				" \n  	on a.PK_SEQ=b.chuyenkho_fk   																																																	  "+
				" \n  	where a.TRANGTHAI=1 and a.NgayChuyen <@Denngay     AND b.SANPHAM_FK=@sanpham_fk																																						  "+
				" \n 																																																									  "+
				" \n  																																																									  "+
				" \n  ) as DB inner join SANPHAM b on b.PK_SEQ=DB.sanpham_fk   																																											  "+
				" \n  inner join ERP_KHOTT c on c.PK_SEQ=DB.KhoNhap_FK    																																												  "+
				" \n  inner join NHAPHANPHOI d on d.PK_SEQ=DB.npp_fk   																																													  "+
				" \n 																																																									  "+
				" \n  where 1=1																																																							  ";
													
		ResultSet rstondau=obj.getDb().get(querytondau);
		rstondau.next();
		double TonDau = rstondau.getDouble("XNT");
		rstondau.close();
		int cong = 0;
		int countRow=4;
		cell = cells.getCell(countRow, cong++ );cell.setValue("STT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(countRow, cong++ );cell.setValue("SỐ CHỨNG TỪ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY CHỨNG TỪ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(countRow, cong );cell.setValue("Nghiệp vụ");	 ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(countRow + 1, cong ++ );cell.setValue("Số dư đầu kỳ"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
		
		cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY XUẤT NHẬP"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(countRow, cong++ );cell.setValue("NHẬP"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0)
		;
		cell = cells.getCell(countRow, cong++ );cell.setValue("XUẤT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		cell = cells.getCell(countRow, cong );cell.setValue("TỒN"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
		
		countRow ++;
		
		cell = cells.getCell(countRow, cong );cell.setValue(TonDau); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
		
		int sott = 1;
		countRow ++;
		while(rs.next())
		{
			
			Color c = Color.WHITE;
			boolean bold =false; 
			
			cong = 0;
			cell = cells.getCell(countRow,cong++); cell.setValue(sott++); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
			cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("soct")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
			cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("ngayct")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
			cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("nghiepvu")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
			cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("ngayct")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
			cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("nhap")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
			cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("xuat")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
			TonDau +=  rs.getDouble("nhap") + rs.getDouble("xuat");
			cell = cells.getCell(countRow,cong++); cell.setValue(TonDau); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
			
		
			++countRow;
		}
		
	
		
		if(rs!=null)rs.close();
		return false;

	}


	private boolean FillData_Details(Workbook workbook, IBcTheKho obj, String query) throws Exception
	{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cell cell = null;

		Cells cells = worksheet.getCells();		
		ResultSet hdRs = obj.getTotalRs();

		Font font = new Font();
		font.setName("Times New Roman");
		font.setColor(Color.BLACK);
		font.setBold(true);
		font.setSize(11);

		if (hdRs != null) 
		{
			try 
			{


				int i = 12;
				hdRs=db.get(query);

				double Total_Nhap=0;
				double Total_Xuat=0;

				double Total=0;

				while (hdRs!=null&& hdRs.next()) 
				{	
					Total+=hdRs.getDouble("SoLuong");
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue( hdRs.getString("NgayCT").length()>=10?getFormatDate(hdRs.getString("NgayCT")):"" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue(hdRs.getString("SoCT"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					String khTEN="";
					if(hdRs.getString("khTEN")!=null&&hdRs.getString("khTEN").length()>0)
						khTEN= hdRs.getString("khTEN")+"("+hdRs.getString("khMA") + ")"; 
					else
						khTEN=hdRs.getString("NghiepVu");

					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue(khTEN );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					String makh="";
					makh=hdRs.getString("khma");

					String Kbh="";
					ResultSet Kenhbh=null;
					ResultSet Kenhbh1 = null;
					if(!makh.equals(""))
					{
						Kenhbh=db.get("select kh.KBH_FK from khachhang kh where kh.maFAST='"+ makh+"' ");

						while(Kenhbh.next())
						{
							Kbh=Kenhbh.getString("KBH_FK");
							System.out.println(Kbh);
							if(Kbh.equals("100025"))
								Kbh="OTC";
						}
						System.out.println("select loainpp from nhaphanphoi  where maFAST='"+ makh+"' ");

						Kenhbh1=db.get("select loainpp from nhaphanphoi  where maFAST='"+ makh+"' ");
						while(Kenhbh1.next())
						{
							Kbh=Kenhbh1.getString("loainpp");
							System.out.println(Kbh);
							if(Kbh.equals("4"))
								Kbh="Đối Tác";
						}
					}
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue(Kbh);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					if(Kenhbh1!=null)
						Kenhbh1.close();
					if(Kenhbh!=null)
						Kenhbh.close();

					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getString("SoLo"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getString("NgayHetHan")!=null&&hdRs.getString("NgayHetHan").length()>=10?getFormatDate(hdRs.getString("NgayHetHan")):"");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);

					if(hdRs.getString("NghiepVu").equals("Tồn đầu"))
					{
						cell = cells.getCell("G" + Integer.toString(i));cell.setValue(hdRs.getDouble("SoLuong"));;ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);			
					}
					else 
					{
						if( hdRs.getDouble("SoLuong") >0  )
						{
							cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SoLuong"));
							Total_Nhap+=hdRs.getDouble("SoLuong");

							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						}
						else
						{
							cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SoLuong")*(-1));
							Total_Xuat+=hdRs.getDouble("SoLuong")*(-1);
							ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
						}
					}
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue("");ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);


					i++;
				}
				if(hdRs!=null)hdRs.close();


				i++;
				cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("TỔNG CỘNG");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("E" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("F" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("G" + Integer.toString(i));	cell.setValue("");
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

				cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(Total_Nhap);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);

				cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(Total_Xuat);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);

				cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(Total);
				ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);

				cell = cells.getCell("K" + Integer.toString(i));	cell.setValue("");ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);				

				i++;

				hdRs=obj.getTotalRs();
				while (hdRs!=null&& hdRs.next()) 
				{	
					cell = cells.getCell("A" + Integer.toString(i));	cell.setValue("" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("B" + Integer.toString(i));	cell.setValue("");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("C" + Integer.toString(i));	cell.setValue("" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					cell = cells.getCell("D" + Integer.toString(i));	cell.setValue("" );
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("E" + Integer.toString(i));	cell.setValue(hdRs.getString("SoLo"));
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("F" + Integer.toString(i));	cell.setValue(hdRs.getString("NgayHetHan")!=null&&hdRs.getString("NgayHetHan").length()>=10?getFormatDate(hdRs.getString("NgayHetHan")):"");
					ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);

					cell = cells.getCell("G" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("H" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("I" + Integer.toString(i));	cell.setValue(0);ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("J" + Integer.toString(i));	cell.setValue(hdRs.getDouble("SoLuong") );ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 41);
					cell = cells.getCell("K" + Integer.toString(i));	cell.setValue("");ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					i++;
				}

				if(db != null) db.shutDown();

				if(i==14){					
					throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
				}

			}catch(Exception ex){
				ex.printStackTrace();
				throw new Exception(ex.getMessage());
			}
		}else{
			return false;
		}		
		return true;

	}

	private void CreateStaticHeader(Workbook workbook, IBcTheKho obj)
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();

		Style style;
		Font font = new Font();
		font.setName("Times New Roman");
		font.setColor(Color.RED);//mau chu
		font.setSize(16);// size chu
		font.setBold(true);

		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		style = cell.getStyle();
		style.setFont(font); 
		style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	   


		dbutils db = new dbutils();

		ResultSet rs=db.get("select makho,ten from nhaphanphoi where pk_Seq ='"+obj.getNppId()+"' ");
		if(rs!=null)
		{
			try
			{
				while(rs.next())
				{
					cells.setRowHeight(3, 18.0f);
					cell = cells.getCell("C5");
					ReportAPI.getCellStyle(cell,Color.BLACK,true, 11, " "+rs.getString("makho")+" - " +rs.getString("ten")  );
				}
				if(rs!=null)rs.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}

		rs=db.get("select ma as spMa,ten as spTEN from sanpham where pk_Seq ='"+obj.getSpId()+"' ");
		if(rs!=null)
		{
			try
			{
				while(rs.next())
				{
					cells.setRowHeight(3, 18.0f);
					cell = cells.getCell("C6");
					ReportAPI.getCellStyle(cell,Color.BLACK,true, 11, "VẬT TƯ "+rs.getString("spMa" )+ " "    +rs.getString("spTEN")  );
				}
				if(rs!=null)rs.close();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				db.shutDown();
			}
		}

		cells.setRowHeight(3, 18.0f);
		cell = cells.getCell("C7");
		ReportAPI.getCellStyle(cell,Color.NAVY,true, 11, "Từ ngày : " + obj.getTuNgay() + " Đến ngày : " + obj.getDenNgay() + ""  );

	}

	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");
		if(arr.length==3)
			return arr[2] + "/" + arr[1] + "/" + arr[0];
		else
			return "";
	}

	
	
	private void ExportToExcel(OutputStream out,IBcTheKho obj )
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
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IBcTheKho obj,int countRow,int column,int sheetNum)throws Exception
	{
		try{


			String query = obj.getQuery();
			
			
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(sheetNum);
			com.aspose.cells.Cells cells = worksheet.getCells();

			com.aspose.cells.Cell cell = cells.getCell("A1");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Thẻ kho");
			Style style = cell.getStyle();
			com.aspose.cells.Font font = new com.aspose.cells.Font();
			font.setColor(Color.RED);//mau chu
			font.setSize(16);// size chu
			font.setBold(true);
			style.setFont(font);
			cell.setStyle(style);	
			
			
			
			cell = cells.getCell("A2");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Người tạo:");
			
			cell = cells.getCell("B2");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, false, 0);
			cell.setValue( obj.getUserName());
			
			cell = cells.getCell("A3");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Thời gian:");
			
			cell = cells.getCell("B3");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, false, 0);
			cell.setValue(obj.getTuNgay() +" đến " + obj.getDenNgay() );
			
			
			cell = cells.getCell("A4");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Vật tư:");
			
			cell = cells.getCell("B4");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( obj.getSpTen());
			
			cell = cells.getCell("A5");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Đơn vị:");
			
			cell = cells.getCell("B5");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( obj.getSpDonvi());
			
			cell = cells.getCell("A6");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( "Mã Số:");
			
			cell = cells.getCell("B6");
			ReportAPI.setCellBackground(cell,  Color.WHITE, BorderLineType.NONE, true, 0);
			cell.setValue( obj.getSpMa());
			
			
			ResultSet	rs = obj.getDb().get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			String querysolo="";
			if(obj.getSolo().trim().length()<0)
			{
				querysolo=" and solo='"+ obj.getSolo() +"' ";
			}
			if(obj.getKhoId().trim().length()<0)
			{
				querysolo +=" and kho_fk='"+ obj.getKhoId() +"' ";
			}
			if(obj.getKbhId().trim().length()<0)
			{
				querysolo +=" and kbh_fk='"+ obj.getKbhId() +"' ";
			}
			String querytondau="select SUM(xnt) as XNT from [dbo].[baocao_XNT_total_tungay_denngay]("+ obj.getNppId() +",convert (char(10),dateadd (dd,-1,'"+ obj.getTuNgay() +"'),126),convert (char(10),dateadd (dd,-1,'"+ obj.getTuNgay() +"'),126))" +
					 " where sanpham_fk='"+ obj.getSpId() +"' " +querysolo;
			ResultSet rstondau=obj.getDb().get(querytondau);
			rstondau.next();
			double TonDau = rstondau.getDouble("XNT");
			rstondau.close();
			int cong = 0;
			cell = cells.getCell(countRow, cong++ );cell.setValue("STT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("SỐ CHỨNG TỪ"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong );cell.setValue("DIỄN GIẢI");	 ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow + 1, cong ++ );cell.setValue("Số dư đầu kỳ"); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("NGÀY XUẤT NHẬP"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong++ );cell.setValue("NHẬP"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0)
			;
			cell = cells.getCell(countRow, cong++ );cell.setValue("XUẤT"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			cell = cells.getCell(countRow, cong );cell.setValue("TỒN"); ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			
			countRow ++;
			
			cell = cells.getCell(countRow, cong );cell.setValue(TonDau); ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, true, 0);
			
			int sott = 1;
			countRow ++;
			while(rs.next())
			{
				
				Color c = Color.WHITE;
				boolean bold =false; 
				
				cong = 0;
				cell = cells.getCell(countRow,cong++); cell.setValue(sott++); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("sonetId")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("ngayct")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("diengiai")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getString("ngayct")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 0);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("nhap")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
				cell = cells.getCell(countRow,cong++); cell.setValue(rs.getDouble("xuat")); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
				TonDau +=  rs.getDouble("nhap") + rs.getDouble("xuat");
				cell = cells.getCell(countRow,cong++); cell.setValue(TonDau); ReportAPI.setCellBackground(cell, c, BorderLineType.THIN, bold, 43);
				
			
				++countRow;
			}
			
		
			
			if(rs!=null)rs.close();



		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	
	
}
