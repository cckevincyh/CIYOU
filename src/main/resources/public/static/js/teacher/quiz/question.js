
function question(id){
	var question = $.trim($("#question").val()) +"?quizId="+ id;
	
	 window.location.href = question;
}
