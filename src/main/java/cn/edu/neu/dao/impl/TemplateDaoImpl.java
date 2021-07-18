package cn.edu.neu.dao.impl;

import cn.edu.neu.dao.TemplateDao;
import cn.edu.neu.po.DataBase;
import cn.edu.neu.pojo.Bed;
import cn.edu.neu.pojo.Patient;
import cn.edu.neu.pojo.Question;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.TemplateService;

import java.util.ArrayList;
import java.util.List;

public class TemplateDaoImpl implements TemplateDao {

    /**
     * 查询所有模板
     * @return 模板集合
     */
    @Override
    public List<Template> queryAllTemplates() {
        List<Template> list = new ArrayList<>();
        for(Template t : DataBase.templateData){
            if(!t.getDeleted()){
                list.add(t);
            }
        }
        return list;
    }

    /**
     * 根据模板ID删除模板
     * @param tid 模板的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTemplateById(int tid) {
        for(Template t : DataBase.templateData){
            if(!t.getDeleted() && t.getTid() == tid){
                t.setDeleted(true);
                return true;
            }
        }
        return false;
    }

}
