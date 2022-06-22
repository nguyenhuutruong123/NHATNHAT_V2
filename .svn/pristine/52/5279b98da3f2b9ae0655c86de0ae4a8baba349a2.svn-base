package geso.dms.center.servlets.nhapkho;

import geso.dms.center.util.Utility;
import geso.dms.center.beans.nhapkho.IErpNhapkho;
import geso.dms.center.beans.nhapkho.IErpNhapkhoList;
import geso.dms.center.beans.nhapkho.imp.ErpNhapkho;
import geso.dms.center.beans.nhapkho.imp.ErpNhapkhoList;
import geso.dms.center.beans.upload.IUpload;
import geso.dms.center.beans.upload.imp.Upload;
import geso.dms.center.db.sql.dbutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;

import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.oreilly.servlet.MultipartRequest;


public class ErpNhapkhoUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "c:\\java-tomcat\\upload\\excel\\";
	PrintWriter out;
	public ErpNhapkhoUpdateSvl() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 
		
		
		Utility util = new Utility();
	    
	    if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
	    
		if(userTen==null){
			userTen="";
		}
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);


			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			if (userId.length()==0)
				userId = util.antiSQLInspection(request.getParameter("userId")); 

			String id = util.getId(querystring);  	
			IErpNhapkho lsxBean = new ErpNhapkho(id);
			lsxBean.setUserId(userId); 

			String nextJSP = "";

			lsxBean.init();
			session.setAttribute("khoXuatId", lsxBean.getKhoNhapId());
			if(!querystring.contains("display"))
				nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoUpdate.jsp";
			else
				nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoDisplay.jsp";

			session.setAttribute("lsxBean", lsxBean);
			response.sendRedirect(nextJSP);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		/*
		 * if(!csdr.__validate_post()) {
		 * response.sendRedirect(request.getContextPath() + "/redirect.jsp"); return; }
		 */

		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			session.setMaxInactiveInterval(30000);

		//	this.out = response.getWriter();
			IErpNhapkho lsxBean;

			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
			if(id == null)
			{  	
				lsxBean = new ErpNhapkho("");
			}
			else
			{
				lsxBean = new ErpNhapkho(id);
			}

			lsxBean.setUserId(userId);

			String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
			if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
				ngayyeucau = getDateTime();
			lsxBean.setNgayyeucau(ngayyeucau);

			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if(ghichu == null)
				ghichu = "";
			lsxBean.setGhichu(ghichu);

			String khonhapId = util.antiSQLInspection(request.getParameter("khonhapId"));
			if (khonhapId == null)
				khonhapId = "";				
			lsxBean.setKhoNhapId(khonhapId);


			String lydo = util.antiSQLInspection(request.getParameter("lydo"));
			if (lydo == null)
				lydo = "";				
			lsxBean.setLydo(lydo);



			String sochungtugoc = util.antiSQLInspection(request.getParameter("sochungtugoc"));
			if (sochungtugoc == null)
				sochungtugoc = "";				
			lsxBean.setSochungtuGoc(sochungtugoc);


			String[] spMa = util.antiSQLInspection_Array(request.getParameterValues("spMa"));
			lsxBean.setSpMa(spMa);

			String[] spHsd =  util.antiSQLInspection_Array(request.getParameterValues("spHansd"));
			lsxBean.setSpHansudung(spHsd);

			String[] spTen =  util.antiSQLInspection_Array(request.getParameterValues("spTen"));
			lsxBean.setSpTen(spTen);

			String[] dvt =  util.antiSQLInspection_Array(request.getParameterValues("donvi"));
			lsxBean.setSpDonvi(dvt);

			String[] soluong =  util.antiSQLInspection_Array(request.getParameterValues("soluong"));
			lsxBean.setSpSoluong(soluong);

			if(spMa != null)
			{
				Hashtable<String, String> sp_chitiet = new Hashtable<String, String>();
				for(int i = 0; i < spMa.length; i++)
				{
					if(soluong[i].trim().length() > 0)
					{
						String[] soluongCT = util.antiSQLInspection_Array(request.getParameterValues("dong" + i + "_spSOLUONG"));
						String[] solo = util.antiSQLInspection_Array(request.getParameterValues("dong" + i + "_spSOLO"));	
						String[] ngaysanxuat = util.antiSQLInspection_Array(request.getParameterValues("dong" + i + "_spNGAYSANXUAT")); 	
						String[] ngayhethan = util.antiSQLInspection_Array(request.getParameterValues("dong" + i + "_spNGAYHETHAN")); 	

						for(int j = 0; j < solo.length; j++)
						{
							if(ngaysanxuat[j].trim().length() <= 0)
								ngaysanxuat[j] = getDateTime();

							if(soluongCT[j].trim().length() > 0 && solo[j].trim().length() > 0  )
							{
								String ct = sp_chitiet.get(spMa[i].trim());
								if(ct == null)
									ct = "";

								if(ct.trim().length() <= 0)
									sp_chitiet.put(spMa[i], soluongCT[j].trim() + "__" + solo[j].trim() + "__" + ngaysanxuat[j].trim() + "__" + ngayhethan[j].trim() + "___");
								else
								{
									ct += soluongCT[j].trim() + "__" + solo[j].trim() + "__" + ngaysanxuat[j].trim() + "__" + ngayhethan[j].trim() + "___";
									sp_chitiet.put(spMa[i], ct);
								}
							}
						}
					}
				}
				lsxBean.setSp_Chitiet(sp_chitiet);
			}


			String action = request.getParameter("action");
			String contentType = request.getContentType();

			if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
			{
				// ---- CHỖ NÀY LẤY DỮ LIỆU TỪ JSP NÈ -----//
				System.out.println("vao upload nhap kho ");
				MultipartRequest multi = new MultipartRequest(request, UPLOAD_DIRECTORY, 20000000, "UTF-8");
				/*userId = util.antiSQLInspection(multi.getParameter("userId"));
				obj.setId(userId);*/
				if(!csdr.__validate_post_mul(multi))
				{
					System.out.println("contentType loi : "+ contentType);
					response.sendRedirect(request.getContextPath() + "/redirect.jsp");
					return;
				}
				IUpload dmhBean = new Upload();
				id= util.antiSQLInspection(multi.getParameter("id"));
				userId = util.antiSQLInspection(multi.getParameter("userId"));
				lsxBean.setUserId(userId);
				String ngaydonhang = util.antiSQLInspection(multi.getParameter("ngaychuyen"));
				lsxBean.setNgayyeucau(ngaydonhang);

				ghichu = util.antiSQLInspection(multi.getParameter("ghichu"));
				lsxBean.setGhichu(ghichu);

				khonhapId = util.antiSQLInspection(multi.getParameter("khonhapId"));
				if (khonhapId == null)
					khonhapId = "";				
				lsxBean.setKhoNhapId(khonhapId);

				Enumeration files = multi.getFileNames();
				String filename = "";
				System.out.println("__userId" + userId);
				while (files.hasMoreElements())
				{
					String name = (String) files.nextElement();
					filename = multi.getFilesystemName(name);
					System.out.println("File  " + UPLOAD_DIRECTORY + filename);
				}

				if (filename == null)
				{
					lsxBean.setMsg("Vui lòng chọn file ");
				} 

				if (filename != null && filename.length() > 0)
				{
					String msg=this.readExcel(UPLOAD_DIRECTORY + filename, dmhBean,lsxBean,id);
					if(msg.trim().length()==0)
					{

						IErpNhapkhoList obj = new ErpNhapkhoList();
						obj.setUserId(userId);
						obj.init("");  
						session.setAttribute("obj", obj);  	
						session.setAttribute("userId", userId);
						response.sendRedirect(request.getContextPath() + "/pages/Center/ErpNhapKho.jsp");
						return;
					}

					else
					{
						System.out.println(" vao else loi upload  ");
						IErpNhapkho lsxBean1 = new ErpNhapkho();
						lsxBean1.setUserId(userId);
						lsxBean1.createRs();
						lsxBean1.setMsg(msg);
						session.setAttribute("lsxBean", lsxBean1);

						String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoNew.jsp";
						response.sendRedirect(nextJSP);
						return;
					}

				}


			}
			  if(!csdr.__validate_post())
				{
					response.sendRedirect(request.getContextPath() + "/redirect.jsp");
					return;
				}

			if(action.equals("save"))
			{	
				if(id == null)
				{
					if(!lsxBean.createNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoNew.jsp";
						response.sendRedirect(nextJSP);
					}
					else
					{
						IErpNhapkhoList obj = new ErpNhapkhoList();
						obj.setUserId(userId);
						obj.init("");  
						session.setAttribute("obj", obj);  	
						session.setAttribute("userId", userId);

						response.sendRedirect(request.getContextPath() + "/pages/Center/ErpNhapKho.jsp");
					}
				}
				else
				{
					if(!lsxBean.updateNK())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoUpdate.jsp";
						response.sendRedirect(nextJSP);
					}
					else
					{
						IErpNhapkhoList obj = new ErpNhapkhoList();
						obj.setUserId(userId);
						obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKho.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}else if(action.equals("download"))
		    {
		    	try{
	    		response.setContentType("application/xls");
	    		response.setHeader("Content-Disposition", "attachment; filename=Template(tt).xls");
	    		OutputStream out1 = response.getOutputStream();
			//	String query = setQuery(obj1,request);
				ExportToExcel_1(out1,lsxBean,id);
				
		    	}
		    	catch (Exception e) {
					System.out.println("Không thể xuất excel " + e.getMessage());
					e.printStackTrace();


					lsxBean.createRs();

					session.setAttribute("lsxBean", lsxBean);
					session.setAttribute("khoXuatId", khonhapId);
					
					String nextJSP = "";

					nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoNew.jsp";
					if(id != null)
						nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoUpdate.jsp";

					response.sendRedirect(nextJSP);
				
		    	
				}
		    }
			else
			{
				lsxBean.createRs();

				session.setAttribute("lsxBean", lsxBean);
				session.setAttribute("khoXuatId", khonhapId);
				
				String nextJSP = "";

				nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoNew.jsp";
				if(id != null)
					nextJSP = request.getContextPath() + "/pages/Center/ErpNhapKhoUpdate.jsp";

				response.sendRedirect(nextJSP);
			}
		}
	}
	private void ExportToExcel_1(OutputStream out,IErpNhapkho obj,String id)throws Exception
	 {
		try{ 
			String chuoi=getServletContext().getInitParameter("path") + "\\TemPlates_NhapKho.xls";
			
			FileInputStream fstream = new FileInputStream(chuoi);
			
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			if(id!=null) {
 			FillData(workbook,obj,id);
			}
			workbook.save(out);	
			fstream.close();
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		
	}
	private void FillData(Workbook workbook,IErpNhapkho obj,String id)throws Exception
	{
		try{
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			//worksheet.setGridlinesVisible(false);
			com.aspose.cells.Cells cells = worksheet.getCells();
			dbutils db = new dbutils();
			String query="";

 				query="\n select npp.pk_seq as manpp,npp.ten as tennpp,sp.MA, "+
 						"\n 	sp.ten,a.soluong,a.solo,ngayhethan,ngaysanxuat from ERP_NHAPKHO_SANPHAM a inner join erp_nhapkho b   "+
 						"\n 	on a.nhapkho_fk=b.pk_seq   "+
 						"\n 	inner join sanpham sp on sp.pk_seq=a.sanpham_fk  "+
 						"\n 	inner join nhaphanphoi npp on npp.pk_seq=1   where b.pk_seq="+id+"";
			 
			ResultSet rs = db.get(query);
			com.aspose.cells.Cell  cell = null;
			int countRow = 4;
			int stt=1;
			if(rs!=null){
			while(rs.next()){
				cell = (com.aspose.cells.Cell) cells.getCell("A" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(stt);
				cell = (com.aspose.cells.Cell) cells.getCell("B" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("manpp"));
				cell = (com.aspose.cells.Cell) cells.getCell("C" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("tennpp"));
				cell = (com.aspose.cells.Cell) cells.getCell("D" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("ma"));
				cell = (com.aspose.cells.Cell) cells.getCell("E" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("ten"));
				cell = (com.aspose.cells.Cell) cells.getCell("F" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getDouble("SOLUONG"));
				cell = (com.aspose.cells.Cell) cells.getCell("G" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("solo"));
				cell = (com.aspose.cells.Cell) cells.getCell("H" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("ngayhethan"));
				cell = (com.aspose.cells.Cell) cells.getCell("I" + String.valueOf(countRow));		((com.aspose.cells.Cell) cell).setValue(rs.getString("ngaysanxuat"));

				
				stt++;
				++countRow;
			}
			}
			if(rs!=null)rs.close();
			if(db!=null){
				db.shutDown();
			}
			//ReportAPI.setHidden(workbook,28);
	
		}catch(Exception ex){
			
			System.out.println("rrrorr : "+ex.toString());
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}


	private String readExcel(String fileName, IUpload dhBean,IErpNhapkho lsxbean,String id)
	{
		File inputWorkbook = new File(fileName);
		jxl.Workbook w = null;
		dbutils db = new dbutils();
		try
		{
			System.out.println("--user 4 :"  +dhBean.getUserId());
			w = jxl.Workbook.getWorkbook(inputWorkbook);

			int sosheet = w.getNumberOfSheets();
			String query="";
			db.getConnection().setAutoCommit(false);
			query="delete from tempt_nhapkho";
			if(!db.update(query))
			{

				db.getConnection().rollback();
				return "error "+query;
			}

			for (int ii = 0; ii < sosheet; ii++)
			{
				jxl.Sheet sheet = w.getSheet(ii);
				int sodong = sheet.getRows();
				int socot = sheet.getColumns();
				int soNpp = 1;
				System.out.println("[SoSheet]" + sosheet + "[So dong ]" + sodong + "[socot]" + socot + "[soNpp]" + soNpp);
				for (int i = 3; i < sodong; i++)
				{
					Cell[] cells = sheet.getRow(i);
					if(cells !=null){
						if(cells.length >= 1 && cells[1] != null  ){
							String nppMa = getValue(sheet, 1, i);
							String spMa = getValue(sheet, 3, i);
							String spTen = getValue(sheet, 4, i);

							String soluong = getValue(sheet, 5, i);
							String solo = getValue(sheet, 6, i);
							String ngayhethan = getValue(sheet,7, i);
							String ngaysanxuat = getValue(sheet, 8, i);
							float tondau= 0; 

							float soluongle =0;
							try{
								soluongle=Float.parseFloat(soluong.trim().replace(",", ""));
							}
							catch(Exception er)
							{

							}
							if(soluongle<0)
							{
								String msg=" sản phẩm "+spMa +" đang nhập số lượng < 0  vui lòng điều chỉnh lại";
								db.getConnection().rollback();
								return msg;
							}


							if(spMa.length()>0  && soluong.length()>0 && solo.length()>0 && ngayhethan.length()>0 && ngaysanxuat.length()>0  )
							{

								String dateFormat = "yyyy-MM-dd";
								if(!isValidDate( ngayhethan,dateFormat) || !isValidDate(ngaysanxuat,dateFormat) )
								{
									String msg="ngày hết hạn hoặc ngày sản xuất không hợp lệ vui lòng chỉnh sửa lại tại sản phẩm "+spMa +" ngày hết hạn "+ngayhethan +" ngày sản xuất "+ngaysanxuat;
									db.getConnection().rollback();
									return msg;
								}

								query="insert into tempt_nhapkho (manpp,masanpham,kho_fk,soluong,solo,ngayhethan,ngaysanxuat) values (N'"+nppMa+"',N'"+spMa+"','1','"+soluongle+"',N'"+solo+"','"+ngayhethan+"','"+ngaysanxuat+"') ";
								if(db.updateReturnInt(query)<=0)
								{

									db.getConnection().rollback();
									return "error "+query;
								}
							}


						} 
					}
				}

			}

			int flag=0;
			String text="";


			query=" select COUNT(masanpham) as sl,masanpham from tempt_nhapkho group by masanpham,solo,soluong,ngayhethan,ngaysanxuat";
			ResultSet rscheck=db.get(query);
			while (rscheck.next())
			{
				if(rscheck.getInt("sl")>1)
				{
					flag=1;
					text+=" sản phẩm "+rscheck.getString("masanpham") +" đang bị trùng nhau vui lòng điều chỉnh lại";
				}
			}
			rscheck.close();
			if(flag==1)
			{
				db.getConnection().rollback();
				return text;
			}


			query=" select kho.masanpham from tempt_nhapkho kho  where kho.masanpham not in (select Ma from sanpham ) ";
			rscheck=db.get(query);
			while (rscheck.next())
			{

				flag=1;
				text+=" sản phẩm "+rscheck.getString("masanpham") +" ";

			}
			rscheck.close();
			if(flag==1)
			{
				db.getConnection().rollback();
				return text +" không tồn tại trong sản phẩm";
			}



            System.out.println("check id:"+id);
            if(id==null ||  id.length()==0) {

			query = " insert ERP_NHAPKHO(ngaynhap, ghichu, trangthai, khonhap_fk, ngaytao, nguoitao, ngaysua, nguoisua,import) " +
			" values('" + lsxbean.getNgayyeucau() + "', N'" + lsxbean.getGhichu() + "', '0', " + lsxbean.getKhoNhapId() + ", '" + getDateTime() + "', '" + lsxbean.getUserId() + "', '" + getDateTime() + "', '" + lsxbean.getUserId() + "',1 )";

			System.out.println("1.Insert NK: " + query);
			if(db.updateReturnInt(query)<=0)
			{

				db.getConnection().rollback();
				return "error "+query;
			}

			query = "select SCOPE_IDENTITY() as btId";
			ResultSet rsBtId = db.get(query);			
			rsBtId.next();
		    String nhapkhoCurrentId = rsBtId.getString("btId");
		    rsBtId.close();
		    
		    int sonetInt =  Utility.getChungTuSonet( db,lsxbean.getUserId(),"1","PhieuNhap_SoChungTu","month('"+lsxbean.getNgayyeucau() +"')","year('"+lsxbean.getNgayyeucau() +"')",nhapkhoCurrentId,"ERP_NHAPKHO" );
		    if(sonetInt <=0)
		    {
		    	db.getConnection().rollback();
		    	return "Không thể lấy số chứng từ tự động";

		    }
		    
		    query = Utility.UpdateChungTuSoNet( "PhieuNhap_SoChungTu","1", nhapkhoCurrentId, lsxbean.getNgayyeucau(),  sonetInt,  "ERP_NHAPKHO", "PN-"  );
		    if(db.updateReturnInt(query)<=0)
			{
				db.getConnection().rollback();
				return"Số chứng từ ("+sonetInt+") đã phát sinh trong đơn gần nhất, vui lòng thử lại!";

			}
		    query = "\n update ERP_NHAPKHO set sonetId ='PN-' + dbo.[PlusZero]("+sonetInt+",5)  where pk_seq =   "+ nhapkhoCurrentId;
		    if( db.updateReturnInt(query) <= 0 )
			{
		    	db.getConnection().rollback();
				return "Không thể tạo mới ERP_YCXUATKHO " + query;
				
			}
		    
		    
		    
			
			query = "insert ERP_NHAPKHO_SANPHAM( nhapkho_fk, SANPHAM_FK, DVDL_FK, soluong, gianhap, solo, ngaysanxuat, ngayhethan,ngaynhapkho ) " +
			"select "+nhapkhoCurrentId+", (select pk_Seq from sanpham a where a.ma=masanpham),(select dvdl_fk from sanpham a where a.ma=masanpham) , soluong, '0', solo, ngaysanxuat,ngayhethan,'"+lsxbean.getNgayyeucau()+"'  " +
			"from tempt_nhapkho  ";

			System.out.println("1.Insert NK - SP: " + query);
			if(db.updateReturnInt(query)<=0)
			{	
				db.getConnection().rollback();
				return "Khong the tao moi ERP_NHAPKHO_SANPHAM: " + query;
			}
			query="select count(*) sl from ERP_NHAPKHO_SANPHAM where nhapkho_fk="+nhapkhoCurrentId +" and (ngayhethan is null or ngaynhapkho is null) ";
			ResultSet rscsl=db.get(query);
			rscsl.next();
			if(rscsl.getInt("sl")>0)
			{
				db.getConnection().rollback();
				rscsl.close();
				return "ngày hết hạn và ngày nhập kho  chưa có giá trị" ;
			}
			rscsl.close();
            }else {
            	   query = "\n delete ERP_NHAPKHO_SANPHAM  where nhapkho_fk="+id+"";
            	   System.out.println(query);
       		    if( db.updateReturnInt(query) <= 0 )
       			{
       		    	db.getConnection().rollback();
       				return "Không thể tạo mới ERP_YCXUATKHO " + query;
       				
       			}
       		    
            	query = "insert ERP_NHAPKHO_SANPHAM( nhapkho_fk, SANPHAM_FK, DVDL_FK, soluong, gianhap, solo, ngaysanxuat, ngayhethan,ngaynhapkho ) " +
            			"select "+id+", (select pk_Seq from sanpham a where a.ma=masanpham),(select dvdl_fk from sanpham a where a.ma=masanpham) , soluong, '0', solo, ngaysanxuat,ngayhethan,'"+lsxbean.getNgayyeucau()+"'  " +
            			"from tempt_nhapkho  ";

            			System.out.println("1.Insert NK - SP: " + query);
            			if(db.updateReturnInt(query)<=0)
            			{	
            				db.getConnection().rollback();
            				return "Khong the tao moi ERP_NHAPKHO_SANPHAM: " + query;
            			}
            			query="select count(*) sl from ERP_NHAPKHO_SANPHAM where nhapkho_fk="+id+" and (ngayhethan is null or ngaynhapkho is null) ";
            			ResultSet rscsl=db.get(query);
            			rscsl.next();
            			if(rscsl.getInt("sl")>0)
            			{
            				db.getConnection().rollback();
            				rscsl.close();
            				return "ngày hết hạn và ngày nhập kho  chưa có giá trị" ;
            			}
            			rscsl.close();
            }
		

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return dhBean.getMsg();
		}	
		catch (Exception e)
		{
			e.printStackTrace();
			return "Vui lòng coi lại định dạng file " + e.getMessage();
		}
	}

	private String getValue(Sheet sheet, int column, int row)
	{
		Cell cell;
		cell = sheet.getCell(column, row);
		try{
			return cell.getContents().replace("'", "");
		}catch(Exception er){
			return	"0";
		}
	}

	public static boolean isValidDate(String dateToValidate, String dateFormat)
	{


		if(dateToValidate == null)
		{
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try 
		{
			//If not valid, it will to throw ParseException.
			//Trường hợp không hợp lệ, nó sẽ chuyển sang xử lý ngoại lệ.
			Date date = sdf.parse(dateToValidate);
			//System.out.println(date);

		} 
		catch (ParseException e) 
		{        
			//e.printStackTrace();
			return false;
		}

		return true;
	}


}
