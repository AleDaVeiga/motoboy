package com.wgsistemas.motoboy.mail;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Component
public class EmailHtmlSender {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmailHtmlSender.class);
	
	@Autowired
    private EmailSender emailSender;
 
    @Autowired
    private Configuration freemarkerConfig;
 
	public EmailStatus send(String to, String subject, String templateName, Map<String, Object> model) {
		try {
			freemarkerConfig.setClassForTemplateLoading(this.getClass(), "/templates/email");
			Template template = freemarkerConfig.getTemplate(templateName);
			String body = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			return emailSender.sendHtml(to, subject, body);
		} catch (Exception e) {
			LOGGER.error(String.format("Problem with template for sending html email to: {}, error message: {}", to, e.getMessage()));
			return new EmailStatus(to, subject, templateName).error(e.getMessage());
		}
	}
}