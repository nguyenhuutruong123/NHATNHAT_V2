package geso.dms.distributor.servlets.hoadontaichinhKM;

import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinhKM.IHoadontaichinhKM;
import geso.dms.distributor.beans.hoadontaichinhKM.imp.HoadontaichinhKM;
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

public class PhieuXuatKhoKMPdfSvl {
	private static final long serialVersionUID = 1L;

	public PhieuXuatKhoKMPdfSvl()
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
		String id = util.antiSQLInspection(request.getParameter("pdf"));
		System.out.println("id:"+ id);
		
		String nppId = util.antiSQLInspection(request.getParameter("nppId"));

		IHoadontaichinhKM pxkBean = new HoadontaichinhKM(id);
		pxkBean.setUserId(userId);

		dbutils db = new dbutils();
				
		String sqlIN_SANPHAM = "select MA, TEN, DONVI, AVG(DONGIA) as DONGIA, '' as SOLO, '' as NGAYHETHAN, AVG(THUEVAT) as THUEVAT, sum(SOLUONG) as SOLUONG, sum(CHIETKHAU) as CHIETKHAU " +
							   "from HOADON_SP_CHITIET where hoadon_fk = '" + pxkBean.getId() + "'" +
							   "group by MA, TEN, DONVI ";
		
		// Hải Phòng, Nam Định, HCM, Đồng Nai, Bình Thuận, Vĩnh Long, Cần Thơ, Q207, Đã Nẵng , Quảng Ngãi, Gia Lai, Thanh Hoa  ---CHI NHUNG NHA NAY MOI DUNG SOLO TRONG HOA DON
		if( nppId.equals("106171")||nppId.equals("106179") || nppId.equals("106172") || nppId.equals("106210") || nppId.equals("106225") || nppId.equals("106226") 
				|| nppId.equals("106224") || nppId.equals("106227") || nppId.equals("100010") || nppId.equals("106211") || nppId.equals("106231") || nppId.equals("106249")
				|| nppId.equals("106277") ||nppId.equals("106251")||nppId.equals("106250")||nppId.equals("106221")||nppId.equals("106275")||nppId.equals("106188") || nppId.equals("106170") ||nppId.equals("106191"))
		{
			sqlIN_SANPHAM = "select MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU " +
			   				"from HOADON_SP_CHITIET where hoadon_fk = '" + pxkBean.getId() + "' ";
		}
		
		System.out.println("---IN SAN PHAM: " + sqlIN_SANPHAM );

		String sqlIN_CHIETKHAU = 
			" select diengiai, chietkhau, thueVAT, STT, tichluyQUY, inchietkhauKIEUMOI " +
			" from HOADON_CHIETKHAU where hoadon_fk = '" + pxkBean.getId() + "' and HIENTHI = '1' and chietkhau > 0 order by STT, tichluyQUY ";
		System.out.println("---CHIET KHAU: " + sqlIN_CHIETKHAU);
		
		
		//=========xac dinh loai loainpp, 1: chi nhanh, khac 1: npp
		
		String qr_loainpp="select LOAINPP from nhaphanphoi where PK_SEQ="+nppId;
		String loai_npp="";
		ResultSet rs_loainpp=db.get(qr_loainpp);
		if(rs_loainpp!=null){
			try {
				while(rs_loainpp.next()){
					loai_npp=rs_loainpp.getString("LOAINPP");
				}
					
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		
		
		String type = request.getParameter("type");
		System.out.println("11type:"+type);
		if(type == null)
			type = "";
		
		//=============khai bao doc
		//3 COT TONG TIEN BUOC PHAI LAY TRONG BANG HOA DON, DO TRONG QUA KHU CO NHUGN CAI BI SAI

		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition", " inline; filename=HoaDonTaiChinh.pdf");

		Document document = new Document();
		ServletOutputStream outstream = response.getOutputStream();
		
		
		if(!type.equals("PGH"))
		{
			if (querystring.indexOf("pdf") > 0)
			{
				if(loai_npp.equals("1")) //cua chi nhanh
				{
					if(nppId.equals("106174") ) // QUAY HA DONG 
					{
						this.CreatePxk_HADONG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106171")) // CHI NHÁNH CÔNG TY TẠI NGHỆ AN 
					{
						this.CreatePxk_NGHEAN(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106170")) // THANH HOA 
					{
						this.CreatePxk_THANHHOA(document, outstream, pxkBean, sqlIN_SANPHAM);		
					}
					else if(nppId.equals("106191")) // PHUTHO 
					{
						this.CreatePxk_PHUTHO(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106224")) // VINH LONG GIONG THANH HOA 
					{
						System.out.println("vao vinh long ne");
						this.CreatePxk_VINHLONG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106275") ) // TIEN GIANG GIONG VINH LONG
					{
						this.CreatePxk_TIENGIANG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106172")) //  NAM ĐỊNH
					{
						this.CreatePxk_NAMDINH(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106182")) //  HẢI DƯƠNG
					{
						this.CreatePxk_HAIDUONG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106192")) //  THÁI NGUYÊN  
					{
						this.CreatePxk_THAINGUYEN(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106221")) //  TÂY NINH - CÔNG TY DƯỢC PHẨM - DƯỢC LIỆU TÂY NAM  
					{
						this.CreatePxk_TAYNINH(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106188")) //  PHƯƠNG MINH  
						
						this.CreatePxk_PHUONGMINH(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106179")) //  HẢI PHÒNG  : 02/10 mẫu mới giống Hà Nội
					{
						this.CreatePxk_HAIPHONG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106225")) //  ĐỒNG NAI   : Giống Hải phòng
					{
						this.CreatePxk_DONGNAI(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106162")) //  QUẢNG NINH >> 23/10 ĐỔI SANG MẪU GIỐNG HẢI DƯƠNG
					{
						this.CreatePxk_QUANGNINH(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106210")  ) //  CN HỒ CHÍ MINH
					{
						this.CreatePxk_CNHOCHIMINH(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106211")) //  Cửa hàng số 01 - Chi Nhánh Công ty cổ phần TraphacoDMS
					{
						this.CreatePxk_CUAHANGQ10(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106226")) //  BÌNH THUẬN   : Giống Đồng Nai
					{
						this.CreatePxk_BINHTHUAN(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106227")) //  CẦN THƠ   : Giống VĨNH LONG
					{
						this.CreatePxk_CANTHO(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106231")) //  ĐÀ NẴNG   
					{
						this.CreatePxk_DANANG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106249")) //  QUẢNG NGÃI   
					{
						this.CreatePxk_QUANGNGAI(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("106250")) //  KHÁNH HÒA   
					{
						this.CreatePxk_KHANHHOA(document, outstream, pxkBean, sqlIN_SANPHAM);
						
					}
					else if(nppId.equals("106251")) //  GIA LAI  
					{
						this.CreatePxk_GIALAI(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if(nppId.equals("100002"))     // CHI NHÁNH HÀ NỘI 
					{
						this.CreatePxk_hanoi(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else if( nppId.equals("106277") ) // 
					{
						this.CreatePxk_BINHDUONG(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
					else      // CHI NHÁNH HÀ NỘI 
					{
						//xuat hoa don
						this.CreatePxk(document, outstream, pxkBean, sqlIN_SANPHAM);
					}
				}
				
				else //cua nha cung cap
				{
					CreatePxk_npp(document, outstream, pxkBean, sqlIN_SANPHAM);
				}
				document.close();
			}
	}
	
	private void CreatePxk_CUAHANGQ10(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
			try
			{			
				dbutils db = new dbutils();
					
				//LAY THONG TIN NCC
				String kbh="";
				String ddh="";
				String npp_fk="";
				String khId="";
				double Vat= 0;
				
				String ctyTen="";
				String cty_MST ="";
				String cty_Diachi="";
				String cty_Sotaikhoan= "";
				String cty_Dienthoai= "";
				String cty_Fax= "";
				String khoxuat ="";
				String thukho = "";
				String sql ="";
		
			   if(kbh.equals("100052")) // ETC
			   {
				  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
				  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
				        " from NHACUNGCAP ";				  
			   }else{ //OTC
				   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
			        " from NHAPHANPHOI" +
			        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
				   
				   //moi
				   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
			        " from NHAPHANPHOI" +
			        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
			   }
			   
			   
			   System.out.println("Lấy TT CTY "+sql);
			   ResultSet rsINFO = db.get(sql);
			   if(rsINFO.next())
			   {
				   khoxuat = rsINFO.getString("XUATTAIKHO");
				   ctyTen = rsINFO.getString("TEN");
				   cty_MST = rsINFO.getString("MASOTHUE");
				   cty_Diachi = rsINFO.getString("DIACHI");
				   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
				   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
				   cty_Fax = rsINFO.getString("FAX");
				   thukho = rsINFO.getString("thukho");
				   rsINFO.close();
				   
			   }
				   
			 //LAY THONG TIN KHACHHANG 
				   
				String Donvi="";
				String kh_MST ="";
				String kh_Diachi="";
				String hinhthucTT= "";
				String ngayxuatHD= "";
				String chucuahieu = "";
				
/*				sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
			        " from KHACHHANG " +
			        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
			   
				//LẤY THEO DỮ LIỆU MỚI
				/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
				  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
				
				//moi
				sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
				"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
				"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
   
				String kh_sotaikhoan="";
				   System.out.println("Lấy TT KH "+sql);
				   ResultSet rsLayKH= db.get(sql);
				   if(rsLayKH.next())
				   {
					   Donvi = rsLayKH.getString("TEN");
					   kh_MST = rsLayKH.getString("MASOTHUE");
					   kh_Diachi = rsLayKH.getString("DIACHI");
					   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
					   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
					   rsLayKH.close();
					   
				   }   
		   
		   
			  // LẤY KHO XUẤT
		   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
		   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
		   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
		   		" 			   ( SELECT case when isnull(innguoimua, 1) = 1 and nguoimua is null  then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
		   		"							 when isnull(innguoimua, 1) = 1 and nguoimua is not null  then isnull(nguoimua,'') " +
				"                            else '' end " +
				"                FROM HOADON " +
				"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
		        " from DONHANG " +
		        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
		   
	       System.out.println("Kho xuất "+sql);
		   ResultSet rsKho= db.get(sql);
		   if(rsKho.next())
		   {
			   hinhthucTT = rsKho.getString("HTTT");		   
			   ngayxuatHD = rsKho.getString("ngayxuathd");
			   chucuahieu = rsKho.getString("nguoimua");
			   
			   rsKho.close();
		   }
		   
		   
		   //=========================khu vuc in=================================
		   
			   
		    
					
		//NHA HCM TEN HOI DAC BIET CHUT
		//String spTEN = rsSP.getString("TEN").replaceAll("bao đường", "bđ");
					
		   //in phieu xuat
		   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
				   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
				   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
		   
		   
		   
			} catch (Exception e)
			{
				System.out.println("115.Exception: " + e.getMessage());
				e.printStackTrace();
			}
		
		}

	
	private String getDate()
	{			
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        dateFormat.format(date);
	        
/*		String arr[] = date.split("-");
		String nam = arr[0];
		String thang = arr[1];
		String ngay = arr[2];*/
		
		return dateFormat.format(date);
	}

	private void CreatePxk_HAIPHONG(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) throws IOException
	{
		System.out.println("----HAI PHONG....ABC");
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   
			   
			   //moi
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
		   
		   System.out.println("Lấy TT CTY HP: " + sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
	/*		sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			   ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"                FROM HOADON" +
			"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   //==============================khu vuc in=====================
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
	   
			
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	
	private void CreatePxk_DONGNAI(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) throws IOException
	{
		System.out.println("----DONG NAI.......");
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   
			 //moi
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
			   
			   
		   
		   System.out.println("Lấy TT CTY HP: " + sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			   ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"                FROM HOADON" +
			"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		 
	  //===================khu vuc in
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}



	private void CreatePxk_CNHOCHIMINH(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
			try
			{			
				dbutils db = new dbutils();
					
				//LAY THONG TIN NCC
				String kbh="";
				String ddh="";
				String npp_fk="";
				String khId="";
				double Vat= 0;
				
				String ctyTen="";
				String cty_MST ="";
				String cty_Diachi="";
				String cty_Sotaikhoan= "";
				String cty_Dienthoai= "";
				String cty_Fax= "";
				String khoxuat ="";
				String thukho = "";
				
				String sql ="";
		
				
				   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ," +
				   	"(case when (select isnull(KHO_FK,'0') from KHACHHANG where pk_seq in (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"')) = 100002 then N'Kho sỉ Nhà Bè'" +
				   	" else  isnull(XUATTAIKHO,' ') end ) XUATTAIKHO, isnull(thukho, '') thukho " +
			        " from NHAPHANPHOI" +
			        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
				   
				   
			   
			   System.out.println("Lấy TT CTY "+sql);
			   ResultSet rsINFO = db.get(sql);
			   if(rsINFO.next())
			   {
				   khoxuat = rsINFO.getString("XUATTAIKHO");
				   ctyTen = rsINFO.getString("TEN");
				   cty_MST = rsINFO.getString("MASOTHUE");
				   cty_Diachi = rsINFO.getString("DIACHI");
				   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
				   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
				   cty_Fax = rsINFO.getString("FAX");
				   thukho = rsINFO.getString("thukho");
				   rsINFO.close();
				   
			   }
				   
			 //LAY THONG TIN KHACHHANG 
				   
				String Donvi="";
				String kh_MST ="";
				String kh_Diachi="";
				String hinhthucTT= "";
				String ngayxuatHD= "";
				String chucuahieu = "";
				
				/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
			        " from KHACHHANG " +
			        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/	
				
				//LẤY THEO DỮ LIỆU MỚI
				/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
				  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
				//moi
				sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
				"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
				"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

			String kh_sotaikhoan="";
			   System.out.println("Lấy TT KH "+sql);
			   ResultSet rsLayKH= db.get(sql);
			   if(rsLayKH.next())
			   {
				   Donvi = rsLayKH.getString("TEN");
				   kh_MST = rsLayKH.getString("MASOTHUE");
				   kh_Diachi = rsLayKH.getString("DIACHI");
				   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
				   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
				   rsLayKH.close();
				   
			   }    
				
			  // LẤY KHO XUẤT
		   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
		   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
		   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
		   		" 			   ( SELECT case when isnull(innguoimua, 1) = 1 and nguoimua is null  then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
		   		"							 when isnull(innguoimua, 1) = 1 and nguoimua is not null  then isnull(nguoimua,'') " +
				"                            else '' end " +
				"                FROM HOADON " +
				"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
		        " from DONHANG " +
		        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
		   
	       System.out.println("Kho xuất "+sql);
		   ResultSet rsKho= db.get(sql);
		   if(rsKho.next())
		   {
			   hinhthucTT = rsKho.getString("HTTT");		   
			   ngayxuatHD = rsKho.getString("ngayxuathd");
			   chucuahieu = rsKho.getString("nguoimua");
			   
			   rsKho.close();
		   }
			   
		   //==============khu vuc in
		   
		 //in phieu xuat
		   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
				   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
				   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
		   
		   
	
				
			} catch (Exception e)
			{
				System.out.println("115.Exception: " + e.getMessage());
				e.printStackTrace();
			}
		
		}

	
	
	private void CreatePxk_BINHDUONG(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
			try
			{			
				dbutils db = new dbutils();
					
				//LAY THONG TIN NCC
				String kbh="";
				String ddh="";
				String npp_fk="";
				String khId="";
				double Vat= 0;
				
				String ctyTen="";
				String cty_MST ="";
				String cty_Diachi="";
				String cty_Sotaikhoan= "";
				String cty_Dienthoai= "";
				String cty_Fax= "";
				String khoxuat ="";
				String thukho = "";
				
				String sql ="";
		
				
				   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ," +
				   	"(case when (select isnull(KHO_FK,'0') from KHACHHANG where pk_seq in (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"')) = 100002 then N'Kho sỉ Nhà Bè'" +
				   	" else  isnull(XUATTAIKHO,' ') end ) XUATTAIKHO, isnull(thukho, '') thukho " +
			        " from NHAPHANPHOI" +
			        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
			   
			   System.out.println("Lấy TT CTY "+sql);
			   ResultSet rsINFO = db.get(sql);
			   if(rsINFO.next())
			   {
				   khoxuat = rsINFO.getString("XUATTAIKHO");
				   ctyTen = rsINFO.getString("TEN");
				   cty_MST = rsINFO.getString("MASOTHUE");
				   cty_Diachi = rsINFO.getString("DIACHI");
				   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
				   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
				   cty_Fax = rsINFO.getString("FAX");
				   thukho = rsINFO.getString("thukho");
				   rsINFO.close();
				   
			   }
				   
			 //LAY THONG TIN KHACHHANG 
				   
				String Donvi="";
				String kh_MST ="";
				String kh_Diachi="";
				String hinhthucTT= "";
				String ngayxuatHD= "";
				String chucuahieu = "";
				
				/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
			        " from KHACHHANG " +
			        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/	
				
				//LẤY THEO DỮ LIỆU MỚI
				/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
				  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
				//moi
				sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
				"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
				"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

			String kh_sotaikhoan="";
			   System.out.println("Lấy TT KH "+sql);
			   ResultSet rsLayKH= db.get(sql);
			   if(rsLayKH.next())
			   {
				   Donvi = rsLayKH.getString("TEN");
				   kh_MST = rsLayKH.getString("MASOTHUE");
				   kh_Diachi = rsLayKH.getString("DIACHI");
				   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
				   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
				   rsLayKH.close();
				   
			   }   
			  // LẤY KHO XUẤT
		   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
		   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
		   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
		   		" 			   ( SELECT case when isnull(innguoimua, 1) = 1 and nguoimua is null  then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
		   		"							 when isnull(innguoimua, 1) = 1 and nguoimua is not null  then isnull(nguoimua,'') " +
				"                            else '' end " +
				"                FROM HOADON " +
				"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
		        " from DONHANG " +
		        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
		   
	       System.out.println("Kho xuất "+sql);
		   ResultSet rsKho= db.get(sql);
		   if(rsKho.next())
		   {
			   hinhthucTT = rsKho.getString("HTTT");		   
			   ngayxuatHD = rsKho.getString("ngayxuathd");
			   chucuahieu = rsKho.getString("nguoimua");
			   
			   rsKho.close();
		   }
			//=========================khu vuc in=========================================   
		 //in phieu xuat
		   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
				   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
				   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
		   
		   
				
			} catch (Exception e)
			{
				System.out.println("115.Exception: " + e.getMessage());
				e.printStackTrace();
			}
		
		}

	
	
	private void CreatePxk_QUANGNINH_OLD(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM, String sqlIN_CHIETKHAU, int loai) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String sql ="";
	
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  
		  
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
	   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"				(select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			   ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"                FROM HOADON" +
			"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   //==============khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	
		
		
	}

	
	private void CreatePxk_HAIPHONG_OLD(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM, String sqlIN_CHIETKHAU, int loai) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String sql ="";
	
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  
		  
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE"+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"       		(select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 				( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"            	 FROM HOADON" +
			"            	 WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}


	private void CreatePxk_THAINGUYEN(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{
			dbutils db = new dbutils();
			
			//LAY THONG TIN NHA CUNG CAP --moi
			
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

			   
		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			
			 //LAY THONG TIN KHACHHANG 		  
			//String sql ="";
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			String sotaikhoan= "";
			//String thukho = "";
			
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE, (SELECT isnull(thukho,'') FROM NHAPHANPHOI where PK_SEQ = npp_fk) thukho " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";
			
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");	
			   thukho = rsLayKH.getString("thukho");	
			   rsLayKH.close();
			   
		   } */
		   
		   
		   
		 //moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
		   
		   // LẤY KHO XUẤT
		   sql = " select top 1 NPP_FK,(select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
		   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
		   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
		   		" 				( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
				"                         else isnull(nguoimua,'') end " +
				"           	  FROM HOADON" +
				"           	  WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
		        " from DONHANG " +
		        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
		   
	       System.out.println("Kho xuất "+sql);
	      // String npp_fk="";
		   ResultSet rsKho= db.get(sql);
		   if(rsKho.next())
		   {
			   hinhthucTT = rsKho.getString("HTTT");		   
			   ngayxuatHD = rsKho.getString("ngayxuathd");
			   chucuahieu = rsKho.getString("nguoimua");
			   npp_fk=rsKho.getString("NPP_FK");
			   rsKho.close();
		   }
		   
		 //KHAC CHI NHANH KHAC XIU
			
			//query lay san pham
			String query = "select MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU,  " +
								"case when MA ='3KOOL' then 'JAPAN'  when MA = '5TRIB' then 'AUSTRALIA'  when ma in (select b.MA from ufn_sanpham("+npp_fk+") a inner join SANPHAM b on a.sanpham_fk=b.PK_SEQ where isnhapkhau=1) then 'SANDOZ' else 'TraphacoDMS' end as  NoiSX   " +
						   "from HOADON_SP_CHITIET where hoadon_fk = '" + pxkBean.getId() + "'";
			System.out.println("[ERP_DONDATHANG_SANPHAM1]"+query);
		   
		   
		   //============in
			//in phieu xuat
			   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
					   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
					   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
			   
			   
			   
			  
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	private void CreatePxk_PHUONGMINH(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

			   
		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
	/*		sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"  				(select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
		//============khu vuc in
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	
	}
	
	private void CreatePxk_HAIDUONG(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			
			String sql ="";
	
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			
			 //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   
		   System.out.println("Lấy TT CTY HP "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
		/*	sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
	   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			   ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"                FROM HOADON" +
			"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   //=================khu vuc in
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}

	private void CreatePxk_NAMDINH(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
	/*		sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"  				(select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	
	}

	private void CreatePxk_HADONG(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{		
			//System.out.println("user la "+userTen);
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"        '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY 1"+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
			/*sql =   " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE   "+
		        	" from KHACHHANG " +
		        	" where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/		
			
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
	   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"  			(select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"             FROM HOADON" +
			"             WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
			
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
			
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
		
	}

	private void CreatePxk_NGHEAN_BK(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM, String sqlIN_CHIETKHAU,int loai)
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String sql ="";
	
		   /*if(kbh.equals("100052")) // ETC
		   {*/
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  
		   /*}else{ //OTC
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
		   }*/
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
	   
	   //moi
		sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
		"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
		"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

	String kh_sotaikhoan="";
	   System.out.println("Lấy TT KH "+sql);
	   ResultSet rsLayKH= db.get(sql);
	   if(rsLayKH.next())
	   {
		   Donvi = rsLayKH.getString("TEN");
		   kh_MST = rsLayKH.getString("MASOTHUE");
		   kh_Diachi = rsLayKH.getString("DIACHI");
		   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
		   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
		   rsLayKH.close();
		   
	   }   
		
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"              (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
	   		   
		
	   //============khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   	
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}


	private void CreatePxk_NGHEAN(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM)
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			
			String sql ="";
	
		   /*if(kbh.equals("100052")) // ETC
		   {*/
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  
		   /*}else{ //OTC
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
		   }*/
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
	   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"              (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
	   		   
		//==========khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}

	
	private void CreatePxk_PHUTHO(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		System.out.println("Phu tho");
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE  "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
	  //============khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void CreatePxk_THANHHOA(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE  "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
	   
	   //===============khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void CreatePxk_TIENGIANG(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String sql ="";
	
			String thukho = "";
			
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE  "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		  //====================khu vuc in======================= 
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	  
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void CreatePxk_VINHLONG(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		System.out.println("vao vinh long");
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE  "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   //=================khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private void CreatePxk_VINHLONG_BK(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM, String sqlIN_CHIETKHAU) 
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE  "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
			"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
			"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";

		String kh_sotaikhoan="";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
 
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   ///============khu vuc in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	private String CapnhatTT(String id, String userId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "";
			String trangthai="";
			// Kiem tra trạng thái hiện tại của Hóa đơn
			
			/*query = " select trangthai from HOADON where pk_seq = "+ id +" ";
			ResultSet rs = db.get(query);
			if(rs!= null)
			{
				while(rs.next())
				{
					trangthai = rs.getString("trangthai");
				}rs.close();
			}
			
			if(!trangthai.equals("3") && !trangthai.equals("5") )
			{*/
				// Cập nhật trạng thái HOADON Đã in đối với HD trạng thái đã xác nhận
				query = "update HOADON set trangthai = '4' where pk_seq = '" + id + "' and TrangThai=2 ";
				if(db.updateReturnInt(query)!=1)
				{
					msg = "Hóa đơn đã cập nhật trạng thái hoặc phát sinh lỗi" + query;
					db.getConnection().rollback();
					return msg;
				}
				
				// Cập nhật trạng thái DONHANG Đã in
				query = "update DONHANG set DAXUATHOADON = '1' where Trangthai=1 and pk_seq in ( select DDH_FK from HOADON_DDH where HOADON_FK = '" + id + "') ";
				if(db.updateReturnInt(query)!=1)
				{
					msg = "Hóa đơn đã cập nhật trạng thái hoặc phát sinh lỗi" + query;
					db.getConnection().rollback();
					return msg;
				}
			//}
			
			//LUU LAI LOG IN  --> TAM THOI BO DI DE THEO DOI TINH TRANG TREO
			
			/*query = "insert HOADON_LOG_IN( hoadon_fk, nguoiin ) values ( '" + id + "', '" + userId + "' )";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật HOADON " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "insert HOADON_LOG_IN_CHITIET(log_fk, hoadonId, sohoadon, kyhieu, ngayxuatHD, donhang_fk, ma, ten, solo, ngayhethan, donvi, soluong, dongia, thanhtien,  " +
					"		tienthue, thueVAT, thanhtienSAUVAT, STT, tichluyQUY ) " +
					"select IDENT_CURRENT('HOADON_LOG_IN'), hd.pk_seq as hoadonId, hd.sohoadon, hd.kyhieu, hd.ngayxuatHD, DAIN.*  " +
					"from HOADON hd  " +
					"inner join " +
					"( " +
					"	select hoadon_fk, MA, TEN, SOLO, NGAYHETHAN, DONVI, SOLUONG, cast(DONGIA as numeric(18, 2)) as DONGIA,  " +
					"			cast ( ( SOLUONG * DONGIA ) as numeric(18, 0) ) as thanhtien,  " +
					"			THUEVAT as tienTHUE,  cast( ( SOLUONG * DONGIA * thuevat / 100 ) as numeric(18, 0) ) as thueVAT, " +
					"			cast ( ( SOLUONG * DONGIA * ( 1 + thuevat / 100 ) ) as numeric(18, 0) ) as thanhtienSAUVAT, -1 as STT, 0 as 	tichluyQUY " +
					"	from HOADON_SP_CHITIET where hoadon_fk = '" + id + "'  " +
					"union ALL " +
					"	select hoadon_fk, diengiai as maCK, case diengiai when 'CN5' then N'Giảm trừ (Chiết khấu bán hàng ngay)' " +
					"						 when 'CN10' then N'Giảm trừ (Chiết khấu bán hàng ngay)' " +
					"						 when 'CT5' then N'Giảm trừ (CK Tháng)' " +
					"						 when 'CT10' then N'Giảm trừ (CK Tháng)' " +
					"						 when 'CQB5' then N'Giảm trừ (CK Quý nhóm hàng BG-HH)' " +
					"						 when 'CQX5' then N'Giảm trừ (CK Quý nhóm hàng XANH)' " +
					"						 when 'CQB10' then N'Giảm trừ (CK Quý nhóm hàng BG-HH)' " +
					"						 when 'CQX10' then N'Giảm trừ (CK Quý nhóm hàng XANH)' end as diengiai, ' ' as solo, ' ' as ngayhethan, '1' as donvi, 0 as soluong, 0 as dongia,  " +
					"						 cast(chietkhau as numeric(18, 0)) as chietkhau, thueVAT, " +
					"						 cast( ( chietkhau * thueVAT / 100 ) as numeric(18, 0) )  as tienVAT,  " +
					"						 cast( (chietkhau * ( 1 + thueVAT / 100 ) ) as numeric(18, 0) ) as thanhtien, STT, tichluyQUY " +
					"	from HOADON_CHIETKHAU  " +
					"	where hoadon_fk = '" + id + "' " +
					"union ALL " +
					"	select hoadon_fk, ' ' as MA, ' ' as TEN, ' ' as SOLO, ' ' as NGAYHETHAN, ' ' as DONVI, 0 as soluong, 0 as dongia,  " +
					"			cast( sum(thanhtien) as numeric(18, 0) ) as thanhtien, 0 as VAT,  " +
					"			cast( sum(thueVAT) as numeric(18, 0) ) as thueVAT,  " +
					"			cast( sum(thanhtienSAUVAT) as numeric(18, 0) ) as thanhtienSAUVAT, 10 as STT, 0 as tichluyQUY " +
					"	from " +
					"	( " +
					"			select hoadon_fk,  " +
					"					SOLUONG * DONGIA as thanhtien,  " +
					"					SOLUONG * DONGIA * thuevat / 100  as thueVAT, " +
					"					SOLUONG * DONGIA * ( 1 + thuevat / 100 ) as thanhtienSAUVAT " +
					"			from HOADON_SP_CHITIET where hoadon_fk = '" + id + "'  " +
					"			 " +
					"		union ALL " +
					"			select hoadon_fk, " +
					"					-1 * SUM(  chietkhau  ) as thanhtien,  " +
					"					-1 * SUM(  chietkhau * thueVAT / 100  ) as thueVAT,  " +
					"					-1 * SUM(  chietkhau * ( 1 + thueVAT / 100 ) ) as thanhtienSAUVAT " +
					"			from HOADON_CHIETKHAU  " +
					"			where hoadon_fk = '" + id + "' and hienthi = '1' " +
					"			group by hoadon_fk " +
					"	) " +
					"	TOTAL group by hoadon_fk " +
					") " +
					"DAIN on hd.pk_seq = DAIN.hoadon_fk " +
					"where hd.pk_seq = '" + id + "' " +
					"order by hd.ngayxuatHD, hd.pk_seq, DAIN.STT, DAIN.tichluyQUY " ;
			if(db.updateReturnInt(query) <= 0 )
			{
				msg = "Không thể cập nhật HOADON " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
		
	
	private void form_phieuxuat(Document document,ServletOutputStream outstream,String sqlIN_SANPHAM,IHoadontaichinhKM pxkBean,String mahoadon,String ctyTen,String cty_MST,String cty_Diachi,
			String cty_Sotaikhoan,String cty_Dienthoai,String cty_Fax,String Donvi,String kh_MST,
			String hinhthucTT,String ngayxuatHD,String chucuahieu,String kh_sotaikhoan, String kh_Diachi, dbutils db, String khoxuat1){
		
		
		String Sodms=pxkBean.getId();
		String tk_co="tk co";
		String tk_no="tk no";
		String diengia=pxkBean.getGhichu();
		String khoxuat=khoxuat1;
		String chietkhau="chiet khau";
		String giamgia="giam gia";
		
		try{
		NumberFormat formatter = new DecimalFormat("#,###,###.##");
		NumberFormat formatter1 = new DecimalFormat("#,###,###");
	
		
		document.setPageSize(PageSize.A4);
		//document = new Document(PageSize.A4,2.0f*CONVERT,1.5f*CONVERT,1.7f*CONVERT, 0.0f*CONVERT);
		document.setMargins(0.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT, 0.5f*CONVERT); // L,R,T,B
		PdfWriter writer = PdfWriter.getInstance(document, outstream);
		
		PdfPCell cell ;
		Paragraph para;
		Chunk chunk;
		
		document.open() ;
	

		BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		Font font = new Font(bf, 13, Font.BOLD);
		Font font2 = new Font(bf, 8, Font.BOLD);
		Font font3 = new Font(bf, 10, Font.BOLD);
		
		
		PdfPTable tableheader =new PdfPTable(1);
		tableheader.setWidthPercentage(100);			

		cell = new PdfPCell();
		cell.setBorder(0);
		cell.setPaddingTop(1.5f * CONVERT);
		cell.setPaddingLeft(2.6f * CONVERT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		
		//NGAY THANG NAM
		String [] ngayHD = ngayxuatHD.split("-");
		
		/*Paragraph pxk = new Paragraph(ngayHD[2] + "                        " + ngayHD[1] +
				"                             " + ngayHD[0]
				+ "                                " + pxkBean.getId() , new Font(bf, 10, Font.BOLDITALIC));*/
		Paragraph pxk = new Paragraph("",new Font(bf, 8, Font.BOLDITALIC));
		chunk =new Chunk(ngayHD[2] + "                        " + ngayHD[1] +"                             " + ngayHD[0],new Font(bf, 8, Font.BOLDITALIC));
		pxk.add(chunk);
		//so hoa don
		/*chunk =new Chunk("                                " + pxkBean.getId() ,new Font(bf, 12, Font.BOLDITALIC));
		chunk.setTextRise(2.0f);
		pxk.add(chunk);*/		
		pxk.setAlignment(Element.ALIGN_CENTER);
		pxk.setSpacingAfter(2);
		cell.addElement(pxk);

		tableheader.addCell(cell);
		document.add(tableheader);
		

		
		//----------------------ADD INFO KHACH HANG : bang hai cot -----------------------------
		PdfPTable tbl_khachhang =new PdfPTable(2);
		float kh_withd []={13.0f*CONVERT,4.0f*CONVERT};
		tbl_khachhang.setWidths(kh_withd);
		tbl_khachhang.setWidthPercentage(100);
		
		//--dong0 ten nguoi mua hang (CHU CUA HIEU) + so dms
			//ten nguoi mua
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph(chucuahieu , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
		
			//so dms
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(Sodms , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
		
		//--dong1 ten don vi + tk no
			
			//ten dv mua hang
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph( Donvi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
		
		
			//tk no
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(tk_no, new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
		
		//--dong2 dia chi + tk co 
			//dia chi
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( kh_Diachi , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
		
		
			//tk co
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(0.5f * CONVERT);
			para = new Paragraph(tk_co , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			
		//--dong3 so hoa don + ngay xuat hoa don 	
			//
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setVerticalAlignment(Element.ALIGN_LEFT); //add bang 2 cot
				PdfPTable tbl_shd_ngay =new PdfPTable(2);
				float withd_shd []={6.0f*CONVERT,10.0f*CONVERT};
				tbl_shd_ngay.setWidths(withd_shd);
				tbl_shd_ngay.setWidthPercentage(100);
					PdfPCell cell_shd = new PdfPCell(); //ad so hoa don
					cell_shd.setBorder(0);
					cell_shd.setPaddingLeft(3.0f * CONVERT);
					cell_shd.setVerticalAlignment(Element.ALIGN_LEFT);
					para = new Paragraph(pxkBean.getId(), new Font(bf, 10, Font.BOLD));
					cell_shd.addElement(para);
					tbl_shd_ngay.addCell(cell_shd);
			
					PdfPCell cell_ngay = new PdfPCell(); //ad so hoa don
					cell_ngay.setBorder(0);
					cell_ngay.setPaddingLeft(1.5f * CONVERT);
					cell_ngay.setVerticalAlignment(Element.ALIGN_LEFT);
					para = new Paragraph(ngayHD[2]+"/"+ngayHD[1]+"/"+ngayHD[0], new Font(bf, 10, Font.BOLD));
					cell_ngay.addElement(para);
					tbl_shd_ngay.addCell(cell_ngay);
			cell.addElement(tbl_shd_ngay);
			tbl_khachhang.addCell(cell);
			
			//rong
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			tbl_khachhang.addCell(cell);
			
		//dong 4 dien giai
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.0f * CONVERT);
			para = new Paragraph( diengia , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			/*//rong
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			tbl_khachhang.addCell(cell);*/
			
			
		//dong 5 kho xuat
			
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(2.5f * CONVERT);
			para = new Paragraph( khoxuat , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_khachhang.addCell(cell);
			
			//rong
			cell=new PdfPCell(new Paragraph("",font2));
			cell.setBorder(0);
			tbl_khachhang.addCell(cell);
			
			document.add(tbl_khachhang);
		//===========================================================	
			

		// Table Content---------------------------------bang du lieu----------------------------------
		
		//---------------------------------------------------------------------------
		
		float[] tbl_withd = { 7.0f, 60f, 15f , 15.0f, 20.0f, 25f, 15f, 7.0f, 15.0f };
		
		PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
		tbl_sanpham.setSpacingBefore(30.0f );
		tbl_sanpham.setWidthPercentage(100);
		tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
		tbl_sanpham.setWidths(tbl_withd);
		PdfPCell cells;
		String query = sqlIN_SANPHAM;
		ResultSet rsSP = db.get(query);
		double tongtien = 0;
		String vat="";
		String tienvat= String.valueOf(pxkBean.getTienVAT());
		String tongtien_vat =String.valueOf( pxkBean.getTongtiensauVAT());
		int stt = 1;
		try {
			while(rsSP.next())
			{
				double soLUONG = rsSP.getDouble("soluong");
				double dongia = rsSP.getDouble("dongia");
				double thanhtien = soLUONG*dongia;
				tongtien +=thanhtien;
				//============tinh quy doi ra thung cho san pham
				String qr_quydoi=" select sp.pk_seq, qc.soluong1  , qc.soluong2  "+    
								 "	from SANPHAM sp inner join QUYCACH qc  "+    
								 " 	on sp.DVDL_FK = qc.DVDL1_FK and qc.DVDL2_FK = 100018 and qc.SANPHAM_FK = sp.PK_SEQ  "+    
								 "	where MA='"+  rsSP.getString("MA") +"'"; 
				String  quydoi="";
				System.out.print("\n -----------qr so thung quy doi:"+qr_quydoi);
				ResultSet rs_quydoi=db.get(qr_quydoi);
				
				if(rs_quydoi!=null){
					System.out.print("\n -----------rs_quy doi khong null nhe:");
					try {
						while(rs_quydoi.next())
						{
							System.out.print("\n -----------so luong tong :"+soLUONG);
							
							int sl1= rs_quydoi.getInt("soluong1") ; //dvdl cua sp
							System.out.print("\n -----------qr so thung quy doi sl 1:"+sl1);
							
							int sl2= rs_quydoi.getInt("soluong2") ; // dvdl cua thung
							System.out.print("\n -----------qr so thung quy doi sl2:"+sl2);
							if(sl1!=0)
							{
								quydoi= String.valueOf(formatter1.format((soLUONG*sl2)/sl1)) +"T";
								if((soLUONG*sl2)%sl1 !=0){
									quydoi += " " + String.valueOf(formatter1.format((soLUONG*sl2)%sl1));
								}
								System.out.print("\n ----------- so thung quy doi:"+quydoi);
							}
							
						}
						rs_quydoi.close();	
					} catch (Exception e) {
						// TODO: handle exception
					
					}
				}
				
				//==============================================	
				//chiet khau tung san pham
				double chietkhau_sp = rsSP.getDouble("chietkhau");
				System.out.print("\n ----------- chiet khau sp:"+chietkhau_sp);
				
				//==================================================
				
				
				
				String[] arr = new String[] { Integer.toString(stt), rsSP.getString("TEN"), rsSP.getString("DONVI"),
						DinhDangTraphacoDMS(formatter.format(dongia)),DinhDangTraphacoDMS(formatter1.format(soLUONG)),
						DinhDangTraphacoDMS(formatter1.format(thanhtien)),rsSP.getString("solo"),DinhDangTraphacoDMS(formatter1.format(chietkhau_sp)),quydoi };
				
				
				for (int j = 0; j < tbl_withd.length; j++)
				{
					cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.BOLD)));
					//canh format
					if(j==1 ||j==5){
						cells.setHorizontalAlignment(Element.ALIGN_LEFT);
					}
					else
					{
						cells.setHorizontalAlignment(Element.ALIGN_CENTER);
					}
					
					cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
					cells.setBorder(0);
					
					tbl_sanpham.addCell(cells);	
				}
				stt++;
			}
			rsSP.close();
			
			
			document.add(tbl_sanpham);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		//lay top(1) vat %
		String qr_vat="select top(1) thuevat as vat from HOADON_SP_CHITIET where hoadon_fk='"+pxkBean.getId()+"'";
		ResultSet rs_vat=db.get(qr_vat);
		if(rs_vat!=null){
			try {
				while (rs_vat.next())
				{
					vat=rs_vat.getString("vat") ;
				}
				rs_vat.close();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		//======================cong tien hang 
		PdfPTable tbl_tien = new PdfPTable(1);
		tbl_tien.setSpacingBefore(0.0f );
		tbl_tien.setWidthPercentage(100);
		tbl_tien.setHorizontalAlignment(Element.ALIGN_LEFT);
		
		//tong tien
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(13.5f * CONVERT);
			para = new Paragraph( DinhDangTraphacoDMS(formatter1.format(tongtien)) , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_tien.addCell(cell);
		
		//chiet khau
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(13.5f * CONVERT);
			para = new Paragraph( chietkhau , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_tien.addCell(cell);
			
		//giam gia
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(13.5f * CONVERT);
			para = new Paragraph( giamgia , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_tien.addCell(cell);
			
			
		//thue vat
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(9.0f * CONVERT);
			para = new Paragraph(vat+"%                                          " +tienvat.replaceAll(",", ".") , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_tien.addCell(cell);
			
		//tong tien
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingLeft(13.5f * CONVERT);
			para = new Paragraph( tongtien_vat.replaceAll(",", ".") , new Font(bf, 10, Font.BOLD));
			cell.addElement(para);
			tbl_tien.addCell(cell);
		
			document.add(tbl_tien);
		//========================================
		
		
		
		
	
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
	}
	

	private void CreatePxk(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) throws IOException
	{
		
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   //moi
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			
		//moi
		sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
				"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
				"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
	   String kh_sotaikhoan = "";
	   System.out.println("Lấy TT KH "+sql);
	   ResultSet rsLayKH= db.get(sql);
	   if(rsLayKH.next())
	   {
		   Donvi = rsLayKH.getString("TEN");
		   kh_MST = rsLayKH.getString("MASOTHUE");
		   kh_Diachi = rsLayKH.getString("DIACHI");
		   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
		   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
		   rsLayKH.close();
		   
	   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"             FROM HOADON" +
			"             WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
	   
	   //====================================KHU VUC IN =====================================//
	   
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}


	private void CreatePxk_npp(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) throws IOException
	{
		
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   //moi
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			
		//moi
		sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
				"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
				"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
	   String kh_sotaikhoan = "";
	   System.out.println("Lấy TT KH "+sql);
	   ResultSet rsLayKH= db.get(sql);
	   if(rsLayKH.next())
	   {
		   Donvi = rsLayKH.getString("TEN");
		   kh_MST = rsLayKH.getString("MASOTHUE");
		   kh_Diachi = rsLayKH.getString("DIACHI");
		   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
		   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
		   rsLayKH.close();
		   
	   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"             FROM HOADON" +
			"             WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
	   
	   //====================================KHU VUC IN =====================================//
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	private void CreatePxk_hanoi(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) throws IOException
	{
		System.out.println("vao ha noi");
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";
		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
			String kh_sotaikhoan = "";
			
			
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
	   
	   System.out.println("Lấy TT KH "+sql);
	   ResultSet rsLayKH= db.get(sql);
	   if(rsLayKH.next())
	   {
		   Donvi = rsLayKH.getString("TEN");
		   kh_MST = rsLayKH.getString("MASOTHUE");
		   kh_Diachi = rsLayKH.getString("DIACHI");
		   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
		   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
		   rsLayKH.close();
		   
	   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"             FROM HOADON" +
			"             WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";			
	   
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
	   
	   //---------------------------- khu vuc mau in-------------------------------------
	   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	
	private void CreatePxk_BINHTHUAN(Document document, ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) throws IOException
	{	
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
			   /*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			 //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

			
			
		   
		   System.out.println("Lấy TT CTY HP: " + sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
	/*		
			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
	   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   } 
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			   ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"                FROM HOADON" +
			"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
	    //==================in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
		
		
	}
	
	private void CreatePxk_QUANGNINH(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
	
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
		//	String kbh="";
		//	String ddh="";
		//	String npp_fk="";
		//	String khId="";
		//	double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";				
				
			String sql ="";
	
		   /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
		    
		    
		  //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

		    
		   
		   System.out.println("Lấy TT CTY HP "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";			*/	  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
	   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
	   		" 			   ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"                FROM HOADON" +
			"                WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   //==============in
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
			
		} catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	private void CreatePxk_CANTHO(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{			
			dbutils db = new dbutils();

			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;

			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";

			String sql ="";

			String thukho = "";

			if(kbh.equals("100052")) // ETC
			{
				sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
						"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
						" from NHACUNGCAP ";				  
			}else{ //OTC
				/*sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
						" from NHAPHANPHOI" +
						" where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
				
				   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
			        " from NHAPHANPHOI" +
			        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";


			}
			System.out.println("Lấy TT CTY "+sql);
			ResultSet rsINFO = db.get(sql);
			if(rsINFO.next())
			{
				khoxuat = rsINFO.getString("XUATTAIKHO");
				ctyTen = rsINFO.getString("TEN");
				cty_MST = rsINFO.getString("MASOTHUE");
				cty_Diachi = rsINFO.getString("DIACHI");
				cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
				cty_Dienthoai = rsINFO.getString("DIENTHOAI");
				cty_Fax = rsINFO.getString("FAX");
				thukho = rsINFO.getString("thukho");
				rsINFO.close();

			}

			//LAY THONG TIN KHACHHANG 
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu= "";
			/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE  "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  


			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
					" FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/

			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   


			// LẤY KHO XUẤT
			sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
					"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd,   "+
					" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
					"                         else isnull(nguoimua,'') end " +
					"               FROM HOADON" +
					"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
					" from DONHANG " +
					" where PK_SEQ = (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  


			ResultSet rsKho= db.get(sql);
			if(rsKho.next())
			{
				hinhthucTT = rsKho.getString("HTTT");		   
				ngayxuatHD = rsKho.getString("ngayxuathd");
				chucuahieu = rsKho.getString("nguoimua");
				rsKho.close();
			}
			//in phieu xuat
			   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
					   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
					   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
			   
			   

		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	private String getSTT(int stt)
	{
		if (stt < 10)
			return "000" + Integer.toString(stt);
		if (stt < 100)
			return "00" + Integer.toString(stt);
		if (stt < 1000)
			return "0" + Integer.toString(stt);
		return Integer.toString(stt);
	}
	
		private void CreatePxk_DANANG(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM)
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  

		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
/*			sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/				  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   } 
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"              (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
			
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	private void CreatePxk_GIALAI(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM)
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			String sql ="";
	
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  

		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   } 
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"              (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
		
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	private void CreatePxk_KHANHHOA(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM)
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			
			String thukho = "";
			
			String sql ="";
	
			  sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  

		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
	/*		sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  
		   */
			
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
	   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"              (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
		
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	private void CreatePxk_QUANGNGAI(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM)
	{
		try
		{			
			dbutils db = new dbutils();
				
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			
			String sql ="";
	
		    sql = " select PK_SEQ, TEN ,DIACHI," +
			  		"       DIENTHOAI, FAX, '0100108656-019' AS MASOTHUE, '102010001014275 - NH TMCP Công thương VN, CN Bến Thủy' as SOTAIKHOAN, isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
			        " from NHAPHANPHOI where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";				  

		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			   
		 //LAY THONG TIN KHACHHANG 
			   
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
		/*	sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
	   
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }
			
		  // LẤY KHO XUẤT
	   sql = " select top 1 (select diengiai from KHO where pk_seq= KHO_FK) as kho,(select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
	   		"              (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd ,  "+
	   		" 			  ( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
			"                         else isnull(nguoimua,'') end " +
			"               FROM HOADON" +
			"               WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
	        " from DONHANG " +
	        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
	   
       System.out.println("Kho xuất "+sql);
	   ResultSet rsKho= db.get(sql);
	   if(rsKho.next())
	   {
		   hinhthucTT = rsKho.getString("HTTT");		   
		   ngayxuatHD = rsKho.getString("ngayxuathd");
		   chucuahieu = rsKho.getString("nguoimua");
		   rsKho.close();
	   }
		   
	 //in phieu xuat
	   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
			   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
			   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
	   
	   
		} 
		catch (Exception e)
		{
			System.out.println("115.Exception: " + e.getMessage());
			e.printStackTrace();
		}
	
	}
	
	
	private static void CapNhatHoaDon(String hoadonID, dbutils db) 
	{
		String query = "";
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			query = "delete HOADON_SP where hoadon_fk = '" + hoadonID + "'  ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				
				System.out.println("---1.LOI ROI..." + query);
				return;
			}
			
			query = "insert HOADON_SP( hoadon_fk, sanpham_fk, donvitinh, soluong, dongia, thanhtien, chietkhau, scheme, vat )  " +
					"select '" + hoadonID + "', c.pk_seq, d.DONVI, SUM( f.soluong) as soluong, dhsp.GIAMUA AS dongia, SUM( f.soluong) * dhsp.GIAMUA, sum(dhsp.chietkhau) as chietkhau, '' as scheme, dhsp.thuevat   " +
					"from  DONHANG dh inner join DONHANG_SANPHAM dhsp on dhsp.donhang_fk=dh.PK_SEQ    " +
					"				 inner join SANPHAM c on dhsp.sanpham_fk = c.PK_SEQ                 " +
					"				 left join DONVIDOLUONG d on d.PK_SEQ = c.dvdl_fk   " +
					"				 left join PHIEUXUATKHO_DONHANG e on dh.pk_seq = e.donhang_fk  " +
					"				 left join PHIEUXUATKHO_SANPHAM_CHITIET f on e.pxk_fk = f.pxk_fk and c.pk_seq = f.sanpham_fk  " +
					"where dh.PK_SEQ in ( select ddh_fk from HOADON_DDH where hoadon_fk =  '" + hoadonID + "' ) and dhsp.soluong > 0   	  " +
					"group by dh.PK_SEQ, c.pk_seq, d.DONVI, dhsp.GIAMUA, dhsp.thuevat ";
			
			System.out.println("---DANG CHAY HOA DON: " + hoadonID );
			if(!db.update(query) )
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				
				query = "insert HOADON_BILOI( hoadon_fk, cap ) values( " + hoadonID + ", 0 ) ";
				db.update(query);
				
				//System.out.println("---2.LOI ROI..." + query);
				return;
			}
			
			//CHEN HOA DON - SAN PHAM SAN PHAM CHI TIET
			query = "delete HOADON_SP_CHITIET where hoadon_fk = '" + hoadonID + "'  ";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				
				System.out.println("---3.LOI ROI..." + query);
				return;
			}
			
			query = "insert HOADON_SP_CHITIET( hoadon_fk, MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, SOLUONG, CHIETKHAU )  " +
					"select '" + hoadonID + "' as hoadon_fk, c.MA, c.TEN, d.DONVI, dhsp.GIAMUA AS dongia,   " +
					"		case f.solo when 'NA' then ' ' else f.solo end as solo,   " +
					"		case f.solo when 'NA' then ' ' else isnull(f.ngayhethan,'') end as ngayhethan, dhsp.thuevat,     " +
					"	SUM( f.soluong) as soluong, '0' as chietkhau   " +
					"from  DONHANG dh inner join DONHANG_SANPHAM dhsp on dhsp.donhang_fk=dh.PK_SEQ    " +
					"				 inner join SANPHAM c on dhsp.sanpham_fk = c.PK_SEQ                 " +
					"				 left join DONVIDOLUONG d on d.PK_SEQ = c.dvdl_fk   " +
					"				 left join PHIEUXUATKHO_DONHANG e on dh.pk_seq = e.donhang_fk  " +
					"				 left join PHIEUXUATKHO_SANPHAM_CHITIET f on e.pxk_fk = f.pxk_fk and c.pk_seq = f.sanpham_fk  " +
					"where dh.PK_SEQ in (select ddh_fk from HOADON_DDH where hoadon_fk =  '" + hoadonID + "' )  and  dhsp.soluong > 0   	  " +
					"group by  c.MA, c.TEN, d.DONVI, dhsp.GIAMUA, dhsp.thuevat, f.solo, f.ngayhethan  ";
			//System.out.println("---CHAY HOA DON CHI TIET: " + hoadonID );
			if(!db.update(query) )
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				
				query = "insert HOADON_BILOI( hoadon_fk, cap ) values( " + hoadonID + ", 1 ) ";
				db.update(query);
				
				System.out.println("---3.LOI ROI..." + query);
				return;
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			try
			{
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			} 
			catch (Exception e1) 
			{
				
				query = "insert HOADON_BILOI( hoadon_fk, cap ) values( " + hoadonID + ", 2 ) ";
				db.update(query);
				
				//System.out.println("---2.LOI ROI..." + query);
				return;
			}
		}

	}
	

	private void CreatePxk_TAYNINH(Document document,ServletOutputStream outstream, IHoadontaichinhKM pxkBean, String sqlIN_SANPHAM) 
	{
		try
		{
			dbutils db = new dbutils();
			
			//thong tin nha phan phoi
			
			//LAY THONG TIN NHA CUNG CAP --moi
			
			//LAY THONG TIN NCC
			String kbh="";
			String ddh="";
			String npp_fk="";
			String khId="";
			double Vat= 0;
			
			String ctyTen="";
			String cty_MST ="";
			String cty_Diachi="";
			String cty_Sotaikhoan= "";
			String cty_Dienthoai= "";
			String cty_Fax= "";
			String khoxuat ="";
			String thukho = "";
			String sql ="";
	
		   if(kbh.equals("100052")) // ETC
		   {
			  sql = " select PK_SEQ, TEN ,'75 phố Yên Ninh, Phường Quán Thánh, Quận Ba Đình, Thành phố Hà Nội, Việt Nam' AS DIACHI," +
			  		"       '04.38454813' AS DIENTHOAI,'04.36811542' AS FAX,'0100108656' AS MASOTHUE, '102010000004158 - NH TMCP Công thương VN, CN Ba Đình' as SOTAIKHOAN "+
			        " from NHACUNGCAP ";				  
		   }else{ //OTC
			  /* sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, FAX, '' as SOTAIKHOAN ,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho,'') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";*/
			   
			   //moi npp
			   sql = " select PK_SEQ, TEN ,DIACHIXHD as DIACHI, MASOTHUE,DIENTHOAI, isnull( FAX,'') as FAX, isnull( SOTAIKHOAN,'') as SOTAIKHOAN,isnull(XUATTAIKHO,' ') as XUATTAIKHO, isnull(thukho, '') thukho "+
		        " from NHAPHANPHOI" +
		        " where PK_SEQ = (select npp_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";

			   
		   }
		   System.out.println("Lấy TT CTY "+sql);
		   ResultSet rsINFO = db.get(sql);
		   if(rsINFO.next())
		   {
			   khoxuat = rsINFO.getString("XUATTAIKHO");
			   ctyTen = rsINFO.getString("TEN");
			   cty_MST = rsINFO.getString("MASOTHUE");
			   cty_Diachi = rsINFO.getString("DIACHI");
			   cty_Sotaikhoan = rsINFO.getString("SOTAIKHOAN");
			   cty_Dienthoai = rsINFO.getString("DIENTHOAI");
			   cty_Fax = rsINFO.getString("FAX");
			   thukho = rsINFO.getString("thukho");
			   rsINFO.close();
			   
		   }
			
			
			 //LAY THONG TIN KHACHHANG 		  
			//String sql ="";
			String Donvi="";
			String kh_MST ="";
			String kh_Diachi="";
			String hinhthucTT= "";
			String ngayxuatHD= "";
			String chucuahieu = "";
			String sotaikhoan= "";
			//String thukho = "";
			
			/*sql = " select  TEN ,DIACHI, isnull(MASOTHUE ,' ' ) as MASOTHUE "+
		        " from KHACHHANG " +
		        " where PK_SEQ = (select khachhang_fk from HOADON where pk_seq = '"+ pxkBean.getId() +"') ";	*/			  
		   
			//LẤY THEO DỮ LIỆU MỚI
			/*sql = " SELECT TENKHACHHANG TEN, DIACHI, ISNULL(MASOTHUE,'') MASOTHUE, (SELECT isnull(thukho,'') FROM NHAPHANPHOI where PK_SEQ = npp_fk) thukho  " +
			  	  " FROM   HOADON WHERE PK_SEQ ='"+pxkBean.getId()+"'";*/
			
			//moi
			sql = "SELECT HD.TENKHACHHANG TEN, HD.DIACHI, ISNULL(HD.MASOTHUE,'') MASOTHUE, HD.HINHTHUCTT AS HINHTHUCTT, ISNULL(KH.TAIKHOAN_FK,0) AS TAIKHOAN "+   
					"FROM HOADON HD INNER JOIN KHACHHANG KH ON KH.PK_SEQ=HD.KHACHHANG_FK "+   
					"WHERE HD.PK_SEQ ='"+pxkBean.getId()+"'";
		   String kh_sotaikhoan = "";
		   System.out.println("Lấy TT KH "+sql);
		   ResultSet rsLayKH= db.get(sql);
		   if(rsLayKH.next())
		   {
			   Donvi = rsLayKH.getString("TEN");
			   kh_MST = rsLayKH.getString("MASOTHUE");
			   kh_Diachi = rsLayKH.getString("DIACHI");
			   hinhthucTT= rsLayKH.getString("HINHTHUCTT");
			   kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
			   rsLayKH.close();
			   
		   }   


		   // LẤY KHO XUẤT
		   sql = " select top 1 (select XUATTAIKHO from NHAPHANPHOI where PK_SEQ = NPP_FK) as kho," +
		   		"               (select hinhthuctt from HOADON where PK_SEQ= '"+ pxkBean.getId() +"' ) as HTTT," +
		   		"               (select ngayxuathd from HOADON where pk_seq = '"+ pxkBean.getId() +"') as ngayxuathd, " +
		   		" 				( SELECT case when  nguoimua is null then (select isnull(chucuahieu,'') from khachhang where pk_seq= khachhang_fk ) " +
				"                         else isnull(nguoimua,'') end " +
				"           	  FROM HOADON" +
				"           	  WHERE PK_SEQ= '"+ pxkBean.getId() +"' ) AS nguoimua "  +
		        " from DONHANG " +
		        " where PK_SEQ in (select DDH_FK from HOADON_DDH where HOADON_FK = '"+ pxkBean.getId() +"') ";				  
		   
	       System.out.println("Kho xuất "+sql);
		   ResultSet rsKho= db.get(sql);
		   if(rsKho.next())
		   {
			   hinhthucTT = rsKho.getString("HTTT");		   
			   ngayxuatHD = rsKho.getString("ngayxuathd");
			   chucuahieu = rsKho.getString("nguoimua");
			   rsKho.close();
		   }
		   
		 //in phieu xuat
		   form_phieuxuat(document, outstream, sqlIN_SANPHAM, pxkBean, pxkBean.getId(),
				   ctyTen, cty_MST, cty_Diachi, cty_Sotaikhoan, cty_Dienthoai, cty_Fax, Donvi, 
				   kh_MST, hinhthucTT, ngayxuatHD, chucuahieu, kh_sotaikhoan, kh_Diachi, db, khoxuat);
		   
		 
		   
		}catch(Exception e)
		{
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
