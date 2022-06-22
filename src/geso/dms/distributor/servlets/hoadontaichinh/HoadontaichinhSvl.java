package geso.dms.distributor.servlets.hoadontaichinh;

import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.IHoadontaichinhList;
import geso.dms.distributor.beans.hoadontaichinh.imp.Hoadontaichinh;
import geso.dms.distributor.beans.hoadontaichinh.imp.HoadontaichinhList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HoadontaichinhSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public HoadontaichinhSvl() {
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
	    
	    geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
	    
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
	    
	    if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}
	    else{
	    	Utility util = new Utility();
    	    
		    String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
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
				String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinh.jsp";
				response.sendRedirect(nextJSP);
		    }
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
	     try {
			myDATA +=
			  	"<tr style='background-color: #CCC;' > "+ 
			  " <td width='100px' > <A href =../../RouterSvl?task="+Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript")+"HoadontaichinhSvl?userId="+userId+"&delete="+hoadonId )+"> <img src='../images/Delete_Icon.png' alt='Xóa hóa đơn' title='Xóa hóa đơn' width='20' height='20' border=0 onclick=\"if(!confirm('Bạn có xóa hóa đơn này?\')) return false;\"></A>" +
			  " </td>  " +
			  " &nbsp; | &nbsp;  "+
				  " <td width='100px' > <A href =../../RouterSvl?task="+Utility.dongMa(getServletContext().getInitParameter("RedirectNoScript")+"HoadontaichinhSvl?userId="+userId+"&huy="+hoadonId )+"><img src='../images/Delete20.png' alt='Hủy hóa đơn' title='Hủy hóa đơn' width='20' height='20' border=0 onclick=\"if(!confirm('Bạn có muốn hủy hóa đơn này?')) return false;\"></A>   " +
					" </td> "+  
			  " </tr>  ";
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     myDATA += " </table> </div> ";
	     System.out.println(myDATA);
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
						"( select PXK_FK from PHIEUXUATKHO_DONHANG where hoadon_fk =  '" + lsxId + "' ) as soPXK  " +
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
							return msg;
						}
						rspxk.close();
						
						//MO LAI TRANG THAI DON HANG
						query = "Update DONHANG set trangthai = '0',checkkho=1, daxuathoadon = '0',isbooked=0 where pk_seq = '" + donhang_fk + "' and TrangThai=1 ";
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
					String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(ngayxuathd, "Xoa HoadontaichinhSvl.java 552: "+hoadon_fk, db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, 
							_NgayHetHan, _ngaynhapkho, 0, (-1)*soluongct, soluongct, 0, 0);
					
					if(msg1.length() >0){
						db.getConnection().rollback();
						return msg1;
					}
					msg1=util_kho.Update_NPP_Kho_Sp(ngayxuathd, "Xoa HoadontaichinhSvl.java 552: "+hoadon_fk, db, _khoid, 
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
			
			/*query="delete from erp_phatsinhketoan where sohoadon= (select SonetId from hoadon where pk_Seq='"+lsxId+"') ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật erp_phatsinhketoan " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			
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
					"	( select PXK_FK from PHIEUXUATKHO_DONHANG where hoadon_fk = '" + lsxId + "' ) as soPXK  " +
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
							return msg;
						}
						rspxk.close();
						//MO LAI TRANG THAI DON HANG
						query = "Update DONHANG set trangthai = '0',checkkho=1, daxuathoadon = '0',isbooked=0 where pk_seq = '" + donhang_fk + "' and TrangThai=1 ";
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
					String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(ngayxuathd, "Xoa HoadontaichinhSvl.java 552: "+hoadon_fk, db, _khoid, _SANPHAM_FK, _NPP_FK, _kbh_fk, _SOLO, 
							_NgayHetHan, _ngaynhapkho, 0, (-1)*soluongct, soluongct, 0, 0);
					
					if(msg1.length() >0){
						db.getConnection().rollback();
						return msg1;
					}
					msg1=util_kho.Update_NPP_Kho_Sp(ngayxuathd, "Xoa HoadontaichinhSvl.java 552: "+hoadon_fk, db, _khoid, 
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
			
			// chot pxk moi ra sonetid nen ko xoa cho nay
			/*query="delete from erp_phatsinhketoan where sohoadon= (select SonetId from hoadon where pk_Seq='"+lsxId+"') ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật erp_phatsinhketoan " + query;
				db.getConnection().rollback();
				return msg;
			}*/
			
			
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
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
		}else{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
		    Utility util = new Utility();
		    userId = util.antiSQLInspection(request.getParameter("userId")); 
		    
		    String action = request.getParameter("action");
		    if (action == null)
		    {
		    	action = "";
		    }
		    
		    String loaidonhang = util.antiSQLInspection(request.getParameter("loaidonhang"));
		    if(loaidonhang == null)
		    	loaidonhang = "0";
		    
		    
			IHoadontaichinhList obj = new HoadontaichinhList();
			obj.setLoaidonhang(loaidonhang);
		 
			obj.setUserId(userId);
			
			String type = util.antiSQLInspection(request.getParameter("type"));
		    if(type == null)
		    	type = "";
		    obj.setType(type);
		    System.out.println("---TYPE GET: " + type);
			
		 
			
		    
		    if(action.equals("Tao moi"))
		    {
		    	IHoadontaichinh lsxBean = new Hoadontaichinh();
		    	
		    	lsxBean.setUserId(userId);
		    	lsxBean.createRs();
		    	session.setAttribute("dvkdId", "");
				session.setAttribute("kbhId", "");
				session.setAttribute("nppId", "");
	    		
		    	session.setAttribute("lsxBean", lsxBean);
		    	
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinhNew.jsp";
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
			    	
			    	String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinh.jsp";
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
		    		String nextJSP = request.getContextPath() + "/pages/Distributor/HoaDonTaiChinh.jsp";
		    		response.sendRedirect(nextJSP);
		    	}
		    }
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
					    "	 case isnumeric(a.sohoadon) when 0  then 9999999999 else cast(sohoadon as float) end as SORT_SOHOADON, isnull(DAINPGH, 0) as DAINPGH    " +
						" from HOADON a left join (select hoadon_fk from HOADON_CHIETKHAU where diengiai='CT5' and hienthi = '0' and round(chietkhau, 0) > 0) b on a.PK_SEQ = b.hoadon_fk  " +
						" inner join KHACHHANG KH on a.KHACHHANG_FK=KH.PK_SEQ  " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" left join CANTRUCONGNO_HOADON ct on ct.hoadon_fk=a.pk_seq   "+
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
						" where a.pk_seq > 0 and a.NPP_FK ="+ nppId +" and isnull(loaihoadon,0) = 0 and a.kho_fk in  "+utilCenter.quyen_kho(obj.getUserId());

		String tungay = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("tungay"));
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("denngay"));
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String madonhang = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("madonhang"));
		if(madonhang == null)
			madonhang = "";
		obj.setMadonhang(madonhang);
		
		String trangthai = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("trangthai"));
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String khten = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("khTen"));		
		if(khten == null)
			khten = "";
		obj.setKhTen(khten);
		
		
		String sohoadon = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("sohoadon"));		
		if(sohoadon == null)
			sohoadon = "";
		obj.setSoHoaDon(sohoadon.trim());
		
		String hoadonId = geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("hoadonId"));		
		if(hoadonId == null)
			hoadonId = "";
		obj.setHoadonId(hoadonId.trim());
		
		
		if(tungay.length() > 0)
			query += " and a.ngayxuatHD >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngayxuatHD <= '" + denngay + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(khten.length() > 0)
			query += " and kh.pk_seq like '%" + khten + "%' ";
		
		
		if(sohoadon.length() > 0)
			query += " and a.sohoadon like   '%" + sohoadon + "%' ";
		
		if(madonhang.length() > 0)
			query += " and a.pk_seq in ( select hoadon_fk from HOADON_DDH where ddh_fk = " + madonhang + ") ";
		
		
		if(hoadonId.length() > 0)
			query += " and  cast(a.pk_seq as varchar(20)) like '%"+hoadonId+"%' ";
		
		System.out.print(query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
