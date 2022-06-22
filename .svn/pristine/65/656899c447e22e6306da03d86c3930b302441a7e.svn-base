package geso.dms.center.servlets.hoadontaichinh;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinh;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinhNPP.*;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.*;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfContentByte;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ErpHoadontaichinhPdfSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ErpHoadontaichinhPdfSvl()
	{
		super();
	}
	float CONVERT = 28.346457f; // = 1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		// String userTen = (String) session.getAttribute("userTen");

		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String querystring = request.getQueryString();
		String id = util.antiSQLInspection(request.getParameter("pdf"));
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));

		IErpHoadontaichinh hdBean = new ErpHoadontaichinh(id);
		hdBean.setUserId(userId); 
		hdBean.init();

		//san pham ban
		/*String	 query_sp = "select hoadon_fk as mahd, donhang_fk as madonhang, ma as MA, ten as TEN, NGAYHETHAN AS NGAYHETHAN,  \n "+    
		" isnull((SELECT DONVI FROM DONVIDOLUONG WHERE PK_SEQ= ERP_HOADON_SP_CHITIET.DVCHUAN),'') AS DONVI, DONGIA AS DONGIA, SOLUONG AS SOLUONG, SOLO AS SOLO, THUEVAT AS VAT , CHIETKHAU AS CHIETKHAU "+    
		"from ERP_HOADON_SP_CHITIET where hoadon_fk="+ hdBean.getId();*/

		String task = request.getParameter("task");
		if (querystring.indexOf("pdf") > 0)
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=Hoadontaichinh_HO.pdf");

			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();
			//mau 1 HO
			this.CreatePxk(document, outstream, hdBean);
		} 
	}



	// mau dang dung HO 23_9_2016
	private void form_hoadon2(Document document,ServletOutputStream outstream,
			IErpHoadontaichinh pxkBean,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi,
			 String phieuxuatkho,dbutils db)
	{
		String	 sqlIN_SANPHAM = "select ma as MA, NGAYHETHAN AS NGAYHETHAN,   SOLO AS SOLO  \n "+       
		"from ERP_HOADON_SP_CHITIET where hoadon_fk="+ pxkBean.getId() +" group by ma,NGAYHETHAN,solo "; 
		System.out.println("\n  qr so lo : "+sqlIN_SANPHAM +"\n");
		String mahoadon=pxkBean.getId();
		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			document.setMargins(1f*CONVERT, 1f*CONVERT, 0.8f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);

			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;
			document.open() ;

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);


			PdfPTable tableheader =new PdfPTable(2);
			float []wky={17.0f,2.0f};
			tableheader.setWidthPercentage(100);	
			tableheader.setWidths(wky);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4.0f*CONVERT);
			cell.setPaddingLeft(1.7f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			String [] ngayHD = ngayxuatHD.split("-");
			para= new Paragraph("",new Font(bf, 8, Font.BOLD));
			if(ngayHD.length>=3){
				chunk =new Chunk(ngayHD[2] + "                     " + ngayHD[1] +"                   " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
				chunk.setTextRise(0.5f*CONVERT);
				para.add(chunk);
				para.setAlignment(Element.ALIGN_CENTER);

			}
			cell.addElement(para);
			tableheader.addCell(cell);

			//phieu xuat kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph(phieuxuatkho,new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tableheader.addCell(cell);

			document.add(tableheader);

			// ================================ADD INFO NHA PHAN PHOI
			PdfPTable tbl_npp =new PdfPTable(1);
			tbl_npp.setWidthPercentage(100);

			//--dong1 ten npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.4f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( ctyTen , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);


			//--dong2 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			cell.setPaddingTop(-0.05f * CONVERT);
			para = new Paragraph( cty_Diachi, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong3 Stk npp + ngan hang
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan=" ";
			}
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( cty_Sotaikhoan, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			document.add(tbl_npp);

			//----------------------ADD INFO KHACH HANG-----------------------------
			PdfPTable tbl_khachhang =new PdfPTable(1);
			tbl_khachhang.setWidthPercentage(100);

			//--dong0 ten nguoi mua hang (CHU CUA HIEU)
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5.3f * CONVERT);
			para = new Paragraph(chucuahieu , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong1 ten khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( Donvi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong2 dia chi 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.05f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			cell.setFixedHeight(0.6f*CONVERT);
			para = new Paragraph( kh_Diachi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong3  so tai khoan khach hang + noi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			if(kh_sotaikhoan.length()<=0){
				kh_sotaikhoan="           ";
			}
			para = new Paragraph( kh_sotaikhoan , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			float[] withd_tbl= {16.0f,8.0f};
			//--dong4 hinh thuc thanh toan va ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.4f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			//cell add bang 2 cot
			PdfPTable tbl_thanhtoan_mst =new PdfPTable(2);
			tbl_thanhtoan_mst.setWidths(withd_tbl);
			tbl_thanhtoan_mst.setWidthPercentage(100);
			//--hinh thu thanh toan
			PdfPCell cell3 = new PdfPCell();
			cell3.setBorder(0);
			cell3.setVerticalAlignment(Element.ALIGN_LEFT);
			cell3.setPaddingLeft(4.0f * CONVERT);
			para = new Paragraph( hinhthucTT, new Font(bf, 10, Font.BOLD));
			cell3.addElement(para);
			tbl_thanhtoan_mst.addCell(cell3);

			//--ma so thue
			PdfPCell cell4 = new PdfPCell();
			cell4.setBorder(0);
			cell4.setVerticalAlignment(Element.ALIGN_LEFT);
			cell4.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_MST, new Font(bf, 10, Font.BOLD));

			cell4.addElement(para);
			tbl_thanhtoan_mst.addCell(cell4);
			cell.addElement(tbl_thanhtoan_mst);
			tbl_khachhang.addCell(cell);

			document.add(tbl_khachhang);
			// Table Content---------------------------------bang du lieu----------------------------------
			
			String[] spId = pxkBean.getSpId();
			String[] spMa = pxkBean.getSpMa(); 
			String[] spTen = pxkBean.getSpTen();
			String[] spDonvi = pxkBean.getSpDonvi();
			String[] spSoluong = pxkBean.getSpSoluong();
			String[] spDongia = pxkBean.getSpDongia();
			
			float[] tbl_withd = { 5.0f,18.0f, 60f, 30f, 10f , 15f, 25.0f, 25f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(2.0f*CONVERT);

			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//LAY SAN PHAM BAN
			String query = sqlIN_SANPHAM;
			ResultSet rsSP = db.getScrol(query);
			double tongtien = 0;
			String vat="";
			String tienvat= pxkBean.getTongVAT(); //lay ben hoa don
			String tongtien_vat =pxkBean.getTongtienAVAT();
			int stt = 1;
			int dongtang=0;
			
			
			try {
				

				String chuoi1="",chuoi2="",chuoicd="";
				int vitri1=0;
				
				if(spMa != null)
				{
					for(int i = 0; i < spMa.length; i++)
					{
						double soLUONG = Double.parseDouble(spSoluong[i]);
						double dongia = Double.parseDouble(spDongia[i]);
						double thanhtien = Math.floor(soLUONG*dongia) ;
						tongtien +=thanhtien;

						String solo="";
						while(rsSP.next())
						{
							System.out.println(" ma sp : "+ rsSP.getString("ma"));
							if(spMa[i].equals(rsSP.getString("ma"))){
								solo=rsSP.getString("solo")== null ?"" :rsSP.getString("solo").trim();
							}
						}
						if(solo.equals("NA")|| solo.equals("NA"))
							solo=" ";
						rsSP.beforeFirst();
						
						System.out.println("\n so lo : "+solo +"\n");

						String[] arr = new String[] { Integer.toString(stt),  spMa[i] , spTen[i], solo, spDonvi[i],
								DinhDangTraphacoDMS(formatter1.format(soLUONG)),
								DinhDangTraphacoDMS(formatter.format(dongia)),
								DinhDangTraphacoDMS(formatter1.format(thanhtien))+"đ" };

					//in
					for (int j = 0; j < tbl_withd.length; j++)
					{

						// NẾU LÀ TÊN SẢN PHẨM
						if(j==2)
						{
							if(arr[2].length() <= 35)
								cells = new PdfPCell(new Paragraph(arr[j],new Font(bf, 10, Font.BOLD)));
							else
							{
								chuoi1 = arr[j].substring(0, 35);
								System.out.println("chuoi 1: " + chuoi1);
								//  System.out.println("vitri 1: " + vitri1);
								vitri1 = chuoi1.lastIndexOf(" ");
								if(vitri1>=0){
								chuoicd = chuoi1.substring(0, vitri1);
								System.out.println("chuoi cd: " + chuoicd);
								chuoi2 = arr[j].substring(vitri1+1, arr[j].length());
								System.out.println("chuoi 2: " + chuoi2);
								cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));  
								}
								else
								{
									chuoicd = chuoi1.substring(0, 35);
									chuoi2 = chuoi2.substring(35 + 1,arr[2].length());
									
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));  
								}
								
							}
						}
						else
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));     


						if(j==2 ||j==1 || j==0 || j==3){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							if(j==0){
								//cells.setPaddingLeft(-0.8f*CONVERT);
								cells.setPaddingRight(-0.1f*CONVERT);
							}
						}
						else
							if(j==5 ||j==6||j==7)
							{
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}


						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setFixedHeight(0.6f*CONVERT);
						cells.setBorder(0);
						tbl_sanpham.addCell(cells);

					}
					stt++; 
					
					
					if(arr[2].length() > 35)
					{
						int dodaichuoi = chuoi2.length();
						System.out.println("chuoi222:"+chuoi2.length());
						while(dodaichuoi > 0)
						{
							int boiso=dodaichuoi/35;
							if(boiso > 0)
							{
								chuoi1 = chuoi2.substring(0,35);
								vitri1 = chuoi1.lastIndexOf(" ");
								if(vitri1>=0){
								System.out.println("chuoi 1: " + chuoi1);
								System.out.println("vitri 1: " + vitri1);
								chuoicd = chuoi1.substring(0, vitri1);
								System.out.println("chuoi cd: " + chuoicd);
								chuoi2 = chuoi2.substring(vitri1 + 1,dodaichuoi );
								System.out.println("chuoi 2: " + chuoi2);
								}
								else
								{
									chuoicd = chuoi1.substring(0, 35);
									chuoi2 = chuoi2.substring(35 + 1,dodaichuoi );
								}
							}
							else
							{
								chuoicd = chuoi2;
								chuoi2 = "";
							}

							for(int m = 0; m < arr.length; m++)
							{   
								if(m==2)
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));
								else
									cells = new PdfPCell(new Paragraph("", new Font(bf, 10, Font.BOLD)));

								if(m==2 ||m==1 || m==0 || m==3){
									cells.setHorizontalAlignment(Element.ALIGN_LEFT);
									if(m==0){

										cells.setPaddingRight(-0.1f*CONVERT);
									}
								}
								else
									if(m==5 ||m==6||m==7)
									{
										cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
									}
									else
									{
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);
									}


								cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
								cells.setFixedHeight(0.6f*CONVERT);
								cells.setBorder(0);
								tbl_sanpham.addCell(cells);
							}
							dodaichuoi = chuoi2.length();

							stt++;

						}
					}			
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(" so thu tu : "+ stt);
			//1 dong trong
			String[] arr_bosung1 = new String[] { " ", " " , " ", " "," ", " "," "," " };
			for (int j = 0; j < arr_bosung1.length; j++)
			{
				cells = new PdfPCell(new Paragraph(arr_bosung1[j], new Font(bf, 12, Font.BOLD)));
				cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setFixedHeight(0.6f*CONVERT);
				cells.setBorder(0);					
				tbl_sanpham.addCell(cells);
			}
			stt++;

			int kk=0;
			while(kk < 20-stt)
			{
				String[] arr_bosung = new String[] { " ", " " , " ", " "," ", " "," "," " };

				for (int j = 0; j < arr_bosung.length; j++)
				{
					cells = new PdfPCell(new Paragraph(arr_bosung[j], new Font(bf, 12, Font.BOLD)));
					cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.6f*CONVERT);
					cells.setBorder(0);

					tbl_sanpham.addCell(cells);
				}

				kk++;
			}



			document.add(tbl_sanpham);

		
			//lay top(1) vat %
			String qr_vat="select top(1) vat as vat from ERP_HOADON_SP where hoadon_fk='"+pxkBean.getId()+"'";
			ResultSet rs_vat=db.get(qr_vat);
			if(rs_vat!=null){
				try {
					while (rs_vat.next())
					{
						vat=rs_vat.getString("vat") ;
					}
					rs_vat.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			//-------Tao bang vat, tong tien 
			PdfPTable tbl_vat = new PdfPTable(2);
			//tbl_vat.setSpacingBefore(0.7f*CONVERT); // so dong trong
			tbl_vat.setWidthPercentage(100);
			tbl_vat.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.getDefaultCell().setBorder(0);
			float[] cr = { 18.0f*CONVERT, 6.0f*CONVERT };
			tbl_vat.setWidths(cr);
			PdfPCell cellss;
			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien don hang
			cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)) +"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(-0.5f*CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//vat
			cellss = new PdfPCell(new Paragraph(vat, new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(-0.3f*CONVERT);
			cellss.setPaddingLeft(3.0f * CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.addCell(cellss);

			//tien vat
			cellss = new PdfPCell(new Paragraph(tienvat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(-0.3f*CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien sau vat
			cellss = new PdfPCell(new Paragraph(tongtien_vat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingTop(0.3f*CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//doc tien thanh chu

			System.out.println( " doc tien: " + pxkBean.getTongtienAVAT());
			System.out.println( " doc tien: " + Long.parseLong (pxkBean.getTongtienAVAT().replaceAll(",", "") ));

			// Tien bang chu
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien(Long.parseLong (pxkBean.getTongtienAVAT().replaceAll(",", "") ));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cellss.setColspan(2);
			cellss.setPaddingTop(0.9f);
			cellss = new PdfPCell(new Paragraph(TienIN, new Font(bf, 10, Font.BOLDITALIC)));
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			cellss.setPaddingLeft(4.0f * CONVERT);

			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			document.add(tbl_vat);
			document.close();

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}



	
	
	
	

	private void CreatePxk(Document document, ServletOutputStream outstream, IErpHoadontaichinh pxkBean) throws IOException
	{
		try
		{

			dbutils db = new dbutils();

			//LAY THONG TIN NCC

			String npp_fk="";
			String khId="";
			String ctyTen=" ";
			String cty_MST =" ";
			String cty_Diachi=" ";
			String cty_Sotaikhoan= " ";
			String cty_Dienthoai= " ";
			String cty_Fax= " ";
			String khoxuat =" ";
			String hinhthucTT= " ";
			String ngayxuathd ="";
			
			
			//DON VI BAN: TONG CTY HO DCL
			String qr_ho="SELECT TEN, DIACHI, MASOTHUE, SOTK AS TAIKHOAN, nganhang, DIENTHOAI, FAX FROM NHACUNGCAP WHERE PK_SEQ=100001 ";
			System.out.println("\n thong tin npp dcl: "+qr_ho);
			ResultSet rsHo = db.get(qr_ho);
			if(rsHo!=null){
				try {
					if(rsHo.next())
					{
						ctyTen = rsHo.getString("TEN");
						cty_MST = rsHo.getString("MASOTHUE");
						cty_Diachi = rsHo.getString("DIACHI");
						cty_Sotaikhoan = rsHo.getString("TAIKHOAN")+" " +rsHo.getString("nganhang");
						cty_Dienthoai = rsHo.getString("DIENTHOAI");
						cty_Fax = rsHo.getString("FAX");
						rsHo.close();

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			//======================= thong tin don hang 
			String  qr_info="select hinhthuctt , ngayxuathd ,(select Ten from ERP_KHOTT  where PK_SEQ = erp_hoadon.kho_fk) as XUATTAIKHO, "+    
			" isnull(khachhang_fk,0) as khachhang_fk,isnull( npp_fk,0) as npp_fk from erp_hoadon where pk_seq="+pxkBean.getId();
			System.out.println("\n thong tin hoa don: "+qr_info);
			ResultSet rs_info=db.get(qr_info);
			if(rs_info!=null)
			{
				try {
					while(rs_info.next())
					{
						khoxuat = rs_info.getString("XUATTAIKHO");
						ngayxuathd=rs_info.getString("ngayxuathd");
						hinhthucTT=rs_info.getString("hinhthuctt");
						khId=rs_info.getString("khachhang_fk");
						npp_fk=rs_info.getString("npp_fk");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			System.out.println("\n npp_pf: "+npp_fk);
			System.out.println("\n ngay xuat hd: "+ngayxuathd);
			//LAY THONG TIN KHACHHANG	 neu khachhang_fk ==null thi lay don vi mua la npp
			String Donvi=" ";
			String kh_MST =" ";
			String kh_Diachi=" ";
			String kh_sotaikhoan=" ";
			String kh_nganhang=" ";
			String nguoimuahang=" ";
			String sql="";		

			if(npp_fk.length()>0 && !npp_fk.equals("0")){//LAY THEO NPP
				sql = " select PK_SEQ, TEN as DONVI, ' ' AS TEN ,DIACHI as DIACHI, isnull(MASOTHUE,'') as MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX,  "+    
				"isnull( SOTAIKHOAN,'') as TAIKHOAN,isnull( NGANHANG,'') as NGANHANG,   isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho  "+    
				"from NHAPHANPHOI WHERE PK_SEQ="+npp_fk;
			}
			else
				if(khId.length() > 0 &&  !khId.equals("0")){
					//LẤY THEO DỮ LIỆU MỚI LAY BEN KHACH HANG
					sql = "select TEN AS DONVI, isnull(CHUCUAHIEU,'') AS TEN, DIENTHOAI, DIACHI, isnull(MASOTHUE,'') as MASOTHUE, isnull(TAIKHOAN_FK,0) AS TAIKHOAN, "+    
					"  '' AS NGANHANG from khachhang WHERE PK_SEQ="+khId;
				}else
				{
					sql = " select PK_SEQ, ' ' as DONVI, ' ' AS TEN ,' ' as DIACHI, ' ' as MASOTHUE,' ' as DIENTHOAI, ' ' as FAX,  "+    
					" 0 as TAIKHOAN,' 'as NGANHANG,   ' ' as XUATTAIKHO "+    
					"from NHAPHANPHOI ";
				}

			System.out.println("\n Lấy DON VI MUA HANG: "+sql);
			ResultSet rsLayKH= db.get(sql);
			if(rsLayKH!=null){
				try {
					while(rsLayKH.next())
					{
						Donvi = rsLayKH.getString("DONVI");
						nguoimuahang=rsLayKH.getString("TEN");
						kh_MST = rsLayKH.getString("MASOTHUE");
						kh_Diachi = rsLayKH.getString("DIACHI");
						kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
						kh_nganhang=rsLayKH.getString("NGANHANG");

						rsLayKH.close();

					}  
					rsLayKH.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
			
			//lay thong tin phieuxuatkho
			String phieuxuatkho=" ";
			sql="select b.sonetid as sophieuxuat from ERP_YCXUATKHO_DDH a "+    
				 "inner join ERP_YCXUATKHO b on a.ycxk_fk =b.pk_seq "+    
				 "where a.hoadon_fk=" +pxkBean.getId();
			ResultSet rsSoxuatkho= db.get(sql);
			if(rsSoxuatkho!=null){
				try {
					while(rsSoxuatkho.next())
					{
						phieuxuatkho=rsSoxuatkho.getString("sophieuxuat");
					}  
					rsSoxuatkho.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
				
			/// ===================ham in
			
			 form_hoadon2(document,outstream,pxkBean, ctyTen, cty_MST, cty_Diachi,cty_Sotaikhoan, cty_Dienthoai,cty_Fax,
					 Donvi, kh_MST,hinhthucTT, ngayxuathd, nguoimuahang, kh_sotaikhoan,  kh_Diachi,phieuxuatkho, db);
			

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}



	private String getSTT(int stt)
	{
		if (stt < 10)
			return "000" + Integer.toString(stt);
		if (stt < 100)
			return "00" + Integer.toString(stt);
		if (stt < 1000)
			return "0" + Integer.toString(stt);
		return Integer.toString(stt);
	}


	private double roundNumer(double num, int dec)
	{
		double result = Math.round(num*Math.pow(10,dec))/Math.pow(10,dec);
		return result;
	}

	private boolean SoNgay (String ngayxuathd){
		boolean kt = false;
		int songay = 0;
		//NẾU NGÀY XUẤT HÓA ĐƠN > '2014-12-08' THÌ ĐƯA VỀ ĐỊNH DẠNG MỚI
		dbutils db = new dbutils();
		String layngay = "select datediff(DD,'2014-01-09', '"+ngayxuathd+"') songay";
		ResultSet checkngay = db.get(layngay);

		try{
			if(checkngay.next())
			{
				songay = checkngay.getInt("songay");
				checkngay.close();
			}
			if(songay >=0 ) kt = true;
		}
		catch (Exception e){
			e.printStackTrace();
			kt = false;
		}

		return kt;

	}

}
