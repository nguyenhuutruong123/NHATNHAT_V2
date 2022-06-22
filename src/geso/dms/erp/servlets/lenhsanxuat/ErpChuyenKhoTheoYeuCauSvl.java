package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.erp.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.center.util.Utility_Kho;
import geso.dms.erp.beans.lenhsanxuat.IErpChuyenkhoSX;
import geso.dms.erp.beans.lenhsanxuat.IErpChuyenkhoSXList;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpChuyenkhoSX;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpChuyenkhoSXList;
import java.io.IOException;
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

public class ErpChuyenKhoTheoYeuCauSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpChuyenKhoTheoYeuCauSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 
		
		IErpChuyenkhoSXList obj;
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
	    
	    String lsxId = util.getId(querystring);
	    obj = new ErpChuyenkhoSXList();
	    if(action.equals("hoantat"))
	    {
	    	String msg = this.HoanTatChuyenKho(lsxId);
			obj.setMsg(msg);
    	}else if (action.equals("chuyenhangsangdiduong")){
    		String msg=this.ChuyenHangSangDiDuong(lsxId);
    		obj.setMsg(msg);
    		
    		
    	}
	    
 
	    String task = request.getParameter("task");
		if(task == null)
			task = "0";
		
		obj.settask(task);
	    obj.setUserId(userId);
	    obj.init_xh("");
		session.setAttribute("obj", obj);
	    String nextJSP = "/SalesUp/pages/Erp/ErpChuyenKho_XuatHangList.jsp";
		response.sendRedirect(nextJSP);
	}
 
	private String ChuyenHangSangDiDuong(String id)
	{
		
		//Lúc này trừ kho chuyển ,trừ số lượng,giảm avai
		//kho nhận vẫn bình thường
		dbutils db = new dbutils();
		
		try 
		{
			Utility util=new Utility();
			
			db.getConnection().setAutoCommit(false);
			
			String sql= " select NGAYCHUYEN, NOIDUNGXUAT_FK, COXACNHANNHAPKHO,(select COUNT(*) from ERP_CHUYENKHO_SP_XUATHANG where chuyenkho_fk=ck.PK_SEQ ) as dong "+
						" from erp_chuyenkho ck  where  isnull(ISDANGVANCHUYEN,'0') ='0' and  trangthai=2  and PK_SEQ="+id;
			System.out.println(sql);
			
			String coxanhannhaphang="";
			String noidungnhan_fk = "";
			String ngaychot = "";
			
			int sodong=0;
			ResultSet rs=db.get(sql);
			if(rs.next())
			{
				coxanhannhaphang=rs.getString("COXACNHANNHAPKHO");
				sodong=rs.getInt("dong");
				
				ngaychot = rs.getString("NGAYCHUYEN");
				noidungnhan_fk = rs.getString("NOIDUNGXUAT_FK");
			}
			else
			{
				db.getConnection().rollback();
				return "Không xác định được phiếu chuyển";
			}
			rs.close();
		 
			String msg1=util.CheckNgayGhiNhanHopLe_Provence(db, ngaychot);
			if(msg1.length()> 0){
				
				db.getConnection().rollback();
				return msg1;
				
			}
				// Check kho chuyển có còn đủ hàng theo check tồn hiện tại và tồn kho ngày
				 
				
			
				sql = 	" SELECT NCC_CHUYEN_FK,NCC_NHAN_FK , SP.MA , CK_SP.DONGIA* CK_SP.SOLUONG AS THANHTIEN , CK_SP.DONGIA ,CK.KHONHAN_FK  " +
						",LOAICHUYENKYGUI,LOAINHANKYGUI,NCC_CHUYEN_FK ,CK.KHOXUAT_FK,CK_SP.SANPHAM_FK,CK_SP.SOLO,CK_SP.SOLUONG  "+
						" FROM ERP_CHUYENKHO_SP_XUATHANG CK_SP INNER JOIN ERP_CHUYENKHO CK "+
						" ON CK.PK_SEQ=CK_SP.CHUYENKHO_FK   INNER JOIN SANPHAM SP ON SP.PK_SEQ=CK_SP.SANPHAM_FK  "+
						" WHERE CK.PK_SEQ= "+id;
				  rs=db.get(sql);
				while (rs.next()){
					// Giảm kho xuất
					String spid=rs.getString("sanpham_fk");
					String khonhan_fk=rs.getString("khonhan_fk");
					String khoxuat_fk=rs.getString("KHOXUAT_FK");
					String soluong=rs.getString("soluong");
					String masp=rs.getString("ma");
					String solo=rs.getString("solo").trim();
					String NCC_CHUYEN_FK=rs.getString("NCC_CHUYEN_FK");
					String NCC_NHAN_FK=rs.getString("NCC_NHAN_FK");
					 
					/*sql ="update erp_khott_sanpham set booked=booked -" +soluong +" ,soluong=soluong - "+soluong+" where khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
					if(db.updateReturnInt(sql)!= 1)
					{
						db.getConnection().rollback();
						return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
					}*/
					String spid_= rs.getString("sanpham_fk");
					String khoit_cn=  rs.getString("khoxuat_fk");
					double soluongct_=0;
					double booked_ct_=0;
					double avai_ct_=0;
					try{ booked_ct_ =(-1)* Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
					try{ soluongct_ =(-1)*  Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
					
					  msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0 ,id,"ErpChuyenkhoTheoYeuCau.java 158");
					
					if(msg1.length() >0 )
					{
						db.getConnection().rollback();
						 
						return msg1;
					}
					
					
					// kho chi tiết có số lô
					
					sql=" update erp_khott_sp_chitiet set booked=booked -" +soluong +" ,soluong=soluong -"+soluong+" where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
					if(db.updateReturnInt(sql)!= 1)
					{
						db.getConnection().rollback();
						return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
					}
					// nếu là kho ký gửi thì giảm kho ký gửi
					if(rs.getString("khoxuat_fk").trim().equals("100015")){
						if(rs.getString("LOAICHUYENKYGUI").trim().equals("0")){
							
							sql=" update ERP_KHOTT_SP_CHITIET_KYGUINPP set booked =booked - "+rs.getString("soluong")+" , " +
								" soluong=soluong - "+rs.getString("soluong")+" " +
								" where npp_fk="+rs.getString("NCC_CHUYEN_FK")+" and rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"'  " +
								" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
								" and sanpham_fk="+rs.getString("sanpham_fk");
							
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
							}
							// kho tong ky gui ERP_KHOTT_SANPHAM_KYGUINPP
							
							sql=" update ERP_KHOTT_SANPHAM_KYGUINPP set booked =booked - "+rs.getString("soluong")+" , " +
							" soluong=soluong - "+rs.getString("soluong")+" " +
							" where npp_fk="+rs.getString("NCC_CHUYEN_FK") +
							" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
							" and sanpham_fk="+rs.getString("sanpham_fk");
						
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
							}
						
							 
							
						}else{
							sql=" update ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN set booked =booked - "+rs.getString("soluong")+" , " +
								" soluong=soluong - "+rs.getString("soluong")+" " +
								" where nhanvien_fk ="+rs.getString("NCC_CHUYEN_FK")+" and  rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"'  " +
								" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
								" and sanpham_fk="+rs.getString("sanpham_fk");
						
							if(db.updateReturnInt(sql) != 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
							}
							
							//CAP NHAT KHO KY GUI NHÂN VIÊN
							sql=" update ERP_KHOTT_SANPHAM_KYGUINHANVIEN set booked =booked - "+rs.getString("soluong")+" , " +
							" soluong=soluong - "+rs.getString("soluong")+" " +
							" where nhanvien_fk ="+rs.getString("NCC_CHUYEN_FK") +
							" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
							" and sanpham_fk="+rs.getString("sanpham_fk");
					
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_KHOTT_SANPHAM_KYGUINHANVIEN " + sql;
							}
							
						 
						}
					}else if(rs.getString("khoxuat_fk").trim().equals("100013")){
						//CAP NHAT KHO GIA CONG
						sql=" update ERP_KHOTT_SP_CHITIET_NCC set booked =booked - "+rs.getString("soluong")+" , " +
						" soluong=soluong - "+rs.getString("soluong")+" " +
						" where rtrim(ltrim(SOLO))='"+solo.trim()+"' and  NCC_FK  ="+rs.getString("NCC_CHUYEN_FK") +
						" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
						" and sanpham_fk="+rs.getString("sanpham_fk");
						
						if(db.updateReturnInt(sql)!= 1)
						{
							db.getConnection().rollback();
							return "Không thể cập nhật ERP_KHOTT_SP_CHITIET_NCC " + sql;
						}
					}
 
					//không tăng kho nhận
 
				}
	 
				sql="Update Erp_chuyenkho set ISDANGVANCHUYEN =1 where pk_seq="+id;
				if(db.updateReturnInt(sql)< 1)
				{
					db.getConnection().rollback();
					return "Không thể cập nhật  : " + sql;
				}
				
				sql=" SELECT count(SANPHAM_FK) as count  "+
				" FROM ERP_YEUCAUCHUYENKHO_SANPHAM A "+
				" WHERE A.CHUYENKHO_FK= (select yeucauchuyenkho_fk from erp_chuyenkho where pk_seq="+id+" ) "+
				" AND A.SOLUONGYEUCAU -ISNULL((  "+
				" SELECT SUM(SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP  "+
				" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "+
				" WHERE CKSP.SANPHAM_FK=A.SANPHAM_FK AND CK.YEUCAUCHUYENKHO_FK=A.CHUYENKHO_FK AND CK.TRANGTHAI=3),0) >0";
				ResultSet rscheckhoantat=db.get(sql);
				
				 rscheckhoantat.next();
				 
				 if(rscheckhoantat.getInt("count") ==0){
					 
					 //set trạng thái hoàn tất cho chuyển kho  ,bao gồm luôn gia công  không tính cái mua hàng 
					 
					 sql="Update  ERP_YEUCAUCHUYENKHO set trangthai =3 where  pk_seq= (select yeucauchuyenkho_fk from erp_chuyenkho where pk_seq="+id+" ) ";
						if(db.updateReturnInt(sql)< 1)
						{
							db.getConnection().rollback();
							return "Không thể cập nhật  : " + sql;
						}
				 }
				 rscheckhoantat.close();
				
				 
				 
 
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {	}
			db.shutDown();
			return "Không thể hoàn tất chuyển kho: " + e.getMessage();
		}
		
		
		return "";
	}

	private String HoanTatChuyenKho(String id)
	{
		dbutils db = new dbutils();
		
		try 
		{
			Utility util=new Utility();
			
			db.getConnection().setAutoCommit(false);
			
			String sql= " select  isnull(ISDANGVANCHUYEN,'0') as ISDANGVANCHUYEN ,NGAYCHUYEN, NOIDUNGXUAT_FK, COXACNHANNHAPKHO,(select COUNT(*) from ERP_CHUYENKHO_SP_XUATHANG where chuyenkho_fk=ck.PK_SEQ ) as dong "+
						" from erp_chuyenkho ck  where trangthai=2  and PK_SEQ="+id;
			System.out.println(sql);
			
			String coxanhannhaphang="";
			String noidungnhan_fk = "";
			String ngaychot = "";
			String ISDANGVANCHUYEN="";
			
			int sodong=0;
			ResultSet rs=db.get(sql);
			if(rs.next())
			{
				coxanhannhaphang=rs.getString("COXACNHANNHAPKHO");
				sodong=rs.getInt("dong");
				
				ngaychot = rs.getString("NGAYCHUYEN");
				noidungnhan_fk = rs.getString("NOIDUNGXUAT_FK");
				ISDANGVANCHUYEN=rs.getString("ISDANGVANCHUYEN");
				
			}
			else
			{
				db.getConnection().rollback();
				return "Không xác định được phiếu chuyển";
			}
			rs.close();
		 
			String msg1=util.CheckNgayGhiNhanHopLe_Provence(db, ngaychot);
			if(msg1.length()> 0){
				
				db.getConnection().rollback();
				return msg1;
				
			}
				// Check kho chuyển có còn đủ hàng theo check tồn hiện tại và tồn kho ngày
				 
				
			
				sql = 	" SELECT NCC_CHUYEN_FK,NCC_NHAN_FK , SP.MA , CK_SP.DONGIA* CK_SP.SOLUONG AS THANHTIEN , CK_SP.DONGIA ,CK.KHONHAN_FK  " +
						",LOAICHUYENKYGUI,LOAINHANKYGUI,NCC_CHUYEN_FK ,CK.KHOXUAT_FK,CK_SP.SANPHAM_FK,CK_SP.SOLO,CK_SP.SOLUONG  "+
						" FROM ERP_CHUYENKHO_SP_XUATHANG CK_SP INNER JOIN ERP_CHUYENKHO CK "+
						" ON CK.PK_SEQ=CK_SP.CHUYENKHO_FK   INNER JOIN SANPHAM SP ON SP.PK_SEQ=CK_SP.SANPHAM_FK  "+
						" WHERE CK.PK_SEQ= "+id;
				  rs=db.get(sql);
				while (rs.next()){
					String spid=rs.getString("sanpham_fk");
					String khonhan_fk=rs.getString("khonhan_fk");
					String khoxuat_fk=rs.getString("KHOXUAT_FK");
					String soluong=rs.getString("soluong");
					String masp=rs.getString("ma");
					String solo=rs.getString("solo").trim();
					String NCC_CHUYEN_FK=rs.getString("NCC_CHUYEN_FK");
					String NCC_NHAN_FK=rs.getString("NCC_NHAN_FK");
					
					if(noidungnhan_fk.equals("100006") && ISDANGVANCHUYEN.equals("1")){
						//không làm gì nữa,vì chỗ chuyển hàng đi đường đã giảm kho nhận rồi
						
					}else{
					// Giảm kho xuất
					
					 
					/*sql ="update erp_khott_sanpham set booked=booked -" +soluong +" ,soluong=soluong - "+soluong+" where khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
					if(db.updateReturnInt(sql)!= 1)
					{
						db.getConnection().rollback();
						return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
					}*/
						String spid_=rs.getString("sanpham_fk");
						String khoit_cn=  rs.getString("khoxuat_fk");
						double soluongct_=0;
						double booked_ct_=0;
						double avai_ct_=0;
						try{ booked_ct_ =(-1)* Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
						try{ soluongct_ =(-1)*  Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
						
						  msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0 ,id,"ErpChuyenkhoTheoYeuCau.java 393");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							 
							return msg1;
						}
						
						
					// kho chi tiết có số lô
					
					sql=" update erp_khott_sp_chitiet set booked=booked -" +soluong +" ,soluong=soluong -"+soluong+" where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+rs.getString("khoxuat_fk")+" and sanpham_fk="+rs.getString("sanpham_fk");
					if(db.updateReturnInt(sql)!= 1)
					{
						db.getConnection().rollback();
						return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
					}
					// nếu là kho ký gửi thì giảm kho ký gửi
					if(rs.getString("khoxuat_fk").trim().equals("100015")){
						if(rs.getString("LOAICHUYENKYGUI").trim().equals("0")){
							
							sql=" update ERP_KHOTT_SP_CHITIET_KYGUINPP set booked =booked - "+rs.getString("soluong")+" , " +
								" soluong=soluong - "+rs.getString("soluong")+" " +
								" where npp_fk="+rs.getString("NCC_CHUYEN_FK")+" and rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"'  " +
								" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
								" and sanpham_fk="+rs.getString("sanpham_fk");
							
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
							}
							// kho tong ky gui ERP_KHOTT_SANPHAM_KYGUINPP
							
							sql=" update ERP_KHOTT_SANPHAM_KYGUINPP set booked =booked - "+rs.getString("soluong")+" , " +
							" soluong=soluong - "+rs.getString("soluong")+" " +
							" where npp_fk="+rs.getString("NCC_CHUYEN_FK") +
							" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
							" and sanpham_fk="+rs.getString("sanpham_fk");
						
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
							}
						
							 
							
						}else{
							sql=" update ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN set booked =booked - "+rs.getString("soluong")+" , " +
								" soluong=soluong - "+rs.getString("soluong")+" " +
								" where nhanvien_fk ="+rs.getString("NCC_CHUYEN_FK")+" and  rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"'  " +
								" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
								" and sanpham_fk="+rs.getString("sanpham_fk");
						
							if(db.updateReturnInt(sql) != 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_CHUYENKHO_SANPHAM " + sql;
							}
							
							//CAP NHAT KHO KY GUI NHÂN VIÊN
							sql=" update ERP_KHOTT_SANPHAM_KYGUINHANVIEN set booked =booked - "+rs.getString("soluong")+" , " +
							" soluong=soluong - "+rs.getString("soluong")+" " +
							" where nhanvien_fk ="+rs.getString("NCC_CHUYEN_FK") +
							" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
							" and sanpham_fk="+rs.getString("sanpham_fk");
					
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật ERP_KHOTT_SANPHAM_KYGUINHANVIEN " + sql;
							}
							
						 
						}
					}else if(rs.getString("khoxuat_fk").trim().equals("100013")){
						//CAP NHAT KHO GIA CONG
						sql=" update ERP_KHOTT_SP_CHITIET_NCC set booked =booked - "+rs.getString("soluong")+" , " +
						" soluong=soluong - "+rs.getString("soluong")+" " +
						" where rtrim(ltrim(SOLO))='"+solo.trim()+"' and  NCC_FK  ="+rs.getString("NCC_CHUYEN_FK") +
						" and khott_fk= "+rs.getString("khoxuat_fk")+" " +
						" and sanpham_fk="+rs.getString("sanpham_fk");
						
						if(db.updateReturnInt(sql)!= 1)
						{
							db.getConnection().rollback();
							return "Không thể cập nhật ERP_KHOTT_SP_CHITIET_NCC " + sql;
						}
					}
					
					}
 
					// Tăng kho nhận
					if(khonhan_fk!=null){
						
						
						sql=" INSERT INTO ERP_CHUYENKHO_SP_NHANHANG (CHUYENKHO_FK,SANPHAM_FK,SOLO,VITRI,SOLUONG) " +
						  " VALUES ("+id+", "+spid+",'"+solo+"',100000,'"+soluong+"')";
						if(db.updateReturnInt(sql)!= 1)
						{
							db.getConnection().rollback();
							return "Không thể cập nhật : " + sql;
						}
						
						/*sql="select sanpham_fk from erp_khott_sanpham  where sanpham_fk="+spid+" and khott_fk="+khonhan_fk+"";
						ResultSet rscheck=db.get(sql);
						if(rscheck.next()){
						
							sql ="update erp_khott_sanpham set available=available + "+soluong+",soluong=soluong + "+soluong+" " +
									"where khott_fk= "+khonhan_fk+" and sanpham_fk="+spid;
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật : " + sql;
							}
						}else{
							sql = 	" insert into erp_khott_sanpham (khott_fk,sanpham_fk,giaton,soluong,thanhtien,booked,available,masp) " +
									" select "+khonhan_fk+",sanpham_fk,giaton,"+soluong+",giaton*"+soluong+",0,"+soluong+",masp  " +
									" from erp_khott_sanpham where SANPHAM_FK="+spid+" AND KHOTT_FK= "+khoxuat_fk;
						 
									
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật : " + sql;
							}
						}
						rscheck.close();*/
						String spid_=spid;
						String khoit_cn=  khonhan_fk;
						double soluongct_=0;
						double booked_ct_=0;
						double avai_ct_=0;
						try{ avai_ct_ =  Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
						try{ soluongct_ =   Double.parseDouble(soluong.replaceAll(",", ""));}catch(Exception err){}
						
						  msg1= Utility_Kho.Update_Kho_Sp_Provence(db, khoit_cn, spid_,  soluongct_,booked_ct_,avai_ct_, 0 ,id,"ErpChuyenkhoTheoYeuCau.java 531");
						
						if(msg1.length() >0 )
						{
							db.getConnection().rollback();
							 
							return msg1;
						}
						
						
						// kho chi tiết có số lô
						
						sql=	" select sanpham_fk from erp_khott_sp_chitiet   " +
								" where rtrim(ltrim(SOLO))='"+solo.trim()+"'  and sanpham_fk="+spid+" and khott_fk="+khonhan_fk+"";
						
						ResultSet rscheck=db.get(sql);
						if(rscheck.next()){
							sql=" update erp_khott_sp_chitiet set available=available + "+soluong+" ,soluong=soluong + "+soluong+"" +
									" where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+khonhan_fk+" and sanpham_fk="+spid;
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật  : " + sql;
							}
						}else{
							sql=" INSERT INTO ERP_KHOTT_SP_CHITIET ( KHOTT_FK,SANPHAM_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,NGAYHETHAN,BIN,NGAYSANXUAT,NGAYNHAPKHO,GIACHIPHINL,GIACHIPHIKHAC,GIATHEOLO)"+
								" SELECT "+khonhan_fk+",SANPHAM_FK,"+soluong+",0,"+soluong+",SOLO,NGAYHETHAN,BIN,NGAYSANXUAT,NGAYNHAPKHO,GIACHIPHINL,GIACHIPHIKHAC,GIATHEOLO FROM  ERP_KHOTT_SP_CHITIET " +
								" WHERE rtrim(ltrim(SOLO))='"+solo.trim()+"' and SANPHAM_FK="+spid+" AND KHOTT_FK= "+khoxuat_fk;
							
							if(db.updateReturnInt(sql)!= 1)
							{
								db.getConnection().rollback();
								return "Không thể cập nhật:  " + sql;
							}
							
						}
						// kho nhận là kho nhờ gia công// tăng kho gia công
						
						if(khonhan_fk.equals("100013")){
							sql="select * from ERP_KHOTT_SP_CHITIET_NCC where khott_fk="+khonhan_fk+" and sanpham_fk ="+spid+" and rtrim(ltrim(SOLO)) ='"+solo.trim()+"' and ncc_fk="+NCC_NHAN_FK;
							rscheck=db.get(sql);
							if(rscheck.next()){
								sql=" update ERP_KHOTT_SP_CHITIET_NCC set soluong=soluong + "+soluong+",available=available + "+soluong+"  " +
										"where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+khonhan_fk+" " +
												" and sanpham_fk="+spid +" and ncc_fk="+NCC_NHAN_FK;
								
								if(db.updateReturnInt(sql)!= 1)
								{
									db.getConnection().rollback();
									return "Không thể cập nhật  : " + sql;
								}
							}else{
								sql=" INSERT INTO ERP_KHOTT_SP_CHITIET_NCC (KHOTT_FK,SANPHAM_FK,NCC_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,BIN,NGAYSANXUAT,NGAYHETHAN,NGAYNHAPKHO) "+
									" select "+khonhan_fk+",SANPHAM_FK,"+NCC_NHAN_FK+","+soluong+",0,"+soluong+",SOLO,BIN,NGAYSANXUAT,NGAYHETHAN,NGAYNHAPKHO  " +
									" from ERP_KHOTT_SP_CHITIET where KHOTT_FK="+khoxuat_fk+" and SANPHAM_FK="+spid+" and rtrim(ltrim(SOLO))='"+solo+"'";
								
								if(db.updateReturnInt(sql)!= 1)
								{
									db.getConnection().rollback();
									return "Không thể cập nhật  : " + sql;
								}
							}
							
						}else if(khonhan_fk.equals("100015")) {
							
							
							if(rs.getString("LOAINHANKYGUI").trim().equals("0")){
								// ky gui la khach hàng
								sql="select * from ERP_KHOTT_SP_CHITIET_KYGUINPP where khott_fk="+khonhan_fk+" and sanpham_fk ="+spid+" and rtrim(ltrim(SOLO)) ='"+solo.trim()+"' and npp_fk="+NCC_NHAN_FK;
								rscheck=db.get(sql);
								if(rscheck.next()){
									sql=" update ERP_KHOTT_SP_CHITIET_KYGUINPP set soluong=soluong + "+soluong+",available=available + "+soluong+"  " +
										" where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+khonhan_fk+" " +
										" and sanpham_fk="+rs.getString("sanpham_fk") +" and npp_fk="+NCC_NHAN_FK;
									
									if(db.updateReturnInt(sql)!= 1)
									{
										db.getConnection().rollback();
										return "Không thể cập nhật  : " + sql;
									}
								}else{
									sql=" INSERT INTO ERP_KHOTT_SP_CHITIET_KYGUINPP (KHOTT_FK,SANPHAM_FK,NPP_FK,SOLUONG,BOOKED,AVAILABLE,SOLO,BIN,NGAYSANXUAT,NGAYHETHAN,NGAYNHAPKHO)  "+
										" select "+khonhan_fk+",SANPHAM_FK,"+NCC_NHAN_FK+","+soluong+",0,"+soluong+",SOLO,BIN,NGAYSANXUAT,NGAYHETHAN,NGAYNHAPKHO  " +
										" from ERP_KHOTT_SP_CHITIET where KHOTT_FK="+khoxuat_fk+" and SANPHAM_FK="+spid+" and rtrim(ltrim(SOLO))='"+solo+"'";
									
									if(db.updateReturnInt(sql)!= 1)
									{
										db.getConnection().rollback();
										return "Không thể cập nhật  : " + sql;
									}
								}
								
								// ***************************KHO TỔNG ***********
									sql="select * from ERP_KHOTT_SANPHAM_KYGUINPP where khott_fk="+khonhan_fk+" and sanpham_fk ="+spid+"  and npp_fk="+NCC_NHAN_FK;
									rscheck=db.get(sql);
									if(rscheck.next()){
										sql=" update ERP_KHOTT_SANPHAM_KYGUINPP set soluong=soluong + "+soluong+",available=available + "+soluong+"  " +
											" where   khott_fk= "+khonhan_fk+" " +
											" and sanpham_fk="+rs.getString("sanpham_fk") +" and npp_fk="+NCC_NHAN_FK;
										
										if(db.updateReturnInt(sql)!= 1)
										{
											db.getConnection().rollback();
											return "Không thể cập nhật  : " + sql;
										}
									}else{
										sql=" INSERT INTO ERP_KHOTT_SANPHAM_KYGUINPP (KHOTT_FK,SANPHAM_FK,NPP_FK,SOLUONG,BOOKED,AVAILABLE,THANHTIEN,GIATON)  "+
											" VALUES ( "+khonhan_fk+","+spid+","+NCC_NHAN_FK+","+soluong+",0,"+soluong+",0,0) " ;
 
										if(db.updateReturnInt(sql)!= 1)
										{
											db.getConnection().rollback();
											return "Không thể cập nhật  : " + sql;
										}
									}
								
								//****************************
							 
								
								
							}else{
								
								// ky gui la nhanvien,loainhankygui=1 là nhân viên
								sql="select * from ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN where khott_fk="+khonhan_fk+" and sanpham_fk ="+spid+" and rtrim(ltrim(SOLO)) ='"+solo.trim()+"' and nhanvien_fk="+NCC_NHAN_FK;
								rscheck=db.get(sql);
								if(rscheck.next()){
									sql=" update ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN set soluong=soluong + "+soluong+",available=available + "+soluong+"  " +
										" where rtrim(ltrim(SOLO))='"+rs.getString("solo").trim()+"' and khott_fk= "+khonhan_fk+" " +
										" and sanpham_fk="+rs.getString("sanpham_fk") +" and nhanvien_fk="+NCC_NHAN_FK;
									
									if(db.updateReturnInt(sql)!= 1)
									{
										db.getConnection().rollback();
										return "Không thể cập nhật  : " + sql;
									}
								}else{
									sql=" INSERT INTO ERP_KHOTT_SP_CHITIET_KYGUINHANVIEN (KHOTT_FK,SANPHAM_FK,NHANVIEN_FK ,SOLUONG,BOOKED,AVAILABLE,SOLO,BIN,NGAYSANXUAT,NGAYHETHAN,NGAYNHAPKHO)  "+
										" select "+khonhan_fk+",SANPHAM_FK,"+NCC_NHAN_FK+","+soluong+",0,"+soluong+",SOLO,BIN,NGAYSANXUAT,NGAYHETHAN,NGAYNHAPKHO  " +
										" from ERP_KHOTT_SP_CHITIET where KHOTT_FK="+khoxuat_fk+" and SANPHAM_FK="+spid+" and rtrim(ltrim(SOLO))='"+solo+"'";
									
									if(db.updateReturnInt(sql)!= 1)
									{
										db.getConnection().rollback();
										return "Không thể cập nhật  : " + sql;
									}
								}
								/// ********************KHO  ERP_KHOTT_SANPHAM_KYGUINHANVIEN*******************
								
								sql="select * from ERP_KHOTT_SANPHAM_KYGUINHANVIEN where khott_fk="+khonhan_fk+" and sanpham_fk ="+spid+"  and nhanvien_fk="+NCC_NHAN_FK;
								rscheck=db.get(sql);
								if(rscheck.next()){
									sql=" update ERP_KHOTT_SANPHAM_KYGUINHANVIEN  set soluong=soluong + "+soluong+",available=available + "+soluong+"  " +
										" where  khott_fk= "+khonhan_fk+" " +
										" and sanpham_fk="+rs.getString("sanpham_fk") +" and nhanvien_fk="+NCC_NHAN_FK;
									
									if(db.updateReturnInt(sql)!= 1)
									{
										db.getConnection().rollback();
										return "Không thể cập nhật  : " + sql;
									}
								}else{
										sql=" INSERT INTO ERP_KHOTT_SANPHAM_KYGUINHANVIEN (KHOTT_FK,SANPHAM_FK,NHANVIEN_FK,SOLUONG,BOOKED,AVAILABLE,THANHTIEN,GIATON)  "+
										" VALUES ( "+khonhan_fk+","+spid+","+NCC_NHAN_FK+","+soluong+",0,"+soluong+",0,0) " ;

										if(db.updateReturnInt(sql)!= 1)
										{
											db.getConnection().rollback();
											return "Không thể cập nhật  : " + sql;
										}
								}
								
								//***************************************************************************
							  
							}
						}
						
					}
 
				}
	 
				sql="Update Erp_chuyenkho set trangthai =3 where pk_seq="+id;
				if(db.updateReturnInt(sql)< 1)
				{
					db.getConnection().rollback();
					return "Không thể cập nhật  : " + sql;
				}
				
				sql=" SELECT count(SANPHAM_FK) as count  "+
				" FROM ERP_YEUCAUCHUYENKHO_SANPHAM A "+
				" WHERE A.CHUYENKHO_FK= (select yeucauchuyenkho_fk from erp_chuyenkho where pk_seq="+id+" ) "+
				" AND A.SOLUONGYEUCAU -ISNULL((  "+
				" SELECT SUM(SOLUONGNHAN) FROM ERP_CHUYENKHO_SANPHAM CKSP  "+
				" INNER JOIN ERP_CHUYENKHO CK ON CK.PK_SEQ=CKSP.CHUYENKHO_FK "+
				" WHERE CKSP.SANPHAM_FK=A.SANPHAM_FK AND CK.YEUCAUCHUYENKHO_FK=A.CHUYENKHO_FK AND CK.TRANGTHAI=3),0) >0";
				ResultSet rscheckhoantat=db.get(sql);
				
				 rscheckhoantat.next();
				 
				 if(rscheckhoantat.getInt("count") ==0){
					 
					 //set trạng thái hoàn tất cho chuyển kho  ,bao gồm luôn gia công  không tính cái mua hàng 
					 
					 sql="Update  ERP_YEUCAUCHUYENKHO set trangthai =3 where  pk_seq= (select yeucauchuyenkho_fk from erp_chuyenkho where pk_seq="+id+" ) ";
						if(db.updateReturnInt(sql)< 1)
						{
							db.getConnection().rollback();
							return "Không thể cập nhật  : " + sql;
						}
				 }
				 rscheckhoantat.close();
				
				//GHI NHAN BUT TOAN
				String thang = ngaychot.substring(5, 7);
				String nam = ngaychot.substring(0, 4);
				
				String queryTK = 	"select b.SOLUONGXUAT, e.pk_seq as taikhoanCO, N'Sản phẩm' as doituongCO, c.PK_SEQ as madoituongCO, a.NOIDUNGXUAT_FK,  " + 
									"       ( select pk_seq from ERP_TAIKHOANKT  " + 
									"		  where SOHIEUTAIKHOAN = (  case a.NOIDUNGXUAT_FK when 100014 then '63270000' " + 
									"											 when 100025 then '63250000' " + 
									"											 when 100024 then ( select SOHIEUTAIKHOAN from ERP_TAIKHOANKT where PK_SEQ = ( case when a.NCC_CHUYEN_FK IS NOT NULL  " +
									"																																then ( select TAIKHOANKT_FK from ERP_NHACUNGCAP where PK_SEQ = a.NCC_CHUYEN_FK )  " +
									"																																else ( select TAIKHOANKT_FK from NHAPHANPHOI where PK_SEQ = a.NPP_FK ) end ) ) " + 
									"											 when 100020 then '15700000' " + 
									"											 when 100003 then '63260000' " + 
									"											 when 100012 then '62721000' " + 
									"											 when 100010 then ( case d.PK_SEQ when '100007' then '64122000' else '64121000' end ) " + 
									"											 when 100008 then '64221000' end )  " + 
									"		) as taikhoanNO, N'' as doituongNO, '' as madoituongNO, " + 
									"		ISNULL ( ( select top(1) AVG(giaton) from ERP_TONKHOTHANG where SANPHAM_FK = c.PK_SEQ group by NAM, THANG order by NAM desc, THANG desc  ), 0 ) AS DONGIA_TON   " + 
									"from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b on a.PK_SEQ = b.CHUYENKHO_FK " + 
									"		inner join SANPHAM c on b.SANPHAM_FK = c.PK_SEQ " + 
									"		inner join ERP_LOAISANPHAM d on c.LOAISANPHAM_FK = d.PK_SEQ " + 
									"		inner join ERP_TAIKHOANKT e on d.TAIKHOANKT_FK = e.PK_SEQ " + 
									"where a.PK_SEQ = '" + id + "' ";
				
				System.out.println("----LAY TAI KHOAN: " + queryTK);
				String doituongNO = "";
				String madoituongNO = "";
				String doituongCO = "";
				String madoituongCO = "";
				
				 
				if(queryTK.trim().length() > 0 && !noidungnhan_fk.equals("100006") && !noidungnhan_fk.equals("100009") )  //CHUYEN KHO BEN NGOAI, NOI BO KHONG GHI NHAN
				{
					ResultSet tkRs = db.get(queryTK);
					
					if(tkRs != null)
					{
						while(tkRs.next())
						{
							String taikhoanNO_SOHIEU = "";
							String taikhoanCO_SOHIEU = "";
							
							doituongNO = tkRs.getString("doituongNO");
							madoituongNO = tkRs.getString("madoituongNO");
							doituongCO = tkRs.getString("doituongCO");
							madoituongCO = tkRs.getString("madoituongCO");
							String tiente_fk = "100000";
							
							double dongiaTON = tkRs.getDouble("DONGIA_TON");
							double soluong = tkRs.getDouble("SOLUONGXUAT");

							taikhoanNO_SOHIEU = tkRs.getString("taikhoanNO") == null ? "" : tkRs.getString("taikhoanNO");
							taikhoanCO_SOHIEU = tkRs.getString("taikhoanCO") == null ? "" : tkRs.getString("taikhoanCO");
								
							//    Chi phí NVL dùng
							if(taikhoanNO_SOHIEU.trim().length() <= 0 || taikhoanCO_SOHIEU.trim().length() <= 0)
							{
								db.getConnection().rollback();
								String msg = "22.Loại sản phẩm và nội dung nhập tương ứng chưa có tài khoản kế toán đi kèm, vui lòng kiểm tra lại dữ liệu nền.";
								tkRs.close();
								return msg;
							}
							
							double tonggiatri = Math.round(dongiaTON * soluong);
							String msg = util.Update_TaiKhoan( db, thang, nam, ngaychot, ngaychot, "Chuyển kho", id, taikhoanNO_SOHIEU, taikhoanCO_SOHIEU, "", 
														Double.toString(tonggiatri), Double.toString(tonggiatri), doituongNO, madoituongNO, doituongCO, madoituongCO, "0", Double.toString(soluong), Double.toString(dongiaTON), tiente_fk, Double.toString(dongiaTON), "1", dongiaTON + "*" + soluong, dongiaTON + "*" + soluong, "" );
							if(msg.trim().length() > 0)
							{
								db.getConnection().rollback();
								tkRs.close();
								return msg;
							}
							
						}	
						tkRs.close();
					}
					
				}
				
				
				/*if(noidungnhan_fk.equals("100014"))  // Xuất hủy (Kho sd)
				{
					
				}
				else
				{
					if(noidungnhan_fk.equals("100025"))  //  Xuất hủy (Kế toán)
					{
						
					}
					else
					{
						if(noidungnhan_fk.equals("100024"))  //  Xuất đổi hàng
						{
							
						}
						else
						{
							if(noidungnhan_fk.equals("100003"))  //  Xuất bán hàng nội bộ
							{
								
							}
							else
							{
								if(noidungnhan_fk.equals("100012"))  //  Xuất sử dụng nội bộ - Cho sản xuất
								{
									
								}
								else
								{
									if(noidungnhan_fk.equals("100010"))  //  Xuất sử dụng nội bộ - Cho bán hàng
									{
										
									}
									else
									{
										if(noidungnhan_fk.equals("100008"))  //  Xuất sử dụng nội bộ - Cho quản lý
										{
											
										}
									}
								}
							}
						}
					}
				}*/
				
				
 
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		} 
		catch (Exception e) 
		{
		
			e.printStackTrace();
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {	}
			db.shutDown();
			return "Không thể hoàn tất chuyển kho: " + e.getMessage();
		}
		
		
		return "";
	}

	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	 
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    String action = request.getParameter("action");
	    if (action == null)
	    {
	    	action = "";
	    }
	   
	    
	    
		IErpChuyenkhoSXList obj = new ErpChuyenkhoSXList();
	    String task = request.getParameter("task");
		if(task == null)
			task = "";
		
		obj.settask(task);
 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    
	   
	    
	    if(task == null)
			task = "0";
		
		 if(task.equals("shop")){
			 String nppid=util.getIdNhapp(userId);
			    obj.setNppid(nppid);
		 }	
		 
		 obj.settask(task);
		 
	    obj.setUserId(userId);
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpChuyenkhoSX lsxBean = new ErpChuyenkhoSX();
	    	lsxBean.setUserId(userId);
	    	 
	    	lsxBean.settask(task);
	    	lsxBean.createRs();
	    	lsxBean.InitNew();
	    	lsxBean.setCheckNhanHang("1");
	    	session.setAttribute("lsxBean", lsxBean);
	    	session.setAttribute("khochuyenIds", "");
			session.setAttribute("trangthaisp", "");
	    	if(task.equals("shop")){
	    		session.setAttribute("khochuyenIds", lsxBean.getKhoXuatId());
	    		session.setAttribute("trangthaisp","");
	    		session.setAttribute("congtyId","100005");
 
	    	}
	    	obj.DBclose();
    		String nextJSP = "/SalesUp/pages/Erp/ErpChuyenKhoSanXuatNew.jsp";
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
		    	
		    	if(!task.equals("2"))
	    		{
	    			response.sendRedirect("/SalesUp/pages/Erp/ErpChuyenKhoSanXuat.jsp");
	    		}
	    		else  
	    		{
	    			response.sendRedirect("/SalesUp/pages/Erp/ErpChuyenKho_XuatHangList.jsp");
	    		}
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.init(search);
				obj.setUserId(userId);
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		if(!task.equals("2"))
	    		{
	    			response.sendRedirect("/SalesUp/pages/Erp/ErpChuyenKhoSanXuat.jsp");
	    		}
	    		else  
	    		{
	    			response.sendRedirect("/SalesUp/pages/Erp/ErpChuyenKho_XuatHangList.jsp");
	    		}
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpChuyenkhoSXList obj)
	{
		
		
		String	query = " select muahang_fk,isnull( ISDANGVANCHUYEN,'0') as ISDANGVANCHUYEN,  isnull(YEUCAUCHUYENKHO_FK,0) as soyeucau  , c.loai  as loaikho , isnull(COXACNHANNHAPKHO,'0') as COXACNHANNHAPKHO,a.PK_SEQ, a.trangthai, a.ngaychuyen, " +
						" a.noidungxuat_fk as ndxId, b.ma + ', ' + b.ten as noidungxuat, isnull(a.lydo, '') as lydo,  \n" +
						" NV.TEN as nguoitao, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua   , \n" +
						"(select cast(isnull((SUM(soluongyeucau)-SUM(soluongxuat)),0) as numeric(18,3)) as lech from   ERP_CHUYENKHO  y  \n" +
						" left join ERP_CHUYENKHO_SANPHAM z on \n"+ 
						"z.CHUYENKHO_FK=y.PK_SEQ where y.PK_SEQ = a.PK_SEQ group by z.CHUYENKHO_FK )  as chenhlech,  \n" +
						"	(  select COUNT(*) from ERP_YEUCAUCHUYENKHO yc inner join ERP_CHUYENKHO ck on yc.PK_SEQ=ck.YEUCAUCHUYENKHO_FK  " +
						"		where yc.pk_seq=a.pk_seq and ( ck.TRANGTHAI  = 3 or( ck.trangthai=2 and ISDANGVANCHUYEN =1 ))) as exist," +
						" (select COUNT(*) \n"+
						"  from  ERP_YEUCAUCHUYENKHO_SANPHAM YC inner join \n"+
						"		 (select SANPHAM_FK, SUM(SOLUONGXUAT) as SLXUAT \n"+
						"		  from ERP_CHUYENKHO ck inner join ERP_CHUYENKHO_SANPHAM sp on ck.PK_SEQ = sp.CHUYENKHO_FK \n"+
						"		  where ck.YEUCAUCHUYENKHO_FK = a.PK_SEQ and ck.TRANGTHAI != 4 \n"+
						"		  group by SANPHAM_FK \n"+
						"		  ) SP ON YC.SANPHAM_FK = SP.SANPHAM_FK   \n"+
						"   where YC.CHUYENKHO_FK = a.PK_SEQ and YC.SOLUONGYEUCAU - SP.SLXUAT != 0 \n"+
						"  ) ISXUATHET \n"+
						" from ERP_CHUYENKHO a " +
						" inner join ERP_NOIDUNGNHAP b on a.noidungxuat_fk = b.pk_seq  " +
						" left join ERP_KHOTT c on a.KHONHAN_FK = c.PK_SEQ " +
						" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ  " +
						" where 1=1 ";
						if(obj.gettask().equals("shop")){
							query=query+ " and c.loai='8' and c.npp_fk="+obj.getNppid();
						 
						}else if(obj.gettask().equals("lsx")) {
							query=query+ " and lenhsanxuat_fk is not null  ";
						}else if(obj.gettask().equals("mh")) {
							query=query+ " and muahang_fk is not null  ";
						}
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
						
						String sochungtu = request.getParameter("sochungtu");
						if(sochungtu == null)
							sochungtu = "";
						obj.setsochungtu(sochungtu.trim());
						
						
						String ndxId = request.getParameter("ndxId");
						if(ndxId == null)
							ndxId = "";
						obj.setNdxId(ndxId);
						
						String sopo = request.getParameter("sopo");
						if(sopo == null)
							sopo = "";
						obj.setSopo(sopo.trim());
						

						String soyeucau = request.getParameter("soyeucau");
						if(soyeucau == null)
							soyeucau = "";
						obj.setSoyeucau(soyeucau.trim());
						
				
						if(tungay.length() > 0)
							query += " and a.ngaychuyen >= '" + tungay + "'";
						
						if(denngay.length() > 0)
							query += " and a.ngaychuyen <= '" + denngay + "'";
					
						if(ndxId.length() > 0)
							query += " and a.noidungxuat_fk = '" + ndxId + "'";
				
						if(trangthai.length() > 0 )
							if (trangthai.equals("5"))
								query+=" and " +
								"(select cast(isnull((SUM(soluongyeucau)-SUM(soluongxuat)),0) as numeric(18,3)) as lech from   ERP_CHUYENKHO  y  " +
								" left join ERP_CHUYENKHO_SANPHAM z on " +
								" z.CHUYENKHO_FK=y.PK_SEQ where y.PK_SEQ = a.PK_SEQ group by z.CHUYENKHO_FK )>0 and a.TrangThai!=4 ";
										
							else
								query += " and a.TrangThai = '" + trangthai + "'";
						if(sochungtu.length() >0) {
							query += " and  cast(a.pk_seq as varchar(10)) like  '%"+sochungtu+"%'";
						}
						
						if(sopo.length()>0)
						{
							query+=" and a.muahang_fk like '%"+ sopo + "%'";
						}
						
						if(soyeucau.length()>0)
						{
							query+=" and isnull(YEUCAUCHUYENKHO_FK,0) like '%"+ soyeucau + "%'";
						}
						
											
						
		
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	
}
