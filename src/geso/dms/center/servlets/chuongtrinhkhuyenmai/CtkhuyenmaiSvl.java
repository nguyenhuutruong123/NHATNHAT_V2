package geso.dms.center.servlets.chuongtrinhkhuyenmai;

import geso.dms.center.beans.ctkhuyenmai.*;
import geso.dms.center.beans.ctkhuyenmai.imp.*;
import geso.dms.center.beans.dieukienkhuyenmai.ISanpham;
import geso.dms.center.beans.dieukienkhuyenmai.imp.Sanpham;
import geso.dms.center.util.Utility;
import geso.dms.distributor.db.sql.dbutils;



import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;













import com.oreilly.servlet.MultipartRequest;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.WritableSheet;

public class CtkhuyenmaiSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	PrintWriter out;

	public CtkhuyenmaiSvl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		this.out = response.getWriter();
		System.out.println("vao doget ctkhuyenmaisvl");
		HttpSession session = request.getSession();
		if(!geso.dms.distributor.util.Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		ICtkhuyenmaiList obj = new CtkhuyenmaiList();

		Utility util = new Utility();
		out = response.getWriter();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		out.println(userId);

		if (userId.length() == 0)
			userId = request.getParameter("userId");

		String action = util.getAction(querystring);
		out.println(action);

		String ctkmId = util.getId(querystring);

		if (action.equals("ngunghd"))
		{
			String msg = ngunghd(ctkmId,userId);
			if (msg.length() > 0)
				obj.setMessage(msg);
		} else if (action.equals("delete"))
		{
			String msg = Delete(ctkmId);
			if (msg.length() > 0)
				obj.setMessage(msg);
		}else if(action.equals("phanbo"))
		{
			String msg = PhanBoKm(ctkmId);
			if (msg.length() > 0)
				obj.setMessage(msg);
		}
		obj.setRequestObj(request);
		obj.setUserId(userId);
		
		String view =  request.getParameter("view");
		if(view == null) view = "";
		obj.setView(view);
		
		System.out.println("\nINIT CTKM");
		obj.init_user();
		obj.init("");
		session.setAttribute("obj", obj);
		session.setAttribute("dkkmDien_giai", "");
		session.setAttribute("dkkmTungay", "");
		session.setAttribute("dkkmDenngay", "");

		String nextJSP = request.getContextPath() + "/pages/Center/ChuongTrinhKhuyenMai.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String contentType = request.getContentType();
		ICtkhuyenmaiList obj = new CtkhuyenmaiList();
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");

		String diengiai = request.getParameter("diengiai");
		String tungay = request.getParameter("tungay");
		String denngay = request.getParameter("denngay");
		String trangthai = request.getParameter("trangthai");
		String npp = request.getParameter("npp");
		String vung =request.getParameter("vung");
		String khuvuc =request.getParameter("khuvuc");
		obj.setDiengiai(diengiai);
		obj.setTungay(tungay);
		obj.setDenngay(denngay);
		obj.setTrangthai(trangthai);
		obj.setNpp(npp);
		obj.setvung(vung);
		obj.setkhuvuc(khuvuc);
		
		String action = request.getParameter("action");
		
		
		String view =  request.getParameter("view");
		if(view == null) view = "";
		obj.setView(view);
		
		
	
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		

		obj.setDiengiai(diengiai);
		obj.setTungay(tungay);
		obj.setDenngay(denngay);
		obj.setTrangthai(trangthai);
		obj.setUserId(userId);
		obj.init_user();
		
		
		if (action == null)
		{
			action = "";
		}
		
		Utility util = new Utility();
		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0)) 
	{
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=THONGTINUPLOAD.xls");
		WritableWorkbook w = jxl.Workbook.createWorkbook(response.getOutputStream());


		dbutils db=new dbutils();
		
		
			
		MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8"); 
		
		
		
		userId = util.antiSQLInspection(multi.getParameter("userId"));
		obj.setUserId(userId);
		tungay = multi.getParameter("tungay");
		if(tungay == null)
			tungay = "";
		obj.setTungay(tungay);

		npp = multi.getParameter("npp");
		if(npp == null)
			npp = "";
		obj.setNpp(npp);

		denngay = multi.getParameter("denngay");
		if(denngay == null)
			denngay = "";
		obj.setDenngay(denngay);

		vung = multi.getParameter("vung");
		if(vung == null)
			vung = "";
		obj.setvung(vung);

		khuvuc = multi.getParameter("khuvuc");
		if(khuvuc == null)
			khuvuc = "";
		obj.setkhuvuc(khuvuc);

		trangthai = multi.getParameter("trangthai");
		if(trangthai == null)
			trangthai = "";
		obj.setTrangthai(trangthai);
		diengiai = multi.getParameter("diengiai");
		if(diengiai == null)
			diengiai = "";
		obj.setDiengiai(diengiai);

		Enumeration files = multi.getFileNames(); 
		String filenameu  ="";
		while (files.hasMoreElements())
		{
			String name = (String)files.nextElement();
			filenameu = multi.getFilesystemName(name); 
			System.out.println("name :   "+name);;
		}
		
		String filename=    "C:\\java-tomcat\\data\\"+ filenameu;
		if (filename.length() > 0)
		{
			//doc file excel
			File file = new File(filename);
			System.out.println("filename  "+file);
			Workbook workbook;

			int indexRow=9;

			try 
			{
				String sott = "";
				String mactkm="";
				String mactkmErp ="";
				String diengiaikm ="";
				String loaict ="";
				String kenh="";
				String tungaykm="";
				String denngaykm="";
				String kho ="";
				String loaikh="";
				String loaingansach ="";
				String maspmua ="";
				String soluongmua ="";
				String masptra ="";
				String soluongtra ="";
				String manppapdung ="";
				String apdungcho = "";
//				String hangcuahang = "";
//				String nhomkhachhang="";
//				String apdungchokh="";
				String npptuchay = "";
				String chietkhau = "";
				// 0 sellin && sellout, 1 sell in, 2 sell out
				workbook = Workbook.getWorkbook(file);
				Sheet[] sheet1 = workbook.getSheets();
				int soct = 0;
				boolean error=false;
				boolean flag = true;
				// phân phối đã chọn ko?
				db.getConnection().setAutoCommit(false);
				if(!error)
				{
					for(int t=0;t<sheet1.length;t++)
					{
						//TAO RA 1 SHEET LUU THONG TIN UPLOAD KHONG THANH CONG
						WritableSheet sheetwrite = w.createSheet(sheet1[t].getName(), t);
						sheetwrite.addCell(new Label(0, 0, "THÔNG TIN UPLOAD KHUYẾN MÃI KHÔNG THÀNH CÔNG:"+ sheet1[t].getName()));
						Sheet sheet=sheet1[t];
						Cell[] cells ;
						indexRow=3;

						for(int i= indexRow; i < sheet.getRows();i++)
						{
							cells = sheet.getRow(i);
							if (cells.length>0)
							{ 
								if(cells[1] !=null && cells[1].getContents().toString().length() >0 )
								{
									soct++;
									sott = getValue(sheet,0,i);
									mactkm = getValue(sheet, 1,i,false);
									mactkmErp = getValue(sheet,2,i);
									diengiaikm = getValue(sheet, 3,i);
									loaict	=  getValue(sheet, 4,i);
									kenh	=	 getValue(sheet, 5,i);

									tungay	=	 getValue(sheet, 6,i);

									denngay	=	 getValue(sheet, 7,i);

									kho =	 getValue(sheet, 8,i);
									loaikh	=	 getValue(sheet, 9,i,false);
									apdungcho = getValue(sheet, 10, i) == null?"0":getValue(sheet, 10, i);
									loaingansach	=	 getValue(sheet, 11,i);
									maspmua =  getValue(sheet, 12,i);
									soluongmua = getValue(sheet, 13,i);;
									masptra = getValue(sheet, 14,i);
									soluongtra = getValue(sheet, 15,i);

									manppapdung =getValue(sheet, 16,i,false);
									npptuchay = getValue(sheet,17,i);
									chietkhau = getValue(sheet,18,i);
//									
//									hangcuahang =getValue(sheet, 16,i,false);
//									nhomkhachhang =getValue(sheet, 17,i,false);
//									apdungchokh =getValue(sheet, 18,i,false);
//									if(nhomkhachhang.trim().length()==0)
//									{
//										nhomkhachhang=null;
//									}
									String errror="";
									if(mactkm.trim().equals(""))
									{
										errror= "Mã CTKM  trong hàng "+ (indexRow+1) +" không hợp lệ " ;
									}
									System.out.println(" apdungcho: "+apdungcho);


									String diengiaikmcodh = "";
									if(diengiaikm.trim().length() <= 0 )
									{
										///errror=errror+  "Diễn giải  trong hàng "+ (indexRow+1)+" không hợp lệ " ;
										diengiaikmcodh = diengiaikm;
										String sqldg = "select N'Mua "+soluongmua+" '+b.DIENGIAI+' '+a.TEN as ten from SANPHAM a inner join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ where MA = '"+maspmua+"' ";
										ResultSet rs = db.get(sqldg);
										if(rs.next())
										{
											diengiaikm = rs.getString("ten")+" - ";

										}
										rs.close();

										sqldg = "select N'Tặng "+soluongtra+" '+b.DIENGIAI+' '+a.TEN as ten from SANPHAM a inner join DONVIDOLUONG b on a.DVDL_FK = b.PK_SEQ where MA = '"+masptra+"' ";
										rs = db.get(sqldg);
										if(rs.next())
										{
											diengiaikm += rs.getString("ten");

										}
										rs.close();

									}

									if(loaict.trim().length() <= 0 )
									{
										errror=errror+  "Loại chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}
									if(apdungcho.trim().length() <= 0 )
									{
										errror=errror+  "Loại áp dụng cho chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

									if(kenh.trim().length() <= 0 )
									{
										errror=errror+  "Kênh chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}


									if(tungay.trim().length() <= 0 || tungay.trim().length() < 10)
									{
										errror=errror+  "Từ ngày chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ hoặc không phải định dạng Text (yyyy-mm-dd) " ;
									}

									if(denngay.trim().length() <= 0 || denngay.trim().length() < 10)
									{
										errror=errror+  "Đến ngày chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ hoặc không phải định dạng Text (yyyy-mm-dd) " ;
									}

									if(kho.trim().length() <= 0 )
									{
										errror=errror+  "Kho chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

//									if(loaikh.trim().length() <= 0 )
//									{
//										errror=errror+  "Loại khách hàng chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
//									}

									if(loaingansach.trim().length() <= 0 )
									{
										errror=errror+  "Loại ngân sách chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

									if(maspmua.trim().length() <= 0 )
									{
										errror=errror+  "Mã sản phẩm mua chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

									if(soluongmua.trim().length() <= 0 )
									{
										errror=errror+  "Số lượng mua chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

									if(maspmua.trim().length() <= 0 )
									{
										errror=errror+  "Mã sản phẩm trả chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

									if(soluongmua.trim().length() <= 0 )
									{
										errror=errror+  "Số lượng trả chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

									if(manppapdung.trim().length() <= 0 )
									{
										errror=errror+  "Mã nhà phân phối chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
									}

//									if(apdungchokh.trim().length() <= 0 )
//									{
//										errror=errror+  "Vui lòng điền đầy đủ áp dụng KH chưa có doanh số  chương trình KM trong hàng "+ (indexRow+1)+" không hợp lệ " ;
//									}




									if(!errror.equals(""))
									{
										sheetwrite.addCell(new Label(0, indexRow, errror));
										System.out.println("1.Loi: "+errror);
										flag = false;
									}
									else
									{

										if(flag)
										{
											int soDong=0;
											String nguoitao = userId;
											String ngaytao = getDateTime();
											String	sql="select count(pk_seq) as num from ctkhuyenmai where scheme = '"+mactkm+"' ";
											ResultSet rscheck = db.get(sql);
											rscheck.next();
			
											soDong = rscheck.getInt("num");
											rscheck.close();
											if(soDong == 0)
											{
												
												if(!kiemtra_scheme(db,kho,mactkm))
												{		
													errror = "Scheme khuyến mại: "+mactkm+" đã tồn tại !";
													sheetwrite.addCell(new Label(0, indexRow, errror));
													System.out.println("2.Loi");
													flag = false;
												}
													
												
												sql = "Insert into Ctkhuyenmai(scheme, diengiai, tungay, denngay, loaict, ngansach, ngaytao, nguoitao, ngaysua, nguoisua,kho_fk, loaingansach, tilevoiprimary,ApDUNGCHODHLE,PHANBOTHEODONHANG, LEVEL_PHANBO,LOAIKHUYENMAI) " +
														"values((select dbo.ftBoDau2(N'" + mactkm + "')), N'" + diengiaikm + "', '" + tungay + "' , '" + denngay + "' , '" + loaict + "','0', " +
														"'" + ngaytao + "', '" +nguoitao + "', '" + ngaytao + "', '" +nguoitao + "','"+ kho +"', '" + loaingansach + "', '', '','0', '',"+apdungcho+")";
												if(flag)
													soDong=db.updateReturnInt(sql);
												//System.out.println("[soDong]"+soDong +"[khachhang.Create]" + sql);
												if(soDong<0)
												{
													System.out.println("Tạo CTKM không thành công :" + sql);
													sheetwrite.addCell(new Label(0, indexRow , "Tạo CTKM không thành công  :" + sql));

													flag = false;
													System.out.println("4.Loi");

												}
												String ctkmCurrent = "";
												String query = "select IDENT_CURRENT('Ctkhuyenmai') as ctkmId";
												System.out.println(query);
												ResultSet rsCtkm = db.get(query);						
												rsCtkm.next();
												ctkmCurrent = rsCtkm.getString("ctkmId");

												rsCtkm.close();
												if(loaikh != null)
													if(loaikh.trim().length() > 0)
													{
														query = "Insert CTKHUYENMAI_LOAIKH(ctkm_fk, LOAIKH_Fk)  " +
																"select '" + ctkmCurrent + "', pk_seq from LOAIKHACHHANG where 1=1  ";
														if(loaikh.trim().length() > 0)
															query += " and pk_seq in ( select dbo.trim( data) from dbo.Split(N'"+loaikh+"',';') ) ";
														if(flag)
															if(!db.update(query))
															{

																sheetwrite.addCell(new Label(1, indexRow , "Tạo CTKM không thành công  :" + query));
																System.out.println("1.Loi: "+query);
																flag = false;
															}
													}


												if(kenh != null)
													if(kenh.trim().length() > 0)
													{
														query = "Insert CTKHUYENMAI_KBH(ctkm_fk, KBH_Fk)  " +
																"select '" + ctkmCurrent + "', pk_seq from Kenhbanhang where pk_seq >= 0  ";
														query += " and pk_seq in ( select dbo.trim( data) from dbo.Split(N'"+kenh+"',';') ) ";
														if(flag)
															if(!db.update(query))
															{

																sheetwrite.addCell(new Label(1, indexRow , "Tạo CTKM không thành công  :" + query));
																System.out.println("1.Loi: "+query);
																flag = false;
															}
													}
												
//												if(hangcuahang.trim().length()!=0)
//													{
//														query = "Insert  into CTKHUYENMAI_HANGCUAHANG(CTKM_FK,HCH_FK)  " +
//																"select '" + ctkmCurrent + "', pk_seq from hangcuahang where pk_seq >= 0  ";
//														query += " and pk_seq in ( select dbo.trim( data) from dbo.Split(N'"+hangcuahang+"',';') ) ";
//														if(flag)
//															if(!db.update(query))
//															{
//
//																sheetwrite.addCell(new Label(1, indexRow , "Tạo CTKM không thành công  :" + query));
//																System.out.println("1.Loi: "+query);
//																flag = false;
//															}
//													}
										
												
												
												
												

												// tạo điều kiện khuyến mãi loại bất trong
												query = "Insert into Dieukienkhuyenmai(diengiai, tongluong, tongtien, loai, ngaytao, nguoitao, ngaysua, nguoisua) values(";
												query = query + "N'ĐK CTKM MUA " +soluongmua  + " sản phẩm mã "+maspmua+" TẶNG "+soluongtra+" sản phẩm mã "+masptra+"',null, " + null + ", '1', '" + ngaytao + "', '" + nguoitao + "', '" + ngaytao + "', '" + nguoitao + "')";
												if(flag)
													if(!db.update(query))
													{
														System.out.println("2.Loi: "+query);
														sheetwrite.addCell(new Label(2, indexRow , "Tạo Dieukienkhuyenmai không thành công  :" + query));
														flag = false;

													}
												String dkkkmCurrent = "";
												query = "select IDENT_CURRENT('Dieukienkhuyenmai') as dkkmId";

												ResultSet rsDkkm = db.get(query);						
												rsDkkm.next();
												dkkkmCurrent = rsDkkm.getString("dkkmId");
												rsDkkm.close();
												query = "Insert into dieukienkm_sanpham(dieukienkhuyenmai_fk, sanpham_fk, soluong,batbuoc) "
														+ " values('" + dkkkmCurrent + "',( select top(1) pk_seq from sanpham where ma ='" + maspmua + "'), "+soluongmua+",1)";
												if(flag)
													if(!db.update(query))
													{
														System.out.println("3.Loi: "+query);
														System.out.println("Tạo mới dieukienkm_sanpham không thành công :" + query);
														sheetwrite.addCell(new Label(3, indexRow , "Tạo mới dieukienkm_sanpham không thành công  :" + query));

														flag = false;

													}
												// tạo mới ctkm_dkkm mặc định là and,và thứ tự đk là 0
												query = "Insert into ctkm_dkkm(ctkhuyenmai_fk, dkkhuyenmai_fk, pheptoan, thutudieukien)"
														+ " values('" + ctkmCurrent + "', '" + dkkkmCurrent + "', '1', 0)";
												if(flag)
													if(!db.update(query))
													{
														System.out.println("4.Loi: "+query);	
														sheetwrite.addCell(new Label(0, indexRow , "Tạo mới dieukienkm_sanpham không thành công  :" + query));
														flag = false; 
													}
												// Tạo mới trả khuyến mãi, loại trả sản phẩm,hình thức bất kỳ trong
												query = "Insert into Trakhuyenmai(diengiai, tongluong, tongtien, chietkhau, loai, hinhthuc, ngaytao, nguoitao, ngaysua, nguoisua) values(";
												query = query + "N'Scheme "+mactkm+".Tặng " + soluongtra + " sản phẩm mã "+masptra+" ',null ,null,null, '3', '1', '" + ngaytao + "', '" + nguoitao + "', '" + ngaytao + "', '" + nguoitao + "')";
												if(flag)
													if(!db.update(query))
													{
														System.out.println("5.Loi: "+query);
														sheetwrite.addCell(new Label(4, indexRow , "Tạo mới Trakhuyenmai không thành công  :" + query));
														flag =  false; 
													}
												String trakmCurrent = "";
												query = "select IDENT_CURRENT('Trakhuyenmai') as trakmId";

												ResultSet rsTrakm = db.get(query);						
												rsTrakm.next();
												trakmCurrent = rsTrakm.getString("trakmId");
												rsTrakm.close();
												query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk, soluong, dongia)"
														+ "  select '" + trakmCurrent + "',pk_seq, dbo.trim( data), null "
														+ " from sanpham,dbo.Split(N'"+soluongtra+"',';')  where ma in ( select dbo.trim( data) from dbo.Split(N'"+masptra+"',';'))";
												if(flag)
													if (!db.update(query))
													{
														System.out.println("6.Loi: "+query);
														sheetwrite.addCell(new Label(5, indexRow, "Tạo mới trả khuyến mãi sản phẩm ko thành công:" + query));

														flag = false;

													}	
												query = "Insert into ctkm_trakm(ctkhuyenmai_fk, trakhuyenmai_fk, pheptoan, thutu)"
														+ "  values('" + ctkmCurrent + "', '" + trakmCurrent + "', '1', '0')";
												if(flag)
													if(!db.update(query))
													{
														System.out.println("7.Loi: "+query);
														sheetwrite.addCell(new Label(6, indexRow, "Tạo mới ctkm_trakm ko thành công:" + query));
														flag = false; 
													}


												query ="insert into ctkm_npp(CTKM_FK,NPP_FK,CHON,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA)  "
														+ "select '" + ctkmCurrent +"',pk_seq,'1',"+nguoitao+",'"+nguoitao+"','"+ngaytao+"','"+ngaytao+"'"
														+ "from  nhaphanphoi where mafast in ( select dbo.trim( data) from dbo.Split(N'"+manppapdung+"',';'))";
												System.out
												.println("them nppad: "+query);
												if(flag)
													if(!db.update(query))
													{
														System.out.println("8.Loi: "+query);
														sheetwrite.addCell(new Label(7, indexRow, "Tạo mới chương trình khuyến mãi NPP ko thành công:" + query));

														flag = false;
													}
												
												
											}

											else
											{


												nguoitao = userId;
												ngaytao = getDateTime();
												
												boolean flagup = false;
												String CtkmID = "";
												String query = "select pk_seq as ID from ctkhuyenmai where scheme = N'"+mactkm+"'";

												ResultSet rsctkm = db.get(query);						
												rsctkm.next();
												CtkmID = rsctkm.getString("ID");
												rsctkm.close();
												sql="select count(*) as num from donhang_ctkm_trakm where  ctkmid='" + CtkmID+ "' and DONHANGID in (select PK_SEQ from DONHANG where TRANGTHAI!=2) ";
												System.out.println(sql);
												ResultSet rs = db.get(sql);
												try 
												{
													rs.next();
													if(rs.getInt("num") > 0)
													{
														flagup = true;
														rs.close();
													}
													rs.close();
												}
												catch (Exception e) {e.printStackTrace();}

												sql="select count(*) as num from ERP_DONDATHANG_CTKM_TRAKM where  ctkmid='" + CtkmID+ "' and DONDATHANGID in (select PK_SEQ from ERP_DONDATHANG where TRANGTHAI!=3) ";
												System.out.println(sql);
												rs = db.get(sql);
												try 
												{
													rs.next();
													if(rs.getInt("num") > 0)
													{
														flagup = true;
														rs.close();
													}
													rs.close();
												}
												catch (Exception e) {e.printStackTrace();}

												boolean ktpb = true;
												sql="select COUNT(*) as ispb  "+
														"		from PHANBOKHUYENMAI pb where pb.CTKM_FK= '"+CtkmID+"' "+
														"						and NGANSACH!=0 ";
												System.out.println(sql);
												rs = db.get(sql);
												try 
												{
													rs.next();
													if(rs.getInt("ispb") > 0)
													{
														ktpb = false;
														rs.close();
													}
													rs.close();
												}
												catch (Exception e) {e.printStackTrace();}
												//khuyen mai tich luy

										
												
												if(!flagup )
												{

													
													query = "Update Ctkhuyenmai set diengiai = N'" +diengiaikm + "', tungay = '" + tungay + "', " +
															"denngay = '" + denngay + "', loaict = '" + loaict + "', ngaysua = '" + ngaytao + "', nguoisua = '" + nguoitao + "'," +
															"kho_fk ='"+ kho +"', LOAIKHUYENMAI = '" + apdungcho + "' " +
															"where pk_seq = '" + CtkmID + "'";
													System.out.println("Truong Hop 1 :"+query);
													if(flag)
														if(!db.update(query))
														{
															flag =  false; 
															sheetwrite.addCell(new Label(1, indexRow, "Cập nhật Ctkhuyenmai không thành công:" + query));
															System.out.println("2.Loi Update Ctkhuyenmai: "+query);
														}
													if(ktpb == false)
													{
														query = "select count(*) as so from Ctkhuyenmai where loaingansach <> '"+loaingansach+"' and pk_seq = '" + CtkmID + "' ";
														ResultSet rsckpb = db.get(query);
														if(rsckpb != null)
														{
															rsckpb.next();
															if(rsckpb.getInt("so") > 0)
															{
																query = "delete from phanbokhuyenmai where ctkm_FK = '" +CtkmID + "'";
																if(!db.update(query))
																{
																	flag =  false; 
																	sheetwrite.addCell(new Label(1, indexRow, "Xóa phân bổ không thành công:" + query));
																	System.out.println("2.Loi Update Ctkhuyenmai: "+query);
																}

																query = "Update Ctkhuyenmai set LOAINGANSACH ='"+loaingansach+"' " +
																		"where pk_seq = '" + CtkmID + "'";
																System.out.println("Truong Hop 1 :"+query);
																if(flag)
																	if(!db.update(query))
																	{
																		flag =  false; 
																		sheetwrite.addCell(new Label(1, indexRow, "Cập nhật Loại ngân sách không thành công:" + query));
																		System.out.println("2.Loi Update Ctkhuyenmai: "+query);
																	}

															}
															rsckpb.close();
														}

													}
													else
													{
														query = "Update Ctkhuyenmai set LOAINGANSACH ='"+loaingansach+"' " +
																"where pk_seq = '" + CtkmID + "'";
														System.out.println("Truong Hop 1 :"+query);
														if(flag)
															if(!db.update(query))
															{
																flag =  false; 
																sheetwrite.addCell(new Label(1, indexRow, "Cập nhật Loại ngân sách không thành công:" + query));
																System.out.println("2.Loi Update Ctkhuyenmai: "+query);
															}
													}

													String dkkkmCurrent = "";
													query = "select DKKHUYENMAI_FK as dkkmId from ctkm_dkkm where CTKHUYENMAI_FK = '"+CtkmID+"'";

													ResultSet rsDkkm = db.get(query);						
													rsDkkm.next();
													dkkkmCurrent = rsDkkm.getString("dkkmId");
													rsDkkm.close();
													if(checkExitsDKKM(db, dkkkmCurrent, CtkmID))
													{
														
														
														// tu dong them dieu kien moi doi voi ctkm chua dung neu co chinh sua
														// tạo điều kiện khuyến mãi loại bất trong
														query = "Insert into Dieukienkhuyenmai(diengiai, tongluong, tongtien, loai, ngaytao, nguoitao, ngaysua, nguoisua) values(";
														query = query + "N'ĐK CTKM MUA " +soluongmua  + " sản phẩm mã "+maspmua+" TẶNG "+soluongtra+" sản phẩm mã "+masptra+"',null, " + null + ", '1', '" + ngaytao + "', '" + nguoitao + "', '" + ngaytao + "', '" + nguoitao + "')";
														if(flag)
															if(!db.update(query))
															{
																System.out.println("2.Loi: "+query);
																sheetwrite.addCell(new Label(2, indexRow , "Tạo Dieukienkhuyenmai không thành công  :" + query));
																flag = false;

															}
														 dkkkmCurrent = "";
														query = "select IDENT_CURRENT('Dieukienkhuyenmai') as dkkmId";

														 rsDkkm = db.get(query);						
														rsDkkm.next();
														dkkkmCurrent = rsDkkm.getString("dkkmId");
														rsDkkm.close();
														
														query = "Insert into dieukienkm_sanpham(dieukienkhuyenmai_fk, sanpham_fk, soluong,batbuoc) "
																+ " values('" + dkkkmCurrent + "',( select top(1) pk_seq from sanpham where ma ='" + maspmua + "'), "+soluongmua+",1)";
														if(flag)
															if(!db.update(query))
															{
																System.out.println("3.Loi: "+query);
																System.out.println("Tạo mới dieukienkm_sanpham không thành công :" + query);
																sheetwrite.addCell(new Label(3, indexRow , "Tạo mới dieukienkm_sanpham không thành công  :" + query));

																flag = false;

															}
														// tạo mới ctkm_dkkm mặc định là and,và thứ tự đk là 0
														query = "Insert into ctkm_dkkm(ctkhuyenmai_fk, dkkhuyenmai_fk, pheptoan, thutudieukien)"
																+ " values('" + CtkmID + "', '" + dkkkmCurrent + "', '1', 0)";
														if(flag)
															if(!db.update(query))
															{
																System.out.println("4.Loi: "+query);	
																sheetwrite.addCell(new Label(0, indexRow , "Tạo mới dieukienkm_sanpham không thành công  :" + query));
																flag = false; 
															}
														
														
														if(flag==false)
														{
															sheetwrite.addCell(new Label(3, indexRow, "L !:" + query));
															System.out.println("4.Loi Update trakhuyenmai_sanpham: "+query);
														}
														
													}
													else
													{
														query = "delete from dieukienkm_sanpham where DIEUKIENKHUYENMAI_FK = '" +dkkkmCurrent + "'";
														db.update(query);
														query = "Insert into dieukienkm_sanpham(dieukienkhuyenmai_fk, sanpham_fk, soluong,batbuoc) "
																+ " values('" + dkkkmCurrent + "',( select top(1) pk_seq from sanpham where ma ='" + maspmua + "'), "+soluongmua+",1)";
														if(flag)
															if(!db.update(query))
															{
																flag =  false; 
																sheetwrite.addCell(new Label(2, indexRow, "Cập nhật dieukienkm_sanpham không thành công:" + query));
																System.out.println("3.Loi Update dieukienkm_sanpham: "+query);
															}
														query = "update dieukienkhuyenmai set diengiai = N'ĐK CTKM MUA " +soluongmua  + " sản phẩm mã "+maspmua+" TẶNG "+soluongtra+" sản phẩm mã "+masptra+"' where pk_seq = "+dkkkmCurrent;
														if(flag)
															if(!db.update(query))
															{
																flag =  false; 
																sheetwrite.addCell(new Label(2, indexRow, "Cập nhật dieukienkhuyenmai không thành công:" + query));
																System.out.println("3.Loi Update dieukienkm_sanpham: "+query);
															}
													}
													String trakmid = "";
													query = "select TRAKHUYENMAI_FK from CTKM_TRAKM where CTKHUYENMAI_FK = '"+CtkmID+"'";
													ResultSet rstrakm = db.get(query);						
													rstrakm.next();
													trakmid = rstrakm.getString("TRAKHUYENMAI_FK");
													rstrakm.close();
													if(checkExits(db,trakmid))
													{
														
														// Tạo mới trả khuyến mãi, loại trả sản phẩm,hình thức bất kỳ trong
														query = "Insert into Trakhuyenmai(diengiai, tongluong, tongtien, chietkhau, loai, hinhthuc, ngaytao, nguoitao, ngaysua, nguoisua) values(";
														query = query + "N'Scheme "+mactkm+".Tặng " + soluongtra + " sản phẩm mã "+masptra+" ',null ,null,null, '3', '1', '" + ngaytao + "', '" + nguoitao + "', '" + ngaytao + "', '" + nguoitao + "')";
														if(flag)
															if(!db.update(query))
															{
																System.out.println("5.Loi: "+query);
																sheetwrite.addCell(new Label(4, indexRow , "Tạo mới Trakhuyenmai không thành công  :" + query));
																flag =  false; 
															}
														String trakmCurrent = "";
														query = "select IDENT_CURRENT('Trakhuyenmai') as trakmId";

														ResultSet rsTrakm = db.get(query);						
														rsTrakm.next();
														trakmCurrent = rsTrakm.getString("trakmId");
														rsTrakm.close();
														query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk, soluong, dongia)"
																+ "  select '" + trakmCurrent + "',pk_seq, dbo.trim( data), null "
																+ " from sanpham,dbo.Split(N'"+soluongtra+"',';')  where ma in ( select dbo.trim( data) from dbo.Split(N'"+masptra+"',';'))";
														if(flag)
															if (!db.update(query))
															{
																System.out.println("6.Loi: "+query);
																sheetwrite.addCell(new Label(5, indexRow, "Tạo mới trả khuyến mãi sản phẩm ko thành công:" + query));

																flag = false;

															}	
														query = "Insert into ctkm_trakm(ctkhuyenmai_fk, trakhuyenmai_fk, pheptoan, thutu)"
																+ "  values('" + CtkmID + "', '" + trakmCurrent + "', '1', '0')";
														if(flag)
															if(!db.update(query))
															{
																System.out.println("7.Loi: "+query);
																sheetwrite.addCell(new Label(6, indexRow, "Tạo mới ctkm_trakm ko thành công:" + query));
																flag = false; 
															}
														
														
														if(flag =  false)
														{
															sheetwrite.addCell(new Label(3, indexRow, "Trả khuyến mãi   !:" + query));
															System.out.println("4.Loi Update trakhuyenmai_sanpham: "+query);
														}
													}else
													{

														
														
														

														query = "delete from trakhuyenmai_sanpham where TRAKHUYENMAI_FK = '" + trakmid+ "'";
														db.update(query);


														query = "Insert into trakhuyenmai_sanpham(trakhuyenmai_fk, sanpham_fk, soluong, dongia)"
																+ "  select '" + trakmid + "',pk_seq, dbo.trim( data), null "
																+ " from sanpham,dbo.Split(N'"+soluongtra+"',';')  where ma in ( select dbo.trim( data) from dbo.Split(N'"+masptra+"',';'))";
														if(flag)
															if(!db.update(query))
															{
																flag =  false; 
																sheetwrite.addCell(new Label(3, indexRow, "Cập nhật trakhuyenmai_sanpham không thành công:" + query));
																System.out.println("4.Loi Update dieukienkm_sanpham: "+query);
															}

														query = "update TRAKHUYENMAI set diengiai = N'Scheme "+mactkm+".Tặng " + soluongtra + " sản phẩm mã "+masptra+" ' where pk_seq = "+trakmid;
														if(flag)
															if(!db.update(query))
															{
																flag =  false; 
																sheetwrite.addCell(new Label(2, indexRow, "Cập nhật TRAKHUYENMAI không thành công:" + query));
																System.out.println("3.Loi Update dieukienkm_sanpham: "+query);
															}
													}
													query = "delete CTKHUYENMAI_LOAIKH where ctkm_fk = '" + CtkmID + "'";
													db.update(query);
													if(loaikh != null)
														if(loaikh.trim().length() > 0)
														{
															query = "Insert CTKHUYENMAI_LOAIKH(ctkm_fk, LOAIKH_Fk)  " +
																	"select '" + CtkmID + "', pk_seq from LOAIKHACHHANG where 1=1   ";
															if(loaikh.trim().length() > 0)
																query += " and pk_seq in ( select dbo.trim( data) from dbo.Split(N'"+loaikh+"',';') ) ";
															System.out.println("loai khachhang "+query);
															if(flag)
																if(!db.update(query))
																{

																	sheetwrite.addCell(new Label(1, indexRow , "Tạo CTKM không thành công  :" + query));
																	System.out.println("1.Loi: "+query);
																	flag = false;
																}
														}


													query = "delete CTKHUYENMAI_KBH where ctkm_fk = '" + CtkmID + "'";
													db.update(query);
													if(kenh != null)
														if(kenh.trim().length() > 0)
														{
															query = "Insert CTKHUYENMAI_KBH(ctkm_fk, KBH_Fk)  " +
																	"select '" + CtkmID + "', pk_seq from Kenhbanhang where pk_seq >= 0  ";
															query += " and pk_seq in ( select dbo.trim( data) from dbo.Split(N'"+kenh+"',';') ) ";
															if(flag)
																if(!db.update(query))
																{

																	sheetwrite.addCell(new Label(1, indexRow , "Tạo CTKM không thành công  :" + query));
																	System.out.println("1.Loi: "+query);
																	flag = false;
																}
														}
													query = "delete CTKHUYENMAI_HANGCUAHANG where ctkm_fk = '" + CtkmID+ "'";
													if(flag)
														db.update(query);
													
//													if(hangcuahang.trim().length()!=0)
//													{
//														query = "Insert  into CTKHUYENMAI_HANGCUAHANG(CTKM_FK,HCH_FK)  " +
//																"select '" + CtkmID + "', pk_seq from hangcuahang where pk_seq >= 0  ";
//														query += " and pk_seq in ( select dbo.trim( data) from dbo.Split(N'"+hangcuahang+"',';') ) ";
//														if(flag)
//															if(!db.update(query))
//															{
//
//																sheetwrite.addCell(new Label(1, indexRow , "Tạo CTKM không thành công  :" + query));
//																System.out.println("1.Loi: "+query);
//																flag = false;
//															}
//													}
										
												
												
													
													query = "delete ctkm_npp where ctkm_fk = '" + CtkmID+ "'";
													if(flag)
														db.update(query);
													query ="insert into ctkm_npp(CTKM_FK,NPP_FK,CHON,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA)  "
															+ "select '" + CtkmID +"',pk_seq,'1',"+nguoitao+",'"+nguoitao+"','"+ngaytao+"','"+ngaytao+"'"
															+ "from  nhaphanphoi where mafast in ( select dbo.trim( data) from dbo.Split(N'"+manppapdung+"',';'))";
													if(flag)
														if(!db.update(query))
														{

															sheetwrite.addCell(new Label(9, indexRow , "Update ctkm_npp không thành công  :" + query));
															System.out.println("10.Loi Update Ctkm_npp: "+query);
															flag = false;

														}
												}
												else
												{

													//kiem tra denngay cua ctkhuyenmai co lon hon ngay lon nhat cua don hang khong?
													query="select max(ngaynhap) as  maxngaynhap from donhang  a inner join donhang_ctkm_trakm b on "+
															" a.pk_seq=b.donhangid where    ctkmid="+CtkmID +
															" having max(ngaynhap) <='"+denngay+"'"; 
													System.out.println("Check Ngay 1 :"+query);

													rs = db.get(query);
													try 
													{

														if(rs.next())
														{


															String maxngaynhap = rs.getString("maxngaynhap");
															query = "Update Ctkhuyenmai  set denngay = '" + denngay + "' , ngaysua = '" + ngaytao + "', nguoisua = '" +nguoitao + "' where pk_seq = '" + CtkmID + "' and '"+maxngaynhap+"' <= '"+denngay+"' ";
															System.out.println("Update Truong Hop 2"+query);
															if(!db.update(query))
															{
																sheetwrite.addCell(new Label(5, indexRow , "Update Ctkhuyenmai không thành công  :" + query));
																System.out.println("6.Loi Update Ctkhuyenmai: "+query);
																flag = false;
															}
															if(flag)
																if(diengiaikmcodh.length() > 0)
																{
																	query="Update Ctkhuyenmai set diengiai = N'" + diengiaikmcodh + "' where pk_seq = '" +CtkmID + "'";
																	if(!db.update(query))
																	{
																		sheetwrite.addCell(new Label(6, indexRow , "Update Ctkhuyenmai không thành công  :" + query));
																		System.out.println("7.Loi Update Ctkhuyenmai: "+query);
																		flag = false;
																	}
																}
															rs.close();
														}else{
															//this.msg = "II. Khong the cap nhat CT Khuyen Mai, Da Co Nha Phan Phoi Ap Chuong Trinh Khuyen Mai Toi Ngay Nay";
															//return false;
															if(diengiaikmcodh.length() > 0)
															{
																query="Update Ctkhuyenmai set diengiai = N'" + diengiaikmcodh + "' where pk_seq = '" +CtkmID + "'";
																if(!db.update(query))
																{
																	sheetwrite.addCell(new Label(6, indexRow , "Update Ctkhuyenmai không thành công  :" + query));
																	System.out.println("7.Loi Update Ctkhuyenmai: "+query);
																	flag = false;
																}
															}
														}

													}
													catch (Exception e) {
														sheetwrite.addCell(new Label(7, indexRow , "Update Ctkhuyenmai không thành công  :" + query));
														System.out.println("8.Loi Update Ctkhuyenmai: "+query);
														flag = false;
													}
												}
												query ="insert into ctkm_npp(CTKM_FK,NPP_FK,CHON,NGUOITAO,NGUOISUA,NGAYTAO,NGAYSUA)  "
														+ "select '" + CtkmID +"',pk_seq,'1',"+nguoitao+",'"+nguoitao+"','"+ngaytao+"','"+ngaytao+"'"
														+ "from  nhaphanphoi where mafast in ( select dbo.trim( data) from dbo.Split(N'"+manppapdung+"',';'))"
														+ " and not exists (select 1 from ctkm_npp where npp_fk = pk_seq and CTKM_FK = '" + CtkmID +"')";
												System.out
												.println("Insert npp: "+query);
												if(flag)
													if(!db.update(query))
													{

														sheetwrite.addCell(new Label(9, indexRow , "Update ctkm_npp không thành công  :" + query));
														System.out.println("10.Loi Update Ctkm_npp: "+query);
														flag = false;

													}



												System.out.println("indexRow1111: "+flag+" Soct: "+soct);
											}




										}
									}

								}
							}
							indexRow++;

						}
					}
					System.out.println("indexRow: "+flag+" Soct: "+soct);
					if(flag && soct > 0)
					{

						obj.setMessage("Upload thành công "+soct+" khuyến mãi !");
						db.getConnection().commit();
						db.getConnection().setAutoCommit(true);
						obj.init("");
						session.setAttribute("obj", obj);

						db.shutDown();
						String nextJSP = request.getContextPath() + "/pages/Center/ChuongTrinhKhuyenMai.jsp";
						response.sendRedirect(nextJSP);

					}
					else
					{
						db.getConnection().rollback();
						obj.setMessage("Upload không thành công ! Vui lòng xem file !");

						obj.init("");
						session.setAttribute("obj", obj);
						w.write();
						db.shutDown();

						w.close();

					}

				}
			}catch(Exception er)
			{
				er.printStackTrace();
				try {
					db.getConnection().rollback();
					w.write();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				obj.setMessage("Upload không thành công ! Vui lòng xem file !");

				obj.init("");
				session.setAttribute("obj", obj);

				db.shutDown();

				try {
					w.close();
				} catch (WriteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String nextJSP = request.getContextPath() + "/pages/Center/ChuongTrinhKhuyenMai.jsp";
				response.sendRedirect(nextJSP);
			}
		}
	}
	else {
		obj.setRequestObj(request);
		System.out.println("oalalalalala111111111111111111111");
		if (action.equals("Tao moi"))
		{
			System.out.println("oalalalalala");
			ICtkhuyenmai ctkmBean = (ICtkhuyenmai) new Ctkhuyenmai("");
			
			System.out.println("in chung ::::::::;;"+ctkmBean.getInchung());
			ctkmBean.setUserId(userId);
			ctkmBean.setView(view);
			ctkmBean.init_user();
			ctkmBean.createRS();
			ctkmBean.CreateVung();
			session.setAttribute("ctkmBean", ctkmBean);
			session.setAttribute("dkkmDien_giai", "");
			session.setAttribute("dkkmTungay", "");
			session.setAttribute("dkkmDenngay", "");
			String nextJSP = request.getContextPath() + "/pages/Center/ChuongTrinhKhuyenMaiNew.jsp";
			obj.DBclose();
			response.sendRedirect(nextJSP);
			return;
		} else if (action.equals("view") || action.equals("next") || action.equals("prev"))
		{
			String search = getSearchQuery(request, obj);
			obj.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			obj.init(search);
			obj.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			session.setAttribute("obj", obj);
			response.sendRedirect(request.getContextPath() + "/pages/Center/ChuongTrinhKhuyenMai.jsp");
		} else if(action.equals("toExcel"))
		{
			OutputStream outstream = null;
			try
			{
				WorkbookSettings wbSettings = new WorkbookSettings();
				wbSettings.setLocale(new Locale("en", "EN"));			
				response.setContentType("application/vnd.ms-excel");
			    response.setHeader("Content-Disposition", "attachment; filename=ChuongTrinhKhuyenMai.xls");
			    outstream = response.getOutputStream();
			    WritableWorkbook workbook = jxl.Workbook.createWorkbook(response.getOutputStream());
			    
				String search = getSearchQuery(request, obj);
				
				ResultSet rs=obj.getDb().get(search);
				WritableSheet sheet =null;
				int sheetIndex=0;
				ICtkhuyenmai e=new Ctkhuyenmai("");
				 e.setUserId(userId);
				System.out.println("[Excel]"+search);
				try
				{
					while(rs.next())
					{
						int rowIndex=10;
				    	e.setId(rs.getString("ctkmId")  );
				    	e.setKhuvucId("");
				    	e.initExcel();
				    	sheet = workbook.createSheet(rs.getString("scheme"), sheetIndex);
				    	workbook.setColourRGB(Colour.RED, 0xff, sheetIndex, sheetIndex);
				    	CreateHeaderCTKM( sheet,e);
				    	List<IDieukienkm> listDkkm =e.getDkkmList();
				    	List<ITrakm> listTrakm = e.getTrakmList();
				    	for(int i=0;i<listDkkm.size();i++)
				    	{
				    		IDieukienkm o=listDkkm.get(i);
				    		IDieukienDetail dkDetail=o.getDieukienDetail();
				    		List<ISanpham> sp_dkkmList=dkDetail.getSpList();
				    		this.CreateHeader(sheet, o,i);
				    		rowIndex=this.CreateContent(sheet, sp_dkkmList,i);
				    		rowIndex++;
				    	}
				    	int nextRow=rowIndex+2;
				    	for(int i=0;i<listTrakm.size();i++)
				    	{
					    	ITrakm   ITrakm=listTrakm.get(i);
				    		ITrakmDetail tkmDetail=ITrakm.getTraDetail();
				    		List<ISanpham> sp_dkkmList=tkmDetail.getSpList();
				    		CreateHeaderTraKM(sheet,ITrakm,nextRow,i);
				    		rowIndex=this.CreateContentTraKM(sheet, sp_dkkmList,nextRow+8,i);
				    		rowIndex++;
				    	}
				    	ResultSet Dsnpp =e.getDsnppSelected();
				    	CreateHeaderNPP(sheet,rowIndex+2);
				    	CreateContentNPP(sheet,Dsnpp,rowIndex+4);
				    	sheetIndex++;
					}
					if(rs!=null)
					{
						rs.close();
						e.DbClose();
					}
				} catch (SQLException ex)
				{
					ex.printStackTrace();
				}
				if(sheetIndex==0)
				{
					obj.setMessage("Không có báo cáo trong thời gian đã chọn!");
					
				}else 
				{
					workbook.write();		
					workbook.close();
				}
				return;
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.print("Exception...");
			}
			finally
		    {
		     if (outstream != null)
		    	 outstream.close();
		    }
			obj.DBclose();
		}else 
		{
			obj.setRequestObj(request);
			String search = getSearchQuery(request, obj);
			obj.setUserId(userId);
			
			obj.init(search);
			session.setAttribute("obj", obj);
			session.setAttribute("userId", userId);
			System.out.println("????????????????????????????????????????????");
			response.sendRedirect(request.getContextPath() + "/pages/Center/ChuongTrinhKhuyenMai.jsp");
		}
	}
}
	
	
	private String ngunghd(String ctkmId,String userId)
	{
		
			dbutils db = new dbutils();
			try
			{
			

				db.getConnection().setAutoCommit(false);
				
				String query="update ctkhuyenmai set ngunghoatdong=1 ,THOIGIAN_NGUNG_HOATDONG = GETDATE(),NGUOI_NGUNGHD ="+userId+" where pk_seq="+ctkmId+" and isnull(ngunghoatdong,0)=0";		
				if(!db.update(query))
				{
					db.update("rollback");
					return "Lỗi cập nhật "+query;
				}
				
				db.getConnection().commit();
				db.getConnection().setAutoCommit(true);
				if(db!=null)
					db.shutDown();
				return "";	
			} catch (Exception e)
			{
		
				e.printStackTrace();
				db.shutDown();
				return "Không thể phân bổ CTKM này !"+e.getMessage();
			}
	
	}

	private String getSearchQuery(HttpServletRequest request, ICtkhuyenmaiList obj)
	{
		String diengiai = request.getParameter("diengiai");
    	if ( diengiai == null)
    		diengiai = "";
    	obj.setDiengiai(diengiai);
    	
    	String tungay = request.getParameter("tungay");
    	if (tungay == null)
    		tungay = "";    	
    	obj.setTungay(tungay);
    	
    	String denngay = request.getParameter("denngay");
    	if (denngay == null)
    		denngay = "";    	
    	obj.setDenngay(denngay);
    	
    	String trangthai = request.getParameter("trangthai");
    	if (trangthai == null)
    		trangthai = "1";    	
    	obj.setTrangthai(trangthai);
    	
    	
    	String nppId = request.getParameter("nppId");
    	if (nppId == null)
    		nppId = "";    	
    	obj.setNppId(nppId);
    	
    	String vungId = request.getParameter("vungId");
    	if (vungId == null)
    		vungId = "";    	
    	obj.setVungId(vungId);
    	
    	String khuvucId = request.getParameter("khuvucId");
    	if (khuvucId == null)
    		khuvucId = "";    	
    	obj.setKhuvucId(khuvucId);
    	
    	String phanbo = request.getParameter("phanbo");
    	if (phanbo == null)
    		phanbo = "";    	
    	obj.setPhanbo(phanbo);

    	
    	String str = " '1' as isnppTao, "; 
		if(!obj.getPhanloai().equals("2"))
		{
			str = " case when a.nppTao_Fk = '"+ obj.getNppTaoId() +"' then '1' else '0' end as isnppTao, ";
		}
    	
    	String 
    	query =
    	"	select distinct  "+str+" isnull(a.ngunghoatdong,0) as ngunghd, a.pk_seq as ctkmid, a.scheme, isnull(a.diengiai, '') as diengiai, a.tungay, a.denngay "+
    	"		, isnull(a.loaict, '1') as type, isnull(a.ngansach, '') as ngansach, a.dasudung "+
    	"		, isnull(a.ngaytao, '') as ngaytao, isnull(a.ngaysua, '') as ngaysua, b.ten as nguoitao, c.ten as nguoisua,isnull(a.LOAINGANSACH,0) as LoaiNganSach,(select COUNT(CTKM_FK) from PhanBoKhuyenMai where CTKM_FK=a.PK_SEQ)as SoNpp, (select count(*) from phanbokhuyenmai where ctkm_fk = a.pk_seq ) as ispb "+ 
    	"	from ctkhuyenmai a  "+
    	"		inner join nhanvien b on a.nguoitao = b.pk_seq  "+
    	"		inner join nhanvien c on a.nguoisua = c.pk_seq "; 
    	if(vungId.length()>0 ||khuvucId.length()>0 ||nppId.length()>0||phanbo.length()>0)
    	{
	    	query+=
	    	"		inner join "+
	    	"		( "+
	    	"			select CTKM_FK "+
	    	"			from CTKM_NPP km  "+
	    	"				inner join NHAPHANPHOI npp on npp.PK_SEQ=km.NPP_FK	 "+
	    	"			where  1=1  and isnull(chon,0)=1  " ;
	    	if(nppId.length()>0)
	    	query+=
	    	" and  km.NPP_FK in ( "+nppId+" ) ";
	    	if(khuvucId.length()>0)
	    	query+=" and npp.KHUVUC_FK ="+khuvucId+"  ";
	    	if(vungId.length()>0)
	    	query+=" and npp.KHUVUC_FK in ( select pk_seq from KHUVUC where VUNG_FK="+vungId+" ) ";
	    	if(phanbo.equals("0"))
	    	{
		    	query+=
		    	"	and km.CTKM_FK not in ( select ctkm_fk from PHANBOKHUYENMAI where 1=1 " ;
		    	if(nppId.length()>0)
		    		query+=" and npp_fk ="+nppId+"  ";
		    	query+=" ) ";
	    	}else if(phanbo.equals("1"))
	    	{
	    		query+=
		    	"	and km.CTKM_FK  in ( select ctkm_fk from PHANBOKHUYENMAI where 1=1 " ;
		    	if(nppId.length()>0)
		    		query+=" and npp_fk ="+nppId+"  ";
		    	query+=" ) ";
	    	}
	    	query+=
	    	" ) pb on pb.CTKM_FK=a.PK_SEQ ";
    	}
    	query+="	where a.pk_seq > 0 and a.loaict in (1,2,5) ";
		
    	if (diengiai.length()>0){
			query = query + " and upper(a.diengiai) like upper('%" + diengiai + "%') or upper(a.SCHEME) like upper('%" + diengiai + "%')";			
    	}
    	if(tungay.length()>0 && denngay.length()>0) {
			query = query + " and ( ( a.tungay <= '" + convertDate(tungay) + "' and a.denngay >= '" + convertDate(tungay) + "') OR"
					+ " ( a.tungay <= '" + convertDate(denngay) + "' and a.denngay >= '" + convertDate(denngay) + "')  )";			

    	}else {
    	if (tungay.length()>0){
			query = query + " and a.tungay <= '" + convertDate(tungay) + "' and  a.denngay >= '" + convertDate(tungay) + "'";			
    	}
    	
    	if (denngay.length()>0){
			query = query + " and a.tungay <= '" + convertDate(denngay) + "' and  a.denngay >= '" + convertDate(denngay) + "'";			
    	}
    	}

		if(trangthai.equals("1"))
		{
			query = query + " and tungay <= (SELECT CONVERT(VARCHAR(10),DATEADD(day,0,GETDATE()),120))" +
							" and denngay >=(SELECT CONVERT(VARCHAR(10),DATEADD(day,0,GETDATE()),120))";
		}
		
		if(trangthai.equals("2"))
		{
			query = query + " and ( tungay > (SELECT CONVERT(VARCHAR(10),DATEADD(day,0,GETDATE()),120))" +
							" or denngay <( SELECT CONVERT(VARCHAR(10),DATEADD(day,0,GETDATE()),120)))";	
		}
		System.out.println("[CTKM]"+query);
    	return query;
	}

	private String convertDate(String date)
	{
		if (!date.contains("-"))
			return "";
		String[] arr = date.split("-");
		if (arr[0].length() < arr[2].length())
			return arr[2] + "-" + arr[1] + "-" + arr[0];
		return date;
	}

	private String Delete(String ctkmId)
	{
		dbutils db = new dbutils();
		
		ResultSet rs = db.get("select count(*) as num from donhang_ctkm_trakm where ctkmId ='" + ctkmId + "'");
		try
		{
			db.getConnection().setAutoCommit(false);
			rs.next();
			if (!rs.getString("num").equals("0"))
			{
				rs.close();
				db.getConnection().rollback();
				return "Chương trình khuyến mãi này đã được sử dụng.";
			}
			rs.close();

			// Xoa Cac Bang Con Truoc
			if(!db.update("delete from ctkm_dkkm where ctkhuyenmai_fk='" + ctkmId + "'"))
			{
				db.getConnection().rollback();
				return "Lỗi xóa dkkm";
			}
			if(!db.update("delete from ctkm_trakm where ctkhuyenmai_fk='" + ctkmId + "'"))
			{
				db.getConnection().rollback();
				return "Lỗi xóa trakm";
			}
			if(!db.update("delete from ctkm_npp where ctkm_fk='" + ctkmId + "'"))
			{
				db.getConnection().rollback();
				return "Lỗi xóa npp";
			}
			if(!db.update("delete from CTKHUYENMAI_HANGCUAHANG where CTKM_FK='" + ctkmId + "'"))
			{
				db.getConnection().rollback();
				return "Lỗi xóa HANGCUAHANG";
			}
			if(!db.update("delete from PHANBOKHUYENMAI where CTKM_FK='" + ctkmId + "'"))
			{
				db.getConnection().rollback();
				return "Lỗi xóa PHANBOKHUYENMAI";
			}
			
			// Xoa Bang Cha
			
			if(!db.update("delete from ctkhuyenmai where pk_seq = '" + ctkmId + "'"))
			{
				db.getConnection().rollback();
				return "Không thể xóa chương trình khuyến mãi này.";
			}
			
			/*
			 * db.update("delete from ctkhuyenmai where pk_seq = '" + ctkmId + "'");
			 * db.update("commit");
			 */

			db.getConnection().commit();
			return "";

		} 
		catch (SQLException e)
		{	
			try { db.getConnection().rollback(); } catch (SQLException e1) { e1.printStackTrace(); }
			return "Không thể xóa chương trình khuyến mãi này.";
		}
		finally { try { db.getConnection().setAutoCommit(true); } catch (SQLException e) { e.printStackTrace(); } }
	}
	
	private String PhanBoKm(String ctkmId)
	{
		dbutils db = new dbutils();
		ResultSet rs = db.get("select count(*) as num from donhang_ctkm_trakm where ctkmId ='" + ctkmId + "'");
		try
		{
			rs.next();
			if (!rs.getString("num").equals("0"))
			{
				rs.close();
				return "Chương trình khuyến mãi này đã được sử dụng.";
			}
			rs.close();

			db.getConnection().setAutoCommit(false);
			
			String query="delete from phanbokhuyenmai where ctkm_fk='"+ctkmId+"'";
			if(!db.update(query))
			{
				db.update("rollback");
				return "Lỗi cập nhật "+query;
			}
			query=
			"insert into PHANBOKHUYENMAI(CTKM_FK,NPP_FK,NGANSACH,DASUDUNG,TINHTRANG,SoXuatToiDa) "+
			" select '"+ctkmId+"' as ctkmId,a.npp_fk,99999999999,0,0,c.SoXuatToiDa "+
			" from ctkm_npp a inner join nhaphanphoi b on a.npp_fk = b.pk_seq " +
			"   inner join ctkhuyenmai c on c.pk_seq=a.ctkm_fk  "+
			" where a.ctkm_fk='"+ctkmId+"' and a.CHON=1  ";
			
			System.out.println("__PhanBo__"+query);
			
			if(!db.update(query))
			{
				db.update("rollback");
				return "Lỗi cập nhật "+query;
			}
			db.getConnection().commit();
			db.getConnection().setAutoCommit(true);
			if(db!=null)
				db.shutDown();
			return "";	
		} catch (Exception e)
		{
			
			try
			{
				db.getConnection().rollback();
			} catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
			db.shutDown();
			return "Không thể phân bổ CTKM này !"+e.getMessage();
		}
	}
	

	private void CreateHeader(WritableSheet ws, IDieukienkm dkkm,int index) throws WriteException
	{

		IDieukienDetail dkkmBean = dkkm.getDieukienDetail();
		//Co dinh 8 row dau tien
		ws.getSettings().setVerticalFreeze(8);
	    ws.getSettings().setDefaultRowHeight(17*20);
	    
	    ws.setColumnView(0, 20);
	    ws.setColumnView(1, 50);
	    ws.setColumnView(2, 13);
	    ws.setColumnView(3, 13);
	    ws.setColumnView(4, 13);
	    ws.setColumnView(5, 15);
	    ws.setColumnView(7, 18);
	    ws.setColumnView(8, 18);
	    ws.setColumnView(9, 18);
	    ws.setColumnView(10, 18);
	    ws.setColumnView(11, 18);
	    
	    WritableFont wf = new WritableFont(WritableFont.ARIAL, 15 , WritableFont.BOLD);
	    wf.setColour(Colour.RED);
	    WritableCellFormat wcf = new WritableCellFormat(wf);
	    wcf.setAlignment(Alignment.LEFT);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	   
	    Label title = new Label(0,8+index*10, "Điều kiện khuyến mãi", wcf);
	    ws.addCell(title);
	    
	    WritableFont font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    
	    WritableFont wf2 = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE , WritableFont.BOLD);
	    WritableCellFormat wcf2 = new WritableCellFormat(wf2);

	    Label l_madieukien = new Label(0, 9+index*10, "Mã điều kiện: ", wcf);
	    ws.addCell(l_madieukien);	
	    
	    Label d_madieukien = new Label(1, 9+index*10, dkkm.getId(), wcf2);
	    ws.addCell(d_madieukien);	
	    

	    Label l_diengiai = new Label(0, 10+index*10, "Diễn giải: ", wcf);
	    ws.addCell(l_diengiai);	
	    
	    Label d_diengiai = new Label(1,10+index*10, dkkmBean.getDiengiai(), wcf2);
	    ws.addCell(d_diengiai);	
	    
	    Label l_loaidieukien = new Label(0, 11+index*10, "Loại điều kiện: ", wcf);
	    ws.addCell(l_loaidieukien);
	    
	    
	    Label d_loaidieukien;
	    if(dkkmBean.getLoaidieukien().equals("1"))
	    	d_loaidieukien = new Label(1, 11+index*10, "Bắt buộc nhập số lượng từng sản phẩm" , wcf2);
	    else
	    	d_loaidieukien = new Label(1, 11+index*10, "Bất kỳ trong" , wcf2);
	    ws.addCell(d_loaidieukien);	

	    
	    Label l_hinhthuc = new Label(0, 12+index*10, "Hình thức: ", wcf);
	    ws.addCell(l_hinhthuc);		    
	    
	    Label d_hinhthuc;
	    Label d_tong;
	    if(dkkmBean.getHinhthuc().equals("1")){
	    	d_hinhthuc = new Label(1, 12+index*10, "Nhập tổng lượng" , wcf2);
	    	d_tong = new Label(2, 12+index*10, dkkmBean.getSotong() , wcf2);
		}else{
	    	d_hinhthuc = new Label(1, 12+index*10,"Nhập tổng tiền" , wcf2);
	    	d_tong = new Label(2, 12+index*10, dkkmBean.getSotong() , wcf2);
	    }
	    ws.addCell(d_hinhthuc);	
	    ws.addCell(d_tong);
	    
	    ws.addCell( new Label(0, 13+index*10, "Số lượng tính theo: ", wcf));
	    Label d_thung;
	    if(dkkm.isTheothung() == 1)
	    	d_thung = new Label(1, 13+index*10, "Thùng" , wcf2);
	    else
	    	d_thung = new Label(1, 13+index*10, "Lẻ" , wcf2);
	    ws.addCell(d_thung);	
	    
	    
	    font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    wcf.setAlignment(Alignment.CENTRE);
	    wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	    	    
	    Label masp = new Label(0, 15+index*10, "Mã sản phẩm", wcf);	    
	    ws.addCell(masp);	

	    Label tensp = new Label(1, 15+index*10, "Tên sản phẩm", wcf);	    
	    ws.addCell(tensp);	
	    
	    Label soluong = new Label(2, 15+index*10, "Số lượng", wcf);	   
	    ws.addCell(soluong);	 
	    
	}
	private int CreateContent(WritableSheet ws, List<ISanpham> sp_dkkmList,int index) throws WriteException
	{
		WritableFont wf = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD);
	    WritableCellFormat wcf_left = new WritableCellFormat(wf);
	    wcf_left.setAlignment(Alignment.LEFT);
	    wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat wcf_center = new WritableCellFormat(wf);
	    wcf_center.setAlignment(Alignment.CENTRE);
	    wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat wcf_right = new WritableCellFormat(wf);
	    wcf_right.setAlignment(Alignment.RIGHT);
	    wcf_right.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat wcf_num = new WritableCellFormat(NumberFormats.THOUSANDS_INTEGER);
	    wcf_num.setAlignment(Alignment.RIGHT);
	    wcf_num.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat cfi2 = new WritableCellFormat(NumberFormats.THOUSANDS_INTEGER);
	    cfi2.setAlignment(Alignment.CENTRE);
	    cfi2.setBorder(Border.ALL, BorderLineStyle.THIN);
			
		int m = 16;
		for(int i = 0; i < sp_dkkmList.size(); i++)
		{
			Sanpham sp = (Sanpham)sp_dkkmList.get(i);
			
			Label masp = new Label(0, m+10*index,sp.getMasanpham(), wcf_left);
			ws.addCell(masp);

			Label tensp = new Label(1, m+10*index, sp.getTensanpham(), wcf_left);
			ws.addCell(tensp);
			
			Label soluong = new Label(2, m+10*index, sp.getSoluong(), wcf_left);
			ws.addCell(soluong);
			m++;
		}
		return m+10*index;
	}
	
	private int  CreateHeaderTraKM(WritableSheet ws, ITrakm trakm,int rowIndex,int index) throws WriteException
	{
		ITrakmDetail dkkmBean = trakm.getTraDetail();
		//Co dinh 8 row dau tien
		ws.getSettings().setVerticalFreeze(8);
	    ws.getSettings().setDefaultRowHeight(17*20);
	    
	    ws.setColumnView(0, 20);
	    ws.setColumnView(1, 50);
	    ws.setColumnView(2, 13);
	    ws.setColumnView(3, 13);
	    ws.setColumnView(4, 13);
	    ws.setColumnView(5, 15);
	    ws.setColumnView(7, 18);
	    ws.setColumnView(8, 18);
	    ws.setColumnView(9, 18);
	    ws.setColumnView(10, 18);
	    ws.setColumnView(11, 18);
	    
	    WritableFont wf = new WritableFont(WritableFont.ARIAL, 15 , WritableFont.BOLD);
	    wf.setColour(Colour.RED);
	    WritableCellFormat wcf = new WritableCellFormat(wf);
	    wcf.setAlignment(Alignment.LEFT);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	   
	    Label title = new Label(0,rowIndex+index*10, "Trả khuyến mãi", wcf);
	    ws.addCell(title);
	    
	    WritableFont font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    
	    WritableFont wf2 = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE , WritableFont.BOLD);
	    WritableCellFormat wcf2 = new WritableCellFormat(wf2);

	    Label l_madieukien = new Label(0, rowIndex+1+index*10, "Mã điều kiện: ", wcf);
	    ws.addCell(l_madieukien);	
	    
	    Label d_madieukien = new Label(1, rowIndex+1+index*10, trakm.getId(), wcf2);
	    ws.addCell(d_madieukien);	
	    

	    Label l_diengiai = new Label(0, rowIndex+2+index*10, "Diễn giải: ", wcf);
	    ws.addCell(l_diengiai);	
	    
	    Label d_diengiai = new Label(1,rowIndex+2+index*10, dkkmBean.getDiengiai(), wcf2);
	    ws.addCell(d_diengiai);	
	    
	    Label l_loaidieukien = new Label(0, rowIndex+3+index*10, "Loại điều kiện: ", wcf);
	    ws.addCell(l_loaidieukien);
	    
	    
	    Label d_loaidieukien =null;
	    if(dkkmBean.getLoaitra().equals("1"))
	    	d_loaidieukien = new Label(1, rowIndex+3+index*10, "Trả tiền" , wcf2);
	    else if(dkkmBean.getLoaitra().equals("2"))
	    	d_loaidieukien = new Label(1, rowIndex+3+index*10, "Trả chiết khấu" , wcf2);
	    else if(dkkmBean.getLoaitra().equals("3"))
	    	d_loaidieukien = new Label(1, rowIndex+3+index*10, "Trả sản phẩm" , wcf2);
	    ws.addCell(d_loaidieukien);	

	    
	    Label l_hinhthuc = new Label(0, rowIndex+4+index*10, "Hình thức: ", wcf);
	    ws.addCell(l_hinhthuc);		    
	    
	    Label d_hinhthuc;
	    Label d_tong;
	    if(dkkmBean.getHinhthuc().equals("1"))
	    {
	    	d_hinhthuc = new Label(1, rowIndex+4+index*10, "Bắt buộc nhập số lượng" , wcf2);
	    	d_tong = new Label(2, rowIndex+4+index*10, dkkmBean.getSotong() , wcf2);
		}else
		{
	    	d_hinhthuc = new Label(1, rowIndex+4+index*10,"Bất kỳ trong" , wcf2);
	    	d_tong = new Label(2, rowIndex+4+index*10, dkkmBean.getSotong() , wcf2);
	    }
	    ws.addCell(d_hinhthuc);	
	    ws.addCell(d_tong);
	    
	    
	    
	    ws.addCell( new Label(0, rowIndex+5+index*10, "Tổng lượng / Tổng tiền" , wcf2));
	    ws.addCell(new Label(1, rowIndex+5+index*10, dkkmBean.getSotong()));
	    
	    ws.addCell( new Label(0, rowIndex+6+index*10, "Số lượng tính theo" , wcf2));
	    ws.addCell(new Label(1, rowIndex+6+index*10, "Lẻ" ));
	    
	    
	    font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    wcf.setAlignment(Alignment.CENTRE);
	    wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	    	    
	    Label masp = new Label(0, rowIndex+7+index*10, "Mã sản phẩm", wcf);	    
	    ws.addCell(masp);	

	    Label tensp = new Label(1, rowIndex+7+index*10, "Tên sản phẩm", wcf);	    
	    ws.addCell(tensp);	
	    
	    Label soluong = new Label(2, rowIndex+7+index*10, "Số lượng", wcf);	   
	    ws.addCell(soluong);	 
	    
	    return rowIndex+7;
	    
	}
	private int CreateContentTraKM(WritableSheet ws, List<ISanpham> sp_dkkmList,int rowIndex,int index) throws WriteException
{
	WritableFont wf = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD);
    WritableCellFormat wcf_left = new WritableCellFormat(wf);
    wcf_left.setAlignment(Alignment.LEFT);
    wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN);
    
    WritableCellFormat wcf_center = new WritableCellFormat(wf);
    wcf_center.setAlignment(Alignment.CENTRE);
    wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
    
    WritableCellFormat wcf_right = new WritableCellFormat(wf);
    wcf_right.setAlignment(Alignment.RIGHT);
    wcf_right.setBorder(Border.ALL, BorderLineStyle.THIN);
    
    WritableCellFormat wcf_num = new WritableCellFormat(NumberFormats.THOUSANDS_INTEGER);
    wcf_num.setAlignment(Alignment.RIGHT);
    wcf_num.setBorder(Border.ALL, BorderLineStyle.THIN);
    
    WritableCellFormat cfi2 = new WritableCellFormat(NumberFormats.THOUSANDS_INTEGER);
    cfi2.setAlignment(Alignment.CENTRE);
    cfi2.setBorder(Border.ALL, BorderLineStyle.THIN);
		
	int m = rowIndex;
		
	for(int i = 0; i < sp_dkkmList.size(); i++)
	{
		Sanpham sp = (Sanpham)sp_dkkmList.get(i);
		
		Label masp = new Label(0, m+10*index,sp.getMasanpham(), wcf_left);
		ws.addCell(masp);

		Label tensp = new Label(1, m+10*index, sp.getTensanpham(), wcf_left);
		ws.addCell(tensp);
		
		Label soluong = new Label(2, m+10*index, sp.getSoluong(), wcf_left);
		ws.addCell(soluong);
		m++;
	}
	return m+10*index;
	
}
	
	private void CreateHeaderNPP(WritableSheet ws,int rowIndex) throws WriteException
	{	
		//Co dinh 8 row dau tien
		ws.getSettings().setVerticalFreeze(8);
	    ws.getSettings().setDefaultRowHeight(17*20);
	    
	    ws.setColumnView(0, 20);
	    ws.setColumnView(1, 50);
	    ws.setColumnView(2, 13);
	    ws.setColumnView(3, 13);
	    ws.setColumnView(4, 13);
	    ws.setColumnView(5, 15);
	    ws.setColumnView(7, 18);
	    ws.setColumnView(8, 18);
	    ws.setColumnView(9, 18);
	    ws.setColumnView(10, 18);
	    ws.setColumnView(11, 18);
	    
	    WritableFont wf = new WritableFont(WritableFont.ARIAL, 15 , WritableFont.BOLD);
	    wf.setColour(Colour.RED);
	    WritableCellFormat wcf = new WritableCellFormat(wf);
	    wcf.setAlignment(Alignment.LEFT);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	   
	    Label title = new Label(0,rowIndex, "Danh sách NPP", wcf);
	    ws.addCell(title);
	    
	    WritableFont font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    
	    WritableFont wf2 = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE , WritableFont.BOLD);
	    WritableCellFormat wcf2 = new WritableCellFormat(wf2);
	    	    	    
	    font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.BOLD, false);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    wcf.setAlignment(Alignment.CENTRE);
	    wcf.setBorder(Border.ALL, BorderLineStyle.THIN);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	    	    
	    Label masp = new Label(0, rowIndex+1, "Mã nhà phân phối", wcf);	    
	    ws.addCell(masp);	

	    Label tensp = new Label(1,  rowIndex+1, "Nhà phân phối", wcf);	    
	    ws.addCell(tensp);	
	    
	}
	String getValue(Sheet sheet,int col,int row)
	{
		return sheet.getCell(col,row).getContents().trim().replaceAll(",", "");
	}
	String getValue(Sheet sheet,int col,int row,boolean replaceDauPhay)
	{
		try{
			if(replaceDauPhay)
				return sheet.getCell(col,row).getContents().trim().replaceAll(",", "").trim();
			else 
				return sheet.getCell(col,row).getContents().trim().trim();
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	boolean kiemtra_scheme(dbutils db,String khoId,String scheme )
	{
		
		String sql = "select count(*) as num  from ctkhuyenmai where scheme = (select dbo.ftBoDau2(N'" + scheme + "'))";
		ResultSet rs = db.get(sql);
		try 
		{
			rs.next();
			if(rs.getString("num").equals("0")){
				rs.close();
				return true;
			}

		} 
		catch (SQLException e) {}
		return false;

	}
	private boolean checkExitsDKKM(dbutils db,String dkkmid,String ctkmid) 
	{
		ResultSet rs = db.get("select count(*) as sodong from ctkm_dkkm where dkkhuyenmai_fk = '" + dkkmid + "' and CTKHUYENMAI_FK <> '"+ctkmid+"' ");
		int sodong = 0;
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					sodong = rs.getInt("sodong");
					rs.close();
				}
			} catch(Exception e) { sodong = 0; }
		}
		if(sodong > 0)
			return true;
		return false;
	}
	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);	
	}
	private void CreateContentNPP(WritableSheet ws, ResultSet npp,int index) throws WriteException
	{
		WritableFont wf = new WritableFont(WritableFont.ARIAL,WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD);
	    WritableCellFormat wcf_left = new WritableCellFormat(wf);
	    wcf_left.setAlignment(Alignment.LEFT);
	    wcf_left.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat wcf_center = new WritableCellFormat(wf);
	    wcf_center.setAlignment(Alignment.CENTRE);
	    wcf_center.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat wcf_right = new WritableCellFormat(wf);
	    wcf_right.setAlignment(Alignment.RIGHT);
	    wcf_right.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat wcf_num = new WritableCellFormat(NumberFormats.THOUSANDS_INTEGER);
	    wcf_num.setAlignment(Alignment.RIGHT);
	    wcf_num.setBorder(Border.ALL, BorderLineStyle.THIN);
	    
	    WritableCellFormat cfi2 = new WritableCellFormat(NumberFormats.THOUSANDS_INTEGER);
	    cfi2.setAlignment(Alignment.CENTRE);
	    cfi2.setBorder(Border.ALL, BorderLineStyle.THIN);
			
		int m = index;
		try
		{
			while(npp.next())
			{
				ws.addCell(new Label(0, m, npp.getString("ma")   , wcf_left));
				ws.addCell(new Label(1, m, npp.getString("ten")   , wcf_left));
				m++;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	private void CreateHeaderCTKM(WritableSheet ws, ICtkhuyenmai ctkmBean ) throws WriteException, SQLException
	{
		//Co dinh 8 row dau tien
		ws.getSettings().setVerticalFreeze(8);
	    ws.getSettings().setDefaultRowHeight(17*20);
	    
	    ws.setColumnView(0, 20);
	    ws.setColumnView(1, 50);
	    ws.setColumnView(2, 13);
	    ws.setColumnView(3, 13);
	    ws.setColumnView(4, 13);
	    ws.setColumnView(5, 15);
	    ws.setColumnView(7, 18);
	    ws.setColumnView(8, 18);
	    ws.setColumnView(9, 18);
	    ws.setColumnView(10, 18);
	    ws.setColumnView(11, 18);
	    
	    WritableFont wf = new WritableFont(WritableFont.ARIAL, 15 , WritableFont.BOLD);
	    wf.setColour(Colour.RED);
	    WritableCellFormat wcf = new WritableCellFormat(wf);
	    wcf.setAlignment(Alignment.LEFT);
	    wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
	   
	    Label title = new Label(0, 0, "Chương trình khuyến mãi", wcf);
	    ws.addCell(title);
	    
	    WritableFont font = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE, WritableFont.NO_BOLD, false,UnderlineStyle.NO_UNDERLINE);
	    wcf = new WritableCellFormat(font);
	    wcf.setWrap(true);
	    
	    WritableFont wf2 = new WritableFont(WritableFont.ARIAL, WritableFont.DEFAULT_POINT_SIZE , WritableFont.BOLD);
	    WritableCellFormat wcf2 = new WritableCellFormat(wf2);
	    	    
	    Label l_ngaytao = new Label(0, 1, "Scheme: ", wcf);
	    ws.addCell(l_ngaytao);	
	    
	    Label d_ngaytao = new Label(1, 1,ctkmBean.getScheme() , wcf2);
	    ws.addCell(d_ngaytao);	
	    
	    Label l_nguoitao = new Label(0, 2, "Diễn giải: ", wcf);
	    ws.addCell(l_nguoitao);	
	    
	    Label d_nguoitao = new Label(1, 2, ctkmBean.getDiengiai(), wcf2);
	    ws.addCell(d_nguoitao);		    	    		    

	    Label l_madieukien = new Label(0, 3, "Loại chương trình: ", wcf);
	    ws.addCell(l_madieukien);	
	    
	    String loai="";
	    if(ctkmBean.getType().equals("1"))
	    {
	    	loai="Bình thường";
	    }else if(ctkmBean.getType().equals("2"))
	    {
	    	loai="On Top";
	    }else  loai="Tích lũy";
	    
	    Label d_madieukien = new Label(1, 3, loai   , wcf2);
	    ws.addCell(d_madieukien);	

	    Label l_diengiai = new Label(0, 4, "Từ ngày: ", wcf);
	    ws.addCell(l_diengiai);	
	    
	    Label d_diengiai = new Label(1, 4, ctkmBean.getTungay()  , wcf2);
	    ws.addCell(d_diengiai);	
	    
	    Label l_loaidieukien = new Label(0, 5, "Đến ngày: ", wcf);
	    ws.addCell(l_loaidieukien);
	    	    
	    Label d_loaidieukien;d_loaidieukien = new Label(1, 5, ctkmBean.getDenngay() , wcf2);
	    ws.addCell(d_loaidieukien);	    	 
	    
	    ws.addCell(new Label(0, 6, "Khu vực áp dụng: " , wcf));
	    ws.addCell(new Label(1, 6, ctkmBean.getKhuvucId() , wcf2));
	
	}
	private boolean checkExits(dbutils db,String id) 
	{
		ResultSet rs = db.get("select count(*) as sodong from ctkm_trakm inner join DONHANG_CTKM_TRAKM on ctkm_trakm.CTKHUYENMAI_FK=DONHANG_CTKM_TRAKM.CTKMID WHERE TRAKHUYENMAI_FK = '" + id + "'");
		int sodong = 0;
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					sodong = rs.getInt("sodong");
					rs.close();
				}
				rs.close();
			} catch(Exception e) { sodong = 0; }
			
		}
		
		rs = db.get("select count(*) as sodong from ctkm_trakm  inner join ERP_DONDATHANG_CTKM_TRAKM on ctkm_trakm.CTKHUYENMAI_FK=ERP_DONDATHANG_CTKM_TRAKM.CTKMID WHERE TRAKHUYENMAI_FK = '" + id + "'");
		if(rs != null)
		{
			try 
			{
				if(rs.next())
				{
					sodong = rs.getInt("sodong");
					rs.close();
				}
				rs.close();
			} catch(Exception e) { sodong = 0; }
		}
		
		if(sodong > 0)
			return true;
		return false;
	}

	
}




