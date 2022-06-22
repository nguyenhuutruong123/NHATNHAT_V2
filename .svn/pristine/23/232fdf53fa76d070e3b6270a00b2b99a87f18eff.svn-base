package geso.dms.distributor.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.report.Ireport;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class BCDonHangBanTrongKy extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	
	private String setQueryETC(HttpServletRequest request,IStockintransit obj)
	{

		
		String conditiondh= "";
		String conditionth= "";
		Utility util = new Utility();
		
		
		conditiondh += " and ddh.NPP_FK in " + util .quyen_npp(obj.getuserId()) ;
		conditionth += " and th.Npp_FK in " + util .quyen_npp(obj.getuserId()) ;
		
		if (obj.getnppId().length() > 0)
		{
			conditiondh += " and ddh.Npp_FK ='" + obj.getnppId() + "'";
			conditionth += " and th.Npp_FK ='" + obj.getnppId() + "'";
		}
		
		if (obj.getkhuvucId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk ='" + obj.getkhuvucId() + "'";
			conditionth += " and npp.khuvuc_fk ='" + obj.getkhuvucId() + "'";
			
		}
		if (obj.getvungId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk =" + obj.getvungId() + ")  ";
			conditionth += " and npp.khuvuc_fk  in (select pk_seq from khuvuc where vung_fk =" + obj.getvungId() + ")  ";
			
		}

		if(obj.getTrangthai().length()>0)
		{
			conditiondh += " and ddh.trangthai ='" + obj.getTrangthai() + "'";
			conditionth += " and th.trangthai ='" + obj.getTrangthai() + "'";
		}		
		
		String condition2019 = "";
		if(obj.getTinhthanhid().length()>0) { condition2019 += " and exists ( select 1 from tinhthanh where pk_seq = kh.tinhthanh_fk and pk_seq = '"+ obj.getTinhthanhid() +"' ) "; }
		if(obj.getQhId().length()>0) 		{ condition2019 += " and exists ( select 1 from quanhuyen where pk_seq = kh.quanhuyen_fk and pk_seq = '"+ obj.getQhId() +"' ) "; }
		if(obj.getPhuongxa().length()>0) 	{ condition2019 += " and exists ( select 1 from phuongxa where pk_seq = kh.phuongxa and pk_seq = '"+ obj.getPhuongxa() +"' ) "; }
		
		String query =
			 "\n  	with ddh as    " + 
			 "\n  	(    " + 
			 "\n  	    " + 
			 "\n  		SELECT distinct DDH.*   " + 
			 "\n 		FROM     " + 
			 "\n  		ERP_DONDATHANGNPP DDH     " + 
			 "\n  		WHERE DDH.NgayDonHang >='"+obj.gettungay()+"' AND DDH.NgayDonHang <= '"+obj.getdenngay()+"' and ddh.trangthai !=3    " + 
			 "\n  			AND DDH.KHACHHANG_FK IS NOT NULL  " + 
			 "\n  	)    " + 
			 "\n   	, th as      " + 
			 "\n   	(      " + 
			 "\n   		SELECT distinct th.*   " + 
			 "\n   		FROM     Erp_HangTraLaiNpp th       " + 
			 "\n   		INNER JOIN KHACHHANG KH ON KH.PK_SEQ = th.KHACHHANG_FK  " + 
			 "\n   		WHERE th.NGAYTRA >='"+obj.gettungay()+"' AND th.NGAYTRA <=  '"+obj.getdenngay()+"'  and kh.kbh_fk = 100052 and th.trangthai !=3  	    " + 
			 "\n   	)     " + 
			 "\n  	SELECT  cast ( ISNULL(DDH.pk_seq,'NA') as varchar) AS MaDonHang, cast ( DDH.HopDong_fk as varchar) MaHopDong  " + 
			 "\n  		,DDH.NgayDonHang  " + 
			 "\n  	,CASE DDH.TRANGTHAI  WHEN '0' THEN N'Chưa chốt' WHEN '1' THEN N'Chờ duyệt'   WHEN '2' THEN N'Đã duyệt'   WHEN '3' THEN N'Đã Hủy'   WHEN '4' THEN N'Hoàn Tất'  END AS TRANGTHAI " + 
			 "\n 	,npp.mafast MaNPP,npp.ten TenNPP,kh.MAfast AS MAKH, kh.TEN AS TENKH, KV.TEN AS VUNG,V.TEN   AS MIEN ,SP.MA AS MASP,     " + 
			 "\n      " + 
			 "\n  		SP.TEN AS TENSP,DVDL.DONVI    " + 
			 "\n  		   " + 
			 "\n  		 , ISNULL( DH_SP.SOLUONG,0) AS SoLuong,(DH_SP.DONGIA* ISNULL(DH_SP.SOLUONG,0)) AS TongTien     " + 
			 "\n      " + 
			 "\n  		, case  DDH.isdhkhac when 0 then 'HB' else 'KM' end loaidonhang    " + 
			 "\n      " + 
			 "\n  	FROM DDH       " + 
			 "\n  	INNER JOIN khachhang kh ON kh.PK_SEQ = DDH.khachhang_fk    " + 
			 "\n  	inner join nhaphanphoi npp on npp.pk_Seq = DDH.npp_Fk   " + 
			 "\n  	LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=DDH.nguoisua     " + 
			 "\n  	LEFT JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK         " + 
			 "\n  	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK        " + 
			 "\n  	INNER JOIN ERP_DONDATHANGNPP_SANPHAM DH_SP ON DH_SP.DONDATHANG_FK = DDH.PK_SEQ     " + 
			 "\n  	INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=DH_SP.dvdl_fk     " + 
			 "\n  	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DH_SP.SANPHAM_FK     " + 
			 "\n  	WHERE DH_SP.soluong > 0  " + conditiondh + condition2019 +
			 "\n  	union all    " + 
			 "\n   	SELECT  cast (-1* th.pk_seq  as varchar) AS MaDonHang,'' MaHopDong  " + 
			 "\n   		,th.NGAYTRA   " + 
			 "\n  	,CASE th.TRANGTHAI  WHEN '0' THEN N'Chưa chốt' WHEN '1' THEN N'Đã chốt'   WHEN '2' THEN N'Hoàn tất'   WHEN '3' THEN N'Đã Hủy '     END AS TRANGTHAI  " + 
			 "\n  	,npp.mafast MaNPP,npp.ten TenNPP,kh.MAfast , kh.TEN , KV.TEN AS VUNG,V.TEN   AS MIEN ,SP.MA AS MASP,       " + 
			 "\n         " + 
			 "\n   		SP.TEN AS TENSP,DVDL.DONVI      " + 
			 "\n   		 ,(-1)* ISNULL( DH_SP.SOLUONG,0) AS SoLuong      " + 
			 "\n   		 ,(-1)*(DH_SP.DONGIA* ISNULL(DH_SP.SOLUONG,0)) AS tongTien       " + 
			 "\n         " + 
			 "\n   		, N'TH' loaidonhang      " + 
			 "\n         " + 
			 "\n   	FROM th         " + 
			 "\n   	INNER JOIN khachhang kh ON kh.PK_SEQ = th.khachhang_fk    " + 
			 "\n  	inner join nhaphanphoi npp on npp.pk_Seq = th.npp_Fk   " + 
			 "\n   	LEFT JOIN NHANVIEN ND ON ND.PK_SEQ=th.nguoisua       " + 
			 "\n   	LEFT JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK              " + 
			 "\n   	LEFT JOIN VUNG V ON V.PK_SEQ=KV.VUNG_FK            " + 
			 "\n   	INNER JOIN Erp_HangTraLaiNpp_SanPham DH_SP ON DH_SP.HangTraLai_fk = th.PK_SEQ         " + 
			 "\n   	INNER JOIN SANPHAM SP ON SP.PK_SEQ = DH_SP.SANPHAM_FK     " + 
			 "\n   	INNER JOIN DONVIDOLUONG DVDL ON DVDL.PK_SEQ=SP.dvdl_fk         " + 
			 "\n   	WHERE 1=1  " +conditionth + condition2019;
		System.out.println("BC Don Dat Hang NPP : " + query);
		return query;
	}
	
	private String setQuery(HttpServletRequest request,String tungay,String denngay,String st,IStockintransit obj) 
	{
		String chuoi="";
		if(obj.getkhuvucId().length() >0 ){
			chuoi=chuoi+ " and kv.pk_seq="+obj.getkhuvucId();
			
		}
		if(obj.getvungId().length() >0 ){
			chuoi=chuoi+ " and v.pk_seq="+obj.getvungId();			
		}
		
		if(obj.getTtId().length() > 0){
			chuoi=chuoi+ " and tt.pk_seq ="+obj.getTtId();
		}
		
		if(obj.getkhoId().length()>0)
		{
			chuoi+=" and kho.pk_Seq='"+obj.getkhoId()+"'";
		}
		
		String condition2019 = "";
		if(obj.getTinhthanhid().length()>0) { condition2019 += " and exists ( select 1 from tinhthanh where pk_seq = kh.tinhthanh_fk and pk_seq = '"+ obj.getTinhthanhid() +"' ) "; }
		if(obj.getQhId().length()>0) 		{ condition2019 += " and exists ( select 1 from quanhuyen where pk_seq = kh.quanhuyen_fk and pk_seq = '"+ obj.getQhId() +"' ) "; }
		if(obj.getPhuongxa().length()>0) 	{ condition2019 += " and exists ( select 1 from phuongxa where pk_seq = kh.phuongxa and pk_seq = '"+ obj.getPhuongxa() +"' ) "; }
		
		String condition="";
		String conditionTVe="";
		String conditionKM="";
		
		if(obj.getTrangthai().length()<=0)
		{
			condition+= " and  dh.TrangThai !=2 ";
			conditionKM+= " and  dh.TrangThai !=2 ";

		}else if( obj.getTrangthai().equals("0") || obj.getTrangthai().equals("1") || obj.getTrangthai().equals("2")  || obj.getTrangthai().equals("3")  )
		{
			condition+= "and  dh.TrangThai ='"+obj.getTrangthai()+"' ";
			conditionKM+= "and  dh.TrangThai ='"+obj.getTrangthai()+"' ";
			conditionTVe += "and  dh.TrangThai ='"+obj.getTrangthai()+"' ";
		}else if(obj.getTrangthai().equals("4"))
		{
			condition+= " and  dh.DaIn=1 and (dh.TrangThai in (1,3) ) ";
			conditionKM+= " and  dh.DaIn=1 and (dh.TrangThai in (1,3) ) ";
		}
		else if(obj.getTrangthai().equals("5"))
		{
			condition+= " and dh.XuatHoaDon=1  and (dh.TrangThai in (1,3) ) ";
			conditionKM+= " and dh.XuatHoaDon=1  and (dh.TrangThai in (1,3) ) ";
		}
		
		if(obj.getnppId().length()>0)
		{
			condition+= " and  dh.npp_fk= '"+obj.getnppId()+"' ";
			conditionKM+= " and  dh.npp_fk= '"+obj.getnppId()+"' ";
			conditionTVe += "and  dh.npp_fk= '"+obj.getnppId()+"' ";
		}
		
		if(obj.getDdkd().length()>0)
		{
			condition+= " and  dh.ddkd_fk ='"+obj.getDdkd()+"' ";
			conditionKM+= " and  dh.ddkd_fk ='"+obj.getDdkd()+"' ";
			conditionTVe += "and  dh.ddkd_fk ='"+obj.getDdkd()+"' ";
		}
		
		if(obj.gettungay().length()>0)
		{
			condition+= " and  dh.NgayNhap>= '"+obj.gettungay()+"' ";
			conditionKM+= " and  dh.NgayNhap>= '"+obj.gettungay()+"' ";
			conditionTVe += "and  dh.NgayTra>= '"+obj.gettungay()+"' ";
		}
		
		if(obj.getdenngay().length()>0)
		{
			condition+= " and  dh.NgayNhap <= '"+obj.getdenngay()+"' ";
			conditionKM+= " and  dh.NgayNhap <= '"+obj.getdenngay()+"' ";
			conditionTVe += "and  dh.NgayTra <= '"+obj.getdenngay()+"' ";
		}
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condition+=" and dh_sp.sanpham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+obj.getNhomhangid()+"' ) ";
			conditionTVe +=" and dh_sp.sanpham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+obj.getNhomhangid()+"' ) ";
		}
		
	
		
		
		
		String condition_ETC="";
		if(obj.getTrangthai().length()<=0)
		{
			condition_ETC=" and trangthai !=3 ";
		}else 
		{
			condition_ETC=" and trangthai ='"+obj.getTrangthai()+"' ";
		}
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condition_ETC+=" and dh_sp.sanpham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+obj.getNhomhangid()+"' ) ";
			
		}
		String condionKMNH="";
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condionKMNH+=" and  dh_sp.SPMA in (select sp.MA from  sanpham sp inner join NhomHang_SanPham nh on nh.SanPham_FK=sp.PK_SEQ where nh.NhomHang_FK='"+obj.getNhomhangid()+"') ";
			
		}
		
		String condionKMCK="";
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condionKMCK+=" and  dh_sp.SPMA in (select sp.MA from  sanpham sp inner join NhomHang_SanPham nh on nh.SanPham_FK=sp.PK_SEQ where nh.NhomHang_FK='"+obj.getNhomhangid()+"') ";
			
		}
	
		
		/*****************************************************************Dong san pham ban******************************************************************************/
		String query ="";
		
		if(obj.getMuc_KhachHang().equals("1"))
		{
			query=  "\n select distinct * from ( "+
					"\n select distinct dh.ghichu,kh.macu,nvgn.ten as tennvgn,kbh.TEN as KenhBanHang,  v.ten as vung,kv.ten as khuvuc,tt.ten as tinhthanh,  npp.ma as sitecode ,npp.ten as nhaphanphoi,dh.ngaynhap as ngay,isnull(ddkd.mafast,'') as nvbhid, isnull(ddkd.ten,'') as nvbhten, kh.Mafast as makh, kh.ChuCuaHieu , kh.ten as tenkh, kh.diachi as diachi,kh.dienthoai,dh.pk_seq as dhid "+ 
					"\n	,''as ngayhoadon,sp.ma as masp,sp.ten as tensanpham, isnull(dh.soluong,0) as soluong, isnull(dh.soluong,0) *isnull (qc.soluong2,1) /isnull(qc.soluong1,1) as sothung, "+ 
					"\n isnull(dh.giamua,0) as dongia,isnull(dh.chietkhau,0) as chietkhau , "+ 
					"\n isnull(dh.soluong,0)*isnull(dh.giamua,0)  as tongtien, 0 as tienkhuyenmai, ' ' as scheme, dh.trangthai,dh.DaIn,dh.DaXuatHoaDon, dh.isdonhangle, dh.issampling, cl.ten as chungloai, nsp1.nhomsanpham ,dh.cttbid,dh.isPDA ,gsbh.mafast as gsMa,gsbh.ten as gsTen, "+
					"\n kho.TEN  as KhoTen, kh.XuatKhau,kh.KhongKyHopDong,0 as DoanhSo,  "+			
					"\n 0 as ckbosung,0 as  ckdonhang,0 as ckkm,0 as cktichluy ,isnull(kh.thanhtoan,'0') as thanhtoan   "+
					"\n , '' AS SoHoaDon, '1' as loai, kh.DiaChiGiaoHang "+
					"\n from "+  	
					"\n ( "+ 
					"\n 	select dh.ghichu,-1* dh.pk_seq pk_seq, dh.ngaytra ngaynhap,dh.ddkd_fk,dh.khachhang_fk,dh.npp_fk,dh.gsbh_fk,dh.kho_fk,dh.kbh_fk,dh_sp.sanpham_fk,   " + 
					"\n     isnull(dh_sp.dongia,0) as  giamua ,(-1)* isnull(dh_sp.soluong,0) as soluong,0 as chietkhau,   " + 
					"\n     dh.trangthai,0 as DaIn,0 as DaXuatHoaDon ,  '0' as isdonhangle, '0' as issampling,NULL  as cttbid,0 as isPDA   " + 
					"\n     from erp_hangtralainpp dh  " +
					"\n 	inner join erp_hangtralainpp_sanpham dh_sp on dh_sp.hangtralai_fk = dh.pk_seq  " +
					"\n 	where 1=1  "+conditionTVe+" ";
				if(st.length()>0)
				query+=  st;
				query+=
					"\n		union all "+ 
					"\n 	select dh.ghichu,dh.pk_seq,	dh.ngaynhap,dh.ddkd_fk,dh.khachhang_fk,dh.npp_fk,dh.gsbh_fk,dh_sp.kho_fk,dh.kbh_fk,dh_sp.sanpham_fk, "+  
					"\n 	dh_sp.giamua ,dh_sp.soluong,isnull(dh_sp.chietkhau,0) as chietkhau , "+
					"\n     dh.trangthai,dh.DAIN,dh.DaXuatHoaDon, dh.isdonhangle, dh.issampling ,NULL  as cttbid,ISNULL(dh.isPDA,0) as isPDA "+
					"\n 	from donhang dh "+ 		
					"\n 	inner join donhang_sanpham  dh_sp on dh.pk_seq=dh_sp.donhang_fk"+  
					"\n 	where 1=1 "+condition+"  " ; 
					if(st.length()>0) { query+=  st; }	
		   query += "\n ) dh "+
				     "\n inner join khachhang kh on kh.pk_seq = dh.khachhang_fk "+  
				     "\n left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)   "+
				     "\n inner join KENHBANHANG kbh on kbh.PK_SEQ=dh.KBH_FK     "+
				     "\n inner join nhaphanphoi npp on npp.pk_seq=dh.npp_fk "+   
				     "\n inner join khuvuc kv on kv.pk_seq=npp.khuvuc_fk "+  
				     "\n inner join vung v on v.pk_seq=kv.vung_fk "+ 
				     "\n inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
				     "\n inner join sanpham sp on sp.pk_seq = dh.sanpham_fk "+   
				     "\n INNER join KHO kho on kho.PK_SEQ=dh.KHO_FK "+
				     "\n left join "+ 
				     "\n ( select sanpham_fk,dvdl2_fk,dvdl1_fk,soluong1,soluong2 from quycach qc where qc.dvdl2_fk=100018 )	qc on sp.pk_seq=qc.sanpham_fk and qc.dvdl1_fk=sp.dvdl_fk "+  
				     "\n left join daidienkinhdoanh ddkd on ddkd.pk_seq = dh.ddkd_fk "+ 
				     "\n left join giamsatbanhang gsbh on gsbh.pk_seq=dh.gsbh_fk   "+
				     "\n inner join sanpham sp1 on sp1.pk_seq = dh.sanpham_fk "+
				     "\n left join chungloai cl on cl.pk_seq = sp1.chungloai_fk "+ 
				     "\n left join "+ 
				     "\n ( "+
				     "\n     select nsp_sp.sp_fk, nsp.diengiai as nhomsanpham, nsp.trangthai, nsp.loainhom, nsp.type  from nhomsanpham nsp "+  
				     "\n     inner join nhomsanpham_sanpham nsp_sp on nsp_sp.nsp_fk = nsp.pk_seq "+ 
				     "\n     where nsp.trangthai = '1' and nsp.loainhom = '0' and nsp.type = '0' "+ 
				     "\n ) nsp1 on nsp1.sp_fk = dh.sanpham_fk "+
				     "\n where 1=1 " + chuoi + condition2019;	
				     	 
			  		/*****************************************************************Dong san pham KM******************************************************************************/
				    query+= 
				    "\n union all " +
				    "\n select dh.ghichu,kh.macu,nvgn.ten as tennvgn, kbh.TEN as KenhBanHang, v.ten as vung,kv.ten as khuvuc,tt.ten as tinhthanh,   npp.sitecode ,npp.ten as nhaphanphoi, dh.ngaynhap as ngay,ddkd.mafast as nvbhid, "+ 
				    "\n ddkd.ten as nvbhten,kh.Mafast as makh,kh.ChuCuaHieu,kh.ten as tenkh, kh.diachi as diachi,kh.dienthoai,  dh.pk_seq as dhid,dh.ngaynhap as ngayhoadon,sp.ma as masp, "+ 
				    "\n sp.ten as tensp, isnull(dh.soluong,0) as soluong , isnull(dh.soluong,0) *isnull (qc.soluong2,1) /isnull(qc.soluong1,1) as sothung, "+ 
				    "\n 0 as dongia, 0 as chietkhau,0 as tongtien, dh.tongtien as tienkhuyenmai,ISNULL(ctkm.scheme,cttb.SCHEME) +' ' +ISNULL(ctkm.diengiai,cttb.diengiai) as scheme,dh.trangthai,dh.DaIn,dh.DaXuatHoaDon, dh.isdonhangle, dh.issampling, cl.ten as chungloai, nsp1.nhomsanpham ,dh.cttbid,dh.isPDA as isPDA,gsbh.mafast as gsMa,gsbh.ten as gsTen, "+
				    "\n kho.TEN as KhoTen,kh.XuatKhau,kh.KhongKyHopDong,0 as DoanhSo , "+
				    "\n 0 as ckbosung,0 as  ckdonhang,0 as ckkm,0 as cktichluy ,isnull(kh.thanhtoan,'0') as thanhtoan   "+
					"\n	,'' AS SoHoaDon,'1' as loai , kh.DiaChiGiaoHang "+
				    "\n from "+ 
				    "\n ( "+	
				    "\n 	select '' ghichu,dh.pk_seq,	dh.ngaynhap,dh.ddkd_fk,dh.khachhang_fk,dh.npp_fk,dh.gsbh_fk,KM.KHO_FK,dh.kbh_fk,dh_sp1.spma ,dh_sp1.ctkmid,(-1)*dh_sp1.soluong as soluong,(-1)* dh_sp1.tonggiatri as tongtien, "+ 
				    "\n		dh.trangthai,dh.DaIn,dh.DaXuatHoaDon, '0' as isdonhangle, '0' as issampling,NULL  as cttbid,0 as isPDA "+
				    "\n 	from donhangtrave dh  inner  join donhang_ctkm_trakm  dh_sp1 on dh.donhang_fk=dh_sp1.donhangid "+ 
				    "\n 	INNER JOIN CTKHUYENMAI KM ON KM.PK_SEQ=dh_sp1.CTKMID "+
				    "\n 	where dh.trangthai=3  and dh.ngaynhap >='"+tungay+"' and dh.ngaynhap <= '"+denngay+"'    ";
				    if(st.length()>0) { query+=  st; }
				    query+= 
				    "\n 	union all"+  
				    "\n 	select dh.ghichu,dh.pk_seq,	dh.ngaynhap,dh.ddkd_fk,dh.khachhang_fk,dh.npp_fk,dh.gsbh_fk,KM.kho_fk,dh.kbh_fk,isnull( dh_sp.spma,dh_tb.spMa)as spMa,dh_sp.ctkmid,isnull(dh_sp.soluong,dh_tb.soluong) as soluong, sum( case when dh_sp.spma is null and dh_tb.spma is null then isnull(dh_sp.tonggiatri,dh_tb.tonggiatri) else 0 end   ) as tongtien, "+  
				    "\n     dh.trangthai,dh.DAIN,dh.DaXuatHoaDon, dh.isdonhangle, dh.issampling  ,dh_tb.cttb_fk as cttbid,isnull(dh.isPDA,0) as isPDA "+
				    "\n 	from donhang dh "+   
				    "\n 	left join donhang_ctkm_trakm  dh_sp on dh.pk_seq=dh_sp.donhangid " +
				    "\n 	left JOIN CTKHUYENMAI KM ON KM.PK_SEQ=dh_sp.CTKMID "+
				    "\n 	left join "+
				    "\n 	( "+ 
				    "\n 		select sp.MA as spMa,dh_tb.* "+
				    "\n 		from donhang_cttb_tratb dh_tb "+
				    "\n 		inner join SANPHAM sp on sp.PK_SEQ=dh_tb.SANPHAM_FK "+
				    "\n 	) as dh_tb on dh_tb.DONHANG_FK=dh.pk_seq   "+  
				    "\n 	where dh.trangthai<>2  and dh.ngaynhap >='"+tungay+"' and dh.ngaynhap <= '"+denngay+"'  "+conditionKM+condionKMNH+"  ";
				    if(st.length()>0) { query+= st; }
				    query+= 
				    "\n     group by dh.ghichu,dh.pk_seq,	dh.ngaynhap,dh.ddkd_fk,dh.khachhang_fk,dh.npp_fk,dh.gsbh_fk,KM.kho_fk,dh.kbh_fk,dh_sp.spma,dh_tb.spMa,dh_sp.ctkmid,dh_sp.soluong, dh_tb.soluong,dh.trangthai, dh.isdonhangle, dh.issampling,dh_tb.cttb_fk,dh.isPDA,dh.DAIN,dh.DAXUATHOADON "+ 
				    "\n ) dh "+
				    "\n left join sanpham sp on dh.spma=sp.ma "+  
				    "\n left join ctkhuyenmai ctkm on ctkm.pk_seq = dh.ctkmid "+  
				    "\n left join cttrungbay cttb on cttb.pk_seq=dh.cttbid "+  
				    "\n inner join KHO kho on kho.PK_SEQ=dh.KHO_FK "+
				    "\n inner join nhaphanphoi npp on npp.pk_seq=dh.npp_fk "+  
				    "\n inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
				    "\n inner join khuvuc kv on kv.pk_seq=npp.khuvuc_fk "+  
				    "\n inner join vung v on v.pk_seq=kv.vung_fk "+   
				    "\n left join "+ 
				    "\n ( select sanpham_fk,dvdl2_fk,dvdl1_fk,soluong1,soluong2 from quycach qc where qc.dvdl2_fk=100018 )	qc on sp.pk_seq=qc.sanpham_fk and qc.dvdl1_fk=sp.dvdl_fk "+  	
				    "\n inner join khachhang kh on kh.pk_seq = dh.khachhang_fk "+  
				    "\n left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)   "+
				  	"\n	inner join KENHBANHANG kbh on kbh.PK_SEQ=dh.KBH_FK     "+
				    "\n left join daidienkinhdoanh ddkd on ddkd.pk_seq = dh.ddkd_fk "+ 
				    "\n left join giamsatbanhang gsbh on gsbh.pk_seq=dh.gsbh_fk   "+
				    "\n left join sanpham sp1 on sp1.ma = dh.spma "+
				    "\n left join chungloai cl on cl.pk_seq = sp1.chungloai_fk "+ 
				    "\n left join "+
				    "\n ( select nsp_sp.sp_fk, nsp.diengiai as nhomsanpham, nsp.trangthai, nsp.loainhom, nsp.type  from nhomsanpham nsp "+  
				    "\n   inner join nhomsanpham_sanpham nsp_sp on nsp_sp.nsp_fk = nsp.pk_seq "+ 
				    "\n   where nsp.trangthai = '1' and nsp.loainhom = '0' and nsp.type = '0' " +
				    "\n ) nsp1 on nsp1.sp_fk = sp.pk_seq "+  
				    "\n where 1=1 " + chuoi + condition2019;
				    
				    /*****************************************************************Dong CK******************************************************************************/
				    System.out.println("do dai nphang id "+obj.getNhomhangid().length());
				    if(obj.getNhomhangid().length()<=0)
				    {
				    	
					 
					 
						query +="\n	union all " +
						"\n select  dh.ghichu,kh.macu,nvgn.ten as tennvgn,	kbh.TEN as KenhBanHang, v.ten as vung,kv.ten as khuvuc, tt.ten as tinhthanh, npp.ma as sitecode ,npp.ten as nhaphanphoi,dh.ngaynhap as ngay,   "+   
						"\n ddkd.mafast as nvbhid,ddkd.ten as nvbhten, kh.Mafast as makh, kh.ChuCuaHieu,kh.ten as tenkh,     "+
						"\n kh.diachi as diachi,kh.dienthoai,dh.pk_seq as dhid       "+
						"\n ,'' as ngayhoadon,' ' as masp,' ' as tensanpham,      "+
						"\n 0 as soluong,0 as sothung,       "+
						"\n 0 as dongia,dh.chietkhau as chietkhau ,   "+ 
						"\n 0  as tongtien, 0 as tienkhuyenmai,' ' as scheme,  "+ 
					 	"\n dh.trangthai,dh.DaIn,dh.DaXuatHoaDon, dh.isdonhangle, dh.issampling,   "+  
						"\n ' ' as chungloai, ' ' as nhomsanpham ,null  as  cttbid,isnull(dh.ispda,0) as ispda  ,gsbh.mafast as gsma,gsbh.ten as gsten,  "+  
						"\n kho.TEN   as khoten,kh.xuatkhau,kh.khongkyhopdong   ,total.doanhso as doanhso,   "+
						"\n total.ckbosung,total.ckdonhang,total.ckkm,total.cktichluy ,isnull(kh.thanhtoan,'0') as thanhtoan   "+  
						"\n	,'' AS SoHoaDon ,'1' as loai , kh.DiaChiGiaoHang "+
						"\n from    "+
						"\n (      "+
						"\n 	select total.donhang_fk,sum(total.doanhso) as doanhso,sum(total.cktichluy) as cktichluy,   "+   
						"\n		sum(total.ckdonhang) as ckdonhang,sum(total.ckbosung) as ckbosung,sum(total.ckkm) as ckkm   "+
						"\n		from    "+
						"\n		 		( "+
						"\n		 			select  dh.pk_seq as donhang_fk,   "+ 
						"\n		 				sum(dh_sp.thanhtoan   ) as cktichluy ,0 as ckdonhang,0 as ckbosung ,0 as ckkm,0 as doanhso   "+  
						"\n		 			from donhang dh  inner join  duyettrakhuyenmai_donhang  dh_sp on dh.pk_seq = dh_sp.donhang_fk      "+
						"\n		 			where 1=1 "+condition+"   "+
						"\n		 			group by	 dh.pk_seq		  							 "+
						"\n		 			union all    "+
						"\n		 			select  dh.pk_seq as donhang_fk,  0 as cktichluy ,0 as ckdonhang,   "+
						"\n		 				sum( dh_sp.thanhtien)  as ckbosung	,0 as ckkm,0 as doanhso      "+
						"\n		 			from donhang dh  inner join  donhang_chietkhaubosung  dh_sp on dh.pk_seq = dh_sp.donhang_fk   "+    
						"\n		 			where 1=1 "+condition+"  "+
						"\n		 			group by	 dh.pk_seq     "+
						"\n		 			union all      "+
						"\n		 			select  dh.pk_seq as donhang_fk,   0 as cktichluy,   "+
						"\n		 				sum(cast ((dh_sp.chietkhau*(1+dh_sp.thuevat/100)) as numeric(18,0))) as  ckdonhang ,0 as ckbosung ,   "+   
						"\n		 				0 as ckkm,   "+
						"\n		 				cast( sum( (dh_sp.soluong * dh_sp.giamua - dh_sp.chietkhau ) * ( 1 + dh_sp.thueVAT / 100 ) ) as numeric(18, 0) )   as doanhso   "+   
						"\n		 						 			from donhang dh  inner join donhang_sanpham  dh_sp on dh.pk_seq = dh_sp.donhang_fk    "+
						"\n		 			where   1=1  "+condition+"  "+
						"\n		 			group by  dh.pk_seq   								  "+
						"\n		 			union all   "+
						"\n		 			select  dh.pk_seq as donhang_fk, 0 as cktichluy,0 as   ckdonhang,0 as   ckbosung,        "+
						"\n		 				sum(dh_sp.tonggiatri) as ckkm , 0 as doanhso      "+
						"\n		 			from donhang dh  inner join donhang_ctkm_trakm  dh_sp on dh.pk_seq = dh_sp.donhangid   "+  
						"\n		 			where 1=1   "+condition+"  "+
						"\n		 			and dh_sp.spma is null    "+
						"\n		 			group by  dh.pk_seq   			   "+				
						"\n		 			)as total    "+
						"\n		 			group by donhang_fk    "+ 
						"\n		 		)as total inner join donhang dh on dh.pk_seq=total.donhang_fk   "+  
						"\n		 		inner join nhaphanphoi npp on npp.pk_seq=dh.npp_fk      "+
						"\n		 		inner join khuvuc kv on kv.pk_seq=npp.khuvuc_fk      "+
						"\n    			inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK"+
						"\n		 		inner join vung v on v.pk_seq=kv.vung_fk      "+
						"\n		 		inner join khachhang kh on kh.pk_seq = dh.khachhang_fk   "+ 
					 	"\n				left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)  "+
						"\n		  		inner join KENHBANHANG kbh on kbh.PK_SEQ=dh.KBH_FK     "+
						"\n		 		left join daidienkinhdoanh ddkd on ddkd.pk_seq = dh.ddkd_fk     "+
						"\n		 		left join giamsatbanhang gsbh on gsbh.pk_seq=dh.gsbh_fk    "+
						"\n  inner join KHO kho on kho.PK_SEQ=dh.KHO_FK  "+
						"\n  where 1=1 " + chuoi + condition2019 + "";
					 
				if(st.length()>0) { query+=  st; }
				query +=  	 
					 "\n union all  " + 
					 "\n select  dh.ghichu,kh.macu,nvgn.ten as tennvgn,	kbh.TEN as KenhBanHang, v.ten as vung,kv.ten as khuvuc,tt.ten as tinhthanh,  npp.ma as sitecode ,npp.ten as nhaphanphoi,dh.ngaytra as ngay,     " + 
					 "\n isnull(ddkd.mafast,'') as nvbhid,isnull(ddkd.ten,'') as nvbhten, kh.Mafast as makh, kh.ChuCuaHieu,kh.ten as tenkh,       " + 
					 "\n kh.diachi as diachi,kh.dienthoai,-1*dh.pk_seq as dhid         " + 
					 "\n ,'' as ngayhoadon,' ' as masp,' ' as tensanpham,        " + 
					 "\n 0 as soluong,0 as sothung,         " + 
					 "\n 0 as dongia,0 as chietkhau ,     " + 
					 "\n 0  as tongtien, 0 as tienkhuyenmai,' ' as scheme,    " + 
					 "\n dh.trangthai,0 dain,0 DaXuatHoaDon, 0 isdonhangle, 0 issampling,     " + 
					 "\n ' ' as chungloai, ' ' as nhomsanpham ,null  as  cttbid,0 as ispda  ,isnull(gsbh.mafast,'') as gsma,isnull(gsbh.ten,'') as gsten,    " + 
					 "\n kho.TEN   as khoten,kh.xuatkhau,kh.khongkyhopdong   ,total.doanhso as doanhso,     " + 
					 "\n total.ckbosung,total.ckdonhang,total.ckkm,total.cktichluy ,isnull(kh.thanhtoan,'0') as thanhtoan     " + 
					 "\n ,'' AS SoHoaDon ,'1' as loai, kh.DiaChiGiaoHang   " + 
					 "\n from      " + 
					 "\n (        " + 
					 "\n 	select total.donhang_fk,sum(total.doanhso) as doanhso,sum(total.cktichluy) as cktichluy,     " + 
					 "\n 	sum(total.ckdonhang) as ckdonhang,sum(total.ckbosung) as ckbosung,sum(total.ckkm) as ckkm     " + 
					 "\n 	from      " + 
					 "\n 	(   " + 
					 "\n 		select  dh.pk_seq as donhang_fk,   0 as cktichluy,     " + 
					 "\n 		0 as  ckdonhang ,0 as ckbosung ,     " + 
					 "\n 		0 as ckkm,     " + 
					 "\n 		-1*sum(dh_sp.soluong* dh_sp.dongia)  as doanhso     " + 
					 "\n 		from erp_hangtralainpp dh  inner join erp_hangtralainpp_sanpham  dh_sp on dh.pk_seq = dh_sp.hangtralai_fk      " + 
					 "\n 		where   1=1   "+conditionTVe+"   " + 
					 "\n 		group by  dh.pk_seq    " + 
					 "\n 	)as total " + 
					 "\n 	group by donhang_fk " + 
					 "\n ) as total inner join erp_hangtralainpp dh on dh.pk_seq=total.donhang_fk     " + 
					 "\n inner join nhaphanphoi npp on npp.pk_seq=dh.npp_fk        " + 
					 "\n inner join khuvuc kv on kv.pk_seq=npp.khuvuc_fk        " + 
					 "\n inner join tinhthanh tt on tt.PK_SEQ=npp.TINHTHANH_FK  " + 
					 "\n inner join vung v on v.pk_seq=kv.vung_fk        " + 
					 "\n inner join khachhang kh on kh.pk_seq = dh.khachhang_fk     " + 
					 "\n left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)     " + 
					 "\n inner join KENHBANHANG kbh on kbh.PK_SEQ=dh.KBH_FK       " + 
					 "\n left join daidienkinhdoanh ddkd on ddkd.pk_seq = dh.ddkd_fk     "+
					 "\n left join giamsatbanhang gsbh on gsbh.pk_seq=dh.gsbh_fk    "+
					 "\n inner join KHO kho on kho.PK_SEQ=dh.KHO_FK    " + 
					 "\n where 1=1 " + chuoi+ condition2019 + "";
						if(st.length()>0)
							query+=  st;
				  }
		}
		query+=
		"\n ) as data  order by makh,sitecode,dhId, scheme	";
		//System.out.println("____BC Don Hang Ban Trong Ky day nay: " + query);
		return query;
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
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
			
			IStockintransit obj = new Stockintransit();

		    Utility util = new Utility();
		    String querystring = request.getQueryString();
		    String userId = util.getUserId(querystring);
		    
		    String view = request.getParameter("view");
		    if(view == null)
		    	view = "";
		    
		    obj.setuserId(userId);
		    
		    String nppId ="";
				if(view.equals("TT"))
				{
					 nppId = util.antiSQLInspection(request.getParameter("nppId"));
					if (nppId == null)
							nppId = "";
						obj.setnppId(nppId);
				}else
				{
					nppId=util.getIdNhapp(userId);
					obj.setnppId(nppId);
				}
		    
		    obj.init();	    
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			
			if(!view.equals("TT"))
			{
				String nextJSP = request.getContextPath() + "/pages/Distributor/BCDonHangBanTrongKy.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				String nextJSP = request.getContextPath() + "/pages/Center/BCDonHangBanTrongKyNPP.jsp";
				response.sendRedirect(nextJSP);
			}
	}
	private String setQueryKoPivot(HttpServletRequest request,IStockintransit obj)
	{

		
		String conditiondh= "";
		String conditionth= "";
		Utility util = new Utility();
		
		
		
		if (obj.getnppId().length() > 0)
		{
			conditiondh += " and dh.Npp_FK ='" + obj.getnppId() + "'";
			conditionth += " and th.Npp_FK ='" + obj.getnppId() + "'";
		}
		else
		{
			conditiondh += " and  dh.NPP_FK in " + util .quyen_npp(obj.getuserId()) ;
			conditionth += " and th.Npp_FK in " + util .quyen_npp(obj.getuserId()) ;
			
		}
		
		if (obj.getkhuvucId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk ='" + obj.getkhuvucId() + "'";
			conditionth += " and npp.khuvuc_fk ='" + obj.getkhuvucId() + "'";
			
		}
		if (obj.getvungId().length() > 0)
		{
			conditiondh += " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk =" + obj.getvungId() + ")  ";
			conditionth += " and npp.khuvuc_fk  in (select pk_seq from khuvuc where vung_fk =" + obj.getvungId() + ")  ";
			
		}

		if(obj.getTrangthai().length()>0)
		{
			conditiondh += " and ddh.trangthai ='" + obj.getTrangthai() + "'";
			conditionth += " and th.trangthai ='" + obj.getTrangthai() + "'";
		}		
		
		
		
		String condition2019 = "";
		if(obj.getTinhthanhid().length()>0) { condition2019 += " and exists ( select 1 from tinhthanh where pk_seq = kh.tinhthanh_fk and pk_seq = '"+ obj.getTinhthanhid() +"' ) "; }
		if(obj.getQhId().length()>0) 		{ condition2019 += " and exists ( select 1 from quanhuyen where pk_seq = kh.quanhuyen_fk and pk_seq = '"+ obj.getQhId() +"' ) "; }
		if(obj.getPhuongxa().length()>0) 	{ condition2019 += " and exists ( select 1 from phuongxa where pk_seq = kh.phuongxa and pk_seq = '"+ obj.getPhuongxa() +"' ) "; }
		
		String query =
			"select dh.NGAYNHAP [Ngày],kbh.ten [[KÊNH], V.DIENGIAI [VÙNG], KV.TEN [KHU VỰC], TT.TEN [TỈNH THÀNH], npp.mafast [MÃ NPP], NPP.TEN [TÊN NPP], GS.maFAST [MÃ GSBH], GS.TEN [TÊN GSBH]\r\n" + 
			"	, DDKD.maFAST [MÃ NVBH], DDKD.TEN [TÊN NVBH],DH.PK_SEQ [MÃ ĐƠN HÀNG], isnull(convert(varchar,dh.tdchot,120),'') [TĐ CHỐT],HD.SOHOADON [SỐ HÓA ĐƠN],HD.NGAYXUATHD [NGÀY HÓA ĐƠN]\r\n" + 
			"	, case when dh.trangthai = 1 then N'Hoàn tất' when dh.trangthai = 0 and dh.synced = 1 then N'Đã đồng bộ' when dh.TRANGTHAI = 0 then N'Chưa chốt' when dh.TRANGTHAI = 2 then N'Đã hủy' else '' end [TRANG THÁI]\r\n" + 
			"	, kh.maFAST [MÃ KH], KH.TEN [TÊN KH], KH.DIENTHOAI [ĐIỆN THOẠI], LCH.DIENGIAI [LOẠI KHÁCH HÀNG], KH.tencuahieu [CHỦ CỬA HIỆU]\r\n" + 
			"	,KH.DIACHI [ĐỊA CHỈ XHĐ], KH.DiaChiGiaoHang [ĐỊA CHỈ GIAO HÀNG]\r\n" + 
			"	,sp.MA [MÃ SẢN PHẨM],sp.TEN [TÊN SẢN PHẨM], CL.TEN [CHỦNG LOẠI], NHOM.Ten [NHÓM SẢN PHẨM], SP.pt_VAT [% THUẾ], ISNULL(DHSP.chietkhau,0) [% CHIẾT KHẤU] , DHSP.SOLUONG [SỐ LƯỢNG],DHSP.GIAMUA [ĐƠN GIÁ],DHSP.dongiaGoc [GIÁ CHƯA GIẢM],DHSP.dongiaGoc * ( 1+ DHSP.THUEVAT/100.0) [GIÁ CHƯA GIẢM + VAT], CTKM.SCHEME [CTKM]\r\n" + 
			"	,  DHSP.SOLUONG * DHSP.GIAMUA [TỔNG TIỀN],  DHSP.SOLUONG * DHSP.dongiaGoc [THÀNH TIỀN CHƯA GIẢM],  DHSP.SOLUONG * DHSP.dongiaGoc * ( 1+ DHSP.THUEVAT/100.0) [THÀNH TIỀN ĐƠN CHƯA GIẢM + VAT], DHSP.tienkhuyenmai [CHIẾT KHẤU/KHUYẾN MÃI], DHSP.SOLUONG * DHSP.GIAMUA   - ISNULL(DHSP.tienkhuyenmai ,0) [THU VỀ], DHSP.SOLUONG * DHSP.GIAMUA * ( 1+ DHSP.THUEVAT/100.0)   - ISNULL(DHSP.tienkhuyenmai ,0) [THU VỀ + VAT],DH.GHICHU [GHI CHÚ]\r\n" + 
			"from donhang dh\r\n" + 
			"OUTER APPLY\r\n" + 
			"(\r\n" + 
			"	SELECT TOP 1 HD.SOHOADON,HD.NGAYXUATHD\r\n" + 
			"	FROM HOADON HD WHERE HD.TRANGTHAI IN (2,4) AND HD.DONDATHANG_FK = DH.PK_SEQ AND HD.tongtienavat > 0\r\n" + 
			"	ORDER BY HD.PK_SEQ DESC\r\n" + 
			")HD\r\n" + 
			"OUTER APPLY\r\n" + 
			"(\r\n" + 
			"	SELECT SANPHAM_fK ,SOLUONG,GIAMUA,dongiaGoc,tienkhuyenmai , 0 CTKMID,THUEVAT,chietkhau FROM DONHANG_SANPHAM DHSP WHERE DONHANG_fk = DH.PK_SEQ UNION ALL\r\n" + 
			"	SELECT SANPHAM_fK ,SOLUONG,0,0,0,CTKMID,0,0 FROM DONHANG_CTKM_TRAKM DHSP WHERE DONHANGID = DH.PK_SEQ  AND sanpham_fk IS NOT NULL\r\n" + 
			")DHSP\r\n" + 
			"INNER JOIN SANPHAM SP ON SP.PK_SEQ =DHSP.SANPHAM_FK\r\n" + 
			"LEFT JOIN CHUNGLOAI CL ON CL.PK_SEQ = SP.CHUNGLOAI_FK\r\n" + 
			"LEFT JOIN CTKHUYENMAI CTKM ON CTKM.PK_SEQ = DHSP.CTKMID\r\n" + 
			"LEFT JOIN NhomHang NHOM ON NHOM.PK_SEQ = (SELECT TOP 1 NhomHang_FK FROM NHOMHANG_SANPHAM X WHERE X.SANPHAM_FK = SP.PK_SEQ)\r\n" + 
			"inner join KENHBANHANG kbh on kbh.PK_SEQ = dh.KBH_FK\r\n" + 
			"INNER JOIN NHAPHANPHOI NPP ON NPP.PK_SEQ = DH.NPP_FK\r\n" + 
			"INNER JOIN KHUVUC KV ON KV.PK_SEQ = NPP.KHUVUC_FK\r\n" + 
			"INNER JOIN VUNG V ON V.PK_SEQ = KV.VUNG_FK\r\n" + 
			"INNER JOIN KHACHHANG KH ON KH.PK_SEQ = DH.KHACHHANG_FK\r\n" + 
			"INNER JOIN TINHTHANH TT ON TT.PK_SEQ = KH.TINHTHANH_FK\r\n" + 
			"INNER JOIN GIAMSATBANHANG GS ON GS.PK_SEQ = DH.GSBH_FK\r\n" + 
			"INNER JOIN DAIDIENKINHDOANH DDKD ON DDKD.PK_SEQ = DH.DDKD_FK\r\n" + 
			"INNER JOIN LOAICUAHANG LCH ON LCH.PK_SEQ =KH.LCH_FK\r\n" + 
			"WHERE DH.TRANGTHAI <> 2 and dh.ngaynhap >='"+obj.gettungay()+"' and dh.ngaynhap <= '"+obj.getdenngay()+"' \r\n" +  
			conditiondh + "\r\n " +
			"order by dh.PK_SEQ";
		System.out.println("BC Don Dat Hang NPP : " + query);
		return query;
	}
	
	
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
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
		
		IStockintransit obj = new Stockintransit();
		Utility  util = new Utility();
		
		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			userId = "";
		obj.setuserId(userId);
		
		String userTen = (String) session.getAttribute("userTen");
		obj.setuserTen(userTen);
		
		String view=request.getParameter("view");
		if(view == null)
			view = "";
		
		String nppId ="";
		if(view.equals("TT"))
		{
			 nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";
			obj.setnppId(nppId);
		}else
		{
			
			nppId=util.getIdNhapp(userId);
		}
		
		obj.setnppId(nppId);
		
		
		String ddkdId = util.antiSQLInspection(request.getParameter("ddkdId"));
		if (ddkdId == null)
			ddkdId = "";
		obj.setDdkd(ddkdId);
		
		String nhomId = util.antiSQLInspection(request.getParameter("nhomId"));
		if (nhomId == null)
			nhomId = "";
		obj.setNhomhangid(nhomId);

	
		String tungay = util.antiSQLInspection(request.getParameter("Sdays"));
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("Edays"));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		
	

		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId"))!=null?
				util.antiSQLInspection(request.getParameter("vungId")):"");
			
		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId"))!=null?
				util.antiSQLInspection(request.getParameter("khuvucId")):"");
		
		
		/*obj.setTtId(util.antiSQLInspection(request.getParameter("ttId"))!=null?
				util.antiSQLInspection(request.getParameter("ttId")):"");*/
		
		obj.setTinhthanhid(util.antiSQLInspection(request.getParameter("tpId"))!=null?util.antiSQLInspection(request.getParameter("tpId")):"");
		obj.setQhId(util.antiSQLInspection(request.getParameter("qhId"))!=null?util.antiSQLInspection(request.getParameter("qhId")):"");
		obj.setPhuongxa(util.antiSQLInspection(request.getParameter("phuongxaId"))!=null?util.antiSQLInspection(request.getParameter("phuongxaId")):"");
		
		obj.settype(util.antiSQLInspection(request.getParameter("type")) != null ? util.antiSQLInspection(request.getParameter("type")) : "");
		
		obj.setMucCN_DT(util.antiSQLInspection(request.getParameter("cndt")) != null ? util.antiSQLInspection(request.getParameter("cndt")) : "");
		
		obj.setMuc_KhachHang(util.antiSQLInspection(request.getParameter("kh")) != null ? util.antiSQLInspection(request.getParameter("kh")) : "");
		
		obj.setTrangthai(util.antiSQLInspection(request.getParameter("trangthai")) != null ? util.antiSQLInspection(request.getParameter("trangthai")) : "");
		
		obj.setkhoId(util.antiSQLInspection(request.getParameter("khoId")) != null ? util.antiSQLInspection(request.getParameter("khoId")) : "");
		
		System.out.println("::::"+request.getParameter("type"));
		String st = "";
		
		if(nppId.length() > 0)
			st += " and dh.npp_fk ='" + nppId + "'";
		if (ddkdId.length() > 0)
			st += " and dh.ddkd_fk ='" + ddkdId + "'";
		if(obj.getkhuvucId().length()>0){
			
		}
		if(view.equals("TT"))
		{
			st += " and dh.npp_fk in " + util .quyen_npp(obj.getuserId()) + " and dh.kbh_fk in  " + util.quyen_kenh(obj.getuserId());
		}
	
		String action = request.getParameter("action");
		if(action.equals("kopivot"))
		{
			
			String query = setQueryKoPivot(request,obj);
			try
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCDonhangBanTrongKyKoPivot.xlsm");
				OutputStream out = response.getOutputStream();
				
				
				ExportToExcelAuto(out, obj, query);
			} catch (Exception e)
			{
				obj.setMsg("Khong the tao bao cao " + e.getMessage());
			}
			
			return;
		}
		else
		if(action.equals("etc"))
		{
			
			String query = setQueryETC(request,obj);
			try
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCDonDatHangNPP_" + util.setTieuDe(obj) + ".xlsm");
				OutputStream out = response.getOutputStream();
				
				
				ExportToExcel(out, obj, query);
			} catch (Exception e)
			{
				obj.setMsg("Khong the tao bao cao " + e.getMessage());
			}
			
			return;
		}
		else
		if (action.equals("tao")) 
		{
			try 
			{
				System.out.println(" my contentType:" +  response.getContentType());
				
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				System.out.println(" my contentType 2 :" +  response.getContentType());
				if(view.equals("TT")){
					
					response.setHeader("Content-Disposition", "attachment; filename=BCDonHangBanTrongKyNPP"+util.setTieuDe(obj)+".xlsm");
				}else{ 
					
					response.setHeader("Content-Disposition", "attachment; filename=BCDonHangBanTrongKyNPP_"+getPiVotName()+".xlsm");
				}
				OutputStream out = response.getOutputStream();

				if(obj.gettype().equals("0"))
				{	
					System.out.println("co vao day hay khong");
					response.setHeader("Content-Disposition", "attachment; filename=BCDonHangBanTrongKyNPP"+util.setTieuDe(obj)+".xlsm");
					String query = setQuery(request, tungay, denngay, st,obj);
					CreatePivotTable(out, obj, query);
				
				}else
				{
					String query = setQuery_HoaDon(request,obj);
					CreatePivotTable_HoaDon(out, obj, query);
				}
			} 
			catch (Exception ex) 
			{
				System.out.println("aaaaaaaaaa:"+ex.getMessage());
				
				response.setContentType("text/html; charset=UTF-8");
				obj.init();	    
				obj.setMsg(ex.getMessage());
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				session.setAttribute("errors","Lỗi"+ ex.getMessage());
				if(!view.equals("TT"))
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/BCDonHangBanTrongKy.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					String nextJSP = request.getContextPath() + "/pages/Center/BCDonHangBanTrongKyNPP.jsp";
					response.sendRedirect(nextJSP);
				}
				
			}
		}else{
				obj.init();	    
				session.setAttribute("obj", obj);
				session.setAttribute("userId", userId);
				
				if(!view.equals("TT"))
				{
					String nextJSP = request.getContextPath() + "/pages/Distributor/BCDonHangBanTrongKy.jsp";
					response.sendRedirect(nextJSP);
				}
				else
				{
					String nextJSP = request.getContextPath() + "/pages/Center/BCDonHangBanTrongKyNPP.jsp";
					response.sendRedirect(nextJSP);
				}
		}
		
	}

	private void ExportToExcelAuto(OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 			

			Workbook workbook = new Workbook();

			
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			TaoBaoCaoAuto(workbook, obj, query);
			
			workbook.save(out);
			
						

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	
	private void TaoBaoCaoAuto(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
	
		try{

			

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo Đơn hàng bán tròng kỳ");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());

			
			
			ResultSet	rs = obj.getDb().get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			
			int location  = 0;
			int row = 8;
			cells.setRowHeight(row, 23.0f);
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i));
				cells.setColumnWidth(location + i-1, 23.0f);	
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				 
				for(int i =1;i <=socottrongSql ; i ++)
				{
					
					cell = cells.getCell(row,location + i-1 );
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
					cells.setColumnWidth(location + i-1, 23.0f);	
					
				}
				cells.setRowHeight(row, 23.0f);
				++row;
			}
			if(rs!=null)rs.close();
			
			
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
		
	}
	
	
	private void ExportToExcel(OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 			

			FileInputStream fstream = null;
			Workbook workbook = new Workbook();

			fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonHangETC.xlsm");
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);

			TaoBaoCao(workbook, obj, query);
			
			workbook.save(out);
			fstream.close();
						

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}

	}
	
	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{
		dbutils db = new dbutils();
		try{

			

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			Cell cell = cells.getCell("A1");;	
		   
			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Báo Cáo Đơn hàng ETC");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
			cell = cells.getCell("A3");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + this.getDateTime());
			cell = cells.getCell("A4");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Người tạo : " + obj.getuserTen());

			
			
			ResultSet	rs = db.get(query);

			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			
			int location  = 200;
			int row = 0;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i));
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}
			
			row ++;
			while(rs.next())
			{
				
				for(int i =1;i <=socottrongSql ; i ++)
				{
					
					cell = cells.getCell(row,location + i-1 );
					
					if(!rsmd.getColumnName(i).contains("Ma") && rsmd.getColumnType(i) == Types.DOUBLE || rsmd.getColumnType(i) == Types.INTEGER || rsmd.getColumnType(i) == Types.DECIMAL )
					{
						int format = 43;
							if(rsmd.getColumnName(i).contains("Tỷ lệ"))	
								format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}
				
				++row;
			}
			if(rs!=null)rs.close();
			
			
			
		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("Lỗi ! Không có dữ liệu để xuất file !");
		}
		db.shutDown();
	}
	
	private String setQuery_HoaDon(HttpServletRequest request, IStockintransit obj)
  {
		String condition ="";
		
		if(obj.gettungay().length()>0)
		{
			condition+=" and hd.NgayXuatHD>='"+obj.gettungay()+"'";
		}
		
		if(obj.getdenngay().length()>0)
		{
			condition+=" and hd.NgayXuatHD <='"+obj.getdenngay()+"'";
		}
		
		if(obj.getnppId().length()>0)
		{
			condition+=" and hd.npp_fk ='"+obj.getnppId()+"'";
		}
		
		if(obj.getTrangthai().length()>0)
		{
			condition+=" and hd.trangthai='"+obj.getTrangthai()+"'";
		}else 
		{
			condition+=" and hd.trangthai not in (1,3,5) ";
		}
		
		if(obj.getTtId().length()>0)
		{
			condition += " and tt.PK_SEQ ="+obj.getTtId();
		}
		
		if(obj.getDdkd().length()>0)
		{
			condition+=
					" and hd.pk_seq in "+
				  "   (		select  aa.hoadon_fk   \n "+      
				  "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
				  "   		where bb.ddkd_fk='"+obj.getDdkd()+"' )  \n ";
					
		}
		
		if(obj.getkhoId().length()>0)
		{
			condition += 
					" and hd.pk_seq in 	           "+
					"   (		select  aa.hoadon_fk   \n "+      
					"   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
					"   		where bb.kho_fk='"+obj.getkhoId()+"' )  \n ";
		}
		String condionO="";
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condionO+=" and hdsp.sanpham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+obj.getNhomhangid()+"' ) ";
			
		}
		String condionkm="";
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condionkm+=" and hdsp.sanpham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+obj.getNhomhangid()+"' ) ";
			
		}
		String condionE="";
		if(obj.getNhomhangid().length()>0)
		{
			//condition+= " and  dh_sp.sanpham_fk <= '"+obj.getdenngay()+"' ";
			condionE+=" and c.sanpham_fk in  (select SANPHAM_FK FROM NHOMHANG_SANPHAM  where NhomHang_fk='"+obj.getNhomhangid()+"' ) ";
			
		}
		
		String condition_ETC ="";
		if(obj.gettungay().length()>0)
		{
			condition_ETC+=" and A.NgayXuatHD>='"+obj.gettungay()+"'";
		}
		
		if(obj.getdenngay().length()>0)
		{
			condition_ETC+=" and A.NgayXuatHD <='"+obj.getdenngay()+"'";
		}
		
		if(obj.getnppId().length()>0)
		{
			condition_ETC+=" and A.npp_fk ='"+obj.getnppId()+"'";
		}
		
		if(obj.getTrangthai().length()>0)
		{
			condition_ETC +=" and A.trangthai='"+obj.getTrangthai()+"'";
		}else 
		{
			condition_ETC +=" and A.trangthai not in (1,3,5) ";
		}
		
		if(obj.getTtId().length()>0)
		{
			condition_ETC += " and tt.PK_SEQ ="+obj.getTtId();
		}
		
		if(obj.getDdkd().length()>0)
		{
			condition_ETC +=
				  " and A.pk_seq in "+
				  " ( select  aa.hoadonnpp_fk   \n "+      
				  "   from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
				  "   where bb.ddkd_fk='"+obj.getDdkd()+"' )  \n ";
		}
		
		if(obj.getkhoId().length()>0)
		{
			condition_ETC += 
			   " and A.pk_seq in 	(           "+
			   "  		select  aa.hoadonnpp_fk           "+ 
			   "  		from ERP_HOADONNPP_DDH aa inner join ERP_DONDATHANGNPP bb on bb.PK_SEQ=aa.DDH_FK "+          
			   "   		where bb.kho_fk='"+obj.getkhoId()+"'   \n "+
			   "  	)  ";
		}
		
		String conditionHDKhac ="";
		if(obj.gettungay().length()>0)
		{
			conditionHDKhac+=" and a.ngayhoadon>='"+obj.gettungay()+"'";
		}
		
		if(obj.getdenngay().length()>0)
		{
			conditionHDKhac+=" and a.ngayhoadon <='"+obj.getdenngay()+"'";
		}
		
		if(obj.getnppId().length()>0)
		{
			conditionHDKhac+=" and a.npp_fk ='"+obj.getnppId()+"'";
		}
		
		if(obj.getTrangthai().length()>0)
		{
			if(obj.getTrangthai().equals("1")) //chờ xác nhận
				conditionHDKhac +=" and a.trangthai = 0 ";
			if(obj.getTrangthai().equals("2")||obj.getTrangthai().equals("4")) //đã xác nhận
				conditionHDKhac +=" and a.trangthai = 1 ";
			if(obj.getTrangthai().equals("3")||obj.getTrangthai().equals("5")) //đã xóa || đã hủy
				conditionHDKhac +=" and a.trangthai = 2 ";
		}
		else 
		{
			conditionHDKhac +=" and a.trangthai = 1 ";
		}
		
		if(obj.getTtId().length()>0)
		{
			conditionHDKhac += " and tt.PK_SEQ ="+obj.getTtId();
		}
		
		String condition2019 = "";
		if(obj.getTinhthanhid().length()>0) { condition2019 += " and exists ( select 1 from tinhthanh where pk_seq = kh.tinhthanh_fk and pk_seq = '"+ obj.getTinhthanhid() +"' ) "; }
		if(obj.getQhId().length()>0) 		{ condition2019 += " and exists ( select 1 from quanhuyen where pk_seq = kh.quanhuyen_fk and pk_seq = '"+ obj.getQhId() +"' ) "; }
		if(obj.getPhuongxa().length()>0) 	{ condition2019 += " and exists ( select 1 from phuongxa where pk_seq = kh.phuongxa and pk_seq = '"+ obj.getPhuongxa() +"' ) "; }
		
		String query="";
		String queryHdKhac = "";
		if(obj.getMuc_KhachHang().equals("1"))
		{
			query=
			   " select  dh.ghichu,kh.macu,nvgn.ten as tennvgn,HD.TRANGTHAI,'OTC' as HD,   \n "+      
			   "   	hdsp.HOADON_FK,hd.NGAYXUATHD,hd.SOHOADON,hd.KYHIEU,npp.MA as nppMa,npp.TEN as nppTen,kh.maFAST as khMA,   \n "+      
			   "   	KH.TEN as khTEN,kh.CHUCUAHIEU,kh.DIACHI as khDiaChi,kh.MASOTHUE as khMST,sp.MA as spMa,sp.TEN as spTEN,   \n "+      
			   "   	hdsp.donvitinh as spDonVi,hdsp.SOLUONG,hdsp.DONGIA ,(ROUND( hdsp.soLuong*hdsp.DONGIA,0)) as ThanhTien,hdsp.VAT as VAT   \n "+      
			   "   	,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as ddkdTEN,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join LOAIKHACHHANG cc on cc.PK_SEQ=bb.LOAIKHACHHANG   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as LoaiKH,0 as LoaiHD,   \n "+
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as KHO "+
			   "   from HOADON hd inner join HOADON_SP hdsp on hdsp.HOADON_FK=hd.PK_SEQ   \n "+      
			   "   	inner join NHAPHANPHOI npp on npp.PK_SEQ=hd.NPP_FK   \n "+ 
			   "   	inner join KHACHHANG kh on kh.PK_SEQ=hd.KHACHHANG_FK   \n "+      
			   "    LEFT join tinhthanh tt on tt.PK_SEQ=kh.TINHTHANH_FK"+ 
			   "	left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)    \n "+ 
			   "   	inner join SANPHAM sp on sp.PK_SEQ=hdsp.SANPHAM_FK   \n "+      
			   "   where hd.LOAIHOADON=0  "+condition+condionO+ condition2019 + "	   \n "+
			   "   union all   \n "+      
			   "      \n "+      
			   "   select  dh.ghichu,kh.macu,nvgn.ten as tennvgn,HD.TRANGTHAI, 'OTC' as HD,   \n "+      
			   "   	hdsp.hoadon_fk,hd.NGAYXUATHD,hd.SOHOADON,hd.KYHIEU,npp.MA as nppMa,npp.TEN as nppTen,kh.maFAST as khMA,   \n "+      
			   "   	KH.TEN as khTEN,kh.CHUCUAHIEU,kh.DIACHI as khDiaChi,kh.MASOTHUE as khMST,sp.MA as spMa,sp.TEN as spTEN,   \n "+      
			   "   		'' as spDonVi,0 as SoLuong,0 as DonGia ,(-1)*ROUND(hdsp.chietkhau,0) as ThanhTien ,hdsp.thueVAT as VAT,   \n "+      
			   "   		(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as ddkdTEN,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join LOAIKHACHHANG cc on cc.PK_SEQ=bb.LOAIKHACHHANG   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as LoaiKH,0 as LoaiHD ,  \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as KHO "+
			   "   from HOADON hd inner join HOADON_CHIETKHAU hdsp on hdsp.HOADON_FK=hd.PK_SEQ   \n "+      
			   "   	inner join NHAPHANPHOI npp on npp.PK_SEQ=hd.NPP_FK   \n "+   
			   "   	inner join KHACHHANG kh on kh.PK_SEQ=hd.KHACHHANG_FK   \n "+ 
			   "   inner join tinhthanh tt on tt.PK_SEQ=kh.TINHTHANH_FK"+
			   "	left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)    \n "+ 
			   "   	inner join loaick sp on sp.Ma=hdsp.diengiai   \n "+      
			   "   where hdsp.hienthi = '1' and hd.LOAIHOADON=0  "+condition+ condition2019 +"	   \n "+ 
			   "      \n "+      
			   "   union all    \n "+      
			   "   select  dh.ghichu,kh.macu,nvgn.ten as tennvgn,HD.TRANGTHAI, 'OTC' as HD,   \n "+      
			   "   	hdsp.HOADON_FK,hd.NGAYXUATHD,hd.SOHOADON,hd.KYHIEU,npp.MA as nppMa,npp.TEN as nppTen,kh.maFAST as khMA,   \n "+      
			   "   	KH.TEN as khTEN,kh.CHUCUAHIEU,kh.DIACHI as khDiaChi,kh.MASOTHUE as khMST,sp.MA as spMa,sp.TEN as spTEN,   \n "+      
			   "   	dvdl.DONVI as spDonVi,hdsp.SOLUONG,case when len(dbo.Trim(hdsp.CTKM))>0 and hdsp.CTKM is not null then 0 else hdsp.DONGIA  end DONGIA,ROUND(hdsp.soLuong*(case when len(dbo.Trim(hdsp.CTKM))>0 and hdsp.CTKM is not null then 0 else hdsp.DONGIA  end),0) as ThanhTien,hdsp.VAT as VAT,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join DAIDIENKINHDOANH cc on cc.PK_SEQ=bb.DDKD_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as ddkdTEN,   \n "+      
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join LOAIKHACHHANG cc on cc.PK_SEQ=bb.LOAIKHACHHANG   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as LoaiKH,1 as LoaiHD,   \n "+     
			   "   	(   \n "+      
			   "   		select top(1) cc.TEN   \n "+      
			   "   		from HOADON_DDH aa inner join DONHANG bb on bb.PK_SEQ=aa.DDH_FK   \n "+      
			   "   			inner join KHO cc on cc.PK_SEQ=bb.KHO_FK   \n "+      
			   "   		where aa.HOADON_FK=HD.pk_Seq   \n "+      
			   "   	)as KHO "+
			   "   from HOADON hd inner join HOADON_CTKM_TRAKM hdsp on hdsp.HOADON_FK=hd.PK_SEQ   \n "+      
			   "   	inner join NHAPHANPHOI npp on npp.PK_SEQ=hd.NPP_FK   \n "+ 
			   "   	inner join KHACHHANG kh on kh.PK_SEQ=hd.KHACHHANG_FK   \n "+  
			   "   	inner join tinhthanh tt on tt.PK_SEQ=kh.TINHTHANH_FK"+
			   "	left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)    \n "+ 
			   "   	inner join SANPHAM sp on sp.PK_SEQ=hdsp.SANPHAM_FK   \n "+      
			   "   	inner join DONVIDOLUONG dvdl on dvdl.PK_SEQ=sp.DVDL_FK   \n "+      
			   "   where hd.LOAIHOADON=1  "+condition+condionkm+ condition2019 + "  \n "+
			   "      \n ";    
				if(obj.getDdkd().length()<=0&&obj.getkhoId().length()<=0){ //HÓA ĐƠN KHÁC K LƯU KHO + DDKD
					queryHdKhac=
						" union all \n"+
						" select dh.ghichu,kh.macu,nvgn.ten as tennvgn,a.trangthai, 'HĐ Khac', a.pk_seq, a.ngayhoadon ngayxuatHD, a.sohoadon, a.kyhieuhoadon kyhieu, npp.MA nppMa, npp.TEN nppTen, kh.maFAST khMA, \n"+
					    "    	 kh.TEN as khTen, kh.CHUCUAHIEU, kh.DIACHI, kh.MASOTHUE khMST, b.diengiai spMa, b.diengiai spTen, b.donvitinh spdonvi, b.soluong, b.dongia, (b.soluong * b.dongia) thanhtien, b.Thuevat vat, '' ddkdTen, loai.ten loaiKH, 0 LOAIHD, '' KHO \n"+
					    " from 	 ERP_HoaDonPheLieu a inner join erp_hoadonphelieu_sanpham b on a.pk_seq = b.hoadonphelieu_fk \n"+
					    "  		 inner join KHACHHANG kh on a.khachhang_fk = kh.PK_SEQ \n"+
					   
					   "		left join NHANVIENGIAONHAN nvgn on nvgn.PK_SEQ in ( select top 1 nvgn_fk from nvgn_kh where khachhang_fk = kh.pk_seq)    \n "+ 
					    "  		 inner join LOAIKHACHHANG loai on kh.XuatKhau = loai.pk_seq \n"+
					    "  		 inner join NHAPHANPHOI npp on a.npp_fk = npp.PK_SEQ \n"+
					    "        inner join TINHTHANH tt on tt.PK_SEQ = kh.TINHTHANH_FK \n"+ 
					    " where 1=1  "+conditionHDKhac + condition2019 + " \n";
				}
				query = query + queryHdKhac;
		}
		if(obj.getMuc_KhachHang().equals("1")&&obj.getMucCN_DT().equals("1"))
		System.out.println("__HD_Ban_Trong_Ky__"+query);
	  return query;
  }

	private void CreatePivotTable(OutputStream out,IStockintransit obj, String query) throws Exception 
	{
		try 
		{
			FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BCDonHangBanTrongKy.xlsm");
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			CreateHeader(workbook,obj); 
			FillData(workbook, query, obj);			
			workbook.save(out);
			fstream.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	
	private void CreatePivotTable_HoaDon(OutputStream out,IStockintransit obj, String query) throws Exception
	{
		try 
		{
			FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\BcHoaDonBanTrongKy.xlsm");
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			CreateHeader_HoaDon(workbook,obj); 
			FillData_HoaDon(workbook, query, obj);
				
			workbook.save(out);
			fstream.close();
		} 
		catch (Exception ex) {
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	
	private void FillData(Workbook workbook, String query, IStockintransit obj) throws Exception
	{
		System.out.println("cau qery" + query);
		dbutils db=new dbutils();
		Hashtable<String, String> htbETC =gethash_ETC(obj, db);
		Hashtable<String, String> htbOTC =gethash_OTC(obj, db);
		
		ResultSet rs = null;
		try
		{	
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();
			
			for(int i=0;i<15;++i)
			{
		    	cells.setColumnWidth(i, 15.0f);	    	
		    }	
			 rs = db.get(query);
			int index = 2;
		    Cell cell = null;
		    String dh_bk="";
		    String kh_bk = "";
		    String dhid="";
		    String khid = "";
		    
		    boolean coData= false;
		    if(rs!=null)
		    {
		    	System.out.println("vao xuat excell");
			while (rs.next())
			{
				
				coData = true;
			
				dhid=rs.getString("dhid");
				khid = rs.getString("makh").trim()+"_"+rs.getString("Sitecode").trim();

				cell = cells.getCell("FA" + String.valueOf(index));		cell.setValue(rs.getString("ngay"));			
				cell = cells.getCell("FB" + String.valueOf(index));		cell.setValue(rs.getString("nvbhTen"));
				cell = cells.getCell("FC" + String.valueOf(index));		cell.setValue(rs.getString("tenkh"));
				cell = cells.getCell("FD" + String.valueOf(index));		cell.setValue(rs.getString("dhId"));
				String ngayhd=rs.getString("ngayhoadon");
				if( htbOTC.get(rs.getString("dhid"))!=null &&  rs.getString("loai").equals("1") )
				{
			//		System.out.println("gia tri"+rs.getString("dhid"));
				//	System.out.println("gia tri"+htbOTC.get(rs.getString("dhid")));
					String arrtemp[]=htbOTC.get(rs.getString("dhid")).split(",");
					for(int i=0;i<arrtemp.length;i++)
					{
						String[] tem=arrtemp[i].split("__");
						ngayhd+= tem[1] +" ";
					}
				}
				cell = cells.getCell("FE" + String.valueOf(index));		cell.setValue(ngayhd);  
				cell = cells.getCell("FF" + String.valueOf(index));		cell.setValue(rs.getString("tensanpham"));
				cell = cells.getCell("FG" + String.valueOf(index));		cell.setValue(rs.getString("scheme"));
				cell = cells.getCell("FH" + String.valueOf(index));		cell.setValue(rs.getString("makh"));
				cell = cells.getCell("FI" + String.valueOf(index));		cell.setValue(rs.getString("masp"));
				cell = cells.getCell("FJ" + String.valueOf(index));		cell.setValue(rs.getString("diachi"));
				cell = cells.getCell("FK" + String.valueOf(index));		cell.setValue(rs.getFloat("chietkhau"));
				cell = cells.getCell("FL" + String.valueOf(index));		cell.setValue(rs.getFloat("soluong"));
				cell = cells.getCell("FM" + String.valueOf(index));		cell.setValue(rs.getFloat("dongia"));
				cell = cells.getCell("FN" + String.valueOf(index));		cell.setValue(rs.getFloat("tongtien"));
				
				String trangthai="";
				
				if(rs.getString("KenhBanHang").equals("OTC"))
				{
				
					if(rs.getInt("trangthai")==0)
					{
						trangthai="Chưa Chốt";
					}else if(rs.getInt("trangthai")==1)
					{
						trangthai="Đã Chốt";
					}else if(rs.getInt("TrangThai")==3)
					{
						trangthai="Đã Xuất Kho";
					}
					if(rs.getInt("DaIn")==1)
					{
						trangthai="Đã In";
					}
					if(rs.getInt("DaXuatHoaDon")==1)
					{
						trangthai="Đã Xuất HĐ";
					}
				}else 
				{
					if(rs.getInt("trangthai")==0)
					{
						trangthai="Chưa Chốt";
					}else if(rs.getInt("trangthai")==1)
					{
						trangthai="Chờ Duyệt ";
					}else if(rs.getInt("TrangThai")==2)
					{
						trangthai="Đã Duyệt";
					}
					else if(rs.getInt("TrangThai")==3)
					{
						trangthai="Đã Hủy";
					}
					else if(rs.getInt("TrangThai")==4)
					{
						trangthai="Hoàn Tất";
					}
				}
				cell = cells.getCell("FO" + String.valueOf(index));		cell.setValue(trangthai);
				
				
				
				cell = cells.getCell("FP" + String.valueOf(index));		cell.setValue(rs.getFloat("sothung"));
				cell = cells.getCell("FQ" + String.valueOf(index));		cell.setValue(rs.getString("Sitecode"));
				cell = cells.getCell("FR" + String.valueOf(index));		cell.setValue(rs.getString("nhaphanphoi"));
				cell = cells.getCell("FS" + String.valueOf(index));		cell.setValue(rs.getFloat("tienkhuyenmai"));
				//khong tinh donn hangtra ve 
				if(!dhid.equals(dh_bk) && !rs.getString("trangthai").equals("3"))
				{
					dh_bk=rs.getString("dhid");
					cell = cells.getCell("FT" + String.valueOf(index));		cell.setValue(1);
				}else
				{
					cell = cells.getCell("FT" + String.valueOf(index));		cell.setValue(0);
				}
			
				String isPDA =rs.getString("isPDA");
				String loaidonhang = "WEB";
				if(isPDA.equals("1"))
					loaidonhang = "PDA";
				cell = cells.getCell("FU" + String.valueOf(index));		cell.setValue(loaidonhang);
				cell = cells.getCell("FV" + String.valueOf(index));		cell.setValue(rs.getString("Vung"));
				cell = cells.getCell("FW" + String.valueOf(index));		cell.setValue(rs.getString("Khuvuc"));
				
				if(!khid.equals(kh_bk) && !rs.getString("trangthai").equals("3"))
				{
					kh_bk=  rs.getString("makh").trim()+"_"+rs.getString("Sitecode").trim();
					cell = cells.getCell("FX" + String.valueOf(index));		cell.setValue(1);
				}else
				{
					cell = cells.getCell("FX" + String.valueOf(index));		cell.setValue(0);
				}
				cell = cells.getCell("FY" + String.valueOf(index));		cell.setValue(rs.getString("nvbhId"));
				cell = cells.getCell("FZ" + String.valueOf(index));		cell.setValue(rs.getString("chungloai"));
				cell = cells.getCell("GA" + String.valueOf(index));		cell.setValue(rs.getString("nhomsanpham"));
				
				cell = cells.getCell("GB" + String.valueOf(index));		cell.setValue(rs.getString("gsMa"));
				cell = cells.getCell("GC" + String.valueOf(index));		cell.setValue(rs.getString("gsTen"));
				cell = cells.getCell("GD" + String.valueOf(index));		cell.setValue(rs.getString("khoTen"));
				
				String XuatKhau =rs.getString("XuatKhau")==null?" ":rs.getString("XuatKhau");
				if(XuatKhau.equals("0"))
					XuatKhau = "Bán lẻ";
				else  if(XuatKhau.equals("1"))
						XuatKhau = "Bán buôn";
				else  if(XuatKhau.equals("2"))
					XuatKhau = "Bán buôn và bán lẻ";
				else  if(XuatKhau.equals("3"))
					XuatKhau = "Bán lẻ và bán buôn";
				
				String KhongKyHopDong = rs.getString("KhongKyHopDong")==null?" ":rs.getString("KhongKyHopDong");
				if(KhongKyHopDong.equals("0"))
					KhongKyHopDong = "Ký hợp đồng";
				else  if(KhongKyHopDong.equals("1"))
					KhongKyHopDong = "Không Ký hợp đồng";
				
				/*
				-Tiền mặt: ko trừ tiền CK tháng, quý trong đơn hàng. Có trừ CK ngay 
				- Hóa đơn: Trừ chiết khấu tháng quý
				*/
				
				double DoanhSo= rs.getDouble("DoanhSo");
				double ckKm =rs.getDouble("ckKm");
				double ckDonHang =rs.getDouble("ckDonHang");
				double ckTichLuy =rs.getDouble("ckTichLuy");
				double ckBoSung =rs.getDouble("ckBoSung");
				
				
				
				
				double ThucThu = DoanhSo-ckBoSung-ckKm;
				if(dhid.indexOf("-")>=0)
					ThucThu = DoanhSo;
				
				
				String ThanhToan =rs.getString("ThanhToan");
				if(ThanhToan.equals("0"))
					ThanhToan = "Tiền mặt";
				else  if(ThanhToan.equals("1"))
				{
					ThanhToan = "Hóa đơn";
					ThucThu -=ckTichLuy;
				}
				
				cell = cells.getCell("GE" + String.valueOf(index));		cell.setValue(XuatKhau);
				cell = cells.getCell("GF" + String.valueOf(index));		cell.setValue(KhongKyHopDong);
				cell = cells.getCell("GG" + String.valueOf(index));		cell.setValue(ThucThu );
				
				cell = cells.getCell("GH" + String.valueOf(index));		cell.setValue(ThanhToan);
				String sohd=rs.getString("SoHoaDon")==null?" ":rs.getString("SoHoaDon");
				if( htbOTC.get(rs.getString("dhid"))!=null &&  rs.getString("loai").equals("1") )
				{
					
					String arrtemp[]=htbOTC.get(rs.getString("dhid")).split(",");
					for(int i=0;i<arrtemp.length;i++)
					{
						String[] tem=arrtemp[i].split("__");
						sohd+= tem[0] +" ";
					}
				//	System.out.println(arrtemp[0] +"-----"+arrtemp[1]+"----");
				}
				if( htbETC.get(rs.getString("dhid"))!=null &&  rs.getString("loai").equals("2") )
				{
					String arrtemp[]=htbETC.get(rs.getString("dhid")).split(",");

					for(int i=0;i<arrtemp.length;i++)
					{
						String[] tem=arrtemp[i].split("__");
						sohd+= tem[0] +" ";
					}
				}
				
				cell = cells.getCell("GI" + String.valueOf(index));		cell.setValue(sohd);
				cell = cells.getCell("GJ" + String.valueOf(index));		cell.setValue(rs.getString("KenhBanHang")==null?" ":rs.getString("KenhBanHang"));
				
				cell = cells.getCell("GK" + String.valueOf(index));		cell.setValue(rs.getString("ChuCuaHieu")==null?" ":rs.getString("ChuCuaHieu"));
				cell = cells.getCell("GL" + String.valueOf(index));		cell.setValue(rs.getString("tennvgn")==null?" ":rs.getString("tennvgn"));
				cell = cells.getCell("GM" + String.valueOf(index));		cell.setValue(rs.getString("macu"));
				cell = cells.getCell("GN" + String.valueOf(index));		cell.setValue(rs.getString("ghichu"));
				cell = cells.getCell("GO" + String.valueOf(index));		cell.setValue(rs.getString("tinhthanh"));
				cell = cells.getCell("GP" + String.valueOf(index));		cell.setValue(rs.getString("DiaChiGiaoHang"));
				cell = cells.getCell("GQ" + String.valueOf(index));		cell.setValue(rs.getString("dienthoai"));
				index++;
			}
		    }
			if(rs!=null){
				rs.close();
			}	
			
			ReportAPI.setHidden(workbook,14);
			 
			if(!coData)
				throw new Exception("Không có dữ liệu theo điều kiện lọc");
		    			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(rs != null)
			{
				rs.close();
			}
			ex.printStackTrace();
			if(db != null)
				db.shutDown();
			
			throw new Exception(ex.getMessage());
		}
	}




	private void CreateHeader_HoaDon(Workbook workbook,IStockintransit obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
    Worksheet worksheet = worksheets.getSheet(0);	    
    worksheet.setName("Sheet1");
    Cells cells = worksheet.getCells();	 
    
    cells.setRowHeight(0, 20.0f);	    
    Cell cell = cells.getCell("A1");	
    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Báo Cáo Hóa Đơn Bán Trong Kỳ");
    
    cell = cells.getCell("A3");
    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
    cell = cells.getCell("A4");
    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
    cell = cells.getCell("A5");
    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());
    
	  cell = cells.getCell("FA1");		cell.setValue("KenhBanHang");				
		cell = cells.getCell("FB1");		cell.setValue("MaSoHoaDon");	
		cell = cells.getCell("FC1");		cell.setValue("NgayHoaDon");		
		cell = cells.getCell("FD1");		cell.setValue("SoHoaDon");			
		cell = cells.getCell("FE1");		cell.setValue("KyHieu");			
		cell = cells.getCell("FF1");		cell.setValue("CN/DT");			
		cell = cells.getCell("FG1");		cell.setValue("MaKH");					
		cell = cells.getCell("FH1");		cell.setValue("TenKhachHang");  		
		cell = cells.getCell("FI1");		cell.setValue("ChuCuaHieu");
		cell = cells.getCell("FJ1");		cell.setValue("DiaChi");
		cell = cells.getCell("FK1");		cell.setValue("MST");
		cell = cells.getCell("FL1");		cell.setValue("MaSanPham");
		cell = cells.getCell("FM1");		cell.setValue("TenSanPham");
		cell = cells.getCell("FN1");		cell.setValue("DonViTinh");
		cell = cells.getCell("FO1");		cell.setValue("SoLuong");
		cell = cells.getCell("FP1");		cell.setValue("DonGia");
		cell = cells.getCell("FQ1");		cell.setValue("ThanhTien");
		cell = cells.getCell("FR1");		cell.setValue("LoaiHoaDon");
		cell = cells.getCell("FS1");		cell.setValue("TrangThai");
		cell = cells.getCell("FT1");		cell.setValue("NVBH");
		cell = cells.getCell("FU1");		cell.setValue("KhoXuat");
		cell = cells.getCell("FV1");		cell.setValue("TenNhanVienGiaoNhan");
	}

	private void FillData_HoaDon(Workbook workbook, String query, IStockintransit obj) throws Exception
	{
		ResultSet rs = null;
		dbutils db = new dbutils();
		try
		{	
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();
			
			for(int i=0;i<15;++i)
			{
		    	cells.setColumnWidth(i, 15.0f);	    	
		    }	
			 rs = db.get(query);
			int index = 2;
		    Cell cell = null;
			while (rs.next())
			{
			
				double ptThue= (1+rs.getDouble("VAT")/100);
				cell = cells.getCell("FA" + String.valueOf(index));		cell.setValue(rs.getString("HD"));			
				cell = cells.getCell("FB" + String.valueOf(index));		cell.setValue(rs.getString("HoaDon_fk"));
				cell = cells.getCell("FC" + String.valueOf(index));		cell.setValue(rs.getString("NgayXUATHD"));
				cell = cells.getCell("FD" + String.valueOf(index));		cell.setValue(rs.getString("SoHoaDon"));
				cell = cells.getCell("FE" + String.valueOf(index));		cell.setValue(rs.getString("KyHieu"));
				cell = cells.getCell("FF" + String.valueOf(index));		cell.setValue(rs.getString("nppTEN"));
				cell = cells.getCell("FG" + String.valueOf(index));		cell.setValue(rs.getString("khMA"));
				cell = cells.getCell("FH" + String.valueOf(index));		cell.setValue(rs.getString("khTEN"));
				cell = cells.getCell("FI" + String.valueOf(index));		cell.setValue(rs.getString("CHUCUAHIEU"));
				
				cell = cells.getCell("FJ" + String.valueOf(index));		cell.setValue(rs.getString("KhDIACHI"));
				cell = cells.getCell("FK" + String.valueOf(index));		cell.setValue(rs.getString("KHMST"));
				cell = cells.getCell("FL" + String.valueOf(index));		cell.setValue(rs.getString("spMA"));
				cell = cells.getCell("FM" + String.valueOf(index));		cell.setValue(rs.getString("spTEN"));
				cell = cells.getCell("FN" + String.valueOf(index));		cell.setValue(rs.getString("spDONVI"));
				cell = cells.getCell("FO" + String.valueOf(index));		cell.setValue(rs.getDouble("sOLUONG"));
				cell = cells.getCell("FP" + String.valueOf(index));		cell.setValue( Math.round( rs.getDouble("DONGIA")*ptThue));
				cell = cells.getCell("FQ" + String.valueOf(index));		cell.setValue(Math.round(rs.getDouble("THANHTIEN")*ptThue));
				
				cell = cells.getCell("FR" + String.valueOf(index));		cell.setValue(rs.getString("LOAIHD").equals("0")?"BÁN":"KM");
				
				String TrangThai="";
				if(rs.getInt("Trangthai")==1)
				{
					TrangThai="Chờ xác nhận";
				}else 	
				if(rs.getInt("Trangthai")==2)
				{
					TrangThai="Đã xác nhận";
				}else if(rs.getInt("Trangthai")==3)
				{
					TrangThai="Đã Xóa";
				}
				else if(rs.getInt("Trangthai")==4)
				{
					TrangThai="Đã In";
				}else if(rs.getInt("Trangthai")==5)
				{
					TrangThai="Đã Hủy";
				}
				
				
				cell = cells.getCell("FS" + String.valueOf(index));		cell.setValue(TrangThai);
				cell = cells.getCell("FT" + String.valueOf(index));		cell.setValue(rs.getString("ddkdTEN"));
				
				cell = cells.getCell("FU" + String.valueOf(index));		cell.setValue(rs.getString("KHO"));

				cell = cells.getCell("FV" + String.valueOf(index));		cell.setValue(rs.getString("tennvgn")==null?"":rs.getString("tennvgn"));

				index++;
			}
			if(rs!=null){
				rs.close();
			}	
			
			ReportAPI.setHidden(workbook,14);
			 
		
		    			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			if(rs != null)
			{
				rs.close();
			}
			ex.printStackTrace();
			if(db != null)
				db.shutDown();
			
			throw new Exception(ex.getMessage());
		}
	}
	
	
	
	
	private void CreateHeader(Workbook workbook,IStockintransit obj) 
	{
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);	    
	    worksheet.setName("Sheet1");
	    Cells cells = worksheet.getCells();	 
	    
	    cells.setRowHeight(0, 20.0f);	    
	    Cell cell = cells.getCell("A1");	
	    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Báo Cáo Đơn Hàng Bán Trong Kỳ");
	    
	    cell = cells.getCell("A3");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Từ ngày: " + obj.gettungay() + "  Đến ngày : " + obj.getdenngay());
	    cell = cells.getCell("A4");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Ngày tạo : " + this.getDateTime()); 
	    cell = cells.getCell("A5");
	    ReportAPI.getCellStyle(cell,Color.NAVY,true,10,"Người tạo : " + obj.getuserTen());
	    
	    
	    cell = cells.getCell("FA1");		cell.setValue("Ngay");				
		cell = cells.getCell("FB1");		cell.setValue("NHANVIENBANHANG");	
		cell = cells.getCell("FC1");		cell.setValue("TenKhachHang");		
		cell = cells.getCell("FD1");		cell.setValue("MaDonHang");			
		cell = cells.getCell("FE1");		cell.setValue("NgayHoaDon");			
		cell = cells.getCell("FF1");		cell.setValue("TenSanPham");			
		cell = cells.getCell("FG1");		cell.setValue("CTKM/CTTB");					
		cell = cells.getCell("FH1");		cell.setValue("MaKhachHang");  		
		cell = cells.getCell("FI1");		cell.setValue("MaSanPham");			
		cell = cells.getCell("FJ1");		cell.setValue("DiaChi");				
		cell = cells.getCell("FK1");		cell.setValue("ChietKhau");			
		cell = cells.getCell("FL1");		cell.setValue("SoLuong");				
		cell = cells.getCell("FM1");		cell.setValue("DonGia");				
		cell = cells.getCell("FN1");		cell.setValue("TongTien");				
		cell = cells.getCell("FO1");		cell.setValue("TrangThai");  			
		cell = cells.getCell("FP1");		cell.setValue("Kien");  		    	
		cell = cells.getCell("FQ1");		cell.setValue("SiteCode");  			
		cell = cells.getCell("FR1");		cell.setValue("ChiNhanh/DoiTac");  			
		cell = cells.getCell("FS1");		cell.setValue("TienKhuyenMai/TienTrungBay");  			
		cell = cells.getCell("FT1");		cell.setValue("DemSoDH");  	
		cell = cells.getCell("FU1");		cell.setValue("LoaiDonHang");  	
		cell = cells.getCell("FV1");		cell.setValue("Vung");
		cell = cells.getCell("FW1");		cell.setValue("Khuvuc");
		cell = cells.getCell("FX1");		cell.setValue("DemSoKH");
		cell = cells.getCell("FY1");		cell.setValue("MANVBH");
		cell = cells.getCell("FZ1");		cell.setValue("ChungLoai");
		cell = cells.getCell("GA1");		cell.setValue("NhomSP");
		
		cell = cells.getCell("GB1");		cell.setValue("MaGiamSat");
		cell = cells.getCell("GC1");		cell.setValue("GiamSatBanHang");
		
		cell = cells.getCell("GD1");		cell.setValue("KhoXuat");
		
		cell = cells.getCell("GE1");		cell.setValue("LoaiKhachHang");
		cell = cells.getCell("GF1");		cell.setValue("KyHopDong");
		cell = cells.getCell("GG1");		cell.setValue("TongTienThuVe");
		cell = cells.getCell("GH1");		cell.setValue("LoaiThanhToan");
		cell = cells.getCell("GI1");		cell.setValue("SoHoaDon");
		cell = cells.getCell("GJ1");		cell.setValue("KenhBanHang");
		
		cell = cells.getCell("GK1");		cell.setValue("ChuCuaHieu");
		cell = cells.getCell("GL1");		cell.setValue("NhanVienGiaoNhan");
		cell = cells.getCell("GM1");		cell.setValue("macu");
		cell = cells.getCell("GN1");		cell.setValue("ghichu");
		cell = cells.getCell("GO1");		cell.setValue("tinhthanhnpp");
		cell = cells.getCell("GP1");		cell.setValue("DiaChiGiaoHang");
		cell = cells.getCell("GQ1");		cell.setValue("DienThoai");
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private String getPiVotName()
	{
		String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
		Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
	    String name = sdf.format(cal.getTime());
	    name = name.replaceAll("-", "");
	    name = name.replaceAll(" ", "_");
	    name = name.replaceAll(":", "");
	    return "_" + name;
	    
	}
	
	public static Hashtable<String, String> gethash_OTC(IStockintransit obj,dbutils db )
	{
		Hashtable<String, String> htb = new Hashtable<String, String>();
		/*String query=	"\n		select dh.pk_seq ,STUFF      "+  
						"\n		(       "+
						"\n			(      "+
						"\n				select DISTINCT TOP 100 PERCENT ' , ' + RTRIM(ltrim(isnull(sohoadon,'')))+' '+isnull(KYHIEU,'')   "+
						"\n				from HOADON a inner join HOADON_DDH b on b.HOADON_FK=a.PK_SEQ    "+
						"\n				where a.TRANGTHAI in (2,4)  and b.DDH_FK=dh.PK_SEQ      "+
						"\n				ORDER BY ' , ' +  RTRIM(ltrim(isnull(sohoadon,'')))+' '+isnull(KYHIEU,'')   "+
						"\n				FOR XML PATH('')     "+    
						"\n			 ), 1, 2, ''      "+
						"\n		) AS SoHoaDon  "+
						"\n		,STUFF      "+  
						"\n		(       "+
						"\n			(      "+
						"\n				select DISTINCT TOP 100 PERCENT ' , ' + RTRIM(ltrim(isnull(NgayXuatHD,'')))  "+
						"\n				from HOADON a inner join HOADON_DDH b on b.HOADON_FK=a.PK_SEQ    "+
						"\n				where a.TRANGTHAI in (2,4) and b.DDH_FK=dh.PK_SEQ      "+
						"\n				ORDER BY ' , ' + RTRIM(ltrim(isnull(NgayXuatHD,'')))   "+
						"\n				FOR XML PATH('')     "+    
						"\n			 ), 1, 2, ''      "+
						"\n		) as ngayhoadon from donhang dh where 1=1 ";*/
		
		String query = "select dh.pk_seq,  (hd.SOHOADON +' '+ hd.kyhieu) as sohoadon, hd.NGAYXUATHD " +
					   "	from donhang dh inner join HOADON_DDH hd_ddh on dh.PK_SEQ = hd_ddh.DDH_FK " +
					   "				inner join HOADON hd on hd_ddh.HOADON_FK = hd.PK_SEQ " +
					   "	where 1=1  and hd.TRANGTHAI in ( 2, 4 )  ";
		
		if(obj.gettungay().length()>0)
		{
			query+= " and dh.NgayNhap>='"+obj.gettungay()+"' ";
		}
		if(obj.getdenngay().length()>0)
		{
			query+= " and dh.NgayNhap <='"+obj.getdenngay()+"' ";
		}
		
		System.out.println("gethast OTC111"+query);
		ResultSet rs = db.get(query);
		try
		{
			while (rs.next())
			{
				String values = "";
				if( htb.get( rs.getString("pk_seq") ) != null )
					values = htb.get( rs.getString("pk_seq") ) + "," + ( rs.getString("SoHoaDon") + "__" + rs.getString("NGAYXUATHD")  );
				else
					values = ( rs.getString("SoHoaDon") + "__" + rs.getString("NGAYXUATHD")  );
					
				htb.put(rs.getString("pk_seq"), values + "__1"  );
				//System.out.println(rs.getString("pk_seq") +"gia tri la "+values);
			}
			rs.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		} 
		return htb;
	}
	
	
	public static Hashtable<String, String> gethash_ETC(IStockintransit obj,dbutils db )
	{
		Hashtable<String, String> htb = new Hashtable<String, String>();
	/*	String query="\n		 select dh.pk_seq ,STUFF "+        
				     	"\n		  	(  "+       
				     	"\n		  	(      "+  
				     	"\n		  		select DISTINCT TOP 100 PERCENT ' , ' + RTRIM(ltrim(isnull(sohoadon,'')))+' '+isnull(KYHIEU,'')   "+   
				     	"\n		  		from ERP_HOADONNPP a inner join ERP_HOADONNPP_DDH b on b.HOADONNPP_FK=a.PK_SEQ       "+
				     	"\n		  		where a.TRANGTHAI in (2,4) and b.DDH_FK=dh.PK_SEQ        "+
				     	"\n		  		ORDER BY ' , ' + RTRIM(ltrim(isnull(sohoadon,'')))+' '+isnull(KYHIEU,'')    "+   
				     	"\n		  		FOR XML PATH('')            "+
				     	"\n		  	 ), 1, 2, ''         "+
				     	"\n		  ) AS SoHoaDon     "+
						"\n from erp_dondathangnpp dh where 1=1  ";*/
		String query = "select dh.pk_seq, (hd.SOHOADON +' '+ hd.kyhieu) as sohoadon" +
				   "	from erp_dondathangnpp dh inner join ERP_HOADONNPP_DDH hd_ddh on dh.PK_SEQ = hd_ddh.DDH_FK " +
				   "				inner join ERP_HOADONNPP hd on hd_ddh.HOADONnpp_FK = hd.PK_SEQ " +
				   "	where 1=1  and hd.TRANGTHAI in ( 2, 4 )  ";
		
		if(obj.gettungay().length()>0)
		{
			query+= " and dh.ngaydonhang>='"+obj.gettungay()+"' ";
		}
		if(obj.getdenngay().length()>0)
		{
			query+= " and dh.ngaydonhang <='"+obj.getdenngay()+"' ";
		}
		System.out.println("gethast ETC11"+query);
		ResultSet rs = db.get(query);
		try
		{
			while (rs.next())
			{
				String values = "";
				if( htb.get( rs.getString("pk_seq") ) != null )
					values = htb.get( rs.getString("pk_seq") ) + "," + ( rs.getString("SoHoaDon")   );
				else
					values = ( rs.getString("SoHoaDon")   );
					
				htb.put(rs.getString("pk_seq"), values + "__2" );
				//System.out.println(rs.getString("pk_seq") +"gia tri la "+values);
			}
			rs.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		return htb;
	}
	
	
	
	

}
