package geso.dms.center.beans.PhanBoKMApGame.imp;

import java.sql.ResultSet;
import java.util.Hashtable;

import geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGame;
import geso.dms.center.db.sql.dbutils;

public class PhanBoKMApGame implements IPhanBoKMApGame {
	
	String ctkmId;
	String scheme;
	String vungId;
	String khuvucId;
	String diengiai;
	String userId;
	dbutils db;
	Hashtable<String, String> hashTiLe;
	String[] nppIds;
	String[] soluong;
	ResultSet rsTraKM;
	String[] traKMIds;
	ResultSet rsNpp;
	Hashtable<String, String> hashVung;
	Hashtable<String, String> hashKhuVuc;
	String msg;
	 
	public PhanBoKMApGame() {
		
		this.ctkmId = "";
		this.scheme = "";
		this.diengiai = "";
		this.userId = "";
		this.msg = "";
		this.vungId="";
		this.khuvucId="";
		this.hashTiLe = new Hashtable<String, String>();
		this.hashVung = new Hashtable<String, String>();
		this.hashKhuVuc = new Hashtable<String, String>();
		this.db = new dbutils(); 
	}
	
	public boolean save(String dulieu) {
		
		try {
			String query ="";
			query = "select b.ma,b.ten from ("+dulieu+") as data inner join nhaphanphoi b on data.nppId=b.pk_seq"
					+ "\n where data.soluong !=0 and not exists ("
					+ "\n		select npp_fk,count(pk_seq) as tong from daidienkinhdoanh "
					+ "\n 		where npp_fk=data.nppId and trangthai=1 and pk_seq not in (select ddkd_fk from ddkd_kophanbo) group by npp_fk having (count(pk_seq) > 0))";
			
			System.out.println("____check ddkd: select b.ma,b.ten from (dulieu) as data inner join nhaphanphoi b on data.nppId=b.pk_seq"
					+ "\n where data.soluong !=0 and not exists (.......");
			
			ResultSet rsCheckDDKD = this.db.get(query);
			String loiddkd="";
			if(rsCheckDDKD !=null)
			{
				while(rsCheckDDKD.next())
				{
					loiddkd += ""+rsCheckDDKD.getString("ma")+"-"+rsCheckDDKD.getString("ten")+" \n";
				}
			}
			System.out.println("_____ddkd: "+loiddkd);
			if(loiddkd.trim().length()>0)
			{
				this.msg="Không có nhân viên bán hàng đang hoạt động. Vui lòng kiểm tra lại \n" + loiddkd;
				return false;
			}
			this.db.getConnection().setAutoCommit(false);	
			
			if(this.hashTiLe != null)
			{
				for (String key : this.hashTiLe.keySet()) {
					if(this.hashTiLe.get(key) == null)
					{
						this.db.getConnection().rollback();
						this.msg="1. Vui lòng nhập tỉ lệ";
						return false;
					}
					else {
						query ="update ctkm_trakm set xacsuat="+this.hashTiLe.get(key)+" where ctkhuyenmai_fk="+this.ctkmId+" and trakhuyenmai_fk="+key+"";
						System.out.println("____1: "+query);
						if(!this.db.update(query))
						{
							this.db.getConnection().rollback();
							this.msg="1. Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
							return false;
						}
					}
				}
			}
			else {
				this.db.getConnection().rollback();
				this.msg="1.1 Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
				return false;
			}
			query ="delete from phanbo_ctkm_trakm_npp where ctkm_fk="+this.ctkmId+" and trakm_fk in (select trakmId from ("+dulieu+") as data)";
			
			System.out.println("____2: delete from phanbo_ctkm_trakm_npp where ctkm_fk="+this.ctkmId+" and trakm_fk in (select trakmId from........");
			
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg="1. Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
				return false;
			}
			query = "insert into phanbo_ctkm_trakm_npp(ctkm_fk,trakm_fk,npp_fk,sosuat) "
					+ "select "+this.ctkmId+", trakmId, nppId, soluong from ("+dulieu+") as data";
			
			System.out.println("____3: insert into phanbo_ctkm_trakm_npp(ctkm_fk,trakm_fk,npp_fk,sosuat) select "+this.ctkmId+", trakmId, nppId, soluong from (dulieu) as data");
			
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg="2. Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
				return false;
			}
			//phân bổ cho từng ddkd của từng npp
			query ="delete from phanbo_ctkm_trakm_ddkd where ctkm_fk="+this.ctkmId+" and trakm_fk in (select trakmId from ("+dulieu+") as data)";
			
			System.out.println("____4: delete from phanbo_ctkm_trakm_ddkd where ctkm_fk="+this.ctkmId+" and trakm_fk in (select trakmId from (dulieu) as data)");
			
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.msg="3. Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
				return false;
			}
			query = "select ctkm_fk,trakm_fk,npp_fk,sosuat from phanbo_ctkm_trakm_npp where ctkm_fk="+this.ctkmId+" and trakm_fk in (select trakmId from ("+dulieu+") as data) and npp_fk in (select nppId from ("+dulieu+") as data1 where data1.soluong !=0)"; 
			System.out.println("____5: select ctkm_fk,trakm_fk,npp_fk,sosuat from phanbo_ctkm_trakm_npp where ctkm_fk="+this.ctkmId+" and trakm_fk in ................");
			ResultSet rsDDKD = this.db.get(query);
			if(rsDDKD != null)
			{
				while(rsDDKD.next())
				{
					String ctkm_fk = rsDDKD.getString("ctkm_fk");
					String trakm_fk = rsDDKD.getString("trakm_fk");
					String npp_fk = rsDDKD.getString("npp_fk");
					String soluong = rsDDKD.getString("sosuat");
					System.out.println("____soluong: "+soluong);
					String slDDKD = "";
					query= "select count(pk_Seq) as sl,npp_fk from daidienkinhdoanh where npp_fk="+npp_fk+" and pk_Seq not in (select ddkd_fk from ddkd_kophanbo) and trangthai=1 group by npp_fk";
					System.out.println("____6: "+query);
					ResultSet rsSL= this.db.get(query);
					if(rsSL != null)
					{
						if(rsSL.next())
						{
							slDDKD=rsSL.getString("sl");
						}
					}

					 
					query = "insert into phanbo_ctkm_trakm_ddkd(ctkm_fk,trakm_fk,npp_fk,ddkd_fk,sosuat)"
							+ "\n select "+ctkm_fk+","+trakm_fk+","+npp_fk+",pk_seq,floor("+soluong+"/"+slDDKD+") "
							+ "\n from daidienkinhdoanh where npp_fk="+npp_fk+" and trangthai=1 and pk_seq not in (select ddkd_fk from ddkd_kophanbo)  ";
					System.out.println("____7: "+query);
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg="4. Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
						return false;
					}
					query = " select sum(sosuat) as tong,ctkm_fk,trakm_fk,npp_fk "
						  + "\n from phanbo_ctkm_trakm_ddkd where ctkm_fk="+ctkm_fk+" and trakm_fk="+trakm_fk+" "
						  + "\n and npp_fk="+npp_fk+" group by ctkm_fk,trakm_fk,npp_fk";
					System.out.println("____8: "+query);
					String tongDDKD="";
					ResultSet rsTongDDKD = this.db.get(query);
					if(rsTongDDKD != null)
					{
						if(rsTongDDKD.next())
						{
							tongDDKD = rsTongDDKD.getString("tong");
						}
					}
					System.out.println("____tongDDKD: "+tongDDKD);
					double tong=0.0;
					try {
						tong = Double.parseDouble(soluong)-Double.parseDouble(tongDDKD);
					}catch (Exception e) {
						this.db.getConnection().rollback();
						this.msg="5.1 Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
						return false;
					}
					System.out.println("____tong: "+tong);
					query ="update phanbo_ctkm_trakm_ddkd set sosuat=sosuat+"+tong+" "
						 + "\n where ctkm_fk="+ctkm_fk+" and trakm_fk="+trakm_fk+" and npp_fk="+npp_fk+" "
						 + "\n and ddkd_fk in ("
						 + "\n select min(ddkd_fk) from phanbo_ctkm_trakm_ddkd "
						 + "\n		where ctkm_fk="+ctkm_fk+" and trakm_fk="+trakm_fk+" and npp_fk="+npp_fk+" "
						 + "\n 		group by ctkm_fk,trakm_fk,npp_fk) ";
					System.out.println("____9: "+query);
					if(!this.db.update(query))
					{
						this.db.getConnection().rollback();
						this.msg="5. Lỗi trong quá trình phân bổ khuyến mãi. Vui lòng thao tác lại hoặc liên hệ Admin ";
						return false;
					}
				}
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public void createRs() {
		try {
			
			String query = "select pk_seq,ten from vung";
			ResultSet rsVung = this.db.get(query);
			if(rsVung != null)
			{
				while (rsVung.next())
				{
					this.hashVung.put(rsVung.getString("pk_Seq"), rsVung.getString("ten"));
				}
			}
			
			query = "select pk_seq,ten from khuvuc where 1=1";
			if(this.vungId.trim().length()>0)
			{
				query +=" and vung_fk="+this.vungId;
			}
			ResultSet rsKhuVuc = this.db.get(query);
			if(rsKhuVuc != null)
			{
				while (rsKhuVuc.next())
				{
					this.hashKhuVuc.put(rsKhuVuc.getString("pk_Seq"), rsKhuVuc.getString("ten"));
				}
			}
			
		/*	query = "\n select distinct pk_seq, ma,ten, b.sosuat as soluong " + 
					"\n from nhaphanphoi a inner join phanbo_ctkm_trakm_npp b on a.pk_seq=b.npp_fk" + 
					"\n where a.trangthai=1 and b.ctkm_fk= "+ this.ctkmId+
					"\n union"+ 
					"\n select distinct pk_seq, ma,ten, 0 as soluong " + 
					"\n  from nhaphanphoi a inner join ctkm_npp b on a.pk_seq=b.npp_fk " + 
					"\n  where a.trangthai=1 and b.ctkm_fk= "+ this.ctkmId+" "+ 
					"\n and a.pk_Seq not in (select npp_fk from phanbo_ctkm_trakm_npp where ctkm_fk="+this.ctkmId+")";
			System.out.println("____init npp: "+query);
			if(this.vungId.trim().length()>0)
			{
				query +=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk="+this.vungId+")";
			}
			if(this.khuvucId.trim().length()>0)
			{
				query +=" and khuvuc_fk ="+this.khuvucId+"";
			}
			this.rsNpp = this.db.get(query);*/
			
			
			query = "select pk_seq, diengiai, isnull(xacsuat,0) as xacsuat from trakhuyenmai a inner join ctkm_trakm b on a.pk_Seq=b.trakhuyenmai_fk where b.ctkhuyenmai_fk ="+this.ctkmId;
			System.out.println(":::init trakm: \n"+query);
			
			this.rsTraKM = this.db.get(query);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void init() {
		try{
			if(this.ctkmId.trim().length()>0)
			{
				String query = "select scheme,diengiai from ctkhuyenmai where pk_seq="+this.ctkmId;
				ResultSet rsKM = this.db.get(query);
				if(rsKM != null)
				{
					if(rsKM.next())
					{
						this.scheme=rsKM.getString("scheme");
						this.diengiai=rsKM.getString("diengiai");
					}
				}
			}
			createRs();
		}catch (Exception e) {
			e.printStackTrace();
			this.msg= e.getMessage();
		}
	}
	
	public String getCtkmId() {
		return ctkmId;
	}
	public void setCtkmId(String ctkmId) {
		this.ctkmId = ctkmId;
	}
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	public String getDiengiai() {
		return diengiai;
	}
	public void setDiengiai(String diengiai) {
		this.diengiai = diengiai;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public dbutils getDb() {
		return db;
	}
	public void setDb(dbutils db) {
		this.db = db;
	} 
	public String[] getNppIds() {
		return nppIds;
	}
	public void setNppIds(String[] nppIds) {
		this.nppIds = nppIds;
	}
	public String[] getSoluong() {
		return soluong;
	}
	public void setSoluong(String[] soluong) {
		this.soluong = soluong;
	}
	public ResultSet getRsTraKM() {
		return rsTraKM;
	}
	public void setRsTraKM(ResultSet rsTraKM) {
		this.rsTraKM = rsTraKM;
	}
	public String[] getTraKMIds() {
		return traKMIds;
	}
	public void setTraKMIds(String[] traKMIds) {
		this.traKMIds = traKMIds;
	}

	public ResultSet getRsNpp() {
		return rsNpp;
	}

	public void setRsNpp(ResultSet rsNpp) {
		this.rsNpp = rsNpp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getVungId() {
		return vungId;
	}

	public void setVungId(String vungId) {
		this.vungId = vungId;
	}

	public String getKhuvucId() {
		return khuvucId;
	}

	public void setKhuvucId(String khuvucId) {
		this.khuvucId = khuvucId;
	}

	public Hashtable<String, String> getHashVung() {
		return hashVung;
	}

	public void setHashVung(Hashtable<String, String> hashVung) {
		this.hashVung = hashVung;
	}

	public Hashtable<String, String> getHashKhuVuc() {
		return hashKhuVuc;
	}

	public void setHashKhuVuc(Hashtable<String, String> hashKhuVuc) {
		this.hashKhuVuc = hashKhuVuc;
	}

	public Hashtable<String, String> getHashTiLe() {
		return hashTiLe;
	}

	public void setHashTiLe(Hashtable<String, String> hashTiLe) {
		this.hashTiLe = hashTiLe;
	}
	
	public static void main(String[] args) {
		
		String dulieu = "\r\n" + 
				"select data.ctkmId,data.trakmId,npp.pk_seq as nppId, data.soluong\r\n" + 
				" from (\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8176' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8077' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7128' as manpp, 16 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7913' as manpp, 27 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7404' as manpp, 69 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8300' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2677' as manpp, 44 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8222' as manpp, 56 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2579' as manpp, 15 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3701' as manpp, 32 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '5952' as manpp, 15 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8235' as manpp, 7 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8263' as manpp, 3 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8321' as manpp, 8 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '5525' as manpp, 60 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '4055' as manpp, 39 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8294' as manpp, 6 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7906' as manpp, 24 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '4019' as manpp, 27 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8130' as manpp, 20 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8144' as manpp, 20 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '5724' as manpp, 8 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7876' as manpp, 35 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3831' as manpp, 35 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7162' as manpp, 12 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7123' as manpp, 33 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8211' as manpp, 9 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8015' as manpp, 48 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '4046' as manpp, 54 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6994' as manpp, 32 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3845' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2988' as manpp, 49 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7461' as manpp, 36 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6434' as manpp, 32 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3689' as manpp, 88 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8157' as manpp, 9 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8260' as manpp, 19 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8297' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8056' as manpp, 5 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2652' as manpp, 70 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7847' as manpp, 59 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7926' as manpp, 11 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2689' as manpp, 11 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3690' as manpp, 54 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3698' as manpp, 16 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2593' as manpp, 44 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8141' as manpp, 46 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7873' as manpp, 27 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3293' as manpp, 13 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3571' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3683' as manpp, 14 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7405' as manpp, 41 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2655' as manpp, 25 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8195' as manpp, 28 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7872' as manpp, 74 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2542' as manpp, 31 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7772' as manpp, 14 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '5527' as manpp, 27 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8041' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8189' as manpp, 15 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6713' as manpp, 26 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3681' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6114' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6153' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3458' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7336' as manpp, 40 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3647' as manpp, 36 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8212' as manpp, 14 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7259' as manpp, 53 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7074' as manpp, 14 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3118' as manpp, 45 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7890' as manpp, 15 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3638' as manpp, 44 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7704' as manpp, 2 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6671' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7881' as manpp, 42 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2913' as manpp, 27 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7943' as manpp, 28 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3221' as manpp, 15 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8416' as manpp, 18 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2910' as manpp, 103 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7832' as manpp, 49 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8185' as manpp, 45 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8230' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8324' as manpp, 6 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8315' as manpp, 61 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7251' as manpp, 65 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2639' as manpp, 6 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6632' as manpp, 52 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7429' as manpp, 23 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7037' as manpp, 70 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7398' as manpp, 55 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8106' as manpp, 60 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7866' as manpp, 40 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3097' as manpp, 21 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '2755' as manpp, 49 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8108' as manpp, 29 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8417' as manpp, 45 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7467' as manpp, 16 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '6275' as manpp, 31 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3687' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3177' as manpp, 32 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3470' as manpp, 30 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7383' as manpp, 46 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '7851' as manpp, 25 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '3574' as manpp, 32 as soluong union all\r\n" + 
				"select 387278 as ctkmId, 187832 as trakmId, '8278' as manpp, 39 as soluong) as data inner join nhaphanphoi npp on data.manpp=npp.ma";
		PhanBoKMApGame obj = new PhanBoKMApGame();
		obj.ctkmId="387278";
		Hashtable<String, String> a = new Hashtable<>();
		a.put("187832", "0.0");
		obj.save(dulieu);
	}
	
}
