package geso.dms.distributor.beans.ctchietkhau.imp;

import geso.dms.distributor.beans.ctchietkhau.ICtChietKhauList;
import geso.dms.distributor.beans.ctchietkhau.imp.CtChietKhauList;
import geso.dms.distributor.beans.ctchietkhau.ICtChietKhau;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import geso.dms.center.db.sql.dbutils;

public class CtChietKhauList implements ICtChietKhauList
{
	private static final long serialVersionUID = -927977546783610214L;
	
	String userId;
	String ten;
	ResultSet dvkd;
	String dvkdId;
	String trangthai;
	List<ICtChietKhau> bgblclist;
	dbutils db;
	String msg = "";
	
	String nppId= "";
	
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	//Constructor
	public CtChietKhauList(String[] param)
	{
		this.ten = param[0];		
		this.dvkdId = param[1];
		this.trangthai = param[3];
		this.db = new dbutils();
	}
	
	public CtChietKhauList(String userId)
	{
		this.userId = userId;
		this.ten= "";		
		this.dvkdId= "";
		this.trangthai = "";
		this.db = new dbutils();
		getNppInfo();
		init("");
	}
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	
	public String getDvkdId() 
	{
		return this.dvkdId;
	}

	public void setDvkdId(String dvkdId) 
	{
		this.dvkdId = dvkdId;
	}
	
	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public List<ICtChietKhau> getBgblcList() 
	{
		return this.bgblclist;
	}

	public void setBgblcList(List<ICtChietKhau> bgblclist) 
	{
		this.bgblclist = bgblclist;
	}


	public ResultSet getDvkd() 
	{
		return this.dvkd;
	}

	public void setDvkd(ResultSet dvkd) 
	{
		this.dvkd = dvkd;
	}

	public boolean saveNewBgblc() 
	{
		return false;
	}

	public boolean UpdateBgblc() 
	{
		return false;
	}

	public void getNppInfo()
	{	
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId); // dùng hàm để lấy 1 số thông tin từ npp
		
	}
	private void createDvkdRS(){  				
				
		//this.dvkd  =  this.db.get("select donvikinhdoanh as dvkd, pk_seq as dvkdId from donvikinhdoanh where trangthai='1' ");;
		this.dvkd  =  this.db.get("select distinct a.donvikinhdoanh as dvkd, a.pk_seq as dvkdId from donvikinhdoanh a,nhacungcap_dvkd b where a.pk_seq = b.DVKD_fk and b.checked ='1' and a.trangthai = '1' order by a.donvikinhdoanh");
		
	}

	public void createBanggiablcBeanList(String query){  	
	    
		ResultSet rs =  db.get(query);
		List<ICtChietKhau> bgblclist = new ArrayList<ICtChietKhau>();
		if (rs != null){		
			ICtChietKhau bgblcBean;
			String[] param = new String[15];
			try{
				while(rs.next()){
					param[0]= rs.getString("id");
					param[1]= rs.getString("diengiai");
					param[2]= "";
					param[3]= rs.getString("trangthai");
					param[4]= rs.getString("ngaytao");
					param[5]= rs.getString("nguoitao");
					param[6]= rs.getString("ngaysua");
					param[7]= rs.getString("nguoisua");
					param[8]= rs.getString("tenkh");
					param[9]= rs.getString("mahopdong");
					
					bgblcBean = new CtChietKhau(param);
					bgblclist.add(bgblcBean);															
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		this.bgblclist = bgblclist;
	}

	public void init(String search){

		String query;
		
		if (search.length()>0){
			query = search;
		}else{
			
			query =  "\n  select a.trangthai, a.pk_seq as id, a.diengiai, c.ten as nguoitao, a.ngaytao, d.ten as nguoisua  " + 
					 "\n 		,a.ngaysua as ngaysua , kh.mafast makh, kh.ten tenkh, hd.mahopdong   " + 
					 "\n  from ChietkhauBacSi a  " + 
					 "\n  inner join khachhang kh on kh.pk_seq = a.khachhang_fk  " + 
					 "\n  inner join erp_hopdongnpp hd on hd.pk_seq = a.hopdong_fk   " + 
					 "\n  inner join nhanvien c on a.nguoitao = c.pk_seq  " + 
					 "\n  inner join nhanvien d on a.nguoisua = d.pk_seq  " + 
					 "\n  where   a.npp_fk = " + this.nppId;
			System.out.println("Init list: "+query);
		}
		createBanggiablcBeanList(query);
	    
		createDvkdRS();
	}

	@Override
	public void DbClose() {
		try{
		// TODO Auto-generated method stub
		if(this.dvkd!=null){
			this.dvkd.close();
		}
		
		if(this.db!=null){
			db.shutDown();
		}
		}catch(Exception er){
			
		}
	}
	
	public String getNppId() {
		return nppId;
	}
	
}

