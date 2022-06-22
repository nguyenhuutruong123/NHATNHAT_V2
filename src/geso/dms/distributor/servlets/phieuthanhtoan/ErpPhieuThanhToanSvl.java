package geso.dms.distributor.servlets.phieuthanhtoan;
import geso.dms.distributor.beans.phieuthanhtoan.IErpDonmuahang_Giay;
import geso.dms.distributor.beans.phieuthanhtoan.IErpDonmuahangList_Giay;
import geso.dms.distributor.beans.phieuthanhtoan.imp.ErpDonmuahang_Giay;
import geso.dms.distributor.beans.phieuthanhtoan.imp.ErpDonmuahangList_Giay;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpPhieuThanhToanSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpPhieuThanhToanSvl() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDonmuahangList_Giay obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    	    
	    HttpSession session = request.getSession();	    

	    Utility util = new Utility();
	    	    
	    String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    
	    String action = util.getAction(querystring);
	    
	    String dmhId = util.getId(querystring);
	    
	    obj = new ErpDonmuahangList_Giay();
	    obj.setUserId(userId);	    
	    
	    obj.setCongtyId((String)session.getAttribute("congtyId"));
	    
	    String msg = "";
	    if (action.equals("delete"))
	    {	
	    	msg = Delete(dmhId,userId);
	    }
	    else
	    {
	    	if(action.equals("hoantat"))
	    	{
	    		dbutils db = new dbutils();
	    		db.update("update ERP_MUAHANG set trangthai = '2' where pk_seq = '" + dmhId + "'");
	    		db.shutDown();
	    	}
	    	else
	    	{
	    		if(action.equals("chot"))
		    	{
	    			msg = obj.Chotmuahang(dmhId);
	    			
		    	}
	    		else if(action.equals("boduyetgmcp"))
		    	{
		    		dbutils db = new dbutils();
		    		
		    		try
		    		{
			    		String sql = "update ERP_MUAHANG set ISCHOTGANMACP = '0' , ISKTTDUYET = '0' where pk_seq = '" + dmhId + "'";
			    		if(!db.update(sql))
			    		{
			    			msg = "Không thể bỏ duyệt Gán mã chi phí của phiếu này.";
			    			db.getConnection().rollback();
			    		}
			    		
			    		sql = "update ERP_DUYETMUAHANG set TRANGTHAI = '0'  where MUAHANG_FK = '" + dmhId + "' AND CHUCDANH_FK = '100001' ";
			    		if(!db.update(sql))
			    		{
			    			msg = "Không thể bỏ duyệt ERP_DUYETMUAHANG của phiếu này.";
			    			db.getConnection().rollback();
			    		}
			    		
			    		
		    		}catch(Exception e)
		    		{
		    			e.printStackTrace();
		    		}		    		
		    		
		    		db.shutDown();
		    	}
	    	}
	    }

	   
	    if(msg.length() > 0) obj.setmsg(msg);
	    
	    obj.init("");
	    
		session.setAttribute("obj", obj);
				
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuThanhToanList.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDonmuahangList_Giay obj;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpDonmuahang_Giay dmhBean = new ErpDonmuahang_Giay();
	    	dmhBean.setCongtyId((String)session.getAttribute("congtyId"));
	    	dmhBean.setUserId(userId);
	    	dmhBean.createRs();
    		
	    	session.setAttribute("lhhId", "");
	    	session.setAttribute("lspId", "");
	    	session.setAttribute("noibo", "");
	    	
	    	session.setAttribute("dmhBean", dmhBean);
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpPhieuthanhtoanNew_Giay.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		obj = new ErpDonmuahangList_Giay();
	    		obj.setUserId(userId);
	    		obj.setCongtyId((String)session.getAttribute("congtyId"));
		    	
	    		obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));

	    		String search = getSearchQuery(request, obj);
		    	
		    	obj.init(search);

		    	session.setAttribute("obj", obj);
		    	response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpPhieuThanhToanList.jsp");	
		    }
	    	else
	    	{
		    	obj = new ErpDonmuahangList_Giay();
		    	obj.setUserId(userId);
		    	obj.setCongtyId((String)session.getAttribute("congtyId"));
		    	
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/ErpPhieuThanhToanList.jsp");  
	    	}
	    }
	    
	}

	private String getSearchQuery(HttpServletRequest request, IErpDonmuahangList_Giay obj)
	{
		Utility util = new Utility();
		
		
		String query=   " select a.PK_SEQ as dmhId,( select SUM(soluong) from ERP_MUAHANG_SP where MUAHANG_FK = a.PK_SEQ ) AS tongluong, a.NGAYMUA, isnull((select TEN from ERP_DONVITHUCHIEN where PK_SEQ = a.DONVITHUCHIEN_FK), '') as ten, isnull(c.TEN, '') as nccTen, c.MA as nccMa, \n" +
						" SOPO as SOCHUNGTU, \n" +
						" a.TONGTIENAVAT, a.VAT, \n" +
						" a.TONGTIENBVAT, \n" +
						" a.TRANGTHAI, a.NGAYSUA, a.NGAYTAO, d.TEN as nguoitao, e.TEN as nguoisua, isnull(a.DaInPdf, 0) as DaInPdf,  \n" +
						" ISNULL(DUYET.DUYET,0) AS DUYET, ISNULL(tt.ma, 'NA') as tiente, isnull(a.NOTE, '') as NOTE, isnull(a.DACHOT, 0) as DACHOT, \n" +
						" isnull(c.noibo, 0) as noibo, isnull(a.ISTRUONGBPDUYET,0) ISTRUONGBPDUYET, isnull(a.ISKTTDUYET,0) ISKTTDUYET,  ISNULL(a.ISCHOTGANMACP,0) ISCHOTGANMACP, \n" +
						" '' as ktPO, isnull(a.ISTHANHTOAN, 0) ISTHANHTOAN, \n" +
						" ISNULL(ISNULL(ISNULL(ISNULL(ISNULL(A.ISQLTT, A.ISKENH),A.ISDUYETCHI), A.isCS), A.ISGANMACP),0) CAPTREN, \n"+
						" ISNULL(A.ISQLTT,0) ISQLTT, ISNULL(A.ISCS,0) ISCS , ISNULL(A.ISDUYETCHI,0) ISDUYETCHI, ISNULL(A.ISKTTH,0) ISKTTH,  ISNULL(A.ISKTT,0) ISKTT \n"+
						" from erp_muahang a \n" +					 
						" inner join ERP_NHACUNGCAP c on a.NHACUNGCAP_FK = c.PK_SEQ \n" +
						" left join NHANVIEN d on a.NGUOITAO = d.PK_SEQ inner join NHANVIEN e on a.NGUOISUA = e.PK_SEQ \n" +
						" left join ERP_TIENTE tt on a.tiente_fk = tt.pk_seq \n" +					 
						" left join( \n" +
						"	SELECT 	MUAHANG_FK AS DMHID, \n" +
						"			CASE WHEN SUM(QUYETDINH) > 0 THEN \n" +
						"			(CASE WHEN \n" +
						"			( SELECT SUM(TRANGTHAI) \n" +
						"			FROM ERP_DUYETMUAHANG  \n" +				
						"			WHERE MUAHANG_FK = DUYETMUAHANG.MUAHANG_FK AND QUYETDINH = 1) > 0 THEN 0 \n" +
						"			ELSE 1 \n" +
						"			END)	\n" +
						"			ELSE COUNT(TRANGTHAI) - SUM(TRANGTHAI) 	END AS DUYET \n" + 
						"	FROM ERP_DUYETMUAHANG DUYETMUAHANG \n" +
						"	GROUP BY MUAHANG_FK \n" +
						"  )DUYET ON DUYET.DMHID = A.PK_SEQ \n" +
						" where  A.LOAIHANGHOA_FK='2' AND a.congty_fk = '" +obj.getCongtyId() + "' and a.type = '1' and isnull(a.ISDNTT,0) = '1' ";
						//" and a.DONVITHUCHIEN_FK in  " + util.quyen_donvithuchien(obj.getUserId())  + " and a.nhacungcap_fk in " + util.quyen_nhacungcap(obj.getUserId()) + " ";
		
		
		
		String query1 = " select a.PK_SEQ as dmhId,( select SUM(soluong) from ERP_MUAHANG_SP where MUAHANG_FK = a.PK_SEQ ) AS tongluong, a.NGAYMUA, isnull((select TEN from ERP_DONVITHUCHIEN where PK_SEQ = a.DONVITHUCHIEN_FK), '') as ten,  \n" +
						" case when a.NHANVIEN_FK is not null then isnull(c.TEN, '') else kh.TEN end as nccTen, case when a.NHANVIEN_FK is not null then c.MA else kh.MA end as nccMa, SOPO as SOCHUNGTU, \n" +
						" a.TONGTIENAVAT, a.VAT, \n" +
						" a.TONGTIENBVAT, \n" +
						" a.TRANGTHAI, a.NGAYSUA, a.NGAYTAO, d.TEN as nguoitao, e.TEN as nguoisua, isnull(a.DaInPdf, 0) as DaInPdf,  \n" +
						" ISNULL(DUYET.DUYET,0) AS DUYET, ISNULL(tt.ma, 'NA') as tiente, isnull(a.NOTE, '') as NOTE, isnull(a.DACHOT, 0) as DACHOT, \n" +
						"  0 as noibo,  isnull(a.ISTRUONGBPDUYET,0) ISTRUONGBPDUYET, isnull(a.ISKTTDUYET,0) ISKTTDUYET, ISNULL(a.ISCHOTGANMACP,0) ISCHOTGANMACP, \n" +
						" '' as ktPO, isnull(a.ISTHANHTOAN, 0) ISTHANHTOAN, \n" +
						" ISNULL(ISNULL(ISNULL(ISNULL(ISNULL(A.ISQLTT, A.ISKENH),A.ISDUYETCHI), A.isCS), A.ISGANMACP),0) CAPTREN, \n"+
						" ISNULL(A.ISQLTT,0) ISQLTT, ISNULL(A.ISCS,0) ISCS , ISNULL(A.ISDUYETCHI,0) ISDUYETCHI, ISNULL(A.ISKTTH,0) ISKTTH,  ISNULL(A.ISKTT,0) ISKTT \n"+
						" from erp_muahang a \n" +
						" LEFT JOIN    ERP_NHANVIEN c on a.NHANVIEN_FK = c.PK_SEQ  \n" +
						" LEFT JOIN    KHACHHANG kh on a.KHACHHANG_FK = kh.PK_SEQ  \n" +
						" inner join NHANVIEN d on a.NGUOITAO = d.PK_SEQ inner join NHANVIEN e on a.NGUOISUA = e.PK_SEQ \n" +
						" left join ERP_TIENTE tt on a.tiente_fk = tt.pk_seq \n" + 
						" left join( \n" +
						"	SELECT 	MUAHANG_FK AS DMHID, \n" +
						"			CASE WHEN SUM(QUYETDINH) > 0 THEN \n" +
						"			(CASE WHEN \n" +
						"			( SELECT SUM(TRANGTHAI) \n" +
						"			FROM ERP_DUYETMUAHANG  \n" +				
						"			WHERE MUAHANG_FK = DUYETMUAHANG.MUAHANG_FK AND QUYETDINH = 1) > 0 THEN 0 \n" +
						"			ELSE 1 \n" +
						"			END)	\n" +
						"			ELSE COUNT(TRANGTHAI) - SUM(TRANGTHAI) 	END AS DUYET \n" +
						"	FROM ERP_DUYETMUAHANG DUYETMUAHANG \n" +
						"	GROUP BY MUAHANG_FK \n" +
						"  )DUYET ON DUYET.DMHID = A.PK_SEQ \n" +
						" where A.LOAIHANGHOA_FK=2 and  a.congty_fk = '" + obj.getCongtyId() + "' and a.type = '1' and isnull(a.ISDNTT,0) = '1' AND A.NHACUNGCAP_FK IS NULL \n";
						//" and a.DONVITHUCHIEN_FK in  " + util.quyen_donvithuchien(obj.getUserId()) ;

		
 
		String tungay = request.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);
		
		String denngay = request.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);
		
		String dvthId = request.getParameter("dvth");
		if(dvthId == null)
			dvthId = "";
		obj.setDvthId(dvthId);
		
		String ncc = request.getParameter("ncc");
		if(ncc == null)
			ncc = "";
		obj.setNCC(ncc);
		
		String tongtien = request.getParameter("tongtien");
		if(tongtien == null)
			tongtien = "";
		obj.setTongtiensauVat(tongtien);
		
		String sodonmuahang = request.getParameter("sodonmuahang");
		if(sodonmuahang == null)
			sodonmuahang = "";
		obj.setSodonmuahang(sodonmuahang);
		
		String loaihh = request.getParameter("loaihh");
		if(loaihh == null)
			loaihh = "";
		obj.setLoaihanghoa(loaihh);
		
		String loaisanpham = request.getParameter("loaisanpham");
		if(loaisanpham == null)
			loaisanpham = "";
		obj.setLoaisanphamid(loaisanpham);
		
		String mactsp = request.getParameter("mactsp");
		if(mactsp == null)
			mactsp = "";
		obj.setMaCtSp(mactsp);
		
		String trangthai = request.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		
		String nguoitao = request.getParameter("nguoitao");
		if(nguoitao == null)
			nguoitao = "";
		obj.setNguoitaoIds(nguoitao);

		if(tungay.length() > 0) {
			query += " and a.ngaymua >= '" + tungay + "'";
			query1+= " and a.ngaymua >= '" + tungay + "'";
		}
		
		if(denngay.length() > 0) {
			query += " and a.ngaymua <= '" + denngay + "'";
			query1 += " and a.ngaymua <= '" + denngay + "'";
		}
		
		if(dvthId.length() > 0){
			query += " and a.DONVITHUCHIEN_FK = '" + dvthId + "'";
			query1 += " and a.DONVITHUCHIEN_FK = '" + dvthId + "'"; 
		}
		
		if(loaihh.length() > 0) {
			query += " and a.LOAIHANGHOA_FK = '" + loaihh + "'";
			query1 += " and a.LOAIHANGHOA_FK = '" + loaihh + "'";
		}
		
		if(loaisanpham.length() > 0) {
			query += " and a.loaisanpham_fk = '" + loaisanpham + "'";
			query1 += " and a.loaisanpham_fk = '" + loaisanpham + "'";
		}
		
		if(ncc.length() > 0)
		{
			query += " and (dbo.ftBoDau(c.ma) like '%" + util.replaceAEIOU(ncc) + "%' or dbo.ftBoDau(c.ten) like N'%" + util.replaceAEIOU(ncc) + "%') ";
			query1 += " and ( (dbo.ftBoDau(c.ma) like '%" + util.replaceAEIOU(ncc) + "%' or dbo.ftBoDau(c.ten) like N'%" + util.replaceAEIOU(ncc) + "%')  "
				   + "   or (dbo.ftBoDau(kh.ma) like '%" + util.replaceAEIOU(ncc) + "%' or dbo.ftBoDau(kh.ten) like N'%" + util.replaceAEIOU(ncc) + "%') )";	
		}
		if(nguoitao.trim().length() > 0) {
			query += " and a.nguoitao = '" + nguoitao.trim() + "' ";
			query1 += " and a.nguoitao = '" + nguoitao.trim() + "' ";
		}
		
		if(sodonmuahang.length() > 0)
		{
			query += " and a.soPO like '%" + sodonmuahang + "%' ";
			query1 += " and a.soPO like '%" + sodonmuahang + "%' ";
		}
		
		if(tongtien.length() > 0)
		{
			query += " and a.TONGTIENBVAT >= " + tongtien + "";
			query1 += " and a.TONGTIENBVAT >= " + tongtien + "";
		}
		
		if(trangthai.trim().length() > 0)
		{
			if(trangthai.equals("0")) // Chưa chốt
			{
				query += " and a.TRANGTHAI = 0 AND ISNULL(a.DACHOT,0) = 0 ";
				query1 += " and a.TRANGTHAI = 0 AND ISNULL(a.DACHOT,0) = 0 ";
			}
			else if(trangthai.equals("-1")) // Chưa duyệt
			{
				query += " and a.TRANGTHAI = 0 AND ISNULL(a.DACHOT,0) = 1 AND ISNULL(a.ISTRUONGBPDUYET,0) = 0 AND ISNULL(a.ISKTTDUYET,0) = 0 ";
				query1 += " and a.TRANGTHAI = 0 AND ISNULL(a.DACHOT,0) = 1 AND ISNULL(a.ISTRUONGBPDUYET,0) = 0 AND ISNULL(a.ISKTTDUYET,0) = 0 ";
			}
			else if(trangthai.equals("-2")) // Đã duyệt (Trưởng BP)
			{
				query += " and a.TRANGTHAI = 0 AND ISNULL(a.ISTRUONGBPDUYET,0) = 1 AND ISNULL(a.ISKTTDUYET,0) = 0 ";
				query1 += " and a.TRANGTHAI = 0 AND ISNULL(a.ISTRUONGBPDUYET,0) = 1 AND ISNULL(a.ISKTTDUYET,0) = 0 ";
			}
			else if(trangthai.equals("-3")) // Đã duyệt (KTT)
			{
				query += " and a.TRANGTHAI = 0 AND ISNULL(a.ISKTTDUYET,0) = 1 ";
				query1 += " and a.TRANGTHAI = 0 AND ISNULL(a.ISKTTDUYET,0) = 1 ";
			}
			else if(trangthai.equals("1"))  // Đã duyệt (TGĐ)
			{
				query += " and a.TRANGTHAI = 1 ";	
				query1 += " and a.TRANGTHAI = 1 ";	
			}
			else if(trangthai.equals("2"))  // Hoàn tất
			{
				query += " and a.TRANGTHAI = 2 ";
				query1 += " and a.TRANGTHAI = 2 ";
			}
			else if(trangthai.equals("3"))  // Đã xóa
			{
				query += " and a.TRANGTHAI = 3 ";
				query1 += " and a.TRANGTHAI = 3 ";
			}
			else if(trangthai.equals("5"))
			{
				query += " and isnull(a.isthanhtoan,0) = 1 ";
				query1 += " and isnull(a.isthanhtoan,0) = 1 ";
			}
			else  // Đã hủy
			{
				query += " and a.TRANGTHAI = 4 ";
				query1 += " and a.TRANGTHAI = 4 ";
			}
			
		}
 
		if(mactsp.trim().length() > 0)
		{
			query +=" and a.pk_seq in (" +
					"     select distinct muahang_fk from erp_muahang_sp where sanpham_fk in ( select distinct pk_seq from erp_Sanpham where MA like N'%" + mactsp + "%' ) " +
					" ) ";
		}
		
		 System.out.println("Câu search: " +query + " UNION ALL  " +query1);
		 		
		return query +" union all  " +query1;
	}

	private String Delete(String dmhId,String userId)
	{
		dbutils db = new dbutils();
		String query="";
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			query = "select COUNT(*) as sodong from ERP_NHANHANG where MUAHANG_FK = '" + dmhId + "'";
			//System.out.println("1.Query check mua hang: " + query);
			int sodong = 0;
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					sodong = rs.getInt("sodong");
					rs.close();
				}
			}
			
			System.out.println("So dong la: " + sodong + "\n");
			
			if(sodong > 0)
			{
				return "Đơn mua hàng này đã có nhận hàng, bạn phải xóa nhận hàng trước khi xóa đơn mua hàng này";
			}
			
			query = " Update  ERP_MUAHANG set trangthai=3,nguoisua="+userId+"  where pk_seq = '" + dmhId + "'";
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể xóa đơn mua hàng này: " + query;
			}
			
			
			// Cap nhat DADATHANG trong ERP_MUANGUYENLIEUDUKIEN
			query = "UPDATE ERP_MUANGUYENLIEUDUKIEN	" +  
					"SET ERP_MUANGUYENLIEUDUKIEN.DADATHANG = ISNULL(A.SOLUONG, 0) " + 
					"FROM " +
					"( " +
					"	SELECT SUM(SOLUONG) AS SOLUONG, SANPHAM_FK, SUBSTRING(NGAYNHAN, 1, 4) AS NAM,	" + 
					"	CONVERT(INT, SUBSTRING(NGAYNHAN, 6,2)) AS THANG	" +
					"	FROM ERP_MUAHANG_SP " +
					"	WHERE SANPHAM_FK IS NOT NULL	" +
					"	GROUP BY SANPHAM_FK, SUBSTRING(NGAYNHAN, 1, 4),CONVERT(INT, SUBSTRING(NGAYNHAN, 6,2))	" +
					")A  " +
					"WHERE ERP_MUANGUYENLIEUDUKIEN.NAM = A.NAM	" + 
					"AND ERP_MUANGUYENLIEUDUKIEN.THANG = A.THANG	" +
					"AND ERP_MUANGUYENLIEUDUKIEN.SANPHAM_FK = A.SANPHAM_FK ";
			
					
			System.out.println("Cap nhat mua NL: " + query);
			
			db.update(query);
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
			
			return "";
		} 
		catch (Exception e)
		{ 
			db.update("rollback");
			db.shutDown(); 
			return "Loi-khong the xoa don mua hang:"+query; 
		}
		
	}
	
	private String Chot(String dmhId,String userId)
	{
		String msg = "";
		dbutils db = new dbutils();
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "update ERP_MUAHANG set DACHOT = '1' where pk_seq = '" + dmhId + "'" ;
			
			if(!db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể chốt đơn mua hàng này: " + query;
			}
			
			// KIỂM TRA LOẠI CẤP CỦA NHÂN VIÊN NÀY TRONG DUYET MUA HÀNG
			
			query = "SELECT loaicap_fk FROM ERP_DUYETMUAHANG WHERE MUAHANG_FK = "+dmhId + " AND NHANVIEN_FK = "+userId;
			ResultSet rs = db.get(query);
			
			String loaicap_fk = "";
			rs = db.get(query);
			if(rs != null)
			{
				if(rs.next())
				{
					loaicap_fk = rs.getString("loaicap_fk");
					rs.close();
				}
			}
						
			if(loaicap_fk.equals("10000")) // CẤP QUẢN LÝ TRỰC TIẾP
			{
				query = " UPDATE ERP_MUAHANG SET ISQLTT = 1 WHERE PK_SEQ = "+dmhId;	
				
				System.out.println("3.quanlycap : " + query);
				
				if (!db.update(query))
				{
					msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return msg;
				}
				
			}
			else if(loaicap_fk.equals("10001")) // QUẢN LÝ KÊNH
			{
				query = " UPDATE ERP_MUAHANG SET ISKENH = 1 WHERE PK_SEQ = "+dmhId;		
				
				System.out.println("4.quanlycap : " + query);
				
				if (!db.update(query))
				{
					msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return msg;
				}
			}
			else if(loaicap_fk.equals("10002")) // QUẢN LÝ DUYỆT CHI
			{
				query = " UPDATE ERP_MUAHANG SET ISDUYETCHI = 1 WHERE PK_SEQ = "+dmhId;		
				
				System.out.println("5.quanlycap : " + query);
				
				if (!db.update(query))
				{
					msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return msg;
				}
			}
			else if(loaicap_fk.equals("10003")) // QUẢN LÝ CS
			{
				query = " UPDATE ERP_MUAHANG SET ISCS = 1 WHERE PK_SEQ = "+dmhId;	
				
				System.out.println("6.quanlycap : " + query);
				
				if (!db.update(query))
				{
					msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return msg;
				}
			}
			else if(loaicap_fk.equals("10004")) // GẮN MÃ CHI PHÍ
			{
				query = " UPDATE ERP_MUAHANG SET ISGANMACP = 1, TRANGTHAI = 1 WHERE PK_SEQ = "+dmhId;		
				
				System.out.println("7.quanlycap : " + query);
				
				if (!db.update(query))
				{
					msg = "Khong the cap nhat ERP_DUYETMUAHANG: " + query;
					db.getConnection().rollback();
					return msg;
				}
				
			}
			
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		}
		catch( Exception ex)
		{
			ex.printStackTrace();
		}
		
		return msg;
	}
}
