package geso.dms.center.servlets.PhanBoKMApGame;

import geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGame;
import geso.dms.center.beans.PhanBoKMApGame.IPhanBoKMApGameList;
import geso.dms.center.beans.PhanBoKMApGame.imp.PhanBoKMApGame;
import geso.dms.center.beans.PhanBoKMApGame.imp.PhanBoKMApGameList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Utility;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PhanBoKMApGameSvl extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public PhanBoKMApGameSvl() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 

		HttpSession session = request.getSession();	    
		IPhanBoKMApGameList obj = new PhanBoKMApGameList();

		Utility util = new Utility(); 

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring); 

		if (userId.length()==0)
			userId = request.getParameter("userId");

		String action = util.getAction(querystring); 

		String ctkmId = util.getId(querystring);

		if (action.equals("chot"))
		{
			//String msg = Delete(ctkmId);
			//if(msg.length() > 0)
			obj.setMessage("Test chốt");
		}  
		
		
		obj.setRequestObj(request);
		obj.setUserId(userId);
		obj.init("");
		session.setAttribute("obj", obj);
		session.setAttribute("dkkmDien_giai", "");
		session.setAttribute("dkkmTungay", "");
		session.setAttribute("dkkmDenngay", "");

		String nextJSP = request.getContextPath() + "/pages/Center/PhanBoKMApGame.jsp";
		response.sendRedirect(nextJSP);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		IPhanBoKMApGameList obj = new PhanBoKMApGameList();	    
		HttpSession session = request.getSession();
		String userId = request.getParameter("userId");
		String userTen = request.getParameter("userTen");
		if(userId == null)
		{
			userId = ""; 
		}
		obj.setUserId(userId);;
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

		String npp = request.getParameter("npp");
		if (npp == null)
			npp = "";    	
		obj.setnpp(npp);
		
		String vung = request.getParameter("vung");
		if (vung == null)
			vung = "";    	
		obj.setvung(vung);
		
		String khuvuc = request.getParameter("khuvuc");
		if (khuvuc == null)
			khuvuc = "";    	
		obj.setkhuvuc(khuvuc);

		String trangthai = request.getParameter("trangthai");
		if (trangthai == null)
			trangthai = "1";    	
		obj.setTrangthai(trangthai);
		
		String action = request.getParameter("action");
		if (action == null)
			action = ""; 
		if(action.equals("taomoi"))
		{
			IPhanBoKMApGame ctkmBean = new PhanBoKMApGame();
			ctkmBean.init();
			session.setAttribute("ctkmBean", ctkmBean);  	
			session.setAttribute("userId", userId); 
			session.setAttribute("userTen", userTen); 
			response.sendRedirect(request.getContextPath() + "/pages/Center/PhanBoKMApGameNew.jsp");
		}
		else {
			obj.init(this.getSearchQuery(request, obj));
			session.setAttribute("obj", obj);  	
			session.setAttribute("userId", userId); 
			session.setAttribute("userTen", userTen); 
			response.sendRedirect(request.getContextPath() + "/pages/Center/PhanBoKMApGame.jsp");
		}
	}
	
	private String getSearchQuery(HttpServletRequest request, IPhanBoKMApGameList obj)
	{	
		Utility util = new Utility();
		dbutils db = new dbutils();

		

		String query = "select count(distinct e.kbh_fk) as sokenh from CTKHUYENMAI a inner join CTKM_NPP d on a.PK_SEQ = d.CTKM_FK "+
				"inner join NHAPP_KBH e on d.NPP_FK = e.NPP_FK and a.PK_SEQ = d.CTKM_FK";
		int sokenh = 0;
		ResultSet rs = db.get(query);
		if(rs != null)
		{
			try
			{
				rs.next();
				sokenh = rs.getInt("sokenh");
			}
			catch(Exception e){e.printStackTrace();}
		}

		query = "select distinct a.PK_SEQ as ctkmId, a.scheme, isnull(a.DIENGIAI, '') as diengiai, a.TUNGAY, a.DENNGAY"
				+"\n , isnull(a.LOAICT, '1') as type, isnull(a.NGANSACH, '') as NGANSACH, a.DASUDUNG"
				+"\n , isnull(a.NGAYTAO, '') as NGAYTAO, isnull(a.NGAYSUA, '') as NGAYSUA, b.TEN as nguoitao, c.TEN as nguoisua " +
				"\n , [dbo].[getvungctkm](a.PK_SEQ) as Vten "+ 
				"\n  , (  "+
				"\n 		select COUNT(*)  "+
				"\n 		from PHANBOKHUYENMAI pb where pb.CTKM_FK=a.PK_SEQ "+
				"\n 						and NGANSACH!=0 ) as isPB, "+
				"\n    isnull(a.isduyet,0) isDuyet "+
				"\n 	, (select COUNT(*) From DONHANG_CTKM_TRAKM  km where km.CTKMID=a.PK_SEQ  )  "+
				"\n 	+ "+
				"\n 	(select COUNT(*) from ERP_DONDATHANG_CTKM_TRAKM  where CTKMID=a.PK_SEQ )as soDH, "+
				"\n  DATEDIFF(day, a.tungay, dbo.GetLocalDate(DEFAULT)) kcngay,isnull(ISDONGBO,'0') as ISDONGBO "+
				"\n  from CTKHUYENMAI a inner join NHANVIEN b on a.NGUOITAO = b.PK_SEQ inner join NHANVIEN c on a.NGUOISUA = c.PK_SEQ "+
				"\n  where 1=1 and a.loaict=5  and a.PK_SEQ in (select ctkm_fk from CTKM_NPP ctn inner join phamvihoatdong pv on pv.npp_fk = ctn.npp_fk 	       "+ 
				"\n inner join nhaphanphoi npp on npp.PK_SEQ = ctn.NPP_FK	  "+
				"\n and pv.Npp_fk = npp.PK_SEQ and pv.Nhanvien_fk = '"+obj.getUserId()+"')"; 
		
		
		
		if(sokenh < 2)
			query+=
			" and (select distinct e.kbh_fk from CTKM_NPP d inner join NHAPP_KBH e on d.NPP_FK = e.NPP_FK and a.PK_SEQ = d.CTKM_FK) in " +util.quyen_kenh(obj.getUserId());
		if (obj.getDiengiai().length()>0){
			query = query + " and upper(a.diengiai) like upper('%" + obj.getDiengiai() + "%') or upper(a.SCHEME) like upper('%" + obj.getDiengiai() + "%')";			
		}
		if (obj.getTungay().length()>0){
			query = query + " and a.tungay >= '" + obj.getTungay() + " '";			
		}

		if (obj.getDenngay().length()>0){
			query = query + " and a.denngay <= '" + obj.getDenngay() + " '";		
		}
 
		if(obj.getvung().length() > 0)
			query += " and  exists (select 1 from ctkm_npp z inner join nhaphanphoi x on z.npp_fk = x.pk_seq inner join khuvuc kv on x.khuvuc_fk = kv.pk_seq where vung_fk = '"+obj.getvung()+"' and z.ctkm_fk = a.pk_seq )" ;
		
		if(obj.getkhuvuc().length() > 0)
			query += " and  exists (select 1 from ctkm_npp z inner join nhaphanphoi x on z.npp_fk = x.pk_seq  where x.khuvuc_fk = '"+obj.getkhuvuc()+"' and z.ctkm_fk = a.pk_seq )" ;
		
		if(obj.getnpp().length() > 0)
			query += " and  exists (select 1 from ctkm_npp z where z.npp_fk = '"+obj.getnpp()+"' and z.ctkm_fk = a.pk_seq )" ;
		
		if(obj.getTrangthai().equals("1")){
			query = query + " and tungay <= (SELECT CONVERT(VARCHAR(10),DATEADD(day,0,dbo.GetLocalDate(DEFAULT)),120))" +
					" and denngay >=( SELECT CONVERT(VARCHAR(10),DATEADD(day,0,dbo.GetLocalDate(DEFAULT)),120))";							 

		}

		if(obj.getTrangthai().equals("2")){
			query = query + " and (tungay > (SELECT CONVERT(VARCHAR(10),DATEADD(day,0,dbo.GetLocalDate(DEFAULT)),120))" +
					" or denngay < ( SELECT CONVERT(VARCHAR(10),DATEADD(day,0,dbo.GetLocalDate(DEFAULT)),120)))";

		}


		//query = query + " order by a.NGAYTAO DESC, a.pk_seq DESC";

		System.out.println("query search: "+query);

		return query;
	}

}
