package com.adobe.aem.guides.vishnujan.core.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;

public final class ResolverUtil {

	public static final String VISHNU_SERVICE_USER = "vishnujanserviceuser";

	public static ResourceResolver newResourceResolver(ResourceResolverFactory resourceResolverFactory)
			throws LoginException {

		final Map<String, Object> map = new HashMap<>();
		map.put(ResourceResolverFactory.SUBSERVICE, VISHNU_SERVICE_USER);
		ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(map);
		return resolver;
	}

}
