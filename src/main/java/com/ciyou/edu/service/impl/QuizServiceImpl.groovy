package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Answer
import com.ciyou.edu.entity.Choice
import com.ciyou.edu.entity.Judge
import com.ciyou.edu.entity.Quiz
import com.ciyou.edu.entity.Score
import com.ciyou.edu.entity.Student
import com.ciyou.edu.mapper.AnswerMapper
import com.ciyou.edu.mapper.ChoiceMapper
import com.ciyou.edu.mapper.JudgeMapper
import com.ciyou.edu.mapper.QuizMapper
import com.ciyou.edu.mapper.ScoreMapper
import com.ciyou.edu.mapper.StudentMapper
import com.ciyou.edu.service.QuizService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * @Author C.
 * @Date 2018-02-24 23:23
 */
@Service
class QuizServiceImpl implements QuizService{

    private static final Logger logger = LoggerFactory.getLogger(QuizServiceImpl.class)

    @Autowired
    private QuizMapper quizMapper

    @Autowired
    private ChoiceMapper choiceMapper

    @Autowired
    private JudgeMapper judgeMapper

    @Autowired
    private ScoreMapper scoreMapper

    @Autowired
    private AnswerMapper answerMapper

    @Autowired
    private StudentMapper studentMapper

    @Override
    Page<Quiz> findByPage(int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return quizMapper?.findAllQuiz()
    }

    @Transactional
    @Override
    int addQuiz(Quiz quiz) {
        return quizMapper?.addQuiz(quiz)
    }

    @Override
    Quiz getQuizById(Integer quizId) {
        Quiz quiz = quizMapper?.getQuizById(quizId)
        quiz?.setChoices(choiceMapper?.getChoicesByQuiz(quizId))
        quiz?.setJudges(judgeMapper?.getJudgesByQuiz(quizId))
        return quiz
    }

    @Transactional
    @Override
    int updateQuiz(Quiz quiz) {
        return quizMapper?.updateQuiz(quiz)
    }

    @Transactional
    @Override
    int deleteQuiz(Integer quizId) {
        return quizMapper?.deleteQuiz(quizId)
    }

    @Override
    Page<Quiz> queryQuizByPage(String value, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return quizMapper?.queryQuizByPage(value)
    }

    @Transactional
    @Override
    boolean quizExam(Integer sid,Integer quizId, String answer) {
        boolean b = true
        Score testScore = scoreMapper?.getScore(sid, quizId)
        if(testScore != null){
            //该试卷已经做过了 ，不允许重复提交
            return false
        }
        Quiz quizById = quizMapper?.getQuizById(quizId)
        //设置选择题和判断题
        quizById?.setChoices(choiceMapper?.getChoicesByQuiz(quizId))
        quizById?.setJudges(judgeMapper?.getJudgesByQuiz(quizId))
        Student student = new Student()
        student?.setSid(sid)
        try{
            HashMap<String, Answer> splitAnswer = splitAnswer(student, quizById, answer)
            //同时需要统计考试做题的成绩
            //创建一个学生成绩实例
            Score score = new Score()
            score?.setQuiz(quizById)
            score?.setStudent(student)
            score?.setChoiceScore(0)
            score?.setJudgeScore(0)
            score?.setAllScore(0)
            logger.info("获得的小测Quiz：" + quizById)

            //得到该套题的所有选择题
            List<Choice> choices = quizById?.getChoices()
            //得到该套题的所有判断题
            List<Judge> judges = quizById?.getJudges()
            //遍历所有的选择题
            for(Choice choice : choices){
                if(splitAnswer?.containsKey(choice?.getChoiceId() + "-1")){
                    Answer answer2 = splitAnswer?.get(choice?.getChoiceId() + "-1")
                    //设置选择题成绩得分
                    score?.setChoiceScore(score?.getChoiceScore() + answer2?.getScore())
                    //添加答案
                    answerMapper?.addAnswer(answer2)
                }else{
                    //添加答案
                    Answer an = new Answer()
                    an?.setStudent(student)
                    an?.setQuiz(quizById)
                    an?.setQuestionType(1)//设置为选择题类型
                    an?.setQuestion(choice.getChoiceId())//设置题目id
                    an?.setAnswer("")
                    an?.setGoodAnswer(choice.getAnswer())
                    an?.setScore(0)
                    //添加答案
                    answerMapper?.addAnswer(an)

                }
            }
            //遍历所有的判断题
            for(Judge judge : judges){
                if(splitAnswer.containsKey(judge?.getJudgeId() + "-2")){
                    Answer answer2 = splitAnswer?.get(judge?.getJudgeId() + "-2")
                    //设置判断题成绩得分
                    score?.setJudgeScore(score?.getJudgeScore()+answer2?.getScore())
                    //添加答案
                    answerMapper?.addAnswer(answer2)
                }else{
                    //添加答案
                    Answer an = new Answer()
                    an?.setQuiz(quizById)
                    an?.setStudent(student)
                    an?.setQuestionType(2)//设置为选择题类型
                    an?.setQuestion(judge?.getJudgeId())//设置题目id
                    an?.setAnswer("")
                    an?.setGoodAnswer(judge?.getAnswer())
                    an?.setScore(0)
                    //添加答案
                    answerMapper.addAnswer(an)
                }

            }
            //设置总得分，选择加上判断
            score?.setAllScore(score?.getChoiceScore() + score?.getJudgeScore())
            //保存总成绩
            scoreMapper?.addScore(score)

            //关闭学生的锁
            //studentMapper.updateStudentLockState(sid,0)
        }catch (Throwable e1) {
            b = false
            throw new RuntimeException(e1.getMessage())
        }
        return b
    }

    public HashMap<String, Answer> splitAnswer(Student student,Quiz quiz,String answer){
        HashMap<String, Answer> map = new HashMap<String, Answer>()
        Quiz quizById = quizMapper?.getQuizById(quiz?.getQuizId())	//得到小测的详细信息
        //分割解析答案字符串
        //得到每一个答案组
        //需要判断一下答案字符串是否为空，为空则不处理
        if(answer == null){
            return map
        }else if(answer?.trim() == ""){
            return map
        }
        String[] str = answer.split("-")
        for(String s : str){
            //得到每个答案组的字符串,继续分割
            String[] ss = s.split("_")
            Answer answer2 = new Answer()
            answer2.setQuestion(Integer.parseInt(ss[0]))
            answer2.setQuestionType(Integer.parseInt(ss[1]))
            answer2.setAnswer(ss[2])

            answer2.setStudent(student)
            answer2.setQuiz(quiz)
            if(answer2.getQuestionType() == 1){
                //选择题
                answer2.setScore(quizById?.getChoiceScore())
                Choice choice = new Choice()
                choice.setChoiceId(answer2?.getQuestion())
                Choice choiceById = choiceMapper.getChoice(choice?.getChoiceId())//得到答案
                answer2.setGoodAnswer(choiceById?.getAnswer())
                //查看当前的答案与正确答案是否匹配
                if(answer2?.getAnswer() == answer2?.getGoodAnswer()){//判题
                    answer2.setScore(quizById?.getChoiceScore())
                }else{
                    answer2.setScore(0)
                }
            }else{
                //判断题
                answer2.setScore(quizById?.getJudgeScore())
                Judge judge = new Judge()
                judge?.setJudgeId(answer2?.getQuestion())
                Judge judgeById = judgeMapper.getJudge(judge?.getJudgeId())
                answer2?.setGoodAnswer(judgeById?.getAnswer())//得到答案
                //查看当前的答案与正确答案是否匹配
                if(answer2?.getAnswer() == answer2?.getGoodAnswer()){//判题
                    answer2?.setScore(quizById?.getJudgeScore())
                }else{
                    answer2?.setScore(0)
                }
            }
            map.put(answer2?.getQuestion() + "-" + answer2?.getQuestionType(), answer2)
        }
        logger.info("分割后的map:" + map)
        return map
    }
}
