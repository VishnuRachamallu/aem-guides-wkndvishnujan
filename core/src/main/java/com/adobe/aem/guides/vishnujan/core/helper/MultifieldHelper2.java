package com.adobe.aem.guides.vishnujan.core.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.sling.api.resource.Resource;

public class MultifieldHelper2 {

	private String bookName;
	private String bookSubject;
	private List<NastedHelper> bookEditions;

	public MultifieldHelper2(Resource rc) {

		bookName = rc.getValueMap().get("bookname", String.class);

		bookSubject = rc.getValueMap().get("booksubject", String.class);

	}

	public String getBookName() {
		return bookName;
	}

	public String getBookSubject() {
		return bookSubject;
	}

	public List<NastedHelper> getBookEditions() {
		return bookEditions;
	}

	public void setBookEditions(List<NastedHelper> bookEditions) {
		this.bookEditions = bookEditions;
	}

}
