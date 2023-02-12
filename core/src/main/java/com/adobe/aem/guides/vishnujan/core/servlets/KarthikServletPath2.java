package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.adapter.Adaptable;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.json.JSONArray;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/blogkar2vishnu")

public class KarthikServletPath2 extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private Logger LOG = LoggerFactory.getLogger(getClass());

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceResolver = request.getResourceResolver();
		JSONObject jsonObject = new JSONObject();
		JSONArray arrayarray = new JSONArray();

		try {

			PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
			Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-api-usage");
			Iterator<Page> pages = page.listChildren();

			while (pages.hasNext()) {
				Page page1 = pages.next();
				JSONObject temp = new JSONObject();

				if (page1.getContentResource().getValueMap().get("jcr:title", String.class) != null)
					temp.put("Title ", page1.getContentResource().getValueMap().get("jcr:title", String.class));
				if (page1.getContentResource().getValueMap().get("cq:tags", String[].class) != null)
					temp.put("Tags ", page1.getContentResource().getValueMap().get("cq:tags", String[].class));

				if (page1.getContentResource().getValueMap().get("jcr:description", String[].class) != null)
					temp.put("Description ",
							page1.getContentResource().getValueMap().get("jcr:description", String.class));
				if (page1.getContentResource().getChild("image") != null)
					if (page1.getContentResource().getChild("image").getValueMap().get("fileReference",
							String.class) != null)
						temp.put("Thumbnail ", page1.getContentResource().getChild("image").getValueMap()
								.get("fileReference", String.class));
				arrayarray.put(temp);

			}

			jsonObject.put("results", arrayarray);

		} catch (Exception e) {
			LOG.info("\n Login Exception {} ", e.getMessage());
			response.getWriter().print(e.getMessage());
		}

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonObject.toString());

		/*
		 * ZonedDateTime dt = ZonedDateTime.now(); DateTimeFormatter df =
		 * DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		 * 
		 * out.print("\n"); out.print(dt.format(df)); out.print("\n");
		 * out.print(ZonedDateTime.now().plusSeconds(30).format(df));
		 */



		out.flush();
	}

}
