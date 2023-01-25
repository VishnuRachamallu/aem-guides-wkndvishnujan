package com.adobe.aem.guides.vishnujan.core.models;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Path;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.inherit.InheritanceValueMap;

@Model(adaptables = SlingHttpServletRequest.class)
public class PropTest {

	@Inject
	public InheritanceValueMap pageProperties;

	@Inject
	public ValueMap properties;

	@Inject
	public ValueMap pp;



	@PostConstruct
	public void activate() {
		String pageString = pageProperties.getInherited("myproperty", "default"); // InheritanceValueMap gives
																					// pageProperties Value Map and

	}

	public String results123() {

		Logger LOG = LoggerFactory.getLogger(PropTest.class);

		LOG.info("\nHello");

		for (Entry<String, Object> e : properties.entrySet()) {
			String key = e.getKey();
			Object value = e.getValue();
			LOG.info(e.getKey());
			LOG.info((String) e.getValue());
		}
		return "succesful";
	}
}
