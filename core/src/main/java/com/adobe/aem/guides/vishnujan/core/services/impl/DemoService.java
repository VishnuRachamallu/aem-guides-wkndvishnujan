package com.adobe.aem.guides.vishnujan.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnujan.core.services.DemoServiceInterf;

@Component(service = DemoServiceInterf.class)
public class DemoService implements DemoServiceInterf {
	Logger Log = LoggerFactory.getLogger(getClass());

	public void getPages() {

		Log.info("in DemoService implementing DemoServiceInterf");
	}

}
