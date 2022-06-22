package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;






import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

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
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.aspose.cells.Picture;

public class DmsdashboardSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;



	private String setQuerySoDonHang(IStockintransit obj,int type) throws SQLException 
	{
		String query = "";
		if(type == 0)
		{
			query = " \n select callofday.vung,sokhphaidi,sokhthucte,sodonhang from" 
					 + " \n (" 
					 + " \n select v.PK_SEQ as vung, COUNT(distinct KHACHHANG_FK) as sokhphaidi from KHACHHANG_TUYENBH khtbh" 
					 + " \n inner join TUYENBANHANG tbh on khtbh.TBH_FK = tbh.PK_SEQ and tbh.NGAYID <> 8" 
					 + " \n inner join DAIDIENKINHDOANH ddkd on tbh.DDKD_FK =ddkd.PK_SEQ" 
					 + " \n inner join DIABAN db on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK"
					 +"\n inner join GIAMSATBANHANG gsbh on gsbh.KHUVUC_FK = kv.PK_SEQ and gsbh.KBH_FK = 100025 "
					 + " \n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK "
					 + " inner join KHACHHANG kh on kh.PK_SEQ = khtbh.KHACHHANG_FK and kh.TRANGTHAI = 1" 
					 + " \n where ddkd.TRANGTHAI = 1 and dbo.[TanSoXuatHien](khtbh.TANSO,'"+obj.getdenngay()+"') = 1 and NGAYID = datepart(dw,'"+obj.getdenngay()+"')" 
					 + " \n group by v.PK_SEQ,v.TEN " 
					 + " \n )callofday   left join " 
					 + " \n (" 
					 + " \n 	select v.PK_SEQ as vung, COUNT(distinct KHACHHANG_FK) as sokhthucte from " 
					 + " \n 	ddkd_khachhang ddkh " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = ddkh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					 + " 	inner join KHACHHANG kh on kh.PK_SEQ = ddkh.KHACHHANG_FK " 
					 + " \n 	where  CAST(CONVERT(char(10),thoidiem,120) as varchar(10)) = '"+obj.getdenngay()+"' and kh.TRANGTHAI = 1 " 
					 + " \n 	group by v.PK_SEQ" 
					 + " \n )callofmonth on callofday.vung = callofmonth.vung" 
					 + " \n   left join "
					 + " ( "
					 + "\n select vung,SUM(sodonhang) as sodonhang from" 
					 + " \n (" 
					 + " \n 	select v.PK_SEQ as vung, COUNT( distinct dh.khachhang_fk) as sodonhang from " 
					 + " \n 	DAIDIENKINHDOANH ddkd " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	inner join BC_donhang dh on dh.DIABAN_FK = db.PK_SEQ" 
					 + " \n 	where ddkd.TRANGTHAI = 1 and dh.NGAYNHAP = '"+obj.getdenngay()+"'  and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
					 + " \n 	group by v.PK_SEQ,dh.ngaynhap"
					 + "\n )kq  group by vung " 
					 + " \n ) orderday on orderday.vung = callofmonth.vung" ;
		}
		if(type == 1)
		{
			query =  " select callofdaytMdt.vung,sokhphaidi,sokhthucte,sodonhang from " 
					 + " \n (" 
					 + " \n 		select kv.vung_fk as vung, SUM(sokhphaidi) as sokhphaidi from " 
						+ " \n 			(" 
						+ " \n 			select   db.PK_SEQ as diaban,COUNT(distinct kht.khachhang_fk) sokhphaidi " 
						+ " \n 			from [dbo].[uf_CacNgayTrongThang]("+obj.getMonth()+","+obj.getYear()+") ng" 
						+ " \n 			inner join DIABAN db on 1=1" 
						+ " \n 			inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ" 
						+ " \n 			inner join GIAMSATBANHANG gsbh on gsbh.KHUVUC_FK = db.KHUVUC_FK and gsbh.KBH_FK = 100025" 
						+ " \n 			inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
						+ " \n 			left join TUYENBANHANG tbh on tbh.DDKD_FK = ddkd.PK_SEQ and DATEPART(dw,ng.Ngay) = tbh.NGAYID and tbh.NGAYID <> 8 " 
						+ " \n 			left join KHACHHANG_TUYENBH kht on kht.TBH_FK = tbh.PK_SEQ" 
						+ " \n 			inner join KHACHHANG kh on kh.PK_SEQ = kht.Khachhang_FK and kh.trangthai = '1' "
						+ " \n 			where ddkd.TRANGTHAI = 1 and ng.Ngay <= '"+obj.getdenngay()+"' and ng.Ngay >= '"+obj.gettungay()+"' and dbo.[TANSOXuatHien](kht.TANSO,ng.Ngay) = 1   " 
						+ " \n 			group by db.PK_SEQ,ng.Ngay" 
						+ " \n 			) a inner join DIABAN db on db.PK_SEQ = a.diaban  "
						+ " \n 	 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK "
						+ " \n 	 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK "
						+ " \n 		group by kv.VUNG_FK "  
				
					 + " \n )as  callofdaytMdt " 
					 + " \n left join " 
					 + " \n 	(	select vung as vung, SUM(sokhthucte) as sokhthucte from " 
					 + " \n (" 
					 + " \n 	select v.PK_SEQ as vung,COUNT(distinct ddkdkh.KHACHHANG_FK) sokhthucte from dbo.uf_CacNgayTrongThang("+obj.getMonth()+","+obj.getYear()+")" 
					 + " \n 	ngay inner join ddkd_khachhang ddkdkh on CONVERT(char(10),ddkdkh.thoidiem ,120)  = ngay.Ngay" 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = ddkdkh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK "
					+ " \n 			inner join KHACHHANG kh on kh.PK_SEQ = ddkdkh.Khachhang_FK  " 
					 + " \n 	where 1 = 1 and kh.trangthai = '1' " 
					 + " \n 	and ngay.Ngay >= '"+obj.gettungay()+"' and ngay.Ngay  <= '"+obj.getdenngay()+"' " 
					 + " \n 	group by v.PK_SEQ,ngay.ngay " 
					 + " \n ) "
					 + "	kq group by vung "
					 + "\n )	" 
					 + " \n callofmonthMtd on callofmonthMtd.vung = callofdaytMdt.vung" 
					 + " \n left join ("
					 + " \n 		select vung , SUM(sodonhang) as sodonhang from " 
					 + " \n (" 
					 + " \n " 
					 + " \n select v.PK_SEQ as vung,COUNT(distinct dh.khachhang_fk) sodonhang from " 
					 + " \n 	BC_donhang dh " 
					 + "\n 	inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = kh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	where  " 
					 + " \n 	 dh.NGAYNHAP >= '"+obj.gettungay()+"' and dh.NGAYNHAP  <= '"+obj.getdenngay()+"' " 
					 + " \n 	and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 	group by v.PK_SEQ,dh.ngaynhap "
					 + "\n )kq  group by vung " 
					 + " \n )orderMtd on orderMtd.vung = callofdaytMdt.vung ";
		}
		if(type == 2)
		{
			query = " select orderday.vung,sodonhang,sotien from" 
					 + " \n (" 
					 + " \n select v.PK_SEQ as vung,COUNT(distinct dh.khachhang_fk) sodonhang from " 
					 + " \n 	BC_donhang dh " 
					 + "\n 	inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = kh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	inner join DAIDIENKINHDOANH ddkd on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n 	where ddkd.TRANGTHAI = 1 " 
					 + " \n 	and dh.NGAYNHAP = '"+obj.getdenngay()+"' " 
					 + " \n 	and dh.trangthai = 1" 
					 + " \n 	group by v.PK_SEQ,v.TEN" 
					 + " \n )orderday" 
					 + " \n left join" 
					 + " \n (" 
					 + " \n select v.PK_SEQ as vung,round(sum(dh_sp.GIAMUA*SOLUONG)/1000000.0,0) sotien from " 
					 + " \n 	BC_donhang dh " 
					 + "\n 	inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = kh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	inner join DAIDIENKINHDOANH ddkd on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n 	inner join BC_DONHANG_SANPHAM dh_sp on dh_sp.DONHANG_FK = dh.PK_SEQ" 
					 + " \n 	where ddkd.TRANGTHAI = 1 " 
					 + " \n 	and dh.NGAYNHAP = '"+obj.getdenngay()+"' " 
					 + " \n 	and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 	group by v.PK_SEQ" 
					 + " \n )salesday on salesday.vung = orderday.vung" ;
		}
		if(type == 3)
		{
			query = " select orderday.vung,sodonhang,sotien, tbthangtruoc from" 
					 + " \n ("
					 + "  select vung,sum(sodonhang) sodonhang from (" 
					 + " \n select v.PK_SEQ as vung,COUNT( distinct dh.KHACHHANG_FK) sodonhang from " 
					 + " \n 	BC_donhang dh "
					 + "\n 	inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = kh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	inner join DAIDIENKINHDOANH ddkd on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n 	where ddkd.TRANGTHAI = 1 " 
					 + " \n 	and dh.NGAYNHAP >= '"+obj.gettungay()+"' AND dh.NGAYNHAP <= '"+obj.getdenngay()+"' " 
					 + " \n 	and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 	group by v.PK_SEQ,dh.NGAYNHAP "
					 + " ) kq  group by vung "
					 + " \n )orderday" 
					 + " \n left join" 
					 + " \n (" 
					 + " \n select v.PK_SEQ as vung,round(sum(dh_sp.GIAMUA*SOLUONG)/1000000.0,0) sotien from " 
					 + " \n 	BC_donhang dh "
					 + "\n 		inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = kh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	inner join DAIDIENKINHDOANH ddkd on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n 	inner join BC_DONHANG_SANPHAM dh_sp on dh_sp.DONHANG_FK = dh.PK_SEQ" 
					 + " \n 	where ddkd.TRANGTHAI = 1 " 
					 + " \n 	and dh.NGAYNHAP >= '"+obj.gettungay()+"' AND dh.NGAYNHAP <= '"+obj.getdenngay()+"' " 
					 + " \n 	and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 	group by v.PK_SEQ" 
					 + " \n )salesday on salesday.vung = orderday.vung" 
					 + " \n left join" 
					 + " \n (" 
					 + " \n select v.PK_SEQ as vung,round(sum(dh_sp.GIAMUA*SOLUONG)/1000000.0,0) tbthangtruoc from " 
					 + " \n 	BC_donhang dh "
					 + "\n		inner join KHACHHANG kh on kh.PK_SEQ = dh.KHACHHANG_FK " 
					 + " \n 	inner join DIABAN db on db.PK_SEQ = kh.diaban_fk" 
					 + " \n 	inner join KHUVUC kv on kv.PK_SEQ = db.KHUVUC_FK" 
					 + " \n 	inner join VUNG v on v.PK_SEQ = kv.VUNG_FK" 
					 + " \n 	inner join DAIDIENKINHDOANH ddkd on db.PK_SEQ = ddkd.diaban_fk" 
					 + " \n 	inner join BC_DONHANG_SANPHAM dh_sp on dh_sp.DONHANG_FK = dh.PK_SEQ" 
					 + " \n 	where ddkd.TRANGTHAI = 1 " 
					 + " \n 	and dh.NGAYNHAP >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120)  and dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)" 
					 + " \n 	and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 	group by v.PK_SEQ" 
					 + " \n )salesLastmonth on salesLastmonth.vung = orderday.vung" ;
		}
		if(type == 4)
		{
			query = " select  SoDDKD1, SoDDKD2, SoDDKD3 from " 
					 + " \n 	(" 
					 + " \n 	select COUNT(*) SoDDKD1 from" 
					 + " \n 	(" 
					 + " \n 		select ddkd.PK_SEQ as DDKD1,count( distinct dhsp.SANPHAM_FK) as SoSKU from " 
					 + " \n 	BC_donhang dh" 
					 + " \n 	inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
					 + " \n 	inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ" 
					 + " \n 	where ddkd.TRANGTHAI = 1 " 
					 + " \n 	and  dh.NGAYNHAP  = '"+obj.getdenngay()+"' " 
					 + " \n 	and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 	group by ddkd.PK_SEQ" 
					 + " \n 		having count( distinct dhsp.SANPHAM_FK) <  5 and count( distinct dhsp.SANPHAM_FK) >=  1" 
					 + " \n 	)a" 
					 + " \n " 
					 + " \n 	)dt ," 
					 + " \n 	(" 
					 + " \n 		select COUNT(*) SoDDKD2 from" 
					 + " \n 		(" 
					 + " \n 			select ddkd.PK_SEQ as DDKD2,count( distinct dhsp.SANPHAM_FK) as SoSKU from " 
					 + " \n 			BC_donhang dh" 
					 + " \n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
					 + " \n 			inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ" 
					 + " \n 			where ddkd.TRANGTHAI = 1 " 
					 + " \n 			and  dh.NGAYNHAP  = '"+obj.getdenngay()+"' " 
					 + " \n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 			group by ddkd.PK_SEQ" 
					 + " \n 			having count( distinct dhsp.SANPHAM_FK) <=  7 and count( distinct dhsp.SANPHAM_FK) >=  5" 
					 + " \n 		)b" 
					 + " \n 	)dt1," 
					 + " \n 	(" 
					 + " \n 	select COUNT(*) SoDDKD3 from" 
					 + " \n 		(" 
					 + " \n 			select ddkd.PK_SEQ as DDKD3,count( distinct dhsp.SANPHAM_FK) as SoSKU from " 
					 + " \n 			BC_donhang dh" 
					 + " \n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
					 + " \n 			inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ" 
					 + " \n 			where ddkd.TRANGTHAI = 1 " 
					 + " \n 			and  dh.NGAYNHAP  = '"+obj.getdenngay()+"' " 
					 + " \n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'" 
					 + " \n 			group by ddkd.PK_SEQ" 
					 + " \n 			having count( distinct dhsp.SANPHAM_FK) >=  8" 
					 + " \n 		)c" 
					 + " \n 	)dt2" ;
		}
			if(type == 5)
			{
					query =	" select  SoDDKD1, SoDDKD2, SoDDKD3,SoDDKDM1,SoDDKDM2,SoDDKDM3 from  " 
						 + "\n 	( " 
						 + "\n 		select COUNT(distinct DDKD1) SoDDKD1 from " 
						 + "\n 		( " 
						 + "\n 			select ddkd.PK_SEQ as DDKD1,count( distinct dhsp.SANPHAM_FK) as SoSKU from  " 
						 + "\n		BC_donhang dh " 
						 + " \n 	inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						 + "\n 		inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ " 
						 + "\n 		where   " 
						 + "\n 		  dh.NGAYNHAP  <= '"+obj.getdenngay()+"'  and dh.NGAYNHAP  >= '"+obj.gettungay()+"' "  
						 + "\n 		and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
						 + "\n 		group by ddkd.PK_SEQ  " 
						 + "\n 			having count( distinct dhsp.SANPHAM_FK) <  5 and count( distinct dhsp.SANPHAM_FK) >=  1" 
						 + "\n 		)a " 
						 + "\n	)dt , " 
						 + "\n 	( " 
						 + "\n 		select COUNT(distinct DDKD2) SoDDKD2 from " 
						 + "\n 		( " 
						 + "\n 			select ddkd.PK_SEQ as DDKD2,count( distinct dhsp.SANPHAM_FK) as SoSKU from  " 
						 + "\n 			BC_donhang dh " 
						 + " \n 		inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						 + "\n 			inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ " 
						 + "\n 			where   " 
						 + "\n 			  dh.NGAYNHAP  <= '"+obj.getdenngay()+"'  and dh.NGAYNHAP  >= '"+obj.gettungay()+"'  " 
						 + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
						 + "\n 			group by ddkd.PK_SEQ " 
						 + "\n 			having count( distinct dhsp.SANPHAM_FK) <=  7 and count( distinct dhsp.SANPHAM_FK) >=  5" 
						 + "\n 		)b " 
						 + "\n 	)dt1, " 
						 + "\n 	( " 
						 + "\n 	select COUNT(distinct DDKD3) SoDDKD3 from " 
						 + "\n 		( " 
						 + "\n 			select ddkd.PK_SEQ as DDKD3,count( distinct dhsp.SANPHAM_FK) as SoSKU from  " 
						 + "\n 			BC_donhang dh " 
						 + " \n 		inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						 + "\n 			inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ " 
						 + "\n 			where 1 = 1  " 
						 + "\n 			and  dh.NGAYNHAP  <= '"+obj.getdenngay()+"'  and dh.NGAYNHAP  >= '"+obj.gettungay()+"'  " 
						 + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
						 + "\n 			group by ddkd.PK_SEQ " 
						 + "\n 			having count( distinct dhsp.SANPHAM_FK) >=  8 " 
						 + "\n 		)c " 
						 + "\n 	)dt2," 
						 + "\n 	( " 
						 + "\n 		select COUNT(distinct DDKD1) SoDDKDM1 from " 
						 + "\n 		( " 
						 + "\n 			select ddkd.PK_SEQ as DDKD1,count( distinct dhsp.SANPHAM_FK) as SoSKU from  " 
						 + "\n 		BC_donhang dh " 
						 + "\n 		inner join DIABAN db on db.PK_SEQ = dh.diaban_fk " 
						 + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						 + "\n 		inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ " 
						 + "\n 		where 1 = 1  " 
						 + "\n 		and  dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)   and dh.NGAYNHAP  >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120)  " 
						 + "\n 		and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
						 + "\n 		group by ddkd.PK_SEQ " 
						 + "\n 			having count( distinct dhsp.SANPHAM_FK) <  5 and count( distinct dhsp.SANPHAM_FK) >=  1" 
						 + "\n 		)a " 
						 + "\n 	)dtm ," 
						 + "\n	( " 
						 + "\n 		select COUNT(distinct DDKD2) SoDDKDM2 from " 
						 + "\n 		( " 
						 + "\n 			select ddkd.PK_SEQ as DDKD2,count( distinct dhsp.SANPHAM_FK) as SoSKU from  " 
						 + "\n 			BC_donhang dh " 
						 + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						 + "\n 			inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ " 
						 + "\n 			where 1 = 1  " 
						 + "\n 			and  dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)   and dh.NGAYNHAP  >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120)  " 
						 + "\n			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
						 + "\n 			group by ddkd.PK_SEQ " 
						 + "\n 			having count( distinct dhsp.SANPHAM_FK) =  7 and count( distinct dhsp.SANPHAM_FK) >=  5" 
						 + "\n 		)b " 
						 + "\n 	)dtm1," 
						 + "\n 	( " 
						 + "\n 	select COUNT(distinct DDKD3) SoDDKDM3 from " 
						 + "\n 		( " 
						 + "\n 			select ddkd.PK_SEQ as DDKD3,count( distinct dhsp.SANPHAM_FK) as SoSKU from  " 
						 + "\n 			BC_donhang dh " 
						 + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						 + "\n 			inner join BC_DONHANG_SANPHAM dhsp on dhsp.DONHANG_FK = dh.PK_SEQ " 
						 + "\n 			where 1 = 1  " 
						 + "\n 			and  dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)   and dh.NGAYNHAP  >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120) " 
						 + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' " 
						 + "\n 			group by ddkd.PK_SEQ " 
						 + "\n 			having count( distinct dhsp.SANPHAM_FK) >= 8 " 
						 + "\n 		)c " 
						 + "\n 	)dtm2" ;
			}
			if(type == 6)
			{
				query	 =	"select  SoDDKD1, SoDDKD2, SoDDKD3 from  "
						   + "\n 	( "
						   + "\n 		select COUNT(*) SoDDKD1 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD1,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n		BC_donhang dh "
							 + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						   + "\n 		where 1 = 1  "
						   + "\n 		and  dh.NGAYNHAP  = '"+obj.getdenngay()+"'  "
						   + "\n 		and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 		group by ddkd.PK_SEQ "
						   + "\n 			having count( distinct dh.PK_SEQ) <=  3 "
						   + "\n 		)a "
						   + "\n	)dt , "
						   + "\n 	( "
						   + "\n 		select COUNT(*) SoDDKD2 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD2,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 			BC_donhang dh "
							 + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						   + "\n 			where 1 = 1  "
						   + "\n 			and  dh.NGAYNHAP  = '"+obj.getdenngay()+"'  "
						   + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 			group by ddkd.PK_SEQ "
						   + "\n 			having count(  distinct dh.PK_SEQ) <  7 and  count(  distinct dh.PK_SEQ) > 3"
						   + "\n 		)b "
						   + "\n 	)dt1, "
						   + "\n 	( "
						   + "\n 	select COUNT(*) SoDDKD3 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD3,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 			BC_donhang dh "
							 + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk" 
						   + "\n 			where 1 = 1  "
						   + "\n 			and  dh.NGAYNHAP  = '"+obj.getdenngay()+"' "
						   + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 			group by ddkd.PK_SEQ "
						   + "\n 			having count( distinct dh.PK_SEQ) >  6 "
						   + "\n 		)c "
						   + "\n 	)dt2 ";
				
			}
			if(type == 7)
			{
				query = 	"   select  SoDDKD1, SoDDKD2, SoDDKD3,SoDDKDM1,SoDDKDM2,SoDDKDM3 from  "
						   + "\n 	( "
						   + "\n 		select COUNT(*) SoDDKD1 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD1,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n		BC_donhang dh "
						   + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk " 
						   + "\n 		where 1 = 1  "
						   + "\n 		and  dh.NGAYNHAP  <= '"+obj.getdenngay()+"'  and dh.NGAYNHAP  >= '"+obj.gettungay()+"'"
						   + "\n 		and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 		group by ddkd.PK_SEQ "
						   + "\n 			having count( distinct dh.PK_SEQ) <=  3 "
						   + "\n 		)a "
						   + "\n	)dt , "
						   + "\n 	( "
						   + "\n 		select COUNT(*) SoDDKD2 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD2,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 			BC_donhang dh "
						   + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk " 
						   + "\n 			where 1 = 1  "
						   + "\n 			and dh.NGAYNHAP  <= '"+obj.getdenngay()+"'  and dh.NGAYNHAP  >= '"+obj.gettungay()+"'"
						   + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 			group by ddkd.PK_SEQ "
						   + "\n 			having count(  distinct dh.PK_SEQ) <  7 and  count(  distinct dh.PK_SEQ) > 3"
						   + "\n 		)b "
						   + "\n 	)dt1, "
						   + "\n 	( "
						   + "\n 	select COUNT(*) SoDDKD3 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD3,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 			BC_donhang dh "
						   + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk " 
						   + "\n 			where 1 = 1  "
						   + "\n 			and  dh.NGAYNHAP  <= '"+obj.getdenngay()+"'  and dh.NGAYNHAP  >= '"+obj.gettungay()+"'"
						   + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 			group by ddkd.PK_SEQ "
						   + "\n 			having count( distinct dh.PK_SEQ) >  6 "
						   + "\n 		)c "
						   + "\n 	)dt2,"
						   + "\n 	( "
						   + "\n 		select COUNT(*) SoDDKDM1 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD1,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 		BC_donhang dh "
						   + "\n 		inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk " 
						   + "\n 		where 1 = 1  "
						   + "\n 		and  dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)   and dh.NGAYNHAP  >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120)  "
						   + "\n 		and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 		group by ddkd.PK_SEQ "
						   + "\n 		having count( distinct dh.PK_SEQ) <=  3 "
						   + "\n 		)a "
						   + "\n 	)dtm ,"
						   + "\n	( "
						   + "\n 		select COUNT(*) SoDDKDM2 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD2,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 			BC_donhang dh "
						   + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk " 
						   + "\n 			where 1 = 1  "
						   + "\n 			and  dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)   and dh.NGAYNHAP  >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120)  "
						   + "\n			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 			group by ddkd.PK_SEQ "
						   + "\n 			having count(  distinct dh.PK_SEQ) <  7 and  count(  distinct dh.PK_SEQ) > 3"
						   + "\n 		)b "
						   + "\n 	)dtm1,"
						   + "\n 	( "
						   + "\n 	select COUNT(*) SoDDKDM3 from "
						   + "\n 		( "
						   + "\n 			select ddkd.PK_SEQ as DDKD3,count( distinct dh.PK_SEQ) as SoSKU from  "
						   + "\n 			BC_donhang dh "
						   + "\n 			inner join DAIDIENKINHDOANH ddkd on ddkd.PK_SEQ = dh.ddkd_fk " 
						   + "\n 			where 1 = 1  "
						   + "\n 			and  dh.NGAYNHAP  <= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.getdenngay()+"'),120)   and dh.NGAYNHAP  >= convert(varchar(10), DATEADD(MONTH,-1,'"+obj.gettungay()+"'),120) "
						   + "\n 			and dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						   + "\n 			group by ddkd.PK_SEQ "
						   + "\n 			having count( distinct dh.PK_SEQ) > 6 "
						   + "\n 		)c "
						   + "\n 	)dtm2";
			}
			if(type == 8)
			{
			String dk = "";	
			if(obj.getYear().equals("2015"))
				dk = "and Month(dh.NGAYNHAP) >= 7 ";
			 query =   " declare @tungay varchar(10)  = '"+obj.gettungay()+"'"
					 + "\n declare @denngay varchar(10) = '"+obj.getdenngay()+"'"
					 + "\n select * from "
					 + "\n  ("
					 + "\n  select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as Totalsales"
					 + "\n  from BC_donhang dh "
					 + "\n  inner join      "
					 + "\n  (	     "
					 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					 + "\n  (		"
					 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					 + "\n  	union all     "
//					 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					 + "\n  )dhsp     "
					 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
					 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
					 + "\n   and dh.NGAYNHAP = @denngay  and dh.DIABAN_FK is not null"
					 + "\n  ) todaysale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as MTDsales"
					 + "\n  from BC_donhang dh "
					 + "\n  inner join      "
					 + "\n  (	     "
					 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					 + "\n  (		"
					 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					 + "\n  	union all     "
//					 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					 + "\n  )dhsp     "
					 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
					 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <=@denngay"
					 + "\n  )MTDSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as YTDsales"
					 + "\n  from BC_donhang dh "
					 + "\n  inner join      "
					 + "\n  (	     "
					 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					 + "\n  (		"
					 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					 + "\n  	union all     "
//					 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					 + "\n  )dhsp     "
					 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
					 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'  and dh.DIABAN_FK is not null"
					 + "\n   and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
					 + "\n  )YTDSale,"
					 + "\n  ("
					 + "\n  select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as TotalKbhsales"
					 + "\n  from BC_donhang dh "
					 + "\n  inner join      "
					 + "\n  (	     "
					 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					 + "\n  (		"
					 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					 + "\n  	union all     "
//					 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					 + "\n  )dhsp     "
					 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
					 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null "
					 + "\n   and dh.NGAYNHAP = @denngay "
					 + "\n  ) todayKbhsale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as MTDKbhsales"
					 + "\n  from BC_donhang dh "
					 + "\n  inner join      "
					 + "\n  (	     "
					 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					 + "\n  (		"
					 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"' "
//					 + "\n  	union all     "
//					 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					 + "\n  )dhsp     "
					 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
					 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <= @denngay"
					 + "\n  )MTDKbhSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as YTDKbhsales"
					 + "\n  from BC_donhang dh "
					 + "\n  inner join      "
					 + "\n  (	     "
					 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					 + "\n  (		"
					 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'     "
//					 + "\n  	union all     "
//					 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					 + "\n  )dhsp     "
					 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
					 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
					 + "\n  )YTDKbhSale,"
					 + "\n  ("
					 + "\n  select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as TotalNsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'   and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP = @denngay"
					 + "\n  ) todaNsale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as MTDNsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <= @denngay"
					 + "\n  )MTDKbhNSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI,0) ) as float)/1000000.0,1),0) as YTDNsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.DIABAN_FK is not null"
					 + "\n   and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
					 + "\n  )YTDKbhNSale,"
					 + "\n  ("
					 + "\n  select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as TotalNKbhsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP = @denngay"
					 + "\n  ) todaNKbhsale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as MTDNKbhsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <=@denngay"
					 + "\n  )MTDKbhNKbhSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as YTDNKbhsales"
					 + "\n  from BC_donhang dh "
					 + "\n "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
					 + "\n  )YTDKbhNKbhSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as MTDNGsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP >= convert(varchar(10), DATEADD(MONTH,-1,@tungay),120)  and dh.NGAYNHAP <= convert(varchar(10), DATEADD(MONTH,-1,@denngay),120)"
					 + "\n  )MTDKbhNGSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI,0) ) as float)/1000000.0,1),0) as YTDNGsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'  and dh.DIABAN_FK is not null"
					 + "\n   and year(dh.NGAYNHAP) = year(convert(varchar(10), DATEADD(MONTH,-1,@tungay),120)) and dh.NGAYNHAP <= convert(varchar(10), DATEADD(MONTH,-1,@denngay),120) "+dk
					 + "\n  )YTDKbhNGSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as MTDNKbhGsales"
					 + "\n  from BC_donhang dh "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and dh.NGAYNHAP >=  convert(varchar(10), DATEADD(MONTH,-1,@tungay),120) and dh.NGAYNHAP <= convert(varchar(10), DATEADD(MONTH,-1,@denngay),120) "
					 + "\n  )MTDKbhNKbhGSale,"
					 + "\n  ("
					 + "\n  	select isnull(round(cast( sum(ISNULL(TONGGIATRI ,0) ) as float)/1000000.0,1),0) as YTDNKbhGsales"
					 + "\n  from BC_donhang dh "
					 + "\n "
					 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' and dh.KBH_FK = 100025  and dh.DIABAN_FK is not null"
					 + "\n   and year(dh.NGAYNHAP) = year(convert(varchar(10), DATEADD(MONTH,-1,@tungay),120)) and dh.NGAYNHAP <= convert(varchar(10), DATEADD(MONTH,-1,@denngay),120) "+dk
					 + "\n  )YTDKbhNKbhGSale ";
			}
			if(type == 9)
			{
				String dk = "";	
				if(obj.getYear().equals("2015"))
					dk = "and Month(dh.NGAYNHAP) >= 7 ";
				query = 
									"\n  declare @tungay varchar(10)  = '"+obj.gettungay()+"'"
					    		   + "\n  declare @denngay varchar(10) = '"+obj.getdenngay()+"' "
					    		   + "\n select CASE  when VUNG.PK_SEQ = 100008 then 1 when   VUNG.PK_SEQ = 100002 then 2 when VUNG.PK_SEQ = 100003 then 3 else 4 end loai,"
					    		   + "isnull(Totalsales,0) as Totalsales ,isnull(MTDSales,0) as MTDSales,isnull(YTDsales,0) as YTDsales, isnull(TotalNsales,0) as TotalNsales , isnull(MTDNsales,0) as MTDNsales, isnull(YTDNsales,0) as YTDNsales from "
					    		   + "\n ("
					    		   + "\n select v.pk_seq, isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as Totalsales"
					    		   + "\n from BC_donhang dh "
					    		   + "\n inner join      "
					    		   + "\n (	     "
					    		   + "\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					    		   + "\n (		"
					    		   + "\n 	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					    		   + "\n 	union all     "
//					    		   + "\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					    		   + "\n )dhsp     "
					    		   + "\n group by DONHANG_FK,giamua,SANPHAM_fk     "
					    		   + "\n )dh_sp on dh.PK_SEQ = dh_sp.DONHANG_FK"
					    		   + "\n inner join DIABAN db on dh.DIABAN_FK = db.PK_SEQ"
					    		   + "\n inner join KHUVUC kv on kv.PK_SEQ = db.khuvuc_fk"
					    		   + "\n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					    		   + "\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
					    		   + "\n  and dh.NGAYNHAP = @denngay"
					    		   + "\n  group by v.PK_SEQ"
					    		   + "\n ) todaysale"
					    		   + "\n full outer join "
					    		   + "\n ( "
					    		   + "\n select pk_seq from vung "
					    		    + "\n ) VUNG  on VUNG.pk_seq = todaysale.pk_seq "

					    		   + " left join"
					    		   + "\n ("
					    		   + "\n 	select v.pk_seq, isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as MTDsales"
					    		   + "\n from BC_donhang dh "
					    		   + "\n inner join      "
					    		   + "\n (	     "
					    		   + "\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					    		   + "\n (		"
					    		   + "\n 	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					    		   + "\n 	union all     "
//					    		   + "\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					    		   + "\n )dhsp     "
					    		   + "\n group by DONHANG_FK,giamua,SANPHAM_fk     "
					    		   + "\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					    		   + "\n inner join DIABAN db on dh.DIABAN_FK = db.PK_SEQ"
					    		   + "\n inner join KHUVUC kv on kv.PK_SEQ = db.khuvuc_fk"
					    		   + "\n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					    		   + "\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
					    		   + "\n  and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <= @denngay"
					    		   + "\n  group by v.PK_SEQ"
					    		   + "\n )MTDSale on VUNG.PK_SEQ = MTDSale.PK_SEQ"
					    		   + "\n left join "
					    		   + "\n ("
					    		   + "\n 	select v.PK_SEQ, isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as YTDsales"
					    		   + "\n from BC_donhang dh "
					    		   + "\n inner join      "
					    		   + "\n (	     "
					    		   + "\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
					    		   + "\n (		"
					    		   + "\n 	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'      "
//					    		   + "\n 	union all     "
//					    		   + "\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null     "
					    		   + "\n )dhsp     "
					    		   + "\n group by DONHANG_FK,giamua,SANPHAM_fk     "
					    		   + "\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
					    		   + "\n inner join DIABAN db on dh.DIABAN_FK = db.PK_SEQ"
					    		   + "\n inner join KHUVUC kv on kv.PK_SEQ = db.khuvuc_fk"
					    		   + "\n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					    		   + "\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'"
					    		   + "\n  and year(dh.NGAYNHAP) = year(@tungay) and dh.NGAYNHAP <= @denngay "+dk
					    		   + "\n   group by v.PK_SEQ"
					    		   + "\n )YTDSale on VUNG.PK_SEQ = YTDSale.PK_SEQ"
					    		   + "\n left join "
					    		   + "\n ("
					    		   + "\n select v.PK_SEQ, isnull(round(cast( sum(ISNULL(tonggiatri ,0) ) as float)/1000000.0,1),0) as TotalNsales"
					    		   + "\n from BC_donhang dh "			
					    		   + "\n inner join DIABAN db on dh.DIABAN_FK = db.PK_SEQ"
					    		   + "\n inner join KHUVUC kv on kv.PK_SEQ = db.khuvuc_fk"
					    		   + "\n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					    		   + "\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"'"
					    		   + "\n  and dh.NGAYNHAP = @denngay"
					    		   + "\n      group by v.PK_SEQ"
					    		   + "\n ) todaNsale on todaNsale.PK_SEQ = VUNG.PK_SEQ"
					    		   + "\n left join"
					    		   + "\n ("
					    		   + "\n 	select v.PK_SEQ, isnull(round(cast( sum(ISNULL(tonggiatri,0) ) as float)/1000000.0,1),0) as MTDNsales"
					    		   + "\n from BC_donhang dh "
					    		   + "\n inner join DIABAN db on dh.DIABAN_FK = db.PK_SEQ"
					    		   + "\n inner join KHUVUC kv on kv.PK_SEQ = db.khuvuc_fk"
					    		   + "\n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					    		   + "\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
					    		   + "\n  and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <= @denngay"
					    		   + "\n   group by v.PK_SEQ"
					    		   + "\n )MTDKbhNSale on MTDKbhNSale.PK_SEQ = VUNG.PK_SEQ"
					    		   + "\n left join "
					    		   + "\n ("
					    		   + "\n 	select v.PK_SEQ, isnull(round(cast( sum(ISNULL(tonggiatri ,0) ) as float)/1000000.0,1),0) as YTDNsales"
					    		   + "\n from BC_donhang dh "
					    		   + "\n inner join DIABAN db on dh.DIABAN_FK = db.PK_SEQ"
					    		   + "\n inner join KHUVUC kv on kv.PK_SEQ = db.khuvuc_fk"
					    		   + "\n inner join VUNG v on v.PK_SEQ = kv.VUNG_FK"
					    		   + "\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
					    		   + "\n  and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
					    		   + "\n    group by v.PK_SEQ"
					    		   + "\n )YTDKbhNSale on VUNG.PK_SEQ = YTDKbhNSale.PK_SEQ"
					    		   + "\n order by loai ";
			}
			if(type == 10)
			{
				String dk = "";	
				if(obj.getYear().equals("2015"))
					dk = "and Month(dh.NGAYNHAP) >= 7 ";
				query = " declare @tungay varchar(10)  = '"+obj.gettungay()+"'" 
						 +"\ndeclare @denngay varchar(10) = '"+obj.getdenngay()+"' " 
						 +"\n select * from "
						 +"\n ("
						 +"\n select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as Totalsales"
						 +"\n from BC_donhang dh "
						 +"\n inner join      "
						 +"\n (	     "
						 +"\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 +"\n (		"
						 +"\n 	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'  and SANPHAM_FK in ( select a.sp_fk  from NHOMSANPHAMCHITIEU_SANPHAM a inner join NHOMSANPHAMCHITIEU b on a.NSP_FK = b.PK_SEQ where 1 = 1 and b.trangthai  = 1 and (('"+obj.getdenngay()+"' <= Denngay and TUNGAY <= '"+obj.getdenngay()+"') "+
						"\n) "+
						"\n	 and NHOMSPTT = '1'   )"
//						 +"\n 	union all     "
//						 +"\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select ma from SANPHAMFOCUS)     "
						 +"\n )dhsp     "
						 +"\n group by DONHANG_FK,giamua,SANPHAM_fk     "
						 +"\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 +"\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' AND DH.DIABAN_FK IS NOT NULL  "
						 +"\n  and dh.NGAYNHAP = @denngay "
						 +"\n ) todaysale,"
						 +"\n ("
						 +"\n 	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as MTDsales"
						 +"\n from BC_donhang dh "
						 +"\n inner join      "
						 +"\n (	     "
						 +"\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 +"\n (		"
						 +"\n 		select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'and SANPHAM_FK in ( select a.sp_fk  from NHOMSANPHAMCHITIEU_SANPHAM a inner join NHOMSANPHAMCHITIEU b on a.NSP_FK = b.PK_SEQ where 1 = 1 and b.trangthai  = 1 and (('"+obj.getdenngay()+"' <= Denngay and TUNGAY <= '"+obj.getdenngay()+"') "+
						"\n ) "+
						"\n	 and NHOMSPTT = '1'   )"
//						 +"\n 	union all     "
//						 +"\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select ma from SANPHAMFOCUS)     "
						 +"\n )dhsp     "
						 +"\n group by DONHANG_FK,giamua,SANPHAM_fk     "
						 +"\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 +"\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' AND DH.DIABAN_FK IS NOT NULL "
						 +"\n  and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <=@denngay"
						 +"\n )MTDSale,"
						 +"\n ("
						 +"\n 	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as YTDsales"
						 +"\n from BC_donhang dh "
						 +"\n inner join      "
						 +"\n (	     "
						 +"\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 +"\n (		"
						 +"\n 		select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'  and SANPHAM_FK in ( select a.sp_fk  from NHOMSANPHAMCHITIEU_SANPHAM a inner join NHOMSANPHAMCHITIEU b on a.NSP_FK = b.PK_SEQ where 1 = 1 and b.trangthai  = 1 and (('"+obj.getdenngay()+"' <= Denngay and TUNGAY <= '"+obj.getdenngay()+"') "+
						"\n ) "+
						"\n	 and NHOMSPTT = '1'   )"
//						 +"\n 	union all     "
//						 +"\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select ma from SANPHAMFOCUS)     "
						 +"\n )dhsp     "
						 +"\n group by DONHANG_FK,giamua,SANPHAM_fk     "
						 +"\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 +"\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' AND DH.DIABAN_FK IS NOT NULL "
						 +"\n  and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
						 +"\n )YTDSale,"
						 +"\n ("
						 +"\n select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as TotalKbhsales"
						 +"\n from BC_donhang dh "
						 +"\n inner join      "
						 +"\n (	     "
						 +"\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 +"\n (		"
						 +"\n 		select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'  and SANPHAM_FK in ( select a.sp_fk  from NHOMSANPHAMCHITIEU_SANPHAM a inner join NHOMSANPHAMCHITIEU b on a.NSP_FK = b.PK_SEQ where 1 = 1 and b.trangthai  = 1 and (('"+obj.getdenngay()+"' <= Denngay and TUNGAY <= '"+obj.getdenngay()+"') "+
						"\n ) "+
						"\n	 and NHOMSPTT = '1'   )"
//						 +"\n 	union all     "
//						 +"\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select ma from SANPHAMFOCUS)     "
						 +"\n )dhsp     "
						 +"\n group by DONHANG_FK,giamua,SANPHAM_fk     "
						 +"\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 +"\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' AND DH.DIABAN_FK IS NOT NULL  and dh.KBH_FK = 100025"
						 +"\n  and dh.NGAYNHAP = @denngay "
						 +"\n ) todayKbhsale,"
						 +"\n ("
						 +"\n 	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as MTDKbhsales"
						 +"\n from BC_donhang dh "
						 +"\n inner join      "
						 +"\n (	     "
						 +"\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 +"\n (		"
						 +"\n 		select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'  and SANPHAM_FK in (  select a.sp_fk  from NHOMSANPHAMCHITIEU_SANPHAM a inner join NHOMSANPHAMCHITIEU b on a.NSP_FK = b.PK_SEQ where 1 = 1 and b.trangthai  = 1 and (('"+obj.getdenngay()+"' <= Denngay and TUNGAY <= '"+obj.getdenngay()+"')"+
						"\n ) "+
						"\n	 and NHOMSPTT = '1'   )"
//						 +"\n 	union all     "
//						 +"\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select ma from SANPHAMFOCUS)     "
						 +"\n )dhsp     "
						 +"\n group by DONHANG_FK,giamua,SANPHAM_fk     "
						 +"\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 +"\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' AND DH.DIABAN_FK IS NOT NULL  and dh.KBH_FK = 100025"
						 +"\n  and dh.NGAYNHAP >= @tungay and dh.NGAYNHAP <= @denngay"
						 +"\n )MTDKbhSale,"
						 +"\n ("
						 +"\n 	select isnull(round(cast( sum(ISNULL(soluong*giamua ,0) ) as float)/1000000.0,1),0) as YTDKbhsales"
						 +"\n from BC_donhang dh "
						 +"\n inner join      "
						 +"\n (	     "
						 +"\n select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 +"\n (		"
						 +"\n 		select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'  and SANPHAM_FK in ( select a.sp_fk  from NHOMSANPHAMCHITIEU_SANPHAM a inner join NHOMSANPHAMCHITIEU b on a.NSP_FK = b.PK_SEQ where 1 = 1 and b.trangthai  = 1 and (('"+obj.getdenngay()+"' <= Denngay and TUNGAY <= '"+obj.getdenngay()+"') "+
						"\n ) "+
						"\n	 and NHOMSPTT = '1'   )"
//						 +"\n 	union all     "
//						 +"\n 	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select ma from SANPHAMFOCUS)     "
						 +"\n )dhsp     "
						 +"\n group by DONHANG_FK,giamua,SANPHAM_fk     "
						 +"\n )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 +"\n where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' AND DH.DIABAN_FK IS NOT NULL  and dh.KBH_FK = 100025"
						 +"\n  and year(dh.NGAYNHAP) = year(@denngay) and dh.NGAYNHAP <= @denngay "+dk
						 +"\n )YTDKbhSale";

			}
		return query;
	}


	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();

		Utility util = new Utility();
		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);


		String view=request.getParameter("view");
		if(view == null)
			view = "";

		obj.setLoaiMenu(view);

		obj.setuserId(userId);
			

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);

		String nextJSP = request.getContextPath() + "/pages/Center/DmsDashBoard.jsp";
		response.sendRedirect(nextJSP);
	}


	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility  util = new Utility();

		String userId = (String) session.getAttribute("userId");
		if (userId == null)
			userId = "";
		obj.setuserId(userId);

		String userTen = (String) session.getAttribute("userTen");
		obj.setuserTen(userTen);

		String denngay = util.antiSQLInspection(request.getParameter("denngay"));
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);
		
		if(denngay.length() > 0)
		{
			String thang = denngay.substring(5,7);
			if (thang == null)
				thang = "0";
			obj.setMonth(thang);
			System.out.println("Thang "+thang);
			String tungay = denngay.substring(0,7) + "-01";
			System.out.println("Tu Ngay "+tungay);
			obj.settungay(tungay);
		}
		
		if(denngay.length() > 0)
		{
			String nam = denngay.substring(0,4);
			if (nam == null)
				nam = "0";
			obj.setYear(nam);
			System.out.println("Nam "+nam);
		}
		
		

		String action = request.getParameter("action");
		if (action.equals("tao")) 
		{
			boolean kt = true;
			try 
			{
				dbutils db = new dbutils();
				Xoa(obj,db,userId);
				String sql = " INSERT INTO [BC_donhang]"
						 +"\n           ([PK_SEQ]"
						 +"\n           ,[KHACHHANG_FK]"
						 +"\n           ,[NGAYNHAP]"
						 +"\n           ,[TRANGTHAI]"
						 +"\n           ,[TONGGIATRI]"
						 +"\n           ,[DDKD_FK]"
						 +"\n           ,[DIABAN_FK],"
						 + "			NPP_FK,UserID,KBH_FK)"
						 +"\n          SELECT  [PK_SEQ],[KHACHHANG_FK],[NGAYNHAP],[TRANGTHAI],[TONGGIATRI],[DDKD_FK],[DIABAN_FK],NPP_FK,'"+userId+"',KBH_FK "
						 +"\n          FROM DONHANG"
						 +"\n          WHERE year(NGAYNHAP) = year('"+obj.getdenngay()+"')  and NGAYNHAP <= '"+obj.getdenngay()+"' and trangthai = 1";
			
				
				if(!db.update(sql))
				{
					System.out.println("1.Insert BC_donhang: "+sql);
					request.getSession().setAttribute("errors", "Lỗi không tạo được báo cáo !");
					kt = false;
				}
				
				
				sql = "	if(MONTH('"+obj.getdenngay()+"') = 1)	"
						+ " INSERT INTO [BC_donhang]"
						 +"\n           ([PK_SEQ]"
						 +"\n           ,[KHACHHANG_FK]"
						 +"\n           ,[NGAYNHAP]"
						 +"\n           ,[TRANGTHAI]"
						 +"\n           ,[TONGGIATRI]"
						 +"\n           ,[DDKD_FK]"
						 +"\n           ,[DIABAN_FK],"
						 + "			NPP_FK,UserID,KBH_FK)"
						 +"\n          SELECT  [PK_SEQ],[KHACHHANG_FK],[NGAYNHAP],[TRANGTHAI],[TONGGIATRI],[DDKD_FK],[DIABAN_FK],NPP_FK,'"+userId+"',KBH_FK "
						 +"\n          FROM DONHANG"
						 +"\n          WHERE year(NGAYNHAP) = year('"+obj.getdenngay()+"')-1  and MONTH(Ngaynhap) = 12 and trangthai = 1";
			
				
				if(!db.update(sql))
				{
					System.out.println("1.Insert BC_donhang: "+sql);
					request.getSession().setAttribute("errors", "Lỗi không tạo được báo cáo !");
					kt = false;
				}
				
				
				
				sql = " INSERT INTO [BC_DONHANG_SANPHAM]"
						 +"\n           ([SANPHAM_FK]"
						 +"\n           ,[DONHANG_FK]"
						 +"\n           ,[KHO_FK]"
						 +"\n           ,[GIAMUA]"
						 +"\n           ,[SOLUONG],userid)"
						 +"\n      SELECT  b.SANPHAM_FK,a.PK_SEQ,b.KHO_FK,b.GIAMUA,b.SOLUONG,a.userid "
						 +"\n          FROM BC_donhang a inner join DONHANG_SANPHAM b on a.PK_SEQ = b.DONHANG_FK"
						 +"\n          where year(a.NGAYNHAP) = year('"+obj.getdenngay()+"')  and a.NGAYNHAP <= '"+obj.getdenngay()+"' and a.userid = '"+userId+"'";
				
				if(!db.update(sql))
				{
					System.out.println("2.Insert BC_DONHANG_SANPHAM: "+sql);
					request.getSession().setAttribute("errors", "Lỗi không tạo được báo cáo !");
					kt = false;
				}
			
				sql = "	if(MONTH('"+obj.getdenngay()+"') = 1)	"+
						" INSERT INTO [BC_DONHANG_SANPHAM]"
						 +"\n           ([SANPHAM_FK]"
						 +"\n           ,[DONHANG_FK]"
						 +"\n           ,[KHO_FK]"
						 +"\n           ,[GIAMUA]"
						 +"\n           ,[SOLUONG],userid)"
						 +"\n      SELECT  b.SANPHAM_FK,a.PK_SEQ,b.KHO_FK,b.GIAMUA,b.SOLUONG,a.userid "
						 +"\n          FROM BC_donhang a inner join DONHANG_SANPHAM b on a.PK_SEQ = b.DONHANG_FK"
						 +"\n          where year(a.NGAYNHAP) = year('"+obj.getdenngay()+"') - 1  and MONTH(Ngaynhap) = 12 and a.userid = '"+userId+"'";
				
				if(!db.update(sql))
				{
					System.out.println("2.Insert BC_DONHANG_SANPHAM: "+sql);
					request.getSession().setAttribute("errors", "Lỗi không tạo được báo cáo !");
					kt = false;
				}

				request.setCharacterEncoding("utf-8");

				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=DmsDashBoard.xlsm");
				if(kt)
				{
					OutputStream out = response.getOutputStream();
					ExportToExcel(out, obj);
					Xoa(obj,db,userId);
					
					
				}


			} 
			catch (Exception ex) 
			{
				ex.printStackTrace();
				request.getSession().setAttribute("errors", ex.getMessage());
			}
		}
		else
		{
			

			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			String nextJSP = request.getContextPath() + "/pages/Center/DmsDashBoard.jsp";
			response.sendRedirect(nextJSP);

		}

	}

	private boolean Xoa(IStockintransit obj, dbutils db,String userId )throws Exception
	{
		boolean kt =true;
		String	sql = "delete BC_DONHANG_SANPHAM from BC_donhang a inner join BC_DONHANG_SANPHAM b on a.Pk_seq = b.donhang_FK "
				+ "where year(a.NGAYNHAP) = year('"+obj.getdenngay()+"') and a.NGAYNHAP <= '"+obj.getdenngay()+"' and a.userid = '"+userId+"'";
		if(!db.update(sql))
		{
			System.out.println("4.delete BC_DONHANG_SANPHAM: "+sql);
			
			kt = false;
		}
		
		
		sql = "	if(MONTH('"+obj.getdenngay()+"') = 1)	"+
				"delete BC_DONHANG_SANPHAM from BC_donhang a inner join BC_DONHANG_SANPHAM b on a.Pk_seq = b.donhang_FK "
				+ "where year(a.NGAYNHAP) = year('"+obj.getdenngay()+"')-1 and Month(a.NGAYNHAP) = 12 and a.userid = '"+userId+"'";
		if(!db.update(sql))
		{
			System.out.println("4.delete BC_DONHANG_SANPHAM: "+sql);
			
			kt = false;
		}
		
		sql = 	"	if(MONTH('"+obj.getdenngay()+"') = 1)	"+
				"delete BC_donhang where year(NGAYNHAP) = year('"+obj.getdenngay()+"')-1 and Month(NGAYNHAP) = 12 and userid = '"+userId+"'";
		if(!db.update(sql))
		{
			System.out.println("6.delete BC_donhang: "+sql);
			
			kt = false;
		}
		
		sql = " delete BC_donhang where year(NGAYNHAP) = year('"+obj.getdenngay()+"')  and NGAYNHAP <= '"+obj.getdenngay()+"' and userid = '"+userId+"'";
		if(!db.update(sql))
		{
			System.out.println("6.delete BC_donhang: "+sql);
			
			kt = false;
		}
		return kt;
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}




	
	public static String GetExcelColumnName(int columnNumber)
	{
		int dividend = columnNumber;
		String columnName = "";
		int modulo;

		while (dividend > 0)
		{
			modulo = (dividend - 1) % 26;
			columnName = (char)(65 + modulo) + columnName;
			dividend = (int)((dividend - modulo) / 26);
		} 

		return columnName;
	}
	

	private void TaoBaoCao(Workbook workbook,IStockintransit obj,String query,int sheetNum )throws Exception
	{
		try{
			
			
			Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			Cells cells = worksheet.getCells();
			Cell cell;
			dbutils db = new dbutils();
			ResultSet rs = db.get(query);
			cell = cells.getCell("A3");
			cell.setValue("Ngày Tạo: "+getDateTime());
			
			cell = cells.getCell("A4");
			cell.setValue("Ngày Báo Cáo: "+obj.getdenngay());
			
			double mek = 0,sou = 0,cen = 0,nor = 0;
			int i = 16;
			while(rs.next())
			{
				// số kh phải đi
				if(sheetNum == 0)
				{
					if(rs.getString("vung").equals("100001"))
					{
						nor = rs.getDouble("sokhphaidi");
						cell = cells.getCell("B30");
						cell.setValue(nor);
						nor = rs.getDouble("sokhthucte");
						cell = cells.getCell("C30");
						cell.setValue(nor);
						nor = rs.getDouble("sodonhang");
						cell = cells.getCell("D30");
						cell.setValue(nor);
						
					}
					if(rs.getString("vung").equals("100002"))
					{
						sou = rs.getDouble("sokhphaidi");
						cell = cells.getCell("B28");
						cell.setValue(sou);
						sou = rs.getDouble("sokhthucte");
						cell = cells.getCell("C28");
						cell.setValue(sou);
						sou = rs.getDouble("sodonhang");
						cell = cells.getCell("D28");
						cell.setValue(sou);
						
					}
					if(rs.getString("vung").equals("100003"))
					{
						cen = rs.getDouble("sokhphaidi");
						cell = cells.getCell("B29");
						cell.setValue(cen);
						cen = rs.getDouble("sokhthucte");
						cell = cells.getCell("C29");
						cell.setValue(cen);
						cen = rs.getDouble("sodonhang");
						cell = cells.getCell("D29");
						cell.setValue(cen);
						
					}
					if(rs.getString("vung").equals("100008"))
					{
						mek = rs.getDouble("sokhphaidi");
						cell = cells.getCell("B27");
						cell.setValue(mek);
						mek = rs.getDouble("sokhthucte");
						cell = cells.getCell("C27");
						cell.setValue(mek);
						mek = rs.getDouble("sodonhang");
						cell = cells.getCell("D27");
						cell.setValue(mek);
						
					}
				}
				if(sheetNum == 1)
				{
					if(rs.getString("vung").equals("100001"))
					{
						nor = rs.getDouble("sokhphaidi");
						cell = cells.getCell("I30");
						cell.setValue(nor);
						nor = rs.getDouble("sokhthucte");
						cell = cells.getCell("J30");
						cell.setValue(nor);
						nor = rs.getDouble("sodonhang");
						cell = cells.getCell("K30");
						cell.setValue(nor);
						
					}
					if(rs.getString("vung").equals("100002"))
					{
						sou = rs.getDouble("sokhphaidi");
						cell = cells.getCell("I28");
						cell.setValue(sou);
						sou = rs.getDouble("sokhthucte");
						cell = cells.getCell("J28");
						cell.setValue(sou);
						sou = rs.getDouble("sodonhang");
						cell = cells.getCell("K28");
						cell.setValue(sou);
						
					}
					if(rs.getString("vung").equals("100003"))
					{
						cen = rs.getDouble("sokhphaidi");
						cell = cells.getCell("I29");
						cell.setValue(cen);
						cen = rs.getDouble("sokhthucte");
						cell = cells.getCell("J29");
						cell.setValue(cen);
						cen = rs.getDouble("sodonhang");
						cell = cells.getCell("K29");
						cell.setValue(cen);
						
					}
					if(rs.getString("vung").equals("100008"))
					{
						mek = rs.getDouble("sokhphaidi");
						cell = cells.getCell("I27");
						cell.setValue(mek);
						mek = rs.getDouble("sokhthucte");
						cell = cells.getCell("J27");
						cell.setValue(mek);
						mek = rs.getDouble("sodonhang");
						cell = cells.getCell("K27");
						cell.setValue(mek);
						
					}
				}
				// Biểu đồ thứ 3
				if(sheetNum == 2)
				{
					if(rs.getString("vung").equals("100001"))
					{
						nor = rs.getDouble("sotien");
						cell = cells.getCell("C53");
						cell.setValue(nor);
						
						nor = rs.getDouble("sodonhang");
						cell = cells.getCell("B53");
						cell.setValue(nor);
						
					}
					if(rs.getString("vung").equals("100002"))
					{
						sou = rs.getDouble("sotien");
						cell = cells.getCell("C51");
						cell.setValue(sou);
					
						sou = rs.getDouble("sodonhang");
						cell = cells.getCell("B51");
						cell.setValue(sou);
						
					}
					if(rs.getString("vung").equals("100003"))
					{
						cen = rs.getDouble("sotien");
						cell = cells.getCell("C52");
						cell.setValue(cen);
						
						cen = rs.getDouble("sodonhang");
						cell = cells.getCell("B52");
						cell.setValue(cen);
						
					}
					if(rs.getString("vung").equals("100008"))
					{
						mek = rs.getDouble("sotien");
						cell = cells.getCell("C50");
						cell.setValue(mek);
						
						mek = rs.getDouble("sodonhang");
						cell = cells.getCell("B50");
						cell.setValue(mek);
						
					}
				}
				
				// Biểu đồ thứ 4
				if(sheetNum == 3)
				{
					if(rs.getString("vung").equals("100001"))
					{
						nor = rs.getDouble("sotien");
						cell = cells.getCell("I53");
						cell.setValue(nor);
						
						nor = rs.getDouble("sodonhang");
						cell = cells.getCell("H53");
						cell.setValue(nor);
						nor = rs.getDouble("tbthangtruoc");
						cell = cells.getCell("J53");
						cell.setValue(nor);
						
						
					}
					if(rs.getString("vung").equals("100002"))
					{
						sou = rs.getDouble("sotien");
						cell = cells.getCell("I51");
						cell.setValue(sou);
					
						sou = rs.getDouble("sodonhang");
						cell = cells.getCell("H51");
						cell.setValue(sou);
						
						sou = rs.getDouble("tbthangtruoc");
						cell = cells.getCell("J51");
						cell.setValue(sou);
						
					}
					if(rs.getString("vung").equals("100003"))
					{
						cen = rs.getDouble("sotien");
						cell = cells.getCell("I52");
						cell.setValue(cen);
						
						cen = rs.getDouble("sodonhang");
						cell = cells.getCell("H52");
						cell.setValue(cen);
						
						cen = rs.getDouble("tbthangtruoc");
						cell = cells.getCell("J52");
						cell.setValue(cen);
					}
					if(rs.getString("vung").equals("100008"))
					{
						mek = rs.getDouble("sotien");
						cell = cells.getCell("I50");
						cell.setValue(mek);
						
						mek = rs.getDouble("sodonhang");
						cell = cells.getCell("H50");
						cell.setValue(mek);
						
						mek = rs.getDouble("tbthangtruoc");
						cell = cells.getCell("J50");
						cell.setValue(mek);
					}
				}
				if(sheetNum == 4)
				{
					double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
					soddkd1 = rs.getDouble("SoDDKD1");
					cell = cells.getCell("B74");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("SoDDKD2");
					cell = cells.getCell("B75");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("SoDDKD3");
					cell = cells.getCell("B76");
					cell.setValue(soddkd3);
				}
				
				if(sheetNum == 5)
				{
					double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
					soddkd1 = rs.getDouble("SoDDKD1");
					cell = cells.getCell("H74");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("SoDDKD2");
					cell = cells.getCell("H75");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("SoDDKD3");
					cell = cells.getCell("H76");
					cell.setValue(soddkd3);
					
					soddkd1 = rs.getDouble("SoDDKDM1");
					cell = cells.getCell("I74");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("SoDDKDM2");
					cell = cells.getCell("I75");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("SoDDKDM3");
					cell = cells.getCell("I76");
					cell.setValue(soddkd3);
				}
				
				if(sheetNum == 6)
				{
					double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
					soddkd1 = rs.getDouble("SoDDKD1");
					cell = cells.getCell("B97");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("SoDDKD2");
					cell = cells.getCell("B98");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("SoDDKD3");
					cell = cells.getCell("B99");
					cell.setValue(soddkd3);
					
				}
				
				if(sheetNum == 7)
				{
					double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
					soddkd1 = rs.getDouble("SoDDKD1");
					cell = cells.getCell("H97");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("SoDDKD2");
					cell = cells.getCell("H98");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("SoDDKD3");
					cell = cells.getCell("H99");
					cell.setValue(soddkd3);
					
					
					soddkd1 = rs.getDouble("SoDDKDM1");
					cell = cells.getCell("I97");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("SoDDKDM2");
					cell = cells.getCell("I98");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("SoDDKDM3");
					cell = cells.getCell("I99");
					cell.setValue(soddkd3);
					
				}
				// Gross sales  and Net sales
				if(sheetNum == 8)
				{
					double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
					soddkd1 = rs.getDouble("Totalsales");
					cell = cells.getCell("C8");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("MTDsales");
					cell = cells.getCell("D8");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("YTDsales");
					cell = cells.getCell("E8");
					cell.setValue(soddkd3);
					
					
					soddkd1 = rs.getDouble("TotalKbhsales");
					cell = cells.getCell("C9");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("MTDKbhsales");
					cell = cells.getCell("D9");
					cell.setValue(soddkd2);
					
					soddkd3 = rs.getDouble("YTDKbhsales");
					cell = cells.getCell("E9");
					cell.setValue(soddkd3);
					
					soddkd1 = rs.getDouble("TotalNsales")/1.05;
					cell = cells.getCell("G8");
					cell.setValue(soddkd1);
					
					
					// tru thue
					soddkd2 = rs.getDouble("MTDNsales")/1.05;
					cell = cells.getCell("H8");
					cell.setValue(soddkd2);
					double gr = soddkd2;
					soddkd3 = rs.getDouble("YTDNsales")/1.05;
					cell = cells.getCell("I8");
					cell.setValue(soddkd3);
					
					soddkd1 = rs.getDouble("TotalNKbhsales")/1.05;
					cell = cells.getCell("G9");
					cell.setValue(soddkd1);
					
					soddkd2 = rs.getDouble("MTDNKbhsales")/1.05;
					cell = cells.getCell("H9");
					cell.setValue(soddkd2);
					double gr1 = soddkd2;
					soddkd3 = rs.getDouble("YTDNKbhsales")/1.05;
					cell = cells.getCell("I9");
					cell.setValue(soddkd3);
					
					
					soddkd2 = rs.getDouble("MTDNGsales")/1.05;
					cell = cells.getCell("J8");
					cell.setValue((gr/soddkd2));
					System.out.println("grow "+rs.getDouble("MTDNGsales")+" "+gr);
					
					soddkd3 = rs.getDouble("MTDNKbhGsales")/1.05;
					cell = cells.getCell("J9");
					cell.setValue((gr1/soddkd3));
					System.out.println("grow "+rs.getDouble("MTDNKbhGsales")+" "+gr);
				
					
					
				}
			
			
			if(sheetNum == 9)
			{
				double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
				soddkd1 = rs.getDouble("Totalsales");
				cell = cells.getCell("C"+i);
				cell.setValue(soddkd1);
				
				soddkd2 = rs.getDouble("MTDsales");
				cell = cells.getCell("D"+i);
				cell.setValue(soddkd2);
				
				soddkd3 = rs.getDouble("YTDsales");
				cell = cells.getCell("E"+i);
				cell.setValue(soddkd3);
				
				soddkd1 = rs.getDouble("TotalNsales")/1.05;
				cell = cells.getCell("H"+i);
				cell.setValue(soddkd1);
				
				soddkd2 = rs.getDouble("MTDNsales")/1.05;
				cell = cells.getCell("I"+i);
				cell.setValue(soddkd2);
				
				soddkd3 = rs.getDouble("YTDNsales")/1.05;
				cell = cells.getCell("J"+i);
				cell.setValue(soddkd3);
				i++;
			}
			
			
			if(sheetNum == 10)
			{
				double soddkd1 = 0,soddkd2 = 0,soddkd3 = 0;
				soddkd1 = rs.getDouble("Totalsales");
				cell = cells.getCell("C11");
				cell.setValue(soddkd1);
				
				soddkd2 = rs.getDouble("MTDsales");
				cell = cells.getCell("D11");
				cell.setValue(soddkd2);
				
				soddkd3 = rs.getDouble("YTDsales");
				cell = cells.getCell("E11");
				cell.setValue(soddkd3);
				
				
				soddkd1 = rs.getDouble("TotalKbhsales");
				cell = cells.getCell("C12");
				cell.setValue(soddkd1);
				
				soddkd2 = rs.getDouble("MTDKbhsales");
				cell = cells.getCell("D12");
				cell.setValue(soddkd2);
				
				soddkd3 = rs.getDouble("YTDKbhsales");
				cell = cells.getCell("E12");
				cell.setValue(soddkd3);
				
//				soddkd1 = rs.getDouble("TotalNsales");
//				cell = cells.getCell("G11");
//				cell.setValue(soddkd1);
//				
//				soddkd2 = rs.getDouble("MTDNsales");
//				cell = cells.getCell("H11");
//				cell.setValue(soddkd2);
//				double gr = soddkd2;
//				soddkd3 = rs.getDouble("YTDNsales");
//				cell = cells.getCell("I11");
//				cell.setValue(soddkd3);
//				
//				soddkd1 = rs.getDouble("TotalNKbhsales");
//				cell = cells.getCell("G12");
//				cell.setValue(soddkd1);
//				
//				soddkd2 = rs.getDouble("MTDNKbhsales");
//				cell = cells.getCell("H12");
//				cell.setValue(soddkd2);
//				double gr1 = soddkd2;
//				soddkd3 = rs.getDouble("YTDNKbhsales");
//				cell = cells.getCell("I12");
//				cell.setValue(soddkd3);
//				
//				
//				
//				soddkd2 = rs.getDouble("MTDNGsales");
//				cell = cells.getCell("J11");
//				cell.setValue((soddkd2/gr));
//				
//				
//				soddkd3 = rs.getDouble("MTDNKbhGsales");
//				cell = cells.getCell("J12");
//				cell.setValue((soddkd3/gr1));
				
				String sql =  "\n declare @denngay varchar(10) = '"+obj.getdenngay()+"' "
						 + "\n declare @songaylamviec float =  (select songaylamviec from chitieunhanvien where THANG =  MONTH(@denngay) and Nam = YEAR(@denngay))"
						 + "\n select * from "
						 + "\n  ("
						 + "\n  select ddkd.TEN"
						 + "\n  from BC_donhang dh "
						 + "\n  inner join      "
						 + "\n  (	     "
						 + "\n  select DONHANG_FK,giamua,SANPHAM_fk,SUM(soluong)soluong from     "
						 + "\n  (		"
						 + "\n  	select DONHANG_FK,SANPHAM_FK,soluong,giamua from BC_DONHANG_SANPHAM  where userid = '"+obj.getuserId()+"'  and SANPHAM_FK in ( select b.SP_FK from NHOMSANPHAMCHITIEU a inner join NHOMSANPHAMCHITIEU_sanpham b on a.PK_SEQ = b.NSP_FK where a.TRANGTHAI = 1     )"
						 + "\n  	union all     "
						 + "\n  	select donhangid,(select pk_seq from sanpham where ma =spma),soluong,isnull(dongia,0) from DONHANG_CTKM_TRAKM_BC where SPMA is not null and SPMA in (select c.MA from NHOMSANPHAMCHITIEU a inner join NHOMSANPHAMCHITIEU_sanpham b on a.PK_SEQ = b.NSP_FK inner join SANPHAM c on b.SP_FK = c.PK_SEQ where a.TRANGTHAI = 1)     "
						 + "\n  )dhsp     "
						 + "\n  group by DONHANG_FK,giamua,SANPHAM_fk     "
						 + "\n  )dh_sp on dh.PK_SEQ =dh_sp.DONHANG_FK"
						 + "\n  inner join DIABAN db on db.PK_SEQ = dh.DIABAN_FK"
						 + "\n  inner join DAIDIENKINHDOANH ddkd on ddkd.diaban_fk = db.PK_SEQ"
						 + "\n  inner join ChiTieuNhanVien_SR ctnv on ctnv.DDKD_FK = ddkd.PK_SEQ "
						 + "\n  where dh.TRANGTHAI = 1 and dh.userid = '"+obj.getuserId()+"' "
						 + "\n   and dh.NGAYNHAP = @denngay and ctnv.THANG = MONTH(@denngay) and ctnv.Nam = YEAR(@denngay) "
						 + "\n   group by ddkd.TEN,ctnv.DoanhSoBanRa"
						 + "\n   having isnull(cast( sum(ISNULL(soluong*giamua ,0) ) as float),0) >= (ctnv.DoanhSoBanRa/@songaylamviec)"
						 + "\n  ) todaysale";
				System.out.println("Get NVBH "+sql);
				ResultSet TdvRs = db.get(sql);
				cell = cells.getCell("A107");
				cell.setValue("Tên NVBH đạt chỉ tiêu");
				int k = 108;
				if(TdvRs != null)
				{
					while(TdvRs.next())
					{
						cell = cells.getCell("A"+k);
						cell.setValue(TdvRs.getString("Ten"));
						k++;
					}
					TdvRs.close();
				}
				sql =  	 "\n declare @denngay varchar(10) = '"+obj.getdenngay()+"' "
						+ "		select b.ten as tentdv,gsbh.TEN as asm "
						+ "\n 	from KEHOACHNV_NGAYNGHI  a "
						+ "\n	 inner join DAIDIENKINHDOANH b on a.DDKD_FK = b.PK_SEQ "
						+ "\n	 inner join DDKD_GSBH kdgh on kdgh.DDKD_FK = b.PK_SEQ "
						+ "\n	 inner join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = kdgh.GSBH_FK "
						 + "\n 	where NGAY = @denngay";
				System.out.println("Get NVBH Nghi "+sql);
				TdvRs = db.get(sql);
				cell = cells.getCell("B107");
				cell.setValue("Sales reps off");
				cell = cells.getCell("C107");
				cell.setValue("ASM");
				k = 108;
				if(TdvRs != null)
				{
					while(TdvRs.next())
					{
						cell = cells.getCell("B"+k);
						cell.setValue(TdvRs.getString("tentdv"));
						
						cell = cells.getCell("C"+k);
						cell.setValue(TdvRs.getString("asm"));
						k++;
					}
					TdvRs.close();
				}
				// Sinh nhật
				sql =  	 "\n declare @denngay varchar(10) = '"+obj.getdenngay()+"' "
						+ "		select b.ten as tentdv,gsbh.TEN as asm "
						+ "\n 	from  "
						+ "\n	 DAIDIENKINHDOANH b on a.DDKD_FK = b.PK_SEQ "
						+ "\n	 inner join DDKD_GSBH kdgh on kdgh.DDKD_FK = b.PK_SEQ "
						+ "\n	 inner join GIAMSATBANHANG gsbh on gsbh.PK_SEQ = kdgh.GSBH_FK "
						 + "\n 	where b.NGAYSINH = @denngay";
				System.out.println("Get Sinh Nhat NVBH: "+sql);
				TdvRs = db.get(sql);
				cell = cells.getCell("D107");
				cell.setValue("Sale rep birthday");
				cell = cells.getCell("E107");
				cell.setValue("ASM");
				k = 108;
				if(TdvRs != null)
				{
					while(TdvRs.next())
					{
						cell = cells.getCell("B"+k);
						cell.setValue(TdvRs.getString("tentdv"));
						
						cell = cells.getCell("C"+k);
						cell.setValue(TdvRs.getString("asm"));
						k++;
					}
					TdvRs.close();
				}
			}
			
			}
		}catch(Exception ex){
			
			ex.printStackTrace();
			throw new Exception("Qua trinh dien du lieu vao file Excel va tao PivotTable bi loi.!!!");
		}
	}
	
	private void ExportToExcel(OutputStream out,IStockintransit obj )throws Exception
	 {
		try{ 			
			
			FileInputStream fstream = new FileInputStream(getServletContext().getInitParameter("path") + "\\DmsDashboard.xlsm");
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			for (int i = 0; i <= 10; i++) // ve 6 sheet
			{
				String query = setQuerySoDonHang(obj,i);
				System.out.println("BC:"+i+" "+query);
				TaoBaoCao(workbook,obj,query,i);	
			}
			workbook.save(out);
			fstream.close();
			

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
		
	}
	
}
	