package geso.dms.center.servlets.hoadon;

import geso.dms.center.beans.donmuahang.IDonmuahangList;
import geso.dms.center.beans.donmuahang.imp.DonmuahangList;
import geso.dms.center.beans.hoadon.IHoaDon;
import geso.dms.center.beans.hoadon.IHoaDon_CTKM;
import geso.dms.center.beans.hoadon.IHoaDon_SanPham;
import geso.dms.center.beans.hoadon.imp.HoaDon;
import geso.dms.center.beans.hoadon.imp.HoaDon_CTKM;
import geso.dms.center.beans.hoadon.imp.HoaDon_SanPham;
import geso.dms.center.beans.nhaphanphoi.INhaphanphoiList;
import geso.dms.center.beans.nhaphanphoi.imp.NhaphanphoiList;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class HoaDonKMNewSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HoaDonKMNewSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int loi=0;
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		dbutils db = new dbutils();
		
		Utility util = new Utility();
		
	    String id = util.antiSQLInspection(request.getParameter("id"));    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String userTen=util.antiSQLInspection(request.getParameter("userTen"));
		session.setAttribute("userId", userId);
		session.setAttribute("userTen", userTen);
		
		String action = request.getParameter("action");		
 		String tenform=request.getParameter("tenform");
 		String nextJSP="";
 		if(tenform.equals("newform")){
 			nextJSP = request.getContextPath() + "/pages/Center/HoaDonKhuyenMaiNew.jsp";
 		}else{
 		nextJSP = request.getContextPath() + "/pages/Center/HoaDonKhuyenMaiUpdate.jsp";
 		}
		IHoaDon dhBean=new HoaDon();
		String ngaygiaodich = util.antiSQLInspection(request.getParameter("ngaygiaodich"));
		dhBean.setNgaygiaodich(ngaygiaodich);
	    dhBean.setId(id);
    	String ngaytao=this.getDateTime();
		String ngaysua=ngaytao;			
    	dhBean.setNgaytao(ngaytao);
    	dhBean.setNgaysua(ngaysua);
    	dhBean.setNguoitao(userId);
    	dhBean.setNguoisua(userId);
    	
    	String vat = util.antiSQLInspection(request.getParameter("VAT"));
    	try{
    		dhBean.setVAT(Double.parseDouble(vat));
    	}catch(Exception er){
    		dhBean.setVAT(10);
    	}
    	String chietkhau=util.antiSQLInspection(request.getParameter("ChietKhau"));
    	try{
    	dhBean.setChietkhau(Double.parseDouble(chietkhau));
    	}catch(Exception er){
    		
    	}
    	String nhaccid=util.antiSQLInspection(request.getParameter("nhaccid"));
    	
    	if(nhaccid==null){
    		nhaccid="";
    	}
    	dhBean.setIdNhaCungCap(nhaccid);
    	
    
    	String kenhbanhang=util.antiSQLInspection(request.getParameter("kenhbanhang"));
    	if(kenhbanhang==null){
    		kenhbanhang="";
    	}

    	dhBean.setIDKenhBanHang(kenhbanhang);
    	session.setAttribute("kenhid", kenhbanhang);
    	
    	String nhappid = util.antiSQLInspection(request.getParameter("nhappid"));
    	session.setAttribute("nhappid",nhappid);
    	if(nhappid == null){
    		nhappid = "";
    	}
    	
    	dhBean.setNppId(nhappid);
    	
    	String dvkdid=util.antiSQLInspection(request.getParameter("dvkdid"));
    	if(dvkdid==null){
    		dvkdid="";
    	}
    	session.setAttribute("dvkdid",dvkdid);
    	
    	String tongtienbvat=util.antiSQLInspection(request.getParameter("SoTienChuaVat"));
    
    	tongtienbvat=tongtienbvat.replace(",","");
    	System.out.println(tongtienbvat);
    	dhBean.setTongtientruocVAT(Double.parseDouble(tongtienbvat));
    	String tongtienavat=util.antiSQLInspection(request.getParameter("SoTienCoVAT"));
    	
    ///	while(tongtienavat.matches(",")){
    		tongtienavat=tongtienavat.replace(",","");
    	
    	dhBean.setTongtiensauVAT(Double.parseDouble(tongtienavat));
    	String ck=util.antiSQLInspection(request.getParameter("ChietKhau"));
    	try{
    	dhBean.setChietkhau(Double.parseDouble(ck));
    	}catch(Exception er){
    		dhBean.setChietkhau(0);
    	}
    	dhBean.setIdDVKD(dvkdid);
    	
    	//Thuc hien lay thong tin nha phan phoi
    	
    // 	String sql_infonpp="select b.pk_seq,b.ten,b.diachi,b.dienthoai,b.masothue,KHOSAP,(select chietkhau from mucchietkhautt c  where b.mucchietkhautt_fk=c.pk_Seq ) as chietkhau from nhaphanphoi b where pk_seq="+ nhappid;
    //	System.out.println(sql_infonpp);

    	String sql_infonpp="select b.pk_seq,b.ten,b.diachi,b.dienthoai,b.masothue,KHOSAP,(select chietkhau from mucchietkhautt c  where b.mucchietkhautt_fk=c.pk_Seq ) as chietkhau from nhaphanphoi b where pk_seq="+ nhappid;

    	ResultSet rs_infonpp=db.get(sql_infonpp);
    	double chietkhau_npp=0;//trong truong hop load khach hang thi moi load lai chiet khau
    	if(rs_infonpp!=null){
    		try{
    		  if(rs_infonpp.next()){
    			  INhaphanphoiList nhapp=new NhaphanphoiList();
    			  nhapp.setDiaChi(rs_infonpp.getString("diachi"));
    			  nhapp.setSodienthoai(rs_infonpp.getString("dienthoai"));
    			  nhapp.setTen(rs_infonpp.getString("ten"));
    			  nhapp.setMaSoThue(rs_infonpp.getString("masothue"));
    			  //chietkhau_npp=rs_infonpp.getDouble("chietkhau");
    			  dhBean.setKhottId(rs_infonpp.getString("khosap"));
    			  dhBean.setInfoNhaPhoiPhoi(nhapp);
    		  }
    		}catch(Exception er){
    			
    		}
    		
    	}
    	
    	
    	//Thuc hien truyen qua cac resultset
    		String sql_getnhapp="select pk_seq,ten from nhaphanphoi where trangthai!='2'";
			ResultSet rs_nhapp=db.get(sql_getnhapp);
			session.setAttribute("rs_nhapp", rs_nhapp);
			
			String sql_getnhacc="select pk_seq,ten from nhacungcap where trangthai!='2'";
			ResultSet rs_nhacc=db.get(sql_getnhacc);
			session.setAttribute("rs_nhacc", rs_nhacc);
			
			String sql_getkenhbanhang="select pk_seq,ten from kenhbanhang where trangthai!='2'";
			ResultSet rs_kenhbanhang=db.get(sql_getkenhbanhang);
			session.setAttribute("rs_kenhbanhang", rs_kenhbanhang);
			
			String sql_getdondathang="select pk_seq from dondathang where trangthai='1'";//lay nhung don dat hang da chot,va chua chuyen het hang sang kho khac
			ResultSet rs_dondathang=db.get(sql_getdondathang);
			session.setAttribute("rs_dondathang", rs_dondathang);
			
			 String sql_khott="select pk_seq,ten from khott where trangthai!='2' ";
	         ResultSet rs_khott=db.get(sql_khott);
	         session.setAttribute("rs_khott", rs_khott);
	         
	         
	        String sql_getdvkd="select pk_seq,donvikinhdoanh as ten from donvikinhdoanh where trangthai='1'";//lay nhung don dat hang da chot,va chua chuyen het hang sang kho khac
			ResultSet rs_dvkd=db.get(sql_getdvkd);
			session.setAttribute("rs_dvkd", rs_dvkd);
			// Lay danh sach cac giam sat ban hang duoc chon
			
		//L???y danh s??ch c??c ch????ng tr??nh khuy???n m??i ???????c ch???n
			List<IHoaDon_CTKM> listctkm=new ArrayList<IHoaDon_CTKM>();
			String[] ctkmlist=request.getParameterValues("chuongtrinhkm");
			String[] tenctkm=request.getParameterValues("tenctkm");
			String[] diengiaictkm=request.getParameterValues("diengiaictkm");
			String[] sudungctkm=request.getParameterValues("ctkmsudung");
			String[] valuecheck=request.getParameterValues("valuecheck");
			if(ctkmlist!=null && ctkmlist.length>0){
				
				for(int n=0;n<ctkmlist.length;n++){
				IHoaDon_CTKM hoadon_ctkm=new HoaDon_CTKM();
				hoadon_ctkm.setDienGiai(diengiaictkm[n]);
				hoadon_ctkm.setTenChuongTrinh(tenctkm[n]);
				hoadon_ctkm.setID(valuecheck[n]);
				System.out.println( "Value  :"+valuecheck[n] );
				hoadon_ctkm.setCTKM(ctkmlist[n]);
				try{
				hoadon_ctkm.setDaSuDung(Double.parseDouble(sudungctkm[n]));
				}catch(Exception er){
				System.out.println("Error 225: HoaDonKMNewSvl - error command :" + er.toString());
				}
				listctkm.add(hoadon_ctkm);
				}
			}
			
    	//	
		dhBean.setListCTKM(listctkm);	
    	//String dondathangid=util.antiSQLInspection(request.getParameter("sodonhangid");
    	//dhBean.setId(dondathangid);
    	//danh sach san pham
    	List<IHoaDon_SanPham>	spList = new ArrayList<IHoaDon_SanPham>();
    	//String[] idsp=request.getParameterValues("idsp");
    	String[]ctkm=request.getParameterValues("ctkmid");
		String[] masp = request.getParameterValues("masp");
		String[] tensp = request.getParameterValues("tensp");
		String[] soluong = request.getParameterValues("soluong");
		String[] donvitinh = request.getParameterValues("donvitinh");
		//String[] dongia = request.getParameterValues("dongia");
		if(action.equals("loadnpp")||action.equals("save")||action.equals("update") || action.equals("duyet") || action.equals("submit")){
			
			if(masp != null)
			{		
			
				int m = 0;
				while(m < masp.length)
				{
				if(masp[m] != "")
				{
					if(soluong[m].length() >  0) //chi them nhung san pham co so luong > 0
					{
						IHoaDon_SanPham sanpham = new HoaDon_SanPham();
						String sql="select pk_seq from sanpham where trangthai!='2' and ma='"+masp[m]+"'";
						//System.out.println("Don Gia Nek :"+sql);
						ResultSet rs_getid=db.get(sql);
						try
						{
						if(rs_getid.next()){
							sanpham.setIdSanPham(rs_getid.getString("pk_seq"));
						}
						
						}catch(Exception er){
							sanpham.setIdSanPham("");
						}
						sanpham.setCTKMID(ctkm[m]);
						sanpham.setMaSanPham(masp[m]);
						sanpham.setTenSanPham(tensp[m]);
						sanpham.setDonViTinh(donvitinh[m]);
						sanpham.setSoLuong(Integer.parseInt(soluong[m]));
						//thuc hien update lai gia khi co truong hop thay doi nha phan phoi,vi moi nha phan phoi co mot bang gia khac nhau
						String sql_getgia="select npp_fk,sanpham_fk,giamuanpp from BANGGIAMUANPP_NPP a inner join BGMUANPP_SANPHAM b on a.bangGiaMuaNpp_fk=b.bgmuanpp_fk where trangthai='1' and  npp_fk="+nhappid +"  and sanpham_fk="+sanpham.getIdSanPham();
						System.out.println("HoaDonNewSvl : line 249 :"+sql_getgia);
						ResultSet rs_getgia =db.get(sql_getgia);
						try{
						if(rs_getgia.next()){
							sanpham.setDonGia(rs_getgia.getDouble("giamuanpp"));
						}
						else{
							sanpham.setDonGia(0);
						}
						}catch(Exception er){
							
						}
						spList.add(sanpham);
					}
				}
				m++;
			}	
		}
		dhBean.setListSanPham(spList);		
    	}
		//Kiem tra nhung san pham khi chot don hang
		Hashtable<String, Integer> spThieuList = new Hashtable<String, Integer>();
		if(masp != null)
		{
			int m = 0;			
			while(m < masp.length)
			{				
				if(masp[m].length() > 0)
				{
					//spThieuList.put(pk_seq, new Integer(10));
					int sl = 0;
					String sql_gettonkho="select available from TONKHOICP where  kho = '"+dhBean.getKhottId()+"'  and MASP in (select ma from sanpham where ma='"+masp[m].trim()+"')";
					//System.out.println(sql_gettonkho);
					ResultSet slspAvailable = db.get(sql_gettonkho);
					if(slspAvailable != null)
					{
						try
						{
						if(	slspAvailable.next()){
							sl = slspAvailable.getInt("available");
						}else{
						
						}
							slspAvailable.close();
							
							//lay nhung sp da co trong don hang
							if(id != null)
							{
								int slg = 0;
								String sqlquery = "select ISNULL(soluong, 0) as soluong from HOADON_SP where HOADON_FK = '" + id + "' and SANPHAM_FK in (select pk_seq from sanpham where ma='" + masp[m].trim() + "')";
								ResultSet SlgRs = db.get(sqlquery);	
								if(SlgRs != null)
								{
									if(SlgRs.next())
									{
										slg = SlgRs.getInt("soluong");
									}
									SlgRs.close();
								}
								sl = sl+slg;
							}							
						} 
						catch(Exception e) {}
						
						if(soluong[m] == "")
							soluong[m] = "0";
						//System.out.println(sl);
						if(Integer.parseInt(soluong[m]) > sl)
							spThieuList.put(masp[m],sl);
					}
				}														
				m++;
			}
		}
		dhBean.setSpThieuList(spThieuList);
 		session.setAttribute("obj", dhBean);
		if(action.equals("save"))//thuc hien save lai don h??ng
		{
			if(id == null)
			{
				if (!(dhBean.saveDonHangKhuyenMai()))
				{									
					response.sendRedirect(nextJSP);	    
				}else{
					//Sau khi them thanh cong
					String sohoadon=dhBean.getId();
					IHoaDon obj=new HoaDon();
					obj.setMessage("Chuc Mung,Ban Vua Them Thanh Cong Hoa Don So :"+ sohoadon+" Vui Long Nhap Thong Tin De Tao Hoa Don Moi");
					session.setAttribute("obj", obj);
					response.sendRedirect(nextJSP);	    			    									
				}
			}		
		}else if(action.equals("loadnpp")){//tr?????ng h???p load l???i nh?? ph??n ph???i
			session.setAttribute("nhappid", nhappid);
			//Thuc Hien load cac thong tin ve nha phan phoi
			//dhBean.setChietkhau(chietkhau_npp);//trong truong hop thay doi nha pp thi se cap nhat lai chiet khau
			//Doi voi don hang khuyen mai thi cho chiet khau =0
			dhBean.setChietkhau(0);
			dhBean.setListCTKMInit();
			response.sendRedirect(nextJSP);
			
		}else if(action.equals("reload")){
			
			IHoaDon obj=new HoaDon();
			//mac dinh cho loai hoa don =0
			session.setAttribute("obj", obj);
			response.sendRedirect(nextJSP);
		}else if(action.equals("duyet")){
			//thuc hien sua lai don hang
			if(dhBean.EditDonHangKhuyenMai()){
				    IDonmuahangList  obj = new DonmuahangList();
				    obj.setUserId(userId);
				    obj.createDdhlist("");
					session.setAttribute("obj", obj);
					nextJSP = request.getContextPath() + "/pages/Center/DonMuaHang.jsp";
					response.sendRedirect(nextJSP);
			}
			else{
					session.setAttribute("obj", dhBean);
					nextJSP = request.getContextPath() + "/pages/Center/HoaDonKhuyenMaiUpdate.jsp";
					response.sendRedirect(nextJSP);
			}
		}else if(action.equals("submit")){
			response.sendRedirect(nextJSP);	   
		}
		
	}

}
