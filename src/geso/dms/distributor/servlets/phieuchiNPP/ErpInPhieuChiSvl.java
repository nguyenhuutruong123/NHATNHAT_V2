package geso.dms.distributor.servlets.phieuchiNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.distributor.beans.phieuchiNPP.IErpPhieuChiNPP;
import geso.dms.distributor.beans.phieuchiNPP.imp.ErpPhieuChiNPP;
import geso.dms.distributor.beans.thutienNPP.IErpThutienKhacNPP;
import geso.dms.distributor.beans.thutienNPP.imp.ErpThutienKhacNPP;
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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpInPhieuChiSvl extends HttpServlet{	
	private static final long serialVersionUID = 1L;

	public ErpInPhieuChiSvl()
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

		IErpPhieuChiNPP pttBean = new ErpPhieuChiNPP(id);
		pttBean.setUserId(userId);
		pttBean.setView(view);
		pttBean.setnppId(userId);
		pttBean.Init();

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
		//phieuchi
		this.CreatePxk(document, outstream, pttBean, userId);	
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


	private void form_phieuthu( Document document,ServletOutputStream outstream,IErpPhieuChiNPP pttBean,
			String ngayxuatphieu,String tennguoinop,String donvi,String diachi, String lydo, double tongtien,dbutils db, String userid){

		//lay noi xuat phieu O CHI NHANH 
		String qr_noiin="select npp.ten as ten, npp.DIACHI as diachi, "+    
		"(select ten from tinhthanh where PK_SEQ= npp.TINHTHANH_FK) as tinh, "+    
		"(select ten from QUANHUYEN where PK_SEQ= npp.QUANHUYEN_FK) as quan "+    
		"from NHANVIEN nv inner join nhaphanphoi npp on nv.CONVSITECODE=npp.SITECODE "+    
		"where nv.PHANLOAI = 1 and nv.PK_SEQ=" +userid;

		String tennoiin="C??NG TY C??? PH???N D?????C PH???M C???U LONG";
		String diachiin="150 ???????ng 14/9, Ph?????ng 5, TP V??nh Long, T??nh V??nh Long";
		ResultSet rsNoiin=db.get(qr_noiin);
		String loaiin="";
		if(rsNoiin!=null){
			try {
				while(rsNoiin.next()){
					loaiin="CN/NPP";
					tennoiin=rsNoiin.getString("ten");
					diachiin=rsNoiin.getString("diachi")+", "+rsNoiin.getString("quan") +", "+rsNoiin.getString("tinh");
				}
				rsNoiin.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)
		String nguoitao="";
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from ERP_PHIEUCHI nh  \n "+
		" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+pttBean.getId();
		String sonetid="";
		ResultSet rsSonet=db.get(qr_sonet);
		if(rsSonet!=null){
			try {
				while(rsSonet.next()){
					if(rsSonet.getString("sonetId")!=null){
						sonetid=rsSonet.getString("sonetId");
						nguoitao=rsSonet.getString("ten");
					}
				}
				rsSonet.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(2.0f*CONVERT, 2.0f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
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
			//tao header1
			PdfPTable tableheader =new PdfPTable(2);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);
			float[] withd= {13f*CONVERT,8.0f*CONVERT};
			tableheader.setWidths(withd);
			// trai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk(tennoiin.toUpperCase(),font10_Bold);
			cell.addElement(chunk);
			tableheader.addCell(cell);
			//phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setPaddingLeft(2.0f*CONVERT);
			chunk=new Chunk("M???u s???: 02-TT",font10_Bold);
			cell.addElement(chunk);
			tableheader.addCell(cell);
			// trai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk(diachiin,font10_Bold);
			chunk.setTextRise(1f);
			cell.addElement(chunk);
			tableheader.addCell(cell);
			//phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			chunk=new Chunk("(Ban h??nh theo th??ng t?? 200/2014/TT-BTC \n         ng??y 22/12/2014 c???a B??? T??i ch??nh)",font10_ilatic);
			chunk.setTextRise(1f);
			cell.addElement(chunk);
			tableheader.addCell(cell);

			//tua de phieu thu
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			cell.setPaddingLeft(6.5f*CONVERT);
			chunk=new Chunk("PHI???U CHI",new Font(bf,19,Font.BOLD));
			cell.addElement(chunk);
			tableheader.addCell(cell);

			//so phieu thu

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
			chunk=new Chunk("   S???: " +sonetid,new Font(bf,12,Font.BOLD));
			/*chunk.setTextRise(-2f);*/
			cell.addElement(chunk);
			tableheader.addCell(cell);

			document.add(tableheader);


			//============tao header1 noi dung: bang 2 cot
			PdfPTable tbl =new PdfPTable(2);
			tbl.setWidthPercentage(100);			
			float[] withd1= {19f*CONVERT,9.0f*CONVERT};
			tbl.setWidths(withd1);

			//cell trai


			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			chunk=new Chunk("                                                               Ng??y: "
					+ngayxuatphieu.substring(8)
					+"/"+ngayxuatphieu.substring(5,7)
					+"/"+ngayxuatphieu.substring(0,4),font12_normal);
			cell.addElement(chunk);

			chunk=new Chunk("H??? t??n ng?????i nh???n ti???n: "+tennguoinop,font12_normal);
			cell.addElement(chunk);

			chunk=new Chunk("????n v???: "+donvi,font12_normal);
			cell.addElement(chunk);

			chunk=new Chunk("?????a ch???: "+diachi,font12_normal);
			cell.addElement(chunk);

			chunk=new Chunk("L?? do chi: "+lydo,font12_normal);
			cell.addElement(chunk);

			chunk=new Chunk("S??? ti???n: "+DinhDangTraphacoDMS( formatter1.format(tongtien)) +" ?????ng",font12_Bold);
			cell.addElement(chunk);


			/*	doctienrachu doctien=new doctienrachu();
		chunk=new Chunk("(Vi???t b???ng ch???): "+doctien.docTien((long)tongtien),font12_normal);
		cell.addElement(chunk);

		chunk=new Chunk("K??m theo: Ch???ng t??? g???c",font12_normal);
		cell.addElement(chunk);*/

			tbl.addCell(cell);

			//cell phai
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_TOP);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			//cell add tbl 2 cot
			PdfPTable tbl_tk =new PdfPTable(2);
			tbl_tk.setWidthPercentage(100);			
			float[] withd2= {3f*CONVERT,5f*CONVERT};
			tbl_tk.setWidths(withd2);
			PdfPCell cell1 ;

			cell1=new PdfPCell(new Paragraph("T??i kho???n", font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorderWidthRight(0);
			cell1.setPaddingBottom(7);
			tbl_tk.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Ti???n VN??", font10_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setPaddingBottom(7);
			cell1.setBorderWidthLeft(0);
			tbl_tk.addCell(cell1);

			//lay tk co no
			//lay tk co no
			int count = 0;
			count = pttBean.getCount() ;
			System.out.println("Count: " + count);
			String[] TaiKhoanNoIds = pttBean.getTaiKhoanNoIds();
			String[] TaiKhoanCoIds = pttBean.getTaiKhoanCoIds();
			String[] Sotien = pttBean.getSotien();
			
			
			
			
			if (Sotien == null) {
				Sotien = new String[count];
				for (int i = 0; i < count; i++) {
					Sotien[i] = "";
				}
			}

			String[] Sotienno;
			Sotienno= new String [count];
			String[] Sotienco;
			Sotienco= new String [count];
			for (int i = 0; i < count; i++){
				Sotienno[i]=Sotien[i];
				Sotienco[i]=Sotien[i];
			}
			
			

			


			//gop tong tien neu cung tai khoan
			for (int i = 0; i < count-1; i++){
				String dt=TaiKhoanNoIds[i].trim();
				
				for (int j = i+1; j < count; j++){
					Double tien=Double.parseDouble(Sotienno[i].replaceAll(",", ""));
					if(dt.equals(TaiKhoanNoIds[j].trim())){
						TaiKhoanNoIds[j]="";
						Sotienno[i]=String.valueOf(tien +Double.parseDouble(Sotienno[j].replaceAll(",", "")));
						Sotienno[j]="0";
					}
				}
			}
			

			//gop tong tien neu cung tai khoan
			for (int i = 0; i < count-1; i++){
				String dt=TaiKhoanCoIds[i].trim();
			
				for (int j = i+1; j < count; j++){
					Double tien=Double.parseDouble(Sotienco[i].replaceAll(",", ""));
					if(dt.equals(TaiKhoanCoIds[j].trim())){
						
						System.out.println(" so sanh : tien i "+ i +" : "+ Sotienco[i] );
						System.out.println(" so sanh : tien j "+ i +" : "+ Sotienco[j] );
						System.out.println(" so sanh : tien "+ tien );

						
						TaiKhoanCoIds[j]="";
						Sotienco[i]=String.valueOf(tien +Double.parseDouble(Sotienco[j].replaceAll(",", "")));
						Sotienco[j]="0";
						
						System.out.println(" so sanh sau : tien i "+ i +" : "+ Sotienco[i] );
						System.out.println(" so sanh sau: tien j "+ i +" : "+ Sotienco[j] );
						System.out.println(" so sanh sau: tien "+ tien );
						
						System.out.println("-- \n --");
					}
				}
			}
			
			
		
			//doc danh sach co
			for (int i = 0; i < count; i++){
				System.out.println(" co : "+ i +": "+TaiKhoanCoIds[i] + " tien co : "+ i +": "+Sotienco[i]);
			}
			
			System.out.println("\n  ");
			
			//doc thu danh sach no
			for (int i = 0; i < count; i++){
				System.out.println(" no : "+ i +": "+TaiKhoanNoIds[i] +" tien no : "+ i +": "+Sotienno[i]);
				
			}
			
			
			

			//tach no rieng 

			for (int i = 0; i < count; i++)
			{
				String tien="0";
				if(Sotienno[i] != null || Sotienno[i].trim().length() >=0)
					tien=DinhDangTraphacoDMS(formatter.format(Double.parseDouble(Sotienno[i].replaceAll(",", ""))));

				if(!TaiKhoanNoIds[i].equals("")){

					String arr[]={"N??? "+TaiKhoanNoIds[i],tien};
					for (int j = 0; j < arr.length; j++) {

						cell1=new PdfPCell(new Paragraph(arr[j], font10_normal));
						if(j==0){
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBorderWidthRight(0);
						}
						else{
							cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
							cell1.setBorderWidthLeft(0);

						}
						cell1.setPaddingBottom(7);
						tbl_tk.addCell(cell1);
					}
				}
			}

			//tach co rieng 
			for (int i = 0; i < count; i++)
			{
				String tien="0";
				if(Sotienco[i] != null || Sotienco[i].trim().length() >=0)
					tien=DinhDangTraphacoDMS(formatter.format(Double.parseDouble(Sotienco[i].replaceAll(",", ""))));

				if(!TaiKhoanCoIds[i].equals("")){	
					String arr[]={"C?? "+TaiKhoanCoIds[i],tien};
					for (int j = 0; j < arr.length; j++) {
						cell1=new PdfPCell(new Paragraph(arr[j], font10_normal));
						if(j==0){
							cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
							cell1.setBorderWidthRight(0);
						}
						else{
							cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);

							cell1.setBorderWidthLeft(0);
						}
						cell1.setPaddingBottom(7);
						tbl_tk.addCell(cell1);
					}
				}
			}


			cell.addElement(tbl_tk);
			tbl.addCell(cell);
			document.add(tbl);


			//--add bang tien va dinh kem
			PdfPTable tbl_tien = new PdfPTable(1);
			tbl_tien.setWidthPercentage(100);
			tbl_tien.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_tien.getDefaultCell().setBorder(0);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_JUSTIFIED);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			doctienrachu doctien=new doctienrachu();
			chunk=new Chunk("(Vi???t b???ng ch???): "+doctien.docTien((long)tongtien),font12_normal);
			cell.addElement(chunk);
			chunk=new Chunk("K??m theo: Ch???ng t??? g???c",font12_normal);
			cell.addElement(chunk);
			tbl_tien.addCell(cell);
			document.add(tbl_tien);

			//--- tao bang chu ki
			//------------bang chu ki---------------------
			float[] wky = {10.0f*CONVERT, 12.0f*CONVERT, 9.0f*CONVERT };
			PdfPTable tbl_vat = new PdfPTable(wky.length);
			tbl_vat.setWidthPercentage(100);
			tbl_vat.setHorizontalAlignment(Element.ALIGN_CENTER);
			tbl_vat.getDefaultCell().setBorder(0);
			tbl_vat.setWidths(wky);

			cell = new PdfPCell();
			if(loaiin.equals("CN/NPP"))
			{
				para=new Paragraph("Gi??m ?????c", font12_Bold);				
			}
			else
			{			
				para=new Paragraph("Ban T???ng gi??m ?????c", font12_Bold);
			}
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			cell = new PdfPCell();				
			para=new Paragraph("K??? to??n tr?????ng", font12_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			cell = new PdfPCell();				
			para=new Paragraph("Ng?????i l???p phi???u", font12_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			//dong 2
			cell = new PdfPCell(new Paragraph("", font12_Bold));
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			cell = new PdfPCell(new Paragraph("", font12_Bold));
			cell.setBorder(0);
			tbl_vat.addCell(cell);
			
			
			cell = new PdfPCell();				
			para=new Paragraph(nguoitao, font12_normal);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			cell.setPaddingTop(1.3f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell.setBorder(0);
			tbl_vat.addCell(cell);


			document.add(tbl_vat);
			
			//==========tbl footer
			//cell add tbl 2 cot
			PdfPTable tbl_fotter =new PdfPTable(2);
			tbl_fotter.setWidthPercentage(100);		
			tbl_fotter.setSpacingBefore(0.3f*CONVERT);
			tbl_fotter.setSpacingAfter(0.3f*CONVERT);
			float[] withd3= {18f*CONVERT,10f*CONVERT};
			tbl_fotter.setWidths(withd3);
			tbl_fotter.getDefaultCell().setBorder(0);
			tbl_fotter.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell1=new PdfPCell(new Paragraph("???? nh???n ????? s??? ti???n (vi???t b???ng ch???): "+doctien.docTien((long)tongtien), font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
			cell1.setColspan(2);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph(" ", font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Ng??y "+ngayxuatphieu.substring(8) +" th??ng "+ngayxuatphieu.substring(5,7)+" n??m "+ngayxuatphieu.substring(0,4), font12_normal));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("            Ng?????i nh???n", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			cell1=new PdfPCell(new Paragraph("Th??? qu???", font12_Bold));
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setBorder(0);
			tbl_fotter.addCell(cell1);

			document.add(tbl_fotter);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}



	private void CreatePxk(Document document, ServletOutputStream outstream, IErpPhieuChiNPP btthBean, String userid) throws IOException
	{
		dbutils db = new dbutils();

		try
		{			
			//LAY THONG phieu thu khac
			String ngayxuatphieu=btthBean.getNgayButToan();
			String tennguoinop=btthBean.getNguoiNopTien();
			String diachi=btthBean.getDiachi();
			String donvi=btthBean.getDonvi();
			String lydo=btthBean.getDienGiai();
			Double tongtien=0.0;

			String qr="select  isnull(sotienthu,0) as  sotienthu from Erp_PhieuChi where pk_seq=" +btthBean.getId();
			ResultSet rs=db.get(qr);
			if(rs!=null){
				try {
					if(rs.next()){
						tongtien= rs.getDouble("sotienthu");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			//in hoa don phieu thu
			form_phieuthu(document, outstream, btthBean,ngayxuatphieu,tennguoinop,donvi,diachi,lydo,tongtien, db, userid);

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
