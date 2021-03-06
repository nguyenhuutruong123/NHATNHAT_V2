package geso.dms.center.servlet.report;




import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.aspose.cells.Cell;
import com.aspose.cells.Cells;
import com.aspose.cells.Color;
import com.aspose.cells.PivotFieldType;
import com.aspose.cells.PivotTable;
import com.aspose.cells.PivotTables;
import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.aspose.cells.Worksheets;


public class OpportunitiesGrowthOrdersOnRoute extends HttpServlet {
	private static final long serialVersionUID = 1L;	
	String query = "";
	String userName = "";
	String[] Fields;
	String[] FieldShow;
	String[] FieldHidden;
	int FieldLength = 0;
		

	private String getQuery(){
		return query;
	}
	private void setQuery(HttpServletRequest request){
		//Get value from Client submit
        String month = request.getParameter("months");
        String year = request.getParameter("years");
        
        String fromDate = year + "-" + month + "-" + request.getParameter("Sdays");
        String toDate = year + "-" + month + "-" + request.getParameter("Edays");
        
        String kenhBH = request.getParameter("kenhs");
        String vungMien = request.getParameter("vungs");
        String gsbh = request.getParameter("giamsbhs");                
        String ddkd = request.getParameter("ddkds");
        String tuyenBH = request.getParameter("tuyens");        
        String userName = (String)request.getSession().getAttribute("userTen");
        String userId  = (String)request.getSession().getAttribute("userId"); 
        String npp = request.getParameter("npps");
            
         
        query = ""
        + "select dh.nguoisua as nguoixoa, dh.ngaysua as ngayxoa," 					
        + "		  kh.pk_seq as makh, kh.ten as tenkh, dh.pk_seq as dhId, dh.ngaynhap as ngayhoadon," 					
        +"		  sp.ma as masp, sp.ten as tensanpham, dh_sp.soluong as soluong, dh_sp.giamua as dongia,"					
        +"		  dh_sp.chietkhau as chietkhau, dh_sp.soluong*dh_sp.giamua - dh_sp.chietkhau  as tongtien, '' as scheme"					
        +"from    donhang dh"					
        +"		  inner join khachhang kh on kh.pk_seq = dh.khachhang_fk"					
        +"         inner join donhang_sanpham dh_sp on dh_sp.donhang_fk= dh.pk_seq"					
        +"         inner join sanpham sp on sp.pk_seq = dh_sp.sanpham_fk"	
        +"where    dh.ngaynhap >='" + fromDate  +"' and dh.ngaynhap <='" + toDate +"' and dh.trangthai = 2 and dh.npp_fk='" + npp +"'"					
        +"         union"					
        +"         select dh.nguoisua as nguoixoa, dh.ngaysua as ngayxoa," 					
        +"                kh.pk_seq as makh, kh.ten as tenkh, dh.pk_seq as dhId, dh.ngaynhap as ngayhoadon," 					
        +"                spkm.spma as masp, sp.ten as tensp, spkm.soluong as soluong, 0 as dongia, 0 as chietkhau, 0 as tongtien, ctkm.scheme as scheme"					
        +"         from  donhang_ctkm_trakm spkm" 					
        +"               inner join sanpham sp on spkm.spma=sp.ma"					
        +"               inner join ctkhuyenmai ctkm on ctkm.pk_seq = ctkmid"					
        +"               inner join donhang dh on dh.pk_seq=donhangid"			
        +"               inner join khachhang kh on kh.pk_seq = dh.khachhang_fk"					
        +"               inner join donhang_sanpham dh_sp on dh_sp.donhang_fk= dh.pk_seq"					
        +"         where dh.ngaynhap >='" + fromDate +"' and dh.ngaynhap <='" + toDate +"' and dh.trangthai = 2  and dh.npp_fk='" + npp + "'"					
        +"         order by dhId, scheme";
        
	}
    
    public OpportunitiesGrowthOrdersOnRoute() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");			
			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename=OpportunitiesGrowthOrdersOnRoute(tt).xls");
	        OutputStream out = response.getOutputStream();
	        
	        
	        setQuery(request);        //Thi???t l???p chu???i truy v???n v??o CSDL l???y tham s??? t??? Client
	        CreatePivotTable(out, response, request);	// Create PivotTable
	        
	        
		}catch(Exception ex){
			request.getSession().setAttribute("errors", ex.getMessage());
		}
	}
	
	private void CreatePivotTable(OutputStream out,HttpServletResponse response,HttpServletRequest request) throws Exception{
		try{
			Workbook workbook = new Workbook();			
			String Distributor = "";
			
			FieldShow = request.getParameterValues("fieldsHien");
			FieldHidden = request.getParameterValues("fieldsAn");	
			if(FieldHidden==null){
				if(FieldShow!=null){
					FieldLength = FieldShow.length;
				}
				else{
					FieldLength = 0;
				}				 
			}
			else
			{
				FieldLength = FieldShow.length + FieldHidden.length;
			}
			
			CreateStaticHeader(workbook,Distributor);	//T???o Header cho trang b??o c??o			
			CreateStaticData(workbook);			//Thi???t l???p in xu???t d??? li???u
			workbook.save(out,0);
			
		}catch(Exception ex){
			throw new Exception(ex.getMessage());
		}		
	}
	private void CreateStaticData(Workbook workbook) throws Exception{
		
        Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    Cells cells = worksheet.getCells();	
	    
	    
	    //Thiet lap dai-rong khi in du lieu vao cot.
	    for(int i=0;i<FieldLength;++i){
	    	cells.setColumnWidth(i, 15.0f);
	    	cells.setRowHeight(i+5, 14.0f);
	    }	    
	    
	    int index = 2;
	    Cell cell = null;
	    for (int j=0;j<10;++j){	    	
	    	cell = cells.getCell("AA" + String.valueOf(index));		cell.setValue(j);				
			cell = cells.getCell("AB" + String.valueOf(index));		cell.setValue(j);	
			cell = cells.getCell("AC" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AD" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AE" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AF" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AG" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AH" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AI" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AJ" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AK" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AL" + String.valueOf(index));		cell.setValue(j);
			cell = cells.getCell("AM" + String.valueOf(index));		cell.setValue(j);	    	 
			index ++;			
	    }		
	    ReportAPI.setHidden(workbook,FieldLength);
		PivotTables pivotTables = worksheet.getPivotTables();
		String pos = Integer.toString(index-1);	
	    int j = pivotTables.add("=AA1:AM" + pos,"B5","PivotTableDemo");
	    PivotTable pivotTable = pivotTables.get(j);
	    pivotTable.setRowGrand(true);
	    pivotTable.setColumnGrand(true);
	    pivotTable.setAutoFormat(true);    
	    
	    for(int i=0;i<FieldShow.length;++i){
	    	if(i>3){
	    		pivotTable.addFieldToArea(PivotFieldType.DATA,i);	    		
	    	}
	    	else
	    	{
		    	pivotTable.addFieldToArea(PivotFieldType.ROW, i); 		
		    	pivotTable.getRowFields().get(i).setAutoSubtotals(false);
	    	}
	    }
	}
	private void CreateStaticHeader(Workbook workbook, String Distributor) {
		
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    worksheet.setName("Sales Opp on Route (Orders)tt.");
	    
	    Cells cells = worksheet.getCells();	    
	    cells.setRowHeight(0, 50.0f);	    
	    Cell cell = cells.getCell("B1");	
	    ReportAPI.getCellStyle(cell,Color.RED, true, 16, "Opportunities Growth Orders on Route");
	    cell = cells.getCell("B2");
	    ReportAPI.getCellStyle(cell,Color.BLUE,true,10,"Date Create: " + ReportAPI.NOW("dd/MM/yyyy : hh-mm-ss"));
	    cell = cells.getCell("B3");
	    ReportAPI.getCellStyle(cell,Color.BLUE,true,10,"Distributor: " + Distributor);
	    
	    
		cell = cells.getCell("AA1");		cell.setValue("Channel");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AB1");		cell.setValue("Region");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AC1");		cell.setValue("Area");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AD1");		cell.setValue("Distributor");		ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AE1");		cell.setValue("Sales Rep");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AF1");		cell.setValue("Customer");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AG1");		cell.setValue("Route");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AH1");		cell.setValue("Monthly Avg.Order");	ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AI1");		cell.setValue("MTD");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AJ1");		cell.setValue("%");					ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AK1");		cell.setValue("Province");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AL1");		cell.setValue("Distributor code");	ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AM1");		cell.setValue("Address");			ReportAPI.setCellHeader(cell);	
		
	}
	
}
