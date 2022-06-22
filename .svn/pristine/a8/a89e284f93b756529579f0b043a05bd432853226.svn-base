package geso.dms.distributor.beans.buttoantonghop.imp;

import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.KeToan;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class ErpButToanTongHop implements IErpButToanTongHop
{
	
	
	int scrollTop = 0;
	
	
	String view ="";
	String congtyId;
	String Id, NgayButToan, DienGiai, UserId, Msg;
	String[] Sotien,  dg, PKSEQIds;

	int count;
	String nppId;
	
	dbutils db;
	
	
	ResultSet nghiepvuketoanRs;
	String[] nghiepvuketoanIds, TaiKhoanNoIds, TaiKhoanCoIds,DoituongNo,DoiTuongNoIds, DoituongCo,DoiTuongCoIds,YeuToNo,YeuToNoIds,YeuToCo,YeuToCoIds;
	
	Hashtable<String, ResultSet> doituongMap = new Hashtable<String, ResultSet>();
	ResultSet nhomhangRs ;

	public ErpButToanTongHop()
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
	public ErpButToanTongHop(String Id)
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
		this.getNppInfo();
		
		this.nghiepvuketoanRs = KeToan.getNghiepvuketoanRs(db,this.UserId,this.nppId,"","");
		
		this.doituongMap = KeToan.getNghieVuKeToanHash(db, this.UserId, this.nppId);		
	
		this.nhomhangRs = KeToan.getNhomHangRs(db, UserId, this.nppId);
		
	}

	public boolean Save()
	{
		
		String ngaytao=getDateTime();
		if(!this.view.equals("TT"))
			getNppInfo();
		try
		{
			this.db.getConnection().setAutoCommit(false);
			String nppInsert= "1";
			if(this.nppId.trim().length() > 0)
				nppInsert =  this.nppId;
			String query=" INSERT INTO ERP_BUTTOANTONGHOP(NGAYBUTTOAN,DIENGIAI,NGUOITAO,NGAYTAO,NGUOISUA,NGAYSUA, NPP_FK,TRANGTHAI) VALUES" +
					"('"+this.NgayButToan+"',N'"+this.DienGiai+"','"+this.UserId+"','"+ngaytao+"','"+this.UserId+"','"+ngaytao+"', '" + nppInsert + "','0') ";
			
			System.out.println(query);
			
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

			
			if(this.nghiepvuketoanIds != null & this.nghiepvuketoanIds != null)
			{
				for(int i = 0 ; i < this.nghiepvuketoanIds.length ; i++)
				{
					//ERP_BUTTOANTONGHOP_CHITIET
					//query = "\n insert ERP_BUTTOANTONGHOP_CHITIET(MaNPP,MaNghiepVu,Noidung,tkno,dtno,ytno,tkco,dtco,)" +
					query =	"\n select TKNO,TKCO,DTNO,DTCO,YTNO,YTCO from nghiepvuketoan where ma = N'"+this.nghiepvuketoanIds[i]+"'    ";
					ResultSet rs = db.get(query);
					if(rs.next())
					{
						String TKNO = rs.getString("TKNO");
						String TKCO = rs.getString("TKCO");
						String DTNO = rs.getString("DTNO"); // co yeu cau doi tuong nowj thi gang tren giao dien xuong
						if(DTNO.trim().length() > 0 )
						{
							
							if(DTNO.equals(this.DoituongNo[i]))
								DTNO = this.DoiTuongNoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co doi tuong no la " +  this.DoituongNo[i];
								return false;
							}
						}
						String DTCO = rs.getString("DTCO");
						if(DTCO.trim().length() > 0  )
						{
							if(DTCO.equals(this.DoituongCo[i]))
								DTCO = this.DoiTuongCoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co doi tuong có la " +  this.DoituongCo[i];
								return false;
							}
						}
						String YTNO = rs.getString("YTNO");
						if(YTNO.trim().length() > 0  )
						{
							if(YTNO.equals(this.YeuToNo[i]))
								YTNO = this.YeuToNoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co  YTNO la " +  this.YeuToNo[i];
								return false;
							}
						}
						String YTCO = rs.getString("YTCO");
						if(YTCO.trim().length() > 0 )
						{
							if(YTCO.equals(this.YeuToCo[i]))
								YTCO = this.YeuToCoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co  YTCO la " +  this.YeuToCo[i];
								return false;
							}	
						}
						double tien = 0;
						if(this.Sotien[i].trim().length() >0)
						{
							tien = Utility.parseDouble(this.Sotien[i].trim().replace(",",""));
						}
						if(tien > 0)
						{
							query = "\n insert ERP_BUTTOANTONGHOP_CHITIET(BUTTOANTONGHOP_FK,MaNPP,MaNghiepVu,Noidung,tkno,dtno,ytno,tkco,dtco,ytco,tiennt) "+
									"\n select  "+ this.Id +",mafast,N'"+this.nghiepvuketoanIds[i] +"',N'"+this.dg[i]+"','"+TKNO+"','"+DTNO+"','"+YTNO+"'" +
									"\n 	,'"+TKCO+"','"+DTCO+"','"+YTCO+"', "+tien+"   from nhaphanphoi where pk_seq =  " + nppInsert;
							if(db.updateReturnInt(query)<= 0)
							{
								this.db.getConnection().rollback();
								this.Msg = "Khong the cap nhat" + query;
								return false;
							}
						}
						else
						{
							this.db.getConnection().rollback();
							this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " noi dung ["+dg[i]+"] chua nhap tien ";
							return false;
						}
					}
				}
				
			}else
			{
				this.db.getConnection().rollback();
				this.Msg="S1.24 Vui long chon nghiep vu ke toan";
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


	public boolean Edit()
	{
		String ngaysua=getDateTime();
		if(!this.view.equals("TT"))
			getNppInfo();
		try
		{
			String nppInsert= "1";
			if(this.nppId.trim().length() > 0)
				nppInsert =  this.nppId;
			
			this.db.getConnection().setAutoCommit(false);

			String query="UPDATE  ERP_BUTTOANTONGHOP SET NGAYBUTTOAN='"+this.NgayButToan+"',DIENGIAI=N'"+this.DienGiai+"',NGUOISUA='"+this.UserId+"',NGAYSUA='"+ngaysua+"' WHERE PK_SEQ='"+this.Id+"'" ;
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.Msg="E1.1 Loi dong lenh sau "+query;
				return false;
			}
			query="DELETE FROM ERP_BUTTOANTONGHOP_CHITIET WHERE BUTTOANTONGHOP_FK='"+this.Id+"'";
			if(!this.db.update(query))
			{
				this.db.getConnection().rollback();
				this.Msg="E1.2 Loi dong lenh sau "+query;
				return false;
			}
			if(this.nghiepvuketoanIds != null & this.nghiepvuketoanIds != null)
			{
				for(int i = 0 ; i < this.nghiepvuketoanIds.length ; i++)
				{
					//ERP_BUTTOANTONGHOP_CHITIET
					//query = "\n insert ERP_BUTTOANTONGHOP_CHITIET(MaNPP,MaNghiepVu,Noidung,tkno,dtno,ytno,tkco,dtco,)" +
					query =	"\n select TKNO,TKCO,DTNO,DTCO,YTNO,YTCO from nghiepvuketoan where ma = N'"+this.nghiepvuketoanIds[i]+"'    ";
					ResultSet rs = db.get(query);
					if(rs.next())
					{
						String TKNO = rs.getString("TKNO");
						String TKCO = rs.getString("TKCO");
						String DTNO = rs.getString("DTNO"); // co yeu cau doi tuong nowj thi gang tren giao dien xuong
						if(DTNO.trim().length() > 0 )
						{
							
							if(DTNO.equals(this.DoituongNo[i]))
								DTNO = this.DoiTuongNoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co doi tuong no la " +  this.DoituongNo[i];
								return false;
							}
						}
						String DTCO = rs.getString("DTCO");
						if(DTCO.trim().length() > 0  )
						{
							if(DTCO.equals(this.DoituongCo[i]))
								DTCO = this.DoiTuongCoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co doi tuong có la " +  this.DoituongCo[i];
								return false;
							}
						}
						String YTNO = rs.getString("YTNO");
						if(YTNO.trim().length() > 0  )
						{
							if(YTNO.equals(this.YeuToNo[i]))
								YTNO = this.YeuToNoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co  YTNO la " +  this.YeuToNo[i];
								return false;
							}
						}
						String YTCO = rs.getString("YTCO");
						if(YTCO.trim().length() > 0 )
						{
							if(YTCO.equals(this.YeuToCo[i]))
								YTCO = this.YeuToCoIds[i];
							else
							{
								this.db.getConnection().rollback();
								this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " khong co  YTCO la " +  this.YeuToCo[i];
								return false;
							}	
						}
						double tien = 0;
						if(this.Sotien[i].trim().length() >0)
						{
							tien = Utility.parseDouble(this.Sotien[i].trim().replace(",",""));
						}
						if(tien > 0)
						{
							query = "\n insert ERP_BUTTOANTONGHOP_CHITIET(BUTTOANTONGHOP_FK,MaNPP,MaNghiepVu,Noidung,tkno,dtno,ytno,tkco,dtco,ytco,tiennt) "+
									"\n select  "+ this.Id +",mafast,N'"+this.nghiepvuketoanIds[i] +"',N'"+this.dg[i]+"','"+TKNO+"','"+DTNO+"','"+YTNO+"'" +
									"\n 	,'"+TKCO+"','"+DTCO+"','"+YTCO+"', "+tien+"   from nhaphanphoi where pk_seq =  " + nppInsert;
							if(db.updateReturnInt(query)<= 0)
							{
								this.db.getConnection().rollback();
								this.Msg = "Khong the cap nhat" + query;
								return false;
							}
						}
						else
						{
							this.db.getConnection().rollback();
							this.Msg = "nghiep vu " + this.nghiepvuketoanIds[i] + " noi dung ["+dg[i]+"] chua nhap tien ";
							return false;
						}
					}
				}
				
			}else
			{
				this.db.getConnection().rollback();
				this.Msg="S1.24 Vui long chon nghiep vu ke toan";
				return false;
			}
			
			
			this.db.getConnection().commit();
			this.db.getConnection().setAutoCommit(true);
		} catch ( Exception e)
		{
			db.update("rollback");
			this.Msg="E1.29 " + e.getMessage();
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
			  query=" SELECT PK_SEQ,NGAYBUTTOAN,DIENGIAI FROM ERP_BUTTOANTONGHOP WHERE PK_SEQ='"+this.Id+"'";
			  rs=this.db.get(query);
			if(rs!=null)
			{
				try
				{
					while(rs.next())
					{
						this.DienGiai=rs.getString("DienGiai");
						this.NgayButToan=rs.getString("NgayButToan");
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
				query =" select count(*) a from ERP_BUTTOANTONGHOP_CHITIET where BUTTOANTONGHOP_FK ='"+this.Id+"'";
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
				
				query=	"\n select MaNghiepVu,Noidung,dtno,ytno,dtco,ytco,tiennt " +
						"\n from ERP_BUTTOANTONGHOP_CHITIET " +
						"\n where BUTTOANTONGHOP_FK ='"+this.Id+"'" +
						"\n order by id ";
				
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
					dem ++;
					
				}rs.close();
				this.setNghiepvuketoanIds(nghiepvuketoanTmp);
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
		if(this.UserId == null || this.UserId.equals("") || this.UserId.equals("null") )
			return "Vui lòng đăng nhập lại";
		
		try
		{
			db.getConnection().setAutoCommit(false);
			
			String query = "UPDATE ERP_BUTTOANTONGHOP SET TRANGTHAI =1,NGUOISUA='"+this.UserId+"' WHERE PK_SEQ='"+this.Id+"'";
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
		String query="UPDATE ERP_BUTTOANTONGHOP SET TRANGTHAI =2,NGUOISUA='"+this.UserId+"' WHERE PK_SEQ='"+this.Id+"'";
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
			
			if(this.doituongMap != null) doituongMap.clone();
			
			db.shutDown();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public boolean Editsauchot() {
		return false;
		
	}
	
	public static String XOA( dbutils db, String Id, String loaict ) 
	{
		String msg = "";
		
		try
		{
//			Utility util = new Utility();
			
			db.getConnection().setAutoCommit(false);
			
			//CHECK KHOA SO THANG
			String query = "";			
			
			//CÀI KẾ TOÁN
			
			String nam = "";
			String thang = "";
			
			//GHI NHAN NGUOC LAI TAI KHOAN NO - CO
			query = "select SOCHUNGTU, TAIKHOAN_FK, TAIKHOANDOIUNG_FK, NO, CO, TIENTEGOC_FK, TONGGIATRINT, NGAYGHINHAN  " +
				    "from ERP_PHATSINHKETOAN " +
				    "where LOAICHUNGTU = N'"+loaict+"' and SOCHUNGTU = '" + Id + "' ";

			System.out.println(query); // round( ( TIENBVAT - isnull(CHIETKHAU, 0) ) * VAT / 100.0, 0 )
			ResultSet rsPSKT = db.get(query);
			
			try 
			{
				while(rsPSKT.next())
				{
					String taikhoan_fk = rsPSKT.getString("TAIKHOAN_FK");
					String tiente_fk = rsPSKT.getString("TIENTEGOC_FK");
					String ngayghinhan = rsPSKT.getString("NGAYGHINHAN");
					double NO = rsPSKT.getDouble("NO");
					double CO = rsPSKT.getDouble("CO");
					double TONGGIATRINT = rsPSKT.getDouble("TONGGIATRINT");
					
					nam = ngayghinhan.substring(0, 4);
					thang = ngayghinhan.substring(5, 7);
					
					//NEU LA CO THI BAY GIO GHI GIAM CO LAI
					if( NO > 0 )
					{
						query = " update ERP_TAIKHOAN_NOCO set GIATRINOVND = GIATRINOVND - " + NO + ", GIATRINONGUYENTE = GIATRINONGUYENTE - " + TONGGIATRINT + "  " +
								" where TAIKHOANKT_FK = '" + taikhoan_fk + "' and THANG = '" + Integer.parseInt(thang) + "' and NAM = '" + Integer.parseInt(nam) + "' and NGUYENTE_FK = '" + tiente_fk + "' ";
					}
					else
					{
						query = " update ERP_TAIKHOAN_NOCO set GIATRICOVND = GIATRICOVND - " + CO + ", GIATRICONGUYENTE = GIATRICONGUYENTE - " + TONGGIATRINT + "  " +
								" where TAIKHOANKT_FK = '" + taikhoan_fk + "' and THANG = '" + Integer.parseInt(thang) + "' and NAM = '" + Integer.parseInt(nam) + "' and NGUYENTE_FK = '" + tiente_fk + "' ";
					}
					
					System.out.println("1.REVERT NO-CO: " + query);
					
					if(db.updateReturnInt(query)<0)
					{
						msg = "KHÔNG THỂ REVERT KẾ TOÁN. YÊU CẦU LIÊN HỆ LẬP TRÌNH ";
						db.getConnection().rollback();
						return msg;
					}
					
				}
				rsPSKT.close();
				

				//HỦY KẾ TOÁN ĐÃ GHI NHẬN
				query = " DELETE ERP_PHATSINHKETOAN WHERE LOAICHUNGTU = N'"+loaict+"' and SOCHUNGTU = '"+Id+"'";			
				if(!db.update(query))
				{
					msg = "Không thể hủy ERP_PHATSINHKETOAN " + query;
					db.getConnection().rollback();
					return msg;
				}
			} 
			catch (Exception e) { e.printStackTrace();}
			
			
			//LUU LAI THONG TIN
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		}
		catch (Exception e) 
		{
			db.update("rollback");
			e.printStackTrace();
			
			try {
				db.getConnection().setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			return e.getMessage();
		}
		
		try {
			db.getConnection().setAutoCommit(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return "";
		
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
	
	public String[] getNghiepvuketoanIds() {
		return nghiepvuketoanIds;
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
	public void setNghiepvuketoanIds(String[] nghiepvuketoanIds) {
		
		this.nghiepvuketoanIds = nghiepvuketoanIds;
		this.TaiKhoanNoIds = new String[this.nghiepvuketoanIds.length];
		this.TaiKhoanCoIds = new String[this.nghiepvuketoanIds.length];
		
		this.YeuToCo = new String[this.nghiepvuketoanIds.length];
		this.YeuToNo = new String[this.nghiepvuketoanIds.length];
		
		this.DoituongCo = new String[this.nghiepvuketoanIds.length];
		this.DoituongNo = new String[this.nghiepvuketoanIds.length];
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
					this.TaiKhoanNoIds[i]= TKNO;
					String TKCO = rs.getString("TKCO");
					this.TaiKhoanCoIds[i] = TKCO;
					String DTNO = rs.getString("DTNO");
					this.DoituongNo[i] = DTNO; 
					String DTCO = rs.getString("DTCO");
					this.DoituongCo[i] = DTCO;
					String YTNO = rs.getString("YTNO");
					this.YeuToNo[i] = YTNO;
					String YTCO = rs.getString("YTCO");
					this.YeuToCo[i] = YTCO;
				}
				
				if(rs!= null)
					rs.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
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
	public int getScrollTop() {
		return scrollTop;
	}
	public void setScrollTop(int scrollTop) {
		this.scrollTop = scrollTop;
	}
}

