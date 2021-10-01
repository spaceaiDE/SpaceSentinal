package de.spaceai.spacesentinal.api;

import de.spaceai.spacesentinal.SpaceSentinal;
import de.spaceai.spacesentinal.api.service.IServiceManager;
import de.spaceai.spacesentinal.service.Service;

public class SpaceSentinalAPI implements ISpaceSentinalAPI {

    private final SpaceSentinal spaceSentinal;
    private static SpaceSentinalAPI api;

    public SpaceSentinalAPI(SpaceSentinal spaceSentinal) {
        this.spaceSentinal = spaceSentinal;
        api = this;
    }

    public static SpaceSentinalAPI getApi() {
        return api;
    }

    @Override
    public IServiceManager getServiceManager() {
        return this.spaceSentinal.getServiceManager();
    }

    @Override
    public <T extends Service> T getService(String serviceName) {
        return this.spaceSentinal.getServiceManager().getService(serviceName);
    }
}
