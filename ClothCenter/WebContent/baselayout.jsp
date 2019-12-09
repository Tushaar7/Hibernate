<!DOCTYPE html>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
<tiles:insertAttribute name="title" ignore="true" />
</title>

	<meta charset="utf-8" />
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->        
	<link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="assets/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	
	<link href="assets/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="assets/css/themes/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->
	
		<link rel="stylesheet" type="text/css" href="assets/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
	
	<!-- BEGIN PAGE LEVEL Datatable STYLES -->
	<link rel="stylesheet" type="text/css" href="assets/plugins/select2/select2_metro.css" />
	<link rel="stylesheet" href="assets/plugins/data-tables/DT_bootstrap.css" />
	<!-- END PAGE LEVEL STYLES -->
	
	
	<!-- BEGIN PAGE LEVEL STYLES --> 
	<link href="assets/css/pages/tasks.css" rel="stylesheet" type="text/css" media="screen"/>
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="favicon.ico" />
	
	<!-- BEGIN PAGE LEVEL STYLES for form wizard-->
	<link rel="stylesheet" type="text/css" href="assets/plugins/chosen-bootstrap/chosen/chosen.css" />
	<!-- END PAGE LEVEL STYLES -->
	
<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->   
	<script src="assets/plugins/jquery-1.10.1.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="assets/plugins/bootstrap-hover-dropdown/twitter-bootstrap-hover-dropdown.min.js" type="text/javascript" ></script>
	<!--[if lt IE 9]>
	<script src="assets/plugins/excanvas.min.js"></script>
	<script src="assets/plugins/respond.min.js"></script>  
	<![endif]-->   
	<script src="assets/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="assets/plugins/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="assets/plugins/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="assets/plugins/uniform/jquery.uniform.min.js" type="text/javascript" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/textUtil.js"></script>				<!-- Text Utility eg. UpperCase -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.validate.min.js"></script>	<!-- Validate Page  -->
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/validateMethods.js"></script>		<!-- Added validation Methods  -->
	
	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL SCRIPTS -->
	<!-- <script src="assets/scripts/app.js" type="text/javascript"></script>
	<script src="assets/scripts/index.js" type="text/javascript"></script>
	<script src="assets/scripts/tasks.js" type="text/javascript"></script>  -->       
	<!-- END PAGE LEVEL SCRIPTS -->  
	
	
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<script type="text/javascript" src="assets/plugins/select2/select2.min.js"></script>
	<script type="text/javascript" src="assets/plugins/data-tables/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="assets/plugins/data-tables/DT_bootstrap.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	
		<script src="assets/scripts/form-components.js"></script>     
		<script type="text/javascript" src="assets/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
	
	<!-- BEGIN PAGE LEVEL SCRIPTS -->
<!-- 	<script src="assets/scripts/app.js"></script>
 -->	<script src="assets/scripts/table-advanced.js"></script>     
	<script>
		jQuery(document).ready(function() {       
		   App.init();
		   TableAdvanced.init();
		   FormWizard.init();
		  
		});
	</script>
	
	
	<!-- END JAVASCRIPTS -->   
	<!-- END JAVASCRIPTS -->
	
		<!-- BEGIN PAGE LEVEL PLUGINS for form wizard -->
	
	<script type="text/javascript" src="assets/plugins/jquery-validation/dist/jquery.validate.min.js"></script>
	<script type="text/javascript" src="assets/plugins/jquery-validation/dist/additional-methods.min.js"></script>
	<script type="text/javascript" src="assets/plugins/bootstrap-wizard/jquery.bootstrap.wizard.min.js"></script>
	<script type="text/javascript" src="assets/plugins/chosen-bootstrap/chosen/chosen.jquery.min.js"></script>
	<!-- END PAGE LEVEL PLUGINS -->
	<!-- BEGIN PAGE LEVEL SCRIPTS for form wizard -->
 	<script src="assets/scripts/app.js"></script>
	<script src="assets/scripts/form-wizard.js"></script>     
	<!-- END PAGE LEVEL SCRIPTS -->
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.datetimepicker.full.js"></script>
<link type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css" rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.datepick.js"></script> 
<script type="text/javascript">

$(function() {
		$(".dateSelect").datepick({
			dateFormat : 'dd-mm-yy',
			yearRange : '2020:1900',
			showTrigger : '#calImg'
		});
	});
</script>
	
</head>
<body>
<div id="mainContainer">
	<div style="width: 100%; margin:0 0 -50px 0; float:left; ">
		<tiles:insertAttribute name="header" />
	</div>
	<div style="width: 100%; margin:10px 0px 0px 0px; float:left;">
		<tiles:insertAttribute name="menu" />
	</div>	
	<div class="container" style="width: 100%; margin:0; float:left;">
		<div class="errors" id="message" style="width: 400px; display: none;"></div>
		
		<tiles:insertAttribute name="body" />
	</div>
</div>
	<div style="position: fixed; bottom: 0px; width: 100%; background: silver;">
		<tiles:insertAttribute name="footer" />
	</div>
	<script type="text/javascript">
	
	</script>
</body>
</html>

