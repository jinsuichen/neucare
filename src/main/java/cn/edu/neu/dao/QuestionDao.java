package cn.edu.neu.dao;

import cn.edu.neu.pojo.Question;

import java.util.List;

public interface QuestionDao {

    /**
     * 查询所有的问题
     * @return 问题集合
     */
    public List<Question> queryAllQuestions();


    /**
     * 根据问题的ID查找问题
     * @param qid 文体ID
     * @return 问题
     */
    public Question queryQuestionByQid(int qid);

    /**
     * 查询所有未被绑定的问题
     * @return 问题集合
     */
    public List<Question> queryQuestionsWithNoTemplate();


    /**
     * 根据模板ID查找所有问题
     * @return 问题集合
     */
    public List<Question> queryQuestionsByTid(int tid);

    /**
     * 添加问题
     * @param question 待添加的问题
     * @return 是否添加成功
     */
    public boolean createQuestion(Question question);

    /**
     * 根据问题ID删除问题
     * @param id 问题的ID
     */
    boolean deletePatientById(int id);

}
