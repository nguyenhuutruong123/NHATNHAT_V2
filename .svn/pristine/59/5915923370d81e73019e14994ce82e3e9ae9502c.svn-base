<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page  import = "geso.dms.center.util.*" %>
<%@ page  import = "geso.dms.center.servlets.count.*" %>
<%	String userId = (String) session.getAttribute("userId");  
	String userTen = (String) session.getAttribute("userTen");  	
	String sum = (String) session.getAttribute("sum");
	Utility util = (Utility) session.getAttribute("util");
	if(!util.check(userId, userTen, sum)){
		response.sendRedirect(request.getContextPath() + "/redirect.jsp");
	}else{
		
		
		String nnId = (String)session.getAttribute("nnId"); 
		if(nnId == null) {
			nnId = "vi"; 
		}
		
		%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"> <% Utility Utilback = new Utility(); %>
<HTML>
<HEAD>
<TITLE>&nbsp;</TITLE>

<META http-equiv="Content-Type" content="text/html; charset=utf-8">

<META http-equiv="Content-Style-Type" content="text/css">

<LINK rel="stylesheet" href="../css/main.css" type="text/css">
<script src="../scripts/jquery-1.8.1.js" type="text/javascript"></script>
<SCRIPT type="text/javascript">
 function submitform()
{
    document.forms["logoutForm"].submit();
}
</SCRIPT>

<script type="text/javascript">  
var flag = true;
function abc()
{
	flag = false;
}

function confirmMe()
{
if(flag)
	 {
		$(window).bind('unload', function(){
		   $.ajax({
		    cache: false,
		    async: false,
		    dataType: "script",
		        url: "../../ThongbaoAjax"
		    });
		}); 
	 }

}
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
      	  
       
       
  messaging.onMessage((payload) => {
    console.log('Message received. ', payload);
    var obj = JSON.parse( JSON.stringify(payload));
    appendMessage(obj.notification);
  });

       

  function resetUI() {
    showToken('loading11...');
    messaging.getToken().then((currentToken) => {
    	document.getElementById("firebase").value = currentToken;
      if (currentToken) {   	
      	   sendTokenToServer(currentToken);  
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
      console.log(currentToken);
  }
        


function sendTokenToServer(currentToken) {
    if (!isTokenSentToServer()) {
          setTokenSentToServer(true);
    	   console.log('token crrent'+currentToken);
    	   
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
	  console.log(obj.body);
	  console.log(' here '+obj.body);
	//  document.getElementById("inputmessage").value  = obj.body;
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
  
  
  
  function Sendmsg()
  {
	 
  	var firebase=document.getElementById("firebase").value ;
  	var userid=document.getElementById("userid").value ;
  	if(firebase.length>0)
  		{
	  		 var o = {};
	  	  	 o['action'] ='updatetoken';
	  	  	  	 o['firebase'] = firebase;
	  	  	  	 o['userid'] = userid;
	  	  	  o['<%=Csrf.get_tokenName_Static() %>'] = $("#<%=Csrf.get_tokenName_Static() %>").val();
	
	  	     $.ajax({
	  	    	 type:'POST',
	  	         data : o ,
	  	         url: '../../FirebaseChatSvl',
	  	         success: function (data) {
	  	           if(data !='')
	  	          	{
	  	        	 console.log('data====='+data)
	  	            	if(data=='CRNEW')	
	  	            		{  			
	  	            			deleteToken();
	  	            			document.getElementById("firebase").value='';
	  	            			Sendmsg();
	  	            		}
	  	        	 
	  	          	}
	  	         },
	
	  	     });
	  	     
	  	     return;
  		}
  	
  	setTimeout(Sendmsg, 300);
  }
  
  Sendmsg();  
        
 });
      
        
       
</script>




</HEAD>
<BODY leftmargin="0" bottommargin="0" topmargin="0" rightmargin="0"  onbeforeunload="confirmMe()">
<form name="logoutForm" method="post" action="../../LogoutSvl">
<div id="dataDisplay"></div>
<input type="hidden" name="firebase"  id="firebase" value="" />
<input type="hidden" name="userid"  id="userid" value="<%=userId %>" />
<% geso.dms.center.util.Csrf csdr=new geso.dms.center.util.Csrf(request,response,true,false,true);%>
 	<input type="hidden" name="<%=csdr.get_tokenName() %>" id="<%=csdr.get_tokenName() %>" value='<%=csdr.get_tokenValue() %>'>
	
	
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0" height="100%">
	<TR bgcolor="#FFFFFF">
		<TD style="width:80%;" align="right" valign="middle">
		<table width="100%" height="100%">
		<TR>
		<TD>
			
			<img src="../images/salesup.jpg" style="top:8px; left:70%; position:absolute;display:flex;height:82%;">
		</TD>
		</table>
		</TD>
		<TD style="width:7%;" align="center" valign="middle" class="blanc">
		<%if(ChuyenNgu.getChuyenNguMode()){ %>
			<A href="../../ChangeLanguageTTSvl?Id=vi&userId=<%= userId %>&userTen=<%= userTen %>" target="_parent" style="font-weight: bold;">
				<IMG src = "../images/vietnamflag.png" heigth="14" width="19"> </A>								
			<A href="../../ChangeLanguageTTSvl?Id=en&userId=<%= userId %>&userTen=<%= userTen %>" target="_parent" style="font-weight: bold;">
				<IMG src = "../images/en_flag.png" heigth="14" width="19"> </A> 
			<%} %>
		</TD>
		
		<TD>
			<A href="../../LogoutSvl" style="font-weight: bold" target="_parent" ><%=ChuyenNgu.get("Đăng xuất",nnId) %></A>
		</TD>
		<TD>
			<A href="../../LoginSvl" style="font-weight: bold" onclick = "abc();" target="_parent" ><%=ChuyenNgu.get("Đổi mật khẩu",nnId) %></A>
		</TD>
	</TR>	
</TABLE>


<%-- 
<TABLE width="100%" border="0" cellspacing="0" cellpadding="0"
	height="100%">

	<TR bgcolor="#FFFFFF">
		<TD width="25%"  align="right" valign="middle">
			
			<img src="../images/salesup.jpg" width="100" height="40" align="right"> &nbsp;&nbsp;
			<img src="../images/dms.jpg" height="30" align="right" style=" padding-top: 10px; display:none; " /> &nbsp;
		</TD>
		
		<TD style="width:7%;" align="center" valign="middle" class="blanc">
			<A href="../../ChangeLanguageTTSvl?Id=vi&userId=<%= userId %>&userTen=<%= userTen %>" target="_parent" style="font-weight: bold;">
				<IMG src = "../images/vietnamflag.png" heigth="14" width="19"> </A>								
			<A href="../../ChangeLanguageTTSvl?Id=en&userId=<%= userId %>&userTen=<%= userTen %>" target="_parent" style="font-weight: bold;">
				<IMG src = "../images/en_flag.png" heigth="14" width="19"> </A> 
			
		</TD>
		
		<TD align="right" valign="middle" class="blanc" >
		<A href="../../LogoutSvl" target="_parent" >Đăng xuất&nbsp;&nbsp;</A>
		<A href="../../LoginSvl" onclick = "abc();" target="_parent" >Đổi mật khẩu &nbsp;&nbsp;</A>
		<div><iframe style="width: 200px ;height: 20px" id = "frame1" src = "counter.jsp" frameborder="0" scrolling="no">
        </iframe></div>
		</TD>
		
	</TR>	

</TABLE> --%>
</form>
</BODY>
</HTML>
<%} %>
