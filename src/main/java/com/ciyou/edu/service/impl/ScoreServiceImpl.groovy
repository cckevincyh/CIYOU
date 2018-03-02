package com.ciyou.edu.service.impl

import com.ciyou.edu.entity.Answer
import com.ciyou.edu.entity.Choice
import com.ciyou.edu.entity.ChoiceAnswer
import com.ciyou.edu.entity.Judge
import com.ciyou.edu.entity.JudgeAnswer
import com.ciyou.edu.entity.ResultScore
import com.ciyou.edu.entity.Score
import com.ciyou.edu.mapper.AnswerMapper
import com.ciyou.edu.mapper.ChoiceMapper
import com.ciyou.edu.mapper.JudgeMapper
import com.ciyou.edu.mapper.ScoreMapper
import com.ciyou.edu.service.ScoreService
import com.github.pagehelper.Page
import com.github.pagehelper.PageHelper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * @Author C.
 * @Date 2018-03-01 13:23
 */
@Service
class ScoreServiceImpl implements ScoreService{

    @Autowired
    private ScoreMapper scoreMapper
    @Autowired
    private ChoiceMapper choiceMapper
    @Autowired
    private JudgeMapper judgeMapper
    @Autowired
    private AnswerMapper answerMapper

    private static final Logger logger = LoggerFactory.getLogger(ScoreServiceImpl.class)

    @Override
    Score getScore(Integer sid,Integer quizId) {
        return scoreMapper?.getScore(sid,quizId)
    }

    @Override
    Page<Score> findMyScoreByPage(Integer sid, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return scoreMapper?.findMyScoreByPage(sid)
    }

    @Override
    ResultScore getResultScore(Integer scoreId) {
        List<ChoiceAnswer> choiceAnswers = new ArrayList<ChoiceAnswer>()
        List<JudgeAnswer> judgeAnswers = new ArrayList<JudgeAnswer>()
        Score score = scoreMapper?.getScoreById(scoreId)
        //得到所有的选择题
        List<Choice> choices = choiceMapper?.getChoicesByQuiz(score?.getQuiz()?.getQuizId())
        //得到所有的判断题
        List<Judge> judges = judgeMapper?.getJudgesByQuiz(score?.getQuiz()?.getQuizId())
        for(Choice choice : choices){
            //遍历所有的选择题,得到对应的答案
            Answer answer = answerMapper?.getChoiceAnswer(score?.getStudent()?.getSid(),score?.getQuiz()?.getQuizId(),choice?.getChoiceId())
            ChoiceAnswer choiceAnswer = new ChoiceAnswer()
            choiceAnswer.setAnswer(answer)
            choiceAnswer.setChoice(choice)
            choiceAnswers.add(choiceAnswer)//加入集合
        }
        for(Judge judge : judges){
            //遍历所有的判断题,得到对应的答案
            Answer answer = answerMapper.getJudgeAnswer(score?.getStudent()?.getSid(),score?.getQuiz()?.getQuizId(), judge?.getJudgeId())
            JudgeAnswer judgeAnswer = new JudgeAnswer()
            judgeAnswer.setAnswer(answer)
            judgeAnswer.setJudge(judge)
            judgeAnswers.add(judgeAnswer)//加入集合
        }
        ResultScore resultScore = new ResultScore()
        resultScore.setChoiceAnswers(choiceAnswers)
        resultScore.setJudgeAnswers(judgeAnswers)
        resultScore?.setScore(score)
        logger.info("resultScore = " + resultScore)
        return resultScore
    }

    @Override
    Page<Score> queryScoreByPage(String value, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return scoreMapper?.queryScoreByPage(value)
    }

    @Override
    Page<Score> queryMyScoreByPage(Integer sid, String value, int pageNo, int pageSize = 10) {
        PageHelper.startPage(pageNo, pageSize)
        return scoreMapper?.queryMyScoreByPage(sid,value)
    }
}
