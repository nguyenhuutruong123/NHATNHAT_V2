package geso.dms.center.beans.khoasothang;

import java.sql.ResultSet;

public interface IErpKhoasothang 
{
	public String getUserId();
	public void setUserId(String userId);
	
	public String getThang();
	public void setThang(String thang);
	public String getNam();
	public void setNam(String nam);
	
	public ResultSet getChungtuRs();
	public void setChungtuRs(ResultSet ctRs);
	
	public int getRow();
	public void setRow(int row);
	public String getMsg();
	public void setMsg(String msg);
	
	public int getSonhanhang();
	public void setSonhanhang(int row);
	public int getSonhapkho();
	public void setSonhapkho(int row);
	
	public int getSoQC_NhanHang();
	public void setSoQC_NhanHang(int row);
	public int getSoQC_LSX();
	public void setSoQC_LSX(int row);
	public int getSoTH_NhanHang();
	public void setSoTH_NhanHang(int row);
	public int getSoTH_LSX();
	public void setSoTH_LSX(int row);
	
	public int getCoNhapKho_ChuaTH();
	public void setCoNhapKho_ChuaTH(int row);
	
	public int getSoDctk();
	public void setSoDctk(int row);
	public int getSoKiemkho();
	public void setSoKiemkho(int row);
	
	public int getSohdNCC();
	public void setSohdNCC(int row);
	public int getSoxuatkho();
	public void setSoxuatkho(int row);
	public int getSoxuatkhoChuaNhanHD();
	public void setSoxuatkhoChuaNhanHD(int row);
	public int getSochuyenkho();
	public void setSochuyenkho(int row);
	public int getSodctk();
	public void setSodctk(int row);
	
	public int getSoHdtc();
	public void setSoHdtc(int row);
	public int getSoLsx();
	public void setSoLsx(int row);
	
	public int getSoPhieuthu();
	public void setSoPhieuthu(int row);
	public int getSoPhieuchi();
	public void setSoPhieuchi(int row);
	public int getSoBtth();
	public void setSoBtth(int row);
	
	public void init();
	public String KhoaSoThang();
	
	
	//Khoa so ke toan
	
	public int getChaykhauhao();
	public void setChaykhauhao(int row);
	public int getChaychenhlechtigia();
	public void setChaychenhlechtigia(int row);
	public int getTinhgiathanh();
	public void setTinhgiathanh(int row);
	
	public void initKSKT();
	public String KhoaSoKeToan();
	
}