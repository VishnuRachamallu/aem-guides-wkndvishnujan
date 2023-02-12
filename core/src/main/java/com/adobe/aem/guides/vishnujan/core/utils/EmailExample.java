package com.adobe.aem.guides.vishnujan.core.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = EmailExample.class, immediate = true)
public class EmailExample {

	private static final String TEMPLATE_PATH = "/etc/notification/email/default/emailTemplate.txt";
	/*
	 * @Reference private EmailService emailService;
	 * 
	 * public void sendEmailAcs(List<String> recipients) { Map<String, String>
	 * emailParams = new HashMap<>(); emailParams.put("name", "John");
	 * emailService.sendEmail(TEMPLATE_PATH, emailParams, recipients.toArray(new
	 * String[] {})); }
	 */
}
