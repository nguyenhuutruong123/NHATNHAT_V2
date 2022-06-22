package geso.dms.center.beans.dieuchinhtonkho.imp;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import geso.dms.center.beans.dieuchinhtonkho.IDieuchinhtonkho;
import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;

public class Dieuchinhtonkho implements IDieuchinhtonkho, Serializable
{
	private static final long serialVersionUID = -9217977546733610214L;
	NumberFormat formatter = new DecimalFormat("#,###,###.##");
	String id;
	String nppId;
	String nppTen;
	String userId;
	String userTen;
	String ngaydc;
	String trangthai;	
	
	String nccId;
	ResultSet ncc;
	String dvkdId;
	ResultSet dvkd;
	String kbhId;
	ResultSet kbh;
	String khoId;
	ResultSet kho;

	String gtdc;
	
	String lydodc;

	String msg;
	String[] spId;
	String[] masp;
	String[] tensp;
	String[] tonkho;
	String[] dv;
	String[] dc;
	String[] soluongthung;
	String[] tkm;
	String[] giamua;
	String[] ttien;
	String[] solo;
	String[] tonthung;
	String[] tonle;
	String[] quycach;
	String[] soluongle;
	String[] dongiathung;
	
	int size;
	dbutils db;
	
	public Dieuchinhtonkho(String[] param)
	{
		this.db = new dbutils();
		this.id = param[0];
		this.nppId = param[1];
		this.nppTen = param[2];
		this.userId = param[3];
		this.userTen = param[4];
		this.ngaydc = param[5];
		this.nccId = param[6];
		this.dvkdId = param[7];
		this.kbhId = param[8];
		this.gtdc = param[9];
		
		this.lydodc = "";
		this.trangthai = "";
	}
	
	public Dieuchinhtonkho()
	{
		this.db = new dbutils();
		this.id = "";
		this.nppId = "";
		this.nppTen = "";
		this.userId = "";
		this.userTen = "";
		this.nccId = "";
		this.dvkdId = "";
		this.kbhId = "";
		this.khoId = "";
		this.gtdc = "";
		this.msg = "";
		this.ngaydc = getDateTime();
		
		this.lydodc = "";
		this.trangthai = "";
	}
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId(String id)
	{
		this.id = id;
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

	public String getUserId()
	{
		return this.userId;
	}
	
	public void setUserId(String userId)
	{
		this.userId = userId;
		geso.dms.distributor.util.Utility util=new geso.dms.distributor.util.Utility();
		this.nppId = util.getIdNhapp(this.userId);
		this.nppTen = util.getTenNhaPP();
		//this.dangnhap = util.getDangNhap();
		//this.sitecode=util.getSitecode();
	}
	
	public String getUserTen()
	{
		return this.userTen;
	}
	
	public void setUserTen(String userTen)
	{
		this.userTen = userTen;
	}
	
	public String getNgaydc()
	{
		return this.ngaydc;
	}
	
	public void setNgaydc(String ngaydc)
	{
		this.ngaydc = ngaydc;
	}
	
	public String getNccId()
	{
		return this.nccId;
	}
	
	public void setNccId(String nccId)
	{
		this.nccId = nccId;
	}

	public ResultSet getNcc()
	{
		return this.ncc;
	}
	
	public void setNcc(ResultSet ncc)
	{
		this.ncc = ncc;
	}

	public String getDvkdId()
	{
		return this.dvkdId;
	}
	
	public void setDvkdId(String dvkdId)
	{
		this.dvkdId = dvkdId;
	}

	public ResultSet getDvkd()
	{
		return this.dvkd;
	}
	
	public void setDvkd(ResultSet dvkd)
	{
		this.dvkd = dvkd;
	}

	
	public String getKbhId()
	{
		return this.kbhId;
	}
	
	public void setKbhId(String kbhId)
	{
		this.kbhId = kbhId;
	}

	public ResultSet getKbh()
	{
		return this.kbh;
	}
	
	public void setKbh(ResultSet kbh)
	{
		this.kbh = kbh;
	}

	public String getKhoId()
	{
		return this.khoId;
	}
	
	public void setKhoId(String khoId)
	{
		this.khoId = khoId;
	}

	public ResultSet getKho()
	{
		return this.kho;
	}
	
	public void setKho(ResultSet kho)
	{
		this.kho = kho;
	}

	public String getGtdc()
	{
		return this.gtdc;
	}
	
	public void setGtdc(String gtdc)
	{
		this.gtdc = gtdc;
	}
	
	public int getSize()
	{
		return this.size;
	}
	
	public void setSize(int size)
	{
		this.size = size;
	}

	public String[] getSpId()
	{
		return this.spId;
	}
	
	public void setSpId(String[] spId)
	{
		this.spId = spId;
	}

	public String[] getMasp()
	{
		return this.masp;
	}
	
	public void setMasp(String[] masp)
	{
		this.masp = masp;
	}
	
	public String[] getTensp()
	{
		return this.tensp;
	}
	
	public void setTenSp(String[] tensp)
	{
		this.tensp = tensp;
	}
	
	public String[] getTonkho()
	{
		return this.tonkho;
	}
	
	public void setTonkho(String[] tonkho)
	{
		this.tonkho = tonkho;
	}
	
	public String[] getDonvi()
	{
		return this.dv;
	}
	
	public void setDonvi(String[] dv)
	{
		this.dv = dv;
	}

	public String[] getDc()
	{
		return this.dc;
	}
	
	public void setDc(String[] dc)
	{
		this.dc = dc;
	}
	
	public String[] getGiamua()
	{
		return this.giamua;
	}
	
	public void setGiamua(String[] giamua)
	{
		this.giamua = giamua;
	}

	public String[] getTtien()
	{
		return this.ttien;
	}
	
	public void setTtien(String[] ttien)
	{
		this.ttien = ttien;
	}
	
	public String getMessage()
	{
		return this.msg;
	}
	
	public void setMessage(String msg)
	{
		this.msg = msg;
	}

	public void init0()
	{
		String query = "select e.pk_seq as dvkdId, e.donvikinhdoanh as dvkd from nhaphanphoi a, nhacungcap b, nhacungcap_dvkd c, nhapp_nhacc_donvikd d, donvikinhdoanh e where a.pk_seq = '"+ this.nppId + "' and a.pk_seq = d.npp_fk and b.pk_seq = c.ncc_fk and c.pk_seq = d.ncc_dvkd_fk and c.dvkd_fk = e.pk_seq";
		this.dvkd = this.db.get(query);
		
		query = "select c.pk_seq as kbhId, c.diengiai as kbh from nhaphanphoi a, nhapp_kbh b, kenhbanhang c where a.pk_seq = '"+ this.nppId +"' and a.pk_seq = b.npp_fk and b.kbh_fk = c.pk_seq";
		this.kbh = this.db.get(query);
	
		query = "select distinct a.pk_seq as khoId, a.ten as ten,a.diengiai from kho a, nhapp_kho b where a.pk_seq = b.kho_fk and b.npp_fk = '"+ this.nppId + "' and a.trangthai ='1'" ;
		this.kho = this.db.get(query);
	}
	
	

	public void initDisplay()
	{
		ResultSet rs = null;
		try
		{
			String query;
			query = "select ngaydc,(select ten from nhaphanphoi where pk_seq=npp_Fk) as nppTen, npp_fk, dvkd_fk, kbh_fk, tongtien,lydodc, kho_fk,trangthai from dieuchinhtonkho where pk_seq='" + this.id + "' ";
			rs = this.db.get(query);
			rs.next();
			this.nppTen=rs.getString("nppten");
			this.ngaydc = rs.getString("ngaydc");
			this.dvkdId = rs.getString("dvkd_fk");
			this.nppId	= rs.getString("npp_fk");
			this.kbhId	= rs.getString("kbh_fk");
			this.khoId	= rs.getString("kho_fk");
			this.gtdc 	= rs.getString("tongtien");
			this.lydodc=rs.getString("lydodc");
			this.trangthai=rs.getString("trangthai");
			rs.close();
			init0();
			query = "select count(*) as num from  sanpham ";
			rs = this.db.get(query);
			int size=0;
			rs.next();
			size = rs.getInt("num");
			rs.close();
			this.size = size;
			this.spId = new String[size];
			this.masp = new String[size];
			this.tensp = new String[size];
			this.tonkho = new String[size];
			this.dv = new String[size];
			this.dc  = new String[size];
			this.giamua = new String[size];
			this.ttien = new String[size];
			this.solo= new String[size];
			this.soluongthung=new String[size];
			this.soluongle=new String[size];
			this.tonthung=new String[size];
			this.tonle=new String[size];
			this.quycach=new String[size];
			this.dongiathung=new String[size];
				
			query = 
			"select distinct  a.sanpham_fk as spId, b.ma, b.ten, a.soluong as tonkho,isnull(e.tonmoi,0) as TonMoi,e.dieuchinh, "+
			 "a.giamua, c.donvi,a.solo,(d.soluong1/d.soluong2) as qc,isnull(e.thanhtien,0) as ThanhTien "+
			" from  "+
			" ( "+
			"	select * "+
			" 	from NHAPP_KHO_CHITIET a "+
			" 	where  a.npp_fk = '"+this.nppId+"'  "+
			"		and a.kbh_fk = '"+this.kbhId+"'  and a.kho_fk = '"+this.khoId+"' "+
			" ) as a "+
			" inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ  "+
			" left join DONVIDOLUONG c on c.PK_SEQ=b.DVDL_FK "+
			" left join QUYCACH d on d.SANPHAM_FK=a.SANPHAM_FK  and d.DVDL1_FK =b.DVDL_FK and d.DVDL2_FK=100018 "+
			" inner join DIEUCHINHTONKHO_SP e on e.SANPHAM_FK=a.SANPHAM_FK and e.SOLO=a.SOLO  and a.NgayHetHan=e.NgayHetHan and a.ngaynhapkho=e.ngaynhapkho   and e.DIEUCHINHTONKHO_FK='"+this.id+"' " ;				
			System.out.print(query);
			rs = this.db.get(query);
			int i = 0;
			while(rs.next())
			{
				this.spId[i] = rs.getString("spId");
				this.masp[i] = rs.getString("ma");
				this.tensp[i]= rs.getString("ten");
				this.tonkho[i] =  formatter.format (rs.getDouble("tonkho"));
				this.dv[i] = rs.getString("donvi");
				this.giamua[i] = formatter.format (rs.getDouble("giamua"));
				this.solo[i]=rs.getString("solo");
				double ton=rs.getDouble("tonkho") + Math.abs(rs.getDouble("dieuchinh"));
				double quycach=rs.getInt("qc");
				double thung = (double) ton/quycach;
				double le = (double) (ton );
				this.tonthung[i] =formatter.format( thung  );
				this.tonle[i]=formatter.format( le  );
				this.dc[i]=rs.getString("dieuchinh")==null?"":rs.getString("dieuchinh");
				this.quycach[i]=rs.getString("qc");
				this.soluongle[i]="";
				this.soluongthung[i]="";
				this.dongiathung[i]=formatter.format( rs.getDouble("giamua")*quycach  );
				this.ttien[i] = formatter.format( rs.getDouble("thanhtien")  );
				if(dc[i]!="")
				{
					double tonmoi=rs.getDouble("tonmoi");
					double thung_quy_doi = (double) tonmoi/quycach;
					this.soluongle[i]=formatter.format( tonmoi  );
					this.soluongthung[i]=formatter.format( thung_quy_doi  );
				}
				i++;
			}			
			this.size=i;
		}catch(Exception e){e.printStackTrace();}
		finally{if(rs != null)
			try {
				rs.close();
			} catch(Exception e) 
			{
				e.printStackTrace();
			}}
	}

	
	public void init()
	{
		ResultSet rs = null;
		try
		{
			String query;
			query = "select ngaydc,(select ten from nhaphanphoi where pk_seq=npp_Fk) as nppTen, npp_fk, dvkd_fk, kbh_fk, tongtien,lydodc, kho_fk,trangthai from dieuchinhtonkho where pk_seq='" + this.id + "' ";
			rs = this.db.get(query);
			rs.next();
			this.nppTen=rs.getString("nppten");
			this.ngaydc = rs.getString("ngaydc");
			this.dvkdId = rs.getString("dvkd_fk");
			this.nppId	= rs.getString("npp_fk");
			this.kbhId	= rs.getString("kbh_fk");
			this.khoId	= rs.getString("kho_fk");
			this.gtdc 	= rs.getString("tongtien");
			this.lydodc=rs.getString("lydodc");
			this.trangthai=rs.getString("trangthai");
			rs.close();
			init0();
			query = "select count(*) as num from  sanpham ";
			rs = this.db.get(query);
			int size=0;
			rs.next();
			size = rs.getInt("num");
			rs.close();
			this.size = size;
			this.spId = new String[size];
			this.masp = new String[size];
			this.tensp = new String[size];
			this.tonkho = new String[size];
			this.dv = new String[size];
			this.dc  = new String[size];
			this.giamua = new String[size];
			this.ttien = new String[size];
			this.solo= new String[size];
			this.soluongthung=new String[size];
			this.soluongle=new String[size];
			this.tonthung=new String[size];
			this.tonle=new String[size];
			this.quycach=new String[size];
			this.dongiathung=new String[size];
				
			query = 
			"select  a.sanpham_fk as spId, b.ma, b.ten, a.soluong as tonkho,isnull(e.tonmoi,0) as TonMoi,e.dieuchinh, "+
			 "a.giamua, c.donvi,a.solo,(d.soluong1/d.soluong2) as qc,isnull(e.thanhtien,0) as ThanhTien "+
			" from  "+
			" ( "+
			"	select * "+
			" 	from NHAPP_KHO_CHITIET a "+
			" 	where  a.npp_fk = '"+this.nppId+"'  "+
			"		and a.kbh_fk = '"+this.kbhId+"'  and a.kho_fk = '"+this.khoId+"' "+
			" ) as a "+
			" inner join SANPHAM b on a.SANPHAM_FK=b.PK_SEQ  "+
			" left join DONVIDOLUONG c on c.PK_SEQ=b.DVDL_FK "+
			" left join QUYCACH d on d.SANPHAM_FK=a.SANPHAM_FK and d.DVDL2_FK=100018   and d.DVDL1_FK=b.DVDL_FK   "+
			" inner join DIEUCHINHTONKHO_SP e on e.SANPHAM_FK=a.SANPHAM_FK and e.SOLO=a.SOLO   and a.NGAYHETHAN=e.NgayHetHan and e.DIEUCHINHTONKHO_FK='"+this.id+"' " ;				
			System.out.print(query);
			rs = this.db.get(query);
			int i = 0;
			while(rs.next())
			{
				this.spId[i] = rs.getString("spId");
				this.masp[i] = rs.getString("ma");
				this.tensp[i]= rs.getString("ten");
				this.tonkho[i] =  formatter.format (rs.getDouble("tonkho"));
				this.dv[i] = rs.getString("donvi");
				this.giamua[i] = formatter.format (rs.getDouble("giamua"));
				this.solo[i]=rs.getString("solo");
				double ton=rs.getDouble("tonkho");
				double quycach=rs.getInt("qc");
				double thung = (double) ton/quycach;
				double le = (double) (ton  % quycach);
				this.tonthung[i] =formatter.format( thung  );
				this.tonle[i]=formatter.format( le  );
				this.dc[i]=rs.getString("dieuchinh")==null?"":rs.getString("dieuchinh");
				this.quycach[i]=rs.getString("qc");
				this.soluongle[i]="";
				this.soluongthung[i]="";
				this.dongiathung[i]=formatter.format( rs.getDouble("giamua")*quycach  );
				this.ttien[i] = formatter.format( rs.getDouble("thanhtien")  );
				if(dc[i]!="")
				{
					double tonmoi=rs.getDouble("tonmoi");
					double thung_quy_doi = (double) tonmoi/quycach;
					this.soluongle[i]=formatter.format( tonmoi  );
					this.soluongthung[i]=formatter.format( thung_quy_doi  );
				}
				i++;
			}			
			this.size=i;
		}catch(Exception e){e.printStackTrace();}
		finally{if(rs != null)
			try {
				rs.close();
			} catch(Exception e) 
			{
				e.printStackTrace();
			}}
	}
	
	
	public String CheckNghiepVu()
	{
		int count=0;
		String query="select count(*) as count from donhang where trangthai=0 and npp_fk="+this.nppId+"";
		ResultSet rs=this.db.get(query);
		try
		{
			while(rs.next())
			{
				count=rs.getInt(1);
			}rs.close();
			if(count>0)
			{
				return "Còn "+count+  " đơn hàng chưa chốt.Bạn phải chốt đơn hàng trước khi làm Kiểm kho ";
			} 
			query="select count(*) from dontrahang where trangthai=0  and npp_fk="+this.nppId+" ";
			rs=this.db.get(query);
			while(rs.next())
			{
				count=rs.getInt(1);
			}rs.close();
			if(count>0)
			{
				return "Còn "+count+  " đơn trả hàng nhà cung cấp chưa chốt.Bạn phải chốt đơn hàng trước khi làm Kiểm kho ";
			}
			query="select count(*) from donhangtrave where trangthai=0 and npp_fk="+this.nppId+" ";
			rs=this.db.get(query);
			while(rs.next())
			{
				count=rs.getInt(1);
			}rs.close();
			if(count>0)
			{
				return "Còn "+count+  " đơn trả hàng chưa chốt.Bạn phải chốt đơn hàng trước khi làm Kiểm kho ";
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return "";
	}

	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}   
	
	public void DBclose(){

		
			try {
				if(this.dvkd != null)
					this.dvkd.close();
				if(this.kbh != null)
					this.kbh.close();
				if(this.kho != null)
					this.kho.close();
				if(this.ncc != null)
					this.ncc.close();
				if(!(this.db == null)){
					this.db.shutDown();
				}
				
			} catch(Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	private boolean CheckNgayDieuChinh()
	{
		Utility util = new Utility();
		String ngayksgn = util.ngaykhoaso(this.nppId);
		
		//System.out.print("\nNgay khoa so gan nhat la: " + this.ngaychungtu + "\n");
		
		if(ngayksgn.equals(""))
			return false;
		
		String[] ngayks = ngayksgn.split("-");
		String[] ngayct = this.ngaydc.split("-");
		
		//System.out.print("\nNgay chung tu la: " + this.ngaychungtu + "\n");
		
		Calendar c1 = Calendar.getInstance(); //new GregorianCalendar();
		Calendar c2 = Calendar.getInstance(); //new GregorianCalendar();

		//NGAY KHOA SO
		
		c1.set(Integer.parseInt(ngayks[0]), Integer.parseInt(ngayks[1]) - 1, Integer.parseInt(ngayks[2]));
	
		//NGAY thuc hien tra hang
		c2.set(Integer.parseInt(ngayct[0]), Integer.parseInt(ngayct[1]) - 1, Integer.parseInt(ngayct[2]));
	
		c1.add(Calendar.DATE, 1);//ngay tra don hang bang ngay khoa so them 1 ngay
		//phep tinh ngay nhan hang - ngay khoa so
			
		long songay = ( c1.getTime().getTime() - c2.getTime().getTime()) / (24 * 3600 * 1000);
		   
		if(songay < 0) //ngay chung tu khong duoc nho hon hoac bang ngay khoa so gan nhat 
		{
			this.msg = "Ngay Tra Don Hang Phai Lon Hon Ngay Khoa So Gan Nhat.";
			return false;
		}
		return true;
	}

	public String getLydodc() 
	{
		return this.lydodc;
	}

	public void setLydodc(String lydodc) 
	{
		this.lydodc = lydodc;
	}

	public String[] getSolo()
	{
		return solo;
	}

	public void setSolo(String[] solo)
	{
		this.solo = solo;
	}

	public String[] getTonthung()
	{
		return tonthung;
	}

	public void setTonthung(String[] tonthung)
	{
		this.tonthung = tonthung;
	}

	public String[] getTonle()
	{
		return tonle;
	}

	public void setTonle(String[] tonle)
	{
		this.tonle = tonle;
	}

	public String[] getSoluongthung()
	{
		return soluongthung;
	}

	public void setSoluongthung(String[] soluongthung)
	{
		this.soluongthung = soluongthung;
	}

	public String[] getQuycach()
	{
		return quycach;
	}

	public void setQuycach(String[] quycach)
	{
		this.quycach = quycach;
	}

	public String[] getSoluongle()
	{
		return soluongle;
	}

	public void setSoluongle(String[] soluongle)
	{
		this.soluongle = soluongle;
	}

	public String[] getDongiathung()
	{
		return dongiathung;
	}

	public void setDongiathung(String[] dongiathung)
	{
		this.dongiathung = dongiathung;
	}
	public String getTrangthai()
	{
		return trangthai;
	}

	public void setTrangthai(String trangthai)
	{
		this.trangthai = trangthai;
	}
	
	
	String[] spNgayHetHan;

	public String[] getSpNgayHetHan()
  {
  	return spNgayHetHan;
  }

	public void setSpNgayHetHan(String[] ngayHetHan)
  {
  	this.spNgayHetHan = ngayHetHan;
  }
	

}