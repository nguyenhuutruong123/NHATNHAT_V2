package geso.dms.center.servlets.thongbao;

import geso.dms.center.beans.thongbao.IThongbao;
import geso.dms.center.beans.thongbao.IThongbaoList;
import geso.dms.center.beans.thongbao.imp.Thongbao;
import geso.dms.center.beans.thongbao.imp.ThongbaoList;
import geso.dms.center.db.sql.dbutils;
import geso.dms.center.util.Csrf;
import geso.dms.center.util.Utility;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.extentech.toolkit.Base64;

public class RouterSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RouterSvl() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		// String task =
		// geso.dms.center.util.Utility.antiSQLInspection(request.getParameter("task"));
		String task = request.getParameter("task");

		String t = request.getServletContext().getInitParameter(
				"RedirectNoScript");
		/*
		 * try { t = Utility.giaiMa(task); } catch (InvalidKeyException |
		 * NoSuchAlgorithmException | NoSuchPaddingException |
		 * IllegalBlockSizeException | BadPaddingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		t = Utility.giaiMa(task);
		
		Csrf csrf = new Csrf(request, response, false, true, true);
		System.out.println("Link " +csrf._create_link(t));
		System.out.println("Link " + t);
		HttpSession session = request.getSession();
		long millis = System.currentTimeMillis();
		String User_Agent = request.getHeader("User-Agent");
		String hash = String.valueOf(millis).concat(".").concat(User_Agent);
		session.setAttribute("flag",hash);		
		response.sendRedirect(csrf._create_link(t)+"&flag="+hash);
		
		
		
		//System.out.println("Link " + t);
	//	response.sendRedirect(t);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

}
