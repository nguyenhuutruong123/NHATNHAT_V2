package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
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

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class DailySecondarySalesTTSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public DailySecondarySalesTTSvl() 
	{
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
		
		IStockintransit obj = new Stockintransit();
	
		Utility util = new Utility();
		
		String querystring = request.getQueryString();
			
		obj.setuserTen((String) session.getAttribute("userTen"));
		String userId = util.getUserId(querystring);
		String nppId= util.getIdNhapp(userId);
		
		obj.setuserId(userId);
		
		String view = request.getParameter("view");
 	    if(view == null)
 	    	view = "";
 	    
 	   System.out.println("view :"+view);
 	    
 	   if(nppId!=null)
 		   view = "NPP";
 	   if(nppId==null)
 		   view ="TT";
 	  System.out.println("view 2:"+view + "NPP ID: "+nppId);
 	  
		obj.setdiscount("1");
		obj.setvat("1");
		obj.init();
		session.setAttribute("obj", obj);
		if(!view.equals("TT"))
		{
			String nextJSP = request.getContextPath() + "/pages/Distributor/DailySecondarySales.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			String nextJSP = request.getContextPath() + "/pages/Center/DailySecondarySales.jsp";
			response.sendRedirect(nextJSP);
		}
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
		
		IStockintransit obj = new Stockintransit();
		OutputStream out = response.getOutputStream();
		
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		Utility util = new Utility();
		
		String tungay =util.antiSQLInspection(request.getParameter("Sdays"));
		obj.settungay(tungay);
		
		String denngay =util.antiSQLInspection(request.getParameter("Edays"));
		obj.setdenngay(denngay);
		
		obj.setuserId(userId!=null? userId:"");
		obj.setuserTen(userTen!=null? userTen:"");
		obj.setkenhId(util.antiSQLInspection(request.getParameter("kenhId"))!=null?util.antiSQLInspection(request.getParameter("kenhId")):"");
		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId"))!=null?util.antiSQLInspection(request.getParameter("vungId")):"");
		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId"))!=null?util.antiSQLInspection(request.getParameter("khuvucId")):"");		
		obj.setgsbhId(util.antiSQLInspection(request.getParameter("gsbhs"))!=null?util.antiSQLInspection(request.getParameter("gsbhs")):"");
		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId"))!=null?util.antiSQLInspection(request.getParameter("ddkdId")):"");
		obj.setnppId(util.antiSQLInspection(request.getParameter("nppId"))!=null?util.antiSQLInspection(request.getParameter("nppId")):"");
		obj.setdvkdId(util.antiSQLInspection(request.getParameter("dvkdId"))!=null?util.antiSQLInspection(request.getParameter("dvkdId")):"");
		obj.setnhanhangId(util.antiSQLInspection(request.getParameter("nhanhangId"))!=null?util.antiSQLInspection(request.getParameter("nhanhangId")):"");
		obj.setchungloaiId(util.antiSQLInspection(request.getParameter("chungloaiId"))!=null?util.antiSQLInspection(request.getParameter("chungloaiId")):"");
		obj.setdvdlId(util.antiSQLInspection(request.getParameter("dvdlid"))!=null?util.antiSQLInspection(request.getParameter("dvdlid")):"");
		obj.setDdkd(util.antiSQLInspection(request.getParameter("ddkdId"))!=null?util.antiSQLInspection(request.getParameter("ddkdId")):"");
		obj.setkhId(util.antiSQLInspection(request.getParameter("khId"))!=null?util.antiSQLInspection(request.getParameter("khId")):"");
		obj.settype(util.antiSQLInspection(request.getParameter("type")) != null ? util.antiSQLInspection(request.getParameter("type")) : "");
		obj.setMucCN_DT(util.antiSQLInspection(request.getParameter("cndt")) != null ? util.antiSQLInspection(request.getParameter("cndt")) : "1");
		obj.setMuc_KhachHang(util.antiSQLInspection(request.getParameter("kh")) != null ? util.antiSQLInspection(request.getParameter("kh")) : "1");
		obj.setCn1(util.antiSQLInspection(request.getParameter("cn1"))!=null?util.antiSQLInspection(request.getParameter("cn1")):"");
		obj.setkhoId(util.antiSQLInspection(request.getParameter("khoid"))!=null?util.antiSQLInspection(request.getParameter("khoid")):"");
		String nppId= util.getIdNhapp(userId);
		String nppID = util.antiSQLInspection(request.getParameter("nppId"));
		System.out.println("___CN1___"+obj.getCn1());
		
		if(nppID==null)
			nppID = "";
      
	    String view=request.getParameter("view");
			if(view == null)
				view = "";
	
	  	if(nppId!=null)
	  	{
	   	   view = "NPP";
	   	   obj.setnppId(nppId);
	  	}
	  	
	    if(nppId==null)
	    {
	   	   view ="TT";
	   	  obj.setnppId(nppID);   
	    }
		obj.init();
	
		
		session.setAttribute("obj", obj);	
		String level = request.getParameter("level")==null?"":request.getParameter("level");
		obj.settungay(tungay);
		obj.setdenngay(denngay);
		
		String action = (String) util.antiSQLInspection(request.getParameter("action"));
		String nextJSP = request.getContextPath() + "/pages/Center/DailySecondarySales.jsp";
		if(!view.equals("TT"))
		{
			 nextJSP = request.getContextPath() + "/pages/Distributor/DailySecondarySales.jsp";
		}
		else
		{
			 nextJSP = request.getContextPath() + "/pages/Center/DailySecondarySales.jsp";
		}
		
		System.out.println("Action la: " + action);
		try
		{
			if (action.equals("Taomoi")) 
			{			
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=DoanhSoBanHang_"+util.setTieuDe(obj)+".xlsm");
				String query = setQuery("", obj,level);
				System.out.println("Query lay du lieu: " + query + "\n");
	        if(obj.gettype().equals("0"))
					{	
	        	if(!CreatePivotTable(out,obj,query,level))
		        {
		        		response.setContentType("text/html");
		            PrintWriter writer = new PrintWriter(out);
		            writer.print("Xin loi khong co bao cao trong thoi gian nay");
		            writer.close();
		        }
					}else
					{
						 query = setQuery_HoaDon(request,obj);
						 CreatePivotTable_HoaDon(out, obj, query);
					}
			}
			else if(action.equals("search"))
			{
		    	obj.setuserId(userId);
		  		String total_Query = getTotal(request, (IStockintransit) obj, view);
		  		obj.setTotal_Query(total_Query);
		    	obj.init();
		    	session.setAttribute("obj", obj);
	    		session.setAttribute("userId", userId);
		    }			
			response.sendRedirect(nextJSP);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			obj.setMsg(ex.getMessage());
			response.sendRedirect(nextJSP);
		}
	}	
		
	
	private String setQuery(String sql, IStockintransit obj,String level) 
	{
		 Utility Ult = new Utility();
		 String	query="";
		 String	sqlotc="";
		 String	sqletc="";
			sql = "";
			String sql1="";
			/*
			 * Lấy doanh số của CN/DT đang đăng nhập + doanh số CN/DT bán dùm cho khách hàng của CN/DT đang đăng nhập.
			 * 
			 */
	  	if(obj.getDdkd().length()>0)
	  	{
	  		sql+= " and ddkd.pk_seq ='"+obj.getDdkd()+"'";
	  	}	
	  	if(obj.getvungId().length()>0)
	  	{
	  		sql+= " and v.pk_seq ='"+obj.getvungId()+"'";
	  		sql1+= " and v.pk_seq ='"+obj.getvungId()+"'";
	  	}	
	  	if(obj.getkhuvucId().length()>0)
	  	{
	  		sql+= " and kv.pk_seq ='"+obj.getkhuvucId()+"'";
	  		sql1+= " and kv.pk_seq ='"+obj.getkhuvucId()+"'";
	  	}	
	  	
	  	if(obj.getkhId().length()>0)
	  	{
	  		sql+= " and kh.pk_seq ='"+obj.getkhId()+"'";
	  		sql1+= " and kh.pk_seq ='"+obj.getkhId()+"'";
	  	}	
		
	  	/*
	  	 * Nếu phân loại là TT và loại nhân viên không phải Phụ trách tỉnh
	  	 * thì thêm phân quyền.
	  	 */
	  	if(obj.getphanloai().equals("2")&& !obj.getLoaiNv().equals("3"))
  		{
	  		sql+= " and npp.pk_seq in " + Ult.quyen_npp(obj.getuserId())+" ";
	  		sql1+= " and npp.pk_seq in " + Ult.quyen_npp(obj.getuserId())+" ";

  		}
	  	
	  	String OTC="";
	  	String ETC="";
	  		OTC=
					"\n     select 'OTC' as LoaiHD, v.TEN as vten,kv.TEN as kvTen,tt.TEN as ttTEN,qh.TEN as qhTen  , npp.ma as nppMa, npp.TEN as nppTen,ddkd.TEN as ddkdTen,kk.ten as tenkho,  "+   
					"\n        kh.maFAST as khMa,kh.TEN as khTen,kh.DIACHI as khDiaChi,isnull(hdOTC.BVAT,0) as BVAT,isnull(hdOTC.AVAT,0) as AVAT,isnull(ck.AVAT_CK,0) as AVAT_CK ,isnull(ck.BVAT_CK,0) as  BVAT_CK ,hd.SOHOADON,hd.KYHIEU,hd.NGAYXUATHD  	  "+   
					"\n          from        "+   
					"\n          (       "+   
					"		select  a.KHACHHANG_FK ,  "+
					"			(	select top(1) bb.ddkd_fk from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ    \n " +
					"						inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ   \n " +
					"				where cc.KHACHHANG_FK = a.KHACHHANG_FK  \n " +
					"			) as DDKD_FK  ,b.HOADON_FK ,a.NPP_FK,sum( round(  b.SoLuong * b.DonGia,0 ) ) as BVAT,  "+  
					"			sum(round( round( b.SoLuong*b.DonGia,0 ) *(1+b.vat/100 )   ,0 )) as AVAT  ,  "+
					"			SUM(b.SoLuong)as SoLuong,SUM(b.DonGia) as DonGia,0 as Type    "+
					"		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ  "+            
					"\n       			where a.LOAIHOADON =0 and a.TRANGTHAI in (2,4)  and a.NgayXuatHD>='"+obj.gettungay()+"'  and a.NgayXuatHd<='"+obj.getdenngay()+"' ";      
					if(obj.getnppId().length()>0)
					{
						OTC+=" and a.npp_fk='"+obj.getnppId()+"'";
					}
					
					OTC+="\n          		group by b.HOADON_FK ,a.NPP_FK,a.KHACHHANG_FK		  ";
					if(obj.getCn1().equals("1"))
					{
						OTC+=
							"		union all  "+
							"		select  a.KHACHHANG_FK   "+
							"		,	(	select top(1) bb.ddkd_fk from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ    \n " +
							"						inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ   \n " +
							"				where cc.KHACHHANG_FK = a.KHACHHANG_FK  \n " +
							"			) as DDKD_FK ,b.HOADON_FK ,a.NPP_FK,sum( round(  b.SoLuong * b.DonGia,0 ) ) as BVAT,  "+  
							"			sum(round( round( b.SoLuong*b.DonGia,0 ) *(1+b.vat/100 )   ,0 )) as AVAT  ,  "+
							"			SUM(b.SoLuong)as SoLuong,SUM(b.DonGia) as DonGia,0 as Type    "+
							"		from HOADON a inner join HOADON_SP b on b.HOADON_FK=a.PK_SEQ  "+            
							"		where a.LOAIHOADON =0 and a.TRANGTHAI in (2,4)   "+
							"		 and a.NgayXuatHD>='"+obj.gettungay()+"'  and a.NgayXuatHd<='"+obj.getdenngay()+"'  "+ 
							"		and exists   "+
							"			(select PK_SEQ from KHACHHANG where TrucThuoc_FK='"+obj.getnppId()+"' and TrucThuoc_FK is not null and PK_SEQ=a.khachhang_Fk)  "+
							"		group by b.HOADON_FK ,a.NPP_FK,a.KHACHHANG_FK  ";
					}
					OTC+=
					"\n          )as hdOTC       "+   
					"\n          left join        "+   
					"\n          ( " +
					"	 							select hoadon_fk,SUM(round( ROUND(ck.chietkhau,0 )*( 1+ ck.thueVAT/100),0))  as AVAT_CK,  "+ 
	  			"	\n 										sum(ROUND( ck.chietkhau,0)) as BVAT_CK, 0 as Type   "+
	  			"	 							from HOADON_CHIETKHAU ck where hienthi = '1'   "+
	  			"	 							group by hoadon_fk   "+					
					"\n          )as ck on ck.HOADON_FK=hdOTC.HOADON_FK and hdOTC.Type=ck.Type       "+   
					"\n          left join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ=hdOTC.DDKD_FK    "+   
					"\n          inner join KHACHHANG kh on kh.PK_SEQ=hdOTC.KHACHHANG_FK  "+   
					"\n          inner join NHAPHANPHOI npp on npp.PK_SEQ=hdOTC.NPP_FK    "+   
					"\n          inner join HOADON hd on hd.PK_SEQ=hdOTC.HOADON_FK   "+   
					"\n			 inner join kho kk on hd.kho_fk=kk.pk_seq "+
					"\n          left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   "+   
					"\n          left join QUANHUYEN qh on qh.PK_SEQ=npp.QUANHUYEN_FK   "+   
					"\n          left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK    "+   
					"\n          left join VUNG v on v.PK_SEQ=kv.VUNG_FK   "+
					"   where  1=1 ";
					if(obj.getkhoId().length()>0)
				  	{
				  		sqlotc= "  and hd.kho_fk ='"+obj.getkhoId()+"'";
				  	}
					OTC+="\n "+sql +sqlotc;
					ETC=
					"\n          select 'ETC' as LoaiHD, v.TEN as vten,kv.TEN as kvTen,tt.TEN as ttTEN,qh.TEN as qhTen  , npp.ma as nppMa, npp.TEN as nppTen,ddkd.TEN as ddkdTen,kk.ten as tenkho  "+   
					"\n          ,	kh.maFAST as khMa,kh.TEN as khTen,kh.DIACHI as khDiaChi,hdETC.BVAT,hdETC.AVAT,hdETC.AVAT_CK,hdETC.BVAT_CK  ,hd.SOHOADON,hd.KYHIEU,hd.NGAYXUATHD  	   "+   
					"\n          from        "+   
					"\n          (       "+   
					"		select  HOADON_FK,npp_fk,KHACHHANG_fk,ddkd_fk,"+ 
					"			sum(soluong) as soluong, ( sum( soluong * dongia ) / sum(soluong) ) as dongia,    "+ 
					"			sum(ROUND( soluong * dongia,0))  as BVAT,( sum(ROUND(  ROUND( soluong * dongia,0) *thuexuat/100,0 ) )) as VAT,    "+
					"			 case when sum(ISNULL(ETC.tienvat,0))>0 then (sum(ETC.TIENVAT + ROUND( soluong * dongia,0))) else  sum(ROUND(  ROUND( soluong * dongia,0) *(1+ thuexuat/100),0 ) ) end as AVAT , sum(isnull(chietkhau,0)*(1+thuexuat/100)) as AVAT_CK, sum(ROUND(  isnull(thuexuat,0),0)) as BVAT_CK "+        
					"		from (   "+ 
					"			select  c.TIENVAT,c.HOADON_FK,a.npp_fk,a.KHACHHANG_fk,c.chietkhau,c.vat,"+ 
					"			(	select top(1) bb.ddkd_fk from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ    \n " +
					"						inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ   \n " +
					"				where cc.KHACHHANG_FK = a.KHACHHANG_FK  \n " +
					"			) as DDKD_FK ,"+
					"					case when c.donvitinh = e.donvi then c.soluong else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   "+ 
					"					case when c.donvitinh = e.donvi then c.dongia else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia, c.vat as thuexuat    "+ 
					"			from ERP_HOADONNPP a   "+ 
					"				inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk    "+ 
					"				inner join SANPHAM d on c.sanpham_fk = d.pk_seq   "+ 
					"				inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   "+ 
					"				where 1=1 and c.SOLUONG > 0 and a.trangthai not in ( 1 , 3, 5 )"+ 
					"				 and a.NgayXuatHD>='"+obj.gettungay()+"' "+ 
					"				and a.NgayXuatHD<='"+obj.getdenngay()+"'  ";
					if(obj.getnppId().length()>0)
					{
						ETC+=" and a.npp_fk='"+obj.getnppId()+"'";
					}
					if(obj.getCn1().equals("1"))
					{
					ETC+=
						"union all"+
						"			select c.TIENVAT, c.HOADON_FK,a.npp_fk,a.KHACHHANG_fk,c.chietkhau,c.vat,"+ 
						"			(	select top(1) bb.ddkd_fk from DAIDIENKINHDOANH aa inner join TUYENBANHANG bb on bb.DDKD_FK = aa.PK_SEQ    \n " +
						"						inner join KHACHHANG_TUYENBH cc on cc.TBH_FK = bb.PK_SEQ   \n " +
						"				where cc.KHACHHANG_FK = a.KHACHHANG_FK  \n " +
						"			) as DDKD_FK ,"+
						"					case when c.donvitinh = e.donvi then c.soluong else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   "+ 
						"					case when c.donvitinh = e.donvi then c.dongia else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia, c.vat as thuexuat    "+ 
						"			from ERP_HOADONNPP a   "+ 
						"				inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk    "+ 
						"				inner join SANPHAM d on c.sanpham_fk = d.pk_seq   "+ 
						"				inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   "+ 
						"				where 1=1 and c.SOLUONG > 0 and a.trangthai not in ( 1 , 3, 5 )"+ 
						"				 and a.NgayXuatHD>='"+obj.gettungay()+"' "+ 
						"				and a.NgayXuatHD<='"+obj.getdenngay()+"'  "+
					"		and exists   "+
					"			(select PK_SEQ from KHACHHANG where TrucThuoc_FK='"+obj.getnppId()+"' and TrucThuoc_FK is not null and PK_SEQ=a.khachhang_Fk)  ";
					}					
					ETC+=
					"		)ETC"+ 
					"		group by NPP_FK,KHACHHANG_FK,ddkd_fk,HOADON_FK"+ 
					"\n          )as hdETC       "+   
					"\n          inner join ERP_HOADONNPP hd on hd.PK_SEQ=hdETC.HOADON_FK    "+  
					"\n  inner join kho kk on hd.kho_fk=kk.pk_seq "+
					"\n          left join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ=hdETC.DDKD_FK    "+   
					"\n          inner join KHACHHANG kh on kh.PK_SEQ=hdETC.KHACHHANG_FK  "+   
					"\n          inner join NHAPHANPHOI npp on npp.PK_SEQ=hdETC.NPP_FK    "+   
					"\n          left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   "+   
					"\n          left join QUANHUYEN qh on qh.PK_SEQ=npp.QUANHUYEN_FK   "+   
					"\n          left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK    "+   
					"\n          left join VUNG v on v.PK_SEQ=kv.VUNG_FK 			      "+   
					"   where 1=1 ";
					if(obj.getkhoId().length()>0)
				  	{
				  		sqletc= "  and hd.kho_fk ='"+obj.getkhoId()+"'";
				  	}
					ETC+="\n "+sql +sqletc;
					
					ETC+=
					"\n UNION ALL  select 'ETC' as LoaiHD, v.TEN as vten,kv.TEN as kvTen,tt.TEN as ttTEN,qh.TEN as qhTen  , npp.ma as nppMa, npp.TEN as nppTen,ddkd.TEN as ddkdTen,kk.ten as tenkho  "+   
					"\n          ,	kh.maFAST as khMa,kh.TEN as khTen,kh.DIACHI as khDiaChi,hdETC.BVAT,hdETC.AVAT,hdETC.AVAT_CK,hdETC.BVAT_CK  ,hd.SOHOADON,hd.KYHIEU,hd.NGAYXUATHD  	   "+   
					"\n          from        "+   
					"\n          (       "+   
					"		select  HOADON_FK,npp_fk,KHACHHANG_fk,ddkd_fk,"+ 
					"			sum(soluong) as soluong, ( sum( soluong * dongia ) / sum(soluong) ) as dongia,    "+ 
					"			sum(ROUND( soluong * dongia,0))  as BVAT,( sum(ROUND(  ROUND( soluong * dongia,0) *thuexuat/100,0 ) )) as VAT,    "+
					"			case when sum(ISNULL(ETC.tienvat,0))>0 then (sum(ETC.TIENVAT + ROUND( soluong * dongia,0))) else  sum(ROUND(  ROUND( soluong * dongia,0) *(1+ thuexuat/100),0 ) ) end as AVAT, sum(isnull(chietkhau,0)*(1+thuexuat/100)) as AVAT_CK, sum(ROUND(  isnull(thuexuat,0),0)) as BVAT_CK "+        
					"		from (   "+ 
					"			select  c.TIENVAT,c.HOADON_FK,a.npp_fk,a.npp_dat_fk as KHACHHANG_fk,c.chietkhau,c.vat,NULL as ddkd_fk , "+ 
					"					case when c.donvitinh = e.donvi then c.soluong else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,   "+ 
					"					case when c.donvitinh = e.donvi then c.dongia else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia, c.vat as thuexuat    "+ 
					"			from ERP_HOADONNPP a   "+ 
					"				inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk    "+ 
					"				inner join SANPHAM d on c.sanpham_fk = d.pk_seq   "+ 
					"				inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq   "+ 
					"				where 1=1 and c.SOLUONG > 0 and a.trangthai not in ( 1 , 3, 5 )"+ 
					"				 and a.NgayXuatHD>='"+obj.gettungay()+"' "+ 
					"				and a.NgayXuatHD<='"+obj.getdenngay()+"'  ";
					if(obj.getnppId().length()>0)
					{
						ETC+=" and a.npp_fk='"+obj.getnppId()+"'";
					}
					ETC+=
					"		)ETC"+ 
					"		group by NPP_FK,KHACHHANG_fk,ddkd_fk,HOADON_FK"+ 
					"\n          )as hdETC       "+   
					"\n          inner join ERP_HOADONNPP hd on hd.PK_SEQ=hdETC.HOADON_FK    "+ 
					"\n			 inner join kho kk on hd.kho_fk=kk.pk_seq "+
					
					"\n          left join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ=hdETC.DDKD_FK    "+   
					"\n          inner join NHAPHANPHOI kh on kh.PK_SEQ=hdETC.KHACHHANG_FK  "+   
					"\n          inner join NHAPHANPHOI npp on npp.PK_SEQ=hdETC.NPP_FK    "+   
					"\n          left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   "+   
					"\n          left join QUANHUYEN qh on qh.PK_SEQ=npp.QUANHUYEN_FK   "+   
					"\n          left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK    "+   
					"\n          left join VUNG v on v.PK_SEQ=kv.VUNG_FK 			      "+   
					"   where 1=1 ";
					if(obj.getkhoId().length()>0)
				  	{
				  		sqletc= "  and hd.kho_fk ='"+obj.getkhoId()+"'";
				  	}
					ETC+="\n "+sql +sqletc;
		String HDKHAC =	"\n select 'HDKHAC' as LoaiHD, v.TEN as vten,kv.TEN as kvTen,tt.TEN as ttTEN,qh.TEN as qhTen  , "+
					"\n npp.ma as nppMa, npp.TEN as nppTen,'' as ddkdTen,'' as tenkho  "+
					"\n  ,	kh.maFAST as khMa,kh.TEN as khTen,kh.DIACHI as khDiaChi,a.BVAT,a.AVAT,0 as AVAT_CK,0 as BVAT_CK  ,a.SOHOADON,a.kyhieuhoadon,a.ngayhoadon  "+	   
					"\n  from        "+
					"\n	erp_hoadonphelieu as a  "+
					"\n  inner join KHACHHANG kh on kh.PK_SEQ=a.KHACHHANG_FK  "+
					"\n  inner join NHAPHANPHOI npp on npp.PK_SEQ=a.NPP_FK    "+
					"\n  left join TINHTHANH tt on tt.PK_SEQ=npp.TINHTHANH_FK   "+
					"\n  left join QUANHUYEN qh on qh.PK_SEQ=npp.QUANHUYEN_FK   "+
					"\n  left join KHUVUC kv on kv.PK_SEQ=npp.KHUVUC_FK    "+
					"\n	  left join VUNG v on v.PK_SEQ=kv.VUNG_FK 			         "+
					"\n  where 1=1 and a.trangthai = 1 "+ 
					"\n		and a.ngayhoadon>='"+obj.gettungay()+"' "+ 
					"\n		and a.ngayhoadon<='"+obj.getdenngay()+"'  "+ sql1;
	
		if(obj.getnppId().length()>0)
		{
			HDKHAC+=" and a.npp_fk='"+obj.getnppId()+"'";
		}
		
		query=OTC+" UNION ALL  "+ ETC+ " UNION ALL "+HDKHAC;
		obj.searchQuery_OTC(OTC+" ORDER BY DDKDTEN,KHMA");
		obj.searchQuery_ETC(ETC+"ORDER BY DDKDTEN,KHMA ");
		obj.searchQuery_HDKhac(HDKHAC+ "ORDER BY NPPTEN");
		  
		return query;
	}	
	
	private boolean CreatePivotTable(OutputStream out, IStockintransit obj, String query, String level)throws Exception 
	{
		boolean isFillData = false;
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();
		String chuoi=getServletContext().getInitParameter("path") + "\\DoanhSoTT.xlsm";
		fstream = new FileInputStream(chuoi);		
		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
		CreateHeader(workbook,obj);
		isFillData = FillData(workbook, obj, query);
		if (!isFillData)
		{
			if(fstream != null) 
				fstream.close();
			return false;
		}
		workbook.save(out);
		fstream.close();
		return true;	
	}
	
	private void CreateHeader(Workbook workbook, IStockintransit obj)throws Exception
	{	
 		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);
	   	
	    cells.setRowHeight(0, 20.0f);
	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 	        
	    
	    String tieude = "BÁO CÁO DOANH SỐ BÁN HÀNG THEO NGÀY";
	    if(obj.getFromMonth().length() > 0)
	    	tieude = "BÁO CÁO DOANH SỐ BÁN HÀNG THEO THÁNG";
	    ReportAPI.getCellStyle(cell,Color.RED, true, 14, tieude);
	    
	    String message = "";
			cells.setRowHeight(2, 18.0f);
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);   

	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("A4");
	    
	    if(obj.getFromMonth() == "")
	    	ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Từ ngày : " + obj.gettungay() + "" );
	    else
	    	ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Từ tháng : " + obj.getFromMonth() + "" );
	    ;
	    cells.setRowHeight(3, 18.0f);
	    cell = cells.getCell("B4"); 
	    if(obj.getFromMonth() == "")
	    	ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Đến ngày : " + obj.getdenngay() + "" );
	    else
	    	ReportAPI.getCellStyle(cell,Color.NAVY,true, 9, "Đến tháng : " + obj.getToMonth() + "" );
		
	    cells.setRowHeight(4, 18.0f);
	    cell = cells.getCell("A5");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày báo cáo: " + ReportAPI.NOW("yyyy-MM-dd"));
	    
	    cells.setRowHeight(5, 18.0f);
	    cell = cells.getCell("A6");
	    ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Được tạo bởi:  " + obj.getuserTen());
	    		
		
		cell = cells.getCell("FA1");		cell.setValue("KenhBanHang");
		cell = cells.getCell("FB1");		cell.setValue("DonViKinhDoanh");
		cell = cells.getCell("FC1");		cell.setValue("Mien");
		cell = cells.getCell("FD1");		cell.setValue("Vung");
		cell = cells.getCell("FE1");		cell.setValue("TinhThanh");
		cell = cells.getCell("FF1");		cell.setValue("QuanHuyen");
		cell = cells.getCell("FG1");		cell.setValue("MaCN/DT");
		cell = cells.getCell("FH1");		cell.setValue("CN/DT");
		cell = cells.getCell("FI1");		cell.setValue("TrinhDuocVien");
		cell = cells.getCell("FJ1");		cell.setValue("NgayXuatHD");
		cell = cells.getCell("FK1");		cell.setValue("SoHD");
		cell = cells.getCell("FL1");		cell.setValue("DoanhSo");		
		cell = cells.getCell("FM1");		cell.setValue("ChietKhau");
		cell = cells.getCell("FN1");		cell.setValue("DoanhThu");
		
		cell = cells.getCell("FO1");		cell.setValue("MaKhachHang");
		cell = cells.getCell("FP1");		cell.setValue("TenKhachHang");
		cell = cells.getCell("FQ1");		cell.setValue("DiaChi");
		cell = cells.getCell("FR1");		cell.setValue("Kho");
		
	}
	
	private boolean FillData(Workbook workbook, IStockintransit obj, String query) throws Exception{
		dbutils db = new dbutils();
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);

		Cells cells = worksheet.getCells();		
		ResultSet rs = db.get(query);
		int i = 2;
		
		if (rs != null) {
			try 
			{
				Cell cell = null;

				while (rs.next()) 
				{					
					
					cell = cells.getCell("FA" + Integer.toString(i));	cell.setValue(rs.getString("LoaiHD"));
					cell = cells.getCell("FB" + Integer.toString(i));	cell.setValue("");
					cell = cells.getCell("FC" + Integer.toString(i));	cell.setValue(rs.getString("vTen"));
					cell = cells.getCell("FD" + Integer.toString(i));	cell.setValue(rs.getString("kVTen"));
					cell = cells.getCell("FE" + Integer.toString(i));	cell.setValue(rs.getString("ttTen"));
					cell = cells.getCell("FF" + Integer.toString(i));	cell.setValue(rs.getString("qhTen"));
					cell = cells.getCell("FG" + Integer.toString(i));	cell.setValue(rs.getString("nppMa"));
					cell = cells.getCell("FH" + Integer.toString(i));	cell.setValue(rs.getString("nppTen"));
					cell = cells.getCell("FI" + Integer.toString(i));	cell.setValue( rs.getString("ddkdTen")==null?" ":rs.getString("ddkdTen"));
					cell = cells.getCell("FJ" + Integer.toString(i));	cell.setValue(rs.getString("ngayxuatHd"));
					cell = cells.getCell("FK" + Integer.toString(i));	cell.setValue(rs.getString("sohoadon"));
					
					cell = cells.getCell("FL" + Integer.toString(i));	cell.setValue( Math.round( rs.getDouble("avat")));
					cell = cells.getCell("FM" + Integer.toString(i));	cell.setValue( Math.round(rs.getDouble("avat_ck")));
					cell = cells.getCell("FN" + Integer.toString(i));	cell.setValue( Math.round( rs.getDouble("avat")-rs.getDouble("avat_ck")));
			
					cell = cells.getCell("FO" + Integer.toString(i));	cell.setValue( rs.getString("khMa")==null?" ":rs.getString("khMa"));
					cell = cells.getCell("FP" + Integer.toString(i));	cell.setValue( rs.getString("khTen")==null?" ":rs.getString("khTen"));
					cell = cells.getCell("FQ" + Integer.toString(i));	cell.setValue( rs.getString("khDiaChi")==null?" ":rs.getString("khDiaChi"));
					cell = cells.getCell("FR" + Integer.toString(i));	cell.setValue( rs.getString("Tenkho")==null?" ":rs.getString("tenkho"));
							
					++i;					
				}

				if (rs != null) rs.close();
				
				if(db != null) db.shutDown();
				
				if(i==2){					
					throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
				}
				  
			}catch(Exception ex){
				ex.printStackTrace();
				throw new Exception(ex.getMessage());
			}
		}else{
			return false;
		}		
		return true;
	}	
		
	private String setQuery_HoaDon(HttpServletRequest request, IStockintransit obj)
  {
		String condition ="";
		
		if(obj.gettungay().length()>0)
		{
			condition+=" and hd.NgayXuatHD>='"+obj.gettungay()+"'";
		}
		
		if(obj.getdenngay().length()>0)
		{
			condition+=" and hd.NgayXuatHD <='"+obj.getdenngay()+"'";
		}
		
		if(obj.getnppId().length()>0)
		{
			condition+=" and hd.npp_fk ='"+obj.getnppId()+"'";
		}
		
		if(obj.getTrangthai().length()>0)
		{
			condition+=" and hd.trangthai='"+obj.getTrangthai()+"'";
		}else 
		{
			condition+=" and hd.trangthai not in (1,3,5) ";
		}
		
		if(obj.getTtId().length()>0)
		{
			condition += " and tt.PK_SEQ ="+obj.getTtId();
		}
		
		if(obj.getDdkd().length()>0)
		{
			condition+=
					" and hd.pk_seq in "+
				  "   (		select  aa.hoadon_fk   \n "+      
				  "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
				  "   		where bb.ddkd_fk='"+obj.getDdkd()+"' )  \n ";
					
		}
		
		if(obj.getkhoId().length()>0)
		{
			condition += 
					" and hd.pk_seq in 	           "+
					"   (		select  aa.hoadon_fk   \n "+      
					"   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
					"   		where bb.kho_fk='"+obj.getkhoId()+"' )  \n ";
		}
		
		String condition_ETC ="";
		
		if(obj.gettungay().length()>0)
		{
			condition_ETC+=" and A.NgayXuatHD>='"+obj.gettungay()+"'";
		}
		
		if(obj.getdenngay().length()>0)
		{
			condition_ETC+=" and A.NgayXuatHD <='"+obj.getdenngay()+"'";
		}
		
		if(obj.getnppId().length()>0)
		{
			condition_ETC+=" and A.npp_fk ='"+obj.getnppId()+"'";
		}
		
		if(obj.getTrangthai().length()>0)
		{
			condition_ETC +=" and A.trangthai='"+obj.getTrangthai()+"'";
		}else 
		{
			condition_ETC +=" and A.trangthai not in (1,3,5) ";
		}
		
		if(obj.getTtId().length()>0)
		{
			condition_ETC += " and tt.PK_SEQ ="+obj.getTtId();
		}
		
		if(obj.getDdkd().length()>0)
		{
			condition_ETC +=
					" and A.pk_seq in "+
				  "   (		select  aa.hoadonnpp_fk   \n "+      
				  "   		from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
				  "   		where bb.ddkd_fk='"+obj.getDdkd()+"' )  \n ";
					
		}
		
		if(obj.getkhoId().length()>0)
		{
			condition_ETC += 
					   " and A.pk_seq in 	(           "+
					   "  		select  aa.hoadonnpp_fk           "+ 
					   "  		from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK "+          
					   "   		where bb.kho_fk='"+obj.getkhoId()+"'   \n "+
					   "  	)  ";
		}
		
		String query="";
			query=
				 " select  HD.TRANGTHAI,'OTC' as HD,   \n "+      
			   "   	hdsp.HOADON_FK,hd.NGAYXUATHD,hd.SOHOADON,hd.KYHIEU,npp.MA as nppMa,npp.TEN as nppTen,kh.maFAST as khMA,   \n "+      
			   "   	KH.TEN as khTEN,kh.CHUCUAHIEU,kh.DIACHI as khDiaChi,kh.MASOTHUE as khMST,sp.MA as spMa,sp.TEN as spTEN,   \n "+      
			   "   	hdsp.donvitinh as spDonVi,hdsp.SOLUONG,hdsp.DONGIA ,(ROUND( hdsp.soLuong*hdsp.DONGIA,0)) as ThanhTien,hdsp.VAT as VAT   \n "+      
			   "   	,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as ddkdTEN,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join LOAIKHACHHANG cc on cc.PK_SEQ=bb.LOAIKHACHHANG   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as LoaiKH,0 as LoaiHD,   \n "+
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as KHO "+
			   "   from HOADON hd inner join HOADON_SP hdsp on hdsp.HOADON_FK=hd.PK_SEQ   \n "+      
			   "   	inner join NHAPHANPHOI npp on npp.PK_SEQ=hd.NPP_FK   \n "+ 
			   "    LEFT join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
			   "   	inner join KHACHHANG kh on kh.PK_SEQ=hd.KHACHHANG_FK   \n "+      
			   "   	inner join SANPHAM sp on sp.PK_SEQ=hdsp.SANPHAM_FK   \n "+      
			   "   where hd.LOAIHOADON=0  "+condition+"	   \n "+
			   "   union all   \n "+      
			   "      \n "+      
			   "   select  HD.TRANGTHAI, 'OTC' as HD,   \n "+      
			   "   	hdsp.hoadon_fk,hd.NGAYXUATHD,hd.SOHOADON,hd.KYHIEU,npp.MA as nppMa,npp.TEN as nppTen,kh.maFAST as khMA,   \n "+      
			   "   	KH.TEN as khTEN,kh.CHUCUAHIEU,kh.DIACHI as khDiaChi,kh.MASOTHUE as khMST,sp.MA as spMa,sp.TEN as spTEN,   \n "+      
			   "   		'' as spDonVi,0 as SoLuong,0 as DonGia ,(-1)*ROUND(hdsp.chietkhau,0) as ThanhTien ,hdsp.thueVAT as VAT,   \n "+      
			   "   		(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as ddkdTEN,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join LOAIKHACHHANG cc on cc.PK_SEQ=bb.LOAIKHACHHANG   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as LoaiKH,0 as LoaiHD ,  \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as KHO "+
			   "   from HOADON hd inner join HOADON_CHIETKHAU hdsp on hdsp.HOADON_FK=hd.PK_SEQ   \n "+      
			   "   	inner join NHAPHANPHOI npp on npp.PK_SEQ=hd.NPP_FK   \n "+   
			   "   inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
			   "   	inner join KHACHHANG kh on kh.PK_SEQ=hd.KHACHHANG_FK   \n "+      
			   "   	inner join loaick sp on sp.Ma=hdsp.diengiai   \n "+      
			   "   where hdsp.hienthi = '1' and hd.LOAIHOADON=0  "+condition+"	   \n "+ 
			   "      \n "+      
			   "   union all    \n "+      
			   "   select  HD.TRANGTHAI, 'OTC' as HD,   \n "+      
			   "   	hdsp.HOADON_FK,hd.NGAYXUATHD,hd.SOHOADON,hd.KYHIEU,npp.MA as nppMa,npp.TEN as nppTen,kh.maFAST as khMA,   \n "+      
			   "   	KH.TEN as khTEN,kh.CHUCUAHIEU,kh.DIACHI as khDiaChi,kh.MASOTHUE as khMST,sp.MA as spMa,sp.TEN as spTEN,   \n "+      
			   "   	dvdl.DONVI as spDonVi,hdsp.SOLUONG,hdsp.DONGIA ,ROUND(hdsp.soLuong*hdsp.DONGIA,0) as ThanhTien,hdsp.VAT as VAT,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as ddkdTEN,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join LOAIKHACHHANG cc on cc.PK_SEQ=bb.LOAIKHACHHANG   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as LoaiKH,1 as LoaiHD,   \n "+     
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as KHO "+
			   "   from HOADON hd inner join HOADON_CTKM_TRAKM hdsp on hdsp.HOADON_FK=hd.PK_SEQ   \n "+      
			   "   	inner join NHAPHANPHOI npp on npp.PK_SEQ=hd.NPP_FK   \n "+ 
			   "   	inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
			   "   	inner join KHACHHANG kh on kh.PK_SEQ=hd.KHACHHANG_FK   \n "+      
			   "   	inner join SANPHAM sp on sp.PK_SEQ=hdsp.SANPHAM_FK   \n "+      
			   "   	inner join DONVIDOLUONG dvdl on dvdl.PK_SEQ=sp.DVDL_FK   \n "+      
			   "   where hd.LOAIHOADON=1  "+condition+"  \n "+
			   "      \n "+      
			   "   UNION ALL   \n "+      
			   "      \n "+      
			   "   select  A.TRANGTHAI,'ETC' as HD,c.hoadon_fk,a.ngayxuatHD,a.sohoadon,a.kyhieu,npp.ma as nppMa,npp.TEN as nppTEN,   \n "+      
			   "   	kh.maFAST as khMA,KH.TEN AS khTEN,kh.ChuCuaHieu,kh.diachi as khDIACHI,   \n "+      
			   "   	KH.MASOTHUE as khMST ,d.ma as spMA,d.ten as spTEN,e.donvi as spDONVI,   \n "+      
			   "   	case when c.donvitinh = e.donvi then c.soluong        \n "+      
			   "   	else c.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as soluong,            \n "+      
			   "   	case when c.donvitinh = e.donvi then c.dongia           \n "+      
			   "   	else c.dongia * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = c.sanpham_fk and DVDL2_FK = ( select pk_seq from DONVIDOLUONG where donvi = c.donvitinh ) and DVDL1_FK = d.DVDL_FK ) end as dongia, ROUND( c.SOLUONG*c.DONGIA,0) -ROUND( ISNULL(c.chietkhau,0),0 ) as ThanhTien,  c.vat as VAT ,   \n "+      
			   "   (   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADONNPP_FK=a.pk_Seq   \n "+      
			   "   	)as ddkdTEN	,loai.ten as LoaiKH ,0 as loaiHD ,  \n "+    
			   "	(           "+
			   "  		select top(1) cc.TEN           "+ 
			   "  		from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK "+          
			   "  			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK "+          
			   "  		where aa.HOADONNPP_FK=a.PK_SEQ "+          
			   "  	)as KHO  "+ 
			   "   	from ERP_HOADONNPP a            \n "+      
			   "   		inner join ERP_HOADONNPP_SP c on a.pk_seq = c.hoadon_fk            \n "+      
			   "   		inner join SANPHAM d on c.sanpham_fk = d.pk_seq            \n "+      
			   "   		inner join DONVIDOLUONG e on d.DVDL_FK = e.pk_seq     \n "+      
			   "   		inner join KHACHHANG kh on kh.PK_SEQ=a.KHACHHANG_FK    \n "+      
			   "   		inner join NHAPHANPHOI npp on npp.PK_SEQ=a.NPP_FK     \n "+   
			   "    	inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
			   "   		left join LOAIKHACHHANG loai on loai.pk_seq=kh.XuatKhau       \n "+      
			   "   	where 1=1 "+condition_ETC+"      \n ";
		System.out.println("__HD_Ban_Trong_Ky__"+query);
	  return query;
  }
	
	private void CreatePivotTable_HoaDon(OutputStream out,IStockintransit obj, String query) throws Exception
	{
		try 
		{
			FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BcHoaDonBanTrongKy.xlsm");
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			CreateHeader_HoaDon(workbook,obj); 
			FillData_HoaDon(workbook, query, obj);			
			workbook.save(out);
			fstream.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}

	private void CreateHeader_HoaDon(Workbook workbook,IStockintransit obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
    Worksheet worksheet = worksheets.getSheet(0);	    
    worksheet.setName("Sheet1");
    Cells cells = worksheet.getCells();	 
    
    cells.setRowHeight(0, 20.0f);	    
    Cell cell = cells.getCell("A1");	
    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Báo Cáo Hóa Đơn Bán Trong Kỳ");
    
    cell = cells.getCell("A3");
    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
    cell = cells.getCell("A4");
    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
    cell = cells.getCell("A5");
    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());
    
	  cell = cells.getCell("FA1");		cell.setValue("KenhBanHang");				
		cell = cells.getCell("FB1");		cell.setValue("MaSoHoaDon");	
		cell = cells.getCell("FC1");		cell.setValue("NgayHoaDon");		
		cell = cells.getCell("FD1");		cell.setValue("SoHoaDon");			
		cell = cells.getCell("FE1");		cell.setValue("KyHieu");			
		cell = cells.getCell("FF1");		cell.setValue("CN/DT");			
		cell = cells.getCell("FG1");		cell.setValue("MaKH");					
		cell = cells.getCell("FH1");		cell.setValue("TenKhachHang");  		
		cell = cells.getCell("FI1");		cell.setValue("ChuCuaHieu");
		cell = cells.getCell("FJ1");		cell.setValue("DiaChi");
		cell = cells.getCell("FK1");		cell.setValue("MST");
		cell = cells.getCell("FL1");		cell.setValue("MaSanPham");
		cell = cells.getCell("FM1");		cell.setValue("TenSanPham");
		cell = cells.getCell("FN1");		cell.setValue("DonViTinh");
		cell = cells.getCell("FO1");		cell.setValue("SoLuong");
		cell = cells.getCell("FP1");		cell.setValue("DonGia");
		cell = cells.getCell("FQ1");		cell.setValue("ThanhTien");
		cell = cells.getCell("FR1");		cell.setValue("LoaiHoaDon");
		cell = cells.getCell("FS1");		cell.setValue("TrangThai");
		cell = cells.getCell("FT1");		cell.setValue("NVBH");
		cell = cells.getCell("FU1");		cell.setValue("KhoXuat");
	}

	private void FillData_HoaDon(Workbook workbook, String query, IStockintransit obj) throws Exception
	{
		ResultSet rs = null;
		dbutils db = new dbutils();
		try
		{	
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();
			
			for(int i=0;i<15;++i)
			{
		    	cells.setColumnWidth(i, 15.0f);	    	
		    }	
			 rs = db.get(query);
			int index = 2;
		    Cell cell = null;
			while (rs.next())
			{
			
				double ptThue= (1+rs.getDouble("VAT")/100);
				cell = cells.getCell("FA" + String.valueOf(index));		cell.setValue(rs.getString("HD"));			
				cell = cells.getCell("FB" + String.valueOf(index));		cell.setValue(rs.getString("HoaDon_fk"));
				cell = cells.getCell("FC" + String.valueOf(index));		cell.setValue(rs.getString("NgayXUATHD"));
				cell = cells.getCell("FD" + String.valueOf(index));		cell.setValue(rs.getString("SoHoaDon"));
				cell = cells.getCell("FE" + String.valueOf(index));		cell.setValue(rs.getString("KyHieu"));
				cell = cells.getCell("FF" + String.valueOf(index));		cell.setValue(rs.getString("nppTEN"));
				cell = cells.getCell("FG" + String.valueOf(index));		cell.setValue(rs.getString("khMA"));
				cell = cells.getCell("FH" + String.valueOf(index));		cell.setValue(rs.getString("khTEN"));
				cell = cells.getCell("FI" + String.valueOf(index));		cell.setValue(rs.getString("CHUCUAHIEU"));
				
				cell = cells.getCell("FJ" + String.valueOf(index));		cell.setValue(rs.getString("KhDIACHI"));
				cell = cells.getCell("FK" + String.valueOf(index));		cell.setValue(rs.getString("KHMST"));
				cell = cells.getCell("FL" + String.valueOf(index));		cell.setValue(rs.getString("spMA"));
				cell = cells.getCell("FM" + String.valueOf(index));		cell.setValue(rs.getString("spTEN"));
				cell = cells.getCell("FN" + String.valueOf(index));		cell.setValue(rs.getString("spDONVI"));
				cell = cells.getCell("FO" + String.valueOf(index));		cell.setValue(rs.getDouble("sOLUONG"));
				cell = cells.getCell("FP" + String.valueOf(index));		cell.setValue( Math.round( rs.getDouble("DONGIA")*ptThue));
				cell = cells.getCell("FQ" + String.valueOf(index));		cell.setValue(Math.round(rs.getDouble("THANHTIEN")*ptThue));
				
				cell = cells.getCell("FR" + String.valueOf(index));		cell.setValue(rs.getString("LOAIHD").equals("0")?"BÁN":"KM");
				
				String TrangThai="";
				if(rs.getInt("Trangthai")==1)
				{
					TrangThai="Chờ xác nhận";
				}else 	
				if(rs.getInt("Trangthai")==2)
				{
					TrangThai="Đã xác nhận";
				}else if(rs.getInt("Trangthai")==3)
				{
					TrangThai="Đã Xóa";
				}
				else if(rs.getInt("Trangthai")==4)
				{
					TrangThai="Đã In";
				}else if(rs.getInt("Trangthai")==5)
				{
					TrangThai="Đã Hủy";
				}
				
				
				cell = cells.getCell("FS" + String.valueOf(index));		cell.setValue(TrangThai);
				cell = cells.getCell("FT" + String.valueOf(index));		cell.setValue(rs.getString("ddkdTEN"));
				
				cell = cells.getCell("FU" + String.valueOf(index));		cell.setValue(rs.getString("KHO"));

				index++;
			}
			if(rs!=null){
				rs.close();
			}	
			
			ReportAPI.setHidden(workbook,14);
			 
		
		    			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(rs != null)
			{
				rs.close();
			}
			ex.printStackTrace();
			if(db != null)
				db.shutDown();
			
			throw new Exception(ex.getMessage());
		}
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String getTotal(HttpServletRequest request, IStockintransit obj, String view)
	{	
		 String	query=this.setQuery("", obj, "");
				query=
				"	 select sum(BVAT) AS BVAT,SUM(AVAT) AS AVAT,SUM(AVAT_CK) AS AVAT_CK, "+
				"		SUM(BVAT_CK) AS BVAT_CK,SUM(AVAT)-SUM(AVAT_CK) AS DOANHTHU from( "+ query+" )as db       ";   
				return query;
	}
}