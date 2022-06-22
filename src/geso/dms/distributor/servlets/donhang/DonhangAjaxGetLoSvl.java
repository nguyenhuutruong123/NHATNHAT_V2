package geso.dms.distributor.servlets.donhang;
 

import geso.dms.distributor.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DonhangAjaxGetLoSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
    
    public DonhangAjaxGetLoSvl() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    
	    System.out.println("vao dknhangajax " );
		PrintWriter out = response.getWriter();
		
		String ngaygiaodich = request.getParameter("ngaygiaodich");
		String masp = request.getParameter("sanphamid");
		String khoid = request.getParameter("khoid");
		String vitri = request.getParameter("vitri");
		String nppId = request.getParameter("nppId");
		String khId = request.getParameter("khId");
		System.out.println("Nhom san pham: " + masp);
		dbutils db = new dbutils();
		try{
	
		
//		int count = 0;
		if(masp.length() > 0)
		{
			String query= 	 "\n SELECT top 10 case when KHO.SoLuong > 0 then 0 else 1 end loai,soluong,AVAILABLE, SOLO, NGAYHETHAN ,KHO.ngaynhapkho  " + 
							 "\n FROM NHAPP_KHO_CHITIET  KHO   " +
							 "\n inner join Nhaphanphoi npp on npp.pk_seq = kho.npp_fk " + 
							 "\n INNER JOIN SANPHAM SP ON SP.PK_SEQ=KHO.SANPHAM_FK  " + 
							 "\n WHERE  KHO.KHO_FK = "+khoid+"  " +
							 "\n AND KHO.NPP_FK = "+nppId+" " +
							 "\n	and KHO.KBH_FK = case npp.DUNGCHUNGKENH  when 1 then 100025 else (select kbh_fk from khachhang where pk_Seq = "+khId+") end  " + 
							 "\n 	and SP.MA='"+masp+"'  and ngaynhapkho <='"+ngaygiaodich+"'  " +
							 "\n ORDER BY  loai asc,NGAYHETHAN asc, ngaynhapkho asc  " ;
			
			System.out.println("345.Lay san pham: " + query);
			ResultSet splo = db.get(query);	
				
			String table = "<table width='90%'  align='center'> " +
							"<tr> <th width='100px'> Số lô</th> " + 
							"<th width='100px'> Ngày nhập kho</th> " +
							"<th width='100px'> Ngày hết hạn</th> " +
							"<th width='50px'> Số lượng tồn </th>   " +
							"<th width='50px'> Số lượng  </th> </tr> " ;
			 		while(splo.next())
			 		{
			 			table+=	" <tr> <th width='100px'> " +
			 					" <input type='text' style='width:100%' name='"+vitri+".solo' value='"+splo.getString("SOLO")+"' readonly='readonly' /> " +
			 					" </th> " + 
						"<th width='100px'>  " +
						" <input type='text' style='width:100%' name='"+vitri+".ngaynhapkho' value='"+splo.getString("ngaynhapkho")+"' readonly='readonly' /> " +
						
						"</th> " +
						" <th width='100px'>  " +
						" <input type='text' style='width:100%' name='"+vitri+".ngayhethan' value='"+ splo.getString("NGAYHETHAN")+"' readonly='readonly' /> " +
						" </th> " +
						" <th width='50px'>  " +
						" <input type='text' style='width:100%' name='"+vitri+".soluongton' value='"+ splo.getInt("AVAILABLE")+"' readonly='readonly' /> " +
						" </th>  " +
						" <th width='50px'>  " +
						" <input type='text' style='width:100%' name='"+vitri+".soluong' value=''  /> " +
						" </th>  " +
						
						"</tr> " ;
			 			
			 		}
			 		splo.close();
		     
		    	table += "</table> " ;
		    	table += 	" <div align=right><a href=javascript:dropdowncontent.hidediv('subcontent"+vitri+"'); >Hoàn tất</a></div> ";
		    	
		    	out.write(table);
		}
		}catch(Exception er){
			er.printStackTrace();
		}finally{
			db.shutDown();
		}
	}
	
 
	private String getDateTime()
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
}
