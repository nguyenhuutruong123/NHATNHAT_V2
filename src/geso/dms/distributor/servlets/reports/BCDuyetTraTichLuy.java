package geso.dms.distributor.servlets.reports;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.bcduyettratl.*;
import geso.dms.distributor.beans.bcduyettratl.imp.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class BCDuyetTraTichLuy extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PrintWriter out; 
	
    public BCDuyetTraTichLuy() {
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
		Ibcduyettratl tctskuBean;
		
		this.out = response.getWriter();
		Utility util = new Utility();		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	   
	    tctskuBean = new bcduyettratl();
	    tctskuBean.setUserId(userId);
	    session.setAttribute("tctskuBean", tctskuBean);
        String TT=request.getParameter("view")==null?"":request.getParameter("view");
        tctskuBean.setTrungtam(TT);
        tctskuBean.init();
        
        String nextJSP = request.getContextPath() + "/pages/Distributor/BCDuyetTraTichLuy.jsp";
        if(querystring.indexOf("display") >= 0)
        	nextJSP = request.getContextPath() + "/pages/Distributor/BCDuyetTraTichLuy.jsp";
        
        response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Ibcduyettratl tctskuBean;
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
 		String action = request.getParameter("action");
 		System.out.println("Action la: " + action);
 		if(action == null)
 			action = "upload";
 		if(!action.equals("excel")) { this.out = response.getWriter(); }
		Utility util = new Utility();
		tctskuBean = new bcduyettratl();
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		tctskuBean.setUserId(userId);
		
		String scheme = util.antiSQLInspection(request.getParameter("ctkmId"));
		if (scheme == null)
			scheme = "";
		tctskuBean.setCtkmId(scheme);
		System.out.println("ctkm ID day : "+scheme);
		
		String TT=request.getParameter("view")==null?"":request.getParameter("view");
		tctskuBean.setTrungtam(TT);
		
		String tungay_ds = util.antiSQLInspection(request.getParameter("tungay_ds"));
		if (tungay_ds == null)
			tungay_ds = "";
		tctskuBean.setTungay_ds(tungay_ds);
		
		String denngay_ds = util.antiSQLInspection(request.getParameter("denngay_ds"));
		if (denngay_ds == null)
			denngay_ds = "";
		tctskuBean.setDenngay_ds(denngay_ds);	

 		if(action.equals("excel")){
 			Export(tctskuBean, response);
 			tctskuBean.createRs();
			session.setAttribute("userId", userId);
			session.setAttribute("tctskuBean", tctskuBean);
			String nextJSP;
			nextJSP = request.getContextPath() + "/pages/Distributor/BCDuyetTraTichLuy.jsp";
			response.sendRedirect(nextJSP);
			return;
 		}
	}
	
	public String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	private void Export(Ibcduyettratl obj, HttpServletResponse response) {
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
			sheet.addCell(new Label(cong++, 3, "CTTL", cellFormat));
			sheet.addCell(new Label(cong++, 3, "STT", cellFormat));
			sheet.addCell(new Label(cong++, 3, "CHI NHÁNH / NHÀ PHÂN PHỐI", cellFormat));
			sheet.addCell(new Label(cong++, 3, "ID", cellFormat));
			sheet.addCell(new Label(cong++, 3, "MÃ KH", cellFormat));
			sheet.addCell(new Label(cong++, 3, "TÊN KH", cellFormat));
			sheet.addCell(new Label(cong++, 3, "DOANH SỐ", cellFormat));
			sheet.addCell(new Label(cong++, 3, "SO LƯỢNG SP", cellFormat));
			sheet.addCell(new Label(cong++, 3, "MỨC ĐẠT", cellFormat));
			//sheet.addCell(new Label(cong++, 3, "MỨC DUYỆT ĐẠT", cellFormat));
			//sheet.addCell(new Label(cong++, 3, "MỨC Đăng ký", cellFormat));
			sheet.addCell(new Label(cong++, 3, "Chiết khấu (%)", cellFormat));
			sheet.addCell(new Label(cong++, 3, "TỔNG THƯỞNG", cellFormat));
			//sheet.addCell(new Label(cong++, 3, "NVBH", cellFormat));
			//sheet.addCell(new Label(cong++, 3, "TỈNH THÀNH", cellFormat));
			geso.dms.distributor.db.sql.dbutils db = new geso.dms.distributor.db.sql.dbutils();
			geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
			String nppid = util.getIdNhapp(obj.getUserId(), db);
			
			/*int tinhtungsp = 0;
			String query = "select tinhtungsp from TIEUCHITHUONGTL b  " +
                           "\n where b.PK_SEQ=" + obj.getCtkmId();
			ResultSet rsTemp = db.get(query);
			if(rsTemp != null){
				if(rsTemp.next()){
					tinhtungsp = rsTemp.getInt("tinhtungsp");
				}
				rsTemp.close();
			}*/
			
			int stt = 4;
			int i =0;
			/*if(tinhtungsp == 0){
				query = "select d.scheme,b.PK_SEQ as KHACHHANG_FK, b.maFAST as MAFAST, b.TEN as KHTEN, a.doanhso, a.MUCDAT, a.soluongmua,e.chietkhau,a.thuong as tongtien " +
		                   "\n from DUYETTRAKHUYENMAI_KHACHHANG a inner join KHACHHANG b on a.khId = b.PK_SEQ " +
		                   "\n inner join DUYETTRAKHUYENMAI c on c.PK_SEQ = a.duyetkm_fk " +
		                   "\n inner join TIEUCHITHUONGTL d on d.PK_SEQ = c.CTKM_FK " +
		                   "\n inner join TIEUCHITHUONGTL_TIEUCHI e on e.thuongtl_fk=d.PK_SEQ and a.MUCDAT=e.muc" +
		                   "\n where a.khid in (select pk_seq from khachhang kh where kh.npp_fk = "+nppid+") and c.trangthai = 1 and d.PK_SEQ="+obj.getCtkmId() +
		                   "\n and d.ngayds_tungay >= '" + obj.getTungay_ds() + "' and d.ngayds_denngay <= '" + obj.getDenngay_ds() + "'" ;
			}
			else{
				
			}
			*/
			String query = "";
			String condition = obj.getCtkmId() == null || obj.getCtkmId().trim().length() <= 0 ? "" : "and d.PK_SEQ="+obj.getCtkmId() ;
			String condition_sub="";
			if(!obj.getTrungtam().equals("TT"))
				condition_sub=" and a.khid in (select pk_seq from khachhang kh where kh.npp_fk = "+nppid+")";
			query = "select d.scheme,b.PK_SEQ as KHACHHANG_FK,npp.TEN NPP, b.maFAST as MAFAST, b.TEN as KHTEN, a.doanhso, a.MUCDAT + 1 MUCDAT, a.soluongmua,case when isnull(d.tinhtungsp,0)=1 then 0  else e.chietkhau end as chietkhau,a.thuong as tongtien " +
	                   "\n from DUYETTRAKHUYENMAI_KHACHHANG a inner join KHACHHANG b on a.khId = b.PK_SEQ " +
	                   "\n inner join nhaphanphoi npp on b.npp_fk = npp.pk_seq " +
	                   "\n inner join DUYETTRAKHUYENMAI c on c.PK_SEQ = a.duyetkm_fk " +
	                   "\n inner join TIEUCHITHUONGTL d on d.PK_SEQ = c.CTKM_FK " +
	                   "\n left join TIEUCHITHUONGTL_TIEUCHI e on e.thuongtl_fk=d.PK_SEQ and a.MUCDAT=e.muc" +
	                   "\n where c.trangthai = 1 " + condition +
	                   "\n and d.ngayds_tungay like '" + obj.getTungay_ds().substring(0, 7) + "%' " +
	                   "\n and d.ngayds_denngay like '" + obj.getDenngay_ds().substring(0, 7) + "%' "+ condition_sub ;
			
			System.out.println("Query lấy báo cáo: " + query);
			ResultSet rs =  db.get(query);
			if(rs != null)
			while ( rs.next() )
			{
				cong = 0;
				sheet.addCell(new Label(cong ++, stt, rs.getString("scheme"), cellFormat));
				sheet.addCell(new Label(cong ++, stt, (++i)+ "", cellFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("NPP") , cellFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("KHACHHANG_FK") , cellFormat));
				sheet.addCell(new Label(cong ++, stt,rs.getString("MAFAST"), cellFormat));
				sheet.addCell(new Label(cong ++, stt, rs.getString("KHTEN"), cellFormat));
				sheet.addCell( new Number(cong ++, stt,rs.getDouble("DOANHSO"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("SOLUONGMUA"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("MUCDAT"), inFormat));
				//sheet.addCell( new Number(cong ++, stt, rs.getDouble("MUCDANGKY"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("CHIETKHAU"), inFormat));
				sheet.addCell( new Number(cong ++, stt, rs.getDouble("TONGTIEN"), inFormat));
				//sheet.addCell(new Label(cong ++, stt, rs.getString("daidienkinhdoanh"), cellFormat));
				//sheet.addCell(new Label(cong ++, stt, rs.getString("tinhthanh"), cellFormat));
				stt++;
			}
			
			if(db != null) db.shutDown();
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
