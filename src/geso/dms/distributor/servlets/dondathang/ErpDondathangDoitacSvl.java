package geso.dms.distributor.servlets.dondathang;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.dondathang.IErpDondathangDoitac;
import geso.dms.distributor.beans.dondathang.IErpDondathangDoitacList;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangDoitac;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangDoitacList;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpDondathangDoitacSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public ErpDondathangDoitacSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDondathangDoitacList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();	    
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);

		String locgiaQUYDOI = request.getParameter("locgiaQUYDOI");
		if(locgiaQUYDOI == null)
			locgiaQUYDOI = "0";


		System.out.println("ACTION LA: " + action );
		if(locgiaQUYDOI.equals("1"))
		{
			String spMa = request.getParameter("spMa");
			if(spMa == null)
				spMa = "";

			String dvt = request.getParameter("dvt");
			if(dvt == null)
				dvt = "";

			String ngaydh = request.getParameter("ngaydh");
			if(ngaydh == null)
				ngaydh = "";

			String dvkdId = "";
			if(session.getAttribute("dvkdId") != null )
				dvkdId = (String) session.getAttribute("dvkdId");

			String kbhId = "";
			if(session.getAttribute("kbhId") != null )
				kbhId = (String) session.getAttribute("kbhId");

			String nppId = "";
			if(session.getAttribute("nppId") != null )
				nppId = (String) session.getAttribute("nppId");

			String doitacId = "";
			if(session.getAttribute("doitacId") != null )
				doitacId = (String) session.getAttribute("doitacId");

			String query = (String)request.getQueryString();
			spMa = new String(query.substring(query.indexOf("&spMa=") + 6, query.indexOf("&dvt=")).getBytes("UTF-8"), "UTF-8");
			spMa = URLDecoder.decode(spMa, "UTF-8").replace("+", " ");

			dvt = new String(query.substring(query.indexOf("&dvt=") + 5, query.indexOf("&locgiaQUYDOI")).getBytes("UTF-8"), "UTF-8");
			dvt = URLDecoder.decode(dvt, "UTF-8").replace("+", " ");

			ngaydh = new String(query.substring(query.indexOf("&locgiaQUYDOI=") + 9, query.indexOf("&ngaydh")).getBytes("UTF-8"), "UTF-8");
			ngaydh = URLDecoder.decode(ngaydh, "UTF-8").replace("+", " ");


			//System.out.println(" -- MA SP: " + spMa + " -- DVT: " + dvt );
			//spMa = URLDecoder.decode(spMa, "UTF-8").replace("+", " ");
			//dvt = URLDecoder.decode(dvt, "UTF-8").replace("+", " ");

			if(spMa.trim().length() > 0 && dvt.trim().length() > 0 )
			{
				dbutils db = new dbutils();


				String command = "select a.DVDL_FK as dvCHUAN, ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvt + "' ) as dvNEW,      " +
				"	isnull( ( select SOLUONG1 / SOLUONG2 from QUYCACH where SANPHAM_FK = a.pk_seq and DVDL1_FK = a.DVDL_FK and DVDL2_FK = ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvt + "' ) ), 0 ) as quydoi,  	  " +
				"	dbo.[GiaDoitacSanpham](100001,"+kbhId+","+nppId+",a.pk_seq,0,'"+ ngaydh +"') as giamua   " +
				"from SANPHAM a where a.MA = '" + spMa + "'  ";

				System.out.println("Lay don gia san pham: " + command);
				String kq  = "0";
				String quycach = "0";
				ResultSet rs = db.get(command);
				try
				{
					if(rs.next())
					{
						String dvCHUAN = rs.getString("dvCHUAN");
						String dvNEW = rs.getString("dvNEW");
						double quydoi = rs.getDouble("quydoi");
						double dongia = rs.getDouble("giamua");
						quycach = Double.toString(quydoi);
						//System.out.println("DON VI NEW: " + dvNEW);
						if(dvCHUAN.equals(dvNEW))
							kq = Double.toString(dongia);
						else
							kq = Double.toString(dongia * quydoi );


					}
					rs.close();
				} 
				catch (Exception e)
				{
					kq = "0_0";
				}

				db.shutDown();
				String res = kq + "_" + quycach;
				System.out.println("GIA: " + res);
				out.write(res);
			}
			else
			{
				out.write("0_0");
			}

			return;
		}
		else
		{
			String lsxId = util.getId(querystring);
			obj = new ErpDondathangDoitacList();

			String loaidonhang = request.getParameter("loaidonhang");
			if(loaidonhang == null)
				loaidonhang = "0";
			obj.setLoaidonhang(loaidonhang);
			System.out.println("---LOAI DON HANG: " + loaidonhang);


			obj.setUserId(userId);

			if (action.equals("delete") )
			{	
				String msg = this.DeleteChuyenKho(lsxId);
				obj.setMsg(msg);
			}
			else if(action.equals("chot"))
			{
				String msg = this.Chot(lsxId, userId);
				obj.setMsg(msg);
			}
			else if(action.equals("unchot"))
			{
				String msg = this.UnChot(lsxId, userId);
				obj.setMsg(msg);
			}

			obj.setUserId(userId);
			obj.init("");

			session.setAttribute("obj", obj);

			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTac.jsp";
			response.sendRedirect(nextJSP);
		}
	}

	public static String Nha_book_erp_dondathangnpp(Idbutils db, String lsxId, String action ) throws SQLException
	{
		String query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+lsxId+" )";
		ResultSet rs = db.get(query);
		boolean dungchungkenh=false;
		if(rs.next())
		{
			if(rs.getString("dungchungkenh").equals("1")){
				dungchungkenh=true;
			}
		}
		rs.close();
		
		Utility uilt_kho=new Utility();
		//GIAM BOOK, TANG AVAI
		query=	"	select khoxuat_fk, npp_fk, "+(dungchungkenh?"100025":" kbh_fk")+ " as kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
		"	from " +
		"	( " +
		"		select c.kho_fk as khoxuat_fk, c.npp_fk,  isnull( a.kbh_fk, c.kbh_fk)  kbh_fk, a.sanpham_fk,       " +
		"				case when a.dvdl_fk IS null then a.soluong       " +
		"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
		"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  end as soluong    " +
		"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
		"				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq     " +
		"		where a.dondathang_fk in (  " + lsxId + "  ) and a.soluong > 0 " +
		"	) " +
		"	DATHANG  " +
		"	group by khoxuat_fk, npp_fk, "+(dungchungkenh?"":" kbh_fk,")+ "  sanpham_fk " ;

		ResultSet rskho=db.get(query);
		while(rskho.next()){
			String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk ;
			_khoxuat_fk=rskho.getString("khoxuat_fk");
			_npp_fk=rskho.getString("npp_fk");
			_kbh_fk=rskho.getString("kbh_fk");
			_sanpham_fk=rskho.getString("sanpham_fk"); 
			double soluongct_ =rskho.getDouble("soluong");
			String msg1=uilt_kho.Update_NPP_Kho_Sp(Utility.getNgayHienTai(), action + "erpdondathangDoitacSvl 340",
					db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, 0, soluongct_*(-1), soluongct_, 0);
			if(msg1.length() >0){			
				return msg1;
			}

		}
		rskho.close();
		
		
		// xóa km
		
		query=	"	select khoxuat_fk, npp_fk, "+(dungchungkenh?"100025":" kbh_fk")+ " as kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
		"	from " +
		"	( " +
		"		select a.kho_fk as khoxuat_fk, c.npp_fk,   a.kbh_fk, a.sanpham_fk,  a.soluong    " +
		"		from ERP_DONDATHANGNPP_ctkm_trakm a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
		"				inner join ERP_DONDATHANGNPP c on a.dondathangId = c.pk_seq     " +
		"		where a.dondathangId in (  " + lsxId + "  ) and a.soluong > 0 " +
		"	) " +
		"	DATHANG  " +
		"	group by khoxuat_fk, npp_fk, "+(dungchungkenh?"":" kbh_fk,")+ "  sanpham_fk " ;
		System.out.println("qiery = "+ query);
		rskho=db.get(query);
		while(rskho.next()){
			String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk ;
			_khoxuat_fk=rskho.getString("khoxuat_fk");
			_npp_fk=rskho.getString("npp_fk");
			_kbh_fk=rskho.getString("kbh_fk");
			_sanpham_fk=rskho.getString("sanpham_fk"); 
			double soluongct_ =rskho.getDouble("soluong");
			String msg1=uilt_kho.Update_NPP_Kho_Sp(Utility.getNgayHienTai(), action +  "erpdondathangDoitacSvl KM ",
					db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, 0, soluongct_*(-1), soluongct_, 0);
			if(msg1.length() >0){
				return msg1;
			}

		}
		rskho.close();

		// xoa bang chi tiet


		query=	" SELECT KHOXUAT_FK, NPP_FK,   "+(dungchungkenh?"100025":" kbh_fk")+ " as  KBH_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG   ,SOLO, NGAYHETHAN, NGAYNHAPKHO "+      
		" FROM "+
		" ( "+
		" SELECT C.KHO_FK AS KHOXUAT_FK, C.NPP_FK,   a.KBH_FK, A.SANPHAM_FK, A.SOLO,A.NGAYHETHAN,A.NGAYNHAPKHO, "+     
		" CASE WHEN A.DVDL_FK IS NULL THEN A.SOLUONG       "+
		" WHEN A.DVDL_FK = B.DVDL_FK THEN A.SOLUONG      "+
		" ELSE  A.SOLUONG * ( SELECT SOLUONG1 / SOLUONG2 FROM QUYCACH "+ 
		" WHERE SANPHAM_FK = A.SANPHAM_FK AND DVDL2_FK = A.DVDL_FK AND DVDL1_FK = B.DVDL_FK )  END AS SOLUONG "+   
		" FROM ERP_DONDATHANGNPP_SANPHAM_CHITIET A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ   "+
		" INNER JOIN ERP_DONDATHANGNPP C ON A.DONDATHANG_FK = C.PK_SEQ     "+
		" WHERE A.DONDATHANG_FK IN (  "+lsxId+" ) AND A.SOLUONG > 0 "+
		" ) "+
		" DATHANG "+ 
		" GROUP BY KHOXUAT_FK, NPP_FK, "+(dungchungkenh?"":" kbh_fk,")+ " SANPHAM_FK ,SOLO, NGAYHETHAN, NGAYNHAPKHO    ";

		rskho=db.get(query);
		while(rskho.next()){
			String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk,_solo,_ngayhethan,_ngaynhapkho ;
			_khoxuat_fk=rskho.getString("khoxuat_fk");
			_npp_fk=rskho.getString("npp_fk");
			_kbh_fk=rskho.getString("kbh_fk");
			_sanpham_fk=rskho.getString("sanpham_fk"); 
			_solo= rskho.getString("SOLO");
			_ngayhethan= rskho.getString("ngayhethan");
			_ngaynhapkho= rskho.getString("ngaynhapkho");

			double soluongct_ =rskho.getDouble("SOLUONG");

			String msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet("", action + " :erpdondathangDoitacSvl 372" 
					, db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, (-1)*soluongct_, soluongct_, soluongct_, 0);
			if(msg1.length() >0){
				return msg1;
			}


		}
		rskho.close();

		
		query=	" SELECT KHOXUAT_FK, NPP_FK,   "+(dungchungkenh?"100025":" kbh_fk")+ " as  KBH_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG   ,SOLO, NGAYHETHAN, NGAYNHAPKHO "+      
		" FROM "+
		" ( "+
		" 	SELECT a.KHO_FK AS KHOXUAT_FK, C.NPP_FK,   a.KBH_FK, A.SANPHAM_FK, A.SOLO,A.NGAYHETHAN,A.NGAYNHAPKHO,a.SOLUONG "+   
		" 	FROM ERP_DONDATHANGNPP_ctkm_trakm_CHITIET A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ   "+
		" 	INNER JOIN ERP_DONDATHANGNPP C ON A.dondathangId = C.PK_SEQ     "+
		" 	WHERE a.dondathangId IN (  "+lsxId+" ) AND A.SOLUONG > 0 "+
		" ) "+
		" DATHANG "+ 
		" GROUP BY KHOXUAT_FK, NPP_FK, "+(dungchungkenh?"":" kbh_fk,")+ " SANPHAM_FK ,SOLO, NGAYHETHAN, NGAYNHAPKHO    ";

		rskho=db.get(query);
		while(rskho.next()){
			String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk,_solo,_ngayhethan,_ngaynhapkho ;
			_khoxuat_fk=rskho.getString("khoxuat_fk");
			_npp_fk=rskho.getString("npp_fk");
			_kbh_fk=rskho.getString("kbh_fk");
			_sanpham_fk=rskho.getString("sanpham_fk"); 
			_solo= rskho.getString("SOLO");
			_ngayhethan= rskho.getString("ngayhethan");
			_ngaynhapkho= rskho.getString("ngaynhapkho");

			double soluongct_ =rskho.getDouble("SOLUONG");

			String msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet("",action + " erpdondathangDoitacSvl 372" 
					, db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, (-1)*soluongct_, soluongct_, soluongct_, 0);
			if(msg1.length() >0){
				return msg1;
			}


		}
		rskho.close();
		
		query = "delete ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '" + lsxId + "' ";
		if(db.updateReturnInt(query)<=0)
		{

			return "Lỗi khi unchot: " + query;
		}
		query = "delete ERP_DONDATHANGNPP_ctkm_trakm_CHITIET where dondathangId = '" + lsxId + "' ";
		if(db.updateReturnInt(query)<0)
		{

			return "Lỗi khi unchot: " + query;
		}
		return "";
	}
	
	private String UnChot(String lsxId, String userId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			String query = "update ERP_DondathangNPP set trangthai = '0', NPP_DACHOT = '0' where trangthai = 1 and pk_seq = '" + lsxId + "'";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Lỗi trạng thái ERP_DondathangNPP : " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			msg = Nha_book_erp_dondathangnpp(db, lsxId, "Mở chốt erp_dondathangnpp ");
			if(msg.length() > 0)
			{				
				Utility.rollback_and_shutdown(db);
				return msg;
			}
			Utility.commit_and_shutdown(db);
			return " Mở chốt thành công! ";
		}
		catch (Exception e) 
		{
			Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}

		
	}

	private String Chot(String lsxId, String userId) 
	{
		geso.dms.distributor.db.sql.dbutils db=new geso.dms.distributor.db.sql.dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
			geso.dms.center.util.Utility utilC = new geso.dms.center.util.Utility();
			String kbh_fk="";
			String kho_fk="";
			String ngayhoadon="";
			String npp_fk="";
			String quanlykho = "";
			String ngaydonhang = "";
			String sqlck=	"\n select a.ngaydonhang,a.NPP_FK,a.KBH_FK,a.KHO_FK, (select quanlykho from nhaphanphoi where pk_seq = a.npp_fk)quanlykho " +
							"\n from ERP_DONDATHANGNPP a  where a.PK_SEQ="+lsxId+" ";
			System.out.println(" chot ban doi tac :"+ sqlck);
			ResultSet rsck=db.get(sqlck);
			if(rsck.next())
			{
				kbh_fk=rsck.getString("kbh_fk");
				kho_fk=rsck.getString("KHO_FK");
				ngaydonhang=rsck.getString("ngaydonhang");
				npp_fk=rsck.getString("NPP_FK");
				quanlykho = rsck.getString("quanlykho");

			}
			
			String ngayhoadon_ = utilC.getngayhoadon(userId, db,ngaydonhang,"NULL",0);
			
			rsck.close();
			String query = "update ERP_DondathangNPP set trangthai = '1', NPP_DACHOT = '1' , ngaygiochot='"+ getDateTime1() +"'  " +
					"where trangthai =0 and  pk_seq = '" + lsxId + "'";
			System.out.println("query: "+query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}


			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ = '" + npp_fk + "' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1"))
					kbh_fk = "100025";
			}
			System.out.println("Outside: "+quanlykho);
			//
			//CHECK BOOKED THEO DV CHUAN
			if(quanlykho.equals("1")) {
				System.out.println("Vao book");
					query =  "\n select sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
					"\n	ISNULL( ( select sum(AVAILABLE) from NHAPP_KHO_ChiTIet where kho_fk = dathang.khoxuat_fk and NGAYNHAPKHO<='"+ngayhoadon_+"'  and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
					"\n from     " +
					"\n (     " +
					"\n	select c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk,a.DVDL_FK as dvCHUAN,     " +
					"\n			case when a.dvdl_fk IS null then a.soluong      " +
					"\n				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
					"\n				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
					"\n								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
					"\n	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
					"\n			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
					"\n	where a.dondathang_fk in ( " + lsxId + " )     " +
					"\n )     " +
					"\n dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
					"\n group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN ";
					rs = db.get(query);
		
					Utility uilt_kho=new Utility();
					while(rs.next())
					{
						String khoID = rs.getString("kho_fk");
						String kbhID = rs.getString("kbh_fk");
						String nppID = rs.getString("npp_fk");
						String spID = rs.getString("PK_SEQ");
						String dvCHUAN=rs.getString("dvCHUAN");
						String DVDL_FK=rs.getString("DVDL_FK");
						double soluong = rs.getDouble("soluongXUAT");
						double tonkho = rs.getDouble("tonkho");
		
						String spten=rs.getString("ten");
		
						if(soluong > tonkho)
						{
							msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) không đủ tồn kho ( " + rs.getString("tonkho") + " ). Vui lòng kiểm tra lại.";
							db.getConnection().rollback();
							rs.close();
							return msg;
						}
		
						String sqldh="select convert(char(10),Created_Date,126) NGAYGIO_TAO from ERP_DONDATHANGNPP where pk_Seq="+lsxId;
						String ngaytaodh="";
						ResultSet rsdh=db.get(sqldh);
						while (rsdh.next())
						{
							ngaytaodh=rsdh.getString("NGAYGIO_TAO");
						}
						rsdh.close();
		
		
						String msg1=uilt_kho.Update_NPP_Kho_Sp(ngayhoadon_, "ErpDondathangDoitacSvl.java 414 " 
								, db, khoID, spID, nppID, kbhID, 0, soluong, (-1)* soluong, 0);
						if(msg1.length() >0){
							msg =msg1;
							db.getConnection().rollback();
							rs.close();
							return msg;
						}
		
						// đề xuất lô để booked ngay 
		
						query=		"\n select KHO_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,available  from NHAPP_KHO_CHITIET "+  
									"\n where NPP_FK ="+nppID+" and KBH_FK= " +kbhID +
									"\n and KHO_FK="+khoID+"  and SANPHAM_FK =  "+ spID +
									"\n AND AVAILABLE >0  and NGAYNHAPKHO<='"+ngayhoadon_+"'"+
									"\n order by NGAYHETHAN ,NGAYNHAPKHO,AVAILABLE ";
						ResultSet rssp=db.get(query);
						double soluongdenghi=soluong ;
		
						while(rssp.next() && soluongdenghi >0)
						{
							double soluong_avai= rssp.getDouble("AVAILABLE");
							double soluongcapnhat=0;
							if(soluong_avai >soluongdenghi){
								soluongcapnhat= soluongdenghi;
								soluongdenghi=0;
							}else{
								soluongcapnhat =soluong_avai;
								soluongdenghi =soluongdenghi - soluong_avai;
							}
							String solo=rssp.getString("SOLO");
							String ngaynhapkho=rssp.getString("ngaynhapkho");
							String ngayhethan=rssp.getString("ngayhethan");
							String _khoid=rssp.getString("kho_fk");
							String _kbhid=rssp.getString("KBH_FK");
							// cập nhật vào bảng đơn hàng sp _chitiet
							double soluongcapnhat_quydoi ;
							if(dvCHUAN.equals(DVDL_FK)){
								// nếu là đơn vị giống nhau
								soluongcapnhat_quydoi= soluongcapnhat;
		
							}else{
								query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spID+" AND DVDL1_FK="+DVDL_FK+"  and qc.DVDL2_FK="+dvCHUAN;
								ResultSet rs1=db.get(query);
								if(rs1.next()){
									soluongcapnhat_quydoi = soluongcapnhat * rs1.getDouble("SOLUONG2")/ rs1.getDouble("SOLUONG1");
		
								}else{
									msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
									db.getConnection().rollback();
									return msg;
								}
							}
		
							soluongcapnhat =soluongcapnhat;

							query = " Insert into ERP_DONDATHANGNPP_SANPHAM_CHITIET(kho_fk,kbh_fk,donDAThang_fk ,   sanpham_fk,solo,ngaynhapkho,ngayhethan, soluong,dvdl_fk) "
								+ " values('" + _khoid + "','" + _kbhid + "','" + lsxId + "', '" +spID + "','" +solo+ "','"+ngaynhapkho+"','"+ngayhethan+"',round("+soluongcapnhat_quydoi+",1) ,"+dvCHUAN+" )";
		
							if (!db.update(query)) 
							{
								msg="Không thể cập nhật : "+query;
								db.getConnection().rollback();
								rs.close();
								return msg;
							}
		
		
							msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "ErpDondathangDoitacSvl.java 489  DHID: "+lsxId,  db, _khoid, spID, npp_fk, _kbhid, solo, ngayhethan, ngaynhapkho, 0,soluongcapnhat,(-1)* soluongcapnhat, (-1)* soluongcapnhat, 0);
							if (msg1.length()> 0)
							{
								msg=msg1;
								db.getConnection().rollback();
								rs.close();
								return msg;
							}
						}
						rssp.close();
		
						if(soluongdenghi!=0){
		
		
							msg=  "Số lượng đề xuất trong lô chi tiết của sản phẩm "+spten+"   tới ngày (ngày cấu hình hóa đơn)"+ngayhoadon_+" không còn đủ, " +
							" vui lòng kiểm tra báo cáo ( xuất nhập tồn,tồn hiện tại) theo lô để biết thêm chi tiết ";
	
							db.getConnection().rollback();
							rs.close();
							return msg;
						}
					}
					rs.close(); 
					
					
					/// KM ahihi
					
					query =  "\n		select b.ten,a.spma,a.soxuat,a.ctkmID,a.trakmId,a.kho_fk , c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.soluong   " +							
							"\n		from ERP_DONDATHANGNPP_CTKM_TRAKM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
							"\n			inner join ERP_DONDATHANGNPP c on a.dondathangId = c.pk_seq " +
							"\n		where a.dondathangID in ( " + lsxId + " ) and a.sanpham_fk is not null    " ;
					rs = db.get(query);
		
					
					while(rs.next())
					{
						String spma = rs.getString("spma");
						String soxuat = rs.getString("soxuat");
						String trakmId = rs.getString("trakmId");
						String ctkmID = rs.getString("ctkmID");
						String khoID = rs.getString("kho_fk");
						String kbhID = rs.getString("kbh_fk");
						String nppID = rs.getString("npp_fk");
						String spID = rs.getString("sanpham_fk");
						double soluong = rs.getDouble("soluong");

		
						String spten=rs.getString("ten");
		
						
		
						String sqldh="select convert(char(10),Created_Date,126) NGAYGIO_TAO from ERP_DONDATHANGNPP where pk_Seq="+lsxId;
						String ngaytaodh="";
						ResultSet rsdh=db.get(sqldh);
						while (rsdh.next())
						{
							ngaytaodh=rsdh.getString("NGAYGIO_TAO");
						}
						rsdh.close();
		
		
						String msg1=uilt_kho.Update_NPP_Kho_Sp(ngayhoadon_, "ErpDondathangDoitacSvl.java KM " 
								, db, khoID, spID, nppID, kbhID, 0, soluong, (-1)* soluong, 0);
						if(msg1.length() >0){
							msg =msg1;
							db.getConnection().rollback();
							rs.close();
							return msg;
						}
		
						// đề xuất lô để booked ngay 
		
						query=		"\n select KHO_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,available  from NHAPP_KHO_CHITIET "+  
									"\n where NPP_FK ="+nppID+" and KBH_FK= " +kbhID +
									"\n and KHO_FK="+khoID+"  and SANPHAM_FK =  "+ spID +
									"\n AND AVAILABLE >0  and NGAYNHAPKHO<='"+ngayhoadon_+"'"+
									"\n order by NGAYHETHAN ,NGAYNHAPKHO,AVAILABLE ";
						ResultSet rssp=db.get(query);
						double soluongdenghi=soluong ;
		
						while(rssp.next() && soluongdenghi >0)
						{
							double soluong_avai= rssp.getDouble("AVAILABLE");
							double soluongcapnhat=0;
							if(soluong_avai >soluongdenghi){
								soluongcapnhat= soluongdenghi;
								soluongdenghi=0;
							}else{
								soluongcapnhat =soluong_avai;
								soluongdenghi =soluongdenghi - soluong_avai;
							}
							String solo=rssp.getString("SOLO");
							String ngaynhapkho=rssp.getString("ngaynhapkho");
							String ngayhethan=rssp.getString("ngayhethan");
							String _khoid=rssp.getString("kho_fk");
							String _kbhid=rssp.getString("KBH_FK");
							// cập nhật vào bảng đơn hàng sp _chitiet
							

							query = " Insert into ERP_DONDATHANGNPP_CTKM_TRAKM_CHITIET(DONDATHANGID, CTKMID, TRAKMID, SOXUAT, TONGGIATRI, SPMA, sanpham_fk, SOLUONG, CHIETKHAU, SOLO, NGAYHETHAN, NGAYNHAPKHO, KHO_FK, KBH_FK) "
								+ " values('" + lsxId + "', '" +ctkmID + "', '" +trakmId + "', '" +soxuat + "',0 , '" +spma + "', '" +spID + "', round("+soluongcapnhat+",1),0,'" +solo+ "','"+ngayhethan+"','"+ngaynhapkho+"',"+_khoid+" ,"+kbhID+" )";
		
							if (db.updateReturnInt(query)!=1) 
							{
								msg="Không thể cập nhật : "+query;
								db.getConnection().rollback();
								rs.close();
								return msg;
							}
		
		
							msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet(ngayhoadon_, "ErpDondathangDoitacSvl.java 489 CTKM CT DHID: "+lsxId,  db, _khoid, spID, npp_fk, _kbhid, solo, ngayhethan, ngaynhapkho, 0,soluongcapnhat,(-1)* soluongcapnhat, (-1)* soluongcapnhat, 0);
							if (msg1.length()> 0)
							{
								msg=msg1;
								db.getConnection().rollback();
								rs.close();
								return msg;
							}
						}
						rssp.close();
		
						if(soluongdenghi!=0){
		
		
							msg=  "Số lượng đề xuất trong lô chi tiết của sản phẩm "+spten+"   tới ngày (ngày cấu hình hóa đơn)"+ngayhoadon_+" không còn đủ, " +
							" vui lòng kiểm tra báo cáo ( xuất nhập tồn,tồn hiện tại) theo lô để biết thêm chi tiết ";
	
							db.getConnection().rollback();
							rs.close();
							return msg;
						}
					}
					rs.close(); 
					
				}

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				db.shutDown();
			}
		
		catch (Exception e) 
		{
			e.printStackTrace();
			Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}

		return "";
	}

	private String getDateTime1() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss.SS");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	private String DeleteChuyenKho(String lsxId)
	{
		dbutils db = new dbutils();
		Utility uilt_kho=new Utility();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			int checkkho=0;
			String _ngayhoadon="";
			String sql="select isnull(checkkho,0) checkkho ,ngaydonhang,khachhang_fk from ERP_DONDATHANGNPP where pk_Seq='" + lsxId + "' and trangthai=0";
			ResultSet rs=db.get(sql);
			try {
				if(rs.next())
				{
					checkkho=rs.getInt("checkkho");
				}
				else
				{
					return "lỗi xóa dh không hợp lệ .";
				}
				rs.close();	
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				Utility.rollback_and_shutdown(db);
				e1.printStackTrace();
				return "lỗi xóa dh không hợp lệ ."+e1.getMessage();
			}


			String query = "update ERP_DondathangNPP set trangthai = '3' where trangthai = 0 and pk_seq = '" + lsxId + "'";
			if(db.updateReturnInt(query)!=1)
			{
				msg = "1.Khong the xoa: " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}

			query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+lsxId+" )";
			rs = db.get(query);
			boolean dungchungkenh=false;
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")){
					dungchungkenh=true;
				}
			}
			rs.close();


			//GIAM BOOK, TANG AVAI
			/*query=	"	select khoxuat_fk, npp_fk, "+(dungchungkenh?"100025":" kbh_fk")+ " as kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
			"	from " +
			"	( " +
			"		select c.kho_fk as khoxuat_fk, c.npp_fk,   kbh_fk, a.sanpham_fk,       " +
			"				case when a.dvdl_fk IS null then a.soluong       " +
			"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
			"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  end as soluong    " +
			"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
			"				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq     " +
			"		where a.dondathang_fk in (  " + lsxId + "  ) and a.soluong > 0 " +
			"	) " +
			"	DATHANG  " +
			"	group by khoxuat_fk, npp_fk, "+(dungchungkenh?"":" kbh_fk,")+ "  sanpham_fk " ;

			ResultSet rskho=db.get(query);
			while(rskho.next()){
				String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk ;
				_khoxuat_fk=rskho.getString("khoxuat_fk");
				_npp_fk=rskho.getString("npp_fk");
				_kbh_fk=rskho.getString("kbh_fk");
				_sanpham_fk=rskho.getString("sanpham_fk"); 
				double soluongct_ =rskho.getDouble("soluong");
				 String msg1=uilt_kho.Update_NPP_Kho_Sp(this.getDateTime(), "erpdondathangDoitacSvl 340",
							  db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, 0, soluongct_*(-1), soluongct_, 0);
					  if(msg1.length() >0){
							db.getConnection().rollback();
							return msg1;
					  }

			}
			rskho.close();*/

			// xoa bang chi tiet

	/*		if(checkkho==1)
			{
				query=	" SELECT KHOXUAT_FK, NPP_FK,   "+(dungchungkenh?"100025":" kbh_fk")+ " as  KBH_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG   ,SOLO, NGAYHETHAN, NGAYNHAPKHO "+      
				" FROM "+
				" ( "+
				" SELECT C.KHO_FK AS KHOXUAT_FK, C.NPP_FK,   KBH_FK, A.SANPHAM_FK, A.SOLO,A.NGAYHETHAN,A.NGAYNHAPKHO, "+     
				" CASE WHEN A.DVDL_FK IS NULL THEN A.SOLUONG       "+
				" WHEN A.DVDL_FK = B.DVDL_FK THEN A.SOLUONG      "+
				" ELSE  A.SOLUONG * ( SELECT SOLUONG1 / SOLUONG2 FROM QUYCACH "+ 
				" WHERE SANPHAM_FK = A.SANPHAM_FK AND DVDL2_FK = A.DVDL_FK AND DVDL1_FK = B.DVDL_FK )  END AS SOLUONG "+   
				" FROM ERP_DONDATHANGNPP_SANPHAM_CHITIET A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ   "+
				" INNER JOIN ERP_DONDATHANGNPP C ON A.DONDATHANG_FK = C.PK_SEQ     "+
				" WHERE A.DONDATHANG_FK IN (  "+lsxId+" ) AND A.SOLUONG > 0 "+
				" ) "+
				" DATHANG "+ 
				" GROUP BY KHOXUAT_FK, NPP_FK, "+(dungchungkenh?"":" kbh_fk,")+ " SANPHAM_FK ,SOLO, NGAYHETHAN, NGAYNHAPKHO    ";

				rskho=db.get(query);
				while(rskho.next()){
					String   _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk,_solo,_ngayhethan,_ngaynhapkho ;
					_khoxuat_fk=rskho.getString("khoxuat_fk");
					_npp_fk=rskho.getString("npp_fk");
					_kbh_fk=rskho.getString("kbh_fk");
					_sanpham_fk=rskho.getString("sanpham_fk"); 
					_solo= rskho.getString("SOLO");
					_ngayhethan= rskho.getString("ngayhethan");
					_ngaynhapkho= rskho.getString("ngaynhapkho");

					double soluongct_ =rskho.getDouble("SOLUONG");

					String msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet("","Cập nhật đơn hàng đối tác :erpdondathangDoitacSvl 372" 
												  , db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, (-1)*soluongct_, soluongct_, soluongct_, 0);
										  if(msg1.length() >0){
											  db.getConnection().rollback();
												return msg1;
										  }


				}
				rskho.close();
			}*/


			// PHAI HUY DON DUOI Đối tác trực thuộc đặt lên (trường hợp không
			// phải tự tao mới)
			query = "update ERP_Dondathang set trangthai = '3', GHICHU = N'Cấp trên không duyệt' where pk_seq = ( select from_dondathang from ERP_DondathangNPP where pk_seq = '" + lsxId + "' ) ";
			if(!db.update(query))
			{
				msg = "2.Khong the xoa: " + query;
				Utility.rollback_and_shutdown(db);
				return msg;
			}

			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			Utility.rollback_and_shutdown(db);
			return "Exception: " + e.getMessage();
		}

		return "";

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		  geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
			if(!csdr.__validate_post())
			{
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
		    
		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}

		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "0";


		IErpDondathangDoitacList obj = new ErpDondathangDoitacList();
		obj.setLoaidonhang(loaidonhang);



		Utility util = new Utility();

		HttpSession session = request.getSession();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 

		obj.setUserId(userId);


		if(action.equals("Tao moi"))
		{
			IErpDondathangDoitac lsxBean = new ErpDondathangDoitac();
			lsxBean.setLoaidonhang(loaidonhang);
			lsxBean.setUserId(userId);

			lsxBean.createRs();
			session.setAttribute("dvkdId", lsxBean.getDvkdId());
			session.setAttribute("kbhId", lsxBean.getKbhId());
			session.setAttribute("nppId", lsxBean.getNppId());
			session.setAttribute("doitacId", "");
			session.setAttribute("ngaydh", lsxBean.getNgayyeucau());

			session.setAttribute("lsxBean", lsxBean);

			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp";
			response.sendRedirect(nextJSP);
		}
		else
		{
			if(action.equals("view") || action.equals("next") || action.equals("prev"))
			{
				String search = getSearchQuery(request, obj);
				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				obj.setUserId(userId);
				obj.init(search);
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);

				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTac.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				String search = getSearchQuery(request, obj);
				obj.setUserId(userId);
				obj.init(search);

				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);

				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTac.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}

	private String getSearchQuery(HttpServletRequest request, IErpDondathangDoitacList obj)
	{
		Utility util = new Utility();
		String query = "select isnull(a.ngaygiochot,a.ngaydonhang) as ngaygiochot,a.PK_SEQ, a.trangthai, a.ngaydonhang, c.ten as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, ISNULL(cast(a.from_dondathang as nvarchar),'')as maddh, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, a.NPP_DACHOT,   " +
		" kbh.ten KenhBanHang,isnull(a.iskm,0) as iskm ,isnull(a.isdhkhac,0) as isdhkhac "+
		" from ERP_DondathangNPP a inner join KHO b on a.kho_fk = b.pk_seq inner join NHAPHANPHOI c on a.NPP_DAT_FK = c.pk_seq  " +
		" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ " +
		" inner join kenhbanhang kbh on kbh.pk_seq = a.kbh_fk  " +
		" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0 and a.kho_fk in "+util.quyen_kho(obj.getUserId());

		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);

		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);

		String nppId = request.getParameter("nppId");
		if(nppId == null)
			nppId = "";
		obj.setNppTen(nppId);

		String sodonhang = request.getParameter("sodonhang");
		if(sodonhang == null)
			sodonhang = "";
		obj.setSodonhang(sodonhang);

		String khId = request.getParameter("khId");
		if(khId == null)
			khId = "";
		obj.setKhTen(khId);

		String madhdt = request.getParameter("madhdt");
		if(madhdt == null)
			madhdt = "";
		obj.setMaddh(madhdt);


		String iskm = util.antiSQLInspection(request.getParameter("iskm")==null?"0":request.getParameter("iskm"));
		obj.setIsKm(iskm);

		if(iskm.length() > 0)
			query += " and isnull(a.isdhkhac,0) = '" + iskm + "' ";


		if(tungay.length() > 0)
			query += " and a.ngaydonhang >= '" + tungay + "'";

		if(denngay.length() > 0)
			query += " and a.ngaydonhang <= '" + denngay + "'";

		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";

		if(nppId.length() > 0){
			query += " and a.NPP_FK= '" + nppId + "'";
		}

		if(sodonhang.length() > 0){
			query += " and cast( a.PK_SEQ as varchar(10) ) like '%" + sodonhang + "%'";
		}

		if(khId.length() > 0){
			query += " and a.npp_dat_FK = '" + khId + "'";
		}
		if(madhdt.length() > 0){
			query += " and a.from_dondathang = '" + madhdt + "'";
		}
		System.out.print(query);
		return query;
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	
	//Xoá đơn bán đối tác và các chứng từ liên quan
	public static void main(String[] args) {

		String query = "";
		String lsxId = "";
		String kbh_fk = "100025";
		ResultSet rs;
		String msg = "";
		String ngaydonhang = "";
		dbutils db = new dbutils();
		String npp_fk = "";

		try {

			db.getConnection().setAutoCommit(false);
			query = "select pk_seq,ngaydonhang from erp_dondathangnpp where pk_Seq in (124412, 124413, 124414)";
			ResultSet temprs = db.get(query);

			while (temprs.next()) {

				lsxId = temprs.getString("pk_seq");
				ngaydonhang = temprs.getString("ngaydonhang");

				query =  "\n select sp.dvdl_fk,dvCHUAN, khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
				"\n 	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
				"\n  from     " +
				"\n  (     " +
				"\n 	select c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk,a.DVDL_FK as dvCHUAN,     " +
				"\n 			case when a.dvdl_fk IS null then a.soluong      " +
				"\n 				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
				"\n 				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
				"\n 								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
				"\n 	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
				"\n 			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
				"\n 	where a.dondathang_fk = " + lsxId +
				"\n )     " +
				"\n dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
				"\n group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,sp.dvdl_fk,dvCHUAN ";
				rs = db.get(query);

				Utility uilt_kho=new Utility();
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					npp_fk = nppID;
					String spID = rs.getString("PK_SEQ");
					double soluong = rs.getDouble("soluongXUAT");

					String msg1 = uilt_kho.Update_NPP_Kho_Sp(ngaydonhang, "Revert đơn hàng đối tác - kho tổng: "+lsxId 
							, db, khoID, spID, nppID, kbhID, soluong, 0, soluong, 0);
					if(msg1.length() >0){
						msg =msg1;
						db.getConnection().rollback();
						rs.close();
					}

					//Revert chi tiết
					query = "\n select soluong,"+khoID+" kho_fk, "+nppID+" npp_fk,"+kbhID+" kbh_fk,SANPHAM_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO " +
					"\n from ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = "+lsxId+" and sanpham_fk = "+spID;

					ResultSet rssp=db.get(query);
					double soluongdenghi=soluong ;

					while(rssp.next() && soluongdenghi >0)
					{
						double soluongcapnhat = 0;
						soluongcapnhat = rssp.getDouble("soluong");
						String solo=rssp.getString("SOLO");
						String ngaynhapkho=rssp.getString("ngaynhapkho");
						String ngayhethan=rssp.getString("ngayhethan");
						String _khoid=rssp.getString("kho_fk");
						String _kbhid=rssp.getString("KBH_FK");

						msg1=uilt_kho.Update_NPP_Kho_Sp_Chitiet(ngaydonhang, "Revert đơn hàng đối tác - kho chi tiết: "+lsxId,  db, _khoid, spID, npp_fk, _kbhid, solo, ngayhethan, ngaynhapkho, soluongcapnhat,0, soluongcapnhat, soluongcapnhat, 0);
						if (msg1.length()> 0)
						{
							msg=msg1;
							db.getConnection().rollback();
							rs.close();
						}
					}
					rssp.close();

				}
				rs.close();
			}
			temprs.close();
			
			if (msg.length() <= 0) {
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
			else {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			db.update("rollback");
		}
	}
}
