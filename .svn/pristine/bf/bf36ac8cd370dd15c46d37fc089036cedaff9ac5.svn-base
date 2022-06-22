package geso.dms.center.beans.ctkhuyenmai.imp;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.beans.ctkhuyenmai.ICtkhuyenmaiList;
import geso.dms.center.util.IPhanTrang;
import geso.dms.center.util.PhanTrang;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

public class CtkhuyenmaiList extends Phan_Trang implements ICtkhuyenmaiList 
{
	private static final long serialVersionUID = -9217977546733610214L;

	String userId; 
	
	String diengiai;
	String tungay;
	String denngay;
	String trangthai;
	String msg;
	String nppId,khuvucId,vungId,phanbo;
	ResultSet khuvucRs,vungRs,nppRs;
	String npp;
	String vung;
	String khuvuc;
	int num;
	ResultSet ctkmList;
//	List<ICtkhuyenmai> ctkmList;
	
	dbutils db;

	private int[] listPages;
	
	private int currentPages;
	
	private HttpServletRequest request;
	
	public CtkhuyenmaiList(String[] param)
	{
		this.diengiai = param[0];
		this.tungay = param[1];
		this.denngay = param[2];
		this.msg = "";
		this.num = Integer.parseInt(param[3]);
		currentPages = 1;
		listPages = new int[num];
		for(int i = 0; i < this.num; i++)
			listPages[i]=i+1;
		this.nppId="";
		this.vungId="";
		this.khuvucId="";
		this.phanbo="";
		db = new dbutils();
	}
	
	public CtkhuyenmaiList()
	{
		this.diengiai = "";
		this.denngay = "";
		this.tungay = "";
		this.trangthai = "";
		this.msg = "";
		currentPages = 1;
		num = 1;
		this.nppId="";
		this.vungId="";
		this.khuvucId="";
		this.phanbo="";
		this.db = new dbutils();
	}
	
	public HttpServletRequest getRequestObj() 
	{
		return this.request;
	}
	public void setNpp(String npp) {
		this.npp = npp;
	}

	public void setRequestObj(HttpServletRequest request) 
	{
		this.request = request;
	}
	
	public int getNum(){
		return this.num;
	}
	public void setNum(int num){
		this.num = num;
		listPages = new int[num];
		for(int i = 0; i < this.num; i++)
			listPages[i]=i+1;
	}
	public String getUserId()
	{
		return this.userId;
	}


	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	public void setvung(String vung)
	{
		this.vung = vung;
	}
	public String getkhuvuc()
	{
		return this.khuvuc;
	}
	public void setkhuvuc(String khuvuc)
	{
		this.khuvuc = khuvuc;
	}
	public String getDiengiai() 
	{		
		return this.diengiai;
	}
	
	public void setDiengiai(String diengiai) 
	{
		this.diengiai = diengiai;
	}
	
	public String getTrangthai() 
	{		
		return this.trangthai;
	}
	
	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
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

	
	public ResultSet getCtkmList() 
	{		
		return this.ctkmList;
	}


	
	public void setCtkmList(ResultSet ctkmlist) 
	{
		this.ctkmList = ctkmlist;	
	}

	public String getPhanloai() {
		return phanloai;
	}
	public void init(String search) 
	{
		this.db = new dbutils();

		String query = "";	
		if (search.length() == 0)
		{
			String str = " '1' as isnppTao, "; 
			if(!this.phanloai.equals("2"))
			{
				str = " case when a.nppTao_Fk = '"+ this.nppTaoId +"' then '1' else '0' end as isnppTao, ";
			}
			query = "select "+ str +" isnull(a.ngunghoatdong,0) as ngunghd, a.PK_SEQ as ctkmId, a.scheme, substring(diengiai,1,100) as diengiai, a.TUNGAY as tungay , a.DENNGAY as denngay, case when " +
					" a.LOAICT is null then 1 else a.LOAICT end as type, isnull(a.NGANSACH,0) as ngansach, a.DASUDUNG, a.NGAYTAO, a.NGAYSUA, b.TEN as nguoitao, c.TEN as nguoisua,isnull(a.LOAINGANSACH,0) as LoaiNganSach," +
					"(select COUNT(CTKM_FK) from PhanBoKhuyenMai where CTKM_FK=a.PK_SEQ)as SoNpp,(select count(*) from phanbokhuyenmai where ctkm_fk = a.pk_seq ) as ispb "+
					" from CTKHUYENMAI a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ" +
					" where 1=1 and a.loaict in (1,2,5) ";
			System.out.println("Cau select : "+query);
		}
		else
		{
			query = search;
		}
		if(this.phanloai.equals("2"))
		{
			query += " and a.nppTao_Fk is null ";
		}
		else // CN/ NPP khai báo ctkm
		{
			if(this.nppTaoId.length() > 0)
			{
				//query += " and a.nppTao_Fk =" + this.nppTaoId;
				query += " and ( a.nppTao_Fk ='"+ this.nppTaoId +"' or exists ( select 1 from ctkm_npp km where km.NPP_FK = '"+ this.nppTaoId +"' and km.CTKM_FK = a.pk_seq and isnull(km.chon,0) = '1' ) ) ";
			}
		}
		
		System.out.println("query : "+ query);
		this.ctkmList =  createSplittingData(50, 10, " tungay desc ", query);
		query="select PK_SEQ as vungId,TEN as vungTen from VUNG";
		this.vungRs=this.db.get(query);
		
		query=" select PK_SEQ as khuvucId,TEN as khuvucTen,VUNG_FK as vungId from KHUVUC where 1=1 ";
		if(this.vungId.length()>0)
			query+=" and vung_fk ="+this.vungId+"";
		this.khuvucRs=this.db.get(query);
		
		query="select PK_SEQ as nppId,TEN as nppTen,MA as nppMa from NHAPHANPHOI where 1=1 ";
		if(this.khuvucId.length()>0)
			query+=" and khuvuc_fk in ("+this.khuvucId+") ";
		if(this.vungId.length()>0)
			query+=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk="+this.vungId+" )";
		if(this.phanloai.equals("1"))
		{
			query += " and ( pk_seq = "+this.nppTaoId+" or tructhuoc_fk ="+this.nppTaoId+") ";
		}
		
		this.nppRs=this.db.get(query);
		
	}
	
	
	public void DBclose() 
	{
		
			try
			{
				if(this.ctkmList!=null)
					this.ctkmList.close();
				
				if(this.nppRs!=null)
					this.nppRs.close();
				
				if(this.vungRs!=null)
					this.vungRs.close();
				
				if(this.khuvucRs!=null)
					this.khuvucRs.close();
				
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
				
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
		
		ResultSet rs = db.get("select count(*) as c from CTKHUYENMAI");
		try 
		{
			rs.next();
			int count = Integer.parseInt(rs.getString("c"));
			rs.close();
			return count;
		}
		catch (SQLException e) {}
		
		return 0;
	}

	
	public int[] getNewPagesList(String action, int num, int currentPage, int theLastPage, String[] listPage) {
		
		IPhanTrang pt = new PhanTrang();
		return pt.getNewPagesList(action, num, currentPage, theLastPage, listPage);
	}

	public String getMessage() 
	{
		return this.msg;
	}

	public void setMessage(String msg) 
	{
		this.msg = msg;
	}

	public String getNppId()
	{
		return nppId;
	}

	public void setNppId(String nppId)
	{
		this.nppId = nppId;
	}

	public String getKhuvucId()
	{
		return khuvucId;
	}

	public void setKhuvucId(String khuvucId)
	{
		this.khuvucId = khuvucId;
	}

	public String getVungId()
	{
		return vungId;
	}

	public void setVungId(String vungId)
	{
		this.vungId = vungId;
	}

	public String getPhanbo()
	{
		return phanbo;
	}

	public void setPhanbo(String phanbo)
	{
		this.phanbo = phanbo;
	}

	public ResultSet getKhuvucRs()
	{
		return khuvucRs;
	}

	public void setKhuvucRs(ResultSet khuvucRs)
	{
		this.khuvucRs = khuvucRs;
	}

	public ResultSet getVungRs()
	{
		return vungRs;
	}

	public void setVungRs(ResultSet vungRs)
	{
		this.vungRs = vungRs;
	}

	public ResultSet getNppRs()
	{
		return nppRs;
	}

	public void setNppRs(ResultSet nppRs)
	{
		this.nppRs = nppRs;
	}

	String phanloai = "";
	String loaiUser = "";
	String nppTaoId=  "";
	String view = "";
	public String getNppTaoId() {
		return nppTaoId;
	}
	public void init_user()
	{
		try{
			
			Utility Ult = new Utility();
			String query="select phanloai,loai from nhanvien where pk_seq="+this.userId;
			System.out.println(" user :" + query);
			ResultSet rs=this.db.get(query);
			if(rs!=null){
				if(rs.next()){

					this.phanloai = rs.getString("phanloai");
					System.out.println("Phan loai : "+this.phanloai);					 				
					this.loaiUser =  rs.getString("loai");
					if( rs.getString("phanloai").equals("1")){
						this.nppTaoId = Ult.getIdNhapp(this.userId);
					}
					rs.close();
				}
			}
		}catch(Exception er){

		}
		System.out.println("nppTaoId = "+ this.nppTaoId);
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public dbutils getDb() {
		return db;
	}

	@Override
	public String getvung() {
		// TODO Auto-generated method stub
		return null;
	}
	
}