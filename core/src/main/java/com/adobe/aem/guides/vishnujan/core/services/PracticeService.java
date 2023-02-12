package com.adobe.aem.guides.vishnujan.core.services;

import org.osgi.service.component.annotations.Component;

@Component()
public class PracticeService {

	private String name = "Vishnu";

	public String getUser() {
		return name;
	}
}
