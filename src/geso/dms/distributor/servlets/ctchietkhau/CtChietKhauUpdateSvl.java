package geso.dms.distributor.servlets.ctchietkhau;

import geso.dms.distributor.beans.ctchietkhau.ICtChietKhau;
import geso.dms.distributor.beans.ctchietkhau.ICtChietKhauList;
import geso.dms.distributor.beans.ctchietkhau.imp.CtChietKhau;
import geso.dms.distributor.beans.ctchietkhau.imp.CtChietKhauList;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.write.Label;
import jxl.write.WritableSheet;

import com.oreilly.servlet.MultipartRequest;



 public class CtChietKhauUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
  

   public CtChietKhauUpdateSvl() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
		HttpSession session = request.getSession();
		
		out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	
	    out.println(id);    
	    
	    ICtChietKhau bgBean = new CtChietKhau();
	    String nextJSP = "";
	    
	    if(querystring.contains("display")) {
	    	bgBean.setDisplay("1");
	        bgBean.setUserId(userId);
	        bgBean.setId(id);
	        bgBean.init();
	        System.out.println("id: "+id);      
	        nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp";
	    }
	    
	    if(querystring.contains("update")) {
	        bgBean.setUserId(userId);
	        bgBean.setId(id);
	        bgBean.init();
	        System.out.println("id: "+id);      
	        nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp";
	    }
        
        if(querystring.contains("copy"))
        {
        	 bgBean.setIsCopy("1");
        	 bgBean.setUserId(userId);
        	 bgBean.setId(id);
        	 bgBean.init();
        	 bgBean.setId(null);
        	 bgBean.setTrangthai("0");
        	 nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauNew.jsp";
        }
        
        session.setAttribute("bgblcBean", bgBean);
        response.sendRedirect(nextJSP);

	}  	
	
	
	public void Upload(HttpServletRequest request, HttpServletResponse response,Utility util,HttpSession session,String contentType,MultipartRequest multi ) throws ServletException, IOException, SQLException
	{
		ICtChietKhau bgBean = new CtChietKhau();
		bgBean.setAction("upload");
		String userId = util.antiSQLInspection(multi.getParameter("userId"));
		String id =  util.antiSQLInspection(multi.getParameter("id"));
		bgBean.setUserId(userId);
		
	    if(id == null){  	
	    	id = "";
	    }
	    bgBean.setId(id);
		bgBean.setUserId(userId);
	    
    	String bgTen = multi.getParameter("bgTen");
		if (bgTen == null)
			bgTen = "";				
    	bgBean.setTen(bgTen);

		String tungay = util.antiSQLInspection(multi.getParameter("tungay"));
		if (tungay == null)
			tungay = "";				
    	bgBean.setTungay(tungay);
	        	
    	String denngay = util.antiSQLInspection(multi.getParameter("denngay"));
		if (denngay == null)
			denngay = "";				
    	bgBean.setDenngay(denngay);
    	
		String trangthai = util.antiSQLInspection(multi.getParameter("trangthai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	bgBean.setTrangthai(trangthai);
		
    	
    	String[] spIds = multi.getParameterValues("spIds");
		String[] chietkhauSp = multi.getParameterValues("chietkhauSp");
		
		if(spIds != null)
		{
			Hashtable<String ,Double> hash = new Hashtable<String, Double>();
			for(int i = 0; i < spIds.length ; i++)
			{
				double ck = Utility.parseDouble(chietkhauSp[i].replace(",",""));
				if(ck > 0)
				hash.put(spIds[i],ck);
			}
			bgBean.setSanpham_chietkhau(hash);
		}
    	
		String[] chon = multi.getParameterValues("chon");
		if(chon != null)
		{
			for (int i=0;i<chon.length;i++)
			{
				System.out.println("chon: "+chon[i]);
			}
			bgBean.setNppArr(chon);
		}
		
		Enumeration files = multi.getFileNames();
		String filenameu = "";
		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			filenameu = multi.getFilesystemName(name);
			System.out.println("name  " + name);
		}
		String filename = "C:\\java-tomcat\\data\\" + filenameu;
		if(filename.trim().length() >0)
		{
			// doc file excel
			File file = new File(filename);
			System.out.println("filename  " + file);
			Workbook workbook;

			int indexRow = 1;// dòng đầu tiên đọc data

			try
			{
				workbook = Workbook.getWorkbook(file);
				Sheet[] sheet1 = workbook.getSheets();			
				Cell[] cells;
				indexRow = 1;
				Sheet sheet = sheet1[0];
				String dataupload ="";
				for (int i = indexRow;i <sheet.getRows(); i++)
				{
					cells = sheet.getRow(i);
					System.out.println("Cells length " +cells.length);
					if (cells.length > 0)						
					{
						if (cells[0].getContents().toString().length() > 0)
						{
							String masp = getStringValue(cells,0);
							double chietkhau = Utility.parseDouble(getStringValue(cells,1));
							if(masp.trim().length()<0)
							{
								bgBean.setMessage(" Không lấy được Mã SP ở dòng ("+(i+1)+") ");

							}
							
							if (dataupload.trim().length() > 0) {
								dataupload +=" union select '"+masp+"' as masp," +chietkhau+" as ck"; }
							else {
								dataupload +=" select '"+masp+"' as masp,"+chietkhau+" as ck"; }
						}
						
						String query = " select * from ("+dataupload+") data ";
						System.out.println("Indexrow: "+indexRow + "\n Query: "+query);
					}
					indexRow++;
				}
				
				Sheet[] sheet2 = workbook.getSheets();			
				indexRow = 1;
				sheet = sheet2[1];
				String dataupload2 = "";
				for (int i = indexRow;i <sheet.getRows(); i++)
				{
					cells = sheet.getRow(i);
					System.out.println("Cells length " +cells.length);
					if (cells.length > 0)						
					{
						if (cells[0].getContents().toString().length() > 0)
						{
							String manpp = getStringValue(cells,0);
							if(manpp.trim().length()<0)
							{
								bgBean.setMessage(" Không lấy được Mã NPP ở dòng ("+(i+1)+") ");

							}
							
							if (dataupload2.trim().length() > 0) {
								dataupload2 +=" union select '"+manpp+"' as manpp"; }
							else {
								dataupload2 +=" select '"+manpp+"' as manpp"; }
						}
						
						String query = " select * from ("+dataupload2+") data2 ";
						System.out.println("Indexrow: "+indexRow + "\n Query2: "+query);
					}
					indexRow++;
				}
				
					System.out.println("dataupload: "+dataupload);
					System.out.println("Length: "+dataupload.trim().length() + " "+dataupload2.trim().length());
					if(dataupload.trim().length() <=0 || dataupload2.trim().length() <=0)
					{
						if (dataupload.trim().length() <=0)
						{ bgBean.setMessage("Không lấy được dữ liệu từ Sheet 1"); }
						else 
						{ bgBean.setMessage("Không lấy được dữ liệu từ Sheet 2"); }
						id = bgBean.getId();
						bgBean.init();
						session.setAttribute("bgblcBean", bgBean);
						session.setAttribute("userId", userId);
						response.sendRedirect(request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp");
						return;
					}
					else
					{
						bgBean.setDataupload(dataupload);
						bgBean.setDataupload2(dataupload2);
						System.out.println("Data: "+dataupload); 
						System.out.println("Data2: "+dataupload2); 
					}
					
					System.out.println("id: "+id);
					
					if (id != null && !id.equals("")) {
						if (!bgBean.UpdateBgblc(request)){
							id = bgBean.getId();
							System.out.println("ID2: "+id);
							String msg = bgBean.getMessage();
							bgBean.setMessage("Upload không thành công! "+msg);
							bgBean.init();
							session.setAttribute("bgblcBean", bgBean);
							session.setAttribute("userId", userId);
							response.sendRedirect(request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp");
						}
						else
						{
							id = bgBean.getId();
							System.out.println("ID2: "+id);
							bgBean.setMessage("Upload thành công!");
							bgBean.init();
							session.setAttribute("bgblcBean", bgBean);
							session.setAttribute("userId", userId);
							response.sendRedirect(request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp");
						}
						
					}
					
					else {
						if (!bgBean.CreateBgblc(request)){
							id = bgBean.getId();
							System.out.println("ID2: "+id);
							String msg = bgBean.getMessage();
							bgBean.setMessage("Upload không thành công! "+msg);
							bgBean.init();
							session.setAttribute("bgblcBean", bgBean);
							session.setAttribute("userId", userId);
							response.sendRedirect(request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp");
						}
						else
						{
							id = bgBean.getId();
							System.out.println("ID2: "+id);
							bgBean.setMessage("Upload thành công!");
							bgBean.init();
							session.setAttribute("bgblcBean", bgBean);
							session.setAttribute("userId", userId);
							response.sendRedirect(request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp");
						}
					}
			}
			catch (Exception er)
			{
				er.printStackTrace();	
			}
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Utility util = new Utility();
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();
		OutputStream out = response.getOutputStream();
		String action = request.getParameter("action");
		
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");
			try {
				Upload( request,  response, util, session, contentType, multi);
			} catch (SQLException e) {
				
			}
			return;
		}
		
		ICtChietKhau bgBean = new CtChietKhau();
	    
		String id =  util.antiSQLInspection(request.getParameter("id"));
	    
	    if(id == null){  	
	    	id = "";
	    }
	    bgBean.setId(id);
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		bgBean.setUserId(userId);
	    
		bgBean.getNppInfo();
		
    	String bgTen = request.getParameter("bgTen");
		if (bgTen == null)
			bgTen = "";				
    	bgBean.setTen(bgTen);

    	
    	String ketthuc = util.antiSQLInspection(request.getParameter("ketthuc"));
		if (ketthuc == null)
			ketthuc = "0";	
		else
			ketthuc ="1";
    	bgBean.setKetthuc(ketthuc);
		
    	
    	
	        	
		String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	bgBean.setTrangthai(trangthai);
		
    	
    	
    	
    	String khId = request.getParameter("khId");
		if (khId == null)
			khId = "";				
    	bgBean.setKhId(khId);
    	
    	
    	if(khId.trim().length() > 0)
    	{
	    	String hopdongId = request.getParameter("hopdongId");
			if (hopdongId == null)
				hopdongId = "";				
	    	bgBean.setHopdongId(hopdongId);
	    	
	    	if(hopdongId.trim().length() >0)
	    	{	
	    		String[] bacsiIds = request.getParameterValues("bacsiIds");
	    		if(bacsiIds != null)
	    		{	
	    			Hashtable<String ,Double> hash = new Hashtable<String, Double>();
	    			for(int i = 0 ; i < bacsiIds.length ;i++)
	    			{
	    				String[] spIds = request.getParameterValues("spIds"+bacsiIds[i]);
	    	    		String[] chietkhauSp = request.getParameterValues("chietkhauSp"+bacsiIds[i]);
	    	    		if(spIds!= null)
	    	    		for(int x = 0; x < spIds.length ; x++)
		    			{
		    				String ckStr = chietkhauSp[x].replace(",","");
		    				if(ckStr.trim().length() > 0 )
		    				{
		    					double ck = Utility.parseDouble(chietkhauSp[x].replace(",",""));
		    					if(ck > 0)
		    					hash.put(bacsiIds[i]+"-"+spIds[x],ck);
		    				}
		    			}
	    			}
	    			bgBean.setSanpham_chietkhau(hash);
	    		}
	    		
	    		
	    	}
    	}
    	
    	
	
    	
		String ngaysua = getDateTime();
    	bgBean.setNgaysua(ngaysua);
    	bgBean.setNgaytao(ngaysua);
    	
		bgBean.setNguoitao(userId);
		bgBean.setNguoisua(userId);
    			
		

		if(action.equals("save"))
		{
			if (id.length()==0)
			{
				if (!(bgBean.CreateBgblc(request))){									
					bgBean.setUserId(userId);
					bgBean.createRS();
					session.setAttribute("bgblcBean", bgBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauNew.jsp";
					response.sendRedirect(nextJSP);
				}else{
					ICtChietKhauList obj = new CtChietKhauList(userId);
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhau.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
				
			}else{
				if (!(bgBean.UpdateBgblc(request))){								
					bgBean.setUserId(userId);
					bgBean.createRS();
					session.setAttribute("bgblcBean", bgBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else{
					ICtChietKhauList obj = new CtChietKhauList(userId);
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhau.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
			}
		}else {
			System.out.println("id la "+id);
			String nextJSP;
			if (id.length()==0)
			{							
				bgBean.setUserId(userId);
				bgBean.createRS();
				session.setAttribute("bgblcBean", bgBean);
				nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauNew.jsp";
			}
			else
			{
				bgBean.setUserId(userId);
				bgBean.setId(id);
				bgBean.init();
				
				session.setAttribute("bgblcBean", bgBean);
				
				nextJSP = request.getContextPath() + "/pages/Distributor/CtChietKhauUpdate.jsp";   						
			}
			response.sendRedirect(nextJSP);
			
		}
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public String getStringValue(Cell[] cells,int vitri)
	{
		try
		{
			return cells[vitri].getContents().toString().replace("\t", "").replace(",", "").replace(" ", "").trim();
		}
		catch (Exception e) {
			return "";
		}
	}

	
}