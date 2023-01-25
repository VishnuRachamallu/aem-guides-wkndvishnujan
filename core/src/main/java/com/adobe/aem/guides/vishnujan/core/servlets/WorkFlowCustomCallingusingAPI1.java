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

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/testpathdelete" })
public class WorkFlowCustomCallingusingAPI1 extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestParameter("pat").getString();
		ResourceResolver resolver = request.getResourceResolver();
		String status = "all good , created page version using custom workflow calling using api";
		try {
			WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/vishnu-jan-custom-wf2");
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", path);

			status = workflowSession.startWorkflow(workflowModel, workflowData).getState();

		} catch (Exception e) {
		}

		response.setHeader("Content-Type", "text/html");
		response.getWriter().write(status);
	}

}
