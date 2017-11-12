package com.dlz.framework.ssme.db.model;

import java.util.Date;

public class ContentTemplate {
    private Long templateId;

    private String templateName;

    private String templateType;

    private Date crtDate;

    private Date lastUptDate;

    private String templateContent;

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType == null ? null : templateType.trim();
    }

    public Date getCrtDate() {
        return crtDate;
    }

    public void setCrtDate(Date crtDate) {
        this.crtDate = crtDate;
    }

    public Date getLastUptDate() {
        return lastUptDate;
    }

    public void setLastUptDate(Date lastUptDate) {
        this.lastUptDate = lastUptDate;
    }

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent == null ? null : templateContent.trim();
    }
}