package geso.dms.distributor.servlets.hopdong;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.hopdong.IErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.IErpDonhangNppETCList;
import geso.dms.distributor.beans.hopdong.imp.ErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.imp.ErpDonhangNppETCList;

import java.awt.geom.QuadCurve2D;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class ErpDonhangNppETCSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpDonhangNppETCSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpDonhangNppETCList obj;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
    
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
	   
	    String type = util.antiSQLInspection(request.getParameter("type") == null ? "" : request.getParameter("type"));
		String typesl = util.antiSQLInspection(request.getParameter("typesl") == null ? "" : request.getParameter("typesl"));

	    if(type.equals("GetDonGia"))
    	{
    		NumberFormat formatter = new DecimalFormat("#,###,###.##");
    		Gson gson = new Gson();
    		
  			String spMa = "";
  			String dvdlId ="";
  			String ngaydh="";
  			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
  			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
  			String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
  		
  			
  			String mahd = request.getParameter("mahopdong");
  			if(mahd == null)
  				mahd = "";
  		
    		String query = (String)request.getQueryString();
    		System.out.println("query la "+query);
    		
    		spMa = new String(query.substring(query.indexOf("&spMa=") + 6, query.indexOf("&dvdlId=")).getBytes("UTF-8"), "UTF-8");
    		spMa = URLDecoder.decode(spMa, "UTF-8").replace("+", " ");
  			
    		ngaydh = new String(query.substring(query.indexOf("&ngaydh=") + 8, query.indexOf("&dvkdId=")).getBytes("UTF-8"), "UTF-8");
    		System.out.println("ngay dh la "+ngaydh);
  			
    		ngaydh = URLDecoder.decode(ngaydh, "UTF-8").replace("+", " ");
    		
    		
    		dvdlId = new String(query.substring(query.indexOf("&dvdlId=") + 8, query.indexOf("&nppId=")).getBytes("UTF-8"), "UTF-8");
    		dvdlId = URLDecoder.decode(dvdlId, "UTF-8").replace("+", " ");

    		
  			
  			dbutils db = new dbutils();
  			
  			if(mahd.trim().length() <= 0)
  			{
	  			 query = " select a.DVDL_FK as dvCHUAN, ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) as dvNEW, " + 
				    	 "	case when a.DVDL_FK =( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) then 1  "+
				    	 "	else ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK=a.PK_SEQ and DVDL1_FK = a.DVDL_FK and DVDL2_FK =  "+
				    	 "		( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) ) end as TyLe,  "+
				    	 "	(select soluong1/soluong2 from QUYCACH where SANPHAM_FK=a.PK_SEQ and DVDL1_FK=a.DVDL_FK and DVDL2_FK=  "+
				    	 "	( select PK_SEQ from DONVIDOLUONG where DONVI =  N'" + dvdlId + "' ) ) as QuyCach_THG ,a.TRONGLUONG,a.THETICH, " +  
						 " 	 [dbo].[GiaBanLeNppSanPham](100025,"+nppId+",a.pk_seq,'"+ngaydh+"' ) as giamua " + 
						 " from SANPHAM a where a.MA = '" + spMa + "'  ";
  			}
  			else
  			{
  				String ddhId = request.getParameter("ddhId");
  				
  				query =  " select a.DVDL_FK as dvCHUAN, ( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) as dvNEW, " + 
				    	 "	case when a.DVDL_FK =( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) then 1  "+
				    	 "	else ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK=a.PK_SEQ and DVDL1_FK = a.DVDL_FK and DVDL2_FK =  "+
				    	 "		( select PK_SEQ from DONVIDOLUONG where DONVI = N'" + dvdlId + "' ) ) end as TyLe,  "+
				    	 "	(select soluong1/soluong2 from QUYCACH where SANPHAM_FK=a.PK_SEQ and DVDL1_FK=a.DVDL_FK and DVDL2_FK=  "+
				    	 "	( select PK_SEQ from DONVIDOLUONG where DONVI =  N'" + dvdlId + "' ) ) as QuyCach_THG ,a.TRONGLUONG,a.THETICH, " +  
						 " 	  isnull( ( select case when a.dvdl_fk = b.dvdl_fk then dongia " +
						 "						else dongia * ( select SOLUONG1 /SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK  ) end as dongia " +
						 "				from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
						 "				where sanpham_fk = ( select pk_seq from SANPHAM where MA = '" + spMa + "' ) and dondathang_fk = '" + ddhId + "' ), 0 ) as giamua " + 
						 " from SANPHAM a where a.MA = '" + spMa + "'  ";
  			}
  			System.out.println("[Sql] - LAY GIA: " + query);

  			ResultSet rs = db.get(query);
  			double TheTich = 0;		
  			double TrongLuong = 0;
			double DonGia = 0;
			double soluongton = 0;



  			double QuyCach=0;
  			double TyLe = 0;
  			
  			
  			if(rs != null)
  			{
  				try 
  				{
  					if(rs.next())
  					{
  						TheTich=rs.getDouble("thetich");
  						TrongLuong= rs.getDouble("trongluong");
  						DonGia =rs.getDouble("giamua");
						QuyCach = rs.getDouble("QuyCach_THG");
  						TyLe = rs.getDouble("TyLe");
  						

  						SanPham sp = new SanPham();
  						sp.setDongia( formatter.format( Math.round(  ( DonGia * TyLe - 0.005 ) * 100.0 ) / 100.0 ) );

  						sp.setTrongluong(formatter.format(TrongLuong*TyLe));
  						sp.setThetich(formatter.format(TheTich*TyLe));
  						sp.setQuycach(formatter.format(QuyCach) );

  						response.setContentType("application/json");
  						response.setCharacterEncoding("UTF-8");

  						response.getWriter().write(gson.toJson(sp));
  						
  					}
  					rs.close();
  				} 
  				catch (Exception e)
  				{
  					e.printStackTrace();
  				}
  				finally
  				{
  					if( db != null)
  						db.shutDown();
  				}
  			}
    	}
	    else
	    {
	    	String lsxId = util.getId(querystring);
		    obj = new ErpDonhangNppETCList();
		    
		    String loaidonhang = request.getParameter("loaidonhang");
		    if(loaidonhang == null)
		    	loaidonhang = "0";
		    obj.setLoaidonhang(loaidonhang);
		    
		    String nppId = request.getParameter("nppId");
		    if(nppId==null)
		    	nppId = "";
		    obj.setNppId(nppId);
		    
		    
		    
		    System.out.println(userId+"---NPP ID: " + obj.getNppId());
		    System.out.println("---LOAI DON HANG: " + loaidonhang);
		    
		    if (action.equals("delete") )
		    {	
		    	String msg = this.DeleteChuyenKho(lsxId,userId);
				obj.setMsg(msg);
		    }
		    else if(action.equals("chot"))
	    	{
	    		String msg = this.Chot(lsxId, request);
				obj.setMsg(msg);
	    	}
		    else if(action.equals("unchot"))
	    	{
	    		String msg = this.UnChot(lsxId);
				obj.setMsg(msg);
	    	}
		    
		    obj.setUserId(userId);
		    obj.init("");
		    
			session.setAttribute("obj", obj);
				
			String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETC.jsp";
			response.sendRedirect(nextJSP);
	    }
	    
	}

	private String UnChot(String lsxId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			String query = "update ERP_DONDATHANGNPP set trangthai = '0' where pk_seq = '" + lsxId + "' and trangthai = '1' ";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			Utility util = new Utility();
			msg= util.Check_Huy_NghiepVu_KhoaSo("ERP_DondathangNPP", lsxId, "ngaydonhang", db);
			if(msg.length()>0)
			{
				db.getConnection().rollback();
				return "không thể làm nghiêp vụ trong tháng đã ks";
			}
			
			String checkkho = "", quanlykho = "";
			//query = "select isnull(checkkho, 0) as checkkho from ERP_DONDATHANGNPP where pk_seq = '"+ lsxId +"' ";
			query = "select isnull(quanlykho, 0) as quanlykho, dungchungkenh from NHAPHANPHOI where PK_SEQ = ( select npp_fk from erp_dondathangnpp where pk_seq = '" + lsxId + "' ) ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				//checkkho = rs.getString("checkkho");
				quanlykho = rs.getString("quanlykho");
			}
			rs.close();
			
			if(quanlykho.trim().equals("1"))
			{
				String _kbh_fk="";
				query= " select b.ngaydonhang,a.sanpham_fk,a.solo,a.soluong,b.Kho_FK,(select case when DUNGCHUNGKENH=1 Then 100025 else b.KBH_FK end  from nhaphanphoi where PK_SEQ=kho.NPP_FK) as kbh_fk,a.ngayhethan,a.ngaynhapkho,b.NPP_FK, \n"+
					   "	round(case when c.dvdl_fk != a.DVDL_FK  \n"+
					   "	then ( select soluong1 / soluong2 from QUYCACH where SANPHAM_FK = c.PK_SEQ  \n"+
					   "	and DVDL1_FK = c.DVDL_FK and DVDL2_FK = a.DVDL_FK ) * a.soluong else a.soluong end,1) as luongdat \n"+
					   "	 from ERP_DONDATHANGNPP_SANPHAM_CHITIET a inner join ERP_DONDATHANGNPP b \n"+
					   "	on a.dondathang_fk=b.PK_SEQ \n"+
					   "	inner join SANPHAM c on c.PK_SEQ=a.SANPHAM_FK \n"+
					   "	inner join NHAPP_KHO_CHITIET kho on kho.NPP_FK=b.NPP_FK \n"+
					   "	and kho.KBH_FK=(select case when DUNGCHUNGKENH=1 Then 100025 else b.KBH_FK end  from nhaphanphoi where PK_SEQ=kho.NPP_FK) and kho.KHO_FK=b.Kho_FK and kho.SANPHAM_FK=a.SANPHAM_FK \n"+
					   "	and kho.SOLO=a.solo and kho.NGAYHETHAN=a.ngayhethan and kho.NGAYNHAPKHO=a.ngaynhapkho \n"+
					   "	where b.PK_SEQ="+lsxId+"";
					System.out.println("nha kho ne "+query);
					ResultSet rsnhakho=db.get(query);
					while (rsnhakho.next())
					{
						String _kho_fk=rsnhakho.getString("kho_fk");
						String _sanpham_fk=rsnhakho.getString("sanpham_fk");
						String _npp_fk=rsnhakho.getString("npp_fk");
						_kbh_fk= rsnhakho.getString("kbh_fk");
						String _solo= rsnhakho.getString("solo");
						String _ngayhethan=rsnhakho.getString("ngayhethan");
						String _ngaynhankho=rsnhakho.getString("ngaynhapkho");
						double _soluong=rsnhakho.getDouble("luongdat");
						String ngaydonhang=rsnhakho.getString("ngaydonhang");
						String kq1=util.Update_NPP_Kho_Sp_Chitiet(ngaydonhang, "ErpDonhangNppETC.java 269 unchot ID: "+lsxId, db, _kho_fk, _sanpham_fk,_npp_fk ,_kbh_fk, _solo,_ngayhethan ,_ngaynhankho , 0, (-1)*_soluong, _soluong, 0, 0);
						if(kq1.length()>0)
						{
						
							db.getConnection().rollback();
							return "Lỗi khi nha kho 2582: " + query;
						}
						
						 kq1=util.Update_NPP_Kho_Sp(ngaydonhang, "unchot 278 "+lsxId, db, _kho_fk, _sanpham_fk, _npp_fk, _kbh_fk, 0,(-1)* _soluong, _soluong, 0);
						System.out.println("__________"+query);
						
						if(!kq1.equals(""))
						{
							msg = "Khong the cap nhat unchot  NHAPP_KHO: " + kq1;
							db.getConnection().rollback();
							return msg;
						}
						
					}
					rsnhakho.close();
					
				query = "delete ERP_DONDATHANGNPP_SANPHAM_CHITIET where dondathang_fk = '" + lsxId + "' ";
				  System.out.println("query del : "+ query);
				  if(db.updateReturnInt(query)<=0) {
					  db.getConnection().rollback(); 
					  return "Lỗi khi unchot: " + query; 
				  }
			}
				
			db.getConnection().commit();
		}
		catch (Exception e) 
		{
			try { db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return "Exception: " + e.getMessage();
		}
		finally { try { db.getConnection().setAutoCommit(true); if(db != null) { db.shutDown(); } } catch (SQLException e) { e.printStackTrace(); } }
		
		return "";
	}
	
	private String Chot(String lsxId, HttpServletRequest request) 
	{
		dbutils db  =new dbutils();
		Utility utilkho = new Utility();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
			geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
			String npp_fk="";
			String kbh_fk="";
			String sqlck=
					" select a.NPP_FK,b.sanpham_fk,a.KBH_FK,a.KHO_FK from ERP_DONDATHANGNPP a inner join ERP_DONDATHANGNPP_SANPHAM b "+
					" on a.PK_SEQ=b.dondathang_fk where a.PK_SEQ="+lsxId+" ";
			//System.out.println("query la "+sqlck);
			ResultSet rsck=db.get(sqlck);
			while(rsck.next())
			{
				npp_fk=rsck.getString("NPP_FK");
				kbh_fk=rsck.getString("KBH_FK");
			}
		
			String quanlykho = "";
			String query = "select isnull(quanlykho, 0) as quanlykho, dungchungkenh from NHAPHANPHOI where PK_SEQ = '" + npp_fk + "' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")) { kbh_fk = "100025"; }
				quanlykho = rs.getString("quanlykho");
				
			}
			rs.close();
			System.out.println("QUAN LY KHO : "+ quanlykho);
			
			if(quanlykho.equals("1"))
			{
				query = "\n select ngaydonhang,dvCHUAN,sp.dvdl_fk,khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
						"\n ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
						"\n from " +
						"\n ( " +
						"\n		select c.ngaydonhang,c.kho_fk as khoxuat_fk, c.npp_fk, '" + kbh_fk + "' kbh_fk, a.sanpham_fk, a.DVDL_FK as dvCHUAN, " +
						"\n 	case when a.dvdl_fk IS null then a.soluong " +
						"\n 	when a.dvdl_fk = b.DVDL_FK then a.soluong " +
						"\n 	else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk ) " +
						"\n 	/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk ) end as soluong " +
						"\n 	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
						"\n 	inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
						"\n 	where a.dondathang_fk in ( " + lsxId + " )     " +
						"\n ) dathang "+
						"\n inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
						"\n group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN ,dvCHUAN,sp.dvdl_fk,ngaydonhang  ";
			
				System.out.println("--CHECK TON KHO: " + query);
			
				rs = db.get(query);
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("PK_SEQ");
					String dvCHUAN=rs.getString("dvCHUAN");
					String spten=rs.getString("ten");
					String dvdl_fk=rs.getString("dvdl_fk");
					double soluong = rs.getDouble("soluongXUAT");
					double tonkho = rs.getDouble("tonkho");
					String ngaydonhang=rs.getString("ngaydonhang");
					if(soluong > tonkho)
					{
						msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) không đủ tồn kho ( " + rs.getString("tonkho") + " ). Vui lòng kiểm tra lại.";
						db.getConnection().rollback();
						rs.close();
						return msg;
					}
				
					String kq1=utilkho.Update_NPP_Kho_Sp(ngaydonhang, "Bán ETC", db, khoID, spID, nppID, kbhID, 0, soluong, (-1)*soluong, 0);
					System.out.println("__________"+query);
					
					if(!kq1.equals(""))
					{
						msg = "Khong the cap nhat NHAPP_KHO: " + kq1;
						db.getConnection().rollback();
						rs.close();
						return msg;
					}
					// de xuat lo book khi chi tiet 
					 query= " select KHO_FK,SANPHAM_FK,KBH_FK,SOLO,NGAYHETHAN,NGAYNHAPKHO,available  from NHAPP_KHO_CHITIET "+  
							" where NPP_FK ="+nppID+" and KBH_FK= " +kbhID +
							" and KHO_FK="+khoID+"  and SANPHAM_FK =  "+ spID +
							" AND AVAILABLE >0  and NGAYNHAPKHO<='"+ ngaydonhang +"'"+
							" order by NGAYHETHAN ,NGAYNHAPKHO,AVAILABLE ";
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
						if(dvCHUAN.equals(dvdl_fk)){
							// nếu là đơn vị giống nhau
							soluongcapnhat_quydoi= soluongcapnhat;
							
						}else{
							query=" SELECT qc.SOLUONG1,qc.SOLUONG2 FROM QUYCACH qc WHERE SANPHAM_FK="+spID+" AND DVDL1_FK="+dvdl_fk+"  and qc.DVDL2_FK="+dvCHUAN;
							ResultSet rs1=db.get(query);
							if(rs1.next()){
								soluongcapnhat_quydoi = soluongcapnhat * rs1.getDouble("SOLUONG2")/ rs1.getDouble("SOLUONG1");
								
							}else{
								msg="Không thể xác định quy đổi của sản phẩm : "+rs.getString("ten");;
								db.getConnection().rollback();
								return msg;
							}
						}
							 
						//soluongcapnhat =Double.parseDouble(formater_1sole.format(soluongcapnhat));
								 
						query = " Insert into ERP_DONDATHANGNPP_SANPHAM_CHITIET(donDAThang_fk ,   sanpham_fk,solo,ngaynhapkho,ngayhethan, soluong,dvdl_fk) "
						+ " values('" + lsxId + "', '" +spID + "','" +solo+ "','"+ngaynhapkho+"','"+ngayhethan+"',round("+soluongcapnhat_quydoi+",1) ,"+dvCHUAN+" )";
				  
						if (!db.update(query)) 
						{
							String msg1="Không thể cập nhật : "+query;
							db.getConnection().rollback();
							rs.close();
							return msg1;
						}
							
						String  msg1=utilkho.Update_NPP_Kho_Sp_Chitiet(ngaydonhang, "8989 ErpDonhangNppETC.java ",  db, _khoid, spID, npp_fk, _kbhid, solo, ngayhethan, ngaynhapkho, 0,soluongcapnhat,(-1)* soluongcapnhat, (-1)* soluongcapnhat, 0);
						if (msg1.length()> 0) 
						{
							
							db.getConnection().rollback();
							rs.close();
							return msg1;
						}
					}
					rssp.close();
							
					if(soluongdenghi!=0)
					{
						msg= " Số lượng đề xuất trong lô chi tiết của sản phẩm "+spten+"   tới ngày (ngày cấu hình hóa đơn)"+ngaydonhang+" không còn đủ, " +
							 " vui lòng kiểm tra báo cáo ( xuất nhập tồn,tồn hiện tại) theo lô để biết thêm chi tiết ";
						db.getConnection().rollback();
						rs.close();
						return msg;
					}
				}
				rsck.close();
			}
			
			// DONG BO QUA ERP ---------------
			msg = dongbodonETC_ERP(lsxId, request, db);
			if ( msg.length() > 0)
			{
				db.getConnection().rollback();
				return msg;
			}
			
			/*if(quanlykho.trim().equals("1"))
			{
				query = "update ERP_DONDATHANGNPP set trangthai = '1' where pk_seq = '" + lsxId + "' and khachhang_fk is not null ";
			}
			else
			{
				query = "update ERP_DONDATHANGNPP set trangthai = '1', checkkho = '0' where pk_seq = '" + lsxId + "' and khachhang_fk is not null ";
			}*/
			
			query = "update ERP_DONDATHANGNPP set trangthai = '1' where pk_seq = '" + lsxId + "' and khachhang_fk is not null ";
			if(db.updateReturnInt(query) <= 0)
			{
				msg = "Vui lòng chọn khách hàng ETC cho đơn hàng";
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
		}
		catch (Exception e) 
		{
			try {
				db.getConnection().rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "Exception: " + e.getMessage();
		}
		finally { try { db.getConnection().setAutoCommit(true); if(db != null) { db.shutDown(); } } catch (SQLException e) { e.printStackTrace(); } }
		
		return "";
	}
	
	private String dongbodonETC_ERP(String id, HttpServletRequest request, dbutils db) throws SQLException
	{
		try 
		{
			
			   if(!Utility.IsDongBo){
				   return "";
			   }
				//dbutils db = new dbutils();
				String dadongbo = "";
				String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
				String queryCT = 
						"\n INSERT INTO "+ LINKSERVER_DB +".[dbo].ERP_DONDATHANG_SANPHAM ( DONDATHANG_FK, SANPHAM_FK, SOLUONG, SOLUONGCHUAN, KHOTT_FK,  DONGIA, SCHEME, THUEVAT, THOIDIEM, DVDL_FK ) "+ 
						"\n SELECT ( SELECT DISTINCT PK_SEQ FROM "+ LINKSERVER_DB +".[dbo].ERP_DONDATHANG WHERE PK_SEQ_DMS = '"+ id +"' ) AS DONDATHANG_FK, "+ 
						"\n ( SELECT PK_SEQ FROM "+ LINKSERVER_DB +".[DBO].ERP_SANPHAM WHERE MA = ( SELECT MA FROM SANPHAM WHERE PK_SEQ = DHSP.SANPHAM_FK ) ) AS SANPHAM_FK, "+ 
						"\n SOLUONG, SOLUONG AS SOLUONGCHUAN,  "+
						"\n ISNULL(( SELECT TOP(1) PK_SEQ FROM "+ LINKSERVER_DB +".[DBO].ERP_KHOTT WHERE MA = ( SELECT DISTINCT XUATTAIKHO FROM "+ LINKSERVER_DB +".[DBO].NHAPHANPHOI WHERE MA = ( SELECT NPP.MA FROM NHAPHANPHOI NPP WHERE EXISTS ( SELECT 1 FROM ERP_DONDATHANGNPP DH WHERE DH.PK_SEQ = DHSP.DONDATHANG_FK AND DH.NPP_FK = NPP.PK_SEQ ) ) ) ), '100013' ) AS KHO_FK,  "+
						"\n ISNULL(DHSP.DONGIA, 0) AS DONGIA, '' SCHEME, THUEVAT, GETDATE() AS THOIDIEM,  "+
						"\n ( SELECT DVDL_FK FROM "+ LINKSERVER_DB +".[DBO].ERP_SANPHAM WHERE MA = ( SELECT MA FROM SANPHAM WHERE PK_SEQ = DHSP.SANPHAM_FK ) ) AS DVDL_FK  "+
						"\n FROM ERP_DONDATHANGNPP_SANPHAM DHSP  "+
						"\n WHERE DONDATHANG_FK = '"+ id +"'  "+
						"\n  UNION ALL  "+
						"\n SELECT ( SELECT DISTINCT PK_SEQ FROM "+ LINKSERVER_DB +".[dbo].ERP_DONDATHANG WHERE PK_SEQ_DMS = '"+ id +"' ) AS DONDATHANG_FK,  "+
						"\n ( SELECT PK_SEQ FROM "+ LINKSERVER_DB +".[DBO].ERP_SANPHAM WHERE MA = ( SELECT MA FROM SANPHAM WHERE PK_SEQ = DHKM.SANPHAM_FK ) ) AS SANPHAM_FK,  "+
						"\n SOLUONG, SOLUONG AS SOLUONGCHUAN,  "+
						"\n ISNULL(( SELECT TOP(1) PK_SEQ FROM "+ LINKSERVER_DB +".[DBO].ERP_KHOTT WHERE MA = ( SELECT DISTINCT XUATTAIKHO FROM "+ LINKSERVER_DB +".[DBO].NHAPHANPHOI WHERE MA = ( SELECT NPP.MA FROM NHAPHANPHOI NPP WHERE EXISTS ( SELECT 1 FROM ERP_DONDATHANGNPP DH WHERE DH.PK_SEQ = DHKM.DONDATHANGID AND DH.NPP_FK = NPP.PK_SEQ ) ) ) ), '100013' ) AS KHO_FK,  "+
						"\n 0 AS DONGIA, (SELECT DISTINCT SCHEME FROM CTKHUYENMAI CTKM WHERE CTKM.PK_SEQ = DHKM.CTKMID) AS SCHEME, 0 THUEVAT, GETDATE() AS THOIDIEM,   "+
						"\n ( SELECT DVDL_FK FROM "+ LINKSERVER_DB +".[DBO].ERP_SANPHAM WHERE MA = ( SELECT MA FROM SANPHAM WHERE PK_SEQ = DHKM.SANPHAM_FK ) ) AS DVDL_FK  "+
						"\n FROM ERP_DONDATHANGNPP_CTKM_TRAKM DHKM  "+
						"\n WHERE DONDATHANGID = '"+ id +"' ";
				
				db.update(" SET XACT_ABORT ON ");
			
				String query = "select count(*) as num from "+ LINKSERVER_DB +".DBO.ERP_DONDATHANG where pk_seq_dms = '"+ id +"' and is_khachhang = '2' ";
				if(geso.dms.center.util.Utility.Check_Count_Query(db, query) > 0) // update
				{
					query = " UPDATE DDH SET DDH.KHO_FK = ISNULL(( SELECT TOP(1) PK_SEQ FROM "+ LINKSERVER_DB +".[DBO].ERP_KHOTT WHERE MA = ( SELECT DISTINCT XUATTAIKHO FROM "+ LINKSERVER_DB +".[DBO].NHAPHANPHOI WHERE MA = NPP.MA ) ), '100013' ), "
						  + " DDH.NGAYDONHANG = DH.NGAYDONHANG, DDH.NGAYDENGHI = DH.NGAYDENGHI, DDH.DVKD_FK = (SELECT DISTINCT D.PK_SEQ FROM "+ LINKSERVER_DB +".DBO.DONVIKINHDOANH D WHERE D.DONVIKINHDOANH = 'HDP'), "
						  + " DDH.KBH_FK = (SELECT DISTINCT K.PK_SEQ FROM "+ LINKSERVER_DB +".DBO.KENHBANHANG K WHERE K.TEN = KBH.TEN), DDH.GHICHU = ISNULL(DH.GHICHU, ''), "
							/*
							 * + " DDH.TONGTIENBVAT = DH.VAT, DDH.TONGTIENAVAT = DH.TONGGIATRI, "
							 * +" DDH.PTVAT = ( CASE WHEN DH.VAT != 0 THEN (( DH.TONGGIATRI / DH.VAT ) - 1) * 100 ELSE 0 END ), "
							 */
						  + " DDH.VAT = DH.VAT, "
						  
						  + " DDH.NGAYSUA = CONVERT(VARCHAR(10), GETDATE(), 120), DDH.KHACHHANG_FK = ( SELECT DISTINCT KH_ERP.PK_SEQ FROM "+ LINKSERVER_DB +".DBO.ERP_KHACHHANG KH_ERP WHERE KH_ERP.MAFAST = KH.MAFAST ),"
						  + " DDH.NPP_FK = ( SELECT DISTINCT NPP_E.PK_SEQ FROM "+ LINKSERVER_DB +".DBO.NHAPHANPHOI NPP_E WHERE NPP_E.MA = NPP.MA ) "
						  + " FROM  "+ LINKSERVER_DB +".DBO.ERP_DONDATHANG DDH "
						  + " INNER JOIN ERP_DONDATHANGNPP DH ON DH.PK_SEQ = DDH.PK_SEQ_DMS "
						  + " INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = DH.KBH_FK "
						  + " INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = DH.NPP_FK "
						  + " INNER JOIN KHACHHANG KH ON KH.PK_SEQ = DH.KHACHHANG_FK "
						  + " WHERE DDH.PK_SEQ_DMS = '"+ id +"'";
					System.out.println("query 1 : "+ query);
					if (db.updateReturnInt(query)!=1)
					{
						dadongbo = "Không thể đồng bộ sang erp.";
						return dadongbo;
					}
					
					query = " delete from "+ LINKSERVER_DB +".DBO.ERP_DONDATHANG_SANPHAM "
						  + " WHERE DONDATHANG_FK = ( SELECT PK_SEQ FROM "+ LINKSERVER_DB +".DBO.ERP_DONDATHANG WHERE PK_SEQ_DMS = '"+ id +"' ) ";
					System.out.println("query 2 : "+ query);
					if (db.updateReturnInt(query) < 1)
					{
						dadongbo = "Không thể đồng bộ sang erp.";
						return dadongbo;
					}
					
					// INSERT CHI TIET
					if (db.updateReturnInt(queryCT) <= 0)
					{
						dadongbo = "Không thể đồng bộ sang erp.";
						return dadongbo;
					}	
				}
				else
				{
					query =  
						 "\n INSERT INTO  "+ LINKSERVER_DB +".DBO.ERP_DONDATHANG ( NGAYDONHANG, NGAYDENGHI, DVKD_FK, KBH_FK, KHO_FK, GHICHU, TONGTIENBVAT, TONGTIENAVAT, PTVAT, NGAYTAO, NGAYSUA, ISETC, KHACHHANG_FK, CONGTY_FK, NPP_FK, IS_KHACHHANG, PK_SEQ_DMS, TRANGTHAI, NGUOITAO, NGUOISUA ) "+
						 "\n SELECT DH.NGAYDONHANG, DH.NGAYDENGHI, (SELECT DISTINCT D.PK_SEQ FROM  "+ LINKSERVER_DB +".DBO.DONVIKINHDOANH D WHERE D.DONVIKINHDOANH = 'HDP') AS DVKD_FK,  "+
						 "\n (SELECT DISTINCT K.PK_SEQ FROM  "+ LINKSERVER_DB +".DBO.KENHBANHANG K WHERE K.TEN = KBH.TEN) AS KBH_FK,  "+
						 "\n ISNULL(( SELECT TOP(1) PK_SEQ FROM  "+ LINKSERVER_DB +".[DBO].ERP_KHOTT WHERE MA = ( SELECT DISTINCT XUATTAIKHO FROM  "+ LINKSERVER_DB +".[DBO].NHAPHANPHOI WHERE MA = NPP.MA ) ), '100013' ) AS KHO_FK,  "+
						 "\n ISNULL(DH.GHICHU, '') AS GHICHU, DH.SOTIENTHU AS TONGTIENBVAT, DH.SOTIENTHU AS TONGTIENAVAT,  "+
						 "\n ISNULL(DH.VAT,0) AS VAT,   "+
						 "\n  dh.NGAYTAO, DH.NGAYSUA, 1 AS ISETC,  "+
						 "\n ( SELECT DISTINCT KH_ERP.PK_SEQ FROM  "+ LINKSERVER_DB +".DBO.ERP_KHACHHANG KH_ERP WHERE KH_ERP.MAFAST = KH.MAFAST ) KHACHHANG_FK, '100000' AS CONGTY_FK,   "+
						 "\n ( SELECT DISTINCT NPP_E.PK_SEQ FROM  "+ LINKSERVER_DB +".DBO.NHAPHANPHOI NPP_E WHERE NPP_E.MA = NPP.MA ) AS NPP_FK, '2' AS IS_KHACHHANG, DH.PK_SEQ AS PK_SEQ_DMS, '0' AS TRANGTHAI,  "+
						 "\n '101220' NGUOITAO, '101220' NGUOISUA  "+
						 "\n FROM ERP_DONDATHANGNPP DH   "+
						 "\n INNER JOIN KENHBANHANG KBH ON KBH.PK_SEQ = DH.KBH_FK  "+
						 "\n INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = DH.NPP_FK  "+
						 "\n INNER JOIN KHACHHANG KH ON KH.PK_SEQ = DH.KHACHHANG_FK  "+
						 "\n WHERE DH.PK_SEQ = '"+ id +"' AND DH.TRANGTHAI != '2' ";
						System.out.println("query else 1 : "+ query);
						if (db.updateReturnInt(query)!=1)
						{
							dadongbo = "Không thể đồng bộ sang erp.";
							return dadongbo;
						}	
					  
						// INSERT CHI TIET
						if (db.updateReturnInt(queryCT) <= 0)
						{
							dadongbo = "Không thể đồng bộ sang erp."; 
							return dadongbo;
						}	
				}
			 
			//geso.dms.center.util.Utility.commit_and_shutdown(db);
			return dadongbo;
		}
		catch(Exception e) 
		{
			//geso.dms.center.util.Utility.rollback_and_shutdown(db);
			e.printStackTrace();
			return "Không thể đồng bộ sang erp. Exception: " + e.getMessage();
		}
	}

	private String DeleteChuyenKho(String lsxId,String userid)
	{
		dbutils db = new dbutils();
		String msg = "";
		Utility utilkho= new Utility();
		int checkkho=0;
		String _ngayhoadon="";
		String sql="select isnull(checkkho,0) checkkho ,ngaydonhang,khachhang_fk from ERP_DONDATHANGNPP where pk_Seq='" + lsxId + "' and trangthai=0";
		ResultSet rs=db.get(sql);
		try {
			if(rs.next())
			{
			 _ngayhoadon=utilkho.getngayhoadon(userid, db, rs.getString("ngaydonhang"), rs.getString("khachhang_fk"),1);
			 checkkho=rs.getInt("checkkho");
			}
			else
			{
				return "lỗi xóa dh không hợp lệ .";
			}
			rs.close();	
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			
			e1.printStackTrace();
			return "lỗi xóa dh không hợp lệ ."+e1.getMessage();
		}
		
		try
		{
			db.getConnection().setAutoCommit(false);
		
			
			String query = "select dungchungkenh from NHAPHANPHOI where PK_SEQ =  (SELECT NPP_FK FROM ERP_DONDATHANGNPP WHERE PK_sEQ="+lsxId+" )";
			 rs = db.get(query);
			boolean dungchungkenh=false;
			if(rs.next())
			{
				if(rs.getString("dungchungkenh").equals("1")){
					dungchungkenh=true;
				}
			}
			rs.close();
			
			
			 query=	"	select khoxuat_fk, npp_fk, "+(dungchungkenh?"100025":" kbh_fk")+ " as kbh_fk, sanpham_fk, sum(soluong) as soluong  " +
					"	from " +
					"	( " +
					"		select c.kho_fk as khoxuat_fk, c.npp_fk,   isnull(a.kbh_fk,c.kbh_fk)kbh_fk , a.sanpham_fk,       " +
					"				case when a.dvdl_fk IS null then a.soluong       " +
					"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
					"					 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )  end as soluong    " +
					"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
					"				inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq     " +
					"		where a.dondathang_fk in (  " + lsxId + "  ) and a.soluong > 0 " +
					"	) " +
					"	DATHANG  " +
					"	group by khoxuat_fk, npp_fk, "+(dungchungkenh?"":" kbh_fk,")+ " sanpham_fk " ;
			
				ResultSet rskho=db.get(query);
				while(rskho.next()){
					String  _khoxuat_fk, _npp_fk, _kbh_fk, _sanpham_fk ;
							 _khoxuat_fk=rskho.getString("khoxuat_fk");
							 _npp_fk=rskho.getString("npp_fk");
							  _kbh_fk=rskho.getString("kbh_fk");
							  _sanpham_fk=rskho.getString("sanpham_fk"); 
							  double soluongct_ =rskho.getDouble("soluong");
							 /* String msg1=utilkho.Update_NPP_Kho_Sp(_ngayhoadon, "Cập nhật đơn hàng đối tác :erpdondathangDoitac.java 996",
									  db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, 0, soluongct_*(-1), soluongct_, 0);
							  if(msg1.length() >0){
									db.getConnection().rollback();
									return msg1;
							  }*/
					
				}
				rskho.close();
				
				// xoa bang chi tiet
				if(checkkho==1)
				{
						query=	" SELECT KHOXUAT_FK, NPP_FK,   "+(dungchungkenh?"100025":" kbh_fk")+ " as  KBH_FK, SANPHAM_FK, SUM(SOLUONG) AS SOLUONG   ,SOLO, NGAYHETHAN, NGAYNHAPKHO "+      
								" FROM "+
								" ( "+
								" SELECT C.KHO_FK AS KHOXUAT_FK, C.NPP_FK,isnull(a.kbh_fk,c.kbh_fk)   KBH_FK, A.SANPHAM_FK, A.SOLO,A.NGAYHETHAN,A.NGAYNHAPKHO, "+     
								" CASE WHEN A.DVDL_FK IS NULL THEN A.SOLUONG       "+
								" WHEN A.DVDL_FK = B.DVDL_FK THEN A.SOLUONG      "+
								" ELSE  A.SOLUONG * ( SELECT SOLUONG1 / SOLUONG2 FROM QUYCACH "+ 
								" WHERE SANPHAM_FK = A.SANPHAM_FK AND DVDL2_FK = A.DVDL_FK AND DVDL1_FK = B.DVDL_FK )  END AS SOLUONG "+   
								" FROM ERP_DONDATHANGNPP_SANPHAM_CHITIET A INNER JOIN SANPHAM B ON A.SANPHAM_FK = B.PK_SEQ   "+
								" INNER JOIN ERP_DONDATHANGNPP C ON A.DONDATHANG_FK = C.PK_SEQ     "+
								" WHERE A.DONDATHANG_FK IN (  "+lsxId+" ) AND A.SOLUONG > 0 "+
								" ) "+
								" DATHANG "+ 
								" GROUP BY KHOXUAT_FK, NPP_FK, "+(dungchungkenh?"":" kbh_fk,")+ "  SANPHAM_FK ,SOLO, NGAYHETHAN, NGAYNHAPKHO    ";
				
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
								  
								 /* String msg1=utilkho.Update_NPP_Kho_Sp_Chitiet(_ngayhoadon,"Cập nhật đơn hàng đối tác :erpdondathangDoitac.java 1056" 
										  , db, _khoxuat_fk, _sanpham_fk, _npp_fk, _kbh_fk, _solo, _ngayhethan, _ngaynhapkho, 0, (-1)*soluongct_, soluongct_, soluongct_, 0);
								  if(msg1.length() >0){
									  
										db.getConnection().rollback();
										return msg1;
								  }*/
								  
						
					}
					rskho.close();
				}
			
			 query = "update ERP_DONDATHANGNPP set trangthai = '3' where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			
		
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			db.shutDown();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
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
	    
		IErpDonhangNppETCList obj = new ErpDonhangNppETCList();
		obj.setLoaidonhang(loaidonhang);
	 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    obj.setUserId(userId);
	    
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpDonhangNppETC lsxBean = new ErpDonhangNppETC();
	    	
	    	lsxBean.setUserId(userId);
	    	
			/* lsxBean.setLoaidonhang(loaidonhang); */
	    	
	    	lsxBean.createRs();
	    	session.setAttribute("dvkdId", "100001");
			session.setAttribute("kbhId", "100052");
			session.setAttribute("nppId", request.getParameter("nppId"));
    		
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCNew.jsp";
    		response.sendRedirect(nextJSP);
	    }
	    else
	    {
	    	if(action.equals("view") || action.equals("next") || action.equals("prev"))
	    	{
	    		obj.setUserId(userId);
		    	String search = getSearchQuery(request, obj);
		    	obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
		    	
		    	obj.init(search);
		    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
		    	session.setAttribute("obj", obj);
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETC.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setUserId(userId);
		    	obj.init(search);				
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
	    		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETC.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpDonhangNppETCList obj)
	{
		//Utility util = new Utility();
		Utility util = new Utility();
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
		
		String mafast = request.getParameter("mafast");
		if(mafast == null)
			mafast = "";
		obj.setMafast(mafast);
		
		String mahopdong = request.getParameter("mahopdong");
		if(mahopdong == null)
			mahopdong = "";
		obj.setMahopdong(mahopdong);
		
		String nppId = request.getParameter("nppId");
		if(nppId == null)
			nppId = "";
		obj.setNppId(nppId);
		
		String khId = request.getParameter("khId");
		if(khId == null)
			khId = "";
		obj.setKhTen(khId);
		
		String sodh = request.getParameter("sodh");
		if(sodh == null)
			sodh = "";
		obj.setSodh(sodh);
		
		String query = "select a.PK_SEQ, a.trangthai, isnull(a.hopdong_fk, -1) as hopdong_fk, a.ngaydonhang, a.ngaydenghi, isnull(c.ten, N'Chưa chọn khách hàng') as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, isnull(a.NOTE, '') as NOTE   "
				+
				"from ERP_DonDatHangNPP a inner join KHO b on a.kho_fk = b.pk_seq left join "+
				"(select PK_SEQ, mafast ,TEN as TEN from KHACHHANG where TRANGTHAI = '1' and KBH_FK = '100052' and NPP_FK = '"+nppId+"')"
						+ " as c on a.khachhang_FK = c.pk_seq  " +
				"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
				"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ "+
				" where  a.npp_dat_fk is null and  a.pk_seq > 0 and a.npp_fk = '" + nppId + "'  and a.kho_fk in "+util.quyen_kho(obj.getUserId());
		
		
		if(tungay.length() > 0)
			query += " and a.ngaydonhang >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.ngaydonhang <= '" + denngay + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(khId.length() > 0){
			query += " and a.khachhang_FK = '" + khId + "'";
		}
		if(mafast.length() > 0){
			query += " and c.mafast LIKE '%" + mafast + "%'";
		}
		
		if(sodh.length()>0){
			query += " and a.PK_SEQ LIKE '%" + sodh + "%'";
		}
				
		if(mahopdong.length() > 0)
		{
			query += " and cast(a.hopdong_Fk as varchar(20)) like  '"+mahopdong+"' ";
		}
			
		System.out.println("Câu query hợp đồng: " + query);
		return query;
	}
		
	public String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	class SanPham
	{
		String dongia;
		String quycach ;
		String trongluong;
		String thetich;
		String soluongton;
		public String getSoluongton()
		{
			return soluongton;
		}
		public void setSoluongton(String soluongton)
		{
			this.soluongton = soluongton;
		}
		public String getTrongluong()
	    {
	    	return trongluong;
	    }
		public void setTrongluong(String trongluong)
	    {
	    	this.trongluong = trongluong;
	    }
		public String getThetich()
	    {
	    	return thetich;
	    }
		public void setThetich(String thetich)
	    {
	    	this.thetich = thetich;
	    }
		public String getQuycach()
	    {
	    	return quycach;
	    }
		public void setQuycach(String quycach)
	    {
	    	this.quycach = quycach;
	    }
		public String getDongia() 
		{
			return dongia;
		}
		public void setDongia(String dongia) 
		{
			this.dongia = dongia;
		}
		
		
	}
	
	
}
