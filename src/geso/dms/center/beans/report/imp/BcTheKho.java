package geso.dms.center.beans.report.imp;

import geso.dms.center.beans.report.IBcTheKho;
import geso.dms.center.util.Phan_Trang;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BcTheKho extends Phan_Trang implements IBcTheKho, Serializable
{
	public BcTheKho()
	{
		this.spId="";
		this.nppId="";
		this.userId="";
		this.khId="";
		this.msg="";
		this.ddkdId="";
		this.tuNgay="";
		this.denNgay="";
		this.kbhId="";
		this.ttId="";
		this.nhomId="";
		this.vungId="";
		this.loaiHoaDon="0";
		this.action="";
		this.type="1";
		this.khoId="";
		db = new dbutils();
	}

	private static final long serialVersionUID = 1L;
	String tuNgay,denNgay,spId,nppId,ddkdId,userId,khId,userName = "";
	float tondau;
	public float getTondau() {
		return tondau;
	}
	public void setTondau(float tondau) {
		this.tondau = tondau;
	}
	public String getKhId()
	{
		return khId;
	}
	public void setKhId(String khId)
	{
		this.khId = khId;
	}
	public String getTuNgay()
	{
		return tuNgay;
	}
	public void setTuNgay(String tuNgay)
	{
		this.tuNgay = tuNgay;
	}
	public String getDenNgay()
	{
		return denNgay;
	}
	public void setDenNgay(String denNgay)
	{
		this.denNgay = denNgay;
	}
	public String getSpId()
	{
		return spId;
	}
	String spMa = "";
	String spTen = "";
	String spDonvi = "";
	public void setSpId(String spId)
	{
		this.spId = spId;
		
		try {
			String query = " select ma,ten,dv.DONVI from SANPHAM sp inner join DONVIDOLUONG dv on sp.DVDL_FK = dv.PK_SEQ where sp.pk_seq =   "+ this.spId;
			ResultSet rs = db.get(query);
			rs.next();
			this.spMa =  rs.getString("ma");
			this.spTen =  rs.getString("ten");
			this.spDonvi =  rs.getString("DONVI");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public String getSpMa() {
		return spMa;
	}
	public String getSpTen() {
		return spTen;
	}
	public String getSpDonvi() {
		return spDonvi;
	}
	public String getNppId()
	{
		return nppId;
	}
	public void setNppId(String nppId)
	{
		this.nppId = nppId;
	}
	public String getDdkdId()
	{
		return ddkdId;
	}
	public void setDdkdId(String ddkdId)
	{
		this.ddkdId = ddkdId;
	}
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public ResultSet getSpRs()
	{
		return spRs;
	}
	public void setSpRs(ResultSet spRs)
	{
		this.spRs = spRs;
	}
	public ResultSet getDdkdRs()
	{
		return ddkdRs;
	}
	public void setDdkdRs(ResultSet ddkdRs)
	{
		this.ddkdRs = ddkdRs;
	}
	ResultSet spRs,ddkdRs,khRs,hoadonRs;
	public ResultSet getHoadonRs()
	{
		return hoadonRs;
	}
	public void setHoadonRs(ResultSet hoadonRs)
	{
		this.hoadonRs = hoadonRs;
	}
	public ResultSet getKhRs()
	{
		return khRs;
	}
	public void setKhRs(ResultSet khRs)
	{
		this.khRs = khRs;
	}

	public void closeDB()
	{

		try
		{
			if(khRs!=null)
				khRs.close();

			if(spRs!=null)spRs.close();

			if(db!=null)db.shutDown();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	dbutils db = new dbutils();
	public void createRs()
	{

		Utility util = new Utility();

		String query="select pk_Seq,ma + '-' + ten as Ten from sanpham where trangthai=1 ";

		if(this.nhomId.length()>0)
		{
			query += " and pk_Seq in (select sanpham_fk from nhomhang_sanpham where nhomhang_fk='"+this.nhomId+"' ) ";
		}
		query += "  order by ma  ";

		this.spRs = this.db.get(query);

		query = "select pk_seq, manhanvien,ten from DaiDienKinhDoanh a where 1=1  ";
		if(this.view.length()>0)
		{
			query += " and pk_seq in  " + util.Quyen_Ddkd(userId) ;
		}

		if(this.nppId.length() > 0)
			query += " and a.pk_seq in ( select ddkd_fk from daidienkinhdoanh_npp where npp_fk = '" + nppId + "')  ";
		this.ddkdRs = this.db.get(query);

		if(this.nppId.length()>0)
		{
			query="select pk_seq,isnull(mafast,'') +' ' + ten + ' ' + isnull(diachi,'') as Ten from khachhang where 1=1 ";

			if(this.view.length()>0)
			{
				query+=" and npp_fk in "+util.quyen_npp(userId);
			}

			if(this.nppId.length()>0)
				query+=" and npp_fk='"+this.nppId+"' ";

			this.khRs= this.db.get(query);
		}
		query="select pk_Seq,ten,DIENGIAI from KENHBANHANG where TRANGTHAI=1 ";
		this.kbhRs = this.db.get(query);	

		query="select pk_seq,ten from tinhthanh where 1=1 ";

		if(this.vungId.length()>0)
		{
			query+=" and vung_fk='"+this.vungId+"' ";
		}
		this.ttRs=this.db.get(query);

		query="select pk_seq,ten from vung where 1=1 ";
		query+=" and pk_Seq in  "+util.Quyen_Vung(userId)+" ";

		this.vungRs=this.db.get(query);


		query="select pk_Seq,ten from NhomHang ";
		this.nhomRs=this.db.get(query);


		query="select pk_Seq,ten,diachi from Nhaphanphoi where trangthai=1 and iskhachhang=0  and loaiNPP<>4 ";



		if(this.view.length()>0)
		{
			query+="and pk_Seq in "+util.quyen_npp(userId)+"" ;
		}else 
		{
			query+=" and pk_Seq='"+this.nppId+"' ";
		}


		if(this.ttId.length()>0)
			query+=" and tinhthanh_Fk='"+this.ttId+"' ";

		if(this.vungId.length()>0)
			query+=" and khuvuc_fk in (select pk_seq from khuvuc where vung_fk='"+this.vungId+"' ) ";

		System.out.println("_NPP_"+query);

		this.nppRs=this.db.get(query);

		query="select pk_Seq,ten,diengiai from kho where trangthai=1";
		this.khoRs=this.db.get(query);
	
	
		if(this.spId.trim().length() > 0 && this.nppId.trim().length() > 0)
		{
			query = " select distinct solo  from NHAPP_KHO_CHITIET where sanpham_fk ="+this.spId+" and nPP_FK = "+this.nppId+" ";
			if(this.khoId.trim().length() > 0)
				query +=" and kho_fk  ="+this.khoId+"  ";
			if(this.kbhId.trim().length() > 0)
				query +=" and kbh_fk  ="+this.kbhId+"  ";
			this.soloRs = db.get(query);
		}
		
	}

	String queryHd="";
	public String getQueryHd()
	{
		return queryHd;
	}
	public void setQueryHd(String query)
	{
		this.queryHd=query;
	}

	public void initTT()
	{
		String query=    
			"declare @Denngay nvarchar(20),@Tungay nvarchar(20),@sanpham_fk numeric(18,0)   \n "+      
			"set @Denngay='"+this.denNgay+"'   \n "+      
			"set @Tungay='"+this.tuNgay+"'   \n "+
			"set @sanpham_fk=("+this.spId+")   \n"+    
			" \n "+      
			" \n  select      "+
			" \n  ngayct,soct,d.ma,d.ten,db.nghiepvu,   "+
			" \n  case when  sum(db.soluong)<0 then sum(db.soluong) else 0 end as xuat,case when  sum(db.soluong)>0 then sum(db.soluong) else 0 end as nhap "+
			" \n   from  (    "+
			" \n    "+
			" \n   "+
			" \n  select a.NgayNhap as ngayct,a.pk_seq as soct,a.KhoNhap_FK,'1' npp_fk,b.sanpham_fk,(b.soluong) as soluong,N'Nhập kho' as nghiepvu 																									  "+
			" \n  from ERP_NHAPKHO a inner join ERP_NHAPKHO_SANPHAM  b   																																											  "+
			" \n  on a.PK_SEQ=b.nhapkho_fk     																																																		  "+
			" \n  where a.NgayNhap>=@Tungay and a.NgayNhap<=@Denngay and a.TRANGTHAI=1   AND b.SANPHAM_FK=@sanpham_fk																																							  "+
			" \n  																																																									  "+
			" \n   union all   																																																						  "+
			" \n  	select  a.NgayChuyen as ngayct,a.pk_seq as soct,a.KhoXuat_FK,a.NPP_FK,b.sanpham_fk,(-1)*(b.soluongchuyen) as soluong,N'Xuất chuyển nội bộ' as nghiepvu  																		  "+
			" \n 	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b    																																										  "+
			" \n  	on a.PK_SEQ=b.chuyenkho_fk   																																																	  "+
			" \n  	where a.NgayChuyen>=@Tungay and a.NgayChuyen<= @Denngay and a.TRANGTHAI=1   AND b.SANPHAM_FK=@sanpham_fk	 																																					  "+
			" \n  																																																									  "+
			" \n  union all   																																																						  "+
			" \n  	select  a.NgayDieuChinh as ngayct,a.pk_seq as soct,a.KhoTT_FK,'1' as npp_fk,b.sanpham_fk,(-1)*(b.tonthucte) as soluong,N'Điều chỉnh tồn kho' as nghiepvu  from ERP_DIEUCHINHTONKHO a inner join ERP_DIEUCHINHTONKHO_SANPHAM b     "+
			" \n  	on a.PK_SEQ=b.dieuchinh_fk   																																																	  "+
			" \n  	where a.NgayDieuChinh>=@Tungay and a.NgayDieuChinh<=@Denngay and TRANGTHAI=1   AND b.SANPHAM_FK=@sanpham_fk	 																																					  "+
			" \n 																																																									  "+
			" \n  union all   																																																						  "+
			" \n  	select ngayct,soct,data.khott_FK,data.NPP_FK,data.SANPHAM_FK,(-1)*(data.SOLUONG),N'Xuất bán' as nghiepvu																														  "+
			" \n 	from (   																																																						  "+
			" \n  	select  a.NGAYXUATHD as ngayct,a.pk_seq as soct,(select top(1) dh.Kho_FK	   																																					  "+
			" \n  				from ERP_DONDATHANG dh    																																															  "+
			" \n  				inner join ERP_HOADON_DDH dhsp on dh.PK_SEQ=dhsp.DDH_FK   																																							  "+
			" \n  				where a.PK_SEQ=dhsp.HOADON_FK) as khott_FK,   																																										  "+
			" \n  			b.SANPHAM_FK,b.SOLUONG,a.NPP_FK   																																														  "+
			" \n  	 from erp_hoadon a inner join ERP_HOADON_SP b   																																												  "+
			" \n  	on a.PK_SEQ=b.HOADON_FK   																																																		  "+
			" \n  	where a.NGAYXUATHD>=@Tungay and a.NGAYXUATHD<=@Denngay    AND b.SANPHAM_FK=@sanpham_fk		 and TRANGTHAI not in (3,5)) as data  																																  "+
			" \n  																																																									  "+
			" \n  union all   																																																						  "+
			" \n  	select  a.NgayChuyen as ngayct,a.pk_seq as soct,a.KhoXuat_FK,a.NPP_FK,b.sanpham_fk,(-1)*(b.soluongchuyen),N'Xuất kho khác' as nghiepvu    																						  "+
			" \n  	from ERP_CHUYENKHO a inner join ERP_CHUYENKHO_SANPHAM b   																																										  "+
			" \n  	on a.PK_SEQ=b.chuyenkho_fk   																																																	  "+
			" \n  	where a.TRANGTHAI=1 and a.NgayChuyen>=@Tungay and a.NgayChuyen <=@Denngay     AND b.SANPHAM_FK=@sanpham_fk																																						  "+
			" \n 																																																									  "+
			" \n  																																																									  "+
			" \n  ) as DB inner join SANPHAM b on b.PK_SEQ=DB.sanpham_fk   																																											  "+
			" \n  inner join ERP_KHOTT c on c.PK_SEQ=DB.KhoNhap_FK    																																												  "+
			" \n  inner join NHAPHANPHOI d on d.PK_SEQ=DB.npp_fk   																																													  "+
			" \n 																																																									  "+
			" \n  where 1=1																																																							  "+
			" \n  group by ngayct,soct,d.ma,d.ten,db.nghiepvu																																														  "+
			" \n  ORDER BY d.ma,d.ten																																																				  ";
																																																													  

		System.out.println("::::::::::::::::"+query);		 
		ResultSet rs=db.get(query);
		/*
		 * float tondau=0; try { while(rs.next()) { tondau+=rs.getFloat("tondau");
		 * rs.close(); } } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } this.tondau=tondau;
		 */
		this.queryHd=query;
		this.hoadonRs = db.get(query);

		createRs();

	}

	public void init(String search)
	{
		String query;

		String param=
			"declare @TuNgay varchar(10),@DenNgay varchar(10),@nppId numeric(18,0),            "+      
			"   @KhoId numeric(18,0),@KbhId numeric(18,0),@DvkdId numeric(18,0),@spId numeric(18,0)            "+      
			"               "+      
			"   set @nppId=('"+this.nppId+"'	)           "+      
			"   set @TuNgay='"+this.tuNgay+"'	           "+      
			"   set @DenNgay='"+this.denNgay+"'           "+      
			"   set @spId =("+this.spId+")   "+      
			"              "+      
			"   IF OBJECT_ID('tempdb.dbo.#ThangKS_GanNhat') IS NOT NULL DROP TABLE #ThangKS_GanNhat \n"+      
			"      create table #ThangKS_GanNhat \n"+      
			"      ( \n"+      
			"   		NPP_FK NUMERIC(18,0), \n"+      
			"   		Nam int, \n"+      
			"   		Thang int, \n"+      
			"   		NgayDauThang char(10) \n"+      
			"   		primary key (npp_fk,nam,thang,NgayDauThang) \n"+      
			"      ) \n"+      
			"insert into #ThangKS_GanNhat(NPP_FK,Thang,Nam,NgayDauThang)  \n"+ 
			"select @nppId,  \n"+ 
			"	(		\n"+ 
			"	select top(1) (thangks)  from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ    and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)   \n"+ 
			"	order by NAM desc ,THANGKS desc \n"+ 
			"	) as Thang , \n"+ 
			"	(	select top(1) (NAM) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ  and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)   \n"+ 
			"	order by NAM desc ,THANGKS desc  \n"+ 
			"	) \n"+ 
			",  \n"+ 
			" CONVERT(char(10),	 DATEADD(month,1,( \n"+ 
			"	cast((	select top(1) (NAM) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ 	 and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)   \n"+ 
			"	order by NAM desc ,THANGKS desc \n"+ 
			"	) as varchar(10)) +'-'+   \n"+ 
			"	case when len(dbo.Trim(  \n"+ 
			"	cast((		select top(1) (thangks) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ 	 and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)   \n"+ 
			"	order by NAM desc ,THANGKS desc  \n"+ 
			"	)as varchar(10))))<2 then '0'+ 	cast((		select top(1) (thangks)  from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ  and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)  \n"+ 
			"	order by NAM desc ,THANGKS desc  \n"+ 
			"		)as varchar(10)) else  \n"+ 
			"	cast((		select top(1) (thangks) from KHOASOTHANG ks  where NPP_FK=a.PK_SEQ 	 and SUBSTRING(NgayThangNam,0,8) < SUBSTRING(@TuNgay,0,8)  \n"+  
			"	order by NAM desc ,THANGKS desc \n"+ 
			"	)as varchar(10)) end +'-01'  \n"+ 
			"	)),120) as NgayDauThang \n"+ 
			"	from NHAPHANPHOI  a \n"+ 
			"		where a.PK_SEQ=@nppId \n";		

		if(this.action.length()>0)
		{
			  query = "select makh khma, tenkh khten,isnull(sonetid,'') as soct,ngayct,isnull(nhap,0) as nhap,xuat,DienGiai nghiepvu from [dbo].[ufn_TheKho_timkiem]('"+this.tuNgay+"','"+this.denNgay+"',"+this.nppId+",'"+this.kbhId+"', '"+this.khoId+"','"+this.spId+"','"+this.solo+"' )    ";
    


			if(this.type.equals("0"))
			{
				query=
					"                          \n "+      
					"   select data.soct,data.ngayct,npp.ma as nppma,npp.ten as nppten,   \n "+      
					"   	isnull(kh.mafast,'') +isnull(dt.mafast,'') as khma,   \n "+      
					"   	isnull(kh.ten,'')+isnull(dt.ten,'') as khten,sp.ma as spma,sp.ten as spten, data.soluong as soluong,                     \n "+      
					"   	solo,ngayhethan,data.nghiepvu   ,kho.ten as khoten      \n "+      
					"   from   \n "+      
					"   (   \n "+      
					"   	select data.npp_fk,data.khachhang_fk,data.dt_fk,data.soct,data.ngayct,data.solo,data.ngayhethan,data.nghiepvu,sum(data.soluong) as soluong,   \n "+      
					"   		data.sanpham_fk ,data.kho_fk as kho_fk   \n "+      
					"     from                               \n "+      
					"     (                              \n "+      
					"     	select  N'Tồn đầu' as soct,'' as ngayct,a.npp_fk,null as khachhang_fk,null as dt_fk,   \n "+      
					"     			a.sanpham_fk,sum(a.soluong) as soluong,N'Tồn đầu' as nghiepvu,a.solo,a.ngayhethan,a.kho_fk                     \n "+      
					"     	from tonkhothang_chitiet	a inner join #thangks_gannhat b on b.npp_fk=a.npp_fk and a.thang=b.thang and a.nam=b.nam	                     \n "+      
					"     	group by a.npp_fk,a.sanpham_fk,a.solo,a.ngayhethan,a.kho_fk	                     \n "+      
					"                          \n "+      
					"     	union all          \n "+      
					"     	              \n "+      
					"     	select sohoadon  as soct,ngayxuathd as ngayct,dh.npp_fk,khachhang_fk,null as dt_fk ,sanpham_fk,(-1)*sum(soluong) as soluong,                     \n "+      
					"     		N'Xuất bán OTC' as nghiepvu,(case when dbo.trim(solo)='' then 'NA' else SOLO end) as solo,(case when dbo.trim(ngayhethan)='' then '2030-12-31' else ngayhethan end) ngayhethan   ,dh.kho_fk                    \n "+      
					"     	from                              \n "+      
					"     	(                              \n "+      
					"     		select dh.npp_fk,dh.kho_fk,dh.khachhang_fk,sp.pk_seq as sanpham_fk,dhsp.hoadon_fk,dh.ngayxuathd,(soluong) as soluong ,   \n "+      
					"     				N'Xuất hàng bán' as type,         solo,ngayhethan,dh.sohoadon,dh.kyhieu                     \n "+      
					"     		from  hoadon_sp_chitiet dhsp                                   \n "+      
					"     			inner join hoadon dh on dh.pk_seq = dhsp.hoadon_fk                     \n "+      
					"     			inner join sanpham sp on sp.ma=dhsp.ma  	                     \n "+      
					"     		where dh.trangthai not in (3,5) and isnull(dh.loaihoadon,0)=0 and dh.ngayxuathd >= @tungay  and dh.ngayxuathd <= @denngay                          \n "+      
					"     		 and dh.npp_fk=@nppid                              \n "+      
					"     	)as dh                              \n "+      
					"     	group by  dh.npp_fk,dh.sanpham_fk,khachhang_fk,hoadon_fk,ngayxuathd,(case when dbo.trim(solo)='' then 'NA' else SOLO end),(case when dbo.trim(ngayhethan)='' then '2030-12-31' else ngayhethan end) ,sohoadon,kyhieu ,kho_fk                          \n "+      
					"     	                             \n "+      
					"     	union all                                                  \n "+      
					"   	select sohoadon as soct,ngayxuathd as ngayct,npp_fk,khachhang_fk,null as dt_fk   \n "+      
					"   		,sanpham_fk,(-1)*sum(soluong) as soluong,N'Xuất KM' as type ,solo,ngayhethan,   \n "+      
					"   		hd.kho_fk             \n "+      
					"   	from                      \n "+      
					"   	(                     \n "+      
					"   		select  hoadon_fk,a.npp_fk,c.pk_seq as sanpham_fk,b.kho_fk,solo,                     \n "+      
					"   				b.soluong as soluong,ngayhethan,a.khachhang_fk,ngayxuathd,sohoadon,kyhieu                     \n "+      
					"   		from  hoadon a inner join hoadon_ctkm_trakm_chitiet b on b.hoadonid=a.pk_seq                     \n "+      
					"   			inner join sanpham c on c.pk_seq=b.sanpham_fk                     \n "+      
					"   		where a.npp_fk=@nppid and a.ngayxuathd>=@tungay and a.ngayxuathd<=@denngay                     \n "+      
					"   			and a.trangthai not in (3,5) and a.loaihoadon in (0,1)                     \n "+      
					"   	) hd                     \n "+      
					"   	group by hd.npp_fk,hd.sanpham_fk,hd.kho_fk,hd.solo,ngayhethan,khachhang_fk,ngayxuathd  ,hoadon_fk,sohoadon,kyhieu                     \n "+      
					"   	   \n "+      
					"   	union all                              \n "+      
					"   	select a.sohoadon as soct,a.ngayxuathd,a.npp_fk,a.khachhang_fk,a.npp_dat_fk as dt_fk,c.pk_seq as spid,-1*sum(b.soluong_chuan) as soluong,   \n "+      
					"   		N'Xuất ETC' as type,(case when dbo.trim(b.solo)='' then 'NA' else b.solo end) as solo,(case when dbo.trim(b.ngayhethan)='' then '2030-12-31' else b.ngayhethan end) as ngayhethan,b.kho_fk            \n "+      
					"   	from erp_hoadonnpp a inner join erp_hoadonnpp_sp_chitiet  b on b.hoadon_fk=a.pk_seq                 \n "+      
					"   		inner join sanpham c on c.ma=b.ma                 \n "+      
					"   	where a.ngayxuathd>=@tungay and a.ngayxuathd<=@denngay and a.trangthai not in (3,5)            \n "+      
					"   	group by a.sohoadon ,a.ngayxuathd,a.npp_fk,a.khachhang_fk,c.pk_seq,b.solo,b.ngayhethan,b.kho_fk  ,a.npp_dat_fk          \n "+      
					"                          \n "+      
					"     	union all                     \n "+      
					"   	select cast(b.sochungtu as varchar(20)) as soct ,b.ngaychungtu as ngayct,b.npp_fk  , null as khachhang_fk   ,null as dt_fk,                      \n "+      
					"   		a.sanpham_fk,sum(cast(soluongnhan as int)) as soluong,N'Nhập hàng' as nghiepvu ,solo,ngayhethan ,a.khonhan_fk as kho_fk   \n "+      
					"   	from nhaphang_sp a inner join nhaphang b on a.nhaphang_fk = b.pk_seq		                                 \n "+      
					"   	where   b.trangthai =1 and b.ngaynhan >= @tungay and b.ngaynhan<=@denngay                              \n "+      
					"   	group by b.sochungtu,b.ngaychungtu,a.sanpham_fk,b.npp_fk ,solo,ngayhethan ,a.khonhan_fk                            \n "+      
					"     	                             \n "+      
					"     	union all                              \n "+      
					"     	                             \n "+      
					"     	select cast( b.hangtralai_fk as varchar(20))as soct ,a.ngaytra as ngayct,    \n "+      
					"     			a.npp_fk,  a.khachhang_fk ,a.npptra_fk as dt_fk ,b.sanpham_fk,sum(b.soluong) as soluong,N'Hàng trả lại',solo,ngayhethan ,a.kho_fk   \n "+      
					"     	from erp_hangtralainpp a inner join erp_hangtralainpp_sanpham b on b.hangtralai_fk=a.pk_seq                              \n "+      
					"     	where a.trangthai=1  and a.ngaytra>=@tungay and a.ngaytra <=@tungay   and a.npp_fk=@nppid                              \n "+      
					"     	group by a.npp_fk,b.sanpham_fk,b.hangtralai_fk,b.hangtralai_fk,a.ngaytra,a.khachhang_fk ,solo,ngayhethan,a.kho_fk,a.npptra_fk                     \n "+      
					"      \n "+      
					"     	union all                              \n "+      
					"     	                             \n "+      
					"     	select cast(sochungtu as varchar(20)) as soct,ngaychuyen as ngayct,ck.npp_fk,null as khachhang_fk ,  ck.dt_fk ,          \n "+      
					"     			ck.sanpham_fk ,(-1)*sum(soluong) as soluong,N'Xuất chuyển nội bộ' as type  ,solo,ngayhethan,ck.kho_fk                               \n "+      
					"     	from                                  \n "+      
					"     	(                                 \n "+      
					"     		select c.sochungtu,a.sanpham_fk,a.solo,a.soluong as soluong,c.npp_fk,c.ngaychuyen,a.ngayhethan,c.npp_dat_fk as dt_fk,c.khoxuat_fk as kho_fk   \n "+      
					"     		from erp_chuyenkhonpp_sanpham_chitiet a inner join sanpham b on a.sanpham_fk = b.pk_seq                                    \n "+      
					"     		inner join erp_chuyenkhonpp c on a.chuyenkho_fk = c.pk_seq                                    \n "+      
					"     		where c.trangthai=1 and c.ngaychuyen>=@tungay and c.ngaychuyen<= @denngay     and npp_fk=@nppid                              \n "+      
					"     	)as ck                                 \n "+      
					"     	group by ck.npp_fk, ck.sanpham_fk,ngaychuyen,sochungtu ,solo,ngayhethan,ck.dt_fk,ck.kho_fk 	                       \n "+      
					"     	                             \n "+      
					"     	union all                              \n "+      
					"     	                             \n "+      
					"     	select  cast(thsp.dontrahang_fk as varchar(20)) as soct ,dth.ngaytra,dth.npp_fk, null as khachhang_fk,   \n "+      
					"     		dth.tructhuoc_fk as dt_fk ,thsp.sanpham_fk,            \n "+      
					"     			(-1)* sum(thsp.soluong) as soluong ,N'Trả hàng về ncc' as type,solo,ngayhethan,dth.kho_fk                                 \n "+      
					"     	from dontrahang_sp thsp inner join  dontrahang dth on  dth.pk_seq = thsp.dontrahang_fk                                 \n "+      
					"     	where dth.trangthai =2 and dth.ngaytra >=@tungay and dth.ngaytra <=@denngay and thsp.soluong > 0                                  \n "+      
					"     	group by dth.npp_fk,thsp.sanpham_fk,thsp.dontrahang_fk,dth.ngaytra ,solo,ngayhethan,dth.kho_fk,dth.tructhuoc_fk                                                                         \n "+      
					"     	union all                              \n "+      
					"     	select cast(dctk_sp.dieuchinhtonkho_fk as varchar(20)) as soct,dctk.ngaydc ,dctk.npp_fk,null as khachhang_fk,null as tructhuoc_fk,   \n "+      
					"     		dctk_sp.sanpham_fk ,sum( cast( isnull(dctk_sp.dieuchinh,0) as int) ) as soluong, N'Kiểm kho(+)' as type ,solo,ngayhethan	,dctk.kho_fk   \n "+      
					"     	from	dieuchinhtonkho dctk  inner join dieuchinhtonkho_sp dctk_sp on dctk_sp.dieuchinhtonkho_fk = dctk.pk_seq                                  \n "+      
					"     	where   dctk.trangthai =1 and dctk.ngaydc >= @tungay and dctk.ngaydc <= @denngay and dctk_sp.dieuchinh>0                              \n "+      
					"     	group by  dctk.npp_fk,dctk_sp.sanpham_fk ,dctk_sp.dieuchinhtonkho_fk,dctk.ngaydc  ,solo,ngayhethan,dctk.kho_fk                          	                            \n "+      
					"     	union all                              \n "+      
					"     	select cast(dctk_sp.dieuchinhtonkho_fk as varchar(20)) as soct,dctk.ngaydc ,dctk.npp_fk,null as khachhang_fk,null as tructhuoc_fk,   \n "+      
					"     			dctk_sp.sanpham_fk ,sum( cast( isnull(dctk_sp.dieuchinh,0) as int) ) as soluong, N'Kiểm kho(-)' as type,solo,ngayhethan ,dctk.kho_fk                               \n "+      
					"     	from	dieuchinhtonkho dctk  inner join dieuchinhtonkho_sp dctk_sp on dctk_sp.dieuchinhtonkho_fk = dctk.pk_seq                                  \n "+      
					"     	where   dctk.trangthai =1 and dctk.ngaydc >= @tungay and dctk.ngaydc <= @denngay and dctk_sp.dieuchinh<0                              \n "+      
					"     	group by  dctk.npp_fk,dctk_sp.sanpham_fk ,dctk_sp.dieuchinhtonkho_fk,dctk.ngaydc  ,solo,ngayhethan,dctk.kho_fk                     \n "+      
					"     	   \n "+      
					"      union all              \n "+      
					"   		select cast( b.doisolo_fk as varchar(20))       \n "+      
					"   				as soct ,a.ngaydoi as ngayct, a.npp_fk, null ,null ,b.sanpham_fk,sum(b.soluong) as soluong,N'Đổi số lô(+)' ,solo,ngayhethan,a.kho_fk       \n "+      
					"   		from erp_doisolonpp a inner join erp_doisolonpp_sanpham b on b.doisolo_fk=a.pk_seq     \n "+      
					"   		where a.trangthai=1 and ngaydoi>=@tungay and ngaydoi<=@denngay      \n "+      
					"   		group by a.npp_fk,a.kho_fk,b.sanpham_fk,b.solo,b.ngayhethan,b.doisolo_fk ,a.ngaydoi,a.kho_fk     \n "+      
					"   		   \n "+      
					"   	union all         \n "+      
					"   	select cast( b.doisolo_fk as varchar(20))         \n "+      
					"   		as soct ,a.ngaydoi as ngayct, a.npp_fk, null ,null ,b.sanpham_fk,(-1)*sum(b.soluong) as soluong,N'Đổi số lô(-)' ,soloold,ngayhethanold,a.kho_fk       \n "+      
					"   	from erp_doisolonpp a inner join erp_doisolonpp_sanpham b on b.doisolo_fk=a.pk_seq    \n "+      
					"   	where a.trangthai=1 and ngaydoi>=@tungay and ngaydoi<=@denngay      \n "+      
					"   	group by a.npp_fk,a.kho_fk,b.sanpham_fk,b.soloold,ngayhethanold ,b.doisolo_fk ,a.ngaydoi  ,a.kho_fk   \n "+      
					"   	   \n "+      
					"   	union all   \n "+      
					"   	   \n "+      
					"   	select cast(chuyenkho_fk as varchar(20)) as soct,ngaychuyen as ngayct,ck.npp_fk,null as khachhang_fk ,  ck.dt_fk ,          \n "+      
					"   			ck.sanpham_fk ,(-1)*sum(soluong) as soluong,N'Xuất kho khác' as type  ,solo,ngayhethan,ck.kho_fk                               \n "+      
					"   	from                                  \n "+      
					"   	(                                 \n "+      
					"   		select a.chuyenkho_fk,a.sanpham_fk,a.solo,a.soluong as soluong,c.tructhuoc_fk as npp_fk,   \n "+      
					"   			c.ngaychuyen,a.ngayhethan,c.npp_fk as dt_fk,c.khoxuat_fk as kho_fk   \n "+      
					"   		from erp_chuyenkho_sanpham_chitiet a inner join sanpham b on a.sanpham_fk = b.pk_seq                                    \n "+      
					"   		inner join erp_chuyenkho c on a.chuyenkho_fk = c.pk_seq                                    \n "+      
					"   		where c.trangthai=1 and c.ngaychuyen>=@tungay and c.ngaychuyen<= @denngay     and tructhuoc_fk=@nppid                              \n "+      
					"   	)as ck                                 \n "+      
					"   	group by ck.npp_fk, ck.sanpham_fk,ngaychuyen,chuyenkho_fk ,solo,ngayhethan,ck.dt_fk,ck.kho_fk   \n "+      
					"    )as data   \n "+      
					"    where data.npp_fk=@nppid and data.sanpham_fk=@spid   \n "+      
					"    group by data.soct,data.sanpham_fk,data.ngayct,data.solo,data.ngayhethan,data.nghiepvu, data.npp_fk,data.khachhang_fk,data.dt_fk   \n "+      
					"    ,data.kho_fk                 \n "+      
					"    )as data   \n "+      
					"     inner join nhaphanphoi npp on npp.pk_seq=data.npp_fk             \n "+      
					"     left join kho kho on kho.pk_seq=data.kho_fk                     \n "+      
					"     inner join sanpham sp on sp.pk_seq=data.sanpham_fk                              \n "+      
					"     left join khachhang kh on kh.pk_seq=data.khachhang_fk      \n "+      
					"     left join nhaphanphoi dt on dt.pk_seq=data.dt_fk                           \n "+      
					"   where npp.pk_seq=@nppid and sp.pk_seq=@spid  and data.soluong<>0             \n ";
			}

			/*
			 * if(this.khoId.length()>0) query+=" and data.kho_fk='"+khoId+"' ";
			 * 
			 */
			setTotal_Query(param,query);
			query=param+query;
 			query+="  order by khma,khten, ngayct  ";
			this.queryHd=query;
			this.hoadonRs = db.get(query);
			System.out.println("::::::::::::::::"+query);
		}
		createRs();
	}

	String view,msg;
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getView()
	{
		return view;
	}
	public void setView(String view)
	{
		this.view = view;
	}

	String solo  = "";
	ResultSet soloRs;
	
	public String getKbhId()
	{
		return kbhId;
	}
	public void setKbhId(String kbhId)
	{
		this.kbhId = kbhId;
	}
	public ResultSet getKbhRs()
	{
		return kbhRs;
	}
	public void setKbhRs(ResultSet kbhRs)
	{
		this.kbhRs = kbhRs;
	}

	public void setTotal_Query(String param, String query) 
	{
		if(this.type.equals("1"))
		{
			/*	String[] param = new String[7];
			param[0] = this.tuNgay.equals("") ? null : this.tuNgay;
			System.out.println(param[0]);
			param[1] = this.denNgay.equals("") ? null : this.denNgay;
			System.out.println(param[1]);
			param[2] = this.nppId.equals("")? null : this.nppId;
			System.out.println(param[2]);
			param[3] ="".equals("") ? null : "";	
			param[4] ="".equals("") ? null : "";	
			param[5] ="".equals("") ? null : "";	
			param[6] =this.spId.equals("") ? null : this.spId;	
			System.out.println(param[6]);
			this.totalRs=db.getRsByPro("Report_XNT_By_Product", param);*/

		}
		else 
		{

			String query1=param+
			"   select  sum(data.soluong) as soluong, solo,ngayhethan "+      
			"   from   \n "+      
			"   (   \n "+query+"   )  as data" +
			" group by data.solo,data.ngayhethan  \n ";
			this.totalRs=this.db.get(query1);
			System.out.println("da vao day sql"+ query1);
		}

	}


	ResultSet totalRs;
	public ResultSet getTotalRs()
	{
		return totalRs;
	}
	public void setTotalRs(ResultSet totalRs)
	{
		this.totalRs=totalRs;
	}

	String vungId,nhomId,ttId;
	ResultSet vungRs,nhomRs,ttRs;
	public String getVungId()
	{
		return vungId;
	}
	public void setVungId(String vungId)
	{
		this.vungId = vungId;
	}
	public String getNhomId()
	{
		return nhomId;
	}
	public void setNhomId(String nhomId)
	{
		this.nhomId = nhomId;
	}
	public String getTtId()
	{
		return ttId;
	}
	public void setTtId(String ttId)
	{
		this.ttId = ttId;
	}
	public ResultSet getVungRs()
	{
		return vungRs;
	}
	public void setVungRs(ResultSet vungRs)
	{
		this.vungRs = vungRs;
	}
	public ResultSet getNhomRs()
	{
		return nhomRs;
	}
	public void setNhomRs(ResultSet nhomRs)
	{
		this.nhomRs = nhomRs;
	}
	public ResultSet getTtRs()
	{
		return ttRs;
	}
	public void setTtRs(ResultSet ttRs)
	{
		this.ttRs = ttRs;
	}

	ResultSet nppRs;
	@Override
	public ResultSet getNppRs()
	{

		return nppRs;
	}
	@Override
	public void setNppRs(ResultSet nppRs)
	{
		this.nppRs=nppRs;

	}

	String loaiHoaDon;
	public String getLoaiHoaDon()
	{
		return loaiHoaDon;
	}
	public void setLoaiHoaDon(String loaiHoaDon)
	{
		this.loaiHoaDon = loaiHoaDon;
	}


	String action;
	public String getAction()
	{
		return action;
	}
	public void setAction(String timkiem)
	{
		this.action = timkiem;
	}
	String type;
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}

	String khoId,kbhId;
	ResultSet khoRs;
	ResultSet kbhRs;
	public String getKhoId()
	{
		return khoId;
	}
	public void setKhoId(String khoId)
	{
		this.khoId = khoId;
	}
	public ResultSet getKhoRs()
	{
		return khoRs;
	}
	public void setKhoRs(ResultSet khoRs)
	{
		this.khoRs = khoRs;
	}

	public String getSolo() {
		return solo;
	}
	public void setSolo(String solo) {
		this.solo = solo;
	}
	public ResultSet getSoloRs() {
		return soloRs;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public dbutils getDb() {
		return db;
	}
	public String getQuery() {
	
		String query = "select isnull(sonetId,'') as sonetid,ngayct,DienGiai,nhap,xuat from [dbo].[ufn_TheKho]('"+this.tuNgay+"','"+this.denNgay+"',"+this.nppId+",'"+this.kbhId+"', '"+this.khoId+"','"+this.spId+"','"+this.solo+"' )  order by ngayct,sonetId ";
		System.out.println("query = "+ query );
		return query;
	}
	
}
