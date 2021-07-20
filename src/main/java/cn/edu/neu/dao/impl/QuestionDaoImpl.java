package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.QuestionDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class QuestionDaoImpl implements QuestionDao {


    /**
     * 查询所有的问题
     *
     * @return 问题集合
     */
    @Override
    public List<Question> queryAllQuestions() {
        List<Question> list = new ArrayList<>();
        for (Question question : DataBase.questionData) {
            if (!question.isDeleted()) {
                list.add(question);
            }
        }
        return list;
    }

    /**
     * 根据问题的ID查找问题
     * @param qid 文体ID
     * @return 问题
     */
    @Override
    public Question queryQuestionByQid(int qid) {
        for(Question q : DataBase.questionData){
            if(q.getQid() == qid){
                return q;
            }
        }
        return null;
    }

    /**
     * 查询所有未被绑定的问题
     * @return 问题集合
     */
    @Override
    public List<Question> queryQuestionsWithNoTemplate() {
        List<Question> list = new ArrayList<>();
        for (Question question : DataBase.questionData) {
            if (!question.isDeleted() && question.getTid() == null) {
                list.add(question);
            }
        }
        return list;
    }

    /**
     * 根据模板ID查找所有问题
     *
     * @return 问题集合
     */
    @Override
    public List<Question> queryQuestionsByTid(int tid) {
        List<Question> list = new ArrayList<>();
        for (Question q : DataBase.questionData) {
            if(!q.isDeleted() && q.getTid()!=null && q.getTid() == tid){
                list.add(q);
            }
        }
        return list;
    }


    /**
     * 添加问题
     *
     * @param question 待添加的问题
     * @return 是否添加成功
     */
    @Override
    public boolean createQuestion(Question question) {
        int maxQid = 1;
        for (Question q : DataBase.questionData) {
            maxQid = max(maxQid, q.getQid());
        }
        question.setQid(maxQid + 1);
        question.setDeleted(false);
        DataBase.questionData.add(question);
        return true;
    }


    /**
     * 根据问题ID删除问题
     *
     * @param id 问题的ID
     */
    @Override
    public boolean deletePatientById(int id) {
        for (Question q : DataBase.questionData) {
            if (!q.isDeleted() && q.getQid() == id) {
                q.setDeleted(true);
                return true;
            }
        }
        return false;
    }


}
