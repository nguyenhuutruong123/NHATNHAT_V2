package geso.traphaco.erp.servlets.uynhiemchi;

import geso.traphaco.erp.beans.doctien.DocTien;
import geso.traphaco.center.db.sql.dbutils;
import geso.traphaco.erp.beans.uynhiemchi.IErpUynhiemchi;
import geso.traphaco.erp.beans.uynhiemchi.imp.ErpUynhiemchi;
import geso.traphaco.erp.beans.thutien.IErpThutien;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpUynhiemchiPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ErpUynhiemchiPdfSvl() {
        super();
    }

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
		String inchitiet = request.getParameter("inchitiet");
		String ctyId = (String)session.getAttribute("congtyId");
		 
		System.out.println("inchitiet:"+inchitiet);
		if(httt == null)
			httt = "";
			
		IErpUynhiemchi tthdBean = new ErpUynhiemchi(id);
		tthdBean.setHtttId(httt);
	    tthdBean.setUserId(userId);
	    tthdBean.setCtyId(ctyId);

		OutputStream out = response.getOutputStream();
	    
	    if(tthdBean.getHtttId().equals("100001"))
	    {
	    	tthdBean.DBclose();
	    	
	    	/*response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=UyNhiemChi.xlsm");
	        try {
				CreateUyNhiemChi(out, tthdBean);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
	    	if(inchitiet.equals("0")){
		    	//KI???M TRA N???U M?? NG??N H??NG TR??? L?? IVB TH?? CANH CHI???U D???C
		    	String query = 
					"	SELECT 	tthd.SOTAIKHOAN as SoTaiKhoan, isnull(nh.TEN, '') as traNganHang, isnull(nh.MA, '') as traNganHangMa \n"+ 
					"	FROM  	ERP_THANHTOANHOADON tthd  left join ERP_NGANHANG nh on tthd.nganhang_fk = nh.pk_seq \n"+   
					"   WHERE   tthd.pk_seq = '"+tthdBean.getId()+"' ";
					
					System.out.println("MA NGAN HANG__:"+query);
					dbutils db = new dbutils();
					ResultSet rs = db.get(query);
					
					String manganhangtra = "";
					try{
						if(rs!=null){
							while(rs.next()){
								manganhangtra = rs.getString("traNganHangMa");
							}
						}
					}
					catch (Exception e){
						e.printStackTrace();
					}
		    	
		    	response.setContentType("application/pdf");
				response.setHeader("Content-Disposition"," inline; filename=UyNhiemChi"+tthdBean.getId()+".pdf");
				
				
				ServletOutputStream outstream = response.getOutputStream();
				
				if(manganhangtra.equals("IVB")){
					Document document = new Document();
					//document.setPageSize(PageSize.A4.rotate()); //CANH H??A ????N THEO CHI???U D???C
					document.setMargins(2.0f*CONVERT, 2.0f*CONVERT, 0f*CONVERT, 0f*CONVERT); // L,R, T, B
					this.CreateUyNhiemChiPdf(document, outstream, tthdBean);
					
					document.close();
					
				} else if (manganhangtra.equals("ACB"))
				{
					float PAGE_LEFT = 1*CONVERT, PAGE_RIGHT = 1*CONVERT, PAGE_TOP = 0.0f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
					Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);//CANH THEO CHI???U NGANG
					Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
					
					this.CreateUyNhiemChiPdf(document, outstream, tthdBean);
					
					document.close();
				}
				else if (manganhangtra.equals("SACOMBANK")) 
				{
					float PAGE_LEFT = 0.2f*CONVERT, PAGE_RIGHT = 0.2f*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.5f*CONVERT; //cm
					Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);//CANH THEO CHI???U NGANG
					Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
					
					this.CreateUyNhiemChiPdf(document, outstream, tthdBean);
					
					document.close();
				}
				else if (manganhangtra.equals("ANBINH")) 
				{
					float PAGE_LEFT = 0.2f*CONVERT, PAGE_RIGHT = 0.2f*CONVERT, PAGE_TOP = 0.0f*CONVERT, PAGE_BOTTOM = 0.2f*CONVERT; //cm
					Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);//CANH THEO CHI???U NGANG
					Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
					
					this.CreateUyNhiemChiPdf(document, outstream, tthdBean);
					
					document.close();
				}
				else if (manganhangtra.equals("VIETINBANK")) 
				{
					float PAGE_LEFT = 0.2f*CONVERT, PAGE_RIGHT = 0.2f*CONVERT, PAGE_TOP = 0.2f*CONVERT, PAGE_BOTTOM = 0.2f*CONVERT; //cm
					Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);//CANH THEO CHI???U NGANG
					Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
					
					this.CreateUyNhiemChiPdf(document, outstream, tthdBean);
					
					document.close();
				}
				else{
					float PAGE_LEFT = 1*CONVERT, PAGE_RIGHT = 1*CONVERT, PAGE_TOP = 0.5f*CONVERT, PAGE_BOTTOM = 0.5f*CONVERT; //cm
					Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);//CANH THEO CHI???U NGANG
					Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
					
					this.CreateUyNhiemChiPdf(document, outstream, tthdBean);
					
					document.close();
				}
			
	    	}
	    	else{
	    		response.setContentType("application/pdf");
				response.setHeader("Content-Disposition"," inline; filename=UyNhiemChiChiTiet"+tthdBean.getId()+".pdf");
				
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();	
				try {
				PdfWriter writer =	PdfWriter.getInstance(document, outstream);
				
				writer.setPageEvent(new HeaderAndFooter(tthdBean.getId()));
				 
				this.CreateUyNhiemChiBPPdf(document, outstream,tthdBean, id);// S??? D???NG CHO IN B??? PH???N CHI TI???T
				} catch (DocumentException e) {
					
					e.printStackTrace();
				}
				
				
				document.close();
	    	}
	    	/*response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", " inline; filename=UyNhiemChi.pdf");
			
			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();		
			
			this.CreatePCPdf(document, outstream,tthdBean, id);*/
			
			
	    }
	    else
	    {
	    	/*tthdBean.initPdf();
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition"," inline; filename=PhieuChi.pdf");
			
			IErpCauHinhInHoaDon config = new ErpCauHinhInHoaDon();
			config.initWithName("PHIEUTHU");
			
			float CONVERT = 28.346457f;
			float PAGE_LEFT = config.getMarginLeft()*CONVERT, PAGE_RIGHT = config.getMarginRight()*CONVERT, PAGE_TOP = config.getMarginTop()*CONVERT, PAGE_BOTTOM = config.getMarginBottom()*CONVERT; //cm
			//float PAGE_LEFT = 1.0f*CONVERT, PAGE_RIGHT = 0.5f*CONVERT, PAGE_TOP = 3.2f*CONVERT, PAGE_BOTTOM = 0.0f*CONVERT; //cm
			Rectangle a5 = new Rectangle(PageSize.A4.getWidth(), PageSize.A4.getHeight()/2);
			Document document = new Document(a5, PAGE_LEFT, PAGE_RIGHT, PAGE_TOP, PAGE_BOTTOM);
			
			ServletOutputStream outstream = response.getOutputStream();
			
			this.CreatePhieuChi(document, outstream, tthdBean);*/
	    	
	    	response.setContentType("application/xlsm");
			response.setHeader("Content-Disposition", "attachment; filename=Thanhtoan"+tthdBean.getSohoadon()+".xlsm");
	        try {
	        	OutputStream outstream = response.getOutputStream();
				this.CreatePhieuChiExcel(outstream, tthdBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
		
	}
	
	private void CreatePhieuChiExcel(OutputStream outstream,
			IErpUynhiemchi tthdBean) {
		// TODO Auto-generated method stub
		
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
			String nhanTen = "", nhanStk = "", nhanNganHang = "", nhanDiaChi = "", nhanChiNhanh = "" , nhanvien_fk, donvithuchien = "" , pk_seq ="";

			dbutils db = new dbutils();
			String query =  " select tthd.SOTAIKHOAN as SoTaiKhoan, isnull(nh.TEN, '') as traNganHang, isnull(nh.MA, '') as traNganHangMa, isnull(cn.TEN, '') as traChiNhanh, isnull((select ten from erp_congty where pk_seq = 100005), '') as congty, \n" +
							"		 isnull(tthd.LOAITHANHTOAN,0) as LOAITHANHTOAN, \n " +
							" 		 isnull(ncc.TEN,'') as nccTen, isnull(ncc.DIACHI,'') as nccDiaChi, ncc.sotaikhoan as nccStk, isnull(nccnh.TEN, '') AS nccNganHang, isnull(ncccn.TEN, '') AS nccChiNhanh, \n " +
							" 		 isnull(nv.TEN,'') as nvTen, isnull(nv.DIACHI,'') as nvDiaChi, nv.sotaikhoan as nvStk, isnull(nvnh.TEN, '') AS nvNganHang, isnull(nvcn.TEN, '') AS nvChiNhanh, \n " +
							" 		 tthd.SOTIENTT as SOTIENTT, tthd.NOIDUNGTT as NOIDUNGTT, \n " +
							" 		 tthd.NGAYGHINHAN as NGAYGHINHAN, \n" +
							"		 tthd.nhanvien_fk  , \n" +
							"   	 tthd.pk_seq , \n " +
							" 		 isnull(dv.ten, '') as donvithuchien, \n " +
							"		 isnull((select DIENTHOAI from erp_congty where pk_seq = 100005), '') as dienthoai \n"+  
							" from ERP_THANHTOANHOADON tthd \n " +
							" left join ERP_NGANHANG nh on tthd.nganhang_fk = nh.pk_seq  \n" +
							" left join ERP_CHINHANH cn on tthd.chinhanh_fk = cn.pk_seq  \n" +
							" left join ERP_NHACUNGCAP ncc on tthd.ncc_fk = ncc.pk_seq \n" +
							" left join ERP_NGANHANG nccnh on ncc.nganhang_fk = nccnh.pk_seq  \n" +
							" left join ERP_CHINHANH ncccn on ncc.chinhanh_fk = ncccn.pk_seq  \n" +
							" left join ERP_NHANVIEN nv on tthd.NHANVIEN_FK = nv.PK_SEQ \n" +
							" left join ERP_NGANHANG nvnh on nv.nganhang_fk = nvnh.pk_seq  \n" +
							" left join ERP_CHINHANH nvcn on nv.chinhanh_fk = nvcn.pk_seq  \n" +
							" left join ERP_DONVITHUCHIEN dv on dv.pk_seq = nv.dvth_fk \n" +
							" where tthd.pk_seq = '"+tthdBean.getId()+"' \n";
			System.out.println("[ErpTTHoaDonPdfSvl.XuatExcel] query 1 = " + query);
			
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
						 nhanvien_fk = rs.getString("nhanvien_fk");
						 donvithuchien = rs.getString("donvithuchien");
						pk_seq = rs.getString("pk_seq");
						 
						if(nhanvien_fk != null) {
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
			
			fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\ThanhToanTienMat.xlsx");
			Workbook workbook = new Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2007);
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			Worksheets worksheets = workbook.getWorksheets();
			
			//Sheet 1
		    Worksheet worksheet1 = worksheets.getSheet("inchi");
		    Cells cells1 = worksheet1.getCells();
	
		    cell = cells1.getCell("G1"); cell.setValue(pk_seq); 
		    cell = cells1.getCell("E4"); cell.setValue(getDate(ngayghinhan)); 
		    cell = cells1.getCell("E6"); cell.setValue(nhanTen); 
		    cell = cells1.getCell("E7"); cell.setValue(donvithuchien); 
		    cell = cells1.getCell("D8"); cell.setValue(noidungtt); 
		    cell = cells1.getCell("E9"); cell.setValue(sotien); 
		    cell = cells1.getCell("C10"); cell.setValue(tienbangchu + "./."); 
		    
			workbook.save(outstream);
			fstream.close();
		
		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
	
	
	
	
	private void CreateUyNhiemChiPdf(Document document, ServletOutputStream outstream, IErpUynhiemchi tthdBean) 
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
			
			String traTen = "", traStk = "", traNganHang = "", traNganHangMa = "",  traDiaChi = "", traChiNhanh = "", traDienthoai = "";
			String nhanTen = "", nhanStk = "", nhanNganHang = "", nhanDiaChi = "", nhanChiNhanh = "" , nhanvien_fk ="";

			dbutils db = new dbutils();
			String query =  " SELECT tthd.SOTAIKHOAN as SoTaiKhoan, isnull(nh.TEN, '') as traNganHang, isnull(nh.MA, '') as traNganHangMa, isnull(cn.TEN, '') as traChiNhanh, isnull((select ten from erp_congty where pk_seq = "+tthdBean.getCtyId()+"), '') as congty, \n" +
							" 		 isnull(ncc.TEN,'') as nccTen, isnull(ncc.DIACHI,'') as nccDiaChi, isnull(ncc.sotaikhoan,'') as nccStk, isnull(nccnh.TEN, '') AS nccNganHang, isnull(ncccn.TEN, '') AS nccChiNhanh, \n" +
							" 		 isnull(nv.TEN,'') as nvTen, isnull(nv.DIACHI,'') as nvDiaChi, isnull(nv.sotaikhoan,'') as nvStk, isnull(nvnh.TEN, '') AS nvNganHang, isnull(nvcn.TEN, '') AS nvChiNhanh, \n" +
							" 		 tthd.SOTIENTT as SOTIENTT, tthd.NOIDUNGTT as NOIDUNGTT, \n" +
							" 		 tthd.NGAYGHINHAN as NGAYGHINHAN, \n" +
							"		 tthd.nhanvien_fk, isnull((select DIENTHOAI from erp_congty where pk_seq = "+tthdBean.getCtyId()+"), '') as tradienthoai   \n " +
							" FROM 	 ERP_THANHTOANHOADON tthd \n" +
							" left join ERP_NGANHANG nh on tthd.nganhang_fk = nh.pk_seq \n"+  
							" left join ERP_CHINHANH cn on tthd.chinhanh_fk = cn.pk_seq \n"+  
							" left join ERP_NHACUNGCAP ncc on tthd.ncc_fk = ncc.pk_seq \n"+ 
							" left join ERP_NGANHANG nccnh on nccnh.PK_SEQ = ncc.NGANHANG_FK \n"+
							"  left join ERP_CHINHANH ncccn on ncccn.PK_SEQ = ncc.CHINHANH_FK \n"+  
							" left join ERP_NHANVIEN nv on tthd.NHANVIEN_FK = nv.PK_SEQ \n"+ 
							" left join ERP_NGANHANG nvnh on nv.nganhang_fk = nvnh.pk_seq \n"+  
							" left join ERP_CHINHANH nvcn on nv.chinhanh_fk = nvcn.pk_seq \n"+  
							" where tthd.pk_seq = '"+tthdBean.getId()+"' \n";
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
						traNganHang = rs.getString("traNganHang");
						traNganHangMa = rs.getString("traNganHangMa");
						traChiNhanh = rs.getString("traChiNhanh");
						traDienthoai = rs.getString("traDienthoai");
						//traDiaChi = rs.getString("traDiaChi");
						 nhanvien_fk = rs.getString("nhanvien_fk");
						
						if(nhanvien_fk != null) {
							nhanTen = rs.getString("nvTen");
							nhanDiaChi = rs.getString("nvDiaChi");
							nhanStk = rs.getString("nvStk");
							nhanNganHang = rs.getString("nvNganHang");
							nhanChiNhanh = rs.getString("nvChiNhanh");
						} else {
							nhanTen = rs.getString("nccTen");
							nhanDiaChi = rs.getString("nccTen");
							nhanStk = rs.getString("nccStk");
							nhanNganHang = rs.getString("nccNganHang");
							nhanChiNhanh = rs.getString("nccChiNhanh");
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
			
			
			
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			document.open();
			//document.setPageSize(new Rectangle(210.0f, 297.0f));

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font8bold = new Font(bf, 8, Font.BOLD);
			Font font8italic = new Font(bf, 8, Font.ITALIC);
			Font font8bi = new Font(bf, 8, Font.BOLDITALIC);
			Font font9bold = new Font(bf, 9, Font.BOLD);
			Font font10bold = new Font(bf, 10, Font.BOLD);
			Font font11bold = new Font(bf, 11, Font.BOLD);
			Font font11bi = new Font(bf, 11, Font.BOLDITALIC);
			Font font11italic = new Font(bf, 11, Font.ITALIC);
			Font font12bold = new Font(bf, 12, Font.BOLD);
			Font font13bold = new Font(bf, 13, Font.BOLD);
			Font font14bold = new Font(bf, 14, Font.BOLD);
			Font font15bold = new Font(bf, 15, Font.BOLD);
			Font font7italic = new Font(bf, 7, Font.ITALIC);
			Font font9italic = new Font(bf, 9, Font.ITALIC);
			Font font10italic = new Font(bf, 10, Font.ITALIC);
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
			Font font6italic = new Font(bf, 6, Font.ITALIC);
			Font font7 = new Font(bf, 7, Font.NORMAL);
			
			float CONVERT = 28.346457f; // = 1cm
			float[] TABLE_WIDTHS = {18.0f * CONVERT};

			float[] TABLE_HEADER_WIDTHS = new float[] {7.0f * CONVERT, 11.0f * CONVERT};
			float[] TABLE_HEADER_WIDTHS_VIB = new float[] {11.0f * CONVERT, 14.0f * CONVERT, 2.0f*CONVERT};
			float[] TABLE_CONTENT_WIDTHS = new float[] {8.0f * CONVERT, 10.0f * CONVERT};
			float[] TABLE_CONTENT_FIELDS_WIDTHS = new float[] {TABLE_CONTENT_WIDTHS[0]*2/5, TABLE_CONTENT_WIDTHS[0]*3/5};
			float[] TABLE_FOOTER_WIDTHS = new float[] {8.0f * CONVERT, 10.0f * CONVERT};
			
			float[] TABLE_ACB_WIDTHS = new float[] {12.0f * CONVERT, 1.75f * CONVERT,  3.25f * CONVERT,  1.0f * CONVERT};
			
			float[] TABLE_EIB_WIDTHS = new float[] {15.0f * CONVERT,0.5f * CONVERT,6.0f * CONVERT};
			
			float[] TABLE_SCBANK_WIDTHS = new float[] {15.0f * CONVERT,0.5f * CONVERT,6.0f * CONVERT};
			
			float[] TABLE_ABBBANK_WIDTHS = new float[] {15.0f * CONVERT,0.2f * CONVERT,7.0f * CONVERT};
			
			float[] TABLE_DABANK_WIDTHS = new float[] {15.0f * CONVERT,0.5f * CONVERT,6.0f * CONVERT};
			
			float[] TABLE_VIETINKBANK_WIDTHS = new float[] {15.0f * CONVERT,0.5f * CONVERT,6.0f * CONVERT};
			
			float[] TABLE_AGB_WIDTHS = new float[] {5.0f * CONVERT, 8.0f * CONVERT,  4.0f * CONVERT,  4.0f * CONVERT};
			
			PdfPCell cell,cell1 ,cell2, cell3, cell4, cell5;
			
			if(traNganHangMa.trim().equals("VCB")) // 
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
				hinhanh.scalePercent(70);
				hinhanh.setAbsolutePosition(1.3f * CONVERT, document.getPageSize().getHeight() - 2.5f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				cell = new PdfPCell(new Paragraph("(Ch???ng t??? ???????c ban h??nh theo c??ng v??n s??? 6097/CT-QTTVAC ng??y 16/04/2009 c???a C???c thu??? H?? N???i)", font7italic)); //Header
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				table.addCell(cell);
				
				//HEADER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(1);
				
				PdfPTable table2 = new PdfPTable(3);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidths(TABLE_HEADER_WIDTHS_VIB);
				table2.setWidthPercentage(100.0f);			
				
				cell2 = new PdfPCell(new Paragraph("???Y NHI???M CHI-PAYMENT ORDER", font12bold));
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(3);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("", font10));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setFixedHeight(0.5f*CONVERT);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell(new Paragraph("Ng??y (Date)   " + getDate(ngayghinhan), font10bi));
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setPaddingLeft(-0.2f*CONVERT);
				cell2.setFixedHeight(1.0f*CONVERT);
				cell2.setColspan(2);
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
				p.add(new Chunk(" (Please Debit account)", font9italic));
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
				
				cell4 = new PdfPCell(new Paragraph("S??? TK (A/C No.): ", font9));
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
				
				cell4 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??N TK ", font9)); 
				p.add(new Chunk(" (A/C name.): \n \n", font9italic));
				p.add(new Chunk("?????A CH??? ", font9)); 
				p.add(new Chunk(" (Address): ", font9italic));
				cell4.addElement(p);
				cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell4.setBorder(0);
				cell4.setFixedHeight(1.2f * CONVERT);
				table4.addCell(cell4);
				
				cell4 = new PdfPCell(new Paragraph(traTen.toUpperCase(), font9bold));
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
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;
				
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
				
				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang = nhanNganHang + " - " + nhanChiNhanh;
				
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
				cell3 = new PdfPCell(new Paragraph(sotientt, font9bold));
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
				cell3 = new PdfPCell(new Paragraph(tienbangchu, font9bold));
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
				cell3 = new PdfPCell(new Paragraph("K??? TO??N TR?????NG K??", font9bold));
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
			else if(traNganHangMa.equals("ACB")) //?? CH??U
			{
				/*//Logo ACB
		        Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/images/acb.png");
				hinhanh.scalePercent(75);
				hinhanh.setAbsolutePosition(1.0f * CONVERT, document.getPageSize().getHeight() - 2.0f * CONVERT);
				document.add(hinhanh);*/
								
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				//HEADER + CONTENT
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorder(0);
				
				PdfPTable table2 = new PdfPTable(TABLE_ACB_WIDTHS.length);	// 4 c???t
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				table2.setWidths(TABLE_ACB_WIDTHS);
				table2.setWidthPercentage(100.0f);
				
				// 1 d??ng
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI", font13bold)); 
				p.add(new Chunk("/PAYMENT ORDER", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length); 
				cell2.addElement(p);
				table2.addCell(cell2);
				
				String NGN = "";
				if(ngayghinhan.trim().length() > 0 )
				{
					String [] ngayHD = ngayghinhan.split("-");		
					NGN = ngayHD[2] +" / " + ngayHD[1] + " / " + ngayHD[0];
				}
				
				// 1 d??ng
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng??y", font11bold)); 
				p.add(new Chunk("/Date", font10italic));
				p.add(new Chunk("  " + NGN, font10));
				cell2.addElement(p);
				//p.setAlignment(Element.ALIGN_CENTER);
				//cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				//cell2.setBorderWidth(1);
				cell2.setColspan(TABLE_ACB_WIDTHS.length - 2);
				cell2.setPaddingLeft(7.5f * CONVERT);				
				table2.addCell(cell2);
				
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S???/ ", font10bold)); 
				p.add(new Chunk("No.", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setColspan(2);
				cell2.setBorder(0);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				//////////////////				
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????N V??? TR??? TI???N", font11bold)); 
				p.add(new Chunk("/APPLICANT", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.setBorderWidth(0);
				cell2.setBorderWidthTop(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				/////////////
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??n t??i kho???n", font11bold)); 
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
				p.add(new Chunk("S??? t??i kho???n", font11bold)); 
				p.add(new Chunk("/Acct No.", font10italic));
				p.add(new Chunk("  " + traStk, font12bold));
				p.add(new Chunk("       T???i ng??n h??ng ?? Ch??u _ chi nh??nh", font11bold)); 
				p.add(new Chunk("/At ACB _ Branch: "+traChiNhanh, font10italic)); 
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
				p.add(new Chunk("????N V??? TH??? H?????NG", font11bold)); 
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
				p.add(new Chunk("????n v??? th??? h?????ng", font11bold)); 
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
				p.add(new Chunk("S??? t??i kho???n", font11bold)); 
				p.add(new Chunk("/Acct No.", font10italic));
				p.add(new Chunk("               " + nhanStk, font10bold));
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
				p.add(new Chunk("CMND/H??? chi???u", font11bold)); 
				p.add(new Chunk("/ID CARD/PP No.", font10italic));
				p.add(new Chunk("           	       		 ", font10bold));
				p.add(new Chunk("Ng??y c???p", font10bold));
				p.add(new Chunk("/Date", font10italic));
				p.add(new Chunk("           			                     ", font10bold));
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
				p.add(new Chunk("T???i Ng??n h??ng", font11bold)); 
				p.add(new Chunk("/Beneficiary's Bank ", font10italic));
				p.add(new Chunk(nhanNganHang, font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				//cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???nh, TP: "+nhanChiNhanh, font11bold)); 
				p.add(new Chunk("/Province, City ", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(0);
				//cell2.setBorderWidthBottom(1);
				cell2.setColspan(TABLE_ACB_WIDTHS.length-1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				
				//Column1 
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
								
				PdfPTable table131 = new PdfPTable(4);	
				table131.setHorizontalAlignment(Element.ALIGN_CENTER);
				table131.setWidths(new float[] {2.5f * CONVERT, 2.5f * CONVERT ,  2.5f * CONVERT , 8.0f*CONVERT });
				table131.setWidthPercentage(100.0f);
							
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ti???n b???ng s??? \n", font10bold)); 
				p.add(new Chunk("Amount in figures", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				cell3.addElement(p);
				table131.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(sotientt, font11bold));
				p.add(new Chunk(" VN??", font9bold)); 
				p.setAlignment(Element.ALIGN_RIGHT);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setPaddingRight(0.2f * CONVERT);
				cell3.setBorder(0);
				cell3.addElement(p);
				cell3.setBackgroundColor(new BaseColor(219, 229, 241));
				table131.addCell(cell3);
								
				cell3 = new PdfPCell();				
				p = new Paragraph();
				p.add(new Chunk("B???ng ch???: \n", font10bold)); 
				p.add(new Chunk("/Amount in words ", font10italic));;
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.addElement(p);
				table131.addCell(cell3);
				
				cell3 = new PdfPCell();				
				p = new Paragraph();
				p.add(new Chunk(tienbangchu +" \n", font11)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.addElement(p);
				table131.addCell(cell3);
								
				cell2.addElement(table131);
				table2.addCell(cell2);	
			
				/////
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("N???i dung", font11bold)); 
				p.add(new Chunk("/Details     ", font10italic));
				p.add(new Chunk(noidungtt, font12));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				/////
				
			/*	cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ph?? chuy???n ti???n do/", font9bold)); 
				p.add(new Chunk("/Transfer fee to be paid by																	", font9italic));
				p.add(new Chunk("????n v??? tr??? ti???n ch???u ph??/", font9bold)); 
				p.add(new Chunk("/The applicant																			", font9italic));
				p.add(new Chunk("????n v??? th??? h?????ng ch???u ph??/", font9bold)); 
				p.add(new Chunk("/The beneficiary ", font9italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setColspan(TABLE_ACB_WIDTHS.length);
				cell2.addElement(p);
				table2.addCell(cell2);*/
				
				cell.addElement(table2);
				table.addCell(cell);
				
				////
				
				//FOOTER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				//cell.setBorderWidthTop(1);
				
				table2 = new PdfPTable(3);	
				table2.setHorizontalAlignment(Element.ALIGN_CENTER);
				float columnSize = 18.0f / 3 * CONVERT;
				table2.setWidths(new float[] {columnSize, columnSize, columnSize});
				table2.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ph?? chuy???n ti???n do/", font9bold)); 
				p.add(new Chunk("/Transfer fee to be paid by", font9italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????n v??? tr??? ti???n ch???u ph??/", font9bold)); 
				p.add(new Chunk("/The applicant	", font9italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????n v??? th??? h?????ng ch???u ph??/", font9bold)); 
				p.add(new Chunk("/The beneficiary ", font9italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorder(0);
				cell2.setBorderWidthBottom(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????N V??? TR??? TI???N", font11bold)); 
				p.add(new Chunk("/Application", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("NG??N H??NG ?? CH??U", font11bold)); 
				p.add(new Chunk("/ACB", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(1);
				cell2.addElement(p);
				table2.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("NG??N H??NG B", font11bold)); 
				p.add(new Chunk("/B Bank", font10italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setBorderWidth(1);
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
				
				cell3 = new PdfPCell(new Paragraph("K??? to??n tr?????ng", font11bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Ch??? t??i kho???n", font11bold));
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
				p.add(new Chunk("Ghi s??? ng??y", font11bold)); 
				p.add(new Chunk("/Post date.............", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Giao d???ch vi??n", font11bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Ki???m so??t vi??n", font11bold));
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
				p.add(new Chunk("Ghi s??? ng??y", font11bold)); 
				p.add(new Chunk("/Post date.............", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Giao d???ch vi??n", font11bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Ki???m so??t vi??n", font11bold));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Teller", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table3.addCell(cell3);
				
				cell3 = new PdfPCell(new Paragraph("Dupervisor", font10italic));
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setFixedHeight(1.5f * CONVERT);
				table3.addCell(cell3);
				
				cell2.addElement(table3);
				table2.addCell(cell2);
				
				cell.addElement(table2);
				table.addCell(cell);
				
				//FOOTER_IMG
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(0);		
				
				p = new Paragraph();
				p.add(new Chunk("	Contact Center 24/7: 1900 54 54 86 -(08) 38 247 247 | acb.com.vn | ", font8)); 
				cell.addElement(p);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_TOP);
				cell.setBorder(0);
				cell.setPaddingLeft(7.0f*CONVERT);
				
				table.addCell(cell);
				
				//Logo ACB
		        Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/images/acb.png");
				hinhanh.scalePercent(60);
				hinhanh.setAbsolutePosition(17.8f * CONVERT, document.getPageSize().getHeight() - 14.5f * CONVERT);
				document.add(hinhanh);
				
				document.add(table);
				
			}
			else if (traNganHangMa.equals("EXIMBANK")){
				//Logo EIB VIETINBANK
		        Image hinhanh = Image.getInstance( getServletContext().getInitParameter("path")+"/images/EXIMBANK.png");
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
				cell.setBorderWidth(0);
				
				PdfPTable table1 = new PdfPTable(TABLE_VIETINKBANK_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_VIETINKBANK_WIDTHS);
				table1.setWidthPercentage(100.0f);
								
				Paragraph p = new Paragraph();
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI \n", font14bold)); 
				p.add(new Chunk("PAYMENT ORDER", font11bold)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setBorder(0);
				cell2.setColspan(2);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				String NGN = "";
				if(ngayghinhan.trim().length() > 0 )
				{
					String [] ngayHD = ngayghinhan.split("-");		
					NGN = ngayHD[2] +" / " + ngayHD[1] + " / " + ngayHD[0];
				}	
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ", font10bold)); 
				p.add(new Chunk("/No \n", font8italic)); 
				p.add(new Chunk("Ng??y ", font10bold)); 
				p.add(new Chunk("Date: ", font8italic)); 
				p.add(new Chunk(NGN, font11italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setBorder(0);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("CHUY???N KHO???N ", font8bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				//cell2.setPaddingTop(-0.2f*CONVERT);
				cell2.setBorder(0);
				cell2.setColspan(3);
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
				float[] withs2 = {100f, 200f};
				table2.setWidths(withs2);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("????n v??? tr??? ti???n \n" , font9bold)); 		
				//p.add(new Chunk("Orderer???s Name ", font8italic));  
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setPaddingTop(-0.1f*CONVERT);
				table2.addCell(cell3);				
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traTen , font10bold)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				cell3.setRowspan(2);
				table2.addCell(cell3);
				
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				//p.add(new Chunk("????n v??? tr??? ti???n \n" , font9bold)); 		
				p.add(new Chunk("Orderer???s Name ", font8italic));  
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setPaddingTop(-0.3f*CONVERT);
				table2.addCell(cell3);				
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n \n" , font9bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traStk , font10bold)); 	 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setRowspan(2);
				cell3.setBorder(0);
				table2.addCell(cell3);
			
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				//p.add(new Chunk("S??? t??i kho???n \n" , font9bold)); 	
				p.add(new Chunk("Account number" , font8italic)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.3f*CONVERT);
				cell3.setBorder(0);
				
				table2.addCell(cell3);
								
				/*if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;*/
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???i Ng??n h??ng Xu???t Nh???p Kh???u Vi???t Nam - SGD/Chi nh??nh/PGD: " , font10bold)); 	
				p.add(new Chunk(traChiNhanh , font10)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				cell3.setColspan(2);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("With VIETNAM EXPORT IMPORT BANK - M.T.O/Branch/T.O" , font8italic));  	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setColspan(2);
				cell3.setPaddingTop(-0.3f*CONVERT);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("????n v??? nh???n ti???n \n" , font10bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(nhanTen , font10bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setRowspan(2);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph(); 	
				p.add(new Chunk("Beneficiary???s Name" , font7italic)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setPaddingTop(-0.3f*CONVERT);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n " , font10bold)); 				
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setPaddingTop(-0.1f*CONVERT);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(traStk , font10bold)); 	 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setRowspan(2);
				cell3.setPaddingTop(-0.1f*CONVERT);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("Account No." , font8italic)); 				
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.3f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("T???i ng??n h??ng " , font10bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(traNganHang , font10bold)); 	 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setRowspan(2);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("With Bank " , font8italic)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.3f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("T???nh/TP: " , font10bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("" , font10bold)); 	 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setRowspan(2);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("Province/City " , font8italic)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.3f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				

				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ti???n b???ng ch???" , font10bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(tienbangchu , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_TOP);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.1f*CONVERT);
				cell3.setBorder(0);
				cell3.setRowspan(2);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Amount in words " , font7italic)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setPaddingTop(-0.3f*CONVERT);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
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
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(3.3f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);
								
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "CMND/H??? chi???u: " , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);			
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "I.D No./PP No. " , font7italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.3f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ng??y c???p " , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);	
				cell4.setPaddingTop(-0.1f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Issued date " , font7italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.3f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "N??i c???p " , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);	
				cell4.setPaddingTop(-0.1f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Issued place " , font7italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.3f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "S??? ti???n b???ng s???" , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.1f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Amount in figures" , font7italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.3f*CONVERT);
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( DinhDangTraphacoERP(sotientt)+" ?????ng" , font11bold); 
				p.setAlignment(Element.ALIGN_RIGHT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.1f*CONVERT);
				cell4.addElement(p);
				cell4.setBorderWidth(1);
				table3.addCell(cell4);
								
				cell2.addElement(table3);
				
				table1.addCell(cell2);	
				
				cell.addElement(table1);
				
				table.addCell(cell);
				
				document.add(table);	
				
				PdfPTable tablesign = new PdfPTable(2);	
				float[] widthsign = {90f, 300f };
				tablesign.setHorizontalAlignment(Element.ALIGN_LEFT);
				tablesign.setWidths(widthsign);
				tablesign.setWidthPercentage(100);	
								
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "N???i dung:" , font10bold));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				tablesign.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( noidungtt , font10));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setRowspan(2);
				tablesign.addCell(cell);				

				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "Details" , font7italic));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				tablesign.addCell(cell);
				
				document.add(tablesign);
				
				
				PdfPTable tablesign1 = new PdfPTable(6);	
				float[] widthsign1 = {150f, 100f, 150f, 150f, 150f, 150f };
				tablesign1.setHorizontalAlignment(Element.ALIGN_LEFT);
				tablesign1.setWidths(widthsign1);
				tablesign1.setWidthPercentage(100);	
								
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "??V tr??? ti???n ng??y ??????." , font9bold));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthTop(1);
				cell.setBorderWidthRight(1);
				cell.setBorderWidthLeft(1);
				cell.setColspan(2);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "NH A (Eximbank)" , font9bold));
				p.add(new Chunk( "(Eximbank) " , font9italic));
				p.add(new Chunk( "ghi s??? ng??y   ??????" , font9bold));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setColspan(2);
				cell.setBorderWidthTop(1);
				cell.setBorderWidthRight(1);
				tablesign1.addCell(cell);				

				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "NH B " , font9bold));
				p.add(new Chunk( "(Beneficiary???s Bank)" , font9italic));
				p.add(new Chunk( " ghi s??? ng??y ?????????" , font9bold));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthTop(1);
				cell.setBorderWidthRight(1);
				cell.setColspan(2);
				tablesign1.addCell(cell);	
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "K??? to??n tr?????ng" , font9bold));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthLeft(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "Ch??? t??i kho???n" , font9bold));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);				
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "Giao d???ch vi??n" , font9bold));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "Ki???m so??t" , font9bold));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				cell.addElement(p);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "Giao d???ch vi??n" , font9bold));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.setBorder(0);
				cell.addElement(p);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				p = new Paragraph();
				p.add(new Chunk( "Ki???m so??t" , font9bold));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				p = new Paragraph();
				p.add(new Chunk( "Chief Accountant" , font7italic));
				p.setAlignment(Element.ALIGN_LEFT);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setFixedHeight(2.0f*CONVERT);
				cell.setBorderWidthLeft(1);
				cell.setBorderWidthBottom(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_TOP);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				p = new Paragraph();
				p.add(new Chunk( "Account holder" , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				cell.setBorderWidthBottom(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_TOP);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				p = new Paragraph();
				p.add(new Chunk( "Teller " , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthBottom(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_TOP);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				p = new Paragraph();
				p.add(new Chunk( "Verifier " , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				cell.setBorderWidthBottom(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_TOP);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				p = new Paragraph();
				p.add(new Chunk( "Teller " , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthBottom(1);
				tablesign1.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_TOP);
				cell.setVerticalAlignment(Element.ALIGN_TOP);				
				p = new Paragraph();
				p.add(new Chunk( "Verifier " , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);	
				cell.setPaddingTop(-0.2f*CONVERT);
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthRight(1);
				cell.setBorderWidthBottom(1);
				tablesign1.addCell(cell);
				
				document.add(tablesign1);
				
				
			}
			else if (traNganHangMa.equals("SACOMBANK")){
				//Logo EIB SACOMBANK
		        Image hinhanh = Image.getInstance( getServletContext().getInitParameter("path")+"/images/SACOMBANK.png");
				hinhanh.scalePercent(50);
				hinhanh.setAbsolutePosition(16.0f * CONVERT, document.getPageSize().getHeight() - 2.0f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				//HEADER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				
				PdfPTable table1 = new PdfPTable(TABLE_SCBANK_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_SCBANK_WIDTHS);
				table1.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI \n", font14bold)); 
				p.add(new Chunk("PAYMENT ORDER", font11)); 
				p.setAlignment(Element.ALIGN_RIGHT);
				//cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.3f * CONVERT);
				cell2.setPaddingRight(5.5f*CONVERT);
				cell2.setBorder(0);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				String NGN = "";
				if(ngayghinhan.trim().length() > 0 )
				{
					String [] ngayHD = ngayghinhan.split("-");		
					NGN = ngayHD[2] +" / " + ngayHD[1] + " / " + ngayHD[0];
				}	
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng??y / Date \n", font10));							
				p.add(new Chunk(NGN, font10));
				
				p.setAlignment(Element.ALIGN_LEFT);
				//cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.5f * CONVERT);
				cell2.setPaddingLeft(-1.5f*CONVERT);
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
				PdfPTable table2 = new PdfPTable(1);	
				table2.setWidthPercentage(100);
				float[] withs2 = {200f};
				table2.setWidths(withs2);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("C?? nh??n/????n v??? chuy???n ti???n " , font10)); 		
				p.add(new Chunk("/Remitter: "+ traTen, font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk("T??i kho???n s??? " , font10)); 	
				p.add(new Chunk("/Account No. " , font10italic)); 
				p.add(new Chunk ( traStk +" \t \t ", font10bold)); 
				p.add(new Chunk("T???i chi nh??nh/PGD/" , font10)); 
				p.add(new Chunk("/At Branch: " , font10italic));
				p.add(new Chunk(traNganHang , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
								
				cell3 = new PdfPCell();				
				p = new Paragraph();
				p.add(new Chunk("C?? nh??n/????n v??? th??? h?????ng" , font10)); 
				p.add(new Chunk("/Beneficiary: ", font10italic)); 	
				p.add(new Chunk("\t "+nhanTen , font10bold)); 			
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
												
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk( "T??i kho???n s??? " , font10)); 
				p.add(new Chunk( "/Account No. " , font10italic));
				p.add(new Chunk( ""+nhanStk , font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);				
				
				cell3 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "S??? CMND" , font10)); 
				p.add(new Chunk( "/HC/ID/PP No. \t \t \t  \t \t \t \t \t \t \t \t \t" , font10italic));
				p.add(new Chunk( "Ng??y c???p" , font10)); 
				p.add(new Chunk( "/Date \t \t \t  \t \t \t \t \t \t" , font10italic));
				p.add(new Chunk( "N??i c???p" , font10)); 
				p.add(new Chunk( "/Place \t \t \t  \t \t \t \t \t \t" , font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				

				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang = nhanNganHang +" - "+ nhanChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "T???i ng??n h??ng" , font10)); 
				p.add(new Chunk( "/Beneficiary bank: \t ", font10italic));
				p.add(new Chunk( nhanNganHang +" \t \t \t \t \t \t  \t \t \t \t \t \t" , font10)); 
				p.add(new Chunk( "T???nh/TP" , font10)); 
				p.add(new Chunk( "/Province/City ", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);			
																
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "S??? ti???n b???ng ch???" , font10)); 
				p.add(new Chunk( "/Amount in words: " , font10italic)); 
				p.add(new Chunk( tienbangchu +"./", font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "N???i dung" , font10)); 
				p.add(new Chunk( "/Narrative: " , font10italic)); 
				p.add(new Chunk( noidungtt , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
							
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
				p = new Paragraph(); 
				p.add(new Chunk( "S??? GD" , font10)); 
				p.add(new Chunk( "/Ref No. " , font10italic));
				p.setAlignment(Element.ALIGN_LEFT);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "M?? GDV" , font10)); 
				p.add(new Chunk( "/Teller ID" , font10italic));
				p.setAlignment(Element.ALIGN_LEFT);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "PH???N DO NG??N H??NG GHI \n" , font8bold)); 
				p.add(new Chunk( "Bank use only" , font8italic)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthTop(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "T??I KHO???N N???" , font8)); 
				p.add(new Chunk( "/Debit account" , font8italic)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthTop(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "T??I KHO???N C??" , font8)); 
				p.add(new Chunk( "/Credit account" , font8italic)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthTop(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				cell4.setFixedHeight(0.2f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "S??? TI???N B???NG S??? \n" , font8bold)); 
				p.add(new Chunk( "Amount in figures" , font8italic));
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.addElement(p);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthBottom(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( DinhDangTraphacoERP(sotientt)+" ?????ng" , font11bold); 
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
				p = new Paragraph( ); 
				p.add(new Chunk( "C?? NH??N/????N V??? CHUY???N " , font8bold)); 
				p.add(new Chunk( "/Remitter" , font8bi));				
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "SACOMBANK \n" , font8bold)); 
				p.add(new Chunk( "Ghi s??? ng??y" , font8));	
				p.add(new Chunk( "/Settlement date" , font8));	
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "NG??N H??NG B " , font8bold)); 
				p.add(new Chunk( "/Beneficiary bank \n" , font8bi));	
				p.add(new Chunk( "Ghi s??? ng??y" , font8));
				p.add(new Chunk( "/Settlement date" , font8italic));
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n tr?????ng " , font7)); 
				p.add(new Chunk( "/Account \t \t \t \t \t \t \t \t \t \t " , font7italic));
				p.add(new Chunk( "Ch??? t??i kho???n " , font7));
				p.add(new Chunk( "/Account holder " , font7italic));		
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
												
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n " , font7)); 
				p.add(new Chunk( "/Account \t \t \t \t \t \t \t \t \t \t " , font7italic));
				p.add(new Chunk( "Tr?????ng ????n v??? " , font7));
				p.add(new Chunk( "/Approved by " , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n " , font7)); 
				p.add(new Chunk( "/Account \t \t \t \t \t \t \t \t \t \t " , font7italic));
				p.add(new Chunk( "Tr?????ng ????n v??? " , font7));
				p.add(new Chunk( "/Approved by " , font7italic));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(3.0f * CONVERT);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				
				cell2.addElement(table4);
				
				table1.addCell(cell2);				
				cell.addElement(table1);
				table.addCell(cell);
				
				document.add(table);				
				
			}
			else if (traNganHangMa.equals("ANBINH")){
				//Logo EIB SACOMBANK
		        Image hinhanh = Image.getInstance( getServletContext().getInitParameter("path")+"/images/ABBANK.png");
				hinhanh.scalePercent(60);
				hinhanh.setAbsolutePosition(0.5f * CONVERT, document.getPageSize().getHeight() - 1.5f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				//HEADER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				
				PdfPTable table1 = new PdfPTable(TABLE_ABBBANK_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_ABBBANK_WIDTHS);
				table1.setWidthPercentage(100.0f);				

				String NGN = "";
				if(ngayghinhan.trim().length() > 0 )
				{
					String [] ngayHD = ngayghinhan.split("-");		
					NGN = ngayHD[2] +" / " + ngayHD[1] + " / " + ngayHD[0];
				}	
				
				
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI (Payment Order) \n", font12bold)); 
				p.add(new Chunk("Ng??y ", font10)); 
				p.add(new Chunk("(Date): ", font10italic)); 
				p.add(new Chunk(NGN, font10)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.0f * CONVERT);
				//cell2.setPaddingRight(5.5f*CONVERT);
				cell2.setBorder(0);
				cell2.setColspan(2);
				cell2.setRowspan(2);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("M?? GDV ", font8));							
				p.add(new Chunk("(Teller ID):????????????????????????", font8italic));
				
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setBorder(0);
				cell2.addElement(p);
				table1.addCell(cell2);	
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? b??t to??n ", font8));							
				p.add(new Chunk("(Transaction No):?????????.", font8italic));
				
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setBorder(0);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				
				//C???T 1
				cell2 = new PdfPCell();
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_TOP);
				cell2.setBorderWidth(0);
				
				//B???NG L???NG
				PdfPTable table2 = new PdfPTable(1);	
				table2.setWidthPercentage(100);
				float[] withs2 = {200f};
				table2.setWidths(withs2);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng?????i chuy???n " , font10)); 		
				p.add(new Chunk(" (Ordering name): ", font10italic)); 
				p.add(new Chunk(traTen, font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("?????a ch??? " , font10)); 		
				p.add(new Chunk("(Address): "+ traTen, font10italic)); 
				p.add(new Chunk(traTen, font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk("S??? t??i kho???n " , font10)); 	
				p.add(new Chunk("(A/C No): " , font10italic)); 
				p.add(new Chunk ( traStk +" \t \t ", font10bold)); 
				p.add(new Chunk("T???i " , font10)); 
				p.add(new Chunk("(at): " , font10italic));
				p.add(new Chunk(traNganHang , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setBackgroundColor(new BaseColor(240,255,255));
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??n t??i kho???n " , font10)); 		
				p.add(new Chunk(" (A/C name): "+ traTen, font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setBackgroundColor(new BaseColor(240,255,255));
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng??y chuy???n " , font10)); 		
				p.add(new Chunk(" (Value Day): ", font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setBackgroundColor(new BaseColor(240,255,255));
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("L???ai ti???n " , font10)); 		
				p.add(new Chunk(" (currency): VND", font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setBackgroundColor(new BaseColor(240,255,255));
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ti???n " , font10)); 		
				p.add(new Chunk(" (Amount):" +DinhDangTraphacoERP(sotientt), font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setBackgroundColor(new BaseColor(240,255,255));
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("B???ng ch??? " , font10)); 		
				p.add(new Chunk("(In words):" + tienbangchu, font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				cell3.setBackgroundColor(new BaseColor(240,255,255));
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??? gi?? " , font10)); 		
				p.add(new Chunk("(Exchange rate):", font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ti???n quy ?????i " , font10)); 		
				p.add(new Chunk(" (Coversion amount):", font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng?????i th??? h?????ng " , font10bold)); 		
				p.add(new Chunk(" (Beneficiary): ", font10italic)); 
				p.add(new Chunk(nhanTen, font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? CMND/H??? chi???u " , font10)); 		
				p.add(new Chunk(" (ID/Passport): ", font10italic)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Ng??y c???p " , font10)); 		
				p.add(new Chunk("(Issued Date): \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ", font10italic)); 
				p.add(new Chunk("N??i c???p " , font10)); 		
				p.add(new Chunk("(Inssued place): \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ", font10italic));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n " , font10)); 		
				p.add(new Chunk("(Beneficiary A/c): ", font10italic)); 
				p.add(new Chunk(nhanStk, font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang = nhanNganHang +" - "+ nhanChiNhanh;
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???i ng??n h??ng " , font10)); 		
				p.add(new Chunk("(Beneficiary's Bank): ", font10italic)); 
				p.add(new Chunk(nhanNganHang, font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);		
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
				p.setAlignment(Element.ALIGN_CENTER);		
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(5.5f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);				
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ph?? chuy???n ti???n" , font9)); 
				p.add(new Chunk( "(Bank charges):" , font9italic)); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				cell4.setBackgroundColor(new BaseColor(255,250,205));
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ng?????i chuy???n " , font9)); 
				p.add(new Chunk( "(Our) \t\t\t\t\t\t\t " , font9italic)); 
				p.add(new Chunk( "Ng?????i nh???n " , font9));
				p.add(new Chunk( "(Beneficiary) " , font9italic)); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "N???i dung " , font9)); 
				p.add(new Chunk( "(Payment details):" , font9italic)); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( noidungtt , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setBorder(0);
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
				p = new Paragraph( ); 
				p.add(new Chunk( "Ph???n d??nh cho ABBANK ghi ng??y chuy???n ti???n " , font8bold)); 
				p.add(new Chunk( " (Transfer date):" , font8bi));				
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setColspan(3);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n tr?????ng \t \t \t \t \t \t \t \t \t \t" , font8bold)); 
				p.add(new Chunk( "Ch??? t??i kho???n ( Account holder )" , font8bold));		
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
								
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Giao d???ch vi??n \n" , font8bold)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(1.0f * CONVERT);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ki???m so??t \t \t \t \t \t \t \t \t \t \t  \t \t \t \t \t  \t \t \t \t \t" , font8bold)); 
				p.add(new Chunk( "G??/Tr?????ng ph??ng " , font8bold));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(1.0f * CONVERT);
				cell5.setBorderWidth(1);
				table4.addCell(cell5);
				
				
				cell2.addElement(table4);
				
				table1.addCell(cell2);				
				cell.addElement(table1);
				table.addCell(cell);
				
				document.add(table);				
				
			}
			else if (traNganHangMa.equals("DONGA")){
				//Logo EIB SACOMBANK
		        Image hinhanh = Image.getInstance( getServletContext().getInitParameter("path")+"/images/DONGA.jpg");
				hinhanh.scalePercent(50);
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
				cell.setBorderWidth(0);
				
				PdfPTable table1 = new PdfPTable(TABLE_DABANK_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_DABANK_WIDTHS);
				table1.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("???Y NHI???M CHI \n", font14bold)); 
				p.add(new Chunk("Chuy???n kh???an, chuy???n ti???n th??, ??i???n", font11)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.3f * CONVERT);
				/*cell2.setPaddingRight(5.5f*CONVERT);*/
				cell2.setBorder(0);
				cell2.setColspan(2);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				String NGN = "";
				if(ngayghinhan.trim().length() > 0 )
				{
					String [] ngayHD = ngayghinhan.split("-");		
					NGN = ngayHD[2] +" / " + ngayHD[1] + " / " + ngayHD[0];
				}	
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? \n", font10));
				p.add(new Chunk("Ng??y: " + NGN, font10));
				p.setAlignment(Element.ALIGN_LEFT);
				//cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
				//cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell2.setFixedHeight(1.5f * CONVERT);
				//scell2.setPaddingLeft(-1.5f*CONVERT);
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
				float[] withs2 = {100f, 200f};
				table2.setWidths(withs2);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??n ????n v??? tr??? ti???n " , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traTen , font10bold)); 		 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??i kh???an s???: " , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traStk , font10bold)); 		 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???i ng??n h??ng: " , font10)); 		 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traNganHang + " \t \t \t \t \t \t  \t \t \t \t \t \t" , font10bold)); 
				p.add(new Chunk( "T???nh/TP" , font10)); 	 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("?????a ch??? : " , font10)); 		 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("" , font10)); 		 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
								
				cell3 = new PdfPCell();				
				p = new Paragraph();
				p.add(new Chunk("T??n ????n v??? nh???n ti???n: " , font10)); 			
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();				
				p = new Paragraph();	
				p.add(new Chunk(nhanTen , font10bold)); 			
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
												
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk( "T??i kho???n s???: " , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);	
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk( nhanStk , font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);		
								
				if(nhanChiNhanh.trim().length()>0)
					nhanNganHang = nhanNganHang +" - "+ nhanChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "T???i ng??n h??ng: " , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);	
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( nhanNganHang +" \t \t \t \t \t \t  \t \t \t \t \t \t" , font10bold)); 
				p.add(new Chunk( "T???nh/TP" , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);	
				
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "?????a ch???: " , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);	
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk("" , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
																				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "S??? ti???n b???ng ch???: " , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( tienbangchu +"./", font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( "N???i dung" , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph(); 
				p.add(new Chunk( noidungtt , font10)); 
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
							
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
				p = new Paragraph( ); 
				p.add(new Chunk( "PH???N DO NG??N H??NG GHI \n" , font8bold)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "T??i kh???an ghi N???" , font10)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setBackgroundColor(new BaseColor(220,220,220));
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthTop(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "" , font8)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setFixedHeight(0.7f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				table3.addCell(cell4);
				
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "T??i kh???an C??" , font10)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBackgroundColor(new BaseColor(220,220,220));
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthTop(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "" , font8)); 
				p.setAlignment(Element.ALIGN_CENTER);				
				cell4.addElement(p);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setFixedHeight(0.7f*CONVERT);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "S??? TI???N B???NG S??? \n" , font8bold)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBackgroundColor(new BaseColor(220,220,220));
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.addElement(p);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( DinhDangTraphacoERP(sotientt)+" VND" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.addElement(p);
				cell4.setBorderWidthTop(0);
				cell4.setBorderWidthLeft(1);
				cell4.setBorderWidthRight(1);
				cell4.setBorderWidthBottom(1);
				
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
				p = new Paragraph( ); 
				p.add(new Chunk( "????n v??? tr??? ti???n " , font10bold)); 			
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthTop(1);
				table4.addCell(cell5);
				
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ng??n h??ng A \n " , font10bold)); 
				p.add(new Chunk( "Ghi s??? ng??y:???????????????." , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthTop(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ng??n h??ng B \n" , font10bold)); 
				p.add(new Chunk( "Ghi s??? ng??y:???????????????." , font10bold)); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthTop(1);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t" , font10bold)); 
				p.add(new Chunk( "Ch??? t??i kho???n " , font10bold));		
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthBottom(1);
				table4.addCell(cell5);
												
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t" , font10bold)); 
				p.add(new Chunk( "Tr?????ng ph??ng k??? to??n " , font10bold));
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(3.0f * CONVERT);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthBottom(1);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t" , font10bold)); 
				p.add(new Chunk( "Tr?????ng ph??ng k??? to??n " , font10bold));
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setFixedHeight(3.0f * CONVERT);
				cell5.setBorder(0);
				cell5.setBorderWidthLeft(1);
				cell5.setBorderWidthBottom(1);
				cell5.setBorderWidthRight(1);
				table4.addCell(cell5);
				
				
				cell2.addElement(table4);
				
				table1.addCell(cell2);				
				cell.addElement(table1);
				table.addCell(cell);
				
				document.add(table);				
				
			}
			else if (traNganHangMa.equals("VIETINBANK")){
				//Logo EIB VIETINBANK
		        Image hinhanh = Image.getInstance( getServletContext().getInitParameter("path")+"/images/VIETINKBANK.png");
				hinhanh.scalePercent(70);
				hinhanh.setAbsolutePosition(16.0f * CONVERT, document.getPageSize().getHeight() - 2.0f * CONVERT);
				document.add(hinhanh);
				
				PdfPTable table = new PdfPTable(1);	
				table.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.setWidths(TABLE_WIDTHS);
				table.setWidthPercentage(100.0f);
				
				//HEADER
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setBorderWidth(0);
				
				PdfPTable table1 = new PdfPTable(TABLE_VIETINKBANK_WIDTHS.length);	
				table1.setHorizontalAlignment(Element.ALIGN_CENTER);
				table1.setWidths(TABLE_VIETINKBANK_WIDTHS);
				table1.setWidthPercentage(100.0f);
				
				cell2 = new PdfPCell();
				Paragraph p = new Paragraph();
				p.add(new Chunk("Ng??n H??ng TMCP C??ng Th????ng Vi???t Nam", font10bold));
				p.setAlignment(Element.ALIGN_LEFT);
				cell2.setBorder(0);
				cell2.setColspan(3);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("L???nh Chi \n", font14bold)); 
				p.add(new Chunk("Payment Order", font11)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell2.setBorder(0);
				cell2.setColspan(3);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				String NGN = "";
				if(ngayghinhan.trim().length() > 0 )
				{
					String [] ngayHD = ngayghinhan.split("-");		
					NGN = ngayHD[2] +" / " + ngayHD[1] + " / " + ngayHD[0];
				}	
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ", font11)); 
				p.add(new Chunk("No \t \t \t \t ", font11italic)); 
				p.add(new Chunk("Ng??y ", font11)); 
				p.add(new Chunk("Date ", font11italic)); 
				p.add(new Chunk(NGN, font11italic)); 
				p.setAlignment(Element.ALIGN_RIGHT);
				cell2.setBorder(0);
				cell2.setColspan(3);
				cell2.addElement(p);
				table1.addCell(cell2);
				
				cell2 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("Li??n 2 D??nh cho kh??ch h??ng Copy 2 Customer's Copy ", font8bold)); 
				cell2.setBackgroundColor(new BaseColor(220,220,220));
				p.setAlignment(Element.ALIGN_RIGHT);
				cell2.setPaddingTop(-0.2f*CONVERT);
				cell2.setBorder(0);
				cell2.setColspan(3);
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
				float[] withs2 = {100f, 200f};
				table2.setWidths(withs2);
								
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T??n ng?????i tr??? ti???n " , font10bold)); 		
				p.add(new Chunk("Payer ", font10));  
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);				
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traTen , font10bold)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n " , font10bold)); 	
				p.add(new Chunk("A/C number" , font10)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traStk , font10bold)); 	 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("T???i ng??n h??ng " , font10bold)); 	
				p.add(new Chunk("With Bank" , font10)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				if(traChiNhanh.trim().length()>0)
					traNganHang = traNganHang + " - "+ traChiNhanh;
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk(traNganHang , font10bold));  	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph();
				p.add(new Chunk("S??? ti???n b???ng ch??? \n" , font10bold)); 	
				p.add(new Chunk("Amount in words" , font10)); 	
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
								
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(tienbangchu , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("T??n ng?????i nh???n ti???n " , font10bold)); 	
				p.add(new Chunk("Payee" , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(nhanTen , font10bold)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("S??? t??i kho???n " , font10bold)); 		
				p.add(new Chunk("A/C number" , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(traStk , font10bold)); 	 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("T???i ng??n h??ng " , font10bold)); 		
				p.add(new Chunk("With Bank" , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(traNganHang , font10bold)); 	 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk("N???i dung " , font10bold)); 		
				p.add(new Chunk("Remarks " , font10)); 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);
				
				cell3 = new PdfPCell(); 
				p = new Paragraph();
				p.add(new Chunk(noidungtt , font10bold)); 	 		
				p.setAlignment(Element.ALIGN_LEFT);
				cell3.addElement(p);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_TOP);
				cell3.setBorder(0);
				table2.addCell(cell3);	
							
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
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setFixedHeight(1.5f*CONVERT);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "S??? TI???N B???NG S??? Amount in figures" , font8)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setPaddingTop(-0.2f*CONVERT);
				cell4.setBackgroundColor(new BaseColor(220,220,220));
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
				
				cell4 = new PdfPCell();
				p = new Paragraph( DinhDangTraphacoERP(sotientt)+" VND" , font11bold); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell4.setFixedHeight(1.0f*CONVERT);
				cell4.setVerticalAlignment(Element.ALIGN_TOP);
				cell4.setBackgroundColor(new BaseColor(220,220,220));
				cell4.addElement(p);
				cell4.setBorder(0);
				table3.addCell(cell4);
								
				cell2.addElement(table3);
				
				table1.addCell(cell2);	
				
				cell2 = new PdfPCell();
				cell2.setBackgroundColor(new BaseColor(220,220,220));
				cell2.setFixedHeight(0.2f*CONVERT);
				cell2.setBorder(0);
				cell2.setColspan(3);
				cell2.addElement(p);
				table1.addCell(cell2);
								
				
				//FOOTER
				cell2 = new PdfPCell();
				cell2.setColspan(4);
				cell2.setBorder(0);	
				
				PdfPTable table4 = new PdfPTable(4);	
				table4.setWidthPercentage(100);
				float[] withs4 = {60f, 60f, 60f, 60f};
				table4.setWidths(withs4);
								
				cell5 = new PdfPCell(); 
				p = new Paragraph( ); 
				p.add(new Chunk( "Ng?????i tr??? ti???n Payer " , font8)); 	
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setBorder(0);
				cell5.setColspan(2);
				cell5.setPaddingTop(-0.2f*CONVERT);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( ); 
				p.add(new Chunk( "Ng??y h???ch to??n Accounting date ??????/???../????????? " , font8)); 	
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setBorder(0);
				cell5.setColspan(2);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell(); 
				p = new Paragraph( ); 
				p.add(new Chunk( "K??? to??n tr?????ng " , font8bold)); 
				p.add(new Chunk( "Chief accountant" , font8));		
				p.setAlignment(Element.ALIGN_LEFT);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setBorder(0);
				table4.addCell(cell5);
												
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ch??? t??i kho???n " , font8bold)); 
				p.add(new Chunk( "A/c holder " , font8));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorder(0);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Giao d???ch vi??n " , font8bold)); 
				p.add(new Chunk( "Teller " , font8));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorder(0);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "Ki???m so??t vi??n " , font8bold)); 
				p.add(new Chunk( "Supervisor " , font8));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setFixedHeight(2.0f * CONVERT);
				cell5.setBorder(0);
				table4.addCell(cell5);
				
				cell5 = new PdfPCell();
				p = new Paragraph( ); 
				p.add(new Chunk( "K?? t??n, ????ng d???u " , font8bold)); 
				p.add(new Chunk( "Signature & Seal " , font8));
				p.setAlignment(Element.ALIGN_CENTER);				
				cell5.addElement(p);
				cell5.setVerticalAlignment(Element.ALIGN_TOP);
				cell5.setHorizontalAlignment(Element.ALIGN_TOP);
				cell5.setPaddingTop(-0.2f*CONVERT);
				cell5.setBorder(0);
				cell5.setColspan(4);
				table4.addCell(cell5);
				
				cell2.addElement(table4);
				table1.addCell(cell2);
				
				cell.addElement(table1);
				
				//Ch??? Form quay g??c 90 ?????
				PdfContentByte cb = writer.getDirectContent();
				cb.beginText();
		        cb.setFontAndSize(bf, 8);
		        cb.setTextMatrix(0, 1, -1, 0, 20.6f*CONVERT, 0.8f * CONVERT);
		        cb.showText("M???u s??? 07/NHCT10");
		        cb.endText();
				
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
				cell1 = new PdfPCell();
				
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
				hinhanh.setAlignment(Element.ALIGN_CENTER);
				
				PdfPCell cellslogo = new PdfPCell(hinhanh);
				cellslogo.setPadding(5);
				cellslogo.setBorder(0);
				tablelogo.addCell(cellslogo);
				
				cellslogo = new PdfPCell();
				cellslogo.setBorder(0);	
				cellslogo.setFixedHeight(0.2f*CONVERT);
				tablelogo.addCell(cellslogo);
				
				cellslogo = new PdfPCell();
				cellslogo.setHorizontalAlignment(Element.ALIGN_TOP);
				cellslogo.setVerticalAlignment(Element.ALIGN_TOP);
				Paragraph p = new Paragraph();
				p = new Paragraph("..............................................", font9);
				p.setAlignment(Element.ALIGN_LEFT);
				cellslogo.setPaddingTop(-0.5f*CONVERT);
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
				p.add(new Chunk("Payment Order", font11bi)); 
				p.setAlignment(Element.ALIGN_CENTER);
				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell3.setBorder(0);
				cell3.addElement(p);				
				table3.addCell(cell3);
				
				cell3 = new PdfPCell();
				p = new Paragraph("Ng??y ......./....... /20.......", font9);
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
				cell3.setPaddingTop(-0.1f*CONVERT);
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
				p = new Paragraph("Li??n 1:   Ng??n h??ng", font9);
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
				p = new Paragraph("S??? b??t to??n: .....................", font9);
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
				p = new Paragraph("Lo???i ti???n: .........................", font9);
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
				p = new Paragraph("????n v??? tr??? ti???n: "+ traTen, font10);
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
				p = new Paragraph("S??? t??i kho???n: "+ traStk, font10);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("??i???n tho???i: "+traDienthoai, font10);
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
				p = new Paragraph("T???i ng??n h??ng: "+traNganHang, font10);
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
				p = new Paragraph("????n v??? th??? h?????ng: "+nhanTen, font10);
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
				p = new Paragraph("CMT/H??? chi???u: ", font10);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("Ng??y c???p: ", font10);
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
				p = new Paragraph("N??i c???p:", font10);
				p.setAlignment(Element.ALIGN_LEFT);
				cell5.addElement(p);	
				cell5.setPaddingTop(-0.1f*CONVERT);
				cell5.setBorder(0);					
				table5.addCell(cell5);
				
				cell5 = new PdfPCell();
				cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
				p = new Paragraph("??i???n tho???i:", font10);
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
				p = new Paragraph("S??? t??i kho???n:"+nhanStk, font10);
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
				p = new Paragraph("T???i ng??n h??ng: "+nhanNganHang, font10);
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
				p = new Paragraph( "S??? ti???n b???ng ch???: " +tienbangchu+"./", font10); 
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
				p = new Paragraph( "Amount in words " , font7italic); 
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
				p = new Paragraph( "N???i dung: " , font10); 
				p.setAlignment(Element.ALIGN_LEFT);				
				cell.addElement(p);
				cell.setBorder(0);
				cell.setBorderWidthLeft(1);
				cell.setBorderWidthRight(1);
				
				tablefooter.addCell(cell);
				
				cell = new PdfPCell();
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);	
				p = new Paragraph( "Details " , font7italic); 
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
	
	
	private boolean CreateUyNhiemChi(OutputStream out, IErpUynhiemchi obj)  throws Exception
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
							" 	isnull(ncc.TEN,'') as nccTen, isnull(ncc.DIACHI,'') as nccDiaChi, isnull(ncc.sotknganhang,'') as nccStk, isnull(nccnh.TEN, '') AS nccNganHang, isnull(ncccn.TEN, '') AS nccChiNhanh, " +
							" 	isnull(nv.TEN,'') as nvTen, isnull(nv.DIACHI,'') as nvDiaChi, isnull(nv.sotaikhoan,'') as nvStk, isnull(nvnh.TEN, '') AS nvNganHang, isnull(nvcn.TEN, '') AS nvChiNhanh, " +
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


	private String getDate(String date)
	{
		String arr[] = date.split("-");
		String nam = arr[0];
		String thang = arr[1];
		String ngay = arr[2];
		
		return ngay + "/" + thang + "/" + nam;
	}
	
	private void CreateUyNhiemChiBPPdf(Document document, ServletOutputStream outstream, IErpUynhiemchi pxkBean, String hdId) throws IOException
	{
		try{
			dbutils db = new dbutils();
			document.setMargins(0.0f*CONVERT, 1.0f*CONVERT, 4.8f*CONVERT, 2.0f*CONVERT);
			
			
			String query = 
			"SELECT UPPER(dvth.TEN) TENBOPHAN FROM ERP_THANHTOANHOADON tthd INNER JOIN erp_donvithuchien dvth on tthd.DVTH_FK = dvth.PK_SEQ "+
			"WHERE  tthd.pk_seq = "+hdId+"";
			
			String TENBP ="";
			ResultSet rsbp = db.get(query);
			if(rsbp!=null){
				while(rsbp.next()){
					TENBP = rsbp.getString("TENBOPHAN");
				}	
			}
			rsbp.close();
						
			
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			
			document.setMargins(1.0f*CONVERT, 1.0f*CONVERT, 1.0f*CONVERT, 2.0f*CONVERT); // L,R, T, B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			
			document.open();
			
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			
			query = 
				" SELECT TEN, DIACHI  FROM ERP_CONGTY  WHERE PK_SEQ = 100005";
				
				String TENCT = "";
				String DIACHICT = "";
				ResultSet rsbp_ct = db.get(query);
				if(rsbp_ct!=null){
					while(rsbp_ct.next()){
						TENCT = rsbp_ct.getString("TEN");
						DIACHICT = rsbp_ct.getString("DIACHI");
					}	
				}
				rsbp_ct.close();
				
				
			PdfPTable tableheader_ct =new PdfPTable(1);
			tableheader_ct.setWidthPercentage(100);			
			
			//T??N CTY
			PdfPCell cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			Paragraph hd = new Paragraph( TENCT, new Font(bf, 10, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader_ct.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			//?????A CH??? CTY
			hd = new Paragraph(DIACHICT, new Font(bf, 10, Font.BOLD));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(0.7f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader_ct.addCell(cell);
			
			document.add(tableheader_ct);
			
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);			

			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			hd = new Paragraph("CHI PH?? C??NG T??C PH?? " + TENBP, new Font(bf, 14, Font.BOLD));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(2.0f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setPaddingTop(0.85f*CONVERT);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			
			hd = new Paragraph("S??? ch???ng t???: " + hdId, new Font(bf, 12, Font.NORMAL));
			hd.setAlignment(Element.ALIGN_CENTER);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			//cell.setPaddingTop(0.85f*CONVERT);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tableheader.addCell(cell);
			
			document.add(tableheader);
			
			String[] th = new String[]{ "STT", "H??? V?? T??N", "S??? T??I KHO???N", "T???NG S??? TI???N","NH", "GHI CH??"};			
			
			PdfPTable nhanvien = new PdfPTable(th.length);
			nhanvien.setSpacingBefore(0.5f*CONVERT);
			nhanvien.setWidthPercentage(100);
			nhanvien.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			float[] withsKM = { 2.5f*CONVERT,13.0f*CONVERT, 10.0f*CONVERT, 8.0f*CONVERT,8.0f*CONVERT ,8.0f*CONVERT};
			nhanvien.setWidths(withsKM);
			
			PdfPCell cells = new PdfPCell();
			
			for (int j = 0; j < th.length; j++)
			{
				System.out.println(th[j]);
				cells = new PdfPCell(new Paragraph(th[j], new Font(bf, 10, Font.BOLD)));
				if (j <= 1 ){ //STT
					cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				}
				else{							
						if(j == 2 )//????N V??? T??NH
						{
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
						}								
						else{//S??? L?????NG, ????N GI??, ????N ???? GI???M GI??, TH??NH TI???N
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
				}
				
				cells.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				cells.setBorderWidth(1);
				cells.setFixedHeight(0.8f * CONVERT);	
				nhanvien.addCell(cells);
			}
			
			query =
				" select nv.MA as MADOITUONG,nv.TEN, nv.SOTAIKHOAN, tt.SOTIENTT, UPPER(dvth.TEN) TENBOPHAN, nh.MA MANGANHANG \n"+
				" from \n"+ 
				" 	ERP_THANHTOANHOADON_HOADONBOPHAN tt \n"+   
				" 	inner join ERP_THANHTOANHOADON tthd on tt.THANHTOANHD_FK = tthd.PK_SEQ \n"+  
				" 	inner join ERP_NHANVIEN nv on nv.PK_SEQ = tt.NHANVIEN_FK \n"+
				"	INNER JOIN erp_donvithuchien dvth on tthd.DVTH_FK = dvth.PK_SEQ "+
				"   INNER JOIN ERP_NGANHANG  nh on tthd.nganhang_fk = nh.PK_sEQ "+
				" WHERE TT.LOAIHD = 6 and tthd.pk_seq = "+hdId+"";
			
			System.out.println(query);
			
			String HOTENNV="";
			String SOTAIKHOAN="";
			int stt =0;
			double SOTIENTT =0;
			
			double SUMSOTIENTT =0;
			
			ResultSet rs = db.get(query);
			if(rs!=null){
				while(rs.next()){
					stt++;
					HOTENNV = rs.getString("TEN");
					SOTAIKHOAN = rs.getString("SOTAIKHOAN");
					SOTIENTT = rs.getDouble("SOTIENTT");
					
					SUMSOTIENTT += Math.round(SOTIENTT);
					
					String[] arr = new String[] { Integer.toString(stt), HOTENNV , SOTAIKHOAN ,DinhDangTraphacoERP(formatter1.format(SOTIENTT)), rs.getString("MANGANHANG"),"" };
					
					for (int j = 0; j < th.length; j++)
					{
						System.out.println(arr[j]);
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 12, Font.NORMAL)));
						if (j <= 1 ){ //STT
							if(j == 0 )
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							if(j == 1 )
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							//cells.setPaddingLeft(-0.5f*CONVERT);
						}
						else{							
								if(j == 2 || j==4)//????N V??? T??NH
								{
									cells.setHorizontalAlignment(Element.ALIGN_CENTER);
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
			
			//D??NG T???NG C???NG
			String[] arr = new String[] { "", "T???ng c???ng" , "" ,DinhDangTraphacoERP(formatter1.format(SUMSOTIENTT)), "","" };
			
			for (int j = 0; j < th.length; j++)
			{
				System.out.println(arr[j]);
				cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 12, Font.BOLD)));
				if (j <= 1 ){ //STT
					if(j == 0 )
						cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					if(j == 1 )
						cells.setHorizontalAlignment(Element.ALIGN_LEFT);
					//cells.setPaddingLeft(-0.5f*CONVERT);
				}
				else{							
						if(j == 2 || j==4)//????N V??? T??NH
						{
							cells.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
						else{//S??? L?????NG, ????N GI??, ????N ???? GI???M GI??, TH??NH TI???N
							cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
				}
				
				cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cells.setBorderWidth(1);
				//cells.setFixedHeight(0.8f * CONVERT);	
				nhanvien.addCell(cells);
			}
			
			document.add(nhanvien);
			
			PdfPTable tablefooter =new PdfPTable(2);
			tablefooter.setSpacingBefore(1.0f*CONVERT);	
			float[] withs1 = {300f, 500f};
			tablefooter.setWidths(withs1);
			
			cell = new PdfPCell();	
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			hd = new Paragraph("Ng??y ...... th??ng ........ n??m ......" , new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(0.8f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setColspan(2);
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("T???NG GI??M ?????C" , new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_LEFT);
			cell.setFixedHeight(1.6f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);			
			cell.setBorder(0);
			cell.addElement(hd);
			
			tablefooter.addCell(cell);
			
			cell = new PdfPCell();	
			hd = new Paragraph("K??? TO??N TR?????NG" , new Font(bf, 12, Font.ITALIC));
			hd.setAlignment(Element.ALIGN_RIGHT);
			cell.setFixedHeight(1.6f*CONVERT);
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
	
	public class HeaderAndFooter extends PdfPageEventHelper {

	    private String name = "";


	    protected Phrase footer;
	    protected Phrase header;
	    BaseFont bf;

	    /*
	     * Font for header and footer part.
	     *//*
	    private static Font headerFont = new Font(bf, 10, Font.BOLD);

	    private static Font footerFont = new Font(Font.TIMES_ROMAN, 9,
	            Font.BOLD,Color.blue);*/


	    /*
	     * constructor
	     */
	    public HeaderAndFooter(String name) {
	        super();

	        this.name = name;


	        header = new Phrase("***** Header *****");
	        footer = new Phrase("**** Footer ****");
	    }


	    @Override
	    public void onEndPage(PdfWriter writer, Document document) {

	        PdfContentByte cb = writer.getDirectContent();
	        
	        try {
				bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			} catch (DocumentException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
	        
	        //header content
	        String headerContent = "S??? ch???ng t???: " +name;

	        //header content
	        String footerContent = headerContent;
	        /*
	         * Header
	         */ 
	        if(writer.getPageNumber()>1){
		        ColumnText.showTextAligned(cb, Element.ALIGN_MIDDLE, new Phrase(headerContent,new Font(bf, 12, Font.NORMAL)), 
		                document.leftMargin() + 12.5f*CONVERT, document.top() + 10, 0);				
		        
	        }

	        /*
	         * Foooter
	         */
	        ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, new Phrase("Trang: "+String.format(" %d ", 
	                writer.getPageNumber()),new Font(bf, 10, Font.BOLD)), 
	                document.right() - 2 , document.bottom() - 20, 0);

	    }

	}
}


