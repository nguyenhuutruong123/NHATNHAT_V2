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
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;
import com.itextpdf.text.Document;

import java.util.*;

public class SecondarySalesPIRTT extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SecondarySalesPIRTT()
	{
		super();
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

		obj.setuserId(userId);
		obj.setdiscount("0");
		obj.setvat("0");
		obj.setdvdlId("");
		obj.settype("1");
		obj.init();
		
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/SecondarySalesPurchaseInventoryReport.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");
		// OutputStream out = response.getOutputStream();
		if (userId == null)
			userId = "";
		obj.setuserId(userId);
		
		Utility util =new Utility();

		String nppId = request.getParameter("nppId");
		obj.setnppId(nppId);
		obj.setuserTen(userTen);

		String vungId = request.getParameter("vungId"); 
		if (vungId == null)
			vungId = "";
		obj.setvungId(vungId);

		String khuvucId = request.getParameter("khuvucId"); 
		if (khuvucId == null)
			khuvucId = "";
		obj.setkhuvucId(khuvucId);

		String kenhId = request.getParameter("kenhId"); 
		if (kenhId == null)
			kenhId = "";
		obj.setkenhId(kenhId);

		String dvkdId = request.getParameter("dvkdId"); 
		if (dvkdId == null)
			dvkdId = "";
		obj.setdvkdId(dvkdId);

		String nhanhangId = request.getParameter("nhanhangId"); 
		if (nhanhangId == null)
			nhanhangId = "";
		obj.setnhanhangId(nhanhangId);

		String chungloaiId = request.getParameter("chungloaiId");
		if (chungloaiId == null)
			chungloaiId = "";
		obj.setchungloaiId(chungloaiId);

		obj.setsanphamId(util.antiSQLInspection(request.getParameter("sanphamId"))!=null?util.antiSQLInspection(request.getParameter("sanphamId")):"");
		obj.setnhanhangId(util.antiSQLInspection(request.getParameter("nganhhangId"))!=null?util.antiSQLInspection(request.getParameter("nganhhangId")):"");
		obj.setHoaDonKmDb(util.antiSQLInspection(request.getParameter("hdkmdb"))!=null?util.antiSQLInspection(request.getParameter("hdkmdb")):"");
		obj.setHangDiDuong(util.antiSQLInspection(request.getParameter("instransit"))!=null?util.antiSQLInspection(request.getParameter("instransit")):"");
		
		
		String tungay = request.getParameter("Sdays"); 
		if (tungay == null)
			tungay = "";
		obj.settungay(tungay);

		String denngay = request.getParameter("Edays");
		if (denngay == null)
			denngay = "";
		obj.setdenngay(denngay);

		String khoId = request.getParameter("khoId"); 
		if (khoId == null)
			khoId = "";
		obj.setkhoId(khoId);

		String vat = request.getParameter("vat"); 
		obj.setvat(vat);
		
		String discount = request.getParameter("discount"); 
		obj.setdiscount(discount);
		

		String giatinh = request.getParameter("giatinh");
		if (giatinh == null)
			giatinh = "1";
		obj.settype(giatinh);
		
		String[] fieldsHien = request.getParameterValues("fieldsHien");
		obj.setFieldShow(fieldsHien);
		
		String[] fieldsAn = request.getParameterValues("fieldsAn");
		obj.setFieldHidden(fieldsAn);
		
		System.out.println("GET TYPE : " + obj.gettype());
		if (!tungay.equals("") && !denngay.equals(""))
		{
			String action = request.getParameter("action");
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			if (action.equals("tao"))
			{
				
				
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "Attachment; filename=BaoCaoXuatNhapTon(TT)" + util.setTieuDe(obj) + ".xlsm");

				OutputStream out = response.getOutputStream();
				try
				{
					
					CreatePivotTable(out, response, request, fieldsHien, obj); // Create
				} catch (Exception ex)
				{
					request.getSession().setAttribute("errors", ex.getMessage());
				}
				return;
				
			} else if (action.equals("toPdf"))
			{
				response.setContentType("application/pdf");
				Document document = new Document();
				ServletOutputStream outstream = response.getOutputStream();
				try
				{
					obj.XuatNhapTonPdf(document, outstream, obj, "", giatinh);
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}

		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/SecondarySalesPurchaseInventoryReport.jsp";
		response.sendRedirect(nextJSP);
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

	private void CreatePivotTable(OutputStream out, HttpServletResponse response, HttpServletRequest request, String[] fieldsHien, IStockintransit obj) throws Exception
	{
		try
		{
			
			String strfstream = getServletContext().getInitParameter("path") + "\\BaoCaoXuatNhapTonTT(NPP).xlsm";
			
			FileInputStream fstream = new FileInputStream(strfstream);
			Workbook workbook = new Workbook();
			workbook.open(fstream);
			workbook.setFileFormatType(FileFormatType.EXCEL2007XLSM);
			
			CreateHeader(workbook, obj);
			FillData(workbook, fieldsHien, obj);
			workbook.save(out);
			fstream.close();
			
		} catch (Exception ex)
		{
			ex.printStackTrace();
			throw new Exception("Error Message: " + ex.getMessage());
		}
	}
	
	private void FillData(Workbook workbook, String[] fieldsHien, IStockintransit obj) throws Exception
	{
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		
		Cells cells = worksheet.getCells();
		
		dbutils db = new dbutils();
		
	/*	String[] param = new String[13];
		param[0] = obj.gettungay().equals("") ? null : obj.gettungay();
		param[1] = obj.getdenngay().equals("") ? null : obj.getdenngay();
		param[2] = obj.getdvkdId().equals("") ? null : obj.getdvkdId();
		param[3] = obj.getkenhId().equals("") ? null : obj.getkenhId();
		param[4] = obj.getvungId().equals("") ? null : obj.getvungId();
		param[5] = obj.getkhuvucId().equals("") ? null : obj.getkhuvucId();
		param[6] = obj.getnppId().equals("") ? null : obj.getnppId();
		param[7] = obj.getkhoId().equals("") ? null : obj.getkhoId();
		param[8] = obj.getnhanhangId().equals("") ? null : obj.getnhanhangId();
		param[9] = obj.getchungloaiId().equals("") ? null : obj.getchungloaiId();
		param[10] = obj.getsanphamId().equals("") ? null : obj.getsanphamId();
		param[11] = obj.getuserId().equals("") ? null : obj.getuserId();
		param[12] ="1";
		ResultSet	rs = db.getRsByPro("Report_XNT_TT", param);*/
		String condition="";
		
		if(!obj.getvungId().equals(""))
			condition+=" and v.pk_seq="+obj.getvungId();
		if(!obj.getkenhId().equals(""))
			condition+=" and kbh.pk_seq= "+obj.getkenhId();
		if(!obj.getdvkdId().equals(""))
			condition+=" and dvkd.pk_seq="+obj.getdvkdId();
		if(!obj.getkhuvucId().equals(""))
			condition+=" and kv.pk_seq="+obj.getkhuvucId();
		if(!obj.getNganhHangId().equals(""))
			condition+=" and nh.pk_seq="+obj.getNganhHangId();
		if(!obj.getnppId().equals(""))
			condition+=" and npp.pk_seq="+obj.getnppId();
		
		String sql="declare	@TuNgay varchar(10),@DenNgay varchar(10),@DvkdId numeric(18,0),"+
	"\n @KbhId numeric(18,0),@VungId numeric(18,0),@KhuvucId numeric(18,0),@NppId numeric(18,0),"+
	"\n @KhoId numeric(18,0),@NhanHangId numeric(18,0),@ChungLoaiId numeric(18,0),"+
	"\n @SpId numeric(18,0),@UserId numeric(18,0),@IsCenter bit"+	
	"\n set @TuNgay='"+obj.gettungay()+"';"+
	"\n set @DenNgay='"+obj.getdenngay()+"'"+
	"\n set @NppId='"+obj.getnppId()+"'"+
	"\n declare @ThangTruoc varchar(10),@Thang int,@Nam int, @NgayDauThang varchar(10) "+ 
     
	"\n set @ThangTruoc=(select convert(varchar(10),DATEADD(month,-1,@TuNgay),20) )  "+    
	"\n set @Thang=SUBSTRING(@ThangTruoc,6,2) "+     
	"\n set @Nam=SUBSTRING(@ThangTruoc,0,5)"+      


"\n IF OBJECT_ID('tempdb.dbo.#ThangKS_GanNhat') IS NOT NULL DROP TABLE #ThangKS_GanNhat"+
"\n create table #ThangKS_GanNhat"+
"\n ("+
"\n NPP_FK NUMERIC(18,0),"+
"\n Nam int,"+
"\n Thang int,"+
"\n NgayDauThang char(10)"+
"\n primary key (npp_fk,nam,thang,NgayDauThang)"+
"\n )"+
"\n insert into #ThangKS_GanNhat(NPP_FK,Thang,Nam,NgayDauThang) "+
"\n select @nppId, "+
"\n	(		"+
"\n	select top(1) (thangks)  from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ  and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8) "+
"\n	order by NAM desc ,THANGKS desc "+
"\n	) as Thang , "+
"\n	(	select top(1) (NAM) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)"+
"\n		order by NAM desc ,THANGKS desc "+
"\n	) "+
"\n,  "+
"\n CONVERT(char(10),	 DATEADD(month,1,( "+
"\n		cast((	select top(1) (NAM) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ 	 and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8) "+
"\n		order by NAM desc ,THANGKS desc "+
"\n		) as varchar(10)) +'-'+   "+
"\n		case when len(dbo.Trim(  "+
"\n		cast((		select top(1) (thangks) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ 	and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)"+
"\n		order by NAM desc ,THANGKS desc "+
"\n		)as varchar(10))))<2 then '0'+ 	cast((		select top(1) (thangks)  from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)"+ 
"\n		order by NAM desc ,THANGKS desc "+ 
"\n		)as varchar(10)) else "+
"\n		cast((		select top(1) (thangks) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ 	and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8) "+
"\n		order by NAM desc ,THANGKS desc "+ 
"\n		)as varchar(10)) end +'-01' "+  
"\n		)),120) as NgayDauThang "+
"\n		from NHAPHANPHOI  a "+
"\n		where a.PK_SEQ=@nppId "+
"\n select	dvkd.donvikinhdoanh as dkkd,kbh.ten as kbhTEN,v.ten as vTEN,kv.ten as kvTEN,"+  
"\n	npp.ma as nppMa,npp.ten as nppTEN,kho.ten as khoTEN, "+
"\n	sp.MA as spMa,sp.ten as spTen,dvdl.donvi as dvTEN,DATA.NPP_FK,data.KBH_FK as KBH_fK ,DATA.KHO_FK,DATA.SANPHAM_FK,"+
"\n	dbo.Trim( DATA.SOLO) as SoLo"+
"\n	,dbo.Trim(DATA.NgayHetHan) as NgayHetHan,DATA.SoLuong,data.TYPE "+
"\n from   "+ 
"\n( select xnt.NPP_FK,xnt.KBH_FK,xnt.KHO_FK,xnt.sanpham_fk, xnt.SOLO,SUM(soluong) as soluong ,ngayhethan as NgayHetHan,type "+  
"\n	from    "+  
"\n	(   "+   
"\n	select npp_Fk,kbh_fk,kho_fk,sanpham_fk,dbo.trim(SoLo) as SoLo,sum(SoLuong) as SoLuong,N'A.Tồn đầu' as Type,dbo.trim(NgayHetHan) as NgayHetHan"+
"\n	from"+
"\n	("+
"\n		select a.npp_fk, KBH_FK,kho_fk, SANPHAM_FK,SOLO as SoLo, sum(soluong) as soluong ,N'A.Tồn đầu' as type,NGAYHETHAN as NgayHetHan"+
"\n		from TONKHOTHANG_CHITIET a inner join #ThangKS_GanNhat b on b.NPP_FK=a.NPP_FK and a.THANG=b.Thang and a.NAM=b.Nam	"+
"\n		group by a.npp_fk, kho_fk, SANPHAM_FK,SOLO,NGAYHETHAN,KBH_FK"+
		
"\n		union all "+
		
"\n		select b.npp_fk,b.KBH_FK,a.khonhan_fk as kho_fk,c.pk_seq as sanpham_fk,a.SOLO, sum(cast(soluongnhan as int)) as soluong, "+
"\n			N'B.Nhập hàng' as type ,NGAYHETHAN"+
"\n		from nhaphang_sp a inner join nhaphang b on a.nhaphang_fk = b.pk_seq"+
"\n			inner join sanpham c on c.pk_seq = a.sanpham_fk"+
"\n		where b.trangthai =1 and b.NGAYNHAN >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=b.npp_fk  ) and b.NGAYNHAN<@TuNgay"+
"\n		group by b.npp_fk,b.KBH_FK,c.pk_seq,a.khonhan_fk ,SOLO ,NGAYHETHAN"+

"\n		union all "+
		
"\n		select ck.npp_fk, ck.KBH_FK,ck.khoxuat_fk as kho_Fk, ck.sanpham_fk ,ck.solo,(-1)*sum(soluong) as SoLuong,N'C.Xuất chuyển nội bộ' as Type ,"+
"\n		ngayhethan"+
"\n		from"+
"\n		("+
"\n			select c.npp_fk, c.kbh_fk, c.khoxuat_fk, a.sanpham_fk, a.soluong ,a.solo,a.ngayhethan"+
"\n			from ERP_CHUYENKHONPP_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ"+
"\n				inner join ERP_CHUYENKHONPP c on a.chuyenkho_fk = c.pk_seq"+
"\n			where c.trangthai=1 and c.NgayChuyen >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=c.npp_fk  ) and c.NgayChuyen<@TuNgay"+
"\n		)as ck"+
"\n		group by ck.npp_fk, ck.khoxuat_fk, ck.sanpham_fk ,ck.solo ,ck.ngayhethan,ck.KBH_FK"+

"\n		union all"+

"\n		select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.solo,SUM(b.Soluong) as SoLuong,N'E.Đổi số lô(+)' as Type,b.ngayhethan"+
"\n		from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq"+
"\n		where a.trangthai=1 and a.ngaydoi >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.npp_fk  ) and a.ngaydoi<@TuNgay"+
"\n		group by a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.solo,b.ngayhethan"+

"\n		union all"+
"\n		select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.soloOLD,(-1)*SUM(b.Soluong) as SoLuong,N'F.Đổi số lô(-)' as Type,NgayHetHanOLD"+
"\n		from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq"+
"\n		where a.trangthai=1 and a.ngaydoi >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.npp_fk  ) and a.ngaydoi<@TuNgay"+
"\n		group by a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.soloOLD,NgayHetHanOLD"+

"\n		union all"+

"\n		select a.NPP_FK,a.KBH_FK,a.kho_fk, b.SANPHAM_FK, b.solo,-(1)*SUM(b.soluong) as soluong ,'ETC' as NghiepVu,ngayhethan"+
"\n		from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.YCXK_FK"+
"\n		where b.soluong > 0"+
"\n			 and a.PK_SEQ in (Select ycxk_fk from ERP_YCXUATKHONPP_DDH where ddh_fk in"+
"\n			(select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK in "+
"\n			(select PK_SEQ from ERP_HOADONNPP where TRANGTHAI not IN (3,5)"+
"\n				and NGAYXUATHD >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.npp_fk  ) and NGAYXUATHD<@TuNgay"+
"\n			)))"+
"\n			and a.TRANGTHAI!=3"+
"\n		group by a.kho_fk, a.NPP_FK, b.SANPHAM_FK, b.solo,ngayhethan,a.KBH_FK"+

"\n		union all"+

"\n		SELECT DTH.NPP_FK ,DTH.KBH_FK,DTH.KHO_FK ,THSP.SANPHAM_FK,THSP.SoLo,(-1)* SUM(THSP.SOLUONG) AS SOLUONG ,N'H.Trả hàng' AS TYPE ,NgayHetHan"+
"\n		FROM DONTRAHANG_SP THSP INNER JOIN DONTRAHANG DTH ON DTH.PK_SEQ = THSP.DONTRAHANG_FK"+
"\n		WHERE DTH.TRANGTHAI =2 "+
"\n			and NGAYTRA >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=DTH.NPP_FK  ) and NGAYTRA<@TuNgay"+
"\n 	AND THSP.SOLUONG > 0 "+
"\n		GROUP BY DTH.NPP_FK,DTH.KBH_FK,THSP.SANPHAM_FK,DTH.KHO_FK,THSP.SoLo,NgayHetHan"+

"\n 	union all"+

"\n		SELECT DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK , DCTK_SP.SOLO,"+
"\n			SUM( CAST( ISNULL(DCTK_SP.DIEUCHINH,0) AS INT) ) AS SOLUONG, N'I.Kiểm kho' AS TYPE ,NgayHetHan"+
"\n		FROM DIEUCHINHTONKHO DCTK INNER JOIN DIEUCHINHTONKHO_SP DCTK_SP ON DCTK_SP.DIEUCHINHTONKHO_FK = DCTK.PK_SEQ"+
"\n		WHERE DCTK.TRANGTHAI =1 "+
"\n		and NGAYDC >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=DCTK.NPP_FK  ) and NGAYDC<@TuNgay"+
		
"\n		GROUP BY DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK ,DCTK_SP.SOLO ,NgayHetHan"+

"\n		union all"+

"\n		SELECT A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK AS SANPHAM_FK,B.SOLO, 	"+
"\n		(-1)*ISNULL(SUM(B.SOLUONG),0) AS SOLUONG  ,N'Xuất hàng bán' AS TYPE,B.NGAYHETHAN "+
"\n		FROM phieuxuatkho A INNER JOIN PHIEUXUATKHO_SANPHAM_CHITIET B ON B.pxk_fk=A.PK_SEQ "+
"\n		WHERE A.ngaylapphieu >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=A.npp_fk  ) and a.ngaylapphieu< @TuNgay "+
"\n		and A.trangthai =1 "+
"\n		GROUP BY A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK ,B.SOLO,B.NGAYHETHAN 	 "+

"\n		union all "+
		
"\n		SELECT A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK AS SANPHAM_FK,B.SOLO, 	"+
"\n		(-1)*ISNULL(SUM(B.SOLUONG),0) AS SOLUONG  ,N'Xuất hàng KM' AS TYPE,B.NGAYHETHAN "+
"\n		FROM phieuxuatkho A INNER JOIN PHIEUXUATKHO_SPKM_CHITIET B ON B.pxk_fk=A.PK_SEQ "+
"\n		WHERE A.ngaylapphieu >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=A.npp_fk  ) and a.ngaylapphieu< @TuNgay "+
"\n		and A.trangthai =1 "+
"\n		GROUP BY A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK ,B.SOLO,B.NGAYHETHAN 	 "+

"\n		union all"+

"\n		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,(-1)* SUM(b.soluong) as tongxuat ,N'L.Đổi kênh(-)' as Type,b.ngayhethan"+
"\n		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk"+
"\n		where a.trangthai=1 and NgayChuyen >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.NPP_FK  ) and NgayChuyen<@TuNgay"+
"\n		and a.KHONHAN_FK is null"+
"\n		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk,b.solo,b.ngayhethan"+

"\n		union all"+
"\n		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,SUM(b.soluong) as tongxuat ,N'M.Đổi kênh(+)' as Type,b.ngayhethan"+
"\n		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk"+
"\n		where a.trangthai=1 and NgayChuyen >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.NPP_FK  ) and NgayChuyen<@TuNgay"+
"\n			and a.KHONHAN_FK is null"+
"\n		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk,b.solo,b.ngayhethan"+

"\n		union all"+
"\n		select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,(-1)* SUM(b.soluong) as tongxuat ,N'N.Chuyển kho(-)' as Type,b.ngayhethan"+
"\n		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk"+
"\n		where a.trangthai=1 and NgayChuyen >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.NPP_FK  ) and NgayChuyen<@TuNgay"+
"\n			and a.KHONHAN_FK is not null"+
"\n		group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk ,solo,b.ngayhethan"+
"\n		union all"+
"\n		select a.NPP_FK,a.KBH_FK,a.KHONHAN_FK, b.sanpham_fk, solo,SUM(b.soluong) as tongxuat ,N'O.Chuyển kho(+)' as Type,b.ngayhethan"+
"\n		from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk"+
"\n		where a.trangthai=1 and NgayChuyen >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.NPP_FK  ) and NgayChuyen<@TuNgay"+
"\n		and a.KHONHAN_FK is not null"+
"\n		group by a.KHONHAN_FK,a.KBH_FK, a.npp_fk, b.sanpham_fk,solo ,ngayhethan"+

"\n union all "+
		
"\n		select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_Fk,b.solo ,SUM(b.SoLuong) as SoLuong,N'P.Hàng trả lại' as Type,b.NgayHetHAN"+
"\n		from Erp_HangTraLaiNpp a inner join Erp_HangTraLaiNpp_SanPham b on b.hangtralai_fk=a.pk_Seq"+
"\n		where a.trangthai=1 and ngaytra >=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.NPP_FK  ) and ngaytra<@TuNgay"+
	
"\n		group by a.npp_fk,a.kbh_fk,b.sanpham_Fk,a.kho_fk,b.SoLo,b.NgayHetHAN"+
"\n union all "+



"\n select a.TrucThuoc_FK as npp_fk,a.KBH_FK ,a.KhoXuat_FK as Kho_FK,b.sanpham_fk,b.solo,(-1)*sum(b.soluong) as SoLuong,N'Q.Xuất kho khác',b.ngayhethan "+
"\n from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on b.chuyenkho_fk=a.PK_SEQ "+
"\n where a.TRANGTHAI=1 and a.TrucThuoc_FK=@NppId and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay "+
"\n group by a.KhoXuat_FK,b.sanpham_fk,a.TrucThuoc_FK,a.KBH_FK,b.solo,b.ngayhethan "+

			
"\n		) as TonDau"+
"\n		group by npp_fk,kbh_Fk,kho_Fk,sanpham_Fk,SoLo,NgayHetHan"+
	
	
	
	
	
	   
"\n		union all  "+    
		   
"\n		select b.npp_fk,b.KBH_FK,a.khonhan_fk as kho_fk,c.pk_seq as sanpham_fk,a.SOLO, sum(cast(soluongnhan as int)) as soluong,  N'B.Nhập hàng' as type  ,NGAYHETHAN "+    
"\n		from nhaphang_sp a inner join nhaphang b on a.nhaphang_fk = b.pk_seq	"+	      
"\n		inner join sanpham c on c.pk_seq = a.sanpham_fk    "+   
"\n	where   b.trangthai =1 and b.NGAYNHAN>=@TuNgay and b.NGAYNHAN<=@DenNgay  "+ 
"\n		group by b.npp_fk,b.KBH_FK,c.pk_seq,a.khonhan_fk ,SOLO	   ,NGAYHETHAN  "+ 
   
"\n		union all  "+    
   
"\n		select ck.npp_fk, ck.KBH_FK,ck.khoxuat_fk as kho_Fk, ck.sanpham_fk ,ck.solo,(-1)*sum(soluong) as SoLuong,N'C.Xuất chuyển nội bộ' as Type , "+  
"\n			ngayhethan  "+   
"\n		from       "+
"\n		(      "+
"\n			select  c.npp_fk, c.kbh_fk, c.khoxuat_fk, a.sanpham_fk,  a.soluong    ,a.solo,a.ngayhethan   "+
"\n			from ERP_CHUYENKHONPP_SANPHAM_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ  "+       
"\n			inner join ERP_CHUYENKHONPP c on a.chuyenkho_fk = c.pk_seq        "+ 
"\n			where c.trangthai=1 and  c.NgayChuyen>=@TuNgay and c.NgayChuyen<=@DenNgay    "+
"\n		)as ck   "+   
"\n		group by ck.npp_fk, ck.khoxuat_fk, ck.sanpham_fk ,ck.solo	,ck.ngayhethan,ck.KBH_FK  "+ 
		   		   
"\n		union all   "+
	   
"\n		select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.solo,SUM(b.Soluong) as SoLuong,N'E.Đổi số lô(+)' as Type,b.ngayhethan "+  
"\n		from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq  "+ 
"\n		where a.trangthai=1 and ngaydoi>=@TuNgay and ngaydoi<=@DenNgay    "+
"\n		group by a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.solo,b.ngayhethan "+  
   
"\n		union all 	"+		   
"\n			select a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.soloOLD,(-1)*SUM(b.Soluong) as SoLuong,N'F.Đổi số lô(-)' as Type,NgayHetHanOLD "+  
"\n			from ERP_DOISOLONPP a inner join ERP_DOISOLONPP_SANPHAM b on b.doisolo_fk=a.pk_seq  "+ 
"\n			where a.trangthai=1 and ngaydoi>=@TuNgay and ngaydoi<=@DenNgay  "+  
"\n			group by a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_fk,b.soloOLD,NgayHetHanOLD "+  
	   
"\n		union all  "+  
   
"\n		select  a.NPP_FK,a.KBH_FK,a.kho_fk, b.SANPHAM_FK, b.solo,-(1)*SUM(b.soluong) as soluong ,'ETC'  as NghiepVu,ngayhethan"+
"\n		from ERP_YCXUATKHONPP a inner join ERP_YCXUATKHONPP_SANPHAM_CHITIET b on a.PK_SEQ = b.YCXK_FK "+ 
"\n		where  b.soluong > 0 "+
"\n			and a.PK_SEQ in (Select ycxk_fk from ERP_YCXUATKHONPP_DDH  where ddh_fk in "+
"\n			(select ddh_fk from ERP_HOADONNPP_DDH where HOADONNPP_FK in (select PK_SEQ from ERP_HOADONNPP where TRANGTHAI not IN (3,5)"+
"\n			and NGAYXUATHD>=@TuNgay and NGAYXUATHD<=@DenNgay)))and a.TRANGTHAI!=3 "+
"\n		group by a.kho_fk, a.NPP_FK, b.SANPHAM_FK, b.solo,ngayhethan,a.KBH_FK "+
   
    
"\n		union all    "+  
	   
"\n		SELECT DTH.NPP_FK ,DTH.KBH_FK,DTH.KHO_FK ,THSP.SANPHAM_FK,THSP.SoLo,(-1)* SUM(THSP.SOLUONG) AS SOLUONG ,N'H.Trả hàng' AS TYPE ,NgayHetHan "+  
"\n		FROM DONTRAHANG_SP THSP INNER JOIN  DONTRAHANG DTH ON  DTH.PK_SEQ = THSP.DONTRAHANG_FK  "+    
"\n		WHERE DTH.TRANGTHAI =2 AND DTH.NGAYTRA >=@TuNgay AND DTH.NGAYTRA <=@DenNgay AND THSP.SOLUONG > 0  "+     
"\n		GROUP BY DTH.NPP_FK,DTH.KBH_FK,THSP.SANPHAM_FK,DTH.KHO_FK,THSP.SoLo,NgayHetHan	 "+     
   
"\n		union all  "+    
   
"\n		SELECT  DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK , DCTK_SP.SOLO, "+ 
"\n			SUM( CAST( ISNULL(DCTK_SP.DIEUCHINH,0) AS INT) ) AS SOLUONG, N'I.Kiểm kho' AS TYPE 	     ,NgayHetHan  "+ 
"\n		FROM	DIEUCHINHTONKHO DCTK  INNER JOIN DIEUCHINHTONKHO_SP DCTK_SP ON DCTK_SP.DIEUCHINHTONKHO_FK = DCTK.PK_SEQ"+       
"\n		WHERE   DCTK.TRANGTHAI =1 AND DCTK.NGAYDC >= @TuNgay AND DCTK.NGAYDC <= @DenNgay"+   
"\n		GROUP BY  DCTK.NPP_FK,DCTK.KBH_FK,DCTK.KHO_FK,DCTK_SP.SANPHAM_FK ,DCTK_SP.SOLO  ,NgayHetHan  "+ 
   
"\n		union all  "+    
		   
"\n		SELECT A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK AS SANPHAM_FK,B.SOLO, 	"+
"\n		(-1)*ISNULL(SUM(B.SOLUONG),0) AS SOLUONG  ,N'Xuất hàng bán' AS TYPE,B.NGAYHETHAN "+
"\n		FROM phieuxuatkho A INNER JOIN PHIEUXUATKHO_SANPHAM_CHITIET B ON B.pxk_fk=A.PK_SEQ "+
"\n		WHERE A.ngaylapphieu >=@TuNgay and a.ngaylapphieu<=@DenNgay "+
"\n		and A.trangthai =1 "+
"\n		GROUP BY A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK ,B.SOLO,B.NGAYHETHAN 	 "+
		   
"\n		union all   "+
		
"\n		SELECT A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK AS SANPHAM_FK,B.SOLO, 	 "+
"\n		(-1)*ISNULL(SUM(B.SOLUONG),0) AS SOLUONG  ,N'Xuất hàng KM' AS TYPE,B.NGAYHETHAN "+
"\n		FROM phieuxuatkho A INNER JOIN PHIEUXUATKHO_SPKM_CHITIET B ON B.pxk_fk=A.PK_SEQ "+
"\n		WHERE A.ngaylapphieu >=@TuNgay and a.ngaylapphieu<=@DenNgay "+
"\n		and A.trangthai =1 "+
"\n		GROUP BY A.NPP_FK,B.kbh_fk,B.kho_fk,B.SANPHAM_FK ,B.SOLO,B.NGAYHETHAN 	 "+
		   
	"\n	union all   "+
		   
	"\n	 select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,(-1)* SUM(b.soluong) as tongxuat ,N'L.Đổi kênh(-)' as Type,b.ngayhethan"+   
	"\n 	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk "+   
	"\n	where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay and a.KHONHAN_FK is  null "+        
	"\n	group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk,b.solo,b.ngayhethan   "+
		   
	"\n	union all   "+
	"\n	select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,SUM(b.soluong) as tongxuat ,N'M.Đổi kênh(+)' as Type,b.ngayhethan"+   
	"\n	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk"+    
	"\n	where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay and a.KHONHAN_FK is  null "+        
	"\n	group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk,b.solo,b.ngayhethan   "+
		   
	"\n	union all    "+
	"\n	select a.NPP_FK,a.KBH_FK,a.KhoXuat_FK, b.sanpham_fk,b.solo,(-1)* SUM(b.soluong) as tongxuat ,N'N.Chuyển kho(-)' as Type,b.ngayhethan"+   
	"\n	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk "+             
	"\n	where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay    and a.KHONHAN_FK is not null   "+      
	"\n	group by a.khoxuat_fk, a.KBH_FK,a.npp_fk, b.sanpham_fk  ,solo,b.ngayhethan       "+    
	"\n	union all  "+           
	"\n	select a.NPP_FK,a.KBH_FK,a.KHONHAN_FK, b.sanpham_fk, solo,SUM(b.soluong) as tongxuat ,N'O.Chuyển kho(+)' as Type,b.ngayhethan "+  
	"\n	from ERP_CHUYENKENH a inner join ERP_CHUYENKENH_SANPHAM_CHITIET b on a.pk_seq = b.chuyenkenh_fk  "+            
	"\n	where a.trangthai=1 and a.NgayChuyen>=@TuNgay and a.NgayChuyen<=@DenNgay   and a.KHONHAN_FK is not null"+         
	"\n	group by a.KHONHAN_FK,a.KBH_FK, a.npp_fk, b.sanpham_fk,solo ,ngayhethan  "+ 
		   
	"\n	union all   "+
		   
	"\n	select  a.npp_fk,a.kbh_fk,a.kho_fk,b.sanpham_Fk,b.solo ,SUM(b.SoLuong) as SoLuong,N'P.Hàng trả lại' as Type,b.NgayHetHAN "+      
	"\n	from Erp_HangTraLaiNpp a inner join Erp_HangTraLaiNpp_SanPham b on b.hangtralai_fk=a.pk_Seq"+   
	"\n	where a.trangthai=1  and a.ngaytra>=@TuNgay and a.ngaytra <=@DenNgay"+  
	"\n	group by a.npp_fk,a.kbh_fk,b.sanpham_Fk,a.kho_fk,b.SoLo,b.NgayHetHAN"+ 
	"\n		union all"+
	"\n select a.TrucThuoc_FK as npp_fk,a.KBH_FK ,a.KhoXuat_FK as Kho_FK,b.sanpham_fk,b.solo,(-1)*sum(b.soluong) as SoLuong,N'Q.Xuất kho khác',b.ngayhethan "+
	"\n from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM_CHITIET b on b.chuyenkho_fk=a.PK_SEQ "+
	"\n where a.TRANGTHAI=1 and a.TrucThuoc_FK=@NppId and a.NgayChuyen>=( select NgayDauThang from #ThangKS_GanNhat where npp_fk=a.NPP_FK  ) "+
	"\n	 and a.NgayChuyen<@TuNgay "+
	"\n group by a.KhoXuat_FK,b.sanpham_fk,a.TrucThuoc_FK,a.KBH_FK,b.solo,b.ngayhethan "+

	"\n )as xnt   "+
	"\n group by xnt.NPP_FK,xnt.KHO_FK,xnt.sanpham_fk,SoLO,ngayhethan,type,xnt.KBH_FK "+  
	"\n ) as DATA   "+ 
	"\n inner join nhaphanphoi npp on npp.pk_Seq=data.npp_fk  "+
	"\n inner join SANPHAM sp on sp.PK_SEQ=DATA.SANPHAM_FK "+
	"\n inner join donvidoluong dvdl on dvdl.pk_seq=sp.dvdl_fk"+ 
	"\n inner join kho kho on kho.pk_Seq=data.kho_fk "+
	"\n inner join KENHBANHANG kbh on kbh.PK_SEQ= case when npp.DUNGCHUNGKENH=1 then 100025 else DATA.KBH_FK  end"+
	"\n inner join donvikinhdoanh dvkd on dvkd.pk_Seq=sp.dvkd_fk "+
	"\n inner join khuvuc kv on kv.pk_Seq=npp.khuvuc_fk "+
	"\n inner join vung v on v.pk_Seq=kv.vung_Fk"+ 
	"\n inner join NGANHHANG nh on sp.NGANHHANG_FK=nh.PK_SEQ"+
    "\n where  1=1  "+ condition+  
    "\n ORDER BY sp.MA,DATA.type";
		System.out.println("excel query:  "+sql + " \n ");
		ResultSet rs=db.get(sql);
		int index = 2;
		Cell cell = null;
		while (rs.next())
		{
			cell = cells.getCell("EA" + String.valueOf(index)); cell.setValue(rs.getString("kbhTen"));
			cell = cells.getCell("EB" + String.valueOf(index));cell.setValue(rs.getString("Dkkd"));
			cell = cells.getCell("EC" + String.valueOf(index));cell.setValue(rs.getString("Vten"));
			cell = cells.getCell("ED" + String.valueOf(index));cell.setValue(rs.getString("Kvten"));
			cell = cells.getCell("EE" + String.valueOf(index));cell.setValue(rs.getString("NPPma"));
			cell = cells.getCell("EF" + String.valueOf(index));cell.setValue(rs.getString("NPPten"));
			cell = cells.getCell("EG" + String.valueOf(index));cell.setValue(rs.getString("spma"));
			cell = cells.getCell("EH" + String.valueOf(index));cell.setValue(rs.getString("spten"));
			cell = cells.getCell("EI" + String.valueOf(index));cell.setValue(rs.getString("dvten"));
			cell = cells.getCell("EJ" + String.valueOf(index));cell.setValue(rs.getString("KHOten"));
			cell = cells.getCell("EK" + String.valueOf(index));cell.setValue(rs.getString("type"));
			cell = cells.getCell("EL" + String.valueOf(index));cell.setValue(rs.getString("solo"));
			cell = cells.getCell("EM" + String.valueOf(index));cell.setValue(rs.getString("ngayhethan"));
			cell = cells.getCell("EN" + String.valueOf(index));cell.setValue(rs.getDouble("soluong"));
			index++;
		}
		
		if (index == 2)
		{
			throw new Exception("Xin loi,khong co bao cao voi dieu kien da chon....!!!");
		}
		if (rs != null)
			rs.close();
		if (db != null)
			db.shutDown();
		
	}
	
	
	private void CreateHeader(Workbook workbook, IStockintransit obj) throws Exception
	{
		
		Worksheets worksheets = workbook.getWorksheets();
		Worksheet worksheet = worksheets.getSheet(0);
		worksheet.setName("Sheet1");
		
		Cells cells = worksheet.getCells();
		cells.setRowHeight(0, 20.0f);
		Cell cell = cells.getCell("A1");
		ReportAPI.getCellStyle(cell, Color.RED, true, 14, "XUẤT NHẬP TỒN");
		
		cell = cells.getCell("A3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Từ ngày : " + obj.gettungay() + "   Đến ngày: " + obj.getdenngay());
		cell = cells.getCell("A4");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Ngày tạo: " + obj.getDateTime());
		
		cell = cells.getCell("B3");
		ReportAPI.getCellStyle(cell, Color.NAVY, true, 9, "Tạo bởi : " + obj.getuserTen());
		
		String message = "";
	
		
		cells.setRowHeight(2, 18.0f);
		cell = cells.getCell("A5");
		ReportAPI.getCellStyle(cell, Color.RED, true, 9, message);
		
		cell = cells.getCell("EA1");
		cell.setValue("KenhBanHang");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EB1");
		cell.setValue("DonViKinhDoanh");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EC1");
		cell.setValue("Mien");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("ED1");
		cell.setValue("Vung");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EE1");
		cell.setValue("MaCN/DT");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EF1");
		cell.setValue("TenCN/DT");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EG1");
		cell.setValue("MaSanPham");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EH1");
		cell.setValue("TenSanPham");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EI1");
		cell.setValue("DonViTinh");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EJ1");
		cell.setValue("Kho");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EK1");
		cell.setValue("Loai");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EL1");
		cell.setValue("SoLo");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EM1");
		cell.setValue("NgayHetHan");
		ReportAPI.setCellHeader(cell);
		
		cell = cells.getCell("EN1");
		cell.setValue("Soluong");
		ReportAPI.setCellHeader(cell);
		
		
		
	}
}

