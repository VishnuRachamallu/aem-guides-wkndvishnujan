package com.adobe.aem.guides.vishnujan.core.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnujan.core.helper.MultifieldHelper;
import com.adobe.aem.guides.vishnujan.core.helper.MultifieldHelper2;
import com.adobe.aem.guides.vishnujan.core.helper.NastedHelper;
import com.adobe.aem.guides.vishnujan.core.models.interfaces.JanMfTraining1Interface;

@Model(adaptables = SlingHttpServletRequest.class, adapters = JanMfTraining1Interface.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class JanMfTraining1 implements JanMfTraining1Interface {

	@Inject
	Resource componenetResource;

	@ValueMapValue
	private List<String> booksauthor;
	
	@Inject
    @Named("log")
    private Logger logger;

	@Override
	public List<String> getBooksauthor() {
		// TODO Auto-generated method stub
		if (booksauthor != null)
			return new ArrayList<>(booksauthor);
		else
			return Collections.emptyList();
	}

	@Override
	public List<Map<String, String>> getBookDetailsWithMap() {
		// TODO Auto-generated method stub

		List<Map<String, String>> totalList = new ArrayList<>();
		Resource resource = componenetResource.getChild("bookdetailswithmap");

		for (Resource rc : resource.getChildren()) {
			Map<String, String> map = new HashMap<>();
			map.put("bookname", rc.getValueMap().get("bookname", String.class));
			map.put("booksubject", rc.getValueMap().get("booksubject", String.class));
			map.put("publishyear", rc.getValueMap().get("publishyear", String.class));

			totalList.add(map);
		}

		return totalList;
	}

	@Override
	public List<MultifieldHelper> getBookDetailsWithBean() {
		// TODO Auto-generated method stub

		List<MultifieldHelper> totalList = new ArrayList<>();
		Resource resource = componenetResource.getChild("bookdetailswithbean");

		for (Resource rc : resource.getChildren()) {

			MultifieldHelper multifieldHelper = new MultifieldHelper(rc);

			totalList.add(multifieldHelper);
		}

		return totalList;
	}

	@Override
	public List<MultifieldHelper2> getNestedMFWithBean() {
		// TODO Auto-generated method stub
		
		Logger Log=LoggerFactory.getLogger(getClass());
		List<MultifieldHelper2> nestedeMF = new ArrayList<>();
		Resource resource = componenetResource.getChild("bookdetailswithnastedmf");

		for (Resource rc : resource.getChildren()) {

			MultifieldHelper2 multifieldHelper2 = new MultifieldHelper2(rc);

			List<NastedHelper> bookNastedList = new ArrayList<>();
			Resource nastedResource = rc.getChild("bookeditons");

			for (Resource nasRes : nastedResource.getChildren()) {
				bookNastedList.add(new NastedHelper(nasRes));

			}
			multifieldHelper2.setBookEditions(bookNastedList);

			nestedeMF.add(multifieldHelper2);
		}

		Log.info("\nhello vishnu");
		return nestedeMF;
	}

}
