package geso.traphaco.erp.servlets.xoakhachhangtt;

import geso.traphaco.erp.beans.doctien.DocTien;
import geso.traphaco.center.beans.cauhinhinhoadon.IErpCauHinhInHoaDon;
import geso.traphaco.center.beans.cauhinhinhoadon.imp.ErpCauHinhInHoaDon;
import geso.traphaco.center.beans.doctien.doctienrachu;
import geso.traphaco.center.db.sql.dbutils;
import geso.traphaco.distributor.util.Utility;
import geso.traphaco.erp.beans.lenhsanxuat.IErpChuyenkhoSX;
import geso.traphaco.erp.beans.thutien.IErpThutien;
import geso.traphaco.erp.beans.thutien.imp.ErpThutien;
import geso.traphaco.erp.beans.xoakhachhangtt.IErpBangkethutien;
import geso.traphaco.erp.beans.xoakhachhangtt.imp.ErpBangkethutien;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
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

public class ErpBangKePdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErpBangKePdfSvl() {
        super();
    }
    
    float CONVERT = 28.346457f;
    dbutils db = new dbutils();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		//String userTen = (String) session.getAttribute("userTen");  	

		 if (userId.length() == 0)
		    	userId = request.getParameter("userId");
			   
		String id = request.getParameter("id");
		String ctyId = (String)session.getAttribute("congtyId");
		 
		System.out.println(ctyId);
		
		IErpBangkethutien tthdBean = new ErpBangkethutien(id);
	    tthdBean.setUserId(userId);
	    tthdBean.setCongtyId(ctyId);
	    
	    Utility util = new Utility();
	    
	    String nppdangnhap = util.getIdNhapp(userId);
	    tthdBean.setnppdangnhap(nppdangnhap);
	    
    	response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Phieuthutien.pdf");
		System.out.println("Ch??c ch??n vao day chua? ");
		
		float CONVERT = 28.346457f;
		float PAGE_LEFT = 1.0f*CONVERT, PAGE_RIGHT = 1.5f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		//chinh khung giay a5
		//Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);
		Document document = new Document();
		
		//Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
		///Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();		
		
		this.CreatePTPdf(document, outstream,tthdBean, id);
		
		document.close();
		
		/*response.setContentType("application/pdf");
		response.setHeader("Content-Disposition"," inline; filename=PhieuThu"+id+".pdf");
		
		System.out.println("Print");*/
		
		
		/*IErpCauHinhInHoaDon config = new ErpCauHinhInHoaDon();
		config.initWithName("PHIEUTHU");
		
		
		//float PAGE_LEFT = 1.0f*CONVERT, PAGE_RIGHT = 0.5f*CONVERT, PAGE_TOP = 3.2f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
		float PAGE_LEFT = config.getMarginLeft()*CONVERT, PAGE_RIGHT = config.getMarginRight()*CONVERT, PAGE_TOP = config.getMarginTop()*CONVERT, PAGE_BOTTOM = config.getMarginBottom()*CONVERT; //cm
		Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);
		Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);*/
		
		/*ServletOutputStream outstream = response.getOutputStream();
		
		this.CreatePhieuThu(document, outstream, tthdBean);*/
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
	
	private void CreatePTPdf(Document document, ServletOutputStream outstream, IErpBangkethutien tthdBean, String Id) throws IOException
	{
		try{
			dbutils db = new dbutils();
			NumberFormat formatter = new DecimalFormat("#,###,###.###");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			
			//tthdBean.initPdf();
			
			String ttnpp = "SELECT TEN, DIACHI FROM NHAPHANPHOI WHERE PK_SEQ = "+tthdBean.getnppdangnhap();
			
			System.out.println(ttnpp);
			ResultSet nppRs = db.get(ttnpp);
			
			String tennpp = "";
			String dcnpp = "";
			
			if(nppRs!= null)
			{
				try {
					while (nppRs.next()) {
						tennpp = nppRs.getString("TEN");
						dcnpp = nppRs.getString("DIACHI");
					}
					nppRs.close();
				}
				catch(SQLException e)
				{
					System.out.println("115..Exception: " + e.getMessage());
				}
			}
			
			
			String query = "select a.ngaychungtu, a.pk_seq machungtu, B.TEN NGUOITAO from ERP_BANGKETHUTIEN a inner join NHANVIEN b on a.nguoitao = b.pk_seq where a.pk_seq = '"+ Id +"' ";
			
			String ngaychungtu ="";
			String machungtu = "";
			String nguoitao = "";
			
			System.out.println(query);
			ResultSet rs = db.get(query);
			if (rs != null) {
				try {
					while (rs.next()) {
						ngaychungtu = rs.getString("ngaychungtu");		
						machungtu = rs.getString("machungtu");
						nguoitao = rs.getString("NGUOITAO");
					}
					rs.close();
				} catch (SQLException e) {
					System.out.println("115..Exception: " + e.getMessage());
				}
			}
			document.setPageSize(PageSize.A3); 
			
			document.setMargins(0.7f*CONVERT, 1.5f*CONVERT, 2.0f*CONVERT, 0f*CONVERT); // L,R, T, B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			
			document.open();
			//document.setPageSize(new Rectangle(100.0f, 100.0f));
			//document.setPageSize(PageSize.A4.rotate());
		

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			
			
			//C??NG TY C??? PH???N ????? H???P H??? LONG
				
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);	
			float[] withstableheader = {120f};
			tableheader.setWidths(withstableheader);
			
			PdfPCell cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			Paragraph hd = new Paragraph(tennpp , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_LEFT);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
						
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					
			hd = new Paragraph(dcnpp , new Font(bf, 12, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			//cell.setFixedHeight(0.8f*CONVERT);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
						
			document.add(tableheader);
			
			//????N V??? B??N H??NG
			
			PdfPTable table1 = new PdfPTable(1);
			table1.setWidthPercentage(100);
			float[] withs1 = {100f};
			table1.setWidths(withs1);
					
			cell = new PdfPCell();	
			hd = new Paragraph("B???NG K?? N???P TI???N" , new Font(bf, 14, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			//cell.setFixedHeight(1.0f*CONVERT);	
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("TH??NG TIN CH???NG T???" , new Font(bf, 13, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table1.addCell(cell);
			
			document.add(table1);
			
			//????N V??? B??N H??NG
			
			PdfPTable table5 = new PdfPTable(2);
			table5.setWidthPercentage(100);
			float[] withs5 = {200f, 300f};
			table5.setWidths(withs5);
					
			cell = new PdfPCell();	
			hd = new Paragraph("M?? ch???ng t???: "+machungtu , new Font(bf, 13, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			//cell.setFixedHeight(1.0f*CONVERT);	
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table5.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("Ng?????i l???p: "+nguoitao , new Font(bf, 13, Font.BOLD));//NGUOI MUA
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table5.addCell(cell);
			
			document.add(table5);
			
			//NG??Y CH???NG T???
			String [] ngayPT = ngaychungtu.split("-");
			
			PdfPTable table2 = new PdfPTable(1);
			table2.setWidthPercentage(100);
			float[] withs2 = {200f};
			table2.setWidths(withs2);
			
			cell = new PdfPCell();	
			hd = new Paragraph("????N ?????T H??NG", new Font(bf, 13, Font.BOLD));//NGUOI MUA
			//hd = new Paragraph("Ng??y " + ngayPT[2] + " th??ng " + ngayPT[1] +  " n??m " + ngayPT[0]  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(0.8f*CONVERT);
			cell.addElement(hd);
			cell.setBorder(0);	
			
			table2.addCell(cell);
				
			document.add(table2);
			
						
			String[] th = new String[]{ "STT", "M?? CT",  "M?? KH","KH??CH H??NG","NG??Y L???P","???? THANH TO??N", "TI???N M???T", "% CHI???T KH???U", "TI???N CHI???T KH???U","PH???I TR???","GHI CH??"};
					
						
			PdfPTable nhanvien = new PdfPTable(th.length);
			nhanvien.setSpacingBefore(0.5f*CONVERT);
			nhanvien.setWidthPercentage(100);
			nhanvien.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			float[] withsKM = { 1.0f*CONVERT,2.0f*CONVERT, 2.0f*CONVERT, 4.0f*CONVERT,2.3f*CONVERT ,3.0f*CONVERT,2.0f*CONVERT, 2.0f*CONVERT, 2.0f*CONVERT, 2.0f*CONVERT, 2.0f*CONVERT };
			nhanvien.setWidths(withsKM);
			
			PdfPCell cells = new PdfPCell();
			
						
			
			for (int j = 0; j < th.length; j++)
			{
				System.out.println(th[j]);
				cells = new PdfPCell(new Paragraph(th[j], new Font(bf, 9, Font.BOLD)));
				if (j <= 1 ){ //STT
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					//cells.setRowspan(2);
				}
				else{							
						if(j == 2 )//????N V??? T??NH
						{
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							//cells.setRowspan(2);
						}								
						else{//S??? L?????NG, ????N GI??, ????N ???? GI???M GI??, TH??NH TI???N
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							//cells.setRowspan(2);
						}
				}
				
				cells.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				cells.setBorderWidth(1);
				//cells.setFixedHeight(0.8f * CONVERT);	
				nhanvien.addCell(cells);
			}
			
			
			query =
					" select B.SOHOADON, B.NGAYXUATHD, ISNULL(ISNULL(C.MAFAST, D.MAFAST), E.MA) MAKH, \n"+ 
					" ISNULL(ISNULL(C.TEN, D.TEN),E.TEN) TENKH, isnull(A.thanhtoan,0) SOTIENTT, \n"+ 
				  	" ISNULL(A.tienck,0) TIENCK, ISNULL(A.thucthu,0) THUCTHU, K.MACHUNGTU, K.NGAYDONHANG, A.PTCHIETKHAU, isnull(A.GHICHU, '') GHICHU \n"+
					" from ERP_BANGKETHUTIEN_HOADON A INNER JOIN ERP_HOADONNPP B ON A.HOADON_FK = B.PK_SEQ \n"+
				  	" LEFT JOIN ERP_HOADONNPP_DDH F ON F.HOADONNPP_FK = B.PK_SEQ \n"+
					" LEFT JOIN ERP_DonDatHangNPP K ON F.DDH_FK = K.PK_SEQ \n"+
					" LEFT JOIN KHACHHANG C ON B.KHACHHANG_FK = C.PK_SEQ \n"+
					" LEFT JOIN NHAPHANPHOI D ON B.NPP_DAT_FK = D.PK_SEQ \n"+
					" LEFT JOIN ERP_NHANVIEN E ON B.NHANVIEN_FK = E.PK_SEQ \n"+
					" WHERE A.BANGKE_FK = "+Id;
					
			System.out.println(query);
			rs = db.get(query);
			int stt =0;
			
			String SOHOADON = "";
			String NGAYXUATHD = "";
			String MAKH = "";
			String TENKH = "";
			String MACHUNGTU = "";
			String PTCHIETKHAU = "";
			String GHICHU1 =  "";
			double TIENTT =  0; 
			double TIENCK = 0;
			double THUCTHU = 0;
			double SUMTIENTT = 0;
			double SUMTIENCK = 0;
			double SUMTHUCTHU = 0;
			
			if(rs!=null){
				while(rs.next()){
					stt++;
					SOHOADON = rs.getString("SOHOADON");
					NGAYXUATHD = rs.getString("NGAYXUATHD");
					MAKH = rs.getString("MAKH");
					TENKH = rs.getString("TENKH");
					TIENTT = rs.getDouble("SOTIENTT");
					TIENCK = rs.getDouble("TIENCK");
					THUCTHU = rs.getDouble("THUCTHU");
					MACHUNGTU = rs.getString("MACHUNGTU");
					PTCHIETKHAU =  rs.getString("PTCHIETKHAU");
					GHICHU1 =  rs.getString("GHICHU");
					
					SUMTIENTT += TIENTT;
					SUMTIENCK += TIENCK;
					SUMTHUCTHU += THUCTHU;
					
					//SUMSOTIENTT += Math.round(SOTIENTT);
					
					String[] arr = new String[] { Integer.toString(stt), SOHOADON , MAKH ,TENKH,NGAYXUATHD ,DinhDangTraphacoERP(formatter1.format(TIENTT)),"0",PTCHIETKHAU, DinhDangTraphacoERP(formatter1.format(TIENCK)),DinhDangTraphacoERP(formatter1.format(THUCTHU)), GHICHU1};
					
					for (int j = 0; j < th.length; j++)
					{
						System.out.println(arr[j]);
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 12, Font.NORMAL)));
						if (j <= 1 ){ //STT
							if(j == 0 )
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							if(j == 1 )
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							//cells.setPaddingLeft(-0.5f*CONVERT);
						}
						else{							
								if(j == 2 || j==4 )//????N V??? T??NH
								{
									cells.setHorizontalAlignment(Element.ALIGN_CENTER);
								}
								else if(j== 3 || j==10)
								{
									cells.setHorizontalAlignment(Element.ALIGN_LEFT);
								}
								else{//S??? L?????NG, ????N GI??, ????N ???? GI???M GI??, TH??NH TI???N
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
								}
						}
						
						cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cells.setBorderWidth(1);
						cells.setPaddingBottom(0.2f*CONVERT);
						nhanvien.addCell(cells);
					}
				}
			}
			rs.close();
			
			stt++;
			//D??NG T???NG C???NG
		/*	String[] arr = new String[] {"T???ng c???ng:", DinhDangTraphacoERP(formatter1.format(SUMTIENTT)),"0","",DinhDangTraphacoERP(formatter1.format(SUMTIENCK)),DinhDangTraphacoERP(formatter1.format(SUMTHUCTHU)) };*/
			String[] arr = new String[] { "T???ng c???ng:", DinhDangTraphacoERP(formatter1.format(SUMTIENTT)),"","", DinhDangTraphacoERP(formatter1.format(SUMTIENCK)),DinhDangTraphacoERP(formatter1.format(SUMTHUCTHU)), ""};
			
			for (int j = 0; j < 7; j++)
			{
				System.out.println(arr[j]);
				cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 12, Font.BOLD)));
				if (j == 0 ){ 
					cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
					cells.setColspan(5);
					
				}
				else{							
					
					cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
						
				}
				
				cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cells.setBorderWidth(1);
				cells.setFixedHeight(0.8f * CONVERT);	
				nhanvien.addCell(cells);
			}
			
			document.add(nhanvien);
			
			
			PdfPTable tablefooter =new PdfPTable(2);
			tablefooter.setSpacingBefore(0.5f*CONVERT);
			tablefooter.setWidthPercentage(100);
			float[] withs14 = {500f, 800f};
			tablefooter.setWidths(withs14);
			NumberFormat formatter2 = new DecimalFormat("###########"); 
			
			cell = new PdfPCell();			
			hd = new Paragraph("T???ng ti???n (B???ng ch???): " + DocTien.docTien(Long.parseLong(formatter2.format(SUMTHUCTHU))), new Font(bf, 12, Font.BOLDITALIC));
			hd.setAlignment(Element.ALIGN_LEFT);
			//cell.setFixedHeight(0.8f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(2);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);
			
			cell = new PdfPCell();				
			hd = new Paragraph("Ghi ch??: ", new Font(bf, 12, Font.BOLDITALIC));
			hd.setAlignment(Element.ALIGN_LEFT);
			//cell.setFixedHeight(0.8f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(2);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);			
			
			
			cell = new PdfPCell();				
			hd = new Paragraph("Ng??y " + ngayPT[2] + " th??ng " + ngayPT[1] +  " n??m " + ngayPT[0]  , new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(2);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("Ng?????i nh???n ti???n " , new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_CENTER);
			//cell.setFixedHeight(1.6f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("Ng?????i l???p " , new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_RIGHT);
			//cell.setFixedHeight(1.6f*CONVERT);
			cell.setPaddingRight(1.0f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);	
			
			document.add(tablefooter);
			
		}
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
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
	
	private void CreatePhieuThu(Document document, ServletOutputStream outstream, IErpThutien tthdBean) 
	{
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,###"); 
			NumberFormat formatter2 = new DecimalFormat("#,###,##0.00000"); 
			NumberFormat formatter3 = new DecimalFormat("#,###,##0.000");
			
			IErpCauHinhInHoaDon config = new ErpCauHinhInHoaDon();
			config.initWithName("PHIEUTHU");
			
			IErpCauHinhInHoaDon configp = new ErpCauHinhInHoaDon();
			configp.initWithName("PHIEUTHUP");
			
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
			Font font9 = new Font(bf, 9, Font.NORMAL);
			Font font10 = new Font(bf, 10, Font.NORMAL);
			Font font11 = new Font(bf, 11, Font.NORMAL);
			Font font12 = new Font(bf, 12, Font.NORMAL);
			Font font13 = new Font(bf, 13, Font.NORMAL);
			Font font14 = new Font(bf, 14, Font.NORMAL);
			
			float CONVERT = 28.346457f; // = 1cm
			float[] TABLE_WIDTHS = {18.0f * CONVERT};
			float PADDING_LEFT_3 = configp.getMarginLeft() * CONVERT;
			float PADDING_LEFT_0 = configp.getMarginRight() * CONVERT;
			float PADDING_LEFT_1 = configp.getMarginTop() * CONVERT;
			float PADDING_LEFT_2 = configp.getMarginBottom() * CONVERT;
			float PADDING_LEFT_4 = configp.getPaddingLeft() * CONVERT;

			/*float PADDING_LEFT_3 = 13.2f * CONVERT;
			float PADDING_LEFT_0 = 10.0f * CONVERT;
			float PADDING_LEFT_1 = 7.0f * CONVERT;
			float PADDING_LEFT_2 = 1.0f * CONVERT;
			float PADDING_LEFT_4 = 5.0f * CONVERT;*/
			
			//????? r???ng m???i d??ng
			float row1w = config.getPaddingLeft() * CONVERT; //0.5f*CONVERT pk_seq
			float row2w = config.getPaddingRight() * CONVERT; //0.8f*CONVERT r???ng
			float row3w = config.getPaddingTop() * CONVERT; //0.8f*CONVERT ng??y ghi s???
			float row4w = config.getPaddingBottom() * CONVERT; //1.2f*CONVERT ng?????i n???p ti???n
			float row5w = config.getNoColumn() * CONVERT; //0.9f*CONVERT ?????a ch???
			float row6w = config.getUnitColumn() * CONVERT; //0.9f*CONVERT l?? do thu
			float row7w = config.getQuantityColumn() * CONVERT; //0.9f*CONVERT ti???n
			float row8w = config.getUniPriceColumn() * CONVERT; //0.9f*CONVERT vi???t b???ng ch???
			float row9w = 0.9f * CONVERT; //0.9f*CONVERT ghi ch??
			
			
			String[] ncc = tthdBean.getNppId().split(" --- ");
			String[] noidung = tthdBean.getNoidungId().split(" --- ");
			
			if(noidung[0].equals("100002") )
			{						
				ncc = tthdBean.getNguoinoptien().split(" --- ");
				if(ncc[0].equals("NA"))	ncc[0] = "";
				if(ncc[1].equals("NA"))	ncc[1] = "";	
					
			}

			
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.tranlate(tthdBean.getThuduoc().replaceAll(",", ""));
			tien = tien.substring(0, 1).toUpperCase() + tien.subSequence(1, tien.length());
			
			//String[] arr = new String[]{"H??? t??n ng?????i n???p ti???n:", ncc[0], "?????a ch???:", ncc[1], "L?? do n???p:", tthdBean.getNoidungId(), 
			//					"S??? ti???n:", tthdBean.getSotientt(), "B???ng ch???:", tien, "K??m theo h??a ????n GTGT:", tthdBean.getNoidungtt()};
			
	        	        
	        PdfPTable table = new PdfPTable(1);	
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setWidths(TABLE_WIDTHS);
			table.setWidthPercentage(100.0f);
			
			PdfPCell cell;
			cell = new PdfPCell(new Paragraph(tthdBean.getId(), font10bold)); //PK_SEQ
			cell.setPaddingLeft(PADDING_LEFT_3);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row1w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(" ", font10bold)); //R???ng
			cell.setPaddingLeft(PADDING_LEFT_3);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row2w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(tthdBean.getNgaychungtu(), font12bold)); //Ng??y ghi s???
			cell.setPaddingLeft(PADDING_LEFT_0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row3w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(ncc[0], font12bold)); //Ng?????i n???p ti???n
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row4w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(ncc[1], font12bold)); //?????a ch???
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row5w);
			table.addCell(cell);
			
			cell = new PdfPCell(new Paragraph(noidung[1], font12bold)); //L?? do thu
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row6w);
			table.addCell(cell);
			
			//Ti???n
			cell = new PdfPCell(new Paragraph(tthdBean.getThuduoc() + "??", font12bold));
			cell.setPaddingLeft(PADDING_LEFT_1);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row7w);
			table.addCell(cell);
			
			//Vi???t b???ng ch???
			cell = new PdfPCell(new Paragraph(tien, font12bold)); 
			cell.setPaddingLeft(PADDING_LEFT_2);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row8w);
			table.addCell(cell);
			
			//Ghi ch??
			cell = new PdfPCell(new Paragraph(tthdBean.getNoidungtt(), font12bold)); 
			cell.setPaddingLeft(PADDING_LEFT_4);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setBorder(0);
			cell.setFixedHeight(row9w);
			table.addCell(cell);
			
			document.add(table);
			
			document.close();
			
			
			/*
			PdfWriter.getInstance(document, outstream);
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 15, Font.BOLD);
			//font2.setColor(BaseColor.GREEN);
			 
			String query = "select * from ERP_CONGTY where PK_SEQ = '100001'";
	        String tenncc = "";
	        String diachincc = "";
	        
	        dbutils db = new dbutils();
	        ResultSet rs = db.get(query);
	        if(rs.next())
	        {
	        	tenncc = rs.getString("ten");
	        	diachincc = rs.getString("diachi");
	        	rs.close();
	        }
	        
			Paragraph pxk = new Paragraph(tenncc, new Font(bf, 8, Font.BOLD));
			pxk.setSpacingAfter(2);
			pxk.setSpacingBefore(-25);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph(diachincc, new Font(bf, 8, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("MST 0 3 1 0 7 7 6 0 7 1", new Font(bf, 8, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("PHI???U THU", font);
			pxk.setSpacingAfter(3);
			pxk.setSpacingBefore(10);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			
			pxk = new Paragraph(this.getDate(tthdBean.getNgaychungtu()), new Font(bf, 8, Font.ITALIC));
			pxk.setSpacingAfter(3);
			pxk.setSpacingBefore(10);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			
			pxk = new Paragraph("S???: " + tthdBean.getId(), new Font(bf, 7, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_RIGHT);
			document.add(pxk);
			
			pxk = new Paragraph("N???:           ", new Font(bf, 7, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_RIGHT);
			document.add(pxk);
			
			pxk = new Paragraph("C??:           ", new Font(bf, 7, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setAlignment(Element.ALIGN_RIGHT);
			document.add(pxk);
			
			PdfPTable table = new PdfPTable(2);
			table.setWidthPercentage(98);
			table.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.setWidths(new float[]{50.0f, 150.0f});
			
			String[] ncc = tthdBean.getNppId().split(" --- ");
			//String[] noidung = tthdBean.getNoidungtt().split(" --- ");
			
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.tranlate(tthdBean.getSotientt().replaceAll(",", ""));
			tien = tien.substring(0, 1).toUpperCase() + tien.subSequence(1, tien.length());
			
			String[] arr = new String[]{"H??? t??n ng?????i n???p ti???n:", ncc[0], "?????a ch???:", ncc[1], "L?? do n???p:", tthdBean.getNoidungId(), 
								"S??? ti???n:", tthdBean.getSotientt(), "B???ng ch???:", tien, "K??m theo h??a ????n GTGT:", tthdBean.getNoidungtt()};
			
			for(int i = 0; i < arr.length; i++)
			{
				PdfPCell cell = new PdfPCell(new Paragraph(arr[i], new Font(bf, 9, Font.NORMAL))); 
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				table.addCell(cell);
			}

			document.add(table);
			
			//Table Footer			
			PdfPTable tableFooter = new PdfPTable(5);
			tableFooter.setSpacingBefore(30.0f);
			tableFooter.setWidthPercentage(90);
			tableFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableFooter.setWidths(new float[]{30.0f, 30.0f, 30.0f, 30.0f, 30.0f});
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("Ng?????i l???p phi???u", new Font(bf, 9, Font.BOLD)));
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell12 = new PdfPCell(new Paragraph("Ng?????i n???p ti???n", new Font(bf, 9, Font.BOLD)));
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell13 = new PdfPCell(new Paragraph("Th??? qu???", new Font(bf, 9, Font.BOLD)));
			cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell14 = new PdfPCell(new Paragraph("K??? to??n tr?????ng", new Font(bf, 9, Font.BOLD)));
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell15 = new PdfPCell(new Paragraph("Gi??m ?????c", new Font(bf, 9, Font.BOLD)));
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			cell11.setBorder(0);
			cell12.setBorder(0);
			cell13.setBorder(0);
			cell14.setBorder(0);
			cell15.setBorder(0);
			
			tableFooter.addCell(cell11);
			tableFooter.addCell(cell12);
			tableFooter.addCell(cell13);
			tableFooter.addCell(cell14);
			tableFooter.addCell(cell15);
			
			cell11 = new PdfPCell(new Paragraph("(K??, h??? t??n)", new Font(bf, 9, Font.ITALIC)));
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell12 = new PdfPCell(new Paragraph("(K??, h??? t??n)", new Font(bf, 9, Font.ITALIC)));
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell13 = new PdfPCell(new Paragraph("(K??, h??? t??n)", new Font(bf, 9, Font.ITALIC)));
			cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell14 = new PdfPCell(new Paragraph("(K??, h??? t??n)", new Font(bf, 9, Font.ITALIC)));
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell15 = new PdfPCell(new Paragraph("(K??, h??? t??n)", new Font(bf, 9, Font.ITALIC)));
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			cell11.setBorder(0);
			cell12.setBorder(0);
			cell13.setBorder(0);
			cell14.setBorder(0);
			cell15.setBorder(0);
			
			tableFooter.addCell(cell11);
			tableFooter.addCell(cell12);
			tableFooter.addCell(cell13);
			tableFooter.addCell(cell14);
			tableFooter.addCell(cell15);
			
			document.add(tableFooter);
			document.close(); 
*/		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Exception In Phieu Thu: " + e.getMessage());
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
