package geso.dms.center.servlets.bundle;

import geso.dms.center.beans.bundle.*;
import geso.dms.center.beans.bundle.imp.RaBundle;
import geso.dms.center.beans.bundle.imp.RaBundleList;
import geso.dms.center.beans.bundle.imp.Sanpham;
import geso.dms.center.beans.bundle.imp.SpDetail;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RaBundleUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out;

	public RaBundleUpdateSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {
			session.setMaxInactiveInterval(30000);

			this.out = response.getWriter();
			Utility util = new Utility();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			IRaBundleList dhList = new RaBundleList();

			String trangthai = request.getParameter("trangthai");
			if (trangthai == null)
				trangthai = "";
			dhList.setTrangthai(trangthai);

			String tungay = request.getParameter("tungay");
			if (tungay == null)
				tungay = "";
			dhList.setTungay(tungay);

			String denngay = request.getParameter("denngay");
			if (denngay == null)
				denngay = "";
			dhList.setDenngay(denngay);

			

			String mafast = request.getParameter("mafast");
			if (mafast == null)
				mafast = "";
			dhList.setMafast(mafast);


			session.setAttribute("dhList", dhList);

			out.println(userId);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String copy = request.getParameter("copy");
			if (copy == null)
				copy = "";

			String id = util.getId(querystring);
			String msg = "";

			IRaBundle dhBean = new RaBundle(id);
			dhBean.setUserId(userId); // phai co UserId truoc khi Init			
			dhBean.init();
		
			String nextJSP;

			if (request.getQueryString().indexOf("display") >= 0) {
				nextJSP = request.getContextPath() + "/pages/Center/RaBundleDisplay.jsp";
			} 
			else if (request.getQueryString().indexOf("update") >= 0){
				nextJSP = request.getContextPath() + "/pages/Center/RaBundleUpdate.jsp";
			}
			else{
				nextJSP = request.getContextPath() + "/pages/Center/RaBundleDuyetDisplay.jsp";
			}

			session.setAttribute("dhBean", dhBean);
			session.setAttribute("dvkdId", dhBean.getDvkdId());
			session.setAttribute("nppId", dhBean.getNppId());
			session.setAttribute("khoId", dhBean.getKhoId());
			session.setAttribute("kbhId", dhBean.getKbhId());
			session.setAttribute("ngaydonhang", dhBean.getNgaygiaodich());
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

		if (!cutil.check(userId, userTen, sum)) {
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} else {
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			session.setMaxInactiveInterval(30000);

			this.out = response.getWriter();
			dbutils db = new dbutils();
			IRaBundle dhBean;
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));
			if (id == null) {
				dhBean = new RaBundle("");
			} else {
				dhBean = new RaBundle(id);
			}
			System.out.println("dhId = "+id);

			userId = util.antiSQLInspection(request.getParameter("userId"));
			dhBean.setUserId(userId);

			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			dhBean.setNppId(nppId);
			session.setAttribute("nppId", dhBean.getNppId());

			

			String ngaygd = util.antiSQLInspection(request.getParameter("ngaygiaodich"));
			if (ngaygd == null || ngaygd == "")
				ngaygd = this.getDateTime();
			dhBean.setNgaygiaodich(ngaygd);
			session.setAttribute("ngaydonhang", ngaygd);

			String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			if (trangthai == null)
				trangthai = "";
			dhBean.setTrangthai(trangthai);


			String khoId = util.antiSQLInspection(request.getParameter("khoTen"));
			if (khoId == null)
				khoId = "";
			dhBean.setKhoId(khoId);
			session.setAttribute("khoId", dhBean.getKhoId());
			
			
			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			if (kbhId == null)
				kbhId = "";
			dhBean.setKbhId(kbhId);
			session.setAttribute("kbhId", dhBean.getKbhId());
			
			String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
			if (dvkdId == null)
				dvkdId = "";
			dhBean.setDvkdId(dvkdId);
			session.setAttribute("dvkdId", dhBean.getDvkdId());
	
	


			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
			if (ghichu == null || ghichu.length() == 0)
				ghichu = " ";
			dhBean.setGhiChu(ghichu);

		
			
			String spId = util.antiSQLInspection(request.getParameter("spId"));
			if (spId == null)
				spId = "";
			dhBean.setSpId(spId);

			
			
			

			String ngaysua = getDateTime();
			dhBean.setNgaysua(ngaysua);

			
			String[] ra_spId = request.getParameterValues("ra_spId");
			String[] ra_nppId = request.getParameterValues("ra_nppId");
			String[] ra_khoId = request.getParameterValues("ra_khoId");
			String[] ra_kbhId = request.getParameterValues("ra_kbhId");
			String[] ra_solo = request.getParameterValues("ra_solo");
			String[] ra_ngayhethan = request.getParameterValues("ra_ngayhethan");
			String[] ra_ngaynhapkho = request.getParameterValues("ra_ngaynhapkho");
			String[] ra_tonhientai = request.getParameterValues("ra_tonhientai");
			String[] ra_soluong = request.getParameterValues("ra_soluong");
			List<ISanpham> soloList = new ArrayList<ISanpham>();
			if (ra_solo != null) 
			{
				
				
				for(int m = 0; m < ra_solo.length ; m ++)
				{
					ISanpham sp = new Sanpham();
					System.out.println("spId =" + spId +" , nppId = "+ nppId +" , khoId = "+ khoId+" , kbhId = "+ kbhId);
					System.out.println("ra_spId[m] =" + ra_spId[m] +" , ra_nppId[m] = "+ ra_nppId[m] +" , ra_khoId[m] = "+ ra_khoId[m]+" , ra_kbhId[m] = "+ ra_kbhId[m]);
					if(ra_spId[m].equals(spId) && ra_nppId[m].equals(nppId)  && ra_khoId[m].equals(khoId)&& ra_kbhId[m].equals(kbhId)
							&& ra_ngaynhapkho[m].compareTo(ngaygd)	<=0 )
					{
						sp.setId(spId);
						sp.setNppId(nppId);
						sp.setKbhId(kbhId);
						sp.setKhoId(khoId);
						
						sp.setSolo(ra_solo[m]);
						sp.setNgayHetHan(ra_ngayhethan[m]);
						sp.setNgaynhapkho(ra_ngaynhapkho[m]);
						sp.setTonhientai(ra_tonhientai[m].replace(",",""));
						sp.setSoluong(ra_soluong[m].replace(",",""));
						soloList.add(sp);
					}
				}
				dhBean.setSoLoList(soloList);
			}
			
			
			String[] masp = request.getParameterValues("masp");
			String[] tensp = request.getParameterValues("tensp");
			String[] solo = request.getParameterValues("solo");
			String[] ngayhethan = request.getParameterValues("ngayhethan");
			String[] soluong = request.getParameterValues("soluong");
			String[] tonkho = request.getParameterValues("tonkho");
			String[] donvitinh = request.getParameterValues("donvitinh");
			List<ISanpham> spList = new ArrayList<ISanpham>();
			if (masp != null) 
			{
				
				String[] param = new String[8];
				
				for(int m = 0; m < masp.length ; m++) 
				{
					ISanpham sanpham = null;
					if (masp[m] != "" && ngaygd.trim().length() > 0 && nppId.trim().length() > 0) 
					{
						if (soluong[m].length() > 0) 
						{
							

							param[0] = "idSP";
							param[1] = masp[m];
							param[2] = tensp[m];
							param[3] = soluong[m].replaceAll(",", "");
							param[4] = donvitinh[m];
							param[6] = ""; // khuyen mai
							param[7] = "0";					
							sanpham = new Sanpham(param);
							sanpham.setTonhientai(tonkho[m].replaceAll(",", ""));
							sanpham.setSoluong1("1");
							sanpham.setSoluong2("1");
							sanpham.setSoluongThung("1");
							sanpham.setSolo(solo[m].trim());
							sanpham.setNgayHetHan(ngayhethan[m].trim());
							spList.add(sanpham);
						}
					}
				
				}
				dhBean.setSpList(spList);
			}



			String action = request.getParameter("action")==null?"":request.getParameter("action");		

			if (action.equals("save")) 
			{
				if (id == null) 
				{
					boolean tao = dhBean.CreateDh();
					String dhId = dhBean.getId();
					if (!tao) 
					{
						dhBean.createRS();
						session.setAttribute("dhBean", dhBean);
						session.setAttribute("dvkdId", dhBean.getDvkdId());
						session.setAttribute("nppId", dhBean.getNppId());
						session.setAttribute("khoId", dhBean.getKhoId());
						session.setAttribute("kbhId", dhBean.getKbhId());
						session.setAttribute("ngaydonhang", dhBean.getNgaygiaodich());
						String nextJSP = request.getContextPath() + "/pages/Center/RaBundleNew.jsp";
						response.sendRedirect(nextJSP);
					} 
					else 
					{
						IRaBundleList obj= new RaBundleList();	
						obj.setUserId(userId);
						settingPage(obj);
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("khId", "");

						String nextJSP = request.getContextPath() + "/pages/Center/RaBundle.jsp";
						response.sendRedirect(nextJSP);
						return;
					}
				} 
				else 
				{
					

					boolean temp = dhBean.UpdateDh();

					if (temp == false) 
					{
						dhBean.createRS();
						
						session.setAttribute("dhBean", dhBean);
						session.setAttribute("dvkdId", dhBean.getDvkdId());
						session.setAttribute("nppId", dhBean.getNppId());
						session.setAttribute("khoId", dhBean.getKhoId());
						session.setAttribute("kbhId", dhBean.getKbhId());
						session.setAttribute("ngaydonhang", dhBean.getNgaygiaodich());
						String nextJSP = request.getContextPath() + "/pages/Center/RaBundleUpdate.jsp";
						response.sendRedirect(nextJSP);
					} 
					else 
					{
						IRaBundleList obj= new RaBundleList();	
						obj.setUserId(userId);
						settingPage(obj);
						obj.init("");
						session.setAttribute("obj", obj);
						session.setAttribute("khId", "");

						String nextJSP = request.getContextPath() + "/pages/Center/RaBundle.jsp";
						response.sendRedirect(nextJSP);
						return;
					}
				}
			}
			else
			{
				dhBean.setUserId(userId);
				dhBean.createRS();
				// Save Data into session
				session.setAttribute("dhBean", dhBean);
				session.setAttribute("dvkdId", dhBean.getDvkdId());
				session.setAttribute("nppId", dhBean.getNppId());
				session.setAttribute("khoId", dhBean.getKhoId());
				session.setAttribute("kbhId", dhBean.getKbhId());
				session.setAttribute("ngaydonhang", dhBean.getNgaygiaodich());
				String nextJSP = request.getContextPath() + "/pages/Center/RaBundleUpdate.jsp";
				if (id == null) 
					nextJSP = request.getContextPath() + "/pages/Center/RaBundleNew.jsp";
				response.sendRedirect(nextJSP);
				
			}
			db.shutDown();
		
		}
	}

	private String getSearchQuery(HttpServletRequest request,IRaBundleList obj) 
	{
		String nppId = request.getParameter("nppId");
		if ( nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		/*String ddkdId = request.getParameter("ddkdTen");
    	if ( ddkdId == null)
    		ddkdId = "";
    	obj.setDdkdId(ddkdId);

    	String trangthai = request.getParameter("trangthai");
    	if (trangthai == null)
    		trangthai = "";    	
    	obj.setTrangthai(trangthai);*/

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


		String query =  "select a.pk_seq as dhId, a.ngaynhap, a.trangthai, a.ngaytao, a.ngaysua, d.maFAST, isnull(a.DAXUATHOADON,0) as DAXUATHOADON , isnull(DAIN, '0') as DAINDH,     " +
		"			c.ten as nguoisua, d.ten as khTen, d.pk_seq as khId, e.pk_seq as ddkdId, e.ten as ddkdTen,        " +
		"			'' as nppTen, a.tonggiatri, d.THANHTOAN, a.VAT, " +
		"	'' as SoHoadon , ' ' as nguoitao, 0 as exitPXK      " +
		" from donhang a   " +
		"		left join nhanvien c on a.nguoisua = c.pk_seq inner join khachhang d on a.khachhang_fk = d.pk_seq        " +
		"		inner join daidienkinhdoanh e on a.ddkd_fk = e.pk_seq  " +
		"where a.npp_fk = '" + nppId + "'  ";

		if (tungay.length() > 0)
		{
			query = query + " and a.ngaynhap >= '" + tungay + "'";			
		}    	
		if (denngay.length() > 0)
		{
			query = query + " and a.ngaynhap <= '" + denngay + "'";	
		}
	
		if (mafast.length() > 0)
		{
			query = query + " and d.maFAST like '%" + mafast + "%'";	
		}
		/*System.out.println("\nQuery cua ban: " + query);*/
		return query;
	}



	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}



	private int items = 50; 
	private int splittings = 20;
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

}
