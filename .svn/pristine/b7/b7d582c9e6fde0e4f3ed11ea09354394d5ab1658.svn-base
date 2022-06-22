package geso.dms.center.servlets.reports;

import geso.dms.center.beans.baocao.IBCKhuyenMaiApGame;
import geso.dms.center.beans.baocao.imp.BCKhuyenMaiApGame;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.BorderType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.VerticalAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

@WebServlet("/BCKhuyenMaiApGameSvl")
public class BCKhuyenMaiApGameSvl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public BCKhuyenMaiApGameSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
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
		
		IBCKhuyenMaiApGame obj = new BCKhuyenMaiApGame();
		
		obj.setUserId(userId); 
		obj.setUserTen(userTen);
		obj.init();
		
		session.setAttribute("obj", obj);
		String nextJSP = request.getContextPath() + "/pages/Center/BCKhuyenMaiApGame.jsp";
		response.sendRedirect(nextJSP);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		response.setContentType("application/xlsm");
		response.setHeader("Content-Disposition", "attachment; filename=BCKhuyenMaiApGame.xlsm");
		
		String nextJSP = "";
		OutputStream out = response.getOutputStream();
		Utility util = new Utility();
		 
		String nppId = util.antiSQLInspection(request.getParameter("nppId"))!=null?util.antiSQLInspection(request.getParameter("nppId")):"";
		String tungay = util.antiSQLInspection(request.getParameter("tungay"));
		String denngay = util.antiSQLInspection(request.getParameter("denngay")); 
		String userId = util.antiSQLInspection(request.getParameter("userId"));  
		String userTen = util.antiSQLInspection(request.getParameter("userTen"));  
		String ddkdId= util.antiSQLInspection(request.getParameter("ddkdId"))!=null?util.antiSQLInspection(request.getParameter("ddkdId")):""; 
		
		IBCKhuyenMaiApGame baocao = new BCKhuyenMaiApGame();
		
		baocao.setUserId(userId);
		baocao.setUserTen(userTen);
		baocao.setTungay(tungay);
		baocao.setDenngay(denngay);
		baocao.setDdkdId(ddkdId);
		baocao.setNppId(nppId);
		
		String action = request.getParameter("action");
		System.out.println(":::Action: "+action);
		
		boolean flag = true;  
		if(tungay.trim().length() <=0 || denngay.trim().length() <=0)
		{
			flag=false;
			baocao.setMsg("Vui lòng nhập từ ngày, đến ngày lấy báo cáo");
		}
		
		String query="";
		if(action.equals("taobaocao") && flag) {
			
			query ="select g.ten as vung, f.ten as khuvuc, e.ten as tennpp,d.smartid as maddkd,d.ten as tenddkd, \n" + 
				"b.scheme,b.diengiai as tenctkm,c.diengiai as dktra,a.sosuat as phanbo,isnull(sudung.dasudung,0) as dasudung \n" + 
				"from phanbo_ctkm_trakm_ddkd a inner join ctkhuyenmai b on a.ctkm_fk=b.pk_seq \n" + 
				"inner join trakhuyenmai c on a.trakm_fk=c.pk_seq inner join daidienkinhdoanh d on d.pk_seq=a.ddkd_fk \n" + 
				"inner join nhaphanphoi e on a.npp_fk =e.pk_seq \n" + 
				"inner join khuvuc f on e.khuvuc_fk=f.pk_seq \n" + 
				"inner join vung g on g.pk_seq=f.vung_fk \n" + 
				"left join ("+ 
					"select a.ddkd_fk,b.ctkmId,b.trakmid,sum(b.soxuat) as dasudung " + 
					"from donhang a inner join donhang_ctkm_trakm b  on a.pk_seq=b.donhangid where a.trangthai != 2 \n" ;
			
			if(baocao.getTungay().trim().length()>0)
			{
		 		query += " and a.ngaynhap >= '"+baocao.getTungay()+"' ";
		 	}
			if(baocao.getDenngay().trim().length()>0)
			{
				query += " and a.ngaynhap <= '"+baocao.getDenngay()+"' ";
			}
			if(baocao.getUserId().trim().length()>0)
			{
				query += " and a.npp_fk in (select npp_fk from phamvihoatdong where nhanvien_fk = "+baocao.getUserId()+") ";
			}
			
			query +=" group by a.ddkd_fk,b.ctkmId,b.trakmid) as sudung on sudung.ddkd_fk=d.pk_seq and sudung.ctkmId=b.pk_seq \n" + 
					" and sudung.trakmid=c.pk_seq where 1=1 ";
			
			if(baocao.getNppId().trim().length()>0)
			{
				query +=" and e.pk_seq="+baocao.getNppId();
			}
			if(baocao.getDdkdId().trim().length()>0)
			{
				query +=" and d.pk_seq="+baocao.getDdkdId();
			}
			if(baocao.getTungay().trim().length()>0)
			{
				query +=" and b.tungay >='"+baocao.getTungay()+"' ";
			}
			if(baocao.getDenngay().trim().length()>0)
			{
				query +=" and b.tungay <='"+baocao.getDenngay()+"' ";
			}
			if(baocao.getUserId().trim().length()>0)
			{
				query += " and a.npp_fk in (select npp_fk from phamvihoatdong where nhanvien_fk = "+baocao.getUserId()+") ";
			}
			
			System.out.println(":::BC KM app game: "+query);
			dbutils db = new dbutils();
			ResultSet result = db.get(query); 
			int dataIndex = 6;
			try
			{
		    	if(result != null) {
		    		
		    		FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path")+"\\BCKhuyenMaiApGame.xlsm");
		    		System.out.println(":::path: "+getServletContext().getInitParameter("path"));
		    		Cell cell= null;
		    		Workbook workbook = new Workbook(); 
		    		workbook.open(fstream);
		    		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		    		Worksheets worksheets = workbook.getWorksheets();
		    		Worksheet worksheet = worksheets.getSheet(0); 
		    		 
					Cells cells = worksheet.getCells();		
					// Thiết lập width cho từng column
					 
					/*cells.setColumnWidth(0,20.0f); 
					cells.setColumnWidth(1, 20.0f);
					cells.setColumnWidth(2, 15.0f);
					cells.setColumnWidth(3, 15.0f);
					cells.setColumnWidth(4, 20.0f);			
					cells.setColumnWidth(5, 30.0f);	
					cells.setColumnWidth(6, 20.0f);	
					cells.setColumnWidth(7, 20.0f);	*/ 
					
					cell = cells.getCell("A1");	cell.setValue("Báo Cáo Khuyến Mãi Áp Game");
					Style style = cell.getStyle();
					Font font1 = style.getFont();
					font1.setSize(20);
					font1.setColor(Color.RED);
					style.setFont(font1);
					cell.setStyle(style);
					cell = cells.getCell("A2");	cell.setValue("Từ ngày: "+baocao.getTungay());
					font1.setColor(Color.BLUE);font1.setBold(true);font1.setSize(10);
					style.setFont(font1);cell.setStyle(style);
					cell = cells.getCell("A3");	cell.setValue("Đến ngày: "+baocao.getDenngay());
					cell.setStyle(style);
					cell = cells.getCell("C2");	cell.setValue("Ngày tạo: "+getDateTime());
					cell.setStyle(style);
					cell = cells.getCell("C3");	cell.setValue("Người tạo: "+baocao.getUserTen());
					cell.setStyle(style);
					//header
					style = cell.getStyle();
					font1.setColor(Color.BLACK);
					style.setFont(font1);
					style.setColor(Color.TEAL);
					style.setVAlignment(VerticalAlignmentType.CENTRED);
					style.setHAlignment(TextAlignmentType.CENTER);
					style.setBorderColor(BorderType.TOP, Color.BLACK);
			        style.setBorderColor(BorderType.BOTTOM, Color.BLACK);
			        style.setBorderColor(BorderType.LEFT, Color.BLACK);
			        style.setBorderColor(BorderType.RIGHT, Color.BLACK); 
			        style.setBorderLine(BorderType.TOP, BorderLineType.THIN);
			        style.setBorderLine(BorderType.BOTTOM, BorderLineType.THIN);
			        style.setBorderLine(BorderType.LEFT, BorderLineType.THIN);
			        style.setBorderLine(BorderType.RIGHT, BorderLineType.THIN) ;
		    		while(result.next()) {
						dataIndex++;
						cell = cells.getCell("A" + Integer.toString(dataIndex));	cell.setValue(result.getString("vung")); CreateBorderSetting(worksheet, "A" + Integer.toString(dataIndex));
						cell = cells.getCell("B" + Integer.toString(dataIndex));	cell.setValue(result.getString("khuvuc"));CreateBorderSetting(worksheet, "B" + Integer.toString(dataIndex));
						cell = cells.getCell("C" + Integer.toString(dataIndex));	cell.setValue(result.getString("tennpp"));CreateBorderSetting(worksheet, "C" + Integer.toString(dataIndex));
						cell = cells.getCell("D" + Integer.toString(dataIndex));	cell.setValue(result.getString("maddkd"));CreateBorderSetting(worksheet, "D" + Integer.toString(dataIndex));
						cell = cells.getCell("E" + Integer.toString(dataIndex));	cell.setValue(result.getString("tenddkd"));CreateBorderSetting(worksheet, "E" + Integer.toString(dataIndex));
						cell = cells.getCell("F" + Integer.toString(dataIndex));	cell.setValue(result.getString("scheme"));CreateBorderSetting(worksheet, "F" + Integer.toString(dataIndex));
						cell = cells.getCell("G" + Integer.toString(dataIndex));	cell.setValue(result.getString("tenctkm"));CreateBorderSetting(worksheet, "G" + Integer.toString(dataIndex));
						cell = cells.getCell("H" + Integer.toString(dataIndex));	cell.setValue(result.getString("dktra"));CreateBorderSetting(worksheet, "H" + Integer.toString(dataIndex));
						cell = cells.getCell("I" + Integer.toString(dataIndex));	cell.setValue(result.getDouble("phanbo"));CreateBorderSetting(worksheet, "I" + Integer.toString(dataIndex));
						cell = cells.getCell("J" + Integer.toString(dataIndex));	cell.setValue(result.getDouble("dasudung"));CreateBorderSetting(worksheet, "J" + Integer.toString(dataIndex));
						cell = cells.getCell("K" + Integer.toString(dataIndex));	cell.setValue(result.getDouble("phanbo")-result.getDouble("dasudung"));CreateBorderSetting(worksheet, "K" + Integer.toString(dataIndex));
						
		    		}
		    		db.shutDown();
		    		workbook.save(out);	
		    		fstream.close(); 
		    		out.close();
		    		
		    		baocao.init();
			    	session.setAttribute("obj", baocao);
					nextJSP = request.getContextPath() + "/pages/Center/BCKhuyenMaiApGame.jsp";
					response.sendRedirect(nextJSP);
			    }
		    	else {
		    		System.out.println(":::Thong bao: ResultSet-> row = 0");
		    		baocao.setMsg("Không tìm thấy dữ liệu để xuất báo cáo");
		    		baocao.init();
			    	session.setAttribute("obj", baocao);
					nextJSP = request.getContextPath() + "/pages/Center/BCKhuyenMaiApGame.jsp";
					response.sendRedirect(nextJSP);
		    	}
			} catch (SQLException e) {
					e.printStackTrace();
					baocao.init();
			    	session.setAttribute("obj", baocao);
					nextJSP = request.getContextPath() + "/pages/Center/BCKhuyenMaiApGame.jsp";
					response.sendRedirect(nextJSP);
			}    
		}
		else {
			
			baocao.init();
	    	session.setAttribute("obj", baocao);
			nextJSP = request.getContextPath() + "/pages/Center/BCKhuyenMaiApGame.jsp";
			response.sendRedirect(nextJSP);
		}
	} 
	
	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String formatDDMMYY(String str) {
		
		String result = "";
		
		if(!str.equals("")) {
			String[] temp = str.trim().split("-");
			if(temp.length == 3) {
				result = temp[2] + "/" + temp[1] + "/" + temp[0];
			}
		}
		
		return result;
	}
	
	public void CreateBorderSetting(Worksheet worksheet,String fileName) throws IOException {
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

}
