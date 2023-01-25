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
@SlingServletPaths(value = { "/bin/testWorkFlowUsingAPI2" })
public class WorkflowJanUsingAPI2 extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestParameter("jaffaPath").getString();
		ResourceResolver resolver = request.getResourceResolver();

		try {
			WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);

			WorkflowModel workflowModel = workflowSession
					.getModel("/var/workflow/models/delete-this-model-sample-page-version");

			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", path);

			workflowSession.startWorkflow(workflowModel, workflowData);

		} catch (Exception e) {
		}

		response.setContentType("application/json");
		response.getWriter().write("done");

	}

}
