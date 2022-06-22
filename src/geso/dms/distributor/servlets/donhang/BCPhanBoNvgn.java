package geso.dms.distributor.servlets.donhang;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import com.sun.corba.se.impl.javax.rmi.CORBA.Util;

public class BCPhanBoNvgn extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public BCPhanBoNvgn() {
        super();
    } 
    
    NumberFormat formatter = new DecimalFormat("#,###,###");
    NumberFormat formatter2 = new DecimalFormat("#,###,###.###");
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();	    	   

	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String view = request.getParameter("view");
	    if(view == null)
	    	view = "";
	    
	    System.out.println("userid: "+userId+" -- view: "+view);
	    obj = new Stockintransit();
	    obj.setuserId(userId);
	    if(!view.equals("TT"))
	    	obj.getNppInfo();
	    System.out.println("NPPID: "+obj.getnppId());
	    //obj.setLoaiMenu(view);
	    obj.initBCChiPhiKhuyenMai();

	    	
	    //obj.setCongtyId(congtyId);
	    //obj.createRsBC_GiaThanh();
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/BCPhanBoNvgn.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Utility util = new Utility();
		IStockintransit obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    String userId = request.getParameter("userId");
	    String action = request.getParameter("action");
		if (action == null){
			action = "";
		}
		
		String view = request.getParameter("view");
		if(view == null)
		view = "";
		    
		    
	    obj = new Stockintransit();
	    obj.setuserId(userId);
	    
	    //obj.setLoaiMenu(view);
	    if(view.equals("NPP"))
	    	obj.getNppInfo();
		    
	   	
	    String tungay = request.getParameter("tungay");
	    if(tungay == null)
	    	tungay = "";
	    obj.settungay(tungay);
	    
	    String denngay = request.getParameter("denngay");
	    if(denngay == null)
	    	denngay = "";
	    obj.setdenngay(denngay);
	    
	    String vungId = request.getParameter("vungId");
	    if(vungId == null)
	    	vungId = "";
	    obj.setvungId(vungId);
	    
	    String kvId = request.getParameter("kvId");
	    if(kvId == null)
	    	kvId = "";
	    obj.setkhuvucId(kvId);
	    
	    String npp = request.getParameter("nppId");
	    if(npp.equals(""))
	    	npp = null;
	    obj.setnppId(npp);
	    
	    String khId = request.getParameter("khId");
	    if(khId.equals(""))
	    	khId = null;
	    obj.setkhId(khId);
	    
		session.setAttribute("obj", obj);
	    
		if( action.equals("taobaocao"))
		{
	    	try 
	    	{	
	    		OutputStream out = response.getOutputStream(); 
	    		
				response.setContentType("application/xlsm");
	    		response.setHeader("Content-Disposition", "attachment; filename=BCPhanBoNvgn.xlsm");
	
				TongHopChiPhiSX(out, obj);
			} 
	    	catch (Exception e) 
	    	{ 
	    		e.printStackTrace();
	    		System.out.println("Exception: " + e.getMessage()); 
	    	}
		}
		else
		{
			obj.initBCChiPhiKhuyenMai();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BCPhanBoNvgn.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	
	private void TongHopChiPhiSX(OutputStream out, IStockintransit obj) throws Exception
    {   
		FileInputStream fstream = null;
		Workbook workbook = new Workbook();

		fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCGiaoHang.xlsm");

		workbook.open(fstream);
		workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

		BCTongHopChiPhiSX(workbook, obj);

		workbook.save(out);
		fstream.close();
		
    }

	private void BCTongHopChiPhiSX(Workbook workbook, IStockintransit obj) 
	{ 
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	   	   
	    //worksheet.setName("Sheet1");
	    Cells cells = worksheet.getCells();
		
	    Style style;
	    Font font = new Font();
	    font.setName("Times New Roman");
	    font.setColor(Color.RED);//mau chu
	    font.setSize(16);// size chu
	   	font.setBold(true);

	    Cell cell = cells.getCell("A1");
	    style = cell.getStyle();
	    style.setFont(font); 
	    style.setHAlignment(TextAlignmentType.LEFT);// canh le cho chu 
	    
	    //create data
	    dbutils db = new dbutils();

	    cell = cells.getCell("A3");
	    cell.setValue("Từ ngày: " + obj.gettungay() + " - Đến ngày: " + obj.getdenngay() );
	    
	    cell = cells.getCell("A4");
	    cell.setValue( "Ngày tạo " + this.getDateTime());
	    String condition = "";
	    String condition_chitiet = "";
	    
	    if (obj.getLoaiMenu().equals("0")) {
	    	condition += "\n and ddkd.pk_seq in ("+Utility.PhanQuyenDDKD(obj.getuserId())+")";
	    	condition_chitiet += "\n and ddkd.pk_seq in ("+Utility.PhanQuyenDDKD(obj.getuserId())+")";
	    }
	    
	    if (obj.gettungay() != null) { 
		    if (obj.gettungay().length() > 0) {
		    	condition += "\n and a.ngaynhap >= convert(varchar,'"+obj.gettungay()+"',21)";
		    	condition_chitiet += "\n and a.ngaynhap >= convert(varchar,'"+obj.gettungay()+"',21)";
		    	//condition_chitiet += "\n and (select max(ngayphanbo) from PhanBo_NVGN aa inner join PhanBo_NVGN_CT b on aa.pk_seq = b.phanbo_fk and b.donhang_fk = a.pk_seq) " +
		    	//					 "\n >= convert(varchar,'"+obj.gettungay()+"',21)";
		    }
	    }
	    
	    if (obj.getdenngay() != null) {
		    if (obj.getdenngay().length() > 0) {
		    	condition += "\n and a.ngaynhap <= convert(varchar,'"+obj.getdenngay()+"',21)";
		    	condition_chitiet += "\n and a.ngaynhap <= convert(varchar,'"+obj.getdenngay()+"',21)";
		    	//condition_chitiet += "\n and (select max(ngayphanbo) from PhanBo_NVGN aa inner join PhanBo_NVGN_CT b on aa.pk_seq = b.phanbo_fk and b.donhang_fk = a.pk_seq) " +
		    	//					 "\n <= convert(varchar,'"+obj.getdenngay()+"',21)";
		    }
	    }
	    
	    if (obj.getvungId() != null) {
		    if (obj.getvungId().length() > 0) {
		    	condition += "\n and v.pk_seq = "+obj.getvungId();
		    	condition_chitiet += "\n and v.pk_seq = "+obj.getvungId();
		    }
	    }
	    
	    if (obj.getkhuvucId() != null) {
		    if (obj.getkhuvucId().length() > 0) {
		    	condition += "\n and kv.pk_seq = "+obj.getkhuvucId();
		    	condition_chitiet += "\n and kv.pk_seq = "+obj.getkhuvucId();
		    }
	    }
	    
	    if (obj.getkhId() != null) {
		    if (obj.getkhId().length() > 0) {
		    	condition += "\n and c.pk_seq = "+obj.getkhId();
		    	condition_chitiet += "\n and c.pk_seq = "+obj.getkhId();
		    }
	    }
	    
	    if (obj.getnppId() != null) {
		    if (obj.getnppId().length()>0)
		    {
		    	condition += "\n and a.npp_fk ="+obj.getnppId();
		    	condition_chitiet += "\n and a.npp_fk ="+obj.getnppId();
		    }
	    }
	    
	    System.out.println("NPP: "+obj.getnppId());
	    //if  (obj.getnppId().length() > 0)
	    //	condition = " and npp_fk in "+u.quyen_npp(obj.getuserId())+"  ";
	    
	    String query = "\n select DENSE_RANK() OVER (ORDER BY dhid) STT,* from ( "+
	    	
	    	//Hàng bán
	    	"\n select pbct.phanbo_fk,a.pk_seq dhid, a.ngaynhap, (select ngayphanbo from phanbo_NVGN where pk_seq = pbct.PHANBO_FK)ngayphanbo,c.mafast khma, c.diachigiaohang khdiachi, "+
	    	"\n c.ten khten, d.ten spten, convert(varchar,ctsp.soluongdh) spsoluongdh,convert(varchar,ctsp.soluong) spsoluonggiao, convert(varchar,b.giamua) spgiamua, convert(varchar,b.thanhtien) spthanhtien, nv1.ten NVGNDH, nv2.ten NVGNTT, "+
	    	"\n  '' tgt, convert(varchar,ctsp.soluong) SLGIAONHAN, (select ngayphanbo from phanbo_NVGN where pk_seq = pbct.PHANBO_FK)npb, isnull(ctsp.thudu,0) thudu "+
	    	"\n  from donhang a inner join donhang_sanpham b on a.pk_seq = b.donhang_fk "+
	    	"\n  inner join khachhang c on c.pk_seq = a.khachhang_fk "+
	    	"\n  inner join sanpham d on d.pk_seq = b.sanpham_fk "+
	    	"\n  inner join nhanviengiaonhan nv1 on nv1.pk_seq = a.nvgn_fk "+
	    	"\n  inner join phanbo_NVGN_CT pbct on pbct.donhang_fk = a.pk_seq and pbct.DONHANG_FK = b.DONHANG_FK  "+
	    	"\n   inner join nhanviengiaonhan nv2 on nv2.pk_seq = pbct.nvgn_fk "+
	    	"\n inner join phanbo_nvgn_CTSP ctsp on ctsp.DONHANG_FK = a.PK_SEQ and  ctsp.DONHANG_FK = pbct.DONHANG_FK and ctsp.sanpham_fk = d.pk_seq and ctsp.PHANBO_FK = pbct.PHANBO_FK"+
			"\n		LEFT JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ=c.NPP_FK  "+
			"\n		inner join daidienkinhdoanh ddkd on ddkd.pk_seq in ( select DDKD_FK from tuyenbanhang where NPP_FK = c.npp_fk and pk_seq in   "+
			"\n 		(select tbh_fk from KHACHHANG_TUYENBH where khachhang_fk = c.pk_seq)  ) and ddkd.TRANGTHAI = 1 "+
			"\n 	left join ddkd_gsbh on ddkd_gsbh.ddkd_fk = ddkd.pk_seq "+
			"\n 	left join gsbh_khuvuc on gsbh_khuvuc.gsbh_fk = ddkd_gsbh.gsbh_fk " +
			"\n 	left join khuvuc kv on kv.pk_seq = gsbh_khuvuc.khuvuc_fk "+
			"\n 	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK    "+ 
	    	"\n  where 1=1  and a.trangthai !=2 and a.trangthai != 0 and isnull(ctsp.iskhuyenmai,0) = 0 "+condition+
	    	
	    	"\n union all " +
	    	
	    	//Hàng km
	    	"\n select pbct.phanbo_fk,a.pk_seq dhid, a.ngaynhap, (select ngayphanbo from phanbo_NVGN where pk_seq = pbct.PHANBO_FK)ngayphanbo,c.mafast khma, c.diachigiaohang khdiachi,  " +
	    	"\n  c.ten khten, d.ten spten, convert(varchar,ctsp.soluongdh) spsoluongdh,convert(varchar,ctsp.soluong) spsoluonggiao,  '0' spgiamua, '0' spthanhtien, nv1.ten NVGNDH, nv2.ten NVGNTT,  " +
	    	"\n   '' tgt, convert(varchar,ctsp.soluong) SLGIAONHAN, (select ngayphanbo from phanbo_NVGN where pk_seq = pbct.PHANBO_FK)npb, isnull(ctsp.thudu,0) thudu  " +
	    	"\n   from donhang a inner join DONHANG_CTKM_TRAKM b on a.pk_seq = b.donhangid  " +
	    	"\n   inner join khachhang c on c.pk_seq = a.khachhang_fk  " +
	    	"\n   inner join sanpham d on d.ma = b.spma  " +
	    	"\n   inner join nhanviengiaonhan nv1 on nv1.pk_seq = a.nvgn_fk  " +
	    	"\n   inner join phanbo_NVGN_CT pbct on pbct.donhang_fk = a.pk_seq and pbct.DONHANG_FK = b.donhangid   " +
	    	"\n   inner join nhanviengiaonhan nv2 on nv2.pk_seq = pbct.nvgn_fk  " +
	    	"\n  inner join phanbo_nvgn_CTSP ctsp on ctsp.DONHANG_FK = a.PK_SEQ and ctsp.sanpham_fk = d.pk_seq and ctsp.PHANBO_FK = pbct.PHANBO_FK " +
	    	"\n 		LEFT JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ=c.NPP_FK   " +
	    	"\n 		inner join daidienkinhdoanh ddkd on ddkd.pk_seq in ( select DDKD_FK from tuyenbanhang where NPP_FK = c.npp_fk and pk_seq in    " +
	    	"\n  		(select tbh_fk from KHACHHANG_TUYENBH where khachhang_fk = c.pk_seq)  ) and ddkd.TRANGTHAI = 1  " +
	    	"\n  	left join ddkd_gsbh on ddkd_gsbh.ddkd_fk = ddkd.pk_seq  " +
	    	"\n  	left join gsbh_khuvuc on gsbh_khuvuc.gsbh_fk = ddkd_gsbh.gsbh_fk  " +
	    	"\n  	left join khuvuc kv on kv.pk_seq = gsbh_khuvuc.khuvuc_fk  " +
	    	"\n  	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK     " +
	    	"\n   where 1=1  and a.trangthai !=2 and a.trangthai != 0 and isnull(ctsp.iskhuyenmai,0) = 1 "+condition+
	    	
	    	"\n  union all "+
	    	
	    	//Tổng giá trị
	    	//(select max(ngayphanbo) from PhanBo_NVGN aa inner join PhanBo_NVGN_CT b on aa.pk_seq = b.phanbo_fk and b.donhang_fk = a.pk_seq) ngayphanbo
	    	"\n  select 999999999 phanbo_fk,a.pk_seq dhid, a.ngaynhap,'' ngayphanbo, c.mafast khma, c.diachigiaohang khdiachi, "+
	    	"\n  c.ten khten, '' spten, '' spsoluongdh,''spsoluonggiao,'' spgiamua, '' spthanhtien, nv1.ten NVGNDH, '' NVGNTT, ltrim(rtrim(str(a.tonggiatri,(len(a.tonggiatri)),0))) tgt, "+
	    	"\n  '' slgiaonhan, convert(varchar(10),getdate(),21) npb, " +
	    	"\n case  " +
	    	"\n when exists (select 1 from PhanBo_NVGN_CT where donhang_fk = a.pk_seq and not exists (select 1 from PhanBo_NVGN_CTsp where donhang_fk = a.pk_seq) ) then -1 " + 
	    	"\n when exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq and exists (  " +
	    	"\n select 1 from (   " +
	    	"\n select sum(soluong) soluong,sanpham_fk,donhang_fk from PhanBo_NVGN_CTsp  " +  
	    	"\n where donhang_Fk = a.pk_seq  " +
	    	"\n group by sanpham_fk,donhang_fk ) x inner join    " +
	    	"\n (select sanpham_fk, sum(soluong)soluong,donhang_fk from (   " +
	    	"\n select sanpham_fk, soluong, donhang_fk from donhang_sanpham where donhang_Fk = a.pk_seq  " +
	    	"\n union all    " +
	    	"\n select bbb.pk_seq,soluong,donhangid donhang_fk from donhang_ctkm_trakm aaa inner join sanpham bbb on aaa.spma = bbb.ma   " + 
	    	"\n where donhangid = a.pk_seq) bb   " +
	    	"\n group by sanpham_fk,donhang_fk   " +
	    	"\n ) y   " +
	    	"\n on x.sanpham_fk = y.sanpham_fk and x.donhang_fk = y.donhang_fk where x.soluong-y.soluong != 0   " +
	    	"\n )) then 0    " +
	    	"\n when  " +
	    	"\n exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq)  " +
	    	"\n and not exists (select 1 from PhanBo_NVGN_CTSP where donhang_fk = a.pk_seq and exists (   " +
	    	"\n select 1 from (    " +
	    	"\n select sum(soluong) soluong,sanpham_fk,donhang_fk from PhanBo_NVGN_CTsp     " +
	    	"\n where donhang_Fk = a.pk_seq   " +
	    	"\n group by sanpham_fk,donhang_fk ) x inner join     " +
	    	"\n (select sanpham_fk, sum(soluong)soluong,donhang_fk from (    " +
	    	"\n select sanpham_fk, soluong, donhang_fk from donhang_sanpham where donhang_Fk = a.pk_seq   " +
	    	"\n union all     " +
	    	"\n select bbb.pk_seq,soluong,donhangid donhang_fk from donhang_ctkm_trakm aaa inner join sanpham bbb on aaa.spma = bbb.ma   " +  
	    	"\n where donhangid = a.pk_seq) bb    " +
	    	"\n group by sanpham_fk,donhang_fk    " +
	    	"\n ) y    " +
	    	"\n on x.sanpham_fk = y.sanpham_fk and x.donhang_fk = y.donhang_fk where x.soluong-y.soluong != 0    " +
	    	"\n )) then 1   " +
	    	"\n else 0 end thudu " +
	    	"\n from donhang a inner join nhanviengiaonhan nv1 on nv1.pk_seq = a.nvgn_fk "+
	    	"\n inner join khachhang c on c.pk_seq = a.khachhang_fk "+
	    	//"\n inner join phanbo_NVGN_CT pbct on pbct.donhang_fk = a.pk_seq  "+
	    	//"\n inner join nhanviengiaonhan nv2 on nv2.pk_seq = pbct.nvgn_fk "+
	    	"\n		LEFT JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ=c.NPP_FK  "+
			"\n		inner join daidienkinhdoanh ddkd on ddkd.pk_seq in ( select DDKD_FK from tuyenbanhang where NPP_FK = c.npp_fk and pk_seq in   "+
			"\n 		(select tbh_fk from KHACHHANG_TUYENBH where khachhang_fk = c.pk_seq)  ) and ddkd.TRANGTHAI = 1 "+
			"\n 	left join ddkd_gsbh on ddkd_gsbh.ddkd_fk = ddkd.pk_seq "+
			"\n 	left join gsbh_khuvuc on gsbh_khuvuc.gsbh_fk = ddkd_gsbh.gsbh_fk "+
			"\n 	left join khuvuc kv on kv.pk_seq = gsbh_khuvuc.khuvuc_fk "+
			"\n 	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK    "+ 
	    	"\n where  a.trangthai !=2 and a.trangthai != 0 and exists (select 1 from phanbo_nvgn_CTSP where donhang_fk = a.pk_seq) "+condition_chitiet;
	    	
	    	query += "\n)tmp order by dhid, npb ";

	    
	    System.out.println("Query BC: " + query);
	    ResultSet rsChiphi = db.get(query);
	    
		try 
		{
			int rowIndex = 6;
			int colIndex = 0;
			
			Style BZStyle = cells.getCell("BZ6").getStyle();
			//BZStyle.setTextWrapped(true);
			Style BXStyle = cells.getCell("BX6").getStyle();
			
			if(rsChiphi != null)
			{
				double totalSL = 0;
				double totalTT = 0;
				double totalKM = 0;
				double totalTong = 0;
				
				while( rsChiphi.next() )
				{	
					int stt = rsChiphi.getInt("stt");
					String pk_seq = rsChiphi.getString("dhid");
					String ngaynhap = rsChiphi.getString("ngaynhap");
					String ngayphanbo = rsChiphi.getString("ngayphanbo");
					String khma = rsChiphi.getString("khma");
					String khten = rsChiphi.getString("khten");
					String khdiachi = rsChiphi.getString("khdiachi");
					String spten = rsChiphi.getString("spten");
					String spsoluongdh = rsChiphi.getString("spsoluongdh");		
					String spsoluonggiao = rsChiphi.getString("spsoluonggiao");	
					String spgiamua = rsChiphi.getString("spgiamua");
					String spthanhtien = rsChiphi.getString("spthanhtien");
					String tgt = rsChiphi.getString("tgt");
					int thudu = rsChiphi.getInt("thudu");
					String nvgndh = rsChiphi.getString("nvgndh");
					String nvgntt = rsChiphi.getString("nvgntt");
					String slgiaonhan = rsChiphi.getString("slgiaonhan");
					
					cell = cells.getCell( rowIndex, colIndex );     cell.setStyle(BZStyle); cell.setValue( stt );
					cell = cells.getCell( rowIndex, colIndex + 1 ); cell.setStyle(BZStyle); cell.setValue( pk_seq );	
					cell = cells.getCell( rowIndex, colIndex + 2 ); cell.setStyle(BZStyle); cell.setValue( ngaynhap );
					cell = cells.getCell( rowIndex, colIndex + 3 ); cell.setStyle(BZStyle); cell.setValue( ngayphanbo );
					cell = cells.getCell( rowIndex, colIndex + 4 ); cell.setStyle(BZStyle); cell.setValue( khma );
					cell = cells.getCell( rowIndex, colIndex + 5 ); cell.setStyle(BZStyle); cell.setValue( khten );
					cell = cells.getCell( rowIndex, colIndex + 6 ); cell.setStyle(BZStyle); cell.setValue( khdiachi );
					cell = cells.getCell( rowIndex, colIndex + 7 ); cell.setStyle(BZStyle); cell.setValue( spten );
					cell = cells.getCell( rowIndex, colIndex + 8 ); cell.setStyle(BXStyle); cell.setValue( !spsoluongdh.equals("")?Integer.parseInt(spsoluongdh):"" );
					cell = cells.getCell( rowIndex, colIndex + 9 ); cell.setStyle(BXStyle); cell.setValue( !spsoluonggiao.equals("")?Integer.parseInt(spsoluonggiao):"" );
					cell = cells.getCell( rowIndex, colIndex + 10 ); cell.setStyle(BXStyle); cell.setValue( !spgiamua.equals("")?formatter2.format(Double.parseDouble(spgiamua)):"" );
					cell = cells.getCell( rowIndex, colIndex + 11 ); cell.setStyle(BXStyle); cell.setValue( !spthanhtien.equals("")?formatter.format(Double.parseDouble(spthanhtien)):"" );
					cell = cells.getCell( rowIndex, colIndex + 12 ); cell.setStyle(BXStyle); cell.setValue( !tgt.equals("")?formatter.format(Double.parseDouble(tgt)):"" );
					cell = cells.getCell( rowIndex, colIndex + 13 ); cell.setStyle(BZStyle); cell.setValue( thudu==1?"Giao đủ":"Giao thiếu" );
					cell = cells.getCell( rowIndex, colIndex + 14 ); cell.setStyle(BZStyle); cell.setValue( nvgndh );
					cell = cells.getCell( rowIndex, colIndex + 15 ); cell.setStyle(BZStyle); cell.setValue( nvgntt );
					cell = cells.getCell( rowIndex, colIndex + 16 ); cell.setStyle(BXStyle); cell.setValue( !slgiaonhan.equals("")?Integer.parseInt(spsoluongdh)-Integer.parseInt(spsoluonggiao):""  );
					
			
					rowIndex ++;
				}
				rsChiphi.close();		
				
			}		
		}
		catch (Exception e) 
		{ 
			e.printStackTrace();
		}
	    
	    db.shutDown();	    
	}
	
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	

}
