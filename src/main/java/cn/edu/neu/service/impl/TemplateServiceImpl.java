package cn.edu.neu.service.impl;

import cn.edu.neu.dao.TemplateDao;
import cn.edu.neu.dao.impl.TemplateDaoImpl;
import cn.edu.neu.pojo.Template;
import cn.edu.neu.service.TemplateService;

import java.util.List;

public class TemplateServiceImpl implements TemplateService {

    private TemplateDao dao = new TemplateDaoImpl();

    /**
     * 查询所有模板
     * @return 模板集合
     */
    @Override
    public List<Template> getAllTemplates() {
        return dao.queryAllTemplates();
    }

    /**
     * 根据模板ID删除模板
     * @param tid 模板的ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteTemplateById(int tid) {
        return dao.deleteTemplateById(tid);
    }
}
