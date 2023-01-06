package com.adobe.aem.guides.vishnujan.core.models.interfaces;

import java.util.List;
import java.util.Map;

import com.adobe.aem.guides.vishnujan.core.helper.MultifieldHelper;
import com.adobe.aem.guides.vishnujan.core.helper.MultifieldHelper2;

public interface JanMfTraining1Interface {

	public List<String> getBooksauthor();

	public List<Map<String, String>> getBookDetailsWithMap();

	public List<MultifieldHelper> getBookDetailsWithBean();

	public List<MultifieldHelper2> getNestedMFWithBean();
}
