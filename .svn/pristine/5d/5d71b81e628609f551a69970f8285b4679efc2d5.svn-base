package geso.dms.center.servlets.kehoachpg;

import geso.dms.center.beans.kehoachpg.imp.*;
import geso.dms.center.beans.kehoachpg.*;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Style;
import com.aspose.cells.Worksheet;
import com.oreilly.servlet.MultipartRequest;

public class KehoachPGUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	PrintWriter out;

	public KehoachPGUpdateSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		IKehoachPG ddkdBean;
		this.out = response.getWriter();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("userId")));

		String id = util.getId(querystring);
		String view = util.antiSQLInspection(util.antiSQLInspection(request.getParameter("view")));
		if (view == null) {
			view = "";
		}

		ddkdBean = (IKehoachPG) new KehoachPG(id,userId,view);
		session.setAttribute("ddkdBean", ddkdBean);
		String nextJSP = request.getContextPath() + "/pages/Center/KehoachPGUpdate.jsp";
		if(querystring.indexOf("display") > 0)
			nextJSP = request.getContextPath() + "/pages/Center/KehoachPGDisplay.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		/*
		 * geso.dms.center.util.Csrf csdr=new
		 * geso.dms.center.util.Csrf(request,response,false,true ,false);
		 * if(!csdr.__validate_post()) {
		 * response.sendRedirect(request.getContextPath() + "/redirect.jsp"); return; }
		 */

		IKehoachPG ddkdBean;



		Utility util = new Utility();
		MultipartRequest multi = null;

		String contentType = request.getContentType();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			multi = new MultipartRequest(request,"C:\\java-tomcat\\data\\", 20000000, "UTF-8");
		}	

		String action = Utility.getValueFromClient("action",contentType,multi,request);
		if (action == null)
			action = "";
		String id = Utility.getValueFromClient("id",contentType,multi,request);

		if (id == null)
			ddkdBean = new KehoachPG("");
		else
			ddkdBean = new KehoachPG(id);

		String view = Utility.getValueFromClient("view",contentType,multi,request);
		ddkdBean.setView(view);
		String userId = Utility.getValueFromClient("userId",contentType,multi,request);   
		ddkdBean.setUserId(userId);

		session.setAttribute("userId", userId);

		String ddkdTen = Utility.getValueFromClient("ddkdTen",contentType,multi,request);   
		if (ddkdTen == null)
			ddkdTen = "";
		ddkdBean.setTen(ddkdTen);

		String nppId = Utility.getValueFromClient("nppId",contentType,multi,request);   
		if (nppId == null)
			nppId = "";
		ddkdBean.setNppId(nppId);

		String tuan = Utility.getValueFromClient("tuan",contentType,multi,request);   
		if (tuan == null)
			tuan = "";
		ddkdBean.setTuan(tuan);

		String nam = Utility.getValueFromClient("nam",contentType,multi,request);   
		if (nam == null)
			nam = "";
		ddkdBean.getNam();


		String ngaysua = getDateTime();
		ddkdBean.setNgaysua(ngaysua);

		String nguoisua = userId;
		ddkdBean.setNguoisua(nguoisua);

		String nextJSP = "";

		if(action.equals("excel"))
		{
			XuatTemplate( response, ddkdBean) ;			 
			session.setAttribute("obj", ddkdBean);
			nextJSP = request.getContextPath() + "/pages/Center/KehoachPGNew.jsp";
			response.sendRedirect(nextJSP);
			return ;
		}
		else
		if (action.equals("save"))
		{
			if (id == null||id.length()==0)
			{
				if (!(ddkdBean.CreateKehoach( contentType, multi, request)))
				{
					ddkdBean.createRS();
					session.setAttribute("ddkdBean", ddkdBean);
					ddkdBean.setUserId(userId);
					nextJSP = request.getContextPath() + "/pages/Center/KehoachPGNew.jsp";
					response.sendRedirect(nextJSP);
					return;
				} else
				{
					IKehoachPGList obj = new KehoachPGList();
					obj.setView(view);
					obj.setUserId(userId);
					obj.init(contentType,request, multi);
					obj.setMsg("Upload thành công!");
					session.setAttribute("obj", obj);
					nextJSP = request.getContextPath() + "/pages/Center/KehoachPG.jsp";
					response.sendRedirect(nextJSP);
					return;
				}
			} else
			{
				if (!(ddkdBean.UpdateKehoach( contentType, multi, request)))
				{
					ddkdBean.createRS();
					session.setAttribute("ddkdBean", ddkdBean);
					ddkdBean.setUserId(userId);
					nextJSP = request.getContextPath() + "/pages/Center/KehoachPGUpdate.jsp";
					response.sendRedirect(nextJSP);
					return;
				} else
				{
					IKehoachPGList obj = new KehoachPGList();
					obj.setView(view);
					obj.setUserId(userId);
					obj.init(contentType,request, multi);
					obj.setMsg("Upload thành công!");
					session.setAttribute("obj", obj);
					nextJSP = request.getContextPath() + "/pages/Center/KehoachPG.jsp";
					response.sendRedirect(nextJSP);
					return;
				}
			}
		}
		else
		{
			ddkdBean.createRS();
			ddkdBean.setUserId(userId);
			session.setAttribute("ddkdBean", ddkdBean);
			if (id == null)
			{
				nextJSP = request.getContextPath() + "/pages/Center/KehoachPGNew.jsp";
			} else
			{
				nextJSP = request.getContextPath() + "/pages/Center/KehoachPGUpdate.jsp";
			}
			response.sendRedirect(nextJSP);
		}
	}



	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String getDateTime2()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private void XuatTemplate1(HttpServletResponse response, IKehoachPG  obj) 
	{
		OutputStream out1 = null;
		try
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=teamplatePG.xls");
			WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());
			
			geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
			
			String sql = "select npp.ten npp,ddkd.smartId, ddkd.pk_seq, ddkd.ten " +
				" FROM daidienkinhdoanh ddkd  " +
				" inner join nhaphanphoi npp on npp.pk_seq = ddkd.npp_fk " +
				" where ddkd.isPG = 1 and ddkd.TRANGTHAI = 1  and ddkd.npp_fk = "+ obj.getNppId();	
			System.out.println("danh sach NVBH :" + sql);
			ResultSet rs = obj.getDb().get(sql);
			int k = 0;
			boolean chkRs = false;
			while (rs.next())
			{
				chkRs = true;
				WritableSheet sheet = w.createSheet(rs.getString("pk_seq"), k);

				sheet.addCell(new Label(0, 1, "NPP : "));
				sheet.addCell(new Label(1, 1, "" +  rs.getString("npp")  ));
				
				sheet.addCell(new Label(0, 2, "PG : "));
				sheet.addCell(new Label(1, 2, "" + rs.getString("ten") +" - " + rs.getString("smartId")  ));



				WritableFont cellFont = new WritableFont(WritableFont.TIMES, 12);
				cellFont.setColour(Colour.BLACK);

				WritableCellFormat cellFormat = new WritableCellFormat(cellFont);

				cellFormat.setBackground(jxl.format.Colour.GRAY_25);
				cellFormat.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);

				int cot = 0;
				sheet.addCell(new Label(cot++, 4, "STT", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Chủ nhật", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Thứ 2", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Thứ 3", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Thứ 4", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Thứ 5", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Thứ 6", cellFormat));
				sheet.addCell(new Label(cot++, 4, "Thứ 7", cellFormat));
				
				
				WritableCellFormat cellFormat2 = new WritableCellFormat();
				if(obj.getId() != null && obj.getId().trim().length() > 0)
				for(int x = 1; x <= 7; x++)
				{
					int row = 5;
					int stt = 1;
					sql = "\n select kh.smartId  "  +
							"\n from KeHoachPG_KhachHang_Ngay a" +
							"\n inner join khachhang kh on kh.pk_seq = a.khachhang_fk  " +
							"\n where a.kehoachPG_fk = "+obj.getId()+" and a.ddkd_fk = "+rs.getString("pk_seq")+"  and a.ngayId = " + x +
							"\n order by stt" ;
					ResultSet rsct = obj.getDb().get(sql);
					while(rsct.next())
					{
						sheet.addCell(new Label(0,row , (stt++) + "" , cellFormat2));
						sheet.addCell(new Label(x, row ++ , rsct.getString("smartId"), cellFormat2));
					}
				}
				
				/*sql =  	"\n with tuyen  as ( " +
						"\n 				select a.khachhang_fk,b.ngayId, max(sott)sott,max(tanso)tanso   " + 
						"\n 				from khachhang_tuyenbh a inner join tuyenbanhang b on a.tbh_fk= b.pk_Seq where b.ddkd_fk =   " + rs.getString("ddkdid") + 
						"\n 				group by a.khachhang_fk,b.ngayId  " + 

						"\n  					)  " +
						"\n select kh.pk_seq MaHeThong,isnull(kh.diachixhd,'')diachixhd,isnull(kh.SoNha,kh.DiaChi)SoNha " +
						"\n  ,case when ltrim(rtrim(kh.phuongxa)) = '' then '' else isnull((select ten from phuongxa where pk_seq = kh.phuongxa),'') end phuongxa,(select diengiai from thanhthinongthon where pk_Seq = kh.thanhthinongthon_fk) as thanhthinongthon_fk,kh.didong,kh.songayno,kh.sotienno,kh.con3,kh.ngsinh_con3,kh.nvdq,kh.sdtnvdq,kh.email" +
						"\n 	, STUFF   \n "+      
						"\n 	 (     \n "+      
						"\n 		(	select ';' + p.MaFAST   \n "+      
						"\n 			from NHAPHANPHOI p   \n "+      
						"\n 			where p.PK_SEQ in (select NPP_FK from KHACHHANG_NPP where KHACHHANG_FK = kh.PK_SEQ  and NPP_FK != kh.NPP_FK) \n "+      
						"\n 			ORDER BY ';' + p.MaFAST   \n "+      
						"\n 			FOR XML PATH('')   \n "+      
					  	"\n 		 ), 1, 1, ''   \n "+      
					  	"\n 	 ) as nppKhac, npp.MaFAST AS MANPP \n "+
					    "\n ,kh.MST_CaNhan,kh.TenKyHD,vt.vitri ,hch.hang as hangcuahang,lch.loai as loaicuahang,kh.phuongXa as Phuong,  'NA' as nhomcuahang, kh.tenduong, kh.apto, kh.didong, kh.masothue, kh.songayno, kh.sotienno, k.ten as kho, kh.long, kh.lat, kh.vochong, kh.Ngsinh_VoChong, kh.con1, kh.con2, kh.Ngsinh_Con1, kh.Ngsinh_Con2, kh.taitro, kh.ngaytaitro, kh.ghichu, \n"+
					    "\n kh.chucuahieu, isnull(tt.ten,'')tinhthanh_fk,isnull(qh.ten,'') quanhuyen_fk, kh.maFAST,kh.MaCu, isnull(kh.nguoimuahang,'NA')nguoimuahang, kh.diachigiaohang as diachigiaohang, kh.PT_CHIETKHAU as chietkhau, \n"+
					    "\n kh.ten as tenkh,kh.tencuahieu,kh.smartid,kh.diachi,kh.dienthoai,tbh.tanso,tbh.sott,kh.NgaySinh,kh.MaHD,kh.MaSoThue,kh.XuatKhau,KH.THANHTOAN,kbh.Ten as KenhBanHang,kh.ThanhToanQuy \n" +
						"\n 	, STUFF    "+      
						"\n 	 (      "+      
						"\n 		(	select ';' + cast( p.ngayId as varchar)    "+      
						"\n 			from tuyen p    "+      
						"\n 			where p.khachhang_fk = kh.pk_seq  "+      
						"\n 			ORDER BY ';' + cast( p.ngayId as varchar)    "+      
						"\n 			FOR XML PATH('')    "+      
					  	"\n 		 ), 1, 1, ''    "+      
					  	"\n 	 ) as ngayid " +
						"\n  from  khachhang kh  " +
						"\n	 inner join " +
						"\n	 (  select khachhang_fk, max(sott)sott,max(tanso)tanso" +
						"\n		from tuyen " +
						"\n		group by khachhang_fk " +
						"\n	 )tbh on kh.pk_seq = tbh.khachhang_fk 	" +
						"\n  inner join NHAPHANPHOI npp on npp.PK_SEQ = kh.NPP_FK " +
						"\n  left join kenhbanhang kbh on kbh.pk_Seq=kh.kbh_fk "+
						"\n	 left join nhomkhachhang_khachhang nkhkh on kh.pk_seq = nkhkh.kh_fk "+
						"\n  left join nhomkhachhang nkh on nkh.pk_seq = nkhkh.nkh_fk "+
						"\n  left join hangcuahang hch on hch.pk_seq=kh.hch_fk " +
						"\n  left join loaicuahang lch on lch.pk_seq=kh.lch_fk " +
						"\n  left join vitricuahang vt on vt.pk_seq=kh.vtch_fk " + 
						"\n  left join tinhthanh tt on tt.pk_seq = kh.tinhthanh_fk "+
						"\n   left join kho k on kh.kho_fk = k.pk_seq "+
						"\n  left join quanhuyen qh on qh.pk_seq = kh.quanhuyen_fk and kh.tinhthanh_fk = qh.tinhthanh_fk "+
						"\n  where kh.trangthai = '1'  " ;
				if(NppId.length() > 0)
					sql += " and kh.PK_SEQ IN ( SELECT KHACHHANG_FK FROM KHACHHANG_NPP WHERE NPP_FK = " + NppId + ")";
				sql += " order by ngayid, tbh.sott ";

				System.out.println(sql);

				ResultSet rs1 = db.get(sql);
				Label label;

				Number number;
				int j = 5;
				// set style to cell data
				WritableCellFormat cellFormat2 = new WritableCellFormat();
				WritableCellFormat c3 = new WritableCellFormat();

				cellFormat2.setBorder(Border.LEFT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.RIGHT, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.TOP, BorderLineStyle.THIN, Colour.BLACK);
				cellFormat2.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.BLACK);
				

					while (rs1.next())
					{
						cot = 0;
						label = new Label(cot++, j, rs1.getString("sott"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("MANPP"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("MaHeThong"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("maFAST"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("tenkh"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("tencuahieu"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("nguoimuahang"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("diachigiaohang"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("diachixhd"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("chietkhau"), cellFormat2);
						sheet.addCell(label);

						
						label = new Label(cot++, j, rs1.getString("loaicuahang"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("nhomcuahang"), cellFormat2);
						sheet.addCell(label);

						
						label = new Label(cot++, j, rs1.getString("hangcuahang"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("vitri"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("SoNha"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("tenduong"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("apto"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("phuongxa"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("quanhuyen_fk"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("tinhthanh_fk"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("thanhthinongthon_fk"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("dienthoai"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("didong"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("NgaySinh"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("MaHD"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("MaSoThue"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("songayno"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("sotienno"), cellFormat2);
						sheet.addCell(label);				
						
						label = new Label(cot++, j, rs1.getString("kenhbanhang"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("kho"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("ngayid"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("tanso"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("long"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("lat"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("vochong"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("ngsinh_vochong"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("con1"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("ngsinh_con1"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("con2"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("ngsinh_con2"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("con3"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("ngsinh_con3"), cellFormat2);
						sheet.addCell(label);					

						label = new Label(cot++, j, rs1.getString("ghichu"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("taitro"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("ngaytaitro"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("nvdq"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("sdtnvdq"), cellFormat2);
						sheet.addCell(label);

						label = new Label(cot++, j, rs1.getString("email"), cellFormat2);
						sheet.addCell(label);
						
						label = new Label(cot++, j, rs1.getString("nppkhac"), cellFormat2);
						sheet.addCell(label);
						
						j++;
					}*/

				k++;
			}

			if (!chkRs)
			{
				obj.setMessage("Không có dữ liệu");
			}
			
			w.write();
			w.close();

			if (out1 != null)
				out1.close();
		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.getMessage());
			e.printStackTrace();
		}
		
	}

	
	
	private void XuatTemplate(HttpServletResponse response, IKehoachPG  obj) 
	{
		try
		{
			response.setContentType("application/xls");
			response.setHeader("Content-Disposition", "attachment; filename=kehoachpg_templete.xls");
			OutputStream out1 =  response.getOutputStream();
			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
			String sql = "select npp.ten npp,ddkd.smartId, ddkd.pk_seq, ddkd.ten " +
				" FROM daidienkinhdoanh ddkd  " +
				" inner join daidienkinhdoanh_NPP x on x.ddkd_fk = ddkd.pk_seq" +
				" inner join nhaphanphoi npp on npp.pk_seq = x.npp_fk   " +
				" where ddkd.isPG = 1 and ddkd.TRANGTHAI = 1  and x.npp_fk = "+ obj.getNppId();	
			System.out.println("danh sach NVBH :" + sql);
			ResultSet rs = obj.getDb().get(sql);
			int k = 0;
			boolean chkRs = false;
			while (rs.next())
			{
				if( worksheets.size() <=k)
					workbook.getWorksheets().addSheet();
				
				chkRs = true;
				System.out.println("k = "+ k);
				Worksheet worksheet =   worksheets.getSheet(k);
				worksheet.setName(rs.getString("pk_seq"));
				com.aspose.cells.Cells cells = worksheet.getCells();

				cells.getCell(1,0).setValue( "NPP : ");
				cells.getCell(1,1).setValue(  "" +  rs.getString("npp"));
				cells.getCell(2,0).setValue( "PG : ");
				cells.getCell(2,1).setValue(  "" + rs.getString("ten") +" - " + rs.getString("smartId") );
				int cot = 0;
				com.aspose.cells.Cell cell =cells.getCell(4,cot++); cell.setValue("STT");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell =cells.getCell(4,cot++); cell.setValue("Chủ nhật");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell =cells.getCell(4,cot++); cell.setValue("Thứ 2");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell =cells.getCell(4,cot++); cell.setValue("Thứ 3");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell =cells.getCell(4,cot++); cell.setValue("Thứ 4");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				 cell =cells.getCell(4,cot++); cell.setValue("Thứ 5");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell =cells.getCell(4,cot++); cell.setValue("Thứ 6");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				cell =cells.getCell(4,cot++); cell.setValue("Thứ 7");
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
				
				int TEXT_STYLE = 49;
				for(int xx = 5; xx < 100; xx ++)
				{
					cot = 0;
					cell =cells.getCell(xx,cot++); cell.setValue("");
					Style _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
					cell.setStyle(_style);
					
					cell =cells.getCell(xx,cot++); cell.setValue("");
					 _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
					cell.setStyle(_style);
					
					cell =cells.getCell(xx,cot++); cell.setValue("");
					 _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
					cell.setStyle(_style);
					
					cell =cells.getCell(xx,cot++); cell.setValue("");
					 _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
						cell.setStyle(_style);
					
					 cell =cells.getCell(xx,cot++); cell.setValue("");
					 _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
						cell.setStyle(_style);
					
					cell =cells.getCell(xx,cot++); cell.setValue("");
					 _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
						cell.setStyle(_style);
					
					cell =cells.getCell(xx,cot++); cell.setValue("");
					 _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
						cell.setStyle(_style);
				}
				if(obj.getId() != null && obj.getId().trim().length() > 0)
				for(int x = 1; x <= 7; x++)
				{
					int row = 5;
					int stt = 1;
					sql = "\n select kh.mafast  "  +
							"\n from KeHoachPG_KhachHang_Ngay a" +
							"\n inner join khachhang kh on kh.pk_seq = a.khachhang_fk  " +
							"\n where a.kehoachPG_fk = "+obj.getId()+" and a.ddkd_fk = "+rs.getString("pk_seq")+"  and a.ngayId = " + x +
							"\n order by stt" ;
					ResultSet rsct = obj.getDb().get(sql);
					while(rsct.next())
					{
						cell =cells.getCell(row,0); cell.setValue((stt++) + "" );
						Style _style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
						cell.setStyle(_style);
						
						cell =cells.getCell(row++,x); cell.setValue( rsct.getString("mafast"));
						_style = cell.getStyle(); _style.setNumber(TEXT_STYLE);
						cell.setStyle(_style);
					}
				}
				k++;
			}
			if (!chkRs)
			{
				obj.setMessage("Không có dữ liệu");
			}
			workbook.save(out1);		
			if (out1 != null)
				out1.close();
		} catch (Exception e)
		{
			System.out.println("Error Cac Ban : " + e.getMessage());
			e.printStackTrace();
		}
	}
}
