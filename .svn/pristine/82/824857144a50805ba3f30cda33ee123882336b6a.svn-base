<%@page import="java.sql.ResultSet"%>
<%@page import="javax.xml.transform.Result"%>
<%@page import="geso.dms.center.beans.Chat.Chat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{ %>
	<% redis.clients.jedis.Jedis jedis =  geso.dms.center.util.Utility.getJedis(); %>
	<%
	
	HttpSession s = request.getSession();
	   if (s.isNew()){
		   s.invalidate();
		   System.out.println("New session");
	   }else{
		   
		   System.out.println("Old session");
	   }
	Chat obj = (Chat)s.getAttribute("obj");
		ResultSet rskh=obj.rskh;
	%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
	<HTML>
	<HEAD>
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Style-Type" content="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet"> 
	<link rel="stylesheet" href="../css/main.css" type="text/css">
	<link rel="stylesheet" href="../css/messenger.css" type="text/css">
	   <link rel="stylesheet" href="../themes/dist/css/adminlte.min.css" type="text/css">
	   <script type="text/javascript" src="../scripts/jquery.min.1.7.js"></script>
  

<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

	
  	<style>
  		.navbar-nav > li > a {padding-top:5px !important; padding-bottom:5px !important;}
		.navbar 
		{
			min-height:32px !important; 
			background-color : #80CB9B;
			font-size: 10pt;
			font-family : roboto;
		}
  	</style>


><script src='//production-assets.codepen.io/assets/editor/live/console_runner-079c09a0e3b9ff743e39ee2d5637b9216b3545af0de366d4b9aad9dc87e26bfd.js'></script><script src='//production-assets.codepen.io/assets/editor/live/events_runner-73716630c22bbc8cff4bd0f07b135f00a0bdc5d14629260c3ec49e5606f98fdd.js'></script><script src='//production-assets.codepen.io/assets/editor/live/css_live_reload_init-2c0dc5167d60a5af3ee189d570b1835129687ea2a61bee3513dee3a50c115a77.js'></script><meta charset='UTF-8'><meta name="robots" content="noindex"><link rel="shortcut icon" type="image/x-icon" href="//production-assets.codepen.io/assets/favicon/favicon-8ea04875e70c4b0bb41da869e81236e54394d63638a1ef12fa558a4a835f1164.ico" /><link rel="mask-icon" type="" href="//production-assets.codepen.io/assets/favicon/logo-pin-f2d2b6d2c61838f7e76325261b7195c27224080bc099486ddd6dccb469b8e8e6.svg" color="#111" /><link rel="canonical" href="https://codepen.io/emilcarlsson/pen/ZOQZaV?limit=all&page=74&q=contact+" />
<link href='https://fonts.googleapis.com/css?family=Source+Sans+Pro:400,600,700,300' rel='stylesheet' type='text/css'>

<script src="https://use.typekit.net/hoy3lrg.js"></script>
<script>try{Typekit.load({ async: true });}catch(e){}</script>
<link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css'><link rel='stylesheet prefetch' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.2/css/font-awesome.min.css'>
<style class="cp-pen-styles">body {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  background: #27ae60;
  font-family: "proxima-nova", "Source Sans Pro", sans-serif;
  font-size: 1em;
  letter-spacing: 0.1px;
  color: #32465a;
  text-rendering: optimizeLegibility;
  text-shadow: 1px 1px 1px rgba(0, 0, 0, 0.004);
  -webkit-font-smoothing: antialiased;
}

#frame {
  width: 95%;
  min-width: 360px;
  max-width: 1000px;
  height: 92vh;
  min-height: 300px;
  max-height: 720px;
  background: #E6EAEA;
}
@media screen and (max-width: 360px) {
  #frame {
    width: 100%;
    height: 100vh;
  }
}
#frame #sidepanel {
  float: left;
  min-width: 280px;
  max-width: 340px;
  width: 40%;
  height: 100%;
  background: #2c3e50;
  color: #f5f5f5;
  overflow: hidden;
  position: relative;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel {
    width: 58px;
    min-width: 58px;
  }
}
#frame #sidepanel #profile {
  width: 80%;
  margin: 25px auto;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile {
    width: 100%;
    margin: 0 auto;
    padding: 5px 0 0 0;
    background: #32465a;
  }
}
#frame #sidepanel #profile.expanded .wrap {
  height: 210px;
  line-height: initial;
}
#frame #sidepanel #profile.expanded .wrap p {
  margin-top: 20px;
}
#frame #sidepanel #profile.expanded .wrap i.expand-button {
  -moz-transform: scaleY(-1);
  -o-transform: scaleY(-1);
  -webkit-transform: scaleY(-1);
  transform: scaleY(-1);
  filter: FlipH;
  -ms-filter: "FlipH";
}
#frame #sidepanel #profile .wrap {
  height: 60px;
  line-height: 60px;
  overflow: hidden;
  -moz-transition: 0.3s height ease;
  -o-transition: 0.3s height ease;
  -webkit-transition: 0.3s height ease;
  transition: 0.3s height ease;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap {
    height: 55px;
  }
}
#frame #sidepanel #profile .wrap img {
  width: 50px;
  border-radius: 50%;
  padding: 3px;
  border: 2px solid #e74c3c;
  height: auto;
  float: left;
  cursor: pointer;
  -moz-transition: 0.3s border ease;
  -o-transition: 0.3s border ease;
  -webkit-transition: 0.3s border ease;
  transition: 0.3s border ease;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap img {
    width: 40px;
    margin-left: 4px;
  }
}
#frame #sidepanel #profile .wrap img.online {
  border: 2px solid #2ecc71;
}
#frame #sidepanel #profile .wrap img.away {
  border: 2px solid #f1c40f;
}
#frame #sidepanel #profile .wrap img.busy {
  border: 2px solid #e74c3c;
}
#frame #sidepanel #profile .wrap img.offline {
  border: 2px solid #95a5a6;
}
#frame #sidepanel #profile .wrap p {
  float: left;
  margin-left: 15px;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap p {
    display: none;
  }
}
#frame #sidepanel #profile .wrap i.expand-button {
  float: right;
  margin-top: 23px;
  font-size: 0.8em;
  cursor: pointer;
  color: #435f7a;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap i.expand-button {
    display: none;
  }
}
#frame #sidepanel #profile .wrap #status-options {
  position: absolute;
  opacity: 0;
  visibility: hidden;
  width: 150px;
  margin: 70px 0 0 0;
  border-radius: 6px;
  z-index: 99;
  line-height: initial;
  background: #435f7a;
  -moz-transition: 0.3s all ease;
  -o-transition: 0.3s all ease;
  -webkit-transition: 0.3s all ease;
  transition: 0.3s all ease;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options {
    width: 58px;
    margin-top: 57px;
  }
}
#frame #sidepanel #profile .wrap #status-options.active {
  opacity: 1;
  visibility: visible;
  margin: 75px 0 0 0;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options.active {
    margin-top: 62px;
  }
}
#frame #sidepanel #profile .wrap #status-options:before {
  content: '';
  position: absolute;
  width: 0;
  height: 0;
  border-left: 6px solid transparent;
  border-right: 6px solid transparent;
  border-bottom: 8px solid #435f7a;
  margin: -8px 0 0 24px;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options:before {
    margin-left: 23px;
  }
}
#frame #sidepanel #profile .wrap #status-options ul {
  overflow: hidden;
  border-radius: 6px;
}
#frame #sidepanel #profile .wrap #status-options ul li {
  padding: 15px 0 30px 18px;
  display: block;
  cursor: pointer;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options ul li {
    padding: 15px 0 35px 22px;
  }
}
#frame #sidepanel #profile .wrap #status-options ul li:hover {
  background: #496886;
}
#frame #sidepanel #profile .wrap #status-options ul li span.status-circle {
  position: absolute;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  margin: 5px 0 0 0;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options ul li span.status-circle {
    width: 14px;
    height: 14px;
  }
}
#frame #sidepanel #profile .wrap #status-options ul li span.status-circle:before {
  content: '';
  position: absolute;
  width: 14px;
  height: 14px;
  margin: -3px 0 0 -3px;
  background: transparent;
  border-radius: 50%;
  z-index: 0;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options ul li span.status-circle:before {
    height: 18px;
    width: 18px;
  }
}
#frame #sidepanel #profile .wrap #status-options ul li p {
  padding-left: 12px;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #profile .wrap #status-options ul li p {
    display: none;
  }
}
#frame #sidepanel #profile .wrap #status-options ul li#status-online span.status-circle {
  background: #2ecc71;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-online.active span.status-circle:before {
  border: 1px solid #2ecc71;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-away span.status-circle {
  background: #f1c40f;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-away.active span.status-circle:before {
  border: 1px solid #f1c40f;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-busy span.status-circle {
  background: #e74c3c;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-busy.active span.status-circle:before {
  border: 1px solid #e74c3c;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-offline span.status-circle {
  background: #95a5a6;
}
#frame #sidepanel #profile .wrap #status-options ul li#status-offline.active span.status-circle:before {
  border: 1px solid #95a5a6;
}
#frame #sidepanel #profile .wrap #expanded {
  padding: 100px 0 0 0;
  display: block;
  line-height: initial !important;
}
#frame #sidepanel #profile .wrap #expanded label {
  float: left;
  clear: both;
  margin: 0 8px 5px 0;
  padding: 5px 0;
}
#frame #sidepanel #profile .wrap #expanded input {
  border: none;
  margin-bottom: 6px;
  background: #32465a;
  border-radius: 3px;
  color: #f5f5f5;
  padding: 7px;
  width: calc(100% - 43px);
}
#frame #sidepanel #profile .wrap #expanded input:focus {
  outline: none;
  background: #435f7a;
}
#frame #sidepanel #search {
  border-top: 1px solid #32465a;
  border-bottom: 1px solid #32465a;
  font-weight: 300;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #search {
    display: none;
  }
}
#frame #sidepanel #search label {
  position: absolute;
  margin: 10px 0 0 20px;
}
#frame #sidepanel #search input {
  font-family: "proxima-nova",  "Source Sans Pro", sans-serif;
  padding: 10px 0 10px 46px;
  width: calc(100% - 25px);
  border: none;
  background: #32465a;
  color: #f5f5f5;
}
#frame #sidepanel #search input:focus {
  outline: none;
  background: #435f7a;
}
#frame #sidepanel #search input::-webkit-input-placeholder {
  color: #f5f5f5;
}
#frame #sidepanel #search input::-moz-placeholder {
  color: #f5f5f5;
}
#frame #sidepanel #search input:-ms-input-placeholder {
  color: #f5f5f5;
}
#frame #sidepanel #search input:-moz-placeholder {
  color: #f5f5f5;
}
#frame #sidepanel #contacts {
  height: calc(100% - 177px);
  overflow-y: scroll;
  overflow-x: hidden;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #contacts {
    height: calc(100% - 149px);
    overflow-y: scroll;
    overflow-x: hidden;
  }
  #frame #sidepanel #contacts::-webkit-scrollbar {
    display: none;
  }
}
#frame #sidepanel #contacts.expanded {
  height: calc(100% - 334px);
}
#frame #sidepanel #contacts::-webkit-scrollbar {
  width: 8px;
  background: #2c3e50;
}
#frame #sidepanel #contacts::-webkit-scrollbar-thumb {
  background-color: #243140;
}
#frame #sidepanel #contacts ul li.contact {
  position: relative;
  padding: 10px 0 15px 0;
  font-size: 0.9em;
  cursor: pointer;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #contacts ul li.contact {
    padding: 6px 0 46px 8px;
  }
}
#frame #sidepanel #contacts ul li.contact:hover {
  background: #32465a;
}
#frame #sidepanel #contacts ul li.contact.active {
  background: #32465a;
  border-right: 5px solid #435f7a;
}
#frame #sidepanel #contacts ul li.contact.active span.contact-status {
  border: 2px solid #32465a !important;
}
#frame #sidepanel #contacts ul li.contact .wrap {
  width: 88%;
  margin: 0 auto;
  position: relative;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #contacts ul li.contact .wrap {
    width: 100%;
  }
}
#frame #sidepanel #contacts ul li.contact .wrap span {
  position: absolute;
  left: 0;
  margin: -2px 0 0 -2px;
  width: 10px;
  height: 10px;
  border-radius: 50%;
  border: 2px solid #2c3e50;
  background: #95a5a6;
}
#frame #sidepanel #contacts ul li.contact .wrap span.online {
  background: #2ecc71;
}
#frame #sidepanel #contacts ul li.contact .wrap span.away {
  background: #f1c40f;
}
#frame #sidepanel #contacts ul li.contact .wrap span.busy {
  background: #e74c3c;
}
#frame #sidepanel #contacts ul li.contact .wrap img {
  width: 40px;
  border-radius: 50%;
  float: left;
  margin-right: 10px;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #contacts ul li.contact .wrap img {
    margin-right: 0px;
  }
}
#frame #sidepanel #contacts ul li.contact .wrap .meta {
  padding: 5px 0 0 0;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #contacts ul li.contact .wrap .meta {
    display: none;
  }
}
#frame #sidepanel #contacts ul li.contact .wrap .meta .name {
  font-weight: 600;
}
#frame #sidepanel #contacts ul li.contact .wrap .meta .preview {
  margin: 5px 0 0 0;
  padding: 0 0 1px;
  font-weight: 400;
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  -moz-transition: 1s all ease;
  -o-transition: 1s all ease;
  -webkit-transition: 1s all ease;
  transition: 1s all ease;
}
#frame #sidepanel #contacts ul li.contact .wrap .meta .preview span {
  position: initial;
  border-radius: initial;
  background: none;
  border: none;
  padding: 0 2px 0 0;
  margin: 0 0 0 1px;
  opacity: .5;
}
#frame #sidepanel #bottom-bar {
  position: absolute;
  width: 100%;
  bottom: 0;
}
#frame #sidepanel #bottom-bar button {
  float: left;
  border: none;
  width: 50%;
  padding: 10px 0;
  background: #32465a;
  color: #f5f5f5;
  cursor: pointer;
  font-size: 0.85em;
  font-family: "proxima-nova",  "Source Sans Pro", sans-serif;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #bottom-bar button {
    float: none;
    width: 100%;
    padding: 15px 0;
  }
}
#frame #sidepanel #bottom-bar button:focus {
  outline: none;
}
#frame #sidepanel #bottom-bar button:nth-child(1) {
  border-right: 1px solid #2c3e50;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #bottom-bar button:nth-child(1) {
    border-right: none;
    border-bottom: 1px solid #2c3e50;
  }
}
#frame #sidepanel #bottom-bar button:hover {
  background: #435f7a;
}
#frame #sidepanel #bottom-bar button i {
  margin-right: 3px;
  font-size: 1em;
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #bottom-bar button i {
    font-size: 1.3em;
  }
}
@media screen and (max-width: 735px) {
  #frame #sidepanel #bottom-bar button span {
    display: none;
  }
}
#frame .content {
  float: right;
  width: 60%;
  height: 100%;
  overflow: hidden;
  position: relative;
}
@media screen and (max-width: 735px) {
  #frame .content {
    width: calc(100% - 58px);
    min-width: 300px !important;
  }
}
@media screen and (min-width: 900px) {
  #frame .content {
    width: calc(100% - 340px);
  }
}
#frame .content .contact-profile {
  width: 100%;
  height: 60px;
  line-height: 60px;
  background: #f5f5f5;
}
#frame .content .contact-profile img {
  width: 40px;
  border-radius: 50%;
  float: left;
  margin: 9px 12px 0 9px;
}
#frame .content .contact-profile p {
  float: left;
}
#frame .content .contact-profile .social-media {
  float: right;
}
#frame .content .contact-profile .social-media i {
  margin-left: 14px;
  cursor: pointer;
}
#frame .content .contact-profile .social-media i:nth-last-child(1) {
  margin-right: 20px;
}
#frame .content .contact-profile .social-media i:hover {
  color: #435f7a;
}
#frame .content .messages {
  height: auto;
  min-height: calc(100% - 93px);
  max-height: calc(100% - 93px);
  overflow-y: scroll;
  overflow-x: hidden;
}
@media screen and (max-width: 735px) {
  #frame .content .messages {
    max-height: calc(100% - 105px);
  }
}
#frame .content .messages::-webkit-scrollbar {
  width: 8px;
  background: transparent;
}
#frame .content .messages::-webkit-scrollbar-thumb {
  background-color: rgba(0, 0, 0, 0.3);
}
#frame .content .messages ul li {
  display: inline-block;
  clear: both;
  float: left;
  margin: 15px 15px 5px 15px;
  width: calc(100% - 25px);
  font-size: 0.9em;
}
#frame .content .messages ul li:nth-last-child(1) {
  margin-bottom: 20px;
}
#frame .content .messages ul li.sent img {
  margin: 6px 8px 0 0;
}
#frame .content .messages ul li.sent p {
  background: #435f7a;
  color: #f5f5f5;
}
#frame .content .messages ul li.replies img {
  float: right;
  margin: 6px 0 0 8px;
}
#frame .content .messages ul li.replies p {
  background: #f5f5f5;
  float: right;
}
#frame .content .messages ul li img {
  width: 400px;
  height:400px;
  float: left;
}
#frame .content .messages ul li p {
  display: inline-block;
  padding: 10px 15px;
  border-radius: 20px;
  max-width: 205px;
  line-height: 130%;
}
@media screen and (min-width: 735px) {
  #frame .content .messages ul li p {
    max-width: 300px;
  }
}
#frame .content .message-input {
  position: absolute;
  bottom: 0;
  width: 100%;
  z-index: 99;
}
#frame .content .message-input .wrap {
  position: relative;
}
#frame .content .message-input .wrap input {
  font-family: "proxima-nova",  "Source Sans Pro", sans-serif;
  float: left;
  border: none;
  width: calc(100% - 100px);
  padding: 11px 32px 10px 8px;
  font-size: 0.8em;
  color: #32465a;
}
@media screen and (max-width: 735px) {
  #frame .content .message-input .wrap input {
    padding: 15px 32px 16px 8px;
  }
}
#frame .content .message-input .wrap input:focus {
  outline: none;
}
#frame .content .message-input .wrap .attachment {
  position: absolute;
  right: 60px;
  z-index: 4;
  margin-top: 10px;
  font-size: 1.1em;
  color: #435f7a;
  opacity: .5;
  cursor: pointer;
}
@media screen and (max-width: 735px) {
  #frame .content .message-input .wrap .attachment {
    margin-top: 17px;
    right: 65px;
  }
}
#frame .content .message-input .wrap .attachment:hover {
  opacity: 1;
}
#frame .content .message-input .wrap button {
  float: right;
  border: none;
  width: 50px;
  padding: 12px 0;
  cursor: pointer;
  background: #32465a;
  color: #f5f5f5;
}
@media screen and (max-width: 735px) {
  #frame .content .message-input .wrap button {
    padding: 16px 0;
  }
}
#frame .content .message-input .wrap button:hover {
  background: #435f7a;
}
#frame .content .message-input .wrap button:focus {
  outline: none;
}
</style>
	</HEAD>
	<BODY class="sidebar-collapse"  leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 	<input type="hidden" name="<%=csdr.get_tokenName() %>" id="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	<input type="hidden" name="userid" id="userid" value="<%=userId %>" />
    
    
    <div id="frame">
	<div id="sidepanel">
		<div id="profile">
			<div class="wrap">
				<img id="profile-img" src="http://emilcarlsson.se/assets/mikeross.png" class="online" alt="" />
				<p>Mike Ross</p>
				
				
			</div>
		</div>
		<!-- <div id="search">
			<label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
			<input type="text" placeholder="Search contacts..." />
		</div> -->
		<div id="contacts">
			<ul id="contacts_detail">
			<% while(rskh.next()){%>
				
				<li class="contact" id="<%=rskh.getString("pk_seq") %>" value="<%=rskh.getString("pk_seq") %>" >
					<div class="wrap">
						<span class="contact-status online"></span>
						<img src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
						<div class="meta">
							<p class="name"><%=rskh.getString("ten") %></p>
							<p class="preview"><%=rskh.getString("message") %></p>
						</div>
					</div>
				</li>
				
				<%} rskh.close(); %>	
				
				
			</ul>
		</div>
		
	</div>
	<div class="content">
		<div class="contact-profile">
			<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
			<p>Harvey Specter</p>
			<div class="social-media">
				<i class="fa fa-facebook" aria-hidden="true"></i>
				<i class="fa fa-twitter" aria-hidden="true"></i>
				 <i class="fa fa-instagram" aria-hidden="true"></i>
			</div>
		</div>
		<div class="messages">
		<input type="hidden" id="user_chat" value="" />
			<ul id="msg_id">
				
				<!-- <li class="sent">
					<img src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
					<p>How the hell am I supposed to get a jury to believe you when I am not even sure that I do?!</p>
				</li>
				<li class="replies">
					<img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
					<p>When you're backed against the wall, break the god damn thing down.</p>
				</li> -->
				
			</ul>
		</div>
		<div class="message-input">
			<div class="wrap ">
		 
			<input class="msg_main" type="text" placeholder="Nội dung tin nhắn..." />
			<input  id="sampleFile" name="sampleFile" type="file"  accept="image/jpeg,image/png"   style="display: none;" />  		
			<div class="btn-group" role="group" aria-label="Basic example">
			 	<button class="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
			 	 <button id="OpenImgUpload"><i class="fa fa-paperclip" aria-hidden="true"></i></button>
				
			</div>
			
			</div>
		</div>
	</div>
	
	 <div id="myModal" class="modal">
        <span class="close">&times;</span>
        <img class="modal-content" id="img01">
       
    </div>
    
</div>
<script src='//production-assets.codepen.io/assets/common/stopExecutionOnTimeout-b2a7b3fe212eaa732349046d8416e00a9dec26eb7fd347590fbced3ab38af52e.js'></script><script src='https://code.jquery.com/jquery-2.2.4.min.js'></script>
<script >$(".messages").animate({ scrollTop: $(document).height() }, "fast");

$("#profile-img").click(function() {
	$("#status-options").toggleClass("active");
});

$(".expand-button").click(function() {
  $("#profile").toggleClass("expanded");
	$("#contacts").toggleClass("expanded");
});

$("#status-options ul li").click(function() {
	$("#profile-img").removeClass();
	$("#status-online").removeClass("active");
	$("#status-away").removeClass("active");
	$("#status-busy").removeClass("active");
	$("#status-offline").removeClass("active");
	$(this).addClass("active");
	
	if($("#status-online").hasClass("active")) {
		$("#profile-img").addClass("online");
	} else if ($("#status-away").hasClass("active")) {
		$("#profile-img").addClass("away");
	} else if ($("#status-busy").hasClass("active")) {
		$("#profile-img").addClass("busy");
	} else if ($("#status-offline").hasClass("active")) {
		$("#profile-img").addClass("offline");
	} else {
		$("#profile-img").removeClass();
	};
	
	$("#status-options").removeClass("active");
});

function newMessage() {
	
	message = $(".msg_main").val();

	if($.trim(message) == '') {
		return false;
	}

	userid = $("#userid").val();
	user_chat= $("#user_chat").val();
	Sendmsg(message,userid,user_chat);
	$('<li class="sent"><p>' + message + '</p></li>').appendTo($('.messages ul'));
	$('.message-input input').val(null);
	$('.contact.active .preview').html('<span>You: </span>' + message);
	$(".messages").animate({ scrollTop: $(document).height() }, "fast");
};


function Sendmsg(msg,userid,user_chat)
{
	
	  
	  var o = {};
 		 o['action'] ='msg';
 		 o['type'] ='0';
 		 o['msg'] = msg;
	  	 o['userid'] = userid;
	  	 o['user_chat'] = user_chat;
	     o['<%=Csrf.get_tokenName_Static() %>'] = $("#<%=Csrf.get_tokenName_Static() %>").val();

	         
   $.ajax({
  	 type:'POST',
       data : o ,
       url: '../../FirebaseChatSvl',
       success: function (data) {
          	if(data !='')
        	{
          	  console.log('data==='+data);
          	  var obj = JSON.parse(data);
          	
        	  console.log('status: '+obj.status);
        	  console.log('msg: '+obj.msg);
        	  
          		
        	}
       },

   });
}


$('.submit').click(function() {
  newMessage();
});

$(window).on('keydown', function(e) {
  if (e.which == 13) {
    newMessage();
    return false;
  }
});
//# sourceURL=pen.js
</script>
    
  
  <script src="https://www.gstatic.com/firebasejs/7.19.0/firebase-app.js"></script>

    <!-- If you enabled Analytics in your project, add the Firebase SDK for Analytics -->
    <script src="https://www.gstatic.com/firebasejs/7.19.0/firebase-analytics.js"></script>

    <!-- Add Firebase products that you want to use -->
    <script src="https://www.gstatic.com/firebasejs/7.19.0/firebase-auth.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.19.0/firebase-firestore.js"></script>
    <script src="https://www.gstatic.com/firebasejs/7.19.0/firebase-messaging.js"></script>

<script>
  // [START get_messaging_object]
  // Retrieve Firebase Messaging object.
  
 
  
  var firebaseConfig = {
		  apiKey: "AIzaSyCKMfKkflxzA6_aNfoZGW4nYmq-GGuizzY",
		    authDomain: "standard-7c774.firebaseapp.com",
		    databaseURL: "https://standard-7c774.firebaseio.com",
		    projectId: "standard-7c774",
		    storageBucket: "standard-7c774.appspot.com",
		    messagingSenderId: "265010975292",
		    appId: "1:265010975292:web:3d17fcab52692088fde939",
		    measurementId: "G-PYQCYDWVKP"
        };

        // Initialize Firebase
        firebase.initializeApp(firebaseConfig);
             
        const messaging = firebase.messaging();
        navigator.serviceWorker.register('./firebase-messaging-sw.js')
        .then((registration) => {
          messaging.useServiceWorker(registration);
          //messaging = firebase.messaging().useServiceWorker(registration);
          messaging.usePublicVapidKey('BBZdxJwZ5KXf6eJxKa74lHjI_ql6TXx4p5K71JbjcBFXV8yHO4vUBMlvpIibvOfcB8FCqLbME6vLgJ_h1D81_6E');
          // Request permission and get token.....
      	

	  messaging.onTokenRefresh(() => {
      messaging.getToken().then((refreshedToken) => {
      console.log('Token refreshed.');
      sendTokenToServer(refreshedToken);
      resetUI();
    
      }).catch((err) => {
      console.log('Unable to retrieve refreshed token ', err);
      showToken('Unable to retrieve refreshed token ', err);
    });
  });
  // [END refresh_token]
  
       
       
  messaging.onMessage((payload) => {
    console.log('Message received chatSVL. ', payload);
    var obj = JSON.parse( JSON.stringify(payload));
    console.log('Message received data. ', obj.data);
    appendMessage(obj.data);
  });

       

  function resetUI() {
    showToken('loading11...');
    messaging.getToken().then((currentToken) => {
      if (currentToken) {
    	
    	  console.log('send token to SV22 111.'+currentToken);
        sendTokenToServer(currentToken);
        updateUIForPushEnabled(currentToken);
      } else {

        console.log('No Instance ID token available. Request permission to generate one.');
        updateUIForPushPermissionRequired();
        setTokenSentToServer(false);
      }
    }).catch((err) => {
      console.log('An error occurred while retrieving token. ', err);
      setTokenSentToServer(false);
    });
    // [END get_token]
  }


  function showToken(currentToken) {
    // Show token in console and UI.
      console.log('my token 1 '+currentToken);
  }
        


function sendTokenToServer(currentToken) {
    if (!isTokenSentToServer()) {
      console.log('Sending token to server...');
      // TODO(developer): Send the current token to your server.
      setTokenSentToServer(true);
    } else {
      console.log('Token already sent to server so won\'t send it again 111 ' +
          'unless it changes');
    }

  }

  function isTokenSentToServer() {
    return window.localStorage.getItem('sentToServer') === '1';
  }

  function setTokenSentToServer(sent) {
    window.localStorage.setItem('sentToServer', sent ? '1' : '0');
  }
 

  // Add a message to the messages element.
  function appendMessage(payload) {
	  var obj = JSON.parse( JSON.stringify(payload));
	  console.log(obj.title);
	  console.log("my body "+obj.body);
	  console.log("my type "+obj.type);
	
	  
	   console.log("user click " +$("#user_chat").val());
	  console.log("user push " +obj.title);
	if($("#user_chat").val()==obj.title)
		{ 
		  if(obj.type==0)
			  {
				  $('#msg_id').append('<li class="replies">' +
			              '       <p> ' + obj.body + '</p>' +
			              '</li>');
			  }
		  else
			  {
			  $('#msg_id').append('<li class="replies">' +
		              '      <img onclick="ViewImage(this)"  src= "'+ obj.body+'"> ' +
		              '</li>');
			  }
	
		
		}
	else
	{
	
		var target = document.getElementById(obj.title);
		var wrap = document.createElement('div');
		wrap.appendChild(target.cloneNode(true));
		$('#'+obj.title).remove();
		
		$('#contacts_detail').prepend(wrap.innerHTML);
	
			
		$('#'+obj.title).find('.preview').empty();
		 if(obj.type==0)
			 {
				 $('#'+obj.title).append("<p class=\"preview\">"+ obj.body+"</p>");
			 }
		 else
			 {
			 $('#'+obj.title).append("<p class=\"preview\">có một ảnh mới</p>");
			 }
		
	} 
	 
  }
  
  

  function deleteToken() {
      messaging.getToken().then((currentToken) => {
       messaging.deleteToken(currentToken).then(() => {
         console.log('Token deleted.');
         setTokenSentToServer(false);
         resetUI();
         // [END_EXCLUDE]
       }).catch((err) => {
         console.log('Unable to delete token.222 ', err);
       });
       // [END delete_token]
     }).catch((err) => {
       console.log('Error retrieving Instance ID token. ', err);
       showToken('Error retrieving Instance ID token. ', err);
     });

   }
  
  resetUI();
        
 });
        
 
          function showToken(currentToken) {
            // Show token in console and UI.
           
            console.log('my token ', currentToken); 
          }
          
     
        function setTokenSentToServer(sent) {
            window.localStorage.setItem('sentToServer', sent ? '1' : '0');
          }
        
      
        
        function sendTokenToServer(currentToken) {
            if (!isTokenSentToServer()) {
              console.log('Sending token to server1 ...');
              // TODO(developer): Send the current token to your server.
              setTokenSentToServer(true);
            } else {
              console.log('Token already sent to server so won\'t send it again ' +
                  'unless it changes');
            }

          }

          function isTokenSentToServer() {
            return window.localStorage.getItem('sentToServer') === '1';
          }
          
          function updateUIForPushEnabled(currentToken) {
        	  
        	    showToken(currentToken);
        	  }
        
         
</script>




<script type="text/javascript">

$(function(){
	  $('#sampleFile').change(function(){
		 
	    var input = this;
	    var url = $(this).val();
	    var ext = url.substring(url.lastIndexOf('.') + 1).toLowerCase();
	    if (input.files && input.files[0]&& (ext == "gif" || ext == "png" || ext == "jpeg" || ext == "jpg")) 
	     {		
	    	userid = $("#userid").val();
	    	user_chat= $("#user_chat").val();
	    	
	    	 var reader = new FileReader();
	         reader.onload = function (e) {

			       var o = {};
		    	 o['action'] ='msg';
		    	 o['type'] ='1';
		    	  	 o['msg'] = e.target.result;
		    	  	 o['userid'] = userid;
		    	  	 o['user_chat'] = user_chat;
		    	  	 o['<%=Csrf.get_tokenName_Static() %>'] = $("#<%=Csrf.get_tokenName_Static() %>").val();

		       $.ajax({
		      	 type:'POST',
		           data : o ,
		           url: '../../FirebaseChatSvl',
		           success: function (data) {
		              	if(data !='')
		            	{
		              	  console.log('data==='+data);
		              	  var obj = JSON.parse(data);
		              	
		            	  console.log('status: '+obj.status);
		            	  console.log('msg: '+obj.msg);
		            	  if(obj.status==1)
		            		  {
			            		  $('<li class="sent"> <img  onclick="ViewImage(this)" src="' + obj.msg + '"> </li>').appendTo($('.messages ul'));
				            		$('.message-input input').val(null);
				            		$('.contact.active .preview').html('<span>You: </span>' + message);
				            		$(".messages").animate({ scrollTop: $(document).height() }, "fast");

		            		  }
		            	  	
		              		
		            	}
		           },

		       });
	         }
	       
	         reader.readAsDataURL(input.files[0]);
	    	
	    }
	    else
	    {

	    	alert('Chỉ gửi được file ảnh');
	    	
	    }
	  });

	});

	$(document).on('click','.contact',function (event) {
  
		$('#'+$(this).val()).find('.preview').empty(); 
		 $("#user_chat").val($(this).val()); 
     	 var o = {};
    	 o['action'] ='init_data';
    	 o['msg'] = $(this).val();
    	 o['userid'] = $("#userid").val(); 
    	 o['<%=Csrf.get_tokenName_Static() %>'] = $("#<%=Csrf.get_tokenName_Static() %>").val();
        $.ajax({
      	 type:'POST',
           data : o ,
           url: '../../FirebaseChatSvl',
           success: function (data) {
              	if(data !='')
            	{              	
              	  $("#msg_id").empty();
              	  $('#msg_id').append(data); 		
              		window.scrollTo(0,document.body.scrollHeight);
            	}
              	else
              	{
              	  $("#msg_id").empty();
              	}
           },

       }); 
     	
    });
	
	
	$('#OpenImgUpload').click(function () { $('#sampleFile').trigger('click'); });
	
	

	 function ViewImage(img) {
         var modal = document.getElementById("myModal");

         // Get the image and insert it inside the modal - use its "alt" text as a caption

         var modalImg = document.getElementById("img01");
         modal.style.display = "block";
         modalImg.src = img.src;

         // Get the <span> element that closes the modal
         var span = document.getElementsByClassName("close")[0];

         // When the user clicks on <span> (x), close the modal



         modal.onclick = function (event) {
             if (event.target.id != 'img01') {
                 modal.style.display = "none";
             }

         }
         }

	
</script>


    
</body>
</html>

<%}%>