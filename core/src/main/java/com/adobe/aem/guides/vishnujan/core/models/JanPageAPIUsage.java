package com.adobe.aem.guides.vishnujan.core.models;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Locale;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.WCMException;

//need to implement below methods
/*
getContentResource()
getContentResource(java.lang.String relPath)
getDeleted()
getDeletedBy()
getLanguage()
getLanguage(boolean ignoreContent)
getOffTime()
getOnTime()
getPageManager() //use of this in real time ?
getProperties(java.lang.String relPath)
getVanityUrl()
isHideInNav()
isValid()
listChildren(Filter<Page> filter) // how to use filters
timeUntilValid()
*/

@Model(adaptables = SlingHttpServletRequest.class, adapters = JanPageAPIUsage.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class JanPageAPIUsage {

	@Self
	SlingHttpServletRequest request;

	@SlingObject
	ResourceResolver resolver;

	// Page should be locked to verify unlock permission.
	// if page is not locked then it will return false for canUnlock question
	public boolean canUnlockMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/lock-unlock-testing");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.canUnlock();
	}

	public String getAbsoluteParentMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		Page absoluteParentPage = page.getAbsoluteParent(2);
		return absoluteParentPage.getName() + "  " + absoluteParentPage.getTitle();
	}

	// need to check
	public String getContentResourceMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());

		Resource resource2 = page.getContentResource();
		return resource2.getPath();
	}

	// need to check
	public String getContentResourceMethodRelativePath() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());

		Resource resource2 = page
				.getContentResource("page-api-usage/pageapimethods/jcr:content/root/container/container/pageapiusing");
		return resource2.getPath();
	}

	public int getDepthMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getDepth();
	}

	// need to check
	public Locale getgetLanguageMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getLanguage();
	}

	public Calendar getLastModifiedMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/pageapimethods");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getLastModified();
	}

	public String getLastModifiedByMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/pageapimethods");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getLastModifiedBy();
	}

	// getName,getTitle,getPageTitle,getNavigationTitle. when they are used
	public String getNameAndNavigationTitleMethods() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/tags-using");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getName() + " : " + page.getTitle() + " : " + page.getPageTitle() + " : "
				+ page.getNavigationTitle();
	}

	// need to check getNavigationTitle and getPageTitle. when they are used
	public String getParentPageMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/pageapimethods");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getParent().getTitle() + " : " + page.getPageTitle();
	}

	public String getParentPageMethodRelative() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/pageapimethods");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getParent(2).getTitle();
	}

	// need to check how to use getProperties(java.lang.String relPath) with
	// relative path
	public String getPropertiesMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getProperties().get("jcr:title", String.class);
	}

	public String getTagsMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/tags-using");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		Tag[] tag = page.getTags();
		String tagsfinal = "";
		for (Tag tag2 : tag) {
			tagsfinal = tagsfinal + tag2.getTitlePath() + ",";
		}
		return tagsfinal;
	}

	public boolean getTemplateMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/tags-using");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getTemplate().isAllowedChild(page.getTemplate());

		// practice isAllowed(Resource parent) and isAllowed(java.lang.String
		// parentPath) in template API
	}

	// how to use getVanityUrl
	public String getVanityUrlMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getVanityUrl();
	}

	public boolean hasChildMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.hasChild("en");
	}

	public boolean hasContentMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.hasContent();
	}

	public String listChildrenMethodPage() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us");

		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us");

		Iterator<Page> itter = page.listChildren(null, true);
		String names = "";
		while (itter.hasNext()) {
			names = names + itter.next().getName() + "\n";
		}

		return names;
	}

	public boolean isLockedMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/lock-unlock-testing");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.isLocked();
	}

	public String lockMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/lock-unlock-testing");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		String str = "locked";
		try {
			page.lock();
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String unlockMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/lock-unlock-testing");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		String str = "unlocked";
		try {
			page.unlock();
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String getLockOwnerMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/page-api-usage/lock-unlock-testing");
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage(resource.getPath());
		return page.getLockOwner();
	}
	
	//good sample for isValid . this page is not valid.
	public boolean isValidMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/restore-revision-usage");
		
		return page.isValid();
		
	}

}
