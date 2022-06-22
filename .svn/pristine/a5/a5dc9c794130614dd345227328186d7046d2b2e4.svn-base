package geso.dms.distributor.servlets.dondathang;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.servlets.reports.TinhHinhThucHienCTNPPSvl;
import geso.dms.distributor.beans.dondathang.IErpDondathangDoitac;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangDoitac;
import geso.dms.distributor.beans.donhang.IDonhang;
import geso.dms.distributor.beans.donhang.ISanpham;
import geso.dms.distributor.beans.donhang.imp.Donhang;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class InBangKeETCpdfSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InBangKeETCpdfSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/PHUCVINH/index.jsp");
		}else{
			IErpDondathangDoitac dhBean;
			dbutils db;
		
			Utility util=new Utility();
			String querystring=request.getQueryString();
			String dhid=util.getId(querystring);
			System.out.println("Id: "+dhid);
			userId=util.getUserId(querystring);
		
			dhBean=new ErpDondathangDoitac(dhid); 
			dhBean.setUserId(userId); //phai co UserId truoc khi Init
			dhBean.init();	
			dhBean.setKhId(dhBean.getKhId());
			List<ISanpham> splist = null;
			List<ISanpham> spkmlist= null;
			Hashtable<String, Float> scheme_tongtien = null;
			Hashtable<String, Float> scheme_chietkhau = null;
			db = new dbutils();
		

			//Đổi trạng thái Đã in đơn hàng
			String msg = "";
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition"," inline; filename=HoaDonGTGT.pdf");
		
			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();
			this.CreatePxk(document, outstream, splist,spkmlist,scheme_tongtien,scheme_chietkhau, dhBean, db);
			db.shutDown();
			/*
			splist.clear();
			spkmlist.clear();
			scheme_tongtien.clear();
			scheme_chietkhau.clear();
			*/
			dhBean.DBclose();
			util=null;
			
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/PHUCVINH/index.jsp");
		}else{
			IErpDondathangDoitac dhBean;
			dbutils db;
			userId = cutil.antiSQLInspection(request.getParameter("userId"));
			String id=cutil.antiSQLInspection(request.getParameter("id"));
		
			dhBean=new ErpDondathangDoitac(id);
			dhBean.setUserId(userId); //phai co UserId truoc khi Init
			dhBean.init();
			dhBean.setKhId(dhBean.getKhId());
	
			//dhBean.CreateSpDisplay();
		
			List<ISanpham> splist = null;
			List<ISanpham> spkmlist = null;
			Hashtable<String, Float> scheme_tongtien = null;
			Hashtable<String, Float> scheme_chietkhau = null;
			db = new dbutils();
			
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition"," inline; filename=HoaDonGTGT.pdf");
		
			Document document = new Document();
			ServletOutputStream outstream = response.getOutputStream();
			this.CreatePxk(document, outstream, splist,spkmlist,scheme_tongtien,scheme_chietkhau, dhBean, db);
			db.shutDown();
			splist.clear();
			spkmlist.clear();
			scheme_tongtien.clear();
			scheme_chietkhau.clear();
			dhBean.DBclose();
			cutil=null;
			
		}

	}

	private void CreatePxk(Document document, ServletOutputStream outstream, List<ISanpham> spList, List<ISanpham> spkmlist,Hashtable<String, Float> scheme_tongtien,Hashtable<String, Float> scheme_chietkhau, IErpDondathangDoitac dhBean, dbutils db) throws IOException
	{
		try
		{		
			db = new dbutils();
			
			String tennpp="";
			String diachi="";
			String dienthoai="";
			String masothue="";
			
			String sql_getinfodis="select ten,isnull(diachi,'Chua xac dinh') as diachi,isnull( masothue,'Chua xac dinh') as masothue ,isnull( dienthoai,'Chua xac dinh') as dienthoai from nhaphanphoi where pk_seq='"+dhBean.getNppId()+"'";
			ResultSet rs=db.get(sql_getinfodis);
			try{
				if(rs.next()){
					tennpp=rs.getString("ten");
					diachi=rs.getString("diachi");
					dienthoai=rs.getString("dienthoai");
					masothue=rs.getString("masothue");
				}
				rs.close();
			}catch(Exception er){
				
			}
			
			NumberFormat formatter = new DecimalFormat("#,###,###"); 
			NumberFormat formatter1 = new DecimalFormat("#,###,###.####"); 
			
			PdfWriter.getInstance(document, outstream);
			document.open();
			//lay doi tuong khach hang
			
			//chi dinh BaseFont.IDENTITY_H de co the go tieng viet
			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 15, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			Font fontnomar=new Font(bf, 9,Font.NORMAL);
			Font fontnomarB = new Font(bf, 9,Font.BOLD);
			Font normal_font = new Font(bf, 12, Font.BOLD);
			//font2.setColor(BaseColor.GREEN);
			 //KHAI BAO 1 BANG CO BAO NHIEU COT
			
			PdfPTable tableheader=new PdfPTable(3);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] withsheader = {30.0f,50.0f,30.0f};
			tableheader.setWidths(withsheader); 
			
			Image hinhanh=Image.getInstance( getServletContext().getInitParameter("path")+"/salesup.jpg");	
			hinhanh.scalePercent(35);
			hinhanh.setAlignment(Element.ALIGN_CENTER);
			PdfPCell cellslogo = new PdfPCell(hinhanh);
			
			cellslogo.setPadding(10);
			
			tableheader.addCell(cellslogo);
			
			Paragraph pxk = new Paragraph("CHI TIẾT ĐƠN HÀNG BÁN ĐỐI TÁC", normal_font);
			pxk.setSpacingAfter(10);
			pxk.setAlignment(Element.ALIGN_CENTER);
			
			PdfPCell celltieude=new PdfPCell();
			celltieude.addElement(pxk);
			Paragraph dvbh = new Paragraph("Đơn vị bán hàng : "+ tennpp+"\n "+"Số ĐT "+dienthoai , fontnomar);
			dvbh.setSpacingAfter(3);
			dvbh.setAlignment(Element.ALIGN_CENTER);
			celltieude.addElement(dvbh);
			
			tableheader.addCell(celltieude);
			
			PdfPCell cellinfo = new PdfPCell();
			
			cellinfo.addElement(new Paragraph("Số chứng từ     : "+dhBean.getId(),fontnomar));
						 
			String ngay=dhBean.getNgayyeucau().substring(8,10)+"-" +dhBean.getNgayyeucau().substring(5,7)+"-"+dhBean.getNgayyeucau().substring(0,4);
			cellinfo.addElement(new Paragraph("Ngày chứng từ : " + ngay,fontnomar));
			tableheader.addCell(cellinfo);
						
			document.add(tableheader);						
			
			PdfPTable table_info=new PdfPTable(1);
			float[] with3 = {50.0f};//SET DO RONG CAC COT
			table_info.setWidthPercentage(100);
			table_info.setWidths(with3);
			PdfPCell cell111= new PdfPCell();
			
			String tenkh="";
			String diachikh="";
			String dienthoaikh="";
			String masothuekh="";
			String maFAST = "";
			String chucuahieu="";
			String sql_getinfokh = "";
			
			String query = "select khachhang_fk, npp_dat_fk npp_fk from erp_dondathangnpp where pk_Seq = "+dhBean.getId();
			String _khachhang_fk = "";
			String _npp_fk = "";
			ResultSet temprs = db.get(query);
			try {
				while (temprs.next()) {
					_khachhang_fk = temprs.getString("khachhang_fk");
					_npp_fk = temprs.getString("npp_fk");
				}
				temprs.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			
			if (_khachhang_fk != null && _khachhang_fk.length() > 0 ) {
				sql_getinfokh = "\n select isnull(chucuahieu,'NA')chucuahieu,ten,isnull(dienthoai,'NA') as dienthoai," +
					"\n isnull(diachi,'NA') as diachi,isnull(masothue,'NA') as masothue, maFAST " +
					"\n from khachhang where pk_seq = " +_khachhang_fk;
			}
			else {
				sql_getinfokh = "\n select isnull(chucuahieu,'NA')chucuahieu,ten,isnull(dienthoai,'NA') as dienthoai," +
				"\n isnull(diachi,'NA') as diachi,isnull(masothue,'NA') as masothue, maFAST " +
				"\n from nhaphanphoi where pk_seq = " +_npp_fk;
			}
			
			ResultSet rs_kh  = db.get(sql_getinfokh);
			try{
				if(rs_kh!=null){
					while(rs_kh.next()){
						tenkh= rs_kh.getString("ten");
						diachikh= rs_kh.getString("diachi");
						dienthoaikh= rs_kh.getString("dienthoai");
						masothuekh= rs_kh.getString("masothue");
						maFAST = rs_kh.getString("maFAST");
						chucuahieu=rs_kh.getString("chucuahieu");
					}
				}
				rs_kh.close();

			}catch(Exception er ){
				er.printStackTrace();
			
			}
			
			//lay dai dien kinh doanh + ghichu đơn hàng
			String ghichu = "";
			String ddkdten="";
			String ddkddt= "";
			String sql_getddkd = "\n select pk_seq,ten, dienthoai from daidienkinhdoanh " +
					"\n where pk_seq = (select ddkd_fk from erp_dondathangnpp where pk_seq = "+dhBean.getId()+")";
			ResultSet rs_getddkd = db.get(sql_getddkd);
			System.out.println("Get DDKD: "+sql_getddkd);
			try{
				if(rs_getddkd != null){
				
					while(rs_getddkd.next()){
						ddkdten=rs_getddkd.getString("ten");
						ddkddt= rs_getddkd.getString("dienthoai");
					}
				}
				rs_getddkd.close();
				
				query = "select isnull(ghichu,'')ghichu from erp_dondathangnpp where pk_seq = "+dhBean.getId();
				rs_getddkd= db.get(query);
				rs_getddkd.next();
				ghichu = rs_getddkd.getString("ghichu");
				rs_getddkd.close();
			}
			catch(Exception er ){
				er.printStackTrace();
			}
			
			
			cell111.addElement(new Paragraph("Nhân viên bán hàng : "+ ddkdten  + "      Điện thoại : " +  ddkddt,fontnomar));
			cell111.addElement(new Paragraph("Mã khách hàng : " + maFAST + "   Tên khách hàng : "+ tenkh ,fontnomar));
			cell111.addElement(new Paragraph("Tên người mua hàng : " + chucuahieu ,fontnomar));
			cell111.addElement(new Paragraph("Số điện thoại : " + dienthoaikh ,fontnomar));
			cell111.addElement(new Paragraph("Địa chỉ : " + diachikh ,fontnomar));
			cell111.addElement(new Paragraph("Ghi chú : " + ghichu ,fontnomar));
			cell111.setPaddingBottom(10);
			table_info.addCell(cell111);
			
			document.add(table_info);
			//Table Content
			PdfPTable table = new PdfPTable(11);//Chu y nhe 6 cot o day, thi xuong duoi nho set withs la 6 cot
			table.setWidthPercentage(100);//chieu dai cua báº£ng 
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] withs = {5.0f, 27.0f, 11.0f, 10.0f, 10.0f, 8.0f, 17.0f, 6.0f, 13.0f, 13.0f, 13.0f}; 	
			System.out.println("0");
	        table.setWidths(withs);
			String[] th = new String[]{"STT", "Tên hàng", "Số lượng", "Số lô", "Ngày hết hạn", "ĐVT", "Đơn giá", "VAT", "CK", "Tiền VAT", "Thành tiền"};
			PdfPCell[] cell = new PdfPCell[11];
			for(int i=0; i < 11 ; i++)
			{
				cell[i] = new PdfPCell(new Paragraph(th[i], font2));
				cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell[i].setBackgroundColor(BaseColor.LIGHT_GRAY);		
				table.addCell(cell[i]);			
			}
			
			float size= 9.0f;
			Font font4 = new Font(bf, size );
			PdfPCell cells_detail = new PdfPCell();
			double totalle=0;
			String[] spMa = dhBean.getSpMa();
			String[] spTen = dhBean.getSpTen();
			String[] spSoluong = dhBean.getSpSoluong();
			String[] spDonvi = dhBean.getSpDonvi();
			String[] spGianhap = dhBean.getSpGianhap();
			String[] spVat = dhBean.getSpVat();
			
			for(int i = 0; i < spMa.length; i++)
			{
				//ISanpham sanpham = (ISanpham)spList.get(i);
				double dongia=Double.parseDouble(spGianhap[i]);
				
				double tienvat = Double.parseDouble(spSoluong[i])* Double.parseDouble(spGianhap[i]) 
								/100 * Double.parseDouble(spVat[i]);
						
				String[] arr = new String[]{Integer.toString(i+1), spTen[i],spSoluong[i], " ", " ", spDonvi[i] ,formatter1.format(Double.parseDouble(spGianhap[i])),
						formatter1.format(Double.parseDouble(spVat[i])), "0", formatter.format( tienvat ), formatter.format(Double.parseDouble(spSoluong[i])* Double.parseDouble(spGianhap[i]) + tienvat )};

				totalle = totalle + Double.parseDouble(spSoluong[i])* Double.parseDouble(spGianhap[i]) + tienvat;
				for(int j=0; j < 11; j++)
				{
					cells_detail = new PdfPCell(new Paragraph(arr[j], font4));
					if(j==1){
						cells_detail.setHorizontalAlignment(Element.ALIGN_LEFT);
					
					} 
					else if(j <= 4 && j != 1) {
						cells_detail.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					else
						cells_detail.setHorizontalAlignment(Element.ALIGN_RIGHT);
					
					cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					//cells_detail.setBorderWidthBottom(0);
					//cells_detail.setBorderWidthTop(0);
					table.addCell(cells_detail);
					
				}	
			}
			
			query = "\n select row_number() over (order by b.scheme) #,isnull (sum (tonggiatri),0) tienckkm, b.scheme from donhang_ctkm_trakm a inner join ctkhuyenmai b on b.pk_seq = a.ctkmid " +
					"\n where donhangid = "+dhBean.getId() +" and spma is null group by donhangid, b.scheme";
			double tienckkm = 0.0;
			double tongtienckkm = 0.0; 
			String scheme = "";
			int stt = 0;
			int _first = 0;
			rs = db.get(query);
			try {
				while (rs.next()) {
					stt++;
					_first = rs.getInt("#");
					
					if (_first == 1) {
					PdfPCell cells_ckkm = new PdfPCell();
					cells_ckkm.setHorizontalAlignment(Element.ALIGN_CENTER);
					Paragraph ckkm1=new Paragraph("Tiền CK/KM", font2);
					ckkm1.setAlignment(Element.ALIGN_LEFT);
					cells_ckkm.addElement(ckkm1);
					cells_ckkm.setColspan(11);
					table.addCell(cells_ckkm); }
					
					tienckkm = rs.getDouble("tienckkm");
					scheme = rs.getString("scheme");
					tongtienckkm += tienckkm;
					if (tienckkm > 0.0) {
						String[] arr = new String[] {String.valueOf(stt),scheme,"","","","","","",formatter.format(Double.parseDouble(String.valueOf(tienckkm))),"",""};
						for (int i = 0; i < 11; i++) {
							cells_detail = new PdfPCell(new Paragraph(arr[i], font4));
							if(i==1){
								cells_detail.setHorizontalAlignment(Element.ALIGN_LEFT);
							
							} 
							else if(i <= 4 && i != 1) {
								cells_detail.setHorizontalAlignment(Element.ALIGN_CENTER);
							}
							else
								cells_detail.setHorizontalAlignment(Element.ALIGN_RIGHT);
							
							cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);
							table.addCell(cells_detail);
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("Tiền CK đầu: "+tongtienckkm);
			
			
			if(spkmlist != null && spkmlist.size() >0 ){
				
				PdfPCell cellkhuyenmai = new PdfPCell();
				cellkhuyenmai.setHorizontalAlignment(Element.ALIGN_CENTER);
				Paragraph km1=new Paragraph("Hàng khuyến mãi", font2);
				km1.setAlignment(Element.ALIGN_LEFT);
				cellkhuyenmai.addElement(km1);
				cellkhuyenmai.setColspan(11);
				table.addCell(cellkhuyenmai);

				int demtt=0;
				for(int i = 0; i < spkmlist.size(); i++)
				{
					ISanpham sanpham = (ISanpham)spkmlist.get(i);
					double dongia=Double.parseDouble(sanpham.getDongia());
					
					double chietkhau=Double.parseDouble(sanpham.getSoluong())* Double.parseDouble(sanpham.getDongia()) /100 * Double.parseDouble(sanpham.getChietkhau());
					
					demtt=demtt+1;
					String[] arr = new String[]{"" + demtt, sanpham.getTensanpham() ,sanpham.getSoluong(), sanpham.getSOLo(), sanpham.getNgayHetHan(), sanpham.getDonvitinh(),formatter1.format(dongia),
							formatter1.format(Double.parseDouble(sanpham.getChietkhau())),formatter.format(chietkhau),formatter.format(Double.parseDouble(sanpham.getSoluong())* Double.parseDouble(sanpham.getDongia())-chietkhau ),"-"};
					
					for(int j=0; j < 11; j++)
					{
						cells_detail = new PdfPCell(new Paragraph(arr[j], font4));
						if(j==1){
							cells_detail.setHorizontalAlignment(Element.ALIGN_LEFT);
						
						} 
						else if(j <= 4 && j != 1) {
							cells_detail.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
						else
							cells_detail.setHorizontalAlignment(Element.ALIGN_RIGHT);
						
						cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cells_detail);
						
					}
				}	
			}

		
			/*//hang khuyen mai
			int demtt=0;
			for(int i = 0; i < spkmlist.size(); i++)
			{
				ISanpham sanpham = (ISanpham)spkmlist.get(i);
				double dongia=Double.parseDouble(sanpham.getDongia());
				
				double chietkhau=Double.parseDouble(sanpham.getSoluong())* Double.parseDouble(sanpham.getDongia()) /100 * Double.parseDouble(sanpham.getChietkhau());
				String sql_getkho="select kho_fk,kho.ten  from ctkhuyenmai inner join kho on  kho.pk_Seq=kho_fk  where scheme ='"+ sanpham.getCTKM()+"'";
				//System.out.println(sql_getkho);
				
				rs = db.get(sql_getkho);
				String TenKho="Khuyến mại";
				try{
					if(rs.next())
					{
						TenKho=rs.getString("ten");
					}
					rs.close();
				}catch(Exception er){
					System.out.println("Loi tai day :"+er.toString());
				}
				demtt=demtt+1;
				String[] arr = new String[]{""+demtt,sanpham.getMasanpham(), sanpham.getTensanpham(),TenKho ,sanpham.getSoluong(),sanpham.getDonvitinh(),formatter1.format(dongia),formatter1.format(Double.parseDouble(sanpham.getChietkhau())),formatter.format(chietkhau),formatter.format(Double.parseDouble(sanpham.getSoluong())* Double.parseDouble(sanpham.getDongia())-chietkhau )};
				
				for(int j=0; j < 10; j++)
				{
					cells_detail = new PdfPCell(new Paragraph(arr[j], font4));
					if(j==2){
						cells_detail.setHorizontalAlignment(Element.ALIGN_LEFT);
					}else{
						cells_detail.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					
					cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cells_detail);
					
				}
			}	*/
			
			/*//Khai  bao 1 bien luu tien khuyen mai
			float tongtienkhuyenmai=0;
			// Xuat ra khuyen mai tra tien
			if(scheme_tongtien.size() > 0)
			{
				Enumeration<String> keys = scheme_tongtien.keys();
				while(keys.hasMoreElements())
				{
					String key = keys.nextElement();
					tongtienkhuyenmai=tongtienkhuyenmai+scheme_tongtien.get(key);
					String tongtien= Float.toString(scheme_tongtien.get(key));
					String sql_tenschem="select diengiai from ctkhuyenmai where scheme='"+key+"'";
					rs	=	db.get(sql_tenschem);
					String Tenschem="";
					try{
						if(rs.next()){					
							Tenschem=rs.getString("diengiai");						
						}
						rs.close();
					}catch(Exception er){
						System.out.println("Loi tai day :"+er.toString());
					}
					demtt=demtt+1;
					String[] arr = new String[]{""+demtt,key,Tenschem, "", "" ,"" ,"","","","-"+tongtien };
					for(int j=0; j < 10; j++)
					{
						cells_detail = new PdfPCell(new Paragraph(arr[j], font4));
						if(j==2){
							cells_detail.setHorizontalAlignment(Element.ALIGN_LEFT);
						}else{
							cells_detail.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
						
						cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cells_detail);
						
					}
				}
				
			}

			if(scheme_chietkhau.size() > 0)
			{
				
				Enumeration<String> keys = scheme_chietkhau.keys();
				while(keys.hasMoreElements())
				{
					String key = keys.nextElement();
					String sql_tenschem="select diengiai from ctkhuyenmai where scheme='"+key+"'";
					rs	=	db.get(sql_tenschem);
					String Tenschem="";
					try{
						if(rs.next()){						
							Tenschem=rs.getString("diengiai");					
						}
						rs.close();
					}catch(Exception er){
						System.out.println("Loi tai day :"+er.toString());
					}
					
					tongtienkhuyenmai=tongtienkhuyenmai+scheme_chietkhau.get(key);
					String tienchietkhau= Float.toString(scheme_chietkhau.get(key));
					demtt=demtt+1;
					String[] arr = new String[]{""+demtt,key,Tenschem, "", "" ,"" ,"","","-"+tienchietkhau,"" };
					for(int j=0; j < 10; j++)
					{
						cells_detail = new PdfPCell(new Paragraph(arr[j], font4));
						if(j==2){
							cells_detail.setHorizontalAlignment(Element.ALIGN_LEFT);
						}else{
							cells_detail.setHorizontalAlignment(Element.ALIGN_CENTER);
						}
						cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);

						table.addCell(cells_detail);
						
					}
				}
				
			}*/
			
			//CHIET KHAU
			double tongtienCK = 0;
			String[] tichluy_scheme = null;
			String[] tichluy_vat  = null;
			String[] tichluy_tongtien  = null;
			
			if(tichluy_scheme != null)
			{
				for(int i = 0; i < tichluy_scheme.length; i++)
				{
					double _vat = Double.parseDouble(tichluy_vat[i].replaceAll(",", ""));
					double _chietkhau = Double.parseDouble(tichluy_tongtien[i].replaceAll(",", ""));
					
					double _tienthue = _vat * _chietkhau / 100;
					double _thanhtien = _chietkhau + _tienthue;
					
					tongtienCK += _thanhtien;
					
					String[] arr = new String[]{ tichluy_scheme[i], Double.toString(_vat), formatter.format(_chietkhau), formatter.format(_tienthue), formatter.format(_thanhtien) };
					for(int j = 0; j < arr.length; j++)
					{
						if(j == 0)
						{
							cells_detail = new PdfPCell(new Paragraph(arr[j], font2));
							cells_detail.setColspan(7);
							cells_detail.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
						else
						{
							cells_detail = new PdfPCell(new Paragraph(arr[j], font4));
							cells_detail.setHorizontalAlignment(Element.ALIGN_RIGHT);
						}
						
						cells_detail.setVerticalAlignment(Element.ALIGN_MIDDLE);
						table.addCell(cells_detail);
						
					}
				}
			}
			
			PdfPCell cell11 = new PdfPCell();
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			Paragraph km=new Paragraph("Cộng tiền hàng ", font2);
			km.setAlignment(Element.ALIGN_RIGHT);
			cell11.addElement(km);
			cell11.setColspan(10);
			table.addCell(cell11);
			
			PdfPCell cell12 = new PdfPCell();
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			Paragraph tongtien =new Paragraph( formatter.format(totalle), fontnomarB);
			tongtien.setAlignment(Element.ALIGN_RIGHT);
			cell12.addElement(tongtien);
			table.addCell(cell12);
			
			
			// tong tien khuyen mai
			cell11 = new PdfPCell();
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			km=new Paragraph("Tổng tiền chiết khấu ", font2);
			km.setAlignment(Element.ALIGN_RIGHT);
			cell11.addElement(km);
			cell11.setColspan(10);
			table.addCell(cell11);
			//
			//tongtienCK += tongtienckkm; 
			tongtienCK += Double.parseDouble("0");
			tongtienCK = Math.abs(tongtienCK);
			cell12 = new PdfPCell();
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			tongtien =new Paragraph(formatter1.format(tongtienCK) , font2);
			tongtien.setAlignment(Element.ALIGN_RIGHT);
			cell12.addElement(tongtien);
			table.addCell(cell12);
			//
			cell11 = new PdfPCell();
			cell11.setHorizontalAlignment(Element.ALIGN_RIGHT);
			km=new Paragraph("Tổng tiền thanh toán(đã làm tròn) ", fontnomarB);
			km.setAlignment(Element.ALIGN_RIGHT);
			cell11.addElement(km);
			cell11.setColspan(10);
			table.addCell(cell11);
			//
			//double tongtiensauthue=totalle/100 *10 +totalle;			
			cell12 = new PdfPCell();
			cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
			tongtien =new Paragraph(formatter.format(totalle - tongtienCK) , fontnomar);
			tongtien.setAlignment(Element.ALIGN_RIGHT);
			cell12.addElement(tongtien);
			table.addCell(cell12);
			
			//doc tien bang chu
			doctienrachu doctien=new doctienrachu();
			 cell11 = new PdfPCell();
			 cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			 //Long tongtienst= Math.round(tongtiensauthue-tongtienkhuyenmai);
			 Long tongtienst= Math.round(totalle - tongtienCK);
			km=new Paragraph("Số tiền bằng chữ : " + doctien.tranlate(tongtienst+""),  font2);
			km.setAlignment(Element.ALIGN_CENTER);
			cell11.addElement(km);
			cell11.setColspan(10);
			table.addCell(cell11);
			
			document.add(table);
			
		
			PdfPTable tablefooter=new PdfPTable(4);
			tablefooter.setWidthPercentage(100);//chieu dai cua báº£ng 
			tablefooter.setHorizontalAlignment(Element.ALIGN_CENTER);
			float[] withfooterder = {35.0f,35.0f,35.0f,35.0f};//SET DO RONG CAC COT
			tablefooter.setWidths(withfooterder); 
			
			//nguoimua hang 
			Paragraph para = new Paragraph("Người lập phiếu", fontnomar);
			
			para.setAlignment(Element.ALIGN_CENTER);
			cell11.addElement(para);
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setBorderWidthRight(0);
			tablefooter.addCell(para);
			//o giua
			 para = new Paragraph("Thủ kho", fontnomar);
			para.setAlignment(Element.ALIGN_CENTER);
			cell11.addElement(para);
			cell11.setBorderWidthLeft(0);
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.setBorderWidthRight(0);
			tablefooter.addCell(para);
			//Káº¿ toÃ¡n
				para = new Paragraph("Nhân viên giao nhận", fontnomar);
				para.setAlignment(Element.ALIGN_CENTER);
				cell11.addElement(para);
				cell11.setBorderWidthLeft(0);
				cell11.setBorderWidthRight(0);
				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
				tablefooter.addCell(para);
			//nguoi nhan hang
			para = new Paragraph("Khách hàng", fontnomar);
			para.add("\n");
			para.add("\n");
			para.add("\n");
			para.add("\n");
			para.add("\n");
			para.add("\n");
			para.add("\n");
			 
			para.setAlignment(Element.ALIGN_CENTER);
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell11.addElement(para);
			cell11.setBorderWidthLeft(0);
			cell11.setBorder(0);
			
			tablefooter.addCell(para);
			
			document.add(tablefooter);
			
			
			document.close(); 
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
		db.shutDown();
		
	}

}
