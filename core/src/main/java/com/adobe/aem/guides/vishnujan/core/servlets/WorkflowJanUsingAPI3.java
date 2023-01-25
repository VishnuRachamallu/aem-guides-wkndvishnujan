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

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Component(service = Servlet.class, immediate = true)
@SlingServletPaths(value = { "/bin/workflowusingapi3" })
public class WorkflowJanUsingAPI3 extends SlingSafeMethodsServlet {

	Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resolver = request.getResourceResolver();

		String path = request.getRequestParameter("path2").getString();
		String status = "mot succesfull";
		try {

			WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);

			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/vishnu-jan-page-version-model3");
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", path);

			status = workflowSession.startWorkflow(workflowModel, workflowData).getState();

		} catch (Exception e) {
			status = e.getMessage().toString();
		}

		response.setHeader("Content-Type", "text/html");
		response.getWriter().write(status);

	}

}
