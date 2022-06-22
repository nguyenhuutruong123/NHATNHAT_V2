package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.erp.beans.lenhsanxuat.IErpRacoloc;
import geso.dms.erp.beans.lenhsanxuat.IErpRacolocList;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpRacoloc;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpRacolocList;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpRacolocPdfSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	static final float CM = 28.346457f; 
       
    public ErpRacolocPdfSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		IErpRacoloc csxBean;
		
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length() == 0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    	    
	    String id = util.getId(querystring);
	   
	    csxBean = new ErpRacoloc(id);
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    csxBean.setCongtyId(ctyId);
	    csxBean.setId(id);
	    csxBean.setUserId(userId);
	    
	    csxBean.initPdf();
        
	    response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=PhieuRaColoc.pdf");
		
		ServletOutputStream outstream = response.getOutputStream();
		
		this.CreatePdf(outstream, csxBean);
	    
	    
	}

	private void CreatePdf(ServletOutputStream outstream, IErpRacoloc csxBean) 
	{
		csxBean.DbClose();
		
		float PAGE_LEFT = 2.0f*CM, PAGE_RIGHT = 2.0f*CM, PAGE_TOP = 1.0f*CM, PAGE_BOTTOM = 0.0f*CM; //cm
		Rectangle pageSize = new Rectangle(PageSize.A3.getWidth(), PageSize.A3.getHeight()/2);
		Document document = new Document(pageSize, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		
		int currentSpId = 0;
		int currentPage = 0;
		int stt = 1;
		int numSps = csxBean.getSpMaIds().length;
		int NUM_SANPHAM_PER_PAGE = 10;
		
		long numPages = Math.round(Math.ceil((double)numSps/NUM_SANPHAM_PER_PAGE));
		
		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###.###"); 
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 11, Font.NORMAL);
			Font fontbold = new Font(bf, 11, Font.BOLD);
			Font fontHeader1 = new Font(bf, 16, Font.BOLD);
			Font fontHeader2 = new Font(bf, 18, Font.BOLD);
			Font fontnomar=new Font(bf,6,Font.NORMAL);
			
			//SIZE 28
			float[] TABLE_SANPHAM_WIDTHS = { 2.0f*CM, 3.0f*CM, 8.0f*CM, 3.0f*CM, 3.0f*CM ,3.0f*CM, 3.0f*CM, 4.0f*CM, };
			float[] TABLE_FOOTER_WIDTHS = { 9.5f*CM, 9.0f*CM, 9.5f*CM, };
			
			//Align
			int[] TABLE_SANPHAM_ALIGNS = {Element.ALIGN_CENTER, Element.ALIGN_LEFT, Element.ALIGN_LEFT, Element.ALIGN_LEFT,Element.ALIGN_CENTER, Element.ALIGN_RIGHT, Element.ALIGN_RIGHT, Element.ALIGN_RIGHT, };
			
			//Ve cac trang pdf
			do 
			{
				currentPage++;
				
				Paragraph ngayin=new Paragraph("Ngày In: " + this.getDateTime(), fontnomar);
				ngayin.setSpacingAfter(2);
				ngayin.setSpacingBefore(-25);
				ngayin.setAlignment(Element.ALIGN_RIGHT);
				document.add(ngayin);
			
				//VẼ khung header (Logo Picture | Header Titles)
				Paragraph pxk = new Paragraph("Đơn vị: CÔNG TY CỔ PHẦN HÀNG TIÊU DÙNG PROVENCE", new Font(bf, 9, Font.NORMAL));
				pxk.setSpacingAfter(2);
				pxk.setAlignment(Element.ALIGN_LEFT);
				document.add(pxk);
				
                String diaChiCongTy = "Địa chỉ: Lầu 8, 161 Võ Văn Tần, Phường 6, Quận 3, Tp Hồ Chí Minh";
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdf.parse("2016-05-31");
	            Date date2 = sdf.parse(csxBean.getNgaythuchien());
	            
	            if(date2.after(date1)){
	            	diaChiCongTy = "Địa chỉ: 149B Trương Định, Phường 9, Quận 3, Tp.Hồ Chí Minh";
	            }
	            
				pxk = new Paragraph(diaChiCongTy, new Font(bf, 9, Font.NORMAL));
				pxk.setSpacingAfter(2);
				pxk.setAlignment(Element.ALIGN_LEFT);
				document.add(pxk);
				
				pxk = new Paragraph("MST: 0 3 1 0 7 7 6 0 7 1", new Font(bf, 9, Font.NORMAL));
				pxk.setSpacingAfter(2);
				pxk.setAlignment(Element.ALIGN_LEFT);
				document.add(pxk);
				
				Paragraph p = new Paragraph("PHIẾU NHẬP - XUẤT KHO", fontHeader2);
				p.setAlignment(Element.ALIGN_CENTER);
				document.add(p);
				
				p = new Paragraph("Số CT: " + csxBean.getId() + "\nTrang " + currentPage + "/" + numPages, font);
				p.setAlignment(Element.ALIGN_RIGHT);
				document.add(p);
				
				String ngaynhap = "";
				try {
					String[] _ngay = csxBean.getNgaythuchien().split("-");
					ngaynhap = "Ngày " + _ngay[2] + " tháng " + _ngay[1] + " năm " + _ngay[0];
				} catch(Exception e) {
					e.printStackTrace();
					ngaynhap = csxBean.getNgaythuchien();
				}
				
				p = new Paragraph(ngaynhap, font);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);

				p = new Paragraph("Lý do nhập: Nhập hàng RACOLOC", font);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);

				p = new Paragraph("Của Sản Phẩm: " + csxBean.gettenSp(), font);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				p = new Paragraph("Số lượng rã Coloc: " + csxBean.getSoluong(), font);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);

				p = new Paragraph("Nhập tại kho: " + csxBean.gettenkho(), font);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);

				p = new Paragraph("Ghi Chú: " + csxBean.getDiengiai(), font);
				p.setAlignment(Element.ALIGN_LEFT);
				document.add(p);
				
				//SANPHAM
				PdfPTable sanPhamTable = new PdfPTable(TABLE_SANPHAM_WIDTHS.length);
				sanPhamTable.setWidths(TABLE_SANPHAM_WIDTHS);
				sanPhamTable.setWidthPercentage(100);
				sanPhamTable.setSpacingBefore(0.2f * CM);
				
				PdfPCell cell;
				String[] spTitles = {"STT", "MÃ SỐ", "TÊN", "SỐ LÔ", "NGÀY SẢN XUẤT", "SỐ LƯỢNG", "ĐƠN GIÁ", "THÀNH TIỀN"};
				for(int i= 0; i < spTitles.length ; i++)
				{
					cell = new PdfPCell(new Paragraph(spTitles[i], fontbold));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPaddingBottom(0.2f * CM);
					sanPhamTable.addCell(cell);			
				}
				
				PdfPCell cells = new PdfPCell();
				
				int currentLocalSpId = 0;
				
				double tongSoLuong = 0, _sl;
				double tongDonGia = 0, _dg;
				double tongThanhTien = 0, _tt;
				while(currentSpId < numSps && currentLocalSpId < NUM_SANPHAM_PER_PAGE)
				{
					_sl = 0; _dg = 0; _tt = 0;
					try { _sl = Double.parseDouble(csxBean.getSoluongIds()[currentSpId].replaceAll(",", "")); } catch(Exception e) { }
					try { _dg = Double.parseDouble(csxBean.getDongiaIds()[currentSpId].replaceAll(",", "")); } catch(Exception e) { }
					try { _tt = Double.parseDouble(csxBean.getThanhtienIds()[currentSpId].replaceAll(",", "")); } catch(Exception e) { }
					tongSoLuong += _sl;
					tongDonGia += _dg;
					tongThanhTien += _tt;
					
					String[] arr = new String[] { 
						Integer.toString(stt),  
						csxBean.getSpMaIds()[currentSpId], 
						csxBean.getSpTenIds()[currentSpId], 
						csxBean.getSoloIds()[currentSpId], 
						csxBean.getNgaysxIds()[currentSpId], 
						csxBean.getSoluongIds()[currentSpId], 
						csxBean.getDongiaIds()[currentSpId],  
						csxBean.getThanhtienIds()[currentSpId],
					};
					
					for(int j = 0; j < spTitles.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], font));
						cells.setHorizontalAlignment(TABLE_SANPHAM_ALIGNS[j]);
						cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cells.setPadding(3.0f);
						
						sanPhamTable.addCell(cells);
					}

					currentLocalSpId++;
					currentSpId++;
					stt++;
				}
				
				//Tổng cộng
				spTitles = new String[] {"TỔNG CỘNG", formatter.format(tongSoLuong), formatter.format(tongDonGia), formatter.format(tongThanhTien)};
				for(int i = 0; i < spTitles.length ; i++)
				{
					cell = new PdfPCell(new Paragraph(spTitles[i], fontbold));
					cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setPaddingBottom(0.2f * CM);
					if(i == 0) { cell.setColspan(5); }
					sanPhamTable.addCell(cell);
				}
				
				document.add(sanPhamTable);
				
				//FOOTER
				PdfPTable tableFooter = new PdfPTable(TABLE_FOOTER_WIDTHS.length);
				tableFooter.setWidthPercentage(100);
				tableFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableFooter.setWidths(TABLE_FOOTER_WIDTHS);
				tableFooter.setSpacingBefore(0.2f * CM);
				
				String[] footers = new String[] { "NGƯỜI LẬP", "THỦ KHO", "KẾ TOÁN" };
				for(int j = 0; j < footers.length; j++) {
					cell = new PdfPCell(new Paragraph(footers[j], fontbold));
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setBorder(0);
					tableFooter.addCell(cell);
				}
				document.add(tableFooter);
				
				//Them trang moi neu con san pham chua in
				if(currentSpId < numSps) {
					document.newPage();
				}
			} while(currentSpId < numSps);

			document.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}
	public String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss, dd-MM-yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
}
