package geso.dms.distributor.servlets.hoadontaichinhNPP;

import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinh;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPP;
import geso.dms.distributor.beans.hoadontaichinhNPP.imp.ErpHoadontaichinhNPP;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
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

import demo.dcl.form.forminpdf;

public class ErpInPhieuGiaoHangNPPSvl extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ErpInPhieuGiaoHangNPPSvl()
	{
		super();
	}


	float CONVERT = 28.346457f;  // =1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");
		
		String id = util.antiSQLInspection(request.getParameter("pdf"));
		IErpHoadontaichinhNPP pxkBean = new ErpHoadontaichinhNPP(id);
		pxkBean.setUserId(userId);
		pxkBean.init();

		System.out.print("\n id hoa don: "+id);

		//=============khai bao doc
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Phieugiaohang.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		this.CreatePxk(document, outstream, pxkBean);

	}


	private String getDate()
	{			
		//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);


	}

	private void CreatePxk(Document document, ServletOutputStream outstream, IErpHoadontaichinhNPP pxkBean) throws IOException
	{
		dbutils db = new dbutils();
		//LAY THONG TIN BEN A
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Dienthoai= "";	
		String	sql_ncc=" select npp.PK_SEQ, npp.TEN ,npp.DIACHI as DIACHI, npp.MASOTHUE,npp.DIENTHOAI, isnull( FAX,'') as FAX, "+    
		" (select ten from tinhthanh where pk_seq=npp.tinhthanh_fk) as tinhthanh, "+    
		" (select ten from QUANHUYEN where pk_seq=npp.quanhuyen_fk) as quanhuyen "+      
		" from NHAPHANPHOI npp "+    
		"where PK_SEQ = (select npp_fk from ERP_HOADONNPP where pk_seq = '"+ pxkBean.getId() +"') ";

		System.out.println(" \n Lấy TT CTY "+sql_ncc +"\n");
		ResultSet rsINFO = db.get(sql_ncc);
		if(rsINFO!=null){
			try {
				if(rsINFO.next())
				{
					ctyTen = rsINFO.getString("TEN");
					cty_MST = rsINFO.getString("MASOTHUE");
					cty_Diachi = rsINFO.getString("DIACHI")+", "+rsINFO.getString("quanhuyen")+", "+rsINFO.getString("tinhthanh");
					cty_Dienthoai = rsINFO.getString("DIENTHOAI");
					rsINFO.close();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		//LAY THONG TIN KHACHHANG BEN B	
		String Donvi="";
		String kh_MST ="";
		String kh_Diachi="";
		String kh_sotaikhoan="";
		String kh_dienthoai="";
		String sql="";
		String nguoimuahang="";
		//ban doi tac lay tu npp
		System.out.println("\n loai khach hang: "+pxkBean.getLoaiXHD() +"\n");
		if(pxkBean.getLoaiXHD().equals("0")){

			sql = "select kh.pk_seq, kh.TEN as donvi, '' as chucuahieu , kh.diachi as diachi,  kh.masothue , kh.sotaikhoan as taikhoan, isnull(dienthoai,'') as dienthoai,  "+    
			"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
			"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
			" from NHAPHANPHOI kh where kh.pk_seq="+pxkBean.getNppId();


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
						kh_dienthoai=rsLayKH.getString("dienthoai");
						rsLayKH.close();

					}  
					rsLayKH.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
			} 

		}

		//ban khach hang etc
		if (pxkBean.getLoaiXHD().equals("1")) {
			sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan,  "+    
			"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
			"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
			" from KHACHHANG kh where kh.pk_seq="+pxkBean.getKhId();  

			System.out.println("\n Lấy TT KH etc: "+sql +"\n");

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
						rsLayKH.close();

					}  
					rsLayKH.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		}

		//thong tin don hang 
		String sohopdong="             ";
		String ngayhopdong="                  ";
		String dothopdong="           ";
		String sohoadon=pxkBean.getSohoadon();
		String ngayhoadon=pxkBean.getNgayxuatHD();
		
		//THONG TIN DANH SACH SAN PHAM
		String	 query_sp = "select  a.MA as MA, isnull(a.ten,'') as TEN,solo as solo, NGAYHETHAN as handung, a.donvi AS DONVI, a.soluong AS SOLUONG, a.dongia  AS DONGIA "+    
		"from ERP_HOADONNPP_SP_CHITIET a where a.hoadon_fk ="+pxkBean.getId();
		ResultSet rsSP = db.get(query_sp);
		double tongtien = 0;
		String vat="";
		String tienvat= pxkBean.getTongVAT();
		String tongtien_vat =pxkBean.getTongtienAVAT();
		 //tien hang sau khi tru tien giam va chiet khau
		double tienhang = Double.parseDouble(pxkBean.getTongtienAVAT().replaceAll(",", ""))-Double.parseDouble( pxkBean.getTongCK().replaceAll(",", ""));

		
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
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


			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(2);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			float[] withd= {10f*CONVERT,12.0f*CONVERT};
			tableheader.setWidths(withd);

			// dong 1 logo 
			Image img = Image.getInstance(getServletContext().getInitParameter("pathhinh")+ "\\logo.gif");
			img.scalePercent(30);
			cell = new PdfPCell();
			cell.addElement(new Chunk(img,50,-10)); // vij tri dat anh 
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableheader.addCell(cell);

			// dong 1 mau
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("Mẫu: 3HD15-CN",font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 2 trai  dia chi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG",font10_normal);
			cell.addElement(chunk);
			tableheader.addCell(cell);

			//// dong 2 phai 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingLeft(1.0f*CONVERT);
			para=new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 3 trai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(3f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("---o0o---",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);


			//dong 3 phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Độc Lập - Tự Do - Hạnh Phúc",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);


			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("---o0o---",font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);



			// dong 4 tua de
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("BIÊN BẢN GIAO HÀNG",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 5 hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			para=new Paragraph("Theo hợp đồng số: "+ sohopdong+ "  ngày: "+ngayhopdong +"    -Đợt:",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);


			// dong 6 hoa don
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Theo hóa đơn số: "+sohoadon.trim()+"  ngày:  "+ngayhoadon.substring(8)+"/"+ngayhoadon.substring(5,7)+"/"+ngayhoadon.substring(0,4),font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 7 dms
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Số phiếu: "+pxkBean.getId()+"  ngày: "+ ngayhoadon.substring(8)+"/"+ngayhoadon.substring(5,7)+"/"+ngayhoadon.substring(0,4) ,font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			document.add(tableheader);

			//== thong tin

			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);

			// dong 1 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("Hôm nay, ngày "+ getDate()+". Chúng tôi gồm có:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("BÊN BÁN (BÊN A): CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Do ông/bà:                                                                                  Làm đại diện",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Điện thoại: "+cty_Dienthoai,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("BÊN MUA (BÊN B): "+Donvi.toUpperCase(),font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- Địa chỉ: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Do ông/bà:                                                                                  Làm đại diện",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Điện thoại: "+ kh_dienthoai,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("         Hai bên nhất trí xác nhận bên A đã giao hàng tại kho của bên B. "+
					"Hai bên cùng tiến hàng nghiệm thu một phần hợp đồng số: "+ sohopdong +" ngày "+ ngayhopdong+
					". Hàng hóa đã được giao đúng số lượng, cụ thể như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);
			document.add(tbl);


			//========bang danh sach san pham
			float[] tbl_withd = { 7.0f, 50f, 10f , 20.0f, 15.0f, 25f, 27f, 15.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//add tieu de
			String[] tieude = new String[] { "Stt","Tên hàng","ĐVT","Đơn giá","Số lượng","Thành tiền","Số lô, hạn dùng","Ghi chú"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			
			//danh sach
			int stt = 1;
			try {
				while(rsSP.next())
				{
					double soLUONG = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = soLUONG*dongia;
					//lay han su dung theo mau MM/yy
					String handung="";
					if(rsSP.getString("handung").length()>0){
						handung=rsSP.getString("handung").substring(5,7)+"/"+rsSP.getString("handung").substring(2,4);
					}

					tongtien +=thanhtien;
					String[] arr = new String[] { Integer.toString(stt), rsSP.getString("TEN"), 
							rsSP.getString("DONVI"),DinhDangTraphacoDMS(formatter.format(dongia)),
							DinhDangTraphacoDMS(formatter1.format(soLUONG)), DinhDangTraphacoDMS(formatter1.format(thanhtien)),
							 rsSP.getString("solo")+"-"+handung ," "};


					for (int j = 0; j < tbl_withd.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));

						if(j==1){
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						}
						else
							if(j==0 ||j==2){
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
								{
									
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
								}

						cells.setPaddingBottom(7);
						tbl_sanpham.addCell(cells);	
					}
					stt++;
				}
				rsSP.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			

			//tong ket
			//cong tien hang 
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Cộng tiền hàng " , font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien))+" đồng" , font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthLeft(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);


			////tien giam
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tiền giảm", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);


			//chiet khau
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tiền chiết khấu", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(pxkBean.getTongCK().replaceAll(",", ".") + " đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			
			//tien hang
			
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tiền hàng ", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell); 

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph((formatter1.format(tienhang)).replaceAll(",", ".")+ " đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			//thue vat
			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Thuế GTGT", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(tienvat.replaceAll(",", ".") +" đồng" , font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthBottom(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("",font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			//tong cong
			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthRight(0);
			cell.setPaddingBottom(7);
			cell.setColspan(4);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(6.3f * CONVERT);
			para = new Paragraph("Tổng cộng ",font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthRight(0);
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(tongtien_vat.replaceAll(",", ".")+" đồng" ,font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderWidthTop(0);
			cell.setBorderWidthLeft(0);
			cell.setPaddingBottom(7);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);
			document.add(tbl_sanpham);
			//=========== footer

			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(90);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Tổng cộng  "+ (stt-1) + "  Khoản. Gồm  .................  thùng (kiện), ................... hộp (chai).", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);


			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.add(new Chunk("Tình trạng hàng hóa:   ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  Bình thường, bao bì nguyên vẹn, nguyên đai nguyên kiện.", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(3.3f*CONVERT);
			para = new Paragraph();
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  Khác ..................................................................."
					+"..............................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("..................................................................................."+
					"..................................................................................................", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);


			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.add(new Chunk("Chứng từ kèm theo:     ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("   Hóa đơn GTGT,      ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk(" Khác  .........................................................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Biên bản được lập thành   ...   bản, mỗi bên giữ   ...   bản và có giá trị như nhau.", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                                         "+
					"ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			document.add(tbl_footer);


			document.close();

		} catch (Exception e) {
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


}

