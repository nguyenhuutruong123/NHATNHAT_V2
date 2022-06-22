package geso.dms.distributor.servlets.chuyenkho;

import geso.dms.center.beans.dieuchinhtonkho.IErpChuyenkhoTT;
import geso.dms.center.beans.dieuchinhtonkho.imp.ErpChuyenkhoTT;
import geso.dms.distributor.beans.chuyenkho.IErpChuyenkhoNpp;
import geso.dms.distributor.beans.chuyenkho.imp.ErpChuyenkhoNpp;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

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

public class ErpInphieuxuatchuyennoiboNppPdfSvl extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ErpInphieuxuatchuyennoiboNppPdfSvl()
	{
		super();
	}
	float CONVERT = 28.346457f; // = 1cm


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		dbutils db = new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		// String userTen = (String) session.getAttribute("userTen");

		Utility util = new Utility();
		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String querystring = request.getQueryString();

		String id = util.getId(querystring); 
		if (id.length()==0)
			id = util.antiSQLInspection(request.getParameter("id")); 

		//LAY THONG TIN
		IErpChuyenkhoNpp lsxBean = new ErpChuyenkhoNpp(id);
		lsxBean.setUserId(userId); 
		lsxBean.init();

		//===========
		String task = request.getParameter("task");    
		/*if (querystring.indexOf("pdf") > 0)
		{*/

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=PhieuXuatKhoTT.pdf");

		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();

		this.form_phieuxuat(document, outstream, lsxBean ,db);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}


	private void form_phieuxuat(Document document,ServletOutputStream outstream,IErpChuyenkhoNpp pxkBean, dbutils db)
	{
		document.open();

		//====LAY THONG TIN ve lenh chuyen noi bo
		String lenhdieudong=pxkBean.getLenhdieudong(); //pkxBean la phieu chuyen kho HO,
		if(lenhdieudong.length()<=0 )
		{
			lenhdieudong=" ";
		}
		//String ngaydieudong=pxkBean.getNgaydieudong();
		String ngaydieudong=pxkBean.getNgayyeucau();
		
		String lddCua=pxkBean.getLDDcua();
		if(lddCua.length()<=0)
		{
			lddCua=" ";
		}
		
		String veviec=pxkBean.getLDDveviec();
		if(veviec.length()<=0)
		{
			veviec=" ";
		}
		
		String nguoivanchuyen=pxkBean.getNguoivanchuyen();
		String sohopdong=pxkBean.getSohopdong();
		if(sohopdong.length()<=0)
		{
			sohopdong=" ";
		}
		
		String phuongtienvanchuyen=pxkBean.getPtvanchuyen();
		if(phuongtienvanchuyen.length()<=0)
		{
			phuongtienvanchuyen=" ";
		}
		String khoxuatid=pxkBean.getKhoXuatId();
		String khonhapid=pxkBean.getKhoNhapId(); 
		String sodms=pxkBean.getId(); // pk_seq cua 
		
		String khoxuat=" ";
		String khonhap=" "; 
		String masothue=" ";
		String nguoitao=" ";

		// tim kho xuat 
		/*ResultSet khoxuatRs = pxkBean.getKhoXuatRs();
		if(khoxuatRs!=null){
			try {
				while(khoxuatRs.next()){
					if( khoxuatRs.getString("pk_seq").equals(khoxuatid))
					{
						khoxuat=khoxuatRs.getString("ten");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/

		//tim ma so thue ben xuat
		String qr_npp="select npp.masothue as masothue, isnull(npp.xuattaikho,' ') as khoxuat from ERP_CHUYENKHONPP ck "+
		" inner join nhaphanphoi npp on npp.PK_SEQ=ck.NPP_FK " +
		" where ck.pk_seq= "+pxkBean.getId();

		ResultSet rsMst=db.get(qr_npp);
		if( rsMst!=null){
			try {
				while(rsMst.next()){
					masothue=rsMst.getString("masothue");
					khoxuat=rsMst.getString("khoxuat");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		//lay kho nhan, lay tu donhang_fk cua e r p chuyen kho
		String qr_khonhan=" select isnull(kho.ten,'') as tenkho "+    
		" from nhaphang nh inner join NHAPHANG_DDH nh_dh on nh.PK_SEQ =nh_dh.nhaphang_fk  \n"+    
		" inner join kho kho on kho.pk_seq=nh.kho_fk \n "+    
		" where nh_dh.ddh_fk=(select ddh_fk from erp_chuyenkhonpp where pk_seq="+ pxkBean.getId() +")";

		System.out.print("\n lenh lay kho nhan: "+ qr_khonhan );

		ResultSet rskhonhan=db.get(qr_khonhan);
		if( rskhonhan!=null){
			try {
				while(rskhonhan.next()){
					//khonhap=rsKhoxuat.getString("xuattaikho");
					khonhap=rskhonhan.getString("tenkho");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		// LAY SO CHUNG TU(  sonetId RESET THEO THANG)
		//String qr_sonet="select sonetId FROM ERP_CHUYENKHONPP WHERE PK_SEQ="+pxkBean.getId();
		String qr_sonet="select nh.sonetId as sonetid,nv.ten as ten from ERP_CHUYENKHONPP nh \n "+
		" left join nhanvien nv on nv.PK_SEQ= nh.nguoisua \n  WHERE nh.PK_SEQ="+pxkBean.getId();
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


		//=====================do du lieu
		try {
			//format
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");				
			document.setPageSize(PageSize.A4);
			document.setMargins(0.5f*CONVERT, 1f*CONVERT, 0.0f*CONVERT, 0.5f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);		
			PdfPCell cell,cell_nho ;
			Paragraph para;
			Chunk chunk;
			document.open() ;


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 13, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font font3 = new Font(bf, 10, Font.BOLD);

			PdfPTable tableheader =new PdfPTable(2);
			float []wky1={15.5f,3.5f};
			tableheader.setWidthPercentage(100);	
			tableheader.setWidths(wky1);

			//rong
			cell = new PdfPCell(new Paragraph(" ",new Font(bf, 10, Font.BOLD)));
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableheader.addCell(cell);

			// sonet
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(4.6f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph(sonetid,new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tableheader.addCell(cell);

			//ngay
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(2.2f*CONVERT);
			cell.setPaddingTop(0.6f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			String [] ngayHD = ngaydieudong.split("-");
			para= new Paragraph("",new Font(bf, 8, Font.BOLD));
			if(ngayHD.length>=3){
				chunk =new Chunk(ngayHD[2] + "                     " + ngayHD[1] +"                   " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
				chunk.setTextRise(0.5f*CONVERT);
				para.add(chunk);
				para.setAlignment(Element.ALIGN_CENTER);
			}
			cell.addElement(para);
			tableheader.addCell(cell);


			//ma so thue
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(0.2f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph(masothue,new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tableheader.addCell(cell);

			document.add(tableheader);


			//============================= thong tin phieu chuyen kho noi bo
			//----------------------ADD INFO KHACH HANG : bang 1 cot -----------------------------
			PdfPTable tbl_khachhang =new PdfPTable(1);
			tbl_khachhang.setWidthPercentage(100);

			cell = new PdfPCell();
			cell.setBorder(0);
			//cell.setPaddingTop(-0.2f * CONVERT);
			//cell.setFixedHeight(0.6f*CONVERT);
			cell.setPaddingLeft(5.0f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT); //add bang 2 cot
			
			PdfPTable tbl_veviec =new PdfPTable(2);
			float withd_shd []={5f*CONVERT,11.5f*CONVERT};
			tbl_veviec.setWidths(withd_shd);
			tbl_veviec.setWidthPercentage(100);
			cell_nho = new PdfPCell(); //ad so lenh
			cell_nho.setBorder(0);

			cell_nho.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(lenhdieudong, new Font(bf, 10, Font.BOLD));
			cell_nho.addElement(para);
			tbl_veviec.addCell(cell_nho);

			cell_nho = new PdfPCell(); //ad ngay thang nam
			cell_nho.setBorder(0);
			cell_nho.setPaddingLeft(0.5f * CONVERT);
			cell_nho.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(ngayHD[2] + "                 " + ngayHD[1] +"               " + ngayHD[0], new Font(bf, 10, Font.BOLD));
			cell_nho.addElement(para);
			tbl_veviec.addCell(cell_nho);
			cell.addElement(tbl_veviec);
			tbl_khachhang.addCell(cell);



			//--dong 2 so lenh cua + ve viec
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.15f * CONVERT);
			cell.setFixedHeight(0.6f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT); //add bang 2 cot
			tbl_veviec =new PdfPTable(2);
			float withd_shd1 []={8.0f*CONVERT,8.0f*CONVERT};
			tbl_veviec.setWidths(withd_shd1);
			tbl_veviec.setWidthPercentage(100);
			
			cell_nho = new PdfPCell(); //ad cua
			cell_nho.setBorder(0);
			cell_nho.setPaddingLeft(1.0f * CONVERT);
			cell_nho.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(lddCua, new Font(bf, 10, Font.BOLD));
			cell_nho.addElement(para);
			tbl_veviec.addCell(cell_nho);

			cell_nho = new PdfPCell(); //ad ve viec
			cell_nho.setBorder(0);
			cell_nho.setPaddingLeft(1.5f * CONVERT);
			cell_nho.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph(veviec, new Font(bf, 10, Font.BOLD));
			cell_nho.addElement(para);
			tbl_veviec.addCell(cell_nho);
			cell.addElement(tbl_veviec);
			tbl_khachhang.addCell(cell);



			//--dong3 nguoi van chuyen + so hop dong: cell add bang 2 cot
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.15f * CONVERT);
			cell.setFixedHeight(0.6f*CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT); //add bang 2 cot
			PdfPTable tbl_shd_ngay =new PdfPTable(2);
			tbl_shd_ngay.setWidths(withd_shd1);
			tbl_shd_ngay.setWidthPercentage(100);
				PdfPCell cell_shd = new PdfPCell(); //ad nguoi van chuyen
				cell_shd.setBorder(0);
				cell_shd.setPaddingLeft(5.5f * CONVERT);
				cell_shd.setVerticalAlignment(Element.ALIGN_LEFT);
				para = new Paragraph(nguoivanchuyen, new Font(bf, 10, Font.BOLD));
				cell_shd.addElement(para);
				tbl_shd_ngay.addCell(cell_shd);

				PdfPCell cell_ngay = new PdfPCell(); //ad so hop dong
				cell_ngay.setBorder(0);
				cell_ngay.setPaddingLeft(2.0f * CONVERT);
				cell_ngay.setVerticalAlignment(Element.ALIGN_LEFT);
				para = new Paragraph(sohopdong, new Font(bf, 10, Font.BOLD));
				cell_ngay.addElement(para);
				tbl_shd_ngay.addCell(cell_ngay);
				cell.addElement(tbl_shd_ngay);
				tbl_khachhang.addCell(cell);


			//--dong4 phuong tien van chuyen
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.1f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(5.5f * CONVERT);
			para = new Paragraph( phuongtienvanchuyen , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong5 xuat tai kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.1f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( khoxuat , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//--dong6 nhap tai kho
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(-0.1f * CONVERT);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(3.5f * CONVERT);
			para = new Paragraph( khonhap , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);

			//========ad bang lon
			document.add(tbl_khachhang);


			// Table Content---------------------------------bang du lieu----------------------------------

			//== thong tin danh sach san pham

			String qrsp="select sp.PK_SEQ as pk_seq, sp.MA as ma, sp.TEN as ten, a.solo as solo, a.soluong as soluong , "+
			" (select isnull(dongia,0) as dongia from ERP_CHUYENKHONPP_SANPHAM where chuyenkho_fk="+pxkBean.getId()+"  and sanpham_fk =sp.PK_SEQ )  as dongia,  "+
			"(select donvi from DONVIDOLUONG where PK_SEQ = sp.DVDL_FK) as donvi, a.ngayhethan as handung  "+    
			"from ERP_CHUYENKHONPP_SANPHAM_CHITIET  "+    
			"a inner join sanpham sp on a.sanpham_fk=sp.PK_SEQ  "+    
			"where a.chuyenkho_fk = "+pxkBean.getId();

			System.out.println (" qr san pham :"+qrsp);
			ResultSet rsSP=db.get(qrsp);

			//================do data

			float[] tbl_withd = { 5.0f, 18f ,70f , 30.0f,10f, 18f, 22.0f, 30.0f };

			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setSpacingBefore(2.2f*CONVERT );
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			double tongtien = 0;

			int stt=1;
			int dongtang=1;
			String chuoi1="",chuoi2="",chuoicd="";
			int vitri1=0;
			
			if(rsSP != null)
			{
				try {
					while(rsSP.next())
					{
						String tenhang=rsSP.getString("ten");
						String dvtinh=rsSP.getString("donvi");
						double soLUONG =rsSP.getDouble("soluong");
						String  masp=rsSP.getString("ma");
						String solo=rsSP.getString("solo");
						double dongia =rsSP.getDouble("dongia");
						double thanhtien = soLUONG*dongia;
						tongtien +=thanhtien;
						String[] arr = new String[] {stt+"",masp,tenhang,solo,  dvtinh, 
								DinhDangTraphacoDMS(formatter1.format(soLUONG)),
								DinhDangTraphacoDMS(formatter.format(dongia)),
								DinhDangTraphacoDMS(formatter1.format(thanhtien))};
						

						//in
						for (int j = 0; j < tbl_withd.length; j++)
						{
							// NẾU LÀ TÊN SẢN PHẨM
							if(j==2)
							{
								if(arr[2].trim().length() <= 35)
									cells = new PdfPCell(new Paragraph(arr[j],new Font(bf, 10, Font.BOLD)));
								else
								{
									chuoi1 = arr[j].substring(0, 35);
									System.out.println("chuoi 1: " + chuoi1);
									//  System.out.println("vitri 1: " + vitri1);
									vitri1 = chuoi1.lastIndexOf(" ");
									if(vitri1>=0){
									chuoicd = chuoi1.substring(0, vitri1);
									System.out.println("chuoi cd: " + chuoicd);
									chuoi2 = arr[j].substring(vitri1+1, arr[j].length());
									System.out.println("chuoi 2: " + chuoi2);
									cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));  
									}
									else
									{
										chuoicd = chuoi1.substring(0, 35);
										chuoi2 = chuoi2.substring(35 + 1,arr[2].length());
										cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));  
									}
									
								}
							}
							else
								cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));     


							//canh format
							if(j==4){
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
								if(j>=5){
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
									if(j==7){
										cells.setPaddingLeft(0.4f*CONVERT);
									}
								}
								else
								{
									cells.setHorizontalAlignment(Element.ALIGN_LEFT);
								}

							cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
							cells.setBorder(0);
							cells.setFixedHeight(0.6f*CONVERT);
							tbl_sanpham.addCell(cells);

						}
						stt++; 
						dongtang++;
						
						if(arr[2].trim().length() > 35)
						{
							int dodaichuoi = chuoi2.length();
							System.out.println("chuoi222:"+chuoi2.length());
							while(dodaichuoi > 0)
							{
								int boiso=dodaichuoi/35;
								if(boiso > 0)
								{
									chuoi1 = chuoi2.substring(0,35);
									vitri1 = chuoi1.lastIndexOf(" ");
									if(vitri1>=0){
									System.out.println("chuoi 1: " + chuoi1);
									System.out.println("vitri 1: " + vitri1);
									chuoicd = chuoi1.substring(0, vitri1);
									System.out.println("chuoi cd: " + chuoicd);
									chuoi2 = chuoi2.substring(vitri1 + 1,dodaichuoi );
									System.out.println("chuoi 2: " + chuoi2);
									}
									else
									{
										chuoicd = chuoi1.substring(0, 35);
										chuoi2 = chuoi2.substring(35 + 1,dodaichuoi );
									}
								}
								else
								{
									chuoicd = chuoi2;
									chuoi2 = "";
								}

								for(int m = 0; m < arr.length; m++)
								{   
									if(m==2)
										cells = new PdfPCell(new Paragraph(chuoicd, new Font(bf, 10, Font.BOLD)));
									else
										cells = new PdfPCell(new Paragraph("", new Font(bf, 10, Font.BOLD)));

									//canh format
									if(m==4){
										cells.setHorizontalAlignment(Element.ALIGN_CENTER);
									}
									else
										if(m>=5){
											cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
										}
										else
										{
											cells.setHorizontalAlignment(Element.ALIGN_LEFT);
										}

									cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
									cells.setBorder(0);
									cells.setFixedHeight(0.6f*CONVERT);
									tbl_sanpham.addCell(cells);
								}
								dodaichuoi = chuoi2.length();

								dongtang++;

							}
						}
						
					}//cua for splist	
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
				int kk=0;
				while(kk < 19-dongtang)
				{
					String[] arr_bosung = new String[] { " ", " " , " ", " ", " "," "," "," " };
		
					for (int j = 0; j < arr_bosung.length; j++)
					{
						cells = new PdfPCell(new Paragraph(arr_bosung[j], new Font(bf, 12, Font.BOLD)));
						cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
						cells.setHorizontalAlignment(Element.ALIGN_CENTER);
						cells.setFixedHeight(0.6f*CONVERT);
						cells.setBorder(0);
											
						tbl_sanpham.addCell(cells);
					}
					
					kk++;
				}
				
				
				
				
				document.add(tbl_sanpham);

			
				//add bang tong tien
				PdfPTable tbl_tongtien = new PdfPTable(2);
				float[] wky = {2.0f, 6.0f};
				tbl_tongtien.setSpacingBefore(2.5f*CONVERT ); //add dong trong
				tbl_tongtien.setWidthPercentage(100);
				tbl_tongtien.setWidths(wky);
				tbl_tongtien.setHorizontalAlignment(Element.ALIGN_RIGHT);

				//dong tong tien
				cells = new PdfPCell(new Paragraph("", new Font(bf, 10, Font.BOLD)));
			    cells.setBorder(0);
				tbl_tongtien.addCell(cells);

				cells = new PdfPCell(new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)), new Font(bf, 10, Font.BOLD)));
				cells.setVerticalAlignment(Element.ALIGN_RIGHT);
				cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cells.setBorder(0);
				tbl_tongtien.addCell(cells);

				//dong chu ki

				cells = new PdfPCell(new Paragraph( nguoitao, new Font(bf, 10, Font.BOLD)));
				cells.setPaddingTop(2.5f*CONVERT);
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setBorder(0);
				tbl_tongtien.addCell(cells);

				cells = new PdfPCell(new Paragraph("", new Font(bf, 10, Font.BOLD)));
				cells.setBorder(0);
				tbl_tongtien.addCell(cells);

				document.add(tbl_tongtien);
			}	
			document.close();
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	private String getDate()
	{			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}


}
