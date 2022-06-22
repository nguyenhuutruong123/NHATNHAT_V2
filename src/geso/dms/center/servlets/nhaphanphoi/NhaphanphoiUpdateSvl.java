package geso.dms.center.servlets.nhaphanphoi;

import geso.dms.center.beans.nhaphanphoi.imp.*;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.beans.nhaphanphoi.*;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class NhaphanphoiUpdateSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	PrintWriter out;

	public NhaphanphoiUpdateSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		INhaphanphoi nppBean;

		// this.out = response.getWriter();
		Utility util = new Utility();
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
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		System.out.println(userId);
		if (userId.length() == 0)
			userId = util.antiSQLInspection(request.getParameter("userId"));
		String id = util.getId(querystring);
		nppBean = new Nhaphanphoi(id, "");
		nppBean.setUserId(userId);
		
		session.setAttribute("nppBean", nppBean);
		
		  String update = util.antiSQLInspection(request.getParameter("update"));
	       if(update==null)
	    	   update="";


	       String display = util.antiSQLInspection(request.getParameter("display"));
	       if(display==null)
	    	   display="";
	     
	       String nextJSP = "";
	       if(update.trim().length()>0)
	       {
	    	   nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoiUpdate.jsp";
	   		if (nppBean.getIsKhachhang().equals("1"))
	   			nextJSP = request.getContextPath() + "/pages/Center/KhachHangETCUpdate.jsp";
	       }
		       if(display.trim().length()>0)
	       {
	    	   nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoiDisplay.jsp";
	   		if (nppBean.getIsKhachhang().equals("1"))
	   			nextJSP = request.getContextPath() + "/pages/Center/KhachHangETCDisplay.jsp";
	       }
	    	  
		
		
		
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		INhaphanphoi nppBean;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		this.out = response.getWriter();

		Utility util = new Utility();

		String id = util.antiSQLInspection(request.getParameter("id"));
		if (id == null) {
			nppBean = new Nhaphanphoi("", request.getParameter("isKH"));
		} else {
			nppBean = new Nhaphanphoi(id, request.getParameter("isKH"));
		}

		String userId = util.antiSQLInspection(request.getParameter("userId"));
		nppBean.setUserId(userId);

		
		String quanlySellOut = util.antiSQLInspection(request.getParameter("quanlySellOut"));
		if (quanlySellOut == null)
			quanlySellOut = "0";
		else
			quanlySellOut = "1";
		nppBean.setQuanlySellOut(quanlySellOut);
		
		String tyleOutIn = util.antiSQLInspection(request.getParameter("tyleOutIn"));
		if (tyleOutIn == null)
			tyleOutIn = "0";
		nppBean.setTyleOutIn(tyleOutIn);
		
		
		String nppTen = util.antiSQLInspection(request.getParameter("nppTen"));
		if (nppTen == null)
			nppTen = "";
		nppBean.setTen(nppTen);

		String b2b = util.antiSQLInspection(request.getParameter("b2b"));
		if (b2b == null)
			b2b = "";
		nppBean.setB2b(b2b);

		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));
		if (trangthai == null)
			trangthai = "0";
		else
			trangthai = "1";
		nppBean.setTrangthai(trangthai);

		String loaicongno = util.antiSQLInspection(request.getParameter("loaicongno"));
		if (loaicongno == null)
			loaicongno = "0";
		nppBean.setLoaicongno(loaicongno);

		String prisec = util.antiSQLInspection(request.getParameter("prisec"));
		if (prisec == null)
			prisec = "0";
		else
			prisec = "1";
		nppBean.setPriSec(prisec);

		String diachi = util.antiSQLInspection(request.getParameter("DiaChi"));
		if (diachi == null)
			diachi = "";
		nppBean.setDiachi(diachi);

		String diachixhd = util.antiSQLInspection(request.getParameter("diachixhd"));
		if (diachixhd == null)
			diachixhd = "";
		nppBean.setDiaChiXuatHoaDon(diachixhd);

		String masothue = util.antiSQLInspection(request.getParameter("masothue"));
		if (masothue == null)
			masothue = "";
		nppBean.setMaSoThue(masothue);

		String khottid = util.antiSQLInspection(request.getParameter("khottid"));
		if (khottid == null)
			khottid = "";
		nppBean.setKhoTT(khottid);

		String tpId = util.antiSQLInspection(request.getParameter("tpId"));
		if (tpId == null)
			tpId = "";
		nppBean.setTpId(tpId);

		String qhId = util.antiSQLInspection(request.getParameter("qhId"));
		if (qhId == null)
			qhId = "";
		nppBean.setQhId(qhId);

		String ma = util.antiSQLInspection(request.getParameter("maSAP"));
		if (ma == null)
			ma = "";
		nppBean.setMaSAP(ma);

		String dienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
		if (dienthoai == null)
			dienthoai = "";
		nppBean.setSodienthoai(dienthoai);

		String kvId = util.antiSQLInspection(request.getParameter("kvId"));
		if (kvId == null)
			kvId = "";
		nppBean.setKvId(kvId);

		String tennguoidaidien = util.antiSQLInspection(request.getParameter("ddthd"));
		if (tennguoidaidien == null)
			tennguoidaidien = "";
		nppBean.setTenNguoiDaiDien(tennguoidaidien);

		String fax = util.antiSQLInspection(request.getParameter("fax"));
		if (fax == null)
			fax = "";
		nppBean.setFAX(fax);

		String email = util.antiSQLInspection(request.getParameter("email"));
		if (email == null)
			email = "";
		nppBean.setEmail(email);

		String httt = util.antiSQLInspection(request.getParameter("httt"));
		if (httt == null)
			httt = "";
		nppBean.setHinhThucThanhToan(httt);

		String nganhang = util.antiSQLInspection(request.getParameter("nganhang"));
		if (nganhang == null)
			nganhang = "";
		nppBean.setNganHang(nganhang);

		String sotaikhoan = util.antiSQLInspection(request.getParameter("sotaikhoan"));
		if (sotaikhoan == null)
			sotaikhoan = "";
		nppBean.setSoTK(sotaikhoan);

		String tenkyhd = util.antiSQLInspection(request.getParameter("tenkyhd"));
		if (tenkyhd == null)
			tenkyhd = "";
		nppBean.setTenKyHd(tenkyhd);

		String gsbhId = util.antiSQLInspection(request.getParameter("gsbhId"));
		if (gsbhId == null)
			gsbhId = "";
		nppBean.setGsbhId(gsbhId);

		String manx = util
				.antiSQLInspection(request.getParameter("manx") == null ? "" : request.getParameter("manx").trim());
		nppBean.setMaNX(manx);

		String makho = util
				.antiSQLInspection(request.getParameter("makho") == null ? "" : request.getParameter("makho").trim());
		nppBean.setMaKho(makho);

		String hanmucdoanhthu = util.antiSQLInspection(request.getParameter("hanmucdoanhthu") == null ? ""
				: request.getParameter("hanmucdoanhthu").trim().replaceAll(",", ""));
		nppBean.setHanmucdoanhthu(hanmucdoanhthu);

		String loaiNPP = request.getParameter("loaiNPP");
		System.out.println("loaiNPP: " + loaiNPP);
		if (loaiNPP == null)
			loaiNPP = "0";
		nppBean.setLoaiNPP(loaiNPP);

		String tructhuocId = request.getParameter("tructhuocId");
		System.out.println("tructhuocId: " + tructhuocId);
		//System.out.println("tructhuocIdHH: " + tructhuocId);
		if (tructhuocId == null)
			tructhuocId = "";
		nppBean.setTructhuocId(tructhuocId);
		
		String idcapcn = request.getParameter("idcapcn");
		
		System.out.println("idcapcn: " + idcapcn);
		if (idcapcn == null)
			idcapcn = "";
		nppBean.setCapCnId(idcapcn);

		String nvbhId = request.getParameter("nvbhId");
		if (nvbhId == null)
			nvbhId = "";
		nppBean.setNvbhId(nvbhId);

		String kyhieuhd = util.antiSQLInspection(request.getParameter("kyhieuhd"));
		if (kyhieuhd == null)
			kyhieuhd = "";
		nppBean.setKyhieuHD(kyhieuhd);

		String soHDTu = util.antiSQLInspection(request.getParameter("soHDTu"));
		if (soHDTu == null)
			soHDTu = "";
		nppBean.setSoHDTu(soHDTu);

		String soHDDen = util.antiSQLInspection(request.getParameter("soHDDen"));
		if (soHDDen == null)
			soHDDen = "";
		nppBean.setSoHDDen(soHDDen);

		String chietkhau = util.antiSQLInspection(request.getParameter("chietkhau"));
		if (chietkhau == null)
			chietkhau = "";
		nppBean.setChietkhau(chietkhau);

		String xuattaikho = util.antiSQLInspection(request.getParameter("xuattaikho"));
		if (xuattaikho == null)
			xuattaikho = "";
		nppBean.setXuattaikho(xuattaikho);

		String tuxuatETC = request.getParameter("tuxuatETC");
		if (tuxuatETC == null)
			tuxuatETC = "0";
		nppBean.setTuxuatkhoETC(tuxuatETC);
		System.out.println("TU XUAT ETC:::::: " + tuxuatETC);

		String tutaohoadon = request.getParameter("tutaohoadon");
		if (tutaohoadon == null)
			tutaohoadon = "0";
		nppBean.setTutaohoadonOTC(tutaohoadon);

		String dungchungkenh = request.getParameter("dungchungkenh");
		if (dungchungkenh == null)
			dungchungkenh = "0";
		nppBean.setDungchungkenh(dungchungkenh);

		String loaicn = request.getParameter("loaicn");
		if (loaicn == null)
			loaicn = "";
		nppBean.setLoaiCN(loaicn);

		String hanmucno = request.getParameter("hanmucno");
		if (hanmucno == null)
			hanmucno = "";
		nppBean.setHanmucno(hanmucno.replace("'", ""));

		String CMTND = request.getParameter("cmnd");
		if (CMTND == null)
			CMTND = "";
		nppBean.setCMTND(CMTND);

		String thukho = request.getParameter("tenthukho");
		if (thukho == null)
			thukho = "";
		nppBean.setThuKho(thukho);

		String tansuatDathang = util.antiSQLInspection(request.getParameter("tansuatDathang"));
		if (tansuatDathang == null)
			tansuatDathang = "";
		nppBean.setTansuatDathang(tansuatDathang);

		String tonkhoantoan = util.antiSQLInspection(request.getParameter("tonkhoantoan"));
		if (tonkhoantoan == null)
			tonkhoantoan = "";
		nppBean.setTonkhoAntoan(tonkhoantoan);

		String tonkhotoida = util.antiSQLInspection(request.getParameter("tonkhotoida"));
		if (tonkhotoida == null)
			tonkhotoida = "";
		nppBean.setTonkhotoida(tonkhotoida);

		String ptchietkhau = util.antiSQLInspection(request.getParameter("ptchietkhau"));
		if (ptchietkhau == null)
			ptchietkhau = "";
		nppBean.setPTChietkhau(ptchietkhau);

		//////// ETC
		String chucuahieu = util.antiSQLInspection(request.getParameter("chucuahieu"));
		if (chucuahieu == null)
			chucuahieu = "";
		nppBean.setChucuahieu(chucuahieu);

		String tencuahieu = util.antiSQLInspection(request.getParameter("tencuahieu"));
		if (tencuahieu == null)
			tencuahieu = "";
		nppBean.setTencuahieu(tencuahieu);

		String thanhthinongthonId = util.antiSQLInspection(request.getParameter("thanhthinongthonId"));
		if (thanhthinongthonId == null)
			thanhthinongthonId = "";
		nppBean.setThanhthinongthonId(thanhthinongthonId);

		String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
		if (kbhId == null)
			kbhId = "";
		nppBean.setKbhId(kbhId);

		String phuongxa = util.antiSQLInspection(request.getParameter("phuongxa"));
		if (phuongxa == null)
			phuongxa = "";
		nppBean.setPhuongxa(phuongxa);

		String didong = util.antiSQLInspection(request.getParameter("didong"));
		if (didong == null)
			didong = "";
		nppBean.setDidong(didong);

		String xuatkhau = util.antiSQLInspection(request.getParameter("xuatkhau"));
		if (xuatkhau == null)
			xuatkhau = "";
		nppBean.setXuatkhau(xuatkhau);

		String hopdong = util.antiSQLInspection(request.getParameter("hopdong"));
		if (hopdong == null)
			hopdong = "";
		nppBean.setHopdong(hopdong);

		String ngaysinh = util.antiSQLInspection(request.getParameter("ngaysinh"));
		if (ngaysinh == null)
			ngaysinh = "";
		nppBean.setNgaysinh(ngaysinh);

		double sotienno = Utility.parseDouble(util.antiSQLInspection(request.getParameter("sotienno")));
		nppBean.setSotienno(sotienno);

		double songayno = Utility.parseDouble(util.antiSQLInspection(request.getParameter("songayno")));
		nppBean.setSongayno(songayno + "");

		String hchId = util.antiSQLInspection(request.getParameter("hchId"));
		if (hchId == null)
			hchId = "";
		nppBean.setHchId(hchId);

		String vtchId = util.antiSQLInspection(request.getParameter("vtchId"));
		if (vtchId == null)
			vtchId = "";
		nppBean.setVtId(vtchId);

		String lchId = util.antiSQLInspection(request.getParameter("lchId"));
		if (lchId == null)
			lchId = "";
		nppBean.setLchId(lchId);

		// Địa bàn
		
		//Phat_a.tuan
		String diabanId = util.antiSQLInspection(request.getParameter("diabanId"));
		if (diabanId == null)
			diabanId = "";
		nppBean.setDiabanId(diabanId);
		
		String action = request.getParameter("action");
		boolean fail = true;
		String nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoi.jsp";

		if (action.equals("save")) {
			if (id == null) {
				boolean error = checkMaDMS(ma).equals("") ? false : true;
				nppBean.setMessage(checkMaDMS(ma));
				if (error || !(nppBean.CreateNpp(request))) 
					nextJSP = (nppBean.getIsKhachhang().equals("1")) ? request.getContextPath() + "/pages/Center/KhachHangETCNew.jsp" : request.getContextPath() + "/pages/Center/NhaPhanPhoiNew.jsp";
				else fail = false;
			} else {
				if (!(nppBean.UpdateNpp(request))) {	
					nextJSP = (nppBean.getIsKhachhang().equals("1")) ? request.getContextPath() + "/pages/Center/KhachHangETCUpdate.jsp" : request.getContextPath() + "/pages/Center/NhaPhanPhoiUpdate.jsp";
				}else fail = false;
			}
			nppBean.setUserId(userId);
			session.setAttribute("nppBean", nppBean);
			if (!fail){
				INhaphanphoiList obj = new NhaphanphoiList();
				obj.setUserId(userId);
				session.setAttribute("obj", obj);
				obj.setIsKhachhang(request.getParameter("isKH"));
				obj.init("");
			}
		} else {
			
			nppBean.createRS();
			nppBean.setUserId(userId);
			session.setAttribute("nppBean", nppBean);
			if (id == null)
				nextJSP = (nppBean.getIsKhachhang().equals("1")) ? request.getContextPath() + "/pages/Center/KhachHangETCNew.jsp" : request.getContextPath() + "/pages/Center/NhaPhanPhoiNew.jsp";
			else nextJSP = (nppBean.getIsKhachhang().equals("1")) ? request.getContextPath() + "/pages/Center/KhachHangETCUpdate.jsp" : request.getContextPath() + "/pages/Center/NhaPhanPhoiUpdate.jsp";
		}
		response.sendRedirect(nextJSP);
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String checkMaDMS(String ma) {
		dbutils db = new dbutils();
		String msg= "";
		try {
			db.getConnection().setAutoCommit(false);
			if (ma.equals("")) 
				msg = "Vui lòng nhập Mã DMS";
				
			String query = "select count(*) as num from nhaphanphoi where ma = '"+ma+"'";
			ResultSet check_ma = db.get(query);
			check_ma.next();
			if (!check_ma.getString("num").equals("0")) {
				msg = "Mã DMS đã tồn tại";
			}
		}catch(Exception e) {
			e.printStackTrace();
			msg = "Lỗi trong quá trình kiểm tra mã DMS";
		}finally {
			db.shutDown();
		}
		return msg;
	}

}
