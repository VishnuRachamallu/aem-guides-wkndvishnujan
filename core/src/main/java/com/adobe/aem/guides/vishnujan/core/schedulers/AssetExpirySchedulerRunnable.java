package com.adobe.aem.guides.vishnujan.core.schedulers;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.aem.guides.vishnujan.core.utils.ResolverUtil;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;

@Designate(ocd = SchedulerConfiguration.class)
@Component(service = Runnable.class, immediate = true)
public class AssetExpirySchedulerRunnable implements Runnable {

	private static final Logger LOG = LoggerFactory.getLogger(AssetExpirySchedulerRunnable.class);
	private int schedulerId;

	@Reference
	ResourceResolverFactory resolverFactory;

	@Reference
	Scheduler scheduler;

	@Activate
	public void activate(SchedulerConfiguration configuration) {
		schedulerId = configuration.schedulerName().hashCode();
		addScheduler(configuration);
	}

	@Deactivate
	public void diactivate(SchedulerConfiguration configuration) {
		removeScheduler();
	}

	@Modified
	protected void modified(SchedulerConfiguration config) {
		removeScheduler();

		schedulerId = config.schedulerName().hashCode();
		addScheduler(config);
	}

	private void addScheduler(SchedulerConfiguration configuration) {
		// TODO Auto-generated method stub
		LOG.info("\n =====   Before activating========== \n");
		ScheduleOptions scheduleOptions = scheduler.EXPR(configuration.cornExpression());
		scheduleOptions.name(String.valueOf(schedulerId));
		scheduleOptions.canRunConcurrently(false);
		scheduler.schedule(this, scheduleOptions);
		LOG.info("\n =====  scheduler  added========== \n");
	}

	private void removeScheduler() {
		// TODO Auto-generated method stub
		scheduler.unschedule(String.valueOf(schedulerId));

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		LOG.info("\n ===== Asset Run Method started ========== \n");

		try {

			ResourceResolver resolver = ResolverUtil.newResourceResolver(resolverFactory);
			String status = "all good , created page version using custom workflow calling using api";
			WorkflowSession workflowSession = resolver.adaptTo(WorkflowSession.class);
			WorkflowModel workflowModel = workflowSession.getModel("/var/workflow/models/assets-expiry-notification");
			WorkflowData workflowData = workflowSession.newWorkflowData("JCR_PATH", "/content/dam/pavan-profile");

			status = workflowSession.startWorkflow(workflowModel, workflowData).getState();

		} catch (Exception e) {
			LOG.info("\n ===== Asset Run Method Exception========== \n");
		}

		LOG.info("\n ===== Asset Run Method ended========== \n");

	}

}
