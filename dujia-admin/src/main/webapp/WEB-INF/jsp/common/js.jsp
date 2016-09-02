<%@ include file="taglibs.jsp"%>
<script type="text/javascript" src="${_domain}/js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="${_domain}/js/jquery-ui-1.8.custom.min.js"></script>
<script type="text/javascript" src="${_domain}/js/singleCalendar/WdatePicker.js"></script>
<script type="text/javascript" src="${_domain}/js/singleCalendar/WdateSelectData.js"></script>
<script type="text/javascript" src="${_domain}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${_domain}/js/tableConverter.js"></script>
<script type="text/javascript" src="${_domain}/js/model-alert1.js"></script>
<script type="text/javascript" src="${_domain}/js/json2.js"></script>
<script type="text/javascript">
$(document).ready(function () {	
	$('#nav dl').css({"display":"none"})
	$('#nav li').hover(
		function () {
			$(this).addClass("now");
			$('dl', this).show();
		}, 
		function () {
			$(this).removeClass("now");
			$('dl', this).hide();			
		}
	);
});
</script>