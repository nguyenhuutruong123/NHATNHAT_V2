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
					else // tu lay so chung tu view lên
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
			this.msg = "Vui lòng chọn ngày nhận hàng";
			return false;
		}
		if(this.khonhanId ==null || this.khonhanId.trim().length()<=0){
			this.msg = "Vui lòng chọn kho nhận hàng";
			return false;
		}
		if(this.list == null)
		{
			this.msg = "Vui lòng kiểm tra lại danh sách sản phẩm nhập kho";
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
				this.msg = "Không thể cập nhật nhập hàng, vui lòng thoát ra để thử lại, không được vui lòng báo Admin để trợ giúp ";
				db.getConnection().rollback();
				return false;
			}
			 
			query="delete NHAPHANG_sP WHERE NHAPHANG_FK="+this.id;
			
			if(!db.update(query))
			{
				this.msg = "Không thể cập nhật NHAPHANG " + query;
				 
				return false;
			}
			boolean conhapsolo=false;
			
			for (SpNhaphang item : list) {
				
				boolean is_sp_conhap_lo=false; 
				for (Lochitiet lo : item.list) {
					
					if(lo.soluong>0  ){
						if(lo.getNgayhethan().trim().equals("")){
							this.msg = "Vui lòng nhập ngày hết hạn cho sản phẩm  : "+item.getSpMa();
							 
							return false;
						}
						if(lo.getSolo().trim().equals("")){
							this.msg = "Vui lòng nhập số lô cho sản phẩm  : "+item.getSpMa();
							 
							return false;
						}
						String nhh=Utility.getDateString(lo.getNgayhethan().trim());
						if(nhh.length()==0 || !nhh.equals(lo.getNgayhethan())){
							this.msg = "Ngày hết hạn không hợp lệ format (yyyy-MM-dd), vui lòng nhập lại ngày hết hạn:  ["+lo.getNgayhethan()+"] cho sản phẩm "+item.getSpMa();
							 
							return false;
						}
						 
						query=" insert into NHAPHANG_sP(NHAPHANG_FK,SANPHAM_FK,SOLUONG,soluongNHAN,loai,SCHEME,donvi,DONGIA,SOLO,NGAYHETHAN,NGAYNHAPKHO,kho_fk,KHONHAN_FK) values "
								+ "("+this.id+","+item.getSpId()+","+item.getSoluongnhap()+","+lo.getSoluong()+",'"+item.getSpLoai()+"',N'"+item.getSpSCheme()+"' "
										+ ",N'"+item.getSpDonvi()+"',"+Double.toString(item.getSpDongia())+",'"+lo.getSolo().trim()+"','"+nhh+"','"+this.ngaynhan+"',"+this.khonhanId+","+this.khonhanId+")";
					 
						if(!db.update(query))
						{
							this.msg = "Không thể cập nhật cập nhật chi tiết nhận hàng, vui lòng thử lại hoặc báo Admin để trợ giúp ";
							 
							return false;
						}
						conhapsolo=true;
						is_sp_conhap_lo =true;
					}
				 
				}
				if(!is_sp_conhap_lo){
					// nếu sản phẩm không có nhập lô nào, thì vẫn insert 1 dòng trống , lô trống
					query=" insert into NHAPHANG_sP(NHAPHANG_FK,SANPHAM_FK,SOLUONG,soluongNHAN,loai,SCHEME,donvi,DONGIA,SOLO,NGAYHETHAN,NGAYNHAPKHO,kho_fk) values "
							+ "("+this.id+","+item.getSpId()+","+item.getSoluongnhap()+",0,'"+item.getSpLoai()+"',N'"+item.getSpSCheme()+"' "
									+ ",N'"+item.getSpDonvi()+"',"+Double.toString(item.getSpDongia())+",'','','"+this.ngaynhan+"',"+this.khonhanId+","+this.khonhanId+")";
				 
					if(!db.update(query))
					{
						this.msg = "Không thể cập nhật cập nhật chi tiết nhận hàng, vui lòng thử lại hoặc báo Admin để trợ giúp ";
						 
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
				  strc +=  rscheck.getString("ma")+" Số lương xuất: ["+rscheck.getDouble("soluong")+"] / Tổng số lượng nhập["+rscheck.getDouble("soluongNHAN")+"] \n ";
				   
			  }
			  
			  if(strc.length()>0){
				  this.msg="Không được nhận vượt số lượng xuất của HO của các sản phẩm sau : "+strc;
				  return false;
			  }
			  
			  
			if(!conhapsolo){
				this.msg = "Không thể cập nhật cập nhật nhận hàng, vui lòng nhập ít nhất 1 lô chi tiết để nhận hàng ";
				return false;
			}
			return true;
			
			
		}catch(Exception er){
			 
			this.msg="Lỗi trong quá trình cập nhật đơn đặt hàng, vui lòng thử lại hoặc báo Admin để trợ giúp";
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
			this.msg = "Vui lòng chọn ngày nhận hàng";
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
				
				
		 
				// cập nhật trạng thái là đã chốt
				String query = " Update NHAPHANG set  trangthai = '1' "+
						" where pk_seq = '" + this.id+ "' and trangthai=0 ";
	
				System.out.println("1.Update NHAPHANG: " + query);
				if (db.updateReturnInt(query) != 1)
				{
					this.msg = "Không thể cập nhật NHAPHANG " + query;
					db.getConnection().rollback();
					return false;
				}
				
		 
				
				/// CẬP NHẬT LẠI SỐ LƯỢNG KHO CHI TIẾT :
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
					
					
					// LẤY NGÀY NHẬP KHO CHO NHAPHANG_SP TRƯỚC
					
					
					this.msg=util.Update_NPP_Kho_Sp_Chitiet(ngaynhan , "Nhận hàng", db, khoId,spId , nppId, kbhId, solo, ngayhethan, ngaynhan, soluong, 0.0, soluong, soluong, 0.0);
					
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						return false;
					}
					
					this.msg=util.Update_NPP_Kho_Sp(ngaynhan, "Nhận hàng", db, khoId, spId, npp_fk, kbhId, soluong, 0.0, soluong, 0.0);
					
					if(this.msg.length()>0)
					{
						db.getConnection().rollback();
						return false;
					}
					davo = true;
				}
				if(!davo)
				{
					this.msg = "Không thể tăng kho:"+ query;
					db.getConnection().rollback();
					return false;
				}
				 
			 
			
			//KHI CÓ NHẬP HÀNG SẼ FIX TỰ ĐỘNG NHỮNG NHÀ ÂM TỒN KHO
				//số lượng dư thì tạo ra nhận hàng mới
				
				
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
							"\n select N'' MA , N'Trả tiền' TEN, '' as donvi, a.DIENGIAI scheme , 1 soluong, a.giatri tonggiatri " +
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
