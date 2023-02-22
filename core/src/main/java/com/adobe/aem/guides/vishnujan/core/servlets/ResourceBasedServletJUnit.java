package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

import com.day.cq.commons.jcr.JcrConstants;

//http://localhost:4502/content/vishnuwknd/us/en/trainingfield2-page/jcr:content.junit.xml
//http://localhost:4502/content/vishnuwknd/us/en/trainingfield2-page.junit.xml
@Component(service = Servlet.class)
@SlingServletResourceTypes(methods = { HttpConstants.METHOD_GET }, resourceTypes = { "vishnuwkndjan/components/page",
		"vishnuwkndjan/components/training1" }, selectors = { "junit",
				"sjunit" }, extensions = { "html", "momos", "gif", "xml" })
public class ResourceBasedServletJUnit extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		final Resource resource = request.getResource();

		response.getWriter()
				.print(" ResourceBasedServletJUnit Page Title :" + resource.getValueMap().get(JcrConstants.JCR_TITLE));

	}

}
