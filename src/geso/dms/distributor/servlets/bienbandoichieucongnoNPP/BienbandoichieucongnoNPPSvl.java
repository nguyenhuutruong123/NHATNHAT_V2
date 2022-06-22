package geso.dms.distributor.servlets.bienbandoichieucongnoNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinhList;
import geso.dms.distributor.beans.bienbandoichieucongnoNPP.IBienbandoichieucongnoNPP;
import geso.dms.distributor.beans.bienbandoichieucongnoNPP.imp.BienbandoichieucongnoNPP;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinhList;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPPList;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPPList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BienbandoichieucongnoNPPSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BienbandoichieucongnoNPPSvl() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBienbandoichieucongnoNPP obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    PrintWriter out = response.getWriter();
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	   
	    obj = new BienbandoichieucongnoNPP();
    	obj.setUserId(userId);	

    	obj.init();
		session.setAttribute("obj", obj);
		String nextJSP="";
			nextJSP= request.getContextPath() + "/pages/Distributor/BienbandoichieucongnoNPP.jsp";
		
		response.sendRedirect(nextJSP);
	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null)
	    {
	    	action = "";
	    }
	    IBienbandoichieucongnoNPP obj = new BienbandoichieucongnoNPP();

	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    if(userId!=null)
    		obj.setUserId(userId);
	    System.out.println("user id la "+userId);

		//lay thong tin khach hang
		String khachhang = request.getParameter("khTen");
		String[] loai = khachhang.split("--"); 
		obj.setKhId(khachhang);
		
		String nppId = request.getParameter("nppId");
    	if ( nppId == null)
    		nppId = "";
    	obj.setnppId(nppId);
    	
    	String tungay = request.getParameter("tungay");
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
		
    	obj.init();
    	
    	
		if(action.equals("in2"))
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=Bienbandoichieucongno.pdf");
			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();
		    form_bienbanbutrucongno(document, outstream, obj, loai, nppId);
		}
		else
			if(action.equals("in3")){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", " inline; filename=Bienbandoichieucongno.pdf");
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();
				form_bienbandoichieu_kiembutrucongno(document, outstream, obj, loai, nppId);
			}
		session.setAttribute("obj", obj);  	
		session.setAttribute("userId", userId);		
		String nextJSP = request.getContextPath() + "/pages/Distributor/BienbandoichieucongnoNPP.jsp";
		response.sendRedirect(nextJSP);
	}
	    
	

		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	float CONVERT = 28.346457f;  // =1cm
	

	private String dinhdangngay (String ngay) {
		String date="";
		if(ngay.length()>=8){
			date=ngay.substring(8)+"/"+ngay.substring(5,7)+"/"+ngay.substring(0,4);
		}
		return date;
	}
	private String getDate()
	{			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}

	
	
	
	private void form_bienbanbutrucongno( Document document,ServletOutputStream outstream, IBienbandoichieucongnoNPP obj, String []loai, String nppId){
		dbutils db = new dbutils();
		
		
		//----------khu vuc lay thong tin
		String ngayxuat=obj.getDenngay();
		String []ngaycuoikyH=ngayxuat.split("-");
		String ngaycuoiky=  ngaycuoikyH[2]+"/"+   ngaycuoikyH[1] +"/" + ngaycuoikyH[0];
			
		String ngayxuat2=obj.getTungay();
		String []ngayxuatHD1=ngayxuat2.split("-");
		String ngaydauky=   ngayxuatHD1[2]+"/"+   ngayxuatHD1[1] +"/" + ngayxuatHD1[0];
		//lay thong khah hang
		String kh_Donvi="";
		String kh_MST ="";		
		String kh_Diachi="";
		String sql="";	
		try {
			//etc
			if(loai[0].equals("ETC") || loai[0].equals("OTC")){
				sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan,  "+    
				" isnull(dienthoai,''), ''  as fax , ''  as nganhang  "+    
				" from KHACHHANG kh where kh.pk_seq="+ loai[1]; 

				System.out.println("\n Lấy TT KH etc: "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							kh_Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							rsLayKH.close();
						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			

			}
			else
			{
				//doi tac (nha phan phoi)
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,isnull( kh.masothue,'') as masothue , kh.sotaikhoan as taikhoan, "+
				"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang "+    
				"\n from NHAPHANPHOI kh where kh.pk_seq="+loai[1];
				
				System.out.println("\n Lấy TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							kh_Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							rsLayKH.close();
						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// lay thong tin ben giao chi nhanhs
		String npp_fk="";
		String khId="";
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Sotaikhoan= "";
		String cty_Dienthoai= "";
		String cty_Fax= "";
		String cty_bank= "";
		String cty_email="";
		
		String qr_ho="select kh.pk_seq, kh.TEN as TEN,  kh.diachi as diachi,isnull(  kh.masothue ,'') as masothue, kh.sotaikhoan as taikhoan, "+
		"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang,ISNULL( KH.EMAIL,'' ) AS EMAIL   "+    
		"\n from NHAPHANPHOI kh where kh.pk_seq=" + nppId;   
		System.out.println(" thong tin chinh nhanh: "+ qr_ho);
		 ResultSet rsHo = db.get(qr_ho);
		   if(rsHo!=null){
			   try {
				   while(rsHo.next())
				   {
					   ctyTen = rsHo.getString("TEN");
					   cty_MST = rsHo.getString("MASOTHUE");
					   cty_Diachi = rsHo.getString("DIACHI");
					   cty_Sotaikhoan = rsHo.getString("taikhoan");
					   cty_Dienthoai = rsHo.getString("dienthoai");
					   cty_Fax = rsHo.getString("fax");
					   cty_bank = rsHo.getString("nganhang");
					   cty_email= rsHo.getString("EMAIL");
					   rsHo.close();   
				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		  
		//-------------------khu vuc in form
		   
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 0.5f*CONVERT,1.0f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell, cells ;
			Paragraph para;
			Chunk chunk;
			BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked='\u00FE';
			char unchecked='\u00A8';


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 10, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 10, Font.BOLD);
			Font font10_ilatic = new Font(bf, 10, Font.ITALIC);

			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);


			Font font9_normal = new Font(bf, 9, Font.NORMAL);
			Font font9_Bold = new Font(bf, 9, Font.BOLD);
			Font font9_ilatic = new Font(bf, 9, Font.ITALIC);
			
			Font font14_Bold = new Font(bf, 14, Font.BOLD);
			
			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(2);
			float[] withd_head= {5.0f,23.0f};
			tableheader.setWidths(withd_head);
			tableheader.setWidthPercentage(100);

			// cell1 logo
			Image img = Image.getInstance(getServletContext().getInitParameter("pathPrint")+ "\\logo.gif");
			img.scalePercent(30);
			cell = new PdfPCell();
			chunk=new Chunk(img,15,-15);
			cell.addElement(chunk);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableheader.addCell(cell);
					
			// cell 2 ten diachi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 //--> cell 2 add bang 2 cotk
					PdfPTable tbl_con =new PdfPTable(1);
					tbl_con.setWidthPercentage(100);
					//d1
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.8f*CONVERT);
					para=new Paragraph(" " +ctyTen.toUpperCase(),font12_Bold);
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
					//d2
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(" " +"CUU LONG PHARMACEUTICAL JOINT STOCK CORPORATION",font10_normal);
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(" " + cty_Diachi,font10_normal);
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
					
					//d3
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(new Paragraph(" " +"TEL: "+cty_Dienthoai + " - " + " FAX: "+cty_Fax,font10_normal));
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);

					//d4					
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(new Paragraph(" " +"http://www.pharimexco.com.vn "+"       E-mail:" + cty_email,font10_normal));
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
			cell.addElement(tbl_con);
			tableheader.addCell(cell);
			
			
			//cell 3
			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setFixedHeight(0.2f*CONVERT);
			cell.addElement(new Paragraph(" ", font12_Bold));
			//duong gach ngang
			cell.setBorderWidthTop(0f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(01f);
			tableheader.addCell(cell);
			
			//cell 4
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setPaddingTop(0.4f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			para.add(new Chunk("BIÊN BẢN ĐỐI CHIẾU CÔNG NỢ",new Font(bf,18,Font.BOLD)));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			//cell 5
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			
			para.add(new Chunk("Đến ngày "+ngaycuoikyH[2]+" tháng "+   ngaycuoikyH[1] +" năm " + ngaycuoikyH[0],font14_Bold));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			document.add(tableheader);

			//== BANG  THONG TIN BEN BAN VA BEN MUA

			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);

			//dong 1
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String date=getDate();
			cell.addElement(new Paragraph("Hôm nay, ngày "+ date.substring(8)+" tháng "+
					date.substring(5,7)+ " năm "+date.substring(0,4) + ", chúng tôi gồm có:",font12_ilatic));
			tbl.addCell(cell);
			
			
			// dong 1 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("A. BÊN BÁN: ", font12_Bold);
		//	chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(ctyTen.toUpperCase(), font12_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+cty_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			/*para=new Paragraph("Điện thoại: " +cty_Dienthoai         +"                 -Fax:"+cty_Fax,font12_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);*/

			para=new Paragraph("- Mã số thuế: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Đại diện:                                                                    Chức vụ:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Tài khoản:  "+ cty_Sotaikhoan,font12_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			

		
					// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B. BÊN MUA: ", font12_Bold);
			//chunk.setUnderline(1f, -2);
			para.add(chunk);

			chunk=new Chunk(kh_Donvi.toUpperCase(), font12_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+kh_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Mã số thuế: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Đại diện:                                                                    Chức vụ:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Cùng tiến hành đối chiếu công nợ đến "+ ngaycuoiky +" với chi tiết như sau:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);
			
			
			// BANG số dư nợ đầu kỳ
			PdfPTable tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("1. Số dư nợ đầu kỳ "+ngaydauky  +" :",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("so tien x",font12_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("2. Phát sinh trong kỳ:",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para=new Paragraph(" ",font12_Bold));
			tbl_duno.addCell(cell);
			
			document.add(tbl_duno);
			
			//---------- Bang phát sinh trong kỳ
			float[] tbl_withd = { 20.0f, 20f, 20.0f, 60.0f, 25.0f, 25.0f};
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f*CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);


			//add tieu de
			String[] tieude = new String[] { "SỐ PHIẾU"," NGÀY","SỐ HĐ","DIỄN GIẢI","NỢ (BÁN)","CÓ (T.TOÁN)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_Bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			
			document.add(tbl_sanpham);
			//---------- Bang Số dư nợ cuối kỳ
			
			tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno1= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("3. Số dư nợ cuối kỳ đến ngày "+ngaycuoiky +" , "+ kh_Diachi.toUpperCase() +" còn nợ " +
							ctyTen.toUpperCase() +" số tiền : ",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("so tien x",font12_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_duno.addCell(cell);
			
			document.add(tbl_duno);
			
			//bang footer
			
			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);


			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)0);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Bằng chữ:  " +TienIN, font12_ilatic); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Biên bản này được thành lập thành 02 bản. Kính mong Qúy Khách hàng đối chiếu số liệu, "+
								"ký xác nhận gửi về cho Công ty chúng tôi 01 bản và chuyển khoản thanh toán cho Công ty chúng tôi các hóa đơn quá hạn theo số TK trên. "
								, font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("           Xin chân thành cảm ơn", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                               "+
					"ĐẠI DIỆN BÊN B", font12_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("            (Ký tên và đóng dấu)", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			

			document.add(tbl_footer);
			
			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	
	
	private void form_bienbandoichieu_kiembutrucongno( Document document,ServletOutputStream outstream, IBienbandoichieucongnoNPP obj, String []loai, String nppId){
		dbutils db = new dbutils();
		
		
		//----------khu vuc lay thong tin
		String ngayxuat=obj.getDenngay();
		String []ngaycuoikyH=ngayxuat.split("-");
		String ngaycuoiky=  ngaycuoikyH[2]+"/"+   ngaycuoikyH[1] +"/" + ngaycuoikyH[0];
			
		String ngayxuat2=obj.getTungay();
		String []ngayxuatHD1=ngayxuat2.split("-");
		String ngaydauky=   ngayxuatHD1[2]+"/"+   ngayxuatHD1[1] +"/" + ngayxuatHD1[0];
		//lay thong khah hang
		String kh_Donvi="";
		String kh_MST ="";		
		String kh_Diachi="";
		String sql="";	
		try {
			//etc
			if(loai[0].equals("ETC") || loai[0].equals("OTC")){
				sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan,  "+    
				" isnull(dienthoai,''), ''  as fax , ''  as nganhang  "+    
				" from KHACHHANG kh where kh.pk_seq="+ loai[1]; 

				System.out.println("\n Lấy TT KH etc: "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							kh_Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							rsLayKH.close();
						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			

			}
			else
			{
				//doi tac (nha phan phoi)
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,isnull( kh.masothue,'') as masothue , kh.sotaikhoan as taikhoan, "+
				"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang "+    
				"\n from NHAPHANPHOI kh where kh.pk_seq="+loai[1];
				
				System.out.println("\n Lấy TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							kh_Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							rsLayKH.close();
						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// lay thong tin ben giao chi nhanhs
		String npp_fk="";
		String khId="";
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Sotaikhoan= "";
		String cty_Dienthoai= "";
		String cty_Fax= "";
		String cty_bank= "";
		String cty_email="";
		
		String qr_ho="select kh.pk_seq, kh.TEN as TEN,  kh.diachi as diachi,isnull(  kh.masothue ,'') as masothue, kh.sotaikhoan as taikhoan, "+
		"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang,ISNULL( KH.EMAIL,'' ) AS EMAIL   "+    
		"\n from NHAPHANPHOI kh where kh.pk_seq=" + nppId;   
		System.out.println(" thong tin chinh nhanh: "+ qr_ho);
		 ResultSet rsHo = db.get(qr_ho);
		   if(rsHo!=null){
			   try {
				   while(rsHo.next())
				   {
					   ctyTen = rsHo.getString("TEN");
					   cty_MST = rsHo.getString("MASOTHUE");
					   cty_Diachi = rsHo.getString("DIACHI");
					   cty_Sotaikhoan = rsHo.getString("taikhoan");
					   cty_Dienthoai = rsHo.getString("dienthoai");
					   cty_Fax = rsHo.getString("fax");
					   cty_bank = rsHo.getString("nganhang");
					   cty_email= rsHo.getString("EMAIL");
					   rsHo.close();   
				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		  
		//-------------------khu vuc in form
		   
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 0.5f*CONVERT,1.0f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell, cells ;
			Paragraph para;
			Chunk chunk;
			BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked='\u00FE';
			char unchecked='\u00A8';


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 10, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 10, Font.BOLD);
			Font font10_ilatic = new Font(bf, 10, Font.ITALIC);

			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);


			Font font9_normal = new Font(bf, 9, Font.NORMAL);
			Font font9_Bold = new Font(bf, 9, Font.BOLD);
			Font font9_ilatic = new Font(bf, 9, Font.ITALIC);
			
			Font font14_Bold = new Font(bf, 14, Font.BOLD);
			
			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(2);
			float[] withd_head= {5.0f,23.0f};
			tableheader.setWidths(withd_head);
			tableheader.setWidthPercentage(100);

			// cell1 logo
			Image img = Image.getInstance(getServletContext().getInitParameter("pathPrint")+ "\\logo.gif");
			img.scalePercent(30);
			cell = new PdfPCell();
			chunk=new Chunk(img,15,-15);
			cell.addElement(chunk);
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableheader.addCell(cell);
					
			// cell 2 ten diachi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 //--> cell 2 add bang 2 cotk
					PdfPTable tbl_con =new PdfPTable(1);
					tbl_con.setWidthPercentage(100);
					//d1
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.8f*CONVERT);
					para=new Paragraph(" " +ctyTen.toUpperCase(),font12_Bold);
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
					//d2
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(" " +"CUU LONG PHARMACEUTICAL JOINT STOCK CORPORATION",font10_normal);
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(" " + cty_Diachi,font10_normal);
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
					
					//d3
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(new Paragraph(" " +"TEL: "+cty_Dienthoai + " - " + " FAX: "+cty_Fax,font10_normal));
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);

					//d4					
					cells= new PdfPCell();
					cells.setBorder(0);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setPaddingTop(-0.2f*CONVERT);
					para=new Paragraph(new Paragraph(" " +"http://www.pharimexco.com.vn "+"       E-mail:" + cty_email,font10_normal));
					para.setAlignment(Element.ALIGN_CENTER);
					cells.addElement(para);
					tbl_con.addCell(cells);
					
			cell.addElement(tbl_con);
			tableheader.addCell(cell);
			
			
			//cell 3
			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setFixedHeight(0.2f*CONVERT);
			cell.addElement(new Paragraph(" ", font12_Bold));
			//duong gach ngang
			cell.setBorderWidthTop(0f);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthBottom(01f);
			tableheader.addCell(cell);
			
			//cell 4
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setPaddingTop(0.2f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("BIÊN BẢN",new Font(bf,18,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			
			para=new Paragraph(" ĐỐI CHIẾU KIÊM BÙ TRỪ CÔNG NỢ",new Font(bf,18,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			
			tableheader.addCell(cell);
			//cell 5
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			
			para.add(new Chunk("Đến ngày "+ngaycuoikyH[2]+" tháng "+   ngaycuoikyH[1] +" năm " + ngaycuoikyH[0],font14_Bold));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			document.add(tableheader);

			//== BANG  THONG TIN BEN BAN VA BEN MUA

			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);

			//dong 1
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			String date=getDate();
			cell.addElement(new Paragraph("Hôm nay, ngày "+ date.substring(8)+" tháng "+
					date.substring(5,7)+ " năm "+date.substring(0,4) + ", chúng tôi gồm có:",font12_ilatic));
			tbl.addCell(cell);
			
			
			// dong 1 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("A. BÊN BÁN: ", font12_Bold);
		//	chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(ctyTen.toUpperCase(), font12_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+cty_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			/*para=new Paragraph("Điện thoại: " +cty_Dienthoai         +"                 -Fax:"+cty_Fax,font12_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);*/

			para=new Paragraph("- Mã số thuế: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Đại diện:                                                                    Chức vụ:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Tài khoản:  "+ cty_Sotaikhoan,font12_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			

		
					// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B. BÊN MUA: ", font12_Bold);
			//chunk.setUnderline(1f, -2);
			para.add(chunk);

			chunk=new Chunk(kh_Donvi.toUpperCase(), font12_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+kh_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- Mã số thuế: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Đại diện:                                                                    Chức vụ:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Cùng tiến hành đối chiếu công nợ đến "+ ngaycuoiky +" với chi tiết như sau:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);
			
			
			// BANG số dư nợ đầu kỳ
			PdfPTable tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("1. Số dư nợ đầu kỳ "+ngaydauky  +" :",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("so tien x",font12_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("2. Phát sinh trong kỳ:",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para=new Paragraph(" ",font12_Bold));
			tbl_duno.addCell(cell);
			
			document.add(tbl_duno);
			
			//---------- Bang phát sinh trong kỳ
			float[] tbl_withd = { 20.0f, 20f, 20.0f, 60.0f, 25.0f, 25.0f};
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f*CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);

			//add tieu de
			String[] tieude = new String[] { "SỐ PHIẾU"," NGÀY","SỐ HĐ","DIỄN GIẢI","NỢ (BÁN)","CÓ (T.TOÁN)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_Bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			
			document.add(tbl_sanpham);
			//---------- Bang Số dư nợ cuối kỳ
			
			
		    tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			tbl_duno.setWidths(withd_duno);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("3. Hai bên thống nhất bù trừ công nợ với số tiền là: ",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("so tien x",font12_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_duno.addCell(cell);
			document.add(tbl_duno);
			
			
			
			
			tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno1= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("4. Số dư nợ cuối kỳ đến ngày "+ngaycuoiky +" , "+ kh_Diachi.toUpperCase() +" còn nợ " +
							ctyTen.toUpperCase() +" số tiền : ",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("so tien x",font12_Bold);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_duno.addCell(cell);
			
			document.add(tbl_duno);
			
			//bang footer
			
			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);


			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)0);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Bằng chữ:  " +TienIN, font12_ilatic); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Biên bản này được thành lập thành 02 bản. Kính mong Qúy Khách hàng đối chiếu số liệu, "+
								"ký xác nhận gửi về cho Công ty chúng tôi 01 bản và chuyển khoản thanh toán cho Công ty chúng tôi các hóa đơn quá hạn theo số TK trên. "
								, font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("           Xin chân thành cảm ơn", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                               "+
					"ĐẠI DIỆN BÊN B", font12_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("            (Ký tên và đóng dấu)", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			

			document.add(tbl_footer);
			
			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	
	
}


