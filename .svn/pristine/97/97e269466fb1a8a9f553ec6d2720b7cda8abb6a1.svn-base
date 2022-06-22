package geso.dms.distributor.beans.buttoantonghop.imp;

import geso.dms.center.util.Phan_Trang;
import geso.dms.distributor.beans.buttoantonghop.IErpButToanTongHopList;
import geso.dms.distributor.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ErpButToanTongHopList extends Phan_Trang implements IErpButToanTongHopList
{
	private static final long serialVersionUID = 1L;
	String NgayButToan, DienGiai, Msg, UserId, congtyId, DenNgayButToan,SoChungTu, TaiKhoanNo, TaiKhoanCo, SoTien, NguoiTao, trangthai, nppId ;
	ResultSet RsButToan, RsTaiKhoanNo, RsTaiKhoanCo, RsNguoiTao;
	dbutils db;

	String view ="";
	
	
	public ErpButToanTongHopList()
	{
		this.NgayButToan = "";
		this.DenNgayButToan = "";
		this.DienGiai = "";
		this.SoChungTu ="";
		this.TaiKhoanNo ="";
		this.TaiKhoanCo = "";
		this.SoTien = "";
		this.NguoiTao = "";
		this.Msg = "";
		this.trangthai = "";
		this.nppId = "";
		
		db = new dbutils();
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
	
	private String LayDuLieu(String id) {
		
		String laytk = "";
		String query = 
			//GHI NHAN TAI KHOAN KE TOAN
					 "\n select b.PK_SEQ as taikhoan_fk, isnull(a.NO,0) as NO, isnull(a.CO,0) as CO, " +
					 "\n 	  case when a.TTCP_FK is not null then (SELECT isnull(DIENGIAI,'') FROM ERP_TRUNGTAMCHIPHI WHERE PK_SEQ = a.TTCP_FK ) " +
					 "		   when a.KHACHHANG_FK is not null then (SELECT isnull(TEN,'') FROM ERP_KHACHHANG WHERE PK_SEQ = a.KHACHHANG_FK) " +
					 "		   when a.KHO_FK is not null then (SELECT isnull(TEN,'') FROM ERP_KHOTT WHERE PK_SEQ = a.KHO_FK) " +
					 "		   when a.NCC_FK is not null then (SELECT isnull(TEN,'') FROM ERP_NHACUNGCAP WHERE PK_SEQ = a.NCC_FK)" +
					 "		   when a.NGANHANG_FK is not null then (SELECT isnull(TEN,'') FROM ERP_NGANHANG WHERE PK_SEQ = a.NGANHANG_FK) " +
					 "		   when a.NHANVIEN_FK is not null then (SELECT isnull(TEN,'') FROM ERP_NHANVIEN WHERE PK_SEQ = a.NHANVIEN_FK) " +
					 "		   when a.TAISAN_FK is not null then  (SELECT isnull(TEN,'') FROM ERP_TAISAN WHERE PK_SEQ = a.TAISAN_FK)  else '--' " +
					 "	  end as doituong "+
					 "from ERP_BUTTOANTONGHOP_CHITIET a inner join ERP_TAIKHOANKT b on a.TAIKHOANKT_FK = b.PK_SEQ " +
					 "where a.BUTTOANTONGHOP_FK = '" + id + "' "+
					 "order by a.STT ";
		System.out.println("___INIT TAI KHOAN: " + query);
		ResultSet rsTK = db.get(query);
		
		String taikhoanFK = "";
		String giatriNO = "";
		String giatriCO = "";
		
		String doituong = "";
		//String madoituong = "";
		
		int i = 1;
		try{
			if(rsTK != null)
			{
				while(rsTK.next())
				{
					taikhoanFK += rsTK.getString("taikhoan_fk") + "__";
					giatriNO += rsTK.getString("NO") + "__";
					giatriCO += rsTK.getString("CO") + "__";
					
					doituong += rsTK.getString("doituong") + "__";
					//madoituong += rsTK.getString("madoituong") + "__";
				}
				rsTK.close();
				
				if(taikhoanFK.trim().length() > 0)
				{
					taikhoanFK = taikhoanFK.substring(0, taikhoanFK.length() - 2);
					giatriNO = giatriNO.substring(0, giatriNO.length() - 2);
					giatriCO = giatriCO.substring(0, giatriCO.length() - 2);
					
					doituong = doituong.substring(0, doituong.length() - 2);
					//madoituong = madoituong.substring(0, madoituong.length() - 2);
					
					String[] _taikhoan = taikhoanFK.split("__");
					String[] _giatriNO = giatriNO.split("__");
					String[] _giatriCO = giatriCO.split("__");
					
					String[] _doituong = doituong.split("__");
					//String[] _madoituong = madoituong.split("__");
					
					int pos = 0;
					
					while(pos < _taikhoan.length)
					{
						/*String taikhoanCO_SoHieu = _taikhoan[pos+ 1];
						String taikhoanNO_SoHieu = _taikhoan[pos];*/

						String taikhoanCO_SoHieu = "";
						String taikhoanNO_SoHieu = "";
						
//						String tiente_fk = "100000";
						
						String doituongNO = "";
						String doituongCO = "";
						
						/*String madoituongNO = "";						
						String madoituongCO = "";*/
						
						double tonggiatri = 0;
						
						if((Double.parseDouble(_giatriCO[pos]) - Double.parseDouble(_giatriNO[pos+1]) != 0) || (Double.parseDouble(_giatriNO[pos]) - Double.parseDouble(_giatriCO[pos+1]) != 0) )
						{
							laytk = "";
						}
						else
						{
							if(Double.parseDouble(_giatriCO[pos]) > 0)
							{
								taikhoanCO_SoHieu = _taikhoan[pos];
								taikhoanNO_SoHieu = _taikhoan[pos+1];
								
								tonggiatri = Double.parseDouble(_giatriCO[pos]);
																
								doituongCO = _doituong[pos];
								doituongNO = _doituong[pos + 1];
								
								/*madoituongCO = _madoituong[pos];								
								madoituongNO = _madoituong[pos + 1];*/
								
/*								if(madoituongNO.equals("--")) madoituongNO= "";
								
								if(madoituongCO.equals("--")) madoituongCO= "";*/
								
								if(doituongNO.equals("--")) doituongNO= "";
								
								if(doituongCO.equals("--")) doituongCO= "";
								
								if(laytk.trim().length()>0) laytk += "UNION ALL \n";
								
								laytk += 
									"SELECT N'NỢ' NO_CO, "+id+" PK_SEQ, "+tonggiatri+" SOTIEN, (SELECT SOHIEUTAIKHOAN FROM ERP_TAIKHOANKT WHERE PK_SEQ ="+taikhoanNO_SoHieu+") SOHIEUTAIKHOAN,N'"+doituongNO+"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 1 SAPXEP \n " +
									"FROM ERP_BUTTOANTONGHOP \n " +
									"WHERE PK_SEQ = '"+id+"'" +
									
									"UNION ALL \n"+
									
									"SELECT 'CÓ' NO_CO, "+id+" PK_SEQ, "+tonggiatri+" SOTIEN, (SELECT SOHIEUTAIKHOAN FROM ERP_TAIKHOANKT WHERE PK_SEQ ="+taikhoanCO_SoHieu+") SOHIEUTAIKHOAN, N'"+doituongCO+"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 2 SAPXEP \n " +
									"FROM ERP_BUTTOANTONGHOP \n " +
									"WHERE PK_SEQ = '"+id+"'" ;
							}
							else
							{
								tonggiatri = Double.parseDouble(_giatriNO[pos]);
								
								taikhoanNO_SoHieu = _taikhoan[pos];
								taikhoanCO_SoHieu = _taikhoan[pos+1];
								
								doituongNO = _doituong[pos];
								doituongCO = _doituong[pos + 1];
								
								/*madoituongNO = _madoituong[pos];								
								madoituongCO = _madoituong[pos + 1];*/
								
								/*if(madoituongNO.equals("--")) madoituongNO= "";
								
								if(madoituongCO.equals("--")) madoituongCO= "";*/
								
								if(doituongNO.equals("--")) doituongNO= "";
								
								if(doituongCO.equals("--")) doituongCO= "";
								
								
								if(laytk.trim().length()>0) laytk += "UNION ALL \n";
								
								laytk += 
									"SELECT N'NỢ' NO_CO, "+id+" PK_SEQ, "+tonggiatri+" SOTIEN, (SELECT SOHIEUTAIKHOAN FROM ERP_TAIKHOANKT WHERE PK_SEQ ="+taikhoanNO_SoHieu+") SOHIEUTAIKHOAN, N'"+doituongNO+"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 1 SAPXEP \n " +
									"FROM ERP_BUTTOANTONGHOP \n " +
									"WHERE PK_SEQ = '"+id+"'" +
									
									"UNION ALL \n"+
									
									"SELECT N'CÓ' NO_CO, "+id+" PK_SEQ, "+tonggiatri+" SOTIEN, (SELECT SOHIEUTAIKHOAN FROM ERP_TAIKHOANKT WHERE PK_SEQ ="+taikhoanCO_SoHieu+") SOHIEUTAIKHOAN, N'"+doituongCO+"' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, "+i+" STT, 2 SAPXEP \n " +
									"FROM ERP_BUTTOANTONGHOP \n " +
									"WHERE PK_SEQ = '"+id+"'" ;
							}
							
							
						}
						
						pos += 2;
						i++;						
					}
				}
			}			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(laytk.trim().length()<=0){
			laytk = "SELECT '' NO_CO, '' PK_SEQ, '' SOTIEN, '' SOHIEUTAIKHOAN, '' DOITUONG, '' TRUNGTAMCHIPHI, '' TRUNGTAMDOANHTHU, 1 STT, 1 SAPXEP \n " +
					"FROM ERP_BUTTOANTONGHOP \n " +
					"WHERE PK_SEQ = '"+id+"'";
		}
		else{
			laytk += " ORDER BY PK_SEQ, STT, SAPXEP \n";
		}
		//System.out.println(laytk);
		
		return laytk;
	}
	
	private void getNppInfo()
	{		
		//Phien ban moi
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.UserId);
	}
	
	public void init()
	{
		if(!this.view.equals("TT"))
			this.getNppInfo();
		
			
		this.RsNguoiTao = db.get("select pk_seq, ten from NHANVIEN where trangthai = '1' and pk_seq in ( select distinct NGUOITAO from ERP_BUTTOANTONGHOP ) ");
		
		String query = 
				"SELECT BT.PK_SEQ,BT.NGAYBUTTOAN,ISNULL(BT.DIENGIAI,'') DIENGIAI,BT.NGAYTAO,NT.TEN AS NGUOITAO,BT.NGAYSUA  " +
				"  ,NS.TEN AS NGUOISUA,ISNULL(BT.TRANGTHAI,0) TRANGTHAI ,	SUM(ISNULL(CT.tiennt,0)) TONGTIEN  \n " +
				"		FROM 	ERP_BUTTOANTONGHOP BT left JOIN ERP_BUTTOANTONGHOP_CHITIET CT ON CT.BUTTOANTONGHOP_FK = BT.PK_SEQ \n "+ 
				"		INNER JOIN NHANVIEN NT ON NT.PK_SEQ=BT.NGUOITAO \n "+
				"		INNER JOIN NHANVIEN NS ON NS.PK_SEQ=BT.NGUOISUA \n " +			
				"		WHERE BT.npp_fk = "+ (this.nppId.trim().length() > 0 ? this.nppId :"1")  +"  \n ";				
		
				if(this.NgayButToan.trim().length()>0)
					query+=" AND BT.NGAYBUTTOAN>='"+this.NgayButToan+"'";
				if(this.DenNgayButToan.trim().length()>0)
					query+=" AND BT.NGAYBUTTOAN <='"+this.DenNgayButToan+"'";
				if(this.SoChungTu.trim().length()>0)
					query+= " AND BT.PK_SEQ = '"+this.SoChungTu+"'";
		
				if(this.TaiKhoanNo.trim().length()>0)
					query+= " AND ct.tkno LIKE '%"+this.TaiKhoanNo+"%' AND CT.NO IS NOT NULL  ";
				else if(this.TaiKhoanCo.trim().length()>0){
					query+= " AND ct.tkco LIKE '%"+this.TaiKhoanCo+"%' AND CT.CO IS NOT NULL  ";
				}
				if(this.NguoiTao.trim().length()>0)
					query+=" AND BT.NGUOITAO = '"+this.NguoiTao+"'";
				
				if(this.trangthai.trim().length()>0)
					query+=" AND BT.TRANGTHAI = '"+this.trangthai+"'";
		
			
				query+=" GROUP BY BT.PK_SEQ,BT.NGAYBUTTOAN,BT.DIENGIAI,BT.NGAYTAO,NT.TEN ,BT.NGAYSUA ,NS.TEN ,ISNULL(BT.TRANGTHAI,0) \n ";
		
			/*	if(this.SoTien.trim().length()>0)
				{
					query+=" HAVING ";
					if(this.TaiKhoanCo.trim().length()>0)
						query+= " (SUM(ISNULL(CT.CO,0)) = "+this.SoTien+") ";
					if(this.TaiKhoanNo.trim().length()>0)
						query+= " (SUM(ISNULL(CT.NO,0)) = "+this.SoTien+")";
					if(this.TaiKhoanCo.trim().length()>0&&this.TaiKhoanNo.trim().length()>0) 
						query+= "  SUM(ISNULL(CT.NO,0)) = "+this.SoTien+" ";
					if(this.TaiKhoanCo.trim().length()<=0&&this.TaiKhoanNo.trim().length()<=0)
						query+= "  SUM(ISNULL(CT.NO,0)) = "+this.SoTien+" ";
				}*/
		
		
		System.out.println("Init List "+query);
		
		this.RsButToan  = db.get(query);
	}

	public String getMsg()
	{
		return this.Msg;
	}

	public void setMsg(String Msg)
	{
		this.Msg = Msg;
	}

	public ResultSet getRsButToan()
	{
		return this.RsButToan;
	}

	public void setRsButToan(ResultSet RsButToan)
	{
		this.RsButToan = RsButToan;
	}

	public void DBClose()
	{
		try
		{
			if (this.RsTaiKhoanNo != null)
				this.RsTaiKhoanNo.close();
			if (this.RsTaiKhoanCo != null)
				this.RsTaiKhoanCo.close();
			if (this.RsNguoiTao != null)
				this.RsNguoiTao.close();
			if(this.RsButToan!=null)
			{
				this.RsButToan.close();
			}
			
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(this.db!=null)
			{
				this.db.shutDown();
			}
		}
	}
	

	public String getUserId()
	{

		return this.UserId;
	}

	public void setUserId(String UserId)
	{
		this.UserId = UserId;

	}

	public String getCongtyId() 
	{
		return this.congtyId;
	}

	public void setCongtyId(String congtyId) 
	{
		this.congtyId = congtyId;
	}
	
		
	public String getDenNgayButToan() {
		
		return this.DenNgayButToan;
	}

	
	public void setDenNgayButToan(String DenNgayButToan) {
		
		this.DenNgayButToan = DenNgayButToan;
	}

	
	public String getSoChungTu() {
		
		return this.SoChungTu;
	}

	
	public void setSoChungTu(String SoChungTu) {
		
		this.SoChungTu = SoChungTu;
	}

	
	public String getTaiKhoanNo() {
		
		return this.TaiKhoanNo;
	}

	
	public void setTaiKhoanNo(String TaiKhoanNo) {
		
		this.TaiKhoanNo = TaiKhoanNo;
	}

	
	public String getTaiKhoanCo() {
		
		return this.TaiKhoanCo;
	}

	
	public void setTaiKhoanCo(String TaiKhoanCo) {
		
		this.TaiKhoanCo = TaiKhoanCo;
	}

	
	public String getSoTien() {
		
		return this.SoTien;
	}

	
	public void setSoTien(String SoTien) {
		
		this.SoTien = SoTien;
	}

	
	public String getNguoiTao() {
		
		return this.NguoiTao;
	}

	
	public void setNguoiTao(String NguoiTao) {
		
		this.NguoiTao = NguoiTao;
	}

	
	public ResultSet getRsTaiKhoanNo() {
	
		return this.RsTaiKhoanNo;
	}

	
	public void setRsTaiKhoanNo(ResultSet RsTaiKhoanNo) {
	
		this.RsTaiKhoanNo = RsTaiKhoanNo;
	}

	
	public ResultSet getRsTaiKhoanCo() {
	
		return this.RsTaiKhoanCo;
	}

	
	public void setRsTaiKhoanCo(ResultSet RsTaiKhoanCo) {
	
		this.RsTaiKhoanCo = RsTaiKhoanCo;
	}

	
	public ResultSet getRsNguoiTao() {
		
		return this.RsNguoiTao;
	}

	
	public void setRsNguoiTao(ResultSet RsNguoiTao) {
		
		this.RsNguoiTao = RsNguoiTao;
	}

	
	public String getTrangthai() {
		
		return this.trangthai;
	}

	
	public void setTrangthai(String trangthai) {
		
		this.trangthai = trangthai;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	
}
