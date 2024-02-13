<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "s" uri = "/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css"
         rel = "stylesheet">
      <script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
      <script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/JavaScript">

$(document).ready(function(){
	$("#loannumber").focusout(function(){
		doLookup();
	});
	
	  $("#loannumber").on('keypress',function(e) {
		    if(e.which == 13) {
		    	
		    	doLookup();   
		    }
		});

		function doLookup()
		{
			$('#errMsg').html("");
	    	var blink = document.getElementById('blink');
	    	$('#blink').html("Please wait..");
	        var interval=setInterval(function() {
	            blink.style.opacity = (blink.style.opacity == 0 ? 1 : 0);
	        }, 400);
	    	$.ajax({
	    		type : "GET",
	            url: 'lookupapi',
	            dataType : 'json',
	            'data':{'loanNumber' : $("#loannumber").val()},
	            success : function(result){
	            	//{alert("Success");}
	               $('#wirefee').val("$" + result.wireFee);	
	               $('#satisfactionfee').val("$" + result.satisfactionFee);
	               $('#payofffee').val("$" + result.payoffFee);	
	               $('#nsffee').val("$" + result.nsfFee);
	               clearInterval(interval);
	               $('#blink').html("");
	        },
	        error : function(jqXHR, errmsg) {
				//alert(errmsg);console.log(xhr)}
	        	var msg = '';
                if (jqXHR.status === 0) {
                    msg = 'Not connect.\n Verify Network.';
                } else if (jqXHR.status == 404) {
                    msg = 'Bad LoanNumber or bad request';
                } else if (jqXHR.status == 500) {
                    msg = 'Internal Server Error [500].';
                } else if (exception === 'parsererror') {
                    msg = 'Requested JSON parse failed.';
                } else if (exception === 'timeout') {
                    msg = 'Time out error.';
                } else if (exception === 'abort') {
                    msg = 'Ajax request aborted.';
                } else {
                    msg = 'Uncaught Error.\n' + jqXHR.responseText;
                }
                console.log(jqXHR.status);
                console.log(msg);
                $('#errMsg').html(msg);
                $('#wirefee').val("");
                $('#satisfactionfee').val("");
                $('#payofffee').val("");
                $('#nsffee').val("");
                clearInterval(interval);
		    	$('#blink').html("");
		    	
		    	
            }
	        
	      });	
			}
	});
		
  </script> 
<title>Feelookup</title>
<style type="text/css">
.tbl {
    width:300px;
    height:200px;
    position:fixed;
    margin-left:-150px; /* half of width */
    margin-top:-150px;  /* half of height */
    top:30%;
    left:50%;
    border-collapse: separate;
    border-spacing: 0 1em;
};

[data-tip] {
  position:relative;

}
[data-tip]:before {
  content:'';
  /* hides the tooltip when not hovered */
  display:none;
  content:'';
  display:none;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  border-bottom: 5px solid #1a1a1a;
  position:absolute;
  top:30px;
  left:35px;
  z-index:8;
  font-size:0;
  line-height:0;
  width:0;
  height:0;
  position:absolute;
  top:30px;
  left:35px;
  z-index:8;
  font-size:0;
  line-height:0;
  width:0;
  height:0;
}
[data-tip]:after {
  display:none;
  content:attr(data-tip);
  position:absolute;
  top:155px;
  left:150px;
  padding:5px 8px;
  background:#1a1a1a;
  color:#fff;
  z-index:9;
  font-size: 0.75em;
  height:18px;
  line-height:18px;
  -webkit-border-radius: 3px;
  -moz-border-radius: 3px;
  border-radius: 3px;
  white-space:nowrap;
  word-wrap:normal;
}
[data-tip]:hover:before,
[data-tip]:hover:after {
  display:block;
}
}
</style>
</head>
<body id="pgeBody" style="background-color:powderblue;">


	<table class = "tbl">
		<colgroup>

	    <col span="2" />
	  </colgroup>
	  
	  <tr>
	  	<td><td style="text-align: left" colspan="2" rowspan ="3"><img id="logo" src="OcwenLogo.jpg"  width="100" height="90" ></td>
	  </tr>
	  <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr> <tr><td></td></tr><tr><td></td></tr>	
	  
		<tr>
	  		<td ><label for="loannumber">Loan Number:</label></td>
	  		<td>
	  			<div data-tip="Enter 10 digit Loan Number and press the 'Enter' or 'Tab' key">
	  			<input type="text" id="loannumber" name="loannumber">
	  			</div>
	  		</td>
	  	</tr>
	  <tr>
	  	<td><label for="nsffee">NSF Fee:</label></td>
	  	<td><input type="text" id="nsffee" name="nsffee" readonly></td>
	  </tr>
	  <tr>
	  	<td><label for="payofffee">Payoff Fee:</label></td>
	  	<td><input type="text" id="payofffee" name="payofffee" readonly></td>
	  </tr>
	  <tr>
	  	<td><label for="satisfactionfee">Satisfaction Fee:</label></td>
	  	<td><input type="text" id="satisfactionfee" name="satisfactionfee" readonly></td>
	  </tr>
	  <tr>
	  	<td><label for="wirefee">Wire Fee:</label></td>
	  	<td><input type="text" id="wirefee" name="wirefee" readonly></td>
	  </tr>
	  
	  <tr>
	  	<td style="text-align: center" colspan="2"><label id="blink" ></label></td>
	  </tr>
	  <tr>
	  	<td style="text-align: center" colspan="2"><label id="errMsg" class="errMsg"></label></td>
	  </tr>
	</table>
	<br><br>
	
	</body>
</html>