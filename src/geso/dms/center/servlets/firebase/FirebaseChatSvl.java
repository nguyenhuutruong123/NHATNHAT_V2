package geso.dms.center.servlets.firebase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;



import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import geso.dms.center.beans.Chat.Chat;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Csrf;
import geso.dms.center.util.Utility;

/**
 * Servlet implementation class FirebaseChatSvl
 */
@WebServlet("/FirebaseChatSvl")
public class FirebaseChatSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirebaseChatSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		
		if(!Utility.val_doget(session, request, response))
		{
			session.setAttribute("flag",null);
			response.sendRedirect(request.getContextPath() + "/redirect.jsp");
			return;
		}
		else
		{
			session.setAttribute("flag",null);
		}
		Utility util = new Utility();

		String querystring = request.getQueryString();
		String userId = util.getUserId(querystring);
		session.setAttribute("userId", userId);
		Chat c=new Chat();
		c.init();
		session.setAttribute("obj", c);
	
		String nextJSP = request.getContextPath() + "/pages/Center/ChatFirebase.jsp";
		response.sendRedirect(nextJSP);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");	    	    
		HttpSession session = request.getSession();	    			   		    

		String action=request.getParameter("action");
		System.out.println("vao day action "+ action);
		Utility ul=new Utility();
		String msg=ul.antiSQLInspection( request.getParameter("msg"));
		String userid=ul.antiSQLInspection(request.getParameter("userid"));
		String firebase=request.getParameter("firebase");
		String user_chat=ul.antiSQLInspection(request.getParameter("user_chat"));

		String _token_in_request=ul.antiSQLInspection(request.getParameter(Csrf.get_tokenName_Static()));		
		if(!Csrf.validate_static_post(_token_in_request, request))
	        {
	        	return;
	        }
		
		if(action.equals("updatetoken"))
		{
			dbutils db=new dbutils();
			String quString="select count(*) sl from firebase where token=N'"+firebase+"' ";
			System.out.println("check user "+quString);
			ResultSet rs=db.get(quString);
			try {
				rs.next();
				int sl=rs.getInt("sl");
				rs.close();
				if(sl==0 && userid.length()>0 && firebase.length()>0)
				{
					String query="insert into firebase (userid,token) values ("+userid+",N'"+firebase+"') ";
					System.out.println("query == "+query);
					db.update(query); 
					response.getWriter().write("ISINSER");	
					return;
				}
				else
				{

					String quString1="select count(*) sl from firebase where userid<>"+userid +" and token=N'"+firebase+"' ";
					System.out.println("check trung token "+quString1);
					ResultSet rs1= db.get(quString1);
					rs1.next();
					int  sl1=rs1.getInt("sl");
					rs1.close();
					if(sl1==1)
					{
						db.update("delete firebase where  token=N'"+firebase+"' ");
						response.getWriter().write("CRNEW");	
						return;
					}

				}


				db.shutDown();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(action.equals("msg"))
		{
			String type=request.getParameter("type");
			System.out.println("type =="+type);
			// viet hàm send tin nhắn  chỗ này
			String  urlParam ="token=&imei=''&userFrom="+userid+"&message="+msg+"&type="+type+"&userTo="+user_chat+" " ;
			System.out.println("urlParam="+urlParam);
			String data ="";
			String url = request.getServletContext().getInitParameter("path_sendms") + "SendMessage";

			
			try {
				data = geso.dms.center.util.Utility.sendPost(url, urlParam);
				System.out.println("data=="+data);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.getWriter().write(data);	
		}
		if(action.equals("init_data"))
		{
			dbutils db=new dbutils();
			String qu="select message,nhanvien_fk,khachhang_fk,loainguoigui,loaitinnhan from KhachHang_TinNhan a where  khachhang_fk='"+msg+"' order by thoidiem ASC ";
			System.out.println("qu===="+qu);
			ResultSet rs=db.get(qu);
			try {
				String data_init="";
				while (rs.next())
				{
					if(rs.getInt("loainguoigui")==1 )
					{
						if(rs.getInt("loaitinnhan")==0)
						{
							data_init+=	" <li class=\"sent\"> \n"+
									"	<p>"+rs.getString("message")+"</p> \n"+
									" </li>";	
						}
						if(rs.getInt("loaitinnhan")==1)
						{
							data_init+=	" <li class=\"sent\"> \n"+
									"	<img  onclick=\"ViewImage(this)\" src=\" "+ rs.getString("message") +" \">  \n"+
									" </li>";	
						}
						
					}
					if( rs.getInt("loainguoigui")==0) {

						if(rs.getInt("loaitinnhan")==0)
						{
							data_init+=	"<li class=\"replies\"> \n"+
									"	<p>"+rs.getString("message")+"</p> \n"+
									" </li>";
						}
						if(rs.getInt("loaitinnhan")==1)
						{
							data_init+=	" <li class=\"replies\"> \n"+
									"	<img  onclick=\"ViewImage(this)\" src=\" "+ rs.getString("message") +" \">  \n"+
									" </li>";	
						}
						
					}


				}
				db.update("update KhachHang_TinNhan set trangthai=1 where  khachhang_fk='"+msg+"'    ");

				response.getWriter().write(data_init);	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}


	}

}
