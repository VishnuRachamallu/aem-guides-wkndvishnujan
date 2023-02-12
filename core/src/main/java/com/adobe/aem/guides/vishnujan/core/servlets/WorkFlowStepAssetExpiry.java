package com.adobe.aem.guides.vishnujan.core.servlets;

import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jcr.Session;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = WorkflowProcess.class, immediate = true, property = {
		"process.label" + "=Work Flow Step Asset Expiry" })
public class WorkFlowStepAssetExpiry implements WorkflowProcess {

	public Logger LOG = LoggerFactory.getLogger(getClass());

	@Reference
	QueryBuilder queryBuilder;

	@Reference
	MessageGatewayService messageGatewayService;

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap processArguments)
			throws WorkflowException {
		LOG.info("\n =======================================");
		try {
			WorkflowData workflowData = workItem.getWorkflowData();

			String path = workflowData.getPayload().toString();

			Map<String, String> queryMap = new HashMap<>();
			final Session session = workflowSession.adaptTo(Session.class);
			UserManager userManager = ((JackrabbitSession) session).getUserManager();
			ZonedDateTime dt = ZonedDateTime.now();
			DateTimeFormatter df = DateTimeFormatter.ISO_OFFSET_DATE_TIME;

			queryMap.put("type", "dam:Asset");
			queryMap.put("path", path);
			queryMap.put("group.p.or", "true");
			queryMap.put("group.1_daterange.property", "@jcr:content/metadata/prism:expirationDate");
			queryMap.put("group.1_daterange.lowerBound", dt.format(df));
			queryMap.put("group.1_daterange.upperBound", dt.plusSeconds(60).format(df));
			queryMap.put("p.limit", Long.toString(-1));
			
			LOG.info("\nLower"+dt.format(df));
			LOG.info("\nUpper"+dt.plusSeconds(60).format(df));

			Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);
			SearchResult result = query.getResult();

			String expiedAssetPaths = "";

			for (Hit hits : result.getHits()) {

				// expiedAssetPaths = expiedAssetPaths + hits.getPath();

				List<String> toList = new ArrayList<>();
				expiedAssetPaths = hits.getPath();
				User user = (User) userManager.getAuthorizable("admin");
				/*
				 * LOG.info("\n user ID :" + user.getID()); LOG.info("\n user.isAdmin :" +
				 * user.isAdmin()); LOG.info("\n user.getPrincipal().getName() :" +
				 * user.getPrincipal().getName()); LOG.info("\n user email :" +
				 * user.getProperty("./profile/email")[0].getString());
				 */
				toList.add(user.getProperty("./profile/email")[0].getString());

				sendEmail("Expired Assets and owner details", expiedAssetPaths, toList);
			}
			/*
			 * String[] recipients = { "vitturachamallu001@gmail.com" };
			 * sendEmail("Expired Assets and owner details", expiedAssetPaths, recipients);
			 */
		} catch (Exception e) {
		}

	}

	private void sendEmail(String subjectLine, String msgBody, List<String> toList) throws EmailException {
		Email email = new HtmlEmail();
		for (String recipient : toList) {
			email.addTo(recipient, recipient);
		}
		email.setSubject(subjectLine);
		email.setMsg(msgBody);
		MessageGateway<Email> messageGateway = messageGatewayService.getGateway(HtmlEmail.class);
		if (messageGateway != null) {
			LOG.info("\n sending out email in WorkFLow Step");
			messageGateway.send((Email) email);
		} else {
			LOG.info("The message gateway could not be retrieved.");
		}
	}

}
