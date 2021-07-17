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
