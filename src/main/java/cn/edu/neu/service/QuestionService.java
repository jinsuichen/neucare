package cn.edu.neu.service;

import cn.edu.neu.pojo.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 查询所有的问题
     * @return 问题集合
     */
    public List<Question> getAllQuestions();

}
