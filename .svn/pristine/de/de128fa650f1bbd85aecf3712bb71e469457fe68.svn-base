package geso.dms.distributor.servlets.taikhoankt;

import geso.dms.center.util.Utility;
import geso.dms.distributor.beans.taikhoankt.ITaikhoankt;
import geso.dms.distributor.beans.taikhoankt.ITaikhoanktList;
import geso.dms.distributor.beans.taikhoankt.imp.Taikhoankt;
import geso.dms.distributor.beans.taikhoankt.imp.TaikhoanktList;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class TaikhoanketoanSvl extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	public TaikhoanketoanSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		Utility util = new Utility();
		
		ITaikhoanktList tkktList = new TaikhoanktList();
		
		String userId = util.antiSQLInspection(request.getParameter("userId"));
		tkktList.setUserId(userId);
				 
		String task = request.getParameter("task");
		
		if (task != null) {
			if (task.equals("xoa")) {
				String pk = request.getParameter("id");
				String msg = tkktList.Delete(pk);
				tkktList.setMsg(msg);
			}
		}
		
		tkktList.init("");
		session.setAttribute("tkktList", tkktList);
		String nextJSP = request.getContextPath() + "/pages/Distributor/TaiKhoanKt.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		String userTen = (String) session.getAttribute("userTen");

		String sum = (String) session.getAttribute("sum");
		geso.dms.center.util.Utility cutil = (geso.dms.center.util.Utility) session.getAttribute("util");
		if (!cutil.check(userId, userTen, sum)) 
		{
			response.sendRedirect("/TraphacoERP/index.jsp");
		}
		else 
		{
			ITaikhoanktList tkktList = new TaikhoanktList();
						
			String LoaiTaiKhoanId = cutil.antiSQLInspection(request.getParameter("LoaiTaiKhoanId"));
			String SoHieuTaiKhoan = cutil.antiSQLInspection(request.getParameter("SoHieuTaiKhoan"));
			String TenTaiKhoan = cutil.antiSQLInspection(request.getParameter("TenTaiKhoan"));
			String TtcpId = cutil.antiSQLInspection(request.getParameter("TtcpId"));
			String TrangThai = cutil.antiSQLInspection(request.getParameter("TrangThai"));
			System.out.println("TrangThai"+TrangThai);
			if (TrangThai != null)
				tkktList.setTrangThai(TrangThai);

			tkktList.setUserId(userId);

	    	String nppId = tkktList.getnppId(); 
	    	
			if (LoaiTaiKhoanId != null)
				tkktList.setLoaiTaiKhoanId(LoaiTaiKhoanId);

			if (SoHieuTaiKhoan != null)
				tkktList.setSoHieuTaiKhoan(SoHieuTaiKhoan);

			if (TenTaiKhoan != null)
				tkktList.setTenTaiKhoan(TenTaiKhoan);


			String action = request.getParameter("action");
			if (action.equals("new"))
			{
				ITaikhoankt tkktBean = new Taikhoankt();
				tkktBean.CreateRs();
				session.setAttribute("tkktBean", tkktBean);
				String nextJSP = "/TraphacoERP/pages/Erp/TaiKhoanKtNew.jsp";
				response.sendRedirect(nextJSP);
			} 
			else 
			{
				if(action.equals("view") || action.equals("next") || action.equals("prev"))
		    	{
			    	
			    	tkktList.setNxtApprSplitting(Integer.parseInt(request.getParameter("nxtApprSplitting")));
			    	tkktList.setUserId(userId);
			    	String search = getSearchQuery(request, tkktList , nppId );
			    	tkktList.init(search);
			    	tkktList.setAttribute(request, action, "list", "crrApprSplitting", "nxtApprSplitting");
			    	session.setAttribute("tkktList", tkktList);
			    	response.sendRedirect("/TraphacoERP/pages/Erp/TaiKhoanKt.jsp");	
			    }
		    	else
		    	{
			    	String search = getSearchQuery(request, tkktList, nppId);
			    	tkktList.init(search);
					
			    	session.setAttribute("tkktList", tkktList);
		    		session.setAttribute("userId", userId);
			
		    		response.sendRedirect(request.getContextPath() + "/pages/Distributor/TaiKhoanKt.jsp");  
		    	}
				
			}
		}
	}

	private String getSearchQuery(HttpServletRequest request, ITaikhoanktList tkktList, String nppId) 
	{
		Utility util = new Utility();
		
		String sql = " SELECT TK.PK_SEQ,TK.SOHIEUTAIKHOAN,TK.TENTAIKHOAN,"+
						" CASE "+ 
						"	WHEN TK.TAIKHOANCOCHITIET=1 THEN N'Có ' "+
						"	WHEN TK.TAIKHOANCOCHITIET=0 THEN N'Không' "+
						" END TAIKHOANCOCHITIET, "+
						"LTK.TEN AS LOAITAIKHOAN,NT.TEN AS NGUOITAO,TK.NGAYTAO,NS.TEN AS NGUOISUA,TK.NGAYSUA," + 
						" CASE "+ 
						"	WHEN TK.TRANGTHAI=1 THEN N'Hoạt động'"+
						"	WHEN TK.TRANGTHAI=0 THEN N'Ngưng hoạt động'"+
						" END TRANGTHAI , '' AS CONGTY"+
					" FROM ERP_TAIKHOANKT  TK "+
					" INNER JOIN ERP_LOAITAIKHOAN LTK ON LTK.PK_SEQ=TK.LOAITAIKHOAN_FK "+
					" INNER JOIN NHANVIEN NT ON NT.PK_SEQ=TK.NGUOITAO "+
					" INNER JOIN NHANVIEN NS ON NS.PK_SEQ=TK.NGUOISUA "+
					" WHERE TK.NPP_FK = '" + nppId + "' ";
 
			if( tkktList.getSoHieuTaiKhoan().trim().length() > 0)
				sql += " AND TK.SoHieuTaiKhoan like N'%" + tkktList.getSoHieuTaiKhoan() + "%' ";
			
			if( tkktList.getTenTaiKhoan().trim().length() > 0)
				sql += " AND dbo.ftBoDau(TK.TenTaiKhoan) LIKE N'%" +  util.replaceAEIOU(tkktList.getTenTaiKhoan()) + "%' ";
			
			if( tkktList.getLoaiTaiKhoanId().trim().length() > 0 )
				sql+=" AND  TK.LOAITAIKHOAN_FK='" + tkktList.getLoaiTaiKhoanId() + "' ";
			
			if( tkktList.getTrangThai().trim().length()>0)
				sql+=" AND  TK.TRANGTHAI='" + tkktList.getTrangThai() + "' ";
		
		return sql;
	}
}
