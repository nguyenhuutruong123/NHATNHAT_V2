package geso.traphaco.erp.servlets.thutien;

import geso.traphaco.erp.beans.doctien.DocTien;
import geso.traphaco.center.beans.cauhinhinhoadon.IErpCauHinhInHoaDon;
import geso.traphaco.center.beans.cauhinhinhoadon.imp.ErpCauHinhInHoaDon;
import geso.traphaco.center.beans.doctien.doctienrachu;
import geso.traphaco.center.db.sql.dbutils;
import geso.traphaco.erp.beans.lenhsanxuat.IErpChuyenkhoSX;
import geso.traphaco.erp.beans.thutien.IErpThutien;
import geso.traphaco.erp.beans.thutien.imp.ErpThutien;
import geso.traphaco.erp.util.DocTienRaChu;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.TabStop;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

public class ErpThutienPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErpThutienPdfSvl() {
        super();
    }
    
    float CONVERT = 15.346457f;
    dbutils db = new dbutils();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		//String userTen = (String) session.getAttribute("userTen");  	

		 if (userId.length() == 0)
		    	userId = request.getParameter("userId");
			   
		String id = request.getParameter("id");
		
		IErpThutien tthdBean = new ErpThutien(id);
	    tthdBean.setUserId(userId);
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Phieuthutien.pdf");
		
		float CONVERT = 28.346457f;
		float PAGE_LEFT = 1.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		Document document = new Document();
		
		ServletOutputStream outstream = response.getOutputStream();		
		
		this.CreatePTPdf(document, outstream,tthdBean, id);
		
		document.close();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
	private void createBody(Document document, BaseFont bf,  String nguoiNop, String doiTuong, String diaChi, String khoanThu,
			String soTien,String docTienRaChu) {
		try {
			PdfPTable table1 = new PdfPTable(1);
			table1.setWidthPercentage(100);
			float[] withs1 = { 100f };
			table1.setWidths(withs1);
			PdfPCell cell = null;
			Paragraph hd = null;

			cell = new PdfPCell();
			hd = new Paragraph("Người nộp: " + nguoiNop, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("Địa chỉ: " + diaChi, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("Về khoản: " + khoanThu, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("Số tiền: " + soTien, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("Bằng chữ :" + docTienRaChu, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("Kèm theo ..... chứng từ gốc ", new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			table1.setSpacingAfter(10f);
			
			document.add(table1);
			
			// Phần kế toán trưởng và đã nhận đủ tiền
			table1 = new PdfPTable(2);
			float[] withs2 = { 100f, 100f };
			table1.setWidths(withs2);
			
			cell = new PdfPCell();
			hd = new Paragraph("KẾ TOÁN TRƯỞNG " , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("KẾ TOÁN THANH TOÁN ", new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);
			table1.setSpacingAfter(30f);
			
			document.add(table1);
			
			hd = new Paragraph("Đã nhân đủ số tiền ( viết bằng chữ) :............................................................................" +
					".............................................\n .................................................................................." +
					"................................................................................................ ", new Font(bf, 12, Font.NORMAL));
			hd.setSpacingAfter(5f);
			document.add(hd);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void createFooter(Document document, BaseFont bf) {
		try {
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			float[] withs1 = { 100f, 100f};
			table1.setWidths(withs1);
			PdfPCell cell = null;
			Paragraph hd = null;

			cell = new PdfPCell();
			hd = new Paragraph("NGƯỜI NỘP ", new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("Ngày ... tháng ... năm ..... \n THỦ QUỸ ", new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			table1.setSpacingAfter(30f);
			document.add(table1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private void createHeader(Document document, BaseFont bf, String id) {
		try {
			PdfPTable table1 = new PdfPTable(3);
			table1.setWidthPercentage(100);
			float[] withs1 = { 100f, 34f, 100f };
			table1.setWidths(withs1);
			PdfPCell cell = null;
			Paragraph hd = null;

			cell = new PdfPCell();
			hd = new Paragraph("CÔNG TY CỔ PHẦN TRAPHACO \n 75 Yên Ninh", new Font(bf, 11, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("", new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			cell = new PdfPCell();
			hd = new Paragraph("Số phiếu  " + id, new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(hd);
			cell.setBorder(0);

			table1.addCell(cell);

			table1.setSpacingAfter(15f);
			document.add(table1);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	private void CreatePTPdf(Document document, ServletOutputStream outstream, IErpThutien tthdBean, String Id) throws IOException
	{
		dbutils db = new dbutils();
		try{
			//LẤY THÔNG TIN
			
			String query =" SELECT tt.pk_seq, tt.ngaychungtu, tt.ngayghiso, tt.trangthai, tt.khachhang_fk as khachhang_fk, \n"+
			  " tt.httt_fk, tt.nganhang_fk, tt.chinhanh_fk, tk.SOHIEUTAIKHOAN +' -- '+ tk.TENTAIKHOAN as dinhkhoanco, " +
			  " tt.doituongdinhkhoan, tt.madoituongdinhkhoan, \n"+
			  " tt.sotaikhoan, tt.noidungtt_fk, isnull(tt.ghichu, '') as ghichu, ISNULL(tt.sotientt, 0) AS SOTIENTT, tt.tiente_fk, \n"+
			  " isnull(tt.sochungtu,'') as sochungtu, tt.NCC_FK, tt.NHANVIEN_FK , \n"+
			  " isnull(tt.thuduoc, 0) as thuduoc, isnull(tt.phinganhang, 0) as phinganhang, isnull(tt.chenhlech, 0) as chenhlech, \n"+
			  " ISNULL(tt.THUDUOCNT, 0) AS THUDUOCNT, ISNULL(tt.PHINGANHANGNT, 0) AS PHINGANHANGNT, \n"+
			  " ISNULL(tt.SOTIENTTNT, 0) AS SOTIENTTNT, ISNULL(tt.TIGIA, 0) AS TIGIA, tt.KBH_FK, \n"+
			  " isnull(tt.chietkhau,0) as chietkhau,isnull(tt.chietkhauNT,0) as chietkhauNT, ISNULL(NGUOINOPTIEN,'') as NGUOINOPTIEN," +
			  " ISNULL(tt.DIACHI,'') as DIACHI , \n"+
			  " xoakhtratruoc_fk , NHOMKHTT_FK, isnull(tt.ctkemtheo,'') chungtukemtheo, doituong, nhomkenh_fk, tt.BANGKE_FK," +
			  " isnull(tt.isNPP,0) isNPP,   \n"+
			  " isnull(npp.TEN, '') as nppTen, isnull(nv.TEN+ '('+ nv.MA +')', '') as nvTen, isnull(ncc.TEN, '') as nccTen"+
			  " FROM ERP_THUTIEN tt \n" + 
			  " left join ERP_TAIKHOANKT tk on tk.SOHIEUTAIKHOAN = tt.dinhkhoanco \n"+
			  " left join NHAPHANPHOI npp on npp.PK_SEQ = tt.khachhang_fk "+
			  " left join erp_nhacungcap ncc on ncc.PK_SEQ = tt.NCC_FK "+
			  " left join erp_nhanvien nv on nv.PK_SEQ = tt.NHANVIEN_FK "+
			  " WHERE tt.pk_seq = " + Id;
			
			System.out.println("in pdf : " + query);
			NumberFormat formatter = new DecimalFormat("#,###,###.###"); 
			String nguoiNop = "";
			String doiTuong = "";
			String diaChi = "";
			String khoanThu = "";
			String soTien = "";
			String ngayChungTu = "";
			long soTienN = 0;
			ResultSet rs = db.get(query);
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						nguoiNop = rs.getString("NGUOINOPTIEN");
						diaChi = rs.getString("DIACHI");
						doiTuong = rs.getString("nppTen")+ rs.getString("nccTen")+ rs.getString("nvTen");
						khoanThu = rs.getString("ghichu");
						soTien = formatter.format(rs.getDouble("SOTIENTT"));
						soTienN = rs.getLong("SOTIENTT");
						ngayChungTu = rs.getString("ngaychungtu");
					}
					rs.close(); 
				}
				catch (Exception e)
				{
					System.out.println("__Exception: " + e.getMessage());
				}
			}
		
			nguoiNop = nguoiNop+ " - " + doiTuong;
			
			DocTienRaChu doctien = new DocTienRaChu();
			String docTienRaChu = doctien.docSo(soTienN);
			
			
			document.setMargins(1.0f*CONVERT, 1.0f*CONVERT, 1.0f*CONVERT, 1.0f*CONVERT); // T, R, B, L
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			
			//GIẤY ĐỀ NGHỊ THANH TOÁN
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			PdfPCell cell = new PdfPCell();		
			
			Paragraph hd = new Paragraph();
			
			
			// liên 1
			// tạo header
			createHeader(document, bf,Id);
			
			hd.add(new Chunk(" PHIẾU THU", new Font(bf, 16, Font.BOLD)));
			hd.setAlignment(Element.ALIGN_CENTER);
			document.add(hd);
			
			hd = new Paragraph(); 
			hd.add(new Chunk(" Ngày:" + this.getDate(ngayChungTu), 
					new Font(bf, 12, Font.NORMAL)));
			hd.setAlignment(Element.ALIGN_CENTER);
			document.add(hd);
			
			
			createBody(document, bf, nguoiNop, doiTuong, diaChi, khoanThu, soTien,docTienRaChu );
			createFooter(document, bf);
			
			// dấu ngăn cách
			/*ArrayList<TabStop> tabList = new ArrayList<TabStop>();
			tabList.add(new TabStop(1, new VerticalPositionMark()));
			tabList.add(new TabStop(30, new DottedLineSeparator()));

			Paragraph paragraph = new Paragraph();
			paragraph.setTabSettings(new TabSettings(tabList));
			paragraph.add(Chunk.TABBING);
			document.add(paragraph);*/
			
			hd = new Paragraph(".................................................................................." +
					"................................................................................................ ", 
					new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			document.add(hd);
			
			
			// liên 2
			// tạo header
			createHeader(document, bf,Id);
			hd = new Paragraph(); 
			hd.add(new Chunk(" PHIẾU THU", new Font(bf, 16, Font.BOLD)));
			hd.setAlignment(Element.ALIGN_CENTER);
			document.add(hd);
			
			hd = new Paragraph(); 
			hd.add(new Chunk(" Ngày:" + this.getDate(ngayChungTu), 
					new Font(bf, 12, Font.NORMAL)));
			hd.setAlignment(Element.ALIGN_CENTER);
			document.add(hd);
			
			
			createBody(document, bf, nguoiNop, doiTuong, diaChi, khoanThu, soTien,docTienRaChu );
			createFooter(document, bf);
			
		}
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		} finally{
			db.shutDown();
		}
		
	}
	private String getDate(String date)
	{
		String arr[] = date.split("-");
		String nam = arr[0];
		String thang = arr[1];
		String ngay = arr[2];
		
		return ngay + "/" + thang + "/" + nam;
	}
	
	
	
	
}
