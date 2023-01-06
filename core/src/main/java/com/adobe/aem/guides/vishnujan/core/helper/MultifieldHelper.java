package com.adobe.aem.guides.vishnujan.core.helper;

import java.util.Date;

import org.apache.sling.api.resource.Resource;

public class MultifieldHelper {

	private String bookName;
	private String bookSubject;
	private Date publishDate;
	private int copies;

	public MultifieldHelper(Resource rc) {

		bookName = rc.getValueMap().get("bookname", String.class);

		bookSubject = rc.getValueMap().get("booksubject", String.class);

		publishDate = rc.getValueMap().get("publishdate", Date.class);

		copies = rc.getValueMap().get("copies", Integer.class);
	}

	public String getBookName() {
		return bookName;
	}

	public String getBookSubject() {
		return bookSubject;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public int getCopies() {
		return copies;
	}

}
