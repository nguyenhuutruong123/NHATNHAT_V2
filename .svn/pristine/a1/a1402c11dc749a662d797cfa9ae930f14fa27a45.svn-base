package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.center.util.Utility;
import geso.dms.erp.beans.danhmucvattu.IDanhmucvattu_SP;
import geso.dms.erp.beans.danhmucvattu.imp.Danhmucvattu_SP;
import geso.dms.erp.beans.lenhsanxuat.IErpLenhsanxuat;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpLenhsanxuat;
import geso.dms.erp.beans.nhapkho.*;
import geso.dms.erp.beans.nhapkho.imp.*;
import geso.dms.erp.beans.phieuxuatkho.ISpDetail;
import geso.dms.erp.beans.phieuxuatkho.imp.SpDetail;
import geso.dms.erp.db.sql.dbutils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ErpLenhsanxuatActionSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	PrintWriter out;
    public ErpLenhsanxuatActionSvl() 
    {
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
			response.sendRedirect("/SalesUp/index.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
		
			this.out = response.getWriter();
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId")); 	
		    String id = util.getId(querystring);  	
		    
		    IErpLenhsanxuat lsxBean = new ErpLenhsanxuat(id);
		    lsxBean.setUserId(userId);
	        String nextJSP;
	        
	        
        	lsxBean.initDisplay();
        	nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatDisplay.jsp";
	        
	        session.setAttribute("lsxBean", lsxBean);
	        response.sendRedirect(nextJSP);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");

		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect("/SalesUp/index.jsp");
		} 
		else
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			session.setMaxInactiveInterval(30000);
			//this.out = response.getWriter();
			
			String task = request.getParameter("task");
			if(task == null)
				task = "";
			
			if(task.equals("tieuHao"))
			{
				this.tieuHaoNL(userId, session, request, response);
			}
			else 
			{
				this.nhapKhoLSX(userId, session, request, response);
			}
		 
		}
			
	}

	private void nhapKhoLSX(String userId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		IErpNhapkho nkBean;

		Utility util = new Utility();
		String id = util.antiSQLInspection(request.getParameter("id"));

		if (id == null)
		{
			nkBean = new ErpNhapkho("");
		} 
		else
		{
			nkBean = new ErpNhapkho(id);
		}

		nkBean.setCongtyId((String)session.getAttribute("congtyId"));
		nkBean.setUserId(userId);

		String ngaygd = util.antiSQLInspection(request.getParameter("ngaynhapkho"));
		if (ngaygd == null || ngaygd == "")
			ngaygd = this.getDateTime();
		nkBean.setNgaynhapkho(ngaygd);

    	String solenhsanxuat = util.antiSQLInspection(request.getParameter("solenhsanxuat"));
		if (solenhsanxuat == null)
			solenhsanxuat = "";				
    	nkBean.setSoLenhsx(solenhsanxuat);
    	
    	String soluongsx = util.antiSQLInspection(request.getParameter("soluongsx"));
		if (soluongsx == null)
			soluongsx = "";				
    	nkBean.setSoluongLSX(soluongsx);

		String noidungnhap = util.antiSQLInspection(request.getParameter("noidungnhap"));
		if (noidungnhap == null)
			noidungnhap = "";
		nkBean.setNdnId(noidungnhap);

		String khonhap = request.getParameter("khonhap");
		if (khonhap == null)
			khonhap = "";
		nkBean.setKhoId(khonhap);

		// Luu lai san pham
		String[] mahangmua = request.getParameterValues("mahangmua");
		String[] diengiai = request.getParameterValues("diengiai");
		String[] soluongnhan = request.getParameterValues("soluongnhan");
		String[] soluongnhap = request.getParameterValues("soluongnhap");
		String[] solo = request.getParameterValues("solo");
		String[] ngayhethan = request.getParameterValues("ngayhethan");
		String[] dongia = request.getParameterValues("dongia");
		String[] dongiaViet = request.getParameterValues("dongiaViet");
		String[] tiente = request.getParameterValues("tiente");

		List<ISanpham> spList = new ArrayList<ISanpham>();

		if (mahangmua != null)
		{
			ISanpham sanpham = null;
			String[] param = new String[11];
			int m = 0;
			while (m < mahangmua.length)
			{
				if (mahangmua[m] != "")
				{
					param[0] = "";
					param[1] = mahangmua[m];
					param[2] = diengiai[m];
					param[3] = solo[m];
					param[4] = soluongnhan[m];
					param[5] = soluongnhap[m];
					double slnhap = 0;
					try { slnhap = Float.parseFloat(param[5]); } catch(Exception e) {}
					DecimalFormat df = new DecimalFormat("########0.###");
					param[5] = df.format(slnhap);

					sanpham = new Sanpham(param);
					sanpham.setDongia(dongia[m]);
					sanpham.setDongiaViet(dongiaViet[m]);
					sanpham.setTiente(tiente[m]);
					sanpham.setNgayhethan(ngayhethan[m]);
					sanpham.setSoluongDanhan(soluongnhan[m]);
 
					spList.add(sanpham);
				}
				m++;
			}
		}
		nkBean.setSpList(spList);
		nkBean.setGhichu(request.getParameter("ghichu"));
		String action = request.getParameter("action");

		if (action.equals("save"))
		{
			if (!nkBean.createNhapKhoLSX())
			{
				nkBean.createRs();
				
				session.setAttribute("nkBean", nkBean);
				String nextJSP = "/SalesUp/pages/Erp/ErpNhapKhoLenhSanXuat.jsp";
				response.sendRedirect(nextJSP);
			} 
			else
			{
				IErpLenhsanxuat lsxBean = new ErpLenhsanxuat(solenhsanxuat);
			    lsxBean.setUserId(userId);
		        
	        	lsxBean.initDisplay();
	        	String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatDisplay.jsp";
		        
		        session.setAttribute("lsxBean", lsxBean);
		        response.sendRedirect(nextJSP);
			}
		}
		else
		{
			nkBean.createRs();

			String nextJSP = "/SalesUp/pages/Erp/ErpNhapKhoLenhSanXuat.jsp";
			
			session.setAttribute("nkBean", nkBean);
			response.sendRedirect(nextJSP);
		}
	}


	private void tieuHaoNL(String userId, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		IErpLenhsanxuat lsxBean;
		
		Utility util = new Utility();	
		String id = util.antiSQLInspection(request.getParameter("id"));
	    if(id == null)
	    {  	
	    	lsxBean = new ErpLenhsanxuat("");
	    }
	    else
	    {
	    	lsxBean = new ErpLenhsanxuat(id);
	    }

	    lsxBean.setUserId(userId);
	    lsxBean.init();
    	
    	String spIds = util.antiSQLInspection(request.getParameter("spIds"));
		if (spIds == null)
			spIds = "";				
		lsxBean.setSpId(spIds);
		
		String nhapkhoid = util.antiSQLInspection(request.getParameter("nhapkhoid"));
		if (nhapkhoid == null)
			nhapkhoid = "";				
		lsxBean.setNhapkhoId(nhapkhoid);
		
		String ngaytieuhao = util.antiSQLInspection(request.getParameter("ngaytieuhao"));
		if (ngaytieuhao == null)
			ngaytieuhao = "";				
		lsxBean.setNgaytieuhao(ngaytieuhao);
 
		System.out.println("Nhap Kho ID Nek : "+nhapkhoid);  
		
		
    	String soluongsx = util.antiSQLInspection(request.getParameter("soluongsx"));
		if (soluongsx == null)
			soluongsx = "";				
		lsxBean.setSoLuongNhapKho(soluongsx);
 
		String soluongchuan = util.antiSQLInspection(request.getParameter("soluongchuan"));
		if (soluongchuan == null)
			soluongchuan = "";
		lsxBean.setSoluong(soluongchuan);
		
		String chophepTT = util.antiSQLInspection(request.getParameter("chophepTT"));
		if (chophepTT == null)
			chophepTT = "0";
		lsxBean.setChophepTT(chophepTT);

		String[] mavt = request.getParameterValues("mavattu");
		String[] tenvt = request.getParameterValues("tenvattu");
		String[] donvitinh = request.getParameterValues("donvitinh");
		String[] soluong = request.getParameterValues("soluongDM");
		String[] soluongTHThucte = request.getParameterValues("thucte");
		
		List<IDanhmucvattu_SP> spList = new ArrayList<IDanhmucvattu_SP>();
		dbutils db = new dbutils();
		String khoTieuhao_fk = "";  
		
		String sql= " select khosx.PK_SEQ  from ERP_NHAPKHO nk "+  
					" inner join ERP_LENHSANXUAT lsx on lsx.PK_SEQ=nk.SOLENHSANXUAT "+ 
					" inner join ERP_KHOTT kho on kho.PK_SEQ=lsx.KHO_FK "+
					" inner join ERP_KHOTT khosx on khosx.TRUNGTAMPP_FK=kho.PK_SEQ "+  
					" and khosx.LOAI='1'  where nk.PK_SEQ="+nhapkhoid;
		ResultSet rs=db.get(sql);
		try{
			rs.next();
			khoTieuhao_fk=rs.getString("pk_seq");
			rs.close();
			
		}catch (Exception  err) {
			err.printStackTrace();
		}
		
		session.setAttribute("khoTieuhao_fk", khoTieuhao_fk);
		
		
		String action = request.getParameter("action");
		if(mavt != null)
		{	
			for(int m = 0; m < mavt.length; m++)
			{	
				if(mavt[m] != "")
				{	
					if(soluongTHThucte[m].trim().length() >  0)
					{	
						IDanhmucvattu_SP sanpham = null;
						sanpham = new Danhmucvattu_SP();
						sanpham.setMaVatTu(mavt[m]);
						sanpham.setTenVatTu(tenvt[m]);
						sanpham.setDvtVT(donvitinh[m]);
						sanpham.setSoLuong(soluong[m]);
						 
						sanpham.setSoLuongTHThucTe(soluongTHThucte[m]);
						//Tao Bean / Lo
						List<ISpDetail> spConList = new ArrayList<ISpDetail>();
						if(!action.equals("save")){
							if(soluongTHThucte[m].trim().length() > 0 && !soluongTHThucte[m].equals("0"))
							{
								List<ISpDetail> spDetail = new ArrayList<ISpDetail>();
								
								String query =  " select SANPHAM_FK, isnull(AVAILABLE, 0) as soluong, SOLO,a.ngayhethan " +
											    " from ERP_KHOTT_SP_CHITIET a  " +
												" where   isnull(AVAILABLE, 0) >0 and a.khott_fk = '" + khoTieuhao_fk + "'  and a.sanpham_fk = ( select pk_seq from SanPham where ma = '" + mavt[m].trim() + "' ) " +
												" order by a.ngayhethan asc, a.AVAILABLE asc";
								 
								//System.out.println("Get du lieu : "+query);
								ResultSet rsSpDetail = db.get(query);
								 
									float tongluong = 0;
									try 
									{
						 
										
										double tongsoluongxuat=Double.parseDouble(soluongTHThucte[m]);
										System.out.println("tongsoluongxuat"+tongsoluongxuat);
										while(rsSpDetail.next())
										{	
											ISpDetail	spCon = new SpDetail();
											String slgton = rsSpDetail.getDouble("soluong")+"";
											String solo = rsSpDetail.getString("solo");
											 
												if( tongsoluongxuat >0) {
													if(tongsoluongxuat <rsSpDetail.getDouble("soluong")){
														spCon.setSoluong(tongsoluongxuat+"");
														tongsoluongxuat=0;
													}else{
														tongsoluongxuat=tongsoluongxuat-rsSpDetail.getDouble("soluong");
														spCon.setSoluong(slgton);
													}
												}else{
													spCon.setSoluong("0");
												}
										
											spCon.setSoluongton(slgton);
											spCon.setSolo(solo);
											spCon.setVitriId("100000");
											spConList.add(spCon);
										 
										}
										rsSpDetail.close();
										
										System.out.println("List Sna pham  : " +spConList.size());
									} 
									catch (Exception e) 
									{
										e.printStackTrace();
									}
									 
								}
						}else{
							String[] soluongchitiet  = request.getParameterValues(mavt[m]+".soluong");
							String[] solo = request.getParameterValues(mavt[m]+".solo");
							
							if(soluongchitiet !=null) {
								for (int j=0;j<soluongchitiet.length;j++){
									double soluongct=0;
									try{
										soluongct=Double.parseDouble(soluongchitiet[j]);
									}catch(Exception err){
										
									}
									if(soluongct > 0){
										ISpDetail	spCon = new SpDetail();
										spCon.setSoluong(soluongct+"");
										spCon.setSolo(solo[j]);
										spConList.add(spCon);
										
									}
									
								}
							}
							
						}
					
						sanpham.setSpDetailList(spConList);
						spList.add(sanpham);												
					}
				}				
			}
			
			lsxBean.setListDanhMuc(spList);
			db.shutDown();
			
			//Check 
			String msg = "";
			
			 if(!action.equals("reload")){
			
					for(int i = 0; i < spList.size(); i++)
					{
						IDanhmucvattu_SP sp = spList.get(i);
						
						if(sp.getSoLuongTHThucTe().trim().length() > 0 && !sp.getSoLuongTHThucTe().equals("0") )
						{
							List<ISpDetail> spDetail = sp.getSpDetailList();
							
							float sum = 0;
							for(int j = 0; j < spDetail.size(); j++)
							{
								sum += Float.parseFloat(spDetail.get(j).getSoluong());
							}
							
							if(sum < Float.parseFloat(sp.getSoLuongTHThucTe()))
							{
								msg += "+ Sản phẩm " + sp.getMaVatTu() + " - " + sp.getTenVatTu() + ", không đủ số lượng trong kho sản xuất để tiêu hao, vui lòng kiểm tra lại \n";
							}
						}
					}
			 }
			
			lsxBean.setMsg(msg);
		}	
		
		
		System.out.println("Actiong nek : "+action);
	   
	    if(action.equals("save"))
		{	
			if(!lsxBean.tieuhaoLsx(khoTieuhao_fk))
			{
				session.setAttribute("lsxBean", lsxBean);
				
				String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatTieuHao.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				lsxBean = new ErpLenhsanxuat(id);
			    lsxBean.setUserId(userId);
		        
	        	lsxBean.initDisplay();
	        	String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatDisplay.jsp";
		        
		        session.setAttribute("lsxBean", lsxBean);
		        response.sendRedirect(nextJSP);
			}
		} 
	    else if(action.equals("reload"))
	    {
			lsxBean.checkTieuHaoLsx();
			session.setAttribute("lsxBean", lsxBean);
			String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatTieuHao.jsp";
			response.sendRedirect(nextJSP);
		}
	    else if(action.equals("print")) 
	    {
	    	response.setContentType("application/pdf");
			response.setHeader("Content-Disposition"," inline; filename=PhieuNhapKhoTT.pdf");
			
			//Rectangle a4Landscape = a4.rotate();
			Document document = new Document(PageSize.A4.rotate());
			
			ServletOutputStream outstream = response.getOutputStream();
			lsxBean.setUserId(userId);
			lsxBean.initDisplay();
			lsxBean.checkTieuHaoLsx();
			this.CreatePhieuTieuHao(document, outstream, lsxBean);
	    }
		else
		{
			
			session.setAttribute("lsxBean", lsxBean);
			String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatTieuHao.jsp";
			response.sendRedirect(nextJSP);
		}
	    
	}
	


	private void CreatePhieuTieuHao(Document document, ServletOutputStream outstream, IErpLenhsanxuat lsxBean) throws IOException
	{
		
		dbutils db = new dbutils();
		try
		{			
			NumberFormat formatter = new DecimalFormat("#,###,##0.000"); 
			PdfWriter.getInstance(document, outstream);
			document.open();

			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font = new Font(bf, 15, Font.BOLD);
			Font font2 = new Font(bf, 8, Font.BOLD);
			//font2.setColor(BaseColor.GREEN);
			
			String 
			query = " SELECT ten, diachi, masothue, dienthoai, fax FROM ERP_CONGTY WHERE PK_SEQ = 100005 ";
			ResultSet rsCt = db.get(query);
			String ctyTen = "";
			String ctyDiachi = "";
			String ctyMasothue = "";
			String ctyDienthoai = "";
			String ctyFax = "";
			try {
				rsCt.next();
				ctyTen = rsCt.getString("ten");
				ctyDiachi = rsCt.getString("diachi");
				ctyMasothue = rsCt.getString("masothue");
				ctyDienthoai = rsCt.getString("dienthoai");
				ctyFax = rsCt.getString("fax");
				rsCt.close();
			} catch(Exception e) {
				ctyTen = "CÔNG TY CỔ PHẦN HÀNG TIÊU DÙNG PROVENCE";
				ctyDiachi = "Lầu 8 161 Võ Văn Tần, Phường 6, Quận 3, Tp.Hồ Chí Minh";
				ctyMasothue = "0 3 1 0 7 7 6 0 7 1";
				ctyDienthoai = "(08) 62905560";
				ctyFax = "(08) 62905104";
			}
			 
			Paragraph pxk = new Paragraph("Đơn vị: " + ctyTen, new Font(bf, 8, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setSpacingBefore(-25);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("Địa chỉ: " + ctyDiachi, new Font(bf, 8, Font.NORMAL));
			pxk.setSpacingAfter(2);
			pxk.setSpacingBefore(-25);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("PHIẾU TIÊU HAO SẢN XUẤT", font);
			pxk.setSpacingAfter(3);
			pxk.setSpacingBefore(10);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			
			pxk = new Paragraph("Số: " + lsxBean.getId(), new Font(bf, 7, Font.NORMAL));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_RIGHT);
			document.add(pxk);
			
			pxk = new Paragraph(getDate(lsxBean.getNgaytao()),  new Font(bf, 9, Font.NORMAL));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_CENTER);
			document.add(pxk);
			
			pxk = new Paragraph("Kho: " + lsxBean.getKho(),  new Font(bf, 9, Font.NORMAL));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			pxk = new Paragraph("Đối tượng tiêu hao: " + lsxBean.getSanPham(),  new Font(bf, 9, Font.NORMAL));
			pxk.setSpacingAfter(5);
			pxk.setAlignment(Element.ALIGN_LEFT);
			document.add(pxk);
			
			
			
			//Table Content
			PdfPTable root = new PdfPTable(2);
			root.setKeepTogether(false);
			root.setSplitLate(false);
			root.setWidthPercentage(100);
			root.setHorizontalAlignment(Element.ALIGN_LEFT);
			root.getDefaultCell().setBorder(0);
			float[] cr = {95.0f, 100.0f};
			root.setWidths(cr);
			
			PdfPTable table = new PdfPTable(5);
			table.setWidthPercentage(100);
			table.setHorizontalAlignment(Element.ALIGN_LEFT);
			float[] withs = {7.0f, 22.0f, 40.0f, 13.0f, 13.0f};
	        table.setWidths(withs);
	        
	        Font font4 = new Font(bf, 9, Font.BOLD);
		
	        float[] withsKM = {15.0f, 70.0f, 30.0f, 20.0f, 30.0f, 20.0f, 30.0f };
			PdfPTable sanpham = new PdfPTable(withsKM.length);
			sanpham.setWidthPercentage(100);
			sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			sanpham.setWidths(withsKM);
		    
		    String[] th = new String[]{"STT", "Tên, nhãn hiệu, quy cách, phẩm chất vật tư (sản phẩm, hàng hóa)", "Mã số", "ĐVT", "Số lô", "Ngày sản xuất", "Số lượng tiêu hao"};
			PdfPCell[] cell = new PdfPCell[10];
			for(int i= 0; i < th.length ; i++)
			{
				cell[i] = new PdfPCell(new Paragraph(th[i], font4));
				cell[i].setHorizontalAlignment(Element.ALIGN_CENTER);
				if(i == 1)
					cell[i].setHorizontalAlignment(Element.ALIGN_LEFT);
				cell[i].setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				cell[i].setPadding(5);
				sanpham.addCell(cell[i]);			
			}
			
			
			List<IDanhmucvattu_SP> spList = lsxBean.getListDanhMuc();
			PdfPCell cells = new PdfPCell();
//			float totalTrongLuong = 0;
//			float totalTheTich = 0;
			double totalSoluong=0;
			double totalSoluongTT=0;
//			double totalthung=0;
//			double totalle=0;
			for(int i = 0; i < spList.size(); i++)
			{
				IDanhmucvattu_SP sp = (IDanhmucvattu_SP)spList.get(i);
				List<ISpDetail> spDetailList = sp.getSpDetailList();
				if(spDetailList.size() > 0)
            	{
            		for(int sd = 0; sd < spDetailList.size(); sd ++)
            		{
            			ISpDetail spDetail = spDetailList.get(sd);
						String[] arr = new String[]{Integer.toString(i+1), sp.getTenVatTu(), sp.getMaVatTu(), sp.getDvtVT(), 
								spDetail.getSolo(), spDetail.getNgaysanxuat(), formatter.format(Double.parseDouble(spDetail.getSoluong()))
						};
            			
//						String[] arr = new String[]{Integer.toString(i+1), sp.getTenVatTu(), sp.getMaVatTu(), sp.getDvtVT(), 
//								formatter.format(Double.parseDouble(sp.getSoLuong())), formatter.format(Double.parseDouble(sp.getSoLuongTHThucTe()))
//						};
						
						for(int j = 0; j < th.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 9, Font.NORMAL)));
							if(j == 1)
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							else
							{
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);
								if( j >= 5)
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							cells.setVerticalAlignment(Element.ALIGN_MIDDLE);
							cells.setPadding(3.0f);
							
							sanpham.addCell(cells);
						}
            		}
            	}
				totalSoluong += Double.parseDouble(sp.getSoLuong());
				totalSoluongTT += Double.parseDouble(sp.getSoLuongTHThucTe());
			}
			
			document.add(sanpham);
			
			//Table Footer			
			PdfPTable tableFooter = new PdfPTable(4);
			tableFooter.setWidthPercentage(90);
			tableFooter.setHorizontalAlignment(Element.ALIGN_CENTER);
			tableFooter.setWidths(new float[]{38.0f, 38.0f, 38.0f, 39.0f});
			
			PdfPCell cell11 = new PdfPCell(new Paragraph("Người lập phiếu", new Font(bf, 9, Font.BOLD)));
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell13 = new PdfPCell(new Paragraph("Thủ kho", new Font(bf, 9, Font.BOLD)));
			cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell14 = new PdfPCell(new Paragraph("Người nhận", new Font(bf, 9, Font.BOLD)));
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			PdfPCell cell15 = new PdfPCell(new Paragraph("Kế toán", new Font(bf, 9, Font.BOLD)));
			cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			cell11.setBorder(0);
			cell13.setBorder(0);
			cell14.setBorder(0);
			cell15.setBorder(0);
			
			tableFooter.addCell(cell11);
			tableFooter.addCell(cell13);
			tableFooter.addCell(cell14);
			tableFooter.addCell(cell15);
			
			cell11 = new PdfPCell(new Paragraph("(Ký, họ tên)", new Font(bf, 9, Font.ITALIC)));
			cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell13 = new PdfPCell(new Paragraph("(Ký, họ tên)", new Font(bf, 9, Font.ITALIC)));
			cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell14 = new PdfPCell(new Paragraph("(Ký, họ tên)", new Font(bf, 9, Font.ITALIC)));
			cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell15 = new PdfPCell(new Paragraph("(Ký, họ tên)", new Font(bf, 9, Font.ITALIC)));
			cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			cell11.setBorder(0);
			cell13.setBorder(0);
			cell14.setBorder(0);
			cell15.setBorder(0);
			
			tableFooter.addCell(cell11);
			tableFooter.addCell(cell13);
			tableFooter.addCell(cell14);
			tableFooter.addCell(cell15);
			
			document.add(tableFooter);
			document.close();
		}
		catch(DocumentException e)
		{
			e.printStackTrace();
		}
		db.shutDown();
	}
	
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}


	private String getDate(String date)
	{
		String arr[] = date.split("-");
		String nam = arr[0];
		String thang = arr[1];
		String ngay = arr[2];
		
		return "Ngày  " + ngay + "  tháng  " + thang + "  Năm  " + nam;
	}

}
