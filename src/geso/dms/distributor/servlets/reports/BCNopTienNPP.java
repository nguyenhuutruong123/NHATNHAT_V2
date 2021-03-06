package geso.dms.distributor.servlets.reports;

import geso.dms.center.beans.stockintransit.IStockintransit;
import geso.dms.center.beans.stockintransit.imp.Stockintransit;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.report.ReportAPI;
import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.khachhang.IKhachhangList;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aspose.cells.BorderLineType;
import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.FileFormatType;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


@WebServlet("/BCNopTienNPP")
public class BCNopTienNPP extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public BCNopTienNPP() {
		super();
		// TODO Auto-generated constructor stub
	}

	String nextJSP="";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		HttpSession session = request.getSession();
		String querystring = request.getQueryString();
		Utility util =new Utility();

		String userId = util.getUserId(querystring);
		//out.println(userId);

		Stockintransit obj = new Stockintransit();
		obj.setuserId(userId);
		obj.init_user();
		session.setAttribute("obj", obj);
		nextJSP=request.getContextPath() + "/pages/Distributor/BCNopTienNPP.jsp";
		response.sendRedirect(nextJSP);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset:UTF-8");
		HttpSession session = request.getSession();
		Utility util = new Utility();

		String userId = util.antiSQLInspection(request.getParameter("userId"));
		String userTen = util.antiSQLInspection(request.getParameter("userTen"));
		String action = util.antiSQLInspection(request.getParameter("action"));
		Stockintransit obj = new Stockintransit();
	
		obj.setuserId(userId);
		obj.setuserTen(userTen);
		obj.init_user();
		String tungay = util.antiSQLInspection(request.getParameter("Sdays"));
		obj.settungay(tungay);

		String denngay = util.antiSQLInspection(request.getParameter("Edays"));
		obj.setdenngay(denngay);

		/*String nppid = util.antiSQLInspection(request.getParameter("nhapp"));
		if(nppid==null) nppid = "";
		obj.setnppId(nppid);*/

		String vungId = util.antiSQLInspection(request.getParameter("vungId"));
		if(vungId == null) vungId = "";
		obj.setvungId(vungId);

		String khuvucId = util.antiSQLInspection(request.getParameter("khuvucId"));
		if(khuvucId == null) khuvucId = "";
		obj.setkhuvucId(khuvucId);
		
		String type = util.antiSQLInspection(request.getParameter("type"));
		if(type == null) type = "";
		obj.settype(type);

		if(action.contains("excel"))
		{
			

			try
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/xlsm");
				response.setHeader("Content-Disposition", "attachment; filename=BCXNT.xls");
				OutputStream out1 = response.getOutputStream();
				String query ="";
				query = setQuery(obj);
				ExportToExcel( out1, obj, query );
				obj.getDb().shutDown();
			}
			catch (Exception ex)
			{
				obj.setMsg("Khong the tao pivot.");
			}




			session.setAttribute("obj", obj);

			session.setAttribute("userId", userId);
			return;
		}
		else
		{
			obj.init();
			session.setAttribute("obj", obj);
			String nextJSP = request.getContextPath() + "/pages/Distributor/BCNopTienNPP.jsp";
			response.sendRedirect(nextJSP);
		}

	}
	public String setQuery(Stockintransit obj)
	{
		
		
		String query= 
		
			"\n select npp.MaFAST [M?? ?????I T?????NG] ,npp.ten [T??N ?????I T?????NG] , dauky.sotien [?????U K???_isNum], nhap.sotien [NH???P_isNum], xuat.sotien [XU???T_isNum] , ISNULL( dauky.sotien,0) + ISNULL(nhap.sotien,0) + ISNULL(xuat.sotien,0) [CU???I K???_isNum] " +
			"\n from NHAPHANPHOI npp " +
			"\n left join [dbo].ufn_BC_Nop_Tien_NPP( null,convert( varchar(10), DATEADD(DAY,-1,'"+obj.gettungay()+"'),120), "+obj.getnppId()+" ,0 , 1 , 1) dauky  on dauky.doituong_fk =npp.pk_seq " +
			"\n left join [dbo].ufn_BC_Nop_Tien_NPP( '"+obj.gettungay()+"','"+obj.getdenngay()+"', "+obj.getnppId()+" ,0 , 1 , 0) nhap on nhap.doituong_fk = npp.pk_seq " +
			"\n left join [dbo].ufn_BC_Nop_Tien_NPP( '"+obj.gettungay()+"','"+obj.getdenngay()+"', "+obj.getnppId()+" ,0 , 0 , 1) xuat on xuat.doituong_fk = npp.pk_seq " +
			"\n where isnull(dauky.doituong_fk,isnull(xuat.doituong_fk,nhap.doituong_fk)) is not null " ;
		if(obj.gettype().equals("1"))
		{
			 query= 
					
					"\n select npp.MaFAST [M?? ?????I T?????NG] ,npp.ten [T??N ?????I T?????NG] , dauky.sotien [?????U K???], nhap.sotien [NH???P], xuat.sotien [XU???T] , ISNULL( dauky.sotien,0) + ISNULL(nhap.sotien,0) + ISNULL(xuat.sotien,0) [CU???I K???] " +
					"\n from khachhang npp " +
					"\n left join [dbo].ufn_BC_Nop_Tien_NPP( null,convert( varchar(10), DATEADD(DAY,-1,'"+obj.gettungay()+"'),120), "+obj.getnppId()+" ,1 , 1 , 1) dauky  on dauky.doituong_fk =npp.pk_seq " +
					"\n left join [dbo].ufn_BC_Nop_Tien_NPP( '"+obj.gettungay()+"','"+obj.getdenngay()+"', "+obj.getnppId()+" ,1 , 1 , 0) nhap on nhap.doituong_fk = npp.pk_seq " +
					"\n left join [dbo].ufn_BC_Nop_Tien_NPP( '"+obj.gettungay()+"','"+obj.getdenngay()+"', "+obj.getnppId()+" ,1 , 0 , 1) xuat on xuat.doituong_fk = npp.pk_seq " +
					"\n where isnull(dauky.doituong_fk,isnull(xuat.doituong_fk,nhap.doituong_fk)) is not null " ;
		}
		System.out.println("xxxx : "+query);
		return query;
	}

	public String setQueryct(Stockintransit obj)
	{
		String condition = " and dh.ddkd_fk in ("+Utility.PhanQuyenDDKD(obj.getuserId())+") and dh.npp_fk in ("+Utility.Quyen_npp(obj.getuserId())+") ";
		if(obj.getnppId().length()>0) 
		{
			condition+= " and dh.npp_fk ='" + obj.getnppId() + "' ";
		}
		else if( obj.getkhuvucId().length() > 0)
			condition+= " and dh.npp_fk in ( select '" + obj.getnppId() + "' ";
			
		String query= 
		
		"\n with dh as " +
		"\n ( " +
		"\n 	select * " +
		"\n 	from donhang dh " +
		"\n 	where dh.ngaynhap >= '"+obj.gettungay()+"' and dh.ngaynhap <= '"+obj.getdenngay()+"' and dh.trangthai <>2  " + condition +
		"\n ) " +
		"\n select KH.MAFAST [M?? KH], KH.TEN [T??N KH], KH.NGUOILIENHE [NG?????I LI??N H???], KH.DiaChiGiaoHang [??/C GIAO H??NG],dh.PK_SEQ [S??? ????N H??NG] , DH.NGAYNHAP [NG??Y ????N H??NG] " +
		"\n 	,HD.SOHOADON [S??? H??A ????N], HD.NGAYXUATHD [NG??Y H??A ????N],XK.ngayquet [NG??Y QU??T BARCODE],ISNULL(XK.PHANLOAI,N'KH??NG QU??T')[PH??N LO???I ????N] " +
		"\n 	,XK.ngayhoantat [NG??Y HO??N T???T]	 , dh.trangthaiText [TR???NG TH??I ????N] , XK.tinhtrangQR [T??NH TR???NG QU??T QR CODE] ,sp.MA [M?? SP] , sp.TEN [T??N SP],xk.BARCODE " +
		"\n from dh " +
		"\n INNER JOIN KHACHHANG KH ON KH.PK_SEQ = DH.KHACHHANG_FK " +
		"\n outer apply " +
		"\n ( " +
		"\n 	select hd.pk_seq hdId, hd.sohoadon, hd.NGAYXUATHD " +
		"\n 	from hoadon hd " +
		"\n 	inner join hoadon_ddh x on hd.pk_Seq = x.hoadon_fk " +
		"\n 	where x.ddh_fk = dh.pk_seq and hd.TRANGTHAI in (2,4) " +
		"\n )hd " +
		"\n outer apply " +
		"\n ( " +
		"\n 	select CAST (N'QR' AS VARCHAR(100)) PHANLOAI,xk.pk_seq xkId, CASE WHEN xk.trangthaiBarcode IN (1,2) THEN  xk.ngaysua  ELSE NULL END ngayquet  " +
		"\n 		,  convert( varchar(10),xk.td_chot,120) ngayhoantat " +
		"\n 		, case when xk.trangthaiBarcode = 2 then N'???? qu??t ?????' when xk.trangthaiBarcode = 1 then N'???? qu??t ch??a ho??n t???t'  " +
		"\n 				when  xk.trangthaiBarcode = 0 then N'Ch??a ho??n t???t' else NULL end tinhtrangQR  ,ct.barcode, ct.sanpham_fk " +
		"\n 	from XuatKhoBarCode xk " +
		"\n 	inner join XuatKhoBarCode_HoaDon x on xk.pk_Seq = x.xuatkho_fk " +
		"\n		inner join XuatKhoBarCode_SanPham ct on ct.xuatkho_fk = xk.pk_seq and ct.barcode <> '' " +
		"\n 	where xk.trangthai <> 2 and x.hoadon_fk = hd.hdId " +
		"\n )xk " +
		"\n	left join SANPHAM sp on sp.PK_SEQ = xk.sanpham_fk " +
		"\n ORDER BY KH.maFAST " ;
		
		
		System.out.println("setQueryct BAR : "+query);
		return query;
	}


	private void ExportToExcel(OutputStream out,IStockintransit obj,String query )throws Exception
	{
		try{ 					


			com.aspose.cells.Workbook workbook = new com.aspose.cells.Workbook();
			workbook.setFileFormatType(FileFormatType.EXCEL2003);
			TaoBaoCao(workbook, obj, query);			
			workbook.save(out);					

		}catch(Exception ex){
			ex.printStackTrace();
			throw new Exception(ex.getMessage());
		}
	}

	private void TaoBaoCao(com.aspose.cells.Workbook workbook,IStockintransit obj,String query)throws Exception
	{

		try{		

			com.aspose.cells.Worksheets worksheets = workbook.getWorksheets();
			Worksheet worksheet = worksheets.getSheet(0);
			com.aspose.cells.Cells cells = worksheet.getCells();
			com.aspose.cells.Cell cell = cells.getCell("A1");;	

			cells.setRowHeight(0, 20.0f);
			ReportAPI.getCellStyle(cell, Color.RED, true, 16, "B??o c??o XNT");
			cell = cells.getCell("A2");
			ReportAPI.getCellStyle(cell, Color.NAVY, true, 10, "Ng??y t???o : " + Utility.getNgayHienTai() );


			ResultSet rs = obj.getDb().get(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int socottrongSql = rsmd.getColumnCount();

			int location  = 0;
			int row = 10;
			for( int i =1 ; i <=socottrongSql ; i ++  )
			{
				cell = cells.getCell(row, location + i-1 );
				cell.setValue(rsmd.getColumnName(i).replace("_isNum","").replace("_isNum2","")  );
				ReportAPI.setCellBackground(cell, new Color(219,229,241), BorderLineType.THIN, true, 0);
			}


			row ++;
			while(rs.next())
			{
				for(int i =1;i <=socottrongSql ; i ++)
				{					
					cell = cells.getCell(row,location + i-1 );

					if(rsmd.getColumnName(i).contains("_isNum") )
					{
						int format = 37;
						if(rsmd.getColumnName(i).contains("_isNum2"))	
							format = 10;
						cell.setValue(rs.getDouble(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, format);
					}
					else
					{
						cell.setValue(rs.getString(i));
						ReportAPI.setCellBackground(cell, Color.WHITE, BorderLineType.THIN, false, 0);
					}
				}

				++row;
			}



			if(rs!=null)rs.close();		

		}catch(Exception ex){

			ex.printStackTrace();
			throw new Exception("L???i ! Kh??ng c?? d??? li???u ????? xu???t file !");
		}	
	}


}
