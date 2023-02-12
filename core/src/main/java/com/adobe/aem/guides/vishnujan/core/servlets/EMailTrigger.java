package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/emailTrigger")
public class EMailTrigger extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	private static Logger log = LoggerFactory.getLogger(EMailTrigger.class);

	@Reference
	private MessageGatewayService messageGatewayService;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
		JSONObject jsonResponse = new JSONObject();
		boolean sent = false;
		try {
			String[] recipients = { "justok1998@gmail.com" };
			sendEmail("This is an test email subject", "This is the email body", recipients);
			response.setStatus(200);
			sent = true;
		} catch (EmailException e) {
			response.setStatus(500);
		}
		try {
			jsonResponse.put("result", sent ? "done" : "something went wrong");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		response.getWriter().write(jsonResponse.toString());
	}

	private void sendEmail(String subjectLine, String msgBody, String[] recipients) throws EmailException {
		Email email = new HtmlEmail();
		for (String recipient : recipients) {
			email.addTo(recipient, recipient);
		}
		email.setSubject(subjectLine);
		email.setMsg(msgBody);
		MessageGateway<Email> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
		if (messageGateway != null) {
			log.info("sending out email");
			messageGateway.send((Email) email);
			log.info("sent email succsfully");
		} else {
			log.info("The message gateway could not be retrieved.");
		}
	}
}