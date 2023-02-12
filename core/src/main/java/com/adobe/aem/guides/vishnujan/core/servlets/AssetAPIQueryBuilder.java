package com.adobe.aem.guides.vishnujan.core.servlets;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.*;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.asset.api.Asset;
import com.day.cq.search.PredicateGroup;
import com.day.cq.search.Query;
import com.day.cq.search.QueryBuilder;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;

@Component(service = Servlet.class)
@SlingServletPaths(value = "/bin/assetapiquery")

public class AssetAPIQueryBuilder extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;

	Logger log = LoggerFactory.getLogger(getClass());

	@Reference
	QueryBuilder queryBuilder;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {

		ResourceResolver resourceResolver = request.getResourceResolver();
		Map<String, String> queryMap = new HashMap<>();
		final Session session = resourceResolver.adaptTo(Session.class);

		DateTimeFormatter df = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
		ZonedDateTime dt = ZonedDateTime.now().plusSeconds(60);

		queryMap.put("type", "dam:Asset");
		queryMap.put("path", "/content/dam/pavan-profile");
		queryMap.put("group.p.or", "true");
		queryMap.put("group.1_daterange.property", "@jcr:content/metadata/prism:expirationDate");
		queryMap.put("group.1_daterange.upperBound", dt.format(df));
		queryMap.put("p.limit", Long.toString(-1));

		Query query = queryBuilder.createQuery(PredicateGroup.create(queryMap), session);
		SearchResult result = query.getResult();
		String assetsNames = "";

		// iterating over the results
		for (Hit hit : result.getHits()) {

			String path = "";
			try {
				path = hit.getPath();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Resource rs = resourceResolver.getResource(path);
			Asset asset = rs.adaptTo(Asset.class);

			assetsNames = assetsNames + asset.getPath() + "\n";

			log.info("\n" + asset.getValueMap().get("jcr:uuid", String.class));

		}

		response.setContentType("text/plain");
		response.getWriter().write("Results :" + result.getTotalMatches());
		response.getWriter().write("\nResults :\n" + assetsNames);

	}

}