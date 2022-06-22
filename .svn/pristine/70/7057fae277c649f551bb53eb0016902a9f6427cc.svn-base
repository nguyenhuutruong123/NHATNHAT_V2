package geso.dms.center.beans.bundle.imp;

import java.io.Serializable;

import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.beans.bundle.*;
import geso.dms.center.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class BoBundle implements IBoBundle, Serializable {
	private static final long serialVersionUID = -9217977546733610214L;

	String userId= "";
	String id;
	String ngaygiaodich= "";
	String trangthai= "";
	String ngaytao= "";
	String nguoitao= "";
	String ngaysua= "";
	String nguoisua= "";
	String msg= "";
	String ghichu= "";


	ResultSet spRs;
	ResultSet kholist;
	ResultSet nppRs;
	ResultSet kbhRs;
	ResultSet dvkdRs;

	String spId = "";
	double soluong = 0;
	String solo="";
	String ngayhethan = "";


	String nppId= "";
	String khoId = "";
	String kbhId = "";
	String dvkdId = "";






	List<ISanpham> splist;


	Hashtable<String, Integer> spThieuList;


	dbutils db;


	Utility util;



	public BoBundle(String id) {
		this.id = id;
		this.ngaygiaodich = "";

		this.trangthai = "";
		this.ngaytao = "";
		this.nguoitao = "";
		this.ngaysua = "";
		this.nguoisua = "";	
		this.msg = "";
		this.spThieuList = new Hashtable<String, Integer>();	
		this.ghichu = "";
		this.db = new dbutils();
		this.util = new Utility();
	}

	public BoBundle(String id, String nppId) {
		this.id = id;
		this.nppId = nppId;
		this.util = new Utility();
		this.CreateSpList();
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;

	}


	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNgaygiaodich() {
		return this.ngaygiaodich;
	}

	public void setNgaygiaodich(String ngaygiaodich) {
		this.ngaygiaodich = ngaygiaodich;
	}

	public String getTrangthai() {
		if (this.trangthai == null)
			this.trangthai = "0";
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) {
		this.trangthai = trangthai;
	}

	public String getNgaytao() {
		return this.ngaytao;
	}

	public void setNgaytao(String ngaytao) {
		this.ngaytao = ngaytao;
	}

	public String getNguoitao() {
		return this.nguoitao;
	}

	public void setNguoitao(String nguoitao) {
		this.nguoitao = nguoitao;
	}

	public String getNgaysua() {
		return this.ngaysua;
	}

	public void setNgaysua(String ngaysua) {
		this.ngaysua = ngaysua;
	}

	public String getNguoisua() {
		return this.nguoisua;
	}

	public void setNguoisua(String nguoisua) {
		this.nguoisua = nguoisua;
	}

	public String getMessage() {
		return this.msg;
	}

	public void setMessage(String msg) {
		this.msg = msg;
	}

	public String getNppId() {
		return this.nppId;
	}

	public void setNppId(String nppId) {
		this.nppId = nppId;
	}

	public List<ISanpham> getSpList() {
		return this.splist;
	}

	public void setSpList(List<ISanpham> splist) {
		this.splist = splist;
	}

	public Hashtable<String, Integer> getSpThieuList() {
		return this.spThieuList;
	}

	public void setSpThieuList(Hashtable<String, Integer> spThieuList) {
		this.spThieuList = spThieuList;
	}



	public boolean CreateDh(List<ISanpham> splist) {
		geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();
		String query="";


		if (this.spThieuList.size() > 0) {
			this.msg = "Trong kho khong du so luong mot so san pham ban chon, vui long chon lai so luong ...";
			return false;
		} else {




			String sanpham = "";
			String sqlCHECK = "";

			for (int m = 0; m < splist.size(); m++) 
			{
				ISanpham sp = splist.get(m);
				sqlCHECK += " select pk_seq as sanpham_fk, '"+ sp.getSoluong()+ "' as soluong from SANPHAM where ma = '" + sp.getMasanpham() + "'  ";
				if (m < splist.size() - 1)
					sqlCHECK += " union ";
			}



			String msg1=this.check_solo_tong_va_chitiet(splist,"(Hàng bán)");
			if(msg1.length() >0){
				this.msg= msg1;
				return false;
			}


			query = "select b.TEN, b.MA, a.available, isnull(dh.soluong, 0) as soluong " +
			"from NHAPP_KHO a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
			"	left join  	( " +sqlCHECK + "	) " +
			"	dh on b.pk_seq = dh.sanpham_fk   " +
			"where a.NPP_FK = '" + this.nppId +"' " +
			"		and a.kho_fk = '" +this.khoId + "' and KBH_FK =  " + this.kbhId +
			"		and a.available < isnull(dh.soluong, 0) ";

			System.out.println("---CHECK TON KHO: " + query);
			ResultSet rsChheck = db.get(query);
			try {
				while (rsChheck.next()) {
					this.msg += "Sản phẩm ( " + rsChheck.getString("TEN")+ " ) còn tối đa ( "+ rsChheck.getString("available")+ " ). Vui lòng kiểm tra lại ";
					this.spThieuList.put(rsChheck.getString("MA"),rsChheck.getInt("available"));
				}
				rsChheck.close();
			} catch (Exception e) {
				e.printStackTrace();
			}


			if (msg.trim().length() > 0) {
				return false;
			}



			try 
			{



				db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
				db.update("SET LOCK_TIMEOUT 60000;");

				db.getConnection().setAutoCommit(false);



				query = "\n insert ERP_BUNDLE(NgayChungTu,dvkd_fk,KBH_FK,NPP_FK, KHO_FK, SANPHAM_FK, SOLUONG, DIENGIAI, SOLO,NGAYHETHAN, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
				"\n values('" + this.ngaygiaodich + "', '" + this.dvkdId + "', '" + this.kbhId + "', '" + this.nppId + "', '" + this.khoId + "', '" + this.spId + "', '" + this.soluong + "', N'" + this.ghichu + "', N'" + this.solo + "', N'" + this.ngayhethan + "', 0 , '" + this.userId + "', '" +  getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "')";

				if (db.updateReturnInt(query) != 1) {
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					this.msg = "1.Khong the tao moi dh , " + query;
					return false;
				}
				query =" select SCOPE_IDENTITY() dhId ";
				ResultSet rs = db.get(query);
				rs.next();
				String dhId = rs.getString("dhId");
				this.id = dhId;


				String kq = InsertDonhang_SanPham_ChiTiet( splist,db, this.id,this.nppId , this.ngaygiaodich);
				if(kq.trim().length() > 0)
				{
					this.msg = kq;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				
				kq = InsertDonhang_SanPham_Tong(splist, db,this.id );
				if(kq.trim().length() > 0)
				{
					this.msg = kq;
					db.getConnection().rollback();
					db.getConnection().setAutoCommit(true);
					return false;
				}
				

				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				return true;


			}catch (Exception e) {
				db.update("rollback");
				this.msg = "Lỗi:" + e.getMessage();
				return false;
			}

		}
	}

	public boolean UpdateDh(List<ISanpham> splist, String action) {
		geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();

		if (this.spThieuList.size() > 0) {
			this.msg = "Trong kho khong du so luong mot so san pham ban chon, vui long chon lai so luong...";
			return false;
		}


		String sqlCHECK = "";
		String query = "";
		for (int m = 0; m < splist.size(); m++) 
		{
			ISanpham sp = splist.get(m);
			sqlCHECK += " select pk_seq as sanpham_fk, '"+ sp.getSoluong()+ "' as soluong from SANPHAM where ma = '" + sp.getMasanpham() + "'  ";
			if (m < splist.size() - 1)
				sqlCHECK += " union ";
		}

		try 
		{



			String msg1=this.check_solo_tong_va_chitiet(splist,"(Hàng bán)");
			if(msg1.length() >0){
				this.msg= msg1;
				return false;
			}

			query = "select b.TEN, b.MA, a.available, isnull(dh.soluong, 0) as soluong " +
			"from NHAPP_KHO a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
			"	left join  	( " +sqlCHECK + "	) " +
			"	dh on b.pk_seq = dh.sanpham_fk   " +
			"where a.NPP_FK = '" + this.nppId +"' " +
			"		and a.kho_fk = '" +this.khoId + "' and KBH_FK =  " + this.kbhId +
			"		and a.available < isnull(dh.soluong, 0) ";

			System.out.println("---CHECK TON KHO: " + query);
			ResultSet rsChheck = db.get(query);
			try {
				while (rsChheck.next()) {
					this.msg += "Sản phẩm ( " + rsChheck.getString("TEN")+ " ) còn tối đa ( "+ rsChheck.getString("available")+ " ). Vui lòng kiểm tra lại ";
					this.spThieuList.put(rsChheck.getString("MA"),rsChheck.getInt("available"));
				}
				rsChheck.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (msg.trim().length() > 0) 
			{
				return false;
			}



		} catch (Exception er) {
			this.msg = "Lỗi khi lưu đơn hàng: " + er.getMessage();
			er.printStackTrace();
			return false;
		}
		ResultSet rs = null;
		try 
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");

			db.getConnection().setAutoCommit(false);





			



			query =  " select dhsp.sanpham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,SUM(dhsp.soluong)soluong  " + 
					 "\n from ERP_BUNDLE_SANPHAM dhsp   " + 
					 "\n inner join  ERP_BUNDLE dh on dh.PK_SEQ = dhsp.BUNDLE_fk  " + 
					 "\n where dh.trangthai = 0 and dhsp.BUNDLE_fk =  " + this.id +
					 "\n group by   dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,dhsp.sanpham_fk" ;
			ResultSet rssp=db.get(query);

			while (rssp.next()){
				String _khoid=rssp.getString("KHO_FK");
				String _nppid=rssp.getString("NPP_FK");
				String _kbhid=rssp.getString("KBH_FK");
				String _spid=rssp.getString("sanpham_fk");
				double _soluong=rssp.getDouble("soluong");
				String msg1=util_kho.Update_NPP_Kho_Sp(this.ngaygiaodich, "bundle revert  tổng", db, _khoid, _spid, _nppid ,_kbhid, 0, (-1)*_soluong, _soluong, 0);// giảm booked,avai cộng
				if(msg1.length() >0)
				{
					db.getConnection().rollback(); 
					this.msg =msg1;
					return false; 
				}
			}
			rssp.close();
			

			query="\n SELECT  DH_CT.SANPHAM_FK , DH.KHO_FK ,   DH.NPP_FK ,  "+
			"\n DH.KBH_FK , DH_CT.SOLO ,DH_CT.NGAYHETHAN ,DH_CT.NGAYNHAPKHO ,DH_CT.SOLUONG "+
			"\n FROM  ERP_BUNDLE_SANPHAM DH_CT INNER JOIN ERP_BUNDLE DH ON DH.PK_SEQ=DH_CT.BUNDLE_fk "+ 
			"\n WHERE DH_CT.BUNDLE_fk=" + this.id +"  AND DH.TRANGTHAI=0 ";
			System.out.println("Lay chi tiet don hang: "+query);
			rssp=db.get(query);

			while(rssp.next())
			{
				String _khoid=rssp.getString("KHO_FK");
				String _nppid=rssp.getString("NPP_FK");
				String _kbhid=rssp.getString("KBH_FK");
				String _spid=rssp.getString("SANPHAM_FK");
				String _solo=rssp.getString("SOLO");
				String _NGAYHETHAN=rssp.getString("NGAYHETHAN");
				String _ngaynhapkho=rssp.getString("NGAYNHAPKHO");

				double _soluong=rssp.getDouble("soluong");
				String msg1=util_kho.Update_NPP_Kho_Sp_Chitiet(this.ngaygiaodich, "bundle revert chi tiet",  db, _khoid, _spid, _nppid, _kbhid, _solo, _NGAYHETHAN, _ngaynhapkho, 0, (-1)* _soluong, _soluong, _soluong, 0);

				if(msg1.length() >0)
				{
					db.getConnection().rollback(); 
					this.msg =msg1;
					return false; 
				}
			}
			rssp.close();

			
			query = "\n update  ERP_BUNDLE set NgayChungTu ='" + this.ngaygiaodich + "',dvkd_fk =  '" + this.dvkdId + "' " +
			"\n	,KBH_FK = '" + this.kbhId + "',NPP_FK = '" + this.nppId + "'" +
			"\n	, KHO_FK = '" + this.khoId + "' , SANPHAM_FK = '" + this.spId + "', SOLUONG= '" + this.soluong + "'" +
			"\n , DIENGIAI = N'" + this.ghichu + "', SOLO =  N'" + this.solo + "'" +
			"\n ,NGAYHETHAN =  N'" + this.ngayhethan + "', NGUOISUA ='" + this.userId + "', NGAYSUA= '" +  getDateTime() + "'" +
			"\n where  trangthai = 0 and pk_seq =   " + this.id;


			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "1.Khong the cap nhat table 'ERP_BUNDLE'( đã chốt hoặc xảy ra lỗi) , " + query;
				return false;
			}
			
			query = "	   delete from ERP_BUNDLE_SANPHAM where BUNDLE_fk = " + this.id + " " +
			"		and BUNDLE_fk in (select pk_seq from ERP_BUNDLE where trangthai = 0 and pk_Seq = '" + this.id + "') ";
			if(!db.update(query) )
			{
				db.getConnection().rollback(); 
				db.getConnection().setAutoCommit(true);
				this.msg = "3.Khong the cap nhat table 'ERP_BUNDLE_SANPHAM' , " + query;
				return false; 
			}

			String kq = InsertDonhang_SanPham_ChiTiet( splist,this.db, this.id,this.nppId , this.ngaygiaodich);
			//String kq = dexuatloDonHang(db, this.id, this.nppId, this.ngaygiaodich); 
			if(kq.trim().length() > 0)
			{
				this.msg = kq;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			kq = InsertDonhang_SanPham_Tong(splist, db,this.id );
			if(kq.trim().length() > 0)
			{
				this.msg = kq;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}


			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			if (rs != null)
				rs.close();
		} catch (Exception e) {
			try {
				db.getConnection().rollback();
			} catch (Exception e1) {
			}
			e.printStackTrace();
			return false;
		}

		return true;

	}


	public void createRS() {

		this.createKho();
		String query = "select pk_seq, ten from NHAPHANPHOI where pk_seq != 1 and pk_Seq in  ( select npp_fk from phamvihoatdong where nhanvien_fk='"+ this.userId + "')";
		this.nppRs = this.db.get(query);


		this.dvkdRs = db.get(" select PK_SEQ,DIENGIAI from donvikinhdoanh where trangthai = 1 ");

		this.kbhRs = db.get(" select PK_SEQ,DIENGIAI from KenhBanHang where trangthai = 1 ");

		if(this.khoId.trim().length() > 0 && this.nppId.trim().length() > 0&& this.kbhId.trim().length() > 0 &&  this.dvkdId .trim().length() > 0)
		{
			query = " select PK_SEQ, ma + ', ' + TEN as TEN from SANPHAM where dvkd_fk = " + this.dvkdId +
			" and pk_seq in ( select sanpham_fk from Nhapp_kho where kho_fk = '" + this.khoId + "' and NPP_FK ="+this.nppId+" and KBH_FK ="+this.kbhId+" ) " +
			" order by MA asc, TEN asc";
			this.spRs = db.get(query);


		}
	}




	private void createKho() {
		geso.dms.center.util.Utility utilCenter = new geso.dms.center.util.Utility();
		String query = "select distinct PK_SEQ as khoId, Ten, Diengiai from KHO where trangthai = 1 and pk_seq in "+ utilCenter.quyen_kho(this.userId)+ " order by PK_SEQ ASC ";
		System.out.println(query);
		this.kholist = db.get(query);
	}

	private void CreateSpList() {
		DecimalFormat df2 = new DecimalFormat("#,###,###,##0.00");
		String command = "";

		command ="\n select   b.pk_seq as spId, b.ma as spMa, b.ten as spTen, sum(a.soluong)soluong  " + 
				 "\n 	,isnull(c.donvi, 'Chua xac dinh') as donvi , d.AVAILABLE as hienhuu  " + 
				 "\n from ERP_BUNDLE_SANPHAM a  " + 
				 "\n inner join SANPHAM b on b.PK_SEQ = a.SanPham_fk  " + 
				 "\n left join donvidoluong c on b.dvdl_fk = c.pk_seq  " + 
				 "\n inner join NHAPP_KHO d on b.PK_SEQ = d.SANPHAM_FK and d.KBH_FK = "+this.kbhId+" and d.KHO_FK = "+this.khoId+"  and d.NPP_FK = "+this.nppId+"  " + 
				 "\n where a.BUNDLE_fk  =   " + this.id +
				 "\n group by  b.pk_seq , b.ma , b.ten,isnull(c.donvi, 'Chua xac dinh') , d.AVAILABLE ";

		System.out.println("22.San pham list:" + command);

		ResultSet splistRs = db.get(command);
		List<ISanpham> splist = new ArrayList<ISanpham>();
		if (splistRs != null) {
			String[] param = new String[11];
			ISanpham sp = null;
			try {
				while (splistRs.next()) {
					param[0] = splistRs.getString("spId");
					param[1] = splistRs.getString("spma");
					param[2] = splistRs.getString("spten");
					param[3] = splistRs.getString("soluong");
					param[4] = splistRs.getString("donvi");
					param[5] = "0";

					param[6] = "";
					param[7] = "0";
					param[10] = "0";


					sp = new Sanpham(param);

					sp.setTonhientai(df2.format(splistRs.getDouble("hienhuu")));

					//// lô sp chi tiết

					List<ISpDetail> spDetail = new ArrayList<ISpDetail>();	
					String query =	  	 "\n  SELECT KHO.SOLO,ISNULL( DHCT.SOLUONG,0) AS SOLUONG ,KHO.NGAYHETHAN,KHO.NGAYNHAPKHO,KHO.ngaynhapkho ,KHO.AVAILABLE     " + 
										 "\n  FROM NHAPP_KHO_CHITIET KHO     " + 
										 "\n  inner join ERP_BUNDLE dh on dh.PK_SEQ = "+this.id+" AND KHO.NPP_FK= dh.NPP_FK and KHO.KBH_FK = dh.KBH_FK and KHO.KHO_FK = dh.KHO_FK and KHO.ngaynhapkho <= DH.NgayChungTu  " + 
										 "\n  left JOIN ERP_BUNDLE_SANPHAM DHCT    ON DHCT.BUNDLE_fk  = dh.pk_seq and KHO.SANPHAM_FK=DHCT.SANPHAM_FK     " + 
										 "\n  					AND KHO.SOLO=DHCT.SOLO and KHO.NGAYHETHAN = DHCT.ngayhethan AND KHO.ngaynhapkho = DHCT.ngaynhapkho  " + 
										 "\n  where (   DHCT.BUNDLE_fk is not null   ) and  ISNULL( DHCT.SOLUONG,0) + AVAILABLE > 0 and KHO.SanPham_FK =  "+splistRs.getString("spId") +  
										 "\n  ORDER BY Kho.NGAYHETHAN ";
					System.out.println(" du lieu 44343: "+query);
					ResultSet rsSpDetail = db.get(query);			 
					while(rsSpDetail.next())
					{
						ISpDetail  splo =new SpDetail();

						splo.setSolo(rsSpDetail.getString("solo"));
						splo.setNgayhethan(rsSpDetail.getString("NGAYHETHAN"));
						splo.setNgaynhapkho(rsSpDetail.getString("ngaynhapkho"));
						splo.setSoluong(""+rsSpDetail.getDouble("SOLUONG"));
						splo.setSoluongton(""+ (rsSpDetail.getDouble("SOLUONG")+ rsSpDetail.getDouble("AVAILABLE")));

						spDetail.add(splo);
					}

					rsSpDetail.close();

					sp.setSpDetailList(spDetail);	




					splist.add(sp);

				}
				if (splistRs != null) {
					splistRs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("___EXCEPTION SAN PHAM: " + e.getMessage());
			}
		}
		this.splist = splist;
	}




	public void init() {

		String query = "select nguoisua,ngaysua,nguoitao,ngaytao,trangthai,ngayhethan,dvkd_fk,kbh_fk,npp_fk,KHO_FK, ngaychungtu, diengiai, sanpham_fk, soluong, SOLO from Erp_BUNDLE " +
		"where PK_SEQ = '" + this.id + "'";
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try 
			{
				while(rs.next())
				{
					this.dvkdId = rs.getString("dvkd_fk");
					this.kbhId = rs.getString("kbh_fk");
					this.nppId = rs.getString("npp_fk");
					this.khoId = rs.getString("KHO_FK");
					this.ngaygiaodich = rs.getString("ngaychungtu");
					this.ghichu = rs.getString("diengiai");
					this.spId = rs.getString("sanpham_fk");
					this.soluong = rs.getDouble("soluong");
					this.solo = rs.getString("SOLO");
					this.ngayhethan = rs.getString("ngayhethan");
					this.trangthai = rs.getString("trangthai");
					this.ngaytao = rs.getString("ngaytao");
					this.nguoitao = rs.getString("nguoitao");
					this.ngaysua = rs.getString("ngaysua");
					this.nguoisua = rs.getString("nguoisua");
				}
				rs.close();
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}



		this.CreateSpList();
		this.createRS();
	}


	public String getKhoId() {
		return this.khoId;
	}

	public void setKhoId(String khoId) {
		this.khoId = khoId;
	}

	public ResultSet getKhoList() {
		return this.kholist;
	}

	public void setKhoList(ResultSet kholist) {
		this.kholist = kholist;
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void DBclose() {
		try {
			if (!(this.kholist == null))
				this.kholist = null;

			splist = null;
			spThieuList = null;

			this.db.shutDown();
		} catch (Exception e) {
		}
	}


	DecimalFormat format_1 = new DecimalFormat( "##########.#" );


	private int CompareDATE(String _date1, String _date2) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// Date date1 = sdf.parse("2014-10-01");
			// Date date2 = sdf.parse("2014-10-01");

			Date date1 = sdf.parse(_date1);
			Date date2 = sdf.parse(_date2);

			// System.out.println(sdf.format(date1));
			// System.out.println(sdf.format(date2));

			return date1.compareTo(date2);
		} catch (Exception e) {
			return 0;
		}

	}


	public String getGhiChu() {
		return this.ghichu;
	}

	public void setGhiChu(String ghichu) {
		this.ghichu = ghichu;
	}






	public ResultSet getNppRs() {
		return nppRs;
	}

	public void setNppRs(ResultSet nppRs) {
		this.nppRs = nppRs;
	}




	public String dexuatloDonHang(Idbutils db,String dhId,String nppId, String NgayNghiepVu)
	{


		try
		{
			geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();
			String query =  "\n select c.kho_fk as khoId, c.kbh_fk as kbhId, b.pk_seq as spId, b.ten as spTEN, sum(a.soluong) as soluong " +
			"\n from donhang_sanpham a inner join sanpham b on a.sanpham_fk = b.pk_seq inner join donhang c on a.donhang_fk = c.pk_seq  " +
			"\n	inner join nhaphanphoi npp on c.npp_fk = npp.pk_seq and npp.loainpp != 0 " +
			"\n where c.trangthai != 2 and a.donhang_fk in ( " + dhId + " )  " +
			"\n group by c.kho_fk, c.kbh_fk, b.pk_seq, b.ten ";
			System.out.println("---5.CHEN SP: " + query);
			ResultSet rsKHO = db.get(query);

			while(rsKHO.next())
			{
				String khoId = rsKHO.getString("khoId");
				String kbhId = rsKHO.getString("kbhId");
				String spId = rsKHO.getString("spId");
				double soluong = rsKHO.getDouble("soluong");


				DecimalFormat format_2 = new DecimalFormat( "##########.#" );
				//TU DE XUAT LO --> KHO CHI TIET THI VAN TRU SO LUONG + AVAI
				query = " select ngaynhapkho,AVAILABLE, SOLO, NGAYHETHAN  " +
				" from NHAPP_KHO_CHITIET " +
				" where AVAILABLE > 0  and NPP_FK = '" + nppId + "' and KBH_FK = '" + kbhId + "' and KHO_FK = '" + khoId + "' and SANPHAM_FK = '" + spId + "' " +
				" and ngaynhapkho <= '"+NgayNghiepVu+"' " +
				" order by  NGAYHETHAN asc, ngaynhapkho asc ,AVAILABLE asc";

				System.out.println("--TU DE XUAT: " + query);
				ResultSet rs = db.get(query);
				String NgayHetHan="";
				double tongluongxuatCT = 0;  //PHAI BAT BUOC TONG LUONG XUAT O KHO CHI TIET PHAI BANG TONG LUONG XUAT O KHO TONG
				/*if(rs != null)*/
				{
					double totalLUONG = 0;
					boolean exit = false;
					while(rs.next() && !exit)
					{
						NgayHetHan= rs.getString("NGAYHETHAN");
						String ngaynhapkho=rs.getString("ngaynhapkho");

						totalLUONG += rs.getDouble("AVAILABLE");

						double soluongXUAT = 0;

						if( totalLUONG <= soluong &&   totalLUONG >0)
						{
							soluongXUAT = rs.getDouble("AVAILABLE") ;
						}
						else
						{
							soluongXUAT = soluong - ( totalLUONG - rs.getDouble("AVAILABLE"));
							exit = true;
						}
						//System.out.println("soluong xuat sp"+spId +" la "+soluongXUAT + "totalLUONG " +totalLUONG +" so lluong xuat "+(soluong - ( totalLUONG - rs.getDouble("AVAILABLE"))));
						soluongXUAT=Double.parseDouble(format_2.format(soluongXUAT));
						if( soluongXUAT > 0)
						{
							System.out.println("vao day de xuat nay "+ (int)soluongXUAT);

							String _solo= rs.getString("SOLO");

							String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(NgayNghiepVu, "Đơn hàng", db, khoId, spId, nppId, kbhId, _solo, NgayHetHan, ngaynhapkho, 0, soluongXUAT, (-1)*soluongXUAT, (-1)*soluongXUAT, 0);

							if(msg1.length() >0){
								return msg1;

							}

							query = "insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk)"+
							" values('"+this.id+"', '" + spId + "', '" + kbhId + "','" + khoId + "', '" +_solo + "','"+NgayHetHan+"','"+ngaynhapkho+"','" + soluongXUAT + "','" + nppId + "')";												
							int kq = db.updateReturnInt(query);
							//	System.out.println("---UPDATE KHO CHI TIET TOI DAY: " + query + " -- KQ: " + kq);
							if(kq != 1 )
							{
								return "loi de xuat lo save DONHANG_SANPHAM_chitiet dh 1.2";

							}



							System.out.println("tong xuat chi tiet"+tongluongxuatCT);
							tongluongxuatCT += soluongXUAT;
							System.out.println("tong xuat chi tiet"+tongluongxuatCT);
							if(exit)  //DA XUAT DU
							{
								//rs.close();
								break;
							}
						}
					}
					rs.close();
				}




				if(Double.parseDouble(format_2.format(tongluongxuatCT)) < Double.parseDouble(format_2.format(soluong))){

					return "1.Lỗi: Số tồn hiện tại tới lô tới ngày (ngày cấu hình hóa đơn) :"+NgayNghiepVu+" của sản phẩm : "+rsKHO.getString("spTEN")+" không còn đủ để trừ kho, vui lòng   " +
					"kiểm lại báo cáo tồn kho (xuất nhập tồn, tồn hiện tại) để biết thêm chi tiết " ;

				}
				System.out.println("so luong check la : "+spId+"-----------"+tongluongxuatCT +"____________"+soluong);
				if(Double.parseDouble(format_2.format(tongluongxuatCT)) < Double.parseDouble(format_2.format(soluong)))
				{

					return "1136.Số lượng đề xuất trong lô chi tiết theo ngày không còn đủ, vui lòng kiểm tra xuất nhập tồn theo lô để biết thêm chi tiết " ;

				}


			}

			return "";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "Lỗi:" + e.getMessage();
		}
	}

	public String InsertDonhang_SanPham_ChiTiet(List<ISanpham> splist,Idbutils db,String dhId,String nppId, String NgayNghiepVu)
	{

		String query ="";
		try
		{
			geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();


			for(int m = 0; m < splist.size(); m++)
			{
				ISanpham sp = splist.get(m);
				List<ISpDetail> spDetail  =sp.getSpDetailList();
				for(int i=0;i<spDetail.size();i++){
					ISpDetail  splo=spDetail.get(i);
					double soluong=0;

					if(splo.getSoluong().trim().length() > 0 && Double.parseDouble(splo.getSoluong()) > 0)
					{
						query  = 	"\n select kho.NPP_FK,kho.KBH_FK,kho.kho_fk ,kho.SanPham_FK,kho.SoLo,kho.Ngayhethan,kho.ngaynhapkho " +
						"\n from nhapp_kho_chitiet kho " +
						"\n inner join sanpham sp on kho.sanpham_fk = sp.pk_seq  " +
						"\n inner join ERP_BUNDLE dh on dh.pk_seq =   " + dhId +
						"\n where kho.NPP_FK ="+nppId+" and kho.KBH_FK = dh.KBH_FK  and kho.kho_fk =dh.KHO_FK " +
						"\n		and sp.ma ='"+sp.getMasanpham()+"' and kho.Solo ='"+splo.getSolo()+"' and kho.Ngayhethan ='"+splo.getNgayhethan()+"'  and kho.Ngaynhapkho ='"+splo.getNgaynhapkho()+"'   " +
						"\n		and kho.ngaynhapkho <= '"+NgayNghiepVu+"' " ;
						System.out.println("querry InsertDonhang_SanPham_ChiTiet "+ query);
						ResultSet rs= db.get(query);
						if(rs.next())
						{

							String _nppId = rs.getString("NPP_FK");
							String _kbhId = rs.getString("KBH_FK");
							String _khoId = rs.getString("kho_fk");
							String _spId = rs.getString("SanPham_FK");
							String _soLo = rs.getString("SoLo");
							String _Ngayhethan = rs.getString("Ngayhethan");
							String _ngaynhapkho = rs.getString("ngaynhapkho");
							try{
								soluong=Double.parseDouble(splo.getSoluong());
							}catch(Exception er){}
							/*	query = "insert into  DONHANG_SANPHAM_chitiet (donhang_fk,sanpham_fk,kbh_fk,kho_fk,solo,ngayhethan,ngaynhapkho,soluong,npp_fk)"+
							" select " + dhId + ", "+_spId+" , '" + _kbhId + "','" + _khoId + "', '" +_soLo + "','"+_Ngayhethan+"','"+_ngaynhapkho+"'," + soluong + "," + _nppId ;												

							 */			
							query = "insert ERP_BUNDLE_SANPHAM(Bundle_fk, SanPham_fk, soluong,  SOLO,NgayHetHan,ngaynhapkho) " +
							"select  " + dhId + ", '" + _spId + "', '" + soluong + "', '" + _soLo + "', N'" + _Ngayhethan + "','"+_ngaynhapkho+"' ";
							if(db.updateReturnInt(query)!=1){
								return  "Lỗi: Không thể thực hiện cập nhật chi tiết lô của sản phẩm:"+sp.getMasanpham() +".Vui lòng thử lại hoặc báo Admin để được trợ giúp. Command Error: "+query;	

							}


							String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(NgayNghiepVu, "BoBundle.java 924:"+dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo , _Ngayhethan, _ngaynhapkho, 0, soluong, (-1)*soluong, (-1)*soluong, 0);

							if(msg1.length() >0){
								return msg1;

							}
						}
						else
						{
							return "Không tồn tại kho theo (SP:"+sp.getMasanpham()+",Lô:"+splo.getSolo()+","+splo.getNgayhethan()+" ) trước ngày ("+NgayNghiepVu+") ";
						}
					}


				}

			}

			return "";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "Lỗi:" + e.getMessage();
		}
	}
	
	public String InsertDonhang_SanPham_Tong(List<ISanpham> splist,Idbutils db,String dhId)
	{

		String query ="";
		try
		{
			geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();

			query =  "\n select dhsp.SanPham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,SUM(dhsp.soluong)soluong  " + 
			 "\n from ERP_BUNDLE_SANPHAM dhsp   " + 
			 "\n inner join  ERP_BUNDLE dh on dh.PK_SEQ = dhsp.BUNDLE_fk  " +
			 "\n inner join sanpham sp on sp.pk_seq = dhsp.SanPham_fk  " + 
			 "\n where  dh.PK_SEQ =   " + dhId + 
			 "\n group by   dhsp.SanPham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK ";
				ResultSet rssp=db.get(query);
				System.out.println("bo bundle kho tong = "+ query);
				while(rssp.next())
				{
					String _khoid=rssp.getString("KHO_FK");
					String _nppid=rssp.getString("NPP_FK");
					String _kbhid=rssp.getString("KBH_FK");
					String _spid=rssp.getString("sanpham_fk");
					double _soluong=rssp.getDouble("soluong");
					
					String msg1=util_kho.Update_NPP_Kho_Sp(this.ngaygiaodich, "InsertDonhang_SanPham_Tong", db, _khoid, _spid, _nppid ,_kbhid, 0, _soluong,(-1)* _soluong, 0);// tang booked,giam avai  
					if(msg1.length() >0)
					{
						return msg1;
					}
				}
				rssp.close();

			return "";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "Lỗi:" + e.getMessage();
		}
	}




	private String check_solo_tong_va_chitiet(List<ISanpham> splist,String tag)
	{
		System.out.println("vak dkday : "+splist.size());
		if(splist != null)
			for(int m = 0; m < splist.size(); m++)
			{
				ISanpham sp = splist.get(m);
				List<ISpDetail> spDetail  =sp.getSpDetailList();
				double totalsoluong=0;
				for(int i=0;i<spDetail.size();i++){
					ISpDetail  splo=spDetail.get(i);
					double soluong=0;

					try{
						soluong=Double.parseDouble(splo.getSoluong());
					}catch(Exception er){}
					totalsoluong+= soluong;


				}


				if(totalsoluong !=Double.parseDouble(sp.getSoluong().replace(",",""))){
					return  tag +"Vui lòng kiểm tra số lượng trong lô chi tiết (phải bằng số lượng tổng xuất) của sản phẩm : "+sp.getMasanpham();
				}
			}

		return "";
	}

	public String getKbhId() {
		return kbhId;
	}
	public void setKbhId(String kbhId) {
		this.kbhId = kbhId;
	}


	public ResultSet getKbhRs() {
		return kbhRs;
	}
	public void setKbhRs(ResultSet kbhRs) {
		this.kbhRs = kbhRs;
	}
	public ResultSet getDvkdRs() {
		return dvkdRs;
	}
	public dbutils getDb() {
		return db;
	}
	public String getDvkdId() {
		return dvkdId;
	}
	public void setDvkdId(String dvkdId) {
		this.dvkdId = dvkdId;
	}

	public String getSpId() {
		return spId;
	}
	public void setSpId(String spId) {
		this.spId = spId;
	}
	public ResultSet getSpRs() {
		return spRs;
	}

	public String getSolo() {
		return solo;
	}
	public void setSolo(String solo) {
		this.solo = solo;
	}
	public double getSoluong() {
		return soluong;
	}
	public void setSoluong(double soluong) {
		this.soluong = soluong;
	}
	public String getNgayhethan() {
		return ngayhethan;
	}
	public void setNgayhethan(String ngayhethan) {
		this.ngayhethan = ngayhethan;
	}

}
