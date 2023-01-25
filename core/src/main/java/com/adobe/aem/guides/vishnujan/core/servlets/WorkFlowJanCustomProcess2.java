package com.adobe.aem.guides.vishnujan.core.servlets;

import javax.jcr.Node;
import javax.jcr.Session;

import org.osgi.service.component.annotations.Component;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, immediate = true, property = {
		"process.label" + "=Work Flow Jan Custom 2" })
public class WorkFlowJanCustomProcess2 implements WorkflowProcess {

	@Override
	public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
			throws WorkflowException {

		try {

			WorkflowData workflowData = workItem.getWorkflowData();

			if (workflowData.getPayloadType().equals("JCR_PATH")) {
				Session session = workflowSession.adaptTo(Session.class);
				String path = workflowData.getPayload().toString() + "/jcr:content";
				Node node= (Node) session.getItem(path);
				
				String[] argProcess=metaDataMap.get("PROCESS_ARGS", "string").toString().split(",");
				for(String pargs : argProcess) {
					String[] args=pargs.split(":");
					node.setProperty(args[0], args[1]);
					
				}

			}

		} catch (

		Exception e) {
		}
	}

}
