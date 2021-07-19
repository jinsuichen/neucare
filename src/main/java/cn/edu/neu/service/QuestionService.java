package cn.edu.neu.service;

import cn.edu.neu.pojo.Question;

import java.util.List;

public interface QuestionService {

    /**
     * 查询所有的问题
     * @return 问题集合
     */
    public List<Question> getAllQuestions();

    /**
     * 根据模板ID查找所有问题
     * @return 问题集合
     */
    public List<Question> getQuestionsByTid(int tid);

    /**
     * 添加问题
     * @param question 待添加的问题
     * @return 是否添加成功
     */
    public boolean addQuestion(Question question);


    /**
     * 根据问题ID删除问题
     * @param id 问题的ID
     */
    boolean deletePatientById(int id);
}
