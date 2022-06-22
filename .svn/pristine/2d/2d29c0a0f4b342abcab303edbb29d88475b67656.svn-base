package geso.dms.distributor.beans.tratichluy.imp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.tratichluy.ITratichluy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class Tratichluy implements ITratichluy
{
	String userId;
	
	String id;
	String scheme;
	String ngayduyet;
	String msg;
	
	String nppId;
	String nppTen;
	String sitecode;
	
	ResultSet khRs;
	ResultSet spRs;
	Hashtable<String, String> kh_spTra;
	
	String[] khachhangId;
	String[] khachhangTen;
	String[] soxuat;
	String khIds;
	
	String[] khotraId;
	
	dbutils db;
	
	public Tratichluy()
	{
		this.userId = "";
		this.id = "";
		this.scheme = "";
		this.ngayduyet = "";
		this.msg = "";
		this.khIds = "";
		
		this.kh_spTra = new Hashtable<String, String>();
		this.db = new dbutils();
	}
	
	public Tratichluy(String id)
	{
		this.id = id;
		this.scheme = "";
		this.ngayduyet = "";
		this.msg = "";
		this.khIds = "";
		
		this.kh_spTra = new Hashtable<String, String>();
		this.db = new dbutils();
	}
	
	public String getId() 
	{
		return this.id;
	}

	public void setId(String Id) 
	{
		this.id = Id;
	}
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;	
	}

	public String getMsg() 
	{
		return this.msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getNppId() 
	{
		return this.nppId;
	}

	public void setNppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String getNppTen() 
	{
		return this.nppTen;
	}
	
	public void setNppTen(String nppTen) 
	{
		this.nppTen = nppTen;
	}
	
	public String getSitecode() 
	{
		return this.sitecode;
	}

	public void setSitecode(String sitecode) 
	{
		this.sitecode = sitecode;
	}
		
	private void getNppInfo()
	{		
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();
	}
	
	public void init() 
	{
		this.getNppInfo();
		
		if(this.id.trim().length() > 0)
		{
			//init
			String query = "select NGAYDUYET, b.SCHEME + ', ' + b.DIENGIAI as SCHEME  " +
							"from DUYETTRAKHUYENMAI a inner join TIEUCHITHUONGTL b on a.CTKM_FK = b.PK_SEQ where a.PK_SEQ = '" + this.id + "'";
			ResultSet rs = db.get(query);
			if(rs != null)
			{
				try 
				{
					if(rs.next())
					{
						this.scheme = rs.getString("SCHEME");
						this.ngayduyet = rs.getString("NGAYDUYET");
					}
					rs.close();
				} 
				catch (Exception e) {}
			}
			
			query = "select b.PK_SEQ as khId, b.SMARTID, b.TEN, b.DIACHI, case a.trangthai when 0 then N'Chưa trả' else N'Đã trả' end as trangthai, a.thuong   " +
					"from DuyetTraKhuyenMai_KhachHang a inner join KHACHHANG b on a.khId = b.PK_SEQ  " +
					"where a.duyetkm_fk = '" + this.id + "' and a.nppId = '" + this.nppId + "' and a.donvi = '2' " +
					"order by a.trangthai asc";
			
			System.out.println("__Khoi tao khach hang: " + query);
			this.khRs = db.getScrol(query);
			
			//init SANPHAM
			query = "select b.PK_SEQ as spId, b.MA, b.TEN, ISNULL(a.soluong, 0) as soluong " +
					"from TIEUCHITHUONGTL_SPTRA a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
					"where thuongtl_fk in ( select CTKM_FK from DUYETTRAKHUYENMAI where PK_SEQ = '" + this.id + "' )";
			
			this.spRs = db.getScrol(query);
			
			
			//Init khach hang da tra
			query = "select khachhang_fk, sanpham_fk, soluong  " +
					"from DuyetTraKhuyenMai_SanPham  " +
					"where duyetkhuyenmai_fk = '" + this.id + "' and npp_fk = '" + this.nppId + "'  " +
					"order by khachhang_fk asc";
			System.out.println("___Init khach hang tra: " + query);
			ResultSet rsDuyet = db.get(query);
			if(rsDuyet != null)
			{
				try 
				{
					String khId = "";
					String spTra = "";
					Hashtable<String, String> kh_spTra = new Hashtable<String, String>();
					
					while(rsDuyet.next())
					{
						//if(khId.trim().length() <= 0)
							//khId = rsDuyet.getString("khachhang_fk");
						
						spTra += rsDuyet.getString("sanpham_fk") + "-" + rsDuyet.getString("soluong") + ";";
						
						if(!khId.equals(rsDuyet.getString("khachhang_fk")))
						{
							if(spTra.trim().length() > 0)
							{
								System.out.println("__Khach hang: " + rsDuyet.getString("khachhang_fk") + " -- SP tra: " + spTra );
								kh_spTra.put(rsDuyet.getString("khachhang_fk"), spTra.substring(0, spTra.length() - 1));
							}
							
							spTra = "";
							khId = rsDuyet.getString("khachhang_fk");
						}
					}
					rsDuyet.close();
					
					this.kh_spTra = kh_spTra;
				} 
				catch (Exception e) {
					System.out.println("__Exception init SP: " + e.getMessage());
				}
			}
			
			
		}
	}
	
	public void DbClose() 
	{
		try 
		{
			this.db.shutDown();
		} 
		catch (Exception e) {}
		
	}
	
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

	
	public String getScheme() {
		
		return this.scheme;
	}

	
	public void setScheme(String scheme) {
		
		this.scheme = scheme;
	}

	
	public String getNgayduyet() {
		
		return this.ngayduyet;
	}

	
	public void setNgayduyet(String ngayduyet) {
		
		this.ngayduyet = ngayduyet;
	}

	
	public ResultSet getKhRs() {
		
		return this.khRs;
	}

	
	public void setKhRs(ResultSet khRs) {
		
		this.khRs = khRs;
	}

	
	public boolean updateTratichluy() 
	{
		if(this.nppId == null || this.nppId.length() <= 0 )
		{
			this.getNppInfo();
		}
		
		if(this.khIds.trim().length() <= 0)
		{
			this.msg = "Vui lòng chọn khách hàng muốn duyệt trả";
			return false;
		}
		
		//Check khach hang co chon so luong
		if(this.kh_spTra.size() <= 0 || this.khachhangId == null )
		{
			this.msg = "Không có khách hàng nào được chọn sản phẩm trả";
			return false;
		}
		
		System.out.println("Lay duoc khach hang: " + this.khIds);
		for(int i = 0; i < khachhangId.length; i++ )
		{
			if(this.khIds.contains(khachhangId[i]))
			{
				double tongluongTRa = 0;
				String spTra = kh_spTra.get(khachhangId[i]);
				
				if(spTra != null && spTra.trim().length() > 0)
				{
					String[] spArr = spTra.split(";");
					for(int j = 0; j < spArr.length; j++ )
					{
						String[] sp_Soluong = spArr[j].split("-");
						if(sp_Soluong[1].trim().length() > 0)
							tongluongTRa += Double.parseDouble(sp_Soluong[1]);
					}
				}
				
				System.out.println("Tong luong tra cua KH trung: " + tongluongTRa);
				if(tongluongTRa != Double.parseDouble(this.soxuat[i]))
				{
					this.msg = "Tổng số lượng sản phẩm trả của khách hàng ( " + this.khachhangTen[i] + " ) phải bằng tổng số lượng được nhận";
					return false;
				}
			}
		}
		
		try 
		{
			db.getConnection().setAutoCommit(false);
			
			for(int i = 0; i < khachhangId.length; i++ )
			{
				if(this.khIds.contains(khachhangId[i]))
				{
					String query = "Update DuyetTraKhuyenMai_KhachHang set trangthai = '1', ngaysua = '" + getDateTime() + "', nguoisua = '" + this.userId + "' " +
								   "where duyetkm_fk = '" + this.id + "' and nppId = '" + this.nppId + "' and khId = '" + this.khachhangId[i] + "' and donvi = '2' ";
					System.out.println("___1.Cap nhat KM: " + query);
					if(!db.update(query))
					{
						this.msg = "1.Lỗi khi cập nhật trả khuyến mại: " + query;
						db.getConnection().rollback();
						return false;
					}
					
					//Luu lai san pham tra
					String spTra = kh_spTra.get(khachhangId[i]);
					
					if(spTra != null && spTra.trim().length() > 0)
					{
						String[] spArr = spTra.split(";");
						for(int j = 0; j < spArr.length; j++ )
						{
							String[] sp_Soluong = spArr[j].split("-");
							if(sp_Soluong[1].trim().length() > 0)
							{
								//check ton kho
								query = "select COUNT(*) as sodong from NHAPP_KHO " +
										"where KHO_FK = '100000' and NPP_FK = '" + this.nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' " +
												"and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' ) and AVAILABLE < '"  + sp_Soluong[1] + "' ";
								ResultSet rsCheckKho = db.get(query);
								
								if(rsCheckKho.next())
								{
									if(rsCheckKho.getInt("sodong") > 0 )
									{
										this.msg = "Sản phẩm trả cho khách hàng ( " + this.khachhangTen[i] + " ) không đủ số lượng tồn trong kho hàng bán. Vui lòng kiểm tra lại.";
										db.getConnection().rollback();
										return false;
									}
								}
								
								query = "insert DuyetTraKhuyenMai_SanPham(duyetkhuyenmai_fk, npp_fk, khachhang_fk, thuong, donvi, sanpham_fk, soluong) " +
										"values('" + this.id + "', '" + this.nppId + "', '" + this.khachhangId[i] + "', '" + this.soxuat[i] + "', '2', '" + sp_Soluong[0] + "', '" + sp_Soluong[1] + "')";
								System.out.println("___2.Cap nhat Duyet KM: " + query);
								if(!db.update(query))
								{
									this.msg = "2.Lỗi khi cập nhật trả khuyến mại: " + query;
									db.getConnection().rollback();
									return false;
								}
								
								query = "Update NHAPP_KHO set soluong = soluong - '" + sp_Soluong[1] + "', AVAILABLE = AVAILABLE - '" + sp_Soluong[1] + "' " +
										"where KHO_FK = '100000' and NPP_FK = '" + this.nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' " +
												"and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' )  ";
								if(!db.update(query))
								{
									this.msg = "3.Lỗi khi cập nhật trả khuyến mại: " + query;
									db.getConnection().rollback();
									return false;
								}
								
								//Update KHO_CHI TIET
								long soluong = Math.round(Double.parseDouble(sp_Soluong[1]));
				    			
				    			//Tu dong de xuat BEAN / LO
								query = "select SANPHAM_FK, isnull(AVAILABLE, 0) as AVAILABLE, SOLO " +
										"from NHAPP_KHO_CHITIET  " +
										"where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' )  " +
										"order by ngayhethan asc, AVAILABLE asc";
												
								System.out.println("2.Check soluong san pham: " + query);
												
								ResultSet rsSpDetail = db.get(query);
								if(rsSpDetail != null)
								{
									double tongluong = 0;
									while( rsSpDetail.next() )
									{
										long avaiD = rsSpDetail.getLong("AVAILABLE");
										String soloD = rsSpDetail.getString("SOLO");
										
										if(avaiD > 0)
										{
											tongluong += avaiD;
											if(tongluong <= soluong)
											{
												query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + avaiD + "', AVAILABLE = AVAILABLE - '" + avaiD + "'  " +
														"where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' ) and solo = '" + soloD + "'  ";
												if(!db.update(query))
												{
													db.getConnection().rollback();
										    		msg = "1.Không thể chốt đon hàng: " + query;
													return false;
												}
												
												query = "Update NHAPP_KHO set soluong = soluong - '" + avaiD + "', AVAILABLE = AVAILABLE - '" + avaiD + "'  " +
														"where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' )   ";
												if(!db.update(query))
												{
													db.getConnection().rollback();
										    		msg = "2.Không thể chốt đon hàng: " + query;
													return false;
												}
											}
											else
											{
												double slg = soluong - (tongluong - avaiD);
												
												query = "Update NHAPP_KHO_CHITIET set soluong = soluong - '" + slg + "', AVAILABLE = AVAILABLE - '" + slg + "'  " +
														"where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' ) and solo = ''  ";
												if(!db.update(query))
												{
													db.getConnection().rollback();
										    		msg = "3.Không thể chốt đon hàng: " + query;
													return false;
												}
												
												query = "Update NHAPP_KHO set soluong = soluong - '" + slg + "', AVAILABLE = AVAILABLE - '" + slg + "'  " +
														"where NPP_FK = '" + nppId + "' and SANPHAM_FK = '" + sp_Soluong[0] + "' and KBH_FK = ( select kbh_fk from KhachHang where pk_seq = '" + this.khachhangId[i] + "' )   ";
												if(!db.update(query))
												{
													db.getConnection().rollback();
										    		msg = "4.Không thể chốt đon hàng: " + query;
													return false;
												}
												
												tongluong = tongluong - avaiD + slg;
										
												break;
											}
										}
									}
									
									rsSpDetail.close();
									
									
									if(tongluong != soluong)
									{
										msg = "5.Tồn kho không đủ. Vui lòng kiểm tra lại tồn kho.";
										db.getConnection().rollback();
										return false;
									}
								}
								
							}
						}
					}
					
					
				}
			}
			
			db.getConnection().commit();
			db.getConnection().rollback();
		} 
		catch (Exception e) 
		{
			try 
			{
				this.msg = "Lỗi khi cập nhật KM tích lũy: " + e.getMessage();
				db.getConnection().rollback();
				return false;
			} 
			catch (SQLException e1) { }
		}
		
		return true;
	}

	public ResultSet getSpTraRs() {
		
		return this.spRs;
	}

	public void setSpTraRs(ResultSet spRs) {
		
		this.spRs = spRs;
	}

	public Hashtable<String, String> getKh_SPTra() {
	
		return this.kh_spTra;
	}

	public void setKh_SPTra(Hashtable<String, String> kh_spTra) {
		
		this.kh_spTra = kh_spTra;
	}

	
	public String getSoluong_From_KhachHang(String spTra, String spId) 
	{
		if(spTra == null)
			return "";
		
		//spTra co dang spId1-soluong;spId2-soluong
		String kq = "";
		if(spTra.trim().length() > 0)
		{
			String[] spArr = spTra.split(";");
			if(spArr.length > 0)
			{
				for(int i = 0; i < spArr.length; i++ )
				{
					String[] spArr2 = spArr[i].split("-");
					if(spArr2[0].equals(spId))
					{
						kq = spArr2[1];
						break;
					}
				}
			}
		}
		else
			return "";
		
		return kq;
	}

	
	public String[] getKhachhangId() {
		
		return this.khachhangId;
	}

	
	public void setKhachhangId(String[] khachhangId) {
		
		this.khachhangId = khachhangId;
	}

	
	public String[] getSoxuat() {
		
		return this.soxuat;
	}

	
	public void setSoxuat(String[] soxuat) {
		
		this.soxuat = soxuat;
	}

	
	public String getKhIds() {
		
		return this.khIds;
	}

	
	public void setKhIds(String khIds) {
		
		this.khIds = khIds;
	}

	
	public String[] getKhachhangTen() {
		
		return this.khachhangTen;
	}


	public void setKhachhangTen(String[] khachhangTen) {
		
		this.khachhangTen = khachhangTen;
	}

	public String[] getKhotraId() {
		
		return this.khotraId;
	}

	public void setKhotraId(String[] khoId) {
		
		this.khotraId = khoId;
	}



}
