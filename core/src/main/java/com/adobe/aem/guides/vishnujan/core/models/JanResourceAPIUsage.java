package com.adobe.aem.guides.vishnujan.core.models;

import java.util.Iterator;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceMetadata;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class, adapters = JanResourceAPIUsage.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class JanResourceAPIUsage {

	@SlingObject
	ResourceResolver resolver;

	@Self
	SlingHttpServletRequest request;

	public String getChildMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/");
		// passing relative path "multifield" instead of absolute path as given above
		Resource tempResource = resource.getChild("multifield");
		return tempResource.getName();
	}

	public String getChildrenMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/");

		// to get current component as resource and access its values
		/*
		 * Resource res=request.getResource(); res.getPath()
		 */

		Iterable<Resource> tempResource = resource.getChildren();
		String childPageNames = "";
		for (Resource resource2 : tempResource) {
			childPageNames = childPageNames + resource2.getName() + "\n";
		}
		return childPageNames;
	}

	public String getParentMethod() {
		Resource resource = resolver
				.getResource("/content/vishnuwkndjan/us/en/resource-api-usage/get-child-and-childern");
		Resource tempResource = resource.getParent();
		return tempResource.getName();
	}

	public String getResourceMetadataMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/resource-api-usage");
		ResourceMetadata resourceMetadata = resource.getResourceMetadata();
		return resourceMetadata.getResolutionPath();
	}

	// need to check below method
	public String getResourceSuperTypeMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/resource-api-usage");
		String resourceSupertype = resource.getResourceSuperType();
		return resourceSupertype;
	}

	public String getResourceTypeMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/resource-api-usage/jcr:content");

		String resourceType = resource.getResourceType();
		return resourceType;
	}

	public String getValueMapValueMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/resource-api-usage");
		return resource.getValueMap().get("jcr:createdBy", String.class);

	}

	public boolean hasChildrenMethod() {
		Resource resource = resolver.getResource(
				"/content/vishnuwkndjan/us/en/resource-api-usage/get-child-and-childern/jcr:content/root/container/container/resourceapiusing");
		return resource.hasChildren();

	}

	public boolean isResourceTypeMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us/en/resource-api-usage");
		return resource.isResourceType("cq:Page");
	}

	public String listChildrenMethod() {
		Resource resource = resolver.getResource("/content/vishnuwkndjan/us");
		Iterator<Resource> itter = resource.listChildren();
		String names = "";
		while (itter.hasNext()) {
			names = names + itter.next().getName() + "\n";
		}

		return names;
	}

}
