package geso.dms.distributor.servlets.dondathang;

import geso.dms.center.beans.dondathang.imp.XLkhuyenmaiTT;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.ctkhuyenmai.ICtkhuyenmai;
import geso.dms.distributor.beans.dondathang.IErpDondathangDoitac;
import geso.dms.distributor.beans.dondathang.IErpDondathangDoitacList;
import geso.dms.distributor.beans.dondathang.IErpDondathangNpp;
import geso.dms.distributor.beans.dondathang.IErpDuyetddhNppList;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangDoitac;
import geso.dms.distributor.beans.dondathang.imp.ErpDondathangDoitacList;
import geso.dms.distributor.beans.dondathang.imp.ErpDuyetddhNppList;
import geso.dms.distributor.beans.trakhuyenmai.ITrakhuyenmai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ErpDondathangDoitacUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	PrintWriter out;
	public ErpDondathangDoitacUpdateSvl() 
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SalesUpERP/index.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);

			Utility util = new Utility();

			String querystring = request.getQueryString();
			userId = util.getUserId(querystring);

			if (userId.length()==0)
				userId = util.antiSQLInspection(request.getParameter("userId")); 

			String id = util.getId(querystring);  	
			IErpDondathangDoitac lsxBean = new ErpDondathangDoitac(id);
			lsxBean.setUserId(userId); 

			String nextJSP = "";

			lsxBean.init(request);

			session.setAttribute("dvkdId", lsxBean.getDvkdId());
			session.setAttribute("kbhId", lsxBean.getKbhId());
			session.setAttribute("nppId", lsxBean.getNppId());
			session.setAttribute("doitacId", lsxBean.getKhId());
			session.setAttribute("khoId", lsxBean.getKhoNhapId());
			session.setAttribute("ngaydh", lsxBean.getNgayyeucau());
			System.out.println(" ngay dh la "+ lsxBean.getNgayyeucau());
			if(querystring.contains("display"))
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacDisplay.jsp";	
			}
			else if(querystring.contains("duyet"))
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacDuyetDisplay.jsp";
			}
			else
			{
				nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
			}

			session.setAttribute("lsxBean", lsxBean);
			response.sendRedirect(nextJSP);
		}		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		 geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
			if(!csdr.__validate_post())
			{
				response.sendRedirect(request.getContextPath() + "/redirect.jsp");
				return;
			}
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 

		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SalesUpERP/index.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			session.setMaxInactiveInterval(30000);

			this.out = response.getWriter();
			IErpDondathangDoitac lsxBean;

			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
			if(id == null)
			{  	
				lsxBean = new ErpDondathangDoitac("");
			}
			else
			{
				lsxBean = new ErpDondathangDoitac(id);
			}

			lsxBean.setUserId(userId);

			String ngayyeucau = util.antiSQLInspection(request.getParameter("ngaychuyen"));
			if(ngayyeucau == null || ngayyeucau.trim().length() <= 0)
				ngayyeucau = getDateTime();
			lsxBean.setNgayyeucau(ngayyeucau);

			String ngaydenghi = util.antiSQLInspection(request.getParameter("ngaydenghi"));
			if(ngaydenghi == null || ngaydenghi.trim().length() <= 0)
				ngaydenghi = "";
			lsxBean.setNgaydenghi(ngaydenghi);

			String ghichu = util.antiSQLInspection(request.getParameter("ghichu").trim());
			if(ghichu == null)
				ghichu = "";
			lsxBean.setGhichu(ghichu);

			String khonhapId = util.antiSQLInspection(request.getParameter("khonhapId"));
			if (khonhapId == null)
				khonhapId = "";				
			lsxBean.setKhoNhapId(khonhapId);

			String dvkdId = util.antiSQLInspection(request.getParameter("dvkdId"));
			if (dvkdId == null)
				dvkdId = "";				
			lsxBean.setDvkdId(dvkdId);

			String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
			if (kbhId == null)
				kbhId = "";				
			lsxBean.setKbhId(kbhId);

			String nppId = util.antiSQLInspection(request.getParameter("nppId"));
			if (nppId == null)
				nppId = "";				
			lsxBean.setNppId(nppId);

			String doitacId = util.antiSQLInspection(request.getParameter("doitacId"));
			if (doitacId == null)
				doitacId = "";				
			lsxBean.setKhId(doitacId);

			System.out.println("doi tac id" + doitacId);

			String vat = util.antiSQLInspection(request.getParameter("ptVat"));
			if (vat == null)
				vat = "";				
			lsxBean.setVat(vat);

			String loaidonhang = util.antiSQLInspection(request.getParameter("loaidonhang"));
			if (loaidonhang == null)
				loaidonhang = "";				
			lsxBean.setLoaidonhang(loaidonhang); 

			String ptChietkhau = util.antiSQLInspection(request.getParameter("ptChietkhau"));
			if (ptChietkhau == null)
				ptChietkhau = "0";				
			lsxBean.setChietkhau(ptChietkhau);


			String iskm = util.antiSQLInspection(request.getParameter("iskm")==null?"0":request.getParameter("iskm"));
			lsxBean.setIsKm(iskm);

			String isdhk = util.antiSQLInspection(request.getParameter("isdhk")==null?"0":request.getParameter("isdhk"));
			lsxBean.setIsdhk(isdhk);

			String isgia = util.antiSQLInspection(request.getParameter("isgia")==null?"0":request.getParameter("isgia"));
			lsxBean.setIsgia(isgia);



			String[] spMa = request.getParameterValues("spMa");
			lsxBean.setSpMa(spMa);

			String[] spTen = request.getParameterValues("spTen");
			lsxBean.setSpTen(spTen);

			String[] dvt = request.getParameterValues("donvi");
			lsxBean.setSpDonvi(dvt);

			String[] soluong = request.getParameterValues("soluong");
			lsxBean.setSpSoluong(soluong);

			String[] soluongton = request.getParameterValues("soluongton");
			lsxBean.setSpSoluongton(soluongton);


			String[] soluongtoncn = request.getParameterValues("soluongtoncn");
			lsxBean.setSptonkhocn(soluongtoncn);

			String[] dongia = request.getParameterValues("dongia");
			lsxBean.setSpGianhap(dongia);

			String[] dongiagoc = request.getParameterValues("dongiagoc");
			lsxBean.setSpGiagoc(dongiagoc);

			String[] chietkhau = request.getParameterValues("chietkhau");
			lsxBean.setSpChietkhau(chietkhau);

			String[] spvat = request.getParameterValues("spvat");
			lsxBean.setSpVat(spvat);

			String[] spScheme = request.getParameterValues("scheme");
			lsxBean.setSpScheme(spScheme);


			String action = request.getParameter("action");

			/*if(action.equals("chot"))
		    {
		    	if(id != null)
				{
			    	boolean kq = lsxBean.chot(id);
					if(!kq)
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpDondathangDoitacList obj = new ErpDondathangDoitacList();
						obj.setLoaidonhang(loaidonhang);

					    obj.setUserId(userId);
					    obj.init("");
						session.setAttribute("obj", obj);							
						String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTac.jsp";
						response.sendRedirect(nextJSP);
					}
				}
		    	else
		    	{
					boolean kq=lsxBean.chot_new();
					if(!kq)
					{
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpDondathangDoitacList obj = new ErpDondathangDoitacList();
						obj.setLoaidonhang(loaidonhang);

						obj.setUserId(userId);
						obj.init("");  
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);

			    		String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTac.jsp";
			    		response.sendRedirect(nextJSP);
					}
		    	}
		    	return;
		    }*/
			if(action.equals("save_donhang_va_khuyen_mai"))
			{	
				String danh_sach_ctkm_Str = request.getParameter("danh_sach_ctkm_Str");
				if(danh_sach_ctkm_Str == null ) danh_sach_ctkm_Str = "";
				lsxBean.setDanh_sach_ctkm_Str(danh_sach_ctkm_Str);

				LuuDonHang( id, userId, loaidonhang, lsxBean, session, request,  response,"");
				return;
			}
			else
			if(action.equals("save"))
			{	
				String danh_sach_ctkm_Str = request.getParameter("danh_sach_ctkm_Str");
				if(danh_sach_ctkm_Str == null ) danh_sach_ctkm_Str = "";
				lsxBean.setDanh_sach_ctkm_Str(danh_sach_ctkm_Str);

				LuuDonHang( id, userId, loaidonhang, lsxBean, session, request,  response,"");
				return;
			}
			else
				if(action.equals("apkhuyenmai_2"))
				{
					String urlParam = "";
					if(spMa.length > 0)
					{
						for(int i = 0; i < spMa.length; i++)
						{
							if(spMa[i].length() > 0)
							{
								urlParam += " { \"ma\":\""+ spMa[i] +"\", \"soluong\":\""+ soluong[i] +"\", \"dongia\":\""+ dongia[i] +"\" },";
							}
						}
						if(urlParam.length() > 0){ urlParam = urlParam.substring(0, urlParam.lastIndexOf(",")); }
					}
					String idAdd = id != null && id.trim().length() > 0 ? id : "0";
					String dh = " { \"id\":\""+ idAdd +"\", \"khId\":\""+ doitacId +"\", \"nppId\":\""+ nppId +"\", \"ngaydh\":\""+ ngayyeucau +"\", \"kbhId\":\""+ kbhId +"\", \"khoId\":\""+ khonhapId +"\" , \"loai\":\"1\" }";				
					urlParam ="sanpham=["+urlParam+"]&donhang="+ dh +"&dieuchinh=0&chuoiSort="; //+chuoisort;
					System.out.println("urlParam : "+ urlParam);
					String url = getServletContext().getInitParameter("path_apkhuyenmai") + "ApKhuyenMai";
					String data = "";

					String status = "0";// kq áp kM // 1 áp đươc  0 là có lỗi
					String msgAKM = "";
					String danh_sach_ctkm_Str = "";
					String dung_khuyen_mai = "";
					String chon_san_pham = "";
					String tra_or = "";
					try {
						data = Utility.sendPost(url, urlParam);

						JsonObject odata =  (JsonObject) new JsonParser().parse(data);   
						status = odata.get("status").getAsString();
						msgAKM = odata.get("msg").getAsString();
						danh_sach_ctkm_Str =  odata.get("data").getAsString();
						dung_khuyen_mai =  odata.get("dung_khuyen_mai").getAsString();
						chon_san_pham =  odata.get("chon_san_pham").getAsString();
						tra_or =  odata.get("tra_or").getAsString();
					} 
					catch (Exception e1) 
					{ 
						e1.printStackTrace(); 
						status = "0";
						msgAKM =" Không thể kết nối tới server.";
					}

					if(status.equals("0")) { lsxBean.setMsg(msgAKM); }
					System.out.println("status : "+ status +" - msgAKM : "+ msgAKM);

					// status = 1 	 danh_sach_ctkm_Str.lenght = 0 : ko có km
					// status = 0   	báo lỗi msgAKM

					/*if(dung_khuyen_mai.equals("0")&& chon_san_pham.equals("0")&& tra_or.equals("0"))	
					{ }*/

					lsxBean.setDanh_sach_ctkm_Str(danh_sach_ctkm_Str);
					if(status.equals("1") && (danh_sach_ctkm_Str == null || danh_sach_ctkm_Str.length() <=0) )
					{
						LuuDonHang( id, userId, loaidonhang, lsxBean, session, request,  response,"  Không có khuyến mãi bình thường");
						return;
					}
					lsxBean.setData(data);

					System.out.println("data : "+ data);

					lsxBean.createRs();
					session.setAttribute("dvkdId", lsxBean.getDvkdId());
					session.setAttribute("kbhId", lsxBean.getKbhId());
					session.setAttribute("nppId", lsxBean.getNppId());
					session.setAttribute("doitacId", lsxBean.getKhId());
					session.setAttribute("khoId", lsxBean.getKhoNhapId());
					session.setAttribute("lsxBean", lsxBean);
					String nextJSP = "";
					if(id == null)
					{ nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp"; }
					else
					{ nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp"; }
					response.sendRedirect(nextJSP);
				}
				else if(action.equals("dieuchinhkm"))
				{
					String showAll = util.antiSQLInspection(request.getParameter("ShowAll"));
					if(showAll == null) { lsxBean.setDieuchinh("1"); }
					else { lsxBean.setDieuchinh("0"); }

					String urlParam = "";
					if(spMa.length > 0)
					{
						for(int i = 0; i < spMa.length; i++)
						{
							if(spMa[i].length() > 0)
							{
								urlParam += " { \"ma\":\""+ spMa[i] +"\", \"soluong\":\""+ soluong[i] +"\", \"dongia\":\""+ dongia[i] +"\" },";
							}
						}
						if(urlParam.length() > 0){ urlParam = urlParam.substring(0, urlParam.lastIndexOf(",")); }

						String[] scheme = request.getParameterValues("Scheme");
						String[] SchemeId = request.getParameterValues("SchemeId");
						//String[] soxuatKm = new String[SchemeId.length];
						String trakmstr = "", chuoiSort = "";
						for(int i=0; i< SchemeId.length; i++)
						{
							System.out.println("SchemeId["+i+"].trim() = " + SchemeId[i].trim() + " - "+ SchemeId[i].trim()+".trakmid");
							String [] chontrakm = request.getParameterValues(SchemeId[i].trim()+".chontrakm");
							for(int j = 0; j < chontrakm.length; j++)
							{
								if(chontrakm[j] != null) 
								{ 
									//System.out.println("scheme : "+ SchemeId[i].trim()+" - chon : "+ chontrakm[j]);
									trakmstr += "{\"ctkmId\":\""+ SchemeId[i] +"\",\"trakmId\":\""+ chontrakm[j] +"\",\"scheme\":\""+ scheme[i] +"\", \"stt\":\""+ i +"\", \"chon\":\"1\"},";
								}
							}
						}
						if(trakmstr.length() > 0) { trakmstr = trakmstr.substring(0, trakmstr.lastIndexOf(",")); }


						String idAdd = id != null && id.trim().length() > 0 ? id : "0";
						String dh = " { \"id\":\""+ idAdd +"\", \"khId\":\""+ doitacId +"\", \"nppId\":\""+ nppId +"\", \"ngaydh\":\""+ ngayyeucau +"\", \"kbhId\":\""+ kbhId +"\", \"khoId\":\""+ khonhapId +"\" , \"loai\":\"1\" }";


						chuoiSort = "["+ trakmstr +"]";

						urlParam ="sanpham=["+urlParam+"]&donhang="+ dh +"&dieuchinh="+ lsxBean.getDieuchinh() +"&chuoiSort="+chuoiSort;
						System.out.println("urlParam : "+ urlParam);
						String url = getServletContext().getInitParameter("path_apkhuyenmai") + "ApKhuyenMai";
						String data = "";

						String status = "0";// kq áp kM // 1 áp đươc  0 là có lỗi
						String msgAKM = "";
						String danh_sach_ctkm_Str = "";
						String dung_khuyen_mai = "";
						String chon_san_pham = "";
						String tra_or = "";
						try {
							data = Utility.sendPost(url, urlParam);
							System.out.println("data : "+ data);
							JsonObject odata =  (JsonObject) new JsonParser().parse(data);   
							status = odata.get("status").getAsString();
							msgAKM = odata.get("msg").getAsString();
							danh_sach_ctkm_Str =  odata.get("data").getAsString();
							System.out.println("danh_sach_ctkm_Str : "+ danh_sach_ctkm_Str);
							dung_khuyen_mai =  odata.get("dung_khuyen_mai").getAsString();
							chon_san_pham =  odata.get("chon_san_pham").getAsString();
							tra_or =  odata.get("tra_or").getAsString();

						} catch (Exception e1) { e1.printStackTrace();
						status = "0";
						msgAKM =" Không thể nối server ";

						}

						if(status.equals("0")) { lsxBean.setMsg(msgAKM); }
						System.out.println("status : "+ status +" - msgAKM : "+ msgAKM);

						/*
					    // status = 1 	 danh_sach_ctkm_Str.lenght = 0 : ko có km
					    // status = 0   	báo lỗi msgAKM
					   if(dung_khuyen_mai.equals("0")&& chon_san_pham.equals("0")&& tra_or.equals("0"))	
					   {
						   // dem di save danh_sach_ctkm_Str
						   // sav
					   }*/

						System.out.println("dung_khuyen_mai : "+ dung_khuyen_mai+" - chon_san_pham : "+ chon_san_pham +" - tra_or : "+ tra_or);
						lsxBean.setDanh_sach_ctkm_Str(danh_sach_ctkm_Str);
						lsxBean.setData(data);
						lsxBean.createRs();
						session.setAttribute("dvkdId", lsxBean.getDvkdId());
						session.setAttribute("kbhId", lsxBean.getKbhId());
						session.setAttribute("nppId", lsxBean.getNppId());
						session.setAttribute("doitacId", lsxBean.getKhId());
						session.setAttribute("khoId", lsxBean.getKhoNhapId());
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = "";
						if(id == null)
						{ nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp"; }
						else
						{ nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp"; }
						response.sendRedirect(nextJSP);
					}
				}
				else
					if(action.equals("apkhuyenmai"))
					{
						//Save donhang truoc
						if(id == null)
						{   
							boolean tao = lsxBean.createNK(request);
							if (!tao)
							{
								lsxBean.createRs();
								session.setAttribute("lsxBean", lsxBean);
								String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp";

								response.sendRedirect(nextJSP);
							}
							else
							{
								id = lsxBean.getId();		
							}						
						}
						else
						{
							boolean temp = lsxBean.updateNK(request);

							if (!temp)
							{
								lsxBean.createRs();
								session.setAttribute("lsxBean", lsxBean);
								String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";

								response.sendRedirect(nextJSP);
							}
						}

						Hashtable<String, Integer> sanpham_soluong = new Hashtable<String, Integer>();
						Hashtable<String, Float> sanpham_dongia = new Hashtable<String, Float>();
						Hashtable<String, Float> sanpham_quycach = new Hashtable<String, Float>();

						String soluong1 = "";
						String spMaKM = "";
						String spSOLUONGKM = "";
						String spDONGIAKM = "";
						float tongiatriDH = 0;

						if(id.trim().length() > 0)
						{	
							//INIT SP VOI QUY CACH NEU TRUONG HOP KHONG PHAI LA DV CHUAN
							dbutils db = new dbutils();

							//XOA HET KM CU NEU CO
							String query = " delete ERP_DONDATHANG_CTKM_TRAKM where DONDATHANGID = '" + id + "' ";
							db.update(query);

							/*query = " select ( select ma from SANPHAM where PK_SEQ = a.sanpham_fk ) as spMA, a.dvdl_fk, b.DVDL_FK as dvCHUAN, a.soluong, a.dongia,  " +
							" 		case when a.dvdl_fk IS null then a.soluong    " +
							" 			 when a.dvdl_fk = b.DVDL_FK then 1  " +
							" 			 else  ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )   end as quycach   " +
							" from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ   " +
							" where a.dondathang_fk = '" + id + "'   " ;*/

							query = "  select ( select ma from SANPHAM where PK_SEQ = a.sanpham_fk ) as spMA, a.dvdl_fk, b.DVDL_FK as dvCHUAN,  " +
							" 		case when a.dvdl_fk = b.DVDL_FK then a.soluong  " +
							"  			 else  a.soluong * ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )   end as soluong, " +
							"  		case when a.dvdl_fk = b.DVDL_FK then a.dongia  " +
							"  			 else  a.dongia / ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )   end as dongia,  " +
							"  		case when a.dvdl_fk = b.DVDL_FK then 1  " +
							"  			 else  ( select SOLUONG1 from QUYCACH where sanpham_fk = a.sanpham_fk and DVDL2_FK = a.dvdl_fk )   end as quycach    " +
							"  from ERP_DONDATHANG_SANPHAM a inner join SANPHAM b on a.sanpham_fk = b.PK_SEQ    " +
							"  where a.dondathang_fk = '" + id + "'  " ;
							ResultSet rsSP = db.get(query);

							if(rsSP != null)
							{
								try 
								{
									while(rsSP.next())
									{
										sanpham_soluong.put(rsSP.getString("spMA"), rsSP.getInt("soluong"));
										sanpham_dongia.put(rsSP.getString("spMA"), rsSP.getFloat("dongia"));
										sanpham_quycach.put(rsSP.getString("spMA"), rsSP.getFloat("quycach"));

										spMaKM += rsSP.getString("spMA") + "__";
										spSOLUONGKM += rsSP.getInt("soluong") + "__";
										spDONGIAKM += rsSP.getString("dongia") + "__";

										soluong1 += rsSP.getString("quycach") + "__";
										tongiatriDH += rsSP.getInt("soluong") * rsSP.getFloat("dongia");
									}
									rsSP.close();
								} 
								catch (Exception e) {}	
							}

							db.shutDown();
						}


						XLkhuyenmaiTT xlkm = new XLkhuyenmaiTT(userId, ngayyeucau, nppId, id); //ngay giao dich trong donhang

						xlkm.setMasp(spMaKM.substring(0, spMaKM.length() - 2).split("__"));
						xlkm.setSoluong(spSOLUONGKM.substring(0, spSOLUONGKM.length() - 2).split("__"));
						xlkm.setDongia(spDONGIAKM.substring(0, spDONGIAKM.length() - 2).split("__"));

						xlkm.setQuycach(soluong1.substring(0, soluong1.length() - 2).split("__"));
						xlkm.setTonggiatriDh(tongiatriDH);
						xlkm.setIdDonhang(id);
						xlkm.setNgaygiaodich(ngayyeucau);
						xlkm.setLoaiDonHang("0");

						xlkm.setHashA(sanpham_soluong);
						xlkm.setHashB(sanpham_dongia);
						xlkm.setHash_QuyCach(sanpham_quycach);

						xlkm.setDieuchinh(false); //Lay truong hop ngau nhien /*****OneOne set lai la True******/

						xlkm.ApKhuyenMai();

						List<ICtkhuyenmai> ctkmResual = xlkm.getCtkmResual();
						System.out.println("+++So xuat khuyen mai duoc huong: " + ctkmResual.size() + "\n");

						if(xlkm.checkConfirm()) //bi dung --> sang trang lua chon
						{
							System.out.println("Bi dung san pham roi...\n");
							session.setAttribute("xlkm", xlkm);
							String nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMaiNppTT.jsp";
							response.sendRedirect(nextJSP);
							return;
						}

						String msg = ""; //nhung ctkm khong thoa
						for(int i = 0; i < ctkmResual.size(); i++)
						{
							ICtkhuyenmai ctkhuyenmai = ctkmResual.get(i);

							//System.out.println("ConFi laf: "+ctkhuyenmai.getConfirm());
							if(ctkhuyenmai.getConfirm() == false) //khong dung chung san pham
							{	
								msg += CreateKhuyenmai(ctkhuyenmai, id, nppId, ngayyeucau, Math.round(tongiatriDH), sanpham_soluong, sanpham_dongia);

								//remove khoi danh sach
								ctkmResual.remove(i);	
								i = i -1;
							}
						}


						if(msg.length() > 0)
						{
							lsxBean.init(request);

							xlkm.DBclose();
							lsxBean.setMsg("khong the them moi 'dondathang_ctkm_trakm' " + msg);
							session.setAttribute("lsxBean", lsxBean);
							String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
							response.sendRedirect(nextJSP);
							return;
						}

						String nextJSP = "";

						if(ctkmResual.size() > 0)
						{
							session.setAttribute("xlkm", xlkm);
							nextJSP = request.getContextPath() + "/pages/Distributor/KhuyenMaiNppTT.jsp";
							response.sendRedirect(nextJSP);
						}
						else
						{	
							xlkm.DBclose();
							lsxBean.init(request);
							;
							session.setAttribute("lsxBean", lsxBean);
							nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
							response.sendRedirect(nextJSP);
							return;
						}
					}
					else

						if(action.equals("duyet"))
						{
							Hashtable<String, String> sanpham_soluong = new Hashtable<String, String>();
							String quanlykho = request.getParameter("quanlykho");
							lsxBean.setQuanlykho(quanlykho);
							System.out.println("Quanlykho: "+quanlykho);

							if (quanlykho.equals("1")) {

								for(int i = 0; i < spMa.length; i++ )
								{
									//System.out.println("---SP MA LA: " + spMa[i]);
									String temID = spMa[i];
									String[] spSOLO = request.getParameterValues(temID + "_spSOLO");
									String[] soLUONGXUAT = request.getParameterValues(temID + "_spSOLUONG");
									String[] spNgayHetHan = request.getParameterValues(temID + "_spNGAYHETHAN");
									String[] spNgayNHAPKHO = request.getParameterValues(temID + "_spNGAYNHAPKHO");

									//System.out.println("   soLUONGXUAT.length  = "+ soLUONGXUAT.length);
									if(soLUONGXUAT != null)
									{
										for(int j = 0; j < soLUONGXUAT.length; j++ )
										{
											if(soLUONGXUAT[j] != null && soLUONGXUAT[j].trim().length() > 0)
											{
												//System.out.println("---KEY SVL: " + ( spId[i] + "__" + spLoai[i] + "__" + spScheme[i].trim() + "__" + spSOLO[j] )  + "   --- GIA TRI: " + soLUONGXUAT[j].replaceAll(",", "") );
												sanpham_soluong.put(spMa[i] + "__" + spSOLO[j]+ "__" + spNgayHetHan[j] + "__" + spNgayNHAPKHO[j], soLUONGXUAT[j].replaceAll(",", "") );								
											}
										}
									}

									lsxBean.setSanpham_Soluong(sanpham_soluong);
								}
							}

							String dungchungkenh = request.getParameter("dungchungkenh");
							if(dungchungkenh == null)
								dungchungkenh = "0";
							lsxBean.setDungchungKenh(dungchungkenh);	

							if(!lsxBean.duyetDH())
							{
								lsxBean.init(request);
								session.setAttribute("lsxBean", lsxBean);
								String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacDuyetDisplay.jsp";
								response.sendRedirect(nextJSP);
							}
							else
							{
								IErpDuyetddhNppList obj = new ErpDuyetddhNppList();
								obj.setUserId(userId);
								obj.init("");

								session.setAttribute("obj", obj);
								String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangNppDuyet.jsp";
								response.sendRedirect(nextJSP);

								/*lsxBean = new ErpDondathangDoitac(id);
						lsxBean.setUserId(userId);
						lsxBean.init();
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacDuyetDisplay.jsp";
	    				response.sendRedirect(nextJSP);*/

							}
						}
						else
						{
							lsxBean.createRs();

							session.setAttribute("dvkdId", lsxBean.getDvkdId());
							session.setAttribute("kbhId", lsxBean.getKbhId());
							session.setAttribute("nppId", lsxBean.getNppId());
							session.setAttribute("doitacId", lsxBean.getKhId());
							session.setAttribute("khoId", lsxBean.getKhoNhapId());
							session.setAttribute("lsxBean", lsxBean);

							String nextJSP = "";

							nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp";
							if(id != null)
							{
								nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
							}

							response.sendRedirect(nextJSP);
						}


		}
	}


	private String CreateKhuyenmai(ICtkhuyenmai ctkm, String id, String nppId, String tungay, long tongGtridh, Hashtable<String, Integer> sp_sl, Hashtable<String, Float> sp_dg)
	{
		String str = "";
		dbutils db = new dbutils();

		try 
		{ 
			db.getConnection().setAutoCommit(false);

			List<ITrakhuyenmai> trakmList = ctkm.getTrakhuyenmai();
			for(int count = 0; count < trakmList.size(); count++)
			{
				//ITrakhuyenmai trakm = ctkm.getTrakhuyenmai().get(0);			
				ITrakhuyenmai trakm = ctkm.getTrakhuyenmai().get(count);

				long tongtienTraKM = 0;
				if(trakm.getType() == 1)
					tongtienTraKM = Math.round(ctkm.getSoxuatKM() * trakm.getTongtien());
				else
				{
					if(trakm.getType() == 2) //tra chiet khau
					{
						System.out.println("___Tong tien tra km: " + ctkm.getTongTienTheoDKKM() + " -- Chiet khau: " + trakm.getChietkhau());
						//Tinh tong gia tri tra khuyen mai theo dieu kien (chu khong phai tren tong gia tri don hang)
						long tonggiatriTrakm = ctkm.getTongTienTheoDKKM();
						tongtienTraKM = Math.round(tonggiatriTrakm * (trakm.getChietkhau() / 100));
						//tongtienTraKM = Math.round(tongGtridh * (trakm.getChietkhau() / 100));
					}
					else
					{
						if(trakm.getHinhthuc() == 1)
						{
							tongtienTraKM = Math.round(ctkm.getSoxuatKM() * trakm.getTongGtriKm());
							System.out.println("Tong tien trakm co dinh: " + tongtienTraKM + "\n");
						}
					}
				}

				/*********************************************************************************/
				if(ctkm.getPhanbotheoDH().equals("0"))
				{
					String msg = "";
					if(msg.trim().length() > 0)
					{
						db.getConnection().rollback();
						return msg;
					}
				}
				/*********************************************************************************/

				if(trakm.getType() == 3) //san pham co so luong co dinh
				{
					if(trakm.getHinhthuc() == 1)
					{

						String sql = "select f.pk_seq as spId, a.soluong, e.GIAMUANPP as dongia, f.ma as spMa  " +
						"from Trakhuyenmai_sanpham a inner join SANPHAM f on a.SANPHAM_FK = f.PK_SEQ " +
						"	inner join BGMUANPP_SANPHAM e on a.sanpham_fk = e.SANPHAM_FK " +
						"where a.TRAKHUYENMAI_FK = '" + trakm.getId() + "' " +
						"and e.BGMUANPP_FK in ( select top(1) a.PK_SEQ " +
						"from BANGGIAMUANPP a inner join BANGGIAMUANPP_NPP b on a.PK_SEQ = b.BANGGIAMUANPP_FK  " +
						"where a.TUNGAY <= '" + tungay + "' and b.NPP_FK = '" + nppId + "' and a.KENH_FK = ( select KBH_FK from ERP_DONDATHANG where PK_SEQ = '" + id + "' ) and a.DVKD_FK = ( select DVKD_FK from ERP_DONDATHANG where PK_SEQ = '" + id + "' ) " +
						"order by a.TUNGAY desc  ) -- and e.GIAMUANPP > 0  and e.trangthai = '1'  ";

						//System.out.println("Query lay gia san pham co dinh la: " + sql + "\n");

						int index = 0;
						ResultSet rsSQl = db.get(sql);
						if(rsSQl != null)
						{
							while(rsSQl.next())
							{
								int slg = Integer.parseInt(rsSQl.getString("soluong")) * (int)ctkm.getSoxuatKM();
								long tonggtri = Math.round(slg * rsSQl.getFloat("dongia"));


								/*********************************************************************************/
								if(ctkm.getPhanbotheoDH().equals("1"))
								{
									String msg = "";
									if(msg.trim().length() > 0)
									{
										db.getConnection().rollback();
										return msg;
									}
								}
								/*********************************************************************************/


								String error = "";
								if(error.length() > 0)
								{
									return error;
								}

								//luu tong gia tri o moi dong sanpham
								//String query = "Insert into ERP_DONDATHANG_CTKM_TRAKM(DONDATHANGID, ctkmId, trakmId, soxuat, tonggiatri, spMa, soluong) values('" + id + "','" + ctkm.getId() + "','" + trakm.getId() + "','" + ctkm.getSoxuatKM() + "','" + Long.toString(tongtienTraKM) + "', '" + rsSQl.getString("spMa").trim() + "', '" + Integer.toString(slg) + "')";
								String query = "Insert into ERP_DONDATHANG_CTKM_TRAKM(DONDATHANGID, ctkmId, trakmId, soxuat, tonggiatri, spMa, soluong) values('" + id + "','" + ctkm.getId() + "','" + trakm.getId() + "','" + ctkm.getSoxuatKM() + "','" + Long.toString(tonggtri) + "', '" + rsSQl.getString("spMa").trim() + "', '" + Integer.toString(slg) + "')";
								System.out.println("1.Chen khuyen mai co dinh: " + query);

								if(!db.update(query))
								{
									db.getConnection().rollback();
									str = query;
									return str;
								}

								//cap nhat kho
								/*query = "Update nhapp_kho set available = available - '" + Integer.toString(slg) + "', booked = booked + '" + Integer.toString(slg) + "' where kho_fk = (select kho_fk from ctkhuyenmai where pk_seq = '" + ctkm.getId() + "') and npp_fk = '" + nppId + "' and sanpham_fk = '" + rsSQl.getString("spId") + "' and kbh_fk = (select kbh_fk from khachhang where pk_seq = '" + khId+ "')";   							
								System.out.println("2.Cap nhat kho: " + query);
								if(!db.update(query))
								{
									db.getConnection().rollback();
									str = query;
									return str;
								}

								query = "update Phanbokhuyenmai set DASUDUNG = DASUDUNG + '" + Long.toString(tongtienTraKM) + "' where ctkm_fk = '" + ctkm.getId() + "' and npp_fk = '" + nppId + "'";
								System.out.println("4.Cap nhat phanbo Phanbokhuyenmai: " + query); 
								if(!db.update(query))
								{
									db.getConnection().rollback(); 
									str = query;
									return str;
								}*/

								index ++;
							}
						}
						rsSQl.close();
					}
					else //tinh so luong san pham nhapp da chon, phai check ton kho tung buoc
					{
						if(trakm.getHinhthuc() == 2)
						{

							String query = "select a.sanpham_fk as spId, c.MA as spMa, isnull(bgmnpp.dongia, '0') as dongia, isnull(b.TONGLUONG, 0) as tongluong " +
							"from TRAKM_NHAPP a inner join TRAKHUYENMAI b on a.trakm_fk = b.PK_SEQ " +
							" inner join SANPHAM c on a.sanpham_fk = c.PK_SEQ " +
							" left join (  select sanpham_fk, GIAMUANPP as dongia  " +
							"				from BGMUANPP_SANPHAM   " +
							"				where BGMUANPP_FK in (  select top(1) a.PK_SEQ " +
							"										from BANGGIAMUANPP a inner join BANGGIAMUANPP_NPP b on a.PK_SEQ = b.BANGGIAMUANPP_FK   " +
							"										where a.TUNGAY <= '" + tungay + "' and b.NPP_FK = '" + nppId + "' and a.KENH_FK = ( select kbh_fk from ERP_DONDATHANG where pk_seq='" + id + "' ) and a.DVKD_FK = ( select dvkd_fk from ERP_DONDATHANG where pk_seq='" + id + "' ) " +
							"										order by a.TUNGAY desc  )   " +
							") bgmnpp on bgmnpp.sanpham_fk=a.sanpham_fk" + 
							" where a.ctkm_fk = '" + ctkm.getId() + "' and a.npp_fk = '" + nppId + "' and a.trakm_fk = '" + trakm.getId() + "' " +
							"order by a.thutuuutien asc";

							System.out.println("5.Query tinh gia km npp chon truoc: " + query);

							ResultSet spkm = db.get(query);

							String sp = "";
							String ma = "";
							String dg = "";
							String tg = "";
							while(spkm.next())
							{
								sp += spkm.getString("spId") + ",";
								dg += spkm.getString("dongia") + ",";
								tg += spkm.getString("tongluong") + ",";
								ma += spkm.getString("spMa") + ",";
							}

							String[] spId = sp.split(",");
							String[] dongia = dg.split(",");
							String[] tongluong = tg.split(",");
							String[] spMa = ma.split(",");

							//CheckTonKho nhung tra khuyen mai da duoc npp chon truoc
							String[] arr = checkTonKhoTraKm(nppId, ctkm, spId, dongia, tongluong, spMa);
							if(arr == null)  //nhung san pham da chon truoc cua tra khuyen mai da het hang trong kho
							{
								db.getConnection().rollback();
								str = "Số lượng những sản phẩm bạn chọn trước trong thiết lập sản phẩm trả khuyến mãi không đủ trong kho";
								System.out.println("Error: " + str + "\n");
								return str;
							}
							else
							{
								/*********************************************************************************/
								if(ctkm.getPhanbotheoDH().equals("1"))
								{
									String msg = "";
									if(msg.trim().length() > 0)
									{
										db.getConnection().rollback();
										return msg;
									}
								}
								/*********************************************************************************/

								//luu tong gia tri o moi dong sanpham
								query = "Insert into ERP_DONDATHANG_CTKM_TRAKM(DONDATHANGID, ctkmId, trakmId, soxuat, tonggiatri, spMa, soluong) values('" + id + "','" + ctkm.getId() + "','" + trakm.getId() + "','" + ctkm.getSoxuatKM() + "','" + arr[2].replaceAll(",", "") + "', '" + arr[3] + "', '" + arr[1].replaceAll(",", "") + "')";
								System.out.println("6.Chen khuyen mai Npp chon truoc: " + query);

								if(!db.update(query))
								{
									db.getConnection().rollback();
									str = query;
									return str;
								}

								//cap nhat kho
								/*query = "Update nhapp_kho set available = available - '" + arr[1].replaceAll(",", "") + "', booked = booked + '" + arr[1].replaceAll(",", "") + "' where kho_fk = (select kho_fk from ctkhuyenmai where pk_seq = '" + ctkm.getId() + "') and npp_fk = '" + nppId + "' and sanpham_fk = '" + arr[0] + "' and kbh_fk = (select kbh_fk from khachhang where pk_seq = '" + khId+ "')";   							
								System.out.println("7.Cap nhat npp_kho: " + query);
								if(!db.update(query))
								{
									db.getConnection().rollback();
									str = query;
									return str;
								}

								query = "update Phanbokhuyenmai set DASUDUNG = DASUDUNG + '" + arr[2].replaceAll(",", "") + "' where ctkm_fk = '" + ctkm.getId() + "' and npp_fk = '" + nppId + "'";
								System.out.println("9.Cap nhat ngan sach Phanbokhuyenmai: " + query);
								if(!db.update(query))
								{
									db.getConnection().rollback();
									str = query;
									return str;
								}*/
							}
						}
					}
				}
				else
				{
					if(trakm.getType() != 3)//tra tien, tra chiet khau
					{
						String query = "Insert into ERP_DONDATHANG_CTKM_TRAKM(DONDATHANGID, ctkmId, trakmId, soxuat, tonggiatri) values('" + id + "','" + ctkm.getId() + "','" + trakm.getId() + "','" + ctkm.getSoxuatKM() + "','" + tongtienTraKM + "')";
						System.out.println("10.Chen khuyen mai tien / ck: " + query);
						if(!db.update(query))
						{
							db.getConnection().rollback();
							str = query;
							return str;
						}

						/*query = "update Phanbokhuyenmai set DASUDUNG = DASUDUNG + '" + Long.toString(tongtienTraKM) + "' where ctkm_fk ='" + ctkm.getId() + "' and npp_fk='" + nppId + "'";
						System.out.println("12.Cap nhat ngan sach Phanbokhuyenmai: " + query);
						if(!db.update(query))
						{
							db.getConnection().rollback();
							str = query;
							return str;
						}*/
					}
				}
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
		} 
		catch (Exception e1)
		{
			try 
			{
				db.getConnection().rollback();
			} 
			catch (SQLException e) {}
			return "Loi khi tao moi ctkm " + ctkm + ", " + e1.toString(); 
		}
		finally
		{
			db.shutDown();
		}

		return str;
	}

	private String[] checkTonKhoTraKm(String nppId, ICtkhuyenmai ctkm, String[] spId, String[] dongia, String[] tongluong, String[] spma) 
	{
		String[] kq = new String[4];

		String msg = "";
		try
		{
			for(int i = 0; i < spId.length; i++)
			{
				int slg = Integer.parseInt(tongluong[i]) * ctkm.getSoxuatKM();
				msg = "";
				if(msg == "")  //thoa ton kho
				{
					kq[0] = spId[i];
					kq[1] = Integer.toString(slg);
					kq[2] = Long.toString(Math.round(slg * Float.parseFloat(dongia[i])));
					//System.out.println("Don gia: " + spId[i] + "- dongia: " + dongia[i] + " - Tong gia tri o buoc nay: " + kq[2] + "\n");
					kq[3] = spma[i];

					return kq;
				}
			}
		}
		catch (Exception e) {
			return null;
		}
		return null;
	}

	private String getDateTime() 
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}
	public void LuuDonHang(String id,String userId,String loaidonhang,IErpDondathangDoitac lsxBean,HttpSession session,HttpServletRequest request, HttpServletResponse response, String msgAdd) throws IOException
	{


		if(id == null)
		{
			boolean kq = lsxBean.createNK(request);

			if(!kq)
			{
				lsxBean.createRs();
				session.setAttribute("dvkdId", lsxBean.getDvkdId());
				session.setAttribute("kbhId", lsxBean.getKbhId());
				session.setAttribute("nppId", lsxBean.getNppId());
				session.setAttribute("doitacId", lsxBean.getKhId());
				session.setAttribute("khoId", lsxBean.getKhoNhapId());
				session.setAttribute("ngaydh", lsxBean.getNgayyeucau());
				session.setAttribute("lsxBean", lsxBean);
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacNew.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				lsxBean.init(request);
				lsxBean.setMsg("Tạo mới đơn hàng "+lsxBean.getId()+" thành công . " +msgAdd);
				session.setAttribute("dvkdId", lsxBean.getDvkdId());
				session.setAttribute("kbhId", lsxBean.getKbhId());
				session.setAttribute("nppId", lsxBean.getNppId());
				session.setAttribute("doitacId", lsxBean.getKhId());
				session.setAttribute("khoId", lsxBean.getKhoNhapId());
				session.setAttribute("ngaydh", lsxBean.getNgayyeucau());
				session.setAttribute("lsxBean", lsxBean);
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
				response.sendRedirect(nextJSP);
				return;
			}
		}
		else
		{
			boolean kq = lsxBean.updateNK(request);

			if(!kq)
			{
				lsxBean.createRs();
				session.setAttribute("dvkdId", lsxBean.getDvkdId());
				session.setAttribute("kbhId", lsxBean.getKbhId());
				session.setAttribute("nppId", lsxBean.getNppId());
				session.setAttribute("doitacId", lsxBean.getKhId());
				session.setAttribute("khoId", lsxBean.getKhoNhapId());
				session.setAttribute("ngaydh", lsxBean.getNgayyeucau());
				session.setAttribute("lsxBean", lsxBean);
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
				response.sendRedirect(nextJSP);
			}
			else
			{
				lsxBean.init(request);
				lsxBean.setMsg("Cập nhật đơn hàng "+lsxBean.getId()+" thành công . " +msgAdd);
				session.setAttribute("dvkdId", lsxBean.getDvkdId());
				session.setAttribute("kbhId", lsxBean.getKbhId());
				session.setAttribute("nppId", lsxBean.getNppId());
				session.setAttribute("doitacId", lsxBean.getKhId());
				session.setAttribute("khoId", lsxBean.getKhoNhapId());
				session.setAttribute("ngaydh", lsxBean.getNgayyeucau());
				session.setAttribute("lsxBean", lsxBean);
				String nextJSP = request.getContextPath() + "/pages/Distributor/ErpDonDatHangDoiTacUpdate.jsp";
				response.sendRedirect(nextJSP);
			}
		}

	}
}
