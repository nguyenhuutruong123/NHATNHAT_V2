package geso.dms.distributor.servlets.khachhang;

import geso.dms.distributor.beans.khachhang.*;
import geso.dms.distributor.beans.khachhang.imp.*;
import geso.dms.distributor.beans.khachhangtt.IKhachhangTTList;
import geso.dms.distributor.beans.khachhangtt.imp.KhachhangTTList;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class KhachhangUpdateSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public KhachhangUpdateSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		
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
		
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} 
		else
		{
			IKhachhang khBean;
			PrintWriter out;

			out = response.getWriter();
			Utility util = new Utility();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			out.println(userId);

			if (userId.length() == 0)
				userId = util.antiSQLInspection(request.getParameter("userId"));

			String id = util.getId(querystring);
			
			khBean = new Khachhang(id);
			khBean.setUserId(userId);
	
			
			
			String view = util.antiSQLInspection(request.getParameter("view"));
			if (view == null)
				view = "";			
			khBean.setView(view);			
			
			String task = util.antiSQLInspection(request.getParameter("task"));
			if (task == null)
				task = "";
			if (task.equals("xoafileanh"))
			{
				out = response.getWriter();
				out.write(khBean.XoaAnhDaiDien(id));
				return;			
			}
			else
				khBean.init();			
			
			session.setAttribute("khBean", khBean);
			String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangUpdate.jsp";
			if (request.getQueryString().indexOf("displayTT") >= 0) {
				nextJSP = request.getContextPath() + "/pages/Center/KhachHangDisplay.jsp";
			}
			else if (request.getQueryString().indexOf("display") >= 0) {
				nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangDisplay.jsp";
			} 
			else if (request.getQueryString().indexOf("updatett") >= 0) {
				nextJSP = request.getContextPath() + "/pages/Center/KhachHangUpdate.jsp";
			} 
			else {
				nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangUpdate.jsp";
			}
			response.sendRedirect(nextJSP);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		String sum = (String) session.getAttribute("sum");
		
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum))
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		} 
		else
		{
			IKhachhang khBean;
			PrintWriter out;

			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			out = response.getWriter();
			Utility util = new Utility();
			String id = util.antiSQLInspection(request.getParameter("id"));

			if (id == null)
			{
				khBean = new Khachhang("");
			} else
			{
				khBean = new Khachhang(id);
			}

			String view = util.antiSQLInspection(request.getParameter("view"));
			if (view == null)
				view = "";
			khBean.setView(view);
			
			String shopkey = util.antiSQLInspection(request.getParameter("shopkey"));
			if (shopkey == null)
				shopkey = "0";
			khBean.setShopkey(shopkey);

			userId = util.antiSQLInspection(request.getParameter("userId"));
			khBean.setUserId(userId);

			String makhoSAP = util.antiSQLInspection(request.getParameter("makhoSAP"));
			if (!Utility.isValid(makhoSAP))
				makhoSAP = "";
			khBean.setMakhoSAP(makhoSAP);

			String diachigiaohang = util.antiSQLInspection(request.getParameter("diachigiaohang"));
			if (diachigiaohang == null)
				diachigiaohang = "";
			khBean.setDiachigiaohang(diachigiaohang);

			String khTen = util.antiSQLInspection(request.getParameter("khTen"));
			if (khTen == null)
				khTen = "";
			khBean.setTen(khTen); 

			String tenkyhd = util.antiSQLInspection(request.getParameter("tenkyhd"));
			if (tenkyhd == null)
				tenkyhd = "";
			khBean.setTenKyHd(tenkyhd);

			String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
			if (maFAST == null)
				maFAST = "";
			khBean.MaFAST(maFAST);

			String macu = util.antiSQLInspection(request.getParameter("macu"));
			if (macu == null)
				macu = "";
			khBean.setMacu(macu);

			String thanhtoan = util.antiSQLInspection(request.getParameter("thanhtoan"));
			if (thanhtoan == null)
				thanhtoan = "";
			khBean.setThanhtoan(thanhtoan);

			String DiabanId = util.antiSQLInspection(request.getParameter("DiabanId"));
			if (DiabanId == null)
				DiabanId = "";
			khBean.setDiabanId(DiabanId);

			String thanhtoanQUY = util.antiSQLInspection(request.getParameter("thanhtoanQUY"));
			if (thanhtoanQUY == null)
				thanhtoanQUY = "";
			khBean.setThanhtoanQuy(thanhtoanQUY);

			String nppId = request.getParameter("nppId");
			if (nppId == null)
				nppId = "";
			khBean.setNppId(nppId.trim());
			//System.out.println("[nppId]"+nppId);

			String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
			if (trangthai == null)
				trangthai = "0";
			else
				trangthai = "1";
			khBean.setTrangthai(trangthai);

			String gioitinh = util.antiSQLInspection(request.getParameter("gioitinh"));
			if (gioitinh == null)
				gioitinh = "";
			khBean.setGioiTinh(gioitinh);

			String moiquanhe = util.antiSQLInspection(request.getParameter("moiquanhe"));
			if (moiquanhe == null)
				moiquanhe = "";
			khBean.setMoiquanhe(moiquanhe);

			String thoigiangap = util.antiSQLInspection(request.getParameter("thoigiangap"));
			if (thoigiangap == null)
				thoigiangap = "";
			khBean.setThoigiangap(thoigiangap);

			String type = util.antiSQLInspection(request.getParameter("type"));
			if (type == null)
				type = "0";
			khBean.settype(type);

			String diachi = util.antiSQLInspection(request.getParameter("diachi"));
			if (diachi == null)
				diachi = "";
			khBean.setDiachi(diachi);

			String hopdong = util.antiSQLInspection(request.getParameter("hopdong"))==null?"":util.antiSQLInspection(request.getParameter("hopdong").trim());
			khBean.setHopdong(hopdong);

			String dtId = util.antiSQLInspection(request.getParameter("dtId"))==null?"":util.antiSQLInspection(request.getParameter("dtId").trim());
			khBean.setDtId(dtId);

			String tpId = util.antiSQLInspection(request.getParameter("tpId"));
			if (tpId == null)
				tpId = "";
			khBean.setTpId(tpId);

			String qhId = util.antiSQLInspection(request.getParameter("qhId"));
			if (qhId == null)
				qhId = "";
			khBean.setQhId(qhId);

			String mckId = util.antiSQLInspection(request.getParameter("mckTen"));
			if (mckId == null)
				mckId = "";
			khBean.setMckId(mckId);

			// //System.out.println("chietkhau :" + mckId);
			String dienthoai = util.antiSQLInspection(request.getParameter("dienthoai"));
			if (dienthoai == null)
				dienthoai = "";
			khBean.setSodienthoai(dienthoai);

			String masothue = util.antiSQLInspection(request.getParameter("masothue"));
			if (masothue == null)
				masothue = "";
			khBean.setMasothue(masothue);

			String kbhId = util.antiSQLInspection(request.getParameter("kbhTen"));
			if (kbhId == null)
				kbhId = "";
			khBean.setKbhId(kbhId);

			String lchId = util.antiSQLInspection(request.getParameter("lchId"));
			if (lchId == null)
				lchId = "";
			khBean.setLchId(lchId);

			String hchId = util.antiSQLInspection(request.getParameter("hangcuahangId"));
			if (hchId == null)
				hchId = "";
			khBean.setHchId(hchId);
			
			String matkhau = util.antiSQLInspection(request.getParameter("matkhau"));
			if (matkhau == null)
				matkhau = "";
			khBean.setMatkhau(matkhau);

			String vtchId = util.antiSQLInspection(request.getParameter("vitri"));
			if (vtchId.trim().equals(""))
				vtchId = null;
			khBean.setVtId(vtchId);

			String diadiemId = util.antiSQLInspection(request.getParameter("diadiemId"));
			if (diadiemId == null)
				diadiemId = "";
			khBean.setDiadiemId(diadiemId);

			String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
			if (xuatkhau == null)
				xuatkhau = "0";
			khBean.setXuatkhau(xuatkhau);

			String kokyhopdong = util.antiSQLInspection(request.getParameter("kokyhopdong"));
			if (kokyhopdong == null)
				kokyhopdong = "0";
			khBean.setKhongkyhd(kokyhopdong);

			String chucuahieu = util.antiSQLInspection(request.getParameter("chucuahieu"));
			if (chucuahieu == null)
				chucuahieu = "";
			khBean.setChucuahieu(chucuahieu); 

			String khoId = util.antiSQLInspection(request.getParameter("khoId"));
			if (khoId == null)
				khoId = "";
			khBean.setkhoId(khoId);

			//System.out.println("kho: "+khoId);
			String mauhoadon = util.antiSQLInspection(request.getParameter("mauhoadon"));
			if (mauhoadon == null)
				mauhoadon = "";

			khBean.setmauhd(mauhoadon);

			String ptCHIETKHAU = util.antiSQLInspection(request.getParameter("ptCHIETKHAU"));
			if (ptCHIETKHAU == null)
				ptCHIETKHAU = "";
			khBean.setPT_Chietkhau(ptCHIETKHAU);

			String mst_canhan = util.antiSQLInspection(request.getParameter("mst_canhan"))==null?"":util.antiSQLInspection(request.getParameter("mst_canhan").trim());
			khBean.setMst(mst_canhan);

			String ngaysinh = util.antiSQLInspection(request.getParameter("ngaysinh"))==null?"":util.antiSQLInspection(request.getParameter("ngaysinh").trim());
			khBean.setNgaysinh(ngaysinh);

			String cmnd = util.antiSQLInspection(request.getParameter("cmnd"))==null?"":util.antiSQLInspection(request.getParameter("cmnd").trim());
			khBean.setCmnd(cmnd);

			String didong = util.antiSQLInspection(request.getParameter("didong"));
			if (didong == null)
				didong = "";
			khBean.setDidong(didong);

			String phuongxaId = util.antiSQLInspection(request.getParameter("phuongxaId"));
			if (phuongxaId == null)
				phuongxaId = "";
			khBean.setPhuongxaId(phuongxaId);

			String phuongxa = util.antiSQLInspection(request.getParameter("phuongxaId"));
			if (phuongxa == null)
				phuongxa = "";
			khBean.setPhuongxa(phuongxa);

			String thanhthinongthonId = util.antiSQLInspection(request.getParameter("thanhthinongthonId"));
			if (thanhthinongthonId.trim().equals(""))
				thanhthinongthonId = null;
			khBean.setThanhthinongthonId(thanhthinongthonId);

			String songayno = util.antiSQLInspection(request.getParameter("songayno"));
			if (songayno == null)
				songayno = "0";
			khBean.setSongayno(Double.parseDouble(songayno.replace(",","").trim()));

			String sotienno = util.antiSQLInspection(request.getParameter("sotienno"));
			if (sotienno == null)
				sotienno = "0";
			khBean.setSotienno(Double.parseDouble(sotienno.replace(",","").trim()));

			String mahd = util.antiSQLInspection(request.getParameter("mahd"));
			if (mahd == null)
				mahd = "";
			khBean.setMahd(mahd);

			String hinhthuctt = util.antiSQLInspection(request.getParameter("hinhthuctt"));
			if (hinhthuctt == null)
				hinhthuctt = "";
			khBean.setHinhthucTT(hinhthuctt);

			String loai = util.antiSQLInspection(request.getParameter("loai"));
			if (loai == null)
				loai = "";

			String nguoimuahang = util.antiSQLInspection(request.getParameter("nguoimuahang"))==null?"":util.antiSQLInspection(request.getParameter("nguoimuahang").trim());
			khBean.setNguoimuahang(nguoimuahang);
			String tencuahieu = util.antiSQLInspection(request.getParameter("tencuahieu"))==null?"":util.antiSQLInspection(request.getParameter("tencuahieu").trim());
			khBean.setTencuahieu(tencuahieu);
			String apto = util.antiSQLInspection(request.getParameter("apto"))==null?"":util.antiSQLInspection(request.getParameter("apto").trim());
			khBean.setApto(apto);
			String sonha = util.antiSQLInspection(request.getParameter("sonha"))==null?"":util.antiSQLInspection(request.getParameter("sonha").trim());
			khBean.setSonha(sonha);
			String tenduong = util.antiSQLInspection(request.getParameter("tenduong"))==null?"":util.antiSQLInspection(request.getParameter("tenduong").trim());
			khBean.setTenduong(tenduong);
			String vochong = util.antiSQLInspection(request.getParameter("vochong"))==null?"":util.antiSQLInspection(request.getParameter("vochong").trim());
			khBean.setVochong(vochong);
			String ngsinh_vochong = util.antiSQLInspection(request.getParameter("ngsinhvc"))==null?"":util.antiSQLInspection(request.getParameter("ngsinhvc").trim());
			khBean.setNgsinh_vochong(ngsinh_vochong);
			String con1 = util.antiSQLInspection(request.getParameter("con1"))==null?"":util.antiSQLInspection(request.getParameter("con1").trim());
			khBean.setCon1(con1);
			String ngsinh_con1 = util.antiSQLInspection(request.getParameter("ngsinhc1"))==null?"":util.antiSQLInspection(request.getParameter("ngsinhc1").trim());
			khBean.setNgsinh_con1(ngsinh_con1);
			String con2 = util.antiSQLInspection(request.getParameter("con2"))==null?"":util.antiSQLInspection(request.getParameter("con2").trim());
			khBean.setCon2(con2);
			String ngsinh_con2 = util.antiSQLInspection(request.getParameter("ngsinhc2"))==null?"":util.antiSQLInspection(request.getParameter("ngsinhc2").trim());
			khBean.setNgsinh_con2(ngsinh_con2);
			String ghichu = util.antiSQLInspection(request.getParameter("ghichu"))==null?"":util.antiSQLInspection(request.getParameter("ghichu").trim());
			khBean.setGhichu(ghichu);
			String taitro = util.antiSQLInspection(request.getParameter("taitro"))==null?"":util.antiSQLInspection(request.getParameter("taitro").trim());
			khBean.setTaitro(taitro);
			String ngaytaitro = util.antiSQLInspection(request.getParameter("ngaytaitro"))==null?"":util.antiSQLInspection(request.getParameter("ngaytaitro").trim());
			khBean.setNgaytaitro(ngaytaitro);
			//System.out.println("Ngay tai tro: "+ngaytaitro);

			String khachhangId = util.antiSQLInspection(request.getParameter("khachhangId"));
			if (khachhangId == null)
				khachhangId = "";
			khBean.setKhachhangId(khachhangId);

			String[] tbhId = util.antiSQLInspection_Array(request.getParameterValues("tbhId"));
			String str = "";
			if (tbhId != null)
			{
				for (int i = 0; i < tbhId.length; i++)
					str += tbhId[i] + ",";
				if (str.length() > 0)
					str = str.substring(0, str.length() - 1);
			}
			khBean.setTbhId(str);

			String[] ddkdId = util.antiSQLInspection_Array(request.getParameterValues("ddkdId"));
			str = "";
			if (ddkdId != null)
			{

				for (int i = 0; i < ddkdId.length; i++)
				{
					str += ddkdId[i] + ",";
				}
				if (str.length() > 0)
					str = str.substring(0, str.length() - 1);
			}
			khBean.setDdkdId(str);
			
			//(HC) nếu không có chọn NVBH thì ko cho bán NPP khác
			str = ""; 
			if (khBean.getDdkdId().trim().length() >0)
			{
				String[] nppBanChungId = util.antiSQLInspection_Array(request.getParameterValues("nppBanChungId"));

				if (nppBanChungId != null)
				{
					for (int i = 0; i < nppBanChungId.length; i++)
					{
						str += nppBanChungId[i] + ",";
					}
					if (str.length() > 0)
						str = str.substring(0, str.length() - 1);
					khBean.setNppBanChungId(str);
				}
			}
			//System.out.println("alobcdef nppBanChungId =" + khBean.getNppBanChungId() );

			String[] nvgnId = util.antiSQLInspection_Array(request.getParameterValues("nvgnTen"));
			str="";
			if (nvgnId != null)
			{
				for (int i = 0; i < nvgnId.length; i++)
					str += nvgnId[i] + ",";
				if (str.length() > 0)
					str = str.substring(0, str.length() - 1);
			}
			khBean.setNvgnId(str);

			String[] nkh_khIds = util.antiSQLInspection_Array(request.getParameterValues("nkh_khList"));
			khBean.setNkh_KhIds(nkh_khIds);

			String ngaysua = getDateTime();
			khBean.setNgaysua(ngaysua);
			
			
			String[] arrAnhupload=new String [5];
			
			String anhupload1 = util.antiSQLInspection(request.getParameter("anhupload1"))==null?""
					:request.getParameter("anhupload1");
			
			
			
			String anhupload2 = util.antiSQLInspection(request.getParameter("anhupload2"))==null?""
					: request.getParameter("anhupload2").trim();
			
			String anhupload3 = util.antiSQLInspection(request.getParameter("anhupload3"))==null?""
					:request.getParameter("anhupload3").trim();
			
			String anhupload4 = util.antiSQLInspection(request.getParameter("anhupload4"))==null?""
					:request.getParameter("anhupload4").trim();
			arrAnhupload[1]=anhupload1;
			arrAnhupload[2]=anhupload2;
			arrAnhupload[3]=anhupload3;
			arrAnhupload[4]=anhupload4;
			//System.out.println("anhupload1:-----------"+anhupload1);
			
			khBean.setAnhuploai(arrAnhupload);

			String action = util.antiSQLInspection(request.getParameter("action"));
			
			/// chiết khấu thêm theo sp 
			int c =0;
			String[] spIds = util.antiSQLInspection_Array(request.getParameterValues("spIds"));
			String[] chietkhauSp = util.antiSQLInspection_Array(request.getParameterValues("chietkhauSp"));
			String[] ctckIds = util.antiSQLInspection_Array(request.getParameterValues("ctckIds"));


			if (spIds != null )
				c = spIds.length;

			String pageX = util.antiSQLInspection(request.getParameter("pageX"));
			String pageY = util.antiSQLInspection(request.getParameter("pageY"));
			if (action.equals("Xoa"))
			{
				c --;
				int xoaId = (int)Math.round(geso.dms.center.util.Utility.parseDouble(util.antiSQLInspection(request.getParameter("xoaId"))));
				khBean.setSpIds(xoa1Dong(spIds,xoaId));
				khBean.setChietkhauSp(xoa1Dong(chietkhauSp,xoaId));
				khBean.setCtckIds(xoa1Dong(ctckIds,xoaId));
				khBean.setPageX((int)Math.round(geso.dms.center.util.Utility.parseDouble(pageX) ) );
				khBean.setPageY((int)Math.round(geso.dms.center.util.Utility.parseDouble(pageY)));

			}
			else if (action.equals("addThem"))
			{
				c++;
				khBean.setSpIds(addThem1DongNull(spIds));
				khBean.setChietkhauSp(addThem1DongNull(chietkhauSp));
				khBean.setCtckIds(addThem1DongNull(ctckIds));

				khBean.setPageX((int)Math.round(geso.dms.center.util.Utility.parseDouble(pageX) ) );
				khBean.setPageY((int)Math.round(geso.dms.center.util.Utility.parseDouble(pageY)));
			} 
			else
			{
				khBean.setSpIds(spIds);
				khBean.setChietkhauSp(chietkhauSp);
				khBean.setCtckIds(ctckIds);
			}
			
			khBean.setCount(c);
			
			boolean error = false;

			if (kbhId.equals("") )
			{
				khBean.setMessage("Vui lòng chọn kênh bán hàng.");
				error = true;
			}

			if (khTen.trim().length() <= 0)
			{
				khBean.setMessage("Vui lòng nhập tên khách hàng.");
				error = true;
			}

			if (tpId.trim().length() <= 0)
			{
				khBean.setMessage("Vui lòng chọn tỉnh/thành phố.");
				error = true;
			}

			if ( tpId.trim().length() != 0 && qhId.trim().length() == 0)
			{
				khBean.setMessage("Vui lòng chọn quận/huyện.");
				error = true;
			}

			if (khoId.trim().length() ==0)
			{
				khBean.setMessage("Vui lòng chọn kho");
				error = true;
			}

			if (!error)
			{
				if (action.equals("save"))
				{
					if (id == null)
					{						
						if (!(khBean.CreateKh(request)))
						{
							khBean.createRS();
							session.setAttribute("khBean", khBean);
							String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangNew.jsp";
							response.sendRedirect(nextJSP);
						} 
						else
						{
							IKhachhangList obj = new KhachhangList();
							obj.setView(view);
							obj.setUserId(userId);
							obj.init("");
							session.setAttribute("obj", obj);
							String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHang.jsp";
							response.sendRedirect(nextJSP);
						}
					} 
					else
					{
						if (!(khBean.UpdateKh(request)))
						{
							khBean.createRS();
							session.setAttribute("khBean", khBean);
							String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangUpdate.jsp";
							response.sendRedirect(nextJSP);
						}
						else
						{
							IKhachhangList obj = new KhachhangList();
							obj.setUserId(userId);
							obj.setView(view);
							obj.init("");
							session.setAttribute("obj", obj);
							String nextJSP = request.getContextPath() + "/pages/Distributor/KhachHang.jsp";
							response.sendRedirect(nextJSP);
						}
					}
				} 
				else
				{
					khBean.setUserId(userId);
					khBean.createRS();
					session.setAttribute("khBean", khBean);

					String nextJSP;
					if (id == null)
					{
						nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangNew.jsp";
					} 
					else
					{
						nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangUpdate.jsp";
					}
					response.sendRedirect(nextJSP);
				}
			} 
			else
			{
				khBean.setUserId(userId);
				khBean.createRS();
				session.setAttribute("khBean", khBean);
				String nextJSP;
				if (id == null)
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangNew.jsp";
				} 
				else
				{
					nextJSP = request.getContextPath() + "/pages/Distributor/KhachHangUpdate.jsp";
				}
				
				response.sendRedirect(nextJSP);
			}
		}
	}

	
	static String getBase64EncodedCipherText(String cipherText) {
	    byte[] cText = cipherText.getBytes();
	    // return an ISO-8859-1 encoded String
	    return Base64.getEncoder().encodeToString(cText);
	}

	static String getBase64DecodedCipherText(String encodedCipherText) throws IOException {
	    return new String((Base64.getDecoder().decode(encodedCipherText)));
	}
	
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public String[] addThem1DongNull(String[]a )
	{
		if (a == null)
		{
			return  new String[1];
		}
		String[] kq = new String[(a.length +1)];
		for(int i = 0 ; i < a.length; i++)
		{
			kq[i] = a[i];
		}
		return kq;
	}
	
	public String[] xoa1Dong(String[]a,int dong )
	{
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0 ; i < a.length; i++)
		{
			if (i !=  dong)
				result.add( a[i] == null ? "" : a[i]);
		}		
		
		String[] str = result.toArray(new String[result.size()]);
		
		return  str;
	}
}
