package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.report.Ireport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.aspose.cells.Picture;

public class BCPGSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String setQuery(IStockintransit obj,String action) 
	{
		
		
		String condition ="";
		
		if(obj.getnppId().trim().length() > 0)
			condition +=" and npp.pk_seq = "+ obj.getnppId();
		if(obj.getDdkd().trim().length() > 0)
			condition +=" and ddkd.pk_seq = "+ obj.getDdkd();
		if(obj.getvungId().trim().length() > 0)
			condition +=" and npp.khuvuc_fk in ( select pk_seq from khuvuc where vung_fk  = "+ obj.getvungId() + ") ";
		if(obj.getkhuvucId().trim().length() > 0)
			condition +=" and npp.khuvuc_fk = "+ obj.getkhuvucId();
		Utility Ult = new Utility();
		condition += " and npp.pk_Seq in " + Ult.quyen_npp(obj.getuserId());
		
		
		String query=   "";
		if(action.equals("anhchup"))
		{
			query ="\n  select npp.sitecode manpp, npp.ten npp,  gs.ten gsbh, ddkd.ten PG, data.ngay[Ngày], kh.SMARTID makh, kh.ten kh,kh.diachi, data.imgvt, data.imgvt2, data.imgroidi, data.imgroidi2  " + 
					"\n from PG_khachhang data " + 
					"\n inner join daidienkinhdoanh ddkd on data.nhanvien_fk= ddkd.pk_seq and data.loai = 1 " + 
					"\n inner join khachhang kh on kh.pk_seq = data.khachhang_fk " + 
					"\n inner join nhaphanphoi npp on npp.pk_seq = kh.npp_fk  " + 
					"\n inner join giamsatbanhang gs on gs.pk_seq = (select top 1 gsbh_fk from ddkd_gsbh x where x.ddkd_fk = ddkd.pk_seq ) " + 
					"\n where data.NGAY >='"+obj.gettungay()+"' and data.NGAY <='"+obj.getdenngay()+"' " +  condition +
					"\n order by [Ngày] " ;
			
		}
		else if(action.equals("banhang"))
		{
			query ="\n  select npp.ten [NPP], gs.ten [GIÁM SÁT] , ddkd.ten [PG],  kh.ten [SIÊU THỊ] ,data.NGAY[Ngày], data.SOKH [SỐ KH TIẾP CẬN] , data.SOBILL [SỐ SUẤT],data.TONGTIEN [TỔNG TIỀN_isNum] " + 
				"\n from tvbanhang data " + 
				"\n inner join DAIDIENKINHDOANH ddkd on ddkd.pk_seq = data.ddkd_fk " + 
				"\n left join giamsatbanhang gs on gs.pk_seq = (select top 1 gsbh_fk from ddkd_gsbh x where x.ddkd_fk = ddkd.pk_seq )  " + 
				"\n inner join khachhang kh on kh.pk_seq = data.khahang_fk "  +
				"\n inner join nhaphanphoi npp on npp.pk_seq = kh.npp_fk " +
				"\n where data.NGAY >='"+obj.gettungay()+"' and data.NGAY <='"+obj.getdenngay()+"' " +  condition +
				"\n order by [Ngày] " ;
		}
		else if(action.equals("thuthap"))
		{
			query ="\n  select npp.ten [NPP], gs.ten [GIÁM SÁT] , ddkd.ten [PG] ,data.NGAYTAO [Ngày], data.TEN [TÊN KH],data.SODT [ĐIỆN THOAI], DATA.DIACHI [ĐỊA CHỈ] " + 
				"\n from KHACHHANGTIEPCAN data " + 
				"\n inner join DAIDIENKINHDOANH ddkd on ddkd.pk_seq = data.NGUOITAO " +
				"\n inner join DAIDIENKINHDOANH_NPP ddnpp on ddnpp.ddkd_fk = ddkd.pk_seq "+
				"\n inner join nhaphanphoi npp on npp.pk_seq = ddnpp.npp_fk " + 
				"\n left join giamsatbanhang gs on gs.pk_seq = (select top 1 gsbh_fk from ddkd_gsbh x where x.ddkd_fk = ddkd.pk_seq )  "  +
				"\n where data.NGAYTAO >='"+obj.gettungay()+"' and data.NGAYTAO <='"+obj.getdenngay()+"' " +  condition +
				"\n order by [Ngày] " ;
		}
		else if(action.equals("chamcong"))
		{
			query ="\n  select  npp.sitecode [MÃ NPP] , npp.ten  [NPP],  gs.ten [GIÁM SÁT], ddkd.ten PG, data.ngay[Ngày], kh.SMARTID [MÃ KH], kh.ten  [TÊN KH],data.thoidiem[VÀO CA], data.THOIDIEMDI[HẾT CA] " + 
					"\n  ,isnull(datediff(minute,thoidiem,thoidiemdi),0) [TỔNG THỜI GIAN LV (phút)] , case when isnull(datediff(minute,thoidiem,thoidiemdi),0) >=280 then 1 else 0 end [CÔNG] " + 
					"\n from PG_khachhang data " + 
					"\n inner join daidienkinhdoanh ddkd on data.nhanvien_fk= ddkd.pk_seq and data.loai = 1  " + 
					"\n  inner join khachhang kh on kh.pk_seq = data.khachhang_fk " +
					"\n inner join nhaphanphoi npp on npp.pk_seq = kh.npp_fk " + 
					"\n left join giamsatbanhang gs on gs.pk_seq = (select top 1 gsbh_fk from ddkd_gsbh x where x.ddkd_fk = ddkd.pk_seq )  "  +
					"\n where data.NGAY >='"+obj.gettungay()+"' and data.NGAY <='"+obj.getdenngay()+"' " +  condition +
					"\n order by [Ngày] " ;
		}

		/*
		e
		
		select * from tvbanhang
		select * from PG_khachhang
		select * from khachhangtiepcan*/
		System.out.println("____BC anh trung bay: " + query);
		return query;
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
		IStockintransit obj = new Stockintransit();
	
	    Utility util = new Utility();
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    String view = util.antiSQLInspection(request.getParameter("view"));
	    if(view == null)
	    	view = "";
	    
	    obj.setuserId(userId);
	    obj.init_BCPG();	
	
	    String nppId ="";
	    nppId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("nppId")));
		if (nppId == null)
			nppId = "";
		obj.setnppId(nppId);
		
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		
		String nextJSP = request.getContextPath() + "/pages/Center/BCPG.jsp";
		response.sendRedirect(nextJSP);
	}
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
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
		IStockintransit obj = new Stockintransit();
		Utility  util = new Utility();
		
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			userId = "";
		obj.setuserId(userId);
		
		String userTen = (String) session.getAttribute("userTen");
		obj.setuserTen(userTen);
		
		String view=util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";
		
		String nppId ="";
		if(view.equals("TT")){
			 nppId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("nppId")));
			if (nppId == null)
				nppId = "";
			obj.setnppId(nppId);
		}else{
			
			nppId=util.getIdNhapp(userId);
		}
		
		
		String ddkdId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("ddkdId")));
		if (ddkdId == null)
			ddkdId = "";
		obj.setDdkd(ddkdId);

		String cttbId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("cttbId")));
		if (cttbId ==null)
			cttbId = "";
		obj.setCttbId(cttbId);
	
		String tungay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("Sdays")));
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("Edays")));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		
		/*geso.htp.center.util.Utility Ult = new geso.htp.center.util.Utility();
		nppId = Ult.getIdNhapp(userId);*/

		obj.setvungId(util.antiSQLInspection(util.antiSQLInspection(request.getParameter("vungId")))!=null?
				util.antiSQLInspection(util.antiSQLInspection(request.getParameter("vungId"))):"");
			
		obj.setkhuvucId(util.antiSQLInspection(util.antiSQLInspection(request.getParameter("khuvucId")))!=null?
				util.antiSQLInspection(util.antiSQLInspection(request.getParameter("khuvucId"))):"");
		
	
		obj.setTbhId(util.antiSQLInspection(util.antiSQLInspection(request.getParameter("tbhid")))!=null?
				util.antiSQLInspection(util.antiSQLInspection(request.getParameter("tbhid"))):"");
	
		String action = util.antiSQLInspection(request.getParameter("action"));
		if (action.equals("anhchup")) 
		{
			String query = setQuery(obj,action);
			obj.setAnhtrungbayRs(query);

			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			obj.init_BCPG();	
			String nextJSP = request.getContextPath() + "/pages/Center/BCPG.jsp";
			response.sendRedirect(nextJSP);
			return;
		}
		else if(action.trim().length() > 0)
		{
			try 
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCPG.xlsm");

				OutputStream out = response.getOutputStream();
				String query = setQuery(obj,action);
				System.out.println(query);
				ExportToExcel(session,out, obj, query);
				obj.DBclose();
				return;
			} 
			catch (Exception ex) 
			{
				request.getSession().setAttribute("errors", ex.getMessage());
			}
		}
		else
		{
			obj.init_BCPG();	
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/BCPG.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private void TaoBaoCao(HttpSession session,com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		try{		
			redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis();
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo PG ");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());			
			
			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();
			
			int location  = 0;
			int row = 10;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				String headerColumnName = Utility.GLanguage(rsmd.getColumnName(i).replace("_isNum",""), session,jedis) ;
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
					
					if(rsmd.getColumnName(i).contains("_isNum") )
					{
						int format = 37;
							if(rsmd.getColumnName(i).contains("_isNum2") )	
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
	}
	
	private void ExportToExcel(HttpSession session,OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 		
			
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			TaoBaoCao(session,workbook,obj,query);		
			workbook.save(out);	
			out.close();
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}
		
	}
	
	private String getQueryExcel(IStockintransit obj)
	{
		String sql = "\n select ROW_NUMBER() OVER (PARTITION BY a.KH_FK order by a.Ngay ASC) as STT, " +
			"\n 	v.TEN as VUNG, kv.TEN as KHUVUC, npp.TEN as NPP,ddkd.smartid as MANV, ddkd.TEN as DDKD, kh.SMARTID, kh.TEN, kh.DIACHI, a.FILENAME, a.NGAY, ISNULL(x.Ten,'') as PHUONGXA, ISNULL(q.TEN,'') as QUANHUYEN, ISNULL(t.TEN,'') as TINHTHANH " + 
			"\n from KHACHHANG_ANHCHUP a " +
			"\n inner join DAIDIENKINHDOANH ddkd on ddkd.pk_seq = a.DDKD_FK " +  
			"\n inner join KHACHHANG kh on kh.PK_SEQ = a.KH_FK " +  
			"\n inner join NHAPHANPHOI npp on npp.PK_SEQ= kh.NPP_FK " +
			"\n left join DDKD_GSBH kdgs on kdgs.DDKD_FK = ddkd.PK_SEQ " +
			"\n left join GIAMSATBANHANG gs on gs.PK_SEQ = kdgs.GSBH_FK " +
			"\n left join GSBH_KHUVUC gskv on gskv.GSBH_FK = gs.PK_SEQ " +
			"\n left join KHUVUC kv on kv.PK_SEQ = gskv.KHUVUC_FK " +
			"\n left join VUNG v on v.PK_SEQ = kv.VUNG_FK " +
			"\n LEFT JOIN PhuongXa x on x.pk_Seq = kh.PhuongXa_FK " +
			"\n left join QUANHUYEN q on q.PK_SEQ = kh.QUANHUYEN_FK " +
			"\n left join TINHTHANH t on t.PK_SEQ = kh.TINHTHANH_FK " +
			"\n where a.NGAY >= '"+obj.gettungay()+"' AND a.NGAY <= '"+obj.getdenngay()+"'";
		
		//xài tạm thời, nếu bỏ xài chỉnh lại ko xuất cột SOANHCHUP
	/*	
		sql = 	"\n select v.TEN as VUNG, kv.TEN as KHUVUC, npp.TEN as NPP,ddkd.smartid as MANV, ddkd.TEN as DDKD, kh.SMARTID, kh.TEN, kh.DIACHI, a.FILENAME, a.NGAY, ISNULL(x.Ten,'') as PHUONGXA, ISNULL(q.TEN,'') as QUANHUYEN, ISNULL(t.TEN,'') as TINHTHANH, " +
				"\n		(SELECT COUNT(*) FROM KHACHHANG_ANHCHUP z WHERE z.KH_FK = dk.KHACHHANG_FK and z.NGAY >= '"+obj.gettungay()+"' AND z.NGAY <= '"+obj.getdenngay()+"') as SOANHCHUP 	" +      
				"\n  from DKTRUNGBAY_KHACHHANG dk " +
				"\n inner join DANGKYTRUNGBAY dktb on dktb.PK_SEQ = dk.dktrungbay_fk " +
				"\n left join " +      
				"\n  KHACHHANG_ANHCHUP a on dk.KHACHHANG_FK = a.KH_FK and a.NGAY >= '"+obj.gettungay()+"' AND a.NGAY <= '"+obj.getdenngay()+"' " +      
				"\n  left join ( " +      
				"\n 	select distinct d.PK_SEQ, d.SMARTID, d.TEN, t.KHACHHANG_FK from KHACHHANG_TUYENBH t inner join TUYENBANHANG tbh on tbh.PK_SEQ = t.TBH_FK " +      
				"\n 	inner join DAIDIENKINHDOANH d on d.PK_SEQ = tbh.DDKD_FK  " +      
				"\n 	where d.TRANGTHAI = 1 " +      
				"\n   " +      
				"\n  )ddkd on ddkd.KHACHHANG_FK = dk.KHACHHANG_FK " +      
				"\n  inner join KHACHHANG kh on kh.PK_SEQ = dk.KHACHHANG_FK " +      
				"\n  inner join NHAPHANPHOI npp on npp.PK_SEQ= kh.NPP_FK  " +      
				"\n  left join DDKD_GSBH kdgs on kdgs.DDKD_FK = ddkd.PK_SEQ  " +      
				"\n  left join GIAMSATBANHANG gs on gs.PK_SEQ = kdgs.GSBH_FK  " +      
				"\n  left join GSBH_KHUVUC gskv on gskv.GSBH_FK = gs.PK_SEQ  " +      
				"\n  left join KHUVUC kv on kv.PK_SEQ = gskv.KHUVUC_FK  " +      
				"\n  left join VUNG v on v.PK_SEQ = kv.VUNG_FK  " +      
				"\n LEFT JOIN PhuongXa x on x.pk_Seq = kh.PhuongXa_FK " +
				"\n left join QUANHUYEN q on q.PK_SEQ = kh.QUANHUYEN_FK " +
				"\n left join TINHTHANH t on t.PK_SEQ = kh.TINHTHANH_FK " +
				"\n  where 1 =1 ";*/
				
		sql = 
			"select * from (" +
			"\n 	 select v.TEN as VUNG, kv.TEN as KHUVUC, npp.TEN as NPP,ddkd.smartid as MANV, ddkd.TEN as DDKD, kh.SMARTID, kh.TEN, kh.DIACHI, a.FILENAME, a.NGAY, ISNULL(x.Ten,'') as PHUONGXA, ISNULL(q.TEN,'') as QUANHUYEN, ISNULL(t.TEN,'') as TINHTHANH,  " +
			"\n (SELECT COUNT(*) FROM KHACHHANG_ANHCHUP z WHERE z.KH_FK = ddkd.KHACHHANG_FK and z.DDKD_FK = ddkd.PK_SEQ  and z.NGAY >= '"+obj.gettungay()+"' AND z.NGAY <= '"+obj.getdenngay()+"') as SOANHCHUP 	 " +
			"\n from (  " +
			"\n select distinct d.PK_SEQ, d.SMARTID, d.TEN, t.KHACHHANG_FK from KHACHHANG_TUYENBH t inner join TUYENBANHANG tbh on tbh.PK_SEQ = t.TBH_FK  " +
			"\n inner join DAIDIENKINHDOANH d on d.PK_SEQ = tbh.DDKD_FK " +
			"\n )ddkd  " +
			"\n left join KHACHHANG_ANHCHUP a on ddkd.KHACHHANG_FK = a.KH_FK and a.DDKD_FK = ddkd.PK_SEQ and a.NGAY >= '"+obj.gettungay()+"' AND a.NGAY <= '"+obj.getdenngay()+"' " +
			"\n inner join KHACHHANG kh on kh.PK_SEQ = ddkd.KHACHHANG_FK  " +
			"\n inner join NHAPHANPHOI npp on npp.PK_SEQ= kh.NPP_FK   " +			
			"\n left join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK   " +
			"\n left join VUNG v on v.PK_SEQ = kv.VUNG_FK   " +
			"\n LEFT JOIN PhuongXa x on x.pk_Seq = kh.PhuongXa_FK  " +
			"\n left join QUANHUYEN q on q.PK_SEQ = kh.QUANHUYEN_FK  " +
			"\n left join TINHTHANH t on t.PK_SEQ = kh.TINHTHANH_FK  " +
			"\n where 1 =1  ";
			if(obj.getCttbId().trim().length() >0)
				sql += " and exists (select 1 from DKTRUNGBAY_KHACHHANG dkkh " +
				" inner join DANGKYTRUNGBAY dktb on dktb.PK_SEQ = dkkh.dktrungbay_fk " +
				" where dkkh.khachhang_fk = kh.pk_seq and dktb.cttrungbay_fk ='"+ obj.getCttbId()+"')";
			if(obj.getnppId().trim().length() > 0)
				sql +=" and npp.pk_seq = "+ obj.getnppId();
			if(obj.getDdkd().trim().length() > 0)
				sql +=" and ddkd.pk_seq = "+ obj.getDdkd();
			if(obj.getvungId().trim().length() > 0)
				sql +=" and v.pk_seq = "+ obj.getvungId();
			if(obj.getkhuvucId().trim().length() > 0)
				sql +=" and kv.pk_seq = "+ obj.getkhuvucId();
			Utility Ult = new Utility();
			sql += " and npp.pk_Seq in " + Ult.quyen_npp(obj.getuserId());
			
			sql += "\n union all " +
			"\n  select v.TEN as VUNG,kv.TEN as KHUVUC, npp.TEN as NPP,ddkd.smartid as MANV, ddkd.TEN as DDKD, kh.SMARTID, kh.TEN, kh.DIACHI, a.FILENAME, a.NGAY, ISNULL(x.Ten,'') as PHUONGXA, ISNULL(q.TEN,'') as QUANHUYEN, ISNULL(t.TEN,'') as TINHTHANH,  " +
			"\n (SELECT COUNT(*) FROM KHACHHANG_ANHCHUP z WHERE z.KH_FK = A.KH_FK and z.DDKD_FK = ddkd.PK_SEQ and z.NGAY >= '"+obj.gettungay()+"' AND z.NGAY <= '"+obj.getdenngay()+"') as SOANHCHUP 	 " +
			"\n from  " +
			"\n KHACHHANG_ANHCHUP a  " +
			"\n inner join DAIDIENKINHDOANH DDKD on ddkd.PK_SEQ = a.DDKD_FK and a.DDKD_FK = ddkd.PK_SEQ and a.NGAY >= '"+obj.gettungay()+"' AND a.NGAY <= '"+obj.getdenngay()+"'  " +
			"\n inner join KHACHHANG kh on kh.PK_SEQ = A.KH_FK  " +
			"\n inner join NHAPHANPHOI npp on npp.PK_SEQ= kh.NPP_FK   " +
			"\n left join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK   " +
			"\n left join VUNG v on v.PK_SEQ = kv.VUNG_FK   " +
			"\n  LEFT JOIN PhuongXa x on x.pk_Seq = kh.PhuongXa_FK  " +
			"\n left join QUANHUYEN q on q.PK_SEQ = kh.QUANHUYEN_FK  " +
			"\n left join TINHTHANH t on t.PK_SEQ = kh.TINHTHANH_FK  " +
			"\n where not exists(  " +
			"\n select * from KHACHHANG_TUYENBH t inner join TUYENBANHANG tbh on tbh.PK_SEQ = t.TBH_FK  " +
			"\n inner join DAIDIENKINHDOANH d on d.PK_SEQ = tbh.DDKD_FK where a.KH_FK = t.KHACHHANG_FK and d.PK_SEQ = ddkd.PK_SEQ ) " ;
		
		if(obj.getCttbId().trim().length() >0)
			sql += " and exists (select 1 from DKTRUNGBAY_KHACHHANG dkkh " +
					" inner join DANGKYTRUNGBAY dktb on dktb.PK_SEQ = dkkh.dktrungbay_fk " +
					" where dkkh.khachhang_fk = kh.pk_seq and dktb.cttrungbay_fk ='"+ obj.getCttbId()+"')";
		if(obj.getnppId().trim().length() > 0)
			sql +=" and npp.pk_seq = "+ obj.getnppId();
		if(obj.getDdkd().trim().length() > 0)
			sql +=" and ddkd.pk_seq = "+ obj.getDdkd();
		if(obj.getvungId().trim().length() > 0)
			sql +=" and v.pk_seq = "+ obj.getvungId();
		if(obj.getkhuvucId().trim().length() > 0)
			sql +=" and kv.pk_seq = "+ obj.getkhuvucId();
		sql += " and npp.pk_Seq in " + Ult.quyen_npp(obj.getuserId());
		
		//sql += " order by v.TEN, kv.TEN,ngay,a.kh_fk";
		sql += "\n) d  order by d.NPP,d.SMARTID, d.ngay ";
		return sql;
	}
	
	private String getQueryExcel_NEW(IStockintransit obj)
	{
		String sql ="\n SELECT "+
		"\n	COUNT(  d.NPP+d.SMARTID+ isnull(d.ngay,'')) OVER (PARTITION BY ( d.NPP+d.SMARTID+ isnull(d.ngay,'')),  d.NPP+d.SMARTID+ isnull(d.ngay,'')) AS SOKH, "+
		"\n	ROW_NUMBER() OVER (PARTITION BY (SMARTID+NPP+isnull(d.ngay,'')), SMARTID+NPP+isnull(d.ngay,'') order by (SMARTID+NPP+isnull(d.ngay,'')),SMARTID+NPP+isnull(d.ngay,'')) as STT, "+
		"\n	 VUNG, KHUVUC, NPP,MANV,  DDKD,DIENGIAI, SMARTID, TEN, DIACHI, FILENAME, NGAY, PHUONGXA, QUANHUYEN,  TINHTHANH,anhtongquan,anhtbkhuvuc,anhcuahang from ( "+
		"\n	 	 select v.TEN as VUNG, kv.TEN as KHUVUC, npp.TEN as NPP,ddkd.smartid as MANV, ddkd.TEN as DDKD, kh.SMARTID, kh.TEN, kh.DIACHI, a.FILENAME, a.NGAY, ISNULL(x.Ten,'') as PHUONGXA, ISNULL(q.TEN,'') as QUANHUYEN, ISNULL(t.TEN,'') as TINHTHANH, "+
		"\n			a.anhtongquan,a.anhtbkhuvuc ,kh.anhcuahang,ddkd.DIENGIAI "+
		"\n	 from (   "+
		"\n	 select distinct tbh.ngayid,d.PK_SEQ, d.SMARTID, d.TEN, t.KHACHHANG_FK,tbh.DIENGIAI from KHACHHANG_TUYENBH t inner join TUYENBANHANG tbh on tbh.PK_SEQ = t.TBH_FK   "+
		"\n	 inner join DAIDIENKINHDOANH d on d.PK_SEQ = tbh.DDKD_FK  "+
		"\n	 )ddkd  "+
		"\n	 left join KHACHHANG_ANHCHUP a on ddkd.KHACHHANG_FK = a.KH_FK and a.DDKD_FK = ddkd.PK_SEQ and a.NGAY >= '"+obj.gettungay()+"' AND a.NGAY <= '"+obj.getdenngay()+"'   "+
		"\n	 inner join KHACHHANG kh on kh.PK_SEQ = ddkd.KHACHHANG_FK   "+
		"\n	 inner join NHAPHANPHOI npp on npp.PK_SEQ= kh.NPP_FK    "+
		"\n	 left join KHUVUC kv on kv.PK_SEQ = npp.KHUVUC_FK    "+
		"\n	 left join VUNG v on v.PK_SEQ = kv.VUNG_FK    "+
		"\n	 LEFT JOIN PhuongXa x on x.pk_Seq = kh.PhuongXa_FK   "+
		"\n	 left join QUANHUYEN q on q.PK_SEQ = kh.QUANHUYEN_FK   "+
		"\n	 left join TINHTHANH t on t.PK_SEQ = kh.TINHTHANH_FK   "+
		"\n	 where 1 =1   and  kh.trangthai=1    ";
		
			if(obj.getnppId().trim().length() > 0)
				sql +=" and npp.pk_seq = "+ obj.getnppId();
			if(obj.getDdkd().trim().length() > 0)
				sql +=" and ddkd.pk_seq = "+ obj.getDdkd();
			if(obj.getvungId().trim().length() > 0)
				sql +=" and v.pk_seq = "+ obj.getvungId();
			if(obj.getkhuvucId().trim().length() > 0)
				sql +=" and kv.pk_seq = "+ obj.getkhuvucId();
			if(obj.getTbhId().trim().length() > 0)
				sql +=" and ddkd.ngayid = "+ obj.getTbhId();
			Utility Ult = new Utility();
			sql += " and npp.pk_Seq in " + Ult.quyen_npp(obj.getuserId());
		sql+="\n ) d "+
			 "\n where 1=1 "+
			 "\n order by d.NPP,d.SMARTID, d.ngay,d.ten ";
		return sql;
	}
	
	
}
