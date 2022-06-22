<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="geso.dms.center.db.sql.dbutils"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.text.NumberFormat"%>
<%
	String ctyId = (String) session.getAttribute("congtyId");
%>
<%
	String lspId = (String) session.getAttribute("lspId");
%>
<%
	String lhhId = (String) session.getAttribute("lhhId");
%>
<%
	String nccId = (String) session.getAttribute("nccId");
%>
<%
	String nccLoai = (String) session.getAttribute("nccLoai");
%>
<%
	String loaimh = (String) session.getAttribute("loaimh");
%>
<%
	String nhacungcapNK = (String) session.getAttribute("nhacungcapNK");
%>
<%
	String ngaymuahang = (String) session.getAttribute("ngaymuahang");
%>
<%
	dbutils db = new dbutils();
	try {
		NumberFormat formatter = new DecimalFormat("#,###,###.#####");
		if (lhhId == null)
			lhhId = "0"; //SP nhap kho
		if (lspId == null)
			lspId = "";
		if (nccLoai == null)
			nccLoai = "";

		String command = "";

		request.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		String query = (String) request.getQueryString();

		String view = query;
		query = new String(query.substring(query.indexOf("&letters=") + 9, query.length()).getBytes("UTF-8"),
				"UTF-8");
		query = URLDecoder.decode(query, "UTF-8").replace("+", " ");

		Utility Ult = new Utility();
		query = Ult.replaceAEIOU(query);

		System.out.println("NCC" + nccId);
		System.out.println("lhhId" + lhhId);

		if (view.indexOf("tenkh") >= 0) {
			command = "select pk_seq, ma +  '  ,  ' + ten as khten  from Erp_KhachHang where trangthai = '1'";
			System.out.println("du lieu  : " + command);
			ResultSet vt = db.get(command);
			while (vt.next()) {
				String khId = vt.getString("pk_seq");
				String khTen = vt.getString("khten");

				if (khId.toUpperCase().contains(query.toUpperCase())
						|| khTen.toUpperCase().contains(query.toUpperCase())) {
					out.println(khId + " -- " + khTen + " |" + "\n");
				}
			}
			vt.close();
		} else if (view.contains("phongbandntt")) {
			command = "select pk_seq, ma +  '  ,  ' + ten as dvtt  from ERP_DONVITHUCHIEN where trangthai = '1'";
			//System.out.println("du lieu  : "+command);
			ResultSet vt = db.get(command);
			while (vt.next()) {
				String khId = vt.getString("pk_seq");
				String khTen = vt.getString("dvtt");

				if (khId.toUpperCase().contains(query.toUpperCase())
						|| khTen.toUpperCase().contains(query.toUpperCase())) {
					out.println(khTen + " -- " + khId + " |" + "\n");
				}
			}
			vt.close();
		} else if (view.contains("kenhbhdntt")) {
			command = "select pk_seq, diengiai as dvtt  from KENHBANHANG where trangthai = '1'";
			//System.out.println("du lieu  : "+command);
			ResultSet vt = db.get(command);
			while (vt.next()) {
				String khId = vt.getString("pk_seq");
				String khTen = vt.getString("dvtt");

				if (khId.toUpperCase().contains(query.toUpperCase())
						|| khTen.toUpperCase().contains(query.toUpperCase())) {
					out.println(khTen + " -- " + khId + " |" + "\n");
				}
			}
			vt.close();
		} else if (view.contains("taikhoantthd")) {
			command = "select pk_seq, (SOHIEUTAIKHOAN + ', ' + TENTAIKHOAN) TAIKHOAN from ERP_TAIKHOANKT where trangthai = '1'";
			//System.out.println("du lieu  : "+command);
			ResultSet vt = db.get(command);
			while (vt.next()) {
				String khId = vt.getString("pk_seq");
				String khTen = vt.getString("TAIKHOAN");

				if (khId.toUpperCase().contains(query.toUpperCase())
						|| khTen.toUpperCase().contains(query.toUpperCase())) {
					out.println(khTen + " -- " + khId + " |" + "\n");
				}
			}
			vt.close();
		} else if (view.contains("doituongtthd")) {
			String Taikhoan_VAT = (String) session.getAttribute("Taikhoan_VAT");
			System.out.println("view : " + view);

			command = "select pk_seq, (SOHIEUTAIKHOAN + ', ' + TENTAIKHOAN) TAIKHOAN from ERP_TAIKHOANKT where trangthai = '1'";
			//System.out.println("du lieu  : "+command);
			ResultSet vt = db.get(command);
			while (vt.next()) {
				String khId = vt.getString("pk_seq");
				String khTen = vt.getString("TAIKHOAN");

				if (khId.toUpperCase().contains(query.toUpperCase())
						|| khTen.toUpperCase().contains(query.toUpperCase())) {
					out.println(khTen + " -- " + khId + " |" + "\n");
				}
			}
			vt.close();
		} else if (view.contains("donmuahang")) {
			System.out.println("dang vao don mua hang");
			if (nccLoai != null) {
				// neu la nha cung cap gia cong va hang hoa la san pham
				if (nccLoai.equals("100003") && lhhId.equals("0")) //nha cung cap nhan gia cong
				{

					command = "select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) "
							+ "then a.ma else a.ma end as ma, a.ma as ma,  " + " ( isnull(a.ten, '')  as ten , "
							+ " a.loaisanpham_fk, a.thuexuat, b.DONVI, 'NA' as nhomhang "
							+ "from  erp_SanPham a " + "left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ "
							+ "where (a.timkiem like '%" + query + "%' or a.ten like '%" + query
							+ "%') and  a.TRANGTHAI = '1' "
							+ "and a.pk_seq in ( select sanpham_fk from ERP_NHACUNGCAP_SPNHANGIACONG where ncc_fk = '"
							+ nccId + "' ) ";

				} else {
					if (lhhId.equals("0")) //San pham
					{
						System.out.println("loaimh " + loaimh);
						if (loaimh.equals("1")) // trong nước
						{
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date date = new Date();
							String today = "";
							if (ngaymuahang.trim().length() > 0) {
								today = ngaymuahang;
							} else {
								today = sdf.format(date);
							}
							
							command = " select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) \n"
									+ " then a.ma else a.ma end as ma, isnull(a.ma, a.ma) as ma, \n"
									+ " isnull(a.ten, '')   as ten , c.giamua, \n"
									+ " a.loaisanpham_fk, a.thuexuat, isnull(b.DONVI, 'NA') as donvi, 'NA' as nhomhang, \n"
									+ " null as mamarquet, null as tenmarquet \n"
									+ " from erp_SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ \n"
									+ " inner join erp_banggiamuancc_sanpham c on a.PK_SEQ = C.sanpham_fk \n"
									+ " where a.TRANGTHAI = '1' and (a.timkiem like '%" + query
									+ "%' or a.ten like '%" + query + "%' or a.ma like '%" + query
									+ "%')  and C.trangthai = '1' \n"
									+ " and c.BANGGIAMUA_FK in (select top(1) pk_seq from erp_banggiamuancc d inner join ERP_BANGGIAMUANCC_NCC e on d.PK_SEQ = e.BANGGIA_FK \n"
									+ " where D.trangthai = '1'  and D.daduyet = '1' and e.ncc_fk = " + nccId
									+ " order by d.tungay desc) \n" +

									" and a.PK_SEQ in (select distinct duyetsp.SANPHAM_FK from "
									+ "	ERP_DUYETNHACUNGCAP_SANPHAM duyetsp inner join ERP_DUYETNHACUNGCAP duyet on duyetsp.DUYETNHACUNGCAP_FK = duyet.PK_SEQ "
									+ " where  duyet.TRANGTHAI = 1 and duyet.TUNGAY <='" + today
									+ "' and duyet.DENNGAY>= '" + today + "' and duyet.NHACUNGCAP_FK ='" + nccId
									+ "') "
							        + "union \n "	
							        + " select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) \n"
									+ " then a.ma else a.ma end as ma, isnull(a.ma, a.ma) as ma, \n"
									+ " isnull(a.ten +' marquette: ' +isnull(m.MA,''), '')   as ten , c.giamua, \n"
									+ " a.loaisanpham_fk, a.thuexuat, isnull(b.DONVI, 'NA') as donvi, 'NA' as nhomhang, \n"
									+ " m.PK_SEQ as mamarquet, isnull(m.MA,'') as tenmarquet  \n"
									+ " from erp_SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ \n"
									+ " left join marquette m on m.SANPHAM_FK = a.PK_SEQ  \n"
									+ " inner join erp_banggiamuancc_sanpham c on a.PK_SEQ = C.sanpham_fk \n"
									+ " where a.TRANGTHAI = '1' and (a.timkiem like '%" + query
									+ "%' or a.ten like '%" + query + "%' or a.ma like '%" + query
									+ "%')  and C.trangthai = '1' \n"
									+ " and c.BANGGIAMUA_FK in (select top(1) pk_seq from erp_banggiamuancc d inner join ERP_BANGGIAMUANCC_NCC e on d.PK_SEQ = e.BANGGIA_FK \n"
									+ " where D.trangthai = '1'  and D.daduyet = '1' and e.ncc_fk = " + nccId
									+ " order by d.tungay desc) \n" +
	
									" and a.PK_SEQ in (select distinct duyetsp.SANPHAM_FK from "
									+ "	ERP_DUYETNHACUNGCAP_SANPHAM duyetsp inner join ERP_DUYETNHACUNGCAP duyet on duyetsp.DUYETNHACUNGCAP_FK = duyet.PK_SEQ "
									+ " where  duyet.TRANGTHAI = 1 and duyet.TUNGAY <='" + today
									+ "' and duyet.DENNGAY>= '" + today + "' and duyet.NHACUNGCAP_FK ='" + nccId
									+ "') "
									+ " and m.TUNGAY <='" + today + "' and m.DENNGAY>= '" + today + "'" ;
									
									
						} else //nhập khẩu
						{
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date date = new Date();
							String today = "";
							if (ngaymuahang.trim().length() > 0) {
								today = ngaymuahang;
							} else {
								today = sdf.format(date);
							}

							command = " select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) then a.ma else a.ma end as ma, isnull(a.ma, a.ma) as ma, \n"
									+ " isnull(a.ten +' marquette: ' +isnull(m.MA,''), '')   as ten, \n"
									+ " a.loaisanpham_fk, a.thuexuat, isnull(b.DONVI, 'NA') as donvi, 'NA' as nhomhang, \n"
									+ " m.PK_SEQ as mamarquet, isnull(m.MA,'') as tenmarquet \n"
									+ " from erp_SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ \n"
									+ " left join marquette m on m.SANPHAM_FK = a.PK_SEQ  \n"
									+ " where a.TRANGTHAI = '1' "
									+ " and (a.timkiem like '%" + query + "%' or a.ten like '%" + query + "%')"
									+ " and a.PK_SEQ in (select distinct d.SANPHAM_FK from "
									+ "	ERP_DUYETNHACUNGCAP_SANPHAM d inner join ERP_DUYETNHACUNGCAP e on d.DUYETNHACUNGCAP_FK = e.PK_SEQ "
									+ " where  e.TRANGTHAI = 1 and e.TUNGAY <='" + today + "' and e.DENNGAY>= '"
									+ today + "' and e.NHACUNGCAP_FK ='" + nhacungcapNK + "') "
									+ " and m.TUNGAY <='" + today + "' and m.DENNGAY>= '" + today + "'"
									+ "union " +

									// phần này để lấy các sản phẩm không idmarquette
									" select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) then a.ma else a.ma end as ma, isnull(a.ma, a.ma) as ma, \n"
									+ " isnull(a.ten, '')   as ten, \n"
									+ " a.loaisanpham_fk, a.thuexuat, isnull(b.DONVI, 'NA') as donvi, 'NA' as nhomhang, \n"
									+ " null as mamarquet, '' as tenmarquet \n" +

									" from erp_SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ \n"
									+ " where a.TRANGTHAI = '1' "
									+ " and (a.timkiem like '%" + query + "%' or a.ten like '%" + query + "%')"
									+ " and a.PK_SEQ in (select distinct d.SANPHAM_FK from "
									+ "	ERP_DUYETNHACUNGCAP_SANPHAM d inner join ERP_DUYETNHACUNGCAP e on d.DUYETNHACUNGCAP_FK = e.PK_SEQ "
									+ " where  e.TRANGTHAI = 1 and e.TUNGAY <='" + today + "' and e.DENNGAY>= '"
									+ today + "' and e.NHACUNGCAP_FK ='" + nhacungcapNK + "') ";

							System.out.println("command:" + command);
						}
					} else {
						if (lhhId.equals("1")) //Tai san co dinh
						{
							command = " select a.pk_seq, a.ma, a.ma as ma, a.diengiai as ten , isnull(c.donvi, 'NA') as donvi, isnull(b.ma, 'NA') as nhomhang "
									+ "	from erp_taisancodinh a left join erp_nhomtaisan b on a.NhomTaiSan_fk = b.pk_seq "
									+ "	left join DonViDoLuong c on a.dvt = c.pk_seq "
									+ "	where a.trangthai = '0' and a.congty_fk = " + ctyId
									+ " order by a.ma asc";
						} else if (lhhId.equals("2")) {
							/* command = "select a.pk_seq, a.ten as ma, a.diengiai as ten, '' as donvi, isnull(b.ten, 'NA') as nhomhang " +
									" from erp_nhomchiphi a left join  erp_trungtamchiphi b on a.ttchiphi_fk = b.pk_seq where a.trangthai = '1' and a.loai = '2' order by a.ten asc"; */

							command = " select a.pk_seq, a.ten as ma, a.ten as ma, a.diengiai as ten, '' as donvi, isnull(b.diengiai, 'NA') as nhomhang "
									+ " from TraphacoERP.dbo.erp_nhomchiphi a "
									+ " left join  TraphacoERP.dbo.erp_trungtamchiphi b on a.ttchiphi_fk = b.pk_seq "
									+ " where a.trangthai = '1' and a.loai = '2'  order by a.ten asc";
						} else if (lhhId.equals("3")) {
							command = " select a.pk_seq, a.MA as ma, a.MA as ma, a.diengiai as ten, isnull(a.DONVI, 'NA') as donvi, isnull(b.diengiai, 'NA') as nhomhang"
									+ " from TraphacoERP.dbo.ERP_CONGCUDUNGCU a"
									+ " left join  TraphacoERP.dbo.erp_trungtamchiphi b on a.TTCP_FK = b.pk_seq where a.trangthai = '0' and a.congty_fk = "
									+ ctyId + " order by a.MA asc";
						}
					}
				}
			}

			System.out.println("Lay san pham / vat tu / tai san: " + command);

			ResultSet sp = db.get(command);
			int j = 0;
			if (sp != null) {

				if (!lhhId.equals("0")) {
					while (sp.next()) {
						String maSP = Ult.replaceAEIOU(sp.getString("ma"));
						String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
						String ma = Ult.replaceAEIOU(sp.getString("ma"));

						if (maSP.toUpperCase().contains(query.toUpperCase())
								|| tenSP.toUpperCase().contains(query.toUpperCase())
								|| ma.toUpperCase().contains(query.toUpperCase())) {
							String tensp = sp.getString("ten");
							out.print("###" + sp.getString("ma") + " -- " + tensp + "- " + " ["
									+ sp.getString("donvi") + "] [" + sp.getString("nhomhang") + "] ["
									+ sp.getString("pk_seq") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						}
					}
					sp.close();
				} else {
					while (sp.next()) {
						String maSP = Ult.replaceAEIOU(sp.getString("ma"));
						String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
						String ma = Ult.replaceAEIOU(sp.getString("ma"));
						String giamua = "";
						if (loaimh.equals("1")) {
							giamua = formatter.format(sp.getDouble("giamua"));
						}
						String mamarquet = sp.getString("mamarquet");
						String tenmarquet = sp.getString("tenmarquet");

						String tensp = sp.getString("ten");
						out.print("###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
								+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq") + "] ["
								+ sp.getString("thuexuat") + "] [" + giamua + "] [" + mamarquet + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						System.out.println(
								"###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
										+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq")
										+ "] [" + sp.getString("thuexuat") + "] [" + giamua + "]|");
					}
					sp.close();
				}
			}
		} else if (view.contains("denghithanhtoan")) {
			command = " select a.pk_seq, a.ten as ma, a.ten as ma, a.diengiai as ten, '' as donvi, isnull(b.diengiai, 'NA') as nhomhang "
					+ " from TraphacoERP.dbo.erp_nhomchiphi a " 
					+ " left join  TraphacoERP.dbo.erp_trungtamchiphi b on a.ttchiphi_fk = b.pk_seq "
					+ " where a.trangthai = '1' and a.loai = '2'  order by a.ten asc";

			System.out.println("Lay san pham / vat tu / tai san: " + command);

			ResultSet sp = db.get(command);
			int j = 0;
			if (sp != null) {

				if (!lhhId.equals("0")) {
					while (sp.next()) {
						String maSP = Ult.replaceAEIOU(sp.getString("ma"));
						String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
						String ma = Ult.replaceAEIOU(sp.getString("ma"));

						if (maSP.toUpperCase().contains(query.toUpperCase())
								|| tenSP.toUpperCase().contains(query.toUpperCase())
								|| ma.toUpperCase().contains(query.toUpperCase())) {
							String tensp = sp.getString("ten");
							out.print("###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
									+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						}
					}
					sp.close();
				} else {
					while (sp.next()) {
						String maSP = Ult.replaceAEIOU(sp.getString("ma"));
						String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
						String ma = Ult.replaceAEIOU(sp.getString("ma"));
						String giamua = "";
						if (loaimh.equals("1")) {
							giamua = formatter.format(sp.getDouble("giamua"));
						}
						String tensp = sp.getString("ten");
						out.print("###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
								+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq") + "] ["
								+ sp.getString("thuexuat") + "] [" + giamua + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						System.out.println(
								"###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
										+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq")
										+ "] [" + sp.getString("thuexuat") + "] [" + giamua + "]|");
					}
					sp.close();
				}
			}
		}

		else if (view.contains("masothue")) {
			command = "  select mst + ' [ ' + NCC +' ] ' as nccTen " + " from MST_NCC ";
			//System.out.println("du lieu  : "+command);
			ResultSet ncc = db.get(command);
			while (ncc.next()) {
				String nccTen = "";
				nccTen = ncc.getString("nccTen");
				if (nccTen.toUpperCase().startsWith(query.toUpperCase())
						|| nccTen.toUpperCase().indexOf(query.toUpperCase()) >= 0) {
					out.println(nccTen + "|");
				}

			}
			ncc.close();
		} else {
			if (nccLoai != null) {
				// náº¿u lÃ  nhÃ  gia cÃ´ng vÃ   chá»n loáº¡i hÃ ng hÃ³a lÃ  sáº£n pháº©m 
				if (nccLoai.equals("100003") && lhhId.equals("0")) //nha cung cap nhan gia cong
				{

					command = "select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) then a.ma else a.ma end as ma, a.ma as ma,  "
							+ " ( isnull(a.ten, '')  as ten , "
							+ " a.loaisanpham_fk, a.thuexuat, b.DONVI, 'NA' as nhomhang "
							+ "from ERP_SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ "
							+ "where (a.timkiem like '%" + query + "%' or a.ten like '%" + query
							+ "%') and  a.TRANGTHAI = '1' and a.pk_seq in ( select sanpham_fk from ERP_NHACUNGCAP_SPNHANGIACONG where ncc_fk = '"
							+ nccId + "' ) ";

				} else {
					if (lhhId.equals("0")) //SÃ¡ÂºÂ£n phÃ¡ÂºÂ©m
					{
						if (loaimh.equals("1")) {
							command = " select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) then a.ma else a.ma end as ma, isnull(a.ma, a.ma) as ma, "
									+ " isnull(a.ten, '')   as ten , c.giamua,"
									+ " a.loaisanpham_fk, a.thuexuat, isnull(b.DONVI, 'NA') as donvi, 'NA' as nhomhang "
									+ " from ERP_SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ "
									+ " inner join erp_banggiamuancc_sanpham c on a.PK_SEQ = C.sanpham_fk "
									+ " where a.TRANGTHAI = '1' and (a.timkiem like '%" + query
									+ "%' or a.ten like '%" + query + "%')  and C.trangthai = '1' "
									+ " and c.BANGGIAMUA_FK in (select top(1) pk_seq from erp_banggiamuancc d inner join ERP_BANGGIAMUANCC_NCC e on d.PK_SEQ = e.BANGGIA_FK "
									+ " where D.trangthai = '1' and D.daduyet = '1' and e.ncc_fk = " + nccId
									+ " order by d.tungay desc) ";
						} else {
							command = " select top 30 a.pk_seq, case when ( len(ltrim(rtrim(a.ma))) <= 0 or ( a.ma is null ) ) then a.ma else a.ma end as ma, isnull(a.ma, a.ma) as ma, "
									+ " isnull(a.ten, '')   as ten, "
									+ " a.loaisanpham_fk, a.thuexuat, isnull(b.DONVI, 'NA') as donvi, 'NA' as nhomhang "
									+ " from SanPham a left join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ "
									+ " where a.TRANGTHAI = '1' and (a.timkiem like '%" + query
									+ "%' or a.ten like '%" + query + "%')";
						}
					} else {
						if (lhhId.equals("1")) //Tai san co dinh
						{
							command = " select a.pk_seq, a.ma, a.ma as ma, a.diengiai as ten , isnull(c.donvi, 'NA') as donvi, isnull(b.ma, 'NA') as nhomhang "
									+ "	from erp_taisancodinh a left join erp_nhomtaisan b on a.NhomTaiSan_fk = b.pk_seq "
									+ "	left join DonViDoLuong c on a.dvt = c.pk_seq "
									+ "	where a.trangthai = '0' order by a.ma asc";
						} else if (lhhId.equals("2")) {
							/* command = "select a.pk_seq, a.ten as ma, a.diengiai as ten, '' as donvi, isnull(b.ten, 'NA') as nhomhang " +
									" from erp_nhomchiphi a left join  erp_trungtamchiphi b on a.ttchiphi_fk = b.pk_seq where a.trangthai = '1' and a.loai = '2' order by a.ten asc"; */

							command = "select a.pk_seq, a.ten as ma, a.ten as ma, a.diengiai as ten, '' as donvi, isnull(b.diengiai, 'NA') as nhomhang "
									+ " from erp_nhomchiphi a left join  erp_trungtamchiphi b on a.ttchiphi_fk = b.pk_seq where a.trangthai = '1' and a.loai = '2' order by a.ten asc";
						} else if (lhhId.equals("3")) {
							command = " select a.pk_seq, a.MA as ma, a.MA as ma, a.diengiai as ten, isnull(a.DONVI, 'NA') as donvi, isnull(b.diengiai, 'NA') as nhomhang"
									+ " from ERP_CONGCUDUNGCU a"
									+ " left join  erp_trungtamchiphi b on a.TTCP_FK = b.pk_seq where a.trangthai = '0' order by a.MA asc";
						}
					}
				}
			}

			System.out.println("Lay san pham / vat tu / tai san: " + command);

			ResultSet sp = db.get(command);
			int j = 0;
			if (sp != null) {

				if (!lhhId.equals("0")) {
					while (sp.next()) {
						String maSP = Ult.replaceAEIOU(sp.getString("ma"));
						String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
						String ma = Ult.replaceAEIOU(sp.getString("ma"));

						if (maSP.toUpperCase().contains(query.toUpperCase())
								|| tenSP.toUpperCase().contains(query.toUpperCase())
								|| ma.toUpperCase().contains(query.toUpperCase())) {
							String tensp = sp.getString("ten");
							out.print("###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
									+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq") + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						}
					}
					sp.close();
				} else {
					while (sp.next()) {
						String maSP = Ult.replaceAEIOU(sp.getString("ma"));
						String tenSP = Ult.replaceAEIOU(sp.getString("ten"));
						String ma = Ult.replaceAEIOU(sp.getString("ma"));
						String giamua = "";
						if (loaimh.equals("1")) {
							giamua = formatter.format(sp.getDouble("giamua"));
						}
						String tensp = sp.getString("ten");
						out.print("###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
								+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq") + "] ["
								+ sp.getString("thuexuat") + "] [" + giamua + "]|"); //luu y: bat buoc phai co dau phan cach '|' o cuoi moi dong'
						System.out.println(
								"###" + sp.getString("ma") + " -- " + tensp + " [" + sp.getString("donvi")
										+ "] [" + sp.getString("nhomhang") + "] [" + sp.getString("pk_seq")
										+ "] [" + sp.getString("thuexuat") + "] [" + giamua + "]|");
					}
					sp.close();
				}
			}
		}
		db.shutDown();

	} catch (Exception ex) {
		ex.printStackTrace();
		System.out.println("Xay ra exception roi ban...");
	}
%>

