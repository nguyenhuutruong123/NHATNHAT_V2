package geso.dms.distributor.servlets.banggiabandoitac;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.banggiabandoitac.IBanggiabandoitac;
import geso.dms.distributor.beans.banggiabandoitac.IBanggiabandoitacList;
import geso.dms.distributor.beans.banggiabandoitac.imp.Banggiabandoitac;
import geso.dms.distributor.beans.banggiabandoitac.imp.BanggiabandoitacList;

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.oreilly.servlet.MultipartRequest;

public class BanggiabandoitacUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet 
{
   static final long serialVersionUID = 1L;
   
	public BanggiabandoitacUpdateSvl() {
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
		PrintWriter out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String id = util.getId(querystring);  	
	    out.println(id);
	    
	    IBanggiabandoitac bgBean = new Banggiabandoitac();
	    
        bgBean.setUserId(userId);
        bgBean.setId(id);
        bgBean.init();
        
        session.setAttribute("bgBean", bgBean);
        String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTacUpdate.jsp";
        
        response.sendRedirect(nextJSP);

	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		MultipartRequest multi = null;

		String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			multi = new MultipartRequest(request,"C:\\java-tomcat\\data\\", 20000000, "UTF-8");
		}	
		
		String action = Utility.getValueFromClient("action",contentType,multi,request);
		if (action == null)
			action = "";
		
		
		IBanggiabandoitac bgBean = (IBanggiabandoitac) new Banggiabandoitac();
	    Utility util = new Utility();
	    
		String id =  Utility.getValueFromClient("id",contentType,multi,request);  
	    if(id == null) 	
	    	id = "";
	    bgBean.setId(id);
	    
		String userId =  Utility.getValueFromClient("userId",contentType,multi,request);   
		bgBean.setUserId(userId);

    	String bgTen = Utility.getValueFromClient("ten",contentType,multi,request);   
		if (bgTen == null)
			bgTen = "";				
    	bgBean.setTen(bgTen);

		String dvkdId =  Utility.getValueFromClient("dvkdId",contentType,multi,request);    
		if(dvkdId == null)
			dvkdId = "";	
		bgBean.setDvkdId(dvkdId);
	    
		String kenhId =  Utility.getValueFromClient("kbhId",contentType,multi,request);    
		if(kenhId == null)
			kenhId = "";
		bgBean.setKenhId(kenhId);
		
		String nppId =  Utility.getValueFromClient("nppId",contentType,multi,request); 
		if(nppId == null)
			nppId = "";
		bgBean.setNppId(nppId);
		
		String tungay =  Utility.getValueFromClient("tungay",contentType,multi,request);    
		if(tungay == null)
			tungay = "";
		bgBean.setTungay(tungay);
		
		String denngay = Utility.getValueFromClient("denngay",contentType,multi,request);    
		if(denngay == null)
			denngay = "";
		bgBean.setDenngay(denngay);
		
		String nhomsp = Utility.getValueFromClient("nhomsp",contentType,multi,request);   
		if(nhomsp == null)
			nhomsp = "";
		bgBean.setNhomsp(nhomsp);
		
		String bgbleId = Utility.getValueFromClient("bgbleId",contentType,multi,request);   
		if(bgbleId == null)
			bgbleId = "";
		bgBean.setBgId(bgbleId);
		

		String trangthai =  Utility.getValueFromClient("trangthai",contentType,multi,request);    
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	bgBean.setTrangthai(trangthai);
    	
    	String[] doitacIds =  Utility.getArrValueFromClient("doitacIds",contentType,multi,request);    
    	if(doitacIds != null)
    	{
    		String doitacId = "";
    		for(int i = 0; i < doitacIds.length; i++)
    			doitacId += doitacIds[i] + ",";
    		
    		if(doitacId.trim().length() > 0)
    		{
    			doitacId = doitacId.substring(0, doitacId.length() - 1);
    			bgBean.setDoitacId(doitacId);
    		}
    	}
    	
    	String[] spIds = Utility.getArrValueFromClient("spIds",contentType,multi,request); 
    	String[] ptchietkhau = Utility.getArrValueFromClient("ptchietkhau",contentType,multi,request); 
    	String[] dongiaSAUCK = Utility.getArrValueFromClient("dongiaSAUCK",contentType,multi,request); 
    	
    	if(spIds != null )
    	{
    		Hashtable<String, String> sanphamCK = new Hashtable<String, String>();
    		Hashtable<String, String> sanphamDG_SauCK = new Hashtable<String, String>();
    		
    		for(int i = 0; i < spIds.length; i++)
    		{
    			sanphamCK.put(spIds[i], ptchietkhau[i]);
    			sanphamDG_SauCK.put(spIds[i], dongiaSAUCK[i]);
    		}
    		bgBean.setSanphamCK(sanphamCK);
    		bgBean.setSanphamDG_SauCK(sanphamDG_SauCK);
    	}
		   	
		if(action.equals("save"))
		{
			if (id.length()==0)
			{
				if (!(bgBean.CreateBg()))
				{			
					bgBean.setUserId(userId);
					bgBean.createRS();
					session.setAttribute("bgBean", bgBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTacNew.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					IBanggiabandoitacList obj = new BanggiabandoitacList();
					obj.setUserId(userId);
					obj.init("");
					session.setAttribute("obj", obj);
						
					String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTac.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}	
			}
			else
			{
				if (!(bgBean.UpdateBg()))
				{								
					bgBean.setUserId(userId);
					bgBean.setId(id);
					bgBean.init();
					session.setAttribute("bgBean", bgBean);
					String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTacUpdate.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					IBanggiabandoitacList obj = new BanggiabandoitacList();
					obj.setUserId(userId);
					obj.init("");
					session.setAttribute("obj", obj);
					String nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTac.jsp";
					response.sendRedirect(nextJSP);			    			    									
				}
			}
		}
		else
		{	
			
			if(action.equals("upload"))
			{
				 Upload( bgBean , contentType, multi )  ;
			}
			String nextJSP;
			if (id.length()==0)
			{			
				bgBean.setUserId(userId);
				bgBean.createRS();
				session.setAttribute("bgBean", bgBean);
				nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTacNew.jsp";
			}
			else
			{
				bgBean.setUserId(userId);
				bgBean.setId(id);
				bgBean.init();
				session.setAttribute("bgBean", bgBean);

				nextJSP = request.getContextPath() + "/pages/Distributor/BangGiaBanDoiTacUpdate.jsp";   						
			}
			response.sendRedirect(nextJSP);
		}
	}
	
	public void Upload(IBanggiabandoitac obj ,String contentType,MultipartRequest multi )  
	{
		
		Hashtable<String, String> sanphamCK = new Hashtable<String, String>();
		Hashtable<String, String> sanphamDG_SauCK = new Hashtable<String, String>();
		
		
		
		/// mẫu upload file
		Enumeration files = multi.getFileNames();
		String filenameu = "";
		while (files.hasMoreElements())
		{
			String name = (String) files.nextElement();
			filenameu = multi.getFilesystemName(name);
			System.out.println("name  " + name);
			;
		}
		String filename = "C:\\java-tomcat\\data\\" + filenameu;// nhớ tạo máy local
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
				
				String data ="";
				for (int i = indexRow;i <sheet.getRows(); i++)
				{
					cells = sheet.getRow(i);
					if (cells.length > 0)
					{
						if (cells[0].getContents().toString().length() > 0)
						{
							String masp = getStringValue(cells,0);
							if(masp.trim().length()<0)
							{
								obj.setMessage( "Vui lòng nhập mã sp");			
								return;
							}
							double gia = Utility.parseDouble(getStringValue(cells,1));
							
						/*	for(int i = 0; i < spIds.length; i++)
							{
								sanphamCK.put(spIds[i], ptchietkhau[i]);
								sanphamDG_SauCK.put(spIds[i], dongiaSAUCK[i]);
							}*/
							
							
						}
						
					}
					indexRow++;
				}
				obj.setSanphamCK(sanphamCK);
				obj.setSanphamDG_SauCK(sanphamDG_SauCK);
				obj.setMessage("Upload thành công!");
				
				
	
				
			} 
			catch (Exception er)
			{
				er.printStackTrace();
				obj.setMessage( "Lỗi upload:"+ er.getMessage());			
				return;
			}
		
		}
		
		
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