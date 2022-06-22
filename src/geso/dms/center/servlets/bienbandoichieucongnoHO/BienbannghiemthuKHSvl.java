package geso.dms.distributor.servlets.bienbannghiemthuNPP;

import geso.dms.center.beans.doctien.doctienrachu;
import geso.dms.center.beans.hoadontaichinh.IErpHoadontaichinhList;
import geso.dms.center.beans.hoadontaichinh.imp.ErpHoadontaichinhList;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinhList;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.HoadontaichinhList;
import geso.dms.distributor.beans.hoadontaichinhNPP.IErpHoadontaichinhNPPList;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
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

public class BienbannghiemthuKHSvl extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	public BienbannghiemthuKHSvl() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IHoadontaichinhList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();	    

		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);

		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);


		String lsxId = util.getId(querystring);
		obj = new HoadontaichinhList();

		String nppId = util.getIdNhapp(userId);

		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "0";
		obj.setLoaidonhang(loaidonhang);
		System.out.println("---LOAI DON HANG: " + loaidonhang);

		String type = request.getParameter("type");
		if(type == null)
			type = "";
		obj.setType(type);

		System.out.println("---TYPE: HoadontaichinhSvl " +type);

		if(type.equals("deleteNEW"))
		{
			GET_DATA_TO_CLIENT( request, response, action );
		}
		else
		{
			if(action.equals("chot"))
			{
				IHoadontaichinh hd = new Hoadontaichinh();
				hd.setUserId(userId);
				String msg = "";
				if(!hd.chot(lsxId, false))
					msg = hd.getMsg();

				obj.setMsg(msg);
			}
			else if(action.equals("delete"))  //DELETE SE GIONG HUY, SE MO LAI TRANG THAI DON HANG
			{
				String msg = this.Delete(lsxId, userId, nppId);
				obj.setMsg(msg);
			}
			else if(action.equals("huy"))
			{
				String msg = this.HuyHoaDon(lsxId, userId, nppId);
				obj.setMsg(msg);
			}


			obj.setUserId(userId);
			System.out.println("vao day ne sad------");
			obj.init("");



			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BienbannghiemthuKH.jsp";
			response.sendRedirect(nextJSP);
		}

	}


	private void GET_DATA_TO_CLIENT(HttpServletRequest request, HttpServletResponse response, String action)  throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String hoadonId =request.getParameter("hoadonId");
		String userId =request.getParameter("userId");	    
		Utility util = new Utility();
		dbutils db = new dbutils();
		String msg=util.Check_CK_DaHuong(hoadonId,userId,db);

		String myDATA="";
		if(msg.length()>0)
		{
			myDATA=" <p style='text-align:right;color:red;font-weight: bold' > " + msg+" </p> ";
		}

		myDATA +=
			"<table width='210' align='center' cellspacing='1' > " + 
			"<tr class='tbheader' > " +
			"	<td align='center' width='100px' style='font-size: 11px'>Xóa HĐ</td> " +
			"	<td align='center' width='100px' style='font-size: 11px'>Hủy HĐ</td> " +
			"</tr> </table> ";

		myDATA += "<div style='max-height:330px; overflow-x:hidden; overflow-y:auto; width: 100%;  ' > " +
		"<table width='200px' align='center' cellspacing='1' > ";
		myDATA +=
			"<tr style='background-color: #CCC;' > "+ 
			" <td width='100px' > <A href ='../../HoadontaichinhSvl?userId="+userId+"&delete="+hoadonId+"' > <img src='../images/Delete_Icon.png' alt='Xóa hóa đơn' title='Xóa hóa đơn' width='20' height='20' border=0 onclick=\"if(!confirm('Bạn có xóa hóa đơn này?\')) return false;\"></A>" +
			" </td>  " +
			" &nbsp; | &nbsp;  "+
			" <td width='100px' > <A href ='../../HoadontaichinhSvl?userId="+userId+"&huy="+hoadonId+"'><img src='../images/Delete20.png' alt='Hủy hóa đơn' title='Hủy hóa đơn' width='20' height='20' border=0 onclick=\"if(!confirm('Bạn có muốn hủy hóa đơn này?')) return false;\"></A>   " +
			" </td> "+  
			" </tr>  ";
		myDATA += " </table> </div> ";

		out.write(myDATA);
	}

	private String HuyHoaDon(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";		

		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("HOADON", lsxId, "NgayXuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;
		}
		try
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");
			db.getConnection().setAutoCommit(false);
			String query = "";			

			// Kiểm tra import =1 thì k cho hủy
			query = 
				" SELECT count(B.PK_SEQ) is_ThuTien " +
				" FROM ERP_THUTIENNPP_HOADON A INNER JOIN ERP_THUTIENNPP B ON A.THUTIENNPP_FK = B.PK_SEQ \n" +
				" INNER JOIN HOADON C ON A.HOADONNPP_FK = C.PK_SEQ AND A.LOAIHD = 0 \n" +
				" AND A.KHACHHANG_FK in \n"+
				" (SELECT PK_SEQ FROM KHACHHANG WHERE KBH_FK = 100025 and NPP_FK='" + nppId +"')  " +	
				" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = C.KHACHHANG_FK  \n" +
				" WHERE B.TRANGTHAI IN (0,1) AND C.PK_SEQ = "+lsxId + " AND C.KHACHHANG_FK = A.KHACHHANG_FK  \n";

			System.out.println(query);
			ResultSet rsKT = db.get(query);
			int is_Thutien = 0;

			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					is_Thutien =  rsKT.getInt("is_ThuTien");
				}
				rsKT.close();
			}

			if(is_Thutien > 0)
			{
				msg = "Hóa đơn này đã thu tiền. Bạn không được phép xóa/hủy hóa đơn ";
				db.getConnection().rollback();
				return msg;
			}


			//TU DONG XOA PHIEU XUAT KHO
			query = "select DDH_FK as donhang_fk,  " +
			"ISNULL( ( select Import from DONHANG where pk_seq = a.DDH_FK ), 0) as Import, " +
			"	( select npp_fk from HOADON where pk_seq = '" + lsxId + "' ) as nppId,	" +
			"( select PXK_FK from PHIEUXUATKHO_DONHANG where donhang_fk = a.DDH_FK and PXK_FK in ( select pk_seq from PHIEUXUATKHO where trangthai != '2' and npp_fk = ( select npp_fk from HOADON where pk_seq = '" + lsxId + "' ) ) ) as soPXK  " +
			"from HOADON_DDH a where hoadon_fk = '" + lsxId + "'";
			System.out.println("---3.HUY HOA DON: " + query );

			String donhang_fk = "";
			String Import = "";
			String pxkId = "";

			ResultSet rs = db.get(query);
			{
				while(rs.next())
				{
					donhang_fk = rs.getString("donhang_fk");
					Import = rs.getString("Import");
					nppId = rs.getString("nppId");

					if(Import.equals("0"))
					{
						pxkId = rs.getString("soPXK");

						//CHECK KHOA SO THANG



						/*query = "update phieuxuatkho set trangthai = '2' where pk_seq = '" + pxkId + "' and TrangThai=1 ";
						if( db.updateReturnInt(query) <= 0 )
						{
							rs.close();
							msg = "Không thể hủy " + query;
							db.getConnection().rollback();
							return msg;
						}*/

						query = "select count(*) as sl from  phieuxuatkho  where pk_seq = " + pxkId + "  ";
						ResultSet rspxk=db.get(query);
						rspxk.next();
						if(rspxk.getInt("sl")!=0)
						{
							rspxk.close();
							msg = "Vui lòng xóa phiếu xuất kho trước khi xóa hóa đơn" ;
							db.getConnection().rollback();
						}
						rspxk.close();

						//MO LAI TRANG THAI DON HANG
						query = "Update DONHANG set trangthai = '0',checkkho=1, daxuathoadon = '0' where pk_seq = '" + donhang_fk + "' and TrangThai=1 ";
						if( db.updateReturnInt(query) <= 0 )
						{
							rs.close();
							msg = "Không thể hủy đơn hàng " + query;
							db.getConnection().rollback();
							return msg;
						}

						//XOA PHIEU CAN TRU NEU CO
						query = "UPDATE CANTRUCONGNO set trangthai = '2' where pk_seq in ( select CANTRUCONGNO_FK from CANTRUCONGNO_HOADON where hoadon_fk = '" + lsxId + "' ) ";
						if( !db.update(query) )
						{
							rs.close();
							msg = "Không thể hủy CANTRUCONGNO " + query;
							db.getConnection().rollback();
							return msg;
						}
					}
					else
					{
						rs.close();
						msg = "Không thể hủy hóa đơn tự IMPORT vào hệ thống ";
						db.getConnection().rollback();
						return msg;
					}
				}
				rs.close();
			}


			if(Import.equals("0")) {

				query=" SELECT ddh_fk FROM HOADON_DDH WHERE HOADON_FK="+lsxId;

				ResultSet rshd=db.get(query);
				int count=0;
				String ddhid="";
				while(rshd.next()){
					ddhid=rshd.getString("ddh_fk");
					count++;
				}
				rshd.close();
				if(count >1){
					db.getConnection().rollback();
					return "Vui lòng báo Admin để được trợ giúp, hóa đơn không hợp lệ để hủy, số chứng từ:"+lsxId;
				}


				query=" DELETE DONHANG_SANPHAM_CHITIET WHERE DONHANG_FK="+ddhid;
				if(!db.update(query)){
					db.getConnection().rollback();
					return "Không thể thực hiện dòng lệnh : "+ ddhid;

				}

				query=" DELETE DONHANG_CTKM_TRAKM_CHITIET where DONHANG_FK="+ddhid;
				if(!db.update(query)){
					db.getConnection().rollback();
					return "Không thể thực hiện dòng lệnh : "+ ddhid;

				}



				query=  " SELECT PK_SEQ,LOAIHOADON FROM  HOADON  "+
				" where   TRANGTHAI not in (3,5) and   pk_seq in (select hoadon_fk from HOADON_DDH where DDH_FK in "+ 
				" (select DDH_FK from HOADON_DDH where HOADON_FK = "+lsxId+"))  ";
				ResultSet rshoadon=db.get(query);
				while(rshoadon.next()){

					String hoadon_fk=rshoadon.getString("PK_SEQ");

					query=  " SELECT a.ngayxuathd, A.KHO_FK, A.KBH_FK, A.NPP_FK, SP.PK_SEQ as sanpham_fk, case when  B.SOLO=' ' then 'NA' else B.SOLO end SOLO, SUM(B.SOLUONG) AS SOLUONG,case  when B.NGAYHETHAN=' ' then '2030-12-31' else B.ngayhethan end ngayhethan, NULL AS SCHEME "+   
					" ,ISNULL(B.NGAYNHAPKHO,'') AS NGAYNHAPKHO   "+
					" FROM HOADON A INNER JOIN HOADON_SP_CHITIET B ON A.PK_SEQ = B.HOADON_FK "+   
					" INNER JOIN SANPHAM SP ON SP.MA=B.MA "+
					" WHERE A.PK_SEQ = "+hoadon_fk+
					" GROUP BY a.ngayxuathd , A.KHO_FK, A.KBH_FK, A.NPP_FK,SP.PK_SEQ, B.SOLO,B.NGAYHETHAN  ,B.NGAYNHAPKHO " +
					" UNION ALL " +
					" SELECT  a.ngayxuathd, B.KHO_FK, A.KBH_FK, A.NPP_FK, SP.PK_SEQ,  case when  B.SOLO=' ' then 'NA' else B.SOLO end SOLO , SUM(B.SOLUONG) AS SOLUONG,  " +
					" case  when B.NGAYHETHAN=' ' then '2030-12-31' else B.ngayhethan end ngayhethan,SCHEME   "+
					" ,ISNULL(B.NGAYNHAPKHO,'') AS NGAYNHAPKHO   "+
					" FROM HOADON A INNER JOIN HOADON_CTKM_TRAKM_CHITIET B ON A.PK_SEQ = B.HOADONID "+   
					" INNER JOIN SANPHAM SP ON SP.PK_SEQ=B.SANPHAM_FK "+
					" WHERE A.PK_SEQ =    "+hoadon_fk + 
					" GROUP BY  a.ngayxuathd, B.KHO_FK, A.KBH_FK, A.NPP_FK,SP.PK_SEQ, B.SOLO,B.NGAYHETHAN  ,B.NGAYNHAPKHO,SCHEME  ";
					geso.dms.center.util.Utility util_kho=new geso.dms.center.util.Utility();
					System.out.println(query);
					ResultSet rskho=db.get(query);
					while(rskho.next()){
						String _khoid=rskho.getString("kho_fk");
						String ngayxuathd=rskho.getString("ngayxuathd");
						String _kbh_fk=rskho.getString("kbh_fk");
						String _NPP_FK=rskho.getString("NPP_FK");
						String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
						String _SOLO=rskho.getString("SOLO");
						String _NgayHetHan=rskho.getString("NgayHetHan");
						String _ngaynhapkho=rskho.getString("ngaynhapkho");
						String SCHEME=rskho.getString("SCHEME");

						double soluongct=rskho.getDouble("SOLUONG");
						String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(ngayxuathd, "Xoa HoadontaichinhSvl.java 552 ", db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, 
								_NgayHetHan, _ngaynhapkho, 0, (-1)*soluongct, soluongct, 0, 0);

						if(msg1.length() >0){
							db.getConnection().rollback();
							return msg1;
						}
						msg1=util_kho.Update_NPP_Kho_Sp(ngayxuathd, "Xoa HoadontaichinhSvl.java 552", db, _khoid, 
								_SANPHAM_FK, _NPP_FK, _kbh_fk, 0, (-1)*soluongct, soluongct, 0);


						if(msg1.length() >0){
							db.getConnection().rollback();
							return msg1;
						}
						if(rshoadon.getString("loaihoadon").equals("0")){
							query=" insert into DONHANG_SANPHAM_CHITIET  (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk) values ("+ddhid+","+_SANPHAM_FK+","+_kbh_fk+","+_khoid+",N'"+_SOLO+"','"+_NgayHetHan+"','"+_ngaynhapkho+"',"+soluongct+","+_NPP_FK+")";
							if(db.updateReturnInt(query) <=0){
								db.getConnection().rollback();
								return "Không thể thực hiện dòng lệnh : "+ query;


							}
						}else{
							query="INSERT INTO DONHANG_CTKM_TRAKM_CHITIET (kho_fk,DONHANG_FK,SANPHAM_FK,SOLUONG,SOLO,NGAYNHAPKHO,NGAYHETHAN,KHONVBH,CTKM_FK,trakm_fk) values " +
							"("+_khoid+","+ddhid+","+_SANPHAM_FK+","+soluongct+",'"+_SOLO+"','"+_ngaynhapkho+"','"+_NgayHetHan+"','0',(select pk_Seq from ctkhuyenmai where scheme='"+SCHEME+"'),(select top(1) a.PK_SEQ from TRAKHUYENMAI a inner join CTKM_TRAKM b on a.PK_SEQ = b.TRAKHUYENMAI_FK where b.CTKHUYENMAI_FK =(select pk_Seq from ctkhuyenmai where scheme='"+SCHEME+"')))";
							if(db.updateReturnInt(query) <=0){
								db.getConnection().rollback();
								System.out.println("loi dong nay" +query);
								return "Không thể thực hiện dòng lệnh : "+ query;


							}

						}

					}


				}
			}


			query = "update HOADON set trangthai = '5', nguoisua = '" + userId + "',NgayGioSua=getdate(),NgaySua='"+getDateTime()+"' where pk_seq = '" + lsxId + "' and TrangThai!=5 ";
			System.out.println("----HUY HOA DON: " + query);
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Không thể cập nhật HOADON " + query;
				db.getConnection().rollback();
				return msg;
			}

			//HUY HOA DON KHUYEN MAI HOAC BAN NEU CO
			query = 
				"update HOADON set trangthai = '5', nguoisua = '" + userId + "' " +
				"where  loaihoadon in (1,2) and pk_seq in (select hoadon_fk from HOADON_DDH where DDH_FK in (select DDH_FK from HOADON_DDH where HOADON_FK = '" + lsxId + "') and hoadon_fk != '" + lsxId + "' )  ";
			System.out.println("----2.HUY HOA DON: " + query);
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_HOADON " + query;
				db.getConnection().rollback();
				return msg;
			}
			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}

			//Cập nhật số hóa đơn trong đơn hàng
			util.Update_SoHoaDon("select DDH_FK from HOADON_DDH where HOADON_FK = '" + lsxId + "'", db);

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			msg = util.Check_CK_DaHuong(lsxId,userId,db);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		finally
		{
			if(db!=null)db.shutDown();
		}
		return msg;
	}

	private String Delete(String lsxId, String userId, String nppId) 
	{
		dbutils db = new dbutils();
		String msg = "";

		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("HoaDon", lsxId, "NgayXuatHD", db);
		if(msg.length()>0)
		{
			db.shutDown();
			return msg;		
		}
		try
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");

			db.getConnection().setAutoCommit(false);
			String query = "";

			// Kiểm tra import =1 thì k cho hủy
			query = 				
				" SELECT count(B.PK_SEQ) is_ThuTien " +
				" FROM ERP_THUTIENNPP_HOADON A INNER JOIN ERP_THUTIENNPP B ON A.THUTIENNPP_FK = B.PK_SEQ \n" +
				" INNER JOIN HOADON C ON A.HOADONNPP_FK = C.PK_SEQ AND A.LOAIHD = 0 \n" +
				" AND A.KHACHHANG_FK in \n"+
				" (SELECT PK_SEQ FROM KHACHHANG WHERE KBH_FK = 100025 and NPP_FK='" + nppId +"')  " +	
				" LEFT JOIN KHACHHANG KH ON KH.PK_SEQ = C.KHACHHANG_FK  \n" +
				" WHERE B.TRANGTHAI IN (0,1) AND C.PK_SEQ = "+lsxId + " AND C.KHACHHANG_FK = A.KHACHHANG_FK  \n";

			System.out.println(query);
			ResultSet rsKT = db.get(query);
			int is_Thutien = 0;

			if(rsKT!= null)
			{
				while(rsKT.next())
				{
					is_Thutien =  rsKT.getInt("is_ThuTien");
				}
				rsKT.close();
			}

			if(is_Thutien > 0)
			{
				msg = "Hóa đơn này đã thu tiền. Bạn không được phép xóa/hủy hóa đơn ";
				db.getConnection().rollback();
				return msg;
			}



			//TU DONG XOA PHIEU XUAT KHO
			query = "select DDH_FK as donhang_fk,  " +
			"	( select npp_fk from HOADON where pk_seq = '" + lsxId + "' ) as nppId,	" +
			"	ISNULL( ( select Import from DONHANG where pk_seq = a.DDH_FK ), 0) as Import, " +
			"	( select PXK_FK from PHIEUXUATKHO_DONHANG where donhang_fk = a.DDH_FK and PXK_FK in ( select pk_seq from PHIEUXUATKHO where trangthai != '2' and npp_fk = ( select npp_fk from HOADON where pk_seq = '" + lsxId + "' ) ) ) as soPXK  " +
			"from HOADON_DDH a where hoadon_fk = '" + lsxId + "'";
			System.out.println("---HUY HOA DON: " + query );

			String donhang_fk = "";
			String Import = "";
			String pxkId = "";

			Import="";
			ResultSet rs = db.get(query);
			{
				while(rs.next())
				{
					donhang_fk = rs.getString("donhang_fk");
					Import = rs.getString("Import");
					nppId = rs.getString("nppId");

					if(Import.equals("0"))
					{
						pxkId = rs.getString("soPXK");
						//TANG KHO NGUOC LAI


						/*query = "update phieuxuatkho set trangthai = '2' where pk_seq = '" + pxkId + "' and TrangThai=1 ";
						if( db.updateReturnInt(query) <= 0 )
						{
							rs.close();
							msg = "Không thể hủy " + query;
							db.getConnection().rollback();
							return msg;
						}*/

						query = "select count(*) as sl from  phieuxuatkho  where pk_seq = " + pxkId + "  ";
						System.out.println("query la "+query);
						ResultSet rspxk=db.get(query);
						rspxk.next();
						if(rspxk.getInt("sl")!=0)
						{
							rspxk.close();
							msg = "Vui lòng xóa phiếu xuất kho trước khi xóa hóa đơn" ;
							db.getConnection().rollback();
						}
						rspxk.close();
						//MO LAI TRANG THAI DON HANG
						query = "Update DONHANG set trangthai = '0',checkkho=1, daxuathoadon = '0' where pk_seq = '" + donhang_fk + "' and TrangThai=1 ";
						System.out.println("---CAP NHAT DON HANG: " + query);
						if( db.updateReturnInt(query) <= 0 )
						{
							rs.close();
							msg = "Không thể hủy đơn hàng " + query;
							db.getConnection().rollback();
							return msg;
						}

						//XOA PHIEU CAN TRU NEU CO
						query = "UPDATE CANTRUCONGNO set trangthai = '2' where pk_seq in ( select CANTRUCONGNO_FK from CANTRUCONGNO_HOADON where hoadon_fk = '" + lsxId + "' ) ";
						if( !db.update(query) )
						{
							rs.close();
							msg = "Không thể hủy CANTRUCONGNO " + query;
							db.getConnection().rollback();
							return msg;
						}
					}
					else
					{
						rs.close();
						msg = "Không thể hủy hóa đơn tự IMPORT vào hệ thống ";
						db.getConnection().rollback();
						return msg;
					}
				}
				rs.close();
			}

			if(Import.equals("0")) {

				query=" SELECT ddh_fk FROM HOADON_DDH WHERE HOADON_FK="+lsxId;

				ResultSet rshd=db.get(query);
				int count=0;
				String ddhid="";
				while(rshd.next()){
					ddhid=rshd.getString("ddh_fk");
					count++;
				}
				rshd.close();
				if(count >1){
					db.getConnection().rollback();
					return "Vui lòng báo Admin để được trợ giúp, hóa đơn không hợp lệ để hủy, số chứng từ:"+lsxId;
				}


				query=" DELETE DONHANG_SANPHAM_CHITIET WHERE DONHANG_FK="+ddhid;
				if(!db.update(query)){
					db.getConnection().rollback();
					return "Không thể thực hiện dòng lệnh : "+ query;
				}

				query=" DELETE DONHANG_CTKM_TRAKM_CHITIET where DONHANG_FK="+ddhid;
				if(!db.update(query)){
					db.getConnection().rollback();
					return "Không thể thực hiện dòng lệnh : "+ query;
				}



				query=  " SELECT PK_SEQ,LOAIHOADON FROM  HOADON  "+
				" where   TRANGTHAI not in (3,5) and   pk_seq in (select hoadon_fk from HOADON_DDH where DDH_FK in "+ 
				" (select DDH_FK from HOADON_DDH where HOADON_FK = "+lsxId+"))  ";
				ResultSet rshoadon=db.get(query);
				int i=0;
				while(rshoadon.next()){
					i++;
					String hoadon_fk=rshoadon.getString("PK_SEQ");

					query=  " SELECT a.ngayxuathd, A.KHO_FK, A.KBH_FK, A.NPP_FK, SP.PK_SEQ as sanpham_fk,case when  B.SOLO=' ' then 'NA' " +
					" else B.SOLO end SOLO, SUM(B.SOLUONG) AS SOLUONG, case  when B.NGAYHETHAN=' ' then '2030-12-31' else B.ngayhethan end ngayhethan , NULL AS SCHEME "+   
					" ,ISNULL(B.NGAYNHAPKHO,'') AS NGAYNHAPKHO   "+
					" FROM HOADON A INNER JOIN HOADON_SP_CHITIET B ON A.PK_SEQ = B.HOADON_FK "+   
					" INNER JOIN SANPHAM SP ON SP.MA=B.MA "+
					" WHERE A.PK_SEQ = "+hoadon_fk+
					" GROUP BY a.ngayxuathd , A.KHO_FK, A.KBH_FK, A.NPP_FK,SP.PK_SEQ, B.SOLO,B.NGAYHETHAN  ,B.NGAYNHAPKHO " +
					" UNION ALL " +
					" SELECT  a.ngayxuathd, B.KHO_FK, A.KBH_FK, A.NPP_FK, SP.PK_SEQ, case when  B.SOLO=' ' then 'NA' " +
					" else B.SOLO end SOLO,  SUM(B.SOLUONG) AS SOLUONG, case  when B.NGAYHETHAN=' ' then '2030-12-31' else B.ngayhethan end ngayhethan ,SCHEME   "+
					" ,ISNULL(B.NGAYNHAPKHO,'') AS NGAYNHAPKHO   "+
					" FROM HOADON A INNER JOIN HOADON_CTKM_TRAKM_CHITIET B ON A.PK_SEQ = B.HOADONID "+   
					" INNER JOIN SANPHAM SP ON SP.PK_SEQ=B.SANPHAM_FK "+
					" WHERE A.PK_SEQ =    "+hoadon_fk + 
					" GROUP BY  a.ngayxuathd, B.KHO_FK, A.KBH_FK, A.NPP_FK,SP.PK_SEQ, B.SOLO,B.NGAYHETHAN  ,B.NGAYNHAPKHO,SCHEME  ";
					geso.dms.center.util.Utility util_kho=new geso.dms.center.util.Utility();
					System.out.println(query);
					ResultSet rskho=db.get(query);
					while(rskho.next()){
						String _khoid=rskho.getString("kho_fk");
						String ngayxuathd=rskho.getString("ngayxuathd");
						String _kbh_fk=rskho.getString("kbh_fk");
						String _NPP_FK=rskho.getString("NPP_FK");
						String _SANPHAM_FK=rskho.getString("SANPHAM_FK");
						String _SOLO=rskho.getString("SOLO");
						String _NgayHetHan=rskho.getString("NgayHetHan");
						String _ngaynhapkho=rskho.getString("ngaynhapkho");
						String SCHEME=rskho.getString("SCHEME");

						double soluongct=rskho.getDouble("SOLUONG");
						String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(ngayxuathd, "Xoa HoadontaichinhSvl.java 552 ", db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, 
								_NgayHetHan, _ngaynhapkho, 0, (-1)*soluongct, soluongct, 0, 0);

						if(msg1.length() >0){
							db.getConnection().rollback();
							return msg1;
						}
						msg1=util_kho.Update_NPP_Kho_Sp(ngayxuathd, "Xoa HoadontaichinhSvl.java 552", db, _khoid, 
								_SANPHAM_FK, _NPP_FK, _kbh_fk, 0, (-1)*soluongct, soluongct, 0);


						if(msg1.length() >0){
							db.getConnection().rollback();
							return msg1;
						}
						if(rshoadon.getString("loaihoadon").equals("0")){
							query=" insert into DONHANG_SANPHAM_CHITIET  (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk) values ("+ddhid+","+_SANPHAM_FK+","+_kbh_fk+","+_khoid+",N'"+_SOLO+"','"+_NgayHetHan+"','"+_ngaynhapkho+"',"+soluongct+","+_NPP_FK+")";
							if(db.updateReturnInt(query) <=0){
								db.getConnection().rollback();
								return "Không thể thực hiện dòng lệnh : "+ query;


							}
						}else{
							query="INSERT INTO DONHANG_CTKM_TRAKM_CHITIET (kho_fk,DONHANG_FK,SANPHAM_FK,SOLUONG,SOLO,NGAYNHAPKHO,NGAYHETHAN,KHONVBH,CTKM_FK,trakm_fk) values " +
							"("+_khoid+","+ddhid+","+_SANPHAM_FK+","+soluongct+",'"+_SOLO+"','"+_ngaynhapkho+"','"+_NgayHetHan+"','0',(select pk_Seq from ctkhuyenmai where scheme='"+SCHEME+"'),(select top(1) a.PK_SEQ from TRAKHUYENMAI a inner join CTKM_TRAKM b on a.PK_SEQ = b.TRAKHUYENMAI_FK where b.CTKHUYENMAI_FK =(select pk_Seq from ctkhuyenmai where scheme='"+SCHEME+"')))";
							if(db.updateReturnInt(query) <=0){
								db.getConnection().rollback();
								System.out.println("loi dong nay" +query);
								return "Không thể thực hiện dòng lệnh : "+ query;


							}

						}

					}


				}
				if(i >3 || i<1){
					db.getConnection().rollback();
					return "Vui lòng báo Admin để được xử lý,lỗi khi hủy hóa đơn được tạo ra nhiều từ đơn đặt hàng : "+lsxId;

				}

				msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
				if(msg.length()>0)
				{
					db.getConnection().rollback();
					return msg;
				}	
			}



			query = "update HOADON set trangthai = '3', nguoisua = '" + userId + "',NgayGioSua=getdate(),NgaySua='"+getDateTime()+"' where pk_seq = '" + lsxId + "' and TrangThai!=3 ";
			System.out.println("---HUY HOA DON: 01 " + query );
			if(db.updateReturnInt(query)!=1)
			{
				msg = "Hóa đơn đã xóa rồi ";
				db.getConnection().rollback();
				return msg;
			}

			//HUY HOA DON KHUYEN MAI HOAC BAN NEU CO
			query = " update HOADON set trangthai = '3', nguoisua = '" + userId + "',NgayGioSua=getdate(),NgaySua='"+getDateTime()+"' " +
			" where  loaihoadon in ( 1,2) and pk_seq in (select hoadon_fk from HOADON_DDH where DDH_FK in (select DDH_FK from HOADON_DDH where HOADON_FK = '" + lsxId + "') and hoadon_fk != '" + lsxId + "' )  ";

			System.out.println("---HUY HOA DON: 02 " + query );
			if(!db.update(query))
			{
				msg = "Không thể cập nhật HOADON " + query;
				db.getConnection().rollback();
				return msg;
			}





			//Đối vơi HD tháng 1,ckQúy và tháng lấy trong 1 HĐ
			/********************************************/
			query=			
				"	select ckThang.hoadon_fk as ckThangId,ckQuy.hoadon_fk as ckQuyId  "+ 
				"	from  "+
				"	(  "+
				"		select b.hoadon_fk,a.KHACHHANG_FK,a.NPP_FK  "+
				"		from HOADON a inner join HOADON_CHIETKHAU b on b.hoadon_fk=a.PK_SEQ  "+
				"		where b.tichluyQUY=0 and a.TRANGTHAI not in (1,3,5) and diengiai like 'CT%' and a.NGAYXUATHD like '2015-01%' and a.pk_seq='"+lsxId+"' "+
				"	)as ckThang left join  "+ 
				"	(  "+
				"		select b.hoadon_fk,a.KHACHHANG_FK,a.NPP_FK  "+
				"		from HOADON a inner join HOADON_CHIETKHAU b on b.hoadon_fk=a.PK_SEQ  "+
				"		where b.tichluyQUY=1 and a.TRANGTHAI not in (1,3,5) and b.diengiai like 'CQ%' and a.NGAYXUATHD like '2015-01%'  "+
				"	)as ckQuy on ckThang.KHACHHANG_FK=ckQuy.KHACHHANG_FK and ckThang.NPP_FK=ckQuy.NPP_FK and ckThang.hoadon_fk!=ckQuy.hoadon_fk  "+
				"	where ckQuy.hoadon_fk is not null  ";
			rs=db.get(query);
			while(rs.next())
			{
				msg +="Vui lòng xóa hóa đơn đã hưởng ck Qúy  mã số "+rs.getString("ckQuyId")+" trước khi xóa HĐ đã hưởng ck Tháng \n";
			}
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}
			/********************************************/

			msg= util.Check_Kho_Tong_VS_KhoCT(nppId, db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return msg;
			}

			//Cập nhật số hóa đơn trong đơn hàng
			util.Update_SoHoaDon("select DDH_FK from HOADON_DDH where HOADON_FK = '" + lsxId + "'", db);

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			//Kiểm tra xem đã có hưởng ck tháng hay chưa.
			msg=util.Check_CK_DaHuong(lsxId,userId,db);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}finally
		{
			if(db!=null)db.shutDown();
		}

		return msg;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		Utility util = new Utility();
		String userId = util.antiSQLInspection(request.getParameter("userId")); 

		String action = request.getParameter("action");
		if (action == null)
		{
			action = "";
		}

		String loaidonhang = request.getParameter("loaidonhang");
		if(loaidonhang == null)
			loaidonhang = "0";


		IHoadontaichinhList obj = new HoadontaichinhList();
		obj.setLoaidonhang(loaidonhang);

		obj.setUserId(userId);

		String type = request.getParameter("type");
		if(type == null)
			type = "";
		obj.setType(type);
		System.out.println("---TYPE GET: " + type);
		HttpSession session = request.getSession();

		
			if(action.equals("view") || action.equals("next") || action.equals("prev"))
			{
				String search = getSearchQuery(request, obj);
				obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
				obj.setUserId(userId);
				obj.init(search);
				obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
				session.setAttribute("obj", obj);

				String nextJSP = request.getContextPath() + "/pages/Distributor/BienbannghiemthuKH.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				obj.setUserId(userId);
				String search = getSearchQuery(request, obj);
				session.setAttribute("obj", obj);  	
				session.setAttribute("userId", userId);
				obj.setUserId(userId);
				obj.init(search);	
				//lay thong tin khach hang
		    	String khachhang = request.getParameter("khTen");
		    	
		    	
		    	if(action.equals("in2"))
				{
					response.setContentType("application/pdf");
					response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
					Document document = new Document();
					ServletOutputStream outstream = response.getOutputStream();
					
					form_phieunghiemthu2(document, outstream, obj, khachhang);
				}
				else
					if(action.equals("in3")){
						response.setContentType("application/pdf");
						response.setHeader("Content-Disposition", " inline; filename=Bienbannghiemthu.pdf");
						Document document = new Document();
						ServletOutputStream outstream = response.getOutputStream();
						form_phieunghiemthu3(document, outstream, obj, khachhang);
					}
				

								
				String nextJSP = request.getContextPath() + "/pages/Distributor/BienbannghiemthuKH.jsp";
				response.sendRedirect(nextJSP);
			}
		
	}

	private String getSearchQuery(HttpServletRequest request, IHoadontaichinhList obj)
	{
		String nppId = request.getParameter("nppId");
		geso.dms.center.util.Utility utilCenter = new  geso.dms.center.util.Utility();
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);

		String query = "select distinct a.sohoadon, a.kyhieu ,isnull(Inhoadon,0) as inhoadon, a.PK_SEQ, a.trangthai, a.ngayxuatHD, NV.TEN as nguoitao, KH.TEN as nppTEN, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ," +
		"  isnull(a.tongtienavatNK,a.tongtienavat) as avat, ISNULL(b.hoadon_fk,0) ckt, " +
		"	 case a.trangthai when 1 then 1 when 2 then 2 when 3 then 4 when 4 then 3 when 5 then 5 end as STT_SORT, " +
		"	 case a.sohoadon when 'NA' then 9999999999 else cast(sohoadon as float) end as SORT_SOHOADON, isnull(DAINPGH, 0) as DAINPGH    " +
		" from HOADON a left join (select hoadon_fk from HOADON_CHIETKHAU where diengiai='CT5' and hienthi = '0' and round(chietkhau, 0) > 0) b on a.PK_SEQ = b.hoadon_fk  " +
		" inner join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
		" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
		" left join CANTRUCONGNO_HOADON ct on ct.hoadon_fk=a.pk_seq   "+
		" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
		" where a.pk_seq > 0 and a.NPP_FK ="+ nppId +" and isnull(loaihoadon,0) = 0 and a.kho_fk in  "+utilCenter.quyen_kho(obj.getUserId());

		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);

		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);

	

		String khten = request.getParameter("khTen");		
		if(khten == null)
			khten = "";
		obj.setKhTen(khten);

		
		
		if(tungay.length() > 0)
			query += " and a.ngayxuatHD >= '" + tungay + "'";

		if(denngay.length() > 0)
			query += " and a.ngayxuatHD <= '" + denngay + "'";

		if(khten.length() > 0)
			query += " and kh.pk_seq like '%" + khten + "%' ";


		System.out.print(query);
		return query;
	}

	public String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}

	

	float CONVERT = 28.346457f;  // =1cm
	private void form_phieunghiemthu2( Document document,ServletOutputStream outstream, IHoadontaichinhList obj, String khachhang){
		dbutils db = new dbutils();
		
		//lay thong khah hang
		String Donvi="";
		String kh_MST ="";
		String kh_dienthoai ="";
		String kh_Diachi="";
		String kh_sotaikhoan="";
		String kh_fax="";
		String sql="";	
		sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan, dienthoai as dienthoai,   "+    
		"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
		"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
		" from KHACHHANG kh where kh.pk_seq=" +khachhang;
		System.out.println("\n Lay TT KH "+sql +"\n");
		ResultSet rsLayKH= db.get(sql);
		if(rsLayKH!=null){
			try {
				while(rsLayKH.next())
				{
					Donvi = rsLayKH.getString("donvi");
					kh_MST = rsLayKH.getString("MASOTHUE");
					kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
					kh_dienthoai=rsLayKH.getString("dienthoai");
					kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
				}   
				rsLayKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		// lay thong tin ben giao //cua duoc cuu long
		String npp_fk="";
		String khId="";
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Sotaikhoan= "";
		String cty_Dienthoai= "";
		String cty_Fax= "";
		String cty_bank= "";
		//DON VI BAN: TONG CTY HO DCL
		String qr_ho="select ten , diachi, dienthoai, fax, masothue, taikhoan, nganhang from nhacungcap where pk_seq=100001 ";   
		 ResultSet rsHo = db.get(qr_ho);
		   if(rsHo!=null){
			   try {
				   if(rsHo.next())
				   {
					   ctyTen = rsHo.getString("TEN");
					   cty_MST = rsHo.getString("MASOTHUE");
					   cty_Diachi = rsHo.getString("DIACHI");
					   cty_Sotaikhoan = rsHo.getString("taikhoan");
					   cty_Dienthoai = rsHo.getString("dienthoai");
					   cty_Fax = rsHo.getString("fax");
					   cty_bank = rsHo.getString("nganhang");
					   rsHo.close();   
				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 1.0f*CONVERT,1.0f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked='\u00FE';
			char unchecked='\u00A8';


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 12, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 12, Font.BOLD);
			Font font10_ilatic = new Font(bf, 12, Font.ITALIC);

			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);


			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);

			// dong 1 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 2 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			chunk=new Chunk("Độc Lập - Tự Do - Hạnh Phúc",font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 3 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("BIÊN BẢN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("NGHIỆM THU THUỐC, VTYTTH, HÓA CHẤT, DỊCH TRUYỀN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//dong 4 5

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("Căn cứ Hợp đồng số:........./ …...... ngày........ tháng....... năm ........",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			String date=getDate();
			para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày "+ date.substring(8)+" tháng "+ date.substring(5,7)+ " năm "+date.substring(0,4) + ", chúng tôi gồm có:",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);


			document.add(tableheader);

			//== thong tin

			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);

			// dong 1 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN NHẬN (Bên A)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(" : "+Donvi.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản:  "+kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                    Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN GIAO (Bên B)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);

			chunk=new Chunk(" : " +ctyTen.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: "+ cty_Dienthoai +"  – Fax: "+cty_Fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản: " +cty_Sotaikhoan + " tại " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                    Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên thống nhất nghiệm thu và bàn giao thuốc, VTYTTH, hóa chất, dịch truyền theo Hợp đồng đã ký với chi tiết như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("Nội dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      Bên B bàn giao cho bên A thuốc, VTYTTH, hóa chất, dịch truyền theo hóa đơn:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			document.add(tbl);


			//========bang danh sach hoa don
			float[] tbl_withd = { 10.0f, 50f, 40.0f, 35.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//add tieu de
			String[] tieude = new String[] { "TT","Số HĐ","Ngày xuất hóa đơn nghiệm thu","Thành tiền \n (VNĐ)"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach
			int stt=1;
			double tongtien=0;
			ResultSet nhapkhoRs =  obj.getDondathangRs();
			try {

				//SAN PHAM BAN
				while(nhapkhoRs.next())
				{
					String[] arr = new String[] { Integer.toString(stt),nhapkhoRs.getString("SoHoaDon").trim(),
							dinhdangngay(nhapkhoRs.getString("NGAYXUATHD")),DinhDangTraphacoDMS(formatter1.format(nhapkhoRs.getDouble("avat")))};
					String tt = nhapkhoRs.getString("trangthai");
					if(tt.equals("2")||tt.equals("4"))
					{
						tongtien+=nhapkhoRs.getDouble("avat");
						for (int j = 0; j < tbl_withd.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
							if(j==3){
								cells.setHorizontalAlignment(Element.ALIGN_RIGHT);
							}
							else
								cells.setHorizontalAlignment(Element.ALIGN_CENTER);

							cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
							cells.setPaddingBottom(7);
							tbl_sanpham.addCell(cells);	
						}
						stt++;
					}
				}
				nhapkhoRs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			//cong 
			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph("TỔNG CỘNG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format(tongtien)), new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			document.add(tbl_sanpham);

			//=========== footer

			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);
			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)tongtien);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  " +TienIN, new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Kết luận: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã kiểm tra đúng chủng loại, hàm lượng, số lô, hạn dùng,…….. của hàng hóa theo yêu cầu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã nhận đầy đủ số lượng hàng hóa. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Trong thời gian sử dụng nếu bên A phát hiện bên trong có sự thiếu hụt về số lượng hoặc kém chất lượng ……,"+
					" Bên A sẽ thông báo ngay cho Bên B bằng cách điện thoại, fax hoặc bằng văn bản để cùng bàn bạc giải quyết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A thống nhất nghiệm thu và thanh toán tiền thuốc, hóa chất, VTYTTH cho bên B theo đúng Hợp đồng đã ký kết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Biên bản lập thành 04 bản, bên A nhận 03 bản, bên B  nhận 01 bản, đều có giá trị như nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                               "+
					"ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GIÁM ĐỐC                                              "+
					"                                                       TUQ. TỔNG GIÁM ĐỐC", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			document.add(tbl_footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}


	private void form_phieunghiemthu3( Document document,ServletOutputStream outstream,  IHoadontaichinhList obj, String khachhang){
		dbutils db = new dbutils();
		//lay thong khah hang
		//lay thong khah hang
		String Donvi="";
		String kh_MST ="";
		String kh_dienthoai ="";
		String kh_Diachi="";
		String kh_sotaikhoan="";
		String kh_fax="";
		String sql="";	
		sql="select kh.pk_seq, kh.TEN as donvi,isnull(kh.chucuahieu,'') as chucuahieu, kh.diachi as diachi ,isnull(masothue,'') as masothue, '' as taikhoan, dienthoai as dienthoai,   "+    
		"(select ten from tinhthanh where pk_seq=kh.tinhthanh_fk) as tinhthanh, "+    
		"(select ten from QUANHUYEN where pk_seq=kh.quanhuyen_fk) as quanhuyen "+    
		" from KHACHHANG kh where kh.pk_seq=" +khachhang;
		System.out.println("\n Lay TT KH "+sql +"\n");
		ResultSet rsLayKH= db.get(sql);
		if(rsLayKH!=null){
			try {
				while(rsLayKH.next())
				{
					Donvi = rsLayKH.getString("donvi");
					kh_MST = rsLayKH.getString("MASOTHUE");
					kh_Diachi = rsLayKH.getString("DIACHI")+", "+rsLayKH.getString("quanhuyen")+", "+rsLayKH.getString("tinhthanh");
					kh_dienthoai=rsLayKH.getString("dienthoai");
					kh_sotaikhoan=rsLayKH.getString("TAIKHOAN");
				}   
				rsLayKH.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		// lay thong tin ben giao //cua duoc cuu long
		String npp_fk="";
		String khId="";
		String ctyTen="";
		String cty_MST ="";
		String cty_Diachi="";
		String cty_Sotaikhoan= "";
		String cty_Dienthoai= "";
		String cty_Fax= "";
		String cty_bank= "";
		//DON VI BAN: TONG CTY HO DCL
		String qr_ho="select ten , diachi, dienthoai, fax, masothue, taikhoan, nganhang from nhacungcap where pk_seq=100001 ";   
		 ResultSet rsHo = db.get(qr_ho);
		   if(rsHo!=null){
			   try {
				   if(rsHo.next())
				   {
					   ctyTen = rsHo.getString("TEN");
					   cty_MST = rsHo.getString("MASOTHUE");
					   cty_Diachi = rsHo.getString("DIACHI");
					   cty_Sotaikhoan = rsHo.getString("taikhoan");
					   cty_Dienthoai = rsHo.getString("dienthoai");
					   cty_Fax = rsHo.getString("fax");
					   cty_bank = rsHo.getString("nganhang");
					   rsHo.close();   
				   }
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
		try {
			//khai bao
			NumberFormat formatter = new DecimalFormat("#,###,###.##");
			NumberFormat formatter1 = new DecimalFormat("#,###,###");
			document.setPageSize(PageSize.A4);
			document.setMargins(1.5f*CONVERT, 1.5f*CONVERT, 1.0f*CONVERT, 1.0f*CONVERT); // L,R,T,B
			PdfWriter writer = PdfWriter.getInstance(document, outstream);
			PdfPCell cell ;
			Paragraph para;
			Chunk chunk;BaseFont charbase = BaseFont.createFont("C:\\windows\\fonts\\Wingding.ttf", BaseFont.IDENTITY_H, false);
			Font charfont = new Font(charbase, 11f, Font.NORMAL);
			char checked='\u00FE';
			char unchecked='\u00A8';


			BaseFont bf = BaseFont.createFont("c:\\windows\\fonts\\times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			Font font10_normal = new Font(bf, 12, Font.NORMAL);			
			Font font10_Bold = new Font(bf, 12, Font.BOLD);
			Font font10_ilatic = new Font(bf, 12, Font.ITALIC);

			Font font12_normal = new Font(bf, 12, Font.NORMAL);
			Font font12_Bold = new Font(bf, 12, Font.BOLD);
			Font font12_ilatic = new Font(bf, 12, Font.ITALIC);


			document.open() ;
			//================tao header1
			PdfPTable tableheader =new PdfPTable(1);
			tableheader.setWidthPercentage(100);
			tableheader.setHorizontalAlignment(Element.ALIGN_CENTER);

			// dong 1 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM",font10_Bold);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 2 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_CENTER);
			chunk=new Chunk("Độc Lập - Tự Do - Hạnh Phúc",font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			cell.addElement(para);
			tableheader.addCell(cell);

			// dong 3 
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingTop(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("BIÊN BẢN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingBottom(10);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("NGHIỆM THU THUỐC, VTYTTH, HÓA CHẤT, DỊCH TRUYỀN",new Font(bf,16,Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);

			//dong 4 5

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			para=new Paragraph("Căn cứ Hợp đồng số:........./ …...... ngày........ tháng....... năm ........",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			String date=getDate();
			para=new Paragraph("Hôm nay, lúc  ... giờ ...  phút, ngày "+ date.substring(8)+" tháng "+ date.substring(5,7)+ " năm "+date.substring(0,4) + ", chúng tôi gồm có:",font10_ilatic);
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tableheader.addCell(cell);


			document.add(tableheader);

			//== thong tin

			PdfPTable tbl =new PdfPTable(1);
			tbl.setWidthPercentage(100);

			// dong 1 2
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN NHẬN (Bên A)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);
			chunk=new Chunk(" : "+Donvi.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);


			// thong tin ben a
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+kh_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: " +kh_dienthoai         +"                 -Fax:"+kh_fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+kh_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản:  "+kh_sotaikhoan,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                    Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);


			// ben giao
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph();
			para.setAlignment(Element.ALIGN_LEFT);
			chunk=new Chunk("BÊN GIAO (Bên B)", font10_Bold);
			chunk.setUnderline(1f, -2);
			para.add(chunk);

			chunk=new Chunk(" : " +ctyTen.toUpperCase(), font10_Bold);
			para.add(chunk);
			cell.addElement(para);
			tbl.addCell(cell);

			// thong tin ben b
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);

			para=new Paragraph("Địa chỉ: "+cty_Diachi,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Điện thoại: "+ cty_Dienthoai +"  – Fax: "+cty_Fax,font10_normal); // kem fax
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Mã số thuế: "+cty_MST,font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);


			para=new Paragraph("Tài khoản: " +cty_Sotaikhoan + " tại " +cty_bank,font10_normal); //tai ngan hang nao?
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);

			para=new Paragraph("Đại diện:                                                                      Chức vụ:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			//loi xac nhan
			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			para=new Paragraph("      Hai bên thống nhất nghiệm thu và bàn giao thuốc, VTYTTH, hóa chất, dịch truyền theo Hợp đồng đã ký với chi tiết như sau:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("Nội dung:",font10_Bold);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			para=new Paragraph("      Bên B bàn giao cho bên A thuốc, VTYTTH, hóa chất, dịch truyền theo hóa đơn:",font10_normal);
			para.setAlignment(Element.ALIGN_LEFT);
			cell.addElement(para);
			tbl.addCell(cell);

			document.add(tbl);



			//========bang danh sach hoa don
			float[] tbl_withd = { 10.0f, 60f, 20.0f, 25.0f, 20.0f, 25.0f };
			PdfPTable tbl_sanpham = new PdfPTable(tbl_withd.length);
			tbl_sanpham.setWidthPercentage(100);
			tbl_sanpham.setSpacingBefore(0.3f * CONVERT);
			tbl_sanpham.setHorizontalAlignment(Element.ALIGN_LEFT);
			tbl_sanpham.setWidths(tbl_withd);
			PdfPCell cells;

			//add tieu de
			String[] tieude = new String[] { "TT"," Tên Hàng Hóa ","Đơn vị tính","Số lượng","Đơn giá","Thành tiền"};
			for (int j = 0; j < tbl_withd.length; j++)
			{
				cells = new PdfPCell(new Paragraph(tieude[j], new Font(bf, 10, Font.BOLD)));
				cells.setHorizontalAlignment(Element.ALIGN_CENTER);
				cells.setPaddingBottom(7);
				tbl_sanpham.addCell(cells);	
			}
			//danh sach hoa don
			int stt=1;
			double tongtien=0;
			double soluongtong=0;
			String dshoadon="";
			ResultSet nhapkhoRs =  obj.getDondathangRs();

			try {

				//SAN PHAM BAN
				while(nhapkhoRs.next())
				{
					String tt = nhapkhoRs.getString("trangthai"); // lay da chot
					if(tt.equals("2")||tt.equals("4"))
					{
						String hoadonid=nhapkhoRs.getString("PK_SEQ");
						
						//tim don hang
						String iddh="";
						String qr_dh=" select ddh_fk as madh from HOADON_DDH where hoadon_fk="+hoadonid;
						ResultSet rsDH=db.get(qr_dh);
						if(rsDH!=null){
							try {
								while(rsDH.next()){
									iddh=rsDH.getString("madh");
									dshoadon+=iddh +",";
								}
								rsDH.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					}
				}
				nhapkhoRs.close();

			} catch (Exception e) {
				e.printStackTrace();
			}

			if(dshoadon.length()>0){

				//bo dau "," cuoi
				dshoadon=dshoadon.substring(0,dshoadon.length()-1);
				System.out.println(" danh sach cac hoa don:"+ dshoadon);
				//lay san pham tung hoa don
				//thong tin san pham hang hoa
				String sqlIN_SANPHAM = " select MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT, sum( SOLUONG ) as soluong, CHIETKHAU, NGAYHETHAN AS HANDUNG from HOADON_SP_CHITIET  "+    
				" where hoadon_fk in "+    
				" ( select a.pk_seq as mahoadon from HOADON a  inner join HOADON_DDH  b "+    
				" on a.PK_SEQ=b.HOADON_FK where  a.loaihoadon='0' and b.ddh_fk in (" + dshoadon + ") and a.trangthai not in(3,5)) "+
				"group by MA, TEN, DONVI, DONGIA, SOLO, NGAYHETHAN, THUEVAT,CHIETKHAU";
				System.out.println("---IN SAN PHAM: " + sqlIN_SANPHAM );
				System.out.println(" qr lay san pham ban: "+sqlIN_SANPHAM);
				ResultSet rsSP = db.get(sqlIN_SANPHAM);
				
				
				String sqlIN_SANPHAMKM =" select sp.MA as ma, sp.TEN as ten, dv.DONVI as donvi,km.DONGIA as dongia, km.SOLO as solo, km.NGAYHETHAN as HANDUNG,sum( km.SOLUONG ) as soluong, '' as CHIETKHAU    "+    
				"  from HOADON_CTKM_TRAKM_CHITIET km inner join sanpham sp on sp.pk_seq=km.sanpham_fk  inner join DONVIDOLUONG dv on dv.PK_SEQ=sp.DVDL_FK "+    
				"  where hoadon_fk in "+    
				"   ( select a.pk_seq as mahoadon from HOADON a  inner join HOADON_DDH  b "+    
				" on a.PK_SEQ=b.HOADON_FK where  a.loaihoadon='1' and b.ddh_fk in (" + dshoadon + ") and a.trangthai not in(3,5)) "+
				" group by sp.MA, sp.TEN,dv.DONVI,km.DONGIA, km.SOLO,km.NGAYHETHAN";
				ResultSet rsSPKM = db.get(sqlIN_SANPHAMKM);
				
				
				try {

					//SAN PHAM BAN
					while(rsSP.next())
					{
						double soLUONG = rsSP.getDouble("soluong");
						double dongia = rsSP.getDouble("dongia");
						double thanhtien = soLUONG*dongia;

						tongtien +=thanhtien;
						soluongtong+=soLUONG;
						String[] arr = new String[] { Integer.toString(stt), rsSP.getString("TEN"), rsSP.getString("DONVI"),
								DinhDangTraphacoDMS(formatter1.format(soLUONG)), 
								DinhDangTraphacoDMS(formatter.format(dongia)),
								DinhDangTraphacoDMS(formatter1.format(thanhtien))};

						System.out.println("\n don vi tinh: "+ rsSP.getString("DONVI"));

						for (int j = 0; j < tbl_withd.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
							if(j==1){
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							else
								if(j==4 || j==5|| j==6){
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);

								}
								else
								{
									cells.setHorizontalAlignment(Element.ALIGN_CENTER);
								}
							cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
							cells.setPaddingBottom(7);
							tbl_sanpham.addCell(cells);	
						}
						stt++;
					}
					rsSP.close();

				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try{
					//SAN PHAM KHUYEN MAI
					while(rsSPKM.next())
					{
						double soLUONG = rsSPKM.getDouble("soluong");
						double dongia = 0.0;
						double thanhtien = soLUONG*dongia;
						soluongtong+=soLUONG;
						tongtien +=thanhtien;
						String[] arr = new String[] { Integer.toString(stt), rsSPKM.getString("TEN"), rsSPKM.getString("DONVI"),
								DinhDangTraphacoDMS(formatter1.format(soLUONG)), 
								DinhDangTraphacoDMS(formatter.format(dongia)),
								DinhDangTraphacoDMS(formatter1.format(thanhtien)),rsSPKM.getString("solo"), rsSPKM.getString("handung"),"Việt Nam" };

						for (int j = 0; j < tbl_withd.length; j++)
						{
							cells = new PdfPCell(new Paragraph(arr[j], new Font(bf, 10, Font.NORMAL)));
							if(j==1){
								cells.setHorizontalAlignment(Element.ALIGN_LEFT);
							}
							else
								if(j==4 || j==5|| j==6){
									cells.setHorizontalAlignment(Element.ALIGN_RIGHT);

								}
								else
								{
									cells.setHorizontalAlignment(Element.ALIGN_CENTER);
								}
							cells.setVerticalAlignment(Element.ALIGN_BOTTOM);
							cells.setPaddingBottom(7);
							tbl_sanpham.addCell(cells);	
						}
						stt++;

						System.out.println(" so thu tu : "+ stt);
					}
					rsSPKM.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			
			//cong 
			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setColspan(2);
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph("TỔNG CỘNG " , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			cell.setPaddingBottom(7);
			para = new Paragraph(formatter1.format( soluongtong ), new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_CENTER);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_LEFT);
			para = new Paragraph("", new Font(bf, 10, Font.NORMAL));
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			cell = new PdfPCell();
			cell.setVerticalAlignment(Element.ALIGN_RIGHT);
			cell.setPaddingBottom(7);
			para = new Paragraph(DinhDangTraphacoDMS(formatter1.format( tongtien )) , new Font(bf, 10, Font.BOLD));
			para.setAlignment(Element.ALIGN_RIGHT);
			cell.addElement(para);
			tbl_sanpham.addCell(cell);

			document.add(tbl_sanpham);

			//=========== footer

			PdfPTable tbl_footer =new PdfPTable(1);
			tbl_footer.setWidthPercentage(100);


			doctienrachu doctien = new doctienrachu();
			String tien = doctien.docTien((long)tongtien);
			String TienIN = (tien.substring(0,1)).toUpperCase() + tien.substring(1); //Viết hoa ký tự đầu tiên
			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Số tiền bằng chữ:  " +TienIN, new Font(bf,12,Font.NORMAL)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("Kết luận: ", new Font(bf,12,Font.BOLD)); //cong doc tine
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã kiểm tra đúng chủng loại, hàm lượng, số lô, hạn dùng,…….. của hàng hóa theo yêu cầu.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A đã nhận đầy đủ số lượng hàng hóa. ", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Trong thời gian sử dụng nếu bên A phát hiện bên trong có sự thiếu hụt về số lượng hoặc kém chất lượng ……,"+
					" Bên A sẽ thông báo ngay cho Bên B bằng cách điện thoại, fax hoặc bằng văn bản để cùng bàn bạc giải quyết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      - Bên A thống nhất nghiệm thu và thanh toán tiền thuốc, hóa chất, VTYTTHcho bên B theo đúng Hợp đồng đã ký kết.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			para = new Paragraph("      Biên bản lập thành 04 bản, bên A nhận 03 bản, bên B  nhận 01 bản, đều có giá trị như nhau.", font10_normal); 
			para.setAlignment(Element.ALIGN_JUSTIFIED);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("ĐẠI DIỆN BÊN A"+
					"                                                                               "+
					"ĐẠI DIỆN BÊN B", font10_Bold);
			cell.addElement(para);
			tbl_footer.addCell(cell);

			cell = new PdfPCell();
			cell.setBorder(0);
			cell.setPaddingLeft(1.5f*CONVERT);
			para = new Paragraph("       GIÁM ĐỐC                                              "+
					"                                                       TUQ. TỔNG GIÁM ĐỐC", new Font(bf,10,Font.BOLD));
			cell.addElement(para);
			tbl_footer.addCell(cell);

			document.add(tbl_footer);

			document.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}



	private String dinhdangngay (String ngay) {
		String date="";
		if(ngay.length()>=8){
			date=ngay.substring(8)+"/"+ngay.substring(5,7)+"/"+ngay.substring(0,4);
		}
		return date;
	}
	private String getDate()
	{			
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	private String DinhDangTraphacoDMS(String sotien)
	{
		sotien = sotien.replaceAll("\\.", "_");
		sotien = sotien.replaceAll(",", "\\.");
		sotien = sotien.replaceAll("_", ",");

		return sotien;
	}

	

}
