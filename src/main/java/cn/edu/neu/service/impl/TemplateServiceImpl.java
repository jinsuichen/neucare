package cn.edu.neu.service.impl;

import cn.edu.neu.dao.QuestionDao;
import cn.edu.neu.dao.TemplateDao;
import cn.edu.neu.dao.impl.QuestionDaoImpl;
import cn.edu.neu.dao.impl.TemplateDaoImpl;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.TemplateService;

import java.util.List;

public class TemplateServiceImpl implements TemplateService {

    private TemplateDao templateDao = new TemplateDaoImpl();
    private QuestionDao questionDao = new QuestionDaoImpl();


    /**
     * 查询所有模板
     * @return 模板集合
     */
    @Override
    public List<Template> getAllTemplates() {
        return templateDao.queryAllTemplates();
    }

    /**
     * 根据模板ID删除模板
     * @param tid 模板的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTemplateById(int tid) {
        return templateDao.deleteTemplateById(tid);
    }


    /**
     * 增加模板
     * @param template 模板
     * @return 是否增加成功
     */
    @Override
    public boolean addTemplate(Template template) {
        return templateDao.createTemplate(template);
    }


    /**
     * 根据模板ID删除模板
     *
     * @param tid 模板的ID
     * @return 是否删除成功
     */
    @Override
    public boolean removeQuestion(int tid, int qid) {
        Question question = questionDao.queryQuestionByQid(qid);
        question.setTid(null);
        return true;
    }

    /**
     * 为指定模板绑定指定问题
     * @param tid 模板ID
     * @param qid 问题ID
     * @return 是否移除成功
     */
    @Override
    public boolean bindQuestion(int tid, int qid) {
        Question question = questionDao.queryQuestionByQid(qid);
        question.setTid(tid);
        return true;
    }


}
