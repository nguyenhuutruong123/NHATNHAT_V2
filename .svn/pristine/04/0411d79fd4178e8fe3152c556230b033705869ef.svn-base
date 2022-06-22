package geso.dms.center.servlets.duyettrakhuyenmai;

import geso.dms.center.beans.duyettrakhuyenmai.*;
import geso.dms.center.beans.duyettrakhuyenmai.imp.*;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.oreilly.servlet.MultipartRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class DuyettrakhuyenmaiUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
	
    public DuyettrakhuyenmaiUpdateSvl() {
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
		IDuyettrakhuyenmai tctskuBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);
	   
	    tctskuBean = new Duyettrakhuyenmai(id);
	    tctskuBean.setId(id);
	    tctskuBean.setUserId(userId);
	    
        tctskuBean.init();
        session.setAttribute("tctskuBean", tctskuBean);
        
        String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";
        if(querystring.indexOf("display") >= 0)
        	nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiDisplay.jsp";
        
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IDuyettrakhuyenmai tctskuBean;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		/*
		 * if(!csdr.__validate_post()) {
		 * response.sendRedirect(request.getContextPath() + "/redirect.jsp"); return; }
		 */
 		String action = request.getParameter("action");
 		System.out.println("Action la: " + action);
 		if(action == null)
 			action = "upload";
 		if(!action.equals("excel"))
			this.out = response.getWriter();
 		String contentType = request.getContentType();
 		
		Utility util = new Utility();
		
	   	String id = util.antiSQLInspection(request.getParameter("id"));
	    if(id == null){  	
	    	tctskuBean = new Duyettrakhuyenmai("");
	    }else{
	    	tctskuBean = new Duyettrakhuyenmai(id);
	    }
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		tctskuBean.setUserId(userId);
		
		String diengiai = util.antiSQLInspection(request.getParameter("diengiai"));
		if (diengiai == null)
			diengiai = "";
		tctskuBean.setDiengiai(diengiai);
		
		String thang = util.antiSQLInspection(request.getParameter("thang"));
		if (thang == null)
			thang = "";
		tctskuBean.setThang(thang);
		
		String nam = util.antiSQLInspection(request.getParameter("nam"));
		if (nam == null)
			nam = "";
		tctskuBean.setNam(nam);
		
		String scheme = util.antiSQLInspection(request.getParameter("ctkmId"));
		if (scheme == null)
			scheme = "";
		tctskuBean.setCtkmId(scheme);
		System.out.println("ctkm ID day : "+scheme);
		
		String ngayduyet = util.antiSQLInspection(request.getParameter("ngayduyet"));
		if (ngayduyet == null)
			ngayduyet = "";
		tctskuBean.setNgayduyet(ngayduyet);
		
		String tungay_ds = util.antiSQLInspection(request.getParameter("tungay_ds"));
		if (tungay_ds == null)
			tungay_ds = "";
		System.out.println("tungayds : "+ tungay_ds);
		tctskuBean.setTungay_ds(tungay_ds);
		
		String denngay_ds = util.antiSQLInspection(request.getParameter("denngay_ds"));
		if (denngay_ds == null)
			denngay_ds = "";
		System.out.println("denngayds : "+ denngay_ds);
		tctskuBean.setDenngay_ds(denngay_ds);	
		
		
		//String[] nppId = request.getParameterValues("nppId");
		//tctskuBean.setNppId(nppId);
		
		String[] nppTen = request.getParameterValues("nppTen");
		tctskuBean.setNppTen(nppTen);
		
		String[] khId = request.getParameterValues("khId");
		tctskuBean.setKhId(khId);
		
		String[] khTen = request.getParameterValues("khTen");
		tctskuBean.setKhTen(khTen);
		
		String[] mucdk = request.getParameterValues("mucdk");
		tctskuBean.setMucDk(mucdk);
		
		String[] doanhso = request.getParameterValues("doanhso");
		tctskuBean.setDoanhso(doanhso);
		
		String[] mucdat = request.getParameterValues("mucdat");
		tctskuBean.setMucthuong(mucdat);
		
		String[] tongtien = request.getParameterValues("tongthuong");
		tctskuBean.setTongtien(tongtien); 
		
		String[] sanpham = request.getParameterValues("sanpham");
		tctskuBean.setSanpham(sanpham);
		
		String[] dangkyIds =  request.getParameterValues("dangkyIds")  ;
		tctskuBean.setDangkyIds(dangkyIds);
 		
 		if(action.equals("save"))
 		{    
 			if (id == null || id.trim().length() <= 0)
 			{
 				if (!(tctskuBean.createDuyet()))
 				{			
 			    	tctskuBean.setUserId(userId);			    	
 			    	tctskuBean.createRs();
 			    	session.setAttribute("userId", userId);
 					session.setAttribute("tctskuBean", tctskuBean);				
 					String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiNew.jsp";
 					response.sendRedirect(nextJSP);
 					return;
 				}
 				else
 				{
 					// tạo ok thì qua trang cập nhật luôn để tính thưởng
 					tctskuBean.setUserId(userId);	    	
 			    	tctskuBean.init();
 			    	session.setAttribute("userId", userId);
 					session.setAttribute("tctskuBean", tctskuBean);				
 					String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";
 					response.sendRedirect(nextJSP);
 					return;
 				}
 			}
 			else
 			{
 				if (!(tctskuBean.updateTctSKU()))
 				{			
 			    	tctskuBean.setUserId(userId);			    	
 			    	tctskuBean.createRs();
 			    	session.setAttribute("userId", userId);
 					session.setAttribute("tctskuBean", tctskuBean);			
 					String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";
 					response.sendRedirect(nextJSP);
 					return;
 				}
 				else
 				{
 					tctskuBean.DbClose();
 					IDuyettrakhuyenmaiList obj = new DuyettrakhuyenmaiList();
 				    obj.setUserId(userId);
 				    obj.init("");
 					session.setAttribute("obj", obj);
 				    String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMai.jsp";
 					response.sendRedirect(nextJSP);
 					return;
 				}
 			}
	    }
 		else if(action.equals("newform")){
 			// tính thưởng
 			tctskuBean.createTctSKU();		
 			tctskuBean.init();
	    	tctskuBean.setUserId(userId);
	    	
	    	session.setAttribute("userId", userId);
			session.setAttribute("tctskuBean", tctskuBean);
			
			String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";
			response.sendRedirect(nextJSP);
			return;		
 		}
 		else if(action.equals("excel")){
 			Export(tctskuBean, response);
 			/*try 
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=Duyettratichluy.xlsm");
				OutputStream out = response.getOutputStream();
				ExportToExcel( out, tctskuBean );
				tctskuBean.DbClose();
			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
				System.out.println("Error Here : "+ex.toString());
				request.getSession().setAttribute("errors", ex.getMessage());
			}*/
 		}
 		else if(action.equals("upload")){
 			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
			{
 				MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8"); 
		    	if(!csdr.__validate_post_mul(multi))
				{
					System.out.println("contentType loi : "+ contentType);
					response.sendRedirect(request.getContextPath() + "/redirect.jsp");
					return;
				}
 				//MultipartRequest multi = new MultipartRequest(request, getServletContext().getRealPath("\\upload\\"), 20000000, "UTF-8"); 
			    userId = util.antiSQLInspection(multi.getParameter("userId"));
			    id = util.antiSQLInspection(multi.getParameter("id"));	 
			    if(id == null){  	
			    	tctskuBean = new Duyettrakhuyenmai("");
			    }else{
			    	tctskuBean = new Duyettrakhuyenmai(id);
			    }
			    tctskuBean.setUserId(userId);
			    tctskuBean.setCtkmId(util.antiSQLInspection(multi.getParameter("ctkmId"))==null? "":util.antiSQLInspection(multi.getParameter("ctkmId")));	 
			    tctskuBean.setDiengiai(util.antiSQLInspection(multi.getParameter("diengiai"))==null? "":util.antiSQLInspection(multi.getParameter("diengiai")));	
			    tctskuBean.setNgayduyet(util.antiSQLInspection(multi.getParameter("ngayduyet"))==null? "":util.antiSQLInspection(multi.getParameter("ngayduyet")));   	 
			    
			  	Enumeration files = multi.getFileNames(); 
			  	String filenameu  ="";
			  	while (files.hasMoreElements())
	            {
	                 String name = (String)files.nextElement();
	                 filenameu = multi.getFilesystemName(name); 
	                 System.out.println("name :   "+name);;
	            }
			  	boolean err = false;
			  	String filename = "C:\\java-tomcat\\data\\" + filenameu;
				//String filename=    getServletContext().getRealPath("\\upload\\")+ "\\"+ filenameu;
				if(filenameu == null)
					tctskuBean.setMsg("Không có file nào được upload");
				if (filenameu != null && filename.length() > 0)
				{
					//doc file excel
					File file = new File(filename);
					System.out.println("filename  "+file);
					Workbook workbook;
					int indexRow = 4;
			
					try 
					{						
						System.out.println(file);
						workbook = Workbook.getWorkbook(file);
						
						Sheet[] sheet1 = workbook.getSheets();
						Sheet sheet=sheet1[0];					 
						Cell[] cells ;
						String khachhang_fks = "";
						Hashtable<String, Integer> kh_muc = new Hashtable<String, Integer>();
						int sokh = 0;
						//System.out.println("getRows = " +sheet.getRows());
						for(int i= indexRow; i < sheet.getRows();i++)
						{	
							cells = sheet.getRow(i);
							//System.out.println("cell length: " +cells.length);
							if (cells.length >= 7){
								String KHACHHANG_FK = cells[1].getContents().toString().trim();
								String muc_dk = cells[6].getContents().toString();
								khachhang_fks += KHACHHANG_FK + ",";
								if(muc_dk.trim().length() == 0)
									muc_dk = "0";
								kh_muc.put(KHACHHANG_FK, Integer.parseInt(muc_dk));
								sokh ++;
								System.out.println("__ " + KHACHHANG_FK + ":" + muc_dk);
							}							
						}
						if(sokh == 0)
							tctskuBean.setMsg("Không có khách hàng nào được chọn!");
						
						if(khachhang_fks.length() > 0)
							khachhang_fks = khachhang_fks.substring(0, khachhang_fks.length() - 1);

						
						if(sokh > 0)
						{
							
							if( tctskuBean.setDuyetMucThuong(khachhang_fks, kh_muc))
							{
								tctskuBean.init();
						    	session.setAttribute("tctskuBean", tctskuBean);
						    	String nextJSP;
								if (id == null){
									nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiNew.jsp";
								}
								else{
									nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";   						
								}
								response.sendRedirect(nextJSP);
						    	return;
							}
						}
					}catch(Exception er){
						er.printStackTrace();
						System.out.println("Exception. "+er.getMessage());
						tctskuBean.setMsg("Lỗi trong quá trình upload: " + er.getMessage());
					}
				}
				tctskuBean.createRs();
		    	session.setAttribute("tctskuBean", tctskuBean);
		    	String nextJSP;
				if (id == null){
					nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiNew.jsp";
				}
				else{
					nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";   						
				}
				response.sendRedirect(nextJSP);
		    	return;
			}
 		}
		else
		{
			tctskuBean.createRs();
			session.setAttribute("userId", userId);
			session.setAttribute("tctskuBean", tctskuBean);
			
			String nextJSP;
			if (id == null){
				nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiNew.jsp";
			}
			else{
				nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiUpdate.jsp";   						
			}
			response.sendRedirect(nextJSP);
		}
	}
	
	public String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private void Export(IDuyettrakhuyenmai obj, HttpServletResponse response) {
		OutputStream out = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=Duyet_tra_tich_luy_"+obj.getCtkmId()+".xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());
			WritableSheet sheet = w.createSheet("Sheet1", 0);
			
			WritableCellFormat cellFormat = new WritableCellFormat();
			cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			
			NumberFormat dp3 = new NumberFormat("#,###,###,##");
			WritableCellFormat inFormat = new WritableCellFormat(dp3);		
			inFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
			inFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
			
			int cong = 0;
			sheet.addCell(new Label(cong++, 3, "STT", cellFormat));
			sheet.addCell(new Label(cong++, 3, "ID", cellFormat));
			sheet.addCell(new Label(cong++, 3, "MÃ KH", cellFormat));
			sheet.addCell(new Label(cong++, 3, "TÊN KH", cellFormat));
			sheet.addCell(new Label(cong++, 3, "DOANH SỐ", cellFormat));
			sheet.addCell(new Label(cong++, 3, "MỨC ĐẠT", cellFormat));
			sheet.addCell(new Label(cong++, 3, "MỨC DUYỆT ĐẠT", cellFormat));
			sheet.addCell(new Label(cong++, 3, "MỨC Đăng ký", cellFormat));
			sheet.addCell(new Label(cong++, 3, "TỔNG THƯỞNG/TỔNG LƯỢNG", cellFormat));
			sheet.addCell(new Label(cong++, 3, "NVBH", cellFormat));
			sheet.addCell(new Label(cong++, 3, "TỈNH THÀNH", cellFormat));
			
			obj.createRs();
			int stt = 4;
			int i =0;
			ResultSet rs =  obj.getKhDuyetRs();
			if(rs != null)
			while ( rs.next() )
			{
				cong = 0;
				sheet.addCell(new Label(cong ++, stt, (++i)+ "", cellFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("KHACHHANG_FK") , cellFormat));
				sheet.addCell(new Label(cong ++, stt,rs.getString("MAFAST"), cellFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("KHTEN"), cellFormat));
				sheet.addCell( new Number(cong ++, stt,rs.getDouble("DOANHSO"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("MUCDAT"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("MUCDUYET"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("MUCDANGKY"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("TONGTIEN"), inFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("daidienkinhdoanh"), cellFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("tinhthanh"), cellFormat));
				stt++;
			}
			
		  
			w.write();
			w.close();
		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.getMessage());
			e.printStackTrace();
		} finally
		{
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	

}
