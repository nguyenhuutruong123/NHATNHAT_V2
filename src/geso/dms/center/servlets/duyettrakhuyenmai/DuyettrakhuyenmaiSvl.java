package geso.dms.center.servlets.duyettrakhuyenmai;

import geso.dms.center.beans.duyettrakhuyenmai.*;
import geso.dms.center.beans.duyettrakhuyenmai.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.report.Ireport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;

public class DuyettrakhuyenmaiSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out;
   
    public DuyettrakhuyenmaiSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
	    Utility util = new Utility();
	    out = response.getWriter();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    IDuyettrakhuyenmaiList obj = new DuyettrakhuyenmaiList();
	    obj.setUserId(userId);
	    
	    String action = util.getAction(querystring);
	    String ctskuId = util.getId(querystring);
	    
	    //System.out.println("___Action: " + action + " -- Id la: " + ctskuId);
	    String msg = "";
	    if(action.trim().equals("duyet"))
	    {
	    	msg =  Duyet( ctskuId, userId) ;
	    	
	    }
	    
	    if(action.trim().equals("delete"))
	    {
	    	XoaChiTieu(ctskuId);
	    }

	    obj.init("");
	    obj.setMsg(msg);
		session.setAttribute("obj", obj);
	    
	    String nextJSP = request.getContextPath() + "/pages/Center/DuyetTraKhuyenMai.jsp";
		response.sendRedirect(nextJSP);
	}

	
	private String Duyet(String ctskuId,String userId) 
	{
		dbutils db = new dbutils();
		String  query = "";
    	try 
    	{
			db.getConnection().setAutoCommit(false);
			
			
					query = "insert into CTKHUYENMAI (SCHEME,LOAICT,DIENGIAI,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA) " +
					 " select SCHEME,3 loaict,DIENGIAI,"+userId+","+userId+",'"+Utility.getNgayHienTai()+"','"+Utility.getNgayHienTai()+"' from TIEUCHITHUONGTL tc " +
					 "\n where PK_SEQ = (select CTKM_FK from DUYETTRAKHUYENMAI where PK_SEQ = " + ctskuId + ")	" +
					 "\n	and not exists ( select 1 from  CTKHUYENMAI x where x.SCHEME =  tc.SCHEME ) " +
					 "  ";
			if(db.updateReturnInt(query)< 0)
	    	{
	    		db.getConnection().rollback();
	    		db.shutDown();
				return "lỗi:"+ query ;
	    	}
			System.out.println("Query 1: "+query);
			
			query = "select pk_seq AS ctkmId from ctkhuyenmai where scheme = (select top 1 SCHEME from tieuchithuongtl where pk_seq =(select CTKM_FK from DUYETTRAKHUYENMAI where PK_SEQ = " + ctskuId + ") ) ";
			ResultSet rs=  db.get(query);
			rs.next();
			String ctkmId = rs.getString("ctkmId");

			query = "\n insert into CTKM_TRAKM (ctkhuyenmai_fk, trakhuyenmai_fk, pheptoan, thutu) " +
					"\n select '"+ ctkmId+ "', (select top 1 pk_seq from trakhuyenmai where hinhthuc = 99),"+ "'2'," + "'1'" +
					"\n where not exists (	select 1 from CTKM_TRAKM x where x.ctkhuyenmai_fk = "+ ctkmId+ " and  x.trakhuyenmai_fk = (select top 1 pk_seq from trakhuyenmai where hinhthuc = 99) 	)	";
			System.out.println("Query 2: "+query);
			if(db.updateReturnInt(query)<  0)
			{
				db.getConnection().rollback();
	    		db.shutDown();
				return "lỗi:"+ query ;
			}
			

			query = " update Duyettrakhuyenmai set ctkhuyenmai_fk = "+ctkmId+", trangthai = '1' where trangthai=0 and pk_seq = '" + ctskuId + "' ";
	    	if(db.updateReturnInt(query)!=1)
	    	{
	    		db.getConnection().rollback();
	    		db.shutDown();
				return "lỗi:"+ query ;
	    	}
	    	
	    	query = " update DUYETTRAKHUYENMAI_KHACHHANG set trangthai = 1 where duyetkm_fk = '" + ctskuId + "'   ";
	    	if(db.updateReturnInt(query)<0)
	    	{
	    		db.getConnection().rollback();
	    		db.shutDown();
				return "lỗi:"+ query ;
	    	}
	    	
	    	
	    	db.getConnection().commit();
	    	db.shutDown();
	    	return "Chốt thành công";
		} 
    	catch (SQLException e)
    	{

    		Utility.rollback_and_shutdown(db);
			db.shutDown();
			return "lỗi:"+ e.getMessage() +"("+query +")" ;
    	}
    	
	}

	
	private void XoaChiTieu(String ctskuId) 
	{
		dbutils db = new dbutils();
    	
    	try 
    	{
			db.getConnection().setAutoCommit(false);
			//System.out.println("delete DuyetTraKhuyenMai_KhachHang where duyetkm_fk = '" + ctskuId + "'");
			String query = "DELETE FROM DUYETTRAKHUYENMAI_KHACHHANG_SANPHAM WHERE DUYETKM_FK = '" + ctskuId + "'";
	    	if(!db.update(query))
	    	{
	    		db.getConnection().rollback();
				return;
	    	}
	    	
	    	query = "DELETE FROM DUYETTRAKHUYENMAI_KHACHHANG WHERE DUYETKM_FK = '" + ctskuId + "'";
	    	if(!db.update(query))
	    	{
	    		db.getConnection().rollback();
				return;
	    	}
	    	
	    	query = "DELETE FROM DUYETTRAKHUYENMAI_DANGKY WHERE DUYETKM_FK = '" + ctskuId + "'";
	    	if(!db.update(query))
	    	{
	    		db.getConnection().rollback();
				return;
	    	}
	    	
	    	if(db.updateReturnInt(" DELETE FROM DUYETTRAKHUYENMAI WHERE trangthai=0 and PK_SEQ = '" + ctskuId + "'")!=1)
			{
				db.getConnection().rollback();
				return;
			}
	    	db.getConnection().commit();
		} 
    	catch (SQLException e)
    	{ try { db.getConnection().rollback(); } catch (SQLException e1) {} } 
    	finally{ try { db.getConnection().setAutoCommit(true); if(db != null) db.shutDown(); } catch (Exception ex) { ex.printStackTrace(); } }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    //this.out  = response.getWriter();
	    HttpSession session = request.getSession();	
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    Utility util = new Utility();
	    
	    String userId = util.antiSQLInspection(request.getParameter("userId"));     
	    IDuyettrakhuyenmaiList obj;
	    
		String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    if(action.equals("new"))
	    {
	    	out = response.getWriter();
    		IDuyettrakhuyenmai tctsku = new Duyettrakhuyenmai();
    		tctsku.setUserId(userId);
    		tctsku.createRs();
    		
	    	session.setAttribute("tctskuBean", tctsku);  	
    		session.setAttribute("userId", userId);
		
    		response.sendRedirect(request.getContextPath() + "/pages/Center/DuyetTraKhuyenMaiNew.jsp");
	    }
	    else
	    {
	    	obj = new DuyettrakhuyenmaiList();
		    obj.setUserId(userId);

	    	String search = getSearchQuery(request, obj);
	    	
	    	
	    	
	    	if(action.equals("excel"))
	    	{
	    		request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=KhuyenMaiTichLuy.xlsm");
				OutputStream out1 = response.getOutputStream();
				String query = setQueryExcel(obj);
				try
				{
					ExportToExcel(session,out1, obj, query);
					return;
				} catch (Exception e)
				{
					
					request.setCharacterEncoding("UTF-8");
				    response.setCharacterEncoding("UTF-8");
				    response.setContentType("text/html; charset=UTF-8");				
					obj.setMsg("Khong the tao bao cao " + e.getMessage());
				}
	    	}
	    	else
	    		out = response.getWriter();
	    	
	    	
	    	obj.setUserId(userId);
	    	obj.init(search);	
	    	session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    		response.sendRedirect(request.getContextPath() + "/pages/Center/DuyetTraKhuyenMai.jsp");	
	    }
	}
	
	private String setQueryExcel(IDuyettrakhuyenmaiList obj)
	{
		
		String query = 	 "\n  select  ttl.SCHEME,p.TEN as NPP, kh.MAFAST, kh.TEN as KHTEN, km.DOANHSO, km.MUCDANGKY_fk as MUCDANGKY  " + 
						 "\n 	, km.MUCDAT as MUCDAT, km.thuong as TONGTIEN, km.sanpham, isnull(km.MUCDUYET,0) AS MUCDUYET,   " + 
						 "\n  isnull(tt.ten, '') as tinhthanh, isnull(dbo.[ListOfNVBH](kh.pk_seq),'') as daidienkinhdoanh   " + 
						 "\n  from DUYETTRAKHUYENMAI_KHACHHANG km   " + 
						 "\n  inner join DUYETTRAKHUYENMAI t on t.PK_SEQ = km.duyetkm_fk  " + 
						 "\n  inner join TIEUCHITHUONGTL ttl on ttl.PK_SEQ = t.CTKM_FK  " + 
						 "\n  inner join KHACHHANG kh on km.khId = kh.PK_SEQ   " + 
						 "\n  inner join NHAPHANPHOI p on p.PK_SEQ = kh.NPP_FK   " + 
						 "\n  left join TINHTHANH tt on tt.PK_SEQ = kh.TINHTHANH_FK   " + 
						 "\n  where 1=1  " ;
		if(obj.getThang().length() > 0)
			query += " and month(t.ngaytao) = '" + obj.getThang() + "' ";
		if(obj.getNam().length() > 0)
			query += " and year(t.ngaytao ) = '" + obj.getNam() + "' ";
		if(obj.getDiengiai().length() > 0)
			query += " and (t.diengiai like N'%" + obj.getDiengiai() + "%' or ttl.scheme like N'%" + obj.getDiengiai() + "%') ";
		if(obj.getTrangthai().length() > 0)
			query += " and t.trangthai = '" + obj.getTrangthai() + "' ";
		System.out.println("setQueryExcel : " + query);
		return query;
	}
	
	private String getSearchQuery(HttpServletRequest request, IDuyettrakhuyenmaiList obj) 
	{
		String thang = request.getParameter("thang");
		if(thang == null)
			thang = "";
		obj.setThang(thang);
		
		String nam = request.getParameter("nam");
		if(nam == null)
			nam = "";
		obj.setNam(nam);
		
		String diengiai = request.getParameter("diengiai");
		if(diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String sql = "select d.scheme, a.pk_seq, a.diengiai, a.trangthai, a.ngaytao, a.ngaysua, b.ten as nguoitao, c.ten as nguoisua  " +
				"from DUYETTRAKHUYENMAI a inner join NHANVIEN b on a.NGUOITAO = b.pk_seq " +
				"	inner join NHANVIEN c on a.NGUOISUA = c.pk_seq" +
				"	inner join TieuChiThuongTL d on a.ctkm_fk = d.pk_seq ";
		
		if(thang.length() > 0)
			sql += " and month(a.ngaytao) = '" + thang + "' ";
		if(nam.length() > 0)
			sql += " and year(a.ngaytao ) = '" + nam + "' ";
		if(diengiai.length() > 0)
			sql += " and (a.diengiai like N'%" + diengiai + "%' or d.scheme like N'%" + diengiai + "%') ";
		if(trangthai.length() > 0)
			sql += " and a.trangthai = '" + trangthai + "' ";
		
		
		sql += "order by a.pk_seq desc";
		
		return sql;
	}

	
	
	private void ExportToExcel(HttpSession session,OutputStream out,IDuyettrakhuyenmaiList obj,String query )throws Exception
	{
		try{ 			

			//FileInputStream fstream = null;
			Workbook workbook = new Workbook();

			//fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonHangETC.xlsm");
			//workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			TaoBaoCao(session,workbook, obj, query);
			
			workbook.save(out);
			//fstream.close();
						

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	
	private void TaoBaoCao(HttpSession session,com.aspose.cells.Workbook workbook,IDuyettrakhuyenmaiList obj,String query)throws Exception
	{
		dbutils db = new dbutils();
		try{

			
			redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis();
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Theo dõi tích lũy khách hàng");
			cell = cells.getCell("A2");
			/*ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());
*/
			
			
			ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			
			int location  = 0;
			int row = 7;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				String headerColumnName = Utility.GLanguage(rsmd.getColumnName(i).replace("(%)",""),session,jedis)  ;
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(headerColumnName);
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				
				for(int i =1;i <=socottrongSql ; i ++)
				{
					
					cell = cells.getCell(row,location + i-1 );
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				
				++row;
			}
			if(rs!=null)rs.close();
			
			geso.dms.center.util.Utility.JedisClose(jedis);
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
		db.shutDown();
	}
}
