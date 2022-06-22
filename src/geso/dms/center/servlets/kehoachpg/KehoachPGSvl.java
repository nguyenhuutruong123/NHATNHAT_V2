package geso.dms.center.servlets.kehoachpg;

import geso.dms.center.beans.kehoachpg.*;
import geso.dms.center.beans.kehoachpg.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
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
import com.aspose.cells.Picture;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.oreilly.servlet.MultipartRequest;

import java.sql.ResultSet;

public class KehoachPGSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	private static final long serialVersionUID = 1L;

	public KehoachPGSvl()
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
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		//String ssUserId = (String)session.getAttribute("userId");
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String view = util.antiSQLInspection(request.getParameter("view"));
		if (view == null) {
			view = "";
		}
		
	
 
		session.setAttribute("userId", userId);
		
		
		//PrintWriter out = response.getWriter();

		String querystring = request.getQueryString();

		
		
		IKehoachPGList obj = new KehoachPGList();

		
		
		
		String action = util.getAction(querystring);
		String ddkdId = util.getId(querystring);
		String id = util.getId(querystring); 
		if(action.equals("chot"))
		{
			
			Chot( id,  obj);
		}
		

		obj.setView(view);
		obj.setRequestObj(request);
		obj.setUserId(session.getAttribute("userId").toString());
		// System.out.println("user iad 1: "+userId);
		obj.init( "", request,null); 
		session.setAttribute("obj", obj);
		
		String nextJSP = request.getContextPath() + "/pages/Center/KehoachPG.jsp";
		response.sendRedirect(nextJSP);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException
	{
		IKehoachPGList obj = new KehoachPGList();
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
		
		
		obj.setUserId(userId);
		
		String action = util.antiSQLInspection(request.getParameter("action")); 
		
		String nppId =  util.antiSQLInspection(request.getParameter("NhaPhanPhoi"));  
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String TrangThai =  util.antiSQLInspection(request.getParameter("TrangThai"));  
		if (TrangThai == null)
			TrangThai = "";
		obj.setTrangthai(TrangThai);
		
		String ddkdTen =  util.antiSQLInspection(request.getParameter("ddkdTen"));  
		if (ddkdTen == null)
			ddkdTen = "";
		obj.setTen(ddkdTen);
		
		String view = util.antiSQLInspection(request.getParameter("view"));  
		if (view == null)
			view = "";
		obj.setView(view);
		
		
		
		obj.setUserId(userId);
		obj.setRequestObj(request);
		String search = "";
		String nextJSP = "";
		
		if(action.equals("Xoa"))
		{
		
			
			String IdXoa = util.antiSQLInspection(request.getParameter("IdXoa"));
			if (IdXoa == null)
				IdXoa = "";
			System.out.println("IdXoa = "+ IdXoa);
			if(IdXoa.trim().length() > 0)
				Delete(IdXoa, obj);
			else
				obj.setMsg("Lỗi dữ liệu");
		}
		else
		if (action.equals("excel"))
		{
			if (view != null && !view.equals("TT")) {
				obj.setNppId(util.getIdNhapp(userId));
			}
			OutputStream outStream = response.getOutputStream();
			String userTen = (String) request.getSession().getAttribute("userTen");
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; " + "filename=DanhSachDDKD(tt).xls");
			try
			{
				ExportToExcel(obj ,outStream, userTen);
				outStream.close();
				return;
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			return;
		}
		if (action.equals("new"))
		{
			// Empty Bean for distributor
			IKehoachPG ddkdBean = (IKehoachPG) new KehoachPG("",userId,view);
			
			// Save Data into session
			session.setAttribute("ddkdBean", ddkdBean);

			nextJSP = request.getContextPath() + "/pages/Center/KehoachPGNew.jsp";
			response.sendRedirect(nextJSP);
			return;
		} else if (action.equals("search"))
		{
			obj.setUserId(userId);

			session.setAttribute("abc", search);

			nextJSP = request.getContextPath() + "/pages/Center/KehoachPG.jsp";
		} else
		{

			// phantrang

			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");


			nextJSP = request.getContextPath() + "/pages/Center/KehoachPG.jsp";
		}

		obj.init( "", request,null); 

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		response.sendRedirect(nextJSP);
	}

	private String getSearchQuery(HttpServletRequest request, IKehoachPGList obj)
	{
		return "";

		/*Utility util = new Utility();

		String ten = util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("ddkdTen")));
		if (ten == null)
			ten = "";
		obj.setTen(ten);
		
		

		


		String nppId = util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("NhaPhanPhoi")));
		if (nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String trangthai = util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("TrangThai")));
		if (trangthai == null)
			trangthai = "";

		if (trangthai.equals("2"))
			trangthai = "";

		obj.setTrangthai(trangthai);
		String loaigiamsat = util.antiSQLInspection(geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("loaigiamsat")));
		if (loaigiamsat == null)
			loaigiamsat = "";
		if (loaigiamsat.equals("2"))
			trangthai = "";

	

		String search = "";

		if (ten.length() > 0)
		{
			search = search + " and upper(dbo.ftBoDau(a.diengiai)) like upper(?)";
			obj.getDataSearch().add( "%" + util.replaceAEIOU(ten) + "%" );
		}

		
		if (nppId.length() > 0)
		{
			search = search + " and a.npp_fk=?";
			obj.getDataSearch().add( nppId);

		}

		if (trangthai.length() > 0)
		{
			query = query + " and a.trangthai = ?";
			obj.getDataSearch().add( trangthai);

		}
		if (loaigiamsat.length() > 0)
		{
			query = query + " and isnull(a.tinhtrang,'0') = ?";
			obj.getDataSearch().add( loaigiamsat);
		}
		if(maSmartid.length() > 0)
			
		{
			query = query + " and a.smartid like ?";
			obj.getDataSearch().add("%"+ maSmartid +"%");
		}
		
		if(kbhId.length() > 0)
			
		{
			query = query + " and g.pk_seq like ?";
			obj.getDataSearch().add("%"+ kbhId +"%");
		}
		System.out.println("que ry la ........" + query);
		return query;*/
	}

	private void Delete(String id, IKehoachPGList obj)
	{
		dbutils db = new dbutils();

		try
		{
			db.getConnection().setAutoCommit(false);
			
			
			
			String sql = "delete from kehoachPG_KhachHang_ngay where kehoachPG_fk = '" + id + "'";
			if (!db.update(sql))
			{
				Utility.rollback_and_shutdown(db);
				obj.setMsg("Ban khong the xoa dai dien kinh doanh nay trong bang ke hoach ngay");
			}
			
			
			sql = "delete from kehoachPG where pk_Seq = '" + id + "' and trangthai = 0 ";
			if (db.updateReturnInt(sql) <= 0 )
			{
				Utility.rollback_and_shutdown(db);
				obj.setMsg("Bạn đã có tuyến đường hoặc đơn hàng rồi nên không xóa được");
				return ;
			}
			Utility.commit_and_shutdown(db);
		} catch (Exception e)
		{
			System.out.println("vao day roi" + e.toString());
			Utility.rollback_and_shutdown(db);
			obj.setMsg("Ban Khong Thuc Hien Xoa Duoc,Vui Long Kiem Tra Lai." + e.toString());
		}

	}
	
	private void Chot(String id, IKehoachPGList obj)
	{
		dbutils db = new dbutils();

		try
		{
			db.getConnection().setAutoCommit(false);
			
			
			
			String sql  = "update kehoachPG set trangthai = 1 where pk_Seq = '" + id + "' and trangthai = 0 ";
			if (db.updateReturnInt(sql) <= 0 )
			{
				Utility.rollback_and_shutdown(db);
				obj.setMsg("Không thể chốt ");
				return ;
			}
			Utility.commit_and_shutdown(db);
			obj.setMsg("Chốt thành công");
		} catch (Exception e)
		{
			System.out.println("vao day roi" + e.toString());
			Utility.rollback_and_shutdown(db);
			obj.setMsg("Ban Khong Thuc Hien Xoa Duoc,Vui Long Kiem Tra Lai." + e.toString());
		}

	}

	private void ExportToExcel(IKehoachPGList obj, OutputStream outStream, String UserCreate) throws Exception
	{
		try
		{
			Workbook workbook = new Workbook();
			createHeader(workbook, UserCreate);
			fillData(obj,workbook);
			workbook.save(outStream, 0);
		} catch (Exception ex)
		{
			throw new Exception(ex.getMessage());
		}

	}

	private void createHeader(Workbook workbook, String UserCreate) throws Exception
	{
		try
		{
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			worksheet.setName("DanhSachDDKD");

			Cells cells = worksheet.getCells();
			cells.setRowHeight(1, 13);
			Cell cell = cells.getCell("B1");
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "DANH SÁCH ĐẠI DIỆN KINH DOANH");
			cell = cells.getCell("B2");
			ReportAPI
					.getCellStyle(cell, Color.BLUE, true, 10, "Date Create: " + ReportAPI.NOW("dd/MM/yyyy : hh-mm-ss"));
			cell = cells.getCell("B3");
			ReportAPI.getCellStyle(cell, Color.BLUE, true, 10, "Distributor: " + UserCreate);

			
			
			/*cell = cells.getCell("A5");cell.setValue("Hình ảnh");ReportAPI.setCellHeader(cell);*/
			cell = cells.getCell("B5");cell.setValue("Mã NPP");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("C5");cell.setValue("Tên NPP");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("D5");cell.setValue("Trạng thái NPP");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("E5");cell.setValue("Mã GSBH");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("F5");cell.setValue("Tên GSBH");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("G5");cell.setValue("Trạng thái GSBH");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("H5");cell.setValue("Mã DDKD");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("I5");cell.setValue("Tên DDKD");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("J5");cell.setValue("Trạng thái DDKD");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("K5");cell.setValue("Khu vực");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("L5");cell.setValue("Vùng/Miền");ReportAPI.setCellHeader(cell);
			
			
			cell = cells.getCell("M5");cell.setValue("Ngày vào làm");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("N5");cell.setValue("Ngày kết thúc");ReportAPI.setCellHeader(cell);
			
			cell = cells.getCell("O5");cell.setValue("Máy đã cấp");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("P5");cell.setValue("Số tiền máy cấp");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("Q5");cell.setValue("Máy thế chân");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("R5");cell.setValue("Số tiền máy thế chân");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("S5");cell.setValue("Đơn vị thế chân");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("T5");cell.setValue("Máy mất");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("U5");cell.setValue("Số tiền máy mất");ReportAPI.setCellHeader(cell);
			cell = cells.getCell("V5");cell.setValue("NPP/ASM giữ máy");ReportAPI.setCellHeader(cell);
			
		} catch (Exception ex)
		{
			throw new Exception("Khong the tao duoc tua de cho bao cao");
		}

	}

	
	private void fillData(IKehoachPGList obj, Workbook workbook) throws Exception
	{
		try
		{
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();
			Cell cell = null;
			dbutils db = new dbutils();

			String query = "\n select a.smartid as id,d.sitecode, case when d.trangthai =1 then 'hoat dong' else 'Ngung hoat dong' end  as ttnpp," +
					"\n a.ten, a.dienthoai, a.diachi, a.trangthai AS TTDDKD, a.ngaytao,"  +
					"\n	b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, d.ma as nppId, " +
					"\n	d.ten as nppTen, F.smartid AS gsbhId,f.ten as gsbhTen, f.TRANGTHAI AS TTGSBH, g.ten as kbhTen," +
					"\n	k.TEN AS TenKV,v.TEN AS TenVung,ISNULL(a.HinhAnh,'') as HinhAnh, " +
					"\n a.maydacap, a.tienmaycap, a.maythechan, a.tienmaythe,a.nppasmgiumay, " +
					"\n case when a.chonmaythe = 0 then N'Nhà phân phối' when a.chonmaythe = 1 then N'Nhân viên bán hàng' " +
					"\n		else N'' end as chonmaythe, a.maymat, a.tienmaymat, " +
					"\n ISNULL(a.NGAYBATDAU, 'N/A') as ngaybatdau, ISNULL(a.NGAYKETTHUC, 'N/A') as ngayketthuc, " +
					"\n ISNULL( " +
					"\n	(select ttpp.TEN from " +
					"\n	(select PK_SEQ,MA,TEN from TRUNGTAMPHANPHOI ttpp where ttpp.PK_SEQ=a.TTPP_FK) " +
					"\n	as ttpp),'N/A') as TenTTPP " +
					"\n	  from daidienkinhdoanh a left join nhanvien b on a.nguoitao = b.pk_seq  " +
					"\n		left join  nhanvien c on a.nguoisua = c.pk_seq  " +
					"\n		left join  nhaphanphoi d on a.npp_fk = d.pk_seq  " +
					"\n		left join  ddkd_gsbh e on a.pk_seq = e.ddkd_fk  "  +
					"\n		left join  giamsatbanhang f on e.gsbh_fk = f.pk_seq "  +
					"\n		left join kenhbanhang g on f.kbh_fk = g.pk_seq " +
					"\n		left join  KHUVUC k on k.PK_SEQ = d.KHUVUC_FK " +
					"\n		left join VUNG v on v.PK_SEQ = k.VUNG_FK " +
					"\n where 1=1 ";
			if (obj.getNppId() != null && obj.getNppId().length() > 0) {
				query += "\n and a.npp_fk = "+obj.getNppId();
			}
			System.out.print("Query Excel: "+query);
			ResultSet rs = db.get(query);
			int rowIndex = 6;
			for(int i=0;i<9;i++)
			{
				cells.setColumnWidth(0, 15f);
			}
			
			while (rs.next())
			{
				/*cells.setRowHeight(rowIndex-1, 50f);
				String img = rs.getString("HinhAnh");
				String dir = getServletContext().getInitParameter("path");
				img=dir+"\\images\\"+img;
				if (img.trim().length() > 0)
				{
					try
					{
						int pictureIndex=worksheet.getPictures().add(rowIndex-1,0,rowIndex-1,0,img);
						Picture picture = worksheet.getPictures().get(pictureIndex);						
						picture.setWidth(50);
						picture.setHeight(50);
					} catch (Exception e)
					{
						System.out.println("Exception: " + e.getMessage());
					}
				}*/
				
				
				cell = cells.getCell("B" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("nppId"));

				cell = cells.getCell("C" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("nppTen"));

				cell = cells.getCell("D" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("ttnpp"));

				cell = cells.getCell("E" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("gsbhId"));

				cell = cells.getCell("F" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("gsbhTen"));

				cell = cells.getCell("G" + String.valueOf(rowIndex));
				cell.setValue(rs.getInt("TTGSBH")== 1 ? "Hoạt động" : "Ngưng Hoạt động");

				cell = cells.getCell("H" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("id"));

				cell = cells.getCell("I" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("ten"));

				cell = cells.getCell("J" + String.valueOf(rowIndex));
				cell.setValue(rs.getInt("TTDDKD") == 1 ? "Hoạt động" : "Ngưng Hoạt động");

				cell = cells.getCell("K" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("TenKV"));

				cell = cells.getCell("L" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("TenVung"));
				
				cell = cells.getCell("M" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("ngaybatdau"));
				
				cell = cells.getCell("N" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("ngayketthuc"));
				
				cell = cells.getCell("O" + String.valueOf(rowIndex));
				cell.setValue(rs.getFloat("maydacap"));
				
				cell = cells.getCell("P" + String.valueOf(rowIndex));
				cell.setValue(rs.getFloat("tienmaycap"));
				
				cell = cells.getCell("Q" + String.valueOf(rowIndex));
				cell.setValue(rs.getFloat("maythechan"));
				
				cell = cells.getCell("R" + String.valueOf(rowIndex));
				cell.setValue(rs.getFloat("tienmaythe"));
				
				cell = cells.getCell("S" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("chonmaythe"));
				
				cell = cells.getCell("T" + String.valueOf(rowIndex));
				cell.setValue(rs.getFloat("maymat"));
				
				cell = cells.getCell("U" + String.valueOf(rowIndex));
				cell.setValue(rs.getFloat("tienmaymat"));
				
				cell = cells.getCell("V" + String.valueOf(rowIndex));
				cell.setValue(rs.getString("nppasmgiumay"));
				++rowIndex;
			}
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("Khong the dien du lieu vao file Excel hoac khong co du lieu");
			
		}
	}

	public static void main(String[] arg)
	{
		System.out.println(getExcelColumFromName("BZ"));
		System.out.println(GetExcelColumnName(78));

	}

	private static String GetExcelColumnName(int columnNumber)
	{
		int dividend = columnNumber;
		String columnName = "";
		int modulo;

		while (dividend > 0)
		{
			modulo = (dividend - 1) % 26;
			columnName = (char) (65 + modulo) + columnName;
			dividend = (int) ((dividend - modulo) / 26);
		}

		return columnName;
	}

	private static int getExcelColumFromName(String name)
	{
		name = name.toUpperCase();

		int he = 25;
		int heso = 1;
		int tong = 0;

		int y = 0;
		for (int i = name.length() - 1; i > -1; i--)
		{
			char c = name.charAt(i);
			tong += ((int) c - 64) * (heso + he * y);
			y++;
		}
		return tong;
	}

}


