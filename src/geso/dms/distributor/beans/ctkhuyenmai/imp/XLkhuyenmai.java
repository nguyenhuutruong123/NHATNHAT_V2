
package geso.dms.distributor.beans.ctkhuyenmai.imp;

import geso.dms.distributor.beans.ctkhuyenmai.ICtkhuyenmai;
import geso.dms.distributor.beans.ctkhuyenmai.ISanpham;
import geso.dms.distributor.beans.dieukienkhuyenmai.IDieukienkhuyenmai;
import geso.dms.distributor.beans.dieukienkhuyenmai.imp.Dieukienkhuyenmai;
import geso.dms.distributor.beans.trakhuyenmai.ITrakhuyenmai;
import geso.dms.distributor.beans.trakhuyenmai.imp.Trakhuyenmai;
import geso.dms.distributor.db.sql.dbutils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class XLkhuyenmai 
{
	private dbutils db;
	
	private Hashtable<String, Double> HashA = new Hashtable<String,Double>(); //khoi tao tu don hang "abc"	
	private Hashtable<String, Float> Hash_QuyCach = new Hashtable<String,Float>(); //khoi tao tu don hang "abc"
	private Hashtable<String,Float> HashB = new Hashtable<String,Float>(); //khoi tao tu don hang "abc"
	
	private Hashtable<String, Double> HashC = new Hashtable<String,Double>(); //dung trong truong hop Ontop 	
	
	private List<ICtkhuyenmai> ctkmList; //chuong trinh km Avaialble
	
	private List<ICtkhuyenmai> ctkmResult; //ket qua
	private List<ICtkhuyenmai> ctkmOntopList; //ontop
	private List<ICtkhuyenmai> ctkmBTList; //chuong trinh km Binh Thuong
	private List<ICtkhuyenmai> ctkmCKList; //chuong trinh km Binh Thuong
	
	private List<ICtkhuyenmai> ctkmOntopResult; //ket qua
	private List<ICtkhuyenmai> ctkmBTResult; //ket qua
	private List<ICtkhuyenmai> ctkmCKResult; //ket qua
	
	private boolean dieuchinhKM;
	private boolean dungdieukien;
	
	//luu lai cac thong tin
	private String[] masp;
	private String[] soluong;
	private String[] dongia;
	private String[] quycach;
	private String idDonhang;
	private float tonggiatriDh;
	private String ngaygiaodich; //ngay giao dich trong don hang
	private String Msg = "";
	String userId;
	String nppId;
	String nppTen;
	String sitecode;
	String khachhang;
	String kenh_kh;
	String loaidonhang;
	String denghitraCK;
	
	String donhangId;

	public XLkhuyenmai(String userId, String date, String khachhang, String donhangId, String isDonHangLe)
	{
		db = new dbutils();
		
		this.loaidonhang = isDonHangLe;
		this.donhangId = donhangId;
		this.khachhang = khachhang;
		String sql = 	"\n select DonHang.kbh_fk,DonHang.npp_fk,npp.ten  " +
						"\n from DonHang " +
						"\n inner join nhaphanphoi npp on npp.pk_Seq=DonHang.npp_fk  where DonHang.pk_Seq="+this.donhangId;
		//System.out.println("1.Lay Kenh Trong Xly khuyen Mai" + sql);
		
		ResultSet rs = db.get(sql);
		try
		{
			if(rs.next())
			{
				this.kenh_kh = rs.getString("kbh_fk");
				this.nppId = rs.getString("npp_fk");
				this.nppTen = rs.getString("ten");
			}
			rs.close();
		}
		catch(Exception exception)
		{
			System.out.println("115.Error Herer: " + exception.toString());
		}
		
		this.userId = userId;
		
		
		this.ctkmList = this.getListCTKM_Avaiable(date);
		
		this.ctkmOntopList = new ArrayList<ICtkhuyenmai>();    		
		this.ctkmBTList = new ArrayList<ICtkhuyenmai>();
		this.ctkmCKList = new ArrayList<ICtkhuyenmai>();
		
		
		/*for(int i = 0; i < this.ctkmList.size(); i++)
		{
			ICtkhuyenmai ctkm = this.ctkmList.get(i);
			if(ctkm.getLoaict() == 1)
			{
				if(ctkm.getCK() > 0)
					this.ctkmCKList.add(ctkm);
				else
					this.ctkmBTList.add(ctkm);
			}
			else
			{
				this.ctkmOntopList.add(ctkm);
			}
		}*/
		
		this.dungdieukien = false;
	}
		
	public List<ICtkhuyenmai> getListCTKM_Avaiable(String date) //list chuong trinh khuyen mai con hieu luc
	{   
		List<ICtkhuyenmai> ctkmList = new ArrayList<ICtkhuyenmai>();
		
		String sql = "";
		
		//CTKM BEN VIFON AP DUNG CHO KENH TU TRUOC, NEN CHI LOC RA NHUNG SCHEME NAO CO KENH CHUA KENH CUA KHACH HANG
		sql = "\n select distinct a.pk_seq, a.scheme, a.tungay, a.denngay, a.loaict, a.kho_fk,  isnull(a.diengiai, '') as diengiai " +
			  "\n ,isnull(track.chietkhau,0)chietkhau  ,isnull(a.loaingansach,0)loaingansach, case when d.ngansach > 2100000000 then 2100000000 else d.ngansach end as ngansach, a.PHANBOTHEODONHANG, ISNULL(a.soxuattoida, 0) as soxuattoida " +
			  "\n ,isnull(ssKH.sosuat,0) as dasudungKH, " +
			  "\n CASE WHEN ISNULL(A.LOAINGANSACH,0) = '1' THEN " +
			  "\n 	   CASE WHEN ISNULL(A.PHANBOTHEODONHANG, 0) = '0' THEN ISNULL(ssNPP.tonggiatri,0) " +
			  "\n	   WHEN ISNULL(A.PHANBOTHEODONHANG, 0) = '1' THEN ISNULL(ssNPP.sosuat,0) END " +
			  "\n ELSE 0 END AS dasudungNPP " +		
		"\n from ctkhuyenmai a inner join ctkm_npp b on a.pk_seq = b.ctkm_fk and b.NPP_FK = '" + this.nppId + "'  " +
		"\n inner join  phanbokhuyenmai d on b.CTKM_FK = d.CTKM_FK and d.NPP_FK = '" + this.nppId + "'  " +
		"\n	inner join CTKM_DKKM e on a.pk_seq = e.CTKHUYENMAI_FK " + 
		"\n	inner join DIEUKIENKM_SANPHAM f on e.DKKHUYENMAI_FK = f.DIEUKIENKHUYENMAI_FK " + 
		"\n outer apply (" +
		"\n		select max(isnull(tkm.chietkhau,0)) chietkhau " +
		"\n		from CTKM_TRAKM x inner join TRAKHUYENMAI tkm on tkm.PK_SEQ =x.TRAKHUYENMAI_FK " +
		"\n		where x.CTKHUYENMAI_FK =  d.CTKM_FK 	" +
		"\n ) track" + 
		
		"\n outer apply " +
		"\n (	" +
		"\n		select sum(soxuat) sosuat, sum(tonggiatri)tonggiatri " +
		"\n		from  " +
		"\n 	(" +
		"\n			select dhkm.donhangId,max(soxuat) soxuat, sum(dhkm.tonggiatri) tonggiatri " +
		"\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId " +
		"\n			where dh.trangthai !=2 and dh.npp_fk =  d.npp_fk and dhkm.ctkmid = d.ctkm_fk and " +
		"\n			not exists ( select 1 from Erp_HangTraLaiNpp where trangthai = '1' and donhang_fk = dh.pk_seq) " +
		"\n			group by dhkm.donhangId " +
		"\n 	)ss " +
		"\n )ssNPP	" +
		
		"\n outer apply " +
		"\n (	" +
		"\n		select sum(soxuat) sosuat " +
		"\n		from  " +
		"\n 	(" +
		"\n			select dhkm.donhangId,max(soxuat) soxuat " +
		"\n			from donhang_ctkm_trakm dhkm inner join donhang dh on dh.pk_seq = dhkm.donhangId " +
		"\n			where dh.khachhang_fk = '"+ this.khachhang +"' and a.PHANBOTHEODONHANG = '1' " +
		"\n   		and dh.trangthai != '2' and dhkm.ctkmid = d.CTKM_FK and not exists (select 1 from erp_hangtralainpp where trangthai = '1' " +
		"\n			and donhang_fk = dh.pk_seq)  " +
		"\n			group by dhkm.donhangId " +
		"\n 	)ss " +
		"\n )ssKH " +
		"\n where isnull(a.ap_dung_npp,0) = 0 and a.loaict <> '3' and a.tungay <= '" + date + "' and '" + date + "' <= a.denngay   " + 
			"\n and ( a.nhomkhnpp_fk is null or a.nhomkhnpp_fk in (select pk_seq from nhomkhachhangnpp  " +
			"\n where pk_seq in (select nhomkhnpp_fk from ctkm_khachhang  " +
								"\n where khachhang_fk ='" + this.khachhang + "') ) )  " +
		
			 "\n and -- " +
             "\n ( -- " +
             "\n   (select COUNT(CTKM_FK) from CTKHUYENMAI_LOAIKH where CTKM_FK = a.PK_SEQ) =0 -- " +
             "\n   or  exists -- " +
             "\n           ( -- " +
             "\n            select 1 from CTKHUYENMAI_LOAIKH where CTKM_FK = a.PK_SEQ  -- " +
             "\n            and loaikh_fk = (select LCH_FK from KHACHHANG where PK_SEQ = '" + this.khachhang + "' ) -- " +
             "\n           )    -- " +
             "\n )  -- "+
			 
			 "\n and -- " +
             "\n ( -- " +
             "\n   (select COUNT(CTKM_FK) from CTKHUYENMAI_HANGCUAHANG where CTKM_FK = a.PK_SEQ) =0 -- " +
             "\n   or  exists -- " +
             "\n           ( -- " +
             "\n            select 1 from CTKHUYENMAI_HANGCUAHANG where CTKM_FK = a.PK_SEQ  -- " +
             "\n            and HCH_FK = (select HCH_FK from KHACHHANG where PK_SEQ = '" + this.khachhang + "' ) -- " +
             "\n           )    -- " +
             "\n )  -- " +
             "\n and exists ( select 1 from ctkhuyenmai_kbh kmkbh where kmkbh.ctkm_fk = a.pk_seq and kmkbh.kbh_fk = ( select kbh_fk from khachhang where pk_seq = '"+ this.khachhang +"' )) "+
             
             "\n and ( a.cttb_fk is null or a.cttb_fk in ( select cttb_fk from khachhang_anhchup where khachhang_fk = '" + this.khachhang + "' and ANHCHUP is not null and cttb_fk is not null ) )  " +
             "\n and f.SANPHAM_FK in ( select sanpham_fk from DONHANG_SANPHAM where donhang_fk = '" + this.donhangId + "' )	" +
             "\n and [dbo].[CheckSanPhamDauTien](a.SanPhamDauTien,a.pk_seq ," + this.khachhang + "," + donhangId + ")  < 1  " +
             "\n order by isnull(track.chietkhau,0) desc";
		
		//N???U CTKM KHAI B??O PH??? THU???C NHAU. TH?? T??? ????N H??NG SAU, N???U ???? CH???N KM N??O TH?? CH??? ???????C CH???N KM ????
		System.out.println("1.Chuoi lay ctkm hien huu: \n" + sql);
		ResultSet ctkm = db.get(sql);
	
		if(ctkm != null)
		{
		try 
		{
			while(ctkm.next())
			{
				String id = ctkm.getString("pk_seq");
				String scheme = ctkm.getString("scheme");
				String tungay = ctkm.getDate("tungay").toString();
				String denngay = ctkm.getDate("denngay").toString();
				String diengiai = ctkm.getString("diengiai");
				int khoId = ctkm.getInt("kho_fk");
				int loaict = ctkm.getInt("loaict");
				
				float chietkhau = ctkm.getFloat("chietkhau");
				double ngansach = 0;
				if(ctkm.getDouble("ngansach") > 2100000000)
				{
					ngansach = 2100000000; // CH??N TR??M CH??N M????I CH??N TR???U
				}
				else { ngansach = ctkm.getDouble("ngansach"); }
				int soxuatKM = 0;
				
				int dasudungKH = ctkm.getInt("dasudungKH");
				int dasudungNPP = ctkm.getInt("dasudungNPP");
				int soxuattoida = ctkm.getInt("soxuattoida");
				int phanbotheodonhang = ctkm.getInt("phanbotheodonhang");
				double sosuat_toida_dh = 999999;
				int conlaiKH  = 999999;
				double conlaiNPP = 999999;
				
				boolean cokm = false;
				if(soxuattoida == 0 && ctkm.getString("loaingansach").equals("0")) { cokm = true; }
				else 
				{
					if(soxuattoida > 0)
					{
						conlaiKH = soxuattoida - dasudungKH;
						sosuat_toida_dh = Math.min(sosuat_toida_dh, conlaiKH);
					}
					
					if(phanbotheodonhang == 1)
					{
						conlaiNPP = ngansach - dasudungNPP;
						sosuat_toida_dh = Math.min(sosuat_toida_dh, conlaiNPP);
					}
					else
					{
						conlaiNPP = ngansach - dasudungNPP;
					}
					if(sosuat_toida_dh > 0 && conlaiNPP > 0 )
					{	cokm = true; }
				}		
					
				/*if(soxuattoida > 0)
				{
					conlaiKH = soxuattoida - dasudungKH;
					conlaiNPP = ngansach - dasudungNPP;
				    int[] nums={conlaiKH, conlaiNPP};
				    Arrays.sort(nums);
					conlaiKM = nums[0]; // minimum value
				}
				else if(soxuattoida == 0)
				{
					conlaiNPP = ngansach - dasudungNPP;
					if(ctkm.getString("loaingansach").equals("1")) // CO AP PHAN BO
					{
						conlaiKM = conlaiNPP;
					}
				}*/
				//System.out.println("sosuat_toida_dh : "+ sosuat_toida_dh+" - cokm : "+ cokm);
				if( sosuat_toida_dh > 0 && cokm)
				{
					int SoxuatKM_TOIDA  = (int)sosuat_toida_dh;
					double sotienKM_TOIDA =2100000000;
					
					
					Ctkhuyenmai km = new Ctkhuyenmai(id, scheme, diengiai, tungay, denngay, loaict, soxuattoida, dasudungKH, soxuatKM);
					km.setCK(chietkhau);
					km.setPhanbotheoDH(ctkm.getString("PHANBOTHEODONHANG"));
					km.setLoaingansach(ctkm.getInt("loaingansach"));
					
					
					
					
					List<IDieukienkhuyenmai> dkkhuyenmai = new ArrayList<IDieukienkhuyenmai>();
					List<ITrakhuyenmai> trakhuyenmai = new ArrayList<ITrakhuyenmai>();
					
					//Lay danh sach dieu kien khuyen mai cua chuong trinh
					ResultSet rs = db.get("select dkkhuyenmai_fk, pheptoan from ctkm_dkkm where Ctkhuyenmai_fk='" + id + "' order by thutudieukien ASC");
					//System.out.println("Lay tra km: select dkkhuyenmai_fk, pheptoan from ctkm_dkkm where Ctkhuyenmai_fk='" + id + "' order by thutudieukien ASC");
					if(rs != null)
					{
						while(rs.next())
						{
							String dkkmId = rs.getString("dkkhuyenmai_fk");
							int pheptoan = Integer.parseInt(rs.getString("pheptoan"));
							
							Dieukienkhuyenmai dkkm = new Dieukienkhuyenmai(dkkmId);
							dkkm.setPheptoan(pheptoan);
							dkkhuyenmai.add(dkkm);
						}
						rs.close();
					}
					km.setDkkhuyenmai(dkkhuyenmai);
					
					//Lay danh sach tra khuyen mai cua chuong trinh
					ResultSet rsTraKm = db.get("select trakhuyenmai_fk, pheptoan from ctkm_trakm where Ctkhuyenmai_fk='" + id + "' order by thutu ASC ");
					//System.out.println("2323.Get tra khuyen mai: select trakhuyenmai_fk, pheptoan from ctkm_trakm where Ctkhuyenmai_fk='" + id + "' order by thutu ASC ");
					if(rsTraKm != null)
					{
						int pos = 0;
						while(rsTraKm.next())
						{
							String trakmId = rsTraKm.getString("trakhuyenmai_fk");
							int pheptoan = Integer.parseInt(rsTraKm.getString("pheptoan"));
							
							Trakhuyenmai trakm = new Trakhuyenmai(trakmId, nppId, date, kenh_kh, khoId);
							
							if(trakm.getType() == 1)// tr??? ti???n
							{
								if(ctkm.getString("loaingansach").equals("1") && phanbotheodonhang == 0 )//  ngan sach theo tien
								{
									sotienKM_TOIDA  = Math.min( sotienKM_TOIDA , conlaiNPP);
									SoxuatKM_TOIDA = (int)Math.floor(sotienKM_TOIDA/trakm.getTongtien());
									
								}
							}else
								if(trakm.getType() == 2)// tr??? ti???n
								{
									if(ctkm.getString("loaingansach").equals("1") && phanbotheodonhang == 0 )//  ngan sach theo tien
									{
										sotienKM_TOIDA  = Math.min( sotienKM_TOIDA , conlaiNPP);
									}
								}
							
							
							trakm.setPheptoan(pheptoan);
							
							if(pos > 0 && pheptoan == 2)
							{
								km.setTra_OR(true);
								//System.out.println("Co tra khuyen mai OR trong CTKM...");
							}
							
							trakhuyenmai.add(trakm);
							pos++;
						}
						rsTraKm.close();
					}
					
					km.setSoxuatKM_TOIDA(SoxuatKM_TOIDA);
					km.setSotienKM_TOIDA(sotienKM_TOIDA);
					
					km.setTrakhuyenmai(trakhuyenmai);
					
					
					ctkmList.add(km);
				}
				
			}
			if(ctkm!=null){
				ctkm.close();
			}
		} 
		
		catch (Exception e) { System.out.println("115.Error: " + e.getMessage()); }
		}
		
		return ctkmList;
	}
	
	
	public void ApKhuyenMai()
	{		
		//int pos_schemeBT = Integer.MAX_VALUE; int pos_schemeCK = Integer.MAX_VALUE;
		for(int i = 0; i < this.ctkmList.size(); i++)
		{
			ICtkhuyenmai ctkm = this.ctkmList.get(i);
			if(ctkm.getLoaict() == 1) { this.ctkmBTList.add(ctkm); }
			else { this.ctkmOntopList.add(ctkm); }
		}
		this.ctkmResult = new ArrayList<ICtkhuyenmai>();
		this.ctkmBTResult = new ArrayList<ICtkhuyenmai>();
		this.ctkmOntopResult = new ArrayList<ICtkhuyenmai>();
		this.ctkmCKResult = new ArrayList<ICtkhuyenmai>();
		
		//Neu Co sort phai xet la Ap KM CHiet Khau hay Binh thuong truoc (CK cua OneOne chay dac biet)
		ApBinhThuong();
		ApOnTop();

		for(int  i = 0; i < this.ctkmBTResult.size(); i++)
		{
			ICtkhuyenmai ctkm = this.ctkmBTResult.get(i);
			this.ctkmResult.add(ctkm);
		}
		
		/*for(int  i = 0; i < this.ctkmCKResult.size(); i++)
		{
			ICtkhuyenmai ctkm = this.ctkmCKResult.get(i);
			this.ctkmResult.add(ctkm);
		}*/
		
		for(int  i = 0; i < this.ctkmOntopResult.size(); i++)
		{
			ICtkhuyenmai ontop = this.ctkmOntopResult.get(i);
			this.ctkmResult.add(ontop);
		}
		
		System.out.println("115___So CTKM duoc huong: " + this.ctkmResult.size());
		
		//OneOne Them Trong So
		for(int i = 0; i < this.ctkmResult.size(); i++)
		{
			Ctkhuyenmai ctkm = (Ctkhuyenmai)this.ctkmResult.get(i);
			
			List<IDieukienkhuyenmai> dkkmList = ctkm.getDkkhuyenmai();
			for(int j = 0; j < dkkmList.size(); j++ )
			{
				IDieukienkhuyenmai dkkm = dkkmList.get(j);
				if(dkkm.getTrongso() > 0)
				{
					/*System.out.println("1.Dkkm: " + dkkm.getDiengiai() + " -- Trong so: " + dkkm.getTrongso());*/

					double tongluong = 0;
					
					if(dkkm.getTongluong() > 0)
					{
						tongluong = ctkm.getSoxuatKM() * dkkm.getTongluong();
					}
					else
					{
						tongluong = ctkm.getSoxuatKM() * dkkm.getTongtien();
					}
					
					double minRequest = 0;
					
					if(dkkm.getTongluong() > 0)
					{
						minRequest = dkkm.getTrongso() * dkkm.getTongluong() / 100;
					}
					else
					{
						minRequest = dkkm.getTrongso() * dkkm.getTongtien() / 100;
					}
					
					/*System.out.println("__Tong so luong yeu cau nho nhat: " + minRequest  );*/
					
					//Tong trong so cua cac san pham bat buoc mua
					Hashtable<String, Float> sanpham_trongso = dkkm.getSanpham_Trongso();
					
					//boolean flag = true;  //Neu SP la bat buoc ma ko su dung thi cung ko dc khuyen mai
					List<ISanpham> spList = dkkm.getSanphamList();	
					float totalRequest = 0;
					for(int k = 0; k < spList.size(); k++)
					{
						System.out.println("__San pham: " + spList.get(k).getMasp() + " -- Su dung:  " + spList.get(k).getSoluongcan() );
						
						if(sanpham_trongso.get(spList.get(k).getMasp()) != null)
						{
							if(sanpham_trongso.get(spList.get(k).getMasp()) > 0)
							{
								if(dkkm.getTongluong() > 0)
									totalRequest += spList.get(k).getSoluongcan();
								else
									totalRequest += spList.get(k).getSoluongcan() * spList.get(k).getDongia();
							}
						}
					}
					
					/*System.out.println("__Tong so luong yeu cau: " + totalRequest  );
					System.out.println("__Tong trong so yeu cau: " + ( totalRequest * 100 / tongluong ) );*/
					
					double trongso = ( totalRequest * 100 / tongluong );
					if ( trongso < dkkm.getTrongso() )
					{
						//System.out.println("__CTKM khong thoa trong so va so xuat se duoc cap nhat la: " );
						
						//tinh lai so xuat
						long soXuat = 0;
						
						if(totalRequest < minRequest)
						{
							soXuat = 0;
						}
						else
						{
							soXuat = Math.round( ( ( ctkm.getSoxuatKM() * totalRequest * 100 / tongluong ) / dkkm.getTrongso() ) - 0.5 );
						}

						if(soXuat > 0)
						{
							ctkm.setSoxuatKM((int)soXuat);
						}
						else
						{
							this.ctkmResult.remove(ctkm);
							i -= 1;
						}
					}
				}
			}
		}
		
		//bo 2 chiet khau trung nhau, chi lay chiet khau lon nhat ---->> OneOne
		//List<ICtkhuyenmai> ctkmBTCK = this.soCtkmCKBinhThuong(this.ctkmBTResult);
		//List<ICtkhuyenmai> ctkmBTCK = this.ctkmCKResult;
		
		List<ICtkhuyenmai> ctkmBTCK = this.soCtkmCKBinhThuong(this.ctkmResult, "1");
		
		//System.out.println("11-----.So CTKM CK Binh Thuong: " + ctkmBTCK.size());
		if(ctkmBTCK.size() >= 2)
		{
			int i = 0;
			while(i < ctkmBTCK.size())
			{
				ICtkhuyenmai ctCurrent = ctkmBTCK.get(i);
				ICtkhuyenmai ctDungChungdk = findCT_CK_CungDK(ctCurrent, ctkmBTCK);
				
				if(ctDungChungdk != null)
				{
					/*System.out.println("1___Toi tim thay ctkm: " + ctDungChungdk.getscheme());*/
					if(ctDungChungdk.getTrakhuyenmai().get(0).getChietkhau() > ctCurrent.getTrakhuyenmai().get(0).getChietkhau() )
					{
						this.ctkmResult.remove(ctCurrent);
						ctkmBTCK.remove(ctCurrent);
						i -= 1;
						/*System.out.println("2___Da go bo ctkm: " + ctCurrent.getscheme());*/
					}
					else
					{
						this.ctkmResult.remove(ctDungChungdk);
						ctkmBTCK.remove(ctDungChungdk);
						i -= 1;
						/*System.out.println("3___Da go bo ctkm tim they: " + ctDungChungdk.getscheme());*/
					}
				}
				i++;
			}
		}
		ctkmBTCK.clear();
		//System.out.println("4____Sau khi remove con: " + this.ctkmResult.size());
		
		
		//bo 2 chiet khau trung nhau, chi lay chiet khau lon nhat ---->> OneOne
		//List<ICtkhuyenmai> ctkmOT = this.soCtkmCKBinhThuong(this.ctkmOntopResult);
		
		List<ICtkhuyenmai> ctkmOT = this.soCtkmCKBinhThuong(this.ctkmResult, "2");
		if(ctkmOT.size() >= 2)
		{
			int i = 0;
			while(i < ctkmOT.size())
			{
				ICtkhuyenmai ctCurrent = ctkmOT.get(i);
				ICtkhuyenmai ctDungChungdk = findCT_CK_CungDK(ctCurrent, ctkmOT);
				
				if(ctDungChungdk != null)
				{
					//System.out.println("1___Toi tim thay ctkm: " + ctDungChungdk.getscheme());
					if(ctDungChungdk.getTrakhuyenmai().get(0).getChietkhau() > ctCurrent.getTrakhuyenmai().get(0).getChietkhau() )
					{
						this.ctkmResult.remove(ctCurrent);
						ctkmOT.remove(ctCurrent);
						i -= 1;
						//System.out.println("2___Da go bo ctkm: " + ctCurrent.getscheme());
					}
					else
					{
						this.ctkmResult.remove(ctDungChungdk);
						ctkmOT.remove(ctDungChungdk);
						i -= 1;
						//System.out.println("3___Da go bo ctkm tim thay: " + ctDungChungdk.getscheme());
					}
				}
				i++;
			}
		}
		ctkmOT.clear();
		
	}
		
	private ICtkhuyenmai findCT_CK_CungDK(ICtkhuyenmai ctCurrent, List<ICtkhuyenmai> ctkmBTCK)
	{
		for(int j = 0; j < ctkmBTCK.size(); j++)
		{
			ICtkhuyenmai ct = ctkmBTCK.get(j);
			
			if(!ct.getId().equals(ctCurrent.getId()))
			{	
				boolean flag = checkDkConfirm(ct.getDkkhuyenmai(), ctCurrent.getDkkhuyenmai());
				if(flag)
				{
					/*System.out.println("111___Tim duoc chuong trinh trung, ct Old: " + ctCurrent.getId() + " ----  Ct New Id: " + ct.getId());*/
					return ct;
				}
			}
		}
		
		return null;
	}

	private List<ICtkhuyenmai> soCtkmCKBinhThuong(List<ICtkhuyenmai> ctkmList, String type)
	{
		List<ICtkhuyenmai> ctkmCKBT = new ArrayList<ICtkhuyenmai>();
		for(int i = 0; i < ctkmList.size(); i++)
		{
			ITrakhuyenmai trakm = ctkmList.get(i).getTrakhuyenmai().get(0);

			if(trakm.getChietkhau() > 0 && ctkmList.get(i).equals(type) )
			{
				//if(checkDKTrongScheme(ctkmList.get(i), ctkmList))
				//{
					//System.out.println("3.Dieu kien chung chiet khau bi trung roi DK..., ct cu: " + ctkmList.get(i).getscheme());
					ctkmCKBT.add(ctkmList.get(i));
				//}
			}
		}
		//System.out.println("=====So CT chiet khau binh thuong tai buoc nay: " + ctkmCKBT.size());
		return ctkmCKBT;
	}
	
	private void ApOnTop() 
	{
		List<ICtkhuyenmai> schemeOnTopList = new ArrayList<ICtkhuyenmai>();
		if(this.dieuchinhKM)
		{
			//System.out.println("Vo day de check ne...\n");
			for(int i = 0; i < this.ctkmOntopList.size(); i++)
			{
				Ctkhuyenmai ct = (Ctkhuyenmai)this.ctkmOntopList.get(i);
				Hashtable<String, Double> copyHashC = copyHashtable(HashC);	
				Ctkhuyenmai ctkm = this.getSoxuattheoScheme(ct, copyHashC);
				
				if(ctkm.getSoxuatKM() > 0) //truong hop soxuat khuyenmai lon hon so xuat duoc huong toi da
				{
					double sx = ctkm.checkCtkm(tonggiatriDh);
					//System.out.println("So xuat check dc la: " + sx + "\n");
					if(sx > 0)
					{
						ctkm.setSoxuatKM(ctkm.checkCtkm(tonggiatriDh));
						schemeOnTopList.add(ctkm);
					}
				}
			}
		}
		else  //dieuchinh == false
		{
			for(int i=0; i < this.ctkmOntopList.size(); i++)
			{				
				Ctkhuyenmai ct = (Ctkhuyenmai)this.ctkmOntopList.get(i);
				Hashtable<String, Double> copyHashA = copyHashtable(HashC);				
				Ctkhuyenmai ctkm = this.getSoxuattheoScheme(ct, copyHashA);
				
				if(ctkm.getSoxuatKM() > 0)
				{
					if(ctkm.checkCtkm(tonggiatriDh) > 0)
					{
						ctkm.setSoxuatKM(ctkm.checkCtkm(tonggiatriDh));
						schemeOnTopList.add(ctkm);
					}
				}
			}
		}
		
		System.out.println("Chuong trinh ontop tong so: " + schemeOnTopList.size() + "\n");
		this.ctkmOntopResult = schemeOnTopList;
		if(this.dieuchinhKM == true)  //buoc nay doi lai, mot don hang co the huong nhieu ontop (neu khong dung chung 1 dk)
		{
			System.out.println("So khuyen mai ontop la: " + this.ctkmOntopResult.size() + "\n");
			/*
			if(this.ctkmOntopResult.size() > 1) //chi duoc 1 ontop trong list Ontop
			{
				int size = this.ctkmOntopResult.size();
				while(this.ctkmOntopResult.size() > 1)
				{
					this.ctkmOntopResult.remove(size-1);
					size--;
				}
			}*/
			
			//Neu 2 ontop cung su dung dieu kien thi chi duoc huong 1 ontop
					//1 don hang co the duoc huogn cung luc nhieu ontop khac dieu kien
			for(int i = 0; i < this.ctkmOntopResult.size() - 1; i++)
			{
				ICtkhuyenmai ctkmA = this.ctkmOntopResult.get(i);
				String dkkmAId = ctkmA.getDkkhuyenmai().get(0).getId();  //Sau nay ICP su dung nhieu dk tong hop cho 1 ontop thi phai sua lai
				System.out.println("Dieu kien khuyen mai A: " + dkkmAId);
				
				for(int j = i+1; j < this.ctkmOntopResult.size(); j++)
				{
					ICtkhuyenmai ctkmB = this.ctkmOntopResult.get(j);
					String dkkmBId = ctkmB.getDkkhuyenmai().get(0).getId();
					System.out.println("Dieu kien khuyen mai B: " + dkkmBId);
					
					if(dkkmAId.equals(dkkmBId))
					{
						this.ctkmOntopResult.remove(j);
						System.out.println("Da remove ctkm B: " + ctkmB.getscheme());
						i = 0;
						j = 0;
					}
				}
			}
		}
		System.out.println("Chuong trinh ontop sau do: " + schemeOnTopList.size() + "\n");
	}

	private void ApBinhThuong() 
	{
		List<ICtkhuyenmai> schemeList = new ArrayList<ICtkhuyenmai>();
		System.out.println("114.So CTKM binh thuong: " + this.ctkmBTList.size());
		if(this.dieuchinhKM)
		{
			for(int i = 0; i < this.ctkmBTList.size(); i++)
			{
				Ctkhuyenmai ct = (Ctkhuyenmai)this.ctkmBTList.get(i);
				Ctkhuyenmai ctkm = this.getSoxuattheoScheme(ct, HashA);
				
				/*Enumeration<String> keys = HashA.keys();
				while(keys.hasMoreElements())
				{
					String key = keys.nextElement();
					System.out.println("__Sau Dieu CHinh, San pham: " + key + ", Con lai: " + HashA.get(key));
				}*/
		
				if(ctkm.getSoxuatKM() > 0) //truong hop soxuat khuyenmai lon hon so xuat duoc huong toi da
				{
					if(ctkm.checkCtkm(tonggiatriDh) > 0)
					{
						ctkm.setSoxuatKM(ctkm.checkCtkm(tonggiatriDh));
						schemeList.add(ctkm);
					}
				}
			}
		}
		else  //dieuchinh == false
		{
			for(int i=0; i < this.ctkmBTList.size(); i++)
			{				
				Ctkhuyenmai ct = (Ctkhuyenmai)this.ctkmBTList.get(i);
				Hashtable<String, Double> copyHashA = copyHashtable(HashA);				
				Ctkhuyenmai ctkm = this.getSoxuattheoScheme(ct, copyHashA);
				
				if(ctkm.getSoxuatKM() > 0)
				{
					if(ctkm.checkCtkm(tonggiatriDh) > 0)
					{
						ctkm.setSoxuatKM(ctkm.checkCtkm(tonggiatriDh));
						schemeList.add(ctkm);
					}
				}
			}
		}
		
		System.out.println("chuong tring binh thuong: " + schemeList.size() + "\n");
		this.ctkmBTResult = schemeList;
	}
	
	public Ctkhuyenmai getSoxuattheoScheme(Ctkhuyenmai ctkm, Hashtable<String, Double> hashSanpham)
	{
		Ctkhuyenmai ctkhuyenmai = new Ctkhuyenmai();
		List<IDieukienkhuyenmai> dkkmList = ctkm.getDkkhuyenmai();
			
		//Xu ly tat cac cac dieukienkhuyenmai cua ctkhuyenmai
		ArrayList<Boolean> dieukien = new ArrayList<Boolean>(); //luu cac ket qua dieukien
		ArrayList<String> pheptoan = new ArrayList<String>(); //luu cac pheptoan
		int soXuatKM = 0;
		
		Hashtable<String, Double> copySp = copyHashtable(hashSanpham);
		long tongtientheodk = 0;
		long tong_diem_theo_dk = 0;
		System.out.println("hashSanpham: "+hashSanpham);
		for(int i = 0; i < dkkmList.size(); i++)
		{
			Dieukienkhuyenmai dkkm = (Dieukienkhuyenmai)dkkmList.get(i);
			
			System.out.println("[DKKM]"+dkkm.getDiengiai());
			
			//List<ISanpham> sanphamList = new ArrayList<ISanpham>();
			
			String pt = ((dkkm.getPheptoan() == 1) ? "&&" : "||");
			pheptoan.add(pt);

			boolean flag = false;
			int sl = 0;
			long ttTheodk = 0;
			long td_theo_dk = 0;			
			if(dkkm.getTongtien() > 0) 								//check tong tien co hop dieu kien ko??
			{
				//System.out.println("1111111.Thu tu Tong tien");
				String[] str = checkDKKM_TongTien(dkkm, HashB, hashSanpham).split("-");
				sl = Integer.parseInt(str[0]);
				ttTheodk = Long.parseLong(str[1]);
			}
			else   
			{
				if(dkkm.getTongluong() > 0) //check tong soluong sp trong dkkm
				{
/*					*/
					/*sl = checkDKKM_TongLuong(dkkm, hashSanpham);*/
					String[] str = checkDKKM_TongLuong(dkkm, HashB, hashSanpham).split("-");
					sl = Integer.parseInt(str[0]);
					ttTheodk = Long.parseLong(str[1]);
					System.out.println("2222222.Thu tu Tong luong:" + str[0] +";" + str[1]);
					if(str.length >=3)
					{
						System.out.println("2222222.Thu tu t???ng ??i???m theo ch????ng tr??nh:" + str[2] );
						td_theo_dk = Long.parseLong(str[2]);
					}
				}
				else												//check so luong cu the tung san pham trong dkkm
				{
					//sl = checkDKKM_SP(dkkm, hashSanpham);
					String[] str = checkDKKM_SP(dkkm, HashB, hashSanpham).split("-");
					sl = Integer.parseInt(str[0]);
					ttTheodk = Long.parseLong(str[1]);
					if(str.length >=3)
					{
						System.out.println("2222222.Thu tu t???ng ??i???m theo ch????ng tr??nh:" + str[2] );
						td_theo_dk = Long.parseLong(str[2]);
					}
				}
			}
			System.out.println("113.So xuat KM khi dang xet scheme ( " + ctkm.getscheme() + " ) la: " + sl);
			if(sl > 0)
			{
				flag = true;
				ctkm.getDkkhuyenmai().get(i).setSoxuatKM(sl);	
				tongtientheodk += ttTheodk;
				tong_diem_theo_dk  += td_theo_dk;
				System.out.println("113.So xuat KM khi dang xet scheme ( " + ctkm.getscheme() + " ) la: " + sl);
				if(this.dieuchinhKM == true) //by danhpc 20/10
					sl = min(ctkm.getSoxuatKM_Dieuchinh(), sl);
				
				this.dieuChinhKM((Dieukienkhuyenmai)ctkm.getDkkhuyenmai().get(i), hashSanpham, HashB, sl, ctkm);
								
				if(i > 0 && pt == "&&") //dieu kien and cac sanpham
				{
					//cap nhat lai so xuat km
					soXuatKM = min(soXuatKM, sl);
					if(soXuatKM > ctkm.getSoxuatKM_TOIDA())
						soXuatKM = ctkm.getSoxuatKM_TOIDA();
					
					for(int j = i; j >= 0; j--)
					{							
						//dieu chinh lai slg sanpham trong hashSanpham (phai thay doi theo soxuat)
						this.dieuChinhKM2((Dieukienkhuyenmai)ctkm.getDkkhuyenmai().get(j), hashSanpham, soXuatKM);
					}
				}
				else //dieu kien or
				{
					soXuatKM += sl;
				}
			}
			dieukien.add(flag);	
		}	
		
		//tong hop cac dieu kien
		ctkhuyenmai.setId(ctkm.getId());
		ctkhuyenmai.setscheme(ctkm.getscheme());	
		ctkhuyenmai.setTungay(ctkm.getTungay());
		ctkhuyenmai.setDenngay(ctkm.getDenngay());
		ctkhuyenmai.setLoaict(ctkm.getLoaict());
		ctkhuyenmai.setNgansach(ctkm.getNgansach());
		ctkhuyenmai.setDasudung(ctkm.getDasudung());
		ctkhuyenmai.setDiengiai(ctkm.getDiengiai());
		ctkhuyenmai.setTra_OR(ctkm.getTra_OR());
		ctkhuyenmai.setPhanbotheoDH(ctkm.getPhanbotheoDH());
		ctkhuyenmai.setSoxuatKM_TOIDA(ctkm.getSoxuatKM_TOIDA());
		ctkhuyenmai.setSotienKM_TOIDA(ctkm.getSotienKM_TOIDA());
		ctkhuyenmai.setLoaingansach(ctkm.getLoaingansach());
		//System.out.println("Tong tien theo dieu kien khuyen mai la: " + tongtientheodk + "\n");
		
		if((checkDieuKien(dieukien, pheptoan) == false))
		{				
			if(this.dieuchinhKM == true)  //under change, phai thay doi truc tiep HashA, ko thong wa tham chieu duoc...
			{
				if(ctkm.getLoaict() == 1 ) //khuyen mai binh thuong
					this.HashA = copyHashtable(copySp);
				else
					this.HashC = copyHashtable(copySp);
			}
			ctkhuyenmai.setSoxuatKM(0);
			ctkhuyenmai.setTongTienTheoDKKM(0);
			ctkhuyenmai.setTong_diem_theo_dkkm(0);
		}
		else
		{
			if(soXuatKM > ctkm.getSoxuatKM_TOIDA())
				soXuatKM = ctkm.getSoxuatKM_TOIDA();
			
			ctkhuyenmai.setSoxuatKM(soXuatKM);
			ctkhuyenmai.setTongTienTheoDKKM(tongtientheodk); //truong hop tra chiet khau
			ctkhuyenmai.setTong_diem_theo_dkkm(tong_diem_theo_dk);// truong hop tra ti???n theo s??? ??i???m;
			ctkhuyenmai.setDkkhuyenmai(ctkm.getDkkhuyenmai());
			ctkhuyenmai.setTrakhuyenmai(ctkm.getTrakhuyenmai());
		}	
		return ctkhuyenmai;
	}
		
	public Hashtable<String, Double> copyHashtable(Hashtable<String, Double> hashSanpham)
	{
		Hashtable<String, Double> copy = new Hashtable<String, Double>();		
		Enumeration<String> keys = hashSanpham.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			copy.put(key, hashSanpham.get(key));
		}
		return copy;
	}
	
	private String checkDKKM_SP(Dieukienkhuyenmai dkkm, Hashtable<String,Float> sanpham_dongia, Hashtable<String, Double> hashSanpham)
	{
		int soxuatKm = 0;
		float tongtientheoDK = 0;
		float tong_diem_theo_dk = 0;
		Hashtable<String,Integer> sp_sl = dkkm.getSanpham_Soluong();
	/*	System.out.println("kich thuoc mang san pham don hang: " + sanpham.size());
		System.out.println("kich thuoc mang san pham dieu kien khuyen mai: " + sp_sl.size());*/
		if(sp_sl.size() > 0)
		{			
			if(hashSanpham.size() < sp_sl.size())
				return "0-0";
			
			double min = Integer.MAX_VALUE; 
			Enumeration<String> keyList = sp_sl.keys();
			while(keyList.hasMoreElements())
			{
				String key = keyList.nextElement();
				if(hashSanpham.get(key) == null)
					return "0-0"; //khong co san pham nay trong dkkhuyenmai bat buoc
				else
				{
					Double soSpA = (double) 0;
					if(dkkm.getIsThung().equals("0"))
					{
						soSpA = hashSanpham.get(key);
					}
					else if(dkkm.getIsThung().equals("1"))
					{
						soSpA = hashSanpham.get(key) / this.Hash_QuyCach.get(key);
					}
					else if(dkkm.getIsThung().equals("2"))
					{
						soSpA = hashSanpham.get(key) *  dkkm.getSanpham_Soluong().get(key);
					}
					
					float soSpPhaiThoa = sp_sl.get(key);
					
					if(soSpA < soSpPhaiThoa)
						return "0-0";	
					else
					{
						double k = soSpA / soSpPhaiThoa;
						
						tongtientheoDK += hashSanpham.get(key) * sanpham_dongia.get(key);
						tong_diem_theo_dk += hashSanpham.get(key) * dkkm.getSanpham_Soluong().get(key);
						if(k < min)
							min = k;
					}
				}
			}				
			soxuatKm = (int)min;	
		}	
		//long tongtiendh = Math.round(tongtientheoDK * 1.1);
		long tongtiendh = Math.round(tongtientheoDK);
		long tongdiem =  Math.round(tong_diem_theo_dk);
		return Integer.toString(soxuatKm) + "-" + Long.toString(tongtiendh)+ "-" + Long.toString(tongdiem);
	}
	
	private String checkDKKM_TongLuong(Dieukienkhuyenmai dkkm, Hashtable<String,Float> sanpham_dongia, Hashtable<String, Double> hashSanpham)
	{
		double soxuatKm = 0;
		double tongtientheoDK = 0;
		double tongdiemtheoDK = 0;
		Hashtable<String,Integer> sanpham = dkkm.getSanpham_Soluong(); //phai la nhung san pham trong dkkm truoc
		/*System.out.println("[spList]"+sanpham.size() +"[getSanpham_Soluong]" + dkkm.getSanpham_Soluong()+"[getIsThung]"+dkkm.getIsThung());*/
		if(sanpham.size() > 0)
		{
			float tongluong = (float)dkkm.getTongluong();
			/*System.out.print("Tong Luong Theo DKKM: " + tongluong + "\n");*/
			float sum = 0;
			Double minSP = (double) Integer.MAX_VALUE; //soxuat bat buoc phai co mat day du cac sanpham trong dkkm
			Enumeration<String> keyList = sanpham.keys();	
			while(keyList.hasMoreElements())
			{
				String key = keyList.nextElement();
				/*System.out.println("[sanpham]"+sanpham.get(key)+"[Key]"+key);*/
				if(dkkm.getType() == 2)
				{
					//qui dinh mua bat ky sanpham a, sanpham b, sanpham c.... trong nhom ABC dat tongluong = xyz
					if(hashSanpham.get(key) != null)
					{
						if(dkkm.getIsThung().equals("0"))
						{
							sum += hashSanpham.get(key) ;
							System.out.println("[Sum]"+sum+"[Key]"+key);
						}
						else if(dkkm.getIsThung().equals("1"))
						{
							sum += hashSanpham.get(key) / this.Hash_QuyCach.get(key);
						}
						else if(dkkm.getIsThung().equals("2")) // ??i???m
						{
							sum += hashSanpham.get(key)  * dkkm.getSanpham_Soluong().get(key);
						}
						
						
						
						tongtientheoDK += hashSanpham.get(key) * sanpham_dongia.get(key);
						if(dkkm.getIsThung().equals("2"))
							tongdiemtheoDK +=  hashSanpham.get(key)* dkkm.getSanpham_Soluong().get(key);
					}
				}
				else
				{
					//qui dinh mua dung sanpham a, sanpham b, sanpham c trong nhom ABC dat tongluong = xyz
					if(!hashSanpham.containsKey(key))
						return "0-0";
					if(hashSanpham.get(key) <= 0)
						return "0-0";	
					
					if(dkkm.getIsThung().equals("0"))
					{
						sum += hashSanpham.get(key);
					}
					else if(dkkm.getIsThung().equals("1"))
					{
						sum += hashSanpham.get(key) / this.Hash_QuyCach.get(key);
					}
					else if(dkkm.getIsThung().equals("2")) // ??i???m
					{
						sum += hashSanpham.get(key)  * dkkm.getSanpham_Soluong().get(key);
					}
					
					tongtientheoDK += hashSanpham.get(key) * sanpham_dongia.get(key);
					
					if(dkkm.getIsThung().equals("2"))
						tongdiemtheoDK +=  hashSanpham.get(key)* dkkm.getSanpham_Soluong().get(key);
					
					if(hashSanpham.get(key) < minSP)
						minSP = hashSanpham.get(key);
				}				
			}
			/*System.out.print("dk khuyen mai " + dkkm.getId() + " ---- Tong thung " + sum + ", Phai dat: " + tongluong + "\n");*/
			if(sum < tongluong)
				return "0-0";
			soxuatKm = min((int)(sum / tongluong), minSP);
		}
		//long tongtiendh = Math.round(tongtientheoDK * 1.1);
		long tongtiendh = Math.round(tongtientheoDK);
		long tongdiemdh = Math.round(tongdiemtheoDK);
		/*System.out.println("TOng gia tri cua cac san pham mua la: " + tongtiendh);*/
		return Integer.toString((int) soxuatKm) + "-" + Long.toString(tongtiendh)+ "-" + Long.toString(tongdiemdh);
	}
		
	private String checkDKKM_TongTien(Dieukienkhuyenmai dkkm, Hashtable<String,Float> sanpham_dongia, Hashtable<String, Double> hashSanpham)
	{
		/*Enumeration<String> keys = sanpham_soluong.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			System.out.println("__________San pham: " + key + ", So luong: " + sanpham_soluong.get(key));
		}*/
		
		double soxuatKm = 0;
		long tongtientheodk = 0;
		Hashtable<String,Integer> sanpham = dkkm.getSanpham_Soluong(); //phai la nhung san pham trong dkkm truoc
		if(sanpham.size() > 0)
		{
			float tongtien = dkkm.getTongtien();
			float sum = 0F;			
			Double minSP = (double) Integer.MAX_VALUE; //soxuat bat buoc phai co mat day du cac sanpham trong dkkm
			
			Enumeration<String> keyList = sanpham.keys();
			while(keyList.hasMoreElements())
			{
				String key = keyList.nextElement();
				
				System.out.println("dkkm.getType(): "+dkkm.getType());
				if(dkkm.getType() == 2)
				{
					System.out.println("sanpham_dongia.get(key): "+sanpham_dongia.get(key));
					//Mua 1, 2, 3...sp bat ky trong nhom san pham
					if(sanpham_dongia.get(key) != null)
					{
						System.out.println("-----Su dung san pham: " + key + " -- So luong: " + hashSanpham.get(key) + " -- Don gia: " + sanpham_dongia.get(key));
						sum += sanpham_dongia.get(key) * hashSanpham.get(key);
					}
				}
				else
				{
					//dk mua sp qui dinh trong nhom sp dat sotien > y  (co dinh san pham)
					if(!sanpham_dongia.containsKey(key))// san pham ko ton tai trong list --> loai
						return "0-0"; //so xuat - tong tien theo dieu kien
					if(hashSanpham.get(key) <= 0)
						return "0-0";
					sum += sanpham_dongia.get(key) * hashSanpham.get(key); //tong so tien cua tat ca sp trong don hang
					
					if(hashSanpham.get(key) < minSP)
						minSP = hashSanpham.get(key);
				}
			}
			
			//double tongtiendonhang = sum * 1.1;
			double tongtiendonhang = sum;
			tongtientheodk = Math.round(tongtiendonhang);
			System.out.println("Tong tien theo dieu kien khuyen mai (" + dkkm.getDiengiai() + ") trong ham la: " + tongtientheodk + "\n");
			
			if(Math.round(tongtiendonhang) < tongtien)
				return "0-0";
			soxuatKm = min((int)(Math.round(tongtiendonhang) / tongtien), minSP);
		}				
		//return soxuatKm;
		return Integer.toString((int) soxuatKm) + "-" + Long.toString(tongtientheodk);
	}
	
	private boolean checkDieuKien(ArrayList<Boolean> dieukien, ArrayList<String> pheptoan)
	{
		if(dieukien.size() <= 1)
		{
			if(dieukien.contains(true))
				return true;
			return false;
		}	
		int i = 0;
		while( i < dieukien.size())
		{
			int j = i + 1;
			if(j < pheptoan.size())
			{
				if(pheptoan.get(j).equals("&&"))
					dieukien.set(j, dieukien.get(i) && dieukien.get(j));
				else
					dieukien.set(j, dieukien.get(i) || dieukien.get(j));
			}
			i++;
		}
		return dieukien.get(dieukien.size() - 1);
	}
	
	private void dieuChinhKM(Dieukienkhuyenmai dkkm, Hashtable<String, Double> sp_sl, Hashtable<String, Float> sp_dongia, double sl, ICtkhuyenmai ctkm)
	{
		Hashtable<String, Integer> sanpham_sl = dkkm.getSanpham_Soluong();
		List<ISanpham> sanphamList = new ArrayList<ISanpham>();
		
		if(dkkm.getTongtien() <= 0)
		{
			if(dkkm.getTongluong() > 0) //truong hop dkkm co set tongluong
			{		
				Sanpham[] sanpham = getSanpham_slg(dkkm, sp_sl, sl);
				
				/*for(int i = 0; i < sanpham.length; i++)
				{
					System.out.println("----4444-----Dieu kien: " + dkkm.getDiengiai() + " : su dung " + sanpham[i].getMasp() + ", voi so luong: " + sanpham[i].getSoluongThungCan() );
				}*/
				
				if(sanpham != null)
				{
					for(int i=0; i < sanpham.length; i++)
					{
						String key = sanpham[i].getMasp();
						if(sp_sl.containsKey(key))
						{
							double slgconlai = 0;
							double soluongsudung = 0;
							
							if(dkkm.getIsThung().equals("0"))
							{
								slgconlai = ( sp_sl.get(key) -  sanpham[i].getSoluongThungCan() );
								soluongsudung = (sanpham[i].getSoluongThungCan());
							}
							else
								if(dkkm.getIsThung().equals("2"))
								{
									slgconlai = (sp_sl.get(key) - ( sanpham[i].getSoluongThungCan() / dkkm.getSanpham_Soluong().get(key) ));
									soluongsudung = (sanpham[i].getSoluongThungCan() / dkkm.getSanpham_Soluong().get(key) );
								}
								else
								{
									slgconlai = (sp_sl.get(key) - ( sanpham[i].getSoluongThungCan() * ( this.Hash_QuyCach.get(key)) ));
									soluongsudung = (sanpham[i].getSoluongThungCan() * this.Hash_QuyCach.get(key));
								}
							
							//System.out.print("110.San pham " + key + " --- Slg ban dau: " + sp_sl.get(key) + " --- Con lai " + slgconlai + "\n");
							
							//luu lai thong tin sl sanpham su dung, sl con lai
							if(ctkm.getTrakhuyenmai().get(0).getChietkhau() > 0)
							{
								System.out.println("-----------------CTKM nay la CK-------------------------------");
								slgconlai = 0;
								soluongsudung= sp_sl.get(key);
							}
							if(ctkm.getTrakhuyenmai().get(0).getType()==4)// t??nh ti???n theo ??i???m
							{
								System.out.println("-----------------CTKM nay la ??i???m-------------------------------");
								slgconlai = 0;
								soluongsudung= sp_sl.get(key);
							}
							
							Sanpham sp = new Sanpham(key, sanpham[i].getTensp(), soluongsudung, sp_dongia.get(key), slgconlai, true);
							
							//System.out.print("111.San pham " + sp.getMasp() + " --- Slg can " + sp.getSoluongcan() + " --- Avaiable " + sp.getSoluongAvaiable() + "\n");
							System.out.print("111.San pham " + key + " --- Slg su dung " + soluongsudung + " --- Avaiable " + slgconlai + "\n");
							
							sp_sl.put(key,  slgconlai); //dieu chinh lai soluong tuong ung
							sanphamList.add(sp);
							
						}
					}
				}
			}
			else
			{
				Enumeration<String> keyList = sanpham_sl.keys();
				while(keyList.hasMoreElements())
				{
					String key = keyList.nextElement();
					if(sp_sl.containsKey(key))
					{
						double slgconlai = 0;
						double soluongsudung = 0;
						
						if(dkkm.getIsThung().equals("0"))
						{
							slgconlai = sp_sl.get(key) - sanpham_sl.get(key) * sl;
							soluongsudung = sanpham_sl.get(key) * sl;
						}
						else
						{
							slgconlai = (int) (sp_sl.get(key) - sanpham_sl.get(key) * this.Hash_QuyCach.get(key) * sl);
							soluongsudung = (int)( sanpham_sl.get(key) * this.Hash_QuyCach.get(key) * sl );
							
						}
						
						//luu lai thong tin sl sanpham su dung, sl con lai
						ResultSet rs = db.get("select ten from sanpham where ma='" + key + "'");
						String tensp = "";
						try 
						{
							rs.next();
							tensp = rs.getString("ten");
							rs.close();
						} 
						catch (SQLException e) {}
						
						if(ctkm.getTrakhuyenmai().get(0).getChietkhau() > 0)
						{
							System.out.println("-----------------CTKM nay la CK-------------------------------");
							slgconlai = 0;
							soluongsudung= sp_sl.get(key);
						}
						if(ctkm.getTrakhuyenmai().get(0).getType() == 4)
						{
							System.out.println("-----------------CTKM nay la ??i???m-------------------------------");
							slgconlai = 0;
							soluongsudung= sp_sl.get(key);
						}
						
						Sanpham sp = new Sanpham(key, tensp, soluongsudung, sp_dongia.get(key), slgconlai, false);
						
						sp_sl.put(key, slgconlai);
						sanphamList.add(sp);
					}
				}
			}
		}
		else //truong hop tong tien
		{
			Sanpham[] sanpham = getSanpham_soluong(dkkm, sp_sl, sl);
			if(sanpham != null)
			{
				for(int i=0; i < sanpham.length; i++)
				{
					String key = sanpham[i].getMasp();
					if(sp_sl.containsKey(key))
					{
						/*int slgconlai = sp_sl.get(key) - sanpham[i].getSoluongcan();
						//luu lai thong tin sl sanpham su dung, sl con lai
						Sanpham sp = new Sanpham(key, sanpham[i].getTensp(), sanpham[i].getSoluongcan(), sp_dongia.get(key), slgconlai, false);*/
						
						double slgconlai = 0;
						double soluongsudung = 0;
						
						if(dkkm.getIsThung().equals("0"))
						{
							/*slgconlai = (int) ( sp_sl.get(key) -  sanpham[i].getSoluongThungCan() );
							soluongsudung = (int)(sanpham[i].getSoluongThungCan());*/
							slgconlai = sp_sl.get(key) - sanpham[i].getSoluongcan();
							soluongsudung = sanpham[i].getSoluongcan();
						}
						else
						{
							slgconlai = (int) (sp_sl.get(key) - ( sanpham[i].getSoluongThungCan() * ( this.Hash_QuyCach.get(key)) ));
							soluongsudung = (int)(sanpham[i].getSoluongThungCan() * this.Hash_QuyCach.get(key));
							
							System.out.print("110.San pham " + key + " --- Slg thung can " + sanpham[i].getSoluongThungCan() + " --- Quy Cach " + this.Hash_QuyCach.get(key) + "\n");
						}
						
						if(ctkm.getTrakhuyenmai().get(0).getChietkhau() > 0)
						{
							System.out.println("-----------------CTKM nay la CK-------------------------------");
							slgconlai = 0;
							soluongsudung= sp_sl.get(key);
						}
						if(ctkm.getTrakhuyenmai().get(0).getType()==4)// t??nh ti???n theo ??i???m
						{
							System.out.println("-----------------CTKM nay la ??i???m-------------------------------");
							slgconlai = 0;
							soluongsudung= sp_sl.get(key);
						}
						
						Sanpham sp = new Sanpham(key, sanpham[i].getTensp(), soluongsudung, sp_dongia.get(key), slgconlai, false);
						
						//System.out.print("\n" + sanpham[i].getTensp() + " -- " + sanpham[i].getSoluongcan() + "\n");
						
						System.out.print("111.San pham " + key + " --- Slg su dung " + soluongsudung + " --- Avaiable " + slgconlai + "\n");
						
						sp_sl.put(key,  slgconlai);
						sanphamList.add(sp);
					}
				}
			}
		}
		
		for(int i = 0; i < sanphamList.size(); i++)
		{
			ISanpham sp = sanphamList.get(i);
			System.out.println("3333.Dieu kien Id: " + dkkm.getId() + ", Su dung san pham: " + sp.getMasp() + ", Voi so luong: " + sp.getSoluongcan() + ", Thung tuong ung: " + sp.getSoluongThungAvaiable());
		}
		
		dkkm.setSanphamList(sanphamList);
	}
	
	private void dieuChinhKM2(Dieukienkhuyenmai dkkm,  Hashtable<String, Double> hashSanpham, int soXuatKM)
	{
		double old = dkkm.getSoxuatKM();
		List<ISanpham> sanpham = dkkm.getSanphamList();
		
		for(int i = 0; i < sanpham.size(); i++ )
		{
			Sanpham sp = (Sanpham)sanpham.get(i);
			System.out.println("dkkm type : "+ dkkm.getType());
			if(dkkm.getType() == 2 ) //mua san pham co slg bat ky trong nhom san pham...
			{
				if(dkkm.getTongluong() > 0)
				{
					double sum = dkkm.getTongluong() * soXuatKM;
					if(sum > sp.getSoluongcan())
						sum = sum - sp.getSoluongcan();
					else
					{						
						double slgCan = sp.getSoluongcan();
						double avai = sp.getSoluongAvaiable();
						sp.setSoluongcan(sum);
						sp.setSoluongAvaiable(avai + slgCan - sum);
						
						//System.out.print(sp.getMasp() + " ---- " + sp.getSoluongcan() + " ----- " + sp.getSoluongAvaiable() + "\n"); 
						
						//dieu chinh lai hashSanpham
						hashSanpham.put(sp.getMasp(), (double) (hashSanpham.get(sp.getMasp()) + slgCan - sum));
						
						for(int j = i + 1; j < sanpham.size(); j++)
						{
							double slgCanOld =  sanpham.get(j).getSoluongcan();
							sanpham.get(j).setSoluongcan(0);
							sanpham.get(j).setSoluongAvaiable(sanpham.get(j).getSoluongAvaiable() + slgCanOld);
							
							hashSanpham.put(sanpham.get(j).getMasp(), (double) (hashSanpham.get(sanpham.get(j).getMasp()) + slgCanOld));
							
							//System.out.print("So luong can Old: " + Integer.toString(slgCanOld) + " ---- Masp: " + sanpham.get(j).getMasp() + " ---- " + sanpham.get(j).getSoluongcan() + " ----- " + sanpham.get(j).getSoluongAvaiable() + "\n"); 
						}
						dkkm.setSoxuatKM(soXuatKM);
						return;
					}
				}
			}
			else //mua sp co soluong bat buoc
			{
				double slgCan =  (sp.getSoluongcan() / old);
				double sum =  (sp.getSoluongcan() + sp.getSoluongAvaiable());
				
				dkkm.getSanphamList().get(i).setSoluongcan(slgCan * soXuatKM);
				dkkm.getSanphamList().get(i).setSoluongAvaiable(sum - (slgCan * soXuatKM));
				
				//dieu chinh lai hashSanpham
				hashSanpham.put(sp.getMasp(), (double) (hashSanpham.get(sp.getMasp()) + (old - soXuatKM) * slgCan));
			}
			//System.out.print("So luong sau dieu chinh 2 la: " + sp.getMasp() + " --- " + Integer.toString(slgCan * soXuat) + "\n");
		}
		dkkm.setSoxuatKM(soXuatKM);
	}
	
	private Sanpham[] InitSanPham(Dieukienkhuyenmai dkkm, Hashtable<String, Double> sp_sl2, boolean flag)
	{
		Hashtable<String, Integer> sp_sl = dkkm.getSanpham_Soluong();
		Hashtable<String, Float> sp_trongso = dkkm.getSanpham_Trongso();
		
		Sanpham[] sanpham = new Sanpham[sp_sl.size()];

		int m = 0;
		Enumeration<String> keyList = sp_sl.keys();
		while(keyList.hasMoreElements())
		{
			String key = keyList.nextElement();
			Double slAvaiable = (double) 0;
			if(sp_sl2.get(key) != null)
				slAvaiable = sp_sl2.get(key);
			
			Double slThungAvai = (double) 0;
			if(sp_sl2.get(key) != null)
			{
				if(dkkm.getIsThung().equals("0"))
					slThungAvai = sp_sl2.get(key);
				else if(dkkm.getIsThung().equals("2")) // theo ??i???m
				{
					slThungAvai = sp_sl2.get(key) * dkkm.getSanpham_Soluong().get(key);
				}
				else
				{
					slThungAvai = sp_sl2.get(key) / this.Hash_QuyCach.get(key);
				}
			}
			
			float dongia = 0f;
			if(HashB.get(key) != null)
				dongia = HashB.get(key);
			
			ResultSet rs = db.get("select ten from sanpham where ma='" + key + "'");
			String tensp = "";
			try {
				rs.next();
				tensp = rs.getString("ten");
				rs.close();
			} catch (SQLException e) {}
						
			if(dkkm.getType() == 2) //sp co soluong bat ky
			{
				sanpham[m] = new Sanpham(key, tensp, 0, dongia, slAvaiable, flag);
				sanpham[m].setSoluongThungAvaiable(slThungAvai);
				sanpham[m].setSoluongThungCan(0);
				sanpham[m].setTrongso(sp_trongso.get(key));
			}
			else
			{
				sanpham[m] = new Sanpham(key, tensp, 1, dongia, slAvaiable, flag); //toi thieu phai co 1sp de thoa dk	
				sanpham[m].setSoluongThungAvaiable(slThungAvai);
				sanpham[m].setSoluongThungCan(1);
				sanpham[m].setTrongso(sp_trongso.get(key));
			}
			m++;					
		}
		
		Arrays.sort(sanpham);
		
		/*for(int i = 0; i < sanpham.length; i++)
		{
			System.out.println("__Thu tu: " + i + " -- " + sanpham[i].getMasp());
		}*/

		return sanpham;
	}
	
	private boolean checkMaSP(String key, List<ISanpham> spList)
	{
		for(int i = 0; i < spList.size(); i++)
		{
			if(spList.get(i).getMasp().equals(key) )
				return true;
		}
		return false;
	}
	/******** End Chua dung toi *********/
	
	
	private Sanpham[] getSanpham_soluong(Dieukienkhuyenmai dkkm, Hashtable<String, Double> sp_sl, double sl) //truong hon tong tien > 0
	{
		Sanpham[] sanpham = InitSanPham(dkkm, sp_sl, false); //Sort sanpham tang dan theo tongtien
		if(sanpham == null)
			return null;
		
		double tongtienKM = dkkm.getTongtien() * sl; //tong tien theo tat ca cac xuat khuyenmai duoc huong
		
		for(int i = 0; i < sanpham.length; i++)
		{
			float sum = getTongtien(sanpham);
			sum = (float) (sum - (sanpham[i].getDongia() * sanpham[i].getSoluongcan()));
			
			double soluongcan = min(sanpham[i].getSoluongAvaiable(), (tongtienKM - sum) / sanpham[i].getDongia() );
			sanpham[i].setSoluongcan(soluongcan);
			/*
			if(i==(sanpham.length - 1))
			{
				if(getTongtien(sanpham) < tongtienKM)
					sanpham[i].setSoluongcan(sanpham[i].getSoluongcan() + 1);
			}
			*/
		}
		return sanpham;
	}
	
	public Sanpham[] getSanpham_slg(Dieukienkhuyenmai dkkm, Hashtable<String, Double> sp_sl, double sl) //truong hop tongluong > 0
	{
		Sanpham[] sanpham = InitSanPham(dkkm, sp_sl, true); //Sort sanpham tang dan theo trong so & soluong

		if(sanpham == null)
			return null;
		
		float tongluongKM = (float) (dkkm.getTongluong() * sl); //tong luong theo tat ca cac xuat khuyenmai duoc huong
		
		for(int i = 0; i < sanpham.length; i++)
		{
			double sum = getTongluong(sanpham);
			
			sum = sum - sanpham[i].getSoluongThungCan();
			
			System.out.println("113. Sum: " + sum + ", Tong luong: " + tongluongKM + ", Avai:  " + sanpham[i].getSoluongThungAvaiable());
			double soluongcan = min(sanpham[i].getSoluongThungAvaiable(), (double) (tongluongKM - sum));
			sanpham[i].setSoluongThungCan(soluongcan);
			
			//System.out.print("115.San pham " + sanpham[i].getMasp() + " --- Slg can " + sanpham[i].getSoluongcan() + " --- Avaiable " + sanpham[i].getSoluongAvaiable() + "\n");
			//System.out.print("---116.San pham " + sanpham[i].getMasp() + " --- Slg can " + sanpham[i].getSoluongThungCan()+ " --- Avaiable " + sanpham[i].getSoluongThungAvaiable() + "\n");
			if(i==(sanpham.length - 1))
			{
				if(getTongluong(sanpham) < tongluongKM)
				{
					if(dkkm.getIsThung().equals("0"))
					{
						sanpham[i].setSoluongcan(sanpham[i].getSoluongcan() + 1);
					}
				}
			}
		}
		return sanpham;
	}
	
	private double min(double soXuatKM, Double minSP)
	{
		return (soXuatKM >= minSP ? minSP : soXuatKM);
	}
	
	private int min(int a, int b)
	{
		return (a >= b ? b : a);
	}
	
	private float getTongtien(Sanpham[] sanpham)
	{
		float sum = 0f;
		for(int j = 0; j < sanpham.length; j++)
		{
			sum += (sanpham[j].getSoluongcan() * sanpham[j].getDongia());
		}
		return sum;
	}
	
	private float getTongluong(Sanpham[] sanpham)
	{
		float sum = 0;
		for(int j = 0; j < sanpham.length; j++)
		{
			sum += (sanpham[j].getSoluongThungCan());
		}
		return sum;
	}
	
	//Sort theo Scheme nao uu tien truoc
	public List<ICtkhuyenmai> SortList(List<ICtkhuyenmai> list, String[] scheme, String[] soxuatKm)
	{
		//List<ICtkhuyenmai> list = List;
		List<ICtkhuyenmai> ctkmList = new ArrayList<ICtkhuyenmai>();
		int k = 0;
		for(int i = 0; i < scheme.length; i++)
		{
			for(int j = 0; j < list.size(); j++)
			{
				Ctkhuyenmai ctkm = (Ctkhuyenmai)list.get(j);
				if(ctkm.getscheme().equals(scheme[i]))
				{
					ctkm.setSoxuatKM_Dieuchinh(Integer.parseInt(soxuatKm[i].trim()));
					ctkmList.add(k, ctkm);
					k ++;
					//list.remove(j);
				}
			}
		}		
		for(int j = 0; j < list.size(); j++)
		{
			Ctkhuyenmai ctkm = (Ctkhuyenmai)list.get(j);
			if(!ctkmList.contains(ctkm))
			{ 
				ctkm.setSoxuatKM_Dieuchinh(999999999);
				ctkmList.add(ctkm);
			}
		}

		System.out.println("7878787878. SO CTKM SAU KHI SORT: " + ctkmList.size());
		return ctkmList;
	}
	
	public Hashtable<String, Double> getHashA()
	{
		return this.HashA;
	}
	
	public void setHashA(Hashtable<String, Double> hash)
	{
		this.HashA = hash;
		
		//dung trong truong hop Ontop
		this.HashC = this.copyHashtable(HashA);
	}
	
	public Hashtable<String,Float> getHashB()
	{
		return this.HashB;
	}
	
	public void setHashB(Hashtable<String,Float> hash)
	{
		this.HashB = hash;
	}
	
	public Hashtable<String,Float> getHash_QuyCach()
	{
		return this.Hash_QuyCach;
	}
	
	public void setHash_QuyCach(Hashtable<String,Float> hashThung)
	{
		this.Hash_QuyCach = hashThung;
	}
	
	public List<ICtkhuyenmai> getCtkmList()
	{
		return this.ctkmList;
	}
	
	public void setCtkmList(List<ICtkhuyenmai> list)
	{
		this.ctkmList.clear();
		this.ctkmList = list;
		
		this.ctkmBTList.clear();
		this.ctkmOntopList.clear();
		this.ctkmCKList.clear();
		
		/*for(int i = 0; i < this.ctkmList.size(); i++)
		{
			ICtkhuyenmai ctkm = this.ctkmList.get(i);
			if(ctkm.getLoaict() == 1)
			{
				System.out.println("111111.CTKM: " + ctkm.getscheme() + " : " + ctkm.getCK());
				if(ctkm.getCK() > 0)
					this.ctkmCKList.add(ctkm);
				else
					this.ctkmBTList.add(ctkm);
				//this.ctkmBTList.add(ctkm);
			}
			else
				this.ctkmOntopList.add(ctkm);
		}*/
		
		//System.out.println("222-222.CTKM Binh Thuong: " + this.ctkmBTList.size());
		//System.out.println("333-333.CTKM Binh Thuong Chiet Khau: " + this.ctkmCKList.size());
	}	
	
	public void setKhachhang(String khachhang)
	{
		this.khachhang = khachhang;
	}
	
	public String getKhachhang()
	{
		return this.khachhang;
	}
	
	public List<ICtkhuyenmai> getCtkmResual()
	{
		return this.ctkmResult;
	}	
	
	public void setCtkmResual(List<ICtkhuyenmai> list)
	{
		this.ctkmResult = list;
	}	
	
	public boolean getDieuchinh()
	{
		return this.dieuchinhKM;
	}	
	
	public void setDieuchinh(boolean flag)
	{
		this.dieuchinhKM = flag;
	}
	
	public boolean getDungDieuKien()
	{
		return this.dungdieukien;
	}	
	
	public int getRowspan(Ctkhuyenmai ctkm)
	{
		int num = 0;
		List<IDieukienkhuyenmai> listDK = ctkm.getDkkhuyenmai();
		for(int i = 0; i < listDK.size(); i++)
		{
			Dieukienkhuyenmai dkkm = (Dieukienkhuyenmai)listDK.get(i);
			if(dkkm.getSoxuatKM() > 0)
			{
				List<ISanpham> sanpham = dkkm.getSanphamList();
				num += sanpham.size();				
			}
			else
				num += 1;
		}
		return num;
	}	
	
	public float getTonggiatriDh()
	{
		return this.tonggiatriDh;
	}
	
	public void setTonggiatriDh(float tonggiatri)
	{
		this.tonggiatriDh = tonggiatri;
	}
	
	public float getTonggiatri(Dieukienkhuyenmai dkkm)
	{
		float sum = 0;
		List<ISanpham> spList = dkkm.getSanphamList();
		for(int i = 0; i < spList.size(); i++)
		{
			Sanpham sp = (Sanpham)spList.get(i);
			sum += sp.getSoluongcan() * sp.getDongia();
		}
		return sum;
	}
	
	public int getTongsoluong(Dieukienkhuyenmai dkkm)
	{
		int sum = 0;
		List<ISanpham> spList = dkkm.getSanphamList();
		for(int i = 0; i < spList.size(); i++)
		{
			Sanpham sp = (Sanpham)spList.get(i);
			sum += sp.getSoluongcan();
		}
		return sum;
	}
	
	public boolean checkConfirm() 
	{
		System.out.println("vao check dung khuyen mai");
		boolean flag = false;
		if(this.ctkmResult.size() == 1)
		{
			ICtkhuyenmai ctkm = this.ctkmResult.get(0);
			
			List<ITrakhuyenmai> trakmList = ctkm.getTrakhuyenmai();
			for(int i = 0; i < trakmList.size(); i++)
			{
				//ITrakhuyenmai trakm = ctkm.getTrakhuyenmai().get(0);
				ITrakhuyenmai trakm = ctkm.getTrakhuyenmai().get(i);
				
				//ctkhuyenmai phai lua chon sanpham
				if(trakm.getType() == 3 && trakm.getHinhthuc() == 2)
				{
					//cai tien: neu duoi nhapp da chon sanpham cho loai trakm la trakhuyenmai co chon sp
					boolean selected = checkTrakm(ctkm, trakm);
					if(selected == false)
					{
						this.ctkmResult.get(0).setConfirm(true);
						flag = true;
					}
				}
			}
		}
		
		
		for(int i = 0; i < this.ctkmResult.size() - 1; i++)
		{
			ICtkhuyenmai ctkm = this.ctkmResult.get(i);
			List<IDieukienkhuyenmai> dkkmList = ctkm.getDkkhuyenmai();
			
			List<ITrakhuyenmai> trakmList = ctkm.getTrakhuyenmai();
			
			for(int j = 0; j < trakmList.size(); j++)
			{
				ITrakhuyenmai trakm = ctkm.getTrakhuyenmai().get(j);
				
				//ct khuyenmai phai lua chon sanpham
				if(trakm.getType() == 3 && trakm.getHinhthuc() == 2)
				{
					boolean selected = checkTrakm(ctkm, trakm);
					if(selected == false)
					{
						this.ctkmResult.get(i).setConfirm(true);
						flag = true;
					}
				}
			}
			
			for(int j = i+1; j < this.ctkmResult.size(); j++)
			{
				ICtkhuyenmai ctkm2 = this.ctkmResult.get(j);
				List<IDieukienkhuyenmai> dkList = ctkm2.getDkkhuyenmai();
				
				ITrakhuyenmai trakm2 = ctkm2.getTrakhuyenmai().get(0);
				if(trakm2.getType() == 3 && trakm2.getHinhthuc() == 2)
				{
					boolean selected = checkTrakm(ctkm2, trakm2);
					if(selected == false)
					{
						this.ctkmResult.get(j).setConfirm(true);
						flag = true;
					}
				}
				
				if(ctkm2.getLoaict() == ctkm.getLoaict() )
				{
					if(checkDkConfirm(dkkmList, dkList))
					{
						this.ctkmResult.get(i).setConfirm(true);
						this.ctkmResult.get(j).setConfirm(true);
						flag = true;
						this.dungdieukien = true;
					}
				}


				
				/*if(ctkm2.getLoaict() != 2)
				{
					if(checkDkConfirm(dkkmList, dkList))
					{
						this.ctkmResult.get(i).setConfirm(true);
						this.ctkmResult.get(j).setConfirm(true);
						flag = true;
						this.dungdieukien = true;
					}
				}
				else
				{
					if(this.ctkmOntopResult.size() >= 2) //neu ontop chi co 1 ct thi ko tinh la dung san pham
					{
						if(checkDkConfirm(dkkmList, dkList))
						{
							this.ctkmResult.get(i).setConfirm(true);
							this.ctkmResult.get(j).setConfirm(true);
							flag = true;
							this.dungdieukien = true;
						}
					}
				}*/
			}
		}
		
		System.out.println("Confgi la: " + flag);
		this.dungdieukien = true;
		return flag;
	}
	
	private boolean checkTrakm(ICtkhuyenmai ctkm, ITrakhuyenmai trakm) 
	{
		String query = "select COUNT(*) as sodong from TRAKM_NHAPP where trakm_fk = '" + trakm.getId() + "' and npp_fk = '" + this.nppId + "' and ctkm_fk = '" + ctkm.getId() + "'";
		System.out.println("Check tra khuyen mai: " + query + "\n");
		ResultSet rs = db.get(query);
		int sodong = 0;
		if(rs != null)
		{
			try
			{
				if(rs.next())
				{
					sodong = rs.getInt("sodong");
				}
				rs.close();
			} 
			catch (SQLException e) {}
		}
		if(sodong > 0)
		{
			System.out.println("Query la true\n");
			return true;
		}
		System.out.println("Query la false\n");
		return false;
	}

	private boolean checkDkConfirm(List<IDieukienkhuyenmai> dkkm1, List<IDieukienkhuyenmai> dkkm2)
	{
		Hashtable<String, Integer> spList = new Hashtable<String, Integer>();
		for(int i=0; i < dkkm1.size(); i++)
		{   
			IDieukienkhuyenmai dkkm = dkkm1.get(i);
			for(int j = 0; j < dkkm.getSanphamList().size(); j++)
			{
				ISanpham sp = dkkm.getSanphamList().get(j);
				System.out.println("DKKM 1: SP ( " + sp.getMasp() + " ) - SO LUONG: ( " + sp.getSoluongcan() + " ) ");
				if(sp.getSoluongcan() > 0)
				{
					if(!spList.containsKey(sp.getMasp().trim()))
					{
						spList.put(sp.getMasp().trim(), (int) sp.getSoluongcan());
					}
				}
			}
		}
		
		for(int i=0; i < dkkm2.size(); i++)
		{
			IDieukienkhuyenmai dk = dkkm2.get(i);
			
			for(int j = 0; j < dk.getSanphamList().size(); j++)
			{
				ISanpham sp = dk.getSanphamList().get(j);
				System.out.println("DKKM 2: SP ( " + sp.getMasp() + " ) - SO LUONG: ( " + sp.getSoluongcan() + " ) ");
				if(sp.getSoluongcan() > 0 && spList.containsKey(sp.getMasp()))
				{
					System.out.println("____Ket qua check DKKM trung la: true\n");
					return true;
				}	
			}
			
		}
		
		System.out.println("____Ket qua check DKKM la: false\n");
		return false;
	}
	
	public boolean CheckSP_Mua_Va_DK(Hashtable<String, Integer> spList)
	{
		Enumeration<String> keys = this.HashA.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			if(spList.containsKey(key))
			{
				System.out.println("__12123434__Ket qua check SP trong DKKM trung la: true\n");
				return true;
			}
		}
		return false;
	}
	
	public String[] getMasp()
	{
		return this.masp;
	}
	
	public void setMasp(String[] masp)
	{
		this.masp = masp;
	}
	
	public String[] getSoluong()
	{
		return this.soluong;
	}
	
	public void setSoluong(String[] soluong)
	{
		this.soluong = soluong;
	}
	
	public String[] getDongia()
	{
		return this.dongia;
	}
	
	public void setDongia(String[] dongia)
	{
		this.dongia = dongia;
	}
	
	public String[] getQuycah()
	{
		return this.quycach;
	}
	
	public void setQuycach(String[] quycach)
	{
		this.quycach = quycach;
	}
	
	public String getIdDonhang()
	{
		return this.idDonhang;
	}
	
	public void setIdDonhang(String id)
	{
		this.idDonhang = id;
	}
	
	public String getNgaygiaodich()
	{
		return this.ngaygiaodich;
	}
	
	public void setNgaygiaodich(String ngd)
	{
		this.ngaygiaodich = ngd;
	}
	
	public String getUserId()
	{
		return this.userId;
	}

	public void setUserId(String userId) 
	{
		this.userId = userId;
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
	
	public static void main(String[] arg) //test
	{
		Hashtable<String, Double> hashA = new Hashtable<String, Double>();
		Hashtable<String, Float> hashB = new Hashtable<String, Float>();
		Hashtable<String, Float> hash_Quycach = new Hashtable<String, Float>();
		
		/*hashA.put("01.01.11.01.00.002", 235);
		hashA.put("01.01.11.01.00.003", 24);   //01.01.11.01.00.003 ct tien

		hashB.put("01.01.11.01.00.002", 20000f);
		hashB.put("01.01.11.01.00.003", 20000f);

		hash_Quycach.put("01.01.11.01.00.002", 48f);
		hash_Quycach.put("01.01.11.01.00.003", 16f);*/
		
		hashA.put("65020001", (double) 60);
		hashA.put("01.01.11.01.00.003", (double) 50);
		hashA.put("01.01.11.01.00.004", (double) 150);

		hashB.put("65020001", 24000f);
		hashB.put("01.01.11.01.00.003", 24000f);
		hashB.put("01.01.11.01.00.004", 30000f);

		hash_Quycach.put("65020001", 5f);
		hash_Quycach.put("01.01.11.01.00.003", 12f);
		hash_Quycach.put("01.01.11.01.00.004", 12f);
		
		XLkhuyenmai xlkm = new XLkhuyenmai("100374","2013-01-08","468033", "", "0");
		xlkm.setHashA(hashA);
		xlkm.setHashB(hashB);
		xlkm.setHash_QuyCach(hash_Quycach);
		
		xlkm.setDieuchinh(false);
		xlkm.ApKhuyenMai();
		
		List<ICtkhuyenmai> listCT = xlkm.getCtkmResual();
		for(int i = 0; i < listCT.size(); i++)
		{
			System.out.println("___Ma CTKM: " + listCT.get(i).getscheme() + "  -- Dien giai: " + listCT.get(i).getDiengiai() );
		}
		/*List<ICtkhuyenmai> listCT = xlkm.getCtkmResual();
		for(int i = 0; i < listCT.size(); i++)
		{
			Ctkhuyenmai ctkm = (Ctkhuyenmai)listCT.get(i);
			
			List<IDieukienkhuyenmai> dkkmList = ctkm.getDkkhuyenmai();
			for(int j = 0; j < dkkmList.size(); j++ )
			{
				IDieukienkhuyenmai dkkm = dkkmList.get(j);
				if(dkkm.getTrongso() > 0)
				{
					//System.out.println("1.Dkkm: " + dkkm.getDiengiai() + " -- Trong so: " + dkkm.getTrongso());

					float tongluong = ctkm.getSoxuatKM() * dkkm.getTongluong();
					
					//Tong trong so cua cac san pham bat buoc mua
					Hashtable<String, Float> sanpham_trongso = dkkm.getSanpham_Trongso();
					
					//boolean flag = true;  //Neu SP la bat buoc ma ko su dung thi cung ko dc khuyen mai
					List<ISanpham> spList = dkkm.getSanphamList();	
					float totalRequest = 0;
					for(int k = 0; k < spList.size(); k++)
					{
						System.out.println("__San pham: " + spList.get(k).getMasp() + " -- Su dung:  " + spList.get(k).getSoluongcan() );
						
						if(sanpham_trongso.get(spList.get(k).getMasp()) != null)
						{
							if(sanpham_trongso.get(spList.get(k).getMasp()) > 0)
							{
								totalRequest += spList.get(k).getSoluongcan();
							}
						}
					}
					
					System.out.println("__Tong trong so yeu cau: " + ( totalRequest * 100 / tongluong ) );
					
					if ( ( totalRequest * 100 / tongluong ) < dkkm.getTrongso() )
					{
						System.out.println("__CTKM khong thoa trong so va so xuat se duoc cap nhat la: " );
						
						//tinh lai so xuat
						long soXuat = Math.round( ( ( ctkm.getSoxuatKM() * totalRequest * 100 / tongluong ) / dkkm.getTrongso() ) - 0.5 );
						
						//System.out.println("__So xuat dieu chinh la: " + soXuat);
						ctkm.setSoxuatKM((int)soXuat);
					}
					
					//check xem co thoa trong so khong
					
				}
			}
			
			List<ITrakhuyenmai> traKmList = ctkm.getTrakhuyenmai();
			for(int j = 0; j < traKmList.size(); j++)
			{
				ITrakhuyenmai tkm = traKmList.get(j);
				System.out.println("Ctkm id: " + ctkm.getId() + " -- So xuat: " + ctkm.getSoxuatKM() + " -- Tra km ID: " + tkm.getId() + " -- Dien giai: " + tkm.getDiengiai());
			}
			
			//System.out.println("Cuoi cung la: " + ctkm.getscheme() + " ---- " + Integer.toString(ctkm.getSoxuatKM())); 
		}*/
		
		
		/*List<ICtkhuyenmai> listCTAvai = xlkm.getCtkmList();
		
		for(int i = 0; i < listCTAvai.size(); i++)
		{
			Ctkhuyenmai ctkm = (Ctkhuyenmai)listCTAvai.get(i);
			List<ITrakhuyenmai> traKmList = ctkm.getTrakhuyenmai();
			for(int j = 0; j < traKmList.size(); j++)
			{
				ITrakhuyenmai tkm = traKmList.get(j);
				System.out.println("Ctkm id: " + ctkm.getId() + " -- Tra km ID: " + tkm.getId() + " -- Dien giai: " + tkm.getDiengiai());
			}
			
			//System.out.print("\n Cuoi cung la: " + ctkm.getscheme() + " ---- " + Integer.toString(ctkm.getSoxuatKM()) + "\n" ); 
		}*/
		
		/*
		Enumeration<String> keys = test.keys();
		while(keys.hasMoreElements())
		{
			String key = keys.nextElement();
			System.out.print(key + " -- " + Integer.toString(test.get(key)) + "\n");
		}
*/
	}
	
	public void setMsg(String Msg)
	{
		this.Msg =Msg;
	}
	
	public String getMsg()
	{
		return this.Msg;
	}
	
	public void setLoaiDonHang(String loaidonhang)
	{
		this.loaidonhang = loaidonhang;
	}
	
	public String getLoaiDonHang()
	{
		return this.loaidonhang;
	}
	
	public String getDenghitraCK()
  {
  	return denghitraCK;
  }

	public void setDenghitraCK(String denghitraCK)
  {
  	this.denghitraCK = denghitraCK;
  }
	
	public void DBclose(){
		
		try {
			if(this.db != null)
				this.db.shutDown();
			
		} catch (Exception e) {
		}
	}

	private boolean checkDKTrongScheme(ICtkhuyenmai ctkm, List<ICtkhuyenmai> ctkmList)
	{
		boolean flag = false;
		for(int j = 0; j < ctkmList.size(); j++)
		{
			ICtkhuyenmai ct = ctkmList.get(j);
			
			if(!ct.getId().equals(ctkm.getId()))
			{	
				flag = checkDkConfirm(ct.getDkkhuyenmai(), ctkm.getDkkhuyenmai());
				if(flag)
				{
					System.out.println("1___Hai dieu kien dung, ct Old: " + ctkm + " ----  Ct New Id: " + ct.getId());
					break;
				}
			}
		}
		
		//System.out.println("11___Ket qua check: " + flag);
		return flag;
	}
	
	private void ApChietKhau() 
	{
		List<ICtkhuyenmai> schemeList = new ArrayList<ICtkhuyenmai>();
		
		System.out.println("114.So CTKM chiet khau: " + this.ctkmCKList.size() + "  -- Che do dieu chinh: " + this.dieuchinhKM);
		
		if(this.dieuchinhKM)
		{
			for(int i = 0; i < this.ctkmCKList.size(); i++)
			{
				Ctkhuyenmai ct = (Ctkhuyenmai)this.ctkmCKList.get(i);
				Ctkhuyenmai ctkm = this.getSoxuattheoScheme(ct, HashA);
			
				if(ctkm.getSoxuatKM() > 0) //truong hop soxuat khuyenmai lon hon so xuat duoc huong toi da
				{
					if(ctkm.checkCtkm(tonggiatriDh) > 0)
					{
						ctkm.setSoxuatKM(ctkm.checkCtkm(tonggiatriDh));
						schemeList.add(ctkm);
					}
				}
			}
		}
		else  //dieuchinh == false
		{
			
			for(int i = 0; i < this.ctkmCKList.size(); i++)
			{
				Ctkhuyenmai ct = (Ctkhuyenmai)this.ctkmCKList.get(i);
				Hashtable<String, Double> hashCK = copyHashtable(HashA);	
				Ctkhuyenmai ctkm = this.getSoxuattheoScheme(ct, hashCK);
			
				if(ctkm.getSoxuatKM() > 0) //truong hop soxuat khuyenmai lon hon so xuat duoc huong toi da
				{
					if(ctkm.checkCtkm(tonggiatriDh) > 0)
					{
						ctkm.setSoxuatKM(ctkm.checkCtkm(tonggiatriDh));
						schemeList.add(ctkm);
					}
				}
			}
		}
		
		System.out.println("123.chuong tring binh thuong chiet khau: " + schemeList.size() + "\n");
		this.ctkmCKResult = schemeList;
	}
	
	private void getNppInfo()
	{	
		//Phien ban moi
		geso.dms.distributor.util.Utility util = new geso.dms.distributor.util.Utility();
		this.nppId=util.getIdNhapp(this.userId);
		this.nppTen=util.getTenNhaPP();
		this.sitecode=util.getSitecode();		
	}
	
	private boolean checkDkConfirm2(List<IDieukienkhuyenmai> dkkm1, List<IDieukienkhuyenmai> dkkm2)
	{
		/*List<IDieukienkhuyenmai> dkkm1 = ct.getDkkhuyenmai();
		List<IDieukienkhuyenmai> dkkm2 = ct2.getDkkhuyenmai();	
			*/
		//Dieu kien su dung san pham nao???
		/*for(int i = 0; i < dkkm1.get(0).getSanphamList().size(); i++)
		{
			ISanpham sp = dkkm1.get(0).getSanphamList().get(i);
			System.out.println("----3333----.Dieu kien Id: " + dkkm1.get(0).getId() + ", Su dung san pham: " + sp.getMasp() + ", Voi so luong: " + sp.getSoluongcan() + ", Thung tuong ung: " + sp.getSoluongThungAvaiable());
		}
		
		for(int i = 0; i < dkkm2.get(0).getSanphamList().size(); i++)
		{
			ISanpham sp = dkkm2.get(0).getSanphamList().get(i);
			System.out.println("----7777----.Dieu kien Id: " + dkkm2.get(0).getId() + ", Su dung san pham: " + sp.getMasp() + ", Voi so luong: " + sp.getSoluongcan() + ", Thung tuong ung: " + sp.getSoluongThungAvaiable());
		}
		System.out.println("_____________________________Het Check 1 Ct__________________________________\n");*/
		
		
		Hashtable<String, Integer> spList = new Hashtable<String, Integer>();
		for(int i=0; i < dkkm1.size(); i++)
		{   
			IDieukienkhuyenmai dkkm = dkkm1.get(i);
			for(int j = 0; j < dkkm.getSanphamList().size(); j++)
			{
				ISanpham sp = dkkm.getSanphamList().get(j);
				if(sp.getSoluongcan() > 0)
				{
					if(!spList.containsKey(sp.getMasp().trim()))
					{
						spList.put(sp.getMasp().trim(), (int) sp.getSoluongcan());
					}
				}
			}
		}
		
		for(int i=0; i < dkkm2.size(); i++)
		{
			IDieukienkhuyenmai dk = dkkm2.get(i);
			
			for(int j = 0; j < dk.getSanphamList().size(); j++)
			{
				ISanpham sp = dk.getSanphamList().get(j);
				if(sp.getSoluongcan() > 0 && spList.containsKey(sp.getMasp()))
				{
					System.out.println("____Ket qua check DKKM trung la: true\n");
					return true;
				}	
			}
			
		}
		
		System.out.println("____Ket qua check DKKM la: false\n");
		return false;
	}
	
	/******** Chua dung toi *********/
	private List<ISanpham> convertToList(Dieukienkhuyenmai dkkm, Hashtable<String, Integer> sanpham_sl)
	{
		List<ISanpham> spList = new ArrayList<ISanpham>();
		
		Hashtable<String, Float> sp_trongso = dkkm.getSanpham_Trongso();
		
		Enumeration<String> keyList = sanpham_sl.keys();
		while(keyList.hasMoreElements())
		{
			String key = keyList.nextElement();
			ISanpham sp;
			
			if(sp_trongso.get(key) != null)
			{
				if(sp_trongso.get(key) > 0)
				{
					sp = new Sanpham();
					
					sp.setMasp(key);
					sp.setSoluongcan(sanpham_sl.get(key));
					
					spList.add(sp);
				}
			}
		}
		
		keyList = sanpham_sl.keys();
		while(keyList.hasMoreElements())
		{
			String key = keyList.nextElement();
			boolean flag = checkMaSP(key, spList);
			
			if(!flag)
			{
				ISanpham sp = new Sanpham();
		
				sp.setMasp(key);
				sp.setSoluongcan(sanpham_sl.get(key));
						
				spList.add(sp);
			}
		}
		
		return spList;
	}
}
