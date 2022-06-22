package geso.dms.erp.servlets.lenhsanxuat;

import geso.dms.center.util.Utility;
import geso.dms.erp.beans.danhmucvattu.IDanhmucvattu_SP;
import geso.dms.erp.beans.danhmucvattu.imp.Danhmucvattu_SP;
import geso.dms.erp.beans.lenhsanxuat.IErpLenhsanxuat;
import geso.dms.erp.beans.lenhsanxuat.IErpLenhsanxuatList;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpLenhsanxuat;
import geso.dms.erp.beans.lenhsanxuat.imp.ErpLenhsanxuatList;
import geso.dms.erp.beans.nhapkho.IErpNhapkho;
import geso.dms.erp.beans.nhapkho.imp.ErpNhapkho;
import geso.dms.erp.db.sql.dbutils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ErpLenhsanxuatUpdateSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	PrintWriter out;
    public ErpLenhsanxuatUpdateSvl() 
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen");  	
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SalesUp/index.jsp");
		}
		else
		{
			session.setMaxInactiveInterval(30000);
		
			this.out = response.getWriter();
			Utility util = new Utility();
			
	    	String querystring = request.getQueryString();
		    userId = util.getUserId(querystring);
		    
		    if (userId.length()==0)
		    	userId = util.antiSQLInspection(request.getParameter("userId")); 	
		    String id = util.getId(querystring);  	
		    IErpLenhsanxuat lsxBean = new ErpLenhsanxuat(id);
		    lsxBean.setCongtyId((String)session.getAttribute("congtyId"));
		    
		    lsxBean.setUserId(userId); //phai co UserId truoc khi Init
	        String nextJSP;
	        if(request.getQueryString().indexOf("display") >= 0 ) 
	        {
	        	lsxBean.initDisplay();
	        	nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatDisplay.jsp";
	        }
	        else
	        {
	        	lsxBean.init();
	        	nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatUpdate.jsp";
	        }
	        
	        session.setAttribute("lsxBean", lsxBean);
	        session.setAttribute("tensudung", "2");
	        
	        response.sendRedirect(nextJSP);
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		dbutils db= new dbutils();
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");  
		String userTen = (String) session.getAttribute("userTen"); 
		
		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if(!cutil.check(userId, userTen, sum)){
			response.sendRedirect("/SalesUp/index.jsp");
		}
		else
		{
			request.setCharacterEncoding("UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			session.setMaxInactiveInterval(30000);
			
			this.out = response.getWriter();
			IErpLenhsanxuat lsxBean;
			
			Utility util = new Utility();	
			String id = util.antiSQLInspection(request.getParameter("id"));
		    if(id == null)
		    {  	
		    	lsxBean = new ErpLenhsanxuat("");
		    }
		    else
		    {
		    	lsxBean = new ErpLenhsanxuat(id);
		    }
	
		    lsxBean.setCongtyId((String)session.getAttribute("congtyId"));
		    lsxBean.setUserId(userId);
		    
		    String PO = util.antiSQLInspection(request.getParameter("PO"));
		    if(PO == null)
		    	PO = "";
		    lsxBean.setPlaintOrder(PO);
		    
		    String trangthai = util.antiSQLInspection(request.getParameter("trangthai"));
		    if(trangthai == null)
		    	trangthai = "";
		    lsxBean.setTrangthai(trangthai);
		    
		    String KhoId = util.antiSQLInspection(request.getParameter("KhoId"));
		    if(KhoId == null)
		    	KhoId = "";
		    lsxBean.setKhoId(KhoId);
		    
		    System.out.println("Kho Nek : "+KhoId);
		    
		    
		    String ngaysanxuat = util.antiSQLInspection(request.getParameter("ngaybatdau"));
		    if( ngaysanxuat == null )
		    	ngaysanxuat = getDateTime();
		    lsxBean.setNgaytao(ngaysanxuat);
		    
		    String ngaydukien = util.antiSQLInspection(request.getParameter("ngayketthuc"));
			if (ngaydukien == null)
				ngaydukien = "";				
			lsxBean.setNgaydukien(ngaydukien);
	    	
	    	String spIds = util.antiSQLInspection(request.getParameter("spIds"));
			if (spIds == null)
				spIds = "";				
			lsxBean.setSpId(spIds);
	    	
	    	String kbsxIds = util.antiSQLInspection(request.getParameter("kbsxIds"));
			if (kbsxIds == null)
				kbsxIds = "";				
			lsxBean.setKbsxId(kbsxIds);
	    	
	    	String nhamayIds = util.antiSQLInspection(request.getParameter("nhamayIds"));
			if (nhamayIds == null)
				nhamayIds = "";				
			lsxBean.setNhamayId(nhamayIds);
	    	
	    	String soluongsx = util.antiSQLInspection(request.getParameter("soluongsx"));
			if (soluongsx == null)
				soluongsx = "";				
			lsxBean.setSoluong(soluongsx);
	    	
			String soluongchuan = util.antiSQLInspection(request.getParameter("soluongchuan"));
			if (soluongchuan == null)
				soluongchuan = "";
			lsxBean.setSoluongchuan(soluongchuan);
			
			String chophepTT = util.antiSQLInspection(request.getParameter("chophepTT"));
			if (chophepTT == null)
				chophepTT = "0";
			lsxBean.setChophepTT(chophepTT);
			

			String Bomid = util.antiSQLInspection(request.getParameter("Bomid"));
			if (Bomid == null)
				Bomid = "0";
			lsxBean.setBomId(Bomid);
			
			
			String viewBom = util.antiSQLInspection(request.getParameter("viewBom"));
			if (viewBom == null)
				viewBom = "0";
			lsxBean.setViewBom(viewBom);
			
			session.setAttribute("tensudung", "2");

			String[] mavt = request.getParameterValues("mavattu");
			String[] tenvt = request.getParameterValues("tenvattu");
			String[] soluong = request.getParameterValues("soluong");
			String[] donvitinh = request.getParameterValues("donvitinh");
			String[] mavtTT = request.getParameterValues("mavattuTT");
			String[] tenvtTT = request.getParameterValues("tenvattuTT"); 
			String[] dvtTT = request.getParameterValues("dvtTT");
			String[] tile = request.getParameterValues("tyle");
			String[] soluongTT = request.getParameterValues("soluongTT");
			
			List<IDanhmucvattu_SP> spList = new ArrayList<IDanhmucvattu_SP>();
			if(tenvt != null)
			{	
				for(int m = 0; m < tenvt.length; m++)
				{	
					if(tenvt[m] != "")
					{	
						if(soluong[m].trim().length() >  0) //chi them nhung san pham co so luong > 0
						{	
							IDanhmucvattu_SP sanpham = null;
							sanpham = new Danhmucvattu_SP();
							
							sanpham.setMaVatTu(mavt[m]);
							sanpham.setTenVatTu(tenvt[m]);
							sanpham.setSoLuong(soluong[m]);
							sanpham.setDvtVT(donvitinh[m]);
							
							sanpham.setMaVatTuThayThe(mavtTT[m]);
							sanpham.setTenVatTuThayThe(tenvtTT[m]);
							sanpham.setDvtThayThe(dvtTT[m]);
							sanpham.setTile(tile[m]);
							sanpham.setSoluongTT(soluongTT[m]);
							
							spList.add(sanpham);												
						}
					}				
				}
				
				lsxBean.setListDanhMuc(spList);	
			}	
			
			
		    String action = request.getParameter("action");
		    
			if(action.equals("save"))
			{	
				if(id == null)
				{
					if(!lsxBean.createLsx())
					{
						lsxBean.createRs();
	    		    	session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatNew.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpLenhsanxuatList obj = new ErpLenhsanxuatList();
						obj.setCongtyId((String)session.getAttribute("congtyId"));
						obj.setUserId(userId);
						obj.init("");  
						lsxBean.DBclose();
				    	session.setAttribute("obj", obj);  	
			    		session.setAttribute("userId", userId);
				
			    		response.sendRedirect("/SalesUp/pages/Erp/ErpLenhSanXuat.jsp");
					}
				}
				else
				{
					if(!lsxBean.updateLsx())
					{
						lsxBean.createRs();
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatUpdate.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
						IErpLenhsanxuatList obj = new ErpLenhsanxuatList();
						obj.setCongtyId((String)session.getAttribute("congtyId"));
					    obj.setUserId(userId);
					    obj.init("");
						lsxBean.DBclose();
						session.setAttribute("obj", obj);							
						String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuat.jsp";
						response.sendRedirect(nextJSP);
					}
				}
			}
			else
			{
				if(action.equals("tieuhao"))
				{
					
					System.out.println("Vào tieu hao đây nek : ");
					if( ! lsxBean.checkTieuHaoLsx() )
					{
						session.setAttribute("lsxBean", lsxBean);
	    				String nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatTieuHao.jsp";
	    				response.sendRedirect(nextJSP);
					}
					else
					{
				        session.setAttribute("lsxBean", lsxBean);
				        response.sendRedirect("/SalesUp/pages/Erp/ErpLenhSanXuatTieuHao.jsp");
					}
				}
				else
				{
					if(action.equals("nhapkho"))
					{	
						
						System.out.println("NHAP KHO -----");
						IErpNhapkho nkBean = new ErpNhapkho();
						String congty = (String)session.getAttribute("congtyId");
						System.out.println("CONGTY ID: " + congty);
						nkBean.setCongtyId(congty);						
						nkBean.setNgaynhapkho(ngaydukien);
						System.out.println("ngay du kien: " + ngaydukien);
						nkBean.setNdnId("100011");
						nkBean.setSoLenhsx(id);
						System.out.println("SOLENHSANXUAT:" + id);
						
						// load cột ghi chú của erp_khonhap khi vào " nhập kho từ lệnh sản xuất "
						ResultSet rs= db.get(" select ghichu from erp_nhapkho where solenhsanxuat='"+id+"'");						
						if(rs!=null)
						{
						try
						
						{
						while(rs.next())	
						{							
							nkBean.setGhichu( rs.getString("ghichu")==null?"":rs.getString("ghichu"));							
						}
						rs.close();
						}
						catch (SQLException e)
						{
							
						}
							
						};
						nkBean.checkLSX();
						
						
						session.setAttribute("nkBean", nkBean);
						
						

						String nextJSP = "/SalesUp/pages/Erp/ErpNhapKhoLenhSanXuat.jsp";
						response.sendRedirect(nextJSP);
					}
					else
					{
						if(action.equals("changeBOM"))
						{
							lsxBean.createRs();
							
							lsxBean.changeKbsx();
						}
						else
						{
							lsxBean.createRs();
						}
						
						String task = request.getParameter("actionTask");
						if(task == null)
							task = "";
						System.out.println("action task: " + task);
						session.setAttribute("lsxBean", lsxBean);
						String nextJSP = "";
						
						if(task.trim().length() <= 0)
						{
							nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatNew.jsp";
						
							if(id != null)
								nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatUpdate.jsp";
						}
						else
						{
							nextJSP = "/SalesUp/pages/Erp/ErpLenhSanXuatDisplay.jsp";
						}
						
						response.sendRedirect(nextJSP);
					}
				}
			}
		}
	}
	
	private String getDateTime() 
	{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}

}
