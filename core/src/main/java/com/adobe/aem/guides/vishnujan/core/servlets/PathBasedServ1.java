package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component(service = Servlet.class)
@SlingServletPaths(value = { "/bin/sampleservletpath" })
public class PathBasedServ1 extends SlingSafeMethodsServlet {

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		List<String> str = new ArrayList<>();
		ObjectMapper objectMapper=new ObjectMapper();
		str.add("vishnu");
		str.add("vi");
		str.add("nudgfd");
		
		response.setHeader("Content-Type", "text/html");
		response.getWriter().write(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(str));
	}

}
