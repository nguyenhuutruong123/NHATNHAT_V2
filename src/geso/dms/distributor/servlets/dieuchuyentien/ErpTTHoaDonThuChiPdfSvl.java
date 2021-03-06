package geso.traphaco.erp.servlets.dieuchuyentien;

import geso.traphaco.center.beans.doctien.DocTien;
import geso.traphaco.center.util.Utility;
import geso.traphaco.erp.beans.cauhinhinhoadon.IErpCauHinhInHoaDon;
import geso.traphaco.erp.beans.cauhinhinhoadon.imp.ErpCauHinhInHoaDon;
import geso.traphaco.erp.beans.dieuchuyentien.IErpDieuchuyentien;
import geso.traphaco.erp.beans.dieuchuyentien.imp.ErpDieuchuyentien;
import geso.traphaco.erp.beans.thanhtoanhoadon.IErpThanhtoanhoadon;
import geso.traphaco.erp.db.sql.dbutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.itextpdf.text.BaseColor;
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
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpTTHoaDonThuChiPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErpTTHoaDonThuChiPdfSvl() {
        super();
    }
    dbutils db = new dbutils();
    float CONVERT = 28.346457f;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		//String userTen = (String) session.getAttribute("userTen");  	

		 if (userId.length() == 0)
		    	userId = request.getParameter("userId");
			   
		String id = request.getParameter("id");
		String httt = request.getParameter("httt");
		if(httt == null)
			httt = "";
	
		IErpDieuchuyentien obj;
	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String ctyId = (String)session.getAttribute("congtyId");
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String Id = util.getId(querystring);
	    
	    obj = new ErpDieuchuyentien();
	    obj.setCongtyId(ctyId);
	    obj.setId(Id);
	    obj.setUserId(userId);
	    obj.init();
	    
//	    OutputStream out = response.getOutputStream();
	    
		float CONVERT = 28.346457f;
		float PAGE_LEFT = 1.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
	
		//chinh khung giay a5
		Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);
		Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		
		ServletOutputStream outstream = response.getOutputStream();		
		String type = getKieuChungTu(id);
		System.out.println("Kieu chung tu: " + type);
		if (type.trim().equals("1") == true) // CHI TI???N M???T
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=PhieuChi.pdf");
			this.CreatePhieuChiPdf(document, outstream, obj, id, true);
			document.close();
		}
		else if (type.trim().equals("2") == true)
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=PhieuChi.pdf");
			this.CreatePhieuChiPdf(document, outstream, obj, id, false);
			document.close();
		}
		else if (type.trim().equals("0") == true)
		{
	    	//KI???M TRA N???U M?? NG??N H??NG TR??? L?? IVB TH?? CANH CHI???U D???C
	    	String query = 
				"	SELECT ISNULL(NHCC.MA, 0) AS NHC " + 
				"	FROM ERP_DIEUCHUYENTIEN DC     " +
				"	INNER JOIN ERP_NGANHANG_CONGTY NHC ON NHC.PK_SEQ = DC.NGANHANGCHUYEN_FK   " +
				"	INNER JOIN ERP_NGANHANG NHCC ON NHCC.PK_SEQ = NHC.PK_SEQ   " +
				"	WHERE DC.PK_SEQ =  '" + obj.getId() + "' ";
				
				System.out.println("MA NGAN HANG__:"+query);
				dbutils db = new dbutils();
				ResultSet rs = db.get(query);
				
				String manganhangtra = "";
				try{
					if(rs!=null){
						while(rs.next()){
							manganhangtra = rs.getString("NHC");
						}
					}
				}
				catch (Exception e){
					e.printStackTrace();
				}finally{
					try {
						rs.close();
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
	    	
	    	response.setContentType("application/pdf");
			response.setHeader("Content-Disposition"," inline; filename=UyNhiemChi" + obj.getId() + ".pdf");
			
			
//			ServletOutputStream outstream = response.getOutputStream();
			
			if(manganhangtra.equals("IVB")){
//				Document document = new Document();
				//document.setPageSize(PageSize.A4.rotate()); //CANH H??A ????N THEO CHI???U D???C
				document.setMargins(2.0f*CONVERT, 2.0f*CONVERT, 0f*CONVERT, 0f*CONVERT); // L,R, T, B
				this.CreateUyNhiemChiPdf(document, outstream, obj);
				document.close();
			}
			else{
//				float PAGE_LEFT = 1*CONVERT, PAGE_RIGHT = 1*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.5f*CONVERT; //cm
//				Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);//CANH THEO CHI???U NGANG
//				Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
				
				this.CreateUyNhiemChiPdf(document, outstream, obj);
				document.close();
			}
		}else
		{
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=TrangTrong.pdf");
			this.createEmptyPdf(document, outstream, type);
			document.close();
		}
		
//		return;
	} 

	//0: uy nhiem chi
	//1: phieu chi
	//2: phieu thu
	//3: ko co form
	private String getKieuChungTu(String id) {
		String result = "0";
		String query = " SELECT ISNULL(NHC.NganHang_FK, 0) AS NHC, ISNULL(NHN.NganHang_FK, 0) AS NHN " +
			" , ISNULL(NHCC.MA, '') AS MANGANHANGCHUYEN  , ISNULL(NHCN.MA, '') AS MANGANHANGNHAN " + 
			" FROM ERP_DIEUCHUYENTIEN DC " +  
			" INNER JOIN ERP_NGANHANG_CONGTY NHC ON NHC.PK_SEQ = DC.NGANHANGCHUYEN_FK " +
			" LEFT JOIN ERP_NGANHANG NHCC ON NHCC.PK_SEQ = NHC.NganHang_FK " + 
			" INNER JOIN ERP_NGANHANG_CONGTY NHN ON NHN.PK_SEQ = DC.NGANHANGNHAN_FK " +
			" LEFT JOIN ERP_NGANHANG NHCN ON NHCN.PK_SEQ = NHN.NganHang_FK " + 
			" WHERE DC.PK_SEQ = " + id;
		System.out.println("getkieuchung tuquery: " + query);
		ResultSet rs = this.db.get(query);
		System.out.println("getkieuchung tuquery: " + query);
		if (rs != null)
		{
			try {
				if (rs.next())
				{
					String nhc = rs.getString("NHC");
					System.out.println("nhc: " + nhc);
					String nhn = rs.getString("NHN");
					System.out.println("nhc_" + nhc + "_nhn" + nhn);
					String maNganHangChuyen = rs.getString("MANGANHANGCHUYEN");
					String maNganHangNhan = rs.getString("MANGANHANGNHAN");
					System.out.println("maNganHangChuyen" + maNganHangChuyen);
//					if (nhc.trim().equals("0") != true && nhn.trim().equals("0") != true)
					if (maNganHangChuyen.trim().equals("TM") != true && maNganHangNhan.trim().equals("TM") != true)
					{
						if ("ACB,VCB,EIB,AGB".trim().contains(maNganHangChuyen))
						{
							System.out.println("result is 0");
							result = "0";
						}else result = maNganHangChuyen;
					}
					else
					{
						if (maNganHangChuyen.trim().equals("TM"))
							result = "1";
						else 
							result = "2";
//							if (nhc.trim().equals("0"))
//								result = "1";
//							else 
//								result = "2";
					}
					System.out.println("result: " + result);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally{
				try {
					rs.close();
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}
		return result;
	}

	private void createEmptyPdf(Document document, ServletOutputStream outstream, String maNganHang) throws IOException
	{
		try {
			PdfWriter.getInstance(document, outstream);
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Paragraph hd = new Paragraph("Ch??a c?? form Phi???u ???y nhi???m chi cho ng??n h??ng " + maNganHang, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			document.add(hd);
			document.close();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void CreatePhieuChiPdf(Document document, ServletOutputStream outstream, IErpDieuchuyentien obj, String Id, boolean isChi) throws IOException
	{
		System.out.println("In phieu chi::");
		try{
			dbutils db = new dbutils();
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			PdfWriter.getInstance(document, outstream);
			
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			// C??U L???Y SQL
			String sotientt = "";
			String noidungtt = "";
			String ngayghinhan = "";
			
			String traTen = "", traStk = "", traNganHang = "", traNganHangMa = "",  traDiaChi = "", traChiNhanh = "";
			String nhanTen = "", nhanStk = "", nhanNganHang = "", nhanDiaChi = "", nhanChiNhanh = "" , nhanvien_fk;
			
			
			String sochungtu = "";
			
			String lydo = "";
			String nguoinhan = "";
			String diachi = "";
			String chungtukemtheo = "";
			String nguoilapphieu = "";
			
			String query =
						" 	SELECT DC.PK_SEQ, DC.SOCHUNGTU, DC.NGAYCHUNGTU AS NGAYGHINHAN, ISNULL(NH1.TEN, N'TI???N M???T') AS NGUOINHAN, '' AS DIACHI, DC.GHICHU AS NOIDUNGTT, TT.PK_SEQ AS TTID, TT.MA AS TIENTE, ISNULL(DC.TIGIA, 1) AS TIGIA, " +   
						" 	DC.NGANHANGCHUYEN_FK, DC.NGANHANGNHAN_FK, ISNULL(DC.GHICHU,'') AS GHICHU,    " +
						" 	ISNULL(DC.SOTIENNT, 0) AS SOTIENNT, ISNULL(DC.SOTIENVND, 0) AS SOTIENTT, DC.TRICHPHI,   " +
						" 	NHTRICHPHI_FK, ISNULL(PHINT, 0) AS PHINT, ISNULL(PHIVND, 0) AS PHIVND, ISNULL(VATNT, 0) AS VATNT, ISNULL(VATVND, 0) AS VATVND,   " +
						" 	ISNULL(MAHOADON, '') AS MAHOADON, ISNULL(MAUHOADON, '') MAUHOADON, ISNULL(KYHIEU, '') KYHIEU, ISNULL(SOHOADON, '') AS SOHOADON,   " +
						" 	ISNULL(NGAYHOADON, '') AS NGAYHOADON, ISNULL(TENNGANHANG, '') AS TENNGANHANG, ISNULL(MST, '') AS MST, " +
						" 	DC.NCC_FK, DC.NHKYQUY_FK, DC.MUAHANG_FK, DC.LOAI , DC.NGUOISUA " +
						" 	FROM ERP_DIEUCHUYENTIEN DC   " +
						" 	INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = DC.TIENTE_FK   " +
						" 	LEFT JOIN ERP_NGANHANG_CONGTY NH_CTY1 ON NH_CTY1.PK_SEQ = DC.NGANHANGCHUYEN_FK   " +
						" 	LEFT JOIN ERP_NGANHANG NH1 ON NH1.PK_SEQ = NH_CTY1.NGANHANG_FK   " +
						" 	LEFT JOIN ERP_CHINHANH CN1 ON CN1.PK_SEQ = NH_CTY1.CHINHANH_FK   " +
						" 	LEFT JOIN ERP_NGANHANG_CONGTY NH_CTY2 ON NH_CTY2.PK_SEQ = DC.NGANHANGNHAN_FK   " +
						" 	LEFT JOIN ERP_NGANHANG NH2 ON NH2.PK_SEQ = NH_CTY2.NGANHANG_FK   " +
						" 	LEFT JOIN ERP_CHINHANH CN2 ON CN2.PK_SEQ = NH_CTY2.CHINHANH_FK   " +
						" 	LEFT JOIN NHANVIEN NV1 ON NV1.PK_SEQ = DC.NGUOISUA   " +
						" 	WHERE DC.PK_SEQ = " + Id;  
				 	
					System.out.println("THONGTINNPP__:" + query);
			
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				try 
				{
					while(rs.next())
					{
						sochungtu = rs.getString("pk_seq");
						ngayghinhan = rs.getString("NGAYGHINHAN");
						noidungtt = rs.getString("NOIDUNGTT");
						sotientt = formatter.format(rs.getDouble("sotienTT"));
						lydo = rs.getString("NOIDUNGTT");
						nguoinhan = rs.getString("nguoinhan");
						diachi = rs.getString("diachi");
						nguoilapphieu = rs.getString("NGUOISUA");
						System.out.println("nguoilapphieu: " + nguoilapphieu);
					}
				} 
				catch (SQLException e) 
				{
					System.out.println("[ErpTTHoaDonPdfSvl.CreateUyNhiemChi] Exception: " + e.getMessage());
				}
			}
			System.out.println("nguoilapphieu2: " + nguoilapphieu);
			db.shutDown();
			
			//C??NG TY C??? PH???N ????? H???P H??? LONG
			PdfPTable tableheader =new PdfPTable(3);
			tableheader.setWidthPercentage(100);	
			float[] withstableheader = {120f,60f,100f};
			tableheader.setWidths(withstableheader);
			
			PdfPCell cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			Paragraph hd = new Paragraph("C??NG TY C??? PH???N ????? H???P H??? LONG" , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			hd = new Paragraph("" , new Font(bf, 11, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			if (isChi == true)
				hd = new Paragraph("M???u s??? 02 - TT" , new Font(bf, 12, Font.BOLD));
			else
				hd = new Paragraph("M???u s??? 01 - TT" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			hd = new Paragraph("71 L?? Lai, Ng?? Quy???n, H???i Ph??ng" , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			hd = new Paragraph("" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			hd = new Paragraph("Q?? s??? 1141-BTC/11-95" , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			document.add(tableheader);
			
			//????N V??? B??N H??NG
			
			PdfPTable table1 = new PdfPTable(2);
			table1.setWidthPercentage(100);
			float[] withs1 = {100f, 80f};
			table1.setWidths(withs1);
					
			cell = new PdfPCell();	
			if (isChi == true)
				hd = new Paragraph("PHI???U CHI" , new Font(bf, 14, Font.BOLD));//NGUOI MUA
			else
				hd = new Paragraph("PHI???U THU" , new Font(bf, 14, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(1.0f*CONVERT);	
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("S???:" + sochungtu, new Font(bf, 12, Font.NORMAL));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			//cell.setFixedHeight(1.0f*CONVERT);	
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			document.add(table1);
			
			//NG??Y CH???NG T???
			String [] ngayPT = ngayghinhan.split("-");
			

			PdfPTable table2 = new PdfPTable(2);
			table2.setWidthPercentage(100);
			float[] withs2 = {80f, 120f};
			table2.setWidths(withs2);
			
			cell = new PdfPCell();	
			hd = new Paragraph(""  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("Ng??y " + ngayPT[2] + " th??ng " + ngayPT[1] +  " n??m " + ngayPT[0]  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph(""  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			
			cell = new PdfPCell();	
			hd = new Paragraph("N???: .........   C??: ........."  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
			
			document.add(table2);
			
			//H??? T??N NG?????I N???P TI???N
						
			PdfPTable table3 =new PdfPTable(2);
			table3.setWidthPercentage(100);
			float[] withs3 = {40f, 105f};
			table3.setWidths(withs3);
						
			cell = new PdfPCell();hd = new Paragraph( "H??? v?? t??n ng?????i nh???n ti???n: ", new Font(bf, 12, Font.NORMAL));
			if (isChi == true)
				hd = new Paragraph( "H??? v?? t??n ng?????i nh???n ti???n: ", new Font(bf, 12, Font.NORMAL));
			else
				hd = new Paragraph( "H??? v?? t??n ng?????i n???p ti???n: ", new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph(nguoinhan, new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);		
			
			
						
			cell = new PdfPCell();	
			hd = new Paragraph( "?????a ch???: ", new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			table3.addCell(cell);	
			
			cell = new PdfPCell();	
			hd = new Paragraph(diachi, new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);	
			
					
			cell = new PdfPCell();	
			if (isChi == true)
				hd = new Paragraph( "L?? do chi: ", new Font(bf, 12, Font.NORMAL));
			else
				hd = new Paragraph( "L?? do n???p: ", new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);	
			
			cell = new PdfPCell();	
			hd = new Paragraph( lydo, new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			table3.addCell(cell);	
			
		
			cell = new PdfPCell();	
			hd = new Paragraph( "S??? ti???n b???ng s???: " , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);	
			
			cell = new PdfPCell();	
			hd = new Paragraph( DinhDangCANFOCO(sotientt) , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);	
			
			
			
			DocTien doctien = new DocTien();
		    
			String tien = doctien.docTien(Math.round(Double.parseDouble(sotientt.replaceAll(",", ""))));
						
			tien = tien.substring(0, 1).toUpperCase() + tien.substring(1, tien.length());
			if(tien.equals("?????ng"))
				 tien="Kh??ng ?????ng";
									
			cell = new PdfPCell();	
			hd = new Paragraph( "S??? ti???n b???ng ch???: " , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			//cell.setPaddingLeft(0.5f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);	
			
			cell = new PdfPCell();	
			hd = new Paragraph(tien , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table3.addCell(cell);	
			
			
			String ctkt = "";
			if(chungtukemtheo.length() > 0)
				ctkt = chungtukemtheo +" ch???ng t??? g???c";
			
			cell = new PdfPCell();	
			hd = new Paragraph( "K??m theo: "+ ctkt , new Font(bf, 2, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.7f*CONVERT);	
			cell.setPaddingLeft(0.5f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			table3.addCell(cell);	
						
			document.add(table3);
			
			PdfPTable table9 =new PdfPTable(1);
			table9.setWidthPercentage(100);
			float[] withs9 = {200f};
			table9.setWidths(withs9);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "???? nh???n ????? ti???n: ..........................................." , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(hd);
			cell.setBorder(0);	
			table9.addCell(cell);
						
			document.add(table9);
			
			PdfPTable table10 =new PdfPTable(1);
			table10.setWidthPercentage(100);
			float[] withs10 = {200f};
			table10.setWidths(withs10);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "Ng??y \t \t \t \t \t \t \t \t th??ng	\t \t \t \t \t \t \t \t n??m	\t \t \t \t \t \t \t \t	" , new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_RIGHT);
			//cell.setFixedHeight(0.7f*CONVERT);	
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table10.addCell(cell);
						
			document.add(table10);
			
			
			PdfPTable table11 =new PdfPTable(5);
			table11.setWidthPercentage(100);
			float[] withs11 = {50f,80f,50f,50f,50f};
			table11.setWidths(withs11);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "Th??? tr?????ng ????n v???" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "K??? to??n tr?????ng" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			
			cell = new PdfPCell();	
			hd = new Paragraph( "Ng?????i l???p phi???u" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("Th??? qu???"  , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			
			cell = new PdfPCell();	
			if (isChi == true)
				hd = new Paragraph("Ng?????i nh???n ti???n"  , new Font(bf, 12, Font.BOLD));
			else
				hd = new Paragraph("Ng?????i n???p ti???n"  , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			///
			cell = new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(3f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			table11.addCell(cell);
			

			//L???Y K??? TO??N TR?????NG
			query = "select b.TEN from ERP_CHUCDANH a inner join NHANVIEN b on a.NHANVIEN_FK = b.PK_SEQ where a.PK_SEQ ='100001'";
			
			String ketoantruong = "";
		
			
			rs = this.db.get(query);
			try{
				if(rs != null){
					rs.next();
						ketoantruong = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
			
			System.out.println("nguoilapphieu  3: " + nguoilapphieu);
			query = "select TEN from NHANVIEN WHERE PK_SEQ = '" + nguoilapphieu + "'";
			
			rs = this.db.get(query);
			try{
				if(rs != null){
					rs.next();
						nguoilapphieu = rs.getString("TEN");
					rs.close();
				}
			}catch(java.sql.SQLException e){
				System.out.println(e.toString());
			}
			
			cell = new PdfPCell();	
			hd = new Paragraph( ketoantruong , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(3f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			
			cell = new PdfPCell();	
			hd = new Paragraph( nguoilapphieu , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(3f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(3f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
			
			
			cell = new PdfPCell();	
			hd = new Paragraph( "" , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setFixedHeight(3f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table11.addCell(cell);
						
			document.add(table11);
			
//			document.close(); 
			System.out.println(" ????ng th??nh c??ng.");
		}
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
	private void CreateUyNhiemChiPdf(Document document, ServletOutputStream outstream, IErpDieuchuyentien obj) 
	{
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,###");
			NumberFormat formatter2 = new DecimalFormat("###########"); 
//			NumberFormat formatter3 = new DecimalFormat("#,###,##0.000");
			
//			String soTk = "";
			String sotientt = "";
			long sotien = 0;
//			int loaitt = 0;
			String tienbangchu = "";
			String noidungtt = "";
			String ngayghinhan = "";
			
			String traTen = "", traStk = "", traNganHang = "", traNganHangMa = "",  traDiaChi = "", traChiNhanh = "";
			String traDienthoai = "";
			String nhanTen = "", nhanStk = "", nhanNganHang = "", nhanDiaChi = "", nhanChiNhanh = "" , nhanvien_fk;
			
			dbutils db = new dbutils();
			/*String query =  " select tthd.SOTAIKHOAN as SoTaiKhoan, isnull(nh.TEN, '') as traNganHang, isnull(nh.MA, '') as traNganHangMa, isnull(cn.TEN, '') as traChiNhanh, isnull((select ten from erp_congty where pk_seq = 100005), '') as congty, " +
							" 	isnull(ncc.TEN,'') as nccTen, isnull(ncc.DIACHI,'') as nccDiaChi, ncc.sotaikhoan as nccStk, isnull(nccnh.TEN, '') AS nccNganHang, isnull(ncccn.TEN, '') AS nccChiNhanh, " +
							" 	isnull(nv.TEN,'') as nvTen, isnull(nv.DIACHI,'') as nvDiaChi, nv.sotaikhoan as nvStk, isnull(nvnh.TEN, '') AS nvNganHang, isnull(nvcn.TEN, '') AS nvChiNhanh, " +
							" 	tthd.SOTIENTT as SOTIENTT, tthd.NOIDUNGTT as NOIDUNGTT, " +
							" 	tthd.NGAYGHINHAN as NGAYGHINHAN, " +
							"	tthd.nhanvien_fk  " +
							" from ERP_THANHTOANHOADON tthd " +
							" left join ERP_NGANHANG nh on tthd.nganhang_fk = nh.pk_seq  " +
							" left join ERP_CHINHANH cn on tthd.chinhanh_fk = cn.pk_seq  " +
							" left join ERP_NHACUNGCAP ncc on tthd.ncc_fk = ncc.pk_seq " +
							" left join ERP_NGANHANG nccnh on ncc.nganhang_fk = nccnh.pk_seq  " +
							" left join ERP_CHINHANH ncccn on ncc.chinhanh_fk = ncccn.pk_seq  " +
							" left join ERP_NHANVIEN nv on tthd.NHANVIEN_FK = nv.PK_SEQ" +
							" left join ERP_NGANHANG nvnh on nv.nganhang_fk = nvnh.pk_seq  " +
							" left join ERP_CHINHANH nvcn on nv.chinhanh_fk = nvcn.pk_seq  " +
							" where tthd.pk_seq = '"+tthdBean.getId()+"' ";*/
			String query = 
			" 	SELECT DC.PK_SEQ, DC.SOCHUNGTU, DC.NGAYCHUNGTU AS NGAYGHINHAN, ISNULL(NH1.TEN, N'TI???N M???T') AS NGUOINHAN, '' AS DIACHI, DC.GHICHU AS NOIDUNGTT, TT.PK_SEQ AS TTID, TT.MA AS TIENTE, ISNULL(DC.TIGIA, 1) AS TIGIA, " +   
			" 	DC.NGANHANGCHUYEN_FK, DC.NGANHANGNHAN_FK, ISNULL(DC.GHICHU,'') AS GHICHU,    " +
			" 	ISNULL(DC.SOTIENNT, 0) AS SOTIENNT, ISNULL(DC.SOTIENVND, 0) AS SOTIENTT, DC.TRICHPHI,   " +
			" 	NHTRICHPHI_FK, ISNULL(PHINT, 0) AS PHINT, ISNULL(PHIVND, 0) AS PHIVND, ISNULL(VATNT, 0) AS VATNT, ISNULL(VATVND, 0) AS VATVND,   " +
			" 	ISNULL(MAHOADON, '') AS MAHOADON, ISNULL(MAUHOADON, '') MAUHOADON, ISNULL(KYHIEU, '') KYHIEU, ISNULL(SOHOADON, '') AS SOHOADON,   " +
			" 	ISNULL(NGAYHOADON, '') AS NGAYHOADON, ISNULL(TENNGANHANG, '') AS TENNGANHANG, ISNULL(MST, '') AS MST, " +
			" 	DC.NCC_FK, DC.NHKYQUY_FK, DC.MUAHANG_FK, DC.LOAI , DC.NGUOISUA, " +
			"   isnull((select ten from erp_congty where pk_seq = 100005), '') as congty, " +
			"   isnull((select DIENTHOAI from erp_congty where pk_seq = 100005), '') as traDienthoai," +
			"    NH1.TEN AS traNganHang, NH1.MA AS traNganHangMa, CN1.TEN AS traChiNhanh, NH_CTY1.SoTaiKhoan, " +
			"   NH_CTY2.ChuTaiKhoan AS nccTen, '' AS NCCDIACHI, NH_CTY2.SoTaiKhoan AS nccStk, NH2.TEN AS nccNganHang " + 
			" 	FROM ERP_DIEUCHUYENTIEN DC   " +
			" 	INNER JOIN ERP_TIENTE TT ON TT.PK_SEQ = DC.TIENTE_FK   " +
			" 	LEFT JOIN ERP_NGANHANG_CONGTY NH_CTY1 ON NH_CTY1.PK_SEQ = DC.NGANHANGCHUYEN_FK   " +
			" 	LEFT JOIN ERP_NGANHANG NH1 ON NH1.PK_SEQ = NH_CTY1.NGANHANG_FK   " +
			" 	LEFT JOIN ERP_CHINHANH CN1 ON CN1.PK_SEQ = NH_CTY1.CHINHANH_FK   " +
			" 	LEFT JOIN ERP_NGANHANG_CONGTY NH_CTY2 ON NH_CTY2.PK_SEQ = DC.NGANHANGNHAN_FK   " +
			" 	LEFT JOIN ERP_NGANHANG NH2 ON NH2.PK_SEQ = NH_CTY2.NGANHANG_FK   " +
			" 	LEFT JOIN ERP_CHINHANH CN2 ON CN2.PK_SEQ = NH_CTY2.CHINHANH_FK   " +
			" 	LEFT JOIN NHANVIEN NV1 ON NV1.PK_SEQ = DC.NGUOISUA   " +
			" 	WHERE DC.PK_SEQ = " + obj.getId();  
	 	
			System.out.println("[ErpTTHoaDonPdfSvl.CreateUyNhiemChi] query 1 = " + query);
			
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				try 
				{
					while(rs.next())
					{
						ngayghinhan = rs.getString("NGAYGHINHAN");
						noidungtt = rs.getString("NOIDUNGTT");
						sotientt = formatter.format(rs.getDouble("SOTIENTT"));
						traTen = rs.getString("congty");
						traStk = rs.getString("SoTaiKhoan");
						traDienthoai = rs.getString("traDienthoai");
						traNganHang = rs.getString("traNganHang");
						traNganHangMa = rs.getString("traNganHangMa");
						traChiNhanh = rs.getString("traChiNhanh");
						//traDiaChi = rs.getString("traDiaChi");
//						 nhanvien_fk = rs.getString("nhanvien_fk");
						
/*						if(nhanvien_fk != null) {
							nhanTen = rs.getString("nvTen");
							nhanDiaChi = rs.getString("nvDiaChi");
							nhanStk = rs.getString("nvStk");
							nhanNganHang = rs.getString("nvNganHang");
						} else {
*/							nhanTen = rs.getString("nccTen");
							System.out.println("nhanTen" + nhanTen);
							nhanDiaChi = rs.getString("NCCDIACHI");
							nhanStk = rs.getString("nccStk");
							nhanNganHang = rs.getString("nccNganHang");
//						}
						try {
							sotien = Long.parseLong(formatter2.format(rs.getDouble("SOTIENTT")));
							tienbangchu = DocTien.docTien(sotien);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} 
				catch (SQLException e) 
				{
					System.out.println("[ErpTTHoaDonPdfSvl.CreateUyNhiemChi] Exception: " + e.getMessage());
				}finally{
					try {
						rs.close();
					} catch (Exception e2) {
						// TODO: handle exception
						e2.printStackTrace();
					}
				}
			}
			
			db.shutDown();
			
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font6italic = new Font(bf, 6, Font.ITALIC);
			Font font7 = new Font(bf, 7, Font.NORMAL);
			Font font8bold = new Font(bf, 8, Font.BOLD);
			Font font9bold = new Font(bf, 9, Font.BOLD);
			Font font10bold = new Font(bf, 10, Font.BOLD);
			Font font11bold = new Font(bf, 11, Font.BOLD);
			Font font11bi = new Font(bf, 11, Font.BOLDITALIC);
			Font font12bold = new Font(bf, 12, Font.BOLD);
			Font font13bold = new Font(bf, 13, Font.BOLD);
			Font font14bold = new Font(bf, 14, Font.BOLD);
			Font font15bold = new Font(bf, 15, Font.BOLD);
			Font font7italic = new Font(bf, 7, Font.ITALIC);
			Font font9italic = new Font(bf, 9, Font.ITALIC);
			Font font10italic = new Font(bf, 10, Font.ITALIC);
			Font font11italic = new Font(bf, 11, Font.ITALIC);
			Font font12italic = new Font(bf, 12, Font.ITALIC);
			Font font9underline = new Font(bf, 9, Font.UNDERLINE);
			Font font9bu = new Font(bf, 9, Font.BOLD); font9bu.setStyle("bold,underline");
			Font font10bi = new Font(bf, 10, Font.BOLDITALIC);
			Font font8 = new Font(bf, 8, Font.NORMAL);
			Font font9 = new Font(bf, 9, Font.NORMAL);
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font11 = new Font(bf, 11, Font.NORMAL);
			Font font12 = new Font(bf, 12, Font.NORMAL);
			Font font13 = new Font(bf, 13, Font.NORMAL);
			Font font14 = new Font(bf, 14, Font.NORMAL);
			
			float CONVERT = 28.346457f; // = 1cm
			float[] TABLE_WIDTHS = {18.0f * CONVERT};

			float[] TABLE_HEADER_WIDTHS = new float[] {7.0f * CONVERT, 11.0f * CONVERT};
			float[] TABLE_CONTENT_WIDTHS = new float[] {8.0f * CONVERT, 10.0f * CONVERT};
			float[] TABLE_CONTENT_FIELDS_WIDTHS = new float[] {TABLE_CONTENT_WIDTHS[0]*2/5, TABLE_CONTENT_WIDTHS[0]*3/5};
			float[] TABLE_FOOTER_WIDTHS = new float[] {8.0f * CONVERT, 10.0f * CONVERT};
			
			float[] TABLE_ACB_WIDTHS = new float[] {12.0f * CONVERT, 1.75f * CONVERT,  3.25f * CONVERT,  1.0f * CONVERT};
			float[] TABLE_EIB_WIDTHS = new float[] {15.0f * CONVERT,0.5f * CONVERT,6.0f * CONVERT};
			float[] TABLE_AGB_WIDTHS = new float[] {5.0f * CONVERT, 8.0f * CONVERT,  4.0f * CONVERT,  4.0f * CONVERT};
			PdfPCell cell, cell2, cell3, cell4, cell5, cell11;
			
			if(traNganHangMa.trim().equals("VCB")) 
			{
				//Ch??? Form quay g??c 90 ?????
				PdfContentByte cb = writer.getDirectContent();
				cb.beginText();
		        cb.setFontAndSize(bf, 6);
		        cb.setTextMatrix(0, 1, -1, 0, 0.9f*CONVERT, 4.4f * CONVERT);
		        cb.showText("Form NHBL 04 - 06/99, Li??n 1: L??u, Li??n 2: KH");
		        cb.endText();
		        
		        //Logo Vietcombank
		        Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/images/vietcombank.png");
				hinhanh.scalePercent(75);
				hinhanh.setAbsolutePosition(1.0f * CONVERT, document.getPageSize().getHeight() - 2.0f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				cell = new PdfPCell(new Paragraph("(H??a ????n ???????c ban h??nh k??m theo Quy???t ?????nh s??? 153 ng??y 06/04/2011 cua TG?? VCB)", font7italic)); //Header
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				table.addCell(cell);
				
				//HEADER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				
				PdfPTable table2 = new PdfPTable(2);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidths(TABLE_HEADER_WIDTHS);
				table2.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell(new Paragraph("CH???NG T??? GIAO D???CH", font9bold));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(2);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("???Y NHI???M CHI-PAYMENT ORDER", font12bold));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(2);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph(traChiNhanh, font10));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("Ng??y (Date)   " + getDate(ngayghinhan), font10bi));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("?????a ch???: ", font8));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph(" ", font10));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("M?? VAT: ", font8));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("S??? H??a ????n-Invoice No: ", font10));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				table2.addCell(cell2);
				
				cell.addElement(table2);
				table.addCell(cell);
				
				//CONTENT
				cell = new PdfPCell(new Paragraph("", font10bold));
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				//cell.setFixedHeight(2 * CONVERT);
				
				table2 = new PdfPTable(2);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidths(TABLE_CONTENT_WIDTHS);
				table2.setWidthPercentage(100.0f);
				
				//Left
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				cell2.setPaddingLeft(0.3f*CONVERT);
				cell2.setPaddingRight(0.2f*CONVERT);
				//cell2.setFixedHeight(2 * CONVERT);
				
				PdfPTable table3 = new PdfPTable(1);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {TABLE_CONTENT_WIDTHS[0]});
				table3.setWidthPercentage(100.0f);
				
				//????? ngh??? ghi n??? t??i kho???n
				cell3 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("????? NGH??? GHI N??? T??I KHO???N", font9bold)); 
				p.add(new Chunk(" (Please Debit account)", font9));
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setFixedHeight(0.8f * CONVERT);
				table3.addCell(cell3);
				
				//S??? t??i kho???n
				cell3 = new PdfPCell();
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				
				PdfPTable table4 = new PdfPTable(2);	
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setWidths(TABLE_CONTENT_FIELDS_WIDTHS);
				table4.setWidthPercentage(100.0f);
				
				cell4 = new PdfPCell(new Paragraph("S??? TK (A/C No.):", font9));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(0.7f * CONVERT);
				cell4.setBorder(0);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(traStk, font9bold));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				cell4.setFixedHeight(0.7f * CONVERT);
				table4.addCell(cell4);

				cell3.addElement(table4);
				table3.addCell(cell3);
				
				//T??n TK + ?????a ch???
				cell3 = new PdfPCell();
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				
				table4 = new PdfPTable(2);
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setWidths(TABLE_CONTENT_FIELDS_WIDTHS);
				table4.setWidthPercentage(100.0f);
				
				cell4 = new PdfPCell(new Paragraph("T??N TK (A/C name.): \n \n?????A CH??? (Address):", font9));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				cell4.setFixedHeight(1.2f * CONVERT);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(traTen, font9bold));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				cell4.setFixedHeight(1.2f * CONVERT);
				table4.addCell(cell4);

				cell3.addElement(table4);
				table3.addCell(cell3);
				
				//T???i NH
				cell3 = new PdfPCell();
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				
				table4 = new PdfPTable(2);	
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setWidths(TABLE_CONTENT_FIELDS_WIDTHS);
				table4.setWidthPercentage(100.0f);
				
				cell4 = new PdfPCell(new Paragraph("T???i NH (With Bank):", font9));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				cell4.setFixedHeight(0.7f * CONVERT);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(traNganHang, font8bold));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				cell4.setFixedHeight(0.8f * CONVERT);
				table4.addCell(cell4);

				cell3.addElement(table4);
				table3.addCell(cell3);
				
				//Ghi c?? t??i kho???n
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("& GHI C?? T??I KHO???N", font9bold)); 
				p.add(new Chunk(" (& Credit account:)", font9));
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				cell3.setFixedHeight(1.0f * CONVERT);
				table3.addCell(cell3);
				
				//S??? t??i kho???n
				cell3 = new PdfPCell();
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				
				table4 = new PdfPTable(2);	
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setWidths(TABLE_CONTENT_FIELDS_WIDTHS);
				table4.setWidthPercentage(100.0f);
				
				cell4 = new PdfPCell(new Paragraph("S??? TK (A/C No.):", font9));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				cell4.setFixedHeight(0.7f * CONVERT);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(nhanStk, font9bold));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				table4.addCell(cell4);

				cell3.addElement(table4);
				table3.addCell(cell3);
				
				//T??n TK + ?????a ch???
				cell3 = new PdfPCell();
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				
				table4 = new PdfPTable(2);	
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setWidths(TABLE_CONTENT_FIELDS_WIDTHS);
				table4.setWidthPercentage(100.0f);
				
				cell4 = new PdfPCell(new Paragraph("T??N TK (A/C name.): \n \n?????A CH??? (Address):", font9));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setFixedHeight(1.2f * CONVERT);
				cell4.setBorder(0);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(nhanTen, font9bold));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				cell4.setFixedHeight(1.2f * CONVERT);
				table4.addCell(cell4);

				cell3.addElement(table4);
				table3.addCell(cell3);
				
				//T???i NH
				cell3 = new PdfPCell();
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				
				table4 = new PdfPTable(2);	
				table4.setHorizontalAlignment(Element.ALIGN_CENTER);
				table4.setWidths(TABLE_CONTENT_FIELDS_WIDTHS);
				table4.setWidthPercentage(100.0f);
				
				cell4 = new PdfPCell(new Paragraph("T???i NH (With Bank):", font9));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(0.7f * CONVERT);
				cell4.setBorder(0);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(nhanNganHang, font8bold));
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setFixedHeight(0.8f * CONVERT);
				cell4.setBorder(0);
				table4.addCell(cell4);

				cell3.addElement(table4);
				table3.addCell(cell3);

				cell2.addElement(table3);
				table2.addCell(cell2);
				
				//Right
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				cell2.setPaddingLeft(0.2f*CONVERT);
				cell2.setPaddingRight(0.4f*CONVERT);
				//cell2.setFixedHeight(2 * CONVERT);
				
				//Row1
				table3 = new PdfPTable(2);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {TABLE_CONTENT_WIDTHS[1]/2, TABLE_CONTENT_WIDTHS[1]/2});
				table3.setWidthPercentage(100.0f);
				
				//S??? ti???n
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? TI???N", font9bold)); 
				p.add(new Chunk(" (With amount)", font9));
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setFixedHeight(0.8f * CONVERT);
				table3.addCell(cell3);
				
				//S??? ti???n
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("PH?? NH", font9bold)); 
				p.add(new Chunk(" (Bank charges)", font9)); 
				p.setAlignment(Element.ALIGN_RIGHT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setFixedHeight(0.8f * CONVERT);
				table3.addCell(cell3);
				
				cell2.addElement(table3);
				
				//Row 2, 3
				
				table3 = new PdfPTable(5);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {TABLE_CONTENT_WIDTHS[1]*4/10, TABLE_CONTENT_WIDTHS[1]*2/10, TABLE_CONTENT_WIDTHS[1]*1.5f/10, TABLE_CONTENT_WIDTHS[1]*1.5f/10, TABLE_CONTENT_WIDTHS[1]*1/10});
				table3.setWidthPercentage(100.0f);
				
				//Row 2
				//B???ng s???
				cell3 = new PdfPCell(new Paragraph("B???NG S??? (In figures):", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorderWidthLeft(1);
				cell3.setBorderWidthTop(1);
				cell3.setBorderWidthBottom(1);
				cell3.setBorderWidthRight(0);
				cell3.setFixedHeight(0.7f * CONVERT);
				table3.addCell(cell3);
				
				//S??? ti???n b???ng s???
				cell3 = new PdfPCell(new Paragraph(sotientt, font9));
				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidthLeft(0);
				cell3.setBorderWidthTop(1);
				cell3.setBorderWidthBottom(1);
				cell3.setBorderWidthRight(1);
				cell3.setFixedHeight(0.7f * CONVERT);
				table3.addCell(cell3);
				
				//VND
				cell3 = new PdfPCell(new Paragraph("VN??", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidthLeft(0);
				cell3.setBorderWidthTop(1);
				cell3.setBorderWidthBottom(1);
				cell3.setBorderWidthRight(1);
				cell3.setFixedHeight(0.7f * CONVERT);
				table3.addCell(cell3);
				
				//Ph?? trong
				cell3 = new PdfPCell(new Paragraph("Ph?? trong\nIncluding", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(0);
				cell3.setFixedHeight(1.0f * CONVERT);
				table3.addCell(cell3);
				
				//
				cell3 = new PdfPCell(new Paragraph(" ", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				cell3.setFixedHeight(0.7f * CONVERT);
				table3.addCell(cell3);
				
				
				//Row 3
				//B???NG CH??? (In words): 3cell
				cell3 = new PdfPCell(new Paragraph("B???NG CH??? (In words):", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setColspan(3);
				cell3.setBorderWidthLeft(1);
				cell3.setBorderWidthTop(0);
				cell3.setBorderWidthBottom(0);
				cell3.setBorderWidthRight(1);
				cell3.setFixedHeight(0.5f * CONVERT);
				table3.addCell(cell3);
				
				// 2cell
				cell3 = new PdfPCell(new Paragraph(" ", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setColspan(2);
				cell3.setBorderWidth(0);
				table3.addCell(cell3);
				
				//Row 4
				//N???i dung ti???n b???ng ch???
				cell3 = new PdfPCell(new Paragraph(tienbangchu, font9));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setColspan(3);
				cell3.setBorderWidthLeft(1);
				cell3.setBorderWidthTop(0);
				cell3.setBorderWidthBottom(1);
				cell3.setBorderWidthRight(1);
				cell3.setRowspan(2);
				cell3.setFixedHeight(1.25f * CONVERT);
				table3.addCell(cell3);
				
				//Ph?? ngo??i
				cell3 = new PdfPCell(new Paragraph("Ph?? ngo??i\nExcluding", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(0);
				cell3.setFixedHeight(1.0f * CONVERT);
				table3.addCell(cell3);
				
				//
				cell3 = new PdfPCell(new Paragraph(" ", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorderWidth(1);
				cell3.setFixedHeight(0.7f * CONVERT);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph(" ", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setColspan(2);
				cell3.setBorderWidth(0);
				table3.addCell(cell3);
				
				//Row 5
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("N???I DUNG", font9bold)); 
				p.add(new Chunk(" (Details of Payment):", font9));
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorderWidth(0);
				cell3.setColspan(5);
				cell3.setFixedHeight(0.8f * CONVERT);
				table3.addCell(cell3);
				
				//Row6: N???i dung
				cell3 = new PdfPCell(new Paragraph(noidungtt, font10));
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setColspan(5);
				cell3.setBorderWidth(0);
				cell3.setMinimumHeight(1.0f * CONVERT);
				table3.addCell(cell3);
				
				//Row 7
				cell3 = new PdfPCell(new Paragraph("K??? TO??N TR?????NG", font9bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setColspan(1);
				cell3.setBorderWidth(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("CH??? T??I KHO???N K?? & ????NG D???U", font9bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setColspan(4);
				cell3.setBorderWidth(0);
				table3.addCell(cell3);
				
				//Row 8
				cell3 = new PdfPCell(new Paragraph("Chief accountant", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setColspan(1);
				cell3.setBorderWidth(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Acc. Holder & Stamp", font9));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setColspan(4);
				cell3.setBorderWidth(0);
				table3.addCell(cell3);
				
				cell2.addElement(table3);
				
				table2.addCell(cell2);
				
				cell.addElement(table2);
				table.addCell(cell);
				
				//FOOTER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				
				table2 = new PdfPTable(2);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidths(TABLE_FOOTER_WIDTHS);
				table2.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell(new Paragraph("D??NH CHO NG??N H??NG (For Bank's Use only)", font9bu));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setFixedHeight(0.5f * CONVERT);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("M?? VAT", font9bold));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setFixedHeight(0.5f * CONVERT);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph(" ", font9));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setFixedHeight(0.5f * CONVERT);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				//cell2.setFixedHeight(0.5f * CONVERT);
				
				table3 = new PdfPTable(3);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {TABLE_FOOTER_WIDTHS[1]/3, TABLE_FOOTER_WIDTHS[1]/3, TABLE_FOOTER_WIDTHS[1]/3});
				table3.setWidthPercentage(100.0f);
				
				cell3 = new PdfPCell(new Paragraph("Thanh to??n vi??n", font9bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Ki???m so??t", font9bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Gi??m ?????c", font9bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);

				cell2.addElement(table3);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph(" ", font9));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setColspan(2);
				cell2.setFixedHeight(1.2f * CONVERT);
				table2.addCell(cell2);

				cell.addElement(table2);
				table.addCell(cell);
				
				document.add(table);
				//END VIETCOMBANK
			} 
			else if(traNganHangMa.equals("ACB")) 
			{
				//Logo ACB
		        Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/images/acb.png");
				hinhanh.scalePercent(75);
				hinhanh.setAbsolutePosition(1.0f * CONVERT, document.getPageSize().getHeight() - 2.0f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				//HEADER + CONTENT
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				
				PdfPTable table2 = new PdfPTable(TABLE_ACB_WIDTHS.length);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidths(TABLE_ACB_WIDTHS);
				table2.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI", font14bold)); 
				p.add(new Chunk("/PAYMENT ORDER", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng??y", font12bold)); 
				p.add(new Chunk("/Date", font10italic));
				p.add(new Chunk("  " + ngayghinhan, font10));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????N V??? TR??? TI???N", font12bold)); 
				p.add(new Chunk("/APPLICANT", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthTop(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??n t??i kho???n", font12bold)); 
				p.add(new Chunk("/Acct's Name", font10italic));
				p.add(new Chunk("     " + traTen, font12bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n", font12bold)); 
				p.add(new Chunk("/Acct No.", font10italic));
				p.add(new Chunk("  " + traStk, font12bold));
				p.add(new Chunk("       T???i ng??n h??ng ?? Ch??u _ chi nh??nh", font12bold)); 
				p.add(new Chunk("/At ACB _ Branch", font10italic)); 
				//p.add(new Chunk(traChiNhanh, font12bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????N V??? TH??? H?????NG", font12bold)); 
				p.add(new Chunk("/BENEFICIARY", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthTop(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????n v??? th??? h?????ng", font12bold)); 
				p.add(new Chunk("/Beneficiary", font10italic));
				p.add(new Chunk("     " + nhanTen, font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n", font12bold)); 
				p.add(new Chunk("/Acct No.", font10italic));
				p.add(new Chunk("     " + nhanStk, font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				cell2.setPaddingLeft(0.5f * CONVERT);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("CMND/H??? chi???u", font12bold)); 
				p.add(new Chunk("/ID CARD/PP No.", font10italic));
				p.add(new Chunk("            ", font10bold));
				p.add(new Chunk("Ng??y c???p", font10bold));
				p.add(new Chunk("/Date", font10italic));
				p.add(new Chunk("            ", font10bold));
				p.add(new Chunk("N??i c???p", font10bold));
				p.add(new Chunk("/Place", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				cell2.setPaddingLeft(0.5f * CONVERT);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???i Ng??n h??ng", font12bold)); 
				p.add(new Chunk("/Beneficiary's Bank ", font10italic));
				p.add(new Chunk(nhanNganHang, font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???nh, TP", font12bold)); 
				p.add(new Chunk("/Province, City ", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthBottom(1);
				cell2.setColspan(TABLE_ACB_WIDTHS.length-1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ti???n b???ng ch???", font12bold)); 
				p.add(new Chunk("/Amount in words", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("B???ng s???:", font12bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(sotientt, font12bold));
				p.setAlignment(Element.ALIGN_RIGHT);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setPaddingRight(0.2f * CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);
				cell2.setRowspan(2);
				cell2.setBackgroundColor(new BaseColor(219, 229, 241));
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("VN??", font9bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorder(0);
				cell2.addElement(p);
				cell2.setRowspan(2);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(tienbangchu, font12));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("In figures", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorder(0);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("N???i dung", font12bold)); 
				p.add(new Chunk("/Details     ", font10italic));
				p.add(new Chunk(noidungtt, font12));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell.addElement(table2);
				table.addCell(cell);
				
				//FOOTER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				
				table2 = new PdfPTable(3);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				float columnSize = 18.0f / 3 * CONVERT;
				table2.setWidths(new float[] {columnSize, columnSize, columnSize});
				table2.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????N V??? TR??? TI???N", font12bold)); 
				p.add(new Chunk("/Application", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthRight(1);
				cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("NG??N H??NG ?? CH??U", font12bold)); 
				p.add(new Chunk("/ACB", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthRight(1);
				cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("NG??N H??NG B", font12bold)); 
				p.add(new Chunk("/B Bank", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				//Column1 
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthRight(1);
				
				PdfPTable table3 = new PdfPTable(2);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {columnSize/2, columnSize/2});
				table3.setWidthPercentage(100.0f);
				
				cell3 = new PdfPCell(new Paragraph("K??? to??n tr?????ng", font12bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Ch??? t??i kho???n", font12bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Chief Accountant", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Account Holder", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell2.addElement(table3);
				table2.addCell(cell2);
				
				//Column2
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthRight(1);
				
				table3 = new PdfPTable(2);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {columnSize/2, columnSize/2});
				table3.setWidthPercentage(100.0f);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ghi s??? ng??y", font12bold)); 
				p.add(new Chunk("/Post date.............", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Giao d???ch vi??n", font12bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Ki???m so??t vi??n", font12bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Teller", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Suppervisor", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell2.addElement(table3);
				table2.addCell(cell2);
				

				//Column3
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorder(0);
				
				table3 = new PdfPTable(2);	
				table3.setHorizontalAlignment(Element.ALIGN_CENTER);
				table3.setWidths(new float[] {columnSize/2, columnSize/2});
				table3.setWidthPercentage(100.0f);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ghi s??? ng??y", font12bold)); 
				p.add(new Chunk("/Post date.............", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Giao d???ch vi??n", font12bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Tr?????ng ????n v???", font12bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Teller", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Manager", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setFixedHeight(2.0f * CONVERT);
				table3.addCell(cell3);
				
				cell2.addElement(table3);
				table2.addCell(cell2);
				
				cell.addElement(table2);
				
				
				table.addCell(cell);
				
				document.add(table);
				
			}
			else if (traNganHangMa.equals("EIB")){
				//Logo EIB EXIMBANK
		        Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/images/eximbank.png");
				hinhanh.scalePercent(80);
				hinhanh.setAbsolutePosition(1.0f * CONVERT, document.getPageSize().getHeight() - 2.0f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				//HEADER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				
				PdfPTable table1 = new PdfPTable(TABLE_EIB_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_EIB_WIDTHS);
				table1.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI", font14bold)); 
				p.setAlignment(Element.ALIGN_RIGHT);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.3f * CONVERT);
				cell2.setPaddingRight(3.5f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S???: ..............	\t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t "+obj.getId(), font11italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.5f * CONVERT);
				cell2.setColspan(2);
				cell2.setBorder(0);
				cell2.addElement(p);
				table1.addCell(cell2);		
				
				//C???T 1
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				
				//B???NG L???NG
				PdfPTable table2 = new PdfPTable(2);	
				table2.setWidthPercentage(100);
				float[] withs2 = {40f, 100f};
				table2.setWidths(withs2);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("CHUY???N KHO???N, CHUY???N TI???N TH??, ??I???N \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t " , font11bold)); 		
				p.add(new Chunk("L???p ng??y: .............." , font11)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				table2.addCell(cell3);
								
				
				cell3 = new PdfPCell();
				p = new Paragraph( "T??n ????n v??? tr??? ti???n: " ,font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( traTen , font11); // "C??NG TY CP ????? H???P H??? LONG " 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( "S??? t??i kho???n: " , font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();				
				p = new Paragraph( traStk , font11); //"160314851021314 " 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph("T???i Ng??n h??ng: "+ traNganHang,font11);				
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				cell3.setPaddingBottom(0.1f*CONVERT);
				cell3.setBorderWidthBottom(1);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( "T??n ????n v??? nh???n ti???n: " , font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( nhanTen , font11); //"DN T?? Nh??n - XN Qu???c Anh "
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);				
				
				cell3 = new PdfPCell();
				p = new Paragraph( "S??? t??i kho???n: " , font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph(nhanStk , font11); //"12410000270665"
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( "T???i ng??n h??ng: " , font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang = nhanNganHang +" - "+ nhanChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph( nhanNganHang , font11); //"NH - BIDV Chi Nh??nh Ho??n Ki???m H?? N???i"
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( "" , font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setFixedHeight(0.1f*CONVERT);
				cell3.setColspan(2);
				cell3.setBorder(0);
				cell3.setBorderWidthBottom(1);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph( "S??? ti???n b???ng ch???: " ,font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				//N???U D??I QU?? TH?? C???T CHU???I XU???NG
				String chuoi_= "";
				String chuoi1= tienbangchu;
				String chuoi2= "";
				int vitri= 0;
				int dodaichuoi = tienbangchu.length();
				if(dodaichuoi >= 100)
				{
					chuoi_ = tienbangchu.substring(0, 70);
					vitri = chuoi_.lastIndexOf(" ");
					chuoi1 = chuoi_.substring(0, vitri);
					chuoi2 = tienbangchu.substring(vitri + 1,dodaichuoi );
				}
				
				cell3 = new PdfPCell();
				p = new Paragraph( chuoi1+"./" , font11italic); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				if(chuoi2.trim().length()>0){
					cell3 = new PdfPCell();
					p = new Paragraph( chuoi2 , font10); 
					p.setAlignment(Element.ALIGN_LEFT);
					cell3.addElement(p);
					cell3.setColspan(2);
					cell3.setBorder(0);
					table2.addCell(cell3);
				}
				
				cell3 = new PdfPCell();
				p = new Paragraph( "N???i dung thanh to??n: " , font11); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				//N???U D??I QU?? TH?? C???T CHU???I XU???NG
				chuoi_= "";
				chuoi1= noidungtt;
				chuoi2= "";
				vitri= 0;
				dodaichuoi = noidungtt.length();
				if(dodaichuoi >= 100)
				{
					chuoi_ = noidungtt.substring(0, 70);
					vitri = chuoi_.lastIndexOf(" ");
					chuoi1 = chuoi_.substring(0, vitri);
					chuoi2 = noidungtt.substring(vitri + 1,dodaichuoi );
				}
				
				
				cell3 = new PdfPCell();
				p = new Paragraph( chuoi1 , font11); //"TT ti???n h??ng H?? 0000665 theo ??N 05/09/2014"
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				if(chuoi2.trim().length()>0){
					cell3 = new PdfPCell();
					p = new Paragraph( chuoi2 , font11); 
					p.setAlignment(Element.ALIGN_LEFT);
					cell3.addElement(p);
					//cell3.setFixedHeight(0.1f*CONVERT);
					cell3.setColspan(2);
					cell3.setBorder(0);
					//cell3.setBorderWidth(1);
					table2.addCell(cell3);
				}
				else{
					cell3 = new PdfPCell();
					p = new Paragraph( "" , font11); 
					p.setAlignment(Element.ALIGN_LEFT);
					cell3.addElement(p);
					cell3.setFixedHeight(0.5f*CONVERT);
					cell3.setColspan(2);
					cell3.setBorder(0);
					//cell3.setBorderWidth(1);
					table2.addCell(cell3);
				}
				
				cell2.addElement(table2);
								
				table1.addCell(cell2);
				
				//C???T 2
				cell2 = new PdfPCell();
				cell2.setBorder(0);				
				table1.addCell(cell2);	
				
				//C???T 3
				cell2 = new PdfPCell();				
				cell2.setBorder(0);
				
				PdfPTable table3 = new PdfPTable(1);	
				table3.setWidthPercentage(100);
				float[] withs1 = {60f};
				table3.setWidths(withs1);
				
				cell4 = new PdfPCell();
				p = new Paragraph( "PH???N DO NH GHI" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( "T??I KHO???N N???" , font11); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(1.7f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthTop(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( "T??I KHO???N C??" , font11); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setFixedHeight(1.7f*CONVERT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.addElement(p);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthBottom(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				cell4.setFixedHeight(0.2f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( "S??? TI???N B???NG S???" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.addElement(p);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthBottom(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( DinhDangCANFOCO(sotientt)+" VND" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.addElement(p);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthBottom(1);
				cell4.setBorderWidthTop(0);
				table3.addCell(cell4);
								
				cell2.addElement(table3);
				
				table1.addCell(cell2);	
											
				//FOOTER
				cell2 = new PdfPCell();
				cell2.setColspan(4);
				cell2.setBorder(0);	
				
				PdfPTable table4 = new PdfPTable(3);	
				table4.setWidthPercentage(100);
				float[] withs4 = {60f, 60f, 60f};
				table4.setWidths(withs4);
				
				cell5 = new PdfPCell();
				p = new Paragraph( "????n v??? tr??? ti???n" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				
				cell5 = new PdfPCell();
				p = new Paragraph( "Ng??n h??ng A" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( "Ng??n h??ng B" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( "K??? to??n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t Ch??? t??i kho???n" , font11); 			
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( "Ghi s??? ng??y ......................" , font11); 			
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( "Ghi s??? ng??y ......................" , font11); 			
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorder(0);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
								
				cell5 = new PdfPCell();
				p = new Paragraph( "K??? to??n  \t \t \t \t \t \t \t \t \t \t  Tr?????ng ph??ng k??? to??n" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorder(0);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( "K??? to??n  \t \t \t \t \t \t \t \t \t \t  Tr?????ng ph??ng k??? to??n" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorder(0);
				table4.addCell(cell5);
				
				
				cell2.addElement(table4);
				
				table1.addCell(cell2);				
				cell.addElement(table1);
				table.addCell(cell);
				
				document.add(table);				
				
			}
			else if(traNganHangMa.equals("AGB")){				
				PdfContentByte cb = writer.getDirectContent();
				cb.beginText();
		        cb.setFontAndSize(bf, 6);
		        cb.setTextMatrix(0, 1, -1, 0, 0.9f*CONVERT, 4.0f * CONVERT);
		        cb.showText("M???u DP005 ban h??nh k??m theo Quy???t ?????nh 1371/Q??-NHNo-TCKT ng??y 16/11/2011");
		        cb.endText();
		        		        
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);				
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				
				//HEADER
				
				PdfPTable table1 = new PdfPTable(TABLE_AGB_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_AGB_WIDTHS);
				table1.setWidthPercentage(100.0f);
				
				//C???T 1
				PdfPCell cell1 = new PdfPCell();
				
				cell1.setHorizontalAlignment(Element.ALIGN_TOP);
				cell1.setVerticalAlignment(Element.ALIGN_TOP);
				cell1.setBorder(0);
				
				PdfPTable tablelogo = new PdfPTable(1);	
				tablelogo.setHorizontalAlignment(Element.ALIGN_TOP);
				tablelogo.setWidthPercentage(100);
				float[] withslogo = {100f};
				tablelogo.setWidths(withslogo);
				
				Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/images/agribank.jpg");
				hinhanh.scalePercent(70);
//				hinhanh.setAlignment(Element.ALIGN_CENTER);
				
				PdfPCell cellslogo = new PdfPCell(hinhanh);
//				cellslogo.setPadding(5);
				cellslogo.setBorder(0);
				tablelogo.addCell(cellslogo);
				
				cellslogo = new PdfPCell();
				cellslogo.setBorder(0);	
//				cellslogo.setFixedHeight(0.2f*CONVERT);
				cellslogo.setFixedHeight(0.05f*CONVERT);
				tablelogo.addCell(cellslogo);
				
				cellslogo = new PdfPCell();
				cellslogo.setHorizontalAlignment(Element.ALIGN_TOP);
				cellslogo.setVerticalAlignment(Element.ALIGN_TOP);
				Paragraph p = new Paragraph();
				p = new Paragraph("..............................................", font8);
				p.setAlignment(Element.ALIGN_LEFT);
//				cellslogo.setPaddingTop(-0.5f*CONVERT);
//				cellslogo.setPaddingTop(-0.1f*CONVERT);
				cellslogo.setBorder(0);
				cellslogo.addElement(p);				
				tablelogo.addCell(cellslogo);
				
				cell1.addElement(tablelogo);
				table1.addCell(cell1);				
				
				//C???T 2				
				cell1 = new PdfPCell();
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell1.setVerticalAlignment(Element.ALIGN_CENTER);
				cell1.setBorder(0);
				
				PdfPTable table3 = new PdfPTable(1);	
				table3.setHorizontalAlignment(Element.ALIGN_TOP);
				table3.setWidthPercentage(100);
				float[] withstable3 = {100f};
				table3.setWidths(withstable3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI\n", font15bold)); 
				p.add(new Chunk("Payment Order", font10bi)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				cell3.addElement(p);				
				table3.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph("Ng??y ......./....... /20.......", font8);
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setPaddingLeft(2.3f*CONVERT);
				cell3.setBorder(0);
				cell3.addElement(p);				
				table3.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph("Date", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setPaddingLeft(2.3f*CONVERT);
//				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				cell3.addElement(p);				
				table3.addCell(cell3);
				
				cell1.addElement(table3);
				
				table1.addCell(cell1);
				
				
				//C???T 3
				cell1 = new PdfPCell();
				cell1.setHorizontalAlignment(Element.ALIGN_BOTTOM);
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setBorder(0);
				
				PdfPTable table6 = new PdfPTable(1);	
				table6.setHorizontalAlignment(Element.ALIGN_MIDDLE);
				table6.setWidthPercentage(100);
				float[] withstable6 = {100f};
				table6.setWidths(withstable6);
				
				cell3 = new PdfPCell();
				p = new Paragraph("S???: ........................", font7);				
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setPaddingTop(-1.2f*CONVERT);
				cell3.addElement(p);				
				table6.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph("No", font6italic);				
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setPaddingTop(-0.8f*CONVERT);
				cell3.addElement(p);				
				table6.addCell(cell3);
				
				cell1.addElement(table6);
								
				table1.addCell(cell1); 
				
				//C???T 4
				cell1 = new PdfPCell();
				p = new Paragraph();
				cell1.setHorizontalAlignment(Element.ALIGN_TOP);
				cell1.setVerticalAlignment(Element.ALIGN_TOP);
				cell1.setBorder(0);
				
				//B???NG L???NG
				PdfPTable table2 = new PdfPTable(1);	
				table2.setHorizontalAlignment(Element.ALIGN_TOP);
				table2.setWidthPercentage(100);
				float[] withs2 = {100f};
				table2.setWidths(withs2);
				
				cell2 = new PdfPCell();
				p = new Paragraph("Li??n 1:   Ng??n h??ng", font8);
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_BOTTOM);
				cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell2.setBorder(0);
				cell2.addElement(p);				
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph("Original \t \t \t \t \t \t \t \t \t \t Bank", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_TOP);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setPaddingTop(-0.1f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);				
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph("S??? b??t to??n: .....................", font8);
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_BOTTOM);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setPaddingTop(-0.1f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);				
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph("Seq No", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_TOP);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setPaddingTop(-0.1f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);				
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph("Lo???i ti???n: .........................", font8);
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_BOTTOM);
				cell2.setVerticalAlignment(Element.ALIGN_BOTTOM);
				cell2.setPaddingTop(-0.1f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);				
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph("Currency", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_TOP);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setPaddingTop(-0.1f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);				
				table2.addCell(cell2);
				
				cell1.addElement(table2);
				
				table1.addCell(cell1);
				
				cell.addElement(table1);
				table.addCell(cell);
				document.add(table);
				
				PdfPTable tablecontents = new PdfPTable(2);	
				float[] withscontents = {500f,250f};
				tablecontents.setHorizontalAlignment(Element.ALIGN_LEFT);
				tablecontents.setWidths(withscontents);
				tablecontents.setWidthPercentage(100);		
				
				//C???T 1
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				cell.setBorderWidth(1);
				
				//B???NG L???NG C???T 1
				
				PdfPTable table5 = new PdfPTable(2);	
				float[] withstable5 = {400f,300f};
				table5.setHorizontalAlignment(Element.ALIGN_TOP);
				table5.setWidths(withstable5);
				table5.setWidthPercentage(100);	
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("????n v??? tr??? ti???n: "+ traTen, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setBorder(0);		
				cell5.setColspan(2);				
				table5.addCell(cell5);	
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Applicant", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);		
				cell5.setColspan(2);				
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("S??? t??i kho???n: "+ traStk, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("??i???n tho???i: "+traDienthoai, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Account No", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Tel", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh ;
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("T???i ng??n h??ng: "+traNganHang, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setColspan(2);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("At bank", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setColspan(2);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("????n v??? th??? h?????ng: "+nhanTen, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setColspan(2);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Beneficiary", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setColspan(2);
				cell5.setBorder(0);					
				table5.addCell(cell5);
								
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("CMT/H??? chi???u: ", font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Ng??y c???p: ", font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("ID/Passport No", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Date of issue", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("N??i c???p:", font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("??i???n tho???i:", font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Place of issue", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Tel", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("S??? t??i kho???n:"+nhanStk, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);
				cell5.setColspan(2);
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Account No", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);
				cell5.setColspan(2);
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang = nhanNganHang + " - " + nhanChiNhanh;
					
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("T???i ng??n h??ng: "+nhanNganHang, font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);
				cell5.setColspan(2);
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("At bank", font6italic);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);
				cell5.setColspan(2);
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell.addElement(table5);
				
				tablecontents.addCell(cell);
				
				//C???T 2
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				cell.setBorderWidth(1);		
				
				//B???NG L???NG C???T 2
				PdfPTable table7 = new PdfPTable(1);	
				float[] withstable7 = {500f};
				table7.setHorizontalAlignment(Element.ALIGN_LEFT);
				table7.setWidths(withstable7);
				table7.setWidthPercentage(100);	
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setBorderWidth(1);	
				
				//B???NG L???NG 
				PdfPTable table8 = new PdfPTable(1);	
				float[] withstable8 = {500f};
				table8.setHorizontalAlignment(Element.ALIGN_LEFT);
				table8.setWidths(withstable8);
				table8.setWidthPercentage(100);	
				
				PdfPCell cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);	
				p = new Paragraph();
				p.add(new Chunk("D??nh cho ng??n h??ng  ", font10));
				p.add(new Chunk("(For bank use only)", font7italic));				
				p.setAlignment(Element.ALIGN_LEFT);	
				cell8.setBackgroundColor(new BaseColor(142, 30, 32));
				cell8.addElement(p);	
				cell8.setBorder(0);	
				cell8.setPaddingTop(-0.1f*CONVERT);
				cell8.setFixedHeight(0.8f*CONVERT);
				table8.addCell(cell8);
								
				cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);	
				p = new Paragraph("T??i kho???n ghi n???:",font10);			
				p.setAlignment(Element.ALIGN_LEFT);	
				cell8.addElement(p);
				cell8.setBorder(0);	
				table8.addCell(cell8);
				
				cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);	
				p = new Paragraph("Debit account",font7italic);			
				p.setAlignment(Element.ALIGN_LEFT);	
				cell8.setPaddingTop(-0.1f*CONVERT);
				cell8.addElement(p);
				cell8.setBorder(0);	
				table8.addCell(cell8);
				
				cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);
				cell8.setBorderWidth(1);
				cell8.setFixedHeight(0.6f*CONVERT);
				table8.addCell(cell8);
				
				cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);	
				p = new Paragraph("T??i kho???n ghi c??:",font10);			
				p.setAlignment(Element.ALIGN_LEFT);	
				cell8.setPaddingTop(-0.1f*CONVERT);
				cell8.addElement(p);
				cell8.setBorder(0);	
				table8.addCell(cell8);
				
				cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);	
				p = new Paragraph("Creadit account",font7italic);			
				p.setAlignment(Element.ALIGN_LEFT);	
				cell8.setPaddingTop(-0.1f*CONVERT);
				cell8.addElement(p);
				cell8.setBorder(0);	
				table8.addCell(cell8);
				
				cell8 = new PdfPCell();
				cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell8.setVerticalAlignment(Element.ALIGN_TOP);
				cell8.setBorderWidth(1);
				cell8.setFixedHeight(0.6f*CONVERT);
				table8.addCell(cell8);
				
				
				cell5.addElement(table8);
				table7.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(0.1f*CONVERT);
				cell5.setBorderWidth(0);				
								
				table7.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				p = new Paragraph("S??? ti???n b???ng s??? ", font10);	
				cell5.addElement(p);	
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthRight(1);
				cell5.setBorderWidthTop(1);
								
				table7.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				p = new Paragraph("Amount in figures", font7italic);	
				cell5.addElement(p);
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthRight(1);
								
				table7.addCell(cell5);				
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				p = new Paragraph(sotientt+" VND", font13);	
				p.setAlignment(Element.ALIGN_CENTER);
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell5.setBackgroundColor(new BaseColor(252, 218, 213));
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setFixedHeight(1.1f*CONVERT);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthRight(1);
				cell5.setBorderWidthBottom(1);
								
				table7.addCell(cell5);		
				
				cell.addElement(table7);
				
				tablecontents.addCell(cell);
				
				document.add(tablecontents);
				
				PdfPTable tablefooter = new PdfPTable(1);	
				float[] withsfooter = {754f};
				tablefooter.setHorizontalAlignment(Element.ALIGN_LEFT);
				tablefooter.setWidths(withsfooter);
				tablefooter.setWidthPercentage(100);		
				
				//C???T 1
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph( "S??? ti???n b???ng ch???: " +tienbangchu+"./", font9); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthLeft(1);
				cell.setBorderWidthRight(1);
				cell.setBorderWidthTop(1);
				
				tablefooter.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph( "Amount in words " , font6italic); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(p);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				cell.setBorderWidthLeft(1);
				cell.setBorderWidthRight(1);
				
				tablefooter.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph( "N???i dung: "+noidungtt , font9); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthLeft(1);
				cell.setBorderWidthRight(1);
				
				tablefooter.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph( "Details " , font6italic); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(p);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setFixedHeight(0.6f*CONVERT);
				cell.setBorder(0);
				cell.setBorderWidthLeft(1);
				cell.setBorderWidthRight(1);
				cell.setBorderWidthBottom(1);
				
				tablefooter.addCell(cell);
				
				document.add(tablefooter);
				
				PdfPTable tablesign = new PdfPTable(3);	
				float[] widthsign = {300f, 300f, 300f};
				tablesign.setHorizontalAlignment(Element.ALIGN_LEFT);
				tablesign.setWidths(widthsign);
				tablesign.setWidthPercentage(100);	
								
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "????n v??? tr??? ti???n " , font9));
				p.add(new Chunk("(Applicant)", font6italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph();
				p.add(new Chunk( "Ng??n h??ng A " , font9));
				p.add(new Chunk("(Bank of Applicant)", font6italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph();
				p.add(new Chunk( "Ng??n h??ng B " , font9));
				p.add(new Chunk("(Bank of Beneficiary)", font6italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph();
				p.add(new Chunk( "Ghi s??? ng??y " , font7));
				p.add(new Chunk("(Settied date) ....../...../........", font6italic));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell.addElement(p);
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph();
				p.add(new Chunk( "Ghi s??? ng??y " , font7));
				p.add(new Chunk("(Settied date) ....../...../........", font6italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph();
				p.add(new Chunk( "K??? to??n tr?????ng \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t Ch??? t??i kho???n" , font7));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell.addElement(p);
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph();
				p.add(new Chunk("Giao d???ch vi??n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t Ki???m so??t" , font7));
				p.setAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph();
				p.add(new Chunk("Giao d???ch vi??n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t Ki???m so??t" , font7));
				p.setAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph();
				p.add(new Chunk( "Chief accountant \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t Account holder " , font6italic));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell.addElement(p);
				cell.setPaddingTop(-0.3f*CONVERT);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph();
				p.add(new Chunk( "Teller \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t        Supervisor " , font6italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.3f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph();
				p.add(new Chunk( "Teller \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t        Supervisor " , font6italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.3f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				
				tablesign.addCell(cell);
				
				document.add(tablesign);
				
				
								
			}
			else if (traNganHangMa.equals("IVB")){ //INDOVINA BANK
								
				Paragraph hd = new Paragraph();
				
				PdfPTable tableheader = new PdfPTable(1);	
				tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
				tableheader.setWidthPercentage(100);
				
				cell = new PdfPCell();
				hd = new Paragraph("TO: INDOVINA BANK LTD. HAI PHONG" , font11bold);
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.addElement(hd);
				tableheader.addCell(cell);
				
				cell = new PdfPCell();
				hd = new Paragraph("K??nh g???i: Ng??n h??ng Indovina H???i Ph??ng" ,font11italic);
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				tableheader.addCell(cell);
				
				
				cell = new PdfPCell();
				hd = new Paragraph("TRANSACTIONS APPLYCATION" , font13bold);
				cell.addElement(hd);
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				tableheader.addCell(cell);
				
				
				cell = new PdfPCell();
				hd = new Paragraph("Y??u c???u chi tr???" , font11italic);
				cell.addElement(hd);
				hd.setAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);	
				cell.setBorder(0);				
				
				tableheader.addCell(cell);
				document.add(tableheader);
				
				PdfPTable table1 =new PdfPTable(2);
				table1.setWidthPercentage(100);
				float[] withs1 = {100f,200f};
				table1.setWidths(withs1);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("Name of applicant \n", font11bold));
				hd.add(new Chunk("T??n C?? nh??n/c??ng ty y??u c???u", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setBorder(0);
				
				table1.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( traTen , font11bold);
				hd.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);				
				table1.addCell(cell);	
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("Passport No \n", font11bold));
				hd.add(new Chunk("S??? CMND/H??? chi???u", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setBorder(0);
				
				table1.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( "" , font11italic);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				table1.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("Address in Vietnam \n", font11bold));
				hd.add(new Chunk("?????a ch??? t???i Vi???t Nam", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setBorder(0);
				
				table1.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( "S??? 71 L?? Lai, Ng?? Quy???n, H???i Ph??ng" , font11bold);//"S??? 71 L?? Lai, Ng?? Quy???n, H???i Ph??ng"
				hd.setAlignment(Element.ALIGN_LEFT);		
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				table1.addCell(cell);	
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("Account with IVB \n", font11bold));
				hd.add(new Chunk("T??i kho???n t???i IVB", font11italic));				
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setBorder(0);
				
				table1.addCell(cell);
				
				//Account with IVB
				cell= new PdfPCell();
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				PdfPTable table11 =new PdfPTable(1);
				table11 = new PdfPTable(1);	
				table11.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				float[] withstable11 = { 10.0f*CONVERT };
				table11.setWidths(withstable11);				
				table11.setWidthPercentage(80);
				
				cell3 = new PdfPCell();
				hd = new Paragraph();
				hd = new Paragraph( traStk , font11bi);		//"3001024-002"		
				hd.setAlignment(Element.ALIGN_CENTER);
				cell3.addElement(hd);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_CENTER);	
				cell3.setPaddingBottom(0.1f*CONVERT);
				cell3.setBorderWidthTop(1);
				cell3.setBorderWidthRight(1);
				cell3.setBorderWidthBottom(1);
				cell3.setBorderWidthLeft(1);
				
				table11.addCell(cell3);
				
				cell.addElement(table11);
				
				table1.addCell(cell);	
				
				document.add(table1);
				
				PdfPTable table2 = new PdfPTable(1);
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidthPercentage(100);
				
				// (1) TRANSACTIONS APPLIED FOR: (Please mark - X - for appropriate item)
				cell = new PdfPCell();
				hd = new Paragraph();
				hd.add(new Chunk("1) TRANSACTIONS APPLIED FOR: (Please mark - X - for appropriate item) \n", font11bold));
				hd.add(new Chunk("Nghi???p v??? y??u c???u (????? ngh??? ????nh d???u -X- v??o m???c th??ch h???p) \n", font11italic));
				hd.add(new Chunk("( ) To receive Vietnamese Dong (In cash) \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Nh???n ti???n ????ng (ti???n m???t) \n", font11italic));
				hd.add(new Chunk("( ) To receive foreign currency (in cash) \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Nh???n ngo???i t??? (ti???n m???t) \n", font11italic));
				hd.add(new Chunk("Kind of currency \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Lo???i ngo???i t??? \n", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				table2.addCell(cell);
				
				document.add(table2);
				
				PdfPTable table3 =new PdfPTable(2);
				table3.setWidthPercentage(100);
				float[] withstable3 = {100f,200f};
				table3.setWidths(withstable3);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("( ) To Transfer to A/C No: \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Chuy???n v??o t??i kho???n s???", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setBorder(0);
				
				table3.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( nhanStk , font11bi);// "12410000270665"
				hd.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				table3.addCell(cell);	
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t With: \n", font11bold));
				hd.add(new Chunk("\t \t \t \t T???i", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table3.addCell(cell);
				
				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang =nhanNganHang + " - "+nhanChiNhanh;
				
				cell= new PdfPCell();	
				hd = new Paragraph( nhanNganHang , font11bi); //"NH - BIDV Chi Nh??nh Ho??n Ki???m H?? N???i"
				hd.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				table3.addCell(cell);	
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t In favour of: \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Cho ng?????i th??? h?????ng l??", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table3.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( nhanTen ,font11bi);//"DN T?? Nh??n - XN Qu???c Anh"
				hd.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table3.addCell(cell);
				document.add(table3);
				
				
				PdfPTable table4 =new PdfPTable(2);
				table4.setWidthPercentage(100);
				float[] withstable4 = {100f,200f};
				table4.setWidths(withstable4);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("( ) To Transfer abroad by \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Chuy???n ti???n ra n?????c ngo??i b???ng", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd = new Paragraph( "" , font11bi);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t - Bank draft \n", font11bold));
				hd.add(new Chunk("\t \t \t \t   H???i phi???u ng??n h??ng", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd = new Paragraph( "" , font11bi);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t - Mail \n", font11bold));
				hd.add(new Chunk("\t \t \t \t   Th??", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph( "" , font11italic);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
								
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t - Cable \n", font11bold));
				hd.add(new Chunk("\t \t \t \t   ??i???n", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd = new Paragraph( "" , font11bold);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
								
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t - With details: \n", font11bold));
				hd.add(new Chunk("\t \t \t \t   Chi ti???t", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd = new Paragraph( noidungtt , font11bi); //"TT ti???n h??ng H?? 0000665 theo ??N 05/09/2014"
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("( ) Other \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Y??u c???u kh??c", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd = new Paragraph( "" , font11bi);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table4.addCell(cell);
				
				document.add(table4);
				
				PdfPTable table5 = new PdfPTable(1);
				table5.setHorizontalAlignment(Element.ALIGN_CENTER);
				table5.setWidthPercentage(100);
				
				// (1) TRANSACTIONS APPLIED FOR: (Please mark - X - for appropriate item)
				cell = new PdfPCell();
				hd = new Paragraph();
				hd.add(new Chunk("2) MEANS OF PAYMENT TO IVB: (Please mark - X - for appropriate item) \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Ph????ng th???c thanh to??n cho IVB (????? ngh??? ????nh d???u -X- v??o m???c th??ch h???p) \n", font11italic));
				hd.add(new Chunk("( ) (In cash) \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t ( ) In checques \n", font11bold));
				hd.add(new Chunk("\t \t \t \t B???ng ti???n m???t  \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  B???ng s??c \n", font11italic));
				hd.add(new Chunk("( ) By debiting applicant-s account with IVB \n", font11bold));
				hd.add(new Chunk("\t \t \t \t Ghi n??? t??i kho???n t???i IVB \n", font11italic));				
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				cell.setPaddingTop(-0.1f*CONVERT);
				table5.addCell(cell);
				
				document.add(table5);
				
				PdfPTable table6 =new PdfPTable(2);
				table6.setWidthPercentage(100);
				float[] withs6 = {100f,200f};
				table6.setWidths(withs6);
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("For the amount of \n", font11bold));
				hd.add(new Chunk("\t \t \t \t S??? ti???n", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table6.addCell(cell);
				
				//Account with IVB
				cell= new PdfPCell();
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setBorder(0);
				
				PdfPTable table61 =new PdfPTable(1);
				table61 = new PdfPTable(1);	
				table61.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				float[] withstable61 = { 10.0f*CONVERT };
				table61.setWidths(withstable61);				
				table61.setWidthPercentage(80);
				
				cell3 = new PdfPCell();
				hd = new Paragraph();
				hd = new Paragraph( sotientt +" VND" , font11bi);				
				hd.setAlignment(Element.ALIGN_CENTER);
				cell3.addElement(hd);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_CENTER);
				cell3.setPaddingBottom(0.2f*CONVERT);
				cell3.setBorderWidthTop(1);
				cell3.setBorderWidthRight(1);
				cell3.setBorderWidthBottom(1);
				cell3.setBorderWidthLeft(1);
				
				table61.addCell(cell3);
				
				cell.addElement(table61);
				
				table6.addCell(cell);	
				
				cell= new PdfPCell();	
				hd = new Paragraph();
				hd.add(new Chunk("\t \t \t \t In word: \n", font11bold));
				hd.add(new Chunk("\t \t \t \t B???ng ch???", font11italic));
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table6.addCell(cell);
				
				cell= new PdfPCell();	
				hd = new Paragraph(tienbangchu+"./" , font11bi);
				hd.setAlignment(Element.ALIGN_LEFT);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				cell.setBorder(0);
				
				table6.addCell(cell);
				
				document.add(table6);
				
				
				PdfPTable table7 = new PdfPTable(1);	
				table7.setHorizontalAlignment(Element.ALIGN_CENTER);
				table7.setWidthPercentage(100);
				
				cell = new PdfPCell();
				hd = new Paragraph("H???i Ph??ng, ng??y   /   /" , font11);
				hd.setAlignment(Element.ALIGN_RIGHT);
				cell.setBorder(0);
				cell.addElement(hd);
				cell.setPaddingTop(-0.1f*CONVERT);
				table7.addCell(cell);
				
				document.add(table7);
			}
			document.close(); 
		}
		catch(Exception e)
		{
			System.out.println("Exception In Phieu Chi: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	private boolean CreateUyNhiemChiExcel(OutputStream out, IErpThanhtoanhoadon obj)  throws Exception
	{
		try
		{
			NumberFormat formatter = new DecimalFormat("#,###,###");
			NumberFormat formatter2 = new DecimalFormat("###########"); 
			NumberFormat formatter3 = new DecimalFormat("#,###,##0.000");
			
			String soTk = "";
			String sotientt = "";
			long sotien = 0;
			int loaitt = 0;
			String tienbangchu = "";
			String noidungtt = "";
			String ngayghinhan = "";
			
			String traTen = "", traStk = "", traNganHang = "", traNganHangMa = "",  traDiaChi = "", traChiNhanh = "";
			String nhanTen = "", nhanStk = "", nhanNganHang = "", nhanDiaChi = "", nhanChiNhanh = "";

			dbutils db = new dbutils();
			String query =  " select tthd.SOTAIKHOAN as SoTaiKhoan, isnull(nh.TEN, '') as traNganHang, isnull(nh.MA, '') as traNganHangMa, isnull(cn.TEN, '') as traChiNhanh, isnull((select ten from erp_congty where pk_seq = 100005), '') as congty, " +
							"	isnull(tthd.LOAITHANHTOAN,0) as LOAITHANHTOAN, " +
							" 	isnull(ncc.TEN,'') as nccTen, isnull(ncc.DIACHI,'') as nccDiaChi, ncc.sotknganhang as nccStk, isnull(nccnh.TEN, '') AS nccNganHang, isnull(ncccn.TEN, '') AS nccChiNhanh, " +
							" 	isnull(nv.TEN,'') as nvTen, isnull(nv.DIACHI,'') as nvDiaChi, nv.sotaikhoan as nvStk, isnull(nvnh.TEN, '') AS nvNganHang, isnull(nvcn.TEN, '') AS nvChiNhanh, " +
							" 	tthd.SOTIENTT as SOTIENTT, tthd.NOIDUNGTT as NOIDUNGTT, " +
							" 	tthd.NGAYGHINHAN as NGAYGHINHAN " +
							" from ERP_THANHTOANHOADON tthd " +
							" left join ERP_NGANHANG nh on tthd.nganhang_fk = nh.pk_seq  " +
							" left join ERP_CHINHANH cn on tthd.chinhanh_fk = cn.pk_seq  " +
							" left join ERP_NHACUNGCAP ncc on tthd.ncc_fk = ncc.pk_seq " +
							" left join ERP_NGANHANG nccnh on ncc.nganhang_fk = nccnh.pk_seq  " +
							" left join ERP_CHINHANH ncccn on ncc.chinhanh_fk = ncccn.pk_seq  " +
							" left join ERP_NHANVIEN nv on tthd.NHANVIEN_FK = nv.PK_SEQ" +
							" left join ERP_NGANHANG nvnh on nv.nganhang_fk = nvnh.pk_seq  " +
							" left join ERP_CHINHANH nvcn on nv.chinhanh_fk = nvcn.pk_seq  " +
							" where tthd.pk_seq = '"+obj.getId()+"' ";
			System.out.println("[ErpTTHoaDonPdfSvl.CreateUyNhiemChi] query 1 = " + query);
			
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				try 
				{
					while(rs.next())
					{
						ngayghinhan = rs.getString("NGAYGHINHAN");
						loaitt = rs.getInt("LOAITHANHTOAN");
						noidungtt = rs.getString("NOIDUNGTT");
						sotientt = formatter.format(rs.getDouble("SOTIENTT"));
						
						traTen = rs.getString("congty");
						traStk = rs.getString("SoTaiKhoan");
						traNganHang = rs.getString("traNganHang");
						traNganHangMa = rs.getString("traNganHangMa");
						traChiNhanh = rs.getString("traChiNhanh");
						//traDiaChi = rs.getString("traDiaChi");
						
						if(loaitt == 1) {
							nhanTen = rs.getString("nvTen");
							nhanDiaChi = rs.getString("nvDiaChi");
							nhanStk = rs.getString("nvStk");
							nhanNganHang = rs.getString("nvNganHang");
						} else {
							nhanTen = rs.getString("nccTen");
							nhanDiaChi = rs.getString("nccTen");
							nhanStk = rs.getString("nccStk");
							nhanNganHang = rs.getString("nccNganHang");
						}
						try {
							sotien = Long.parseLong(formatter2.format(rs.getDouble("SOTIENTT")));
							tienbangchu = DocTien.docTien(sotien);
						} catch (Exception e) {
							
						}
					}
				} 
				catch (SQLException e) 
				{
					System.out.println("[ErpTTHoaDonPdfSvl.CreateUyNhiemChi] Exception: " + e.getMessage());
				}
			}
			
			db.shutDown();
			
			FileInputStream fstream;

			
			Cell cell = null;
			
			if(traNganHangMa.trim().equals("VIETCOMBANK")) 
			{
				fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\UNC_VCB.xlsm");
				Workbook workbook = new Workbook();
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				workbook.open(fstream);
				
				Worksheets worksheets = workbook.getWorksheets();
			    Worksheet worksheet = worksheets.getSheet(0);
			    Cells cells = worksheet.getCells();
				
				//C???t b??n tr??i
			    cell = cells.getCell("C5"); cell.setValue(traChiNhanh); //T??n chi nh??nh ng??n h??ng b??n tr???
				cell = cells.getCell("F11"); cell.setValue(traStk); //S??? t??i kho???n b??n tr???
				cell = cells.getCell("G13"); cell.setValue(traTen); //T??n t??i kho???n tr???
				cell = cells.getCell("G17"); cell.setValue(traNganHang); //T??n ng??n h??ng tr???
				
				cell = cells.getCell("G22"); cell.setValue(nhanStk); //S??? t??i kho???n nh???n
				cell = cells.getCell("G24"); cell.setValue(nhanTen); //T??n t??i kho???n nh???n
				cell = cells.getCell("G28"); cell.setValue(nhanNganHang); //Ng??n h??ng t??i kho???n nh???n
				
				//C???t b??n ph???i
				cell = cells.getCell("O5"); cell.setValue(getDate(ngayghinhan));
				cell = cells.getCell("S11"); cell.setValue(sotientt);
				cell = cells.getCell("N14"); cell.setValue(tienbangchu);
				cell = cells.getCell("N20"); cell.setValue(noidungtt);
				
				workbook.save(out);
				fstream.close();
			}
			else if(traNganHangMa.trim().equals("ACB")) 
			{
				fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\UNC_ACB.xlsm");
				Workbook workbook = new Workbook();
				workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
				workbook.open(fstream);
				
				Worksheets worksheets = workbook.getWorksheets();
			    Worksheet worksheet = worksheets.getSheet(0);
			    Cells cells = worksheet.getCells();
				
				//C???t b??n tr??i
				cell = cells.getCell("F7"); cell.setValue(traStk);
				cell = cells.getCell("H6"); cell.setValue(traTen);
				cell = cells.getCell("K7"); cell.setValue(traNganHang + " - " + traChiNhanh);
				cell = cells.getCell("G12"); cell.setValue(nhanStk);
				cell = cells.getCell("I10"); cell.setValue(nhanTen);
				cell = cells.getCell("I16"); cell.setValue(nhanNganHang);
				
				//C???t b??n ph???i
				cell = cells.getCell("L3"); cell.setValue(getDate(ngayghinhan));
				cell = cells.getCell("W18"); cell.setValue(sotientt);
				cell = cells.getCell("A19"); cell.setValue(tienbangchu);
				cell = cells.getCell("E21"); cell.setValue(noidungtt);
				
				workbook.save(out);
				fstream.close();
			} else {
				return false;
			}
			
		} catch (Exception e) {
			return false;
		}
		return true;
		
			
	}

	private void CreatePhieuChi(Document document, ServletOutputStream outstream, IErpThanhtoanhoadon tthdBean) 
	{
		try
		{			

			NumberFormat formatter = new DecimalFormat("#,###,###"); 
//			NumberFormat formatter2 = new DecimalFormat("#,###,##0.00000"); 
//			NumberFormat formatter3 = new DecimalFormat("#,###,##0.000");
			
			IErpCauHinhInHoaDon config = new ErpCauHinhInHoaDon();
			config.initWithName("PHIEUTHU");
			
			IErpCauHinhInHoaDon configp = new ErpCauHinhInHoaDon();
			configp.initWithName("PHIEUTHUP");
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//			Font font = new Font(bf, 13, Font.BOLD);
//			Font font2 = new Font(bf, 8, Font.BOLD);
//			Font font9bold = new Font(bf, 9, Font.BOLD);
			Font font10bold = new Font(bf, 10, Font.BOLD);
//			Font font11bold = new Font(bf, 11, Font.BOLD);
			Font font12bold = new Font(bf, 12, Font.BOLD);
//			Font font13bold = new Font(bf, 13, Font.BOLD);
//			Font font14bold = new Font(bf, 14, Font.BOLD);
//			Font font9 = new Font(bf, 9, Font.NORMAL);
//			Font font10 = new Font(bf, 10, Font.NORMAL);
//			Font font11 = new Font(bf, 11, Font.NORMAL);
//			Font font12 = new Font(bf, 12, Font.NORMAL);
//			Font font13 = new Font(bf, 13, Font.NORMAL);
//			Font font14 = new Font(bf, 14, Font.NORMAL);
			
			float CONVERT = 28.346457f; // = 1cm
			float[] TABLE_WIDTHS = {18.0f * CONVERT};

			float PADDING_LEFT_3 = configp.getMarginLeft() * CONVERT;
			float PADDING_LEFT_0 = configp.getMarginRight() * CONVERT;
			float PADDING_LEFT_1 = configp.getMarginTop() * CONVERT;
			float PADDING_LEFT_2 = configp.getMarginBottom() * CONVERT;
			//float PADDING_LEFT_4 = configp.getPaddingLeft() * CONVERT;
			
			/*float PADDING_LEFT_0 = 10.0f * CONVERT;
			float PADDING_LEFT_1 = 7.0f * CONVERT;
			float PADDING_LEFT_2 = 1.0f * CONVERT;
			float PADDING_LEFT_3 = 13.2f * CONVERT;*/
			
			//????? r???ng m???i d??ng
			float row1w = config.getPaddingLeft() * CONVERT; //0.5f*CONVERT pk_seq
			float row2w = config.getPaddingRight() * CONVERT; //0.75f*CONVERT r???ng
			float row3w = config.getPaddingTop() * CONVERT; //0.75f*CONVERT ng??y ghi s???
			float row4w = config.getPaddingBottom() * CONVERT; //1.2f*CONVERT ng?????i n???p ti???n
			float row5w = config.getNoColumn() * CONVERT; //0.9f*CONVERT ?????a ch???
			float row6w = config.getUnitColumn() * CONVERT; //0.9f*CONVERT l?? do thu
			float row7w = config.getQuantityColumn() * CONVERT; //0.9f*CONVERT ti???n
			float row8w = config.getUniPriceColumn() * CONVERT; //0.9f*CONVERT vi???t b???ng ch???
			//float row9w = 0.9f * CONVERT; //0.9f*CONVERT ghi ch??
			
			int BORDER_WIDTH = 0;
	        	        
	        PdfPTable table = new PdfPTable(1);	
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setWidths(TABLE_WIDTHS);
			table.setWidthPercentage(100.0f);
			
			PdfPCell cell;
			cell = new PdfPCell(new Paragraph(tthdBean.getId(), font10bold)); //PK_SEQ
			cell.setPaddingLeft(PADDING_LEFT_3);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row1w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(" ", font10bold)); //R???ng
			cell.setPaddingLeft(PADDING_LEFT_3);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row2w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(tthdBean.getNgayghinhan(), font12bold)); //Ng??y ghi s???
			cell.setPaddingLeft(PADDING_LEFT_0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row3w);
			table.addCell(cell);
			
			System.out.println("[ErpTTHoadonPdfSvl.CreatePhieuChi] nccId = " + tthdBean.getNccId());
			cell = new PdfPCell(new Paragraph(tthdBean.getNccId(), font12bold)); //Ng?????i nh???n
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row4w);
			table.addCell(cell);

			System.out.println("[ErpTTHoadonPdfSvl.CreatePhieuChi] diachi = " + tthdBean.getDiachi());
			cell = new PdfPCell(new Paragraph(tthdBean.getDiachi(), font12bold)); //?????a ch???
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row5w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(tthdBean.getNoidungtt(), font12bold)); //N???i dung thanh to??n
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row6w);
			table.addCell(cell);
			
			//S??? ti???n thanh to??n
			float fsotien = 0.0f;
			try {
				fsotien = Float.parseFloat(tthdBean.getSotientt());
				cell = new PdfPCell(new Paragraph(formatter.format(fsotien) + "??", font12bold)); 
			} catch (Exception e) {
				System.out.println("[ErpTTHoaDonPdfSvl.CreatePhieuChi] so tien thanh toan exception message= " + e.getMessage());
				cell = new PdfPCell(new Paragraph(tthdBean.getSotientt() + "??", font12bold));
			}
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row7w);
			table.addCell(cell);
			
			//Vi???t b???ng ch???
			try {
				NumberFormat _format = new DecimalFormat("############");
				String sotienfm = _format.format(fsotien);
				long lsotien = Long.parseLong(sotienfm);
				cell = new PdfPCell(new Paragraph(DocTien.docTien(lsotien), font12bold)); 
			}
			catch (Exception e) {
				System.out.println("[ErpTTHoaDonPdfSvl.CreatePhieuChi] viet bang chu exception message = " + e.getMessage());
				cell = new PdfPCell(new Paragraph(" ", font12bold));
			}
			cell.setPaddingLeft(PADDING_LEFT_2);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(BORDER_WIDTH);
			cell.setFixedHeight(row8w);
			table.addCell(cell);
			
			
			document.add(table);
			
			document.close(); 
			
	        
		}
		catch(Exception e)
		{
			System.out.println("Exception In Phieu Chi: " + e.getMessage());
			e.printStackTrace();
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
	
	private String DinhDangCANFOCO(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");
		
		return sotien;
	}
	
	
}
