
package geso.dms.center.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.Idbutils;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.khachhang.imp.Khachhang;
import geso.dms.distributor.db.sql.dbutils;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.TimeZone;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.functors.ExceptionClosure;

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.Label;
import com.oreilly.servlet.MultipartRequest;

public class UploadTuyenBanHangSvl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public UploadTuyenBanHangSvl()
	{
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		IStockintransit obj = new Stockintransit();
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		obj.setuserId(userId);
		obj.init();

		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/UploadTuyenBanHang.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String contentType = request.getContentType();

		String userId = request.getParameter("userId");
		IStockintransit obj = new Stockintransit();
		obj.setuserId(userId);
		Utility util = new Utility();
		obj.setvungId(util.antiSQLInspection(request.getParameter("vungId")) == null ? "" : util.antiSQLInspection(request.getParameter("vungId")));
		obj.setkhuvucId(util.antiSQLInspection(request.getParameter("khuvucId")) == null ? "" : util.antiSQLInspection(request.getParameter("khuvucId")));

		//obj.setnppId(util.antiSQLInspection(request.getParameter("nppId")) == null ? "" : util.antiSQLInspection(request.getParameter("nppId")));

		if ((contentType != null) && (contentType.indexOf("multipart/form-data") >= 0))
		{
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=THONGTINUPLOAD.xls");
			WritableWorkbook w = Workbook.createWorkbook(response.getOutputStream());

			dbutils db = new dbutils();
			MultipartRequest multi = new MultipartRequest(request, "C:\\java-tomcat\\data\\", 20000000, "UTF-8");

			userId = util.antiSQLInspection(multi.getParameter("userId"));
			obj.setuserId(userId);
			obj.setvungId(util.antiSQLInspection(multi.getParameter("vungId")) == null ? "" : util.antiSQLInspection(multi.getParameter("vungId")));
			obj.setkhuvucId(util.antiSQLInspection(multi.getParameter("khuvucId")) == null ? "" : util.antiSQLInspection(multi.getParameter("khuvucId")));
			obj.setnppId(util.antiSQLInspection(multi.getParameter("nppId")) == null ? "" : util.antiSQLInspection(multi.getParameter("nppId")));

			Enumeration<?> files = multi.getFileNames();
			String filenameu = "";
			while (files.hasMoreElements())
			{
				String name = (String) files.nextElement();
				filenameu = multi.getFilesystemName(name);
				//System.out.println("name ___:   " + name);
			}

			String filename = "C:\\java-tomcat\\data\\" + filenameu;
			if (filename.length() > 0)
			{
				// doc file excel
				File file = new File(filename);
				//System.out.println("filename  " + file);
				Workbook workbook;
				WorkbookSettings ws = new WorkbookSettings();
				ws.setEncoding("ISO8859_1");

				int indexRow = 5;

				try
				{
					String sott = "";
					String makh = "";
					String macu ="";
					String tencuahieu = "";
					String chucuahieu = "";					
					String sohopdong = "";
					String quanhuyen = "";
					String tinhthanh = "";
					String sodienthoai = "";
					String masothue = "";
					String loaicuahang = "";
					String nhomcuahang = "";
					String ngaysinh = "";
					String tuyenbanhang = "";
					String tanso = "";
					String kbh_fk = "";
					String vtch_fk = "";
					String hch_fk = "";
					String lch_fk = "";
					String tbh_fk = "";
					String ThanhToanQuy="";
					String TenKyHd="";
					String MST_CaNhan="";
					String hkh_fk = "";
					String phuongxa ="";
					String nppKhac = "";
					String nguoimuahang = "";
					String apto = "";
					String didong = "";
					String mahd = "";
					String tdo_lat = "";
					String tdo_long = "";
					String vochong = "";
					String ngsinh_vochong = "";
					String con1 = "";
					String con2 = "";
					String ngsinh_con2 = "";
					String ngsinh_con1 = "";
					String ghichu = "";
					String taitro = "";
					String ngaytaitro = "";
					String vitrich = "";
					String thanhthinongthon_fk = "";
					String tenkh = "";

					ResultSet rs;
					//System.out.println(file);
					workbook = Workbook.getWorkbook( file, ws );
					Sheet[] sheet1 = workbook.getSheets();

					//System.out.println("[SoSheet]"+sheet1.length);

					Hashtable<String, String> htp_hch = gethtpHangcuahang(db);
					Hashtable<String, String> htp_lch = gethtpLoaicuahang(db);
					Hashtable<String, String> htp_nch = gethtpNhomcuahang(db);
					Hashtable<String, String> htp_vitri = gethtpVitri(db);			
					Hashtable<String, String> htp_vtch = gethtpVitricuahang(db);

					Hashtable<String, String> htp_tinhId = new  Hashtable<String, String>();
					Hashtable<String, String> htp_tinhTen = new  Hashtable<String, String>();	
					gethtpTinhThanh(db, htp_tinhId, htp_tinhTen);// dùng static để lấy dc cả 2 hashtable

					Hashtable<String, String> htp_quanId = new  Hashtable<String, String>();
					Hashtable<String, String> htp_quanTen = new  Hashtable<String, String>();
					Hashtable<String, String> htp_tinhFk = new  Hashtable<String, String>();
					gethtpQuanHuyen(db, htp_quanId, htp_quanTen, htp_tinhFk);// dùng static để lấy dc cả 2 hashtable

					Hashtable<String, String> htp_pxId = new  Hashtable<String, String>();
					Hashtable<String, String> htp_pxTen = new  Hashtable<String, String>();
					Hashtable<String, String> htp_qhFk = new  Hashtable<String, String>();
					gethtpPhuongXa(db, htp_pxId, htp_pxTen, htp_qhFk);					

					Hashtable<String, String> htp_thanhtoan = getThanhToan(db);
					Hashtable<String, String> htp_kbh = getKenhBanHang(db);
					Hashtable<String, String> htp_nkh = getNhomKhachHang(db);
					Hashtable<String, String> htp_ttnt = getThanhthiNongthon(db);
					Hashtable<String, String> htp_px = getPhuongXa(db);
					Hashtable<String, String> htp_TanSo = getTanSoHash( db);

					boolean error = false;
					// Kiểm tra các mã nhân viên này có đúng mã và phải của nhà
					// phân phối đã chọn ko?
					for (int t = 0; t < sheet1.length; t++)
					{
						Sheet sheet = sheet1[t];
						//System.out.println("____Name_______ :  " + sheet.getName());
						String nvbhid = sheet.getName().trim();
						String sql = "\n select ddkd.pk_seq,gsbh.kbh_fk  from daidienkinhdoanh ddkd inner join ddkd_gsbh b on ddkd.pk_seq=b.ddkd_fk "+ 
						"\n inner join giamsatbanhang gsbh on gsbh.pk_seq=b.gsbh_fk  " + 
						"\n where ddkd.pk_seq = " + nvbhid;
						//System.out.println("get du lieu : "+sql);
						rs = db.get(sql);
						if (rs == null || !rs.next())
						{
							obj.setMsg("Mã NHÂN VIÊN BÁN HÀNG : " + nvbhid+ " không xác định trong nhà phân phối bạn đang chọn,hoặc nhân viên này chưa được phân PTT / GĐ CN 2 ,vui lòng kiểm tra lại.");
							//error = true;
							//	break;
						} 
						if (rs!=null)rs.close();
					}

					if (!error)
					{
						boolean loiErrorRollback = false;
						for (int t = 0; t < sheet1.length; t++)
						{
							//1 NVBH 1 trans
							db.getConnection().setAutoCommit(false);
							
							int maxc = 0;
							String valueKh = "";
							String valueTuyen = "";
							String valueNppKhac = "";
							// TAO RA 1 SHEET LUU THONG TIN UPLOAD KHONG THANH
							// CONG
							WritableSheet sheetwrite = w.createSheet(sheet1[t].getName(), t);
							sheetwrite.addCell(new Label(0, 0, "THÔNG TIN CẬP NHẤT TUYẾN CỦA NHÂN VIÊN BÁN HÀNG CÓ MÃ: " + sheet1[t].getName()));
							Sheet sheet = sheet1[t];
							String ddkdid = sheet.getName().trim();
							Cell[] cells;
							indexRow = 5;

							Hashtable<String, String> htbtuyen = Htb_TuyenBH( userId, ddkdid, db);
							//	//System.out.println("htbtuyen size = " + htbtuyen.size());
							Hashtable<String, String> htp_nppList = getNhaphanphoiList(db,ddkdid);

							loiErrorRollback = false;
							String errror = "";
							boolean isPassed = false;
							String query = "";
							//Tạo bảng #Temp, không dùng Union All nữa
							createTempTable(db);
							for (int i = indexRow;i < sheet.getRows(); i++)
							{
								
								
								
								

								String maNppChinh ="";
								String inerror = "";
								cells = sheet.getRow(i);
								if (cells.length > 0)
								{
									if (cells[0].getContents().toString().length() > 0)
									{								
										int cot = 0;	
										sott = getValue(sheet,cot++,i,true);
										////System.out.println("sott"+sott);
										maNppChinh = getValue(sheet,cot++,i,true);
										maNppChinh = htp_nppList.get(maNppChinh);
										if (maNppChinh == null)
										{
											inerror = inerror + " Mã NPP trong hàng " + (indexRow + 1) + " không tồn tại hoặc không thuộc NVBH";
										}

										String pk_seqKH = getValue(sheet,cot++,i,false);
										if (pk_seqKH.equals("NA"))
										{
											pk_seqKH = "NA_Tmp" + getDateTime_MaTmp() +"_" +i ;
										}
										else if (pk_seqKH.trim().equals(""))
										{
											inerror = inerror + " Mã KH trong hàng " + (indexRow + 1) + " không được để trống!";
										}

										makh = getValue(sheet,cot++,i,false);
										if (makh.trim().length() <= 0 || makh.trim().equals("NA"))
											makh = pk_seqKH;										

										/*
										if (makh.equals("NA"))
											makh = "NA_Tmp" + getDateTime_MaTmp() +"_" +i ; 		

										macu = getValue(sheet,cot++,i,false);
										 */

										if (pk_seqKH.contains("NA_Tmp") && makh.trim().equals("NA")) // makh khác NA thì giữ nguyên
										{	makh = pk_seqKH; }

										tenkh = getValue(sheet,cot++,i,false).replace("'","");
										if (tenkh.trim().length() <= 0)
										{
											inerror = inerror + " Tên khách hàng trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}

										tencuahieu = getValue(sheet,cot++,i,false).replace("'","");;
										if (tencuahieu.trim().length() <= 0)
										{
											inerror = inerror + " Tên cửa hiệu trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}

										nguoimuahang = getValue(sheet,cot++,i,false).replace("'","");;
										if (nguoimuahang.trim().length() <= 0)
										{
											inerror = inerror + " Tên người mua hàng trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}

										String diachigiaohang = getValue(sheet,cot++,i,false).replace("'","");
										if (diachigiaohang.trim().length() <= 0)
										{
											inerror = inerror + " Tên địa chỉ giao hàng trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}

										//Thêm một cột địa chỉ xuất hoá đơn, ở dưới chạy hàm Update diachixhd luôn
										//String diachixhd = getValue(sheet,cot++,i,false).replace("'","");

										String chietkhau = getValue(sheet,cot++,i,false).replace("'","");;
										inerror += check_DauNhay(chietkhau, indexRow, "Chiet khau");

										loaicuahang = getValue(sheet,cot++,i,true);
										loaicuahang = htp_lch.get(loaicuahang.trim());

										nhomcuahang = getValue(sheet, cot++, i, true);
										nhomcuahang = htp_nch.get(nhomcuahang.trim());

										hch_fk = getValue(sheet,cot++,i,true);
										if (hch_fk!= null && hch_fk.trim().length() >0)
										{
											hch_fk = htp_hch.get(hch_fk.trim());
											if (hch_fk == null)
											{
												inerror = inerror + " Hạng cửa hàng trong hàng " + (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại DLN";
											}
										}	
										else
											hch_fk = null;

										vitrich = getValue(sheet, cot++, i, true);
										if (vitrich != null && vitrich.trim().length() > 0)
										{
											vitrich = htp_vitri.get(vitrich.trim().toUpperCase());

											if (vitrich == null)
											{
												//inerror = inerror + " Vị trí cửa hàng trong hàng " + (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại DLN";
											}
										}
										else
										{
											vitrich = null;
										}

										////System.out.println("vitri: "+vitrich);

										String sonha = getValue(sheet,cot++,i,false).replace("'","");
										if (sonha.trim().length() <= 0)
										{
											inerror = inerror + " Tên số nhà trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}

										String tenduong = getValue(sheet,cot++,i,false).replace("'","");
										if (tenduong.trim().length() <= 0)
										{
											//inerror = inerror + " Tên đường trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}
										
										apto = getValue(sheet,cot++,i,false).replace("'","");

										String pxId = getValue(sheet,cot++,i,true);
										phuongxa = htp_pxId.get(pxId);
										////System.out.println("px : "+ phuongxa);
										//if(phuongxa.equals("")) { phuongxa = null; }
										/*
										 * if (phuongxa == null) { inerror = inerror + " Mã Phường/xã trong hàng " +
										 * (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại DLN"; }
										 */
										String pxTen = htp_pxTen.get(pxId);
										String qhfk = htp_qhFk.get(pxId);

										String quanMa = getValue(sheet,cot++,i,true);
										quanhuyen = htp_quanId.get(quanMa);
										if (quanhuyen == null)
										{
											inerror = inerror + " Mã quận/huyện trong hàng " + (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại DLN";
										}
										String quanTen = htp_quanTen.get(quanMa);
										String tinh_fk = htp_tinhFk.get(quanMa);

										/*
										 * if (qhfk != null && !qhfk.equals(quanMa)) { inerror = inerror +
										 * " Mã phường/xã trong hàng " + (indexRow + 1) +
										 * " không thuộc về Quận/huyện đã Upload"; }
										 */

										String tinhMa = getValue(sheet,cot++,i,true);
										tinhthanh = htp_tinhId.get(tinhMa);
										if (tinhthanh == null)
										{
											inerror = inerror + " Mã tỉnh thành trong hàng " + (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại DLN";
										}

										String tinhTen = htp_tinhTen.get(tinhMa);

										if (tinh_fk != null && !tinh_fk.equals(tinhMa))
										{
											inerror = inerror + " Mã quận huyện trong hàng " + (indexRow + 1) + " không thuộc về tỉnh thành đã Upload";
										}

										thanhthinongthon_fk = getValue(sheet,cot++,i,true);
										inerror += check_DauNhay(thanhthinongthon_fk, indexRow, "Thành thị nông thôn");
										if (thanhthinongthon_fk!= null && thanhthinongthon_fk.trim().length() >0)
										{
											thanhthinongthon_fk = htp_ttnt.get(thanhthinongthon_fk.trim());
											if (thanhthinongthon_fk == null)
											{
												inerror = inerror + " Thành thị nông thôn trong hàng " + (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại";
											}
										}	
										else
											thanhthinongthon_fk = null;

										sodienthoai = getValue(sheet,cot++,i,false).replace("'","").trim();
										inerror += check_DauNhay(sodienthoai, indexRow, "Số điện thoại");
										if (sodienthoai == null || sodienthoai.trim().length() <= 0)
											sodienthoai = null;
										else {
											sodienthoai = "'"+sodienthoai+"'";
										}

										didong = getValue(sheet,cot++,i,false).replace("'","").trim();
										inerror += check_DauNhay(didong, indexRow, "Di động");
										if (didong == null || didong.trim().length() <= 0)
											didong = null;
										else {
											didong = "'"+didong+"'";
										}

										ngaysinh = getValue(sheet,cot,i,false);
										inerror += check_DauNhay(ngaysinh, indexRow, "Ngày sinh");

										Cell valueCell = sheet.getCell(cot, i);
										if (cells[cot++].getType() == CellType.DATE) 
										{  
											DateCell dCell = (DateCell) valueCell;  
											TimeZone gmtZone = TimeZone.getTimeZone("GMT");  
											DateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd");  
											destFormat.setTimeZone(gmtZone);  
											ngaysinh = destFormat.format(dCell.getDate());  
										}   

										sohopdong = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(sohopdong, indexRow, "So hop dong");

										masothue = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(masothue, indexRow, "Ma so thue");

										String songayno = ""; 
										songayno = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(songayno, indexRow, "So ngay no");

										String sotienno = ""; 
										sotienno = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(sotienno, indexRow, "So tien no");

										kbh_fk = getValue(sheet,cot++,i,false);// = OTC
										if (kbh_fk!= null && kbh_fk.trim().length() >0)
										{
											kbh_fk = htp_kbh.get(kbh_fk.trim());
											if (kbh_fk == null)
											{
												inerror = inerror + " Kênh bán hàng trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại DLN";
											}
										}	
										else
											inerror = inerror + " Kênh bán hàng trong hàng " + (indexRow + 1) + " không được để trống";

										//Kho
										String kho_fk = "";
										kho_fk = getValue(sheet,cot++,i,false);	

										tuyenbanhang =  getValue(sheet,cot++,i,false);
										if (tuyenbanhang.trim().length() <= 0)
										{
											inerror = inerror + " Tuyến bán hàng trong hàng " + (indexRow + 1) + " không hợp lệ, vui lòng kiểm tra lại";
										}

										tanso = getValue(sheet,cot++,i,false);
										tanso = htp_TanSo.get(tanso);
										if (tanso == null)
										{
											inerror = inerror + " Tần số trong hàng " + (indexRow + 1) + " không tồn tại, vui lòng kiểm tra lại";
										}

										tdo_long = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(tdo_long, indexRow, "Kinh do (LONG)");
										if (tdo_long.trim().equals(""))
											tdo_long = null;

										tdo_lat = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(tdo_lat, indexRow, "Vi do (LAT)");
										if (tdo_lat.trim().equals(""))
											tdo_lat = null;

										vochong = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(vochong, indexRow, "Vo chong");

										ngsinh_vochong = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(ngsinh_vochong, indexRow, "Ngay sinh vo chong");

										con1 = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(con1, indexRow, "Con 1");

										ngsinh_con1 = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(ngsinh_con1, indexRow, "Ngay sinh con 1");

										con2 = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(con2, indexRow, "Con 2");

										ngsinh_con2 = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(ngsinh_con2, indexRow, "Ngay sinh con 2");

										String con3 = "";
										/*con3 = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(con3, indexRow, "Con 3");*/

										String ngsinh_con3 = "";
										/*ngsinh_con3 = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(ngsinh_con3, indexRow, "Ngay sinh con 3");*/

										ghichu = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(ghichu, indexRow, "Ghi chu");

										taitro = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(taitro, indexRow, "Tai tro");

										ngaytaitro = getValue(sheet,cot++,i,true).replace("'","");
										inerror += check_DauNhay(ngaytaitro, indexRow, "Ngay tai tro");

										String nvdq = "";
										//nvdq = getValue(sheet,cot++,i,true).replace("'","");
										//inerror += check_DauNhay(nvdq, indexRow, "NVDQ");

										String sdtnvdq = "";
										//sdtnvdq = getValue(sheet,cot++,i,true).replace("'","");
										//inerror += check_DauNhay(sdtnvdq, indexRow, "SDT NVDQ");

										String email = "";
										//email = getValue(sheet,cot++,i,true).replace("'","");
										//inerror += check_DauNhay(email, indexRow, "Email");										

										//Không dùng NPP khác
												nppKhac =	getValue(sheet,cot++,i,false);
										String[] nppKhacArr ;

										if (nppKhac!= null && !nppKhac.trim().equals("") && nppKhac.trim().length() >0)
										{
											nppKhacArr = nppKhac.replace(" ","").trim().split(";");
											System.out.println("nppKhacArr.length = " + nppKhacArr.length);
											nppKhac ="";
											for(int nppIndex =0; nppIndex < nppKhacArr.length; nppIndex ++  )
											{

												String nppValue = nppKhacArr[nppIndex].trim();
												//System.out.println("nppValu = " + nppValue);
												String kq =htp_nppList.get(nppValue.trim());
												if (kq == null)
												{
													errror = errror + " Mã NPP ("+nppKhacArr[nppIndex]+") trong hàng " + (indexRow + 1) + " không tồn tại hoặc không  thuộc NVBH  ";
												}
												else
												{
													if (nppIndex < nppKhacArr.length - 1)
														nppKhac += kq +",";
													else 
														nppKhac +=kq;
												}
												nppKhacArr[nppIndex] = kq;
											}
										}	
										else
										{
											nppKhacArr = new String[0];
											nppKhac = "";
										}

										//Dòng nào có số hợp đồng tức là KH có ký hợp đồng;
										String khongkyhopdong = "1";
										if (sohopdong != null && sohopdong.length() > 0)
										{
											khongkyhopdong="0";
										}
										
										if (!inerror.equals(""))
										{
											System.out.println("inerror: "+inerror);
											loiErrorRollback = true;
											sheetwrite.addCell(new Label(0, indexRow, inerror));
										} 
										errror += inerror;
										
										System.out.println("inerror: "+errror);
										
										if(errror.length() <=0 && !loiErrorRollback)	
										{
											
											//String diachi = sonha + ", "+pxTen.replace("'","") +", "+quanTen +", "+tinhTen;
											String diachi = sonha + ", "+quanTen +", "+tinhTen;

											//Thêm mafast_temp dùng để insert tuyến, mafast_temp = 'NA_Tmp'+mafast
											valueKh += "\n select '"+pk_seqKH+"' pk_seq, dbo.ftBoDau(N'"+makh+"' + ' ' + isnull(N'"+ tencuahieu+ "', '') + ' ' + isnull(N'"+ diachi+ "', '')) as timkiem, " +
											"\n 1 as Import, 100000 as kho_fk, 1 as daduyet, "+loaicuahang+" as lch_fk, N'"+diachigiaohang+"' as diachigiaohang, " +
											"\n N'"+ tenkh+ "' as tenkh , N'"+tencuahieu+"'as tencuahieu, '1' as tt ,'"+ this.getDateTime()+ "'as ngaytao, " +
											"\n '"+ this.getDateTime()+ "' as ngaysua, "+ userId+ " as nguoitao, "+ userId+" as nguoisua, "+ 
											"\n "+ kbh_fk+ " as kbh, '"+ maNppChinh+ "' as nppId, "+sodienthoai+ " as dienthoai, "+didong+" as didong, " +
											"\n N'"+ diachi+ "' as diachi, "+ tinhthanh+ " as tinhthanh_fk, "+ quanhuyen+" as quanhuyen_fk, " + 
											"\n N'"+makh+"' AS SmartId, N'"+nguoimuahang+"' as nguoimuahang, '"+chietkhau+"'as chietkhau, "+vitrich+" as vitri, " +
											"\n N'"+tenduong+"' as tenduong, N'"+apto+"' as apto, '"+ngaysinh+"' as NgaySinh, '"+sohopdong+"' as MaHD, " +
											"\n '"+masothue+"' as masothue,0 as xuatkhau, N'"+makh+"' MaFast, " +
											"\n '"+khongkyhopdong+"' as KhongKyHopDong, '"+songayno+"' as songayno, '"+sotienno+"' as sotienno, " +
											"\n "+tdo_long+" as long, "+tdo_lat+" as lat, N'"+vochong+"' as vochong, '"+ngsinh_vochong+"' as ngsinh_vochong, " +
											"\n N'"+con1+"' as con1, N'"+con2+"' as con2, '"+ngsinh_con1+"' as ngsinh_con1, '"+ngsinh_con2+"' as ngsinh_con2, " +
											"\n N'"+con3+"' con3, '"+ngsinh_con3+"' ngsinh_con3,N'"+nvdq+"' nvdq, '"+sdtnvdq+"' sdtnvdq, '"+email+"' email, " +
											"\n N'"+ghichu+"' as ghichu, N'"+taitro+"' as taitro, '"+ngaytaitro+"' as ngaytaitro, " +
											"\n "+hch_fk+" as HCH_FK, "+phuongxa+" as [phuongxa_fk], N'"+macu+"' as [macu], " +
											"\n "+thanhthinongthon_fk+" as thanhthinongthon_fk, N'"+sonha+"' as sonha, N'NA_Tmp'+N'"+makh+"' [mafast_temp] " +
											"\n union all ";

											//valueTemp là từng dòng valueKH, valueKH đổi sao thì valueTemp phải đổi tương tự, 
											//Nếu thêm cột khi Import thì phải sửa trong hàm tạo bảng #tempKh
											String valueTemp = "\n select '"+pk_seqKH+"' pk_seq, dbo.ftBoDau(N'"+makh+"' + ' ' + isnull(N'"+ tencuahieu+ "', '') + ' ' + isnull(N'"+ diachi+ "', '')) as timkiem, " +
											"\n 1 as Import, 100000 as kho_fk, 1 as daduyet, "+loaicuahang+" as lch_fk, N'"+diachigiaohang+"' as diachigiaohang, " +
											"\n N'"+ tenkh+ "'as tenkh , N'"+tencuahieu+"'as tencuahieu, '1' as tt ,'"+ this.getDateTime()+ "'as ngaytao, " +
											"\n '"+ this.getDateTime()+ "' as ngaysua, "+ userId+ " as nguoitao, "+ userId+" as nguoisua, "+ 
											"\n "+ kbh_fk+ " as kbh, '"+ maNppChinh+ "' as nppId, "+sodienthoai+ " as dienthoai, "+didong+" as didong, " +
											"\n N'"+ diachi+ "' as diachi, "+ tinhthanh+ " as tinhthanh_fk, "+ quanhuyen+" as quanhuyen_fk, " + 
											"\n N'"+makh+"' AS SmartId, N'"+nguoimuahang+"' as nguoimuahang, '"+chietkhau+"'as chietkhau, "+vitrich+" as vitri, " +
											"\n N'"+tenduong+"' as tenduong, N'"+apto+"' as apto, '"+ngaysinh+"' as NgaySinh, '"+sohopdong+"' as MaHD, " +
											"\n '"+masothue+"' as masothue,0 as xuatkhau, N'"+makh+"' MaFast, " +
											"\n '"+khongkyhopdong+"' as KhongKyHopDong, '"+songayno+"' as songayno, '"+sotienno+"' as sotienno, " +
											"\n "+tdo_long+" as long, "+tdo_lat+" as lat, N'"+vochong+"' as vochong, '"+ngsinh_vochong+"' as ngsinh_vochong, " +
											"\n N'"+con1+"' as con1, N'"+con2+"' as con2, '"+ngsinh_con1+"' as ngsinh_con1, '"+ngsinh_con2+"' as ngsinh_con2, " +
											"\n N'"+con3+"' con3, '"+ngsinh_con3+"' ngsinh_con3,N'"+nvdq+"' nvdq, '"+sdtnvdq+"' sdtnvdq, '"+email+"' email, " +
											"\n N'"+ghichu+"' as ghichu, N'"+taitro+"' as taitro, '"+ngaytaitro+"' as ngaytaitro, " +
											"\n "+hch_fk+" as HCH_FK, "+phuongxa+" as [phuongxa_fk], N'"+macu+"' as [macu], " +
											"\n "+thanhthinongthon_fk+" as thanhthinongthon_fk, N'"+sonha+"' as sonha, N'NA_Tmp'+N'"+makh+"' [mafast_temp] ";											
											query = "insert #temp_kh select * from ("+valueTemp+") a";
											db.update(query);
											System.out.println(""+query);

											//valueNppKhac +="\n select npp.pk_seq as npp,  (select pk_Seq from khachhang where mafast ='"+makh+"' and npp_fk ="+maNppChinh+" )as [khId] from Nhaphanphoi npp where pk_Seq in ( "+maNppChinh+" ) \n union all ";
											if (nppKhac.trim().length() >0)
											{
												valueNppKhac +="\n select npp.pk_seq as npp, (select pk_Seq from khachhang where mafast ='"+makh+"'  )as [khId] from Nhaphanphoi npp where pk_Seq in ( "+nppKhac+" ) and not exists(select 1 from KHACHHANG_NPP where npp_fk=pk_seq and khachhang_fk =(select pk_Seq from khachhang where mafast ='"+makh+"'  )) \n union all ";
											}

											String[] tbhSplit = tuyenbanhang.split(";");
											for(int st=0;st<tbhSplit.length;st++)
											{
												if (tbhSplit[st].length()>0)
												{
													tbh_fk = htbtuyen.get(maNppChinh.trim() + "_" + ddkdid.trim() + "_" + tbhSplit[st]);

													if (tbh_fk == null)
													{

														inerror = "1. Không xác định được tuyến của khách hàng trong hàng " + (indexRow + 1);
														sheetwrite.addCell(new Label(1, indexRow, "Tạo tuyến không thành công "));
													}
													else
													{
														//Thêm 'NA_Tmp' nối chuỗi với mafast để = mafast_temp
														inerror = "";
														valueTuyen += "\n select pk_seq as khId," + tbh_fk + " tbh_fk,'" + tanso + "' as tanso,'" + sott+"' as stt " +
														"\n from khachhang where Mafast_temp = N'NA_Tmp'+N'"+makh.trim()+"' " +
														"\n union all";

														query = "\n insert #temp_tuyen select a.mafast mafast," + tbh_fk + " tbh_fk,'" + tanso + "' as tanso,'" + sott+"' as stt " +
														"\n from ("+valueTemp+") a";
														db.update(query);
													}

													/*if (nppKhac.trim().length() >0)
													{
														for(int nppIndex =0; nppIndex < nppKhacArr.length; nppIndex ++  )
														{
															String tbh_fk2 = htbtuyen.get(nppKhacArr[nppIndex].trim() + "_" + ddkdid.trim() + "_" + tbhSplit[st]);
															if (tbh_fk2 == null)
															{
																errror +=  "2.Không xác định được tuyến(NPP:"+nppKhacArr[nppIndex]+") của khách hàng trong hàng  " + (indexRow + 1);
																sheetwrite.addCell(new Label(1, indexRow, " 2. Tạo tuyến không thành công  " ));
																errror ="";
															}
															else
															{
																valueTuyen += "\n select pk_seq  as khId," + tbh_fk2 + " tbh_fk,  '" + tanso + "','" + sott
																+ "' from khachhang where   Mafast= '" + makh.trim() + "'  \n union all";	
																errror ="";
															}
														}
													}*/
												}
											}										
										}
									}
								}
								
								inerror = "";
								indexRow++;
								
							}
							
							int sodongUpdate = 0;
							int sodongInsert = 0;
							int sodongthucte = indexRow - 5;
							////System.out.println("nvbh = " + ddkdid);
							
							//System.out.println("DATOIDAY");
							
							if (loiErrorRollback)
							{
								
								if (!db.getConnection().getAutoCommit())
								{
									////System.out.println("Errrr" + ddkdid);
									sheetwrite.addCell(new Label(1, 2 , "Thông báo: Lỗi Error "+errror));
									db.getConnection().rollback();
									db.getConnection().setAutoCommit(true);
								}

							}
							else if (valueKh.trim().length() >0)
							{
								System.out.println("DATOIDAY 1");
								String diaban_fk = "(select diaban_fk from daidienkinhdoanh where pk_Seq = "+ddkdid+" )";
								boolean pass = true;
								String sql = "";
								ResultSet checkSql = null;
								String khLoi = "";
								if (pass)
								{
									sql = "\n select tmp.mafast from #temp_kh tmp " +
									"\n inner join khachhang kh on kh.mafast = tmp.mafast " +
									"\n group by tmp.mafast having count(kh.pk_seq) > 1 ";
									////System.out.println("ValueKH: "+valueKh);
									////System.out.println("Check mafast: "+sql);
									try {
										checkSql = db.get(sql);
										if (checkSql != null) {
											while(checkSql.next())
											{
												khLoi = ", "+khLoi+checkSql.getString("MaFast");
												khLoi = khLoi.substring(2);
												pass = false;
											}
										}
										checkSql.close();
									}
									catch (Exception e) {
										sheetwrite.addCell(new Label(1, indexRow +1 , "Dữ liệu Import không hợp lệ, vui lòng xoá dấu '"));
										e.printStackTrace();
									}
									if (khLoi.trim().length() > 0)
									{
										pass = false;
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
										sheetwrite.addCell(new Label(1, indexRow +1 , "Có mã KH đã bị trùng:" + khLoi));
									}
								}

								System.out.println("DATOIDAY 3 "+pass);
								khLoi = "";
								if (pass)
								{									
									// ko cap nhat //Thêm mafast_temp để insert tuyến, do mafast của CVI cập nhật động tăng dần
									sql = "\n update khachhang set  synced = case when (select loainpp from nhaphanphoi where pk_seq = tmp.nppid) = 0 then 0 else 1 end, " +
									"\n lch_fk = tmp.lch_fk,diaban_fk = (select diaban_fk from daidienkinhdoanh where pk_Seq = "+ddkdid+"), " +
									"\n TimKiem = tmp.timkiem, diachigiaohang = tmp.diachigiaohang, NGUOISUA = tmp.NGUOISUA, NGAYSUA = tmp.NGAYSUA, " + 
									"\n kbh_fk = tmp.kbh, tructhuoc_fk = tmp.nppId, npp_fk = tmp.nppId,DIENTHOAI = tmp.DIENTHOAI, " +
									"\n tinhthanh_fk = tmp.tinhthanh_fk, quanhuyen_fk = tmp.quanhuyen_fk, " + 
									"\n TEN = tmp.tenkh, tencuahieu = tmp.tencuahieu, DIACHI = tmp.DIACHI, vitricuahang = tmp.vitri, " +
									"\n NgaySinh = tmp.NgaySinh,MaHD = tmp.MaHD,masothue= tmp.masothue ,KhongKyHopDong = tmp.KhongKyHopDong, " + 
									"\n HCH_FK = tmp.HCH_FK, phuongxa = tmp.phuongxa_fk, sonha = tmp.sonha, songayno = tmp.songayno, sotienno = tmp.sotienno, " +
									"\n ghichu = tmp.ghichu, taitro = tmp.taitro, ngaytaitro = tmp.ngaytaitro, vochong = tmp.vochong, Ngsinh_VoChong = tmp.ngsinh_vochong, " + 
									"\n con1 = tmp.con1, con2 = tmp.con2, Ngsinh_Con1 = tmp.ngsinh_con1, Ngsinh_Con2 = tmp.ngsinh_con2, con3 = tmp.con3," +
									"\n ngsinh_con3 = tmp.ngsinh_con3, nvdq = tmp.nvdq, sdtnvdq = tmp.sdtnvdq, email = tmp.email, didong = tmp.didong," +
									"\n nguoimuahang = tmp.nguoimuahang, apto = tmp.apto, pt_chietkhau = tmp.chietkhau, " +
									"\n thanhthinongthon_fk = tmp.thanhthinongthon_fk, vtch_fk = tmp.vitri, tenduong = tmp.tenduong " + 
									"\n from khachhang kh inner join #temp_kh tmp on cast( kh.pk_seq as varchar) = tmp.pk_seq "; // and (diaban_fk is  null or diaban_fk = " + diaban_fk + " )
									sodongUpdate = db.updateReturnInt(sql);
									if (sodongUpdate < 0)
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi cập nhật KH: "+sql ));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}

									/*
									String tacvu = "After Update MCP";
									String logmsg = InsertLog(tacvu, valueKh, userId, db);
									if (logmsg != null && logmsg.length() > 0) {
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi ghi Log KH 2!"));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
									*/
								}
								System.out.println("DATOIDAY 4 "+pass);
								if (pass)
								{
									/*query = "exec Identity_reseed";	
									if (!db.update(query))
									{
										sheetwrite.addCell(new Label(1, indexRow +1 , "Không thể Reseed mã khách hàng, vui lòng liên hệ Admin để được trợ giúp!"));
										db.getConnection().rollback();
										pass = false;
									}*/

									sql = "\n insert into khachhang (synced,ismcp,insert_log, TimKiem,Import,kho_fk,daduyet," +
									"\n lch_fk,diachigiaohang,ten,tencuahieu,trangthai,ngaytao,ngaysua,nguoitao,nguoisua," +
									"\n kbh_fk,npp_fk,dienthoai,didong,diachi,tinhthanh_fk, quanhuyen_fk,smartid,nguoimuahang," +
									"\n pt_chietkhau,vtch_fk,tenduong,apto,NgaySinh,MaHD,masothue,XuatKhau,MaFast,KhongKyHopDong," +
									"\n songayno,sotienno,long,lat,vochong,ngsinh_vochong,con1,con2,ngsinh_con1," +
									"\n ngsinh_con2,con3,ngsinh_con3,nvdq,sdtnvdq,email,ghichu,taitro,ngaytaitro, " +
									"\n HCH_FK,phuongxa,macu,thanhthinongthon_fk,sonha,MaFast_Temp,diaban_fk) " +
									"\n select case when (select loainpp from nhaphanphoi where pk_seq = tmp.nppid) = 0 then 0 else 1 end synced, " +
									"\n case when (select loainpp from nhaphanphoi where pk_seq = tmp.nppid) = 0 then 1 else 0 end ismcp,*," + diaban_fk+ "" +
									"\n from #temp_kh tmp where CHARINDEX('NA_Tmp',tmp.pk_seq) >= 1  "; // CHARINDEX('NA',tmp.mafast) >= 1 
									////System.out.println("Insert SQL: "+sql);
									
										sodongInsert = db.updateReturnInt(sql);										
										////System.out.println("sodong ="+ sodongthucte+",sodongUpdate =" +sodongUpdate+",sodongInsert =" +sodongInsert);
										if (sodongInsert < 0)
										{
											pass = false;
											sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi tạo mới KH: "));
											db.getConnection().rollback();
											db.getConnection().setAutoCommit(true);
										}	
									
																			
								}
								System.out.println("DATOIDAY 5 "+pass);
								//Update địa chỉ XHĐ
								if (pass) {
									query = "\n update khachhang set Diachixhd = sonha+', '+tenduong+', '+(select ten from phuongxa where pk_seq = khachhang.phuongxa)+', '+ "+
									"\n (select ten from quanhuyen where pk_seq = khachhang.quanhuyen_fk)+', '+(select ten from tinhthanh where pk_seq = khachhang.tinhthanh_fk) ";
									db.update(query);
								}
								System.out.println("DATOIDAY 6 "+pass);
								//Insert NVGN
								if (pass)
								{										
									query = "\n insert nhanviengiaonhan (ten,trangthai,diachi,npp_fk,ngaytao,ngaysua,nguoitao,nguoisua,dienthoai)" +
									"\n select 'NVGN',1,'na',temp.nppid,'"+this.getDateTime()+"','"+this.getDateTime()+"',100002,100002,'na' " +
									"\n from (select distinct nppid from #temp_kh tmp " +
									"\n where not exists (select 1 from nhanviengiaonhan where npp_fk = tmp.nppid))temp";
									////System.out.println("Query Insert NVGN: "+query);	
									db.update(query);

									query = "\n insert NVGN_KH (KHACHHANG_FK,NVGN_FK) "+
									"\n select pk_Seq,(select top 1 PK_SEQ from nhanviengiaonhan where npp_Fk = kh.NPP_FK ) "+
									"\n from KHACHHANG kh "+
									"\n where not exists (select 1 from NVGN_KH where KHACHHANG_FK = kh.PK_SEQ )" +
									"\n and (select top 1 PK_SEQ from nhanviengiaonhan where npp_Fk = kh.NPP_FK ) is not null";
									////System.out.println("Query Insert NVGN_KH: "+query);
									db.update(query);
								}
								System.out.println("DATOIDAY 7 "+pass);
								if (pass)
								{
									sql = "\n select tmp.MaFast from #temp_kh tmp left join khachhang kh on tmp.mafast = kh.mafast " +
									"\n where kh.mafast is null  ";
									////System.out.println("Check khLoi: "+sql);
									checkSql = db.get(sql);
									while(checkSql.next())
									{
										pass = false;
										khLoi += ","+checkSql.getString("MaFast");
									}
									checkSql.close();

									if (khLoi.trim().length() > 0)
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "Các mã KH không Insert được: " + khLoi.substring(1)));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
								}
								System.out.println("DATOIDAY 8 "+pass);
								if (pass )
								{
									
									if(valueNppKhac.length()>0)
									{
										int vitri = valueNppKhac.lastIndexOf("union all") ; 
										  valueNppKhac =valueNppKhac.substring(0,vitri);
										 
									}
									  
									sql = "\n delete KHACHHANG_NPP " +
									"\n where khachhang_fk in ( select pk_seq from khachhang where mafast in (select mafast from #temp_kh tmp)) ";
									////System.out.println("Delete NPP_FK: "+sql);
									if (!db.update(sql))
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "LoiXoaNppCu1:"));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
								}

								System.out.println("DATOIDAY 9 "+pass);
								if (pass)
								{
									if(valueNppKhac.length()>0)
									{
										sql ="insert KHACHHANG_NPP (NPP_FK,KhachHang_fk) " + valueNppKhac; 
										 if (!db.update(sql))
											{
												pass = false;
												sheetwrite.addCell(new Label(1, indexRow +1 , "LoiXoaNppCu3:"));
												db.getConnection().rollback();
												db.getConnection().setAutoCommit(true);
											}
									}
									 
									 
									 
									sql = "\n insert KHACHHANG_NPP (NPP_FK,KhachHang_fk) " +
									"\n select npp_fk, pk_seq from khachhang where mafast in (select mafast from #temp_kh tmp) ";
									System.out.println("sql insert = " +sql);
									if (!db.update(sql))
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "LoiXoaNppCu2:"));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
								}					

								System.out.println("DATOIDAY 10 "+pass);
								//Nếu tồn tại maFAST bị trùng thì không cho Up
								if (pass) {
									String text = "";
									sql = "\n select mafast from khachhang " +
									"\n where pk_seq in (select pk_seq from khachhang group by pk_seq having count(mafast) > 1)";
									ResultSet temprs = db.get(sql);
									while (temprs.next()) {
										text += ", "+temprs.getString("mafast");
									}
									temprs.close();

									if (text != null && text.length() > 0) {
										pass = false;
										String msg = "Các mã khách hàng: "+text.substring(2) +" đang bị trùng, " +
										"vui lòng điều chỉnh lại DLN sau đó tiếp tục Upload MCP!";
										sheetwrite.addCell(new Label(1, indexRow +1, msg));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
								}

								System.out.println("DATOIDAY 11 "+pass);
								if (pass)
								{
									sql = "\n delete a from " +
									"\n khachhang_tuyenbh a inner join khachhang kh on kh.pk_seq = a.khachhang_fk " +
									"\n inner join #temp_tuyen b on b.mafast = kh.mafast " ; // and b.tbh_fk = a.tbh_fk  xoa het tuyen cu luon
									////System.out.println("sql xoa tuyen: "+sql);
									if (!db.update(sql))
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi thêm Tuyến KH1"));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
								}

								System.out.println("DATOIDAY 12 "+pass);
								if (pass)
								{
									sql = "\n insert into khachhang_tuyenbh (khachhang_fk,tbh_fk,tanso,sott) " +
									"\n select kh.pk_seq,a.tbh_fk,a.tanso,a.sott " +
									"\n from #temp_tuyen a inner join khachhang kh on a.mafast = kh.mafast ";
									//////System.out.println("insert into khachhang_tuyenbh=" + sql);
									if (!db.update(sql))
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi thêm Tuyến KH2"));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
									}
								}

								System.out.println("DATOIDAY 13 "+pass);
								if (pass)
								{
									sql = "\n select kh.kbh_fk, kh.Pk_seq,kh.lch_fk,kh.ten,kh.quanhuyen_fk,kh.tinhthanh_fk, tt.ma + qh.ma prefix, " +
											"\n (select vung_fk from tinhthanh where pk_seq = kh.tinhthanh_fk) vung_fk  " +
											"\n from khachhang kh " +
											"\n inner join tinhthanh tt on tt.pk_Seq = kh.tinhthanh_fk " +
											"\n inner join quanhuyen qh on qh.pk_seq = kh.quanhuyen_fk and qh.tinhthanh_fk = tt.pk_Seq  " +
											"\n where  mafast like N'NA_Tmp%' ";
											ResultSet rsKh = db.get(sql);
											while (rsKh.next())
											{
												String pk_seq = rsKh.getString("pk_seq");
												String ten = rsKh.getString("ten");
												String qh = rsKh.getString("quanhuyen_fk");
												String tt = rsKh.getString("tinhthanh_fk");
												

												String prefixMa = Khachhang.Prefix_MaKhachHang(db,qh,tt);
												if(prefixMa.trim().length() <=0)
												{
													pass = false;
													sheetwrite.addCell(new Label(1, indexRow +1 , "Không thể lấy mã của KH ("+ten+") theo Tỉnh thành/Quận huyện"));
													db.getConnection().rollback();
													db.getConnection().setAutoCommit(true);
													break;
													
												}
												int stt = Khachhang.MaKhachHang(db,qh,tt);
												if(stt <=0 )
												{
													pass = false;
													sheetwrite.addCell(new Label(1, indexRow +1 , "Không thể lấy số thứ tự của KH ("+ten+") theo Tỉnh thành/Quận huyện"));
													db.getConnection().rollback();
													db.getConnection().setAutoCommit(true);
													break;
													

												}

												

												sql = "\n update khachhang  set " +
												"\n mafast = N'" + prefixMa + "' + dbo.PlusZero("+stt+",4), " +
												"\n smartId = N'" + prefixMa + "' + dbo.PlusZero("+stt+",4) " +
												"\n where pk_seq =" + pk_seq;										
												if (!db.update(sql))
												{
													pass = false;
													sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi: Cập nhật mã khách hàng " + sql ));
													db.getConnection().rollback();
													db.getConnection().setAutoCommit(true);
													break;
												}
																					
												String kq = Khachhang.Log_MaKhachHang(db, pk_seq, stt);
												if (kq.trim().length() > 0)
												{
													pass = false;
													sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi: Ghi Log mã khách hàng "+kq ));
													db.getConnection().rollback();
													db.getConnection().setAutoCommit(true);
													break;												
												}
												
												sql =" update khachhang set timkiem =  dbo.ftBoDau(maFAST + '-' + isnull(ten, '') + '-' + diachi) where pk_seq =" + pk_seq;	
												if (!db.update(sql))
												{
													pass = false;
													sheetwrite.addCell(new Label(1, indexRow +1 , "Lỗi: Cập nhật  tìm kiếm " + sql ));
													db.getConnection().rollback();
													db.getConnection().setAutoCommit(true);
													break;
												}
											}
											rsKh.close();
								}	

								if (pass)
								{
									sql = "\n select 1  " +
									"\n from khachhang kh " +
									"\n where mafast like N'NA_Tmp%' ";
									ResultSet rsKh = db.get(sql);
									if (rsKh.next())
									{
										pass = false;
										sheetwrite.addCell(new Label(1, indexRow +1 , "Tồn tại khách chưa dc tự tạo mã!"));
										db.getConnection().rollback();
										db.getConnection().setAutoCommit(true);
										break;
									}
									rsKh.close();
								}

								if (pass)
								{
									////System.out.println("Passed: "+ddkdid);
									sheetwrite.addCell(new Label(1, 2 , "Thông báo: Upload Thành công!"));
									db.getConnection().commit();
									db.getConnection().setAutoCommit(true);
								}
							}
							else 
							{
								System.out.println("DATOIDAY 2");
								////System.out.println("Xóa hết tuyến:" + ddkdid);
								sheetwrite.addCell(new Label(1, 2 , "Thông báo: Đã xóa hết tuyến của NVBH! "+errror));
								db.getConnection().commit();
								db.getConnection().setAutoCommit(true);
							}
						}
						obj.setMsg("Đã thực hiện xong, vui lòng xem File thông báo");
					}
					db.shutDown();

					try
					{
						w.write();
						w.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				} 
				catch (Exception er)
				{
					
					try{
						er.printStackTrace();	
						WritableSheet sheetwrite1 = w.createSheet("Exception", 1);
						sheetwrite1.addCell(new Label(0, 0, er.getMessage()));
						w.write();
						w.close();
					er.getMessage();
						if (!db.getConnection().getAutoCommit())
						{
							db.getConnection().rollback();
							db.getConnection().setAutoCommit(true);
						}
					}
					catch(Exception exx){exx.printStackTrace();return;}
					
					if (er != null && er.equals("Unable to recognize OLE stream"))
					{
						try
						{
							WritableSheet sheetwrite = w.createSheet("Exception", 1);
							sheetwrite.addCell(new Label(0, 0, "Vui lòng  Up file Excel 2003"));
							w.write();
							w.close();
						}catch(Exception e)
						{
							e.printStackTrace();
						}
					}
					else {
						obj.setMsg("Exception 333." + er.getMessage());
						////System.out.println("Exception 333." + er.getMessage());
						
						er.printStackTrace();
					}
				}				
			}
			return;
		}
		
		obj.init();
		session.setAttribute("obj", obj);
		session.setAttribute("userId", userId);
		String nextJSP = request.getContextPath() + "/pages/Center/UploadTuyenBanHang.jsp";
		response.sendRedirect(nextJSP);
	}
		
	private static void gethtpPhuongXa(dbutils db, Hashtable<String, String> htp_pxId, 
			Hashtable<String, String> htp_pxTen, Hashtable<String, String> htp_qhFk)
	{
		htp_pxId.clear();
		htp_pxTen.clear();
		htp_qhFk.clear();
		
		String query = "select a.pk_seq, a.ten, b.PK_SEQ qhfk from phuongxa a inner join quanhuyen b on a.quanhuyen_fk = b.pk_seq";
		ResultSet rs = db.get(query);
		try
		{
			while (rs.next())
			{
				htp_pxId.put(rs.getString("pk_seq").trim(), rs.getString("pk_seq").trim());
				htp_pxTen.put(rs.getString("pk_seq").trim(), rs.getString("ten").trim());
				htp_qhFk.put(rs.getString("pk_seq").trim(), rs.getString("qhfk").trim());

			}
			rs.close();
		}
		catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Lỗi lấy phường xã: " + er.toString());
		}
	}
	
	private static void gethtpQuanHuyen(dbutils db, Hashtable<String, String> htp_quanId
			, Hashtable<String, String> htp_quanTen, Hashtable<String, String> htp_tinhFk )
	{

		htp_quanId.clear();
		htp_quanTen.clear();
		String sql = "\n select qh.pk_seq,tt.pk_seq tinhfk,qh.ten " +
		"\n from quanhuyen qh inner join tinhthanh tt on qh.tinhthanh_fk = tt.pk_seq  ";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htp_quanId.put(rs.getString("pk_seq").trim(), rs.getString("pk_seq").trim());
				htp_quanTen.put(rs.getString("pk_seq").trim(), rs.getString("ten").trim());
				htp_tinhFk.put(rs.getString("pk_seq").trim(), rs.getString("tinhfk").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Loi lay tinhthanh : " + er.toString());
		}
	}

	private static void  gethtpTinhThanh(dbutils db, Hashtable<String, String> htp_tinhId
											, Hashtable<String, String> htp_tinhTen )
	{

		htp_tinhId.clear();
		htp_tinhTen.clear();
		
		String sql = "select pk_seq,ma,ten from tinhthanh";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htp_tinhId.put(rs.getString("pk_seq"), rs.getString("pk_seq").trim());
				htp_tinhTen.put(rs.getString("pk_seq"), rs.getString("ten").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Loi lay tinhthanh : " + er.toString());
		}

	}

	private Hashtable<String, String> gethtpVitricuahang(dbutils db)
	{

		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = "select vitri,pk_seq from vitricuahang";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("vitri").trim(), rs.getString("pk_seq").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Loi lay vtch : " + er.toString());
		}
		return htbtuyen;
	}


	private Hashtable<String, String> getPhanLoai(dbutils db)
	{

		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			" SELECT data.pLoai,data.ploaiId  "+ 
			" FROM "+
			" (   "+
			"	select 'BL' as pLoai,0 as ploaiId union all  "+
			"	select 'BB' as pLoai,1 as ploaiId union all   "+
			"	select 'BB-BL' as pLoai,2 as ploaiId    "+
			/*"	select 'BL-BB' as pLoai,3 as ploaiId   "+*/
			") as data";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("pLoai").trim(), rs.getString("ploaiId").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}

	private Hashtable<String, String> getThanhToan(dbutils db)
	{

		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			" SELECT data.pLoai,data.ploaiId  "+ 
			" FROM "+
			" (   "+
			"	select 'TM' as pLoai,0 as ploaiId union all  "+
			"	select 'HD' as pLoai,1 as ploaiId   "+
			") as data";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("pLoai").trim(), rs.getString("ploaiId").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}


	private Hashtable<String, String> getKenhBanHang(dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			"		select PK_SEQ,TEN,diengiai from kenhbanhang ";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("TEN").trim(), rs.getString("PK_SEQ").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}


	private Hashtable<String, String> gethtpLoaicuahang(dbutils db)
	{

		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = "select loai,pk_seq from loaicuahang";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("loai").trim(), rs.getString("pk_seq").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Lỗi lấy loại cửa hàng: " + er.toString());
		}
		return htbtuyen;
	}
	
	private Hashtable<String, String> gethtpVitri(dbutils db)
	{

		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = "select upper(diengiai)diengiai,pk_seq from vitricuahang";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("diengiai").trim(), rs.getString("pk_seq").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Lỗi lấy vị trí cửa hàng: " + er.toString());
		}
		return htbtuyen;
	}
	
	private Hashtable<String, String> gethtpNhomcuahang(dbutils db)
	{

		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = "select diengiai,pk_seq from loaicuahang";

		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("diengiai").trim(), rs.getString("pk_seq").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			////System.out.println("Lỗi lấy nhóm cửa hàng: " + er.toString());
		}
		return htbtuyen;
	}

	private Hashtable<String, String> gethtpHangcuahang(dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = "select hang,pk_seq from hangcuahang";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("hang").trim(), rs.getString("pk_seq").trim());

			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
			//System.out.println("Lỗi lấy hạng cửa hàng: " + er.toString());
		}
		return htbtuyen;
	}

	private String getDateTime()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		return dateFormat.format(date);
	}

	private Hashtable<String, String> Htb_TuyenBH(String userid, String nvbhid, dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		try
		{
			String sql ="select npp_fk from daidienkinhdoanh_npp where  ddkd_fk = "+nvbhid;

			ResultSet rsT = db.get(sql);
			while(rsT.next())
			{
				String nppId = rsT.getString("npp_fk");
				////System.out.println("NPP isert =" + nppId);
				sql = 
					" insert into tuyenbanhang  (diengiai,ngaylamviec,ngaytao,ngaysua,nguoitao,nguoisua,ddkd_fk,npp_fk,ngayid) " +

					" select N'Thứ Hai',N'Thứ hai','" + this.getDateTime() + "','"+ this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",2 from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=2 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") and pk_seq= " + nvbhid + 

					" union  "+ 

					" select N'Thứ Ba',N'Thứ ba','" + this.getDateTime() + "','" + this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",3   " + " from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=3 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") " + " and pk_seq=" + nvbhid + 

					" union  "+ 

					" select N'Thứ Tư',N'Thứ tư','" + this.getDateTime() + "','" + this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",4   " + " from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=4 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") " + " and pk_seq=" + nvbhid + 

					" union  "+ 

					" select N'Thứ Năm',N'Thứ năm','" + this.getDateTime() + "','" + this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",5   " + " from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=5 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") " + " and pk_seq=" + nvbhid + 

					" union  "+ 

					" select N'Thứ Sáu',N'Thứ sáu','" + this.getDateTime() + "','" + this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",6  " + " from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=6 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") " + " and pk_seq=" + nvbhid + 

					" union  "+ 

					" select N'Thứ Bảy',N'Thứ bảy','" + this.getDateTime() + "','" + this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",7 " + " from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=7 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") " + " and pk_seq=" + nvbhid+ 
					" union  "+ 

					" select N'Chủ nhật',N'Chủ nhật','" + this.getDateTime() + "','" + this.getDateTime() + "'," + userid + "," + userid + ",pk_seq," + nppId + ",1 " + " from  daidienkinhdoanh   "+ 
					" where pk_seq not in (select ddkd_fk from tuyenbanhang where ngayid=1 and ddkd_fk=" + nvbhid + " and npp_fk=" + nppId + ") " + " and pk_seq=" + nvbhid;
				////System.out.println("Tao Tuyen ban hang : " + sql);
				db.update(sql);


				sql = "delete khachhang_tuyenbh where tbh_fk in (select pk_seq from tuyenbanhang where ddkd_fk=" + nvbhid + ") and khachhang_fk in (select pk_seq from khachhang where npp_fk=" + nppId + ") ";
				if (!db.update(sql))
				{
					//System.out.println("xoa Tuyen ban hang : " + sql);
				}

				sql = "select pk_seq,ngayid,npp_fk,ddkd_fk from tuyenbanhang where ddkd_fk=" + nvbhid + " and npp_fk=" + nppId;
				////System.out.println(sql);
				ResultSet rs = db.get(sql);
				int tang = 0;
				while (rs.next())
				{
					//System.out.println("alo123= " + rs.getString("npp_fk").trim() + "_" + rs.getString("ddkd_fk").trim() + "_" + rs.getString("ngayid").trim());
					//System.out.println("va= " + rs.getString("pk_seq").trim());
					htbtuyen.put(rs.getString("npp_fk").trim() + "_" + rs.getString("ddkd_fk").trim() + "_" + rs.getString("ngayid").trim(), rs.getString("pk_seq").trim());
					tang++;
				}
				////System.out.println("nvbhid="+ nvbhid +",npp_fk = "+ nppId + ",tang = " +tang );
			}
		}catch (Exception e) {
			e.printStackTrace();
		}

		return htbtuyen;
	}

	private Hashtable<String, String> getNhomKhachHang(dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			"		select PK_SEQ,TEN from NHOMKHACHHANG ";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("TEN").trim(), rs.getString("PK_SEQ").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}

	private Hashtable<String, String> getThanhthiNongthon(dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			"		select PK_SEQ,TEN from ThanhthiNongthon ";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("TEN").trim(), rs.getString("PK_SEQ").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}

	private Hashtable<String, String> getHangCuaHang(dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			"		select PK_SEQ,DienGiai from HANGCUAHANG ";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("DienGiai").trim(), rs.getString("PK_SEQ").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}

	private Hashtable<String, String> getPhuongXa(dbutils db)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = 
			"		select PK_SEQ,Ten from PhuongXa ";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("pk_seq").trim(), rs.getString("PK_SEQ").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}


	String getValue(Sheet sheet, int col, int row, boolean replaceDauPhay)
	{
		try{
			if (replaceDauPhay)
				return sheet.getCell(col,row).getContents().trim().replaceAll("'","").replaceAll(",", "").trim();
			else 
				return sheet.getCell(col,row).getContents().trim().trim();
		}catch (Exception e) {
			//System.out.println(" sheet " + sheet.getName() + ", col = "+ col +", row = "+ row ); 
			e.printStackTrace();
			return "";
		}
	}

	private Hashtable<String, String> getNhaphanphoiList(dbutils db,String ddkdId)
	{
		Hashtable<String, String> htbtuyen = new Hashtable<String, String>();
		String sql = "\n select PK_SEQ,MaFast " +
		"\n from NhaPhanPhoi a " +
		"\n inner join DaiDienKinhDoanh_NPP b on a.pk_seq = b.Npp_fk " +
		"\n where  b.ddkd_fk ="+ddkdId+" and   a.trangthai = 1 and ISNULL(a.isKHACHHANG,0) != 1 ";
		//System.out.println("Npp List: " + sql);
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				htbtuyen.put(rs.getString("MaFast").trim(), rs.getString("PK_SEQ").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return htbtuyen;
	}


	public String check_DauNhay(String s,int vitri,String column)
	{
		////System.out.println("Loi dau nhay" + s + vitri + column);
		if (s.trim().length() > 0 && s.indexOf("'") >=0)
			return column + " trong hàng " + (vitri + 1) + " tồn tại ký tự đặc biệt dấu nháy đơn ( ' )   ";

		return "";
	}
	private String getDateTime_MaTmp()
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		Date date = new Date();
		return dateFormat.format(date);
	}
	private Hashtable<String, String> getTanSoHash(dbutils db)
	{
		Hashtable<String, String> hTanSo = new Hashtable<String, String>();
		String sql = 
			"\n select MA,MA from TANSO ";
		ResultSet rs = db.get(sql);
		try
		{
			while (rs.next())
			{
				hTanSo.put(rs.getString("MA").trim(), rs.getString("MA").trim());
			}
			rs.close();
		} catch (Exception er)
		{
			er.printStackTrace();
		}
		return hTanSo;
	}
	
	public String InsertLog(String tacvu, String valueKh, String userId, Idbutils db) {
		String query = "\n insert khachhang_log (pk_seq,TEN,TRANGTHAI,NGAYTAO,NGAYSUA,NGUOITAO,NGUOISUA,CHIETKHAU_FK,KBH_FK,HCH_FK, " +
		"\n LCH_FK,NPP_FK,GHCN_FK,VTCH_FK,DIENTHOAI,DIACHI,BGST_FK,TINHTHANH_FK,QUANHUYEN_FK,LAT,LONG,SMARTID, " +
		"\n MASOTHUE,TANSO,HANGCUAHANG,LOAICUAHANG,VITRICUAHANG,PHUONGXA,CHUCUAHIEU,GhiChu,ImageFilePath, " +
		"\n DiaDiem_Fk,XuatKhau,ANHCUAHANG,maFAST,KhongKyHopDong,DsThang,DsQuy,TienQ,dsNhomXanh,dsNhomH, " +
		"\n NgaySinh,MaHD,DiDong,THANHTOAN,Created_Date,THANHTOANQUY,PT_CHIETKHAU,THOIHANNO,MAUHOADON,KHO_FK, " +
		"\n TrucThuoc_FK,timkiem,TenKyHd,Phuong,MST_CaNhan,XK_BACKUP,cmnd,NgayKyHD,cokhuyenmai,mauho,loaihopdong, " +
		"\n TAIKHOAN_FK,songayno,sotienno,thanhthinongthon_fk,daduyet,kt_duyet,NghiepVuSua,ThoiDiemSua, " +
		"\n MCP_IsCusNew,DiaChiGiaoHang,Import,tencuahieu,diaban_fk,MaCu,gioitinh,sonha,Ngsinh_VoChong,Ngsinh_Con1, " +
		"\n Ngsinh_Con2,nguoimuahang,apto,vochong,con1,con2,taitro,ngaytaitro,TenDuong,MaKhoSAP,insert_log,DongBo, " +
		"\n isPDA_Update,con3,ngsinh_con3,nvdq,sdtnvdq,email,DDDKTAO_FK,isPDA,NgayGioQuanLyDuyet,DiaChiXHD, " +
		"\n SoDienThoai2,SoDienThoai3,MaFast_Temp,isMCP,synced,userid,tacvu) " +
		"\n select a.pk_seq,a.TEN,a.TRANGTHAI,a.NGAYTAO,a.NGAYSUA,a.NGUOITAO,a.NGUOISUA,a.CHIETKHAU_FK,a.KBH_FK,a.HCH_FK,a. " +
		"\n LCH_FK,a.NPP_FK,a.GHCN_FK,a.VTCH_FK,a.DIENTHOAI,a.DIACHI,a.BGST_FK,a.TINHTHANH_FK,a.QUANHUYEN_FK,a.LAT,a.LONG,a.SMARTID,a. " +
		"\n MASOTHUE,a.TANSO,a.HANGCUAHANG,a.LOAICUAHANG,a.VITRICUAHANG,a.PHUONGXA,a.CHUCUAHIEU,a.GhiChu,a.ImageFilePath,a. " +
		"\n DiaDiem_Fk,a.XuatKhau,a.ANHCUAHANG,a.maFAST,a.KhongKyHopDong,a.DsThang,a.DsQuy,a.TienQ,a.dsNhomXanh,a.dsNhomH,a. " +
		"\n NgaySinh,a.MaHD,a.DiDong,a.THANHTOAN,a.Created_Date,a.THANHTOANQUY,a.PT_CHIETKHAU,a.THOIHANNO,a.MAUHOADON,a.KHO_FK,a. " +
		"\n TrucThuoc_FK,a.timkiem,a.TenKyHd,a.Phuong,a.MST_CaNhan,a.XK_BACKUP,a.cmnd,a.NgayKyHD,a.cokhuyenmai,a.mauho,a.loaihopdong,a. " +
		"\n TAIKHOAN_FK,a.songayno,a.sotienno,a.thanhthinongthon_fk,a.daduyet,a.kt_duyet,a.NghiepVuSua,a.ThoiDiemSua,a. " +
		"\n MCP_IsCusNew,a.DiaChiGiaoHang,a.Import,a.tencuahieu,a.diaban_fk,a.MaCu,a.gioitinh,a.sonha,a.Ngsinh_VoChong,a.Ngsinh_Con1,a. " +
		"\n Ngsinh_Con2,a.nguoimuahang,a.apto,a.vochong,a.con1,a.con2,a.taitro,a.ngaytaitro,a.TenDuong,a.MaKhoSAP,a.insert_log,a.DongBo,a. " +
		"\n isPDA_Update,a.con3,a.ngsinh_con3,a.nvdq,a.sdtnvdq,a.email,a.DDDKTAO_FK,a.isPDA,a.NgayGioQuanLyDuyet,a.DiaChiXHD,a. " +
		"\n SoDienThoai2,a.SoDienThoai3,a.MaFast_Temp,a.isMCP,a.synced,"+userId+",N'"+tacvu+"' " +
		"\n from khachhang a inner join #temp_kh tmp on cast(a.pk_seq as varchar) = tmp.pk_seq ";
		if (!db.update(query)) {
			return "Lỗi ghi Log!";
		}
		
		return "";
	}
	
	public static void main(String[] args) {
		
		String query = "\n insert makhachhang " +
			"\n select kh.pk_seq,convert(int,substring(kh.mafast,4,8)),mafast, " +
			"\n (select vung_fk from tinhthanh where pk_seq = kh.tinhthanh_fk)vung_fk,  " +
			"\n case when (select vung_fk from tinhthanh where pk_seq = kh.tinhthanh_fk) = 100016 then 'DN' else 'DS' end,tt.ma,qh.ma " +
			"\n from khachhang kh  " +
			"\n inner join TinhThanh tt on kh.TinhThanh_FK = tt.pk_seq  " +
			"\n inner join Quanhuyen qh on qh.TinhThanh_FK = tt.pk_seq and kh.QuanHuyen_FK = qh.pk_seq " +
			"\n where kh.pk_seq in ( " +
			"\n 	select pk_seq from khachhang a  " +
			"\n 	where not exists (select 1 from makhachhang where khachhang_fk = a.pk_seq) " +
			"\n 	and isnull(ispda,0) = 0 " +
			"\n 	and len(a.mafast) = 7 " +
			"\n 	and isnumeric(substring(a.mafast,4,1)) = 1 " +
			"\n ) ";

	}
	
	/*public static void main(String[] args) {
		dbutils db = new dbutils();
		
		Hashtable<String, String> htp_quanId = new Hashtable<String, String>();;
		Hashtable<String, String> htp_quanTen = new Hashtable<String, String>();;
		Hashtable<String, String> htp_tinhFk = new Hashtable<String, String>();;
		gethtpQuanHuyen( db, htp_quanId
					,  htp_quanTen, htp_tinhFk);
		 System.err.println("htp_quanId = "+ htp_quanId.size());
		 System.err.println("htp_quanTen = "+ htp_quanTen.size());
		db.shutDown();
	}*/
	
	public void createTempTable(Idbutils db) {
		String query = "\n IF OBJECT_ID('tempdb.dbo.#temp_kh') IS NOT NULL DROP TABLE #temp_kh " +
		"\n create table #temp_kh ( " +
		"\n pk_seq nvarchar(max), " +
		"\n timkiem nvarchar(max), " +
		"\n Import nvarchar(max), " +
		"\n kho_fk nvarchar(max), " +
		"\n daduyet nvarchar(max), " +
		"\n lch_fk nvarchar(max), " +
		"\n diachigiaohang nvarchar(max), " + 	
		"\n tenkh nvarchar(max), " +
		"\n tencuahieu nvarchar(max), " +
		"\n tt nvarchar(max), " +
		"\n ngaytao varchar(10), " +
		"\n ngaysua varchar(10), " +
		"\n nguoitao nvarchar(max), " +
		"\n nguoisua nvarchar(max), " +
		"\n kbh nvarchar(max), " +
		"\n nppId nvarchar(max), " +
		"\n dienthoai nvarchar(max), " +
		"\n didong nvarchar(max), " +
		"\n diachi nvarchar(max), " +
		"\n tinhthanh_fk nvarchar(max), " +
		"\n quanhuyen_fk nvarchar(max), " +
		"\n SmartId nvarchar(max), " +
		"\n nguoimuahang nvarchar(max), " +
		"\n chietkhau nvarchar(max), " +
		"\n vitri nvarchar(max), " +
		"\n tenduong nvarchar(max), " +
		"\n apto nvarchar(max), " +
		"\n NgaySinh varchar(10), " +
		"\n MaHD nvarchar(max), " +
		"\n masothue nvarchar(max), " +
		"\n xuatkhau nvarchar(max), " +
		"\n MaFast nvarchar(max), " +
		"\n KhongKyHopDong nvarchar(max), " +
		"\n songayno nvarchar(max), " +
		"\n sotienno nvarchar(max), " +
		"\n long nvarchar(max), " +
		"\n lat nvarchar(max), " +
		"\n vochong nvarchar(max), " +
		"\n ngsinh_vochong nvarchar(max), " +
		"\n con1 nvarchar(max), " +
		"\n con2 nvarchar(max), " +
		"\n ngsinh_con1 varchar(10), " +
		"\n ngsinh_con2 varchar(10), " +
		"\n con3 nvarchar(max), " +
		"\n ngsinh_con3 varchar(10), " +
		"\n nvdq nvarchar(max), " +
		"\n sdtnvdq nvarchar(max), " +
		"\n email nvarchar(max), " +
		"\n ghichu nvarchar(max), " +
		"\n taitro nvarchar(max), " +
		"\n ngaytaitro varchar(10), " +
		"\n HCH_FK nvarchar(max), " +
		"\n phuongxa_fk nvarchar(max), " +
		"\n macu nvarchar(max), " +
		"\n thanhthinongthon_fk nvarchar(max), " +
		"\n sonha nvarchar(max), " +
		"\n mafast_temp nvarchar(max) ) ";
		db.update(query);
		
		query = "\n IF OBJECT_ID('tempdb.dbo.#temp_tuyen') IS NOT NULL DROP TABLE #temp_tuyen " +
		"\n create table #temp_tuyen (mafast nvarchar(max), tbh_fk numeric, tanso varchar(10), sott varchar(10)) ";
		db.update(query);
	}
}
