package com.adobe.aem.guides.vishnujan.core.servlets;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = { "process.label" + "=Work Flow Jan Custom 3" })
public class WorkFlowJanCustomProcess3 implements WorkflowProcess {

	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
			throws WorkflowException {

		try {
			WorkflowData workflowData = workItem.getWorkflowData();
			if (workflowData.getPayloadType().equals("JCR_PATH")) {
				String payload = workflowData.getPayload().toString() + "/jcr:content";

				Session session = workflowSession.adaptTo(Session.class);

				Node node = (Node) session.getItem(payload);

				String[] processArgs = metaDataMap.get("PROCESS_ARGS", "string").toString().split(",");
				for (String args : processArgs) {
					String[] arg = args.split(":");

					String key = arg[0];
					String value = arg[1];

					node.setProperty(key, value);

					LOG.info("\n" + key);
					LOG.info("\n" + value);

				}

			}

		} catch (Exception e) {
		}

	}

}
