package geso.dms.distributor.servlets.tamung;

import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.*;
import geso.dms.distributor.beans.tamung.imp.ErpTamUng;
import geso.dms.distributor.beans.tamung.IErpTamUng;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.db.sql.dbutils;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.rmi.CORBA.Tie;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
@WebServlet("/ErpTamUngPdfSvl")
public class ErpTamUngPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpTamUngPdfSvl() {
		super();

	}

	NumberFormat formatter = new DecimalFormat("#,###,###");
	NumberFormat formatter2 = new DecimalFormat("#,###,###.00");
	
	float CONVERT = 28.346457f;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
			IErpTamUng obj;
			Utility util = new Utility();
			String querystring = request.getQueryString();
			String ddhId = util.getId(querystring);
			String doituongId = util.getLoai(querystring);
			System.out.println("doituongId:"+doituongId);
			userId = util.getUserId(querystring);
			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));
			obj = new ErpTamUng(ddhId);
			obj.InitPDF();
			/*response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition","attachment; filename=DeNghiTamUng.xlsm");
			OutputStream out = response.getOutputStream();*/
			String nextJSP = "";
			try {
				
				
				/*response.setContentType("application/pdf");
				response.setHeader("Content-Disposition"," inline; filename=PhieuDeNghiTamUngTT.pdf");
				
				float CONVERT = 28.346457f;
				float PAGE_LEFT = 2.0f*CONVERT, PAGE_RIGHT = 2.0f*CONVERT, PAGE_TOP = 1.0f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
				//Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);
				Document document = new Document(PageSize.A4, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
				
				ServletOutputStream outstream = response.getOutputStream();*/
				
				System.out.println("Print");
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", " inline; filename=Phieuchuyenkho.pdf");
				
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();	
				
				//this.inPDF(document, outstream, obj);
				if(doituongId.equals("2")){
					this.CreatePXCPdf_NCC(document, outstream, obj, ddhId);
				}
				else{
					this.CreatePXCPdf(document, outstream,obj, ddhId);
				}
				
				document.close();

			} catch (Exception e) {
				session.setAttribute("tuBean", obj);
				nextJSP = "/TraphacoERP/pages/Erp/ErpTamUngUpdate.jsp";
				obj.setMsg("Khong the tao bao cao..." + e.getMessage());
				response.sendRedirect(nextJSP);
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	private void CreatePXCPdf_NCC(Document document, ServletOutputStream outstream, IErpTamUng pxkBean, String Id) throws IOException
	{
		try{
			dbutils db = new dbutils();
			
			//L???Y TH??NG TIN
			
			String query = 	"SELECT NGAYTAMUNG, CASE WHEN TU.NCC_FK IS NOT NULL THEN NCC.TEN \n "+ 
							"		WHEN TU.NHANVIEN_FK IS NOT NULL THEN NV.TEN  END TEN, \n "+
							"		isnull(TU.NCC_FK,0) NCC_FK,SOTIENTAMUNG,LYDOTAMUNG,THOIGIANHOANUNG,HINHTHUCHOANUNG, \n "+ 	
							"		CASE WHEN TU.NHANVIEN_FK IS NOT NULL THEN 1 ELSE 2 END DOITUONGTAMUNG , \n"+
							"		isnull(n.TEN, '') as nguoitao, muahang_fk dmhId, BP.TEN TENBOPHAN \n "+
							"FROM	ERP_TAMUNG TU 	LEFT JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ=TU.NCC_FK \n "+	
							"		LEFT JOIN ERP_NhanVien NV ON NV.pk_seq=TU.NHANVIEN_FK  	\n"+
							"		LEFT JOIN NHANVIEN n ON n.PK_SEQ = TU.NGUOITAO 	\n"+
							"		LEFT JOIN ERP_DONVITHUCHIEN BP ON NV.DVTH_FK = BP.PK_SEQ \n"+
							"WHERE	TU.PK_SEQ='"+Id+"'\n";
			
			System.out.println("in pdf : " + query);
			String NGUOIDENGHI = "";
			String BOPHAN = "";
			String NGAYDENGHI ="";
			String SOPHIEU ="";
			double TONGTIENAVAT= 0;
			ResultSet rs = db.get(query);
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						BOPHAN = rs.getString("TENBOPHAN");
						NGAYDENGHI = rs.getString("NGAYTAMUNG");
						SOPHIEU = Id;
					}
					rs.close(); 
				}
				catch (Exception e)
				{
					System.out.println("__Exception: " + e.getMessage());
				}
			}
		
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			//document.setPageSize(PageSize.A4.rotate()); CANH H??A ????N THEO CHI???U D???C
			document.setMargins(1.0f*CONVERT, 1.5f*CONVERT, 2.0f*CONVERT, 2.0f*CONVERT); // L,R, T, B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			
			document.open();
			//document.setPageSize(new Rectangle(100.0f, 100.0f));
			//document.setPageSize(PageSize.A4.rotate());
		

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			
			
			//GI???Y ????? NGH??? THANH TO??N
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			PdfPCell cell = new PdfPCell();		
			
			Paragraph hd = new Paragraph();
			hd.add(new Chunk("GI???Y ????? NGH??? CHUY???N TI???N", new Font(bf, 16, Font.ITALIC)));
			hd.setAlignment(Element.ALIGN_CENTER);	
			cell.setBorderWidthBottom(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph();
			hd.add(new Chunk("S???: "+ Id, new Font(bf, 10, Font.ITALIC)));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setBorderWidthTop(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
				
			document.add(tableheader);
			
			//C??NG TY
			
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			float[] withs1 = {60f,100f};
			table1.setWidths(withs1);
					
			cell = new PdfPCell();	
			hd = new Paragraph("C??NG TY " , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("C??? PH???N ????? H???P H??? LONG " , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			document.add(table1);
			
			//B??? PH???N
			
			PdfPTable table2 = new PdfPTable(2);
			table2.setWidthPercentage(100);
			float[] withs2 = {60f,100f};
			table2.setWidths(withs2);
					
			cell = new PdfPCell();	
			hd = new Paragraph( "B??? PH???N" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "Ph??ng K??? Ho???ch Cung ???ng", new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			//NG??Y ????? NGH???
			
			cell = new PdfPCell();	
			hd = new Paragraph( "NG??Y" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setPaddingLeft(2.0f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			String [] ngayHD = NGAYDENGHI.split("-");
			cell = new PdfPCell();	
			hd = new Paragraph( ngayHD[2] + "/" + ngayHD[1] +  "/" + ngayHD[0], new Font(bf, 10, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);			
			
			table2.addCell(cell);
			
			document.add(table2);
			
			//H??NH TH???C THANH TO??N - M?? S??? THU???
			
			String nd = "SELECT NGAYTAMUNG NGAYNHAN, LYDOTAMUNG DIENGIAI, SOTIENTAMUNG THANHTIEN FROM ERP_TAMUNG WHERE PK_SEQ ='"+Id+"'";
			
			System.out.println("in nd : " + query);
			String NGAYNHAN = "";
			String DIENGIAI = "";
			double THANHTIENVIET =0;
			
			int i =0;
			
			
			PdfPTable table4 =new PdfPTable(4);
			table4.setWidthPercentage(100);
			float[] withs4 = {100f,200f,100f, 100f};
			table4.setWidths(withs4);
			table4.setSpacingAfter(0.2f*CONVERT);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "Ng??y" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "Chi ti???t chuy???n ti???n \n (N???i dung chuy???n ti???n)" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "S??? ti???n c??? VAT" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "Ghi ch??" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			doctienrachu doctien = new doctienrachu();
			
			rs = db.get(nd);
			if (rs != null)
			{
				try
				{
					while (rs.next())
					{
						NGAYNHAN = rs.getString("NGAYNHAN");
						DIENGIAI = rs.getString("DIENGIAI");
						THANHTIENVIET = rs.getDouble("THANHTIEN");	
						
						cell= new PdfPCell();					
						hd = new Paragraph( ngayHD[2] + "/" + ngayHD[1] +  "/" + ngayHD[0] , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_CENTER);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						cell= new PdfPCell();	
						hd = new Paragraph( DIENGIAI , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_LEFT);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						cell= new PdfPCell();
						String sotienbangchu = doctien.docTien(Math.round(THANHTIENVIET));
						
						System.out.println("sotien:"+sotienbangchu);
						
						hd = new Paragraph(DinhDangTraphacoERP(formatter.format( THANHTIENVIET))+ " \n "+ sotienbangchu  , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_CENTER);
						cell.addElement(hd);
						table4.addCell(cell);
						
						cell= new PdfPCell();	
						hd = new Paragraph( "" , new Font(bf, 10, Font.NORMAL)); 
						hd.setAlignment(Element.ALIGN_LEFT);
						cell.addElement(hd);
						
						table4.addCell(cell);
						
						i++;
					}
					rs.close(); 
				}
				catch (Exception e)
				{
					System.out.println("__Exception: " + e.getMessage());
				}
			}
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph("" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.5f*CONVERT);
			cell.setBorder(0);	
			cell.addElement(hd);
			
			table4.addCell(cell);
			
			document.add(table4);
						
			
			PdfPTable table7 = new PdfPTable(2);
			table7.setWidthPercentage(100);
			float[] withs7 = {100f, 100f};
			table7.setWidths(withs7);
			
			cell = new PdfPCell();
			hd = new Paragraph("NG?????I ????? NGH???" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("PH??? TR??CH B??? PH???N" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("K??? TO??N TR?????NG" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			//L???Y K??? TO??N TR?????NG
			query = "select b.TEN from ERP_CHUCDANH a inner join NHANVIEN b on a.NHANVIEN_FK = b.PK_SEQ where a.PK_SEQ ='100001'";
			
			String ketoantruong = "";
			
			rs = db.get(query);
			try{
				if(rs != null){
					rs.next();
						ketoantruong = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
			
			cell = new PdfPCell();
			hd = new Paragraph(ketoantruong, new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			cell = new PdfPCell();
			hd = new Paragraph("T???NG GI??M ?????C" , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
			
			table7.addCell(cell);
			
			//L???Y K??? TO??N TR?????NG
			query = "select b.TEN from ERP_CHUCDANH a inner join NHANVIEN b on a.NHANVIEN_FK = b.PK_SEQ where a.PK_SEQ ='100000'";
			
			String tonggiamdoc = "";
			
			rs = db.get(query);
			try{
				if(rs != null){
					rs.next();
						tonggiamdoc = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
			
			cell = new PdfPCell();
			hd = new Paragraph(tonggiamdoc , new Font(bf, 12, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(1.0f*CONVERT);
			cell.addElement(hd);	
					
			table7.addCell(cell);
			
			document.add(table7);
							
			
		}
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void CreatePXCPdf(Document document, ServletOutputStream outstream, IErpTamUng pxkBean, String Id) throws IOException
	{
		try{
			dbutils db = new dbutils();
			
			//L???Y TH??NG TIN 
			String query = "";
		
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			//document.setPageSize(PageSize.A4.rotate()); CANH H??A ????N THEO CHI???U D???C
			document.setMargins(2.0f*CONVERT, 1.5f*CONVERT, 2.0f*CONVERT, 2.0f*CONVERT); // L,R, T, B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			
			document.open();
			//document.setPageSize(new Rectangle(100.0f, 100.0f));
			//document.setPageSize(PageSize.A4.rotate());
		

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			
			
			query =
				"SELECT NGAYTAMUNG, CASE WHEN TU.NCC_FK IS NOT NULL THEN NCC.TEN \n "+ 
				"		WHEN TU.NHANVIEN_FK IS NOT NULL THEN NV.TEN  END TEN, \n "+
				"		isnull(TU.NCC_FK,0) NCC_FK,SOTIENTAMUNG,LYDOTAMUNG,THOIGIANHOANUNG,HINHTHUCHOANUNG, \n "+ 	
				"		CASE WHEN TU.NHANVIEN_FK IS NOT NULL THEN 1 ELSE 2 END DOITUONGTAMUNG ,isnull(NV.CMND,'') CMND,  \n"+
				"		isnull(n.TEN, '') as nguoitao, muahang_fk dmhId, BP.TEN TENBOPHAN, ISNULL(NV.NOICAP, '') NOICAP, ISNULL(NV.NGAYCAP, '') NGAYCAP \n "+
				"FROM	ERP_TAMUNG TU 	LEFT JOIN ERP_NHACUNGCAP NCC ON NCC.PK_SEQ=TU.NCC_FK \n "+	
				"		LEFT JOIN ERP_NhanVien NV ON NV.pk_seq=TU.NHANVIEN_FK  	\n"+
				"		LEFT JOIN NHANVIEN n ON n.PK_SEQ = TU.NGUOITAO 	\n"+
				"		LEFT JOIN ERP_DONVITHUCHIEN BP ON NV.DVTH_FK = BP.PK_SEQ \n"+
				"WHERE	TU.PK_SEQ='"+Id+"'\n";
			
			
			System.out.println("Tram pa:"+query);
			
			String TEN = "";
			String NGAYTAMUNG = "";
			double SOTIENTAMUNG = 0;
			String LYDOTAMUNG = "";
			String THOIGIANHOANUNG = "";
			String BOPHAN =  "";
			String CMND = "";
			String NOICAP = "";
			String NGAYCAP = "";
			
			ResultSet rs = db.get(query);
			if(rs!=null){
				while(rs.next()){
					TEN = rs.getString("TEN");
					NGAYTAMUNG = rs.getString("NGAYTAMUNG");
					SOTIENTAMUNG = rs.getDouble("SOTIENTAMUNG");
					LYDOTAMUNG = rs.getString("LYDOTAMUNG");
					THOIGIANHOANUNG = rs.getString("THOIGIANHOANUNG");
					BOPHAN = rs.getString("TENBOPHAN");
					CMND = rs.getString("CMND");
					NOICAP = rs.getString("NOICAP");
					NGAYCAP = rs.getString("NGAYCAP");
				}
			}
			
			
			//C??NG TY CP ????? H???P H??? LONG
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			PdfPCell cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						
			Paragraph hd = new Paragraph("C??NG TY CP ????? H???P H??? LONG" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			document.add(tableheader);
			
			//G???CH CH??N
			PdfPTable tableline =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
						
			hd = new Paragraph("________________________" , new Font(bf, 5, Font.BOLD));
			hd.setAlignment(Element.ALIGN_TOP);
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableline.addCell(cell);
			document.add(tableline);
			
			//PHI???U T???M ???NG
			
			PdfPTable table1 = new PdfPTable(1);
			table1.setWidthPercentage(100);
			float[] withs1 = {100f};
			table1.setWidths(withs1);
					
			cell = new PdfPCell();	
			hd = new Paragraph("PHI???U T???M ???NG" , new Font(bf, 14, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("S???:"+Id , new Font(bf, 10, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(1.0f*CONVERT);	//hd.setSpacingAfter(4);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			document.add(table1);
			
			//T??I T??N L?? - 
			
			PdfPTable table2 = new PdfPTable(1);
			table2.setWidthPercentage(100);
			float[] withs2 = {100f};
			table2.setWidths(withs2);
					
			cell = new PdfPCell();	
			hd = new Paragraph("T??i t??n l??: "+TEN  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);	//hd.setSpacingAfter(4);			
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);	
						
			document.add(table2);
			
			//S??? CMND
			
			PdfPTable table3 =new PdfPTable(3);
			table3.setWidthPercentage(100);
			float[] withs3 = {50f, 50f, 50f };
			table3.setWidths(withs3);
						
			cell = new PdfPCell();	
			hd = new Paragraph( "S??? CMND : " +CMND, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "C???p ng??y: " +NGAYCAP, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);
						
			cell = new PdfPCell();	
			hd = new Paragraph( "t???i: " +NOICAP, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);
			
			document.add(table3);
			
			//B??? PH???N
			
			PdfPTable table4 =new PdfPTable(1);
			table4.setWidthPercentage(100);
			float[] withs4 = {100f};
			table4.setWidths(withs4);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "B??? ph???n: "+BOPHAN , new Font(bf, 12, Font.NORMAL)); /* HINH THUC THANH TOAN*/
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table4.addCell(cell);
			document.add(table4);
						
			//????? NGH??? T???M ???NG
			
			PdfPTable table5 =new PdfPTable(1);
			table5.setWidthPercentage(100);
			float[] withs5 = {100f};
			table5.setWidths(withs5);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "????? ngh??? t???m ???ng: "+DinhDangTraphacoERP(formatter1.format(SOTIENTAMUNG))+" ?????ng" , new Font(bf, 12, Font.NORMAL)); 
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table5.addCell(cell);
			document.add(table5);
			
			
			
			//?????C TI???N RA CH???
			
			doctienrachu doctien = new doctienrachu();
					    
			String tien = doctien.docTien(Math.round(SOTIENTAMUNG));
						
			//String tien = doctien.tranlate(tongtiencovat + "");
			tien = tien.substring(0, 1).toUpperCase() + tien.substring(1, tien.length());
			if(tien.equals("?????ng"))
				 tien="Kh??ng ?????ng";
			
			//B???NG CH???
			PdfPTable table6 =new PdfPTable(1);
			table6.setWidthPercentage(100);
			float[] withs6 = {100f};
			table6.setWidths(withs6);
						
			cell= new PdfPCell();	
			hd = new Paragraph( " (B???ng ch???: "+tien+" )" , new Font(bf, 12, Font.ITALIC)); 
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table6.addCell(cell);
			document.add(table6);
			
			//L?? DO T???M ???NG
			
			PdfPTable table7 =new PdfPTable(1);
			table7.setWidthPercentage(100);
			float[] withs7 = {100f};
			table7.setWidths(withs7);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "L?? do t???m ???ng: " + LYDOTAMUNG, new Font(bf, 12, Font.NORMAL)); 
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table7.addCell(cell);
			document.add(table7);
			
			//NG??Y/TH??NG/N??M ????? NGH??? T???M ???NG
			
			String [] ngayTAMUNG = NGAYTAMUNG.split("-");
			
			PdfPTable table8 =new PdfPTable(1);
			table8.setWidthPercentage(100);
			float[] withs8 = {100f};
			table8.setWidths(withs8);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "Ng??y/th??ng/n??m ????? ngh??? t???m ???ng: "+ngayTAMUNG[2]+"/"+ngayTAMUNG[1]+"/"+ngayTAMUNG[0] , new Font(bf, 12, Font.NORMAL)); 
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table8.addCell(cell);
			document.add(table8);
			
			//TH???I GIAN HO??N ???NG
			
			String [] THOIHANTU = THOIGIANHOANUNG.split("-");
			
			PdfPTable table9 =new PdfPTable(1);
			table9.setWidthPercentage(100);
			float[] withs9 = {100f};
			table9.setWidths(withs9);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "Th???i h???n thanh to??n t???m ???ng: " +THOIHANTU[2]+"/"+THOIHANTU[1]+"/"+THOIHANTU[0] , new Font(bf, 12, Font.NORMAL)); 
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table9.addCell(cell);
			document.add(table9);
			
			// N???I DUNG CAM K???T
			PdfPTable table10 =new PdfPTable(1);
			table10.setWidthPercentage(100);
			float[] withs10 = {100f};
			table10.setWidths(withs10);
						
			cell= new PdfPCell();	
			hd = new Paragraph( " \t \t \t \t T??i cam k???t th???c hi???n ????ng nh???ng n???i dung sau: \n" , new Font(bf, 12, Font.NORMAL)); 
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table10.addCell(cell);
			document.add(table10);
			
			String laynd =
			" \t \t \t \t 1.Ch???p h??nh ?????y ????? c??c quy ?????nh n???i b??? c???a C??ng ty li??n quan ?????n vi???c t???m ???ng, s??? d???ng v?? thanh to??n t???m ???ng; \n" +
			" \t \t \t \t 2.S??? d???ng ti???n t???m ???ng ????ng m???c ????ch v?? c?? tr??ch nhi???m thanh to??n t???m ???ng ????ng th???i h???n; \n" +
			" \t \t \t \t 3.N???u qu?? th???i h???n thanh to??n t???m ???ng 15 ng??y l??m vi???c, ph???i b??o c??o gi???i tr??nh " +
			"T???ng Gi??m ?????c v??? vi???c ch???m thanh to??m t???m ???ng v?? c?? x??c nh???n c???a ph??? tr??ch b??? ph???n; \n" +
			" \t \t \t \t 4.Ch???u tr??ch nhi???m b???o qu???n kho???n ti???n ???? t???m ???ng v?? ?????ng ?? r???ng: C??ng ty kh??ng " +
			"ch???p nh???n b???t k??? b??o c??o n??o c???a t??i hay x??c nh???n c???a b???t k??? t??? ch???c/ c?? nh??n n??o v??? vi???c b??? " +
			"m???t to??n b??? hay m???t ph???n kho???n ti???n ???? t???m ???ng v?? s??? ph???i ch???u tr??ch nhi???m thanh to??n ti???n " +
			"t???m ???ng trong th???i gian m?? C??ng ty y??u c???u./.";
			
			PdfPTable table11 =new PdfPTable(1);
			table11.setWidthPercentage(100);
			float[] withs11 = {100f};
			table11.setWidths(withs11);
						
			cell= new PdfPCell();	
			hd = new Paragraph( laynd , new Font(bf, 12, Font.NORMAL)); 
			hd.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(hd);			
			cell.setBorder(0);
			
			table11.addCell(cell);
			document.add(table11);
			
			//DUY???T T???M ???NG
			
			PdfPTable table12 =new PdfPTable(1);
			table12.setWidthPercentage(100);
			float[] withs12 = {100f};
			table11.setWidths(withs12);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "Duy???t t???m ???ng: "+DinhDangTraphacoERP(formatter1.format(SOTIENTAMUNG))+" ?????ng" , new Font(bf, 12, Font.BOLD)); 
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table12.addCell(cell);
			document.add(table12);
			
			//CH??? K??
			
			PdfPTable table13 =new PdfPTable(4);
			table13.setWidthPercentage(100);
			float[] withs13 = {200f,150f,150f, 100f};
			table13.setWidths(withs13);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "NG?????I ????? NGH??? T???M ???NG" , new Font(bf, 11, Font.BOLD)); /* S??? ??I???U ?????NG*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table13.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "PH??? TR??CH B??? PH???N" , new Font(bf, 11, Font.BOLD)); /* NG??Y TH??NG N??M*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);			
			cell.setBorder(0);
			
			table13.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "K??? TO??N TR?????NG" , new Font(bf, 11, Font.BOLD)); /* NG??Y TH??NG N??M*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.6f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table13.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "NG?????I DUY???T" , new Font(bf, 11, Font.BOLD)); /* NG??Y TH??NG N??M*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.6f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table13.addCell(cell);
			//

			//L???Y K??? TO??N TR?????NG
			query = "select b.TEN from ERP_CHUCDANH a inner join NHANVIEN b on a.NHANVIEN_FK = b.PK_SEQ where a.PK_SEQ ='100001'";
			
			String ketoantruong = "";
			
			rs = db.get(query);
			try{
				if(rs != null){
					rs.next();
						ketoantruong = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
				
			
			
			
			document.add(table13);			
			
			
			PdfPTable table14 = new PdfPTable(4);
			table14.setWidthPercentage(100);
			float[] withs14 = {200f,150f,150f, 100f};
			table14.setWidths(withs14);
						
			cell= new PdfPCell();	
			hd = new Paragraph( "(K??, ghi r?? h??? t??n)" , new Font(bf, 10, Font.ITALIC)); /* C???A*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table14.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "(K??, ghi r?? h??? t??n)" , new Font(bf, 10, Font.ITALIC)); /* V??? VI???C*/
			hd.setAlignment(Element.ALIGN_CENTER);			
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table14.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.NORMAL)); /* V??? VI???C*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table14.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.NORMAL)); /* V??? VI???C*/
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table14.addCell(cell);
			
			document.add(table14);
			
			PdfPTable table15 = new PdfPTable(4);
			table15.setWidthPercentage(100);
			float[] withs15 = {200f,150f,150f, 100f};
			table15.setWidths(withs15);			
						
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.ITALIC)); /* C???A*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setPaddingTop(1.5f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table15.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.ITALIC)); /* V??? VI???C*/
			hd.setAlignment(Element.ALIGN_CENTER);			
			cell.setPaddingTop(1.5f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table15.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( ketoantruong , new Font(bf, 10, Font.NORMAL)); /* V??? VI???C*/
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setPaddingTop(1.5f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table15.addCell(cell);
			
			cell= new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 10, Font.NORMAL)); /* V??? VI???C*/
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setPaddingTop(1.5f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);
			
			table15.addCell(cell);
			
			document.add(table15);
		}
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	
	private void inPDF(Document document, ServletOutputStream outstream, IErpTamUng obj) throws IOException {
		
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,###"); 
			NumberFormat formatter2 = new DecimalFormat("#,###,##0.00000"); 
			NumberFormat formatter3 = new DecimalFormat("#,###,##0.000"); 
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font9bold = new Font(bf, 9, Font.BOLD);
			Font font10bold = new Font(bf, 10, Font.BOLD);
			Font font11bold = new Font(bf, 11, Font.BOLD);
			Font font12bold = new Font(bf, 12, Font.BOLD);
			Font font13bold = new Font(bf, 13, Font.BOLD);
			Font font14bold = new Font(bf, 14, Font.BOLD);
			Font font15bold = new Font(bf, 15, Font.BOLD);
			Font font9 = new Font(bf, 9, Font.NORMAL);
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font11 = new Font(bf, 11, Font.NORMAL);
			Font font12 = new Font(bf, 12, Font.NORMAL);
			Font font13 = new Font(bf, 13, Font.NORMAL);
			Font font14 = new Font(bf, 14, Font.NORMAL);
			
			//SIZE
			float CONVERT = 28.346457f; // = 1cm
			float[] TABLE_HEADER_WIDTHS = {3.5f * CONVERT, 8.1f * CONVERT, 5.0f * CONVERT };
			float[] TABLE_MIDDLE_WIDTHS = {3.5f * CONVERT, 13.1f * CONVERT};
			float[] TABLE_FOOTER_WIDTHS = {4.15f * CONVERT, 4.15f * CONVERT, 4.15f * CONVERT, 4.15f * CONVERT};			
			
			//V??? khung header (Logo Picture | Header Titles)
			PdfPTable headerTable = new PdfPTable(TABLE_HEADER_WIDTHS.length);
			
			headerTable.setWidths(TABLE_HEADER_WIDTHS);
			headerTable.setWidthPercentage(100);
			
			PdfPCell cell;
			
			//header left
			PdfPTable headerLeftTable = new PdfPTable(1);
			headerLeftTable.setWidths(new float[] {TABLE_HEADER_WIDTHS[0]});
			headerLeftTable.setWidthPercentage(100);
			
			String[] imageSources = {
					"C:\\Program Files\\Apache Software Foundation\\8888\\webapps\\TraphacoERP\\pages\\images\\logoTraphacoERP.jpg",
					"C:\\Program Files (x86)\\Apache Software Foundation\\8888\\webapps\\TraphacoERP\\pages\\images\\logoTraphacoERP.jpg",
					"D:\\TOMCAT WEB SOURCE 2\\webapps\\TraphacoERP\\pages\\images\\logoTraphacoERP.jpg",
					"D:\\Project\\TraphacoERP\\TraphacoERP\\WebContent\\pages\\images\\logoTraphacoERP.jpg"
			}; 
			Image logoImage = null;
			
			for(int i = 0; i < imageSources.length; i++) {
				try {
					if(logoImage == null) {
						logoImage = Image.getInstance(imageSources[i]);
						System.out.println("[ErpPhieuxuatkhoPdfSvl.CreatePxk] imgSrc = " + imageSources[i]);
						break;
					}
				} catch (Exception e) {	}
			}
			if(logoImage != null) {
				System.out.println("[ErpPhieuxuatkhoPdfSvl.CreatePxk] Load Images Logo Thanh Cong....");
				logoImage.setBorder(0);
				logoImage.setAlignment(Element.ALIGN_CENTER);
				logoImage.scaleToFit(2.0f * CONVERT, 2.0f * CONVERT);
				//headerLeftTable.addCell(logoImage);
				logoImage.setAbsolutePosition(2.8f * CONVERT, PageSize.A4.getHeight() - 3.2f*CONVERT);
				
				document.add(logoImage);
				/**/
			} else {
				
			}
			System.out.println("[ErpPhieuxuatkhoPdfSvl.CreatePxk] Khong load duoc hinh anh logo");
			cell = new PdfPCell(new Paragraph(" ", new Font(bf, 8, Font.NORMAL)));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(2.5f * CONVERT);
			headerLeftTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("MPP: ", font9));
			cell.setBorderWidthTop(1);
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthRight(0);
			cell.setFixedHeight(0.5f * CONVERT);
			headerLeftTable.addCell(cell);

			
			headerTable.addCell(headerLeftTable);
			
			//Header middle: PHI???U ????? NGH??? T???M ???NG
			
			String tieude="";
			if(obj.getDoiTuongTamUng().equals("1"))
			{
				tieude = "PHI???U ????? NGH??? T???M ???NG";
			}
			else 
			{
				tieude = "PHI???U ????? NGH??? \n" +
						"THANH TO??N TR?????C";
			}
			
			cell = new PdfPCell(new Paragraph(tieude, font15bold));
			cell.setFixedHeight(3.0f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			headerTable.addCell(cell);
			
			//Header right: Table
			PdfPTable headerRightTable = new PdfPTable(2);
			headerRightTable.setWidths(new float[] {TABLE_HEADER_WIDTHS[2]*0.4f, TABLE_HEADER_WIDTHS[2]*0.6f});
			headerRightTable.setWidthPercentage(100);
			
			cell = new PdfPCell(new Paragraph("Lo???i", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": Bi???u m???u", font10));
			
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("M?? s???", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			String maso="";
			if(obj.getDoiTuongTamUng().equals("1"))
			{
				maso = ": BM-KTTC-005";
			}
			else 
			{
				maso = ": BM-KTTC-012";
			}
			cell = new PdfPCell(new Paragraph(maso, font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("So??t x??t", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": 01", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("??i???u ch???nh", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": 01", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("Trang", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": 1/1", font10));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.6f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			headerRightTable.addCell(cell);
			
			headerTable.addCell(headerRightTable);
			
			document.add(headerTable);
			
			
			//S???
			Paragraph par = new Paragraph("S???: " + obj.getId(), font9);
			par.setAlignment(Element.ALIGN_CENTER);
			par.setSpacingBefore(0.5f * CONVERT);
			par.setSpacingAfter(0.2f * CONVERT);
			document.add(par);
			
			//Middle Table
			PdfPTable middleTable = new PdfPTable(TABLE_MIDDLE_WIDTHS.length);
			middleTable.setWidths(TABLE_MIDDLE_WIDTHS);
			middleTable.setWidthPercentage(100);
			
			
			cell = new PdfPCell(new Paragraph("H??? v?? t??n", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			String hovaten="";
			if(obj.getDoiTuongTamUng().equals("1"))
			{
				hovaten = obj.getTenHienThi();
			}
			else 
			{
				hovaten = obj.getNguoitao();
			}
			
			cell = new PdfPCell(new Paragraph(": " + hovaten, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("B??? ph???n", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": " + obj.getBoPhan(), font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph("S??? ti???n ????? ngh???", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			//cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			String tienchu = "";
			try {		
				float sotien = Float.parseFloat(obj.getSoTienTamUng().replaceAll(",", ""));			
				NumberFormat nf = new DecimalFormat("##########");
				long ltien = Long.parseLong(nf.format(sotien));
				tienchu = doctienrachu.docTien(ltien);
			} catch (Exception e) {
				System.out.println("Exception = " + e.getMessage());
			}
			
			cell = new PdfPCell(new Paragraph(": " + formatter.format(Float.parseFloat(obj.getSoTienTamUng())) + "                   B???ng ch???: " + tienchu, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			//cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			
			
			 
		/*	cell = new PdfPCell(new Paragraph("B???ng ch???", font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
		
			cell = new PdfPCell(new Paragraph(": " + tienchu, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);*/
			
			String lydo="";
			if(obj.getDoiTuongTamUng().equals("1"))
			{
				lydo = "L?? do t???m ???ng";
			}
			else 
			{
				lydo = "L?? do thanh to??n";
			}
			
			cell = new PdfPCell(new Paragraph(lydo, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": " + obj.getLyDoTamUng(), font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			String ngay ="";
			String thang ="";
			String nam ="";
			if(obj.getDoiTuongTamUng().equals("1"))
			{
				cell = new PdfPCell(new Paragraph("Th???i h???n thanh to??n", font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
				
				ngay = obj.getThoiGianHoanUng().substring(8,10);
				thang = obj.getThoiGianHoanUng().substring(5,7);
				nam = obj.getThoiGianHoanUng().substring(0,4);
				cell = new PdfPCell(new Paragraph(": " + ngay + "-"+thang+"-" + nam, font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
			}
			else 
			{
				cell = new PdfPCell(new Paragraph("Nh?? cung c???p", font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
				
				cell = new PdfPCell(new Paragraph(": " + obj.getTenHienThi(), font11));
				cell.setBorder(Rectangle.NO_BORDER);
				cell.setFixedHeight(0.7f * CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				middleTable.addCell(cell);
			}
			
			
			String duyet="";
			if(obj.getDoiTuongTamUng().equals("1"))
			{
				duyet = "Duy???t t???m ???ng";
			}
			else 
			{
				duyet = "Duy???t";
			}
			
			cell = new PdfPCell(new Paragraph(duyet, font11));
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(": ", font11));// ????? tr???ng
			cell.setBorder(Rectangle.NO_BORDER);
			cell.setFixedHeight(0.7f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			middleTable.addCell(cell);
			
			document.add(middleTable);
			

			//Ng??y th??ng n??m
			ngay = obj.getNgayTamUng().substring(8,10);
			thang = obj.getNgayTamUng().substring(5,7);
			nam = obj.getNgayTamUng().substring(0,4);
			
			par = new Paragraph("Ng??y "+ngay+" th??ng "+thang+" n??m " + nam, font11);
			par.setAlignment(Element.ALIGN_RIGHT);
			par.setSpacingBefore(0.3f * CONVERT);
			par.setSpacingAfter(0.2f * CONVERT);
			document.add(par);
			
			//TABLE FOOTER
			PdfPTable tableFooter = new PdfPTable(TABLE_FOOTER_WIDTHS.length);
			tableFooter.setWidthPercentage(100);
			tableFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableFooter.setWidths(TABLE_FOOTER_WIDTHS);
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("NG?????I ????? NGH???", font9bold));
			cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell12 = new PdfPCell(new Paragraph("K??? TO??N TR?????NG", font9bold));
			cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell13 = new PdfPCell(new Paragraph("T.B??? PH???N", font9bold));
			cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
			PdfPCell cell14 = new PdfPCell(new Paragraph("BAN T???NG GI??M ?????C", font9bold));
			cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell11.setBorder(0);
			cell12.setBorder(0);
			cell13.setBorder(0);
			cell14.setBorder(0);
			tableFooter.addCell(cell11);
			tableFooter.addCell(cell12);
			tableFooter.addCell(cell13);
			tableFooter.addCell(cell14);
			
			
			PdfPCell cell15 = new PdfPCell(new Paragraph( hovaten, font9));
			cell15.setBorder(Rectangle.NO_BORDER);
			cell15.setFixedHeight(2.0f * CONVERT);
			cell15.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell15.setColspan(4);
			tableFooter.addCell(cell15);
					
			
			
			document.add(tableFooter);
			document.close();
			
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private String DinhDangTraphacoERP(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");
		
		return sotien;
	}
}
