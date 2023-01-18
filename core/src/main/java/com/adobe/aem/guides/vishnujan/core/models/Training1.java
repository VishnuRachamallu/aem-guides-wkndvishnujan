package com.adobe.aem.guides.vishnujan.core.models;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.adobe.aem.guides.vishnujan.core.models.interfaces.Training1Interface;

@Model(adaptables = SlingHttpServletRequest.class, adapters = Training1Interface.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Training1 implements Training1Interface {

	@Inject
	@Via(value = "resource")
	private String fname;

	@ValueMapValue
	@Named(value = "fname")
	private String firstName;

	public String getFname() {
		// TODO Auto-generated method stub
		return fname;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

}
