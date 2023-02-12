package com.adobe.aem.guides.vishnujan.core.schedulers;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "aaaa config 2 ", description = "Asset Expiry Scheduler config description")

public @interface SchedulerConfiguration2 {

	@AttributeDefinition(name = "Cron Expression", description = "Cron Expression used by Scheduler", type = AttributeType.STRING)
	public String cornExpression() default "*/5 * * * * ?";

	@AttributeDefinition(name = "Scheduler Name", description = "Name of Scheduler", type = AttributeType.STRING)
	public String schedulerName() default "Asset Expiry Custom  Scheduler config";

}
