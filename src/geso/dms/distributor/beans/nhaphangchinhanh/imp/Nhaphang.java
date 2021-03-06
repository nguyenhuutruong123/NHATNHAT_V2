package geso.dms.distributor.beans.nhaphangchinhanh.imp;

import geso.dms.distributor.db.sql.dbutils;
 
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.nhaphangchinhanh.INhaphang;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Nhaphang implements INhaphang
{
 
	String userId;
	String id;
	
	String nppId;
	
	String ngayyeucau;
	String ngaynhan;
	String sochungtu;
	String ghichu;

	String msg;
	String trangthai;

	String ddhId;
	ResultSet ddhRs;
	

	String khonhanId;
	ResultSet khonhanRs;

 
	
	List<SpNhaphang> list=new ArrayList<SpNhaphang>();
	
	 
	 
	dbutils db;
	
	public Nhaphang()
	{
		this.id = "";
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";
		this.ngaynhan = "";
		this.sochungtu = "";
		this.khonhanId = "";
		this.loaiDh="";
		this.db = new dbutils();
	}
	
	public Nhaphang(String id)
	{
		this.id = id;
		this.ngayyeucau = getDateTime();
		this.ghichu = "";
		this.msg = "";
		this.trangthai = "0";
		this.ddhId = "";
		this.ngaynhan = "";
		this.sochungtu = "";
		this.khonhanId = "";
		this.loaiDh="";
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

	public String getId() 
	{
		return this.id;
	}

	public void setId(String Id) 
	{
		this.id = Id;
	}

	public String getNgayyeucau() 
	{
		return this.ngayyeucau;
	}

	public void setNgayyeucau(String ngayyeucau) 
	{
		this.ngayyeucau = ngayyeucau;
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	
	public void createRs() 
	{
		String	query = "select PK_SEQ, CAST(pk_seq as varchar(10)) + ' / ' + NgayDonHang as ten " +
						"from ERP_DONDATHANG where  "
						+ " PK_SEQ in ( select dondathang_fk from NHAPHANG where pk_Seq = '" + this.id + "' )  ";
		this.ddhRs = db.get(query);
		Utility util = new Utility();
		  
		this.khonhanRs = db.get("select pk_seq, ten from KHO where pk_seq in " + util.quyen_kho(this.userId)+" order by pk_seq asc");
		
		
		this.getNppInfo();
	}

	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
	}
	
	public void init() 
	{
		String query = 
				"\n select isnull(dondathang_fk,0) as dondathang_fk  ,SoChungTu,ngaychungtu as ngayyeucau, isnull(ghichu,'') as ghichu, npp_fk, isnull(ngaynhan, '') as ngaynhan, trangthai, isnull(ycxk_fk, chuyenkho_fk) as ycxk_fk, isnull(kho_fk, 100000) as kho_fk " +
				"\n	,	(	select top(1) isnull(a.loaidonhang,b.LoaiDonHang) as loaidh  from ERP_DONDATHANG a inner join ERP_CHUYENKHO b on b.ddh_fk=a.PK_SEQ  "+
				"\n		where b.PK_SEQ=NHAPHANG.CHUYENKHO_FK and b.trangthai=1 "+
				"\n )as LoaiDH  "+
				"\n from NHAPHANG where pk_seq = '" + this.id + "'";
		System.out.println("____INIT NHAP KHO: " + query);
		ResultSet rs = db.get(query);
		 
			try 
			{
				if(rs.next())
				{
					this.ngayyeucau = rs.getString("ngayyeucau");
					this.ghichu = rs.getString("ghichu");
					this.ngaynhan = rs.getString("ngaynhan");
					String loaidh=rs.getString("loaidh")==null?"0":rs.getString("loaidh");
					if(loaidh.equals("4"))
						this.khonhanId="100001";
					else
					this.khonhanId = rs.getString("kho_fk");
					System.out.println("da vao day"+this.khonhanId);
					if(rs.getString("trangthai").equals("1"))
						this.sochungtu = rs.getString("SoChungTu");
					else // tu lay so chung tu view l??n
					{
						query = LaySoChungTu();
						ResultSet rsChungTu = db.get(query);
						rsChungTu.next();
						this.sochungtu  = rsChungTu.getString("SoChungTu");
						rsChungTu.close();
					}
					this.loaiDh=rs.getString("loaiDh")==null?"":rs.getString("loaiDh");
					this.ddhId= rs.getString("dondathang_fk");
					
					
				}
				rs.close();
				
				  
			 
				  query =
				  "  select isnull(a.thuevat,0) as thuevat ,0 chietkhau,b.PK_SEQ, b.MA, b.TEN, c.DONVI, isnull(a.DONGIA, 0) as DONGIA, "
				  + "   a.SOLUONG , sum(soluongNHAN ) as soluongNHAN, "
				  + " isnull(a.loai, 0) as loai, isnull(a.SCHEME, '') as SCHEME      "
				  +
				  "  from NHAPHANG_SP a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ "
				  + "		inner join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ " +
				  "  where a.NHAPHANG_FK = '" + this.id + "' "
				  		+ "  group by a.thuevat,b.PK_SEQ, b.MA, b.TEN, c.DONVI, isnull(a.DONGIA, 0) , "
				  		+ " a.SOLUONG ,    isnull(a.loai, 0)  , isnull(a.SCHEME, '')   ";
				 
				
				System.out.println("---INIT NK: " + query);
				ResultSet spRs = db.get(query);
				NumberFormat formater = new DecimalFormat("##,###,###");
				
				
				this.list=new ArrayList<SpNhaphang>();
				
				 
				 
						String spPK_SEQ = "";
						String spID = "";
						String spMA = "";
						String spTEN = "";
						String spDONVI = "";
						double spDONGIA = 0 ;
						String spVAT = "";
						String spchietkhau = "";
					 
						 
						double spSOLUONG = 0;
						String spLOAI = "";
						String spSCHEME = "";
				 
						
						while(spRs.next())
						{
							
							
							 
							spID = spRs.getString("PK_SEQ") ;
							spMA = spRs.getString("MA") ;
							spTEN = spRs.getString("TEN");
							spDONVI = spRs.getString("DONVI") ;
							spDONGIA = spRs.getDouble("DONGIA") ;
						 
							spSOLUONG = spRs.getDouble("SOLUONG");
							spchietkhau = formater.format(spRs.getDouble("chietkhau")) ;
							spVAT = formater.format(spRs.getDouble("thuevat"));
							spLOAI = spRs.getString("LOAI") ;
							
											
							 
							spSCHEME = spRs.getString("SCHEME") ;
					 
							
							SpNhaphang sp=new SpNhaphang();
							sp.setSpPK_SEQ(spPK_SEQ);
							sp.setSpMa(spMA);
							sp.setSpId(spID);
							sp.setSpTen(spTEN);
							sp.setSpDonvi(spDONVI);
							sp.setSpDongia(spDONGIA);
							sp.setSoluongnhap(spSOLUONG);
							sp.setSpchietkhau(spchietkhau);
							sp.setSpvat(spVAT);
							sp.setSpLoai(spLOAI);
							sp.setSpSCheme(spSCHEME);
							
							 List<Lochitiet> listlo =new ArrayList<Lochitiet>();
							query="select isnull(solo,'NA') as solo ,isnull( ngayhethan,'') as ngayhethan ,soluongNHAN  "
									+ "  from NHAPHANG_SP a where nhaphang_fk="+this.id +" and sanpham_fk="+spID
									+"  and isnull(scheme,'')='"+sp.getSpSCheme()+"' and  isnull(a.DONGIA, 0)= "+Double.toString(sp.getSpDongia())+" ";
							
							ResultSet rssolo=db.get(query);
							
							while (rssolo.next()){
								Lochitiet loct=new Lochitiet();
								loct.setNgayhethan(rssolo.getString("ngayhethan"));
								loct.setSolo(rssolo.getString("solo"));
								loct.setSoluong( rssolo.getDouble("soluongNHAN"));
								listlo.add(loct);
								
							}
							for(int i=0;i<5;i++){
								Lochitiet loct=new Lochitiet();
								loct.setNgayhethan("");
								loct.setSolo("");
								loct.setSoluong(0);
								listlo.add(loct);
								
							}
							sp.setList(listlo);
							
							this.list.add(sp);
							 
						}
						spRs.close();
					 
				
				
				
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
				System.out.println("---LOI INIT: " + e.getMessage());
			}
	 
		this.createRs();
	
	}

	public void DBclose() {
		
		try{
			
			this.db.shutDown();
			
		}catch(Exception er){
			
		}
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getGhichu() {
		
		return this.ghichu;
	}

	public void setGhichu(String ghichu) {
		
		this.ghichu = ghichu;
	}

	 
 
	public String getDondathangId() {
		
		return this.ddhId;
	}

	
	public void setDondathangId(String kbhId) {
		
		this.ddhId = kbhId;
	}

	
	public ResultSet getDondathangRs() {
		
		return this.ddhRs;
	}

	
	public void setDondathangRs(ResultSet ddhRs) {
		
		this.ddhRs = ddhRs;
	}
	

	 
	public boolean update() 
	{
		if(this.ngaynhan==null ||  this.ngaynhan.trim().length() <= 0)
		{
			this.msg = "Vui l??ng ch???n ng??y nh???n h??ng";
			return false;
		}
		if(this.khonhanId ==null || this.khonhanId.trim().length()<=0){
			this.msg = "Vui l??ng ch???n kho nh???n h??ng";
			return false;
		}
		if(this.list == null)
		{
			this.msg = "Vui l??ng ki???m tra l???i danh s??ch s???n ph???m nh???p kho";
			return false;
		}
	 
		
		try
		{
			db.getConnection().setAutoCommit(false);
			 
			boolean bien=this.capnhat_nhaphang(db);
			if(!bien){
				db.getConnection().rollback();
				return false;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			return false;
		}
		
		return true;
	}
	
	private boolean capnhat_nhaphang(dbutils db2) {
		// TODO Auto-generated method stub
		try{

			String query = " Update NHAPHANG set ngaynhan = '" + this.ngaynhan + "', ngaysua = '" + this.getDateTime() + "', nguoisua = '" + this.userId + "', kho_fk = '" + this.khonhanId + "' where pk_seq = '" + this.id + "' and trangthai=0  ";
			
			System.out.println("1.Update NHAPHANG: " + query);
			if(db.updateReturnInt(query)!=1 )
			{
				this.msg = "Kh??ng th??? c???p nh???t nh???p h??ng, vui l??ng tho??t ra ????? th??? l???i, kh??ng ???????c vui l??ng b??o Admin ????? tr??? gi??p ";
				db.getConnection().rollback();
				return false;
			}
			 
			query="delete NHAPHANG_sP WHERE NHAPHANG_FK="+this.id;
			
			if(!db.update(query))
			{
				this.msg = "Kh??ng th??? c???p nh???t NHAPHANG " + query;
				 
				return false;
			}
			boolean conhapsolo=false;
			
			for (SpNhaphang item : list) {
				
				boolean is_sp_conhap_lo=false; 
				for (Lochitiet lo : item.list) {
					
					if(lo.soluong>0  ){
						if(lo.getNgayhethan().trim().equals("")){
							this.msg = "Vui l??ng nh???p ng??y h???t h???n cho s???n ph???m  : "+item.getSpMa();
							 
							return false;
						}
						if(lo.getSolo().trim().equals("")){
							this.msg = "Vui l??ng nh???p s??? l?? cho s???n ph???m  : "+item.getSpMa();
							 
							return false;
						}
						String nhh=Utility.getDateString(lo.getNgayhethan().trim());
						if(nhh.length()==0 || !nhh.equals(lo.getNgayhethan())){
							this.msg = "Ng??y h???t h???n kh??ng h???p l??? format (yyyy-MM-dd), vui l??ng nh???p l???i ng??y h???t h???n:  ["+lo.getNgayhethan()+"] cho s???n ph???m "+item.getSpMa();
							 
							return false;
						}
						 
						query=" insert into NHAPHANG_sP(NHAPHANG_FK,SANPHAM_FK,SOLUONG,soluongNHAN,loai,SCHEME,donvi,DONGIA,SOLO,NGAYHETHAN,NGAYNHAPKHO,kho_fk,KHONHAN_FK) values "
								+ "("+this.id+","+item.getSpId()+","+item.getSoluongnhap()+","+lo.getSoluong()+",'"+item.getSpLoai()+"',N'"+item.getSpSCheme()+"' "
										+ ",N'"+item.getSpDonvi()+"',"+Double.toString(item.getSpDongia())+",'"+lo.getSolo().trim()+"','"+nhh+"','"+this.ngaynhan+"',"+this.khonhanId+","+this.khonhanId+")";
					 
						if(!db.update(query))
						{
							this.msg = "Kh??ng th??? c???p nh???t c???p nh???t chi ti???t nh???n h??ng, vui l??ng th??? l???i ho???c b??o Admin ????? tr??? gi??p ";
							 
							return false;
						}
						conhapsolo=true;
						is_sp_conhap_lo =true;
					}
				 
				}
				if(!is_sp_conhap_lo){
					// n???u s???n ph???m kh??ng c?? nh???p l?? n??o, th?? v???n insert 1 d??ng tr???ng , l?? tr???ng
					query=" insert into NHAPHANG_sP(NHAPHANG_FK,SANPHAM_FK,SOLUONG,soluongNHAN,loai,SCHEME,donvi,DONGIA,SOLO,NGAYHETHAN,NGAYNHAPKHO,kho_fk) values "
							+ "("+this.id+","+item.getSpId()+","+item.getSoluongnhap()+",0,'"+item.getSpLoai()+"',N'"+item.getSpSCheme()+"' "
									+ ",N'"+item.getSpDonvi()+"',"+Double.toString(item.getSpDongia())+",'','','"+this.ngaynhan+"',"+this.khonhanId+","+this.khonhanId+")";
				 
					if(!db.update(query))
					{
						this.msg = "Kh??ng th??? c???p nh???t c???p nh???t chi ti???t nh???n h??ng, vui l??ng th??? l???i ho???c b??o Admin ????? tr??? gi??p ";
						 
						return false;
					}
				}
			}
			
			
			  query =
					  "  select isnull(a.thuevat,0) as thuevat ,0 chietkhau,b.PK_SEQ, b.MA, b.TEN, c.DONVI, isnull(a.DONGIA, 0) as DONGIA, "
					  + "   a.SOLUONG , sum(soluongNHAN ) as soluongNHAN, "
					  + " isnull(a.loai, 0) as loai, isnull(a.SCHEME, '') as SCHEME      "
					  +
					  "  from NHAPHANG_SP a inner join SANPHAM b on a.SANPHAM_FK = b.PK_SEQ "
					  + "		inner join DONVIDOLUONG c on b.DVDL_FK = c.PK_SEQ " +
					  "  where a.NHAPHANG_FK = '" + this.id + "' "
					  		+ "  group by a.thuevat,b.PK_SEQ, b.MA, b.TEN, c.DONVI, isnull(a.DONGIA, 0) , "
					  		+ " a.SOLUONG ,    isnull(a.loai, 0)  , isnull(a.SCHEME, '')   "
					  		+ " having a.soluong < sum(soluongNHAN ) ";
			  
			  ResultSet rscheck=db.get(query);
			  String strc="";
			  while (rscheck.next()){
				  strc +=  rscheck.getString("ma")+" S??? l????ng xu???t: ["+rscheck.getDouble("soluong")+"] / T???ng s??? l?????ng nh???p["+rscheck.getDouble("soluongNHAN")+"] \n ";
				   
			  }
			  
			  if(strc.length()>0){
				  this.msg="Kh??ng ???????c nh???n v?????t s??? l?????ng xu???t c???a HO c???a c??c s???n ph???m sau : "+strc;
				  return false;
			  }
			  
			  
			if(!conhapsolo){
				this.msg = "Kh??ng th??? c???p nh???t c???p nh???t nh???n h??ng, vui l??ng nh???p ??t nh???t 1 l?? chi ti???t ????? nh???n h??ng ";
				return false;
			}
			return true;
			
			
		}catch(Exception er){
			 
			this.msg="L???i trong qu?? tr??nh c???p nh???t ????n ?????t h??ng, vui l??ng th??? l???i ho???c b??o Admin ????? tr??? gi??p";
			return false;
		}
	 
	}

	public String LaySoChungTu()
	{
		String query =
			"\n		select  (select loainpp from NHAPHANPHOI where PK_SEQ= NHAPHANG.NPP_FK) as loainpp,case   "+ 
			"\n			when YCXK_FK IS not null then CAST( YCXK_FK   AS VARCHAR(50) )  "+
			"\n			when CHUYENKHO_FK IS not null then (SELECT SoChungTu from ERP_CHUYENKHO where PK_SEQ=NHAPHANG.CHUYENKHO_FK)  "+
			"\n			when YCXKNPP_FK IS not null then  "+
			"\n	 "+
			"\n			(     "+
			"\n				SELECT top(1) SOHOADON FROM ERP_HOADONNPP_DDH A    "+
			"\n					INNER JOIN  ERP_YCXUATKHONPP_DDH B ON A.DDH_FK=B.DDH_FK   "+
			"\n					INNER JOIN ERP_HOADONNPP C ON C.PK_SEQ=A.HOADONNPP_FK     "+
			"\n				WHERE C.TRANGTHAI IN (2,4)	AND B.YCXK_FK=NHAPHANG.YCXKNPP_FK  "+   
			"\n			)  "+  
			"\n			when CHUYENKHONPP_FK IS NOT NULL THEN (SELECT SoChungTu from ERP_CHUYENKHONPP where pk_seq=NHAPHANG.CHUYENKHONPP_FK) end  as SoChungTu  "+
			"\n			,YCXK_FK,CHUYENKHO_FK,YCXKNPP_FK,CHUYENKHONPP_FK, NPP_FK  "+
			"\n		from NHAPHANG  " +
			"\n 	where pk_Seq='"+this.id+"'   ";
		return query;
	}
	
	public boolean chot() 
	{
		getNppInfo();
		
		Utility util = new Utility();
		msg= util.Check_Huy_NghiepVu_KhoaSo("NhapHang", this.id, "NgayNhan", db);
		if(msg.length()>0)
			return false;
		
		if(this.ngaynhan.trim().length() <= 0)
		{
			this.msg = "Vui l??ng ch???n ng??y nh???n h??ng";
			return false;
		}
	 
		try
		{ 

				db.getConnection().setAutoCommit(false);
			
				boolean bien=this.capnhat_nhaphang(db);
				if(!bien){
					db.getConnection().rollback();
					return false;
				}
				
				
		 
				// c???p nh???t tr???ng th??i l?? ???? ch???t
				String query = " Update NHAPHANG set  trangthai = '1' "+
						" where pk_seq = '" + this.id+ "' and trangthai=0 ";
	
				System.out.println("1.Update NHAPHANG: " + query);
				if (db.updateReturnInt(query) != 1)
				{
					this.msg = "Kh??ng th??? c???p nh???t NHAPHANG " + query;
					db.getConnection().rollback();
					return false;
				}
				
		 
				
				/// C???P NH???T L???I S??? L?????NG KHO CHI TI???T :
				query="select b.npp_fk ,a.khonhan_fk kho_fk,b.ngaynhan, b.NPP_FK, a.SANPHAM_FK,  "
						+ " a.soluong as soluongNHAN, 0, b.KBH_FK, a.SOLO,  Ngayhethan  " +
				"from NHAPHANG_SP a inner join NHAPHANG b on a.NHAPHANG_FK = b.PK_SEQ  " +
				"where b.PK_SEQ = '" + this.id + "'  ";
				
				
				System.out.println("query la "+query);
				ResultSet nhspRs= db.get(query);
				boolean davo = false;
				while (nhspRs.next())
				{
					String khoId= nhspRs.getString("kho_fk");
					String nppId= nhspRs.getString("NPP_FK");
					String spId= nhspRs.getString("SANPHAM_FK");
					double soluong= nhspRs.getDouble("soluongNHAN");
					String kbhId= nhspRs.getString("KBH_FK");
					String solo= nhspRs.getString("SOLO");
					String ngaynhan= nhspRs.getString("ngaynhan");
					String ngayhethan= nhspRs.getString("Ngayhethan");
					String npp_fk= nhspRs.getString("npp_fk");
					
					
					// L???Y NG??Y NH???P KHO CHO NHAPHANG_SP TR?????C
					
					
					this.msg=util.Update_NPP_Kho_Sp_Chitiet(ngaynhan , "Nh???n h??ng", db, khoId,spId , nppId, kbhId, solo, ngayhethan, ngaynhan, soluong, 0.0, soluong, soluong, 0.0);
					
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						return false;
					}
					
					this.msg=util.Update_NPP_Kho_Sp(ngaynhan, "Nh???n h??ng", db, khoId, spId, npp_fk, kbhId, soluong, 0.0, soluong, 0.0);
					
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						return false;
					}
					davo = true;
				}
				if(!davo)
				{
					this.msg = "Kh??ng th??? t??ng kho:"+ query;
					db.getConnection().rollback();
					return false;
				}
				 
			 
			
			//KHI C?? NH???P H??NG S??? FIX T??? ?????NG NH???NG NH?? ??M T???N KHO
				//s??? l?????ng d?? th?? t???o ra nh???n h??ng m???i
				
				
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				
			 
		} 
		catch (Exception e) 
		{
			db.update("rollback");
			this.msg = "Exception: " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}

	 
	public boolean create() {
		
		return false;
	}

	
	public String getNgaynhap() {
		
		return this.ngaynhan;
	}

	
	public void setNgaynhap(String ngaynhap) {
		
		this.ngaynhan = ngaynhap;
	}

	
	public String getSochungtu() {
		
		return this.sochungtu;
	}

	
	public void setSOchungtu(String sochungtu) {
		
		this.sochungtu = sochungtu;
	}

	
	public String getKhonhanId() {
		
		return this.khonhanId;
	}

	
	public void setKhonhanId(String khonhanId) {
		
		this.khonhanId = khonhanId;
	}

	
	public ResultSet getKhonhanRs() {
		
		return this.khonhanRs;
	}

	
	public void setKhonhanRs(ResultSet khonhanRs) {
		
		this.khonhanRs = khonhanRs;
	}
	String[] spNgayHetHan;

	public String[] getSpNgayHetHan()
  {
  	return spNgayHetHan;
  }

	public void setSpNgayHetHan(String[] ngayHetHan)
  {
  	this.spNgayHetHan = ngayHetHan;
  }
		
	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	String loaiDh;
	public String getLoaiDh()
  {
  	return loaiDh;
  }

	public void setLoaiDh(String loaiDh)
  {
  	this.loaiDh = loaiDh;
  }

 
	public ResultSet getKhuyenMaiRs()
	{
		if( this.id != null && this.id.length() > 0)
		{
			String query =  
							"\n select N'' MA , N'Tr??? ti???n' TEN, '' as donvi, a.DIENGIAI scheme , 1 soluong, a.giatri tonggiatri " +
							"\n from NhapHang_ChietKhau a  " +
							"\n where a.NHAPHANG_FK = '" + this.id + "'  " ;
			System.out.println("--getKMRS: " + query);
			return db.get(query);
		}
		return null;
	}

	@Override
	public void setListSp(List<SpNhaphang> list) {
		// TODO Auto-generated method stub
		this.list=list;
	}

	@Override
	public List<SpNhaphang> getListSp() {
		// TODO Auto-generated method stub
		return this.list;
	}	
}
