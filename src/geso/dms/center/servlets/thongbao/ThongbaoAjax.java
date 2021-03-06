package geso.dms.center.servlets.thongbao;

import geso.dms.distributor.db.sql.dbutils;
import geso.dms.distributor.util.Utility;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThongbaoAjax extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    public ThongbaoAjax() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		/*HttpSession session = request.getSession();
		String userId=(String)session.getAttribute("userId");
		String query ="update NHANVIEN set ISLOGIN='0' where PK_SEQ='"+userId+"'";
		dbutils db=new dbutils();
		db.update(query);
		db.shutDown();
		session.invalidate();*/
	}

	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		String task = request.getParameter("action");
		if(task==null)
			task="";
	
		if(task.equals("download"))
		{
			
			dbutils db=new dbutils();
		    String id= request.getParameter("id");
		    String query = "select filename from thongbao where pk_seq='"+id+"'";
			//System.out.println("cau select: "+query);
			ResultSet rs = db.get(query);
			String filename="";
			try {
				rs.next();
				filename = rs.getString("filename");
			} catch (SQLException e) {
				
			}
			String[] spli=new String[3];
  			spli= filename.split(",");
		    FileInputStream fileToDownload = new FileInputStream("C:\\java-tomcat\\dinhkem\\"+filename);
		    ServletOutputStream output = response.getOutputStream();
		    response.setContentType("application/octet-stream");
		    String tenfile=spli[0]+spli[2];
		    ////System.out.println(tenfile);
		    response.setHeader("Content-Disposition","attachment; filename=\"" + tenfile + "\"");
		    response.setContentLength(fileToDownload.available());
		    int c;
		    while ((c = fileToDownload.read()) != -1)
		    {
		    output.write(c);
		    }
		    output.flush();
		    output.close();
		    fileToDownload.close();
		}
		else 
		{
		    PrintWriter out = response.getWriter();
			Utility util = new Utility();
			String q = util.antiSQLInspection(request.getParameter("q"));
			String id = util.antiSQLInspection(request.getParameter("id"));
			
				
			
			if(q != null)
			{
				
				dbutils db = new dbutils();
				String query ="";
				String chuoi2="";
				try
				{
					query ="select count(*) sodong from  nhanvien where ( gsbh_fk is not null or BM_FK is not null  ) and pk_seq = " +id;
					//System.out.println("Thong Bao: "+query);
					ResultSet rsCheck = db.get(query);
					rsCheck.next();
					int isGSBH = rsCheck.getInt("sodong");
					if(isGSBH >0)
					{
						//
					//	//System.out.println("aaaa=" +;
						if(getDateTime().compareTo("2015-07-31") <0  )// demo cho h???ng mai t???m
						{
							query =  "\n DECLARE @denngay varchar(10)='"+getDateTime()+"'  -- " + 
							 "\n declare @tungay varchar(10)= convert(varchar(10),DATEADD(dd, -90, @denngay) ,120) -- " + 
							 "\n select case when tonkho < dh.soluong*TONKHOTOITHIEU then 1 else 0 end loai , npp.PK_SEQ nppId ,npp.TEN as npp , sp.ten as sanpham,dh.soluong tbNgay,tonkho,npp.TONKHOTOITHIEU ,npp.TonKhoAnToan ,npp.TONKHOTOITHIEU * dh.soluong as kqToiThieu ,npp.TonKhoAnToan*dh.soluong as kqAnToan -- " + 
							 "\n from NHAPHANPHOI npp -- " + 
							 "\n inner join PHAMVIHOATDONG pvhd on pvhd.npp_fk = npp.pk_seq and pvhd.nhanvien_fk = "+id+" -- " + 
							 "\n inner join TrungBinhBanNgay dh on dh.npp_fk = npp.PK_SEQ and dh.tungay <=@denngay and dh.denngay >=@denngay   -- " + 
							 "\n inner join SANPHAM sp on dh.SANPHAM_FK = sp.PK_SEQ -- " + 
							 "\n left join  -- " + 
							 "\n 	select NPP_FK,sanpham_fk, SUM(soluong)tonkho  -- " + 
							 "\n 	from NHAPP_KHO  -- " + 
							 "\n 	group by NPP_FK,sanpham_fk -- " + 
							 "\n )kho on kho.SANPHAM_FK = dh.SANPHAM_FK and kho.NPP_FK =dh.NPP_FK -- " +
							 "\n where npp.loainpp <> 0 and  tonkho < dh.soluong*TONKHOTOITHIEU or tonkho > npp.TonKhoAnToan*dh.soluong  " +
							 "\n Union  " + 
							 "\n select case when tonkho < dh.soluong*TONKHOTOITHIEU then 1 else 0 end loai ,npp.PK_SEQ nppId,npp.TEN as npp , sp.ten as sanpham,dh.soluong tbNgay,tonkho,npp.TONKHOTOITHIEU,npp.TonKhoAnToan,npp.TONKHOTOITHIEU * dh.soluong as kqToiThieu,npp.TonKhoAnToan*dh.soluong as kqAnToan --" +
							 "\n from NHAPHANPHOI npp -- " + 
							 "\n inner join PHAMVIHOATDONG pvhd on pvhd.npp_fk = npp.pk_seq and pvhd.nhanvien_fk = "+id+" -- " +
							 "\n inner  join -- " + 
							 "\n ( -- " + 
							 "\n 	select NPP_FK,SANPHAM_FK,SUM(dhsp.soluong)/90  as soluong -- " + 
							 "\n 	from DONHANG dh -- " + 
							 "\n 	inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK -- " + 
							 "\n 	where dh.TRANGTHAI = 1 and dh.NGAYNHAP>=@tungay and dh.NGAYNHAP <=@denngay -- " + 
							 "\n 	group by npp_fk,SANPHAM_FK -- " + 
							 "\n )dh on dh.npp_fk = npp.pk_seq  -- " + 
							 "\n inner join SANPHAM sp on dh.SANPHAM_FK = sp.PK_SEQ -- " + 
							 "\n left join  -- " + 
							 "\n ( -- " + 
							 "\n 	select NPP_FK,sanpham_fk, SUM(soluong)tonkho  -- " + 
							 "\n 	from NHAPP_KHO  -- " + 
							 "\n 	group by NPP_FK,sanpham_fk -- " + 
							 "\n )kho on kho.SANPHAM_FK = dh.SANPHAM_FK and kho.NPP_FK =dh.NPP_FK -- " +
							 "\n where npp.pk_seq != 106306 and  npp.loainpp <> 0 and tonkho < dh.soluong*TONKHOTOITHIEU or tonkho > npp.TonKhoAnToan*dh.soluong "  +
							 "\n order by npp desc, loai desc" ;
						}
						else
						{
							query =  "\n DECLARE @denngay varchar(10)='"+getDateTime()+"'  -- " + 
							 "\n declare @tungay varchar(10)= convert(varchar(10),DATEADD(dd, -90, @denngay) ,120) -- " + 
							 "\n select case when tonkho < dh.soluong*TONKHOTOITHIEU then 1 else 0 end loai ,npp.PK_SEQ nppId,npp.TEN as npp , sp.ten as sanpham,dh.soluong tbNgay,tonkho,npp.TONKHOTOITHIEU,npp.TonKhoAnToan,npp.TONKHOTOITHIEU * dh.soluong as kqToiThieu,npp.TonKhoAnToan*dh.soluong as kqAnToan -- " + 
							 "\n from NHAPHANPHOI npp -- " + 
							 "\n inner join PHAMVIHOATDONG pvhd on pvhd.npp_fk = npp.pk_seq and pvhd.nhanvien_fk = "+id+" -- " +
							 "\n inner  join -- " + 
							 "\n ( -- " + 
							 "\n 	select NPP_FK,SANPHAM_FK,SUM(dhsp.soluong)/90  as soluong -- " + 
							 "\n 	from DONHANG dh -- " + 
							 "\n 	inner join DONHANG_SANPHAM dhsp on dh.PK_SEQ = dhsp.DONHANG_FK -- " + 
							 "\n 	where dh.TRANGTHAI = 1 and dh.NGAYNHAP>=@tungay and dh.NGAYNHAP <=@denngay -- " + 
							 "\n 	group by npp_fk,SANPHAM_FK -- " + 
							 "\n )dh on dh.npp_fk = npp.pk_seq  -- " + 
							 "\n inner join SANPHAM sp on dh.SANPHAM_FK = sp.PK_SEQ -- " + 
							 "\n left join  -- " + 
							 "\n ( -- " + 
							 "\n 	select NPP_FK,sanpham_fk, SUM(soluong)tonkho  -- " + 
							 "\n 	from NHAPP_KHO  -- " + 
							 "\n 	group by NPP_FK,sanpham_fk -- " + 
							 "\n )kho on kho.SANPHAM_FK = dh.SANPHAM_FK and kho.NPP_FK =dh.NPP_FK -- " +
							 "\n where npp.loainpp <> 0 and tonkho < dh.soluong*TONKHOTOITHIEU or tonkho > npp.TonKhoAnToan*dh.soluong "  +
							 "\n order by npp desc, loai desc " ;
						}
						//System.out.println("query = " + query);
						ResultSet rs2 = db.get(query);
						int i =0;
						if(rs2 != null)
						while(rs2.next())
						{
							
							if(i==0)
							{
								chuoi2 = "<p align=\"center\" style=' color: red; font-weight: bold;' >C???nh b??o t???n kho</p>" +
										 "<p  align=\"center\"  style='color: black; font-size: 15px' >M??u ?????: d?????i m???c t???i thi???u; M??u xanh: tr??n m???c t???i ??a</p> " ;
								chuoi2 += "<div align=\"center\"  style=\"height:200px; overflow: auto;\" >";
								chuoi2 +="<TABLE  width=\"100%\" border=\"0\" cellspacing=\"1\" cellpadding=\"4\">" +
										 "<TR class=\"tbheader\">" +
										 " <TH width=\"30%\">Nh?? ph??n ph???i</TH> " +
										 " <TH width=\"30%\">S???n ph???m</TH> " +
										 " <TH width=\"10%\">Trung b??nh b??n/Ng??y</TH> " +
										 " <TH width=\"10%\">T???n kho t???i thi???u</TH> " +
										 " <TH width=\"10%\">T???n kho an to??n</TH> " +
										 " <TH width=\"10%\">T???n hi???n t???i</TH> " +
										 "</TR>";
								
							}
							String kqTT ="",kqAT="";
							
							if(rs2.getString("loai").equals("1"))
							{
								kqTT = rs2.getString("kqToiThieu");
								kqAT = "";
							}
							else
							{
								kqTT = "";
								kqAT = rs2.getString("kqAnToan");
							}
								
							chuoi2 +="<TR>" +
										 "<td><input type=\"text\" value=\""+rs2.getString("npp")+"\" style=\"width: 100%;\" readonly> </td>" +
										 (rs2.getString("loai").equals("1") ? 
											 "<td><input type=\"text\" value=\""+rs2.getString("sanpham")+"\" style=\"width: 100%; color:red;\" readonly> </td>"	 
										 :
											 "<td><input type=\"text\" value=\""+rs2.getString("sanpham")+"\" style=\" width: 100%;color:blue;\" readonly> </td>"
										 ) +
										 "<td><input type=\"text\" value=\""+rs2.getString("tbNgay")+"\" style=\"width: 100%;\" readonly> </td>" +
										 "<td><input type=\"text\" value=\""+kqTT+"\" style=\"width: 100%;\" readonly> </td>" +
										 "<td><input type=\"text\" value=\""+kqAT+"\" style=\"width: 100%;\" readonly> </td>"+
										 
										 "<td><input type=\"text\" value=\""+rs2.getString("tonkho")+"\" style=\"width: 100%;\" readonly> </td>"+
									 "</TR>";
							//chuoi2 += "<li><p style=\"color: black;font-size:10px\">"+rs2.getString("sanpham") +"</p></li> ";
							i++;
						}
						if(chuoi2.trim().length() >0)
							//chuoi2 += "</div></ul>"; 
							chuoi2 += "</TABLE></div>";
					}
					
				
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				
				String header="";
				query =  " select count(pk_seq) as soluong from thongbao a " +
						 " where  exists (select top(1)* from THONGBAO_NHANVIEN where nhanvien_fk = '"+ id +"' and trangthai = 0 and thongbao_fk = a.pk_seq)";
				//System.out.println("Dem so thong bao: "+query);
				ResultSet rs = db.get(query);
				int soTB = 0;
				try 
				{
					if(rs.next())
					{
						soTB = rs.getInt("soluong");
					}
					rs.close();
					header = 
					/*"H???p th?? ?????n  <span class=\"label label-danger pull-center\">"+ soTB +"</span>";*/
						"<li class=\"nav-item dropdown\"> "+
						"<a class=\"nav-link\" data-toggle=\"dropdown\" href=\"#\"> " +
						"<i class=\"far fa-lg fa-bell\"></i> ";
					if(soTB > 0) { header += "<span class=\"badge badge-danger navbar-badge\">"+ soTB +"</span> "; }
					header +=
						"</a> " +
						"<div class=\"dropdown-menu dropdown-menu-lg dropdown-menu-right\"> "+
						"<span class=\"dropdown-item dropdown-header\">H???p th?? ?????n</span>";
					
				} 
				catch(Exception e) 
				{
					header = //"H???p th?? ?????n <span class=\"label label-danger pull-center\">0</span>";
						"<li class=\"nav-item dropdown\"> "+
						"<a class=\"nav-link\" data-toggle=\"dropdown\" href=\"#\"> " +
						"<i class=\"far fa-lg fa-bell\"></i> " +
						"</a> " +
						"<div class=\"dropdown-menu dropdown-menu-lg dropdown-menu-right\"> "+
						"<span class=\"dropdown-item dropdown-header\">H???p th?? ?????n</span>";
					e.printStackTrace();
				}
				
				String headerTong = "";
				String detail = "";
				String footer_div = "";
				String detail_div = " <div class=\"box-body text-left\"> ";
				headerTong = "<div class=\"box box-primary\" style=\"border: 1px solid #dddbdb;\"> "+
				  "<div class=\"box-header with-border\"> "+
				  "<h3 class=\"box-title\">H???p th?? ?????n</h3> " +
				  "</div> "; 
			
				query = " select top(10) a.pk_seq, case when len(a.tieude) > 15 then substring(a.tieude,0, 15)+'...' else a.tieude end as tieude, " +
						" a.ngaysua "+
						" from thongbao a " +
						" where exists ( select 1 from THONGBAO_NHANVIEN where nhanvien_fk = '"+ id +"' " +
						" and trangthai = '0' and thongbao_fk = a.pk_seq ) ";
				rs = db.get(query);
				try {
					while (rs.next())
					{
						detail +=
						" <div class=\"dropdown-divider\"></div> " +
						" <a href=\"../../ThongbaoUpdateSvl?task=capnhatnv&id="+ rs.getString("PK_SEQ") +"\" class=\"dropdown-item\"> " +
						" <i class=\"fas fa-envelope mr-2\"></i> "+ rs.getString("TIEUDE") +"" +
						" <span style=\"font-size : 0.75rem;\" class=\"float-right text-muted \">"+ rs.getString("NGAYSUA") +"</span> " +
						" </a> ";
								
							/*"<ul class=\"products-list product-list-in-box\"> "+
							"<li class=\"item\" > "+  
							"<div class=\"product-info\"> "+
							"<a href=\"../../ThongbaoUpdateSvl?task=capnhatnv&id="+ rs.getString("PK_SEQ") +"\" class=\"product-title\">"+ rs.getString("TIEUDE") +"</a> "+
							" </div> "+
							"</li> "+
							"</ul> ";*/
					}
					if(rs!=null){ rs.close(); }
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				footer_div = 
					/*"<div class=\"box-footer text-center\"> "+
				    "<a class=\"btn btn-default\" href=\"../../ThongbaoSvl?userId="+ id +"&task=1\" target=\"content\">"+ header +"</a> "+
				    "</div> ";*/
				
					"<div class=\"dropdown-divider\"></div> " +
					"<a href=\"../../ThongbaoSvl?userId="+ id +"&task=1\" class=\"dropdown-item dropdown-footer\">Xem th??ng b??o</a> " +
					"</div> </li> ";
				
				detail_div += detail + "</div> ";
				//out.write(header + detail_div + footer_div);
				//System.out.println("result : "+header + detail + footer_div);
				out.write(header + detail + footer_div);
				
				/*------------------------------------------------------------*/
				
				/*String chuoi = "";
				if(chuoi2.trim().length() >0)
					chuoi = chuoi2;
				chuoi += "<div align=\"center\"><p align=\"center\" style=' color: red; font-weight: bold;' >H???p th?? m???i ch??a ?????c (" + soTB +  ")</p>";

				query = "select top 10 pk_seq,tieude from THONGBAO a  " +
						"where  exists (select top(1)* from THONGBAO_NHANVIEN where nhanvien_fk = '"+ id +"' and trangthai = 0 and thongbao_fk = a.pk_seq) order by ngaybatdau desc";
				rs = db.get(query);
				if(rs != null)
				{
					chuoi += "<ul  align=\"center\"  id=\"thongbao\" style=\"display:none\"><li align=\"center\"><p  align=\"center\" style=\"color:red \">C??c th?? ?????n ch??a ?????c:</p></li>";
					try 
					{
						while(rs.next())
						{
							chuoi += "<li><A href=\"../../ThongbaoUpdateSvl?task=capnhatnv&id="+ rs.getString("PK_SEQ") +"\"><strong>"+rs.getString("tieude") +"</A></strong></li> ";
						}
						rs.close();
					} 
					catch (Exception e) 
					{
						e.printStackTrace();
					}
					finally
					{ 
						if(db!=null)db.shutDown();
					}
					chuoi += "</ul></div>";
				}
				out.write(chuoi);*/
				
			}
			
		}
	}
}
