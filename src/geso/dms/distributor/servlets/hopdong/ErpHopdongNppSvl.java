
package geso.dms.distributor.servlets.hopdong;

import geso.dms.distributor.beans.hopdong.IErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.IErpHopdongNpp;
import geso.dms.distributor.beans.hopdong.IErpHopdongNppList;
import geso.dms.distributor.beans.hopdong.imp.ErpDonhangNppETC;
import geso.dms.distributor.beans.hopdong.imp.ErpHopdongNpp;
import geso.dms.distributor.beans.hopdong.imp.ErpHopdongNppList;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.IOException;
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

public class ErpHopdongNppSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ErpHopdongNppSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		IErpHopdongNppList obj;
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
	   
    	String lsxId = util.getId(querystring);
	    obj = new ErpHopdongNppList();
	   
	    
	    
	    
	    String view = request.getParameter("view");
	    if(view == null)
	    	view = "";
	    obj.setView(view);
	    System.out.println("---view=: " + view);
	    
	    
	    
	    String loaidonhang = request.getParameter("loaidonhang");
	    if(loaidonhang == null)
	    	loaidonhang = "0";
	    obj.setLoaidonhang(loaidonhang);
	    System.out.println("---LOAI DON HANG: " + loaidonhang);
	    
	    
	    
	    if (action.equals("khongtrungthau") )
	    {	
	    	String msg = this.KhongTrungThau(lsxId,userId);
			obj.setMsg(msg);
	    }
	    else
	    if (action.equals("delete") )
	    {	
	    	String msg = this.DeleteChuyenKho(lsxId,userId);
			obj.setMsg(msg);
	    }
	    else if(action.equals("chot"))
    	{
    		String msg = this.Chot(lsxId,userId);
			obj.setMsg(msg);
    	}
	    else if(action.equals("convert"))
    	{
	    	IErpDonhangNppETC lsxBean = new ErpDonhangNppETC();
	    	lsxBean.setUserId(userId); 
	    	
    		String msg = this.Convert(lsxId, lsxBean, request);
    		if(msg.trim().length() <= 10)
    		{
    		    /*String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCUpdate.jsp";
        		lsxBean.init();*/
        		
        		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonHangNppETCNew.jsp";
        		lsxBean.createRs();
        		
        		session.setAttribute("dvkdId", lsxBean.getDvkdId());
    			session.setAttribute("kbhId", lsxBean.getKbhId());
    			session.setAttribute("nppId", lsxBean.getNppId());
    			session.setAttribute("hopdongids","1");
    			session.setAttribute("lsxBean", lsxBean);
    	        response.sendRedirect(nextJSP);
    	        
    	        return;
    		}
    		else
    		{
    			lsxBean.DBclose();
    			obj.setMsg(msg);
    		}
    	}
	    System.out.println("user id :"+userId);
	    obj.setUserId(userId);
	    
	    obj.init("");
	    
		session.setAttribute("obj", obj);
			
		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNpp.jsp";
		response.sendRedirect(nextJSP);
	    
	}

	private String Convert(String lsxId, IErpDonhangNppETC lsxBean, HttpServletRequest request) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			/*String query = 	"select loaidonhang, tungay, denngay " +
							"from ERP_HOPDONGNPP where pk_seq = '" + lsxId + "' and tungay <= '" + getDateTime() + "' and '" + getDateTime() + "' <= denngay ";*/
			String query =  "select loaidonhang, tungay, case when denngayPL is null then denngay else  denngayPL end as denngay  " +
					"from " +
					"( " +
					"	select loaidonhang, tungay, denngay, " +
					"			( select max(denngay) from ERP_HOPDONGNPP where hopdong_fk = a.pk_seq and trangthai in (1, 2) ) as denngayPL " +
					"	from ERP_HOPDONGNPP a  " +
					"	where pk_seq = '" + lsxId + "'  " +
					") " +
					"HD " +
					"where tungay <= '" + this.getDateTime() + "' and '" + this.getDateTime() + "' <= ( case when denngayPL is null then denngay else  denngayPL end ) ";

			ResultSet rs = db.get(query);
			String loaidonhang = "";
			String tungay = "";
			String denngay = "";
			if(rs.next())
			{
				loaidonhang = rs.getString("loaidonhang");
				tungay = rs.getString("tungay");
				denngay = rs.getString("denngay");
				
				rs.close();
			}
			
			String sql = " select quanlykho from nhaphanphoi where pk_Seq = ( select npp_fk from erp_hopdongnpp where pk_seq = '"+ lsxId +"' ) ";
			String quanlykho = "";
			rs = db.get(sql);
			try {
				rs.next();
				quanlykho = rs.getString("quanlykho");
				rs.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//CHECK CON HIEU LUC KHONG
			if(loaidonhang.trim().length() <= 0)
			{
				msg = "Hợp đồng đã hết hạn. Bạn không thể thành đơn hàng.";
				db.getConnection().rollback();
				db.shutDown();
				return msg;
			}

			query=
					"	select b.MA as spMa,b.TEN as spTEN,c.DONVI as spDONVI,sanpham_fk, a.dvdl_fk as dvDATHANG, b.dvdl_fk as dvCHUAN,  "+  
							"		case when a.dvdl_fk IS null then 1          "+
							"			 when a.dvdl_fk = b.DVDL_FK then 1         "+
							"			 else  isnull( ( select SOLUONG1 /SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK ),-1) end as soluong, "+   
							"		dongia, chietkhau, thueVAT, tungay, denngay     "+
							"	from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq "+    
							"		inner join DONVIDOLUONG c on c.PK_SEQ=a.dvdl_fk   "+
							"	where HOPDONG_FK = '  "+lsxId+"  '   and a.SoLuong>0  "+
							"		union ALL    "+
							"	select b.ma as spMA,b.TEN as spTEN,c.DONVI as spDONVI,  sanpham_fk, a.dvdl_fk as dvDATHANG, b.dvdl_fk as dvCHUAN, "+  
							"		case when a.dvdl_fk IS null then 1          "+
							"			 when a.dvdl_fk = b.DVDL_FK then 1         "+
							"			 else isnull(  ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK ) ,-1) end as soluong, "+   
							"		dongia, chietkhau, thueVAT, tungay, denngay     "+
							"	from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq "+   
							"		inner join DONVIDOLUONG c on c.PK_SEQ=a.dvdl_fk   "+
							"	where  a.SoLuong>0 and  HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '  "+lsxId+"  ' and trangthai in (1, 2) ) ";   
			rs=db.get(query);
			msg="";
			while(rs.next())
			{
				int SoLuong =rs.getInt("SoLuong");
				if(SoLuong==-1)
				{
					msg += "Sản phẩm "+rs.getString("spMa")+"-"+rs.getString("spTEN")+"-"+rs.getString("spDONVI")+ " chưa khai báo quy đổi ! \n";
				}
			}
			if(rs!=null)rs.close();

			if(msg.length()>0)
			{
				db.getConnection().rollback();
				db.shutDown();
				return msg;
			}


			if(loaidonhang.equals("0") ) //Hóa đơn bình thường, chỉ được phép đặt bằng số còn lại
			{
				query = "select count(*) as soDONG  " +
						"from " +
						"( " +
						"	select sanpham_fk, dvdl_fk, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay " +
						"	from " +
						"	( " +
						"		select sanpham_fk,  " +
						"			case when a.dvdl_fk IS null then a.soluong       " +
						"				 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK=b.DVDL_FK )       " +
						"							/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  and DVDL1_FK=b.DVDL_FK )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
						"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq  " +
						"		where HOPDONG_FK = '" + lsxId + "'  " +
						"	union ALL " +
						"		select sanpham_fk,  " +
						"			case when a.dvdl_fk IS null then a.soluong       " +
						"				 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK=b.DVDL_FK )       " +
						"							/ ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK=b.DVDL_FK )	 end as soluong, dongia, chietkhau, thueVAT, b.pk_seq as dvdl_fk, tungay, denngay  " +
						"		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
						"		where HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '" + lsxId + "' and trangthai in (1, 2) ) " +
						"	) " +
						"	hopdong group by sanpham_fk, dvdl_fk, tungay, denngay " +
						") " +
						"hd left join " +
						"( " +
						"	select sanpham_fk, sum(soluong) as daDAT " +
						"	from " +
						"	( " +
						"		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,      " +
						"				case when a.dvdl_fk IS null then a.soluong       " +
						"					 when a.dvdl_fk = b.DVDL_FK then a.soluong      " +
						"					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK=b.DVDL_FK )       " +
						"									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK=b.DVDL_FK ) end as soluong  " +
						"		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ      " +
						"		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + lsxId + "'  )     " +
						"	) " +
						"	dathang group by sanpham_fk " +
						") " +
						"dh on hd.sanpham_fk = dh.sanpham_fk " +
						"where hd.soluong > isnull(dh.daDAT, 0) ";  //KHONG CON SP NAO

				System.out.println("----CHECK SANPHAM: " + query );
				rs = db.get(query);
				int soDONG = 0;
				if(rs.next())
				{
					soDONG = rs.getInt("soDONG");
				}
				rs.close();

				if(soDONG <= 0)
				{
					msg = "Hợp đồng đã chuyển hết thành SO. Bạn không thể chuyển tiếp.";
					db.getConnection().rollback();
					db.shutDown();
					return msg;
				}
			}

			query = "update ERP_HOPDONGNPP set trangthai = '2' where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				db.shutDown();
				return msg;
			}
			String msgLog = ErpHopdongNpp.Log_HopDongNPP( db,lsxId,"Tạo mới",lsxBean.getUserId());
			if(msgLog.trim().length() > 0)
			{
				msg = msgLog;
				db.getConnection().rollback();
				db.shutDown();
				return msg;
			} 

			/*query = " insert ERP_DondathangNPP(ngaydonhang, ngaydenghi, loaidonhang, npp_dachot, ghichu, trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, hopdong_fk, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua) " +
					" select tungay, denngay, 0, 1 as npp_dachot, ghichu, 0 as trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, khachhang_fk, npp_fk, kho_fk, pk_seq, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua " +
					" from ERP_HOPDONGNPP where pk_seq = '" + lsxId + "' ";
			System.out.println("-- INSERT DDH: " + query );
			if(db.updateReturnInt(query) < 1 )
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				return msg;
			}*/

			query = " select  mahopdong, tungay, denngay, 0, 1 as npp_dachot, ghichu, 0 as trangthai, dvkd_fk, kbh_fk, gsbh_fk, ddkd_fk, " +
					"	khachhang_fk, npp_fk, kho_fk, pk_seq, chietkhau, vat, ngaytao, nguoitao, ngaysua, nguoisua, " +
					"	Isnull( ( select dungchungkenh from NHAPHANPHOI where pk_seq = a.npp_fk ), 0 ) as dungchungkenh " +
					" from ERP_HOPDONGNPP a where pk_seq = '" + lsxId + "' ";
			System.out.println("-- INIT DDH: " + query );
			rs = db.get(query);
			{
				if(rs.next())
				{
					lsxBean.setMahopdong(rs.getString("pk_seq"));
					lsxBean.setTungay("");
					lsxBean.setDenngay("");
					lsxBean.setGhichu(rs.getString("ghichu"));
					lsxBean.setDvkdId(rs.getString("dvkd_fk"));
					lsxBean.setKbhId(rs.getString("kbh_fk"));

					if(rs.getString("khachhang_fk") != null)
						lsxBean.setKhId(rs.getString("khachhang_fk"));

					lsxBean.setKhoNhapId(rs.getString("kho_fk"));
					lsxBean.setLoaidonhang("0");
					lsxBean.setChietkhau(rs.getString("chietkhau"));
					lsxBean.setVat(rs.getString("vat"));
					lsxBean.setGsbhId(rs.getString("gsbh_fk"));
					lsxBean.setDdkdId(rs.getString("ddkd_fk"));

					if(rs.getString("mahopdong").trim().length() > 0 )  //Chuyển từ hợp đồng thì ko có chiết khấu
						lsxBean.setChietkhau("0");

					lsxBean.setDungchungKenh(rs.getString("dungchungkenh"));
				}
				rs.close();
			}



			String sqlSOLUONG = "		case when hd.dvCHUAN = hd.dvDATHANG then hd.soluong - isnull(dh.daDAT, 0)  " +
					"			else ( hd.soluong - isnull(dh.daDAT, 0) ) * ( select SOLUONG2 / SOLUONG1 from QUYCACH where sanpham_fk = hd.sanpham_fk and DVDL2_FK = hd.dvDATHANG and DVDL1_FK = hd.dvCHUAN ) end as soluong,  ";

			if(loaidonhang.equals("2") ) //Hợp đồng nguyên tắc hoặc hợp đòng chung
				sqlSOLUONG = " isnull(hd.soluong, 0) as soluong, ";

			/*if(loaidonhang.equals("2") || loaidonhang.equals("3")) //Hợp đồng nguyên tắc hoặc hợp đòng chung
				sqlSOLUONG = " 0 as soluong, ";*/

			if(quanlykho.equals("0"))
			{
				
				String LINKSERVER_DB = request.getServletContext().getInitParameter("LINKSERVER_DB");
				
				query =
					"IF OBJECT_ID('tempdb.dbo.#kho') IS NOT NULL DROP TABLE #kho select * into #kho from "+
				 	" ( "+
					" 	 SELECT SP_E.MA SPMA, NPP_E.MA NPPMA, SUM(AVAILABLE) AVAILABLE "+
					" 	 FROM "+ LINKSERVER_DB +".[dbo].[ERP_KHOTT_SP_CHITIET] K   "+
					" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].NHAPHANPHOI NPP_E ON NPP_E.PK_SEQ = K.NPP_FK   "+
					" 	 INNER JOIN "+ LINKSERVER_DB +".[dbo].ERP_SANPHAM SP_E ON SP_E.PK_SEQ = K.SANPHAM_FK    "+
					" 	 WHERE K.NGAYNHAPKHO <= '"+ tungay +"' AND NPP_E.MA = ( SELECT DISTINCT MA FROM NHAPHANPHOI WHERE PK_SEQ = ( select NPP_FK from ERP_HOPDONGNPP where  PK_SEQ = '"+ lsxId +"' )) "+
					" 	 GROUP BY SP_E.MA, NPP_E.MA "+
					" ) AS K "+
								
					"select hd.stt, isnull(dh.daDAT,0)* dbo.[LayQuyCach_DVBan](hd.sanpham_fk, null, hd.dvdl_fk ) soluongdat,hd.sanpham_fk, " + sqlSOLUONG +
					//"\n (select kho.available from nhapp_kho kho where kho.sanpham_fk=SP.pk_seq and kho.KHO_FK= "+ lsxBean.getKhoNhapId() +" and NPP_FK=(select npp_fk from ERP_HOPDONGNPP where pk_seq = "+ lsxId +") and kho.KBH_FK=( (select case when (select npp.dungchungkenh from NHAPHANPHOI npp where npp.PK_SEQ=NPP_FK)=1 then 100025 else '"+lsxBean.getKbhId()+"' end  from ERP_HOPDONGNPP where PK_SEQ="+lsxId+") )) *dbo.[LayQuyCach_DVBan](hd.sanpham_fk, null, hd.dvdl_fk )as soluongton "+
					"\n K.AVAILABLE * dbo.[LayQuyCach_DVBan](hd.sanpham_fk, null, hd.dvdl_fk )as soluongton "+
					
					"\n ,hd.dvDATHANG as dvdl_fk, hd.dongia, hd.chietkhau, hd.thueVAT, hd.tungay, hd.denngay, ISNULL(sp.trongluong, 0) as trongluong, ISNULL(sp.thetich, 0) as thetich, " +
					"\n sp.MA, sp.TEN, DV.donvi" +
					"\n	 , [dbo].[LayQuyCach](hd.sanpham_fk, null , hd.dvdl_fk ) spQuyDoi  " +
					"\n  from  " +
					"\n  (  " +
					"\n  	select stt, dvdl_fk,sanpham_fk, dvDATHANG, dvCHUAN, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay  " +
					"\n  	from  " +
					"\n  	(  " +
					"\n  		select a.stt, a.dvdl_fk,sanpham_fk, a.dvdl_fk as dvDATHANG, b.dvdl_fk as dvCHUAN, " +
					"\n  			case when a.dvdl_fk IS null then a.soluong        " +
					"\n  				 when a.dvdl_fk = b.DVDL_FK then a.soluong       " +
					"\n  				 else  a.soluong * ( select SOLUONG1 /SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK ) end as soluong,  " +
					"\n  			dongia, chietkhau, thueVAT, tungay, denngay   " +
					"\n  		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
					"\n  		where HOPDONG_FK = '" + lsxId + "'   " +
					"\n  	union ALL  " +
					"\n  		select a.stt, a.dvdl_fk,sanpham_fk, a.dvdl_fk as dvDATHANG, b.dvdl_fk as dvCHUAN, " +
					"\n  			case when a.dvdl_fk IS null then a.soluong        " +
					"\n  				 when a.dvdl_fk = b.DVDL_FK then a.soluong       " +
					"\n  				 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )  end as soluong,  " +
					"\n  			dongia, chietkhau, thueVAT, tungay, denngay   " +
					"\n  		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq    " +
					"\n  		where HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '" + lsxId + "' and trangthai in (1, 2) )  " +
					"\n  	)  " +
					"\n  	hopdong group by sanpham_fk, dvDATHANG, dvCHUAN, tungay, denngay, dvdl_fk, stt  " +
					"\n  )  " +
					"\n  hd "+
					" left join  " +
					"\n  (  " +
					"\n  	select sanpham_fk, sum(soluong) as daDAT  " +
					"\n  	from  " +
					"\n  	(  " +
					"\n  		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,       " +
					"\n  				case when a.dvdl_fk IS null then a.soluong        " +
					"\n  					 when a.dvdl_fk = b.DVDL_FK then a.soluong       " +
					"\n  					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  and QUYCACH.DVDL1_FK=b.DVDL_FK )        " +
					"\n  									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk   and QUYCACH.DVDL1_FK=b.DVDL_FK) end as soluong   " +
					"\n  		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ       " +
					"\n  		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + lsxId + "'  )      " +
					"\n  	)  " +
					"\n  	dathang group by sanpham_fk  " +
					"\n  )  " +
					"\n  dh on hd.sanpham_fk = dh.sanpham_fk " +
					"\n   inner Join SanPham SP on hd.SANPHAM_FK = SP.PK_SEQ " +
					" OUTER APPLY ( SELECT * FROM #KHO WHERE SPMA = sp.MA ) AS K "+
					"\n   INNER JOIN DONVIDOLUONG DV ON hd.dvDATHANG = dv.PK_SEQ ";
			}
			else
			{
				query = //"insert ERP_DondathangNPP_SANPHAM( Dondathang_fk, SANPHAM_FK, soluong, dvdl_fk, dongia, chietkhau, thueVAT, tungay, denngay )  " +
						//"select '" + msg + "', hd.sanpham_fk, " + sqlSOLUONG +
					"select hd.stt, isnull(dh.daDAT,0)* dbo.[LayQuyCach_DVBan](hd.sanpham_fk, null, hd.dvdl_fk ) soluongdat,hd.sanpham_fk, " + sqlSOLUONG +
					"\n       (select kho.available from nhapp_kho kho where kho.sanpham_fk=SP.pk_seq and kho.KHO_FK= "+ lsxBean.getKhoNhapId() +" and NPP_FK=(select npp_fk from ERP_HOPDONGNPP where pk_seq = "+ lsxId +") and kho.KBH_FK=( (select case when (select npp.dungchungkenh from NHAPHANPHOI npp where npp.PK_SEQ=NPP_FK)=1 then 100025 else '"+lsxBean.getKbhId()+"' end  from ERP_HOPDONGNPP where PK_SEQ="+lsxId+") )) *dbo.[LayQuyCach_DVBan](hd.sanpham_fk, null, hd.dvdl_fk )as soluongton "+
					"\n  		,hd.dvDATHANG as dvdl_fk, hd.dongia, hd.chietkhau, hd.thueVAT, hd.tungay, hd.denngay, ISNULL(sp.trongluong, 0) as trongluong, ISNULL(sp.thetich, 0) as thetich, " +
					"\n  		sp.MA, sp.TEN, DV.donvi" +
					"\n	--, (select soluong1/ soluong2 from QUYCACH where sanpham_fk = hd.sanpham_fk and DVDL1_FK = sp.DVDL_FK and DVDL2_FK = '100018') as spQuyDoi  " +
					"\n		, [dbo].[LayQuyCach](hd.sanpham_fk, null , hd.dvdl_fk ) spQuyDoi  " +
					"\n  from  " +
					"\n  (  " +
					"\n  	select stt, dvdl_fk,sanpham_fk, dvDATHANG, dvCHUAN, sum(soluong) as soluong, avg(dongia) as dongia, avg(chietkhau) as chietkhau, avg(thuevat) as thuevat, tungay, denngay  " +
					"\n  	from  " +
					"\n  	(  " +
					"\n  		select a.stt, a.dvdl_fk,sanpham_fk, a.dvdl_fk as dvDATHANG, b.dvdl_fk as dvCHUAN, " +
					"\n  			case when a.dvdl_fk IS null then a.soluong        " +
					"\n  				 when a.dvdl_fk = b.DVDL_FK then a.soluong       " +
					"\n  				 else  a.soluong * ( select SOLUONG1 /SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK ) end as soluong,  " +
					"\n  			dongia, chietkhau, thueVAT, tungay, denngay   " +
					"\n  		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq   " +
					"\n  		where HOPDONG_FK = '" + lsxId + "'   " +
					"\n  	union ALL  " +
					"\n  		select a.stt, a.dvdl_fk,sanpham_fk, a.dvdl_fk as dvDATHANG, b.dvdl_fk as dvCHUAN, " +
					"\n  			case when a.dvdl_fk IS null then a.soluong        " +
					"\n  				 when a.dvdl_fk = b.DVDL_FK then a.soluong       " +
					"\n  				 else  a.soluong * ( select SOLUONG1 / SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and DVDL1_FK = b.DVDL_FK )  end as soluong,  " +
					"\n  			dongia, chietkhau, thueVAT, tungay, denngay   " +
					"\n  		from ERP_HOPDONGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.pk_seq    " +
					"\n  		where HOPDONG_FK in ( select pk_seq from ERP_HOPDONGNPP where hopdong_fk = '" + lsxId + "' and trangthai in (1, 2) )  " +
					"\n  	)  " +
					"\n  	hopdong group by sanpham_fk, dvDATHANG, dvCHUAN, tungay, denngay, dvdl_fk, stt  " +
					"\n  )  " +
					"\n  hd left join  " +
					"\n  (  " +
					"\n  	select sanpham_fk, sum(soluong) as daDAT  " +
					"\n  	from  " +
					"\n  	(  " +
					"\n  		select a.sanpham_fk, b.DVDL_FK as dvCHUAN,       " +
					"\n  				case when a.dvdl_fk IS null then a.soluong        " +
					"\n  					 when a.dvdl_fk = b.DVDL_FK then a.soluong       " +
					"\n  					 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk  and QUYCACH.DVDL1_FK=b.DVDL_FK )        " +
					"\n  									 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk   and QUYCACH.DVDL1_FK=b.DVDL_FK) end as soluong   " +
					"\n  		from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ       " +
					"\n  		where a.dondathang_fk in (   select pk_seq from ERP_DondathangNPP where trangthai != '3' and hopdong_fk = '" + lsxId + "'  )      " +
					"\n  	)  " +
					"\n  	dathang group by sanpham_fk  " +
					"\n  )  " +
					"\n  dh on hd.sanpham_fk = dh.sanpham_fk " +
					"\n   inner Join SanPham SP on hd.SANPHAM_FK = SP.PK_SEQ " +
					"\n   INNER JOIN DONVIDOLUONG DV ON hd.dvDATHANG = dv.PK_SEQ ";
			}
			

			if(!loaidonhang.equals("2") && !loaidonhang.equals("3")){
				query += "where hd.soluong > isnull(dh.daDAT, 0) ";
			}
			query += "\n	order by hd.stt ";
			System.out.println("--CHEN SP: " + query);

			/*System.out.println("--CHEN SP: " + query);
			if(db.updateReturnInt(query) <= 0)
			{
				msg = "Sản phẩm chưa có thiết lập quy cách từ đơn vị đặt về đơn vị chuẩn. Vui lòng kiểm tra lại dữ liệu nền.";
				db.getConnection().rollback();
				return msg;
			}*/

			System.out.println("--INIT SP: " + query);
			rs = db.get(query);
			{
				String spSTT = "";
				String spMA = "";
				String spTEN = "";
				String spDONVI = "";
				String spSOLUONG = "";
				String spGIANHAP = "";
				String spCHIETKHAU = "";
				String spVAT = "";
				String spTUNGAY = "";
				String spDENNGAY = "";

				String spTRONGLUONG = "";
				String spTHETICH = "";

				String spSLTON = "";

				String spQuyDoi ="";
				NumberFormat formater = new DecimalFormat("#,###,####");

				while(rs.next())
				{
					spSTT += rs.getString("stt") + "__";
					spMA += rs.getString("MA") + "__";
					spTEN += rs.getString("TEN") + "__";
					spDONVI += rs.getString("DONVI") + "__";
					spSOLUONG += formater.format(rs.getDouble("SOLUONG")) + "__";
					
					
					System.out.println("ma = "+ rs.getString("MA") + ", sl = "+spSOLUONG );
					
					spGIANHAP += rs.getDouble("DONGIA") + "__";
					spCHIETKHAU += formater.format(rs.getDouble("chietkhau")) + "__";
					spVAT += formater.format(rs.getDouble("thueVAT")) + "__";
					spSLTON += formater.format(rs.getDouble("SOLUONGTON")) + "__";

					if(rs.getString("tungay").trim().length() > 0)
						spTUNGAY += rs.getString("tungay") + "__";
					else
						spTUNGAY += " __";

					if(rs.getString("denngay").trim().length() > 0)
						spDENNGAY += rs.getString("denngay") + "__";
					else
						spDENNGAY += " __";

					spTRONGLUONG += rs.getString("trongluong") + "__";
					spTHETICH += rs.getString("thetich") + "__";
					spQuyDoi +=rs.getString("spQuyDoi") + "__";
				}
				rs.close();
				System.out.println("spMA = "+spMA );
				System.out.println("spSOLUONG = "+spSOLUONG );
				System.out.println("spQuyDoi = "+spQuyDoi );
				
				if(spMA.trim().length() > 0)
				{
					spSTT = spSTT.substring(0, spSTT.length() - 2);
					lsxBean.setSpStt(spSTT.split("__"));
					
					spMA = spMA.substring(0, spMA.length() - 2);
					lsxBean.setSpMa(spMA.split("__"));

					spTEN = spTEN.substring(0, spTEN.length() - 2);
					lsxBean.setSpTen(spTEN.split("__"));

					spDONVI = spDONVI.substring(0, spDONVI.length() - 2);
					lsxBean.setSpDonvi(spDONVI.split("__"));

					spSOLUONG = spSOLUONG.substring(0, spSOLUONG.length() - 2);
					lsxBean.setSpSoluong(spSOLUONG.split("__"));

					spGIANHAP = spGIANHAP.substring(0, spGIANHAP.length() - 2);
					lsxBean.setSpGianhap(spGIANHAP.split("__"));

					spCHIETKHAU = spCHIETKHAU.substring(0, spCHIETKHAU.length() - 2);
					lsxBean.setSpChietkhau(spCHIETKHAU.split("__"));

					spVAT = spVAT.substring(0, spVAT.length() - 2);
					lsxBean.setSpVat(spVAT.split("__"));

					spTUNGAY = spTUNGAY.substring(0, spTUNGAY.length() - 2);
					lsxBean.setSpTungay(spTUNGAY.split("__"));

					spDENNGAY = spDENNGAY.substring(0, spDENNGAY.length() - 2);
					lsxBean.setSpDenngay(spDENNGAY.split("__"));

					spTRONGLUONG = spTRONGLUONG.substring(0, spTRONGLUONG.length() - 2);
					lsxBean.setSpTrongluong(spTRONGLUONG.split("__"));

					spTHETICH = spTHETICH.substring(0, spTHETICH.length() - 2);
					lsxBean.setSpThetich(spTHETICH.split("__"));

					spQuyDoi = spQuyDoi.substring(0, spQuyDoi.length() - 2);
					lsxBean.setSpQuyDoi(spQuyDoi.split("__"));

					spSLTON = spSLTON.substring(0, spSLTON.length() - 2);
					lsxBean.setSpSoluongton(spSLTON.split("__"));
				}
			}

			/*query = "insert ERP_DONDATHANGNPP_CHIETKHAU(DONDATHANG_FK, DIENGIAI, GIATRI, LOAI) " +
					"select '" + msg + "', DIENGIAI, GIATRI, LOAI from ERP_HOPDONGNPP_CHIETKHAU where hopdong_fk = '" + lsxId + "' ";
			System.out.println("--CHEN SP 2: " + query);
			if(!db.update(query))
			{
				msg = "Lỗi khi chuyển sang đơn đặt hàng: " + query;
				db.getConnection().rollback();
				return msg;
			}*/

			//CHECK BOOKED THEO DV CHUAN
			/*query =  "select khoxuat_fk as kho_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN, SUM(dathang.soluong) as soluongXUAT,  " +
					"	ISNULL( ( select AVAILABLE from NHAPP_KHO where kho_fk = dathang.khoxuat_fk and sanpham_fk = sp.PK_SEQ and kbh_fk = dathang.kbh_fk and npp_fk = dathang.npp_fk ), 0) as tonkho  " +
					"from     " +
					"(     " +
					"	select c.kho_fk as khoxuat_fk, c.npp_fk, case when ( select dungchungkenh from NHAPHANPHOI where PK_SEQ = c.npp_fk ) = 1 then 100025 else c.KBH_FK end as KBH_FK, " +
					"		a.sanpham_fk, b.DVDL_FK as dvCHUAN,     " +
					"			case when a.dvdl_fk IS null then a.soluong      " +
					"				 when a.dvdl_fk = b.DVDL_FK then a.soluong     " +
					"				 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )      " +
					"								 / ( select SOLUONG2 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk and dvdl1_fk = b.dvdl_fk )	 end as soluong   " +
					"	from ERP_DONDATHANGNPP_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  " +
					"			inner join ERP_DONDATHANGNPP c on a.dondathang_fk = c.pk_seq    " +
					"	where a.dondathang_fk in ( " + msg + " )     " +
					")     " +
					"dathang inner join SANPHAM sp on dathang.sanpham_fk = sp.PK_SEQ   " +
					"group by khoxuat_fk, npp_fk, kbh_fk, sp.PK_SEQ, sp.TEN  ";

			System.out.println("--CHECK TON KHO: " + query);

			rs = db.get(query);
			if(rs != null)
			{
				while(rs.next())
				{
					String khoID = rs.getString("kho_fk");
					String kbhID = rs.getString("kbh_fk");
					String nppID = rs.getString("npp_fk");
					String spID = rs.getString("PK_SEQ");

					double soluong = rs.getDouble("soluongXUAT");
					double tonkho = rs.getDouble("tonkho");

					if(soluong > tonkho)
					{
						msg = "Sản phẩm ( " + rs.getString("TEN") + " ) với số lượng yêu cầu ( " + rs.getString("soluongXUAT") + " ) không đủ tồn kho ( " + rs.getString("tonkho") + " ). Vui lòng kiểm tra lại.";
						db.getConnection().rollback();
						rs.close();
						return msg;
					}

					//CAP NHAT KHO XUAT TONG
					query = "Update NHAPP_KHO set booked = booked + '" + soluong + "', AVAILABLE = AVAILABLE - '" + soluong + "' " +
							"where KHO_FK = '" + khoID + "' and KBH_FK = '" + kbhID + "' and NPP_FK = '" + nppID + "' and SANPHAM_FK = '" + spID + "' ";
					if(!db.update(query))
					{
						msg = "Khong the cap nhat ERP_KHOTT_SANPHAM: " + query;
						db.getConnection().rollback();
						rs.close();
						return msg;
					}
				}
				rs.close();
			}*/

			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			db.update("rollback");
			db.shutDown();
			e.printStackTrace();
			return "Exception: " + e.getMessage();
		}
		

		return msg;
	}

	private String Chot(String lsxId,String userId) 
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);
		
			String query = "update ERP_HOPDONGNPP set trangthai = '1' where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "Khong the chot: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			String msgLog = ErpHopdongNpp.Log_HopDongNPP( db,lsxId,"Chot",userId);
			if(msgLog.trim().length() > 0)
			{
				msg  = msgLog;
				db.getConnection().rollback();
				return msg;
			} 
			
			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
	}

	private String DeleteChuyenKho(String lsxId,String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			String query = "delete ERP_HopDongNpp_SanPham where hopdong_fk = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "1.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			query = "delete ERP_HopDongNpp_ChietKhau where hopdong_fk = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "2.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			String msgLog = ErpHopdongNpp.Log_HopDongNPP( db,lsxId,"Xóa",userId);
			if(msgLog.trim().length() > 0)
			{
				msg  = msgLog;
				db.getConnection().rollback();
				return msg;
			} 
			
			query = "delete ERP_HopDongNpp where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "3.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			db.update("rollback");
			db.shutDown();
			return "Exception: " + e.getMessage();
		}
		
		return "";
		
	}
	private String KhongTrungThau(String lsxId,String userId)
	{
		dbutils db = new dbutils();
		String msg = "";
		try
		{
			db.getConnection().setAutoCommit(false);

			String query = "";
			
			query = "update ERP_HopDongNpp set [isKhongTrungThau] = 1 , nguoisua = "+userId+", trangthai = 4 where pk_seq = '" + lsxId + "'";
			if(!db.update(query))
			{
				msg = "3.Khong the xoa: " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			String msgLog = ErpHopdongNpp.Log_HopDongNPP( db,lsxId,"KhongTrungThau",userId);
			if(msgLog.trim().length() > 0)
			{
				msg  = msgLog;
				db.getConnection().rollback();
				return msg;
			} 
			
			
			
			db.getConnection().commit();
			db.shutDown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
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
	    
		IErpHopdongNppList obj = new ErpHopdongNppList();
		obj.setLoaidonhang(loaidonhang);
		
		
		 String view = request.getParameter("view");
		    if(view == null)
		    	view = "";
		 obj.setView(view);   
	 
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
		
		
	    String userId = util.antiSQLInspection(request.getParameter("userId")); 
	    obj.setUserId(userId);
	    
	    
	    if(action.equals("Tao moi"))
	    {
	    	IErpHopdongNpp lsxBean = new ErpHopdongNpp();
	    	
	    	lsxBean.setUserId(userId);
	    	
	    	lsxBean.setLoaidonhang(loaidonhang);
	    	
	    	lsxBean.createRs();
	    	session.setAttribute("dvkdId", "");
			session.setAttribute("kbhId", "");
			session.setAttribute("nppId", "");
    		
	    	session.setAttribute("lsxBean", lsxBean);
	    	
    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNppNew.jsp";
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
		    	
		    	String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNpp.jsp";
				response.sendRedirect(nextJSP);
		    }
	    	else
	    	{
		    	String search = getSearchQuery(request, obj);
		    	obj.setUserId(userId);
		    	obj.init(search);
				
				
		    	session.setAttribute("obj", obj);  	
	    		session.setAttribute("userId", userId);
		
	    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpHopDongNpp.jsp";
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	}
	
	private String getSearchQuery(HttpServletRequest request, IErpHopdongNppList obj)
	{
		//Utility util = new Utility();
		geso.dms.center.util.Utility util = new geso.dms.center.util.Utility();
		
		String query = "select a.PK_SEQ, a.trangthai, a.mahopdong, a.loaidonhang, a.tungay, a.denngay, isnull(c.ten, '') as nppTEN, b.ten as khoTEN, NV.TEN as nguoitao, b.ten as khonhan, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua, isnull(a.NOTE, '') as NOTE   " +
						"from ERP_HopDongNPP a inner join KHO b on a.kho_fk = b.pk_seq left join KHACHHANG c on a.khachhang_FK = c.pk_seq  " +
						"inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
						"inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ where a.pk_seq > 0  and a.kho_fk in "+util.quyen_kho(obj.getUserId());
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
		
		String nppId = request.getParameter("nppId");
		if(nppId == null)
			nppId = "";
		obj.setNppTen(nppId);
		
		String khId = request.getParameter("khId");
		if(khId == null)
			khId = "";
		obj.setKhTen(khId);
		
		String mahd = request.getParameter("mahd");
		if(mahd == null)
			mahd = "";
		obj.setMaHD(mahd);
		
		String loaihdid=request.getParameter("loaihdId");
		if(loaihdid == null)
			loaihdid = "";
		obj.setLoaiHD(loaihdid);		
		
		if(tungay.length() > 0)
			query += " and a.tungay >= '" + tungay + "'";
		
		if(denngay.length() > 0)
			query += " and a.denngay <= '" + denngay + "'";
	
		if(trangthai.length() > 0)
			query += " and a.TrangThai = '" + trangthai + "'";
		
		if(nppId.length() > 0)
			query += " and a.NPP_FK = '" + nppId + "'";
		
		if(khId.length() > 0)
			query += " and a.KhachHang_FK = '" + khId + "'";
		
		if(loaihdid.length()>0)
			query+=" and a.loaidonhang = '"+loaihdid+"'";
		
		if(mahd.length()>0)
			query+=" and a.mahopdong like '%"+mahd+"%' ";
		
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
