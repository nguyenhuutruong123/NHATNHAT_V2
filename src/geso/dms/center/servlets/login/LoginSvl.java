package geso.dms.center.servlets.login;

import geso.dms.center.beans.mokhoaso.IMokhoaso;
import geso.dms.center.beans.mokhoaso.imp.Mokhoaso;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.servlets.count.SessionCounter;
import geso.dms.center.util.Utility;
import geso.dms.distributor.util.FixData;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 public class LoginSvl extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet  {
   static final long serialVersionUID = 1L;
   public LoginSvl() {
		super();
	}   	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
	
	
    	String userId = (String)request.getSession(false).getAttribute("userId");  
    	String userTen = (String)request.getSession(false).getAttribute("userTen");
    	String sum = (String)request.getSession(false).getAttribute("sum");
    	String site = (String)request.getSession(false).getAttribute("site");
    	Utility util = (Utility)request.getSession(false).getAttribute("util");
    	if(!util.check(userId, userTen, sum)){
    		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
    	}else{
    		response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
    	}
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("  getScheme = " + request.getScheme());
		System.out.println("  getServerName = " + request.getServerName());

		
		HttpSession session = request.getSession(true);
		geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,false,true ,false);
		if(!csdr.__validate_post())
		{
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		
		String userId = ""; 
		String userTen = ""; 
			
		Utility util = new Utility();
	    request.setCharacterEncoding("UTF-8");
	    response.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
	    dbutils db = new dbutils();
	    
	    String name = util.ValidateParam(util.antiSQLInspection(request.getParameter("username")));
		String pass = util.ValidateParam(util.antiSQLInspection(request.getParameter("password")));
	  
		String sql="select nppid,ngayks,trangthai from KHAOSOTHANGTUDONG where NGAYKS=CONVERT(varchar(10), GETDATE(),120)";
		ResultSet rs1=db.get(sql);
		if(rs1!=null)
		{
			try {
				while(rs1.next())
				{
					System.out.println("vao day rui ne bat dau khoa so");
					  IMokhoaso mksBean = new Mokhoaso();
					boolean flag=mksBean.KhoaSoNgay(rs1.getString("nppid"));
					System.out.println("flag="+flag);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			rs1.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*String name = util.ValidateParam(util.antiSQLInspection(request.getParameter("username")));
		
		if(name == null)
			name = "";
		session.setAttribute("userLogin", name); 
		
		if( name.contains("<script") || name.contains("</script") || name.contains("< script") || name.contains("</ script") || name.contains("<") )
			name = "";
		System.out.println("---NAME LA: " + name);
		
		String pass = util.ValidateParam(util.antiSQLInspection(request.getParameter("password")));*/
		
		String site="";
		try{
		String qr =	"select top(1) phanloai from nhanvien where dangnhap='"+name+"'";
		System.out.println("qr : "+qr);
		ResultSet pl = db.get(qr);
		
		pl.next();
		
		site =  pl.getString("phanloai");//util.antiSQLInspection(request.getParameter("site"));
		System.out.println("site: " + site + "; "+qr);
		}catch(Exception  ex){}
		
	    String login = util.antiSQLInspection(request.getParameter("login"));
	    
	    String chonchucdanh = util.antiSQLInspection(request.getParameter("chonchucdanh"));
	    if(chonchucdanh == null)
	    	chonchucdanh = "";
	    
	    if (login.equals("1")){
	    	
	    	try{
	    	if(chonchucdanh.equals("1"))
	    	{
	    		String chucdanhId = request.getParameter("chucdanhId");
	    		session.setAttribute("chucdanhId", chucdanhId);
	    		session.setMaxInactiveInterval(240000);
	    		
	    		String query = "UPDATE NHANVIEN SET CONVSITECODE = (SELECT SITECODE FROM NHAPHANPHOI WHERE PK_SEQ = '"+ chucdanhId +"') WHERE PK_SEQ = '"+ session.getAttribute("userId") +"'";
	    		
	    		System.out.println("query : "+query);
	    		if(db.update(query))
	    		{
	    			    							
					query=
					"	select top(1) NAM as namMax, THANGKS as thangMax, 	( select top(1) loaiNPP from NHAPHANPHOI where pk_seq in( select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp   "+ 
					"	on nv.convsitecode = sitecode where nv.pk_seq = '"+ session.getAttribute("userId") +"' and nv.trangthai = '1' ) ) as loaiNPP    "+
					"	from NHAPHANPHOI a left join   KHOASOTHANG b on b.NPP_FK=a.PK_SEQ  "+
					"	where a.PK_SEQ in ( select npp.pk_seq from nhanvien nv  inner join nhaphanphoi npp on nv.convsitecode = sitecode  where nv.pk_seq =  '"+ session.getAttribute("userId") +"' and nv.trangthai = '1' )  "+ 
					"	order by NAM desc, THANGKS desc ";
					
					System.out.println("1.Khoi tao thang: " + query);
					ResultSet rs = db.get(query);
					
					String thangKs = "";
					String namKs = "";
					
					if(rs != null)
					{
						while(rs.next())
						{
							thangKs = rs.getString("thangMax")==null?"":rs.getString("thangMax");
							namKs = rs.getString("namMax")==null?"":rs.getString("namMax"); 
					
							session.setAttribute("ngaykhoasonpp", thangKs + " / " + namKs);
							session.setAttribute("loaiNPP", rs.getString("loaiNPP"));
						}
						rs.close();
					}	
					
					SessionCounter.activeSessions+=1;	    			
	    			response.sendRedirect(request.getContextPath() + "/Distributor/mainpage.jsp");
	    			return;
				}
	    		
	    	}
	    	}catch(Exception ex){ex.printStackTrace();}
	    	System.out.println("toi day !!!!");
	    	
	    	String result = createSession(request, name, pass, site);
	    	
	    	System.out.println("result : "+result);
	    	if(result.equals("3"))
	    	{
	    		response.sendRedirect(request.getContextPath() + "/ChonChiNhanhDoiTac.jsp");
	    	}
	    	else if (result.equals("2"))
	    	{			
	    		if(site.equals("1"))
	    		{
	    			//FIX BOOKED
	    			FixData fix = new FixData();
	    			fix.ProcessBOOKED_DANGNHAP( session.getAttribute("userId").toString() );
	    			
	    			SessionCounter.activeSessions+=1;
	    			response.sendRedirect(request.getContextPath() + "/Distributor/mainpage.jsp");	
	    		}
	    		else
	    		{
	    			SessionCounter.activeSessions+=1;
	    			response.sendRedirect(request.getContextPath() + "/Center/mainpage.jsp");
	    		}
	    	}else{
	    		if(result.equals("1")){
	    			response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");				
	    		}else{
	    			session.setAttribute("msg", "Tai khoan khong hop le hoac tai khoan da duoc dang nhap");
	    			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	    		}
	    	}
	    }else{
	    	userId = (String)request.getSession(false).getAttribute("userId");  
	    	userTen = (String)request.getSession(false).getAttribute("userTen");
	    	String sum = (String)request.getSession(false).getAttribute("sum");
	    	site = (String)request.getSession(false).getAttribute("site");
	    	util = (Utility)request.getSession(false).getAttribute("util");
	    	if(!util.check(userId, userTen, sum))
	    	{
	    		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	    	}else{ 
	    		String oldpass = util.ValidateParam(util.antiSQLInspection(request.getParameter("oldpass")));
	    		String newpass = util.ValidateParam(util.antiSQLInspection(request.getParameter("newpass1")));
	    		String query = "SELECT pwdcompare ('" + oldpass + "', (select matkhau from nhanvien where pk_seq='" + userId + "')) as num";
	    		
	    		
	    		ResultSet rs = db.get(query);
	    		try{
	    			rs.next();
	    			if (rs.getString("num").equals("0"))
	    			{
	    				session.setAttribute("msg", "Mat khau khong hop le");
		    			response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");
	    			}
	    			else
	    			{
	    				if(newpass.length() > 5 & !newpass.contains("12345")){
	    					query = "update nhanvien set matkhau= pwdencrypt('" + newpass + "') , sessionId = '" + getDate() + "'  where pk_seq='" + userId + "'";
	    					db.update(query);
	    					
	    					if(site.equals("1"))
	    					{
	    						//FIX BOOKED
	    		    			FixData fix = new FixData();
	    		    			fix.ProcessBOOKED_DANGNHAP( session.getAttribute("userId").toString() );
	    		    			
	    						response.sendRedirect(request.getContextPath() + "/Distributor/mainpage.jsp");	
	    					}
	    					else
	    					{
	    						response.sendRedirect(request.getContextPath() + "/Center/mainpage.jsp");
	    					}
	    				}
	    				else
	    				{
	    					if (newpass.length() <= 5){
	    						session.setAttribute("msg", "Mat khau phai dai tren 5 ky tu");
	    						response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");	    					
	    					}else{
	    					if(newpass.contains("12345")){
	    						session.setAttribute("msg", "Mat khau qua de doan");
	    						response.sendRedirect(request.getContextPath() + "/ChangePassword.jsp");	    					   						
	    					}
	    					}
	    				}
	    			}
	    			rs.close();
	    			db.shutDown();
	    		}
	    		catch(Exception e)
	    		{
	    			e.printStackTrace();
	    		}

	    	}
	    }		
	}
			
    private String getTime()
    {
        return new Date(System.currentTimeMillis()).toString();
    }
	
    private String createSession(HttpServletRequest request, String name, String pass, String site)
    {
		Utility util = new Utility();
		dbutils db = new dbutils();
//		dbutils db = new dbutils();
		String userId = "";
		String userTen = "";
		String loai = "";
		String query;
		String result;
		int loaimenu = -1;
		
		
		//  db.AddParam("@pass", pass); db.AddParam("@name", name); query =
		//  "SELECT pwdcompare (@pass, (select matkhau from nhanvien where dangnhap=@name)) as num"
		//  ;
		 
		query = "select 1 num";
		ResultSet rs = db.get_with_param(query);
		try{
			if(rs.next()){
				if(rs.getString("num").equals("1")){
					rs.close();
					
					query = "\n select pk_seq, ten, isnull(loai,'') as loai " +
							"\n ,(select top 1 loaimenu from danhmucquyen where pk_seq in (select dmq_fk from PHANQUYEN where nhanvien_fk = nhanvien.pk_seq ))loaimenu " +
							"\n from nhanvien where dangnhap='" + name + "' and phanloai='" + site + "' and trangthai='1'";					
					rs = db.get(query);
		
					if(rs.next()){			
						userId = rs.getString("pk_seq"); 
						userTen = rs.getString("ten");
						loai = rs.getString("loai");
						loaimenu= rs.getInt("loaimenu");
						System.out.println("UserId 1:" + userId);
						
						query = "insert into DangNhap_NhanVien(nhanvien_fk,ngay,PhanLoai,logout) 	select '" + userId + "','" + getDate() + "'," + site + ",NULL where not exists (select * from dangnhap_nhanvien where nhanvien_fk='" + userId + "' and ngay='" + getDate()
								+ "') ";
			    		db.update(query);
						rs.close();				
					}else{
						userId =  "";
						userTen = "";
						loai = "";
						System.out.println("UserId 1:" + userId);
					}									
				}
			}
			
			
		
		rs.close();
		if(site.equals("1"))
		{
			query = "SELECT COUNT(PK_SEQ) AS NUM FROM NHANVIEN WHERE DANGNHAP='" + name + "' " + 
					"and sessionId <=(SELECT CONVERT(VARCHAR(10),DATEADD(day,-600,GETDATE()),120))";
		}else
		{
			query = "SELECT COUNT(PK_SEQ) AS NUM FROM NHANVIEN WHERE DANGNHAP='" + name + "' " + 
						"and sessionId <=(SELECT CONVERT(VARCHAR(10),DATEADD(day,-600,GETDATE()),120))";
		}
		System.out.println(query);


		rs = db.get(query);
		
		rs.next();
		if ((userId.length()>0) )
		{
			// Kiem tra password co bi het han su dung khong?
			if (rs.getString("NUM").equals("1"))
			{
				result = "1";
			}else
			{
				rs.close();
				//Kiem tra password co bang voi username khong?
				query = "SELECT pwdcompare ('" + name + "', (select matkhau from nhanvien where dangnhap='" + name + "')) as num";
				rs = db.get(query);
				rs.next();
				
				if(rs.getString("num").equals("1")) 
					result = "1";
				else
					{
						result = "2";
						System.out.println("result ss : "+result);
					}
				rs.close();
			}
		}else
		{
			result = "0";
		}
		

		HttpSession session = request.getSession(true);

		if(result.equals("1") || result.equals("2"))
		{
			session.setAttribute("userId", userId);	
			////Up date lai islog =1
			
			query ="update NHANVIEN set ISLOGIN='1' where PK_SEQ='"+userId+"'";
			db.update(query);
			//
			session.setAttribute("userTen", userTen);
			session.setAttribute("sum", util.calSum(userId, userTen));
			session.setAttribute("util", util);
			session.setAttribute("site", site);			
			session.setMaxInactiveInterval(30000);
					
			System.out.println("site : "+site+" - loai : "+loai);
			if(site.equals("2") && loai.equals("3") && loaimenu == 1)
			{
				
				session.setAttribute("userId", userId);					
				session.setAttribute("userTen", userTen);
				session.setAttribute("sum", util.calSum(userId, userTen));
				session.setAttribute("util", util);
				session.setAttribute("site", site);	
				session.setAttribute("pass", pass);
				
				session.setMaxInactiveInterval(30000);
				result = "3";
				
				System.out.println("RESULT TRONG LOGIN : "+result);
			}
				
			
			if(site.equals("1"))
			{
					/*
					 * query =
					 * "select isnull(max(ngayks), 'Chưa có ngày khóa sổ') as ngay from khoasongay where npp_fk in ( "
					 * +
					 * "select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode "
					 * + "where nv.pk_seq='" + userId +
					 * "' and nv.trangthai = '1')"; ResultSet ngayks =
					 * db.get(query); if(ngayks.next()) {
					 * session.setAttribute("ngaykhoasonpp",
					 * ngayks.getString("ngay")); } ngayks.close();
					 */
				
		/*		query = "select top(1) NAM as namMax, THANGKS as thangMax, " +
						"	( select loaiNPP from NHAPHANPHOI where pk_seq = ( select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode where nv.pk_seq = '" + userId + "' and nv.trangthai = '1' ) ) as loaiNPP " +
						"from KHOASOTHANG where NPP_FK = ( select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp on nv.convsitecode = sitecode where nv.pk_seq = '" + userId + "' and nv.trangthai = '1' ) " +
						"order by NAM desc, THANGKS desc ";*/
			
				query=
			"	select top(1) NAM as namMax, THANGKS as thangMax, 	( select top(1) loaiNPP from NHAPHANPHOI where pk_seq in( select npp.pk_seq from nhanvien nv inner join nhaphanphoi npp   "+ 
			"					on nv.convsitecode = sitecode where nv.pk_seq = '"+userId+"' and nv.trangthai = '1' ) ) as loaiNPP    "+
			"	from NHAPHANPHOI a left join   KHOASOTHANG b on b.NPP_FK=a.PK_SEQ  "+
			"	where a.PK_SEQ in ( select npp.pk_seq from nhanvien nv  inner join nhaphanphoi npp on nv.convsitecode = sitecode  where nv.pk_seq =  '"+userId+"' and nv.trangthai = '1' )  "+ 
			"	order by NAM desc, THANGKS desc ";
				
				System.out.println("1.Khoi tao thang: " + query);
				rs = db.get(query);
				
				String thangKs = "";
				String namKs = "";
				
				if(rs != null)
				{
					while(rs.next())
					{
						thangKs = rs.getString("thangMax")==null?"":rs.getString("thangMax");
						namKs = rs.getString("namMax")==null?"":rs.getString("namMax"); 
				
						session.setAttribute("ngaykhoasonpp", thangKs + " / " + namKs);
						session.setAttribute("loaiNPP", rs.getString("loaiNPP"));
					}
					rs.close();
				}
				
			}
			
	    		
			
		}
		if(rs != null)
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
			return "0";
		}
		db.shutDown();
		return result;
    }
    
	private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return dateFormat.format(date);	
	}
}