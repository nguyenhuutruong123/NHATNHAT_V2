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
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> <% Utility Utilback = new Utility(); %>
	<HTML>
	<HEAD>
	<title><%= getServletContext().getInitParameter("TITLENAME") %></title>
	<META http-equiv="Content-Style-Type" content="text/css">
	<link href="https://fonts.googleapis.com/css?family=Roboto&display=swap" rel="stylesheet"> 
	<link rel="stylesheet" href="../css/main.css" type="text/css">
	 <!-- Font Awesome -->
     <link rel="stylesheet" href="../themes/plugins/fontawesome-free/css/all.min.css" type="text/css">
     <!-- OverlayScrollbars -->
     <link rel="stylesheet" href="../themes/plugins/overlayScrollbars/css/OverlayScrollbars.min.css" type="text/css">
 	 <!-- Ionicons -->
 	 <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css"> -->
     <!-- Theme style -->
     <link rel="stylesheet" href="../themes/dist/css/adminlte.min.css" type="text/css">
  	 <!-- Google Font: Source Sans Pro -->
  	<!--  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" /> -->
  	
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
	</HEAD>
	<BODY class="sidebar-collapse" onload="goiham('<%=userId%>', 'xxx')" leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0">
	
	<nav style="margin: 1px 3px 1px 3px; height:12px !important;" class="main-header navbar navbar-expand navbar-white navbar-light">
    <!-- Left navbar links -->
    <ul class="navbar-nav">
     <!-- <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
      </li> -->
      </ul>

    <!-- Right navbar links -->
    <ul style="padding-top: 15px;" class="navbar-nav ml-auto">
      <!-- Messages Dropdown Menu -->
      
      <li class="nav-item dropdown user-menu">
      <div id ="chuadoc"></div>
      </li>
		
	 <!--  <li class="nav-item dropdown user-menu">	
      <div id = "like_button_container"></div>
      </li> -->
      
      <li class="nav-item dropdown user-menu">
      	<span class="d-none d-md-inline"><%=Utility.GLanguage("Chào mừng bạn",session,jedis) %>  <%=userTen %></span>
      </li>
      </ul>
  </nav>
  
  <!-- <div id="chuadoc">
  </div> -->
  
  <!-- jQuery -->
    <script src="../themes/plugins/jquery/jquery.min.js"></script> 
    <!-- jQuery UI 1.11.4 -->
    <script src="../themes/plugins/jquery-ui/jquery-ui.min.js"></script>
    <!-- Bootstrap 4 -->
    <script src="../themes/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>       
    <!-- overlayScrollbars -->
    <script src="../themes/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js"></script>
	<!-- FastClick -->
	<script src="../themes/plugins/fastclick/fastclick.js"></script>
	<!-- AdminLTE App -->
	<script src="../themes/dist/js/adminlte.js"></script>
	<script src="https://kit.fontawesome.com/ff58e95563.js"></script>
	<script type="text/javascript">
		var ma,chuoi;
		function goiham(id,st)
		{
			ma = id;
			chuoi =st;
			goi();
		}
		function goi()
		{
			ajaxOption(ma, chuoi);
			setTimeout(goi, 300000);
		}
		function ajaxOption(id, str)
		{
			var xmlhttp;
			if (window.XMLHttpRequest)
			{// code for IE7+, Firefox, Chrome, Opera, Safari
			   xmlhttp = new XMLHttpRequest();
			}
			else
			{// code for IE6, IE5
			   xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange=function()
			{
			   if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
			   {
				   var dl=xmlhttp.responseText;
		
				   dl=dl.replace('id="thongbao" style="display:none"', 'id="thongbao"');
				   dl=dl.replace('style=""', 'style="display:none"');
			       document.getElementById('chuadoc').innerHTML = dl;
			   }
			}
			xmlhttp.open("POST","../../ThongbaoAjax?q=" + str + "&id=" + id,true);
			xmlhttp.send();
		}
	</script>  
	 
	 <!--
	  <script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>  
	  <script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
	  
 	 <script type="text/babel">
		const e = React.createElement;
		class LikeButton extends React.Component {
  			constructor(props) {
    			super(props);
    			this.state = { liked: false };
  			}
  render() {
    if (this.state.liked) {
      return 'You liked this. Yahoodsdsddsdssdsdsdsdsdaaaa !';
    }

    return e(
      'button',
      { onClick: () => this.setState({ liked: true }) },
      'Like'
    );
  }
}

const domContainer = document.querySelector('#like_button_container');
ReactDOM.render(e(LikeButton), domContainer);	</script>
 	 <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>-->
	</BODY>
	</HTML>

<%}%>