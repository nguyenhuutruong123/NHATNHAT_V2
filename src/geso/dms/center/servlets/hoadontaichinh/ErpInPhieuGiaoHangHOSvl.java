package geso.dms.center.servlets.hoadontaichinh;

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

public class ErpInPhieuGiaoHangHOSvl extends HttpServlet{	
	private static final long serialVersionUID = 1L;

	public ErpInPhieuGiaoHangHOSvl()
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
		IErpHoadontaichinh hdBean = new ErpHoadontaichinh(id);
		hdBean.setUserId(userId); 
		hdBean.init();
		System.out.print("\n id hoa don: "+id);

		//=============khai bao doc
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=Phieugiaohang.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		this.CreatePxk(document, outstream, hdBean);
	
		
	}


	private String getDate()
	{			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
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



	private void CreatePxk(Document document, ServletOutputStream outstream, IErpHoadontaichinh pxkBean) throws IOException
	{
		System.out.println(" anh sang in ");
		
		dbutils db = new dbutils();
		//LAY THONG TIN BEN A DCL HO
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Dienthoai= "";	
		
		
		String qr_ho="select ten , diachi, dienthoai, fax, masothue,  SOTK AS TAIKHOAN, nganhang from nhacungcap where pk_seq=100001 ";   

		ResultSet rsHo = db.get(qr_ho);
		if(rsHo!=null){
			try {
				if(rsHo.next())
				{
					ctyTen = rsHo.getString("TEN");
					cty_MST = rsHo.getString("MASOTHUE");
					cty_Diachi = rsHo.getString("DIACHI");
					cty_Dienthoai = rsHo.getString("DIENTHOAI");
					rsHo.close();

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
		String npp_fk="";
		String khId="";

		String  qr_info="select hinhthuctt , ngayxuathd ,(select Ten from ERP_KHOTT  where PK_SEQ = erp_hoadon.kho_fk) as XUATTAIKHO, "+    
		" isnull(khachhang_fk,0) as khachhang_fk,isnull( npp_fk,0) as npp_fk from erp_hoadon where pk_seq="+pxkBean.getId();
		System.out.println("\n thong tin hoa don: "+qr_info);
		ResultSet rs_info=db.get(qr_info);
		if(rs_info!=null)
		{
			try {
				while(rs_info.next())
				{
					khId=rs_info.getString("khachhang_fk");
					npp_fk=rs_info.getString("npp_fk");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if(npp_fk.length()>0 && !npp_fk.equals("0")){//LAY THEO NPP
			sql = " select PK_SEQ, TEN as DONVI, '' AS TEN ,DIACHI as DIACHI, isnull(MASOTHUE,'') as MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX,  "+    
			"isnull( SOTAIKHOAN,'') as TAIKHOAN,isnull( NGANHANG,'') as NGANHANG,   isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho  "+    
			"from NHAPHANPHOI WHERE PK_SEQ="+npp_fk;
		}
		else
			if(khId.length() > 0 &&  !khId.equals("0")){

				//L???Y THEO D??? LI???U M???I LAY BEN KHACH HANG
				sql = "select TEN AS DONVI, isnull(CHUCUAHIEU,'') AS TEN, DIENTHOAI, DIACHI, isnull(MASOTHUE,'') as MASOTHUE, isnull(TAIKHOAN_FK,0) AS TAIKHOAN, "+    
				"  '' AS NGANHANG from khachhang WHERE PK_SEQ="+khId;
			}else
			{
				sql = " select PK_SEQ, '' as DONVI, '' AS TEN ,'' as DIACHI, '' as MASOTHUE,'' as DIENTHOAI, '' as FAX,  "+    
				" 0 as TAIKHOAN,''as NGANHANG,   '' as XUATTAIKHO "+    
				"from NHAPHANPHOI ";
			}

		System.out.println("\n L???y DON VI MUA HANG: "+sql);
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
					
				}  
				rsLayKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 

		//thong tin don hang 
		String sohopdong="             ";
		String ngayhopdong="                  ";
		String dothopdong="           ";
		String sohoadon=pxkBean.getSohoadon();
		String ngayhoadon=pxkBean.getNgayxuatHD();

		//THONG TIN DANH SACH SAN PHAM
		//san pham ban
		String	 query_sp = "select hoadon_fk as mahd, donhang_fk as madonhang, ma as MA, ten as TEN,NGAYHETHAN as handung,  "+    
							 " isnull((SELECT DONVI FROM DONVIDOLUONG WHERE PK_SEQ= ERP_HOADON_SP_CHITIET.DVCHUAN),'') AS DONVI, DONGIA AS DONGIA, SOLUONG AS SOLUONG, SOLO AS SOLO  "+    
							 " from ERP_HOADON_SP_CHITIET where hoadon_fk="+ pxkBean.getId();
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
			//duong dan o may
		//	Image img = Image.getInstance(getServletContext().getInitParameter("pathPrint")+ "\\logo.gif");
			
			//duong dan o 115
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
			para=new Paragraph("M???u: 3HD15-CN",font10_normal);
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tableheader.addCell(cell);
			

			// dong 2 trai  dia chi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("C??NG TY C??? PH???N D?????C PH???M C???U LONG",font10_normal);
			cell.addElement(chunk);
			tableheader.addCell(cell);

			//// dong 2 phai 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingLeft(1.0f*CONVERT);
			para=new Paragraph("C???NG H??A X?? H???I CH??? NGH??A VI???T NAM",font10_normal);
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
			para=new Paragraph("?????c L???p - T??? Do - H???nh Ph??c",font10_normal);
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
			para=new Paragraph("BI??N B???N GIAO H??NG",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 5 hop dong
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			para=new Paragraph("Theo h???p ?????ng s???: "+ sohopdong+ "  ng??y: "+ngayhopdong +"    -?????t:",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);


			// dong 6 hoa don
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("Theo h??a ????n s???: "+sohoadon.trim()+"  ng??y:  "+ngayhoadon.substring(8)+"/"+ngayhoadon.substring(5,7)+"/"+ngayhoadon.substring(0,4),font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 7 dms
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			para=new Paragraph("S??? phi???u: "+pxkBean.getId()+"  ng??y: "+ ngayhoadon.substring(8)+"/"+ngayhoadon.substring(5,7)+"/"+ngayhoadon.substring(0,4) ,font10_ilatic);
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
			para=new Paragraph("H??m nay, ng??y "+ getDate()+". Ch??ng t??i g???m c??:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("B??N B??N (B??N A): C??NG TY C??? PH???N D?????C PH???M C???U LONG",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- ?????a ch???: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- M?? s??? thu???: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Do ??ng/b??:                                                                                  L??m ?????i di???n",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- ??i???n tho???i: "+cty_Dienthoai,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("B??N MUA (B??N B): "+Donvi.toUpperCase(),font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("- ?????a ch???: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- M?? s??? thu???: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- Do ??ng/b??:                                                                                  L??m ?????i di???n",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("- ??i???n tho???i: "+ kh_dienthoai,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// dong 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("         Hai b??n nh???t tr?? x??c nh???n b??n A ???? giao h??ng t???i kho c???a b??n B. "+
					"Hai b??n c??ng ti???n h??ng nghi???m thu m???t ph???n h???p ?????ng s???: "+ sohopdong +" ng??y "+ ngayhopdong+
					". H??ng h??a ???? ???????c giao ????ng s??? l?????ng, c??? th??? nh?? sau:",font10_normal);
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
			String[] tieude = new String[] { "Stt","T??n h??ng","??VT","????n gi??","S??? l?????ng","Th??nh ti???n","S??? l??, h???n d??ng","Ghi ch??"};
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
			para = new Paragraph("C???ng ti???n h??ng " , font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien))+" ?????ng" , font10_normal);
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
			para = new Paragraph("Ti???n gi???m", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(" ?????ng" ,font10_normal);
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
			para = new Paragraph("Ti???n chi???t kh???u", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(pxkBean.getTongCK().replaceAll(",", ".") + " ?????ng" ,font10_normal);
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
			para = new Paragraph("Ti???n h??ng ", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell); 

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph((formatter1.format(tienhang)).replaceAll(",", ".")+ " ?????ng" ,font10_normal);
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
			para = new Paragraph("Thu??? GTGT", font10_normal);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(tienvat.replaceAll(",", ".") +" ?????ng" , font10_normal);
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
			para = new Paragraph("T???ng c???ng ",font10_normal);
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
			para = new Paragraph(tongtien_vat.replaceAll(",", ".")+" ?????ng" ,font10_normal);
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
			para = new Paragraph("T???ng c???ng  "+ (stt-1) + "  Kho???n. G???m  .................  th??ng (ki???n), ................... h???p (chai).", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);


			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph();
			para.add(new Chunk("T??nh tr???ng h??ng h??a:   ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  B??nh th?????ng, bao b?? nguy??n v???n, nguy??n ??ai nguy??n ki???n.", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(3.3f*CONVERT);
			para = new Paragraph();
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("  Kh??c ..................................................................."
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
			para.add(new Chunk("Ch???ng t??? k??m theo:     ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk("   H??a ????n GTGT,      ", font10_normal ));
			para.add(new Chunk( String.valueOf(unchecked), charfont ));
			para.add(new Chunk(" Kh??c  .........................................................................................", font10_normal ));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Bi??n b???n ???????c l???p th??nh   ...   b???n, m???i b??n gi???   ...   b???n v?? c?? gi?? tr??? nh?? nhau.", font10_normal);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("?????I DI???N B??N A"+
					"                                                                                         "+
					"?????I DI???N B??N B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			document.add(tbl_footer);


			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}



	}



}

