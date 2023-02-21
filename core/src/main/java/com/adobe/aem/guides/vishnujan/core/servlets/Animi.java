package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = { "sling.servlet.resourceTypes=vishnuwkndjan/components/page",
		"sling.servlet.methods=GET", "sling.servlet.selectors=txttwt", "sling.servlet.extensions=recent" })
public class Animi extends SlingAllMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * JsonObjectBuilder jobj = Json.createObjectBuilder();
		 * 
		 * ResourceResolver resolver = request.getResourceResolver();
		 * 
		 * Resource resource =
		 * resolver.getResource("/content/vishnuwkndjan/us/delete-this-page");
		 * 
		 * ValueMap valueMap = resource.getValueMap();
		 * 
		 * Object object = valueMap.get("pageTitle");
		 * 
		 * jobj.add("pageTitle", object.toString());
		 * 
		 * response.getWriter().write(jobj.build().toString());
		 */

		response.getWriter().write("hello servlet");
	}

}
