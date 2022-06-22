package  geso.dms.center.beans.Chat;

import java.sql.ResultSet;

import javax.xml.transform.Result;

import geso.dms.center.db.sql.dbutils;

public class Chat {
	
	public String khachhangid;
	public String ten;
	public ResultSet rskh;
	public Chat()
	{
		this.khachhangid="";
		this.ten="";
	}
	public void init()
	{
		dbutils db=new dbutils();
		
		String query="select a.pk_Seq,a.ten,isnull(a.dienthoai,'')  dienthoai ,case when data.loaitinnhan=1 then N'Có hình ảnh mới' else isnull(data.message,'') end   message  from khachhang a outer apply (\r\n" + 
						"	select top(1) message , loaitinnhan from KhachHang_TinNhan b where a.PK_SEQ=b.khachhang_fk and isnull(trangthai,0)=0\r\n" + 
						")  data  where shopkey=1 and a.trangthai=1\r\n" + 
						"order by data.message DESC";
		this.rskh=db.get(query);
		
	}
	
}