package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.QuestionDao;
import cn.edu.neu.dao.TemplateDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Template;

import java.util.ArrayList;
import java.util.List;

import static sun.swing.MenuItemLayoutHelper.max;

public class TemplateDaoImpl implements TemplateDao {

    private QuestionDao questionDao = new QuestionDaoImpl();

    /**
     * 查询所有模板
     * @return 模板集合
     */
    @Override
    public List<Template> queryAllTemplates() {
        List<Template> list = new ArrayList<>();
        for(Template t : DataBase.templateData){
            if(!t.isDeleted()){
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 根据模板ID查找模板
     * @param tid 模板ID
     * @return 模板
     */
    @Override
    public Template queryTemplateByTid(int tid) {
        for(Template t : DataBase.templateData){
            if(t.getTid() == tid){
                return t;
            }
        }
        return null;
    }

    /**
     * 根据模板ID删除模板
     * @param tid 模板的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTemplateById(int tid) {
        for(Template t : DataBase.templateData){
            if(!t.isDeleted() && t.getTid() == tid){
                t.setDeleted(true);
                List<Question> questionList = questionDao.queryQuestionsByTid(tid);
                for(Question q : questionList){
                    q.setTid(null);
                }
                return true;
            }
        }
        return false;
    }


    /**
     * 增加模板
     * @param template 模板
     * @return 是否增加成功
     */
    @Override
    public boolean createTemplate(Template template) {
        if (template.getName() != null) {
            int maxId = 0;
            for (Template t : DataBase.templateData) {
                maxId = max(maxId, t.getTid());
            }
            template.setTid(maxId + 1);
            template.setDeleted(false);
            DataBase.templateData.add(template);
            return true;
        } else {
            return false;
        }
    }

}
