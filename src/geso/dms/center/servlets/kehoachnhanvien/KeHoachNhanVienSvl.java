package geso.dms.center.servlets.kehoachnhanvien;

import geso.dms.center.beans.kehoachnhanvien.*;
import geso.dms.center.beans.kehoachnhanvien.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;
import jxl.Workbook;

import com.oreilly.servlet.MultipartRequest;


public class KeHoachNhanVienSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;
	
    public KeHoachNhanVienSvl()
    {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IKeHoachNhanVienList obj;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    this.out  = response.getWriter();
	    	    
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
	    obj = new KeHoachNhanVienList();
	    Utility util = new Utility();
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = request.getParameter("userId");
	    obj.setUserId(userId);
	    //System.out.println("\nUserId = "+userId+"\n");
	    String action = util.getAction(querystring);
	    out.println(action);
	    
	    String khnvId = util.getId(querystring);

	    if (action.equals("delete")) {
	    	obj.delete(khnvId);
	    } else if(action.equals("duyet")) {
	    	obj.duyet(khnvId);
	    } else if(action.equals("moduyet")) {
	    	obj.moduyet(khnvId);
	    }
	    
	    obj.init("");
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Center/KeHoachNhanVien.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IKeHoachNhanVienList obj;
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    this.out = response.getWriter();
	    obj = new KeHoachNhanVienList();
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		Utility utl=new Utility();
	    String userId = utl.antiSQLInspection( request.getParameter("userId"));
	    
	    //System.out.println("\nUserId = "+userId+"\n");
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    if (action.equals("new")) {
		    response.setContentType("text/html; charset=UTF-8");
	    	obj.closeDB();
	    	// Empty Bean for distributor
	    	IKeHoachNhanVien khnvBean = (IKeHoachNhanVien) new KeHoachNhanVien(userId, "");
	    	khnvBean.init();
	    	khnvBean.createRs();
	    	// Save Data into session
	    	session.setAttribute("khnvBean", khnvBean);
    		String nextJSP = request.getContextPath() + "/pages/Center/KeHoachNhanVienNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else if (action.equals("search"))
	    {
		    response.setContentType("text/html; charset=UTF-8");
	    	String loai =utl.antiSQLInspection( request.getParameter("Loai"));
	    	if (loai == null)
	    		loai = "";
	    	obj.setLoai(loai);
	    	
	    	String thang = utl.antiSQLInspection(request.getParameter("Thang"));
	    	if (thang == null)
	    		thang = "";    	
	    	obj.setThang(thang);
	    	
	    	String nam = utl.antiSQLInspection(request.getParameter("Nam"));
	    	if (nam == null)
	    		nam = "";
	    	obj.setNam(nam);
	    	
	    	String vungId = utl.antiSQLInspection(request.getParameter("vungId")==null?"": request.getParameter("vungId"));
		    obj.setVungId(vungId);
		    
		    String khuvucId = utl.antiSQLInspection(request.getParameter("khuvucId")==null?"": request.getParameter("khuvucId"));
		    obj.setKhuvucId(khuvucId);
	    	
	    	obj.setUserId(userId);
	    	String search = obj.getSearchQuery();
	    		
	    	obj.init(search);
			
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
	    		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/KeHoachNhanVien.jsp");	    	
	    }
	    /*else{
	    	String contentType = request.getContentType();
			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			{
				GET_DATA_FORM_EXCEL( request, response, obj);
				return;
			}
	    }*/
	}
	/*private void GET_DATA_FORM_EXCEL(HttpServletRequest request,
			HttpServletResponse response, IKeHoachNhanVienList obj) 
	{
		HttpSession session = request.getSession();
		String UPLOAD_DIRECTORY = "C:\\upload\\excel\\";
		MultipartRequest multi;
		try 
		{
			multi = new MultipartRequest(request, UPLOAD_DIRECTORY, 20000000, "UTF-8");
			
			//DOC DU LIEU TU EXCEL FILE
			Enumeration files = multi.getFileNames();
			String filename = "";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filename = multi.getFilesystemName(name);
				System.out.println("File  " + UPLOAD_DIRECTORY + filename);
			}
			
			
			if (filename != null && filename.length() > 0)
			{
				String rs = this.readExcel(UPLOAD_DIRECTORY + filename, obj.getUserId());
				if(rs != null && rs.length() != 0){
					obj.setMsg(rs);
				}
			}

		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		String userid = (String) session.getAttribute("userId");
		session.setAttribute("userId", userid);
		obj.setUserId(userid);
		obj.init("");
		session.setAttribute("obj", obj);
		try {
			response.sendRedirect(request.getContextPath() + "/pages/Center/KeHoachNhanVien.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}*/
	/*private String readExcel(String fileName, String nguoitao) 
	{
		File inputWorkbook = new File(fileName);
		Workbook w;
		try
		{
			w = Workbook.getWorkbook(inputWorkbook);
			Sheet sheet = w.getSheet(0);
			int sodong = sheet.getRows();
			int socot = sheet.getColumns();
			Utility util = new Utility();
			System.out.println("So dong " + sodong + " socot " + socot );
			String queryInsert = "";
			
			String nvId = util.antiSQLInspection(sheet.getCell("B4").getContents());
			String thang = util.antiSQLInspection(sheet.getCell("A7").getContents());
			String nam = util.antiSQLInspection(sheet.getCell("B7").getContents());
			if(nvId == null || nvId.trim().length() == 0)
				return "Mã nhân viên không đúng";
			else{
				String test = "select * from NHANVIEN WHERE PK_SEQ = '"+nvId+"'";
				dbutils db = new dbutils();
				ResultSet kq = db.get(test);
				if(kq == null)
					return "Mã nhân viên không đúng";
				kq.close();
				db.shutDown();
			}
			if(thang == null || nam == null || thang.trim().length() == 0 || nam.trim().length() == 0)
				return "Tháng Năm chưa nhập";
			
			queryInsert = "delete from KEHOACHNV_NPP where KEHOACHNV_FK in (select PK_SEQ from KEHOACHNV where NHANVIEN_FK = '"+nvId+"' and THANG = '"+thang+"' and NAM = '"+nam+"') \n";
			queryInsert += "delete from KEHOACHNV_THITRUONG where KEHOACHNV_FK in (select PK_SEQ from KEHOACHNV where NHANVIEN_FK = '"+nvId+"' and THANG = '"+thang+"' and NAM = '"+nam+"')\n";
			queryInsert += "delete from KEHOACHNV where NHANVIEN_FK = '"+nvId+"' and THANG = '"+thang+"' and NAM = '"+nam+"' \n";
			queryInsert += "INSERT INTO KEHOACHNV(NHANVIEN_FK, THANG, NAM, NGAYTAO, NGUOITAO, NGAYSUA, NGUOISUA, TRANGTHAI) \n" +
					"VALUES('"+nvId+"','"+thang+"','"+nam+"','"+getDateTime()+"','"+nguoitao+"','"+getDateTime()+"','"+nguoitao+"','0') \n";
			
			for (int i = 11; i < sodong; i++)
			{
				String ngay = "1";
				String ngayE = util.antiSQLInspection(sheet.getCell(0, i).getContents());
				String nppId = util.antiSQLInspection(sheet.getCell(1, i).getContents());
				String GhichuNpp = util.antiSQLInspection(sheet.getCell(2, i).getContents());
				String Tbh_fk = util.antiSQLInspection(sheet.getCell(3, i).getContents());
				String GhichuTT = util.antiSQLInspection(sheet.getCell(4, i).getContents());
				
				if(ngayE == null || ngayE.trim().length() == 0)
					ngayE = ngay;
				ngay = ngayE;
				if(nppId.trim().length() > 0)
				{
					queryInsert += "INSERT INTO KEHOACHNV_NPP(KEHOACHNV_FK, GHICHU, NGAY, NPP_FK) VALUES ((select IDENT_CURRENT('KEHOACHNV'), N'"+GhichuNpp+"', '"+ngay+"', '"+nppId+"') \n";
				}
				if(Tbh_fk.trim().length() > 0 ){
					queryInsert += "INSERT INTO KEHOACHNV_THITRUONG(KEHOACHNV_FK, TBH_FK, GHICHU, NGAY) VALUES ((select IDENT_CURRENT('KEHOACHNV'),'"+Tbh_fk+"',N'"+GhichuTT+"','"+ngay+"') \n";
				}
			}
			System.out.println(queryInsert);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return null;
	}*/
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
      
        return dateFormat.format(date);
	}
	
	private String getDateTime(String pattern) 
	{
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
      
        return dateFormat.format(date);
	}

}
