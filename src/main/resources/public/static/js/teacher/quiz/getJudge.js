function getJudge(id){

	$.ajax({
		            type: 'POST',
		            url: 'getJudge',
		            cache: false,
					dataType: "json",
		            data: {
		                judgeId: id
		            },
		            success: function (data) {
						if(data.entity.quiz != null && data.entity.quiz.grade != null){
							$("#findJudge_grade").val(data.entity.quiz.grade.gradeName);
						}
						if(data.entity.quiz != null && data.entity.quiz.subject != null){
							$("#findJudge_subject").val(data.entity.quiz.subject.subjectName);
						}
						if(data.entity.quiz != null){
							$("#findJudge_quiz").val(data.entity.quiz.quizName);
						}
						$("#findJudge_question").val(data.entity.question);
						$("#findJudge_answer").val(data.entity.answer);
						if(data.entity.quiz != null){
							$("#findJudge_score").val(data.entity.quiz.judgeScore);
						}
		            }
		        });
		        
}