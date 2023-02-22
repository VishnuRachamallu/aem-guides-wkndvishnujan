package com.adobe.aem.guides.vishnujan.core.models;



import java.util.List;
import java.util.Map;



public interface AuthorBooks {
	List<Map<String, String>> getBookDetailsWithMap();

	String getAuthorName();

	List<String> getAuthorBooks();

	List<MultifieldHelper> getBookDetailsWithBean();

	
}
