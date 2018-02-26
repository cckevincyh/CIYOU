function getQuiz(id){

	$.ajax({
		            type: 'POST',
		            url: 'getQuizById',
		            cache: false,
					dataType: "json",
		            data: {
		                quizId: id
		            },
		            success: function (data) {
		            	$("#findQuizName").val(data.entity.quizName);
						if(data.entity.grade != null){
							$("#findGrade").val(data.entity.grade.gradeName);
						}
						if(data.entity.subject != null){
							$("#findSubject").val(data.entity.subject.subjectName);
						}
						if(data.entity.teacher != null){
							$("#findTeacher").val(data.entity.teacher.name);
						}
						$("#findQuizTime").val(data.entity.quizTime + " 分钟");
						$("#findChoiceScore").val(data.entity.choiceScore + " 分");
						$("#findJudgeScore").val(data.entity.judgeScore + " 分");
						$("#findChoiceNum").val(data.entity.choiceNum + " 个");
						$("#findJudgeNum").val(data.entity.judgeNum + " 分");
						$("#findAllScore").val(data.entity.allScore + " 分");
		            }
		        });
		        
}