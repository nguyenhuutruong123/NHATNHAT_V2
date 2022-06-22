package geso.dms.distributor.beans.phieuchiNPP.imp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import geso.dms.center.util.KeToan;
import geso.dms.center.util.Utility;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.db.sql.dbutils;
import geso.dms.distributor.beans.phieuchiNPP.IErpPhieuChiNPP;


public class ErpPhieuChiNPP implements IErpPhieuChiNPP 
{
	
	int pageX= 0;
	int pageY = 0;
	
	String htttId = "100000"; // fix cứng tiền mặt mới vào
	ResultSet htttRs ;
	
	String view ="";
	String congtyId;
	String Id, NgayButToan, DienGiai,NguoiNopTien="", UserId, Msg;
	String[] Sotien,  dg, PKSEQIds;

	int count = 0;
	String nppId;
	
	dbutils db;
	
	String[] nghiepvuketoanId ,TaiKhoanNo ,TaiKhoanCo ,DoituongNo 
		,DoituongCo ,YeuToNo  ,YeuToCo ;
	ResultSet nghiepvuketoanRs;
	String[] TaiKhoanNoIds, TaiKhoanCoIds,DoiTuongNoIds,DoiTuongCoIds,YeuToNoIds,YeuToCoIds;
	
	Hashtable<String, ResultSet> doituongMap = new Hashtable<String, ResultSet>();
	ResultSet nhomhangRs ;
	
	
	String donvi  ="",diachi= "";
	
	ResultSet donviRs;

	public ErpPhieuChiNPP()
	{
		this.Id = "";
		this.NgayButToan = "";
		this.DienGiai = "";
		this.UserId = "";
		this.Msg = "";
		this.count=0;
		this.nppId = "";
		db = new dbutils();
	}
	public ErpPhieuChiNPP(String Id)
	{
		this.Id = Id;
		this.NgayButToan = "";
		this.DienGiai = "";
		this.UserId = "";
		this.Msg = "";
		this.count=0;
		this.nppId = "";
		db = new dbutils();
	}
	public String getId()
	{

		return this.Id;
	}

	public void setId(String Id)
	{
		this.Id = Id;

	}

	public String getNgayButToan()
	{

		return this.NgayButToan;
	}

	public void setNgayButToan(String NgayButToan)
	{
		this.NgayButToan = NgayButToan;
	}

	public String getDienGiai()
	{

		return this.DienGiai;
	}

	public void setDienGiai(String DienGiai)
	{
		this.DienGiai = DienGiai;
	}

	public String getMsg()
	{

		return this.Msg;
	}

	public void setMsg(String Msg)
	{
		this.Msg = Msg;
	}

	public String getUserId()
	{

		return this.UserId;
	}

	public void setUserId(String UserId)
	{
		this.UserId = UserId;
	}


	public String[] getTaiKhoanNoIds()
	{

		return this.TaiKhoanNoIds;
	}

	public void setTaiKhoanNoIds(String[] TaiKhoanNoIds)
	{
		this.TaiKhoanNoIds = TaiKhoanNoIds;
	}

	public String[] getTaiKhoanCoIds()
	{

		return this.TaiKhoanCoIds;
	}

	public void setTaiKhoanCoIds(String[] TaiKhoanCoIds)
	{
		this.TaiKhoanCoIds = TaiKhoanCoIds;
	}

	public String[] getSotien()
	{

		return this.Sotien;
	}

	public void setSotien(String[] sotien)
	{
		this.Sotien = sotien;
	}


	public String[] getDg()
	{

		return this.dg;
	}

	public void setDg(String[] dg)
	{
		this.dg = dg;
	}

	public ResultSet getNhanvien_NoRs() {
		String query = "SELECT PK_SEQ, MA, TEN FROM ERP_NHANVIEN WHERE  TRANGTHAI=1  AND NPP_FK = " + this.nppId + " " ;  
		return this.db.getScrol(query);
	}
	
	public ResultSet getNhanvien_CoRs() {
		String query = "SELECT PK_SEQ, MA, TEN FROM ERP_NHANVIEN WHERE  TRANGTHAI=1  AND NPP_FK = " + this.nppId + " " ;  
		return this.db.getScrol(query);
	}
	

	public void createRs()
	{
		
		System.out.println("createRs-------------------");
		this.getNppInfo();
		
		
		String tkcocondition = "'1111'";
		if(!htttId.equals("100000"))
			tkcocondition = "'1121'";
			
			
		this.nghiepvuketoanRs = KeToan.getNghiepvuketoanRs(db,this.UserId,this.nppId,"",tkcocondition);
		
		this.doituongMap = KeToan.getNghieVuKeToanHash(db, this.UserId, this.nppId);		
	
		this.nhomhangRs = KeToan.getNhomHangRs(db, UserId, this.nppId);
		
		this.htttRs = db.get(" select pk_seq ,ten from HINHTHUCTHANHTOAN  ");
		
		this.donviRs = db.get( KeToan.getDonVi(db,this.UserId,this.nppId) );
	}

	public boolean Save()
	{
		String query ="";
		this.NgayButToan = Utility.getNgayHienTai();
		String ngaytao=getDateTime();
		if(!this.view.equals("TT"))
			getNppInfo();
		try
		{
			this.db.getConnection().setAutoCommit(false);
			String nppInsert= "1";
			if(this.nppId.trim().length() > 0)
				nppInsert =  this.nppId;
			
			
			String donviTen  = "";
			String donviDiaChi = "";
			
			query = " select top 1 mafast,ten,diachi from ("+KeToan.getDonVi(db,this.UserId,nppInsert)+")kq where mafast = '"+this.donvi+"' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				donviTen = rs.getString("ten");
				donviDiaChi = rs.getString("diachi");
			}
			
			String tenHTTT= "(select TEN from HINHTHUCTHANHTOAN where PK_SEQ = " +this.htttId+ ")";
			query=	"\n INSERT INTO Erp_PhieuChi(donviTen,DiaChi,DonVi,NGAYCHUNGTU,TRANGTHAI,NPP_FK,NguoiNhan,SOTIENTT,NGAYTAO,NGUOITAO,NGAYSUA,NGUOISUA,GhiChu,HINHTHUCTT,noidungtt_fk,HTTT_FK,loaiMenu) " +
					"\n select N'"+donviTen+"',N'"+donviDiaChi+"',N'"+this.donvi+"','"+this.NgayButToan+"',0,'" + nppInsert + "',N'"+this.NguoiNopTien+"',0,'"+ngaytao+"','"+this.UserId+"','"+ngaytao+"','"+this.UserId+"' ,N'"+this.DienGiai+"',"+tenHTTT+",100002,"+this.htttId+",'1' ";
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.Msg = "S1.1 Loi dong lenh sau "+query;
				return false;
			}
			
			query = "select SCOPE_IDENTITY() as btId";
			ResultSet rsBtId = db.get(query);			
			rsBtId.next();
		    this.Id = rsBtId.getString("btId");
		    rsBtId.close();

		    
		    		    
			if( this.nghiepvuketoanId != null & this.nghiepvuketoanId.length > 0)
			{
				for(int i = 0 ;  i < this.nghiepvuketoanId.length ; i ++)
				{
					if(!this.TaiKhoanNoIds[i].startsWith(this.TaiKhoanNo[i]))
					{
						this.db.getConnection().rollback();
						this.Msg = "Tài khoản nợ ("+this.TaiKhoanNoIds[i]+") không phù hợp với mã nghiệp vụ hiện tại";
						return false;
					}
					if(!this.TaiKhoanCoIds[i].startsWith(this.TaiKhoanCo[i]))
					{
						this.Id  = null;
						this.db.getConnection().rollback();
						this.Msg = "Tài khoản có ("+this.TaiKhoanCoIds[i]+") không phù hợp với mã nghiệp vụ hiện tại";
						return false;
					}
					
					if(this.DoituongNo[i].trim().length() > 0 && this.DoiTuongNoIds[i].trim().length() <=0 )
					{
						this.Id  = null;
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn đối tượng nợ ";
						return false;
					}
					if(this.DoituongCo[i].trim().length() > 0 && this.DoiTuongCoIds[i].trim().length() <=0 )
					{
						this.Id  = null;
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn đối tượng có ";
						return false;
					}
					if(this.YeuToNo[i].trim().length() > 0 && this.YeuToNoIds[i].trim().length() <=0 )
					{
						this.Id  = null;
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn yếu tố nợ ";
						return false;
					}
					if(this.YeuToCo[i].trim().length() > 0 && this.YeuToCoIds[i].trim().length() <=0 )
					{
						this.Id  = null;
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn yếu tố có ";
						return false;
					}
					double tien = 0;
					if(this.Sotien[i].trim().length() >0)
						tien = Utility.parseDouble(this.Sotien[i].trim().replace(",",""));
					
					if(tien > 0)
					{
						query = "\n insert Erp_PhieuChi_CHITIET ([phieuchi_fk],[MaNPP],[MaNghiepVu],[Noidung],[tkno],[dtno],[ytno],[tkco],[dtco],[ytco],[tiennt])  " +
								"\n		select "+this.Id+", null,N'"+this.nghiepvuketoanId[i]+"',N'"+this.dg[i]+"',N'"+this.TaiKhoanNoIds[i]+"',N'"+this.DoiTuongNoIds[i]+"',N'"+this.YeuToNoIds[i]+"',N'"+this.TaiKhoanCoIds[i]+"',N'"+this.DoiTuongCoIds[i]+"',N'"+this.YeuToCoIds[i]+"',"+tien;
						if(db.updateReturnInt(query)<= 0)
						{
							this.Id  = null;
							this.db.getConnection().rollback();
							this.Msg = "Khong the cap nhat" + query;
							return false;
						}
					}
					
					
					
				}
				
				query = "\n update Erp_PhieuChi set  SOTIENTT = (select sum (tiennt) from Erp_PhieuChi_CHITIET where phieuchi_fk =  "+ this.Id + ")" +
				"\n						, SOTIENTHU = (select sum (tiennt) from Erp_PhieuChi_CHITIET where phieuchi_fk =  "+ this.Id + ")" +
				"\n where pk_Seq =  "+ this.Id ;
				if(db.updateReturnInt(query)<= 0)
				{
					this.Id  = null;
					this.db.getConnection().rollback();
					this.Msg = "Khong the cap nhat" + query;
					return false;
				}
				
			}else
			{
				this.Id  = null;
				this.db.getConnection().rollback();
				this.Msg="S1.24 Vui long chon nghiep vu ke toan";
				System.out.println(this.Msg);
				return false;
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e)
		{
			this.Id  = null;
			db.update("rollback");
			this.Msg = "S1.25" + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}


	public boolean Edit()
	{
		this.NgayButToan = Utility.getNgayHienTai();
		String ngaytao=getDateTime();
		if(!this.view.equals("TT"))
			getNppInfo();
		try
		{
			this.db.getConnection().setAutoCommit(false);
	
			String nppInsert= "1";
			if(this.nppId.trim().length() > 0)
				nppInsert =  this.nppId;
			
			if(!Utility.KiemTraNgayChungTuSoNet( db ,this.UserId,nppInsert,"PhieuChi_SoChungTu",this.NgayButToan, this.Id,"Erp_PhieuChi" ))
			{
				this.db.getConnection().rollback();
				this.Msg="Không được đổi ngày khác tháng với số chứng từ đã phát sinh!";
				return false;
			}
			String donviTen = "";
			String donviDiaChi = "";
			String	query = " select top 1 mafast,ten,diachi from ("+KeToan.getDonVi(db,this.UserId,nppInsert)+")kq where mafast = '"+this.donvi+"' ";
			ResultSet rs = db.get(query);
			if(rs.next())
			{
				donviTen = rs.getString("ten");
				donviDiaChi = rs.getString("diachi");
			}
			String tenHTTT= "(select TEN from HINHTHUCTHANHTOAN where PK_SEQ = " +this.htttId+ ")";
			
			 query=	"\n update Erp_PhieuChi set donviTen = N'"+donviTen+"' , diachi = N'"+donviDiaChi+"' ,DonVi = N'"+this.donvi+"' ,  NGAYCHUNGTU = '"+this.NgayButToan+"'" +
							"\n		,NguoiNhan = N'"+this.NguoiNopTien+"',NGAYSUA = '"+ngaytao+"',NGUOISUA = '"+this.UserId+"'" +
							"\n		,GhiChu = N'"+this.DienGiai+"',HINHTHUCTT = "+tenHTTT+",HTTT_FK = "+this.htttId+" " +
							"\n where trangthai = 0 and  pk_Seq = "+ this.Id;
			
			System.out.println(query);
			
			if(this.db.updateReturnInt(query)!= 1)
			{
				this.db.getConnection().rollback();
				this.Msg = "S1.1 Loi dong lenh sau "+query;
				return false;
			}
			
			
			
			if(this.nghiepvuketoanId != null & this.nghiepvuketoanId.length > 0)
			{
				
				query = "delete Erp_PhieuChi_CHITIET where phieuchi_fk = "+ this.Id;
				if(!this.db.update(query))
				{
					this.db.getConnection().rollback();
					this.Msg = "S1.1 Loi dong lenh sau "+query;
					return false;
				}
				
				for(int i = 0 ;  i < this.TaiKhoanNoIds.length ; i ++)
				{
					if(!this.TaiKhoanNoIds[i].startsWith(this.TaiKhoanNo[i]))
					{
						this.db.getConnection().rollback();
						this.Msg = "Tài khoản nợ ("+this.TaiKhoanNoIds[i]+") không phù hợp với mã nghiệp vụ hiện tại";
						return false;
					}
					if(!this.TaiKhoanCoIds[i].startsWith(this.TaiKhoanCo[i]))
					{
						this.db.getConnection().rollback();
						this.Msg = "Tài khoản có ("+this.TaiKhoanCoIds[i]+") không phù hợp với mã nghiệp vụ hiện tại";
						return false;
					}
					
					if(this.DoituongNo[i].trim().length() > 0 && this.DoiTuongNoIds[i].trim().length() <=0 )
					{
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn đối tượng nợ ";
						return false;
					}
					if(this.DoituongCo[i].trim().length() > 0 && this.DoiTuongCoIds[i].trim().length() <=0 )
					{
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn đối tượng có ";
						return false;
					}
					if(this.YeuToNo[i].trim().length() > 0 && this.YeuToNoIds[i].trim().length() <=0 )
					{
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn yếu tố nợ ";
						return false;
					}
					if(this.YeuToCo[i].trim().length() > 0 && this.YeuToCoIds[i].trim().length() <=0 )
					{
						this.db.getConnection().rollback();
						this.Msg = "Nghiệp vụ " + this.nghiepvuketoanId[i] + " chưa chọn yếu tố có ";
						return false;
					}
					double tien = 0;
					if(this.Sotien[i].trim().length() >0)
						tien = Utility.parseDouble(this.Sotien[i].trim().replace(",",""));
					if(tien > 0)
					{
						query = "\n insert Erp_PhieuChi_CHITIET ([phieuchi_fk],[MaNPP],[MaNghiepVu],[Noidung],[tkno],[dtno],[ytno],[tkco],[dtco],[ytco],[tiennt])  " +
								"\n		select "+this.Id+", null,N'"+this.nghiepvuketoanId[i]+"',N'"+this.dg[i]+"',N'"+this.TaiKhoanNoIds[i]+"',N'"+this.DoiTuongNoIds[i]+"',N'"+this.YeuToNoIds[i]+"',N'"+this.TaiKhoanCoIds[i]+"',N'"+this.DoiTuongCoIds[i]+"',N'"+this.YeuToCoIds[i]+"',"+tien;
						if(db.updateReturnInt(query)<= 0)
						{
							this.db.getConnection().rollback();
							this.Msg = "Khong the cap nhat" + query;
							return false;
						}
					}
					
					query = "\n update Erp_PhieuChi set SOTIENTT = (select sum (tiennt) from Erp_PhieuChi_CHITIET where phieuchi_fk =  "+ this.Id + ")" +
					"\n						, SOTIENTHU = (select sum (tiennt) from Erp_PhieuChi_CHITIET where phieuchi_fk =  "+ this.Id + ")" +
					"\n where pk_Seq =  "+ this.Id ;
					if(db.updateReturnInt(query)<= 0)
					{
						this.db.getConnection().rollback();
						this.Msg = "Khong the cap nhat" + query;
						return false;
					}
					
				}
				
			}else
			{
				this.db.getConnection().rollback();
				this.Msg="S1.24 Vui long chon nghiep vu ke toan";
				System.out.println(this.Msg);
				return false;
			}
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		} catch (Exception e)
		{
			db.update("rollback");
			this.Msg = "S1.25 " + e.getMessage();
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void Init()
	{
		this.getNppInfo();
		
		String query="";
		ResultSet rs;
		if(this.Id .length() >0){
			  query=" SELECT isnull(donvi,'')donvi, isnull(diachi,'')diachi, nguoinhan ,PK_SEQ,ngaychungtu,GhiChu,HTTT_FK FROM Erp_PhieuChi WHERE PK_SEQ='"+this.Id+"'";
			  rs=this.db.get(query);
			if(rs!=null)
			{
				try
				{
					while(rs.next())
					{
						this.donvi = rs.getString("donvi");
						this.diachi = rs.getString("diachi");
						this.NguoiNopTien=rs.getString("nguoinhan");
						this.DienGiai=rs.getString("GhiChu");
						this.NgayButToan=rs.getString("ngaychungtu");
						this.htttId =rs.getString("HTTT_FK");
						
					}rs.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
					System.out.println("Init But toan Exception "+query);
				}
			}
		}
		this.count=1;
		if(this.Id.length() >0){
			try
			{
				query =" select count(*) a from Erp_PhieuChi_CHITIET where phieuchi_fk ='"+this.Id+"'";
				rs =this.db.get(query);
				rs.next();
				this.count = rs.getInt("a");
				String[] nghiepvuketoanTmp = new String[this.count];
				this.dg = new String[this.count];
				this.Sotien = new String[this.count];
				this.DoiTuongNoIds = new String[this.count];
				this.DoiTuongCoIds = new String[this.count];
				this.YeuToNoIds = new String[this.count];
				this.YeuToCoIds = new String[this.count];
				this.TaiKhoanNoIds = new String[this.count];
				this.TaiKhoanCoIds = new String[this.count];
				
				query=	" select MaNghiepVu,tkno,tkco,Noidung,dtno,ytno,dtco,ytco,tiennt from Erp_PhieuChi_CHITIET where phieuchi_fk ='"+this.Id+"' order by id ";
				
				rs =this.db.get(query);
				int dem = 0;
				while(rs.next())
				{
					nghiepvuketoanTmp[dem] = rs.getString("MaNghiepVu");
					this.dg[dem] = rs.getString("Noidung");
					this.Sotien[dem] = rs.getString("tiennt");
					this.DoiTuongNoIds[dem] = rs.getString("dtno");
					this.DoiTuongCoIds[dem] = rs.getString("dtco");
					this.YeuToNoIds[dem] = rs.getString("ytno");
					this.YeuToCoIds[dem] = rs.getString("ytco");
					this.TaiKhoanNoIds[dem] = rs.getString("tkno");
					this.TaiKhoanCoIds[dem] = rs.getString("tkco");
					dem ++;
					
				}rs.close();
				this.setNghiepvuketoanId(nghiepvuketoanTmp);
			} catch (SQLException e)
			{
				e.printStackTrace();
				System.out.println("Init But toan Exception "+e.getMessage());
			}
		}
	

		
		
		createRs();
	}
	
	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	public int getCount()
	{
		return this.count;
	}
	
	public void setCount(int count)
	{
		this.count=count;	
	}
	
	
	public String Chot()
	{
		this.NgayButToan = Utility.getNgayHienTai();
		if(this.UserId == null || this.UserId.equals("") || this.UserId.equals("null") )
			return "Vui lòng đăng nhập lại";
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "UPDATE Erp_PhieuChi SET ngaychungtu ='"+this.NgayButToan+"', TRANGTHAI =1,NGUOISUA='"+this.UserId+"' WHERE PK_SEQ='"+this.Id+"'";
			if(!this.db.update(query))
			{
				db.getConnection().rollback();
				return "Không thể chốt bút toán này " + query;
			}
			
			
		
			db.getConnection().commit();
			db.shutDown();
		}
		catch(Exception er)
		{
			db.update("rollback");
			er.printStackTrace();
			return "Lỗi chốt bút toán: " + er.getMessage();
			
		}
		
		return "";
		
	}
	
	public String Delete()
	{
		
		if(this.UserId==null ||this.UserId.equals("")|| this.UserId.equals("null") )
			return "Vui lòng đăng nhập lại";
		String query="UPDATE Erp_PhieuChi SET TRANGTHAI =2,NGUOISUA='"+this.UserId+"' WHERE PK_SEQ='"+this.Id+"'";
		if(!this.db.update(query)) {
			return "Không thể xóa bút toán này "+query;
		}
		return "";
		
	}

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
	}
	
	
	
	public void DBClose(){
		try{
			
			if(this.doituongMap != null) doituongMap.clear();
			if(htttRs != null) htttRs.close();
			db.shutDown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	
	private void getNppInfo()
	{				
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.UserId);
	}
	
	public String getnppId()
	{
		return this.nppId;
	}

	public void setnppId(String nppId) 
	{
		this.nppId = nppId;
	}
	
	public String[] getPKSEQIds() {
		
		return this.PKSEQIds;
	}
	
	public void setPKSEQIds(String[] PKSEQIds) {
		
		this.PKSEQIds = PKSEQIds;
	}
	
	public String[] getNghiepvuketoanId() {
		return nghiepvuketoanId;
	}
	public void setNghiepvuketoanRs(ResultSet nghiepvuketoanRs) {
		this.nghiepvuketoanRs = nghiepvuketoanRs;
	}
	public ResultSet getNghiepvuketoanRs() {
		return nghiepvuketoanRs;
	}
	public String[] getYeuToCo() {
		return YeuToCo;
	}
	public String[] getYeuToNo() {
		return YeuToNo;
	}
	public String[] getDoituongNo() {
		return DoituongNo;
	}
	public String[] getDoituongCo() {
		return DoituongCo;
	}
	public void setNghiepvuketoanId(String[] nghiepvuketoanIds) {
		this.nghiepvuketoanId = nghiepvuketoanIds;
		if(this.nghiepvuketoanId != null)
		{
			this.TaiKhoanNo = new String[this.nghiepvuketoanId.length];
			this.TaiKhoanCo = new String[this.nghiepvuketoanId.length];
			
			this.YeuToCo = new String[this.nghiepvuketoanId.length];
			this.YeuToNo = new String[this.nghiepvuketoanId.length];
			
			this.DoituongCo = new String[this.nghiepvuketoanId.length];
			this.DoituongNo = new String[this.nghiepvuketoanId.length];
			/*
			this.DoiTuongCoIds = new String[this.nghiepvuketoanIds.length];
			this.DoiTuongNoIds = new String[this.nghiepvuketoanIds.length];
			this.YeuToCoIds = new String[this.nghiepvuketoanIds.length];
			this.YeuToNoIds = new String[this.nghiepvuketoanIds.length];*/
			
			
			String query = "";
			try
			{
				ResultSet rs;
				if(nghiepvuketoanIds != null)
				for(int i = 0 ;  i < nghiepvuketoanIds.length; i ++)
				{
					query =" select TKNO,TKCO,DTNO,DTCO,YTNO,YTCO from nghiepvuketoan where ma = N'"+nghiepvuketoanIds[i]+"'  ";
					System.out.println("query = "  + query);
					rs = db.get(query);
					if(rs.next())
					{
						String TKNO = rs.getString("TKNO");
						System.out.println("TKNO = "  + TKNO);
						this.TaiKhoanNo[i]= TKNO;
						String TKCO = rs.getString("TKCO");
						this.TaiKhoanCo[i] = TKCO;
						String DTNO = rs.getString("DTNO");
						this.DoituongNo[i] = DTNO; 
						String DTCO = rs.getString("DTCO");
						this.DoituongCo[i] = DTCO;
						String YTNO = rs.getString("YTNO");
						this.YeuToNo[i] = YTNO;
						String YTCO = rs.getString("YTCO");
						this.YeuToCo[i] = YTCO;
					}
					else
					{
						
						this.TaiKhoanNo[i]= "";
						this.TaiKhoanCo[i]= "";
						this.DoituongNo[i]= "";
						this.DoituongCo[i]= "";
						this.YeuToNo[i]= "";
						this.YeuToCo[i]= "";
					}
					if(rs!= null)
						rs.close();
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public Hashtable<String, ResultSet> getDoituongMap() {
		return doituongMap;
	}
	public void setDoituongMap(Hashtable<String, ResultSet> doituongMap) {
		this.doituongMap = doituongMap;
	}
	public ResultSet getNhomhangRs() {
		return nhomhangRs;
	}
	public void setNhomhangRs(ResultSet nhomhangRs) {
		this.nhomhangRs = nhomhangRs;
	}
	
	public String[] getDoiTuongCoIds() {
		return DoiTuongCoIds;
	}
	public String[] getDoiTuongNoIds() {
		return DoiTuongNoIds;
	}
	public void setDoiTuongCoIds(String[] doiTuongCoIds) {
		DoiTuongCoIds = doiTuongCoIds;
	}
	public void setDoiTuongNoIds(String[] doiTuongNoIds) {
		DoiTuongNoIds = doiTuongNoIds;
	}
	public String[] getYeuToCoIds() {
		return YeuToCoIds;
	}
	public String[] getYeuToNoIds() {
		return YeuToNoIds;
	}
	public void setYeuToCoIds(String[] yeuToCoIds) {
		YeuToCoIds = yeuToCoIds;
	}
	public void setYeuToNoIds(String[] yeuToNoIds) {
		YeuToNoIds = yeuToNoIds;
	}
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}	
	
	public void setTaiKhoanNo(String[] taiKhoanNo) {
		TaiKhoanNo = taiKhoanNo;
	}
	public String[] getTaiKhoanNo() {
		return TaiKhoanNo;
	}
	public void setTaiKhoanCo(String[] taiKhoanCo) {
		TaiKhoanCo = taiKhoanCo;
	}
	public String[] getTaiKhoanCo() {
		return TaiKhoanCo;
	}
	public String getNguoiNopTien() {
		return NguoiNopTien;
	}
	public void setNguoiNopTien(String nguoiNopTien) {
		NguoiNopTien = nguoiNopTien;
	}
	public ResultSet getHtttRs() {
		return htttRs;
	}
	
	public String getHtttId() {
		return htttId;
	}
	public void setHtttId(String htttId) {
		this.htttId = htttId;
	}

	public String getDonvi() {
		return donvi;
	}
	public void setDonvi(String donvi) {
		this.donvi = donvi;
	}
	public String getDiachi() {
		return diachi;
	}
	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
	public ResultSet getDonviRs() {
		return donviRs;
	}
	public void setDonviRs(ResultSet donviRs) {
		this.donviRs = donviRs;
	}
	public int getPageX() {
		return pageX;
	}
	public void setPageX(int pageX) {
		this.pageX = pageX;
	}
	public int getPageY() {
		return pageY;
	}
	public void setPageY(int pageY) {
		this.pageY = pageY;
	}
}

