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

public class ChiTieuNewSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	public ChiTieuNewSvl()
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
		// //System.out.println("Ten user:  "+ userId);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.getId(querystring);
		String loaict = "1";
		
		IChiTieu obj = new ChiTieu(id, loaict);
		obj.setUserId(userId);
		obj.Init();
		session.setAttribute("userId", userId);
		
		String action = util.getAction(querystring);
		session.setAttribute("check", "0");
		
		session.setAttribute("obj", obj);
		if (action.equals("update"))
		{
			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuUpdate.jsp";// default
			response.sendRedirect(nextJSP);
		} else if (action.equals("display"))
		{
			String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuDisplay.jsp";// default
			response.sendRedirect(nextJSP);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();
		IChiTieu chitieu = new ChiTieu();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();
		
		Utility util = new Utility();
		
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			
			String userId = util.antiSQLInspection(multi.getParameter("userId"));
			chitieu.setUserId(userId);
			Enumeration files = multi.getFileNames();
			String filenameu = "";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filenameu = multi.getFilesystemName(name);
				
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
			try
			{
				
				chitieu.setID(id);
				
			} catch (Exception err)
			{
			}
			String dvkdid = util.antiSQLInspection(multi.getParameter("dvkdid"));
			chitieu.setDVKDID(dvkdid);
			String kbhid = util.antiSQLInspection(multi.getParameter("kbhid"));
			chitieu.setKenhId(kbhid);
			String songaylamviec = util.antiSQLInspection(multi.getParameter("songaylamviec"));
			chitieu.setSoNgayLamViec(songaylamviec);
			String ngayketthuc = util.antiSQLInspection(multi.getParameter("ngayketthuc"));
			chitieu.setNgayKetThuc(ngayketthuc);
			String diengiai = util.antiSQLInspection(multi.getParameter("diengiai"));
			chitieu.setDienGiai(diengiai);
			chitieu.setKhuVucID("");
			chitieu.setNguoiSua(userId);
			chitieu.setNguoiTao(userId);
			chitieu.setNgayTao(this.getDateTime());
			chitieu.setNgaySua(this.getDateTime());
			String filename = "C:\\java-tomcat\\data\\" + filenameu;
			if (filename.length() > 0)
			{
				File file = new File(filename);
				Workbook workbook;
				
				int indexRow = 5;
				int j = 6;
				try
				{
					
					workbook = Workbook.getWorkbook(file);
					Sheet sheet = workbook.getSheet(0);
					Cell[] cells = sheet.getRow(indexRow);
					String nhomspid = "";
					int dodai = 10;
					while (dodai < cells.length)
					{
						if (cells[dodai].getContents().trim().length() > 0)
						{
							dodai = dodai + 1;
							
						} else
						{
							break;
						}
					}
					while (j < cells.length)
					{
						if (cells[j] != null)
						{
							if (j == 10)
							{
								nhomspid = cells[j].getContents();
							} else
							{
								nhomspid = nhomspid + ";" + cells[j].getContents();
							}
							j++;
						}
					}
					chitieu.setChuoiNhomSp(nhomspid);
					String chuoi = "";
					for (j = 1; j < dodai - 9; j++)
					{
						chuoi = chuoi + "," + "manhom" + j;
					}
					chitieu.setKhuVucID(chuoi);
					String sql = " delete chitieutmp ";
					db.update(sql);
					
					for (int i = indexRow + 1; i < sheet.getRows(); i++)
					{
						cells = sheet.getRow(i);
						String values = "";
						for (j = 0; j < dodai; j++)
						{
							
							if (cells.length > 0)
							{
								
								if (cells[0].getContents().toString().length() > 0)
								{
									if (j != 1)
									{
										
										if (j == 0)
										{
											
											values = "'" + cells[j].getContents() + "'";
										} else if (j == 2)
										{
											
											values = values + ",'" + cells[j].getContents() + "'";
										} else
										{
											
											double sotmp = 0;
											try
											{
												sotmp = Double.parseDouble(cells[j].getContents().toString().replace(",", ""));
												
											} catch (Exception er)
											{
												
											}
											values = values + "," + Math.round(sotmp) + "";
										}
									}
								}
							}
							
						}
						if (values.length() > 0)
						{
							System.out.println("values : " + values);
							sql = " insert into chitieutmp (ma,chucvu,SELLSOUT,DOANHTHU,CUAHIEUCODS,DONHANGTHANHCONG,donhang,SKU,TIME_1OUTLET" + chuoi + ") " + "values(" + values + ")";
							if (!db.update(sql))
							{
								System.out.println(sql);
							}
						}
					}
					chitieu.Init();
					if (id.equals("0"))
					{
						if (chitieu.SaveChiTieu_Sec())
						{
							chitieu.setListChiTieu("", "1");
							session.setAttribute("obj", chitieu);
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieu.jsp";
							response.sendRedirect(nextJSP);
						} else
						{
							
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuNew.jsp";// default
							session.setAttribute("obj", chitieu);
							response.sendRedirect(nextJSP);
							
						}
						
					} else
					{
						if (chitieu.EditChiTieu_Sec())
						{
							chitieu.setListChiTieu("", "1");
							session.setAttribute("obj", chitieu);
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieu.jsp";
							response.sendRedirect(nextJSP);
							
						} else
						{
							String nextJSP = request.getContextPath() + "/pages/Center/ChiTieuUpdate.jsp";// default
							session.setAttribute("obj", chitieu);
							response.sendRedirect(nextJSP);
						}
						
					}
					
				} catch (Exception er)
				{
					er.printStackTrace();
					chitieu.setMessage("Thong bao loi : " + er.toString());
					
				}
				
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
