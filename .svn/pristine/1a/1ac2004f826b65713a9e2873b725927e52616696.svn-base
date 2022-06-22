package geso.dms.erp.servlets.lenhsanxuat;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import geso.dms.erp.beans.lenhsanxuat.*;
import geso.dms.erp.beans.lenhsanxuat.imp.*;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ErpYeucaunguyenlieuPdfSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
  
    public ErpYeucaunguyenlieuPdfSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		//String userTen = (String) session.getAttribute("userTen");  	

		if (userId.length() == 0)
		    userId = request.getParameter("userId");
			   
		String id = request.getParameter("id");
			
		IErpYeucaunguyenlieu ycnlBean = new ErpYeucaunguyenlieu(id);
		ycnlBean.setUserId(userId);
	    
	    String task = request.getParameter("task");
	    if(task.equals("chuyenNL"))
	    {
		    ycnlBean.initChuyenNLPdf();
		    
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition"," inline; filename=PhieuChuyenNguyenLieu.pdf");
			
			Document document = new Document(PageSize.LETTER);
			ServletOutputStream outstream = response.getOutputStream();
			
			this.CreatePhieuChuyenNL(document, outstream, ycnlBean);
	    }
	    else
	    {
	    	if(task.equals("yeucauNL"))
	    	{
	    		ycnlBean.initYeucauNLPdf();
			    
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition"," inline; filename=PhieuYeuCauNguyenLieu.pdf");
				
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();
				
				this.CreatePhieuYeuCauNL(document, outstream, ycnlBean);
	    	}
	    }
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.doGet(request, response);
	}
	
	private void CreatePhieuYeuCauNL(Document document, ServletOutputStream outstream, IErpYeucaunguyenlieu ycnlBean) throws IOException
	{
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,###"); 
			
			PdfWriter.getInstance(document, outstream);
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			 
			Paragraph pxk = new Paragraph("Số phiếu..: " + ycnlBean.getId(), new Font(bf, 8, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setSpacingBefore(-25);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("PHIẾU YÊU CẦU NGUYÊN LIỆU", font);
			pxk.setSpacingAfter(10);
			pxk.setSpacingBefore(5);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
		
			PdfPTable sanpham = new PdfPTable(8);
			sanpham.setKeepTogether(false);
			sanpham.setSplitLate(false);
			sanpham.setWidthPercentage(100);
			sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] withsKM = {35.0f, 10.0f, 15.0f, 15.0f, 15.0f, 15.0f, 12.0f, 10.0f};
			sanpham.setWidths(withsKM);
			
			String[] th = new String[]{"Sản phẩm", "Số lô", " ", "Kho nhận", "Khu", "Bean", "Số lượng", "Đơn vị"};
			PdfPCell[] cell = new PdfPCell[8];
			for(int i= 0; i < 8 ; i++)
			{
				cell[i] = new PdfPCell(new Paragraph(th[i], new Font(bf, 9, Font.NORMAL)));
				cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
				
				if(i >= 6)
					cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[i].setBorder(0);
				cell[i].setPadding(4);
				
				sanpham.addCell(cell[i]);	
			}
		    
			PdfPCell space = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
			space.setColspan(8);
			space.setPadding(2.0f);
			space.setBorderWidthLeft(0);
			space.setBorderWidthRight(0);
			space.setBorderWidthTop(0);
			space.setBorderWidthBottom(0.8f);
			sanpham.addCell(space);
			
			space = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
			space.setColspan(8);
			space.setPadding(2.0f);
			space.setBorder(0);
			sanpham.addCell(space);
			
			List<IYeucau> spList = ycnlBean.getSpList();
			PdfPCell cells = new PdfPCell();

			int stt = 0;
			for(int i = 0; i <= spList.size(); i++)
			{
				if(i < spList.size())
				{
					IYeucau sp = (IYeucau)spList.get(i);
					List<ISpDetail> spDetail = sp.getSpDetailList();
					for(int k = 0; k < spDetail.size(); k++)
					{
						ISpDetail detail = spDetail.get(k);
						
						String[] soluong = detail.getSoluong().split(" - "); //0 - soluong yeu cau, 1 - soluong nhap, 2 - con lai
						
						String[] arr = new String[]{sp.getMa(), detail.getSolo(), "Yêu cầu", " ", " ", " ", 
								formatter.format(Long.parseLong(sp.getSoluongYC())), detail.getMa()};
						
						for(int j = 0; j < 8; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 8, Font.NORMAL)));
							if(j >= 5)
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(2.0f);
							cells.setBorder(0);
							
							sanpham.addCell(cells);
						}
						
						String[] arr2 = new String[]{sp.getTen(), "Nhận", detail.getVitriId(), detail.getKhu(), detail.getVitri(), formatter.format(Long.parseLong(soluong[1])), " "};
						
						for(int j = 0; j < 7; j++)
						{
							cells = new PdfPCell(new Paragraph(arr2[j], new Font(bf, 8, Font.NORMAL)));
							if(j >= 5)
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							
							if(j == 0)
								cells.setColspan(2);
							
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(2.0f);
							cells.setBorder(0);
							
							sanpham.addCell(cells);
						}
						
						String[] arr3 = new String[]{" ", "Còn lại", " ", " ", " ", formatter.format(Long.parseLong(soluong[2])), " "};
						
						for(int j = 0; j < 7; j++)
						{
							cells = new PdfPCell(new Paragraph(arr3[j], new Font(bf, 8, Font.NORMAL)));
							if(j >= 5)
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							
							if(j == 0)
								cells.setColspan(2);
							
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(2.0f);
							cells.setBorder(0);
							
							sanpham.addCell(cells);
						}
										
						cells = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
						cells.setColspan(8);
						cells.setPadding(2.0f);
						cells.setBorder(0);
						sanpham.addCell(cells);
						
						stt++;
					}
				}
			}	
			
			
			/*space = new PdfPCell(new Paragraph("Picker. . . . . . . . . . . .  ____________________________________", new Font(bf, 8, Font.NORMAL)));
			space.setColspan(4);
			space.setPaddingTop(60.0f);
			space.setBorder(0);
			sanpham.addCell(space);
			
			space = new PdfPCell(new Paragraph("Controlller.  _____________________________________________", new Font(bf, 8, Font.NORMAL)));
			space.setColspan(4);
			space.setPaddingTop(60.0f);
			space.setBorder(0);
			sanpham.addCell(space);*/
			
			document.add(sanpham);
			document.close(); 
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
	}


	private void CreatePhieuChuyenNL(Document document, ServletOutputStream outstream, IErpYeucaunguyenlieu ycnlBean) throws IOException
	{
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,###"); 
			
			PdfWriter.getInstance(document, outstream);
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			 
			Paragraph pxk = new Paragraph("Số phiếu..: " + ycnlBean.getId(), new Font(bf, 8, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setSpacingBefore(-25);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("PHIẾU CHUYỂN NGUYÊN LIỆU", font);
			pxk.setSpacingAfter(10);
			pxk.setSpacingBefore(5);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
		
			PdfPTable sanpham = new PdfPTable(8);
			sanpham.setKeepTogether(false);
			sanpham.setSplitLate(false);
			sanpham.setWidthPercentage(100);
			sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] withsKM = {35.0f, 10.0f, 15.0f, 15.0f, 15.0f, 15.0f, 12.0f, 10.0f};
			sanpham.setWidths(withsKM);
			
			String[] th = new String[]{"Sản phẩm", "Số lô", " ", "Kho chuyển", "Khu", "Bean", "Số lượng", "Đơn vị"};
			PdfPCell[] cell = new PdfPCell[8];
			for(int i= 0; i < 8 ; i++)
			{
				cell[i] = new PdfPCell(new Paragraph(th[i], new Font(bf, 9, Font.NORMAL)));
				cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
				
				if(i >= 6)
					cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[i].setBorder(0);
				cell[i].setPadding(4);
				
				sanpham.addCell(cell[i]);	
			}
		    
			PdfPCell space = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
			space.setColspan(8);
			space.setPadding(2.0f);
			space.setBorderWidthLeft(0);
			space.setBorderWidthRight(0);
			space.setBorderWidthTop(0);
			space.setBorderWidthBottom(0.8f);
			sanpham.addCell(space);
			
			space = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
			space.setColspan(8);
			space.setPadding(2.0f);
			space.setBorder(0);
			sanpham.addCell(space);
			
			List<IYeucau> spList = ycnlBean.getSpList();
			PdfPCell cells = new PdfPCell();

			int stt = 0;
			for(int i = 0; i <= spList.size(); i++)
			{
				if(i < spList.size())
				{
					IYeucau sp = (IYeucau)spList.get(i);
					List<ISpDetail> spDetail = sp.getSpDetailList();
					for(int k = 0; k < spDetail.size(); k++)
					{
						ISpDetail detail = spDetail.get(k);
						
						String[] soluong = detail.getSoluong().split(" - "); //0 - soluong yeu cau, 1 - soluong nhap, 2 - con lai
						
						String[] arr = new String[]{sp.getMa(), detail.getSolo(), "Yêu cầu", " ", " ", " ", 
								formatter.format(Long.parseLong(sp.getSoluongYC())), detail.getMa()};
						
						for(int j = 0; j < 8; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 8, Font.NORMAL)));
							if(j >= 5)
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(2.0f);
							cells.setBorder(0);
							
							sanpham.addCell(cells);
						}
						
						String[] arr2 = new String[]{sp.getTen(), "Chuyển", detail.getVitriId(), detail.getKhu(), detail.getVitri(), formatter.format(Long.parseLong(soluong[1])), " "};
						
						for(int j = 0; j < 7; j++)
						{
							cells = new PdfPCell(new Paragraph(arr2[j], new Font(bf, 8, Font.NORMAL)));
							if(j >= 5)
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							
							if(j == 0)
								cells.setColspan(2);
							
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(2.0f);
							cells.setBorder(0);
							
							sanpham.addCell(cells);
						}
						
						String[] arr3 = new String[]{" ", "Còn lại", " ", " ", " ", formatter.format(Long.parseLong(soluong[2])), " "};
						
						for(int j = 0; j < 7; j++)
						{
							cells = new PdfPCell(new Paragraph(arr3[j], new Font(bf, 8, Font.NORMAL)));
							if(j >= 5)
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							
							if(j == 0)
								cells.setColspan(2);
							
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(2.0f);
							cells.setBorder(0);
							
							sanpham.addCell(cells);
						}
										
						cells = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
						cells.setColspan(8);
						cells.setPadding(2.0f);
						cells.setBorder(0);
						sanpham.addCell(cells);
						
						stt++;
					}
				}
			}	
			
			document.add(sanpham);
			document.close(); 
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
	}


	
}
