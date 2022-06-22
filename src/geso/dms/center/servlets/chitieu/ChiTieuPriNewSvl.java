package geso.dms.center.servlets.chitieu;

import geso.dms.center.beans.chitieu.IChiTieu;
import geso.dms.center.beans.chitieu.imp.ChiTieu;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import com.oreilly.servlet.MultipartRequest;

public class ChiTieuPriNewSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ChiTieuPriNewSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String querystring = request.getQueryString();
		Utility util = new Utility();
		String userId = util.getUserId(querystring);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.getId(querystring);
		String loaict = "0";

		IChiTieu obj = new ChiTieu(id, loaict);
		obj.setUserId(userId);
		obj.Init();
		session.setAttribute("loaichitieu", loaict);
		session.setAttribute("userId", userId);

		session.setAttribute("obj", obj);
		String action = util.getAction(querystring);

		session.setAttribute("check", "0");
		if (action.equals("update"))
		{
			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriUpdate.jsp";// default
			response.sendRedirect(nextJSP);
		} else if (action.equals("display"))
		{
			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriDisplay.jsp";// default
			response.sendRedirect(nextJSP);
		} else
		{

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();

		Utility util = new Utility();

		System.out.println("contentType " + contentType);
		System.out.println("contentType.indexOf(multipart/form-data;charset=utf-8)    " + contentType);
		//
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			IChiTieu chitieu = new ChiTieu();
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			String userId = util.antiSQLInspection(multi.getParameter("userId"));
			chitieu.setUserId(userId);
			Enumeration files = multi.getFileNames();
			String filenameu = "";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filenameu = multi.getFilesystemName(name);
				System.out.println("name  " + name);
			}
			try
			{
				int thang = Integer.parseInt(util.antiSQLInspection(multi.getParameter("thang")));
				chitieu.setThang(thang);
			} catch (Exception er)
			{
			}
			try
			{
				int nam = Integer.parseInt(util.antiSQLInspection(multi.getParameter("nam")));
				chitieu.setNam(nam);
			} catch (Exception er)
			{

			}
			String id = util.antiSQLInspection(multi.getParameter("id"));
			chitieu.setID(id);
			String dvkdid = util.antiSQLInspection(multi.getParameter("dvkdid"));
			chitieu.setDVKDID(dvkdid);
			String kbhid = util.antiSQLInspection(multi.getParameter("kbhid"));
			chitieu.setKenhId(kbhid);
			String songaylamviec = util.antiSQLInspection(multi.getParameter("songaylamviec"));
			chitieu.setSoNgayLamViec(songaylamviec);
			
			
			String ngayketthuc = util.antiSQLInspection(multi.getParameter("ngayketthuc"));
			chitieu.setNgayKetThuc(ngayketthuc);

			String ngaybatdau = util.antiSQLInspection(multi.getParameter("ngaybatdau"));
			chitieu.setNgayBatDau(ngaybatdau);

			
			
			String diengiai = util.antiSQLInspection(multi.getParameter("diengiai"));
			chitieu.setDienGiai(diengiai);

			chitieu.setNguoiSua(userId);
			chitieu.setNguoiTao(userId);
			chitieu.setNgayTao(this.getDateTime());
			chitieu.setNgaySua(this.getDateTime());
			chitieu.setLoaict("0");
			chitieu.Init();

			String filename = "C:\\java-tomcat\\data\\" + filenameu;
			if (filename.length() > 0)
			{
				// doc file excel
				File file = new File(filename);
				Workbook workbook;
				int indexRow = 5;
				try
				{
					workbook = Workbook.getWorkbook(file);
					Sheet sheet = workbook.getSheet(0);
					Cell[] cells = sheet.getRow(indexRow);

					String sql = "delete chitieutmp";
					db.update(sql);

					for (int i = indexRow + 1; i < sheet.getRows(); i++)
					{

						cells = sheet.getRow(i);
						
						if (cells != null && cells.length>0 && cells[0] != null)
						{
							String sotmp = "";
							try
							{
								sotmp = cells[3].getContents().toString().replace(",", "");
							} catch (Exception er)
							{
								er.printStackTrace();
							}
							if (cells[0].getContents().toString().length() > 0)
							{
								sql = " insert into chitieutmp (ma,chucvu,SELLSOUT )values(N'" + cells[0].getContents().toString() + "','" + cells[2].getContents().toString() + "','" + sotmp + "')";
								if (!db.update(sql))
								{
									System.out.println(sql);
								}
							}
						}
					}
					// sau khi doc vao bang tam,se thuc hien lay du lieu ra
					if (id.equals("0"))
					{
						if (chitieu.SaveChiTieu())
						{
							chitieu.setListChiTieu("", "0");
							session.setAttribute("obj", chitieu);
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPri.jsp";
							response.sendRedirect(nextJSP);
							System.out.println(chitieu.getMessage());
						} else
						{
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriNew.jsp";// default
							session.setAttribute("obj", chitieu);
							response.sendRedirect(nextJSP);
							System.out.println(chitieu.getMessage());
						}
					} else
					{
						if (chitieu.EditChiTieu())
						{
							chitieu.setListChiTieu("", "0");
							session.setAttribute("obj", chitieu);
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPri.jsp";
							response.sendRedirect(nextJSP);

						} else
						{
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriUpdate.jsp";// default
							session.setAttribute("obj", chitieu);
							response.sendRedirect(nextJSP);
						}
					}
				} catch (Exception er)
				{
					chitieu.setMessage("Thong bao loi : " + er.toString());
					er.printStackTrace();
				}
			}
		} else
		{

			IChiTieu obj = new ChiTieu();
			String id = request.getParameter("id");
			obj.setID(id);
			String userId = request.getParameter("userId") == null ? "" : request.getParameter("userId");
			obj.setUserId(userId);
			String loaict = "0";
			obj.setLoaict(loaict);

			try
			{
				int thang = Integer.parseInt(util.antiSQLInspection(request.getParameter("thang")));
				obj.setThang(thang);
			} catch (Exception er)
			{
			}
			try
			{
				int nam = Integer.parseInt(util.antiSQLInspection(request.getParameter("nam")));
				obj.setNam(nam);
			} catch (Exception er)
			{

			}

			String dvkdid = util.antiSQLInspection(request.getParameter("dvkdid"));
			obj.setDVKDID(dvkdid);
			String kbhid = util.antiSQLInspection(request.getParameter("kbhid"));
			obj.setKenhId(kbhid);
			String songaylamviec = util.antiSQLInspection(request.getParameter("songaylamviec"));
			obj.setSoNgayLamViec(songaylamviec);
			String ngayketthuc = util.antiSQLInspection(request.getParameter("ngayketthuc"));
			obj.setNgayKetThuc(ngayketthuc);
			
			String ngaybatdau = util.antiSQLInspection(request.getParameter("ngaybatdau"));
			obj.setNgayBatDau(ngaybatdau);
			
			

			String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
			obj.setDienGiai(diengiai);

			obj.setNguoiSua(userId);
			obj.setNguoiTao(userId);
			obj.setNgayTao(this.getDateTime());
			obj.setNgaySua(this.getDateTime());
			obj.setLoaict("0");

			String action = request.getParameter("action");
			if (action.equals("save"))
			{
				if (obj.EditChiTieu(request))
				{
					obj.setListChiTieu("", "0");
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPri.jsp";
					response.sendRedirect(nextJSP);

				} else
				{
					String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriUpdate.jsp";// default
					session.setAttribute("obj", obj);
					response.sendRedirect(nextJSP);
				}

			} else
			{

				String[] vungId = request.getParameterValues("vungId");
				String str = "";
				if (vungId != null)
				{
					for (int i = 0; i < vungId.length; i++)
						str += vungId[i] + ",";
					if (str.length() > 0)
						str = str.substring(0, str.length() - 1);
				}
				obj.setVungId(str);

				String[] khuvucId = request.getParameterValues("kvId");
				String str2 = "";
				if (khuvucId != null)
				{
					for (int i = 0; i < khuvucId.length; i++)
						str2 += khuvucId[i] + ",";
					if (str2.length() > 0)
						str2 = str2.substring(0, str2.length() - 1);
				}
				obj.setKvId(str2);

				obj.InitDisplay();
				session.setAttribute("userId", userId);

				session.setAttribute("check", "0");
				session.setAttribute("obj", obj);
				String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuPriDisplay.jsp";// default
				response.sendRedirect(nextJSP);

				System.out.println("[ChiTieuDisplay]" + "[VungId]" + str + "[kvId]" + str2);
			}
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		return dateFormat.format(date);
	}
}
