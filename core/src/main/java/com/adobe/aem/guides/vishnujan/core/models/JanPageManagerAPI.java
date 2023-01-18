package com.adobe.aem.guides.vishnujan.core.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.jcr.Node;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.Revision;
import com.day.cq.wcm.api.Template;
import com.day.cq.wcm.api.TemplateManager;
import com.day.cq.wcm.api.WCMException;

//http://localhost:4502/editor.html/content/vishnuwkndjan/us/en/page-manager-api/copy-pages-main/page-1.html

@Model(adaptables = SlingHttpServletRequest.class, adapters = JanPageManagerAPI.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class JanPageManagerAPI {

	@Self
	SlingHttpServletRequest request;

	@SlingObject
	ResourceResolver resolver;

	/*
	 * need to understand beforeName - the name of the next page. if null the page
	 * is ordered at the end.
	 * 
	 * copy(Page page, java.lang.String destination, java.lang.String beforeName,
	 * boolean shallow, boolean resolveConflict)
	 */
	// copy pages recursively
	public String copyMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/copy-pages-main/page-1");
		String str = "Succesfully copied";
		String destination = "/content/vishnuwkndjan/us/en/page-manager-api/copy-pages-child/";
		try {
			pageManager.copy(page, destination, null, false, true);

		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	// copy pages recursively and auto save . learn in which cases AutoSave usage is
	// implemented
	public String copyMethodwithAutoSave() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager
				.getPage("/content/vishnuwkndjan/us/en/page-manager-api/copy-pages-main/page-1-autosave");
		String str = "Succesfully copied";
		String destination = "/content/vishnuwkndjan/us/en/page-manager-api/copy-pages-child/";
		try {
			pageManager.copy(page, destination, null, false, true, true);

		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String createMethodPage() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		String parentPath = "/content/vishnuwkndjan/us/en/page-manager-api/new-page-creation-with-code-using-template";
		String pageName = "jaffanew";
		String template = "/conf/vishnuwkndjan/settings/wcm/templates/janfirsttemplate";
		String title = "New Page";

		String str = "succesfully created page";
		try {
			pageManager.create(parentPath, pageName, template, title);
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String createRivisionMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/create-rivision-usage");
		String str = "Succesfully Created Rivision";

		try {
			pageManager.createRevision(page);
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String createRivisionMethodWithLabelsAndComments() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager
				.getPage("/content/vishnuwkndjan/us/en/page-manager-api/create-rivision-usage-with-label-and-comment");
		String str = "Succesfully Created Rivision with label and comment";

		try {
			pageManager.createRevision(page, "Label1", "comment 1");
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String deleteMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager
				.getPage("/content/vishnuwkndjan/us/en/page-manager-api/new-page-creation-with-code-using-template");
		String str = "Succesfully Deleted page content / page itself recursively";
		Iterator<Page> temp = page.listChildren();

		while (temp.hasNext()) {
			try {
				pageManager.delete(temp.next(), false);
			} catch (WCMException e) { // TODO Auto-generated catch block
				str = e.getMessage();
			}
		}
		page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/copy-pages-child");
		temp = page.listChildren();

		while (temp.hasNext()) {
			try {
				pageManager.delete(temp.next(), false);
			} catch (WCMException e) { // TODO Auto-generated catch block
				str = e.getMessage();
			}
		}

		return str;
	}

	public String moveMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);

		String parentPath = "/content/vishnuwkndjan/us/en/page-manager-api/move-pages-main";
		String pageName = "page1";
		String template = "/conf/vishnuwkndjan/settings/wcm/templates/janfirsttemplate";
		String title = "New Page";

		Page newPage = null;

		String str = "Succesfully Moved Page";
		try {
			newPage = pageManager.create(parentPath, pageName, template, title);
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}

		String destination = "/content/vishnuwkndjan/us/en/page-manager-api/move-pages-child/lorry";

		try {
			pageManager.move(newPage, destination, null, false, true, null);
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			str = e.getMessage();
		}
		return str;
	}

	public String getContainingPageMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		return pageManager.getContainingPage(
				"/content/vishnuwkndjan/us/en/multifield/simplemf-using-map/jcr:content/root/container/container/janmftraining/bookdetailswithmap")
				.getName();

	}

	public List<String> workingWithRestoreRevision() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/restore-revision");

		List<String> rivisionsId = new ArrayList<>();
		try {

			// pageManager.createRevision(page);
			Collection<Revision> revisions = pageManager.getRevisions(page.getPath(), null);
			for (Revision revision : revisions) {
				rivisionsId.add(revision.getId());
			}

			pageManager.restore(page.getPath(), "92668afa-6674-4fcd-b272-968151997f7d");
			// empty content "92668afa-6674-4fcd-b272-968151997f7d"
			// some text content revision "7a49ff5f-e415-4d05-b245-40252ededc5a"
		} catch (WCMException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return rivisionsId;
	}

	// page revisions test sample
	public List<String> VishnuRevisions() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/delete-sample-page");
		List<String> rivisionsIds = new ArrayList<>();
		try {
			Collection<Revision> revisions = pageManager.getRevisions(page.getPath(), null);
			for (Revision revision : revisions) {
				rivisionsIds.add(revision.getId());
			}
			pageManager.restore(page.getPath(), "e8e448c3-d688-45bf-bf21-8401771c9909");

		} catch (WCMException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		return rivisionsIds;
	}

	// is not working properly , how to pass calendar here
	public String touchMethod() {
		PageManager pageManager = resolver.adaptTo(PageManager.class);
		Page page = pageManager.getPage("/content/vishnuwkndjan/us/en/page-manager-api/touch-method-usage");

		String str = "touched succesfully";

		try {
			pageManager.touch((Node) page, true, Calendar.getInstance(), false);
		} catch (WCMException e) {
			str = e.getMessage();
		}

		return str;
	}
}
