package geso.dms.center.servlets.dieuchinhtonkho;

import geso.dms.center.beans.dieuchinhtonkho.IDieuchinhtonkhoList;
import geso.dms.center.beans.dieuchinhtonkho.imp.DieuchinhtonkhoList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.servlets.dieuchinhtonkho.DieuchinhtonkhoSvl;
import geso.dms.distributor.util.Utility;
import geso.dms.center.beans.dieuchinhtonkho.IDieuchinhtonkho;
import geso.dms.center.beans.dieuchinhtonkho.imp.Dieuchinhtonkho;


import java.io.IOException;
import java.io.OutputStream;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class DuyetdctkSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	dbutils db = new dbutils();

	public DuyetdctkSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
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
	
		final HttpServletResponse response2=response;
		
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			OutputStream out = response.getOutputStream();
			IDieuchinhtonkhoList obj;  
	
			Utility util = new Utility();
	
			String querystring = request.getQueryString();
			String action = util.getAction(querystring);
			// out.println(action);
	
			String dctkId = util.getId(querystring);
	
			if (userId.length() == 0)
				userId =  util.antiSQLInspection(request.getParameter("userId"));
	
			System.out.println("action la :" + action);
			if (action.equals("approve"))
			{
				Duyet(dctkId, userId);
	
			} else if (action.equals("delete"))
			{
				String kq = DieuchinhtonkhoSvl.Delete(dctkId,userId);
				session.setAttribute("MSG", kq);
			}
			userId = util.getUserId(querystring);
	
			obj = new DieuchinhtonkhoList();
			obj.setUserId(userId);
			obj.init0();
			obj.createDctklist("");
			session.setAttribute("obj", obj);
	
			String nextJSP = request.getContextPath() + "/pages/Center/DuyetDieuChinhTonKho.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private void CreateStaticHeader(Workbook workbook)
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();

		Cell cell = cells.getCell("A1");
		cell.setValue("Danh sách sản phẩm");

		cell = cells.getCell("A3");

		// cell = cells.getCell("A4");
		// cell.setValue("User:  " + UserName);

		// tieu de
		cell = cells.getCell("A8");
		cell.setValue("So PHieu");

		cell = cells.getCell("B8");
		cell.setValue("Đơn vị kinh doanh");
		cell = cells.getCell("C8");
		cell.setValue("Kho");
		cell = cells.getCell("D8");
		cell.setValue("Kenh");
		cell = cells.getCell("E8");
		cell.setValue("SITECODE");
		cell = cells.getCell("F8");
		cell.setValue("Nha Phan  PHoi");
		cell = cells.getCell("G8");
		cell.setValue("Ma SP");
		cell = cells.getCell("H8");
		cell.setValue("TEN SP");
		cell = cells.getCell("I8");
		cell.setValue("Dieu CHinh");
		cell = cells.getCell("J8");
		cell.setValue("DON VI");
		cell = cells.getCell("K8");
		cell.setValue("Gia MUa");
		cell = cells.getCell("L8");
		cell.setValue("THanh Tien");
		cell = cells.getCell("M8");
		cell.setValue("Ton Hien Tai");
		cell = cells.getCell("N8");
		cell.setValue("TOn MOi");

		worksheet.setName("Danh sách  sản phẩm");
	}

	private void CreateStaticData(Workbook workbook, String dctkid)
	{
		String query = "select dctk.pk_seq,kbh.diengiai as kbh,dvkd.diengiai as dvkd,kho.pk_Seq,kho.ten as kho,npp.sitecode,npp.ten as nppten " + " ,sp.ma,sp.ten spten,dctk_sp.* " + "  from dieuchinhtonkho dctk "
				+ " inner join dieuchinhtonkho_sp dctk_sp on dctk.pk_seq=dctk_sp.dieuchinhtonkho_fk " + " inner join sanpham sp on sp.pk_seq=sanpham_fk " + " inner join kho  on kho.pk_seq=dctk.kho_fk "
				+ " inner join donvikinhdoanh dvkd on  dctk.dvkd_fk=dvkd.pk_seq " + " inner join kenhbanhang kbh on kbh.pk_Seq=dctk.kbh_fk " + " inner join nhaphanphoi npp on npp.pk_seq=dctk.npp_fk " + " where dctk.pk_seq=" + dctkid;
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		Cells cells = worksheet.getCells();

		dbutils db = new dbutils();
		ResultSet rs = db.get(query);
		System.out.println("Get san pham :" + query);
		int i = 9;
		if (rs != null)
		{
			try
			{
				cells.setColumnWidth(0, 25.0f);
				cells.setColumnWidth(1, 15.0f);
				cells.setColumnWidth(2, 15.0f);
				cells.setColumnWidth(3, 30.0f);
				cells.setColumnWidth(4, 45.0f);
				cells.setColumnWidth(5, 25.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);
				cells.setColumnWidth(2, 15.0f);
				cells.setColumnWidth(3, 30.0f);
				cells.setColumnWidth(4, 45.0f);
				cells.setColumnWidth(5, 25.0f);
				cells.setColumnWidth(6, 15.0f);
				cells.setColumnWidth(7, 15.0f);
				cells.setColumnWidth(8, 15.0f);
				for (int j = 0; j < 9; j++)
					cells.setRowHeight(j, 13.0f);

				Cell cell = null;
				while (rs.next())
				{

					cell = cells.getCell("A" + Integer.toString(i));
					cell.setValue(rs.getString("pk_seq"));
					cell = cells.getCell("B" + Integer.toString(i));
					cell.setValue(rs.getString("dvkd"));
					cell = cells.getCell("C" + Integer.toString(i));
					cell.setValue(rs.getString("kho"));
					cell = cells.getCell("D" + Integer.toString(i));
					cell.setValue(rs.getString("kbh"));
					cell = cells.getCell("E" + Integer.toString(i));
					cell.setValue(rs.getString("sitecode"));
					cell = cells.getCell("F" + Integer.toString(i));
					cell.setValue(rs.getString("nppten"));
					cell = cells.getCell("G" + Integer.toString(i));
					cell.setValue(rs.getString("ma"));
					cell = cells.getCell("H" + Integer.toString(i));
					cell.setValue(rs.getString("spten"));
					cell = cells.getCell("I" + Integer.toString(i));
					cell.setValue(rs.getFloat("dieuchinh"));
					cell = cells.getCell("J" + Integer.toString(i));
					cell.setValue(rs.getString("donvi"));
					cell = cells.getCell("K" + Integer.toString(i));
					cell.setValue(rs.getFloat("giamua"));

					cell = cells.getCell("L" + Integer.toString(i));
					cell.setValue(rs.getFloat("thanhtien"));

					cell = cells.getCell("M" + Integer.toString(i));
					cell.setValue(rs.getFloat("tonhientai"));

					cell = cells.getCell("N" + Integer.toString(i));
					cell.setValue(rs.getFloat("tonmoi"));

					i++;
				}
				rs.close();
			} catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("Loi Nek :" + e.toString());
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
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
			OutputStream out = response.getOutputStream();
			IDieuchinhtonkhoList obj;
	

		 	Utility util = new Utility();
			userId =  util.antiSQLInspection(request.getParameter("userId"));
			obj = (IDieuchinhtonkhoList) new DieuchinhtonkhoList();
			if (userId == null)
			{
				response.sendRedirect("/OneOne");
			} else
			{
				obj.setUserId(userId);
			}
	
			String action = request.getParameter("action");

			String id =  util.antiSQLInspection(request.getParameter("id"));
	
			if (action.equals("approve"))
			{
				String loi = Duyet(id, userId);
				if (!loi.trim().equals(""))
				{
					IDieuchinhtonkho dctkBean = (IDieuchinhtonkho) new Dieuchinhtonkho();
					dctkBean.setUserId(userId);
					dctkBean.setId(id);
					dctkBean.setMessage(loi);
					dctkBean.initDisplay();
					session.setAttribute("dctkBean", dctkBean);
					String nextJSP = request.getContextPath() + "/pages/Center/DieuChinhTonKhoDisplay.jsp";
					response.sendRedirect(nextJSP);
				} else
				{
					obj = new DieuchinhtonkhoList();
					obj.setUserId(userId);
					obj.init0();
					obj.createDctklist("");
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/DuyetDieuChinhTonKho.jsp";
					response.sendRedirect(nextJSP);
				}
			} else if (action.equals("excel"))
			{
	
				IDieuchinhtonkho dctkBean = (IDieuchinhtonkho) new Dieuchinhtonkho();
	
				try
				{
	
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("Content-Disposition", "attachment; filename=DanhSachSanPham.xls");
	
					Workbook workbook = new Workbook();
	
					CreateStaticHeader(workbook);
					CreateStaticData(workbook, id);
					// Saving the Excel file
					workbook.save(out);
				} catch (Exception ex)
				{
					dctkBean.setMessage("Khong Xuat Ra Excel Duoc");
				}
				dctkBean.setUserId(userId);
				dctkBean.setId(id);
				dctkBean.initDisplay();
				session.setAttribute("dctkBean", dctkBean);
				String nextJSP = request.getContextPath() + "/pages/Center/DieuChinhTonKhoDisplay.jsp";
				response.sendRedirect(nextJSP);
				return;
			} 
			else if (action.equals("toExcel"))
		 		{
					String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
					if (dvkdId == null)
					{
						dvkdId = "";
					}
					obj.setDvkdId(dvkdId);
	
					String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
					if (kbhId == null)
					{
						kbhId = "";
					}
					obj.setKbhId(kbhId);
	
					String khoId = util.antiSQLInspection(request.getParameter("khoId"));
					if (khoId == null)
					{
						khoId = "";
					}
	
					obj.setKhoId(khoId);
	
					String tungay = util.antiSQLInspection(request.getParameter("tungay"));
					if (tungay == null)
					{
						tungay = "";
					}
					obj.setTungay(tungay);
	
					String denngay = util.antiSQLInspection(request.getParameter("denngay"));
					if (denngay == null)
					{
						denngay = "";
					}
					obj.setDenngay(denngay);
					
					obj.setDenngay(denngay);
					obj.init0();
					String search=obj.createQueryString();
		 			ToExcel(response, search);
		 		}
			
			else
			{ 
				String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
				if (dvkdId == null)
				{
					dvkdId = "";
				}
				obj.setDvkdId(dvkdId);
	
				String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
				if (kbhId == null)
				{
					kbhId = "";
				}
				obj.setKbhId(kbhId);
	
				String khoId = util.antiSQLInspection(request.getParameter("khoId"));
				if (khoId == null)
				{
					khoId = "";
				}
	
				obj.setKhoId(khoId);
	
				String tungay = util.antiSQLInspection(request.getParameter("tungay"));
				if (tungay == null)
				{
					tungay = "";
				}
				obj.setTungay(tungay);
	
				String denngay = util.antiSQLInspection(request.getParameter("denngay"));
				if (denngay == null)
				{
					denngay = "";
				}
				obj.setDenngay(denngay);
				
				String trangthai =  util.antiSQLInspection(request.getParameter("trangthai"));
				if(trangthai == null){
					trangthai= "";
				}
				obj.setttId(trangthai);
				System.out.println("trang thai" + obj.getttId());
				
				obj.init0();
				obj.createDctklist(obj.createQueryString());
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Center/DuyetDieuChinhTonKho.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}

	private boolean CheckNgayDuyet(String nppId, String ngayduyet)
	{
		Utility util = new Utility();
		String ngayksgn = util.ngaykhoaso(nppId);

		// System.out.print("\nNgay khoa so gan nhat la: " + this.ngaychungtu +
		// "\n");

		if (ngayksgn.equals(""))
			return false;

		String[] ngayks = ngayksgn.split("-");
		String[] ngayct = ngayduyet.split("-");

		// System.out.print("\nNgay chung tu la: " + this.ngaychungtu + "\n");

		Calendar c1 = Calendar.getInstance(); // new GregorianCalendar();
		Calendar c2 = Calendar.getInstance(); // new GregorianCalendar();

		// NGAY KHOA SO

		c1.set(Integer.parseInt(ngayks[0]), Integer.parseInt(ngayks[1]) - 1, Integer.parseInt(ngayks[2]));

		// NGAY thuc hien tra hang
		c2.set(Integer.parseInt(ngayct[0]), Integer.parseInt(ngayct[1]) - 1, Integer.parseInt(ngayct[2]));

		c1.add(Calendar.DATE, 1);// ngay tra don hang bang ngay khoa so them 1
									// ngay
		// phep tinh ngay nhan hang - ngay khoa so

		long songay = (c1.getTime().getTime() - c2.getTime().getTime()) / (24 * 3600 * 1000);

		if (songay < 0) // ngay chung tu khong duoc nho hon hoac bang ngay khoa
						// so gan nhat
		{
			// this.msg =
			// "Ngay Tra Don Hang Phai Lon Hon Ngay Khoa So Gan Nhat.";
			return false;
		}
		return true;
	}

	private String Duyet(String dctkId, String userId)
	{
		geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
		dbutils db = new dbutils();
		String msg="";
		try
		{
			 String  query =    
			  		 "  select a.ngaydc,b.ngaynhapkho,a.npp_fk as nppId, a.kbh_fk as kbhId, a.kho_fk as khoId, b.sanpham_fk as spId, " + 
			  		 	"  b.dieuchinh as soluong,b.solo,b.NgayHetHan from dieuchinhtonkho a, dieuchinhtonkho_sp b  "+ 
			  		 	"  where a.pk_seq = b.dieuchinhtonkho_fk and b.dieuchinhtonkho_fk='" + dctkId + "' and a.trangthai <> '1' ";
			ResultSet   rs = db.get(query);
		
			db.getConnection().setAutoCommit(false);
			String nppId = "";
			while (rs.next())
			{

				nppId = rs.getString("nppId");
				String kbhId = rs.getString("kbhId");
				String khoId = rs.getString("khoId");
				String spId = rs.getString("spId");
				String sl = rs.getString("soluong");
				String solo=rs.getString("solo");
				String ngaydc=rs.getString("ngaydc");
				String NgayHetHan=rs.getString("NgayHetHan");
				String ngaynhapkho=rs.getString("ngaynhapkho");
				double num = Double.valueOf(sl).doubleValue();
				if (num < 0)
				{
					num = num * (-1);
//					query = "update nhapp_kho_chitiet set soluong=soluong-" + num + ", booked = booked-" + num + "  where npp_fk = '" + nppId + "' and kbh_fk = '" + kbhId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "' and solo=N'"+solo+"' and NgayHetHan='"+NgayHetHan+"'  ";
//					if(db.updateReturnInt(query)!=1)
//					{
//						db.update("rollback");
//						return "Khong The Duyet Dieu Chinh Ton Kho,Xay Ra Loi Cap Nhat Sau :" + query;
//					}
//					query = "update nhapp_kho set soluong=soluong-" + num + ", booked = booked-" + num + "  where npp_fk = '" + nppId + "' and kbh_fk = '" + kbhId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "'";
//					if(db.updateReturnInt(query)!=1)
//					{
//						db.update("rollback");
//						return "Khong The Duyet Dieu Chinh Ton Kho,Xay Ra Loi Cap Nhat Sau :" + query;
//					}
					msg=util.Update_NPP_Kho_Sp_Chitiet(ngaydc, "Kiểm kho", db, khoId, spId, nppId, kbhId, solo, NgayHetHan, ngaynhapkho, (-1)*num, (-1)*num, 0.0, 0.0, 0.0);
					if(msg.length()>0)
					{
						db.update("rollback");
						return msg;
					}
					msg=util.Update_NPP_Kho_Sp(ngaydc, "Kiểm kho", db, khoId, spId, nppId, kbhId, (-1)*num, (-1)*num, 0.0, 0.0);
					if(msg.length()>0)
					{
						db.update("rollback");
						return msg;
					}
				} else
				{
//					query = "update nhapp_kho_chitiet set soluong=soluong+" + num + ", available = available+" + num + " where npp_fk = '" + nppId + "' and kbh_fk = '" + kbhId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "' and solo=N'"+solo+"'  and NgayHetHan='"+NgayHetHan+"'    ";
//					if(db.updateReturnInt(query)!=1)
//					{
//						db.update("rollback");
//						return "Khong The Duyet Dieu Chinh Ton Kho,Xay Ra Loi Cap Nhat Sau :" + query;
//
//					}
//					query = "update nhapp_kho set soluong=soluong+" + num + ", available = available+" + num + " where npp_fk = '" + nppId + "' and kbh_fk = '" + kbhId + "' and kho_fk = '" + khoId + "' and sanpham_fk = '" + spId + "'";
//					if(db.updateReturnInt(query)!=1)
//					{
//						db.update("rollback");
//						return "Khong The Duyet Dieu Chinh Ton Kho,Xay Ra Loi Cap Nhat Sau :" + query;
//
//					}
					msg=util.Update_NPP_Kho_Sp_Chitiet(ngaydc, "Kiểm kho", db, khoId, spId, nppId, kbhId, solo, NgayHetHan, ngaydc, num, 0.0, num, num, 0.0);
					if(msg.length()>0)
					{
						db.update("rollback");
						return msg;
					}
					msg=util.Update_NPP_Kho_Sp(ngaydc, "Kiểm kho", db, khoId, spId, nppId, kbhId, num, 0.0, num, 0.0);
					if(msg.length()>0)
					{
						db.update("rollback");
						return msg;
					}
				}
			}

			rs.close();
		
			query = "update dieuchinhtonkho set trangthai='1', nguoiduyet = '" + userId + "' where pk_seq = '" + dctkId + "' and trangthai=0 ";

			if(db.updateReturnInt(query)!=1)
			{
				db.update("rollback");
				return "Khong The Duyet Dieu Chinh Ton Kho,Xay Ra Loi Cap Nhat Sau :" + query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} catch (Exception e)
		{
			db.update("rollback");
			return "Khong The Duyet Dieu Chinh Ton Kho,Xay Ra Loi Cap Nhat Sau :" + e.toString();
		}
		db.shutDown();
		return "";

	}
	
	private void ToExcel(HttpServletResponse response, String query) throws IOException
	{
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=DieuChinhTonKho.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());

			int k = 0;
			int j = 5;

			WritableSheet sheet = null;

			WritableFont cellTitle = new WritableFont(WritableFont.TIMES, 14);
			cellTitle.setColour(Colour.BLACK);
			cellTitle.setBoldStyle(WritableFont.BOLD);

			sheet = w.createSheet("DieuChinhTonKho", k);
			sheet.addCell(new Label(0, 1, "Kiểm kho : ", new WritableCellFormat(cellTitle)));

			sheet.addCell(new Label(0, 2, "Ngày tạo: "));
			sheet.addCell(new Label(1, 2, "" + getDateTime()));

			sheet.addCell(new Label(2, 4, "Đơn vị tiền tệ:VND"));

			WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
			cellFont.setColour(Colour.BLACK);

			WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

			cellFormat.setBackground(jxl.format.Colour.LIME);
			cellFormat.setWrap(true);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormatSpecical = new WritableCellFormat(cellFont);

			cellFormatSpecical.setBackground(jxl.format.Colour.LIGHT_ORANGE);
			cellFormatSpecical.setWrap(true);
			cellFormatSpecical.setAlignment(Alignment.CENTRE);
			cellFormatSpecical.setVerticalAlignment(VerticalAlignment.CENTRE);
			cellFormatSpecical.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormatSpecical.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			sheet.addCell(new Label(0, 4, "STT", cellFormat));
			sheet.addCell(new Label(1, 4, "NGÀY ĐIỀU CHỈNH", cellFormatSpecical));
			sheet.addCell(new Label(2, 4, "SỐ CHỨNG TỪ", cellFormat));
			sheet.addCell(new Label(3, 4, "TỔNG SỐ TIỀN", cellFormat));
			sheet.addCell(new Label(4, 4, "ĐƠN VỊ KINH DOANH", cellFormat));
			sheet.addCell(new Label(5, 4, "KÊNH BÁN HÀNG", cellFormat));
			sheet.addCell(new Label(6, 4, "KHO", cellFormat));
			sheet.addCell(new Label(7, 4, "TRẠNG THÁI", cellFormat));
			sheet.addCell(new Label(8, 4, "NGƯỜI TẠO", cellFormat));
			sheet.addCell(new Label(9, 4, "NGƯỜI SỬA", cellFormat));
			sheet.addCell(new Label(10, 4, "NGƯỜI DUYỆT", cellFormat));

			sheet.setRowView(100, 4);

			sheet.setColumnView(0, 10);
			sheet.setColumnView(1, 20);
			sheet.setColumnView(2, 30);
			sheet.setColumnView(3, 25);
			sheet.setColumnView(4, 20);
			sheet.setColumnView(5, 20);
			sheet.setColumnView(6, 15);
			sheet.setColumnView(7, 35);
			sheet.setColumnView(8, 15);
			sheet.setColumnView(9, 15);
			sheet.setColumnView(10, 15);
			sheet.setColumnView(11, 15);
			sheet.setColumnView(12, 15);
			sheet.setColumnView(13, 15);
			sheet.setColumnView(14, 60);
			dbutils db = new dbutils();

			ResultSet rs = db.get(query);

			WritableCellFormat cellFormat2 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));

			cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cellFormat3 = new WritableCellFormat(new jxl.write.NumberFormat("#,###,###"));
			cellFormat3.setBackground(jxl.format.Colour.YELLOW);
			cellFormat3.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat3.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

			WritableCellFormat cformat = null;
			Label label;
			Number number;
			int stt=0;
			while (rs.next())
			{
		
				cformat = cellFormat2 ;
				stt++;
			
		
				number = new Number(0, j, stt, cformat);sheet.addCell(number);
				label = new Label(1, j, rs.getString("ngaydc"), cformat);sheet.addCell(label);
				label = new Label(2, j, rs.getString("chungtu"), cformat);sheet.addCell(label);
				number = new Number(3, j, rs.getDouble("tongtien"), cformat);sheet.addCell(number);
				label = new Label(4, j, rs.getString("dvkd"), cformat);sheet.addCell(label);
				label = new Label(5, j, rs.getString("dvkd"), cformat);sheet.addCell(label);
				label = new Label(6, j, rs.getString("ten"), cformat);sheet.addCell(label);
				label = new Label(7, j, rs.getInt("trangthai") == 0 ? "Chờ duyệt" : "Đã duyệt", cformat);sheet.addCell(label);
				
				label = new Label(8, j, rs.getString("NguoiTao"), cformat);sheet.addCell(label);
				label = new Label(9, j, rs.getString("NguoiSua"), cformat);sheet.addCell(label);
				label = new Label(10, j, rs.getString("nguoiduyet"), cformat);sheet.addCell(label);

				j++;
			}
			w.write();
			w.close();
			rs.close();
			db.shutDown();
		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			if (out != null)
				out.close();

		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
