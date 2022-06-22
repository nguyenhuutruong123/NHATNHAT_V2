package geso.dms.center.beans.kehoachnhanvien.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import geso.dms.center.beans.kehoachnhanvien.IKeHoachNhanVienList;
import geso.dms.center.db.sql.dbutils;

public class KeHoachNhanVienList implements IKeHoachNhanVienList
{
	private static final long serialVersionUID = -9217977546733610214L;
	
	// Tieu chi tim kiem
	String userId;
	String nhanvienId = "";
	String nhanvienTen = "";
	String loai = "";
	String thang;
	String nam;
	String Msg;
	ResultSet khnvRs; 
	ResultSet vungRs;
	ResultSet khuvucRs;
	String vungId;
	String khuvucId;
	dbutils db;

	private String Loai;
	
	public KeHoachNhanVienList(String[] param)
	{
		this.db = new dbutils();
		this.nhanvienTen = param[0];
		this.thang = param[1];
		this.nam = param[2];
		this.userId = "";
		this.vungId = "";
		this.khuvucId = "";
	}
	
	public KeHoachNhanVienList()
	{
		this.db = new dbutils();
		this.userId = "";
		this.nhanvienTen = "";
		this.thang = "";
		this.nam = "";
		this.Msg ="";
		this.vungId = "";
		this.khuvucId = "";
	}
	
	public KeHoachNhanVienList(boolean abc)
	{
		this.db = new dbutils();
		this.userId = "";
		this.nhanvienTen = "";
		this.thang = "";
		this.nam = "";
		this.Msg ="";
		this.vungId = "";
		this.khuvucId = "";
	}
	
	public ResultSet getKhnvRs() 
	{
		return this.khnvRs;
	}

	public void setKhnvRs(ResultSet khnvRs)
	{
		this.khnvRs = khnvRs;
	}
	
	public String getUserId() 
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
	}
	
	public String getTenNhanVien() 
	{
		return this.nhanvienTen;
	}

	public void setTenNhanVien(String nhanvienTen) 
	{
		this.nhanvienTen = nhanvienTen;
	}

	public String getLoai() 
	{
		return this.loai;
	}

	public void setLoai(String loai) 
	{
		this.loai = loai;
	}

	public String getThang() 
	{
		return this.thang;
	}

	public void setThang(String thang) 
	{
		this.thang = thang;
	}

	public String getNam() 
	{
		return this.nam;
	}

	public void setNam(String nam) 
	{
		this.nam = nam;
	}
	
	public void init(String search) 
	{
		String query = "";
		query = "select * from VUNG where TRANGTHAI = 1";
		
		this.vungRs = this.db.get(query);
		
		query = "select * from KHUVUC where TRANGTHAI = 1";
		if(this.vungId != null && this.vungId.length() > 0)
			query += " and VUNG_FK = '"+this.vungId+"'";

		this.khuvucRs = this.db.get(query);
		
		if (search == null || search.trim().length() == 0)
		{
			
			query = "\n select case when a.tutao = 1 or a.loai not in (1,2,3)  then 1 else 0 end allowChot ,* from " +
			"\n ( " +
			"\n     select  0 tutao ,isnull(nv.loai,0)loai,a.nhanvien_fk,a.pk_seq, nv.ten as nhanvien, a.thang, a.nam, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, isnull(a.trangthai, 0) as trangthai, case when nv.loai not in (1,2,3) then 0 else 1 end  as loaikehoach " + 
			"\n     from kehoachnv a " + 
			"\n     inner join nhanvien nv on nv.pk_seq = a.nhanvien_fk " + 
			"\n     inner join nhanvien b on a.nguoitao = b.pk_seq " + 
			"\n     inner join nhanvien c on a.nguoisua = c.pk_seq " +
			"\n     where a.nhanvien_fk = " + this.userId + 
			"\n     union " +
			"\n     select  1 tutao ,isnull(nv.loai,0)loai,a.nhanvien_fk,a.pk_seq, nv.ten as nhanvien, a.thang, a.nam, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, isnull(a.trangthai, 0) as trangthai, '0' as loaikehoach " +
			"\n     from kehoachnv a " + 
			"\n     inner join nhanvien nv on nv.pk_seq = a.nhanvien_fk " + 
			"\n     inner join nhanvien b on a.nguoitao = b.pk_seq " + 
			"\n     inner join nhanvien c on a.nguoisua = c.pk_seq " +
			"\n     where a.nhanvien_fk !=" + this.userId + "   "+(getIdNhanVienCapDuoiQuery().equals("null")?"":"and a.nhanvien_fk in ( "+ getIdNhanVienCapDuoiQuery()+" )")+
			"\n )a " +
			"\n order by loaikehoach, a.trangthai, a.nam desc, a.thang desc ";
		
		}
		
		else
		{
			query = search;
		}
		System.out.println("[KeHoachNhanVienList.init] query = " + query);
		
		
		khnvRs = this.db.get(query);  
	}
	
	/**
	 * Lấy câu sql trả về các pk_seq nhân viên cấp dưới của nhân viên này
	 * @return String result
	 */
	public static String getIdNhanVienCapDuoiQuery(dbutils db, String userId) {
		String query = "\n SELECT LOAI, isnull(isadmin,'') isadmin, phanloai " +
		"\n FROM NHANVIEN WHERE PK_SEQ = " + userId;
		System.out.println("[KeHoachNhanVienList.init] query = " + query);
		ResultSet rs = db.get(query);
		String loai = "";
		String isadmin  = "";
		String phanloai = "";
		try 
		{
			rs.next();
			loai = rs.getString("LOAI") == null ? "" : rs.getString("LOAI");
			isadmin = rs.getString("isadmin") == null ? rs.getString("isadmin") : rs.getString("isadmin");
			phanloai = rs.getString("phanloai") == null ? rs.getString("phanloai") : rs.getString("phanloai");
			rs.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if ( phanloai.equals("2") && !loai.equals("1")&& !loai.equals("2")&& !loai.equals("3") ) //Admin HO
			isadmin = "1";
		
		query = "null";		
		if (isadmin.equals("1") || userId.equals("100002"))
		{
			query = " SELECT PK_SEQ FROM NHANVIEN  ";
		}
		else if (loai.equals("1")) // BM chỉ lấy của ASM
		{
			query = "\n SELECT PK_SEQ FROM NHANVIEN WHERE LOAI = 2 AND ASM_FK IN ( " +
			"\n 	SELECT DISTINCT(ASM_FK) FROM ASM_KHUVUC Y INNER JOIN ASM Z ON Z.PK_SEQ = Y.ASM_FK WHERE Y.KHUVUC_FK IN ( " +
			"\n 		SELECT PK_SEQ FROM KHUVUC WHERE VUNG_FK IN ( " +
			"\n 			SELECT VUNG_FK FROM BM_CHINHANH A INNER JOIN VUNG B ON B.PK_SEQ = A.VUNG_FK WHERE A.BM_FK IN ( " +
			"\n 				SELECT BM_FK FROM NHANVIEN WHERE PK_SEQ = "+userId+" " +
			"\n 			) AND B.TRANGTHAI = 1 " +
			"\n 		) AND TRANGTHAI = 1 " +
			"\n 	) AND Z.TRANGTHAI = 1 " +
			"\n ) AND TRANGTHAI = 1 " ;

		} 
		else if (loai.equals("3")) // loai GSBH lấy bản thân
		{
			
			query = "\n 0 "; // gs ko laasy cap duoi
		} 		
		else if (loai.equals("2")) 	//ASM: lấy tất cả giám sát bán hàng
		{
		
			query = "\n SELECT PK_SEQ FROM NHANVIEN WHERE LOAI = 3 AND GSBH_FK IN ( " +
			"\n 	SELECT DISTINCT(GSBH_FK) FROM GSBH_KHUVUC Q INNER JOIN GIAMSATBANHANG P ON Q.GSBH_FK = P.PK_SEQ WHERE Q.KHUVUC_FK IN ( " +
			"\n 		SELECT KHUVUC_FK FROM ASM_KHUVUC WHERE ASM_FK IN ( " +
			"\n 			SELECT ASM_FK FROM NHANVIEN WHERE PK_SEQ = "+userId+" " +
			"\n 		)  " +
			"\n 	) AND P.TRANGTHAI = 1 " +
			"\n ) AND TRANGTHAI = 1 ";
		} 
		
		
		return query;
	}
	
	/**
	 * Lấy câu sql trả về các pk_seq nhân viên cấp dưới của nhân viên này
	 * @return String result
	 */
	private String getIdNhanVienCapDuoiQuery() {
		return KeHoachNhanVienList.getIdNhanVienCapDuoiQuery(this.db, this.userId);
	}
	
	public String getSearchQuery() 
	{
		String condition = "";
		if (loai.length() > 0) {
			condition = condition + " and nhanvien_fk in (select pk_seq from nhanvien where loai = " + loai + ") ";
		}
    	if (thang.length() > 0) {
    		condition = condition + " and thang = " + thang;
    	}
    	if (nam.length() > 0) {
    		condition = condition + " and nam = " + nam;
    	}
		if(this.vungId.length() > 0)
			condition += " and v.PK_SEQ = " + this.vungId;
		if(this.khuvucId.length() > 0)
			condition += " and kv.PK_SEQ = " + this.khuvucId;
    	String query;
    	String query2 = getIdNhanVienCapDuoiQuery();
    	
    	query = "\n select case when a.tutao = 1 or a.loai not in (1,2,3)  then 1 else 0 end allowChot ,* from " +
    			"\n ( " +
    			"\n     select  0 tutao ,isnull(nv.loai,0)loai,a.nhanvien_fk,a.pk_seq, nv.ten as nhanvien, a.thang, a.nam, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, isnull(a.trangthai, 0) as trangthai, case when nv.loai not in (1,2,3) then 0 else 1 end  as loaikehoach " + 
    			"\n     from kehoachnv a " + 
    			"\n     inner join nhanvien nv on nv.pk_seq = a.nhanvien_fk " + 
    			"\n     inner join nhanvien b on a.nguoitao = b.pk_seq " + 
    			"\n     inner join nhanvien c on a.nguoisua = c.pk_seq " +
    			"\n     where a.nhanvien_fk = " + this.userId + " " + condition +
    			"\n     union " +
    			"\n     select  1 tutao ,isnull(nv.loai,0)loai,a.nhanvien_fk,a.pk_seq, nv.ten as nhanvien, a.thang, a.nam, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, isnull(a.trangthai, 0) as trangthai, '0' as loaikehoach " +
    			"\n     from kehoachnv a " + 
    			"\n     inner join nhanvien nv on nv.pk_seq = a.nhanvien_fk " + 
    			"\n     inner join nhanvien b on a.nguoitao = b.pk_seq " + 
    			"\n     inner join nhanvien c on a.nguoisua = c.pk_seq " +
    			"\n 	where a.nhanvien_fk !=" + this.userId + " and a.nhanvien_fk in (" + query2 + ") " + condition +
    			"\n )a " +
    			"\n order by loaikehoach, a.trangthai, a.nam desc, a.thang desc ";
    	
		/*query = " select * from (" +
				" 	select 0 tutao, a.pk_seq, nv.ten as nhanvien, a.thang, a.nam, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, isnull(a.trangthai, 0) as trangthai, '1' as loaikehoach " + 
				" 	from kehoachnv a " + 
				" 	inner join " +
				"(	" +
				"	select nv.*, gskv.KHUVUC_FK from NHANVIEN nv " +
				"	left join GIAMSATBANHANG gs on gs.PK_SEQ = nv.GSBH_FK " +
				"	left join GSBH_KHUVUC gskv on gs.PK_SEQ = gskv.GSBH_FK " +
				"	union all " +
				"	select nv.*, asmkv.KHUVUC_FK from NHANVIEN nv " +
				"	left join ASM am on am.PK_SEQ = nv.ASM_FK " +
				"	left join ASM_KHUVUC asmkv on am.PK_SEQ = asmkv.ASM_FK " +
				") nv on nv.pk_seq = a.nhanvien_fk " + 
				" 	inner join nhanvien b on a.nguoitao = b.pk_seq " + 
				" 	inner join nhanvien c on a.nguoisua = c.pk_seq " +
				"	left join KHUVUC kv on kv.PK_SEQ = nv.KHUVUC_FK " +
				"	left join VUNG v on kv.VUNG_FK = v.PK_SEQ " +
				" 	where a.nhanvien_fk = " + this.userId + " " + condition +
				
				" 	union " +
				
				" 	select 1 tutao, a.pk_seq, nv.ten as nhanvien, a.thang, a.nam, a.ngaytao, b.ten as nguoitao, a.ngaysua, c.ten as nguoisua, isnull(a.trangthai, 0) as trangthai, '0' as loaikehoach " +
				" 	from kehoachnv a " + 
				" 	inner join (	" +
				"	select nv.*, gskv.KHUVUC_FK from NHANVIEN nv " +
				"	left join GIAMSATBANHANG gs on gs.PK_SEQ = nv.GSBH_FK " +
				"	left join GSBH_KHUVUC gskv on gs.PK_SEQ = gskv.GSBH_FK " +
				"	union all " +
				"	select nv.*, asmkv.KHUVUC_FK from NHANVIEN nv " +
				"	left join ASM am on am.PK_SEQ = nv.ASM_FK " +
				"	left join ASM_KHUVUC asmkv on am.PK_SEQ = asmkv.ASM_FK " +
				")  nv on nv.pk_seq = a.nhanvien_fk " + 
				" 	inner join nhanvien b on a.nguoitao = b.pk_seq " + 
				" 	inner join nhanvien c on a.nguoisua = c.pk_seq " +
				"	left join KHUVUC kv on kv.PK_SEQ = nv.KHUVUC_FK " +
				"	left join VUNG v on kv.VUNG_FK = v.PK_SEQ "+
				" 	where a.nhanvien_fk in (" + query2 + ") " + condition +
				
				" ) a order by loaikehoach, a.trangthai, a.nam desc, a.thang desc ";*/

    	System.out.println("Tim kiem : "+query);
    	return query;
	}
	
	public void closeDB(){
		try {
			if(khnvRs != null) {
				khnvRs.close();
			}
			if(vungRs != null) {
				vungRs.close();
			}
			if(khuvucRs != null) {
				khuvucRs.close();
			}
		} catch(Exception e) { }
		if(this.db != null)
			this.db.shutDown();
	}


	public void setMsg(String Msg) {
	   this.Msg = Msg;
	}

	
	public String getMsg() {
		return this.Msg;
	}

	@Override
	public String getNhanVienId() {
		return nhanvienId;
	}

	@Override
	public void setNhanVienId(String nhanvienId) {
		this.nhanvienId = nhanvienId;
	}

	@Override
	public boolean delete(String id) 
	{
		String query = " SELECT count(*) as num from KEHOACHNV where pk_seq = " + id + " and nhanvien_fk = " + this.userId + " and trangthai = 1 ";
		System.out.println(query);
		ResultSet rs1 = db.get(query);
		try 
		{
			rs1.next();
			int count = rs1.getInt("num");
			if( count > 0) 
			{
				rs1.close();
				this.Msg = "Không thể xóa kế hoạch: Kế hoạch đã chốt hoặc không phải do bạn tạo!";
				return false;
			} 
			query = "select SUM(num.num) as num from " +
					"( " +
					"select COUNT(*) as num from KEHOACHNV_NPP where KEHOACHNV_FK = " + id + " and THOIDIEMDEN is not null " +
					"union all " +
					"select COUNT(*) as num from KEHOACHNV_THITRUONG where KEHOACHNV_FK = " + id + " " +
						//	+ "and THOIDIEM is not null " +
					"union all " +
					"select COUNT(*) as num from KEHOACHNV_TBH k inner join KEHOACHNV_TBH_KHACHHANG vt on vt.KEHOACHNV_TBH_FK = k.PK_SEQ where KEHOACHNV_FK = " + id + " " +
					") num";
			rs1 = db.get(query);
			rs1.next();
			count = rs1.getInt("num");
			if(count > 0) 
			{
				rs1.close();
				this.Msg = "Không thể xóa kế hoạch: Kế hoạch đã phát sinh dữ liệu viếng thăm";
				return false;
			} 
			else 
			{
				db.getConnection().setAutoCommit(false);
				query = "DELETE KEHOACHNV_NPP WHERE KEHOACHNV_FK = '" + id + "'";
				if(!db.update(query)) {
					db.getConnection().rollback();
					this.Msg = "Không thể xóa kế hoạch nhân viên (" + query + ")";
					return false;
				}
				query = "DELETE KEHOACHNV_THITRUONG WHERE KEHOACHNV_FK = '" + id + "'";
				if(!db.update(query)) {
					db.getConnection().rollback();
					this.Msg = "Không thể xóa kế hoạch nhân viên (" + query + ")";
					return false;
				}
				
				query = "delete from KEHOACHNV_TBH where KEHOACHNV_FK  = '" + id + "'";
				if(!db.update(query)) {
					db.getConnection().rollback();
					this.Msg = "Không thể xóa kế hoạch nhân viên (" + query + ")";
					return false;
				}
				
				query = "delete kehoachnv where pk_seq = '" + id + "'";
				if(!db.update(query)) {
					this.Msg = "Không thể xóa kế hoạch " + id;
					return false;
				}
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
			}
		} 
		catch(Exception e) 
		{
			try 
			{
				db.getConnection().rollback();
			} 
			catch (SQLException e1) {	}
			this.Msg = "Xảy ra lỗi khi xóa kế hoạch " + id + " ("+e.getMessage()+")";
			return false;
		}
		return true;
	}

	@Override
	public boolean duyet(String id) {
		String ngaysua = getDateTime();
		String nguoisua = this.userId;
		
		//Cập nhật trạng thái kế hoạch nhân viên có trạng thái chưa duyệt của cấp dưới nhân viên này 
		String query = " UPDATE KEHOACHNV SET TRANGTHAI = 1, NGUOISUA = '"+nguoisua+"', NGAYSUA = '"+ngaysua+"' WHERE PK_SEQ = " + id + " AND TRANGTHAI = 0 AND NHANVIEN_FK IN (" + this.getIdNhanVienCapDuoiQuery() + ")";
		if(!db.update(query)) {
			this.Msg = "Không thể duyệt kế hoạch " + id;
			return false;
		}
		return true;
	}

	@Override
	public boolean moduyet(String id) {
		String query = "";
		String ngaysua = getDateTime();
		String nguoisua = this.userId;
		
		int thang = Integer.parseInt(getDateTime("MM"));
		int nam = Integer.parseInt(getDateTime("yyyy"));
		
		query = " SELECT THANG, NAM FROM KEHOACHNV WHERE PK_SEQ = " + id + " ";
		ResultSet rs = db.get(query);
		try {
			rs.next();
			int thangKh = rs.getInt("thang");
			int namKh = rs.getInt("nam");
			
			if(namKh < nam || (namKh == nam && thangKh < thang)) {
				this.Msg = "Chỉ được bỏ duyệt những kế hoạch có tháng lớn hơn hoặc bằng tháng hiện tại!";
				return false;
			}
			
		} catch(Exception e) {
			this.Msg = "Xảy ra lỗi khi duyệt kế hoạch " + id + "(" + e.getMessage() + ")";
			return false;
		}
		
		//Cập nhật trạng thái kế hoạch nhân viên có trạng thái chưa duyệt của cấp dưới nhân viên này 
		query = " UPDATE KEHOACHNV SET TRANGTHAI = 0, NGUOISUA = '"+nguoisua+"', NGAYSUA = '"+ngaysua+"' WHERE PK_SEQ = " + id + " AND TRANGTHAI = 1 AND NHANVIEN_FK IN (" + this.getIdNhanVienCapDuoiQuery() + ")";
		if(!db.update(query)) {
			this.Msg = "Không thể duyệt kế hoạch " + id;
			return false;
		}
		return true;
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
      
        return dateFormat.format(date);
	}
	

	
	private String getDateTime(String pattern) 
	{
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
	}

	@Override
	public ResultSet getVungRs() {
		return this.vungRs;
	}

	@Override
	public ResultSet getKhuvucRs() {
		return this.khuvucRs;
	}

	@Override
	public String getVungId() {
		return this.vungId;
	}

	@Override
	public void setVungId(String value) {
		this.vungId = value;
	}

	@Override
	public String getKhuvucId() {
		return this.khuvucId;
	}

	@Override
	public void setKhuvucId(String value) {
		this.khuvucId = value;
	}

}

