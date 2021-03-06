package cn.edu.neu.service.impl;

import cn.edu.neu.dao.QuestionDao;
import cn.edu.neu.dao.impl.QuestionDaoImpl;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.service.QuestionService;

import java.util.List;

public class QuestionServiceImpl implements QuestionService {

    QuestionDao questionDao = new QuestionDaoImpl();

    /**
     * 查询所有的问题
     * @return 问题集合
     */
    @Override
    public List<Question> getAllQuestions() {
        return questionDao.queryAllQuestions();
    }

    /**
     * 根据模板ID查找所有问题
     * @return 问题集合
     */
    @Override
    public List<Question> getQuestionsByTid(int tid) {
        return questionDao.queryQuestionsByTid(tid);
    }


    /**
     * 查询所有未被绑定的问题
     * @return 问题集合
     */
    @Override
    public List<Question> getQuestionsWithNoTemplate() {
        return questionDao.queryQuestionsWithNoTemplate();
    }


    /**
     * 添加问题
     * @param question 待添加的问题
     * @return 是否添加成功
     */
    @Override
    public boolean addQuestion(Question question) {
        return questionDao.createQuestion(question);
    }

    /**
     * 根据问题ID删除问题
     * @param id 问题的ID
     */
    @Override
    public boolean deletePatientById(int id) {
        return questionDao.deletePatientById(id);
    }
}
