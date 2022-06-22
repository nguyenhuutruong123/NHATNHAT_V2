
package geso.dms.distributor.servlets.hangtralainpp;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.hangtralai.IErpHangTraLaiNpp;
import geso.dms.distributor.beans.hangtralai.imp.ErpHangTraLaiNpp;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPPList;
import geso.dms.distributor.beans.phieugiaohangkm.IPhieugiaohangkm;
import geso.dms.distributor.beans.phieugiaohangkm.imp.Phieugiaohangkm;
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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
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

@WebServlet("/ErpHangTraLaiNppPdfSvl")
public class ErpHangTraLaiNppPdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ErpHangTraLaiNppPdfSvl() {
		super();

	}

	float CONVERT = 28.346457f; // =1cm

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		Utility util = new Utility();

		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String id = util.antiSQLInspection(request.getParameter("pdf"));
		System.out.println("ma phieu : id:" + id);

		IErpHangTraLaiNpp lsxBean = new ErpHangTraLaiNpp(id);
		lsxBean.setUserId(userId);
		lsxBean.init();

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
		" inline; filename=NhapHangBanTraLai.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		try {
			// this.CreatePhieuGiaoHang(document, outstream, id);

			// bien ban tra hang
			form_phieunghiemthu2(document, outstream, lsxBean);

		} catch (Exception e) {
		}
	}

	private void form_phieunghiemthu2(Document document,
			ServletOutputStream outstream, IErpHangTraLaiNpp obj) {
		dbutils db = new dbutils();

		// lay thong khah hang
		String Donvi = "";
		String kh_MST = "";
		String kh_dienthoai = "";
		String kh_Diachi = "";
		String kh_sotaikhoan = "";
		String kh_fax = "";
		String kh_bank = "";
		String sql = "";
		String doituong = obj.getDoituong();

		if (doituong.equals("1")) // la khach hang
		{
			sql = "select   kh.pk_seq, kh.ten as ten ,kh.dienthoai as dienthoai,'' as taikhoan, '' as fax, '' as nganhang, "
				+ "isnull(kh.masothue,'') as masothue,  kh.diachi   as diachi  "
				+ "from Erp_HangTraLaiNpp a inner join khachhang kh on  kh.PK_SEQ=a.khachhang_fk  "
				+ "where kh.TRANGTHAI=1 and a.pk_seq=" + obj.getId();
		} else // la doi tac
		{
			sql = "select   kh.pk_seq, kh.ten as ten ,kh.dienthoai as dienthoai,kh.SOTAIKHOAN as taikhoan, kh.FAX as fax, kh.NGANHANG as nganhang, "
				+ "isnull(kh.masothue,'') as masothue,  kh.diachi   as diachi  "
				+ "from Erp_HangTraLaiNpp a inner join nhaphanphoi kh on  kh.PK_SEQ=a.khachhang_fk  "
				+ "where kh.TRANGTHAI=1 and a.pk_seq=" + obj.getId();
		}
		ResultSet rsKH = db.get(sql);
		if (rsKH != null) {
			try {
				if (rsKH.next()) {
					Donvi = rsKH.getString("TEN");
					kh_MST = rsKH.getString("MASOTHUE");
					kh_Diachi = rsKH.getString("DIACHI");
					kh_sotaikhoan = rsKH.getString("taikhoan");
					kh_dienthoai = rsKH.getString("dienthoai");
					kh_fax = rsKH.getString("fax");
					kh_bank = rsKH.getString("nganhang");
					rsKH.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// lay thong tin ben giao //cua duoc cuu long
		String npp_fk = "";
		String khId = "";
		String ctyTen = "";
		String cty_MST = "";
		String cty_Diachi = "";
		String cty_Sotaikhoan = "";
		String cty_Dienthoai = "";
		String cty_Fax = "";
		String cty_bank = "";
		// DON VI BAN: TONG CTY HO DCL
		String qr_ho = "select ten , diachi, dienthoai, fax, masothue, taikhoan, nganhang from nhacungcap where pk_seq=100001 ";
		ResultSet rsHo = db.get(qr_ho);
		if (rsHo != null) {
			try {
				if (rsHo.next()) {
					ctyTen = rsHo.getString("TEN");
					cty_MST = rsHo.getString("MASOTHUE");
					cty_Diachi = rsHo.getString("DIACHI");
					cty_Sotaikhoan = rsHo.getString("taikhoan");
					cty_Dienthoai = rsHo.getString("dienthoai");
					cty_Fax = rsHo.getString("fax");
					cty_bank = rsHo.getString("nganhang");
					rsHo.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// danh sach san pham
		String[] spMa = obj.getSpMa();
		String[] spTen = obj.getSpTen();
		String[] spDonvi = obj.getSpDonvi();
		String[] spSoluong = obj.getSpSoluong();
		String[] spGianhap = obj.getSpGianhap();
		String[] spTonkho = obj.getSpTonkho();
		String[] spSoLo = obj.getSpSolo();
		String[] spNgayHetHan = obj.getSpNgayHetHan();
		String[] spGhiChu = obj.getSpGhiChu();
		String[] spVat = obj.getSpVat();

		double tienvat=0;
		double tientruocthue=0;
		double tiensauthue=0;
		String qrTien=" select TienTruocThue, tienthue, tiensauthue from Erp_HangTraLaiNpp where pk_seq= "+obj.getId();
		ResultSet rsTien = db.get(qrTien);
		if (rsTien != null) {
			try {
				if (rsTien.next()) {
					tienvat = rsTien.getDouble("tienthue");
					tientruocthue = rsTien.getDouble("TienTruocThue");
					tiensauthue = rsTien.getDouble("tiensauthue");
					rsTien.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			// khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f * CONVERT, 1.5f * CONVERT, 1.0f * CONVERT,
					1.0f * CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell;
			Paragraph para;
			Chunk chunk;
			BaseFont charbase = BaseFont.createFont(
					"C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H,
					false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked = '\u00FE';
			char unchecked = '\u00A8';

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 12, Font.NORMAL);
			Font font10_Bold = new Font(bf, 12, Font.BOLD);
			Font font10_ilatic = new Font(bf, 12, Font.ITALIC);

			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);

			document.open();
			// ================tao header1
			PdfPTable tableheader = new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);

			// dong 1
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",
					font10_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			chunk = new Chunk("Độc Lập - Tự Do - Hạnh Phúc", font10_normal);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 3
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(10);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para = new Paragraph("BIÊN BẢN TRẢ HÀNG", new Font(bf, 16,
					Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 4 5

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.0f * CONVERT);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			String date = getDate();
			para = new Paragraph("Hôm nay, ngày "
					+ date.substring(8) + " tháng " + date.substring(5, 7)
					+ " năm " + date.substring(0, 4) + ", chúng tôi gồm có:",
					font10_ilatic);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tableheader.addCell(cell);

			document.add(tableheader);

			// == thong tin

			PdfPTable tbl = new PdfPTable(1);
			tbl.setWidthPercentage(100);

			// thong tin ben ban
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.0f * CONVERT);

			para = new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk = new Chunk("- Bên bán", font10_normal);
			para.add(chunk);
			chunk = new Chunk(" : " + ctyTen.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);

			para = new Paragraph("- Địa chỉ: " + cty_Diachi, font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph("- Điện thoại: " + cty_Dienthoai
					+ "                Fax: " + cty_Fax, font10_normal); // kem
			// fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph("- Mã số thuế: " + cty_MST, font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph("- Tài khoản: " + cty_Sotaikhoan + " tại "
					+ cty_bank, font10_normal); // tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph(
					"- Đại diện:                                                          Chức vụ:",
					font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben mua
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.0f * CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingTop(10);
			para = new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk = new Chunk("- Bên mua", font10_normal);
			para.add(chunk);
			chunk = new Chunk(" : " + Donvi.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);

			para = new Paragraph("- Địa chỉ: " + kh_Diachi, font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph("- Điện thoại: " + kh_dienthoai
					+ "                 Fax:" + kh_fax, font10_normal); // kem
			// fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph("- Mã số thuế: " + kh_MST, font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph("- Tài khoản: " + kh_sotaikhoan,font10_normal); // tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para = new Paragraph(
					"- Đại diện:                                                          Chức vụ:",
					font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(
					"Hai bên thống nhất lập biên bản hoàn trả các mặt hàng thuốc của các số hóa đơn sau:",
					font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			document.add(tbl);

			// ========bang danh sach hoa don
			float[] tbl_withd = { 10.0f, 50f, 15.0f, 20.0f, 15f, 18.0f, 20.0f,
					25.0f, 25.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			// add tieu de
			String[] tieude = new String[] { "STT", " Tên Hàng Hóa ", "ĐVT",
					"Số lô- Hạn dùng", "Số lượng", "Số hóa đơn",
					"Ngày hóa đơn", "Đơn giá chưa \n (VAT)", "Thành tiền" };
			for (int j = 0; j < tbl_withd.length; j++) {
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10,
						Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				cells.setBorderColor(BaseColor.LIGHT_GRAY);
				tbl_sanpham.addCell(cells);
			}
			
			
			
			// danh sach
			int stt = 1;
			double tongtien = 0;
			String qrsp = "select	c.MA as spMa,c.TEN as spTEN, d.DONVI as spDonVi,b.SoLuong as soluong,b.SoLo as spSoLo,b.ngayhethan as spNGAYHETHAN, "
				+ "					isnull (a.SoHoaDon,'')  as sohoadon, isnull (a.NgayHoaDon,'')  as ngayhoadon, "
				+ "\n					round(b.SoLuong*b.DonGia,0)/b.SoLuong as dongia, round(b.SoLuong*b.DonGia,0) as thanhtien "
				+ "\n			from  "
				+ "\n			Erp_HangTraLaiNpp a inner join Erp_HangTraLaiNpp_SanPham b on b.HangTraLai_fk=a.pk_seq "
				+ "\n			inner join SANPHAM c on c.PK_SEQ=b.Sanpham_fk     "
				+ "\n			left join DONVIDOLUONG d on d.PK_SEQ=b.Dvdl_Fk "
				+ "\n		where a.pk_seq=" + obj.getId();

			ResultSet rsSP = db.get(qrsp);
			try {

				// SAN PHAM BAN
				while (rsSP.next()) {

					String ma = rsSP.getString("spMa");
					String ten = rsSP.getString("spTen");
					String donvi = rsSP.getString("spDonvi");
					String solo = rsSP.getString("spSolo");
					String ngayhethang = rsSP.getString("spNGAYHETHAN");
					String sohoadon = rsSP.getString("sohoadon");
					String ngayhoadon = rsSP.getString("ngayhoadon");
					double soluong = rsSP.getDouble("soluong");
					double dongia = rsSP.getDouble("dongia");
					double thanhtien = rsSP.getDouble("thanhtien");
					tongtien += thanhtien;
					
					/*if(ngayhethang.length()>8){
						ngayhethang=ngayhethang.substring(5,7)+"/"+ngayhethang.substring(2,4);
						solo+= "-" +ngayhethang;
					}*/
					
					
					
					/*//tach lay so lo thui
					if(solo.contains("-")){
						String []sl=solo.split("-");
						solo=sl[0];
					}*/
				
					if(solo.toUpperCase().trim().equals("NA")){
						solo="";	
					}

					if(ngayhoadon.trim().length()>8){
						ngayhoadon=dinhdangngay(ngayhoadon);
					}

					String[] arr = new String[] {
							Integer.toString(stt),ten, donvi,solo,
							DinhDangTraphacoDMS(formatter.format(soluong)),sohoadon,ngayhoadon,
							DinhDangTraphacoDMS(formatter.format(dongia)),DinhDangTraphacoDMS( formatter1.format(thanhtien))
					};
					for (int j = 0; j < tbl_withd.length; j++) {
						cells = new PdfPCell(new Paragraph(arr[j],
								new Font(bf, 10, Font.NORMAL)));
						if (j == 1) {
							cells.setHorizontalAlignment(Element.ALIGN_LEFT);
						} else
							if(j==4 ||j==7 ||j==8){
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
						cells.setBorderColor(BaseColor.LIGHT_GRAY);
						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setPaddingBottom(7);
						tbl_sanpham.addCell(cells);

						
					}
					stt++;
				}
				rsSP.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			// cong
			cell = new PdfPCell();
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingBottom(7);
			para = new Paragraph("Tổng cộng", new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			for (int i = 0; i <= 5; i++) {
				cell = new PdfPCell();
				cell.setBorderColor(BaseColor.LIGHT_GRAY);
				cell.setVerticalAlignment(Element.ALIGN_LEFT);
				para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
				cell.addElement(para);
				tbl_sanpham.addCell(cell);
			}

			cell = new PdfPCell();
			cell.setBorderColor(BaseColor.LIGHT_GRAY);
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)), new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			document.add(tbl_sanpham);

			// =========== footer

			//tinh phan tram vat
			String qrvat="select top 1 c.PT_VAT as ptvat "+    
			"from Erp_HangTraLaiNpp_SanPham b inner join SANPHAM c on c.PK_SEQ=b.Sanpham_fk  "+    
			"	where b.HangTraLai_fk=" +obj.getId();
			String ptvat="";
			System.out.println(" \n lay phan tram vat: "+ qrvat);
			ResultSet rsVAT=db.get(qrvat);
			if(rsVAT!=null){
				try {
					while(rsVAT.next()){
						ptvat=rsVAT.getString("ptvat");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


			PdfPTable tbl_footer = new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("       - Tiền Thuế VAT ("+ptvat+"%): " + DinhDangTraphacoDMS(formatter1.format(tienvat)) +" đồng", new Font(bf,
					12, Font.NORMAL)); // cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("       - Tổng Tiền có VAT: "+ DinhDangTraphacoDMS(formatter1.format(tiensauthue)) +" đồng", new Font(bf, 12,
					Font.NORMAL)); // cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long) tiensauthue);
			String TienIN = (tien.substring(0, 1)).toUpperCase()
			+ tien.substring(1); // Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("       (Bằng chữ: " + TienIN + ").",
					new Font(bf, 12, Font.ITALIC)); // cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("       - Lý do: ", font10_normal);
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph(
					"       Hai bên đồng ý xác nhận vào biên bản này để làm căn cứ bù trừ vào công nợ theo số tiền trên và là chứng từ hợp pháp để khai trình thuế.",
					font10_normal);
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph(
					"       Biên bản này được lập thành 2 bản thống nhất của hai bên, làm cơ sở cho bên bán khai thuế GTGT theo đúng quy định.",
					font10_normal);
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f * CONVERT);
			para = new Paragraph(
					"ĐẠI DIỆN BÊN A"
					+ "                                                                               "
					+ "ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			document.add(tbl_footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private String dinhdangngay(String ngay) {
		String date = "";
		if (ngay.length() >= 8) {
			date = ngay.substring(8) + "/" + ngay.substring(5, 7) + "/"
			+ ngay.substring(0, 4);
		}
		return date;
	}

	private String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	private String DinhDangTraphacoDMS(String sotien) {
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}
}
