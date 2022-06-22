package geso.dms.distributor.servlets.buttoantonghop;

import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHop;
import geso.dms.distributor.beans.buttoantonghop.imp.ErpButToanTongHop;
import geso.dms.distributor.beans.phieuchiNPP.IErpPhieuChiNPP;
import geso.dms.distributor.beans.phieuchiNPP.imp.ErpPhieuChiNPP;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.NumberFormat;

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
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lowagie.text.pdf.PdfCell;

public class ErpInPhieuKeToanSvl extends HttpServlet{

	public ErpInPhieuKeToanSvl()
	{
		super();
	}
	float CONVERT = 28.346457f;  // =1cm
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		Utility util = new Utility();	
		if (userId.length() == 0)
			userId = request.getParameter("userId");
		String querystring = request.getQueryString();
		String id = util.antiSQLInspection(request.getParameter("id"));
		System.out.println("id:"+ id);
		String view = util.antiSQLInspection(request.getParameter("view"));
		if(view == null)
			view = "";

		IErpButToanTongHop btthBean = new ErpButToanTongHop(id);
		btthBean.setUserId(userId);
		btthBean.setView(view);
		btthBean.setnppId(util.getIdNhapp(userId));
		btthBean.Init();

		System.out.println(" \n user  id "+userId +"\n");
		String type = request.getParameter("type");
		System.out.println("11type:"+type);
		if(type == null)
			type = "";

		//=============khai bao doc

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=HoaDonTaiChinh.pdf");
		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();
		//xuat hoa don
		this.CreatePxk(document, outstream, btthBean,userId);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}

	private void CreatePxk(Document document, ServletOutputStream outstream, IErpButToanTongHop btthBean, String userid) throws IOException
	{
		
		dbutils db = new dbutils();
		
		//lay noi xuat phieu O CHI NHANH 
		String qr_noiin="select npp.ten as ten, npp.DIACHI as diachi, "+    
						 "(select ten from tinhthanh where PK_SEQ= npp.TINHTHANH_FK) as tinh, "+    
						 "(select ten from QUANHUYEN where PK_SEQ= npp.QUANHUYEN_FK) as quan "+    
						 "from NHANVIEN nv inner join nhaphanphoi npp on nv.CONVSITECODE=npp.SITECODE "+    
						 "where nv.PHANLOAI = 1 and nv.PK_SEQ=" +userid;
		
		String tennoiin="CÔNG TY CỔ PHẦN DƯỢC PHẨM CỬU LONG";
		String diachiin="150 Đường 14/9, Phường 5, TP Vĩnh Long, Tĩnh Vĩnh Long";
		ResultSet rsNoiin=db.get(qr_noiin);
		if(rsNoiin!=null){
			try {
				while(rsNoiin.next()){
					tennoiin=rsNoiin.getString("ten");
					diachiin=rsNoiin.getString("diachi")+", "+rsNoiin.getString("quan") +", "+rsNoiin.getString("tinh");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		String nguoitao="";
		String qr_sonet="select nv.ten as ten from ERP_BUTTOANTONGHOP nh  \n "+
		" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+btthBean.getId();
		String sonetid="";
		System.out.println(" qr nguoi nguoitao :" + qr_sonet);
		ResultSet rsSonet=db.get(qr_sonet);
		if(rsSonet!=null){
			try {
				while(rsSonet.next()){
										
						nguoitao=rsSonet.getString("ten");
					
				}
				rsSonet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		

		try
		{			
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.0f*CONVERT, 1.0f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;
			document.open() ;
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 10, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 10, Font.BOLD);
			Font font10_ilatic = new Font(bf, 10, Font.ITALIC);
			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);

			//===header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			// ten ct
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk(tennoiin.toUpperCase(),font10_Bold);
			cell.addElement(chunk);
			chunk=new Chunk(diachiin,font10_normal);
			cell.addElement(chunk);
			tableheader.addCell(cell);

			// tieu de +ngay +so phieu
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setVerticalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingLeft(7.5f*CONVERT);
			chunk=new Chunk("PHIẾU KẾ TOÁN",new Font(bf,19,Font.BOLD));
			cell.addElement(chunk);
			chunk=new Chunk("     Ngày "+ btthBean.getNgayButToan().substring(8)+" tháng "+btthBean.getNgayButToan().substring(5,7)+" năm "+btthBean.getNgayButToan().substring(0,4),font10_Bold);
			cell.addElement(chunk);
			chunk=new Chunk("          Số phiếu: "+ btthBean.getId() , font10_Bold);
			cell.addElement(chunk);
			tableheader.addCell(cell);

			document.add(tableheader);

			//==bang du lieu
			float[] with = {1.5f*CONVERT, 4.5f*CONVERT,1.5f*CONVERT, 1.5f*CONVERT, 4.5f*CONVERT, 1.5f*CONVERT,4.0f*CONVERT, 2.5f*CONVERT, 3.0f*CONVERT };
			PdfPTable table1 = new PdfPTable(with.length);
			table1.setSpacingBefore(0.3f*CONVERT);
			table1.setWidthPercentage(100);
			table1.setWidths(with);
			// in tieu de
			String[] tieude = {"TK Nợ","Đối tượng nợ", "YT Nợ","TK Có","Đối tượng có","YT Có","Nội dung","Đơn vị","Phát sinh"};
			for(int i =0; i< tieude.length; i++){
				cell=new PdfPCell(new Paragraph(tieude[i], font10_Bold));
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				cell.setPaddingBottom(7);
				table1.addCell(cell);
			}

			// lay du lieu
			String[] TaiKhoanNoIds = btthBean.getTaiKhoanNoIds();
			String[] TaiKhoanCoIds = btthBean.getTaiKhoanCoIds();

			String[] DoiTuongNoIds = btthBean.getDoiTuongNoIds();
			String[] DoiTuongCoIds = btthBean.getDoiTuongCoIds();

			String[] loaiDoiTuongNo = btthBean.getDoituongNo();
			String[] loaiDoiTuongCo = btthBean.getDoituongCo();

			String[] YeuToNoIds = btthBean.getYeuToNoIds();
			String[] YeuToCoIds = btthBean.getYeuToCoIds();

			String[] loaiYeuToNo = btthBean.getYeuToNo();
			String[] loaiYeuToCo = btthBean.getYeuToCo();

			//tien
			double tongtien=0;
			int count = btthBean.getCount();
			String[] Sotien = btthBean.getSotien();
			if (Sotien == null) {
				Sotien = new String[count];
				for (int i = 0; i < count; i++) {
					Sotien[i] = "0";
				}
			}
			else
			{
				for (int i = 0; i < count; i++) {
					tongtien +=Double.parseDouble(Sotien[i].replaceAll(",", "")) ;

				}
			}
			
			//dien giai - noi dung
			String[] dg = btthBean.getDg();
			if (dg == null) {
				dg = new String[count];
				for (int i = 0; i < count; i++) {
					dg[i] = "";
				}
			}

			// in bang
			for (int i = 0; i < count; i++) {

				String doituongno="";
				String doituongco="";
				String ytco="";
				String ytno="";
				String sotien="";
				if (Sotien[i] == null ) 
					sotien="0";
				else
					sotien=formatter.format(Double.parseDouble(Sotien[i].replaceAll(",", "")));
				
				
				//doi tuong co
				if(loaiDoiTuongNo[i].trim().length() > 0){
					ResultSet rs = btthBean.getDoituongMap().get(loaiDoiTuongNo[i]);
					if(rs!= null)
					{	rs.beforeFirst();
					try {
						while (rs.next()) 
						{
							if (DoiTuongNoIds[i]!= null &&  rs.getString("ma").equals(DoiTuongNoIds[i])){
								/*doituongno=rs.getString("ma")+"--"+rs.getString("ten");*/
								doituongno=rs.getString("ten");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					}
				}

				//doi tuong co
				if(loaiDoiTuongCo[i].trim().length() > 0){
					ResultSet rs2 = btthBean.getDoituongMap().get(loaiDoiTuongCo[i]);
					rs2.beforeFirst();
					try {
						while (rs2.next()) 
						{
							if (DoiTuongCoIds[i]!= null &&  rs2.getString("ma").equals(DoiTuongCoIds[i])) {
								/*doituongco=rs2.getString("ma")+"--"+rs2.getString("ten");*/
								doituongco=rs2.getString("ten");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}


				//yeu to no
				if(loaiYeuToNo[i].trim().length() > 0){
					ResultSet rs = btthBean.getNhomhangRs();
					rs.beforeFirst();
					try {
						while (rs.next()) 
						{
							if (YeuToNoIds[i]!= null &&  rs.getString("ma").equals(YeuToNoIds[i])) {
								/*ytno=rs.getString("ma")+"--"+rs.getString("ten");*/
								ytno=rs.getString("ma");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				//yeu to co
				if(loaiYeuToCo[i].trim().length() > 0){
					ResultSet rs = btthBean.getNhomhangRs();
					rs.beforeFirst();
					try {
						while (rs.next()) 
						{
							if (YeuToCoIds[i]!= null &&  rs.getString("ma").equals(YeuToCoIds[i])) {
								/*ytco=rs.getString("ma")+"--"+rs.getString("ten");*/
								ytco=rs.getString("ma");
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}

				//du lieu do ra
				String[] data = {TaiKhoanNoIds[i],doituongno,ytno,TaiKhoanCoIds[i],doituongco,ytco,dg[i]," ",DinhDangTraphacoDMS(sotien) };

				for(int j =0; j< data.length; j++){
					cell=new PdfPCell(new Paragraph(data[j], font10_normal));
					cell.setPaddingBottom(7);
					cell.setVerticalAlignment(Element.ALIGN_CENTER);
					if(j==0 || j==2|| j==3 || j==5){
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					else
						if(j==7 || j==8){
							cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
						else
							cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table1.addCell(cell);
				}
			}
			//tổng cộng bên dưới.
			cell = new PdfPCell( new Paragraph("Cộng", font10_Bold));
			cell.setColspan(8);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingBottom(7);
			cell.setPaddingTop(5);
			table1.addCell(cell);

			cell = new PdfPCell( new Paragraph(DinhDangTraphacoDMS(formatter.format(tongtien)), font10_Bold));
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			cell.setPaddingTop(5);
			table1.addCell(cell);
			document.add(table1);

			//==footer == bang 3 cot

			//cell add tbl 2 cot
			float[] withd3= {10f*CONVERT,10f*CONVERT,10f*CONVERT};
			PdfPTable tbl_fotter =new PdfPTable(withd3.length);
			tbl_fotter.setWidthPercentage(100);		
			tbl_fotter.setWidths(withd3);
			tbl_fotter.setSpacingBefore(0.3f*CONVERT);
			tbl_fotter.getDefaultCell().setBorder(0);
			tbl_fotter.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell1;
		

			//dong 1
			cell1=new PdfPCell(new Paragraph(" ", font12_normal));
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph(" ", font12_normal));
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Ngày "+ btthBean.getNgayButToan().substring(8)+" tháng "+btthBean.getNgayButToan().substring(5,7)+" năm "+btthBean.getNgayButToan().substring(0,4), font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			//dong 2
			cell1.setPaddingLeft(2.0f*CONVERT);
			cell1=new PdfPCell(new Paragraph("Tổng giám đốc", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Kế toán trưởng", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);


			cell1=new PdfPCell(new Paragraph("Lập bảng \n \n \n ", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);
			
			
			//dong 3
			cell1=new PdfPCell(new Paragraph(" ", font12_normal));
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph(" ", font12_normal));
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph(nguoitao, font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			document.add(tbl_fotter);

			document.close();
			db.shutDown();

		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
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
