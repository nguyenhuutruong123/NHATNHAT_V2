package geso.dms.distributor.beans.uynhiemchi.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.distributor.beans.uynhiemchi.IErpUynhiemchiList;
import geso.dms.center.db.sql.dbutils;

public class ErpUynhiemchiList extends Phan_Trang implements IErpUynhiemchiList {
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String congtyId;
	String nppdangnhap;

	String nccId;
	ResultSet nccRs;
	String htttId;
	ResultSet htttRs;

	String nvId;
	ResultSet nvRs;

	String loaihoadon;
	ResultSet loaihoadonRs;

	String trangthai;
	String msg;

	String sotien;

	ResultSet tthdRs;

	private int num;
	private int[] listPages;
	private int currentPages;

	String Sochungtu;
	String Sohoadon;


	dbutils db;

	public ErpUynhiemchiList() {
		this.tungay = "";
		this.denngay = "";
		this.nccId = "";
		this.htttId = "";
		this.nvId = "";
		this.loaihoadon = "";
		this.trangthai = "";
		this.msg = "";
		this.sotien = "";
		this.congtyId = "";
		this.nppdangnhap = "";

		currentPages = 1;
		num = 1;
		this.Sochungtu = "";
		this.Sohoadon = "";
		this.db = new dbutils();

	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTungay() {
		return this.tungay;
	}

	public void setTungay(String tungay) {
		this.tungay = tungay;
	}

	public String getDenngay() {
		return this.denngay;
	}

	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}

	public String getNccId() {
		return this.nccId;
	}

	public void setNccId(String nccid) {
		this.nccId = nccid;
	}

	public ResultSet getNccList() {
		return this.nccRs;
	}

	public void setNccList(ResultSet ncclist) {
		this.nccRs = ncclist;
	}

	public String getHtttId() {
		return this.htttId;
	}

	public void setHtttId(String htttid) {
		this.htttId = htttid;
	}

	public ResultSet getHtttList() {
		return this.htttRs;
	}

	public void setHtttList(ResultSet htttlist) {
		this.htttRs = htttlist;
	}

	public String getTrangthai() {
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public void setmsg(String msg) {
		this.msg = msg;
	}

	public String getmsg() {
		return this.msg;
	}

	public ResultSet getTThoadonList() {
		return this.tthdRs;
	}

	public void setTThoadonList(ResultSet tthdlist) {
		this.tthdRs = tthdlist;
	}

	public int getNum() {
		return this.num;
	}

	public void setNum(int num) {
		this.num = num;
		listPages = PhanTrang.getListPages(num);
	}

	public int getCurrentPage() {
		return this.currentPages;
	}

	public void setCurrentPage(int current) {
		this.currentPages = current;
	}

	public int[] getListPages() {
		return this.listPages;
	}

	public void setListPages(int[] listPages) {
		this.listPages = listPages;
	}

	public int getLastPage() {
		ResultSet rs = db.get("select count(*) as c from ERP_THANHTOANHOADON");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage) {
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}

	private String LayDuLieu(String id) {
		String layKT = "";

		return layKT;
	}

	public void init(String search) {
		this.getnppdangnhap();
		String query = "";
		if (search.length() <= 0) {
			query = " select distinct a.loaithanhtoan,  a.pk_seq as tthdId, a.trangthai, a.ngayghinhan, a.ngaytao, a.ngaysua, a.phinganhang, a.vat , isnull(a.thanhtoantutienvay,0) as thanhtoantutienvay,  \n"+
					" case when a.ncc_fk is not null then  isnull(b.ten,'') \n"+
					"      else (case when nv.ten is not null then isnull(nv.ten,'') \n"+
					"                 when kh.ten is not null then isnull(kh.ten,'') \n"+
					"                 when npp.ten is not null then isnull(npp.ten,'') \n"+
					"                 when dvth.ten is not null then isnull(dvth.ten,'') \n"+
					"                 else isnull(f.DIENGIAI,'') end) end  as nccTen, \n"+
					" c.ten as htttTen , isnull(a.iskttduyet,0) as iskttduyet, \n"+
					" d.ten as nguoitao, e.ten as nguoisua, a.PREFIX \n" + 
					"    ,CASE \n"+
					"   	WHEN a.NCC_FK is not null or a.NHANVIEN_FK is not null or a.KHACHHANG_FK is not null THEN \n"+
					"   			(isnull((select cast(isnull(CT.sohoadon,'') as varchar(20)) +', ' as [text()] \n"+
					"   	   		from  ERP_THANHTOANHOADON_HOADON CT \n"+
					"               where CT.THANHTOANHD_FK = A.PK_SEQ \n" + 
					"   	   		For XML PATH ('')),'')) \n"+
					"   	WHEN a.DVTH_FK is not null THEN '' \n" + 
					"   	ELSE '' \n"+
					"   	END	as sohoadon, A.SOTIENTT, isnull(A.SOCHUNGTU,'') SOCTTUDONG, a.NOIDUNGTT, a.MACHUNGTU \n"+
					" from ERP_THANHTOANHOADON a left  join TraphacoERP.dbo.erp_nhacungcap b on a.ncc_fk = b.pk_seq  \n"+
					"  left join TraphacoERP.dbo.NHOMNHACUNGCAPCN f on a.NHOMNCCCN = f.PK_SEQ \n"+
					"  left join TraphacoERP.dbo.NHOMNHACUNGCAPCN_NCC g on f.PK_SEQ = g.NHOMNHACUNGCAPCN_FK \n"+
					"  left join ERP_NHANVIEN nv on nv.pk_seq =  a.nhanvien_fk  \n"+
					"  left join KHACHHANG kh on kh.pk_seq = a.khachhang_fk and isnull(a.isnpp,0) = '0' \n"+
					"  left join NHAPHANPHOI npp on npp.pk_seq = a.khachhang_fk and isnull(a.isnpp,0) = '1' \n"+
					"  left join ERP_DONVITHUCHIEN dvth on dvth.pk_seq = a.dvth_fk \n"+
					"  inner join ERP_HINHTHUCTHANHTOAN c on a.HTTT_FK = c.pk_seq \n"+
					"  inner join NHANVIEN d on a.nguoitao = d.pk_seq inner join NHANVIEN e on a.nguoisua = e.pk_seq \n"+
					" where ((c.PK_SEQ != 100002) or (c.PK_SEQ = 100002 and a.trangthai = 1) ) and a.HTTT_FK = '100001' and a.NPP_FK = "+ this.nppdangnhap + " \n";

		} else
			query = search;

		System.out.println("Query init: " + query);

		this.tthdRs = db.get(query);

		// this.tthdRs = createSplittingData(50, 10, "tthdId desc, NGAYGHINHAN
		// desc, trangthai asc ", query);

		this.nccRs = db.get("select pk_seq, ma + ', ' + ten as nccTen from erp_nhacungcap where trangthai = '1'");
		this.nvRs = db.get("select pk_seq, ma + ', ' + ten as nvTen from erp_nhanvien where trangthai = '1'");
		this.htttRs = db.get("select pk_seq, ma, ten from ERP_HINHTHUCTHANHTOAN where trangthai = '1'");

		String sql = "select 0 as pk_seq, N'Hóa đơn NCC' as ten \n" + "union all \n"
				+ "select 1 as pk_seq, N'Đề nghị tạm ứng' as ten \n" + "union all \n"
				+ "select 2 as pk_seq, N'Chi phí nội bộ' as ten \n" + "union all \n"
				+ "select 3 as pk_seq, N'Chi phí nhận hàng' as ten \n" + "union all \n"
				+ "select 4 as pk_seq, N'Thuế nhập khẩu' as ten \n" + "union all \n"
				+ "select 5 as pk_seq, N'Chi phí khác' as ten \n" + "union all \n"
				+ "select 6 as pk_seq, N'Đề nghị thanh toán' as ten \n" + "union all \n"
				+ "select 7 as pk_seq, N'Khách hàng trả trước' as ten \n" + "union all \n"
				+ "select 8 as pk_seq, N'Chi đề nghị thanh toán' as ten \n" + "union all \n"
				+ "select 9 as pk_seq, N'Chi đề nghị tạm ứng' as ten \n" + "";
		System.out.println("loaihd: " + sql);
		this.loaihoadonRs = db.get(sql);
	}

	public void DBclose() {

		try {
			if (this.nccRs != null)
				this.nccRs.close();

			if (this.htttRs != null)
				this.htttRs.close();

			if (this.tthdRs != null)
				this.tthdRs.close();
			this.db.shutDown();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSochungtu() {

		return this.Sochungtu;
	}

	public void setSochungtu(String sochungtu) {

		this.Sochungtu = sochungtu;
	}

	public String getSohoadon() {

		return this.Sohoadon;
	}

	public void setSohoadon(String sohoadon) {

		this.Sohoadon = sohoadon;
	}

	public void setNvId(String nvid) {
		this.nvId = nvid;
	}

	public String getNvId() {
		return this.nvId;
	}

	public ResultSet getNvList() {
		return this.nvRs;
	}

	public void setNvList(ResultSet nvlist) {
		this.nvRs = nvlist;
	}

	public String getLoaihoadon() {
		return this.loaihoadon;
	}

	public void setLoaihoadon(String loaihoadon) {
		this.loaihoadon = loaihoadon;
	}

	public ResultSet getLoaihoadonList() {
		return this.loaihoadonRs;
	}

	public void setLoaihoadonList(ResultSet loaihoadonRs) {
		this.loaihoadonRs = loaihoadonRs;

	}

	public String getSotien() {
		return this.sotien;
	}

	public void setSotien(String sotien) {
		this.sotien = sotien;
	}

	public void setcongtyId(String congtyId) {
		this.congtyId = congtyId;
	}

	public String getcongtyId() {
		return this.congtyId;
	}

	public String getnppdangnhap() {
		return this.nppdangnhap;
	}

	public void setnppdangnhap(String nppdangnhap) {
		this.nppdangnhap = nppdangnhap;
	}

}
