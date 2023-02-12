package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;;

//http://localhost:4502/bin/setExpiry
@Component(service = Servlet.class, immediate = true)
@SlingServletPaths(value = { "/bin/setExpiry" })
public class SetExpiryForAssets extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(getClass());

	@Reference
	QueryBuilder queryBuilder;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceResolver = request.getResourceResolver();

		Map<String, String> queryMap = new HashMap<>();

		Session session = resourceResolver.adaptTo(Session.class);

		queryMap.put("type", "dam:Asset");
		queryMap.put("path", "/content/dam/pavan-profile");
		queryMap.put("p.limit", Long.toString(-1));

		Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);

		SearchResult searchResult = query.getResult();

		for (Hit hit : searchResult.getHits()) {
			String path = "";
			try {
				path = hit.getPath();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Resource rs = resourceResolver.getResource(path);
			Asset asset = rs.adaptTo(Asset.class);

			for (Entry<String, Object> e : rs.adaptTo(ValueMap.class).entrySet()) {
				String key = e.getKey();
				Object value = e.getValue();
				//log.info("Key:" + key + " " + "value:" + value.toString());

			}
			/*
			 * for (Entry<String, Object> e : asset.getValueMap().entrySet()) { String key =
			 * e.getKey(); Object value = e.getValue(); log.info(" Meta data Key:" + key +
			 * " " + "Meta data value:" + value.toString());
			 * 
			 * }
			 */

			/*
			 * log.info("\n dam:Producer#" +
			 * asset.getChild("jcr:content/metadata").getValueMap().get("dam:Producer",
			 * String.class)); log.info("\n" + asset.getValueMap().get("jcr:uuid",
			 * String.class));
			 */
			//log.info("\nprism:expirationDate" + asset.getMetadata().get("prism:expirationDate"));

			Node node = rs.getChild("jcr:content/metadata").adaptTo(Node.class);
			try {
				node.setProperty("prism:expirationDate", "2023-02-12T14:07:00+05:30");
				node.save();
			} catch (RepositoryException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			//log.info("\n second time prism:expirationDate" + asset.getMetadata().get("prism:expirationDate"));

		}

		response.getWriter().write("all good");
	}

}
