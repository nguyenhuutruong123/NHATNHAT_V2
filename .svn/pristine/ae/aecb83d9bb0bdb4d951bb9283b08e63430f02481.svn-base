package geso.dms.distributor.beans.ctchietkhau.imp;

import geso.dms.distributor.beans.ctchietkhau.ICtChietKhau;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.db.sql.*;
import geso.dms.center.util.Utility;
import geso.dms.center.util.UtilitySyn;
public class CtChietKhau implements ICtChietKhau
{

	String display ="0";

	private static final long serialVersionUID = -9217977546733610214L;
	String userId;
	String id;
	String ten;
	String trangthai;
	String ngaytao;
	String nguoitao;
	String ngaysua;
	String nguoisua;
	String msg;
	String khId = "";
	String nppId = "";
	String hopdongId = "";
	dbutils db;

	ResultSet sanphamlist;
	ResultSet khRs;
	ResultSet bacsiRs;
	ResultSet hopdongRs;


	Hashtable<String, Double> sanpham_chietkhau ;
	String[] nppArr;
	String dataupload = "";
	String dataupload2 = "";
	String action = "";

	String tungay ="";
	String denngay ="";

	String ketthuc ="0";
	String isCopy = "0"; //Thêm biến, nếu = 1 thì Copy CSBH mới không bao gồm SPKM




	public String getIsCopy() {
		return isCopy;
	}
	public void setIsCopy(String isCopy) {
		this.isCopy = isCopy;
	}
	public String getDataupload2() {
		return dataupload2;
	}
	public void setDataupload2(String dataupload2) {
		this.dataupload2 = dataupload2;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getDataupload() {
		return dataupload;
	}
	public void setDataupload(String dataupload) {
		this.dataupload = dataupload;
	}

	public String[] getNppArr() {
		return nppArr;
	}
	public void setNppArr(String[] nppArr) {
		this.nppArr = nppArr;
	}
	public dbutils getDb() {
		return db;
	}

	public CtChietKhau(String[] param)
	{
		this.id 		= param[0];
		this.ten 		= param[1];

		this.trangthai 	= param[3];
		this.ngaytao 	= param[4];
		this.nguoitao 	= param[5];
		this.ngaysua 	= param[6];
		this.nguoisua 	= param[7];
		this.khId 	= param[8];
		this.hopdongId 	= param[9];
		this.msg = "";

		/*this.db = new dbutils();

		createRS();*/
	}

	public CtChietKhau()
	{

		this.id = "";
		this.ten 		= "";
		this.trangthai 	= "";
		this.ngaytao 	= "";
		this.nguoitao 	= "";
		this.ngaysua 	= "";
		this.nguoisua 	= "";
		this.msg = "";

		this.db = new dbutils();



	}

	public void getNppInfo()
	{	
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId); // dùng hàm để lấy 1 số thông tin từ npp

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

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getTen() 
	{
		return this.ten;
	}

	public void setTen(String ten) 
	{
		this.ten = ten;
	}

	public void setTrangthai(String trangthai) 
	{
		this.trangthai = trangthai;
	}

	public String getTrangthai() 
	{
		return this.trangthai;
	}


	public String getNgaytao()
	{
		return this.ngaytao;

	}

	public void setNgaytao(String ngaytao)
	{
		this.ngaytao = ngaytao;
	}

	public String getNgaysua()
	{
		return this.ngaysua;

	}

	public void setNgaysua(String ngaysua)
	{
		this.ngaysua = ngaysua;
	}

	public String getNguoitao()
	{
		return this.nguoitao;
	}

	public void setNguoitao(String nguoitao)
	{
		this.nguoitao = nguoitao;
	}

	public String getNguoisua()
	{
		return this.nguoisua;
	}

	public void setNguoisua(String nguoisua)
	{
		this.nguoisua = nguoisua;
	}

	public String getMessage() 
	{
		return this.msg;
	}

	public void setMessage(String msg) 
	{
		this.msg = msg;
	}


	public ResultSet getSanPhamList() 
	{
		return this.sanphamlist;
	}

	public void setSanPhamList(ResultSet sanphamlist) 
	{
		this.sanphamlist = sanphamlist;
	}


	public boolean CreateBgblc(HttpServletRequest request) 
	{
		try{



			db.getConnection().setAutoCommit(false);

			if(khId.trim().length() <=0)
			{
				this.msg = "Vui lòng chọn Khách hàng";
				db.getConnection().rollback();	
				db.getConnection().setAutoCommit(true);	
				return false;	
			}
			if(hopdongId.trim().length() <=0)
			{
				this.msg = "Vui lòng chọn hơp đồng";
				db.getConnection().rollback();	
				db.getConnection().setAutoCommit(true);	
				return false;	
			}

			String query = "insert into ChietKhauBacSi (diengiai,ngaytao,ngaysua,nguoitao,nguoisua,trangthai,hopdong_Fk,KHACHHANG_Fk) "+
			"\n select N'" + this.ten + "','" + Utility.getNgayHienTai() + "','" + Utility.getNgayHienTai() + "','" + this.userId + "','" + this.userId + "',0, "+hopdongId+","+khId+" " ;
			System.out.println("1. BGBLC : "+query);
			if (db.updateReturnInt(query) <=0){
				this.msg = "Không thể tạo chiết khấu bác sĩ";
				db.getConnection().rollback();	
				db.getConnection().setAutoCommit(true);	
				return false;		

			}
			query = "select scope_identity() bgblcId";				
			ResultSet rs = db.get(query);
			rs.next();
			String bgid = rs.getString("bgblcId");

			System.out.println(rs.getString("bgblcId"));


			if (!this.action.equals("upload")) 
			{
				for (String dataId : sanpham_chietkhau.keySet()) 
				{

					String[] dataArr = dataId.split("-");
					String bacsiId = dataArr[0];
					String spId = dataArr[1];

					double chietkhau =  sanpham_chietkhau.get(dataId);
					query = 	"\n insert into ChietkhauBacSi_BacSi_SanPham(ChietKhauBacSi_FK,bacsi_fk,sanpham_fk,hopdong_fk,chietkhau) " +
					"\n values('" + bgid + "',"+bacsiId+",'" + spId + "',"+this.hopdongId+", " +chietkhau +")";				
					System.out.println("2. BGBLC SP : "+query);
					if (db.updateReturnInt(query) <=0){

						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");							
						return false;		
					}

					query = 	"\n insert into ChietkhauBacSi_BacSi_SanPham_Log(ChietKhauBacSi_FK,bacsi_fk,sanpham_fk,hopdong_fk,chietkhau,ghichu,userid) " +
					"\n values('" + bgid + "',"+bacsiId+",'" + spId + "',"+this.hopdongId+", " +chietkhau +",N'Create',"+this.userId+")";				
					System.out.println("2. BGBLC SP : "+query);
					if (db.updateReturnInt(query) <=0){

						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");							
						return false;		
					}



				}


			}

			if (this.action.equals("upload")) 
			{
				this.msg = "Chức năng chưa được hỗ trợ";
				db.update("rollback");
				return false;

				/*query =  "select * from ("+this.dataupload+")up where not exists (select 1 from sanpham where ma = up.masp)";
				rs = db.get(query);
				String loi = "";
				while (rs.next()){
					loi = loi + rs.getString("masp") + ", ";
				}
				rs.close();

				if (loi.trim().length() > 0) {
					this.db.update("rollback");
					System.out.println("Loi a: "+loi);
					loi.substring(0, loi.length() - 3);
					System.out.println("Loi: "+loi);
					this.msg = "Mã SP không có trong hệ thống, vui lòng kiểm tra lại: " + loi;
					return false;
				}

				System.out.println("Dataupload: "+this.dataupload);

				query="delete from CTCK_SanPham where ctck_fk= " + bgid + " ";
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}

				query = "insert into CTCK_SanPham(SANPHAM_FK,ctck_fk,chietkhau) select (select pk_seq from sanpham where ma=up.masp),"+bgid+",up.ck from ("+dataupload+")up ";
				System.out.println("Query insert sp: "+query);
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}

				query = "insert into CTCK_SanPham_Log(SANPHAM_FK,ctck_fk,chietkhau,ghichu,userId) select (select pk_seq from sanpham where ma=up.masp),"+bgid+",up.ck,N'Create',"+this.userId+" from ("+dataupload+")up ";
				System.out.println("Query insert sp: "+query);
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}

				query =  "select * from ("+this.dataupload2+")up where not exists (select 1 from nhaphanphoi where mafast = up.manpp)";
				rs = db.get(query);
				while (rs.next()){
					loi = loi + rs.getString("mafast") + ", ";
				}
				rs.close();

				if (loi.trim().length() > 0) {
					this.db.update("rollback");
					System.out.println("Loi a: "+loi);
					loi.substring(0, loi.length() - 3);
					System.out.println("Loi: "+loi);
					this.msg = "Mã NPP không có trong hệ thống, vui lòng kiểm tra lại: " + loi;
					return false;
				}

				System.out.println("Dataupload2: "+this.dataupload2);

				query="delete from CTCK_npp where ctck_fk= " +bgid+ " ";
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}

				query = "insert into CTCK_npp(npp_fk,ctck_fk) select (select pk_seq from nhaphanphoi where mafast=up.manpp),"+bgid+" from ("+dataupload2+")up ";
				System.out.println("Query insert npp: "+query);
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}

				query = "insert into CTCK_npp_log(npp_fk,ctck_fk,ghichu,userId) select (select pk_seq from nhaphanphoi where mafast=up.manpp),"+bgid+",N'Create',"+this.userId+" from ("+dataupload2+")up ";
				System.out.println("Query insert npp: "+query);
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}*/
			}

			this.id = bgid;
			rs.close();
			db.getConnection().commit();						
			db.getConnection().setAutoCommit(true);	
			return true;

		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Loi : "+e.toString());
			db.update("rollback");
			return false;
		}

	}

	public boolean UpdateBgblc(HttpServletRequest request) 
	{	

		try
		{	
			db.getConnection().setAutoCommit(false);

			String query= " select trangthai from ChietkhauBacSi where pk_seq =  " + this.id ;
			ResultSet rs= db.get(query);
			rs.next();
			int tt = rs.getInt("trangthai");
			rs.close();


			if(khId.trim().length() <=0)
			{
				this.msg = "Vui lòng chọn Khách hàng";
				db.getConnection().rollback();	
				db.getConnection().setAutoCommit(true);	
				return false;	
			}
			if(hopdongId.trim().length() <=0)
			{
				this.msg = "Vui lòng chọn hơp đồng";
				db.getConnection().rollback();	
				db.getConnection().setAutoCommit(true);	
				return false;	
			}

			if( tt == 2)
			{
				db.getConnection().rollback();
				this.msg="  Chính sách đã kết thúc!";
				return false;
			}



			query=	"\n update ChietkhauBacSi set  diengiai = N'" + this.ten + "'" +
			"\n 	, ngaysua = '" + Utility.getNgayHienTai() + "', nguoisua = '" + this.userId + "' " +
			"\n where pk_seq = '" + this.id + "' ";

			System.out.println("query nay la gi: "+query);
			if (db.updateReturnInt(query) <=0){

				db.getConnection().rollback();
				this.msg="  chuong trinh đã chốt ";
				return false;
			}

			if (!this.action.equals("upload")) {



				query = "  insert ChietkhauBacSi_BacSi_SanPham_Log( ChietKhauBacSi_FK,bacsi_fk,sanpham_fk,hopdong_fk,chietkhau,userId,ghichu) " +
				"  select ChietKhauBacSi_FK,bacsi_fk,sanpham_fk,hopdong_fk,chietkhau,"+this.userId+",N'Trước cập nhật' " +
				"  from   ChietkhauBacSi_BacSi_SanPham where  ChietkhauBacSi_fk = " + this.id ;
				if(!db.update(query) )
				{
					this.msg=" lỗi lưu log ck: "+ query;
					db.getConnection().rollback();
					return false;
				}

				query="delete from ChietkhauBacSi_BacSi_SanPham where ChietkhauBacSi_fk= " + this.id + " ";
				if(!db.update(query))
				{
					this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
					db.update("rollback");
					return false;
				}
				if(sanpham_chietkhau != null)
					for (String dataId : sanpham_chietkhau.keySet()) 
					{

						String[] dataArr = dataId.split("-");
						String bacsiId = dataArr[0];
						String spId = dataArr[1];

						double chietkhau =  sanpham_chietkhau.get(dataId);
						query = 	"\n insert into ChietkhauBacSi_BacSi_SanPham(ChietKhauBacSi_FK,bacsi_fk,sanpham_fk,hopdong_fk,chietkhau) " +
						"\n values('" + this.id + "',"+bacsiId+",'" + spId + "',"+this.hopdongId+", " +chietkhau +")";				
						System.out.println("2. BGBLC SP : "+query);
						if (db.updateReturnInt(query) <=0){

							this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
							db.update("rollback");							
							return false;		
						}

						query = 	"\n insert into ChietkhauBacSi_BacSi_SanPham_Log(ChietKhauBacSi_FK,bacsi_fk,sanpham_fk,hopdong_fk,chietkhau,ghichu,userid) " +
						"\n values('" + this.id  + "',"+bacsiId+",'" + spId + "',"+this.hopdongId+", " +chietkhau +",'Update',"+this.userId+")";				
						System.out.println("2. BGBLC SP : "+query);
						if (db.updateReturnInt(query) <=0){

							this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
							db.update("rollback");							
							return false;		
						}



					}	 


			}

			if (this.action.equals("upload")) 
			{
				/*query =  "select * from ("+this.dataupload+")up where not exists (select 1 from sanpham where ma = up.masp)";
					rs = db.get(query);
					String loi = "";
					while (rs.next()){
						loi = loi + rs.getString("masp") + ", ";
					}
					rs.close();

					if (loi.trim().length() > 0) {
						this.db.update("rollback");
						loi = loi.substring(0, loi.length() - 3);
						System.out.println("Loi: "+loi);
						this.msg = "Mã SP không có trong hệ thống, vui lòng kiểm tra lại: " + loi;
						return false;
					}

					System.out.println("Dataupload: "+this.dataupload);

					String spExists = "";
					query = "select sanpham_fk from CTCK_SanPham where ctck_fk = "+this.id+" and sanpham_fk not in ( select pk_seq from sanpham a inner join ("+dataupload+") tmp on tmp.masp = a.ma )";
					ResultSet rsx = db.get(query);
					while (rsx.next()) {
						if (spExists.equals("")) {
							spExists += rsx.getString("sanpham_fk");
						}
						else
						{
							spExists += ","+rsx.getString("sanpham_fk");
						}
					}
					rsx.close();

				    String condition = "";
				    if(spExists.trim().length() > 0)
				    	condition = " and SanPham_FK not in ("+spExists+") ";				    

				    query = "  insert CTCK_SanPham_Log( ctck_fk,SanPham_FK,ChietKhau,userId,ghichu) " +
				    "  select "+this.id+",SanPham_FK,0,"+this.userId+", N'Delete' " +
				    "  from   CTCK_SanPham where  ctck_fk = " + this.id + " " + condition; 
				    if(!db.update(query) )
				    {
					    this.msg=" lỗi lưu log ck: "+ query;
					    db.getConnection().rollback();
					    return false;
				    }

					query="delete from CTCK_SanPham where ctck_fk= " + this.id + " ";
					if(!db.update(query))
					{
						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");
						return false;
					}

					query = "insert into CTCK_SanPham(SANPHAM_FK,ctck_fk,chietkhau) select (select pk_seq from sanpham where ma=up.masp),"+this.id+",up.ck from ("+dataupload+")up ";
					System.out.println("Query insert sp: "+query);
					if(!db.update(query))
					{
						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");
						return false;
					}
					query = "insert into CTCK_SanPham_Log(SANPHAM_FK,ctck_fk,chietkhau,ghichu,userId) select (select pk_seq from sanpham where ma=up.masp),"+this.id+",up.ck,N'Update',"+this.userId+" from ("+dataupload+")up ";
					System.out.println("Query insert sp: "+query);
					if(!db.update(query))
					{
						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");
						return false;
					}


					query =  "select * from ("+this.dataupload2+")up where not exists (select 1 from nhaphanphoi where mafast = up.manpp)";
					rs = db.get(query);
					while (rs.next()){
						loi = loi + rs.getString("manpp") + ", ";
					}
					rs.close();

					if (loi.trim().length() > 0) {
						this.db.update("rollback");
						System.out.println("Loi a: "+loi);
						loi = loi.substring(0, loi.length() - 3);
						System.out.println("Loi: "+loi);
						this.msg = "Mã NPP không có trong hệ thống, vui lòng kiểm tra lại: " + loi;
						return false;
					}

					System.out.println("Dataupload2: "+this.dataupload2);

					String nppExists  = "";   
					query = "select npp_fk from CTCK_NPP where ctck_fk ="+this.id+" and npp_fk not in ( select pk_seq from nhaphanphoi a inner join ("+dataupload2+") tmp on tmp.manpp = a.mafast ) ";
					rsx = db.get(query);
					while (rsx.next()) {
						if (nppExists.equals("")) {
							nppExists += rsx.getString("npp_fk");
						}
						else
						{
							nppExists += ","+rsx.getString("npp_fk");
						}
					}

				    condition = "";
				    if(spExists.trim().length() > 0)
				     condition = " and npp_fk not in ("+nppExists+") ";				    

				    query = "  insert CTCK_npp_Log(ctck_fk,npp_fk,userId,ghichu) " +
				    "  select ctck_fk,npp_fk,"+this.userId+", N'Delete' " +
				    "  from   CTCK_npp where  ctck_fk = " + this.id + " " + condition; 
				    if(!db.update(query) )
				    {
				     this.msg=" Lỗi lưu log CTCK_npp_Log: "+ query;
				     db.getConnection().rollback();
				     return false;
				    }

					query="delete from CTCK_npp where ctck_fk= " + this.id + " ";
					if(!db.update(query))
					{
						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");
						return false;
					}

					query = "insert into CTCK_npp(npp_fk,ctck_fk) select (select pk_seq from nhaphanphoi where mafast=up.manpp),"+this.id+" from ("+dataupload2+")up ";
					System.out.println("Query insert npp: "+query);
					if(!db.update(query))
					{
						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");
						return false;
					}

					query = "insert into CTCK_npp_log(npp_fk,ctck_fk,ghichu,userId) select (select pk_seq from nhaphanphoi where mafast=up.manpp),"+this.id+",N'Update',"+this.userId+" from ("+dataupload2+")up ";
					System.out.println("Query insert npp: "+query);
					if(!db.update(query))
					{
						this.msg = "Vui lòng liên hệ Admin để kiểm tra "+query;
						db.update("rollback");
						return false;
					}
				}
				 */			
			}
			

			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);

			return true;

		}catch(Exception e)
		{
			this.msg="Loi Xay Ra Tong Qua Trinh Sua :"+ e.getMessage() ;
			e.printStackTrace();
			db.update("rollback");
			return false;
		}

	}


	private void initSP() throws SQLException
	{

		sanpham_chietkhau = new Hashtable<String, Double>();
		String sql = " select BacSi_FK,sanpham_fk, chietkhau from [ChietkhauBacSi_BacSi_SanPham] where [ChietkhauBacSi_fk] = "+ this.id;

		ResultSet rs=  db.get(sql);
		while(rs.next())
		{
			sanpham_chietkhau.put(rs.getString("BacSi_FK") +"-"+rs.getString("sanpham_fk"), rs.getDouble("chietkhau"));
		}

		rs.close();

	}

	public void createRS()
	{
		getNppInfo();


		String query = "select pk_seq,mafast ma,ten from KhachHang a where npp_fk = "+this.nppId+" and  KBH_FK = 100052 and ( trangthai = 1 or exists (select 1 from ChietkhauBacSi where pk_seq ="+(this.id.trim().length() > 0 ? this.id :"0" )+" and khachhang_fk = a.pk_seq ) )  ";
		System.out.println("querykh = "+ query);
		this.khRs = db.get(query);

		if(this.khId.trim().length() > 0)
		{
			query =  "\n  select PK_SEQ,MaHopDong,LoaiDonHang,case when LoaiDonHang=0 then N'Bình thường'  " + 
			"\n 	when LoaiDonHang=2 then N'Nguyên tắc' when LoaiDonHang=3 then N'Hợp đồng chung' else ''  end as tenloai    " + 
			"\n  from ERP_HOPDONGNPP   " + 
			"\n  where  LoaiDonHang!=1 and ((KHACHHANG_FK= "+this.khId+" and LoaiDonHang!=3) or (LoaiDonHang=3 and KHACHHANG_FK is null) )   " + 
			"\n 	and not exists ( select 1 from ChietkhauBacSi where hopdong_fk =ERP_HOPDONGNPP.pk_seq and  ChietkhauBacSi.pk_seq != "+(this.id.trim().length() > 0 ? this.id :"0" )+"  ) "+
			"\n 	and trangthai in (1,2)	and NPP_FK = " + this.nppId  ;
			System.out.println("query  hop dong = "+ query);
			this.hopdongRs = db.get(query);

			if(hopdongId.trim().length() >0)
			{
				query =  " select pk_seq,ten from BacSi where trangthai = 1 and pk_seq in ( select bacsi_fk from   KhachHang_BacSi where KhachHang_fk = "+this.khId+") ";
				this.bacsiRs = db.getScrol(query);

				query =" select pk_seq,ma, ten from sanpham where pk_seq in (select sanpham_Fk from  ERP_HOPDONGNPP_SANPHAM where hopdong_fk = "+this.hopdongId+" )  ";
				System.out.println("Query SP:" +query);
				this.sanphamlist = db.getScrol(query);
			}
		}

	}	
	public void init()
	{
		System.out.println("ID: "+this.id);
		ResultSet rs = this.db.get("select pk_seq,diengiai, trangthai,hopdong_fk,khachhang_fk from ChietkhauBacSi where pk_seq='" + this.id + "'");
		try
		{
			if (rs != null)
			{
				rs.next();
				this.ten = rs.getString("diengiai");
				this.setTrangthai(rs.getString("trangthai"));
				this.hopdongId  = rs.getString("hopdong_fk");
				this.khId  = rs.getString("khachhang_fk");
				if(this.trangthai.equals("2")) this.ketthuc = "1";
				else this.ketthuc = "0";

				if(rs!=null){
					rs.close();
				}
				initSP();	
			}
		}catch(Exception e){ e.printStackTrace(); }

		createRS();
	}

	@Override
	public void DbClose() {
		try{

			if( sanphamlist!=null){
				sanphamlist.close();
			}
		}catch(Exception er){

		}
	}

	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	public Hashtable<String, Double> getSanpham_chietkhau() {
		return sanpham_chietkhau;
	}
	public void setSanpham_chietkhau(Hashtable<String, Double> sanpham_chietkhau) {
		this.sanpham_chietkhau = sanpham_chietkhau;
	}
	public String getTungay() {
		return tungay;
	}
	public void setTungay(String tungay) {
		this.tungay = tungay;
	}
	public String getDenngay() {
		return denngay;
	}
	public void setDenngay(String denngay) {
		this.denngay = denngay;
	}
	public String getKetthuc() {
		return ketthuc;
	}
	public void setKetthuc(String ketthuc) {
		this.ketthuc = ketthuc;
	}

	public String getKhId() {
		return khId;
	}
	public void setKhId(String khId) {
		this.khId = khId;
	}
	public ResultSet getKhRs() {
		return khRs;
	}
	public void setKhRs(ResultSet khRs) {
		this.khRs = khRs;
	}
	public ResultSet getBacsiRs() {
		return bacsiRs;
	}
	public void setBacsiRs(ResultSet bacsiRs) {
		this.bacsiRs = bacsiRs;
	}
	public ResultSet getHopdongRs() {
		return hopdongRs;
	}
	public void setHopdongRs(ResultSet hopdongRs) {
		this.hopdongRs = hopdongRs;
	}
	public String getHopdongId() {
		return hopdongId;
	}
	public void setHopdongId(String hopdongId) {
		this.hopdongId = hopdongId;
	}

}


