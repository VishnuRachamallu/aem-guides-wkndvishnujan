package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;


/*http://localhost:4502/bin/testWorkFlowUsingAPI1?pagepath=/content/vishnuwkndjan/us/en/workflow/trigger-version-workflow-1-using-api
*/
@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/testWorkFlowUsingAPI1" })
public class WorkflowJanUsingAPI1 extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String status = "Workflow Executing";

		final ResourceResolver resolver = request.getResourceResolver();
		String payload = request.getRequestParameter("pagepath").getString();

		try {
			WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);

			WorkflowModel workflowModel = workflowSession
					.getModel("/var/workflow/models/vishnu-jan-page-version-model-1");

			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", payload);

			status=workflowSession.startWorkflow(workflowModel, workflowData).getState();

		} catch (WorkflowException e) {

			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(status);

	}

}
