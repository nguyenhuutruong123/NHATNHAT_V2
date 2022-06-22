package geso.dms.center.servlets.thongtinsanpham;

import geso.dms.center.beans.thongtinsanpham.IThongtinsanpham;
import geso.dms.center.beans.thongtinsanpham.IThongtinsanphamList;
import geso.dms.center.beans.thongtinsanpham.imp.Thongtinsanpham;
import geso.dms.center.beans.thongtinsanpham.imp.ThongtinsanphamList;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

public class ThongtinsanphamUpdateSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet
{
	static final long serialVersionUID = 1L;
	private PrintWriter out;
	private int items = 50;
	private int splittings = 10;

	public ThongtinsanphamUpdateSvl()
	{
		super();
	}

	private void settingPage(IThongtinsanphamList obj)
	{
		Utility util = new Utility();
		if (getServletContext().getInitParameter("items") != null)
		{
			String i = getServletContext().getInitParameter("items").trim();
			if (util.isNumeric(i))
				items = Integer.parseInt(i);
		}

		if (getServletContext().getInitParameter("splittings") != null)
		{
			String i = getServletContext().getInitParameter("splittings").trim();
			if (util.isNumeric(i))
				splittings = Integer.parseInt(i);
		}

		obj.setItems(items);
		obj.setSplittings(splittings);
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
		this.out = response.getWriter();
		Utility util = new Utility();
			
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		out.println(userId);

		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String id = util.getId(querystring);

		IThongtinsanpham spBean = new Thongtinsanpham();
		spBean.setId(id);
		spBean.setUserId(userId);
		if (querystring.equals("display"))
			spBean.init2();
		else
			spBean.init();
		session.setAttribute("spBean", spBean);
		String nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamUpdate.jsp";
		if (querystring.indexOf("display") > 0)
			nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamDisplay.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);

		boolean error = false;
		// PrintWriter out = response.getWriter();
		Utility util = new Utility();
		IThongtinsanpham spBean = new Thongtinsanpham();
		
		String id="",userId="",nccId="",masp="",pt_vat="",pt_vat_2="",trangthai="",isKm="", tensp="",dvdlId="",dvkdId="",nhId="",clId="",spchuluc="",spmoi="",kl="",packsizeid="",
			   machuan="",tenchuan="",nganhhangid="",tenviettat="",thanhphan="",dangbaoche="",hamluong="",hansudung="",
			   tt="",dvdlETCId="",ngaysua="",nguoisua="", nhanhangId="",type="", chungloaiId = "",nhomhangId = "",qcdonggoi="",action="";
		String[] nspIds, sl1, dvdl1, sl2, dvdl2, spIds, nhanhangIds, chungloaiIds, nhomhangIds;
		String tontoithieu = "";
		long quydoithuong = 1;
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			
			MultipartRequest multi = new MultipartRequest(request, getServletContext().getInitParameter("AnhSanPham"), 20000000, "UTF-8");
			if(!csdr.__validate_post_mul(multi))
			{
				System.out.println("contentType loi : "+ contentType);
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
			System.out.println("\n Multi :"+multi.getParameter("dangbaoche"));
			try 
			{
				Enumeration files = multi.getFileNames();
				String filename = "";
				while (files.hasMoreElements())
				{
					String name = (String) files.nextElement();
					filename = multi.getFilesystemName(name);
				}
				
				if (filename == null || filename.trim().length() <= 0)
				{
					filename = multi.getParameter("hinhanh");
				}
				spBean.setHinhanh(filename);
				System.out.println("1____READ EXCEL TOI DAY, FILE NAME......" + filename);
				
				id = util.antiSQLInspection(multi.getParameter("id"));
				if (id != null)
					spBean.setId(id);
				
				tontoithieu = util.antiSQLInspection(multi.getParameter("tontoithieu"));
				spBean.setTontoithieu(tontoithieu);
					

				userId = util.antiSQLInspection(multi.getParameter("userId"));
				spBean.setUserId(userId);

				nccId = util.antiSQLInspection(multi.getParameter("nccId"));
				if (nccId == null)
					nccId = "";
				spBean.setNccId(nccId);
				
				masp = util.antiSQLInspection(multi.getParameter("masp"));
				if (masp == null)
					masp = "";
				spBean.setMasp(masp);
	 
				pt_vat = util.antiSQLInspection(multi.getParameter("pt_vat").replace(",",""));
				if (pt_vat == null)
					pt_vat = "0";
				spBean.setPt_vat(Utility.parseDouble(pt_vat));
				
				pt_vat_2 = util.antiSQLInspection(multi.getParameter("pt_vat_2").replace(",",""));
				if (pt_vat_2 == null)
					pt_vat_2 = "0";
				spBean.setPt_vat_2(pt_vat_2);
				
				trangthai = util.antiSQLInspection(multi.getParameter("trangthai"));
				if (trangthai == null)
					trangthai = "0";
				else
					trangthai = "1";
				spBean.setTrangthai(trangthai);
				
				
				isKm = util.antiSQLInspection(multi.getParameter("isKm"));
				if (isKm == null)
					isKm = "0";
				else
					isKm = "1";
				spBean.setIsKm(isKm);

				tensp = util.antiSQLInspection(multi.getParameter("tensp"));
				if (tensp == null)
					tensp = "";
				spBean.setTen(tensp);

				dvdlId = util.antiSQLInspection(multi.getParameter("dvdlId"));
				if (dvdlId == null)
					dvdlId = "";
				spBean.setDvdlId(dvdlId);

				dvkdId = util.antiSQLInspection(multi.getParameter("dvkdId"));
				if (dvkdId == null)
					dvkdId = "";
				spBean.setDvkdId(dvkdId);
				// System.out.print(dvkdId +"dvkdId;");

				nhId = util.antiSQLInspection(multi.getParameter("nhId"));
				if (nhId == null)
					nhId = "";
				spBean.setNhId(nhId);
				System.out.println("nhãn hàng id --- "+nhId);

				clId = util.antiSQLInspection(multi.getParameter("clId"));
				if (clId == null)
					clId = "";
				spBean.setClId(clId);
				
				spchuluc = util.antiSQLInspection(multi.getParameter("spchuluc"));
				if (spchuluc == null)
					spchuluc = "0";
				else 
					spchuluc="1";
				spBean.setSpChuLuc(spchuluc);
				
				spmoi = util.antiSQLInspection(multi.getParameter("spmoi"));
				if (spmoi == null)
					spmoi = "";
				else 
					spmoi="1";
				spBean.setSpMoi(spmoi);

				/*giablc = util.antiSQLInspection(request.getParameter("giablc").replaceAll(",", ""));
				if (giablc == null)
					giablc = "";
				spBean.setGiablc(giablc);
		*/
				kl = util.antiSQLInspection(multi.getParameter("kl"));
				if (kl == null)
					kl = "";
				spBean.setKL(kl);

				packsizeid = util.antiSQLInspection(multi.getParameter("packsizeid"));
				if (packsizeid == null)
					packsizeid = "";
				spBean.setPacksizeId(packsizeid);

				machuan = util.antiSQLInspection(multi.getParameter("machuan"));
				if (machuan == null)
					machuan = "";
				spBean.setMachuan(machuan);

				tenchuan = util.antiSQLInspection(multi.getParameter("tenchuan"));
				if (tenchuan == null)
					tenchuan = "";
				spBean.setTenchuan(tenchuan);

				nganhhangid = util.antiSQLInspection(multi.getParameter("nganhhangid"));
				if (nganhhangid == null)
					nganhhangid = "";
				spBean.setNganhhangid(nganhhangid);
				
				tenviettat = util.antiSQLInspection(multi.getParameter("tenviettat"));
				if (tenviettat == null)
					tenviettat = "";
				spBean.setTenviettat(tenviettat);
				
				thanhphan = util.antiSQLInspection(multi.getParameter("thanhphan"));
				if (thanhphan == null)
					thanhphan = "";
				spBean.setThanhphan(thanhphan);
				
				
				dangbaoche = util.antiSQLInspection(multi.getParameter("dangbaoche"));
				if (dangbaoche == null)
					dangbaoche = "";
				spBean.setDangbaoche(dangbaoche);
				
				hamluong = util.antiSQLInspection(multi.getParameter("hamluong"));
				if (hamluong == null)
					hamluong = "";
				spBean.setHamluong(hamluong);

				quydoithuong = 1;
				try
				{
					quydoithuong = Math.round(Double.parseDouble(util.antiSQLInspection(multi.getParameter("quydoithuong"))));
				} 
				catch (Exception er) { }

				hansudung = util.antiSQLInspection(multi.getParameter("hansudung"));
				if (hansudung == null)
					hansudung = "180";
				spBean.setHansudung(hansudung);

				spBean.setquydoithuong(quydoithuong + "");

				tt = util.antiSQLInspection(multi.getParameter("tt"));
				if (tt == null)
					tt = "";
				spBean.setTT(tt);
				
				dvdlETCId = util.antiSQLInspection(multi.getParameter("dvdlETCId"));
				if (dvdlETCId == null)
					dvdlETCId = "";
				spBean.setDvdlETCId(dvdlETCId);

				ngaysua = getDateTime();
				spBean.setNgaysua(ngaysua);

				nguoisua = userId;
				spBean.setNguoisua(nguoisua);

				nspIds = multi.getParameterValues("nspIds");
				if (!(nspIds == null))
					spBean.setNspIds(nspIds);

				sl1 = multi.getParameterValues("sl1");

				dvdl1 = multi.getParameterValues("dvdl1");

				sl2 = multi.getParameterValues("sl2");

				dvdl2 = multi.getParameterValues("dvdl2");

				if (!(sl1 == null))
					spBean.setSl1(sl1);

				if (!(dvdl1 == null))
					spBean.setDvdl1(dvdl1);

				if (!(sl2 == null))
					spBean.setSl2(sl2);

				if (!(dvdl2 == null))
					spBean.setDvdl2(dvdl2);

				type = util.antiSQLInspection(multi.getParameter("type"));
				if (type == null)
					type = "0";
				else
					type = "1";
				spBean.setType(type);

				spIds = multi.getParameterValues("spIds");
				if (spIds != null)
				{
					for(int i=0;i<spIds.length;i++)
					{
						String values=spIds[i];
						String soluong=multi.getParameter("spSoluong_"+spIds[i]);
						if(soluong != "")
		  					values += "-" + soluong;
						spIds[i]=values;
					}
					spBean.setSpIds(spIds);
					
					System.out.println("So san pham la: " + spIds.length + "\n");
				}
				
				nhanhangId = "";
				nhanhangIds = multi.getParameterValues("nhanhangIds");

				if (nhanhangIds != null)
				{
					for (int i = 0; i < nhanhangIds.length; i++)
					{
						nhanhangId += nhanhangIds[i] + ",";
					}
					if (nhanhangId.length() > 0)
						nhanhangId = nhanhangId.substring(0, nhanhangId.length() - 1);
				}
				spBean.setNhanhangIds(nhanhangId);
				 
				chungloaiId = "";
				chungloaiIds = multi.getParameterValues("chungloaiIds");
				if (chungloaiIds != null)
				{
					for (int i = 0; i < chungloaiIds.length; i++)
					{
						chungloaiId += chungloaiIds[i] + ",";
					}
					if (chungloaiId.length() > 0)
						chungloaiId = chungloaiId.substring(0, chungloaiId.length() - 1);
				}
				spBean.setChungloaiIds(chungloaiId);

				nhomhangId = "";
				nhomhangIds = multi.getParameterValues("nhomhangId");

				if (nhomhangIds != null)
				{
					for (int i = 0; i < nhomhangIds.length; i++)
					{
						nhomhangId += nhomhangIds[i] + ",";
					}
					System.out.println("_________"+nhomhangId);
					
					if (nhomhangId.length() > 0)
						nhomhangId = nhomhangId.substring(0, nhomhangId.length() - 1);
				}
				spBean.setNhomHangId(nhomhangId);
				
				qcdonggoi = util.antiSQLInspection(multi.getParameter("qcdonggoi"));
				if (qcdonggoi == null)
					qcdonggoi = "";
				spBean.setQcDongGoi(qcdonggoi);
				
				action = multi.getParameter("action");
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			if(!csdr.__validate_post())
			{
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
			
			System.out.println("Request");
			id = util.antiSQLInspection(request.getParameter("id"));
			if (id != null)
				spBean.setId(id);

			userId = util.antiSQLInspection(request.getParameter("userId"));
			spBean.setUserId(userId);

			tontoithieu = util.antiSQLInspection(request.getParameter("tontoithieu"));
			spBean.setTontoithieu(tontoithieu);
			
			nccId = util.antiSQLInspection(request.getParameter("nccId"));
			if (nccId == null)
				nccId = "";
			spBean.setNccId(nccId);
			
			masp = util.antiSQLInspection(request.getParameter("masp"));
			if (masp == null)
				masp = "";
			spBean.setMasp(masp);

			System.out.println("masp :" + request.getParameter("masp").replace(",",""));
			pt_vat = util.antiSQLInspection(request.getParameter("pt_vat").replace(",",""));
			if (pt_vat == null)
				pt_vat = "0";
			spBean.setPt_vat(Double.parseDouble(pt_vat));
			
			pt_vat_2 = util.antiSQLInspection(request.getParameter("pt_vat_2").replace(",",""));
			if (pt_vat_2 == null)
				pt_vat_2 = "0";
			spBean.setPt_vat_2(pt_vat_2);
			
			trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			if (trangthai == null)
				trangthai = "0";
			else
				trangthai = "1";
			spBean.setTrangthai(trangthai);
			
			
			isKm = util.antiSQLInspection(request.getParameter("isKm"));
			if (isKm == null)
				isKm = "0";
			else
				isKm = "1";
			spBean.setIsKm(isKm);
			
			tensp = util.antiSQLInspection(request.getParameter("tensp"));
			if (tensp == null)
				tensp = "";
			spBean.setTen(tensp);

			dvdlId = util.antiSQLInspection(request.getParameter("dvdlId"));
			if (dvdlId == null)
				dvdlId = "";
			spBean.setDvdlId(dvdlId);

			dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
			if (dvkdId == null)
				dvkdId = "";
			spBean.setDvkdId(dvkdId);
			// System.out.print(dvkdId +"dvkdId;");

			nhId = util.antiSQLInspection(request.getParameter("nhId"));
			if (nhId == null)
				nhId = "";
			spBean.setNhId(nhId);
			System.out.println("nhãn hàng id --- "+nhId);
			
			clId = util.antiSQLInspection(request.getParameter("clId"));
			if (clId == null)
				clId = "";
			spBean.setClId(clId);
			
			spchuluc = util.antiSQLInspection(request.getParameter("spchuluc"));
			if (spchuluc == null)
				spchuluc = "0";
			else 
				spchuluc="1";
			spBean.setSpChuLuc(spchuluc);
			
			spmoi = util.antiSQLInspection(request.getParameter("spmoi"));
			if (spmoi == null)
				spmoi = "";
			else 
				spmoi="1";
			spBean.setSpMoi(spmoi);

			/*giablc = util.antiSQLInspection(request.getParameter("giablc").replaceAll(",", ""));
			if (giablc == null)
				giablc = "";
			spBean.setGiablc(giablc);
	*/
			kl = util.antiSQLInspection(request.getParameter("kl"));
			if (kl == null)
				kl = "";
			spBean.setKL(kl);

			packsizeid = util.antiSQLInspection(request.getParameter("packsizeid"));
			if (packsizeid == null)
				packsizeid = "";
			spBean.setPacksizeId(packsizeid);

			machuan = util.antiSQLInspection(request.getParameter("machuan"));
			if (machuan == null)
				machuan = "";
			spBean.setMachuan(machuan);
			
			quydoithuong = 1;
			tenchuan = util.antiSQLInspection(request.getParameter("tenchuan"));
			if (tenchuan == null)
				tenchuan = "";
			spBean.setTenchuan(tenchuan);

			nganhhangid = util.antiSQLInspection(request.getParameter("nganhhangid"));
			if (nganhhangid == null)
				nganhhangid = "";
			spBean.setNganhhangid(nganhhangid);
			
			tenviettat = util.antiSQLInspection(request.getParameter("tenviettat"));
			if (tenviettat == null)
				tenviettat = "";
			spBean.setTenviettat(tenviettat);
			
			thanhphan = util.antiSQLInspection(request.getParameter("thanhphan"));
			if (thanhphan == null)
				thanhphan = "";
			spBean.setThanhphan(thanhphan);
			
			
			dangbaoche = util.antiSQLInspection(request.getParameter("dangbaoche"));
			System.out.println("dangbaoche : "+ dangbaoche);
			if (dangbaoche == null)
				dangbaoche = "";
			spBean.setDangbaoche(dangbaoche);
			
			hamluong = util.antiSQLInspection(request.getParameter("hamluong"));
			if (hamluong == null)
				hamluong = "";
			spBean.setHamluong(hamluong);

			quydoithuong = 1;
			try
			{
				quydoithuong = Math.round(Double.parseDouble(util.antiSQLInspection(request.getParameter("quydoithuong"))));
			} 
			catch (Exception er) { }

			hansudung = util.antiSQLInspection(request.getParameter("hansudung"));
			if (hansudung == null)
				hansudung = "180";
			spBean.setHansudung(hansudung);
			spBean.setquydoithuong(quydoithuong + "");
			
			tt = util.antiSQLInspection(request.getParameter("tt"));
			if (tt == null)
				tt = "";
			spBean.setTT(tt);
			
			dvdlETCId = util.antiSQLInspection(request.getParameter("dvdlETCId"));
			if (dvdlETCId == null)
				dvdlETCId = "";
			spBean.setDvdlETCId(dvdlETCId);

			ngaysua = getDateTime();
			spBean.setNgaysua(ngaysua);

			nguoisua = userId;
			spBean.setNguoisua(nguoisua);

			nspIds = request.getParameterValues("nspIds");
			if (!(nspIds == null))
				spBean.setNspIds(nspIds);

			sl1 = request.getParameterValues("sl1");

			dvdl1 = request.getParameterValues("dvdl1");

			sl2 = request.getParameterValues("sl2");

			dvdl2 = request.getParameterValues("dvdl2");

			if (!(sl1 == null))
				spBean.setSl1(sl1);

			if (!(dvdl1 == null))
				spBean.setDvdl1(dvdl1);

			if (!(sl2 == null))
				spBean.setSl2(sl2);

			if (!(dvdl2 == null))
				spBean.setDvdl2(dvdl2);

			type = util.antiSQLInspection(request.getParameter("type"));
			if (type == null)
				type = "0";
			else
				type = "1";
			spBean.setType(type);

			spIds = request.getParameterValues("spIds");
			if (spIds != null)
			{
				for(int i=0;i<spIds.length;i++)
				{
					String values=spIds[i];
					String soluong=request.getParameter("spSoluong_"+spIds[i]);
					if(soluong != "")
	  					values += "-" + soluong;
					spIds[i]=values;
				}
				spBean.setSpIds(spIds);
				
				System.out.println("So san pham la: " + spIds.length + "\n");
			}
			
			nhanhangId = "";
			nhanhangIds = request.getParameterValues("nhanhangIds");

			if (nhanhangIds != null)
			{
				for (int i = 0; i < nhanhangIds.length; i++)
				{
					nhanhangId += nhanhangIds[i] + ",";
				}
				if (nhanhangId.length() > 0)
					nhanhangId = nhanhangId.substring(0, nhanhangId.length() - 1);
			}
			spBean.setNhanhangIds(nhanhangId);
			 
			chungloaiId = "";
			chungloaiIds = request.getParameterValues("chungloaiIds");
			if (chungloaiIds != null)
			{
				for (int i = 0; i < chungloaiIds.length; i++)
				{
					chungloaiId += chungloaiIds[i] + ",";
				}
				if (chungloaiId.length() > 0)
					chungloaiId = chungloaiId.substring(0, chungloaiId.length() - 1);
			}
			spBean.setChungloaiIds(chungloaiId);

			nhomhangId = "";
			nhomhangIds = request.getParameterValues("nhomhangId");

			if (nhomhangIds != null)
			{
				for (int i = 0; i < nhomhangIds.length; i++)
				{
					nhomhangId += nhomhangIds[i] + ",";
				}
				System.out.println("_________"+nhomhangId);
				
				if (nhomhangId.length() > 0)
					nhomhangId = nhomhangId.substring(0, nhomhangId.length() - 1);
			}
			spBean.setNhomHangId(nhomhangId);
			
			qcdonggoi = util.antiSQLInspection(request.getParameter("qcdonggoi"));
			if (qcdonggoi == null)
				qcdonggoi = "";
			spBean.setQcDongGoi(qcdonggoi);
			action = request.getParameter("action");
		}
		
		if (dvkdId.trim().length() == 0)
		{
			spBean.setMessage("Vui lòng nhập  đơn vị kinh doanh của sản phẩm");
			error = true;
		}

		if (dvdlId.trim().length() == 0)
		{
			spBean.setMessage("Vui lòng nhập vào đơn vị đo lường của sản phẩm");
			error = true;
		}

		if (tensp.trim().length() == 0)
		{
			spBean.setMessage("Vui lòng nhập tên của sản phẩm");
			error = true;
		}

		if (masp.trim().length() == 0)
		{
			spBean.setMessage("Vui lòng nhập vào mã của sản phẩm");
			error = true;
		}
		
		if (nganhhangid.trim().length() == 0)
		{
			spBean.setMessage("Vui lòng nhập vào ngành hàng");
			error = true;
		}
		
		if (error)
		{
			System.out.print("error = true");
		}
		spBean.CreateRS();
		spBean.setUserId(userId);
		session.setAttribute("userId", userId);
		session.setAttribute("spBean", spBean);

		
		
		
		String nextJSP;
		if (action.equals("save") && !error)
		{
			if (!(spBean.CreateSp()))
			{
				System.out.println("KET QUA TAO MOI SP LA FALSE... " + spBean.getMessage());
				nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamNew.jsp";
				response.sendRedirect(nextJSP);
			} 
			else
			{
				IThongtinsanphamList obj = new ThongtinsanphamList();
				obj.setUserId(userId);
				settingPage(obj);
				obj.init();
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");
			}
		} 
		else if (action.equals("update") && !error)
		{
			if (!(spBean.UpdateSp()))
			{
				nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamUpdate.jsp";
				response.sendRedirect(nextJSP);
			} 
			else
			{
				IThongtinsanphamList obj = new ThongtinsanphamList();
				obj.setUserId(userId);
				settingPage(obj);
				obj.init();
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				response.sendRedirect(request.getContextPath() + "/pages/Center/ThongTinSanPham.jsp");
			}
		} 
		else if(action.equals("xoaanh"))
		{
			spBean.xoaanhSp();
			IThongtinsanpham spBean1 = new Thongtinsanpham();
			spBean1.setId(id);
			spBean1.setUserId(userId);
			spBean1.init();
			session.setAttribute("spBean", spBean1);
			 nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamUpdate.jsp";
			 response.sendRedirect(nextJSP);
			 return;
		}
			
		else if (id == null)
		{
			nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamNew.jsp";
			response.sendRedirect(nextJSP);
		} 
		else
		{
			nextJSP = request.getContextPath() + "/pages/Center/ThongTinSanPhamUpdate.jsp";
			response.sendRedirect(nextJSP);
		}
		
		System.out.println("action" + action);
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

}