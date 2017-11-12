package com.dlz.apps.freemaker.service.impl;

import java.io.OutputStream;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dlz.apps.freemaker.service.TemplateService;

import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 基于Freemarker模板技术的邮件模板服务
 */
@Service
public class FreemarkerMsmTemplateService implements TemplateService {
    /**
     * 短信模板的存放位置
     */
    private static final String TEMPLATE_PATH = "/msm/";
    /**
     * 启动模板缓存
     */
    private static final Map<String, Template> TEMPLATE_CACHE = new HashMap<String, Template>();
    /**
     * 模板文件后缀
     */
    private static final String SUFFIX = ".txt";
    /**
     * 模板引擎配置
     */
    private Configuration configuration;

    public void init() {
        configuration = new Configuration();
        configuration.clearTemplateCache();
        configuration.setTemplateLoader(new ClassTemplateLoader(FreemarkerMsmTemplateService.class, TEMPLATE_PATH));
        configuration.setEncoding(Locale.getDefault(), "UTF-8");
        configuration.setDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    public FreemarkerMsmTemplateService() {
        init();
    }

    public String getText(String templateId, Map<Object, Object> parameters) {
        String templateFile = templateId + SUFFIX;
        try {
            Template template = TEMPLATE_CACHE.get(templateFile);
            if (template == null) {
                template = configuration.getTemplate(templateFile);
                TEMPLATE_CACHE.put(templateFile, template);
            }
            StringWriter stringWriter = new StringWriter();
            template.process(parameters, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

		public void getOutputStream(String templateId, Map<Object, Object> parameters, OutputStream fos) {
			// TODO Auto-generated method stub
			
		}
}  