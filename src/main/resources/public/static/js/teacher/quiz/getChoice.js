function getChoice(id){

	$.ajax({
		            type: 'POST',
		            url: 'getChoice',
		            cache: false,
					dataType: "json",
		            data: {
		                choiceId: id
		            },
		            success: function (data) {
						if(data.entity.quiz != null && data.entity.quiz.grade != null){
							$("#findChoice_grade").val(data.entity.quiz.grade.gradeName);
						}
						if(data.entity.quiz != null && data.entity.quiz.subject != null){
							$("#findChoice_subject").val(data.entity.quiz.subject.subjectName);
						}
						if(data.entity.quiz != null){
							$("#findChoice_quiz").val(data.entity.quiz.quizName);
						}
						$("#findChoice_question").val(data.entity.question);
						$("#findOptionA").val(data.entity.optionA);
						$("#findOptionB").val(data.entity.optionB);
						$("#findOptionC").val(data.entity.optionC);
						$("#findOptionD").val(data.entity.optionD);
						$("#findChoice_answer").val(data.entity.answer);
						if(data.entity.quiz != null){
							$("#findChoice_score").val(data.entity.quiz.choiceScore);
						}
		            }
		        });
		        
}