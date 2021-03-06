package com.marklogic.appdeployer.command.forests;

import com.marklogic.appdeployer.AppConfig;
import com.marklogic.appdeployer.command.CommandContext;
import com.marklogic.appdeployer.command.databases.DeployDatabaseCommand;
import com.marklogic.mgmt.ManageClient;
import com.marklogic.mgmt.ManageConfig;

/**
 * Not an actual test, as this depends on an environment with multiple hosts, which is normally not the case on a
 * development machine.
 */
public class ConfigureForestReplicasDebug {

    public static void main(String[] args) {
        final String host = args[0];
        final String password = args[1];

        ManageConfig config = new ManageConfig(host, 8002, "admin", password);
        ManageClient manageClient = new ManageClient(config);
        AppConfig appConfig = new AppConfig();
        appConfig.setDatabaseNamesAndReplicaCounts("testdb,1");
        CommandContext context = new CommandContext(appConfig, manageClient, null);

        DeployDatabaseCommand ddc = new DeployDatabaseCommand();
        ddc.setForestsPerHost(1);
        ddc.setCreateDatabaseWithoutFile(true);
        ddc.setDatabaseName("testdb");

        ConfigureForestReplicasCommand cfrc = new ConfigureForestReplicasCommand();

        // Deploy the database, and then configure replicas
        ddc.execute(context);
        cfrc.execute(context);

        // Then delete the replicas, and then undeploy the database
        cfrc.undo(context);
        ddc.undo(context);
    }
}
