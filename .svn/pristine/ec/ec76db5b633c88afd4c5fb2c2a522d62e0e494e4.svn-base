package geso.dms.center.servlets.bundle;

import geso.dms.center.beans.bundle.*;
import geso.dms.center.beans.bundle.imp.RaBundle;
import geso.dms.center.beans.bundle.imp.RaBundleList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class RaBundleSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	private static final long serialVersionUID = 1L;

	PrintWriter out;

	private int items = 50; 
	//String userId;
	//String nppId;

	private int splittings = 20;

	public RaBundleSvl() 
	{
		super();
	}

	private void settingPage(IRaBundleList obj) {
		Utility util = new Utility();
		if(getServletContext().getInitParameter("items") != null){
			String i = getServletContext().getInitParameter("items").trim();
			if(util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if(getServletContext().getInitParameter("splittings") != null){
			String i = getServletContext().getInitParameter("splittings").trim();
			if(util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			this.out  = response.getWriter();


			session.setMaxInactiveInterval(30000);

			Utility util = new Utility();
			out = response.getWriter();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);
			//out.println(userId);
			System.out.println();
			Date date = new Date();
			System.out.println("DonhangSvl user :" + userId + "  ,sessionId: " + session.getId() );

			if (userId.length()==0)
				userId = request.getParameter("userId");
			//String nppId;
			//if(request.getParameter("nppId") != null)
			//nppId = request.getParameter("nppId");


			//Lay Nha PP Theo Dang Nhap Moi
			//nppId = util.getIdNhapp(userId);

			String action = util.getAction(querystring);
			if(action == null)
				action = request.getParameter("action");
			System.out.println("ACTION LA: " + action);

			String dhId = util.getId(querystring);

			String msg = "";

			IRaBundleList obj; 
			obj = new RaBundleList();	
			obj.setUserId(userId);
			settingPage(obj);
			obj.init("");
			obj.setMsg(msg);
			session.setAttribute("obj", obj);
			session.setAttribute("khId", "");

			String nextJSP = request.getContextPath() + "/pages/Center/RaBundle.jsp";
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
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");


			session.setMaxInactiveInterval(30000);

			userId = request.getParameter("userId");
			System.out.println();
			System.out.println("RaBundleSvl user :" + userId + "  ,sessionId: " + session.getId()) ;
			String action = request.getParameter("action");
			if (action == null){
				action = "";
			}

			if (action.equals("new"))
			{
				IRaBundle dhBean = (IRaBundle) new RaBundle("");
				dhBean.setUserId(userId);
				dhBean.createRS();

				// Save Data into session
				session.setAttribute("dhBean", dhBean);//truyen vao session mot doi tuong donhang co gia tri rong khi khoi tao de ben form don hang nhan dc
				session.setAttribute("khId", "");
				session.setAttribute("ddkdId", "");
				session.setAttribute("ngaydonhang", "" );
				session.setAttribute("nppId", dhBean.getNppId());

				String nextJSP = request.getContextPath() + "/pages/Center/RaBundleNew.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				IRaBundleList obj;
				obj = new RaBundleList();
				settingPage(obj);

				obj.setUserId(userId);

				if(action.equals("search"))
				{
					obj.setUserId(userId);
					String search = getSearchQuery(request, obj);

					obj.init(search);
					session.setAttribute("userId", userId);
					session.setAttribute("obj", obj);

					response.sendRedirect(request.getContextPath() + "/pages/Center/RaBundle.jsp");	    		    	
				}
				else if(action.equals("view") || action.equals("next") || action.equals("prev"))
				{
					obj.setUserId(userId);
					String search = getSearchQuery(request, obj);

					obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

					obj.init(search);
					obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
					session.setAttribute("obj", obj);
					response.sendRedirect(request.getContextPath() + "/pages/Center/RaBundle.jsp");
				}
				else if(action.equals("delete"))
				{
					String dhId = request.getParameter("dhId");
					String msg = "";
					String lydoxoa = request.getParameter("lydoxoa");
					if(lydoxoa == null)
						lydoxoa = "";
					System.out.println("Ly do xoa: " + lydoxoa);
					msg = XoaChungTu(dhId,  lydoxoa);
					obj = new RaBundleList();
					obj.setUserId(userId);
					settingPage(obj);
					obj.init("");
					obj.setMsg(msg);

					session.setAttribute("obj", obj);
					session.setAttribute("khId", "");

					String nextJSP = request.getContextPath() + "/pages/Center/RaBundle.jsp";
					response.sendRedirect(nextJSP);	
				}
				else if(action.equals("duyet"))  //DUYET PHAI DUNG DOPOST THI MOI GIU LAI DUOC CAC DK LOC
				{
					String dhId = request.getParameter("dhId");
					String msg = "";
					msg = ChotChungTu(dhId);
					obj = new RaBundleList();
					obj.setUserId(userId);
					settingPage(obj);
					obj.init("");
					obj.setMsg(msg);
					session.setAttribute("obj", obj);
					session.setAttribute("khId", "");
					String nextJSP = request.getContextPath() + "/pages/Center/RaBundle.jsp";
					response.sendRedirect(nextJSP);		   
				}
				else if(action.equals("1")){

					obj.setUserId(userId);
					settingPage(obj);
					obj.init("");

					session.setAttribute("obj", obj);
					session.setAttribute("khId", "");		    				
					String nextJSP = request.getContextPath() + "/pages/Center/RaBundle.jsp";
					response.sendRedirect(nextJSP);	
				}
			}
		}
	}


	private  String XoaChungTu(String dhId, String lydoxoa) 
	{
		String msg = "";
		dbutils db = new dbutils();
		geso.dms.center.util.Utility util_kho=new geso.dms.center.util.Utility();
		String query  ="";
		try 
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);

			query =  " select dh.sanpham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,SUM(dhsp.soluong)soluong  " + 
			"\n from ERP_RABUNDLE_ChiTiet dhsp   " + 
			"\n inner join  ERP_RaBUNDLE dh on dh.PK_SEQ = dhsp.RABUNDLE_fk and dh.sanpham_fk = dhsp.sanpham_fk  " + 
			"\n where dh.trangthai = 0 and dhsp.raBUNDLE_fk =  " + dhId +
			"\n group by   dh.sanpham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK" ;
			ResultSet rssp=db.get(query);

			while (rssp.next()){
				String _khoid=rssp.getString("KHO_FK");
				String _nppid=rssp.getString("NPP_FK");
				String _kbhid=rssp.getString("KBH_FK");
				String _spid=rssp.getString("sanpham_fk");
				double _soluong=rssp.getDouble("soluong");
				String msg1=util_kho.Update_NPP_Kho_Sp("", "Xóa rã bundle revert  tổng", db, _khoid, _spid, _nppid ,_kbhid, 0, (-1)*_soluong, _soluong, 0);// giảm booked,avai cộng
				if(msg1.length() >0)
				{
					msg = msg1;
					db.getConnection().rollback();
					return msg; 
				}
			}
			rssp.close();


			query="\n SELECT  DH_CT.SANPHAM_FK , DH.KHO_FK ,   DH.NPP_FK ,  "+
			"\n DH.KBH_FK , DH_CT.SOLO ,DH_CT.NGAYHETHAN ,DH_CT.NGAYNHAPKHO ,DH_CT.SOLUONG "+
			"\n FROM  ERP_RABUNDLE_ChiTiet DH_CT INNER JOIN ERP_RaBUNDLE DH ON DH.PK_SEQ=DH_CT.RABUNDLE_fk "+ 
			"\n WHERE DH_CT.RABUNDLE_fk=" + dhId +"  AND DH.TRANGTHAI=0 ";
			System.out.println("Lay chi tiet don hang: "+query);
			rssp=db.get(query);

			while(rssp.next())
			{
				String _khoid=rssp.getString("KHO_FK");
				String _nppid=rssp.getString("NPP_FK");
				String _kbhid=rssp.getString("KBH_FK");
				String _spid=rssp.getString("SANPHAM_FK");
				String _solo=rssp.getString("SOLO");
				String _NGAYHETHAN=rssp.getString("NGAYHETHAN");
				String _ngaynhapkho=rssp.getString("NGAYNHAPKHO");

				double _soluong=rssp.getDouble("soluong");
				String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet("", "bundle revert chi tiet",  db, _khoid, _spid, _nppid, _kbhid, _solo, _NGAYHETHAN, _ngaynhapkho, 0, (-1)* _soluong, _soluong, _soluong, 0);

				if(msg1.length() >0)
				{
					msg = msg1;
					db.getConnection().rollback();
					return msg; 
				}
			}
			rssp.close();



			query = "update ERP_RABUNDLE set trangthai = 2 where pk_seq = '" + dhId + "' and TrangThai = 0 ";
			System.out.println("--CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Đơn hàng xóa rồi !";
				db.getConnection().rollback();
				return msg;
			}



			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			try 
			{
				db.getConnection().rollback();
			} 
			catch (Exception e1) { }
			e.printStackTrace();
			msg = "Lỗi khi xóa: " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		return msg;
	}



	private  String ChotChungTu(String dhId) 
	{
		String msg = "";
		dbutils db = new dbutils();
		geso.dms.center.util.Utility util_kho=new geso.dms.center.util.Utility();
		String query  ="";
		try 
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);

			query = "update ERP_RABUNDLE set trangthai = 1 where pk_seq = '" + dhId + "' and TrangThai = 0 ";
			System.out.println("--CAP NHAT TRANG THAI: " + query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Đơn hàng chốt rồi !";
				db.getConnection().rollback();
				return msg;
			}
			boolean datrukho = false;
			query =  "\n select dh.NgayChungTu,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.SanPham_fk ,SUM(dhsp.soluong)soluong  " + 
			"\n from ERP_RABUNDLE dh   " + 
			"\n inner join ERP_RABUNDLE_ChiTiet dhsp on dh.PK_SEQ = dhsp.RABUNDLE_fk  " + 
			"\n where  dh.PK_SEQ = " + dhId + 
			"\n group by dh.NgayChungTu,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.SanPham_fk  " ;
			ResultSet rs = db.get(query);
			while ( rs.next())
			{
				String NgayChungTu = rs.getString("NgayChungTu");
				String khoId = rs.getString("KHO_FK");
				String spId = rs.getString("SanPham_fk");
				String nppId = rs.getString("NPP_FK");
				String kbhId = rs.getString("KBH_FK");
				double soluong = rs.getDouble("soluong");
				String msg1=util_kho.Update_NPP_Kho_Sp(NgayChungTu, "Chốt rã bundle RaBundleSvl 393 ", db, khoId, spId, nppId, kbhId,  soluong*(-1), soluong*(-1), 0, 0);
				if(msg1.length() >0){
					msg = msg1;
					db.getConnection().rollback();
					return msg;
				}


				query =  "\n select dh.NgayChungTu,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.SanPham_fk,dhsp.SOLO,dhsp.NgayHetHan,dhsp.ngaynhapkho ,dhsp.soluong  " + 
				"\n from ERP_RABUNDLE dh   " + 
				"\n inner join ERP_RABUNDLE_ChiTiet dhsp on dh.PK_SEQ = dhsp.RABUNDLE_fk  " + 
				"\n where  dh.PK_SEQ = " + dhId + " and dhsp.SanPham_fk =  " + spId;
				double tongluongxuatCT=0;
				ResultSet rssp = db.get(query);
				while (rssp.next())
				{
					String _spid=spId;
					String _KHO_FK=khoId;
					String _KBH_FK=kbhId;
					String _NPP_FK=nppId;
					String _SOLO=rssp.getString("SOLO");
					String _NGAYNHAPKHO=rssp.getString("ngaynhapkho");
					String _NGAYHETHAN=rssp.getString("NgayHetHan");
					double soluongct=rssp.getDouble("soluong");

					msg1 =util_kho.Update_NPP_Kho_Sp_Chitiet(NgayChungTu, "Chốt rã bundle RaBundleSvl 423 ",   db, _KHO_FK, _spid, _NPP_FK, _KBH_FK, _SOLO, _NGAYHETHAN, _NGAYNHAPKHO,
							(-1)*soluongct, (-1)*soluongct, 0, 0, 0);

					if(msg1.length()>0){
						msg = msg1;
						db.getConnection().rollback();
						return msg;
					}
					tongluongxuatCT+=soluongct;
					datrukho = true;
				}
				rssp.close();
				if(tongluongxuatCT != soluong)
				{
					msg = "437.RaBundleSvl.Lỗi hệ thống ( SỐ LƯỢNG LÔ TỔNG VÀ LÔ CHI TIẾT KHÔNG KHỚP ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
					db.getConnection().rollback();
					return msg;

				}
			}
			if(!datrukho)
			{
				msg = "Chưa trừ kho  vui lòng liện hệ admin để xử lý!";
				db.getConnection().rollback();
				return msg;

			}
			//// tăng kho nguyên liệu
			boolean datangkho = false;
			query =  "\n select dh.NgayChungTu,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.SanPham_fk ,SUM(dhsp.soluong)soluong  " + 
			"\n from ERP_RABUNDLE dh   " + 
			"\n inner join ERP_RABUNDLE_sanpham dhsp on dh.PK_SEQ = dhsp.RABUNDLE_fk  " + 
			"\n where  dh.PK_SEQ = " + dhId + 
			"\n group by dh.NgayChungTu,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.SanPham_fk  " ;
			rs = db.get(query);
			while ( rs.next())
			{
				String NgayChungTu = rs.getString("NgayChungTu");
				String khoId = rs.getString("KHO_FK");
				String spId = rs.getString("SanPham_fk");
				String nppId = rs.getString("NPP_FK");
				String kbhId = rs.getString("KBH_FK");
				double soluong = rs.getDouble("soluong");
				String msg1=util_kho.Update_NPP_Kho_Sp(NgayChungTu, "Chốt rã bundle RaBundleSvl 466 tang kho "
						, db, khoId, spId, nppId, kbhId,  soluong, 0, soluong, 0);
				if(msg1.length() >0){
					msg = msg1;
					db.getConnection().rollback();
					return msg;
				}


				query =  "\n select dh.NgayChungTu,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.SanPham_fk,dhsp.SOLO,dhsp.NgayHetHan,dhsp.ngaynhapkho ,dhsp.soluong  " + 
				"\n from ERP_RABUNDLE dh   " + 
				"\n inner join ERP_RABUNDLE_sanpham dhsp on dh.PK_SEQ = dhsp.RABUNDLE_fk  " + 
				"\n where  dh.PK_SEQ = " + dhId + " and dhsp.SanPham_fk =  " + spId;
				double tongluongxuatCT=0;
				ResultSet rssp = db.get(query);
				while (rssp.next())
				{
					String _spid=spId;
					String _KHO_FK=khoId;
					String _KBH_FK=kbhId;
					String _NPP_FK=nppId;
					String _SOLO=rssp.getString("SOLO");
					String _NGAYNHAPKHO=NgayChungTu;
					String _NGAYHETHAN=rssp.getString("NgayHetHan");
					double soluongct=rssp.getDouble("soluong");

					msg1 =util_kho.Update_NPP_Kho_Sp_Chitiet(NgayChungTu, "Chốt rã bundle RaBundleSvl 492 tang kho ",   db, _KHO_FK, _spid, _NPP_FK, _KBH_FK, _SOLO, _NGAYHETHAN, _NGAYNHAPKHO,
							soluongct, 0, soluongct, 0, 0);

					if(msg1.length()>0){
						msg = msg1;
						System.out.println("loi insert Update_NPP_Kho_Sp_Chitiet" + msg);
						db.getConnection().rollback();
						return msg;
					}
					tongluongxuatCT+=soluongct;
					datangkho = true;
				}
				rssp.close();
				if(tongluongxuatCT != soluong)
				{
					msg = "437.RaBundleSvl.Lỗi hệ thống ( SỐ LƯỢNG LÔ TỔNG VÀ LÔ CHI TIẾT KHÔNG KHỚP ). Vui lòng liên hệ trung tâm để được hỗ trợ xử lý.";
					db.getConnection().rollback();
					return msg;

				}
				datangkho = true;
			}
			if(!datangkho)
			{
				msg = "Chưa tăng kho Bundle vui lòng liện hệ admin để xử lý!";
				db.getConnection().rollback();
				return msg;
			}
			
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			msg = "Chốt thành công!";
			return msg;
		} 
		catch (Exception e) 
		{
			try 
			{
				db.getConnection().rollback();
			} 
			catch (Exception e1) { }
			e.printStackTrace();
			msg = "Lỗi khi xóa: " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		return msg;
	}

	private String getSearchQuery(HttpServletRequest request,IRaBundleList obj) 
	{
		Utility util = new Utility();



		String nppId = request.getParameter("nppId");
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String trangthai = request.getParameter("trangthai");
		if (trangthai == null)
			trangthai = "";    	
		obj.setTrangthai(trangthai);
		System.out.println(" ---- TRANG THAI LA: " + trangthai);

		String tungay = request.getParameter("tungay");
		if (tungay == null)
			tungay = "";    	
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if (denngay == null)
			denngay = "";    	
		obj.setDenngay(denngay);



		String mafast = request.getParameter("mafast");
		if(mafast==null)
			mafast="";
		obj.setMafast(mafast);

		String query =  "\n select a.pk_seq, a.ngaychungtu, d.ten as spTen, ( select sum(SOluong) from erp_rabundle_chitiet where rabundle_fk = a.pk_seq )soluong, a.trangthai, b.ten as nguoitao, a.ngaytao, c.ten as nguoisua, a.ngaysua    " +
		"\n from Erp_RABUNDLE a inner join NhanVien b on a.nguoitao = b.pk_seq    " +
		"\n inner join nhanvien c on a.nguoisua = c.pk_seq inner join SanPham d on a.sanpham_fk = d.pk_seq " ;

		if(nppId.length() > 0)
			query += " and a.NPP_FK= '" + nppId + "'";



		if (trangthai.length() > 0)
		{
			query += " and a.trangthai = '" + trangthai + "'";
		}
		else
			query += " and a.trangthai != '2' ";

		if (tungay.length() > 0)
		{
			query = query + " and a.ngaythuchien >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and a.ngaythuchien <= '" + denngay + "'";	
		}

		if (mafast.length() > 0)
		{
			query = query + " and d.pk_seq = " + mafast + " ";	
		}


		System.out.println("Query cua ban: " + query);
		return query;
	}


	public String getFormatDate(String date) 
	{
		String[] arr = date.split("-");

		return arr[2] + "/" + arr[1] + "/" + arr[0];
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}





}

