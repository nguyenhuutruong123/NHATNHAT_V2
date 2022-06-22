package geso.dms.distributor.servlets.hoadontaichinhNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinhNPP.*;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.*;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;

import javax.print.DocFlavor.STRING;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
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
import com.sun.mail.util.QEncoderStream;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ErpHoadontaichinhNPPPdfSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public ErpHoadontaichinhNPPPdfSvl()
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

		IErpHoadontaichinhNPP pxkBean = new ErpHoadontaichinhNPP(id);
		pxkBean.setUserId(userId);
		pxkBean.init();

		System.out.print("\n id hoa don: "+id);

		String nppId = util.antiSQLInspection(request.getParameter("nppId"));
		if(nppId==null)
			nppId="";

		/*String	 query_sp = "select  a.MA as MA, isnull(a.ten,'') as TEN, solo as solo, a.donvi AS DONVI, a.soluong AS SOLUONG, a.dongia  AS DONGIA, a.NGAYHETHAN AS NGAYHETHAN "+    
		"from ERP_HOADONNPP_SP_CHITIET a where a.hoadon_fk ="+pxkBean.getId();*/

		String	 query_sp = "select  a.MA as MA, solo as solo, a.NGAYHETHAN AS NGAYHETHAN "+    
		"from ERP_HOADONNPP_SP_CHITIET a where a.hoadon_fk ="+pxkBean.getId() +" group by ma,NGAYHETHAN,solo";

		System.out.print("\n cau qr_sp: "+query_sp);

		String task = request.getParameter("task");    
		if (querystring.indexOf("pdf") > 0)
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=HoadontaichinhNpp.pdf");
			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();
			this.CreatePxk(document, outstream, pxkBean,nppId, query_sp);
			document.close();
			String msg = this.CapnhatTT(id);
			pxkBean.setMsg(msg);
		} 

	}


	///====================================================

	private void form_hoadon(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM, IErpHoadontaichinhNPP  pxkBean,String mahoadon,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_nganhang,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String nguoimuahang,
			String kh_sotaikhoan, String kh_Diachi,String phieuxuatkho, dbutils db)
	{

		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			//document = new Document(PageSize.A4,2.0f*CONVERT,1.5f*CONVERT,1.7f*CONVERT, 0.0f*CONVERT);
			document.setMargins(1f*CONVERT, 1f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B

			PdfWriter writer = PdfWriter.getInstance(document, outstream);

			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;

			document.open() ;


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);


			PdfPTable tableheader =new PdfPTable(2);
			float []wky={16.0f,3.0f};
			tableheader.setWidthPercentage(100);	
			tableheader.setWidths(wky);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4.0f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			String [] ngayHD = ngayxuatHD.split("-");
			para= new Paragraph("",new Font(bf, 8, Font.BOLD));
			if(ngayHD.length>=3){
				chunk =new Chunk(ngayHD[2] + "                        " + ngayHD[1] +"                             " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
				chunk.setTextRise(0.5f*CONVERT);
				para.add(chunk);
				para.setAlignment(Element.ALIGN_CENTER);

			}
			cell.addElement(para);
			tableheader.addCell(cell);

			//phieu xuat kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4.0f*CONVERT);
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
			cell.setPaddingTop(0.67f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			//para = new Paragraph("                   "+ ctyTen , new Font(bf, 10, Font.BOLD));
			para = new Paragraph( ctyTen , new Font(bf, 10, Font.BOLD));

			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong2 ma so thue npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( cty_MST , new Font(bf, 10, Font.BOLD));
			//para = new Paragraph( "ma so thue" , new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong3 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( cty_Diachi, new Font(bf, 10, Font.BOLD));
			//para = new Paragraph(" 640/8 phan van tri , go vap, hcm", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_npp.addCell(cell);


			/*//--dong4 SDT npp
		cell = new PdfPCell();
		cell.setBorder(0);
		cell.setVerticalAlignment(Element.ALIGN_LEFT);
		cell.setPaddingLeft(3.0f * CONVERT);
		para = new Paragraph( cty_Dienthoai, new Font(bf, 10, Font.BOLD));
		//para = new Paragraph("so dien thoai", new Font(bf, 10, Font.NORMAL));
		cell.addElement(para);
		tbl_npp.addCell(cell);*/

			//--dong5 Stk npp
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan="";
			}
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( cty_Sotaikhoan , new Font(bf, 10, Font.BOLD));
			//para = new Paragraph( "so tai khoan", new Font(bf, 10, Font.NORMAL));
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
			cell.setPaddingLeft(5.0f * CONVERT);
			para = new Paragraph(nguoimuahang , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong1 ten khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			/*cell.setPaddingTop(0.67f * CONVERT);
		cell.setPaddingLeft(2.6f * CONVERT);*/
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( Donvi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong2 dia chi va so tai khoan khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);

			//cell add bang 2 cot
			PdfPTable tbl_diachi_stk =new PdfPTable(2);
			float[] withd_tbl= {16.0f,8.0f};
			tbl_diachi_stk.setWidths(withd_tbl);
			tbl_diachi_stk.setWidthPercentage(100);
			//--dia chi
			PdfPCell cell1 = new PdfPCell();
			cell1.setBorder(0);
			cell1.setVerticalAlignment(Element.ALIGN_LEFT);
			cell1.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph(kh_Diachi, new Font(bf, 10, Font.BOLD));
			//para = new Paragraph("63/7 pho tong, hang chen,ho tay, ha noi", new Font(bf, 10, Font.NORMAL));
			cell1.addElement(para);
			tbl_diachi_stk.addCell(cell1);
			//--so tai khoankhoan
			if(kh_sotaikhoan.equals("0")){
				kh_sotaikhoan="";
			}
			PdfPCell cell2 = new PdfPCell();
			cell2.setBorder(0);
			cell2.setVerticalAlignment(Element.ALIGN_LEFT);
			cell2.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_sotaikhoan, new Font(bf, 10, Font.BOLD));
			//para = new Paragraph( "so tai khoan" , new Font(bf, 10, Font.NORMAL));
			cell2.addElement(para);
			tbl_diachi_stk.addCell(cell2);

			cell.addElement(tbl_diachi_stk);
			tbl_khachhang.addCell(cell);

			//--dong3 hinh thuc thanh toan va ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);
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

			float[] tbl_withd = {7.0f, 17.0f, 60f, 25f,  10f , 15.0f, 15.0f, 20f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(30.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;


			double tongtien = 0;
			String vat="";
			String tienvat= pxkBean.getTongVAT();
			String tongtien_vat =pxkBean.getTongtienAVAT();
			int stt = 1;

			//==================== lay tu qr_sp 
			String query = sqlIN_SANPHAM;
			ResultSet rsSP = db.getScrol(query);


			try {
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
						rsSP.beforeFirst();

						
						System.out.println("\n so lo : "+solo +"\n");

						String[] arr = new String[] { Integer.toString(stt),  spMa[i] , spTen[i], solo, spDonvi[i],
								DinhDangTraphacoDMS(formatter1.format(soLUONG)),
								DinhDangTraphacoDMS(formatter.format(dongia)),
								DinhDangTraphacoDMS(formatter1.format(thanhtien))+"đ" };

						for (int j = 0; j < tbl_withd.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));
							//canh f ormat
							if(j==2 || j==1){
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
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
							cells.setBorder(0);

							tbl_sanpham.addCell(cells);	
						}
						stt++;

					}
				}

				document.add(tbl_sanpham);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//================================================== 

			//lay top(1) vat %
			String qr_vat="select top(1) thuevat as vat from ERP_HOADONNPP_SP_CHITIET where hoadon_fk='"+pxkBean.getId()+"'";
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
			tbl_vat.setSpacingBefore((20-stt)*0.6f*CONVERT); // so dong trong
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
			cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien))+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//vat
			cellss = new PdfPCell(new Paragraph(vat, new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingLeft(3.0f * CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.addCell(cellss);

			//tien vat
			cellss = new PdfPCell(new Paragraph(tienvat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien sau vat
			cellss = new PdfPCell(new Paragraph(tongtien_vat.replaceAll(",", ".")+"đ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tbl_vat.addCell(cellss);

			//doc tien thanh chu
			// Tien bang chu
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien(Long.parseLong(pxkBean.getTongtienAVAT().replaceAll(",", "")));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cellss.setColspan(2);
			cellss.setPaddingTop(0.6f);
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

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}



	private void form_hoadon1(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM,IErpHoadontaichinhNPP pxkBean,String mahoadon,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String nguoimuahang,String kh_sotaikhoan, String kh_Diachi, dbutils db)
	{
		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");

			document.setPageSize(PageSize.A4);
			//document = new Document(PageSize.A4,2.0f*CONVERT,1.5f*CONVERT,1.7f*CONVERT, 0.0f*CONVERT);
			document.setMargins(0.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B

			PdfWriter writer = PdfWriter.getInstance(document, outstream);

			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;

			document.open() ;


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);


			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(2.4f * CONVERT);
			cell.setPaddingLeft(2.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);


			//NGAY THANG NAM, VA SO HOA DON
			String [] ngayHD = ngayxuatHD.split("-");

			Paragraph pxk = new Paragraph("",new Font(bf, 8, Font.BOLDITALIC));
			chunk =new Chunk(ngayHD[2] + "                        " + ngayHD[1] +"                             " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
			pxk.add(chunk);


			pxk.setAlignment(Element.ALIGN_CENTER);
			pxk.setSpacingAfter(2);
			cell.addElement(pxk);

			tableheader.addCell(cell);
			document.add(tableheader);

			// ================================ADD INFO NHA PHAN PHOI
			PdfPTable tbl_npp =new PdfPTable(1);
			tbl_npp.setWidthPercentage(100);

			//--dong1 ten npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(0.67f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			//para = new Paragraph("                   "+ ctyTen , new Font(bf, 10, Font.BOLD));
			para = new Paragraph( ctyTen , new Font(bf, 10, Font.BOLD));

			cell.addElement(para);
			tbl_npp.addCell(cell);


			//--dong2 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( cty_Diachi, new Font(bf, 10, Font.BOLD));
			//para = new Paragraph(" 640/8 phan van tri , go vap, hcm", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong3 Stk npp + ngan hang
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan="";
			}
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( cty_Sotaikhoan, new Font(bf, 10, Font.BOLD));
			//para = new Paragraph( "so tai khoan", new Font(bf, 10, Font.NORMAL));
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
			cell.setPaddingLeft(5.0f * CONVERT);
			para = new Paragraph(nguoimuahang , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong1 ten khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			/*cell.setPaddingTop(0.67f * CONVERT);
		cell.setPaddingLeft(2.6f * CONVERT);*/
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( Donvi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong2 dia chi 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( kh_Diachi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong3  so tai khoan khach hang + noi

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( kh_sotaikhoan+"              "+ "ngan hang" , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			float[] withd_tbl= {16.0f,8.0f};
			//--dong4 hinh thuc thanh toan va ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);
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
			//para = new Paragraph("hinh thuc thanh toan", new Font(bf, 10, Font.NORMAL));
			cell3.addElement(para);
			tbl_thanhtoan_mst.addCell(cell3);

			//--ma so thue
			PdfPCell cell4 = new PdfPCell();
			cell4.setBorder(0);
			cell4.setVerticalAlignment(Element.ALIGN_LEFT);
			cell4.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_MST, new Font(bf, 10, Font.BOLD));
			//para = new Paragraph("ma so thue", new Font(bf, 10, Font.NORMAL));

			cell4.addElement(para);
			tbl_thanhtoan_mst.addCell(cell4);
			cell.addElement(tbl_thanhtoan_mst);
			tbl_khachhang.addCell(cell);

			document.add(tbl_khachhang);




			// Table Content---------------------------------bang du lieu----------------------------------

			//---------------------------------------------------------------------------

			float[] tbl_withd = { 7.0f, 15.0f, 50f, 20f,15f , 15.0f, 15.0f, 25f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(30.0f );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;
			String query = sqlIN_SANPHAM;
			ResultSet rsSP = db.get(query);
			double tongtien = 0;
			String vat="";
			String tienvat= pxkBean.getTongVAT();
			String tongtien_vat =pxkBean.getTongtienAVAT();
			int stt = 1;
			try {
				while(rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = soLUONG*dongia;

					String ngayhethan= rsSP.getString("NGAYHETHAN");
					String solo=rsSP.getString("solo");

					if(ngayhethan.length()>0){
						ngayhethan=ngayhethan.substring(8)+"/"+ngayhethan.substring(2,4);
						solo+= " - "+ ngayhethan;
					}

					tongtien +=thanhtien;
					String[] arr = new String[] { Integer.toString(stt), rsSP.getString("MA") , rsSP.getString("TEN"),solo , 
							rsSP.getString("DONVI"),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), DinhDangTraphacoDMS(formatter.format(dongia)),DinhDangTraphacoDMS(formatter1.format(thanhtien)) };


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));
						//canh format
						if(j==2){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						else
						{
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
						}

						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setBorder(0);

						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSP.close();
				document.add(tbl_sanpham);

			} catch (Exception e) {
				// TODO: handle exception
			}


			//lay top(1) vat %
			String qr_vat="select top(1) vat as vat from ERP_HOADONNPP_SP where hoadon_fk='"+pxkBean.getId()+"'";
			ResultSet rs_vat=db.get(qr_vat);
			if(rs_vat!=null){
				try {
					while (rs_vat.next())
					{
						vat=rs_vat.getString("vat") ;
					}
					rs_vat.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}


			//-------Tao bang vat, tong tien 
			PdfPTable tbl_vat = new PdfPTable(2);
			tbl_vat.setSpacingBefore((25-stt)*0.6f*CONVERT); // so dong trong
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
			cellss = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)), new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//vat
			cellss = new PdfPCell(new Paragraph(vat, new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			cellss.setPaddingLeft(3.0f * CONVERT);
			cellss.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_vat.addCell(cellss);

			//tien vat
			cellss = new PdfPCell(new Paragraph(tienvat.replaceAll(",", "."), new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//rong
			cellss = new PdfPCell(new Paragraph(" ", new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//tong tien sau vat
			cellss = new PdfPCell(new Paragraph(tongtien_vat.replaceAll(",", "."), new Font(bf, 10, Font.BOLD)));
			cellss.setBorder(0);
			tbl_vat.addCell(cellss);

			//doc tien thanh chu
			// Tien bang chu
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien(Long.parseLong(pxkBean.getTongtienAVAT().replaceAll(",", "")));
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cellss.setColspan(2);
			cellss.setPaddingTop(0.6f);
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

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}


	//=======================================================================

	private String CapnhatTT(String id) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			String query = "";
			String trangthai="";
			// Kiem tra trạng thái hiện tại của Hóa đơn

			query = " select trangthai from ERP_HOADONNPP where pk_seq = "+ id +" ";
			ResultSet rs = db.get(query);
			if(rs!= null)
			{
				while(rs.next())
				{
					trangthai = rs.getString("trangthai");
				}rs.close();
			}

			if(!trangthai.equals("3") && !trangthai.equals("5"))
			{
				// Cập nhật trạng thái Đã in
				query = "update ERP_HOADONNPP set trangthai = '4' where pk_seq = '" + id + "' ";
				if(!db.update(query))
				{
					msg = "Không thể cập nhật ERP_HOADONNPP " + query;
					db.getConnection().rollback();
					return msg;
				}

			}

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}

		return "";
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	private void CreatePxk(Document document, ServletOutputStream outstream, 
			IErpHoadontaichinhNPP pxkBean, String nppId,String query_sp) throws IOException
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
			String nguoimuahang=" ";
			double chietkhauDH = 0;
			String cty_nganhang=" ";

			String sql_ncc;
			sql_ncc=" select npp.PK_SEQ, npp.TEN ,npp.DIACHI as DIACHI, npp.MASOTHUE,npp.DIENTHOAI, isnull( FAX,'') as FAX, "+        
			"  isnull( npp.SOTAIKHOAN,'') as SOTAIKHOAN,isnull(npp.XUATTAIKHO,' ') as XUATTAIKHO,  "+    
			" isnull(npp.nganhang,'') as nganhang from NHAPHANPHOI npp "+    
			"where PK_SEQ = (select npp_fk from ERP_HOADONNPP where pk_seq = '"+ pxkBean.getId() +"') ";

			System.out.println(" \n Lấy TT CTY "+sql_ncc +"\n");
			ResultSet rsINFO = db.get(sql_ncc);
			if(rsINFO!=null){
				try {
					if(rsINFO.next())
					{
						//khoxuat = rsINFO.getString("XUATTAIKHO");
						ctyTen = rsINFO.getString("TEN");
						cty_MST = rsINFO.getString("MASOTHUE");
						cty_Diachi = rsINFO.getString("DIACHI");
						cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN")+" tại " +rsINFO.getString("nganhang");
						cty_Dienthoai = rsINFO.getString("DIENTHOAI");
						cty_Fax = rsINFO.getString("FAX");
						// cty_nganhang = rsINFO.getString("nganhang");
						rsINFO.close();

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}


			//======================= thong tin don hang 
			String  qr_info="select hinhthuctt , ngayxuathd ,(select Ten from ERP_KHOTT  where PK_SEQ = ERP_HOADONNPP.kho_fk) as XUATTAIKHO, chietkhau,  "+    
			" khachhang_fk as khachhang_fk, npp_fk as npp_fk from ERP_HOADONNPP where pk_seq="+pxkBean.getId();
			System.out.println("\n thong tin hoa don: "+qr_info);
			ResultSet rs_info=db.get(qr_info);
			if(rs_info!=null)
			{
				try {
					while(rs_info.next())
					{
						npp_fk = rs_info.getString("NPP_FK")== null ? "" :rs_info.getString("NPP_FK") ;
						khId = rs_info.getString("KHACHHANG_FK")== null ? "" :rs_info.getString("KHACHHANG_FK");

						khoxuat = rs_info.getString("XUATTAIKHO");
						ngayxuathd=rs_info.getString("ngayxuathd");
						hinhthucTT=rs_info.getString("hinhthuctt");
						khId=rs_info.getString("khachhang_fk");
						npp_fk=rs_info.getString("npp_fk");
						chietkhauDH = rs_info.getDouble("chietkhauDH");
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}


			//LAY THONG TIN KHACHHANG	
			String Donvi=" ";
			String kh_MST =" ";
			String kh_Diachi=" ";
			String kh_sotaikhoan=" ";
			String sql="";		
			//ban doi tac lay tu npp
			System.out.println("\n loai khach hang: "+pxkBean.getLoaiXHD() +"\n");
			if(pxkBean.getLoaiXHD().equals("0")){

				sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,  isnull( kh.masothue,'') as masothue ,isnull(kh.sotaikhoan,0) as taikhoan  "+       
				"\n from NHAPHANPHOI kh where kh.pk_seq="+pxkBean.getNppId();


				System.out.println("\n Lấy TT KH doi tac "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							nguoimuahang=rsLayKH.getString("chucuahieu");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
							// kh_nganhang=rsLayKH.getString("NGANHANG");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 

			}

			//ban khach hang etc
			if (pxkBean.getLoaiXHD().equals("1")) {
				sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan  "+     
				"\n from KHACHHANG kh where kh.pk_seq="+pxkBean.getKhId();  

				System.out.println("\n Lấy TT KH etc: "+sql +"\n");

				ResultSet rsLayKH= db.get(sql);
				if(rsLayKH!=null){
					try {
						while(rsLayKH.next())
						{
							Donvi = rsLayKH.getString("DONVI");
							nguoimuahang=rsLayKH.getString("chucuahieu");
							kh_MST = rsLayKH.getString("MASOTHUE");
							kh_Diachi = rsLayKH.getString("DIACHI");
							kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
							rsLayKH.close();

						}  
						rsLayKH.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} 
			}

			//lay thong tin phieuxuatkho
			String phieuxuatkho=" ";
			sql="select b.sonetid as sophieuxuat from ERP_YCXUATKHONPP_DDH a "+    
			"\n inner join ERP_YCXUATKHONPP b on a.ycxk_fk =b.pk_seq "+    
			" \n where a.hoadon_fk=" +pxkBean.getId();

			System.out.println(" qr phieuxuat kho: "+sql);
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

			//doi khoang trang neu khong co data
			ctyTen=doikhoangtrang(ctyTen);
			cty_MST=doikhoangtrang(cty_MST);
			cty_Diachi=doikhoangtrang(cty_Diachi);
			cty_Sotaikhoan=doikhoangtrang(cty_Sotaikhoan);
			cty_Dienthoai=doikhoangtrang(cty_Dienthoai);
			cty_Fax=doikhoangtrang(cty_Fax);
			Donvi=doikhoangtrang(Donvi);
			kh_MST=doikhoangtrang(kh_MST);
			hinhthucTT=doikhoangtrang(hinhthucTT);
			nguoimuahang=doikhoangtrang(nguoimuahang);
			//lay thong tin danh sach san pham 		
			System.out.println("\n [ERP_DONDATHANG_SANPHAM]"+query_sp +"\n");

			/// ===================ham in
			if(nppId.equals("100065") || nppId.equals("100087")) // mau in cho chi nhanh vinhx long va tien giang 
			{
				form_hoadon2(document, outstream, query_sp, pxkBean,  ctyTen, cty_MST,
						cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, kh_MST, hinhthucTT, ngayxuathd, nguoimuahang, 
						kh_sotaikhoan, kh_Diachi,phieuxuatkho, db);
			}
			else // mau in cho chi nhanh khac
			form_hoadon(document, outstream, query_sp, pxkBean, pxkBean.getId(), 
					ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan,cty_nganhang, cty_Dienthoai, 
					cty_Fax, Donvi, kh_MST, hinhthucTT, ngayxuathd, nguoimuahang, kh_sotaikhoan, kh_Diachi,phieuxuatkho, db);




		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
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
	
	


	private void form_hoadon2(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM,
			IErpHoadontaichinhNPP pxkBean,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi,
			 String phieuxuatkho,dbutils db)
	{

		

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
			cell.setPaddingTop(-0.8f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( ctyTen  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);


			//--dong2 ma dia chi npp
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(1.5f * CONVERT);
			cell.setPaddingTop(-0.3f * CONVERT);
			para = new Paragraph( cty_Diachi +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_npp.addCell(cell);

			//--dong3 Stk npp + ngan hang
			if(cty_Sotaikhoan.equals("0"))
			{
				cty_Sotaikhoan="";
			}
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.5f * CONVERT);
			cell.setPaddingTop(-0.1f * CONVERT);
			para = new Paragraph( cty_Sotaikhoan +" ", new Font(bf, 10, Font.BOLD));
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
			cell.setPaddingLeft(4.3f * CONVERT);
			para = new Paragraph(chucuahieu  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong1 ten khach hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( Donvi  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong2 dia chi 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.05f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( kh_Diachi  +" ", new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);


			//--dong3  so tai khoan khach hang + noi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			if(kh_sotaikhoan.length()<=0){
				kh_sotaikhoan="           ";
			}
			para = new Paragraph( kh_sotaikhoan  +" ", new Font(bf, 10, Font.BOLD));
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
			cell3.setPaddingLeft(3.0f * CONVERT);
			para = new Paragraph( hinhthucTT  +" ", new Font(bf, 10, Font.BOLD));
			cell3.addElement(para);
			tbl_thanhtoan_mst.addCell(cell3);

			//--ma so thue
			PdfPCell cell4 = new PdfPCell();
			cell4.setBorder(0);
			cell4.setVerticalAlignment(Element.ALIGN_LEFT);
			cell4.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph(kh_MST +" ", new Font(bf, 10, Font.BOLD));

			cell4.addElement(para);
			tbl_thanhtoan_mst.addCell(cell4);
			cell.addElement(tbl_thanhtoan_mst);
			tbl_khachhang.addCell(cell); 

			document.add(tbl_khachhang);
			
			
			PdfPTable tbl_tong = new PdfPTable(1);
			tbl_tong.setSpacingBefore(2.0f*CONVERT);
			tbl_tong.setWidthPercentage(110f);
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(0.5f*CONVERT);
			// Table Content---------------------------------bang du lieu----------------------------------
			
			String[] spId = pxkBean.getSpId();
			String[] spMa = pxkBean.getSpMa(); 
			String[] spTen = pxkBean.getSpTen();
			String[] spDonvi = pxkBean.getSpDonvi();
			String[] spSoluong = pxkBean.getSpSoluong();
			String[] spDongia = pxkBean.getSpDongia();
			
			//float[] tbl_withd = { 5.0f,18.0f, 60f, 30f, 10f , 15f, 25.0f, 25f };
		//	float[] tbl_withd = { 5.0f,18.0f, 60f, 30f, 10f , 12f, 17.0f, 25f,10f };
			float[] tbl_withd = { 5.0f,18.0f, 60f, 25f, 10f , 10f, 17.0f, 35f,10f };
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
			int dongtang=1;

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
								DinhDangTraphacoDMS(formatter1.format(thanhtien))+"đ" ," "};

					//in
					for (int j = 0; j < tbl_withd.length; j++)
					{

						// NẾU LÀ TÊN SẢN PHẨM
						if(j==2)
						{
							if(arr[2].trim().length() <= 35)
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
					dongtang++;
					
					if(arr[2].trim().length() > 35)
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

							dongtang++;

						}
					}
					
					//het if
			
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			System.out.println(" so thu tu : "+ stt);
			//1 dong trong
			String[] arr_bosung1 = new String[] { " ", " " , " ", " "," ", " "," "," " ," "};
			for (int j = 0; j < arr_bosung1.length; j++)
			{
				cells = new PdfPCell(new Paragraph(arr_bosung1[j], new Font(bf, 12, Font.BOLD)));
				cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setFixedHeight(0.6f*CONVERT);
				cells.setBorder(0);					
				tbl_sanpham.addCell(cells);
			}
			dongtang++;
			
			
			int kk=0;
			while(kk <=19-dongtang)
			{
				String[] arr_bosung = new String[] { " ", " " , " ", " "," ", " "," "," "," " };

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


			//giam them 1 dong 24/10/2014
			for (int i = 0; i < 1; i++) {
				String[] arr_bosung = new String[] {" ", " " , " ", " "," ", " "," "," "," " };

				for (int j = 0; j < arr_bosung.length; j++)
				{
					cells = new PdfPCell(new Paragraph(arr_bosung[j], new Font(bf, 8, Font.BOLD)));
					cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					cells.setFixedHeight(0.6f*CONVERT);
					cells.setBorder(0);

					tbl_sanpham.addCell(cells);
				}

			}


			//document.add(tbl_sanpham);
			
			
			
			cell.addElement(tbl_sanpham);
			tbl_tong.addCell(cell);
			document.add(tbl_tong);

		
			//lay top(1) vat %
			String qr_vat="select top(1) thuevat as vat from ERP_HOADONNPP_SP_CHITIET where hoadon_fk='"+pxkBean.getId()+"'";
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

	String doikhoangtrang(String s){
		if(s.length()<=0){
			s=" ";
		}
		return s;
	}

	
	

}
