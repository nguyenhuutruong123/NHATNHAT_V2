<%@page import="java.awt.Dimension"%>
<%@page import="java.awt.Toolkit"%>
<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF-8"%>

<% String msg = (String) session.getAttribute("msg");  
   session.setAttribute("msg", "");
   
   String userName = (String) session.getAttribute("userLogin");  
   if(userName == null)
	   userName = "";
   
   if( userName.contains("<script") || userName.contains("</script") || userName.contains("< script") || userName.contains("</ script") )
	   userName = "";
   
   Toolkit kit = Toolkit.getDefaultToolkit();
	Dimension screenSize = kit.getScreenSize();
	int screenWidth = screenSize.width;
	int screenHeight = screenSize.height;
   if(screenWidth>=1280)
   {
   	 session.putValue("chuoi","../css/style1280.css");
    }
   else if(screenWidth>=1024)
   {
   	 session.putValue("chuoi","../css/style1024.css");
   }
   else if(screenWidth>=800)
   {
   	 session.putValue("chuoi","../css/style800.css");
   }
%>
<html>

<head>
	<title>Đăng nhập</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link rel="shortcut icon" href="pages/images/salesup.jpg">
	<LINK rel="stylesheet" href="pages/css/style_v2_optimized.css" type="text/css">
	<!-- <LINK rel="stylesheet" href="pages/css/main.css" type="text/css">  -->
	<style type="text/css">
		.blanc 
		{
		  color : #999999;
		  font-family: Arial, Helvetica, sans-serif;
		  letter-spacing : 0pt;
		  text-decoration: none ; 
		  font-weight : 200;
		  font-size: 1.0em;
		}
	</style>
</head>
<body>
<form name="loginForm" method="post">
<input type="hidden" name="login" value = "1"></input>
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %><% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 <input type="hidden" name="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
<table width="350px" align="left" style="margin-left: 40px" cellpadding="0px" cellspacing="0px">
    	<tr height="25px">
        	<td style="background-image:url(pages/images/borderTopLeft.png); width:25px"></td>
            <td style="background-image:url(pages/images/borderTopCenter.png)"></td>
            <td style="background-image:url(pages/images/borderTopRight.png); width:25px"></td>
        </tr>
    <tr>
    <td style="background-image:url(pages/images/borderMiddleLeft.png); width:25px; background-repeat:repeat-y; background-position:left"></td>
    <td style="background-image:url(pages/images/point_make_area_transparent.png)">
    
    <div style="width: 100%; margin-top: -5" align="center">
    	<img src="pages/images/dms.jpg" width="70px" height="40px" style="display:none;" />
    	<img src="pages/images/salesup.jpg" width="90px" height="40px"/>
    </div>
    <table cellpadding="1px" cellspacing="4px">
    	<tr>
    		<td class="blanc"><span>Tên đăng nhập</span></td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<div class="input-field-login icon username-container">
		            <input name="username" id="username" autofocus="autofocus" placeholder="Vui lòng nhập tên đăng nhập." 
		                    class="std_textbox" tabindex="1" required="" type="text" AUTOCOMPLETE="off" value="<%= request.getParameter("username") != null ? request.getParameter("username") : "admin" %>" >
		        </div>
    		</td>
    	</tr>
    	<tr>
    		<td class="blanc"><span>Mật khẩu</span></td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<div class="input-field-login icon password-container">
		            <input name="password" id="password" placeholder="Vui lòng nhập mật khẩu." class="std_textbox" tabindex="2" required="" type="password" AUTOCOMPLETE="off" value="admin1">
		        </div>
    		</td>
    	</tr>
    	<tr>
    		<td class="blanc"><span>Mã bảo vệ</span></td>
    		<td>&nbsp;</td>
    	</tr>
    	<tr>
    		<td colspan="2">
    			<div class="input-field-login icon username-container">
		            <input name="captchas" id="captchas" autofocus="autofocus" placeholder="Vui lòng nhập mã xác nhận." 
		                    class="std_textbox" tabindex="3" required="" type="text" AUTOCOMPLETE="off" value="geso@123" >
		        </div>
    		</td>
    	</tr>
    	<tr>
    		<td colspan="2"><img src="/NHATNHAT/CaptchaSvl"></td>
    	</tr>
    	<tr>
    		<td colspan="2"><input type="submit" value=" Đăng nhập "></td>
    	</tr>
	  	<%if (msg != null){ if(msg != ""){ %>
	  		<tr>
    			<td colspan="2"><font color="red"><%= msg %></font></td>
    		</tr>
	  	<%}} %>
    </table>

    </td>
    <td style="background-image:url(pages/images/borderMiddleRight.png); width:25px; background-repeat:repeat-y; background-position:right"></td>
</tr>
<tr height="25px">
	<td style="background-image:url(pages/images/borderBottomLeft.png); width:25px"></td>
    <td style="background-image:url(pages/images/borderBottomCenter.png)"></td>
    <td style="background-image:url(pages/images/borderBottomRight.png); width:25px"></td>
</tr>
</table>
</form>
<%
	String captcha = "geso@123"; //(String) session.getAttribute("captcha");
	String username = (String) request.getParameter("username");
	String password = (String) request.getParameter("password");
	String cap = (String) request.getParameter("captchas");	
	
	if(session.getAttribute("solandn") != null)
	{
		//System.out.println("So lan dang nhap o tren: " + session.getAttribute("solandn").toString() + "\n");
		if(Integer.parseInt(session.getAttribute("solandn").toString()) >= 20)
		{
			out.print("<script>alert('Bạn đã đăng nhập vượt quá số lần qui định')</script>");
			return;
		}
	}
		
	if (cap != null && username != null && password != null)
	{
		if (captcha.toUpperCase().equals(cap.toUpperCase())) 
		{
			out.print("<script>document.forms['loginForm'].username.value='" + username + "'; document.forms['loginForm'].password.value='" + password + "'; document.forms['loginForm'].action = '/NHATNHAT/LoginSvl'; document.forms['loginForm'].submit(); </script>");
		} 
		else
		{
			if(session.getAttribute("solandn") == null)
				session.setAttribute("solandn", 0);
			else
			{
				session.setAttribute("solandn", Integer.parseInt(session.getAttribute("solandn").toString()) + 1);
				//System.out.println("So lan dang nhap: " + session.getAttribute("solandn").toString() + "\n");
			}
			out.print("<script>alert('Mã bảo vệ không đúng ')</script>");
		}
	}
%>
</body>
</HTML>
