package geso.dms.center.beans.capnhatnhanvien.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


import geso.dms.center.beans.capnhatnhanvien.ICapnhatnhanvien;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

public class Capnhatnhanvien implements ICapnhatnhanvien {

	String Ten;
	String Ngaysinh;
	String Diachi;
	String Dienthoai;
	String Email;
	String Tendangnhap;
	String matkhau;
	//String Trangthai;
	String Phanloai;
	String userId;
	ResultSet quyen;
	String nppId;
	String userTen;
	String trangthai;
	ResultSet quyenchon;
	String Id;
	String msg;
	ResultSet kenh;
	ResultSet kenhchon;
	ResultSet npp;
	ResultSet nppchon;
	ResultSet sanpham;
	ResultSet sanphamchon;
	ResultSet kho;
	ResultSet khochon;
	String vungId;
	ResultSet vung;
	String khuvucId;
	ResultSet khuvuc;
	String nhanhangId;
	ResultSet nhanhang;
	String chungloaiId;
	ResultSet chungloai;
	ResultSet nhaphanphoi;
	
	//dành cho lập kế hoạch nhân viên
	String loai = "0"; //Loại nhân viên: 0: nhân viên | 1: RSM | 2: ASM | 3: SS
	String loaiId = ""; //Mã RSM | ASM | SS tùy loại nhân viên
	ResultSet loaiRs;
	ResultSet DanhmucquyenRs;
	
	String sohoadontu;
	String sohoadonden;
	
	dbutils db;
	String chon;
	String nppIds;
	
	//danh muc quyen
	String quyenId;
	
	public Capnhatnhanvien()
	{   this.Id ="";
		this.Ten="";
		this.Ngaysinh="";
		this.Dienthoai="";
		this.Email="";
		this.Tendangnhap="";
		this.matkhau = " ";
		this.Phanloai="";
		this.userId="";
		this.nppId="";
		this.userTen="";
		this.trangthai="";
		this.vungId ="";
		this.khuvucId ="";
		this.nhanhangId ="";
		this.chungloaiId="";
		this.Phanloai ="";
		this.chon ="1";
		this.msg="";
		this.nppIds="";
		this.kenhId="";
		this.activeTab="0";
		
		this.quyenId = "";
		this.sohoadontu="";
		this.sohoadonden = "";
		this.qhId="";
		this.ttId="";
		db = new dbutils();
	}
	public Capnhatnhanvien(String Id)
	{   this.Id = Id;
		this.userId="";
		this.nppId="";
		this.matkhau = " ";
		this.userTen="";
		this.trangthai="";
		this.vungId ="";
		this.khuvucId ="";
		this.nhanhangId ="";
		this.chungloaiId="";
		this.chon ="1";
		this.Phanloai="";
		this.msg="";
		this.nppIds="";
		this.kenhId="";
		this.activeTab="0";
		
		this.sohoadontu="";
		this.sohoadonden = "";
		this.quyenId = "";
		this.qhId="";
		this.ttId="";
		db = new dbutils();
	}
	
	
	public void setuserId(String userId) {
		
		this.userId = userId;
	}
	
	public String getuserId() {
		
		return userId;
	}
	
	public void setnppId(String nppId) {
		
		this.nppId = nppId;
	}
	
	public String getnppId() {
		
		return this.nppId;
	}
	
	public void setTen(String Ten) {
		
		this.Ten = Ten;
	}
	
	public String getTen() {
		
		return this.Ten;
	}
	
	public void setngaysinh(String ngaysinh) {
		
		this.Ngaysinh = ngaysinh;
	}
	
	public String getngaysinh() {
		
		return this.Ngaysinh;
	}
	
	public void setemail(String email) {
		
		this.Email = email;
	}
	
	public String getemail() {
		
		return this.Email;
	}
	
	public void setdienthoai(String dienthoai) {
		
		this.Dienthoai = dienthoai;
	}
	
	public String getdienthoai() {
		
		return this.Dienthoai;
	}
	
	public void settendangnhap(String tendangnhap) {
		
		this.Tendangnhap = tendangnhap;
	}
	
	public String gettendangnhap() {
		
		return this.Tendangnhap;
	}
	
	public void setmatkhau(String matkhau) {
		
		this.matkhau = matkhau;
	}
	
	public String getmatkhau() {
		
		return this.matkhau;
	}

	public void setphanloai(String phanloai) {
		
		this.Phanloai = phanloai;
	}
	
	public String getphanloai() {
		
		return this.Phanloai;
	}
	
	public void setquyen(ResultSet quyen) {
		
		this.quyen = quyen;
	}
	
	
	public void init() {
		
		if(this.Id.length()>0)
		{
			String sql =" select ten,isnull(ngaysinh, ' ') as ngaysinh, isnull(dienthoai,' ') as dienthoai, isnull(email, ' ' ) as email,dangnhap,isnull(phanloai,'') as phanloai,isnull(convsitecode,'') as convsitecode,trangthai, isnull(loai, 0) as loai, bm_fk, asm_fk, gsbh_fk," +
					    " isnull(sohoadontu,'') as sohoadontu, isnull(sohoadonden,'') as sohoadonden " +
					    " from nhanvien where pk_seq ='"+ this.Id+"'";
			System.out.println(sql);
			ResultSet rs = db.get(sql);
			try {
				while(rs.next())
				{
					this.Ten= rs.getString("ten");
					if(rs.getString("ngaysinh") == null || rs.getString("ngaysinh").equals("null") || rs.getString("ngaysinh").equals("NULL") )
						this.Ngaysinh = " ";
					else
						this.Ngaysinh = rs.getString("ngaysinh");
					
					if(rs.getString("dienthoai") == null || rs.getString("dienthoai").equals("null") || rs.getString("dienthoai").equals("NULL") )
						this.Dienthoai = " ";
					else
						this.Dienthoai=rs.getString("dienthoai");
									
					if(rs.getString("email") == null || rs.getString("email").equals("null") || rs.getString("email").equals("NULL") )
						this.Email = " ";
					else
						this.Email = rs.getString("email");
					
					this.Tendangnhap=rs.getString("dangnhap");
					
					this.Phanloai=rs.getString("phanloai");
					
					this.sohoadontu = rs.getString("sohoadontu");
					
					this.sohoadonden = rs.getString("sohoadonden");
					
					this.nppId = rs.getString("convsitecode");
					System.out.println("NPPID "+this.nppId );
					
					this.userId="";
					
					this.userTen="";
					
					this.trangthai=rs.getString("trangthai");
					
					if(this.Phanloai.equals("2")) {
						this.loai = rs.getString("loai");
						if(this.loai == null) this.loai = "0";
						if(this.loai.equals("1")) {
							this.loaiId = rs.getString("bm_fk");
						} else if(this.loai.equals("2")) {
							this.loaiId = rs.getString("asm_fk");
						} else if(this.loai.equals("3")) {
							this.loaiId = rs.getString("gsbh_fk");
						}
					}
					CreateLoaiRs();
					
					this.msg="";
				}
				rs.close();
			String	query="select npp_fk from phamvihoatdong where nhanvien_fk='"+this.Id+"'";
			System.out.println("++++++______________"+query);
			rs=db.get(query);
			while(rs.next())
			{
				this.nppIds+=rs.getString("npp_fk")+",";
			}
			if(this.nppIds.length()>0)
			{
				this.nppIds=this.nppIds.substring(0,this.nppIds.length()-1);
			}
			if(rs!=null)
			{
				rs.close();
			}
				System.out.println("[NppId]"+nppIds+"[query]"+query);
				
			if(this.Phanloai.equals("1"))
			{
				query =
					" SELECT a.Ten, a.PK_SEQ FROM DANHMUCQUYEN a inner join PHANQUYEN b on a.PK_SEQ = b.DMQ_fk \n"+
					"  where Nhanvien_fk ='"+this.Id+"' \n";
				rs=db.get(query);
				
				while(rs.next())
				{
					this.quyenId+=rs.getString("PK_SEQ");
				}
				
				if(rs!=null)
				{
					rs.close();
				}
				System.out.println(query);
				
			}	
			
			} catch(Exception e) {
				
				e.printStackTrace();
			}
		}
	}
   
	public ResultSet getquyen() {
		
		return this.quyen;
	}
	public void setquyenchon(ResultSet quyenchon) {
	
		this.quyenchon = quyenchon;
	}

	public ResultSet getquyenchon() {
	
		return this.quyenchon;
	}
	
	public void settrangthai(String trangthai) {
		
		this.trangthai = trangthai;
	}
	
	public String gettrangthai() {
		
		return this.trangthai;
	}
	
	public void setmsg(String msg) 
	{
		this.msg = msg;
	}
	
	public String getmsg() {
		
		return this.msg;
	}
	boolean xoa()
	{
		String sql ="delete from phanquyen where nhanvien_fk ='"+this.Id +"'";
		
		if(!db.update(sql))
			{
			this.msg = sql;
			return false;
			}
		System.out.println(sql);
		sql ="delete from phamvihoatdong where nhanvien_fk ='"+ this.Id+"'";
		
		if(!db.update(sql))
		{
			this.msg = sql;
			return false;
		}
		System.out.println(sql);
		sql ="delete from nhanvien_kenh where nhanvien_fk ='"+ this.Id +"'";
		
		if(!db.update(sql))
		{
			this.msg = sql;
			return false;
		}
		System.out.println(sql);
		sql ="delete from nhanvien_sanpham where nhanvien_fk ='"+ this.Id +"'";
		
		if(!db.update(sql))
		{
			this.msg = sql;
			return false;
		}
		System.out.println(sql);
		
		sql ="delete from nhanvien_kho where nhanvien_fk ='"+ this.Id +"'";
		
		if(!db.update(sql))
		{
			this.msg = sql;
			return false;
		}
		System.out.println(sql);
		
		return true;
	}
	boolean kiemtra()
	{ 
		
		String sql = " select count(*) as num  from nhanvien where pk_seq ='"+ this.Id +"' and pk_seq ='"+ this.userId +"'";
		ResultSet rs = db.get(sql);
		try {
			if(rs.next())
			{
				if(rs.getString("num").equals("0"))
					return false;
			}
			rs.close();
		} catch(Exception e) {
			
		}
		return true;
	}
	
	public boolean save() 
	{
		if(this.Id.length()>0 )
		{
			if(kiemtra())
			{
				this.msg ="Bạn không được cập nhật quyền cho chính mình";
				return false;
			}
		}
		
		if(this.Ten ==""|| this.Ngaysinh =="" ||this.Tendangnhap=="")
		{  
			this.msg ="Bạn phải nhập đầy đủ thông tin";
			return false;
		}
		
		if(KiemTra_TenDangNhap()!=0)
		{
			this.msg = "Tên đăng nhập này đã có người sử dụng,vui lòng đổi lại";
			return false;
		}			
		
		if(this.loai == null) {
			this.loai = "0";
		}
		
		// Kiểm tra Sohoadontu && Sohoadonden phải nam trong khoảng Sohoadon của CN/Doitac khai trong DLN
		String query = "select isnull(sohoadontu,'0') as sohoadontu , isnull(sohoadonden,'0') as sohoadonden from NHAPHANPHOI where CONVSiteCode = '"+ this.nppId +"'";
		System.out.println("SO hoa don "+query);
		ResultSet LaySHD = db.get(query);
		String SoHDtu = "";
		String SoHDden = "";
		if(LaySHD!= null)
		{
			try
			{
				while(LaySHD.next())
				{
					SoHDtu = LaySHD.getString("sohoadontu");
					
					SoHDden = LaySHD.getString("sohoadonden");
				}
				LaySHD.close();
			}catch(Exception e)
			{
				msg =" Lỗi trong quá trình lấy Số hóa đơn của CN/ĐỐI TÁC ";
				e.printStackTrace();
				return false;						
			}
		}
		if(SoHDtu.trim().length() <=0) SoHDtu ="0";
		if(SoHDden.trim().length() <=0) SoHDden ="0";
		
		if(this.Id.length() >0)
		{	
			String sql;
			String bm_fk = null, asm_fk = null, gsbh_fk = null;
			if(this.Phanloai.equals("2"))
			{
				if(loai.equals("1")) {
					bm_fk = loaiId;
				} else if (loai.equals("2")) {
					asm_fk = loaiId;
				} else if (loai.equals("3")) {
					gsbh_fk = loaiId;
				}
				
				sql ="update nhanvien set ten = N'"+ this.Ten +"',ngaysinh = '"+ this.Ngaysinh +"',dangnhap = N'"+this.Tendangnhap+"', convsitecode=null " ;
						if(!this.matkhau.trim().equals("")){
							sql=sql+ " , matkhau = pwdencrypt('" + this.matkhau.trim() + "') " ;
						}
				sql = sql +		" , email ='"+ this.Email+"',dienthoai = '"+this.Dienthoai+"',trangthai = '"+this.trangthai+"',ngaysua ='"+ this.getDateTime() +"',nguoisua ='"+ this.userId+"',phanloai= '"+ this.Phanloai +"', sessionid='"+this.getDateTime()+"', loai = "+loai+", bm_fk = "+bm_fk+", asm_fk = "+asm_fk+", gsbh_fk = "+gsbh_fk+" where pk_seq ='"+ this.Id +"'";
				
				//System.out.println(sql);
			}
			else
			{
				if(this.sohoadontu.trim().length() > 0 && this.sohoadonden.trim().length() > 0 )
				{
					if((Integer.parseInt(this.sohoadontu) >=  Integer.parseInt(SoHDtu.length()<=0?"0":SoHDtu) 
							&& Integer.parseInt(this.sohoadontu) < Integer.parseInt(SoHDden.length()<=0?"0":SoHDden) 
							&& Integer.parseInt(this.sohoadonden) >  Integer.parseInt(this.sohoadontu) 
							&& Integer.parseInt(this.sohoadonden) <= Integer.parseInt(SoHDden.length()<=0?"0":SoHDden)
					) ||( Integer.parseInt(SoHDtu.length()<=0?"0":SoHDtu) ==0 && Integer.parseInt(SoHDden.length()<=0?"0":SoHDden) ==0 ))
				    {
						sql ="update nhanvien set ten = N'"+ this.Ten +"',ngaysinh = '"+ this.Ngaysinh +"',dangnhap = N'"+this.Tendangnhap+"',bm_fk=null,asm_fk=null,gsbh_fk=null,loai=null, " ;
						if(!this.matkhau.trim().equals(""))
						{
							sql = sql + "  matkhau = pwdencrypt('" + this.matkhau.trim() + "'), " ;
						}
						sql = sql + " email ='"+ this.Email+"',dienthoai = '"+this.Dienthoai+"',trangthai = '"+this.trangthai+"',ngaysua ='"+ this.getDateTime() +"',nguoisua ='"+ this.userId+"',phanloai= '"+ this.Phanloai +"',convsitecode =N'"+ this.nppId +"', sessionid='"+this.getDateTime()+"', sohoadontu = '"+ this.sohoadontu +"', sohoadonden = '"+ this.sohoadonden +"' " +
									" where pk_seq ='"+ this.Id +"'";
					}
					else
					{
						this.msg = "Số hóa đơn của user này không hợp lệ ";
						db.update("rollback");
						return false;
					}
				}
				else
				{
					sql ="update nhanvien set ten = N'"+ this.Ten +"',ngaysinh = '"+ this.Ngaysinh +"',dangnhap = N'"+this.Tendangnhap+"',bm_fk=null,asm_fk=null,gsbh_fk=null,loai=null, " ;
					if(!this.matkhau.trim().equals("")){
						sql=sql+	"  matkhau = pwdencrypt('" + this.matkhau.trim() + "'), " ;
					}
							sql=sql+		"  email ='"+ this.Email+"',dienthoai = '"+this.Dienthoai+"',trangthai = '"+this.trangthai+"',ngaysua ='"+ this.getDateTime() +"',nguoisua ='"+ this.userId+"',phanloai= '"+ this.Phanloai +"',convsitecode =N'"+ this.nppId +"', sessionid='"+this.getDateTime()+"' " +
									"where pk_seq ='"+ this.Id +"'";
				}
			
			}
			System.out.println("lenh update:"+ sql);
			try {
				db.getConnection().setAutoCommit(false);
				
				if(!xoa()) 
				{
					return false ;// khong xoa duoc
				}
				else
				{
					if(this.Phanloai.equals("2"))
					{   if(!createUpdate(db))
						{ return false; } }
				}	
				
				if(!db.update(sql))
				{	db.update("rollback");
					this.msg =sql;
					return false;
				}
				System.out.println("quyền: "+this.quyenId);
				if(this.quyenId.trim().length()>0)
				{
					sql = "delete phanquyen where nhanvien_fk='"+this.Id+"'";
					System.out.println(sql);
					if (!db.update(sql))
					{
						this.msg = "không thể xóa phanquyen";
						db.update("rollback");
						return false;
					}
					
					sql=" delete from NhanVien_UngDung  where nhanvien_Fk   = "+this.Id ;
					
					System.out.println(sql);
					
					if (!db.update(sql))
					{
						this.msg = "không thể xóa NhanVien_UngDung";
						db.update("rollback");
						return false;
					}
					
					sql ="insert phanquyen values ("+this.Id+","+this.quyenId+")";
					System.out.println(sql);
					if (!db.update(sql))
					{
						this.msg = "không thể xóa phanquyen";
						db.update("rollback");
						return false;
					}
						
					sql=
						" insert into NhanVien_UngDung(UngDung_fk,NhanVien_fk)  "+
						" select distinct nq.UngDung_fk,pq.Nhanvien_fk from NHOMQUYEN nq inner join phanquyen pq on pq.DMQ_fk=nq.DMQ_fk  "+
						" inner join UNGDUNG ud on ud.PK_SEQ=nq.UngDung_fk  "+
					    " where nq.HienThi=1 and ud.TRANGTHAI=1 and pq.Nhanvien_fk ='"+this.Id+"' and nq.DMQ_fk ='"+this.quyenId+"'";
					System.out.println(sql);
					if (!db.update(sql))
					{
						this.msg = "không thể thêm mới NhanVien_UngDung";
						db.update("rollback");
						return false;
					}
					
					if(this.qhId.length()>0)
					{
						sql =
							"  insert into NhanVien_QuanHuyen(QuanHuyen_fK,NhanVien_fk)  "+
							"  select pk_Seq ,'"+this.Id+"' From QuanHuyen where pk_Seq in ("+this.qhId+")";
							System.out.println(sql);
							if (!db.update(sql))
							{
								this.msg = "không thể thêm mới NhanVien_QuanHuyen";
								db.update("rollback");
								return false;
							}
					}
					
					if(this.ttId.length()>0)
					{
						sql=
							"  insert into NhanVien_TinhThanh(TinhThanh_fK,NhanVien_fk)  "+
							"  select pk_Seq ,'"+this.Id+"' From TinhThanh where pk_Seq in ("+this.ttId+")";
							System.out.println(sql);
							if (!db.update(sql))
							{
								this.msg = "không thể thêm mới NhanVien_TinhThanh";
								db.update("rollback");
								return false;
							}
					}
					if(this.khochon != null)
					{ 
						while(khochon.next())
						{
							sql = "insert into nhanvien_kho(nhanvien_fk,kho_fk) values ('"+ this.Id+"','"+ khochon.getString("pk_seq")+"')";
							System.out.println(sql);
							if(!db.update(sql))
							{	db.update("rollback");
							    this.msg =sql;
								return false;
							}
						}
					}
					
				sql=
				"	update nhanvien set timkiem=  "+
				"			upper(dbo.ftBoDau(isnull(kh.TEN,''))) +   '-' +  "+
				"							upper(dbo.ftBoDau(isnull(kh.DANGNHAP,''))) +   '-' +  "+
				"							upper(dbo.ftBoDau(isnull(kh.DIENTHOAI,''))) +   '-' +  "+
				"							upper(dbo.ftBoDau(isnull(kh.EMAIL,''))) +   '-' +  "+
				"							upper(dbo.ftBoDau(isnull(kh.CONVSITECODE,'')))  "+
				"			from NHANVIEN kh				  "+
				"			where pk_Seq='"+this.Id+"'";
				if(!db.update(sql))
				{	
					db.update("rollback");
				  this.msg =sql;
					return false;
				}
			}				
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			} catch(Exception e) 
			{
				db.get("rollback");
				e.printStackTrace();
			}
		}
		else
		{      
			String sql= "";
			String bm_fk = null, asm_fk = null, gsbh_fk = null;
			if(this.Phanloai.equals("2"))
			{
				if(loai.equals("1")) {
					bm_fk = loaiId;
				} else if (loai.equals("2")) {
					asm_fk = loaiId;
				} else if (loai.equals("3")) {
					gsbh_fk = loaiId;
				}
				
				sql =  " insert into nhanvien(ten,ngaysinh,dangnhap,matkhau,email,dienthoai,trangthai,ngaytao,ngaysua,nguoitao,nguoisua,phanloai, sessionid, loai, bm_fk, asm_fk, gsbh_fk) " +
						"values(N'"+ this.Ten +"','"+ this.Ngaysinh +"','"+this.Tendangnhap.trim()+"',pwdencrypt('"+ this.matkhau.trim() + "'),'"+ this.Email+"','"+this.Dienthoai+"','"+this.trangthai+"','"+ this.getDateTime() +"','"+ this.getDateTime() +"','"+ this.userId+"','"+ this.userId+"','"+ this.Phanloai +"', '2012-01-01', " + this.loai + ", " + bm_fk + ", " + asm_fk + ", " + gsbh_fk + ") ";
			}
			else
			{
				
				
			   if(this.sohoadontu.trim().length() > 0 && this.sohoadonden.trim().length() > 0 )
			   {
					System.out.println(this.sohoadontu+"----"+SoHDtu+"-----"+this.sohoadonden+"-----"+SoHDden);
				   if( (Integer.parseInt(this.sohoadontu) >=  Integer.parseInt(SoHDtu) 
						   && Integer.parseInt(this.sohoadontu) < Integer.parseInt(SoHDden)
						   && Integer.parseInt(this.sohoadonden) >  Integer.parseInt(this.sohoadontu)
						   && Integer.parseInt(this.sohoadonden) <= Integer.parseInt(SoHDden)
						)||
						( Integer.parseInt(SoHDtu.length()<=0?"0":SoHDtu) ==0 && Integer.parseInt(SoHDden.length()<=0?"0":SoHDden) ==0 )
				   
				   
				   )
					{					
						sql ="insert into nhanvien(ten,ngaysinh,dangnhap,matkhau,email,dienthoai,trangthai,ngaytao,ngaysua,nguoitao,nguoisua,phanloai,convsitecode, sessionid, sohoadontu, sohoadonden ) " +
							 "values(N'"+ this.Ten +"','"+ this.Ngaysinh +"','"+this.Tendangnhap.trim()+"', pwdencrypt('"+ this.matkhau.trim() + "'),'"+ this.Email+"','"+this.Dienthoai+"','"+this.trangthai+"','"+ this.getDateTime() +"','"+ this.getDateTime() +"','"+ this.userId+"','"+ this.userId+"','"+ this.Phanloai +"',N'"+ this.nppId +"','2012-01-01', '"+ this.sohoadontu +"', '"+ this.sohoadonden +"')";
					}else
					{
						this.msg = "Số hóa đơn của user này không hợp lệ ";
						db.update("rollback");
						return false;
					}
			   }
			   else
			   {
				   sql ="insert into nhanvien(ten,ngaysinh,dangnhap,matkhau,email,dienthoai,trangthai,ngaytao,ngaysua,nguoitao,nguoisua,phanloai,convsitecode, sessionid ) " +
					 "values(N'"+ this.Ten +"','"+ this.Ngaysinh +"','"+this.Tendangnhap.trim()+"', pwdencrypt('"+ this.matkhau.trim() + "'),'"+ this.Email+"','"+this.Dienthoai+"','"+this.trangthai+"','"+ this.getDateTime() +"','"+ this.getDateTime() +"','"+ this.userId+"','"+ this.userId+"','"+ this.Phanloai +"',N'"+ this.nppId +"','2012-01-01')";
			
			   }
			   
			}
			System.out.println("[Capnhatnhanvien.save] sql = " + sql);
			
			try {
				
				db.getConnection().setAutoCommit(false);
				if(!db.update(sql))
				{	db.update("rollback");
					this.msg =sql;
					return false;
				}
				
				System.out.println(sql);
				sql = "select scope_identity() as nv";
				
				ResultSet rsDh = this.db.get(sql);						
				rsDh.next();
				this.Id = rsDh.getString("nv");
				rsDh.close();
				
				if(this.Phanloai.equals("2"))
				{
						if(!createUpdate(db))
						{  this.Id="";
							return false;
						}
				}
				else
				{
				if(this.quyenId.trim().length()>0){
					
					sql = "delete phanquyen where nhanvien_fk='"+this.Id+"'";
					System.out.println(sql);
					if (!db.update(sql))
					{
						this.msg = "không thể xóa phanquyen";
						db.update("rollback");
						return false;
					}
					
					sql="	delete from NhanVien_UngDung  	where nhanvien_Fk   = "+this.Id ;
					
					System.out.println(sql);
					
					if (!db.update(sql))
					{
						this.msg = "không thể xóa NhanVien_UngDung";
						db.update("rollback");
						return false;
					}
					
					
					sql =" insert phanquyen values ("+this.Id+","+this.quyenId+")";
					System.out.println(sql);
					
					if (!db.update(sql))
					{
						this.msg = "không thể xóa phanquyen";
						db.update("rollback");
						return false;
					}
					
					sql=
						"  insert into NhanVien_UngDung(UngDung_fk,NhanVien_fk)  "+
						"  select distinct nq.UngDung_fk,pq.Nhanvien_fk from NHOMQUYEN nq inner join phanquyen pq on pq.DMQ_fk=nq.DMQ_fk  "+
						"  inner join UNGDUNG ud on ud.PK_SEQ=nq.UngDung_fk  "+
					   " 	where nq.HienThi=1 and ud.TRANGTHAI=1 and pq.Nhanvien_fk ='"+this.Id+"' and nq.DMQ_fk ='"+this.quyenId+"'";
					System.out.println(sql);
					if (!db.update(sql))
					{
						this.msg = "không thể thêm mới NhanVien_UngDung";
						db.update("rollback");
						return false;
					}
					
					if(this.khochon != null)
					{ 
						while(khochon.next())
						{
							sql = "insert into nhanvien_kho(nhanvien_fk,kho_fk) values ('"+ this.Id+"','"+ khochon.getString("pk_seq")+"')";
							System.out.println(sql);
							if(!db.update(sql))
							{	db.update("rollback");
							    this.msg =sql;
								return false;
							}
						}
					}
				}
			}
				sql=
						"	update nhanvien set timkiem=  "+
						"			upper(dbo.ftBoDau(isnull(kh.TEN,''))) +   '-' +  "+
						"							upper(dbo.ftBoDau(isnull(kh.DANGNHAP,''))) +   '-' +  "+
						"							upper(dbo.ftBoDau(isnull(kh.DIENTHOAI,''))) +   '-' +  "+
						"							upper(dbo.ftBoDau(isnull(kh.EMAIL,''))) +   '-' +  "+
						"							upper(dbo.ftBoDau(isnull(kh.CONVSITECODE,'')))  "+
						"			from NHANVIEN kh				  "+
						"			where pk_Seq='"+this.Id+"'";
						if(!db.update(sql))
						{	
							db.update("rollback");
						  this.msg =sql;
							return false;
						}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			} catch(Exception e) 
			{
				this.db.update("rollback");
				this.Id="";
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean update() {
		return false;
	}
	
	
	public void CreateQuyen(String[] chuoi) {
		String st="(";
		if(chuoi!=null)
		{
			for(int i =0;i< chuoi.length;i++)
				st =st + chuoi[i]+",";
			st =st.substring(0,st.length()-1);
			st = st +")";	               
			
		}
		String sql;
			
		if(this.Id.length()>0)
	    	{
		     sql =" select * from danhmucquyen where pk_seq not in (select dmq_fk from phanquyen where nhanvien_fk ='"+ this.Id +"')";
			}
		else
			 sql = "select * from danhmucquyen";
		 if(chuoi !=null)
			sql = "select * from danhmucquyen where pk_seq not in " +st;
		 
		 quyen = db.get(sql);
		   
		   if(this.Id.length()>0)
		   {
		     sql = "select * from danhmucquyen a ,phanquyen b where a.pk_seq = b.dmq_fk and b.nhanvien_fk='"+ this.Id +"'";
		     quyenchon = db.get(sql);
		   }
		   if(chuoi !=null)
			 {
			   sql = "select * from danhmucquyen where pk_seq in " +st;
			   quyenchon = db.get(sql);
			 }
	}

	
	public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	public void setId(String Id) {
		this.Id = Id;
		
	}
	
	public String getId() {
		
		return this.Id;
	}
	
	public void setkenh(ResultSet kenh) {
		
		this.kenh= kenh;
	}
	
	public ResultSet getkenh() {
		
		return this.kenh;
	}
	
	public void setkenhchon(ResultSet kenhchon) {
		
		this.kenhchon = kenhchon;
	}
	
	public ResultSet getkenhchon() {
		
		return this.kenhchon;
	}
	
	public void CreateKenh(String[] chuoi) {
		
		String st="";
		if(chuoi!=null)
		{ st="(";
			for(int i =0;i< chuoi.length;i++)
				st =st + chuoi[i]+",";
			st =st.substring(0,st.length()-1);
			st = st +")";	               
			
		}
		System.out.println("dieu kien"+ st);
		String sql;
			
		if(this.Id.length()>0)
	    	{
		     sql =" select * from kenhbanhang where pk_seq not in (select kenh_fk from NHANVIEN_KENH where nhanvien_fk ='"+ this.Id +"')";
			}
		else
			 sql = "select * from kenhbanhang";
		 if(chuoi !=null)
			sql = "select * from kenhbanhang where pk_seq not in " +st;
		 
		 kenh = db.get(sql);
		  if(this.Id.length()>0)
		   {
		     sql = "select * from kenhbanhang a ,NHANVIEN_KENH b where a.pk_seq = b.kenh_fk and b.nhanvien_fk='"+ this.Id +"'";
		     System.out.println(sql);
		     kenhchon = db.get(sql);
		   }
		   if(chuoi !=null)
			 {
			   sql = "select * from kenhbanhang where pk_seq in " +st;
			   System.out.println(sql);
			   kenhchon = db.get(sql);
			 }
		   System.out.println("ngoai:"+sql);
	}
	
	public void setnpp(ResultSet npp) {
		
		this.npp = npp;
	}
	
	public ResultSet getnpp() {
		
		return this.npp;
	}
	
	public void setnppchon(ResultSet nppchon) {
		
		this.nppchon = nppchon;
	}
	
	public ResultSet getnppchon() {
		
		return this.nppchon;
	}
	
	public void CreateNpp(String[] chuoi) {
		System.out.println("vao hciuoaiusdjoiasudjoiaj");
		
		String st="";
		if(chuoi!=null)
		{
			for(int i =0;i< chuoi.length;i++)
				st =st + chuoi[i]+",";
			st =st.substring(0,st.length()-1);
			st = st +"";	               
			System.out.println("[DanhSachNppChon]"+st);
		}
		String sql;
		 sql=
		 "select v.ten as Vung,kv.ten as KhuVuc,npp.ma as nppMa,npp.Ten as NppTen,npp.pk_seq as nppId "+
		 "from nhaphanphoi npp  "+ 
		 "	left join khuvuc kv  on kv.pk_seq=npp.khuvuc_Fk " +
		 "	left join vung v on v.pk_seq=kv.vung_fk  "+
		 "where npp.trangthai=1	 and npp.pk_seq != 1 and   npp.loainpp in (0,1,2,3,4,5) ";
		 String v="";
		 if(this.vungId.length()>0)
			 v = " and npp.khuvuc_fk in (select pk_seq from khuvuc where vung_fk = ("+ this.vungId +") ";
		 if(this.khuvucId.length()>0)
			 v = " and npp.khuvuc_fk in("+ this.khuvucId +") ";
		if(v.length()>0)
			 sql = sql + v;
		
		if(st.length()>0)
		{
			sql +=" and npp.pk_seq not in ("+st+")  ";
			sql += " union  "+
			"select v.ten as Vung,kv.ten as KhuVuc,npp.ma as nppMa,npp.Ten as NppTen,npp.pk_seq as nppId "+
			 "from nhaphanphoi npp  "+ 
			 "	left join khuvuc kv  on kv.pk_seq=npp.khuvuc_Fk " +
			 "	left join vung v on v.pk_seq=kv.vung_fk  "+
			 "where npp.pk_Seq in ("+st+") and npp.loainpp ";
		}
		sql+="order by kv.ten,v.ten,npp.ma ";
		System.out.println("[SqlNpp]"+sql);
		 this.npp = db.get(sql);
	}
	
	public void setsanpham(ResultSet sanpham) {
		
		this.sanpham = sanpham;
	}
	
	public ResultSet getsanpham() {
		
		return this.sanpham;		
	}
	
	public void setsanphamchon(ResultSet sanphamchon) 
	{
		
		this.sanphamchon = sanphamchon;
	}
	
	public ResultSet getsanphamchon() {
		
		return this.sanphamchon;
	}
	
	public void CreateSanpham(String[] chuoi) {
		
		String st="(";
		if(chuoi!=null)
		{
			for(int i =0;i< chuoi.length;i++)
				st =st + chuoi[i]+",";
			st =st.substring(0,st.length()-1);
			st = st +")";	               		
		}
		String sql;
		String nh ="";
		if(this.nhanhangId.length()>0)
		{
			nh = nh +" and nhanhang_fk ='"+ this.nhanhangId +"'";
		}
		if(this.chungloaiId.length()>0)
		{
			nh = nh + " and chungloai_fk ='"+ this.chungloaiId +"'";
		}
		if(this.Id.length()>0)
	    	{
		     sql =" select * from sanpham where trangthai ='1' and pk_seq not in (select sanpham_fk from nhanvien_sanpham where nhanvien_fk ='"+ this.Id +"')";
			}
		else
			 sql = "select * from sanpham where trangthai ='1' ";
		 if(chuoi !=null)
			sql = "select * from sanpham where trangthai ='1' and pk_seq not in " +st;
		 if(nh.length()>0)
			 sql =sql + nh;
		 this.sanpham = db.get(sql);
		   
		   if(this.Id.length()>0)
		   {
		     sql = "select * from sanpham a ,nhanvien_sanpham b where a.trangthai ='1' and a.pk_seq = b.sanpham_fk and b.nhanvien_fk='"+ this.Id +"'";
		   //  if(nh.length()>0)
			//	 sql =sql + nh;
		     this.sanphamchon = db.get(sql);
		   }
		   if(chuoi !=null)
			 {
			   sql = "select * from sanpham where trangthai ='1' and pk_seq in " +st;
			 //  if(nh.length()>0)
				//	 sql =sql + nh;
			  this.sanphamchon = db.get(sql);
			 }
	}
	
	boolean createUpdate(dbutils db)
	{
		String sql;
		try {
		if(quyenchon !=null)
		{
			while(quyenchon.next())
			{
				sql ="insert into phanquyen(dmq_fk,nhanvien_fk)values('"+ quyenchon.getString("pk_seq")+"','"+ this.Id +"')";
				if(!db.update(sql))
				{	db.update("rollback");
				    this.msg =sql;
					return false;
				}
			}
		}
		else
		{
		//	 this.msg ="Ban phai chon quyen truy cap";
		//	 db.update("rollback");
		//		return false;
		}
		sql="delete from NhanVien_UngDung where NhanVien_fk='"+this.Id+"'" ;
		if (!db.update(sql))
		{
			db.update("rollback");
			this.msg = sql;
			return false;
		}
		
		sql=
		"		insert into NhanVien_UngDung(UngDung_fk,NhanVien_fk)  "+
		"  select distinct nq.UngDung_fk,pq.Nhanvien_fk from NHOMQUYEN nq inner join phanquyen pq on pq.DMQ_fk=nq.DMQ_fk  "+
		"  inner join UNGDUNG ud on ud.PK_SEQ=nq.UngDung_fk  "+
	  " 	where nq.HienThi=1 and ud.TRANGTHAI=1 and pq.Nhanvien_fk ='"+this.Id+"' ";
		if (!db.update(sql))
		{
			db.update("rollback");
			this.msg = sql;
			return false;
		}
	if(this.kenhchon !=null)
	{ while(kenhchon.next())
		{
			sql ="insert into nhanvien_kenh(nhanvien_fk,kenh_fk)values('"+ this.Id +"','"+ this.kenhchon.getString("pk_seq")+"')";
			//System.out.println(sql);
			if(!db.update(sql))
			{	db.update("rollback");
			    this.msg =sql;
				return false;
			}
		}
		}
		else
		{
		
		}
	
		if(this.nppId!=null&& this.nppIds.length()>0)
		{
			sql ="insert into phamvihoatdong(nhanvien_fk,npp_fk) select '"+this.Id+"',pk_seq from nhaphanphoi where pk_Seq in ("+this.nppIds+")  ";
			System.out.println(sql);
			if(!db.update(sql))
			{	db.update("rollback");
			    this.msg =sql;
				return false;
			}
		}
		
		if(this.sanphamchon != null)
		{ 
			while(sanphamchon.next())
			{
				sql = "insert into nhanvien_sanpham(nhanvien_fk,sanpham_fk)values('"+ this.Id+"','"+ sanphamchon.getString("pk_seq")+"')";
				System.out.println(sql);
				if(!db.update(sql))
				{	db.update("rollback");
				    this.msg =sql;
					return false;
				}
			}
		}
		else
			{
				//db.update("rollback");
			//	 this.msg ="Ban phai san pham";
				//	return false;
			}
		if(this.khochon != null)
		{ 
			while(khochon.next())
			{
				sql = "insert into nhanvien_kho(nhanvien_fk,kho_fk) values ('"+ this.Id+"','"+ khochon.getString("pk_seq")+"')";
				System.out.println(sql);
				if(!db.update(sql))
				{	db.update("rollback");
				    this.msg =sql;
					return false;
				}
			}
		}
		else
			{
				//db.update("rollback");
			//	 this.msg ="Ban phai san pham";
				//	return false;
			}
		} catch (Exception e) {
			db.update("rollback");
			return false;
		}
		return true;
	}
	public void setvungId(String vungId) {
		
		this.vungId = vungId;
	}
	
	public String getvungId() {
		
		return this.vungId;
	}
	
	public void setvung(ResultSet vung) {
		
		this.vung = vung;
	}
	
	public ResultSet getvung() {
		
		return this.vung;
	}
	
	public void setkhuvucId(String khuvucId) {
		
		this.khuvucId = khuvucId;
	}
	
	public String getkhuvucId() {
		
		return this.khuvucId;
	}
	
	public void setkhuvuc(ResultSet khuvuc) {
		
		this.khuvuc = khuvuc;
	}
	
	public ResultSet getkhuvuc() {
		
		return this.khuvuc;
	}
	
	void CreateVung()
	{
		String sql = "select * from vung";
		this.vung = db.get(sql);
		
		if(this.vungId.length()>0)
			sql ="select * from khuvuc where vung_fk ='"+ this.vungId+"'";
		else
			sql ="select * from khuvuc";
		this.khuvuc = db.get(sql);
	}

	public void setnhanhangId(String nhanhangId) {
		
		this.nhanhangId = nhanhangId;
	}

	public String getnhanhangId() {
		
		return this.nhanhangId;
	}

	public void setnhanhang(ResultSet nhanhang) {
		
		this.nhanhang = nhanhang;
	}

	public ResultSet getnhanhang() {
		
		return this.nhanhang;
	}

	public void setchungloaiId(String chungloaiId) {
		
		this.chungloaiId = chungloaiId;
	}

	public String getchungloaiId() {
		
		return this.chungloaiId;
	}

	public void setchungloai(ResultSet chungloai) {
		
		this.chungloai = chungloai;
	}

	public ResultSet getchungloai() {
		
		return this.chungloai;
	}
	
	public void CreateNhanhang()
	{
		String sql ="select * from nhanhang";
		this.nhanhang = db.get(sql);
		//if(this.nhanhangId.length()>0)
			sql ="select * from chungloai ";
			this.chungloai = db.get(sql);
	}
	
	public void setchon(String chon) {
		this.chon = chon;
		
	}
	
	public String getchon() {
		
		return this.chon;
	}
	
	public void setnhaphanphoi(ResultSet nhaphanphoi) {
		
		this.nhaphanphoi = nhaphanphoi;
	}
	
	public ResultSet getnhaphanphoi() {
		
		return this.nhaphanphoi;
	}
	
	public void CreateRS() {
		CreateVung();
		CreateNhanhang();				
		String sql;
		
		 sql = "select * from nhaphanphoi where  TRANGTHAI=1 ";
		 String v="";
		 if(this.vungId.length()>0)
			 v = " and khuvuc_fk in (select pk_seq from khuvuc where vung_fk ='"+ this.vungId +"')";
		 if(this.khuvucId.length()>0)
			 v = " and khuvuc_fk ='"+ this.khuvucId +"'";
		if(v.length()>0)
			 sql = sql + v;
		System.out.println("v  " + sql);
		
		this.nhaphanphoi = db.get(sql);
		
		String query = " select PK_SEQ, DienGiai from DANHMUCQUYEN ";
		
		this.DanhmucquyenRs =  db.get(query);
		
		CreateLoaiRs();
		sql="select * from kenhbanhang ";
		this.kenhRs=this.db.get(sql);
		
		
		sql="select * from TinhThanh ";
		this.ttRs=this.db.get(sql);
		
		sql="select * from QuanHuyen ";
		this.qhRs=this.db.get(sql);
		
	}
	
	
	private void CreateLoaiRs() {
		
		String query = "";
		if(this.loai == null) this.loai = "0";
		if(this.loai.equals("1")) 
		{
			query = " SELECT PK_SEQ, TEN FROM BM WHERE TRANGTHAI = 1 ";
		} 
		else if(this.loai.equals("2")) 
		{
			query = " SELECT PK_SEQ, TEN FROM ASM WHERE TRANGTHAI = 1 ";
		} 
		else if(this.loai.equals("3")) 
		{
			query = " SELECT PK_SEQ, TEN FROM GIAMSATBANHANG WHERE TRANGTHAI = 1 ";
		}
		if(query.length() > 0) {
			this.loaiRs = this.db.get(query);
		}
	}
	public void DBClose() {
		
		try{
		if(kenh!=null){
			kenh.close();
		}
		if(kenh!=null){
			kenh.close();
		}
	
		if(npp!=null){
			npp.close();
		}

		if(nppchon!=null){
			nppchon.close();
		}

		if(sanpham!=null){
			sanpham.close();
		}

		if(sanphamchon!=null){
			sanphamchon.close();
		}
	
		

		if(vung!=null){
			vung.close();
		}
	
	

		if(khuvuc!=null){
			khuvuc.close();
		}
		
	
		if(nhanhang!=null){
			nhanhang.close();
		}
		
	
		if(chungloai!=null){
			chungloai.close();
		}
		
		if(nhaphanphoi!=null){
			nhaphanphoi.close();
		}
		
		if(loaiRs != null){
			loaiRs.close();
		}
		
		if(db!=null){
			db.shutDown();
		}
		}catch(Exception er){
			
		}
		finally
		{
			db.shutDown();
		}
		
	}
	
	public String getNppIds()
	{
		return this.nppIds;
	}
	
	public void setNppIds(String nppId)
	{
		this.nppIds=nppId;
	}
	
	public void setLoai(String loai) {
		this.loai = loai;
	}
	
	public String getLoai() {
		return this.loai;
	}
	
	public void setLoaiId(String loaiId) {
		this.loaiId = loaiId;
	}
	
	public String getLoaiId() {
		return this.loaiId;
	}
	
	public void setLoaiRs(ResultSet loaiRs) {
		this.loaiRs = loaiRs;
	}
	
	public ResultSet getLoaiRs() {
		return this.loaiRs;
	}
	
	String kenhId;

	public String getKenhId()
	{
		return kenhId;
	}
	public void setKenhId(String kenhId)
	{
		this.kenhId = kenhId;
	}
	
	
	ResultSet kenhRs;

	public ResultSet getKenhRs()
	{
		return kenhRs;
	}
	public void setKenhRs(ResultSet kenhRs)
	{
		this.kenhRs = kenhRs;
	}
	
	String activeTab;

	public String getActiveTab()
	{
		return activeTab;
	}
	public void setActiveTab(String activeTab)
	{
		this.activeTab = activeTab;
	}
	
	public int KiemTra_TenDangNhap()
	{
		int soDong=0;
		String	query=			
		"	select COUNT(*) as SoDong "+
		"	from NHANVIEN  "+
		"	where DANGNHAP=N'"+this.Tendangnhap+"'   ";
		
		if(this.Id.length() > 0)
			query += " and pk_seq != '" + this.Id + "' ";
		
		System.out.println("[KiemTra]"+query);
		
		ResultSet rs = this.db.get(query);
		try
		{
			while(rs.next())
			{
				soDong=rs.getInt("SoDong");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			soDong=-1;
		}
		return soDong;
	}
	
	public String getSohoadonden() 
	{
		return sohoadonden;
	}
	public void setSohoadonden(String sohoadonden) 
	{
		this.sohoadonden = sohoadonden;
	}
	
	public String getSohoadontu()
	{
		return sohoadontu;
	}
	
	public void setSohoadontu(String sohoadontu) 
	{
		this.sohoadontu = sohoadontu;
	}
	
	public void setdmquyenId(String quyenId) {
		
		this.quyenId = quyenId;
	}
	
	public String getdmquyenId() {
		
		return this.quyenId;
	}
	
	public void setDanhmucquyenRs(ResultSet Rsdanhmucquyen) {
		
		this.DanhmucquyenRs = Rsdanhmucquyen;
	}
	
	public ResultSet getDanhmucquyenRs() {
		
		return this.DanhmucquyenRs;
	}
	String qhId,ttId;
	ResultSet qhRs,ttRs;

	public String getQhId()
  {
  	return qhId;
  }
	public void setQhId(String qhId)
  {
  	this.qhId = qhId;
  }
	public String getTtId()
  {
  	return ttId;
  }
	public void setTtId(String ttId)
  {
  	this.ttId = ttId;
  }
	public ResultSet getQhRs()
  {
  	return qhRs;
  }
	public void setQhRs(ResultSet qhRs)
  {
  	this.qhRs = qhRs;
  }
	public ResultSet getTtRs()
  {
  	return ttRs;
  }
	public void setTtRs(ResultSet ttRs)
  {
  	this.ttRs = ttRs;
  }

  public void closeDB()
  {
		
	    try
      {
	    	if(ttRs!=null)    ttRs.close();
	      if(qhRs!=null)	qhRs.close();
	      if(quyen!=null)quyen.close();
	      if(quyenchon!=null)quyenchon.close();
	      if(vung!=null)vung.close();
	      if(khuvuc!=null)khuvuc.close();
	      if(DanhmucquyenRs!=null)DanhmucquyenRs.close();
	      if(nhanhang!=null)nhanhang.close();
	      if(nhaphanphoi!=null)nhaphanphoi.close();
	      if(kho!=null)kho.close();
	      if(khochon!=null)khochon.close();
	      
      } catch (SQLException e)
      {
	      e.printStackTrace();
      }
  }

	public void setKho(ResultSet khors) {
		
		this.kho=khors;
	}

	public ResultSet getKhoRs() {
		
		return this.kho;
	}

	public void setKhochon(ResultSet khochonrs) {
		
		this.khochon=khochonrs;
	}

	public ResultSet getKhochonrs() {
		
		return this.khochon;
	}
	public void CreateKho(String[] chuoi) {
		
		Utility util = new Utility(); 
		String st="";
		if(chuoi!=null)
		{
			for(int i =0;i< chuoi.length;i++)
				st =st + chuoi[i]+",";
			st =st.substring(0,st.length()-1);
			st = st +"";	               
			System.out.println("[DanhSachKhoChon]"+st);
		}
		String sql="";		
		
		if(this.Id.length()>0)	
			sql =" select * from kho where trangthai ='1' and pk_seq not in (select kho_fk from nhanvien_kho where nhanvien_fk ='"+ this.Id +"')";
		else
			sql = "select * from kho where trangthai ='1' and pk_seq  in (select kho_fk from nhanvien_kho where nhanvien_fk ='"+ this.userId +"')";
		if(chuoi !=null)
			sql = "select * from kho where trangthai ='1' and pk_seq not in "+ util.quyen_kho(this.Id);
		this.kho = db.get(sql);
	   
	   if(this.Id.length()>0)
	   {
	     sql = "select * from kho a ,nhanvien_kho b where a.trangthai ='1' and a.pk_seq = b.kho_fk and b.nhanvien_fk='"+ this.Id +"'";
	     this.khochon = db.get(sql);
	   }
	   if(chuoi !=null)
	   {
		   sql = "select * from kho where trangthai ='1' and pk_seq in ( " +st+" )";
		  this.khochon = db.get(sql);
	   }
	}
	
}
