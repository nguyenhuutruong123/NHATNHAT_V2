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

public class RaBundle implements IRaBundle, Serializable {
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
	String nppId= "";
	String khoId = "";
	String kbhId = "";
	String dvkdId = "";






	List<ISanpham> splist; /// nguyên liệu được rã ra
	List<ISanpham> SoLoList; // bundle bó


	Hashtable<String, Integer> spThieuList;


	dbutils db;


	Utility util;



	public RaBundle(String id) {
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
		this.SoLoList = new ArrayList<ISanpham>();
	}

	public RaBundle(String id, String nppId) {
		this.id = id;
		this.nppId = nppId;
		this.util = new Utility();
		this.CreateSpList();
		this.SoLoList = new ArrayList<ISanpham>();
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



	public boolean CreateDh() {
		geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();
		String query="";

		String sanpham = "";
		String sqlCHECK = "";
		
		if(this.SoLoList.size() <=0)
		{
			this.msg =" Chưa chọn sản phẩm cần rã ! ";
			return false;
		}
		if(this.splist.size() <=0)
		{
			this.msg =" Chưa chọn nguyên liệu rã ra ! ";
			return false;
		}

		for (int m = 0; m < SoLoList.size(); m++) 
		{
			ISanpham sp = SoLoList.get(m);
			sqlCHECK += " select " + sp.getId() + " as  sanpham_fk, '"+sp.getSOLo()+"' Solo ,'"+sp.getNgayHetHan()+"' ngayhethan,'"+sp.getNgaynhapkho()+"' ngaynhapkho, "+ sp.getSoluong().replace(",","")+ " as soluong    ";
			if (m < SoLoList.size() - 1)
				sqlCHECK += " union ";
		}



		try 
		{

			

			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");

			db.getConnection().setAutoCommit(false);

			query = "\n select b.TEN, b.MA, a.available,a.Solo,a.ngayhethan,a.ngaynhapkho, isnull(dh.soluong, 0) as soluong " +
					"\n from NHAPP_KHO_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
					"\n 	left join  	( " +sqlCHECK + "	)dh on b.pk_seq = dh.sanpham_fk and dh.Solo = a.Solo and dh.ngayhethan = a.ngayhethan and dh.ngaynhapkho = a.ngaynhapkho   " +
					"\n where a.NPP_FK = '" + this.nppId +"' " +
					"\n 		and a.kho_fk = '" +this.khoId + "' and KBH_FK =  " + this.kbhId +
					"\n 		and a.available < isnull(dh.soluong, 0) ";

			System.out.println("---CHECK TON KHO: " + query);
			ResultSet rsChheck = db.get(query);
			while (rsChheck.next()) {
				this.msg += "\n SP("+rsChheck.getString("TEN")+")  Số lô ( " + rsChheck.getString("Solo")+ " ),ngay hết hạn("+rsChheck.getString("ngayhethan")+"),ngày nhập kho ("+rsChheck.getString("ngaynhapkho")+") " +
							" còn tối đa ( "+ rsChheck.getString("available")+ " ) không thể rả. Vui lòng kiểm tra lại ";
			}
			rsChheck.close();
		
			if (this.msg.trim().length() > 0) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			query = "\n insert ERP_RABUNDLE(NgayChungTu,dvkd_fk,KBH_FK,NPP_FK, KHO_FK, SANPHAM_FK, DIENGIAI, TRANGTHAI, NGUOITAO, NGAYTAO, NGUOISUA, NGAYSUA) " +
					"\n values('" + this.ngaygiaodich + "', '" + this.dvkdId + "', '" + this.kbhId + "', '" + this.nppId + "', '" + this.khoId + "', '" + this.spId + "', N'" + this.ghichu + "', 0 , '" + this.userId + "', '" +  getDateTime() + "', '" + this.userId + "', '" + getDateTime() + "')";

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
			


			String kq = InsertDonhang_SanPham_ChiTiet( SoLoList,db, dhId,this.nppId , this.ngaygiaodich);
			if(kq.trim().length() > 0)
			{
				this.msg = kq;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			
			kq = InsertDonhang_SanPham_Tong(db,dhId );
			if(kq.trim().length() > 0)
			{
				this.msg = kq;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			 kq = InsertDonhang_SanPham_NguyenLieu( splist,db, dhId,this.nppId , this.ngaygiaodich);
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

	public boolean UpdateDh() {
		geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();

		String sqlCHECK = "";
		String query = "";
		if(this.SoLoList.size() <=0)
		{
			this.msg =" Chưa chọn sản phẩm cần rã ! ";
			return false;
		}
		if(this.splist.size() <=0)
		{
			this.msg =" Chưa chọn nguyên liệu rã ra ! ";
			return false;
		}

		for (int m = 0; m < SoLoList.size(); m++) 
		{
			ISanpham sp = SoLoList.get(m);
			sqlCHECK += " select " + sp.getId() + " as  sanpham_fk, '"+sp.getSOLo()+"' Solo ,'"+sp.getNgayHetHan()+"' ngayhethan,'"+sp.getNgaynhapkho()+"' ngaynhapkho, "+ sp.getSoluong().replace(",","")+ " as soluong    ";
			if (m < SoLoList.size() - 1)
				sqlCHECK += " union ";
		}
		ResultSet rs = null;
		try 
		{
			db.update("SET TRANSACTION ISOLATION LEVEL SNAPSHOT;");
			db.update("SET LOCK_TIMEOUT 60000;");

			db.getConnection().setAutoCommit(false);



			query = "\n select b.TEN, b.MA, a.available,a.Solo,a.ngayhethan,a.ngaynhapkho, isnull(dh.soluong, 0) as soluong " +
					"\n from NHAPP_KHO_CHITIET a inner join SANPHAM b on a.sanpham_fk = b.pk_seq " +
					"\n 	left join  	( " +sqlCHECK + "	)dh on b.pk_seq = dh.sanpham_fk and dh.Solo = a.Solo and dh.ngayhethan = a.ngayhethan and dh.ngaynhapkho = a.ngaynhapkho   " +
					"\n where a.NPP_FK = '" + this.nppId +"' " +
					"\n 		and a.kho_fk = '" +this.khoId + "' and KBH_FK =  " + this.kbhId +
					"\n 		and a.available < isnull(dh.soluong, 0) ";

			System.out.println("---CHECK TON KHO: " + query);
			ResultSet rsChheck = db.get(query);
			while (rsChheck.next()) {
				this.msg += "\n SP("+rsChheck.getString("TEN")+")  Số lô ( " + rsChheck.getString("Solo")+ " ),ngay hết hạn("+rsChheck.getString("ngayhethan")+"),ngày nhập kho ("+rsChheck.getString("ngaynhapkho")+") " +
							" còn tối đa ( "+ rsChheck.getString("available")+ " ) không thể rả. Vui lòng kiểm tra lại ";
			}
			rsChheck.close();
		
			if (this.msg.trim().length() > 0) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}



			



			query =  " select dh.sanpham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,SUM(dhsp.soluong)soluong  " + 
					 "\n from ERP_RABUNDLE_ChiTiet dhsp   " + 
					 "\n inner join  ERP_RaBUNDLE dh on dh.PK_SEQ = dhsp.RABUNDLE_fk and dh.sanpham_fk = dhsp.sanpham_fk  " + 
					 "\n where dh.trangthai = 0 and dhsp.RABUNDLE_fk =  " + this.id +
					 "\n group by   dh.sanpham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK" ;
			System.out.println(" revertt query = "+ query);
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
			"\n FROM  ERP_RABUNDLE_ChiTiet DH_CT INNER JOIN ERP_RaBUNDLE DH ON DH.PK_SEQ=DH_CT.RABUNDLE_fk "+ 
			"\n WHERE DH_CT.RABUNDLE_fk=" + this.id +"  AND DH.TRANGTHAI=0 ";
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

			query = "\n update  ERP_RaBUNDLE set NgayChungTu = '" + this.ngaygiaodich + "',dvkd_fk =  '" + this.dvkdId + "' " +
			"\n	,KBH_FK = '" + this.kbhId + "',NPP_FK = '" + this.nppId + "'" +
			"\n	, KHO_FK = '" + this.khoId + "' , SANPHAM_FK = '" + this.spId + "'" +
			"\n , DIENGIAI = N'" + this.ghichu + "'" +
			"\n , NGUOISUA ='" + this.userId + "', NGAYSUA= '" +  getDateTime() + "'" +
			"\n where  trangthai = 0 and pk_seq =   " + this.id;


			if (db.updateReturnInt(query) != 1) {
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				this.msg = "1.Khong the cap nhat table 'ERP_RABUNDLE'( đã chốt hoặc xảy ra lỗi) , " + query;
				return false;
			}
			
			query = "	   delete from ERP_RABUNDLE_SANPHAM where RABUNDLE_fk = " + this.id + " " +
					"				and RABUNDLE_fk in (select pk_seq from ERP_RABUNDLE where trangthai = 0 and pk_Seq = '" + this.id + "') ";
			if(!db.update(query) )
			{
				db.getConnection().rollback(); 
				db.getConnection().setAutoCommit(true);
				this.msg = "3.Khong the cap nhat table 'ERP_BUNDLE_SANPHAM' , " + query;
				return false; 
			}
			query = "	   delete from ERP_RABUNDLE_ChiTiet where RABUNDLE_fk = " + this.id + " " +
					"				and RABUNDLE_fk in (select pk_seq from ERP_RABUNDLE where trangthai = 0 and pk_Seq = '" + this.id + "') ";
			if(!db.update(query) )
			{
				db.getConnection().rollback(); 
				db.getConnection().setAutoCommit(true);
				this.msg = "3.Khong the cap nhat table 'ERP_BUNDLE_SANPHAM' , " + query;
				return false; 
			}

			String kq = InsertDonhang_SanPham_ChiTiet( SoLoList,this.db, this.id,this.nppId , this.ngaygiaodich);
			//String kq = dexuatloDonHang(db, this.id, this.nppId, this.ngaygiaodich); 
			if(kq.trim().length() > 0)
			{
				this.msg = kq;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}

			kq = InsertDonhang_SanPham_Tong(db,this.id );
			if(kq.trim().length() > 0)
			{
				this.msg = kq;
				db.getConnection().rollback();
				db.getConnection().setAutoCommit(true);
				return false;
			}
			 kq = InsertDonhang_SanPham_NguyenLieu( splist,db, this.id,this.nppId , this.ngaygiaodich);
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

			try 
			{
				if(this.spId.trim().length() > 0 && this.ngaygiaodich.trim().length() > 0)
				{
					query = "\n select khoct.SOLO,khoct.NGAYHETHAN,khoct.NGAYNHAPKHO,khoct.AVAILABLE, isnull(ract.soluong,0) soluong" +
							"\n from NHAPP_KHO_CHITIET khoct  " +
							"\n left join  ERP_RaBUNDLE_ChiTiet ract on khoct.KHO_FK = ract.KHO_FK and khoct.KBH_FK = ract.KBH_FK " +
							"\n											and khoct.NPP_FK = ract.NPP_FK and khoct.SANPHAM_FK = ract.SANPHAM_FK    " +
							"\n											and khoct.SOLO = ract.SOLO and khoct.NGAYHETHAN = ract.NGAYHETHAN  " +
							"\n											and khoct.NGAYNHAPKHO = ract.NGAYNHAPKHO and ract.RaBUNDLE_fk = " + (this.id != null && this.id.trim().length() > 0 ? this.id : "0" )+
							"\n where khoct.KHO_FK = " + this.khoId + " and khoct.KBH_FK = "+this.kbhId+" and khoct.NPP_FK = "+this.nppId+" and khoct.SANPHAM_FK = "+this.spId+" " +
							"\n		  and (khoct.AVAILABLE > 0 or isnull(ract.soluong,0) > 0   )" +
							"\n  and khoct.ngaynhapkho <= '"+this.ngaygiaodich+"'	" +
							"\n order by isnull(ract.soluong,0) desc, NGAYHETHAN asc,NGAYNHAPKHO     ";
					ResultSet rs= db.get(query);
					if(this.SoLoList == null)
						this.SoLoList  = new ArrayList<ISanpham>();
					
					System.out.println("rabundle List = "+ query);
						while (rs.next())
						{
							if(!CheckTonTai(this.SoLoList, rs))
							{
								ISanpham sp = new Sanpham();
								sp.setId(this.spId);
								sp.setKbhId(this.kbhId);
								sp.setKhoId(this.khoId);
								sp.setNppId(this.nppId);
								sp.setSolo(rs.getString("SOLO"));
								sp.setNgayHetHan(rs.getString("NGAYHETHAN"));
								sp.setNgaynhapkho(rs.getString("NGAYNHAPKHO"));
								sp.setTonhientai(rs.getString("AVAILABLE"));
								sp.setSoluong(rs.getString("soluong"));
								SoLoList.add(sp);
							}
						}
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

	public boolean CheckTonTai (List<ISanpham> spList,ResultSet rs ) throws SQLException
	{
		if(spList != null)
		{
			for(int i = 0; i < spList.size(); i ++)
			{
				ISanpham sp = spList.get(i);
				if(	sp.getId().equals(this.spId) &&
						sp.getNppId().equals(this.nppId) &&
						sp.getKbhId().equals(this.kbhId) &&
						sp.getKhoId().equals(this.khoId) &&
					sp.getSOLo().toUpperCase().trim().equals(rs.getString("SOLO").toUpperCase().trim()) 
					&& sp.getNgayHetHan().trim().equals(rs.getString("NGAYHETHAN").trim())
					&& sp.getNgaynhapkho().trim().equals(rs.getString("NGAYNHAPKHO").trim()))
					
				{return true;
					
				}
			}
		}
		return false;
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

		command ="\n select  a.solo,a.ngayhethan,a.ngaynhapkho ,b.pk_seq as spId, b.ma as spMa, b.ten as spTen, a.soluong  " + 
				 "\n 	,isnull(c.donvi, 'Chua xac dinh') as donvi , isnull(d.AVAILABLE,0) as hienhuu  " + 
				 "\n from ERP_RABUNDLE_SANPHAM a  " + 
				 "\n inner join SANPHAM b on b.PK_SEQ = a.SanPham_fk  " + 
				 "\n left join donvidoluong c on b.dvdl_fk = c.pk_seq  " + 
				 "\n left join NHAPP_KHO_ChiTiet d on  d.solo = a.solo and d.ngayhethan = a.ngayhethan and d.ngaynhapkho = a.ngaynhapkho and  b.PK_SEQ = d.SANPHAM_FK and d.KBH_FK = "+this.kbhId+" and d.KHO_FK = "+this.khoId+"  and d.NPP_FK = "+this.nppId+"  " + 
				 "\n where a.RABUNDLE_fk  =   " + this.id ;

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
					
					sp.setSolo(splistRs.getString("solo"));
					sp.setNgayHetHan(splistRs.getString("ngayhethan"));
					sp.setNgaynhapkho(splistRs.getString("ngaynhapkho"));
					
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

		String query = "select nguoisua,ngaysua,nguoitao,ngaytao,trangthai,dvkd_fk,kbh_fk,npp_fk,KHO_FK, ngaychungtu, diengiai, sanpham_fk from Erp_RABUNDLE " +
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




	public String InsertDonhang_SanPham_ChiTiet(List<ISanpham> SoLoList,Idbutils db,String dhId,String nppId, String NgayNghiepVu)
	{

		String query ="";
		try
		{
			geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();


			for(int m = 0; m < SoLoList.size(); m++)
			{
				ISanpham sp = SoLoList.get(m);

				double soluong=0;

				if(sp.getSoluong().trim().length() > 0 && Utility.parseDouble(sp.getSoluong().replace(",","")) > 0)
				{
					query  = 	"\n select sp.Ma maSp,kho.NPP_FK,kho.KBH_FK,kho.kho_fk ,kho.SanPham_FK,kho.SoLo,kho.Ngayhethan,kho.ngaynhapkho " +
								"\n from nhapp_kho_chitiet kho " +
								"\n inner join Sanpham sp on sp.pk_seq = kho.sanpham_fk    " +
								"\n where kho.NPP_FK ="+nppId+" and kho.KBH_FK = "+this.kbhId+"  and kho.kho_fk =" + this.khoId +
								"\n		and kho.sanpham_fk ='"+this.spId+"' and kho.Solo ='"+sp.getSOLo()+"' and kho.Ngayhethan ='"+sp.getNgayHetHan()+"'  and kho.Ngaynhapkho ='"+sp.getNgaynhapkho()+"'   " +
								"\n		and kho.ngaynhapkho <= '"+NgayNghiepVu+"'  " ;
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
						String maSp = rs.getString("maSp");
						try{
							soluong=Double.parseDouble(sp.getSoluong());
						}catch(Exception er){}
		
						query = "\n insert ERP_RABUNDLE_CHITIET(RABUNDLE_FK,NPP_FK,KBH_FK,KHO_FK,SanPHAM_FK,SoLo,NgayHetHan,NgayNhapKho,SoLuong) " +
								"\n select  " + dhId + ", '" + _nppId + "', '" + _kbhId + "', '" + _khoId + "', '" + _spId + "', '" + _soLo + "', N'" + _Ngayhethan + "','"+_ngaynhapkho+"',"+soluong;
						if(db.updateReturnInt(query)!=1){
							return  "Lỗi: Không thể thực hiện cập nhật chi tiết lô của sản phẩm:"+ maSp+".Vui lòng thử lại hoặc báo Admin để được trợ giúp. Command Error: "+query;	

						}


						String msg1= util_kho.Update_NPP_Kho_Sp_Chitiet(NgayNghiepVu, "RaBundle.java 924:"+dhId, db, _khoId, _spId, _nppId, _kbhId, _soLo , _Ngayhethan, _ngaynhapkho, 0, soluong, (-1)*soluong, (-1)*soluong, 0);

						if(msg1.length() >0){
							return msg1;

						}
					}
					else
					{
						return "Không tồn tại kho theo (SP:"+sp.getId()+",Lô:"+sp.getSOLo()+","+sp.getNgayHetHan()+" ) trước ngày ("+NgayNghiepVu+") ";
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
	
	
	public String InsertDonhang_SanPham_NguyenLieu(List<ISanpham> spList,Idbutils db,String dhId,String nppId, String NgayNghiepVu)
	{

		String query ="";
		try
		{
			geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();


			for(int m = 0; m < spList.size(); m++)
			{
				ISanpham sp = spList.get(m);

				double soluong=0;

				if(sp.getSoluong().trim().length() > 0 && Utility.parseDouble(sp.getSoluong().replace(",","")) > 0)
				{
					query  = 	"\n select sp.pk_seq SanPham_FK " +
								"\n from Sanpham sp    " +
								"\n where sp.ma = '"+sp.getMasanpham()+"' ";
					ResultSet rs= db.get(query);
					if(rs.next())
					{

						String _nppId = this.nppId;
						String _kbhId = this.kbhId;
						String _khoId = this.khoId;
						String _spId = rs.getString("SanPham_FK");
						String _soLo = sp.getSOLo();
						String _Ngayhethan = sp.getNgayHetHan();
						String _ngaynhapkho = NgayNghiepVu;
						
						try{
							soluong=Double.parseDouble(sp.getSoluong());
						}catch(Exception er){}
		
						query = "\n insert ERP_RABUNDLE_SanPham(RABUNDLE_FK,NPP_FK,KBH_FK,KHO_FK,SanPHAM_FK,SoLo,NgayHetHan,NgayNhapKho,SoLuong) " +
								"\n select  " + dhId + ", '" + _nppId + "', '" + _kbhId + "', '" + _khoId + "', '" + _spId + "', '" + _soLo + "', N'" + _Ngayhethan + "','"+_ngaynhapkho+"',"+soluong;
						if(db.updateReturnInt(query)!=1){
							return  "Lỗi: Không thể thực hiện cập nhật chi tiết lô của sản phẩm:"+ sp.getMasanpham()+".Vui lòng thử lại hoặc báo Admin để được trợ giúp. Command Error: "+query;	

						}
					}
					else
					{
						return "Không tồn tại san pham mã (SP:"+sp.getMasanpham()+")  ";
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
	
	public String InsertDonhang_SanPham_Tong(Idbutils db,String dhId)
	{

		String query ="";
		try
		{
			geso.dms.center.util.Utility  util_kho=new geso.dms.center.util.Utility();
			boolean daInsert = false;
			query =  "\n select dh.SanPham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK,SUM(dhsp.soluong)soluong  " + 
			 "\n from ERP_RABUNDLE_CHITIET dhsp   " + 
			 "\n inner join  ERP_RABUNDLE dh on dh.PK_SEQ = dhsp.RABUNDLE_fk  " +
			 "\n inner join sanpham sp on sp.pk_seq = dhsp.SanPham_fk  " + 
			 "\n where  dh.PK_SEQ =   " + dhId + 
			 "\n group by   dh.SanPham_fk,dh.KHO_FK,dh.KBH_FK,dh.NPP_FK ";
			System.out.println("tru kho tong:"+query);
				ResultSet rssp=db.get(query);
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
					daInsert = true;
				}
				rssp.close();
				if(!daInsert)
				{
					return "Chua tru kho tong";
				}
			return "";
		}catch(Exception e)
		{
			e.printStackTrace();
			return "Lỗi:" + e.getMessage();
		}
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

	public List<ISanpham> getSoLoList() {
		return SoLoList;
	}
	public void setSoLoList(List<ISanpham> soLoList) {
		SoLoList = soLoList;
	}

}
