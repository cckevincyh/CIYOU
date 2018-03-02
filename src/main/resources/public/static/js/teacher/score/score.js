
function score(id){
	var score_action = $.trim($("#score_action").val()) +"?scoreId="+ id;
	 window.location.href = score_action;
}
