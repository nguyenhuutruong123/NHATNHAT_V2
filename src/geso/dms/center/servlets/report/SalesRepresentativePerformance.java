package geso.dms.center.servlet.report;

import geso.dms.center.bean.report.ICenterReport;


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

/**
 * Servlet implementation class SalesRepresentativePerformance
 */
public class SalesRepresentativePerformance extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	private String query = "";	
	ICenterReport obj;
	int length = 0;  
	
    public String getQuery() {
		return query;
	}


	public void setQuery(HttpServletRequest request) {
		obj = new ICenterReport();
        obj.setFromDate(request.getParameter("Sdays"));
        obj.setToDate(request.getParameter("Edays"));
        obj.setChanel(request.getParameter("kenhs"));
        obj.setRegion(request.getParameter("vungs"));
        obj.setErea(request.getParameter("khuvucs"));
        obj.setDistributor(request.getParameter("npps"));
        obj.setUnit(request.getParameter("units"));
        obj.setGroupfocusSKU(request.getParameter("groups"));
        obj.setSalesSup(request.getParameter("giamsbhs"));
        obj.setSalesRep(request.getParameter("ddkds"));
        obj.setRoute(request.getParameter("tuyens"));
        
        obj.setFieldShow(request.getParameterValues("fieldsHien"));
		obj.setFieldHidden(request.getParameterValues("fieldsAn"));
		if (obj.getFieldHidden() == null) {
			if (obj.getFieldShow() != null) {
				length = obj.getFieldShow().length;
			} else {
				length = 0;
			}
		} else {
			length = obj.getFieldShow().length + obj.getFieldHidden().length;
		}                      
        
        obj.setUserName((String)request.getSession().getAttribute("userTen"));
        obj.setUserID((String)request.getSession().getAttribute("userId"));  
        obj.setTitle("Sales Representative Performance");
        
        query = "";
                
	}
    public SalesRepresentativePerformance() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");			
			response.setContentType("application/vnd.ms-excel");
	        response.setHeader("Content-Disposition", "attachment; filename=SalesRepresentativePerformance(tt).xls");
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
	    
	    for(int i=0;i<length;++i){
	    	cells.setColumnWidth(i, 15.0f);	    	
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
			cell = cells.getCell("AN" + String.valueOf(index));		cell.setValue(j);
			index ++;			
	    }	
	    ReportAPI.setHidden(workbook,length);
		PivotTables pivotTables = worksheet.getPivotTables();
		String pos = Integer.toString(index-1);	
	    int j = pivotTables.add("=SalesRepresentativePerformance!AA1:AN" + pos,"B5","PivotTable");
	    PivotTable pivotTable = pivotTables.get(j);
	    pivotTable.setRowGrand(true);
	    pivotTable.setColumnGrand(true);
	    pivotTable.setAutoFormat(true);    
	    
	    for(int i=0;i<obj.getFieldShow().length;++i){
	    	pivotTable.addFieldToArea(PivotFieldType.ROW, i); 		
	    	pivotTable.getRowFields().get(i).setAutoSubtotals(false);
	    }
		
	}
	
	private void CreateStaticHeader(Workbook workbook, String Distributor) {
		Worksheets worksheets = workbook.getWorksheets();
	    Worksheet worksheet = worksheets.getSheet(0);
	    worksheet.setName("SalesRepresentativePerformance");
	    
	    Cells cells = worksheet.getCells();	    
	    cells.setRowHeight(1, 13);    
	    Cell cell = cells.getCell("B1");	
	    ReportAPI.getCellStyle(cell,Color.RED, true, 16, obj.getTitle());
	    cell = cells.getCell("B2");
	    ReportAPI.getCellStyle(cell,Color.BLUE,true,10,"Form : " + obj.getFromDate() + 
	    												"  To: " + obj.getToDate());
	    cell = cells.getCell("B3");
	    ReportAPI.getCellStyle(cell,Color.BLUE,true,10,"Date create:  " + obj.getCreateDate());
	    
	    cell = cells.getCell("B4");
	    ReportAPI.getCellStyle(cell,Color.BLUE,true,10,"User:  " + obj.getUserName());
	    
		cell = cells.getCell("AA1");		cell.setValue("Channel");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AB1");		cell.setValue("Region");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AC1");		cell.setValue("Area");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AD1");		cell.setValue("Sales Sup");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AE1");		cell.setValue("Customer");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AF1");		cell.setValue("Distributor");		ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AG1");		cell.setValue("Sales Rep");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AH1");		cell.setValue("SKU");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AI1");		cell.setValue("Monthly Avg second sales");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AJ1");		cell.setValue("Province");					ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AK1");		cell.setValue("Distributor code");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AL1");		cell.setValue("Sku code");			ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AM1");		cell.setValue("Route");				ReportAPI.setCellHeader(cell);
		cell = cells.getCell("AN1");		cell.setValue("Address");			ReportAPI.setCellHeader(cell);
	}


}
