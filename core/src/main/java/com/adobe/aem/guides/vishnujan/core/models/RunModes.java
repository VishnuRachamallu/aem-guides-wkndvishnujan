package com.adobe.aem.guides.vishnujan.core.models;

import java.util.Iterator;
import java.util.Set;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.RequestAttribute;
import org.apache.sling.settings.SlingSettingsService;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = SlingHttpServletRequest.class)
public class RunModes {

	@Reference
	SlingSettingsService slingSettingsService;
	
	@RequestAttribute @Default(values = "jai")
	String saname;

	Logger LOG = LoggerFactory.getLogger(getClass());

	public String getNames() {
		return "Vishnu";
	}

	public String getRunModelsForAEM() {

		Set<String> run = slingSettingsService.getRunModes();
		LOG.info("Run modes :" + run.toString());
		String modes = "";
		Iterator<String> namesIterator = run.iterator();

		while (namesIterator.hasNext()) {
			modes = modes + namesIterator.next();
		}

		return saname;
	}
}
