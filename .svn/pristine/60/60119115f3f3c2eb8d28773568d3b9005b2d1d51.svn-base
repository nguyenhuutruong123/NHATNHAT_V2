package geso.dms.erp.beans.lenhsanxuat.imp;

import java.sql.ResultSet;

import geso.dms.erp.db.sql.dbutils;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.erp.beans.lenhsanxuat.IErpChuyenkhoSXList;

public class ErpChuyenkhoSXList extends Phan_Trang implements IErpChuyenkhoSXList
{
	private static final long serialVersionUID = 1L;
	String userId;
	String tungay;
	String denngay;
	String sopo;
	String masanpham;
	String trangthai;
	String msg;
	String isnhanHang;
	
	ResultSet lsxRs;
	ResultSet ndxRs;
	String ndxId;
	String task;
	
	String Nppid;
	private int num;
	private int[] listPages;
	private int currentPages;
	String Ngayyeucau="";
	String sochungtu="";
	dbutils db;
	String sophieuin="";
	
	String soyeucau = "";
	
	String lydo = "";
	
	public ErpChuyenkhoSXList()
	{
		this.tungay = "";
		this.denngay = "";
		this.trangthai = "";
		this.ndxId = "";
		this.task="";
		this.masanpham = "";
		this.sochungtu="";
		this.Nppid="";
		String Ngayyeucau="";
		this.soyeucau = "";
		this.msg = "";	
		this.isnhanHang = "0";
		currentPages = 1;
		num = 1;
		sopo="";
		this.db = new dbutils();
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getTrangthai()
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}
	
	public int getNum()
	{
		return this.num;
	}
	
	public void setNum(int num)
	{
		this.num = num;
		listPages = PhanTrang.getListPages(num);
	}

	public int getCurrentPage()
	{
		return this.currentPages;
	}

	public void setCurrentPage(int current) 
	{
		this.currentPages = current;
	}
	
	public String getSopo() {
		return sopo;
	}
	public void setSopo(String sopo) {
		this.sopo = sopo;
	}
	
	public int[] getListPages() 
	{
		return this.listPages;
	}

	public void setListPages(int[] listPages) 
	{
		this.listPages = listPages;
	}

	public int getLastPage() 
	{
		ResultSet rs = db.get("select count(*) as c from ERP_YEUCAUNGUYENLIEU");
		return PhanTrang.getLastPage(rs);
	}

	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage)
	{
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getNdxId()
	{
		return this.ndxId;
	}

	public void setNdxId(String ndxId) 
	{
		this.ndxId = ndxId;
	}
	
	public ResultSet getNdxList()
	{
		return this.ndxRs;
	}

	public void setNdxList(ResultSet ndxList) 
	{
		this.ndxRs = ndxList;	
	}
	
	
	
	public void init(String search)
	{
		String query = "";
		
		if(search.length() > 0)
			query = search;
		else
		{
			query = " select   lenhsanxuat_fk,muahang_fk ,c.loai  as loaikho , isnull(COXACNHANNHAPKHO,'0')" +
					" as COXACNHANNHAPKHO,a.PK_SEQ, a.trangthai, a.ngaychuyen, " +
					" a.noidungxuat_fk as ndxId, b.ma + ', ' + b.ten as noidungxuat, isnull(a.lydo, '') as lydo,  " +
					" NV.TEN as nguoitao, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ,  " +
					"(select cast(isnull((SUM(soluongyeucau)-SUM(soluongxuat)),0) as numeric(18,3)) as lech from   ERP_YEUCAUCHUYENKHO  y  " +
					" left join ERP_YEUCAUCHUYENKHO_SANPHAM z on " +
					" z.CHUYENKHO_FK=y.PK_SEQ where y.PK_SEQ = a.PK_SEQ group by z.CHUYENKHO_FK )  as chenhlech," +
					"		(  select COUNT(*) from ERP_YEUCAUCHUYENKHO yc inner join ERP_CHUYENKHO ck on yc.PK_SEQ=ck.YEUCAUCHUYENKHO_FK  " +
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
					" from ERP_YEUCAUCHUYENKHO a " +
					" inner join ERP_NOIDUNGNHAP b on a.noidungxuat_fk = b.pk_seq  " +
					" left join ERP_KHOTT c on a.KHONHAN_FK = c.PK_SEQ " +
					" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ  " +
					" where 1=1 ";
			if(this.task.equals("shop")){
				Utility util=new Utility();
				this.Nppid=util.getIdNhapp(this.userId);
				query=query+ " and c.loai='8' and c.npp_fk="+this.Nppid;
			}else if(this.task.equals("lsx")) {
				query=query+ " and lenhsanxuat_fk is not null  ";
			}else if(this.task.equals("mh")) {
				query=query+ " and muahang_fk is not null  ";
			}
						 
		}
		
		System.out.println("[ErpChuyenKhoSXList.init] query = " + query);
		this.lsxRs = createSplittingDataNew(this.db,  50, 10, "ngaychuyen desc, pk_seq desc, trangthai asc ", query);
		
		//Lấy danh sách nội dung xuất
		query = 	"SELECT PK_SEQ,TEN FROM ERP_NOIDUNGNHAP WHERE MA LIKE 'X%' AND TRANGTHAI = '1' " +
						"AND PK_SEQ NOT IN (100002, 100003, 100007, 100022, 100023) ORDER BY TEN ";
		this.ndxRs = this.db.get(query);

	}
	
	public void DBclose() 
	{
		try{
			if(this.ndxRs != null) this.ndxRs.close();
			if(this.lsxRs != null) this.lsxRs.close();
			this.db.shutDown();
		}catch(java.sql.SQLException e){}
	}

	
	public String getTungay() 
	{
		return this.tungay;
	}

	public void setTungay(String tungay) 
	{
		this.tungay = tungay;
	}

	public String getDenngay() 
	{
		return this.denngay;
	}

	public void setDenngay(String denngay)
	{
		this.denngay = denngay;
	}

	public String getMasp() 
	{
		return this.masanpham;
	}

	public void setMasp(String masp) 
	{
		this.masanpham = masp;
	}

	public ResultSet getLsxRs() 
	{
		return this.lsxRs;
	}

	public void setLsxRs(ResultSet lsxRs) 
	{
		this.lsxRs = lsxRs;
	}

	public String getIsnhanHang() 
	{
		return this.isnhanHang;
	}

	public void setIsnhanHang(String isnhanHang) 
	{
		this.isnhanHang = isnhanHang;
	}
	

	@Override
	public String getNppid() {
		// TODO Auto-generated method stub
		return this.Nppid;
	}

	@Override
	public void setNppid(String Nppid) {
		// TODO Auto-generated method stub
		this.Nppid=Nppid;
	}

	@Override
	public String gettask() {
		// TODO Auto-generated method stub
		return this.task;
	}

	@Override
	public void settask(String _task) {
		// TODO Auto-generated method stub
		this.task=_task;
	}

	@Override
	public String getNgayyeucau() {
		// TODO Auto-generated method stub
		return this.Ngayyeucau;
	}

	@Override
	public void setNgayYeucau(String nyc) {
		// TODO Auto-generated method stub
		this.Ngayyeucau=nyc;
	}

	@Override
	public String getPoLsx() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPoLsx(String PoLsx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getsochungtu() {
		// TODO Auto-generated method stub
		return this.sochungtu;
	}

	@Override
	public void setsochungtu(String soct) {
		// TODO Auto-generated method stub
		this.sochungtu=soct;
	}

	@Override
	public String getsophieuin() {
		// TODO Auto-generated method stub
		return this.sophieuin;
	}

	@Override
	public void setsophieuin(String sophieuin) {
		// TODO Auto-generated method stub
		this.sophieuin=sophieuin ;
	}

	@Override
	public void init_xh(String search) {
	String query = "";
		
		if(search.length() > 0)
			query = search;
		else
		{
			query = " select   isnull(ISDANGVANCHUYEN,'0') as ISDANGVANCHUYEN ,isnull(YEUCAUCHUYENKHO_FK,0) as soyeucau  ,lenhsanxuat_fk,muahang_fk ,c.loai  as loaikho , isnull(COXACNHANNHAPKHO,'0') as COXACNHANNHAPKHO,a.PK_SEQ, a.trangthai, a.ngaychuyen, " +
					" a.noidungxuat_fk as ndxId, b.ma + ', ' + b.ten as noidungxuat, isnull(a.lydo, '') as lydo,  " +
					" NV.TEN as nguoitao, a.NGAYSUA, a.NGAYTAO, NV2.TEN as nguoisua ,  " +
					"(select cast(isnull((SUM(soluongyeucau)-SUM(soluongxuat)),0) as numeric(18,3)) as lech from   ERP_CHUYENKHO  y  " +
					" left join ERP_CHUYENKHO_SANPHAM z on "+ 
					"z.CHUYENKHO_FK=y.PK_SEQ where y.PK_SEQ = a.PK_SEQ group by z.CHUYENKHO_FK )  as chenhlech " +
					
					" from ERP_CHUYENKHO a " +
					" inner join ERP_NOIDUNGNHAP b on a.noidungxuat_fk = b.pk_seq  " +
					" left join ERP_KHOTT c on a.KHONHAN_FK = c.PK_SEQ " +
					" inner join NHANVIEN nv on a.NGUOITAO = nv.PK_SEQ   " +
					" inner join NHANVIEN nv2 on a.NGUOISUA = nv2.PK_SEQ  " +
					" where 1=1 ";
			if(this.task.equals("shop")){
				Utility util=new Utility();
				this.Nppid=util.getIdNhapp(this.userId);
				query=query+ " and c.loai='8' and c.npp_fk="+this.Nppid;
			}else if(this.task.equals("lsx")) {
				query=query+ " and lenhsanxuat_fk is not null  ";
			}else if(this.task.equals("mh")) {
				query=query+ " and muahang_fk is not null  ";
			}
						 
		}
		
		System.out.println("[ErpChuyenKhoSXList.init] query = " + query);
		this.lsxRs = createSplittingDataNew(this.db, 50, 10, "ngaychuyen desc, pk_seq desc, trangthai asc ", query);
		
		//Lấy danh sách nội dung xuất
		query = 	"SELECT PK_SEQ,TEN FROM ERP_NOIDUNGNHAP WHERE MA LIKE 'X%' AND TRANGTHAI = '1' " +
						"AND PK_SEQ NOT IN (100002, 100003, 100007, 100022, 100023) ORDER BY TEN ";
		this.ndxRs = this.db.get(query);
	}
	public boolean XoaYeuCauVaXuatKho(String ycid)
	{
		dbutils db = new dbutils();		
		try
		{					
			String q="select  a.pk_seq as ycid, b.pk_seq as ckid, b.trangthai as ck_trangthai from ERP_YEUCAUCHUYENKHO a " +
					"inner join ERP_CHUYENKHO b on a.PK_SEQ=b.YEUCAUCHUYENKHO_FK" +
					" where a.pk_seq='"+ycid+"'";	
			System.out.println(" alo test "+q);
			ResultSet rs1= db.get(q);
			if(rs1!=null)
			{
				while(rs1.next())
				{	
					System.out.println("intungdong"+rs1.getString("ck_trangthai"));
					System.out.println("intungdong"+( rs1.getString("ck_trangthai").equals("1") ));
					
					if(!rs1.getString("ck_trangthai").equals("1"))					
						return false;
					
				}
			}										
		//db.shutDown();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			//db.shutDown();
			return false;
		}
		
		return true;
		
	}

	public String getSoyeucau() 
	{
		return this.soyeucau;
	}

	public void setSoyeucau(String soyeucau) 
	{
		this.soyeucau = soyeucau;
	}
	
	@Override
	public String getLydo() {
		return this.lydo;
	}

	@Override
	public void setLydo(String lydo) {
		// TODO Auto-generated method stub
		this.lydo = lydo;
	}

	public String DeleteChuyenKho(String id)
	{
		String msg = "";
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "";
			
			// Cập nhật trạng thái Chuyển kho
			
			query = " update ERP_CHUYENKHO set trangthai = '4' where PK_SEQ = '"+ id +"' ";
			if(!db.update(query))
			{
				msg = "Không thể cập nhật ERP_CHUYENKHO " + query;
				db.getConnection().rollback();
				return msg;
			}
			
			// Kiểm tra yêu cầu còn Chuyển kho nào không :
			String command = "select  COUNT(*) as dem "
						   + "from ERP_CHUYENKHO "
						   + "where TRANGTHAI != 4 AND YEUCAUCHUYENKHO_FK in ( select YEUCAUCHUYENKHO_FK from ERP_CHUYENKHO where PK_SEQ =  "+ id +") ";
			ResultSet rs= db.get(command);
			int dem = 0;
			if(rs!= null)
			{
				while(rs.next())
				{
					dem = rs.getInt("dem");
				}rs.close();
			}			
			
			// Cập nhật trạng thái Yêu cầu chuyển kho
			
			if(dem <= 0)
			{
				query = "update ERP_YEUCAUCHUYENKHO set trangthai = '1' where PK_SEQ = ( select YEUCAUCHUYENKHO_FK from ERP_CHUYENKHO where PK_SEQ =  "+ id +")"; 
				if(!db.update(query))
				{
					msg = "Không thể cập nhật ERP_YEUCAUCHUYENKHO " + query;
					db.getConnection().rollback();
					return msg;
				}
			}
			
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "";	
	}
}
