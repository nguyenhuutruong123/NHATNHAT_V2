package geso.dms.center.beans.khuyenmaidacbiet;

import java.io.Serializable;
import java.util.List;

public interface ISanpham extends Serializable
{
	public String getBarcode() ;
	public String getId();
	public String getMa();
	public String getStt() ;
	public String getTen();
	
	public void setBarcode(String barcode);
	public void setId(String id) ;
	
	public void setMa(String ma) ;
	public void setStt(String stt) ;
	public void setTen(String ten) ;
	
}
