package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.QuestionDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class QuestionDaoImpl implements QuestionDao {


    /**
     * 查询所有的问题
     * @return 问题集合
     */
    @Override
    public List<Question> queryAllQuestions() {
        List<Question> list = new ArrayList<>();
        for(Question question : DataBase.questionData){
            if(!question.isDeleted()){
                list.add(question);
            }
        }
        return list;
    }


}
