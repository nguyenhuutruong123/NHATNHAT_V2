package geso.dms.center.servlets.PhanBoKMApGame;

import geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGame;
import geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGameList;
import geso.dms.center.beans.PhanBoKMApGame.imp.PhanBoKMApGame;
import geso.dms.center.beans.PhanBoKMApGame.imp.PhanBoKMApGameList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Sheet;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.oreilly.servlet.MultipartRequest;

public class PhanBoKMApGameUpdateSvl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "c:\\upload\\excel\\";

    public PhanBoKMApGameUpdateSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 

		HttpSession session = request.getSession();	    
		IPhanBoKMApGame obj = new PhanBoKMApGame();

		Utility util = new Utility(); 

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);  
		if (userId.length()==0)
			userId = request.getParameter("userId");

		String action = util.getAction(querystring); 

		String ctkmId = util.getId(querystring);

		obj.setCtkmId(ctkmId);
		obj.setUserId(userId);
		obj.init();
		
		String nextJSP = "";
		//if (action.equals("update"))
			nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGameUpdate.jsp";
		/*else 
			nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGameDisplay.jsp";*/
		
		session.setAttribute("ctkmBean", obj); 
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/xlsm");
		response.setHeader("Content-Disposition", "attachment; filename=KhuyenMaiApGame.xls");
		OutputStream out = response.getOutputStream(); 
		String userId =request.getParameter("userId");
		String action = request.getParameter("action");
		Utility uti = new Utility();
		
		PhanBoKMApGame obj = new PhanBoKMApGame();
		String contentType = request.getContentType();
		
		System.out.println(":::contentType: "+contentType);
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			MultipartRequest multi = new MultipartRequest(request, UPLOAD_DIRECTORY, 20000000, "UTF-8");
			Enumeration files = multi.getFileNames();
			String filename = "";
			String filename1 = "";
			String dulieu="";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filename1 = multi.getFilesystemName(name); 
				if(filename1 != null && filename1.trim().length()>0)
				{
					filename=filename1;
				}
				System.out.println(":::filename loop: "+filename);
			}
		
			System.out.println(":::filename: "+filename);
			
			if (filename != null && filename.length() > 0)
			{
				dulieu=this.readExcel(UPLOAD_DIRECTORY+filename, obj);
				
				System.out.println(":::dulieu: "+dulieu);
				
				obj.init();
				obj.setMsg(dulieu);
		    	session.setAttribute("ctkmBean", obj); 		
		        String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGameUpdate.jsp";
		    	response.sendRedirect(nextJSP); 
				
			}
				
		}
		else { 
			String[] vungId=request.getParameterValues("vungId");
			if(vungId != null)
			{
				for(int i=0;i<vungId.length;i++)
				{
					if(vungId[i] !=null)
					{
						if(vungId[i].trim().length()>0)
						{
							obj.setVungId(vungId[i]);
						}
					}
				}
			}
			
			String[] khuVucId=request.getParameterValues("khuVucId");
			if(khuVucId != null)
			{
				for(int i=0;i<khuVucId.length;i++)
				{
					if(khuVucId[i] != null)
					{
						if(khuVucId[i].trim().length()>0)
						{
							obj.setKhuvucId(khuVucId[i]);
						}
					}
				}
			} 
			
			String ctkmId=request.getParameter("ctkmId");
			if(ctkmId == null)
			{
				ctkmId="";
			} 
			obj.setCtkmId(ctkmId);
			String traKMExcelId=request.getParameter("traKMExcelId");
			if(traKMExcelId == null)
			{
				traKMExcelId="";
			}
			String scheme=request.getParameter("scheme");
			if(scheme == null)
			{
				scheme="";
			}
			obj.setScheme(scheme);
			String diengiai=request.getParameter("diengiai");
			if(diengiai == null)
			{
				diengiai="";
			}
			obj.setDiengiai(diengiai); 
			dbutils db = new dbutils();
			System.out.println("___CtkmId: "+ctkmId);
			String queryTraKM = "select trakhuyenmai_fk  from trakhuyenmai a inner join ctkm_trakm b on a.pk_Seq=b.trakhuyenmai_fk where ctkhuyenmai_fk ="+obj.getCtkmId();
			String dulieu="";
			Hashtable<String, String> hashTiLe =new Hashtable<String, String>();
			ResultSet rsTraKM = db.get(queryTraKM);
			try {
				if(rsTraKM != null)
				{
					while(rsTraKM.next())
					{
						String tile=request.getParameter("tile."+rsTraKM.getString("trakhuyenmai_fk"));
						hashTiLe.put(rsTraKM.getString("trakhuyenmai_fk"), tile);
						String[] nppIds=request.getParameterValues("nppIds."+rsTraKM.getString("trakhuyenmai_fk"));
						String[] soluong=request.getParameterValues("soluong."+rsTraKM.getString("trakhuyenmai_fk"));
						for(int i =0;i<nppIds.length;i++)
						{
							dulieu +="\n select  "+rsTraKM.getString("trakhuyenmai_fk")+" as trakmId,"+nppIds[i]+" as nppId, "+soluong[i]+" as soluong "+ 
									 "\n union all";
						}
					}
	
					rsTraKM.close();
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			obj.setHashTiLe(hashTiLe);
			dulieu = dulieu.substring(0, dulieu.length()-9);
			if(action.equals("locnpp"))
			{
				obj.init();
	    		session.setAttribute("ctkmBean", obj); 		
	        	String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGameUpdate.jsp";
	    		response.sendRedirect(nextJSP);
			}
			
			if(action.equals("xuatexcel"))
			{
				try { 
					String query = "select pk_seq, scheme from ctkhuyenmai where pk_Seq="+ctkmId;
					ResultSet rsCTKM = db.get(query); 
					if(rsCTKM != null)
					{
						if(rsCTKM.next())
						{
							scheme = rsCTKM.getString("scheme");
						}
						rsCTKM.close();
					}
					query = "select pk_seq, diengiai from trakhuyenmai where pk_Seq="+traKMExcelId;
					ResultSet rsTraKMExcel = db.get(query);
					String trakm="";
					if(rsTraKM != null)
					{
						if(rsTraKMExcel.next())
						{
							trakm = rsTraKMExcel.getString("diengiai");
						}
						rsTraKMExcel.close();
					}
					query = "select isnull(xacsuat,0) as xacsuat from ctkm_trakm where ctkhuyenmai_fk="+ctkmId+" and trakhuyenmai_fk="+traKMExcelId;
					ResultSet rsTile = db.get(query);
					String tile="";
					if(rsTile != null)
					{
						if(rsTile.next())
						{
							tile = rsTile.getString("xacsuat");
						}
						rsTile.close();
					}
					
					query = "select b.scheme,c.diengiai,d.ma,d.ten,isnull(a.sosuat,0) as sosuat,a.ctkm_fk,a.trakm_fk,a.npp_fk from phanbo_Ctkm_trakm_npp a "
							+ "\n inner join ctkhuyenmai b on a.ctkm_fk=b.pk_seq"
							+ "\n inner join trakhuyenmai c on c.pk_seq=a.trakm_fk inner join nhaphanphoi d on d.pk_Seq=a.npp_fk"
							+ "\n where ctkm_fk="+ctkmId+" and trakm_fk= "+traKMExcelId+
							"\n union"+ 
							"\n select  c.scheme,e.diengiai,a.ma,a.ten,0 as sosuat,b.ctkm_fk,d.trakhuyenmai_fk as trakm_fk,b.npp_fk " + 
							"\n  from nhaphanphoi a inner join ctkm_npp b on a.pk_seq=b.npp_fk " + 
							"\n inner join ctkhuyenmai c on c.pk_seq=b.ctkm_fk inner join ctkm_trakm d on d.ctkhuyenmai_fk=c.pk_seq" + 
							"\n inner join trakhuyenmai e on e.pk_seq=d.trakhuyenmai_fk " + 
							"\n  where a.trangthai=1 and b.ctkm_fk= "+ ctkmId+" and e.pk_seq="+traKMExcelId+" "+ 
							"\n and a.pk_Seq not in (select npp_fk from phanbo_ctkm_trakm_npp where ctkm_fk="+ctkmId+" and trakm_fk="+traKMExcelId+")";;
					System.out.println("____Baocao: "+query);
					ResultSet rsExcel = db.get(query);
					FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path")+"\\KhuyenMaiApGame.xls" );
		    		System.out.println("____path: "+getServletContext().getInitParameter("path"));
		    		Cell cell= null;
		    		Workbook workbook = new Workbook(); 
		    		workbook.open(fstream);
		    		workbook.setFileFormatType(FileFormatType.EXCEL2003);
		    		Worksheets worksheets = workbook.getWorksheets();
		    		Worksheet worksheet = worksheets.getSheet(0); 
		    		//worksheet.setName("BCDanhGia");
					Cells cells = worksheet.getCells();		 
				
					
					 
					cell = cells.getCell("B2");	cell.setValue(scheme);
					Style style = cell.getStyle();
					Font font1 = style.getFont();
					font1.setSize(20);
					font1.setColor(Color.RED);
					style.setFont(font1);
					font1.setColor(Color.BLUE);font1.setBold(true);font1.setSize(10);
					style.setFont(font1);cell.setStyle(style);
					cell = cells.getCell("B3");	cell.setValue(trakm);
					cell.setStyle(style);  
					cell = cells.getCell("B4");	cell.setValue(tile);
					cell.setStyle(style); 
					int dataIndex = 6;
					if(rsExcel != null)
					{
						while(rsExcel.next())
						{
							dataIndex++; 
							cell = cells.getCell("A" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("ctkm_fk")); CreateBorderSetting(worksheet, "A" + Integer.toString(dataIndex));
							cell = cells.getCell("B" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("scheme")); CreateBorderSetting(worksheet, "B" + Integer.toString(dataIndex));
							cell = cells.getCell("C" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("trakm_fk"));CreateBorderSetting(worksheet, "C" + Integer.toString(dataIndex));
							cell = cells.getCell("D" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("diengiai"));CreateBorderSetting(worksheet, "D" + Integer.toString(dataIndex));
							cell = cells.getCell("E" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("ma"));CreateBorderSetting(worksheet, "E" + Integer.toString(dataIndex));
							cell = cells.getCell("F" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("ten"));CreateBorderSetting(worksheet, "F" + Integer.toString(dataIndex));
							cell = cells.getCell("G" + Integer.toString(dataIndex));	cell.setValue(rsExcel.getString("sosuat"));CreateBorderSetting(worksheet, "G" + Integer.toString(dataIndex));
						}
						
					}
					db.shutDown();
		    		workbook.save(out);	
		    		fstream.close(); 
		    		out.close(); 
		    		obj.init(); 
					session.setAttribute("ctkmBean", obj); 		
		        	String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGameUpdate.jsp";
		        	response.sendRedirect(nextJSP);
				}catch (Exception e) {
					e.printStackTrace();
				}
			} 
			if(action.equals("save"))
			{
				if(obj.save(dulieu))
				{
					IPhanBoKMApGameList list = new PhanBoKMApGameList();
					list.setUserId(userId);
					list.init("");
					session.setAttribute("obj", list); 		
		        	String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGame.jsp";
		    		response.sendRedirect(nextJSP);
				}
				else {
					obj.createRs();
					session.setAttribute("ctkmBean", obj); 		
		        	String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGameUpdate.jsp";
		    		response.sendRedirect(nextJSP);
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
	
	public void CreateBorderSetting(Worksheet worksheet,String fileName) throws IOException
    {
        Cells cells = worksheet.getCells();
        Cell cell;
        Style style;

        cell = cells.getCell(fileName);
        style = cell.getStyle();

        //Set border color
        style.setBorderColor(BorderType.TOP, Color.BLACK);
        style.setBorderColor(BorderType.BOTTOM, Color.BLACK);
        style.setBorderColor(BorderType.LEFT, Color.BLACK);
        style.setBorderColor(BorderType.RIGHT, Color.BLACK); 
        style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN) ;
        cell.setStyle(style);

       
    } 
	private String readExcel(String fileName, IPhanBoKMApGame obj)
	{
		dbutils db = new dbutils();
		try
		{
			String msg="";
			File inputWorkbook = new File(fileName);
			jxl.Workbook w = null;
			w = jxl.Workbook.getWorkbook(inputWorkbook);
			jxl.Sheet sheet = w.getSheet(0);
			int sodong = sheet.getRows();
			/*db.getConnection().setAutoCommit(false);*/
			String dulieu="";
			String schemeId="";
			String trakmId="";
			
			for (int i = 6; i < sodong; i++)
			{
				jxl.Cell[] cells = sheet.getRow(i);
				if (cells != null)
				{
					
					schemeId = getValue(sheet, 0, i).trim();
					if(schemeId.trim().length()<=0)
					{
						break;
					}
					else {
						obj.setCtkmId(schemeId); 
					}
					trakmId = getValue(sheet, 2, i).trim();
					String maNpp = getValue(sheet, 4, i).trim();
					String soluong=getValue(sheet, 6, i).trim();
					dulieu += " select "+schemeId+" as ctkmId, "+trakmId+" as trakmId, '"+maNpp+"' as manpp, "+soluong+" as soluong union all";
					
				}
			}
			
			dulieu = dulieu.substring(0,dulieu.length()-9);
			dulieu = "select data.ctkmId,data.trakmId,npp.pk_seq as nppId, data.soluong from ("+dulieu+") as data inner join nhaphanphoi npp on data.manpp=npp.ma";
			System.out.println(":::dulieu excel 2: "+dulieu);
			Hashtable<String, String> tile = new Hashtable<String, String>();
			tile.put(trakmId, getValue(sheet, 1, 3).trim());
			obj.setHashTiLe(tile);
			if(obj.save(dulieu))
			{
				return "Upload phân bổ khuyến mãi áp game thành công!";
			}
			else {
				//return "Có lỗi xảy ra trong quá lưu dữ liệu. Vui lòng thao tác lại";
				return obj.getMsg();
			}
			/*db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();*/
		} catch (Exception e)
		{
			e.printStackTrace();
			/*try {
				db.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			db.shutDown();
			return "Vui lòng coi lại file " + e.getMessage();*/
			return "Có lỗi xảy ra trong quá trình upload. Vui lòng thao tác lại";
		} 
	}
	private String getValue(Sheet sheet, int column, int row)
	{
		jxl.Cell cell;
		cell = sheet.getCell(column, row);
		try
		{
			return cell.getContents();
		} catch (Exception er)
		{
			return "0";
		}
	}

}
