package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = { "sling.servlet.resourceTypes=aemgeeks/components/page",
		"selectors=tnx", "extensions=true" })
public class PropertiesServlet extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resolver = request.getResourceResolver();

		Resource resource = resolver.getResource("/content/aemgeeks/us/en/jcr:content");

		Map<String, Object> pro = new HashMap<String, Object>();

		pro.put("jai", "hind");

		resolver.create(resource, "lucky", pro);

		resolver.commit();
		
		response.getWriter().write("Added property");

	}

}
