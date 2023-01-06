package com.adobe.aem.guides.vishnujan.core.helper;

import java.util.Date;

import org.apache.sling.api.resource.Resource;

public class NastedHelper {

	private Date editondate;
	private int editionNumber;
	
	public NastedHelper(Resource sc){
		editondate = sc.getValueMap().get("editondate", Date.class);

		editionNumber = sc.getValueMap().get("bookediton", Integer.class);	
	}

	public Date getEditondate() {
		return editondate;
	}

	public int getEditionNumber() {
		return editionNumber;
	}
	
	
}
