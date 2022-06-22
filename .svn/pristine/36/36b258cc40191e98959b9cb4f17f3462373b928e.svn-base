package geso.dms.center.servlets.nhaphanphoi;

import geso.dms.center.beans.nhaphanphoi.*;
import geso.dms.center.beans.nhaphanphoi.imp.*;
import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.center.util.UtilitySyn;
import geso.dms.distributor.beans.khachhang.IKhachhangList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.aspose.cells.Font;
import com.aspose.cells.Style;
import com.aspose.cells.TextAlignmentType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;

public class NhaphanphoiSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;   
	Utility bodau=new Utility();
    public NhaphanphoiSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		INhaphanphoiList obj;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out  = response.getWriter();

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
		Utility util = new Utility();
		out = response.getWriter();
		obj = new NhaphanphoiList(); 

		/* obj.setRequestObj(request);*/

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length()==0)
			userId = util.antiSQLInspection(request.getParameter("userId"));

		String action = util.getAction(querystring);
		out.println(action);

		String nppId = util.getId(querystring);

		System.out.print("action"+ action);

		if (action.equals("delete"))	   		  
			obj.setMsg(Delete(nppId, obj));	    	

		obj.setUserId(userId);
		System.out.println("iskh la "+request.getParameter("isKH"));
		obj.setIsKhachhang(request.getParameter("isKH"));
		obj.init("");
		session.setAttribute("obj", obj);

		String nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoi.jsp";
		response.sendRedirect(nextJSP);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		INhaphanphoiList obj = new NhaphanphoiList();
		
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    OutputStream out = response.getOutputStream();
	    
	    Utility util = new Utility();
	    
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
	    String userId = util.antiSQLInspection(request.getParameter("userId"));
	    
		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
    	if (maFAST == null)
    		maFAST = "";    	
    	obj.setMaFAST(maFAST);
	   
		String ten = util.antiSQLInspection(request.getParameter("nppTen"));
    	if ( ten == null)
    		ten = "";
    	obj.setTen(ten);
    	
    	String sodienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
    	if (sodienthoai == null)
    		sodienthoai = "";    	
    	obj.setSodienthoai(sodienthoai);
    	    	
    	String kvId = util.antiSQLInspection(request.getParameter("kvId"));
    	if (kvId == null)
    		kvId = "";    	
    	obj.setKvId(kvId);
    	
    	String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
    	if (kenhId == null)
    		kenhId = "";    	
    	obj.setKenhId(kenhId);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
    	if (trangthai == null)
    		trangthai = "";    	
	
    	if (trangthai.equals("2"))
    		trangthai = "";
    	obj.setTrangthai(trangthai);
    	
    	String isKH = util.antiSQLInspection(request.getParameter("isKH"));
    	if (isKH == null)
    		isKH = "";    	
    	obj.setIsKhachhang(isKH);
    	
	    String action = request.getParameter("action");
	    if (action == null){
	    	action = "";
	    }
	   
	    String diaban = util.antiSQLInspection(request.getParameter("diaban"));   	
    	if (diaban == null)
    		diaban = "";    	
    	obj.setDiaban(diaban);
    	
    	String diabanId = util.antiSQLInspection(request.getParameter("diabanId"));   	
       	if (diabanId == null)
       		diabanId = "";   
       	obj.setDiabanId(diabanId);
       	
    	String vung = util.antiSQLInspection(request.getParameter("vung"));   	
    	if (vung == null)
    		vung = "";   
	    obj.setVung(vung);
	    String nextJSP = "";
	    if (action.equals("new")){
	    	
	    	// Empty Bean for distributor
	    	INhaphanphoi nppBean = (INhaphanphoi) new Nhaphanphoi("", request.getParameter("isKH"));
	    	nppBean.setUserId(userId);
	    	nppBean.createRS();
	    	// Save Data into session
	    	session.setAttribute("nppBean", nppBean);
    		
    		nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoiNew.jsp";
    		if (isKH.equals("1"))
    			nextJSP = request.getContextPath() + "/pages/Center/KhachHangETCNew.jsp";
    		
    		session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    	    response.sendRedirect(nextJSP);
    		
	    } 
	    else if (action.equals("search"))
	    {
	    	obj.setRequestObj(request);	    	
	    
	    	obj.setUserId(userId);
	    	obj.setIsKhachhang(isKH);
	    	String search = getSearchQuery(request);
	    	obj.init(search);
	    	nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoi.jsp"; 
    		session.setAttribute("obj", obj);  	
    		session.setAttribute("userId", userId);
    	    response.sendRedirect(nextJSP);
	    }
	    else if (action.equals("view") || action.equals("next") || action.equals("prev")){
	    	
	    	obj.setUserId(userId);
	    	obj.setIsKhachhang(isKH);
	    	obj.setNxtApprSplitting(Integer.parseInt(util.antiSQLInspection(request.getParameter("nxtApprSplitting"))));
	    	String search = getSearchQuery(request);
	    	obj.init(search);
	    	obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
	    	session.setAttribute("obj", obj);
			nextJSP = request.getContextPath() + "/pages/Center/NhaPhanPhoi.jsp";
			response.sendRedirect(nextJSP);
	    }
	    
	    else if (action.equals("excel")){
	    	try
	    	{
	    		
	    		response.setContentType("application/xlsm");
	    		response.setHeader("Content-Disposition", "attachment; filename=DanhSachNhaPhanPhoi.xls");
	    		OutputStream out1 = response.getOutputStream();
	    		String query = "\n SELECT V.TEN AS [VÙNG/MIỀN], d.ten as [KHU VỰC],  b.TEN  [TỈNH THÀNH]" +
	    		"\n		, c.TEN  [QUẬN HUYỆN] , A.PK_SEQ as [Mã hệ thống] , a.MaFAST [MÃ CN/ĐT], a.TEN as [TÊN] , a.TENNGUOIDAIDIEN [TÊN NGƯỜI ĐẠI DIỆN]" +
	    		"\n		, g.TEN as [TRỰC THUỘC], a.DIACHI [ĐỊA CHỈ],  a.DIACHIXHD AS[ĐỊA CHỈ XUẤT HĐ ], a.MASOTHUE [MST]" +
	    		"\n		, a.XUATTAIKHO [XUẤT KHO TẠI], a.DIENTHOAI [ĐIỆN THOẠI], a.FAX , a.EMAIL , CASE  a.quanlySellOut WHEN 1 THEN N'CÓ' ELSE N'KHÔNG' END [QUẢN LÝ SELL OUT] , A.tyleOutIn [% Doanh số sell out]" +
	    		"\n		, a.HINHTHUCTHANHTOAN [HÌNH THỨC THANH TOÁN] " +
	    		"\n 	, a.NGANHANG [NGÂN HÀNG], a.SOTAIKHOAN [SỐ TÀI KHOẢN], a.KYHIEUHOADON [KÝ HIỆU HÓA ĐƠN], a.SOHOADONTU [SỐ HÓA ĐƠN TỪ], a.SOHOADONDEN [SỐ HÓA ĐƠN ĐẾN] " +
	    		"\n 	, a.MaKho [MÃ KHO], a.MaNX [MÃ NHẬP XUẤT], a.TenKyHd [TÊN KÝ HĐ]  " +
	    		"\n FROM NHAPHANPHOI a " +
	    		"\n left join TINHTHANH b on b.PK_SEQ = a.TINHTHANH_FK " +
	    		"\n LEFT JOIN QUANHUYEN c on c.PK_SEQ = a.QUANHUYEN_FK " +  
	    		"\n left join KHUVUC d on d.PK_SEQ = a.KHUVUC_FK " +
	    		"\n left join VUNG V on V.PK_SEQ = D.VUNG_FK " +
	    		"\n left join LOAINHAPHANPHOI e on e.maloai = a.loaiNPP " +
	    		"\n left join NHAPHANPHOI g on g.PK_SEQ = a.TRUCTHUOC_FK " +
	    		"\n where 1 = 1";
	    		if (ten.length() > 0)
	        	{
	    			query = query + " and (dbo.ftBoDau(a.ten) like ( N'%"+bodau.replaceAEIOU(ten.trim())+"%') or dbo.ftBoDau(a.ma) like (N'%"+bodau.replaceAEIOU(ten.trim())+"%'))";				
	        	}
	        	
	        	if (sodienthoai.length() > 0)
	        	{
	    			query = query + " and upper(a.dienthoai) like upper('%" + sodienthoai + "%')";
	        	}
	        	if (kvId.length() > 0)
	        	{
	    			query = query + " and d.pk_seq = '" + kvId + "'";
	        	}
	        	
	        	if (trangthai.length() > 0){
	        		query = query + " and a.trangthai = '" + trangthai + "'";
	        		
	        	}
	        	if (kenhId.length() > 0)
	        	{
	        		query += " and a.pk_seq in (select npp_fk from NHAPP_KBH where kbh_fk='"+kenhId+"'   )";
	        	}
	        	
	        	if (vung.length() > 0)
	        	{
	        		query+= " and v.pk_seq like '%"+vung+"%' ";
	        	}
	        	
	        	//Địa bàn này là tỉnh thành
	        	if (diaban.length() > 0)
	        	{
	        		query+= " and b.pk_seq like '%"+diaban+"%' ";
	        	}
	        	
	        	//Cái này mới là địa bàn
	        	if (diabanId.length() > 0)
	        	{
	        		query+= " and db.pk_seq = '"+diabanId+"' ";
	        	}
	        	
	        	if (maFAST.length() > 0)
	        	{
	        		query+= " and a.maFast like '%"+maFAST+"%' ";
	        	}

	    		query += "\n order by [VÙNG/MIỀN],  [KHU VỰC], [TỈNH THÀNH] ";
	    		ExportToExcel(out1, obj, query);
	    		obj.getDb().shutDown();
	    		return;
	    	}
	    	catch (Exception e) 
	    	{
				e.printStackTrace();
			}
	    }
	}

	
	
	
	 private String getSearchQuery(HttpServletRequest request)
	 {		
		INhaphanphoiList obj = new NhaphanphoiList();
		
		Utility util = new Utility();
		
		String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));
    	if (maFAST == null)
    		maFAST = "";    	
    	obj.setMaFAST(maFAST);
		
		String ten = util.antiSQLInspection(request.getParameter("nppTen"));
    	if ( ten == null)
    		ten = "";
    	obj.setTen(ten);
    	
    	String sodienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
    	if (sodienthoai == null)
    		sodienthoai = "";    	
    	obj.setSodienthoai(sodienthoai);
    	    	
    	String kvId = util.antiSQLInspection(request.getParameter("kvId"));
    	if (kvId == null)
    		kvId = "";    	
    	obj.setKvId(kvId);
    	
    	String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
    	if (kenhId == null)
    		kenhId = "";    	
    	obj.setKenhId(kenhId);
    	
    	String isKH = util.antiSQLInspection(request.getParameter("isKH"));
    	if (isKH == null)
    		isKH = "";    	
    	obj.setIsKhachhang(isKH);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));   	
    	if (trangthai == null)
    		trangthai = "";    	
    	
    	String diaban = util.antiSQLInspection(request.getParameter("diaban"));   	
    	if (diaban == null)
    		diaban = "";    
    	obj.setDiaban(diaban);
    	
    	String diabanId = util.antiSQLInspection(request.getParameter("diabanId"));   	
    	if (diabanId == null)
    		diabanId = "";    
    	obj.setDiabanId(diabanId);
    	
    	String vung = util.antiSQLInspection(request.getParameter("vung"));   	
    	if (vung == null)
    		vung = "";    	
    	obj.setVung(vung);
    	
    	if (trangthai.equals("2"))
    		trangthai = "";
    	
    	
    	obj.setTrangthai(trangthai);
    	String query =  "	select distinct isnull(a.maFast,'')as maFAST,  a.ma as nppMa, '' as KenhBanHang, a.pk_seq as id, a.ten as nppTen, isnull(a.diachi,'') as diachi, isnull(a.dienthoai,'') as dienthoai , isnull(d.ten,'') as khuvuc, a.trangthai, a.ngaytao, "+  
						"			a.ngaysua, b.ten as nguoitao, c.ten as nguoisua ,a.TongThau_FK,isnull((select TEN from NHAPHANPHOI where PK_SEQ=a.TongThau_FK),'')  as TongThau,isnull(sitecode_conv.tennpptn,npptn.ten) as tennpptn," +
						"  		isnull(lnpp.tenloai,'')	 as loaiNPP," +
						"	ISNULL( ( select ten from TINHTHANH where pk_seq = tt.pk_seq ) , '' ) as tinhthanh		"+ 
						"	from nhaphanphoi a    "+
						//"		left join NHAPP_KBH on NHAPP_KBH.NPP_FK=a.PK_SEQ "+
						//"		left join KENHBANHANG kbh on kbh.PK_SEQ=NHAPP_KBH.KBH_FK "+
						"		inner join nhanvien b on b.pk_seq=a.nguoitao  "+
						"		inner join  nhanvien c on c.pk_seq=a.nguoisua   "+
						"		left join  khuvuc d  on a.khuvuc_fk=d.pk_seq " +
						"		left join diaban db on db.khuvuc_fk=d.pk_seq " +
						"		left join vung v on v.pk_seq=d.vung_fk "+
						"		left join tinhthanh tt on tt.pk_seq=a.TINHTHANH_FK"+
						"       left join sitecode_conv on sitecode_conv.convsitecode=a.sitecode                                              " +
						"       left join nhaphanphoi npptn on npptn.pk_Seq=a.npptn_fk          "+   
						"       left join LOAINHAPHANPHOI lnpp on lnpp.maloai=a.loainpp \n"+   
						"	 where 1=1 and a.isKHACHHANG = '" + isKH + "'  ";
    	
    	if (ten.length() > 0)
    	{
			query = query + " and (dbo.ftBoDau(a.ten) like ( N'%"+bodau.replaceAEIOU(ten.trim())+"%') or dbo.ftBoDau(a.ma) like (N'%"+bodau.replaceAEIOU(ten.trim())+"%'))";				
    	}
    	
    	if (sodienthoai.length() > 0)
    	{
			query = query + " and upper(a.dienthoai) like upper('%" + sodienthoai + "%')";
    	}
    	if (kvId.length() > 0)
    	{
			query = query + " and d.pk_seq = '" + kvId + "'";
    	}
    	
    	if (trangthai.length() > 0){
    		query = query + " and a.trangthai = '" + trangthai + "'";
    		
    	}
    	if (kenhId.length() > 0)
    	{
    		query += " and a.pk_seq in (select npp_fk from NHAPP_KBH where kbh_fk='"+kenhId+"'   )";
    	}
    	
    	if (vung.length() > 0)
    	{
    		query+= " and v.pk_seq like '%"+vung+"%' ";
    	}
    	
    	//Địa bàn này là tỉnh thành
    	if (diaban.length() > 0)
    	{
    		query+= " and tt.pk_seq like '%"+diaban+"%' ";
    	}
    	
    	//Cái này mới là địa bàn
    	if (diabanId.length() > 0)
    	{
    		query+= " and db.pk_seq = '"+diabanId+"' ";
    	}
    	
    	if (maFAST.length() > 0)
    	{
    		query+= " and a.maFast like '%"+maFAST+"%' ";
    	}
    	System.out.println("search sql ::: "+query);
	    return query;
	}	
	
	String query = "";
	private void setQuery(IStockintransit obj,HttpServletRequest request)
	{	
		Utility util = new Utility();
		String query = "\n select distinct kbh.diengiai as kenh, npp.ma as pk_seq, npp.sitecode, isnull(npp.ten,N'Chưa xác định') as ten, " +
		"\n isnull(npp.TENNGUOIDAIDIEN,N'Chưa xác định') AS tennguoidaidien, isnull(npp.diachi,N'Chưa xác định') as diachi, " +
		"\n isnull(npp.dienthoai,N'Chưa xác định') as dienthoai, npp.khosap, " +
		"\n case when npp.trangthai = '1' then N'Hoạt động' else N'Ngưng hoạt động' end as trangthai, " + 
		"\n ISNULL(npp.FAX,N'Chưa xác định') AS fax, ISNULL(npp.EMAIL,N'Chưa xác định') AS email, " +
		"\n isnull(npp.masothue,N'Chưa xác định') as masothue, isnull(npp.diachixhd,N'Chưa xác định') as diachixhd, " +
		"\n isnull(tinhthanh.ten,N'Chưa xác đinh') as tinhthanh, isnull(quanhuyen.ten,N'Chưa xác định') as quanhuyen, " +
		"\n isnull(vung.ten,N'Chưa xác định') as tenvung, isnull(khuvuc.ten,N'Chưa xác đinh') as tenkhuvuc, " +
		"\\n gsbh.pk_seq as gsid, isnull(gsbh.ten,N'Chưa xác định') as tengs, isnull(gsbh.dienthoai,N'Chưa xác định') as dienthoaigs, " +
		"\n isnull(gsbh.diachi,N'Chưa xác định') as diachigs, " +
		"\n ISNULL(npp.HINHTHUCTHANHTOAN,N'Chưa xác định') AS hinhthucthanhtoan, ISNULL(npp.NGANHANG,N'Chưa xác định') AS nganhang, " +
		"\n ISNULL(npp.SOTAIKHOAN,N'Chưa xác định') AS sotk, ISNULL(npp.ngaybatdau,N'Chưa xác định') AS ngaybatdau, " +
		"\n ISNULL(npp.ngayketthuc,N'Chưa xác định') AS ngayketthuc, b.NgayKetThuc as NgayKtQL " +
		"\n ISNULL(SITECODE_CONV.TENNPPTN,N'Chưa xác định') AS tennpptn, ISNULL(qh.TEN,N'Chưa xác định') as quanly, " +
		"\n ISNULL(npp.GHICHU,N'Chưa xác định') AS ghichu, isnull(npp.chietkhau,0) as ChietKhau, b.NgayBatDau as NgaybdQl " +
		"\n from nhaphanphoi npp " +
		"\n left join  NHAPP_GIAMSATBH b on npp.pk_seq = b.npp_fk " +
		"\n left join giamsatbanhang gsbh on gsbh.pk_Seq = b.gsbh_fk " +
		"\n left join nhapp_kbh npp_kbh on npp_kbh.npp_fk = npp.pk_seq " +
		"\n left join kenhbanhang kbh on kbh.pk_seq = npp_kbh.kbh_fk " +
		"\n left join khuvuc on khuvuc.pk_seq = npp.khuvuc_fk " +
		"\n left join SITECODE_CONV on npp.SITECODE = SITECODE_CONV.CONVSITECODE " +
		"\n left join vung on vung.pk_seq = khuvuc.vung_fk " +
		"\n left join tinhthanh on tinhthanh.pk_Seq = npp.tinhthanh_fk " +
		"\n left join quanhuyen on quanhuyen.pk_Seq = npp.quanhuyen_fk " +
		"\n left join " +
		"\n ( " +
		"\n     select tt.TEN,NPP_FK from NHAPHANPHOI_QUANHUYEN npp_tt " +
		"\n     inner join QUANHUYEN tt ON tt.PK_SEQ = npp_tt.QUANHUYEN_FK " +
		"\n ) As qh on qh.NPP_FK = npp.PK_SEQ " +
		"\n where 1 = 1";

		String ten = util.antiSQLInspection(request.getParameter("nppTen"));
		if (ten.length() > 0){
			//Utility util = new Utility();
			query = query + " and upper(npp.ten) like upper(N'%" + util.replaceAEIOU(ten) + "%')";

		}
		String sodienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
		if (sodienthoai.length() > 0){
			query = query + " and upper(npp.dienthoai) like upper('%" + sodienthoai + "%')";

		}

		String kvId = util.antiSQLInspection(request.getParameter("kvId"));
		if (kvId.length() > 0){
			query = query + " and npp.khuvuc_fk = '" + kvId + "'";

		}

		String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));
		if (!trangthai.equals("2")){
			query = query + " and npp.trangthai = '" + trangthai + "'";

		}
		String kenhId = util.antiSQLInspection(request.getParameter("kenhId"));
		if (kenhId == null)
			kenhId = "";    	
		if (kenhId.length() > 0)
		{
			query += " and npp.pk_seq in (select npp_fk from NHAPP_KBH where kbh_fk='"+kenhId+"'   )";
		}
		obj.setuserTen(util.antiSQLInspection(request.getParameter("userTen")));
		System.out.println("1/Xuất excel nhà phân phối :"+query);

	}	

	boolean kiemtra(String sql)
	{
		dbutils db =new dbutils();
    	ResultSet rs = db.get(sql);
		try {//kiem tra ben san pham
			while (rs.next())
			{ 
				if (rs.getString("num").equals("1"))
			   return true;
			}
		} 
		catch(Exception e) { 
			e.printStackTrace();
		}
		
		return false;
	}

	private String Delete(String id, INhaphanphoiList obj)
	{
		dbutils db = new dbutils();
		String msg = "";
		String sql="select count(*) as num from donhang where npp_fk='"+id+"'";
		System.out.println("cau lenh xoa: "+ sql);
		
		if (kiemtra(sql))
		{
			msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! ";
		}
		System.out.println("1.1"+sql);
		
		sql=" select count(*) as num from dieuchinhtonkho where npp_fk='"+id+"' ";
		if (kiemtra(sql))
		{
			msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! ";
		}
		System.out.println("1.2"+sql);
		
		sql=" select count(*) as num from DonTraHang where npp_fk='"+id+"' ";
		if (kiemtra(sql))
		{
			msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! ";
		}
		System.out.println("1.3"+sql);
		
		sql=" select count(*) as num from NhapHang where npp_fk='"+id+"' ";
		if (kiemtra(sql))
		{
			msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! ";
		}
		System.out.println("1.4"+sql);
		
		sql=" select count(*) as num from DonDatHang where npp_fk='"+id+"' ";
		if (kiemtra(sql))
		{
			msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! ";
		}
		System.out.println("1.6"+sql);
		try
		{
			db.getConnection().setAutoCommit(false);
			
			sql = "delete from CHITIEU_NHAPP_SEC where NHAPP_FK= '" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
				
			}
			System.out.println("----1---"+sql);
			
			sql = "delete from CHITIEUNPP where NHAPP_FK= '" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
				
			}
			System.out.println("----2---"+sql);
			
			
			sql = "delete from nhapp_kbh where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
			}
			System.out.println("1.7"+sql);
			
			sql = "delete from nhapp_nhacc_donvikd where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */; 
			}
			System.out.println("1.8"+sql);
			
			sql = "delete from nhapp_giamsatbh where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
			}
			System.out.println("1.9"+sql);
			
			sql = "delete from nhapp_kho where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */; 
			}
			System.out.println("1.10"+sql);
			
			
			sql = "delete from BGBANLENPP_SANPHAM where BGBANLENPP_FK =(select pk_seq from BANGGIABANLENPP  where npp_fk='" + id + "' )";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */; 
			}
			System.out.println("1.11"+sql);
			
			
			sql = "delete from banggiabanlenpp where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */; 
			}
			System.out.println("1.12"+sql);
			
			sql = "delete from nhapp_kho where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
			}
			System.out.println("1.13"+sql);
			
			
			sql = "delete from nhapp_kho_chitiet where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
			}
			System.out.println("1.13"+sql);
			
			sql = "delete from BANGGIAMUANPP_NPP where npp_fk='" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
			}
			System.out.println("1.14"+sql);
			
			sql = "delete from nhaphanphoi where pk_seq = '" + id + "'";
			if (!db.update(sql))
			{
				db.update("rollback");
				msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "/* +sql */;
			}
						
			System.out.println("1.14"+sql);
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			
			String query = "select pk_seq from BANGGIABANLENPP  where npp_fk = '" + id + "'  ";
			
			System.out.println(query);
			ResultSet rs = db.get(query);
			String bgbl = "";
			if (rs != null)
			{
				while (rs.next())
				{
					bgbl = rs.getString("pk_seq");
				}
				rs.close();
			}
						
		/*	UtilitySyn.SynData(db, "CHITIEU_NHAPP_SEC", "CHITIEU_NHAPP_SEC", "NHAPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "CHITIEUNPP", "CHITIEUNPP", "NHAPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "nhapp_kbh", "nhapp_kbh", "NPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "nhapp_nhacc_donvikd", "nhapp_nhacc_donvikd", "NPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "nhapp_giamsatbh", "nhapp_giamsatbh", "NPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "nhapp_kho", "nhapp_kho", "npp_fk", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "BGBANLENPP_SANPHAM", "BGBANLENPP_SANPHAM", "BGBANLENPP_FK", bgbl , "2", true); // XÓA 
			UtilitySyn.SynData(db, "banggiabanlenpp", "banggiabanlenpp", "NPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "nhapp_kho_chitiet", "nhapp_kho_chitiet", "NPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "BANGGIAMUANPP_NPP", "BANGGIAMUANPP_NPP", "NPP_FK", id , "2", true); // XÓA 
			UtilitySyn.SynData(db, "nhaphanphoi", "nhaphanphoi", "PK_SEQ", id , "2", true); // XÓA 
		*/	
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			db.update("rollback");
			msg = "Không thể xóa Nhà phân phối khi đã phát sinh dữ liệu ! "+e.getMessage();
		}finally {
			db.shutDown();
		}
		
		return msg;			
	}
	
	private String getDateTime() 
	{
	    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy : hh-mm-ss");
	    Date date = new Date();
	    return dateFormat.format(date);	
	}
	
	private void ExportToExcel(OutputStream out,INhaphanphoiList obj,String query )throws Exception
	{
		try{ 					


			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			TaoBaoCao(workbook, obj, query);			
			workbook.save(out);					

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	private void TaoBaoCao(com.aspose.cells.Workbook workbook,INhaphanphoiList obj,String query)throws Exception
	{

		try{		

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			com.aspose.cells.Cell cell = cells.getCell("A1");;	

			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "Danh sách nhà phân phối");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ngày tạo : " + Utility.getNgayHienTai() );


			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int location  = 0;
			int row = 10;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i).replace("_isNum","").replace("_isNum2","")  );
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}


			row ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{					
					cell = cells.getCell(row,location + i-1 );

					if(rsmd.getColumnName(i).contains("_isNum") )
					{
						int format = 37;
						if(rsmd.getColumnName(i).contains("_isNum2"))	
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
	}
	
	public static void main(String[] args) {
		
		dbutils x = new dbutils();
		try {
		
			String query = " select pk_seq from nhaphanphoi where tructhuoc_fk =  " + 110261;
		//	String query = " select pk_seq from nhaphanphoi where pk_seq =  " + 110261;
			ResultSet rs=  x.get(query);
			while(rs.next())
			{
				System.out.println(	"kq="+DeleteNghiepVuNPP(rs.getString("pk_seq"),true)) ;
			}
			System.out.println("end");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String DeleteNghiepVuNPP(String nppId,boolean xoakh) throws SQLException {
		dbutils db = new dbutils();
		// Utility util = new Utility();
		// String nppId = util.getIdNhapp(userId);

		try {
			db.getConnection().setAutoCommit(false);

			String query = "";
			/// hóa đơn
			query = " delete hoadon_sp where hoadon_fk in (select pk_seq from hoadon where NPP_FK ="+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}

			query = " delete hoadon_sp_CHitiet where hoadon_fk in (select pk_seq from hoadon where NPP_FK ="+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete hoadon_chietkhau where hoadon_fk in (select pk_seq from hoadon where NPP_FK ="+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete hoadon_ddh where hoadon_fk in (select pk_seq from hoadon where NPP_FK ="+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete hoadon_ctkm_trakm where hoadon_fk in (select pk_seq from hoadon where NPP_FK ="+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}

			
			query = " delete hoadon_ctkm_trakm_Chitiet where hoadon_fk in (select pk_seq from hoadon where NPP_FK ="+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = "delete from hoadon where npp_fk = '" + nppId + "'  ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				System.out.println("::::" + query);
				return "Không thể xóa khách hàng 21.";
			}
			// xuát kho
			query = " delete PHIEUXUATKHO_DONHANG where PXK_FK in (select pk_seq from PHIEUXUATKHO where npp_fk = "+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete PHIEUXUATKHO_SANPHAM where PXK_FK in (select pk_seq from PHIEUXUATKHO where npp_fk = "+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete PHIEUXUATKHO_SANPHAM_CHITIET where PXK_FK in (select pk_seq from PHIEUXUATKHO where npp_fk = "+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete PHIEUXUATKHO_SPKM where PXK_FK in (select pk_seq from PHIEUXUATKHO where npp_fk = "+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete PHIEUXUATKHO_SPKM_CHITIET where PXK_FK in (select pk_seq from PHIEUXUATKHO where npp_fk = "+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete PHIEUXUATKHO_TIENKM where PXK_FK in (select pk_seq from PHIEUXUATKHO where npp_fk = "+nppId+") ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = "delete from PHIEUXUATKHO where npp_fk = '" + nppId + "'  ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				System.out.println("::::" + query);
				return "Không thể xóa khách hàng 21.";
			}
			
			
			
			
			query = " delete DONHANG_SANPHAM where donhang_Fk in (select pk_seq from donhang where npp_Fk ="+nppId+")";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete DONHANG_SANPHAM_chitiet where donhang_Fk in (select pk_seq from donhang where npp_Fk ="+nppId+")";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete donhang_ctkm_dkkm where donhang_Fk in (select pk_seq from donhang where npp_Fk ="+nppId+")";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete DONHANG_CTKM_TRAKM where donhangID in (select pk_seq from donhang where npp_Fk ="+nppId+")";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			query = " delete DONHANG_CTKM_TRAKM_CHITIET where donhang_Fk in (select pk_seq from donhang where npp_Fk ="+nppId+")";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			query = " delete from donhang where npp_fk = '" + nppId + "'  ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				System.out.println("::::" + query);
				return "Không thể xóa khách hàng 21.";
			}
			
			
			query = " delete TONKHOTHANG where npp_fk="+nppId+"\r\n" + 
					"delete TONKHOTHANG_CHITIET where npp_fk="+nppId+"\r\n" + 
					"delete khoasothang  where npp_fk="+nppId+"\r\n" + 
					"delete NHAPP_KHO  where npp_fk="+nppId+"\r\n" + 
					"delete NHAPP_KHO_CHITIET  where npp_fk="+nppId+" ";
			if (!db.update(query)) {
				Utility.rollback_and_shutdown(db);
				return "Không thể xóa khách hàng 1.";
			}
			
			
			
			if(xoakh)
			{
				query = "delete from KhachHang_NPP where khachhang_fk in ( select pk_seq from khachhang where npp_fk = "+nppId+" )   ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 6.";
				}

				query = "delete from khachhang_nkhachhang where khachhang_fk in ( select pk_seq from khachhang where npp_fk = "+nppId+" )";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 7.";
				}

				query = "delete from NHOMKHACHHANG_KHACHHANG where kh_fk in ( select pk_seq from khachhang where npp_fk = "+nppId+" )";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 8.";
				}

				query = "delete from khachhang_tuyenBH where khachhang_fk in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 9.";
				}

				query = "delete from nvgn_kh where khachhang_fk   in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 10.";
				}

				query = "delete from KHACHHANG_TUYENBH where khachhang_fk   in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";

				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 11.";
				}

				query = "delete from KHACHHANG_ANHCHUP where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 12.";
				}

				query = "delete from KHACHHANG_CONGNO where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 13.";
				}

				query = "delete from KhachHang_DaiDienKinhDoanh where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 14.";
				}

				query = "delete from KHACHHANG_KHODOITHU where KH_FK  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 14.";
				}

				query = "delete from KHACHHANG_MUCTIEUNGAY where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 15.";
				}

				query = "delete from KHACHHANG_TOADO_LOG where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 16.";
				}

				query = "delete from KHACHHANG_YKIEN where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 17.";			
				}

				query = "delete from ddkd_khachhang where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 18.";
				}

				query = "delete from DDKD_KHACHHANG_LOG where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 19.";
				}

				query = "delete from makhachhang where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 20.";
				}
				
				query = "delete from DDKD_Ngay_KH_Log where khachhang_fk  in ( select pk_seq from khachhang where npp_fk = "+nppId+" ) ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					return "Không thể xóa khách hàng 20.";
				}

				query = "delete from khachhang where  npp_fk = "+nppId+" ";
				if (!db.update(query))
				{
					db.getConnection().rollback();
					System.out.println("::::"+query);
					return "Không thể xóa khách hàng 21.";
				}
				
			}
			
			

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			return "Xoá khách hàng thành công.";
		} catch (Exception e) {
			Utility.rollback_and_shutdown(db);
			e.printStackTrace();
			return "Exception xóa: " + e.getMessage();
		} 

		
	}
}
