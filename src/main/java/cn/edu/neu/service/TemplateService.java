package cn.edu.neu.service;

import cn.edu.neu.pojo.Template;

import java.util.List;

public interface TemplateService {

    /**
     * 查询所有模板
     *
     * @return 模板集合
     */
    public List<Template> getAllTemplates();

    /**
     * 根据模板ID删除模板
     *
     * @param tid 模板的ID
     * @return 是否删除成功
     */
    boolean deleteTemplateById(int tid);


    /**
     * 增加模板
     *
     * @param template 模板
     * @return 是否增加成功
     */
    boolean addTemplate(Template template);

    /**
     * 为指定模板移除指定问题
     * @param tid 模板ID
     * @param qid 问题ID
     * @return 是否移除成功
     */
    boolean removeQuestion(int tid, int qid);

    /**
     * 为指定模板绑定指定问题
     * @param tid 模板ID
     * @param qid 问题ID
     * @return 是否移除成功
     */
    boolean bindQuestion(int tid, int qid);


}
