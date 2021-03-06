package geso.dms.center.servlets.bienbandoichieucongnoHO;

import geso.dms.center.beans.bienbandoichieucongnoHO.IBienbandoichieucongnoHO;
import geso.dms.center.beans.bienbandoichieucongnoHO.imp.BienbandoichieucongnoHO;
import geso.dms.center.beans.doctien.doctienrachu;
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

public class BienbandoichieucongnoHOSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BienbandoichieucongnoHOSvl() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IBienbandoichieucongnoHO obj;
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
	   
	    obj = new BienbandoichieucongnoHO();
    	obj.setUserId(userId);	

    	obj.init();
		session.setAttribute("obj", obj);
		String nextJSP="";
			nextJSP= request.getContextPath() + "/pages/Center/BienbandoichieucongnoHO.jsp";
		
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
	    IBienbandoichieucongnoHO obj = new BienbandoichieucongnoHO();

	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    if(userId!=null)
    		obj.setUserId(userId);
	    System.out.println("user id la "+userId);

		//lay thong tin khach hang
		String khachhang = request.getParameter("khTen");
		//String[] loai = khachhang.split("--"); 
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
		    form_bienbanbutrucongno(document, outstream, obj, nppId);
		}
		else
			if(action.equals("in3")){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", " inline; filename=BienbandoichieucongnoHO.pdf");
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();
				form_bienbandoichieu_kiembutrucongno(document, outstream, obj,nppId);
			}
		session.setAttribute("obj", obj);  	
		session.setAttribute("userId", userId);		
		String nextJSP = request.getContextPath() + "/pages/Center/BienbandoichieucongnoHO.jsp";
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

	
	
	
	private void form_bienbanbutrucongno( Document document,ServletOutputStream outstream, IBienbandoichieucongnoHO obj, String nppId){
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
		
		
			
				//doi tac (nha phan phoi)
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,isnull( kh.masothue,'') as masothue , kh.sotaikhoan as taikhoan, "+
				"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang "+    
				"\n from NHAPHANPHOI kh where kh.pk_seq="+obj.getKhId();
				
				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							kh_Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
		System.out.println("\n L???y TT KH doi tac "+sql +"\n");
		
		
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
		
		//ho lay tu nha cung cap  DCL
		
		String qr_ho="select kh.pk_seq, kh.TEN as TEN,  kh.diachi as diachi,isnull(  kh.masothue ,'') as masothue, kh.taikhoan as taikhoan, "+
		"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang,ISNULL( KH.EMAIL,'' ) AS EMAIL   "+    
		"\n from NHACUNGCAP kh where kh.pk_seq= 100001 ";   
	
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
				   }
				   rsHo.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		   
		   System.out.println(" thong tin chinh nhanh: "+ qr_ho);
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
			System.out.println(" da vo: " +"\n");
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
			para.add(new Chunk("BI??N B???N ?????I CHI???U C??NG N???",new Font(bf,18,Font.BOLD)));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);
			
			//cell 5
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			
			para.add(new Chunk("?????n ng??y "+ngaycuoikyH[2]+" th??ng "+   ngaycuoikyH[1] +" n??m " + ngaycuoikyH[0],font14_Bold));
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
			cell.addElement(new Paragraph("H??m nay, ng??y "+ date.substring(8)+" th??ng "+
					date.substring(5,7)+ " n??m "+date.substring(0,4) + ", ch??ng t??i g???m c??:",font12_ilatic));
			tbl.addCell(cell);
			
			
			// dong 1 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("A. B??N B??N: ", font12_Bold);
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

			para=new Paragraph("- ?????a ch???: "+cty_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			/*para=new Paragraph("??i???n tho???i: " +cty_Dienthoai         +"                 -Fax:"+cty_Fax,font12_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);*/

			para=new Paragraph("- M?? s??? thu???: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- ?????i di???n:                                                                    Ch???c v???:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- T??i kho???n:  "+ cty_Sotaikhoan,font12_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			

		
					// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B. B??N MUA: ", font12_Bold);
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

			para=new Paragraph("- ?????a ch???: "+kh_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- M?? s??? thu???: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- ?????i di???n:                                                                    Ch???c v???:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      C??ng ti???n h??nh ?????i chi???u c??ng n??? ?????n "+ ngaycuoiky +" v???i chi ti???t nh?? sau:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);
			
			
			// BANG s??? d?? n??? ?????u k???
			PdfPTable tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("1. S??? d?? n??? ?????u k??? "+ngaydauky  +" :",font12_Bold));
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
			cell.addElement(para=new Paragraph("2. Ph??t sinh trong k???:",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para=new Paragraph(" ",font12_Bold));
			tbl_duno.addCell(cell);
			
			document.add(tbl_duno);
			
			//---------- Bang ph??t sinh trong k???
			float[] tbl_withd = { 20.0f, 20f, 20.0f, 60.0f, 25.0f, 25.0f};
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f*CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);


			//add tieu de
			String[] tieude = new String[] { "S??? PHI???U"," NG??Y","S??? H??","DI???N GI???I","N??? (B??N)","C?? (T.TO??N)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_Bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			
			document.add(tbl_sanpham);
			//---------- Bang S??? d?? n??? cu???i k???
			
			tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno1= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("3. S??? d?? n??? cu???i k??? ?????n ng??y "+ngaycuoiky +" , "+ kh_Diachi.toUpperCase() +" c??n n??? " +
							ctyTen.toUpperCase() +" s??? ti???n : ",font12_Bold));
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
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Vi???t hoa k?? t??? ?????u ti??n
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("B???ng ch???:  " +TienIN, font12_ilatic); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Bi??n b???n n??y ???????c th??nh l???p th??nh 02 b???n. K??nh mong Q??y Kh??ch h??ng ?????i chi???u s??? li???u, "+
								"k?? x??c nh???n g???i v??? cho C??ng ty ch??ng t??i 01 b???n v?? chuy???n kho???n thanh to??n cho C??ng ty ch??ng t??i c??c h??a ????n qu?? h???n theo s??? TK tr??n. "
								, font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("           Xin ch??n th??nh c???m ??n", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                               "+
					"?????I DI???N B??N B", font12_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("            (K?? t??n v?? ????ng d???u)", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			

			document.add(tbl_footer);
			
			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	
	
	private void form_bienbandoichieu_kiembutrucongno( Document document,ServletOutputStream outstream, IBienbandoichieucongnoHO obj,
			 String nppId){
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
			
				//doi tac (nha phan phoi)
				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,isnull( kh.masothue,'') as masothue , kh.sotaikhoan as taikhoan, "+
				"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang "+    
				"\n from NHAPHANPHOI kh where kh.pk_seq="+obj.getKhId();
				
				System.out.println("\n LAY TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							kh_Donvi = rsLayKH.getString("DONVI");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
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
		
		String qr_ho="select kh.pk_seq, kh.TEN as TEN,  kh.diachi as diachi,isnull(  kh.masothue ,'') as masothue, kh.taikhoan as taikhoan, "+
		"\n isnull(dienthoai,'') as dienthoai, isnull(fax,'') as fax , isnull(nganhang,'') as nganhang,ISNULL( KH.EMAIL,'' ) AS EMAIL   "+    
		"\n from NHACUNGCAP kh where kh.pk_seq=100001";   
		System.out.println(" thong tin chinh nhanh: "+ qr_ho);
		 ResultSet rsHo = db.get(qr_ho);
		   if(rsHo!=null){
			   try {
				   if(rsHo.next())
				   {
					   ctyTen = rsHo.getString("TEN");
					   cty_MST = rsHo.getString("MASOTHUE");
					   cty_Diachi = rsHo.getString("DIACHI");
					   cty_Sotaikhoan = rsHo.getString("taikhoan");
					   cty_Dienthoai = rsHo.getString("dienthoai");
					   cty_Fax = rsHo.getString("fax");
					   cty_bank = rsHo.getString("nganhang");
					   cty_email= rsHo.getString("EMAIL");   
				   }
				   rsHo.close();  
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
			para=new Paragraph("BI??N B???N",new Font(bf,18,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			
			para=new Paragraph(" ?????I CHI???U KI??M B?? TR??? C??NG N???",new Font(bf,18,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			
			tableheader.addCell(cell);
			//cell 5
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph();
			
			para.add(new Chunk("?????n ng??y "+ngaycuoikyH[2]+" th??ng "+   ngaycuoikyH[1] +" n??m " + ngaycuoikyH[0],font14_Bold));
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
			cell.addElement(new Paragraph("H??m nay, ng??y "+ date.substring(8)+" th??ng "+
					date.substring(5,7)+ " n??m "+date.substring(0,4) + ", ch??ng t??i g???m c??:",font12_ilatic));
			tbl.addCell(cell);
			
			
			// dong 1 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("A. B??N B??N: ", font12_Bold);
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

			para=new Paragraph("- ?????a ch???: "+cty_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			/*para=new Paragraph("??i???n tho???i: " +cty_Dienthoai         +"                 -Fax:"+cty_Fax,font12_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);*/

			para=new Paragraph("- M?? s??? thu???: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- ?????i di???n:                                                                    Ch???c v???:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- T??i kho???n:  "+ cty_Sotaikhoan,font12_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			

		
					// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("B. B??N MUA: ", font12_Bold);
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

			para=new Paragraph("- ?????a ch???: "+kh_Diachi,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			
			para=new Paragraph("- M?? s??? thu???: "+cty_MST,font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- ?????i di???n:                                                                    Ch???c v???:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      C??ng ti???n h??nh ?????i chi???u c??ng n??? ?????n "+ ngaycuoiky +" v???i chi ti???t nh?? sau:",font12_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);
			
			
			// BANG s??? d?? n??? ?????u k???
			PdfPTable tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			float[] withd_duno= {20f,6.0f};
			tbl_duno.setWidths(withd_duno);
			
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("1. S??? d?? n??? ?????u k??? "+ngaydauky  +" :",font12_Bold));
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
			cell.addElement(para=new Paragraph("2. Ph??t sinh trong k???:",font12_Bold));
			tbl_duno.addCell(cell);
			
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para=new Paragraph(" ",font12_Bold));
			tbl_duno.addCell(cell);
			
			document.add(tbl_duno);
			
			//---------- Bang ph??t sinh trong k???
			float[] tbl_withd = { 20.0f, 20f, 20.0f, 60.0f, 25.0f, 25.0f};
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f*CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);

			//add tieu de
			String[] tieude = new String[] { "S??? PHI???U"," NG??Y","S??? H??","DI???N GI???I","N??? (B??N)","C?? (T.TO??N)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], font10_Bold));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			
			document.add(tbl_sanpham);
			//---------- Bang S??? d?? n??? cu???i k???
			
			
		    tbl_duno =new PdfPTable(2);
			tbl_duno.setWidthPercentage(100);
			tbl_duno.setWidths(withd_duno);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.addElement(para=new Paragraph("3. Hai b??n th???ng nh???t b?? tr??? c??ng n??? v???i s??? ti???n l??: ",font12_Bold));
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
			cell.addElement(para=new Paragraph("4. S??? d?? n??? cu???i k??? ?????n ng??y "+ngaycuoiky +" , "+ kh_Diachi.toUpperCase() +" c??n n??? " +
							ctyTen.toUpperCase() +" s??? ti???n : ",font12_Bold));
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
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Vi???t hoa k?? t??? ?????u ti??n
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("B???ng ch???:  " +TienIN, font12_ilatic); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Bi??n b???n n??y ???????c th??nh l???p th??nh 02 b???n. K??nh mong Q??y Kh??ch h??ng ?????i chi???u s??? li???u, "+
								"k?? x??c nh???n g???i v??? cho C??ng ty ch??ng t??i 01 b???n v?? chuy???n kho???n thanh to??n cho C??ng ty ch??ng t??i c??c h??a ????n qu?? h???n theo s??? TK tr??n. "
								, font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("           Xin ch??n th??nh c???m ??n", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                               "+
					"?????I DI???N B??N B", font12_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("            (K?? t??n v?? ????ng d???u)", font12_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);
			

			document.add(tbl_footer);
			
			document.close();
			System.out.println(" vao form in");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
}


