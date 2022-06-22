<!DOCTYPE html>
<html lang="en">
<head>
<title>jQuery Text Over Demo</title>
<meta http-equiv="Content-type" content="text/html;charset=UTF-8" />
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="../scripts/jquery.textover.min.js"></script>
<script type="text/javascript">
  jQuery(function($){

    var textover_api;

    // How easy is this??
    $('#target').TextOver({}, function() {
      textover_api = this;
    });

    $('#show').click(function () {
      html = '';
      $.each(textover_api.getData(), function() {
        html += 'Text &raquo; ' + this.text + ' Left &raquo; ' + this.left + ' Top &raquo; ' + this.top + '<br />';
      });
      $('#data').html(html).show();
    });

  });

</script>
<link rel="stylesheet" href="../css/demos.css" type="text/css" />
</head>
<body>
<div id="jquery-script-menu">
<div class="jquery-script-center">
<ul>
<li><a href="http://www.jqueryscript.net/other/jQuery-Plugin-To-Add-Text-Notes-Over-The-Images-textover.html">Download This Plugin</a></li>
<li><a href="http://www.jqueryscript.net/">Back To jQueryScript.Net</a></li>
</ul>
<div class="jquery-script-ads"><script type="text/javascript"><!--
google_ad_client = "ca-pub-2783044520727903";
/* jQuery_demo */
google_ad_slot = "2780937993";
google_ad_width = 728;
google_ad_height = 90;
//-->
</script>
<script type="text/javascript"
src="http://pagead2.googlesyndication.com/pagead/show_ads.js">
</script></div>
<div class="jquery-script-clear"></div>
</div>
</div>
<div class="container" style="margin-top:150px;">
<div class="row">
<div class="span11">
<div class="demo-box">
<div class="page-header">
<h1>jQuery Text Over Demo</h1>
</div>
<img src='../images/Hoadon.jpg' id="target" alt="[Text Over Example]" />
<div id="data" class="well"></div>
<button id="show">Show Data</button>
<div class="clearfix"></div>
</div>
</div>
</div>
</div>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>
</body>
</HTML>
