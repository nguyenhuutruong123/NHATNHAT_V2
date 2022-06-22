package geso.dms.center.servlets.daidienkinhdoanh;

import geso.dms.center.beans.daidienkinhdoanh.imp.*;
import geso.dms.center.beans.daidienkinhdoanh.*;
import geso.dms.center.util.Utility;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class DaidienkinhdoanhUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	PrintWriter out;  
    
    public DaidienkinhdoanhUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
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
		IDaidienkinhdoanh ddkdBean;
		this.out = response.getWriter();
		Utility util = new Utility();
		
    	String querystring = request.getQueryString();
	    String userId = util.getUserId(querystring);
	    
	    out.println(userId);
	    
	    if (userId.length()==0)
	    	userId = util.antiSQLInspection(request.getParameter("userId"));
	    String isPG = util.antiSQLInspection(request.getParameter("isPG"));
	    if(isPG == null) isPG = "0";
	    
	    
	    String id = util.getId(querystring);  	

	    ddkdBean = new Daidienkinhdoanh(id);
        ddkdBean.setUserId(userId);
        ddkdBean.setIsPG(isPG);
        session.setAttribute("ddkdBean", ddkdBean);
        
        String update = util.antiSQLInspection(request.getParameter("update"));
	       if(update==null)
	    	   update="";


	       String display = util.antiSQLInspection(request.getParameter("display"));
	       if(display==null)
	    	   display="";
	     
	       String nextJSP = "";
	       if(update.trim().length()>0)
	    	   nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhUpdate.jsp";
	       if(display.trim().length()>0)
	    	   nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhDisplay.jsp";
		
        
             response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		IDaidienkinhdoanh ddkdBean;
		this.out = response.getWriter();
		Utility util = new Utility();
		
	    String id = util.antiSQLInspection(request.getParameter("id"));
	    if(id == null){  	
	    	ddkdBean = new Daidienkinhdoanh("");
	    }else{
	    	ddkdBean = new Daidienkinhdoanh(id);
	    }
	    
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		ddkdBean.setUserId(userId);
		String isPG = util.antiSQLInspection(request.getParameter("isPG"));
	    if(isPG == null) isPG = "0";
	    ddkdBean.setIsPG(isPG);
		
		String diabanId = util.antiSQLInspection(request.getParameter("diabanId"));
		if (diabanId == null)
			diabanId = "";
		ddkdBean.setDiabanId(diabanId);
		
		
		String kbhId = util.antiSQLInspection(request.getParameter("kbhId"));
		if (kbhId == null)
			kbhId = "";
		ddkdBean.setKenhbanhang(kbhId);
		
    	String ddkdTen = util.antiSQLInspection(request.getParameter("ddkdTen"));
		if (ddkdTen == null)
			ddkdTen = "";				
    	ddkdBean.setTen(ddkdTen);
    	
    	String trangthai = util.antiSQLInspection(request.getParameter("TrangThai"));
    	if (trangthai == null)
    		trangthai = "0";
    	else
    		trangthai = "1";
    	ddkdBean.setTrangthai(trangthai);
    	
    	String diachi = util.antiSQLInspection(request.getParameter("DiaChi"));
		if (diachi == null)
			diachi = "";
		ddkdBean.setDiachi(diachi);
				
		String dienthoai = util.antiSQLInspection(request.getParameter("DienThoai"));
		if (dienthoai == null)
			dienthoai = "";
		ddkdBean.setSodt(dienthoai);
		
		String[] nppIds = request.getParameterValues("nppId");
		String nppId = "";
		if (nppIds != null)
		{
			for(int i = 0; i < nppIds.length; i++)
				nppId += nppIds[i] + ",";
			
			if(nppId.trim().length() > 0)
				nppId = nppId.substring(0, nppId.length() - 1);
		}
		ddkdBean.setNppId(nppId);
		System.out.println("---NHA PP: " + nppId );
		
		String DDKDTIENNHIEM = util.antiSQLInspection(request.getParameter("ddkdtiennhiem"));
		if (DDKDTIENNHIEM == null)
			DDKDTIENNHIEM = "";
		ddkdBean.setDDKDTn(DDKDTIENNHIEM);
		
		String phantramchuyen = util.antiSQLInspection(request.getParameter("phantramchuyen"));
		if (phantramchuyen == null)
			phantramchuyen = "";
		ddkdBean.setPhantramChuyen(phantramchuyen);
		
		String macty = util.antiSQLInspection(request.getParameter("maCty"));
		if(macty==null)
			macty="";
		ddkdBean.setMaCongTy(macty);
		
		String vitri = util.antiSQLInspection(request.getParameter("vitri"));
		if(vitri==null)
			vitri="";
		ddkdBean.setViTri(vitri);
		
		String khuvuctt = util.antiSQLInspection(request.getParameter("khuvuctt"));
		if(khuvuctt==null)
			khuvuctt="";
		ddkdBean.setKhuVucTT(khuvuctt);
		
		String sonamlamviec = util.antiSQLInspection(request.getParameter("sonamlamviec"));
		if(sonamlamviec==null)
			sonamlamviec="";
		ddkdBean.setSoNamLamViec(sonamlamviec);
		
		String sodtcongty = util.antiSQLInspection(request.getParameter("soDTcty"));
		if(sodtcongty==null)
			sodtcongty="";
		ddkdBean.setSoDTCty(sodtcongty);
		
		String ngayvaocongty = util.antiSQLInspection(request.getParameter("ngayCty"));
		if(ngayvaocongty==null)
			ngayvaocongty="";
		ddkdBean.setNgayVaoCty(ngayvaocongty);
		
		String hdld = util.antiSQLInspection(request.getParameter("HDLD"));
		if(hdld==null)
			hdld="";
		ddkdBean.setHDLD(hdld);
		
		String loaihd = util.antiSQLInspection(request.getParameter("loaiHD"));
		if(loaihd==null)
			loaihd="";
		ddkdBean.setLoaiHD(loaihd);
		
		String ngaykyhd = util.antiSQLInspection(request.getParameter("ngaykyHD"));
		if(ngaykyhd==null)
			ngaykyhd="";
		ddkdBean.setNgayKyHD(ngaykyhd);
		
		String ngayketthuchd = util.antiSQLInspection(request.getParameter("ngayketthucHD"));
		if(ngayketthuchd==null)
			ngayketthuchd="";
		ddkdBean.setNgayKetThucHD(ngayketthuchd);
		
		String teamleader = util.antiSQLInspection(request.getParameter("teamleader"));
		if(teamleader==null)
			teamleader="";
		ddkdBean.setTeamLeader(teamleader);
		
		String sotaikhoan = util.antiSQLInspection(request.getParameter("sotaikhoan"));
		if(sotaikhoan==null)
			sotaikhoan="";
		ddkdBean.setSoTaiKhoan(sotaikhoan);
		
		String email = util.antiSQLInspection(request.getParameter("email"));
		if(email==null)
			email="";
		ddkdBean.setEmail(email);
	    
	    String nganhang = util.antiSQLInspection(request.getParameter("NganHang"));	    
	    if(nganhang == null) nganhang = "";
	    ddkdBean.setNganHang(nganhang);
	    
	    String chinhanh = util.antiSQLInspection(request.getParameter("ChiNhanh"));	    
	    if(chinhanh == null) chinhanh = "";
	    ddkdBean.setChiNhanh(chinhanh);
	    
	    String manhanvien = util.antiSQLInspection(request.getParameter("MaNhanVien"));	    
	    if(manhanvien == null) manhanvien = "";
	    ddkdBean.setMaNhanVien(manhanvien);
	    
	    String mathuviec = util.antiSQLInspection(request.getParameter("MaThuViec"));	    
	    if(mathuviec == null) mathuviec = "";
	    ddkdBean.setMaThuViec(mathuviec);
	    
	    String maFAST = util.antiSQLInspection(request.getParameter("maFAST"));	    
	    if(maFAST == null) maFAST = "";
	    ddkdBean.setMaFAST(maFAST);
	    
	    
	    String matkhau = util.antiSQLInspection(request.getParameter("matkhau"));
	    if(matkhau == null) matkhau = "";
	    
	    String maERP = util.antiSQLInspection(request.getParameter("maERP"));	    
	    if(maERP == null) maERP = "";
	    ddkdBean.setMaERP(maERP);
	    
		String ghichu = util.antiSQLInspection(request.getParameter("ghichu"));
		if(ghichu==null)
			ghichu="";
		ddkdBean.setGhiChu(ghichu);
		
		String gsbhId = util.antiSQLInspection(request.getParameter("gsbhId"));
		if(gsbhId == null)
			gsbhId = "";
		ddkdBean.setGsbanhang(gsbhId);
		
		String isNVTT = util.antiSQLInspection(request.getParameter("isNVTT"));
		if(isNVTT == null)
			isNVTT = "0";
		ddkdBean.setIsNVTT(isNVTT);
		
		String ngaysua = getDateTime();
    	ddkdBean.setNgaysua(ngaysua);
		
		String nguoisua = userId;
		ddkdBean.setNguoisua(nguoisua);
		boolean error = false;
				
		if (ddkdTen.trim().length()== 0){
			ddkdBean.setMessage("Vui lòng nhập tên đại diện kinh doanh.");
			error = true;
		}
		
		if (maFAST.trim().length()== 0){
			ddkdBean.setMessage("Vui lòng nhập mã DMS.");
			error = true;
		}

		
		if (diachi.trim().length()== 0){
			ddkdBean.setMessage("Vui lòng nhập địa chỉ.");
			error = true;
		}
		
		if (dienthoai.trim().length()== 0){
			ddkdBean.setMessage("Vui lòng nhập số điện thoại.");
			error = true;			
		}
		
		if (nppId.trim().length()== 0){
			ddkdBean.setMessage("Vui lòng chọn thông tin trực thuộc.");
			error = true;			
		}
		
		String action = request.getParameter("action");
		if (action.equals("save") && id==null && matkhau.trim().length() == 0) { //Tạo mới -> cần nhập mật khẩu khởi tạo
			ddkdBean.setMessage("Vui lòng nhập mật khẩu khởi tạo.");
			error = true;
		} else {
			ddkdBean.setMatkhau(matkhau);
		}
		
		String cothechonTN = request.getParameter("cothechonTN");
		if(cothechonTN == null)
			cothechonTN = "0";
		ddkdBean.setCothechonTN(cothechonTN);
 		
 		String nextJSP = "";
	    if (!error)
	    {
	    	System.out.println("vao day rui");
	    	if(action.equals("save"))
	    	{
	    		System.out.println("vao day save");
	    		IDaidienkinhdoanhList obj = new DaidienkinhdoanhList();
	    		obj.setItems(10);
			    obj.setCrrSplitting(15);
			    obj.setNxtApprSplitting(1);
	    		if (id == null)
	    		{
	    			System.out.println("vao day id null");
	    			if (!(ddkdBean.CreateDdkd()))
	    			{				
	    				session.setAttribute("ddkdBean", ddkdBean);
	    				ddkdBean.setUserId(userId);
	    				nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhNew.jsp";
	    				response.sendRedirect(nextJSP);
	    			}
	    			else
	    			{
	    				//IDaidienkinhdoanhList obj = new DaidienkinhdoanhList();
	    				obj.setIsPG(isPG);
	    				obj.setUserId(userId);
	    				obj.init("");	
	    				
	    				session.setAttribute("obj", obj);
						
	    				nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanh.jsp";
	    				response.sendRedirect(nextJSP);			    			    									
	    			}
	    		}
	    		else{
	    			System.out.println("vao day update");
	    			if (!(ddkdBean.UpdateDdkd()))
	    			{			
	    				session.setAttribute("ddkdBean", ddkdBean);
	    				nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhUpdate.jsp";
	    				response.sendRedirect(nextJSP);
	    			}
	    			else
	    			{
	    				//IDaidienkinhdoanhList obj = new DaidienkinhdoanhList();
	    				obj.setIsPG(isPG);
	    				obj.setUserId(userId);
	    				obj.init("");	
	    				
	    				session.setAttribute("obj", obj);
						
	    				nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanh.jsp";
	    				response.sendRedirect(nextJSP);			    			    									
	    			}
	    		}
	    	}
	    	else
	    	{
	    		System.out.println("vao day ");
	    		ddkdBean.createRS();
	    		ddkdBean.setUserId(userId);
	    		session.setAttribute("ddkdBean", ddkdBean);
			
	    		//String nextJSP;
	    		if (id == null){			
	    			nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhNew.jsp";
	    		}else{
	    			nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhUpdate.jsp";   						
	    		}
	    		response.sendRedirect(nextJSP);
	    	}
	    }
	    else
	    {
	    	System.out.println("nppid la "+ddkdBean.getNppId());
			ddkdBean.createRS();
			
    		ddkdBean.setUserId(userId);
    		session.setAttribute("ddkdBean", ddkdBean);
		
    		//String nextJSP;
    		if (id == null){			
    			nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhNew.jsp";
    		}else{
    			nextJSP = request.getContextPath() + "/pages/Center/DaiDienKinhDoanhUpdate.jsp";   						
    		}
    		response.sendRedirect(nextJSP);	
	    }
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}
