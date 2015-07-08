package com.marklogic.appdeployer.command.viewschemas;

import java.io.File;

import com.marklogic.appdeployer.command.AbstractResourceCommand;
import com.marklogic.appdeployer.command.CommandContext;
import com.marklogic.rest.mgmt.ResourceManager;
import com.marklogic.rest.mgmt.tasks.ViewSchemaManager;

public class ManageViewSchemasCommand extends AbstractResourceCommand {

    @Override
    protected File getResourcesDir(CommandContext context) {
        return new File(context.getAppConfig().getConfigDir().getBaseDir(), "view-schemas");
    }

    @Override
    protected ResourceManager getResourceManager(CommandContext context) {
        return new ViewSchemaManager(context.getManageClient(), context.getAppConfig().getContentDatabaseName());
    }

}