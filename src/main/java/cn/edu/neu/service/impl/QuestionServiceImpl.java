package cn.edu.neu.service.impl;

import cn.edu.neu.dao.QuestionDao;
import cn.edu.neu.dao.impl.QuestionDaoImpl;
import cn.edu.neu.po.DataBase;
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
}
